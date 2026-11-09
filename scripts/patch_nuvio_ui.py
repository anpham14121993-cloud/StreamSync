#!/usr/bin/env python3
"""
patch_nuvio_ui.py:
Applies Nuvio smooth UI & performance optimizations directly to decompiled StreamSync APK:
1. ContinueWatchingCard: 16:9 landscape aspect ratio (224dp x 126dp) and smooth pre-rendering (beyondViewportPageCount = 8).
2. Coil Image Prefetching: Throttles prefetch queue to 15 items, eliminating UI freezes.
3. Android TV Leanback: Adds LEANBACK_LAUNCHER, leanback feature, touchscreen optional, and 320x180 banner.
4. Single-swipe Edge Back & Screen-specific Exit Dialog:
   - Removes systemBars hide() so swipe from right edge works on 1st swipe.
   - Shows exit confirmation dialog ("Thoát ứng dụng", "Đồng ý", "Hủy") ONLY on the Home (catalog) screen.
   - On all other screens (Detail, Settings, Player, Addons...), performs normal back navigation (popBackStack).
5. Player Default Decoder: Sets default decoder to "Ưu tiên bộ giải mã thiết bị" (PREFER_DEVICE).
6. Integrations & DNS/ByeDPI: Merges DNS-over-HTTPS and ByeDPI into the "Tích hợp" settings section.
7. Account & Supabase Sync: Updates settings to Nuvio-style "Tài khoản & Đồng bộ Supabase" (Cloud sync & multi-device settings).
8. In-App Updater: Adds "Cập nhật ứng dụng" checking releases from repo anpham14121993-cloud/StreamSync with direct download & install dialog.
9. Upgrade & Overwrite Support: Increments versionCode to 189 and versionName to 1.8.9 with REQUEST_INSTALL_PACKAGES permission.
10. Native Libraries: Injects 64-bit arm64-v8a and 32-bit armeabi-v7a native libraries (FFmpeg JNI, AC3, Subtitle translation).
"""

import os
import sys
import re
import zlib
import struct
import shutil

def log(msg):
    print(f"[PATCH-NUVIO] {msg}")

def create_tv_banner_png(width=320, height=180):
    """Creates a sleek 320x180 TV banner PNG (Dark blue background with red play accent)"""
    raw_data = bytearray()
    for y in range(height):
        raw_data.append(0)  # No filter
        for x in range(width):
            bg_r = 13 + int((x / width) * 9)
            bg_g = 17 + int((y / height) * 10)
            bg_b = 23 + int((y / height) * 11)
            
            if 140 <= x <= 185 and abs(y - 90) <= (x - 140) * 0.5:
                raw_data.extend(bytes((229, 9, 20)))  # Red #E50914
            else:
                raw_data.extend(bytes((bg_r, bg_g, bg_b)))

    ihdr = struct.pack('>IIBBBBB', width, height, 8, 2, 0, 0, 0)
    ihdr_chunk = b'IHDR' + ihdr
    ihdr_crc = struct.pack('>I', zlib.crc32(ihdr_chunk) & 0xffffffff)

    compressed = zlib.compress(bytes(raw_data), 9)
    idat_chunk = b'IDAT' + compressed
    idat_crc = struct.pack('>I', zlib.crc32(idat_chunk) & 0xffffffff)

    iend_chunk = b'IEND'
    iend_crc = struct.pack('>I', zlib.crc32(iend_chunk) & 0xffffffff)

    return (b'\x89PNG\r\n\x1a\n' +
            struct.pack('>I', len(ihdr)) + ihdr_chunk + ihdr_crc +
            struct.pack('>I', len(compressed)) + idat_chunk + idat_crc +
            struct.pack('>I', 0) + iend_chunk + iend_crc)

def find_files(root_dir, pattern):
    matches = []
    regex = re.compile(pattern)
    for dirpath, _, filenames in os.walk(root_dir):
        for fname in filenames:
            if regex.search(fname):
                matches.append(os.path.join(dirpath, fname))
    return matches

def patch_continue_watching(unpack_dir):
    """Patch ContinueWatchingCard to 16:9 landscape aspect ratio (224dp x 126dp) and smooth pre-rendering"""
    log("Searching for ContinueWatchingCardKt smali files...")
    targets = find_files(unpack_dir, r"^ContinueWatchingCardKt.*\.smali$")
    if not targets:
        log("WARNING: ContinueWatchingCardKt smali file not found!")
        return False

    patched_count = 0
    for target in targets:
        with open(target, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        changed = False

        # 1. Float constants (124.0f -> 224.0f, 186.0f -> 126.0f)
        if "0x42f80000" in content:
            content = content.replace("0x42f80000", "0x43600000")
            log(f"Replaced 0x42f80000 (124.0f) with 0x43600000 (224.0f) in {os.path.basename(target)}")
            changed = True

        if "0x433a0000" in content:
            content = content.replace("0x433a0000", "0x42fc0000")
            log(f"Replaced 0x433a0000 (186.0f) with 0x42fc0000 (126.0f) in {os.path.basename(target)}")
            changed = True

        # 2. Integer constants
        if "POSTER_WIDTH" in content:
            new_content = re.sub(r"(const/16\s+v\d+,\s*)0x7c", r"\g<1>0xe0", content)
            if new_content != content:
                content = new_content
                log(f"Patched POSTER_WIDTH integer const 0x7c -> 0xe0 in {os.path.basename(target)}")
                changed = True

        if "POSTER_HEIGHT" in content:
            new_content = re.sub(r"(const/16\s+v\d+,\s*)0xba", r"\g<1>0x7e", content)
            if new_content != content:
                content = new_content
                log(f"Patched POSTER_HEIGHT integer const 0xba -> 0x7e in {os.path.basename(target)}")
                changed = True

        # 3. Increase beyondViewportPageCount in ContinueWatchingPager to 8 items for catalog-like smooth sliding
        if "HorizontalPager-xYaah8o" in content:
            pager_patched = re.sub(
                r"(HorizontalPager-xYaah8o[\s\S]*?)(const/16\s+v\d+,\s*)0x0",
                r"\g<1>\g<2>0x8",
                content,
                count=1
            )
            if pager_patched != content:
                content = pager_patched
                log(f"Increased beyondViewportPageCount to 8 in {os.path.basename(target)}")
                changed = True

        if changed:
            with open(target, 'w', encoding='utf-8') as f:
                f.write(content)
            patched_count += 1

    return patched_count > 0

def patch_image_prefetch(unpack_dir):
    """Throttles Coil image prefetch from all items to top 15 items"""
    log("Searching for StreamSyncImageLoaderKt smali files...")
    targets = find_files(unpack_dir, r"^StreamSyncImageLoaderKt.*\.smali$")
    if not targets:
        log("WARNING: StreamSyncImageLoaderKt smali file not found!")
        return False

    patched = False
    for target in targets:
        with open(target, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        if "prefetchPosters" in content:
            pattern = re.compile(r"(\.method\s+public\s+static\s+final\s+prefetchPosters\(Landroid/content/Context;Ljava/util/List;\)V.*?)(\s+invoke-static\s*\{p0,\s*\"context\"\})", re.DOTALL)
            throttle_smali = r"""\1
    # [NUVIO PATCH] Throttle prefetch to top 15 items to prevent GC frame drops
    const/16 v0, 0xf
    invoke-static {p1, v0}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;
    move-result-object p1
\2"""
            new_content, count = pattern.subn(throttle_smali, content)
            if count > 0:
                with open(target, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                log(f"Successfully injected 15-item throttle into prefetchPosters in {os.path.basename(target)}")
                patched = True

    return patched

def patch_single_swipe_and_exit_dialog(unpack_dir):
    """
    1. Removes controller.hide(systemBars) to fix the 'swipe twice from right edge' bug.
    2. Stores sNavController in MainActivity so back presses from sub-screens pop the backstack,
       and ONLY the home screen (where previousBackStackEntry == null) shows the Nuvio exit dialog.
    """
    log("Searching for MainActivity.smali and MainActivityKt.smali...")
    main_activity_targets = find_files(unpack_dir, r"^MainActivity\.smali$")
    if not main_activity_targets:
        log("WARNING: MainActivity.smali not found!")
        return False

    main_activity_path = main_activity_targets[0]
    target_dir = os.path.dirname(main_activity_path)

    with open(main_activity_path, 'r', encoding='utf-8', errors='ignore') as f:
        content = f.read()

    changed = False

    # 1. Add static fields for NavController and Activity instance
    if "sNavController" not in content:
        content = content.replace(
            "# static fields\n",
            "# static fields\n.field public static sNavController:Landroidx/navigation/NavHostController;\n.field public static sInstance:Lcom/streamsync/app/MainActivity;\n"
        )
        changed = True

    # 2. Remove controller.hide(systemBars) to enable immediate 1-swipe back gesture
    if "Landroidx/core/view/WindowInsetsControllerCompat;->hide(I)V" in content:
        pattern = re.compile(r"(invoke-virtual\s*\{[^\}]+\},\s*Landroidx/core/view/WindowInsetsControllerCompat;->hide\(I\)V)")
        content = pattern.sub(r"# [NUVIO PATCH] \1", content)
        log("Disabled WindowInsetsControllerCompat.hide() to enable single-swipe edge back gesture.")
        changed = True

    # 3. Change setSystemBarsBehavior from 1 to 2
    if "setSystemBarsBehavior" in content:
        pattern = re.compile(r"(const/4\s+v\d+,\s*)0x1(\s+invoke-virtual\s*\{[^\}]+\},\s*Landroidx/core/view/WindowInsetsControllerCompat;->setSystemBarsBehavior)")
        content = pattern.sub(r"\g<1>0x2\2", content)
        changed = True

    # 4. Register HomeBackCallback and set sInstance in onCreate(Bundle)
    if "HomeBackCallback" not in content:
        callback_inject = """
    # [NUVIO PATCH] Store MainActivity instance and register HomeBackCallback
    sput-object p0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    invoke-virtual {p0}, Lcom/streamsync/app/MainActivity;->getOnBackPressedDispatcher()Landroidx/activity/OnBackPressedDispatcher;
    move-result-object v0
    new-instance v1, Lcom/streamsync/app/HomeBackCallback;
    invoke-direct {v1, p0}, Lcom/streamsync/app/HomeBackCallback;-><init>(Lcom/streamsync/app/MainActivity;)V
    invoke-virtual {v0, p0, v1}, Landroidx/activity/OnBackPressedDispatcher;->addCallback(Landroidx/lifecycle/LifecycleOwner;Landroidx/activity/OnBackPressedCallback;)V
"""
        onCreate_pattern = re.compile(r"(invoke-super\s*\{p0,\s*p1\},\s*Landroidx/activity/ComponentActivity;->onCreate\(Landroid/os/Bundle;\)V)", re.MULTILINE)
        content = onCreate_pattern.sub(lambda m: m.group(1) + callback_inject, content)
        log("Injected HomeBackCallback and sInstance into MainActivity.onCreate().")
        changed = True

    # 5. Overwrite onBackPressed() method in MainActivity to check previous backstack entry
    on_back_pressed_smali = """
.method public onBackPressed()V
    .registers 4

    # [NUVIO PATCH] Only show exit dialog at Home, otherwise pop backstack
    sget-object v0, Lcom/streamsync/app/MainActivity;->sNavController:Landroidx/navigation/NavHostController;
    if-eqz v0, :cond_dialog

    invoke-virtual {v0}, Landroidx/navigation/NavHostController;->getPreviousBackStackEntry()Landroidx/navigation/NavBackStackEntry;
    move-result-object v1
    if-eqz v1, :cond_dialog

    invoke-virtual {v0}, Landroidx/navigation/NavHostController;->popBackStack()Z
    return-void

    :cond_dialog
    new-instance v0, Landroid/app/AlertDialog$Builder;
    invoke-direct {v0, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string v1, "Tho\\u00e1t \\u1ee9ng d\\u1ee5ng"
    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    const-string v1, "B\\u1ea1n c\\u00f3 ch\\u1eafc ch\\u1eafn mu\\u1ed1n tho\\u00e1t \\u1ee9ng d\\u1ee5ng kh\\u00f4ng?"
    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    const-string v1, "\\u0110\\u1ed3ng \\u00fd"
    new-instance v2, Lcom/streamsync/app/ExitConfirmListener;
    invoke-direct {v2, p0}, Lcom/streamsync/app/ExitConfirmListener;-><init>(Landroid/app/Activity;)V
    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    const-string v1, "H\\u1ee7y"
    const/4 v2, 0x0
    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    return-void
.end method
"""
    if "onBackPressed()V" in content:
        content = re.sub(r"\.method public onBackPressed\(\)V[\s\S]*?\.end method", lambda _: on_back_pressed_smali.strip(), content)
        changed = True
    else:
        content += "\n" + on_back_pressed_smali
        changed = True

    if changed:
        with open(main_activity_path, 'w', encoding='utf-8') as f:
            f.write(content)

    # 6. Inject sNavController capture into MainActivityKt.StreamSyncNavHost
    main_kt_targets = find_files(unpack_dir, r"^MainActivityKt\.smali$")
    if main_kt_targets:
        with open(main_kt_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            kt_content = f.read()
        if "sput-object p0, Lcom/streamsync/app/MainActivity;->sNavController" not in kt_content:
            target_method = r"(\.method public static final StreamSyncNavHost\(Landroidx/navigation/NavHostController;Landroidx/compose/runtime/Composer;I\)V[\s\S]*?\.registers\s+\d+[\s\S]*?)(const-string v0, \"navController\")"
            kt_content_patched = re.sub(target_method, lambda m: m.group(1) + "sput-object p0, Lcom/streamsync/app/MainActivity;->sNavController:Landroidx/navigation/NavHostController;\n    " + m.group(2), kt_content, count=1)
            if kt_content_patched != kt_content:
                with open(main_kt_targets[0], 'w', encoding='utf-8') as f:
                    f.write(kt_content_patched)
                log("Captured NavController in MainActivityKt.StreamSyncNavHost.")

    # 7. Create ExitConfirmListener.smali
    exit_listener_path = os.path.join(target_dir, "ExitConfirmListener.smali")
    exit_listener_smali = """.class public Lcom/streamsync/app/ExitConfirmListener;
.super Ljava/lang/Object;
.implements Landroid/content/DialogInterface$OnClickListener;

.field private final activity:Landroid/app/Activity;

.method public constructor <init>(Landroid/app/Activity;)V
    .registers 2
    .param p1, "activity"    # Landroid/app/Activity;

    .prologue
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    iput-object p1, p0, Lcom/streamsync/app/ExitConfirmListener;->activity:Landroid/app/Activity;
    return-void
.end method

.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    iget-object v0, p0, Lcom/streamsync/app/ExitConfirmListener;->activity:Landroid/app/Activity;
    if-eqz v0, :cond_0
    invoke-virtual {v0}, Landroid/app/Activity;->finish()V
    :cond_0
    return-void
.end method
"""
    with open(exit_listener_path, 'w', encoding='utf-8') as f:
        f.write(exit_listener_smali)

    # 8. Create HomeBackCallback.smali with check for previous backstack entry
    home_callback_path = os.path.join(target_dir, "HomeBackCallback.smali")
    home_callback_smali = """.class public Lcom/streamsync/app/HomeBackCallback;
.super Landroidx/activity/OnBackPressedCallback;

.field private final activity:Lcom/streamsync/app/MainActivity;

.method public constructor <init>(Lcom/streamsync/app/MainActivity;)V
    .registers 3
    .param p1, "activity"    # Lcom/streamsync/app/MainActivity;

    .prologue
    const/4 v0, 0x1
    invoke-direct {p0, v0}, Landroidx/activity/OnBackPressedCallback;-><init>(Z)V
    iput-object p1, p0, Lcom/streamsync/app/HomeBackCallback;->activity:Lcom/streamsync/app/MainActivity;
    return-void
.end method

.method public handleOnBackPressed()V
    .registers 4

    .prologue
    # If not on home screen (previous backstack entry exists), pop backstack
    sget-object v0, Lcom/streamsync/app/MainActivity;->sNavController:Landroidx/navigation/NavHostController;
    if-eqz v0, :cond_dialog

    invoke-virtual {v0}, Landroidx/navigation/NavHostController;->getPreviousBackStackEntry()Landroidx/navigation/NavBackStackEntry;
    move-result-object v1
    if-eqz v1, :cond_dialog

    invoke-virtual {v0}, Landroidx/navigation/NavHostController;->popBackStack()Z
    return-void

    :cond_dialog
    # On Home screen: show Nuvio exit confirmation dialog
    iget-object v0, p0, Lcom/streamsync/app/HomeBackCallback;->activity:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_end

    new-instance v1, Landroid/app/AlertDialog$Builder;
    invoke-direct {v1, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string v2, "Tho\\u00e1t \\u1ee9ng d\\u1ee5ng"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    const-string v2, "B\\u1ea1n c\\u00f3 ch\\u1eafc ch\\u1eafn mu\\u1ed1n tho\\u00e1t \\u1ee9ng d\\u1ee5ng kh\\u00f4ng?"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    const-string v2, "\\u0110\\u1ed3ng \\u00fd"
    new-instance v3, Lcom/streamsync/app/ExitConfirmListener;
    invoke-direct {v3, v0}, Lcom/streamsync/app/ExitConfirmListener;-><init>(Landroid/app/Activity;)V
    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    const-string v2, "H\\u1ee7y"
    const/4 v3, 0x0
    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    :cond_end
    return-void
.end method
"""
    with open(home_callback_path, 'w', encoding='utf-8') as f:
        f.write(home_callback_smali)

    return True

def patch_default_decoder(unpack_dir):
    """Sets default decoder preference to PREFER_DEVICE (Hardware/Device decoder priority)"""
    log("Setting default decoder preference to PREFER_DEVICE...")
    targets = find_files(unpack_dir, r"^PlaybackSettingsStore.*\.smali$")
    if not targets:
        log("WARNING: PlaybackSettingsStore.smali not found!")
        return False

    for target in targets:
        with open(target, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        changed = False
        if 'const-string v2, "PREFER_APP"' in content:
            content = content.replace('const-string v2, "PREFER_APP"', 'const-string v2, "PREFER_DEVICE"')
            changed = True

        if "sget-object v1, Lcom/streamsync/app/settings/DecoderPreference;->PREFER_APP:" in content:
            content = content.replace(
                "sget-object v1, Lcom/streamsync/app/settings/DecoderPreference;->PREFER_APP:",
                "sget-object v1, Lcom/streamsync/app/settings/DecoderPreference;->PREFER_DEVICE:"
            )
            changed = True

        if changed:
            with open(target, 'w', encoding='utf-8') as f:
                f.write(content)
            log(f"Successfully updated default decoder in {os.path.basename(target)}")

    return True

def patch_settings_integrations_and_account_and_updater(unpack_dir):
    """
    1. Merges DNS & ByeDPI into the 'Tích hợp' section.
    2. Updates Account to Nuvio-style 'Tài khoản & Đồng bộ Supabase'.
    3. Adds in-app update checker connected to anpham14121993-cloud/StreamSync repo.
    """
    log("Patching settings: merging DNS & ByeDPI into Tích hợp, adding Account & Updater...")

    # 1. Create CombinedIntegrationsLambda.smali
    settings_dirs = find_files(unpack_dir, r"^SettingsScreenKt.*\.smali$")
    if not settings_dirs:
        log("WARNING: SettingsScreenKt not found!")
        return False

    target_dir = os.path.dirname(settings_dirs[0])

    combined_lambda_path = os.path.join(target_dir, "CombinedIntegrationsLambda.smali")
    combined_lambda_smali = """.class public Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;
.super Ljava/lang/Object;
.source "CombinedIntegrationsLambda.smali"
.implements Lkotlin/jvm/functions/Function2;

.field public static sDnsLambda:Lkotlin/jvm/functions/Function2;
.field public static sIntegrationsLambda:Lkotlin/jvm/functions/Function2;

.method public constructor <init>()V
    .registers 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public invoke(Landroidx/compose/runtime/Composer;I)V
    .registers 5
    # Render DNS & ByeDPI first
    sget-object v0, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->sDnsLambda:Lkotlin/jvm/functions/Function2;
    if-eqz v0, :cond_0
    const/4 v1, 0x0
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
    move-result-object v1
    invoke-interface {v0, p1, v1}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :cond_0
    # Render Badges & Provider logo second
    sget-object v0, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->sIntegrationsLambda:Lkotlin/jvm/functions/Function2;
    if-eqz v0, :cond_1
    const/4 v1, 0x0
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
    move-result-object v1
    invoke-interface {v0, p1, v1}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :cond_1
    return-void
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 4
    check-cast p1, Landroidx/compose/runtime/Composer;
    check-cast p2, Ljava/lang/Number;
    invoke-virtual {p2}, Ljava/lang/Number;->intValue()I
    move-result v0
    invoke-virtual {p0, p1, v0}, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->invoke(Landroidx/compose/runtime/Composer;I)V
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method
"""
    with open(combined_lambda_path, 'w', encoding='utf-8') as f:
        f.write(combined_lambda_smali)

    # 2. Patch SettingsScreenKt$SettingsScreen$2.smali
    settings_screen_2_targets = find_files(unpack_dir, r"^SettingsScreenKt\$SettingsScreen\$2\.smali$")
    if settings_screen_2_targets:
        s2_path = settings_screen_2_targets[0]
        with open(s2_path, 'r', encoding='utf-8', errors='ignore') as f:
            s2_content = f.read()

        # Save DNS lambda and skip standalone DNS-ByPassDPI group
        pattern_dns_group = re.compile(
            r"(const-string\s+v\d+,\s*\"DNS-ByPassDPI\"[\s\S]*?)(invoke-static/range\s*\{[^\}]+\},\s*Lcom/streamsync/app/ui/settings/SettingsScreenKt;->access\$SettingsGroup[^\n]+)",
            re.MULTILINE
        )
        if pattern_dns_group.search(s2_content):
            s2_content = pattern_dns_group.sub(
                lambda m: "sput-object v49, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->sDnsLambda:Lkotlin/jvm/functions/Function2;\n    # [NUVIO PATCH] Merged into Tích hợp card\n    # " + m.group(2),
                s2_content
            )
            log("Merged DNS-ByPassDPI group into CombinedIntegrationsLambda.")

        # Update Integrations group to use CombinedIntegrationsLambda and updated description
        pattern_int_group = re.compile(
            r"(const-string\s+v\d+,\s*\"T\\u00edch h\\u1ee3p\"[\s\S]*?const-string\s+v\d+,\s*\")[^\"]+(\")",
            re.MULTILINE
        )
        s2_content = pattern_int_group.sub(
            lambda m: m.group(1) + "DNS-over-HTTPS, ByeDPI, huy hi\\u1ec7u ch\\u1ea5t l\\u01b0\\u1ee3ng v\\u00e0 nh\\u00e3n addon" + m.group(2),
            s2_content
        )

        pattern_int_invoke = re.compile(
            r"(new-instance\s+v\d+,\s*Lcom/streamsync/app/ui/settings/SettingsScreenKt\$SettingsScreen\$2\$1\$6;[\s\S]*?move-object/from16\s+v49,\s*v\d+)",
            re.MULTILINE
        )
        if pattern_int_invoke.search(s2_content):
            s2_content = pattern_int_invoke.sub(
                lambda m: m.group(1) + "\n    sput-object v49, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->sIntegrationsLambda:Lkotlin/jvm/functions/Function2;\n    new-instance v0, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;\n    invoke-direct {v0}, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;-><init>()V\n    move-object/from16 v49, v0",
                s2_content
            )

        with open(s2_path, 'w', encoding='utf-8') as f:
            f.write(s2_content)

    # 3. Patch SettingsScreenKt$SettingsScreen$2$1$4.smali (Account & In-App Updater)
    s4_targets = find_files(unpack_dir, r"^SettingsScreenKt\$SettingsScreen\$2\$1\$4\.smali$")
    if s4_targets:
        s4_path = s4_targets[0]
        with open(s4_path, 'r', encoding='utf-8', errors='ignore') as f:
            s4_content = f.read()

        # Update "Máy chủ đồng bộ" -> "Tài khoản & Đồng bộ Supabase"
        if "M\\u00e1y ch\\u1ee7 \\u0111\\u1ed3ng b\\u1ed9" in s4_content:
            s4_content = s4_content.replace(
                "M\\u00e1y ch\\u1ee7 \\u0111\\u1ed3ng b\\u1ed9",
                "T\\u00e0i kho\\u1ea3n & \\u0110\\u1ed3ng b\\u1ed9 Supabase"
            )
            s4_content = s4_content.replace(
                "G\\u1eedi ti\\u1ebfn \\u0111\\u1ed9 xem l\\u00ean m\\u00e1y ch\\u1ee7 c\\u1ee7a b\\u1ea1n, ho\\u1eb7c d\\u00f9ng addon sync",
                "T\\u00e0i kho\\u1ea3n Supabase, \\u0111\\u1ed3ng b\\u1ed9 ti\\u1ebfn \\u0111\\u1ed9 xem v\\u00e0 c\\u1ea5u h\\u00ecnh \\u0111a thi\\u1ebft b\\u1ecb"
            )
            log("Updated Account & Supabase sync row in Settings.")

        # Add "Cập nhật ứng dụng" row right after Clear Cache
        if "StreamSyncUpdater" not in s4_content:
            updater_row = """
    # [NUVIO PATCH] In-App Updater row connected to StreamSync repo
    sget-object v0, Landroidx/compose/material/icons/Icons;->INSTANCE:Landroidx/compose/material/icons/Icons;
    invoke-virtual {v0}, Landroidx/compose/material/icons/Icons;->getDefault()Landroidx/compose/material/icons/Icons$Filled;
    move-result-object v0
    invoke-static {v0}, Landroidx/compose/material/icons/filled/SystemUpdateKt;->getSystemUpdate(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;
    move-result-object v1
    const-string v2, "C\\u1eadp nh\\u1eadt \\u1ee9ng d\\u1ee5ng"
    const-string v3, "B\\u1ea3n v1.8.9 \\u2022 Ki\\u1ec3m tra phi\\u00ean b\\u1ea3n m\\u1edbi t\\u1eeb repo StreamSync"
    new-instance v4, Lcom/streamsync/app/updater/UpdaterClickLambda;
    invoke-direct {v4}, Lcom/streamsync/app/updater/UpdaterClickLambda;-><init>()V
    const/16 v6, 0x1b0
    move-object v5, p1
    invoke-static/range {v1 .. v6}, Lcom/streamsync/app/ui/settings/SettingsScreenKt;->access$SettingsNavRow(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V
"""
            pattern_clear_cache = re.compile(r"(const-string\s+v2,\s*\"Xo\\u00e1 b\\u1ed9 \\u0111\\u1ec7m\"[\s\S]*?invoke-static/range\s*\{[^\}]+\},\s*Lcom/streamsync/app/ui/settings/SettingsScreenKt;->access\$SettingsNavRow[^\n]+)", re.MULTILINE)
            s4_content = pattern_clear_cache.sub(lambda m: m.group(1) + "\n" + updater_row, s4_content)
            log("Injected 'Cập nhật ứng dụng' row into settings.")

        with open(s4_path, 'w', encoding='utf-8') as f:
            f.write(s4_content)

    # 4. Generate Updater smali classes
    updater_dir = os.path.join(unpack_dir, "smali_classes21", "com", "streamsync", "app", "updater")
    if not os.path.exists(os.path.dirname(updater_dir)):
        updater_dir = os.path.join(target_dir, "updater")
    os.makedirs(updater_dir, exist_ok=True)

    # StreamSyncUpdater.smali
    with open(os.path.join(updater_dir, "StreamSyncUpdater.smali"), "w", encoding="utf-8") as f:
        f.write(""".class public Lcom/streamsync/app/updater/StreamSyncUpdater;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.smali"

.method public constructor <init>()V
    .registers 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public static checkUpdate(Landroid/content/Context;)V
    .registers 3
    new-instance v0, Ljava/lang/Thread;
    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1;-><init>(Landroid/content/Context;)V
    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V
    return-void
.end method
""")

    # StreamSyncUpdater$1.smali (Worker thread)
    with open(os.path.join(updater_dir, "StreamSyncUpdater$1.smali"), "w", encoding="utf-8") as f:
        f.write(""".class final Lcom/streamsync/app/updater/StreamSyncUpdater$1;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.smali"
.implements Ljava/lang/Runnable;

.field final synthetic val$context:Landroid/content/Context;

.method constructor <init>(Landroid/content/Context;)V
    .registers 2
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public run()V
    .registers 10
    :try_start_0
    new-instance v0, Ljava/net/URL;
    const-string v1, "https://api.github.com/repos/anpham14121993-cloud/StreamSync/releases/latest"
    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;
    move-result-object v0
    check-cast v0, Ljava/net/HttpURLConnection;

    const-string v1, "GET"
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V
    const-string v1, "User-Agent"
    const-string v2, "StreamSync-Updater"
    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V
    const/16 v1, 0x1f40
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I
    move-result v1
    const/16 v2, 0xc8
    if-ne v1, v2, :cond_err

    new-instance v1, Ljava/io/BufferedReader;
    new-instance v2, Ljava/io/InputStreamReader;
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;
    move-result-object v0
    invoke-direct {v2, v0}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V
    invoke-direct {v1, v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    new-instance v0, Ljava/lang/StringBuilder;
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    :goto_0
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;
    move-result-object v2
    if-eqz v2, :cond_done
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    goto :goto_0

    :cond_done
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v0

    const-string v1, "\\"tag_name\\":\\""
    invoke-virtual {v0, v1}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I
    move-result v1
    if-ltz v1, :cond_latest
    add-int/lit8 v1, v1, 0xc
    const-string v2, "\\""
    invoke-virtual {v0, v2, v1}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I
    move-result v2
    if-le v2, v1, :cond_latest
    invoke-virtual {v0, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;
    move-result-object v1

    # Compare remote tag with current versionName
    iget-object v2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;
    invoke-virtual {v2}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;
    move-result-object v2
    iget-object v4, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;
    invoke-virtual {v4}, Landroid/content/Context;->getPackageName()Ljava/lang/String;
    move-result-object v4
    const/4 v5, 0x0
    invoke-virtual {v2, v4, v5}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    move-result-object v2
    iget-object v2, v2, Landroid/content/pm/PackageInfo;->versionName:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z
    move-result v4
    if-eqz v4, :cond_check_older
    goto :cond_latest

    :cond_check_older
    const-string v4, "1.8.8"
    invoke-virtual {v1, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z
    move-result v4
    if-eqz v4, :cond_fetch_url
    goto :cond_latest

    :cond_fetch_url
    const-string v2, "\\"browser_download_url\\":\\""
    invoke-virtual {v0, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I
    move-result v2
    const-string v3, "https://github.com/anpham14121993-cloud/StreamSync/releases/latest"
    if-ltz v2, :cond_show
    add-int/lit8 v2, v2, 0x18
    const-string v4, "\\""
    invoke-virtual {v0, v4, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I
    move-result v4
    if-le v4, v2, :cond_show
    invoke-virtual {v0, v2, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;
    move-result-object v3

    :cond_show
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;
    instance-of v2, v0, Landroid/app/Activity;
    if-eqz v2, :cond_end
    check-cast v0, Landroid/app/Activity;
    new-instance v2, Lcom/streamsync/app/updater/StreamSyncUpdater$2;
    invoke-direct {v2, p0, v1, v3}, Lcom/streamsync/app/updater/StreamSyncUpdater$2;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;Ljava/lang/String;Ljava/lang/String;)V
    invoke-virtual {v0, v2}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V
    goto :cond_end

    :cond_latest
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;
    instance-of v1, v0, Landroid/app/Activity;
    if-eqz v1, :cond_end
    check-cast v0, Landroid/app/Activity;
    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$3;
    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$3;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V
    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V
    goto :cond_end

    :cond_err
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    move-exception v0
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;
    instance-of v1, v0, Landroid/app/Activity;
    if-eqz v1, :cond_end
    check-cast v0, Landroid/app/Activity;
    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$4;
    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$4;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V
    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    :cond_end
    return-void
.end method
""")

    # StreamSyncUpdater$2.smali (Update Available Dialog)
    with open(os.path.join(updater_dir, "StreamSyncUpdater$2.smali"), "w", encoding="utf-8") as f:
        f.write(""".class final Lcom/streamsync/app/updater/StreamSyncUpdater$2;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.smali"
.implements Ljava/lang/Runnable;

.field final synthetic this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
.field final synthetic val$tag:Ljava/lang/String;
.field final synthetic val$url:Ljava/lang/String;

.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;Ljava/lang/String;Ljava/lang/String;)V
    .registers 4
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$tag:Ljava/lang/String;
    iput-object p3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$url:Ljava/lang/String;
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public run()V
    .registers 5
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    new-instance v1, Landroid/app/AlertDialog$Builder;
    invoke-direct {v1, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string v2, "C\\u1eadp nh\\u1eadt \\u1ee9ng d\\u1ee5ng"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    new-instance v2, Ljava/lang/StringBuilder;
    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V
    const-string v3, "\\u0110\\u00e3 c\\u00f3 phi\\u00ean b\\u1ea3n m\\u1edbi: "
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    iget-object v3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$tag:Ljava/lang/String;
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    const-string v3, "\\nB\\u1ea1n c\\u00f3 mu\\u1ed1n t\\u1ea3i v\\u1ec1 v\\u00e0 c\\u00e0i \\u0111\\u1eb7t kh\\u00f4ng?"
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v2
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    const-string v2, "T\\u1ea3i & C\\u00e0i \\u0111\\u1eb7t"
    new-instance v3, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;
    invoke-direct {v3, p0, v0}, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$2;Landroid/content/Context;)V
    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    const-string v2, "\\u0110\\u1ec3 sau"
    const/4 v3, 0x0
    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    return-void
.end method
""")

    # StreamSyncUpdater$2$1.smali (Download intent click)
    with open(os.path.join(updater_dir, "StreamSyncUpdater$2$1.smali"), "w", encoding="utf-8") as f:
        f.write(""".class final Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.smali"
.implements Landroid/content/DialogInterface$OnClickListener;

.field final synthetic this$1:Lcom/streamsync/app/updater/StreamSyncUpdater$2;
.field final synthetic val$context:Landroid/content/Context;

.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$2;Landroid/content/Context;)V
    .registers 3
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->this$1:Lcom/streamsync/app/updater/StreamSyncUpdater$2;
    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->val$context:Landroid/content/Context;
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 6
    :try_start_0
    new-instance v0, Landroid/content/Intent;
    const-string v1, "android.intent.action.VIEW"
    iget-object v2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->this$1:Lcom/streamsync/app/updater/StreamSyncUpdater$2;
    iget-object v2, v2, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$url:Ljava/lang/String;
    invoke-static {v2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;
    move-result-object v2
    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V
    const/high16 v1, 0x10000000
    invoke-virtual {v0, v1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;
    iget-object v1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->val$context:Landroid/content/Context;
    invoke-virtual {v1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-void
.end method
""")

    # StreamSyncUpdater$3.smali (Already Latest)
    with open(os.path.join(updater_dir, "StreamSyncUpdater$3.smali"), "w", encoding="utf-8") as f:
        f.write(""".class final Lcom/streamsync/app/updater/StreamSyncUpdater$3;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.smali"
.implements Ljava/lang/Runnable;

.field final synthetic this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V
    .registers 2
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public run()V
    .registers 4
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    new-instance v1, Landroid/app/AlertDialog$Builder;
    invoke-direct {v1, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V
    const-string v2, "C\\u1eadp nh\\u1eadt \\u1ee9ng d\\u1ee5ng"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    const-string v2, "\\u1ee8ng d\\u1ee5ng \\u0111ang \\u1edf phi\\u00ean b\\u1ea3n m\\u1edbi nh\\u1ea5t (v1.8.9)!"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    const-string v2, "\\u0110\\u00f3ng"
    const/4 v0, 0x0
    invoke-virtual {v1, v2, v0}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    return-void
.end method
""")

    # StreamSyncUpdater$4.smali (Error)
    with open(os.path.join(updater_dir, "StreamSyncUpdater$4.smali"), "w", encoding="utf-8") as f:
        f.write(""".class final Lcom/streamsync/app/updater/StreamSyncUpdater$4;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.smali"
.implements Ljava/lang/Runnable;

.field final synthetic this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V
    .registers 2
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$4;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public run()V
    .registers 4
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$4;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;
    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    new-instance v1, Landroid/app/AlertDialog$Builder;
    invoke-direct {v1, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V
    const-string v2, "C\\u1eadp nh\\u1eadt \\u1ee9ng d\\u1ee5ng"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    const-string v2, "Kh\\u00f4ng th\\u1ec3 ki\\u1ec3m tra c\\u1eadp nh\\u1eadt l\\u00fac n\\u00e0y. Vui l\\u00f2ng th\\u1eed l\\u1ea1i sau!"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    const-string v2, "\\u0110\\u00f3ng"
    const/4 v0, 0x0
    invoke-virtual {v1, v2, v0}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    return-void
.end method
""")

    # UpdaterClickLambda.smali
    with open(os.path.join(updater_dir, "UpdaterClickLambda.smali"), "w", encoding="utf-8") as f:
        f.write(""".class public Lcom/streamsync/app/updater/UpdaterClickLambda;
.super Ljava/lang/Object;
.source "UpdaterClickLambda.smali"
.implements Lkotlin/jvm/functions/Function0;

.method public constructor <init>()V
    .registers 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public invoke()Lkotlin/Unit;
    .registers 2
    sget-object v0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_0
    invoke-static {v0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->checkUpdate(Landroid/content/Context;)V
    :cond_0
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .registers 2
    invoke-virtual {p0}, Lcom/streamsync/app/updater/UpdaterClickLambda;->invoke()Lkotlin/Unit;
    move-result-object v0
    return-object v0
.end method
""")

    log("Successfully created Updater classes.")
    return True

def patch_manifest_and_resources(unpack_dir):
    """Ensures Android TV Leanback features, banner, permissions, and versionCode bump in manifest and res"""
    manifest_path = os.path.join(unpack_dir, "AndroidManifest.xml")
    if not os.path.exists(manifest_path):
        log(f"ERROR: {manifest_path} not found!")
        return False

    with open(manifest_path, 'r', encoding='utf-8') as f:
        manifest = f.read()

    changed = False

    # 1. Leanback launcher category
    if "LEANBACK_LAUNCHER" not in manifest:
        manifest = manifest.replace(
            '<category android:name="android.intent.category.LAUNCHER"/>',
            '<category android:name="android.intent.category.LAUNCHER"/>\n                <category android:name="android.intent.category.LEANBACK_LAUNCHER"/>'
        )
        log("Added LEANBACK_LAUNCHER category to MainActivity.")
        changed = True

    # 2. Leanback & touchscreen features
    if "android.software.leanback" not in manifest:
        features = (
            '    <uses-feature android:name="android.software.leanback" android:required="false"/>\n'
            '    <uses-feature android:name="android.hardware.touchscreen" android:required="false"/>\n'
        )
        if "<uses-permission" in manifest:
            manifest = manifest.replace("<uses-permission", features + "    <uses-permission", 1)
        else:
            manifest = manifest.replace("<application", features + "    <application", 1)
        log("Added leanback and touchscreen optional features.")
        changed = True

    # 3. Add REQUEST_INSTALL_PACKAGES permission for in-app updates
    if "REQUEST_INSTALL_PACKAGES" not in manifest:
        perm = '    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>\n'
        manifest = manifest.replace("<application", perm + "    <application", 1)
        log("Added REQUEST_INSTALL_PACKAGES permission for updates.")
        changed = True

    # 4. Bump versionCode to 189 and versionName to 1.8.9 for overwrite/update support
    if 'android:versionCode=' in manifest:
        manifest = re.sub(r'android:versionCode="[^"]*"', 'android:versionCode="189"', manifest)
        manifest = re.sub(r'android:versionName="[^"]*"', 'android:versionName="1.8.9"', manifest)
        log("Updated AndroidManifest versionCode to 189 and versionName to 1.8.9.")
        changed = True

    # 5. Add banner attribute to application if not present
    if 'android:banner=' not in manifest:
        manifest = manifest.replace("<application ", '<application android:banner="@drawable/tv_banner" ', 1)
        log("Added android:banner=@drawable/tv_banner to application tag.")
        changed = True

    # 6. Fix "Gói có vẻ không hợp lệ":
    if 'android:extractNativeLibs="false"' in manifest:
        manifest = manifest.replace('android:extractNativeLibs="false"', 'android:extractNativeLibs="true"')
        log("Changed extractNativeLibs from false to true to fix package parsing failure.")
        changed = True

    if changed:
        with open(manifest_path, 'w', encoding='utf-8') as f:
            f.write(manifest)

    # 7. Update apktool.yml so version is updated and native libraries are stored uncompressed
    apktool_yml_path = os.path.join(unpack_dir, "apktool.yml")
    if os.path.exists(apktool_yml_path):
        with open(apktool_yml_path, 'r', encoding='utf-8') as yf:
            yml_content = yf.read()
        
        yml_content = re.sub(r"versionCode:\s*'[0-9]+'", "versionCode: '189'", yml_content)
        yml_content = re.sub(r"versionName:\s*'[^']*'", "versionName: '1.8.9'", yml_content)

        if "doNotCompress:" in yml_content and "- so" not in yml_content:
            yml_content = yml_content.replace(
                "doNotCompress:\n",
                "doNotCompress:\n- so\n- lib/arm64-v8a/libffmpegJNI.so\n- lib/arm64-v8a/libtranslate_jni.so\n- lib/arm64-v8a/liblanguage_id_l2c_jni.so\n"
            )
        with open(apktool_yml_path, 'w', encoding='utf-8') as yf:
            yf.write(yml_content)
        log("Updated apktool.yml with version 189 (1.8.9) and uncompressed shared libraries.")

    # 8. Generate tv_banner.png and copy tv_banner.xml into res/drawable
    drawable_dir = os.path.join(unpack_dir, "res", "drawable")
    drawable_xhdpi = os.path.join(unpack_dir, "res", "drawable-xhdpi")
    os.makedirs(drawable_dir, exist_ok=True)
    os.makedirs(drawable_xhdpi, exist_ok=True)

    banner_png_bytes = create_tv_banner_png(320, 180)
    banner_png_path = os.path.join(drawable_xhdpi, "tv_banner.png")
    with open(banner_png_path, "wb") as f:
        f.write(banner_png_bytes)
    with open(os.path.join(drawable_dir, "tv_banner.png"), "wb") as f:
        f.write(banner_png_bytes)

    return True

def inject_native_libraries(unpack_dir, repo_root):
    """Copies 64-bit arm64-v8a and 32-bit armeabi-v7a native libraries"""
    log("Injecting native libraries...")
    arm64_src = os.path.join(repo_root, "app", "src", "main", "jniLibs", "arm64-v8a")
    arm64_dst = os.path.join(unpack_dir, "lib", "arm64-v8a")
    
    if os.path.isdir(arm64_src):
        os.makedirs(arm64_dst, exist_ok=True)
        for lib in os.listdir(arm64_src):
            src_lib = os.path.join(arm64_src, lib)
            dst_lib = os.path.join(arm64_dst, lib)
            shutil.copy2(src_lib, dst_lib)
            log(f"Copied arm64 lib: {lib} ({os.path.getsize(dst_lib)} bytes)")

    v7a_src = os.path.join(repo_root, "app", "src", "main", "jniLibs", "armeabi-v7a")
    v7a_dst = os.path.join(unpack_dir, "lib", "armeabi-v7a")
    if os.path.isdir(v7a_src):
        os.makedirs(v7a_dst, exist_ok=True)
        for lib in os.listdir(v7a_src):
            src_lib = os.path.join(v7a_src, lib)
            dst_lib = os.path.join(v7a_dst, lib)
            if not os.path.exists(dst_lib):
                shutil.copy2(src_lib, dst_lib)
                log(f"Copied armeabi-v7a lib: {lib}")

def main():
    if len(sys.argv) < 2:
        print("Usage: python3 patch_nuvio_ui.py <path_to_apk_unpack_dir> [repo_root]")
        sys.exit(1)

    unpack_dir = sys.argv[1]
    repo_root = sys.argv[2] if len(sys.argv) > 2 else os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

    if not os.path.isdir(unpack_dir):
        print(f"Directory not found: {unpack_dir}")
        sys.exit(1)

    log(f"Starting Nuvio UI patches on: {unpack_dir}")
    patch_continue_watching(unpack_dir)
    patch_image_prefetch(unpack_dir)
    patch_single_swipe_and_exit_dialog(unpack_dir)
    patch_default_decoder(unpack_dir)
    patch_settings_integrations_and_account_and_updater(unpack_dir)
    patch_manifest_and_resources(unpack_dir)
    inject_native_libraries(unpack_dir, repo_root)
    log("All Nuvio UI & feature patches successfully applied!")

if __name__ == "__main__":
    main()

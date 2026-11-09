#!/usr/bin/env python3
"""
patch_nuvio_ui.py:
Applies Nuvio smooth UI & performance optimizations directly to decompiled StreamSync APK:
1. ContinueWatchingCard: 16:9 landscape aspect ratio (224dp x 126dp) and smooth pre-rendering (beyondViewportPageCount = 8).
2. Coil Image Prefetching: Throttles prefetch queue to 15 items, eliminating UI freezes.
3. Android TV Leanback: Adds LEANBACK_LAUNCHER, leanback feature, touchscreen optional, and 320x180 banner.
4. Single-swipe Edge Back & Screen-specific Exit Dialog:
   - Removes systemBars hide() so swipe from right edge works on 1st swipe.
   - Captures sNavController in both StreamSyncNavHost and rememberNavController.
   - Shows exit confirmation dialog ("Thoát ứng dụng", "Đồng ý", "Hủy") ONLY on the Home (catalog) screen.
   - On all other screens (Detail, Settings, Player, Addons...), performs normal back navigation (popBackStack).
5. Player Default Decoder: Sets default decoder to "Ưu tiên bộ giải mã thiết bị" (PREFER_DEVICE).
6. Integrations & DNS/ByeDPI: Merges DNS-over-HTTPS and ByeDPI into the "Tích hợp" settings section.
7. Account & Supabase Sync: Hooks Settings row to Nuvio Mobile-style Supabase authentication and account management.
8. In-App Updater: Adds OTA in-app updater checking releases from repo anpham14121993-cloud/StreamSync with direct download & FileProvider install dialog.
9. Upgrade & Overwrite Support: Increments versionCode to 191 and versionName to 1.9.1 with REQUEST_INSTALL_PACKAGES permission and fixed keystore.
10. Native Libraries: Injects 64-bit arm64-v8a and 32-bit armeabi-v7a native libraries (FFmpeg JNI, AC3, Subtitle translation).
11. Meta Screen Upgrades:
   - Fixes missing Cast / Actors in AddonRepository for single-part composite IDs (Cinemeta).
   - Fixes movie showing series episode selector in AddonRepositoryKt.
   - Hides stream list on detail screen until user clicks 'Xem phim'.
   - Adds primary 'Xem phim' ActionButton in DetailActionRow.
   - Allows stream titles to wrap across multiple lines (maxLines = 5) like Nuvio.
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

def find_file_exact(root_dir, exact_name):
    matches = []
    for dirpath, _, filenames in os.walk(root_dir):
        if exact_name in filenames:
            matches.append(os.path.join(dirpath, exact_name))
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

def patch_single_swipe_and_exit_dialog(unpack_dir, script_dir):
    """
    1. Removes controller.hide(systemBars) to fix the 'swipe twice from right edge' bug.
    2. Stores sNavController in MainActivity so back presses from sub-screens pop the backstack,
       and ONLY the home screen (where previousBackStackEntry == null) shows the Nuvio exit dialog.
    3. Copies precompiled, verified HomeBackCallback.smali and ExitConfirmListener.smali.
    """
    log("Searching for MainActivity.smali, MainActivityKt.smali, and ComposableSingletons...")
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

    # 2. Show systemBars explicitly (instead of hide) and set behavior to 2
    # This prevents Android from hiding the status bar or capturing the 1st edge swipe gesture
    if "Landroidx/core/view/WindowInsetsControllerCompat;->hide(I)V" in content:
        content = re.sub(
            r"invoke-virtual\s*\{([^\}]+)\},\s*Landroidx/core/view/WindowInsetsControllerCompat;->hide\(I\)V",
            r"invoke-virtual {\1}, Landroidx/core/view/WindowInsetsControllerCompat;->show(I)V",
            content
        )
        log("Replaced WindowInsetsControllerCompat.hide() with show() to keep status bar visible.")
        changed = True

    # 3. Explicitly set setSystemBarsBehavior to 2 (BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE)
    if "setSystemBarsBehavior" in content:
        content = re.sub(
            r"invoke-virtual\s*\{([^\}]+),\s*([^\}]+)\},\s*Landroidx/core/view/WindowInsetsControllerCompat;->setSystemBarsBehavior\(I\)V",
            r"const/4 \2, 0x2\n    invoke-virtual {\1, \2}, Landroidx/core/view/WindowInsetsControllerCompat;->setSystemBarsBehavior(I)V",
            content
        )
        log("Set WindowInsetsControllerCompat.setSystemBarsBehavior to 2.")
        changed = True

    # 3b. Add onResume() to MainActivity so system bars are always visible when returning to activity
    if "protected onResume()V" not in content:
        on_resume_smali = """
.method protected onResume()V
    .registers 4

    invoke-super {p0}, Landroidx/activity/ComponentActivity;->onResume()V

    # [NUVIO PATCH] Ensure systemBars are visible on resume for 1-swipe back gesture
    invoke-virtual {p0}, Lcom/streamsync/app/MainActivity;->getWindow()Landroid/view/Window;
    move-result-object v0
    if-eqz v0, :cond_resume_end

    new-instance v1, Landroidx/core/view/WindowInsetsControllerCompat;
    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;
    move-result-object v2
    invoke-direct {v1, v0, v2}, Landroidx/core/view/WindowInsetsControllerCompat;-><init>(Landroid/view/Window;Landroid/view/View;)V

    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->systemBars()I
    move-result v0
    invoke-virtual {v1, v0}, Landroidx/core/view/WindowInsetsControllerCompat;->show(I)V

    const/4 v0, 0x2
    invoke-virtual {v1, v0}, Landroidx/core/view/WindowInsetsControllerCompat;->setSystemBarsBehavior(I)V

    :cond_resume_end
    return-void
.end method
"""
        content += "\n" + on_resume_smali
        log("Added onResume() to MainActivity to maintain visible systemBars.")
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
    if-eqz v0, :cond_fallback

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

    :cond_fallback
    invoke-super {p0}, Landroidx/activity/ComponentActivity;->onBackPressed()V
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
            target_method = re.compile(r"(\.method public static final StreamSyncNavHost\(Landroidx/navigation/NavHostController;Landroidx/compose/runtime/Composer;I\)V\s*\.registers\s+\d+)", re.MULTILINE)
            kt_content_patched = target_method.sub(lambda m: m.group(1) + "\n    sput-object p0, Lcom/streamsync/app/MainActivity;->sNavController:Landroidx/navigation/NavHostController;", kt_content, count=1)
            if kt_content_patched != kt_content:
                with open(main_kt_targets[0], 'w', encoding='utf-8') as f:
                    f.write(kt_content_patched)
                log("Captured NavController in MainActivityKt.StreamSyncNavHost.")

    # 7. Inject sNavController capture into ComposableSingletons$MainActivityKt$lambda-1$1
    cs_targets = find_files(unpack_dir, r"^ComposableSingletons\$MainActivityKt\$lambda-1\$1\.smali$")
    if cs_targets:
        with open(cs_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            cs_content = f.read()
        if "sput-object" not in cs_content or "sNavController" not in cs_content:
            remember_pattern = re.compile(r"(invoke-static\s*\{[^\}]+\},\s*Landroidx/navigation/compose/NavHostControllerKt;->rememberNavController[^\n]+\s*move-result-object\s+(v\d+))", re.MULTILINE)
            cs_content_patched = remember_pattern.sub(lambda m: m.group(1) + f"\n    sput-object {m.group(2)}, Lcom/streamsync/app/MainActivity;->sNavController:Landroidx/navigation/NavHostController;", cs_content, count=1)
            if cs_content_patched != cs_content:
                with open(cs_targets[0], 'w', encoding='utf-8') as f:
                    f.write(cs_content_patched)
                log("Captured NavController in ComposableSingletons rememberNavController.")

    # 8. Restore systemBars in PlayerScreen.onDispose
    player_dispose_targets = find_files(unpack_dir, r"^PlayerScreenKt\$PlayerScreen\$2\$1\$invoke\$\$inlined\$onDispose\$1\.smali$")
    if player_dispose_targets:
        with open(player_dispose_targets[0], 'r', encoding='utf-8') as f:
            dispose_smali = f.read()
        restore_bars_dispose = """
.method public dispose()V
    .locals 4

    # [NUVIO PATCH] Restore systemBars when leaving player screen
    sget-object v0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/streamsync/app/MainActivity;->getWindow()Landroid/view/Window;
    move-result-object v1
    if-eqz v1, :cond_0

    new-instance v2, Landroidx/core/view/WindowInsetsControllerCompat;
    invoke-virtual {v1}, Landroid/view/Window;->getDecorView()Landroid/view/View;
    move-result-object v3
    invoke-direct {v2, v1, v3}, Landroidx/core/view/WindowInsetsControllerCompat;-><init>(Landroid/view/Window;Landroid/view/View;)V

    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->systemBars()I
    move-result v1
    invoke-virtual {v2, v1}, Landroidx/core/view/WindowInsetsControllerCompat;->show(I)V

    const/4 v1, 0x2
    invoke-virtual {v2, v1}, Landroidx/core/view/WindowInsetsControllerCompat;->setSystemBarsBehavior(I)V

    :cond_0
    return-void
.end method
"""
        if "public dispose()V" in dispose_smali:
            dispose_smali = re.sub(r"\.method public dispose\(\)V[\s\S]*?\.end method", restore_bars_dispose.strip(), dispose_smali)
            with open(player_dispose_targets[0], 'w', encoding='utf-8') as f:
                f.write(dispose_smali)
            log("Injected systemBars restoration into PlayerScreen.onDispose().")

    # 8. Copy verified HomeBackCallback.smali & ExitConfirmListener.smali
    smali_patches_dir = os.path.join(script_dir, "smali_patches")
    shutil.copy2(os.path.join(smali_patches_dir, "HomeBackCallback.smali"), os.path.join(target_dir, "HomeBackCallback.smali"))
    shutil.copy2(os.path.join(smali_patches_dir, "ExitConfirmListener.smali"), os.path.join(target_dir, "ExitConfirmListener.smali"))
    log("Copied verified HomeBackCallback.smali and ExitConfirmListener.smali.")

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

def patch_settings_integrations_and_account_and_updater(unpack_dir, script_dir):
    """
    1. Merges DNS & ByeDPI into the 'Tích hợp' section using CombinedIntegrationsLambda.
    2. Updates Account to Nuvio-style 'Tài khoản & Đồng bộ Supabase' hooked to SupabaseAccountManager dialog.
    3. Adds in-app update checker connected to anpham14121993-cloud/StreamSync repo using crash-free StreamSyncUpdater.
    """
    log("Patching settings: merging DNS & ByeDPI into Tích hợp, adding Account & Updater...")

    smali_patches_dir = os.path.join(script_dir, "smali_patches")

    # 1. Copy CombinedIntegrationsLambda.smali
    settings_dirs = find_files(unpack_dir, r"^SettingsScreenKt.*\.smali$")
    if not settings_dirs:
        log("WARNING: SettingsScreenKt not found!")
        return False

    settings_target_dir = os.path.dirname(settings_dirs[0])
    shutil.copy2(
        os.path.join(smali_patches_dir, "settings", "CombinedIntegrationsLambda.smali"),
        os.path.join(settings_target_dir, "CombinedIntegrationsLambda.smali")
    )
    log("Copied CombinedIntegrationsLambda.smali.")

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

        # Replace bottom disclaimer note with centered AioStream v1.9.4 (strictly 1 line, centered)
        pattern_disclaimer = re.compile(
            r'(const-string\s+v52,\s*"StreamSync[^\n]+")([\s\S]*?)(const/16\s+v64,\s*0x0)([\s\S]*?)(const\s+v76,\s*0x[0-9a-fA-F]+)([\s\S]*?Text--4IGK_g)'
        )
        if pattern_disclaimer.search(s2_content):
            def repl_disclaimer(m):
                return (
                    "sget-object v0, Landroidx/compose/ui/Modifier;->Companion:Landroidx/compose/ui/Modifier$Companion;\n"
                    "    check-cast v0, Landroidx/compose/ui/Modifier;\n"
                    "    const/4 v1, 0x1\n"
                    "    const/4 v6, 0x0\n"
                    "    const/high16 v8, 0x3f800000    # 1.0f\n"
                    "    invoke-static {v0, v8, v1, v6}, Landroidx/compose/foundation/layout/SizeKt;->fillMaxWidth$default(Landroidx/compose/ui/Modifier;FILjava/lang/Object;)Landroidx/compose/ui/Modifier;\n"
                    "    move-result-object v53\n"
                    "    const-string v52, \"AioStream v1.9.7\"" + m.group(2) +
                    "sget-object v0, Landroidx/compose/ui/text/style/TextAlign;->Companion:Landroidx/compose/ui/text/style/TextAlign$Companion;\n"
                    "    invoke-virtual {v0}, Landroidx/compose/ui/text/style/TextAlign$Companion;->getCenter-e0LSkKk()I\n"
                    "    move-result v0\n"
                    "    invoke-static {v0}, Landroidx/compose/ui/text/style/TextAlign;->box-impl(I)Landroidx/compose/ui/text/style/TextAlign;\n"
                    "    move-result-object v64" + m.group(4) +
                    "const v76, 0xfef8" + m.group(6)
                )
            s2_content = pattern_disclaimer.sub(repl_disclaimer, s2_content, count=1)
            log("Replaced settings disclaimer note with centered single-line AioStream v1.9.7.")

        with open(s2_path, 'w', encoding='utf-8') as f:
            f.write(s2_content)

    # 3. Patch SettingsScreenKt$SettingsScreen$2$1$4.smali (Account & In-App Updater)
    s4_targets = find_files(unpack_dir, r"^SettingsScreenKt\$SettingsScreen\$2\$1\$4\.smali$")
    if s4_targets:
        s4_path = s4_targets[0]
        with open(s4_path, 'r', encoding='utf-8', errors='ignore') as f:
            s4_content = f.read()

        # Update text "Máy chủ đồng bộ" -> "Tài khoản" (strictly Tài khoản, no supabase text)
        if "M\\u00e1y ch\\u1ee7 \\u0111\\u1ed3ng b\\u1ed9" in s4_content:
            s4_content = s4_content.replace(
                "M\\u00e1y ch\\u1ee7 \\u0111\\u1ed3ng b\\u1ed9",
                "T\\u00e0i kho\\u1ea3n"
            )
            s4_content = s4_content.replace(
                "G\\u1eedi ti\\u1ebfn \\u0111\\u1ed9 xem l\\u00ean m\\u00e1y ch\\u1ee7 c\\u1ee7a b\\u1ea1n, ho\\u1eb7c d\\u00f9ng addon sync",
                "\\u0110\\u0103ng nh\\u1eadp t\\u00e0i kho\\u1ea3n \\u0111\\u1ec3 t\\u1ef1 \\u0111\\u1ed9ng \\u0111\\u1ed3ng b\\u1ed9"
            )

        # Hook Account row to SupabaseAccountManager via AccountClickLambda
        sync_pattern = re.compile(r"iget-object\s+v\d+,\s*p0,\s*Lcom/streamsync/app/ui/settings/SettingsScreenKt\$SettingsScreen\$2\$1\$4;->\$onSyncServerClick:Lkotlin/jvm/functions/Function0;")
        if sync_pattern.search(s4_content):
            s4_content = sync_pattern.sub(
                "new-instance v4, Lcom/streamsync/app/auth/AccountClickLambda;\n    invoke-direct {v4}, Lcom/streamsync/app/auth/AccountClickLambda;-><init>()V",
                s4_content
            )
            log("Hooked Supabase Account login dialog to Settings NavRow.")

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
    const-string v3, "B\\u1ea3n v1.9.7 \\u2022 Ki\\u1ec3m tra & c\\u1eadp nh\\u1eadt tr\\u1ef1c ti\\u1ebfp (OTA)"
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

    # 4. Copy all precompiled smali classes (updater, auth, addon, detail, settings)
    main_activity_targets = find_files(unpack_dir, r"^MainActivity\.smali$")
    if main_activity_targets:
        app_dir = os.path.dirname(main_activity_targets[0])
    else:
        app_dir = os.path.join(unpack_dir, "smali_classes21", "com", "streamsync", "app")

    packages_to_copy = {
        "updater": os.path.join(app_dir, "updater"),
        "auth": os.path.join(app_dir, "auth"),
        "addon": os.path.join(app_dir, "data", "addon"),
        "detail": os.path.join(app_dir, "ui", "detail"),
        "settings": os.path.join(app_dir, "settings"),
    }

    for sub_name, dst_dir in packages_to_copy.items():
        src_dir = os.path.join(smali_patches_dir, sub_name)
        if os.path.isdir(src_dir):
            os.makedirs(dst_dir, exist_ok=True)
            for f in os.listdir(src_dir):
                if f.endswith(".smali"):
                    shutil.copy2(os.path.join(src_dir, f), os.path.join(dst_dir, f))
            log(f"Copied precompiled smali patches from {sub_name} to {dst_dir}.")

    return True

def patch_meta_screen(unpack_dir, script_dir):
    """
    Patches the Movie Detail / Meta screen:
    1. Fixes movie showing series episode selector in AddonRepositoryKt (isSeriesWithEpisodes checks type == 'series').
    2. Allows stream titles to wrap across multiple lines (maxLines = 5) like Nuvio (StreamRow$1).
    """
    log("Patching Meta detail screen (episode selector fix, multi-line streams)...")

    # 1. Patch AddonRepositoryKt.smali (isSeriesWithEpisodes)
    repo_kt_targets = find_file_exact(unpack_dir, "AddonRepositoryKt.smali")
    if repo_kt_targets:
        with open(repo_kt_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            kt_content = f.read()

        series_check_smali = """.method public static final isSeriesWithEpisodes(Lcom/streamsync/app/data/addon/AddonMeta;)Z
    .locals 2
    .param p0, "$this$isSeriesWithEpisodes"    # Lcom/streamsync/app/data/addon/AddonMeta;

    if-nez p0, :cond_null
    const/4 v0, 0x0
    return v0

    :cond_null
    invoke-virtual {p0}, Lcom/streamsync/app/data/addon/AddonMeta;->getType()Ljava/lang/String;
    move-result-object v0
    const-string v1, "series"
    invoke-static {v1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z
    move-result v0
    if-nez v0, :cond_is_series

    const/4 v0, 0x0
    return v0

    :cond_is_series
    invoke-virtual {p0}, Lcom/streamsync/app/data/addon/AddonMeta;->getVideos()Ljava/util/List;
    move-result-object v0
    check-cast v0, Ljava/util/Collection;
    const/4 v1, 0x1
    if-eqz v0, :cond_1
    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z
    move-result v0
    if-eqz v0, :cond_0
    goto :goto_0

    :cond_0
    const/4 v0, 0x0
    goto :goto_1

    :cond_1
    :goto_0
    move v0, v1

    :goto_1
    xor-int/2addr v0, v1
    return v0
.end method"""
        p_series = re.compile(r'\.method public static final isSeriesWithEpisodes\(Lcom/streamsync/app/data/addon/AddonMeta;\)Z[\s\S]*?\.end method')
        if p_series.search(kt_content):
            kt_content = p_series.sub(series_check_smali, kt_content)
            with open(repo_kt_targets[0], 'w', encoding='utf-8') as f:
                f.write(kt_content)
            log("Patched isSeriesWithEpisodes to check meta.type == 'series'.")

    # 2. Patch StreamRow$1.smali (multi-line stream rows up to 5 lines)
    sr_targets = find_file_exact(unpack_dir, "MovieDetailScreenKt$StreamRow$1.smali")
    if sr_targets:
        with open(sr_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            sr_content = f.read()

        p_maxlines = re.compile(r'(const/16\s+v\d+,\s*)0x[12](\s*\n\s*const/16\s+v\d+,\s*0x0\s*\n\s*const/16\s+v\d+,\s*0x0[\s\S]*?Text--4IGK_g)')
        sr_content_patched, count = p_maxlines.subn(r'\g<1>0x5\2', sr_content)
        if count > 0:
            with open(sr_targets[0], 'w', encoding='utf-8') as f:
                f.write(sr_content_patched)
            log(f"Patched {count} stream Text elements to support up to 5 lines.")

    # 3. Patch AddonRepository.smali (withVideosIfSeries) to enrich catalog series episodes from Cinemeta
    addon_repo_targets = find_file_exact(unpack_dir, "AddonRepository.smali")
    if addon_repo_targets:
        with open(addon_repo_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            ar_content = f.read()

        p_with_videos = re.compile(
            r'(if-nez\s+p4,\s*:cond_\w+[\s\S]*?return-object\s+p1[\s\S]*?invoke-static\s*\{p1,\s*p2\},\s*Lcom/streamsync/app/data/repository/AddonRepositoryKt;->mergeSeriesEpisodesFrom[^\n]+[\s\S]*?move-result-object\s+p4[\s\S]*?return-object\s+p4)',
            re.MULTILINE
        )
        if p_with_videos.search(ar_content):
            def repl_with_videos(m):
                block = m.group(1)
                # Hook null return when catalog addon returned null
                block = re.sub(
                    r'(if-nez\s+p4,\s*:cond_\w+[\s\S]*?)(return-object\s+p1)',
                    r'\g<1>invoke-static {p1, p4}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->enrichSeriesIfEmpty(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;\n    move-result-object p1\n    check-cast p1, Lcom/streamsync/app/data/addon/AddonMeta;\n    return-object p1',
                    block,
                    count=1
                )
                # Hook after mergeSeriesEpisodesFrom in case fullMeta was also missing videos
                block = re.sub(
                    r'(invoke-static\s*\{p1,\s*p2\},\s*Lcom/streamsync/app/data/repository/AddonRepositoryKt;->mergeSeriesEpisodesFrom[^\n]+[\s\S]*?move-result-object\s+p4[\s\S]*?)(return-object\s+p4)',
                    r'\g<1>invoke-static {p4, p1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->enrichSeriesIfEmpty(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;\n    move-result-object p4\n    check-cast p4, Lcom/streamsync/app/data/addon/AddonMeta;\n    return-object p4',
                    block,
                    count=1
                )
                return block
            ar_content = p_with_videos.sub(repl_with_videos, ar_content, count=1)
            with open(addon_repo_targets[0], 'w', encoding='utf-8') as f:
                f.write(ar_content)
            log("Patched AddonRepository.withVideosIfSeries to enrich series episodes via SeriesEpisodeHelper.")

    # 4. Patch MovieDetailScreen to resolve series trailer if missing
    mds_targets = find_files(unpack_dir, r"^MovieDetailScreenKt\$MovieDetailScreen\$3\$4\$1\$2\.smali$")
    if mds_targets:
        with open(mds_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            mds_content = f.read()

        p_trailer_resolve = re.compile(
            r'(invoke-virtual\s*\{[^\}]+\},\s*Lcom/streamsync/app/data/model/MediaItem;->getTrailerId\(\)Ljava/lang/String;[\s\S]*?move-object\s+v9,\s*v1)',
            re.MULTILINE
        )
        if p_trailer_resolve.search(mds_content) and "SeriesEpisodeHelper" not in mds_content:
            resolve_trailer_hook = """
    if-nez v9, :cond_trailer_set
    invoke-virtual {v5}, Lcom/streamsync/app/data/model/MediaItem;->getId()Ljava/lang/String;
    move-result-object v1
    const-string v2, "series"
    invoke-static {v9, v1, v2}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->resolveTrailerId(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    move-result-object v9
    :cond_trailer_set"""
            mds_content = p_trailer_resolve.sub(lambda m: m.group(1) + resolve_trailer_hook, mds_content, count=1)
            with open(mds_targets[0], 'w', encoding='utf-8') as f:
                f.write(mds_content)
            log("Patched MovieDetailScreen to resolve series trailer via SeriesEpisodeHelper.")

    # 5. Patch DetailSectionsKt$DetailActionRow$1$1$1.smali to launch TrailerPlayerActivity in-app
    action_row_targets = find_files(unpack_dir, r"^DetailSectionsKt\$DetailActionRow\$1\$1\$1\.smali$")
    if action_row_targets:
        with open(action_row_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            ar1_content = f.read()

        p_intent = re.compile(
            r'(\.method public final invoke\(\)V[\s\S]*?\.locals 4[\s\S]*?)(new-instance v0, Landroid/content/Intent;[\s\S]*?invoke-virtual\s*\{v2,\s*v0\},\s*Landroid/content/Context;->startActivity\(Landroid/content/Intent;\)V)',
            re.MULTILINE
        )
        if p_intent.search(ar1_content):
            inapp_trailer_smali = """    iget-object v2, p0, Lcom/streamsync/app/ui/detail/DetailSectionsKt$DetailActionRow$1$1$1;->$context:Landroid/content/Context;

    new-instance v0, Landroid/content/Intent;
    const-class v1, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;
    invoke-direct {v0, v2, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v3, "trailer_key"
    iget-object v1, p0, Lcom/streamsync/app/ui/detail/DetailSectionsKt$DetailActionRow$1$1$1;->$trailerKey:Ljava/lang/String;
    invoke-virtual {v0, v3, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const/high16 v1, 0x10000000
    invoke-virtual {v0, v1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    :try_start_0
    invoke-virtual {v2, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V"""
            ar1_content = p_intent.sub(lambda m: m.group(1) + inapp_trailer_smali, ar1_content, count=1)
            with open(action_row_targets[0], 'w', encoding='utf-8') as f:
                f.write(ar1_content)
            log("Patched DetailActionRow to launch TrailerPlayerActivity in-app.")

    return True

def patch_addons_screen(unpack_dir):
    """
    1. Patches AddonRepository.toManifest to check AddonToggleHelper.isAddonEnabled(context, id).
       If false, catalogs and resources are cleared.
    2. Patches AddonsScreenKt items default 4 to insert Config and Toggle IconButtons.
    """
    log("Patching Addons screen (per-addon toggle & config)...")

    # 1. Patch AddonRepository.toManifest
    addon_repo_targets = find_file_exact(unpack_dir, "AddonRepository.smali")
    if addon_repo_targets:
        with open(addon_repo_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            repo_content = f.read()

        p_manifest_init = re.compile(
            r'(\.method private final toManifest\(Lcom/streamsync/app/data/addon/InstalledAddonEntity;\)Lcom/streamsync/app/data/addon/AddonManifest;[\s\S]*?)(invoke-direct/range\s*\{v1 \.\. v12\},\s*Lcom/streamsync/app/data/addon/AddonManifest;-><init>)',
            re.MULTILINE
        )
        if p_manifest_init.search(repo_content) and "AddonToggleHelper" not in repo_content:
            toggle_hook = """    # [NUVIO PATCH] Disable catalogs and resources if addon is disabled
    sget-object v0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_addon_enabled
    invoke-virtual/range {p1 .. p1}, Lcom/streamsync/app/data/addon/InstalledAddonEntity;->getId()Ljava/lang/String;
    move-result-object v13
    invoke-static {v0, v13}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->isAddonEnabled(Landroid/content/Context;Ljava/lang/String;)Z
    move-result v0
    if-nez v0, :cond_addon_enabled
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;
    move-result-object v7
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;
    move-result-object v8
    :cond_addon_enabled
    """
            repo_content = p_manifest_init.sub(lambda m: m.group(1) + toggle_hook + m.group(2), repo_content, count=1)
            with open(addon_repo_targets[0], 'w', encoding='utf-8') as f:
                f.write(repo_content)
            log("Patched AddonRepository.toManifest to respect AddonToggleHelper enabled state.")

    # 2. Patch AddonsScreen items to insert Config and Toggle IconButtons
    items_targets = find_files(unpack_dir, r"^AddonsScreenKt\$AddonsScreen\$1\$1\$2\$1\$invoke\$\$inlined\$items\$default\$4\.smali$")
    if items_targets:
        with open(items_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            items_content = f.read()

        p_delete_btn = re.compile(
            r'(sget-object v1, Lcom/streamsync/app/ui/addons/ComposableSingletons\$AddonsScreenKt;->INSTANCE:[\s\S]*?getLambda-4\$app_mobileDebug\(\)Lkotlin/jvm/functions/Function2;)',
            re.MULTILINE
        )
        if p_delete_btn.search(items_content) and "AddonToggleClickLambda" not in items_content:
            addon_buttons_smali = """
    # [NUVIO PATCH] Config IconButton
    new-instance v2, Lcom/streamsync/app/data/addon/AddonConfigClickLambda;
    invoke-direct {v2, v5}, Lcom/streamsync/app/data/addon/AddonConfigClickLambda;-><init>(Lcom/streamsync/app/data/addon/AddonManifest;)V
    move-object/from16 v76, v2
    new-instance v2, Lcom/streamsync/app/data/addon/AddonConfigIconLambda;
    invoke-direct {v2}, Lcom/streamsync/app/data/addon/AddonConfigIconLambda;-><init>()V
    const v4, 0x51484a5e
    const/4 v11, 0x1
    invoke-static {v3, v4, v11, v2}, Landroidx/compose/runtime/internal/ComposableLambdaKt;->composableLambda(Landroidx/compose/runtime/Composer;IZLjava/lang/Object;)Landroidx/compose/runtime/internal/ComposableLambda;
    move-result-object v81
    check-cast v81, Lkotlin/jvm/functions/Function2;
    const/high16 v83, 0x30000
    const/16 v84, 0x1e
    const/16 v77, 0x0
    const/16 v78, 0x0
    const/16 v79, 0x0
    const/16 v80, 0x0
    move-object/from16 v82, v3
    invoke-static/range {v76 .. v84}, Landroidx/compose/material3/IconButtonKt;->IconButton(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V

    # Restore delete lambda in v76 for the original Delete button
    move-object/from16 v76, v1

    # [NUVIO PATCH] Toggle IconButton
    new-instance v2, Lcom/streamsync/app/data/addon/AddonToggleClickLambda;
    invoke-direct {v2, v5}, Lcom/streamsync/app/data/addon/AddonToggleClickLambda;-><init>(Lcom/streamsync/app/data/addon/AddonManifest;)V
    move-object/from16 v76, v2
    new-instance v2, Lcom/streamsync/app/data/addon/AddonToggleIconLambda;
    invoke-direct {v2, v5}, Lcom/streamsync/app/data/addon/AddonToggleIconLambda;-><init>(Lcom/streamsync/app/data/addon/AddonManifest;)V
    const v4, 0x51484a5f
    const/4 v11, 0x1
    invoke-static {v3, v4, v11, v2}, Landroidx/compose/runtime/internal/ComposableLambdaKt;->composableLambda(Landroidx/compose/runtime/Composer;IZLjava/lang/Object;)Landroidx/compose/runtime/internal/ComposableLambda;
    move-result-object v81
    check-cast v81, Lkotlin/jvm/functions/Function2;
    invoke-static/range {v76 .. v84}, Landroidx/compose/material3/IconButtonKt;->IconButton(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V

    # Restore delete lambda in v76 for the original Delete button
    move-object/from16 v76, v1
    """
            items_content = p_delete_btn.sub(lambda m: addon_buttons_smali + "\n    " + m.group(1), items_content, count=1)
            with open(items_targets[0], 'w', encoding='utf-8') as f:
                f.write(items_content)
            log("Patched AddonsScreen to add Config and Toggle IconButtons.")

    return True

def patch_exo_native_buffers(unpack_dir):
    """
    1. Patches BufferCeiling.smali (forDevice and compute) to scale buffers based on RAM / 4,
       capped by available physical device RAM (85% normal, 50% low memory).
    2. Patches RamTier.smali RAM_1 through RAM_8 safetyLimitMb and warningMb constants
       to exactly RAM / 4 (1GB: 256MB, 1.5GB: 384MB, 2GB: 512MB, 3GB: 768MB, 4GB: 1024MB, 6GB: 1536MB, 8GB: 2048MB).
    """
    log("Patching ExoPlayer buffer limits based on device RAM / 4...")

    # 1. Patch BufferCeiling.smali
    bc_targets = find_file_exact(unpack_dir, "BufferCeiling.smali")
    if bc_targets:
        with open(bc_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            bc_content = f.read()

        new_compute = """.method public final compute(Lcom/streamsync/app/settings/RamTier;IIZI)I
    .locals 3
    .param p1, "tier"    # Lcom/streamsync/app/settings/RamTier;
    .param p2, "heapLimitMb"    # I
    .param p3, "availableMb"    # I
    .param p4, "lowMemory"    # Z
    .param p5, "imageCacheMb"    # I

    const-string v0, "tier"
    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/streamsync/app/settings/RamTier;->getSafetyLimitMb()I
    move-result v0

    if-eqz p4, :cond_0

    int-to-float v1, p3
    const/high16 v2, 0x3f000000    # 0.5f
    mul-float/2addr v1, v2
    float-to-int v1, v1
    goto :goto_0

    :cond_0
    int-to-float v1, p3
    const v2, 0x3f59999a    # 0.85f
    mul-float/2addr v1, v2
    float-to-int v1, v1

    :goto_0
    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I
    move-result v0

    const/16 v1, 0x30
    invoke-static {v0, v1}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I
    move-result v0

    return v0
.end method"""

        new_for_device = """.method public final forDevice(Landroid/content/Context;Lcom/streamsync/app/settings/RamTier;)I
    .locals 3
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "tier"    # Lcom/streamsync/app/settings/RamTier;

    const-string v0, "tier"
    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p2}, Lcom/streamsync/app/settings/RamTier;->getSafetyLimitMb()I
    move-result v0

    if-eqz p1, :cond_exit

    :try_start_0
    sget-object v1, Lcom/streamsync/app/settings/DeviceMemory;->INSTANCE:Lcom/streamsync/app/settings/DeviceMemory;
    invoke-virtual {v1, p1}, Lcom/streamsync/app/settings/DeviceMemory;->read(Landroid/content/Context;)Lcom/streamsync/app/settings/DeviceMemory$Info;
    move-result-object v1

    invoke-virtual {v1}, Lcom/streamsync/app/settings/DeviceMemory$Info;->getAvailableMb()I
    move-result v2

    invoke-virtual {v1}, Lcom/streamsync/app/settings/DeviceMemory$Info;->getLowMemory()Z
    move-result v1

    if-eqz v1, :cond_normal

    int-to-float v1, v2
    const/high16 v2, 0x3f000000    # 0.5f
    mul-float/2addr v1, v2
    float-to-int v1, v1
    goto :goto_cap

    :cond_normal
    int-to-float v1, v2
    const v2, 0x3f59999a    # 0.85f
    mul-float/2addr v1, v2
    float-to-int v1, v1

    :goto_cap
    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I
    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    :cond_exit
    const/16 v1, 0x30
    invoke-static {v0, v1}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I
    move-result v0

    return v0
.end method"""

        p_compute = re.compile(r'\.method public final compute\(Lcom/streamsync/app/settings/RamTier;IIZI\)I[\s\S]*?\.end method')
        p_for_device = re.compile(r'\.method public final forDevice\(Landroid/content/Context;Lcom/streamsync/app/settings/RamTier;\)I[\s\S]*?\.end method')

        if p_compute.search(bc_content):
            bc_content = p_compute.sub(new_compute, bc_content)
        if p_for_device.search(bc_content):
            bc_content = p_for_device.sub(new_for_device, bc_content)

        with open(bc_targets[0], 'w', encoding='utf-8') as f:
            f.write(bc_content)
        log("Patched BufferCeiling.compute and BufferCeiling.forDevice with RAM/4 safety limits.")

    # 2. Patch RamTier.smali
    rt_targets = find_file_exact(unpack_dir, "RamTier.smali")
    if rt_targets:
        with open(rt_targets[0], 'r', encoding='utf-8', errors='ignore') as f:
            rt_content = f.read()

        # Update RAM_1: safetyLimit 256 (0x100), warning 320 (0x140)
        rt_content = re.sub(
            r'(const/16\s+v9,\s*)0x60(\s*\n\s*const/16\s+v10,\s*)0x78(\s*\n\s*const-string\s+v1,\s*"RAM_1")',
            r'\g<1>0x100\g<2>0x140\g<3>',
            rt_content
        )
        # Update RAM_15: safetyLimit 384 (0x180), warning 480 (0x1e0)
        rt_content = re.sub(
            r'(const/16\s+v21,\s*)0x70(\s*\n\s*const/16\s+v22,\s*)0x8c(\s*\n\s*const-string\s+v13,\s*"RAM_15")',
            r'\g<1>0x180\g<2>0x1e0\g<3>',
            rt_content
        )
        # Update RAM_2: safetyLimit 512 (0x200), warning 640 (0x280)
        rt_content = re.sub(
            r'(const/16\s+v10,\s*)0x80(\s*\n\s*const/16\s+v11,\s*)0xa0(\s*\n\s*const-string\s+v2,\s*"RAM_2")',
            r'\g<1>0x200\g<2>0x280\g<3>',
            rt_content
        )
        # Update RAM_3: safetyLimit 768 (0x300), warning 960 (0x3c0)
        rt_content = re.sub(
            r'(const/16\s+v21,\s*)0xa0(\s*\n\s*const/16\s+v22,\s*)0xc8(\s*\n\s*const-string\s+v13,\s*"RAM_3")',
            r'\g<1>0x300\g<2>0x3c0\g<3>',
            rt_content
        )
        # Update RAM_4: safetyLimit 1024 (0x400), warning 1280 (0x500)
        rt_content = re.sub(
            r'(const/16\s+v10,\s*)0xc8(\s*\n\s*const/16\s+v11,\s*)0xfa(\s*\n\s*const-string\s+v2,\s*"RAM_4")',
            r'\g<1>0x400\g<2>0x500\g<3>',
            rt_content
        )
        # Update RAM_6: safetyLimit 1536 (0x600), warning 1920 (0x780)
        rt_content = re.sub(
            r'(const/16\s+v21,\s*)0x100(\s*\n\s*const/16\s+v22,\s*)0x140(\s*\n\s*const-string\s+v13,\s*"RAM_6")',
            r'\g<1>0x600\g<2>0x780\g<3>',
            rt_content
        )
        # Update RAM_8: safetyLimit 2048 (0x800), warning 2560 (0xa00)
        rt_content = re.sub(
            r'(const/16\s+v10,\s*)0x140(\s*\n\s*const/16\s+v11,\s*)0x190(\s*\n\s*const-string\s+v2,\s*"RAM_8")',
            r'\g<1>0x800\g<2>0xa00\g<3>',
            rt_content
        )

        with open(rt_targets[0], 'w', encoding='utf-8') as f:
            f.write(rt_content)
        log("Patched RamTier constants to RAM / 4 limits (up to 2048MB).")

    return True

def patch_disable_logging(unpack_dir):
    """
    Disables all disk and Logcat logging across the app (FileLog).
    """
    log("Patching: Disabling logging from app and device (FileLog)...")
    file_log_targets = find_files(unpack_dir, r"^FileLog\.smali$")
    for target in file_log_targets:
        with open(target, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        # Disable write(Ljava/lang/String;)V
        content = re.sub(
            r'\.method public final write\(Ljava/lang/String;\)V[\s\S]*?\.end method',
            r'''.method public final write(Ljava/lang/String;)V
    .registers 2
    return-void
.end method''',
            content
        )

        # Disable d(Ljava/lang/String;Ljava/lang/Throwable;)V
        content = re.sub(
            r'\.method public final d\(Ljava/lang/String;Ljava/lang/Throwable;\)V[\s\S]*?\.end method',
            r'''.method public final d(Ljava/lang/String;Ljava/lang/Throwable;)V
    .registers 3
    return-void
.end method''',
            content
        )

        # Disable w(Ljava/lang/String;Ljava/lang/Throwable;)V
        content = re.sub(
            r'\.method public final w\(Ljava/lang/String;Ljava/lang/Throwable;\)V[\s\S]*?\.end method',
            r'''.method public final w(Ljava/lang/String;Ljava/lang/Throwable;)V
    .registers 3
    return-void
.end method''',
            content
        )

        # Disable init(Landroid/content/Context;)V
        content = re.sub(
            r'\.method public final init\(Landroid/content/Context;\)V[\s\S]*?\.end method',
            r'''.method public final init(Landroid/content/Context;)V
    .registers 2
    return-void
.end method''',
            content
        )

        # Disable synthetic d$default
        content = re.sub(
            r'\.method public static synthetic d\$default\(Lcom/streamsync/app/util/FileLog;Ljava/lang/String;Ljava/lang/Throwable;ILjava/lang/Object;\)V[\s\S]*?\.end method',
            r'''.method public static synthetic d$default(Lcom/streamsync/app/util/FileLog;Ljava/lang/String;Ljava/lang/Throwable;ILjava/lang/Object;)V
    .registers 5
    return-void
.end method''',
            content
        )

        # Disable synthetic w$default
        content = re.sub(
            r'\.method public static synthetic w\$default\(Lcom/streamsync/app/util/FileLog;Ljava/lang/String;Ljava/lang/Throwable;ILjava/lang/Object;\)V[\s\S]*?\.end method',
            r'''.method public static synthetic w$default(Lcom/streamsync/app/util/FileLog;Ljava/lang/String;Ljava/lang/Throwable;ILjava/lang/Object;)V
    .registers 5
    return-void
.end method''',
            content
        )

        with open(target, 'w', encoding='utf-8') as f:
            f.write(content)
        log(f"Disabled all logging methods in {os.path.basename(target)}.")

    return True

def patch_manifest_and_resources(unpack_dir):
    """Ensures Android TV Leanback features, banner, permissions, and versionCode bump in manifest and res"""
    manifest_path = os.path.join(unpack_dir, "AndroidManifest.xml")
    if not os.path.exists(manifest_path):
        log(f"ERROR: {manifest_path} not found!")
        return False

    with open(manifest_path, 'r', encoding='utf-8', errors='ignore') as f:
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

    # 3b. Add FileProvider for in-app OTA installer
    if "com.streamsync.app.fileprovider" not in manifest:
        provider_xml = """        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.streamsync.app.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
    </application>"""
        manifest = manifest.replace("</application>", provider_xml, 1)
        log("Added FileProvider to AndroidManifest.")
        changed = True

    # 3c. Register TrailerPlayerActivity for in-app YouTube trailer playback
    if "com.streamsync.app.ui.detail.TrailerPlayerActivity" not in manifest:
        activity_xml = """        <activity
            android:name="com.streamsync.app.ui.detail.TrailerPlayerActivity"
            android:configChanges="orientation|screenSize|screenLayout|keyboardHidden"
            android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
            android:exported="false" />
    </application>"""
        manifest = manifest.replace("</application>", activity_xml, 1)
        log("Registered TrailerPlayerActivity in AndroidManifest.")
        changed = True

    # 4. Bump versionCode to 197 and versionName to 1.9.7 for overwrite/update support
    if 'android:versionCode=' in manifest:
        manifest = re.sub(r'android:versionCode="[^"]*"', 'android:versionCode="197"', manifest)
        manifest = re.sub(r'android:versionName="[^"]*"', 'android:versionName="1.9.7"', manifest)
        log("Updated AndroidManifest versionCode to 197 and versionName to 1.9.7.")
        changed = True
    else:
        manifest = re.sub(r'<manifest\s+', '<manifest android:versionCode="197" android:versionName="1.9.7" ', manifest)
        log("Inserted android:versionCode and versionName into AndroidManifest.")
        changed = True

    # 4b. Create res/xml/file_paths.xml for FileProvider
    xml_dir = os.path.join(unpack_dir, "res", "xml")
    os.makedirs(xml_dir, exist_ok=True)
    file_paths_xml = """<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <cache-path name="cache" path="." />
    <external-cache-path name="external_cache" path="." />
    <external-files-path name="external_files" path="." />
    <files-path name="files" path="." />
</paths>
"""
    with open(os.path.join(xml_dir, "file_paths.xml"), "w", encoding="utf-8") as f:
        f.write(file_paths_xml)
    log("Created res/xml/file_paths.xml.")

    # 5. Add banner attribute to application if not present
    if 'android:banner=' not in manifest:
        manifest = manifest.replace("<application ", '<application android:banner="@drawable/tv_banner" ', 1)
        log("Added android:banner=@drawable/tv_banner to application tag.")
        changed = True

    # 5b. Add largeHeap="true" to application if not present
    if 'android:largeHeap="true"' not in manifest:
        manifest = manifest.replace("<application ", '<application android:largeHeap="true" ', 1)
        log("Added android:largeHeap=true to application tag.")
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
        
        yml_content = re.sub(r"versionCode:\s*[^\r\n]+", lambda m: "versionCode: '197'", yml_content)
        yml_content = re.sub(r"versionName:\s*[^\r\n]+", lambda m: "versionName: '1.9.7'", yml_content)

        if "doNotCompress:" in yml_content and "- so" not in yml_content:
            yml_content = yml_content.replace(
                "doNotCompress:\n",
                "doNotCompress:\n- so\n- lib/arm64-v8a/libffmpegJNI.so\n- lib/arm64-v8a/libtranslate_jni.so\n- lib/arm64-v8a/liblanguage_id_l2c_jni.so\n"
            )
        with open(apktool_yml_path, 'w', encoding='utf-8') as yf:
            yf.write(yml_content)
        log("Updated apktool.yml with version 197 (1.9.7) and uncompressed shared libraries.")

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
            shutil.copy2(src_lib, dst_lib)
            log(f"Copied armeabi-v7a lib: {lib} ({os.path.getsize(dst_lib)} bytes)")

def main():
    if len(sys.argv) < 2:
        print("Usage: python3 patch_nuvio_ui.py <path_to_apk_unpack_dir> [repo_root]")
        sys.exit(1)

    unpack_dir = sys.argv[1]
    repo_root = sys.argv[2] if len(sys.argv) > 2 else os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    script_dir = os.path.dirname(os.path.abspath(__file__))

    if not os.path.isdir(unpack_dir):
        print(f"Directory not found: {unpack_dir}")
        sys.exit(1)

    log(f"Starting Nuvio UI patches on: {unpack_dir}")
    patch_continue_watching(unpack_dir)
    patch_image_prefetch(unpack_dir)
    patch_single_swipe_and_exit_dialog(unpack_dir, script_dir)
    patch_default_decoder(unpack_dir)
    patch_settings_integrations_and_account_and_updater(unpack_dir, script_dir)
    patch_meta_screen(unpack_dir, script_dir)
    patch_addons_screen(unpack_dir)
    patch_exo_native_buffers(unpack_dir)
    patch_disable_logging(unpack_dir)
    patch_manifest_and_resources(unpack_dir)
    inject_native_libraries(unpack_dir, repo_root)
    log("All Nuvio UI & feature patches successfully applied!")

if __name__ == "__main__":
    main()

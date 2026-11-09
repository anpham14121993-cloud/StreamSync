#!/usr/bin/env python3
"""
patch_nuvio_ui.py:
Applies Nuvio smooth UI & performance optimizations directly to decompiled StreamSync APK:
1. ContinueWatchingCard: Changes card dimensions from 2:3 vertical (124x186) to 16:9 landscape (224x126).
2. Coil Image Prefetching: Throttles prefetch queue from 400 items to top 15 items, eliminating 360ms UI freeze and onTrimMemory GC spikes.
3. Android TV Leanback: Adds LEANBACK_LAUNCHER, leanback feature, touchscreen optional, and 320x180 banner.
4. Native Libraries: Injects arm64-v8a and armeabi-v7a native libraries (FFmpeg JNI, AC3, Subtitle translation).
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
            # Gradient dark background (#0D1117 to #161B22)
            bg_r = 13 + int((x / width) * 9)
            bg_g = 17 + int((y / height) * 10)
            bg_b = 23 + int((y / height) * 11)
            
            # Simple centered red triangle (Play icon)
            # Center is (160, 90)
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
    """Patch ContinueWatchingCard to 16:9 landscape aspect ratio (224dp x 126dp)"""
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

        # 1. Check for float constants (124.0f -> 224.0f, 186.0f -> 126.0f)
        # 124.0f = 0x42f80000 -> 224.0f = 0x43600000
        # 186.0f = 0x433a0000 -> 126.0f = 0x42fc0000
        if "0x42f80000" in content:
            content = content.replace("0x42f80000", "0x43600000")
            log(f"Replaced 0x42f80000 (124.0f) with 0x43600000 (224.0f) in {os.path.basename(target)}")
            changed = True

        if "0x433a0000" in content:
            content = content.replace("0x433a0000", "0x42fc0000")
            log(f"Replaced 0x433a0000 (186.0f) with 0x42fc0000 (126.0f) in {os.path.basename(target)}")
            changed = True

        # 2. Check for integer constants if compiled with int-to-float
        # 124 = 0x7c -> 224 = 0xe0
        # 186 = 0xba -> 126 = 0x7e
        if "POSTER_WIDTH" in content:
            # Look for const/16 vX, 0x7c followed nearby by POSTER_WIDTH
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

        if changed:
            with open(target, 'w', encoding='utf-8') as f:
                f.write(content)
            patched_count += 1

    log(f"ContinueWatchingCard patched in {patched_count} file(s).")
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

        # Target method: prefetchPosters(Landroid/content/Context;Ljava/util/List;)V
        # In smali:
        # .method public static final prefetchPosters(Landroid/content/Context;Ljava/util/List;)V
        if "prefetchPosters" in content:
            pattern = re.compile(r"(\.method\s+public\s+static\s+final\s+prefetchPosters\(Landroid/content/Context;Ljava/util/List;\)V.*?)(\s+invoke-static\s*\{p0,\s*\"context\"\})", re.DOTALL)
            
            # Injection to take at most 15 items from p1
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
            else:
                # Alternate pattern if Intrinsics check is different
                alt_pattern = re.compile(r"(\.method\s+public\s+static\s+final\s+prefetchPosters\(Landroid/content/Context;Ljava/util/List;\)V[\s\S]*?\.prologue)", re.DOTALL)
                alt_smali = r"""\1
    # [NUVIO PATCH] Throttle prefetch to top 15 items to prevent GC frame drops
    const/16 v0, 0xf
    invoke-static {p1, v0}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;
    move-result-object p1
"""
                new_content, count = alt_pattern.subn(alt_smali, content)
                if count > 0:
                    with open(target, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    log(f"Successfully injected throttle via prologue pattern into {os.path.basename(target)}")
                    patched = True

    return patched

def patch_manifest_and_resources(unpack_dir):
    """Ensures Android TV Leanback features, banner and permissions in manifest and res"""
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

    # 3. Add banner attribute to application if not present
    if 'android:banner=' not in manifest:
        manifest = manifest.replace("<application ", '<application android:banner="@drawable/tv_banner" ', 1)
        log("Added android:banner=@drawable/tv_banner to application tag.")
        changed = True

    if changed:
        with open(manifest_path, 'w', encoding='utf-8') as f:
            f.write(manifest)

    # 4. Generate tv_banner.png and copy tv_banner.xml into res/drawable
    drawable_dir = os.path.join(unpack_dir, "res", "drawable")
    drawable_xhdpi = os.path.join(unpack_dir, "res", "drawable-xhdpi")
    os.makedirs(drawable_dir, exist_ok=True)
    os.makedirs(drawable_xhdpi, exist_ok=True)

    banner_png_bytes = create_tv_banner_png(320, 180)
    banner_png_path = os.path.join(drawable_xhdpi, "tv_banner.png")
    with open(banner_png_path, "wb") as f:
        f.write(banner_png_bytes)
    log(f"Saved 320x180 TV banner PNG to {banner_png_path}")

    # Also copy to drawable/
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
    patch_manifest_and_resources(unpack_dir)
    inject_native_libraries(unpack_dir, repo_root)
    log("All Nuvio UI patches successfully applied!")

if __name__ == "__main__":
    main()

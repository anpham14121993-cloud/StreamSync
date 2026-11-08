# StreamSync

Decompiled source code of **StreamSync-v188-mobile-debug.apk**.

## Overview
- **Package**: `com.streamsync.app`
- **Application Class**: `com.streamsync.app.StreamSyncApp`
- **Main Activity**: `com.streamsync.app.MainActivity`
- **Architecture**: Kotlin + Jetpack Compose + Coroutines + Retrofit / OkHttp + ExoPlayer

## Project Structure
- `app/src/main/java/com/streamsync/app/`
  - `ui/`: Compose UI screens (Player, Catalog, Detail, Settings, Search, Library, Addons)
  - `subtitle/`: Subtitle parser, translation (`SubtitleTranslator`), and offset management
  - `transcode/`: AC3 Audio encoder & transcoding audio sink
  - `net/`: DoH resolver, DNS resolver, and DPI bypass socket factory
  - `settings/`: Playback, UI, buffer, and subtitle style stores
  - `diag/`: Crash reporter, frame timing logger, last exit diagnostics
  - `data/`: Addon repository, remote API, and local storage
- `app/src/main/jniLibs/armeabi-v7a/`:
  - `libac3encoder.so`
  - `libffmpegJNI.so`
  - `liblanguage_id_l2c_jni.so`
  - `libtranslate_jni.so`
- `app/src/main/res/`: Android resources and layouts
- `app/src/main/AndroidManifest.xml`: Android manifest

# Android

Kanama runs inside a stock Godot Android export through a Godot Android plugin
AAR, without a Godot fork and without custom JNI in game code. This page is the
export workflow: the toolchain, the build/install/export commands, the smoke
script, and the boundaries you meet while shipping. The support tier, the
device evidence behind it, and the caveats it carries (validated Android
versions per build type, the runtime-only packaged addon, the PanamaPort fork,
no mobile hot reload) are recorded once in
[Version Support → Android](../reference/version-support.md#android); the
runtime design and its audits are in
[Android Internals](../contributing/backends/android.md).

Use the pinned Godot release from
[Version Support → Requirements](../reference/version-support.md#requirements)
for both the editor binary and the Android export templates.

## What Gets Installed

The Android deliverable is two AARs (runtime/scripts split, mirroring
desktop's `kanama.jar` / `kanama-scripts.jar`):

- **Runtime AAR** (`KanamaAndroid.debug.aar`, project-agnostic): the Godot
  Android plugin class, a Java bootstrap that loads `libkanama_bootstrap.so`,
  the Android native bootstrap library, remapped Kanama runtime classes, and
  `.gdextension` metadata for Android AAR loading.
- **Scripts AAR** (`KanamaAndroidScripts.debug.aar`, per project): the game's
  Kotlin scripts and generated KSP registrars, remapped and compiled against
  the runtime classes. The runtime plugin's `.gdap` references it as a local
  dependency, so both AARs land in the exported APK.

PanamaPort dependency metadata is declared in the `.gdap`. The exported game
runs on Android ART with [PanamaPort](https://github.com/vova7878/PanamaPort)
as the FFM layer rather than on a desktop JVM — how that works, and how the
source remap is audited, is in
[Android Internals](../contributing/backends/android.md). Game projects should
not need JNI code, a custom Godot fork, or hand-edited Android native code.

## Toolchain

Kanama itself builds on the desktop JDK and Godot's Android Gradle export runs
on its own JDK; both versions, and the Godot pin, are in
[Version Support → Requirements](../reference/version-support.md#requirements).
The Android game runtime is ART plus PanamaPort, not a desktop JDK. This table
owns the Android tool versions:

| Tool | Version / Setting |
|---|---|
| Godot Android export template AGP | 8.6.1 |
| Godot Android export template compile SDK | 36 |
| Godot Android export template target SDK | 36 |
| Godot Android export template min SDK | 24 |
| Godot Android export template build tools | 36.1.0 |
| Godot Android export template NDK | 29.0.14206865 |
| Kanama Android plugin AGP | 9.2.1 |
| Kanama Android plugin compile SDK | 36 |
| Kanama Android plugin min SDK | 26 |
| Kanama Android plugin CMake | 3.22+ (`cmake_minimum_required`; AGP's default 3.22.1 is what builds it) |
| Kanama AAR ABIs | `arm64-v8a`, `x86_64` |
| Demo APK export ABI | `arm64-v8a` |

Configure Android SDK, NDK, CMake, and JDK locations through Godot editor
settings or standard environment variables such as `ANDROID_HOME`,
`ANDROID_SDK_ROOT`, and `JAVA_HOME`. Godot's export-template build needs the
SDK platform, build-tools, and NDK versions in the table installed. Do not
commit workstation-local paths.

## Godot Export Requirements

Godot Android exports that use local Android plugins must use the Gradle export
path, not the plain template-only path.

Requirements:

- Android export templates installed for the same Godot release as the editor
  (the pinned release in
  [Version Support → Requirements](../reference/version-support.md#requirements)).
- Android build template installed for the project, either from the editor or
  with `--install-android-build-template`.
- Export preset has `gradle_build/use_gradle_build=true`.
- Project contains `android/plugins/KanamaAndroid.gdap`.
- Project contains the matching `KanamaAndroid.debug.aar` and
  `KanamaAndroidScripts.debug.aar`.
- `addons/kanama/kanama.gdextension` contains Android AAR entries:

```ini
[configuration]
android_aar_plugin = true

[libraries]
android.debug.arm64 = "libkanama_bootstrap.so"
android.release.arm64 = "libkanama_bootstrap.so"
android.debug.x86_64 = "libkanama_bootstrap.so"
android.release.x86_64 = "libkanama_bootstrap.so"
```

The Android plugin AAR also carries matching `.gdextension` metadata. The
project file uses Android entries so Godot's export scanner recognizes the
GDExtension for Android instead of warning about a missing `arm64` library.

## Build And Install The AAR

Build and install the Android plugin into a Godot project:

```sh
./gradlew installAndroidPluginAar \
  -PkanamaAndroidDemoDir=/absolute/path/to/godot_project
```

This builds `android/godot-plugin` (both halves), compiles the Android native
bootstrap from `bootstrap/bootstrap.c`, writes
`android/plugins/KanamaAndroid.debug.aar` (runtime) and
`android/plugins/KanamaAndroidScripts.debug.aar` (your project's scripts +
registrars), and writes `android/plugins/KanamaAndroid.gdap` referencing both.

The runtime half alone builds without a project directory
(`./gradlew assembleAndroidPluginAar`), which is what the packaged Android
add-on zip ships (`./gradlew packageMobileAddonAndroid`; prebuilt runtime
only — compiling your project's Kotlin scripts still requires this checkout's
`installAndroidPluginAar`).

## Export A Debug APK

```sh
godot --headless \
  --path /absolute/path/to/godot_project \
  --install-android-build-template \
  --export-debug Android /absolute/path/to/output.apk
```

Use a Godot binary that matches the project export preset and installed Android
export templates.

## Run A Smoke Test

```sh
ANDROID_HOME=/path/to/android/sdk \
ANDROID_SDK_ROOT=/path/to/android/sdk \
scripts/android_smoke.sh \
  /path/to/godot \
  /absolute/path/to/godot_project \
  com.example.package.name \
  /tmp/kanama-demo.apk
```

The smoke script builds scripts, installs the Kanama Android AAR, exports a
debug APK, installs and launches it with `adb`, checks logcat for Kanama startup
signals, verifies a captured screenshot is not blank, and force-stops the app
before exiting.

To run the same smoke under a different renderer, set
`KANAMA_ANDROID_RENDERER` (`mobile`, `gl_compatibility`, or `forward_plus`).
The script temporarily overrides the project's `rendering_method.mobile`
setting for the run (restored afterwards) and asserts in logcat that the
requested renderer actually initialized, so a silent fallback fails the smoke.
The device screen must be on and unlocked — a secure lock screen fails renderer
surface creation at launch.

## Boundaries And Troubleshooting

Constraints that shape how you write and ship an Android game:

- Android hot reload is not designed and should be considered disabled.
- Android-targeted game code should avoid nullable Kotlin callback invocation
  written as `callback?.invoke()`. The source audit rejects that form before
  remap because the Android compatibility pass can otherwise mistake it for
  low-level `MethodHandle.invoke(...)`.
- Android source remapping is guarded by audits, but it is still a generated
  compatibility path rather than a dedicated source-set design.
- Several action demos include Android-only touch overlays, D-pad controls, or
  `VirtualJoystick` controls so smoke runs can exercise real gameplay input on
  a phone. Mobile polish is still per demo: orientation, screen size, touch
  controls, and UI scaling are separate from the core runtime path. Godot 4.7
  `VirtualJoystick` wrappers are available in Kanama, but demo-level
  touch-control polish remains a project-specific validation claim.
- Physical devices can show first-use hitches when gameplay first loads or
  decodes scenes, audio streams, particles, or generated meshes. Mobile demos
  should preload/cache those resources and avoid rebuilding generated geometry
  every frame on input-driven paths.
- Emulator/OpenGL compatibility rendering can differ from desktop Forward+.
  In the 3D Platformer smoke, gameplay and geometry render correctly, but the
  skybox/background is darker than the upstream desktop screenshot. Some
  unsupported ASTC textures may be converted at runtime by the emulator.

Symptoms and what they mean:

| Symptom | Likely cause / fix |
|---|---|
| A **release** APK (`--export-release`, minified or not) crashes during PanamaPort's FFI bootstrap on Android 12 or older, while the debug APK runs. | Release builds are validated only on Android 13+; debug builds run down to Android 9. The floors, the models they were measured on, and the release-mode ART constraint behind them are in [Version Support → Android](../reference/version-support.md#android). Ship a debug build to older devices or raise the app's minimum SDK. |
| Any build fails the FFI bootstrap on every device **below Android 16**. | PanamaPort fork `0.1.3-kanama-r8.3` or newer is required (the `SDK_INT_FULL` bootstrap guard); older fork versions and upstream PanamaPort fail there. Check the coordinate under [PanamaPort](#panamaport). |
| A **minified release** APK crashes in the FFI bootstrap with `AssertionError: Should not reach here` at `nativeLinker().downcallHandle()`, then logs `No loader found for resource: res://kotlin-src/*.kt` and shows a flickering splash. | You are on upstream PanamaPort `v0.1.3`, which Godot's R8 mis-optimizes. Use Kanama's fork coordinate (below); the root cause and the fork's fix are in [Android Internals → PanamaPort](../contributing/backends/android.md#panamaport). |
| The app segfaults inside the vendor shader compiler (`libllvm-glnext.so`) before any Kanama code runs, on an old device with a factory image. | Old vendor **OpenGL** drivers (a Pixel 3 XL on its factory Android 9 image, 2018 Adreno 630) crash while linking Godot 4.7 Compatibility-renderer shaders. The same device runs the demo smoke with the **Mobile (Vulkan) renderer** (`rendering_method.mobile = "mobile"`; Vulkan 1.1.66 initialized, 2026-07-13). This is an engine-vs-driver constraint, not a Kanama one — the renderer is chosen by Godot from project settings before scripts run, so it cannot be switched from Kanama at runtime. Projects targeting older devices should prefer the Mobile renderer (Godot falls back to GL Compatibility on devices without usable Vulkan) and validate on real hardware. |
| The smoke fails at launch with a renderer surface error. | The device screen must be on and unlocked — a secure lock screen fails renderer surface creation. |

## PanamaPort

Kanama's Android plugin currently consumes a forked
[PanamaPort](https://github.com/vova7878/PanamaPort)
artifact, `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`, published
via [JitPack](https://jitpack.io) from Kanama's PanamaPort fork. The dependency
can be overridden with `-PkanamaPanamaPortCore=...`, and the Android export
scripts inject the JitPack repository (alongside the local Maven repository, for
fork iteration) into Godot's generated Gradle project. Upstream PanamaPort
`v0.1.3` from Maven Central is not an R8-supported release path for Kanama;
why, and what each fork revision fixed, is in
[Android Internals → PanamaPort](../contributing/backends/android.md#panamaport).

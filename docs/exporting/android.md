# Android

Kanama's Android path is **Supported** on Godot 4.7 stable,
device-validated across four physical models (Pixel 7 / Moto g 5G 2023 / Galaxy
S10+ / Pixel 3 XL, Android 16/14/12/9). It proves that
Kanama can run inside a
stock Godot Android export through a Godot Android plugin AAR, without a Godot
fork and without custom JNI in game code.

Use the
[Godot 4.7.2 stable archive](https://godotengine.org/download/archive/4.7.2-stable/)
for the matching editor binary and Android export templates.

Supported carries the mobile caveats recorded in
[Version Support](../reference/version-support.md): release builds require
Android 13+ (debug runs down to Android 9), the packaged addon is runtime-only
(compiling project scripts needs the Kanama checkout), the release path depends
on Kanama's PanamaPort fork, and there is no mobile hot reload.

## Current Status

The nine public demo exports (the eight detailed below plus Bunnymark) are
Android smoke targets. On 2026-06-26, the full
Godot 4.7 stable matrix passed on a Pixel 7 using debug APK exports, logcat
startup checks, and screenshot smoke checks. The R8-minified Match3 release APK
also passed on Pixel 7 when built against Kanama's PanamaPort fork
(`com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`). The minified-release
claim is tied to that forked dependency, not upstream PanamaPort `v0.1.3`.

| Demo | Current Result |
|---|---|
| `godot-demo-2d-dodge-the-creeps` | APK exports, launches, initializes Kanama, and handles D-pad input. |
| `Starter-Kit-3D-Platformer` | APK exports, launches, loads seven Kotlin scripts, reaches the main loop, renders the 3D scene, and runs gameplay logic with mobile controls. |
| `Starter-Kit-Match3` | APK exports, launches, initializes Kanama, and passes startup/screenshot smoke checks. |
| `godot-demo-3d-squash-the-creeps` | APK exports, launches, initializes Kanama, and passes startup/screenshot smoke checks. |
| `Starter-Kit-FPS` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with touch-control coverage. |
| `Starter-Kit-Racing` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with mobile steering controls. |
| `godot-4-3d-character-controller-tutorial` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with virtual joystick controls. |
| `godot-4-3d-third-person-controller` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with virtual joystick controls. |

On 2026-07-10, the same nine-demo matrix also passed under Godot's
**Vulkan/Mobile renderer** on the Pixel 7 (Mali-G710, Android 16): each demo was
exported with `rendering_method.mobile="mobile"`, and the smoke asserted the
renderer actually initialized (`Vulkan 1.4.305 - Forward Mobile` in logcat — a
silent OpenGL fallback fails the check) alongside the normal Kanama startup and
screenshot checks.

The Pixel 7 debug matrix, the minified Match3 gate, and the Vulkan/Mobile
renderer pass are the evidence behind the Supported claim; they are not a claim
of per-demo mobile polish. The demo corpus
ships OpenGL Compatibility as its default mobile renderer; both renderers are
now smoke-validated on Pixel 7, while gameplay/visual parity under Vulkan
beyond the smoke bar remains per-demo validation.

## Runtime Shape

Desktop Kanama embeds a normal JDK/JVM and calls Godot's GDExtension ABI through
`java.lang.foreign`.

Android does not embed a desktop JDK. The exported game runs on Android ART and
uses [PanamaPort](https://github.com/vova7878/PanamaPort)'s
`com.v7878.foreign` API for the FFM compatibility layer.

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

PanamaPort dependency metadata is declared in the `.gdap`.

Game projects should not need JNI code, a custom Godot fork, or hand-edited
Android native code.

## Toolchain

Use separate Java assumptions for desktop and Android:

- Desktop Kanama development: JDK 25.
- Android Gradle/export tooling: JDK 21.
- Android game runtime: ART plus PanamaPort, not a desktop JDK.

Current values:

| Tool | Version / Setting |
|---|---|
| Godot | 4.7.2 stable |
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
`ANDROID_SDK_ROOT`, and `JAVA_HOME`. The 4.7.2 stable Android export-template
build requires the Android SDK platform for API 36, build-tools 36.1.0, and NDK
29.0.14206865. Do not commit workstation-local paths.

## Validated Android Versions

The plugin's declared `minSdk` is 26, but the *validated* floor is
build-type-specific (2026-07-13 four-model matrix — Pixel 3 XL / Galaxy S10+ /
Moto g 5G 2023 / Pixel 7, Android 9/12/14/16):

| Build type | Floor | Evidence |
|---|---|---|
| **Debug** (`--export-debug`) | **Android 9** (API 28) | Nine-demo matrix green on Android 12 + 14 + 16; Android 9 validated via the dodge smoke (Vulkan/Mobile renderer — see the GL-driver boundary below) |
| **Release** (`--export-release`, minified or not) | **Android 13** (API 33); validated on 14 + 16 | On Android 12 and below, release-mode (`android:debuggable=false`) builds crash during PanamaPort's upcall-stub generation into the system `libLLVM_android.so` (argument marshalling lands shifted under release-mode ART's invocation of PanamaPort's runtime-bound native methods; debug-mode ART invokes them compatibly). Android 13 replaced that code path. Characterized with a full tombstone 2026-07-13; the fix is upstream-shaped and deferred |

Pre-16 devices additionally require fork `0.1.3-kanama-r8.3`+ (the
`SDK_INT_FULL` bootstrap guard) — older fork versions and upstream PanamaPort
fail the FFI bootstrap on every device below Android 16.

## Godot Export Requirements

Godot Android exports that use local Android plugins must use the Gradle export
path, not the plain template-only path.

Requirements:

- Android export templates installed for the matching Godot version from the
  [Godot 4.7.2 stable archive](https://godotengine.org/download/archive/4.7.2-stable/).
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

## Current Boundaries

- Android source remapping is guarded by audits, but it is still a generated
  compatibility path rather than a dedicated source-set design.
- Android-targeted game code should avoid nullable Kotlin callback invocation
  written as `callback?.invoke()`. The source audit rejects that form before
  remap because the Android compatibility pass can otherwise mistake it for
  low-level `MethodHandle.invoke(...)`.
- Android hot reload is not designed and should be considered disabled.
- Android is Supported on 4.7 stable; the device matrix spans four models
  (Android 9/12/14/16). Broader input coverage would still strengthen the claim.
  Release builds require Android 13+ (see "Validated Android Versions").
- Several action demos include Android-only touch overlays, D-pad controls, or
  `VirtualJoystick` controls so smoke runs can exercise real gameplay input on
  a phone. Mobile polish is still per demo: orientation, screen size, touch
  controls, and UI scaling are separate from the core runtime path.
- Physical devices can show first-use hitches when gameplay first loads or
  decodes scenes, audio streams, particles, or generated meshes. Mobile demos
  should preload/cache those resources and avoid rebuilding generated geometry
  every frame on input-driven paths.
- Old vendor **OpenGL** drivers can crash before any Kanama code runs: a
  Pixel 3 XL on its factory Android 9 image (2018 Adreno 630 GL driver)
  segfaults inside the vendor shader compiler (`libllvm-glnext.so`) while
  linking Godot 4.7 Compatibility-renderer shaders. The same device runs the
  demo smoke fine with the **Mobile (Vulkan) renderer**
  (`rendering_method.mobile = "mobile"`; Vulkan 1.1.66 initialized,
  2026-07-13). This is an engine-vs-driver constraint, not a Kanama one —
  the renderer is chosen by Godot from project settings before scripts run,
  so it cannot be switched from Kanama at runtime. Projects targeting older
  devices should prefer the Mobile renderer (Godot falls back to GL
  Compatibility on devices without usable Vulkan) and validate on real
  hardware.
- Emulator/OpenGL compatibility rendering can differ from desktop Forward+.
  In the 3D Platformer smoke, gameplay and geometry render correctly, but the
  skybox/background is darker than the upstream desktop screenshot.
- Some unsupported ASTC textures may be converted at runtime by the emulator.
- Godot 4.7 `VirtualJoystick` wrappers are available in Kanama, but demo-level
  touch-control polish remains a project-specific validation claim.
- R8/ProGuard minification is validated only with Kanama's PanamaPort fork,
  `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`. Root-caused on a Pixel 7
  (2026-06-26), upstream PanamaPort `v0.1.3` crashes in the FFI bootstrap at
  `nativeLinker().downcallHandle()` with `AssertionError: Should not reach
  here`; the recurring `No loader found for resource: res://kotlin-src/*.kt`
  and flickering splash are downstream of that crash. Deobfuscated, PanamaPort's
  Android linker (`_AndroidLinkerImpl`) builds native stubs with Java
  pattern-matching `switch`es over sealed types (`_LLVMStorageDescriptor`
  storages and the `MemoryLayout` hierarchy), and Godot 4.7's R8 (AGP 8.6.1)
  mis-optimizes those switches so they fall through to
  `default -> Utils.shouldNotReachHere()`. This is unfixable from consumer keep
  rules: keeping the sealed types blocks the optimization PanamaPort's own
  `@CheckDiscard` rules require, failing the build; not keeping them leaves the
  switch broken at runtime. The fork rewrites the affected source switch sites
  to explicit `instanceof` branches and adds targeted R8 annotations/signing
  mechanics. `scripts/android_export_minified.sh` now builds, installs, launches,
  and verifies the minified Match3 release APK on Pixel 7 (Android 16) and
  Moto g 5G 2023 (Android 14). A second R8 landmine was fixed in fork r8.4
  (2026-07-13): AGP 8.6.1's R8 collapses a one-element `byte[]...` varargs
  call into `filled-new-array` of `byte[][]`, which the ART interpreter below
  Android 13 segfaults on; the fork's `NativeCodeBlob.makeCodeBlobSingle`
  removes the only such site. Release builds below Android 13 remain blocked
  by the separate release-mode PanamaPort constraint above ("Validated
  Android Versions").

## Validation Shape

Android validation uses the normal demo folders plus Android export metadata,
the Kanama Android plugin AAR, `adb install`, logcat checks, screenshots, and a
forced package stop after the run. The support claim rests on the four-model
device matrix and the Vulkan/Mobile renderer coverage; broader input coverage
would strengthen it further.

## PanamaPort

Kanama's Android plugin currently consumes a forked
[PanamaPort](https://github.com/vova7878/PanamaPort)
artifact, `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`, published
via [JitPack](https://jitpack.io) from Kanama's PanamaPort fork. The dependency
can be overridden with `-PkanamaPanamaPortCore=...`, and the Android export
scripts inject the JitPack repository (alongside the local Maven repository, for
fork iteration) into Godot's generated Gradle project. Upstream PanamaPort
`v0.1.3` from Maven Central is not an R8-supported release path for Kanama.

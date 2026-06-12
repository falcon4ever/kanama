# Android Experimental

Kanama's Android path is experimental for the v0.2.2 line and currently tracks
the Godot 4.7 rc 2 preview baseline. It proves that Kanama can run inside a
stock Godot Android export through a Godot Android plugin AAR, without a Godot
fork and without custom JNI in game code.

Use the
[Godot 4.7 rc 2 archive](https://godotengine.org/download/archive/4.7-rc2/)
for the matching editor binary and Android export templates.

This is not a production-ready mobile target yet. Treat it as an opt-in preview
for testing Kanama games on Android.

## Current Status

Eight public demo exports are Android smoke targets. The rc 2 Android matrix
is pending revalidation; the last completed Android matrix had emulator
validation and Pixel 7 startup/playability coverage, while remaining an
experimental physical-device path:

| Demo | Current Result |
|---|---|
| `godot-demo-2d-dodge-the-creeps` | APK exports, launches, initializes Kanama, and handles D-pad input. |
| `Starter-Kit-3D-Platformer` | APK exports, launches, loads seven Kotlin scripts, reaches the main loop, renders the 3D scene, and runs gameplay logic with mobile controls. |
| `Starter-Kit-Match3` | APK exports, launches, initializes Kanama, and passes startup/screenshot smoke checks. |
| `godot-demo-3d-squash-the-creeps` | APK exports, launches, initializes Kanama, and passes startup/screenshot smoke checks. |
| `Starter-Kit-FPS` | Android export preset and smoke target with touch controls for startup/playability checks. |
| `Starter-Kit-Racing` | Android export preset and smoke target with mobile steering controls. |
| `godot-4-3d-character-controller-tutorial` | Android export preset and smoke target with virtual joystick controls. |
| `godot-4-3d-third-person-controller` | Android export preset and smoke target with virtual joysticks and warmup coverage for gameplay hitches. |

Pixel 7 coverage in the last completed Android matrix includes manual
playability checks for the
Android-enabled demos in addition to startup/screenshot smoke checks. Android
still remains experimental; these checks validate the preview path, not
production mobile support.

The validated Android demos currently use Godot's OpenGL Compatibility renderer.
Desktop demos can continue using their normal renderer. Vulkan/Mobile-renderer
validation is a separate support claim.

## Runtime Shape

Desktop Kanama embeds a normal JDK/JVM and calls Godot's GDExtension ABI through
`java.lang.foreign`.

Android does not embed a desktop JDK. The exported game runs on Android ART and
uses [PanamaPort](https://github.com/vova7878/PanamaPort)'s
`com.v7878.foreign` API for the FFM compatibility layer.

The Android AAR contains:

- the Godot Android plugin class,
- a Java bootstrap that loads `libkanama_bootstrap.so`,
- the Android native bootstrap library,
- remapped Kanama runtime classes,
- game Kotlin scripts and generated KSP registrars,
- `.gdextension` metadata for Android AAR loading, and
- PanamaPort dependency metadata.

Game projects should not need JNI code, a custom Godot fork, or hand-edited
Android native code.

## Toolchain

Use separate Java assumptions for desktop and Android:

- Desktop Kanama development: JDK 25.
- Android Gradle/export tooling: JDK 21.
- Android game runtime: ART plus PanamaPort, not a desktop JDK.

Current experimental values:

| Tool | Version / Setting |
|---|---|
| Godot | 4.7 rc 2 preview |
| Godot Android export template AGP | 8.6.1 |
| Godot Android export template compile SDK | 36 |
| Godot Android export template target SDK | 36 |
| Godot Android export template min SDK | 24 |
| Godot Android export template build tools | 36.1.0 |
| Godot Android export template NDK | 29.0.14206865 |
| Kanama Android plugin AGP | 9.2.1 |
| Kanama Android plugin compile SDK | 36 |
| Kanama Android plugin min SDK | 26 |
| Kanama Android plugin CMake | 3.22.1 |
| Kanama AAR ABIs | `arm64-v8a`, `x86_64` |
| Demo APK export ABI | `arm64-v8a` |

Configure Android SDK, NDK, CMake, and JDK locations through Godot editor
settings or standard environment variables such as `ANDROID_HOME`,
`ANDROID_SDK_ROOT`, and `JAVA_HOME`. The 4.7 rc 2 Android export-template
build requires the Android SDK platform for API 36, build-tools 36.1.0, and NDK
29.0.14206865. Do not commit workstation-local paths.

## Godot Export Requirements

Godot Android exports that use local Android plugins must use the Gradle export
path, not the plain template-only path.

Requirements:

- Android export templates installed for the matching Godot version from the
  [Godot 4.7 rc 2 archive](https://godotengine.org/download/archive/4.7-rc2/).
- Android build template installed for the project, either from the editor or
  with `--install-android-build-template`.
- Export preset has `gradle_build/use_gradle_build=true`.
- Project contains `android/plugins/KanamaAndroid.gdap`.
- Project contains the matching `KanamaAndroid.debug.aar`.
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

Build and install the experimental Android plugin into a Godot project:

```sh
./gradlew installAndroidPluginAar \
  -PkanamaAndroidDemoDir=/absolute/path/to/godot_project
```

This builds `android/godot-plugin`, compiles the Android native bootstrap from
`bootstrap/bootstrap.c`, writes `android/plugins/KanamaAndroid.debug.aar`, and
writes `android/plugins/KanamaAndroid.gdap`.

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

## Current Boundaries

- Android source remapping is guarded by audits, but it is still a generated
  compatibility path rather than a dedicated source-set design.
- Android-targeted game code should avoid nullable Kotlin callback invocation
  written as `callback?.invoke()`. The source audit rejects that form before
  remap because the Android compatibility pass can otherwise mistake it for
  low-level `MethodHandle.invoke(...)`.
- Android hot reload is not designed and should be considered disabled.
- Current validation remains experimental even when Pixel 7 smoke checks pass;
  broader physical-device, renderer, and input coverage would be stronger
  platform claims.
- Several action demos include Android-only touch overlays, D-pad controls, or
  `VirtualJoystick` controls so smoke runs can exercise real gameplay input on
  a phone. Mobile polish is still per demo: orientation, screen size, touch
  controls, and UI scaling are separate from the core runtime path.
- Physical devices can show first-use hitches when gameplay first loads or
  decodes scenes, audio streams, particles, or generated meshes. Mobile demos
  should preload/cache those resources and avoid rebuilding generated geometry
  every frame on input-driven paths.
- Emulator/OpenGL compatibility rendering can differ from desktop Forward+.
  In the 3D Platformer smoke, gameplay and geometry render correctly, but the
  skybox/background is darker than the upstream desktop screenshot.
- Some unsupported ASTC textures may be converted at runtime by the emulator.
- Godot 4.7 `VirtualJoystick` wrappers are available in Kanama, but demo-level
  touch-control polish remains a project-specific validation claim.

## Validation Shape

Android validation uses the normal demo folders plus Android export metadata,
the Kanama Android plugin AAR, `adb install`, logcat checks, screenshots, and a
forced package stop after the run. Current preview claims stay experimental
unless broader physical-device and renderer coverage are added later.

## PanamaPort

Kanama currently consumes
[PanamaPort](https://github.com/vova7878/PanamaPort)
`io.github.vova7878.panama:Core:v0.1.3` from Maven Central. The current Android
work did not require PanamaPort edits, so Kanama does not need a fork today.

Kanama can continue using the published PanamaPort artifact unless platform
testing uncovers a dependency fix that cannot be handled upstream.

# Android Internals

This page records the current Android implementation path. Android is an
experimental Kanama target for the Godot 4.7 rc 2 preview baseline, and the
API/build flow is less settled than the desktop path.

## What Works

Eight Kanama demo exports are Android smoke targets. The Godot 4.7 rc 2
Android matrix is pending revalidation:

- `godot-demo-2d-dodge-the-creeps`
- `Starter-Kit-3D-Platformer`
- `Starter-Kit-Match3`
- `godot-demo-3d-squash-the-creeps`
- `Starter-Kit-FPS`
- `Starter-Kit-Racing`
- `godot-4-3d-character-controller-tutorial`
- `godot-4-3d-third-person-controller`

The 3D Platformer and third-person controller runs are stronger signals because
they exercise larger scenes, Android input overlays, and gameplay warmup paths.
The exported APK path:

- loads the Android Godot plugin AAR,
- loads `libkanama_bootstrap.so` from the APK,
- captures the Android JVM through JNI,
- initializes the Kanama GDExtension,
- registers `KanamaScriptLanguage`,
- registers the `.kt` resource loader,
- loads project Kotlin scripts,
- reaches `OnGodotMainLoopStarted`, and
- renders the 3D scene through Godot's OpenGL compatibility renderer.

This proves the basic Kanama runtime path is viable on Android. Treat it as
experimental support, not a production-ready mobile target.

## PanamaPort

The current Android implementation did not require editing
[PanamaPort](https://github.com/vova7878/PanamaPort). The Android build
consumes `io.github.vova7878.panama:Core:v0.1.3` as a dependency.

A fork is not part of the current implementation. Kanama currently consumes the
published artifact directly.

## Desktop vs Android FFM

Desktop Kanama uses JDK 25 and `java.lang.foreign`.

Android does not run a desktop JDK inside the game. The Android runtime uses
ART, and the Android build maps the Kanama FFM-facing code to
[PanamaPort](https://github.com/vova7878/PanamaPort)'s `com.v7878.foreign`
package.

The build tools are also separate from the runtime:

- Desktop development currently uses JDK 25.
- Godot's Android Gradle export flow is run with JDK 21.
- Godot 4.7 rc 2 Android export templates require Android SDK platform API
  36, build-tools 36.1.0, and NDK 29.0.14206865.
- The exported Android game runs on ART and PanamaPort, not on a desktop JVM.

## Implementation Shape

The current Android implementation lives under `android/godot-plugin`.

The AAR includes:

- a Godot Android plugin class,
- a small Java bootstrap that loads `libkanama_bootstrap.so`,
- the Android native bootstrap library,
- the remapped Kanama runtime sources,
- the demo's Kotlin scripts and generated KSP registrars, and
- a `.gdextension` file marked as an Android AAR plugin.

The Android build currently copies Kanama desktop sources into generated Android
sources and applies a small compatibility remap. That remap is deliberately
guarded by `auditAndroidKanamaSources`, which fails the Android build if stale
desktop-only APIs or incorrectly rewritten Kotlin callback calls appear in the
generated Android source tree.

The demo project needs Android entries in `addons/kanama/kanama.gdextension`:

```ini
[configuration]
android_aar_plugin = true

[libraries]
android.debug.arm64 = "libkanama_bootstrap.so"
android.release.arm64 = "libkanama_bootstrap.so"
android.debug.x86_64 = "libkanama_bootstrap.so"
android.release.x86_64 = "libkanama_bootstrap.so"
```

The Android AAR also includes matching `.gdextension` metadata. The project file
uses Android entries so Godot's export scanner recognizes the extension for
Android instead of warning that no `arm64` library exists.

## Current Boundaries

The current Android path is intentionally narrow:

- The Android source remap is a guarded Gradle step rather than a dedicated
  source-set design.
- Some JVM APIs used on desktop have Android-compatible replacements.
- `MethodHandle.invoke(...)` uses Android-compatible handling through
  `invokeWithArguments(...)`.
- Demo Kotlin sources are audited before remap for nullable callback invocation
  such as `callback?.invoke()`, which would otherwise be rewritten like a
  low-level method-handle call.
- The emulator's OpenGL compatibility path can differ visually from desktop
  rendering. In the 3D Platformer smoke, geometry and gameplay render, but the
  skybox/background is darker than the upstream desktop screenshot. Logcat also
  reports unsupported ASTC textures being converted at runtime.
- The current validated demo exports use Godot's OpenGL Compatibility renderer
  on Android. Desktop can continue using its normal renderer.
- Godot Android can also use Vulkan through the Mobile renderer; Kanama's
  Android preview has been validated through OpenGL Compatibility.
- Mobile polish is per demo: screen size, orientation, touch input, and UI
  scaling are separate from the core runtime path.
- Physical devices can expose first-use hitches when gameplay first instantiates
  scenes, decodes audio, starts particles, or builds generated meshes. Demo
  ports should warm up these resources during startup when Android smoke shows
  a hitch that is not visible on desktop.

The smoke script force-stops the launched package on exit so emulator runs do
not leave demo processes alive after validation.

## Validation Shape

Android validation uses the normal demo folders plus Android export metadata,
the Kanama Android plugin AAR, `adb install`, logcat checks, screenshots, and a
forced package stop after the run. Keep rc 2 support wording pending until
the matching APK smoke matrix passes; physical device validation remains a
stronger platform claim.

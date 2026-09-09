# Android Internals

This page records the current Android implementation path. Android is a
**Supported** Kanama target on the Godot 4.7 stable baseline; the API/build flow
is less settled than the desktop path.

## What Works

Nine Kanama demo exports are Android smoke targets (the eight starter/creeps
demos plus Bunnymark; `tps-demo-kanama` also carries an Android preset but sits
outside the automated matrix). On 2026-06-26, the Godot
4.7 stable demo matrix passed on Pixel 7 with debug APK exports, and
Starter-Kit-Match3 also passed the R8-minified release smoke with Kanama's
PanamaPort fork:

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

This proves the Kanama runtime path on Android, including the forked-PanamaPort
minified path. Android is Supported on 4.7 stable; release builds require
Android 13+ (debug down to Android 9), and the release path depends on Kanama's
PanamaPort fork.

## PanamaPort

The Android implementation uses a forked
[PanamaPort](https://github.com/vova7878/PanamaPort) artifact, published via
[JitPack](https://jitpack.io):
`com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`.

Upstream PanamaPort `v0.1.3` miscompiles under Godot 4.7's R8 because its
Android linker uses Java pattern switches over sealed storage/layout types. The
fork rewrites the affected switch sites to explicit `instanceof` chains and is
published to the configured local Maven repository for Android exports. Keep the
fork diff small and retire it when the upstream artifact carries the fix.

The root cause, in full. R8/ProGuard minification is validated only with the
fork. Root-caused on a Pixel 7 (2026-06-26), upstream PanamaPort `v0.1.3`
crashes in the FFI bootstrap at `nativeLinker().downcallHandle()` with
`AssertionError: Should not reach here`; the recurring
`No loader found for resource: res://kotlin-src/*.kt` and flickering splash
are downstream of that crash. Deobfuscated, PanamaPort's Android linker
(`_AndroidLinkerImpl`) builds native stubs with Java pattern-matching
`switch`es over sealed types (`_LLVMStorageDescriptor` storages and the
`MemoryLayout` hierarchy), and Godot 4.7's R8 (AGP 8.6.1) mis-optimizes those
switches so they fall through to `default -> Utils.shouldNotReachHere()`. This
is unfixable from consumer keep rules: keeping the sealed types blocks the
optimization PanamaPort's own `@CheckDiscard` rules require, failing the
build; not keeping them leaves the switch broken at runtime. The fork rewrites
the affected source switch sites to explicit `instanceof` branches and adds
targeted R8 annotations/signing mechanics.
`scripts/android_export_minified.sh` builds, installs, launches, and verifies
the minified Match3 release APK on Pixel 7 (Android 16) and Moto g 5G 2023
(Android 14).

A second R8 landmine was fixed in fork r8.4 (2026-07-13): AGP 8.6.1's R8
collapses a one-element `byte[]...` varargs call into `filled-new-array` of
`byte[][]`, which the ART interpreter below Android 13 segfaults on; the
fork's `NativeCodeBlob.makeCodeBlobSingle` removes the only such site. Fork
r8.3 added the `SDK_INT_FULL` bootstrap guard that every device below
Android 16 needs. Release builds below Android 13 remain blocked by a
separate release-mode PanamaPort constraint — the floors and their evidence
are in [Version Support → Validated Android versions](../../reference/version-support.md#validated-android-versions).

## Desktop vs Android FFM

Desktop Kanama uses JDK 25 and `java.lang.foreign`.

Android does not run a desktop JDK inside the game. The Android runtime uses
ART, and the Android build maps the Kanama FFM-facing code to
[PanamaPort](https://github.com/vova7878/PanamaPort)'s `com.v7878.foreign`
package.

The build tools are also separate from the runtime: Kanama itself builds on
the desktop JDK, Godot's Android Gradle export flow runs on its own JDK, and
the exported game runs on ART and PanamaPort, not on a desktop JVM. The
versions are in [Version Support → Requirements](../../reference/version-support.md#requirements)
and the [Android toolchain table](../../exporting/android.md#toolchain).

## Implementation Shape

The current Android implementation lives under `android/godot-plugin`, split
into two AAR modules (task 36; mirrors desktop's `kanama.jar` /
`kanama-scripts.jar` shape):

- **`:plugin` — runtime AAR** (project-agnostic, `KanamaAndroid.debug.aar`):
  a Godot Android plugin class, a small Java bootstrap that loads
  `libkanama_bootstrap.so`, the Android native bootstrap library, the remapped
  Kanama runtime + annotations sources, and a `.gdextension` file marked as an
  Android AAR plugin. Builds with no consumer project
  (`assembleAndroidPluginAar`), which is what `packageMobileAddonAndroid`
  ships.
- **`:scripts` — scripts AAR** (per project, `KanamaAndroidScripts.debug.aar`):
  the consumer project's `kotlin-src/` scripts and generated KSP registrars,
  remapped identically and compiled against the runtime classes
  (`compileOnly(project(":plugin"))`). The runtime `.gdap` written by
  `installAndroidPluginAar` references it as a `local=` dependency, so Godot's
  export adds both AARs and they dex into the same APK classloader — registrar
  lookup needs no loader-aware path.

Both modules copy sources into generated Android source trees through the same
PanamaPort compatibility remap, shared in `buildSrc` (`KanamaAndroidRemap`).
The remap is deliberately guarded per module: `auditAndroidKanamaSources`
(runtime) and `auditAndroidKanamaScriptSources` (scripts, plus the pre-remap
demo-source audit) fail the Android build if stale desktop-only APIs or
incorrectly rewritten Kotlin callback calls appear in the generated trees.

R8 note: the runtime AAR's `consumer-rules.pro` applies APK-wide, so its keeps
for `net.multigesture.kanama.generated.**` and `**.KanamaRegistry` cover the
scripts AAR's classes too — no second rules file is needed.

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
- Godot Android can also use Vulkan through the Mobile renderer; the nine-demo
  matrix passed under it on Pixel 7 (2026-07-10), while the demo corpus still
  ships OpenGL Compatibility as its default mobile renderer.
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
forced package stop after the run. Keep stable support wording pending until
the matching APK smoke matrix passes; physical device validation remains a
stronger platform claim.

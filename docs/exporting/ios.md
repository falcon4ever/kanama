# iOS Spike

Kanama's iOS path is an experimental branch spike. It is not a support claim
for the `0.2.2` preview line yet.

The current direction is:

- Godot GDExtension entry point in a small C shim,
- Kotlin/Native static library linked into the same iOS `.xcframework`,
- no desktop JVM, GraalVM, or TeaVM in the iOS app, and
- simulator-first validation before physical-device signing.

## Current Status

The spike builds debug and release iOS `.xcframework` artifacts with device
`arm64` and Apple Silicon simulator `arm64` slices. The Kotlin/Native runtime
currently proves the loader path, lifecycle callbacks, a main-loop frame
callback, one cached typed Godot call from Kotlin/Native, and a minimal
built-in `.kt` script-resource probe:

```text
Godot iOS export -> C shim -> Kotlin/Native runtime -> typed ptrcall
```

The script probe registers a `ScriptLanguageExtension`, `ScriptExtension`, and
`.kt` `ResourceFormatLoader`, attaches a test `.kt` resource to a `Label`, and
uses a Kotlin/Native `_ready` callback to update that owner. It does not yet
compile or run arbitrary Kanama project scripts on iOS.

## Build The iOS Artifacts

Use Xcode 26.5 or newer enough to provide the installed iOS SDK and simulator.
If `xcode-select` points at Command Line Tools, set `DEVELOPER_DIR` for the
Gradle process. Kotlin/Native's Apple linker checks this environment variable
directly.

```sh
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer \
./gradlew assembleIosKanamaXcframework \
  -PkanamaXcodeDeveloperDir=/Applications/Xcode.app/Contents/Developer
```

The task writes:

```text
build/ios/xcframework/debug/kanama_ios.debug.xcframework
build/ios/xcframework/release/kanama_ios.release.xcframework
```

## Check The Godot iOS Template

Before simulator work on Apple Silicon, verify that the installed Godot iOS
export template has an `arm64` simulator engine archive:

```sh
./gradlew verifyIosGodotTemplate \
  -PkanamaXcodeDeveloperDir=/Applications/Xcode.app/Contents/Developer
```

Or run the script directly:

```sh
scripts/ios_template_preflight.sh \
  --xcode-developer-dir /Applications/Xcode.app/Contents/Developer
```

## Install Into A Test Project

```sh
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer \
./gradlew installIosAddon \
  -PkanamaIosProjectDir=/absolute/path/to/godot_project \
  -PkanamaXcodeDeveloperDir=/Applications/Xcode.app/Contents/Developer
```

This installs the iOS descriptor entries:

```ini
[libraries]
ios.debug.arm64 = "res://addons/kanama/bin/ios/kanama_ios.debug.xcframework"
ios.release.arm64 = "res://addons/kanama/bin/ios/kanama_ios.release.xcframework"
```

## Boundaries

- Simulator startup is the first validation target.
- Device export requires signing identities and provisioning and is a later
  validation target.
- Hot reload is out of scope for the first iOS backend.
- KSP metadata, user project script compilation, Kotlin Multiplatform source
  layout, and real demo ports are still future work for the iOS backend.
- The production path must port the runtime through backend-neutral wrappers
  and prefer cached typed calls over Variant-heavy or allocation-heavy paths.

## Visual Simulator Smoke

The visual smoke script proves the native loader, Xcode simulator build, app
install, app launch, and a simple Godot render path. With `--kanama-probe`, it
also proves a Kotlin/Native main-loop frame callback can call back into Godot
through the C shim and update a normal `Label` via typed `ptrcall`.

With `--kanama-script-probe`, it attaches a `.kt` script resource to a normal
`Label` and proves that the iOS shim can create a script resource, create a
Godot script instance, enter Kotlin/Native from `_ready`, and call back into
Godot through a cached typed `ptrcall`.

These modes still do not prove general Kanama script execution, KSP-generated
registrars, project-specific Kotlin/Native compilation, or hot reload.

```sh
scripts/ios_visual_smoke.sh \
  --godot /Applications/Godot.app/Contents/MacOS/Godot
```

Run the Kotlin/Native frame probe with:

```sh
scripts/ios_visual_smoke.sh \
  --godot /Applications/Godot.app/Contents/MacOS/Godot \
  --kanama-probe
```

Run the attached `.kt` script-resource probe with:

```sh
scripts/ios_visual_smoke.sh \
  --godot /Applications/Godot.app/Contents/MacOS/Godot \
  --kanama-script-probe
```

If the installed Godot iOS template is missing `arm64` simulator support, build
a matching Godot simulator library and pass it explicitly:

```sh
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer \
scons platform=ios target=template_debug arch=arm64 simulator=yes precision=single

scripts/ios_visual_smoke.sh \
  --godot /Applications/Godot.app/Contents/MacOS/Godot \
  --godot-simulator-lib /absolute/path/to/libgodot.ios.template_debug.arm64.simulator.a \
  --kanama-script-probe
```

## Apple Silicon Simulator Notes

Apple Silicon iOS simulators build and run `arm64` simulator binaries. That is
separate from real-device `arm64`, which uses the `iphoneos` SDK instead of the
`iphonesimulator` SDK.

If Xcode fails while linking the exported project with many warnings about
`libgodot.a` objects being `x86_64` when `arm64` is required, the installed
Godot iOS simulator template is missing the arm64 simulator engine archive.
Kanama's spike artifacts already include an `ios-arm64-simulator` slice; the
matching Godot export template must provide one too.

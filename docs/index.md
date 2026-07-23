# Kanama Docs

<p align="center">
  <img src="assets/kanama-logo.png" alt="Kanama logo" width="180">
</p>

Kanama brings Kotlin scripting to Godot 4 through a GDExtension runtime powered
by the JVM and the Foreign Function & Memory API.

Kanama is desktop-first and currently targets the Godot 4.7 stable API.
If you need a more established Kotlin integration for Godot today, also
evaluate [Godot Kotlin/JVM](https://godot-kotl.in/en/stable/). Kanama is a
separate Panama/FFM GDExtension path.

## Start Here

New to Kanama? Start with the [Introduction](getting-started/index.md), then
read [The Editor Loop](getting-started/editor-workflow.md) to understand the
build button, build-on-save, scene reload, hot reload, and IntelliJ debugging
workflow.

Already comfortable with Godot? The [Writing Kotlin Scripts](game-dev/scripts.md)
and [Calling Godot APIs](game-dev/godot-api.md) pages cover the main Kanama
differences: `this` vs `self`, immutable value types, rebuilds, resource
ownership, signals, and wrapper coverage. For existing GDScript projects, use
[Porting GDScript](game-dev/porting-gdscript.md) with the
[Kotlin Style](game-dev/style-guide.md) guide.

## Platform Status

| Platform | Status |
| --- | --- |
| macOS | Supported (4.7 stable) |
| Linux x86_64 | Supported (4.7 stable) |
| Linux arm64 | Supported (4.7 stable) |
| Windows | Supported (4.7 stable) |
| Android | Supported (4.7 stable); debug ≥ Android 9, release ≥ Android 13 (Pixel 7 / Moto g 5G / S10+ / Pixel 3 XL) |
| iOS | Supported (4.7 stable, Kotlin/Native); device-validated iPhone 12 / 15 Pro |
| Web | In development (Kotlin/Wasm backend; no supported export workflow yet) |

See [Version Support](reference/version-support.md) for exact Godot, JDK,
smoke-test, and packaging boundaries.

## Contributors

Changing Kanama itself? Start with the
[Contributor Guide](contributing/index.md). Architecture, wrapper maintenance,
hot reload internals, demo porting rules, and Android internals live under the
Contributing section.

Kanama is distributed under the
[MIT license](https://github.com/falcon4ever/kanama/blob/main/LICENSE).

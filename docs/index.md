# Kanama Docs

<p align="center">
  <img src="assets/kanama-logo.png" alt="Kanama logo" width="180">
</p>

<p align="center">
  <img alt="Godot 4.7.2 stable" src="https://img.shields.io/badge/Godot-4.7.2_stable-478cbf.svg">
  <img alt="JDK 25+" src="https://img.shields.io/badge/JDK-25%2B-f89820.svg">
  <img alt="Android: supported" src="https://img.shields.io/badge/Android-supported-3ddc84.svg">
  <img alt="iOS: supported" src="https://img.shields.io/badge/iOS-supported-000000.svg">
  <img alt="Web: experimental" src="https://img.shields.io/badge/Web-experimental-f5a623.svg">
</p>

Kanama brings Kotlin scripting to Godot 4 through a GDExtension runtime powered
by the JVM and the Foreign Function & Memory API.

Kanama is desktop-first, with Android, iOS, and Web backends. If you need a
more established Kotlin integration for Godot today, also evaluate
[Godot Kotlin/JVM](https://godot-kotl.in/en/stable/). Kanama is a separate
Panama/FFM GDExtension path.

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

The badges above are the current tiers. Which Godot release and JDK Kanama
requires, what each platform was validated on, and the caveats each tier
carries are recorded once, in [Version Support](reference/version-support.md).

## Contributors

Changing Kanama itself? Start with the
[Contributor Guide](contributing/index.md). Architecture, wrapper maintenance,
hot reload internals, demo porting rules, and the per-backend internals
(Android, iOS, Web) live under the Contributing section.

Kanama is distributed under the
[MIT license](https://github.com/falcon4ever/kanama/blob/main/LICENSE).

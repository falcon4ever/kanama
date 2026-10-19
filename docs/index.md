# Kanama

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

Kanama lets you write Godot 4 scripts in Kotlin. A `.kt` file attaches to a
node like a `.gd` file does, and every Godot class, method, property and signal
has a Kotlin twin with Godot's own documentation, so IntelliJ IDEA completes
the whole engine API. You keep the Godot editor and your scenes; you gain
Kotlin, Gradle, coroutines and the Java libraries. Your game runs on desktop,
Android and iOS, and experimentally in the browser.

```kotlin
@ScriptClass(attachTo = "CharacterBody2D")
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody2D>(godotObject, ::CharacterBody2D) {

    @Export
    var speed: Double = 300.0

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) {
        val input = Input.getVector("ui_left", "ui_right", "ui_up", "ui_down")
        self.velocity = input * speed
        self.moveAndSlide()
    }
}
```

## Where to Start

- **New to Kanama?** [Get Started](getting-started/index.md) takes you from
  nothing installed to a Kotlin script running in a scene.
- **Ready to write game code?** [Writing Scripts](game-dev/scripts.md) and
  [Calling Godot APIs](game-dev/godot-api.md) cover what differs from
  GDScript: `self`, immutable vectors, rebuilding after an edit, and releasing
  resources. Porting an existing game? Read
  [Porting from GDScript](game-dev/porting-gdscript.md).
- **Changing Kanama itself?** [Develop Kanama](contributing/index.md) has the
  architecture, the generator, the backends and the checks.

## Platform Status

The badges above are the current status of each platform.
[Requirements and Platform Status](reference/version-support.md) lists what
each workflow needs and what each platform has been tested on.

Kanama is a preview, and not the only way to use Kotlin with Godot.
[Godot Kotlin/JVM](https://godot-kotl.in/en/stable/) is a separate, more
established project with a different runtime and export model. If you need a
proven integration today, evaluate it too.

Kanama is distributed under the
[MIT license](https://github.com/falcon4ever/kanama/blob/main/LICENSE).

<p align="center">
  <img src="assets/kanama-logo.png" alt="Kanama logo" width="220">
</p>

<h1 align="center">Kanama</h1>

<p align="center">
  Kotlin scripting for Godot 4, through a GDExtension runtime on the JVM's
  Foreign Function & Memory API.
</p>

<p align="center">
  <a href="LICENSE"><img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-blue.svg"></a>
  <img alt="Godot 4.7.2 stable" src="https://img.shields.io/badge/Godot-4.7.2_stable-478cbf.svg">
  <img alt="JDK 25+" src="https://img.shields.io/badge/JDK-25%2B-f89820.svg">
  <img alt="Android: supported" src="https://img.shields.io/badge/Android-supported-3ddc84.svg">
  <img alt="iOS: supported" src="https://img.shields.io/badge/iOS-supported-000000.svg">
  <img alt="Web: experimental" src="https://img.shields.io/badge/Web-experimental-f5a623.svg">
  <img alt="Status: 0.4.0 preview" src="https://img.shields.io/badge/status-0.4.0_preview-478cbf.svg">
</p>

Kanama lets you write Godot scripts in Kotlin. A `.kt` file attaches to a node
like a `.gd` file does, and you keep the Godot editor, scenes and inspector you
already use.

- **Every Godot 4.7 class has a Kotlin class** with the same methods,
  properties and signals, and Godot's own documentation in your IDE.
- **No engine fork and no engine module.** Kanama is an addon in your project.
- **Desktop, Android and iOS are supported**, Web is experimental. Desktop
  runs your scripts on the JDK and Android on ART; iOS and Web compile them
  ahead of time, with no JVM on the device.
- **A short edit loop:** save a `.kt` file and the Godot editor builds it and
  picks up your Kotlin; a status light in the toolbar shows when it is done. IntelliJ IDEA's debugger attaches to a running game.
- **Exported desktop games carry their own Java runtime.** Players install
  nothing, and one machine builds for Windows, Linux and macOS.

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

## Getting Started in Five Minutes

You need [Godot 4.7.2](https://godotengine.org/download/archive/4.7.2-stable/)
and a JDK 25 or newer. Download a desktop kit for your machine from the
[releases page](https://github.com/falcon4ever/kanama/releases), unzip it, and
build its Kotlin script:

```sh
cd /path/to/kanama-starter
./gradlew buildScripts
```

Open `project.godot` in Godot and press **Play**: a label shows text written by
Kotlin, and the scene turns.

The kits published so far are for Kanama 0.4. Until 0.5.0 is released, build
the starter from this repository instead. That also needs CMake and a C
compiler:

```sh
git clone https://github.com/falcon4ever/kanama
cd kanama
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/path/to/kanama-starter
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/kanama-starter \
  -PkanamaProjectScriptsDir=/path/to/kanama-starter/kotlin-src
```

Then open `/path/to/kanama-starter/project.godot` and press **Play**.
[Get Started](docs/getting-started/index.md) walks through both paths, your
first edit, and what to do when something goes wrong.

## Documentation

The documentation is published at
[falcon4ever.github.io/kanama](https://falcon4ever.github.io/kanama/).

- [Get Started](docs/getting-started/index.md), and
  [Upgrading from 0.4](docs/getting-started/upgrading-0.4-to-0.5.md) if you have 0.4 scripts.
- [Writing Scripts](docs/game-dev/scripts.md) and
  [Calling Godot APIs](docs/game-dev/godot-api.md).
- Shipping your game: [Desktop](docs/exporting/desktop.md),
  [Android](docs/exporting/android.md), [iOS](docs/exporting/ios.md),
  [Web](docs/exporting/web.md).
- [Requirements and Platform Status](docs/reference/version-support.md).
- [Changelog](CHANGELOG.md).

Kanama is a preview. [Godot Kotlin/JVM](https://godot-kotl.in/en/stable/) is a
separate, more established Kotlin integration with a different runtime and
export model; if you need one today, evaluate it too.

## Demos

[falcon4ever/kanama-demos](https://github.com/falcon4ever/kanama-demos) has
starter kits, official Godot demos and GDQuest 3D controller demos ported to
Kanama. Clone it beside this checkout (`kanama/` and `kanama-demos/` in one
folder) to build them against your Kanama changes.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) and [Develop Kanama](docs/contributing/index.md).

## License

MIT. See [LICENSE](LICENSE).

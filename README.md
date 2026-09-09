<p align="center">
  <img src="assets/kanama-logo.png" alt="Kanama logo" width="220">
</p>

<h1 align="center">Kanama</h1>

<p align="center">
  Kotlin for Godot through a GDExtension runtime powered by the JVM and the
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

Kanama lets Kotlin scripts attach to Godot nodes through a GDExtension runtime.
In the Godot editor, Kanama `.kt` files appear as script resources and can be
attached directly to nodes like `.gd` scripts. Kanama aims to preserve the
Godot workflow while giving game code access to Kotlin, Gradle, coroutines, and
the JVM ecosystem.

## Related Projects

Kanama is a preview-stage (`0.4.0`) project using a Panama/FFM-based GDExtension
architecture. If you want a more established Kotlin integration for Godot today, also
evaluate [Godot Kotlin/JVM](https://godot-kotl.in/en/stable/). It is a
separate project with a different runtime and export model.

## Status

Kanama is desktop-first. The badges above are the current platform tiers;
[Version Support](docs/reference/version-support.md) is the one page that
records what each tier was validated on, when, and with which caveats, and
it is the only page that states requirements. Exported desktop games are
unpack-and-play: they ship a bundled, jlink-trimmed JVM runtime, and that
runtime is cross-target, so one host can produce Windows, Linux, and macOS
builds — see [Desktop and Packaging](docs/exporting/desktop.md).

## Highlights

- Kotlin scripts attach to Godot nodes like GDScript
- No engine fork, no engine module, no JNI glue in game code
- Desktop runtime powered by the JDK Foreign Function & Memory API
- Android runtime through Godot's Android plugin AAR flow (ART + PanamaPort)
- iOS runtime through a Kotlin/Native `.xcframework`, no on-device JVM
- Kotlin/Wasm Web backend through a generated proxy and a versioned JavaScript
  bridge, no on-device JVM
- Hot reload and editor build tools for a fast iteration loop
- Full Godot 4.7 class coverage (1036/1036 wrapped classes, generated KDoc from
  Godot docs; engine virtuals overridable via `@OverrideVirtual`)

## Requirements

The Godot pin and JDK are the badges above. The full list — per workflow, and
per export platform — lives once, in
[Version Support → Requirements](docs/reference/version-support.md#requirements).

## Quick Start

Use a source checkout for the current public onboarding path:

```sh
git clone https://github.com/falcon4ever/kanama
cd kanama
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/path/to/kanama-starter
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/kanama-starter \
  -PkanamaProjectScriptsDir=/path/to/kanama-starter
```

Open `kanama-starter/project.godot` in Godot and press **Play**. After editing
`kotlin-src/HelloScript.kt`, press **Build Scripts** in Godot or rerun
`./gradlew buildScripts`.

Package tasks can also build local desktop kit and store-addon zips for smoke
testing:

```sh
./gradlew packageDistributions
```

If a matching GitHub zip release exists, a release kit can be used for a new
project:

```sh
unzip kanama-desktop-kit-v<version>-<platform>.zip -d kanama-starter
cd kanama-starter
./gradlew buildScripts
```

For an existing Godot project and a locally built or published store-addon zip,
unzip it at the project root, then initialize the project:

```sh
sh addons/kanama/setup-kanama-project.sh
./gradlew buildScripts
```

The release-kit and store-addon pages describe those generated zip shapes; they
become download flows once matching release artifacts are published.

## Example

```kotlin
package com.example.game

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import java.lang.foreign.MemorySegment

@ScriptClass(attachTo = "Node")
class HelloKanama(godotObject: MemorySegment) :
    KanamaScript<Node>(godotObject, ::Node) {
    @OnReady
    fun ready() {
        GD.print("Hello from Kotlin")
    }
}
```

## Documentation

The latest public documentation is published at
[falcon4ever.github.io/kanama](https://falcon4ever.github.io/kanama/).

- [Getting Started](docs/getting-started/index.md)
- [Use a Release Kit](docs/getting-started/release-kit.md)
- [Use a Store Addon](docs/getting-started/store-addon.md)
- [Use a Source Checkout](docs/getting-started/source-checkout.md)
- [Work on Kanama](docs/getting-started/work-on-kanama.md)
- [The Editor Loop](docs/getting-started/editor-workflow.md)
- [Writing Kotlin Scripts](docs/game-dev/scripts.md)
- [Calling Godot APIs](docs/game-dev/godot-api.md)
- [Exports and Resources](docs/game-dev/properties-resources.md)
- [Signals and Callbacks](docs/game-dev/signals.md)
- [Porting GDScript](docs/game-dev/porting-gdscript.md)
- [Kotlin Style](docs/game-dev/style-guide.md)
- [Desktop and Packaging](docs/exporting/desktop.md)
- [Android](docs/exporting/android.md)
- [iOS](docs/exporting/ios.md)
- [Version Support](docs/reference/version-support.md)
- [API Coverage](docs/reference/generated/api-coverage.md)
- [C# Comparison](docs/reference/c-sharp-compat.md)
- [Changelog](CHANGELOG.md)
- [Contributor Guide](docs/contributing/index.md)

To preview documentation changes locally:

```sh
pip install -r docs/requirements.txt
mkdocs serve
```

## Demos

The companion demo repository is
[falcon4ever/kanama-demos](https://github.com/falcon4ever/kanama-demos). Keep
it beside this checkout:

```text
dev/
  kanama/
  kanama-demos/
```

Current demo ports cover starter kits, official Godot demos, and GDQuest 3D
controller demos. The demo repo is also where new wrappers are validated
against real gameplay before release.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## License

MIT. See [LICENSE](LICENSE).

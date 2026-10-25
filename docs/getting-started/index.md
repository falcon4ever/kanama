# Getting Started with Kanama

Kanama lets you write Godot scripts in Kotlin. A `.kt` file attaches to a node
like a `.gd` file does, and you keep the Godot editor, scenes and inspector you
already use. This page takes you from nothing installed to a Kotlin script
running in a scene, in about ten minutes.

## Requirements

- **Godot 4.7.2** from the
  [4.7.2 stable archive](https://godotengine.org/download/archive/4.7.2-stable/).
  Kanama is built and tested against this release.
- **A JDK 25 or newer**, for example [Temurin 25](https://adoptium.net/).
  Kanama runs your scripts on it and builds them with it.
- **A desktop computer** running macOS (Apple silicon), Windows (x64) or
  Linux (x64 or ARM64).
- **IntelliJ IDEA** (the free Community edition is enough). Optional, but it
  is where you get completion and documentation for the Godot API.

Exporting to Android, iOS or the Web needs more tools, which each platform's
page lists. [Requirements and Platform Status](../reference/version-support.md)
has the full list and says which platforms are supported.

Then pick one of the two ways to get Kanama. The kit is the short one. If no
kit has been published for the version you want, or you want to follow `main`,
use the source checkout.

!!! note "Until Kanama 0.5.0 is released"
    The kits published so far are for Kanama 0.4, whose scripts look different
    from the ones in these docs. Until the 0.5.0 kits are out, use
    [Option 2: Build Kanama from Source](#option-2-build-kanama-from-source).

## Option 1: Start from the Desktop Release Kit

A desktop kit is a complete Godot project with Kanama inside it.

1. Download `kanama-desktop-kit-v<version>-<platform>.zip` for your machine
   from the [releases page](https://github.com/falcon4ever/kanama/releases)
   and unzip it into a new folder. `project.godot`, `build.gradle.kts` and
   `addons/` are at the top level.
2. On macOS, clear the download quarantine once, or Godot refuses to load the
   addon:

    ```sh
    xattr -dr com.apple.quarantine /path/to/kanama-starter
    ```

3. Build the Kotlin script that ships with the kit:

    ```sh
    cd /path/to/kanama-starter
    ./gradlew buildScripts
    ```

    On Windows, run `gradlew.bat buildScripts`. The first build downloads
    Gradle and the Kotlin compiler, so it takes a minute or two; later builds
    take a few seconds.

4. Open `project.godot` in Godot and press **Play**.

You should see a label that says *Hello from Kanama* and *Kotlin script is
running for 1.2s*, and the whole scene slowly rotating. That text comes from
Kotlin. Skip to [Making Your First Change](#making-your-first-change).

## Option 2: Build Kanama from Source

This path also needs **CMake 3.22 or newer** and a C compiler (the Xcode
command line tools, `build-essential`, or Visual Studio with the C++
workload), because it builds Kanama's small native loader on your machine.

1. Clone Kanama and create a starter project next to it:

    ```sh
    git clone https://github.com/falcon4ever/kanama
    cd kanama
    ./gradlew createStarterProject \
      -PkanamaStarterProjectDir=/path/to/kanama-starter
    ```

2. Build Kanama and install it into the starter:

    ```sh
    ./gradlew installAddonJar \
      -PkanamaProjectDir=/path/to/kanama-starter \
      -PkanamaProjectScriptsDir=/path/to/kanama-starter/kotlin-src
    ```

    This takes a few minutes the first time. It compiles the runtime, the
    native loader and the starter script, and copies everything into
    `kanama-starter/addons/kanama`.

3. Open `/path/to/kanama-starter/project.godot` in Godot and press **Play**.

The same label and the same slow rotation appear. When you later update the
checkout, run the `installAddonJar` command again so the starter picks up the
new runtime.

## How Your First Script Works

The scene is one `Node2D` with a `Label` child. The node has
`kotlin-src/HelloScript.kt` attached, the way a `.gd` script would be. The
script, shortened:

```kotlin
package com.example.game

@ScriptClass(attachTo = "Node2D")
class HelloScript(godotObject: GodotHandle) :
    KanamaScript<Node2D>(godotObject, ::Node2D) {

    @Export
    var greeting: String = "Hello from Kanama"

    private var elapsedSeconds = 0.0
    private var statusLabel: Label? = null

    @OnReady
    fun ready() {
        statusLabel = self.getNodeAsOrNull("Status", "Label", ::Label)
        GD.print("[kanama:starter] $greeting")
    }

    @OnProcess
    fun process(delta: Double) {
        elapsedSeconds += delta
        self.rotation = elapsedSeconds * 0.6
        statusLabel?.text = "$greeting\nKotlin script is running."
    }
}
```

Five things to notice, because every Kanama script has them:

- `@ScriptClass(attachTo = "Node2D")` says which Godot class the script
  attaches to, like `extends Node2D` in GDScript.
- The class extends `KanamaScript<Node2D>`. Inside, `self` is the node:
  `self.rotation` is the node's `rotation` property. Kotlin's `this` is the
  script object, not the node.
- `@Export var greeting` shows up in the inspector, like `@export var greeting`
  in GDScript.
- `@OnReady` and `@OnProcess` mark the functions Godot calls for `_ready` and
  `_process`. The function names are yours to choose.
- `GD.print` writes to Godot's output panel, like `print()` in GDScript.

The full file also has `@Tool`, so the script runs inside the editor too and
the scene turns there as well, and `@GlobalClass`, which is GDScript's
`class_name`.

## Making Your First Change

1. Open the project folder in IntelliJ IDEA as a Gradle project (**File >
   Open**, pick the folder, trust the project). Completion for every Godot
   class now works. [Open in IntelliJ IDEA](intellij.md) has the details and
   sets up the debugger.
2. In `kotlin-src/HelloScript.kt`, change `"Hello from Kanama"` to anything
   else and save.
3. Back in Godot, watch the dot at the left of the Kanama row in the toolbar.
   Saving the file started a build: the dot is yellow while it runs and turns
   green when your scripts are built. If it turns red, click it to see the
   compiler's error. **Build Scripts** beside it runs the same build, which is
   `./gradlew buildScripts` in a terminal.
4. Press **Play**. The label shows your text.

That is the loop: edit in IntelliJ, save, play in Godot. GDScript changes are
live; Kotlin changes need a build, and build on save starts it for you. It
takes a few seconds. To build only when you press the button, turn off
**Editor > Editor Settings > Kanama > Build > Build On Save**. [Edit, Build, Run](editor-workflow.md)
covers the rest of the loop: scene reload after a build, and attaching the
IntelliJ debugger to a running game.

## Next Steps

- [Writing Scripts](../game-dev/scripts.md): lifecycle callbacks, node lookup,
  `await`, and the differences from GDScript.
- [Calling Godot APIs](../game-dev/godot-api.md): how a Godot name becomes a
  Kotlin name, vectors, enums, resources.
- [Exports and Resources](../game-dev/properties-resources.md) and
  [Signals and Await](../game-dev/signals.md).
- Adding Kanama to a project you already have:
  [Store Addon](store-addon.md).
- Shipping: [Desktop](../exporting/desktop.md), [Android](../exporting/android.md),
  [iOS](../exporting/ios.md), [Web](../exporting/web.md).
- Coming from Kanama 0.4: [Upgrading from 0.4](upgrading-0.4-to-0.5.md).

## Troubleshooting

| Godot says | Do this |
| --- | --- |
| It cannot find `libjvm`, or the Kanama Tools plugin warns about Java. | Install a JDK 25 or newer. Kanama looks in the usual install folders; if yours is elsewhere, set **Editor Settings > Kanama > Build > JDK Path** (`kanama/build/jdk_path`) to the JDK folder, or set `JAVA_HOME`, then restart Godot. [JDK Selection](editor-workflow.md#jdk-selection) lists the folders. |
| `"libkanama_bootstrap.dylib" Not Opened` (macOS). | The zip was quarantined by Gatekeeper. Run `xattr -dr com.apple.quarantine /path/to/project` and open the project again. |
| The game runs, but your edit does not show. | The build did not run. Press **Build Scripts** or run `./gradlew buildScripts`, then Play again. |
| `res://kotlin-src/HelloScript.kt` is missing, or the node has no script. | The addon is not registered. Check that `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen the project. On the source checkout path, run `installAddonJar` again. |
| Gradle cannot resolve `net.multigesture.kanama`. | The project resolves Kanama from `addons/kanama/maven`. Make sure that folder exists; on the source checkout path, `installAddonJar` creates it. |

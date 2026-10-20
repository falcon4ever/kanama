# Install From a Release Kit

A release kit is a complete Godot project with Kanama inside it: the runtime,
the Kanama Tools editor plugin, a Gradle build and a starter Kotlin script.
Start a new game from it. To add Kanama to a project you already have, use the
[store addon](store-addon.md) instead.

## Requirements

You need Godot 4.7.2 and a JDK 25 or newer, as listed in
[Get Started](index.md#requirements). The kit brings everything else. The
first build downloads Gradle and the Kotlin compiler, so it needs a network
connection.

!!! note "Until Kanama 0.5.0 is released"
    The kits published so far are for Kanama 0.4, whose scripts look different
    from the ones in these docs. Until the 0.5.0 kits are out, use
    [a source checkout](source-checkout.md).

## Installation

1. Download the kit for your machine from the
   [releases page](https://github.com/falcon4ever/kanama/releases):

    | Platform | File |
    | --- | --- |
    | macOS (Apple silicon) | `kanama-desktop-kit-v<version>-macos-arm64.zip` |
    | Linux x64 | `kanama-desktop-kit-v<version>-linux-x64.zip` |
    | Linux ARM64 | `kanama-desktop-kit-v<version>-linux-arm64.zip` |
    | Windows x64 | `kanama-desktop-kit-v<version>-windows-x64.zip` |

2. Unzip it into a new, empty folder. `project.godot`, `build.gradle.kts` and
   `addons/` end up at the top level of that folder.
3. On macOS, clear the download quarantine once, or Godot refuses to load the
   addon:

    ```sh
    xattr -dr com.apple.quarantine /path/to/kanama-starter
    ```

4. Build the Kotlin script that ships with the kit:

    ```sh
    cd /path/to/kanama-starter
    ./gradlew buildScripts
    ```

    On Windows, run `.\gradlew.bat buildScripts`. The build compiles every
    `.kt` file in `kotlin-src/` into `addons/kanama/kanama-scripts.jar`.

5. Open `project.godot` in Godot and press **Play**.

A label shows *Hello from Kanama*, and the scene slowly rotates. Both come from
`kotlin-src/HelloScript.kt`. [Get Started](index.md#how-your-first-script-works)
walks through that script and your first change to it.

Do not unzip a kit into a project you already have. It carries its own
`project.godot`, `build.gradle.kts` and `gradlew`, and would replace yours.

## What Is in the Kit

| Path | What it is |
| --- | --- |
| `project.godot`, `main.tscn` | The starter scene: a `Node2D` with `HelloScript.kt` attached and a `Status` label. |
| `kotlin-src/` | Your Kotlin scripts. `buildScripts` compiles every `.kt` file here. |
| `build.gradle.kts`, `settings.gradle.kts`, `gradlew` | The Gradle build. IntelliJ IDEA opens the folder as a Gradle project; see [Open in IntelliJ IDEA](intellij.md). |
| `addons/kanama/` | The runtime Godot loads, and in `maven/` the Kanama libraries your build compiles against. |
| `addons/kanama_tools/` | The Kanama Tools editor plugin, already enabled. It adds **Build Scripts** and **Open Kotlin** to the editor toolbar. |

[Edit, Build, Run](editor-workflow.md) covers the plugin: build on save, scene
reload after a build, and the debugger.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Godot cannot find `libjvm`. | Install a JDK 25 or newer. Kanama looks in the usual install folders; if yours is elsewhere, set `JAVA_HOME`, or the editor setting **Kanama > Build > JDK Path**, to the JDK folder and restart Godot. See [Which JDK Kanama Uses](editor-workflow.md#which-jdk-kanama-uses). |
| macOS reports `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper quarantined the zip. Run `xattr -dr com.apple.quarantine /path/to/project` and open the project again. |
| The game still runs your old Kotlin code. | Press **Build Scripts** or run `./gradlew buildScripts`, then press **Play** again. |
| Gradle cannot resolve the Kanama libraries. | The build reads them from `addons/kanama/maven`. Check that the folder exists; unzip the kit again if it does not. |
| Godot reports a missing `res://kotlin-src/HelloScript.kt`. | Check that `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen the project. |

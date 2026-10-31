# Add Kanama to an Existing Project

!!! note "Until Kanama 0.5.0 is released"
    The addons published so far are for Kanama 0.4, whose scripts look
    different from the ones in these docs. Until the 0.5.0 addon is out,
    [build the addon from source](#building-the-addon-from-source) and follow
    the same steps with it.

The store addon adds Kanama to a Godot project you already have. The zip only
writes under `addons/`. One menu item in the editor then adds a Gradle build
and a starter Kotlin script, and keeps any file that is already there.

## Requirements

You need Godot 4.7.2 and a JDK 25 or newer, as listed in
[Get Started](index.md#requirements). The addon carries the Kanama runtime for
every desktop platform and the Gradle wrapper. The first build downloads
Gradle and the Kotlin compiler, so it needs a network connection.

## Installation

1. Download `kanama-store-addon-v<version>.zip` from the
   [releases page](https://github.com/falcon4ever/kanama/releases) and unzip
   it at the root of your Godot project, next to `project.godot`. On macOS,
   also clear the download quarantine; see
   [On Other Platforms](#on-other-platforms). The zip adds:
    - `addons/kanama`: the runtime Godot loads, with the Kanama libraries your
      build compiles against in `addons/kanama/maven`;
    - `addons/kanama_tools`: the Kanama Tools editor plugin;
    - `addons/kanama/templates/release-kit`: the Gradle build and the Gradle
      wrapper (`gradlew`, `gradlew.bat`, `gradle/`) that the setup copies.
2. Open the project in Godot, or reopen it if it was open. If you have set
   the JDK Path editor setting, Godot asks you to restart the editor the first
   time it opens the project; restart it once.
3. In **Project > Project Settings > Plugins**, enable **Kanama Tools**. It
   adds the Kanama row to the editor toolbar.
4. Choose **Project > Tools > Kanama Tools: Set Up Kanama Project**. It adds
   what the project is missing and lists it:
    - `build.gradle.kts`, `settings.gradle.kts` and the Gradle wrapper
      (`gradlew`, `gradlew.bat`, `gradle/`);
    - `kotlin-src/HelloScript.kt`, when `kotlin-src/` has no Kotlin script;
    - `build/.gdignore`, so Godot skips Gradle's build folder.

    It never replaces a file that is already there; a `settings.gradle.kts`
    of your own gets the Kanama block added at the top (see
    [Projects That Already Have a Gradle Build](#projects-that-already-have-a-gradle-build)).
    Choosing it again adds nothing.

5. Press **Build Scripts** in the toolbar. The build compiles every `.kt` file
   in `kotlin-src/` into `addons/kanama/kanama-scripts.jar`, and the status
   light beside the button turns green. The first build downloads Gradle and
   the Kotlin compiler, so it takes a minute.
6. Select a `Node2D` in a scene. In the inspector, load
   `res://kotlin-src/HelloScript.kt` into its **Script** property, the same way
   you attach a `.gd` file. Press **Run Current Scene** (F6, or Cmd+R on
   macOS): the node rotates, and the Output panel shows
   `[kanama:starter] Hello from Kanama`.

From here the loop is the same as in any Kanama project: edit in IntelliJ IDEA,
save, and press **Play**; build on save rebuilds your scripts. [Open in IntelliJ IDEA](intellij.md)
and [Edit, Build, Run](editor-workflow.md) cover it.

## Setting Up from a Terminal

The menu item runs a setup script that comes with the addon. You can run it
yourself instead, for example on a machine without the editor:

```sh
cd /path/to/your-project
sh addons/kanama/setup-kanama-project.sh
./gradlew buildScripts
```

It adds the same files, prints `wrote` for each one it adds and
`keeping existing` for each one it skips, and registers Kanama in
`.godot/extension_list.cfg`.

## Projects That Already Have a Gradle Build

If your project already has a `settings.gradle.kts`, the setup keeps your
lines and adds the template's `pluginManagement { }` block, which resolves the
Kanama Gradle plugin from the addon. The block goes at the top, after any
`import` and `@file:` lines. If your file has a `pluginManagement { }` block
of its own, or your project has a Groovy `settings.gradle`, the setup changes
nothing and says so: merge the template's block into yours as step 1 below
shows, then run it again.

If your project already has a `build.gradle.kts`, the setup keeps it.
It still adds the Gradle wrapper if the project has none, so
`./gradlew buildScripts` works once your build has the Kanama parts:

1. Merge the `pluginManagement { }` block of
   `addons/kanama/templates/release-kit/settings.gradle.kts` into your
   `settings.gradle.kts`: add its lines to your own `pluginManagement { }`
   block, or add the whole block at the top of the file if it has none. It
   reads the Kanama version from `addons/kanama/kanama.version` and finds the
   Kanama Gradle plugin in `addons/kanama/maven`, so a newer addon updates
   both.
2. Merge the `plugins { }` block of
   `addons/kanama/templates/release-kit/build.gradle.kts` into your build. It
   applies Kotlin, KSP (the Kotlin compiler plugin that generates the code
   that registers your scripts with Godot) and the Kanama Gradle plugin, which
   adds the [Android](../exporting/android.md) and [iOS](../exporting/ios.md)
   export commands.
3. Add the rest:

    ```kotlin
    kotlin {
        jvmToolchain(25)
        sourceSets.named("main") {
            kotlin.srcDir("kotlin-src")
            kotlin.srcDir(layout.buildDirectory.dir("generated/ksp/main/kotlin"))
        }
    }

    apply(from = "addons/kanama/kanama-project.gradle.kts")
    ```

The last line does the rest: `addons/kanama/kanama-project.gradle.kts` adds
the `addons/kanama/maven` repository, the Kanama dependencies and the
`buildScripts` task. If your build has no `settings.gradle.kts`, the setup
has copied the template's; it lets Gradle download a JDK 25 when only
another JDK is installed.

To replace the root Gradle files with the template instead, run the setup
script with `--force`. It overwrites `build.gradle.kts`, `settings.gradle.kts`,
the Gradle wrapper and `kotlin-src/HelloScript.kt`, so only use it when nothing
in those files is yours.

## Building the Addon from Source

Until the 0.5.0 addon is published, build it from a checkout of Kanama. This
needs CMake and a C compiler as well; see the
[source checkout requirements](source-checkout.md#requirements).

```sh
git clone https://github.com/falcon4ever/kanama
cd kanama
./gradlew packageStoreAddon
```

The zip is written to `build/distributions/kanama-store-addon-v<version>.zip`.
Use it in step 1 of [Installation](#installation). It holds the runtime for
your own machine's platform only.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Godot cannot find `libjvm`. | Install a JDK 25 or newer. Kanama looks in the usual install folders; if yours is elsewhere, set **Editor Settings > Kanama > Build > JDK Path** (`kanama/build/jdk_path`) to the JDK folder, or set `JAVA_HOME`, and restart Godot. See [JDK Selection](editor-workflow.md#jdk-selection). |
| macOS reports `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper quarantined the zip. Clear the quarantine as shown in [On Other Platforms](#on-other-platforms) and open the project again. |
| Gradle cannot resolve the Kanama libraries. | Check that `addons/kanama/maven` exists and that `build.gradle.kts` ends with `apply(from = "addons/kanama/kanama-project.gradle.kts")`. |
| Gradle stops with `Kanama addon not found` or `Kanama addon is incomplete`. | Part of `addons/kanama` is missing. Unzip the store addon at the project root again. |
| Godot says `your scripts were built for Kanama` one version `but the addon is` another. | You unzipped a newer addon. Press **Sync Addon** in that message, or **Build Scripts**, to rebuild your scripts for it. |
| Godot does not recognize `.kt` scripts. | Check that `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen the project. |
| The game still runs your old Kotlin code. | Look at the status light in the toolbar. Yellow: press **Build Scripts** or run `./gradlew buildScripts`, then run the scene again. Red: the build failed; click the light to see why. |

## On Other Platforms

On **macOS**, clear the download quarantine once after unzipping, before you
open the project. Otherwise Godot refuses to load the addon:

```sh
xattr -dr com.apple.quarantine /path/to/your-project
```

On **Windows**, run the PowerShell setup script,
`.\addons\kanama\setup-kanama-project.ps1` (with `-Force` in place of
`--force`), and `.\gradlew.bat` wherever this page says `./gradlew`.

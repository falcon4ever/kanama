# Install from a Source Checkout

You can build Kanama from its repository and install it into a Godot project.
Use this path to follow `main`, to try a change that is not released yet, or
when no kit has been published for the version you want.

## Requirements

- **Godot 4.7.2** and **a JDK 25 or newer**, as listed in
  [Get Started](index.md#requirements). Any JDK 25 or newer runs the build. If
  you have no JDK 25 itself, the build downloads one once into
  `~/.gradle/jdks`, which needs a network connection.
- **Git**, to clone the repository.
- **CMake 3.22 or newer and a C compiler**: the Xcode command line tools,
  `build-essential`, or Visual Studio with the C++ workload. The build
  compiles Kanama's small native loader on your machine.

You do not need the Godot source code. The Godot headers Kanama builds against
are in the repository.

## Installation

### Cloning Kanama

```sh
git clone https://github.com/falcon4ever/kanama /path/to/kanama
cd /path/to/kanama
```

### Creating a Starter Project

```sh
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/path/to/your-project
```

The starter has the same layout as a [release kit](release-kit.md#kit-contents):
a Godot project with `kotlin-src/HelloScript.kt` attached to its main scene,
the Kanama Tools plugin enabled, and its own Gradle build and wrapper. Its
`build.gradle.kts` is short: it applies Kotlin and KSP, the Kotlin compiler
plugin that generates the code that registers your scripts with Godot, and
names `kotlin-src` as the source folder. The Kanama libraries and the
`buildScripts` task come from `addons/kanama/kanama-project.gradle.kts`.

The task stops if the folder already has a `project.godot`. For a project you
already have, see
[Adding Kanama to an Existing Project](#adding-kanama-to-an-existing-project).

### Building and Installing Kanama

```sh
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/your-project \
  -PkanamaProjectScriptsDir=/path/to/your-project/kotlin-src
```

This takes a few minutes the first time. It compiles the Kanama runtime, the
native loader and your scripts, copies them into the project's `addons/kanama`,
and registers Kanama in `.godot/extension_list.cfg`. It also refreshes
`addons/kanama/maven` and `addons/kanama/kanama-project.gradle.kts`, so the
project's own build, and IntelliJ IDEA, use the same Kanama API your game runs
on.

Run the command again whenever you update the checkout.

`kanamaProjectScriptsDir` is the folder whose `.kt` files are compiled. If your
scripts live in several folders, pass them all with
`-PkanamaProjectScriptsDirs=`, separated by commas or by your platform's path
separator.

### Running the Starter

1. Open `/path/to/your-project/project.godot` in Godot. If you have set the
   JDK Path editor setting, Godot asks you to restart the editor the first
   time it opens the project; restart it once.
2. Press **Play**. A label shows *Hello from Kanama*, and the scene slowly
   rotates.

From now on, build your scripts in the project itself: press **Build Scripts**
in the Godot toolbar, or run `./gradlew buildScripts` in the project folder.
You only go back to the checkout to update Kanama.
[Open in IntelliJ IDEA](intellij.md) sets up completion and the debugger, and
[Edit, Build, Run](editor-workflow.md) covers build on save and scene reload.

## Adding Kanama to an Existing Project

For a project that should have its own Gradle build, and open in IntelliJ IDEA
like the starter, use [the store addon](store-addon.md). Until the 0.5.0
addon is published, its page shows how to
[build the addon from this checkout](store-addon.md#building-the-addon-from-source).

You can also install Kanama straight from the checkout, without a Gradle build
in the project:

1. Copy the starter script and the Kanama Tools plugin into the project. This
   never replaces `project.godot` or your scenes:

    ```sh
    ./gradlew installStarterTemplate \
      -PkanamaStarterProjectDir=/path/to/your-project
    ```

2. Build Kanama and the project's scripts into it:

    ```sh
    ./gradlew installAddonJar \
      -PkanamaProjectDir=/path/to/your-project \
      -PkanamaProjectScriptsDir=/path/to/your-project
    ```

3. Open the project in Godot and enable **Kanama Tools** in
   **Project > Project Settings > Plugins**.

`HelloScript.kt` lands in the project root. Attach it to a `Node2D` to try it.

A project set up this way has no Gradle build of its own. **Build Scripts**
runs `installAddonJar` from the checkout instead, so the plugin has to find the
checkout. Keep it at `../kanama` or `../../kanama` from the project, or set
the path in `project.godot`:

```ini
[kanama]
tools/repo_dir="/path/to/kanama"
```

## Troubleshooting

| Problem | Fix |
| --- | --- |
| CMake stops and names a JDK whose `jni.h` is too old. | The native loader needs JDK 25 headers. Point `JAVA_HOME` at your JDK 25 and run the command again. |
| CMake cannot find a JDK with `include/jni.h`. | Install a full JDK 25, not a JRE, and set `JAVA_HOME` to it. |
| CMake is not found, or no C compiler is found. | Install the tools listed in [Requirements](#requirements) and open a new terminal. |
| **Build Scripts** in Godot says it found no JDK 25 or newer, while the terminal build works. | Godot started from a desktop launcher does not see your shell's `JAVA_HOME`. Set **Editor Settings > Kanama > Build > JDK Path** (`kanama/build/jdk_path`); see [JDK Selection](editor-workflow.md#jdk-selection). |
| The game still runs your old Kotlin code. | Press **Build Scripts** or run `./gradlew buildScripts` in the project folder, then press **Play** again. |

## On Other Platforms

On **Windows**, run `.\gradlew.bat` wherever this page says `./gradlew`, for
example `.\gradlew.bat createStarterProject` and
`.\gradlew.bat installAddonJar`. Put a command on one line, or end each line
with a backtick (`` ` ``) instead of a backslash, which is the PowerShell line
continuation.

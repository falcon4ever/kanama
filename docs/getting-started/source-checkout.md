# Install From a Source Checkout

You can build Kanama from its repository and install it into a Godot project.
Use this path to follow `main`, to try a change that is not released yet, or
when no kit has been published for the version you want.

## Requirements

- **Godot 4.7.2** and **a JDK 25 or newer**, as listed in
  [Get Started](index.md#requirements).
- **Git**, to clone the repository.
- **CMake 3.22 or newer and a C compiler**: the Xcode command line tools,
  `build-essential`, or Visual Studio with the C++ workload. The build
  compiles Kanama's small native loader on your machine.

You do not need the Godot source code. The Godot headers Kanama builds against
are in the repository.

## Installation

### Cloning Kanama

```sh
git clone https://github.com/falcon4ever/kanama
cd kanama
```

### Creating a Starter Project

```sh
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/path/to/kanama-starter
```

The starter has the same layout as a [release kit](release-kit.md#what-is-in-the-kit):
a Godot project with `kotlin-src/HelloScript.kt` attached to its main scene,
its own Gradle build and wrapper, and the Kanama Tools plugin enabled. The task
stops if the folder already has a `project.godot`; for a project you already
have, see [Adding Kanama to an Existing Project](#adding-kanama-to-an-existing-project).

### Building and Installing Kanama

```sh
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/kanama-starter \
  -PkanamaProjectScriptsDir=/path/to/kanama-starter/kotlin-src
```

This takes a few minutes the first time. It compiles the Kanama runtime, the
native loader and your scripts, copies them into the project's `addons/kanama`,
and registers Kanama in `.godot/extension_list.cfg`. It also refreshes
`addons/kanama/maven`, the Kanama libraries the starter's own build compiles
against, so IntelliJ IDEA sees the same API your game runs on.

Run the command again whenever you update the checkout.

`kanamaProjectScriptsDir` is the folder whose `.kt` files are compiled. If your
scripts live in several folders, pass them all with
`-PkanamaProjectScriptsDirs=`, separated by commas or by your platform's path
separator.

### Running the Starter

1. Open `/path/to/kanama-starter/project.godot` in Godot.
2. Press **Play**. A label shows *Hello from Kanama*, and the scene slowly
   rotates.

From now on, build your scripts in the starter itself: press **Build Scripts**
in the Godot toolbar, or run `./gradlew buildScripts` in the starter folder.
You only go back to the checkout to update Kanama.
[Open in IntelliJ IDEA](intellij.md) sets up completion and the debugger, and
[Edit, Build, Run](editor-workflow.md) covers build on save and scene reload.

## Adding Kanama to an Existing Project

For a project that should have its own Gradle build, and open in IntelliJ IDEA
like the starter, use [the store addon](store-addon.md). Without a release,
you can also install Kanama straight from the checkout:

1. Copy the starter script and the Kanama Tools plugin into the project. This
   never replaces `project.godot` or your scenes:

    ```sh
    ./gradlew installStarterTemplate \
      -PkanamaStarterProjectDir=/path/to/project
    ```

2. Build Kanama and the project's scripts into it:

    ```sh
    ./gradlew installAddonJar \
      -PkanamaProjectDir=/path/to/project \
      -PkanamaProjectScriptsDir=/path/to/project
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

## Notes on the Build

- **Any JDK 25 or newer runs the build.** The build compiles with JDK 25. If
  only another JDK is installed, Gradle downloads a JDK 25 once into
  `~/.gradle/jdks`, which needs a network connection the first time.
- **The native loader is built against the JDK's headers.** If CMake stops and
  names a JDK, that JDK is too old to build it. Point `JAVA_HOME` at your JDK
  25 and run the command again. Godot started from a desktop launcher on Linux
  or Windows does not see your shell's `JAVA_HOME`; see
  [Which JDK Kanama Uses](editor-workflow.md#which-jdk-kanama-uses).
- **The project's `build.gradle.kts` is short.** It applies the Kotlin and KSP
  plugins and names `kotlin-src` as the source folder. Everything else, the
  Kanama libraries and the `buildScripts` task, comes from
  `addons/kanama/kanama-project.gradle.kts`, which `installAddonJar` refreshes.

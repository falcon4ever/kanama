# Kanama Starter Template

This folder contains the small Kotlin script and editor plugin copied by the
starter Gradle tasks, and `project-README.md`, the README of a project made by
`createStarterProject`.

For a new first project, run this from the Kanama source checkout:

```sh
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/absolute/path/to/new_godot_project
./gradlew installAddonJar \
  -PkanamaProjectDir=/absolute/path/to/new_godot_project \
  -PkanamaProjectScriptsDir=/absolute/path/to/new_godot_project/kotlin-src
```

For an existing Godot project, copy only the safe starter files:

```sh
./gradlew installStarterTemplate \
  -PkanamaStarterProjectDir=/absolute/path/to/your_godot_project
./gradlew installAddonJar \
  -PkanamaProjectDir=/absolute/path/to/your_godot_project \
  -PkanamaProjectScriptsDir=/absolute/path/to/your_godot_project
```

`createStarterProject` creates the release-kit project shape: a tiny `main.tscn`
with `kotlin-src/HelloScript.kt` attached to a `Node2D`, a Gradle build with its
own wrapper (`./gradlew buildScripts`; open the folder in IntelliJ IDEA as a
Gradle project), and the editor tools. `installAddonJar` then adds the runtime
and `addons/kanama/maven`. `installStarterTemplate` copies only `HelloScript.kt`
and `addons/kanama_tools`, so it will not replace an existing `project.godot`
or main scene.

Open the project in Godot after running `installAddonJar`. The `Kanama Tools`
plugin adds the `Build Scripts` toolbar button, `Open Kotlin` shortcut, optional
build-on-save, and a desktop Java preflight warning when `libjvm` cannot be
found. Install JDK 25+ and set `JAVA_HOME` if that preflight fails.

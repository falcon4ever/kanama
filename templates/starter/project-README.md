# Kanama Starter

A Godot project with Kotlin scripts, made by `createStarterProject` from a Kanama
source checkout. It builds its scripts with Gradle and the Kanama addon that
`installAddonJar` installed from that checkout.

Open the folder in IntelliJ IDEA as a Gradle project for Kotlin completion of
the Kanama API (resolved from `addons/kanama/maven`). The starter script is
`kotlin-src/HelloScript.kt`, attached to the root node of `main.tscn`.

## Build Kotlin scripts

```sh
./gradlew buildScripts
```

On Windows, use `gradlew.bat buildScripts`. The Kanama Tools editor plugin adds
a `Build Scripts` button to Godot that runs the same Gradle task.

## Open in Godot

```sh
./gradlew openGodotEditor
```

You can also open the folder directly in Godot and press **Play**.

## After updating the Kanama checkout

The runtime in `addons/kanama` and the API in `addons/kanama/maven` come from
the checkout. After pulling a new Kanama version, rerun `installAddonJar` from
the checkout so the starter builds and runs against it:

```sh
cd /path/to/kanama
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/this/project \
  -PkanamaProjectScriptsDir=/path/to/this/project/kotlin-src
```

## Requirements

The Godot release and JDK Kanama targets are listed once, in Kanama's
[Version Support → Requirements](https://falcon4ever.github.io/kanama/reference/version-support/#requirements).
Set `JAVA_HOME` if Kanama cannot find `libjvm`.

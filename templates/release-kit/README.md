# Kanama Desktop Kit

This Godot project builds its Kotlin scripts with Gradle and the Kanama addon.
Open the folder in IntelliJ IDEA as a Gradle project for Kotlin completion of
the Kanama API (resolved from `addons/kanama/maven`).

`build.gradle.kts` is intentionally small. Kanama's reusable repositories,
dependencies, and build/run tasks live in `addons/kanama/kanama-project.gradle.kts`.

## Build Kotlin scripts

```sh
./gradlew buildScripts
```

On Windows, use `gradlew.bat buildScripts`.

## Open in Godot

```sh
./gradlew openGodotEditor
```

You can also open the folder directly in Godot. The Kanama Tools editor plugin
adds a `Build Scripts` button that runs the same Gradle task, builds your
scripts each time you save a `.kt` file, and shows a status light that is
green when they are built and current.

## Requirements

The Godot release and JDK this kit targets are listed once, in Kanama's
[Requirements and Platform Status](https://falcon4ever.github.io/kanama/reference/version-support/#requirements).
Set `JAVA_HOME` if Kanama cannot find `libjvm`.

## macOS Gatekeeper

GitHub-downloaded Kanama zips may be quarantined by macOS. If Godot reports
`"libkanama_bootstrap.dylib" Not Opened`, clear quarantine on the unzipped
project copy you trust:

```sh
xattr -dr com.apple.quarantine /absolute/path/to/project
```

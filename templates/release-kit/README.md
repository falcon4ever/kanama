# Kanama Desktop Kit

This project is ready to run with the packaged Kanama addon.

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
adds a `Build Scripts` button that runs the same Gradle task.

## Requirements

- Godot 4.7 rc 2 or newer matching Kanama's supported Godot version.
- JDK 25 or newer. Set `JAVA_HOME` if Kanama cannot find `libjvm`.

## macOS Gatekeeper

GitHub-downloaded Kanama zips may be quarantined by macOS. If Godot reports
`"libkanama_bootstrap.dylib" Not Opened`, clear quarantine on the unzipped
project copy you trust:

```sh
xattr -dr com.apple.quarantine /absolute/path/to/project
```

# Kanama Addon

Kanama lets Kotlin scripts attach to Godot nodes through a GDExtension runtime
powered by the JVM and the Foreign Function & Memory API.

This addon package is install-safe for existing Godot projects. It provides:

- `addons/kanama` with the runtime jar, `.gdextension` descriptor, native
  bootstrap libraries, and local Maven artifacts,
- `addons/kanama_tools` with the optional editor build tools, and
- starter Gradle and wrapper templates under
  `addons/kanama/templates/release-kit`.

The template directory is safe to keep under `addons/`: it does not include a
nested `project.godot` or `main.tscn`.

From the root of your Godot project, initialize Kanama:

```sh
sh addons/kanama/setup-kanama-project.sh
./gradlew buildScripts
```

On Windows:

```powershell
.\addons\kanama\setup-kanama-project.ps1
.\gradlew.bat buildScripts
```

The setup script keeps existing root Gradle files by default. If your project
already has Gradle files, merge the small template build shape manually or run
with `--force` only when replacing those files is safe.

Desktop projects require Godot 4.7 rc 2 or newer matching Kanama's supported
version and JDK 25 or newer. Set `JAVA_HOME` if Kanama cannot find `libjvm`.

On macOS, downloaded Kanama zips may be quarantined by Gatekeeper. If Godot
reports `"libkanama_bootstrap.dylib" Not Opened`, clear quarantine on the
project copy you trust:

```sh
xattr -dr com.apple.quarantine /absolute/path/to/project
```

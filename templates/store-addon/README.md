# Kanama

Kanama lets you write Godot scripts in Kotlin. It needs a JDK 25 or newer
(Temurin 25 is a good choice) and builds your scripts with an editor button.
Like C# needs the .NET SDK, it is not enable-and-go.

This folder is the runtime Godot loads, with the Kanama libraries your scripts
compile against. The editor plugin is next to it, in `addons/kanama_tools`.
Its README has the steps from zip to a running script: enable **Kanama Tools**,
choose **Project > Tools > Kanama Tools: Set Up Kanama Project**, then press
**Build Scripts**. Setup keeps every file your project already has. It adds
the Kanama block to a `settings.gradle.kts` of your own, unless that file has a
`pluginManagement { }` block already or your project uses a Groovy
`settings.gradle`; then it changes nothing and says what to merge. An existing
`build.gradle.kts` is never touched. See
https://falcon4ever.github.io/kanama/getting-started/store-addon/#projects-that-already-have-a-gradle-build

Kanama needs Godot 4.8 (each release lists its exact Godot version:
https://falcon4ever.github.io/kanama/reference/version-support/#requirements).
The editor side runs on macOS (Apple silicon), Windows x64, and Linux x64 and
arm64.

No editor? Run the same setup from the project root:

```sh
sh addons/kanama/setup-kanama-project.sh
./gradlew buildScripts
```

On Windows:

```powershell
powershell -ExecutionPolicy Bypass -File .\addons\kanama\setup-kanama-project.ps1
.\gradlew.bat buildScripts
```

If Godot cannot find the JDK, set **Editor > Editor Settings > Kanama > Build >
JDK Path** to your JDK 25 folder and restart the editor. A desktop launcher
does not pass `JAVA_HOME` to Godot, so the setting is the reliable way.

On macOS, Gatekeeper may quarantine the downloaded zip. If Godot reports
`"libkanama_bootstrap.dylib" Not Opened`, clear it on your project, then
reopen the project:

```sh
xattr -dr com.apple.quarantine /absolute/path/to/project
```

More: https://falcon4ever.github.io/kanama/getting-started/store-addon/ for
setup and fixes, https://falcon4ever.github.io/kanama/ for the documentation,
and https://github.com/falcon4ever/kanama for source and issues.

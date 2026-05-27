# Use a Release Kit

Release kits are the default install path for trying Kanama without cloning the
Kanama repository. A desktop kit is a complete starter Godot project with the
Kanama addon, editor tools, Gradle wrapper, local Maven dependencies, and a
starter Kotlin script.

## 1. Download a Desktop Kit

Download the matching artifact from the Kanama GitHub release:

| Platform | Artifact |
| --- | --- |
| macOS arm64 | `kanama-desktop-kit-v<version>-macos-arm64.zip` |
| Linux x64 | `kanama-desktop-kit-v<version>-linux-x64.zip` |
| Linux ARM64 | `kanama-desktop-kit-v<version>-linux-arm64.zip` |
| Windows x64 | `kanama-desktop-kit-v<version>-windows-x64.zip` |

Unzip it into a new project folder. The zip is rooted at the project directory,
so `project.godot`, `build.gradle.kts`, and `addons/` should be at the top
level after extraction.

## 2. Build Kotlin Scripts

From the unzipped project folder:

```sh
./gradlew buildScripts
```

On Windows:

```powershell
.\gradlew.bat buildScripts
```

This compiles `kotlin-src/*.kt` and writes
`addons/kanama/kanama-scripts.jar`. The Kanama runtime jar, native bootstrap,
and local Maven repository are already included in the kit.

## 3. Open and Run

Open `project.godot` in Godot and press **Play**. The starter scene updates a
label from Kotlin and rotates the root `Node2D`.

The `Kanama Tools` editor plugin is enabled by default. It adds:

- **Build Scripts** to compile Kotlin scripts from the editor,
- **Open Kotlin** to open the configured source folder,
- optional build-on-save,
- scene reload after sync, and
- desktop Java preflight warnings when `libjvm` cannot be found.

## Existing Projects

The desktop kit is meant for new projects. For an existing Godot project, use
the store-ready addon zip once published, or copy the release kit pieces
carefully:

- `addons/kanama`,
- `addons/kanama_tools`, and
- the Gradle template files from `addons/kanama/templates/release-kit`.

Do not blindly unzip a desktop kit over an existing project because it contains
root project files such as `project.godot`, `build.gradle.kts`, and `gradlew`.

## Troubleshooting

| Symptom | Fix |
| --- | --- |
| Godot cannot find `libjvm`. | Install JDK 25+ and set `JAVA_HOME` to the JDK home directory. |
| macOS reports `"libkanama_bootstrap.dylib" Not Opened`. | The downloaded zip was quarantined by Gatekeeper. After unzipping a Kanama artifact you trust, run `xattr -dr com.apple.quarantine /absolute/path/to/project` before opening it in Godot. |
| Godot still runs old Kotlin behavior. | Press **Build Scripts** or rerun `./gradlew buildScripts`. |
| Gradle cannot resolve Kanama dependencies. | Confirm `addons/kanama/maven` exists; the release kit resolves Kanama from that local Maven repo. |
| Godot reports missing `res://*.kt` scripts. | Confirm `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen/import the project. |

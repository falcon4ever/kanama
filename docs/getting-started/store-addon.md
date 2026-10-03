# Use a Store Addon

Use this path when you have a generated store-addon zip and want to add Kanama
to an existing Godot project without a sibling Kanama source checkout. The
store addon is install-safe: it adds files under `addons/` and keeps
project-root Gradle files inside a template folder until you copy them.

If no matching GitHub zip release exists yet, use
[a source checkout](source-checkout.md) or build a local addon with
`./gradlew packageStoreAddon`.

## 1. Download the Addon

Use `build/distributions/kanama-store-addon-v<version>.zip` from a local
package build, or download `kanama-store-addon-v<version>.zip` from a Kanama
GitHub release when one exists. Unzip it at the root of your Godot project.

The zip adds:

- `addons/kanama` with the Kanama runtime, native libraries, and local Maven
  repository,
- `addons/kanama_tools` with optional editor build tools, and
- `addons/kanama/templates/release-kit` with Gradle starter files.

On macOS, clear quarantine after unzipping a package you trust:

```sh
xattr -dr com.apple.quarantine /absolute/path/to/project
```

## 2. Initialize the Project

From the Godot project root, run the packaged setup script:

```sh
sh addons/kanama/setup-kanama-project.sh
```

On Windows:

```powershell
.\addons\kanama\setup-kanama-project.ps1
```

The setup script copies the starter Gradle files when they are missing, adds
`kotlin-src/HelloScript.kt`, and registers
`res://addons/kanama/kanama.gdextension` in `.godot/extension_list.cfg`.

If the project already has Gradle files, the setup script keeps them. Merge the
small template `build.gradle.kts` shape manually, or run the script with
`--force` only when replacing the root Gradle files is safe.

## 3. Build Scripts

Compile the Kotlin scripts:

```sh
./gradlew buildScripts
```

On Windows:

```powershell
.\gradlew.bat buildScripts
```

This writes `addons/kanama/kanama-scripts.jar`. Attach `.kt` scripts to nodes
the same way you attach other Godot script resources.

## 4. Open and Run

Open or reopen the project in Godot, enable the `Kanama Tools` plugin if needed,
and press **Play**. After editing Kotlin files, use **Build Scripts** in the
editor or rerun `./gradlew buildScripts`.

## Troubleshooting

| Symptom | Fix |
| --- | --- |
| Godot cannot find `libjvm`. | Install JDK 25+. Kanama also searches the usual install locations (`/usr/lib/jvm`, `/Library/Java/JavaVirtualMachines`, Program Files, `~/.jdks`); otherwise set `JAVA_HOME`, or the `kanama/build/jdk_path` editor setting, to the JDK home directory and restart the editor. See [Which JDK Kanama Uses](editor-workflow.md#which-jdk-kanama-uses). |
| macOS reports `"libkanama_bootstrap.dylib" Not Opened`. | Clear quarantine on the unzipped project with `xattr -dr com.apple.quarantine /absolute/path/to/project`. |
| Gradle cannot resolve Kanama dependencies. | Confirm `addons/kanama/maven` exists and your build uses `maven { url = uri("addons/kanama/maven") }`. |
| Godot does not recognize `.kt` scripts. | Confirm `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen/import the project. |

# Desktop and Packaging

Kanama is distributed as source on GitHub for the current development phase.
The repository is the expected install/update path until release packaging is
stabilized.

## Current Distribution Model

- Supported runtime target: desktop Godot editor/player processes with a
  regular JDK/JVM available.
- Baseline engine target: Godot 4.7 beta 3 preview.
- Compatible binaries: the
  [Godot 4.7 beta 3 archive](https://godotengine.org/download/archive/4.7-beta3/).
- Experimental runtime target: Android exports through a Godot Android plugin
  AAR and Android-specific PanamaPort/ART bootstrap path.
- Not planned: Web exports.
- Unsupported today: iOS exports.

The source repository contains the example project, starter template, native
bootstrap, Gradle build, and local CI scripts used to build and validate the
addon. Packaged consumer artifacts are separate from this source checkout.

## Source Install

For now, clone the repository and use the Gradle tasks from the repo root:

```sh
./gradlew syncExampleAddonJar
./gradlew installAddonJar \
  -PkanamaProjectDir=/absolute/path/to/godot_project \
  -PkanamaProjectScriptsDir=/absolute/path/to/godot_project
./gradlew installStarterTemplate -PkanamaStarterProjectDir=/absolute/path/to/godot_project
```

`syncExampleAddonJar` updates the example project's Kanama runtime jars and
native bootstrap and remains the local smoke-test path. `installAddonJar` is
the current downstream project install path: it builds `kanama.jar`, builds the
host native bootstrap with CMake, compiles Kotlin scripts from
`kanamaProjectScriptsDir` into `kanama-scripts.jar`, and copies the jars plus
the addon `.gdextension`/native bootstrap files into
`<kanamaProjectDir>/addons/kanama`. It also creates or updates
`<kanamaProjectDir>/.godot/extension_list.cfg` with
`res://addons/kanama/kanama.gdextension`; without that entry, Godot may see the
files on disk but never load the extension.
`installStarterTemplate` copies `templates/starter` into an existing Godot
project so consumers can start from a small attachable script and optional
editor plugin.

`kanamaProjectScriptsDir` can point at the project root or a dedicated scripts
folder. For multiple source roots, use `-PkanamaProjectScriptsDirs=` with paths
separated by the platform path separator (`:` on macOS/Linux, `;` on Windows)
or commas.

## Platform Artifacts

The `.gdextension` file already names desktop library slots:

| Platform | Library name | Current status |
| --- | --- | --- |
| macOS arm64 | `libkanama_bootstrap.dylib` | Current Godot 4.7 beta 3 preview baseline and primary local CI path. |
| Linux arm64 | `libkanama_bootstrap.so` | Pending beta 3 revalidation; last local runtime, editor, and demo smoke validation passed with the 4.7 beta 2 ARM64 binary. |
| Linux x86_64 | `libkanama_bootstrap.so` | Pending beta 3 revalidation; last local runtime, editor, and demo smoke validation passed with the 4.7 beta 2 x64 binary. |
| Windows x86_64 | `kanama_bootstrap.dll` | Pending beta 3 revalidation; last local runtime/editor smoke validation passed with the 4.7 beta 2 console binary. Use PowerShell Gradle commands and Git Bash smoke marker checks. |

Validated platform support claims require a native bootstrap library and JVM
loading smoke path for that platform.

Native bootstrap libraries are generated build artifacts. Source repositories
ignore `kanama_bootstrap.dll`, `libkanama_bootstrap.so`, and
`libkanama_bootstrap.dylib`; rebuild the matching library locally for the
platform under test instead of committing it. `syncExampleAddonJar` and
`installAddonJar` run the `buildNativeBootstrap` Gradle task automatically; run
`./gradlew buildNativeBootstrap` directly only when you need to refresh the
native library without syncing an addon.

Building the desktop native bootstrap from Kanama source requires CMake, a
platform C toolchain, and JDK 25 headers. It does not require a Godot source
checkout because Kanama tracks the required GDExtension headers.

On macOS arm64, install JDK 25, use
`/Applications/Godot.app/Contents/MacOS/Godot` or a Godot executable on
`PATH`, and run Gradle commands with `./gradlew`.

On Linux, set `JAVA_HOME` to a JDK 25+ install before launching Godot or running
desktop smokes. The native bootstrap loads `${JAVA_HOME}/lib/server/libjvm.so`
at runtime. Without `JAVA_HOME`, Kanama cannot register the `.kt` resource
loader, and Godot may report misleading missing-script errors for existing
`res://kotlin-src/*.kt` files.

For repeatable Linux validation, build the native bootstrap from a clean
checkout, preflight `libkanama_bootstrap.so` with `file`, `ldd`, and `readelf`
before copying it into demos, set `XDG_DATA_HOME` to an isolated temporary
directory for Godot state, and refresh each demo addon with `installAddonJar`
before smoke checks.

The Linux desktop smoke passes used Godot's OpenGL Compatibility path:

```sh
JAVA_HOME=/path/to/jdk-25 \
godot --headless \
  --rendering-driver opengl3 \
  --rendering-method gl_compatibility \
  --path /absolute/path/to/godot_project
```

On Windows, set `JAVA_HOME` to a JDK 25+ install before launching Godot or
running smokes. The native bootstrap loads
`%JAVA_HOME%\bin\server\jvm.dll` at runtime. Building the Windows bootstrap
requires Visual Studio 2022 with the **Desktop development with C++** workload,
including MSVC, the Windows SDK, and CMake tools for Windows.

Use the Godot 4.7 beta 3 Windows console binary for smoke runs. Run Gradle
commands with `.\gradlew.bat` in PowerShell. When installing Kanama into a demo
or project, quote the full Gradle property argument and point
`kanamaProjectScriptsDir` at the Kotlin source directory when the project root
contains Android export build artifacts:

```powershell
.\gradlew.bat :project-scripts:jar installAddonJar `
  "-PkanamaProjectDir=C:\path\to\godot-project" `
  "-PkanamaProjectScriptsDir=C:\path\to\godot-project\kotlin-src"
```

## Android Track

Android exports use a different runtime path from desktop: a Godot Android
plugin AAR, ART, PanamaPort, and Android-specific packaging. Keep Android
release wording experimental until the matching APK smoke path has passed.

See [Android Experimental](android.md) for the current workflow, validation
status, and boundaries.

## Exported Games

Export support is a separate release-readiness track from editor/source
development. An exported desktop game must include or locate:

- the platform bootstrap library referenced by the `.gdextension` file,
- `kanama.jar`,
- the project's Kotlin scripts jar,
- a compatible JDK/JVM runtime or a documented system-JDK requirement,
- platform-specific JVM loading paths handled by the bootstrap library.

The current project validates editor/headless workflows on the platforms listed
above. Exported desktop game packaging is not a release claim yet. Platform
smoke validation does not mean native desktop export packaging is finished.
Packaged exports need to state whether they bundle a JVM runtime or require
users to install JDK 25+.

## Local Release Checks

Run the same local CI shortcut used during development:

```sh
scripts/local_ci.sh /absolute/path/to/godot-4.7-beta3
```

When testing multiple engine versions:

```sh
scripts/local_ci.sh \
  /absolute/path/to/godot-4.7-beta3 \
  /absolute/path/to/godot-4.7-stable
```

Release validation covers API validation, Gradle sync, bootstrap build, strict
docs build, runtime smoke, `@Tool` smoke, and hot-reload smoke for every claimed
Godot binary. The main headless runtime smoke is `scripts/runtime_smoke.sh`.

## Packaged Addon Artifacts

A packaged addon contains the runtime jars, native bootstrap libraries,
`.gdextension` metadata, and editor plugin files needed by a consumer Godot
project. These packaged native libraries are produced from ignored build
outputs; they are not source-repo files. Packaged addons omit local build
directories, crash logs, temporary smoke logs, and machine-specific paths.

# Desktop and Packaging

Kanama has two desktop distribution shapes:

- **Desktop kit**: a complete starter Godot project for new users.
- **Store addon**: an install-safe addon zip for existing projects and future
  Godot Asset Store submission.

Source checkout installs remain supported for development. See
[Use a Source Checkout](../getting-started/source-checkout.md) for that path.
The package tasks below produce local zips today; they become public download
flows only when matching GitHub release artifacts are published.

## Desktop Kit

A desktop kit is built per platform:

```sh
./gradlew packageDesktopKit
```

The output is:

```text
build/distributions/kanama-desktop-kit-v<version>-<platform>.zip
```

The zip is rooted at the Godot project directory and contains:

- `project.godot`, `main.tscn`, and `kotlin-src/HelloScript.kt`,
- `build.gradle.kts`, `settings.gradle.kts`, `gradlew`, and Gradle wrapper
  files,
- `addons/kanama/kanama.jar`,
- `addons/kanama/maven` with Kanama runtime, annotations, and processor
  Gradle artifacts,
- `addons/kanama/bin/<platform>/` with the native bootstrap,
- `addons/kanama/kanama.gdextension`,
- `addons/kanama_tools`, and
- `.godot/extension_list.cfg`.

Validate a kit from a temporary project:

```sh
scripts/package_install_smoke.sh \
  build/distributions/kanama-desktop-kit-v<version>-<platform>.zip \
  /absolute/path/to/godot-4.7-stable
```

The smoke unzips the kit, runs `./gradlew buildScripts`, confirms
`kanama-scripts.jar`, and launches Godot when a binary is provided.

## Store Addon

The store addon is intentionally safer for existing projects. It does not place
files at the project root. It contains:

- `addons/kanama`,
- `addons/kanama_tools`,
- all available desktop native bootstrap binaries under
  `addons/kanama/bin/<platform>/`,
- the local Maven repo under `addons/kanama/maven`, and
- release-kit Gradle and wrapper templates under
  `addons/kanama/templates/release-kit`.

The store addon intentionally does not include nested `project.godot` or
`main.tscn` files under `addons/`, so installing it into an existing Godot
project does not create a second embedded Godot project.

Build a local host-only store addon:

```sh
./gradlew packageStoreAddon
```

The all-platform store addon is assembled by the GitHub package workflow after
the matrix builds macOS arm64, Linux x64, Linux ARM64, and Windows x64 native
artifacts. The workflow smokes every desktop kit and the assembled store addon
before uploading artifacts.

Validate a store addon from a temporary project:

```sh
scripts/package_install_smoke.sh \
  --store-addon \
  --require-all-store-platforms \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7-stable
```

For a local host-only `packageStoreAddon` build, omit
`--require-all-store-platforms`.

## GitHub Release Workflow

The package workflow runs only on manual dispatch and `v*` tags. It does not
run on pull requests.

Matrix targets:

| Platform | Runner | Artifact classifier |
| --- | --- | --- |
| macOS arm64 | `macos-15` | `macos-arm64` |
| Linux x64 | `ubuntu-24.04` | `linux-x64` |
| Linux ARM64 | `ubuntu-24.04-arm` | `linux-arm64` |
| Windows x64 | `windows-2025` | `windows-x64` |

The release job grants `contents: write` only when publishing assets for a tag.
All other package jobs use read-only repository permissions.

## Runtime Requirements

Desktop Kanama development needs a JDK 25+ distribution that contains
`libjvm`. The native bootstrap checks for a bundled app-relative `runtime/`
image first (exported games, see below), then `JAVA_HOME`, then platform
fallback locations. The optional `addons/kanama_tools` editor plugin runs the
same preflight and warns inside Godot if it cannot find `libjvm`.

Native bootstrap libraries are generated build artifacts. Source repositories
ignore `kanama_bootstrap.dll`, `libkanama_bootstrap.so`, and
`libkanama_bootstrap.dylib`; rebuild the matching library locally for the
platform under test instead of committing it.

Current macOS GitHub artifacts are not Apple-notarized. If Gatekeeper reports
`"libkanama_bootstrap.dylib" Not Opened` after unzipping a downloaded desktop
kit or store addon, clear quarantine on the project copy you trust:

```sh
xattr -dr com.apple.quarantine /absolute/path/to/project
```

## Exported Games

The decided end state (issue #102) is **unpack-and-play**: exported desktop
games ship a bundled, jlink-trimmed JVM runtime that the native bootstrap
finds app-relative, so players never install a JDK. The system JDK stays the
**developer** path — like C# development needs the .NET SDK while Godot/.NET
exports bundle the .NET runtime.

Status: the same-OS flow below is implemented and smoke-validated on macOS
arm64. Windows and Linux exports (the v1 distribution targets) plus
cross-platform runtime production (exporting a Windows game from a macOS
machine) are still open; until then, treat desktop exported-game packaging as
release-readiness work in progress, not a support claim.

An exported game needs four pieces next to each other: the platform bootstrap
library referenced by `kanama.gdextension` (Godot's export copies it),
`kanama.jar`, the project `kanama-scripts.jar`, and the `runtime/` image.
Assembly is three steps:

```sh
./gradlew jlinkGameRuntime
# export the game from Godot (editor or `godot --headless --export-release ...`)
scripts/export_game_assemble.sh \
  --scripts-jar /path/to/project/addons/kanama/kanama-scripts.jar \
  /path/to/exported-game
```

`jlinkGameRuntime` builds `build/game-runtime/runtime` from the local JDK
25+ with a pinned module set (`java.base`, `java.instrument`,
`jdk.unsupported` — about 31 MB on macOS arm64, one recipe for every Kanama
game). A game that needs an extra JDK module adds
`-PkanamaRuntimeAdditionalModules=java.net.http,...`; the default path
requires nothing.

`export_game_assemble.sh` anchors on the exported bootstrap library and
places the payload where the bootstrap probes before `JAVA_HOME`:
next to the library on Windows/Linux, and inside `Contents/Resources/` for a
macOS `.app` (the bundle location that survives re-signing). On macOS it also
re-seals the bundle ad-hoc, because adding files after Godot's export breaks
the code signature.

macOS export presets need two things for the embedded JVM: the
`Import ETC2 ASTC` VRAM compression project setting (any universal/arm64
export), and the `allow_jit_code_execution`,
`allow_unsigned_executable_memory`, and `disable_library_validation`
codesign entitlements — without them the hardened runtime kills the JVM at
startup. Distribution-grade signing/notarization of the bundled runtime is
the separate macOS notarization track.

`scripts/export_game_smoke.sh /path/to/godot` proves the whole story on the
host platform: it exports the example project, assembles the runtime, and
launches the export headless with `JAVA_HOME` unset and `PATH` stripped,
asserting the game boots from the app-relative runtime. The desktop kits
still validate editor/runtime onboarding only; the export smoke is the
exported-game gate.

## Android Track

Android exports use a different runtime path: a Godot Android plugin AAR, ART,
PanamaPort, and Android-specific packaging. Android is Supported on 4.7 stable;
release builds require Android 13+ (debug down to Android 9).

See [Android](android.md) for the workflow, validation status, and boundaries.

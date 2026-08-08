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

Status: implemented for Windows, Linux, and macOS, and **cross-target** — a
developer on any desktop host can produce the runtime for any other. Treat
exported-game packaging as release-readiness work in progress rather than a
support claim until the macOS notarization track catches up, but the packaging
itself is no longer host-locked.

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

`jlinkGameRuntime` builds `build/game-runtime/runtime` for **this** platform
from the local JDK 25+ with a pinned module set (`java.base`,
`java.instrument`, `jdk.unsupported` — one recipe for every Kanama game). A
game that needs an extra JDK module adds
`-PkanamaRuntimeAdditionalModules=java.net.http,...`; the default path
requires nothing.

### Exporting for another platform

Godot's export templates are cross-platform, and so is Kanama's bundled
runtime. `jlinkGameRuntimeCross` builds the runtime image for a target other
than the host:

```sh
./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64
# export the game for Windows from Godot
scripts/export_game_assemble.sh \
  --runtime build/game-runtime/windows-x64/runtime \
  --scripts-jar /path/to/project/addons/kanama/kanama-scripts.jar \
  /path/to/windows-export-dir
```

Targets are the same classifiers the release artifacts use: `windows-x64`,
`linux-x64`, `linux-arm64`, `macos-arm64`.

The task downloads that platform's Temurin **jmods** (about 80 MB compressed,
SHA-256 pinned in `build.gradle.kts`) and links against them. Note that a
Temurin JDK install no longer contains jmods at all — since JDK 24's JEP 493
the JDK links from its own run-time image, which only ever produces an image
for the platform it is running on — so the jmods come from Adoptium's separate
per-platform download. They are cached outside `build/` (default
`~/.gradle/kanama-target-jmods`, override with `-PkanamaTargetJmodsCacheDir` or
`KANAMA_TARGET_JMODS_CACHE`), so the download happens once, not per build.

The jmods must be the same JDK **feature** version as the JDK doing the
linking; patch levels may differ. `kanamaTargetJdkRelease` in
`gradle.properties` pins which Temurin release is fetched, and the task fails
with a clear message if it does not match the build JDK.

`export_game_assemble.sh` refuses to pair a runtime image with an export built
for a different platform — a mismatch would only surface as a dead game on a
player's machine.

### Size

The bundled runtime is the whole download cost of unpack-and-play. Measured
with the pinned module set, cross-built from macOS arm64 against Temurin
25.0.4+7:

| Target | Runtime image |
| --- | --- |
| `windows-x64` | 31 MB |
| `linux-x64` | 42 MB |
| `macos-arm64` | 31 MB |
| `linux-arm64` | 40 MB |

(Sum of file sizes; `du` reports 1-2 MB more from block rounding.)

Plus `kanama.jar` and the project's `kanama-scripts.jar`. That is the accepted
trade: every shipped commercial Java game bundles its runtime, and a smaller
download that asks players to install Java is effectively fatal for a game.
Module stripping and compression are later tuning knobs, not blockers.

### Layout notes

`export_game_assemble.sh` anchors on the exported bootstrap library and
places the payload where the bootstrap probes before `JAVA_HOME`:
next to the library on Windows/Linux, and inside `Contents/Resources/` for a
macOS `.app` (the bundle location that survives re-signing). On macOS it also
re-seals the bundle ad-hoc, because adding files after Godot's export breaks
the code signature.

Windows keeps the server JVM at `runtime\bin\server\jvm.dll`, not
`runtime/lib/server` as macOS and Linux do, and jvm.dll's CRT dependencies sit
one level up in `runtime\bin`. The bootstrap registers that directory with the
loader before loading the JVM, so a bundled Windows runtime works on a machine
with no Visual C++ redistributable installed.

macOS export presets need two things for the embedded JVM: the
`Import ETC2 ASTC` VRAM compression project setting (any universal/arm64
export), and the `allow_jit_code_execution`,
`allow_unsigned_executable_memory`, and `disable_library_validation`
codesign entitlements — without them the hardened runtime kills the JVM at
startup. Distribution-grade signing/notarization of the bundled runtime is
the separate macOS notarization track.

### The gate

`scripts/export_game_smoke.sh /path/to/godot` proves the whole story: it
exports the example project, assembles the runtime, and launches the export
headless with `JAVA_HOME` unset and `PATH` carrying no JDK, asserting the game
boots from the app-relative runtime. `--runtime DIR` points it at an image
built elsewhere.

The `package` workflow runs it that way on purpose: a macOS job cross-builds
the Windows and Linux runtimes, uploads them, and the `windows-2025` and
`ubuntu-24.04` jobs export and boot a game against those exact artifacts. A
runner that jlinked its own runtime would be green and would still prove
nothing about exporting from another host.

The desktop kits still validate editor/runtime onboarding only; the export
smoke is the exported-game gate.

## Android Track

Android exports use a different runtime path: a Godot Android plugin AAR, ART,
PanamaPort, and Android-specific packaging. Android is Supported on 4.7 stable;
release builds require Android 13+ (debug down to Android 9).

See [Android](android.md) for the workflow, validation status, and boundaries.

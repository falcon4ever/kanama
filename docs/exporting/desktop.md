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
  /absolute/path/to/godot-4.7-rc2
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
  /absolute/path/to/godot-4.7-rc2
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

Desktop Kanama needs a JDK 25+ distribution that contains `libjvm`. The native
bootstrap checks `JAVA_HOME` first, then platform fallback locations. The
optional `addons/kanama_tools` editor plugin runs the same preflight and warns
inside Godot if it cannot find `libjvm`.

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

Exported desktop game packaging is still a separate release-readiness track.
An exported game must include or locate:

- the platform bootstrap library referenced by `kanama.gdextension`,
- `kanama.jar`,
- the project `kanama-scripts.jar`,
- a compatible JDK/JVM runtime or a documented system-JDK requirement, and
- platform-specific JVM loading paths handled by the bootstrap library.

The desktop kits validate editor/runtime onboarding. They do not yet claim a
complete exported-game packaging story.

## Android Track

Android exports use a different runtime path: a Godot Android plugin AAR, ART,
PanamaPort, and Android-specific packaging. Keep Android release wording
experimental until the matching APK smoke path has passed.

See [Android Experimental](android.md) for the current workflow, validation
status, and boundaries.

# Release Process

How the distributable zips are built and checked. Users download them from the
releases page; see [Install from a Release Kit](../getting-started/release-kit.md)
and [Add Kanama to an Existing Project](../getting-started/store-addon.md).

## Desktop Packages

Kanama has two desktop distribution shapes:

- **Desktop kit**: a complete starter Godot project for new users.
- **Store addon**: an install-safe addon zip for existing projects and future
  Godot Asset Store submission.

The package tasks produce local zips; they become public download flows only
when matching GitHub release artifacts are published.

### Desktop Kit

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
  /absolute/path/to/godot-4.7.2-stable
```

The smoke unzips the kit, runs `./gradlew buildScripts`, confirms
`kanama-scripts.jar`, and launches Godot when a binary is provided.

### Store Addon

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
  /absolute/path/to/godot-4.7.2-stable
```

For a local host-only `packageStoreAddon` build, omit
`--require-all-store-platforms`.

## Mobile Addon Packages

The mobile addon zips carry a prebuilt runtime only: compiling a project's
Kotlin scripts for Android or iOS still needs a Kanama source checkout
(`installAndroidPluginAar`, `installIosAddon`).

### Android

The Android runtime AAR builds without a project directory
(`./gradlew assembleAndroidPluginAar`); `./gradlew packageMobileAddonAndroid`
packages it, debug only.

### iOS

```sh
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer \
./gradlew packageMobileAddonIos \
  -PkanamaXcodeDeveloperDir=/Applications/Xcode.app/Contents/Developer
```

This writes `build/distributions/kanama-mobile-addon-ios-v<version>.zip`
containing both device `arm64` xcframeworks (debug + release — the static libraries are large,
roughly 168 MB debug / 110 MB release before zip; measured in [Requirements and Platform Status](../reference/version-support.md#ios-library-size)), <!-- kanama-claim: ios-static-size -->
a `.gdextension` entries fragment to merge, and an install README.

**Honest caveat:** the packaged addon delivers the *runtime* only. Compiling a
project's Kotlin scripts for iOS still requires a Kanama source checkout
(`installIosAddon` runs the Kotlin/Native + KSP build). Use the zip to update
the prebuilt runtime or for script-less evaluation; the source-checkout flow
in [Export for iOS](../exporting/ios.md) remains the full workflow.

Validate a packaged zip without a device:

```sh
scripts/package_install_smoke.sh --ios-addon \
  build/distributions/kanama-mobile-addon-ios-v<version>.zip
```

## Package Workflow

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

## Native Bootstrap Libraries

Native bootstrap libraries are generated build artifacts. Source repositories
ignore `kanama_bootstrap.dll`, `libkanama_bootstrap.so`, and
`libkanama_bootstrap.dylib`; rebuild the matching library locally for the
platform under test instead of committing it.

## Version Bumps

The Gradle artifact version is `version` in `build.gradle.kts`. Change it
together with the matching Gradle coordinates, docs snippets, demo project
versions, badges and changelog headings, and only with a passing smoke matrix
for every claimed target ([Platform Evidence](platform-evidence.md)).

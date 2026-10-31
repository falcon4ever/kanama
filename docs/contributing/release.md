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
when matching GitHub release artifacts are published. Build the host desktop
kit and the local host store addon together with:

```sh
./gradlew packageDistributions
```

Every package carries the Android runtime AAR, so packaging needs the Android
SDK, NDK and CMake, or `-PkanamaAndroidPackageDir`; see [Desktop Kit](#desktop-kit).

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
- `addons/kanama/kanama.version`, the one line that ties the addon, the Kanama
  Gradle plugin and the runtime dependencies to one version (the template's
  `settings.gradle.kts` resolves the plugin from it, and the editor compares it
  with the `kanama-scripts.version` stamp `buildScripts` writes),
- `addons/kanama/maven` with the Kanama runtime, annotations and processor,
  the Kanama Gradle plugin (`net.multigesture.kanama`, which adds
  `buildAndroidScripts`) and the Android runtime AAR (`kanama-android`),
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
`kanama-scripts.jar` and its version stamp, and, when a binary is provided,
launches Godot and checks that a pack export holds nothing from `build/`.
Add `--android` to also run `buildAndroidScripts` and a headless Android
export from the kit alone, and check the APK (no device). On a Mac, add
`--ios` to run `buildIosScripts`, a headless iOS export of an Xcode project,
and an unsigned `xcodebuild` of it for the device SDK (no device either).

Packaging builds the Android runtime AAR with the nested Android build, so it
needs the Android SDK (`ANDROID_HOME`) with the NDK 29.0.14206865 and CMake
3.22.1 packages ([Export for Android](../exporting/android.md#requirements)
lists the SDK packages; CMake is the runtime build's own). Without them, pass `-PkanamaAndroidPackageDir=<dir>`: a directory that
holds a `kanama-android` Maven tree of the same Kanama version
(`<dir>/net/multigesture/kanama/kanama-android/<version>/`), such as the
`addons/kanama/maven` of a kit built elsewhere, or the `kanama-android-maven`
artifact of a package workflow run. The package workflow builds the AAR once
and passes it to every kit job this way.

The macOS kit also carries the iOS runtime: the `iosArm64` klib of the runtime
(`kanama-iosarm64`, with its cinterop klib) and a zip of the C shim and its
headers (`kanama-ios-shim`), published by `publishKanamaIosPackage`. Kotlin/Native
builds them on a Mac only, so the Linux and Windows kits carry neither. A
store addon built off a Mac takes them from `-PkanamaIosPackageDir=<dir>`, a
directory with both Maven trees (`<dir>/net/multigesture/kanama/kanama-iosarm64/<version>/`
and `.../kanama-ios-shim/<version>/`), such as the `kanama-ios-maven` artifact
of a package workflow run.

### Store Addon

The store addon is intentionally safer for existing projects. It does not place
files at the project root. It contains:

- `addons/kanama`,
- `addons/kanama_tools`,
- all available desktop native bootstrap binaries under
  `addons/kanama/bin/<platform>/`,
- the local Maven repo under `addons/kanama/maven`, with the Kanama Gradle
  plugin and the Android runtime AAR as in the kit, and `kanama.version`,
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

## Mobile Exports

Neither mobile platform has a separate zip. Kits and the store addon carry the
Android runtime AAR, and the macOS kit and the store addon carry the iOS
runtime klib and shim; the Kanama Gradle plugin compiles a project's scripts
against them (`buildAndroidScripts`, `buildIosScripts`; see
[Android](backends/android.md#the-kit-path) and
[iOS](backends/ios.md#the-kit-path)).

## Package Workflow

The package workflow (`.github/workflows/package.yml`) runs only on manual
dispatch and `v*` tags. It does not run on pull requests. Its jobs:

- `desktop-kit` builds and smokes a desktop kit per platform, and the store
  native library for each.
- `cross-runtimes` builds the Windows and Linux game runtime images on macOS,
  and `exported-game` boots a real export with each image on its own runner,
  so cross-target export is proven on every tag.
- `store-addon` assembles and smokes the all-platform store addon.
- `publish-release`, on a tag only, creates the GitHub release if it does not
  exist and uploads the desktop kits and the store addon.

Matrix targets:

| Platform | Runner | Artifact classifier |
| --- | --- | --- |
| macOS arm64 | `macos-15` | `macos-arm64` |
| Linux x64 | `ubuntu-24.04` | `linux-x64` |
| Linux ARM64 | `ubuntu-24.04-arm` | `linux-arm64` |
| Windows x64 | `windows-2025` | `windows-x64` |

`android-aar` builds the Android runtime AAR once, on Linux, for every kit job
and the store addon. `android-export` then unzips the Linux kit and the store
addon into fresh projects and runs
`package_install_smoke.sh --android` on each: `buildAndroidScripts` and a
headless Godot export with a fresh `GRADLE_USER_HOME`, then the APK checks.
The macOS kit job also uploads the iOS runtime trees (`kanama-ios-maven`) for
the store addon, and `ios-export` runs `package_install_smoke.sh --ios` on
`macos-15` for the macOS kit and the store addon: `buildIosScripts`, a headless
export of an Xcode project, and `xcodebuild` for the device SDK without
signing, with the newest Xcode on the runner.

The release job grants `contents: write` only when publishing assets for a tag.
All other package jobs use read-only repository permissions.

## Native Bootstrap Libraries

Native bootstrap libraries are generated build artifacts. Source repositories
ignore `kanama_bootstrap.dll`, `libkanama_bootstrap.so`, and
`libkanama_bootstrap.dylib`; rebuild the matching library locally for the
platform under test instead of committing it.

## Support Levels

[Requirements and Platform Status](../reference/version-support.md#platform-status)
gives each platform one word:

- **Supported**: a user can build and ship their own game on it.
- **Experimental**: it runs the Kanama demos, and nothing is promised for a
  user's own game.

A platform moves to Supported only on evidence, on the current Godot pin:

- its smoke path passed: local CI and the desktop demo smokes for a desktop
  platform, the device matrix for a mobile one, the browser matrix for Web
  ([Device and Browser Runs](device-runs.md)), recorded in the
  `evidence/gates.json` ledger that the
  [Gates Index](../reference/generated/gates.md) shows;
- for a mobile platform, the bar Android and iOS met: device-matrix breadth
  (the demo matrix on more than one device model), renderer coverage,
  release-grade packaging (a release build runs, not only a debug one), and a
  heavy demo;
- a user's own project, not only a demo, builds and exports by following the
  user pages.

The runs, dates and devices go on [Platform Evidence](platform-evidence.md).
The status word then changes in the same pull request on Requirements and
Platform Status, in `README.md` and in `CHANGELOG.md`. Never word a claim
wider than the device or browser runs validated: a package zip that installs
is not proof that exported games work, and a desktop pass says nothing about
a device.

## Cutting a Release

The maintainer decides when a release is cut. The steps:

1. **Version.** Change `version` in `build.gradle.kts` together with the
   Gradle coordinates, docs snippets, demo project versions, the status badge
   in `README.md`, the package workflow variables and the companion demo
   defaults. Grep the repository for the old version before you finish.
2. **Changelog.** Turn `## Unreleased` into the version heading and open a new,
   empty `## Unreleased` above it: `check_public_signature_changes.py` counts no
   break as announced without one. Keep every `Source break` line.
3. **Gates.** Run `scripts/local_ci.sh` with the pinned Godot, then the
   isolated gates from clean clones: `scripts/fresh_clone_smoke.sh` and, for
   Web, `scripts/web_fresh_checkout_smoke.sh`. Smoke the packages as
   [Gates and Claims](gates.md#local-validation) shows.
4. **Evidence.** Check the [Gates Index](../reference/generated/gates.md)
   ledger: every local-only gate behind a Supported claim has a passing run on
   the current Godot pin. Run the ones that do not.
5. **Tag.** Push a `v<version>` tag. The package workflow builds and smokes
   every package and publishes the desktop kits and the store addon to the
   release.
6. **Check the release page.** Every platform's desktop kit and the store
   addon are attached. The workflow writes a one-line release note; the
   changelog section is the full record.

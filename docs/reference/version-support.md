# Version Support

Kanama `0.2.1` is the current public preview line. This page records the
platforms and engine versions validated for the current preview.

## Current Support Claims

| Target | Status | Notes |
| --- | --- | --- |
| Godot 4.7 beta 3, macOS arm64 | Baseline preview | API/header inputs, generated wrappers, KDoc, local CI, and desktop demo smokes target this preview baseline. |
| Android export, Godot 4.7 beta 3 | Experimental preview | Android toolchain uses Godot 4.7 beta 3 export templates with Android SDK API 36, build-tools 36.1.0, and NDK 29.0.14206865; matching export templates and emulator/Pixel 7 smoke remain validation gates. |
| Linux arm64 | Pending beta 3 revalidation | Last local runtime, editor, and demo smoke validation passed with the 4.7 beta 2 ARM64 binary. Packaged desktop exports remain a separate release-readiness track. |
| Linux x86_64 | Pending beta 3 revalidation | Last local runtime, editor, and demo smoke validation passed with the 4.7 beta 2 x64 binary. Packaged desktop exports remain a separate release-readiness track. |
| Windows x86_64 | Pending beta 3 revalidation | Last local runtime/editor smoke validation passed with the 4.7 beta 2 console binary. PowerShell Gradle commands and Git Bash smoke marker checks are the documented path. |
| iOS | Unsupported | No current runtime path. |
| Web | Not planned | Kanama depends on a JVM/FFM-style runtime path. |

Validated support is only claimed after the matching smoke path passes.
Use the
[Godot 4.7 beta 3 archive](https://godotengine.org/download/archive/4.7-beta3/)
for compatible desktop binaries and Android export templates.

## API Baseline

The checked-in wrapper surface is generated from the current
`extension_api.json`, `gdextension_interface.h`, generated Panama bindings, and
Kotlin wrapper sources. For this preview, that API baseline is Godot 4.7 beta
3.

## Kanama Version

The current Gradle artifact version is `0.2.1`.

Release version changes should be paired with matching Gradle coordinates, docs
snippets, demo project versions, badges, changelog headings, and a passing
smoke matrix for every claimed target.

## Android

Android is tracked separately from the desktop matrix. The current Android path
uses:

- Godot 4.7 beta 3 Android export,
- Android SDK API 36, build-tools 36.1.0, and NDK 29.0.14206865 for the
  matching Godot export templates,
- a Godot Android plugin AAR,
- Android ART,
- [PanamaPort](https://github.com/vova7878/PanamaPort),
- emulator smoke tests, and
- Pixel 7 smoke and playability checks for the Android-enabled demos.

See [Android Experimental](../exporting/android.md) for the build/export
workflow and [Android Internals](../contributing/android-internals.md) for
implementation details.

## Local Validation

Run the local CI shortcut with one or more Godot binaries:

```sh
scripts/local_ci.sh /absolute/path/to/godot-4.7-beta3
```

Before tagging a release, run the isolated source-and-demo gate from a clean
temporary clone:

```sh
scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7-beta3
```

```sh
scripts/local_ci.sh \
  /absolute/path/to/godot-4.7-beta3 \
  /absolute/path/to/godot-4.7-stable
```

The script runs the main local checks:

- API constant validation against `extension_api.json`,
- Gradle jar sync,
- native bootstrap configure/build when CMake is available,
- strict docs build when `mkdocs` is available,
- runtime smoke through `scripts/runtime_smoke.sh`,
- `@Tool` editor execution smoke, and
- hot-reload smoke.

`runtime_smoke.sh` checks the core Kanama runtime path: Godot loads the
GDExtension, starts the JVM, registers the script language/resource loader,
loads Kotlin scripts, and runs the example project far enough to verify the
expected markers.

For Linux desktop validation, use a matching Linux Godot binary from the Godot
4.7 beta 3 archive and set `JAVA_HOME` to JDK 25+:

```sh
cd /path/to/kanama-demos
JAVA_HOME=/path/to/jdk-25 \
XDG_DATA_HOME=/tmp/kanama-godot-state-linux \
KANAMA_DESKTOP_SMOKE_LOG_DIR=/tmp/kanama-desktop-smokes-linux \
scripts/desktop_smoke_all.sh /path/to/Godot_v4.7-beta3_linux.arm64
```

Use the Godot binary for the architecture under test, such as
`Godot_v4.7-beta3_linux.arm64` or
`Godot_v4.7-beta3_linux.x86_64`. The demo smoke script uses Godot's OpenGL
Compatibility renderer on desktop. Before refreshing demo addons, rebuild the
native bootstrap from a clean checkout and preflight `libkanama_bootstrap.so`
with `file`, `ldd`, and `readelf`.

For Windows validation, launch Godot from an environment with `JAVA_HOME` set
to JDK 25+ so Kanama can load `%JAVA_HOME%\bin\server\jvm.dll`. Build the
native bootstrap from a Visual Studio 2022 C++ developer environment, and use
PowerShell/`.\gradlew.bat` for Gradle commands. Existing Bash smoke scripts can
hit CRLF-related false negatives on Windows; use the 4.7 beta 3 console binary,
PowerShell Gradle commands, and Git Bash smoke marker checks for the documented
path.

Install docs dependencies with:

```sh
pip install -r docs/requirements.txt
```

## Godot Upgrade Checklist

For a new Godot version:

1. Refresh API/header inputs and regenerated Panama bindings:

   ```sh
   scripts/refresh_godot_api.sh /absolute/path/to/new_godot_binary
   ```

2. Review changes in `extension_api.json`, `gdextension_interface.h`, generated
   bindings, generated wrappers, and generated KDoc.
3. Regenerate wrapper coverage reports:

   ```sh
   python3 scripts/api_wrapper_generator_report.py --markdown docs/reference/wrapper-generator-report.md
   python3 scripts/api_wrapper_coverage.py --markdown docs/reference/api-coverage.md
   ```

4. Validate API metadata and wrapper policy:

   ```sh
   python3 scripts/validate_godot_api.py --api extension_api.json
   python3 scripts/check_wrapper_generator.py
   ```

5. Run local CI against the current baseline and any candidate Godot binary.
6. Run relevant demo smoke checks before changing public support claims.

## Compatibility Gates

Treat these as high-risk when moving to a new Godot version:

- GDExtension struct layouts.
- Script language and script instance virtual method surfaces.
- MethodBind and utility-function hashes.
- Builtin method hashes and constructor indexes.
- `VariantType` enum ids.
- Builtin type sizes such as Variant and GDExtensionCallError.
- Generated header function names such as `classdb_register_extension_class6`.
- `.gdextension` compatibility metadata.
- Engine singleton lifetime policy. Godot 4.7 beta 3 warns when
  `Engine.register_singleton` receives a `RefCounted`; Kanama rejects this in
  `Engine.registerSingleton` and audits built-in singletons so singleton
  handles remain engine-owned `Object` instances.

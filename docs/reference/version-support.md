# Version Support

Kanama `0.3.0` is the current public release (a pre-1.0 preview baseline). This
page records the platforms and engine versions validated for it.

## Current Support Claims

| Target | Status | Notes |
| --- | --- | --- |
| Godot 4.7 stable, macOS arm64 | Supported (4.7 stable) | API/header inputs, generated wrappers, KDoc, local CI, and desktop demo smokes target this baseline; `runtime_smoke.sh` passed against the 4.7 stable binary (2026-06-21). Primary supported runtime and package target. |
| Android export, Godot 4.7 stable | Supported (4.7 stable) | Promoted from Experimental 2026-07-14 (§7 mobile promotion bar B1–B4 MET). Device-validated across four models: **Pixel 7** (Android 16) + **Moto g 5G 2023** (Android 14) pass the full gate (nine-demo debug matrix + R8-minified Match3 release); **Galaxy S10+** (Android 12) and **Pixel 3 XL** (Android 9, Vulkan/Mobile) add debug breadth. **Min-version: debug validated to Android 9; release builds require Android 13+** (validated 14/16 — on Android 12 and below a PanamaPort release-mode FFI constraint blocks release; documented in exporting/android.md). Demos ship OpenGL Compatibility by default; the nine-demo **Vulkan/Mobile renderer** smoke also passes on Pixel 7. `scripts/android_smoke.sh` passes on the API 36 emulator. Toolchain: SDK API 36, build-tools 36.1.0, NDK 29.0.14206865. Packaged addon is runtime-only (compiling project scripts needs the Kanama checkout; debug AAR only). The R8/release path is tied to the PanamaPort fork `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`, not upstream. No mobile hot reload. |
| Linux arm64 | Supported (4.7 stable) | Full local CI, native bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop smoke matrix, TPS checked smoke, distribution packaging, and desktop-kit/store-addon install smokes passed on Ubuntu 26.04 with Godot `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.3 (native AArch64, 2026-07-14). The resource-loader/saver teardown fix is required. Packaged desktop exports remain a separate release-readiness track. |
| Linux x86_64 | Supported (4.7 stable) | Full local CI, native bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop smoke matrix, TPS checked smoke, distribution packaging, and desktop-kit/store-addon install smokes passed on Ubuntu 25.04 with Godot `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.2 (2026-07-13/14). The resource-loader/saver teardown fix is required. Packaged desktop exports remain a separate release-readiness track. |
| Windows x86_64 | Supported (4.7 stable) | Full local revalidation on the 4.7 stable console binary (2026-07-13): demo audits, script builds, imports, the nine-demo desktop runtime smoke, the TPS smoke, and the packaged desktop-kit + store-addon install smokes all passed. Gradle commands that build the native bootstrap run from a VS 2022 developer environment (VsDevCmd); Git Bash runs the smoke scripts. |
| iOS (Kotlin/Native backend) | Supported (4.7 stable) | Promoted from Experimental 2026-07-14 (§7 mobile promotion bar B1–B4 MET). The iOS backend runs full Kanama project scripts via a C shim + Kotlin/Native static `.xcframework`, using the same wrapper generator as desktop/Android (no JVM on device). Full device gate (9-demo matrix + fresh-project install path) passed on two models: **iPhone 12** (iOS 26.5, 2026-06-25; 0 guardrail failures) + **iPhone 15 Pro** (iOS 26.5, 2026-07-10, full-breadth wrapper runtime), both on 4.7 stable iOS templates. Packaged `.xcframework` addon is runtime-only (compiling project scripts needs the Kanama checkout; ~199.5 MB debug / ~87.6 MB release static `.a`). No mobile hot reload. One FPS Audio autoload follow-up + task-26 multiplayer UI polish tracked as non-blocking — see [exporting/ios.md](../exporting/ios.md). |
| Web | In development (4.7 stable) | Kotlin/Wasm backend (no on-device JVM); runs the Bunnymark and Match3 production exports in Chrome/Firefox/Safari through a generated proxy + versioned JS bridge. **Not a Supported target: no packaged export workflow, no export guide, single-thread Compatibility renderer only.** See §Web below. |

Validated support is only claimed after the matching smoke path passes.
Use the
[Godot 4.7 stable archive](https://godotengine.org/download/archive/4.7-stable/)
for compatible desktop binaries and Android export templates.

## API Baseline

The checked-in wrapper surface is generated from the current
`extension_api.json`, `gdextension_interface.h`, generated Panama bindings, and
Kotlin wrapper sources. For this preview, that API baseline is Godot 4.7
stable.

The 4.7 rc 2 → 4.7 stable bump was a metadata-only change: the dumped
`extension_api.json` (excluding the `version_status` header field) and
`gdextension_interface.h` are **byte-identical** between the two builds, so the
generated wrappers, Panama bindings, name constants, and struct layouts are
unchanged and the rc 2 validation carries over. `scripts/runtime_smoke.sh`
passed against the macOS arm64 4.7 stable binary
(`4.7.stable.official.5b4e0cb0f`) on 2026-06-21. iOS (iPhone 12 self-test) and
Android (`android_smoke.sh` on the API 36 emulator, Starter-Kit-Match3) also
re-passed against 4.7 stable templates the same day. The Pixel 7 hardware
device gate (debug demo matrix + R8-minified Match3 release APK) has passed
(2026-06-26). Windows x86_64 completed its full local 4.7-stable revalidation
(2026-07-13); Linux x86_64 and arm64 both re-passed the full local CI, demo,
TPS, package, and native-artifact gates against Godot 4.7 stable (x86_64 on
2026-07-13/14, arm64 on 2026-07-14 on a native AArch64 Ubuntu 26.04 host).

## Kanama Version

The current Gradle artifact version is `0.3.0`.

Release version changes should be paired with matching Gradle coordinates, docs
snippets, demo project versions, badges, changelog headings, and a passing
smoke matrix for every claimed target.

## Build Toolchain

The current source build uses Kotlin `2.3.21`, KSP `2.3.9`, and
kotlinx.coroutines `1.11.0`. Gradle build cache is enabled for the main Kanama
build and the Android plugin build.

## Android

Android is tracked separately from the desktop matrix. The current Android path
uses:

- Godot 4.7 stable Android export,
- Android SDK API 36, build-tools 36.1.0, and NDK 29.0.14206865 for the
  matching Godot export templates,
- a Godot Android plugin AAR,
- Android ART,
- [PanamaPort](https://github.com/vova7878/PanamaPort),
- emulator smoke tests, and
- Pixel 7 device smoke and playability checks for the Android-enabled demos.

The Godot 4.7 stable Android emulator smoke path and the Pixel 7 device gate
(debug demo matrix + R8-minified Match3 release APK) have both passed, and the
nine-demo Vulkan/Mobile renderer smoke matrix passed on Pixel 7 (2026-07-10)
with a per-demo renderer-init assertion. Android is **Supported (4.7 stable)**:
device-validated across four models (Android 9/12/14/16), with debug validated
to Android 9 and release builds requiring Android 13+. The R8/release path
depends on Kanama's PanamaPort fork rather than upstream.

See [Android](../exporting/android.md) for the build/export
workflow and [Android Internals](../contributing/android-internals.md) for
implementation details.

## iOS

iOS runs full Kanama project scripts on the Kotlin/Native backend and is
**Supported (4.7 stable)**. The backend uses a static `.xcframework`, a C
GDExtension shim, and a Kotlin/Native runtime, with GENERATED Godot API wrappers (the
same generator as desktop/Android) over a C-shim generic ptrcall. The current iOS demo
corpus has playable device runs; per-frame Kanama script+binding overhead is ~0.63 ms
on iPhone 12. Physical-device export and launch are the validation target; simulator
runs are optional compile/link checks and not a frame-rate signal.

iOS is Supported on 4.7 stable, with documented mobile caveats: the packaged `.xcframework`
addon is runtime-only (compiling project scripts needs the Kanama checkout), there is no mobile
hot reload, and the FPS Audio autoload follow-up + task-26 multiplayer UI polish are tracked as
non-blocking.

See the [iOS export workflow](../exporting/ios.md) and the
[iOS backend architecture](../internals/reference/ios-backend-architecture.md) (guardrails, how it
stays in sync with desktop/Android).

## Web

Web is **In development** on the Godot 4.7 stable baseline. It is **not a
Supported target**: there is no packaged export workflow, no user-facing export
guide, and no support claim. This supersedes the earlier note that ruled Web
out entirely.

Unlike desktop/Android/iOS, the Web backend does **not** use a JVM or an
FFM/PanamaPort path. It is a **Kotlin/Wasm** backend: project gameplay compiles
to WebAssembly and talks to the Godot 4.7 Web export (Emscripten/Wasm) through a
generated per-call proxy and a versioned JavaScript bridge
(`web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js`, protocol version
6). The typed backend seam is shared with the other platforms through
`scripts/platform_backend_calls.json`, and
`scripts/generate_web_gameplay_coverage.py` fails loudly if a call the demo
executes has no admitted backend family. See
[Web Internals](../contributing/web-internals.md) for the architecture.

### Validated evidence

Two production Godot Web exports pass an automated, assertion-driven play
sequence — not a page-load check — in three browsers:

- **Match3** (`Starter-Kit-Match3`): original board, a runtime-selected legal
  swap, match/collapse/refill, particles, audio, restart, and two full
  zero-state teardowns.
- **Bunnymark**: 256 sprites, one bounded position batch, and deterministic
  257-to-zero handle teardown.

Browsers (headless): **Chrome 150**, **Firefox 153**, and **Safari 26.5**. Each
run asserts gameplay deltas, crossing budgets, and handle/callback/scheduler
teardown to baseline, and rejects stale handles. Gameplay coverage reports zero
blocking calls; `GodotObject.emit_signal_typed` remains visible as one explicit
nonblocking unsupported family rather than being pattern-hidden.

### Explicit non-support limitations

- No packaged/user export workflow and no `exporting/web.md` guide yet.
- Godot **Compatibility renderer, single-thread** only.
- No Web editor, no hot reload, no threads, no Kotlin/JS path.
- Reproducible export builds currently require
  `--no-daemon -Pkotlin.compiler.execution.strategy=in-process` (Kotlin daemon
  builds exhausted memory).

## Local Validation

Run the local CI shortcut with one or more Godot binaries:

```sh
scripts/local_ci.sh /absolute/path/to/godot-4.7-stable
```

Before tagging a release, run the isolated source-and-demo gate from a clean
temporary clone:

```sh
scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7-stable
```

For package validation, build the packaged artifacts and smoke them without a
sibling Kanama checkout:

```sh
./gradlew packageDistributions
scripts/package_install_smoke.sh \
  --desktop-kit \
  build/distributions/kanama-desktop-kit-v<version>-<platform>.zip \
  /absolute/path/to/godot-4.7-stable
scripts/package_install_smoke.sh \
  --store-addon \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7-stable
```

```sh
scripts/local_ci.sh \
  /absolute/path/to/godot-4.7-stable \
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
4.7 stable archive and set `JAVA_HOME` to JDK 25+:

```sh
cd /path/to/kanama-demos
JAVA_HOME=/path/to/jdk-25 \
XDG_DATA_HOME=/tmp/kanama-godot-state-linux \
XDG_CONFIG_HOME=/tmp/kanama-godot-config-linux \
KANAMA_DESKTOP_SMOKE_LOG_DIR=/tmp/kanama-desktop-smokes-linux \
scripts/desktop_smoke_all.sh /path/to/Godot_v4.7-stable_linux.arm64
```

(`desktop_smoke_all.sh` defaults both XDG variables to those paths on Linux; set
them explicitly only to relocate the isolated state.)

Use the Godot binary for the architecture under test, such as
`Godot_v4.7-stable_linux.arm64` or
`Godot_v4.7-stable_linux.x86_64`. The demo smoke script uses Godot's OpenGL
Compatibility renderer on desktop. Before refreshing demo addons, rebuild the
native bootstrap from a clean checkout and preflight `libkanama_bootstrap.so`
with `file`, `ldd`, and `readelf`.

For Windows validation, launch Godot from an environment with `JAVA_HOME` set
to JDK 25+ so Kanama can load `%JAVA_HOME%\bin\server\jvm.dll`, and use the 4.7
stable **console** binary so smoke markers reach the terminal.

The native bootstrap needs CMake + MSVC, which are not on the default
PowerShell/Git Bash `PATH`: wrap every Gradle command that builds demos in
`VsDevCmd.bat` (a plain `gradlew.bat buildAllScripts` fails on the nested
native bootstrap otherwise):

```bat
cmd /c "call ""C:\Program Files\Microsoft Visual Studio\2022\Community\Common7\Tools\VsDevCmd.bat"" -arch=x64 -host_arch=x64 && .\gradlew.bat buildAllScripts -PkanamaRoot=C:\path\to\kanama"
```

Point the demo builds at the Godot console binary either with the `KANAMA_GODOT`
environment variable or with `-Pkanama.godot.executable=...` (the root aggregate
tasks forward the property to nested demo builds):

```bat
cmd /c "call ""...\VsDevCmd.bat"" -arch=x64 -host_arch=x64 && .\gradlew.bat importAllGodot -PkanamaRoot=C:\path\to\kanama -Pkanama.godot.executable=C:\path\to\Godot_v4.7-stable_win64_console.exe"
```

Run the demo smoke matrix through Git Bash:

```bat
cmd /c "set KANAMA_DESKTOP_SMOKE_LOG_DIR=%TEMP%\kanama-desktop-smokes&& ""C:\Program Files\Git\bin\bash.exe"" scripts/desktop_smoke_all.sh C:\path\to\Godot_v4.7-stable_win64_console.exe"
```

The Gradle audit tasks resolve Python as `py`/`python` on Windows (the
`python3` name usually hits the inert Microsoft Store shim). Keep
`core.autocrlf` disabled (the demo repos normalize to LF via `.gitattributes`);
CRLF-rewritten `.tres`/`.tscn` files fail Godot resource parsing.

Install docs dependencies with:

```sh
pip install -r docs/requirements.txt
```

## Godot Upgrade

Kanama re-pins only on stable Godot releases. The single authoritative process
for bumping the baseline — the pin edit, the mechanical
`scripts/upgrade_godot.sh` pipeline, the gates, and the human-judgment steps —
is the [Godot Upgrade Runbook](../contributing/godot-upgrade.md). Support
claims never move before the matching smoke/device gates pass on the new
baseline.

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
- Engine singleton lifetime policy. Godot 4.7 preview builds warn when
  `Engine.register_singleton` receives a `RefCounted`; Kanama rejects this in
  `Engine.registerSingleton` and audits built-in singletons so singleton
  handles remain engine-owned `Object` instances.

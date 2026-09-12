# Version Support

Kanama `0.4.0` is the current public release (a pre-1.0 preview baseline). This
page is the single owner of two kinds of fact: what Kanama **requires** (the
Godot pin, JDKs, host platforms, per-workflow toolchains) and what each
platform's **status** is, with the evidence behind it. Other pages — the
README, the docs home, the getting-started and export guides, the packaged
template READMEs — link here rather than restating either.

## Requirements

| Workflow | Needs |
| --- | --- |
| Desktop project (release kit, store addon, or source checkout) | **Godot 4.7.2 stable** from the [Godot 4.7.2 stable archive](https://godotengine.org/download/archive/4.7.2-stable/) (editor/player binaries and export templates); **JDK 25+** (Temurin 25 recommended) for the desktop runtime and Gradle builds; a host on **macOS arm64, Windows x64, Linux x64, or Linux arm64** — the current desktop package and smoke targets. |
| Source checkout or contributor checkout | The desktop row plus **CMake 3.22+** and the platform C toolchain, because these workflows build the native bootstrap locally (`bootstrap/CMakeLists.txt`). Release kits and store add-ons ship the prebuilt bootstrap, so they do not need CMake. Building Kanama does not require a Godot source checkout; the pinned GDExtension headers are tracked in this repository. |
| Android export | Godot 4.7.2 stable **Android export templates** from the same archive; **JDK 21** for Godot's Android Gradle export flow (Kanama itself still builds with JDK 25); the Android SDK platform, build-tools, NDK, and CMake versions in the [Android toolchain table](../exporting/android.md#toolchain), which that page owns. |
| iOS export | Godot 4.7.2 stable **iOS export templates**; a full Xcode install (`DEVELOPER_DIR`) and Apple signing — versions and setup in the [iOS toolchain table](../exporting/ios.md#toolchain). |
| Web export | Godot 4.7.2 stable editor plus the `web_nothreads_release` template for that exact version; a Kanama source checkout (there is no packaged Web addon); Node.js only for the smoke harness — see [Web → Requirements](../exporting/web.md#requirements). |

The Godot pin is `kanamaGodotVersion` in `gradle.properties`;
`scripts/check_godot_version_pin.py` fails `local_ci.sh` when a workflow or
build file drifts from it, and the [Godot Upgrade Runbook](../contributing/godot-upgrade.md)
is the only process that moves it. The JDK floor is the Gradle toolchain
(`jvmToolchain(25)` in `build.gradle.kts`).

## Current Support Claims

| Target | Status | Notes |
| --- | --- | --- |
| Godot 4.7 stable, macOS arm64 | Supported (4.7 stable) | API/header inputs, generated wrappers, KDoc, local CI, and desktop demo smokes target this baseline; `runtime_smoke.sh` passed against the 4.7 stable binary (2026-06-21). Primary supported runtime and package target. **Exported-game validation (2026-09-10):** `scripts/export_game_smoke.sh` passed twice on macOS arm64 (Godot `4.7.2.stable`, Temurin 25.0.4.1+1) — once against a host-jlinked runtime image and once against one linked from the pinned `macos-arm64` Temurin jmods (31.7 MB either way). The exported `.app` booted headless with `JAVA_HOME` unset and `PATH` stripped of any JDK, from its own bundled runtime (`[kanama] using libjvm: <app>/Contents/Resources/runtime/lib/server/libjvm.dylib`), constructed a script (`SelfSmoke self_class=Node3D same_object=true`) and tore down clean (23/23 tracked objects destroyed, 6 extension classes unregistered). Not yet double-clicked on a second Mac with no JDK installed. Distribution signing/notarization of the bundled runtime remains a separate release-readiness track — the export is ad-hoc signed and carries only the JIT/library-validation entitlements the embedded JVM needs (see [Desktop and Packaging](../exporting/desktop.md#macos-bundle-layout-entitlements-and-signing)). |
| Android export, Godot 4.7 stable | Supported (4.7 stable) | Promoted from Experimental 2026-07-14 (§7 mobile promotion bar B1–B4 MET). Device-validated across four models: **Pixel 7** (Android 16) + **Moto g 5G 2023** (Android 14) pass the full gate (nine-demo debug matrix + R8-minified Match3 release); **Galaxy S10+** (Android 12) and **Pixel 3 XL** (Android 9, Vulkan/Mobile) add debug breadth. **Min-version: debug validated to Android 9; release builds require Android 13+** (validated 14/16 — on Android 12 and below a PanamaPort release-mode FFI constraint blocks release; see [Validated Android versions](#validated-android-versions) below). Demos ship OpenGL Compatibility by default; the nine-demo **Vulkan/Mobile renderer** smoke also passes on Pixel 7. `scripts/android_smoke.sh` passes on the API 36 emulator. Toolchain: [Android → Toolchain](../exporting/android.md#toolchain). Packaged addon is runtime-only (compiling project scripts needs the Kanama checkout; debug AAR only). The R8/release path is tied to the PanamaPort fork `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`, not upstream. No mobile hot reload. |
| Linux arm64 | Supported (4.7 stable) | Full local CI, native bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop smoke matrix, TPS checked smoke, distribution packaging, and desktop-kit/store-addon install smokes passed on Ubuntu 26.04 with Godot `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.3 (native AArch64, 2026-07-14). The resource-loader/saver teardown fix is required. Exported games bundle a jlink runtime and the runtime is cross-target (see [Desktop and Packaging](../exporting/desktop.md)); distribution signing/notarization remains a separate release-readiness track. |
| Linux x86_64 | Supported (4.7 stable) | Full local CI, native bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop smoke matrix, TPS checked smoke, distribution packaging, and desktop-kit/store-addon install smokes passed on Ubuntu 25.04 with Godot `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.2 (2026-07-13/14). The resource-loader/saver teardown fix is required. Exported games bundle a jlink runtime and the runtime is cross-target (see [Desktop and Packaging](../exporting/desktop.md)); distribution signing/notarization remains a separate release-readiness track. |
| Windows x86_64 | Supported (4.7 stable) | Full local revalidation on the 4.7 stable console binary (2026-07-13): demo audits, script builds, imports, the nine-demo desktop runtime smoke, the TPS smoke, and the packaged desktop-kit + store-addon install smokes all passed. Gradle commands that build the native bootstrap run from a VS 2022 developer environment (VsDevCmd); Git Bash runs the smoke scripts. **Exported-game validation (2026-08-10):** a game exported with a jlink runtime **cross-built on macOS arm64** was run on real Windows hardware and booted from its own bundled runtime — decisive because that machine has a JDK installed and the app-relative probe still won (`[kanama] using libjvm: <export>\\runtime\\bin\\server\\jvm.dll`, with no `checked JAVA_HOME` line). Real GPU path exercised (`Vulkan 1.2.175 - Forward+`, NVIDIA GTX 670), no VC++/CRT redistributable required, and a clean teardown (22/22 scripts and 221/221 StringNames released, script language unregistered with `Error=0`). |
| iOS (Kotlin/Native backend) | Supported (4.7 stable) | Promoted from Experimental 2026-07-14 (§7 mobile promotion bar B1–B4 MET). The iOS backend runs full Kanama project scripts via a C shim + Kotlin/Native static `.xcframework`, using the same wrapper generator as desktop/Android (no JVM on device). Full device gate (9-demo matrix + fresh-project install path) passed on two models: **iPhone 12** (iOS 26.5, 2026-06-25; 0 guardrail failures) + **iPhone 15 Pro** (iOS 26.5, 2026-07-10, full-breadth wrapper runtime), both on 4.7 stable iOS templates. Packaged `.xcframework` addon is runtime-only (compiling project scripts needs the Kanama checkout; ~199.5 MB debug / ~87.6 MB release static `.a`). No mobile hot reload. One FPS Audio autoload follow-up + task-26 multiplayer UI polish tracked as non-blocking — see [exporting/ios.md](../exporting/ios.md). |
| Web | Experimental (4.7 stable) | Kotlin/Wasm backend (no on-device JVM); runs a twelve-demo production-export corpus in Chrome/Firefox/Safari through a generated proxy + versioned JS bridge, with a reproducible source-checkout export workflow ([guide](../exporting/web.md)). **Not a Supported target: source-checkout export only (no packaged addon), single-thread Compatibility renderer, no multiplayer, no hot reload, desktop browsers only (iOS/iPadOS hand-checked on device, not gated).** See §Web below. |

Validated support is only claimed after the matching smoke path passes.
Use the
[Godot 4.7.2 stable archive](https://godotengine.org/download/archive/4.7.2-stable/)
for compatible desktop binaries and Android export templates.

## API Baseline

The checked-in wrapper surface is generated from the current
`extension_api.json`, `gdextension_interface.h`, generated Panama bindings, and
Kotlin wrapper sources. For this preview, that API baseline is Godot 4.7.2
stable.

Kanama re-pinned from 4.7.0 to **4.7.2 stable on 2026-09-08** (task 91).
`scripts/upgrade_godot.sh` classified the bump as **metadata-only**: only the
`extension_api.json` header changed (`version_patch` 0 → 2); the API body and
`gdextension_interface.h` are byte-identical, so wrappers, name constants, and
struct layouts are unchanged and the regen churn was version strings plus a
KDoc re-sync from the 4.7.2 `doc/classes`. Desktop gates re-ran on the 4.7.2
binary the same day: `scripts/local_ci.sh` passed end to end on macOS arm64
(runtime, `@Tool`, hot-reload, and in-process hot-reload smokes included), and
the demos `desktop_smoke_all.sh` nine-demo matrix passed. **Not re-run on 4.7.2
templates:** the Android and iOS device gates and the Web browser matrix; the
mobile and Web evidence below stays dated on the 4.7.0 templates until they
are. Godot's Android export-template toolchain (`config.gradle`) is identical
between the two tags, so the toolchain table in [Android](../exporting/android.md)
holds for 4.7.2.

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

The current Gradle artifact version is `0.4.0`.

Release version changes should be paired with matching Gradle coordinates, docs
snippets, demo project versions, badges, changelog headings, and a passing
smoke matrix for every claimed target.

## Build Toolchain

The current source build uses Kotlin `2.3.21`, KSP `2.3.9`, and
kotlinx.coroutines `1.11.0`. Gradle build cache is enabled for the main Kanama
build and the Android plugin build.

## Android

Android is tracked separately from the desktop matrix. The current Android path
uses the Godot 4.7.2 stable Android export (templates and toolchain: the
[Requirements](#requirements) row and the [Android toolchain table](../exporting/android.md#toolchain)),
a Godot Android plugin AAR, Android ART,
[PanamaPort](https://github.com/vova7878/PanamaPort), emulator smoke tests, and
device smoke and playability checks for the Android-enabled demos.

The Godot 4.7 stable Android emulator smoke path and the Pixel 7 device gate
(debug demo matrix + R8-minified Match3 release APK) have both passed, and the
nine-demo Vulkan/Mobile renderer smoke matrix passed on Pixel 7 (2026-07-10)
with a per-demo renderer-init assertion. Android is **Supported (4.7 stable)**:
device-validated across four models (Android 9/12/14/16), with debug validated
to Android 9 and release builds requiring Android 13+. The R8/release path
depends on Kanama's PanamaPort fork rather than upstream.

### Android demo matrix

The nine public demo exports (the eight below plus Bunnymark) are the Android
smoke targets. On 2026-06-26 the full Godot 4.7 stable matrix passed on a
Pixel 7 using debug APK exports, logcat startup checks, and screenshot smoke
checks. The R8-minified Match3 release APK also passed on Pixel 7 when built
against Kanama's PanamaPort fork
(`com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4`); the
minified-release claim is tied to that forked dependency, not upstream
PanamaPort `v0.1.3`.

| Demo | Result |
|---|---|
| `godot-demo-2d-dodge-the-creeps` | APK exports, launches, initializes Kanama, and handles D-pad input. |
| `Starter-Kit-3D-Platformer` | APK exports, launches, loads seven Kotlin scripts, reaches the main loop, renders the 3D scene, and runs gameplay logic with mobile controls. |
| `Starter-Kit-Match3` | APK exports, launches, initializes Kanama, and passes startup/screenshot smoke checks. |
| `godot-demo-3d-squash-the-creeps` | APK exports, launches, initializes Kanama, and passes startup/screenshot smoke checks. |
| `Starter-Kit-FPS` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with touch-control coverage. |
| `Starter-Kit-Racing` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with mobile steering controls. |
| `godot-4-3d-character-controller-tutorial` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with virtual joystick controls. |
| `godot-4-3d-third-person-controller` | APK exports, launches, initializes Kanama, reaches the main loop, and passes screenshot smoke checks with virtual joystick controls. |

On 2026-07-10 the same nine-demo matrix also passed under Godot's
**Vulkan/Mobile renderer** on the Pixel 7 (Mali-G710, Android 16): each demo
was exported with `rendering_method.mobile="mobile"`, and the smoke asserted
the renderer actually initialized (`Vulkan 1.4.305 - Forward Mobile` in logcat
— a silent OpenGL fallback fails the check) alongside the normal Kanama
startup and screenshot checks. The demo corpus ships OpenGL Compatibility as
its default mobile renderer; both renderers are smoke-validated on Pixel 7,
while gameplay/visual parity under Vulkan beyond the smoke bar remains
per-demo validation. These passes are the evidence behind the Supported
claim; they are not a claim of per-demo mobile polish, and broader input
coverage would still strengthen the claim.

### Validated Android versions

The plugin's declared `minSdk` is 26, but the *validated* floor is
build-type-specific (2026-07-13 four-model matrix — Pixel 3 XL / Galaxy S10+ /
Moto g 5G 2023 / Pixel 7, Android 9/12/14/16):

| Build type | Floor | Evidence |
|---|---|---|
| **Debug** (`--export-debug`) | **Android 9** (API 28) | Nine-demo matrix green on Android 12 + 14 + 16; Android 9 validated via the dodge smoke (Vulkan/Mobile renderer — the factory OpenGL driver on that device crashes before Kanama runs; see [Android → Boundaries And Troubleshooting](../exporting/android.md#boundaries-and-troubleshooting)) |
| **Release** (`--export-release`, minified or not) | **Android 13** (API 33); validated on 14 + 16 | On Android 12 and below, release-mode (`android:debuggable=false`) builds crash during PanamaPort's upcall-stub generation into the system `libLLVM_android.so` (argument marshalling lands shifted under release-mode ART's invocation of PanamaPort's runtime-bound native methods; debug-mode ART invokes them compatibly). Android 13 replaced that code path. Characterized with a full tombstone 2026-07-13; the fix is upstream-shaped and deferred |

Pre-16 devices additionally require fork `0.1.3-kanama-r8.3`+ (the
`SDK_INT_FULL` bootstrap guard) — older fork versions and upstream PanamaPort
fail the FFI bootstrap on every device below Android 16.

See [Android](../exporting/android.md) for the build/export workflow and
troubleshooting, and [Android Internals](../contributing/backends/android.md)
for the runtime design, the source-remap audits, and the PanamaPort fork
history.

## iOS

iOS runs full Kanama project scripts on the Kotlin/Native backend and is
**Supported (4.7 stable)**. The backend uses a static `.xcframework`, a C
GDExtension shim, and a Kotlin/Native runtime, with GENERATED Godot API wrappers (the
same generator as desktop/Android) over a C-shim generic ptrcall. Physical-device
export and launch are the validation target; simulator runs are optional compile/link
checks and not a frame-rate signal.

The evidence is the **full ten-step device gate** (`scripts/ios_device_gate.sh`: the
fresh-project install path plus the nine-demo matrix; since task 105 each demo step also
streams the device console for 30 s after launch and fails on a crash signature, so a crash
inside that window is no longer invisible), validated on two physical
models — **iPhone 12** (iOS 26.5, 2026-06-25; 0 guardrail hits, per-frame Kanama
script+binding overhead about 0.63 ms/frame measured there) and **iPhone 15 Pro**
(iOS 26.5, 2026-07-10, on the full-breadth generated-wrapper runtime). The claim
names those two models; it is not a device-family ("recent iPhones") claim. The
playable demo set matches the Android-enabled public demo set plus Bunnymark, and
the heavy `tps-demo-kanama` also runs on device (the audited type set and KSP
registration path cover the whole corpus); its mobile touch/multiplayer UI polish
(task 26) is tracked separately, and FPS is playable with an intermittent Audio
autoload follow-up.

iOS is Supported on 4.7 stable, with documented mobile caveats: the packaged `.xcframework`
addon is runtime-only (compiling project scripts needs the Kanama checkout), there is no mobile
hot reload, and the FPS Audio autoload follow-up + task-26 multiplayer UI polish are tracked as
non-blocking.

See the [iOS export workflow](../exporting/ios.md) and the
[iOS backend architecture](../contributing/backends/ios.md) (guardrails, how it
stays in sync with desktop/Android).

## Web

Web is **Experimental (Kotlin/Wasm preview)** on the Godot 4.7 stable baseline. It
is **not a Supported target**: it is a source-checkout export (no packaged addon),
single-thread Compatibility renderer only, and makes no support claim. A
user-facing export guide is at [Exporting → Web](../exporting/web.md). The
twelve-demo corpus passes the automated production export smoke in Chrome,
Firefox, and Safari. This supersedes the earlier note that ruled Web out
entirely.

Unlike desktop/Android/iOS, the Web backend does **not** use a JVM or an
FFM/PanamaPort path. It is a **Kotlin/Wasm** backend: project gameplay compiles
to WebAssembly and talks to the Godot 4.7 Web export (Emscripten/Wasm) through a
generated per-call proxy and a versioned JavaScript bridge
(`web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js`, currently
protocol 27). <!-- kanama-claim: protocol --> The typed backend seam is shared with the other platforms through
`scripts/platform_backend_calls.json`, and
`scripts/generate_web_gameplay_coverage.py` fails loudly if a call the demo
executes has no admitted backend family. See
[Web Internals](../contributing/backends/web.md) for the architecture.

### Validated evidence

Twelve production Godot Web exports — Bunnymark, Starter-Kit-Match3, dodge,
web3d, 3D-Platformer, squash, FPS, character-controller, third-person, Racing,
City-Builder and tps-demo — each pass an automated, assertion-driven play
sequence, not a page-load check. Every run asserts gameplay deltas, crossing
budgets, and handle/callback/scheduler teardown to baseline, and rejects stale
handles. Gameplay coverage reports zero blocking calls; the families a demo
calls that the backend does not model (`GodotObject.emit_signal_typed` among
them) stay listed as explicit nonblocking unsupported entries rather than being
pattern-hidden.

Browser floors and the versions the corpus is driven on (protocol 27). <!-- kanama-claim: protocol --> The
floors are declared once, machine-readably, in `scripts/web/browser_floors.json`,
and `web_export_smoke.sh` fails any run below them:

| Browser | Floor | Basis | Corpus validated at | Notes |
|---|---|---|---|---|
| Chrome | 130 | tested (2026-07-28) | 150 (headless) | CI cell |
| Firefox | 141 | tested (2026-07-28) | 152–153 (headless) | CI cell |
| Safari | 26.5 | validated-at (2026-08-20) | 26.5 / WebKit 605.1.15, macOS 26.5.1 — **13 of 13 cells on 2026-08-20** (the twelve-demo corpus plus the spike cell), zero console errors corpus-wide, at protocol 20 | **spot-checked, not gated**; no headless mode, needs a logged-in GUI session, and cannot run two at once |

**Safari is spot-checked, deliberately.** It has no headless mode, needs a
logged-in GUI session with an unoccluded window, and cannot run two gates
concurrently, so a CI cell would cost more than it returns (maintainer decision,
2026-08-14). The trade is explicit: **between spot checks, the Safari claim can go
stale without anything noticing.** It did — the corpus was 12/12 on 2026-07-27 and
**11/12** when next run on 2026-08-14, with `match3` failing on the swap path. It is
**13/13 as of 2026-08-20**, re-measured after the fix.

**That was not a regression in Kanama or in the demo.** Root-caused 2026-08-15: the
harness dispatched the swap gesture while the Safari window was **not focused**, and
Safari delivers synthesized pointer input to the key window only, so the gesture was
discarded outright — the board was healthy, the coordinates were right, and Godot simply
never received any input. It reproduced about **1 run in 8**. Fixed by retrying the
focus recovery and by proving the engine accepts input before playing the real gesture;
`match3` then passed 12 consecutive runs.

Two things are worth carrying from it. **A locked screen voids a Safari run entirely** —
Safari suspends `requestAnimationFrame` for a non-visible page, so the engine advances a
few frames and stops, and the run fails inside a demo assertion with nothing naming the
cause; the driver now refuses to start in that state. And **quote the date a Safari
result was last measured**, not the best result ever recorded.

**"Tested" and "validated-at" are different claims.** Tested means the gate was
run on the floor version and on the one below it (2026-07-28, macOS arm64, on a
protocol-15 export): Chrome 129 never boots the Kotlin/Wasm module and 130 does —
that is where WebAssembly JS String Builtins shipped. Firefox's number is a limit of the **harness**
rather than of the engine — 141, 143 and 145 all pass, while 140 ESR and older
never expose a reachable WebDriver BiDi endpoint to the driver, so they are
untestable, not known-bad. Safari cannot be installed side by side with itself,
so its number is only the oldest version ever driven.

**iOS and iPadOS are hand-checked, not gated, and no mobile-WebKit floor is
claimed.** A device pass (2026-08-03, iPhone 15 Pro, Safari/WebKit
26.5.2/605.1.15, served over HTTPS — Godot Web exports require a secure
context) had 11 of the 12 corpus demos boot and render with zero console
errors; dodge and match3 were hand-played with working audio and lock/unlock
resume, and tps-demo exceeds the per-tab memory budget during load.
`safaridriver` can drive Safari on a USB-connected device (Remote Automation),
but no automated iOS gate exists, so none of this is "tested" in the sense
above — mobile WebKit stays outside the validated claim.

### Explicit non-support limitations

- No packaged/user export workflow: export requires a Kanama source checkout,
  and there is no runtime-only addon (gameplay is AOT-compiled into the Wasm
  payload). The guide is at [Exporting → Web](../exporting/web.md).
- Godot **Compatibility renderer, single-thread** only.
- No Web editor, no hot reload, no threads, no Kotlin/JS path.
- Safari has **no headless mode**, so the Safari gate is a local GUI gate rather
  than a CI cell; iOS/iPadOS WebKit is hand-checked only, not gated.
<!-- KANAMA-BLOCKED(since:2026-07-28, task:71): Linux-host mobs never free; the dodge:firefox cell is quarantined -->
- One defect is tracked openly rather than solved: on one Linux CI host,
  spawned mobs never receive `VisibleOnScreenNotifier2D.screen_exited` and are
  never freed (task 71; the `dodge:firefox` cell is quarantined, not hidden).
  Nothing measured implicates the Kanama backend, but it has not been ruled
  out — see [Exporting → Web](../exporting/web.md) Known Limitations.
- Reproducible export builds currently require
  `--no-daemon -Pkotlin.compiler.execution.strategy=in-process` (Kotlin daemon
  builds exhausted memory).

## Local Validation

Run the local CI shortcut with one or more Godot binaries:

```sh
scripts/local_ci.sh /absolute/path/to/godot-4.7.2-stable
```

Before tagging a release, run the isolated source-and-demo gate from a clean
temporary clone:

```sh
scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7.2-stable
```

For package validation, build the packaged artifacts and smoke them without a
sibling Kanama checkout:

```sh
./gradlew packageDistributions
scripts/package_install_smoke.sh \
  --desktop-kit \
  build/distributions/kanama-desktop-kit-v<version>-<platform>.zip \
  /absolute/path/to/godot-4.7.2-stable
scripts/package_install_smoke.sh \
  --store-addon \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7.2-stable
```

```sh
scripts/local_ci.sh \
  /absolute/path/to/godot-4.7.2-stable \
  /absolute/path/to/godot-4.7.2-stable
```

The script runs about forty stages (see `scripts/local_ci.sh`); the headline
ones are:

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
4.7.2 stable archive and set `JAVA_HOME` to JDK 25+:

```sh
cd /path/to/kanama-demos
JAVA_HOME=/path/to/jdk-25 \
XDG_DATA_HOME=/tmp/kanama-godot-state-linux \
XDG_CONFIG_HOME=/tmp/kanama-godot-config-linux \
KANAMA_DESKTOP_SMOKE_LOG_DIR=/tmp/kanama-desktop-smokes-linux \
scripts/desktop_smoke_all.sh /path/to/Godot_v4.7.2-stable_linux.arm64
```

(`desktop_smoke_all.sh` defaults both XDG variables to those paths on Linux; set
them explicitly only to relocate the isolated state.)

Use the Godot binary for the architecture under test, such as
`Godot_v4.7.2-stable_linux.arm64` or
`Godot_v4.7.2-stable_linux.x86_64`. The demo smoke script uses Godot's OpenGL
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
cmd /c "call ""...\VsDevCmd.bat"" -arch=x64 -host_arch=x64 && .\gradlew.bat importAllGodot -PkanamaRoot=C:\path\to\kanama -Pkanama.godot.executable=C:\path\to\Godot_v4.7.2-stable_win64_console.exe"
```

Run the demo smoke matrix through Git Bash:

```bat
cmd /c "set KANAMA_DESKTOP_SMOKE_LOG_DIR=%TEMP%\kanama-desktop-smokes&& ""C:\Program Files\Git\bin\bash.exe"" scripts/desktop_smoke_all.sh C:\path\to\Godot_v4.7.2-stable_win64_console.exe"
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

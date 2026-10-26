# Platform Evidence

The dated runs and measurements behind each platform's status. The user pages
state what works, and [Requirements and Platform
Status](../reference/version-support.md) gives each platform one status word;
this page records how that was established, so a later change can be checked
against it. The runs themselves are described in
[Device and Browser Runs](device-runs.md).

A status is claimed only after the matching smoke path passes on that target,
and it never moves to a new Godot baseline before the matching smoke and device
runs pass there ([Godot Upgrade Runbook](godot-upgrade.md)).

## Godot Baseline Changes

Kanama re-pinned from 4.7.0 to **4.7.2 stable on 2026-09-08**.
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
between the two tags, so the Android toolchain table holds for 4.7.2.

The 4.7 rc 2 → 4.7 stable bump was also metadata-only: the dumped
`extension_api.json` (excluding the `version_status` header field) and
`gdextension_interface.h` are **byte-identical** between the two builds, so the
generated wrappers, Panama bindings, name constants, and struct layouts are
unchanged and the rc 2 validation carries over. `scripts/runtime_smoke.sh`
passed against the macOS arm64 4.7 stable binary
(`4.7.stable.official.5b4e0cb0f`) on 2026-06-21. iOS (iPhone 12 self-test) and
Android (`android_smoke.sh` on the API 36 emulator, Starter-Kit-Match3) also
re-passed against 4.7 stable templates the same day. The Pixel 7 hardware
device gate (debug demo matrix + R8-minified Match3 release APK) passed on
2026-06-26. Windows x86_64 completed its full local 4.7-stable revalidation on
2026-07-13; Linux x86_64 and arm64 both re-passed the full local CI, demo,
TPS, package, and native-artifact gates against Godot 4.7 stable (x86_64 on
2026-07-13/14, arm64 on 2026-07-14 on a native AArch64 Ubuntu 26.04 host).

## Desktop

### Validation Runs

- **macOS arm64** is the primary runtime and package target. API and header
  inputs, generated wrappers, KDoc, local CI and the desktop demo smokes target
  this baseline; `runtime_smoke.sh` passed against the 4.7 stable binary on
  2026-06-21. The exported-game run is under [Exported Games](#exported-games).
- **Linux arm64**: full local CI, native bootstrap preflight, strict docs, all
  11 demo builds, the nine-demo desktop smoke matrix, the TPS checked smoke,
  distribution packaging, and the desktop-kit and store-addon install smokes
  passed on Ubuntu 26.04 with Godot `4.7.stable.official.5b4e0cb0f` and OpenJDK
  25.0.3 (native AArch64, 2026-07-14). The resource-loader/saver teardown fix
  is required.
- **Linux x86_64**: the same set passed on Ubuntu 25.04 with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.2 (2026-07-13/14). The
  resource-loader/saver teardown fix is required.
- **Windows x86_64**: full local revalidation on the 4.7 stable console binary
  (2026-07-13): demo audits, script builds, imports, the nine-demo desktop
  runtime smoke, the TPS smoke, and the packaged desktop-kit and store-addon
  install smokes all passed. The commands that build the native bootstrap run
  from a VS 2022 developer environment (`VsDevCmd`); Git Bash runs the smoke
  scripts ([Local Validation](gates.md#windows-validation)).

### Exported Games

The decided end state is **unpack-and-play**: exported desktop
games ship a bundled, jlink-trimmed JVM runtime that the native bootstrap
finds app-relative, so players never install a JDK. The system JDK stays the
**developer** path — like C# development needs the .NET SDK while Godot/.NET
exports bundle the .NET runtime.

Status: implemented for Windows, Linux, and macOS, and **cross-target** — a
developer on any desktop host can produce the runtime for any other.

Evidence, so you can judge how far it has been taken:

- **Windows** — a game exported with a runtime **cross-built on macOS arm64**
  was run on real Windows hardware (2026-08-10) and booted from its own bundled
  runtime. That machine had a JDK installed and the app-relative probe still
  won (`[kanama] using libjvm: <export>\runtime\bin\server\jvm.dll`, no
  `checked JAVA_HOME` line), so this is not a case of a system JVM standing in.
  Real GPU path (`Vulkan 1.2.175 - Forward+`, NVIDIA GTX 670), no VC++/CRT
  redistributable needed, and a clean teardown (22/22 scripts and 221/221
  StringNames released, script language unregistered with `Error=0`).
- **Linux** — the same cross-built-on-macOS proof runs on the CI runner with
  `JAVA_HOME` unset. No dedicated real-hardware pass yet.
- **macOS** — validated on macOS arm64 (2026-09-10, Godot `4.7.2.stable`,
  Temurin 25.0.4.1+1): the exported `.app` boots headless with `JAVA_HOME` unset
  and `PATH` stripped, from
  `<app>/Contents/Resources/runtime/lib/server/libjvm.dylib`. Proven twice —
  once against a host-jlinked image and once against one linked from the pinned
  `macos-arm64` Temurin jmods, 31.7 MB either way. The path in that line is the
  proof, not the fact that the game ran: with the bundled `runtime/` deleted the
  same `.app` still starts on a developer Mac, because the bootstrap's last
  resort is a hardcoded `/Library/Java/…/temurin-25.jdk` fallback. The run
  constructed a script (`SelfSmoke self_class=Node3D same_object=true`) and
  tore down clean (23/23 tracked objects destroyed, 6 extension classes
  unregistered). No pass yet on
  a second Mac with no JDK installed. **Distribution-grade signing and
  notarization of the bundled runtime is a separate track and is not done** — see
  [macOS Entitlements and
  Signing](../exporting/desktop.md#macos-entitlements-and-signing). That caveat is
  macOS-specific — it does not gate the Windows or Linux path.

### Cross-Target Runtime Images

A Temurin JDK install no longer contains jmods at all — since JDK 24's JEP 493
the JDK links from its own run-time image, which only ever produces an image
for the platform it is running on — so `jlinkGameRuntimeCross` fetches the
target platform's jmods from Adoptium's separate per-platform download
(about 80 MB compressed, SHA-256 pinned in `build.gradle.kts`).
`kanamaTargetJdkRelease` in `gradle.properties` pins which Temurin release is
fetched; the jmods must be the same JDK **feature** version as the JDK doing
the linking (patch levels may differ), and the task fails with a clear message
if they do not match. `jlinkGameRuntime` links the pinned module set
(`java.base`, `java.instrument`, `jdk.unsupported`) — one recipe for every
Kanama game.

### Runtime Image Size

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

In a finished bundle the runtime is not the biggest item. The macOS validation
export of the example project (universal binary) measured 206 MB in total:
162 MB of Godot's own universal executable, 31.7 MB of `runtime/`, 12 MB of
`kanama.jar`, and under 1 MB of scripts jar plus `.pck`.

Plus `kanama.jar` and the project's `kanama-scripts.jar`. That is the accepted
trade: every shipped commercial Java game bundles its runtime, and a smaller
download that asks players to install Java is effectively fatal for a game.
Module stripping and compression are later tuning knobs, not blockers.

### Exported Game Layout

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

The bootstrap anchors on its own dylib (`dladdr`) and walks up two parent levels,
probing a `Resources/` subdirectory at each level, so a library in
`Contents/Frameworks/` finds `Contents/Resources/runtime`. `Resources/` is the
only location that works: when `codesign` seals the bundle it rejects jars and
loose runtime files under `Contents/` or `Contents/Frameworks/` as unsigned
**nested code**, while everything under `Resources/` is sealed by hash as a plain
resource.

The macOS validation export reports `flags=0x10002(adhoc,runtime)` with exactly
the three JVM entitlements, and `codesign --verify --deep --strict` passes on
the assembled bundle. Without `com.apple.security.cs.allow-jit`,
`JNI_CreateJavaVM` dies (SIGTRAP in `pthread_jit_write_protect_np`) before the
VM exists, because HotSpot's code cache is `MAP_JIT` memory. A Temurin-derived
image carries `Developer ID Application: Eclipse Foundation, Inc.` signatures,
which library validation would refuse. The pinned module set produces 16 Mach-O
files inside `runtime/` (13 dylibs, including `lib/server/libjvm.dylib` and
`lib/server/libjsig.dylib`, plus `bin/java`, `bin/keytool` and
`lib/jspawnhelper`); files under `Resources/` are only hashed into
`CodeResources` and keep whatever signature the JDK vendor shipped. Notarizing
the bundled runtime is an explicit non-goal of the exported-game work.

### Young-Generation Sizing after a Large Spawn

The user pages recommend `-XX:MaxNewSize=128m` for games that spawn many
objects at once. This is the measurement behind it.

After a game creates many script objects at once (a level load, 10,000 Bunnymark
sprites), frames that walk those objects can run ~20–30 % slower until the next
young garbage collection, and with HotSpot's defaults that collection can be
seconds away. The cause is memory layout: the new objects sit in eden interleaved
with the garbage the spawn produced (~2–6 KB per Bunnymark bunny), and G1, seeing
a small live set and short pauses, grows eden to ~450–600 MiB, so it fills slowly.
The first young collection copies the survivors together and the frames recover
at once. Measured on Bunnymark V1 Sprites (100-frame windows after the spawn):
the phase lasts ~900 frames at ~2,250 µs per frame instead of ~1,750 µs; forcing a
young collection right after the spawn removes it; adding 4 KB of garbage per
bunny makes it ~15 % worse.

**Recommendation for games that spawn many objects at once:**
`-XX:MaxNewSize=128m` (in `kanama/jvm/options` or `KANAMA_JVM_OPTIONS`). Capping
the young generation makes that collection come after at most 128 MiB of
allocation (~200 frames in Bunnymark). It is not the default because it costs
steady-state frame time, and the cost grows with how much the game allocates and
with how loaded the machine is: ~5× more, shorter young pauses (~1.1 ms instead
of ~2.6 ms each). On Bunnymark V3, which allocates ~0.9 GB/s, four interleaved
runs of 10–12 rounds each measured +0.4 % and +0.9 % (load average 4–7) and
+4.6 % and +7.6 % (load 4–6 with other builds running, one spike to 103): plan
for ~5–8 % on a busy machine in a game that allocates like V3. V2 (~0.5 GB/s)
measured −1 % to +3.5 %, V1 Sprites +1.5 % to +3.5 %. A game that allocates less
pays less — and, without the cap, waits longer for that first collection.

`-Xmn64m` ends the phase as well, but measured head to head with the 128m cap
(10 interleaved rounds, load 4–7) it costs as much or more in steady state (V3
+1.4 %, V2 +1.4 %, V1 Sprites −1 % against the cap), runs twice the collections
and has the worse p99 frames, so the cap is the one recommended.

| Young generation (Bunnymark, 10,000 bunnies) | Frames 100–800 after the spawn vs steady state (V1 Sprites) | Steady state vs G1 default, V3 / V2 / V1 Sprites | Young GCs per second (V3) | Rounds |
| --- | --- | --- | --- | --- |
| G1 default | +22–30 % | — | ~1.4 | — |
| `-XX:MaxNewSize=128m` (recommended for spawn-heavy games) | +1 % | +0.4…+7.6 % / −1…+3.5 % / +1.5…+3.5 % | ~7 | 4 runs of 10–12 |
| `-Xmn64m` | −1 % | +1 % / +2 % / +3 % | ~14 | 10 |
| `-XX:MaxNewSize=256m` | +17–24 % | +2.5 % / +1 % / −1 % | ~3.3 | 12 |
| `-XX:MaxNewSize=64m` | 0 % | +3 % / −5 % / +1.5 % | ~15 | 4 |
| `-Xms64m` | +1 % | +3 % / −2 % / +3 % | ~6 (falling as the heap grows) | 4 |

Apple M1 Max, editor binary, median frame time after a 1,500–3,000-frame
warm-up, each option interleaved with the default in the same rounds on a
machine shared with other builds; at 4 rounds ±3 % is noise. `-Xms64m` ends the
phase only while the heap is small: G1 grows the heap (and eden with it, to
~300 MiB in V3) as the game runs, so a later spawn is slow again. ZGC and
generational Shenandoah were dropped after one screening run each (Shenandoah:
V3 frames ~2× slower; ZGC: the slow phase lasted ~2,000 frames and V3 had 70 ms
p99 frames).

### Multiplayer Smoke

One desktop smoke (`kanama-demos` `tpsBuildAndMultiplayerSmokeGodot`, run
locally, not in CI) starts a headless host and a headless client of the TPS
demo over localhost ENet. It checks that the lobby RPCs work in both directions
and that, after a real-bullet robot kill on the host, the server's part
`destroy` RPC reaches the client. The demo sends each RPC its GDScript original
sends through the generated `*Rpcs` senders (lobby in `Menu.kt`; `jump`,
`land`, `shoot`, `hit`, `explode`, `play_shoot` and `destroy` in gameplay).
Not covered: Android or iOS peers, more than two peers, client input RPCs
during gameplay, latency or packet loss, and a clean shutdown with a peer still
connected (the smoke kills both processes).

### Debugger Attach

JDWP attach was verified by hand on 2026-10-18 by attaching a JDWP client
(`jdb`, the JDK's debugger) to a headless starter project started with the
`kanama/debug/jdwp_enabled` project setting and with `KANAMA_JDWP_PORT`.
IntelliJ itself is not driven by any automated check, and the CI stage only
greps that the settings are wired through `bootstrap.c` and the editor
plugins.

### Global Classes in the Editor

`@GlobalClass` registration (the class in Create New Resource, its `.tres`
instances matching typed export slots) was validated by hand in the editor GUI
([issue #39](https://github.com/falcon4ever/kanama/issues/39)).

### String Method Cost

Each generated `String` method from `net.multigesture.kanama.builtins` makes one
engine call and converts the text both ways: `getExtension()` measured about
0.24 µs on desktop where GDScript's takes 0.08 µs. That is why
[API Conventions](../reference/wrapper-conventions.md#string-nodepath-bytearray-and-callable-methods)
points per-frame loops over many strings at Kotlin's own functions.

## Android

Android was promoted from Experimental to **Supported** on 2026-07-14, when it
met the mobile promotion bar (B1 to B4). The Android path uses the Godot 4.7.2
stable Android export, a Godot Android plugin AAR, Android ART,
[PanamaPort](https://github.com/vova7878/PanamaPort), emulator smoke tests, and
device smoke and playability checks for the Android-enabled demos.
`scripts/android_smoke.sh` passes on the API 36 emulator.

Device-validated across four models: **Pixel 7** (Android 16; Android 17 on
2026-09-15; the September Android 17 update on 2026-10-01 with PanamaPort fork
`0.1.5-kanama-r8.1`) and **Moto g 5G 2023** (Android 14) pass the full gate
(nine-demo debug matrix + R8-minified Match3 release); **Galaxy S10+**
(Android 12) and **Pixel 3 XL** (Android 9, Vulkan/Mobile) added debug breadth
before Android 13 became the minimum.
The R8/release path is tied to the PanamaPort fork
`com.github.falcon4ever.PanamaPort:Core:0.1.5-kanama-r8.1` (Android 17 needs
it), not upstream. Kits and the store addon carry the runtime as the debug AAR
`kanama-android` and compile a project's scripts with the Kanama Gradle
plugin's `buildAndroidScripts`; `package.yml`'s `android-export` job exports
an APK from the Linux kit and from the store addon, with no device.

### Android Demo Matrix

The nine public demo exports (the eight below plus Bunnymark) are the Android
smoke targets. On 2026-06-26 the full Godot 4.7 stable matrix passed on a
Pixel 7 using debug APK exports, logcat startup checks, and screenshot smoke
checks. The R8-minified Match3 release APK also passed on Pixel 7 when built
against Kanama's PanamaPort fork
(`com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4` at the time); the
minified-release claim is tied to the forked dependency, not upstream
PanamaPort. On 2026-10-01 the nine-demo debug matrix and the R8-minified Match3
release passed again on the Pixel 7 after its September **Android 17** update
(API 37, build `CP3A.260905.009`), on fork `0.1.5-kanama-r8.1`; the earlier
fork aborts in the FFI bootstrap on that build (it had passed on the phone's
earlier Android 17 build on 2026-09-15). The Android 14 (Moto g 5G 2023)
results are carried from the `0.1.3-kanama-*` fork era and were not re-run on
`0.1.5-kanama-r8.1` (no such device at hand).

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
the renderer actually initialized (`Vulkan 1.4.305 - Forward Mobile` in logcat;
a silent OpenGL fallback fails the check) alongside the normal Kanama
startup and screenshot checks. The demo corpus ships OpenGL Compatibility as
its default mobile renderer; both renderers are smoke-validated on Pixel 7,
while gameplay and visual parity under Vulkan beyond the smoke bar remains
per-demo validation. These passes are the evidence behind the Supported
claim; they are not a claim of per-demo mobile polish, and broader input
coverage would still strengthen the claim.

### Validated Android Versions

Android 13 (API 33) is the minimum, for debug and release builds alike. The
plugin declares `minSdk` 33, and the Kanama Tools export guard stops an
Android Gradle export whose preset sets **Min SDK** below 33 or leaves it
empty. Debug and release builds are validated on Android 14 (Moto g 5G 2023)
and Android 16 and 17 (Pixel 7).

The minimum comes from release builds. On Android 12 and below, release-mode
(`android:debuggable=false`) builds crash during PanamaPort's upcall-stub
generation into the system `libLLVM_android.so` (argument marshalling lands
shifted under release-mode ART's invocation of PanamaPort's runtime-bound
native methods; debug-mode ART invokes them compatibly). Android 13 replaced
that code path. Characterized with a full tombstone 2026-07-13.

Until 2026-10-21 the plugin declared `minSdk` 26 and the floor was per build
type (2026-07-13 four-model matrix: Pixel 3 XL, Galaxy S10+, Moto g 5G 2023
and Pixel 7, on Android 9, 12, 14 and 16): debug builds were validated down to
Android 9 (the dodge smoke on the Vulkan/Mobile renderer; the factory OpenGL
driver on that device crashes before Kanama runs, see
[Other Android Findings](#other-android-findings)) and Android 12 (the
nine-demo matrix), while release builds already needed Android 13. A game
therefore installed on phones it could not run on as a release build, so one
floor replaced the two. The Android 8-12 code paths went with it: the
reachability-fence probe (API 28) and the `java.lang.ref.Cleaner` probe
(API 33) that turned the garbage-collector release off on older phones.

Pre-16 devices additionally require fork `0.1.3-kanama-r8.3`+ (the
`SDK_INT_FULL` bootstrap guard); older fork versions and upstream PanamaPort
fail the FFI bootstrap on every device below Android 16. **Android 17 from the
September 2026 update requires fork `0.1.5-kanama-r8.1`+** (upstream `v0.1.5`'s
Android 17 QPR fixes); every earlier fork aborts in the bootstrap there.

### Other Android Findings

- **Old vendor OpenGL drivers.** A Pixel 3 XL on its factory Android 9 image
  (2018 Adreno 630) segfaults inside the vendor shader compiler
  (`libllvm-glnext.so`) while linking Godot 4.7 Compatibility-renderer shaders,
  before any Kanama code runs. The same device runs the demo smoke with the
  Mobile (Vulkan) renderer (`rendering_method.mobile = "mobile"`; Vulkan 1.1.66
  initialized, 2026-07-13). This is an engine-vs-driver constraint: the
  renderer is chosen by Godot from project settings before scripts run, so it
  cannot be switched from Kanama at runtime.
- **Android 17.** The FFI bootstrap abort with `Check failed:
  IsExceptionPending()` in `MethodHandles.reflectAs` was seen from the
  September 2026 update, build `CP3A.260905.009`; PanamaPort fork
  `0.1.5-kanama-r8.1` (upstream `v0.1.4`/`v0.1.5` carry the Android 17 QPR
  fixes) is required, and the earlier `0.1.3-kanama-*` forks predate them. Fork
  `0.1.3-kanama-r8.3` (included in every `0.1.5-kanama-*` fork) added the
  `SDK_INT_FULL` bootstrap guard every device below Android 16 needs.
- **Export guard.** The command-line stop is verified on a macOS host (exit
  137); Linux and Windows hosts are not tested.
- **Plugin minimum.** The Kanama plugin declares `minSdk` 33 (Android 13); see
  [Validated Android Versions](#validated-android-versions).

## iOS

iOS was promoted from Experimental to **Supported** on 2026-07-14, when it met
the mobile promotion bar (B1 to B4). The backend runs full Kanama project
scripts through a C shim and a Kotlin/Native static `.xcframework`, using the
same wrapper generator as desktop and Android, with no JVM on the device.
Physical-device export and launch are the validation target; simulator runs
are optional compile and link checks and not a frame-rate signal.

The evidence is the **full ten-step device gate** (`scripts/ios_device_gate.sh`:
the fresh-project install path plus the nine-demo matrix; each demo step also
streams the device console for 30 s after launch and fails on a crash
signature), validated on two physical models: **iPhone 12** (iOS 26.5,
2026-06-25; 0 guardrail hits, per-frame Kanama script and binding overhead
about 0.63 ms/frame measured there) and **iPhone 15 Pro** (iOS 26.5,
2026-07-10, on the full-breadth generated-wrapper runtime), both on 4.7 stable
iOS templates. The claim names those two models; it is not a device-family
("recent iPhones") claim. The playable demo set matches the Android-enabled
public demo set plus Bunnymark, and the heavy `tps-demo-kanama` also runs on
device (the audited type set and KSP registration path cover the whole
corpus).

Tracked as non-blocking: the TPS demo's mobile touch and multiplayer UI
polish, and an intermittent Audio autoload follow-up in the FPS demo. The
packaged `.xcframework` addon is runtime-only (compiling project scripts needs
the Kanama checkout), and there is no mobile hot reload.

### iOS Library Size

The size in [Requirements and Platform
Status](../reference/version-support.md#ios-library-size) was measured on
2026-10-17 with Kotlin 2.4.21 on a clean link without incremental caches:

```sh
./gradlew linkDebugStaticIosArm64 --rerun -Pkotlin.native.cacheKind=none -Pkotlin.incremental.native=false
./gradlew linkReleaseStaticIosArm64 --rerun -Pkotlin.native.cacheKind=none -Pkotlin.incremental.native=false
```

then the size of `build/bin/iosArm64/{debug,release}Static/libkanama_ios_runtime.a`.
`check_doc_claims.py` reads the measured sentence on that page and holds every
other stated size to it.

## Web

Web is **Experimental (Kotlin/Wasm preview)** on the Godot 4.7 stable baseline.
It is **not a Supported target**: it is a source-checkout export (no packaged
addon), single-thread Compatibility renderer only, and makes no support claim.
The twelve-demo corpus passes the automated production export smoke in Chrome,
Firefox, and Safari.

Unlike desktop, Android and iOS, the Web backend does **not** use a JVM or an
FFM/PanamaPort path. It is a **Kotlin/Wasm** backend: project gameplay compiles
to WebAssembly and talks to the Godot 4.7 Web export (Emscripten/Wasm) through a
generated per-call proxy and a versioned JavaScript bridge
(`web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js`, currently
protocol 39). <!-- kanama-claim: protocol --> The typed backend seam is shared with the other platforms through
`scripts/platform_backend_calls.json`, and
`scripts/generate_web_gameplay_coverage.py` fails loudly if a call the demo
executes has no admitted backend family. See
[Web Internals](backends/web.md) for the architecture.

### Validated Web Evidence

Twelve production Godot Web exports (Bunnymark, Starter-Kit-Match3, dodge,
web3d, 3D-Platformer, squash, FPS, character-controller, third-person, Racing,
City-Builder and tps-demo) each pass an automated, assertion-driven play
sequence, not a page-load check. Every run asserts gameplay deltas, crossing
budgets, and handle/callback/scheduler teardown to baseline, and rejects stale
handles. Gameplay coverage reports zero blocking calls; the families a demo
calls that the backend does not model (getters of write-only properties, for
example) stay listed as explicit nonblocking unsupported entries rather than
being pattern-hidden. A thirteenth cell, `starter`, exports a fresh
`createStarterProject` the way a game developer exports their own project
(`-PkanamaWebProjectDir`, the generic `stageWebProject`) and passes in Chrome
and Firefox (2026-10-21, macOS arm64; part of the per-PR Web matrix). It has
not been run in Safari: the Safari evidence below is the demo corpus.

### Browser Floors

Browser floors and the versions the corpus is driven on (protocol 39). <!-- kanama-claim: protocol --> The
floors are declared once, machine-readably, in `scripts/web/browser_floors.json`,
and `web_export_smoke.sh` fails any run below them:

| Browser | Floor | Basis | Corpus validated at | Notes |
|---|---|---|---|---|
| Chrome | 130 | tested (2026-07-28) | 150 (headless) | CI cell |
| Firefox | 141 | tested (2026-07-28) | 152–153 (headless) | CI cell |
| Safari | 26.5 | validated-at (2026-08-20) | 26.5 / WebKit 605.1.15, macOS 26.5.1: **13 of 13 cells on 2026-08-20** (the twelve-demo corpus plus the spike cell), zero console errors corpus-wide, at protocol 20 | **spot-checked, not gated**; no headless mode, needs a logged-in GUI session, and cannot run two at once |

**"Tested" and "validated-at" are different claims.** Tested means the gate was
run on the floor version and on the one below it (2026-07-28, macOS arm64, on a
protocol-15 export): Chrome 129 never boots the Kotlin/Wasm module and 130 does;
that is where WebAssembly JS String Builtins shipped. Firefox's number is a
limit of the **harness** rather than of the engine: 141, 143 and 145 all pass,
while 140 ESR and older never expose a reachable WebDriver BiDi endpoint to the
driver, so they are untestable, not known-bad. Safari cannot be installed side
by side with itself, so its number is only the oldest version ever driven.

### Safari Spot Checks

**Safari is spot-checked, deliberately.** It has no headless mode, needs a
logged-in GUI session with an unoccluded window, and cannot run two gates
concurrently, so a CI cell would cost more than it returns (maintainer
decision, 2026-08-14). The trade is explicit: **between spot checks, the Safari
claim can go stale without anything noticing.** It did: the corpus was 12/12
on 2026-07-27 and **11/12** when next run on 2026-08-14, with `match3` failing
on the swap path. It is **13/13 as of 2026-08-20**, re-measured after the fix.

**That was not a regression in Kanama or in the demo.** Root-caused
2026-08-15: the harness dispatched the swap gesture while the Safari window was
**not focused**, and Safari delivers synthesized pointer input to the key
window only, so the gesture was discarded outright. The board was healthy, the
coordinates were right, and Godot simply never received any input. It
reproduced about **1 run in 8**. Fixed by retrying the focus recovery and by
proving the engine accepts input before playing the real gesture; `match3` then
passed 12 consecutive runs.

Two things are worth carrying from it. **A locked screen voids a Safari run
entirely**: Safari suspends `requestAnimationFrame` for a non-visible page, so
the engine advances a few frames and stops, and the run fails inside a demo
assertion with nothing naming the cause; the driver now refuses to start in
that state. And **quote the date a Safari result was last measured**, not the
best result ever recorded.

### Safari on iPhone and iPad

**iOS and iPadOS are hand-checked, not gated, and no mobile-WebKit floor is
claimed.** A device pass (2026-08-03, iPhone 15 Pro, Safari/WebKit
26.5.2/605.1.15, served over HTTPS, since Godot Web exports require a secure
context) had 11 of the 12 corpus demos boot and render with zero console
errors; dodge and match3 were hand-played with working audio and lock/unlock
resume, and tps-demo exceeds the per-tab memory budget during load. How to
drive Safari on a device is in
[Device and Browser Runs](device-runs.md#safari-on-iphone-and-ipad); no
automated iOS gate exists, so mobile WebKit stays outside the validated claim.

### Web Gaps

- No packaged export workflow: a project is exported from a Kanama source
  checkout (`-PkanamaWebProjectDir`), and there is no runtime-only addon
  (gameplay is AOT-compiled into the Wasm payload).
- Godot **Compatibility renderer, single-thread** only.
- No Web editor, no hot reload, no threads, no Kotlin/JS path.
- Safari has **no headless mode**, so the Safari gate is a local GUI gate rather
  than a CI cell; iOS/iPadOS WebKit is hand-checked only, not gated.
<!-- KANAMA-BLOCKED(since:2026-07-28, issue:370): screen_exited never fires for dodge's mobs on Linux Firefox; the dodge:firefox cell is quarantined -->
- One defect is tracked openly rather than solved: on one Linux CI host,
  spawned mobs never receive `VisibleOnScreenNotifier2D.screen_exited`, so they
  are not freed off screen; the next round's `queue_free` still frees them
  ([issue #370](https://github.com/falcon4ever/kanama/issues/370); the `dodge:firefox` cell is quarantined, not hidden). Nothing measured implicates the Kanama backend, but it has not been ruled
  out; see [Quarantined Cells](device-runs.md#quarantined-cells).

### Other Web Findings

- **Secure context on a LAN address.** Serving an export over plain HTTP on a
  LAN address never starts the engine on the device. Measured: a fatal error in
  audio-worklet init right after "starting Godot…"; `serve_export.py` now
  refuses `--lan` without `--https` up front.
- **tps-demo payload.** tps-demo serves ~638 MB (570 MB of upstream demo assets
  in `index.pck`) and exceeds the itch.io defaults; `packageWebExport` fails it
  by name rather than producing an artifact nobody could upload. This is the
  same known exception recorded in `scripts/web/budgets.json` — shrinking it is
  asset work in the demo, not a packaging concern. Its online lobby is
  deliberately inert on the Web, because Web builds are single-player.

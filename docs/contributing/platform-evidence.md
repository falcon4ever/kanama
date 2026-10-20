# Platform Evidence

The dated runs and measurements behind each platform's status. The user pages
state what works; this page records how that was established, so a later
change can be checked against it. The runs themselves are described in
[Device and Browser Runs](device-runs.md).

## Desktop

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
  Real GPU path (`Vulkan 1.2.175 - Forward+`), no VC++ redistributable needed,
  clean teardown.
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
  resort is a hardcoded `/Library/Java/…/temurin-25.jdk` fallback. No pass yet on
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

## Android

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
- **Plugin minimum.** The Kanama plugin's declared `minSdk` is 26; the
  validated floors per build type are in
  [Requirements and Platform Status](../reference/version-support.md).

## Web

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

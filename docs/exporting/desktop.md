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
  /absolute/path/to/godot-4.7.2-stable
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
  /absolute/path/to/godot-4.7.2-stable
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

Desktop Kanama development needs a JDK distribution that contains `libjvm`
(the JDK floor is in [Version Support → Requirements](../reference/version-support.md#requirements)). The native bootstrap checks for a bundled app-relative `runtime/`
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

## JVM Options

The native bootstrap starts the JVM inside Godot's process with only the options
Kanama needs (classpath, native access, JDWP when enabled); heap and GC sizes are
HotSpot's defaults. Add your own with `KANAMA_JVM_OPTIONS` (whitespace-separated).
They reach the JVM as JNI options, which HotSpot treats exactly like command-line
options:

```sh
KANAMA_JVM_OPTIONS="-XX:MaxNewSize=128m -Xlog:gc" godot --path my_game
```

`JAVA_TOOL_OPTIONS` works as for any JVM, with one HotSpot rule to know: the JVM
reads it as environment options, and G1 sizes its young generation only from
command-line ones, so a `-XX:MaxNewSize` or `-XX:NewSize` there is silently
discarded. Use `KANAMA_JVM_OPTIONS` for those (`-Xmn` works in either).

### Slow frames after a large spawn

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
`KANAMA_JVM_OPTIONS="-XX:MaxNewSize=128m"`. Capping the young generation makes
that collection come after at most 128 MiB of allocation (~200 frames in
Bunnymark). It is not the default because it costs steady-state frame time in
proportion to how much the game allocates: more, shorter young pauses. Bunnymark
V2 and V3 allocate ~0.5–0.9 GB/s and run 1–5 % slower with it; a game that
allocates less pays less (and, without the cap, waits longer for that first
collection).

| Young generation (Bunnymark, 10,000 bunnies) | Frames 100–800 after the spawn vs steady state (V1 Sprites) | Steady state vs G1 default, V3 / V2 / V1 Sprites | Young GCs per second (V3) |
| --- | --- | --- | --- |
| G1 default | +22–30 % | — | ~1.4 |
| `-XX:MaxNewSize=128m` (recommended for spawn-heavy games) | +1 % | +1–5 % / +3 % / +1 % | ~7 |
| `-XX:MaxNewSize=256m` | +17–24 % | +2.5 % / +1 % / −1 % | ~3.3 |
| `-XX:MaxNewSize=64m` | 0 % | +3 % / −5 % / +1.5 % | ~15 |
| `-Xmn64m` | 0 % | 0 % / −3 % / −0.5 % | ~15 |
| `-Xms64m` | +1 % | +3 % / −2 % / +3 % | ~6 (falling as the heap grows) |

Apple M1 Max, editor binary, median frame time after a 1,500–3,000-frame
warm-up, each option interleaved with the default in the same rounds on a
machine shared with other builds: 10–12 rounds for the default and the two
`MaxNewSize=128m`/`256m` rows (the 128m cost was +1 % at load average ~4 and up
to +5 % under heavy load), 4 rounds (±3 % is noise) for the others. Young pauses
with the 128m cap: ~1.1 ms each instead of ~2.6 ms. `-Xms64m` ends the phase
only while the heap is small: G1 grows the heap (and eden with it, to ~300 MiB in
V3) as the game runs, so a later spawn is slow again. `-Xmn64m` and
`-XX:MaxNewSize=64m` double the collections again for no gain on the phase. ZGC
and generational Shenandoah were dropped after one screening run each
(Shenandoah: V3 frames ~2× slower; ZGC: the slow phase lasted ~2,000 frames and
V3 had 70 ms p99 frames).

## Exported Games

The decided end state (issue #102) is **unpack-and-play**: exported desktop
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
  [macOS bundle layout, entitlements, and
  signing](#macos-bundle-layout-entitlements-and-signing). That caveat is
  macOS-specific — it does not gate the Windows or Linux path.

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

In a finished bundle the runtime is not the biggest item. The macOS validation
export of the example project (universal binary) measured 206 MB in total:
162 MB of Godot's own universal executable, 31.7 MB of `runtime/`, 12 MB of
`kanama.jar`, and under 1 MB of scripts jar plus `.pck`.

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

macOS needs more than a copy step — the payload changes the `.app` layout and
the embedded JVM needs hardened-runtime entitlements. That is the next section.

### macOS bundle layout, entitlements, and signing

A macOS export is an `.app` bundle, so "next to the executable" is not somewhere
the payload can go. `export_game_assemble.sh` puts it in `Contents/Resources/`,
beside the exported `.pck`:

```text
<Game>.app/Contents
├── Frameworks/libkanama_bootstrap.dylib   # where Godot's export puts it
├── MacOS/<Game>                           # the exported Godot binary
├── Resources
│   ├── <Game>.pck
│   ├── kanama.jar
│   ├── kanama-scripts.jar
│   └── runtime/                           # the bundled jlink image
│       ├── bin/{java,keytool}
│       └── lib/server/libjvm.dylib
└── _CodeSignature/CodeResources
```

The bootstrap anchors on its own dylib (`dladdr`) and walks up two parent levels,
probing a `Resources/` subdirectory at each level, so a library in
`Contents/Frameworks/` finds `Contents/Resources/runtime`. `Resources/` is the
only location that works: when `codesign` seals the bundle it rejects jars and
loose runtime files under `Contents/` or `Contents/Frameworks/` as unsigned
**nested code**, while everything under `Resources/` is sealed by hash as a plain
resource.

Adding files after Godot's export invalidates the signature Godot wrote, so the
assembly script re-seals the bundle ad-hoc:

```sh
codesign --force --sign - --preserve-metadata=entitlements,flags,identifier <Game>.app
```

Without it macOS kills the app at launch. That reseal is **ad-hoc**, so it
replaces a Developer ID signature if the export had one: for a signed build the
order is export → assemble → sign, never export-and-sign → assemble.

#### Entitlements the embedded JVM needs

A macOS export preset needs the `Import ETC2 ASTC` VRAM compression project
setting (for any universal/arm64 export) and three hardened-runtime exceptions
from the preset's codesign entitlements (`allow_jit_code_execution`,
`allow_unsigned_executable_memory`, `disable_library_validation`):

| Entitlement in the signed bundle | Why the JVM needs it |
| --- | --- |
| `com.apple.security.cs.allow-jit` | HotSpot's code cache is `MAP_JIT` memory. Without it `JNI_CreateJavaVM` dies (SIGTRAP in `pthread_jit_write_protect_np`) before the VM exists. |
| `com.apple.security.cs.allow-unsigned-executable-memory` | HotSpot executes pages it wrote itself, which the hardened runtime otherwise refuses. |
| `com.apple.security.cs.disable-library-validation` | The runtime image's dylibs are signed by a different team than the game (a Temurin-derived image carries `Developer ID Application: Eclipse Foundation, Inc.`). Library validation loads only nested code signed by the app's own team or Apple, so it would refuse `libjvm.dylib`. |

Check a build with `codesign -dv --entitlements - <Game>.app`. The macOS
validation export reports `flags=0x10002(adhoc,runtime)` with exactly those three
entitlements, and `codesign --verify --deep --strict` passes on the assembled
bundle.

#### What signed distribution additionally requires (not implemented)

Kanama signs nothing with a real identity, and notarizing the bundled runtime is
an explicit non-goal of this work. What a developer shipping a macOS build has to
do themselves — recorded here so the size of the gap is known, not because
Kanama does any of it:

- **Every Mach-O file inside `runtime/` is code and needs its own signature.**
  The pinned module set produces 16 of them: 13 dylibs (including
  `lib/server/libjvm.dylib` and `lib/server/libjsig.dylib`) plus `bin/java`,
  `bin/keytool` and `lib/jspawnhelper`. Signing the `.app` does **not** sign
  them — files under `Resources/` are only hashed into `CodeResources` and keep
  whatever signature the JDK vendor shipped.
- **Sign inside-out**: every nested binary first (`codesign --force --timestamp
  --options runtime --sign "Developer ID Application: …"`), the bundle last.
  Signing the bundle and then touching a nested file breaks the seal again.
- Re-signing the runtime with your own Developer ID is also what would let you
  drop `disable_library_validation`; keeping the vendor's signatures means
  keeping that exception.
- Then the usual distribution steps on top: hardened runtime and a secure
  timestamp everywhere, `xcrun notarytool submit` on a zipped `.app`, and
  `xcrun stapler staple`.
- Until that lands the `.app` is ad-hoc signed: fine for local runs and for an
  unzipped copy that was never quarantined, but a downloaded build meets
  Gatekeeper. The `xattr -dr com.apple.quarantine` workaround above is a
  developer convenience, not something to ask players to do.

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
nothing about exporting from another host. The matrix carries a `macos-arm64`
row as well; host and target coincide there, so it proves something narrower —
that an image linked from the pinned per-platform Temurin jmods (the recipe
every cross target uses, rather than the build JDK's own run-time image) boots a
real export.

The desktop kits still validate editor/runtime onboarding only; the export
smoke is the exported-game gate.

## Android Track

Android exports use a different runtime path: a Godot Android plugin AAR, ART,
PanamaPort, and Android-specific packaging. See [Android](android.md) for the
workflow and [Version Support](../reference/version-support.md) for the tier
and its caveats.

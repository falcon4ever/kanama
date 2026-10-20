# Device and Browser Runs

These are the runs behind each platform's status: the desktop export smoke, the
Android and iOS device runs, and the Web browser matrix. They are for people
changing Kanama; a game developer exports with the
[Shipping Your Game](../exporting/desktop.md) pages instead. Commands run from
the Kanama checkout. Which run last passed, and when, is in the
[Gates Index](../reference/generated/gates.md); the dated results are in
[Platform Evidence](platform-evidence.md).

## Desktop Export Smoke

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

## Desktop Package Smokes

The desktop kit and store addon install smokes are in
[Release Process](release.md#desktop-packages).

## Android Smoke

```sh
ANDROID_HOME=/path/to/android/sdk \
ANDROID_SDK_ROOT=/path/to/android/sdk \
scripts/android_smoke.sh \
  /path/to/godot \
  /absolute/path/to/godot_project \
  com.example.package.name \
  /tmp/kanama-demo.apk
```

The smoke script builds scripts, installs the Kanama Android AAR **and the
desktop addon** (the export runs in the desktop editor, which needs
`kanama.jar` plus the project's `kanama-scripts.jar` to load the `.kt` scripts;
without them the export stops with the Kanama Tools error described under
[Export Guard](../exporting/android.md#export-guard)), installs the Android build template
(by hand from the export templates when Godot's `--install-android-build-template
--quit` leaves nothing, as it does on 4.7.2), clears `.godot/exported/`, exports
a debug APK and fails if the export prints `Kanama: Export stopped`, exits
non-zero or shows an unbound `.kt` script, runs
`scripts/check_exported_scene_properties.gd -- --require-converted` on the project, then installs and
launches the APK with `adb`, checks logcat for Kanama startup signals, verifies a
captured screenshot is not blank, and force-stops the app before exiting.
`scripts/android_export_minified.sh` (the R8 release smoke) does the same up to
the export.

To run the same smoke under a different renderer, set
`KANAMA_ANDROID_RENDERER` (`mobile`, `gl_compatibility`, or `forward_plus`).
The script temporarily overrides the project's `rendering_method.mobile`
setting for the run (restored afterwards) and asserts in logcat that the
requested renderer actually initialized, so a silent fallback fails the smoke.
The device screen must be on and unlocked — a secure lock screen fails renderer
surface creation at launch.

The export guard's command-line stop (the editor process ends itself because
Godot's Android exporter ignores an export plugin's error) is verified on a
macOS host, with exit status 137; Linux and Windows hosts are not tested.

## iOS Device Runs

### Self-Test and FAULT Lines

A user's game prints no `FAULT-PROBE` line. Kanama's device runners build the
self-test in, and these seven lines are expected in such a run:

```
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_get_method_bind: bind-lookup-failed Node3D.set_visible hash=1
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_ptrcall_dispatch: null-bind
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_take_pending_utf8: pending-protocol g_pending_utf8
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_take_pending_utf8: pending-protocol g_pending_utf8
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_take_pending_packed: pending-protocol g_pending_packed (nothing pending)
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_take_pending_container_blob: pending-protocol g_pending_container_blob
[kanama][ios][c] FAULT-PROBE kanama_ios_godot_take_pending_blob: pending-protocol g_pending_blob
```

Your game's own builds carry no self-test: it is a Kanama test hook, compiled only into a build
made with `-PkanamaIosSelfTest=true` (Kanama's device runners pass it), so these lines appear
only in such a run. The self-test deliberately makes seven bad calls — one lookup with a wrong
hash, one call through the resulting null bind, and five takes from a pending slot that
has already been drained (one per pending buffer: the UTF-8 string slot twice, then the
packed, container-blob and array-blob slots) — so a healthy debug run ends with
`faults=7 expected=7` on both `OBJECTCALLS SELFTEST` summary lines. That is the fault
sink proving it still works. While it makes them, the self-test puts the sink in probe
mode, so these seven print as `FAULT-PROBE`; they are still counted, which is why the
summary says 7. **Every `FAULT` line (without `-PROBE`) is a real one.**

Do not read anything into where the `FAULT-PROBE` lines sit in the log. The self-test
also prints `OBJECTCALLS SELFTEST fault-probes begin` / `... end` markers, but those go
to stdout and the fault lines go to stderr, and the device console (`devicectl
--console`) merges the two streams without keeping their relative order — on a phone
the seven `FAULT-PROBE` lines routinely appear after the `end` marker. The tag on the
line and the `faults=`/`expected=` count are what to trust.

A build without the self-test (every build of your game, and every release build) prints
no FAULT-PROBE line and no `OBJECTCALLS SELFTEST` summary line — there is no
`faults=`/`expected=` pair to read, rather than a pair reading `0`; any `FAULT` line is
still a real fault.

`kanama-demos/scripts/ios_device_run.sh` fails the device run when the captured console
contains any `[kanama][ios][c] FAULT ` line, when a summary line's `faults=` does not
equal its `expected=`, or when a summary line is missing (the runner builds the
self-test in, so it requires `PTRCALL SELFTEST MATRIX`, `OBJECTCALLS SELFTEST` and
`OBJECTCALLS SELFTEST (frame 1)`, each with `0 failed`). `FAULT-PROBE` lines do not fail it by themselves —
the count does that if there are more or fewer than seven. To re-check an existing log
by hand, from the kanama-demos checkout:

```sh
scripts/ios_device_run.sh --check-console-faults /path/to/console.log
```

If you are chasing a fault, the first FAULT line in the log is the one to read: later
ones are often consequences of it.

The fault sink itself, and why the probe lines are told apart by a mark on the
line rather than by their position, are in
[iOS Backend Architecture](backends/ios.md#contract-no-silent-paths-the-fault-sink).
Before the sink existed, a failed call returned `0`, `null` or an empty list
and nothing said anything: a whole family of Godot static methods was a no-op
on iOS for six days and neither the self-tests nor two demo smokes noticed.

### Environment Variables on a Device

`System.getenv` works on iOS: the value comes from the app's process
environment, which `xcrun devicectl device process launch --environment-variables
'{"NAME":"value"}'` can set for a development launch. The device gate uses this to
run each demo's smoke script (the `kotlin-src/Smoke*.kt` that reads it)
on the phone (`KANAMA_DEMO_SMOKE_QUIT=1`). A
shipped app has no such variables, so a `getenv` check is a debugging switch, not
a configuration mechanism.

### Simulator Builds

`installIosAddon` builds device-only xcframeworks by default. Pass
`-PkanamaIosXcframeworkMode=full` only when intentionally building the optional
device plus simulator xcframeworks, for simulator experiments.

Only do this when deliberately using the simulator path. Before simulator work
on Apple Silicon, verify that the installed Godot iOS export template has an
`arm64` simulator engine archive:

```sh
./gradlew verifyIosGodotTemplate \
  -PkanamaXcodeDeveloperDir=/Applications/Xcode.app/Contents/Developer
```

Or run the script directly:

```sh
scripts/ios_template_preflight.sh \
  --xcode-developer-dir /Applications/Xcode.app/Contents/Developer
```

The simulator path remains available for Xcode link checks and loader-debug
experiments, but it is not a performance gate for Kanama iOS.

If the installed Godot iOS template is missing `arm64` simulator support, build
a matching Godot simulator library and pass it explicitly to maintainer smoke
tooling:

```sh
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer \
scons platform=ios target=template_debug arch=arm64 simulator=yes precision=single

scripts/ios_visual_smoke.sh \
  --godot /Applications/Godot.app/Contents/MacOS/Godot \
  --simulator \
  --godot-simulator-lib /absolute/path/to/libgodot.ios.template_debug.arm64.simulator.a \
  --kanama-script-probe
```

Apple Silicon iOS simulators build and run `arm64` simulator binaries. That is
separate from real-device `arm64`, which uses the `iphoneos` SDK instead of the
`iphonesimulator` SDK. Use a real iPhone for any playability or performance
read.

### Maintainer Visual Smoke

The visual smoke script proves the native loader, Xcode device build, app
install, app launch, and a simple Godot render path. With the Kanama probe flags
it can also prove Kotlin/Native frame callbacks, script resources, or full
KSP-generated project script execution. These probes are maintainer validation
tools; they are not the primary user export workflow.

Set `KANAMA_IOS_DEVICE` and, when needed, `KANAMA_IOS_TEAM` in your shell or
private maintainer notes before running the script. Do not commit the real
values.

```sh
scripts/ios_visual_smoke.sh \
  --godot /Applications/Godot.app/Contents/MacOS/Godot \
  --kanama-user-script-probe \
  --allow-provisioning-updates
```

Use lighter probe flags such as `--kanama-probe` or `--kanama-script-probe` for
loader/render checks. None of the modes prove hot reload.

## Web Runs

The Web harness needs **Node.js**; the export itself does not.

### Export Smoke

`web_export_smoke.sh` serves an already-built export, drives the demo in a real
browser through a full play + teardown sequence, validates the machine-readable
result against a versioned schema, and proves the served tree was not mutated. A
run is never green from page load alone.

```sh
scripts/web_export_smoke.sh \
  --engine chrome \
  --export-dir web-runtime/build/web-export/match3 \
  --demo match3 \
  --result /tmp/match3-chrome.json
```

Each gate asserts gameplay deltas, crossing budgets, handle/callback/scheduler
teardown to baseline, stale-handle rejection, console-error checks, a
protocol-version match, and — per demo — that the registered script members on
its required list in `scripts/web/required_members.json` actually dispatched
during the run (the exercised-member census).

Read the harness's own `web_export_smoke: PASS` line, not a driver's check count.
The driver's checks and the envelope schema are two different gates: a demo can
pass 13/13 of its own checks and still be rejected by the schema (which is what
enforces `liveAfterTeardown === 0` and the required-member census), and that is
exactly how one demo stayed un-gated on every engine for weeks.

### Package Smoke

Validate a `packageWebExport` artifact itself, not the export directory it came
from, before uploading. `web_package_smoke.sh` unzips the artifact to a scratch
directory, serves that copy, and drives it through the full export smoke:

```sh
scripts/web_package_smoke.sh \
  --zip web-runtime/build/distributions/kanama-web-match3-v<version>.zip \
  --demo match3 --engine chrome --result /tmp/match3-package.json
```

Read the inner `web_export_smoke: PASS` line, as always.

`packageWebExport` scans every served byte for workstation-absolute paths with
`scripts/web/check_no_local_paths.py`, the fresh-checkout gate's scanner, and
prints the measured size and file count against itch.io's defaults either way.

### Browser Matrix

`web_ci_matrix.sh` is the corpus-wide gate: it exports each demo and drives it in
each requested browser through `web_export_smoke.sh`, then aggregates every cell
into one evidence JSON plus a Markdown summary.

```sh
scripts/web_ci_matrix.sh \
  --godot /absolute/path/to/godot \
  --template "$HOME/Library/Application Support/Godot/export_templates/4.7.2.stable/web_nothreads_release.zip" \
  --demos-dir /absolute/path/to/kanama-demos \
  --demo-set full \
  --engine chrome --engine firefox \
  --evidence /tmp/web-matrix.json
```

Every cell runs even after an earlier one fails, so a red run reports the whole
picture. `--demo-set pr` is the per-PR subset (`match3`, `web3d`, `dodge` — a
pointer-drag demo, a 3D demo needing no demos checkout, and a full-lifecycle
demo); `--demo-set full` is the 12-demo corpus plus the gated spike benchmark
cell (as is `ci`, minus what a hosted runner cannot build — see the Spike
Benchmark Cell below). Per-demo budgets live in `scripts/web/demos.sh`; scale
them for a slower host with `--timeout-scale` rather than editing them, so local
and CI numbers stay comparable.

**Chrome and Firefox are the CI cells** (`.github/workflows/web.yml`): the PR
subset on every Web-relevant pull request, the full corpus on push to `main` and
nightly. **Safari is a local pre-promotion gate, not a CI cell** — see
[Safari Gate](#safari-gate). Run it with the same script, `--engine safari`, one run at a time.

### Regression Cadence

Which gate runs when, and where, is derived from the workflow files,
`local_ci.sh`, and the `evidence/gates.json` ledger by the generated
[Gates Index](../reference/generated/gates.md): the PR subset and the `ci` corpus on
Chrome + Firefox, the nightly soak and spike cells, and the local-only Safari,
tps-demo, and fresh-checkout gates each appear there with their cadence and
last recorded run. Two policies sit on top of that list:

- **Browser floor re-bisect** — when a floor is claimed to move, or a browser
  major ships that breaks a cell (local).
- **Everything above on a Godot baseline bump** — the export template,
  generated proxy, and bridge protocol all move together (local + CI).

The nightly run matters because two of the inputs change without anyone touching
the repository: the browsers on the runner image, and the runner itself. A red
nightly on an unchanged tree is a browser-side regression, which is exactly the
class of failure a per-PR gate can never see.

### Soak Gate

```sh
KANAMA_WEB_SOAK_SECONDS=600 scripts/web_ci_matrix.sh \
  --godot /absolute/path/to/godot --template <web_nothreads_release.zip> \
  --demos-dir /absolute/path/to/kanama-demos --demo soak --engine chrome
```

The soak driver runs against the **dodge** export for ten minutes, restarting the
round every sixty seconds. Dodge is the choice on purpose: leak detection wants
churn, not polygons — a mob is instantiated every half second and frees itself on
leaving the screen, so a ten-minute run is hundreds of full node create/free
cycles through the handle registry, the signal-connection table and the
deferred-free path.

It splits its samples in half and compares high-water marks, so it fails on a
*trend* rather than on a threshold: live handles, pending signal callbacks and
registered coroutine jobs must not be higher in the second half than the first
(plus a few handles of sampling slack), gameplay must still be running at the
end, and teardown must still drain to zero after a long run rather than only
after a short one.

### Spike Benchmark Cell

```sh
scripts/web_ci_matrix.sh --godot /absolute/path/to/godot \
  --template <web_nothreads_release.zip> --demo spike --engine chrome --engine firefox
```

The spike is the synthetic transport benchmark (the staged in-repo
`webSpikeGodot` project — it needs no demos checkout and exports through its own
`:web-runtime:exportWebSpike` task). It is **opt-in**: the bridge's frame
fallthrough is the real `_process`, and only a page that stamps
`globalThis.KanamaWebMode = "spike"` reaches the benchmark transport. This cell
is what keeps the opt-in path from rotting. Its driver asserts the staging still
names the mode (an export that loses the mode line falls back to gameplay and
fails here, loudly), that the benchmark branch's own counters advanced while the
real `_process` path stayed at exactly zero — the inverse of the demo drivers'
`realProcessPathDispatched` — folds the page's structural verdict (the
10000-mutation batch must cross the boundary exactly ONCE) into the envelope,
and quits the SceneTree to drain live handles to zero. Transport cost is gated
structurally rather than by a ratio: the spike never takes the
`_process`/`_physics_process` tick paths, so crossings-per-tick is exempt in
`scripts/web/budgets.json` with the reason recorded.

### Performance Budgets

Every smoke run is checked against a per-demo budget declared in
`scripts/web/budgets.json`, so a demo cannot quietly grow a payload, stop
starting, or start doing per-frame work at the module boundary.

| Budget | Unit | Why |
|---|---|---|
| Payload | bytes | Host-independent by construction |
| Startup | ms | Wall-clock, so deliberately loose — it catches a demo that stopped booting, not a slow runner |
| **Crossings per engine tick** | ratio | The real invariant: what batching and snapshots exist to bound |

**Why per engine.** The ratio is engine-stable across most of the corpus — Chrome
vs Firefox: bunnymark 2.22/1.97, dodge 0.19/0.20, fps 1.10/1.02 — but *not* for
the input-heavy demos, where Firefox reports several times the boundary work
(charactercontroller 4.50/**19.58**, thirdperson 1.15/**6.01**). Budgets are
therefore measured and declared per engine. The asymmetry itself is settled
(task 74): nothing paces `requestAnimationFrame` in headless Firefox, so
render-frame-driven (`_process`) command emission multiplies freely while
physics-driven work tracks the wall-capped physics step — a per-opcode
histogram shows the multiplier concentrated in per-render-frame emissions,
with physics-driven calls at exactly the tick ratio on both engines. The
number is a property of the headless environment, not of Firefox as users run
it (display-paced, it sits near Chrome); per-engine budgets bound regressions
within each environment's own baseline.

**Why the headline budget is per tick rather than per second.** Godot's Web main
loop is paced by `requestAnimationFrame` and advances a fixed step per iteration,
so the same build runs at very different rates depending on the host — measured
between roughly **2× and 8.7× real time** across four hosts on one day. A budget
denominated in wall-clock seconds would grade the machine rather than the
backend. Crossings per tick means the same thing everywhere.

A **tick** is one engine dispatch into the script layer, counting **both**
`_process` and `_physics_process`. Counting render frames alone reported *zero*
ticks for a third of the corpus, because the character-controller, racing and
third-person demos do all their work in the physics tick.

Every number in `budgets.json` was measured, and each demo records the run it
came from in its `measured` block. Re-baseline by running the corpus and
regenerating, never by nudging a number until a run passes:

```sh
scripts/web_ci_matrix.sh --godot <godot> --skip-export --demo-set full --engine chrome \
  --result-dir /tmp/budgets
python3 scripts/web/check_budgets.py /tmp/budgets/<demo>-chrome.json --report
```

**One demo is exempt from the ratio, with its reason recorded.** `match3` is
input-driven — the driver spends its run on pointer gestures and settle waits, so
the script layer is dispatched only ~20–50 times per run (53 locally, 23 on a CI
Chrome). A ratio whose denominator swings 2× with host speed is not a
measurement, and failing on it would grade the runner. Its payload and startup
budgets still apply, and an exemption without a stated reason is a hard error in
the checker rather than a quiet pass.

**One demo is over any sane budget and says so**: tps-demo serves **638 MB**, of
which 570 MB is upstream demo assets in `index.pck`. It runs, but nobody would
download it. The budget file records that as a known exception rather than
blessing it — fixing it is asset work in the demo, which 60f puts out of scope,
because budgets are not met by editing gameplay or scene content.

### What CI Runs and What Stays Local

Not everything can or should run on a hosted runner, and the two reasons are
different:

- **Local-only** — CI *structurally cannot* do it, and no fix changes that.
  **Safari** (no headless mode; it needs a logged-in GUI session) is in this
  tier: a release gate a maintainer runs by hand. (tps-demo used to be listed
  here for a reported out-of-memory compile on hosted runners; it compiles and
  passes there, and is in the `ci` corpus.)
- **Quarantined** — a real defect, temporary, tied to a task. See below.

`--demo-set ci` is the corpus minus any local-only demos (none today) and is what the workflow
runs; `--demo-set full` always means the full corpus, so a local run is never
quietly narrowed. Skipped demos are **announced and written into the evidence
JSON** with their reason, because a corpus that silently shrinks is how "the
corpus is green" stops meaning anything.

### Quarantined Cells

A known-failing `demo:engine` pair can be **quarantined** in
`scripts/web/demos.sh` with a reason that names a task and an expiry date; past
the date the matrix prints `QUARANTINE EXPIRED` on every run until the cell is
lifted or the date is renewed. A quarantined cell still
exports, still runs and still reports — it just does not fail the build. Deleting
the demo from the matrix instead would be the trap this gate exists to close: the
corpus would look green because nobody was looking.

A quarantined cell that **passes** is reported just as loudly, with an explicit
"lift the quarantine" line, because a stale quarantine is worse than none.
Lifting one is a one-line deletion.
<!-- KANAMA-BLOCKED(since:2026-07-28, issue:370): the dodge quarantine below -->
Currently quarantined: `dodge:firefox` — `screen_exited` never fires for dodge's
mobs on the Linux runner's Firefox (dodge passes in Firefox on macOS, and
`dodge:chrome` passes on Linux), so the cell fails `mobsFreedOffScreen` there and
nothing else (the next game's `queue_free` still frees every mob); the quarantine
runs until 2027-03-31. `squash:firefox` was lifted on
2026-08-11 (the [#370](https://github.com/falcon4ever/kanama/issues/370) signature stopped reproducing there and the cell passes
outright under the task-81 gameplay checks, death path included).
`squash:chrome` was lifted on 2026-08-12: its quarantine covered the
death-phase choreography only (the parked player waited for a randomly-heading
mob, 1-for-2 on the slow runner), and the driver now steers the player into a
mob instead of parking — deterministic by construction, 3/3 Chrome + 3/3
Firefox local repeats green; the runner-scale proof is the lifting PR's own CI
plus the next main full-corpus run.

The same signature is the open defect users see under
[Export for the Web → Known Limitations](../exporting/web.md#known-limitations):
on one Linux CI host, spawned mobs in two demos travel far off screen yet
`VisibleOnScreenNotifier2D.screen_exited` never fires, so they are never freed
and live handles plateau. Five hypotheses have been eliminated by measurement
across three hosts, and a plain-GDScript control shows the notifier itself
firing on that host under Chrome — nothing measured implicates the Kanama
backend, but "not our bug" has not been earned either. The affected cells are
quarantined, not hidden.

### Bumping the Demos Pin

The workflow checks out `kanama-demos` at the `DEMOS_REF` commit pinned in
`.github/workflows/web.yml`. This is deliberate: an unpinned checkout lets an
unrelated demo-repo commit redden every Kanama pull request. When a demo port
lands in `kanama-demos`, bump `DEMOS_REF` in the same pass — a Kanama change that
needs new demo code is not green until both sides are pinned together.

### Fresh-Checkout Gate

`web_fresh_checkout_smoke.sh` answers a different question from the export smoke:
not "does this export run?" but "can anyone reproduce it?". It clones Kanama (and
kanama-demos, when the selected demo lives there) into a throwaway workspace with
its own `HOME`, Gradle home and Maven-local, exports from that clone, and then
asserts what a promotion review needs to see:

1. **no build-machine path in any served file** — the whole export tree is
   scanned byte-for-byte, not just `index.html`;
2. **the demo source tree is untouched** — checksummed before and after, plus a
   `git status` check on the demo checkout; and
3. **the artifact really runs** — the export smoke drives it in a real browser
   using the harness *from the fresh clone*, so the tooling is proven to ship.

```sh
scripts/web_fresh_checkout_smoke.sh \
  --template "$HOME/Library/Application Support/Godot/export_templates/4.7.2.stable/web_nothreads_release.zip" \
  --demo web3d --demo match3 \
  --evidence /tmp/web-fresh-checkout.json \
  /absolute/path/to/godot
```

The default demo set is `web3d` (an in-repo fixture, so the Kanama clone alone is
enough) plus `match3` (an external demo, exercising the demos checkout);
`--demo all` runs the whole corpus. `--kanama-source` / `--demos-source` accept a
local path for validating an unmerged branch, and `--skip-browser` reduces the
run to the export and artifact checks. The `--evidence` JSON records the clone
commits, per-demo checksums, payload sizes, protocol version and driver results.

### Browser Debugging

- **Chrome** — the driver self-launches headless Chrome and drives it over the
  DevTools Protocol. Godot's Compatibility renderer needs a WebGL context, which
  in headless Chrome comes from ANGLE's SwiftShader software path
  (`--enable-unsafe-swiftshader --use-angle=swiftshader`); do **not** pass
  `--disable-gpu`, which disables it. It collects console/exception events.
- **Firefox** — driven over WebDriver BiDi; console errors via
  `log.entryAdded`.
- **Safari** — driven over classic W3C WebDriver; needs a one-time
  `safaridriver --enable` and "Allow Remote Automation" in the Develop menu.
  SafariDriver exposes no browser-log endpoint, so the Safari gate asserts bridge
  callback/telemetry plus every gameplay and teardown invariant. Three traps,
  all of which have cost real debugging time:
    - **Retina coordinates.** Pointer coordinates are CSS client pixels (W3C),
      and Godot's own coordinate space is CSS pixels too. A driver that derives
      screen geometry from `canvas.width` — the `devicePixelRatio`-scaled
      backing store — is correct only at DPR 1, so it passes headless
      Chrome/Firefox and silently misses on a Retina Safari. Use
      `getBoundingClientRect()`.
    - **One `POST /actions` per gesture.** SafariDriver dispatches a pointer
      sequence on the trailing `DELETE /actions`, and does not carry pointer
      position across requests — a press sent in its own request lands at
      `0,0`. Put the whole press/move/release in a single request.
    - **The browser outlives its driver.** The automation Safari is not a child
      of `safaridriver`, so killing the driver leaks it; the driver reaps it by
      PID instead.

### Safari Gate

Safari cannot run headless. Chrome and Firefox gate the corpus headless, so
they run unattended; `safaridriver` drives a real Safari window on a logged-in
GUI session. The Safari gate is therefore a **local** gate, not a CI cell, and
two Safari runs must not be started concurrently on one machine.

### Safari on iPhone and iPad

Safari on iOS and iPadOS is hand-checked only, and no mobile-WebKit version
floor is claimed. Every iOS browser is WebKit. Hand-checking on a device on
the same network (`serve_export.py --lan --https`) is the standard recipe.
Safari on a USB-connected iPhone or iPad can additionally be driven over
WebDriver: enable Settings → Safari → Advanced → Remote Automation on the
device, keep it unlocked, and create a `safaridriver` session with
`platformName: iOS`, the device UDID (`xcrun xctrace list devices`), and
`acceptInsecureCerts: true` — one session per device at a time. No automated
iOS gate exists, and mobile WebKit is not part of the validated claim.

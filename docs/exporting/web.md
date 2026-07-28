# Web

## Current Status

The Web backend is **Experimental (Kotlin/Wasm preview)** on the Godot 4.7 stable
baseline. It compiles Kanama project scripts to **Kotlin/Wasm** and runs them
against a Godot 4.7 Web export through a generated per-call proxy and a versioned
JavaScript bridge (protocol 15). It is **not a Supported target**: the renderer is
single-thread Compatibility only, the browser matrix and performance budgets are
still being hardened, and there is no packaged install path yet.

**Evidence.** The full twelve-demo corpus — Bunnymark, Starter-Kit-Match3, dodge,
web3d, 3D-Platformer, squash, FPS, character-controller, third-person, Racing,
City-Builder and tps-demo — passes the automated production export smoke in
**Chrome** and **Firefox** (both CI cells) and **Safari** (a local gate — it has
no headless mode), each with a
play-and-teardown driver run, zero console errors, and live handles draining to
zero. Every corpus export is also proven to embed no build-machine paths in any
served file, and to be reproducible from a clean clone (see
[Fresh-Checkout Gate](#fresh-checkout-gate)).

**Browser version floors.** The declared floors live in one machine-readable
file, `scripts/web/browser_floors.json`, and every smoke run is checked against
them — a run on an older browser fails the gate instead of printing the same
`PASS` line as a declared one.

| Browser | Floor | Basis | Corpus validated at |
|---|---|---|---|
| Chrome | **130** | tested | 150 (headless) |
| Firefox | **141** | tested | 152–153 (headless) |
| Safari | **26.5** | validated-at | 26.5 / WebKit 605.1.15, macOS 26.5.1 |

"Tested" means the gate was run on that version *and* on the one below it
(2026-07-28, macOS arm64, protocol-15 export). Chrome 129 never boots the
Kotlin/Wasm module; 130 runs it — that is where WebAssembly JS String Builtins
shipped. The Firefox number is a **harness** bound, not an engine verdict: 141,
143 and 145 all pass, while 140 ESR and older never expose a reachable WebDriver
BiDi endpoint to the driver's launch recipe, so they cannot be judged either way.
Safari is "validated-at" only: it ships with the OS and cannot be installed side
by side, so no lower bound is testable at all.

**iOS and iPadOS have not been validated**; `safaridriver` drives desktop Safari
only, so no mobile-WebKit claim is made here.

This page is the reproducible export workflow. For the architecture — batching,
snapshots, handle generations, the bridge protocol — see
[Web Internals](../contributing/web-internals.md).

## How Web Differs

Unlike desktop, Android, and iOS, the Web backend uses **no JVM and no
FFM/PanamaPort path**. Project gameplay is ahead-of-time compiled to WebAssembly
(Kotlin/Wasm, which depends on the WasmGC and exception-handling proposals, so it
targets modern browsers). The Kanama Wasm module and Godot's own Emscripten/Wasm
runtime are **separate modules** that cannot share a heap, so calls cross as
typed commands over a JavaScript bridge rather than as direct FFI.

## Requirements

- **Godot 4.7 stable** editor binary (matching the pinned baseline).
- The **`web_nothreads_release`** export template for that exact Godot version.
  The single-thread template is required: the preview backend does not use
  threads or cross-origin isolation.
- A **modern browser** with WasmGC + exception handling (recent
  Chrome/Firefox/Safari).
- **Node.js** (only for the export-smoke harness, not for the export itself).
- Reproducible builds currently require
  `--no-daemon -Pkotlin.compiler.execution.strategy=in-process`; the Kotlin
  daemon can exhaust memory on these builds while the in-process path is stable.

## Web-Compatible Project Scripts

A project's JVM/desktop scripts are **not** directly Wasm-compatible: they take a
`java.lang.foreign.MemorySegment` script-constructor handle and may use JVM-only
pointer APIs. The Web export therefore uses a Wasm-compatible source set kept
next to the JVM sources at **`<project>/web/kotlin-src/`**:

- the script constructor takes a neutral `GodotHandle` instead of
  `MemorySegment`;
- raw pointer identity (`handle.address()`) is replaced by `isSameInstance()`.

The `web/` directory is the export's Kotlin script root, so files under
`web/kotlin-src/` resolve to `res://kotlin-src/*.kt` and match the scene script
attachments. (Single-sourcing JVM and Web scripts is planned future work.)

## Build The Web Scripts

`buildWebScripts` generates the GDScript proxy bundle (proxies + manifest +
protocol descriptor) and collects the Kotlin/Wasm runtime a project attaches:

```sh
./gradlew --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  :web-runtime:buildWebScripts
```

The bundle lands in `web-runtime/build/web-scripts/` with a
`build-web-scripts.report.json` recording the protocol version, renderer, and
files. No source maps are published.

## Export A Demo

`exportWeb` stages a disposable copy of the project, runs the Godot Web export,
installs the Kotlin/Wasm runtime and bridge, cache-busts the entry scripts, and
writes a self-contained served directory plus a release payload report. Point it
at a **clean checkout** of the demo (never a shared working tree); the Web script
root is auto-derived from `<project>/web`.

Each demo is selected with `-PkanamaWebDemo=<key>` and given its checkout with
the matching `-PkanamaWeb<Key>ProjectDir`:

| Key | Demo project | Key | Demo project |
|---|---|---|---|
| `match3` | Starter-Kit-Match3 | `thirdperson` | godot-4-3d-third-person-controller |
| `bunnymark` | Bunnymark | `charactercontroller` | godot-4-3d-character-controller-tutorial |
| `dodge` | godot-demo-2d-dodge-the-creeps | `racing` | Starter-Kit-Racing |
| `platformer` | Starter-Kit-3D-Platformer | `citybuilder` | Starter-Kit-City-Builder |
| `squash` | godot-demo-3d-squash-the-creeps | `tpsdemo` | tps-demo-kanama |
| `fps` | Starter-Kit-FPS | `web3d` | in-repo fixture (no checkout needed) |

Match3:

```sh
./gradlew --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  :web-runtime:exportWeb \
  -PkanamaWebDemo=match3 \
  -PkanamaGodotExecutable=/absolute/path/to/godot \
  -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip \
  -PkanamaWebMatch3ProjectDir=/absolute/path/to/checkout/Starter-Kit-Match3
```

Bunnymark (the validated 256-sprite V1Sprites variant):

```sh
./gradlew --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  :web-runtime:exportWeb \
  -PkanamaWebDemo=bunnymark \
  -PkanamaWebBunnymarkVariant=BunnymarkV1Sprites \
  -PkanamaGodotExecutable=/absolute/path/to/godot \
  -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip \
  -PkanamaWebBunnymarkProjectDir=/absolute/path/to/checkout/Bunnymark
```

The export lands in `web-runtime/build/web-export/<demo>/`. It is self-contained
— no workstation-absolute paths leak into the served HTML — and includes
`kanama-web/export-report.json` listing every served file, its size, the total
payload, the renderer, the thread setting, and the source-map policy.

## Serve The Export

The export must be served over HTTP (opening `index.html` from disk will not
load the Wasm modules). Any static server works. The repository ships a minimal
one that binds an ephemeral localhost port, sends the correct
`application/wasm` MIME type, and sets `Cache-Control: no-store`:

```sh
python3 scripts/web/serve_export.py web-runtime/build/web-export/match3
# prints PORT=<n>; open http://127.0.0.1:<n>/
```

### Testing On A Phone Or Tablet

Loopback is the default so an export server is never reachable from the network
unless asked for. `--lan` binds every interface and prints the address to open on
the device:

```sh
python3 scripts/web/serve_export.py --lan web-runtime/build/web-export/match3
# prints PORT=<n> and LAN=http://<this-machine>:<n>/
```

This is the **only** way to exercise iOS or iPadOS: `safaridriver` drives desktop
Safari only, so mobile WebKit cannot be automated and has to be hand-checked on a
real device with the phone on the same network. Nothing on mobile WebKit is
validated today — see Known Limitations.

## Run The Export Smoke

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
teardown to baseline, stale-handle rejection, console-error checks, and a
protocol-version match.

Read the harness's own `web_export_smoke: PASS` line, not a driver's check count.
The driver's checks and the envelope schema are two different gates: a demo can
pass 13/13 of its own checks and still be rejected by the schema (which is what
enforces `liveAfterTeardown === 0`), and that is exactly how one demo stayed
un-gated on every engine for weeks.

## Browser Matrix

`web_ci_matrix.sh` is the corpus-wide gate: it exports each demo and drives it in
each requested browser through `web_export_smoke.sh`, then aggregates every cell
into one evidence JSON plus a Markdown summary.

```sh
scripts/web_ci_matrix.sh \
  --godot /absolute/path/to/godot \
  --template "$HOME/Library/Application Support/Godot/export_templates/4.7.stable/web_nothreads_release.zip" \
  --demos-dir /absolute/path/to/kanama-demos \
  --demo-set full \
  --engine chrome --engine firefox \
  --evidence /tmp/web-matrix.json
```

Every cell runs even after an earlier one fails, so a red run reports the whole
picture. `--demo-set pr` is the per-PR subset (`match3`, `web3d`, `dodge` — a
pointer-drag demo, a 3D demo needing no demos checkout, and a full-lifecycle
demo); `--demo-set full` is the 12-demo corpus. Per-demo budgets live in
`scripts/web/demos.sh`; scale them for a slower host with `--timeout-scale`
rather than editing them, so local and CI numbers stay comparable.

**Chrome and Firefox are the CI cells** (`.github/workflows/web.yml`): the PR
subset on every Web-relevant pull request, the full corpus on push to `main` and
nightly. **Safari is a local pre-promotion gate, not a CI cell** — see Known
Limitations. Run it with the same script, `--engine safari`, one run at a time.

### Regression Cadence

Which gate runs when, and where. A gate that is not on this list runs nowhere.

| Gate | Cadence | Where |
|---|---|---|
| PR subset × Chrome + Firefox | every Web-relevant pull request | CI (`web` workflow) |
| `ci` corpus × Chrome + Firefox (everything a runner can build) | push to `main`, and nightly | CI |
| **tps-demo** | before a release tag | local (OOM-killed on a hosted runner) |
| Soak (10 min, `--demo soak`) | nightly | CI |
| Full corpus on **Safari** | before a release tag, and before any promotion decision | local (no headless mode) |
| Fresh-checkout gate | before a release tag | local |
| Browser floor re-bisect | when a floor is claimed to move, or a browser major ships that breaks a cell | local |
| Everything above | **on a Godot baseline bump** — the export template, generated proxy and bridge protocol all move together | local + CI |

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

### What CI Runs, And What Stays Local

Not everything can or should run on a hosted runner, and the two reasons are
different:

- **Local-only** — CI *structurally cannot* do it, and no fix changes that.
  **Safari** (no headless mode; it needs a logged-in GUI session) and
  **tps-demo** (its Kotlin/Wasm compile is OOM-killed on a 16 GB GitHub runner)
  are both in this tier. They are release gates a maintainer runs by hand, and
  they pass there.
- **Quarantined** — a real defect, temporary, tied to a task. See below.

`--demo-set ci` is the corpus minus the local-only demos and is what the workflow
runs; `--demo-set full` always means the full corpus, so a local run is never
quietly narrowed. Skipped demos are **announced and written into the evidence
JSON** with their reason, because a corpus that silently shrinks is how "the
corpus is green" stops meaning anything.

### Quarantined Cells

A known-failing `demo:engine` pair can be **quarantined** in
`scripts/web/demos.sh` with a reason that names a task. A quarantined cell still
exports, still runs and still reports — it just does not fail the build. Deleting
the demo from the matrix instead would be the trap this gate exists to close: the
corpus would look green because nobody was looking.

A quarantined cell that **passes** is reported just as loudly, with an explicit
"lift the quarantine" line, because a stale quarantine is worse than none.
Lifting one is a one-line deletion.

Currently quarantined: `dodge:firefox`, `squash:chrome` and `squash:firefox` —
task 71, spawned mobs never free on a Linux host. Both demos pass on macOS, and
`dodge:chrome` passes on Linux, so it is neither a browser nor a demo property.

### Bumping The Demos Pin

The workflow checks out `kanama-demos` at the `DEMOS_REF` commit pinned in
`.github/workflows/web.yml`. This is deliberate: an unpinned checkout lets an
unrelated demo-repo commit redden every Kanama pull request. When a demo port
lands in `kanama-demos`, bump `DEMOS_REF` in the same pass — a Kanama change that
needs new demo code is not green until both sides are pinned together.

## Fresh-Checkout Gate

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
  --template "$HOME/Library/Application Support/Godot/export_templates/4.7.stable/web_nothreads_release.zip" \
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

## Browser Debugging

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

## Renderer And Thread Constraints

- **Compatibility renderer only** (`rendering_method = gl_compatibility`). The
  Forward+/Mobile renderers are not used for Web.
- **Single thread.** The export uses the `web_nothreads` template and does not
  require COOP/COEP cross-origin isolation, so it can be served from a plain
  static host. Threaded Godot Web is out of scope for the preview.

## Payload And Source Maps

- The Kotlin gameplay Wasm is **content-hashed by the build** (its filename is
  its hash), so it is cache-busted automatically when gameplay changes.
- The two fixed-name entry scripts (`kanama-web-spike.js`,
  `kanama-web-bridge.js`) are cache-busted with a content-derived `?v=<hash>`
  query string stamped into `index.html`.
- **No source maps ship** (webpack `sourceMaps = false`); both
  `buildWebScripts` and `exportWeb` fail loudly if a `.map` file appears.
- `kanama-web/export-report.json` reports the full payload for budget tracking.

## Known Limitations

- **Safari cannot run headless.** Chrome and Firefox gate the corpus headless, so
  they run unattended; `safaridriver` drives a real Safari window on a logged-in
  GUI session. The Safari gate is therefore a **local** gate, not a CI cell, and
  two Safari runs must not be started concurrently on one machine.
- **Safari on iOS/iPadOS is unvalidated.** Every iOS browser is WebKit, but
  `safaridriver` covers desktop Safari only. No mobile-WebKit version floor is
  claimed.
- **Lifecycle virtuals are limited to what the proxy dispatches**: `_ready`,
  `_process`, `_physics_process`, `_draw`, `_exit_tree`, `_input`, and
  `_unhandled_input`. Anything else — `@OnEnterTree` in particular — is rejected
  at build time with a KSP error naming the script and the function, rather than
  compiling and then never running. Move the body into an `@OnReady` function.
- Single-thread Compatibility renderer only; no threads, no cross-origin
  isolation.
- No Web editor, no compiler, no hot reload, no Web GDExtension, and no TeaVM or
  Kotlin/JS production path.
- A packaged/addon install path (exporting without the Kanama checkout) is not
  yet available; the current workflow is a source-checkout export.
- Not a Supported target: no support claim, and the corpus/browser matrix and
  budgets are still being hardened.

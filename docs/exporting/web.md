# Web

## Current Status

The Web backend is **Experimental (Kotlin/Wasm preview)** on the Godot 4.7 stable
baseline. It compiles Kanama project scripts to **Kotlin/Wasm** and runs them
against a Godot 4.7 Web export through a generated per-call proxy and a versioned
JavaScript bridge (protocol 6). It is **not a Supported target**: two demos are
validated (Starter-Kit-Match3 and Bunnymark) as production Web exports, the
renderer is single-thread Compatibility only, and the corpus/browser matrix is
still growing.

**Evidence.** Both demos pass the automated production export smoke in **Chrome**
(the CI gate) and **Firefox**. In **Safari**, Bunnymark passes the automated gate
and Match3 is verified running by hand (its runtime-selected swipe works and
matches/collapses/refills); the automated SafariDriver gate cannot synthesize
Match3's drag reliably, so that one cell is a manual local check — see Known
Limitations.

This page is the reproducible export workflow for those two validated demos. For
the architecture — batching, snapshots, handle generations, the bridge protocol
— see [Web Internals](../contributing/web-internals.md).

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

**Chrome is the intended CI gate.** Firefox and Safari are explicit **local
release gates** run before a promotion. Each gate asserts gameplay deltas,
crossing budgets, handle/callback/scheduler teardown to baseline, stale-handle
rejection, console-error checks, and a protocol-version match.

## Browser Debugging

- **Chrome** — the driver self-launches headless Chrome and drives it over the
  DevTools Protocol. Godot's Compatibility renderer needs a WebGL context, which
  in headless Chrome comes from ANGLE's SwiftShader software path
  (`--enable-unsafe-swiftshader --use-angle=swiftshader`); do **not** pass
  `--disable-gpu`, which disables it. It collects console/exception events.
- **Firefox** — driven over WebDriver BiDi; console errors via
  `log.entryAdded`.
- **Safari** — driven over WebDriver. SafariDriver exposes no browser-log
  endpoint, so the Safari gate asserts bridge callback/telemetry plus every
  gameplay and teardown invariant, and needs device-pixel-ratio-aware pointer
  coordinates.

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

- Only **Starter-Kit-Match3** and **Bunnymark** are validated; the wider demo
  corpus and 3D are not yet on Web.
- **Safari automated Match3 drag.** The Match3 export runs correctly in Safari by
  hand, but SafariDriver's synthesized pointer drag does not reliably trigger the
  swipe in the automated gate (coordinates and Godot picking are correct; the
  drag-to-swipe is a WebDriver synthesis limitation). The automated Safari gate
  covers Bunnymark; Safari Match3 is a manual local check.
- Single-thread Compatibility renderer only; no threads, no cross-origin
  isolation.
- No Web editor, no compiler, no hot reload, no Web GDExtension, and no TeaVM or
  Kotlin/JS production path.
- A packaged/addon install path (exporting without the Kanama checkout) is not
  yet available; the current workflow is a source-checkout export.
- Not a Supported target: no support claim, and the corpus/browser matrix and
  budgets are still being hardened.

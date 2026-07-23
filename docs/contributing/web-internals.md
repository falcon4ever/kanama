# Web Internals

This page records the current Web implementation path. Web is **In development**
on the Godot 4.7 stable baseline — not a Supported target, with no packaged
export workflow and no user-facing export guide yet. The API/build flow is less
settled than desktop, Android, or iOS.

## Where Web Sits Relative to the Other Backends

The four platforms split by **how Kotlin reaches Godot**, and Web belongs with
iOS, not with the JVM platforms:

- **Desktop and Android (JVM):** Kotlin runs on an embedded JVM inside the Godot
  process and calls Godot's C GDExtension ABI directly through the Foreign
  Function & Memory API (PanamaPort on Android). In-process, synchronous, with
  real pointer/struct access to Godot memory.
- **iOS (Kotlin/Native):** no JVM. Kotlin is AOT-compiled to a static
  `.xcframework` and reaches Godot through a hand-written C shim doing generic
  `ptrcall`. Still in-process and able to share raw pointers.
- **Web (Kotlin/Wasm):** no JVM. Project gameplay is AOT-compiled to
  WebAssembly (Kotlin/Wasm, which depends on the WasmGC and exception-handling
  proposals, so it targets modern browsers). The Kanama Wasm module and Godot's
  own Emscripten/Wasm runtime are **separate modules** that cannot share a heap
  or trade raw pointers, so calls cross as **typed commands over a JavaScript
  bridge** rather than as direct FFI.

Web therefore reuses the same generated wrapper surface as the other platforms
but pays the highest marshalling cost, which shapes everything below: it batches
crossings, mirrors state in snapshots, and tracks handles by generation.

## What Works

Two production Godot 4.7 Web exports pass an automated, assertion-driven play
sequence (not a page-load check) in headless **Chrome 150**, **Firefox 153**,
and **Safari 26.5**:

- **Bunnymark** — 256 sprites, one bounded position batch, and deterministic
  257-to-zero handle teardown.
- **Match3** (`Starter-Kit-Match3`) — original board, a runtime-selected legal
  swap, match/collapse/refill, particles, audio, restart, and two full
  zero-state teardowns.

Each run asserts gameplay deltas, crossing budgets, and handle/callback/scheduler
teardown to baseline, and fails on stale-handle use.

## Architecture

### Kotlin/Wasm backend

Gameplay and the Kanama Web runtime live in `web-runtime/` and compile to the
`wasmJs` target. The platform-neutral API and gameplay proxies are in
`src/commonMain`; the Wasm entry point, command interop, and the Godot-facing
backend are in `src/wasmJsMain`
(`Main.kt`, `WebCommandInterop.kt`, `WebCommonGodotBackend.kt`).

### Versioned JavaScript bridge

`web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js` is the seam between
the Kanama Wasm module and Godot's Web export. It carries a
`KANAMA_WEB_PROTOCOL_VERSION` (currently **6**); startup rejects a mismatch
between the bridge constant and the value the Wasm backend reports, so a bridge
and a backend built from different revisions fail loudly instead of drifting.

### Typed backend seam and fail-loud coverage

The typed per-call families are shared with the other platforms through
`scripts/platform_backend_calls.json` — the same contract desktop, Android, and
iOS use. `scripts/generate_web_gameplay_coverage.py` harvests the calls each
demo actually executes and **fails if a demo call has no admitted backend
family**, so coverage metadata cannot be silently erased. The current report has
zero blocking calls and keeps `GodotObject.emit_signal_typed` visible as one
explicit nonblocking unsupported family rather than pattern-hiding it.

### Batching, snapshots, and handle generations

Because a bridge crossing is a real module boundary, the design minimizes and
mirrors crossings:

- **Batching** — many mutations coalesce into one crossing (e.g. Bunnymark's
  single 256-position batch) instead of one call per operation.
- **Property snapshots** — the Kotlin side keeps a read-your-write mirror of
  Godot object state so reads need not round-trip through JS.
- **Handle generations** — handles are opaque IDs across the bridge, not live
  pointers, so stale generations are detected and rejected explicitly.

### Lifecycle ownership

Handles, callbacks, connections, tweens/tweeners, scheduler continuations, audio
players, particles, resource handles, snapshots, queued commands, and generation
tables are owned per script/owner and released at owner, direct, and full
teardown. The validated runs return every owned registry to baseline after both
gameplay and full scene teardown; stale handle use after teardown fails.

## Validation Fixtures

The current fixtures are per-browser driver scripts plus machine-readable JSON
results (Bunnymark and Match3, one file per browser) with matching screenshots.
A run is not green from page load alone — it must satisfy gameplay assertions,
final state, the crossing budget, handle/callback/scheduler teardown, console
error checks, and protocol-version match.

Browser-specific notes:

- **Safari** WebDriver input needs device-pixel-ratio-aware conversion from
  Godot's logical canvas to trusted pointer coordinates, or the Retina backing
  store targets the wrong tile. SafariDriver does not expose the legacy browser
  log endpoint, so the Safari gate asserts bridge callback/failure telemetry
  plus every gameplay and teardown invariant.
- **Firefox** collects console errors through BiDi `log.entryAdded`.
- **Chrome** collects CDP console/exception events and is the intended CI path.

The two WorkerThreadPool `PagedAllocator` lines printed after a `get_tree().quit()`
from `_process` are an upstream Godot 4.7 shutdown diagnostic — reproduced by an
equivalent resource-heavy pure-GDScript project — and are classified narrowly as
engine shutdown noise, not a Kanama boundary failure.

## Contributor Workflow

Reproducible export builds currently require
`--no-daemon -Pkotlin.compiler.execution.strategy=in-process`; two Kotlin daemon
attempts exhausted memory while the in-process path is stable. Build exports from
a disposable clean demo archive, and never point a formatting task at a shared
demo checkout.

Narrow checks while iterating, then the broader gate:

```sh
node --check web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js
./gradlew :web-runtime:compileKotlinWasmJs :web-runtime:generateWebGameplayCoverage
mkdocs build --strict
./scripts/local_ci.sh /path/to/godot-4.7-stable
```

## Out of Scope

No Web editor or compiler, no hot reload, no threads, no TeaVM or Kotlin/JS
production path, and no Supported status. The user-facing export workflow, an
`exporting/web.md` guide, and any Experimental-preview announcement are tracked
separately and are not part of this in-development backend.

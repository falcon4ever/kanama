# Web Internals

This page records the current Web implementation path. Web is **Experimental
(Kotlin/Wasm preview)** on the Godot 4.7 stable baseline — not a Supported target,
with a source-checkout export workflow (no packaged addon) and a user-facing
[export guide](../exporting/web.md). The API/build flow is less settled than
desktop, Android, or iOS.

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
backend are in `src/wasmJsMain` (`Main.kt`, `WebCommandInterop.kt`). The backend
is split three ways: `WebCommonGodotBackend.generated.kt` (generated opcode
dispatch), `WebBackendBookkeeping.kt` (hand-written Web-only state + hooks), and
`WebBackendTransport.kt` (hand-written `js(...)` bridge externs) — see the
Backend-dispatch codegen section below.

### Versioned JavaScript bridge

`web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js` is the seam between
the Kanama Wasm module and Godot's Web export. It carries a
`KANAMA_WEB_PROTOCOL_VERSION` (currently **14**); startup rejects a mismatch
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

### Backend-dispatch codegen: generated dispatch + hand-written transport (Task 60a)

The Web backend maps each opcode from `platform_backend_calls.json` to a Godot
call and its JS-bridge codec. Historically that whole file was hand-written; Task
60a makes the **dispatch generated** so admitting a new call family is a
regenerated diff, not a bespoke hand-written path — the mechanism that let iOS
reach full class coverage.

**Decision (Task 60a, 2026-07-23): generate the mechanical dispatch, keep
Web-only stateful bookkeeping hand-written next to it ("Option A").** The
generator (`scripts/generate_web_backend.py`) reads the shared
`platform_backend_calls.json` (via `scripts/platform_backend_contract.py`, the
same policy loader desktop/iOS use) joined with a **Web-local** per-opcode policy
declared in the generator, and emits `WebCommonGodotBackend.generated.kt`: the
`when (opcode)` routing, the execution-mode / argument-range guards, and calls to
the `js(...)` bridge externs. The genuinely Web-specific bookkeeping — property
snapshots and read-your-write updates, browser handle-kind tracking (RESOURCE /
NODE / OBJECT), and free-time cache clearing — stays hand-written in
`WebBackendBookkeeping.kt`, reached through a stable, opcode-neutral hook
interface; the `js(...)` externs are hand-written in `WebBackendTransport.kt`.
This matches the roadmap's framing of admitting a family as "a regenerated diff
plus a transport implementation."

Regenerate with `./gradlew :web-runtime:generateWebBackendDispatch` (then
`ktfmtFormat`). `./gradlew :web-runtime:checkWebBackendDispatch` (also in
`local_ci.sh` and wired into `check`) fails loud if the committed dispatch drifts
from the shared contract; it compares token streams, so it is insensitive to
ktfmt reflow but catches any changed arm, opcode, extern, or guard.

**Why not fully data-driven ("Option B" — encode the snapshot/handle policy into
`platform_backend_calls.json` and generate the entire file byte-for-byte):**
`platform_backend_calls.json` is the **platform-neutral** contract that desktop,
Android, and iOS also consume (via the generated `InitialGodotCallDescriptors`).
Those backends call Godot in-process and do none of Web's caching; folding Web-only
snapshot/handle rules into the shared file would stop it being neutral and over-fit
it to the two-demo corpus. Option A keeps the shared model clean.

**When to reconsider Option B:** if the hand-written bookkeeping companion grows
faster than the generated dispatch as the corpus expands (60b–60e) — i.e. if
"admitting a family" routinely means non-trivial new hand-written state rather than
a near-mechanical hook wiring — revisit encoding a Web-side (not shared-model)
policy layer so more of the bookkeeping generates. Record any such change here.

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

### RefCounted resource ownership (create/close on the handle bridge)

The user-facing contract is the **same** as the pointer backends —
[*close what you create*](../game-dev/properties-resources.md): a factory
(`X.create()`) or a temporary load hands back an owning handle, you hand the value
to the engine, then release your handle with `use { }`/`close()`; the engine keeps
its own reference so the object lives on. The **implementation is different**, and
that difference is the answer 60a owed for this backend (issue #91 was an
FFI-backend bug and its fix does not apply here):

- **Pointer backends** (desktop/Android/iOS) construct via
  `classdb_construct_object3` (an already-owning object) and `close()` `ptrcall`s
  `RefCounted.unreference()` to drop the caller's reference.
- **Web holds no pointers and makes no `ptrcall`.** The real Godot object lives
  engine-side and **GDScript refcounts it**; the bridge only carries an opaque
  handle ID interned in the owning script's object table. So the Web model is
  **"drop the handle = release the reference"**: `close()` (e.g.
  `Texture2D.close()` → `releaseWebResource`) emits a release-handle bridge command
  that runs the generated GDScript `_kanama_resource_release`, which *erases the
  handle from that script's `_kanama_object_handles`* — dropping GDScript's
  reference. If nothing else holds the object, its engine-side refcount reaches
  zero and Godot frees it; if the caller already handed it to a node/resource, the
  engine's own reference keeps it alive.

**There is no `init_ref` claim to get wrong** (the #91 root cause) because the
bridge never constructs the native object itself — it asks GDScript to, and
GDScript's return value is already a live, refcounted reference. **The
created-then-handed-off-then-closed case survives** for the same reason it must on
the pointer backends: the handoff (e.g. `AudioStreamPlayer.setStream`) is applied
**before** the temporary handle is released, so the engine takes its reference
first. `AudioStreamPlayer.setStreamFromPath` is the canonical example —
`load → setStream(player) → finally { releaseWebResource(temp) }` — and it is
covered by an explicit **bridge-level ownership assertion** in the Match3 export
smoke (`scripts/web/drivers/demos/match3.mjs`): the stream resources are loaded
and handed off with zero failures, they play (survived their temp release), and
every resource handle returns to baseline at teardown (no leak).

Practical consequence for generated wrappers: a Web `X.create()` proxy returns an
**owning** handle and `close()` is a real "release this reference" command — the
bridge does **not** silently GC handles behind your back, so *the same
`use { }`/`close()` discipline the docs teach applies on Web*, and forgetting it
leaks the same way (until the owning script tears down and its whole object table
is released).

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

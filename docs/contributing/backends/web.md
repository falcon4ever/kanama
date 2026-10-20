# Web Internals

This page records the current Web implementation path. Web is **Experimental
(Kotlin/Wasm preview)** on the Godot 4.7 stable baseline — not a Supported target,
with a source-checkout export workflow (no packaged addon) and a user-facing
[export guide](../../exporting/web.md). The API/build flow is less settled than
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

Web therefore exposes the same Godot classes as the other platforms but does not
share their generated ptrcall wrappers: its wrappers are generated from the Web
call contract described below (one file per Godot class under
`web-runtime/.../api/generated/`, task 96) and pay the highest marshalling cost,
which shapes everything below: it batches crossings, mirrors state in snapshots,
and tracks handles by generation.

## What Works

A twelve-demo corpus of production Godot 4.7 Web exports passes an automated,
assertion-driven play sequence (not a page-load check) in headless
**Chrome 150**, headless **Firefox 153**, and **Safari 26.5** (WebKit 605.1.15;
Safari has no headless mode, so it runs windowed on a GUI session). Two
representative members:

- **Bunnymark** — 256 sprites, one bounded position batch, and deterministic
  257-to-zero handle teardown.
- **Match3** (`Starter-Kit-Match3`) — original board, a runtime-selected legal
  swap, match/collapse/refill, particles, audio, restart, and two full
  zero-state teardowns.

The full list is in [Export for the Web](../../exporting/web.md#exporting-a-demo).

Each run asserts gameplay deltas, crossing budgets, and handle/callback/scheduler
teardown to baseline, and fails on stale-handle use.

## Architecture

### Kotlin/Wasm backend

Gameplay and the Kanama Web runtime live in `web-runtime/` and compile to the
`wasmJs` target. The Web API wrappers are in `src/commonMain/.../api/`: the
Godot classes are generated into `api/generated/<Class>.kt` (see "Generated
wrapper tree" below) and the hand-shaped remainder sits beside them by concern
(`WebGodotApi.kt` base + coverage markers, `WebSignals.kt`, `WebCoroutines.kt`,
`WebMath.kt`, `WebFacades.kt`, `WebPhysicsQuery.kt`, `WebScriptResources.kt`,
`WebFrameScheduler.kt`). The Wasm entry point, command interop, and the
Godot-facing backend are in `src/wasmJsMain` (`Main.kt`, `WebCommandInterop.kt`).
The backend is split three ways: `WebCommonGodotBackend.generated.kt` (generated
opcode dispatch), `WebBackendBookkeeping.kt` (hand-written Web-only state +
hooks), and `WebBackendTransport.kt` (hand-written `js(...)` bridge externs) —
see the Backend-dispatch codegen section below.

### Versioned JavaScript bridge

`web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js` is the seam between
the Kanama Wasm module and Godot's Web export. It carries a
`KANAMA_WEB_PROTOCOL_VERSION` (currently protocol 39); startup rejects a mismatch <!-- kanama-claim: protocol -->
between the bridge constant and the value the Wasm backend reports, so a bridge
and a backend built from different revisions fail loudly instead of drifting.

### Typed backend seam and fail-loud coverage

One source feeds two mechanisms. `extension_api.json` drives the native wrapper
generator (`scripts/generate_api_wrapper.py`: typed ptrcall wrappers that let
desktop, Android, and iOS call Godot in-process) and, separately, the **Web call
contract**: `scripts/platform_backend_calls.json` is Web's opcode table — which
engine methods Web can reach, each with its pinned hash, call shape, execution
mode, and return ownership — compiled into `kanama-common-api` as
`InitialGodotCallDescriptors` and dispatched as typed commands over the bridge.
`./gradlew :kanama-common-api:checkPlatformBackendContract` validates every
entry's hash and signature against the same `extension_api.json`, so both
mechanisms pin the same engine methods. No native backend dispatches through the
contract (task 95 removed the never-called desktop/iOS adapters); its one
implementer is the generated Kotlin/Wasm backend.
`scripts/generate_web_gameplay_coverage.py` harvests the calls each demo
actually executes and **fails if a demo call has no admitted backend
family**, so coverage metadata cannot be silently erased. The current report has
zero blocking calls, and the families a demo calls that the backend does not
model (getters of write-only properties, for example) stay listed as explicit
nonblocking unsupported entries rather than being pattern-hidden.

### Backend-dispatch codegen: generated dispatch + hand-written transport (Task 60a)

The Web backend maps each opcode from `platform_backend_calls.json` to a Godot
call and its JS-bridge codec. Historically that whole file was hand-written; Task
60a makes the **dispatch generated** so admitting a new call family is a
regenerated diff, not a bespoke hand-written path — the mechanism that let iOS
reach full class coverage.

**Decision (Task 60a, 2026-07-23): generate the mechanical dispatch, keep
Web-only stateful bookkeeping hand-written next to it ("Option A").** The
generator (`scripts/generate_web_backend.py`) reads
`platform_backend_calls.json` (via `scripts/platform_backend_contract.py`)
joined with a per-opcode transport/bookkeeping policy declared in the generator,
and emits `WebCommonGodotBackend.generated.kt`: the
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
from the contract; it compares token streams, so it is insensitive to
ktfmt reflow but catches any changed arm, opcode, extern, or guard.

**Why not fully data-driven ("Option B" — encode the snapshot/handle policy into
`platform_backend_calls.json` and generate the entire file byte-for-byte):**
`platform_backend_calls.json` is a **call table** — which engine methods Web can
reach, with their pinned hashes, shapes, execution modes, and return ownership —
and that is exactly what `checkPlatformBackendContract` and the contract test
validate against `extension_api.json`. Snapshot slots, handle-kind tracking, and
cache-clearing hooks are Web runtime state, not facts about engine methods;
folding them into the table would over-fit it to the demo corpus and put data the
engine cannot validate behind the hash gate. (The original argument — keeping the
table neutral for native backends that "also consume" it — no longer applies:
task 95 recorded that no native backend ever dispatched through it.) Option A
keeps the call table a call table.

**When to reconsider Option B — the growth metric (task 96, review R3/R13).**
The trigger used to watch one file pair (the bookkeeping companion against the
generated dispatch, a 0.21 ratio) and missed four fifths of the hand-written Web
code. It is now the **sum of every hand-written Web family** against everything
generated from the contract, printed by

```sh
python3 scripts/web_hand_metric.py --ref origin/main --ref HEAD
```

and **pasted into every admission PR** (an admission is any PR that adds an
opcode to `platform_backend_calls.json`). The families are: hand-written
wrappers (`api/*.kt` outside `generated/`), the wrapper policy
(`generate_web_wrappers.py`, per-opcode/per-class policy and custom sections),
the contract (`GodotBackendContract.kt`), the dispatch companion
(`WebBackendBookkeeping.kt` + `WebBackendTransport.kt` + `generate_web_backend.py`),
the rest of the runtime (`Main.kt`, interop, generic call, `web/`), the script
emitter (`WebScriptCodeEmitter.kt`, tracked on its own line because it is the
one hand file that was still growing: 5,006 lines, +1,430 in Jul→Aug), and the
bridge (`kanama-web-bridge.js`). Task 96 itself moved the metric from
20,931 hand / 5,181 generated (4.04) to 17,875 hand / 11,108 generated (1.61):
the probe classes and the eleven demo-named wrapper files are gone, and what
replaced them is 1,285 lines of hand-shaped facades plus the generator's policy.

Reconsider lines: revisit encoding a policy layer beside the call table (Option
B) when an admission adds more hand-written lines than generated ones, or when
the emitter grows by more than a few hundred lines in a parcel without a new
dispatched shape to show for it. Record the numbers and the decision here.

### Generated wrapper tree: one class, one file, from the same contract (task 96)

The Web API classes under `web-runtime/src/commonMain/.../api/generated/` are
generated by `scripts/generate_web_wrappers.py` from the same
`platform_backend_calls.json` the dispatch generator reads, joined with
`extension_api.json` for names, argument widths, defaults, the class hierarchy
and the property table:

- **One member per opcode, on the Godot class that owns it.** `Node.get_child`
  becomes `Node.getChild(idx: Int, includeInternal: Boolean = false)`; an
  argument the shape does not carry is exposed with Godot's default and a
  `require(...)` guard, so Web says "supports only the default" loudly instead
  of dropping it silently. Int widths follow the desktop generator (`int32`
  meta → `Int`, else `Long`); handle returns wrap into the nearest generated
  class (`Node?`, `Material?`), and the object returns Godot marks
  `meta: "required"` are non-null (`Node.createTween(): Tween`) through the
  same `requireGodotReturn` as desktop/iOS — a null throws
  `IllegalStateException("Godot returned null from required <Class>.<method>")`.
  No object return is a raw `GodotHandle`: a class whose Web wrapper is a
  `WEB_HANDSHAPED` facade is wrapped in it (`wrap_facade` policy:
  `SceneTree.getRoot(): Window`), and the gate rejects a raw handle.
- **Typed enums (task 128 C).** Every slot Godot types `enum::X` /
  `bitfield::X` uses the same value class as desktop/iOS (`Node.ProcessMode`,
  `GodotObject.ConnectFlags`, `GodotError`), with the same value names: the
  generator imports `scripts/godot_enum_model.py` and reads the frozen prefix
  lock `scripts/enum_prefix_lock.json` (it never writes it; the native generator
  owns it). The bridge ABI is unchanged — an enum crosses as its raw number
  (`.value` into the command, `X(raw)` out of a return). Each generated class
  nests ALL its Godot enums (desktop parity, not only the ones a Web slot
  names); the 22 global enums and the `GodotEnumValue` marker are in the
  generated `GlobalEnums.kt` (with desktop's four renames); an enum owner a Web
  slot names but the tree does not generate becomes an enum-only class
  (`object PhysicsServer3D`, for `PhysicsBody3D.setAxisLock(axis:
  PhysicsServer3D.BodyAxis)`); and each hand-written facade under `api/` that
  stands in for an enum-owning Godot class (`Window`, `DisplayServer`, `Time`,
  `IP`, the multiplayer facades) carries a `GENERATED ENUMS` region the
  generator fills and `--check` holds to a regen. No `@JvmInline`: Kotlin/Wasm
  inlines a value class without it. The Web `Any?` encoders (generic call,
  `ConfigFile.setValue`, Variant-style `emitSignal`) map a `GodotEnumValue` to
  INT. `scripts/check_web_typed_enums.py` (local_ci) gates the slots (a member
  is tied to its Godot method by the opcode descriptor it dispatches, a hand
  facade member by owner + name, a facade property by the Godot property of the
  same name), the required returns, that no Godot enum/object return a native
  backend exposes comes back as `Unit` on Web, and the names.
- **Properties come from Godot's property table.** Both accessors admitted →
  `var`; setter only → a write-only `var` whose getter is the
  `unsupportedWebGameplayFamily("Class.getter")` coverage marker; getter only →
  `val`. Non-enum class constants (`GridMap.INVALID_CELL_ITEM`) render from the
  API as `const val`; enums are value classes (above).
- **Every member has an import-compat extension** (`fun Node.setProcess(...)
  = setProcess(...)`, suppressed shadowing), because the Web-only override
  sources in the demos import members by name; the 25 hand-written aliases
  this replaces are gone.
- **Hand-shaped content is explicit.** `CLASS_POLICY[...]["custom"]` in the
  generator carries the members the contract cannot express (Variant-style
  `emitSignal`/`tweenProperty` dispatchers over the typed arms, Node3D's
  composed `basis`/`transform`, `getChildren`, `setStreamFromPath`, factories),
  so a regen is lossless. Every `RefCounted` gets the one `close()` and
  `AutoCloseable` from the generated `RefCounted` itself (see "RefCounted
  handles" below), so no class carries a release policy of its own. `WEB_HANDSHAPED` names the one
  facade that stays hand-written under `api/` and dispatches an opcode directly: the ray query
  (`WebPhysicsQuery.kt`), the owner-bound `World3D` / `PhysicsDirectSpaceState3D` with desktop's
  signatures (`Node3D.getWorld3d(): World3D?`, `directSpaceState`, `intersectRay(query): Map`). The
  query parameters are a Kotlin value with desktop's whole `PhysicsRayQueryParameters3D` surface
  (a `WEB_INERT_FACADES` entry), and the applier builds the engine object from them in the querying
  node's world, so a ray is one crossing and makes no handle (a generated path would make four
  crossings and a RefCounted handle per ray). The other hand facades are listed in
  `WEB_INERT_FACADES` (`WebFacades.kt`, the single-player session in `WebMultiplayer.kt`); the rest of
  `api/` (`WebSignals.kt`, `WebCoroutines.kt`, `WebMath.kt`, `WebScriptResources.kt`,
  `WebFrameScheduler.kt`) is glue with no opcode.
- **RIDs are values.** A `RID` crosses the bridge as its 64-bit id in text (the Variant codec's
  `23:<id>`, or a packed query string such as the ray query's), never in an int32 or double slot:
  ids pass 2^53 after about two million RID allocations, which a double rounds and an int32 cuts.
  The bridge keeps no table of RIDs (they have no lifetime signal); a stale RID is the engine's to
  judge, as on desktop (excluded, it excludes nothing; handed to a server method, Godot prints its
  own error). So every RID member is a `WEB_GENERIC_METHODS` row (`getRid()` is `cached_fun`: one
  read per handle, kept in `WebHandleCache`), and the generator's `--check` refuses a contract entry
  that binds a RID to a typed slot.
- **Retired opcodes.** Opcode numbers are append-only. An opcode the Web no longer dispatches keeps
  its number as a tombstone in `platform_backend_calls.json` (`{"opcode": N, "retired": "<why>"}`):
  nothing describes, dispatches or wraps it, and no later entry takes its number. Opcode 170
  (`SpringArm3D.add_excluded_object`, which took the body's handle and derived the RID
  applier-side) is the first.

Regenerate with `./gradlew :web-runtime:generateWebWrappers` (or
`python3 scripts/generate_web_wrappers.py`). The gate,
`python3 scripts/generate_web_wrappers.py --check` (`local_ci.sh` stage "web
wrapper tree drift gate", `:web-runtime:checkWebWrappers`, wired into `check`),
fails byte-for-byte drift (ktfmt does not touch `api/**`), a stray or missing
file, a hand-written file under `api/` that dispatches an opcode outside the
`WEB_HANDSHAPED` allowlist, a hand-written redeclaration of a generated class,
and a `WEB_HANDSHAPED` entry that no longer exists. Admitting a call family is
therefore a JSON entry, a `WEB_POLICY` entry, and a regen: the wrapper member,
its property and its alias appear without hand-writing.

### Batching, snapshots, and handle generations

Because a bridge crossing is a real module boundary, the design minimizes and
mirrors crossings:

- **Batching** — many mutations coalesce into one crossing (e.g. Bunnymark's
  single 256-position batch) instead of one call per operation.
- **Property snapshots** — the Kotlin side keeps a read-your-write mirror of
  Godot object state so reads need not round-trip through JS. State the engine
  mutates on its own (a body displaced by `move_and_slide`) is pushed back into
  the mirror at the start of every `_process` **and** every `_physics_process`
  dispatch (task 87: the per-tick transform refresh — refreshing only per
  rendered frame collapsed held-input movement to one physics tick per frame
  whenever physics outpaced rendering).
- **Handle generations** — handles are opaque IDs across the bridge, not live
  pointers, so stale generations are detected and rejected explicitly.

### Value types, builtin calls and signals (task 134 D1, protocol 32)

**One set of value types.** The Web build compiles the shared value types
(`src/commonMain/.../types`, a source directory of `web-runtime`) instead of a Web copy; it
supplies only the internal `real_t` half (`types/WebReal.kt`, float32) and its own
`BuiltinFrame` (`binding/runtime/BuiltinFrame.kt`, same shape as `BuiltinFrame.expect.kt`; the
compiler holds the shared types to it). An engine-backed method fills the frame in Godot's
memory layout and runs:

- **in Kotlin** when `WebLocalBuiltins` has it: the few transcendental methods gameplay calls
  every tick (`slerp`, `rotated`, `getEuler`/`fromEuler` behind `Node3D.rotation`, `lookingAt`,
  `interpolateWith`, `angle`, `signedAngleTo`), ported from Godot's `core/math` at float32 width
  with release semantics (Web export templates are release builds: no `MATH_CHECKS`), so they cost
  no crossing. The arithmetic ones (`inverse`, `transposed`, `determinant`, `getScale`,
  `orthonormalized`, `getRotationQuaternion`, …) are not Web code: they are shared pure formulas
  (`BuiltinFormulas.kt`, `generate_builtin_ops.py`) every backend runs, handing the engine only
  the inputs Godot's debug `MATH_CHECKS` reject. `WebBuiltinParityTest` holds every local port
  to Godot's recorded values (`facade_values` in `builtin_parity_expected.json`) within a recorded
  per-method float32 ulp bound (`WEB_LOCAL_FACADE` in the generator: 1 for every method built on
  sin/cos/atan2/asin/acos, 64 for `interpolateWith`, 0 only for the two `lookingAt`), the same
  bound the runtime smoke's `--verify-recorded` gives the engine on the machine it runs on, since
  libm's last bit differs between CPUs too (the engine's `Vector3.rotated` on Linux x86_64 is 1
  ulp off the macOS arm64 recording);
- **in the engine** otherwise: one immediate crossing (`KanamaWebBridge.immediateBuiltinCall`,
  object-query opcode 1003, any live proxy answers it) carrying
  `<Variant.Type>␟<method>␟<static>␟<base>␟<args…>`, each value `<Variant.Type>:<payload>`; the
  proxy's `_kanama_web_builtin_call` rebuilds the Variants and calls
  `Callable.create(base, method).callv(args)`. GDScript cannot reflect a builtin method's
  parameter types, so the generated `WebBuiltinSignatures.kt` (`generate_builtin_ops.py`) supplies
  them.

**Utility functions (the shared `GD`) run in Kotlin first.** `BuiltinFrame.callUtility` and
`UtilityCalls.call` ask the generated `WebLocalUtilities` (rows in `scripts/web_local_utilities.py`,
rendered by `generate_web_wrappers.py`, whose `--check` also requires every utility in
`extension_api.json` to be in exactly one table) by an id each `UtilityFunction` gets at
construction. The arithmetic, rounding and interpolation utilities are Godot's formulas, bit for
bit; the transcendental ones (`sin` … `pow`, `db_to_linear`) carry a 1-ulp bound with the libm
reason (Kotlin/Wasm's own fdlibm port of `kotlin.math` against the engine's emscripten libm), the
composites a measured one: `linear_to_db` 2 ulps, `ease` 2^-52 absolute (its `1 - (...)` branches
cancel, so ulps of a small result are not bounded; the web3d sweep's worst is 1.1e-16, 8 ulps); a
float-to-int whose result is NaN or outside int64, or `posmod` by zero, still goes
to the engine for its error and answer. The rest (printing, Variant text and serialization, object
identity, RNG) ride the builtin opcode's utility form (static flag `2`, one cached `Expression` per
name and arity). The web3d twin (`gd_twin.gd`, `BundleProbe.gdRows`) holds the local results to the
engine's within each row's bound, and the budget gate counts every remaining synchronous builtin or
utility crossing per engine tick (`maxBuiltinCallsPerTick`, 0.5 unless a demo says otherwise). A demo whose
calls follow simulated time rather than ticks (bunnymark's random draws) declares
`maxBuiltinCallsPerSimSecond` instead, which replaces the per-tick cap for that demo.

RNG (decision 2026-10-14): the `GD` random functions (`randf`, `randi`, `randfRange`, `randiRange`,
`randfn`, `seed`, `randomize`, `randFromSeed`) use Godot's global generator on every platform, like
GDScript and C#, so `seed()` from GDScript or Kotlin gives the same sequence everywhere. On Web each
call crosses the bridge (about 10-30 us): there is no Web-local generator (the old `WebRandom` is
gone; the `UTILITY_RANDOMIZE` / `UTILITY_RANDI` / `UTILITY_RANDF` backend arms call `GD`, and the
rows sit in `WEB_ENGINE_UTILITIES` with that reason). For strict determinism a script uses a
dedicated `RandomNumberGenerator` with its own seed; the global generator is shared with anything
else that draws from it. `bunnymark` draws about 300 numbers per simulated second (about 5 per tick on a
desktop, 10 on a slow CI host that runs 30 ticks per simulated second), which is why it is graded per
simulated second (`maxBuiltinCallsPerSimSecond`) and not against the per-tick default.

**Objects in results keep their handles** (review B2). An object inside a builtin, utility or
generic-call result (`GD.instanceFromId`, an Array of nodes, a Callable's target) resolves like a
generic call's object return: its script handle, else the handle already tracking it, else a handle
of its kind minted for it (a Node's or plain Object's lives as long as the object, a RefCounted's
while its wrappers hold it, see "RefCounted handles" below; a tween's goes with the calling
script). A `GD.weakref` result is a RefCounted like any other, so a per-frame `weakref` does not
grow the table past what one collection holds (`weakrefDoesNotGrow`). A repeated query reuses one handle and keeps
identity (`call("get_children")[i] == getChild(i)`). A lambda connection's guard is held weakly, so
a `getConnections()` answer does not keep it (or its Kotlin callback) alive.

**One Variant text format.** `<Variant.Type>:<payload>` (`WebPackedValues.encodeVariant` /
`decodeVariant`, the proxy's `_kanama_web_pack_variant` / `_kanama_web_unpack_variant`): bool
`1`/`0`, an int as a decimal, a float (and every decimal value-type component) as its IEEE-754
bits, an int64 decimal (`WebPackedFloats`; the proxy converts through a `PackedByteArray`, so NaN, -0
and denormals survive and nothing is rounded; review S1), text
%-escaped, an object as its bridge handle id, a RID as its id (the one way a RID crosses: text, never
an int32 or double slot), a value type as its components in
Godot's memory layout (a Basis as its rows; the processor's `WebValueTypes.LAYOUTS`). Signal
arguments, builtin calls, generic-call value arguments (`v:`) and results use it.

**Signals of any arity.** A typed lambda connection binds the target script's variadic
`_kanama_web_signal_dispatch_args(...args)` with the callback id bound last; the proxy packs every
emitted argument and Kotlin decodes each with its `SignalArgType` (`Signal0` … `Signal5`, typed
handles for every signal of every generated class and every `@Signal`). An **await** is bound to
the emitter instead: `_kanama_connect_await` creates a small `_KanamaSignalWatcher` (a RefCounted
inner class of the proxy) and binds it as an argument of the emitter's own one-shot connection, so
the connection is all that holds it (no metadata, never `CONNECT_PERSIST`: `Node.duplicate()` and
`PackedScene.pack` copy neither). The watcher delivers through the awaiting script's proxy; when
the emitter is freed first its `NOTIFICATION_PREDELETE` calls `releaseSignalCallback`, which
cancels the waiting coroutine (as on desktop) instead of leaving it suspended; when the awaiting
script is freed first its proxy's predelete disconnects every watcher it was delivering
(`KanamaWebHandles.await_watchers`), so none stays on a long-lived emitter. `emitSignal` takes its typed arm for one int32 / String /
object / Vector2i argument and otherwise one immediate generic `emit_signal` call, so handlers run
before it returns.

**Arguments and a return.** A registered method that takes arguments AND returns a value rides
`callPackedArgs` (`WebMethodArm.PACKED_ARGS_RETURN`): the packed argument list in (floats and
value types now included, decimals as their bits), the packed return out.

**A failed builtin call throws.** `callv` answers a wrong argument count or type with an error
print and `nil`; the proxy checks the count first (`E:<message>`), and Kotlin compares the
returned Variant type with the declared one (`WebBuiltinSignatures`, generated with each method's
return type), so a failure throws instead of reading zeros or a pooled frame's earlier result.

### Coroutine frame scheduler (one advance per engine frame, no demo opt-in)

A script's `launch { … delaySeconds(…) … }` queues its continuation on one
global scheduler. Nothing runs a queued continuation except the per-frame pump,
so a demo whose pump never fires never resumes a delay — and nothing throws,
because nothing failed: the work simply never runs.

The pump is therefore **unconditional and demo-blind**. The bridge calls the
ownerless `kanamaWebPumpFrameScheduler(delta)` crossing from the `_process`
dispatch that *every* generated proxy emits, before any per-mode branch, so a new
demo cannot be brought up without it. Exactly one advance per engine frame: the
pump also advances the scheduler's frame counter and elapsed clock, so a second
call in the same frame would run time at N× speed. Frame identity is the rAF tick
(one engine iteration runs synchronously inside one animation frame, so every
dispatch of that iteration observes the same tick), with a repeat-handle fallback
— Godot dispatches a node's `_process` at most once per iteration, so seeing the
same handle twice proves a new iteration began.

Because it rides `_process`, the pump advances while *something* is processing.
Every proxy emits `func _process` unconditionally, so any live Kanama script node
drives it, and a scene with no live Kanama script has no coroutine owner either —
owners are script handles, and freeing one drains its queue. The consequence
worth knowing: while `get_tree().set_paused(true)` holds (or if every Kanama node
in the scene has explicitly called `set_process(false)`), no `_process` runs, so
coroutine delays freeze with the rest of the game and resume on unpause. That is
the intended reading of "paused", not a stall.

Each continuation runs under **its own** owner on both sides of the boundary: the
Kotlin active script handle and the bridge's active owner are switched to the
task's owner, not to whichever script's `_process` drove the pump. Billing a
resumed coroutine's allocations to the pumping script is the task-72 leak shape —
the handles outlive the script that made them.

Before protocol 18 this ran through a per-script `kanamaWebFrame(objectId, delta)`
crossing that the bridge invoked for four hardcoded "Main" handles. The other
eight demos in the corpus never pumped, so every coroutine delay in them hung
forever (task 82). The conformance probe that closes it lives in the in-repo
`web3d` fixture: a coroutine that awaits `delaySeconds` and publishes a mask
proving it *resumed*, asserted by the browser driver.

### Lifecycle ownership

Callbacks, connections, tweens/tweeners, scheduler continuations, snapshots,
queued commands, and generation tables are owned per script/owner and released
at owner, direct, and full teardown; browser handles are routed per script but
live as long as what they name (below), and full teardown releases them all. The validated runs return every owned registry to baseline after both
gameplay and full scene teardown; stale handle use after teardown fails.

**A handle lives as long as the thing it names, never with the script that obtained it** (protocol
38). A Node or plain Object handle, however it was minted (a lookup, `getChild`, an indexed hit, a
property object read, a call's object result, a construction, an instantiated scene, a dispatch
argument, a ray's collider), lives as long as its object; a RefCounted one while a Kotlin wrapper of
it is reachable (below); only a tween's or tweener's goes with the script that made it (the bridge's
`OWNER_BOUND_KINDS`, `mintGenericHandle`'s `tween` tag: its proxy runs the tween's callbacks). The
bridge's `handleOwners` names a handle's routing owner (the proxy whose callbacks run its calls),
not who keeps it: when that script goes, `releaseBrowserHandlesOwnedBy` re-routes every handle
`outlivesItsRouter` keeps to another installed proxy, so a value another script stored, or a
companion holds (`nodeOutlivesItsReceiver`), stays valid; the last proxy releases everything. The
SceneTree and the root Window live as long as the page: `_kanama_track` marks their one handle each
(`markPageHandle`; an orphan Window is no page handle, only the tree's root is), which the bridge
counts in `pageHandles`, apart from the live handles a scene's teardown returns to zero (an
autoload's proxy keeps routing them after the scene is gone). Every Web run's envelope fails when
`pageHandles` passes 2 (`pageHandlesAtMostTwo`). The generic tier's `WebGenericCallResult.close()`
releases a RefCounted's or a tween's minted handle; on a Node's or plain Object's, which another
script may hold, it does nothing (`genericCloseKeepsSharedNode`). A
retired handle's entry in the proxies' shared `_kanama_object_handles` dictionary is erased
(through any installed proxy's release callback), so a later lookup of the same object mints a fresh
handle instead of finding a dead one. The calls that answer a live object (a collider, `getParent`,
`getViewport`, `getCamera3d`, `getTree`, `getRoot`) answer the handle it already has, so a
per-frame call does not pile up handles. Before protocol 38 a lookup's handle was held by the
scripts that looked it up (counted per holder) and released with the last of them, and released at
once by Kotlin's `queueFree()`, so a node a companion kept, or one used later in the frame it was
queue-freed in, was a stale handle.

**One handle per object: the instance-id index.** Every browser handle the proxies enter into the
shared `_kanama_object_handles` for an object goes through `_kanama_track` (and leaves through
`_kanama_untrack`), which indexes it by the object's instance id (`KanamaWebHandles.by_id` /
`id_of`; the first handle tracked wins; script handles are never indexed, a script-backed object
always crosses as its script handle). A lookup, a call's object result, a collider and a dispatch
argument find the handle already tracking their object with one dictionary lookup
(`_kanama_tracked_handle`), so within Web a token IS the object's identity: `body == player` and
`typed == arg` hold as on desktop, and a lookup costs O(1) instead of a scan of every handle.

**Dispatch arguments are lent for the object's lifetime.** A signal argument (one-object and packed
arms), `_input` / `_unhandled_input`'s event and a registered method's object arguments cross as
`_kanama_lend(value)`: the handle already tracking the object (no JS call), else one
`bridge.lendHandle` mints (NODE kind for a Node, so its snapshot and `queueFree` work; OBJECT
otherwise). The handle is not released when the handler returns: like every Node or plain Object
handle it lives until the engine frees the object, or the last Kanama script goes away (a
RefCounted's while Kotlin wraps it, below). `liveLent` / `lentMints` count the handles a dispatch
minted. A Node2D, Node3D or Control argument carries its transform snapshot, refreshed on every dispatch in the
same call, so `body.position` reads the current value inside the handler (a stored one read later
reads its last refresh, the rule for every non-self node).

*Engine frees: the sweep.* The engine frees objects without telling Kotlin, so a sweep runs once
per process frame from `KanamaWebHandles.drain_frame`, the shared class's `SceneTree.process_frame`
hook (made by the first track, bound to the class, not a proxy), which fires every frame even while
the tree is paused, a script called `set_process(false)` or only non-Node scripts exist. Every Node
or plain Object handle `_kanama_track` enters joins its ring (`KanamaWebHandles.lent`, `lent_ring`;
an object's instance id is in the ring once, `ring_ids`, however many handles it had: the web3d row
`handleRingBounded`). It checks up to 32 handles from a ring cursor; a handle whose object is gone
(`is_instance_id_valid`, or a dead weak entry) is untracked and released in one
`bridge.releaseFreedHandles` call, which drops the slot's per-kind state too (an adopted
`AudioStreamPlayer`'s audio state, a `Resource`'s path) as an owner teardown does. So K lent handles
whose objects die at once are all released within ⌈K / 32⌉ frames (`lentBurstDrains`: 256 within 10).
Each new handle also checks the one before it and one ring entry, which keeps create / hand over /
free churn bounded (the web3d row `lentHandlesBounded`: 500 rounds stay within 34 handles of the
baseline). Nothing else retires such a handle: a queued command whose target the engine freed is
skipped and reported (see the freed-target ladder) but leaves the handle to the sweep. A released
handle is remembered as freed (the bridge's `freedHandles`, Kotlin's `freedBrowserHandles`, the last
4096 each), so its later use throws "Invalid access to previously freed instance", desktop's error.
A use of one freed longer ago than that is still an `IllegalStateException`, worded as a stale
handle ("Stale Kanama Web browser handle"): the typed calls make Kotlin's liveness check
(`requireLive`: freed first, then stale) before crossing, and `WebExperimentalGenericCall.callImmediate`
makes the same one, so a call by name reads the same. `webFreedAware` does not turn the bridge's own
"No Kanama Web proxy owns handle" into a script error: a handle Kotlin still registers that the
bridge no longer owns is a bookkeeping disagreement and stays a fatal page error;
`lentLongFreedHandleIsIllegalState` pins the script-visible side.

*Kotlin `queueFree()`.* The node lives to the end of the frame, as on desktop, so its handle stays,
however it was obtained: the bridge keeps its slot (marked `freeQueued`) and Kotlin its
registration. A typed call or a second dispatch of the node in the same frame reads it; once the
engine freed it the sweep releases the handle as freed and a use is the freed-instance error
(`lentKotlinQueueFree`, `lentQueueFreedNodeLivesToFrameEnd`, `lookupSurvivesQueueFree`).

*RefCounted handles: held by Kotlin wrappers* (protocol 37, closes
[#332](https://github.com/falcon4ever/kanama/issues/332)). A browser handle that names a
`RefCounted` (an `InputEvent` argument, a load, a `create()`, a call's result) lives while a Kotlin
wrapper of it can still be reached, or until that wrapper's `close()`, as desktop's owned wrappers
do, not with the script that made it. The proxy marks such a handle when it tracks the object
(`_kanama_track` calls `markRefCounted`; a lend passes flag 16 instead; a `Tween` or `Tweener`
keeps its own lifetime, to its end), and Kotlin keeps the same mark
(`kanamaWebMarkRefCountedHandle`). Every wrapper is constructed fresh at its decode, and the
generated `GodotObject` root's `init` registers a wrapper of a marked handle with the bridge's one
`FinalizationRegistry` (`registerWrapper`, with the wrapper's `toJsReference()` as target and
unregister token; a Node or plain-Object wrapper costs a set lookup and no crossing), so each
wrapper is one hold and the slot counts them (`wrappers`). A decoded RefCounted handle is wrapped as
a `RefCounted` (`webWrap`), so `as? AutoCloseable` and `use { }` work on a Variant result. When the
browser collects the last wrapper, the registry's callback (between tasks: bookkeeping only) queues
the handle, and the drain (once per engine frame, inside a Godot callback: the frame scheduler's
pump, or `KanamaWebHandles.drain_frame` on `SceneTree.process_frame`, bound to that class so it
runs when no proxy processes, as after a teardown that leaves only scripted resources) empties the
queue: it erases the proxies' dictionary entry through any installed proxy, tells
Kotlin, and retires the slot, as desktop's `OwnedReleases` drains the GC's cleanups on the next
frame. `close()` unregisters its wrapper and releases the handle at once when that was the last
hold (`closeWrapper`), so another wrapper of the same object keeps it. It first applies both
command queues (`flushWebCommandsBeforeRelease`, the ordering barrier every immediate call makes;
`ScriptResource.close()` and the generic tier's `WebGenericCallResult.close()` too): a queued setter
that hands the object to the engine (`ButtonGroup.create().use { button.buttonGroup = it }`) must
reach it before the release erases the proxies' reference, or it finds no object and sets null
(`rcCloseAfterQueuedSet`). Deferring the release to the next drain instead would also order it after
the flush, but `close()` would no longer release at once. A conversion that builds
the typed wrapper over a temporary one (a typed load over `load()`'s `Resource`, a generic call's
typed return) ends the temporary's hold (`webRetyped`, bridge `dropWrapper`), so `close()` on the
wrapper the script got releases at once. Owner teardown re-routes a
RefCounted handle that still has wrappers to another proxy, like a lent one; the last proxy
releases everything, so full teardown stays deterministic. A Sprite2D texture snapshot is a hold
too (`holdRefCounted`): `sprite.texture` answers after the code that set it dropped its wrapper.
Owner links are not implemented: a Kotlin script object of a `RefCounted` owner (a custom resource)
does not hold its owner on Web, as the desktop runtime's does, so only a wrapper of the resource
keeps it (the user-facing sentence is in `godot-api.md`, "A script object keeps its resource alive").

A lent RefCounted the handler never wrapped (a packed argument nobody read) has no hold from its
lend, so the drain releases it after its dispatch and the next 2 engine frames (`LENT_GRACE_FRAMES`
in the bridge). Above `RC_LENT_CAP` (4096) lent RefCounted handles pinned past that floor, the drain
releases the least recently wrapped first; a use of one Kotlin still holds is the freed-instance
error. A RefCounted a Variant result hands over (a utility, builtin or generic call's: the bridge's
`mintGenericHandle`, so a per-frame `GD.weakref`) is under the same cap from its mint; a load, a
construction or a typed call's result the script owns never is. The
cap is Kanama's own (C#'s binding has none): Chrome collects within about a second under game load
and at once when the page idles, but Firefox collects only under allocation pressure, so a quiet
scene with mouse motion would otherwise pin every event until its nursery fills (CI's Linux Firefox
collected none of 300 `GD.weakref` results in 600 frames of 4 MB allocation). A browser whose
`FinalizationRegistry` refuses a Kotlin/Wasm object (not seen in Chrome or Firefox; Safari is not
measured) makes `registerWrapper` answer 0 after one console warning: a RefCounted only Kotlin keeps
then goes with its script, a lent one after its 2 frames (the protocol-36 rule, loud). In that mode
`holdRefCounted` and `dropWrapper` change nothing: no wrapper is counted, so their -1 would release a
handle a wrapper still names (`sprite.texture = a; sprite.texture = b` would drop `a`). Registering a
wrapper of a lent RefCounted again moves it to the young end of the cap's queue, so the cap evicts
the least recently wrapped lend. `scripts/web/bridge_rc_ownership_selftest.mjs` pins both (no
browser run is in fallback mode). Every
driver's performance section reports `maxRcPinned`, `rcReleasedByGc`, `rcReleasedByClose`,
`rcReleasedByGrace`, `rcReleasedByCap`, `wrapperRegistrations` and `wrapperRegistry` (0 for the
fallback). Because a freed script's dropped wrappers release its handles only once the browser
collects them, every demo driver's teardown check asks the page to collect before each poll
(`collecting` in `scripts/web/drivers/envelope.mjs`: `gc()` on Chrome, an allocation burst on
Firefox), so does dodge's per-mob retention trough between rounds, and match3's mid-game bound leaves
out the RefCounted handles still pinned (`rcPinned`).

The web3d `LentProbe` rows (`lentStoredArgumentStaysValid`, `lentArgumentIdentity`,
`lentNestedDispatchIdentity`, `lentArgumentOutlivesItsReceiver`, `lentEngineFreeIsFreedInstance`,
`lentHandlesBounded`, `lentRefCountedGracePeriod`, `lentRefCountedExpiry`, `lentNodeSnapshotReads`,
`lentKotlinQueueFree`, `lentDispatchCost`, `lentQueueFreedNodeLivesToFrameEnd`,
`lentLongFreedHandleIsIllegalState`, `lentRefCountedGraceRenewed`, `lentBurstDrains`,
`lentSweepRunsWhilePaused`, `lentSweepOutlivesItsMinter`, `createdHandleSweptWhilePaused`) and
`OwnProbe` rows (`rcHeldByKotlinOutlives`, `rcReleasedWhenCollected`, `rcCloseReleasesNow`,
`rcCloseAfterQueuedSet`, `rcOutlivesItsReceiver`, `rcResultOutlivesItsScript`,
`rcNeverMaterializedIsReleased`, `weakrefDoesNotGrow`, `lookupSurvivesQueueFree`,
`rayHitColliderIsLent`, `nodeOutlivesItsReceiver`, `constructorRollbackAfterFlush`,
`uint32Masks`, `genericCloseKeepsSharedNode`, `handleRingBounded`, `rcDispatchCost`; Chrome runs with `--js-flags=--expose-gc` so the collection rows call `gc()`,
Firefox allocates 4 MB a frame instead) pin all of it. A collection row never depends on the browser
choosing to collect: where `gc()` forces one (Chrome) the release must happen; where nothing can force
one (Firefox) the row also passes on the bound that holds without it (O2 and O7: every handle left is
pinned under the lent cap; O6, a load, which is never capped: still held for its collection). The
driver's `KANAMA_WEB_IGNORE_COLLECTIONS=1` makes the bridge ignore every collection
(`ignoreWrapperCollections`, harness only) to prove those bounds locally on either engine.
`lentDispatchCost` holds a seen plain Node argument at 0 lending JS calls per dispatch and a Node3D
at 1 (the old per-dispatch lend cost 2), `rcDispatchCost` a seen `InputEvent` at 1 (its wrapper's
registration).
The web3d smoke's `sharedNodeHandleSurvivesFirstFree` check (`Main.share_probe`) pins the rule.

**Ownership measurements (2026-10-15, `54fe2ab7`, protocol 38).** One `web_ci_matrix.sh --demo-set ci`
run of the 13 demos at the workflow's `DEMOS_REF` on Chrome 155 and Firefox 157 (macOS arm64, Godot
4.7.2 stable, `web_nothreads_release`), read from each cell's envelope: `liveAfterGameplay` and
`liveAfterTeardown` (browser handles live after the driven play and after the full scene teardown),
`maxRcPinned` (most RefCounted handles pinned at once by Kotlin wrappers or lends), `maxPageHandles`
(the SceneTree and root Window), `rcReleasedByGc` (handles the browser's collector released through
the wrapper registry; 0 where the browser did not collect during the run), `crossings`
(`crossings.kotlinToGodotCalls`) and `durationMs` (the driven run). `liveAfterTeardown` is 0 in
every cell. The first Firefox run of `charactercontroller` failed `playerMovedOnInput` and
`physicsFramesAdvanced` (142 physics ticks where the rerun had 293; handles and pins unchanged), so
its row is from the passing rerun.

| demo | engine | liveAfterGameplay | liveAfterTeardown | maxRcPinned | maxPageHandles | rcReleasedByGc | crossings | durationMs |
|---|---|---|---|---|---|---|---|---|
| match3 | chrome | 86 | 0 | 10 | 2 | 6 | 269 | 15117 |
| match3 | firefox | 96 | 0 | 10 | 2 | 0 | 322 | 18260 |
| bunnymark | chrome | 257 | 0 | 1 | 0 | 0 | 423 | 8491 |
| bunnymark | firefox | 257 | 0 | 1 | 0 | 0 | 422 | 10240 |
| dodge | chrome | 26 | 0 | 7 | 2 | 6 | 437 | 17329 |
| dodge | firefox | 22 | 0 | 7 | 2 | 10 | 280 | 16100 |
| web3d | chrome | 16 | 0 | 197 | 2 | 318 | 82 | 20120 |
| web3d | firefox | 16 | 0 | 254 | 2 | 262 | 85 | 34513 |
| platformer | chrome | 51 | 0 | 1 | 0 | 1 | 1547 | 14898 |
| platformer | firefox | 52 | 0 | 1 | 0 | 0 | 1964 | 43502 |
| squash | chrome | 15 | 0 | 2 | 0 | 0 | 1508 | 15316 |
| squash | firefox | 14 | 0 | 2 | 0 | 0 | 1257 | 24590 |
| fps | chrome | 44 | 0 | 6 | 1 | 1 | 480 | 11377 |
| fps | firefox | 42 | 0 | 6 | 1 | 1 | 488 | 30561 |
| charactercontroller | chrome | 17 | 0 | 4 | 1 | 4 | 1886 | 28099 |
| charactercontroller | firefox | 21 | 0 | 6 | 1 | 0 | 2089 | 60755 |
| thirdperson | chrome | 185 | 0 | 15 | 1 | 12 | 1515 | 41385 |
| thirdperson | firefox | 187 | 0 | 15 | 1 | 12 | 3009 | 98801 |
| racing | chrome | 15 | 0 | 0 | 1 | 0 | 972 | 16533 |
| racing | firefox | 16 | 0 | 0 | 1 | 0 | 1369 | 25449 |
| citybuilder | chrome | 85 | 0 | 64 | 1 | 47 | 481 | 17028 |
| citybuilder | firefox | 85 | 0 | 64 | 1 | 47 | 1216 | 29185 |
| tpsdemo | chrome | 293 | 0 | 33 | 2 | 4 | 395 | 67496 |
| tpsdemo | firefox | 294 | 0 | 34 | 2 | 4 | 3243 | 155721 |
| spike | chrome | 1 | 0 | 1 | 0 | 0 | 186 | 17618 |
| spike | firefox | 1 | 0 | 1 | 0 | 0 | 184 | 12307 |

**A lambda connection's Kotlin callback goes with whatever held it.** `connect { }`, `connectLong`,
`connectObject` and the other typed overloads register a callback in `WebSignalCallbackRegistry` and
connect the receiving script's proxy to the emitter with the callback id bound. The entry is dropped
when its receiver script is freed (`kanamaWebFree`), when its one-shot fires, on `close()`, and --
the case that leaked before protocol 34 -- when the *emitter* is freed by the engine: the proxy binds
a `_KanamaSignalGuard` (a `RefCounted` held only by the connection) as the last bound argument, and
its `NOTIFICATION_PREDELETE` calls `releaseSignalCallback`, as desktop's custom Callable `free_func`
does. `close()` marks the guard done before disconnecting so it does not report. The web3d
fixture's `LeakProbe` pins each case (`leak_probe`).

**Script errors are contained and reported.** `webCallbackBoundary` catches an exception from user
code (and only that: the registry/project call into user code runs through `userScript`, which
records the exception that escaped it, and the boundary contains exactly that exception -- the
registry's invariants, `WebFrameScheduler.withOwner`, the boundary's own flush and an untranslated
bridge `JsException` stay fatal, and `kanamaWebFree` therefore never skips its release),
`WebScriptErrors` builds the shared `ScriptErrorReport` (type, message, frame; the same source
as desktop and iOS) and hands it to the bridge's `reportScriptError`, which the proxy's
`_kanama_report_error` turns into `push_error("SCRIPT ERROR: ...\n   at: ...")`; the boundary then
throws an exception with a marker prefix that `invoke` recognises, so the callback returns its
fallback instead of ending the page (a bridge or protocol fault still does). Coroutines
(`CoroutineExceptionHandler`) and frame-scheduler tasks (a per-task catch) report the same way, and a
script constructor that throws is reported, its half-built script torn down and the commands its
initializers queued dropped (`kanamaWebConstruct` rolls the queue back to its checkpoint; if a
`close()` or an immediate call applied the queue meanwhile, only what was queued since goes:
`constructorRollbackAfterFlush`). What Wasm provides: the
trace is the JS `Error.stack` -- `kotlin.createJsError` then anonymous `wasm-function[n]:0xoffset`
frames; the production build has no name section and no source map, so no game frame can be named
and the report's function is the containment site (`Script.callback`, `<signal handler>`,
`<coroutine>`). A build that keeps names is parsed (`WebScriptErrors.parseFrame`) and picks the
game frame. Reported errors are counted in `callbackErrors` as well (and in `scriptErrors`), so a
demo that throws still fails its zero-errors check; the web3d driver expects exactly its eight
deliberate ones (four from `ErrorProbe`, three thrown at scene load by the `Throwing*` scripts, and
`OwnProbe`'s `ThrowingCloser` constructor) plus its four freed-target setter reports, subtracts only those from `callbackErrors`, and lists them as
`expectedConsoleErrors`, which the envelope verifies reach the console. A call whose script threw
answers the bridge's `scriptErrorResult` sentinel instead of a packed value, which the proxy turns
into the declared type's default (Variant returns: `null`) before any parse. Red run for the
boundary: `KANAMA_WEB3D_INJECT_RUNTIME_FAULT=1` dispatches a callback id the registry never issued
and expects the page to end (`runtimeFaultIsFatal`).

**A freed object is a freed-object error.** A browser handle's object the engine frees leaves the
handle live in Kotlin until the sweep releases it as freed (after that, `requireLive` throws before
crossing, as desktop's setter does).
When a bridge callback then cannot publish its result, `unpublishedResult` asks the proxy
(`FREED_CHECK_OPCODE` 1004) whether the object is gone and throws "Invalid access to previously freed
instance"; `webFreedAware` turns that `JsException` into the `IllegalStateException` desktop throws,
once per entry point -- the generated backend dispatch wraps every call shape's body
(`generate_web_backend.py`), so a new extern gets it for free, as does `WebExperimentalGenericCall`.
The object-returning shapes (node lookups, `getParent`, `getChild`, indexed lookups, tween and
collision returns) go through `rejectUnpublishedObject`, which also hands back the result handle it
had allocated. Every proxy callback reads its receiver through `_kanama_live_object`, never a typed
`var x: Object = handles.get(...)`: a debug build aborts the function on assigning a freed
instance to a typed Object, and a cast or call on one is undefined behaviour in a release build.
A callback whose receiver is gone publishes NOTHING (the proxy tests `_kanama_handle_gone` before
it publishes a default), so the same `unpublishedResult` path raises the error for every shape, the
value shapes (signal emit, child count, `Control` position, connect) included.
Queued calls (setters, `queueFree`) do not cross here and do not throw at the call: when the proxy's
command loop stops on a target the engine freed (a browser handle that still has its bridge slot),
`flushCommands` asks the same freed check, skips that one command (dropping a skipped generic call's
staged arguments), applies the rest of the group and records the freed-instance message; the
telemetry that follows tests each command as applied AND not skipped, since the applied ones are no
longer a prefix;
`WebCommandBuffer.flush` reports each as a script error (`takeFreedCommandSkips`). A
**script-backed** object (a script handle, bit 30 clear) is freed in Kotlin with its node
(`kanamaWebFree`), so `requireLive` throws the same freed-instance message for a call on it
(`WebInstanceRegistry.wasFreed`: a minted slot that is no longer live; not "Stale Kanama Web browser
handle"), and `flushCommands` skips and reports a command queued on it before the free, both when
its script was already gone at the scan (`kanamaWebIsFreedScriptHandle`) and when an earlier group
of the same batch freed it (its owner's apply callback is gone; task 138 item 22; a command that
group owned on another, live object is reported as dropped with its script, not as a freed
instance). A command that
stops the loop for any other reason is a protocol fault and ends the page through the
applied/expected check.

**Proving a script-facing change in a DEBUG export.** CI exports release only; GDScript's
`DEBUG_ENABLED` checks (the typed-Object abort above) only exist in the debug template. Locally:
`./gradlew :web-runtime:exportWeb -PkanamaWebDemo=web3d ... -PkanamaWebTemplateDebug=<...>/web_nothreads_debug.zip`
(it exports with `--export-debug`), then `scripts/web_export_smoke.sh ... --debug-template`, which
reads the debug engine's "GDScript backtrace" lines as part of the error above them. Run web3d
in both.

**Lambda connections never coalesce.** Godot keys a signal's slots by the *base* callable and
ignores binds, so binding the callback id alone did not separate two lambdas from one script on one
signal (the second connect answered "already connected", task 138 item 20). Each lambda connection
therefore targets its own `_KanamaSignalGuard` (`Callable(guard, "fire")`, the callback id and the
guard bound), whose `fire` forwards to the receiving proxy's dispatch method: two connects of the
same lambda, or two lambdas, are two connections (`CONNECT_REFERENCE_COUNTED` included), each with
its own guard and registry entry. A receiver freed before its emitter ends its lambda connections
with it, as Godot ends a connection whose target is freed: its proxy's teardown marks each of its
guards done and disconnects it (`_kanama_drop_routed_guards`, next to the routed awaits). A user `disconnectBound` keeps Godot's own
error for a connection that is not there; only `SignalConnection.close()` is quiet about one that
is already gone.
The handle namespaces are disjoint (bit 30): `WebInstanceRegistry` used to mask bit 30 away, so a
browser handle with the slot and generation of a live script read as that script (task 138 item 18).

### RefCounted resource ownership (create/close on the handle bridge)

The user-facing contract is the **same** as the pointer backends —
[*close what you create*](../../game-dev/properties-resources.md): a factory
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
  handle ID interned in the proxies' shared object table. So the Web model is
  **"drop the handle = release the reference"**: once no Kotlin wrapper holds the
  handle (the last one was `close()`d, or collected by the browser; see
  "RefCounted handles" above), the bridge runs the generated GDScript
  `_kanama_resource_release`, which *erases the handle from
  `_kanama_object_handles`* — dropping GDScript's reference. If nothing else holds
  the object, its engine-side refcount reaches zero and Godot frees it; if the
  caller already handed it to a node/resource, the engine's own reference keeps it
  alive. `close()` is per wrapper, as on desktop: it ends that wrapper's hold, and
  another wrapper of the same object keeps the handle.

**There is no `init_ref` claim to get wrong** (the #91 root cause) because the
bridge never constructs the native object itself — it asks GDScript to, and
GDScript's return value is already a live, refcounted reference. **The
created-then-handed-off-then-closed case survives** for the same reason it must on
the pointer backends: the handoff (e.g. `AudioStreamPlayer.setStream`) is applied
**before** the temporary handle is released, so the engine takes its reference
first. `AudioStreamPlayer.setStreamFromPath` is the canonical example —
`load → setStream(player) → finally { stream.close() }` — and it is
covered by an explicit **bridge-level ownership assertion** in the Match3 export
smoke (`scripts/web/drivers/demos/match3.mjs`): the stream resources are loaded
and handed off with zero failures, they play (survived their temp release), and
every resource handle returns to baseline at teardown (no leak).

Practical consequence for generated wrappers: a Web `X.create()` returns an
**owning** wrapper and `close()` (from `RefCounted`, on every RefCounted class) is
a real "release this reference" command, so *the same `use { }`/`close()`
discipline the docs teach applies on Web*; a forgotten `close()` is a late
release, when the browser collects the wrapper, as on desktop.

### Declared dispatch degradations (the protocol manifest)

The Web backend supports a **hand-maintained set of member shapes**. Anything
outside it degrades, and the generator has always known when it was degrading —
it just never said so. `KanamaWebProtocol.generated.json` therefore carries a
`dispatch` field on every `properties` / `virtuals` / `methods` / `signals`
entry, plus a short `dispatchReason` whenever that value is not `typed`
(a typed entry carries no reason, so the absence of one means "no degradation"):

| `dispatch` | Meaning |
|---|---|
| `typed` | Dispatches with its declared payload intact. |
| `unsupported` | Emitted, but the proxy stub throws if the engine ever calls it. |
| `argument-dropped` | Dispatches, but part of the declared payload never reaches Kotlin. |
| `not-emitted` | No crossing is emitted at all, so the member can never run. |

The value is **not** a second reading of the member's signature: `WebMethodArm`
and `WebPropertyArm` in `WebScriptCodeEmitter.kt` are the arm tables themselves,
the GDScript emitter switches on them, and the manifest reports whichever arm was
taken. Admitting a new shape means adding an arm and its emitter branch — the
manifest cannot drift from what the proxy actually does, because there is only one
table. The same tables feed a per-build census line on the KSP warn channel:

```
[kanama:web-dispatch] 0 of 37 declared member(s) across 6 script(s) do not dispatch typed (method 0, signal 0, property 0, virtual 0)
```

**A non-`typed` member fails the build.** `undispatchedMemberErrors` turns every
entry the census would report into a KSP **error** naming the script, the member,
the shape and the reason, plus the shapes that *are* dispatched:

```
e: [ksp] .../Main.kt:49: [kanama:web-dispatch] Main.gate_proof (registered function): no arm
   for the registered-method shape (FLOAT) -> INT; the proxy emits a stub that throws. Declare a
   shape the Web backend dispatches — no arguments; any all-numeric argument list up to 6 scalar
   slots; a single String or object argument; a mixed list of String/NodePath/Long/Boolean/object
   arguments; or a zero-argument value return — or add the arm for this shape to WebMethodArm and
   its emitter branch.
```

This is the repo-wide rule 66a (`undispatchedVirtualErrors`, kanama#114) and
#148's property guards already applied to virtuals and properties, now
generalized to registered functions and signals: **a generator may not emit a
stub that throws at runtime, or quietly drop a declared payload — it either
dispatches, or it fails the build.** There is deliberately **no allowlist**:
every shape the corpus declares has an arm, so an exemption list would only be a
place for the next degradation to hide. Widening a shape means adding an arm, not
adding an entry. A non-`typed` property or virtual additionally means one of the
older guards has a hole, and the error says so.

The manifest's own `schemaVersion` versions this file's shape and is independent
of `protocolVersion`, which versions the runtime bridge contract; adding these
fields moved the former only.

**What the census bought.** Reading it across the twelve-demo corpus turned
"add a `callDouble` arm" into a measured parcel — 52 degraded members over 19
distinct missing shapes — and three arms plus one signal change closed all of
them, at protocol 17 (the version current when task 80 landed) and with **no new
bridge entry point after slice 2**:

| Arm | Covers |
|---|---|
| `NUMERIC_VOID` | Any all-numeric argument list, flattened into the six-slot `callDoubles` crossing (six slots is exactly one `(VECTOR3, VECTOR3)` pair). |
| `PACKED_RETURN` | Every zero-argument value-returning method. The value crosses packed into one string with the same encoding `getPackedProperty` uses, so one entry point serves STRING/NODE_PATH/INT/FLOAT/BOOL/VECTOR2/VECTOR2I/VECTOR3/QUATERNION/BASIS. |
| `PACKED_ARGS` | A **mixed-channel** argument list — text, whole numbers, booleans and object handles together — packed into one string over the *existing* `callString` crossing. Object arguments ride as their handle id (a Kanama-scripted object as its own script handle, anything else as a transient handle released right after the call), which is what reaches shapes the string and object arms cannot: `(STRING, OBJECT)` (match3 `Tile.set_tile_type`) and `(INT, OBJECT?)` (tps-demo `add_player`). |
| `_kanama_web_signal_dispatch1` | One emitted scalar, packed the same way, delivered by the typed `GodotSignal.connect*` overloads. A zero-argument lambda still runs and ignores it. |

`PACKED_ARGS` deliberately refuses **floats** (and the float-backed vectors). The
packed list is decimal text produced by GDScript's `str()`, which rounds a double
to 14 significant digits, so a float carried there would arrive slightly *wrong* —
the silent-wrong-VALUE class this whole gate exists to kill. An all-numeric shape
has the exact `callDoubles` crossing anyway; what is left over is a float *mixed*
with text or an object, and that shape has no arm and fails the build rather than
losing precision in silence. Text arguments are `%`-escaped with the same
spelling the generic-call transport uses (`%` → `%25`, unit separator → `%1F`), so
a payload can never split the list.

**Each admitted shape is exercised, not just emitted.** The in-repo `web3d`
fixture declares one registered function per shape and drives each through the
real crossing — Kotlin asks Godot to call it BY NAME, Godot dispatches to the
generated proxy, the proxy takes the arm — then compares the value that came
back against the value that went out (`Main.dispatch_probe`, resolved by name in the driver,
must return the full mask — 127). A shape that only the emitter tests cover is a
shape nothing has ever actually run, and the manifest cannot see a shape that
dispatches but delivers the WRONG VALUE. The mixed-channel bit carries a
deliberately hostile label (real unit separators, percent-escape look-alikes, a
quote and a backslash) plus a live object handle, and a second call passes a
`null` object so the nullable lane is proven to deliver `null` rather than a
wrapper around handle 0.

### The Web Script Environment

The script-facing differences from desktop, with their mechanism. The user-facing list is
[Limitations → Web](../../reference/limitations.md#web).

**Value types.** The Web value types are the same classes as native. The methods that run in
Kotlin natively (on every platform that includes `Basis`/`Transform3D` `inverse`, `affineInverse`,
`transposed`, `determinant`, `getScale`, `scaled`, `orthonormalized` and `getRotationQuaternion`)
run the same Kotlin, with the same results, and the engine-computed ones reach the engine through
the bridge (one crossing per call, so prefer the Kotlin ones in a hot loop). A few engine-computed
ones that gameplay calls every tick run in Kotlin on Web, at no crossing, as ports of Godot's own
code: `Vector2.angle`/`rotated`, `Vector3.rotated`/`signedAngleTo`, `Quaternion.slerp`,
`Basis.rotated`/`getEuler`/`fromEuler`/`lookingAt` and `Transform3D.lookingAt`/`interpolateWith`.
Those built on `sin`/`cos`/`atan2` match the engine except where math libraries round differently
in the last bit (they also differ between the engine's own builds on different CPUs): by at most
one float32 step, and `interpolateWith` by at most 64 (measured against Godot on every build); the
two `lookingAt` use no such function and match to the bit. Web exports are release builds, so these
ports skip the debug-only argument checks, as the engine does there.

**Strings, NodePath, ByteArray, Callable.** They compile from the same source: the byte codecs run
in Kotlin/Wasm (checked against Godot's recorded results), the engine-run ones cross the bridge,
which carries text, numbers, `NodePath`, `ByteArray`, the packed returns, objects, Callables, a
`List`/`Map` argument (`godotFormat`) and an `Array`/`Dictionary` result (as a `List`/`Map`).
`GodotCallable` is the same class on Web, and `GodotSignal` has the same queries; each is a Godot
call by name there. Two differences: `getObjectId()` (and `GodotObject.instanceId`) asks the
engine, so it fails on a freed target instead of answering its old id, and in `getConnections()` a
Kotlin lambda connection's `callable` is its own guard object's `fire` method (each lambda
connection has its own callable, so two lambdas can connect one signal).

**RIDs and ray queries.** A `RID` is the same value class and crosses the bridge as its id.
`body.getRid()`, `PhysicsRayQueryParameters3D.create(from, to, mask, listOf(body.getRid()))` with
every query property, and `node.getWorld3d()?.directSpaceState?.intersectRay(query)` (desktop's
`Map`; empty on a miss) are the same code as desktop. A ray is one engine crossing and makes no
handle; `getRid()` crosses once per object. `RayCast3D.addExceptionRid` and
`SpringArm3D.addExcludedObject(rid)` take the RID too.

**`GD` and `Mathf`.** Web compiles the same `GD` and `Mathf` and runs the math in Kotlin, with no
bridge call: everything `Mathf` has, and `GD`'s arithmetic, rounding, interpolation and
transcendental functions (`sin`, `pow`, `exp`, `ease`, `dbToLinear`, ...). The arithmetic ones
return GDScript's double bit for bit; the transcendental ones can differ from the engine's in the
last bit or two (Kotlin/Wasm computes `kotlin.math` with its own fdlibm port, not the engine's libm;
`ease` is within 2^-52 absolute, since some of its branches subtract from 1). The rest of `GD` runs
in the engine: printing and `pushError`, `str` and the `varTo*` / `bytesTo*` conversions, `typeOf`,
`hash`, `weakref`, `instanceFromId`, and the random functions (`randf`, `randi`, `randfRange`,
`randiRange`, `randfn`, `seed`, `randomize`, `randFromSeed`). Each of those is one bridge crossing
(about 10-30 µs), so draw thousands of random numbers per frame with care.

**Kept `RefCounted` arguments.** A `RefCounted` you keep (an `InputEvent` argument, a loaded
`Resource`, a `create()` result) is released when the browser collects your last wrapper of it, or
when you `close()` it; `close()` ends that wrapper only, so another wrapper of the same object stays
valid. A dropped one is released when the browser's collector runs (Chrome collects within about a
second under game load, Firefox only under allocation pressure). At most 4096 `RefCounted`
arguments that only Kotlin holds stay alive this way (`RC_LENT_CAP`): past that the oldest are
released first, and using one still kept throws "Invalid access to previously freed instance". C#
has no such cap; it is Kanama's bound for Firefox, which in a quiet scene would otherwise keep
every mouse-motion event until it collects. A script object does not keep its `RefCounted` owner
alive on Web (the native `KanamaScript` owner link is not built here).

**Script errors.** A contained exception reaches the browser console through Godot's error log:
`ERROR: SCRIPT ERROR: kotlin.IllegalStateException: no target`, then
`at: Player._ready (no source line on Wasm)`, then Godot's own `at: push_error (...)` line (the
report is printed by `push_error`), and the callback returns its default while the game runs on.
Only an exception from script code is contained: the runtime failing around it (a bridge or
protocol fault) still ends the page. A call whose return type is a typed GDScript value (`Long`,
`Double`, `Boolean`, `String`, `Vector2`) answers that type's default (`0`, `0.0`, `false`, `""`,
zero) where desktop answers `null`; a `Vector3`, `Color` or other value-type return answers `null`
as on desktop.

**Freed objects.** A call that returns a value or an object (`getName()`, `getParent()`,
`getNode(...)`, `getChild(i)`, `getChildCount()`, `emitSignal(...)`, `connect(...)` on a freed
source or target, a `Control` position or size read, and every other query that crosses to Godot)
on an object the engine freed throws the same `IllegalStateException: Invalid access to previously
freed instance` as desktop, naming the handle instead of the class. A call that returns nothing
(`node.position = ...`, `queueFree()`, `addChild(...)`) is queued and applied with the rest of the
frame's batch, so it cannot throw at the call. On a freed object that one command is skipped and the
batch reports the same `IllegalStateException` once as a script error
(`SCRIPT ERROR: ... at: Script.<queued command>`), the other commands of the batch are applied, and
the page keeps running; the exception is not catchable at the setter. A Kanama-scripted object freed
while another script holds it behaves the same way. A command that is malformed for a live object
(a protocol fault) still ends the page. A position read of a `Node2D`/`Node3D` answers the last
snapshot it had.

**Registered functions.** The bridge dispatches a registered function only for the argument lists
it has an arm for (`WebMethodArm`): no arguments, all-numeric lists up to `NUMERIC_ARG_SLOTS` (6)
scalar slots, a single String or object, mixed String/NodePath/Long/Double/Boolean/object/value-type
lists with or without a value return, and a zero-argument value return. Any other public function
fails only the Web build (`undispatchedMemberErrors`), naming the shape. Lifecycle virtuals are
limited to `DISPATCHED_VIRTUALS`. Whether a Godot-side call that omits Kotlin default arguments gets
the defaults is not covered on Web.

**Script API not on Web yet.** `launch`, `wait`, `nextFrame`, `isScript`, `asScript` and the tree
accessors exist (`WebScriptAccess.kt`); the `node`/`script`/`preload` delegates, `castOrNull`/`cast`,
`requireAs<T>`/`getNodeAs<T>` and the `instantiate*` functions do not. `wait` is the frame
scheduler's delay, with the defaults only. Tweens: `node.createTween()` only (no
`SceneTree.createTween()`, `getProcessedTweens()` or `tweenAwait`). There is no
`Engine.registerSingleton`.

**Autoloads.** The generated `Autoloads` object works on Web, but the lookup goes through the
running script's node, so an autoload is read from a script callback (a lifecycle callback, a signal,
a coroutine), which is where Web code runs.

## Export Workflow Internals

### Building the Web Scripts

`buildWebScripts` generates the GDScript proxy bundle (proxies + manifest +
protocol descriptor) and collects the Kotlin/Wasm runtime a project attaches:

```sh
./gradlew --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  :web-runtime:buildWebScripts
```

The bundle lands in `web-runtime/build/web-scripts/` with a
`build-web-scripts.report.json` recording the protocol version, renderer, and
files. No source maps are published.

`exportWeb` depends on `buildWebScripts`, so the user page runs `exportWeb`
only.

### Export Staging

The `exportWeb` task stages a copy of the project without `addons/kanama/`
and `kotlin-src/` and points every scene at the generated Web proxies, so no staged
scene attaches a `.kt` script. The Kanama Tools plugin stays enabled in that copy;
its export check (see the Android guide's
[Export Guard](../../getting-started/editor-workflow.md#export-guard)) finds no `.kt` script to check and stays
silent. A plain Godot Web export of the project itself is checked like the other
platforms and stops with `Kanama: Export stopped: …` when a `.kt` script that its
scenes attach does not bind in the editor.

`exportWeb` stages a disposable copy of the project, runs the Godot Web export,
installs the Kotlin/Wasm runtime and bridge, cache-busts the entry scripts, and
writes a self-contained served directory plus a release payload report. Point it
at a **clean checkout** of the demo (never a shared working tree); the Web script
root is auto-derived from `<project>/web`.

Each demo has its own staging task (`stageWeb<Demo>Project`), selected by
`-PkanamaWebDemo`; an unknown key fails with the list of supported keys. There
is no staging path for an arbitrary project, which is why the user page says a
game cannot be exported to the Web. The export also writes
`kanama-web/export-report.json` listing every served file, its size, the total
payload, the renderer, the thread setting, and the source-map policy; no
workstation-absolute paths leak into the served HTML.

### Payload and Source Maps

- The Kotlin gameplay Wasm is **content-hashed by the build** (its filename is
  its hash), so it is cache-busted automatically when gameplay changes.
- The two fixed-name entry scripts (`kanama-web-spike.js`,
  `kanama-web-bridge.js`) are cache-busted with a content-derived `?v=<hash>`
  query string stamped into `index.html`.
- **No source maps ship** (webpack `sourceMaps = false`); both
  `buildWebScripts` and `exportWeb` fail loudly if a `.map` file appears.
- `kanama-web/export-report.json` reports the full payload for budget tracking.

The production Wasm module has no name section and publishes no source maps,
so a Kotlin/Wasm stack trace is only anonymous `wasm-function[n]` frames. A
script error is therefore reported once through Godot's error log
(`SCRIPT ERROR: <type>: <message>`, `at: <Script>.<callback>`) and contained:
the call returns its default, and the frame loop and the other scripts go on.

### Threads

Exports use Godot's `nothreads` Web template. Threaded Godot Web is out of
scope, and it is a double gate: even with Godot's threads template, gameplay
runs on Kotlin/Wasm, whose own threading support is immature, so threads are
not one fix away. itch.io-style hosts' COOP/COEP support is experimental, which
is another reason the single-thread export is the one that ships.

### Script Portability Details

A script in the shared `<project>/kotlin-src/` root is directly
Wasm-compatible when written portably: the constructor takes `GodotHandle` (a
zero-cost value class over the backend's own handle — an FFM `MemorySegment` on
JVM/Android and iOS, a generation-tagged registry id on Web); vector and color
components are `Double` on every backend, so no numeric conversion is needed;
raw pointer identity (`handle.address()`) is replaced by `isSameInstance()`;
and no JVM-only APIs are used.

The Web build compiles gameplay from a merged view of `<project>/kotlin-src/`
and the per-file overrides in `<project>/web/kotlin-src/`. The
`web/kotlin-src-excludes.txt` merge fails loudly on a stale or contradictory
entry (a listed file that does not exist, or one that is also overridden).
Anything a shared script calls that the Web API surface does not model fails
the Web compile — that is the fail-loud coverage gate working as intended.
Files under the merged root resolve to `res://kotlin-src/*.kt` and match the
scene script attachments on both platforms.

`@Export` declarations are portable including `NodePath`
properties and hint metadata (a typed hint annotation such as `@ExportRange`
reaches the generated proxy verbatim as `@export_custom(hint, "hint string")`,
and `@ExportStorage` as `@export_storage`). The generated `Autoloads` object works
on Web too (the processor is pointed at the demo's `project.godot`); read an
autoload from a script callback. Two Web-specific rules fail the build loudly
instead of silently mis-hydrating: a property default must be a literal or a
constant expression the processor folds (`Mathf.PI / 3.0` is fine; a reference to
another property is not — the proxy re-emits the default into GDScript and pushes
it back into Kotlin at hydration), and a property type outside the supported Web
set is rejected with an error naming the property.

**A script's property initializers may call the engine**, as GDScript's `var x = ...` can. The
constructor runs with the script's proxy wired and its handle-keyed snapshots seeded, so an
initializer can call an engine singleton (`RenderingServer.getCurrentRenderingDriverName()`,
`RenderingServer.getCurrentRenderingMethod()`), construct a `RefCounted` (`ConfigFile.create()`),
and use `self`: `self.getTree()`, `self.getNodeOrNull(...)`, `self.addChild(...)` and the mirrored
spatial reads (`self.position`). Two Web differences from desktop: the node is usually already in the
tree when its initializers run (the proxy builds the script at `_enter_tree`/`_ready`; desktop runs
them at `.new()`, before it is), and the `self` reads see
the value mirrored when the proxy was wired, not a later engine change. A script that calls into
its own script instance from an initializer fails with "still constructing". If an initializer
throws, the script is not built and not retried: the failure is reported once, and any other
script that looks it up gets an error, not a half-built instance.

A **companion-object or top-level property** is initialized on first access, inside whichever
script's callback touches it first; what it keeps outlives that script, as on desktop (a node lives
until it is freed, a `RefCounted` while Kotlin holds it). Engine code that runs with no script
running at all (a call outside any callback) fails with "No Kanama Web proxy owns handle=0" or "A
Kanama Web singleton call was made outside a script callback".

**Physics loops should derive movement from velocity, not from re-reading a
spatial value they just wrote.** On Web, spatial reads (`self.position`,
`self.rotation`, …) come from a mirrored snapshot rather than a live engine
call. The snapshot refreshes at the start of every `_process` **and** (since
task 87) every `_physics_process` dispatch, so a per-tick read is coherent with
the previous tick — but it is still a start-of-dispatch mirror: a value the
engine changes *inside* the current tick (after `moveAndSlide()`, for example)
is not visible until the next dispatch. Desktop reads the engine live, so a
spelling that re-reads a just-written spatial value can be subtly
platform-different even when it works. Preferred spelling: accumulate movement
in `self.velocity` + `moveAndSlide()`, and derive orientation from the intended
direction (as squash's Player does with `lookAtFromPosition(self.position,
self.position + direction, UP)` — the *direction* is the input, not a read-back
of engine physics).

## Validation Fixtures

The current fixtures are per-demo driver scripts
(`scripts/web/drivers/demos/*.mjs`, one per corpus demo plus the `soak`, `spike`,
and `visibilityprobe` harness drivers, shared by the
Chrome/Firefox/Safari engine drivers) plus machine-readable JSON results, one
per demo × browser cell, validated against a versioned envelope schema by
`web_export_smoke.sh`; `web_ci_matrix.sh` aggregates the cells into a single
evidence JSON. A run is not green from page load alone — it must satisfy
gameplay assertions, final state, the crossing budget,
handle/callback/scheduler teardown, console error checks, and protocol-version
match, and the harness's own `web_export_smoke: PASS` line (not a driver's
check count) is what decides green.

**The required-member gate (task 81).** The schema also asserts *what* ran,
not only that checks were boolean: the bridge records every boundary dispatch
of a registered script member (the nine `call*` funnels by `method#<id>`, the
dispatched virtuals by name, Kotlin-lambda signal subscriptions as one
aggregate key) at the `invoke` chokepoint, the engine drivers collect that
census into the envelope's `exercisedMembers` section — resolving ids to
member names from the export's own copy of
`KanamaWebProtocol.generated.json` — and `result_schema.py` fails any demo
whose per-demo *required* list in `scripts/web/required_members.json` names a
member that did not dispatch, naming exactly which. A driver asserting frame
counters is therefore no longer exactly as green as one asserting an enemy
died (the FPS `Enemy.damage` break survived every gate for weeks because
nothing required that member to run). Two scope notes: the census sees the
JS↔Kotlin boundary — exactly where the dispatch-degradation bug class lives —
so a member called Kotlin-to-Kotlin (`kotlinScriptInstance`) cannot be
required and appears as a documented `todo` row with its transitive evidence;
and an absent demo or empty list passes, so coverage ratchets up as drivers
grow rather than being faked. Old exports fail the gate with a rebuild hint
(their envelopes carry unresolved `method#N` keys because the export predates
the manifest copy).

**The Web API exercise.** Every generated Web wrapper member is one
`GodotBackendCalls.invoke<Shape>` call, so a defect in a call shape (its Kotlin arm, JS extern or
GDScript arm) is shared by every opcode of it. The web3d fixture runs one generated row per shape
of `scripts/platform_backend_calls.json`: `scripts/generate_web_exercise.py` writes
`web-runtime/src/web3dSmoke/web/kotlin-src/ApiExerciseProbe.kt` and the
[Web API Exercise](../../reference/generated/web-api-exercise.md) page from a hand-kept row table
(the opcode, receiver and arguments per shape) and derives the rest from the contract. A row calls
its shape on an object the scene constructs and checks it against the generic tier
(`WebExperimentalGenericCall.callImmediate`) on the same object: a result equals the generic call's,
a setter's value reads back through the generic getter (a queued mutation a frame later), and the
few shapes the generic tier cannot be the oracle of assert a connected handler's payload, a new
object's state or a seeded random draw, or only reach. What the generic tier cannot reach by
itself (the engine singletons, `seed`) is in `web-runtime/src/web3dSmoke/api_twin.gd`. The driver requires every row to
pass (`checks.apiExercise`); the console line is `[kanama:web] API EXERCISE: <n> passed, <m> failed,
<w> weak, <r> reach, <e> excluded in <ms> ms`, with one `API EXERCISE FAIL:` line per failed row
(`KANAMA_WEB_SMOKE_DEBUG=1` copies both into the Chrome driver log). `generate_web_exercise.py
--check` (local_ci stage "Web API exercise drift", `:web-runtime:checkWebExercise`) fails when a
contract shape has no row, so admitting a new shape means adding its row.

Browser-specific notes:

- **Safari** exposes Retina coordinate bugs the other engines hide. Godot's
  coordinate space and W3C pointer coordinates are both **CSS pixels**, so a
  driver must derive on-screen geometry from `getBoundingClientRect()`. Deriving
  it from `canvas.width` — the `devicePixelRatio`-scaled backing store — happens
  to agree at DPR 1, so it passes headless Chrome/Firefox and targets the wrong
  tile on a Retina Safari. SafariDriver also does not expose the legacy browser
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
./scripts/local_ci.sh /path/to/godot-4.7.2-stable
```

## Out of Scope

No Web editor or compiler, no hot reload, no threads, no TeaVM or Kotlin/JS
production path, no Web GDExtension, and no Supported status. The user-facing export workflow lives
in [Export for the Web](../../exporting/web.md); promotion past Experimental is a
separate decision, gated on the browser matrix and budgets described in
[Device and Browser Runs](../device-runs.md#web-runs).

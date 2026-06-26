# Callable Arguments in Generated Wrappers — Design (Phase 1.4)

Design record for roadmap task 1.4 (`wrapper-coverage-roadmap.md`): how
generated desktop/Android wrappers pass `Callable` arguments to engine
methods, who owns what for how long, and what the iOS counterpart will
need later.

## Where Callable support already stood

Phase 1.4 is **not** the first Callable surface. Before this task:

- `BuiltinTypes.initCallable(dest, objectHandle, methodName)` constructs an
  engine `Callable` via the builtin **constructor index 2**
  (`Callable(Object, StringName)`), and `destroyTyped(VariantType.CALLABLE, …)`
  destroys the cell.
- ~60 hand-audited Callable arg shapes already exist as special cases inside
  `candidate_for()` in `scripts/generate_api_wrapper.py` (e.g. `connect`,
  `Tween.tween_callback`, `OpenXRSpatialEntityExtension.create_spatial_context`)
  backed by `ObjectCalls.ptrcallWith…Callable…` helpers.
- The public Kotlin form is `GodotCallable(target: GodotObject, method: String)`;
  the generator expands each Callable parameter into
  `name.target.handle, name.method` at the call site
  (`call_argument_expressions`).
- Lambda callbacks exist **only** for signals: `GodotSignal.connect`
  registers the lambda in `SignalCallbackRegistry` and connects an
  object+method Callable to a hidden `__kanama_signal_dispatchN` method that
  KSP emits on every Kanama script class, with the registry id as a bound
  argument. Lifetime is bounded by the explicit `SignalConnection.close()`.

So the design question is narrow: which Callable *form* may generated
wrappers accept, and what is the lifetime story for the remaining six
blocked methods — all of which hand the Callable to the engine as an
**engine-retained async completion callback**:

| Method | Retention |
|---|---|
| `JavaClassWrapper.create_sam_callback(String, Callable)` | held by the returned `JavaObject` proxy, unbounded |
| `OpenXRSpatialAnchorCapability.create_default_persistence_context(Callable)` | held until the `OpenXRFutureResult` completes |
| `OpenXRSpatialAnchorCapability.start_entity_discovery(RID, typedarray, Object, Object, Callable)` | idem |
| `OpenXRSpatialMarkerTrackingCapability.start_entity_discovery(…)` | idem (same hash) |
| `OpenXRSpatialPlaneTrackingCapability.start_entity_discovery(…)` | idem (same hash) |
| `OpenXRSpatialEntityExtension.discover_spatial_entities_with_component_data(RID, typedarray, Object, Callable)` | idem |

## Decision 1 — argument form: `GodotCallable` (object + method), no lambdas in 1.4

Generated wrappers keep accepting `GodotCallable(target, method)` only.
Rationale:

- **Lifetime lives entirely in the engine.** An object+method Callable
  stores an `ObjectID` + `StringName` — *no Kotlin-side state exists*, so
  there is nothing for Kanama to keep alive and nothing that can leak,
  regardless of how long the engine retains the Callable.
- **Consistency**: every already-shipped Callable wrapper (including the
  sibling `create_spatial_context`) uses this form; introducing a second
  form for six methods would fork the marshalling contract mid-phase.
- **iOS parity**: the same form is implementable over the C shim with only
  the Callable variant constructor (see Decision 5).

## Decision 2 — destroy/unreference discipline: destroy the cell immediately after ptrcall

Per-call confined `Arena`; `allocateCallable` → `initCallable` → ptrcall →
`destroyTyped(VariantType.CALLABLE, cell)` in `finally`.

This is safe even though the engine retains the Callable, because Godot's
ptrcall ABI passes builtin args as `const void*` and the bound method
**copy-constructs** the `Callable` parameter (`PtrToArg<Callable>::convert`
returns by value). Whatever the engine stores afterwards is its own copy:

- object+method Callables copy as `ObjectID` + refcounted `StringName`;
- custom Callables (future) copy as a refcount bump on the engine-side
  `CallableCustom`.

Either way the Kanama-owned cell is dead weight after `objectMethodBindPtrcall`
returns, and destroying it in `finally` matches every existing Callable
helper. The new helpers follow the same pattern verbatim.

## Decision 3 — reentrancy and late invocation

The engine may invoke the retained Callable arbitrarily later (XR discovery
completion, Java SAM dispatch), possibly after the target object was freed:

- The engine resolves the `ObjectID` at *call* time through `ObjectDB`. A
  freed target makes the Callable invalid; the engine logs a call error and
  the invocation is a no-op. **Never UB, never a dangling Kotlin reference**
  — identical to GDScript semantics and to how Kanama signal connections
  already degrade.
- If the target is a Kanama script instance, invocation enters through the
  existing KSP script `call` dispatch — the same path as signal delivery,
  with the same threading caveats (engine may call on a non-main thread for
  XR; the user method owns its own thread-safety, as with signals).
- Extension teardown: object+method Callables hold no extension-owned
  function pointers, so a retained Callable outliving the JVM runtime
  degrades to the invalid-ObjectID no-op above rather than calling into a
  dead runtime. (This is the property the custom-callable path must
  re-establish explicitly via `free_func`; see Decision 4.)

## Decision 4 — Kotlin lambda → Callable: designed, deliberately deferred

The signal-callback machinery was evaluated for reuse and **rejected** for
these methods: it requires (a) a Kanama script-instance target carrying the
hidden `__kanama_signal_dispatchN` methods — generated wrappers cannot
assume one — and (b) an explicit `close()` to release the
`SignalCallbackRegistry` entry. For engine-retained callbacks there is no
disconnect moment, so the bound-id trick would leak one registry entry per
call with no release signal. Correctness over coverage: no lambda surface
ships until lifetime is exact.

The correct lambda design (future task, not implemented here) is the
**custom-callable path** over `callable_custom_create2` /
`GDExtensionCallableCustomInfo2` (`gdextension/gdextension_interface.h`):

- `LambdaCallableRegistry` (mirror of `SignalCallbackRegistry`):
  `id -> (List<Any?>) -> Any?`; `callable_userdata` = id;
  `token` = the extension library pointer.
- `call_func` Panama upcall (via `Upcalls.stub`, process-long arena):
  decode `p_args` Variants with the existing `BuiltinTypes` readers, invoke
  the lambda, write `r_return` with `initVariantFromAny`.
- `free_func` upcall unregisters the id. The engine calls it when the last
  engine-side reference drops — this gives the *exact* lifetime that
  engine-retained callbacks need: the lambda lives precisely as long as any
  Callable copy exists, with no user-visible close().
- `is_valid_func`/hash/equal/less/to_string/get_argument_count may start as
  NULL (the spec defaults identity to `call_func` + userdata).
- Adoption in generated wrappers then happens by widening `GodotCallable`
  into a sealed hierarchy (method-ref | lambda) and funneling helper
  marshalling through a single `BuiltinTypes.initCallable(cell, callable)`
  entry point — a deliberate, separate refactor because today's ~60 helpers
  take `.target.handle, .method` pairs and every signature is covered by
  the layout/signature audits.

Deferred because it adds a new engine-facing upcall surface (crash-grade
risk), it is orthogonal to unblocking the six methods, and it should land
once for both lambda-connect and lambda-args rather than twice.

## Decision 5 — iOS forward-compatibility

The shipped design is implementable over the C shim with no new concepts:

- Shim needs `Callable` construct-from-(object, StringName) — the same
  builtin **constructor index 2** Kanama pins in `BuiltinTypes.initCallable`
  — plus the variant destructor for the cell; both belong in the shim's
  self-test matrix like every other ptrcall width.
- Helper discipline is identical: build cell, ptrcall, destroy cell.
- `Callable` stays out of `IOS_ARG_KINDS` until that shim support and a
  self-test row exist; these six methods stay iOS-skipped until then.
- The future lambda path maps to a shim-side `CallableCustomInfo2` with
  Kotlin/Native static trampolines — precedent already exists in
  `kanama_ios_callable_trampoline` (signal payload path).

## What Phase 1.4 ships

- Generator (`candidate_for`, hand-audited Callable tables — Callable
  deliberately stays **out of `CALL_SHAPES`**, which
  `audit_generator_shape_policy.py` enforces):
    - `(Callable) -> OpenXRFutureResult` joins the existing
      `ptrcallWithCallableArgRetObject` return-type allowlist.
    - `(String, Callable) -> JavaObject` →
      `ptrcallWithStringCallableArgsRetObject` (new helper).
    - `(RID, typedarray::T, Object, Callable) -> OpenXRFutureResult` →
      `ptrcallWithRIDObjectListObjectCallableArgsRetObject` (new helper).
    - `(RID, typedarray::T, Object, Object, Callable) -> OpenXRFutureResult` →
      `ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject` (new helper).
    - Note on the `typedarray::T` args: these do NOT contradict the Phase 1.3
      "typed-array args blocked" decision — Object-element typed arrays reuse
      the long-standing audited `initArrayOfObjects` path (same as the
      pre-existing `create_spatial_context` helper). What Phase 1.3 left
      blocked, and what remains blocked, is TypedRIDArray /
      TypedTransform3DArray args, which have no `BuiltinTypes` init paths.
- All six blocked methods are exposed; none needed to stay blocked, because
  under the object+method form the engine-retained lifetime is safe by
  construction (Decisions 2–3).
- Runtime untouched: no new registries, no upcalls, no `GodotCallable`
  shape change.

# Wrapper Maintenance

This page is for Kanama maintainers working on generated wrappers, KDoc, or API
refreshes. Game projects do not need these commands.

## Generator Role

The wrapper generator turns Godot's `extension_api.json` into conservative
Kotlin wrapper drafts. Its job is to scale API coverage without hand-writing
thousands of MethodBind calls, while refusing shapes where Kanama has not
defined ABI, ownership, or marshalling policy yet.

Generated drafts are not automatically promoted. A class only becomes public
source after review, smoke coverage where practical, and reproducibility checks
against the generator output. This keeps broad wrapper growth auditable instead
of relying on one-off manual wrappers.

Refreshing generated Panama bindings from a new Godot API/header set requires
`jextract` 25+. Normal game projects do not run `jextract`; it is only part of
Kanama's API/header refresh workflow.

## Wrapper Guardrails

Kanama's conservative wrapper generator is intentionally fail-loud. CI checks
the generated method shapes against the runtime FFM helpers, including
int32/int64 width, float/double layout, packed-array storage, generic
Array/Dictionary/Variant policy, and Callable blocking.

Generated public APIs must keep concrete Godot object types concrete. For
example, a Godot `Node3D` return should render as `Node3D?`, not
`GodotObject`, `Object`, or `MemorySegment`. Exact Godot `Object` APIs remain
dynamic because the engine itself does not promise a more specific type.
Ownership-sensitive namespace-style types use explicit policy before default
generation. `Callable` stays blocked unless a helper has a bounded ownership
shape; `DirAccess`, `FileAccess`, and `SceneTree` use dedicated handle aliases
where factory methods return nullable object handles.

Generated wrappers are held to a **full per-platform drift-gate**. The generator
can emit selected classes with `--emit-class`, and `check_full_drift_gate` in
`scripts/check_wrapper_generator.py` regenerates the *entire* committed wrapper
set — every in-API class under `src/main/kotlin/.../api` (desktop/Android) and
`ios-runtime/.../api` (iOS) — and fails if any committed file diverges from a
fresh regen (behavior-comparable; `sync_kdoc_from_godot_docs.py` owns the prose).
A hand-edit to a generated class, an un-adopted generator improvement, or a
platform copy drifting from another all fail the gate, so the three wrapper trees
cannot silently diverge. The only exemptions are the deliberately hand-shaped
classes listed in `DESKTOP_HANDSHAPED`/`IOS_HANDSHAPED` (hand-authored facades,
ergonomic helpers, aliases, and custom defaults the generator does not emit); a
class joins that list only when the generator genuinely cannot reproduce it.
Non-API files (`GodotObject`, `GD`, `DirAccessHandle`, …) are auto-excluded.
Android has no separate committed tree — `prepareAndroidKanamaSources` copies the
desktop sources through the PanamaPort remap, so the desktop gate covers it
transitively. Adopted classes with skipped methods are only accepted when every
skip is a Godot virtual callback that belongs to the override-registration design
rather than the public ptrcall wrapper surface.

## RefCounted Return Ownership

Engine methods whose return type derives from `RefCounted` hand the ptrcall
caller a **+1 reference through the return slot**. This holds for every such
method: Godot's `PtrToArg<Ref<T>>::encode` copy-assigns into the slot and
`PtrToArg<RequiredResult<T>>::encode` nets the same +1 — `meta: "required"`
signals never-null-on-success, **not** a different ownership convention
(runtime-measured on Godot 4.7-stable; see the `Tweener ownership` probe in
`scripts/runtime_smoke.sh`). Non-RefCounted object returns (nodes, servers)
are raw pointers with no reference transfer.

The wrapper convention on desktop/Android:

- A generated wrapper returned from a RefCounted-typed method **owns that
  reference**; `close()` releases it (`unreference()` + destroy at zero).
  This matches the script-property retention path
  (`ScriptBridge.retainScriptResource`), which takes its own reference.
- **Self-returning fluent methods collapse**: when the returned address equals
  the receiver's handle, the generated method releases the duplicate reference
  and returns `this` instead of minting a second owning wrapper (chained calls
  such as `tweenAwait(...)?.setTimeout(...)` stay reference-neutral). The
  generator emits this pattern whenever the receiver class conforms to the
  method's return class; it is the same policy the hand-shaped Tween/Tweener
  classes use (`wrapOrThis`).
- Wrappers minted from **Variant-path** returns or `fromHandle` casts borrow;
  a release there underflows. Hand-shaped self-collapse helpers must therefore
  sit on a ptrcall object-return helper, never on `callWithVariantArgs`
  (`PropertyTweener.from` regressed exactly this way once).

The iOS island mirrors the same convention (task 30): the C-shim exposes
`object_destroy` (`kanama_ios_godot_object_destroy`), `ObjectCalls.destroyObject`
wraps it, and the generated iOS `RefCounted` carries `close()` (unreference +
destroy at zero) plus the internal `releaseHandle` primitive via generator
custom sections — so the collapse pattern above is emitted identically under
`--ios-emit-class`. The base iOS `GodotObject.close()` stays a no-op (node/server
returns are raw pointers with no reference transfer). Both the custom sections
and the collapse emission are locked by `check_ios_policies`, and the on-device
self-test matrix carries a `refcounted-ret-owns-plus1` refcount probe
(duplicate() → refcount 1 → close()).

### Freshly created resources and the `ResourceSaver.save` guard

`X.create()` factories return a **non-owning** wrapper: it holds only Godot's
construction placeholder (refcount 1, `refcount_init` still set), *not* an owned
`+1`. That is deliberate — it matches GDScript's `MyResource.new()` idiom where
you create a resource, assign it to the engine (a mesh, a material slot, the
scene tree), and never explicitly free it (`RefCounted.close()`'s contract says
so). An owning `create()` would leak every such assign-and-forget, because
there is no wrapper finalizer to release the reference.

The one hazard is passing a freshly created resource **by value to a method that
takes `const Ref<Resource>&` and releases its transient `Ref` on return** — the
canonical case is `ResourceSaver.save`. The engine's `PtrToArg<Ref<Resource>>`
claims the placeholder and drops it on return; for a resource whose only
reference *was* that placeholder, the refcount hits zero and the object is freed
while its Kotlin wrapper still points at it (issue #81 — a JVM SIGSEGV on the
next touch, after the file has already been written).

The fix is a guard inside the save ptrcall helper
(`ObjectCalls.ptrcallWithObjectStringLongArgsRetLong`, the sole wrapper of its
`(Object, String, int64) -> int64` shape): take a protective `reference()`
before the call, then release it **only if `get_reference_count() > 1`
afterward**. When the call consumed the sole reference the protective ref is
kept — the resource survives and becomes wrapper-owned, so `close()` frees it
once; an already-owned resource (loaded/cached, scene-held) is restored to its
original count, a balanced no-op. The desktop helper is hand-written in
`ObjectCalls.kt`; the iOS mirror is hand-written too
(`IOS_HANDWRITTEN_HELPERS`), so `ResourceSaver` stays fully generated on both
platforms. Covered by the `issue81 packed_scene_save` row in
`scripts/runtime_smoke.sh`.

Two **dynamic** paths share this ownership problem: `ClassDB.instantiate` and
`ClassDB.class_call_static` both return a Variant that holds the fresh
instance's *only* reference, so the borrowed Variant-scalar decode would hand
back a handle that dies with the Variant (use-after-free for RefCounted classes
— GitHub PR #42; `instantiate` is also the canonical construction path for
third-party GDExtension classes, and a static factory such as
`RegEx.create_from_string` or `Image.create` has the same sole-reference
return). Both route through the same mechanism — a `METHOD_CALL_SHAPE_OVERRIDES`
entry in `generate_api_wrapper.py` that names the owned dispatch helper the
generated wrapper calls — and differ only in that helper:

- `ClassDB.instantiate` is a generated ptrcall →
  `ptrcallWithStringNameArgRetVariantScalarOwned`.
- `ClassDB.class_call_static` is a varargs `Object.call`, so its override names
  the vararg helper `callWithVariantArgsOwned`, which delegates to
  `callWithVariantArgs(owned = true)` (`render_vararg_method` reads the override
  to pick the helper, defaulting to `callWithVariantArgs`).

In both cases RefCounted results are retained **before** the return Variant is
destroyed and come back as the owning `RefCounted` wrapper (`close()` releases).
On iOS the retain must happen inside the C shim, before it destroys the return
Variant: `instantiate` uses the dedicated entry
`kanama_ios_classdb_instantiate_owned`, while `class_call_static` uses the
shared `kanama_ios_godot_object_call` with its `out_is_refcounted` out-param
(non-NULL turns on the retain). Non-RefCounted and non-object results stay
borrowed. Do **not** blanket-retain in `variantToScalar` itself — every other
dynamic object read (property gets, ordinary `call()` returns) is a borrow, and
a retain there leaks one reference per read. Other fresh-sole-reference dynamic
calls (`obj.call("duplicate")`) remain unfixed by design for now; add a
`METHOD_CALL_SHAPE_OVERRIDES` entry naming an owned helper if one bites.
Validated by the `ClassDB instantiate ownership` and `ClassDB class_call_static
ownership` probes in `scripts/runtime_smoke.sh`.

Engine-wide `MethodName`, `PropertyName`, and `SignalName` constants are
generated from `extension_api.json` separately from the class wrapper drafts:

```sh
python3 scripts/generate_name_constants.py
```

`scripts/check_wrapper_generator.py` runs the same generator in `--check` mode,
so a Godot API refresh fails loudly if the committed name constants are stale.

## iOS Generator Policy

The iOS island is emitted under `IOS_AUDIT_ONLY` and has extra policy so a regen stays
honest instead of silently dropping or clashing. These are locked by `check_ios_policies`
in `scripts/check_wrapper_generator.py`:

- **Bare-`Object` returns.** `get_collider()`-style methods that return the root Godot
  `Object` wrap to `GodotObject?`. Desktop/Android find `GodotObject.wrap()` via a
  `GodotObject.kt` file; on iOS `GodotObject` lives inside `IosGodotApi.kt`, so
  `wrapper_has_wrap` special-cases `GodotObject` as always-present. Without this the iOS
  regen drops every bare-`Object`-return method (a silent coverage loss), even though the
  `ptrcallNoArgsRetObject` helper is fully wired (Node returns use it).

- **Subclass-override openness.** Where a hand-written iOS subclass overrides a generated
  method, the base method must be generated `open` — otherwise a regen drops the keyword and
  the override stops compiling. `Node.createTween()` is emitted `open` (via the Node custom
  member section) so the hand-written `SceneTree` subclass can override it with the correct
  `SceneTree.create_tween` bind (the FPS F2 fix). Add such cases to the class's
  `IOS_CUSTOM_MEMBER_SECTIONS` entry, not by hand-editing the generated file.

- **Explicit class collisions.** Real Godot classes that are deliberately hand-written on iOS
  (inside `IosGodotApi.kt` or a bespoke single-class file) are listed in
  `IOS_HANDWRITTEN_COLLISION_CLASSES` with a reason. `--ios-emit-class <that class>` logs a
  `collision:` line and skips it, instead of writing a `<Class>.kt` that duplicate-declares
  the class and breaks the compile. When a class graduates to a real generated wrapper
  (as `Time`/`InputMap`/`PhysicsServer3D` did), delete its entry so generation is allowed.

- **Explicit uncompilable classes.** `IOS_UNSUPPORTED_CLASSES` lists the classes whose
  generated draft cannot compile on iOS, each with its reason: `DirAccess`/`FileAccess`
  (their drafts reference the hand-authored `DirAccessHandle`/`FileAccessHandle` desktop
  policy classes iOS does not carry) and `MethodTweener` (its generated fluent methods clash
  with the hand-written iOS `Tweener` glue). `--ios-emit-class <that class>` logs an
  `unsupported:` line and skips it. Together with the collision registry these are the only
  by-design exceptions to iOS class-set parity with desktop (task 30); retire an entry by
  porting the desktop policy surface it depends on.

- **RefCounted ownership sections.** The iOS `RefCounted` wrapper's `close()`/`releaseHandle`
  custom sections and the fluent self-return collapse emission (see "RefCounted Return
  Ownership" above) are locked by `check_ios_policies` so a generator refactor cannot
  silently reintroduce the per-call RefCounted return leak.

Two cross-platform load-bearing wrapper shapes are reproduced by explicit policy (so a regen
does not drop them), locked by `check_ios_policies`:

- **Composite default-value overrides.** `KOTLIN_DEFAULT_EXPRESSION_OVERRIDES` injects a final
  Kotlin default expression by exact `(class, method, arg)`. `Node3D.look_at(up = Vector3.UP)`
  is the current entry — demos call the 1-arg `lookAt(target)` form and rely on it. Kept
  surgical (per exact arg) so no other method silently gains a default.
- **Non-null factory.** `NON_NULL_FROM_HANDLE_CLASSES` (currently `{Resource}`) emits
  `fromHandle(handle): Resource` (non-null) so a `@ScriptClass(attachTo = "Resource")` script's
  `(MemorySegment) -> Resource` selfFactory type-checks. The nullable `wrap` helper stays.

The broad pre-existing regen drift (a fresh regen once changed the majority of the committed
desktop generated files — accumulated generator improvements that were never re-adopted) has been
**closed** by the task 21 convergence pass: all in-API wrappers were regenerated and re-adopted
from the honest generator, and the full drift-gate above now keeps committed == fresh regen on
every platform, so that class of drift cannot silently return.

## Coverage Triage

For v0.3.0 wrapper work, use the coverage reports as the baseline instead of
waiting for another demo to expose a gap:

1. Refresh `docs/contributing/api-coverage.md` and
   `docs/contributing/wrapper-generator-report.md`.
2. Start from the generator skip categories and missing helper shapes.
3. Rank gaps by common Godot workflows: scene tree, resources, physics queries,
   animation, UI, input, materials, signals, RPC, and export-facing APIs.
4. Prefer removing raw escape hatches (`Object.call`, `Object.get`,
   `Object.set`, string method names, manual handle casts) from normal user
   workflows over broad class-count increases.
5. Use public demos as regression coverage after choosing a wrapper slice, not
   as the only source of wrapper priorities.

The current coverage pages already separate promoted source coverage from
generator capability. Treat low-coverage areas such as variant/dictionary
helper shapes, typed arrays, and multiplayer/editor methods as API design work:
add helper policy and audits before promoting wider wrappers.

### Long-tail triage (task 22)

Once the class surface is broadly promoted, the remaining skips are a long tail
of **data-type / shape** gaps, not missing classes. A full triage of the
non-virtual skips and the skipped properties found:

- **Non-virtual method skips (65 as of task 28's start):** the
  large majority are *intentional*, not gaps —
  - **~49 root `Object` methods** (`call`/`get`/`set`/`connect`/`emit_signal`/…)
    are deliberately routed through the hand-shaped `GodotObject` policy, not
    regenerated per subclass. These are the escape hatches; keep them on
    `GodotObject`.
  - **2 `RefCounted` lifetime methods** (`reference`/`init_ref`) are
    ownership-sensitive and hand-shaped.
  - **One clean low-ABI slice landed** — a new call shape that recombines
    *already-audited* primitives and whose signature contains a type not in
    `IOS_ARG_KINDS` / `IOS_RET_KOTLIN`, so it generates on desktop+Android and
    is **cleanly iOS-skipped** (no C-shim helper, no self-test row, no device
    gate — the same guardrail Dictionary uses):
    - **`(Rect2i, Object, Color, int32, Object)`→`void`**
      (`DrawableTexture2D.blit_rect`) via `ptrcallWithRect2iObjectColorIntObjectArgs`
      — reuses the Rect2i/Object/Color/int cells. `Rect2i` is not an iOS arg kind.
  - **`Dictionary`→`Dictionary` (`GDScriptTextDocument.resolve`/`.rename`) is a
    by-design skip**, not a gap. Task 22 briefly landed it via
    `ptrcallWithDictionaryArgRetDictionary`, but the shape was **reverted for
    stability**: a Dictionary-in/Dictionary-out passthrough through the generic
    `Map<String, Any?>` helper silently drops non-String keys, so
    `audit_generator_shape_policy` keeps the `('Dictionary',) -> 'Dictionary'`
    shape out of `CALL_SHAPES` (it must stay hand-audited if ever needed). The
    two methods are editor-LSP plumbing with no game-runtime workflow.
  - **The formerly deferred ~12 landed in task 28** as audited desktop/Android
    families (all iOS-cleanly-skipped — none of the new kinds are iOS
    arg/return kinds; task 30 brought iOS to full class-set parity, and these
    per-method shape mirrors remain documented skip-report deferrals):
    - **Typed-array argument family**: RenderingDevice `blas_create` /
      `tlas_build` / `raytracing_pipeline_create`, `ImporterMesh.merge_importer_meshes`
      (`TypedObjectArray` + `TypedTransform3DArray`), `DrawableTexture2D.blit_rect_multi`,
      `RenderingServer.texture_drawable_blit_rect` (`TypedRIDArray` + `Rect2i`),
      and OpenXR `do_entity_update` — new `ObjectCalls` helpers recombining the
      audited `initArrayOfObjects` / `initArrayOfRids` / `initArray` primitives,
      registered in the shape-policy audit's dynamic helper sets.
    - **`Signal` scalar arg** (`Tween.tween_await`): admitted per-method like
      Callable (never in the generic `CALL_SHAPES` table). `BuiltinTypes.initSignal`
      builds Signal(Object, StringName) for the call and destroys it after; the
      engine keeps its own ObjectID-based copy, so no Kotlin state is retained.
      Runtime-validated in `runtime_smoke` (await resolves on real emission).
    - **Hand-shaped-class landings**: `EditorExportPlatform.export_project`,
      `OpenXRSpatialAnchorCapability.create_new_anchor`/`do_entity_update`, and
      `Font.find_variation` are hand-hosted on their `DESKTOP_HANDSHAPED` wrappers
      in the exact generated form (draft-verified) — the committed copies had
      drifted to pre-4.7 signatures on compatibility hashes and now target the
      current 4.7 binds.

    After task 28, the generator report's non-virtual skips are **only** the
    documented by-design entries above (root-Object policy, RefCounted lifetime,
    the `GDScriptTextDocument` Dictionary revert) — each carries its rationale
    verbatim in the report via `BY_DESIGN_METHOD_SKIPS` / the policy messages.
- **~505 skipped properties are NOT a marshalling gap.** Triaged:
  - **~415 are indexed / parameterized accessors** whose getter takes an
    argument (e.g. `get_list_stream(i)`, `get_param(param)`). Godot lists them as
    `foo/0`, `foo/1`, … pseudo-properties; they are not Kotlin `val`/`var`s by
    design, and the underlying getter/setter **methods are already generated and
    callable**. Not a gap.
  - **~90 have no simple own getter method** — the getter is inherited (the
    property surfaces on the parent wrapper and the getter method is inherited),
    an engine virtual `_get_*` (task 13 territory), or an indexed inherited
    getter. None are unlocked by a new marshalling shape.

  Net: **no skipped property is a "quick win behind a shape."** Property coverage
  advances only when its *getter method* becomes generatable (a new no-arg return
  shape) or via ergonomic hand-shaping, never by widening property emission.

### Virtual-return coverage (task 29)

`@OverrideVirtual` return marshalling covers **every Variant-expressible return
family** in the 4.7 virtual signature table on desktop/Android. Tasks 13 + 29
landed, per family (Kotlin return type in parentheses): the audited scalar/POD
set, `String`, `PackedStringArray` (`List<String>`), all remaining fixed-element
`Packed*Array`s (`ByteArray`/`IntArray`/`LongArray`/`FloatArray`/`DoubleArray`/
`List<Vector2>`/`List<Vector3>`/`List<Color>`), `Dictionary`
(`Map<String, Any?>`, String keys — the audited container policy), generic and
typed Arrays (`List<Any?>` — also the shape for the engine's `typedarray::*`
returns), `RID`, `Rect2`, `AABB`, `Transform2D`, `Transform3D`, `Projection`,
and `Variant` (`Any?`). Two families additionally ride existing ones by
Variant conversion: `StringName` returns are declared as `String` and
enum/bitfield returns as `Long` (the engine casts the returned Variant at the
call site, exactly as it does for GDScript).

**By-design excluded returns (6 virtuals):** `void*` (3) and `const Glyph*`
(3, TextServerExtension glyph buffers). Raw-pointer returns are not
Variant-expressible — GDScript cannot override these virtuals either — so they
stay outside the `@OverrideVirtual` surface permanently.

**iOS mirror bounds:** iOS value-returns cover Bool/Int/Float/Vector2/Vector2i/
Vector3/String/RID, all fixed-element `Packed*Array` families (a
`KanamaIosPackedArgDesc` + flat element buffer through the PT return scratch),
`PackedStringArray`, `Dictionary` and generic `Array` (tagged-entry blobs), and
Variant returns over that audited inner-type set (an unaudited inner value
serializes as nil — a valid Variant). The `Rect2`/`AABB`/`Transform2D`/
`Transform3D`/`Projection` returns are **desktop/Android-only for now, by
design**: Transform3D/Projection exceed the 32-byte PT return scratch (they
need the pointer-to-scratch route), the C shim has no `variant_from` boxing for
the four yet, and no 4.7 virtual returning them has arguments that are
declarable in Kotlin today — the iOS emitter warn-skips them fail-loud (the
same guardrail the task-28 desktop families use), and the mirror belongs to the
iOS wrapper-family follow-up slice.

Width-sensitive coverage: the desktop families are runtime-validated in
`runtime_smoke.sh` (the `vret` rows — GDScript `typeof()` + content checks
through the script-instance dispatch, including `Int.MAX_VALUE`, `1.0e308`, and
full-range bytes); the iOS families have Kotlin encode round-trips plus C
build/box round-trips in the on-device self-test matrix (the
`virtual-packed-*-ret` / `virtual-dictionary-ret` / `virtual-array-ret` rows).

**iOS `@ScriptProperty` conversion parity:** narrow scalars
(`kotlin.Float`/`kotlin.Int` widened to the 64-bit FLOAT/INT slots), Kotlin
`enum class` exports (INT slot carrying the ordinal), and enum-list exports
(typed int Array of ordinals) use the same conversions on iOS as on
desktop/Android. The iOS emitter narrows numeric slots, clamps scalar enum
ordinals, and maps/clamps enum-list elements. Integer arrays have a dedicated
C/runtime delivery entrypoint rather than sharing object-array cells, so stale
or malformed integers cannot be misread as object handles. Scene-loaded values
and later engine-originated `Object.set` updates both take this path. The iOS
descriptor now preserves each property's hint, hint string, and usage, while
the ScriptInstance getter maps the Kotlin values back to widened scalars or
ordinal arrays for engine reads.

## KDoc Maintenance

Public Godot-backed wrappers and builtin value types carry generated KDoc
imported from Godot's `doc/classes/*.xml` files. The generated blocks include a
`Generated from Godot docs:` marker so they can be refreshed safely instead of
hand-edited.

**Docs version pin:** wrapper KDoc is synced from the **Godot 4.7-stable**
`doc/classes` (commit `5b4e0cb0fd`), matching the shipped runtime baseline. Do
not sync from a `-rc` / `latest` tree — a mismatched docs tree reintroduces
version skew. Bump this pin when the Godot runtime baseline moves, and re-run the
full refresh (part of the [Godot Upgrade Runbook](godot-upgrade.md)).

To check whether KDoc is current against a local Godot source tree:

```sh
python3 scripts/sync_kdoc_from_godot_docs.py \
  --godot-docs /path/to/godot-4.7-stable/doc/classes --check
```

To refresh the whole tree (the 4.7-stable baseline refresh, task 23):

```sh
python3 scripts/sync_kdoc_from_godot_docs.py \
  --godot-docs /path/to/godot-4.7-stable/doc/classes --write
```

The script uses `$GODOT_DOCS` when set, otherwise it defaults to
`godot/doc/classes` relative to the current working directory. Use
`--godot-docs /path/to/godot/doc/classes` when syncing against a different
Godot checkout. Use `--classes Node,Node3D,Tween` for a focused refresh while
working on one wrapper slice, or `--scope types --classes Vector3` when
working on builtin values. Keep the default separate `--api-dir`
(`.../kanama/api`) and `--types-dir` (`.../kanama/types`) — pointing `--types-dir`
at the api dir mislabels node classes as value types.

The refresh is **gate-neutral**: `check_full_drift_gate` strips
`Generated from Godot docs:` blocks before comparing (`comparable_source`), so a
KDoc refresh never affects drift-gate correctness and only touches comments. The
sync replaces an existing generated block in place (multi-line **or** the older
single-line `/** … */` form), so re-running is idempotent.

## Value-Type Audits

Value-type helpers that mirror Godot builtin methods should call the matching
Godot builtin unless the local implementation is deliberately proven equivalent.
Use the audit script when editing `types/*.kt`:

```sh
python3 scripts/audit_value_type_wrappers.py --api extension_api.json
```

The audit is report-only by default, and `--strict` is wired into local CI.
Reviewed local scalar formulas are allowlisted in the script; any newly added
Godot-named value helper that does not call the builtin should be treated as
suspicious until it is either bound to Godot's builtin or deliberately added to
the reviewed list with focused parity evidence.

For scalar Godot `float` method arguments, keep using the separate ABI guard:

```sh
python3 scripts/audit_scalar_float_abi.py
```

Godot's scalar `float` ptrcall slot is 64-bit (`JAVA_DOUBLE`) even when
single-precision `real_t` value components are `Float`.

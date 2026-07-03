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

- **Non-virtual method skips (~64 after the Dictionary→Dictionary family):** the
  large majority are *intentional*, not gaps —
  - **~49 root `Object` methods** (`call`/`get`/`set`/`connect`/`emit_signal`/…)
    are deliberately routed through the hand-shaped `GodotObject` policy, not
    regenerated per subclass. These are the escape hatches; keep them on
    `GodotObject`.
  - **2 `RefCounted` lifetime methods** (`reference`/`init_ref`) are
    ownership-sensitive and hand-shaped.
  - The rest are **wide / exotic ABI shapes** — RenderingDevice raytracing
    (`blas_create`, `tlas_build`, `raytracing_pipeline_create`),
    `TypedObjectArray` blit combos, `Signal`→Object, wide RID signatures. Each is
    a large per-shape audit (JVM actual + iOS C-shim + width self-test); defer
    with the report entry as the reason until a workflow needs it.
  - The one clean low-ABI slice was **`Dictionary`→`Dictionary`** (`resolve`,
    `rename` on `GDScriptTextDocument`), landed via the
    `ptrcallWithDictionaryArgRetDictionary` helper. It reuses the existing
    Dictionary-arg init + Dictionary-return read paths, so it is desktop+Android
    only and cleanly iOS-skipped (`Dictionary` is not in `IOS_ARG_KINDS` /
    `IOS_RET_KOTLIN` — no C-shim work, no device gate).
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

## KDoc Maintenance

Public Godot-backed wrappers and builtin value types carry generated KDoc
imported from Godot's `doc/classes/*.xml` files. The generated blocks include a
`Generated from Godot docs:` marker so they can be refreshed safely instead of
hand-edited.

**Docs version pin:** wrapper KDoc is synced from the **Godot 4.7-stable**
`doc/classes` (commit `5b4e0cb0fd`), matching the shipped runtime baseline. Do
not sync from a `-rc` / `latest` tree — a mismatched docs tree reintroduces
version skew. Bump this pin when the Godot runtime baseline moves, and re-run the
full refresh.

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

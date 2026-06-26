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

Promoted generated wrappers are intentionally allowlisted. The generator can
emit selected classes with `--emit-class`, and
`scripts/check_wrapper_generator.py` compares allowlisted promoted source files
against freshly generated output so manual edits and generator drift fail
loudly. Adopted classes with skipped methods are only accepted when every skip
is a Godot virtual callback that belongs to the future override-registration
design rather than the public ptrcall wrapper surface.

Engine-wide `MethodName`, `PropertyName`, and `SignalName` constants are
generated from `extension_api.json` separately from the class wrapper drafts:

```sh
python3 scripts/generate_name_constants.py
```

`scripts/check_wrapper_generator.py` runs the same generator in `--check` mode,
so a Godot API refresh fails loudly if the committed name constants are stale.

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

## KDoc Maintenance

Public Godot-backed wrappers and builtin value types carry generated KDoc
imported from Godot's `doc/classes/*.xml` files. The generated blocks include a
`Generated from Godot docs:` marker so they can be refreshed safely instead of
hand-edited.

To check whether KDoc is current against a local Godot source tree:

```sh
python3 scripts/sync_kdoc_from_godot_docs.py --check
```

To refresh it:

```sh
python3 scripts/sync_kdoc_from_godot_docs.py --write
```

The script uses `$GODOT_DOCS` when set, otherwise it defaults to
`godot/doc/classes` relative to the current working directory. Use
`--godot-docs /path/to/godot/doc/classes` when syncing against a different
Godot checkout. Use `--classes Node,Node3D,Tween` for a focused refresh while
working on one wrapper slice, or `--scope types --classes Vector3` when
working on builtin values.

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

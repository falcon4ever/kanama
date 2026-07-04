# Wrapper Coverage Progress Tracker

Current execution state for the wrapper generator and iOS coverage work. The original detailed session
logs were intentionally condensed; keep raw task logs, private device IDs, signing teams, and local
commands outside this public repo.

## Status Summary

| Area | Status | Notes |
|---|---|---|
| Desktop/Android shape gaps | Done for the 2026-06 pass | Scalar combos, container returns, Variant/RID returns, and callable-argument design landed. |
| iOS audited type set | Done for demo/runtime use | POD/value types, strings, NodePath/StringName, typed arrays, Variant scalar returns, typed object arrays, Projection/Vector4/Plane, and generic Array cases were wired with matrix/ObjectCalls coverage. |
| iOS script model | Done | iOS consumes the shared KSP model; the regex parser was deleted. |
| iOS stubs/sugar | Done | 0 STUB / 0 SUGAR; remaining HANDWRITTEN entries are platform/runtime glue. |
| Virtual methods | Done for audited return set incl. String + PackedStringArray | `@OverrideVirtual` works across desktop/Android/iOS; iOS value returns cover the audited POD set plus String and PackedStringArray (task 13). Other Packed* arrays and Variant virtual returns remain deferred (not yet in the audited return type set). |
| iOS demo breadth | Done for Android-enabled public demos | See [ios-demo-port-tracker.md](./ios-demo-port-tracker.md). |
| Stable mobile support | Not done | See [ios-backend-roadmap.md](./ios-backend-roadmap.md). |

## Phase State

### Phase 1 — Desktop/Android Shape Gaps

| Task | Status |
|---|---|
| 1.1 Scalar-combo `CALL_SHAPES` | done |
| 1.2 Container returns | done |
| 1.3 Variant / RID returns | done |
| 1.4 Callable argument design | done |
| 1.4 Callable/vararg cross-platform impl | done (desktop/Android shipped; iOS Callable-args via PT_CALLABLE ptrcall + iOS varargs via callWithVariantArgs — self-test rows added, device validation pending). Callable-as-Variant-arg (1 method) + the Kotlin-lambda→Callable path stay deferred per design Decision 4 |

### Phase 2 — iOS Audited Type Widening

| Task | Status |
|---|---|
| 2.1 Vector2i / Vector3i | done |
| 2.2 Color / Rect2 returns | done |
| 2.3 String/StringName returns | done |
| 2.4 String / NodePath args | done |
| 2.5 Transform3D / Basis / RID / Quaternion / AABB | done |
| 2.6 `@ScriptProperty` value types | done |
| 2.7 Variant / typed-array / generic-array long tail | done for emitted demo/runtime closure |

### Phase 3 — KSP Script Model

| Task | Status |
|---|---|
| 3.1 Platform-neutral script model | done |
| 3.2 iOS consumes KSP model; regex parser removed | done |
| 3.3 Generated per-signature trampolines | done |
| 3.4 Lifecycle/input/RPC annotations | done; RPC config delivered on desktop/Android and iOS |
| 3.5 Non-object signal payloads | done |

### Phase 4 — Retire Hand-Written iOS Surfaces

| Task | Status |
|---|---|
| 4.1 Variant Object dispatch and signal close paths | done |
| 4.2 Generator custom sections | done |
| 4.3 `commonMain` + `expect/actual ObjectCalls` | convergence landed via drift-gate (task 21): all three wrapper trees re-adopted from the honest generator and locked by `check_full_drift_gate` (committed == fresh regen per platform). The physical shared-module move (`:kanama-common-api`, `expect/actual ObjectCalls`) remains a staged follow-up per the commonMain design; the drift-gate already makes cross-platform drift structurally impossible. |
| 4.4 iOS `GodotReal` centralization | todo; low priority while single-precision is the target |
| 4.5 Review/shrink hand-written iOS sites | done (2026-07 pass, task 10). 14 -> 11 HANDWRITTEN: Time/InputMap/PhysicsServer3D retired to generated wrappers (08/09 shapes made them clean supersets); remaining 11 are platform/runtime glue or documented permanent exceptions (Engine get_main_loop -> MainLoop non-wrapper, ProjectSettings Variant->Double coercion, ResourceLoader typed loaders). Revisit if MainLoop becomes a wrapper or generator custom sections (task 11) can host the coercion/typed-load sugar. |

### Phase 5 — Virtual Methods

| Task | Status |
|---|---|
| 5.1 Design and signature table | done |
| 5.2 JVM implementation | done |
| 5.3 iOS void and audited value-returning virtuals | done |
| 5.3c iOS String (first non-POD) virtual return | done (task 13) — variable-length destroy-after-read via `kanama_ios_pt_return_to_variant`; device self-test rows green |
| 5.3d iOS PackedStringArray virtual return | done (task 13) — `List<String>` marshalled as a length-prefixed blob rebuilt into a Godot PackedStringArray (`kanama_ios_build_packed_string_array_from_blob`), destroy-after-read; device self-test rows green |
| 5.4 Coverage accounting | done |
| 5.5 Other Packed*/Variant virtual returns | deferred — each needs new `TypeMapping` entries + JVM/iOS marshalling; land as their own audited shape families |

## Remaining Work

These are the active forward-looking items. Keep the strategic ordering in
[ios-backend-roadmap.md](./ios-backend-roadmap.md) authoritative.

1. **Stable iOS export workflow**: user-facing `docs/exporting/ios.md`, fresh-project path, and
   physical-device validation gate.
2. **Android hardening**: current-device Godot 4.7 stable smoke plus R8-minified APK gate.
3. **`commonMain` wrapper unification**: migrate toward shared generated wrappers with
   `expect/actual ObjectCalls`.
4. **Long-tail shapes**: conservative container policies and non-POD virtual returns. (Callable-arg +
   vararg landed cross-platform — object+method Callable form on desktop/Android/iOS; the
   Kotlin-lambda→Callable custom-callable path and Callable-as-Variant-arg remain deferred.)
5. **Generator policy cleanup**: explicit class-collision handling, subclass override generation, and
   tighter fixture coverage for custom member/companion sections.
6. **Optional iOS polish**: `GodotReal` centralization.

## Required Report Refreshes

Run and commit refreshed reports whenever generator coverage or support claims change:

```sh
python3 scripts/ios_handwritten_report.py --markdown docs/internals/reference/ios-backend-handwritten.md
python3 scripts/api_wrapper_generator_report.py --markdown docs/contributing/wrapper-generator-report.md
python3 scripts/api_wrapper_coverage.py --markdown docs/contributing/api-coverage.md
```

Use private handoff notes for exact local device commands and signing values.

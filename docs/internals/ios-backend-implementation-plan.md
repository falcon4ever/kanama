# iOS Backend Scalability — Implementation Plan & Tracker

This is the **living, checkable working copy** for the iOS backend pivot from a
hand-written stub API to a generated Godot API (the desktop/Android model). Check
tasks off and fill **Done by** as work completes. Companion docs:
[architecture](./ios-backend-architecture.md) · [backlog](./ios-backend-backlog.md).
Session-handoff notes live in the private `kanama-ios-support.md` (outside the repo).

## Goal

iOS gets the full Godot API the same way desktop does — generated from
`extension_api.json` by `scripts/generate_api_wrapper.py`, with wrappers calling a
runtime abstraction `ObjectCalls` backed by the C shim — on a fast cached-bind /
ptrcall path. This ends the per-method hand-marshalling grind and the silent-no-op
bug class. The Kenney **3D platformer** is the first validation target.

## Status legend

`☐` not started · `▶` in progress · `✅` done. **Owner**: S = Sonnet (mechanical /
pattern-following), O = Opus (contract design + risky marshalling / crash debugging).

## Execution & orchestration

- Each task is dispatched to a subagent on the model matching **Owner** (Agent tool
  `model`: `sonnet`/`opus`). An Opus orchestrator coordinates, runs [O] reviews, and
  fills **Done by**.
- Subagents start cold — every task prompt must point at this doc, the
  [architecture doc](./ios-backend-architecture.md), the
  [backlog](./ios-backend-backlog.md), and `kanama-ios-support.md`.
- **Serialize** device/build tasks (`installIosAddon` / `xcodebuild` / `devicectl`):
  one device, one Xcode export dir. Generator/codegen/doc tasks may parallelize.
- Escalate S→O if a mechanical agent churns without progress (esp. crash/marshalling).

## Cross-cutting guardrails (apply to every marshalling/codegen task)

- **Runtime (debug builds):** `kanama_ios_check_call_error` and
  `kanama_ios_check_variant_arg` must log **zero** hits; `connect` reports its real
  return Error. Any nonzero = task fails.
- **Generated code:** `scripts/check_wrapper_generator.py` (extended with iOS
  fixtures) must pass; the generator `--skip-report` is reviewed (no silently-wrong
  emission).
- **No silent stubs:** every generated API method must call through `ObjectCalls`; a
  grep check confirms no API method has an empty body or bare `return false/0.0/null`.
- **Every phase** ends with an on-device run on the iPhone 15 Pro (0 SIGSEGV baseline).

---

## Phase 0 — Docs & bootstrap

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T0.0 Write this tracker (`ios-backend-implementation-plan.md`) | O | ✅ | Opus orchestrator 2026-06-09 | File exists, mirrors plan, has all columns | — |
| T0.1 Write `ios-backend-architecture.md` | O→S | ▶ | Opus orchestrator 2026-06-09 (bootstrap) | Covers arch diagram, runtime/generated split, perf rules, no-silent-stubs; links resolve | [O] review |
| T0.2 Write `ios-backend-backlog.md` (migrate from `kanama-ios-support.md`) | O→S | ▶ | Opus orchestrator 2026-06-09 (bootstrap) | Every handoff backlog item present (diff check); nothing dropped | — |

> Note: T0.0–T0.2 are authored by the Opus orchestrator (not delegated) because they
> are the cold-start bootstrap for all later subagents and need the full session
> context.

## Phase 1 — `ObjectCalls` contract

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T1.1 Enumerate the `ObjectCalls.*` helper set the generator emits; group by marshalling pattern | S | ✅ | Sonnet subagent 2026-06-09 | Survey [`ios-objectcalls-helper-survey.md`](./ios-objectcalls-helper-survey.md); grep-verified complete for platformer classes (zero unlisted) | — |
| T1.2 Design iOS `ObjectCalls` contract + generic C ptrcall dispatch; confirm sharing strategy ((B) iosMain copies first, toward (A) commonMain expect/actual) | O | ✅ | Opus orchestrator 2026-06-09 | Contract in architecture doc §"Contract: generic ptrcall dispatch (iOS ObjectCalls)"; compile-smoke folded into T2.1 | Every call via cached `MethodBind` + generic typed ptrcall; Variant only where ptrcall can't express |

## Phase 2 — iOS `ObjectCalls` helper surface

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T2.1 Design + implement the generic C-shim ptrcall/marshalling dispatch (arg cells, return reading, Variant fallback) | O | ✅ | Opus orchestrator 2026-06-09 | **Type-coverage matrix self-test 11/11 PASS on iPhone 15 Pro AND iPhone 12** (bool, int32, int64, scalar-float, Vector2/3, Color, StringName-arg, Object-arg+multiarg+Object-ret); 0 SIGSEGV. Matrix kept as a debug-gated guardrail (`KANAMA_IOS_DEBUG_VARIANT_CHECKS`). **Matrix caught a real bug:** scalar `float` must marshal as 8-byte double (PtrToArg<float>=convert<float,double>) — fixed; also fixes inaudible audio | matrix is the guardrail; 0 SIGSEGV |
| T2.2 Add typed arg/return marshallers per the T2.1 pattern; wire/generate `ObjectCalls` helper bodies | O (pattern) → S (long tail) | ◐ | Opus orchestrator 2026-06-09 (pattern+core) | **iOS `ObjectCalls` cinterop pattern established + validated on iPhone 12: Kotlin probe 4/4** (Vector3 float32 layout, bool, int64-ret, scalar-float-as-double via `ptrcallWithDoubleArg`). Core helpers written (no-arg rets, bool/int/long/double/vector2/3/object args). Long-tail helper shapes + StringName/Color/Transform args remain — to be emitted by the T3.1 generator | debug-gated Kotlin probe kept as guardrail |

## Phase 3 — Generator target + platformer

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T3.1 Add iOS emission target to `scripts/generate_api_wrapper.py`; extend fixtures/check | O (+S fixtures) | ✅ | Opus orchestrator 2026-06-10 (device iPhone 12) | `--ios-emit-class`/`--ios-objectcalls`/`--ios-skip-report` added; iOS-audited guardrail (`IOS_AUDIT_ONLY`) skips un-audited shapes; ObjectCalls helper bodies generated as extensions over the generic `kanama_ios_godot_ptrcall`. Generated CharacterBody3D ancestry (23 helpers) into iosMain; **device round-trip iPhone 12: generated `ptrcallWithVector3ArgRetVector3` g=(10,20,30), OBJECTCALLS SELFTEST 6/6, PTRCALL MATRIX 11/11, 0 guardrail hits, Match3 60fps.** Node3D regression fixture added to `check_wrapper_generator.py` (harness green; desktop path unchanged) | scalar-float→double + real_t→float32 mapping correct; matrix + ObjectCalls probe clean ✅ |
| T3.2 Generate platformer classes (Node3D, CharacterBody3D, Camera3D, AnimationPlayer, Area3D, CollisionShape3D, GPUParticles3D + bases) | S | ✅ | Subsumed by Phase 4 (`48d436f`) | Done as part of the Phase-4 3D facade swap — all listed classes + bases generated, `installIosAddon` compiles clean, device-validated. | no needed method dropped |
| T3.3 Wire `Input`/InputMap (`get_axis`/`get_vector`/`is_action_just_pressed`) | O | ✅ | Opus 2026-06-10 (`ba0143c`, device iPhone 15 Pro) | **Marshalling done + device-validated:** added `getSingleton` (C shim + ObjectCalls); `Input.getAxis`/`isActionJustPressed` now real (were 0.0/false stubs) via generated StringName helpers. SELFTEST 9/9 incl. input-singleton/get_axis/is_action_just_pressed, MATRIX 11/11, 0 hits. (`get_vector` not needed by the demo; add when required.) **Nonzero-under-live-input + player movement = T3.4** (needs full demo + interactive input). | guardrail clean ✅ |
| T3.4 Deploy to iPhone 15 Pro; verify movement/jump/animation + coin pickup (Area3D signals via custom-Callable) | S (→O on bug) | ✅ | Opus 2026-06-10 (`d1cd42c`, full demo, user-verified) | **Full Kenney 3D platformer runs on iPhone 15 Pro** (deployed via `kanama-demos/scripts/ios_device_run.sh`, Godot 4.7 beta5, built-in VirtualJoystick). User-verified: 3D renders, joystick movement + jump (real Input.getAxis/moveAndSlide), animation smooth, coins collect, fall→respawn. 0 guardrail/crash. Fixed 3 device-found bugs: Vector3.rotated handedness (movement dir), getCurrentAnimation stub (jitter/low-char/break-hitch), SceneTree.reloadCurrentScene/quit stubs (respawn). **fps measurement deferred to Phase 5.** | guardrail clean ✅ |

## Phase 4 — Migrate + regress

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T4.1 Replace hand-written classes in `IosGodotApi.kt` with generated output; delete dead stubs | S (+O review) | ✅ | Opus+Sonnet 2026-06-10 | **DONE** — 3D slice (`48d436f`) + 2D slice (`1171190`). All Godot-API wrapper classes generated; hand `IosGodotApi.kt` shrinks to bespoke runtime (GodotObject base, Input/GD/Mathf/ResourceLoader, GodotSignal/Callable, Tween family, AudioStreamPlayer, SceneTree). `velocity`/`moveAndSlide`/`isOnFloor` + 2D wrappers now real ptrcalls. Generator hardened (no @JvmStatic on iOS, closed-island `IOS_EMIT_CLASSES`, custom-section suppression). No generated API method is a silent stub (grep-checked); kept bespoke = text/modulate/getVisibleRect/getViewportRect/getCurrentAnimation (Color/String/Rect2 not audited) + Tween/Audio (Variant/runtime). Both demos compile; harness green. | no-silent-stubs ✅ |
| T4.2 Re-run Match3 on device (2D regression) | S | ✅ | Opus 2026-06-10 | **iPhone 15 Pro:** Match3 on generated 2D wrappers — 64 tiles, 60fps, SELFTEST 6/6, MATRIX 11/11, 0 guardrail hits; Platformer3d scene clean. | guardrail clean ✅ |

## Phase 5 — 3D performance review

The 60fps baseline was **Match3 (2D, sprite-based)**. 3D is a separate, unverified
question. Measure two distinct things and keep them apart: **(a) Godot rendering/GPU**
(Metal/MoltenVK — mostly Godot's concern) and **(b) the Kanama scripting/binding
overhead** (per-frame C↔Kotlin/Native crossings in `_physics_process`:
`move_and_slide` + velocity + `is_on_floor` + input + animation, per character).
Test the **iPhone 12** (honest floor) and **iPhone 15 Pro**.

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T5.1 Measure platformer frame time + per-frame script/binding time (instrument `_physics_process`, e.g. monotonic timestamps around the script call path) on iPhone 12 **and** 15 Pro | S | ☐ | | Frame time + script-time captured and recorded for both devices over a representative play window | Measure with per-frame debug logging OFF (fprintf/fflush per frame skews timing — a known past pitfall); consistent scene + device not thermally throttled |
| T5.2 Compare vs targets; if over budget apply the architecture-doc perf guidance (cache `MethodBind`, avoid per-call `StringName` alloc, prefer ptrcall over Variant `call`, fewer boundary crossings) and re-measure | O | ☐ | | **Targets:** sustained 60fps on 15 Pro and a documented acceptable framerate on iPhone 12; binding/script time a small fraction of the 16.6ms frame budget. Findings written to the backlog/architecture doc | re-measure after each optimization; no regression to Match3 |

---

## Done already (this session, pre-pivot — for reference)

| Item | Commit | Verified |
|---|---|---|
| Match3 SIGSEGV fix (connect raw-Callable-as-Variant) + guardrails | `4f76efe` | iPhone 12 + 15 Pro |
| Script `@Signal` registration (`_has_script_signal` + plumbing) | `4f76efe` | swiping works |
| AudioStreamPlayer playback (create + binds) | `9afcbaa` | machinery verified (not audible — backlog) |
| Lambda/bound custom Callable (`connect`/`connectObject`) | `e1c0529` | 138 dispatches, no leak |
| `GodotSignal.await()` | `eff2206` | compiles, no regression |

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
| T2.1 Design + implement the generic C-shim ptrcall/marshalling dispatch (arg cells, return reading, Variant fallback) | O | ☐ | | On-device probe calls representative shapes (no-arg→bool/double/object, with-double/object/vector3) and asserts round-trip results | `check_call_error`/`check_variant_arg` clean |
| T2.2 Add typed arg/return marshallers per the T2.1 pattern; wire/generate `ObjectCalls` helper bodies | S | ☐ | | Per-marshaller set→get round-trip probe asserts equality on device | guardrail logs clean |

## Phase 3 — Generator target + platformer

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T3.1 Add iOS emission target to `scripts/generate_api_wrapper.py`; extend fixtures/check | O (+S fixtures) | ☐ | | `check_wrapper_generator.py` passes with iOS fixtures; sample class diffs clean vs fixture | skip-report reviewed |
| T3.2 Generate platformer classes (Node3D, CharacterBody3D, Camera3D, AnimationPlayer, Area3D, CollisionShape3D, GPUParticles3D + bases) | S | ☐ | | `installIosAddon` compiles clean; generated method set matches `extension_api.json` per class | no needed method dropped |
| T3.3 Wire `Input`/InputMap (`get_axis`/`get_vector`/`is_action_just_pressed`) | O | ☐ | | On-device `get_axis` nonzero under active input; player responds; temp log then removed | guardrail clean |
| T3.4 Deploy to iPhone 15 Pro; verify movement/jump/animation + coin pickup (Area3D signals via custom-Callable) | S (→O on bug) | ☐ | | Device checklist: player moves/jumps/animates, coins collect, 0 SIGSEGV / 0 connect / 0 VARIANT-mismatch | guardrail clean |

## Phase 4 — Migrate + regress

| Task | Owner | Status | Done by | Validate | Guardrail |
|---|---|---|---|---|---|
| T4.1 Replace hand-written classes in `IosGodotApi.kt` with generated output; delete dead stubs | S (+O review) | ☐ | | Grep finds no empty-body / `return false\|0.0\|null` stub methods left; `installIosAddon` compiles clean | no-silent-stubs |
| T4.2 Re-run Match3 on device (2D regression) | S | ☐ | | Matches known-good baseline: tiles spawn, swipe swaps, 0 SIGSEGV / 0 connect failures | guardrail clean |

---

## Done already (this session, pre-pivot — for reference)

| Item | Commit | Verified |
|---|---|---|
| Match3 SIGSEGV fix (connect raw-Callable-as-Variant) + guardrails | `4f76efe` | iPhone 12 + 15 Pro |
| Script `@Signal` registration (`_has_script_signal` + plumbing) | `4f76efe` | swiping works |
| AudioStreamPlayer playback (create + binds) | `9afcbaa` | machinery verified (not audible — backlog) |
| Lambda/bound custom Callable (`connect`/`connectObject`) | `e1c0529` | 138 dispatches, no leak |
| `GodotSignal.await()` | `eff2206` | compiles, no regression |

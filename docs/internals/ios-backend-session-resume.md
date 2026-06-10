# iOS Backend — Session Resume Note

Short-lived handoff for picking the work back up in a fresh session. The durable docs
are [implementation-plan](./ios-backend-implementation-plan.md) (living tracker),
[architecture](./ios-backend-architecture.md) (contract + width table), and
[backlog](./ios-backend-backlog.md). This file just says **exactly where we stopped
and what to do next** so a cold agent doesn't re-derive context.

## Repo state at handoff (2026-06-10)

- Repo: `/Users/lmuller/dev/kanama`
- Branch: `spike/ios-kotlin-native-backend`
- Last commit: `e009829` — "docs(ios): complete the authoritative ptrcall type-width
  audit + generator guardrail"
- Working tree: clean.
- Commit discipline: commit incrementally; never modify Kotlin demo code
  (`Main.kt`, `Tile.kt`, anything in `kanama-demos/`). iOS fixes go **only** in
  `ios/bootstrap/kanama_ios_shim.c`, `ios/include/kanama_ios.h`,
  `ios-runtime/src/iosMain/kotlin/...`, or `ios-runtime/build.gradle.kts`.

## Done (verified on device, iPhone 12 + 15 Pro)

- Match3 SIGSEGV fix, `@Signal` registration (swiping), lambda/bound custom Callables,
  `await`, audio chain (silence = Godot engine AVAudioSession issue + device silent
  mode, NOT Kanama — backlog).
- **T2.1 ✅** generic C ptrcall dispatch `kanama_ios_godot_ptrcall(...)`; self-test
  matrix 11/11 on both devices; caught + fixed scalar-float width (must marshal as
  8-byte double, `PtrToArg<float>=convert<float,double>`).
- **T2.2 ◐** iOS `ObjectCalls` cinterop helper pattern established + device-validated
  (Kotlin probe). Core helpers written. Long-tail shapes deferred to generator (T3.1).
- **Authoritative type-width audit ✅** — complete width table + conservative-generator
  guardrail in architecture doc. Only scalars widen (float→double 8B, int→int64 8B,
  bool 1B); structs/containers are native bytes (Vector real_t = float32, int structs
  = int32, Color = 4×float32). Generator must emit only audited types, skip the rest.

## NEXT TASK — T3.1 (Owner: Opus) — generator emission target

**Goal:** add an iOS emission target to `scripts/generate_api_wrapper.py` so iOS gets
generated Godot API wrappers + matching `ObjectCalls` helper bodies, instead of
hand-stubbing. The risky type-mapping foundation is DONE; T3.1 is now the mechanical
application of the audited width table.

Approach (recorded in architecture doc §"T3.1 generator approach"):
1. During generation, collect a registry: `shape.function -> (arg godot-types,
   return godot-type)` from the existing `CallShape` taxonomy
   (`candidate_for(method, object_types)` in the generator).
2. Reuse the platform-agnostic generated wrapper classes (they call
   `ObjectCalls.<helper>(bind, receiver, args)`) — emit copies into `iosMain`
   (strategy (B): iosMain copies first, design toward (A) commonMain expect/actual).
3. Emit iOS `ObjectCalls` helper bodies for the used shape set, applying the
   authoritative width table: scalar float→double/8B, scalar int→int64/8B,
   Vector real_t→float32, int structs→int32, Color→4×float32, Object/RID→8B ptr,
   StringName-family constructed C-side, opaque types via Variant fallback.
4. **Conservative guardrail:** emit only audited types; skip un-audited shapes (don't
   silently emit wrong marshalling). Mirror desktop's `--skip-report`.
5. Validate: generate `CharacterBody3D`, compile via `installIosAddon`, round-trip on
   device; matrix + ObjectCalls probe stay clean.

Key generator internals to reuse (don't reinvent):
- `scripts/generate_api_wrapper.py` (~1470 lines): `candidate_for(method,
  object_types)` returns `CallShape(function, kotlin_return, default)`;
  `render_method` emits `ObjectCalls.{shape.function}(bind, receiver, args)`.
- Imports `ApiMethod`/`ApiClass` from `wrapper_model`, `CallShape` from
  `api_wrapper_candidates`.
- Regression harness: `scripts/check_wrapper_generator.py` +
  `scripts/fixtures/wrapper_generator/` — extend with an iOS fixture.

## After T3.1

- **T3.2 [S]** generate platformer classes (Node3D, CharacterBody3D, Camera3D,
  AnimationPlayer, Area3D, CollisionShape3D, GPUParticles3D + bases).
- **T3.3 [O]** wire `Input`/InputMap (`get_axis`/`get_vector`/`is_action_just_pressed`).
- **T3.4 [S]** deploy + validate platformer on device.
- **Phase 4 [S]** migrate `IosGodotApi.kt` to generated, delete stubs, Match3 regression.
- **Phase 5** 3D perf review on iPhone 12 + 15 Pro.

## Build / device cheatsheet

- Build (cwd resets between Bash calls — always `cd` first):
  `cd /Users/lmuller/dev/kanama && ./gradlew installIosAddon -PkanamaIosProjectDir=...`
- Device install needs `xcodebuild -destination 'id=<udid>' -allowProvisioningUpdates`
  (else error 3002 for an unregistered device). Device must be **unlocked** to launch.
- iPhone 15 Pro id: `8AABADCB-7A0E-5A94-91B8-7B7629DBACC4`.
- Guardrails (debug builds, `KANAMA_IOS_DEBUG_VARIANT_CHECKS=1`):
  `kanama_ios_check_call_error` + `kanama_ios_check_variant_arg` must log **zero**
  hits; ptrcall self-test matrix + ObjectCalls probe run at scene-init. Any nonzero
  or non-11/11 = task fails.

## Subagent orchestration

[S]→`sonnet` subagent, [O]→`opus` subagent (Agent tool `model` param). Opus
orchestrates, runs [O] reviews, fills the tracker **Done by** column. Serialize all
device/build tasks (one device, one Xcode export dir). Each subagent prompt must point
at the three durable docs + the private `kanama-ios-support.md` handoff log.

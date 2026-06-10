# iOS Backend — Session Resume Note

Short-lived handoff for picking the work back up in a fresh session. The durable docs
are [implementation-plan](./ios-backend-implementation-plan.md) (living tracker),
[architecture](./ios-backend-architecture.md) (contract + width table), and
[backlog](./ios-backend-backlog.md). This file just says **exactly where we stopped
and what to do next** so a cold agent doesn't re-derive context.

## Repo state at handoff (2026-06-10)

- Repo: `/Users/lmuller/dev/kanama`
- Branch: `spike/ios-kotlin-native-backend`
- Last commit: `138b41b` — "ios: T3.1 — generated ObjectCalls helpers for
  CharacterBody3D, device-validated" (T3.1 done; `8977708` is the generator+fixture).
- Working tree: docs updates in progress (this note + tracker + handoff log).
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

## T3.1 ✅ DONE (Opus, device iPhone 12, 2026-06-10)

The iOS emission target is in `scripts/generate_api_wrapper.py`:
- `--ios-emit-class CLASS` renders the platform-agnostic wrapper (plus a
  `binding.runtime.*` import so generated extension helpers resolve);
  `--ios-output-dir` is the staging dir (do NOT point at the compiled facade —
  it would collide with the hand-written classes in `IosGodotApi.kt`; Phase 4 swaps).
- `--ios-objectcalls FILE` generates `ObjectCallsGenerated.kt`: one **extension on
  `object ObjectCalls`** per used `CallShape.function`, each marshalling through the
  generic `kanama_ios_godot_ptrcall` with the audited width table. Hand-written
  ObjectCalls.kt helpers are the override set (`IOS_HANDWRITTEN_HELPERS`) — not
  re-emitted.
- `--ios-skip-report FILE` + `IOS_AUDIT_ONLY`: conservative guardrail. Audited kinds
  today = `IOS_ARG_KINDS` {bool,int32/64,uint32,enum,bitfield,float,Object,Vector2/3,
  StringName} and `IOS_RET_KOTLIN` {Unit,Boolean,Int,Long,Double,Vector2/3,
  MemorySegment}. Anything else (Transform3D/Basis/Quaternion/Color/RID/NodePath/
  Vector*i/typed-arrays/varargs) is skipped, never guessed. **To widen coverage: add
  the kind to those sets AND add a width-sensitive self-test matrix row + an
  ObjectCalls probe row — only then is it "audited".**
- Regression: `check_wrapper_generator.py` locks a Node3D iOS fixture in
  `scripts/fixtures/wrapper_generator/ios/`.

Compiled `ObjectCallsGenerated.kt` (CharacterBody3D ancestry, 23 helpers) into
`ios-runtime/.../binding/runtime/`; probe extended in `ObjectCalls.kt`. Device
(iPhone 12): generated `ptrcallWithVector3ArgRetVector3` g=(10,20,30), SELFTEST 6/6,
MATRIX 11/11, 0 guardrail hits, Match3 60fps.

## ⚠️ Blocker found for T3.2/T3.4 (platformer on device)

`:ios-runtime:compileKotlinIosArm64` with the **3D-Platformer** demo's kotlin-src
FAILS — not in any T3.1 code, but in the regex-generated project registry
`build/generated/iosProjectScripts/.../KanamaIosProjectRegistry.generated.kt`:
`Unresolved reference 'Long'` and `'NodePath'`. This is the incomplete-iOS-regex-parser
backlog item (the parser doesn't import/qualify those types for the platformer
scripts). The **Match3** demo compiles + runs clean (used for T3.1 device validation).
T3.2/T3.4 must fix the platformer registry generation first (extend the
`build.gradle.kts` parser, or fully-qualify Long/NodePath in its codegen), else the
platformer won't compile regardless of the generated API wrappers.

## NEXT TASK — T3.2 [S] — generate platformer classes

- Generate platformer classes (Node3D, CharacterBody3D, Camera3D,
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
- Device ids: iPhone 15 Pro `8AABADCB-7A0E-5A94-91B8-7B7629DBACC4`,
  iPhone 12 `48DF9662-42F3-541F-9F88-7FA2AB870F86` (USB; preferred for device runs).
  `xcrun devicectl list devices` to confirm what's connected.
- **Capturing the self-test on device:** `ios_visual_smoke.sh --kanama-match3-probe`
  builds+installs+launches but on a physical device launches WITHOUT console
  streaming, so the SELFTEST/MATRIX lines don't reach its stdout log. After the smoke
  reports `OK`, relaunch with console to capture them:
  `xcrun devicectl device process launch --console --terminate-existing --device <id>
  net.multigesture.kanama.iosvisualsmoke` (streams forever — the game keeps running;
  grep its output for `SELFTEST|MATRIX`, then kill it).
- Guardrails (debug builds, `KANAMA_IOS_DEBUG_VARIANT_CHECKS=1`):
  `kanama_ios_check_call_error` + `kanama_ios_check_variant_arg` must log **zero**
  hits; ptrcall self-test matrix + ObjectCalls probe run at scene-init. Any nonzero
  or non-11/11 = task fails.

## Subagent orchestration

[S]→`sonnet` subagent, [O]→`opus` subagent (Agent tool `model` param). Opus
orchestrates, runs [O] reviews, fills the tracker **Done by** column. Serialize all
device/build tasks (one device, one Xcode export dir). Each subagent prompt must point
at the three durable docs + the private `kanama-ios-support.md` handoff log.

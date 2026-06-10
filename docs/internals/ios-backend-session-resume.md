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

## Blocker #1 — FIXED 2026-06-10 (`ec4db4b`)

The 3D-Platformer iOS runtime failed to compile: the `build.gradle.kts` registry
codegen mis-classified value types as object wrappers, emitting invalid
`net.multigesture.kanama.api.NodePath(...)` (view/target NodePath props) and
`api.Long(...)` (onCoinCollected(Long) signal handler). Fixed with `IOS_VALUE_TYPES`:
value-typed single method args with no typed bridge → UNSUPPORTED (warn+skip);
value-typed `@ScriptProperty`s that aren't settable scalars → skipped (warn, keep
Kotlin default). Both demos now compile. **Backlog (not blockers):** deliver NodePath
`@ScriptProperty` + typed value-arg signal dispatch (the coin-count `onCoinCollected`)
end-to-end — until then the platformer's NodePath props keep their defaults (scripts
recompute) and the coin counter won't update.

## Phase 4 — 3D slice DONE 2026-06-10 (`48d436f`, device iPhone 15 Pro)

Generated 3D wrappers replaced the hand stubs (Node, Node3D, VisualInstance3D,
GeometryInstance3D, CollisionObject3D, PhysicsBody3D, CharacterBody3D, Camera3D, Area3D,
CollisionShape3D, GPUParticles3D, AnimationMixer, AnimationPlayer). `velocity`/
`moveAndSlide`/`isOnFloor` are now real. Device: Match3 + Platformer3d both SELFTEST 6/6,
MATRIX 11/11, 0 guardrail hits, 60fps. Generator hardening landed (reuse for 2D slice):
- **No `@JvmStatic` on iOS** (`render_wrap_helpers`/singleton gated on `IOS_AUDIT_ONLY`)
  — K/N rejects it; `@JvmName` is fine.
- **Closed-island guardrail** `IOS_EMIT_CLASSES`: a method is skipped unless every Object
  arg/return wrapper type is also emitted (or root Object→GodotObject). So pass the FULL
  ancestry + every referenced peer type to `--ios-emit-class`, else methods silently skip.
- **Desktop CUSTOM_MEMBER_SECTIONS suppressed on iOS**.

**GOTCHAS for the 2D slice:**
- Generated `Node`/`AnimationPlayer` are **hand-augmented** (Kanama sugar as members,
  under a `// Kanama sugar` block) — mirrors desktop `Node.kt`. When you re-emit Node
  (2D slice re-passes `--ios-emit-class Node`), the generator OVERWRITES it → you must
  re-add the sugar block. The sugar: requireAs/getNodeOrNull/getAsOrNull(x2)/
  getNodeAsOrNull/createTween/getViewport/getTree/setProcessInput/setProcessUnhandledInput/
  hide/show (bodies call `IosGodot.*`, which is now `internal`). See `48d436f` Node.kt.
- `GodotObject` base lost `queueFree()`/`isInGroup()` (now Node-level generated) + gained
  `fromHandle`/`wrap` companion. Keep that.
- Demos call these on 2D types — preserve or generate: `Node2D.position/scale` (Vector2),
  `Sprite2D.texture`(Texture2D?)/`modulate`(Color)/`setTexture`, `Label.text`/`setText`,
  `Viewport.getVisibleRect():Rect2` (Rect2 NOT audited → bespoke sugar), CanvasItem
  `getViewportRect`(Rect2)/`getLocalMousePosition`(Vector2). Color/Rect2 returns are NOT
  in the audited set → those methods generate-skip; keep bespoke (they already work via
  `IosGodot.*`). `PackedScene.instantiate`, `Tween` family stay bespoke (Variant).
- Workflow: generate the 2D set to `/tmp`, copy PRISTINE files in, hand-augment the ones
  needing sugar, delete the matching hand classes from IosGodotApi.kt, compile BOTH demos,
  Match3 device-regress. Then grep no-silent-stubs.

## Phase 4 — DONE 2026-06-10 (3D `48d436f` + 2D `1171190`, device iPhone 15 Pro)

Full facade migration complete: every Godot-API wrapper class in the iOS api package is
now GENERATED; `IosGodotApi.kt` holds only bespoke runtime (GodotObject base, Input, GD,
Mathf, ResourceLoader, MainThread/KanamaScope, GodotSignal/SignalConnection/
IosCallableRegistry, Tween/Tweener/PropertyTweener, AudioStreamPlayer, SceneTree, the
`IosGodot` cinterop object). `ObjectCallsGenerated.kt` = 76 helper extensions (3D+2D
union). Device-validated: Match3 (2D, generated Control/Node2D/Sprite2D) 64 tiles 60fps +
Platformer3d, both SELFTEST 6/6 / MATRIX 11/11 / 0 guardrail hits.

Hand-augmented generated files (carry a `// Kanama sugar` member block — re-add on
regenerate): Node, AnimationPlayer, CanvasItem, Label, Viewport.

## T3.3 DONE 2026-06-10 (`ba0143c`, device iPhone 15 Pro)

`Input.getAxis`/`isActionJustPressed` were silent stubs (0.0/false) — now real. Added
`kanama_ios_godot_get_singleton` (C shim export) + `ObjectCalls.getSingleton`; bespoke
`Input` resolves the singleton + calls the generated StringName helpers
(`ptrcallWithTwoStringNameArgsRetDouble` get_axis 1958752504,
`ptrcallWithStringNameAndBoolArgRetBool` is_action_just_pressed 1558498928 — added Input
to the `--ios-emit-class` union so those helpers emit; Input stays bespoke, Input.kt not
written). `IosGodotApi.kt` now imports `binding.runtime.*` (extension helpers).
SELFTEST 9/9 (added input-singleton/get_axis/is_action_just_pressed), MATRIX 11/11, 0 hits.
`get_vector` not needed by the demo (add when required). setCustomMouseCursor still a
no-op stub (cosmetic, Match3-only).

GOTCHA (device): the smoke (`ios_visual_smoke.sh`) DOES install (`devicectl device
install app` then launch), but a manual `--console` relaunch can RACE ahead of the
smoke's install → "not installed"/stale app. If the console shows the wrong probe or
"not installed", reinstall the smoke's built `.app`
(`$WORKDIR/derived/Build/Products/Debug-iphoneos/KanamaIosVisualSmoke.app`) then
`devicectl device process launch --console`.

## T3.4 DONE 2026-06-10 (`d1cd42c`, full platformer user-verified on iPhone 15 Pro)

Full Kenney 3D platformer deployed via `kanama-demos/scripts/ios_device_run.sh`
(Godot 4.7 beta5, built-in VirtualJoystick; env `KANAMA_IOS_DEVICE`/`KANAMA_IOS_TEAM`,
args `<godot> <demoDir> net.multigesture.kanama.platformer3d Platformer3D`). User-verified:
3D renders, joystick movement + jump (real `Input.getAxis`/`moveAndSlide`), animation
smooth, coins collect, fall→respawn. 0 guardrail/crash. **3 device-found bugs fixed** (all
silent-stub / iOS-impl bugs):
- **Vector3.rotated** handedness (off-diagonal terms negated → rotated by -angle) →
  mirrored camera-relative movement. Replaced with Rodrigues form.
- **AnimationPlayer.getCurrentAnimation** `""` stub → demo re-fired `play()` every frame
  (leg jitter + character looked low + block-break hitch). Now caches last `play()` name.
- **SceneTree.reloadCurrentScene/quit** empty stubs → wired to real ptrcalls (respawn).

Deploy gotchas: `ios_device_run.sh` exit/notification lags (use a watcher loop for PASS);
a manual `--console` relaunch can race the install; devicectl `--console` only captures
~1s before detaching (throttle any per-frame diagnostic to <30-frame interval).

Known remaining (backlog, non-blocking): real String-return ptrcall (getCurrentAnimation
is a cache workaround; also unblocks Label.text get etc.), NodePath `@ScriptProperty`
delivery (view/target use defaults), coin-counter `onCoinCollected(Long)` value-arg
signal dispatch, `Input.setCustomMouseCursor`.

## NEXT TASK — Phase 5: 3D perf review (iPhone 12 + 15 Pro)

Measure (a) Godot rendering/GPU vs (b) Kanama scripting/binding overhead separately, on
BOTH devices, per-frame in `_physics_process` (move_and_slide + velocity + is_on_floor +
input + animation per character). Get the actual fps (the user asked — deferred here).
Targets: sustained 60fps on 15 Pro + a documented floor on iPhone 12; binding/script time
a small fraction of the 16.6ms budget. Measure with per-frame debug logging OFF (it skews
timing). If over budget, apply the architecture-doc perf guidance (cache MethodBind — done;
avoid per-call StringName alloc; prefer ptrcall over Variant; fewer crossings) + the
per-call buffer-alloc reduction (pool/preallocate the memScoped arg/ret buffers — the
known FFM-vs-iOS gap). See implementation-plan Phase 5.

## (historical) earlier T3.4 brief

Build/install the FULL Kenney 3D platformer (not the smoke probe) on the iPhone 15 Pro
and verify on-device with live input (USER must drive input — agent can't simulate):
player moves/jumps (CharacterBody3D.moveAndSlide + Input.getAxis now real), animates
(AnimationPlayer), camera follows, coins collect (Area3D body_entered via custom-
Callable). Watch: `get_axis` nonzero under input, 0 SIGSEGV / 0 connect / 0 VARIANT
mismatch. Known gaps that may surface (backlog, not blockers): NodePath `@ScriptProperty`
(view/target keep defaults), coin counter (`onCoinCollected(Long)` value-arg signal
dispatch not wired). Then **Phase 5** perf (iPhone 12 + 15 Pro).

Now that the 3D classes are real, the platformer needs polling input to actually move:
`Input.getVector`/`get_axis`/`is_action_pressed`/`is_action_just_pressed` (action-based,
not `@OnInput`). Add a C-shim `Input` singleton access + expose in the bespoke `Input`
object in IosGodotApi.kt. Then **T3.4 [S]**: deploy the FULL platformer demo (not the
smoke probe) on device — verify movement/jump/animation + coin pickup. (The platformer
*runtime* compiles; full gameplay needs T3.3 + the value-arg signal dispatch for the coin
counter — see backlog.) Then **Phase 5** 3D perf review (iPhone 12 + 15 Pro).

## (historical) earlier T3.2/T3.4 ordering

## (historical) Phase 4 plan (reordered before T3.2, user decision 2026-06-10)

Migrate the hand-written `IosGodotApi.kt` Godot-API wrapper classes to GENERATED
wrappers (via the T3.1 `--ios-emit-class`/`--ios-objectcalls` target), deleting the
silent stubs. This resolves the **facade collision** (generated `CharacterBody3D.kt`
etc. can't compile next to the hand-written classes) so generated wrappers can be
compiled into the runtime. **Scoping (read before starting):**

- **Replace** (pure Godot-API wrappers, many are silent stubs): Resource, Texture2D,
  Node, CanvasItem, Node2D, Node3D, Control, Camera3D, Area3D, StaticBody3D,
  CollisionShape3D, GPUParticles3D, CharacterBody3D, Viewport, Label, Sprite2D, Area2D,
  GPUParticles2D, AudioStreamPlayer, AnimationPlayer, Tween, PropertyTweener, Tweener,
  PackedScene, InputEventMouseButton. Generate the TRUE Godot inheritance chains
  (CharacterBody3D : PhysicsBody3D : CollisionObject3D : Node3D — the facade shortcuts
  these) + their `--ios-objectcalls` helpers (union → one ObjectCallsGenerated.kt).
- **KEEP (bespoke runtime, NOT generated)**: GodotObject (base — generated wrappers
  extend it; it provides `handle`/`requireOpenHandle`/connect/emit/signal), KanamaScope,
  KanamaCoroutineOwner, MainThread, GodotSignal, SignalConnection, Input, Mathf,
  ResourceLoader, GD, IosCallableRegistry.
- **Preserve the Kanama sugar** the demos/runtime call that the generator does NOT emit:
  `Node.requireAs/getAsOrNull/getNodeAsOrNull/createTween/getNodeOrNull`,
  `Node3D.rotationDegrees` (computed), `SceneTree.delaySeconds`, `Tween.tweenProperty`
  + TRANS/EASE consts, `AudioStreamPlayer.create/setStreamFromPath`,
  `PackedScene.instantiate`, the `Signals` objects. Move these to extension functions /
  a small hand layer so the generated class bodies stay generated.
- **Conflicts to reconcile**: generated Node3D has `position`/`setPosition` etc. that
  overlap the hand sugar — keep generated, drop the hand dup; check every demo + runtime
  call site still resolves (esp. `velocity`, `moveAndSlide`, `isOnFloor` which are
  currently STUBS and become real generated calls).
- **Validate**: `installIosAddon` compiles clean; grep shows no empty-body /
  `return false|0.0|null` stub API methods left; **Match3 device regression** (iPhone 12)
  AND now the **platformer device run** (blocker #1 fixed) with matrix 11/11 + ObjectCalls
  probe clean + 0 guardrail hits. Owner T4.1 [S +O review], T4.2 [S].

## After Phase 4 — T3.2/T3.3/T3.4

- **T3.2 [S]** generate any remaining platformer classes beyond the Phase-4 set.
- **T3.3 [O]** wire `Input`/InputMap (`get_axis`/`get_vector`/`is_action_just_pressed`).
- **T3.4 [S]** deploy + validate platformer movement/jump/animation + coin pickup on device.
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

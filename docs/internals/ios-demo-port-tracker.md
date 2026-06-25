# iOS Demo Port — Tracker

Working tracker for porting the Android-supported demos to the iOS (Kotlin/Native) backend. Each
"Remaining task" is self-contained: read this file + the named code files and you can do it in a fresh
session without replaying prior history.

> **Private bits (device UDIDs, exact local-path commands, raw logs) are kept in the loose private
> handoff outside this public repo** (per AGENTS.md). This file stays public-safe.

- **Repos / branch:** `kanama` and `kanama-demos`, both on `ios-demo-ports`.
- **Devices:** a paired iPhone 15 Pro (primary) + iPhone 12; UDIDs/team in the private handoff.
- **Goal/scope:** parity-first — get the demos building + playable on iOS, fixing the underlying
  iOS-runtime/library bugs they expose (don't hack the demos). Prefer generation over hand-writing
  (same as desktop/Android). Defer the full Godot API.

## Status

| Demo | On device |
|---|---|
| Bunnymark, Match3, 3D-Platformer | playable (pre-existing) |
| **dodge** | playable + touch controls |
| **squash** | playable + touch controls |
| **character-controller** | playable; flag works; death plane respawns (V1 fixed) |
| **Racing** | playable + camera-follow + steering joystick (R1 validated) |
| **Bunnymark** | playable; FPS + Bunnies readouts safe-area-inset (E1 validated) |
| **FPS** | playable; weapons `List<Weapon>` @ScriptProperty delivered + AnimatedSprite3D generated (F1). KNOWN: intermittent SIGSEGV in Audio autoload `_ready` (pre-existing lambda-connect infra, see F2) |
| third-person | **PLAYABLE on iPhone 15 Pro (T1), walk-anim fixed** — runs, all scripts init, self-tests 54/78. Fixed on-device: attack crash (Vector3 in Variant path) + visuals (Mobile renderer, not gl_compatibility) + enemy walk animation (`List<String>`/PackedStringArray `@ScriptProperty` delivery, see T1 card) |

## Build / deploy / debug (see private handoff for exact commands + UDIDs)
- **Build-check a demo's scripts** (fast, no device): `./gradlew installIosAddon
  -PkanamaIosProjectDir=<demoDir> -PkanamaIosProjectScriptsDir=<demoDir>/kotlin-src
  -PkanamaXcodeDeveloperDir=<XcodeDeveloperDir>` (set `DEVELOPER_DIR` to the Xcode app, not CLT).
- **Deploy + launch:** `kanama-demos/scripts/ios_device_run.sh <godot> <demoDir> <bundle.id> <AppName>`
  with `KANAMA_IOS_DEVICE` / `KANAMA_IOS_TEAM` / `KANAMA_ROOT` / `DEVELOPER_DIR`. Demo→AppName/bundle
  map: `kanama-demos/scripts/ios_smoke_all.sh`.
- **Device console** (essential for gameplay bugs): `xcrun devicectl device process launch --console
  --device <id> <bundle>` (background); grep for `[kanama]`, `FAILED`, `exception`. The runtime logs
  script-resource/instance creation, `@RegisterFunction` method calls, and connect failures — but NOT
  lambda firing (add temp `println`s for those).
- **Caveats:** free provisioning ≈ 3 installed apps (uninstall between batches); keep device unlocked.

## CRITICAL gotcha — regenerating iOS wrappers
Always regenerate within the **FULL class union** (all currently-emitted classes), not a single
`--ios-emit-class X`. A single-class run sets `wrapper_classes={X}` and **drops methods returning other
wrapper types** (e.g. `Viewport.getCamera3d(): Camera3D?`). Build the union from the generated `.kt`
files' "Generated from Godot docs" headers, regen to a temp dir, verify `removed_funs=0` vs the committed
file before copying. Generator + iOS custom sections: `scripts/generate_api_wrapper.py`
(`IOS_CUSTOM_MEMBER_SECTIONS`, `IOS_CUSTOM_COMPANION_MEMBER_SECTIONS`).

## Key iOS-backend facts (reuse these)
- iOS value-type components are `Double`; Android/desktop are `real_t`(=Float). Kotlin allows `<`/`>`
  across Double/Float (`compareTo`) but NOT `==`/`!=` — shared demo source must `.toDouble()` before
  `==`/`!=`, and use `withX/withY(Number)` instead of `Vector.copy(...toFloat())`.
- The iOS **Variant-call** path (`GodotObject.call`/`get`) decodes a Variant **OBJECT** return as a raw
  `MemorySegment` handle, not a wrapped object — wrap it (`Wrapper(memSeg)` /
  `iosScriptInstanceForOwner(handle.address())` for user scripts).
- Script signals/properties are advertised via the **Script object** (`ScriptExtension`), populated by
  `kanama_ios_script_resource_init_metadata` (C shim). Runtime `Object::connect` to a script `@Signal`
  needs `_has_script_signal` true.

## DONE — iOS runtime/library bugs fixed
- `GodotObject.get/set/setScript` desktop parity (dropped generic `get<T>`). `GodotObject : AutoCloseable`.
- Generated: Input, OS, PathFollow2D/3D, RayCast3D, RenderingServer, AnimationNodeStateMachinePlayback,
  BaseMaterial3D.
- Hand-written: Mathf/GD math, Vector2/3/Basis/Transform3D methods, SceneTree methods,
  MainThread.awaitNextFrame/postAfterFrames (frame-pumped), Light3D.lightEnergy.
- Generator: StringName default emission; `IOS_CUSTOM_COMPANION_MEMBER_SECTIONS` mechanism (KEY_*,
  InputEventMouseMotion.from, BaseMaterial3D.fromMaterial); Viewport/AnimationMixer iOS sections;
  annotations (@GlobalClass, PropertyHint).
- **emitSignal** dropped no-arg custom signals (dodge Start). Fixed via `call("emit_signal", …)`.
- **AnimationMixer.getStateMachinePlayback** wraps the raw `MemorySegment` object return.
- **node_paths object @ScriptProperty** delivery — `IosScriptCodeEmitter` emits a `setProperty(Long)`
  case for `customScriptFqName` props (`iosScriptInstanceForOwner(value) as? <Fq>`). (Racing camera.)
- **Signal registration for method-less scripts** — `init_metadata` early-returned on 0 methods, skipping
  property+signal registration (Events autoload). Guarded with `if (method_count > 0)`.
- **V1 frame hook stopped firing** — `kanama_ios_frame` (C, `kanama_ios_shim.c`) hard-capped at 30 frames
  then set `g_main_loop_callbacks_active = 0`, permanently killing `MainThread.pumpNextFrame()` and ALL
  frame-deferred logic (`awaitNextFrame`/`postNextFrame`/`postAfterFrames`, incl. `KillPlane3D` deferred
  respawn). Cap was redundant — Kotlin `frame()` already self-terminates its startup probe via
  `probeLabelUpdated`. Removed the cap → persistent per-frame hook. Device-verified: death plane respawns.
- Demos: iOS gdextension entries; `mobile_controls.gd` enabled on iOS (`or OS.has_feature("ios")`);
  cross-platform script idioms; character flag reloads the level instead of quitting.
- **R1 Racing steering — no fix needed.** `VirtualJoystick` is a **built-in Godot 4.7 class** (confirmed
  via `--doctool`: `doc/classes/VirtualJoystick.xml`), so it's already in the iOS export template — it was
  never "absent on iOS". The only thing required was making the `MobileControls` CanvasLayer visible on
  iOS, already done by `mobile_controls.gd` (commit `5c060ab`). Input actions `left`/`right` exist in
  `project.godot`. Device-validated on iPhone 15 Pro: steering joystick turns the car.
- **F1 FPS demo (`Starter-Kit-FPS`)** — builds + plays on device (iPhone 15 Pro). Fixes:
  - **Generated `AnimatedSprite3D` + `SpriteBase3D`** via the full `--ios-emit-class` union (per the
    CRITICAL gotcha). Verified the regen did NOT overwrite 5 existing files that the fresh generator
    would have *shrunk* (`getCollider`/`shapeOwnerGetOwner`/Kinematic `getCollider`/`getColliderShape`
    return bare `Object` — kept the committed versions); all ptrcall shapes the 2 new files use already
    exist. Only the 2 new files were copied in.
  - **iOS API methods:** `GodotObject.hasMethod`/`isQueuedForDeletion`, `AudioStreamPlayer.setStream(null)`,
    `Resource.fromHandle` (now non-null, matching desktop — satisfies `KanamaScript<Resource>` selfFactory),
    `ResourceLoader.loadPackedScene`, `GD.degToRad`/`radToDeg`, and `Tween.bindNode`/`setEase`/`tweenCallback`/
    `EASE_OUT_IN` (+`CallbackTweener`). `tween_callback` needed a new C shim
    (`kanama_ios_godot_tween_tween_callback`, builds the method-Callable via the existing
    `g_callable_object_method_constructor`, boxes it, calls via the Variant path) + `.h` decl + cinterop.
  - **iOS KSP emitter (`IosScriptCodeEmitter`) — two parity gaps:** (a) `<Class>Methods.<m>(target, args…)`
    typed dispatchers were emitted **only for zero-arg** methods; now emitted for arg-bearing methods too
    (unblocked `PlayerMethods.damage(collider, 5.0)`). (b) **THE crash root cause:** a
    `List<UserScript>` `@ScriptProperty` (`Player.weapons: List<Weapon>`, Weapon = user `@ScriptClass`
    Resource) was **silently dropped** — the emitter only generated `setPropertyObjectArray` for arrays of
    *engine* wrapper types (`listElementClassName` was empty for user scripts), so the property advertised
    to the engine had no delivery case → `weapons` stayed `emptyList()` → `Player.ready()` threw
    `IllegalStateException: Player requires at least one Weapon`. Fixed by resolving each delivered owner
    handle via `iosScriptInstanceForOwner(it) as? <Fq>` (mirrors the node_paths single-object case). The
    C array-extraction path already existed (`set_property_array`); only the bridge case was missing. Also
    fixed a latent sibling: the engine-wrapper array case had an un-compiled lambda-inference shape; both
    now route via `.toList()` + explicit-typed lambda param (`LongArray` lacks `mapNotNull`). Device-confirmed:
    `property array set ... index=3 count=2`.
  - **KNOWN-FOLLOWUP (F2):** intermittent **SIGSEGV in the Audio autoload `_ready`** (12× `AudioStreamPlayer.create`
    + lambda `signal("finished").connect{…}` in a loop), hits *before* `Player._ready`. Pre-existing iOS
    lambda-connect/AudioStreamPlayer-lifetime infra — **not** an F1 change. Repros under the
    `KANAMA_FPS_SMOKE=1` harness; intermittent in normal play (works after restart). Suspect loop-captured
    `player` in the lambda or ref-counting under 12 rapid connects.
- **E1 Bunnymark readout safe-area** — `Benchmarker.gd` now insets the top-left readouts on mobile by
  `DisplayServer.get_display_safe_area().position + SAFE_AREA_MARGIN(24,24)` (`_apply_safe_area()`).
  Stretch mode is disabled, so GUI coords == screen px. TWO readouts needed it: the scene `Panel/FPS`
  Label AND the **Kotlin-created "Bunnies" Label** (`BunnymarkV2/V3Kanama.kt`, `position=(0,20)`, not
  safe-area-aware) — the latter is offset from GDScript via `find_children(...,"Label")` after
  `add_child`, so no Kotlin rebuild/dual-target needed. In landscape the safe-area *top* inset is tiny,
  hence the fixed margin. Device-validated on iPhone 15 Pro.

## Remaining tasks (each resumable on its own)

### F2. FPS Audio autoload `_ready` SIGSEGV (intermittent)
FPS demo is otherwise playable (F1 done). The Audio autoload's `_ready` creates 12 `AudioStreamPlayer`s
and lambda-`connect`s each `"finished"` signal in a loop; intermittently SIGSEGVs (signal 11) *before*
`Player._ready`. Pre-existing iOS lambda-connect / AudioStreamPlayer-lifetime infra (not an F1 change).
Repro: launch with `KANAMA_FPS_SMOKE=1` (the smoke harness reliably hit it twice; normal play is
intermittent — works after restart). Suspect the loop-captured `player` lambda capture or RefCounted
lifetime under 12 rapid `connect`s. Pull the `.ips` for the native backtrace (idevicecrashreport could
not see the network-paired device this session; try USB or `xcrun devicectl`).

### T1. third-person (`godot-4-3d-third-person-controller`) — PLAYABLE on device (2026-06-24)
**Device-validated on iPhone 15 Pro.** Runs, all script instances init, self-tests 54/78, gameplay
works. Two on-device fixes landed after the first launch:
- **Attack crash (FIXED, committed):** attacking passed a `Vector3` (knockback) through the Object
  Variant call path, which abort()ed — `encodeVariantArgs` (Kotlin) + `pt_arg_to_variant` (C) handled
  Vector2/Vector2i/Color but not Vector3. Added the Vector3 case to both (PT_VECTOR3 / g_variant_from_vector3).
- **Over-bright scene + white water (FIXED, committed in kanama-demos):** the demo shipped
  `rendering_method.mobile="gl_compatibility"` — too low-tier for its Forward+-authored visuals (HDR
  tonemap, glow, screen-space water shader). Switched to `"mobile"` (Vulkan/MoltenVK); device-confirmed
  exposure + water correct. (Other demos keep gl_compatibility; they don't use those features.)
- **Enemy walk animation (FIXED + device-validated):** BeetleBot calls `beetleSkin.walk()` every
  physics frame → `BeetlebotSkin` runs `AnimationNodeStateMachinePlayback.travel("walk")`. Early
  investigation suspected either a detached playback Ref from the Variant
  `get("parameters/.../playback")` path or an inactive AnimationTree, but device logs ruled both
  out: the AnimationTree was active and `travel()` was advancing state (proven by `[anim-dbg]` logs:
  `node='Walk' playing=true`). The bug was that `BeetlebotSkin._force_loop: List<String>` (scene-stored
  `PackedStringArray("Bob","walk")`) was **silently dropped on iOS** — the emitter only generated
  list-property delivery cases for engine-wrapper / user-script element types, so a `List<String>`
  fell through to the C dispatch's `else { return 0; }`. With `forceLoop` empty, the Walk animation
  never got `LOOP_LINEAR` → played once and froze (looked static); one-shot Attack animated fine
  because it doesn't need looping. Same class of silent drop as the FPS `List<Weapon>` gap. FIX
  (branch `fix/ios-string-list-property-delivery`, 4 layers): emitter advertises `List<String>` as
  Variant type 34 (PACKED_STRING_ARRAY) + emits a `setPropertyStringArray` bridge branch; Kotlin
  runtime gains `setPropertyStringArray` + a `kanama_ios_runtime_script_instance_set_property_string_array`
  @CName entrypoint; the C shim's `set_property` dispatch gains a `PACKED_STRING_ARRAY` branch that
  extracts the packed array (reusing the existing `g_variant_to_packed_string_array` + cached
  size/operator_index_const trio) and hands a C string array to Kotlin. Plus a build-time guardrail
  that warns on any `@ScriptProperty` with no iOS delivery case (0 warnings on this demo). Device:
  `property string-array set count=2` logs confirm delivery; bug visibly walks + animates on the
  iPhone 15 Pro. `[anim-dbg]` diagnostic prints stripped from `BeetlebotSkin.kt` (kanama-demos).

### T1 — original task notes (now done) ────────────────────────────────────────
**169 → 0 compile errors.** All layers landed + committed/pushed: KSP dual-annotation fix, 7 wrappers,
math, value-types, 5 singletons, misc glue, generator companions (create/from/fromResource/BODY_AXIS +
SurfaceTool commit), the targeted int32→Long return widening (getCollisionCount + ShapeCast3D Long
index overload), and the 2 tween C shims (tween_method, PropertyTweener.from). iOS addon installs clean.
**ONLY REMAINING: on-device validation on iPhone 15 Pro** (`ios_device_run.sh … thirdperson` →
KanamaThirdPerson / net.multigesture.kanama.thirdperson) — esp. engine-backed Basis/Quaternion math
(getRotationQuaternion/getScale/slerp/fromEuler), free-cam getEuler, coin-fly tween_method, grenade
landing prediction (ShapeCast getCollisionCount/Point). FLAG USER before launch (auto-lock).
Two tracked follow-ups (NOT third-person blockers): (1) systematic int32→Long widening (add the ~dozen
Long-return ptrcall helpers the 23 dropped arg-bearing returns need, then flip logical_return_kind
fully); (2) PropertyTweener.from only handles Color (add Vector2/Double/etc. overloads if a demo needs).

#### Original task notes (now done) ────────────────────────────────────────────
Largest demo. Build-check: `installIosAddon -PkanamaIosProjectDir=.../godot-4-3d-third-person-controller
-PkanamaIosProjectScriptsDir=.../kotlin-src`. Demo compile errors **169 → ~50**. Working tree has
UNCOMMITTED edits (not committed — user hadn't asked). 4 layers landed, **all compiling clean** (0
`ios-runtime` errors; only the demo scripts still fail on the remaining layers):

**DONE this session (verify by build-check):**
1. **iOS KSP dual-annotation const conflict** (`IosScriptCodeEmitter.kt` ~line 339) — a single Kotlin fn
   annotated `@OnInput`+`@OnUnhandledInput` (CameraController.unhandledInput) backed two Godot methods
   (`_input`/`_unhandled_input`) that collapsed to one kotlinName-keyed const → two `const val
   unhandledInput` → "Conflicting declarations". Fixed: `methods.distinctBy { constantIdentifier(it.kotlinName) }`
   on the `Names.Methods` emission only (registration/dispatch correctly keep both). iOS-only bug (JVM
   emitter uses `uniqueConstantIdentifier(godotName, seen)`; Android was fine). **Verified: single const.**
2. **7 wrapper classes** via the full-union regen (CRITICAL gotcha): NavigationAgent3D, SpringArm3D,
   ShapeCast3D, MeshDataTool, SurfaceTool, MultiMeshInstance3D, WorldEnvironment. Union = 232 committed
   "Generated from Godot docs:" names + 7 new → 239 `--ios-emit-class`. Copied ONLY the 7 new files +
   `ObjectCallsGenerated.kt` (proven purely additive: 0 removed / +43 via difflib). All demo-needed
   methods present; skips are exotic (vertex tangents/bones/meta, PackedVector3Array paths). Parents all
   resolve to emitted classes; no name collisions with IosGodotApi hand-written objects.
3. **Math layer** (`IosGodotApi.kt`) — `Mathf.sin`/`sqrt`/`inverseLerp`, `GD.randfn` (Box-Muller).
   Pure kotlin.math (see roadmap "Scalar-math backend divergence" — deliberate, documented).
4. **Value-type layer** — `Vector2` LEFT/RIGHT/UP/DOWN consts; `Vector3.distanceTo`/`distanceSquaredTo`
   (pure)/`bounce`; `Quaternion.slerp`+`fromEuler` (engine builtins, hashes in file); `Basis(Quaternion)`
   ctor + `getEuler()` (pure-Kotlin YXZ, exact Godot formula) + `getScale`/`getRotationQuaternion`
   (engine builtins) + `fromEuler`=`Basis(Quaternion.fromEuler)` (sidesteps the int euler-order arg);
   `Transform3D.times(Vector3)` (xform) + `scaledLocal` (pure); `Node3D.lookAt` `up: Vector3 = Vector3.UP`
   default. NOT yet device-validated — the engine-backed Basis/Quaternion math wants a device check.

**REMAINING (~50 errors, all demo-side):**
- **5 singletons** (mirror `OS.kt`: `getSingleton("X")` + per-method `getMethodBind(hash)`):
  `Time.getTicksMsec`, `Engine.isEditorHint`, `ProjectSettings.getSettingDouble` (get_setting returns
  Variant → use the GodotObject.call Variant path), `PhysicsServer3D.bodyAddCollisionException` (TWO RID
  args — verify an ObjectCalls helper exists), `InputMap.hasAction`/`addAction`/`actionAddEvent`
  (StringName args; actionAddEvent takes StringName+InputEvent → verify/ add the (StringName,Object)
  ptrcall shape).
- **Constants:** `PhysicsBody3D.BODY_AXIS_ANGULAR_X/Y/Z` (generated companion consts — regen
  PhysicsBody3D or add a custom companion section); `InputEventMouseButton.MOUSE_BUTTON_RIGHT` (+ its
  `.from(event)` — hand-written class in IosGodotApi, warned-collision list).
- **`.create()` factories** on generated classes (`Camera3D`, `SurfaceTool`, `MeshDataTool`,
  `InputEventKey`) and `ArrayMesh.fromResource(Resource)` — durable path is the generator's
  `IOS_CUSTOM_COMPANION_MEMBER_SECTIONS` (+ regen the affected files), mirroring KEY_*/InputEventMouseMotion.from/
  BaseMaterial3D.fromMaterial. Pattern: `X(MemorySegment.ofAddress(IosGodot.constructObject("X")))`.
  NOTE: GrassScatter's `createFromSurface`/`fromResource`/`T`/`R`-inference errors all CASCADE from the
  missing `MeshDataTool.create()`/`ArrayMesh.fromResource` — fixing those two clears all of GrassScatter.
- **Misc engine methods:** `GD.isInstanceValid(Object)` + `Object.getInstanceId()`; `SceneTree.isPaused`
  + `getNodesInGroup` (typed List<Node> — reuse the 2.7d typed-object-array helper); `Node.isClass(String)`;
  `Signal.emit()` (on the `.signal(name)` GodotSignal — SmokeQuit `resume.signal(...).emit()`);
  `Tween.tweenMethod(...)`; `ResourceLoader.loadAudioStream(path)`; `System.err` (iOS compat `System`
  shim in IosScriptCodeEmitter.compatibilitySources lacks `err` — SmokeQuit `System.err.println`).
- **`@ScriptProperty(hint=…, hintString=…)`** — CameraController:32/35 "No parameter with name
  'hint'/'hintString'": the iOS annotation shadow lacks those params (add to the iOS Annotations.kt copy +
  emitter, per the KSP2-native shadow-annotation rule).
- **Int/Long width:** `ShapeCast3D.getCollisionCount()` returns `Int`; demo writes `!= 0L`
  (GrenadeLauncher:123). iOS generates `Int` for int32 returns; desktop/Android return `Long`. Either a
  generator int-width parity fix or confirm the demo's `0L` is the outlier — check the desktop wrapper.
- **`quit`/`unloadCurrentScene`** (SmokeQuit/DemoPage): exist on `SceneTree` already — verify the
  `getTree()` receiver type at those sites (likely a nullable/receiver mismatch, not a missing method).
**UPDATE 2026-06-24 — now at 3 demo errors** (singletons + most of misc DONE, committed/pushed). The
single-class regen for companions used `IOS_CUSTOM_COMPANION_MEMBER_SECTIONS` (create()/from()/
fromResource()/BODY_AXIS) + one `IOS_CUSTOM_MEMBER_SECTIONS` (SurfaceTool no-arg commit()), additive
regen (ObjectCallsGenerated byte-identical, all files removed=0). Final irreducible 3:
- **Coin.kt:68 `tween.tweenMethod(self, "_follow", 0.0, 1.0, dur)`** — no `tween_method` C shim exists.
  Needs a new `kanama_ios_godot_tween_tween_method` (Callable + from/to Variants + duration) mirroring
  the F1 `tween_callback` shim (.c + .h + cinterop regen → DEVELOPER_DIR full-Xcode build) + Kotlin
  `Tween.tweenMethod`.
- **Icone.kt:30 `tweener?.from(source)`** — `PropertyTweener.from(value)` (sets the tween start value);
  no C shim. Needs `kanama_ios_godot_tweener_property_from` (Variant value) + Kotlin `PropertyTweener.from`.
  Cosmetic (icon fade start); could also be made a no-op stub if the shim is deferred — but check_ios_no_
  silent_stubs forbids silent stubs, so it needs a real impl or an explicit documented skip.
- **GrenadeLauncher.kt:123 `getCollisionCount() != 0L`** — iOS generator maps `int32`-meta returns →
  `Int` (generate_api_wrapper.py line 34 `"int32": "Int"`); desktop/Android map `int32` → `Long` (Godot
  ints are 64-bit; int32 is a storage hint). SYSTEMATIC divergence (~364 iOS `: Int` returns). PARITY
  DECISION (blast radius): (a) targeted — special-case the few count getters to Long; (b) systematic —
  flip iOS `int32`→`Long` to match desktop (correct parity, full regen + revalidate all demos);
  (c) document + leave. The ptrcall decode already reads a `LongVar` then `.toInt()`, so (b) is mostly a
  type-label change + regen.
- **THEN:** clean `compileKotlinIosArm64`, deploy to iPhone 15 Pro (`ios_device_run.sh … thirdperson`
  → AppName `KanamaThirdPerson`, bundle `net.multigesture.kanama.thirdperson`), device-validate
  (esp. the engine-backed Basis/Quaternion math + free-cam getEuler). FLAG USER before on-device launch.

### Misc
- Roadmap table: `docs/internals/ios-backend-roadmap.md` (keep in sync).
- V1 landed (frame hook now persistent). Worth re-testing signal-heavy + frame-deferred flows across all
  demos now that `awaitNextFrame`/`postNextFrame`/`postAfterFrames` work for the whole session.

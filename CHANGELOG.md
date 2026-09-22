# Changelog

All notable user-facing changes will be recorded here.

This project uses a Keep a Changelog-style format and follows semantic
versioning once public releases begin.

## Unreleased

### Fixed — iOS: Godot **static** methods called through the shared wrapper tree were silent no-ops

- **Every `is_static` Godot method reached through `src/sharedApi` did nothing on iOS.** The generator
  renders `NULL_SEGMENT` as the instance for a static method (`_null_segment()` in
  `scripts/generate_api_wrapper.py`). Desktop hands that `MemorySegment.NULL` straight to
  `object_method_bind_ptrcall`, which Godot accepts for a static bind. On iOS the same helpers all ended
  in the C entry point `kanama_ios_godot_ptrcall`, which deliberately early-returns when `instance == 0`
  — the guard commit `30c949a1` kept when it added the separate `kanama_ios_godot_ptrcall_static` entry
  point. The per-platform iOS hand copies used the `ptrcallStatic*` helpers and were fine; the shared
  tree never did, so its static calls returned `null` / `0` / the default on device while passing on
  desktop. Present since the shared tree first rendered statics (`7a43afcf`, 2026-09-15, task 104 step
  3). Recount the blast radius with
  `grep -rc "Bind, NULL_SEGMENT" src/sharedApi/kotlin/net/multigesture/kanama/api/ | grep -v ':0'`:
  **72 static call sites across 36 shared classes**.
- **The fix is a dispatcher per guarded C entry point, in the iOS `ObjectCalls`** — never in the shared
  tree. `ptrcallDispatch(...)`, hand-written above the `BEGIN GENERATED MEMBERS` marker in
  `src/iosMain/.../binding/runtime/ObjectCalls.kt`, routes a zero instance to
  `kanama_ios_godot_ptrcall_static` and everything else to `kanama_ios_godot_ptrcall`. All **1172**
  plain-ptrcall call sites in that file go through it, the generated region included: the generator
  emits `ptrcallDispatch` now, so a regen keeps the fix. Desktop needs no change — its ptrcall path
  carries no null-instance guard (the one in `src/jvmMain/.../ObjectCalls.kt` is inside
  `notifyPostinitialize`).
- **`kanama_ios_godot_ptrcall` was not the only guarded entry point.** Thirteen of the 72 sites (in
  `EditorExportPlatform`, `FileDialog`, `GLTFDocument`, `JSON`, `MultiplayerAPI`, `Node`, `Resource`
  and `ShaderIncludeDB`) return through a specialised C entry — `..._no_args_ret_string`,
  `..._no_args_ret_string_name`, `..._no_args_ret_packed_string_array`,
  `..._no_args_ret_typed_array_blob`, `..._ret_array_blob`, `..._ret_utf8`, `..._ret_variant_scalar`
  and `kanama_ios_godot_object_call` — each carrying the same `instance == 0` guard. **All 23 guarded
  entry points in `ios/bootstrap/kanama_ios_shim.c` that an `ObjectCalls` helper calls with an instance
  now follow the `30c949a1` pattern**: the body moves into an unguarded `static ..._dispatch(...)`, the
  existing symbol keeps its guard and calls it, and a new `<symbol>_static(...)` sibling (declared in
  `ios/include/kanama_ios.h` for cinterop) calls it with a null instance. No existing guard was removed
  and no existing signature changed. The existing `ptrcallStatic*` helpers and their hand users
  (`FileAccess`, `ImageTexture`) are untouched.
  Entry points no helper reaches with the static marker — `kanama_ios_godot_ptrcall_string_arg`,
  `kanama_ios_godot_ptrcall_ret_object_array` and the object-handle entries, whose zero check guards a
  live engine handle — keep their single guarded form on purpose.
- **Two `_static` siblings were dead ends until the split reached one level down.**
  `kanama_ios_godot_ptrcall_ret_raycast_dict_dispatch` and
  `..._ret_object_handles_dispatch` — the *unguarded* halves of their own splits, so by definition
  reached with a zero instance — themselves called `kanama_ios_godot_ptrcall` rather than
  `kanama_ios_godot_ptrcall_dispatch`. The guard bit one frame below the Kotlin dispatcher and returned
  before writing the result cell, so a static routed through the raycast-dictionary or
  object-handle-list shapes still no-opped. Both now call the unguarded body, as
  `kanama_ios_ptrcall_encode_container` already did. **With that, 72 of 72 static call sites reach
  Godot on iOS.**
- **A 24th guarded entry point was invisible to the gate: `kanama_ios_classdb_instantiate_owned`**
  (task 43's owned `ClassDB.instantiate` decode). It takes a `method_bind` + an instance and rejects a
  zero instance like the rest, but it is not spelled `kanama_ios_godot_*`, and the gate's regexes keyed
  on that prefix. It now has the same `_dispatch` / guarded entry / `_static` split and a
  `classdbInstantiateOwnedDispatch` in `ObjectCalls.kt`. Its one shared-tree caller (`ClassDB.kt`)
  passes the singleton, so this was latent rather than live — which is the point: the property now holds
  by construction.
- **New gate `scripts/check_ios_static_dispatch.py`** (a `local_ci.sh` stage beside
  `check_objectcalls_parity`) makes this structural instead of remembered. It derives the guarded set
  from the shim by **signature** — any `kanama_ios_*` definition taking both an `int64_t method_bind`
  and an instance it *early-returns* on (an `if` whose condition tests the instance for zero and whose
  block returns, not a zero comparison anywhere in the body) — and checks both sides of the boundary:
  `ObjectCalls.kt` may name a guarded entry point only inside a private `*Dispatch` function that also
  calls its `_static` sibling, and **no `_dispatch` body in the shim may name one at all** (it must
  call the callee's `_dispatch` body). It prints the 26 guarded entry points, the 24 dispatched ones,
  the count of `_dispatch` bodies scanned and the out-of-scope object-handle entries on PASS. Pointing
  `--shim` / `--header` / `--objectcalls` at a scratch copy runs its negative tests without touching
  the tree.
- **The iOS `OBJECTCALLS SELFTEST` goes from 205 to 218 checks.** Thirteen new rows drive the
  shared-tree static path through the public wrapper API rather than the `ptrcallStatic*` helpers:
  `Image.createFromData(2, 2, false, FORMAT_RGBA8, ByteArray(16))` (non-null, width 2, height 2),
  `Image.create(4, 3, false, FORMAT_RGBA8)` (non-null, width 4), `Thread.isMainThread()`,
  `RegEx.createFromString("a+b", false)`, a `JSON.stringify`/`JSON.parseString` round-trip,
  `ShaderIncludeDB.listBuiltInIncludeFiles()` and `getBuiltInIncludeFile(...)` — **both replaced
  further down: they were not probes** — `Resource.generateSceneUniqueId()` and
  `MultiplayerAPI.getDefaultInterface()`.
- **The dispatcher exposed a self-test row that had only ever passed because of the guard.** On
  device the `RenderingServer.mesh_create_from_surfaces` row (added by task 100 parcel 10)
  SIGSEGV'd inside `RenderingServer::_mesh_create_from_surfaces` with a null `this`. Its comment
  claimed "the RenderingServer exists before extensions initialise"; it does not. Godot 4.7.2
  `main/main.cpp` runs `initialize_extensions(INITIALIZATION_LEVEL_SCENE)` long before
  `register_server_singletons()` adds the `RenderingServer` singleton
  (`servers/register_server_types.cpp`), so at scene init `getSingleton("RenderingServer")`
  returned 0, the old C null-instance guard turned the whole call into a no-op returning RID 0 —
  and the row asserted exactly `RID(0L)`. It had therefore passed for the wrong reason since it
  was written. Routing a zero instance to the static entry point sent the call to Godot for real,
  with no `this`. **The dispatch is right; the row was wrong.** Two mitigations, both in this
  commit:
  - **A first-frame self-test phase.** `kanamaIosRuntimeObjectCallsSelfTestFrame`
    (`@CName("kanama_ios_runtime_objectcalls_selftest_frame")`) sits beside the scene-init
    self-test with the same `check` machinery and holds the moved RenderingServer row, now run
    against a real instance. (The `RID(0L)` expectation this bullet kept is **wrong**, and is
    corrected further down: 4.7.2 returns a valid, empty mesh.) `kanama_ios_frame` calls it
    exactly once, under `KANAMA_IOS_DEBUG_VARIANT_CHECKS`, when the frame counter first reaches 1,
    before `kanama_ios_runtime_frame()`. It prints
    `[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): N passed, M failed`; the scene-init summary
    line is unchanged. Totals at this commit: 236 at scene init, 2 on frame 1 — **superseded by 236 and 4** after the probe-rule fixes further down (218 − the moved row − the
    now-redundant `input-singleton` row, + 12 `singleton-present(…)` + 8 `object-constructed(…)`
    checks) — **236 and 4** after the probe-rule fixes further down.
  - **Singleton lookups fail loudly on both platforms.** `ObjectCalls.getSingleton` prints
    `[kanama][ios][kn] ERROR: getSingleton("<name>") returned null — not registered at this
    initialization level` on iOS (Godot's own error print does not reach the device console
    capture) and the `[kanama:kt]` equivalent on `System.err` on desktop/Android. Both return the
    null pointer unchanged — no throw; the return shape is public behaviour.
- **Every singleton and every false-pass-prone instance in the self-test is now checked.** A new
  `requireSingleton(name)` helper records `singleton-present(<name>)` and each of the 13 singleton
  rows (Input, Time, OS, ClassDB ×2, Geometry3D ×2, ProjectSettings, InputMap, TranslationServer,
  RenderingServer, Engine, NativeMenu) skips its dependent calls when the lookup fails — recorded
  as a FAILED check with a reason, never silently passed and never executed with a zero instance.
  The audit of the remaining instances found that every `constructObject`-backed row asserts a
  round-trip value a zero instance cannot produce, **except eight** whose assertion is a default
  (an empty list, an empty string, a zero `Rect2i`, "no signal fired", "all components finite") —
  exactly the shape of the RenderingServer false pass. Those go through a matching
  `requireObject(class)` helper and skip on failure: `TileMap.get_used_rect`,
  `AnimationPlayer.animation_get_next`, `GridMap.get_used_cells`,
  `Camera3D.get_camera_projection`, `Camera3D.get_frustum`, and the two lambda-Callable
  free-ordering rows.
- **With the self-test honest, the phone failed four rows at scene init and one on frame 1 — and
  three of the five could never have failed for the right reason.** The rule they broke, now
  written into the self-test
  header and `docs/contributing/backends/ios.md`: **a probe must assert a value the no-op path
  cannot produce.** A static routed with a zero instance used to return `null` / `0` / `false` /
  `""` / an empty list / a zeroed struct, so a probe whose *expected* value is one of those cannot
  tell the working call from the call that never happened.
  - **`NativeMenu` was the same false pass as `RenderingServer`.** Godot adds the Engine singleton
    entry in `register_server_singletons()` (`servers/register_server_types.cpp:400`), the same late
    step as `RenderingServer` — `main/main.cpp:3864`, long after
    `initialize_extensions(INITIALIZATION_LEVEL_SCENE)` at `main/main.cpp:3793` — so the scene-init
    lookup returned 0 and the guard answered with the null Callable the row asserted. The NativeMenu
    *object* exists far earlier on iOS, so the row MOVED rather than went:
    `DisplayServerAppleEmbedded` (which `platform/ios` inherits through `drivers/apple_embedded`)
    constructs one at `drivers/apple_embedded/display_server_apple_embedded.mm:65`, and
    `NativeMenu`'s constructor sets its own singleton (`servers/display/native_menu.h:154`);
    `DisplayServer::create` runs at `main/main.cpp:3368`. Both rows are now in the frame-1 phase.
  - **The two `ShaderIncludeDB` probes were indistinguishable on the GL Compatibility renderer, and
    are deleted.** `listBuiltInIncludeFiles()` expected a non-empty list and `getBuiltInIncludeFile`
    a non-empty source, but Godot registers the built-in includes only from the RenderingDevice
    renderer (`servers/rendering/renderer_rd/renderer_scene_render_rd.cpp:1796-1798`). Match3 runs
    GL Compatibility, so the honest answer is the empty list — which is also exactly what the old
    no-op produced. Their two C entry points keep their coverage through replacements that assert a
    value a no-op cannot fake: `GLTFDocument.getSupportedGltfExtensions()` (same shared-tree
    `ptrcallNoArgsRetPackedStringList` helper, same `NULL_SEGMENT`) must be non-empty **and** contain
    `KHR_lights_punctual` — a hard-coded set in
    `modules/gltf/gltf_document.cpp:6968` registered by `initialize_gltf_module` at
    `MODULE_INITIALIZATION_LEVEL_SCENE`, which `main/main.cpp` runs immediately *before* extension
    SCENE init (`main/main.cpp:3792-3793`), independent of the renderer; and a helper-level
    `ObjectCalls.ptrcallWithStringArgRetString(getSha256Bind, NULL_SEGMENT, "res://project.binary")`
    must return 64 lowercase hex characters. That second one is helper-level on purpose:
    `FileAccess` is per-platform on iOS and hosts no shared-tree static of that shape, but the route
    (`objectCallDispatch` → `kanama_ios_godot_object_call_static`) is identical. It is guarded by a
    new iOS `FileAccess.fileExists` — the desktop member's exact shape, static bind through
    `NULL_SEGMENT` — and a missing `res://project.binary` is a recorded FAILURE, not a skip.
  - **The `RenderingServer` expectation was wrong, and had never been checked against 4.7.2.**
    Follow-up 4 kept `RID(0L)` on the claim that the engine rejects an empty surface list with
    `ERR_FAIL_COND_V`. It does not: `RenderingServer::_mesh_create_from_surfaces`
    (`servers/rendering/rendering_server.cpp:1996-2002`) has no guard at all, and
    `mesh_create_from_surfaces` (`servers/rendering/rendering_server_default.h:363`) opens with
    `mesh_allocate()` — `mesh_owner.allocate_rid()` in the GL Compatibility mesh storage
    (`drivers/gles3/storage/mesh_storage.cpp:65-67`) — so an empty list yields a **valid, empty
    mesh**. An invalid RID was also the no-op answer, which is what made the row unable to tell the
    fix from the defect either way. The row now asserts a valid RID and frees it with
    `RenderingServer.free_rid`, so the frame-1 phase carries a non-default assertion.
  - **A fourth row failed the rule on review and is strengthened: `Camera3D.get_camera_projection`.**
    It asserted `isFinite()` on the four diagonal cells, which `0.0f` satisfies — so the zeroed
    64-byte return buffer of the no-op path passed it. Its stated reason ("treeless camera
    projection values aren't deterministic") was also unverified and wrong:
    `Camera3D::get_camera_projection` opens with
    `ERR_FAIL_COND_V_MSG(!is_inside_tree(), Projection(), ...)` (`scene/3d/camera_3d.cpp:299-302`)
    and `Projection` is `= default` over member initialisers spelling the **identity** matrix
    (`core/math/projection.h:55-60`). The row now asserts the identity — a 1.0 diagonal and a 0.0
    off-diagonal, which the zeroed buffer cannot produce.
- **Self-test totals: 236 at scene init (unchanged — four rows out, four in) and 4 on frame 1**
  (was 2), all expected to pass. The remaining default-valued assertions were audited against the
  rule and all are admissible: every one is an INSTANCE row whose instance `requireSingleton` /
  `requireObject` has already proven non-zero (so the static route is not taken and the default is a
  real answer), or it sits in a row that also asserts a non-default value through the same helper.
  The last one, `plane-array-ret(get_frustum finite)`, was vacuously true (`all {}` on an empty
  list): `Camera3D::get_frustum` returns an empty list outside the world tree
  (`scene/3d/camera_3d.cpp:792-798`) and a populated frustum needs a viewport that does not exist at
  scene-level extension init. It now asserts that documented empty answer
  (`plane-array-ret(get_frustum off-world == empty)`), which is at least falsifiable, and its comment
  names the non-default row that carries the Plane record decode
  (`array-ret(Geometry3D.build_box_planes has 6 planes)`).

### Changed — the `Tweener` family is generated once (task 117 P2', 3/3) — **source break: fluent setters return `X?`**

- `Tweener`, `PropertyTweener`, `CallbackTweener` and `MethodTweener` retire together into the shared
  wrapper tree (`src/sharedApi/.../api/<Class>.kt`). They had to go as a set: the iOS hand cluster in
  `IosGodotApi.kt` put `setTrans`/`setEase` on the *base* `Tweener` (Godot declares them on the
  subclasses) precisely because the generated subclass members would have clashed with it — which is
  also why `MethodTweener` was the `unsupported` cell iOS did not host at all. All four
  `PER_PLATFORM_WRAPPERS` entries are gone (29 → 25), and `IntervalTweener`, `AwaitTweener` and
  `SubtweenTweener` were already shared. **`Tween` itself stays hand-written on both platforms** (its
  iOS Variant `tween_property` runtime is outside task 117).
- **The fluent setters return the NULLABLE self type.** The desktop hand copies returned the non-null
  self through a private `wrapOrThis` that turned a null engine return into `this`; the generated
  self-return collapse keeps the reference-neutral part (`if (ret.address() == segment.address()) {
  RefCounted.releaseHandle(ret); return this }`) but ends in `wrap(ret)`, which is nullable, like
  every other generated object return. So on **desktop**:
  `PropertyTweener.from`, `fromCurrent`, `asRelative`, `setTrans`, `setEase`, `setCustomInterpolator`,
  `setDelay` are `PropertyTweener?`, and `CallbackTweener.setDelay` is `CallbackTweener?`. Chained
  calls need `?.`:
  `tweener.setTrans(Tween.TRANS_BACK)?.setEase(Tween.EASE_OUT)`. `wrapOrThis` itself is gone — after
  the inline collapse it had no callers. (`Tween`'s own `wrapOrThis` is untouched: `Tween` is still
  hand-written and its 12 fluent methods still return a non-null `Tween`.)
- **iOS gains the whole family.** `MethodTweener` arrives as a class iOS did not host (`setDelay`,
  `setTrans`, `setEase`); `PropertyTweener` goes from one hand method (`from(value: Color)`) to the
  generated seven, with `from(value: Any?)` covering the Color case through the iOS Variant argument
  encoder (`packVariantDesc` boxes a `Color` as `PT_COLOR`); `CallbackTweener` gains `setDelay`; the
  base `Tweener` gains `Signals.finished` and the companion `fromHandle`/`wrap`.
- **iOS: `Tweener.setTrans` / `Tweener.setEase` move to the subclasses.** The hand base class carried
  them for every tweener; Godot declares them on `PropertyTweener` and `MethodTweener` only, and that
  is where the generated tree puts them. Every tween chain in the demos and `example_project` starts
  from `tweenProperty(...)`, so they still resolve; `tweenCallback(...)` and `tweenInterval(...)` never
  had a transition to set.
- **iOS `Tween.tweenMethod(...)` returns `MethodTweener?`** (it was declared `Tweener?` because no
  `MethodTweener` existed), matching desktop. That is the one hand edit the iOS `Tween` class needed;
  the rest of it compiles unchanged against the generated classes, whose primary constructors are
  public.
- **`PropertyTweener` and `CallbackTweener` primary constructors are public** (they were `internal` on
  desktop) — the generator's shape for every retiring class, as `Mesh`, `PackedScene` and `Resource`
  took before (D4 as amended by D10). Both also gain `@JvmStatic fun fromHandle(handle: GodotHandle)`.
- `Tweener` and `MethodTweener` are byte-identical to their deleted desktop copies apart from the
  `internal wrap` parameter (`MemorySegment` → the `RawSegment` alias).
- The iOS hand glue retires with the cluster: `IosGodot.tweenerSetTrans`, `IosGodot.tweenerSetEase`
  and `IosGodot.propertyTweenerFromColor` are deleted along with their three cinterop imports. The C
  shim functions (`kanama_ios_godot_tweener_set_trans` / `_set_ease` /
  `kanama_ios_godot_property_tweener_from_color`) and their three static method binds stay in
  `ios/bootstrap/kanama_ios_shim.c` for a later cleanup.
- **Task 117 P2' is complete.** The wrapper parity gate is down to its permanent contract: `HAND_SHAPED`
  is **3 classes** — `GodotObject`, `RefCounted`, `GodotCallable`, the three roots P3' turns into
  `expect`/`actual` — and the allowlist is 68 → **62** (the six `Tweener` lines). The shared tree grows
  1006 → **1010** classes, `PER_PLATFORM_WRAPPERS` 29 → **25**, and `IOS_UNSUPPORTED_CLASSES` is down to
  `DirAccess` alone.

### Fixed — `StaticBody3D` sits on the real physics chain on iOS (task 117 P2', 2/3)

- `StaticBody3D` is generated once into the shared wrapper tree (`src/sharedApi/.../api/StaticBody3D.kt`);
  the generated desktop copy and the hand-written iOS class in `IosGodotApi.kt` are both deleted and
  `PER_PLATFORM_WRAPPERS` loses the entry. The shared draft is signature-identical to the deleted desktop
  file (only the `internal wrap` parameter goes `MemorySegment` → the `RawSegment` alias), so **no desktop
  source change and no int width change**.
- **iOS: `StaticBody3D : Node3D` becomes `StaticBody3D : PhysicsBody3D : CollisionObject3D : Node3D`** —
  task 117's decision **D3**, satisfied by construction rather than by a hand edit. The hand class was a
  thin `Node3D` subclass that re-declared just `collisionLayer` and `collisionMask` because the chain it
  needed did not exist on iOS; both now come from `CollisionObject3D` with the same `Long` type, the same
  `ptrcallNoArgsRetUInt32` / `ptrcallWithUInt32Arg` helpers and the same method-bind hashes
  (`set_collision_layer` 1286410249, `get_collision_layer` 3905245786, and the mask pair), so nothing
  changes at a `body.collisionLayer` call site.
- **iOS gains `StaticBody3D`'s own 9 members** — `physicsMaterialOverride`, `constantLinearVelocity`,
  `constantAngularVelocity` and their `get*`/`set*` pairs — plus the companion `fromHandle`/`wrap`, plus
  everything the corrected chain brings: `PhysicsBody3D`'s 14 members and `CollisionObject3D`'s 38
  (`shapeOwner*`, `inputRay*`, `collisionPriority`, `areaEntered`-style binds, …).
- `AnimatableBody3D`, which the shared tree already declared as `: StaticBody3D`, inherits the corrected
  chain on iOS with it.
- The wrapper parity gate drops the class from `HAND_SHAPED` (5 → **4 classes**) and its 14 allowlist
  lines go (82 → **68**), including the `StaticBody3D | supertype | *` line that carried D3 as a known
  divergence since P0. The shared tree grows 1005 → **1006** classes, `PER_PLATFORM_WRAPPERS` shrinks
  30 → 29, and iOS hand-writes 10 collision classes instead of 11.

### Changed — `Image` and `PlaneMesh` generated once — iOS gains 21 `Image` members (task 117 P2', 1/3)

- The first two group-B classes are generated once into the shared wrapper tree
  (`src/sharedApi/.../api/Image.kt`, `.../PlaneMesh.kt`); all four per-platform copies are deleted and
  `PER_PLATFORM_WRAPPERS` loses both entries. Group B is the OTHER direction from group A: desktop was
  already generated and the **iOS** copy was the hand-written one, so every change below lands on iOS —
  the shared draft is signature-identical to the deleted desktop file (only the `internal wrap` parameter
  goes `MemorySegment` → the `RawSegment` alias, which is `MemorySegment` on the JVM). **No desktop
  source change, no int width change, on either class.**
- **`Image` gains 21 members on iOS** — the shapes the 2026-07 iOS renderer could not emit and task 100's
  `Packed*Array` / `Rect2i` / `Vector2i` marshalling since can: `blitRect`, `blitRectMask`, `blendRect`,
  `blendRectMask`, `fillRect`, `getRegion`, `setData`, `computeImageMetrics`, the nine
  `load{Bmp,Dds,Exr,Jpg,Ktx,Svg,Tga,Webp}FromBuffer` readers (`loadPngFromBuffer` was already hand-wired)
  and the five `save{Dds,Exr,Jpg,Png,Webp}ToBuffer` writers. The iOS shape gap is unchanged at **3
  desktop-only members waiting** across 2 classes — no `Image` member was refused, so no `Image.jvm.kt`
  companion exists.
- **`Image`'s four companion factories keep their signatures on both platforms**: `create(width,
  height, useMipmaps, format)`, `createEmpty(...)`, `createFromData(..., data: ByteArray)` and
  `loadFromFile(path)` are declared exactly as the iOS hand copy declared them — they are Godot statics
  the generator emits itself, so the hand `KANAMA-IOS-SUGAR` re-add note retires with the file. Their
  iOS **dispatch** did change: the hand copy called `createFromData` through the static entry point
  (`ObjectCalls.ptrcallStaticWithTwoLongBoolLongByteArrayArgsRetObject`), while the shared file renders
  the generator's `NULL_SEGMENT` static marker on the ordinary instance helper
  (`ptrcallWithTwoIntBoolLongByteArrayArgsRetObject`). That path was a silent no-op on iOS until the
  `ptrcallDispatch` fix recorded under **Fixed** above; with it, all four factories work on iOS again.
- `Image.getData()` on iOS no longer passes a `getDataSize()` size hint to
  `ObjectCalls.ptrcallNoArgsRetByteArray`; it calls the two-argument form, whose iOS `actual` delegates
  with `-1L` (size read from the returned `PackedByteArray`). Same bytes, one fewer engine call.
- **`PlaneMesh.fromResource(value: Resource)` takes a non-null `Resource` on iOS** (the hand copy declared
  `Resource?` and returned `null` for it; desktop always required non-null). It is a
  `FACTORY_HELPERS["PlaneMesh"]` row now, generated identically for every platform. Zero callers in the
  repo or the demos. The rest of `PlaneMesh` is 15 members on both sides before and after.
- `ObjectCalls`: the shared tree reaches 16 more helpers, all already present and audited on both
  platforms and now `actual` on both — `ptrcallWithRect2iAndColorArg`, `ptrcallWithVector2iAndColorArg`,
  `ptrcallWithObjectRect2iAndVector2iArgs`, `ptrcallWithTwoObjectRect2iAndVector2iArgs`,
  `ptrcallWithTwoIntBoolLongArgsRetObject`, `ptrcallWithTwoIntBoolLongByteArrayArgs`,
  `ptrcallWithTwoIntBoolLongByteArrayArgsRetObject`, `ptrcallWithByteArrayAndDoubleArgRetLong`,
  `ptrcallWithDoubleArgRetByteArray`, `ptrcallWithBoolAndDoubleArgRetByteArray`,
  `ptrcallWithTwoBoolAndDoubleArgRetByteArray`, `ptrcallWithBoolAndLongArgs`,
  `ptrcallWithStringAndDoubleArgRetLong`, `ptrcallWithStringBoolDoubleArgsRetLong`,
  `ptrcallWithStringTwoBoolAndDoubleArgRetLong`, `ptrcallWithThreeLongArgsRetLong`. The common
  `expect object ObjectCalls` grows 1415 → 1431. No new native path.
- The wrapper parity gate drops both classes from `HAND_SHAPED` (7 → **5 classes**) and their 21 allowlist
  lines go (103 → **82**) — all 21 are `Image | desktop-only | …`; `PlaneMesh` had none. The shared tree
  grows 1003 → **1005** classes and `PER_PLATFORM_WRAPPERS` shrinks 32 → 30.
- `docs/reference/generated/ios-backend-handwritten.md` is regenerated and drops four stale rows (two
  `Image` sugar sites, one `PlaneMesh`, and one for `iosMain/.../StandardMaterial3D.kt`, a file P1'(c)
  already deleted): 20 → 16 marked sites, SUGAR 9 → 5.

### Changed — `Viewport` and `Resource` generated once — group A complete (task 117 P1'(c), 3/3) — **desktop source break**

- The last two group-A classes are generated once into the shared wrapper tree
  (`src/sharedApi/.../api/Viewport.kt`, `.../Resource.kt`); both per-platform copies of each are
  deleted and `PER_PLATFORM_WRAPPERS` loses both entries. As in 1/3 and 2/3 the shared draft is
  signature-identical to the committed iOS copy, so every change below lands on desktop — except that
  iOS GAINS `Resource.create()` and `Resource.fromObject()` (they were desktop-only companion helpers).
- One desktop body changed without a signature change: `Viewport.isUsingXr()` was bound to Godot's
  deprecated compatibility bind (hash 2240911060, `_is_using_xr_115799`); the generated form uses the live
  `is_using_xr` bind (36873697), as the iOS copy already did. No caller in the repo or the demos.
- **`Viewport.getCamera3D()` and `getCamera2D()` survive as shared members.** The camelCase aliases
  over the generator's `getCamera3d()` / `getCamera2d()` lived twice — on the desktop hand copy
  (`getCamera3D` only) and in `IOS_MEMBER_SECTIONS["Viewport"]` (both) — and are now one
  `SHARED_MEMBER_SECTIONS["Viewport"]` entry, so the four demo call sites and
  `example_project/WrapperConvenienceProbe.kt` keep compiling on desktop, Android and iOS, and
  **desktop gains `getCamera2D()`**.
- **`Viewport.pushInput` / `pushUnhandledInput` take a non-null `InputEvent`.** The desktop hand copy
  declared `event: InputEvent?` and passed `NULL_SEGMENT` for null; the generated form is
  `pushInput(event: InputEvent, inLocalCoords: Boolean = false)` — passing `null` no longer compiles
  (0 callers in the repo or the demos). The `inLocalCoords` default is unchanged.
- **Four `Viewport` properties arrive on desktop**: `positionalShadowAtlasQuad0`…`Quad3`
  (`var …: Long` over the `get*`/`set*` pair desktop already had), plus five companion constants —
  `DEBUG_DRAW_AREA_LIGHT_ATLAS`, `DEBUG_DRAW_CLUSTER_AREA_LIGHTS`,
  `DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_PARENT_NODE`, `DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_PARENT_NODE`,
  `SCALING_3D_MODE_NEAREST`.
- **`Resource`'s primary constructor is public** (it was `internal` on desktop, public on iOS) — the
  generator's shape for every retiring class, as `Mesh` and `PackedScene` took in P1'(a) (D4 as
  amended by D10). `Resource` is not an `expect`/`actual` root, so D4's `internal` rule no longer
  applies to it; the ownership guidance is unchanged (a wrapper you mint around a handle you already
  hold is a borrowed view — never `close()` it).
- **`Resource.create()` and `Resource.fromObject(value: GodotObject)` keep working**: they are a
  `FACTORY_HELPERS["Resource"] = FactorySpec(True, (Downcast("fromObject", "GodotObject", False),))`
  row now, generated for every platform with the same bodies, so `example_project/HelloScript.kt`
  and the demos' `Resource.fromObject(...)` call sites are unaffected. `fromHandle` keeps its
  non-null `Resource` return.
- **`Resource.asObject()` is dropped** (desktop-only, 0 callers). It was already documented as a
  compatibility alias: `Resource` inherits `GodotObject`'s surface directly, so call
  `setMeta`/`connect`/`callDeferred` on the resource, or `GodotObject(resource.handle)` if you really
  need the base wrapper.
- **The two `duplicate` parameter names follow the generator** (named-argument call sites only):
  `duplicate(deep: Boolean = false)` (was `subresources`) and
  `duplicateDeep(deepSubresourcesMode: Long = 1L)` (was `mode: Long = DEEP_DUPLICATE_INTERNAL` — the
  same value; the `DEEP_DUPLICATE_NONE`/`INTERNAL`/`ALL` constants stay). Both also gain the standard
  generated **self-return collapse**: when the engine hands back the same object, the +1 return-slot
  reference is released through `RefCounted.releaseHandle` and `this` is returned instead of a second
  wrapper — the lifetime fix every other generated `RefCounted` return already had.
- **`copyFromResource(resource: Resource?): Long` and four `resource*` properties arrive on desktop**:
  `resourcePath`, `resourceName`, `resourceLocalToScene`, `resourceSceneUniqueId` — each over the
  `get*`/`set*`/`is*` pair desktop already had. The coverage page's `Resource` row goes from `21/22`
  to `22/22`.
- **`Resource.generateSceneUniqueId()` loses `@JvmStatic`**, which the whole shared tree does for
  Godot statics (see `GLTFDocument`). Kotlin call sites are unchanged; a Java caller writes
  `Resource.Companion.generateSceneUniqueId()`.
- **The lifetime policy is unchanged.** Every `Resource` method still opens with `checkOpen()` and
  every nullable Object argument still goes through `requireOpenHandle()` — the generator emits both
  for `RefCounted`-derived classes (480 shared files already do), so retiring the hand file loses no
  guard. The refcount policy itself still comes from the per-platform `RefCounted` root, which stays
  hand-shaped until P3'.
- `ObjectCalls`: the shared tree reaches one more helper, `ptrcallWithTwoLongArgsRetInt` (behind
  `Viewport.getRenderInfo`), already present on both platforms and now
  `actual` on both; the common `expect object ObjectCalls` grows 1414 → 1415. No new native path.
- **Group A of task 117 P1' is complete.** The wrapper parity gate drops both classes from
  `HAND_SHAPED` (9 → **7 classes**) and their 23 allowlist lines go (126 → **103**): Viewport 12,
  Resource 11. The seven left are the group-B four (`Image`, `PlaneMesh`, `Tweener`, `StaticBody3D`)
  and the three roots (`GodotObject`, `RefCounted`, `GodotCallable`). The shared tree grows
  1001 → 1003 classes and `PER_PLATFORM_WRAPPERS` shrinks 34 → 32.

### Changed — `Button`, `LineEdit`, `Range`, `Slider` generated once (task 117 P1'(c), 2/3) — **desktop source break**

- Four more classes are generated once into the shared wrapper tree (`src/sharedApi/.../api/<Class>.kt`);
  both per-platform copies are deleted and `PER_PLATFORM_WRAPPERS` loses all four entries. For each of
  the four the shared draft is signature-identical to the committed iOS copy, so **iOS sees no change**
  and everything below lands on desktop scripts.
- **Twelve properties take the generator's names (D17).** The desktop hand copies had spelled them
  after the Kotlin accessor; the generator names them after the Godot property, which is what the iOS
  copies already used. `rg` over `kanama-demos`, `example_project` and `templates` found **zero** callers
  of the old spellings, so there are no aliases — the old names are simply gone. Old desktop name →
  generated name, with the accessor pair that proves the pairing:

  | class | old desktop name | now (both platforms) | accessors |
  |---|---|---|---|
  | `Button` | `buttonIcon: Texture2D?` | `icon: Texture2D?` | `getButtonIcon` / `setButtonIcon` |
  | `Button` | `textAlignment: Long` | `alignment: Long` | `getTextAlignment` / `setTextAlignment` |
  | `LineEdit` | `horizontalAlignment: Long` | `alignment: Long` | `getHorizontalAlignment` / `setHorizontalAlignment` |
  | `LineEdit` | `placeholder: String` | `placeholderText: String` | `getPlaceholder` / `setPlaceholder` |
  | `LineEdit` | `expandToTextLengthEnabled: Boolean` | `expandToTextLength: Boolean` | `isExpandToTextLengthEnabled` / `setExpandToTextLengthEnabled` |
  | `LineEdit` | `caretBlinkEnabled: Boolean` | `caretBlink: Boolean` | `isCaretBlinkEnabled` / `setCaretBlinkEnabled` |
  | `LineEdit` | `caretMidGraphemeEnabled: Boolean` | `caretMidGrapheme: Boolean` | `isCaretMidGraphemeEnabled` / `setCaretMidGraphemeEnabled` |
  | `Range` | `min: Double` | `minValue: Double` | `getMin` / `setMin` |
  | `Range` | `max: Double` | `maxValue: Double` | `getMax` / `setMax` |
  | `Range` | `useRoundedValues: Boolean` | `rounded: Boolean` | `isUsingRoundedValues` / `setUseRoundedValues` |
  | `Range` | `expRatio: Boolean` | `expEdit: Boolean` | `isRatioExp` / `setExpRatio` |
  | `Slider` | `ticks: Int` | `tickCount: Int` | `getTicks` / `setTicks` |

  Every pair keeps its type (no int-width change anywhere in this group), and the underlying `get*`/`set*`
  methods are untouched — a script that called the methods instead of the property needs no edit.
- **`Range.valueChanged(newValue: Double)` is dropped.** Despite the name it was not the `value_changed`
  signal and not a rename of anything: the desktop hand copy bound Godot's **private** `Range._value_changed`
  virtual, which the generator does not emit for any class. Nothing in the repo or the demos called it,
  and `Range.Signals.valueChanged` (the signal name constant, `"value_changed"`) is unchanged. Its removal
  is why the coverage page's `Range` row goes from `24/23` (104.3%) to `23/23`.
- **`Range.ratio` arrives** — `var ratio: Double` over the `getAsRatio()` / `setAsRatio()` pair desktop
  already had — and `Range.setPage`'s parameter is the generator's `pagesize` (was `pageSize`), which
  matters only to a named-argument call site.
- **`LineEdit.getMenu()` returns `PopupMenu?`** (was non-null `PopupMenu` on desktop) — the generator's
  nullability for every Object return, as D2 anticipated. 0 callers in the repo or the demos.
- **Companion constants and nested `Signals` arrive on desktop**, all previously iOS-only: `LineEdit` gains
  its nested `object Signals` (`textChanged`, `textChangeRejected`, `textSubmitted`, `editingToggled`) and
  43 companion constants (`MENU_*`, `KEYBOARD_TYPE_*`, `EXPAND_MODE_*`); `Slider` gains its nested
  `object Signals` (`dragStarted`, `dragEnded`) and four `TICK_POSITION_*` constants; `Button` gains
  nothing beyond the two renames. `Range` and `Slider` also gain the standard
  `fromHandle` / `wrap` companion pair every generated class has.
- The wrapper parity gate drops all four from `HAND_SHAPED` (13 → 9 classes) and their 81 allowlist lines
  go (207 → 126): LineEdit 55, Range 13, Slider 9, Button 4. The shared tree grows 997 → 1001 classes and
  `PER_PLATFORM_WRAPPERS` shrinks 38 → 34. No new `ObjectCalls` helper is referenced (the common
  `expect object` stays at 1414) and no new native path.

### Changed — `TabBar`, `AnimationPlayer`, `Light3D`, `StandardMaterial3D` generated once (task 117 P1'(c), 1/3) — **desktop source break**

- Four more classes are generated once into the shared wrapper tree (`src/sharedApi/.../api/<Class>.kt`)
  instead of living as two per-platform copies; both copies are deleted and `PER_PLATFORM_WRAPPERS`
  loses all four entries. The generated shape wins on both platforms (D9/D12/D17). iOS sees no
  signature change at all — for every one of the four the shared draft was byte-for-signature
  identical to the committed iOS copy. Everything below is therefore a **desktop** change.
- `StandardMaterial3D` has no class-body members of its own (they are all inherited from
  `BaseMaterial3D`), so retiring it needed only a `FACTORY_HELPERS["StandardMaterial3D"] =
  FactorySpec(True)` row: `StandardMaterial3D.create()` is generated for every platform now and keeps
  compiling. Neither hand copy carried a downcast, so none is rendered. Its **primary constructor is
  public** (it was `internal` on desktop, public on iOS) — the generator's shape for every retiring
  class, as `Mesh`/`PackedScene` took in P1'(a) (D4 as amended by D10).
- **`TabBar.addTab` loses the `icon` default on desktop.** The generated form is
  `addTab(title: String = "", icon: Texture2D?)` — the desktop hand copy had `icon: Texture2D? = null`,
  the iOS copy never did. `tabBar.addTab("Alpha")` no longer compiles: pass the icon explicitly,
  `tabBar.addTab("Alpha", null)`. `example_project/HelloScript.kt` is the in-repo canary and is fixed
  that way. Desktop also **gains** `TabBar.Signals.tabButtonPressed` (`"tab_button_pressed"`) and eight
  companion constants: `ALIGNMENT_LEFT`/`CENTER`/`RIGHT`/`MAX`, `CLOSE_BUTTON_SHOW_NEVER`/
  `SHOW_ACTIVE_ONLY`/`SHOW_ALWAYS`/`MAX`.
- **`AnimationPlayer.Signals.animationFinished` is dropped.** `animation_finished` is declared on
  `AnimationMixer`, not on `AnimationPlayer`, so the generator emits it on the shared
  `AnimationMixer` — which `AnimationPlayer` extends — and the desktop hand copy's duplicate goes.
  Write `AnimationMixer.Signals.animationFinished` (the spelling every demo already uses); the string
  value is unchanged. Desktop also gains defaults the generator reads from the Godot docs, all
  additive: `playBackwards`, `playSection`, `playSectionBackwards`, `playSectionWithMarkers`,
  `playSectionWithMarkersBackwards`, `playWithCapture` and `setSectionWithMarkers` now default their
  `name` / `startMarker` / `endMarker` parameters to `""`.
- **Twelve `Light3D` properties arrive on desktop** (they existed only on the iOS copy, hence twelve
  allowlist lines): `lightAngularDistance`, `lightIndirectEnergy`, `lightIntensityLumens`,
  `lightIntensityLux`, `lightSize`, `lightSpecular`, `lightVolumetricFogEnergy`, `shadowBias`,
  `shadowBlur`, `shadowNormalBias`, `shadowOpacity`, `shadowTransmittanceBias`. Each is
  `var …: Double` over the `get*`/`set*` pair desktop already had, so no new native path.
- **No int-width change and no ergonomic sugar in this group.** The four classes' member sets are
  74/74/74 (TabBar), 66/66/66 (AnimationPlayer), 46 desktop / 58 iOS / 58 draft (Light3D) and
  0/0/0 (StandardMaterial3D) — the twelve Light3D properties are the only member-set delta, and a
  signature-level diff of the draft against both committed copies shows no return or parameter type
  changing width anywhere in the four. No `SHARED_MEMBER_SECTIONS` entry was needed.
- `ObjectCalls`: the shared tree reaches eight more helpers (1413 → 1421 referenced), all of them
  already present on both platforms and now `actual` on both — `ptrcallWithDoubleAndTwoBoolArgs`,
  `ptrcallWithStringNameAndThreeDoubleArgs`, `ptrcallWithStringNameDoubleDoubleBoolArgs`,
  `ptrcallWithStringNameFourDoubleBoolArgs`, `ptrcallWithStringNameThreeDoubleBoolTwoLongArgs`,
  `ptrcallWithThreeStringNameAndDoubleArg`, `ptrcallWithThreeStringNameTwoDoubleBoolArgs`,
  `ptrcallWithTwoStringNameAndDoubleArg`. The common `expect object ObjectCalls` grows 1406 → 1414.
  No new native path.
- The wrapper parity gate drops all four from `HAND_SHAPED` (17 → 13 classes) and their 21 allowlist
  lines go (228 → 207): TabBar 8, Light3D 12, StandardMaterial3D 1, AnimationPlayer 0. The shared tree
  grows 993 → 997 classes and `PER_PLATFORM_WRAPPERS` shrinks 42 → 38.

### Changed — `Node` generated once (task 117 P1'(b2)) — **desktop source break**

- `Node` is generated once into the shared wrapper tree (`src/sharedApi/.../api/Node.kt`) instead of
  being hand-written on desktop (`src/jvmMain/.../api/Node.kt`, 2,285 lines) and generated on iOS
  (`src/iosMain/.../api/Node.kt`, 1,321 lines). Both copies are deleted and `PER_PLATFORM_WRAPPERS`
  loses its `Node` entry. **The generated shape wins** (D15): desktop scripts see the generator's
  int widths, its parameter names and a nullable `getTree()`. iOS scripts see no signature change
  except the two parameter renames below.
- **The desktop ergonomic helpers are shared members now** (`SHARED_MEMBER_SECTIONS["Node"]`), with
  desktop's names, overloads and signatures, so every call site keeps compiling on both platforms:
  `getNodeAsOrNull(path, expectedClass, wrapper)`, `getAsOrNull(path, wrapper)`,
  `requireAs(path, wrapper)`, `requireNodeAs(path, expectedClass, wrapper)` (each in a `String` and a
  `NodePath` overload), `callLocalRpc(method, vararg extraArgs)`, and the string-path overloads
  `getNodeOrNull(String)`, `getNode(String)`, `hasNode(String)`, `hasNodeAndResource(String)`,
  `getNodeAndResource(String)` (each spelled `…(NodePath(path))` over the generated `NodePath` form).
  **iOS gains** `requireNodeAs` (both overloads), the `NodePath` overloads of `getNodeAsOrNull`, and
  `getNodeAndResource(String)`; **desktop keeps** everything it had.
- **`Node.getTree()` returns `SceneTree?`** on both platforms — the b1 non-null shim
  (`requireNotNull(SceneTree.wrap(...))`) and its `IOS_SECTION_REPLACED_METHODS` entry are gone, so
  `get_tree()` is generated like every other Object return. `self.getTree().quit()` no longer
  compiles: write `self.getTree()?.quit()`, or `SceneTree.quit()` (the companion entry point, which
  resolves the live tree itself and throws a named error when there is none).
- **Nine `Long` → `Int` width changes plus `duplicate(flags)`** — ten member signatures in all (the
  shared tree maps Godot `int32` to `Int`; the hand-written desktop file used `Long` everywhere, the
  iOS copy already had these):

  | old desktop hand `Node` | now (both platforms) |
  |---|---|
  | `getChildCount(includeInternal: Boolean = false): Long` | `getChildCount(includeInternal: Boolean = false): Int` |
  | `getIndex(includeInternal: Boolean = false): Long` | `getIndex(includeInternal: Boolean = false): Int` |
  | `getMultiplayerAuthority(): Long` | `getMultiplayerAuthority(): Int` |
  | `getPhysicsProcessPriority(): Long` | `getPhysicsProcessPriority(): Int` |
  | `getProcessPriority(): Long` | `getProcessPriority(): Int` |
  | `getProcessThreadGroupOrder(): Long` | `getProcessThreadGroupOrder(): Int` |
  | `setPhysicsProcessPriority(priority: Long)` | `setPhysicsProcessPriority(priority: Int)` |
  | `setProcessPriority(priority: Long)` | `setProcessPriority(priority: Int)` |
  | `setProcessThreadGroupOrder(order: Long)` | `setProcessThreadGroupOrder(order: Int)` |
  | `duplicate(flags: Long = DUPLICATE_DEFAULT): Node?` | `duplicate(flags: Int = 15): Node?` |

  The `DUPLICATE_*` companion constants stay `Long` (they are `@GlobalScope`-style flag constants,
  `const val DUPLICATE_DEFAULT: Long = 15L`), so **`duplicate(Node.DUPLICATE_SIGNALS or Node.DUPLICATE_GROUPS)`
  no longer compiles** — use `duplicate()` for the default, or `.toInt()` on the flag expression.
  A `Long`-typed counter fed from `getChildCount()` / `getIndex()` needs `.toLong()`, and `?: -1L`
  fallbacks become `?: -1`.
- **Dropped:** the desktop-only convenience overload `getChild(idx: Long, includeInternal: Boolean = false)`
  — the generated `getChild(idx: Int, includeInternal: Boolean = false)` is the only form now (pass
  an `Int`, or `.toInt()`).
- **`Node.createTween()` is no longer a member, and no longer `open`.** The shared tree cannot host
  it (iOS has no `Tween.wrap`), so it is a generated **extension** on both platforms:
  `src/jvmMain/.../api/Node.jvm.kt` (the iOS-gap companion) on desktop/Android and
  `src/iosMain/.../api/Node.ios.kt` (from a new `IOS_EXTENSION_SECTIONS["Node"]`) on iOS. Call sites
  are unchanged apart from the import (`import net.multigesture.kanama.api.createTween`), but a
  script subclass can no longer `override fun createTween()` — nothing in the repo did since
  P1'(b1), when `SceneTree` stopped being a `Node`. The iOS Shape Gap page goes from 1 class / 2
  waiting members to 2 classes / 3.
- **Parameter names follow the generator** (they matter to named-argument call sites):
  `addChild(node, forceReadableName, internalValue)` (was `internalMode`; its default changes from
  the constant `INTERNAL_MODE_DISABLED` to the literal `0L` — the same value),
  `setEditorDescription(editorDescription)` (was `description`). The iOS `ctor` parameter of
  `getAsOrNull`/`requireAs` is now desktop's `wrapper`.
- **Thirteen generated properties arrive on desktop** (they existed only on the iOS copy):
  `autoTranslateMode`, `editorDescription`, `multiplayer`, `name`, `physicsInterpolationMode`,
  `processMode`, `processPhysicsPriority`, `processPriority`, `processThreadGroup`,
  `processThreadGroupOrder`, `processThreadMessages`, `sceneFilePath`, `uniqueNameInOwner` — plus
  three companion constants: `NOTIFICATION_APPLICATION_PIP_MODE_ENTERED`,
  `NOTIFICATION_APPLICATION_PIP_MODE_EXITED`, `NOTIFICATION_WM_OUTPUT_MAX_LINEAR_VALUE_CHANGED`.
- One behaviour detail: the `requireAs` / `requireNodeAs` failure message still reads
  `Required node 'x' was not found under Node#12345`, but the class name is read through the Variant
  `call("get_class")` seam instead of desktop's `GodotObject.getClassName()`, which iOS does not
  have (the roots stay per-platform until P3'). Error path only.
- `getTree()` on iOS also changes transport: the retired iOS section called the `IosGodot.nodeGetTree` C shim,
  the generated form is the `Node.get_tree` ptrcall every other Object-returning method uses (device-proven by
  the Match3 + third-person smokes, which call `getTree()` on every quit).
- `ObjectCalls`: the shared tree calls nine more helpers (all newly referenced because `Node` moved in), so
  `ptrcallWithBoolArgRetTypedObjectList` and `ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList`
  (behind `getChildren` and `findChildren`) are `actual` on both platforms now — the desktop file
  gained the generic `…RetTypedObjectList` form (its `…RetTypedNodeList` twin delegates to it) and
  the two hand-written iOS helpers were renamed to desktop's canonical `boolArg` / `wrapper`
  parameters. The common `expect object ObjectCalls` grows 1397 → 1406 members. No new native path.
- The wrapper parity gate drops `Node` from `HAND_SHAPED` (18 → 17 classes) and its 35 allowlist
  lines go (263 → 228 entries; the gate's divergence count 265 → 228, two more than the line count
  because the `getAsOrNull` and `requireAs` parameter renames each produced two findings against one
  line). The drift gate's `open fun createTween()` probe — which locked the task-103 "F2 fix" — now
  asserts that `IOS_EXTENSION_SECTIONS['Node']` carries `createTween`, the invariant that actually
  keeps `Node.create_tween` reachable on iOS.
- `example_project/HelloScript.kt` is the in-repo canary: `getTree()?.getNodeCount() ?: -1` and two
  `?: -1L` → `?: -1` fallbacks on `getChildCount()`. `templates/starter/HelloScript.kt` needed no
  change (`getNodeAsOrNull` is unchanged). The KSP processor's hard-coded
  `net.multigesture.kanama.api.Node(instance.godotObject).rpc/rpcId/callLocalRpc` emissions
  (`KanamaProcessor.kt:2019,2024,2031`, `IosScriptCodeEmitter.kt:570,577,583`) are unaffected: the
  generated class keeps the public `Node(handle: GodotHandle)` constructor and all three members.

### Changed — `SceneTree` generated once, `SceneTreeHandle` retired (task 117 P1'(b1))

- `SceneTree` is generated once into the shared wrapper tree (`src/sharedApi/.../api/SceneTree.kt`)
  as `class SceneTree(handle: GodotHandle) : MainLoop(handle)` — Godot's real chain. It replaces
  **three** hand-written things: the desktop singleton `object SceneTree`
  (`src/jvmMain/.../api/SceneTree.kt`, 50 `@JvmStatic` entry points over the live main loop), the
  desktop placeholder `class SceneTreeHandle : MainLoop` the generator used to return from
  `Node.getTree()`, and the iOS `class SceneTree(handle) : Node(handle)` inside `IosGodotApi.kt`
  (the wrong chain). All three files/blocks are deleted and `PER_PLATFORM_WRAPPERS` loses its
  `SceneTree` entry; `SPECIAL_OBJECT_WRAPPER_TYPES` loses the `SceneTree -> SceneTreeHandle` line, so
  the generator types `get_tree()` as `SceneTree?` by itself.
- **`Node.getTree()` returns the shared `SceneTree`, still non-null** on both platforms
  (`requireNotNull(SceneTree.wrap(...)) { "Node.getTree(): not inside a SceneTree" }`), so
  `self.getTree().quit()` and friends keep compiling. The two hand-shaped `Node` files are otherwise
  untouched; `Node` itself retires in a later parcel.
- **Every entry point of the desktop `object` survives as a companion member of the same name**,
  delegating to `SceneTree.active()` — the running tree, resolved through `Engine.get_main_loop()`
  via `ObjectCalls` and checked with `checkNotNull` + `isClass("SceneTree")`, so a missing or
  non-`SceneTree` main loop throws with a message instead of faulting. `SceneTree.quit()`,
  `SceneTree.unloadCurrentScene()`, `SceneTree.delaySeconds(...)`, `SceneTree.setGroup(...)` and the
  rest read exactly as before from Kotlin. `val SceneTree.root: Window` stays **non-null** on the
  companion (the generated *instance* property `root` is `Window?`, like every generated object
  property).
- **The companion twins carry no `@JvmStatic`.** A `@JvmStatic` companion member compiles to a
  static method on `SceneTree` itself, which collides with the instance method of the same JVM
  signature (`quit(I)V`, `isPaused()Z`, …). Kotlin call sites are unaffected — `SceneTree.quit()`
  resolves to the companion member either way — but **a Java caller** now needs
  `SceneTree.Companion.quit()`. `active()`, `createTimerHandle(...)` and `createTweenHandle()` have
  no instance twin and stay `@JvmStatic`.
- **Signatures whose types changed** (the companion twin is typed like the generated instance member
  it delegates to, which is the generator's int-width mapping and its wrapper returns):

  | old desktop `object SceneTree` | now (instance and companion) |
  |---|---|
  | `getNodeCount(): Long` | `getNodeCount(): Int` |
  | `getNodeCountInGroup(name: String): Long` | `getNodeCountInGroup(name: String): Int` |
  | `quit(exitCode: Long = 0)` | `quit(exitCode: Int = 0)` |
  | `notifyGroup(groupName: String, notification: Long)` | `notifyGroup(groupName: String, notification: Int)` |
  | `notifyGroupFlags(flags: Long, groupName: String, notification: Long)` | `notifyGroupFlags(flags: Long, groupName: String, notification: Int)` |
  | `getRoot(): GodotHandle` | `getRoot(): Window?` |
  | `getCurrentScene(): GodotHandle` | `getCurrentScene(): Node?` |
  | `getFirstNodeInGroup(name: String): GodotHandle` | `getFirstNodeInGroup(name: String): Node?` |
  | `getEditedSceneRoot(): GodotHandle` | `getEditedSceneRoot(): Node?` |
  | `setCurrentScene(nodeObject: GodotHandle)` | `setCurrentScene(childNode: Node)` |
  | `setEditedSceneRoot(nodeObject: GodotHandle)` | `setEditedSceneRoot(scene: Node)` |
  | `queueDelete(nodeObject: GodotHandle)` | `queueDelete(obj: GodotObject)` |
  | `changeSceneToPacked(packedSceneObject: GodotHandle): Long` | `changeSceneToPacked(packedScene: PackedScene): Long` |
  | `changeSceneToNode(nodeObject: GodotHandle): Long` | `changeSceneToNode(node: Node): Long` |
  | `setMultiplayer(multiplayerApiObject: GodotHandle, rootPath: String = "")` | `setMultiplayer(multiplayer: MultiplayerAPI?, rootPath: NodePath = NodePath(""))` |
  | `getMultiplayer(forPath: String = ""): GodotHandle` | `getMultiplayer(forPath: NodePath = NodePath("")): MultiplayerAPI?` |

  Unchanged: `isPaused`, `setPaused`, `getFrame(): Long`, `changeSceneToFile(path): Long`,
  `reloadCurrentScene(): Long`, `unloadCurrentScene()`, `hasGroup`, `getNodesInGroup(name): List<Node>`,
  `callGroup`, `callGroupFlags`, `setGroup`, `setGroupFlags`, `createTimer(...): SceneTreeTimer?`,
  `createTimerHandle(...): GodotHandle`, `createTweenHandle(): GodotHandle`, `isMultiplayerPollEnabled`,
  `setMultiplayerPollEnabled`, the accessibility/auto-accept-quit/quit-on-go-back/debug-hint pairs and
  `is/setPhysicsInterpolationEnabled`. The `GROUP_CALL_*` constants and the `Signals` object are NEW on both platforms (neither retired copy declared them; additive).
- **`SceneTree.createTween()` and `SceneTree.getProcessedTweens()` are desktop/Android-only** and
  must now be imported by name (`import net.multigesture.kanama.api.createTween`). iOS hosts no
  `Tween` wrapper with a `wrap` helper, so the generator puts the instance forms in the
  `src/jvmMain/.../api/SceneTree.jvm.kt` gap companion and the static forms come from
  `DESKTOP_EXTENSION_SECTIONS`. The iOS Shape Gap page goes from 0 waiting members to 1 class / 2
  members (`SceneTree` waits on `ptrcallNoArgsRetTypedObjectList` and a `Tween` wrapper). iOS keeps a
  `SceneTree.createTween()` extension of its own in `src/iosMain/.../api/SceneTree.ios.kt`, carried
  over from the retired class's `override fun createTween()` (the task-103 "F2 fix") — it uses
  `SceneTree.create_tween`, never `Node.create_tween`. `Node.createTween()` stays `open` on both
  platforms even though nothing overrides it any more.
- **`SceneTree.delaySeconds(...)` is now the same frame-driven wait on both platforms.** The desktop
  body — `MainThread.awaitNextFrame()` once per engine frame, `Time.getTicksUsec()` deltas, skipping
  time while `paused` unless `processAlways`, scaling by `Engine.time_scale` unless
  `ignoreTimeScale` — is a shared member, and the companion form delegates to it. **iOS behaviour
  change:** iOS previously implemented `delaySeconds(seconds: Double)` as a wall-clock
  `kotlinx.coroutines.delay(ms)`, which ignored both pause and time scale; it now waits in engine
  frames like desktop (`KanamaIosRuntime.frame()` resumes the parked continuations every frame).
  iOS also gains the three optional parameters. Proven by the iPhone 12 device smokes (Match3 and
  third-person), both of which await `delaySeconds` before quitting.
- `getTree().setPaused(b)` keeps working on both platforms: `setPaused` is shared sugar over the
  generated `setPause`. The iOS-only `quit(exitCode: Long)` overload is gone (`quit(Int)` remains,
  with its default); no call site passed a `Long`.
- Two changes worth knowing that are not visible in the signatures: every static entry point now resolves the live
  tree through `SceneTree.active()` (a `get_main_loop` call, a wrapper construction that captures the instance id,
  and an `is_class` check) instead of the retired object's single cached-segment call — per-frame callers of
  `SceneTree.root` / `SceneTree.isPaused()` pay that; and `callGroup`/`callGroupFlags`/`setGroup`/`setGroupFlags` now go
  through cached-methodbind Variant calls (`callWithVariantArgs` and two new iOS helpers) instead of a name-resolved
  `Object.call("call_group", …)` — a new path on iOS for `getTree().callGroup(…)` (dodge-the-creeps), covered by the full
  iPhone gate's dodge smoke rather than the two per-parcel smokes.
- `ObjectCalls`: the shared tree now calls six more helpers, which gained `actual` in the
  hand-written desktop file (`ptrcallWithStringNameStringAndVariantArg`,
  `ptrcallWithUInt32StringNameStringVariantArgs`, `ptrcallWithDoubleAndThreeBoolArgsRetObject`,
  `ptrcallWithNodePathArgRetObject`, `ptrcallWithUInt32StringNameAndIntArgs`,
  `ptrcallWithObjectAndNodePathArg`, plus the four Node3D ones above). Two hand-written helpers were
  reconciled so the `expect` can declare them: desktop
  `ptrcallWithObjectAndNodePathArg(..., path: String)` takes a `NodePath` now (its only caller was
  the retired `object SceneTree`), and the iOS
  `ptrcallWithDoubleAndThreeBoolArgsRetObject(..., a0, a1, a2, a3)` parameters are renamed to
  desktop's canonical `value, first, second, third`. The desktop-only
  `ptrcallWithNodePathArgRetObject(..., path: String)` overload, which the hand-shaped `Node` still
  uses, is recorded in `EXPECT_OVERLOAD_EXCLUSIONS` beside the existing `…RetBool` one. The common
  `expect object ObjectCalls` grows 1391 -> 1397 members.
- Generator: `IOS_SECTION_REPLACED_METHODS` is new — a custom section that declares the same Kotlin
  name AND parameter list as a generated member (here `Node.getTree()`, non-null in the section
  versus `SceneTree?` generated) would otherwise collide as conflicting overloads; the pair is
  recorded with its reason and the generated form is skipped on the iOS render target.
- Tooling fixes the same change exposed: the drift gate's iOS collision probe moved from `SceneTree`
  (no longer a collision class) to `ResourceLoader`, and `scan_wrappers` now strips the `.jvm` /
  `.ios` suffix from a companion file's stem, so a `<Class>.jvm.kt` member is credited to its class
  — `SceneTree` reads 47/47 on the generated coverage page, and `GDExtensionManager` (6/7 -> 7/7)
  and `OpenXRAPIExtension` (49/51 -> 51/51) stop under-reporting their desktop-only-by-design
  members.
- `example_project/HelloScript.kt` imports `createTween` and `getProcessedTweens` by name; nothing
  else in the repository changed shape. `SceneTree` was never in the wrapper parity gate's
  `HAND_SHAPED` list, so the gate stays at 18 classes / 265 allowlisted divergences.

### Changed — wrapper classes generated once: `Node3D` (task 117 P1'(b1))

- `Node3D` is generated once into the shared wrapper tree (`src/sharedApi/.../api/Node3D.kt`)
  instead of being hand-written on desktop (`src/jvmMain/.../api/Node3D.kt`) and generated on iOS
  (`src/iosMain/.../api/Node3D.kt`); its `PER_PLATFORM_WRAPPERS` entry is gone. The two copies
  already had the same 88 members, and every declaration is identical to the shared
  render — the whole transform surface (`setTransform`/`getTransform`, `setPosition`/`getPosition`,
  `setRotation`/`getRotation`, `setRotationDegrees`, `setScale`/`getScale`, `setQuaternion`,
  `setBasis`, `setGlobalTransform`/`getGlobalTransform`, `getParentNode3d`, `lookAt`,
  `lookAtFromPosition`, `translate`/`translateObjectLocal`, `rotate`/`rotateX`/`rotateY`/`rotateZ`,
  `rotateObjectLocal`, `scaleObjectLocal`, `globalRotate`/`globalScale`/`globalTranslate`,
  `toLocal`/`toGlobal`, `orthonormalize`, `setIdentity`, the visibility family
  (`show`/`hide`/`setVisible`/`isVisible`/`isVisibleInTree`), `setNotifyTransform`,
  `setAsTopLevel`, `forceUpdateTransform`, `setDisableScale`, the gizmo helpers and the
  `setRotationEditMode`/`setRotationOrder` pair) and all 17 generated properties (`transform`,
  `globalTransform`, `position`, `rotation`, `rotationDegrees`, `quaternion`, `basis`, `scale`,
  `globalPosition`, `globalBasis`, `globalRotation`, `globalRotationDegrees`, `topLevel`,
  `visible`, `visibilityParent`, `rotationEditMode`, `rotationOrder`, …), the nested
  `object Signals { visibilityChanged }`, the `NOTIFICATION_*` constants and the companion's
  `fromHandle`/`wrap` — so callers such as `example_project/WrapperConvenienceProbe.kt`
  (`node3dConveniences(node: Node3D)`) and `example_project/SelfSmoke.kt` are unaffected.
- No member arrived or left, no body changed, no default argument changed and no int width changed:
  desktop, iOS and the shared render each carry the same 18 `Long` occurrences and the same
  `up: Vector3 = Vector3.UP` / `useModelFront: Boolean = false` defaults on `lookAt` and
  `lookAtFromPosition`. The primary constructor was already
  `open class Node3D(handle: GodotHandle) : Node(handle)` on both platforms, so there is no source
  break. The iOS copy gains the KDoc it never carried and loses its wildcard
  `net.multigesture.kanama.binding.runtime.*` import; desktop's import block is reordered and gains `kotlin.jvm.JvmStatic` (for `fromHandle`); the `@JvmName`
  imports the shared render uses.
- Because the shared tree now calls them, four desktop `ObjectCalls` helpers gained the `actual`
  marker — `ptrcallWithObjectIntTransform3DArgs`, `ptrcallWithThreeVector3AndBoolArgs`,
  `ptrcallWithTwoVector3AndBoolArgs`, `ptrcallWithVector3AndDoubleArg` — and the common
  `expect object ObjectCalls` grew from 1387 to 1391 members. No new native call path: all four were
  already implemented on both platforms.
- The wrapper parity gate no longer lists `Node3D` (19 → 18 classes, 265 allowlisted divergences —
  unchanged, because the class contributed none).

### Changed — wrapper classes generated once: `BaseMaterial3D` (task 117 P1'(a))

- `BaseMaterial3D` is generated once into the shared wrapper tree
  (`src/sharedApi/.../api/BaseMaterial3D.kt`) instead of being hand-written on desktop
  (`src/jvmMain/.../api/BaseMaterial3D.kt`) and generated on iOS
  (`src/iosMain/.../api/BaseMaterial3D.kt`); its `PER_PLATFORM_WRAPPERS` entry is gone. The two
  copies already had the same 285 members, and every one keeps its name, signature, default
  arguments and body — the whole `set*`/`get*` surface (`setAlbedo`/`getAlbedo`,
  `setTexture`/`getTexture`, `setFeature`/`getFeature`, `setFlag`/`getFlag`,
  `setTransparency`/`getTransparency`, `setShadingMode`/`getShadingMode`,
  `setCullMode`/`getCullMode`, `setTextureFilter`/`getTextureFilter`, the emission, rim, clearcoat,
  anisotropy, subsurface-scattering, backlight, refraction, detail, UV, billboard, grow,
  proximity-fade, distance-fade and MSDF families) and the 131 generated properties
  (`albedoColor`, `metallic`, `roughness`, `emission`, `transparency`, `shadingMode`, `cullMode`,
  `textureFilter`, …), plus the companion's `TEXTURE_*`, `TEXTURE_FILTER_*`, `DETAIL_UV_*`,
  `TRANSPARENCY_*`, `SHADING_MODE_*`, `FEATURE_*`, `BLEND_MODE_*`, `ALPHA_ANTIALIASING_*`,
  `DEPTH_DRAW_*`, `CULL_*`, `FLAG_*`, `DIFFUSE_*`, `SPECULAR_*`, `BILLBOARD_*`, `TEXTURE_CHANNEL_*`,
  `EMISSION_OP_*` and `DISTANCE_FADE_*` constants and `fromHandle`/`wrap` — so callers are
  unaffected. `example_project/HelloScript.kt`'s `BaseMaterial3D.SHADING_MODE_UNSHADED`,
  `BaseMaterial3D.TRANSPARENCY_ALPHA` and `BaseMaterial3D.CULL_DISABLED` keep their `Long` type and
  values.
- `BaseMaterial3D.fromMaterial(value: Material): BaseMaterial3D?` survives as a generated
  `@JvmStatic` companion helper with the same signature, parameter name and body
  (`if (value.isClass("BaseMaterial3D")) BaseMaterial3D(value.handle) else null`). Since kanama#269
  it is a `FACTORY_HELPERS["BaseMaterial3D"] = FactorySpec(False, (Downcast("fromMaterial",
  "Material", False),))` row, and the row simply moves from the iOS-only universe to the shared one:
  iOS keeps `fromMaterial` and gains `@JvmStatic` (inert on Kotlin/Native). Callers —
  `example_project/WrapperConvenienceProbe.kt` and `godot-4-3d-character-controller-tutorial`'s
  `SophiaSkin.kt` — are unaffected.
- No member arrived or left, no body changed, and no int width changed: all 285 declarations are
  byte-identical across the desktop hand file, the iOS copy and the shared render (69 `Long` and 18
  `Int` occurrences in the signature lines on all three), so `setFeature(feature: Long, …)`,
  `setFlag(flag: Long, …)`, `setTextureFilter(mode: Long)` and their getters keep `Long`. The
  primary constructor was already `open class BaseMaterial3D(handle: GodotHandle) : Material(handle)`
  on both platforms, so there is no source break and no wrapper-parity allowlist line disappears
  (`BaseMaterial3D` had none). `BaseMaterial3D` is `RefCounted`-derived and every member already
  opened with `checkOpen()` on both sides, so no guard was added. No new `ObjectCalls` helper is
  referenced, so the common `expect object ObjectCalls` stays at 1387 members.
- `StandardMaterial3D` stays per-platform (hand/hand, until task 117 P1'(c)) and now extends the
  shared `BaseMaterial3D`; both copies still compile unchanged — each only uses the public primary
  constructor, `handle.segment` and `ObjectCalls.constructObject`.
- The wrapper parity gate no longer lists `BaseMaterial3D` (19 classes, 265 allowlisted divergences —
  unchanged, because the class contributed none).

### Changed — wrapper classes generated once: `ButtonGroup` (task 117 P1'(a))

- `ButtonGroup` is generated once into the shared wrapper tree
  (`src/sharedApi/.../api/ButtonGroup.kt`) instead of being hand-written on desktop
  (`src/jvmMain/.../api/ButtonGroup.kt`) and generated on iOS (`src/iosMain/.../api/ButtonGroup.kt`);
  its `PER_PLATFORM_WRAPPERS` entry is gone. The two copies already had the same five members, and
  every one keeps its name, signature, default arguments and body — `getPressedButton`,
  `getButtons`, `setAllowUnpress`/`isAllowUnpress`, the `allowUnpress: Boolean` property with its
  `@JvmName("allowUnpressProperty")`/`@JvmName("setAllowUnpressProperty")` accessors — and so do the
  nested `object Signals { const val pressed }` and the companion's `fromHandle`/`wrap` helpers, so
  callers are unaffected.
- `ButtonGroup.create()` survives as a generated `@JvmStatic` companion helper with the same
  signature and body (`ButtonGroup(GodotHandle(ObjectCalls.constructObject("ButtonGroup")))`). Since
  kanama#269 it is a `FACTORY_HELPERS["ButtonGroup"] = FactorySpec(True)` row, and the row simply
  moves from the iOS-only universe to the shared one: iOS keeps `create()`, gains `@JvmStatic`
  (inert on Kotlin/Native) and swaps its `MemorySegment.ofAddress(IosGodot.constructObject(...))`
  body for the desktop `ObjectCalls.constructObject("ButtonGroup")` one. The caller —
  `tps-demo-kanama`'s `TpsScenes.kt` (`fun buttonGroup(): ButtonGroup = ButtonGroup.create()`) — is
  unaffected.
- No member arrived or left, no int width changed (all five members are `Boolean`/object-typed; the
  class declares no `Long` or `Int`), the primary constructor was already public on both platforms,
  and the nested/companion shapes match, so there is no source break and no wrapper-parity allowlist
  line disappears (`ButtonGroup` had none). `ButtonGroup` is `RefCounted`-derived and every member
  already opened with `checkOpen()` on both sides, so no guard was added.
- `getButtons()` now calls the generic
  `ObjectCalls.ptrcallNoArgsRetTypedObjectList(bind, segment, BaseButton::wrap)` on desktop too,
  instead of the desktop-only `ptrcallNoArgsRetTypedBaseButtonList`; the two have identical bodies
  (`callArrayReturn` + `BuiltinTypes.readArrayObjects`), so the returned `List<BaseButton>` is the
  same. iOS already used the generic helper. No new `ObjectCalls` helper is referenced, so the
  common `expect object ObjectCalls` stays at 1387 members.
- The wrapper parity gate no longer lists `ButtonGroup` (19 classes, 265 allowlisted divergences —
  unchanged, because the class contributed none).

### Changed — wrapper classes generated once: `Camera3D` (task 117 P1'(a))

- `Camera3D` is generated once into the shared wrapper tree instead of being hand-written on desktop
  and generated on iOS. The two copies already had the same 64 members, and every one keeps its
  name, signature, default arguments and body — the `projectRay*`/`projectPosition`/
  `unprojectPosition` family, `isPositionBehind`, `getCameraTransform`/`getCameraProjection`,
  `getFrustum`, `makeCurrent`/`clearCurrent`, `setPerspective`/`setOrthogonal`/`setFrustum`, the
  `fov`/`near`/`far`/`size`/`projection`/`current`/`cullMask`/`environment`/`attributes`/
  `compositor`/`dopplerTracking`/`keepAspect`/`hOffset`/`vOffset`/`frustumOffset` properties,
  `setCullMaskValue`/`getCullMaskValue`, and the companion's eight `PROJECTION_*`/`KEEP_*`/
  `DOPPLER_TRACKING_*` constants plus `fromHandle`/`wrap` — so callers are unaffected.
- `Camera3D.create()` survives as a generated `@JvmStatic` companion helper with the same signature
  and body. Since kanama#269 it is a `FACTORY_HELPERS["Camera3D"] = FactorySpec(True)` row, and the
  row simply moves from the iOS-only universe to the shared one: iOS keeps `create()` and gains
  `@JvmStatic` (inert there) and the desktop body `ObjectCalls.constructObject("Camera3D")` in place
  of `IosGodot.constructObject`. Callers — `godot-4-3d-third-person-controller`'s `CameraMode.kt`
  and `example_project`'s `WrapperConvenienceProbe.kt` — are unaffected.
- No member arrived or left, no int width changed, no desktop body changed (the one iOS body change
  is `create()`'s construct call above) and the primary constructor was already public on both
  platforms, so there is no source break and no wrapper-parity allowlist line
  disappears (`Camera3D` had none). `Camera3D` is not `RefCounted`-derived, so no `checkOpen()`
  guard was added.
- Seven `ObjectCalls` helpers that only this class calls from the shared tree
  (`ptrcallNoArgsRetPlaneList` for `getFrustum`, `ptrcallWithThreeDoubleArgs` for `setPerspective`/
  `setOrthogonal`, `ptrcallWithDoubleVector2TwoDoubleArgs` for `setFrustum`,
  `ptrcallWithVector2ArgRetVector3` and `ptrcallWithVector2AndDoubleArgRetVector3` for the ray/
  position projections, `ptrcallWithVector3ArgRetBool` for `isPositionBehind`/`isPositionInFrustum` and
  `ptrcallWithVector3ArgRetVector2` for `unprojectPosition`) are now part of the common
  `expect object ObjectCalls` (1380 → 1387 members), so both platforms declare them as `actual`.
  `ptrcallNoArgsRetPlaneList` is hand-written on both backends (the desktop file has no generated
  region; on iOS it sits above the region), so its `actual` is hand-added on each.

### Changed — wrapper classes generated once: `MeshLibrary` (task 117 P1'(a))

- `MeshLibrary` is generated once into the shared wrapper tree instead of being hand-written on
  desktop and generated on iOS. All 24 members the desktop hand file had keep their names,
  signatures, default arguments and bodies — `createItem`, the `setItem*`/`getItem*` family
  (`Name`, `Mesh`, `MeshTransform`, `MeshCastShadow`, `NavigationMesh`,
  `NavigationMeshTransform`, `NavigationLayers`, `Shapes`, `Preview`), `removeItem`,
  `findItemByName`, `clear`, `getItemList`, `getLastUnusedItemId` — and so do the companion's
  `fromHandle`/`wrap` helpers, so callers are unaffected.
- `MeshLibrary.create()` survives as a generated `@JvmStatic` companion helper with the same
  signature and body (`MeshLibrary(GodotHandle(ObjectCalls.constructObject("MeshLibrary")))`). It is
  a `FACTORY_HELPERS["MeshLibrary"] = FactorySpec(True)` row now, not pasted Kotlin, so one row
  feeds both platforms; iOS gains `create()` (previously it had none) and `@JvmStatic` (inert
  there). Callers — `Starter-Kit-City-Builder`'s `Builder.kt` and `example_project`'s
  `WrapperConvenienceProbe.kt` — are unaffected; the returned wrapper is still owned, so
  `close()`/`use` stays required.
- Gained on desktop/Android: `getItemCount(): Int`, a Godot method the hand file omitted. iOS
  already had it; it was the `MeshLibrary | ios-only | getItemCount` line of the wrapper parity
  allowlist, which is now gone, along with `MeshLibrary | companion-desktop-only | create`.
- No int width changed, no body changed and the primary constructor was already public on both
  platforms, so there is no source break. `MeshLibrary` is `RefCounted`-derived and every member
  already opened with `checkOpen()` on both sides, so no guard was added. No new `ObjectCalls`
  helper was referenced (the common `expect object` stays at 1380 members).

### Changed — wrapper classes generated once: `PhysicsBody3D` (task 117 P1'(a))

- `PhysicsBody3D` is generated once into the shared wrapper tree instead of being hand-written on
  desktop and generated on iOS. All eight members the desktop hand file had keep their names,
  signatures, default arguments and bodies — `moveAndCollide`, `testMove`, `getGravity`,
  `setAxisLock`/`getAxisLock`, `getCollisionExceptions`,
  `addCollisionExceptionWith`/`removeCollisionExceptionWith` — and so do the companion's
  `fromHandle`/`wrap` helpers, so callers are unaffected.
- The six `BODY_AXIS_*` companion constants moved from the generator's
  `IOS_COMPANION_MEMBER_SECTIONS` into a new `SHARED_COMPANION_MEMBER_SECTIONS["PhysicsBody3D"]`
  entry — one section now feeds both platforms. Desktop keeps the spelling it had
  (`const val BODY_AXIS_LINEAR_X: Long = PhysicsServer3D.BODY_AXIS_LINEAR_X`, and so on for
  `LINEAR_Y`/`LINEAR_Z`/`ANGULAR_X`/`ANGULAR_Y`/`ANGULAR_Z`): `PhysicsServer3D` is itself part of the
  shared tree, so the aliases resolve on iOS too and the iOS copy's literals (`1L`…`32L`) become the
  same aliases of the same values. `PhysicsBody3D.BODY_AXIS_ANGULAR_X`/`_Y`/`_Z`
  (`godot-4-3d-third-person-controller`'s `BeetleBot.kt`, `example_project`'s
  `WrapperConvenienceProbe.kt`) keep their `Long` type and values.
- Gained on desktop/Android: the six `axisLock{Linear,Angular}{X,Y,Z}: Boolean` properties the hand
  file omitted, generated as `getAxisLock`/`setAxisLock` pairs with the matching bit flag. iOS
  already had them; they were the six `PhysicsBody3D | ios-only` lines of the wrapper parity
  allowlist, which are now gone.
- No int width changed, no body changed and the primary constructor was already public on both
  platforms, so there is no source break. `getCollisionExceptions()` now calls the generic
  `ObjectCalls.ptrcallNoArgsRetTypedObjectList(bind, segment, PhysicsBody3D::wrap)` instead of the
  desktop-only `ptrcallNoArgsRetTypedPhysicsBody3DList`; the two have identical bodies
  (`callArrayReturn` + `BuiltinTypes.readArrayObjects`), so the returned `List<PhysicsBody3D>` is the
  same. `PhysicsBody3D` is not `RefCounted`-derived, so no `checkOpen()` guard was added.
- `ObjectCalls.ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool` (`testMove`) and
  `ptrcallWithVector3BoolFloatBoolIntArgsRetObject` (`moveAndCollide`) are now part of the common
  `expect object ObjectCalls` (1378 → 1380 members), so both platforms declare them as `actual`.

### Changed — generator: factory/downcast companion helpers are table-driven (task 119 item 33)

- The wrapper generator's `create()` and `from*` downcast companion helpers are rows in one
  `FACTORY_HELPERS` table rendered by `render_factory_helpers`, instead of Kotlin pasted per class
  into `SHARED_COMPANION_MEMBER_SECTIONS` / `IOS_COMPANION_MEMBER_SECTIONS` /
  `DESKTOP_COMPANION_MEMBER_SECTIONS`. All 23 classes that had one moved (8 shared, 12 iOS-only, 3
  desktop-only), so the first two tables are now empty and the iOS one holds only non-factory
  content: `RefCounted`'s `releaseHandle`, `InputEventKey`'s Key constants, `PhysicsBody3D`'s
  BodyAxis flags. A P1'(a) retirement adds a row instead of copying a helper body.
- **No API change.** Every generated signature, annotation and body is byte-identical; the only
  change in the generated files is the one-line comment above each helper — the renderer's uniform
  wording, replacing per-class prose where there was some and added where there was none.
- `check_section_tables` gained `check_factory_helpers`: a `FACTORY_HELPERS` key must be a class the
  generator actually renders, and a companion section that still pastes a helper the table renders is
  a hard error, so the same helper cannot be emitted twice.

### Changed — iOS exports require iOS 15.0; the gate fixture and docs follow Godot 4.7.2's default (task 123)

- The iOS visual-smoke fixture (`scripts/ios_visual_smoke.sh`) exported with `application/min_ios_version="14.0"`, a
  pin carried over from an older Godot, and the docs said 14.0 was the default. Godot 4.7.2's exporter defaults to
  **15.0**, and Xcode 27.0 refuses to build a project whose deployment target is below 15.0 ("range of supported
  deployment target versions is 15.0 to 27.0.x"), so on the current Xcode the device gate could not build at all. The
  fixture now pins 15.0 and the iOS toolchain table says so; the demo corpus makes the same change in its presets. No
  validated device runs anything older than iOS 26, so nothing shipped is affected.

### Changed — wrapper classes generated once: `EditorExportPlatform` (task 117 P1'(a))

- `EditorExportPlatform` is generated once into the shared wrapper tree instead of being hand-written
  on desktop and generated on iOS. All 26 instance members keep their names, signatures, default
  arguments and bodies — `exportProject`, `exportPack`/`exportZip`(`Patch`), `savePack`/`saveZip`(`Patch`),
  `sshRunOnRemote`(`NoWait`), the message accessors — and so does the companion's
  `getForcedExportFiles(preset)` (a Godot static, emitted on both platforms), so callers are
  unaffected. The desktop companion had no hand sugar to move.
- Gained on desktop/Android: the nine companion constants the hand file omitted —
  `EXPORT_MESSAGE_NONE`/`INFO`/`WARNING`/`ERROR` and `DEBUG_FLAG_DUMB_CLIENT`/`REMOTE_DEBUG`/
  `REMOTE_DEBUG_LOCALHOST`/`VIEW_COLLISIONS`/`VIEW_NAVIGATION`, all `Long`. iOS already had them;
  they were the nine `EditorExportPlatform | companion-ios-only` lines of the wrapper parity
  allowlist, which are now gone. `sshRunOnRemote()` also gained the `checkOpen()` guard every other
  member has, so calling it through a closed handle raises
  `IllegalStateException("RefCounted handle is closed")` instead of calling through a freed handle.
- No int width changed and the primary constructor was already public on both platforms, so there is
  no source break.
- Eleven `ObjectCalls` helpers that only this class calls from the shared tree (the
  `ptrcallWithObjectBoolString…` export/save family, `ptrcallWithObjectAndBoolArgRetDictionary` — also
  used by the desktop-only `Image` — `ptrcallWithLongAndTwoStringArgs` and the three
  `ptrcallWithTwoStringPackedStringList…` ssh helpers) are now part of the common
  `expect object ObjectCalls`, so both platforms declare them as `actual`.

### Changed — wrapper classes generated once: `ArrayMesh` (task 117 P1'(a))

- `ArrayMesh` is generated once into the shared wrapper tree instead of being hand-written on desktop
  and generated on iOS. `ArrayMesh.fromResource(value: Resource): ArrayMesh?` survives as a generated
  `@JvmStatic` companion helper with the same signature, so callers
  (`godot-4-3d-third-person-controller`'s `GrassScatter.kt`, `example_project`'s
  `WrapperConvenienceProbe.kt`) are unaffected. The iOS-only copy of that helper was deleted from the
  generator's `IOS_COMPANION_MEMBER_SECTIONS` — one shared entry now feeds both platforms, and iOS
  gains the `@JvmStatic` annotation (inert there).
- Behaviour gained on desktop/Android: `getShadowMesh()` now uses the shared tree's self-return
  collapse — when the engine hands back this same object it releases the extra reference and returns
  `this` instead of minting a second wrapper. `addSurfaceFromArrays()` now calls `checkOpen()` first,
  like every other member, so calling it through a closed handle raises
  `IllegalStateException("RefCounted handle is closed")` instead of calling through a freed handle.
- No int width changed and no signature changed: all 29 members (the `blendShapeMode` / `customAabb` /
  `shadowMesh` properties and the 26 functions from `addBlendShape` to `getShadowMesh`, e.g.
  `surfaceGetArrayLen`, `lightmapUnwrap`, `surfaceUpdateVertexRegion`) keep the exact types, parameter
  names and defaults the desktop hand file had. (`getSurfaceCount` and the `ARRAY_*` / `PRIMITIVE_*` /
  `BLEND_SHAPE_MODE_*` constants are inherited from `Mesh`, generated once since chunk 1.)
  `ArrayMesh`'s primary constructor was already public on both platforms.
- `ObjectCalls.ptrcallWithLongArrayArrayListDictionaryLongArgs` (used by `addSurfaceFromArrays`),
  `ptrcallWithTransform3DAndDoubleArgRetLong` (`lightmapUnwrap`) and `ptrcallWithTwoIntAndByteArrayArg`
  (`surfaceUpdate*Region`) are now part of the common `expect object ObjectCalls`, so both platforms
  declare them as `actual`.

### Changed — wrapper classes generated once: `Font` (task 117 P1'(a))

- `Font` is generated once into the shared wrapper tree instead of being hand-written on desktop and
  generated on iOS. Every member keeps the name, signature, default arguments and body it had in the
  desktop hand file — `getSpacing`, `findVariation`, `getStringSize`, the `draw*` family and the
  `Font.wrap` / `Font.fromHandle` companion helpers the shared tree calls (Control, Theme, Window,
  TextMesh, …) are unchanged, so callers are unaffected. The desktop companion carried no sugar to
  move, so no `SHARED_COMPANION_MEMBER_SECTIONS` entry was needed.
- Gained on desktop/Android: `getPaletteCount()`, `getPaletteName(index)` and
  `getPaletteColors(index)` — three Godot 4.7 methods the hand file omitted, which the generator
  emits on both platforms. iOS already had them; they were the three `Font | ios-only` lines of the
  wrapper parity allowlist, which are now gone.
- No int width changed: `findVariation`'s `faceIndex`/`spacing*` parameters stay `Int`,
  `paletteIndex` stays `Long`, `getSpacing(spacing: Long): Int`, `getFontWeight`/`getFontStretch`
  stay `Int` and `getFontStyle` stays `Long` — the desktop hand file already used the generator's
  width mapping. `Font`'s primary constructor was already public on both platforms, so nothing moved
  there either.
- `ObjectCalls` gained ten `expect`/`actual` helpers that only `Font` calls
  (`ptrcallWithLongArgRetPackedColorList`,
  `ptrcallWithDictionaryIntDoubleTransform2DFourIntDoubleLongPackedColorListArgsRetRID`, the four
  `ptrcallWithRIDVector2String…` draw helpers, the two `…ColorDoubleArgsRetDouble` char helpers and
  the two `ptrcallWithStringLongDouble…RetVector2` string-size helpers): they existed on both
  backends already and are now declared in the common `expect object ObjectCalls`.

### Added — gate: the five iOS PT tag tables must agree (task 119 item 30)

- The iOS ptrcall type tags (`PT_*`) are the wire protocol of the iOS seam — the C shim's dispatch
  switches on those numbers — and the table exists **five times**: the `KANAMA_IOS_PT_*` enum in
  `ios/bootstrap/kanama_ios_shim.c` (the authority), `IOS_PT_TAG_VALUES` in
  `scripts/generate_api_wrapper.py`, the `private const val PT_*` block inside the generated region
  of the iOS `ObjectCalls.kt`, the hand-written `IOS_PT_*` subset in `KanamaIosRuntime.kt`, and the
  common `PT_*` in `BuiltinTags.kt`. Nothing compared any two of them: the drift gate compares the
  generated region by member NAMES only, so a renumber in any one copy compiled everywhere, passed
  every gate, and failed only at the shim's tag dispatch on a phone.
- `scripts/check_pt_tag_tables.py` parses all five (the C enum by walking the enumerators with C's
  implicit-value rule, the generator by importing the dict rather than by regex, the three Kotlin
  copies by regex over comment/string-blanked source) and fails naming the tag and the files on a
  value mismatch, a Kotlin/Python tag the C enum does not declare, two C enumerators resolving to
  the same number, any difference between the generated region and the generator's dict, or a copy
  that does not parse. A tag present only in C is not a failure — `KanamaIosRuntime.kt` and
  `BuiltinTags.kt` are subsets by design — so the PASS line prints all five sizes instead.
- Runs as the `iOS PT tag table parity (five copies, task 119 item 30)` stage of
  `scripts/local_ci.sh`, so it gates every PR through `ci.yml`'s `gate` job.

### Changed — wrapper classes generated once: `PackedScene` (task 117 P1'(a))

- `PackedScene` is generated once into the shared wrapper tree instead of being hand-written on
  desktop and generated on iOS. `PackedScene.create()` survives as a generated companion helper with
  the same signature, so callers are unaffected.
- `PackedScene.pack`'s parameter is named `path` (the Godot doc name the generator uses), not `node`.
  Positional calls — every call site in the repo and the demos — are unaffected; a call that passed
  the argument by name as `pack(node = ...)` must say `pack(path = ...)`.
- `PackedScene`'s primary constructor is now **public** on desktop/Android (the hand file declared it
  `internal`), matching the iOS copy and the rest of the shared tree. `PackedScene.create()` /
  `fromHandle` remain the intended way to obtain one.
- No int width changed: `pack` still returns `Long` and `instantiate(editState: Long = 0L)` keeps its
  `Long` parameter and default (`0L` == `GEN_EDIT_STATE_DISABLED`).

### Fixed — the API coverage page counts the shared tree (task 117 P1'(a))

- `docs/reference/generated/api-coverage.md` counted method coverage only from the desktop directory, so every class
  generated into `src/sharedApi` showed 0/N (Sprite2D, Node2D, Label, … and, after this change, Material/Mesh/PackedScene).
  The scanner now reads the shared tree and companion files like the class scanner already did; the totals rise
  accordingly and are the real numbers.

### Changed — wrapper classes generated once: `Mesh` (task 117 P1'(a))

- `Mesh` is generated once into the shared wrapper tree instead of being hand-written on desktop and
  generated on iOS. `Mesh.fromObject` survives as a generated companion helper with the same
  signature, so callers (Starter-Kit-City-Builder `Builder.kt`) are unaffected.
- `Mesh`'s primary constructor is now **public** on desktop/Android (the hand file declared it
  `internal`), matching the iOS copy and every other class in the shared tree. `Mesh.fromObject` /
  `Mesh.fromHandle` remain the intended way to obtain one.
- No int width changed: the desktop hand file already used `Int` for `getSurfaceCount()` and the
  `surfIdx` parameters, and the `ARRAY_*` / `PRIMITIVE_*` / `BLEND_SHAPE_MODE_*` constants keep their
  `Long` values.
- `ObjectCalls.ptrcallWithIntArgRetArrayList` and `ObjectCalls.ptrcallWithTwoBoolArgsRetObject`
  (used by `surfaceGetBlendShapeArrays` / `createConvexShape`) are now part of the common
  `expect object ObjectCalls`, so both platforms declare them as `actual`.

### Changed — wrapper classes generated once: `Material` (task 117 P1'(a))

- `Material` is no longer hand-written per platform: it is generated once into the shared wrapper
  tree, so desktop/Android and iOS get the same class from the same renderer. `Material.fromResource`
  survives as a generated companion helper with the same signature, so callers are unaffected.
- Behaviour gained on desktop/Android: `getNextPass()` and `createPlaceholder()` now use the shared
  tree's self-return collapse (a call that returns this object releases the extra reference and
  returns `this` instead of minting a second wrapper).

### Fixed — iOS: container arguments and `PackedVector4Array` (task 122)

- `Object.set` / `call` / `set_deferred` with a `List<String>`, `List<Double>`, `List<Vector2>`, a `Map` or a nested
  container argument threw on iOS ("unsupported List element type"); desktop boxed them. The argument encoder now lays
  containers out with the same blob builders the return path uses, nested containers included, so the iOS arg encoder
  accepts what desktop accepts (a value with no Variant shape still throws, as before).
- `PackedVector4Array` results decode to `List<Vector4>` on iOS (it had no type entry at all).
- A `List<String>` set through `Object.set` on a `List<String>` `@ScriptProperty` now reaches the property (the
  property dispatch routed a Godot Array only to the int-list and object-list bridges). Top-level `NodePath` and
  `RID` arguments are accepted (they were accepted only inside containers).
- Not changed: object elements of a returned container stay borrowed on both platforms; the owning design is
  recorded in task 122.

### Fixed — iOS: container results of `Object.call` / `Object.get` reach Kotlin (task 121)

- `Object.get` of a `List<String>` `@ScriptProperty` (and a `call(...)` whose result is an Array, a
  Dictionary or one of the nine packed arrays below) returned null on iOS; desktop returned the container. The C call path
  now serialises a container return as one self-describing blob record (the same encoder the
  container ptrcalls use) and Kotlin decodes it: Array → `List<Any?>`, Dictionary → `Map<String,
  Any?>`, `Packed{Byte,Int32,Int64,Float32,Float64,Vector2,Vector3,Color,String}Array` → the desktop
  list types. Packed kinds other than bytes also decode as Array/Dictionary elements now.
  `PackedVector4Array` followed in task 122 (entry above); RefCounted elements of a container result
  are borrowed on both platforms.
- Found by the third-person demo's iPhone smoke reading a BeetlebotSkin `_force_loop` export; its
  starter-probe twin (`probe_tags`, `smoke_modes`) is now asserted instead of "requested".

### Fixed — iOS: Object-typed values now cross from Kotlin scripts to the engine (task 115)

- `Object.get("shooter")` on a `@ScriptProperty var shooter: Node?` returned nil on iOS (desktop
  returned the node): the iOS bridge generator skipped Object and `@ScriptClass`-typed properties in
  `getProperty`, and the runtime's return encoder had no case for a wrapper, so the value never
  reached the engine. Both are fixed; the get/set parity guard now covers object refs too, so a
  future set-only object property fails the build instead of shipping write-only.
- The same encoder gap made Object-returning script methods and virtuals (`fun target():
  GodotObject?` — typed wrapper returns such as `Node?` are still rejected by the processor on every
  platform) ship nil (the generator warned and dropped them), and wrapper elements inside a returned
  `List` or `Map` encode as nil. Wrappers and script instances now ship their owner handle; the
  engine boxes it as an Object Variant (taking a reference for RefCounted, as `Variant(Object*)`
  does). A null Object property reads back as nil (not "property not found"), a closed RefCounted
  or a `lateinit` read before `_ready` answers nil instead of aborting the app.
- `Object.call`/`Object.get` results that are Objects come back as a `GodotObject` wrapper on iOS,
  as on desktop (they were the raw handle; `ClassDB.instantiate` of a non-RefCounted class too).
  Packed-array, `Array` and `Dictionary` results still decode to nil on iOS — task 121.
- Found by the third-person demo's iPhone smoke, which sets a bullet's `shooter` through
  `Object.set` before `add_child` and reads it back through `Object.get`.

### Changed — Kanama is one Kotlin Multiplatform module (task 104, step 3 parcels C+D)

- **Contributors' commands change; game code does not.** No wrapper member, signature or runtime
  type changed, and the published surface is unchanged too: the module still publishes exactly one
  Maven module, `net.multigesture.kanama:kanama`, carrying the JVM variant (the KMP root
  publication, which would advertise iOS variants Kanama does not publish, is disabled). Verified
  against a demo and a bare consumer project. The desktop jar is still `build/libs/kanama.jar`.
- The root project is one `kotlin("multiplatform")` module with targets `jvm()`, `iosArm64()` and
  `iosSimulatorArm64()`. **`:ios-runtime` no longer exists** — its cinterop, static library,
  per-target user-script dirs and per-target KSP moved to the root build.
- Task names: `compileKotlin` → `compileKotlinJvm`, `test` → `jvmTest`, `jar` → `jvmJar`,
  `kspKotlin` → `kspKotlinJvm`, and every `:ios-runtime:X` → `X` (`linkDebugStaticIosArm64`,
  `cinteropKanama_iosIosArm64`, …). `publishKanamaToMavenLocal`, `installAddonJar`,
  `syncExampleAddonJar`, the `packageX` lane, the xcframework tasks and `ktfmtCheck`/`ktfmtFormat`
  keep their names.
- Source layout: `src/main/kotlin` → `src/jvmMain/kotlin`, `src/test` → `src/jvmTest`,
  `ios-runtime/src/iosMain/kotlin` → `src/iosMain/kotlin`, and the shared generated wrapper tree →
  `src/sharedApi/kotlin`. `src/commonMain/kotlin` is now the module's KMP **common fragment**: the
  19 value types, `GodotHandle`, the generated `Real.kt` and the `expect` seams.
- **The compiler is the cross-platform contract.** `RawSegment`/`NULL_SEGMENT`, `BuiltinCalls` and
  a generated `ObjectCalls` (1,352 of the 1,359 ptrcall helpers the tree calls, seven documented
  exceptions) are `expect` declarations in the common fragment with one `actual` per backend, so a
  helper missing from either backend — or spelling a parameter, type or type-parameter bound
  differently — is now a build error. `scripts/check_builtin_calls_contract.py` is deleted (the
  compiler replaced it); `scripts/check_objectcalls_parity.py` stays for the one thing the compiler
  cannot see, that the generated `expect` list still matches the helpers the tree calls.
- The api tree is shared SOURCE compiled per platform, not common code: its classes extend the
  hand-shaped per-platform wrappers (`Node`, `GodotObject`, …), which a common source file may not
  name. Making those 29 classes `expect`/`actual` is **task 117**.
- `BuiltinCalls.call`/`callScalar`/`callBool`/`callInt` no longer default `args` to an empty list
  (an `actual` cannot carry a default, and the Android source copy has no common fragment); pass
  `emptyList()` explicitly. Internal API — game code does not call these.
- The Android PanamaPort remap skips `*.expect.kt` and strips a leading `actual ` modifier; its
  audit now ignores comments and matches declaration keywords instead of two text fragments.

### Fixed — Android smokes exported without the desktop runtime and never installed the build template (task 116)

- `scripts/android_smoke.sh` and `scripts/android_export_minified.sh` installed the Android AAR but not
  the desktop addon, so on a fresh checkout the export-time editor printed `No loader found for
  resource: res://….kt` and Godot's export re-packed every scene with no script properties (the
  task-106 failure, on the Android path); and their `--install-android-build-template --quit` step
  installs nothing on 4.7.2 (it only takes effect combined with an export), which the scripts hid
  with a silent fallback before failing on the missing `build.gradle`. Both scripts now install the
  desktop addon with the project's scripts registered, install the build template by hand from the
  export templates when Godot leaves nothing, clear `.godot/exported/`, log the export and fail on an
  unbound script, and run the exported-scene parity check (task 112) before touching the device.
  Found on a fresh clone with a Pixel 7; the first nine-demo matrix stopped on demo one.

### Internal — `ObjectCalls` is one object per platform, with a name-parity gate (task 104, step 3 parcel B)

- **Not a user-facing change.** No wrapper member, signature or runtime type changed; game code
  never names `ObjectCalls`.
- The 1,388 generated iOS ptrcall helpers were extension functions (`fun ObjectCalls.x(...)`) in a
  39,252-line `ObjectCallsGenerated.kt`. That file is gone: the generator now writes them as MEMBERS
  into a marked `GENERATED MEMBERS` region at the end of the hand-written iOS
  `object ObjectCalls` body (one ~50k-line file, like desktop's), and rewrites only that region.
  Four helpers that were both generated and hand-written join the generator's override set — a
  member always won over the extension, so the generated bodies were dead code and the behaviour is
  unchanged.
- Every one of the 1,359 helpers the shared tree calls now carries the DESKTOP file's parameter
  names on both platforms (the generator reads them from the desktop signature; nine hand-written
  iOS helpers were renamed by hand). Why: the rest of task 104 step 3 makes this an `expect object`,
  and an `expect` member is actualized only by a MEMBER with the same parameter names — an
  extension is invisible from common code and a differently spelled parameter breaks
  `foo(bar = 1)`.
- New gate `scripts/check_objectcalls_parity.py` (`local_ci` stage 34) proves it: every referenced
  helper is a member on both platforms with equal arity and equal parameter names in order.
  Parameter TYPES are deliberately not compared — the raw pointer differs by design (`RawSegment`).
- `ObjectCallsGenerated.kt`'s deletion is swept through `scripts/`, `AGENTS.md` and
  `docs/contributing/`. The drift gate and `--write-tree` now compare the region's member set with a
  whitespace-tolerant regex, which also fixes a regen that rewrote the generated file raw on every
  run because three ktfmt-wrapped helpers were missed.

### Internal — the shared wrapper tree no longer names `java.lang.foreign` (task 104, step 3 parcel A)

- **Not a user-facing change.** No member, signature or runtime type changed: `GodotHandle.segment`
  is declared as `net.multigesture.kanama.binding.runtime.RawSegment`, which is a plain `typealias`
  to the same pointer type each backend already used — the FFM `MemorySegment` on desktop/Android
  (`com.v7878.foreign.MemorySegment` after the Android remap), the Kotlin/Native shim on iOS. Game
  code never names either.
- `RawSegment` and the top-level `NULL_SEGMENT` beside it are declared once per platform under one
  fully-qualified name, and the wrapper generator emits them in the only three shared-tree shapes
  that named the pointer: `internal fun wrap(handle: RawSegment)` (979 files), `NULL_SEGMENT` for a
  static receiver or a null object argument (600 sites), and `private val singleton: RawSegment by
  lazy` (38 files). `GodotHandle` is now ONE file in `src/commonMain` instead of a copy per platform.
- Why: `src/commonMain` becomes a real KMP `commonMain` in the rest of task 104 step 3, and a
  `commonMain` can neither declare nor `expect` a JDK package. The later parcel turns this pair into
  `expect class RawSegment` / `expect val NULL_SEGMENT` with these typealiases as the `actual`s.
- The documented public raw-pointer exceptions drop from five to three: the two `value class
  GodotHandle` declarations are now one shared declaration over `RawSegment`, leaving only the three
  desktop-only `const void*` helpers (`GDExtensionManager.loadExtensionFromFunction`,
  `OpenXRAPIExtension.transformFromPose`, `OpenXRAPIExtension.setCustomPlaySpace`).
- `check_wrapper_generator.py` now fails when any file under `src/commonMain` names a
  `java.lang.foreign` type.

### Fixed — iOS: calling a `@RegisterFunction` with omitted default arguments aborted the app (task 114)

- The generated iOS bridge dispatched every registered method as one positional call
  (`script.spawn(args[0] as Double)`), so a Godot-side caller that left a defaulted parameter out —
  GDScript `coin.spawn()`, `call("spawn")`, a connection with fewer bound arguments — threw
  `IndexOutOfBoundsException` inside `callV` and the uncaught exception aborted the app. Found by the
  first smoke-on-device run (task 111) in third-person's `Coin.spawn(coinDelay: Double = 0.5)`. The
  emitter now dispatches by argument count for methods whose trailing parameters have defaults, one
  branch per admissible count, so Kotlin fills the defaults — the same shape the JVM bridge has always
  used. Value-returning methods (`callVReturning`) get the same treatment.

### Changed — iOS device gate: the demos' smoke scripts run on the phone; crash reports are read (task 111)

- **`System.getenv` on iOS reads the process environment** (`platform.posix.getenv`) instead of
  returning null. Every demo ships a `SmokeQuit` script (spawn / damage / free / unload / quit) gated
  on `KANAMA_DEMO_SMOKE_QUIT=1`; on the phone it never ran, so the gate proved "launched", not "ran
  and tore down" — task 108's crash lived in exactly that gap.
- `ios_device_gate.sh` sets `KANAMA_IOS_SMOKE_QUIT=1` by default; the demos' runner then launches each
  app with `devicectl … --environment-variables {"KANAMA_DEMO_SMOKE_QUIT":"1"}`, requires the smoke's
  completion line (`[kanama:smoke] SmokeQuit complete`) inside the console window, and snapshots the
  device's crash logs before and after the window — a new `<App>-*.ips` fails the step even when the
  crash left no signature in the console (kanama-demos, same task). `KANAMA_IOS_SMOKE_QUIT=0` restores
  the launch-only matrix.
- Godot's own ERROR output on iOS goes to the unified log (`OsLogLogger`), not the console stream the
  gate reads; capturing it stays open (task 111, item 3).

### Added — export integrity check for scene-stored `@ScriptProperty` values (task 112)

- `scripts/check_exported_scene_properties.gd` runs headless in an exported project, loads every
  scene Godot converted to binary next to its source `.tscn` (via `.godot/exported/*/file_cache`)
  and fails, naming the node and property, when a script-declared property present in the source is
  missing from the export — the effect behind task 106, whatever causes it next time. Native
  (ClassDB) properties are ignored because the re-pack may drop class defaults. The starter smoke
  (`ios_visual_smoke.sh`) runs it after its export; the demos' iOS runner does too (kanama-demos).
  Verified on Match3: passes on a correct export, fails with the five dropped Main-node properties
  when the example project's scripts jar is installed.

### Fixed — iOS: freeing a node after its signal emitter crashed in `Object::~Object` (task 108)

- **Third-person's BeeBot crashed the iPhone in `SceneTree::_flush_delete_queue` →
  `Object::~Object` → `Object::_disconnect` (`KERN_INVALID_ADDRESS at 0x18`)**, intermittently,
  with no Kotlin frame. `SignalConnection.close()` recreated the lambda Callable for
  `Object.disconnect` **without the receiver's ObjectID**. Godot's `_disconnect` takes the receiver
  from the Callable it is given, so it removed the slot from the emitter but left the entry in the
  receiver's own connection list dangling. When the receiver was freed after its emitter (a child
  `Area3D` is destroyed before the `RigidBody3D` parent that connected to it), the destructor
  followed the stale entry to a freed emitter and dereferenced null. The disconnect path now binds
  the same receiver as connect (`kanama_ios_godot_object_disconnect_callable` takes
  `target_object`; `SignalConnection` remembers its receiver). Self-test row
  `lambda-callable(disconnect then free emitter before receiver)` reproduces the order; it
  segfaulted the app at extension init before the fix.

### Fixed — iOS export dropped every scene-stored `@ScriptProperty` value (task 106)

- **Match3 crashed two seconds after launch on the iPhone** (`tile_scene is not assigned` in
  `Main._ready`) because the exported `main.scn` no longer contained the Main node's `tile_scene`,
  `sparkles_scene`, `textures` and cursor properties, nor the external resources they referenced.
  Godot's export instantiates and re-packs every scene when it converts text resources to binary
  and keeps only the properties the script instance reports. `installIosAddon` installed the
  *example project's* desktop `kanama-scripts.jar` into the target project unless the caller also
  passed `-PkanamaProjectScriptsDir`, so the export-time editor bound no Kotlin class to the
  project's `.kt` scripts, they reported no properties, and the values were dropped silently
  (Godot's error output on iOS goes to the system log, not the device console). Three fixes:
  - `:project-scripts` now falls back to `kanamaIosProjectScriptsDir(s)` when
    `kanamaProjectScriptsDir(s)` is unset, so `installIosAddon -PkanamaIosProjectScriptsDir=…`
    registers a project's scripts for **both** targets (the iOS side already fell back the other
    way). Passing both stays valid.
  - The desktop `.kt` loader prints a `WARNING: no Kotlin class bound for <path>` line when the
    scripts jar lacks a script, naming the consequence (dropped export values) and the fix.
  - Documented the second trap in `docs/exporting/ios.md`: Godot caches each converted scene under
    `.godot/exported/` keyed by the source's md5 + mtime, so a stripped conversion is reused by
    every later export until the `.tscn` changes; delete that directory after fixing the jar.
  The demos' iOS runner passes both properties, clears `.godot/exported/` before exporting and
  fails when the export log shows an unbound project script (kanama-demos, same task). The
  September 13 pass and the September 11/14 failures on the same code differed only in which
  desktop jar sat in the demo's addon folder when the scene was first converted.

### Changed — one shared body per Godot value type (task 104, step 2)

- **The 19 builtin value types are one set of files, not two.** `Vector3`, `Basis`,
  `Transform3D`, `Quaternion`, `Vector2`, `AABB`, `Plane`, … used to exist twice — once for
  desktop/Android, once for iOS — with different method sets and different implementations of
  the same method. They now live once, under
  `src/commonMain/kotlin/net/multigesture/kanama/types`, compiled by the root JVM module,
  `:ios-runtime` and the Android plugin alike. **Nothing was removed from either platform:**
  the shared public surface is the union of the two, so every type gained the members the
  other platform had.

  What iOS gains: `Vector4i` (desktop-only until now), `Quaternion.dot` / `length` /
  `lengthSquared` / `normalized` / `times` / `unaryMinus`, `Vector2.distanceTo` /
  `distanceSquaredTo` / `dot` / `limitLength`, `Vector3.limitLength` (and the `times`/`div`
  `Double`/`Float` overloads), `Vector4.dot`, `Plane.distanceTo` / `intersectsRay`,
  `AABB.end` / `hasPoint` / `volume`, `Rect2.area` / `hasPoint`, `Basis.lerp` /
  `EULER_ORDER_*` / `getEuler(order)`, and `Basis.fromEuler(euler, order)`.
  What desktop and Android gain: `Basis.transposed`, `Transform3D.affineInverse` / `inverse` /
  `translated`, `Vector3.isNormalized` / `maxAxisIndex`, `Vector2i` / `Vector3i` `withX`/`withY`/
  `withZ` and `ONE`, `Projection.ZERO`, and `Plane(x, y, z, d)`.

- **One rule decides who computes a method.** A method that either platform used to route
  through the engine keeps doing so — those encode Godot's own edge cases (epsilons,
  orthonormalization, Euler order, shortest-arc slerp) — through a single facade,
  `net.multigesture.kanama.binding.runtime.BuiltinCalls`, which exists once per backend under
  one fully-qualified name. Everything else is exact arithmetic in one Kotlin body.

  Two consequences worth knowing. On **iOS**, `Vector3.cross`, `dot`, `isNormalized` and
  `maxAxisIndex` are now computed in Kotlin instead of by the engine — same results (they are
  exact formulas; `isNormalized` is Godot's `is_equal_approx(length_squared(), 1, UNIT_EPSILON)`
  including its exact-equality short-circuit), minus four builtin round-trips per call. On
  **desktop and Android**, `Quaternion.inverse()` is now the engine's `Quaternion::inverse` —
  the conjugate, as GDScript and C# return — where the old local body divided by the squared
  length and returned `IDENTITY` for a zero quaternion. For a unit quaternion, which is what
  `inverse()` is defined for, the two agree; for a non-unit or zero one, Kanama now matches
  Godot instead of differing from it. `Basis.getEuler()` on iOS is the engine's decomposition
  (and takes the `order` argument) rather than a hand-written YXZ one, and
  `Transform3D.scaledLocal` / `Vector2.lerp` / `Vector2.clamp` on iOS are engine-computed.

- Not a breaking change for game code: no member was removed or renamed, and no signature
  changed. Scripts that used a member on the platform that had it keep compiling, on every
  platform now.

### Breaking — `GodotHandle` replaces `MemorySegment` in every public signature (task 104, step 1)

- **Every attachable script constructor changes type.** The opaque handle a wrapper and a
  `KanamaScript` take is `net.multigesture.kanama.api.GodotHandle` — a zero-cost
  `@JvmInline value class` declared once per backend under that one fully-qualified name
  (over the FFM `MemorySegment` on desktop/Android and iOS, over the registry id on Web).
  No public wrapper or script signature names a `java.lang.foreign` type any more, which is
  what lets one script source compile for all four backends. Migrating a script is three
  lines:

  1. the constructor parameter type: `MemorySegment` → `GodotHandle`
     (`class Player(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)`);
  2. the import: drop `import java.lang.foreign.MemorySegment`, add
     `import net.multigesture.kanama.api.GodotHandle`;
  3. nothing else — re-wrapping through another wrapper's handle
     (`CharacterBody3D(body.handle)`, `selfAs(::Node3D)`, `getAsOrNull(path, ::Sprite2D)`) is
     unchanged, because `GodotObject.handle` keeps its name and is a `GodotHandle` now.

  A script left on the old type fails the build with that migration spelled out, instead of an
  argument-type mismatch inside the generated registrar. Code that reached through the handle
  for a raw pointer (`handle.address()`) has no replacement by design: use `isSameInstance()`,
  and `handle.segment` only from backend glue. The editor's "new script" template, the starter
  template, the example project and the twelve demos are migrated. Three desktop-only helpers
  keep a `MemorySegment` parameter on purpose, because what they take is a raw native pointer and
  not an object handle: `GDExtensionManager.loadExtensionFromFunction(initFunc)`,
  `OpenXRAPIExtension.transformFromPose(pose)` and `OpenXRAPIExtension.setCustomPlaySpace(space)`.

### Added — Web render-quality, window and glue families (task 64, tps-demo parcel 8)

- **Web protocol 27 → 28.** The Kotlin/Wasm backend admits the settings families tps-demo's
  Settings / Menu / Level write on every graphics apply: `Environment.set_ssao_enabled` (322) and
  `set_volumetric_fog_enabled` (323) as queued mutations, `RenderingServer.voxel_gi_set_quality`
  (324), `environment_set_sdfgi_ray_count` (325) and the six-argument
  `environment_set_ssao_quality` (326) / `environment_set_ssil_quality` (327) on a new
  `LONG_BOOL_DOUBLE_LONG_DOUBLE_DOUBLE_ARG_SINGLETON` shape (the six values ride the object-query
  string channel joined by U+001F and the applier calls Godot with the full signature), plus
  `RenderingServer.get_current_rendering_driver_name` (328) and `OS.get_name` (329) on the
  immediate string channel, the queued `Viewport.set_input_as_handled` (330) and
  `Control.set_position` / `set_size` (331/332). The Compatibility renderer ignores most of the
  quality writes, but the calls reach the engine instead of a Kotlin-side stub. `Node.get_window`,
  `Node.propagate_call("set", [property, bool])`, `ResourceLoader`'s threaded-load family and
  `loadLightmapGIData`, and the `ENV_SSAO_/ENV_SSIL_/VOXEL_GI_/ENV_SDFGI_RAY_COUNT_` constants are
  generated members of the Web wrappers now rather than hand-written extensions needing their own
  imports — the shared demo sources spell them exactly as desktop does, and the threaded-load
  status constants take Godot's own values (the old facade had `THREAD_LOAD_IN_PROGRESS` and
  `THREAD_LOAD_LOADED` transposed). The `LONG_OBJECT_ARG` object slot is nullable, so
  `Mesh.surface_set_material(i, null)` clears a surface material. The Web script processor also
  emits the `<Script>Rpcs` helpers desktop has (the parcel-5 `<Script>Methods` precedent): a
  browser build has a single local peer, so a broadcast or a call addressed to peer 0/1 runs the
  local leg when `@Rpc(callLocal = true)` says so and any remote peer id fails loud. The in-repo
  `web3d` fixture proves the new surface (`Main.render_settings_probe` = 63,
  `Main.window_family_probe` = 31, both required by the smoke gate).
  **Existing Web exports must be rebuilt**: a protocol-27 export refuses to load against a
  protocol-28 bridge, and vice versa.

### Added — Web SceneTree.create_timer (task 64, tps-demo parcel 7)

- **Web protocol 26 → 27.** `SceneTree.create_timer(time_sec)` (opcode 321, new `DOUBLE_RET_HANDLE`
  shape: the seconds ride the property-object channel, the applier registers the `SceneTreeTimer`
  under the proposed OBJECT slot as a retained RefCounted, like a Tween), returned by the generated
  `SceneTree.createTimer(timeSec, …)` as a `RefCounted` handle whose `timeout` is awaited through the
  generic signal path, so the shared demo idiom
  `getTree().createTimer(t)?.signal(Timer.Signals.timeout)?.await(self)` compiles and runs on Web
  unchanged (the three flags must keep Godot's defaults). `MainThread.postNextFrame` is a member (was
  an extension needing its own import); the Web multiplayer facade's `MultiplayerAPI` gains the
  owned-handle `close()` the shared helpers call and `MultiplayerPeer` is `AutoCloseable`, so the
  stdlib `use { }` resolves on both backends (the facade's own `use` extension is gone). The in-repo
  `web3d` fixture proves the timer fires (`Main.timer_probe`, required by the smoke gate, must read 7).
  **Existing Web exports must be rebuilt**: a protocol-26 export refuses to load against a
  protocol-27 bridge, and vice versa.

### Added — Web node lifecycle queries (task 64, tps-demo parcel 6)

- **Web protocol 25 → 26.** The Kotlin/Wasm backend admits `Node.is_inside_tree` (319) and
  `Object.is_queued_for_deletion` (320) as immediate bool queries — the guards tps-demo's Level,
  Part and Blast put around late signal handlers. The `web3d` fixture's `node_lifecycle_probe`
  (required member, mask 15) creates and adds a node from Kotlin and reads both queries back.
  **Existing Web exports must be rebuilt**: a protocol-25 export refuses to load against a
  protocol-26 bridge, and vice versa. Demos side: tps-demo's Blast, Door, FlyingForklift
  and CameraNoiseShakeEffect drop their Web overrides.

### Added — Web: typed cross-script call helpers (task 64, parcel 5)

- The Web script processor now emits the `<Script>Methods` objects desktop has always had (one
  overload per `@RegisterFunction` taking the Kotlin instance, one resolving it from a
  `GodotObject` through `kotlinScriptInstance`), so a shared demo file can call another script the
  typed way on every platform — third-person's `BeetleBot` hits the player through
  `PlayerMethods.damage` on desktop and Web alike, and the runtime node-lookup audit's "no dynamic
  `call` in per-frame functions" rule holds on the shared file. No protocol change (a direct Kotlin
  call through the script-instance registry; no Godot crossing).

### Added — Web: the CameraMode family (task 64, parcel 4)

- **Web protocol 24 → 25.** The Kotlin/Wasm backend admits `Input.is_key_pressed` (opcode 313, a
  new `LONG_RET_BOOL_SINGLETON` shape: the key code rides the object-query string channel),
  `Input.get_last_mouse_velocity` (314, new `NOARGS_RET_VECTOR2_SINGLETON`), `Camera3D.set_current`
  (315, queued) / `set_fov` (316, queued) / `get_fov` (317), `SceneTree.get_nodes_in_group` (318, new
  `STRINGNAME_RET_HANDLE_LIST`: scripted members resolve to script handles, engine nodes get
  tracked browser handles, as `find_children` does), and `Camera3D.create()` (the
  `ClassDB.instantiate` composition). This is everything third-person's shared `CameraMode.kt`
  — the debug fly-camera — needs to compile on Web; it self-gates on `OS.isDebugBuild()`, false
  for the release template, so it stays inert at runtime. The `web3d` fixture's
  `Main.camera_mode_probe` (required by the smoke gate, mask 31) constructs a camera, makes it
  current, sets and reads its fov, queries a group, polls a key and the mouse velocity, and
  restores the previous camera. **Existing Web exports must be rebuilt** (protocol mismatch
  refuses to load).

### Changed — iOS device gate: the demo steps watch the device console (task 105)

- `scripts/ios_device_gate.sh --console-seconds N` (default 30) makes every demo step stream the
  phone's console for N seconds after launch and fail on a crash signature (`App terminated due
  to signal`, `FATAL`) or when the runtime never reports; the log is kept as
  `<output-dir>/<App>.console.log`. Until now the nine demo steps only checked that the launch
  returned, so a crash seconds later read PASS (the third-person demo did exactly that on
  2026-09-10). Needs a demos checkout whose `ios_device_run.sh` supports
  `KANAMA_IOS_CONSOLE_SECONDS`; `--console-seconds 0` keeps the launch-only behaviour.

### Added — Web: the DemoPage set (task 64, parcel 3)

- **Web protocol 23 → 24.** The Kotlin/Wasm backend admits `SceneTree.is_paused` (307),
  `Control.release_focus` (308), `Environment.set_ssil_enabled` / `set_sdfgi_enabled` (309/310),
  `SceneTree.unload_current_scene` (311) and `Input.get_connected_joypads` (312, a new
  `NOARGS_RET_LONG_LIST_SINGLETON` shape: comma-joined ids on the string channel). The Web
  `MainThread` gains `postAfterFrames(frames) { }` (one scheduler post per frame) and `SceneTree`
  the companion `quit()` / `unloadCurrentScene()` calls through the executing script's tree, so
  third-person's shared `DemoPage.kt` compiles and runs on Web without an override (the browser
  branches — Exit resumes instead of quitting, the deferred-lighting upgrade is skipped on the
  Compatibility renderer — are `OS.hasFeature("web")` gates in the shared file). The in-repo
  `web3d` fixture proves the set (`Main.demo_page_probe` = 31, `demo_page_probe_after` = 1).
  **Existing Web exports must be rebuilt**: a protocol-23 export refuses to load against a
  protocol-24 bridge, and vice versa.

### Changed — iOS shape gap page: desktop-only-by-design members recorded (task 100 close-out)

- The generated gap page (`docs/reference/generated/ios-shape-gap.md`) now separates members that
  desktop keeps desktop-only on purpose — `GDExtensionManager.load_extension_from_function`
  (function pointer), `OpenXRAPIExtension.transform_from_pose` / `set_custom_play_space`
  (`const void*`) — into a "Desktop-only by design" table with the reason
  (`IOS_DESKTOP_ONLY_BY_DESIGN` in `scripts/generate_api_wrapper.py`), and counts only members
  that wait on an iOS helper. With parcels 1-11 merged that count is **0**: the shared tree is the
  whole generated API on both native backends. No wrapper changes; desktop keeps the three members.

### Added — iOS: Callable returns on every audited argument shape (task 100, parcel 11)

- A method returning a `Callable` — `TreeItem.get_custom_draw_callback`,
  `MultiplayerSpawner.get_spawn_function` (and the `spawn_function` property), the four
  `NativeMenu.get_*_callback` getters and `DisplayServer.global_menu_get_item_callback` /
  `global_menu_get_item_key_callback` — was desktop-only. Every such method whose arguments are
  already audited now gets a generated helper returning `GodotCallable?`, decoded the way the
  desktop backend decodes it: the method runs once into a Callable cell, `Callable.get_object()`
  and `Callable.get_method()` are read back through the builtin-method table, the target comes
  back as a borrowed `GodotObject` over its handle and the method name travels through the
  parcel-1 UTF-8 path (inline buffer, single pending slot beyond it — never truncated, never
  re-issued). An empty or object-less Callable is `null`, as on desktop. New C entry
  `kanama_ios_godot_ptrcall_ret_callable`; no new tag. 9 members on 4 classes move from the
  desktop companions into the shared tree; the gap index goes from 34 to 25 desktop-only members
  (20 → 17 companion files, 27 → 22 helpers waited on). Five self-test rows round-trip a
  `MultiplayerSpawner` spawn function (target handle and method name; a 602-byte non-ASCII name
  through the pending slot), read the empty spawn function back as `null`, and read `NativeMenu`'s
  popup callback for an invalid RID back as `null` (no Control is constructed: a Control's
  post-initialize needs the theme contexts, which do not exist before `Main::setup2`).

### Added — iOS: typed arrays of containers and packed arrays on every audited shape (task 100, parcel 10)

- A method taking an `Array[Dictionary]`, `Array[Array]`, `Array[PackedByteArray]` or
  `Array[PackedStringArray]` argument — or returning an `Array[Array]` — was desktop-only; every such
  method whose other arguments are already audited now gets a generated helper. The typed-array
  descriptor of parcel 8 carries each element as a nested blob (a Dictionary / Array element is the
  task-29 entry blob, one container level with scalars and `ByteArray`s inside; a PackedByteArray
  element is its raw bytes; a PackedStringArray element the task-13 string blob), and the dispatch's
  blob boxer rebuilds each element before `push_back`, destroying the temporary once the Variant
  holds its own reference. `Array[Array]` returns ride the parcel-6 container blob, which now encodes
  PackedByteArray elements as their bytes (other nested packed arrays still surface `null`, the
  recorded limit). 13 members on 8 classes move from the desktop companions into the shared tree —
  `GraphEdit.set_connections`, `GLTFState.set_buffers`, `GLTFObjectModelProperty.set_json_pointers`,
  `OggPacketSequence.set_packet_data` / `get_packet_data`, `RenderingDevice.texture_create`,
  `RenderingServer.mesh_create_from_surfaces` / `mesh_surface_get_blend_shape_arrays`,
  `ImporterMesh.add_surface`, `EditorVCSInterface.add_diff_hunks_into_diff_file` /
  `add_line_diffs_into_diff_hunk`, `DisplayServer.file_dialog_with_options_show` — and the
  `connections`, `buffers` and `jsonPointers` properties are read-write on iOS again; the gap index
  goes from 34 to 21 desktop-only members (20 → 13 companion files, 27 → 16 helpers waited on, 3 → 0
  read-only properties). Five self-test rows hand an empty `Array[Dictionary]` (which the engine rejects, as asserted) to
  `set_connections` / `get_connection_list`, two OggPacketSequence pages of PackedByteArray packets
  (byte-exact, including a 300-byte packet), three GLTFState buffers (an empty one and a 5000-byte
  one) and three JSON-pointer PackedStringArrays (a non-ASCII element, an empty array, an empty
  string).

### Added — iOS: typed-object-list returns on every audited argument shape (task 100, parcel 9)

- A method returning `Array[<Object subclass>]` (`List<T>` on Kotlin) reached iOS through three
  hand-written argument layouts only (no-arg, one bool, two String + two bool); every other such
  method was desktop-only. Every audited argument layout now gets a generated helper —
  `fun <T> ObjectCalls.<shape>(..., fromHandle: (MemorySegment) -> T?): List<T>` — that lays its
  arguments out like any other helper and hands them to the shared `retTypedObjectList` body. That
  body rides a new C entry, `kanama_ios_godot_ptrcall_ret_object_handles`, which runs the method
  **once** and delivers the element handles into the caller's buffer or, past 64 elements, a
  pending slot the Kotlin side drains (the older two-call length protocol re-invoked the method,
  acceptable for `get_children`, not for `Noise.get_image_3d` or `RenderingServer.bake_render_uv2`).
  The three hand-written helpers gain the run-once behaviour too. Element handles stay borrowed,
  as on desktop. 9 members on 7 classes move from the desktop companions into the shared tree:
  `InputMap.action_get_events`, `RegEx.search_all`, `Noise.get_image_3d` / `get_seamless_image_3d`,
  `TranslationServer.find_translations`, `TranslationDomain.find_translations`,
  `RenderingServer.texture_3d_get` / `bake_render_uv2`, `EditorInterface.make_mesh_previews`; the
  gap index goes from 34 to 25 desktop-only members (20 → 14 companion files, 27 → 19 helpers
  waited on). Four self-test rows round-trip `RegEx.search_all` through the inline buffer (3
  matches) and the pending slot (200 distinct match handles), `InputMap.action_get_events` by
  handle identity and an empty `TranslationServer.find_translations`.

### Added — iOS: typed-array and Rect2i arguments on every audited shape (task 100, parcel 8)

- A method taking a typed `Array[...]` argument — `Array[RID]`, `Array[String]`, `Array[StringName]`,
  `Array[NodePath]`, `Array[int]`, `Array[Vector2i]`, `Array[Plane]`, `Array[Transform3D]`,
  `Array[PackedVector2Array]` or an `Array[<Object subclass>]` whose element wrapper is emitted on
  iOS — was desktop-only, as were `Rect2i`, `Plane` and `Vector4` arguments and `Plane` / `Vector4`
  returns. Every such method whose other arguments are already audited now gets a generated
  helper. Typed arrays ride a new BUILD-tagged descriptor: the dispatch constructs an empty Godot
  Array, makes it typed through the `array_set_typed` interface (the engine only accepts that on
  an empty array, and several setters reject an untyped one), pushes the descriptor's tagged
  elements one by one (the task-29 container layout; `push_back` validates each against the
  type) and destroys the Array after the call. `Rect2i`, `Plane` and `Vector4` are plain
  fixed-width cells the layout table lacked. 135 members on 55 classes move from the desktop
  companions into the shared tree — `PhysicsPointQueryParameters2D.set_exclude`,
  `PhysicsRayQueryParameters3D.create`, `NavigationPathQueryParameters2D.set_included_regions`,
  `GLTFState.set_nodes`, `GLTFState.set_unique_names`, `CodeEdit.set_comment_delimiters`,
  `Control.set_accessibility_controls_nodes`, `RDPipelineMultisampleState.set_sample_masks`,
  `Geometry3D.compute_convex_mesh_points`, `Geometry3D.clip_polygon`,
  `RenderingDevice.vertex_format_create`, `RenderingServer.texture_3d_create`,
  `AStarGrid2D.set_region`, `BitMap.set_bit_rect`, `Window.popup`,
  `VisualShaderNodeVec4Parameter.set_default_value`, `XRAnchor3D.get_plane` among them; the gap
  index goes from 393 to 258 desktop-only members (147 → 108 companion files, 195 → 128 helpers
  waited on). Eight self-test rows round-trip a 1000-element `Array[RID]`, `Array[String]`,
  `Array[GLTFNode]`, `Array[NodePath]`, `Array[int]` and `Array[PackedVector2Array]` through a
  generated setter and its read-back, feed the unit cube's six planes to
  `Geometry3D.compute_convex_mesh_points`, and round-trip a `Rect2i` and a `Vector4`.

### Added — iOS: Dictionary, Array and Variant arguments on every audited shape (task 100, parcel 7)

- A method taking a `Variant`, a `Dictionary` or a generic `Array` argument was callable on iOS
  only through the hand-written Object-call helpers (`callWithVariantArgs` and the few shapes
  built on it). Every such method whose other arguments are already audited now gets a generated
  helper: a Variant argument travels as a `KanamaIosVariantArgDesc {tag, ptr}` that the generic
  ptrcall dispatch boxes with the Object-call boxer into a Variant cell for the call
  (`KANAMA_IOS_PT_VARIANT`, appended), and a Dictionary or Array argument travels as the task-29
  entry blob the dispatch rebuilds into a container cell (the DICTIONARY / ARRAY tags double as
  argument tags). Every cell is destroyed after the call — the engine copies what it keeps, so
  nothing outlives the ptrcall. Inside a Variant: null, Boolean, Int/Long, Float/Double, String,
  NodePath, Vector2/2i/3, Color, RID, object handles, and one level of Map / List with scalar
  values; inside a Dictionary or Array argument: scalars — a nested container or any other Kotlin
  object throws (`IosReturnContainerScratch.taggedValue` strict mode) instead of silently passing
  nil, and the blob for each container argument lives in the call's own scope so two container
  arguments never share a buffer. 109 generated helpers use the new packers; 222 members on 97
  classes move from the desktop companions into the shared tree — `ConfigFile.set_value`,
  `ConfigFile.get_value`, `Expression.execute`, `AudioStreamWAV.set_tags`,
  `ProjectSettings.set_setting`, `JSON.stringify`, `JSON.from_native`, `RichTextLabel.push_meta`,
  `TextLine.add_string`, `PhysicsServer3D.body_set_param`, `RenderingServer.global_shader_parameter_set`,
  `CodeHighlighter.set_keyword_colors`, `DisplayServer.global_menu_add_item`,
  `EngineDebugger.send_message` among them; the gap index goes from 389 to 171 desktop-only
  members (147 → 68 companion files, 195 → 96 helpers waited on). Eleven self-test rows round-trip
  int, float, String, bool, Vector2, null (which erases a ConfigFile key), a Map and a List through
  `ConfigFile.set_value` / `get_value`, an Array element through `Expression.execute`, a Dictionary
  through `AudioStreamWAV.set_tags` / `get_tags`, and a Variant through `ProjectSettings.set_setting`.

### Added — iOS: string-list and typed-array returns on every audited argument shape (task 100, parcel 5)

- A method returning a PackedStringArray or a typed `Array[...]` of RID, Vector2i, Vector3i,
  String, StringName, NodePath, int, Plane, Vector2, Vector3, Rect2, Transform3D,
  PackedVector2Array, PackedByteArray or PackedStringArray was callable on iOS only through
  the hand-written no-arg read-backs, and only for the few element kinds those covered. Every
  such method whose arguments are already audited now gets a generated helper: the new C entry
  `kanama_ios_godot_ptrcall_ret_array_blob` ptrcalls once, encodes the array into the same
  length-prefixed blob the no-arg read-backs use (their element encoders are now one shared
  set, extended with the new element kinds) and delivers it whole — into the caller's buffer
  when it fits, otherwise parked C-side and drained by `kanama_ios_godot_take_pending_blob`.
  Nothing is truncated and the method is never re-invoked (the no-arg entries' two-call length
  protocol was only safe for pure getters). 43 helpers, 117 members on 44 classes move from the
  desktop companions into the shared tree — `ClassDB.get_inheriters_from_class`,
  `ClassDB.class_get_enum_list`, `TileMapLayer.get_used_cells`, `TileMapLayer.get_used_cells_by_id`,
  `GridMap.get_used_cells_by_item`, `Geometry3D.build_box_planes`, `Theme.get_color_list`,
  `NavigationServer3D.map_get_regions`, `NavigationPathQueryParameters3D.get_included_regions`,
  `CodeEdit.get_comment_delimiters`, `TextServer.font_get_kerning_list`,
  `DisplayServer.get_display_cutouts`, `GLTFState.get_buffers` among them; the gap index goes
  from 852 to 735 desktop-only members (229 → 224 companion files, 385 → 343 helpers waited
  on). Left in the gap on purpose: typed `Array[Dictionary]` / `Array[Array]` returns (they
  need the Dictionary / Array decode) and typed object arrays (their own gate). Eight
  self-test rows cover a PackedStringArray with an argument (short, and the >4 KiB
  `get_inheriters_from_class("Object")` through the pending slot), Array[Vector2i] with and
  without arguments, Array[StringName], Array[Plane] and Array[Vector3i].

### Added — iOS: Packed*Array arguments on every audited shape (task 100, parcel 4)

- A method taking a PackedByteArray, PackedInt32Array, PackedInt64Array, PackedFloat32Array,
  PackedFloat64Array, PackedVector2Array, PackedVector3Array, PackedColorArray or
  PackedStringArray argument was called on iOS only through five hand-written helpers (the four
  CanvasItem draw shapes and the single-arg PackedFloat32Array setter). Every such method whose
  other arguments are already audited now gets a generated helper: the generic ptrcall dispatch
  already built Vector2/Color/byte arrays from a `KanamaIosPackedArgDesc {count, data}`, and the
  task-29 virtual-return path had extended the builder to every fixed-element kind — the dispatch
  now accepts all of them as BUILD-tagged arguments, plus PackedStringArray built from the same
  `[int32 count]([int32 len][utf8])*` blob the virtual-return path rebuilds from. The generator
  lays each argument out through an `ObjectCalls.pack<Kind>Desc` helper (`IOS_PACKED_ARGS`).
  200 members on 98 classes move from the desktop companions into the shared tree —
  `Polygon2D.set_polygon`, `CPUParticles2D.set_emission_points`, `ConvexPolygonShape3D.set_points`,
  `NavigationPolygon.add_polygon`, `Crypto.encrypt`, `AESContext.update`, `AudioStreamMP3.set_data`,
  `Geometry2D.triangulate_polygon`, `RenderingServer.canvas_item_add_polygon`,
  `RenderingDevice.buffer_update`, `WebSocketPeer.set_supported_protocols`,
  `OS.create_process` among them; 76 shared-tree properties whose setter was desktop-only are
  read-write again (127 → 51 read-only); the gap index goes from 852 to 652 desktop-only members
  (229 → 173 companion files, 385 → 278 helpers waited on). Seven self-test rows round-trip a
  Vector2, byte, int32, Vector3, Color, int64, float64 and string array through a generated
  setter and its read-back, two of them longer than the 256-element inline capacity. Elements are
  still pushed one at a time on the C side (the builder predates this parcel); a bulk `resize` +
  copy is a follow-up, not a correctness concern.

### Added — iOS: Dictionary and Array returns on every audited argument shape (task 100, parcel 6)

- A method returning a `Dictionary`, a generic `Array` or an `Array[Dictionary]` reached iOS only
  through two hand-written generic-Array helpers and the fixed-shape raycast decode. Every such
  method whose arguments are already audited now gets a generated helper: the new C entry
  `kanama_ios_godot_ptrcall_ret_container_blob` runs the method once through the generic ptrcall
  dispatch and serializes the container into one self-describing blob (records of variant type,
  byte length and payload; nested Dictionary / Array values as nested blobs; String and StringName
  keys as UTF-8), parking a blob longer than the 4 KiB inline buffer C-side for
  `kanama_ios_godot_take_pending_container_blob` — nothing is truncated and nothing is re-issued.
  Decode parity with desktop: `Map<String, Any?>` keeps String / StringName keys only (as
  `BuiltinTypes.readDictionaryScalars` does) and values decode as bool, int, float, String family,
  borrowed Object handle, Vector2 / Vector2i / Vector3 / Color, nested Map / List; other value types
  surface `null`. `Array[Dictionary]` drops non-Dictionary elements like `readArrayDictionaries`.
  The existing generic-Array blob entry is now a thin wrapper over the same encoder, so its
  hand-written callers see nested containers instead of `null`. 36 helpers, 129 members on 53
  classes move into the shared tree — `Time.get_datetime_dict_from_unix_time`,
  `OS.get_memory_info`, `ClassDB.class_get_method_list`, `ClassDB.class_get_signal`,
  `Script.get_script_property_list`, `GraphEdit.get_connection_list`,
  `PhysicsDirectSpaceState2D.intersect_ray`, `RenderingServer.mesh_surface_get_arrays`,
  `StreamPeer.get_data` among them; the gap index goes from 852 to 723 desktop-only members
  (229 → 218 companion files). Six self-test rows cover a Dictionary with an int64 argument,
  a no-arg Dictionary, an Array with an int argument, an `Array[Dictionary]` far larger than the
  inline buffer with nested records, the drained pending slot, and a two-StringName-argument
  Dictionary.

### Added — Web Curve.sample (task 64, Curve + Resource-typed hydration parcel)

- **Web protocol 22 → 23.** The Kotlin/Wasm backend admits `Curve.sample(offset: Double):
  Double` (opcode 306, `DOUBLE_RET_DOUBLE` shape), generating a Web `Curve` wrapper class for
  the first time. This is the one method call third-person's shared `Bullet.kt` needs once its
  `scaleDecay: Curve?` `@ScriptProperty` hydrates — the Resource-typed property push itself
  already rode the existing generic OBJECT property arm (`objectWrapperFqName`), so no processor
  change was needed there. The in-repo `web3d` fixture proves the family delivers a VALUE, not
  merely a dispatch (`Main.curve_sample_probe`, required by the smoke gate, must return 3).
  **Existing Web exports must be rebuilt**: a protocol-22 export refuses to load against a
  protocol-23 bridge, and vice versa.
- **Fixed — Web value-returning double queries.** `Noise.get_noise_1d` (opcode 276) and the new
  `Curve.sample` rode the bridge's confirm-only double channel, which treats any result other
  than `1` as "not applied" — so every sample except 0.001 threw a boundary failure. The
  `DOUBLE_RET_DOUBLE` shape now has its own bridge path (`immediateDoubleRetDouble`) that
  accepts any published x1000 integer (0 included), and the generated GDScript applier gained
  the `Curve.sample` arm it was missing.

### Changed — macOS exported games: validated on the bundled runtime, and the signing gap written down (task 63)

- The bundled-jlink-runtime work was proven end to end on Windows and Linux from a macOS host;
  macOS itself had only the first slice's export and no current evidence. `export_game_smoke.sh`
  now has a recorded macOS arm64 pass (2026-09-10, Godot `4.7.2.stable`, Temurin 25.0.4.1+1), run
  twice: against a host-jlinked image and against one linked from the pinned `macos-arm64` Temurin
  jmods (31.7 MB either way). The exported `.app` boots headless with `JAVA_HOME` unset and `PATH`
  stripped, from `Contents/Resources/runtime/lib/server/libjvm.dylib`. The logged path is the
  proof and not the fact that it ran: with the bundled runtime deleted the same `.app` still
  starts on a developer Mac through the bootstrap's hardcoded Temurin fallback, and only the path
  assertion catches that.
- [Desktop and Packaging](docs/exporting/desktop.md) gains a macOS section for what the bundled
  runtime changes: the `.app` layout with the payload in `Contents/Resources/`, why that is the
  only location `codesign` will seal, the ad-hoc reseal (and that it replaces a Developer ID
  signature, so a signed build assembles before it signs), the three hardened-runtime entitlements
  the embedded JVM needs and what each is for, and an inventory of what signed distribution would
  additionally require — 16 Mach-O files inside the runtime image that the bundle signature only
  hashes, inside-out signing, notarize and staple. Signing and notarization remain non-goals; this
  is documentation of the gap, not an implementation.

### Added — iOS: Packed*Array returns on every audited argument shape (task 100, parcel 3)

- A method returning a PackedByteArray, PackedInt32Array, PackedInt64Array, PackedFloat32Array,
  PackedFloat64Array, PackedVector2Array, PackedVector3Array or PackedColorArray was called on iOS
  only through the hand-written no-arg read-backs (and only for the four kinds that had one).
  Every such method whose arguments are already audited now gets a generated helper: the new C
  entry `kanama_ios_godot_ptrcall_ret_packed` ptrcalls into a packed-array cell, reads the element
  count from the kind's `size` builtin and copies the contiguous elements out in one `memcpy`;
  an array longer than the caller's capacity is parked C-side and drained whole
  (`kanama_ios_godot_take_pending_packed`), so the method runs once and nothing is truncated.
  66 helpers, 154 members on 71 classes move into the shared tree — `AStar2D.get_point_path`,
  `AStar3D.get_id_path`, `Curve3D.get_baked_points`, `Curve2D.tessellate`, `FontFile.get_data`,
  `AudioStreamWAV.get_data`, `RenderingDevice.texture_get_data`,
  `RenderingServer.instances_cull_ray`, `NavigationServer2D.map_get_path`,
  `Geometry3D.segment_intersects_sphere`, `ConvexPolygonShape3D.get_points`,
  `CPUParticles3D.get_emission_points` among them; the gap index goes from 1006 to 852
  desktop-only members (250 → 230 companion files). Seven self-test rows cover int64, Vector2,
  byte (3000 elements through the pending slot) and Vector3 arrays via AStar2D, Crypto and
  Curve3D.

### Added — iOS: Variant-scalar returns on every audited argument shape (task 100, parcel 2)

- A Variant-returning method with arguments was called on iOS only through three hand-written
  helpers (the no-arg and StringName-arg getters, and the owned `ClassDB.instantiate` decode).
  Every such method whose arguments are already audited now gets a generated helper: the new C
  entry `kanama_ios_godot_ptrcall_ret_variant_scalar` ptrcalls into a Variant cell and decodes
  the scalar payload exactly as the Object-call path does (bool, int, float, String family,
  borrowed Object handle, Vector2/Vector2i/Vector3/Color; anything else surfaces `null`, matching
  desktop's `RetVariantScalar`). That decode is now one shared C function, and a String-family
  payload longer than the 1 KiB buffer is parked C-side and drained whole instead of truncated —
  which also lifts the 1 KiB cap the Object-call path (`callWithVariantArgs`) had. 18 helpers,
  56 members on 32 classes move into the shared tree — `StreamPeer.get_var`,
  `PacketPeer.get_var`, `Animation.track_get_key_value`, `PhysicsServer3D.body_get_param`,
  `RenderingServer.material_get_param`, `JSON.parse_string`, `Theme.get_theme_item`,
  `Geometry2D.segment_intersects_segment` among them; the gap index goes from 1062 to 1006
  desktop-only members (252 → 250 companion files). Seven self-test rows round-trip int, float,
  bool, short and 3000-byte Strings and null through `StreamPeerBuffer.put_var` / `get_var`.

### Added — iOS: String, StringName and NodePath returns on every audited argument shape (task 100, parcel 1)

- The iOS backend used to call a String-, StringName- or NodePath-returning method only when
  its exact shape had a hand-written helper (the no-arg getters plus a handful of arg-bearing
  ones on the Object-call decode). Every such method whose arguments are already audited now
  gets a generated helper: one new C entry, `kanama_ios_godot_ptrcall_ret_utf8`, runs the
  method once through the generic ptrcall dispatch and UTF-8 encodes the return, parking a
  value longer than the inline buffer C-side so the caller drains it whole
  (`kanama_ios_godot_take_pending_utf8`). Nothing is truncated (the Object-call decode caps at
  1 KiB) and nothing is re-issued (the no-arg helpers' two-call length protocol is only safe for
  pure getters; `StreamPeer.get_utf8_string` consumes the stream). 29 helpers, 212 members on
  26 classes move from the desktop companions into the shared tree — `TextEdit.get_line`,
  `ItemList.get_item_text`, `Skeleton3D.get_bone_name`, `Animation.track_get_path`,
  `RenderingServer.shader_get_code` among them; the generated gap index goes from 1274 to 1062
  desktop-only members (277 → 252 companion files). Four self-test rows cover the three return
  builtins and a 3000-byte String through the pending slot.

### Fixed — root `build` / `publishToMavenLocal` no longer compile ios-runtime metadata

- `ios-runtime` ships as a static xcframework and is not a Maven/KMP library, but the
  Kotlin Multiplatform plugin still registered a publication for it, and a root `./gradlew
  build` or `publishToMavenLocal` compiled its intermediate `iosMain` source set to Kotlin
  metadata for that publication. That compilation rejects the `@JvmName`/`@JvmStatic` the
  generated wrappers carry ("Declaration annotated with '@OptionalExpectation' can only be
  used in common module sources"), so those two root commands failed while every platform
  compile, the static link and the xcframework lane were green. Pre-existing (the iOS tree
  carried `@JvmName` before the shared tree); found while republishing after #220. The unused
  metadata compilation and publications are disabled; `publishKanamaToMavenLocal` (the gate's
  task) was never affected.

### Changed — one shared generated wrapper tree (task 103)

- **The generated Godot API wrappers are emitted once.** The shared tree
  `src/commonMain/kotlin/net/multigesture/kanama/api` (979 classes) is compiled by the
  desktop JVM module, by `:ios-runtime` and by the Android plugin; the iOS copies under
  `ios-runtime/.../api` are gone. A shared file holds the members both native backends
  can call; the members only desktop/Android can call (no audited iOS ptrcall helper
  yet) are generated as extensions into per-class `<Class>.jvm.kt` companions (277
  classes, 1,274 members) whose headers name the helpers they wait on, all listed in
  the new generated page `docs/reference/generated/ios-shape-gap.md`. A helper landing
  on iOS moves its members back into the shared file on the next regen.
- **Scripts calling a desktop-only member import it by name.** Those members are
  extension functions now, so a class import alone no longer brings them in: add
  `import net.multigesture.kanama.api.getUsedCells` (one line per member), as the eight
  affected demo scripts do (kanama-demos#48). Kanama code, demos and templates
  import by name, never `net.multigesture.kanama.api.*`. Call syntax is unchanged; as
  iOS helper shapes land (task 100) the members move back into the classes and the
  imports become ordinary member imports.
- **Aligned across platforms:** `@JvmStatic` is emitted on iOS too (harmless on
  Kotlin/Native); `Node.createTween()` is `open` on both; the iOS `GodotObject` is no
  longer `AutoCloseable` (like desktop; `RefCounted` still is and owns `close()`); iOS
  `RefCounted.unreference()` is `internal`; the generated iOS `close()` no longer carries
  the deprecated `@ManualGodotLifetimeApi`.
- **Tooling.** One platform-tagged table, `PER_PLATFORM_WRAPPERS`, lists the 56 classes
  that are not shared. `check_wrapper_generator.py` is a single-tree gate (every
  generated file, companion and the gap index must equal a fresh in-process regen; a
  per-platform copy of a shared class fails) and runs in about 5 s instead of 57 s.
  `generate_api_wrapper.py --write-tree` re-adopts the whole tree (`upgrade_godot.sh`
  step 5 uses it); the wrapper audits, property coverage, KDoc sync and iOS stub check
  read the shared tree.
- **Measured (one run each, same laptop, clean builds with the Gradle build cache off):**
  wrapper sources 1,053 + 1,029 files / 302,193 + 181,056 lines in two trees →
  979 shared + 351 desktop + 54 iOS files / 233,944 + 73,482 + 19,573 lines (the
  desktop figure includes the 277 companions, 22,189 lines); drift gate 57.5 s → 4.7 s;
  clean desktop `installAddonJar` 60 s → 73 s (24 % more Kotlin files to compile: the
  companions), warm 4.6 s → 5.7 s; iOS `compileKotlinIosArm64` from clean 46.5 s → 46.3 s;
  `linkDebugStaticIosArm64` 106 s → 103 s.

### Changed — Web wrappers generated from the call contract (task 96)

- **The Web API surface is generated.** Every opcode in
  `scripts/platform_backend_calls.json` now renders one member on the Godot
  class that owns it, in `web-runtime/.../api/generated/<Class>.kt` (107
  classes, one file each), with names, parameter widths and defaults taken
  from `extension_api.json` and properties derived from Godot's property
  table. The eleven demo-named `Web*Api.kt` files and the ~1,360 lines of
  probe classes in `GodotBackendContract.kt` are gone; what stays hand-written
  is explicit (`WEB_HANDSHAPED`: the handle-less `Window` mirror and the
  owner-bound ray query) or lives in the generator's per-class policy.
  `python3 scripts/generate_web_wrappers.py --check` gates drift in
  `local_ci.sh` and `:web-runtime:check`; the wire protocol is unchanged
  (still 22).
- **Source compatibility.** Existing call-site spellings keep compiling: every
  generated member also carries an import-compat extension (`import
  net.multigesture.kanama.api.setProcess` still resolves), so the 25
  hand-written `@Suppress("EXTENSION_SHADOWED_BY_MEMBER")` aliases are
  replaced, not dropped. Members now use Godot's argument names and desktop's
  int widths (`int32` → `Int`), and inherited members surface on their Godot
  owner (`Node.getLocalMousePosition` on `CanvasItem`, collision layers on
  `CollisionObject3D`, root motion on `AnimationMixer`); a few getters that
  used to throw as write-only now read the engine (`Node3D.visible`,
  `Label.text`).
- **Growth metric redefined** (`scripts/web_hand_metric.py`): the Option-B
  reconsider line in `docs/contributing/backends/web.md` now sums every
  hand-written Web family (wrappers, wrapper policy, contract, dispatch
  companion, runtime, script emitter, bridge) against the generated code —
  20,931 / 5,181 (4.04) before, 17,875 / 11,108 (1.61) after — and admission
  PRs paste it.

### Changed — docs consolidation (task 94)

- **One page owns each fact.** Requirements (Godot pin, JDKs, host platforms,
  per-workflow toolchains) and platform status/evidence are stated once, in
  `docs/reference/version-support.md` (new "Requirements" table; the Android
  demo matrix, the validated Android version floors, the iOS device-gate
  evidence and the Web browser floors moved there from the export guides).
  README.md and `docs/index.md` carry the badges (a Web badge is new) and a
  link; the getting-started introduction, the release-kit and store-addon
  template READMEs, and the export guides link instead of restating. The
  export guides (`docs/exporting/{android,ios,web}.md`) are now
  workflow-only: toolchain table, commands, troubleshooting.
- **Pages moved** (no redirects — update bookmarks; the published site keeps
  the same section names):
    - `docs/contributing/android-internals.md` → `docs/contributing/backends/android.md`
      (also gained the Android R8/PanamaPort root-cause history from the export guide)
    - `docs/contributing/web-internals.md` → `docs/contributing/backends/web.md`
    - `docs/internals/reference/ios-backend-architecture.md` → `docs/contributing/backends/ios.md`
    - `docs/internals/reference/ios-backend-handwritten.md` → `docs/reference/generated/ios-backend-handwritten.md`
    - `docs/contributing/api-coverage.md` → `docs/reference/generated/api-coverage.md`
    - `docs/contributing/wrapper-generator-report.md` → `docs/reference/generated/wrapper-generator-report.md`
    - `docs/contributing/gates.md` → `docs/reference/generated/gates.md`
    - `docs/internals/README.md` deleted (it only indexed the two files above
      and listed what had moved out of the folder in July); `docs/internals/`
      is gone.
- The generators (`scripts/api_wrapper_coverage.py`,
  `scripts/api_wrapper_generator_report.py`, `scripts/generate_gates_index.py`,
  `scripts/ios_handwritten_report.py`) write to the new paths, and the
  `local_ci.sh` docs-check stages and `scripts/upgrade_godot.sh` check them
  there. `evidence/gates.json` citations point at the consolidated pages.

### Fixed — lifetime safety (task 98)

- **`GD.isInstanceValid` no longer reads a freed object.** `GodotObject` captures
  its engine instance id once at construction (`instanceId`, via the
  `object_get_instance_id` interface function) and `isInstanceValid` asks
  `is_instance_id_valid` about that id — the same thing GDScript does with the id
  its Variant cached — instead of building an OBJECT Variant from the wrapper's
  raw pointer, which dereferenced the freed object's header. Measured cost of the
  capture on Godot 4.7.2 (macOS arm64, JIT-warm): `GodotObject(handle)` goes from
  ~4 ns to ~9 ns per mint; the `getChildren()` element decode it rides on costs
  125–220 ns per element. iOS mirrors it through the shim's
  `object_get_instance_id` / `object_get_instance_from_id`, replacing the
  "non-zero handle" approximation. Every *other* wrapper member still assumes
  the object is alive; nothing invalidates a wrapper for you.
- **Every RefCounted-derived wrapper refuses use after `close()`.** The generator
  now opens each receiver-bound method with `checkOpen()` (the guard 13
  hand-shaped classes already had), on desktop/Android and iOS, and the same line
  was inserted into the hand-shaped RefCounted classes the drift gate exempts
  (`BaseMaterial3D`, `Material`, `Font`, `SurfaceTool`, …). A call through a
  handle whose `close()` destroyed the object is
  `IllegalStateException("RefCounted handle is closed")` instead of a ptrcall on
  freed memory — 661/661 desktop and 654/654 iOS RefCounted wrappers
  (11,400 generated guard lines). `check_wrapper_generator.py` locks the policy.
- **A throwing callback can no longer take Godot down.** `Upcalls.stub` wraps
  every Godot→JVM stub in `MethodHandles.catchException`: an exception escaping a
  `@RegisterFunction`, a generated virtual dispatcher, the `.kt` resource loader
  or any ScriptInstance callback is logged
  (`[kanama] upcall <Class.method> threw: …`, stack trace once per site) and the
  engine receives the zero of the return type (NIL / `false` / `NULL`) instead of
  the JVM aborting through native frames. Containment used to be nine hand-placed
  catches for 112 upcall targets; the bespoke ones whose return value carries
  meaning (`siCall`, the property accessors) stay on top. No new native adapter
  is linked, so the prewarm gate is unaffected.

### Added — threading note and diagnostic

- `docs/game-dev/scripts.md` gains a **Threads** section: what runs on the main
  thread, that Kanama performs no thread-affinity checks, and that
  `ResourceLoader.load_threaded_request` on a `.kt` script runs the loader — and
  any script constructor it reaches — on a worker thread. `KANAMA_THREAD_DIAGNOSTICS=1`
  logs once per site when `ScriptBridge.siCall` or the `.kt` loader runs off the
  `initialize` thread (a diagnostic, not an assertion; one boolean read when
  unset). `runtime_smoke.sh` gains the `LifetimeSmoke` row (validity across
  `free()`, use-after-close at receiver, inherited, generated and argument
  positions) and the upcall-containment row.

### Fixed — CI change filter skipped the mobile and Web lanes on large PRs

- `ci.yml` and `web.yml` decide whether to run the Android/iOS lanes and the Web matrix
  by grepping the PR's changed-file list. The step runs under `pipefail`, and
  `printf | grep -q` exits on the first match, so on a diff larger than the pipe buffer
  (about 900 files) `printf` died with SIGPIPE and the filter answered **false**: the
  lanes were skipped exactly on the PRs that most needed them (kanama#217, 1,000 files,
  every mobile lane skipped with `ios-runtime/` in the list). The filter now reads the
  list from a here-string. Small PRs were never affected.

### Changed — one resource-ownership rule, and `close()` no longer needs an opt-in

- **Getters are owned; the docs now say so in one place and nowhere contradicts
  it** (task 97). Every `RefCounted`-typed return — `create()`,
  `ResourceLoader.load…`, and plain getters such as `getMesh()`,
  `getAnimation(...)` or the `Tweener` a `tweenProperty(...)` hands back — is a
  `+1` the caller closes; a wrapper you mint yourself over a handle you already
  hold (`fromHandle`/`fromObject`) and a live `Tween` are the only things you never
  close. `docs/game-dev/godot-api.md` "Resource Ownership" is the rule; the
  style guide, `properties-resources.md` and the demo-porting rules lost the
  sentences that still taught the pre-task-62 "do not close what you handed to a
  setter" exception and link instead. The demos parity audit
  (`kanama-demos/scripts/demo_parity_audit.py`) used to fail exactly the closes
  the rule requires; it now enforces the same table.
- **`@ManualGodotLifetimeApi` is deprecated and no longer applied.**
  `RefCounted.close()` (desktop) and the Web `Texture2D`/`AudioStream`/
  `PackedScene`/`ResourceLoader` members carried a `RequiresOptIn(WARNING)`
  that warned on the one call the ownership rule tells you to make. An existing
  `@OptIn(ManualGodotLifetimeApi::class)` still compiles, with a deprecation
  warning; delete it, nothing replaces it. On iOS the annotation is an inert
  marker until the generator stops emitting it on the generated `RefCounted`.
- `GodotObject.call()`, `callDeferred()`, `callv()`, `get()`, `getMeta()` and
  `getScript()` now document that an object result is a **borrowed** view of the
  Variant-path decode — never `close()` it, and it may already be dead when the
  call minted the object (`call("duplicate")`); use the typed wrapper getter or
  `ClassDB.instantiate` to hold one. No behaviour change.

### Added — Web input-map, key-event, process-mode and window-mode families

- **Web protocol 21 → 22.** The Kotlin/Wasm backend admits the call families the
  third-person demo's shared `Player.kt` and `FullScreenHandler.kt` need so they
  can later compile on Web without `web/kotlin-src` overrides (task 64 tier 3):
  `InputMap.hasAction` / `addAction` / `actionAddEvent` / `eraseAction`,
  `InputEventKey.create()` / `from()` with `keycode` / `physicalKeycode` and the
  `KEY_*` constants, `InputEvent.isEcho()` / `isAction()`,
  `InputEventWithModifiers.isAltPressed()`, `Node.setProcessMode` /
  `getProcessMode` with `PROCESS_MODE_*`, `SceneTree.getRoot()`, and a
  handle-taking `Window(...)` with `setMode` / `getMode`. `Node3D.setVisible` /
  `show()` are now members and `InputEventMouseButton.MOUSE_BUTTON_RIGHT` exists.
  One new contract shape (`STRINGNAME_OBJECT_ARG_SINGLETON`) carries the
  action-plus-event call; `actionAddEvent` fails loud if the engine did not
  attach the event. The in-repo `web3d` fixture proves every family delivered a
  VALUE (`Main.input_map_probe`, required by the smoke gate, must return 255).
  **Existing Web exports must be rebuilt**: a protocol-21 export refuses to load
  against a protocol-22 bridge, and vice versa.

### Changed — Backend call contract is Web-local

- **`kanama-common-api` is the Web backend's call table, not a shared platform
  seam** (task 95). The desktop (JVM/Panama) and iOS (Kotlin/Native)
  `CommonGodotBackend` adapters were installed at bootstrap but no native wrapper
  ever dispatched through them; they are removed, together with the native
  modules' dependency on `kanama-common-api` and the Android plugin's source copy
  of it. The module keeps its name and its `jvm` (KSP Web emitter, contract test)
  and `wasmJs` (Web runtime) targets; its unused iOS targets are gone. Every
  `GodotBackendSpi` member is now abstract — the generated Kotlin/Wasm backend
  implements all 87 — so a call shape the generator stops emitting fails at
  compile time instead of at the first call. No user-visible behaviour change on
  any platform; the desktop runtime jar shrinks by about 170 KB (12,106,707 →
  11,936,181 bytes). `docs/contributing/web-internals.md` now describes one
  source (`extension_api.json`) feeding two mechanisms — the native ptrcall
  wrapper generator and the Web contract, whose hashes are validated against the
  same file — instead of claiming native backends consume the contract.

### Changed — Verification hygiene (task 99)

- **The `kanama-common-api` tests now run.** `local_ci.sh` names
  `:kanama-common-api:jvmTest` and `checkPlatformBackendContract` explicitly;
  `gradlew test` never reached the KMP module, so `GodotBackendContractTest` had
  not executed in any CI run since it landed.
- **Gates ledger.** `evidence/gates.json` records each run of the local-only
  gates (device matrices, Safari corpus, host revalidations) with the Godot pin
  and Kanama commit it ran on. `scripts/check_gate_evidence.py`, inside
  `scripts/audit_claims.sh`, fails when a gate's latest run predates the current
  pin unless the entry carries a dated, reasoned `acceptedStaleUntil`. Seeded
  from the dated evidence in Version Support; the 4.7.0-template entries are
  accepted stale until 2026-10-31 and say so on every run. `ios_device_gate.sh`
  and Safari `web_ci_matrix.sh` runs append to the ledger on PASS.
- **Gates index.** `docs/contributing/gates.md` lists every `local_ci.sh`
  stage, CI job and local-only gate — what it proves, where it runs, when it
  landed — generated from the scripts and workflows, with `--check` as a
  local-CI stage.
- **`audit_scalar_float_abi.py` retired**: `audit_ptrcall_helper_layouts.py`
  checks the same float/Color slot rules across every helper, not 30
  name-matched ones. `audit_wrapper_signatures.py` was reviewed for the same
  fate and kept — its `java.lang.Object`/`AutoCloseable`/`GodotObject`
  name-collision checks have no successor.
- `local_ci.sh` prints per-stage seconds (also in the failure banner) and
  writes `build/local-ci-timings.json`. Web quarantine entries carry an expiry
  date; the matrix prints `QUARANTINE EXPIRED` past it (non-fatal for now).

### Changed — Godot baseline

- **Godot baseline re-pinned to 4.7.2 stable** (task 91). `scripts/upgrade_godot.sh`
  classifies the 4.7.0 → 4.7.2 dump as **metadata-only**: the `extension_api.json`
  body and `gdextension_interface.h` are byte-identical apart from the header's
  version fields, so wrappers, name constants, and struct layouts are unchanged and
  the regen churn is version strings plus a KDoc re-sync from the 4.7.2
  `doc/classes` (comment-only). CI now downloads 4.7.2 (`ci.yml`, `web.yml`,
  `package.yml`); requirements, download links, badges, and templates name 4.7.2;
  support-tier labels keep naming the 4.7 stable line. Desktop gates re-ran green on
  the 4.7.2 binary (`local_ci.sh` on macOS arm64 and the nine-demo desktop smoke
  matrix). Mobile device gates and the
  Web browser matrix have not been re-run on the 4.7.2 export templates yet — their
  evidence in Version Support stays dated on the 4.7.0 templates until they are.
- The upgrade tooling now handles maintenance pins: the docs-tree guard in
  `upgrade_godot.sh` dropped the patch component (a 4.7.2 tree read as
  `4.7.stable`), the iOS re-adopt emitted only the non-hand-shaped classes and so
  stripped every method returning a hand-shaped type (`Image`, …) from 15 wrappers,
  and `check_godot_version_pin.py` checked only `package.yml` while `ci.yml` and
  `web.yml` carried their own pins. All three are fixed; `web.yml` derives its
  template folder from `GODOT_VERSION`.

### Fixed — Web behaviour (agent-surface audit, tasks 88/89)

An adversarial audit of the Web backend's agent-written surfaces found and closed
nineteen defects. The ones that change what a running script does:

- **Queued mutations issued from a Kotlin lambda signal callback were silently
  discarded.** Any `queue_free`, property write or `add_child` made from inside a
  `connect { ... }` lambda never reached Godot: the boundary returned without flushing
  the command buffer. Every Web boundary that runs user Kotlin now flushes.
- **Re-parenting a node destroyed its script state.** `_exit_tree` tore the Kotlin
  instance down, so a node removed and re-added — a pooling pattern, or any
  `reparent()` — came back blank. Teardown moved to `NOTIFICATION_PREDELETE`, where
  Godot actually means it. (Protocol 19.)
- **A `queue_free`'d node became uncallable immediately** instead of surviving to the
  end of the frame as it does on desktop.
- **A null element in an exported object array arrived as a live wrapper.** The array
  arm minted a browser handle for `null`, so a script read a real-looking object where
  its scene held nothing. Null now stays null; if the element type is non-nullable the
  build fails naming the property and the fix, rather than fabricating a value.
- **`tween_property` with a NUMBER had no arm.** Vector2, Color and Vector3 all worked,
  so a component path — `tween_property(node, "position:y", 4.0, 0.5)` — faulted the
  Web boundary. (Protocol 20.)
- **`PropertyTweener.from` did not exist on Web.** A tween could not be given a custom
  starting value, so a demo doing so had to drop the call and animate differently on Web
  than on desktop — silently, since nothing failed. The Color arm is in; other value
  types name the arms that do exist rather than failing vaguely. (Protocol 21.)
- Handle-seeding and snapshot-refresh parity fixes for objects arriving through
  sibling paths, and a self-snapshot refresh guarded for nodes outside the tree.

**Web exports must be rebuilt.** The protocol moved 18 → 21 over these fixes (19 for the
re-parent repair, 20 for the scalar tween arm, 21 for `PropertyTweener.from`). The
generated proxies and the JS bridge compare it at startup, so an export built against an
older protocol will refuse to run — rebuild rather than mixing.

### Fixed — gates that certified the wrong thing

Several gates passed without testing what they claimed. These are developer-facing, but
they are why the defects above went unseen:

- The **post-teardown invariant was never enforced at all**, and nine drivers asserted
  it tautologically.
- **A GDScript parse error did not fail the export** — a broken proxy shipped as BUILD
  SUCCESSFUL.
- **Safari reported an empty console it had never observed**, making the
  zero-console-errors pass leg vacuous on the one engine that gate exists for.
- **Safari runs on a locked screen produced meaningless results**: the engine advances a
  few frames and stops, and the run fails inside a demo assertion with nothing naming
  the cause. The driver now refuses to start in that state.
- The envelope schema's pass mirror now encodes all four legs; the protocol check is
  derived from the export manifest rather than hardcoded.

### Added — checks that would have caught them

- **Exercised-member coverage report** naming every declared member no driver reaches,
  and a **desktop↔Web differential probe**.
- **A backend conformance fixture** asserting that every typed property and signal shape
  delivers its *value*, not merely a dispatch.
- **A build failure when a lifecycle annotation is imported but never applied** — a dead
  `@OnReady` had silently disabled a demo's pause-time input handling.
- **Documentation claims are checked against their sources**, so a marked claim fails the
  build when the code moves out from under it.

### Added

- **The Web corpus grew from two demos to twelve.** After 0.4.0 shipped Match3 and
  Bunnymark, dodge-the-creeps, the 3D Platformer, squash-the-creeps, FPS, the
  character-controller tutorial, the third-person controller, Racing, City-Builder,
  and tps-demo were each brought up as production Web exports (#96–#113, protocols
  7–15), adding the 3D rendering foundation, physics, RayCast3D and slide-collision
  queries, Resource-script hydration, object-carrying signals, and a shared handle
  registry along the way. A Web build now rejects any script virtual the proxy does
  not dispatch (#114) and dispatches `_enter_tree` (#145, protocol 16).
- **Web gameplay compiles from one `kotlin-src` per demo**, with per-file overrides
  under `web/kotlin-src` (#133, #136). NodePath exports, hint metadata, and fail-loud
  property guards reach the Web proxy (#148); extension helpers became members on the
  Web wrapper classes and `Resource.fromHandle` / `AudioStreamPlayer.setStream` gained
  Web parity (#144, #146, #150); a generic `callv` fallback covers calls with no typed
  family (#141).
- **Exported desktop games bundle a jlink-trimmed Java runtime**, found app-relative
  before `JAVA_HOME`, so players never install a JDK: `jlinkGameRuntime`,
  `scripts/export_game_assemble.sh`, and `scripts/export_game_smoke.sh` (#140). The
  cross-target variant below builds on this.
- Exported desktop games are published as CI artifacts
  (`kanama-exported-game-<target>`) so the build CI already boots can be tested
  on real hardware without rebuilding.
- Verified on real Windows hardware: a game exported with a jlink runtime
  cross-built on macOS boots from its own bundled runtime even when a system JDK
  is installed, with no VC++ redistributable required.
- **Web exports can be served to a phone for hand-checks**: `scripts/web/serve_export.py
  --lan --https`, since Godot's Web export needs a secure context, with load progress
  and an up-front refusal on plain HTTP (#122, #134).
- **Web exports package into a publishable artifact.**
  `:web-runtime:packageWebExport -PkanamaWebDemo=<demo>` zips an already-built,
  smoke-validated Web export (index.html at the zip root, deterministic
  `kanama-web-<demo>-v<version>.zip` name) after gating it: stale-export
  refusal (demo/protocol/buildId cross-checks against
  `kanama-web/export-report.json`), itch.io's HTML5 defaults (500 MB / 1000
  files, measured output printed either way, tps-demo named as the documented
  638 MB exception), and a byte-level no-local-paths scan.
  `scripts/web_package_smoke.sh` proves the artifact is the game by unzipping
  it to a scratch directory and driving that copy through the full export
  smoke. Publishing guidance (itch.io via butler, generic static-HTTPS
  hosting, why the nothreads export needs no COOP/COEP) is in
  `docs/exporting/web.md` under "Publishing A Web Export". The Web backend's
  status is unchanged: it remains Experimental.
- **Exported desktop games can be built for another platform.** `./gradlew
  jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64` produces the bundled
  JVM runtime for a target other than the host, so a developer on macOS can ship
  a Windows or Linux game — the same expectation Godot's own cross-platform
  export templates set. Targets: `windows-x64`, `linux-x64`, `linux-arm64`,
  `macos-arm64`. Runtime image sizes, all four cross-built from macOS arm64
  against Temurin 25.0.4+7: 31 MB Windows x64, 42 MB Linux x64, 40 MB Linux
  arm64, 31 MB macOS arm64.

  Two things worth knowing if you look under the hood: a Temurin JDK install no
  longer contains `jmods/` at all (JDK 24's JEP 493 lets jlink link from the
  JDK's own run-time image, but only for the platform it runs on), so the task
  fetches Adoptium's separate per-platform jmods download, SHA-256 pinned and
  cached outside `build/`; and the jmods must be the same JDK *feature* version
  as the build JDK, which the task checks before linking.

  `scripts/export_game_assemble.sh` gained `--runtime DIR` handling that refuses
  to pair a runtime image with an export built for a different platform, and
  `scripts/export_game_smoke.sh` gained `--runtime DIR` plus a Windows branch.
  The `package` workflow now cross-builds the Windows and Linux runtimes on a
  macOS job and boots exported games against those artifacts on `windows-2025`
  and `ubuntu-24.04` — a runner building its own runtime would prove only
  same-OS packaging.

### Fixed

- **Web: coroutine delays resume in every demo.** The frame scheduler is now pumped once
  per engine frame from every generated proxy; before, eight of the twelve demos never
  pumped it, so a `delaySeconds` in them hung forever (#157, protocol 18). The real
  `_process` is the bridge's frame fallthrough and the spike benchmark is opt-in (#162);
  physics ticks read a fresh transform snapshot, so held-input movement is no longer
  quantized to the render frame (#170); a spawned script owns its own lifetime instead of
  dying with the script that spawned it (#125).
- **A bundled Windows runtime no longer needs the Visual C++ redistributable on
  the player's machine.** `jvm.dll` lives in `runtime\bin\server` while its CRT
  dependencies ship one level up in `runtime\bin`, and Windows resolves a loaded
  DLL's dependencies against the *executable's* directory and System32 — never
  next to the DLL itself. The bootstrap now registers `runtime\bin` with the
  loader and loads the JVM with the explicit search flags. It also locates
  itself with `GetModuleFileNameW` instead of the ANSI variant, so an export
  under a player profile whose name has no ANSI representation is still found.

- **Web: `@RegisterFunction` shapes that used to throw at runtime now dispatch**
  (Web protocol 16 → 17). The Web emitter models a hand-maintained set of
  supported method shapes; everything else took an `else` arm that emitted a
  stub throwing `Kanama Web gameplay method is not implemented`. That is why FPS
  enemies were immortal on Web — `Enemy.damage(amount: Double)` had no arm.
  Filled in one protocol bump:
  - every all-numeric argument list, through one six-slot crossing:
    `(Float)`, `(Boolean)`, `(Vector2)`, `(Vector3)`, `(Vector3, Vector3)`,
    `(Vector2, Boolean)`, `(Int, Float)`;
  - the whole value-returning category, which previously had no arm at all —
    `String`, `NodePath`, `Int`, `Float`, `Boolean`, `Vector2`, `Vector2i`,
    `Vector3`, `Quaternion` and `Basis` returns from a zero-argument
    `@RegisterFunction`;
  - scalar `@ScriptSignal` payloads reaching Kotlin lambdas. The one-argument
    delivery helper used to discard the emitted value; new typed
    `GodotSignal.connectLong/connectDouble/connectBoolean/connectString/`
    `connectVector2/connectVector2i/connectVector3` overloads receive it, and a
    zero-argument `connect` lambda still runs and ignores it as before.

  The fps Web smoke now shoots an enemy dead, so the player→enemy damage path is
  gated on Chrome and Firefox instead of untested.

  The last two shapes — `(String, Object)` and `(Int, Object?)`, which mix the
  string and object-handle channels — now dispatch too, packed into one string
  over the existing crossing (no protocol change). **An argument shape the Web
  backend cannot dispatch is now a build error** naming the script, the member,
  the shape and the shapes that *are* supported, instead of a stub that throws
  when the engine happens to call it; the same gate covers `@ScriptSignal`
  payloads a Kotlin lambda could not receive. Floats deliberately may not ride
  the mixed-argument crossing (its decimal text would round them), so a float
  mixed with text or an object is one of the shapes the build now rejects.
- `GD.isInstanceValid`, `GD.typeOf` and `GD.hash` now encode their argument as a
  real Variant on desktop/Android (task 78). `GD.encodeVariant` handled six
  scalar types and stringified everything else, so a wrapper object arrived as a
  STRING variant: `GD.isInstanceValid(node)` asked Godot whether a *string* was a
  live instance and returned false for every live object, and `GD.typeOf(node)`
  reported `TYPE_STRING` (the same collapse hit value types such as `Vector3`).
  The encoder now delegates to the shared `BuiltinTypes.initVariantFromAny` used
  by the ptrcall and `Object.call` paths, and fails with a named error instead of
  silently stringifying an unencodable value; the `print`/`str` family keeps its
  `toString()` rendering, now scoped to those text utilities alone. The runtime
  smoke asserts `typeof(node) == TYPE_OBJECT` and `is_instance_valid` true for a
  live node / false after it is freed.
- iOS now mirrors the object-typed export `class_name` fix (task 64 follow-up):
  the script property descriptor bridge gained a
  `kanama_ios_runtime_script_resource_property_class_name` entry (same
  RESOURCE_TYPE/NODE_TYPE policy as desktop) and the shim caches per-property
  StringNames and emits them in the instance property list, so GDScript on an
  iOS export sees e.g. `class_name=AudioStream` instead of empty. Validated on
  an iPhone 15 Pro via the user-script visual smoke, which now asserts the
  engine-visible `class_name` from GDScript (reported through a Kotlin
  `@RegisterFunction`, since Godot-level `print()` does not reach the device
  console) and doubles as a GDScript→Kotlin String/bool call-dispatch check.
- Object-typed Kotlin exports now carry `PropertyInfo.class_name` (task 64,
  follow-up to issue #106): a `@ScriptProperty var x: SmokeResource?` (or an
  engine wrapper slot like `AudioStream?`) previously reported an empty
  `class_name`, so typed GDScript degraded `node.smoke_resource` to plain
  `Object`/`Resource` (no safe static typing or completion). Both metadata
  paths emit it now — the generated instance property list
  (`ClassDB.PropertySpec` → `GDExtensionPropertyInfo`) and the script-level
  dictionaries (`PropertyInfo.from_dict`). Also replaces the always-true
  `_is_placeholder_fallback_enabled` stub with GDScript-parity semantics
  (fallback only for a script that failed to bind), and documents why the
  remaining constant-returning Script/ScriptLanguage virtuals are correct as
  constants. Runtime smoke asserts `class_name` on both paths plus a statically
  typed member read.
- GDScript can now statically type against Kanama `@GlobalClass` script classes
  (issue #106): `@export var x: CustomResource` plus
  `var copy: CustomResource = x` no longer fails with "Cannot assign a value of
  type res://CustomResource.kt … with specified type res://CustomResource.kt".
  The `.kt` resource loader pre-set the script's path inside `_load`, which made
  the engine's post-load `set_path()` early-return without registering the
  script in ResourceCache — so every `load()` of the same `.kt` produced a
  distinct Script object, and GDScript's analyzer (which compares script types
  by identity) rejected the class as its own type. The loader now leaves path
  assignment to ResourceLoader, and `KanamaScript` implements
  `_inherits_script` (same script object, or same bound Kotlin class) instead
  of always answering `false`, so typed containers such as
  `Array[CustomResource]` also match. Runtime smoke now pins the reported
  shape: typed member + typed local assignment, `is` check, load identity, and
  a property round-trip.

### Changed

- `GD.isInstanceValid` now takes `GodotObject?` instead of `Any?`, matching the
  Web backend (task 78). Godot answers false for every non-object Variant, so
  the old signature let an instance id or a string compile and then always
  return false; a wrong argument is now a compile error. New
  `GD.isInstanceIdValid(id: Long)` covers the id spelling — it does not
  dereference the object, so it is the safe check to keep across a `free()`.
  Source-breaking only for callers that passed a non-`GodotObject` value, which
  could not have been working.
- **Desktop/Android: every native call adapter is now generated during bootstrap,
  before Godot can call back into the JVM** (task 83). Linking a Panama downcall
  handle for a `FunctionDescriptor` the process has not seen makes the JVM
  generate native code; doing that while execution is already inside a
  Godot→JVM upcall puts code generation inside a thread-local
  executable-memory transition. `KanamaBinding.init` now calls
  `NativeCallSurface.prewarm()` before installing the lifecycle upcall stubs, so
  an upcall only ever *executes* adapters that already exist. Measured on the
  example project: 16 of the 18 adapter shapes used to be generated inside an
  upcall, between 0.118 s and 0.544 s after launch; the prewarm moves all of
  them to 0.001–0.064 s and costs about 30 ms before Godot's first callback.
  `KANAMA_TRACE_NATIVE_ADAPTERS=1` prints each adapter with its timestamp and
  the first-upcall boundary; the runtime, tool, and hot-reload smokes assert no
  adapter is created after it, and
  `scripts/check_native_call_surface.py` (a `local_ci.sh` stage) fails the build
  if a source change adds a call shape that is not prewarmed. No ABI change.
  This is a hardening change with no known user-visible symptom.

## 0.4.0 - 2026-07-24

### Added

- **Web backend promoted to Experimental (Kotlin/Wasm preview)** on Godot 4.7
  stable. A reproducible source-checkout export workflow (`buildWebScripts` /
  `exportWeb`) turns the two validated demos (Starter-Kit-Match3, Bunnymark) into
  self-contained, cache-busted, HTTP-servable Web exports with a release payload
  report and a versioned export-smoke harness (`scripts/web_export_smoke.sh`).
  Both demos pass the automated smoke in Chrome (the CI gate) and Firefox; in
  Safari, Bunnymark passes automatically and Match3 is verified by hand. Adds the
  [Web export guide](docs/exporting/web.md). Still **not a Supported target**:
  single-thread Compatibility renderer only, no packaged addon, two-demo corpus.

- Create script-backed custom resources from Kotlin with `newScriptInstance<T>()`
  — the equivalent of GDScript's `MyResource.new()` (issue #38). `T` must be a
  `@ScriptClass(attachTo = "Resource")` `@GlobalClass`. It returns an
  `OwnedScriptResource<T>` holding the live `instance` and its owning `resource`;
  `close()`/`use { }` releases the owning reference (the factory takes one, like
  `.new()`, so the resource survives a `ResourceSaver.save`). Construction is
  exception-safe (a failed attach never leaks the reference) and the loaded
  script wrapper is released after attach. **Deferred on iOS** (compile-compatible
  stub that throws at runtime) — supported on desktop and Android, including
  R8-minified Android release (the `@ScriptClass` annotation is now `BINARY`-retained
  and consumer keep rules preserve `@ScriptClass` class names, so
  `T::class.qualifiedName` still matches the registered template under obfuscation;
  device-validated on Pixel 7).

### Changed

- **Source-breaking:** object parameters that Godot 4.7 explicitly marks
  `meta: "required"` are now generated as **non-null**, including the 65
  Resource/RefCounted-derived required arguments that were previously nullable.
  For example `ResourceSaver.save(resource: Resource, …)`,
  `CanvasItem.drawTexture(texture: Texture2D, …)`, and the required-shape
  arguments on `Control`, `InputMap`, `PhysicsServer2D/3D`,
  `PhysicsDirectSpaceState2D/3D`, `Shape2D`, `CollisionObject2D/3D`, `OS`, and
  `Input` no longer accept `null` and marshal via `requireOpenHandle()` without a
  safe-call. This narrows public signatures: a call site passing `null` (or a
  nullable value without a null-check) to one of these now fails to compile —
  which is the honest contract, since the engine rejects null there. Unmarked
  legacy Resource parameters stay nullable, and the audited
  `NULLABLE_OBJECT_PARAM_OVERRIDES` (e.g. `Node.set_owner`) keep their null. The
  policy (`required` wins over resource ancestry) is centralized in the generator
  and locked by `audit_generator_object_policy.py`.

- Desktop/Android `Resource` now extends `RefCounted`, restoring Godot's real
  `Object > RefCounted > Resource` chain (which iOS already had). Previously
  `Resource` was its own wrapper root that re-implemented the refcount lifetime
  and hid `GodotObject`'s surface, so `setMeta`/`getMeta`/`connect`/
  `callDeferred` were unreachable from every `Resource` subclass without an
  `asObject()` hop. They are now callable directly. The duplicated refcount
  policy is gone — `Resource` inherits one implementation from `RefCounted` —
  and the inheritance audit enforces the parent rather than whitelisting
  `Resource` as a root. `asObject()` is kept as a compatibility alias. No
  intended behaviour change to resource lifetime; purely a surface gain.

- `scripts/local_ci.sh` failures are now self-announcing. A failing stage prints a
  banner naming the stage, the failing command, its exit code, and the source line —
  always as the last output, so it cannot be buried. The smoke scripts
  (`runtime_smoke`, `tool_smoke`, `hot_reload_smoke`,
  `hot_reload_in_process_smoke`) additionally repeat the failed assertion *after*
  their ~120-line Godot log dump, along with the full log path. Previously a failed
  assertion printed its reason before the dump, leaving the run looking like a
  non-zero exit with no error message. Also fixes a genuinely silent exit: the
  Kanama version probe ran before any error trap was installed.

### Fixed

- A resource created with `X.create()`, handed to the engine, and then released
  via `close()`/`use { }` is no longer freed out from under the engine (issue
  #91). Assigning `StandardMaterial3D.create()` to a `MeshInstance3D` surface (or
  material) override and closing it dropped the engine's only reference, so the
  material vanished from the saved scene (`Parameter "material" is null`). The
  desktop/Android backend now constructs via Godot 4.7's `classdb_construct_object3`
  (replacing the deprecated `construct_object2`), which returns RefCounted values
  **already owned** — matching what iOS already did. So `create()` is owning and
  `close()` releases only the wrapper's reference while the engine keeps its own.
  **Contract: close what you create** — a created resource you never `close()`
  (or `use { }`) leaks its reference; prefer `X.create().use { … }`. (This also
  subsumes issue #81 and let the earlier `ResourceSaver.save` guard be removed.)

- Migrated off deprecated 4.7 GDExtension functions and converged the JVM and iOS
  backends on the same entry points: `classdb_construct_object3` (was
  `construct_object2` on desktop/Android) and `get_godot_version2` (was
  `get_godot_version`). This is what makes a freshly created resource owned at
  construction (see the `X.create()` fix above), which is why the earlier
  issue-#81 `ResourceSaver.save` protective-reference guard could be removed —
  `save` needs no special-casing now.
- A throwing `@ScriptProperty` accessor no longer aborts the process. Generated
  property get/set dispatch ran inside an FFM upcall stub with no exception guard
  (unlike method calls), so any `Throwable` escaping a user getter/setter unwound
  through native frames and killed the JVM — taking Godot down with it (exit 134
  + `hs_err` dump) instead of surfacing as an engine error. Every exported
  property shape was one unchecked exception away from a process abort. Both entry
  points now contain the `Throwable` and log it: a rejected write keeps the
  previous value and still reports the property as owned; a throwing getter
  nil-initializes the return so the engine does not treat the property as missing
  (which would abort the *calling* function instead).

- `Node.setOwner(null)` now compiles and clears the owner through the typed
  wrapper (issue #60). Godot uses a null owner to clear it (the engine itself
  calls `child->set_owner(nullptr)` while replacing nodes), but the generator
  emitted a non-null `Node` parameter, so the only way to pass null was the
  dynamic `call("set_owner", null)`. Object-parameter nullability is now decided
  by one contextual policy (consumed by both the type renderer and the
  marshalling so they cannot drift): explicitly `meta: "required"` parameters
  stay non-null; Resource/RefCounted-derived stay nullable (legacy); and an
  audited `(class, method, arg)` override admits null for a specific ordinary
  object — seeded with `Node.set_owner(owner)`, each entry citing the pinned 4.7
  source. Generated `Node.owner` is now a writable `Node?` property, and the
  desktop hand-shaped `Node` exposes the same. (Tightening the 65 explicitly
  `required` resource parameters to non-null is a separate, source-breaking
  follow-up.)

- iOS: a `MutableList<T>` `@ScriptProperty` no longer generates non-compiling
  Kotlin/Native. The iOS registrar's list-property setters decode into an
  immutable `List`, which is not assignable to a `MutableList` field
  (`Assignment type mismatch: List<String> vs MutableList<String>`), so an iOS
  build of any script with a mutable-list export failed to compile. The setters
  now append `.toMutableList()` for mutable properties, mirroring the desktop
  emitter — which had handled this all along, while the iOS emitter dropped the
  mutability flag entirely. Covers the string-list, engine-wrapper-list,
  `@ScriptClass`-element-list, and enum-list arms. Immutable `List<T>` is
  unchanged.

- `ClassDB.class_call_static` no longer frees RefCounted instances before
  returning them: like `ClassDB.instantiate` ([#42](https://github.com/falcon4ever/kanama/pull/42)),
  a static factory can hand the fresh instance's **only** reference back inside
  the return Variant, and the borrowed dynamic decode destroyed that Variant
  after extracting the pointer — a use-after-free for every RefCounted result
  (e.g. `RegEx.create_from_string`, `Image.create`). RefCounted results are now
  retained before the Variant is destroyed and come back as the owning
  `RefCounted` wrapper (`close()` releases; assigning into a node/scene refs
  independently, matching C# semantics). Non-RefCounted and non-object results
  are unchanged. Desktop, Android, and iOS (the retain happens inside the shared
  `kanama_ios_godot_object_call` shim, before it destroys the return Variant).

- `newScriptInstance<T>()`'s editor "build a real instance instead of a
  placeholder" override is now scoped to the specific resource being created, not
  thread-wide. A non-`@Tool` script instantiated reentrantly on the same thread
  during the create (e.g. a scene loaded from a resource constructor) was also
  forced to a real instance, bypassing the editor placeholder it should have
  gotten. The override now keys on the owner under construction, so any unrelated
  reentrant instantiation keeps its placeholder. Editor-only.

## 0.3.0 - 2026-07-16

This release closes Kanama's mobile + convergence phase. The headline is
**full cross-platform parity on Godot 4.7 stable**: desktop (macOS arm64,
Linux x64/arm64, Windows x64) and Android are **Supported**, and the new iOS
Kotlin/Native backend graduated from experimental to **Supported** as well —
all running the same generated Godot API wrappers under a cross-platform drift
gate. Wrapper coverage reached ~99% of classes/methods, the docs were
reorganized into consumer/maintainer/internal tracks, and the support tiers
were formalized. See `docs/internals/release-support-decision.md` (moved out of the public docs
tree on 2026-07-14; see `docs/internals/README.md`) for the
grounded support matrix and §7 for the mobile promotion bar. `0.3.0` is a
pre-1.0 preview baseline.

### Added

- Exporting custom `Map` properties from scripts and resources is now supported
  (issue #40). A `@ScriptProperty`/`@Export` `Map<K, V>` registers as a typed
  Godot `Dictionary` (`PROPERTY_HINT_DICTIONARY_TYPE`) so the inspector shows
  the matching key/value pickers. Keys may be `String`, `Long`/`Int`,
  `Double`/`Float`, `Boolean`, Godot value types (`Vector*`/`Color`), or an
  `enum class`; values reach `List<T>` element parity plus scalars/value types
  (scalars, `String`, `Vector*`/`Color`, engine resource/node wrappers, custom
  `@ScriptClass` scripts, and `enum class`). Resource-typed values are
  ownership-managed like `List<Resource>`; enum keys/values store as ordinals.
  Decoding is **fail-soft**: a wrong-typed key or value (a hand-edited entry, or
  a stale `.tscn` saved before the types changed) is skipped instead of throwing
  a `ClassCastException` out of the FFM upcall — which would abort the process.
  Nil-value handling mirrors Godot C#'s engine-backed `Dictionary`: `Map<K, V?>`
  keeps the key with a `null` value (round-tripping through the write path),
  while non-null `Map<K, V>` drops the entry (Kotlin cannot hold null there).
  Nullable object-value maps (`Map<K, Texture2D?>`) are rejected at build time
  with a clear message. iOS defers dictionary-property marshalling (keeps the
  Kotlin default, warns at build) as the enum-list exports do. See
  [Exporting Dictionaries](docs/game-dev/properties-resources.md#exporting-dictionaries).
- iOS `@ScriptProperty` codegen now **fails the build** (was a warning) when a *data* property is
  settable by the engine but not readable (a write-only get/set asymmetry). This is the exact class
  that silently shipped write-only value types and broke multiplayer replication; a new settable data
  type must gain a `getProperty`/`encodeIosReturn` path before it can compile. Object/custom-script
  refs remain intentionally exempt.
- Fixed iOS lambda signal callbacks dropping non-object scalar arguments.
  Emitted `bool`, `int`, and `float` Variants now reach Kotlin with their
  original values, matching desktop and Android; this fixes integer peer IDs
  from multiplayer signals such as `peer_connected` arriving as `null`.
- Fixed script lifecycle notifications re-enabling `_process` and
  `_physics_process` after user code disabled them in `_ready` or
  `_enter_tree`. Authority-gated multiplayer input scripts now remain disabled
  on non-authoritative peers instead of letting one device drive multiple
  players.
- Fixed iOS value-type `@ScriptProperty` fields being write-only. `Vector2`,
  `Vector3`, `String`, `NodePath`, and `List<String>` are now readable by the
  engine (`Object.get`), matching desktop/Android — the iOS bridge previously
  emitted a reader only for properties with a scalar setter, so these types
  were settable from scene data but read back as `nil`. That silently broke
  `MultiplayerSynchronizer` replication of value-type properties on the
  authority peer (e.g. a `Vector2` movement vector never reaching the host, so
  a client could not move or shoot). A codegen parity guard now flags any
  future set-only data property.
- Fixed iOS lambda/bound signal `Callable`s not being auto-disconnected when
  their receiver is freed. The receiver's `ObjectID` is now recorded so Godot
  disconnects the connection on free, instead of later firing the signal into a
  freed object — a use-after-free crash on the client when a multiplayer host
  ended the game.
- iOS `@Export` / `@ScriptProperty` conversion parity: Kotlin `Int`/`Float`
  fields now narrow correctly from Godot's 64-bit Variant slots, scalar enum
  ordinals clamp and resolve to entries, and `List<Enum>` arrays map each
  ordinal with the same clamp/null-fill semantics as desktop/Android. A
  dedicated integer-array bridge keeps enum ordinals separate from object
  handles. iOS property metadata now preserves enum hints and live
  ScriptInstance getters widen values back to engine slots; scene-loaded
  values plus later `Object.set` / `Object.get` updates are covered by the iOS
  project-script probe.
- **iOS and Android promoted from Experimental to Supported (4.7 stable)**: the
  §7 mobile promotion bar (device-matrix breadth, renderer coverage, release-grade
  packaging, heavy-demo) is fully green on both platforms. Android is
  device-validated across four models (Pixel 7 / Moto g 5G 2023 / Galaxy S10+ /
  Pixel 3 XL) — **debug validated to Android 9; release builds require Android
  13+** (validated 14/16; the A12 release-mode PanamaPort constraint is
  documented and upstream-shaped). iOS is device-validated on iPhone 12 + iPhone
  15 Pro. Carried caveats: packaged mobile addons are runtime-only (compiling
  project scripts needs the Kanama checkout), the Android release path depends on
  the JitPack PanamaPort fork (`0.1.3-kanama-r8.4`), and no mobile hot reload.
  `0.3.0` remains a pre-1.0 preview baseline.
- Linux x86_64 promoted to **Supported (4.7 stable)**: full local CI, native
  bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop
  smoke matrix, TPS checked smoke, distribution packaging, and
  desktop-kit/store-addon install smokes passed on Ubuntu 25.04 with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.2 (2026-07-13/14). Requires
  the resource-loader/saver teardown fix below.
- Linux arm64 promoted to **Supported (4.7 stable)**: the same full gate suite
  (local CI incl. the resource-loader/saver teardown `runtime_smoke`, strict
  docs, all 11 demo builds, nine-demo desktop smoke matrix, TPS checked smoke,
  distribution packaging, desktop-kit/store-addon install smokes, and an AArch64
  ELF native preflight) passed on a native Ubuntu 26.04 AArch64 host with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.3 (2026-07-14).
- Fixed a shutdown hang on Linux x86_64: `KanamaResourceFormatLoader` and
  `KanamaResourceFormatSaver` now destroy their explicitly constructed handler
  objects during `unregister()`, before `ClassDB` tears down their extension
  classes. Leaving the live instances behind corrupted/blocked shutdown on Linux
  (orphan `StringName` and allocator diagnostics); desktop processes now exit
  cleanly.
- Windows x86_64 promoted to **Supported (4.7 stable)**: full local
  revalidation on the 4.7 stable console binary (2026-07-13) — demo audits,
  script builds, imports, nine-demo desktop runtime smoke, TPS smoke, and the
  packaged desktop-kit/store-addon install smokes.
- iOS `Mathf.roundToInt` parity helper (Godot `roundi` semantics, half away
  from zero), matching the desktop `Mathf.roundToInt` facade; demo ports can
  now share the same rounding call on both backends.
- `.gdextension` descriptors written by the addon install and packaging tasks
  now always carry the `ios.*.arm64` xcframework entries. Installing the addon
  from Windows/Linux no longer strips the iOS lines from a project's committed
  descriptor (Godot ignores non-target platform entries).
- iOS wrapper breadth (task 30): the generated iOS Godot API class set grew from
  ~254 to **1017 classes — full desktop-equivalent breadth**. Every desktop
  generated class is now emitted on iOS except the documented exceptions
  (`DirAccess`, whose draft depends on a desktop-only hand-authored
  handle-alias class, and `MethodTweener`, which clashes with the hand-written
  iOS Tween glue; `FileAccess` is since hosted as a hand-written static facade). Un-audited per-method marshalling shapes remain conservatively
  skipped with generator-report entries — never stubbed — and the full
  cross-platform drift gate holds committed == fresh regen at the new counts.
- iOS RefCounted return ownership (task 31 mirror): the C shim now exposes
  `object_destroy`, the generated iOS `RefCounted` owns the +1 reference every
  RefCounted-typed ptrcall return transfers (`close()` releases it: unreference +
  destroy at zero), and fluent self-returning methods emit the same
  reference-neutral collapse pattern as desktop/Android. This closes the
  per-call engine-reference leak on RefCounted-typed returns on iOS; an
  on-device refcount probe (`refcounted-ret-owns-plus1`) guards the convention.
- Resource-typed exports widened
  ([#36](https://github.com/falcon4ever/kanama/issues/36)): `@Export` /
  `@ScriptProperty` slots now accept `AudioStream`, `Texture`, `Mesh`,
  `Shape2D`, `Shape3D`, `Font`, `Animation`, and `StyleBox` (base-typed slots
  accept engine subtypes). Reads stay ownership-managed: retained while held,
  released exactly once on script cleanup.
- Subclassing a generated wrapper (e.g. `class X(...) : Resource(...)`) now
  fails the build with a Kanama error naming the supported pattern
  (`@ScriptClass(attachTo = "Resource")` on a plain class) instead of Kotlin's
  opaque internal-constructor error. `lateinit` exports now warn (no inspector
  default; crashes if read before assignment) and point to the nullable
  `= null` shape. Docs: "Resource Slots" + expanded "Custom Resources" in the
  exports guide.
- `@Export` / `@ScriptProperty` now works on Kotlin `enum class` properties
  ([#37](https://github.com/falcon4ever/kanama/issues/37)). Matching C# enum
  exports, the property registers as an `int` with `PROPERTY_HINT_ENUM` and the
  entry names as the hint string, so the inspector renders a dropdown; values
  are stored as entry ordinals, `.tscn`-stored ints deserialize back into the
  enum slot, and out-of-range stored values clamp to a valid entry instead of
  crashing. Enum-entry initializers (`var mode = MyEnum.NORMAL`) are preserved
  as inspector defaults. `@ScriptClass` scripts work across desktop, Android,
  and iOS.
- `@Export` / `@ScriptProperty` now also works on **lists of Kotlin enums**
  (`List<MyEnum>` / `MutableList<MyEnum>`,
  [#40](https://github.com/falcon4ever/kanama/issues/40)). Matching C# enum-array
  exports, the property registers as a typed int Array whose elements carry
  `PROPERTY_HINT_ENUM` with the entry names (the inspector renders an array of
  dropdowns); elements are stored as ordinals, `.tscn`-stored arrays deserialize
  back into the list, and out-of-range stored elements clamp to a valid entry.
  Same scope as scalar enum exports: `@ScriptClass` scripts work across desktop,
  Android, and iOS. `Map<K, V>` exports remain out of scope (non-String
  Dictionary keys are a policy gate).
- `@OverrideVirtual` now supports **every Variant-expressible return type** in
  the Godot 4.7 virtual surface (170 additional virtuals). New Kotlin return
  types: all fixed-element packed arrays (`ByteArray`, `IntArray`, `LongArray`,
  `FloatArray`, `DoubleArray`, `List<Vector2>`, `List<Vector3>`, `List<Color>`),
  `Map<String, Any?>` (Dictionary), `List<Any?>` (generic and typed Arrays),
  `RID`, `Rect2`, `AABB`, `Transform2D`, `Transform3D`, and `Projection`.
  `StringName`-returning virtuals are declared as `String` and enum/bitfield
  returns as `Long` (the engine converts at the call site). Desktop/Android
  cover all families; iOS covers everything except
  `Rect2`/`AABB`/`Transform2D`/`Transform3D`/`Projection` returns (documented
  in the wrapper-maintenance guide). The only excluded virtuals are the 6
  raw-pointer (`void*`/`const Glyph*`) callbacks, which are not
  Variant-expressible by design.
- Added an iOS Kotlin/Native backend that runs full Kanama project
  scripts: a C GDExtension shim plus GENERATED Godot API wrappers (the same
  wrapper generator as desktop/Android) over a C-shim generic `ptrcall`. The core
  self-test and current demo corpus have playable device runs; per-frame Kanama
  binding overhead measured ~0.63 ms on iPhone 12. iOS shipped experimental in
  this cycle and was then promoted to Supported (4.7 stable, above); see
  `docs/internals/active/ios-backend-roadmap.md` (moved out of the public docs tree
  on 2026-07-14; see `docs/internals/README.md`) for its initial gaps.
- Added an iOS hand-written/stub registry: `// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}`
  markers, `scripts/ios_handwritten_report.py` (generates
  `docs/internals/reference/ios-backend-handwritten.md`), and `scripts/check_ios_no_silent_stubs.py`
  (fails CI on an un-annotated silent stub). The current registry is 0 STUB / 0 SUGAR.
- Added iOS physical-device validation tooling: a visual smoke script with an
  optional Kotlin/Native frame probe that updates a Godot `Label` through a
  cached typed `ptrcall`. Simulator checks remain available for compile/link
  debugging only.
- Made the iOS install path build device-only xcframeworks by default, with an
  explicit `kanamaIosXcframeworkMode=full` escape hatch for simulator work.
- Reached iOS demo parity: the public demo corpus runs on device (full 9-demo
  device gate on iPhone 12, playable corpus + singleton/virtual self-tests on
  iPhone 15 Pro, both on Godot 4.7 stable). Landed the iOS long-tail wrapper
  shapes (Transform2D, NodePath/StringName returns, `Packed*`/`Typed*` arrays,
  Variant scalars), `Callable`/vararg support (object+method callables, varargs),
  and non-POD virtual returns (`String`, `PackedStringArray`, `Variant`/`Any?`)
  across desktop/Android/iOS.
- Added `@Rpc` config delivery so Godot receives RPC configuration on all three
  platform backends (desktop, Android, iOS).
- Hardened the Android path: the Godot 4.7 stable debug demo matrix and an
  R8-minified Match3 release APK both pass on a physical Pixel 7. R8 minification
  is validated against Kanama's PanamaPort fork, which fixes an upstream
  sealed-switch miscompile that crashed the FFI bootstrap under R8.
- Grew the generated Godot API wrapper surface to ~99% coverage (classes
  1032/1036 = 99.6%, callable methods 15274/15385 = 99.3%); engine virtuals are
  overridable across POD plus `String`/`PackedStringArray`/`Variant` returns.
  Residual exotic shapes are documented as promote-on-demand.
- Added a full cross-platform wrapper drift gate (`check_full_drift_gate`):
  committed wrappers are byte-identical to a fresh regeneration per platform
  (desktop 985 / iOS 242), making silent cross-platform wrapper drift
  structurally impossible.
- Extended `isEqualApprox` to composite value types (Transform3D, Basis, AABB,
  Rect2, Transform2D, Projection).
- Added a Godot-version pin as a single source of truth (`kanamaGodotVersion`)
  plus a KDoc sync tool and drift check wired into local CI, so a Godot upgrade
  is a repeatable process.
- Added `MobileControls` (virtual joysticks + on-screen buttons) to the
  `tps-demo-kanama` companion demo for Android/iOS touch play.

### Changed

- Updated the Kanama preview baseline to Godot 4.7 stable. Re-dumped
  `extension_api.json` and regenerated Godot API name constants for the stable
  version metadata; the 4.7 stable `extension_api.json` (excluding the
  `version_status` header field) and GDExtension interface header are
  byte-identical to 4.7 rc 2, so generated bindings, wrappers, name constants,
  and struct layouts are unchanged. Docs, scripts, templates, and the demos
  README were updated from `4.7 rc 2` to `4.7 stable`.
- Updated the Kanama preview baseline to Godot 4.7 rc 2. Re-dumped
  `extension_api.json` and regenerated Godot API name constants for the rc 2
  version metadata; the rc 2 GDExtension interface header and API body are
  byte-identical to beta 5, so generated bindings and wrappers are unchanged.
- Updated the build toolchain to Kotlin 2.3.21, KSP 2.3.9, and
  kotlinx.coroutines 1.11.0, with Gradle build cache enabled for the main and
  Android plugin builds.
- Enabled Kotlin Multiplatform cinterop commonization for the experimental
  iOS runtime.
- `installIosAddon` now preserves a project's Android (and desktop)
  `kanama.gdextension` library entries instead of overwriting them, so installing
  the iOS addon no longer regresses Android support (it mirrors `installAddonJar`'s
  Android-metadata preservation and asserts the entries survive).
- Reorganized the documentation into consumer / maintainer / internal tracks and
  refreshed the agent-facing guides (`AGENTS.md`, `CLAUDE.md`) so a fresh session
  can orient quickly.
- Refreshed generated wrapper KDoc against Godot 4.7 stable `doc/classes`
  (comment-only; verified zero code change).
- Updated the public support wording: desktop (macOS, Linux x64/arm64, Windows)
  and mobile (Android, iOS) are all **Supported (4.7 stable)** following the §7
  mobile promotion bar and the desktop 4.7-stable revalidations above.

### Fixed

- `ClassDB.instantiate` no longer frees RefCounted instances before returning
  them ([#42](https://github.com/falcon4ever/kanama/pull/42)): the engine hands
  the fresh instance's **only** reference back inside the return Variant, and
  the borrowed dynamic decode destroyed that Variant after extracting the
  pointer — a use-after-free that crashed or silently dropped anything
  instantiated by class name (the canonical construction path for third-party
  GDExtension classes such as Terrain3D). RefCounted results are now retained
  before the Variant is destroyed and come back as the owning `RefCounted`
  wrapper (`close()` releases; assigning into a node/scene refs independently,
  matching C# semantics). Non-RefCounted results (Nodes) are unchanged.
  Desktop, Android, and iOS (dedicated C-shim entry: the retain must happen
  before the shim destroys the return Variant). Thanks @T-bond for the
  diagnosis and repro.
- Android runtime now works below Android 14: the binding runtime used
  `Path.of(...)` (a Java 11 API Android only ships from API 34) in the `.kt`
  resource loader/saver and the scripts-jar lookup, so script loading died
  with `NoSuchMethodError` on older devices; switched to the equivalent
  `Paths.get(...)` (API 26+). Found and device-validated on a Galaxy S10+
  (Android 12) during the second-device promotion gate, together with a
  PanamaPort-fork fix for the same class of bug (`SDK_INT_FULL` reads that
  crash pre-Android-16 devices).
- Android release builds no longer hit an R8-emitted `filled-new-array` of
  `byte[][]` in PanamaPort's stub generation (the ART interpreter below
  Android 13 segfaults on that instruction): PanamaPort fork
  `0.1.3-kanama-r8.4` routes single-blob code generation through a
  non-varargs path. Validated Android version floors are now documented in
  the Android export guide ("Validated Android Versions"): debug builds
  Android 9+, release builds Android 13+ (validated 14/16 — below 13,
  release-mode ART crashes in PanamaPort's LLVM-driven upcall generation;
  characterized and deferred upstream).
- `@GlobalClass` Kotlin scripts now actually register in the editor's global
  class list ([#39](https://github.com/falcon4ever/kanama/issues/39)): they
  appear in the Create New Resource dialog, and `.tres` resources using them
  match typed export slots (the *"selected resource (Resource) does not match
  … (MyClass)"* rejection reported in
  [#38](https://github.com/falcon4ever/kanama/issues/38) is gone). The script
  language answered the editor's `_handles_global_class_type` routing query
  with global-class-*name* semantics instead of script-resource-*type*
  semantics ("Script" for `.kt` files), so the editor never queried
  `_get_global_class_name` and no Kotlin class ever reached the global class
  cache. A `@GlobalClass` in a file not named `<ClassName>.kt` now gets a
  build warning (the class cannot be mapped back to its script file and stays
  out of the list).

### Known Limitations

- `0.3.0` is a pre-1.0 preview baseline.
- Packaged mobile addons are runtime-only: compiling project scripts still
  requires the Kanama checkout, and there is no hot reload on mobile.
- The Android release path depends on the JitPack PanamaPort fork
  (`0.1.3-kanama-r8.4`). Debug builds run on Android 9+; release builds require
  Android 13+ (validated 14/16 — below 13, release-mode ART crashes in
  PanamaPort's LLVM-driven upcall generation).
- `Map<K, V>` exports remain out of scope (non-String Dictionary keys are a
  policy gate).

## 0.2.2 - 2026-06-05

### Changed

- Updated the Kanama preview baseline to Godot 4.7 beta 5.
- Refreshed `extension_api.json` and generated Godot API name constants for the
  beta 5 version metadata; the beta 5 API/header body matches the beta 4 input
  used by the previous preview.
- Updated Gradle coordinates, release packaging defaults, documentation, and
  demo project defaults for Kanama `0.2.2`.

### Known Limitations

- Android remains experimental. Godot 4.7 beta 5 Android APK smoke validation is
  pending before changing Android support claims.

## 0.2.1 - 2026-05-26

### Added

- Fresh-clone smoke validation for release checks and clean source checkouts.
- A ready-to-run starter project creation task for first-time Kanama projects.

### Changed

- First-run documentation now starts from the new starter project flow and
  source-install validation path.
- Clean Gradle environments now get explicit JVM memory defaults.

### Fixed

- Local CI now creates the generated Godot GDExtension header when it is missing
  from a fresh checkout.
- Maven local validation now publishes/checks the current Kanama version in the
  effective Maven local repository.

## 0.2.0 - 2026-05-26

### Added

- Godot 4.7 beta 4 API baseline updates for generated wrappers, docs, and
  smoke validation.
- Editor workflow helpers for opening Kotlin sources and common build actions
  from the Kanama Tools dock.
- Basic Kotlin syntax highlighting in the Kanama Tools editor integration.
- Additional export inspector metadata support, including property hints,
  categories, groups, subgroups, and inspector tool buttons.
- Typed signal helper overloads for common connect, emit, and await usage while
  keeping the existing string-based signal APIs available.
- Convenience editor-time script helpers for `@Tool` scripts, including editor
  hint checks and inspector/property-list refresh.
- Generated engine-wide `MethodName`, `PropertyName`, and `SignalName`
  constants for type-safe Godot API name references.
- Multiplayer and porting guardrail audits for risky runtime node lookups, raw
  string dispatch, and `SceneReplicationConfig` custom properties.
- Multiplayer docs covering generated RPC helpers, ENet peer setup, replicated
  script properties, and review checklist items for ports.

### Changed

- Script/runtime hot paths are leaner, including vector math, object-array
  decoding, string-name handling, and common object call paths.
- Runtime diagnostics now report more lifecycle and script binding context when
  debugging editor/runtime integration failures.
- Example project registries are separated from runtime registration paths so
  local examples do not hide integration drift in external projects.
- Runtime and local CI smoke checks now assert the QoL metadata, tool button,
  and generated name-constant coverage.
- The replicated-script-property audit can now check multiple project roots in
  one run, so demo aggregate checks can use the same guardrail script as local
  CI.

### Fixed

- Script object lifecycle and property replay now preserve inspector-authored
  values more reliably across load/reload paths.
- Retained resource wrapper lifetime handling no longer drops resource handles
  too early in common script property flows.
- Custom resource script properties now release only the retained custom
  resource handle during parent cleanup. They no longer recursively clean the
  child script's exported resource properties, which could close shared
  resources such as `PackedScene` models too early.
- Getting-started, README, local docs preview, and release-validation examples
  were corrected for the current source-first workflow.

## 0.1.0 - 2026-05-19

### Added

- Desktop Kotlin script runtime for Godot through GDExtension and the JDK
  Foreign Function & Memory API.
- KSP-based script registration for lifecycle callbacks, exported properties,
  signals, global classes, and editor tool scripts.
- Hot reload support for desktop editor workflows.
- Generated Godot API wrappers with reproducibility checks and ABI policy
  audits.
- Promoted Kotlin wrapper classes for the Godot 4.7 beta 4 API baseline, with
  conservative method coverage documented in the wrapper coverage report.
- Generated KDoc carried from Godot API documentation for wrapper classes and
  methods.
- Starter project template and example smoke-test project.
- MkDocs documentation covering setup, API usage, distribution, wrapper
  coverage, and maintainer internals.
- Companion demo projects used as integration coverage for real gameplay code.
- Experimental Android export workflow through a Godot Android plugin AAR,
  Android ART, and PanamaPort, smoke-tested on emulator/Pixel 7 paths with
  eight public demo targets.

### Changed

- Wrapper generation is now an active source-promotion path instead of a report-only
  experiment.
- Public documentation has been organized around getting started, porting,
  manual pages, reference coverage, and internals.

### Known Limitations

- Kanama is desktop-first. macOS arm64 is the active development and smoke-test
  platform; Linux and Windows have runtime/demo smoke coverage with remaining
  automated editor/tool shutdown caveats documented in Version Support.
- Android exports are experimental. The current path includes Android smoke
  targets with selected touch overlays, D-pad controls, virtual joysticks, and
  demo warmup fixes for first-use hitches, but Vulkan/Mobile renderer coverage,
  hot reload, and complete phone-specific UI polish are not release claims yet.
- iOS is not supported. Web export is not planned.
- Broad `Callable`, `Dictionary`, generic container, virtual override, and
  ownership-sensitive APIs remain conservative policy buckets.

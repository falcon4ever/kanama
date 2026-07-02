# iOS Backend Roadmap

This is the public-safe plan for taking the iOS Kotlin/Native backend from demo parity to a supportable
mobile backend, then folding the remaining mobile work back into the broader wrapper-generator roadmap.
Deep implementation notes live in [ios-backend-architecture.md](../reference/ios-backend-architecture.md);
demo status lives in [ios-demo-port-tracker.md](./ios-demo-port-tracker.md); generated hand-written-site
accounting lives in [ios-backend-handwritten.md](../reference/ios-backend-handwritten.md).

## Current Position

**Status refreshed 2026-06-25:** iOS is experimental but now covers the Android-enabled public demo set
plus Bunnymark. The current physical-device demo corpus is playable on recent iPhones:

- Bunnymark
- Starter-Kit-Match3
- Starter-Kit-3D-Platformer
- godot-demo-2d-dodge-the-creeps
- godot-demo-3d-squash-the-creeps
- godot-4-3d-character-controller
- Starter-Kit-Racing
- Starter-Kit-FPS
- godot-4-3d-third-person-controller

This is **demo parity**, not desktop-level support. Desktop remains the primary supported runtime and
package target for the 0.2.2 preview line. iOS should stay described as experimental until the export,
validation, and maintainability gates below are complete.

## Why iOS Still Has Exceptions

The direction is still generated wrappers over hand-written bindings. The current exceptions are mostly
platform/runtime glue:

- iOS cannot use JVM/Panama, so wrappers call a Kotlin/Native `ObjectCalls` backend over the C
  GDExtension shim.
- Some high-traffic iOS classes are still hand-written in `IosGodotApi.kt` because they mix singleton
  access, coroutine/frame-loop behavior, Variant-call paths, or convenience APIs.
- The FPS F2 fix is an example of a temporary exception: generated iOS `Node.createTween()` is now
  `open` so the hand-written iOS `SceneTree` subclass can override it with the correct
  `SceneTree.create_tween` method bind. This should disappear when SceneTree is generated or when the
  generator can emit subclass-specific overrides for these collision cases.

The invariant remains: no silent no-op API stubs. `ios-backend-handwritten.md` and
`scripts/check_ios_no_silent_stubs.py` are the guardrails.

## Roadmap To Stable iOS

### 1. Export Workflow

Turn `docs/exporting/ios.md` into a real user-facing workflow, not a maintainer smoke harness:

- Required Xcode/Godot/JDK/Kotlin setup.
- How to install the Kanama iOS addon into a project.
- How to build the XCFramework and Godot iOS export.
- Signing/provisioning expectations and common failures.
- How existing desktop/Android project entries coexist with iOS entries.

Exit gate: a fresh project can follow the docs from checkout to an installed iOS app without relying on
private handoff notes.

### 2. Physical-Device Gate

Create a repeatable iOS validation gate comparable to Android smoke work:

- Re-run the full current demo matrix on at least one recent iPhone.
- Include a fresh starter-project install path, not only existing demo repos.
- Record self-test baselines in public docs without private device IDs.
- Keep the device-run scripts private-value-safe and useful for batching.

Exit gate: the iOS demo matrix and fresh-project path pass after a clean checkout/install flow.

### 3. Runtime Support Blockers

Close or explicitly document the remaining runtime blockers:

- `@Rpc` config delivery to Godot is wired for desktop/Android and the iOS C-shim path. Full
  `tps-demo-kanama` iOS validation still depends on the remaining wrapper-coverage work tracked by
  task 08.
- AVAudioSession Ring/Silent behavior: resolved in the iOS shim by setting
  `AVAudioSessionCategoryAmbient` at startup, with the default and override guidance documented in
  `docs/exporting/ios.md`. Device-validated on iPhone 12 with the FPS demo:
  built-in speaker audio plays in Ring mode and is muted in Silent mode.
- Any crash/regression surfaced by the full matrix after the export workflow is rebuilt.

Exit gate: the remaining limitations are either fixed or intentionally listed in user docs.

### 4. Hand-Written iOS Surface Reduction

Keep `IosGodotApi.kt` small and justified:

- Re-run `scripts/ios_handwritten_report.py` after moving any class from hand-written to generated.
- Prefer generator custom sections or generated wrappers over adding methods directly to
  `IosGodotApi.kt`.
- Retire temporary class collisions such as hand-written `SceneTree` / `Tween` only when the generated
  path can preserve the bespoke runtime behavior.

2026-07 pass (task 10): once tasks 08 (long-tail shapes) and 09 (Callable/vararg) landed, the audited
shapes made three hand-written singleton files clean generated supersets, so they were retired to
generated wrappers (14 -> 11 HANDWRITTEN):

- `Time` — `get_ticks_msec` (and the rest of the singleton) is a plain audited ptrcall.
- `InputMap` — `has_action`/`add_action`/`action_add_event` are StringName/Object ptrcalls (task 08).
  Note: generated `add_action` uses Godot's real default deadzone (0.2) instead of the legacy 0.5;
  demo actions are keyboard-mapped, where deadzone is irrelevant.
- `PhysicsServer3D` — `body_add_collision_exception` (two-RID) and the Callable-arg monitor callbacks
  (task 09 PT_CALLABLE) are now generatable; the generated wrapper is a byte-identical superset for the
  demo-used methods and needed no new `ObjectCallsGenerated.kt` helper (all RID/Callable shapes already
  emitted by other union classes).

Remaining hand-written singleton/glue exceptions are documented in-line as permanent, each blocked by a
specific generator limitation rather than an un-audited shape:

- `Engine` — `get_main_loop()` returns a `MainLoop`, which has no iOS wrapper class, so the generator
  drops the method. `SceneTree.active()` needs it for the static `SceneTree.quit()`/`unloadCurrentScene()`
  forms. Retire when `MainLoop` becomes a wrapper or the generator emits raw-handle object returns.
- `ProjectSettings` — the only demo-used method is `getSettingDouble`, a bespoke `Variant -> Double`
  coercion over `get_setting`; the generator emits no coercing helper.
- `ResourceLoader` — demos use the typed loaders (`loadTexture2D`/`loadAudioStream`/`loadPackedScene`)
  that pass a `type_hint` and wrap to the concrete type; the generator emits only the bare `load()`.
- `SceneTree` / `Tween` — bespoke runtime (createTween F2 fix + `open` override, coroutine
  `delaySeconds`, Variant `tween_property` path, static companion forms).

Retiring the `Engine`/`ProjectSettings`/`ResourceLoader` sugar depends on generator custom sections
(task 11) or a `MainLoop` wrapper, so they stay hand-written but documented.

Device-validated on iPhone 15 Pro (iOS 26.5, Godot 4.7): Starter-Kit-FPS (the `SceneTree.createTween`
F2 regression check) and godot-4-3d-third-person-controller (exercises all three retired singletons —
`InputMap`, `Time`, `PhysicsServer3D`) both launch with self-tests green (`PTRCALL SELFTEST MATRIX:
57 passed, 0 failed`; `OBJECTCALLS SELFTEST: 82 passed, 0 failed`) and no crash.

Exit gate: 0 STUB / 0 SUGAR remains true, and remaining HANDWRITTEN entries are platform/runtime glue,
not missing wrapper coverage.

## Mobile Parity With Android

Android and iOS should converge on the same public support story:

- Both have a documented export path.
- Both have a physical-device smoke gate on the current Godot baseline.
- The Android-enabled public demo set passes on both.
- Support wording distinguishes "mobile experimental" from desktop until packaging and validation are
  release-grade.

When both mobile backends meet that bar, mobile parity work should stop being demo-led and move back to
the shared generator/runtime backlog.

## Post-Mobile-Parity Generator Roadmap

The generator still has important unfinished work after the demo corpus is green.

### 1. Common Wrapper Source Set

Unify generated wrappers toward `commonMain` plus `expect/actual ObjectCalls`.

Why it matters:

- Desktop wrappers currently live in the root JVM module while iOS wrappers live in `:ios-runtime`.
- This allows wrapper drift, including temporary fixes like iOS-only `Node.createTween()` openness.
- A common wrapper layer would make generated API shape identical across desktop, Android, and iOS.

This is a high-blast-radius migration and needs a design pass before implementation.

### 2. Long-Tail Method Shapes

Finish the non-demo wrapper gaps:

- Callable and vararg support across platforms.
- Generic `Array` / `Dictionary` / `Variant` container semantics where policy is still conservative.
- Remaining string/packed/typed-array returns and odd argument mixes as they become worth supporting.
- Value-returning virtuals beyond the currently audited POD set, such as String/Packed/Variant returns.

Rule: widen shapes only with generator fixtures, ABI audits, and iOS matrix/ObjectCalls coverage where
the shape touches the C shim.

### 3. Generator Policy Cleanup

Reduce ad hoc exceptions:

- Make generated class-collision handling explicit so hand-written classes do not silently block future
  generated coverage.
- Preserve subclass-specific overrides without manually editing generated files.
- Keep custom companion/member sections documented and fixture-tested.
- Refresh `api_wrapper_generator_report.py`, `api_wrapper_coverage.py`, and the wrapper coverage docs
  whenever support claims change.

### 4. Android Hardening

Android still needs release-grade validation alongside iOS:

- Pixel 7 / current-device smoke on Godot 4.7 stable. _(Debug matrix passed
  2026-06-26.)_
- R8-minified APK smoke gate for `consumer-rules.pro`. _(Passed 2026-06-26 on
  Pixel 7 for Starter-Kit-Match3 using Kanama's PanamaPort fork
  `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.2`; upstream PanamaPort
  `v0.1.3` remains the unfixed sealed-switch path.)_
- Check that package/install flows remain intact after mobile generator changes.

### 5. Release Support Decision

Only after the above should docs move from "experimental mobile export" toward a stronger claim:

- Decide whether iOS and Android are supported together or remain separate support tiers.
- Define which device/OS/Godot/JDK matrix is required before a release tag.
- Move relevant internal smoke knowledge into user docs, keeping private signing/device details out.

## Demo Matrix

| Demo | iOS status | Remaining relevance |
|---|---|---|
| Bunnymark | Playable | Performance/readout regression check. |
| Match3 | Playable | Baseline 2D starter reference. |
| 3D-Platformer | Playable | NodePath and 3D starter reference. |
| dodge | Playable | Signals, timers, touch controls. |
| squash | Playable | 3D physics/input/math regression check. |
| character-controller | Playable | Frame-deferred work, reload/respawn, animation wrappers. |
| Racing | Playable | Mobile controls and camera follow. |
| FPS | Playable | User-script resource lists, Tween, audio player churn, generated AnimatedSprite3D. |
| third-person | Playable | Variant Vector3, AnimationTree playback, PackedStringArray script properties. |
| City-Builder | Desktop-only by design | Desktop-focused controls (GridMap/custom-list); not a mobile target and not reassessed for mobile. |
| tps-demo-kanama | RPC unblocked; iOS wrappers and mobile UI deferred | `@Rpc` config delivery is wired on all runtime paths. Full iOS launch still needs the task 08 wrapper gaps; mobile playability (touch controls, mobile multiplayer UI) is deferred — see `kanama-tasks/19-tps-mobile-virtual-joysticks.md`. |

## Validation Commands

Use the narrowest useful check while iterating, then a device gate for runtime changes:

```sh
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer ./gradlew compileKotlinIosArm64
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer ./gradlew installIosAddon \
  -PkanamaIosProjectDir=<demoDir> \
  -PkanamaIosProjectScriptsDir=<demoDir>/kotlin-src \
  -PkanamaXcodeDeveloperDir=/Applications/Xcode.app/Contents/Developer
python3 scripts/ios_handwritten_report.py --markdown docs/internals/reference/ios-backend-handwritten.md
python3 scripts/api_wrapper_generator_report.py --markdown docs/contributing/wrapper-generator-report.md
python3 scripts/api_wrapper_coverage.py --markdown docs/contributing/api-coverage.md
```

Device launch commands need private device/signing values from the private handoff and should not be
copied into repo docs.

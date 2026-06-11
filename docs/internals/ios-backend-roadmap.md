# iOS Backend — roadmap & sync guide

How to keep the experimental iOS (Kotlin/Native) backend in sync with desktop/Android, the
guardrails that protect it, and the prioritized backlog. Deep design lives in
[ios-backend-architecture.md](./ios-backend-architecture.md); the generated registry of
hand-written/stub sites is [ios-backend-handwritten.md](./ios-backend-handwritten.md).

**Status:** experimental, **proven viable**. Same wrapper generator as desktop/Android
(`scripts/generate_api_wrapper.py` iOS target → `ObjectCalls.*` over a C-shim generic
ptrcall). Match3 + the Kenney 3D platformer run device-validated; per-frame Kanama
script+binding overhead measured ~0.63 ms on iPhone 12 (~4% of the 60fps budget — FFM-class,
not reflection/Variant). Labeled experimental on `main`; broadening to "standard Kanama" is a
scoping decision, not a research risk.

## Architecture in one paragraph

iOS forbids JIT, so there is no JVM/Panama. Instead: Kotlin/Native AOT + a C shim
(`ios/bootstrap/kanama_ios_shim.c`) over the GDExtension ABI. Godot API wrappers are
GENERATED (the desktop model) and call `ObjectCalls.<helper>(bind, receiver, args)`; on iOS
the `ObjectCalls` backend is the C-shim generic ptrcall `kanama_ios_godot_ptrcall` (vs
desktop Panama/FFM). A conservative **audited type set** gates which method shapes get
emitted; everything else is skipped (not silently wrong). See architecture.md for the
authoritative ptrcall width table and the contract.

## Guardrails (what keeps iOS correct + non-regressing)

- **Audited type set** — the generator only emits method shapes whose arg/return kinds are
  audited (bool/int32/int64/uint32/enum/bitfield/float/Object/Vector2/Vector3/StringName
  args; Unit/Boolean/Int/Long/Double/Vector2/Vector3/Object returns). Unaudited shapes go to
  the skip report, never a wrong call. Widen deliberately (see "staying in sync").
- **Self-test matrix + ObjectCalls probe** — on-device ptrcall round-trip matrix (11/11) +
  a Kotlin ObjectCalls probe, with 0 guardrail hits
  (`kanama_ios_check_call_error` / `check_variant_arg`). Run on every device validation.
- **No-silent-stubs check** — `scripts/check_ios_no_silent_stubs.py` (wired into
  `scripts/local_ci.sh`) fails on an un-annotated bare-default return in the iOS api layer.
  Every hand-written/stub/sugar site carries a `// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}`
  marker; `scripts/ios_handwritten_report.py` regenerates
  [ios-backend-handwritten.md](./ios-backend-handwritten.md).
- **Unwired-annotation warning** — `parseIosScript` (`ios-runtime/build.gradle.kts`) prints
  `WARNING: [kanama-ios] <file> fun <name>: @<Annotation> is not wired on iOS (silent no-op)`
  for known-but-unhandled annotations (OnEnterTree/OnUnhandledInput/OnShortcutInput/
  OnUnhandledKeyInput/Rpc), so a demo using one is visible at build time.
- **Cross-platform non-regression** — `installIosAddon` preserves a project's Android (and
  desktop) `kanama.gdextension` entries (it mirrors `installAddonJar`'s
  `preserveAndroidExtensionMetadata` + asserts Android entries survive). The iOS install must
  never drop android/desktop libraries. Verify Match3 + 3D-Platformer still build on Android
  after iOS changes.
- **Generator fixture** — `scripts/check_wrapper_generator.py` includes an iOS Node3D fixture;
  desktop fixtures must stay green (iOS generator changes are `IOS_AUDIT_ONLY`-gated).

## How to stay in sync with desktop / Android

- **Desktop adds a wrapper class / method** → regenerate the iOS wrappers for the classes a
  demo needs (`scripts/generate_api_wrapper.py --ios-emit-class ...`), regenerate the union
  `ObjectCallsGenerated.kt`, review the skip report. If a needed method uses an unaudited
  type, see "widen the audited set" below.
- **Widen the audited set** (e.g. Vector2i/Transform3D/Color/String-return) → add the kind to
  `IOS_ARG_KINDS` / `IOS_RET_KOTLIN` in the generator AND a width-sensitive self-test matrix
  row + an ObjectCalls probe row + (if a value type) the iOS `types/` struct. Never widen
  without a matrix row — the width is the whole correctness story.
- **Hand-augmented generated files** — Node/AnimationPlayer/CanvasItem/Label/Viewport carry
  `// KANAMA-IOS-SUGAR` blocks that regeneration overwrites. After regenerating, re-add them
  (they're listed in the handwritten registry). The durable fix is iOS generator
  custom-sections (backlog).
- **New Kanama annotation** → wire it in the iOS `parseIosScript` `when` (and add a bridge
  kind if it needs argument dispatch); otherwise add it to `IOS_UNWIRED_FUNCTION_ANNOTATIONS`
  so the build warns. The real fix is consuming the KSP model instead of the regex parser
  (backlog).
- **Signal handler with a new arg shape** → add an `IosScriptBridgeKind` (current:
  ZERO_ARG, DOUBLE_ARG, OBJECT_ARG, OBJECT_OBJECT_LONG_ARG, VECTOR2I_ARG, LONG_ARG) end to
  end: build.gradle codegen + C-shim `kanama_ios_script_instance_call` branch + runtime
  dispatch. (The platformer coin scoring bug was a missing LONG_ARG bridge.)

## Backlog (feature gates, prioritized; none block the experimental merge)

- **Widen audited types** — Vector2i/Vector3i (Match3/City-Builder properties+signals),
  Transform3D/Basis (FPS, third-person), Color/Rect2 returns, String-return ptrcall (unblocks
  `Label.text` get, `AnimationPlayer.getCurrentAnimation` live read), Variant/typed-arrays/
  varargs. Each = generator kind + matrix row + probe.
- **More bridge kinds** — multi-arg signal handlers (beyond OBJECT_OBJECT_LONG_ARG), value-arg
  signal payloads (Vector2/Color → currently UNSUPPORTED/`null`).
- **Annotation parser** — extend to the full ~35 annotations as demos need, or (better)
  consume the KSP `*ScriptRegistrar` model instead of the hand-rolled regex parser in
  `ios-runtime/build.gradle.kts` (a parallel, incomplete reimplementation today).
- **@ScriptProperty value types** — NodePath/Vector/Color property delivery (platformer
  `view: NodePath` keeps its default today). List<custom-class> (only List<Object> works).
- **Real String-return ptrcall** — replace the `Label.text` "" stub + the
  `getCurrentAnimation` last-play() cache; unblocks string getters generally.
- **`GodotObject.call` / setDeferred / disconnect** — wire the Variant Object dispatch path
  (currently STUBs).
- **real_t precision** — iOS hardcodes float32; centralize via an iOS `GodotReal` equivalent
  (desktop reference: `net.multigesture.kanama.types.GodotReal`) to survive double-precision
  Godot builds. Low priority while targeting the single-precision iOS template.
- **commonMain sharing** — move the iosMain wrapper copies toward `commonMain` +
  `expect/actual ObjectCalls` to reduce desktop/iOS drift.
- **Non-object signal payloads to lambda callbacks** — the custom-Callable dispatch forwards
  only object-typed args (up to 4); int/float/bool/string/vector arrive `null`. Pass per-arg
  variant type+value through `kanama_ios_callable_trampoline` →
  `kanama_ios_runtime_dispatch_callable`. Low priority (real lambdas are 0-arg/1-object).
- **`_get_script_signal_list`** — stubbed empty array; editor-only introspection, irrelevant
  on-device. Needs a Godot `Array<Dictionary>` builder in C.
- **Audio AVAudioSession** — RESOLVED as a GODOT ENGINE issue, not Kanama: Godot's iOS
  CoreAudio driver never sets `AVAudioSessionCategoryPlayback`, so audio is silenced by the
  Ring/Silent switch (affects all Godot iOS games). Optional Kanama-side workaround: set the
  category from the iOS shim at startup.
- **Input.setCustomMouseCursor** — needs Texture2D arg marshalling (cosmetic).
- **PT_STRING / PT_NODE_PATH** — reserved C-shim ptrcall arg tags, construction not
  implemented (only PT_STRING_NAME is built C-side).

## Demo iOS coverage (per-demo readiness)

Audited surface today: 2D + 3D scalar/Object/Vector2/Vector3 methods; the 6 bridge kinds
above; @ScriptProperty scalars/object/List<Object>. NOT Vector2i/3i, Transform3D, Color/RID,
String-return, NodePath/value-type properties, @Rpc, multi-arg signals, or the unwired
annotations.

| Demo | 2D/3D | iOS status | Key gaps |
|---|---|---|---|
| Starter-Kit-Match3 | 2D | **Working** (device-validated) | — |
| Starter-Kit-3D-Platformer | 3D | **Working** (device-validated) | NodePath property (`view`) keeps default; String-return cache |
| Bunnymark | 2D | Ready (separate session) | none material; do Android+iOS for a perf comparison |
| godot-demo-2d-dodge-the-creeps | 2D | Tier-1 ready | standard 2D; minor |
| godot-demo-3d-squash-the-creeps | 3D | Tier-2 | `@OnUnhandledInput`, GodotObject arg, Long property |
| Starter-Kit-Racing | 3D | Tier-2 | missing @ScriptProperty defs, GodotObject usage |
| Starter-Kit-City-Builder | 2D | **Skip** (Android doesn't support it either) | Vector2i props/signals, GridMap, List<custom> |
| Starter-Kit-FPS | 3D | Tier-4 | Transform3D, InputEventMouseMotion, List<Weapon>, @OnInput |
| godot-4-3d-character-controller | 3D | Tier-5 | heavy @OnInput/@OnUnhandledInput, InputEvent handling |
| godot-4-3d-third-person-controller | 3D | Tier-5 | 60+ API classes, Transform3D, complex input |
| tps-demo-kanama | 3D | **Future** (not Android-supported yet) | @Rpc networking, Transform3D, large API surface |

Common gates across the not-yet demos: Vector2i/3i, Transform3D, the input annotations
(@OnInput/@OnUnhandledInput), multi-arg/value-arg signal bridges, @Rpc. Pick them off via the
backlog above; each unblocks a tier.

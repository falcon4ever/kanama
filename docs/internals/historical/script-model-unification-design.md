# Unified Script Model — Design (Phase 3.1)

> **Historical design record:** Phase 3 is complete. The regex parser was deleted,
> the shared KSP model now drives iOS registration, and generic per-signature
> `callV` dispatch is in use. This page is kept for design context; use
> [wrapper-coverage-tracker.md](../active/wrapper-coverage-tracker.md) for the final
> implementation state.

Design record for roadmap task 3.1 (`wrapper-coverage-roadmap.md`): make the
KSP processor emit a **serialized, platform-neutral script model** that the iOS
build consumes, so `@ScriptClass` registration has one source of truth instead
of two parsers that drift. This is the keystone for 3.2 (delete the regex
parser), 3.3 (generated trampolines), 3.4 (wire remaining annotations), and the
gate for 2.6 (`@ScriptProperty` value-type delivery).

## The problem: two parsers, derived independently from the same source

There are two completely separate front-ends that read the user's `@ScriptClass`
Kotlin and build a script model:

- **Desktop / Android — KSP.** `KanamaProcessor`
  (`processor/.../KanamaProcessor.kt`, ~2900 lines) runs during the JVM/Android
  Kotlin compile. It reads annotations via the KSP symbol API and builds a rich
  `ScriptModel` (`KanamaProcessor.kt:1173`): `ScriptPropertyModel` with full type
  info (object wrapper FQN, list element type, custom-script resource flags,
  export category/group/subgroup, hint/hintString, default literal),
  `VirtualModel`, `MethodModel` (+ `RpcModel`), `SignalModel` with typed
  `ArgModel`s. It emits Kotlin registrar source into
  `net.multigesture.kanama.generated` via `env.codeGenerator`.

- **iOS — a Gradle task with a regex parser.** `parseIosScript`
  (`ios-runtime/build.gradle.kts:187`, the file is 711 lines) is invoked by the
  `generateIosScriptRegistry` task (`:633`), which `walkTopDown()`s the user's
  `kotlin-src` and regex-matches `package`, `@ScriptClass(attachTo=…)`, `class`,
  `fun`, `var/val`. It builds a much thinner `IosScriptModel` (`:41`) →
  `IosScriptPropertyModel` (`:26`: just name, isObject, class name, isList,
  element class, nullable) and generates Kotlin bridges + `KanamaIosScript*`
  descriptors consumed by `KanamaIosRuntime`.

Both derive the model from the *same* `.kt` files, independently. Consequences:

1. **Every feature is implemented twice or silently no-ops on iOS.** The iOS
   side keeps a hand-maintained denylist `IOS_UNWIRED_FUNCTION_ANNOTATIONS`
   (`build.gradle.kts:179`: `@OnEnterTree`, `@OnUnhandledInput`,
   `@OnShortcutInput`, `@OnUnhandledKeyInput`, `@Rpc`, …) that only *warns* a
   used annotation is a no-op — it can't actually wire it.
2. **Value-type `@ScriptProperty` delivery is missing on iOS (blocks 2.6).** The
   regex parser warns and keeps the Kotlin default for NodePath/Vector/Color
   props (`build.gradle.kts:~282`) — it has no type fidelity to deliver them.
3. **Enumerated bridge kinds drop signatures (the platformer coin bug).**
   `IosScriptBridgeKind` (`build.gradle.kts:~15`) + `bridgeKindFor` (`:149`)
   enumerate a fixed set of call shapes; anything outside → `UNSUPPORTED` and the
   dispatch is silently skipped. (3.3 fixes this with generated trampolines.)
4. **The regex parser is inherently fragile** (multi-line signatures, default
   values, generics, comments) — a structural mismatch with KSP's real AST.

## What the iOS model lacks vs. the KSP `ScriptModel`

| KSP `ScriptModel` | iOS `IosScriptModel` |
|---|---|
| property type fidelity (TypeMapping, object wrapper FQN, list element, custom-script resource flags, hint/hintString/usage, **default literal**, export category/group/subgroup) | name + isObject + class name + isList + element class + nullable |
| `VirtualModel` (lifecycle + ptrcall arg conventions) | inferred from a fixed `when` over method names |
| `MethodModel` typed args + `RpcModel` | param *strings* + an enumerated `IosScriptBridgeKind` |
| `SignalModel` with typed `ArgModel`s | name only (`IosScriptSignalModel`) |
| tool buttons, `@GlobalClass` | — |

3.1 must produce a serialization rich enough to carry **all** of the KSP model so
the iOS consumer can reach parity (and 2.6/3.3/3.4 become iOS-free).

## Proposed: a serialized, platform-neutral model

Extract the annotation→model logic into a platform-neutral core and serialize
its output to a stable schema (JSON; human-diffable, trivially parsed by the
Gradle task, language-agnostic for any future consumer).

**Platform-neutrality is the key constraint.** Today's `ScriptModel` leaks
JVM/Panama detail: `ArgModel.readFromScratch` emits `MemorySegment`/`ADDRESS`
code, `TypeMapping` carries JVM ptrcall expressions. The serialized model must
describe *what the type is*, not *how the JVM marshals it* — each platform emitter
re-derives marshalling from the neutral type descriptor (iOS already has the
type machinery: the PT_* tags + `BuiltinCalls`/`ObjectCalls` from Phase 2 + items
9–13). Sketch:

```jsonc
{ "schemaVersion": 1,
  "scripts": [{
    "fqName": "com.game.Player", "simpleName": "Player",
    "resourcePath": "res://kotlin-src/Player.kt",
    "attachTo": "CharacterBody3D", "isTool": false, "isGlobalClass": false,
    "properties": [{
      "kotlinName": "view", "godotName": "view",
      "type": { "kind": "NODE_PATH" },            // neutral type descriptor
      "isMutable": true, "defaultLiteral": "NodePath(\"\")",
      "hint": 0, "hintString": "", "usage": 6,
      "export": { "group": null, "subgroup": null, "category": null }
    }],
    "methods": [{ "kotlinName": "onHit", "godotName": "on_hit",
      "kind": "REGULAR",
      "args": [{ "name": "amount", "type": { "kind": "INT" } }],
      "returnType": null, "rpc": null }],
    "virtuals": [{ "virtualName": "_ready", "kotlinMethodName": "ready", "args": [] }],
    "signals": [{ "godotName": "died", "args": [{ "name": "by", "type": { "kind": "OBJECT", "wrapper": "Node3D" } }] }],
    "toolButtons": []
  }]}
```

Type descriptor `kind` is the existing neutral enum (INT/FLOAT/BOOL/STRING/
NODE_PATH/VECTOR3/OBJECT/LIST/…); object/list carry a `wrapper`/`element`
class name; custom-script props carry the script FQN + `isResource`. This is a
1:1 serialization of `ScriptModel`/`ScriptPropertyModel` minus the JVM codegen
helpers.

## The real fork: *where* the model is produced for iOS

The JSON schema is the same regardless; the decision is **what runs the
annotation front-end for the iOS target.**

- **Option A — KSP emits JSON on the JVM/Android compile; the iOS Gradle task
  reads it.** Minimal new Gradle wiring; one KSP run. But the iOS build now
  depends on the JVM/Android script compile having run and on a stable artifact
  path inside the *user's* project, and an iOS-only build must still trigger it.
  Couples two pipelines that are otherwise independent.

- **Option B (recommended) — run the shared processor via KSP on the iOS
  (Kotlin/Native) source set too; it emits the JSON (and later the registrar)
  per-target.** KSP is multiplatform-capable; `generateIosScriptRegistry` is
  replaced by consuming KSP output. This is the only option that makes model
  derivation single-source on *every* platform and lets 3.2 delete the regex
  parser outright (the roadmap's "done when"). Cost: apply KSP to the KMP
  `iosMain`/native compilations and split `KanamaProcessor` into a
  platform-neutral *model builder* (annotation → serializable `ScriptModel`) +
  thin per-platform emitters. Heavier Gradle work up front, correct end state.

- **Option C — a standalone model-builder the Gradle task calls directly (no KSP
  on iOS).** Collapses to the regex problem unless it reuses a real Kotlin
  front-end; not viable without re-inventing KSP. Rejected.

**Recommendation: B.** A is a faster 3.1 but leaves two front-ends (KSP for the
model, regex still latent) and a cross-pipeline coupling; the roadmap explicitly
wants the regex parser *deleted*, which only B achieves cleanly. B also sets up
3.3 (generated trampolines emitted by the same processor) and Phase 5 virtuals
("design against the Phase 3 unified model").

## Migration & validation (low-risk landing)

1. **Refactor (no behavior change):** split `KanamaProcessor` into
   `model/` (annotation → `ScriptModel`, platform-neutral, no MemorySegment) and
   `emit-jvm/` (today's registrar codegen, consumes the model). Existing JVM
   output byte-identical — guard with the existing fixtures.
2. **3.1 deliverable:** add the JSON serializer + `schemaVersion`; emit it from
   the processor. Under Option B, apply KSP to the iOS source set so the model
   is produced there; under A, emit it as a JVM resource.
3. **Parallel-run gate:** for one release, keep `parseIosScript` AND the new
   model; assert the two `IosScriptModel`s are equal for the example/demo
   scripts (a build-time check, like the existing `check_*` gates). This catches
   schema/parse gaps before the regex parser is trusted to be gone.
4. **3.2:** point the iOS registry codegen at the deserialized model; delete
   `parseIosScript`, `IosScriptBridgeKind` heuristics, and
   `IOS_UNWIRED_FUNCTION_ANNOTATIONS`. **2.6 falls out for free** here — value-type
   props now carry full type descriptors, so NodePath/Vector/Color delivery is
   just an emitter case over the Phase-2 type machinery.
5. **3.3:** the emitter generates per-signature trampolines from `args` instead
   of mapping to a fixed `IosScriptBridgeKind`.

## Open decisions (need the user)

1. **Option A vs B** — where the model is produced for iOS (recommend **B**).
   This is the load-bearing call; it sets the Gradle work and whether the regex
   parser truly dies.
2. **Serialization format** — JSON (recommended: human-diffable, zero deps) vs a
   binary/`.proto`. JSON unless build-time parse cost matters (it won't at this
   scale).
3. **Schema-version policy** — fail-closed on mismatch (recommend) vs
   best-effort. With one repo producing and consuming, fail-closed is simplest.

## Option B — validated 2026-06-15 (wiring held for 3.2)

The Option-B path was proven end-to-end, then the Gradle wiring was reverted from
main pending its 3.2 consumer (see "what's committed" below):

- **Processor is platform-aware** (committed, `0735e99`): `emitJvmCode` (from
  `env.platforms`) gates the JVM registrar/aggregator emission; the
  `.script-model.json` is emitted on every target. On the JVM target output is
  byte-identical; on Native only the JSON is produced.
- **KSP on the iOS native target works.** Applying `id("com.google.devtools.ksp")`
  + `add("kspIosArm64", project(":processor"))` (and `kspIosSimulatorArm64`) to
  `ios-runtime`, with the user scripts already on `iosMain` via `kotlin.srcDir`
  (`ios-runtime/build.gradle.kts:698`), made `kspKotlinIosArm64` run the shared
  processor over the `@ScriptClass` sources and emit the JSON to
  `build/generated/ksp/iosArm64/iosArm64Main/resources/…`. **Proofs:** (1) zero
  `.kt` registrars emitted on the iOS target (platform-gating correct, Native
  compile unaffected); (2) the iOS-emitted `DefaultProbeScript` JSON is
  **byte-identical** to the JVM-emitted one — one source of truth, both targets.
- **Caveat found:** two example scripts (`VehicleWrapperProbe`, `SnowWrapperProbe`)
  failed KSP on iOS with `unsupported ScriptProperty type 'null'` — their
  object-typed `@ScriptProperty`s reference desktop API wrapper types that don't
  resolve on the iOS compilation. The processor (correctly) fails closed on an
  unresolved ScriptProperty type. Real iOS scripts use iOS-available types; this is
  a property-type-resolution divergence to handle as types/wrappers unify (Phase
  2/4), not a wiring defect.
- **Why the wiring isn't in main yet:** it gates *every* iOS build (incl. the
  device self-test path) on KSP success, but its consumer (3.2) doesn't exist, so
  committing it alone adds device-build risk for no functional gain. 3.2 re-applies
  it together with the JSON-consuming iOS registry codegen and a device-build check.

**What's committed for 3.1:** the platform-neutral model (`ScriptModel.kt`), the
JSON serializer (`ScriptModelJson.kt`) + per-script emission, and the
platform-aware processor. The KSP-on-iOS Gradle wiring is validated and documented
here, to land with 3.2.

## Phase 3.2 plan (next session — concrete)

Decision (clear from the architecture, no fork): **approach (b)** — the processor's
Native emitter generates the iOS registrar Kotlin directly, the exact analogue of
the JVM `ScriptCodeEmitter`. This removes BOTH the `parseIosScript` regex parser
AND the separate `generateIosScriptRegistry` Gradle codegen, and dissolves the
task-ordering problem (KSP emits the registrar into the iosArm64 compilation just
like the JVM registrar). The standalone `.script-model.json` stays useful as the
parallel-run oracle and for any non-Kotlin consumer.

Steps:
1. **Add `IosScriptCodeEmitter` to the processor** (Native analogue of
   `ScriptCodeEmitter`). It must reproduce what `ios-runtime/build.gradle.kts`'s
   `generateIosRegistrySource` / `generateIosGeneratedConstantsSource` /
   `generateIosCompatibilitySources` emit today: `registerKanamaIosProjectScripts()`,
   the per-script `KanamaIosScriptDescriptor` (path/baseType/methods/properties/
   signals/factory), and the bridge classes (`call`/`callObject`/`callArgs`/
   `callVector2i`/`callLong`/`setProperty`/`setPropertyString`/
   `setPropertyObjectArray`) consumed by `KanamaIosRuntime`. Gate it on `!emitJvmCode`
   (Native only), beside the JSON emission in `emitScriptRegistrar`.
2. **Parallel-run gate first (de-risk before deleting anything):** generate the iOS
   registry both ways for the demo scripts and assert equality (a `check_*`-style
   build gate). The emitter must match the regex parser's output byte-for-byte (or a
   normalized form) before cutover.
3. **Cutover:** re-apply the KSP-on-iOS wiring (validated above), delete
   `parseIosScript` + `generateIosScriptRegistry` + the `IosScriptBridgeKind`
   heuristics + `IOS_UNWIRED_FUNCTION_ANNOTATIONS`.
4. **Device-build check:** run `ios_visual_smoke` (iPhone) — confirm the probe
   scripts pass KSP-on-iOS and the self-test matrix stays green. (Warn before
   device launch.)
5. **2.6 then falls out for free:** the emitter has full type fidelity, so
   value-type `@ScriptProperty` delivery (NodePath/Vector/Color) is just an emitter
   case over the Phase-2 type machinery — no separate 2.6 work.

Open item carried from validation: object-typed `@ScriptProperty`s whose wrapper
types don't resolve on Kotlin/Native (the `unsupported type 'null'` caveat) — decide
whether the emitter degrades gracefully (skip + warn, like today's regex path) or
requires the iOS wrapper to exist (Phase 2/4 dependency).

### Resolved unknowns (so 3.2 starts with zero discovery cost)

- **`MemorySegment` on iOS is a shim**, not the JVM type:
  `ios-runtime/src/iosMain/kotlin/java/lang/foreign/MemorySegment.kt` is a
  Kotlin/Native `class MemorySegment(Long)` with `ofAddress(Long)`. The iOS API
  wrappers take it (`GodotObject(handle: MemorySegment)`), so the emitter must keep
  producing `…api.GodotObject(MemorySegment.ofAddress(handle))` exactly as
  `generateIosRegistrySource` does today — it compiles on K/N via the shim.
- **The emitter must reproduce three current generators** in
  `ios-runtime/build.gradle.kts`: `generateIosRegistrySource` (`:319` — the
  `registerKanamaIosProjectScripts()` aggregator + per-script `KanamaIosScriptDescriptor`
  + the bridge classes with `call`/`callObject`/`callArgs`/`callVector2i`/`callLong`/
  `setProperty`/`setPropertyString`/`setPropertyObjectArray` dispatch),
  `generateIosGeneratedConstantsSource` (`:498` — `<Class>Signals` emit helpers), and
  `generateIosCompatibilitySources`.
- **Model mapping to replicate** (rich `ScriptModel` → the regex path's thin fields):
  `method.argumentCount` = `args.size`; `bridgeKind` = `bridgeKindFor` re-expressed
  over `MethodModel.args` types (ZERO/DOUBLE/LONG/VECTOR2I/OBJECT/OBJECT_OBJECT_LONG/
  UNSUPPORTED); `property.isObjectType`/`godotClassName`/`isList`/`listElementClassName`/
  `isNullable` ← `ScriptPropertyModel.objectWrapperFqName`/`type`/`arrayElementWrapperFqName`.
  Get these classifications byte-equal or the parallel-run gate won't pass.
- **Validation is semantic, not a byte-diff:** the regex path emits ONE aggregated
  file in package `net.multigesture.kanama.ios`; the processor emits PER-script files
  in `net.multigesture.kanama.generated`. The gate must normalize (e.g. compare the
  set of generated declarations / the assembled registry), or the processor must
  emit the same aggregated shape. Decide the comparison form first.
- **No processor unit-test infra:** validating codegen requires running KSP, so the
  parallel-run gate lives in the build (re-add the validated KSP-on-iOS wiring, run
  both paths over the demo scripts, assert equivalence) — then the device check.

## Scope note

3.1 is **design + the serialized model emission + parallel-run validation** — it
does NOT change iOS runtime behavior (3.2 does). Per roadmap it is tagged
**fable → Opus 4.8** (Fable unavailable since 2026-06-12). Model tags: 3.1/3.3
Opus, 3.2 Opus, 3.4 sonnet.

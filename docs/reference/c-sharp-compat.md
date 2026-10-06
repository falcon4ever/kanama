# C# Parity Matrix

This page answers a different question than the wrapper coverage reports:
can a Godot C# user expect the same broad workflows in Kanama?

- [API Coverage](generated/api-coverage.md) is the numeric API surface: promoted
  classes/methods and generator reach.
- [Wrapper Generator Report](generated/wrapper-generator-report.md) is generator
  internals: skip categories, helper-shape gaps, and complete draft classes.
- This page is capability parity: scripting model, editor workflow, data
  marshalling, resources, signals, coroutines, and debugging.

Kanama follows the same broad principle as Godot C# — idiomatic host-language
naming, `GD`/`Mathf` helpers, attribute-driven exports, signals, and typed API
wrappers — but for Kotlin/JVM through GDExtension and Panama FFM. Public Kanama
API uses Kotlin lower-camel names; it does not provide PascalCase C# aliases.

Legend: `SUPPORTED` means validated in smoke tests or real demo ports.
`PARTIAL` means usable with caveats. `MISSING` means not implemented yet.

## Scripting Model

| Capability | Status | Notes |
|---|---|---|
| Attach Kotlin scripts to Godot nodes/resources | SUPPORTED | `.kt` resources load through Kanama's ScriptLanguage and resource loader/saver. |
| Register new Godot classes | SUPPORTED | `@RegisterClass` registers permanent ClassDB types. |
| Lifecycle callbacks | SUPPORTED | `@OnReady`, `@OnProcess`, `@OnPhysicsProcess`, `@OnEnterTree`, `@OnExitTree`. |
| Tool scripts | SUPPORTED | `@Tool` works for both `@ScriptClass` and `@RegisterClass`. |
| Global classes | SUPPORTED | `@GlobalClass` registers in the editor's global class list: the class appears in the Create New Resource dialog and its `.tres` instances match typed export slots (GUI-validated, [#39](https://github.com/falcon4ever/kanama/issues/39)). One constraint: the file must be named after the class. |
| Hot reload | PARTIAL | Desktop only (no mobile or Web reload, see [Version Support](version-support.md)). Build + scene reload is the reliable workflow; in-place live-node replacement still has edge cases, and `@RegisterClass` types need an editor restart. |

## Editor And Build Workflow

| Capability | Status | Notes |
|---|---|---|
| Kotlin project build | SUPPORTED | External projects use Gradle + KSP and resolve Kanama artifacts from the kit's bundled `addons/kanama/maven` repository, or from `mavenLocal()` after `publishKanamaToMavenLocal` on a source checkout. |
| Editor build button | SUPPORTED | Optional Kanama Tools plugin adds `Build Scripts`. |
| Auto build on save | SUPPORTED | Debounced and opt-in through project settings. |
| Scene reload after script sync | SUPPORTED | Enabled through the Kanama Tools plugin. |
| IntelliJ debugging | PARTIAL | Desktop only. Enable JDWP, restart the game process, attach a Remote JVM Debug configuration. Verified by hand on 2026-10-03 by attaching a JDWP client (`jdb`, the JDK's debugger) to a headless starter project started with `KANAMA_JDWP_PORT`; IntelliJ itself is not driven by any automated check, and the CI stage only greps that the settings are wired through `bootstrap.c` and the editor plugins. |
| Rider support | MISSING | Rider does not support Kotlin/JVM project editing the way IntelliJ IDEA does. |

## API Surface

| Capability | Status | Notes |
|---|---|---|
| Promoted Kotlin wrappers | SUPPORTED | Generated wrapper promotion is the main coverage path. See [API Coverage](generated/api-coverage.md) for current percentages and per-class details. |
| Generated KDoc | SUPPORTED | Wrapper docs are synced from Godot `doc/classes/*.xml` where available. |
| Core singletons and utilities | SUPPORTED | `GD`, `Mathf`, `Input`, `OS`, `Engine`, `Time`, `ProjectSettings`, `DisplayServer`, and related helpers are available. |
| 2D/3D gameplay APIs | PARTIAL | Broad promoted coverage exists, and real demo ports exercise common movement, physics, animation, particles, UI, audio, files, scenes, resources, and tween use cases. Use the coverage reports for exact wrapper availability. |
| Dynamic fallback | PARTIAL | `GodotObject.call(...)`, `signal(...)`, and typed object wrappers cover mixed GDScript/Kanama edges. Prefer typed wrappers when available. |
| Networking/multiplayer parity | PARTIAL | `ENetMultiplayerPeer` host/join, `@Rpc` registration and dispatch, and `MultiplayerSynchronizer` `@Export` replication are implemented. The TPS demo sends each RPC its GDScript original sends through the generated `*Rpcs` senders (lobby in `Menu.kt`; `jump`, `land`, `shoot`, `hit`, `explode`, `play_shoot` and `destroy` in gameplay), and the processor warns about a direct call to an `@Rpc` function without `callLocal` ([Multiplayer](../game-dev/multiplayer.md#rpc-methods)). One desktop smoke (`kanama-demos` `tpsBuildAndMultiplayerSmokeGodot`, run locally, not in CI) starts a headless host and a headless client over localhost ENet. It checks that the lobby RPCs work in both directions and that, after a real-bullet robot kill on the host, the server's part `destroy` RPC reaches the client. Not covered: Android or iOS peers, more than two peers, client input RPCs during gameplay, latency or packet loss, and a clean shutdown with a peer still connected (the smoke kills both processes). Web has no multiplayer. Less-common multiplayer APIs follow the general wrapper-coverage path. |

## Data And Marshalling

| Capability | Status | Notes |
|---|---|---|
| Scalars and strings | SUPPORTED | `Boolean`, `Long`/`Int`, `Double`/`Float`, `String`, and `null` cross common Variant paths. |
| Godot value types | SUPPORTED | Vectors, transforms, colors, planes, AABB, `NodePath`, `RID`, and related helpers are covered for current promoted APIs. |
| Object/resource handles | PARTIAL | Typed wrappers and closeable `Resource`/`RefCounted` handles are available; ownership-sensitive returns still require explicit policy. |
| Packed strings and bytes | SUPPORTED | `PackedStringArray` maps to `List<String>` in supported APIs; `PackedByteArray` maps to `ByteArray` in file helpers. |
| Typed exported arrays | PARTIAL | `List<String>`, `List<Texture2D>`, custom-enum arrays (`List<MyEnum>`), and same-project global resource-script arrays are supported. Broader typed arrays remain policy work. |
| Typed exported dictionaries | PARTIAL | `Map<K, V>` exports as a typed `Dictionary` (`PROPERTY_HINT_DICTIONARY_TYPE`): `String`, `Long`, `Int`, `Double`, `Float`, `Boolean`, enum, and Godot value-type keys (`Vector2`, `Vector2i`, `Vector3`, `Vector3i`, `Color`); with scalar/value-type/resource/node-wrapper/custom-script/enum values. Decode is fail-soft (wrong-typed entries skipped). Nil-value handling mirrors C#'s engine-backed `Dictionary`: `Map<K, V?>` keeps the key with `null`; non-null `Map<K, V>` drops the entry (Kotlin cannot hold null). Nullable object-value maps are rejected. **Deferred on iOS** (desktop/Android only). See [Exporting Dictionaries](../game-dev/properties-resources.md#exporting-dictionaries). |
| General Array/Dictionary/Variant APIs | PARTIAL | Selected dictionary/list shapes exist. Fully general heterogeneous containers are still a deliberate policy bucket. |
| Callable | PARTIAL | Signal connections, lambda callbacks, and tween callable helpers exist for bounded shapes. General public Callable ownership remains intentionally constrained. |

## Signals, Resources, And Async

| Capability | Status | Notes |
|---|---|---|
| Custom signal declarations | SUPPORTED | `@Signal` metadata and generated `*Signals` emit helpers are available. |
| Godot signal connections | SUPPORTED | Every engine signal is a typed property (`area.bodyEntered: Signal1<Node3D>`, like C#'s generated signal events) with `connect`, `await` and `emit`; `connect(target, "method")` and generated method-name constants for method connections. Desktop, Android, iOS and Web (on Web the classes it generates). |
| Lambda signal callbacks | SUPPORTED | Typed lambdas up to five arguments (every engine signal), untyped `List<Any?>` lambdas with any number. A lambda connection is a Godot custom Callable bound to its target and is released when Godot drops the connection (desktop, Android, iOS). |
| Runtime custom resources | SUPPORTED | `newScriptInstance<T>()` creates a script-backed `Resource` from Kotlin (GDScript `.new()` parity); or create a Godot `Resource`, attach a loaded Kanama script, then resolve `kotlinScriptInstance<T>()`. `newScriptInstance` is desktop/Android only (deferred on iOS; use the attach-then-resolve path there). |
| Inspector exports | PARTIAL | Scalars (including `Int`/`Float` narrow slots), strings, enums, enum lists, `NodePath`, groups/subgroups, common object/resource wrappers, typed node references, and selected arrays are supported across desktop, Android, and iOS, except typed `Map` (Dictionary) exports, which iOS does not deliver (the property keeps its Kotlin default there; see Typed exported dictionaries above). Flags and broader resource arrays remain intentionally conservative. |
| Coroutines | SUPPORTED | Each `KanamaScript` has a main-thread scope cancelled when its object is freed: `launch { }`, `wait(seconds)`, `nextFrame()`, `MainThread.awaitNextFrame`, `SceneTree.delaySeconds`, and signal awaits are available. |

## Intentional Differences From C#

| Difference | Rationale |
|---|---|
| Kotlin script object is separate from the Godot node | Keeps JVM script lifetime, Godot object lifetime, hot reload, and editor placeholders explicit. Use `KanamaScript<T>.self` for the attached node/resource. |
| Lower-camel Kotlin API only | Matches Kotlin style and avoids maintaining duplicate C#-style aliases. |
| Explicit install paths | Desktop kits cover new packaged projects, store addons cover existing packaged projects, and source checkouts remain the development path for unreleased Kanama changes. |
| Desktop-first runtime | Kanama embeds a normal JVM in a desktop Godot process. Android runs through ART and PanamaPort, iOS through a Kotlin/Native `.xcframework`, and Web through Kotlin/Wasm (no on-device JVM on either); platform tiers are in [Version Support](version-support.md). |

## Coverage References

For exact wrapper coverage, use [API Coverage](generated/api-coverage.md). For
generator blockers, use [Wrapper Generator Report](generated/wrapper-generator-report.md).

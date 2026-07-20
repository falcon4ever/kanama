# C# Parity Matrix

This page answers a different question than the wrapper coverage reports:
can a Godot C# user expect the same broad workflows in Kanama?

- [API Coverage](../contributing/api-coverage.md) is the numeric API surface: promoted
  classes/methods and generator reach.
- [Wrapper Generator Report](../contributing/wrapper-generator-report.md) is generator
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
| Global classes | SUPPORTED | `@GlobalClass`/`@ClassName` register in the editor's global class list: the class appears in the Create New Resource dialog and its `.tres` instances match typed export slots (GUI-validated, [#39](https://github.com/falcon4ever/kanama/issues/39)). One constraint: the file must be named after the class. |
| Hot reload | PARTIAL | Build + scene reload is the reliable workflow; in-place live-node replacement still has edge cases. |

## Editor And Build Workflow

| Capability | Status | Notes |
|---|---|---|
| Kotlin project build | SUPPORTED | External projects use Gradle + KSP and depend on Kanama artifacts from `mavenLocal()`. |
| Editor build button | SUPPORTED | Optional Kanama Tools plugin adds `Build Scripts`. |
| Auto build on save | SUPPORTED | Debounced and opt-in through project settings. |
| Scene reload after script sync | SUPPORTED | Enabled through the Kanama Tools plugin. |
| IntelliJ debugging | SUPPORTED | Enable JDWP, restart the game process, attach a Remote JVM Debug configuration. |
| Rider support | MISSING | Rider does not support Kotlin/JVM project editing the way IntelliJ IDEA does. |

## API Surface

| Capability | Status | Notes |
|---|---|---|
| Promoted Kotlin wrappers | SUPPORTED | Generated wrapper promotion is the main coverage path. See [API Coverage](../contributing/api-coverage.md) for current percentages and per-class details. |
| Generated KDoc | SUPPORTED | Wrapper docs are synced from Godot `doc/classes/*.xml` where available. |
| Core singletons and utilities | SUPPORTED | `GD`, `Mathf`, `Input`, `OS`, `Engine`, `Time`, `ProjectSettings`, `DisplayServer`, and related helpers are available. |
| 2D/3D gameplay APIs | PARTIAL | Broad promoted coverage exists, and real demo ports exercise common movement, physics, animation, particles, UI, audio, files, scenes, resources, and tween use cases. Use the coverage reports for exact wrapper availability. |
| Dynamic fallback | PARTIAL | `GodotObject.call(...)`, `signal(...)`, and typed object wrappers cover mixed GDScript/Kanama edges. Prefer typed wrappers when available. |
| Networking/multiplayer parity | SUPPORTED | The core multiplayer workflow — `ENetMultiplayerPeer` host/join, `@Rpc` calls, and `MultiplayerSynchronizer` `@ScriptProperty` replication — is validated end-to-end in the TPS demo across desktop, Android, and iOS, with each platform in the host role (clients see each other move/shoot over LAN and control only their own player). Less-common multiplayer APIs follow the general wrapper-coverage path. |

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
| Godot signal connections | SUPPORTED | Use `object.signal(Name.Signals.foo).connect(...)` and generated method-name constants. |
| Lambda signal callbacks | PARTIAL | Zero to three emitted arguments are supported through generated dispatcher methods used by Kanama's signal connection helpers. |
| Runtime custom resources | SUPPORTED | Create a Godot `Resource`, attach a loaded Kanama script, then resolve `kotlinScriptInstance<T>()`. |
| Inspector exports | PARTIAL | Scalars (including `Int`/`Float` narrow slots), strings, enums, enum lists, `NodePath`, groups/subgroups, common object/resource wrappers, typed node references, and selected arrays are supported across desktop, Android, and iOS. Flags and broader resource arrays remain intentionally conservative. |
| Coroutines | SUPPORTED | `KanamaScope`, Godot main-thread dispatch, `awaitNextFrame`, `SceneTree.delaySeconds`, and signal awaits are available. |

## Intentional Differences From C#

| Difference | Rationale |
|---|---|
| Kotlin script object is separate from the Godot node | Keeps JVM script lifetime, Godot object lifetime, hot reload, and editor placeholders explicit. Use `KanamaScript<T>.self` for the attached node/resource. |
| Lower-camel Kotlin API only | Matches Kotlin style and avoids maintaining duplicate C#-style aliases. |
| Explicit install paths | Desktop kits cover new packaged projects, store addons cover existing packaged projects, and source checkouts remain the development path for unreleased Kanama changes. |
| Desktop-first runtime | Kanama embeds a normal JVM in a desktop Godot process. Android and iOS are both Supported on 4.7 stable (Android through ART and PanamaPort; iOS through a Kotlin/Native `.xcframework`, no on-device JVM); web export is not planned. |

## Coverage References

For exact wrapper coverage, use [API Coverage](../contributing/api-coverage.md). For
generator blockers, use [Wrapper Generator Report](../contributing/wrapper-generator-report.md).

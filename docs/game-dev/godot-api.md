# Calling Godot APIs

Kanama should feel familiar to Godot users coming from GDScript or C#, while
still being honest about the places where Kotlin/JVM and GDExtension have
different constraints. This section mirrors the Godot C# API documentation
topics and tracks the Kotlin equivalent.

## API Differences

Kanama exposes two layers:

- **Godot-shaped wrappers** — stay close to engine names and GDExtension
  signatures. Useful for version alignment and debugging.
- **Kotlin conveniences** — lower-camel names, Kotlin properties, extension
  helpers, and coroutine integration.

### Wrapped Classes

Kanama now promotes generated wrappers in broad slices rather than maintaining
a hand-written class list in this page. Use
[API Coverage](../contributing/api-coverage.md) for the current promoted
class/method totals and
[Wrapper Generator Report](../contributing/wrapper-generator-report.md) for the
generator reach and skip categories.

When writing gameplay code, prefer typed wrappers such as `Node`, `Node3D`,
`CharacterBody3D`, `Area3D`, `AnimationPlayer`, `Control`, `ResourceLoader`,
`Input`, and `Mathf` whenever they exist. For your own Kanama scripts, prefer
generated `*Methods`, `*Signals`, `*Rpcs`, and `*Names` helpers. Use
`GodotObject.call(...)` only at dynamic boundaries such as mixed
GDScript/Kanama interop or APIs that are not wrapped yet.

### Generated KDoc

Public Godot-backed wrappers and builtin value types carry generated KDoc
imported from Godot's official `doc/classes/*.xml` files. In IntelliJ, this
means wrapped Godot APIs show useful tooltips and documentation while writing
Kotlin scripts. Game projects do not need to run any documentation-generation
commands.

## Value Types

Builtin value types such as `Vector2`, `Vector3`, `Color`, and `Transform3D`
are immutable snapshots in Kanama. This intentionally makes copied Godot values
less error-prone: changing a component means creating a new value and assigning
it back to the Godot property, not mutating a hidden copy.

Value-type helpers mirror Godot behavior where possible, including transform
and physics math. Gameplay code can treat these helpers as normal Kotlin value
APIs and assign the updated value back to the Godot property.

Node lookup helpers: `getNodeOrNull`, `getAsOrNull(path, ::Class)`,
`getNodeAsOrNull(path, "ClassName", ::Class)`, `requireAs(path, ::Class)`.
String and `NodePath` overloads available for all lookup helpers.

Deferred mutation: `GodotObject.setDeferred(property, value)`.

Interop with GDScript autoloads: `GodotObject.call(method, vararg args)` —
see the GDScript interop section below.

`GodotObject.call(method, vararg args)` is available as a mixed-project
interop path for scalar Variant arguments/returns, `Vector2`/`Vector3`,
`Color`, `GodotObject`, and `Resource` arguments. Vector and color returns
decode to Kotlin value types; object returns decode as non-owning
`GodotObject` wrappers. This is intended for cases such as calling a GDScript
autoload while porting a project incrementally:

```kotlin
val audio = self.getNodeOrNull("/root/Audio")
audio?.call("play", "res://sounds/jump.ogg")
```

For built-in Godot names used at dynamic boundaries, Kanama generates
engine-wide constants from `extension_api.json`:

```kotlin
audio?.call(MethodName.play, "res://sounds/jump.ogg")
player.signal(SignalName.treeExited)
self.getTree().setGroup("enemies", PropertyName.visible, false)
```

Script-local generated names such as `PlayerNames.Methods.onBodyEntered` remain
the right choice when Godot APIs need a method, property, or signal name. When
Kotlin code is invoking another Kanama script method directly, prefer the
generated `PlayerMethods.damage(...)`-style helpers instead of string dispatch.

For now, project autoloads should be resolved through the scene tree root
(`/root/<Name>`). `Engine.getSingleton()` remains useful for engine singletons,
but project autoloads are not treated as engine singletons in the current smoke
path.

## Collections

Godot collections only matter when data crosses the engine boundary. For pure
Kotlin game logic, prefer Kotlin/JDK collections (`List`, `MutableList`, `Map`,
and arrays) because they avoid per-element engine marshalling.

Current Kanama collection support is intentionally narrow:

- `PackedStringArray` is exposed as `List<String>` in wrappers such as
  `DirAccess.getFilesAt`, `ResourceLoader.listDirectory`, and
  `ProjectSettings.getChangedSettings`. Scene-authored `PackedStringArray` and
  `Array[String]` values also decode to `List<String>` when they pass through
  generic Variant paths such as `@ScriptProperty` setters.
- `PackedByteArray` is exposed as `ByteArray` in `FileAccess` byte helpers.
- Selected object arrays are exposed as non-owning Kotlin wrapper lists, such
  as `Area3D.getOverlappingBodies()` and `Area3D.getOverlappingAreas()`.
- Scalar `Dictionary` values are exposed as `Map<String, Any?>` where the
  wrapper knows the dictionary shape, such as `ProjectSettings` and selected
  singleton metadata calls.
- `Vector2` and `Vector3` include Kotlin-side arithmetic and common gameplay
  math helpers such as `length`, `normalized`, `dot`, `lerp`, distance, and
  length limiting; `Vector3` also includes `cross` and `rotated`.
- `Basis` and `Transform3D` expose Godot-style 3D transform math:
  `basis * vector`, `transform * vector`, `basis.determinant()`, and
  `basis.inverse()`. `Basis.x`, `Basis.y`, and `Basis.z` match Godot's public
  column-axis API, even though the GDExtension native memory block is packed as
  rows internally.

General public `Array`/`Dictionary` wrappers and broad Kotlin collection
conversion are intentionally conservative. For APIs outside the promoted
shapes above, use typed wrappers where available or call through explicit
dynamic Godot APIs at the boundary.

## Variant

Kanama already marshals the common scalar Variant-compatible types used by the
current API surface: `null`, `Boolean`, `Long`/`Int`, `Double`/`Float`,
`String`, selected vectors/transforms, `NodePath`, `RID`,
`PackedStringArray`, `PackedByteArray`, scalar `Dictionary`, and selected
object/resource handles where lifetime is explicit.

Use typed wrappers when possible. Kanama keeps the broad public `Variant` API
small because it must define ownership, object lifetime, enum handling, and
generic constraints clearly. This matters for APIs such as
`FileAccess.store_var`, `FileAccess.get_var`, `Node.rpc_config`, and broader
Object/Resource conversions.

## Resource Ownership

Godot `Resource` and `RefCounted` wrappers returned by Kanama APIs are
closeable Kotlin handles. When you load a temporary resource and hand it to a
Godot node, release the temporary wrapper with `use` or `close` after the node
has accepted the value:

```kotlin
ResourceLoader.loadAudioStream("res://sounds/jump.ogg")?.use { stream ->
    player.setStream(stream)
}
```

This mirrors GDScript's `stream = load("res://sounds/jump.ogg")`: the node
keeps its own reference, while the local temporary reference is released when
the assignment is done. Kotlin/JVM does not have GDScript's deterministic local
reference cleanup, so relying on garbage collection can leave extra Godot
references alive until shutdown.

Do not register `RefCounted` values as engine singletons. Godot 4.7 preview builds
warns for `Engine.register_singleton` with `RefCounted` instances because the
engine singleton table stores a raw `Object*`, not a `Ref<>`. Kanama's
`Engine.registerSingleton` wrapper rejects this shape before calling Godot; use
an `Object`-derived singleton instead.

Prefer convenience APIs when they exist. For audio players, use:

```kotlin
player.setStreamFromPath("res://sounds/jump.ogg")
player.play()
```

The same rule applies to other returned `RefCounted` helpers, such as
`Tween.tweenProperty(...)` and `Tween.tweenCallback(...)`: if you do not keep
the returned wrapper, close it after configuring it.

Resource-returning getters follow the same rule. For example,
`TextureRect.texture`, `Sprite2D.texture`, and `AudioStreamPlayer.getStream()`
return closeable wrappers. If you only need to inspect the value, close the
temporary wrapper immediately:

```kotlin
val current = crosshair.texture
check(current != null)
current?.close()
```

For gameplay animation, prefer `node.createTween()` over
`SceneTree.createTween()` when the tween belongs to a node. Godot binds tweens
created this way to the node, so they stop processing when the node leaves the
tree and are killed when the node is freed. Use `SceneTree.createTween()` only
for tweens that intentionally outlive any particular node, or bind them
explicitly with `Tween.bindNode(node)`.

For `@ScriptProperty` fields, Kanama-generated registrars release closeable
property wrappers when Godot frees the script instance. Mutable script
properties also release the previous closeable wrapper before accepting a new
value from Godot. This covers retained exported resources such as
`PackedScene?`, `Texture2D?`, and `List<Texture2D>`. `List<String>` script
properties export as `Array[String]` and read back as Kotlin strings, including
scene-authored `PackedStringArray` values.

## Inspector Properties and Signals

This page focuses on the Godot API wrapper surface. For inspector-visible
script data, see [Exports and Resources](properties-resources.md). For custom
signals, scene connections, and lambda callbacks, see
[Signals and Callbacks](signals.md).

## Global Classes

Kanama supports globally named classes with `@GlobalClass` or `@ClassName`.

```kotlin
@ScriptClass(attachTo = "Node")
@GlobalClass
class Player(godotObject: MemorySegment) :
    KanamaScript<Node>(godotObject, ::Node)
```

This is the Kotlin equivalent of Godot's named script/global class concept and
is intended to make classes easier to find in editor-facing workflows.

Use conservative, case-sensitive file/class naming for global classes. Kanama
supports the generated metadata path today; editor-facing global-class behavior
and typed exported global-class references are advanced usage
until the surrounding object/Variant lifetime rules are broader.

## Coverage References

Use [API Coverage](../contributing/api-coverage.md) and
[Wrapper Generator Report](../contributing/wrapper-generator-report.md) as the
source of truth for promoted wrapper availability. Dynamic container APIs,
ownership-sensitive object returns, and convenience helpers are promoted when
their Kotlin surface is explicit enough to be stable.

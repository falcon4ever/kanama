# Exports and Resources

Kanama exposes inspector-visible properties with annotations and maps common
Godot resources to Kotlin wrapper types.

## Exported Properties

Use `@ScriptProperty` on `@ScriptClass` scripts and `@RegisterProperty` on
registered classes. `@Export` is available as a migration-friendly alias. Use
`PropertyHint` and `PropertyUsage` constants instead of raw Godot integers when
you need inspector metadata.

```kotlin
@ScriptClass(attachTo = "Node")
class Player(godotObject: MemorySegment) :
    KanamaScript<Node>(godotObject, ::Node) {
    @ExportCategory("Tuning")
    @ExportGroup("Movement")
    @ScriptProperty(hint = PropertyHint.RANGE, hintString = "0,20,0.1")
    var speed: Double = 5.0

    @ExportSubgroup("Jump")
    @ScriptProperty
    var jumpVelocity: Double = 8.0
}
```

`@ScriptProperty(name = "...")` and `@Export(name = "...")` override the
Godot-facing `snake_case` property name. `usage = PropertyUsage.READ_ONLY or
PropertyUsage.EDITOR` and other usage flags are available for advanced
inspector behavior, but ordinary exported gameplay data should keep the
default usage.

Simple source-literal defaults are preserved in generated script registrars:
numeric, boolean, string, enum entry, `NodePath("...")`, and
`Math.toRadians(<number>)` initializers show up as inspector defaults.

## Exporting Enums

Kotlin `enum class` properties export directly and render as an inspector
dropdown of the entry names:

```kotlin
enum class Difficulty { EASY, NORMAL, HARD }

@ScriptClass(attachTo = "Node")
class GameRules(godotObject: MemorySegment) :
    KanamaScript<Node>(godotObject, ::Node) {
    @ScriptProperty
    var difficulty = Difficulty.NORMAL
}
```

This mirrors C# enum exports: the property registers as an `int` with
`PROPERTY_HINT_ENUM`, the stored value is the entry ordinal, and `.tscn`-stored
ints deserialize back into the enum slot (out-of-range values clamp to a valid
entry instead of crashing). Because storage is ordinal-based, reordering or
removing enum entries silently remaps values already saved in scenes — append
new entries at the end, the same trade-off C# has with implicit enum values.

Enum exports are supported on `@ScriptClass` scripts (`@ScriptProperty` /
`@Export`) on desktop, Android, and iOS; `@RegisterProperty` on
`@RegisterClass` classes does not accept enum types yet.

Lists of enums export too, as a typed int array whose elements render the
same dropdown (C# `Difficulty[]` parity):

```kotlin
@ScriptProperty
var unlockedModes: List<Difficulty> = emptyList()
```

Elements are stored as ordinals with the same clamp-on-stale-value and
reordering trade-off as the scalar form. Defaults must be empty
(`emptyList()` / `listOf()`); populate initial values in the scene or
inspector. Enum-list delivery uses the same conversion and clamping semantics
on desktop, Android, and iOS.

## Inspector Buttons

Use `@ToolButton` on a zero-argument function in a `@Tool @ScriptClass` to
show a clickable button in the inspector:

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class Spawner(godotObject: MemorySegment) :
    KanamaScript<Node>(godotObject, ::Node) {
    @ToolButton(text = "Rebuild", icon = "Reload")
    fun rebuild() {
        rebuildPreview()
        notifyInspectorChanged()
    }
}
```

`@ExportToolButton` is available as an alias for C# migration. The generated
button is editor-facing metadata only; keep the script in `@Tool` mode and
guard editor-only work the same way you would for any other tool script.

## Node References

Exported node references should use the target wrapper type when the original
GDScript exported an object reference:

```kotlin
@ScriptProperty
var crosshair: TextureRect? = null
```

Use `NodePath` only when the original script intentionally exposed a path value:

```kotlin
@ScriptProperty
var viewPath: NodePath = NodePath("../View")
```

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

For more detail, see [Calling Godot APIs](godot-api.md#resource-ownership).

## Resource Slots

Exported properties can hold engine resources. Use a nullable wrapper type
with a `= null` default; base-typed slots accept engine subtypes (an
`AudioStreamWAV` or `AudioStreamOggVorbis` lands in an `AudioStream` slot):

```kotlin
@ScriptProperty
var stream: AudioStream? = null

@ScriptProperty
var icons: List<Texture2D> = emptyList()
```

Supported resource slot types: `PackedScene`, `Texture`, `Texture2D`,
`NoiseTexture2D`, `Material`, `ShaderMaterial`, `Curve`, `FastNoiseLite`,
`ButtonGroup`, `LightmapGIData`, `AudioStream`, `Mesh`, `Shape2D`, `Shape3D`,
`Font`, `Animation`, and `StyleBox`. Resource-typed reads are
ownership-managed (retained while the script holds them, released on script
cleanup), so the set is a deliberate allowlist — file an issue when a common
slot type you need is missing.

Avoid `lateinit var` for exports: it has no inspector default, and a read
before assignment crashes into the engine. The build warns and points to the
nullable `= null` shape.

## Custom Resources

To create your own resource type (the Kotlin equivalent of GDScript's
`extends Resource` / C#'s `[GlobalClass] public partial class X : Resource`),
declare a **plain class** — do not subclass the `Resource` wrapper — and
attach it with `@ScriptClass(attachTo = "Resource")`:

```kotlin
@ScriptClass(attachTo = "Resource")
@GlobalClass
class Weapon(val godotObject: MemorySegment) {
    @Export
    var damage: Long = 10

    @Export
    var displayName: String = "Sword"

    @Export
    var swingSound: AudioStream? = null
}
```

`@GlobalClass` registers `Weapon` in the editor's global class list: it shows
up in the Create New Resource dialog, its `.tres` instances match typed export
slots, and another script can export `Weapon?` or `List<Weapon>` — Kanama
resolves the live Kotlin resource script instances when reading the property;
values (including nested resource slots like `swingSound`) round-trip through
`.tscn`/`.tres` storage.

One naming constraint: the file must be named after the class (`Weapon.kt`),
or the class cannot be mapped back to its script file and stays out of the
global class list — the build warns when they diverge.

Generated wrappers (`net.multigesture.kanama.api.Resource`, `AudioStream`,
...) are non-owning views with internal constructors and **cannot be
subclassed** — attempting it fails the build with a pointer to the pattern
above. The wrapper surface would otherwise drift from the script surface;
`KanamaScript<T>` is the only supported base class.

## Saving Custom Resources

Because a custom resource script is a plain class rather than a `Resource`
subtype, APIs with `Resource`-typed parameters (such as `ResourceSaver.save`)
do not accept it directly. Wrap the script's own `godotObject` handle with
`Resource.fromHandle` instead:

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class WeaponForge(godotObject: MemorySegment) : KanamaScript<Node>(godotObject, ::Node) {
    @Export
    var weapon: Weapon? = null

    @ToolButton(text = "Save weapon")
    fun saveWeapon() {
        val weapon = weapon ?: return
        ResourceSaver.save(Resource.fromHandle(weapon.godotObject), "res://weapon.tres")
    }
}
```

`Resource.fromHandle` is a non-owning view over the same engine object; do
not `close()` it — the script instance still uses that handle.

The instance you save must be engine-created: loaded from a `.tres`, assigned
through an inspector slot, or instantiated by the editor. Calling a script
class constructor yourself — `Weapon(someOtherObject.godotObject)` — does not
create a new `Weapon` resource; it only wraps an existing handle in a Kotlin
view, and passing another object's handle (a node's, for example) produces a
view of the wrong object. Programmatic creation of new script-backed
resources from Kotlin is not supported yet.

# Exports and Resources

Kanama exposes inspector-visible properties with annotations and maps common
Godot resources to Kotlin wrapper types.

## Exported Properties

Use `@Export` (GDScript `@export`) on `@ScriptClass` scripts and on
`@RegisterClass` types alike. (Before Kanama 0.5 the names were `@ScriptProperty`
and `@RegisterProperty`, with the same parameters.) Use
`PropertyHint` and `PropertyUsage` constants instead of raw Godot integers when
you need inspector metadata.

```kotlin
@ScriptClass(attachTo = "Node")
class Player(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {
    @ExportCategory("Tuning")
    @ExportGroup("Movement")
    @Export(hint = PropertyHint.RANGE, hintString = "0,20,0.1")
    var speed: Double = 5.0

    @ExportSubgroup("Jump")
    @Export
    var jumpVelocity: Double = 8.0
}
```

`@Export(name = "...")` overrides the
Godot-facing `snake_case` property name. `usage = PropertyUsage.READ_ONLY or
PropertyUsage.EDITOR` and other usage flags are available for advanced
inspector behavior, but ordinary exported gameplay data should keep the
default usage.

Simple source-literal defaults are preserved in generated script registrars:
numeric, boolean, string, enum entry (a Kotlin `enum class` entry or a Godot
enum value such as `Node.ProcessMode.ALWAYS`), `NodePath("...")`, and
`Math.toRadians(<number>)` initializers show up as inspector defaults.

## Export Hints

Inspector hints are set with a `PropertyHint` constant plus a hint string.
This is the same shape Godot C# uses — Kanama's
`@Export(hint = PropertyHint.RANGE, hintString = "0,100,1")` is the direct
equivalent of C#'s `[Export(PropertyHint.Range, "0,100,1")]`. Prefer the
`PropertyHint` constants over raw `PROPERTY_HINT_*` integers.

If you are porting from GDScript, its dedicated `@export_*` annotations map to
the hint form as follows:

| GDScript | Kanama |
|---|---|
| `@export_range(0, 100, 1)` | `@Export(hint = PropertyHint.RANGE, hintString = "0,100,1")` |
| `@export_range(0, 100, 1, "or_greater")` | `@Export(hint = PropertyHint.RANGE, hintString = "0,100,1,or_greater")` |
| `@export_file("*.png", "*.jpg")` | `@Export(hint = PropertyHint.FILE, hintString = "*.png,*.jpg")` |
| `@export_dir` | `@Export(hint = PropertyHint.DIR)` |
| `@export_global_file("*.txt")` | `@Export(hint = PropertyHint.GLOBAL_FILE, hintString = "*.txt")` |
| `@export_global_dir` | `@Export(hint = PropertyHint.GLOBAL_DIR)` |
| `@export_multiline` | `@Export(hint = PropertyHint.MULTILINE_TEXT)` |
| `@export_placeholder("name")` | `@Export(hint = PropertyHint.PLACEHOLDER_TEXT, hintString = "name")` |
| `@export_color_no_alpha` | `@Export(hint = PropertyHint.COLOR_NO_ALPHA)` |
| `@export_exp_easing` | `@Export(hint = PropertyHint.EXP_EASING)` |
| `@export_flags("Fire", "Water", "Earth")` | `@Export(hint = PropertyHint.FLAGS, hintString = "Fire,Water,Earth")` on an `Int` |

```kotlin
@ScriptClass(attachTo = "Node")
class Tuning(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {
    @Export(hint = PropertyHint.RANGE, hintString = "0,100,1,or_greater")
    var health: Long = 100

    @Export(hint = PropertyHint.FILE, hintString = "*.png,*.jpg")
    var icon: String = ""

    @Export(hint = PropertyHint.MULTILINE_TEXT)
    var description: String = ""
}
```

The `RANGE` hint string accepts Godot's suffix flags after `min,max[,step]`:
`or_greater`, `or_less`, `exp`, `hide_slider`, `radians_as_degrees`,
`degrees`, and `suffix:<unit>`. The hint-string grammar is Godot's own, so a
malformed string is accepted as-is and simply renders no special editor — the
hint form is an escape hatch, not a validated builder. Enum-typed properties
do not need a hint: a Kotlin `enum class` or a Godot enum exports as a dropdown
(a bitfield as flag checkboxes) automatically (see below).

## Exporting Enums

Kotlin `enum class` properties export directly and render as an inspector
dropdown of the entry names:

```kotlin
enum class Difficulty { EASY, NORMAL, HARD }

@ScriptClass(attachTo = "Node")
class GameRules(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {
    @Export
    var difficulty = Difficulty.NORMAL
}
```

This mirrors C# enum exports: the property registers as an `int` with
`PROPERTY_HINT_ENUM`, the stored value is the entry ordinal, and `.tscn`-stored
ints deserialize back into the enum slot (out-of-range values clamp to a valid
entry instead of crashing). Because storage is ordinal-based, reordering or
removing enum entries silently remaps values already saved in scenes — append
new entries at the end, the same trade-off C# has with implicit enum values.

Enum exports are supported on `@ScriptClass` scripts on desktop, Android, and
iOS; `@Export` on `@RegisterClass` classes does not accept enum types yet.

Lists of enums export too, as a typed int array whose elements render the
same dropdown (C# `Difficulty[]` parity):

```kotlin
@Export
var unlockedModes: List<Difficulty> = emptyList()
```

Elements are stored as ordinals with the same clamp-on-stale-value and
reordering trade-off as the scalar form. Defaults must be empty
(`emptyList()` / `listOf()`); populate initial values in the scene or
inspector. Enum-list delivery uses the same conversion and clamping semantics
on desktop, Android, and iOS.

### Godot enums and bitfields

A property typed with one of Godot's own enums (every Godot enum is a typed
value class in Kanama, see [Godot Enums and Bitfields](godot-api.md#godot-enums-and-bitfields))
exports as an inspector dropdown (a bitfield as flag checkboxes):

```kotlin
@Export
var mode: Node.ProcessMode = Node.ProcessMode.PAUSABLE

@Export // a bitfield: flag checkboxes
var messages: Node.ProcessThreadMessages = Node.ProcessThreadMessages.MESSAGES

@Export
var allowedModes: List<Node.ProcessMode> = emptyList()
```

- The inspector metadata follows Godot's **native-property style** — what the
  engine's own `Node.process_mode` shows — not GDScript's: an `int` with
  `PROPERTY_HINT_ENUM` (a bitfield with `PROPERTY_HINT_FLAGS`) and a hint
  string of the Kanama value names as Godot capitalizes them, with the values
  spelled out: `Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4`,
  `Messages:1,Messages Physics:2,Messages All:3`. Whether a type is a bitfield
  is Godot's call: `BaseMaterial3D.Flags`, despite its name, is an enum of flag
  indices and exports as a dropdown.
- How that differs from GDScript's `@export var mode: Node.ProcessMode`:
  GDScript's hint string uses the **full** Godot names (`Process Mode
  Inherit:0,Process Mode Pausable:1,…`), and GDScript exports a **bitfield as
  `PROPERTY_HINT_ENUM`** too (a dropdown of single flags, `Flag Process Thread
  Messages:1,…`), where Kanama shows flag checkboxes. The stored value is the
  same `int` either way, so scenes are interchangeable.
- Like GDScript's export, the property also carries the enum's identity:
  `class_name` is Godot's qualified name (`Node.ProcessMode`) and usage
  includes `PROPERTY_USAGE_CLASS_IS_ENUM` (for a bitfield, Godot's native
  `PROPERTY_USAGE_CLASS_IS_BITFIELD`, which GDScript types as a plain `int`, so
  assigning a combination of flags does not warn), so typed GDScript sees
  `$Node.mode` as a `Node.ProcessMode`. On Web the generated GDScript proxy
  declares the property with `@export_custom(<hint>, "<hint string>")`, which
  cannot set `class_name`, so a Web build reports the hint and hint string
  only (no class marker).
- Unlike a Kotlin `enum class`, the stored value is the **Godot value**, not an
  ordinal, so a scene saved by a GDScript or C# version of the script loads
  unchanged, and a value the inspector does not list (a newer Godot's, or an
  extension's) is kept rather than clamped.
- The default may be any value (`Node.ProcessMode.ALWAYS`), the raw form
  (`Node.ProcessMode(3L)`) or, for a bitfield, an `or` of values; it is
  constant-folded into the inspector default.
- `List<Node.ProcessMode>` exports as a typed int array (`"2/2:<hint>"`) of
  Godot values. Defaults must be empty, as for the other lists.
- The type is non-null (`Node.ProcessMode?` is a build error: the slot is an
  `int`).
- Supported on desktop, Android and iOS; on Web the scalar form is supported
  and the list form is a build error, like `List<enum class>`.

## Exporting Dictionaries

`Map<K, V>` exports as a typed Godot `Dictionary` (registered with
`PROPERTY_HINT_DICTIONARY_TYPE`), so the inspector shows the matching key/value
type pickers:

```kotlin
@Export
var mapRegions: Map<Long, UnitMetadata> = mapOf()
```

Supported **key** types are `String`, `Long` / `Int`, `Double` / `Float`,
`Boolean`, Godot value types (`Vector2`, `Vector2i`, `Vector3`, `Vector3i`,
`Color`), and `enum class`.
Supported **value** types match the typed-array element set plus scalars:
scalars (`Long`, `Int`, `Double`, `Float`, `Boolean`), `String`, Godot value
types (`Vector2`, `Vector2i`, `Vector3`, `Vector3i`, `Color`), engine
resource/node wrappers, custom `@ScriptClass` scripts (resource or node), and
`enum class`. Resource-typed values are ownership-managed (retained while the
script holds them, released on cleanup), exactly like `List<Resource>`. Enum
keys and values store as ordinals with the same reorder/clamp trade-off as the
scalar and list enum exports.

Defaults must be empty (`emptyMap()` / `mapOf()`); populate initial entries in
the scene or inspector. A plain `Map<String, Any?>` also works as an untyped
Dictionary export.

Dictionary exports are supported on `@ScriptClass` scripts; `@Export` on
`@RegisterClass` classes does not accept `Map` (or `List`) types.

### Nil values and malformed entries

Decoding a Dictionary from the engine is **fail-soft**: an entry whose key or
value does not match the declared types (a hand-edited value, or a stale `.tscn`
saved before the types changed) is skipped rather than throwing — a wrong-typed
entry can never crash the script.

What happens to a **nil value** depends on whether the value type is nullable,
mirroring Godot C#'s engine-backed `Dictionary`:

- `Map<K, V?>` (nullable value) — the key is **kept with a `null` value**, and a
  wrong-typed value also decodes to `null`. This matches C#, whose Dictionary
  value slots are always nullable references.
- `Map<K, V>` (non-null value) — Kotlin cannot hold a `null` there, so a nil
  entry is **dropped** (the same way typed arrays drop nil elements).

Nullable *scalar / value-type / enum* values are supported. A nullable
**object/resource** value (`Map<K, Texture2D?>`) is rejected at build time —
declare it non-null; its nil entries drop.

### iOS

Typed-Dictionary property **delivery is deferred on iOS**: the Kotlin/Native
backend cannot read or write `Map` properties yet (desktop and Android are fully
supported). Because such a property would silently keep its Kotlin default on
iOS — the scene and inspector value dropped — an iOS build with a `Map`
`@Export` **fails** and names the property. The same holds for every
other `@Export` the iOS backend cannot deliver (for example a
`Vector2i` value). To accept the skip and build anyway, pass
`-PkanamaIosAllowExportSkips=true` to the iOS build (or set it in
`gradle.properties`); each skipped property is then a warning and keeps its
Kotlin default on iOS.

## Inspector Buttons

Use `@ExportToolButton` (GDScript `@export_tool_button`) on a zero-argument
function in a `@Tool @ScriptClass` to show a clickable button in the inspector:

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class Spawner(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {
    @ExportToolButton(text = "Rebuild", icon = "Reload")
    fun rebuild() {
        rebuildPreview()
        notifyInspectorChanged()
    }
}
```

The generated
button is editor-facing metadata only; keep the script in `@Tool` mode and
guard editor-only work the same way you would for any other tool script.

## Node References

Exported node references should use the target wrapper type when the original
GDScript exported an object reference:

```kotlin
@Export
var crosshair: TextureRect? = null
```

Use `NodePath` only when the original script intentionally exposed a path value:

```kotlin
@Export
var viewPath: NodePath = NodePath("../View")
```

## Releasing Resources You Create

The one rule, and the categories it produces, live in
[Godot API → Resource Ownership](godot-api.md#resource-ownership):
**`close()` releases your reference, it does not destroy the object.** This
section covers the case you hit most while writing gameplay — a resource you
created yourself — and why the JVM cannot clean it up for you.

Godot `Resource` and `RefCounted` wrappers returned by Kanama APIs are
closeable Kotlin handles. When you load a temporary resource and hand it to a
Godot node, release the temporary wrapper with `use` or `close` after the node
has accepted the value:

```kotlin
ResourceLoader.loadAudioStream("res://sounds/jump.ogg")?.use { stream ->
    player.setStream(stream)
}
```

### Close what you create

The same rule applies to anything you make with an `X.create()` factory: create
it, hand it to the engine, then release your handle with `use { }` (or `close()`).
The engine keeps its own reference, so the resource lives on — you are only
letting go of *your* copy.

```kotlin
for (keycode in keycodes) {
    InputMap.addAction(action)
    InputEventKey.create().use { inputKey ->   // you own this handle…
        inputKey.keycode = keycode
        InputMap.actionAddEvent(action, inputKey) // …InputMap takes its own reference…
    }                                              // …use { } releases yours
}
```

If you coded games in GDScript or C#, note that **the same code there needs no
cleanup** — and it is not leaking:

```gdscript
# GDScript
var input_key = InputEventKey.new()
input_key.keycode = keycode
InputMap.action_add_event(action, input_key)
# input_key's reference is dropped automatically when it leaves scope
```

```csharp
// C#
var inputKey = new InputEventKey();
inputKey.Keycode = keycode;
InputMap.ActionAddEvent(action, inputKey);
// the .NET GC drops the reference for you when inputKey is collected
```

GDScript's script VM drops your reference the moment the variable leaves scope,
and C#'s garbage collector drops it when the wrapper is collected. Kanama does
both: the `use { }` above releases your reference at once, as GDScript's
scope-exit does, and a wrapper you forget is released like C#'s, after the
garbage collector drops it (the release itself runs on the main thread at the
next frame, never on the collector's thread). Closing is therefore about
*when*: `use { }` releases now, the collector releases eventually. The project
setting `kanama/debug/log_gc_releases` names every creation site the collector
had to clean up after. See
[Resource Ownership](godot-api.md#a-forgotten-close-is-a-late-release). Holding a
custom resource's script object (`kotlinScriptInstance<T>()`,
`newScriptInstance<T>().instance`) keeps the resource itself alive, as in GDScript
([details](godot-api.md#a-script-object-keeps-its-resource-alive)).

The **Web (Kotlin/Wasm)** backend reaches Godot over a JavaScript *handle bridge*
rather than an FFI pointer boundary: `close()`/`use { }` emits a release-handle
command that drops the engine-side reference, but the bridge does not GC handles
for you yet, so on Web a forgotten `close()` still keeps the reference until the
owning script tears down: the GC fallback and the script-object owner links above
do not exist on Web yet. On Web `RefCounted` itself is not `AutoCloseable`; only a
dozen classes have `close()` (`PackedScene`, `Texture2D`, `AudioStream`, `Mesh`,
`Material`, `ConfigFile`, ...), so code shared with Web cannot `use { }` most
`RefCounted` wrappers. See
[Web internals → RefCounted resource ownership](../contributing/backends/web.md).

For more detail, see [Calling Godot APIs](godot-api.md#resource-ownership).

## Resource Slots

Exported properties can hold engine resources. Use a nullable wrapper type
with a `= null` default; base-typed slots accept engine subtypes (an
`AudioStreamWAV` or `AudioStreamOggVorbis` lands in an `AudioStream` slot):

```kotlin
@Export
var stream: AudioStream? = null

@Export
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
declare a class that extends `KanamaScript<Resource>` — do not subclass the
`Resource` wrapper — and attach it with `@ScriptClass(attachTo = "Resource")`:

```kotlin
@ScriptClass(attachTo = "Resource")
@GlobalClass
class Weapon(godotObject: GodotHandle) :
    KanamaScript<Resource>(godotObject, Resource::fromHandle) {
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
slots, GDScript can statically type against it (`@export var w: Weapon`,
`var copy: Weapon = w`, `w is Weapon`), and another script can export
`Weapon?` or `List<Weapon>` — Kanama
resolves the live Kotlin resource script instances when reading the property;
values (including nested resource slots like `swingSound`) round-trip through
`.tscn`/`.tres` storage.

One naming constraint: the file must be named after the class (`Weapon.kt`),
or the class cannot be mapped back to its script file and stays out of the
global class list — the build warns when they diverge.

Extending `KanamaScript` is what lets the script object keep its resource
alive: holding a `Weapon` keeps the weapon resource usable however you got it
(`kotlinScriptInstance<Weapon>()`, `newScriptInstance<Weapon>().instance`), and
dropping it lets the resource go, as in GDScript
([details](godot-api.md#a-script-object-keeps-its-resource-alive)). A **plain
class** (`class Weapon(val godotObject: GodotHandle)`) also works as a resource
script, but cannot carry that link: a resource with a plain script keeps the
lifetime it had before the garbage-collector fallback — wrappers you close
release at once, while a forgotten wrapper of it stays alive until its script is
detached or the game ends.

Generated wrappers (`net.multigesture.kanama.api.Resource`, `AudioStream`,
...) are non-owning views (constructors take a raw handle and never retain) and **cannot be
subclassed** — attempting it fails the build with a pointer to the pattern
above. The wrapper surface would otherwise drift from the script surface;
`KanamaScript<T>` is the only supported base class.

## Saving Custom Resources

Because a custom resource script is a script object rather than a `Resource`
subtype, APIs with `Resource`-typed parameters (such as `ResourceSaver.save`)
do not accept it directly. Pass its `self` (the `Resource` view of the object the
script is attached to):

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class WeaponForge(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    @Export
    var weapon: Weapon? = null

    @ExportToolButton(text = "Save weapon")
    fun saveWeapon() {
        val weapon = weapon ?: return
        ResourceSaver.save(weapon.self, "res://weapon.tres")
    }
}
```

`self` is a view over the same engine object that takes no reference of its
own (the script object keeps the resource alive); there is nothing to close.
For a plain script class, `Resource.fromHandle(weapon.godotObject)` is the same
kind of view.

Calling a script class constructor yourself —
`Weapon(someOtherObject.godotObject)` — does **not** create a new `Weapon`
resource; it only wraps an existing handle in a Kotlin view, and passing another
object's handle (a node's, for example) produces a view of the wrong object.

## Creating Custom Resources from Kotlin

To mint a brand-new resource from code (the equivalent of GDScript's
`Weapon.new()` or C#'s `new Weapon()`), use `newScriptInstance<T>()`. It returns
an `OwnedScriptResource<T>` holding the live `instance` and its owning `resource`:

```kotlin
newScriptInstance<Weapon>().use { owned ->
    owned.instance.damage = 25
    owned.instance.displayName = "Excalibur"
    ResourceSaver.save(owned.resource, "res://excalibur.tres")
}
```

It constructs a fresh engine `Resource`, attaches the Kanama script, and returns
the live Kotlin instance. It works at runtime and from `@Tool` editor code (a real
instance is built even though the resource class itself is not `@Tool`).

Requirements: `T` must be a `@ScriptClass(attachTo = "Resource")` class, and it
must be `@GlobalClass` with a matching file name (`Weapon.kt`) so its `res://`
path is discoverable for saving — the same constraint the global class list
already imposes.

Ownership mirrors GDScript `.new()`: the returned resource comes back with one
owning reference, so it survives the `ResourceSaver.save` call above (which wraps
it in a transient `Ref<>` internally). **Release that reference** with
`owned.close()` — or `use { }` as above — once you are done, including after
assigning `owned.resource` into a node/scene or an exported `Resource` slot: the
engine takes its own reference there and `close()` drops only yours (the one
rule in [Resource Ownership](godot-api.md#resource-ownership)). Pass `owned.resource`
directly to `Resource`-typed APIs such as `ResourceSaver.save`; there is no
`Resource.fromHandle` view to manage.

**iOS**: `newScriptInstance` is **deferred on iOS** (Kotlin/Native) — the
declaration exists so shared game code compiles, but calling it throws at
runtime. Construct script-backed resources on desktop/Android, or guard the call
by platform. Its sibling `kotlinScriptInstance<T>()` (resolving the Kotlin
instance of an existing resource) is supported on all three backends.

**Not `ClassDB.instantiate`**: `ClassDB.instantiate("Weapon")` returns `null`
(with `Cannot get class 'Weapon'`) for a custom resource class, even though
`ClassDB.can_instantiate("Weapon")` returns `true`. This is **upstream Godot
behaviour, not a Kanama limitation** — script global classes (GDScript, C#, and
Kanama alike) live in the script server, not the engine `ClassDB` registry, so
`ClassDB.instantiate` cannot construct them and `can_instantiate` answers from a
different lookup. Construct with `newScriptInstance<T>()` from Kotlin, or from
GDScript attach the loaded script to a base resource:
`var r = Resource.new(); r.set_script(load("res://Weapon.kt"))`.

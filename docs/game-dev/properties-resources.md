# Exports and Resources

An exported property is a field the inspector can edit and a scene can save,
as with GDScript's `@export`. This page covers exports and their inspector
hints, enums, lists and dictionaries, resource slots, and your own resource
types. The snippets sit inside a script like the one in
[Writing Scripts](scripts.md#a-complete-script), which lists the imports.

## Exported Properties

```kotlin
@ScriptClass(attachTo = "CharacterBody3D")
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    @ExportCategory("Tuning")
    @ExportGroup("Movement")
    @ExportRange(0.0, 20.0, 0.1)
    var speed: Double = 5.0

    @ExportSubgroup("Jump")
    @Export
    var jumpVelocity: Double = 8.0

    @Export(name = "hp")
    @ExportRange(0.0, 100.0)
    var health: Long = 100
}
```

`@Export` puts a property in the inspector under its `snake_case` name
(`jump_velocity`). A hint annotation such as `@ExportRange` exports the
property by itself, as in GDScript. Add `@Export` beside a hint only to set
`name`, which overrides the Godot name, or `usage`, which takes
`PropertyUsage` flags for unusual inspector behavior.

The initializer is the inspector's default, the value the revert arrow goes
back to, when the build can work it out: a literal, an enum value,
`NodePath("...")`, a named constant such as `Vector3.UP`, a value-type
constructor of those, or arithmetic on them (`Mathf.PI / 3.0`,
`degToRad(90.0)`). Any other initializer still sets the property, but the
inspector shows no default for it.

### Values of the Wrong Type

```kotlin
@OnReady
fun ready() {
    self.set("hp", "42")                        // a numeric String: stored as 42
    self.set("hp", Vector2(1.0, 2.0))           // refused: hp keeps its value
}
```

A typed property follows GDScript's rule for a typed `@export var` when a
value comes from `set()` or from GDScript. A value of the declared type
is stored. A value Godot can convert to the declared type is converted: an
`int` for a `Double` and back, a numeric `String` for a number, a `Vector2i`
for a `Vector2`, a colour name for a `Color`. Anything else is refused: the
property keeps its value and Godot prints a script error such as
`Expected Vector3 for property 'velocity' but the Variant holds Vector2`.

## Export Hints

Every GDScript `@export_*` annotation has a Kotlin twin that builds the same
hint, so the inspector shows a Kotlin property exactly as it shows the
GDScript one:

| GDScript | Kanama | Property types |
|---|---|---|
| `@export_range(0, 100, 1)` | `@ExportRange(0.0, 100.0, 1.0)` | `Long`, `Int`, `Double`, `Float` |
| `@export_range(0, 100, 1, "or_greater", "suffix:m")` | `@ExportRange(0.0, 100.0, 1.0, orGreater = true, suffix = "m")` | as above |
| `@export_enum("Warrior", "Magician:5")` | `@ExportEnum("Warrior", "Magician:5")` | `Long`, `Int`, `String`, `List<String>` |
| `@export_flags("Fire", "Water:4")` | `@ExportFlags("Fire", "Water:4")` | `Long`, `Int` |
| `@export_flags_2d_render` / `_2d_physics` / `_2d_navigation` | `@ExportFlags2DRender` / `@ExportFlags2DPhysics` / `@ExportFlags2DNavigation` | `Long`, `Int` |
| `@export_flags_3d_render` / `_3d_physics` / `_3d_navigation` | `@ExportFlags3DRender` / `@ExportFlags3DPhysics` / `@ExportFlags3DNavigation` | `Long`, `Int` |
| `@export_flags_avoidance` | `@ExportFlagsAvoidance` | `Long`, `Int` |
| `@export_file("*.png", "*.jpg")` | `@ExportFile("*.png", "*.jpg")` | `String`, `List<String>` |
| `@export_file_path("*.txt")` | `@ExportFilePath("*.txt")` | `String`, `List<String>` |
| `@export_dir` | `@ExportDir` | `String`, `List<String>` |
| `@export_global_file("*.cfg")` / `@export_global_dir` | `@ExportGlobalFile("*.cfg")` / `@ExportGlobalDir` | `String`, `List<String>` |
| `@export_multiline("monospace", "no_wrap")` | `@ExportMultiline(monospace = true, noWrap = true)` | `String`, `List<String>` |
| `@export_placeholder("Name")` | `@ExportPlaceholder("Name")` | `String`, `List<String>` |
| `@export_exp_easing("attenuation", "positive_only")` | `@ExportExpEasing(attenuation = true, positiveOnly = true)` | `Double`, `Float` |
| `@export_color_no_alpha` | `@ExportColorNoAlpha` | `Color` |
| `@export_node_path("Button", "TouchScreenButton")` | `@ExportNodePath("Button", "TouchScreenButton")` | `NodePath` |
| `@export_storage` | `@ExportStorage` | any exportable type |
| `@export_custom(PROPERTY_HINT_PASSWORD, "")` | `@ExportCustom(PropertyHint.PASSWORD)` | any exportable type |
| `@export_tool_button("Text", "Icon")` | `@ExportToolButton("Text", "Icon")` on a function | see [Inspector Buttons](#inspector-buttons) |

```kotlin
@ScriptClass(attachTo = "Node")
class Tuning(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {

    @ExportRange(0.0, 100.0, 1.0, orGreater = true)
    var health: Long = 100

    @ExportFile("*.png", "*.jpg")
    var icon: String = ""

    @ExportMultiline
    var description: String = ""

    @ExportFlags3DPhysics
    var hitLayers: Long = 1

    @ExportColorNoAlpha
    var tint: Color = Color(1.0, 0.5, 0.0)

    @Export
    var area: Rect2 = Rect2(Vector2.ZERO, Vector2(64.0, 32.0))
}
```

The arguments follow GDScript's rules, and a mistake is a build error: an
empty argument, a malformed `@ExportFlags` value, a hint on a type it does not
take, or two hint annotations on one property. On a `List<String>` the hint
applies to each element, as on a GDScript `Array[String]`. `@ExportRange`
without a step uses the editor's default: 1 for an integer. `@ExportCustom` takes any
`PropertyHint` value, for a hint no typed annotation covers.

`Color` and every other Godot value type (`Vector2` to `Vector4i`, `Rect2`,
`Rect2i`, `Plane`, `AABB`, `Quaternion`, `Basis`, `Transform2D`, `Transform3D`,
`Projection`) can be exported. A Kotlin `enum class` or a Godot enum needs no
hint: it shows as a dropdown on its own.

Coming from Kanama 0.4? [Typed Export Hints](../getting-started/upgrading-0.4-to-0.5.md#typed-export-hints)
converts the old annotations.

## Exporting Enums

```kotlin
enum class Difficulty { EASY, NORMAL, HARD }

@ScriptClass(attachTo = "Node")
class GameRules(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {

    @Export
    var difficulty: Difficulty = Difficulty.NORMAL

    @Export
    var unlockedModes: List<Difficulty> = emptyList()
}
```

A Kotlin `enum class` property shows as a dropdown of its entry names, as a C#
enum export does. The scene stores the entry's position in the class, so
**add new entries at the end**: reordering or removing entries changes what
saved scenes mean. A stored number with no matching entry loads as a valid
entry instead of failing. A `List` of an enum shows the same dropdown for each
element; its default must be empty (`emptyList()`), and you fill it in the
inspector.

### Godot Enums and Bitfields

```kotlin
@Export
var mode: Node.ProcessMode = Node.ProcessMode.PAUSABLE

@Export
var messages: Node.ProcessThreadMessages = Node.ProcessThreadMessages.MESSAGES   // a bitfield

@Export
var allowedModes: List<Node.ProcessMode> = emptyList()
```

A property typed with one of Godot's own enums (see
[Godot Enums and Bitfields](godot-api.md#godot-enums-and-bitfields)) shows as
a dropdown, and a bitfield as flag checkboxes. The scene stores Godot's number
for the value, so a scene saved by a GDScript or C# version of the script loads
unchanged, and typed GDScript sees the property as a `Node.ProcessMode`. The
type is non-null, and a list default must be empty. How the inspector metadata
compares with GDScript's is in
[API Conventions](../reference/wrapper-conventions.md#5-enums-and-bitfields).

## Exporting Dictionaries

```kotlin
@Export
var spawnCounts: Map<String, Long> = emptyMap()

@Export
var regionIcons: Map<Vector2i, Texture2D> = emptyMap()
```

A `Map<K, V>` exports as a typed Godot `Dictionary`, and the inspector offers
the matching key and value pickers. The default must be empty; fill it in the
inspector. A `Map<String, Any?>` is an untyped `Dictionary`.

- **Keys** can be `String`, `Long`, `Int`, `Double`, `Float`, `Boolean`,
  `Vector2`, `Vector2i`, `Vector3`, `Vector3i`, `Color` or an `enum class`.
- **Values** can be any of those, a Godot resource or node class, or one of
  your `@ScriptClass` scripts. A resource value is kept alive while the
  dictionary holds it, as in a `List<Texture2D>`. An `enum class` key or value
  is stored by position, as in an enum export.

An entry whose key or value does not match the declared types, such as one in
a hand-edited scene, is skipped. A nil value is kept as `null` in a
`Map<K, V?>` and dropped from a `Map<K, V>`. An object value type cannot be
nullable: declare `Map<String, Texture2D>`, not `Map<String, Texture2D?>`.

## Node References

```kotlin
@Export
var crosshair: TextureRect? = null

@Export
var viewPath: NodePath = NodePath("../View")
```

Export a node reference as its class when the GDScript original exported the
node. Use `NodePath` only when the original exported a path on purpose.

## Resource Slots

```kotlin
@Export
var stream: AudioStream? = null

@Export
var icons: List<Texture2D> = emptyList()
```

An exported resource is nullable with a `= null` default. A slot of a base
class accepts its subclasses: an `AudioStreamWAV` or `AudioStreamOggVorbis`
goes into an `AudioStream` slot. The resource classes you can export are
`PackedScene`, `Texture`, `Texture2D`, `NoiseTexture2D`, `Material`,
`ShaderMaterial`, `Curve`, `FastNoiseLite`, `ButtonGroup`, `LightmapGIData`,
`AudioStream`, `Mesh`, `Shape2D`, `Shape3D`, `Font`, `Animation`, `StyleBox`,
and your own [custom resources](#custom-resources). If you need another one,
please [open an issue](https://github.com/falcon4ever/kanama/issues).

Do not use `lateinit var` for an export. It has no inspector default, and
reading it before the scene assigns it crashes. The build warns and suggests
the `= null` form.

## Inspector Buttons

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class Spawner(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {

    @Export
    var seed: Long = 0

    @ExportToolButton(text = "Randomize", icon = "RandomNumberGenerator")
    fun randomize() {
        seed = GD.randiRange(0, 9999)
        notifyInspectorChanged()
    }
}
```

`@ExportToolButton` on a function with no parameters shows a button in the
inspector, like GDScript's `@export_tool_button`. The script must be `@Tool`,
and the function runs in the editor, so guard editor-only work as in any tool
script.

## Releasing Resources You Create

The rule is on [Calling Godot APIs](godot-api.md#resource-ownership):
**`close()` releases your reference. It never destroys an object someone else
still holds.** The case you meet most is a resource you create and hand to the
engine:

```kotlin
private fun bindKeys(action: String, keys: List<Key>) {
    InputMap.addAction(action)
    for (key in keys) {
        InputEventKey.create().use { inputKey ->      // you own this reference
            inputKey.keycode = key
            InputMap.actionAddEvent(action, inputKey) // InputMap takes its own
        }                                             // use { } releases yours
    }
}
```

The same code in GDScript or C# needs no cleanup, and it does not leak there
either:

```gdscript
var input_key = InputEventKey.new()
input_key.keycode = keycode
InputMap.action_add_event(action, input_key)
# the reference is dropped when input_key leaves scope
```

```csharp
var inputKey = new InputEventKey();
inputKey.Keycode = keycode;
InputMap.ActionAddEvent(action, inputKey);
// the reference is dropped when the garbage collector collects inputKey
```

GDScript drops your reference when the variable leaves scope. C# drops it when
the garbage collector collects the object. Kanama does both: `use { }` releases
your reference at once, as GDScript's scope exit does, and a wrapper you forget
is released after the garbage collector drops it, as in C#. So closing is about
*when*: `use { }` releases now, the collector releases later. The project
setting `kanama/debug/log_gc_releases` names each line whose wrapper the
collector had to release.

## Custom Resources

```kotlin
@ScriptClass(attachTo = "Resource")
@GlobalClass
class Weapon(godotObject: GodotHandle) :
    KanamaScript<Resource>(godotObject, ::Resource) {

    @Export
    var damage: Long = 10

    @Export
    var displayName: String = "Sword"

    @Export
    var swingSound: AudioStream? = null
}
```

A custom resource is a script attached to `Resource`, like GDScript's
`extends Resource` with a `class_name`. Extend `KanamaScript<Resource>`; the
Godot classes cannot be subclassed. Save the class in a file of the same name
(`Weapon.kt`), or it stays out of the editor's class list; the build warns when
they differ.

`@GlobalClass` makes `Weapon` a resource type the editor knows: it appears in
**Create New Resource**, its `.tres` files fit typed export slots, and GDScript
can type against it (`@export var w: Weapon`, `w is Weapon`). Another script
can export `Weapon?` or `List<Weapon>`, and the values, nested resources such as
`swingSound` included, are saved in `.tscn` and `.tres` files.

### Keeping a Resource through Its Script

```kotlin
@ScriptClass(attachTo = "Node")
class WeaponSlot(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {

    private var weapon: Weapon? = null

    @OnReady
    fun ready() {
        // keep the script object; the loaded wrapper is closed at once
        weapon = ResourceLoader.load("user://weapon.tres")?.use { it.kotlinScriptInstance<Weapon>() }
    }

    fun save() {
        val weapon = weapon ?: return
        ResourceSaver.save(weapon.self, "user://weapon.tres")
    }
}
```

Holding the script object of a resource keeps the resource alive, as holding
the resource does in GDScript, however you got the script object and whatever
happened to the wrapper you reached it through. Drop your last reference to
the script object and the resource is released like any forgotten wrapper.

The garbage collector can drop the script object while Godot still caches the
resource. If the resource is then loaded again before the next frame, its
script object is rebuilt on first use and reads its values from the resource's
file, as a GDScript resource would:

- the constructor and `init` blocks run again, once for the rebuilt script
  object and once for an uncached copy of the file that the rebuild reads;
- if the file changed on disk since the load, the engine properties keep their
  values in memory and the script properties come from the file;
- a resource that was never saved, or one stored inside another file
  (`res://level.tres::Resource_x`), starts from its defaults, with a warning.

A plain class (`class Weapon(val godotObject: GodotHandle)`) also works as a
resource script, but its script object does not keep the resource alive: a
wrapper you close releases at once, and a wrapper you forget stays alive until
the script is detached or the game ends.

## Saving Custom Resources

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class WeaponForge(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {

    @Export
    var weapon: Weapon? = null

    @ExportToolButton(text = "Save Weapon")
    fun saveWeapon() {
        val weapon = weapon ?: return
        ResourceSaver.save(weapon.self, "res://weapon.tres")
    }
}
```

A custom resource script is not itself a `Resource`, so pass its `self` to an
API that takes one, such as `ResourceSaver.save`. `self` takes no reference of
its own, so there is nothing to close. For a plain script class,
`Resource.fromHandle(weapon.godotObject)` is the same kind of view.

Calling the constructor yourself, `Weapon(someNode.handle)`, does not
create a resource. It wraps the handle you pass, which gives a view of the
wrong object when that handle is not a `Weapon` resource.

## Creating Custom Resources from Kotlin

```kotlin
@ScriptClass(attachTo = "Node")
@Tool
class Armory(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) {

    @ExportToolButton(text = "Make Excalibur")
    fun makeExcalibur() {
        newScriptInstance<Weapon>().use { owned ->
            owned.instance.damage = 25
            owned.instance.displayName = "Excalibur"
            ResourceSaver.save(owned.resource, "res://excalibur.tres")
        }
    }
}
```

`newScriptInstance<T>()` is GDScript's `Weapon.new()`: it creates a `Resource`,
attaches the script and returns both, the script object as `instance` and the
resource as `resource`. It works in the game and in `@Tool` code, even when
the resource script is not `@Tool`. `T` must be
a `@GlobalClass` resource script in a file of the same name.

The result holds one reference to the new resource, as `.new()` does. Release
it with `use { }` or `close()` when you are done, also after you assign
`owned.resource` to a node or an exported slot: the engine takes its own
reference there.

`ClassDB.instantiate("Weapon")` returns `null` for a custom resource, even
though `ClassDB.canInstantiate("Weapon")` is `true`. This is Godot's behavior
for every script class, GDScript and C# included, because script classes are
not in `ClassDB`. Use `newScriptInstance<Weapon>()` from Kotlin. From GDScript,
attach the script to a new resource:
`var r = Resource.new(); r.set_script(load("res://Weapon.kt"))`.

## On Other Platforms

Exports and custom resources work the same way on desktop and Android. iOS
and Web cannot carry a few export types, and the build stops and names the
property when you use one. iOS has no `newScriptInstance`, and on Web a custom
resource's script object does not keep its resource alive. On Android 8 to 12
the garbage collector does not release a resource you forget to close.
[Limitations](../reference/limitations.md) lists each case with its workaround.

package net.multigesture.kanama.annotations

/**
 * Marks a Kotlin class for registration with Godot's ClassDB as a new class (an extension class,
 * like a godot-cpp class), as opposed to [ScriptClass], which attaches a script to an existing
 * node.
 *
 * The class is registered as a new node type under [parentClassName] in Godot's "Add Node" dialog.
 * The KSP processor generates a companion `<ClassName>Registrar` object that wires up the upcall
 * stubs and calls [net.multigesture.kanama.binding.runtime.ClassDB]. Its public functions are
 * registered as methods and its [Export] properties as properties, exactly as on a script class.
 *
 * @property parentClassName The Godot engine class this type derives from (e.g. `"Node"`,
 *   `"Node3D"`, `"Sprite2D"`). Must match an engine-known class name exactly.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class RegisterClass(val parentClassName: String = "Object")

/**
 * Gives a registered function a Godot-side name other than its snake_case Kotlin name.
 *
 * Every public function declared in a [ScriptClass] or [RegisterClass] class is registered with
 * Godot automatically, under its Kotlin name converted from camelCase (`fun showMessage()` is
 * `show_message`), like a GDScript `func`. Use this annotation only where Godot must find the
 * function under another name: a signal connection saved in a `.tscn` by the editor
 * (`@GodotName("_on_start_button_pressed") fun onStartButtonPressed()`), or a name a GDScript
 * caller already uses. `private`, `protected` and `internal` functions stay Kotlin-only.
 *
 * @property name The engine-facing method name, used verbatim.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class GodotName(val name: String)

/**
 * Marks a registered function as available for Godot high-level multiplayer RPC.
 *
 * Put it on a public function of a [ScriptClass] (registered automatically). The defaults match
 * Godot's `@rpc` defaults.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Rpc(
  val mode: Int = RpcMode.AUTHORITY,
  val callLocal: Boolean = false,
  val transferMode: Int = RpcTransferMode.RELIABLE,
  val channel: Int = 0,
)

/** Godot `MultiplayerAPI.RPCMode` values for [Rpc]. */
object RpcMode {
  const val DISABLED = 0
  const val ANY_PEER = 1
  const val AUTHORITY = 2
}

/** Godot `MultiplayerPeer.TransferMode` values for [Rpc]. */
object RpcTransferMode {
  const val UNRELIABLE = 0
  const val UNRELIABLE_ORDERED = 1
  const val RELIABLE = 2
}

/**
 * Godot's `PropertyHint` values (`PROPERTY_HINT_*`), every one Godot 4.7 declares.
 *
 * The typed export annotations ([ExportRange], [ExportFile], [ExportFlags2DPhysics], ...) build the
 * hint and its hint string for you, as GDScript's `@export_*` annotations do; these constants are
 * for [ExportCustom] (GDScript `@export_custom`), the escape hatch for a hint no typed annotation
 * covers.
 */
object PropertyHint {
  /** No hint. `PROPERTY_HINT_NONE`. */
  const val NONE = 0

  /** Numeric range (`@ExportRange`). `PROPERTY_HINT_RANGE`. */
  const val RANGE = 1

  /** Fixed-value enum (`@ExportEnum`). `PROPERTY_HINT_ENUM`. */
  const val ENUM = 2

  /** Suggested values; arbitrary text is still accepted. `PROPERTY_HINT_ENUM_SUGGESTION`. */
  const val ENUM_SUGGESTION = 3

  /** Easing curve (`@ExportExpEasing`). `PROPERTY_HINT_EXP_EASING`. */
  const val EXP_EASING = 4

  /** Linked x/y/z values (vectors). `PROPERTY_HINT_LINK`. */
  const val LINK = 5

  /** Bit flags (`@ExportFlags`). `PROPERTY_HINT_FLAGS`. */
  const val FLAGS = 6

  /** 2D render layers (`@ExportFlags2DRender`). `PROPERTY_HINT_LAYERS_2D_RENDER`. */
  const val LAYERS_2D_RENDER = 7

  /** 2D physics layers (`@ExportFlags2DPhysics`). `PROPERTY_HINT_LAYERS_2D_PHYSICS`. */
  const val LAYERS_2D_PHYSICS = 8

  /** 2D navigation layers (`@ExportFlags2DNavigation`). `PROPERTY_HINT_LAYERS_2D_NAVIGATION`. */
  const val LAYERS_2D_NAVIGATION = 9

  /** 3D render layers (`@ExportFlags3DRender`). `PROPERTY_HINT_LAYERS_3D_RENDER`. */
  const val LAYERS_3D_RENDER = 10

  /** 3D physics layers (`@ExportFlags3DPhysics`). `PROPERTY_HINT_LAYERS_3D_PHYSICS`. */
  const val LAYERS_3D_PHYSICS = 11

  /** 3D navigation layers (`@ExportFlags3DNavigation`). `PROPERTY_HINT_LAYERS_3D_NAVIGATION`. */
  const val LAYERS_3D_NAVIGATION = 12

  /** Project file path, stored as a UID when possible (`@ExportFile`). `PROPERTY_HINT_FILE`. */
  const val FILE = 13

  /** Project directory (`@ExportDir`). `PROPERTY_HINT_DIR`. */
  const val DIR = 14

  /** Absolute file path (`@ExportGlobalFile`). `PROPERTY_HINT_GLOBAL_FILE`. */
  const val GLOBAL_FILE = 15

  /** Absolute directory (`@ExportGlobalDir`). `PROPERTY_HINT_GLOBAL_DIR`. */
  const val GLOBAL_DIR = 16

  /** Resource class (derived from the property type). `PROPERTY_HINT_RESOURCE_TYPE`. */
  const val RESOURCE_TYPE = 17

  /** Multiline text (`@ExportMultiline`). `PROPERTY_HINT_MULTILINE_TEXT`. */
  const val MULTILINE_TEXT = 18

  /** An `Expression` string. `PROPERTY_HINT_EXPRESSION`. */
  const val EXPRESSION = 19

  /** Placeholder text (`@ExportPlaceholder`). `PROPERTY_HINT_PLACEHOLDER_TEXT`. */
  const val PLACEHOLDER_TEXT = 20

  /** Color without alpha. `PROPERTY_HINT_COLOR_NO_ALPHA`. */
  const val COLOR_NO_ALPHA = 21

  /** Object id. `PROPERTY_HINT_OBJECT_ID`. */
  const val OBJECT_ID = 22

  /** Typed-array element hint (derived from `List<T>`). `PROPERTY_HINT_TYPE_STRING`. */
  const val TYPE_STRING = 23

  /** Deprecated by Godot; kept for completeness. `PROPERTY_HINT_NODE_PATH_TO_EDITED_NODE`. */
  const val NODE_PATH_TO_EDITED_NODE = 24

  /** Object too big to send (debugger). `PROPERTY_HINT_OBJECT_TOO_BIG`. */
  const val OBJECT_TOO_BIG = 25

  /**
   * Allowed node classes of a `NodePath` (`@ExportNodePath`).
   * `PROPERTY_HINT_NODE_PATH_VALID_TYPES`.
   */
  const val NODE_PATH_VALID_TYPES = 26

  /** Project save-file path. `PROPERTY_HINT_SAVE_FILE`. */
  const val SAVE_FILE = 27

  /** Absolute save-file path. `PROPERTY_HINT_GLOBAL_SAVE_FILE`. */
  const val GLOBAL_SAVE_FILE = 28

  /** Deprecated by Godot. `PROPERTY_HINT_INT_IS_OBJECTID`. */
  const val INT_IS_OBJECTID = 29

  /** Integer is a pointer. `PROPERTY_HINT_INT_IS_POINTER`. */
  const val INT_IS_POINTER = 30

  /** Array element type. `PROPERTY_HINT_ARRAY_TYPE`. */
  const val ARRAY_TYPE = 31

  /** Locale code. `PROPERTY_HINT_LOCALE_ID`. */
  const val LOCALE_ID = 32

  /** Localizable string dictionary. `PROPERTY_HINT_LOCALIZABLE_STRING`. */
  const val LOCALIZABLE_STRING = 33

  /** Node class (derived from the property type). `PROPERTY_HINT_NODE_TYPE`. */
  const val NODE_TYPE = 34

  /** Hide the quaternion editor. `PROPERTY_HINT_HIDE_QUATERNION_EDIT`. */
  const val HIDE_QUATERNION_EDIT = 35

  /** Masked text. `PROPERTY_HINT_PASSWORD`. */
  const val PASSWORD = 36

  /** Avoidance layers (`@ExportFlagsAvoidance`). `PROPERTY_HINT_LAYERS_AVOIDANCE`. */
  const val LAYERS_AVOIDANCE = 37

  /** Typed-dictionary hint string (derived from `Map<K, V>`). `PROPERTY_HINT_DICTIONARY_TYPE`. */
  const val DICTIONARY_TYPE = 38

  /** Inspector tool button (`@ExportToolButton`). `PROPERTY_HINT_TOOL_BUTTON`. */
  const val TOOL_BUTTON = 39

  /** One-shot value the inspector resets. `PROPERTY_HINT_ONESHOT`. */
  const val ONESHOT = 40

  /** A group header with an enable checkbox. `PROPERTY_HINT_GROUP_ENABLE`. */
  const val GROUP_ENABLE = 42

  /** Input action name. `PROPERTY_HINT_INPUT_NAME`. */
  const val INPUT_NAME = 43

  /** Project file path stored as a raw path (`@ExportFilePath`). `PROPERTY_HINT_FILE_PATH`. */
  const val FILE_PATH = 44
}

/** Godot property usage flag constants for inspector and serialization policy. */
object PropertyUsage {
  const val NONE = 0
  const val STORAGE = 2
  const val EDITOR = 4
  const val DEFAULT = STORAGE or EDITOR
  const val GROUP = 64
  const val CATEGORY = 128
  const val SUBGROUP = 256
  const val SCRIPT_VARIABLE = 4096
  const val STORE_IF_NULL = 8192
  const val UPDATE_ALL_IF_MODIFIED = 16384
  const val SCRIPT_DEFAULT_VALUE = 32768
  const val READ_ONLY = 268435456
  const val SECRET = 536870912
  const val NO_EDITOR = STORAGE
}

/**
 * Exports a property to Godot: inspector-visible, saved with the scene or resource, and readable
 * and writable from GDScript and the engine (GDScript `@export`).
 *
 * On a [ScriptClass] the property is routed through the script instance's `set` / `get` callbacks;
 * on a [RegisterClass] the processor emits `get_<name>` / `set_<name>` methods and registers the
 * property with ClassDB.
 *
 * Inspector hints are their own annotations, as in GDScript, and export the property by themselves:
 * `@ExportRange(0.0, 100.0, 1.0) var health = 100L` is GDScript's `@export_range(0, 100, 1) var
 * health := 100`. Keep `@Export` next to one only to set [name] or [usage]. (Kanama 0.5 removed the
 * raw `hint` / `hintString` parameters; [ExportCustom] is GDScript's `@export_custom` for a hint no
 * typed annotation covers.)
 *
 * @property name Optional engine-facing property name (snake_case). Defaults to the Kotlin property
 *   name converted from camelCase.
 * @property usage PROPERTY_USAGE_* flags. Defaults to [PropertyUsage.DEFAULT].
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class Export(val name: String = "", val usage: Int = PropertyUsage.DEFAULT)

/**
 * Marks a class as a tool script — its code runs in the editor too, not just at runtime. Equivalent
 * to `@tool` in GDScript. Only meaningful on [RegisterClass]- and [ScriptClass]-annotated types.
 */
@Target(AnnotationTarget.CLASS) @Retention(AnnotationRetention.SOURCE) annotation class Tool

/**
 * Exposes a zero-argument function as a clickable inspector button (GDScript `@export_tool_button`,
 * Godot C# `[ExportToolButton]`).
 *
 * Use on `@Tool @ScriptClass` scripts. The generated script metadata exposes a Callable property
 * with Godot's tool-button hint; clicking it in the inspector invokes the annotated function on the
 * edited script instance.
 *
 * @property text Button label shown in the inspector.
 * @property icon Optional editor icon name from Godot's `EditorIcons` theme.
 * @property name Optional engine-facing property name. Defaults to the function name converted from
 *   camelCase plus `_button`.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportToolButton(val text: String, val icon: String = "", val name: String = "")

/**
 * Exposes the class globally — i.e. it appears under "Node" in the Add Node dialog even without
 * being explicitly referenced. Matches the GDScript `class_name` directive.
 */
@Target(AnnotationTarget.CLASS) @Retention(AnnotationRetention.SOURCE) annotation class GlobalClass

/**
 * Marks a zero-arg, void Kotlin function as the handler for Godot's `_ready` virtual. The function
 * name on the Kotlin side is free — the generator wires this annotation to the engine's `_ready`
 * slot.
 */
@Target(AnnotationTarget.FUNCTION) @Retention(AnnotationRetention.SOURCE) annotation class OnReady

/** Handler for Godot's `_enter_tree` virtual. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnEnterTree

/** Handler for Godot's `_exit_tree` virtual. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnExitTree

/**
 * Handler for Godot's `_process(delta: Double)` virtual. The annotated function must accept a
 * single `Double` parameter (the frame delta in seconds).
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnProcess

/**
 * Handler for Godot's `_physics_process(delta: Double)` virtual. The annotated function must accept
 * a single `Double` parameter.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnPhysicsProcess

/**
 * Handler for Godot's `_input(event: InputEvent)` virtual. Receives every input event the engine
 * routes to this node before SceneTree dispatches it for action handling. The annotated function
 * takes one `InputEvent` (`fun input(event: InputEvent)`); cast it to the subclass you handle with
 * `InputEventKey.from(event)` and friends. The same holds for [OnUnhandledInput], [OnShortcutInput]
 * and [OnUnhandledKeyInput].
 */
@Target(AnnotationTarget.FUNCTION) @Retention(AnnotationRetention.SOURCE) annotation class OnInput

/**
 * Handler for Godot's `_unhandled_input(event: InputEvent)` virtual. Fires for events that no other
 * UI/control consumed. Most gameplay input handlers belong here, not in `_input`.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnUnhandledInput

/**
 * Handler for Godot's `_shortcut_input(event: InputEvent)` virtual. Fires for events that may match
 * a `Shortcut` resource bound on this node. Runs before `_unhandled_input`.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnShortcutInput

/**
 * Handler for Godot's `_unhandled_key_input(event: InputEvent)` virtual. Fires only for unhandled
 * keyboard events; cheaper than `_unhandled_input` when you only care about keyboard.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnUnhandledKeyInput

/**
 * Overrides an arbitrary engine virtual method on the script's attach-to class (or any of its
 * ancestors), beyond the fixed lifecycle set.
 *
 * **The annotated function's name is the Godot virtual's engine name** — write the underscored
 * virtual directly, exactly as in GDScript: `fun _draw()`, `fun _gui_input(event: InputEvent)`,
 * `fun _get_minimum_size(): Vector2`. The function's parameters and return type must match the
 * engine virtual's signature; the KSP processor validates this against a generated table derived
 * from `extension_api.json` and fails the build on a mismatch (unknown virtual for the attach-to
 * class, wrong arity, or a value returned from a `void` virtual / vice versa).
 *
 * (The virtual name is taken from the function name rather than an annotation argument because KSP2
 * over Kotlin/Native does not expose function-annotation argument values; the function name is
 * available on every target, and this also matches GDScript's `func _draw()` convention.)
 *
 * The lifecycle annotations ([OnReady], [OnProcess], [OnInput], …) cover the most common virtuals;
 * use `@OverrideVirtual` for everything else (custom drawing via `_draw`, control input via
 * `_gui_input`, editor warnings via `_get_configuration_warnings`, drag-and-drop, …).
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OverrideVirtual

/**
 * Marks a Kotlin class as a Godot script that can be attached to an existing node type (e.g.
 * CharacterBody3D, Node3D) without registering a new type in ClassDB.
 *
 * The KSP processor generates a `<ClassName>ScriptRegistrar` that creates a [KanamaScript] resource
 * backed by this class, with a factory that constructs the Kotlin instance and wires
 * property/method dispatch via the [ScriptBridge] vtable.
 *
 * The annotated class must have a primary constructor accepting a single
 * `java.lang.foreign.MemorySegment` (the owning Godot node object).
 *
 * @property attachTo The Godot base class this script is compatible with (e.g. "CharacterBody3D").
 *   Shown in the editor and returned from `KanamaScript._get_instance_base_type`.
 */
@Target(AnnotationTarget.CLASS)
// BINARY (not SOURCE) so the marker survives into the class file: R8/ProGuard consumer rules keep
// @ScriptClass class names by this annotation, which newScriptInstance<T>() needs (it resolves the
// template by T::class.qualifiedName, and obfuscation would otherwise break that lookup in minified
// release). KSP reads it from source regardless of retention, and nothing reads it via reflection.
@Retention(AnnotationRetention.BINARY)
annotation class ScriptClass(val attachTo: String = "Node")

/**
 * Starts an inspector export category before the annotated exported property.
 *
 * This mirrors Godot's category rows in the inspector. Because Kotlin annotations cannot be
 * standalone declarations, place this on the first [Export] that should appear inside the category.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportCategory(val name: String)

/**
 * Starts an inspector export group before the annotated exported property.
 *
 * This mirrors GDScript's `@export_group("Name", "prefix_")`. Because Kotlin annotations cannot be
 * standalone declarations, place this on the first [Export] that should appear inside the group.
 *
 * @property name Display name for the group. Use an empty name with an empty [prefix] to end the
 *   current group.
 * @property prefix Optional property-name prefix used by Godot's inspector grouping logic. When
 *   non-empty, Godot removes this prefix from displayed property names and ends the group when a
 *   following property does not match.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportGroup(val name: String, val prefix: String = "")

/**
 * Starts an inspector export subgroup before the annotated exported property.
 *
 * This mirrors GDScript's `@export_subgroup("Name", "prefix_")`. Because Kotlin annotations cannot
 * be standalone declarations, place this on the first [Export] that should appear inside the
 * subgroup.
 *
 * Subgroups require a parent [ExportGroup] to be active in Godot's inspector.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportSubgroup(val name: String, val prefix: String = "")

/**
 * Declares a Godot signal on a [ScriptClass] or [RegisterClass] type.
 *
 * Place on a zero-body function whose parameter list describes the signal's argument signature
 * (names + types). The function body is ignored by the processor — declaring it as `Unit` is
 * conventional:
 * ```
 * @Signal fun pinged(value: Long) = Unit
 * ```
 *
 * @property name Optional engine-facing signal name (snake_case). Defaults to the Kotlin function
 *   name converted from camelCase.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Signal(val name: String = "")

// ---------- Removed in Kanama 0.5 (task 133 B) ----------
//
// Tombstones, not aliases: each removed name still resolves, so a leftover use is a compile error
// whose message names the replacement (without the class, `@Process` would quietly resolve to
// `java.lang.Process` and `@ScriptProperty` would say only "Unresolved reference").
// `DeprecationLevel.ERROR` makes every use fail to compile; nothing reads them. The KSP processor
// reports the same leftovers at their declarations. scripts/migrate_script_annotations.py rewrites
// a source tree.

@Deprecated(
  "removed in Kanama 0.5: public functions are registered automatically; delete it, or use @GodotName(\"...\") for another Godot name. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class RegisterFunction(val name: String = "")

@Deprecated(
  "removed in Kanama 0.5: public functions are registered automatically; delete it, or use @GodotName(\"...\") for another Godot name. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Method(val name: String = "")

@Deprecated(
  "removed in Kanama 0.5: use @Export (same parameters). scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ScriptProperty(
  val name: String = "",
  val hint: Int = 0,
  val hintString: String = "",
  val usage: Int = 6,
)

@Deprecated(
  "removed in Kanama 0.5: use @Export (same parameters). scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class RegisterProperty(
  val name: String = "",
  val hint: Int = 0,
  val hintString: String = "",
  val usage: Int = 6,
)

@Deprecated(
  "removed in Kanama 0.5: use @GlobalClass. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ClassName()

@Deprecated(
  "removed in Kanama 0.5: use @ExportToolButton (same parameters). scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ToolButton(val text: String = "", val icon: String = "", val name: String = "")

@Deprecated(
  "removed in Kanama 0.5: use @OnReady. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Ready()

@Deprecated(
  "removed in Kanama 0.5: use @OnEnterTree. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class EnterTree()

@Deprecated(
  "removed in Kanama 0.5: use @OnExitTree. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ExitTree()

@Deprecated(
  "removed in Kanama 0.5: use @OnProcess. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Process()

@Deprecated(
  "removed in Kanama 0.5: use @OnPhysicsProcess. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class PhysicsProcess()

@Deprecated(
  "removed in Kanama 0.5: use @OnInput. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Input()

@Deprecated(
  "removed in Kanama 0.5: use @OnUnhandledInput. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class UnhandledInput()

@Deprecated(
  "removed in Kanama 0.5: use @OnShortcutInput. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ShortcutInput()

@Deprecated(
  "removed in Kanama 0.5: use @OnUnhandledKeyInput. scripts/migrate_script_annotations.py rewrites a source tree.",
  level = DeprecationLevel.ERROR,
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class UnhandledKeyInput()

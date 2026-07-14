package net.multigesture.kanama.annotations

/**
 * Marks a Kotlin class for registration with Godot's ClassDB.
 *
 * The annotated class must extend an engine type (directly or
 * transitively) and will be registered as a new node type under
 * [parentClassName] in Godot's "Add Node" dialog. The KSP processor
 * generates a companion `<ClassName>Registrar` object that wires up
 * the upcall stubs and calls [net.multigesture.kanama.binding.runtime.ClassDB].
 *
 * @property parentClassName The Godot engine class this type derives
 *   from (e.g. `"Node"`, `"Node3D"`, `"Sprite2D"`). Must match an
 *   engine-known class name exactly.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class RegisterClass(val parentClassName: String = "Object")

/**
 * Marks a function on a [RegisterClass]-annotated class as an engine-
 * callable method. The generator emits call/ptrcall upcall stubs and
 * registers the method via [net.multigesture.kanama.binding.runtime.ClassDB.registerMethod].
 *
 * @property name Optional override for the engine-facing method name
 *   (snake_case). Defaults to the function's Kotlin name converted
 *   from camelCase.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class RegisterFunction(val name: String = "")

/** Alias for [RegisterFunction] to reduce C#/attribute migration friction. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Method(val name: String = "")

/**
 * Marks a registered function as available for Godot high-level multiplayer RPC.
 *
 * Use with [RegisterFunction] or [Method]. The defaults match Godot's
 * `@rpc` defaults.
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
 * Marks a property on a [RegisterClass]-annotated class as an inspector-
 * visible, engine-accessible property. The generator emits backing
 * `get_<name>` / `set_<name>` methods and registers the property via
 * [net.multigesture.kanama.binding.runtime.ClassDB.registerProperty].
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class RegisterProperty(
    val name: String = "",
    /** PROPERTY_HINT_* constant. 0 = PROPERTY_HINT_NONE. */
    val hint: Int = 0,
    /** Hint string (e.g. "0,100,1" for PROPERTY_HINT_RANGE). */
    val hintString: String = "",
    /** PROPERTY_USAGE_* flags. Defaults to [PropertyUsage.DEFAULT]. */
    val usage: Int = PropertyUsage.DEFAULT,
)

/**
 * Godot property hint constants for inspector metadata.
 *
 * Use these in [RegisterProperty], [Export], and [ScriptProperty] annotations
 * instead of repeating raw `PROPERTY_HINT_*` integer values in gameplay code.
 */
object PropertyHint {
    /** No hint. Matches Godot's `PROPERTY_HINT_NONE`. */
    const val NONE = 0

    /** Numeric range hint. Matches Godot's `PROPERTY_HINT_RANGE`. */
    const val RANGE = 1

    /** Fixed-value enum hint. Matches Godot's `PROPERTY_HINT_ENUM`. */
    const val ENUM = 2

    /** Suggested enum values without restricting text input. Matches Godot's `PROPERTY_HINT_ENUM_SUGGESTION`. */
    const val ENUM_SUGGESTION = 3

    /** Exponential easing hint. Matches Godot's `PROPERTY_HINT_EXP_EASING`. */
    const val EXP_EASING = 4

    /** Bit flags hint. Matches Godot's `PROPERTY_HINT_FLAGS`. */
    const val FLAGS = 6

    /** File path relative to the project. Matches Godot's `PROPERTY_HINT_FILE`. */
    const val FILE = 13

    /** Directory path relative to the project. Matches Godot's `PROPERTY_HINT_DIR`. */
    const val DIR = 14

    /** Absolute file path. Matches Godot's `PROPERTY_HINT_GLOBAL_FILE`. */
    const val GLOBAL_FILE = 15

    /** Absolute directory path. Matches Godot's `PROPERTY_HINT_GLOBAL_DIR`. */
    const val GLOBAL_DIR = 16

    /** Resource type hint. Matches Godot's `PROPERTY_HINT_RESOURCE_TYPE`. */
    const val RESOURCE_TYPE = 17

    /** Multiline string hint. Matches Godot's `PROPERTY_HINT_MULTILINE_TEXT`. */
    const val MULTILINE_TEXT = 18

    /** Placeholder text hint. Matches Godot's `PROPERTY_HINT_PLACEHOLDER_TEXT`. */
    const val PLACEHOLDER_TEXT = 20

    /** Color without alpha hint. Matches Godot's `PROPERTY_HINT_COLOR_NO_ALPHA`. */
    const val COLOR_NO_ALPHA = 21

    /** Typed-array hint string. Matches Godot's `PROPERTY_HINT_TYPE_STRING`. */
    const val TYPE_STRING = 23

    /** Node type hint. Matches Godot's `PROPERTY_HINT_NODE_TYPE`. */
    const val NODE_TYPE = 34

    /** Typed-dictionary hint string (`"<key>;<value>"`). Matches Godot's `PROPERTY_HINT_DICTIONARY_TYPE`. */
    const val DICTIONARY_TYPE = 38

    /** Inspector tool button hint. Matches Godot's `PROPERTY_HINT_TOOL_BUTTON`. */
    const val TOOL_BUTTON = 39
}

/**
 * Godot property usage flag constants for inspector and serialization policy.
 */
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
 * Unified export alias for properties.
 *
 * On `@RegisterClass` types this maps to [RegisterProperty]-style behavior.
 * On `@ScriptClass` types this maps to [ScriptProperty]-style behavior.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class Export(
    val name: String = "",
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = PropertyUsage.DEFAULT,
)

/**
 * Marks a class as a tool script — its code runs in the editor too,
 * not just at runtime. Equivalent to `@tool` in GDScript. Only
 * meaningful on [RegisterClass]- and [ScriptClass]-annotated types.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Tool

/**
 * Exposes a zero-argument function as a clickable inspector button.
 *
 * Use on `@Tool @ScriptClass` scripts. The generated script metadata exposes
 * a Callable property with Godot's tool-button hint; clicking it in the
 * inspector invokes the annotated function on the edited script instance.
 *
 * @property text Button label shown in the inspector.
 * @property icon Optional editor icon name from Godot's `EditorIcons` theme.
 * @property name Optional engine-facing property name. Defaults to the
 *   function name converted from camelCase.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ToolButton(
    val text: String,
    val icon: String = "",
    val name: String = "",
)

/** Alias for [ToolButton], mirroring Godot C# `[ExportToolButton]`. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportToolButton(
    val text: String,
    val icon: String = "",
    val name: String = "",
)

/**
 * Exposes the class globally — i.e. it appears under "Node" in the
 * Add Node dialog even without being explicitly referenced. Matches
 * the GDScript `class_name` directive.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class GlobalClass

/** Alias for [GlobalClass], mirroring GDScript `class_name` intent. */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ClassName

/**
 * Marks a zero-arg, void Kotlin function as the handler for Godot's
 * `_ready` virtual. The function name on the Kotlin side is free —
 * the generator wires this annotation to the engine's `_ready` slot.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnReady

/** Alias for [OnReady]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Ready

/** Handler for Godot's `_enter_tree` virtual. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnEnterTree

/** Alias for [OnEnterTree]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class EnterTree

/** Handler for Godot's `_exit_tree` virtual. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnExitTree

/** Alias for [OnExitTree]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ExitTree

/**
 * Handler for Godot's `_process(delta: Double)` virtual.
 * The annotated function must accept a single `Double` parameter (the
 * frame delta in seconds).
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnProcess

/** Alias for [OnProcess]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Process

/**
 * Handler for Godot's `_physics_process(delta: Double)` virtual.
 * The annotated function must accept a single `Double` parameter.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnPhysicsProcess

/** Alias for [OnPhysicsProcess]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class PhysicsProcess

/**
 * Handler for Godot's `_input(event: InputEvent)` virtual. Receives every
 * input event the engine routes to this node before SceneTree dispatches
 * it for action handling. The annotated function must accept a single
 * `GodotObject` (the input event).
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnInput

/** Alias for [OnInput]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Input

/**
 * Handler for Godot's `_unhandled_input(event: InputEvent)` virtual. Fires
 * for events that no other UI/control consumed. Most gameplay input
 * handlers belong here, not in `_input`.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnUnhandledInput

/** Alias for [OnUnhandledInput]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class UnhandledInput

/**
 * Handler for Godot's `_shortcut_input(event: InputEvent)` virtual.
 * Fires for events that may match a `Shortcut` resource bound on this
 * node. Runs before `_unhandled_input`.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnShortcutInput

/** Alias for [OnShortcutInput]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ShortcutInput

/**
 * Handler for Godot's `_unhandled_key_input(event: InputEvent)` virtual.
 * Fires only for unhandled keyboard events; cheaper than
 * `_unhandled_input` when you only care about keyboard.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnUnhandledKeyInput

/** Alias for [OnUnhandledKeyInput]. */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class UnhandledKeyInput

/**
 * Overrides an arbitrary engine virtual method on the script's attach-to class
 * (or any of its ancestors), beyond the fixed lifecycle set.
 *
 * **The annotated function's name is the Godot virtual's engine name** — write
 * the underscored virtual directly, exactly as in GDScript: `fun _draw()`,
 * `fun _gui_input(event: InputEvent)`, `fun _get_minimum_size(): Vector2`. The
 * function's parameters and return type must match the engine virtual's
 * signature; the KSP processor validates this against a generated table derived
 * from `extension_api.json` and fails the build on a mismatch (unknown virtual
 * for the attach-to class, wrong arity, or a value returned from a `void` virtual
 * / vice versa).
 *
 * (The virtual name is taken from the function name rather than an annotation
 * argument because KSP2 over Kotlin/Native does not expose function-annotation
 * argument values; the function name is available on every target, and this also
 * matches GDScript's `func _draw()` convention.)
 *
 * The lifecycle annotations ([OnReady], [OnProcess], [OnInput], …) remain
 * convenience aliases for the most common virtuals; use `@OverrideVirtual` for
 * everything else (custom drawing via `_draw`, control input via `_gui_input`,
 * editor warnings via `_get_configuration_warnings`, drag-and-drop, …).
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OverrideVirtual

/**
 * Marks a Kotlin class as a Godot script that can be attached to an
 * existing node type (e.g. CharacterBody3D, Node3D) without registering
 * a new type in ClassDB.
 *
 * The KSP processor generates a `<ClassName>ScriptRegistrar` that creates
 * a [KanamaScript] resource backed by this class, with a factory that
 * constructs the Kotlin instance and wires property/method dispatch via the
 * [ScriptBridge] vtable.
 *
 * The annotated class must have a primary constructor accepting a single
 * `java.lang.foreign.MemorySegment` (the owning Godot node object).
 *
 * @property attachTo The Godot base class this script is compatible with
 *   (e.g. "CharacterBody3D"). Shown in the editor and returned from
 *   `KanamaScript._get_instance_base_type`.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ScriptClass(val attachTo: String = "Node")

/**
 * Marks a property on a [@ScriptClass][ScriptClass]-annotated class as an
 * inspector-visible property routed through the [ScriptBridge] `set_func` /
 * `get_func` ScriptInstance callbacks.
 *
 * @property name Optional engine-facing property name (snake_case). Defaults
 *   to the Kotlin property name converted from camelCase.
 * @property hint  PROPERTY_HINT_* constant. 0 = PROPERTY_HINT_NONE. Prefer [PropertyHint] constants
 *   over raw integers in user-facing code.
 * @property hintString  Hint string (e.g. "0,100,1" for PROPERTY_HINT_RANGE).
 * @property usage PROPERTY_USAGE_* flags. Defaults to [PropertyUsage.DEFAULT].
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ScriptProperty(
    val name: String = "",
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = PropertyUsage.DEFAULT,
)

/**
 * Starts an inspector export category before the annotated exported property.
 *
 * This mirrors Godot's category rows in the inspector. Because Kotlin
 * annotations cannot be standalone declarations, place this on the first
 * [ScriptProperty] or [Export] that should appear inside the category.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportCategory(val name: String)

/**
 * Starts an inspector export group before the annotated exported property.
 *
 * This mirrors GDScript's `@export_group("Name", "prefix_")`. Because Kotlin
 * annotations cannot be standalone declarations, place this on the first
 * [ScriptProperty] or [Export] that should appear inside the group.
 *
 * @property name Display name for the group. Use an empty name with an empty
 *   [prefix] to end the current group.
 * @property prefix Optional property-name prefix used by Godot's inspector
 *   grouping logic. When non-empty, Godot removes this prefix from displayed
 *   property names and ends the group when a following property does not match.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportGroup(val name: String, val prefix: String = "")

/**
 * Starts an inspector export subgroup before the annotated exported property.
 *
 * This mirrors GDScript's `@export_subgroup("Name", "prefix_")`. Because
 * Kotlin annotations cannot be standalone declarations, place this on the
 * first [ScriptProperty] or [Export] that should appear inside the subgroup.
 *
 * Subgroups require a parent [ExportGroup] to be active in Godot's inspector.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportSubgroup(val name: String, val prefix: String = "")

/**
 * Declares a Godot signal on a [RegisterClass]-annotated type.
 *
 * Place on a zero-body function whose parameter list describes the
 * signal's argument signature (names + types). The function body is
 * ignored by the processor — declaring it as `Unit` is conventional:
 *
 * ```
 * @Signal fun pinged(value: Long) = Unit
 * ```
 *
 * @property name Optional engine-facing signal name (snake_case).
 *   Defaults to the Kotlin function name converted from camelCase.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Signal(val name: String = "")

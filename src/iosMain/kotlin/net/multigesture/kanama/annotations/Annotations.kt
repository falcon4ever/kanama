package net.multigesture.kanama.annotations

// The iOS (Kotlin/Native) copy of the script annotations: the `annotations` module is JVM-only, so
// the K/N runtime carries the same canonical set itself (everything but the JVM-only
// `@RegisterClass`). The names and parameters equal those of
// annotations/src/main/kotlin/net/multigesture/kanama/annotations/ (scripts/check_hand_copies.py).

annotation class ScriptClass(val attachTo: String = "Node")

@Target(AnnotationTarget.CLASS) @Retention(AnnotationRetention.SOURCE) annotation class Tool

// Godot-side name for a registered function (public functions are registered automatically).
// Declared like the former iOS `RegisterFunction` (default retention), whose `name` argument the
// iOS KSP pass reads.
annotation class GodotName(val name: String)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportToolButton(val text: String, val icon: String = "", val name: String = "")

annotation class OnReady

annotation class OnEnterTree

annotation class OnExitTree

annotation class OnProcess

annotation class OnPhysicsProcess

annotation class OnInput

annotation class OnUnhandledInput

annotation class OnShortcutInput

annotation class OnUnhandledKeyInput

/**
 * Override an arbitrary engine virtual; the function name is the virtual name (e.g. `fun _draw()`).
 * iOS shadow of [net.multigesture.kanama.annotations.OverrideVirtual].
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OverrideVirtual

annotation class Rpc(
  val mode: Int = RpcMode.AUTHORITY,
  val callLocal: Boolean = false,
  val transferMode: Int = RpcTransferMode.RELIABLE,
  val channel: Int = 0,
)

object RpcMode {
  const val DISABLED = 0
  const val ANY_PEER = 1
  const val AUTHORITY = 2
}

object RpcTransferMode {
  const val UNRELIABLE = 0
  const val UNRELIABLE_ORDERED = 1
  const val RELIABLE = 2
}

annotation class Export(val name: String = "", val usage: Int = PropertyUsage.DEFAULT)

// The typed inspector-hint annotations (task 133 C), iOS shadows of
// annotations/src/main/kotlin/net/multigesture/kanama/annotations/ExportHints.kt. Default
// retention,
// like `Export`, so the iOS KSP pass reads their argument values.

annotation class ExportRange(
  val min: Double,
  val max: Double,
  val step: Double = Double.NaN,
  val orGreater: Boolean = false,
  val orLess: Boolean = false,
  val exp: Boolean = false,
  val radiansAsDegrees: Boolean = false,
  val degrees: Boolean = false,
  val preferSlider: Boolean = false,
  val hideControl: Boolean = false,
  val suffix: String = "",
)

annotation class ExportEnum(vararg val names: String)

annotation class ExportFlags(vararg val names: String)

annotation class ExportFlags2DRender

annotation class ExportFlags2DPhysics

annotation class ExportFlags2DNavigation

annotation class ExportFlags3DRender

annotation class ExportFlags3DPhysics

annotation class ExportFlags3DNavigation

annotation class ExportFlagsAvoidance

annotation class ExportFile(vararg val filters: String)

annotation class ExportFilePath(vararg val filters: String)

annotation class ExportDir

annotation class ExportGlobalFile(vararg val filters: String)

annotation class ExportGlobalDir

annotation class ExportMultiline(val monospace: Boolean = false, val noWrap: Boolean = false)

annotation class ExportColorNoAlpha

annotation class ExportPlaceholder(val placeholder: String)

annotation class ExportExpEasing(
  val attenuation: Boolean = false,
  val positiveOnly: Boolean = false,
)

annotation class ExportNodePath(vararg val types: String)

annotation class ExportStorage

annotation class ExportCustom(
  val hint: Int,
  val hintString: String = "",
  val usage: Int = PropertyUsage.DEFAULT,
)

annotation class ExportGroup(val name: String, val prefix: String = "")

annotation class ExportSubgroup(val name: String, val prefix: String = "")

annotation class ExportCategory(val name: String)

annotation class Signal(val name: String = "")

// iOS shadow of the desktop PropertyHint constants (every Godot 4.7 PROPERTY_HINT_*; for
// @ExportCustom).
object PropertyHint {
  const val NONE = 0
  const val RANGE = 1
  const val ENUM = 2
  const val ENUM_SUGGESTION = 3
  const val EXP_EASING = 4
  const val LINK = 5
  const val FLAGS = 6
  const val LAYERS_2D_RENDER = 7
  const val LAYERS_2D_PHYSICS = 8
  const val LAYERS_2D_NAVIGATION = 9
  const val LAYERS_3D_RENDER = 10
  const val LAYERS_3D_PHYSICS = 11
  const val LAYERS_3D_NAVIGATION = 12
  const val FILE = 13
  const val DIR = 14
  const val GLOBAL_FILE = 15
  const val GLOBAL_DIR = 16
  const val RESOURCE_TYPE = 17
  const val MULTILINE_TEXT = 18
  const val EXPRESSION = 19
  const val PLACEHOLDER_TEXT = 20
  const val COLOR_NO_ALPHA = 21
  const val OBJECT_ID = 22
  const val TYPE_STRING = 23
  const val NODE_PATH_TO_EDITED_NODE = 24
  const val OBJECT_TOO_BIG = 25
  const val NODE_PATH_VALID_TYPES = 26
  const val SAVE_FILE = 27
  const val GLOBAL_SAVE_FILE = 28
  const val INT_IS_OBJECTID = 29
  const val INT_IS_POINTER = 30
  const val ARRAY_TYPE = 31
  const val LOCALE_ID = 32
  const val LOCALIZABLE_STRING = 33
  const val NODE_TYPE = 34
  const val HIDE_QUATERNION_EDIT = 35
  const val PASSWORD = 36
  const val LAYERS_AVOIDANCE = 37
  const val DICTIONARY_TYPE = 38
  const val TOOL_BUTTON = 39
  const val ONESHOT = 40
  const val GROUP_ENABLE = 42
  const val INPUT_NAME = 43
  const val FILE_PATH = 44
}

// iOS shadow of the desktop PropertyUsage flags (used in @Export usage=...).
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

// iOS shadow of @GlobalClass (GDScript class_name intent). SOURCE-retained; consumed by KSP.
@Target(AnnotationTarget.CLASS) @Retention(AnnotationRetention.SOURCE) annotation class GlobalClass

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

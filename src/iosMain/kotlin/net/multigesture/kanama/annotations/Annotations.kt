package net.multigesture.kanama.annotations

// The iOS (Kotlin/Native) copy of the script annotations: the `annotations` module is JVM-only, so
// the K/N runtime carries the same canonical set itself (everything but the JVM-only
// `@RegisterClass`). Keep the names and parameters equal to
// annotations/src/main/kotlin/net/multigesture/kanama/annotations/Annotations.kt.

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

annotation class Export(
  val name: String = "",
  val hint: Int = 0,
  val hintString: String = "",
  val usage: Int = PropertyUsage.DEFAULT,
)

annotation class ExportGroup(val name: String, val prefix: String = "")

annotation class ExportSubgroup(val name: String, val prefix: String = "")

annotation class ExportCategory(val name: String)

annotation class Signal(val name: String = "")

// iOS shadow of the desktop PropertyHint constants (used in @Export hint=...).
object PropertyHint {
  const val NONE = 0
  const val RANGE = 1
  const val ENUM = 2
  const val ENUM_SUGGESTION = 3
  const val EXP_EASING = 4
  const val FLAGS = 6
  const val FILE = 13
  const val DIR = 14
  const val GLOBAL_FILE = 15
  const val GLOBAL_DIR = 16
  const val RESOURCE_TYPE = 17
  const val MULTILINE_TEXT = 18
  const val PLACEHOLDER_TEXT = 20
  const val COLOR_NO_ALPHA = 21
  const val TYPE_STRING = 23
  const val NODE_TYPE = 34
  const val DICTIONARY_TYPE = 38
  const val TOOL_BUTTON = 39
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

package net.multigesture.kanama.annotations

annotation class ScriptClass(val attachTo: String = "Node")

annotation class RegisterFunction(val name: String = "")

annotation class Method(val name: String = "")

annotation class OnReady

annotation class Ready

annotation class OnEnterTree

annotation class EnterTree

annotation class OnExitTree

annotation class ExitTree

annotation class OnProcess

annotation class Process

annotation class OnPhysicsProcess

annotation class PhysicsProcess

annotation class OnInput

annotation class Input

annotation class OnUnhandledInput

annotation class UnhandledInput

annotation class OnShortcutInput

annotation class ShortcutInput

annotation class OnUnhandledKeyInput

annotation class UnhandledKeyInput

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

annotation class ScriptProperty(
  val name: String = "",
  val hint: Int = 0,
  val hintString: String = "",
  val usage: Int = 6,
)

annotation class Export(
  val name: String = "",
  val hint: Int = 0,
  val hintString: String = "",
  val usage: Int = 6,
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
  const val TOOL_BUTTON = 39
}

// iOS shadow of @GlobalClass (GDScript class_name intent). SOURCE-retained; consumed by KSP.
@Target(AnnotationTarget.CLASS) @Retention(AnnotationRetention.SOURCE) annotation class GlobalClass

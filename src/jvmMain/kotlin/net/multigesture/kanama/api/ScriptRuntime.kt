package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineScope
import net.multigesture.kanama.binding.ScriptBridge

/** Desktop/Android actual of the script-authoring seam (`ScriptRuntime.expect.kt`, task 133). */
internal actual object ScriptRuntime {
  actual fun scriptInstanceOf(owner: GodotObject): Any? =
    ScriptBridge.kotlinObjectForOwner(owner.handle.segment)

  actual fun loadResource(path: String): Resource? = ResourceLoader.load(path)

  actual fun isEditorHint(): Boolean = Engine.isEditorHint()

  actual fun newScriptScope(): CoroutineScope = KanamaScope()

  actual fun platformClasses(): GodotClassTable = PlatformGodotClasses
}

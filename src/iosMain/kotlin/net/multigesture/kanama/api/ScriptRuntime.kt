package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.multigesture.kanama.binding.runtime.IosScriptErrors

// KANAMA-IOS-HANDWRITTEN: [platform] iOS actual of the script-authoring seam (ScriptRuntime.expect.kt, task 133): the runtime's script-instance table, the main dispatcher and the script-error report.
internal actual object ScriptRuntime {
  actual fun scriptInstanceOf(owner: GodotObject): Any? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(owner.handle.segment.address())

  actual fun loadResource(path: String): Resource? = ResourceLoader.load(path)

  actual fun isEditorHint(): Boolean = Engine.isEditorHint()

  actual fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

  actual fun reportCoroutineFailure(throwable: Throwable, where: String) {
    IosScriptErrors.report(throwable, where)
  }

  actual fun platformClasses(): GodotClassTable = PlatformGodotClasses
}

package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineScope

// KANAMA-IOS-HANDWRITTEN: [platform] iOS actual of the script-authoring seam (ScriptRuntime.expect.kt, task 133): the runtime's script-instance table, the typed-loader ResourceLoader and Engine singletons.
internal actual object ScriptRuntime {
  actual fun scriptInstanceOf(owner: GodotObject): Any? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(owner.handle.segment.address())

  actual fun loadResource(path: String): Resource? = ResourceLoader.load(path)

  actual fun isEditorHint(): Boolean = Engine.isEditorHint()

  actual fun newScriptScope(): CoroutineScope = KanamaScope()

  actual fun platformClasses(): GodotClassTable = PlatformGodotClasses
}

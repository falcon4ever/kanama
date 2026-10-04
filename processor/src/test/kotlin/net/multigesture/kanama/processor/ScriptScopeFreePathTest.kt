package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 133 item 5: a script's coroutines belong to `KanamaScript.scriptScope`, which the runtime's
 * free path cancels (`ScriptBridge.siFree`, the iOS `freeScriptInstance`). The generated registrar
 * no longer cancels a `KanamaCoroutineOwner` scope on `_exit_tree`: that interface is gone, and a
 * GDScript coroutine survives leaving the tree too.
 */
class ScriptScopeFreePathTest {
  @Test
  fun exitTreeDispatchNoLongerCancelsAScope() {
    val model =
      ScriptModel(
        simpleName = "ExitFixture",
        fqName = "net.multigesture.kanama.test.ExitFixture",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = emptyList(),
        toolButtons = emptyList(),
        virtuals = listOf(VirtualModel("_exit_tree", "exitTree", "exitTree")),
        methods = emptyList(),
        signals = emptyList(),
      )
    val source = ScriptCodeEmitter(model, "ExitFixtureScriptRegistrar").emit()
    assertTrue(source.contains("kt.exitTree()"), "the _exit_tree virtual still dispatches")
    assertFalse(source.contains("KanamaCoroutineOwner"), "no reference to the removed interface")
    assertFalse(source.contains("cancelKanamaScope"), "no exit-tree scope cancel")
  }
}

package web3d

import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only teardown for the web3d render smoke (never gameplay).
 *
 * The browser driver calls [smokeTeardown] (its only @RegisterFunction, method#1) to free the
 * scene root; every node then exits the tree and releases its Godot handles, so the live-handle
 * count drains to zero — the smoke wrapper's `handles.liveAfterTeardown === 0` assertion.
 */
@ScriptClass(attachTo = "Node")
class SmokeQuit(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @RegisterFunction("smoke_teardown")
  fun smokeTeardown() {
    val root = self.getParent() ?: error("SmokeQuit has no parent to tear down")
    Node(root.handle).queueFree()
  }
}

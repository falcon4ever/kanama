package web3d

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 131 item 14): a script that throws at scene load; see `ErrorProbe` and the
 * web3d driver's `scriptErrorsAreContained` row. `@OnReady` throws.
 */
@ScriptClass(attachTo = "Node")
class ThrowingReady(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @OnReady
  fun ready() {
    throw IllegalStateException("web3d deliberate script error (ready)")
  }
}

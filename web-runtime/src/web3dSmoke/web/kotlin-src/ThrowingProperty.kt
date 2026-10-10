package web3d

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 131 item 14): a script that throws at scene load; see `ErrorProbe` and the
 * web3d driver's `scriptErrorsAreContained` row. The scene pushes `gate = 5` through a setter that throws.
 */
@ScriptClass(attachTo = "Node")
class ThrowingProperty(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export
  var gate: Long = 0L
    set(value) {
      if (value != 0L) throw IllegalStateException("web3d deliberate script error (property)")
      field = value
    }
}

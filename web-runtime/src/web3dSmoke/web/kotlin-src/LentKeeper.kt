package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (LentProbe's escape row): the script that RECEIVES a dispatch argument and stores it
 * where another script reads it (a companion object), then is freed. The stored value must outlive
 * the script that received it, as it does in GDScript and on desktop.
 */
@ScriptClass(attachTo = "Node")
class LentKeeper(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  /** Connects this script (the receiver) to LentTwin's `escape` signal. 1 when it connected. */
  fun armEscape(value: Long): Long {
    val twin = self.requireAs("../LentTwin", ::Node)
    val connection = twin.signal("escape").connectObject(self) { kept = it }
    return if (connection != null) 1L else 0L
  }

  companion object {
    /** The argument this script's handler stored. */
    var kept: GodotObject? = null
  }
}

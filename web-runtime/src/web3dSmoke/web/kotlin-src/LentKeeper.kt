package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceLoader

/**
 * Harness-only (LentProbe's escape row): the script that RECEIVES a dispatch argument and stores it
 * where another script reads it (a companion object), then is freed. The stored value must outlive
 * the script that received it, as it does in GDScript and on desktop. OwnProbe's O5 / O6 rows make
 * one at run time: it stores a RefCounted it received ([armOwn]) or loaded ([keepLoaded]) in the
 * companion, and is freed; the value must stay valid while Kotlin holds it.
 */
@ScriptClass(attachTo = "Node")
class LentKeeper(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  /** Connects this script (the receiver) to LentTwin's `escape` signal. 1 when it connected. */
  fun armEscape(value: Long): Long {
    val twin = self.requireAs("../LentTwin", ::Node)
    val connection = twin.signal("escape").connectObject(self) { kept = it }
    return if (connection != null) 1L else 0L
  }

  /** O5: connects this script to OwnTwin's `own_keep`; the handler stores the argument. */
  fun armOwn(value: Long): Long {
    val twin = self.requireAs("../OwnTwin", ::Node)
    val connection = twin.signal("own_keep").connectObject(self) { ownKept = it }
    return if (connection != null) 1L else 0L
  }

  /** O6: loads a resource (owned by this script's calls) into the companion. 1 when it loaded. */
  fun keepLoaded(value: Long): Long {
    ownLoaded = ResourceLoader.load(OWN_LOADED_PATH)
    return if (ownLoaded != null) 1L else 0L
  }

  companion object {
    /** The argument this script's handler stored. */
    var kept: GodotObject? = null

    /** O5: the RefCounted argument a run-time keeper stored. */
    var ownKept: GodotObject? = null

    /** O6: the resource a run-time keeper loaded. */
    var ownLoaded: Resource? = null

    const val OWN_LOADED_PATH = "res://assets/beep.wav"
  }
}

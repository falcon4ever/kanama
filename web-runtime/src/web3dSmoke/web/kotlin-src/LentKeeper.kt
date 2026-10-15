package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.Sprite2D

/**
 * Harness-only (LentProbe's escape row): the script that RECEIVES a dispatch argument and stores it
 * where another script reads it (a companion object), then is freed. The stored value must outlive
 * the script that received it, as it does in GDScript and on desktop. OwnProbe's O5 / O6 rows make
 * one at run time: it stores a RefCounted it received ([armOwn]) or loaded ([keepLoaded]) in the
 * companion, and is freed; the value must stay valid while Kotlin holds it. O11 ([keepNodes]): a node
 * it looked up and one a top-level initializer made while it ran stay valid after it is freed.
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

  /**
   * O11: a node looked up, and one made by a top-level object's initializer (it runs on first
   * access, inside this script's call), both kept in the companion. 1 when both are there.
   */
  fun keepNodes(value: Long): Long {
    nodeKept = self.getNodeOrNull("../OwnTwin/OwnKeptNode")
    madeKept = OwnMade.sprite
    self.requireAs("../OwnTwin", ::Node).addChild(OwnMade.sprite)
    return if (nodeKept != null) 1L else 0L
  }

  companion object {
    /** The argument this script's handler stored. */
    var kept: GodotObject? = null

    /** O5: the RefCounted argument a run-time keeper stored. */
    var ownKept: GodotObject? = null

    /** O6: the resource a run-time keeper loaded. */
    var ownLoaded: Resource? = null

    /** O11: the node a run-time keeper looked up. */
    var nodeKept: Node? = null

    /** O11: the node [OwnMade]'s initializer made while a run-time keeper ran. */
    var madeKept: Sprite2D? = null

    const val OWN_LOADED_PATH = "res://assets/beep.wav"
  }
}

/** O11: an engine object made by a top-level initializer (Kotlin runs it on first access). */
object OwnMade {
  val sprite: Sprite2D = Sprite2D.create().also { it.name = "OwnMadeSprite" }
}

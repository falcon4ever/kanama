package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 138 item 3): holds a node the ENGINE frees under it -- `FreedChild` goes when
 * its parent `FreedParent` is freed, and nothing in Kotlin ever freed the child itself. Using the
 * held node afterwards must throw the freed-instance error (an `IllegalStateException`, as on
 * desktop), not fail in the bridge with "callback did not publish a string result".
 */
@ScriptClass(attachTo = "Node")
class FreedHolder(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var held: Node? = null

  fun freedLookup(value: Long): Long {
    held = self.requireAs("../FreedParent/FreedChild", ::Node)
    return if (held?.getName().toString() == "FreedChild") 1L else 0L
  }

  /**
   * 1 while the node answers; 2 when using it throws the freed-instance IllegalStateException; 3
   * when it throws anything else (the bridge's own "did not publish" failure, before the fix).
   */
  fun freedName(value: Long): Long =
    try {
      if (held?.getName().toString() == "FreedChild") 1L else 0L
    } catch (e: IllegalStateException) {
      if (e.message.orEmpty().contains("previously freed instance")) 2L else 3L
    } catch (e: Throwable) {
      3L
    }

  /** 1 when this script's own node still answers after the held one was freed. */
  fun freedSelf(value: Long): Long = if (self.getName().toString() == "FreedHolder") 1L else 0L
}

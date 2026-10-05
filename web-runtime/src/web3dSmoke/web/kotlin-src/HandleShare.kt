package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (shared-node-handle fix): two instances (ShareA, ShareB) look up the same plain
 * node (`ShareTarget`) and keep calling it. Main's `share_probe` frees ShareA first, then checks
 * that ShareB's handle for the node, and a third script's lookup of it, still work.
 */
@ScriptClass(attachTo = "Node")
class HandleShare(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var target: Node? = null

  fun shareLookup(value: Long): Long {
    target = self.requireAs("../ShareTarget", ::Node)
    return 1L
  }

  /**
   * 1 when the looked-up node answers with its own name; 2 when using it throws the freed-handle
   * IllegalStateException (the node was freed under this script), which a script may catch.
   */
  fun shareName(value: Long): Long =
    try {
      if (target?.getName().toString() == "ShareTarget") 1L else 0L
    } catch (e: IllegalStateException) {
      2L
    }

  /** 1 when this script's own node answers (the freed-node case must not break its owners). */
  fun shareSelf(value: Long): Long = if (self.getName().toString().startsWith("Share")) 1L else 0L
}

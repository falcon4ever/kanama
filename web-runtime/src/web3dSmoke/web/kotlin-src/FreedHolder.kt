package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Control
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

  private var heldControl: Control? = null

  fun freedLookup(value: Long): Long {
    held = self.requireAs("../FreedParent/FreedChild", ::Node)
    heldControl = self.requireAs("../FreedParent/FreedControl", ::Control)
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

  /**
   * The object-returning shapes on the freed node: 2 when `block` throws the freed-instance
   * IllegalStateException, 1 when it answers (a bridge that hid the freed node behind a null), 3
   * when it throws anything else (the bridge's own "published an invalid handle" failure).
   */
  private fun freedKind(block: () -> Any?): Long =
    try {
      block()
      1L
    } catch (e: IllegalStateException) {
      if (e.message.orEmpty().contains("previously freed instance")) 2L else 3L
    } catch (e: Throwable) {
      3L
    }

  fun freedGetParent(value: Long): Long = freedKind { held?.getParent() }

  fun freedGetNodeOrNull(value: Long): Long = freedKind { held?.getNodeOrNull("Missing") }

  fun freedGetChild(value: Long): Long = freedKind { held?.getChild(0) }

  /** `emitSignal` on the freed node (the immediate signal shape). */
  fun freedEmitSignal(value: Long): Long = freedKind { held?.emitSignal("renamed") }

  /** `Control.position` read on a freed Control (the immediate Vector2 shape). */
  fun freedControlPosition(value: Long): Long = freedKind { heldControl?.position }

  /**
   * A setter on the freed node (a queued command). It does not throw at the call on Web; the
   * freed-instance error is reported as a script error when the queue is applied and the page
   * carries on. 1 when the call came back.
   */
  fun freedSetter(value: Long): Long {
    held?.setProcessMode(Node.ProcessMode.DISABLED)
    return 1L
  }

  /** 1 when this script's own node still answers after the held one was freed. */
  fun freedSelf(value: Long): Long = if (self.getName().toString() == "FreedHolder") 1L else 0L
}

package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Camera3D
import net.multigesture.kanama.api.Control
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.web.WebExperimentalGenericCall

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
   * A setter on the freed node (a queued command): the sweep released the node's handle as freed,
   * so the call throws desktop's freed-instance IllegalStateException at once (2), as desktop's
   * setter does.
   */
  fun freedSetter(value: Long): Long = freedKind { held?.setProcessMode(Node.ProcessMode.DISABLED) }

  private var batchLive: Camera3D? = null

  /**
   * Batches with a command on a node the engine freed this frame (Kotlin learns it at the next
   * sweep, so the setters queue): the freed one is skipped and reported, the live one after it must
   * still be applied. First batch: the node's `free` (a queued generic call), a setter on it, then a
   * setter on a live node, read back (the read flushes). Second batch: a setter on the freed node
   * then `queueFree` on the live node, which must release the live node's bridge slot. 1 when the
   * first batch's live setter took effect.
   */
  fun freedBatch(value: Long): Long {
    val doomed = Camera3D.create()
    val live = Camera3D.create()
    WebExperimentalGenericCall.queueVoidCall(doomed, "free")
    doomed.setProcessMode(Node.ProcessMode.DISABLED)
    live.setProcessMode(Node.ProcessMode.DISABLED)
    val applied = if (live.getProcessMode() == Node.ProcessMode.DISABLED) 1L else 0L
    doomed.setProcessMode(Node.ProcessMode.DISABLED)
    live.queueFree()
    batchLive = live
    return applied
  }

  /** 1 once the live node of [freedBatch] is gone from the engine. */
  fun freedBatchGone(value: Long): Long = if (GD.isInstanceValid(batchLive)) 0L else 1L

  private var heldScript: Node? = null

  /** Task 138 item 22: hold the script-backed FreedScriptTarget (its handle is its script's). */
  fun freedScriptLookup(value: Long): Long {
    heldScript = self.requireAs("../FreedScriptParent/FreedScriptTarget", ::Node)
    return if (heldScript?.getName().toString() == "FreedScriptTarget") 1L else 0L
  }

  /**
   * Task 138 item 22, the late case: ONE batch frees FreedScriptParent (a queued `free`, so the
   * script-backed child goes while the batch applies) and then sets a property on the held script
   * node. That setter is skipped and reported as the freed-instance error instead of failing the
   * batch. 1 when the call came back and this script still answers (the read flushes the batch).
   */
  fun freedScriptLate(value: Long): Long {
    val parent = self.requireAs("../FreedScriptParent", ::Node)
    WebExperimentalGenericCall.queueVoidCall(parent, "free")
    heldScript?.setProcessMode(Node.ProcessMode.DISABLED)
    return if (self.getName().toString() == "FreedHolder") 1L else 0L
  }

  /**
   * Task 138 item 22, the synchronous case: a call on the freed script-backed node throws desktop's
   * freed-instance IllegalStateException (2), not "Stale Kanama Web browser handle" (3).
   */
  fun freedScriptCall(value: Long): Long = freedKind { heldScript?.getName() }

  /** 1 when this script's own node still answers after the held one was freed. */
  fun freedSelf(value: Long): Long = if (self.getName().toString() == "FreedHolder") 1L else 0L
}

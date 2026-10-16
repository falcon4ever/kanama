package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * A failed script constructor rolls the command queue back to where it was when the constructor
 * started (`kanamaWebConstruct`). With no bridge on the page a flush applies every queued command,
 * so the count it returns is the queue's length.
 */
class WebCommandBufferTest {
  @Test
  fun rollbackDropsOnlyTheCommandsQueuedSinceTheCheckpoint() {
    val buffer = WebCommandBuffer(8)
    buffer.appendScalarMutation(1, 1)
    val checkpoint = buffer.checkpoint()
    buffer.appendScalarMutation(2, 2)
    buffer.rollbackTo(checkpoint)
    assertEquals(1, buffer.flush())
  }

  @Test
  fun rollbackAfterAFlushNeverRewindsOverAppliedCommands() {
    // The constructor applied the queue (a close(), an immediate call), queued one more and threw:
    // the command queued before the checkpoint was applied once, and the one queued since is the
    // failed constructor's; nothing is left to apply.
    val buffer = WebCommandBuffer(8)
    buffer.appendScalarMutation(1, 1)
    val checkpoint = buffer.checkpoint()
    assertEquals(1, buffer.flush())
    buffer.appendScalarMutation(2, 2)
    buffer.rollbackTo(checkpoint)
    assertEquals(0, buffer.flush())
  }
}

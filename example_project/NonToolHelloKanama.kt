package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterClass

/**
 * Non-@Tool sister of [HelloKanama]. Locks in editor/runtime gating for
 * project-provided @RegisterClass classes.
 */
@RegisterClass(parentClassName = "Node")
class NonToolHelloKanama(val godotObject: MemorySegment) {

  @OnReady
  fun ready() {
    System.err.println("[kanama:kt] NonToolHelloKanama._ready fired")
  }
}

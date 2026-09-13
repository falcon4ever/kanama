package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterClass
import net.multigesture.kanama.api.GodotHandle

/**
 * Non-@Tool sister of [HelloKanama]. Locks in editor/runtime gating for
 * project-provided @RegisterClass classes.
 */
@RegisterClass(parentClassName = "Node")
class NonToolHelloKanama(val godotObject: GodotHandle) {

  @OnReady
  fun ready() {
    System.err.println("[kanama:kt] NonToolHelloKanama._ready fired")
  }
}

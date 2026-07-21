package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnExitTree
import net.multigesture.kanama.annotations.OnPhysicsProcess
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.PropertyHint
import net.multigesture.kanama.annotations.RegisterClass
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.RegisterProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.generated.HelloKanamaSignals

/**
 * Canonical @RegisterClass smoke class for the example project. It stays out of the framework
 * runtime jar so real games only register their own classes.
 */
@RegisterClass(parentClassName = "Node")
@Tool
class HelloKanama(val godotObject: MemorySegment) {

  private var pingCount: Long = 0

  @RegisterProperty(hint = PropertyHint.RANGE, hintString = "0,100,1") var counter: Long = 0

  @RegisterProperty var scale: Double = 1.0

  @RegisterProperty var label: String = "hello"

  @RegisterFunction
  fun greet(name: String): String {
    val msg = "Hello, $name! (ping=$pingCount)"
    System.err.println("[kanama:kt] HelloKanama.greet(\"$name\") -> \"$msg\"")
    return msg
  }

  @RegisterFunction
  fun isActive(): Boolean {
    val result = counter > 0
    System.err.println(
      "[kanama:kt] HelloKanama.isActive() -> $result obj=0x${godotObject.address().toString(16)}"
    )
    return result
  }

  @RegisterFunction
  fun ping(): Long {
    pingCount += 1
    System.err.println(
      "[kanama:kt] HelloKanama.ping() -> $pingCount obj=0x${godotObject.address().toString(16)}"
    )
    HelloKanamaSignals.pinged(this, pingCount)
    return pingCount
  }

  @OnReady
  fun ready() {
    System.err.println(
      "[kanama:kt] HelloKanama._ready() obj=0x${godotObject.address().toString(16)}"
    )
  }

  @OnEnterTree
  fun enterTree() {
    System.err.println(
      "[kanama:kt] HelloKanama._enter_tree() obj=0x${godotObject.address().toString(16)}"
    )
  }

  @OnExitTree
  fun exitTree() {
    System.err.println(
      "[kanama:kt] HelloKanama._exit_tree() obj=0x${godotObject.address().toString(16)}"
    )
  }

  @OnProcess
  fun process(delta: Double) {
    // Intentionally quiet: per-frame logs flood the editor output.
  }

  @OnPhysicsProcess
  fun physicsProcess(delta: Double) {
    // Intentionally quiet: per-frame logs flood the editor output.
  }

  @Signal fun pinged(value: Long) = Unit
}

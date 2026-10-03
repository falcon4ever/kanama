package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnExitTree
import net.multigesture.kanama.annotations.OnPhysicsProcess
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.PropertyHint
import net.multigesture.kanama.annotations.RegisterClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.generated.HelloKanamaSignals

/**
 * Canonical @RegisterClass smoke class for the example project. It stays out of the framework
 * runtime jar so real games only register their own classes.
 */
@RegisterClass(parentClassName = "Node")
@Tool
class HelloKanama(val godotObject: GodotHandle) {

  private var pingCount: Long = 0

  @Export(hint = PropertyHint.RANGE, hintString = "0,100,1") var counter: Long = 0

  @Export var scale: Double = 1.0

  @Export var label: String = "hello"

  fun greet(name: String): String {
    val msg = "Hello, $name! (ping=$pingCount)"
    System.err.println("[kanama:kt] HelloKanama.greet(\"$name\") -> \"$msg\"")
    return msg
  }

  fun isActive(): Boolean {
    val result = counter > 0
    System.err.println(
      "[kanama:kt] HelloKanama.isActive() -> $result obj=0x${godotObject.segment.address().toString(16)}"
    )
    return result
  }

  fun ping(): Long {
    pingCount += 1
    System.err.println(
      "[kanama:kt] HelloKanama.ping() -> $pingCount obj=0x${godotObject.segment.address().toString(16)}"
    )
    HelloKanamaSignals.pinged(this, pingCount)
    return pingCount
  }

  // task 128 B probe: an object return from a @RegisterClass method, through both the call_
  // (varcall)
  // and ptrcall_ upcalls. Before 128 B the registrar wrote the GodotHandle itself where the raw
  // address goes, so this shape did not compile.
  fun selfObject(): GodotObject = GodotObject(godotObject)

  // task 98 smoke probe: a registered function on a @RegisterClass dispatches through the generated
  // call_/ptrcall_ upcall stubs, which have no bespoke catch. Before structural containment in
  // Upcalls.stub this exception escaped the FFM upcall and aborted the JVM (and Godot with it).
  fun smokeThrow(): Long {
    throw IllegalStateException("kanama smoke: deliberate upcall failure")
  }

  @OnReady
  fun ready() {
    System.err.println(
      "[kanama:kt] HelloKanama._ready() obj=0x${godotObject.segment.address().toString(16)}"
    )
  }

  @OnEnterTree
  fun enterTree() {
    System.err.println(
      "[kanama:kt] HelloKanama._enter_tree() obj=0x${godotObject.segment.address().toString(16)}"
    )
  }

  @OnExitTree
  fun exitTree() {
    System.err.println(
      "[kanama:kt] HelloKanama._exit_tree() obj=0x${godotObject.segment.address().toString(16)}"
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

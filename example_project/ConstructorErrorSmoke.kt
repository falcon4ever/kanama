package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * A script whose constructor throws, attached by `constructor_error_driver.gd`
 * (`constructor_error_smoke.tscn`, run by `scripts/runtime_smoke.sh`). The attach must report a
 * script error naming this file and line and leave the node without a script instance, as a
 * GDScript whose instance cannot be built does; before the fix Godot read the unwritten
 * `_instance_create` return as a pointer and crashed with signal 11.
 */
@ScriptClass(attachTo = "Node")
class ConstructorErrorSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  val budget: Long = fail()

  fun probe(): Long = budget

  private fun fail(): Long {
    System.err.println("[kanama:kt] ConstructorErrorSmoke constructor: throwing")
    throw IllegalStateException("kanama smoke: deliberate constructor failure")
  }
}

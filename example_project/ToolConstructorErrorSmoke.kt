package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * The @Tool twin of [ConstructorErrorSmoke], opened in the editor by `scripts/tool_smoke.sh`
 * (`constructor_error_tool_smoke.tscn`): its constructor throws in the editor. The node must keep
 * the script and its `rounds` value, so saving the scene keeps both, as with a GDScript `@tool`
 * whose initializer fails at run time.
 */
@Tool
@ScriptClass(attachTo = "Node")
class ToolConstructorErrorSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var rounds: Long = 3

  val budget: Long = fail()

  private fun fail(): Long {
    System.err.println("[kanama:kt] ToolConstructorErrorSmoke constructor: throwing")
    throw IllegalStateException("kanama smoke: deliberate tool constructor failure")
  }
}

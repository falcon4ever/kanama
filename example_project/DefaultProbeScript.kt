package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.types.NodePath

@ScriptClass(attachTo = "Node")
class DefaultProbeScript(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private val node = self

  @Export var amount: Long = 250

  @Export var target: NodePath = NodePath("../SceneTarget3D")

  // Narrow scalars: the Variant slot stays 64-bit, the registrar widens on
  // get and narrows on set (NarrowScalar in the processor).
  @Export var narrowRatio: Float = 0.5f

  @Export var narrowCount: Int = 42
}

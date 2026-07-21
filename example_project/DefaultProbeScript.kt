package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.types.NodePath

@ScriptClass(attachTo = "Node")
class DefaultProbeScript(godotObject: MemorySegment) : KanamaScript<Node>(godotObject, ::Node) {
  private val node = self

  @ScriptProperty var amount: Long = 250

  @ScriptProperty var target: NodePath = NodePath("../SceneTarget3D")

  // Narrow scalars: the Variant slot stays 64-bit, the registrar widens on
  // get and narrows on set (NarrowScalar in the processor).
  @ScriptProperty var narrowRatio: Float = 0.5f

  @ScriptProperty var narrowCount: Int = 42
}

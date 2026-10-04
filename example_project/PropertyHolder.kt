package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Material
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.PackedScene

// Task 132 review fixture (property_lifetime_smoke.tscn): a node script with a resource export and
// Node-typed exports, set through the engine.
@ScriptClass(attachTo = "Node")
class PropertyHolder(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var res: PackedScene? = null

  @Export var node: Node? = null

  @Export var nodes: List<Node> = emptyList()

  @Export var items: List<Material> = emptyList()
}

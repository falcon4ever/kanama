package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.types.Vector2i

enum class MapFixtureTier {
    LOW,
    HIGH,
}

// Map @Export shapes the iOS emitter delivers (typed scalar / value-type / enum keys and values,
// a nullable value, a MutableMap, and the plain String-keyed Map), so the generated
// setPropertyDictionary / getProperty branches compile on every mobile PR.
@ScriptClass(attachTo = "Node")
class MapExportFixture(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    @Export
    var names: Map<Int, String> = emptyMap()

    @Export
    var cells: Map<Vector2i, Long> = emptyMap()

    @Export
    var tiers: MutableMap<MapFixtureTier, Double?> = mutableMapOf()

    @Export
    var byName: Map<String, MapFixtureTier> = emptyMap()

    @Export
    var meta: Map<String, Any?> = emptyMap()
}

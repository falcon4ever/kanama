package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Resource

// Task 132 fixture: a KanamaScript resource holding a list of KanamaScript resources, the shape of
// the City-Builder demo's DataMap.structures (property_retain_smoke.tscn).
@ScriptClass(attachTo = "Resource")
@GlobalClass
class RetainingOwnerResource(godotObject: GodotHandle) :
  KanamaScript<Resource>(godotObject, Resource::fromHandle) {
  @Export var items: List<RetainedItemResource> = emptyList()
}

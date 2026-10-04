package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Resource

// Task 132 fixture: an element of RetainingOwnerResource.items (property_retain_smoke.tscn), the
// shape of the City-Builder demo's DataStructure.
@ScriptClass(attachTo = "Resource")
@GlobalClass
class RetainedItemResource(godotObject: GodotHandle) :
  KanamaScript<Resource>(godotObject, Resource::fromHandle) {
  @Export var tag: Long = 0
}

package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Resource

// Task 132 fixture: a KanamaScript resource, the shape of the City-Builder demo's DataMap.
// script_owner_smoke.tscn keeps only this script object of a loaded resource.
@ScriptClass(attachTo = "Resource")
@GlobalClass
class OwnerLinkResource(godotObject: GodotHandle) :
  KanamaScript<Resource>(godotObject, Resource::fromHandle) {
  @Export var cash: Long = 10
}

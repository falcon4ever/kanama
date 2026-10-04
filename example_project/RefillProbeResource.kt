package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Resource

// Review probe fixture: counts constructions of its script object.
@ScriptClass(attachTo = "Resource")
@GlobalClass
class RefillProbeResource(godotObject: GodotHandle) :
  KanamaScript<Resource>(godotObject, Resource::fromHandle) {
  @ScriptProperty var cash: Long = 10

  init {
    constructions += 1
  }

  companion object {
    @JvmStatic var constructions = 0
  }
}

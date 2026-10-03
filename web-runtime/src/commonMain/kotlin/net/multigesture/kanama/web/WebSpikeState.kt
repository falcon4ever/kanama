package net.multigesture.kanama.web

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle

/** Resource attachment used by the Phase 0 export-staging proof. */
@ScriptClass(attachTo = "Resource")
class WebSpikeState(objectId: GodotHandle) : KanamaWebScript(objectId) {
  @Export var label: String = "Kotlin/Wasm resource"
}

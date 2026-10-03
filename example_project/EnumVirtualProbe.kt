package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.TextServer

/**
 * Task 128 B probe: an engine virtual with a Godot-enum PARAMETER takes the typed value class.
 * `TextServerExtension._has_feature(feature: enum::TextServer.Feature)` — declared with `Long`, the
 * processor fails the build and names `TextServer.Feature`. `main.gd` calls it by name with Godot's
 * raw constants and checks the answers.
 */
@ScriptClass(attachTo = "TextServerExtension")
class EnumVirtualProbe(val godotObject: GodotHandle) {
  @OverrideVirtual
  fun _has_feature(feature: TextServer.Feature): Boolean = feature == TextServer.Feature.SHAPING
}

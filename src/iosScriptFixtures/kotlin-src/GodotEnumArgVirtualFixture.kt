package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.TextServer

// Task 128 B compile fixture: an engine virtual with a Godot-enum parameter on the iOS emitter.
@ScriptClass(attachTo = "TextServerExtension")
class GodotEnumArgVirtualFixture(val godotObject: GodotHandle) {
    @OverrideVirtual
    fun _has_feature(feature: TextServer.Feature): Boolean = feature == TextServer.Feature.SHAPING
}

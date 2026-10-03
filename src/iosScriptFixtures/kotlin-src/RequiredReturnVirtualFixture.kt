package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject

// Task 128 B compile fixture: the `meta: "required"` `_get_space_state` return, declared non-null.
@ScriptClass(attachTo = "PhysicsDirectBodyState3DExtension")
class RequiredReturnVirtualFixture(val godotObject: GodotHandle) {
    @OverrideVirtual
    fun _get_space_state(): GodotObject = GodotObject(godotObject)
}

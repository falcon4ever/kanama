package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.Shader

// Task 128 B compile fixture: an engine virtual with a Godot-enum return on the iOS emitter.
@ScriptClass(attachTo = "Material")
class GodotEnumVirtualFixture(val godotObject: GodotHandle) {
    @OverrideVirtual
    fun _get_shader_mode(): Shader.Mode = Shader.Mode.PARTICLES
}

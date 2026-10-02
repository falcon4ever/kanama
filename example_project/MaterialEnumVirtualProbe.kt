package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.Shader

/**
 * Task 128 B probe: an engine virtual with a Godot-enum RETURN (`Material._get_shader_mode():
 * enum::Shader.Mode`) returns the typed value class; the registrar hands Godot its `.value`.
 */
@ScriptClass(attachTo = "Material")
class MaterialEnumVirtualProbe(val godotObject: GodotHandle) {
  @OverrideVirtual fun _get_shader_mode(): Shader.Mode = Shader.Mode.PARTICLES
}

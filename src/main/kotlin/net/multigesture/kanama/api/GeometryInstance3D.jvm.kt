package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GeometryInstance3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GeometryInstance3D waits on: ptrcallWithStringNameAndVariantArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Set the value of a shader uniform for this instance only (per-instance uniform
 * ($DOCS_URL/tutorials/shaders/shader_reference/shading_language.html#per-instance-uniforms)). See
 * also `ShaderMaterial.set_shader_parameter` to assign a uniform on all instances using the same
 * `ShaderMaterial`. Note: For a shader uniform to be assignable on a per-instance basis, it must
 * be defined with `instance uniform ...` rather than `uniform ...` in the shader code. Note:
 * `name` is case-sensitive and must match the name of the uniform in the code exactly (not the
 * capitalized name in the inspector). Note: Per-instance shader uniforms are only available in
 * Spatial and CanvasItem shaders, but not for Fog, Sky, or Particles shaders.
 *
 * Generated from Godot docs: GeometryInstance3D.set_instance_shader_parameter
 */
fun GeometryInstance3D.setInstanceShaderParameter(name: String, value: Any?) {
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setInstanceShaderParameterBind, handle, name, value)
}

private const val SET_INSTANCE_SHADER_PARAMETER_HASH = 3776071444L
private val setInstanceShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("GeometryInstance3D", "set_instance_shader_parameter", SET_INSTANCE_SHADER_PARAMETER_HASH)
}

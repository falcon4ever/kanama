package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for CanvasItem (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CanvasItem waits on: ptrcallWithStringNameAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Set the value of a shader uniform for this instance only (per-instance uniform
 * ($DOCS_URL/tutorials/shaders/shader_reference/shading_language.html#per-instance-uniforms)). See
 * also `ShaderMaterial.set_shader_parameter` to assign a uniform on all instances using the same
 * `ShaderMaterial`. Note: For a shader uniform to be assignable on a per-instance basis, it must
 * be defined with `instance uniform ...` rather than `uniform ...` in the shader code. Note:
 * `name` is case-sensitive and must match the name of the uniform in the code exactly (not the
 * capitalized name in the inspector).
 *
 * Generated from Godot docs: CanvasItem.set_instance_shader_parameter
 */
fun CanvasItem.setInstanceShaderParameter(name: String, value: Any?) {
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setInstanceShaderParameterBind, handle, name, value)
}

private const val SET_INSTANCE_SHADER_PARAMETER_HASH = 3776071444L
private val setInstanceShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("CanvasItem", "set_instance_shader_parameter", SET_INSTANCE_SHADER_PARAMETER_HASH)
}

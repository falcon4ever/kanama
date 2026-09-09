package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Shader (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Shader waits on: ptrcallWithBoolArgRetArray
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the list of shader uniforms that can be assigned to a `ShaderMaterial`, for use with
 * `ShaderMaterial.set_shader_parameter` and `ShaderMaterial.get_shader_parameter`. The parameters
 * returned are contained in dictionaries in a similar format to the ones returned by
 * `Object.get_property_list`. If argument `get_groups` is `true`, parameter grouping hints are
 * also included in the list.
 *
 * Generated from Godot docs: Shader.get_shader_uniform_list
 */
fun Shader.getShaderUniformList(getGroups: Boolean = false): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithBoolArgRetArray(getShaderUniformListBind, handle, getGroups)
}

private const val GET_SHADER_UNIFORM_LIST_HASH = 1230511656L
private val getShaderUniformListBind by lazy {
    ObjectCalls.getMethodBind("Shader", "get_shader_uniform_list", GET_SHADER_UNIFORM_LIST_HASH)
}

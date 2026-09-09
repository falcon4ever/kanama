package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDPipelineShader (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDPipelineShader waits on: ptrcallWithObjectListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Specialization constants applied to the selected shader stage at pipeline creation time.
 *
 * Generated from Godot docs: RDPipelineShader.set_specialization_constants
 */
fun RDPipelineShader.setSpecializationConstants(specializationConstants: List<RDPipelineSpecializationConstant>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setSpecializationConstantsBind, handle, specializationConstants)
}

private const val SET_SPECIALIZATION_CONSTANTS_HASH = 381264803L
private val setSpecializationConstantsBind by lazy {
    ObjectCalls.getMethodBind("RDPipelineShader", "set_specialization_constants", SET_SPECIALIZATION_CONSTANTS_HASH)
}

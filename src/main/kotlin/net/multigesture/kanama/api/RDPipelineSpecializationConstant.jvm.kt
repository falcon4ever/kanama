package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDPipelineSpecializationConstant (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDPipelineSpecializationConstant waits on: ptrcallWithVariantArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The specialization constant's value. Only `bool`, `int` and `float` types are valid for
 * specialization constants.
 *
 * Generated from Godot docs: RDPipelineSpecializationConstant.set_value
 */
fun RDPipelineSpecializationConstant.setValue(value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(setValueBind, handle, value)
}

private const val SET_VALUE_HASH = 1114965689L
private val setValueBind by lazy {
    ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "set_value", SET_VALUE_HASH)
}

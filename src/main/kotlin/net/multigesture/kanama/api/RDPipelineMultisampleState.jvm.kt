package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDPipelineMultisampleState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDPipelineMultisampleState waits on: ptrcallWithTypedIntListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The sample mask array. See the sample mask Vulkan documentation
 * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fragops-samplemask)
 * for more details.
 *
 * Generated from Godot docs: RDPipelineMultisampleState.set_sample_masks
 */
fun RDPipelineMultisampleState.setSampleMasks(masks: List<Long>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedIntListArg(setSampleMasksBind, handle, masks)
}

private const val SET_SAMPLE_MASKS_HASH = 381264803L
private val setSampleMasksBind by lazy {
    ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_sample_masks", SET_SAMPLE_MASKS_HASH)
}

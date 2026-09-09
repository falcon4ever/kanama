package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for MultiMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP MultiMesh waits on: ptrcallWithTwoPackedFloat32ListArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * An alternative to setting the `buffer` property, which can be used with physics interpolation.
 * This method takes two arrays, and can set the data for the current and previous tick in one go.
 * The renderer will automatically interpolate the data at each frame. This is useful for
 * situations where the order of instances may change from physics tick to tick, such as particle
 * systems. When the order of instances is coherent, the simpler alternative of setting `buffer`
 * can still be used with interpolation.
 *
 * Generated from Godot docs: MultiMesh.set_buffer_interpolated
 */
fun MultiMesh.setBufferInterpolated(bufferCurr: List<Float>, bufferPrev: List<Float>) {
    checkOpen()
    ObjectCalls.ptrcallWithTwoPackedFloat32ListArgs(setBufferInterpolatedBind, handle, bufferCurr, bufferPrev)
}

private const val SET_BUFFER_INTERPOLATED_HASH = 3514430332L
private val setBufferInterpolatedBind by lazy {
    ObjectCalls.getMethodBind("MultiMesh", "set_buffer_interpolated", SET_BUFFER_INTERPOLATED_HASH)
}

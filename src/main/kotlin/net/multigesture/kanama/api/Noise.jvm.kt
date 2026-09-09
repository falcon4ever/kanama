package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Noise (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Noise waits on: ptrcallWithThreeIntBoolDoubleBoolArgsRetTypedObjectList,
//   ptrcallWithThreeIntTwoBoolArgsRetTypedObjectList
// Index: docs/reference/generated/ios-shape-gap.md

fun Noise.getImage3d(width: Int, height: Int, depth: Int, invert: Boolean = false, normalize: Boolean = true): List<Image> {
    checkOpen()
    return ObjectCalls.ptrcallWithThreeIntTwoBoolArgsRetTypedObjectList(getImage3dBind, handle, width, height, depth, invert, normalize, Image::fromHandle)
}

fun Noise.getSeamlessImage3d(width: Int, height: Int, depth: Int, invert: Boolean = false, skirt: Double = 0.1, normalize: Boolean = true): List<Image> {
    checkOpen()
    return ObjectCalls.ptrcallWithThreeIntBoolDoubleBoolArgsRetTypedObjectList(getSeamlessImage3dBind, handle, width, height, depth, invert, skirt, normalize, Image::fromHandle)
}

private const val GET_IMAGE_3D_HASH = 3977814329L
private val getImage3dBind by lazy {
    ObjectCalls.getMethodBind("Noise", "get_image_3d", GET_IMAGE_3D_HASH)
}

private const val GET_SEAMLESS_IMAGE_3D_HASH = 451006340L
private val getSeamlessImage3dBind by lazy {
    ObjectCalls.getMethodBind("Noise", "get_seamless_image_3d", GET_SEAMLESS_IMAGE_3D_HASH)
}

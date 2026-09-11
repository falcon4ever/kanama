package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

// GENERATED desktop/Android companion for OpenXRAPIExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRAPIExtension waits on: ptrcallWithConstVoidPtrArg,
//   ptrcallWithConstVoidPtrArgRetTransform3D, ptrcallWithLongStringArrayArgsRetBool
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRAPIExtension.transformFromPose(pose: MemorySegment): Transform3D {
    checkOpen()
    return ObjectCalls.ptrcallWithConstVoidPtrArgRetTransform3D(transformFromPoseBind, handle, pose)
}

fun OpenXRAPIExtension.xrResult(result: Long, format: String, args: List<Any?>): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithLongStringArrayArgsRetBool(xrResultBind, handle, result, format, args)
}

fun OpenXRAPIExtension.setCustomPlaySpace(space: MemorySegment) {
    checkOpen()
    ObjectCalls.ptrcallWithConstVoidPtrArg(setCustomPlaySpaceBind, handle, space)
}

private const val TRANSFORM_FROM_POSE_HASH = 2963875352L
private val transformFromPoseBind by lazy {
    ObjectCalls.getMethodBind("OpenXRAPIExtension", "transform_from_pose", TRANSFORM_FROM_POSE_HASH)
}

private const val XR_RESULT_HASH = 3886436197L
private val xrResultBind by lazy {
    ObjectCalls.getMethodBind("OpenXRAPIExtension", "xr_result", XR_RESULT_HASH)
}

private const val SET_CUSTOM_PLAY_SPACE_HASH = 1286410249L
private val setCustomPlaySpaceBind by lazy {
    ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_custom_play_space", SET_CUSTOM_PLAY_SPACE_HASH)
}

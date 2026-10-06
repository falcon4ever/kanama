package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmField
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

// GENERATED desktop/Android companion for OpenXRAPIExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRAPIExtension waits on: ptrcallWithConstVoidPtrArg,
//   ptrcallWithConstVoidPtrArgRetTransform3D
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRAPIExtension.transformFromPose(pose: MemorySegment): Transform3D {
    checkOpen()
    return ObjectCalls.ptrcallWithConstVoidPtrArgRetTransform3D(OpenXRAPIExtensionJvmBinds.transformFromPoseBind, segment, pose)
}

fun OpenXRAPIExtension.setCustomPlaySpace(space: MemorySegment) {
    checkOpen()
    ObjectCalls.ptrcallWithConstVoidPtrArg(OpenXRAPIExtensionJvmBinds.setCustomPlaySpaceBind, segment, space)
}

private object OpenXRAPIExtensionJvmBinds {
    private const val TRANSFORM_FROM_POSE_HASH = 2963875352L
    @JvmField
    val transformFromPoseBind =
        ObjectCalls.getMethodBind("OpenXRAPIExtension", "transform_from_pose", TRANSFORM_FROM_POSE_HASH)

    private const val SET_CUSTOM_PLAY_SPACE_HASH = 1286410249L
    @JvmField
    val setCustomPlaySpaceBind =
        ObjectCalls.getMethodBind("OpenXRAPIExtension", "set_custom_play_space", SET_CUSTOM_PLAY_SPACE_HASH)
}

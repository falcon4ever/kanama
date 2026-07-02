package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: OpenXRHand
 */
class OpenXRHand(handle: MemorySegment) : Node3D(handle) {
    var hand: Long
        @JvmName("handProperty")
        get() = getHand()
        @JvmName("setHandProperty")
        set(value) = setHand(value)

    var motionRange: Long
        @JvmName("motionRangeProperty")
        get() = getMotionRange()
        @JvmName("setMotionRangeProperty")
        set(value) = setMotionRange(value)

    var handSkeleton: NodePath
        @JvmName("handSkeletonProperty")
        get() = getHandSkeleton()
        @JvmName("setHandSkeletonProperty")
        set(value) = setHandSkeleton(value)

    var skeletonRig: Long
        @JvmName("skeletonRigProperty")
        get() = getSkeletonRig()
        @JvmName("setSkeletonRigProperty")
        set(value) = setSkeletonRig(value)

    var boneUpdate: Long
        @JvmName("boneUpdateProperty")
        get() = getBoneUpdate()
        @JvmName("setBoneUpdateProperty")
        set(value) = setBoneUpdate(value)

    fun setHand(hand: Long) {
        ObjectCalls.ptrcallWithLongArg(setHandBind, handle, hand)
    }

    fun getHand(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHandBind, handle)
    }

    fun setHandSkeleton(handSkeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setHandSkeletonBind, handle, handSkeleton)
    }

    fun getHandSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getHandSkeletonBind, handle)
    }

    fun setMotionRange(motionRange: Long) {
        ObjectCalls.ptrcallWithLongArg(setMotionRangeBind, handle, motionRange)
    }

    fun getMotionRange(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMotionRangeBind, handle)
    }

    fun setSkeletonRig(skeletonRig: Long) {
        ObjectCalls.ptrcallWithLongArg(setSkeletonRigBind, handle, skeletonRig)
    }

    fun getSkeletonRig(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSkeletonRigBind, handle)
    }

    fun setBoneUpdate(boneUpdate: Long) {
        ObjectCalls.ptrcallWithLongArg(setBoneUpdateBind, handle, boneUpdate)
    }

    fun getBoneUpdate(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBoneUpdateBind, handle)
    }

    companion object {
        const val HAND_LEFT: Long = 0L
        const val HAND_RIGHT: Long = 1L
        const val HAND_MAX: Long = 2L
        const val MOTION_RANGE_UNOBSTRUCTED: Long = 0L
        const val MOTION_RANGE_CONFORM_TO_CONTROLLER: Long = 1L
        const val MOTION_RANGE_MAX: Long = 2L
        const val SKELETON_RIG_OPENXR: Long = 0L
        const val SKELETON_RIG_HUMANOID: Long = 1L
        const val SKELETON_RIG_MAX: Long = 2L
        const val BONE_UPDATE_FULL: Long = 0L
        const val BONE_UPDATE_ROTATION_ONLY: Long = 1L
        const val BONE_UPDATE_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRHand? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRHand? =
            if (handle.address() == 0L) null else OpenXRHand(handle)

        private const val SET_HAND_HASH = 1849328560L
        private val setHandBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "set_hand", SET_HAND_HASH)
        }

        private const val GET_HAND_HASH = 2850644561L
        private val getHandBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "get_hand", GET_HAND_HASH)
        }

        private const val SET_HAND_SKELETON_HASH = 1348162250L
        private val setHandSkeletonBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "set_hand_skeleton", SET_HAND_SKELETON_HASH)
        }

        private const val GET_HAND_SKELETON_HASH = 4075236667L
        private val getHandSkeletonBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "get_hand_skeleton", GET_HAND_SKELETON_HASH)
        }

        private const val SET_MOTION_RANGE_HASH = 3326516003L
        private val setMotionRangeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "set_motion_range", SET_MOTION_RANGE_HASH)
        }

        private const val GET_MOTION_RANGE_HASH = 2191822314L
        private val getMotionRangeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "get_motion_range", GET_MOTION_RANGE_HASH)
        }

        private const val SET_SKELETON_RIG_HASH = 1528072213L
        private val setSkeletonRigBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "set_skeleton_rig", SET_SKELETON_RIG_HASH)
        }

        private const val GET_SKELETON_RIG_HASH = 968409338L
        private val getSkeletonRigBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "get_skeleton_rig", GET_SKELETON_RIG_HASH)
        }

        private const val SET_BONE_UPDATE_HASH = 3144625444L
        private val setBoneUpdateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "set_bone_update", SET_BONE_UPDATE_HASH)
        }

        private const val GET_BONE_UPDATE_HASH = 1310695248L
        private val getBoneUpdateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRHand", "get_bone_update", GET_BONE_UPDATE_HASH)
        }
    }
}

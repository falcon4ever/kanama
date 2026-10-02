package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: OpenXRHand
 */
class OpenXRHand(handle: GodotHandle) : Node3D(handle) {
    var hand: OpenXRHand.Hands
        @JvmName("handProperty")
        get() = getHand()
        @JvmName("setHandProperty")
        set(value) = setHand(value)

    var motionRange: OpenXRHand.MotionRange
        @JvmName("motionRangeProperty")
        get() = getMotionRange()
        @JvmName("setMotionRangeProperty")
        set(value) = setMotionRange(value)

    var handSkeleton: NodePath
        @JvmName("handSkeletonProperty")
        get() = getHandSkeleton()
        @JvmName("setHandSkeletonProperty")
        set(value) = setHandSkeleton(value)

    var skeletonRig: OpenXRHand.SkeletonRig
        @JvmName("skeletonRigProperty")
        get() = getSkeletonRig()
        @JvmName("setSkeletonRigProperty")
        set(value) = setSkeletonRig(value)

    var boneUpdate: OpenXRHand.BoneUpdate
        @JvmName("boneUpdateProperty")
        get() = getBoneUpdate()
        @JvmName("setBoneUpdateProperty")
        set(value) = setBoneUpdate(value)

    fun setHand(hand: OpenXRHand.Hands) {
        ObjectCalls.ptrcallWithLongArg(setHandBind, segment, hand.value)
    }

    fun getHand(): OpenXRHand.Hands {
        return OpenXRHand.Hands(ObjectCalls.ptrcallNoArgsRetLong(getHandBind, segment))
    }

    fun setHandSkeleton(handSkeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setHandSkeletonBind, segment, handSkeleton)
    }

    fun getHandSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getHandSkeletonBind, segment)
    }

    fun setMotionRange(motionRange: OpenXRHand.MotionRange) {
        ObjectCalls.ptrcallWithLongArg(setMotionRangeBind, segment, motionRange.value)
    }

    fun getMotionRange(): OpenXRHand.MotionRange {
        return OpenXRHand.MotionRange(ObjectCalls.ptrcallNoArgsRetLong(getMotionRangeBind, segment))
    }

    fun setSkeletonRig(skeletonRig: OpenXRHand.SkeletonRig) {
        ObjectCalls.ptrcallWithLongArg(setSkeletonRigBind, segment, skeletonRig.value)
    }

    fun getSkeletonRig(): OpenXRHand.SkeletonRig {
        return OpenXRHand.SkeletonRig(ObjectCalls.ptrcallNoArgsRetLong(getSkeletonRigBind, segment))
    }

    fun setBoneUpdate(boneUpdate: OpenXRHand.BoneUpdate) {
        ObjectCalls.ptrcallWithLongArg(setBoneUpdateBind, segment, boneUpdate.value)
    }

    fun getBoneUpdate(): OpenXRHand.BoneUpdate {
        return OpenXRHand.BoneUpdate(ObjectCalls.ptrcallNoArgsRetLong(getBoneUpdateBind, segment))
    }

    @JvmInline
    value class Hands(override val value: Long) : GodotEnumValue {
        companion object {
            val LEFT: Hands get() = Hands(0L)
            val RIGHT: Hands get() = Hands(1L)
            val MAX: Hands get() = Hands(2L)
        }
    }

    @JvmInline
    value class MotionRange(override val value: Long) : GodotEnumValue {
        companion object {
            val UNOBSTRUCTED: MotionRange get() = MotionRange(0L)
            val CONFORM_TO_CONTROLLER: MotionRange get() = MotionRange(1L)
            val MAX: MotionRange get() = MotionRange(2L)
        }
    }

    @JvmInline
    value class SkeletonRig(override val value: Long) : GodotEnumValue {
        companion object {
            val OPENXR: SkeletonRig get() = SkeletonRig(0L)
            val HUMANOID: SkeletonRig get() = SkeletonRig(1L)
            val MAX: SkeletonRig get() = SkeletonRig(2L)
        }
    }

    @JvmInline
    value class BoneUpdate(override val value: Long) : GodotEnumValue {
        companion object {
            val FULL: BoneUpdate get() = BoneUpdate(0L)
            val ROTATION_ONLY: BoneUpdate get() = BoneUpdate(1L)
            val MAX: BoneUpdate get() = BoneUpdate(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRHand? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRHand? =
            if (handle.address() == 0L) null else OpenXRHand(GodotHandle(handle))

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

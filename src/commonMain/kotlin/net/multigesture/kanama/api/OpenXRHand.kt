package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithLongArg(Binds.setHandBind, segment, hand.value)
    }

    fun getHand(): OpenXRHand.Hands {
        return OpenXRHand.Hands(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHandBind, segment))
    }

    fun setHandSkeleton(handSkeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setHandSkeletonBind, segment, handSkeleton)
    }

    fun getHandSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getHandSkeletonBind, segment)
    }

    fun setMotionRange(motionRange: OpenXRHand.MotionRange) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMotionRangeBind, segment, motionRange.value)
    }

    fun getMotionRange(): OpenXRHand.MotionRange {
        return OpenXRHand.MotionRange(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMotionRangeBind, segment))
    }

    fun setSkeletonRig(skeletonRig: OpenXRHand.SkeletonRig) {
        ObjectCalls.ptrcallWithLongArg(Binds.setSkeletonRigBind, segment, skeletonRig.value)
    }

    fun getSkeletonRig(): OpenXRHand.SkeletonRig {
        return OpenXRHand.SkeletonRig(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSkeletonRigBind, segment))
    }

    fun setBoneUpdate(boneUpdate: OpenXRHand.BoneUpdate) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBoneUpdateBind, segment, boneUpdate.value)
    }

    fun getBoneUpdate(): OpenXRHand.BoneUpdate {
        return OpenXRHand.BoneUpdate(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBoneUpdateBind, segment))
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
    }

    private object Binds {
        private const val SET_HAND_HASH = 1849328560L
        @JvmField
        val setHandBind =
            ObjectCalls.getMethodBind("OpenXRHand", "set_hand", SET_HAND_HASH)

        private const val GET_HAND_HASH = 2850644561L
        @JvmField
        val getHandBind =
            ObjectCalls.getMethodBind("OpenXRHand", "get_hand", GET_HAND_HASH)

        private const val SET_HAND_SKELETON_HASH = 1348162250L
        @JvmField
        val setHandSkeletonBind =
            ObjectCalls.getMethodBind("OpenXRHand", "set_hand_skeleton", SET_HAND_SKELETON_HASH)

        private const val GET_HAND_SKELETON_HASH = 4075236667L
        @JvmField
        val getHandSkeletonBind =
            ObjectCalls.getMethodBind("OpenXRHand", "get_hand_skeleton", GET_HAND_SKELETON_HASH)

        private const val SET_MOTION_RANGE_HASH = 3326516003L
        @JvmField
        val setMotionRangeBind =
            ObjectCalls.getMethodBind("OpenXRHand", "set_motion_range", SET_MOTION_RANGE_HASH)

        private const val GET_MOTION_RANGE_HASH = 2191822314L
        @JvmField
        val getMotionRangeBind =
            ObjectCalls.getMethodBind("OpenXRHand", "get_motion_range", GET_MOTION_RANGE_HASH)

        private const val SET_SKELETON_RIG_HASH = 1528072213L
        @JvmField
        val setSkeletonRigBind =
            ObjectCalls.getMethodBind("OpenXRHand", "set_skeleton_rig", SET_SKELETON_RIG_HASH)

        private const val GET_SKELETON_RIG_HASH = 968409338L
        @JvmField
        val getSkeletonRigBind =
            ObjectCalls.getMethodBind("OpenXRHand", "get_skeleton_rig", GET_SKELETON_RIG_HASH)

        private const val SET_BONE_UPDATE_HASH = 3144625444L
        @JvmField
        val setBoneUpdateBind =
            ObjectCalls.getMethodBind("OpenXRHand", "set_bone_update", SET_BONE_UPDATE_HASH)

        private const val GET_BONE_UPDATE_HASH = 1310695248L
        @JvmField
        val getBoneUpdateBind =
            ObjectCalls.getMethodBind("OpenXRHand", "get_bone_update", GET_BONE_UPDATE_HASH)
    }
}

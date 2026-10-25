package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Node supporting hand and finger tracking in OpenXR.
 *
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

    /**
     * Specifies whether this node tracks the left or right hand of the player.
     *
     * Generated from Godot docs: OpenXRHand.set_hand
     */
    fun setHand(hand: OpenXRHand.Hands) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHandBind, segment, hand.value)
    }

    /**
     * Specifies whether this node tracks the left or right hand of the player.
     *
     * Generated from Godot docs: OpenXRHand.get_hand
     */
    fun getHand(): OpenXRHand.Hands {
        return OpenXRHand.Hands(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHandBind, segment))
    }

    /**
     * Set a `Skeleton3D` node for which the pose positions will be updated.
     *
     * Generated from Godot docs: OpenXRHand.set_hand_skeleton
     */
    fun setHandSkeleton(handSkeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setHandSkeletonBind, segment, handSkeleton)
    }

    /**
     * Set a `Skeleton3D` node for which the pose positions will be updated.
     *
     * Generated from Godot docs: OpenXRHand.get_hand_skeleton
     */
    fun getHandSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getHandSkeletonBind, segment)
    }

    /**
     * Set the motion range (if supported) limiting the hand motion.
     *
     * Generated from Godot docs: OpenXRHand.set_motion_range
     */
    fun setMotionRange(motionRange: OpenXRHand.MotionRange) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMotionRangeBind, segment, motionRange.value)
    }

    /**
     * Set the motion range (if supported) limiting the hand motion.
     *
     * Generated from Godot docs: OpenXRHand.get_motion_range
     */
    fun getMotionRange(): OpenXRHand.MotionRange {
        return OpenXRHand.MotionRange(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMotionRangeBind, segment))
    }

    /**
     * Set the type of skeleton rig the `hand_skeleton` is compliant with.
     *
     * Generated from Godot docs: OpenXRHand.set_skeleton_rig
     */
    fun setSkeletonRig(skeletonRig: OpenXRHand.SkeletonRig) {
        ObjectCalls.ptrcallWithLongArg(Binds.setSkeletonRigBind, segment, skeletonRig.value)
    }

    /**
     * Set the type of skeleton rig the `hand_skeleton` is compliant with.
     *
     * Generated from Godot docs: OpenXRHand.get_skeleton_rig
     */
    fun getSkeletonRig(): OpenXRHand.SkeletonRig {
        return OpenXRHand.SkeletonRig(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSkeletonRigBind, segment))
    }

    /**
     * Specify the type of updates to perform on the bone.
     *
     * Generated from Godot docs: OpenXRHand.set_bone_update
     */
    fun setBoneUpdate(boneUpdate: OpenXRHand.BoneUpdate) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBoneUpdateBind, segment, boneUpdate.value)
    }

    /**
     * Specify the type of updates to perform on the bone.
     *
     * Generated from Godot docs: OpenXRHand.get_bone_update
     */
    fun getBoneUpdate(): OpenXRHand.BoneUpdate {
        return OpenXRHand.BoneUpdate(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBoneUpdateBind, segment))
    }

    /**
     * Godot's `OpenXRHand.Hands` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`OpenXRHand.Hands.<NAME>`).
     *
     * Generated from Godot docs: OpenXRHand.Hands
     */
    @JvmInline
    value class Hands(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Tracking the player's left hand.
             *
             * Generated from Godot docs: OpenXRHand.HAND_LEFT
             */
            val LEFT: Hands get() = Hands(0L)
            /**
             * Tracking the player's right hand.
             *
             * Generated from Godot docs: OpenXRHand.HAND_RIGHT
             */
            val RIGHT: Hands get() = Hands(1L)
            /**
             * Maximum supported hands.
             *
             * Generated from Godot docs: OpenXRHand.HAND_MAX
             */
            val MAX: Hands get() = Hands(2L)
        }
    }

    /**
     * Godot's `OpenXRHand.MotionRange` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`OpenXRHand.MotionRange.<NAME>`).
     *
     * Generated from Godot docs: OpenXRHand.MotionRange
     */
    @JvmInline
    value class MotionRange(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * When player grips, hand skeleton will form a full fist.
             *
             * Generated from Godot docs: OpenXRHand.MOTION_RANGE_UNOBSTRUCTED
             */
            val UNOBSTRUCTED: MotionRange get() = MotionRange(0L)
            /**
             * When player grips, hand skeleton conforms to the controller the player is holding.
             *
             * Generated from Godot docs: OpenXRHand.MOTION_RANGE_CONFORM_TO_CONTROLLER
             */
            val CONFORM_TO_CONTROLLER: MotionRange get() = MotionRange(1L)
            /**
             * Maximum supported motion ranges.
             *
             * Generated from Godot docs: OpenXRHand.MOTION_RANGE_MAX
             */
            val MAX: MotionRange get() = MotionRange(2L)
        }
    }

    /**
     * Godot's `OpenXRHand.SkeletonRig` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`OpenXRHand.SkeletonRig.<NAME>`).
     *
     * Generated from Godot docs: OpenXRHand.SkeletonRig
     */
    @JvmInline
    value class SkeletonRig(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * An OpenXR compliant skeleton.
             *
             * Generated from Godot docs: OpenXRHand.SKELETON_RIG_OPENXR
             */
            val OPENXR: SkeletonRig get() = SkeletonRig(0L)
            /**
             * A `SkeletonProfileHumanoid` compliant skeleton.
             *
             * Generated from Godot docs: OpenXRHand.SKELETON_RIG_HUMANOID
             */
            val HUMANOID: SkeletonRig get() = SkeletonRig(1L)
            /**
             * Maximum supported hands.
             *
             * Generated from Godot docs: OpenXRHand.SKELETON_RIG_MAX
             */
            val MAX: SkeletonRig get() = SkeletonRig(2L)
        }
    }

    /**
     * Godot's `OpenXRHand.BoneUpdate` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`OpenXRHand.BoneUpdate.<NAME>`).
     *
     * Generated from Godot docs: OpenXRHand.BoneUpdate
     */
    @JvmInline
    value class BoneUpdate(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The skeletons bones are fully updated (both position and rotation) to match the tracked bones.
             *
             * Generated from Godot docs: OpenXRHand.BONE_UPDATE_FULL
             */
            val FULL: BoneUpdate get() = BoneUpdate(0L)
            /**
             * The skeletons bones are only rotated to align with the tracked bones, preserving bone length.
             *
             * Generated from Godot docs: OpenXRHand.BONE_UPDATE_ROTATION_ONLY
             */
            val ROTATION_ONLY: BoneUpdate get() = BoneUpdate(1L)
            /**
             * Maximum supported bone update mode.
             *
             * Generated from Godot docs: OpenXRHand.BONE_UPDATE_MAX
             */
            val MAX: BoneUpdate get() = BoneUpdate(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRHand? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRHand? =
            if (handle.address() == 0L) null else OpenXRHand(GodotHandle(handle))

        /** A new OpenXRHand, GDScript's `OpenXRHand.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRHand =
            OpenXRHand(GodotHandle(ObjectCalls.constructObject("OpenXRHand")))
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

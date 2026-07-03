package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform2D

/**
 * A joint used with `Skeleton2D` to control and animate other nodes.
 *
 * Generated from Godot docs: Bone2D
 */
class Bone2D(handle: MemorySegment) : Node2D(handle) {
    var rest: Transform2D
        @JvmName("restProperty")
        get() = getRest()
        @JvmName("setRestProperty")
        set(value) = setRest(value)

    /**
     * Rest transform of the bone. You can reset the node's transforms to this value using
     * `apply_rest`.
     *
     * Generated from Godot docs: Bone2D.set_rest
     */
    fun setRest(rest: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setRestBind, handle, rest)
    }

    /**
     * Rest transform of the bone. You can reset the node's transforms to this value using
     * `apply_rest`.
     *
     * Generated from Godot docs: Bone2D.get_rest
     */
    fun getRest(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getRestBind, handle)
    }

    /**
     * Resets the bone to the rest pose. This is equivalent to setting `Node2D.transform` to `rest`.
     *
     * Generated from Godot docs: Bone2D.apply_rest
     */
    fun applyRest() {
        ObjectCalls.ptrcallNoArgs(applyRestBind, handle)
    }

    /**
     * Returns the node's `rest` `Transform2D` if it doesn't have a parent, or its rest pose relative
     * to its parent.
     *
     * Generated from Godot docs: Bone2D.get_skeleton_rest
     */
    fun getSkeletonRest(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getSkeletonRestBind, handle)
    }

    /**
     * Returns the node's index as part of the entire skeleton. See `Skeleton2D`.
     *
     * Generated from Godot docs: Bone2D.get_index_in_skeleton
     */
    fun getIndexInSkeleton(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getIndexInSkeletonBind, handle)
    }

    /**
     * When set to `true`, the `Bone2D` node will attempt to automatically calculate the bone angle and
     * length using the first child `Bone2D` node, if one exists. If none exist, the `Bone2D` cannot
     * automatically calculate these values and will print a warning.
     *
     * Generated from Godot docs: Bone2D.set_autocalculate_length_and_angle
     */
    fun setAutocalculateLengthAndAngle(autoCalculate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutocalculateLengthAndAngleBind, handle, autoCalculate)
    }

    /**
     * Returns whether this `Bone2D` is going to autocalculate its length and bone angle using its
     * first `Bone2D` child node, if one exists. If there are no `Bone2D` children, then it cannot
     * autocalculate these values and will print a warning.
     *
     * Generated from Godot docs: Bone2D.get_autocalculate_length_and_angle
     */
    fun getAutocalculateLengthAndAngle(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAutocalculateLengthAndAngleBind, handle)
    }

    /**
     * Sets the length of the bone in the `Bone2D`.
     *
     * Generated from Godot docs: Bone2D.set_length
     */
    fun setLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLengthBind, handle, length)
    }

    /**
     * Returns the length of the bone in the `Bone2D` node.
     *
     * Generated from Godot docs: Bone2D.get_length
     */
    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    /**
     * Sets the bone angle for the `Bone2D`. This is typically set to the rotation from the `Bone2D` to
     * a child `Bone2D` node. Note: This is different from the `Bone2D`'s rotation. The bone's angle is
     * the rotation of the bone shown by the gizmo, which is unaffected by the `Bone2D`'s
     * `Node2D.transform`.
     *
     * Generated from Godot docs: Bone2D.set_bone_angle
     */
    fun setBoneAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBoneAngleBind, handle, angle)
    }

    /**
     * Returns the angle of the bone in the `Bone2D`. Note: This is different from the `Bone2D`'s
     * rotation. The bone's angle is the rotation of the bone shown by the gizmo, which is unaffected
     * by the `Bone2D`'s `Node2D.transform`.
     *
     * Generated from Godot docs: Bone2D.get_bone_angle
     */
    fun getBoneAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBoneAngleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Bone2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Bone2D? =
            if (handle.address() == 0L) null else Bone2D(handle)

        private const val SET_REST_HASH = 2761652528L
        private val setRestBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "set_rest", SET_REST_HASH)
        }

        private const val GET_REST_HASH = 3814499831L
        private val getRestBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "get_rest", GET_REST_HASH)
        }

        private const val APPLY_REST_HASH = 3218959716L
        private val applyRestBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "apply_rest", APPLY_REST_HASH)
        }

        private const val GET_SKELETON_REST_HASH = 3814499831L
        private val getSkeletonRestBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "get_skeleton_rest", GET_SKELETON_REST_HASH)
        }

        private const val GET_INDEX_IN_SKELETON_HASH = 3905245786L
        private val getIndexInSkeletonBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "get_index_in_skeleton", GET_INDEX_IN_SKELETON_HASH)
        }

        private const val SET_AUTOCALCULATE_LENGTH_AND_ANGLE_HASH = 2586408642L
        private val setAutocalculateLengthAndAngleBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "set_autocalculate_length_and_angle", SET_AUTOCALCULATE_LENGTH_AND_ANGLE_HASH)
        }

        private const val GET_AUTOCALCULATE_LENGTH_AND_ANGLE_HASH = 36873697L
        private val getAutocalculateLengthAndAngleBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "get_autocalculate_length_and_angle", GET_AUTOCALCULATE_LENGTH_AND_ANGLE_HASH)
        }

        private const val SET_LENGTH_HASH = 373806689L
        private val setLengthBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "set_length", SET_LENGTH_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "get_length", GET_LENGTH_HASH)
        }

        private const val SET_BONE_ANGLE_HASH = 373806689L
        private val setBoneAngleBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "set_bone_angle", SET_BONE_ANGLE_HASH)
        }

        private const val GET_BONE_ANGLE_HASH = 1740695150L
        private val getBoneAngleBind by lazy {
            ObjectCalls.getMethodBind("Bone2D", "get_bone_angle", GET_BONE_ANGLE_HASH)
        }
    }
}

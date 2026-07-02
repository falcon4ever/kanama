package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A modification that rotates a `Bone2D` node to look at a target.
 *
 * Generated from Godot docs: SkeletonModification2DLookAt
 */
class SkeletonModification2DLookAt(handle: MemorySegment) : SkeletonModification2D(handle) {
    var boneIndex: Int
        @JvmName("boneIndexProperty")
        get() = getBoneIndex()
        @JvmName("setBoneIndexProperty")
        set(value) = setBoneIndex(value)

    var bone2dNode: NodePath
        @JvmName("bone2dNodeProperty")
        get() = getBone2dNode()
        @JvmName("setBone2dNodeProperty")
        set(value) = setBone2dNode(value)

    var targetNodepath: NodePath
        @JvmName("targetNodepathProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodepathProperty")
        set(value) = setTargetNode(value)

    fun setBone2dNode(bone2dNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setBone2dNodeBind, handle, bone2dNodepath)
    }

    fun getBone2dNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getBone2dNodeBind, handle)
    }

    fun setBoneIndex(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneIndexBind, handle, boneIdx)
    }

    fun getBoneIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneIndexBind, handle)
    }

    fun setTargetNode(targetNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    fun setAdditionalRotation(rotation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAdditionalRotationBind, handle, rotation)
    }

    fun getAdditionalRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAdditionalRotationBind, handle)
    }

    fun setEnableConstraint(enableConstraint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableConstraintBind, handle, enableConstraint)
    }

    fun getEnableConstraint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableConstraintBind, handle)
    }

    fun setConstraintAngleMin(angleMin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setConstraintAngleMinBind, handle, angleMin)
    }

    fun getConstraintAngleMin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstraintAngleMinBind, handle)
    }

    fun setConstraintAngleMax(angleMax: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setConstraintAngleMaxBind, handle, angleMax)
    }

    fun getConstraintAngleMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstraintAngleMaxBind, handle)
    }

    fun setConstraintAngleInvert(invert: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setConstraintAngleInvertBind, handle, invert)
    }

    fun getConstraintAngleInvert(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getConstraintAngleInvertBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModification2DLookAt? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DLookAt? =
            if (handle.address() == 0L) null else SkeletonModification2DLookAt(handle)

        private const val SET_BONE2D_NODE_HASH = 1348162250L
        private val setBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_bone2d_node", SET_BONE2D_NODE_HASH)
        }

        private const val GET_BONE2D_NODE_HASH = 4075236667L
        private val getBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_bone2d_node", GET_BONE2D_NODE_HASH)
        }

        private const val SET_BONE_INDEX_HASH = 1286410249L
        private val setBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_bone_index", SET_BONE_INDEX_HASH)
        }

        private const val GET_BONE_INDEX_HASH = 3905245786L
        private val getBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_bone_index", GET_BONE_INDEX_HASH)
        }

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 4075236667L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_ADDITIONAL_ROTATION_HASH = 373806689L
        private val setAdditionalRotationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_additional_rotation", SET_ADDITIONAL_ROTATION_HASH)
        }

        private const val GET_ADDITIONAL_ROTATION_HASH = 1740695150L
        private val getAdditionalRotationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_additional_rotation", GET_ADDITIONAL_ROTATION_HASH)
        }

        private const val SET_ENABLE_CONSTRAINT_HASH = 2586408642L
        private val setEnableConstraintBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_enable_constraint", SET_ENABLE_CONSTRAINT_HASH)
        }

        private const val GET_ENABLE_CONSTRAINT_HASH = 36873697L
        private val getEnableConstraintBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_enable_constraint", GET_ENABLE_CONSTRAINT_HASH)
        }

        private const val SET_CONSTRAINT_ANGLE_MIN_HASH = 373806689L
        private val setConstraintAngleMinBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_constraint_angle_min", SET_CONSTRAINT_ANGLE_MIN_HASH)
        }

        private const val GET_CONSTRAINT_ANGLE_MIN_HASH = 1740695150L
        private val getConstraintAngleMinBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_constraint_angle_min", GET_CONSTRAINT_ANGLE_MIN_HASH)
        }

        private const val SET_CONSTRAINT_ANGLE_MAX_HASH = 373806689L
        private val setConstraintAngleMaxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_constraint_angle_max", SET_CONSTRAINT_ANGLE_MAX_HASH)
        }

        private const val GET_CONSTRAINT_ANGLE_MAX_HASH = 1740695150L
        private val getConstraintAngleMaxBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_constraint_angle_max", GET_CONSTRAINT_ANGLE_MAX_HASH)
        }

        private const val SET_CONSTRAINT_ANGLE_INVERT_HASH = 2586408642L
        private val setConstraintAngleInvertBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "set_constraint_angle_invert", SET_CONSTRAINT_ANGLE_INVERT_HASH)
        }

        private const val GET_CONSTRAINT_ANGLE_INVERT_HASH = 36873697L
        private val getConstraintAngleInvertBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DLookAt", "get_constraint_angle_invert", GET_CONSTRAINT_ANGLE_INVERT_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3

/**
 * Rotation based intersection of two circles inverse kinematics solver.
 *
 * Generated from Godot docs: TwoBoneIK3D
 */
class TwoBoneIK3D(handle: GodotHandle) : IKModifier3D(handle) {
    /**
     * Sets the target node that the end bone is trying to reach.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_target_node
     */
    fun setTargetNode(index: Int, targetNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setTargetNodeBind, segment, index, targetNode)
    }

    /**
     * Returns the target node that the end bone is trying to reach.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_target_node
     */
    fun getTargetNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getTargetNodeBind, segment, index)
    }

    /**
     * Sets the pole target node that constructs a plane which the joints are all on and the pole is
     * trying to direct.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_pole_node
     */
    fun setPoleNode(index: Int, poleNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setPoleNodeBind, segment, index, poleNode)
    }

    /**
     * Returns the pole target node that constructs a plane which the joints are all on and the pole is
     * trying to direct.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_pole_node
     */
    fun getPoleNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getPoleNodeBind, segment, index)
    }

    /**
     * Sets the root bone name.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_root_bone_name
     */
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setRootBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the root bone name.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_root_bone_name
     */
    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getRootBoneNameBind, segment, index)
    }

    /**
     * Sets the root bone index.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_root_bone
     */
    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setRootBoneBind, segment, index, bone)
    }

    /**
     * Returns the root bone index.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_root_bone
     */
    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getRootBoneBind, segment, index)
    }

    /**
     * Sets the middle bone name. Note: The middle bone must be a child of the root bone.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_middle_bone_name
     */
    fun setMiddleBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setMiddleBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the middle bone name.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_middle_bone_name
     */
    fun getMiddleBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getMiddleBoneNameBind, segment, index)
    }

    /**
     * Sets the middle bone index.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_middle_bone
     */
    fun setMiddleBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setMiddleBoneBind, segment, index, bone)
    }

    /**
     * Returns the middle bone index.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_middle_bone
     */
    fun getMiddleBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getMiddleBoneBind, segment, index)
    }

    /**
     * Sets the pole direction. The pole is on the middle bone and will direct to the pole target. The
     * rotation axis is a vector that is orthogonal to this and the forward vector. Note: The pole
     * direction and the forward vector shouldn't be colinear to avoid unintended rotation.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_pole_direction
     */
    fun setPoleDirection(index: Int, direction: SkeletonModifier3D.SecondaryDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setPoleDirectionBind, segment, index, direction.value)
    }

    /**
     * Returns the pole direction.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_pole_direction
     */
    fun getPoleDirection(index: Int): SkeletonModifier3D.SecondaryDirection {
        return SkeletonModifier3D.SecondaryDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getPoleDirectionBind, segment, index))
    }

    /**
     * Sets the pole direction vector. This vector is normalized by an internal process. If the vector
     * length is `0`, it is considered synonymous with `SkeletonModifier3D.SecondaryDirection.NONE`.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_pole_direction_vector
     */
    fun setPoleDirectionVector(index: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setPoleDirectionVectorBind, segment, index, vector)
    }

    /**
     * Returns the pole direction vector. If `get_pole_direction` is
     * `SkeletonModifier3D.SecondaryDirection.NONE`, this method returns `Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_pole_direction_vector
     */
    fun getPoleDirectionVector(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getPoleDirectionVectorBind, segment, index)
    }

    /**
     * Sets the end bone name. Note: The end bone must be a child of the middle bone.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_end_bone_name
     */
    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setEndBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the end bone name.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_end_bone_name
     */
    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getEndBoneNameBind, segment, index)
    }

    /**
     * Sets the end bone index.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_end_bone
     */
    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setEndBoneBind, segment, index, bone)
    }

    /**
     * Returns the end bone index.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_end_bone
     */
    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getEndBoneBind, segment, index)
    }

    /**
     * If `enabled` is `true`, the end bone is extended from the middle bone as a virtual bone.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_use_virtual_end
     */
    fun setUseVirtualEnd(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setUseVirtualEndBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the end bone is extended from the middle bone as a virtual bone.
     *
     * Generated from Godot docs: TwoBoneIK3D.is_using_virtual_end
     */
    fun isUsingVirtualEnd(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isUsingVirtualEndBind, segment, index)
    }

    /**
     * If `enabled` is `true`, the end bone is extended to have a tail.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_extend_end_bone
     */
    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setExtendEndBoneBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the end bone is extended to have a tail.
     *
     * Generated from Godot docs: TwoBoneIK3D.is_end_bone_extended
     */
    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isEndBoneExtendedBind, segment, index)
    }

    /**
     * Sets the end bone tail direction when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_end_bone_direction
     */
    fun setEndBoneDirection(index: Int, boneDirection: SkeletonModifier3D.BoneDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setEndBoneDirectionBind, segment, index, boneDirection.value)
    }

    /**
     * Returns the end bone's tail direction when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_end_bone_direction
     */
    fun getEndBoneDirection(index: Int): SkeletonModifier3D.BoneDirection {
        return SkeletonModifier3D.BoneDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getEndBoneDirectionBind, segment, index))
    }

    /**
     * Sets the end bone tail length when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: TwoBoneIK3D.set_end_bone_length
     */
    fun setEndBoneLength(index: Int, length: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setEndBoneLengthBind, segment, index, length)
    }

    /**
     * Returns the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: TwoBoneIK3D.get_end_bone_length
     */
    fun getEndBoneLength(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getEndBoneLengthBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TwoBoneIK3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TwoBoneIK3D? =
            if (handle.address() == 0L) null else TwoBoneIK3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TARGET_NODE_HASH = 2761262315L
        @JvmField
        val setTargetNodeBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_target_node", SET_TARGET_NODE_HASH)

        private const val GET_TARGET_NODE_HASH = 408788394L
        @JvmField
        val getTargetNodeBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_target_node", GET_TARGET_NODE_HASH)

        private const val SET_POLE_NODE_HASH = 2761262315L
        @JvmField
        val setPoleNodeBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_pole_node", SET_POLE_NODE_HASH)

        private const val GET_POLE_NODE_HASH = 408788394L
        @JvmField
        val getPoleNodeBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_node", GET_POLE_NODE_HASH)

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        @JvmField
        val setRootBoneNameBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        @JvmField
        val getRootBoneNameBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)

        private const val SET_ROOT_BONE_HASH = 3937882851L
        @JvmField
        val setRootBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_root_bone", SET_ROOT_BONE_HASH)

        private const val GET_ROOT_BONE_HASH = 923996154L
        @JvmField
        val getRootBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_root_bone", GET_ROOT_BONE_HASH)

        private const val SET_MIDDLE_BONE_NAME_HASH = 501894301L
        @JvmField
        val setMiddleBoneNameBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_middle_bone_name", SET_MIDDLE_BONE_NAME_HASH)

        private const val GET_MIDDLE_BONE_NAME_HASH = 844755477L
        @JvmField
        val getMiddleBoneNameBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_middle_bone_name", GET_MIDDLE_BONE_NAME_HASH)

        private const val SET_MIDDLE_BONE_HASH = 3937882851L
        @JvmField
        val setMiddleBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_middle_bone", SET_MIDDLE_BONE_HASH)

        private const val GET_MIDDLE_BONE_HASH = 923996154L
        @JvmField
        val getMiddleBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_middle_bone", GET_MIDDLE_BONE_HASH)

        private const val SET_POLE_DIRECTION_HASH = 258741388L
        @JvmField
        val setPoleDirectionBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_pole_direction", SET_POLE_DIRECTION_HASH)

        private const val GET_POLE_DIRECTION_HASH = 377522128L
        @JvmField
        val getPoleDirectionBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_direction", GET_POLE_DIRECTION_HASH)

        private const val SET_POLE_DIRECTION_VECTOR_HASH = 1530502735L
        @JvmField
        val setPoleDirectionVectorBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_pole_direction_vector", SET_POLE_DIRECTION_VECTOR_HASH)

        private const val GET_POLE_DIRECTION_VECTOR_HASH = 711720468L
        @JvmField
        val getPoleDirectionVectorBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_pole_direction_vector", GET_POLE_DIRECTION_VECTOR_HASH)

        private const val SET_END_BONE_NAME_HASH = 501894301L
        @JvmField
        val setEndBoneNameBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)

        private const val GET_END_BONE_NAME_HASH = 844755477L
        @JvmField
        val getEndBoneNameBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)

        private const val SET_END_BONE_HASH = 3937882851L
        @JvmField
        val setEndBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone", SET_END_BONE_HASH)

        private const val GET_END_BONE_HASH = 923996154L
        @JvmField
        val getEndBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone", GET_END_BONE_HASH)

        private const val SET_USE_VIRTUAL_END_HASH = 300928843L
        @JvmField
        val setUseVirtualEndBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_use_virtual_end", SET_USE_VIRTUAL_END_HASH)

        private const val IS_USING_VIRTUAL_END_HASH = 1116898809L
        @JvmField
        val isUsingVirtualEndBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "is_using_virtual_end", IS_USING_VIRTUAL_END_HASH)

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        @JvmField
        val setExtendEndBoneBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        @JvmField
        val isEndBoneExtendedBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        @JvmField
        val setEndBoneDirectionBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        @JvmField
        val getEndBoneDirectionBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)

        private const val SET_END_BONE_LENGTH_HASH = 1602489585L
        @JvmField
        val setEndBoneLengthBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "set_end_bone_length", SET_END_BONE_LENGTH_HASH)

        private const val GET_END_BONE_LENGTH_HASH = 2339986948L
        @JvmField
        val getEndBoneLengthBind =
            ObjectCalls.getMethodBind("TwoBoneIK3D", "get_end_bone_length", GET_END_BONE_LENGTH_HASH)
    }
}

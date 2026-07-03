package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A node that may modify Skeleton3D's bone with associating the two bones.
 *
 * Generated from Godot docs: BoneConstraint3D
 */
open class BoneConstraint3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    /**
     * Sets the apply amount of the setting at `index` to `amount`.
     *
     * Generated from Godot docs: BoneConstraint3D.set_amount
     */
    fun setAmount(index: Int, amount: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setAmountBind, handle, index, amount)
    }

    /**
     * Returns the apply amount of the setting at `index`.
     *
     * Generated from Godot docs: BoneConstraint3D.get_amount
     */
    fun getAmount(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getAmountBind, handle, index)
    }

    /**
     * Sets the apply bone of the setting at `index` to `bone_name`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.set_apply_bone_name
     */
    fun setApplyBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setApplyBoneNameBind, handle, index, boneName)
    }

    /**
     * Returns the apply bone name of the setting at `index`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.get_apply_bone_name
     */
    fun getApplyBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getApplyBoneNameBind, handle, index)
    }

    /**
     * Sets the apply bone of the setting at `index` to `bone`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.set_apply_bone
     */
    fun setApplyBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setApplyBoneBind, handle, index, bone)
    }

    /**
     * Returns the apply bone of the setting at `index`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.get_apply_bone
     */
    fun getApplyBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getApplyBoneBind, handle, index)
    }

    /**
     * Sets the reference target type of the setting at `index` to `type`. See also `ReferenceType`.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_type
     */
    fun setReferenceType(index: Int, type: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setReferenceTypeBind, handle, index, type)
    }

    /**
     * Returns the reference target type of the setting at `index`. See also `ReferenceType`.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_type
     */
    fun getReferenceType(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getReferenceTypeBind, handle, index)
    }

    /**
     * Sets the reference bone of the setting at `index` to `bone_name`. This bone will be only
     * referenced and not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_bone_name
     */
    fun setReferenceBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setReferenceBoneNameBind, handle, index, boneName)
    }

    /**
     * Returns the reference bone name of the setting at `index`. This bone will be only referenced and
     * not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_bone_name
     */
    fun getReferenceBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getReferenceBoneNameBind, handle, index)
    }

    /**
     * Sets the reference bone of the setting at `index` to `bone`. This bone will be only referenced
     * and not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_bone
     */
    fun setReferenceBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setReferenceBoneBind, handle, index, bone)
    }

    /**
     * Returns the reference bone of the setting at `index`. This bone will be only referenced and not
     * modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_bone
     */
    fun getReferenceBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getReferenceBoneBind, handle, index)
    }

    /**
     * Sets the reference node path of the setting at `index` to `node`. This node will be only
     * referenced and not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_node
     */
    fun setReferenceNode(index: Int, node: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setReferenceNodeBind, handle, index, node)
    }

    /**
     * Returns the reference node path of the setting at `index`. This node will be only referenced and
     * not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_node
     */
    fun getReferenceNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getReferenceNodeBind, handle, index)
    }

    /**
     * Sets the number of settings in the modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_setting_count
     */
    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setSettingCountBind, handle, count)
    }

    /**
     * Returns the number of settings in the modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_setting_count
     */
    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSettingCountBind, handle)
    }

    /**
     * Clear all settings.
     *
     * Generated from Godot docs: BoneConstraint3D.clear_setting
     */
    fun clearSetting() {
        ObjectCalls.ptrcallNoArgs(clearSettingBind, handle)
    }

    companion object {
        const val REFERENCE_TYPE_BONE: Long = 0L
        const val REFERENCE_TYPE_NODE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): BoneConstraint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoneConstraint3D? =
            if (handle.address() == 0L) null else BoneConstraint3D(handle)

        private const val SET_AMOUNT_HASH = 1602489585L
        private val setAmountBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_amount", SET_AMOUNT_HASH)
        }

        private const val GET_AMOUNT_HASH = 2339986948L
        private val getAmountBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_amount", GET_AMOUNT_HASH)
        }

        private const val SET_APPLY_BONE_NAME_HASH = 501894301L
        private val setApplyBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_apply_bone_name", SET_APPLY_BONE_NAME_HASH)
        }

        private const val GET_APPLY_BONE_NAME_HASH = 844755477L
        private val getApplyBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_apply_bone_name", GET_APPLY_BONE_NAME_HASH)
        }

        private const val SET_APPLY_BONE_HASH = 3937882851L
        private val setApplyBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_apply_bone", SET_APPLY_BONE_HASH)
        }

        private const val GET_APPLY_BONE_HASH = 923996154L
        private val getApplyBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_apply_bone", GET_APPLY_BONE_HASH)
        }

        private const val SET_REFERENCE_TYPE_HASH = 1830520418L
        private val setReferenceTypeBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_type", SET_REFERENCE_TYPE_HASH)
        }

        private const val GET_REFERENCE_TYPE_HASH = 3456416152L
        private val getReferenceTypeBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_type", GET_REFERENCE_TYPE_HASH)
        }

        private const val SET_REFERENCE_BONE_NAME_HASH = 501894301L
        private val setReferenceBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_bone_name", SET_REFERENCE_BONE_NAME_HASH)
        }

        private const val GET_REFERENCE_BONE_NAME_HASH = 844755477L
        private val getReferenceBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_bone_name", GET_REFERENCE_BONE_NAME_HASH)
        }

        private const val SET_REFERENCE_BONE_HASH = 3937882851L
        private val setReferenceBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_bone", SET_REFERENCE_BONE_HASH)
        }

        private const val GET_REFERENCE_BONE_HASH = 923996154L
        private val getReferenceBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_bone", GET_REFERENCE_BONE_HASH)
        }

        private const val SET_REFERENCE_NODE_HASH = 2761262315L
        private val setReferenceNodeBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_node", SET_REFERENCE_NODE_HASH)
        }

        private const val GET_REFERENCE_NODE_HASH = 408788394L
        private val getReferenceNodeBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_node", GET_REFERENCE_NODE_HASH)
        }

        private const val SET_SETTING_COUNT_HASH = 1286410249L
        private val setSettingCountBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_setting_count", SET_SETTING_COUNT_HASH)
        }

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        private val getSettingCountBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_setting_count", GET_SETTING_COUNT_HASH)
        }

        private const val CLEAR_SETTING_HASH = 3218959716L
        private val clearSettingBind by lazy {
            ObjectCalls.getMethodBind("BoneConstraint3D", "clear_setting", CLEAR_SETTING_HASH)
        }
    }
}

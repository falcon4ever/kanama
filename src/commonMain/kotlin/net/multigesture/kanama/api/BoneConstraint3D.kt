package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A node that may modify Skeleton3D's bone with associating the two bones.
 *
 * Generated from Godot docs: BoneConstraint3D
 */
open class BoneConstraint3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    /**
     * Sets the apply amount of the setting at `index` to `amount`.
     *
     * Generated from Godot docs: BoneConstraint3D.set_amount
     */
    fun setAmount(index: Int, amount: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setAmountBind, segment, index, amount)
    }

    /**
     * Returns the apply amount of the setting at `index`.
     *
     * Generated from Godot docs: BoneConstraint3D.get_amount
     */
    fun getAmount(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getAmountBind, segment, index)
    }

    /**
     * Sets the apply bone of the setting at `index` to `bone_name`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.set_apply_bone_name
     */
    fun setApplyBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setApplyBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the apply bone name of the setting at `index`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.get_apply_bone_name
     */
    fun getApplyBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getApplyBoneNameBind, segment, index)
    }

    /**
     * Sets the apply bone of the setting at `index` to `bone`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.set_apply_bone
     */
    fun setApplyBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setApplyBoneBind, segment, index, bone)
    }

    /**
     * Returns the apply bone of the setting at `index`. This bone will be modified.
     *
     * Generated from Godot docs: BoneConstraint3D.get_apply_bone
     */
    fun getApplyBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getApplyBoneBind, segment, index)
    }

    /**
     * Sets the reference target type of the setting at `index` to `type`. See also `ReferenceType`.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_type
     */
    fun setReferenceType(index: Int, type: BoneConstraint3D.ReferenceType) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setReferenceTypeBind, segment, index, type.value)
    }

    /**
     * Returns the reference target type of the setting at `index`. See also `ReferenceType`.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_type
     */
    fun getReferenceType(index: Int): BoneConstraint3D.ReferenceType {
        return BoneConstraint3D.ReferenceType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getReferenceTypeBind, segment, index))
    }

    /**
     * Sets the reference bone of the setting at `index` to `bone_name`. This bone will be only
     * referenced and not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_bone_name
     */
    fun setReferenceBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setReferenceBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the reference bone name of the setting at `index`. This bone will be only referenced and
     * not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_bone_name
     */
    fun getReferenceBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getReferenceBoneNameBind, segment, index)
    }

    /**
     * Sets the reference bone of the setting at `index` to `bone`. This bone will be only referenced
     * and not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_bone
     */
    fun setReferenceBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setReferenceBoneBind, segment, index, bone)
    }

    /**
     * Returns the reference bone of the setting at `index`. This bone will be only referenced and not
     * modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_bone
     */
    fun getReferenceBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getReferenceBoneBind, segment, index)
    }

    /**
     * Sets the reference node path of the setting at `index` to `node`. This node will be only
     * referenced and not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_reference_node
     */
    fun setReferenceNode(index: Int, node: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setReferenceNodeBind, segment, index, node)
    }

    /**
     * Returns the reference node path of the setting at `index`. This node will be only referenced and
     * not modified by this modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_reference_node
     */
    fun getReferenceNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getReferenceNodeBind, segment, index)
    }

    /**
     * Sets the number of settings in the modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.set_setting_count
     */
    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSettingCountBind, segment, count)
    }

    /**
     * Returns the number of settings in the modifier.
     *
     * Generated from Godot docs: BoneConstraint3D.get_setting_count
     */
    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSettingCountBind, segment)
    }

    /**
     * Clear all settings.
     *
     * Generated from Godot docs: BoneConstraint3D.clear_setting
     */
    fun clearSetting() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSettingBind, segment)
    }

    /**
     * Godot's `BoneConstraint3D.ReferenceType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`BoneConstraint3D.ReferenceType.<NAME>`).
     *
     * Generated from Godot docs: BoneConstraint3D.ReferenceType
     */
    @JvmInline
    value class ReferenceType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The reference target is a bone. In this case, the reference target spaces is local space.
             *
             * Generated from Godot docs: BoneConstraint3D.REFERENCE_TYPE_BONE
             */
            val BONE: ReferenceType get() = ReferenceType(0L)
            /**
             * The reference target is a `Node3D`. In this case, the reference target spaces is model space. In
             * other words, the reference target's coordinates are treated as if it were placed directly under
             * `Skeleton3D` which parent of the `BoneConstraint3D`.
             *
             * Generated from Godot docs: BoneConstraint3D.REFERENCE_TYPE_NODE
             */
            val NODE: ReferenceType get() = ReferenceType(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoneConstraint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BoneConstraint3D? =
            if (handle.address() == 0L) null else BoneConstraint3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_AMOUNT_HASH = 1602489585L
        @JvmField
        val setAmountBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_amount", SET_AMOUNT_HASH)

        private const val GET_AMOUNT_HASH = 2339986948L
        @JvmField
        val getAmountBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_amount", GET_AMOUNT_HASH)

        private const val SET_APPLY_BONE_NAME_HASH = 501894301L
        @JvmField
        val setApplyBoneNameBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_apply_bone_name", SET_APPLY_BONE_NAME_HASH)

        private const val GET_APPLY_BONE_NAME_HASH = 844755477L
        @JvmField
        val getApplyBoneNameBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_apply_bone_name", GET_APPLY_BONE_NAME_HASH)

        private const val SET_APPLY_BONE_HASH = 3937882851L
        @JvmField
        val setApplyBoneBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_apply_bone", SET_APPLY_BONE_HASH)

        private const val GET_APPLY_BONE_HASH = 923996154L
        @JvmField
        val getApplyBoneBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_apply_bone", GET_APPLY_BONE_HASH)

        private const val SET_REFERENCE_TYPE_HASH = 1830520418L
        @JvmField
        val setReferenceTypeBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_type", SET_REFERENCE_TYPE_HASH)

        private const val GET_REFERENCE_TYPE_HASH = 3456416152L
        @JvmField
        val getReferenceTypeBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_type", GET_REFERENCE_TYPE_HASH)

        private const val SET_REFERENCE_BONE_NAME_HASH = 501894301L
        @JvmField
        val setReferenceBoneNameBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_bone_name", SET_REFERENCE_BONE_NAME_HASH)

        private const val GET_REFERENCE_BONE_NAME_HASH = 844755477L
        @JvmField
        val getReferenceBoneNameBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_bone_name", GET_REFERENCE_BONE_NAME_HASH)

        private const val SET_REFERENCE_BONE_HASH = 3937882851L
        @JvmField
        val setReferenceBoneBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_bone", SET_REFERENCE_BONE_HASH)

        private const val GET_REFERENCE_BONE_HASH = 923996154L
        @JvmField
        val getReferenceBoneBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_bone", GET_REFERENCE_BONE_HASH)

        private const val SET_REFERENCE_NODE_HASH = 2761262315L
        @JvmField
        val setReferenceNodeBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_reference_node", SET_REFERENCE_NODE_HASH)

        private const val GET_REFERENCE_NODE_HASH = 408788394L
        @JvmField
        val getReferenceNodeBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_reference_node", GET_REFERENCE_NODE_HASH)

        private const val SET_SETTING_COUNT_HASH = 1286410249L
        @JvmField
        val setSettingCountBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "set_setting_count", SET_SETTING_COUNT_HASH)

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        @JvmField
        val getSettingCountBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "get_setting_count", GET_SETTING_COUNT_HASH)

        private const val CLEAR_SETTING_HASH = 3218959716L
        @JvmField
        val clearSettingBind =
            ObjectCalls.getMethodBind("BoneConstraint3D", "clear_setting", CLEAR_SETTING_HASH)
    }
}

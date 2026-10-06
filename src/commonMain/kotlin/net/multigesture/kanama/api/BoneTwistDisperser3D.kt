package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Quaternion

/**
 * A node that propagates and disperses the child bone's twist to the parent bones.
 *
 * Generated from Godot docs: BoneTwistDisperser3D
 */
class BoneTwistDisperser3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    var mutableBoneAxes: Boolean
        @JvmName("mutableBoneAxesProperty")
        get() = areBoneAxesMutable()
        @JvmName("setMutableBoneAxesProperty")
        set(value) = setMutableBoneAxes(value)

    var settingCount: Int
        @JvmName("settingCountProperty")
        get() = getSettingCount()
        @JvmName("setSettingCountProperty")
        set(value) = setSettingCount(value)

    /**
     * The number of settings.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_setting_count
     */
    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSettingCountBind, segment, count)
    }

    /**
     * The number of settings.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_setting_count
     */
    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSettingCountBind, segment)
    }

    /**
     * Clears all settings.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.clear_settings
     */
    fun clearSettings() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSettingsBind, segment)
    }

    /**
     * If `true`, the solver retrieves the bone axis from the bone pose every frame. If `false`, the
     * solver retrieves the bone axis from the bone rest and caches it.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_mutable_bone_axes
     */
    fun setMutableBoneAxes(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMutableBoneAxesBind, segment, enabled)
    }

    /**
     * If `true`, the solver retrieves the bone axis from the bone pose every frame. If `false`, the
     * solver retrieves the bone axis from the bone rest and caches it.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.are_bone_axes_mutable
     */
    fun areBoneAxesMutable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.areBoneAxesMutableBind, segment)
    }

    /**
     * Sets the root bone name of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_root_bone_name
     */
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setRootBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the root bone name of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_root_bone_name
     */
    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getRootBoneNameBind, segment, index)
    }

    /**
     * Sets the root bone index of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_root_bone
     */
    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setRootBoneBind, segment, index, bone)
    }

    /**
     * Returns the root bone index of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_root_bone
     */
    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getRootBoneBind, segment, index)
    }

    /**
     * Sets the end bone name of the bone chain. Note: The end bone must be a child of the root bone.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_end_bone_name
     */
    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setEndBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the end bone name of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_end_bone_name
     */
    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getEndBoneNameBind, segment, index)
    }

    /**
     * Sets the end bone index of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_end_bone
     */
    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setEndBoneBind, segment, index, bone)
    }

    /**
     * Returns the end bone index of the bone chain.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_end_bone
     */
    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getEndBoneBind, segment, index)
    }

    /**
     * Returns the reference bone name to extract twist of the setting at `index`. This bone is either
     * the end of the chain or its parent, depending on `is_end_bone_extended`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_reference_bone_name
     */
    fun getReferenceBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getReferenceBoneNameBind, segment, index)
    }

    /**
     * Returns the reference bone to extract twist of the setting at `index`. This bone is either the
     * end of the chain or its parent, depending on `is_end_bone_extended`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_reference_bone
     */
    fun getReferenceBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getReferenceBoneBind, segment, index)
    }

    /**
     * If `enabled` is `true`, the end bone is extended to have a tail. If `enabled` is `false`,
     * `get_reference_bone` becomes a parent of the end bone and it uses the vector to the end bone as
     * a twist axis.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_extend_end_bone
     */
    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setExtendEndBoneBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the end bone is extended to have a tail.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.is_end_bone_extended
     */
    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isEndBoneExtendedBind, segment, index)
    }

    /**
     * Sets the end bone tail direction of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_end_bone_direction
     */
    fun setEndBoneDirection(index: Int, boneDirection: SkeletonModifier3D.BoneDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setEndBoneDirectionBind, segment, index, boneDirection.value)
    }

    /**
     * Returns the tail direction of the end bone of the bone chain when `is_end_bone_extended` is
     * `true`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_end_bone_direction
     */
    fun getEndBoneDirection(index: Int): SkeletonModifier3D.BoneDirection {
        return SkeletonModifier3D.BoneDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getEndBoneDirectionBind, segment, index))
    }

    /**
     * If `enabled` is `true`, it extracts the twist amount from the difference between the bone rest
     * and the current bone pose. If `enabled` is `false`, it extracts the twist amount from the
     * difference between `get_twist_from` and the current bone pose. See also `set_twist_from`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_twist_from_rest
     */
    fun setTwistFromRest(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setTwistFromRestBind, segment, index, enabled)
    }

    /**
     * Returns `true` if extracting the twist amount from the difference between the bone rest and the
     * current bone pose.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.is_twist_from_rest
     */
    fun isTwistFromRest(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isTwistFromRestBind, segment, index)
    }

    /**
     * Sets the rotation to an arbitrary state before twisting for the current bone pose to extract the
     * twist when `is_twist_from_rest` is `false`. In other words, by calling `set_twist_from` by
     * `SkeletonModifier3D.modification_processed` of a specific `SkeletonModifier3D`, you can extract
     * only the twists generated by modifiers processed after that but before this
     * `BoneTwistDisperser3D`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_twist_from
     */
    fun setTwistFrom(index: Int, from: Quaternion) {
        ObjectCalls.ptrcallWithIntAndQuaternionArg(Binds.setTwistFromBind, segment, index, from)
    }

    /**
     * Returns the rotation to an arbitrary state before twisting for the current bone pose to extract
     * the twist when `is_twist_from_rest` is `false`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_twist_from
     */
    fun getTwistFrom(index: Int): Quaternion {
        return ObjectCalls.ptrcallWithIntArgRetQuaternion(Binds.getTwistFromBind, segment, index)
    }

    /**
     * Sets whether to use automatic amount assignment or to allow manual assignment.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_disperse_mode
     */
    fun setDisperseMode(index: Int, disperseMode: BoneTwistDisperser3D.DisperseMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setDisperseModeBind, segment, index, disperseMode.value)
    }

    /**
     * Returns whether to use automatic amount assignment or to allow manual assignment.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_disperse_mode
     */
    fun getDisperseMode(index: Int): BoneTwistDisperser3D.DisperseMode {
        return BoneTwistDisperser3D.DisperseMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getDisperseModeBind, segment, index))
    }

    /**
     * Sets the position at which to divide the segment between joints for weight assignment when
     * `get_disperse_mode` is `DisperseMode.WEIGHTED`. For example, when `weight_position` is `0.5`, if
     * two bone segments with a length of `1.0` exist between three joints, weights are assigned to
     * each joint from root to end at ratios of `0.5`, `1.0`, and `0.5`. Then amounts become `0.25`,
     * `0.75`, and `1.0` respectively.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_weight_position
     */
    fun setWeightPosition(index: Int, weightPosition: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setWeightPositionBind, segment, index, weightPosition)
    }

    /**
     * Returns the position at which to divide the segment between joints for weight assignment when
     * `get_disperse_mode` is `DisperseMode.WEIGHTED`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_weight_position
     */
    fun getWeightPosition(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getWeightPositionBind, segment, index)
    }

    /**
     * Sets the damping curve when `get_disperse_mode` is `DisperseMode.CUSTOM`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_damping_curve
     */
    fun setDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setDampingCurveBind, segment, index, curve?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the damping curve when `get_disperse_mode` is `DisperseMode.CUSTOM`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_damping_curve
     */
    fun getDampingCurve(index: Int): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getDampingCurveBind, segment, index))
    }

    /**
     * Returns the bone name at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_joint_bone_name
     */
    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(Binds.getJointBoneNameBind, segment, index, joint)
    }

    /**
     * Returns the bone index at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_joint_bone
     */
    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getJointBoneBind, segment, index, joint)
    }

    /**
     * Returns the twist amount at `joint` in the bone chain's joint list when `get_disperse_mode` is
     * `DisperseMode.CUSTOM`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_joint_twist_amount
     */
    fun getJointTwistAmount(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getJointTwistAmountBind, segment, index, joint)
    }

    /**
     * Sets the twist amount at `joint` in the bone chain's joint list when `get_disperse_mode` is
     * `DisperseMode.CUSTOM`.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.set_joint_twist_amount
     */
    fun setJointTwistAmount(index: Int, joint: Int, twistAmount: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setJointTwistAmountBind, segment, index, joint, twistAmount)
    }

    /**
     * Returns the joint count of the bone chain's joint list.
     *
     * Generated from Godot docs: BoneTwistDisperser3D.get_joint_count
     */
    fun getJointCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getJointCountBind, segment, index)
    }

    /**
     * Godot's `BoneTwistDisperser3D.DisperseMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`BoneTwistDisperser3D.DisperseMode.<NAME>`).
     *
     * Generated from Godot docs: BoneTwistDisperser3D.DisperseMode
     */
    @JvmInline
    value class DisperseMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Assign amounts so that they monotonically increase from `0.0` to `1.0`, ensuring all weights are
             * equal. For example, with five joints, the amounts would be `0.2`, `0.4`, `0.6`, `0.8`, and `1.0`
             * starting from the root bone.
             *
             * Generated from Godot docs: BoneTwistDisperser3D.DISPERSE_MODE_EVEN
             */
            val EVEN: DisperseMode get() = DisperseMode(0L)
            /**
             * Assign amounts so that they monotonically increase from `0.0` to `1.0`, based on the length of
             * the bones between joint segments. See also `set_weight_position`.
             *
             * Generated from Godot docs: BoneTwistDisperser3D.DISPERSE_MODE_WEIGHTED
             */
            val WEIGHTED: DisperseMode get() = DisperseMode(1L)
            /**
             * You can assign arbitrary amounts to the joint list. See also `set_joint_twist_amount`. When
             * `is_end_bone_extended` is `false`, a child of the reference bone exists solely to determine the
             * twist axis, so its custom amount has absolutely no effect at all.
             *
             * Generated from Godot docs: BoneTwistDisperser3D.DISPERSE_MODE_CUSTOM
             */
            val CUSTOM: DisperseMode get() = DisperseMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoneTwistDisperser3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BoneTwistDisperser3D? =
            if (handle.address() == 0L) null else BoneTwistDisperser3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SETTING_COUNT_HASH = 1286410249L
        @JvmField
        val setSettingCountBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_setting_count", SET_SETTING_COUNT_HASH)

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        @JvmField
        val getSettingCountBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_setting_count", GET_SETTING_COUNT_HASH)

        private const val CLEAR_SETTINGS_HASH = 3218959716L
        @JvmField
        val clearSettingsBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "clear_settings", CLEAR_SETTINGS_HASH)

        private const val SET_MUTABLE_BONE_AXES_HASH = 2586408642L
        @JvmField
        val setMutableBoneAxesBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_mutable_bone_axes", SET_MUTABLE_BONE_AXES_HASH)

        private const val ARE_BONE_AXES_MUTABLE_HASH = 36873697L
        @JvmField
        val areBoneAxesMutableBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "are_bone_axes_mutable", ARE_BONE_AXES_MUTABLE_HASH)

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        @JvmField
        val setRootBoneNameBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        @JvmField
        val getRootBoneNameBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)

        private const val SET_ROOT_BONE_HASH = 3937882851L
        @JvmField
        val setRootBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_root_bone", SET_ROOT_BONE_HASH)

        private const val GET_ROOT_BONE_HASH = 923996154L
        @JvmField
        val getRootBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_root_bone", GET_ROOT_BONE_HASH)

        private const val SET_END_BONE_NAME_HASH = 501894301L
        @JvmField
        val setEndBoneNameBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)

        private const val GET_END_BONE_NAME_HASH = 844755477L
        @JvmField
        val getEndBoneNameBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)

        private const val SET_END_BONE_HASH = 3937882851L
        @JvmField
        val setEndBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_end_bone", SET_END_BONE_HASH)

        private const val GET_END_BONE_HASH = 923996154L
        @JvmField
        val getEndBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone", GET_END_BONE_HASH)

        private const val GET_REFERENCE_BONE_NAME_HASH = 844755477L
        @JvmField
        val getReferenceBoneNameBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_reference_bone_name", GET_REFERENCE_BONE_NAME_HASH)

        private const val GET_REFERENCE_BONE_HASH = 923996154L
        @JvmField
        val getReferenceBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_reference_bone", GET_REFERENCE_BONE_HASH)

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        @JvmField
        val setExtendEndBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        @JvmField
        val isEndBoneExtendedBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        @JvmField
        val setEndBoneDirectionBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        @JvmField
        val getEndBoneDirectionBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)

        private const val SET_TWIST_FROM_REST_HASH = 300928843L
        @JvmField
        val setTwistFromRestBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_twist_from_rest", SET_TWIST_FROM_REST_HASH)

        private const val IS_TWIST_FROM_REST_HASH = 1116898809L
        @JvmField
        val isTwistFromRestBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "is_twist_from_rest", IS_TWIST_FROM_REST_HASH)

        private const val SET_TWIST_FROM_HASH = 2823819782L
        @JvmField
        val setTwistFromBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_twist_from", SET_TWIST_FROM_HASH)

        private const val GET_TWIST_FROM_HASH = 476865136L
        @JvmField
        val getTwistFromBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_twist_from", GET_TWIST_FROM_HASH)

        private const val SET_DISPERSE_MODE_HASH = 2954194337L
        @JvmField
        val setDisperseModeBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_disperse_mode", SET_DISPERSE_MODE_HASH)

        private const val GET_DISPERSE_MODE_HASH = 1326397005L
        @JvmField
        val getDisperseModeBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_disperse_mode", GET_DISPERSE_MODE_HASH)

        private const val SET_WEIGHT_POSITION_HASH = 1602489585L
        @JvmField
        val setWeightPositionBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_weight_position", SET_WEIGHT_POSITION_HASH)

        private const val GET_WEIGHT_POSITION_HASH = 2339986948L
        @JvmField
        val getWeightPositionBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_weight_position", GET_WEIGHT_POSITION_HASH)

        private const val SET_DAMPING_CURVE_HASH = 1447180063L
        @JvmField
        val setDampingCurveBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_damping_curve", SET_DAMPING_CURVE_HASH)

        private const val GET_DAMPING_CURVE_HASH = 747537754L
        @JvmField
        val getDampingCurveBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_damping_curve", GET_DAMPING_CURVE_HASH)

        private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
        @JvmField
        val getJointBoneNameBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)

        private const val GET_JOINT_BONE_HASH = 3175239445L
        @JvmField
        val getJointBoneBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_bone", GET_JOINT_BONE_HASH)

        private const val GET_JOINT_TWIST_AMOUNT_HASH = 3085491603L
        @JvmField
        val getJointTwistAmountBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_twist_amount", GET_JOINT_TWIST_AMOUNT_HASH)

        private const val SET_JOINT_TWIST_AMOUNT_HASH = 3506521499L
        @JvmField
        val setJointTwistAmountBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_joint_twist_amount", SET_JOINT_TWIST_AMOUNT_HASH)

        private const val GET_JOINT_COUNT_HASH = 923996154L
        @JvmField
        val getJointCountBind =
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_count", GET_JOINT_COUNT_HASH)
    }
}

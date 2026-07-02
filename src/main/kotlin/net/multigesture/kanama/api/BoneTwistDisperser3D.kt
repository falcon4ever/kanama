package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Quaternion

/**
 * A node that propagates and disperses the child bone's twist to the parent bones.
 *
 * Generated from Godot docs: BoneTwistDisperser3D
 */
class BoneTwistDisperser3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
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

    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setSettingCountBind, handle, count)
    }

    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSettingCountBind, handle)
    }

    fun clearSettings() {
        ObjectCalls.ptrcallNoArgs(clearSettingsBind, handle)
    }

    fun setMutableBoneAxes(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMutableBoneAxesBind, handle, enabled)
    }

    fun areBoneAxesMutable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areBoneAxesMutableBind, handle)
    }

    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setRootBoneNameBind, handle, index, boneName)
    }

    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
    }

    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setRootBoneBind, handle, index, bone)
    }

    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getRootBoneBind, handle, index)
    }

    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setEndBoneNameBind, handle, index, boneName)
    }

    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
    }

    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setEndBoneBind, handle, index, bone)
    }

    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getEndBoneBind, handle, index)
    }

    fun getReferenceBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getReferenceBoneNameBind, handle, index)
    }

    fun getReferenceBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getReferenceBoneBind, handle, index)
    }

    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setExtendEndBoneBind, handle, index, enabled)
    }

    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isEndBoneExtendedBind, handle, index)
    }

    fun setEndBoneDirection(index: Int, boneDirection: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setEndBoneDirectionBind, handle, index, boneDirection)
    }

    fun getEndBoneDirection(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getEndBoneDirectionBind, handle, index)
    }

    fun setTwistFromRest(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTwistFromRestBind, handle, index, enabled)
    }

    fun isTwistFromRest(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTwistFromRestBind, handle, index)
    }

    fun setTwistFrom(index: Int, from: Quaternion) {
        ObjectCalls.ptrcallWithIntAndQuaternionArg(setTwistFromBind, handle, index, from)
    }

    fun getTwistFrom(index: Int): Quaternion {
        return ObjectCalls.ptrcallWithIntArgRetQuaternion(getTwistFromBind, handle, index)
    }

    fun setDisperseMode(index: Int, disperseMode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setDisperseModeBind, handle, index, disperseMode)
    }

    fun getDisperseMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getDisperseModeBind, handle, index)
    }

    fun setWeightPosition(index: Int, weightPosition: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setWeightPositionBind, handle, index, weightPosition)
    }

    fun getWeightPosition(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getWeightPositionBind, handle, index)
    }

    fun setDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setDampingCurveBind, handle, index, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getDampingCurve(index: Int): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getDampingCurveBind, handle, index))
    }

    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
    }

    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getJointBoneBind, handle, index, joint)
    }

    fun getJointTwistAmount(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getJointTwistAmountBind, handle, index, joint)
    }

    fun setJointTwistAmount(index: Int, joint: Int, twistAmount: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setJointTwistAmountBind, handle, index, joint, twistAmount)
    }

    fun getJointCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getJointCountBind, handle, index)
    }

    companion object {
        const val DISPERSE_MODE_EVEN: Long = 0L
        const val DISPERSE_MODE_WEIGHTED: Long = 1L
        const val DISPERSE_MODE_CUSTOM: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): BoneTwistDisperser3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoneTwistDisperser3D? =
            if (handle.address() == 0L) null else BoneTwistDisperser3D(handle)

        private const val SET_SETTING_COUNT_HASH = 1286410249L
        private val setSettingCountBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_setting_count", SET_SETTING_COUNT_HASH)
        }

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        private val getSettingCountBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_setting_count", GET_SETTING_COUNT_HASH)
        }

        private const val CLEAR_SETTINGS_HASH = 3218959716L
        private val clearSettingsBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "clear_settings", CLEAR_SETTINGS_HASH)
        }

        private const val SET_MUTABLE_BONE_AXES_HASH = 2586408642L
        private val setMutableBoneAxesBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_mutable_bone_axes", SET_MUTABLE_BONE_AXES_HASH)
        }

        private const val ARE_BONE_AXES_MUTABLE_HASH = 36873697L
        private val areBoneAxesMutableBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "are_bone_axes_mutable", ARE_BONE_AXES_MUTABLE_HASH)
        }

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        private val setRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)
        }

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        private val getRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
        }

        private const val SET_ROOT_BONE_HASH = 3937882851L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 923996154L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_END_BONE_NAME_HASH = 501894301L
        private val setEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)
        }

        private const val GET_END_BONE_NAME_HASH = 844755477L
        private val getEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
        }

        private const val SET_END_BONE_HASH = 3937882851L
        private val setEndBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_end_bone", SET_END_BONE_HASH)
        }

        private const val GET_END_BONE_HASH = 923996154L
        private val getEndBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone", GET_END_BONE_HASH)
        }

        private const val GET_REFERENCE_BONE_NAME_HASH = 844755477L
        private val getReferenceBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_reference_bone_name", GET_REFERENCE_BONE_NAME_HASH)
        }

        private const val GET_REFERENCE_BONE_HASH = 923996154L
        private val getReferenceBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_reference_bone", GET_REFERENCE_BONE_HASH)
        }

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        private val setExtendEndBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)
        }

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        private val isEndBoneExtendedBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)
        }

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        private val setEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)
        }

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        private val getEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)
        }

        private const val SET_TWIST_FROM_REST_HASH = 300928843L
        private val setTwistFromRestBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_twist_from_rest", SET_TWIST_FROM_REST_HASH)
        }

        private const val IS_TWIST_FROM_REST_HASH = 1116898809L
        private val isTwistFromRestBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "is_twist_from_rest", IS_TWIST_FROM_REST_HASH)
        }

        private const val SET_TWIST_FROM_HASH = 2823819782L
        private val setTwistFromBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_twist_from", SET_TWIST_FROM_HASH)
        }

        private const val GET_TWIST_FROM_HASH = 476865136L
        private val getTwistFromBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_twist_from", GET_TWIST_FROM_HASH)
        }

        private const val SET_DISPERSE_MODE_HASH = 2954194337L
        private val setDisperseModeBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_disperse_mode", SET_DISPERSE_MODE_HASH)
        }

        private const val GET_DISPERSE_MODE_HASH = 1326397005L
        private val getDisperseModeBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_disperse_mode", GET_DISPERSE_MODE_HASH)
        }

        private const val SET_WEIGHT_POSITION_HASH = 1602489585L
        private val setWeightPositionBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_weight_position", SET_WEIGHT_POSITION_HASH)
        }

        private const val GET_WEIGHT_POSITION_HASH = 2339986948L
        private val getWeightPositionBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_weight_position", GET_WEIGHT_POSITION_HASH)
        }

        private const val SET_DAMPING_CURVE_HASH = 1447180063L
        private val setDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_damping_curve", SET_DAMPING_CURVE_HASH)
        }

        private const val GET_DAMPING_CURVE_HASH = 747537754L
        private val getDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_damping_curve", GET_DAMPING_CURVE_HASH)
        }

        private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
        private val getJointBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)
        }

        private const val GET_JOINT_BONE_HASH = 3175239445L
        private val getJointBoneBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_bone", GET_JOINT_BONE_HASH)
        }

        private const val GET_JOINT_TWIST_AMOUNT_HASH = 3085491603L
        private val getJointTwistAmountBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_twist_amount", GET_JOINT_TWIST_AMOUNT_HASH)
        }

        private const val SET_JOINT_TWIST_AMOUNT_HASH = 3506521499L
        private val setJointTwistAmountBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "set_joint_twist_amount", SET_JOINT_TWIST_AMOUNT_HASH)
        }

        private const val GET_JOINT_COUNT_HASH = 923996154L
        private val getJointCountBind by lazy {
            ObjectCalls.getMethodBind("BoneTwistDisperser3D", "get_joint_count", GET_JOINT_COUNT_HASH)
        }
    }
}

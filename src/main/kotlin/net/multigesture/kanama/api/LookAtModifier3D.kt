package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3

/**
 * The `LookAtModifier3D` rotates a bone to look at a target.
 *
 * Generated from Godot docs: LookAtModifier3D
 */
class LookAtModifier3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var targetNode: NodePath
        @JvmName("targetNodeProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodeProperty")
        set(value) = setTargetNode(value)

    var boneName: String
        @JvmName("boneNameProperty")
        get() = getBoneName()
        @JvmName("setBoneNameProperty")
        set(value) = setBoneName(value)

    var bone: Int
        @JvmName("boneProperty")
        get() = getBone()
        @JvmName("setBoneProperty")
        set(value) = setBone(value)

    var forwardAxis: Long
        @JvmName("forwardAxisProperty")
        get() = getForwardAxis()
        @JvmName("setForwardAxisProperty")
        set(value) = setForwardAxis(value)

    var primaryRotationAxis: Long
        @JvmName("primaryRotationAxisProperty")
        get() = getPrimaryRotationAxis()
        @JvmName("setPrimaryRotationAxisProperty")
        set(value) = setPrimaryRotationAxis(value)

    var useSecondaryRotation: Boolean
        @JvmName("useSecondaryRotationProperty")
        get() = isUsingSecondaryRotation()
        @JvmName("setUseSecondaryRotationProperty")
        set(value) = setUseSecondaryRotation(value)

    var relative: Boolean
        @JvmName("relativeProperty")
        get() = isRelative()
        @JvmName("setRelativeProperty")
        set(value) = setRelative(value)

    var originFrom: Long
        @JvmName("originFromProperty")
        get() = getOriginFrom()
        @JvmName("setOriginFromProperty")
        set(value) = setOriginFrom(value)

    var originBoneName: String
        @JvmName("originBoneNameProperty")
        get() = getOriginBoneName()
        @JvmName("setOriginBoneNameProperty")
        set(value) = setOriginBoneName(value)

    var originBone: Int
        @JvmName("originBoneProperty")
        get() = getOriginBone()
        @JvmName("setOriginBoneProperty")
        set(value) = setOriginBone(value)

    var originExternalNode: NodePath
        @JvmName("originExternalNodeProperty")
        get() = getOriginExternalNode()
        @JvmName("setOriginExternalNodeProperty")
        set(value) = setOriginExternalNode(value)

    var originOffset: Vector3
        @JvmName("originOffsetProperty")
        get() = getOriginOffset()
        @JvmName("setOriginOffsetProperty")
        set(value) = setOriginOffset(value)

    var originSafeMargin: Double
        @JvmName("originSafeMarginProperty")
        get() = getOriginSafeMargin()
        @JvmName("setOriginSafeMarginProperty")
        set(value) = setOriginSafeMargin(value)

    var duration: Double
        @JvmName("durationProperty")
        get() = getDuration()
        @JvmName("setDurationProperty")
        set(value) = setDuration(value)

    var transitionType: Long
        @JvmName("transitionTypeProperty")
        get() = getTransitionType()
        @JvmName("setTransitionTypeProperty")
        set(value) = setTransitionType(value)

    var easeType: Long
        @JvmName("easeTypeProperty")
        get() = getEaseType()
        @JvmName("setEaseTypeProperty")
        set(value) = setEaseType(value)

    var useAngleLimitation: Boolean
        @JvmName("useAngleLimitationProperty")
        get() = isUsingAngleLimitation()
        @JvmName("setUseAngleLimitationProperty")
        set(value) = setUseAngleLimitation(value)

    var symmetryLimitation: Boolean
        @JvmName("symmetryLimitationProperty")
        get() = isLimitationSymmetry()
        @JvmName("setSymmetryLimitationProperty")
        set(value) = setSymmetryLimitation(value)

    var primaryLimitAngle: Double
        @JvmName("primaryLimitAngleProperty")
        get() = getPrimaryLimitAngle()
        @JvmName("setPrimaryLimitAngleProperty")
        set(value) = setPrimaryLimitAngle(value)

    var primaryDampThreshold: Double
        @JvmName("primaryDampThresholdProperty")
        get() = getPrimaryDampThreshold()
        @JvmName("setPrimaryDampThresholdProperty")
        set(value) = setPrimaryDampThreshold(value)

    var primaryPositiveLimitAngle: Double
        @JvmName("primaryPositiveLimitAngleProperty")
        get() = getPrimaryPositiveLimitAngle()
        @JvmName("setPrimaryPositiveLimitAngleProperty")
        set(value) = setPrimaryPositiveLimitAngle(value)

    var primaryPositiveDampThreshold: Double
        @JvmName("primaryPositiveDampThresholdProperty")
        get() = getPrimaryPositiveDampThreshold()
        @JvmName("setPrimaryPositiveDampThresholdProperty")
        set(value) = setPrimaryPositiveDampThreshold(value)

    var primaryNegativeLimitAngle: Double
        @JvmName("primaryNegativeLimitAngleProperty")
        get() = getPrimaryNegativeLimitAngle()
        @JvmName("setPrimaryNegativeLimitAngleProperty")
        set(value) = setPrimaryNegativeLimitAngle(value)

    var primaryNegativeDampThreshold: Double
        @JvmName("primaryNegativeDampThresholdProperty")
        get() = getPrimaryNegativeDampThreshold()
        @JvmName("setPrimaryNegativeDampThresholdProperty")
        set(value) = setPrimaryNegativeDampThreshold(value)

    var secondaryLimitAngle: Double
        @JvmName("secondaryLimitAngleProperty")
        get() = getSecondaryLimitAngle()
        @JvmName("setSecondaryLimitAngleProperty")
        set(value) = setSecondaryLimitAngle(value)

    var secondaryDampThreshold: Double
        @JvmName("secondaryDampThresholdProperty")
        get() = getSecondaryDampThreshold()
        @JvmName("setSecondaryDampThresholdProperty")
        set(value) = setSecondaryDampThreshold(value)

    var secondaryPositiveLimitAngle: Double
        @JvmName("secondaryPositiveLimitAngleProperty")
        get() = getSecondaryPositiveLimitAngle()
        @JvmName("setSecondaryPositiveLimitAngleProperty")
        set(value) = setSecondaryPositiveLimitAngle(value)

    var secondaryPositiveDampThreshold: Double
        @JvmName("secondaryPositiveDampThresholdProperty")
        get() = getSecondaryPositiveDampThreshold()
        @JvmName("setSecondaryPositiveDampThresholdProperty")
        set(value) = setSecondaryPositiveDampThreshold(value)

    var secondaryNegativeLimitAngle: Double
        @JvmName("secondaryNegativeLimitAngleProperty")
        get() = getSecondaryNegativeLimitAngle()
        @JvmName("setSecondaryNegativeLimitAngleProperty")
        set(value) = setSecondaryNegativeLimitAngle(value)

    var secondaryNegativeDampThreshold: Double
        @JvmName("secondaryNegativeDampThresholdProperty")
        get() = getSecondaryNegativeDampThreshold()
        @JvmName("setSecondaryNegativeDampThresholdProperty")
        set(value) = setSecondaryNegativeDampThreshold(value)

    fun setTargetNode(targetNode: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNode)
    }

    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    fun setBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setBoneNameBind, handle, boneName)
    }

    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBoneNameBind, handle)
    }

    fun setBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneBind, handle, bone)
    }

    fun getBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneBind, handle)
    }

    fun setForwardAxis(forwardAxis: Long) {
        ObjectCalls.ptrcallWithLongArg(setForwardAxisBind, handle, forwardAxis)
    }

    fun getForwardAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getForwardAxisBind, handle)
    }

    fun setPrimaryRotationAxis(axis: Long) {
        ObjectCalls.ptrcallWithLongArg(setPrimaryRotationAxisBind, handle, axis)
    }

    fun getPrimaryRotationAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPrimaryRotationAxisBind, handle)
    }

    fun setUseSecondaryRotation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseSecondaryRotationBind, handle, enabled)
    }

    fun isUsingSecondaryRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingSecondaryRotationBind, handle)
    }

    fun setRelative(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRelativeBind, handle, enabled)
    }

    fun isRelative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRelativeBind, handle)
    }

    fun setOriginSafeMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOriginSafeMarginBind, handle, margin)
    }

    fun getOriginSafeMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOriginSafeMarginBind, handle)
    }

    fun setOriginFrom(originFrom: Long) {
        ObjectCalls.ptrcallWithLongArg(setOriginFromBind, handle, originFrom)
    }

    fun getOriginFrom(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOriginFromBind, handle)
    }

    fun setOriginBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginBoneNameBind, handle, boneName)
    }

    fun getOriginBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginBoneNameBind, handle)
    }

    fun setOriginBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(setOriginBoneBind, handle, bone)
    }

    fun getOriginBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOriginBoneBind, handle)
    }

    fun setOriginExternalNode(externalNode: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setOriginExternalNodeBind, handle, externalNode)
    }

    fun getOriginExternalNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getOriginExternalNodeBind, handle)
    }

    fun setOriginOffset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setOriginOffsetBind, handle, offset)
    }

    fun getOriginOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getOriginOffsetBind, handle)
    }

    fun setDuration(duration: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDurationBind, handle, duration)
    }

    fun getDuration(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDurationBind, handle)
    }

    fun setTransitionType(transitionType: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransitionTypeBind, handle, transitionType)
    }

    fun getTransitionType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransitionTypeBind, handle)
    }

    fun setEaseType(easeType: Long) {
        ObjectCalls.ptrcallWithLongArg(setEaseTypeBind, handle, easeType)
    }

    fun getEaseType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEaseTypeBind, handle)
    }

    fun setUseAngleLimitation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAngleLimitationBind, handle, enabled)
    }

    fun isUsingAngleLimitation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingAngleLimitationBind, handle)
    }

    fun setSymmetryLimitation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymmetryLimitationBind, handle, enabled)
    }

    fun isLimitationSymmetry(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLimitationSymmetryBind, handle)
    }

    fun setPrimaryLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryLimitAngleBind, handle, angle)
    }

    fun getPrimaryLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryLimitAngleBind, handle)
    }

    fun setPrimaryDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryDampThresholdBind, handle, power)
    }

    fun getPrimaryDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryDampThresholdBind, handle)
    }

    fun setPrimaryPositiveLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryPositiveLimitAngleBind, handle, angle)
    }

    fun getPrimaryPositiveLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryPositiveLimitAngleBind, handle)
    }

    fun setPrimaryPositiveDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryPositiveDampThresholdBind, handle, power)
    }

    fun getPrimaryPositiveDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryPositiveDampThresholdBind, handle)
    }

    fun setPrimaryNegativeLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryNegativeLimitAngleBind, handle, angle)
    }

    fun getPrimaryNegativeLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryNegativeLimitAngleBind, handle)
    }

    fun setPrimaryNegativeDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryNegativeDampThresholdBind, handle, power)
    }

    fun getPrimaryNegativeDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryNegativeDampThresholdBind, handle)
    }

    fun setSecondaryLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryLimitAngleBind, handle, angle)
    }

    fun getSecondaryLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryLimitAngleBind, handle)
    }

    fun setSecondaryDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryDampThresholdBind, handle, power)
    }

    fun getSecondaryDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryDampThresholdBind, handle)
    }

    fun setSecondaryPositiveLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryPositiveLimitAngleBind, handle, angle)
    }

    fun getSecondaryPositiveLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryPositiveLimitAngleBind, handle)
    }

    fun setSecondaryPositiveDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryPositiveDampThresholdBind, handle, power)
    }

    fun getSecondaryPositiveDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryPositiveDampThresholdBind, handle)
    }

    fun setSecondaryNegativeLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryNegativeLimitAngleBind, handle, angle)
    }

    fun getSecondaryNegativeLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryNegativeLimitAngleBind, handle)
    }

    fun setSecondaryNegativeDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryNegativeDampThresholdBind, handle, power)
    }

    fun getSecondaryNegativeDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryNegativeDampThresholdBind, handle)
    }

    fun getInterpolationRemaining(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInterpolationRemainingBind, handle)
    }

    fun isInterpolating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInterpolatingBind, handle)
    }

    fun isTargetWithinLimitation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTargetWithinLimitationBind, handle)
    }

    companion object {
        const val ORIGIN_FROM_SELF: Long = 0L
        const val ORIGIN_FROM_SPECIFIC_BONE: Long = 1L
        const val ORIGIN_FROM_EXTERNAL_NODE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): LookAtModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LookAtModifier3D? =
            if (handle.address() == 0L) null else LookAtModifier3D(handle)

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 4075236667L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_BONE_NAME_HASH = 83702148L
        private val setBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_bone_name", SET_BONE_NAME_HASH)
        }

        private const val GET_BONE_NAME_HASH = 201670096L
        private val getBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_bone_name", GET_BONE_NAME_HASH)
        }

        private const val SET_BONE_HASH = 1286410249L
        private val setBoneBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_bone", SET_BONE_HASH)
        }

        private const val GET_BONE_HASH = 3905245786L
        private val getBoneBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_bone", GET_BONE_HASH)
        }

        private const val SET_FORWARD_AXIS_HASH = 3199955933L
        private val setForwardAxisBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_forward_axis", SET_FORWARD_AXIS_HASH)
        }

        private const val GET_FORWARD_AXIS_HASH = 4076020284L
        private val getForwardAxisBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_forward_axis", GET_FORWARD_AXIS_HASH)
        }

        private const val SET_PRIMARY_ROTATION_AXIS_HASH = 1144690656L
        private val setPrimaryRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_rotation_axis", SET_PRIMARY_ROTATION_AXIS_HASH)
        }

        private const val GET_PRIMARY_ROTATION_AXIS_HASH = 3050976882L
        private val getPrimaryRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_rotation_axis", GET_PRIMARY_ROTATION_AXIS_HASH)
        }

        private const val SET_USE_SECONDARY_ROTATION_HASH = 2586408642L
        private val setUseSecondaryRotationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_use_secondary_rotation", SET_USE_SECONDARY_ROTATION_HASH)
        }

        private const val IS_USING_SECONDARY_ROTATION_HASH = 36873697L
        private val isUsingSecondaryRotationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "is_using_secondary_rotation", IS_USING_SECONDARY_ROTATION_HASH)
        }

        private const val SET_RELATIVE_HASH = 2586408642L
        private val setRelativeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_relative", SET_RELATIVE_HASH)
        }

        private const val IS_RELATIVE_HASH = 36873697L
        private val isRelativeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "is_relative", IS_RELATIVE_HASH)
        }

        private const val SET_ORIGIN_SAFE_MARGIN_HASH = 373806689L
        private val setOriginSafeMarginBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_origin_safe_margin", SET_ORIGIN_SAFE_MARGIN_HASH)
        }

        private const val GET_ORIGIN_SAFE_MARGIN_HASH = 1740695150L
        private val getOriginSafeMarginBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_origin_safe_margin", GET_ORIGIN_SAFE_MARGIN_HASH)
        }

        private const val SET_ORIGIN_FROM_HASH = 4254695669L
        private val setOriginFromBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_origin_from", SET_ORIGIN_FROM_HASH)
        }

        private const val GET_ORIGIN_FROM_HASH = 4057166297L
        private val getOriginFromBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_origin_from", GET_ORIGIN_FROM_HASH)
        }

        private const val SET_ORIGIN_BONE_NAME_HASH = 83702148L
        private val setOriginBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_origin_bone_name", SET_ORIGIN_BONE_NAME_HASH)
        }

        private const val GET_ORIGIN_BONE_NAME_HASH = 201670096L
        private val getOriginBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_origin_bone_name", GET_ORIGIN_BONE_NAME_HASH)
        }

        private const val SET_ORIGIN_BONE_HASH = 1286410249L
        private val setOriginBoneBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_origin_bone", SET_ORIGIN_BONE_HASH)
        }

        private const val GET_ORIGIN_BONE_HASH = 3905245786L
        private val getOriginBoneBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_origin_bone", GET_ORIGIN_BONE_HASH)
        }

        private const val SET_ORIGIN_EXTERNAL_NODE_HASH = 1348162250L
        private val setOriginExternalNodeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_origin_external_node", SET_ORIGIN_EXTERNAL_NODE_HASH)
        }

        private const val GET_ORIGIN_EXTERNAL_NODE_HASH = 4075236667L
        private val getOriginExternalNodeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_origin_external_node", GET_ORIGIN_EXTERNAL_NODE_HASH)
        }

        private const val SET_ORIGIN_OFFSET_HASH = 3460891852L
        private val setOriginOffsetBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_origin_offset", SET_ORIGIN_OFFSET_HASH)
        }

        private const val GET_ORIGIN_OFFSET_HASH = 3360562783L
        private val getOriginOffsetBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_origin_offset", GET_ORIGIN_OFFSET_HASH)
        }

        private const val SET_DURATION_HASH = 373806689L
        private val setDurationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_duration", SET_DURATION_HASH)
        }

        private const val GET_DURATION_HASH = 1740695150L
        private val getDurationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_duration", GET_DURATION_HASH)
        }

        private const val SET_TRANSITION_TYPE_HASH = 1058637742L
        private val setTransitionTypeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_transition_type", SET_TRANSITION_TYPE_HASH)
        }

        private const val GET_TRANSITION_TYPE_HASH = 3842314528L
        private val getTransitionTypeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_transition_type", GET_TRANSITION_TYPE_HASH)
        }

        private const val SET_EASE_TYPE_HASH = 1208105857L
        private val setEaseTypeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_ease_type", SET_EASE_TYPE_HASH)
        }

        private const val GET_EASE_TYPE_HASH = 631880200L
        private val getEaseTypeBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_ease_type", GET_EASE_TYPE_HASH)
        }

        private const val SET_USE_ANGLE_LIMITATION_HASH = 2586408642L
        private val setUseAngleLimitationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_use_angle_limitation", SET_USE_ANGLE_LIMITATION_HASH)
        }

        private const val IS_USING_ANGLE_LIMITATION_HASH = 36873697L
        private val isUsingAngleLimitationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "is_using_angle_limitation", IS_USING_ANGLE_LIMITATION_HASH)
        }

        private const val SET_SYMMETRY_LIMITATION_HASH = 2586408642L
        private val setSymmetryLimitationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_symmetry_limitation", SET_SYMMETRY_LIMITATION_HASH)
        }

        private const val IS_LIMITATION_SYMMETRY_HASH = 36873697L
        private val isLimitationSymmetryBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "is_limitation_symmetry", IS_LIMITATION_SYMMETRY_HASH)
        }

        private const val SET_PRIMARY_LIMIT_ANGLE_HASH = 373806689L
        private val setPrimaryLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_limit_angle", SET_PRIMARY_LIMIT_ANGLE_HASH)
        }

        private const val GET_PRIMARY_LIMIT_ANGLE_HASH = 1740695150L
        private val getPrimaryLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_limit_angle", GET_PRIMARY_LIMIT_ANGLE_HASH)
        }

        private const val SET_PRIMARY_DAMP_THRESHOLD_HASH = 373806689L
        private val setPrimaryDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_damp_threshold", SET_PRIMARY_DAMP_THRESHOLD_HASH)
        }

        private const val GET_PRIMARY_DAMP_THRESHOLD_HASH = 1740695150L
        private val getPrimaryDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_damp_threshold", GET_PRIMARY_DAMP_THRESHOLD_HASH)
        }

        private const val SET_PRIMARY_POSITIVE_LIMIT_ANGLE_HASH = 373806689L
        private val setPrimaryPositiveLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_positive_limit_angle", SET_PRIMARY_POSITIVE_LIMIT_ANGLE_HASH)
        }

        private const val GET_PRIMARY_POSITIVE_LIMIT_ANGLE_HASH = 1740695150L
        private val getPrimaryPositiveLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_positive_limit_angle", GET_PRIMARY_POSITIVE_LIMIT_ANGLE_HASH)
        }

        private const val SET_PRIMARY_POSITIVE_DAMP_THRESHOLD_HASH = 373806689L
        private val setPrimaryPositiveDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_positive_damp_threshold", SET_PRIMARY_POSITIVE_DAMP_THRESHOLD_HASH)
        }

        private const val GET_PRIMARY_POSITIVE_DAMP_THRESHOLD_HASH = 1740695150L
        private val getPrimaryPositiveDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_positive_damp_threshold", GET_PRIMARY_POSITIVE_DAMP_THRESHOLD_HASH)
        }

        private const val SET_PRIMARY_NEGATIVE_LIMIT_ANGLE_HASH = 373806689L
        private val setPrimaryNegativeLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_negative_limit_angle", SET_PRIMARY_NEGATIVE_LIMIT_ANGLE_HASH)
        }

        private const val GET_PRIMARY_NEGATIVE_LIMIT_ANGLE_HASH = 1740695150L
        private val getPrimaryNegativeLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_negative_limit_angle", GET_PRIMARY_NEGATIVE_LIMIT_ANGLE_HASH)
        }

        private const val SET_PRIMARY_NEGATIVE_DAMP_THRESHOLD_HASH = 373806689L
        private val setPrimaryNegativeDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_primary_negative_damp_threshold", SET_PRIMARY_NEGATIVE_DAMP_THRESHOLD_HASH)
        }

        private const val GET_PRIMARY_NEGATIVE_DAMP_THRESHOLD_HASH = 1740695150L
        private val getPrimaryNegativeDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_primary_negative_damp_threshold", GET_PRIMARY_NEGATIVE_DAMP_THRESHOLD_HASH)
        }

        private const val SET_SECONDARY_LIMIT_ANGLE_HASH = 373806689L
        private val setSecondaryLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_secondary_limit_angle", SET_SECONDARY_LIMIT_ANGLE_HASH)
        }

        private const val GET_SECONDARY_LIMIT_ANGLE_HASH = 1740695150L
        private val getSecondaryLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_secondary_limit_angle", GET_SECONDARY_LIMIT_ANGLE_HASH)
        }

        private const val SET_SECONDARY_DAMP_THRESHOLD_HASH = 373806689L
        private val setSecondaryDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_secondary_damp_threshold", SET_SECONDARY_DAMP_THRESHOLD_HASH)
        }

        private const val GET_SECONDARY_DAMP_THRESHOLD_HASH = 1740695150L
        private val getSecondaryDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_secondary_damp_threshold", GET_SECONDARY_DAMP_THRESHOLD_HASH)
        }

        private const val SET_SECONDARY_POSITIVE_LIMIT_ANGLE_HASH = 373806689L
        private val setSecondaryPositiveLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_secondary_positive_limit_angle", SET_SECONDARY_POSITIVE_LIMIT_ANGLE_HASH)
        }

        private const val GET_SECONDARY_POSITIVE_LIMIT_ANGLE_HASH = 1740695150L
        private val getSecondaryPositiveLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_secondary_positive_limit_angle", GET_SECONDARY_POSITIVE_LIMIT_ANGLE_HASH)
        }

        private const val SET_SECONDARY_POSITIVE_DAMP_THRESHOLD_HASH = 373806689L
        private val setSecondaryPositiveDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_secondary_positive_damp_threshold", SET_SECONDARY_POSITIVE_DAMP_THRESHOLD_HASH)
        }

        private const val GET_SECONDARY_POSITIVE_DAMP_THRESHOLD_HASH = 1740695150L
        private val getSecondaryPositiveDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_secondary_positive_damp_threshold", GET_SECONDARY_POSITIVE_DAMP_THRESHOLD_HASH)
        }

        private const val SET_SECONDARY_NEGATIVE_LIMIT_ANGLE_HASH = 373806689L
        private val setSecondaryNegativeLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_secondary_negative_limit_angle", SET_SECONDARY_NEGATIVE_LIMIT_ANGLE_HASH)
        }

        private const val GET_SECONDARY_NEGATIVE_LIMIT_ANGLE_HASH = 1740695150L
        private val getSecondaryNegativeLimitAngleBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_secondary_negative_limit_angle", GET_SECONDARY_NEGATIVE_LIMIT_ANGLE_HASH)
        }

        private const val SET_SECONDARY_NEGATIVE_DAMP_THRESHOLD_HASH = 373806689L
        private val setSecondaryNegativeDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "set_secondary_negative_damp_threshold", SET_SECONDARY_NEGATIVE_DAMP_THRESHOLD_HASH)
        }

        private const val GET_SECONDARY_NEGATIVE_DAMP_THRESHOLD_HASH = 1740695150L
        private val getSecondaryNegativeDampThresholdBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_secondary_negative_damp_threshold", GET_SECONDARY_NEGATIVE_DAMP_THRESHOLD_HASH)
        }

        private const val GET_INTERPOLATION_REMAINING_HASH = 1740695150L
        private val getInterpolationRemainingBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "get_interpolation_remaining", GET_INTERPOLATION_REMAINING_HASH)
        }

        private const val IS_INTERPOLATING_HASH = 36873697L
        private val isInterpolatingBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "is_interpolating", IS_INTERPOLATING_HASH)
        }

        private const val IS_TARGET_WITHIN_LIMITATION_HASH = 36873697L
        private val isTargetWithinLimitationBind by lazy {
            ObjectCalls.getMethodBind("LookAtModifier3D", "is_target_within_limitation", IS_TARGET_WITHIN_LIMITATION_HASH)
        }
    }
}

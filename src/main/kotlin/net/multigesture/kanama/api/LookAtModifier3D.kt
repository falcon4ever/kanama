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

    /**
     * The `NodePath` to the node that is the target for the look at modification. This node is what
     * the modification will rotate the bone to.
     *
     * Generated from Godot docs: LookAtModifier3D.set_target_node
     */
    fun setTargetNode(targetNode: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNode)
    }

    /**
     * The `NodePath` to the node that is the target for the look at modification. This node is what
     * the modification will rotate the bone to.
     *
     * Generated from Godot docs: LookAtModifier3D.get_target_node
     */
    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    /**
     * The bone name of the `Skeleton3D` that the modification will operate on.
     *
     * Generated from Godot docs: LookAtModifier3D.set_bone_name
     */
    fun setBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setBoneNameBind, handle, boneName)
    }

    /**
     * The bone name of the `Skeleton3D` that the modification will operate on.
     *
     * Generated from Godot docs: LookAtModifier3D.get_bone_name
     */
    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBoneNameBind, handle)
    }

    /**
     * Index of the `bone_name` in the parent `Skeleton3D`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_bone
     */
    fun setBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneBind, handle, bone)
    }

    /**
     * Index of the `bone_name` in the parent `Skeleton3D`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_bone
     */
    fun getBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneBind, handle)
    }

    /**
     * The forward axis of the bone. This `SkeletonModifier3D` modifies the bone so that this axis
     * points toward the `target_node`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_forward_axis
     */
    fun setForwardAxis(forwardAxis: Long) {
        ObjectCalls.ptrcallWithLongArg(setForwardAxisBind, handle, forwardAxis)
    }

    /**
     * The forward axis of the bone. This `SkeletonModifier3D` modifies the bone so that this axis
     * points toward the `target_node`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_forward_axis
     */
    fun getForwardAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getForwardAxisBind, handle)
    }

    /**
     * The axis of the first rotation. This `SkeletonModifier3D` works by compositing the rotation by
     * Euler angles to prevent to rotate the `forward_axis`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_rotation_axis
     */
    fun setPrimaryRotationAxis(axis: Long) {
        ObjectCalls.ptrcallWithLongArg(setPrimaryRotationAxisBind, handle, axis)
    }

    /**
     * The axis of the first rotation. This `SkeletonModifier3D` works by compositing the rotation by
     * Euler angles to prevent to rotate the `forward_axis`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_rotation_axis
     */
    fun getPrimaryRotationAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPrimaryRotationAxisBind, handle)
    }

    /**
     * If `true`, provides rotation by two axes.
     *
     * Generated from Godot docs: LookAtModifier3D.set_use_secondary_rotation
     */
    fun setUseSecondaryRotation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseSecondaryRotationBind, handle, enabled)
    }

    /**
     * If `true`, provides rotation by two axes.
     *
     * Generated from Godot docs: LookAtModifier3D.is_using_secondary_rotation
     */
    fun isUsingSecondaryRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingSecondaryRotationBind, handle)
    }

    /**
     * The relative option. If `true`, the rotation is applied relative to the pose. If `false`, the
     * rotation is applied relative to the rest. It means to replace the current pose with the
     * `LookAtModifier3D`'s result. Note: This option affects the base angle for
     * `use_angle_limitation`. Since the `LookAtModifier3D` relies strongly on Euler rotation, the axis
     * that determines the limitation and the actual rotation are strongly tied together.
     *
     * Generated from Godot docs: LookAtModifier3D.set_relative
     */
    fun setRelative(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRelativeBind, handle, enabled)
    }

    /**
     * The relative option. If `true`, the rotation is applied relative to the pose. If `false`, the
     * rotation is applied relative to the rest. It means to replace the current pose with the
     * `LookAtModifier3D`'s result. Note: This option affects the base angle for
     * `use_angle_limitation`. Since the `LookAtModifier3D` relies strongly on Euler rotation, the axis
     * that determines the limitation and the actual rotation are strongly tied together.
     *
     * Generated from Godot docs: LookAtModifier3D.is_relative
     */
    fun isRelative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRelativeBind, handle)
    }

    /**
     * If the target passes through too close to the origin than this value, time-based interpolation
     * is used even if the target is within the angular limitations, to prevent the angular velocity
     * from becoming too high.
     *
     * Generated from Godot docs: LookAtModifier3D.set_origin_safe_margin
     */
    fun setOriginSafeMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOriginSafeMarginBind, handle, margin)
    }

    /**
     * If the target passes through too close to the origin than this value, time-based interpolation
     * is used even if the target is within the angular limitations, to prevent the angular velocity
     * from becoming too high.
     *
     * Generated from Godot docs: LookAtModifier3D.get_origin_safe_margin
     */
    fun getOriginSafeMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOriginSafeMarginBind, handle)
    }

    /**
     * This value determines from what origin is retrieved for use in the calculation of the forward
     * vector.
     *
     * Generated from Godot docs: LookAtModifier3D.set_origin_from
     */
    fun setOriginFrom(originFrom: Long) {
        ObjectCalls.ptrcallWithLongArg(setOriginFromBind, handle, originFrom)
    }

    /**
     * This value determines from what origin is retrieved for use in the calculation of the forward
     * vector.
     *
     * Generated from Godot docs: LookAtModifier3D.get_origin_from
     */
    fun getOriginFrom(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOriginFromBind, handle)
    }

    /**
     * If `origin_from` is `ORIGIN_FROM_SPECIFIC_BONE`, the bone global pose position specified for
     * this is used as origin.
     *
     * Generated from Godot docs: LookAtModifier3D.set_origin_bone_name
     */
    fun setOriginBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginBoneNameBind, handle, boneName)
    }

    /**
     * If `origin_from` is `ORIGIN_FROM_SPECIFIC_BONE`, the bone global pose position specified for
     * this is used as origin.
     *
     * Generated from Godot docs: LookAtModifier3D.get_origin_bone_name
     */
    fun getOriginBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginBoneNameBind, handle)
    }

    /**
     * Index of the `origin_bone_name` in the parent `Skeleton3D`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_origin_bone
     */
    fun setOriginBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(setOriginBoneBind, handle, bone)
    }

    /**
     * Index of the `origin_bone_name` in the parent `Skeleton3D`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_origin_bone
     */
    fun getOriginBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOriginBoneBind, handle)
    }

    /**
     * If `origin_from` is `ORIGIN_FROM_EXTERNAL_NODE`, the global position of the `Node3D` specified
     * for this is used as origin.
     *
     * Generated from Godot docs: LookAtModifier3D.set_origin_external_node
     */
    fun setOriginExternalNode(externalNode: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setOriginExternalNodeBind, handle, externalNode)
    }

    /**
     * If `origin_from` is `ORIGIN_FROM_EXTERNAL_NODE`, the global position of the `Node3D` specified
     * for this is used as origin.
     *
     * Generated from Godot docs: LookAtModifier3D.get_origin_external_node
     */
    fun getOriginExternalNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getOriginExternalNodeBind, handle)
    }

    /**
     * The offset of the bone pose origin. Matching the origins by offset is useful for cases where
     * multiple bones must always face the same direction, such as the eyes. Note: This value indicates
     * the local position of the object set in `origin_from`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_origin_offset
     */
    fun setOriginOffset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setOriginOffsetBind, handle, offset)
    }

    /**
     * The offset of the bone pose origin. Matching the origins by offset is useful for cases where
     * multiple bones must always face the same direction, such as the eyes. Note: This value indicates
     * the local position of the object set in `origin_from`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_origin_offset
     */
    fun getOriginOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getOriginOffsetBind, handle)
    }

    /**
     * The duration of the time-based interpolation. Interpolation is triggered at the following cases:
     * - When the target node is changed - When an axis is flipped due to angle limitation Note: The
     * flipping occurs when the target is outside the angle limitation and the internally computed
     * secondary rotation axis of the forward vector is flipped. Visually, it occurs when the target is
     * outside the angle limitation and crosses the plane of the `forward_axis` and
     * `primary_rotation_axis`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_duration
     */
    fun setDuration(duration: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDurationBind, handle, duration)
    }

    /**
     * The duration of the time-based interpolation. Interpolation is triggered at the following cases:
     * - When the target node is changed - When an axis is flipped due to angle limitation Note: The
     * flipping occurs when the target is outside the angle limitation and the internally computed
     * secondary rotation axis of the forward vector is flipped. Visually, it occurs when the target is
     * outside the angle limitation and crosses the plane of the `forward_axis` and
     * `primary_rotation_axis`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_duration
     */
    fun getDuration(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDurationBind, handle)
    }

    /**
     * The transition type of the time-based interpolation. See also `Tween.TransitionType`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_transition_type
     */
    fun setTransitionType(transitionType: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransitionTypeBind, handle, transitionType)
    }

    /**
     * The transition type of the time-based interpolation. See also `Tween.TransitionType`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_transition_type
     */
    fun getTransitionType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransitionTypeBind, handle)
    }

    /**
     * The ease type of the time-based interpolation. See also `Tween.EaseType`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_ease_type
     */
    fun setEaseType(easeType: Long) {
        ObjectCalls.ptrcallWithLongArg(setEaseTypeBind, handle, easeType)
    }

    /**
     * The ease type of the time-based interpolation. See also `Tween.EaseType`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_ease_type
     */
    fun getEaseType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEaseTypeBind, handle)
    }

    /**
     * If `true`, limits the amount of rotation. For example, this helps to prevent a character's neck
     * from rotating 360 degrees. Note: As with `AnimationTree` blending, interpolation is provided
     * that favors `Skeleton3D.get_bone_rest` or `Skeleton3D.get_bone_pose` depends on the `relative`
     * option. This means that interpolation does not select the shortest path in some cases. Note:
     * Some values for `transition_type` (such as `Tween.TRANS_BACK`, `Tween.TRANS_ELASTIC`, and
     * `Tween.TRANS_SPRING`) may exceed the limitations. If interpolation occurs while overshooting the
     * limitations, the result might not respect the bone rest.
     *
     * Generated from Godot docs: LookAtModifier3D.set_use_angle_limitation
     */
    fun setUseAngleLimitation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAngleLimitationBind, handle, enabled)
    }

    /**
     * If `true`, limits the amount of rotation. For example, this helps to prevent a character's neck
     * from rotating 360 degrees. Note: As with `AnimationTree` blending, interpolation is provided
     * that favors `Skeleton3D.get_bone_rest` or `Skeleton3D.get_bone_pose` depends on the `relative`
     * option. This means that interpolation does not select the shortest path in some cases. Note:
     * Some values for `transition_type` (such as `Tween.TRANS_BACK`, `Tween.TRANS_ELASTIC`, and
     * `Tween.TRANS_SPRING`) may exceed the limitations. If interpolation occurs while overshooting the
     * limitations, the result might not respect the bone rest.
     *
     * Generated from Godot docs: LookAtModifier3D.is_using_angle_limitation
     */
    fun isUsingAngleLimitation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingAngleLimitationBind, handle)
    }

    /**
     * If `true`, the limitations are spread from the bone symmetrically. If `false`, the limitation
     * can be specified separately for each side of the bone rest.
     *
     * Generated from Godot docs: LookAtModifier3D.set_symmetry_limitation
     */
    fun setSymmetryLimitation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymmetryLimitationBind, handle, enabled)
    }

    /**
     * If `true`, the limitations are spread from the bone symmetrically. If `false`, the limitation
     * can be specified separately for each side of the bone rest.
     *
     * Generated from Godot docs: LookAtModifier3D.is_limitation_symmetry
     */
    fun isLimitationSymmetry(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLimitationSymmetryBind, handle)
    }

    /**
     * The limit angle of the primary rotation when `symmetry_limitation` is `true`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_limit_angle
     */
    fun setPrimaryLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryLimitAngleBind, handle, angle)
    }

    /**
     * The limit angle of the primary rotation when `symmetry_limitation` is `true`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_limit_angle
     */
    fun getPrimaryLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryLimitAngleBind, handle)
    }

    /**
     * The threshold to start damping for `primary_limit_angle`. It provides non-linear (b-spline)
     * interpolation, let it feel more resistance the more it rotate to the edge limit. This is useful
     * for simulating the limits of human motion. If `1.0`, no damping is performed. If `0.0`, damping
     * is always performed.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_damp_threshold
     */
    fun setPrimaryDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryDampThresholdBind, handle, power)
    }

    /**
     * The threshold to start damping for `primary_limit_angle`. It provides non-linear (b-spline)
     * interpolation, let it feel more resistance the more it rotate to the edge limit. This is useful
     * for simulating the limits of human motion. If `1.0`, no damping is performed. If `0.0`, damping
     * is always performed.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_damp_threshold
     */
    fun getPrimaryDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryDampThresholdBind, handle)
    }

    /**
     * The limit angle of positive side of the primary rotation when `symmetry_limitation` is `false`,
     * in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_positive_limit_angle
     */
    fun setPrimaryPositiveLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryPositiveLimitAngleBind, handle, angle)
    }

    /**
     * The limit angle of positive side of the primary rotation when `symmetry_limitation` is `false`,
     * in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_positive_limit_angle
     */
    fun getPrimaryPositiveLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryPositiveLimitAngleBind, handle)
    }

    /**
     * The threshold to start damping for `primary_positive_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_positive_damp_threshold
     */
    fun setPrimaryPositiveDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryPositiveDampThresholdBind, handle, power)
    }

    /**
     * The threshold to start damping for `primary_positive_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_positive_damp_threshold
     */
    fun getPrimaryPositiveDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryPositiveDampThresholdBind, handle)
    }

    /**
     * The limit angle of negative side of the primary rotation when `symmetry_limitation` is `false`,
     * in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_negative_limit_angle
     */
    fun setPrimaryNegativeLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryNegativeLimitAngleBind, handle, angle)
    }

    /**
     * The limit angle of negative side of the primary rotation when `symmetry_limitation` is `false`,
     * in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_negative_limit_angle
     */
    fun getPrimaryNegativeLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryNegativeLimitAngleBind, handle)
    }

    /**
     * The threshold to start damping for `primary_negative_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_primary_negative_damp_threshold
     */
    fun setPrimaryNegativeDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPrimaryNegativeDampThresholdBind, handle, power)
    }

    /**
     * The threshold to start damping for `primary_negative_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_primary_negative_damp_threshold
     */
    fun getPrimaryNegativeDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPrimaryNegativeDampThresholdBind, handle)
    }

    /**
     * The limit angle of the secondary rotation when `symmetry_limitation` is `true`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.set_secondary_limit_angle
     */
    fun setSecondaryLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryLimitAngleBind, handle, angle)
    }

    /**
     * The limit angle of the secondary rotation when `symmetry_limitation` is `true`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.get_secondary_limit_angle
     */
    fun getSecondaryLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryLimitAngleBind, handle)
    }

    /**
     * The threshold to start damping for `secondary_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_secondary_damp_threshold
     */
    fun setSecondaryDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryDampThresholdBind, handle, power)
    }

    /**
     * The threshold to start damping for `secondary_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_secondary_damp_threshold
     */
    fun getSecondaryDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryDampThresholdBind, handle)
    }

    /**
     * The limit angle of positive side of the secondary rotation when `symmetry_limitation` is
     * `false`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.set_secondary_positive_limit_angle
     */
    fun setSecondaryPositiveLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryPositiveLimitAngleBind, handle, angle)
    }

    /**
     * The limit angle of positive side of the secondary rotation when `symmetry_limitation` is
     * `false`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.get_secondary_positive_limit_angle
     */
    fun getSecondaryPositiveLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryPositiveLimitAngleBind, handle)
    }

    /**
     * The threshold to start damping for `secondary_positive_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_secondary_positive_damp_threshold
     */
    fun setSecondaryPositiveDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryPositiveDampThresholdBind, handle, power)
    }

    /**
     * The threshold to start damping for `secondary_positive_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_secondary_positive_damp_threshold
     */
    fun getSecondaryPositiveDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryPositiveDampThresholdBind, handle)
    }

    /**
     * The limit angle of negative side of the secondary rotation when `symmetry_limitation` is
     * `false`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.set_secondary_negative_limit_angle
     */
    fun setSecondaryNegativeLimitAngle(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryNegativeLimitAngleBind, handle, angle)
    }

    /**
     * The limit angle of negative side of the secondary rotation when `symmetry_limitation` is
     * `false`, in radians.
     *
     * Generated from Godot docs: LookAtModifier3D.get_secondary_negative_limit_angle
     */
    fun getSecondaryNegativeLimitAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryNegativeLimitAngleBind, handle)
    }

    /**
     * The threshold to start damping for `secondary_negative_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.set_secondary_negative_damp_threshold
     */
    fun setSecondaryNegativeDampThreshold(power: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSecondaryNegativeDampThresholdBind, handle, power)
    }

    /**
     * The threshold to start damping for `secondary_negative_limit_angle`.
     *
     * Generated from Godot docs: LookAtModifier3D.get_secondary_negative_damp_threshold
     */
    fun getSecondaryNegativeDampThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSecondaryNegativeDampThresholdBind, handle)
    }

    /**
     * Returns the remaining seconds of the time-based interpolation.
     *
     * Generated from Godot docs: LookAtModifier3D.get_interpolation_remaining
     */
    fun getInterpolationRemaining(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInterpolationRemainingBind, handle)
    }

    /**
     * Returns `true` if time-based interpolation is running. If `true`, it is equivalent to
     * `get_interpolation_remaining` returning `0.0`. This is useful to determine whether a
     * `LookAtModifier3D` can be removed safely.
     *
     * Generated from Godot docs: LookAtModifier3D.is_interpolating
     */
    fun isInterpolating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInterpolatingBind, handle)
    }

    /**
     * Returns whether the target is within the angle limitations. It is useful for unsetting the
     * `target_node` when the target is outside of the angle limitations. Note: The value is updated
     * after `SkeletonModifier3D._process_modification`. To retrieve this value correctly, we recommend
     * using the signal `SkeletonModifier3D.modification_processed`.
     *
     * Generated from Godot docs: LookAtModifier3D.is_target_within_limitation
     */
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

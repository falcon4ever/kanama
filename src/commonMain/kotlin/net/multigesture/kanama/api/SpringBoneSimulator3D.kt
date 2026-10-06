package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3

/**
 * A `SkeletonModifier3D` to apply inertial wavering to bone chains.
 *
 * Generated from Godot docs: SpringBoneSimulator3D
 */
class SpringBoneSimulator3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    var externalForce: Vector3
        @JvmName("externalForceProperty")
        get() = getExternalForce()
        @JvmName("setExternalForceProperty")
        set(value) = setExternalForce(value)

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
     * Sets the root bone name of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_root_bone_name
     */
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setRootBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the root bone name of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_root_bone_name
     */
    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getRootBoneNameBind, segment, index)
    }

    /**
     * Sets the root bone index of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_root_bone
     */
    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setRootBoneBind, segment, index, bone)
    }

    /**
     * Returns the root bone index of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_root_bone
     */
    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getRootBoneBind, segment, index)
    }

    /**
     * Sets the end bone name of the bone chain. Note: End bone must be the root bone or a child of the
     * root bone. If they are the same, the tail must be extended by `set_extend_end_bone` to jiggle
     * the bone.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_end_bone_name
     */
    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setEndBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the end bone name of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_end_bone_name
     */
    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getEndBoneNameBind, segment, index)
    }

    /**
     * Sets the end bone index of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_end_bone
     */
    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setEndBoneBind, segment, index, bone)
    }

    /**
     * Returns the end bone index of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_end_bone
     */
    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getEndBoneBind, segment, index)
    }

    /**
     * If `enabled` is `true`, the end bone is extended to have a tail. The extended tail config is
     * allocated to the last element in the joint list. In other words, if you set `enabled` to
     * `false`, the config of the last element in the joint list has no effect in the simulated result.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_extend_end_bone
     */
    fun setExtendEndBone(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setExtendEndBoneBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the end bone is extended to have a tail.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.is_end_bone_extended
     */
    fun isEndBoneExtended(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isEndBoneExtendedBind, segment, index)
    }

    /**
     * Sets the end bone tail direction of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_end_bone_direction
     */
    fun setEndBoneDirection(index: Int, boneDirection: SkeletonModifier3D.BoneDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setEndBoneDirectionBind, segment, index, boneDirection.value)
    }

    /**
     * Returns the tail direction of the end bone of the bone chain when `is_end_bone_extended` is
     * `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_end_bone_direction
     */
    fun getEndBoneDirection(index: Int): SkeletonModifier3D.BoneDirection {
        return SkeletonModifier3D.BoneDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getEndBoneDirectionBind, segment, index))
    }

    /**
     * Sets the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_end_bone_length
     */
    fun setEndBoneLength(index: Int, length: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setEndBoneLengthBind, segment, index, length)
    }

    /**
     * Returns the end bone tail length of the bone chain when `is_end_bone_extended` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_end_bone_length
     */
    fun getEndBoneLength(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getEndBoneLengthBind, segment, index)
    }

    /**
     * Sets what the center originates from in the bone chain. Bone movement is calculated based on the
     * difference in relative distance between center and bone in the previous and next frames. For
     * example, if the parent `Skeleton3D` is used as the center, the bones are considered to have not
     * moved if the `Skeleton3D` moves in the world. In this case, only a change in the bone pose is
     * considered to be a bone movement.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_center_from
     */
    fun setCenterFrom(index: Int, centerFrom: SpringBoneSimulator3D.CenterFrom) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setCenterFromBind, segment, index, centerFrom.value)
    }

    /**
     * Returns what the center originates from in the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_center_from
     */
    fun getCenterFrom(index: Int): SpringBoneSimulator3D.CenterFrom {
        return SpringBoneSimulator3D.CenterFrom(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getCenterFromBind, segment, index))
    }

    /**
     * Sets the center node path of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_center_node
     */
    fun setCenterNode(index: Int, nodePath: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setCenterNodeBind, segment, index, nodePath)
    }

    /**
     * Returns the center node path of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_center_node
     */
    fun getCenterNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getCenterNodeBind, segment, index)
    }

    /**
     * Sets the center bone name of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_center_bone_name
     */
    fun setCenterBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setCenterBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the center bone name of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_center_bone_name
     */
    fun getCenterBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getCenterBoneNameBind, segment, index)
    }

    /**
     * Sets the center bone index of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_center_bone
     */
    fun setCenterBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setCenterBoneBind, segment, index, bone)
    }

    /**
     * Returns the center bone index of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_center_bone
     */
    fun getCenterBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getCenterBoneBind, segment, index)
    }

    /**
     * Sets the joint radius of the bone chain. It is used to move and slide with the
     * `SpringBoneCollision3D` in the collision list. The value is scaled by `set_radius_damping_curve`
     * and cached in each joint setting in the joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_radius
     */
    fun setRadius(index: Int, radius: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setRadiusBind, segment, index, radius)
    }

    /**
     * Returns the joint radius of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_radius
     */
    fun getRadius(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getRadiusBind, segment, index)
    }

    /**
     * Sets the rotation axis of the bone chain. If set to a specific axis, it acts like a hinge joint.
     * The value is cached in each joint setting in the joint list. The axes are based on the reference
     * pose's space, if `axis` is `SkeletonModifier3D.RotationAxis.CUSTOM`, you can specify any axis.
     * In here, the reference pose is the bone pose immediately before the simulation. Note: The
     * rotation axis vector and the forward vector shouldn't be colinear to avoid unintended rotation
     * since `SpringBoneSimulator3D` does not factor in twisting forces.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_rotation_axis
     */
    fun setRotationAxis(index: Int, axis: SkeletonModifier3D.RotationAxis) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setRotationAxisBind, segment, index, axis.value)
    }

    /**
     * Returns the rotation axis of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_rotation_axis
     */
    fun getRotationAxis(index: Int): SkeletonModifier3D.RotationAxis {
        return SkeletonModifier3D.RotationAxis(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getRotationAxisBind, segment, index))
    }

    /**
     * Sets the rotation axis vector of the bone chain. The value is cached in each joint setting in
     * the joint list. This vector is normalized by an internal process and represents the axis around
     * which the bone chain can rotate. If the vector length is `0`, it is considered synonymous with
     * `SkeletonModifier3D.RotationAxis.ALL`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_rotation_axis_vector
     */
    fun setRotationAxisVector(index: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setRotationAxisVectorBind, segment, index, vector)
    }

    /**
     * Returns the rotation axis vector of the bone chain. This vector represents the axis around which
     * the bone chain can rotate. It is determined based on the rotation axis set for the bone chain.
     * If `get_rotation_axis` is `SkeletonModifier3D.RotationAxis.ALL`, this method returns `Vector3(0,
     * 0, 0)`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_rotation_axis_vector
     */
    fun getRotationAxisVector(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getRotationAxisVectorBind, segment, index)
    }

    /**
     * Sets the joint radius damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_radius_damping_curve
     */
    fun setRadiusDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setRadiusDampingCurveBind, segment, index, curve?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the joint radius damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_radius_damping_curve
     */
    fun getRadiusDampingCurve(index: Int): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getRadiusDampingCurveBind, segment, index))
    }

    /**
     * Sets the stiffness force of the bone chain. The greater the value, the faster it recovers to its
     * initial pose. If `stiffness` is `0`, the modified pose will not return to the original pose. The
     * value is scaled by `set_stiffness_damping_curve` and cached in each joint setting in the joint
     * list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_stiffness
     */
    fun setStiffness(index: Int, stiffness: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setStiffnessBind, segment, index, stiffness)
    }

    /**
     * Returns the stiffness force of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_stiffness
     */
    fun getStiffness(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getStiffnessBind, segment, index)
    }

    /**
     * Sets the stiffness force damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_stiffness_damping_curve
     */
    fun setStiffnessDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setStiffnessDampingCurveBind, segment, index, curve?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the stiffness force damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_stiffness_damping_curve
     */
    fun getStiffnessDampingCurve(index: Int): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getStiffnessDampingCurveBind, segment, index))
    }

    /**
     * Sets the drag force of the bone chain. The greater the value, the more suppressed the wiggling.
     * The value is scaled by `set_drag_damping_curve` and cached in each joint setting in the joint
     * list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_drag
     */
    fun setDrag(index: Int, drag: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setDragBind, segment, index, drag)
    }

    /**
     * Returns the drag force damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_drag
     */
    fun getDrag(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getDragBind, segment, index)
    }

    /**
     * Sets the drag force damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_drag_damping_curve
     */
    fun setDragDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setDragDampingCurveBind, segment, index, curve?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the drag force damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_drag_damping_curve
     */
    fun getDragDampingCurve(index: Int): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getDragDampingCurveBind, segment, index))
    }

    /**
     * Sets the gravity amount of the bone chain. This value is not an acceleration, but a constant
     * velocity of movement in `set_gravity_direction`. If `gravity` is not `0`, the modified pose will
     * not return to the original pose since it is always affected by gravity. The value is scaled by
     * `set_gravity_damping_curve` and cached in each joint setting in the joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_gravity
     */
    fun setGravity(index: Int, gravity: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setGravityBind, segment, index, gravity)
    }

    /**
     * Returns the gravity amount of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_gravity
     */
    fun getGravity(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getGravityBind, segment, index)
    }

    /**
     * Sets the gravity amount damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_gravity_damping_curve
     */
    fun setGravityDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setGravityDampingCurveBind, segment, index, curve?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the gravity amount damping curve of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_gravity_damping_curve
     */
    fun getGravityDampingCurve(index: Int): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getGravityDampingCurveBind, segment, index))
    }

    /**
     * Sets the gravity direction of the bone chain. This value is internally normalized and then
     * multiplied by `set_gravity`. The value is cached in each joint setting in the joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_gravity_direction
     */
    fun setGravityDirection(index: Int, gravityDirection: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setGravityDirectionBind, segment, index, gravityDirection)
    }

    /**
     * Returns the gravity direction of the bone chain.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_gravity_direction
     */
    fun getGravityDirection(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getGravityDirectionBind, segment, index)
    }

    /**
     * The number of settings.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_setting_count
     */
    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSettingCountBind, segment, count)
    }

    /**
     * The number of settings.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_setting_count
     */
    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSettingCountBind, segment)
    }

    /**
     * Clears all settings.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.clear_settings
     */
    fun clearSettings() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSettingsBind, segment)
    }

    /**
     * If `enabled` is `true`, the config can be edited individually for each joint.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_individual_config
     */
    fun setIndividualConfig(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setIndividualConfigBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the config can be edited individually for each joint.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.is_config_individual
     */
    fun isConfigIndividual(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isConfigIndividualBind, segment, index)
    }

    /**
     * Returns the bone name at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_bone_name
     */
    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(Binds.getJointBoneNameBind, segment, index, joint)
    }

    /**
     * Returns the bone index at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_bone
     */
    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getJointBoneBind, segment, index, joint)
    }

    /**
     * Sets the rotation axis at `joint` in the bone chain's joint list when `is_config_individual` is
     * `true`. The axes are based on the reference pose's space, if `axis` is
     * `SkeletonModifier3D.RotationAxis.CUSTOM`, you can specify any axis. In here, the reference pose
     * is the bone pose immediately before the simulation. Note: The rotation axis and the forward
     * vector shouldn't be colinear to avoid unintended rotation since `SpringBoneSimulator3D` does not
     * factor in twisting forces.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_rotation_axis
     */
    fun setJointRotationAxis(index: Int, joint: Int, axis: SkeletonModifier3D.RotationAxis) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(Binds.setJointRotationAxisBind, segment, index, joint, axis.value)
    }

    /**
     * Returns the rotation axis at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_rotation_axis
     */
    fun getJointRotationAxis(index: Int, joint: Int): SkeletonModifier3D.RotationAxis {
        return SkeletonModifier3D.RotationAxis(ObjectCalls.ptrcallWithTwoIntArgsRetLong(Binds.getJointRotationAxisBind, segment, index, joint))
    }

    /**
     * Sets the rotation axis vector for the specified joint in the bone chain. This vector is
     * normalized by an internal process and represents the axis around which the bone chain can
     * rotate. If the vector length is `0`, it is considered synonymous with
     * `SkeletonModifier3D.RotationAxis.ALL`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_rotation_axis_vector
     */
    fun setJointRotationAxisVector(index: Int, joint: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(Binds.setJointRotationAxisVectorBind, segment, index, joint, vector)
    }

    /**
     * Returns the rotation axis vector for the specified joint in the bone chain. This vector
     * represents the axis around which the joint can rotate. It is determined based on the rotation
     * axis set for the joint. If `get_joint_rotation_axis` is `SkeletonModifier3D.RotationAxis.ALL`,
     * this method returns `Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_rotation_axis_vector
     */
    fun getJointRotationAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(Binds.getJointRotationAxisVectorBind, segment, index, joint)
    }

    /**
     * Sets the joint radius at `joint` in the bone chain's joint list when `is_config_individual` is
     * `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_radius
     */
    fun setJointRadius(index: Int, joint: Int, radius: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setJointRadiusBind, segment, index, joint, radius)
    }

    /**
     * Returns the radius at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_radius
     */
    fun getJointRadius(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getJointRadiusBind, segment, index, joint)
    }

    /**
     * Sets the stiffness force at `joint` in the bone chain's joint list when `is_config_individual`
     * is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_stiffness
     */
    fun setJointStiffness(index: Int, joint: Int, stiffness: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setJointStiffnessBind, segment, index, joint, stiffness)
    }

    /**
     * Returns the stiffness force at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_stiffness
     */
    fun getJointStiffness(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getJointStiffnessBind, segment, index, joint)
    }

    /**
     * Sets the drag force at `joint` in the bone chain's joint list when `is_config_individual` is
     * `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_drag
     */
    fun setJointDrag(index: Int, joint: Int, drag: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setJointDragBind, segment, index, joint, drag)
    }

    /**
     * Returns the drag force at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_drag
     */
    fun getJointDrag(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getJointDragBind, segment, index, joint)
    }

    /**
     * Sets the gravity amount at `joint` in the bone chain's joint list when `is_config_individual` is
     * `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_gravity
     */
    fun setJointGravity(index: Int, joint: Int, gravity: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setJointGravityBind, segment, index, joint, gravity)
    }

    /**
     * Returns the gravity amount at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_gravity
     */
    fun getJointGravity(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getJointGravityBind, segment, index, joint)
    }

    /**
     * Sets the gravity direction at `joint` in the bone chain's joint list when `is_config_individual`
     * is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_joint_gravity_direction
     */
    fun setJointGravityDirection(index: Int, joint: Int, gravityDirection: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(Binds.setJointGravityDirectionBind, segment, index, joint, gravityDirection)
    }

    /**
     * Returns the gravity direction at `joint` in the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_gravity_direction
     */
    fun getJointGravityDirection(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(Binds.getJointGravityDirectionBind, segment, index, joint)
    }

    /**
     * Returns the joint count of the bone chain's joint list.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_joint_count
     */
    fun getJointCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getJointCountBind, segment, index)
    }

    /**
     * If `enabled` is `true`, all child `SpringBoneCollision3D`s are colliding and
     * `set_exclude_collision_path` is enabled as an exclusion list at `index` in the settings. If
     * `enabled` is `false`, you need to manually register all valid collisions with
     * `set_collision_path`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_enable_all_child_collisions
     */
    fun setEnableAllChildCollisions(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setEnableAllChildCollisionsBind, segment, index, enabled)
    }

    /**
     * Returns `true` if all child `SpringBoneCollision3D`s are contained in the collision list at
     * `index` in the settings.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.are_all_child_collisions_enabled
     */
    fun areAllChildCollisionsEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.areAllChildCollisionsEnabledBind, segment, index)
    }

    /**
     * Sets the node path of the `SpringBoneCollision3D` at `collision` in the bone chain's exclude
     * collision list when `are_all_child_collisions_enabled` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_exclude_collision_path
     */
    fun setExcludeCollisionPath(index: Int, collision: Int, nodePath: NodePath) {
        ObjectCalls.ptrcallWithTwoIntAndNodePathArg(Binds.setExcludeCollisionPathBind, segment, index, collision, nodePath)
    }

    /**
     * Returns the node path of the `SpringBoneCollision3D` at `collision` in the bone chain's exclude
     * collision list when `are_all_child_collisions_enabled` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_exclude_collision_path
     */
    fun getExcludeCollisionPath(index: Int, collision: Int): NodePath {
        return ObjectCalls.ptrcallWithTwoIntArgsRetNodePath(Binds.getExcludeCollisionPathBind, segment, index, collision)
    }

    /**
     * Sets the number of exclude collisions in the exclude collision list at `index` in the settings
     * when `are_all_child_collisions_enabled` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_exclude_collision_count
     */
    fun setExcludeCollisionCount(index: Int, count: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setExcludeCollisionCountBind, segment, index, count)
    }

    /**
     * Returns the exclude collision count of the bone chain's exclude collision list when
     * `are_all_child_collisions_enabled` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_exclude_collision_count
     */
    fun getExcludeCollisionCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getExcludeCollisionCountBind, segment, index)
    }

    /**
     * Clears all exclude collisions from the collision list at `index` in the settings when
     * `are_all_child_collisions_enabled` is `true`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.clear_exclude_collisions
     */
    fun clearExcludeCollisions(index: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.clearExcludeCollisionsBind, segment, index)
    }

    /**
     * Sets the node path of the `SpringBoneCollision3D` at `collision` in the bone chain's collision
     * list when `are_all_child_collisions_enabled` is `false`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_collision_path
     */
    fun setCollisionPath(index: Int, collision: Int, nodePath: NodePath) {
        ObjectCalls.ptrcallWithTwoIntAndNodePathArg(Binds.setCollisionPathBind, segment, index, collision, nodePath)
    }

    /**
     * Returns the node path of the `SpringBoneCollision3D` at `collision` in the bone chain's
     * collision list when `are_all_child_collisions_enabled` is `false`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_collision_path
     */
    fun getCollisionPath(index: Int, collision: Int): NodePath {
        return ObjectCalls.ptrcallWithTwoIntArgsRetNodePath(Binds.getCollisionPathBind, segment, index, collision)
    }

    /**
     * Sets the number of collisions in the collision list at `index` in the settings when
     * `are_all_child_collisions_enabled` is `false`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_collision_count
     */
    fun setCollisionCount(index: Int, count: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setCollisionCountBind, segment, index, count)
    }

    /**
     * Returns the collision count of the bone chain's collision list when
     * `are_all_child_collisions_enabled` is `false`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_collision_count
     */
    fun getCollisionCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getCollisionCountBind, segment, index)
    }

    /**
     * Clears all collisions from the collision list at `index` in the settings when
     * `are_all_child_collisions_enabled` is `false`.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.clear_collisions
     */
    fun clearCollisions(index: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.clearCollisionsBind, segment, index)
    }

    /**
     * The constant force that always affected bones. It is equal to the result when the parent
     * `Skeleton3D` moves at this speed in the opposite direction. This is useful for effects such as
     * wind and anti-gravity.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_external_force
     */
    fun setExternalForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setExternalForceBind, segment, force)
    }

    /**
     * The constant force that always affected bones. It is equal to the result when the parent
     * `Skeleton3D` moves at this speed in the opposite direction. This is useful for effects such as
     * wind and anti-gravity.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.get_external_force
     */
    fun getExternalForce(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getExternalForceBind, segment)
    }

    /**
     * If `true`, the solver retrieves the bone axis from the bone pose every frame. If `false`, the
     * solver retrieves the bone axis from the bone rest and caches it, which increases performance
     * slightly, but position changes in the bone pose made before processing this
     * `SpringBoneSimulator3D` are ignored.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.set_mutable_bone_axes
     */
    fun setMutableBoneAxes(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMutableBoneAxesBind, segment, enabled)
    }

    /**
     * If `true`, the solver retrieves the bone axis from the bone pose every frame. If `false`, the
     * solver retrieves the bone axis from the bone rest and caches it, which increases performance
     * slightly, but position changes in the bone pose made before processing this
     * `SpringBoneSimulator3D` are ignored.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.are_bone_axes_mutable
     */
    fun areBoneAxesMutable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.areBoneAxesMutableBind, segment)
    }

    /**
     * Resets a simulating state with respect to the current bone pose. It is useful to prevent the
     * simulation result getting violent. For example, calling this immediately after a call to
     * `AnimationPlayer.play` without a fading, or within the previous
     * `SkeletonModifier3D.modification_processed` signal if it's condition changes significantly.
     *
     * Generated from Godot docs: SpringBoneSimulator3D.reset
     */
    fun reset() {
        ObjectCalls.ptrcallNoArgs(Binds.resetBind, segment)
    }

    /**
     * Godot's `SpringBoneSimulator3D.CenterFrom` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`SpringBoneSimulator3D.CenterFrom.<NAME>`).
     *
     * Generated from Godot docs: SpringBoneSimulator3D.CenterFrom
     */
    @JvmInline
    value class CenterFrom(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The world origin is defined as center.
             *
             * Generated from Godot docs: SpringBoneSimulator3D.CENTER_FROM_WORLD_ORIGIN
             */
            val WORLD_ORIGIN: CenterFrom get() = CenterFrom(0L)
            /**
             * The `Node3D` specified by `set_center_node` is defined as center. If `Node3D` is not found, the
             * parent `Skeleton3D` is treated as center.
             *
             * Generated from Godot docs: SpringBoneSimulator3D.CENTER_FROM_NODE
             */
            val NODE: CenterFrom get() = CenterFrom(1L)
            /**
             * The bone pose origin of the parent `Skeleton3D` specified by `set_center_bone` is defined as
             * center. If `Node3D` is not found, the parent `Skeleton3D` is treated as center.
             *
             * Generated from Godot docs: SpringBoneSimulator3D.CENTER_FROM_BONE
             */
            val BONE: CenterFrom get() = CenterFrom(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SpringBoneSimulator3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SpringBoneSimulator3D? =
            if (handle.address() == 0L) null else SpringBoneSimulator3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        @JvmField
        val setRootBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        @JvmField
        val getRootBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)

        private const val SET_ROOT_BONE_HASH = 3937882851L
        @JvmField
        val setRootBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_root_bone", SET_ROOT_BONE_HASH)

        private const val GET_ROOT_BONE_HASH = 923996154L
        @JvmField
        val getRootBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_root_bone", GET_ROOT_BONE_HASH)

        private const val SET_END_BONE_NAME_HASH = 501894301L
        @JvmField
        val setEndBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)

        private const val GET_END_BONE_NAME_HASH = 844755477L
        @JvmField
        val getEndBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)

        private const val SET_END_BONE_HASH = 3937882851L
        @JvmField
        val setEndBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone", SET_END_BONE_HASH)

        private const val GET_END_BONE_HASH = 923996154L
        @JvmField
        val getEndBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone", GET_END_BONE_HASH)

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        @JvmField
        val setExtendEndBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        @JvmField
        val isEndBoneExtendedBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        @JvmField
        val setEndBoneDirectionBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        @JvmField
        val getEndBoneDirectionBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)

        private const val SET_END_BONE_LENGTH_HASH = 1602489585L
        @JvmField
        val setEndBoneLengthBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone_length", SET_END_BONE_LENGTH_HASH)

        private const val GET_END_BONE_LENGTH_HASH = 2339986948L
        @JvmField
        val getEndBoneLengthBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_length", GET_END_BONE_LENGTH_HASH)

        private const val SET_CENTER_FROM_HASH = 2551505749L
        @JvmField
        val setCenterFromBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_from", SET_CENTER_FROM_HASH)

        private const val GET_CENTER_FROM_HASH = 2721930813L
        @JvmField
        val getCenterFromBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_from", GET_CENTER_FROM_HASH)

        private const val SET_CENTER_NODE_HASH = 2761262315L
        @JvmField
        val setCenterNodeBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_node", SET_CENTER_NODE_HASH)

        private const val GET_CENTER_NODE_HASH = 408788394L
        @JvmField
        val getCenterNodeBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_node", GET_CENTER_NODE_HASH)

        private const val SET_CENTER_BONE_NAME_HASH = 501894301L
        @JvmField
        val setCenterBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_bone_name", SET_CENTER_BONE_NAME_HASH)

        private const val GET_CENTER_BONE_NAME_HASH = 844755477L
        @JvmField
        val getCenterBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_bone_name", GET_CENTER_BONE_NAME_HASH)

        private const val SET_CENTER_BONE_HASH = 3937882851L
        @JvmField
        val setCenterBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_bone", SET_CENTER_BONE_HASH)

        private const val GET_CENTER_BONE_HASH = 923996154L
        @JvmField
        val getCenterBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_bone", GET_CENTER_BONE_HASH)

        private const val SET_RADIUS_HASH = 1602489585L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 2339986948L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_ROTATION_AXIS_HASH = 1539703856L
        @JvmField
        val setRotationAxisBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_rotation_axis", SET_ROTATION_AXIS_HASH)

        private const val GET_ROTATION_AXIS_HASH = 2844851118L
        @JvmField
        val getRotationAxisBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_rotation_axis", GET_ROTATION_AXIS_HASH)

        private const val SET_ROTATION_AXIS_VECTOR_HASH = 1530502735L
        @JvmField
        val setRotationAxisVectorBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_rotation_axis_vector", SET_ROTATION_AXIS_VECTOR_HASH)

        private const val GET_ROTATION_AXIS_VECTOR_HASH = 711720468L
        @JvmField
        val getRotationAxisVectorBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_rotation_axis_vector", GET_ROTATION_AXIS_VECTOR_HASH)

        private const val SET_RADIUS_DAMPING_CURVE_HASH = 1447180063L
        @JvmField
        val setRadiusDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_radius_damping_curve", SET_RADIUS_DAMPING_CURVE_HASH)

        private const val GET_RADIUS_DAMPING_CURVE_HASH = 747537754L
        @JvmField
        val getRadiusDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_radius_damping_curve", GET_RADIUS_DAMPING_CURVE_HASH)

        private const val SET_STIFFNESS_HASH = 1602489585L
        @JvmField
        val setStiffnessBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_stiffness", SET_STIFFNESS_HASH)

        private const val GET_STIFFNESS_HASH = 2339986948L
        @JvmField
        val getStiffnessBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_stiffness", GET_STIFFNESS_HASH)

        private const val SET_STIFFNESS_DAMPING_CURVE_HASH = 1447180063L
        @JvmField
        val setStiffnessDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_stiffness_damping_curve", SET_STIFFNESS_DAMPING_CURVE_HASH)

        private const val GET_STIFFNESS_DAMPING_CURVE_HASH = 747537754L
        @JvmField
        val getStiffnessDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_stiffness_damping_curve", GET_STIFFNESS_DAMPING_CURVE_HASH)

        private const val SET_DRAG_HASH = 1602489585L
        @JvmField
        val setDragBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_drag", SET_DRAG_HASH)

        private const val GET_DRAG_HASH = 2339986948L
        @JvmField
        val getDragBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_drag", GET_DRAG_HASH)

        private const val SET_DRAG_DAMPING_CURVE_HASH = 1447180063L
        @JvmField
        val setDragDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_drag_damping_curve", SET_DRAG_DAMPING_CURVE_HASH)

        private const val GET_DRAG_DAMPING_CURVE_HASH = 747537754L
        @JvmField
        val getDragDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_drag_damping_curve", GET_DRAG_DAMPING_CURVE_HASH)

        private const val SET_GRAVITY_HASH = 1602489585L
        @JvmField
        val setGravityBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_gravity", SET_GRAVITY_HASH)

        private const val GET_GRAVITY_HASH = 2339986948L
        @JvmField
        val getGravityBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_gravity", GET_GRAVITY_HASH)

        private const val SET_GRAVITY_DAMPING_CURVE_HASH = 1447180063L
        @JvmField
        val setGravityDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_gravity_damping_curve", SET_GRAVITY_DAMPING_CURVE_HASH)

        private const val GET_GRAVITY_DAMPING_CURVE_HASH = 747537754L
        @JvmField
        val getGravityDampingCurveBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_gravity_damping_curve", GET_GRAVITY_DAMPING_CURVE_HASH)

        private const val SET_GRAVITY_DIRECTION_HASH = 1530502735L
        @JvmField
        val setGravityDirectionBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_gravity_direction", SET_GRAVITY_DIRECTION_HASH)

        private const val GET_GRAVITY_DIRECTION_HASH = 711720468L
        @JvmField
        val getGravityDirectionBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_gravity_direction", GET_GRAVITY_DIRECTION_HASH)

        private const val SET_SETTING_COUNT_HASH = 1286410249L
        @JvmField
        val setSettingCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_setting_count", SET_SETTING_COUNT_HASH)

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        @JvmField
        val getSettingCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_setting_count", GET_SETTING_COUNT_HASH)

        private const val CLEAR_SETTINGS_HASH = 3218959716L
        @JvmField
        val clearSettingsBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "clear_settings", CLEAR_SETTINGS_HASH)

        private const val SET_INDIVIDUAL_CONFIG_HASH = 300928843L
        @JvmField
        val setIndividualConfigBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_individual_config", SET_INDIVIDUAL_CONFIG_HASH)

        private const val IS_CONFIG_INDIVIDUAL_HASH = 1116898809L
        @JvmField
        val isConfigIndividualBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "is_config_individual", IS_CONFIG_INDIVIDUAL_HASH)

        private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
        @JvmField
        val getJointBoneNameBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)

        private const val GET_JOINT_BONE_HASH = 3175239445L
        @JvmField
        val getJointBoneBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_bone", GET_JOINT_BONE_HASH)

        private const val SET_JOINT_ROTATION_AXIS_HASH = 1391134969L
        @JvmField
        val setJointRotationAxisBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_rotation_axis", SET_JOINT_ROTATION_AXIS_HASH)

        private const val GET_JOINT_ROTATION_AXIS_HASH = 3312594080L
        @JvmField
        val getJointRotationAxisBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_rotation_axis", GET_JOINT_ROTATION_AXIS_HASH)

        private const val SET_JOINT_ROTATION_AXIS_VECTOR_HASH = 2866752138L
        @JvmField
        val setJointRotationAxisVectorBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_rotation_axis_vector", SET_JOINT_ROTATION_AXIS_VECTOR_HASH)

        private const val GET_JOINT_ROTATION_AXIS_VECTOR_HASH = 1592972041L
        @JvmField
        val getJointRotationAxisVectorBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_rotation_axis_vector", GET_JOINT_ROTATION_AXIS_VECTOR_HASH)

        private const val SET_JOINT_RADIUS_HASH = 3506521499L
        @JvmField
        val setJointRadiusBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_radius", SET_JOINT_RADIUS_HASH)

        private const val GET_JOINT_RADIUS_HASH = 3085491603L
        @JvmField
        val getJointRadiusBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_radius", GET_JOINT_RADIUS_HASH)

        private const val SET_JOINT_STIFFNESS_HASH = 3506521499L
        @JvmField
        val setJointStiffnessBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_stiffness", SET_JOINT_STIFFNESS_HASH)

        private const val GET_JOINT_STIFFNESS_HASH = 3085491603L
        @JvmField
        val getJointStiffnessBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_stiffness", GET_JOINT_STIFFNESS_HASH)

        private const val SET_JOINT_DRAG_HASH = 3506521499L
        @JvmField
        val setJointDragBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_drag", SET_JOINT_DRAG_HASH)

        private const val GET_JOINT_DRAG_HASH = 3085491603L
        @JvmField
        val getJointDragBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_drag", GET_JOINT_DRAG_HASH)

        private const val SET_JOINT_GRAVITY_HASH = 3506521499L
        @JvmField
        val setJointGravityBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_gravity", SET_JOINT_GRAVITY_HASH)

        private const val GET_JOINT_GRAVITY_HASH = 3085491603L
        @JvmField
        val getJointGravityBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_gravity", GET_JOINT_GRAVITY_HASH)

        private const val SET_JOINT_GRAVITY_DIRECTION_HASH = 2866752138L
        @JvmField
        val setJointGravityDirectionBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_gravity_direction", SET_JOINT_GRAVITY_DIRECTION_HASH)

        private const val GET_JOINT_GRAVITY_DIRECTION_HASH = 1592972041L
        @JvmField
        val getJointGravityDirectionBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_gravity_direction", GET_JOINT_GRAVITY_DIRECTION_HASH)

        private const val GET_JOINT_COUNT_HASH = 923996154L
        @JvmField
        val getJointCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_count", GET_JOINT_COUNT_HASH)

        private const val SET_ENABLE_ALL_CHILD_COLLISIONS_HASH = 300928843L
        @JvmField
        val setEnableAllChildCollisionsBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_enable_all_child_collisions", SET_ENABLE_ALL_CHILD_COLLISIONS_HASH)

        private const val ARE_ALL_CHILD_COLLISIONS_ENABLED_HASH = 1116898809L
        @JvmField
        val areAllChildCollisionsEnabledBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "are_all_child_collisions_enabled", ARE_ALL_CHILD_COLLISIONS_ENABLED_HASH)

        private const val SET_EXCLUDE_COLLISION_PATH_HASH = 132481804L
        @JvmField
        val setExcludeCollisionPathBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_exclude_collision_path", SET_EXCLUDE_COLLISION_PATH_HASH)

        private const val GET_EXCLUDE_COLLISION_PATH_HASH = 464924783L
        @JvmField
        val getExcludeCollisionPathBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_exclude_collision_path", GET_EXCLUDE_COLLISION_PATH_HASH)

        private const val SET_EXCLUDE_COLLISION_COUNT_HASH = 3937882851L
        @JvmField
        val setExcludeCollisionCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_exclude_collision_count", SET_EXCLUDE_COLLISION_COUNT_HASH)

        private const val GET_EXCLUDE_COLLISION_COUNT_HASH = 923996154L
        @JvmField
        val getExcludeCollisionCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_exclude_collision_count", GET_EXCLUDE_COLLISION_COUNT_HASH)

        private const val CLEAR_EXCLUDE_COLLISIONS_HASH = 1286410249L
        @JvmField
        val clearExcludeCollisionsBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "clear_exclude_collisions", CLEAR_EXCLUDE_COLLISIONS_HASH)

        private const val SET_COLLISION_PATH_HASH = 132481804L
        @JvmField
        val setCollisionPathBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_collision_path", SET_COLLISION_PATH_HASH)

        private const val GET_COLLISION_PATH_HASH = 464924783L
        @JvmField
        val getCollisionPathBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_collision_path", GET_COLLISION_PATH_HASH)

        private const val SET_COLLISION_COUNT_HASH = 3937882851L
        @JvmField
        val setCollisionCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_collision_count", SET_COLLISION_COUNT_HASH)

        private const val GET_COLLISION_COUNT_HASH = 923996154L
        @JvmField
        val getCollisionCountBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_collision_count", GET_COLLISION_COUNT_HASH)

        private const val CLEAR_COLLISIONS_HASH = 1286410249L
        @JvmField
        val clearCollisionsBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "clear_collisions", CLEAR_COLLISIONS_HASH)

        private const val SET_EXTERNAL_FORCE_HASH = 3460891852L
        @JvmField
        val setExternalForceBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_external_force", SET_EXTERNAL_FORCE_HASH)

        private const val GET_EXTERNAL_FORCE_HASH = 3360562783L
        @JvmField
        val getExternalForceBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_external_force", GET_EXTERNAL_FORCE_HASH)

        private const val SET_MUTABLE_BONE_AXES_HASH = 2586408642L
        @JvmField
        val setMutableBoneAxesBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_mutable_bone_axes", SET_MUTABLE_BONE_AXES_HASH)

        private const val ARE_BONE_AXES_MUTABLE_HASH = 36873697L
        @JvmField
        val areBoneAxesMutableBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "are_bone_axes_mutable", ARE_BONE_AXES_MUTABLE_HASH)

        private const val RESET_HASH = 3218959716L
        @JvmField
        val resetBind =
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "reset", RESET_HASH)
    }
}

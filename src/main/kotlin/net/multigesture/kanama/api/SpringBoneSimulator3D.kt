package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3

/**
 * A `SkeletonModifier3D` to apply inertial wavering to bone chains.
 *
 * Generated from Godot docs: SpringBoneSimulator3D
 */
class SpringBoneSimulator3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
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

    fun setEndBoneLength(index: Int, length: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setEndBoneLengthBind, handle, index, length)
    }

    fun getEndBoneLength(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getEndBoneLengthBind, handle, index)
    }

    fun setCenterFrom(index: Int, centerFrom: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCenterFromBind, handle, index, centerFrom)
    }

    fun getCenterFrom(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCenterFromBind, handle, index)
    }

    fun setCenterNode(index: Int, nodePath: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setCenterNodeBind, handle, index, nodePath)
    }

    fun getCenterNode(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getCenterNodeBind, handle, index)
    }

    fun setCenterBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setCenterBoneNameBind, handle, index, boneName)
    }

    fun getCenterBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getCenterBoneNameBind, handle, index)
    }

    fun setCenterBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setCenterBoneBind, handle, index, bone)
    }

    fun getCenterBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCenterBoneBind, handle, index)
    }

    fun setRadius(index: Int, radius: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setRadiusBind, handle, index, radius)
    }

    fun getRadius(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getRadiusBind, handle, index)
    }

    fun setRotationAxis(index: Int, axis: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setRotationAxisBind, handle, index, axis)
    }

    fun getRotationAxis(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getRotationAxisBind, handle, index)
    }

    fun setRotationAxisVector(index: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setRotationAxisVectorBind, handle, index, vector)
    }

    fun getRotationAxisVector(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getRotationAxisVectorBind, handle, index)
    }

    fun setRadiusDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setRadiusDampingCurveBind, handle, index, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getRadiusDampingCurve(index: Int): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getRadiusDampingCurveBind, handle, index))
    }

    fun setStiffness(index: Int, stiffness: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setStiffnessBind, handle, index, stiffness)
    }

    fun getStiffness(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getStiffnessBind, handle, index)
    }

    fun setStiffnessDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setStiffnessDampingCurveBind, handle, index, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getStiffnessDampingCurve(index: Int): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getStiffnessDampingCurveBind, handle, index))
    }

    fun setDrag(index: Int, drag: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setDragBind, handle, index, drag)
    }

    fun getDrag(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getDragBind, handle, index)
    }

    fun setDragDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setDragDampingCurveBind, handle, index, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getDragDampingCurve(index: Int): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getDragDampingCurveBind, handle, index))
    }

    fun setGravity(index: Int, gravity: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setGravityBind, handle, index, gravity)
    }

    fun getGravity(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getGravityBind, handle, index)
    }

    fun setGravityDampingCurve(index: Int, curve: Curve?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setGravityDampingCurveBind, handle, index, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getGravityDampingCurve(index: Int): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getGravityDampingCurveBind, handle, index))
    }

    fun setGravityDirection(index: Int, gravityDirection: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setGravityDirectionBind, handle, index, gravityDirection)
    }

    fun getGravityDirection(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getGravityDirectionBind, handle, index)
    }

    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setSettingCountBind, handle, count)
    }

    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSettingCountBind, handle)
    }

    fun clearSettings() {
        ObjectCalls.ptrcallNoArgs(clearSettingsBind, handle)
    }

    fun setIndividualConfig(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setIndividualConfigBind, handle, index, enabled)
    }

    fun isConfigIndividual(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isConfigIndividualBind, handle, index)
    }

    fun getJointBoneName(index: Int, joint: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getJointBoneNameBind, handle, index, joint)
    }

    fun getJointBone(index: Int, joint: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getJointBoneBind, handle, index, joint)
    }

    fun setJointRotationAxis(index: Int, joint: Int, axis: Long) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(setJointRotationAxisBind, handle, index, joint, axis)
    }

    fun getJointRotationAxis(index: Int, joint: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getJointRotationAxisBind, handle, index, joint)
    }

    fun setJointRotationAxisVector(index: Int, joint: Int, vector: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(setJointRotationAxisVectorBind, handle, index, joint, vector)
    }

    fun getJointRotationAxisVector(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(getJointRotationAxisVectorBind, handle, index, joint)
    }

    fun setJointRadius(index: Int, joint: Int, radius: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setJointRadiusBind, handle, index, joint, radius)
    }

    fun getJointRadius(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getJointRadiusBind, handle, index, joint)
    }

    fun setJointStiffness(index: Int, joint: Int, stiffness: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setJointStiffnessBind, handle, index, joint, stiffness)
    }

    fun getJointStiffness(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getJointStiffnessBind, handle, index, joint)
    }

    fun setJointDrag(index: Int, joint: Int, drag: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setJointDragBind, handle, index, joint, drag)
    }

    fun getJointDrag(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getJointDragBind, handle, index, joint)
    }

    fun setJointGravity(index: Int, joint: Int, gravity: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setJointGravityBind, handle, index, joint, gravity)
    }

    fun getJointGravity(index: Int, joint: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getJointGravityBind, handle, index, joint)
    }

    fun setJointGravityDirection(index: Int, joint: Int, gravityDirection: Vector3) {
        ObjectCalls.ptrcallWithTwoIntAndVector3Arg(setJointGravityDirectionBind, handle, index, joint, gravityDirection)
    }

    fun getJointGravityDirection(index: Int, joint: Int): Vector3 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector3(getJointGravityDirectionBind, handle, index, joint)
    }

    fun getJointCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getJointCountBind, handle, index)
    }

    fun setEnableAllChildCollisions(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setEnableAllChildCollisionsBind, handle, index, enabled)
    }

    fun areAllChildCollisionsEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(areAllChildCollisionsEnabledBind, handle, index)
    }

    fun setExcludeCollisionPath(index: Int, collision: Int, nodePath: NodePath) {
        ObjectCalls.ptrcallWithTwoIntAndNodePathArg(setExcludeCollisionPathBind, handle, index, collision, nodePath)
    }

    fun getExcludeCollisionPath(index: Int, collision: Int): NodePath {
        return ObjectCalls.ptrcallWithTwoIntArgsRetNodePath(getExcludeCollisionPathBind, handle, index, collision)
    }

    fun setExcludeCollisionCount(index: Int, count: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setExcludeCollisionCountBind, handle, index, count)
    }

    fun getExcludeCollisionCount(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getExcludeCollisionCountBind, handle, index)
    }

    fun clearExcludeCollisions(index: Int) {
        ObjectCalls.ptrcallWithIntArg(clearExcludeCollisionsBind, handle, index)
    }

    fun setCollisionPath(index: Int, collision: Int, nodePath: NodePath) {
        ObjectCalls.ptrcallWithTwoIntAndNodePathArg(setCollisionPathBind, handle, index, collision, nodePath)
    }

    fun getCollisionPath(index: Int, collision: Int): NodePath {
        return ObjectCalls.ptrcallWithTwoIntArgsRetNodePath(getCollisionPathBind, handle, index, collision)
    }

    fun setCollisionCount(index: Int, count: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setCollisionCountBind, handle, index, count)
    }

    fun getCollisionCount(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCollisionCountBind, handle, index)
    }

    fun clearCollisions(index: Int) {
        ObjectCalls.ptrcallWithIntArg(clearCollisionsBind, handle, index)
    }

    fun setExternalForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setExternalForceBind, handle, force)
    }

    fun getExternalForce(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getExternalForceBind, handle)
    }

    fun setMutableBoneAxes(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMutableBoneAxesBind, handle, enabled)
    }

    fun areBoneAxesMutable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areBoneAxesMutableBind, handle)
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
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    companion object {
        const val CENTER_FROM_WORLD_ORIGIN: Long = 0L
        const val CENTER_FROM_NODE: Long = 1L
        const val CENTER_FROM_BONE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpringBoneSimulator3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpringBoneSimulator3D? =
            if (handle.address() == 0L) null else SpringBoneSimulator3D(handle)

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        private val setRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)
        }

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        private val getRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
        }

        private const val SET_ROOT_BONE_HASH = 3937882851L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 923996154L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_END_BONE_NAME_HASH = 501894301L
        private val setEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)
        }

        private const val GET_END_BONE_NAME_HASH = 844755477L
        private val getEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
        }

        private const val SET_END_BONE_HASH = 3937882851L
        private val setEndBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone", SET_END_BONE_HASH)
        }

        private const val GET_END_BONE_HASH = 923996154L
        private val getEndBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone", GET_END_BONE_HASH)
        }

        private const val SET_EXTEND_END_BONE_HASH = 300928843L
        private val setExtendEndBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_extend_end_bone", SET_EXTEND_END_BONE_HASH)
        }

        private const val IS_END_BONE_EXTENDED_HASH = 1116898809L
        private val isEndBoneExtendedBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "is_end_bone_extended", IS_END_BONE_EXTENDED_HASH)
        }

        private const val SET_END_BONE_DIRECTION_HASH = 2838484201L
        private val setEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone_direction", SET_END_BONE_DIRECTION_HASH)
        }

        private const val GET_END_BONE_DIRECTION_HASH = 1843036459L
        private val getEndBoneDirectionBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_direction", GET_END_BONE_DIRECTION_HASH)
        }

        private const val SET_END_BONE_LENGTH_HASH = 1602489585L
        private val setEndBoneLengthBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_end_bone_length", SET_END_BONE_LENGTH_HASH)
        }

        private const val GET_END_BONE_LENGTH_HASH = 2339986948L
        private val getEndBoneLengthBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_end_bone_length", GET_END_BONE_LENGTH_HASH)
        }

        private const val SET_CENTER_FROM_HASH = 2551505749L
        private val setCenterFromBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_from", SET_CENTER_FROM_HASH)
        }

        private const val GET_CENTER_FROM_HASH = 2721930813L
        private val getCenterFromBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_from", GET_CENTER_FROM_HASH)
        }

        private const val SET_CENTER_NODE_HASH = 2761262315L
        private val setCenterNodeBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_node", SET_CENTER_NODE_HASH)
        }

        private const val GET_CENTER_NODE_HASH = 408788394L
        private val getCenterNodeBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_node", GET_CENTER_NODE_HASH)
        }

        private const val SET_CENTER_BONE_NAME_HASH = 501894301L
        private val setCenterBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_bone_name", SET_CENTER_BONE_NAME_HASH)
        }

        private const val GET_CENTER_BONE_NAME_HASH = 844755477L
        private val getCenterBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_bone_name", GET_CENTER_BONE_NAME_HASH)
        }

        private const val SET_CENTER_BONE_HASH = 3937882851L
        private val setCenterBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_center_bone", SET_CENTER_BONE_HASH)
        }

        private const val GET_CENTER_BONE_HASH = 923996154L
        private val getCenterBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_center_bone", GET_CENTER_BONE_HASH)
        }

        private const val SET_RADIUS_HASH = 1602489585L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 2339986948L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_ROTATION_AXIS_HASH = 1539703856L
        private val setRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_rotation_axis", SET_ROTATION_AXIS_HASH)
        }

        private const val GET_ROTATION_AXIS_HASH = 2844851118L
        private val getRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_rotation_axis", GET_ROTATION_AXIS_HASH)
        }

        private const val SET_ROTATION_AXIS_VECTOR_HASH = 1530502735L
        private val setRotationAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_rotation_axis_vector", SET_ROTATION_AXIS_VECTOR_HASH)
        }

        private const val GET_ROTATION_AXIS_VECTOR_HASH = 711720468L
        private val getRotationAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_rotation_axis_vector", GET_ROTATION_AXIS_VECTOR_HASH)
        }

        private const val SET_RADIUS_DAMPING_CURVE_HASH = 1447180063L
        private val setRadiusDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_radius_damping_curve", SET_RADIUS_DAMPING_CURVE_HASH)
        }

        private const val GET_RADIUS_DAMPING_CURVE_HASH = 747537754L
        private val getRadiusDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_radius_damping_curve", GET_RADIUS_DAMPING_CURVE_HASH)
        }

        private const val SET_STIFFNESS_HASH = 1602489585L
        private val setStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_stiffness", SET_STIFFNESS_HASH)
        }

        private const val GET_STIFFNESS_HASH = 2339986948L
        private val getStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_stiffness", GET_STIFFNESS_HASH)
        }

        private const val SET_STIFFNESS_DAMPING_CURVE_HASH = 1447180063L
        private val setStiffnessDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_stiffness_damping_curve", SET_STIFFNESS_DAMPING_CURVE_HASH)
        }

        private const val GET_STIFFNESS_DAMPING_CURVE_HASH = 747537754L
        private val getStiffnessDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_stiffness_damping_curve", GET_STIFFNESS_DAMPING_CURVE_HASH)
        }

        private const val SET_DRAG_HASH = 1602489585L
        private val setDragBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_drag", SET_DRAG_HASH)
        }

        private const val GET_DRAG_HASH = 2339986948L
        private val getDragBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_drag", GET_DRAG_HASH)
        }

        private const val SET_DRAG_DAMPING_CURVE_HASH = 1447180063L
        private val setDragDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_drag_damping_curve", SET_DRAG_DAMPING_CURVE_HASH)
        }

        private const val GET_DRAG_DAMPING_CURVE_HASH = 747537754L
        private val getDragDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_drag_damping_curve", GET_DRAG_DAMPING_CURVE_HASH)
        }

        private const val SET_GRAVITY_HASH = 1602489585L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val GET_GRAVITY_HASH = 2339986948L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_GRAVITY_DAMPING_CURVE_HASH = 1447180063L
        private val setGravityDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_gravity_damping_curve", SET_GRAVITY_DAMPING_CURVE_HASH)
        }

        private const val GET_GRAVITY_DAMPING_CURVE_HASH = 747537754L
        private val getGravityDampingCurveBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_gravity_damping_curve", GET_GRAVITY_DAMPING_CURVE_HASH)
        }

        private const val SET_GRAVITY_DIRECTION_HASH = 1530502735L
        private val setGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_gravity_direction", SET_GRAVITY_DIRECTION_HASH)
        }

        private const val GET_GRAVITY_DIRECTION_HASH = 711720468L
        private val getGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_gravity_direction", GET_GRAVITY_DIRECTION_HASH)
        }

        private const val SET_SETTING_COUNT_HASH = 1286410249L
        private val setSettingCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_setting_count", SET_SETTING_COUNT_HASH)
        }

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        private val getSettingCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_setting_count", GET_SETTING_COUNT_HASH)
        }

        private const val CLEAR_SETTINGS_HASH = 3218959716L
        private val clearSettingsBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "clear_settings", CLEAR_SETTINGS_HASH)
        }

        private const val SET_INDIVIDUAL_CONFIG_HASH = 300928843L
        private val setIndividualConfigBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_individual_config", SET_INDIVIDUAL_CONFIG_HASH)
        }

        private const val IS_CONFIG_INDIVIDUAL_HASH = 1116898809L
        private val isConfigIndividualBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "is_config_individual", IS_CONFIG_INDIVIDUAL_HASH)
        }

        private const val GET_JOINT_BONE_NAME_HASH = 1391810591L
        private val getJointBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_bone_name", GET_JOINT_BONE_NAME_HASH)
        }

        private const val GET_JOINT_BONE_HASH = 3175239445L
        private val getJointBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_bone", GET_JOINT_BONE_HASH)
        }

        private const val SET_JOINT_ROTATION_AXIS_HASH = 1391134969L
        private val setJointRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_rotation_axis", SET_JOINT_ROTATION_AXIS_HASH)
        }

        private const val GET_JOINT_ROTATION_AXIS_HASH = 3312594080L
        private val getJointRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_rotation_axis", GET_JOINT_ROTATION_AXIS_HASH)
        }

        private const val SET_JOINT_ROTATION_AXIS_VECTOR_HASH = 2866752138L
        private val setJointRotationAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_rotation_axis_vector", SET_JOINT_ROTATION_AXIS_VECTOR_HASH)
        }

        private const val GET_JOINT_ROTATION_AXIS_VECTOR_HASH = 1592972041L
        private val getJointRotationAxisVectorBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_rotation_axis_vector", GET_JOINT_ROTATION_AXIS_VECTOR_HASH)
        }

        private const val SET_JOINT_RADIUS_HASH = 3506521499L
        private val setJointRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_radius", SET_JOINT_RADIUS_HASH)
        }

        private const val GET_JOINT_RADIUS_HASH = 3085491603L
        private val getJointRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_radius", GET_JOINT_RADIUS_HASH)
        }

        private const val SET_JOINT_STIFFNESS_HASH = 3506521499L
        private val setJointStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_stiffness", SET_JOINT_STIFFNESS_HASH)
        }

        private const val GET_JOINT_STIFFNESS_HASH = 3085491603L
        private val getJointStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_stiffness", GET_JOINT_STIFFNESS_HASH)
        }

        private const val SET_JOINT_DRAG_HASH = 3506521499L
        private val setJointDragBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_drag", SET_JOINT_DRAG_HASH)
        }

        private const val GET_JOINT_DRAG_HASH = 3085491603L
        private val getJointDragBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_drag", GET_JOINT_DRAG_HASH)
        }

        private const val SET_JOINT_GRAVITY_HASH = 3506521499L
        private val setJointGravityBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_gravity", SET_JOINT_GRAVITY_HASH)
        }

        private const val GET_JOINT_GRAVITY_HASH = 3085491603L
        private val getJointGravityBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_gravity", GET_JOINT_GRAVITY_HASH)
        }

        private const val SET_JOINT_GRAVITY_DIRECTION_HASH = 2866752138L
        private val setJointGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_joint_gravity_direction", SET_JOINT_GRAVITY_DIRECTION_HASH)
        }

        private const val GET_JOINT_GRAVITY_DIRECTION_HASH = 1592972041L
        private val getJointGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_gravity_direction", GET_JOINT_GRAVITY_DIRECTION_HASH)
        }

        private const val GET_JOINT_COUNT_HASH = 923996154L
        private val getJointCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_joint_count", GET_JOINT_COUNT_HASH)
        }

        private const val SET_ENABLE_ALL_CHILD_COLLISIONS_HASH = 300928843L
        private val setEnableAllChildCollisionsBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_enable_all_child_collisions", SET_ENABLE_ALL_CHILD_COLLISIONS_HASH)
        }

        private const val ARE_ALL_CHILD_COLLISIONS_ENABLED_HASH = 1116898809L
        private val areAllChildCollisionsEnabledBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "are_all_child_collisions_enabled", ARE_ALL_CHILD_COLLISIONS_ENABLED_HASH)
        }

        private const val SET_EXCLUDE_COLLISION_PATH_HASH = 132481804L
        private val setExcludeCollisionPathBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_exclude_collision_path", SET_EXCLUDE_COLLISION_PATH_HASH)
        }

        private const val GET_EXCLUDE_COLLISION_PATH_HASH = 464924783L
        private val getExcludeCollisionPathBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_exclude_collision_path", GET_EXCLUDE_COLLISION_PATH_HASH)
        }

        private const val SET_EXCLUDE_COLLISION_COUNT_HASH = 3937882851L
        private val setExcludeCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_exclude_collision_count", SET_EXCLUDE_COLLISION_COUNT_HASH)
        }

        private const val GET_EXCLUDE_COLLISION_COUNT_HASH = 923996154L
        private val getExcludeCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_exclude_collision_count", GET_EXCLUDE_COLLISION_COUNT_HASH)
        }

        private const val CLEAR_EXCLUDE_COLLISIONS_HASH = 1286410249L
        private val clearExcludeCollisionsBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "clear_exclude_collisions", CLEAR_EXCLUDE_COLLISIONS_HASH)
        }

        private const val SET_COLLISION_PATH_HASH = 132481804L
        private val setCollisionPathBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_collision_path", SET_COLLISION_PATH_HASH)
        }

        private const val GET_COLLISION_PATH_HASH = 464924783L
        private val getCollisionPathBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_collision_path", GET_COLLISION_PATH_HASH)
        }

        private const val SET_COLLISION_COUNT_HASH = 3937882851L
        private val setCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_collision_count", SET_COLLISION_COUNT_HASH)
        }

        private const val GET_COLLISION_COUNT_HASH = 923996154L
        private val getCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_collision_count", GET_COLLISION_COUNT_HASH)
        }

        private const val CLEAR_COLLISIONS_HASH = 1286410249L
        private val clearCollisionsBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "clear_collisions", CLEAR_COLLISIONS_HASH)
        }

        private const val SET_EXTERNAL_FORCE_HASH = 3460891852L
        private val setExternalForceBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_external_force", SET_EXTERNAL_FORCE_HASH)
        }

        private const val GET_EXTERNAL_FORCE_HASH = 3360562783L
        private val getExternalForceBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "get_external_force", GET_EXTERNAL_FORCE_HASH)
        }

        private const val SET_MUTABLE_BONE_AXES_HASH = 2586408642L
        private val setMutableBoneAxesBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "set_mutable_bone_axes", SET_MUTABLE_BONE_AXES_HASH)
        }

        private const val ARE_BONE_AXES_MUTABLE_HASH = 36873697L
        private val areBoneAxesMutableBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "are_bone_axes_mutable", ARE_BONE_AXES_MUTABLE_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneSimulator3D", "reset", RESET_HASH)
        }
    }
}

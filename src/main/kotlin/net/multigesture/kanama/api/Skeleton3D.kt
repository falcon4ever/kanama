package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A node containing a bone hierarchy, used to create a 3D skeletal animation.
 *
 * Generated from Godot docs: Skeleton3D
 */
class Skeleton3D(handle: MemorySegment) : Node3D(handle) {
    var motionScale: Double
        @JvmName("motionScaleProperty")
        get() = getMotionScale()
        @JvmName("setMotionScaleProperty")
        set(value) = setMotionScale(value)

    var showRestOnly: Boolean
        @JvmName("showRestOnlyProperty")
        get() = isShowRestOnly()
        @JvmName("setShowRestOnlyProperty")
        set(value) = setShowRestOnly(value)

    var modifierCallbackModeProcess: Long
        @JvmName("modifierCallbackModeProcessProperty")
        get() = getModifierCallbackModeProcess()
        @JvmName("setModifierCallbackModeProcessProperty")
        set(value) = setModifierCallbackModeProcess(value)

    var animatePhysicalBones: Boolean
        @JvmName("animatePhysicalBonesProperty")
        get() = getAnimatePhysicalBones()
        @JvmName("setAnimatePhysicalBonesProperty")
        set(value) = setAnimatePhysicalBones(value)

    fun addBone(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(addBoneBind, handle, name)
    }

    fun findBone(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findBoneBind, handle, name)
    }

    fun getBoneName(boneIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getBoneNameBind, handle, boneIdx)
    }

    fun setBoneName(boneIdx: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setBoneNameBind, handle, boneIdx, name)
    }

    fun getBoneMeta(boneIdx: Int, key: String): Any? {
        return ObjectCalls.ptrcallWithIntAndStringNameArgRetVariantScalar(getBoneMetaBind, handle, boneIdx, key)
    }

    fun getBoneMetaList(boneIdx: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetStringNameList(getBoneMetaListBind, handle, boneIdx)
    }

    fun hasBoneMeta(boneIdx: Int, key: String): Boolean {
        return ObjectCalls.ptrcallWithIntAndStringNameArgRetBool(hasBoneMetaBind, handle, boneIdx, key)
    }

    fun setBoneMeta(boneIdx: Int, key: String, value: Any?) {
        ObjectCalls.ptrcallWithIntStringNameAndVariantArg(setBoneMetaBind, handle, boneIdx, key, value)
    }

    fun getConcatenatedBoneNames(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getConcatenatedBoneNamesBind, handle)
    }

    fun getBoneParent(boneIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getBoneParentBind, handle, boneIdx)
    }

    fun setBoneParent(boneIdx: Int, parentIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setBoneParentBind, handle, boneIdx, parentIdx)
    }

    fun getBoneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneCountBind, handle)
    }

    fun getVersion(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVersionBind, handle)
    }

    fun unparentBoneAndRest(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(unparentBoneAndRestBind, handle, boneIdx)
    }

    fun getBoneChildren(boneIdx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getBoneChildrenBind, handle, boneIdx)
    }

    fun getParentlessBones(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getParentlessBonesBind, handle)
    }

    fun getBoneRest(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneRestBind, handle, boneIdx)
    }

    fun setBoneRest(boneIdx: Int, rest: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBoneRestBind, handle, boneIdx, rest)
    }

    fun getBoneGlobalRest(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalRestBind, handle, boneIdx)
    }

    fun createSkinFromRestTransforms(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(createSkinFromRestTransformsBind, handle))
    }

    fun registerSkin(skin: Skin?): SkinReference? {
        return SkinReference.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(registerSkinBind, handle, skin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun localizeRests() {
        ObjectCalls.ptrcallNoArgs(localizeRestsBind, handle)
    }

    fun clearBones() {
        ObjectCalls.ptrcallNoArgs(clearBonesBind, handle)
    }

    fun getBonePose(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBonePoseBind, handle, boneIdx)
    }

    fun setBonePose(boneIdx: Int, pose: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBonePoseBind, handle, boneIdx, pose)
    }

    fun setBonePosePosition(boneIdx: Int, position: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setBonePosePositionBind, handle, boneIdx, position)
    }

    fun setBonePoseRotation(boneIdx: Int, rotation: Quaternion) {
        ObjectCalls.ptrcallWithIntAndQuaternionArg(setBonePoseRotationBind, handle, boneIdx, rotation)
    }

    fun setBonePoseScale(boneIdx: Int, scale: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setBonePoseScaleBind, handle, boneIdx, scale)
    }

    fun getBonePosePosition(boneIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getBonePosePositionBind, handle, boneIdx)
    }

    fun getBonePoseRotation(boneIdx: Int): Quaternion {
        return ObjectCalls.ptrcallWithIntArgRetQuaternion(getBonePoseRotationBind, handle, boneIdx)
    }

    fun getBonePoseScale(boneIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getBonePoseScaleBind, handle, boneIdx)
    }

    fun resetBonePose(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(resetBonePoseBind, handle, boneIdx)
    }

    fun resetBonePoses() {
        ObjectCalls.ptrcallNoArgs(resetBonePosesBind, handle)
    }

    fun isBoneEnabled(boneIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isBoneEnabledBind, handle, boneIdx)
    }

    fun setBoneEnabled(boneIdx: Int, enabled: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBoneEnabledBind, handle, boneIdx, enabled)
    }

    fun getBoneGlobalPose(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalPoseBind, handle, boneIdx)
    }

    fun setBoneGlobalPose(boneIdx: Int, pose: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBoneGlobalPoseBind, handle, boneIdx, pose)
    }

    fun forceUpdateAllBoneTransforms() {
        ObjectCalls.ptrcallNoArgs(forceUpdateAllBoneTransformsBind, handle)
    }

    fun forceUpdateBoneChildTransform(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(forceUpdateBoneChildTransformBind, handle, boneIdx)
    }

    fun setMotionScale(motionScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMotionScaleBind, handle, motionScale)
    }

    fun getMotionScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMotionScaleBind, handle)
    }

    fun setShowRestOnly(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowRestOnlyBind, handle, enabled)
    }

    fun isShowRestOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShowRestOnlyBind, handle)
    }

    fun setModifierCallbackModeProcess(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModifierCallbackModeProcessBind, handle, mode)
    }

    fun getModifierCallbackModeProcess(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModifierCallbackModeProcessBind, handle)
    }

    /**
     * Manually advance the child `SkeletonModifier3D`s by the specified time (in seconds). Note: The
     * `delta` is temporarily accumulated in the `Skeleton3D`, and the deferred process uses the
     * accumulated value to process the modification.
     *
     * Generated from Godot docs: Skeleton3D.advance
     */
    fun advance(delta: Double) {
        ObjectCalls.ptrcallWithDoubleArg(advanceBind, handle, delta)
    }

    fun clearBonesGlobalPoseOverride() {
        ObjectCalls.ptrcallNoArgs(clearBonesGlobalPoseOverrideBind, handle)
    }

    fun setBoneGlobalPoseOverride(boneIdx: Int, pose: Transform3D, amount: Double, persistent: Boolean = false) {
        ObjectCalls.ptrcallWithIntTransform3DDoubleBoolArgs(setBoneGlobalPoseOverrideBind, handle, boneIdx, pose, amount, persistent)
    }

    fun getBoneGlobalPoseOverride(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalPoseOverrideBind, handle, boneIdx)
    }

    fun getBoneGlobalPoseNoOverride(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalPoseNoOverrideBind, handle, boneIdx)
    }

    fun setAnimatePhysicalBones(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAnimatePhysicalBonesBind, handle, enabled)
    }

    fun getAnimatePhysicalBones(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAnimatePhysicalBonesBind, handle)
    }

    fun physicalBonesStopSimulation() {
        ObjectCalls.ptrcallNoArgs(physicalBonesStopSimulationBind, handle)
    }

    fun physicalBonesStartSimulation(bones: List<String>) {
        ObjectCalls.ptrcallWithStringNameListArg(physicalBonesStartSimulationBind, handle, bones)
    }

    fun physicalBonesAddCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(physicalBonesAddCollisionExceptionBind, handle, exception)
    }

    fun physicalBonesRemoveCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(physicalBonesRemoveCollisionExceptionBind, handle, exception)
    }

    object Signals {
        const val restUpdated: String = "rest_updated"
        const val poseUpdated: String = "pose_updated"
        const val skeletonUpdated: String = "skeleton_updated"
        const val boneEnabledChanged: String = "bone_enabled_changed"
        const val boneListChanged: String = "bone_list_changed"
        const val showRestOnlyChanged: String = "show_rest_only_changed"
    }

    companion object {
        const val NOTIFICATION_UPDATE_SKELETON: Long = 50L
        const val MODIFIER_CALLBACK_MODE_PROCESS_PHYSICS: Long = 0L
        const val MODIFIER_CALLBACK_MODE_PROCESS_IDLE: Long = 1L
        const val MODIFIER_CALLBACK_MODE_PROCESS_MANUAL: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Skeleton3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Skeleton3D? =
            if (handle.address() == 0L) null else Skeleton3D(handle)

        private const val ADD_BONE_HASH = 1597066294L
        private val addBoneBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "add_bone", ADD_BONE_HASH)
        }

        private const val FIND_BONE_HASH = 1321353865L
        private val findBoneBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "find_bone", FIND_BONE_HASH)
        }

        private const val GET_BONE_NAME_HASH = 844755477L
        private val getBoneNameBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_name", GET_BONE_NAME_HASH)
        }

        private const val SET_BONE_NAME_HASH = 501894301L
        private val setBoneNameBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_name", SET_BONE_NAME_HASH)
        }

        private const val GET_BONE_META_HASH = 203112058L
        private val getBoneMetaBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_meta", GET_BONE_META_HASH)
        }

        private const val GET_BONE_META_LIST_HASH = 663333327L
        private val getBoneMetaListBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_meta_list", GET_BONE_META_LIST_HASH)
        }

        private const val HAS_BONE_META_HASH = 921227809L
        private val hasBoneMetaBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "has_bone_meta", HAS_BONE_META_HASH)
        }

        private const val SET_BONE_META_HASH = 702482756L
        private val setBoneMetaBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_meta", SET_BONE_META_HASH)
        }

        private const val GET_CONCATENATED_BONE_NAMES_HASH = 2002593661L
        private val getConcatenatedBoneNamesBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_concatenated_bone_names", GET_CONCATENATED_BONE_NAMES_HASH)
        }

        private const val GET_BONE_PARENT_HASH = 923996154L
        private val getBoneParentBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_parent", GET_BONE_PARENT_HASH)
        }

        private const val SET_BONE_PARENT_HASH = 3937882851L
        private val setBoneParentBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_parent", SET_BONE_PARENT_HASH)
        }

        private const val GET_BONE_COUNT_HASH = 3905245786L
        private val getBoneCountBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_count", GET_BONE_COUNT_HASH)
        }

        private const val GET_VERSION_HASH = 3905245786L
        private val getVersionBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_version", GET_VERSION_HASH)
        }

        private const val UNPARENT_BONE_AND_REST_HASH = 1286410249L
        private val unparentBoneAndRestBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "unparent_bone_and_rest", UNPARENT_BONE_AND_REST_HASH)
        }

        private const val GET_BONE_CHILDREN_HASH = 1706082319L
        private val getBoneChildrenBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_children", GET_BONE_CHILDREN_HASH)
        }

        private const val GET_PARENTLESS_BONES_HASH = 1930428628L
        private val getParentlessBonesBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_parentless_bones", GET_PARENTLESS_BONES_HASH)
        }

        private const val GET_BONE_REST_HASH = 1965739696L
        private val getBoneRestBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_rest", GET_BONE_REST_HASH)
        }

        private const val SET_BONE_REST_HASH = 3616898986L
        private val setBoneRestBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_rest", SET_BONE_REST_HASH)
        }

        private const val GET_BONE_GLOBAL_REST_HASH = 1965739696L
        private val getBoneGlobalRestBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_global_rest", GET_BONE_GLOBAL_REST_HASH)
        }

        private const val CREATE_SKIN_FROM_REST_TRANSFORMS_HASH = 1032037385L
        private val createSkinFromRestTransformsBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "create_skin_from_rest_transforms", CREATE_SKIN_FROM_REST_TRANSFORMS_HASH)
        }

        private const val REGISTER_SKIN_HASH = 3405789568L
        private val registerSkinBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "register_skin", REGISTER_SKIN_HASH)
        }

        private const val LOCALIZE_RESTS_HASH = 3218959716L
        private val localizeRestsBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "localize_rests", LOCALIZE_RESTS_HASH)
        }

        private const val CLEAR_BONES_HASH = 3218959716L
        private val clearBonesBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "clear_bones", CLEAR_BONES_HASH)
        }

        private const val GET_BONE_POSE_HASH = 1965739696L
        private val getBonePoseBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_pose", GET_BONE_POSE_HASH)
        }

        private const val SET_BONE_POSE_HASH = 3616898986L
        private val setBonePoseBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_pose", SET_BONE_POSE_HASH)
        }

        private const val SET_BONE_POSE_POSITION_HASH = 1530502735L
        private val setBonePosePositionBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_pose_position", SET_BONE_POSE_POSITION_HASH)
        }

        private const val SET_BONE_POSE_ROTATION_HASH = 2823819782L
        private val setBonePoseRotationBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_pose_rotation", SET_BONE_POSE_ROTATION_HASH)
        }

        private const val SET_BONE_POSE_SCALE_HASH = 1530502735L
        private val setBonePoseScaleBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_pose_scale", SET_BONE_POSE_SCALE_HASH)
        }

        private const val GET_BONE_POSE_POSITION_HASH = 711720468L
        private val getBonePosePositionBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_pose_position", GET_BONE_POSE_POSITION_HASH)
        }

        private const val GET_BONE_POSE_ROTATION_HASH = 476865136L
        private val getBonePoseRotationBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_pose_rotation", GET_BONE_POSE_ROTATION_HASH)
        }

        private const val GET_BONE_POSE_SCALE_HASH = 711720468L
        private val getBonePoseScaleBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_pose_scale", GET_BONE_POSE_SCALE_HASH)
        }

        private const val RESET_BONE_POSE_HASH = 1286410249L
        private val resetBonePoseBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "reset_bone_pose", RESET_BONE_POSE_HASH)
        }

        private const val RESET_BONE_POSES_HASH = 3218959716L
        private val resetBonePosesBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "reset_bone_poses", RESET_BONE_POSES_HASH)
        }

        private const val IS_BONE_ENABLED_HASH = 1116898809L
        private val isBoneEnabledBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "is_bone_enabled", IS_BONE_ENABLED_HASH)
        }

        private const val SET_BONE_ENABLED_HASH = 972357352L
        private val setBoneEnabledBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_enabled", SET_BONE_ENABLED_HASH)
        }

        private const val GET_BONE_GLOBAL_POSE_HASH = 1965739696L
        private val getBoneGlobalPoseBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_global_pose", GET_BONE_GLOBAL_POSE_HASH)
        }

        private const val SET_BONE_GLOBAL_POSE_HASH = 3616898986L
        private val setBoneGlobalPoseBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_global_pose", SET_BONE_GLOBAL_POSE_HASH)
        }

        private const val FORCE_UPDATE_ALL_BONE_TRANSFORMS_HASH = 3218959716L
        private val forceUpdateAllBoneTransformsBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "force_update_all_bone_transforms", FORCE_UPDATE_ALL_BONE_TRANSFORMS_HASH)
        }

        private const val FORCE_UPDATE_BONE_CHILD_TRANSFORM_HASH = 1286410249L
        private val forceUpdateBoneChildTransformBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "force_update_bone_child_transform", FORCE_UPDATE_BONE_CHILD_TRANSFORM_HASH)
        }

        private const val SET_MOTION_SCALE_HASH = 373806689L
        private val setMotionScaleBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_motion_scale", SET_MOTION_SCALE_HASH)
        }

        private const val GET_MOTION_SCALE_HASH = 1740695150L
        private val getMotionScaleBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_motion_scale", GET_MOTION_SCALE_HASH)
        }

        private const val SET_SHOW_REST_ONLY_HASH = 2586408642L
        private val setShowRestOnlyBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_show_rest_only", SET_SHOW_REST_ONLY_HASH)
        }

        private const val IS_SHOW_REST_ONLY_HASH = 36873697L
        private val isShowRestOnlyBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "is_show_rest_only", IS_SHOW_REST_ONLY_HASH)
        }

        private const val SET_MODIFIER_CALLBACK_MODE_PROCESS_HASH = 3916362634L
        private val setModifierCallbackModeProcessBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_modifier_callback_mode_process", SET_MODIFIER_CALLBACK_MODE_PROCESS_HASH)
        }

        private const val GET_MODIFIER_CALLBACK_MODE_PROCESS_HASH = 997182536L
        private val getModifierCallbackModeProcessBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_modifier_callback_mode_process", GET_MODIFIER_CALLBACK_MODE_PROCESS_HASH)
        }

        private const val ADVANCE_HASH = 373806689L
        private val advanceBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "advance", ADVANCE_HASH)
        }

        private const val CLEAR_BONES_GLOBAL_POSE_OVERRIDE_HASH = 3218959716L
        private val clearBonesGlobalPoseOverrideBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "clear_bones_global_pose_override", CLEAR_BONES_GLOBAL_POSE_OVERRIDE_HASH)
        }

        private const val SET_BONE_GLOBAL_POSE_OVERRIDE_HASH = 3483398371L
        private val setBoneGlobalPoseOverrideBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_bone_global_pose_override", SET_BONE_GLOBAL_POSE_OVERRIDE_HASH)
        }

        private const val GET_BONE_GLOBAL_POSE_OVERRIDE_HASH = 1965739696L
        private val getBoneGlobalPoseOverrideBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_global_pose_override", GET_BONE_GLOBAL_POSE_OVERRIDE_HASH)
        }

        private const val GET_BONE_GLOBAL_POSE_NO_OVERRIDE_HASH = 1965739696L
        private val getBoneGlobalPoseNoOverrideBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_bone_global_pose_no_override", GET_BONE_GLOBAL_POSE_NO_OVERRIDE_HASH)
        }

        private const val SET_ANIMATE_PHYSICAL_BONES_HASH = 2586408642L
        private val setAnimatePhysicalBonesBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "set_animate_physical_bones", SET_ANIMATE_PHYSICAL_BONES_HASH)
        }

        private const val GET_ANIMATE_PHYSICAL_BONES_HASH = 36873697L
        private val getAnimatePhysicalBonesBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "get_animate_physical_bones", GET_ANIMATE_PHYSICAL_BONES_HASH)
        }

        private const val PHYSICAL_BONES_STOP_SIMULATION_HASH = 3218959716L
        private val physicalBonesStopSimulationBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "physical_bones_stop_simulation", PHYSICAL_BONES_STOP_SIMULATION_HASH)
        }

        private const val PHYSICAL_BONES_START_SIMULATION_HASH = 2787316981L
        private val physicalBonesStartSimulationBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "physical_bones_start_simulation", PHYSICAL_BONES_START_SIMULATION_HASH)
        }

        private const val PHYSICAL_BONES_ADD_COLLISION_EXCEPTION_HASH = 2722037293L
        private val physicalBonesAddCollisionExceptionBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "physical_bones_add_collision_exception", PHYSICAL_BONES_ADD_COLLISION_EXCEPTION_HASH)
        }

        private const val PHYSICAL_BONES_REMOVE_COLLISION_EXCEPTION_HASH = 2722037293L
        private val physicalBonesRemoveCollisionExceptionBind by lazy {
            ObjectCalls.getMethodBind("Skeleton3D", "physical_bones_remove_collision_exception", PHYSICAL_BONES_REMOVE_COLLISION_EXCEPTION_HASH)
        }
    }
}

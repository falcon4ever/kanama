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

    /**
     * Adds a new bone with the given name. Returns the new bone's index, or `-1` if this method fails.
     * Note: Bone names should be unique, non empty, and cannot include the `:` and `/` characters.
     *
     * Generated from Godot docs: Skeleton3D.add_bone
     */
    fun addBone(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(addBoneBind, handle, name)
    }

    /**
     * Returns the bone index that matches `name` as its name. Returns `-1` if no bone with this name
     * exists.
     *
     * Generated from Godot docs: Skeleton3D.find_bone
     */
    fun findBone(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findBoneBind, handle, name)
    }

    /**
     * Returns the name of the bone at index `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_name
     */
    fun getBoneName(boneIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getBoneNameBind, handle, boneIdx)
    }

    /**
     * Sets the bone name, `name`, for the bone at `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_name
     */
    fun setBoneName(boneIdx: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setBoneNameBind, handle, boneIdx, name)
    }

    /**
     * Returns the metadata with the given `key` for the bone at index `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_meta
     */
    fun getBoneMeta(boneIdx: Int, key: String): Any? {
        return ObjectCalls.ptrcallWithIntAndStringNameArgRetVariantScalar(getBoneMetaBind, handle, boneIdx, key)
    }

    /**
     * Returns the list of all metadata keys for the bone at index `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_meta_list
     */
    fun getBoneMetaList(boneIdx: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetStringNameList(getBoneMetaListBind, handle, boneIdx)
    }

    /**
     * Returns `true` if the bone at index `bone_idx` has metadata with the given `key`.
     *
     * Generated from Godot docs: Skeleton3D.has_bone_meta
     */
    fun hasBoneMeta(boneIdx: Int, key: String): Boolean {
        return ObjectCalls.ptrcallWithIntAndStringNameArgRetBool(hasBoneMetaBind, handle, boneIdx, key)
    }

    /**
     * Sets the metadata with the given `key` to `value` for the bone at index `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_meta
     */
    fun setBoneMeta(boneIdx: Int, key: String, value: Any?) {
        ObjectCalls.ptrcallWithIntStringNameAndVariantArg(setBoneMetaBind, handle, boneIdx, key, value)
    }

    /**
     * Returns all bone names concatenated with commas (`,`) as a single `StringName`. It is useful to
     * set it as a hint for the enum property.
     *
     * Generated from Godot docs: Skeleton3D.get_concatenated_bone_names
     */
    fun getConcatenatedBoneNames(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getConcatenatedBoneNamesBind, handle)
    }

    /**
     * Returns the bone index which is the parent of the bone at `bone_idx`. If -1, then bone has no
     * parent. Note: The parent bone returned will always be less than `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_parent
     */
    fun getBoneParent(boneIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getBoneParentBind, handle, boneIdx)
    }

    /**
     * Sets the bone index `parent_idx` as the parent of the bone at `bone_idx`. If -1, then bone has
     * no parent. Note: `parent_idx` must be less than `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_parent
     */
    fun setBoneParent(boneIdx: Int, parentIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setBoneParentBind, handle, boneIdx, parentIdx)
    }

    /**
     * Returns the number of bones in the skeleton.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_count
     */
    fun getBoneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneCountBind, handle)
    }

    /**
     * Returns the number of times the bone hierarchy has changed within this skeleton, including
     * renames. The Skeleton version is not serialized: only use within a single instance of
     * Skeleton3D. Use for invalidating caches in IK solvers and other nodes which process bones.
     *
     * Generated from Godot docs: Skeleton3D.get_version
     */
    fun getVersion(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVersionBind, handle)
    }

    /**
     * Unparents the bone at `bone_idx` and sets its rest position to that of its parent prior to being
     * reset.
     *
     * Generated from Godot docs: Skeleton3D.unparent_bone_and_rest
     */
    fun unparentBoneAndRest(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(unparentBoneAndRestBind, handle, boneIdx)
    }

    /**
     * Returns an array containing the bone indexes of all the child node of the passed in bone,
     * `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_children
     */
    fun getBoneChildren(boneIdx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getBoneChildrenBind, handle, boneIdx)
    }

    /**
     * Returns an array with all of the bones that are parentless. Another way to look at this is that
     * it returns the indexes of all the bones that are not dependent or modified by other bones in the
     * Skeleton.
     *
     * Generated from Godot docs: Skeleton3D.get_parentless_bones
     */
    fun getParentlessBones(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getParentlessBonesBind, handle)
    }

    /**
     * Returns the rest transform for a bone `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_rest
     */
    fun getBoneRest(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneRestBind, handle, boneIdx)
    }

    /**
     * Sets the rest transform for bone `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_rest
     */
    fun setBoneRest(boneIdx: Int, rest: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBoneRestBind, handle, boneIdx, rest)
    }

    /**
     * Returns the global rest transform for `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_global_rest
     */
    fun getBoneGlobalRest(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalRestBind, handle, boneIdx)
    }

    fun createSkinFromRestTransforms(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(createSkinFromRestTransformsBind, handle))
    }

    /**
     * Binds the given Skin to the Skeleton.
     *
     * Generated from Godot docs: Skeleton3D.register_skin
     */
    fun registerSkin(skin: Skin?): SkinReference? {
        return SkinReference.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(registerSkinBind, handle, skin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns all bones in the skeleton to their rest poses.
     *
     * Generated from Godot docs: Skeleton3D.localize_rests
     */
    fun localizeRests() {
        ObjectCalls.ptrcallNoArgs(localizeRestsBind, handle)
    }

    /**
     * Clear all the bones in this skeleton.
     *
     * Generated from Godot docs: Skeleton3D.clear_bones
     */
    fun clearBones() {
        ObjectCalls.ptrcallNoArgs(clearBonesBind, handle)
    }

    /**
     * Returns the pose transform of the specified bone. Note: This is the pose you set to the skeleton
     * in the process, the final pose can get overridden by modifiers in the deferred process, if you
     * want to access the final pose, use `SkeletonModifier3D.modification_processed`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_pose
     */
    fun getBonePose(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBonePoseBind, handle, boneIdx)
    }

    /**
     * Sets the pose transform, `pose`, for the bone at `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_pose
     */
    fun setBonePose(boneIdx: Int, pose: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBonePoseBind, handle, boneIdx, pose)
    }

    /**
     * Sets the pose position of the bone at `bone_idx` to `position`. `position` is a `Vector3`
     * describing a position local to the `Skeleton3D` node.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_pose_position
     */
    fun setBonePosePosition(boneIdx: Int, position: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setBonePosePositionBind, handle, boneIdx, position)
    }

    /**
     * Sets the pose rotation of the bone at `bone_idx` to `rotation`. `rotation` is a `Quaternion`
     * describing a rotation in the bone's local coordinate space with respect to the rotation of any
     * parent bones.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_pose_rotation
     */
    fun setBonePoseRotation(boneIdx: Int, rotation: Quaternion) {
        ObjectCalls.ptrcallWithIntAndQuaternionArg(setBonePoseRotationBind, handle, boneIdx, rotation)
    }

    /**
     * Sets the pose scale of the bone at `bone_idx` to `scale`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_pose_scale
     */
    fun setBonePoseScale(boneIdx: Int, scale: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setBonePoseScaleBind, handle, boneIdx, scale)
    }

    /**
     * Returns the pose position of the bone at `bone_idx`. The returned `Vector3` is in the local
     * coordinate space of the `Skeleton3D` node.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_pose_position
     */
    fun getBonePosePosition(boneIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getBonePosePositionBind, handle, boneIdx)
    }

    /**
     * Returns the pose rotation of the bone at `bone_idx`. The returned `Quaternion` is local to the
     * bone with respect to the rotation of any parent bones.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_pose_rotation
     */
    fun getBonePoseRotation(boneIdx: Int): Quaternion {
        return ObjectCalls.ptrcallWithIntArgRetQuaternion(getBonePoseRotationBind, handle, boneIdx)
    }

    /**
     * Returns the pose scale of the bone at `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_pose_scale
     */
    fun getBonePoseScale(boneIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getBonePoseScaleBind, handle, boneIdx)
    }

    /**
     * Sets the bone pose to rest for `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.reset_bone_pose
     */
    fun resetBonePose(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(resetBonePoseBind, handle, boneIdx)
    }

    /**
     * Sets all bone poses to rests.
     *
     * Generated from Godot docs: Skeleton3D.reset_bone_poses
     */
    fun resetBonePoses() {
        ObjectCalls.ptrcallNoArgs(resetBonePosesBind, handle)
    }

    /**
     * Returns whether the bone pose for the bone at `bone_idx` is enabled.
     *
     * Generated from Godot docs: Skeleton3D.is_bone_enabled
     */
    fun isBoneEnabled(boneIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isBoneEnabledBind, handle, boneIdx)
    }

    /**
     * Disables the pose for the bone at `bone_idx` if `false`, enables the bone pose if `true`.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_enabled
     */
    fun setBoneEnabled(boneIdx: Int, enabled: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBoneEnabledBind, handle, boneIdx, enabled)
    }

    /**
     * Returns the overall transform of the specified bone, with respect to the skeleton. Being
     * relative to the skeleton frame, this is not the actual "global" transform of the bone. Note:
     * This is the global pose you set to the skeleton in the process, the final global pose can get
     * overridden by modifiers in the deferred process, if you want to access the final global pose,
     * use `SkeletonModifier3D.modification_processed`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_global_pose
     */
    fun getBoneGlobalPose(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalPoseBind, handle, boneIdx)
    }

    /**
     * Sets the global pose transform, `pose`, for the bone at `bone_idx`. Note: If other bone poses
     * have been changed, this method executes a dirty poses recalculation and will cause performance
     * to deteriorate. If you know that multiple global poses will be applied, consider using
     * `set_bone_pose` with precalculation.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_global_pose
     */
    fun setBoneGlobalPose(boneIdx: Int, pose: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBoneGlobalPoseBind, handle, boneIdx, pose)
    }

    /**
     * Force updates the bone transforms/poses for all bones in the skeleton.
     *
     * Generated from Godot docs: Skeleton3D.force_update_all_bone_transforms
     */
    fun forceUpdateAllBoneTransforms() {
        ObjectCalls.ptrcallNoArgs(forceUpdateAllBoneTransformsBind, handle)
    }

    /**
     * Force updates the bone transform for the bone at `bone_idx` and all of its children.
     *
     * Generated from Godot docs: Skeleton3D.force_update_bone_child_transform
     */
    fun forceUpdateBoneChildTransform(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(forceUpdateBoneChildTransformBind, handle, boneIdx)
    }

    /**
     * Multiplies the 3D position track animation. Note: Unless this value is `1.0`, the key value in
     * animation will not match the actual position value.
     *
     * Generated from Godot docs: Skeleton3D.set_motion_scale
     */
    fun setMotionScale(motionScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMotionScaleBind, handle, motionScale)
    }

    /**
     * Multiplies the 3D position track animation. Note: Unless this value is `1.0`, the key value in
     * animation will not match the actual position value.
     *
     * Generated from Godot docs: Skeleton3D.get_motion_scale
     */
    fun getMotionScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMotionScaleBind, handle)
    }

    /**
     * If `true`, forces the bones in their default rest pose, regardless of their values. In the
     * editor, this also prevents the bones from being edited.
     *
     * Generated from Godot docs: Skeleton3D.set_show_rest_only
     */
    fun setShowRestOnly(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowRestOnlyBind, handle, enabled)
    }

    /**
     * If `true`, forces the bones in their default rest pose, regardless of their values. In the
     * editor, this also prevents the bones from being edited.
     *
     * Generated from Godot docs: Skeleton3D.is_show_rest_only
     */
    fun isShowRestOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShowRestOnlyBind, handle)
    }

    /**
     * Sets the processing timing for the Modifier.
     *
     * Generated from Godot docs: Skeleton3D.set_modifier_callback_mode_process
     */
    fun setModifierCallbackModeProcess(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModifierCallbackModeProcessBind, handle, mode)
    }

    /**
     * Sets the processing timing for the Modifier.
     *
     * Generated from Godot docs: Skeleton3D.get_modifier_callback_mode_process
     */
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

    /**
     * Removes the global pose override on all bones in the skeleton.
     *
     * Generated from Godot docs: Skeleton3D.clear_bones_global_pose_override
     */
    fun clearBonesGlobalPoseOverride() {
        ObjectCalls.ptrcallNoArgs(clearBonesGlobalPoseOverrideBind, handle)
    }

    /**
     * Sets the global pose transform, `pose`, for the bone at `bone_idx`. `amount` is the
     * interpolation strength that will be used when applying the pose, and `persistent` determines if
     * the applied pose will remain. Note: The pose transform needs to be a global pose! To convert a
     * world transform from a `Node3D` to a global bone pose, multiply the `Transform3D.affine_inverse`
     * of the node's `Node3D.global_transform` by the desired world transform.
     *
     * Generated from Godot docs: Skeleton3D.set_bone_global_pose_override
     */
    fun setBoneGlobalPoseOverride(boneIdx: Int, pose: Transform3D, amount: Double, persistent: Boolean = false) {
        ObjectCalls.ptrcallWithIntTransform3DDoubleBoolArgs(setBoneGlobalPoseOverrideBind, handle, boneIdx, pose, amount, persistent)
    }

    /**
     * Returns the global pose override transform for `bone_idx`.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_global_pose_override
     */
    fun getBoneGlobalPoseOverride(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalPoseOverrideBind, handle, boneIdx)
    }

    /**
     * Returns the overall transform of the specified bone, with respect to the skeleton, but without
     * any global pose overrides. Being relative to the skeleton frame, this is not the actual "global"
     * transform of the bone.
     *
     * Generated from Godot docs: Skeleton3D.get_bone_global_pose_no_override
     */
    fun getBoneGlobalPoseNoOverride(boneIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBoneGlobalPoseNoOverrideBind, handle, boneIdx)
    }

    /**
     * If you follow the recommended workflow and explicitly have `PhysicalBoneSimulator3D` as a child
     * of `Skeleton3D`, you can control whether it is affected by raycasting without running
     * `physical_bones_start_simulation`, by its `SkeletonModifier3D.active`. However, for old
     * (deprecated) configurations, `Skeleton3D` has an internal virtual `PhysicalBoneSimulator3D` for
     * compatibility. This property controls the internal virtual `PhysicalBoneSimulator3D`'s
     * `SkeletonModifier3D.active`.
     *
     * Generated from Godot docs: Skeleton3D.set_animate_physical_bones
     */
    fun setAnimatePhysicalBones(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAnimatePhysicalBonesBind, handle, enabled)
    }

    /**
     * If you follow the recommended workflow and explicitly have `PhysicalBoneSimulator3D` as a child
     * of `Skeleton3D`, you can control whether it is affected by raycasting without running
     * `physical_bones_start_simulation`, by its `SkeletonModifier3D.active`. However, for old
     * (deprecated) configurations, `Skeleton3D` has an internal virtual `PhysicalBoneSimulator3D` for
     * compatibility. This property controls the internal virtual `PhysicalBoneSimulator3D`'s
     * `SkeletonModifier3D.active`.
     *
     * Generated from Godot docs: Skeleton3D.get_animate_physical_bones
     */
    fun getAnimatePhysicalBones(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAnimatePhysicalBonesBind, handle)
    }

    /**
     * Tells the `PhysicalBone3D` nodes in the Skeleton to stop simulating.
     *
     * Generated from Godot docs: Skeleton3D.physical_bones_stop_simulation
     */
    fun physicalBonesStopSimulation() {
        ObjectCalls.ptrcallNoArgs(physicalBonesStopSimulationBind, handle)
    }

    /**
     * Tells the `PhysicalBone3D` nodes in the Skeleton to start simulating and reacting to the physics
     * world. Optionally, a list of bone names can be passed-in, allowing only the passed-in bones to
     * be simulated.
     *
     * Generated from Godot docs: Skeleton3D.physical_bones_start_simulation
     */
    fun physicalBonesStartSimulation(bones: List<String>) {
        ObjectCalls.ptrcallWithStringNameListArg(physicalBonesStartSimulationBind, handle, bones)
    }

    /**
     * Adds a collision exception to the physical bone. Works just like the `RigidBody3D` node.
     *
     * Generated from Godot docs: Skeleton3D.physical_bones_add_collision_exception
     */
    fun physicalBonesAddCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(physicalBonesAddCollisionExceptionBind, handle, exception)
    }

    /**
     * Removes a collision exception to the physical bone. Works just like the `RigidBody3D` node.
     *
     * Generated from Godot docs: Skeleton3D.physical_bones_remove_collision_exception
     */
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

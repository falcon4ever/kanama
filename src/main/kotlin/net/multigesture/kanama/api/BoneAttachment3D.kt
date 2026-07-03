package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * А node that dynamically copies or overrides the 3D transform of a bone in its parent
 * `Skeleton3D`.
 *
 * Generated from Godot docs: BoneAttachment3D
 */
class BoneAttachment3D(handle: MemorySegment) : Node3D(handle) {
    var boneName: String
        @JvmName("boneNameProperty")
        get() = getBoneName()
        @JvmName("setBoneNameProperty")
        set(value) = setBoneName(value)

    var boneIdx: Int
        @JvmName("boneIdxProperty")
        get() = getBoneIdx()
        @JvmName("setBoneIdxProperty")
        set(value) = setBoneIdx(value)

    var overridePose: Boolean
        @JvmName("overridePoseProperty")
        get() = getOverridePose()
        @JvmName("setOverridePoseProperty")
        set(value) = setOverridePose(value)

    var useExternalSkeleton: Boolean
        @JvmName("useExternalSkeletonProperty")
        get() = getUseExternalSkeleton()
        @JvmName("setUseExternalSkeletonProperty")
        set(value) = setUseExternalSkeleton(value)

    var externalSkeleton: NodePath
        @JvmName("externalSkeletonProperty")
        get() = getExternalSkeleton()
        @JvmName("setExternalSkeletonProperty")
        set(value) = setExternalSkeleton(value)

    /**
     * Returns the parent or external `Skeleton3D` node if it exists, otherwise returns `null`.
     *
     * Generated from Godot docs: BoneAttachment3D.get_skeleton
     */
    fun getSkeleton(): Skeleton3D? {
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkeletonBind, handle))
    }

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: BoneAttachment3D.set_bone_name
     */
    fun setBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setBoneNameBind, handle, boneName)
    }

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: BoneAttachment3D.get_bone_name
     */
    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBoneNameBind, handle)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: BoneAttachment3D.set_bone_idx
     */
    fun setBoneIdx(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneIdxBind, handle, boneIdx)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: BoneAttachment3D.get_bone_idx
     */
    fun getBoneIdx(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneIdxBind, handle)
    }

    /**
     * A function that is called automatically when the `Skeleton3D` is updated. This function is where
     * the `BoneAttachment3D` node updates its position so it is correctly bound when it is not set to
     * override the bone pose.
     *
     * Generated from Godot docs: BoneAttachment3D.on_skeleton_update
     */
    fun onSkeletonUpdate() {
        ObjectCalls.ptrcallNoArgs(onSkeletonUpdateBind, handle)
    }

    /**
     * Whether the `BoneAttachment3D` node will override the bone pose of the bone it is attached to.
     * When set to `true`, the `BoneAttachment3D` node can change the pose of the bone. When set to
     * `false`, the `BoneAttachment3D` will always be set to the bone's transform. Note: This override
     * performs interruptively in the skeleton update process using signals due to the old design. It
     * may cause unintended behavior when used at the same time with `SkeletonModifier3D`.
     *
     * Generated from Godot docs: BoneAttachment3D.set_override_pose
     */
    fun setOverridePose(overridePose: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOverridePoseBind, handle, overridePose)
    }

    /**
     * Whether the `BoneAttachment3D` node will override the bone pose of the bone it is attached to.
     * When set to `true`, the `BoneAttachment3D` node can change the pose of the bone. When set to
     * `false`, the `BoneAttachment3D` will always be set to the bone's transform. Note: This override
     * performs interruptively in the skeleton update process using signals due to the old design. It
     * may cause unintended behavior when used at the same time with `SkeletonModifier3D`.
     *
     * Generated from Godot docs: BoneAttachment3D.get_override_pose
     */
    fun getOverridePose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOverridePoseBind, handle)
    }

    /**
     * Whether the `BoneAttachment3D` node will use an external `Skeleton3D` node rather than
     * attempting to use its parent node as the `Skeleton3D`. When set to `true`, the
     * `BoneAttachment3D` node will use the external `Skeleton3D` node set in `external_skeleton`.
     *
     * Generated from Godot docs: BoneAttachment3D.set_use_external_skeleton
     */
    fun setUseExternalSkeleton(useExternalSkeleton: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseExternalSkeletonBind, handle, useExternalSkeleton)
    }

    /**
     * Whether the `BoneAttachment3D` node will use an external `Skeleton3D` node rather than
     * attempting to use its parent node as the `Skeleton3D`. When set to `true`, the
     * `BoneAttachment3D` node will use the external `Skeleton3D` node set in `external_skeleton`.
     *
     * Generated from Godot docs: BoneAttachment3D.get_use_external_skeleton
     */
    fun getUseExternalSkeleton(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseExternalSkeletonBind, handle)
    }

    /**
     * The `NodePath` to the external `Skeleton3D` node.
     *
     * Generated from Godot docs: BoneAttachment3D.set_external_skeleton
     */
    fun setExternalSkeleton(externalSkeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setExternalSkeletonBind, handle, externalSkeleton)
    }

    /**
     * The `NodePath` to the external `Skeleton3D` node.
     *
     * Generated from Godot docs: BoneAttachment3D.get_external_skeleton
     */
    fun getExternalSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getExternalSkeletonBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): BoneAttachment3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoneAttachment3D? =
            if (handle.address() == 0L) null else BoneAttachment3D(handle)

        private const val GET_SKELETON_HASH = 1814733083L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_BONE_NAME_HASH = 83702148L
        private val setBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "set_bone_name", SET_BONE_NAME_HASH)
        }

        private const val GET_BONE_NAME_HASH = 201670096L
        private val getBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "get_bone_name", GET_BONE_NAME_HASH)
        }

        private const val SET_BONE_IDX_HASH = 1286410249L
        private val setBoneIdxBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "set_bone_idx", SET_BONE_IDX_HASH)
        }

        private const val GET_BONE_IDX_HASH = 3905245786L
        private val getBoneIdxBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "get_bone_idx", GET_BONE_IDX_HASH)
        }

        private const val ON_SKELETON_UPDATE_HASH = 3218959716L
        private val onSkeletonUpdateBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "on_skeleton_update", ON_SKELETON_UPDATE_HASH)
        }

        private const val SET_OVERRIDE_POSE_HASH = 2586408642L
        private val setOverridePoseBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "set_override_pose", SET_OVERRIDE_POSE_HASH)
        }

        private const val GET_OVERRIDE_POSE_HASH = 36873697L
        private val getOverridePoseBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "get_override_pose", GET_OVERRIDE_POSE_HASH)
        }

        private const val SET_USE_EXTERNAL_SKELETON_HASH = 2586408642L
        private val setUseExternalSkeletonBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "set_use_external_skeleton", SET_USE_EXTERNAL_SKELETON_HASH)
        }

        private const val GET_USE_EXTERNAL_SKELETON_HASH = 36873697L
        private val getUseExternalSkeletonBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "get_use_external_skeleton", GET_USE_EXTERNAL_SKELETON_HASH)
        }

        private const val SET_EXTERNAL_SKELETON_HASH = 1348162250L
        private val setExternalSkeletonBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "set_external_skeleton", SET_EXTERNAL_SKELETON_HASH)
        }

        private const val GET_EXTERNAL_SKELETON_HASH = 4075236667L
        private val getExternalSkeletonBind by lazy {
            ObjectCalls.getMethodBind("BoneAttachment3D", "get_external_skeleton", GET_EXTERNAL_SKELETON_HASH)
        }
    }
}

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

    fun getSkeleton(): Skeleton3D? {
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkeletonBind, handle))
    }

    fun setBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setBoneNameBind, handle, boneName)
    }

    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBoneNameBind, handle)
    }

    fun setBoneIdx(boneIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneIdxBind, handle, boneIdx)
    }

    fun getBoneIdx(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneIdxBind, handle)
    }

    fun onSkeletonUpdate() {
        ObjectCalls.ptrcallNoArgs(onSkeletonUpdateBind, handle)
    }

    fun setOverridePose(overridePose: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOverridePoseBind, handle, overridePose)
    }

    fun getOverridePose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOverridePoseBind, handle)
    }

    fun setUseExternalSkeleton(useExternalSkeleton: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseExternalSkeletonBind, handle, useExternalSkeleton)
    }

    fun getUseExternalSkeleton(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseExternalSkeletonBind, handle)
    }

    fun setExternalSkeleton(externalSkeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setExternalSkeletonBind, handle, externalSkeleton)
    }

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

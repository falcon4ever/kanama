package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFSkeleton
 */
class GLTFSkeleton(handle: MemorySegment) : Resource(handle) {
    val joints: List<Int>
        @JvmName("jointsProperty")
        get() = getJoints()

    val roots: List<Int>
        @JvmName("rootsProperty")
        get() = getRoots()

    val godotBoneNode: Map<String, Any?>
        @JvmName("godotBoneNodeProperty")
        get() = getGodotBoneNode()

    fun getJoints(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getJointsBind, handle)
    }

    fun getRoots(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootsBind, handle)
    }

    fun getGodotSkeleton(): Skeleton3D? {
        checkOpen()
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGodotSkeletonBind, handle))
    }

    fun getGodotBoneNode(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getGodotBoneNodeBind, handle)
    }

    fun getBoneAttachmentCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneAttachmentCountBind, handle)
    }

    fun getBoneAttachment(idx: Int): BoneAttachment3D? {
        checkOpen()
        return BoneAttachment3D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBoneAttachmentBind, handle, idx))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFSkeleton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFSkeleton? =
            if (handle.address() == 0L) null else GLTFSkeleton(handle)

        private const val GET_JOINTS_HASH = 969006518L
        private val getJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_joints", GET_JOINTS_HASH)
        }

        private const val GET_ROOTS_HASH = 969006518L
        private val getRootsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_roots", GET_ROOTS_HASH)
        }

        private const val GET_GODOT_SKELETON_HASH = 1814733083L
        private val getGodotSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_skeleton", GET_GODOT_SKELETON_HASH)
        }

        private const val GET_GODOT_BONE_NODE_HASH = 2382534195L
        private val getGodotBoneNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_bone_node", GET_GODOT_BONE_NODE_HASH)
        }

        private const val GET_BONE_ATTACHMENT_COUNT_HASH = 2455072627L
        private val getBoneAttachmentCountBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_bone_attachment_count", GET_BONE_ATTACHMENT_COUNT_HASH)
        }

        private const val GET_BONE_ATTACHMENT_HASH = 945440495L
        private val getBoneAttachmentBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_bone_attachment", GET_BONE_ATTACHMENT_HASH)
        }
    }
}

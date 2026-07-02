package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GLTFSkeleton
 */
class GLTFSkeleton(handle: MemorySegment) : Resource(handle) {
    var joints: List<Int>
        @JvmName("jointsProperty")
        get() = getJoints()
        @JvmName("setJointsProperty")
        set(value) = setJoints(value)

    var roots: List<Int>
        @JvmName("rootsProperty")
        get() = getRoots()
        @JvmName("setRootsProperty")
        set(value) = setRoots(value)

    var uniqueNames: List<String>
        @JvmName("uniqueNamesProperty")
        get() = getUniqueNames()
        @JvmName("setUniqueNamesProperty")
        set(value) = setUniqueNames(value)

    var godotBoneNode: Map<String, Any?>
        @JvmName("godotBoneNodeProperty")
        get() = getGodotBoneNode()
        @JvmName("setGodotBoneNodeProperty")
        set(value) = setGodotBoneNode(value)

    fun getJoints(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getJointsBind, handle)
    }

    fun setJoints(joints: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setJointsBind, handle, joints)
    }

    fun getRoots(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootsBind, handle)
    }

    fun setRoots(roots: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setRootsBind, handle, roots)
    }

    fun getGodotSkeleton(): Skeleton3D? {
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGodotSkeletonBind, handle))
    }

    fun getUniqueNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getUniqueNamesBind, handle)
    }

    fun setUniqueNames(uniqueNames: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setUniqueNamesBind, handle, uniqueNames)
    }

    fun getGodotBoneNode(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getGodotBoneNodeBind, handle)
    }

    fun setGodotBoneNode(godotBoneNode: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setGodotBoneNodeBind, handle, godotBoneNode)
    }

    fun getBoneAttachmentCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneAttachmentCountBind, handle)
    }

    fun getBoneAttachment(idx: Int): BoneAttachment3D? {
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

        private const val SET_JOINTS_HASH = 3614634198L
        private val setJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_joints", SET_JOINTS_HASH)
        }

        private const val GET_ROOTS_HASH = 969006518L
        private val getRootsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_roots", GET_ROOTS_HASH)
        }

        private const val SET_ROOTS_HASH = 3614634198L
        private val setRootsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_roots", SET_ROOTS_HASH)
        }

        private const val GET_GODOT_SKELETON_HASH = 1814733083L
        private val getGodotSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_skeleton", GET_GODOT_SKELETON_HASH)
        }

        private const val GET_UNIQUE_NAMES_HASH = 2915620761L
        private val getUniqueNamesBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_unique_names", GET_UNIQUE_NAMES_HASH)
        }

        private const val SET_UNIQUE_NAMES_HASH = 381264803L
        private val setUniqueNamesBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_unique_names", SET_UNIQUE_NAMES_HASH)
        }

        private const val GET_GODOT_BONE_NODE_HASH = 2382534195L
        private val getGodotBoneNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_bone_node", GET_GODOT_BONE_NODE_HASH)
        }

        private const val SET_GODOT_BONE_NODE_HASH = 4155329257L
        private val setGodotBoneNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_godot_bone_node", SET_GODOT_BONE_NODE_HASH)
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

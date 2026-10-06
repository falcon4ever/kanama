package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GLTFSkeleton
 */
class GLTFSkeleton(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getJointsBind, segment)
    }

    fun setJoints(joints: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setJointsBind, segment, joints)
    }

    fun getRoots(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getRootsBind, segment)
    }

    fun setRoots(roots: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setRootsBind, segment, roots)
    }

    fun getGodotSkeleton(): Skeleton3D? {
        checkOpen()
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGodotSkeletonBind, segment))
    }

    fun getUniqueNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(Binds.getUniqueNamesBind, segment)
    }

    fun setUniqueNames(uniqueNames: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedStringListArg(Binds.setUniqueNamesBind, segment, uniqueNames)
    }

    fun getGodotBoneNode(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getGodotBoneNodeBind, segment)
    }

    fun setGodotBoneNode(godotBoneNode: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setGodotBoneNodeBind, segment, godotBoneNode)
    }

    fun getBoneAttachmentCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBoneAttachmentCountBind, segment)
    }

    fun getBoneAttachment(idx: Int): BoneAttachment3D? {
        checkOpen()
        return BoneAttachment3D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBoneAttachmentBind, segment, idx))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFSkeleton? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFSkeleton? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFSkeleton(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFSkeleton? =
            if (handle.address() == 0L) null else GLTFSkeleton(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_JOINTS_HASH = 969006518L
        @JvmField
        val getJointsBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_joints", GET_JOINTS_HASH)

        private const val SET_JOINTS_HASH = 3614634198L
        @JvmField
        val setJointsBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_joints", SET_JOINTS_HASH)

        private const val GET_ROOTS_HASH = 969006518L
        @JvmField
        val getRootsBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_roots", GET_ROOTS_HASH)

        private const val SET_ROOTS_HASH = 3614634198L
        @JvmField
        val setRootsBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_roots", SET_ROOTS_HASH)

        private const val GET_GODOT_SKELETON_HASH = 1814733083L
        @JvmField
        val getGodotSkeletonBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_skeleton", GET_GODOT_SKELETON_HASH)

        private const val GET_UNIQUE_NAMES_HASH = 2915620761L
        @JvmField
        val getUniqueNamesBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_unique_names", GET_UNIQUE_NAMES_HASH)

        private const val SET_UNIQUE_NAMES_HASH = 381264803L
        @JvmField
        val setUniqueNamesBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_unique_names", SET_UNIQUE_NAMES_HASH)

        private const val GET_GODOT_BONE_NODE_HASH = 2382534195L
        @JvmField
        val getGodotBoneNodeBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_bone_node", GET_GODOT_BONE_NODE_HASH)

        private const val SET_GODOT_BONE_NODE_HASH = 4155329257L
        @JvmField
        val setGodotBoneNodeBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "set_godot_bone_node", SET_GODOT_BONE_NODE_HASH)

        private const val GET_BONE_ATTACHMENT_COUNT_HASH = 2455072627L
        @JvmField
        val getBoneAttachmentCountBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_bone_attachment_count", GET_BONE_ATTACHMENT_COUNT_HASH)

        private const val GET_BONE_ATTACHMENT_HASH = 945440495L
        @JvmField
        val getBoneAttachmentBind =
            ObjectCalls.getMethodBind("GLTFSkeleton", "get_bone_attachment", GET_BONE_ATTACHMENT_HASH)
    }
}

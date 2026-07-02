package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: GLTFSkin
 */
class GLTFSkin(handle: MemorySegment) : Resource(handle) {
    var skinRoot: Int
        @JvmName("skinRootProperty")
        get() = getSkinRoot()
        @JvmName("setSkinRootProperty")
        set(value) = setSkinRoot(value)

    var jointsOriginal: List<Int>
        @JvmName("jointsOriginalProperty")
        get() = getJointsOriginal()
        @JvmName("setJointsOriginalProperty")
        set(value) = setJointsOriginal(value)

    var inverseBinds: List<Transform3D>
        @JvmName("inverseBindsProperty")
        get() = getInverseBinds()
        @JvmName("setInverseBindsProperty")
        set(value) = setInverseBinds(value)

    var joints: List<Int>
        @JvmName("jointsProperty")
        get() = getJoints()
        @JvmName("setJointsProperty")
        set(value) = setJoints(value)

    var nonJoints: List<Int>
        @JvmName("nonJointsProperty")
        get() = getNonJoints()
        @JvmName("setNonJointsProperty")
        set(value) = setNonJoints(value)

    var roots: List<Int>
        @JvmName("rootsProperty")
        get() = getRoots()
        @JvmName("setRootsProperty")
        set(value) = setRoots(value)

    var skeleton: Int
        @JvmName("skeletonProperty")
        get() = getSkeleton()
        @JvmName("setSkeletonProperty")
        set(value) = setSkeleton(value)

    var jointIToBoneI: Map<String, Any?>
        @JvmName("jointIToBoneIProperty")
        get() = getJointIToBoneI()
        @JvmName("setJointIToBoneIProperty")
        set(value) = setJointIToBoneI(value)

    var jointIToName: Map<String, Any?>
        @JvmName("jointIToNameProperty")
        get() = getJointIToName()
        @JvmName("setJointIToNameProperty")
        set(value) = setJointIToName(value)

    var godotSkin: Skin?
        @JvmName("godotSkinProperty")
        get() = getGodotSkin()
        @JvmName("setGodotSkinProperty")
        set(value) = setGodotSkin(value)

    fun getSkinRoot(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSkinRootBind, handle)
    }

    fun setSkinRoot(skinRoot: Int) {
        ObjectCalls.ptrcallWithIntArg(setSkinRootBind, handle, skinRoot)
    }

    fun getJointsOriginal(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getJointsOriginalBind, handle)
    }

    fun setJointsOriginal(jointsOriginal: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setJointsOriginalBind, handle, jointsOriginal)
    }

    fun getInverseBinds(): List<Transform3D> {
        return ObjectCalls.ptrcallNoArgsRetTransform3DList(getInverseBindsBind, handle)
    }

    fun setInverseBinds(inverseBinds: List<Transform3D>) {
        ObjectCalls.ptrcallWithTransform3DListArg(setInverseBindsBind, handle, inverseBinds)
    }

    fun getJoints(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getJointsBind, handle)
    }

    fun setJoints(joints: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setJointsBind, handle, joints)
    }

    fun getNonJoints(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getNonJointsBind, handle)
    }

    fun setNonJoints(nonJoints: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setNonJointsBind, handle, nonJoints)
    }

    fun getRoots(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootsBind, handle)
    }

    fun setRoots(roots: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setRootsBind, handle, roots)
    }

    fun getSkeleton(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSkeletonBind, handle)
    }

    fun setSkeleton(skeleton: Int) {
        ObjectCalls.ptrcallWithIntArg(setSkeletonBind, handle, skeleton)
    }

    fun getJointIToBoneI(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getJointIToBoneIBind, handle)
    }

    fun setJointIToBoneI(jointIToBoneI: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setJointIToBoneIBind, handle, jointIToBoneI)
    }

    fun getJointIToName(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getJointIToNameBind, handle)
    }

    fun setJointIToName(jointIToName: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setJointIToNameBind, handle, jointIToName)
    }

    fun getGodotSkin(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGodotSkinBind, handle))
    }

    fun setGodotSkin(godotSkin: Skin?) {
        ObjectCalls.ptrcallWithObjectArgs(setGodotSkinBind, handle, listOf(godotSkin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFSkin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFSkin? =
            if (handle.address() == 0L) null else GLTFSkin(handle)

        private const val GET_SKIN_ROOT_HASH = 2455072627L
        private val getSkinRootBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_skin_root", GET_SKIN_ROOT_HASH)
        }

        private const val SET_SKIN_ROOT_HASH = 1286410249L
        private val setSkinRootBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_skin_root", SET_SKIN_ROOT_HASH)
        }

        private const val GET_JOINTS_ORIGINAL_HASH = 969006518L
        private val getJointsOriginalBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_joints_original", GET_JOINTS_ORIGINAL_HASH)
        }

        private const val SET_JOINTS_ORIGINAL_HASH = 3614634198L
        private val setJointsOriginalBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_joints_original", SET_JOINTS_ORIGINAL_HASH)
        }

        private const val GET_INVERSE_BINDS_HASH = 2915620761L
        private val getInverseBindsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_inverse_binds", GET_INVERSE_BINDS_HASH)
        }

        private const val SET_INVERSE_BINDS_HASH = 381264803L
        private val setInverseBindsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_inverse_binds", SET_INVERSE_BINDS_HASH)
        }

        private const val GET_JOINTS_HASH = 969006518L
        private val getJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_joints", GET_JOINTS_HASH)
        }

        private const val SET_JOINTS_HASH = 3614634198L
        private val setJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_joints", SET_JOINTS_HASH)
        }

        private const val GET_NON_JOINTS_HASH = 969006518L
        private val getNonJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_non_joints", GET_NON_JOINTS_HASH)
        }

        private const val SET_NON_JOINTS_HASH = 3614634198L
        private val setNonJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_non_joints", SET_NON_JOINTS_HASH)
        }

        private const val GET_ROOTS_HASH = 969006518L
        private val getRootsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_roots", GET_ROOTS_HASH)
        }

        private const val SET_ROOTS_HASH = 3614634198L
        private val setRootsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_roots", SET_ROOTS_HASH)
        }

        private const val GET_SKELETON_HASH = 2455072627L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_SKELETON_HASH = 1286410249L
        private val setSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_skeleton", SET_SKELETON_HASH)
        }

        private const val GET_JOINT_I_TO_BONE_I_HASH = 2382534195L
        private val getJointIToBoneIBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_joint_i_to_bone_i", GET_JOINT_I_TO_BONE_I_HASH)
        }

        private const val SET_JOINT_I_TO_BONE_I_HASH = 4155329257L
        private val setJointIToBoneIBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_joint_i_to_bone_i", SET_JOINT_I_TO_BONE_I_HASH)
        }

        private const val GET_JOINT_I_TO_NAME_HASH = 2382534195L
        private val getJointIToNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_joint_i_to_name", GET_JOINT_I_TO_NAME_HASH)
        }

        private const val SET_JOINT_I_TO_NAME_HASH = 4155329257L
        private val setJointIToNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_joint_i_to_name", SET_JOINT_I_TO_NAME_HASH)
        }

        private const val GET_GODOT_SKIN_HASH = 1032037385L
        private val getGodotSkinBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_godot_skin", GET_GODOT_SKIN_HASH)
        }

        private const val SET_GODOT_SKIN_HASH = 3971435618L
        private val setGodotSkinBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_godot_skin", SET_GODOT_SKIN_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: GLTFSkin
 */
class GLTFSkin(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkinRootBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setSkinRoot(skinRoot: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSkinRootBind, segment, skinRoot)
        ObjectCalls.keepReachable(this)
    }

    fun getJointsOriginal(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getJointsOriginalBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setJointsOriginal(jointsOriginal: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setJointsOriginalBind, segment, jointsOriginal)
        ObjectCalls.keepReachable(this)
    }

    fun getInverseBinds(): List<Transform3D> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTransform3DList(Binds.getInverseBindsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setInverseBinds(inverseBinds: List<Transform3D>) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DListArg(Binds.setInverseBindsBind, segment, inverseBinds)
        ObjectCalls.keepReachable(this)
    }

    fun getJoints(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getJointsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setJoints(joints: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setJointsBind, segment, joints)
        ObjectCalls.keepReachable(this)
    }

    fun getNonJoints(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getNonJointsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setNonJoints(nonJoints: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setNonJointsBind, segment, nonJoints)
        ObjectCalls.keepReachable(this)
    }

    fun getRoots(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getRootsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setRoots(roots: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setRootsBind, segment, roots)
        ObjectCalls.keepReachable(this)
    }

    fun getSkeleton(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkeletonBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setSkeleton(skeleton: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSkeletonBind, segment, skeleton)
        ObjectCalls.keepReachable(this)
    }

    fun getJointIToBoneI(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getJointIToBoneIBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setJointIToBoneI(jointIToBoneI: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setJointIToBoneIBind, segment, jointIToBoneI)
        ObjectCalls.keepReachable(this)
    }

    fun getJointIToName(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getJointIToNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setJointIToName(jointIToName: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setJointIToNameBind, segment, jointIToName)
        ObjectCalls.keepReachable(this)
    }

    fun getGodotSkin(): Skin? {
        checkOpen()
        val ret = Skin.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGodotSkinBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setGodotSkin(godotSkin: Skin?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setGodotSkinBind, segment, listOf(godotSkin?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(godotSkin)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFSkin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFSkin? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFSkin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFSkin? =
            if (handle.address() == 0L) null else GLTFSkin(GodotHandle(handle))

        /** A new GLTFSkin, GDScript's `GLTFSkin.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFSkin =
            RefCounted.owned(GLTFSkin(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFSkin"), "GLTFSkin"))))
    }

    private object Binds {
        private const val GET_SKIN_ROOT_HASH = 2455072627L
        @JvmField
        val getSkinRootBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_skin_root", GET_SKIN_ROOT_HASH)

        private const val SET_SKIN_ROOT_HASH = 1286410249L
        @JvmField
        val setSkinRootBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_skin_root", SET_SKIN_ROOT_HASH)

        private const val GET_JOINTS_ORIGINAL_HASH = 969006518L
        @JvmField
        val getJointsOriginalBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_joints_original", GET_JOINTS_ORIGINAL_HASH)

        private const val SET_JOINTS_ORIGINAL_HASH = 3614634198L
        @JvmField
        val setJointsOriginalBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_joints_original", SET_JOINTS_ORIGINAL_HASH)

        private const val GET_INVERSE_BINDS_HASH = 2915620761L
        @JvmField
        val getInverseBindsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_inverse_binds", GET_INVERSE_BINDS_HASH)

        private const val SET_INVERSE_BINDS_HASH = 381264803L
        @JvmField
        val setInverseBindsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_inverse_binds", SET_INVERSE_BINDS_HASH)

        private const val GET_JOINTS_HASH = 969006518L
        @JvmField
        val getJointsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_joints", GET_JOINTS_HASH)

        private const val SET_JOINTS_HASH = 3614634198L
        @JvmField
        val setJointsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_joints", SET_JOINTS_HASH)

        private const val GET_NON_JOINTS_HASH = 969006518L
        @JvmField
        val getNonJointsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_non_joints", GET_NON_JOINTS_HASH)

        private const val SET_NON_JOINTS_HASH = 3614634198L
        @JvmField
        val setNonJointsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_non_joints", SET_NON_JOINTS_HASH)

        private const val GET_ROOTS_HASH = 969006518L
        @JvmField
        val getRootsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_roots", GET_ROOTS_HASH)

        private const val SET_ROOTS_HASH = 3614634198L
        @JvmField
        val setRootsBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_roots", SET_ROOTS_HASH)

        private const val GET_SKELETON_HASH = 2455072627L
        @JvmField
        val getSkeletonBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_skeleton", GET_SKELETON_HASH)

        private const val SET_SKELETON_HASH = 1286410249L
        @JvmField
        val setSkeletonBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_skeleton", SET_SKELETON_HASH)

        private const val GET_JOINT_I_TO_BONE_I_HASH = 2382534195L
        @JvmField
        val getJointIToBoneIBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_joint_i_to_bone_i", GET_JOINT_I_TO_BONE_I_HASH)

        private const val SET_JOINT_I_TO_BONE_I_HASH = 4155329257L
        @JvmField
        val setJointIToBoneIBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_joint_i_to_bone_i", SET_JOINT_I_TO_BONE_I_HASH)

        private const val GET_JOINT_I_TO_NAME_HASH = 2382534195L
        @JvmField
        val getJointIToNameBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_joint_i_to_name", GET_JOINT_I_TO_NAME_HASH)

        private const val SET_JOINT_I_TO_NAME_HASH = 4155329257L
        @JvmField
        val setJointIToNameBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_joint_i_to_name", SET_JOINT_I_TO_NAME_HASH)

        private const val GET_GODOT_SKIN_HASH = 1032037385L
        @JvmField
        val getGodotSkinBind =
            ObjectCalls.getMethodBind("GLTFSkin", "get_godot_skin", GET_GODOT_SKIN_HASH)

        private const val SET_GODOT_SKIN_HASH = 3971435618L
        @JvmField
        val setGodotSkinBind =
            ObjectCalls.getMethodBind("GLTFSkin", "set_godot_skin", SET_GODOT_SKIN_HASH)
    }
}

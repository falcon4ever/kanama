package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFSkin
 */
class GLTFSkin(handle: MemorySegment) : Resource(handle) {
    var skinRoot: Int
        @JvmName("skinRootProperty")
        get() = getSkinRoot()
        @JvmName("setSkinRootProperty")
        set(value) = setSkinRoot(value)

    val jointsOriginal: List<Int>
        @JvmName("jointsOriginalProperty")
        get() = getJointsOriginal()

    val joints: List<Int>
        @JvmName("jointsProperty")
        get() = getJoints()

    val nonJoints: List<Int>
        @JvmName("nonJointsProperty")
        get() = getNonJoints()

    val roots: List<Int>
        @JvmName("rootsProperty")
        get() = getRoots()

    var skeleton: Int
        @JvmName("skeletonProperty")
        get() = getSkeleton()
        @JvmName("setSkeletonProperty")
        set(value) = setSkeleton(value)

    var godotSkin: Skin?
        @JvmName("godotSkinProperty")
        get() = getGodotSkin()
        @JvmName("setGodotSkinProperty")
        set(value) = setGodotSkin(value)

    fun getSkinRoot(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSkinRootBind, handle)
    }

    fun setSkinRoot(skinRoot: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSkinRootBind, handle, skinRoot)
    }

    fun getJointsOriginal(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getJointsOriginalBind, handle)
    }

    fun getJoints(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getJointsBind, handle)
    }

    fun getNonJoints(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getNonJointsBind, handle)
    }

    fun getRoots(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootsBind, handle)
    }

    fun getSkeleton(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSkeletonBind, handle)
    }

    fun setSkeleton(skeleton: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSkeletonBind, handle, skeleton)
    }

    fun getGodotSkin(): Skin? {
        checkOpen()
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGodotSkinBind, handle))
    }

    fun setGodotSkin(godotSkin: Skin?) {
        checkOpen()
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

        private const val GET_JOINTS_HASH = 969006518L
        private val getJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_joints", GET_JOINTS_HASH)
        }

        private const val GET_NON_JOINTS_HASH = 969006518L
        private val getNonJointsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_non_joints", GET_NON_JOINTS_HASH)
        }

        private const val GET_ROOTS_HASH = 969006518L
        private val getRootsBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_roots", GET_ROOTS_HASH)
        }

        private const val GET_SKELETON_HASH = 2455072627L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_SKELETON_HASH = 1286410249L
        private val setSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFSkin", "set_skeleton", SET_SKELETON_HASH)
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

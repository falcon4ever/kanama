package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: Skin
 */
class Skin(handle: MemorySegment) : Resource(handle) {
    fun setBindCount(bindCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setBindCountBind, handle, bindCount)
    }

    fun getBindCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBindCountBind, handle)
    }

    fun addBind(bone: Int, pose: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(addBindBind, handle, bone, pose)
    }

    fun addNamedBind(name: String, pose: Transform3D) {
        ObjectCalls.ptrcallWithStringAndTransform3DArg(addNamedBindBind, handle, name, pose)
    }

    fun setBindPose(bindIndex: Int, pose: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBindPoseBind, handle, bindIndex, pose)
    }

    fun getBindPose(bindIndex: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBindPoseBind, handle, bindIndex)
    }

    fun setBindName(bindIndex: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBindNameBind, handle, bindIndex, name)
    }

    fun getBindName(bindIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBindNameBind, handle, bindIndex)
    }

    fun setBindBone(bindIndex: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setBindBoneBind, handle, bindIndex, bone)
    }

    fun getBindBone(bindIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getBindBoneBind, handle, bindIndex)
    }

    fun clearBinds() {
        ObjectCalls.ptrcallNoArgs(clearBindsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Skin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Skin? =
            if (handle.address() == 0L) null else Skin(handle)

        private const val SET_BIND_COUNT_HASH = 1286410249L
        private val setBindCountBind by lazy {
            ObjectCalls.getMethodBind("Skin", "set_bind_count", SET_BIND_COUNT_HASH)
        }

        private const val GET_BIND_COUNT_HASH = 3905245786L
        private val getBindCountBind by lazy {
            ObjectCalls.getMethodBind("Skin", "get_bind_count", GET_BIND_COUNT_HASH)
        }

        private const val ADD_BIND_HASH = 3616898986L
        private val addBindBind by lazy {
            ObjectCalls.getMethodBind("Skin", "add_bind", ADD_BIND_HASH)
        }

        private const val ADD_NAMED_BIND_HASH = 3154712474L
        private val addNamedBindBind by lazy {
            ObjectCalls.getMethodBind("Skin", "add_named_bind", ADD_NAMED_BIND_HASH)
        }

        private const val SET_BIND_POSE_HASH = 3616898986L
        private val setBindPoseBind by lazy {
            ObjectCalls.getMethodBind("Skin", "set_bind_pose", SET_BIND_POSE_HASH)
        }

        private const val GET_BIND_POSE_HASH = 1965739696L
        private val getBindPoseBind by lazy {
            ObjectCalls.getMethodBind("Skin", "get_bind_pose", GET_BIND_POSE_HASH)
        }

        private const val SET_BIND_NAME_HASH = 3780747571L
        private val setBindNameBind by lazy {
            ObjectCalls.getMethodBind("Skin", "set_bind_name", SET_BIND_NAME_HASH)
        }

        private const val GET_BIND_NAME_HASH = 659327637L
        private val getBindNameBind by lazy {
            ObjectCalls.getMethodBind("Skin", "get_bind_name", GET_BIND_NAME_HASH)
        }

        private const val SET_BIND_BONE_HASH = 3937882851L
        private val setBindBoneBind by lazy {
            ObjectCalls.getMethodBind("Skin", "set_bind_bone", SET_BIND_BONE_HASH)
        }

        private const val GET_BIND_BONE_HASH = 923996154L
        private val getBindBoneBind by lazy {
            ObjectCalls.getMethodBind("Skin", "get_bind_bone", GET_BIND_BONE_HASH)
        }

        private const val CLEAR_BINDS_HASH = 3218959716L
        private val clearBindsBind by lazy {
            ObjectCalls.getMethodBind("Skin", "clear_binds", CLEAR_BINDS_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: Skin
 */
class Skin(handle: GodotHandle) : Resource(handle) {
    fun setBindCount(bindCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setBindCountBind, segment, bindCount)
    }

    fun getBindCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBindCountBind, segment)
    }

    fun addBind(bone: Int, pose: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(addBindBind, segment, bone, pose)
    }

    fun addNamedBind(name: String, pose: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndTransform3DArg(addNamedBindBind, segment, name, pose)
    }

    fun setBindPose(bindIndex: Int, pose: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setBindPoseBind, segment, bindIndex, pose)
    }

    fun getBindPose(bindIndex: Int): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getBindPoseBind, segment, bindIndex)
    }

    fun setBindName(bindIndex: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBindNameBind, segment, bindIndex, name)
    }

    fun getBindName(bindIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBindNameBind, segment, bindIndex)
    }

    fun setBindBone(bindIndex: Int, bone: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(setBindBoneBind, segment, bindIndex, bone)
    }

    fun getBindBone(bindIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getBindBoneBind, segment, bindIndex)
    }

    fun clearBinds() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBindsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Skin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Skin? =
            if (handle.address() == 0L) null else Skin(GodotHandle(handle))

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

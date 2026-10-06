package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithIntArg(Binds.setBindCountBind, segment, bindCount)
    }

    fun getBindCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindCountBind, segment)
    }

    fun addBind(bone: Int, pose: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(Binds.addBindBind, segment, bone, pose)
    }

    fun addNamedBind(name: String, pose: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndTransform3DArg(Binds.addNamedBindBind, segment, name, pose)
    }

    fun setBindPose(bindIndex: Int, pose: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(Binds.setBindPoseBind, segment, bindIndex, pose)
    }

    fun getBindPose(bindIndex: Int): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(Binds.getBindPoseBind, segment, bindIndex)
    }

    fun setBindName(bindIndex: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setBindNameBind, segment, bindIndex, name)
    }

    fun getBindName(bindIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getBindNameBind, segment, bindIndex)
    }

    fun setBindBone(bindIndex: Int, bone: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setBindBoneBind, segment, bindIndex, bone)
    }

    fun getBindBone(bindIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getBindBoneBind, segment, bindIndex)
    }

    fun clearBinds() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBindsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Skin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Skin? =
            if (handle.address() == 0L) null else RefCounted.owned(Skin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Skin? =
            if (handle.address() == 0L) null else Skin(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BIND_COUNT_HASH = 1286410249L
        @JvmField
        val setBindCountBind =
            ObjectCalls.getMethodBind("Skin", "set_bind_count", SET_BIND_COUNT_HASH)

        private const val GET_BIND_COUNT_HASH = 3905245786L
        @JvmField
        val getBindCountBind =
            ObjectCalls.getMethodBind("Skin", "get_bind_count", GET_BIND_COUNT_HASH)

        private const val ADD_BIND_HASH = 3616898986L
        @JvmField
        val addBindBind =
            ObjectCalls.getMethodBind("Skin", "add_bind", ADD_BIND_HASH)

        private const val ADD_NAMED_BIND_HASH = 3154712474L
        @JvmField
        val addNamedBindBind =
            ObjectCalls.getMethodBind("Skin", "add_named_bind", ADD_NAMED_BIND_HASH)

        private const val SET_BIND_POSE_HASH = 3616898986L
        @JvmField
        val setBindPoseBind =
            ObjectCalls.getMethodBind("Skin", "set_bind_pose", SET_BIND_POSE_HASH)

        private const val GET_BIND_POSE_HASH = 1965739696L
        @JvmField
        val getBindPoseBind =
            ObjectCalls.getMethodBind("Skin", "get_bind_pose", GET_BIND_POSE_HASH)

        private const val SET_BIND_NAME_HASH = 3780747571L
        @JvmField
        val setBindNameBind =
            ObjectCalls.getMethodBind("Skin", "set_bind_name", SET_BIND_NAME_HASH)

        private const val GET_BIND_NAME_HASH = 659327637L
        @JvmField
        val getBindNameBind =
            ObjectCalls.getMethodBind("Skin", "get_bind_name", GET_BIND_NAME_HASH)

        private const val SET_BIND_BONE_HASH = 3937882851L
        @JvmField
        val setBindBoneBind =
            ObjectCalls.getMethodBind("Skin", "set_bind_bone", SET_BIND_BONE_HASH)

        private const val GET_BIND_BONE_HASH = 923996154L
        @JvmField
        val getBindBoneBind =
            ObjectCalls.getMethodBind("Skin", "get_bind_bone", GET_BIND_BONE_HASH)

        private const val CLEAR_BINDS_HASH = 3218959716L
        @JvmField
        val clearBindsBind =
            ObjectCalls.getMethodBind("Skin", "clear_binds", CLEAR_BINDS_HASH)
    }
}

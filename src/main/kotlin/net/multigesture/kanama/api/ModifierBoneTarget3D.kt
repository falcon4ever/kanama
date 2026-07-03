package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * А node that dynamically copies the 3D transform of a bone in its parent `Skeleton3D`.
 *
 * Generated from Godot docs: ModifierBoneTarget3D
 */
class ModifierBoneTarget3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var boneName: String
        @JvmName("boneNameProperty")
        get() = getBoneName()
        @JvmName("setBoneNameProperty")
        set(value) = setBoneName(value)

    var bone: Int
        @JvmName("boneProperty")
        get() = getBone()
        @JvmName("setBoneProperty")
        set(value) = setBone(value)

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.set_bone_name
     */
    fun setBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setBoneNameBind, handle, boneName)
    }

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.get_bone_name
     */
    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBoneNameBind, handle)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.set_bone
     */
    fun setBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneBind, handle, bone)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.get_bone
     */
    fun getBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ModifierBoneTarget3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ModifierBoneTarget3D? =
            if (handle.address() == 0L) null else ModifierBoneTarget3D(handle)

        private const val SET_BONE_NAME_HASH = 83702148L
        private val setBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "set_bone_name", SET_BONE_NAME_HASH)
        }

        private const val GET_BONE_NAME_HASH = 201670096L
        private val getBoneNameBind by lazy {
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "get_bone_name", GET_BONE_NAME_HASH)
        }

        private const val SET_BONE_HASH = 1286410249L
        private val setBoneBind by lazy {
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "set_bone", SET_BONE_HASH)
        }

        private const val GET_BONE_HASH = 3905245786L
        private val getBoneBind by lazy {
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "get_bone", GET_BONE_HASH)
        }
    }
}

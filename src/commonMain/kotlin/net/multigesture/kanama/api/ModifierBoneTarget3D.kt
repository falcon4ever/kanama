package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * А node that dynamically copies the 3D transform of a bone in its parent `Skeleton3D`.
 *
 * Generated from Godot docs: ModifierBoneTarget3D
 */
class ModifierBoneTarget3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
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
        ObjectCalls.ptrcallWithStringArg(Binds.setBoneNameBind, segment, boneName)
    }

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.get_bone_name
     */
    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getBoneNameBind, segment)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.set_bone
     */
    fun setBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setBoneBind, segment, bone)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: ModifierBoneTarget3D.get_bone
     */
    fun getBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBoneBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ModifierBoneTarget3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ModifierBoneTarget3D? =
            if (handle.address() == 0L) null else ModifierBoneTarget3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BONE_NAME_HASH = 83702148L
        @JvmField
        val setBoneNameBind =
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "set_bone_name", SET_BONE_NAME_HASH)

        private const val GET_BONE_NAME_HASH = 201670096L
        @JvmField
        val getBoneNameBind =
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "get_bone_name", GET_BONE_NAME_HASH)

        private const val SET_BONE_HASH = 1286410249L
        @JvmField
        val setBoneBind =
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "set_bone", SET_BONE_HASH)

        private const val GET_BONE_HASH = 3905245786L
        @JvmField
        val getBoneBind =
            ObjectCalls.getMethodBind("ModifierBoneTarget3D", "get_bone", GET_BONE_HASH)
    }
}

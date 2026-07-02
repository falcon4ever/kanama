package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node for inverse kinematics which may modify more than one bone.
 *
 * Generated from Godot docs: IKModifier3D
 */
open class IKModifier3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var mutableBoneAxes: Boolean
        @JvmName("mutableBoneAxesProperty")
        get() = areBoneAxesMutable()
        @JvmName("setMutableBoneAxesProperty")
        set(value) = setMutableBoneAxes(value)

    fun setSettingCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setSettingCountBind, handle, count)
    }

    fun getSettingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSettingCountBind, handle)
    }

    fun clearSettings() {
        ObjectCalls.ptrcallNoArgs(clearSettingsBind, handle)
    }

    fun setMutableBoneAxes(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMutableBoneAxesBind, handle, enabled)
    }

    fun areBoneAxesMutable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areBoneAxesMutableBind, handle)
    }

    /**
     * Resets a state with respect to the current bone pose.
     *
     * Generated from Godot docs: IKModifier3D.reset
     */
    fun reset() {
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): IKModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): IKModifier3D? =
            if (handle.address() == 0L) null else IKModifier3D(handle)

        private const val SET_SETTING_COUNT_HASH = 1286410249L
        private val setSettingCountBind by lazy {
            ObjectCalls.getMethodBind("IKModifier3D", "set_setting_count", SET_SETTING_COUNT_HASH)
        }

        private const val GET_SETTING_COUNT_HASH = 3905245786L
        private val getSettingCountBind by lazy {
            ObjectCalls.getMethodBind("IKModifier3D", "get_setting_count", GET_SETTING_COUNT_HASH)
        }

        private const val CLEAR_SETTINGS_HASH = 3218959716L
        private val clearSettingsBind by lazy {
            ObjectCalls.getMethodBind("IKModifier3D", "clear_settings", CLEAR_SETTINGS_HASH)
        }

        private const val SET_MUTABLE_BONE_AXES_HASH = 2586408642L
        private val setMutableBoneAxesBind by lazy {
            ObjectCalls.getMethodBind("IKModifier3D", "set_mutable_bone_axes", SET_MUTABLE_BONE_AXES_HASH)
        }

        private const val ARE_BONE_AXES_MUTABLE_HASH = 36873697L
        private val areBoneAxesMutableBind by lazy {
            ObjectCalls.getMethodBind("IKModifier3D", "are_bone_axes_mutable", ARE_BONE_AXES_MUTABLE_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("IKModifier3D", "reset", RESET_HASH)
        }
    }
}

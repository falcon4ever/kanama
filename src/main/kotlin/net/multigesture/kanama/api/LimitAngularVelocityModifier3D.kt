package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Limit bone rotation angular velocity.
 *
 * Generated from Godot docs: LimitAngularVelocityModifier3D
 */
class LimitAngularVelocityModifier3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var maxAngularVelocity: Double
        @JvmName("maxAngularVelocityProperty")
        get() = getMaxAngularVelocity()
        @JvmName("setMaxAngularVelocityProperty")
        set(value) = setMaxAngularVelocity(value)

    var exclude: Boolean
        @JvmName("excludeProperty")
        get() = isExclude()
        @JvmName("setExcludeProperty")
        set(value) = setExclude(value)

    var chainCount: Int
        @JvmName("chainCountProperty")
        get() = getChainCount()
        @JvmName("setChainCountProperty")
        set(value) = setChainCount(value)

    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setRootBoneNameBind, handle, index, boneName)
    }

    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getRootBoneNameBind, handle, index)
    }

    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setRootBoneBind, handle, index, bone)
    }

    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getRootBoneBind, handle, index)
    }

    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setEndBoneNameBind, handle, index, boneName)
    }

    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getEndBoneNameBind, handle, index)
    }

    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setEndBoneBind, handle, index, bone)
    }

    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getEndBoneBind, handle, index)
    }

    fun setChainCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setChainCountBind, handle, count)
    }

    fun getChainCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getChainCountBind, handle)
    }

    fun clearChains() {
        ObjectCalls.ptrcallNoArgs(clearChainsBind, handle)
    }

    fun setMaxAngularVelocity(angularVelocity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxAngularVelocityBind, handle, angularVelocity)
    }

    fun getMaxAngularVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxAngularVelocityBind, handle)
    }

    fun setExclude(exclude: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExcludeBind, handle, exclude)
    }

    fun isExclude(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isExcludeBind, handle)
    }

    /**
     * Sets the reference pose for angle comparison to the current pose with the influence of
     * constraints removed. This function is automatically triggered when joints change or upon
     * activation.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.reset
     */
    fun reset() {
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): LimitAngularVelocityModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LimitAngularVelocityModifier3D? =
            if (handle.address() == 0L) null else LimitAngularVelocityModifier3D(handle)

        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        private val setRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)
        }

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        private val getRootBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)
        }

        private const val SET_ROOT_BONE_HASH = 3937882851L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 923996154L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_END_BONE_NAME_HASH = 501894301L
        private val setEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)
        }

        private const val GET_END_BONE_NAME_HASH = 844755477L
        private val getEndBoneNameBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)
        }

        private const val SET_END_BONE_HASH = 3937882851L
        private val setEndBoneBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_end_bone", SET_END_BONE_HASH)
        }

        private const val GET_END_BONE_HASH = 923996154L
        private val getEndBoneBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_end_bone", GET_END_BONE_HASH)
        }

        private const val SET_CHAIN_COUNT_HASH = 1286410249L
        private val setChainCountBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_chain_count", SET_CHAIN_COUNT_HASH)
        }

        private const val GET_CHAIN_COUNT_HASH = 3905245786L
        private val getChainCountBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_chain_count", GET_CHAIN_COUNT_HASH)
        }

        private const val CLEAR_CHAINS_HASH = 3218959716L
        private val clearChainsBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "clear_chains", CLEAR_CHAINS_HASH)
        }

        private const val SET_MAX_ANGULAR_VELOCITY_HASH = 373806689L
        private val setMaxAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_max_angular_velocity", SET_MAX_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_MAX_ANGULAR_VELOCITY_HASH = 1740695150L
        private val getMaxAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_max_angular_velocity", GET_MAX_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_EXCLUDE_HASH = 2586408642L
        private val setExcludeBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_exclude", SET_EXCLUDE_HASH)
        }

        private const val IS_EXCLUDE_HASH = 36873697L
        private val isExcludeBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "is_exclude", IS_EXCLUDE_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "reset", RESET_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Limit bone rotation angular velocity.
 *
 * Generated from Godot docs: LimitAngularVelocityModifier3D
 */
class LimitAngularVelocityModifier3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
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

    /**
     * Sets the root bone name of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_root_bone_name
     */
    fun setRootBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setRootBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the root bone name of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.get_root_bone_name
     */
    fun getRootBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getRootBoneNameBind, segment, index)
    }

    /**
     * Sets the root bone index of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_root_bone
     */
    fun setRootBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setRootBoneBind, segment, index, bone)
    }

    /**
     * Returns the root bone index of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.get_root_bone
     */
    fun getRootBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getRootBoneBind, segment, index)
    }

    /**
     * Sets the end bone name of the bone chain. Note: End bone must be the root bone or a child of the
     * root bone.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_end_bone_name
     */
    fun setEndBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setEndBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the end bone name of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.get_end_bone_name
     */
    fun getEndBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getEndBoneNameBind, segment, index)
    }

    /**
     * Sets the end bone index of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_end_bone
     */
    fun setEndBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setEndBoneBind, segment, index, bone)
    }

    /**
     * Returns the end bone index of the bone chain.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.get_end_bone
     */
    fun getEndBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getEndBoneBind, segment, index)
    }

    /**
     * The number of chains.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_chain_count
     */
    fun setChainCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setChainCountBind, segment, count)
    }

    /**
     * The number of chains.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.get_chain_count
     */
    fun getChainCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getChainCountBind, segment)
    }

    /**
     * Clear all chains.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.clear_chains
     */
    fun clearChains() {
        ObjectCalls.ptrcallNoArgs(Binds.clearChainsBind, segment)
    }

    /**
     * The maximum angular velocity per second.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_max_angular_velocity
     */
    fun setMaxAngularVelocity(angularVelocity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxAngularVelocityBind, segment, angularVelocity)
    }

    /**
     * The maximum angular velocity per second.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.get_max_angular_velocity
     */
    fun getMaxAngularVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxAngularVelocityBind, segment)
    }

    /**
     * If `true`, the modifier processes bones not included in the bone list. If `false`, the bones
     * processed by the modifier are equal to the bone list.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.set_exclude
     */
    fun setExclude(exclude: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExcludeBind, segment, exclude)
    }

    /**
     * If `true`, the modifier processes bones not included in the bone list. If `false`, the bones
     * processed by the modifier are equal to the bone list.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.is_exclude
     */
    fun isExclude(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isExcludeBind, segment)
    }

    /**
     * Sets the reference pose for angle comparison to the current pose with the influence of
     * constraints removed. This function is automatically triggered when joints change or upon
     * activation.
     *
     * Generated from Godot docs: LimitAngularVelocityModifier3D.reset
     */
    fun reset() {
        ObjectCalls.ptrcallNoArgs(Binds.resetBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LimitAngularVelocityModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): LimitAngularVelocityModifier3D? =
            if (handle.address() == 0L) null else LimitAngularVelocityModifier3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ROOT_BONE_NAME_HASH = 501894301L
        @JvmField
        val setRootBoneNameBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_root_bone_name", SET_ROOT_BONE_NAME_HASH)

        private const val GET_ROOT_BONE_NAME_HASH = 844755477L
        @JvmField
        val getRootBoneNameBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_root_bone_name", GET_ROOT_BONE_NAME_HASH)

        private const val SET_ROOT_BONE_HASH = 3937882851L
        @JvmField
        val setRootBoneBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_root_bone", SET_ROOT_BONE_HASH)

        private const val GET_ROOT_BONE_HASH = 923996154L
        @JvmField
        val getRootBoneBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_root_bone", GET_ROOT_BONE_HASH)

        private const val SET_END_BONE_NAME_HASH = 501894301L
        @JvmField
        val setEndBoneNameBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_end_bone_name", SET_END_BONE_NAME_HASH)

        private const val GET_END_BONE_NAME_HASH = 844755477L
        @JvmField
        val getEndBoneNameBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_end_bone_name", GET_END_BONE_NAME_HASH)

        private const val SET_END_BONE_HASH = 3937882851L
        @JvmField
        val setEndBoneBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_end_bone", SET_END_BONE_HASH)

        private const val GET_END_BONE_HASH = 923996154L
        @JvmField
        val getEndBoneBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_end_bone", GET_END_BONE_HASH)

        private const val SET_CHAIN_COUNT_HASH = 1286410249L
        @JvmField
        val setChainCountBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_chain_count", SET_CHAIN_COUNT_HASH)

        private const val GET_CHAIN_COUNT_HASH = 3905245786L
        @JvmField
        val getChainCountBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_chain_count", GET_CHAIN_COUNT_HASH)

        private const val CLEAR_CHAINS_HASH = 3218959716L
        @JvmField
        val clearChainsBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "clear_chains", CLEAR_CHAINS_HASH)

        private const val SET_MAX_ANGULAR_VELOCITY_HASH = 373806689L
        @JvmField
        val setMaxAngularVelocityBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_max_angular_velocity", SET_MAX_ANGULAR_VELOCITY_HASH)

        private const val GET_MAX_ANGULAR_VELOCITY_HASH = 1740695150L
        @JvmField
        val getMaxAngularVelocityBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "get_max_angular_velocity", GET_MAX_ANGULAR_VELOCITY_HASH)

        private const val SET_EXCLUDE_HASH = 2586408642L
        @JvmField
        val setExcludeBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "set_exclude", SET_EXCLUDE_HASH)

        private const val IS_EXCLUDE_HASH = 36873697L
        @JvmField
        val isExcludeBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "is_exclude", IS_EXCLUDE_HASH)

        private const val RESET_HASH = 3218959716L
        @JvmField
        val resetBind =
            ObjectCalls.getMethodBind("LimitAngularVelocityModifier3D", "reset", RESET_HASH)
    }
}

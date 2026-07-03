package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A modifier to transfer parent skeleton poses (or global poses) to child skeletons in model space
 * with different rests.
 *
 * Generated from Godot docs: RetargetModifier3D
 */
class RetargetModifier3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var profile: SkeletonProfile?
        @JvmName("profileProperty")
        get() = getProfile()
        @JvmName("setProfileProperty")
        set(value) = setProfile(value)

    var useGlobalPose: Boolean
        @JvmName("useGlobalPoseProperty")
        get() = isUsingGlobalPose()
        @JvmName("setUseGlobalPoseProperty")
        set(value) = setUseGlobalPose(value)

    var enable: Long
        @JvmName("enableProperty")
        get() = getEnableFlags()
        @JvmName("setEnableProperty")
        set(value) = setEnableFlags(value)

    /**
     * `SkeletonProfile` for retargeting bones with names matching the bone list.
     *
     * Generated from Godot docs: RetargetModifier3D.set_profile
     */
    fun setProfile(profile: SkeletonProfile?) {
        ObjectCalls.ptrcallWithObjectArgs(setProfileBind, handle, listOf(profile?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `SkeletonProfile` for retargeting bones with names matching the bone list.
     *
     * Generated from Godot docs: RetargetModifier3D.get_profile
     */
    fun getProfile(): SkeletonProfile? {
        return SkeletonProfile.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProfileBind, handle))
    }

    /**
     * If `false`, in case the target skeleton has fewer bones than the source skeleton, the source
     * bone parent's transform will be ignored. Instead, it is possible to retarget between models with
     * different body shapes, and position, rotation, and scale can be retargeted separately. If
     * `true`, retargeting is performed taking into account global pose. In case the target skeleton
     * has fewer bones than the source skeleton, the source bone parent's transform is taken into
     * account. However, bone length between skeletons must match exactly, if not, the bones will be
     * forced to expand or shrink. This is useful for using dummy bone with length `0` to match
     * postures when retargeting between models with different number of bones.
     *
     * Generated from Godot docs: RetargetModifier3D.set_use_global_pose
     */
    fun setUseGlobalPose(useGlobalPose: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseGlobalPoseBind, handle, useGlobalPose)
    }

    /**
     * If `false`, in case the target skeleton has fewer bones than the source skeleton, the source
     * bone parent's transform will be ignored. Instead, it is possible to retarget between models with
     * different body shapes, and position, rotation, and scale can be retargeted separately. If
     * `true`, retargeting is performed taking into account global pose. In case the target skeleton
     * has fewer bones than the source skeleton, the source bone parent's transform is taken into
     * account. However, bone length between skeletons must match exactly, if not, the bones will be
     * forced to expand or shrink. This is useful for using dummy bone with length `0` to match
     * postures when retargeting between models with different number of bones.
     *
     * Generated from Godot docs: RetargetModifier3D.is_using_global_pose
     */
    fun isUsingGlobalPose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingGlobalPoseBind, handle)
    }

    /**
     * Flags to control the process of the transform elements individually when `use_global_pose` is
     * disabled.
     *
     * Generated from Godot docs: RetargetModifier3D.set_enable_flags
     */
    fun setEnableFlags(enableFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setEnableFlagsBind, handle, enableFlags)
    }

    /**
     * Flags to control the process of the transform elements individually when `use_global_pose` is
     * disabled.
     *
     * Generated from Godot docs: RetargetModifier3D.get_enable_flags
     */
    fun getEnableFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEnableFlagsBind, handle)
    }

    /**
     * Sets `TRANSFORM_FLAG_POSITION` into `enable`.
     *
     * Generated from Godot docs: RetargetModifier3D.set_position_enabled
     */
    fun setPositionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPositionEnabledBind, handle, enabled)
    }

    /**
     * Returns `true` if `enable` has `TRANSFORM_FLAG_POSITION`.
     *
     * Generated from Godot docs: RetargetModifier3D.is_position_enabled
     */
    fun isPositionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPositionEnabledBind, handle)
    }

    /**
     * Sets `TRANSFORM_FLAG_ROTATION` into `enable`.
     *
     * Generated from Godot docs: RetargetModifier3D.set_rotation_enabled
     */
    fun setRotationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRotationEnabledBind, handle, enabled)
    }

    /**
     * Returns `true` if `enable` has `TRANSFORM_FLAG_ROTATION`.
     *
     * Generated from Godot docs: RetargetModifier3D.is_rotation_enabled
     */
    fun isRotationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRotationEnabledBind, handle)
    }

    /**
     * Sets `TRANSFORM_FLAG_SCALE` into `enable`.
     *
     * Generated from Godot docs: RetargetModifier3D.set_scale_enabled
     */
    fun setScaleEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScaleEnabledBind, handle, enabled)
    }

    /**
     * Returns `true` if `enable` has `TRANSFORM_FLAG_SCALE`.
     *
     * Generated from Godot docs: RetargetModifier3D.is_scale_enabled
     */
    fun isScaleEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScaleEnabledBind, handle)
    }

    companion object {
        const val TRANSFORM_FLAG_POSITION: Long = 1L
        const val TRANSFORM_FLAG_ROTATION: Long = 2L
        const val TRANSFORM_FLAG_SCALE: Long = 4L
        const val TRANSFORM_FLAG_ALL: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): RetargetModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RetargetModifier3D? =
            if (handle.address() == 0L) null else RetargetModifier3D(handle)

        private const val SET_PROFILE_HASH = 3870374136L
        private val setProfileBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "set_profile", SET_PROFILE_HASH)
        }

        private const val GET_PROFILE_HASH = 4291782652L
        private val getProfileBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "get_profile", GET_PROFILE_HASH)
        }

        private const val SET_USE_GLOBAL_POSE_HASH = 2586408642L
        private val setUseGlobalPoseBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "set_use_global_pose", SET_USE_GLOBAL_POSE_HASH)
        }

        private const val IS_USING_GLOBAL_POSE_HASH = 36873697L
        private val isUsingGlobalPoseBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "is_using_global_pose", IS_USING_GLOBAL_POSE_HASH)
        }

        private const val SET_ENABLE_FLAGS_HASH = 2687954213L
        private val setEnableFlagsBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "set_enable_flags", SET_ENABLE_FLAGS_HASH)
        }

        private const val GET_ENABLE_FLAGS_HASH = 358995420L
        private val getEnableFlagsBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "get_enable_flags", GET_ENABLE_FLAGS_HASH)
        }

        private const val SET_POSITION_ENABLED_HASH = 2586408642L
        private val setPositionEnabledBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "set_position_enabled", SET_POSITION_ENABLED_HASH)
        }

        private const val IS_POSITION_ENABLED_HASH = 36873697L
        private val isPositionEnabledBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "is_position_enabled", IS_POSITION_ENABLED_HASH)
        }

        private const val SET_ROTATION_ENABLED_HASH = 2586408642L
        private val setRotationEnabledBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "set_rotation_enabled", SET_ROTATION_ENABLED_HASH)
        }

        private const val IS_ROTATION_ENABLED_HASH = 36873697L
        private val isRotationEnabledBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "is_rotation_enabled", IS_ROTATION_ENABLED_HASH)
        }

        private const val SET_SCALE_ENABLED_HASH = 2586408642L
        private val setScaleEnabledBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "set_scale_enabled", SET_SCALE_ENABLED_HASH)
        }

        private const val IS_SCALE_ENABLED_HASH = 36873697L
        private val isScaleEnabledBind by lazy {
            ObjectCalls.getMethodBind("RetargetModifier3D", "is_scale_enabled", IS_SCALE_ENABLED_HASH)
        }
    }
}

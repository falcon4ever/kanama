package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A modifier to transfer parent skeleton poses (or global poses) to child skeletons in model space
 * with different rests.
 *
 * Generated from Godot docs: RetargetModifier3D
 */
class RetargetModifier3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
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

    var enable: RetargetModifier3D.TransformFlag
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
        ObjectCalls.ptrcallWithObjectArgs(setProfileBind, segment, listOf(profile?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `SkeletonProfile` for retargeting bones with names matching the bone list.
     *
     * Generated from Godot docs: RetargetModifier3D.get_profile
     */
    fun getProfile(): SkeletonProfile? {
        return SkeletonProfile.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProfileBind, segment))
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
        ObjectCalls.ptrcallWithBoolArg(setUseGlobalPoseBind, segment, useGlobalPose)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingGlobalPoseBind, segment)
    }

    /**
     * Flags to control the process of the transform elements individually when `use_global_pose` is
     * disabled.
     *
     * Generated from Godot docs: RetargetModifier3D.set_enable_flags
     */
    fun setEnableFlags(enableFlags: RetargetModifier3D.TransformFlag) {
        ObjectCalls.ptrcallWithLongArg(setEnableFlagsBind, segment, enableFlags.value)
    }

    /**
     * Flags to control the process of the transform elements individually when `use_global_pose` is
     * disabled.
     *
     * Generated from Godot docs: RetargetModifier3D.get_enable_flags
     */
    fun getEnableFlags(): RetargetModifier3D.TransformFlag {
        return RetargetModifier3D.TransformFlag(ObjectCalls.ptrcallNoArgsRetLong(getEnableFlagsBind, segment))
    }

    /**
     * Sets `TransformFlag.POSITION` into `enable`.
     *
     * Generated from Godot docs: RetargetModifier3D.set_position_enabled
     */
    fun setPositionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPositionEnabledBind, segment, enabled)
    }

    /**
     * Returns `true` if `enable` has `TransformFlag.POSITION`.
     *
     * Generated from Godot docs: RetargetModifier3D.is_position_enabled
     */
    fun isPositionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPositionEnabledBind, segment)
    }

    /**
     * Sets `TransformFlag.ROTATION` into `enable`.
     *
     * Generated from Godot docs: RetargetModifier3D.set_rotation_enabled
     */
    fun setRotationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRotationEnabledBind, segment, enabled)
    }

    /**
     * Returns `true` if `enable` has `TransformFlag.ROTATION`.
     *
     * Generated from Godot docs: RetargetModifier3D.is_rotation_enabled
     */
    fun isRotationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRotationEnabledBind, segment)
    }

    /**
     * Sets `TransformFlag.SCALE` into `enable`.
     *
     * Generated from Godot docs: RetargetModifier3D.set_scale_enabled
     */
    fun setScaleEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScaleEnabledBind, segment, enabled)
    }

    /**
     * Returns `true` if `enable` has `TransformFlag.SCALE`.
     *
     * Generated from Godot docs: RetargetModifier3D.is_scale_enabled
     */
    fun isScaleEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScaleEnabledBind, segment)
    }

    /**
     * Godot's `RetargetModifier3D.TransformFlag` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RetargetModifier3D.TransformFlag.<NAME>`).
     *
     * Generated from Godot docs: RetargetModifier3D.TransformFlag
     */
    @JvmInline
    value class TransformFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: TransformFlag): TransformFlag = TransformFlag(value or other.value)

        infix fun and(other: TransformFlag): TransformFlag = TransformFlag(value and other.value)

        infix fun xor(other: TransformFlag): TransformFlag = TransformFlag(value xor other.value)

        fun inv(): TransformFlag = TransformFlag(value.inv())

        operator fun contains(other: TransformFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * If set, allows to retarget the position.
             *
             * Generated from Godot docs: RetargetModifier3D.TRANSFORM_FLAG_POSITION
             */
            val POSITION: TransformFlag get() = TransformFlag(1L)
            /**
             * If set, allows to retarget the rotation.
             *
             * Generated from Godot docs: RetargetModifier3D.TRANSFORM_FLAG_ROTATION
             */
            val ROTATION: TransformFlag get() = TransformFlag(2L)
            /**
             * If set, allows to retarget the scale.
             *
             * Generated from Godot docs: RetargetModifier3D.TRANSFORM_FLAG_SCALE
             */
            val SCALE: TransformFlag get() = TransformFlag(4L)
            /**
             * If set, allows to retarget the position/rotation/scale.
             *
             * Generated from Godot docs: RetargetModifier3D.TRANSFORM_FLAG_ALL
             */
            val ALL: TransformFlag get() = TransformFlag(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RetargetModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RetargetModifier3D? =
            if (handle.address() == 0L) null else RetargetModifier3D(GodotHandle(handle))

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

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * The `AimModifier3D` rotates a bone to look at a reference bone.
 *
 * Generated from Godot docs: AimModifier3D
 */
class AimModifier3D(handle: GodotHandle) : BoneConstraint3D(handle) {
    /**
     * Sets the forward axis of the bone.
     *
     * Generated from Godot docs: AimModifier3D.set_forward_axis
     */
    fun setForwardAxis(index: Int, axis: SkeletonModifier3D.BoneAxis) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setForwardAxisBind, segment, index, axis.value)
    }

    /**
     * Returns the forward axis of the bone.
     *
     * Generated from Godot docs: AimModifier3D.get_forward_axis
     */
    fun getForwardAxis(index: Int): SkeletonModifier3D.BoneAxis {
        return SkeletonModifier3D.BoneAxis(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getForwardAxisBind, segment, index))
    }

    /**
     * If sets `enabled` to `true`, it provides rotation with using euler. If sets `enabled` to
     * `false`, it provides rotation with using rotation by arc generated from the forward axis vector
     * and the vector toward the reference.
     *
     * Generated from Godot docs: AimModifier3D.set_use_euler
     */
    fun setUseEuler(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setUseEulerBind, segment, index, enabled)
    }

    /**
     * Returns `true` if it provides rotation with using euler.
     *
     * Generated from Godot docs: AimModifier3D.is_using_euler
     */
    fun isUsingEuler(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isUsingEulerBind, segment, index)
    }

    /**
     * Sets the axis of the first rotation. It is enabled only if `is_using_euler` is `true`.
     *
     * Generated from Godot docs: AimModifier3D.set_primary_rotation_axis
     */
    fun setPrimaryRotationAxis(index: Int, axis: Vector3.Axis) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setPrimaryRotationAxisBind, segment, index, axis.value)
    }

    /**
     * Returns the axis of the first rotation. It is enabled only if `is_using_euler` is `true`.
     *
     * Generated from Godot docs: AimModifier3D.get_primary_rotation_axis
     */
    fun getPrimaryRotationAxis(index: Int): Vector3.Axis {
        return Vector3.Axis(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getPrimaryRotationAxisBind, segment, index))
    }

    /**
     * If sets `enabled` to `true`, it provides rotation by two axes. It is enabled only if
     * `is_using_euler` is `true`.
     *
     * Generated from Godot docs: AimModifier3D.set_use_secondary_rotation
     */
    fun setUseSecondaryRotation(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setUseSecondaryRotationBind, segment, index, enabled)
    }

    /**
     * Returns `true` if it provides rotation by two axes. It is enabled only if `is_using_euler` is
     * `true`.
     *
     * Generated from Godot docs: AimModifier3D.is_using_secondary_rotation
     */
    fun isUsingSecondaryRotation(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isUsingSecondaryRotationBind, segment, index)
    }

    /**
     * Sets relative option in the setting at `index` to `enabled`. If sets `enabled` to `true`, the
     * rotation is applied relative to the pose. If sets `enabled` to `false`, the rotation is applied
     * relative to the rest. It means to replace the current pose with the `AimModifier3D`'s result.
     *
     * Generated from Godot docs: AimModifier3D.set_relative
     */
    fun setRelative(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setRelativeBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the relative option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: AimModifier3D.is_relative
     */
    fun isRelative(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isRelativeBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AimModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AimModifier3D? =
            if (handle.address() == 0L) null else AimModifier3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FORWARD_AXIS_HASH = 2496831085L
        @JvmField
        val setForwardAxisBind =
            ObjectCalls.getMethodBind("AimModifier3D", "set_forward_axis", SET_FORWARD_AXIS_HASH)

        private const val GET_FORWARD_AXIS_HASH = 3949866735L
        @JvmField
        val getForwardAxisBind =
            ObjectCalls.getMethodBind("AimModifier3D", "get_forward_axis", GET_FORWARD_AXIS_HASH)

        private const val SET_USE_EULER_HASH = 300928843L
        @JvmField
        val setUseEulerBind =
            ObjectCalls.getMethodBind("AimModifier3D", "set_use_euler", SET_USE_EULER_HASH)

        private const val IS_USING_EULER_HASH = 1116898809L
        @JvmField
        val isUsingEulerBind =
            ObjectCalls.getMethodBind("AimModifier3D", "is_using_euler", IS_USING_EULER_HASH)

        private const val SET_PRIMARY_ROTATION_AXIS_HASH = 776736805L
        @JvmField
        val setPrimaryRotationAxisBind =
            ObjectCalls.getMethodBind("AimModifier3D", "set_primary_rotation_axis", SET_PRIMARY_ROTATION_AXIS_HASH)

        private const val GET_PRIMARY_ROTATION_AXIS_HASH = 4131134770L
        @JvmField
        val getPrimaryRotationAxisBind =
            ObjectCalls.getMethodBind("AimModifier3D", "get_primary_rotation_axis", GET_PRIMARY_ROTATION_AXIS_HASH)

        private const val SET_USE_SECONDARY_ROTATION_HASH = 300928843L
        @JvmField
        val setUseSecondaryRotationBind =
            ObjectCalls.getMethodBind("AimModifier3D", "set_use_secondary_rotation", SET_USE_SECONDARY_ROTATION_HASH)

        private const val IS_USING_SECONDARY_ROTATION_HASH = 1116898809L
        @JvmField
        val isUsingSecondaryRotationBind =
            ObjectCalls.getMethodBind("AimModifier3D", "is_using_secondary_rotation", IS_USING_SECONDARY_ROTATION_HASH)

        private const val SET_RELATIVE_HASH = 300928843L
        @JvmField
        val setRelativeBind =
            ObjectCalls.getMethodBind("AimModifier3D", "set_relative", SET_RELATIVE_HASH)

        private const val IS_RELATIVE_HASH = 1116898809L
        @JvmField
        val isRelativeBind =
            ObjectCalls.getMethodBind("AimModifier3D", "is_relative", IS_RELATIVE_HASH)
    }
}

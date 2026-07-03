package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * The `AimModifier3D` rotates a bone to look at a reference bone.
 *
 * Generated from Godot docs: AimModifier3D
 */
class AimModifier3D(handle: MemorySegment) : BoneConstraint3D(handle) {
    /**
     * Sets the forward axis of the bone.
     *
     * Generated from Godot docs: AimModifier3D.set_forward_axis
     */
    fun setForwardAxis(index: Int, axis: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setForwardAxisBind, handle, index, axis)
    }

    /**
     * Returns the forward axis of the bone.
     *
     * Generated from Godot docs: AimModifier3D.get_forward_axis
     */
    fun getForwardAxis(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getForwardAxisBind, handle, index)
    }

    /**
     * If sets `enabled` to `true`, it provides rotation with using euler. If sets `enabled` to
     * `false`, it provides rotation with using rotation by arc generated from the forward axis vector
     * and the vector toward the reference.
     *
     * Generated from Godot docs: AimModifier3D.set_use_euler
     */
    fun setUseEuler(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setUseEulerBind, handle, index, enabled)
    }

    /**
     * Returns `true` if it provides rotation with using euler.
     *
     * Generated from Godot docs: AimModifier3D.is_using_euler
     */
    fun isUsingEuler(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isUsingEulerBind, handle, index)
    }

    /**
     * Sets the axis of the first rotation. It is enabled only if `is_using_euler` is `true`.
     *
     * Generated from Godot docs: AimModifier3D.set_primary_rotation_axis
     */
    fun setPrimaryRotationAxis(index: Int, axis: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setPrimaryRotationAxisBind, handle, index, axis)
    }

    /**
     * Returns the axis of the first rotation. It is enabled only if `is_using_euler` is `true`.
     *
     * Generated from Godot docs: AimModifier3D.get_primary_rotation_axis
     */
    fun getPrimaryRotationAxis(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getPrimaryRotationAxisBind, handle, index)
    }

    /**
     * If sets `enabled` to `true`, it provides rotation by two axes. It is enabled only if
     * `is_using_euler` is `true`.
     *
     * Generated from Godot docs: AimModifier3D.set_use_secondary_rotation
     */
    fun setUseSecondaryRotation(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setUseSecondaryRotationBind, handle, index, enabled)
    }

    /**
     * Returns `true` if it provides rotation by two axes. It is enabled only if `is_using_euler` is
     * `true`.
     *
     * Generated from Godot docs: AimModifier3D.is_using_secondary_rotation
     */
    fun isUsingSecondaryRotation(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isUsingSecondaryRotationBind, handle, index)
    }

    /**
     * Sets relative option in the setting at `index` to `enabled`. If sets `enabled` to `true`, the
     * rotation is applied relative to the pose. If sets `enabled` to `false`, the rotation is applied
     * relative to the rest. It means to replace the current pose with the `AimModifier3D`'s result.
     *
     * Generated from Godot docs: AimModifier3D.set_relative
     */
    fun setRelative(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setRelativeBind, handle, index, enabled)
    }

    /**
     * Returns `true` if the relative option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: AimModifier3D.is_relative
     */
    fun isRelative(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRelativeBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AimModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AimModifier3D? =
            if (handle.address() == 0L) null else AimModifier3D(handle)

        private const val SET_FORWARD_AXIS_HASH = 2496831085L
        private val setForwardAxisBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "set_forward_axis", SET_FORWARD_AXIS_HASH)
        }

        private const val GET_FORWARD_AXIS_HASH = 3949866735L
        private val getForwardAxisBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "get_forward_axis", GET_FORWARD_AXIS_HASH)
        }

        private const val SET_USE_EULER_HASH = 300928843L
        private val setUseEulerBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "set_use_euler", SET_USE_EULER_HASH)
        }

        private const val IS_USING_EULER_HASH = 1116898809L
        private val isUsingEulerBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "is_using_euler", IS_USING_EULER_HASH)
        }

        private const val SET_PRIMARY_ROTATION_AXIS_HASH = 776736805L
        private val setPrimaryRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "set_primary_rotation_axis", SET_PRIMARY_ROTATION_AXIS_HASH)
        }

        private const val GET_PRIMARY_ROTATION_AXIS_HASH = 4131134770L
        private val getPrimaryRotationAxisBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "get_primary_rotation_axis", GET_PRIMARY_ROTATION_AXIS_HASH)
        }

        private const val SET_USE_SECONDARY_ROTATION_HASH = 300928843L
        private val setUseSecondaryRotationBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "set_use_secondary_rotation", SET_USE_SECONDARY_ROTATION_HASH)
        }

        private const val IS_USING_SECONDARY_ROTATION_HASH = 1116898809L
        private val isUsingSecondaryRotationBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "is_using_secondary_rotation", IS_USING_SECONDARY_ROTATION_HASH)
        }

        private const val SET_RELATIVE_HASH = 300928843L
        private val setRelativeBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "set_relative", SET_RELATIVE_HASH)
        }

        private const val IS_RELATIVE_HASH = 1116898809L
        private val isRelativeBind by lazy {
            ObjectCalls.getMethodBind("AimModifier3D", "is_relative", IS_RELATIVE_HASH)
        }
    }
}

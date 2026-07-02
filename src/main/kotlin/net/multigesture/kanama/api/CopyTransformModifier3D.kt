package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A `SkeletonModifier3D` that apply transform to the bone which copied from reference.
 *
 * Generated from Godot docs: CopyTransformModifier3D
 */
class CopyTransformModifier3D(handle: MemorySegment) : BoneConstraint3D(handle) {
    fun setCopyFlags(index: Int, copyFlags: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCopyFlagsBind, handle, index, copyFlags)
    }

    fun getCopyFlags(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCopyFlagsBind, handle, index)
    }

    fun setAxisFlags(index: Int, axisFlags: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setAxisFlagsBind, handle, index, axisFlags)
    }

    fun getAxisFlags(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getAxisFlagsBind, handle, index)
    }

    fun setInvertFlags(index: Int, axisFlags: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setInvertFlagsBind, handle, index, axisFlags)
    }

    fun getInvertFlags(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getInvertFlagsBind, handle, index)
    }

    fun setCopyPosition(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCopyPositionBind, handle, index, enabled)
    }

    fun isPositionCopying(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isPositionCopyingBind, handle, index)
    }

    fun setCopyRotation(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCopyRotationBind, handle, index, enabled)
    }

    fun isRotationCopying(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRotationCopyingBind, handle, index)
    }

    fun setCopyScale(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCopyScaleBind, handle, index, enabled)
    }

    fun isScaleCopying(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isScaleCopyingBind, handle, index)
    }

    fun setAxisXEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisXEnabledBind, handle, index, enabled)
    }

    fun isAxisXEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisXEnabledBind, handle, index)
    }

    fun setAxisYEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisYEnabledBind, handle, index, enabled)
    }

    fun isAxisYEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisYEnabledBind, handle, index)
    }

    fun setAxisZEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisZEnabledBind, handle, index, enabled)
    }

    fun isAxisZEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisZEnabledBind, handle, index)
    }

    fun setAxisXInverted(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisXInvertedBind, handle, index, enabled)
    }

    fun isAxisXInverted(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisXInvertedBind, handle, index)
    }

    fun setAxisYInverted(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisYInvertedBind, handle, index, enabled)
    }

    fun isAxisYInverted(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisYInvertedBind, handle, index)
    }

    fun setAxisZInverted(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisZInvertedBind, handle, index, enabled)
    }

    fun isAxisZInverted(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisZInvertedBind, handle, index)
    }

    fun setRelative(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setRelativeBind, handle, index, enabled)
    }

    fun isRelative(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRelativeBind, handle, index)
    }

    fun setAdditive(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAdditiveBind, handle, index, enabled)
    }

    fun isAdditive(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAdditiveBind, handle, index)
    }

    companion object {
        const val TRANSFORM_FLAG_POSITION: Long = 1L
        const val TRANSFORM_FLAG_ROTATION: Long = 2L
        const val TRANSFORM_FLAG_SCALE: Long = 4L
        const val TRANSFORM_FLAG_ALL: Long = 7L
        const val AXIS_FLAG_X: Long = 1L
        const val AXIS_FLAG_Y: Long = 2L
        const val AXIS_FLAG_Z: Long = 4L
        const val AXIS_FLAG_ALL: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CopyTransformModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CopyTransformModifier3D? =
            if (handle.address() == 0L) null else CopyTransformModifier3D(handle)

        private const val SET_COPY_FLAGS_HASH = 2252507859L
        private val setCopyFlagsBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_copy_flags", SET_COPY_FLAGS_HASH)
        }

        private const val GET_COPY_FLAGS_HASH = 1685185931L
        private val getCopyFlagsBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "get_copy_flags", GET_COPY_FLAGS_HASH)
        }

        private const val SET_AXIS_FLAGS_HASH = 2044211897L
        private val setAxisFlagsBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_flags", SET_AXIS_FLAGS_HASH)
        }

        private const val GET_AXIS_FLAGS_HASH = 992162046L
        private val getAxisFlagsBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "get_axis_flags", GET_AXIS_FLAGS_HASH)
        }

        private const val SET_INVERT_FLAGS_HASH = 2044211897L
        private val setInvertFlagsBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_invert_flags", SET_INVERT_FLAGS_HASH)
        }

        private const val GET_INVERT_FLAGS_HASH = 992162046L
        private val getInvertFlagsBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "get_invert_flags", GET_INVERT_FLAGS_HASH)
        }

        private const val SET_COPY_POSITION_HASH = 300928843L
        private val setCopyPositionBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_copy_position", SET_COPY_POSITION_HASH)
        }

        private const val IS_POSITION_COPYING_HASH = 1116898809L
        private val isPositionCopyingBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_position_copying", IS_POSITION_COPYING_HASH)
        }

        private const val SET_COPY_ROTATION_HASH = 300928843L
        private val setCopyRotationBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_copy_rotation", SET_COPY_ROTATION_HASH)
        }

        private const val IS_ROTATION_COPYING_HASH = 1116898809L
        private val isRotationCopyingBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_rotation_copying", IS_ROTATION_COPYING_HASH)
        }

        private const val SET_COPY_SCALE_HASH = 300928843L
        private val setCopyScaleBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_copy_scale", SET_COPY_SCALE_HASH)
        }

        private const val IS_SCALE_COPYING_HASH = 1116898809L
        private val isScaleCopyingBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_scale_copying", IS_SCALE_COPYING_HASH)
        }

        private const val SET_AXIS_X_ENABLED_HASH = 300928843L
        private val setAxisXEnabledBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_x_enabled", SET_AXIS_X_ENABLED_HASH)
        }

        private const val IS_AXIS_X_ENABLED_HASH = 1116898809L
        private val isAxisXEnabledBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_axis_x_enabled", IS_AXIS_X_ENABLED_HASH)
        }

        private const val SET_AXIS_Y_ENABLED_HASH = 300928843L
        private val setAxisYEnabledBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_y_enabled", SET_AXIS_Y_ENABLED_HASH)
        }

        private const val IS_AXIS_Y_ENABLED_HASH = 1116898809L
        private val isAxisYEnabledBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_axis_y_enabled", IS_AXIS_Y_ENABLED_HASH)
        }

        private const val SET_AXIS_Z_ENABLED_HASH = 300928843L
        private val setAxisZEnabledBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_z_enabled", SET_AXIS_Z_ENABLED_HASH)
        }

        private const val IS_AXIS_Z_ENABLED_HASH = 1116898809L
        private val isAxisZEnabledBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_axis_z_enabled", IS_AXIS_Z_ENABLED_HASH)
        }

        private const val SET_AXIS_X_INVERTED_HASH = 300928843L
        private val setAxisXInvertedBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_x_inverted", SET_AXIS_X_INVERTED_HASH)
        }

        private const val IS_AXIS_X_INVERTED_HASH = 1116898809L
        private val isAxisXInvertedBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_axis_x_inverted", IS_AXIS_X_INVERTED_HASH)
        }

        private const val SET_AXIS_Y_INVERTED_HASH = 300928843L
        private val setAxisYInvertedBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_y_inverted", SET_AXIS_Y_INVERTED_HASH)
        }

        private const val IS_AXIS_Y_INVERTED_HASH = 1116898809L
        private val isAxisYInvertedBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_axis_y_inverted", IS_AXIS_Y_INVERTED_HASH)
        }

        private const val SET_AXIS_Z_INVERTED_HASH = 300928843L
        private val setAxisZInvertedBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_axis_z_inverted", SET_AXIS_Z_INVERTED_HASH)
        }

        private const val IS_AXIS_Z_INVERTED_HASH = 1116898809L
        private val isAxisZInvertedBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_axis_z_inverted", IS_AXIS_Z_INVERTED_HASH)
        }

        private const val SET_RELATIVE_HASH = 300928843L
        private val setRelativeBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_relative", SET_RELATIVE_HASH)
        }

        private const val IS_RELATIVE_HASH = 1116898809L
        private val isRelativeBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_relative", IS_RELATIVE_HASH)
        }

        private const val SET_ADDITIVE_HASH = 300928843L
        private val setAdditiveBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "set_additive", SET_ADDITIVE_HASH)
        }

        private const val IS_ADDITIVE_HASH = 1116898809L
        private val isAdditiveBind by lazy {
            ObjectCalls.getMethodBind("CopyTransformModifier3D", "is_additive", IS_ADDITIVE_HASH)
        }
    }
}

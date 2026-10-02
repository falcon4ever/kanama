package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `SkeletonModifier3D` that apply transform to the bone which copied from reference.
 *
 * Generated from Godot docs: CopyTransformModifier3D
 */
class CopyTransformModifier3D(handle: GodotHandle) : BoneConstraint3D(handle) {
    /**
     * Sets the flags to process the transform operations. If the flag is valid, the transform
     * operation is processed. Note: If the rotation is valid for only one axis, it respects the roll
     * of the valid axis. If the rotation is valid for two axes, it discards the roll of the invalid
     * axis.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_copy_flags
     */
    fun setCopyFlags(index: Int, copyFlags: CopyTransformModifier3D.TransformFlag) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCopyFlagsBind, segment, index, copyFlags.value)
    }

    /**
     * Returns the copy flags of the setting at `index`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.get_copy_flags
     */
    fun getCopyFlags(index: Int): CopyTransformModifier3D.TransformFlag {
        return CopyTransformModifier3D.TransformFlag(ObjectCalls.ptrcallWithIntArgRetLong(getCopyFlagsBind, segment, index))
    }

    /**
     * Sets the flags to copy axes. If the flag is valid, the axis is copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_flags
     */
    fun setAxisFlags(index: Int, axisFlags: CopyTransformModifier3D.AxisFlag) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setAxisFlagsBind, segment, index, axisFlags.value)
    }

    /**
     * Returns the axis flags of the setting at `index`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.get_axis_flags
     */
    fun getAxisFlags(index: Int): CopyTransformModifier3D.AxisFlag {
        return CopyTransformModifier3D.AxisFlag(ObjectCalls.ptrcallWithIntArgRetLong(getAxisFlagsBind, segment, index))
    }

    /**
     * Sets the flags to inverte axes. If the flag is valid, the axis is copied. Note: An inverted
     * scale means an inverse number, not a negative scale. For example, inverting `2.0` means `0.5`.
     * Note: An inverted rotation flips the elements of the quaternion. For example, a two-axis
     * inversion will flip the roll of each axis, and a three-axis inversion will flip the final
     * orientation. However, be aware that flipping only one axis may cause unintended rotation by the
     * unflipped axes, due to the characteristics of the quaternion.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_invert_flags
     */
    fun setInvertFlags(index: Int, axisFlags: CopyTransformModifier3D.AxisFlag) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setInvertFlagsBind, segment, index, axisFlags.value)
    }

    /**
     * Returns the invert flags of the setting at `index`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.get_invert_flags
     */
    fun getInvertFlags(index: Int): CopyTransformModifier3D.AxisFlag {
        return CopyTransformModifier3D.AxisFlag(ObjectCalls.ptrcallWithIntArgRetLong(getInvertFlagsBind, segment, index))
    }

    /**
     * If sets `enabled` to `true`, the position will be copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_copy_position
     */
    fun setCopyPosition(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCopyPositionBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the copy flags has the flag for the position in the setting at `index`. See
     * also `set_copy_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_position_copying
     */
    fun isPositionCopying(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isPositionCopyingBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the rotation will be copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_copy_rotation
     */
    fun setCopyRotation(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCopyRotationBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the copy flags has the flag for the rotation in the setting at `index`. See
     * also `set_copy_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_rotation_copying
     */
    fun isRotationCopying(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRotationCopyingBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the scale will be copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_copy_scale
     */
    fun setCopyScale(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCopyScaleBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the copy flags has the flag for the scale in the setting at `index`. See also
     * `set_copy_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_scale_copying
     */
    fun isScaleCopying(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isScaleCopyingBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the X-axis will be copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_x_enabled
     */
    fun setAxisXEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisXEnabledBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the enable flags has the flag for the X-axis in the setting at `index`. See
     * also `set_axis_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_axis_x_enabled
     */
    fun isAxisXEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisXEnabledBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the Y-axis will be copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_y_enabled
     */
    fun setAxisYEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisYEnabledBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the enable flags has the flag for the Y-axis in the setting at `index`. See
     * also `set_axis_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_axis_y_enabled
     */
    fun isAxisYEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisYEnabledBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the Z-axis will be copied.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_z_enabled
     */
    fun setAxisZEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisZEnabledBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the enable flags has the flag for the Z-axis in the setting at `index`. See
     * also `set_axis_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_axis_z_enabled
     */
    fun isAxisZEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisZEnabledBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the X-axis will be inverted.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_x_inverted
     */
    fun setAxisXInverted(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisXInvertedBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the invert flags has the flag for the X-axis in the setting at `index`. See
     * also `set_invert_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_axis_x_inverted
     */
    fun isAxisXInverted(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisXInvertedBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the Y-axis will be inverted.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_y_inverted
     */
    fun setAxisYInverted(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisYInvertedBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the invert flags has the flag for the Y-axis in the setting at `index`. See
     * also `set_invert_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_axis_y_inverted
     */
    fun isAxisYInverted(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisYInvertedBind, segment, index)
    }

    /**
     * If sets `enabled` to `true`, the Z-axis will be inverted.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_axis_z_inverted
     */
    fun setAxisZInverted(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAxisZInvertedBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the invert flags has the flag for the Z-axis in the setting at `index`. See
     * also `set_invert_flags`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_axis_z_inverted
     */
    fun isAxisZInverted(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAxisZInvertedBind, segment, index)
    }

    /**
     * Sets relative option in the setting at `index` to `enabled`. If sets `enabled` to `true`, the
     * extracted and applying transform is relative to the rest. If sets `enabled` to `false`, the
     * extracted transform is absolute.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_relative
     */
    fun setRelative(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setRelativeBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the relative option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_relative
     */
    fun isRelative(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRelativeBind, segment, index)
    }

    /**
     * Sets additive option in the setting at `index` to `enabled`. This mainly affects the process of
     * applying transform to the `BoneConstraint3D.set_apply_bone`. If sets `enabled` to `true`, the
     * processed transform is added to the pose of the current apply bone. If sets `enabled` to
     * `false`, the pose of the current apply bone is replaced with the processed transform. However,
     * if set `set_relative` to `true`, the transform is relative to rest.
     *
     * Generated from Godot docs: CopyTransformModifier3D.set_additive
     */
    fun setAdditive(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAdditiveBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the additive option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: CopyTransformModifier3D.is_additive
     */
    fun isAdditive(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAdditiveBind, segment, index)
    }

    @JvmInline
    value class TransformFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: TransformFlag): TransformFlag = TransformFlag(value or other.value)

        infix fun and(other: TransformFlag): TransformFlag = TransformFlag(value and other.value)

        infix fun xor(other: TransformFlag): TransformFlag = TransformFlag(value xor other.value)

        fun inv(): TransformFlag = TransformFlag(value.inv())

        operator fun contains(other: TransformFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * If set, allows to copy the position.
             *
             * Generated from Godot docs: CopyTransformModifier3D.TRANSFORM_FLAG_POSITION
             */
            val POSITION: TransformFlag get() = TransformFlag(1L)
            /**
             * If set, allows to copy the rotation.
             *
             * Generated from Godot docs: CopyTransformModifier3D.TRANSFORM_FLAG_ROTATION
             */
            val ROTATION: TransformFlag get() = TransformFlag(2L)
            /**
             * If set, allows to copy the scale.
             *
             * Generated from Godot docs: CopyTransformModifier3D.TRANSFORM_FLAG_SCALE
             */
            val SCALE: TransformFlag get() = TransformFlag(4L)
            /**
             * If set, allows to copy the position/rotation/scale.
             *
             * Generated from Godot docs: CopyTransformModifier3D.TRANSFORM_FLAG_ALL
             */
            val ALL: TransformFlag get() = TransformFlag(7L)
        }
    }

    @JvmInline
    value class AxisFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: AxisFlag): AxisFlag = AxisFlag(value or other.value)

        infix fun and(other: AxisFlag): AxisFlag = AxisFlag(value and other.value)

        infix fun xor(other: AxisFlag): AxisFlag = AxisFlag(value xor other.value)

        fun inv(): AxisFlag = AxisFlag(value.inv())

        operator fun contains(other: AxisFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * If set, allows to process the X-axis.
             *
             * Generated from Godot docs: CopyTransformModifier3D.AXIS_FLAG_X
             */
            val X: AxisFlag get() = AxisFlag(1L)
            /**
             * If set, allows to process the Y-axis.
             *
             * Generated from Godot docs: CopyTransformModifier3D.AXIS_FLAG_Y
             */
            val Y: AxisFlag get() = AxisFlag(2L)
            /**
             * If set, allows to process the Z-axis.
             *
             * Generated from Godot docs: CopyTransformModifier3D.AXIS_FLAG_Z
             */
            val Z: AxisFlag get() = AxisFlag(4L)
            /**
             * If set, allows to process the all axes.
             *
             * Generated from Godot docs: CopyTransformModifier3D.AXIS_FLAG_ALL
             */
            val ALL: AxisFlag get() = AxisFlag(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CopyTransformModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CopyTransformModifier3D? =
            if (handle.address() == 0L) null else CopyTransformModifier3D(GodotHandle(handle))

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

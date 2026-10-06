package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A `SkeletonModifier3D` that apply transform to the bone which converted from reference.
 *
 * Generated from Godot docs: ConvertTransformModifier3D
 */
class ConvertTransformModifier3D(handle: GodotHandle) : BoneConstraint3D(handle) {
    /**
     * Sets the operation of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_transform_mode
     */
    fun setApplyTransformMode(index: Int, transformMode: ConvertTransformModifier3D.TransformMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setApplyTransformModeBind, segment, index, transformMode.value)
    }

    /**
     * Returns the operation of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_transform_mode
     */
    fun getApplyTransformMode(index: Int): ConvertTransformModifier3D.TransformMode {
        return ConvertTransformModifier3D.TransformMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getApplyTransformModeBind, segment, index))
    }

    /**
     * Sets the axis of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_axis
     */
    fun setApplyAxis(index: Int, axis: Vector3.Axis) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setApplyAxisBind, segment, index, axis.value)
    }

    /**
     * Returns the axis of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_axis
     */
    fun getApplyAxis(index: Int): Vector3.Axis {
        return Vector3.Axis(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getApplyAxisBind, segment, index))
    }

    /**
     * Sets the minimum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_range_min
     */
    fun setApplyRangeMin(index: Int, rangeMin: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setApplyRangeMinBind, segment, index, rangeMin)
    }

    /**
     * Returns the minimum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_range_min
     */
    fun getApplyRangeMin(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getApplyRangeMinBind, segment, index)
    }

    /**
     * Sets the maximum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_range_max
     */
    fun setApplyRangeMax(index: Int, rangeMax: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setApplyRangeMaxBind, segment, index, rangeMax)
    }

    /**
     * Returns the maximum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_range_max
     */
    fun getApplyRangeMax(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getApplyRangeMaxBind, segment, index)
    }

    /**
     * Sets the operation of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_transform_mode
     */
    fun setReferenceTransformMode(index: Int, transformMode: ConvertTransformModifier3D.TransformMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setReferenceTransformModeBind, segment, index, transformMode.value)
    }

    /**
     * Returns the operation of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_transform_mode
     */
    fun getReferenceTransformMode(index: Int): ConvertTransformModifier3D.TransformMode {
        return ConvertTransformModifier3D.TransformMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getReferenceTransformModeBind, segment, index))
    }

    /**
     * Sets the axis of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_axis
     */
    fun setReferenceAxis(index: Int, axis: Vector3.Axis) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setReferenceAxisBind, segment, index, axis.value)
    }

    /**
     * Returns the axis of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_axis
     */
    fun getReferenceAxis(index: Int): Vector3.Axis {
        return Vector3.Axis(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getReferenceAxisBind, segment, index))
    }

    /**
     * Sets the minimum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_range_min
     */
    fun setReferenceRangeMin(index: Int, rangeMin: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setReferenceRangeMinBind, segment, index, rangeMin)
    }

    /**
     * Returns the minimum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_range_min
     */
    fun getReferenceRangeMin(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getReferenceRangeMinBind, segment, index)
    }

    /**
     * Sets the maximum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_range_max
     */
    fun setReferenceRangeMax(index: Int, rangeMax: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setReferenceRangeMaxBind, segment, index, rangeMax)
    }

    /**
     * Returns the maximum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_range_max
     */
    fun getReferenceRangeMax(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getReferenceRangeMaxBind, segment, index)
    }

    /**
     * Sets relative option in the setting at `index` to `enabled`. If sets `enabled` to `true`, the
     * extracted and applying transform is relative to the rest. If sets `enabled` to `false`, the
     * extracted transform is absolute.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_relative
     */
    fun setRelative(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setRelativeBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the relative option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.is_relative
     */
    fun isRelative(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isRelativeBind, segment, index)
    }

    /**
     * Sets additive option in the setting at `index` to `enabled`. This mainly affects the process of
     * applying transform to the `BoneConstraint3D.set_apply_bone`. If sets `enabled` to `true`, the
     * processed transform is added to the pose of the current apply bone. If sets `enabled` to
     * `false`, the pose of the current apply bone is replaced with the processed transform. However,
     * if set `set_relative` to `true`, the transform is relative to rest.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_additive
     */
    fun setAdditive(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setAdditiveBind, segment, index, enabled)
    }

    /**
     * Returns `true` if the additive option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.is_additive
     */
    fun isAdditive(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isAdditiveBind, segment, index)
    }

    /**
     * Godot's `ConvertTransformModifier3D.TransformMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ConvertTransformModifier3D.TransformMode.<NAME>`).
     *
     * Generated from Godot docs: ConvertTransformModifier3D.TransformMode
     */
    @JvmInline
    value class TransformMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Convert with position. Transfer the difference.
             *
             * Generated from Godot docs: ConvertTransformModifier3D.TRANSFORM_MODE_POSITION
             */
            val POSITION: TransformMode get() = TransformMode(0L)
            /**
             * Convert with rotation. The angle is the roll for the specified axis.
             *
             * Generated from Godot docs: ConvertTransformModifier3D.TRANSFORM_MODE_ROTATION
             */
            val ROTATION: TransformMode get() = TransformMode(1L)
            /**
             * Convert with scale. Transfers the ratio, not the difference.
             *
             * Generated from Godot docs: ConvertTransformModifier3D.TRANSFORM_MODE_SCALE
             */
            val SCALE: TransformMode get() = TransformMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ConvertTransformModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ConvertTransformModifier3D? =
            if (handle.address() == 0L) null else ConvertTransformModifier3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_APPLY_TRANSFORM_MODE_HASH = 1386463405L
        @JvmField
        val setApplyTransformModeBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_transform_mode", SET_APPLY_TRANSFORM_MODE_HASH)

        private const val GET_APPLY_TRANSFORM_MODE_HASH = 3234663511L
        @JvmField
        val getApplyTransformModeBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_transform_mode", GET_APPLY_TRANSFORM_MODE_HASH)

        private const val SET_APPLY_AXIS_HASH = 776736805L
        @JvmField
        val setApplyAxisBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_axis", SET_APPLY_AXIS_HASH)

        private const val GET_APPLY_AXIS_HASH = 4131134770L
        @JvmField
        val getApplyAxisBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_axis", GET_APPLY_AXIS_HASH)

        private const val SET_APPLY_RANGE_MIN_HASH = 1602489585L
        @JvmField
        val setApplyRangeMinBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_range_min", SET_APPLY_RANGE_MIN_HASH)

        private const val GET_APPLY_RANGE_MIN_HASH = 2339986948L
        @JvmField
        val getApplyRangeMinBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_range_min", GET_APPLY_RANGE_MIN_HASH)

        private const val SET_APPLY_RANGE_MAX_HASH = 1602489585L
        @JvmField
        val setApplyRangeMaxBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_range_max", SET_APPLY_RANGE_MAX_HASH)

        private const val GET_APPLY_RANGE_MAX_HASH = 2339986948L
        @JvmField
        val getApplyRangeMaxBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_range_max", GET_APPLY_RANGE_MAX_HASH)

        private const val SET_REFERENCE_TRANSFORM_MODE_HASH = 1386463405L
        @JvmField
        val setReferenceTransformModeBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_transform_mode", SET_REFERENCE_TRANSFORM_MODE_HASH)

        private const val GET_REFERENCE_TRANSFORM_MODE_HASH = 3234663511L
        @JvmField
        val getReferenceTransformModeBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_transform_mode", GET_REFERENCE_TRANSFORM_MODE_HASH)

        private const val SET_REFERENCE_AXIS_HASH = 776736805L
        @JvmField
        val setReferenceAxisBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_axis", SET_REFERENCE_AXIS_HASH)

        private const val GET_REFERENCE_AXIS_HASH = 4131134770L
        @JvmField
        val getReferenceAxisBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_axis", GET_REFERENCE_AXIS_HASH)

        private const val SET_REFERENCE_RANGE_MIN_HASH = 1602489585L
        @JvmField
        val setReferenceRangeMinBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_range_min", SET_REFERENCE_RANGE_MIN_HASH)

        private const val GET_REFERENCE_RANGE_MIN_HASH = 2339986948L
        @JvmField
        val getReferenceRangeMinBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_range_min", GET_REFERENCE_RANGE_MIN_HASH)

        private const val SET_REFERENCE_RANGE_MAX_HASH = 1602489585L
        @JvmField
        val setReferenceRangeMaxBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_range_max", SET_REFERENCE_RANGE_MAX_HASH)

        private const val GET_REFERENCE_RANGE_MAX_HASH = 2339986948L
        @JvmField
        val getReferenceRangeMaxBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_range_max", GET_REFERENCE_RANGE_MAX_HASH)

        private const val SET_RELATIVE_HASH = 300928843L
        @JvmField
        val setRelativeBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_relative", SET_RELATIVE_HASH)

        private const val IS_RELATIVE_HASH = 1116898809L
        @JvmField
        val isRelativeBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "is_relative", IS_RELATIVE_HASH)

        private const val SET_ADDITIVE_HASH = 300928843L
        @JvmField
        val setAdditiveBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_additive", SET_ADDITIVE_HASH)

        private const val IS_ADDITIVE_HASH = 1116898809L
        @JvmField
        val isAdditiveBind =
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "is_additive", IS_ADDITIVE_HASH)
    }
}

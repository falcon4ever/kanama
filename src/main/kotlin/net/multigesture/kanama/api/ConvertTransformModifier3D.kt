package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A `SkeletonModifier3D` that apply transform to the bone which converted from reference.
 *
 * Generated from Godot docs: ConvertTransformModifier3D
 */
class ConvertTransformModifier3D(handle: MemorySegment) : BoneConstraint3D(handle) {
    /**
     * Sets the operation of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_transform_mode
     */
    fun setApplyTransformMode(index: Int, transformMode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setApplyTransformModeBind, handle, index, transformMode)
    }

    /**
     * Returns the operation of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_transform_mode
     */
    fun getApplyTransformMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getApplyTransformModeBind, handle, index)
    }

    /**
     * Sets the axis of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_axis
     */
    fun setApplyAxis(index: Int, axis: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setApplyAxisBind, handle, index, axis)
    }

    /**
     * Returns the axis of the remapping destination transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_axis
     */
    fun getApplyAxis(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getApplyAxisBind, handle, index)
    }

    /**
     * Sets the minimum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_range_min
     */
    fun setApplyRangeMin(index: Int, rangeMin: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setApplyRangeMinBind, handle, index, rangeMin)
    }

    /**
     * Returns the minimum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_range_min
     */
    fun getApplyRangeMin(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getApplyRangeMinBind, handle, index)
    }

    /**
     * Sets the maximum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_apply_range_max
     */
    fun setApplyRangeMax(index: Int, rangeMax: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setApplyRangeMaxBind, handle, index, rangeMax)
    }

    /**
     * Returns the maximum value of the remapping destination range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_apply_range_max
     */
    fun getApplyRangeMax(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getApplyRangeMaxBind, handle, index)
    }

    /**
     * Sets the operation of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_transform_mode
     */
    fun setReferenceTransformMode(index: Int, transformMode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setReferenceTransformModeBind, handle, index, transformMode)
    }

    /**
     * Returns the operation of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_transform_mode
     */
    fun getReferenceTransformMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getReferenceTransformModeBind, handle, index)
    }

    /**
     * Sets the axis of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_axis
     */
    fun setReferenceAxis(index: Int, axis: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setReferenceAxisBind, handle, index, axis)
    }

    /**
     * Returns the axis of the remapping source transform.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_axis
     */
    fun getReferenceAxis(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getReferenceAxisBind, handle, index)
    }

    /**
     * Sets the minimum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_range_min
     */
    fun setReferenceRangeMin(index: Int, rangeMin: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setReferenceRangeMinBind, handle, index, rangeMin)
    }

    /**
     * Returns the minimum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_range_min
     */
    fun getReferenceRangeMin(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getReferenceRangeMinBind, handle, index)
    }

    /**
     * Sets the maximum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_reference_range_max
     */
    fun setReferenceRangeMax(index: Int, rangeMax: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setReferenceRangeMaxBind, handle, index, rangeMax)
    }

    /**
     * Returns the maximum value of the remapping source range.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.get_reference_range_max
     */
    fun getReferenceRangeMax(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getReferenceRangeMaxBind, handle, index)
    }

    /**
     * Sets relative option in the setting at `index` to `enabled`. If sets `enabled` to `true`, the
     * extracted and applying transform is relative to the rest. If sets `enabled` to `false`, the
     * extracted transform is absolute.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.set_relative
     */
    fun setRelative(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setRelativeBind, handle, index, enabled)
    }

    /**
     * Returns `true` if the relative option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.is_relative
     */
    fun isRelative(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isRelativeBind, handle, index)
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
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAdditiveBind, handle, index, enabled)
    }

    /**
     * Returns `true` if the additive option is enabled in the setting at `index`.
     *
     * Generated from Godot docs: ConvertTransformModifier3D.is_additive
     */
    fun isAdditive(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isAdditiveBind, handle, index)
    }

    companion object {
        const val TRANSFORM_MODE_POSITION: Long = 0L
        const val TRANSFORM_MODE_ROTATION: Long = 1L
        const val TRANSFORM_MODE_SCALE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConvertTransformModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvertTransformModifier3D? =
            if (handle.address() == 0L) null else ConvertTransformModifier3D(handle)

        private const val SET_APPLY_TRANSFORM_MODE_HASH = 1386463405L
        private val setApplyTransformModeBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_transform_mode", SET_APPLY_TRANSFORM_MODE_HASH)
        }

        private const val GET_APPLY_TRANSFORM_MODE_HASH = 3234663511L
        private val getApplyTransformModeBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_transform_mode", GET_APPLY_TRANSFORM_MODE_HASH)
        }

        private const val SET_APPLY_AXIS_HASH = 776736805L
        private val setApplyAxisBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_axis", SET_APPLY_AXIS_HASH)
        }

        private const val GET_APPLY_AXIS_HASH = 4131134770L
        private val getApplyAxisBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_axis", GET_APPLY_AXIS_HASH)
        }

        private const val SET_APPLY_RANGE_MIN_HASH = 1602489585L
        private val setApplyRangeMinBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_range_min", SET_APPLY_RANGE_MIN_HASH)
        }

        private const val GET_APPLY_RANGE_MIN_HASH = 2339986948L
        private val getApplyRangeMinBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_range_min", GET_APPLY_RANGE_MIN_HASH)
        }

        private const val SET_APPLY_RANGE_MAX_HASH = 1602489585L
        private val setApplyRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_apply_range_max", SET_APPLY_RANGE_MAX_HASH)
        }

        private const val GET_APPLY_RANGE_MAX_HASH = 2339986948L
        private val getApplyRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_apply_range_max", GET_APPLY_RANGE_MAX_HASH)
        }

        private const val SET_REFERENCE_TRANSFORM_MODE_HASH = 1386463405L
        private val setReferenceTransformModeBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_transform_mode", SET_REFERENCE_TRANSFORM_MODE_HASH)
        }

        private const val GET_REFERENCE_TRANSFORM_MODE_HASH = 3234663511L
        private val getReferenceTransformModeBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_transform_mode", GET_REFERENCE_TRANSFORM_MODE_HASH)
        }

        private const val SET_REFERENCE_AXIS_HASH = 776736805L
        private val setReferenceAxisBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_axis", SET_REFERENCE_AXIS_HASH)
        }

        private const val GET_REFERENCE_AXIS_HASH = 4131134770L
        private val getReferenceAxisBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_axis", GET_REFERENCE_AXIS_HASH)
        }

        private const val SET_REFERENCE_RANGE_MIN_HASH = 1602489585L
        private val setReferenceRangeMinBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_range_min", SET_REFERENCE_RANGE_MIN_HASH)
        }

        private const val GET_REFERENCE_RANGE_MIN_HASH = 2339986948L
        private val getReferenceRangeMinBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_range_min", GET_REFERENCE_RANGE_MIN_HASH)
        }

        private const val SET_REFERENCE_RANGE_MAX_HASH = 1602489585L
        private val setReferenceRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_reference_range_max", SET_REFERENCE_RANGE_MAX_HASH)
        }

        private const val GET_REFERENCE_RANGE_MAX_HASH = 2339986948L
        private val getReferenceRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "get_reference_range_max", GET_REFERENCE_RANGE_MAX_HASH)
        }

        private const val SET_RELATIVE_HASH = 300928843L
        private val setRelativeBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_relative", SET_RELATIVE_HASH)
        }

        private const val IS_RELATIVE_HASH = 1116898809L
        private val isRelativeBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "is_relative", IS_RELATIVE_HASH)
        }

        private const val SET_ADDITIVE_HASH = 300928843L
        private val setAdditiveBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "set_additive", SET_ADDITIVE_HASH)
        }

        private const val IS_ADDITIVE_HASH = 1116898809L
        private val isAdditiveBind by lazy {
            ObjectCalls.getMethodBind("ConvertTransformModifier3D", "is_additive", IS_ADDITIVE_HASH)
        }
    }
}

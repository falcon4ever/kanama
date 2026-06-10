package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualInstance3D
 */
open class VisualInstance3D(handle: MemorySegment) : Node3D(handle) {
    var layers: Long
        @JvmName("layersProperty")
        get() = getLayerMask()
        @JvmName("setLayersProperty")
        set(value) = setLayerMask(value)

    var sortingOffset: Double
        @JvmName("sortingOffsetProperty")
        get() = getSortingOffset()
        @JvmName("setSortingOffsetProperty")
        set(value) = setSortingOffset(value)

    var sortingUseAabbCenter: Boolean
        @JvmName("sortingUseAabbCenterProperty")
        get() = isSortingUseAabbCenter()
        @JvmName("setSortingUseAabbCenterProperty")
        set(value) = setSortingUseAabbCenter(value)

    fun setLayerMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setLayerMaskBind, handle, mask)
    }

    fun getLayerMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getLayerMaskBind, handle)
    }

    fun setLayerMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerMaskValueBind, handle, layerNumber, value)
    }

    fun getLayerMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getLayerMaskValueBind, handle, layerNumber)
    }

    fun setSortingOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSortingOffsetBind, handle, offset)
    }

    fun getSortingOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSortingOffsetBind, handle)
    }

    fun setSortingUseAabbCenter(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSortingUseAabbCenterBind, handle, enabled)
    }

    fun isSortingUseAabbCenter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSortingUseAabbCenterBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): VisualInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualInstance3D? =
            if (handle.address() == 0L) null else VisualInstance3D(handle)

        private const val SET_LAYER_MASK_HASH = 1286410249L
        private val setLayerMaskBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_layer_mask", SET_LAYER_MASK_HASH)
        }

        private const val GET_LAYER_MASK_HASH = 3905245786L
        private val getLayerMaskBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_layer_mask", GET_LAYER_MASK_HASH)
        }

        private const val SET_LAYER_MASK_VALUE_HASH = 300928843L
        private val setLayerMaskValueBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_layer_mask_value", SET_LAYER_MASK_VALUE_HASH)
        }

        private const val GET_LAYER_MASK_VALUE_HASH = 1116898809L
        private val getLayerMaskValueBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_layer_mask_value", GET_LAYER_MASK_VALUE_HASH)
        }

        private const val SET_SORTING_OFFSET_HASH = 373806689L
        private val setSortingOffsetBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_sorting_offset", SET_SORTING_OFFSET_HASH)
        }

        private const val GET_SORTING_OFFSET_HASH = 1740695150L
        private val getSortingOffsetBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_sorting_offset", GET_SORTING_OFFSET_HASH)
        }

        private const val SET_SORTING_USE_AABB_CENTER_HASH = 2586408642L
        private val setSortingUseAabbCenterBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_sorting_use_aabb_center", SET_SORTING_USE_AABB_CENTER_HASH)
        }

        private const val IS_SORTING_USE_AABB_CENTER_HASH = 36873697L
        private val isSortingUseAabbCenterBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "is_sorting_use_aabb_center", IS_SORTING_USE_AABB_CENTER_HASH)
        }
    }
}

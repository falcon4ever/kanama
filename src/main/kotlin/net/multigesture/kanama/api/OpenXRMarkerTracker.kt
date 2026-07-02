package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRMarkerTracker
 */
class OpenXRMarkerTracker(handle: MemorySegment) : OpenXRSpatialEntityTracker(handle) {
    var boundsSize: Vector2
        @JvmName("boundsSizeProperty")
        get() = getBoundsSize()
        @JvmName("setBoundsSizeProperty")
        set(value) = setBoundsSize(value)

    var markerType: Long
        @JvmName("markerTypeProperty")
        get() = getMarkerType()
        @JvmName("setMarkerTypeProperty")
        set(value) = setMarkerType(value)

    var markerId: Long
        @JvmName("markerIdProperty")
        get() = getMarkerId()
        @JvmName("setMarkerIdProperty")
        set(value) = setMarkerId(value)

    fun setBoundsSize(boundsSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setBoundsSizeBind, handle, boundsSize)
    }

    fun getBoundsSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getBoundsSizeBind, handle)
    }

    fun setMarkerType(markerType: Long) {
        ObjectCalls.ptrcallWithLongArg(setMarkerTypeBind, handle, markerType)
    }

    fun getMarkerType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMarkerTypeBind, handle)
    }

    fun setMarkerId(markerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setMarkerIdBind, handle, markerId)
    }

    fun getMarkerId(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getMarkerIdBind, handle)
    }

    fun setMarkerData(markerData: Any?) {
        ObjectCalls.ptrcallWithVariantArg(setMarkerDataBind, handle, markerData)
    }

    fun getMarkerData(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getMarkerDataBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRMarkerTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRMarkerTracker? =
            if (handle.address() == 0L) null else OpenXRMarkerTracker(handle)

        private const val SET_BOUNDS_SIZE_HASH = 743155724L
        private val setBoundsSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_bounds_size", SET_BOUNDS_SIZE_HASH)
        }

        private const val GET_BOUNDS_SIZE_HASH = 3341600327L
        private val getBoundsSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_bounds_size", GET_BOUNDS_SIZE_HASH)
        }

        private const val SET_MARKER_TYPE_HASH = 2156241362L
        private val setMarkerTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_type", SET_MARKER_TYPE_HASH)
        }

        private const val GET_MARKER_TYPE_HASH = 612702862L
        private val getMarkerTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_marker_type", GET_MARKER_TYPE_HASH)
        }

        private const val SET_MARKER_ID_HASH = 1286410249L
        private val setMarkerIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_id", SET_MARKER_ID_HASH)
        }

        private const val GET_MARKER_ID_HASH = 3905245786L
        private val getMarkerIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_marker_id", GET_MARKER_ID_HASH)
        }

        private const val SET_MARKER_DATA_HASH = 1114965689L
        private val setMarkerDataBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_data", SET_MARKER_DATA_HASH)
        }

        private const val GET_MARKER_DATA_HASH = 1214101251L
        private val getMarkerDataBind by lazy {
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_marker_data", GET_MARKER_DATA_HASH)
        }
    }
}

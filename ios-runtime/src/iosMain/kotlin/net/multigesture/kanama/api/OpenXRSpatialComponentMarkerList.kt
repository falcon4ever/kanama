package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialComponentMarkerList
 */
class OpenXRSpatialComponentMarkerList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getMarkerType(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getMarkerTypeBind, handle, index)
    }

    fun getMarkerId(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetUInt32(getMarkerIdBind, handle, index)
    }

    companion object {
        const val MARKER_TYPE_UNKNOWN: Long = 0L
        const val MARKER_TYPE_QRCODE: Long = 1L
        const val MARKER_TYPE_MICRO_QRCODE: Long = 2L
        const val MARKER_TYPE_ARUCO: Long = 3L
        const val MARKER_TYPE_APRIL_TAG: Long = 4L
        const val MARKER_TYPE_MAX: Long = 5L

        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentMarkerList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentMarkerList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentMarkerList(handle)

        private const val GET_MARKER_TYPE_HASH = 2627847866L
        private val getMarkerTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_type", GET_MARKER_TYPE_HASH)
        }

        private const val GET_MARKER_ID_HASH = 923996154L
        private val getMarkerIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_id", GET_MARKER_ID_HASH)
        }
    }
}

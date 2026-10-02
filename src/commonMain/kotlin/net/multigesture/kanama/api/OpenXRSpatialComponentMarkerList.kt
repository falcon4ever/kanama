package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialComponentMarkerList
 */
class OpenXRSpatialComponentMarkerList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getMarkerType(index: Long): OpenXRSpatialComponentMarkerList.MarkerType {
        checkOpen()
        return OpenXRSpatialComponentMarkerList.MarkerType(ObjectCalls.ptrcallWithLongArgRetLong(getMarkerTypeBind, segment, index))
    }

    fun getMarkerId(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetUInt32(getMarkerIdBind, segment, index)
    }

    fun getMarkerData(snapshot: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(getMarkerDataBind, segment, snapshot, index)
    }

    @JvmInline
    value class MarkerType(override val value: Long) : GodotEnumValue {
        companion object {
            val UNKNOWN: MarkerType get() = MarkerType(0L)
            val QRCODE: MarkerType get() = MarkerType(1L)
            val MICRO_QRCODE: MarkerType get() = MarkerType(2L)
            val ARUCO: MarkerType get() = MarkerType(3L)
            val APRIL_TAG: MarkerType get() = MarkerType(4L)
            val MAX: MarkerType get() = MarkerType(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentMarkerList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentMarkerList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentMarkerList(GodotHandle(handle))

        private const val GET_MARKER_TYPE_HASH = 2627847866L
        private val getMarkerTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_type", GET_MARKER_TYPE_HASH)
        }

        private const val GET_MARKER_ID_HASH = 923996154L
        private val getMarkerIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_id", GET_MARKER_ID_HASH)
        }

        private const val GET_MARKER_DATA_HASH = 4069510997L
        private val getMarkerDataBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_data", GET_MARKER_DATA_HASH)
        }
    }
}

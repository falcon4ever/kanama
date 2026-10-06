package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        return OpenXRSpatialComponentMarkerList.MarkerType(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getMarkerTypeBind, segment, index))
    }

    fun getMarkerId(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetUInt32(Binds.getMarkerIdBind, segment, index)
    }

    fun getMarkerData(snapshot: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(Binds.getMarkerDataBind, segment, snapshot, index)
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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentMarkerList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentMarkerList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentMarkerList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentMarkerList(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_MARKER_TYPE_HASH = 2627847866L
        @JvmField
        val getMarkerTypeBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_type", GET_MARKER_TYPE_HASH)

        private const val GET_MARKER_ID_HASH = 923996154L
        @JvmField
        val getMarkerIdBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_id", GET_MARKER_ID_HASH)

        private const val GET_MARKER_DATA_HASH = 4069510997L
        @JvmField
        val getMarkerDataBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_data", GET_MARKER_DATA_HASH)
    }
}

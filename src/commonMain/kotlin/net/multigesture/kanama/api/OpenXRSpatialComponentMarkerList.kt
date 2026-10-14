package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Object for storing the queries marker result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentMarkerList
 */
class OpenXRSpatialComponentMarkerList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the marker type for the marker at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentMarkerList.get_marker_type
     */
    fun getMarkerType(index: Long): OpenXRSpatialComponentMarkerList.MarkerType {
        checkOpen()
        return OpenXRSpatialComponentMarkerList.MarkerType(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getMarkerTypeBind, segment, index))
    }

    /**
     * Returns the marker ID for the marker at this `index`. Only applicable for Aruco or April Tag
     * markers.
     *
     * Generated from Godot docs: OpenXRSpatialComponentMarkerList.get_marker_id
     */
    fun getMarkerId(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetUInt32(Binds.getMarkerIdBind, segment, index)
    }

    /**
     * Returns either a `String` or a `PackedByteArray` buffer with data for the marker at this
     * `index`. Only applicable for QR code markers.
     *
     * Generated from Godot docs: OpenXRSpatialComponentMarkerList.get_marker_data
     */
    fun getMarkerData(snapshot: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(Binds.getMarkerDataBind, segment, snapshot, index)
    }

    /**
     * Godot's `OpenXRSpatialComponentMarkerList.MarkerType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialComponentMarkerList.MarkerType.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MarkerType
     */
    @JvmInline
    value class MarkerType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Unknown or unset marker type.
             *
             * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MARKER_TYPE_UNKNOWN
             */
            val UNKNOWN: MarkerType get() = MarkerType(0L)
            /**
             * Marker based on a QR code.
             *
             * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MARKER_TYPE_QRCODE
             */
            val QRCODE: MarkerType get() = MarkerType(1L)
            /**
             * Marker based on a micro QR code.
             *
             * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MARKER_TYPE_MICRO_QRCODE
             */
            val MICRO_QRCODE: MarkerType get() = MarkerType(2L)
            /**
             * Marker based on an Aruco code.
             *
             * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MARKER_TYPE_ARUCO
             */
            val ARUCO: MarkerType get() = MarkerType(3L)
            /**
             * Marker based on an April Tag.
             *
             * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MARKER_TYPE_APRIL_TAG
             */
            val APRIL_TAG: MarkerType get() = MarkerType(4L)
            /**
             * Maximum value for this enum.
             *
             * Generated from Godot docs: OpenXRSpatialComponentMarkerList.MARKER_TYPE_MAX
             */
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

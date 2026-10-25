package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Spatial entity tracker for our spatial entity marker tracking extension.
 *
 * Generated from Godot docs: OpenXRMarkerTracker
 */
class OpenXRMarkerTracker(handle: GodotHandle) : OpenXRSpatialEntityTracker(handle) {
    var boundsSize: Vector2
        @JvmName("boundsSizeProperty")
        get() = getBoundsSize()
        @JvmName("setBoundsSizeProperty")
        set(value) = setBoundsSize(value)

    var markerType: OpenXRSpatialComponentMarkerList.MarkerType
        @JvmName("markerTypeProperty")
        get() = getMarkerType()
        @JvmName("setMarkerTypeProperty")
        set(value) = setMarkerType(value)

    var markerId: Long
        @JvmName("markerIdProperty")
        get() = getMarkerId()
        @JvmName("setMarkerIdProperty")
        set(value) = setMarkerId(value)

    /**
     * The bounds size for this marker.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.set_bounds_size
     */
    fun setBoundsSize(boundsSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setBoundsSizeBind, segment, boundsSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The bounds size for this marker.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.get_bounds_size
     */
    fun getBoundsSize(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getBoundsSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of marker.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.set_marker_type
     */
    fun setMarkerType(markerType: OpenXRSpatialComponentMarkerList.MarkerType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setMarkerTypeBind, segment, markerType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type of marker.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.get_marker_type
     */
    fun getMarkerType(): OpenXRSpatialComponentMarkerList.MarkerType {
        checkOpen()
        val ret = OpenXRSpatialComponentMarkerList.MarkerType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMarkerTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The marker ID for this marker, this is only returned for Aruco and April Tag markers. Call
     * `get_marker_data` for QRCode markers.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.set_marker_id
     */
    fun setMarkerId(markerId: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setMarkerIdBind, segment, markerId)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The marker ID for this marker, this is only returned for Aruco and April Tag markers. Call
     * `get_marker_data` for QRCode markers.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.get_marker_id
     */
    fun getMarkerId(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getMarkerIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the marker data for this marker. Note: This should only be set by marker discovery logic.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.set_marker_data
     */
    fun setMarkerData(markerData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.setMarkerDataBind, segment, markerData)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the marker data for this marker. This can return a `String` or `PackedByteArray`. Only
     * applicable to QR Code based markers.
     *
     * Generated from Godot docs: OpenXRMarkerTracker.get_marker_data
     */
    fun getMarkerData(): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getMarkerDataBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRMarkerTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRMarkerTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRMarkerTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRMarkerTracker? =
            if (handle.address() == 0L) null else OpenXRMarkerTracker(GodotHandle(handle))

        /** A new OpenXRMarkerTracker, GDScript's `OpenXRMarkerTracker.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRMarkerTracker =
            RefCounted.owned(OpenXRMarkerTracker(GodotHandle(ObjectCalls.constructObject("OpenXRMarkerTracker"))))
    }

    private object Binds {
        private const val SET_BOUNDS_SIZE_HASH = 743155724L
        @JvmField
        val setBoundsSizeBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_bounds_size", SET_BOUNDS_SIZE_HASH)

        private const val GET_BOUNDS_SIZE_HASH = 3341600327L
        @JvmField
        val getBoundsSizeBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_bounds_size", GET_BOUNDS_SIZE_HASH)

        private const val SET_MARKER_TYPE_HASH = 2156241362L
        @JvmField
        val setMarkerTypeBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_type", SET_MARKER_TYPE_HASH)

        private const val GET_MARKER_TYPE_HASH = 612702862L
        @JvmField
        val getMarkerTypeBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_marker_type", GET_MARKER_TYPE_HASH)

        private const val SET_MARKER_ID_HASH = 1286410249L
        @JvmField
        val setMarkerIdBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_id", SET_MARKER_ID_HASH)

        private const val GET_MARKER_ID_HASH = 3905245786L
        @JvmField
        val getMarkerIdBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_marker_id", GET_MARKER_ID_HASH)

        private const val SET_MARKER_DATA_HASH = 1114965689L
        @JvmField
        val setMarkerDataBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_data", SET_MARKER_DATA_HASH)

        private const val GET_MARKER_DATA_HASH = 1214101251L
        @JvmField
        val getMarkerDataBind =
            ObjectCalls.getMethodBind("OpenXRMarkerTracker", "get_marker_data", GET_MARKER_DATA_HASH)
    }
}

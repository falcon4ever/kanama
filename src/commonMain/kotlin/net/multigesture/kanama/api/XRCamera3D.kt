package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A camera node which automatically positions itself based on XR tracking data.
 *
 * Generated from Godot docs: XRCamera3D
 */
class XRCamera3D(handle: GodotHandle) : Camera3D(handle) {
    var tracker: String
        @JvmName("trackerProperty")
        get() = getTracker()
        @JvmName("setTrackerProperty")
        set(value) = setTracker(value)

    /**
     * The name of the camera tracker we're bound to. Which trackers are available is not known during
     * design time. The default tracker refers to the HMD position of the main player. Consult the
     * documentation of the `XRInterface` for any additional trackers. There may be additional tracked
     * headsets for multiplayer systems or a tracker may be available for a physical camera in a mixed
     * reality scenario.
     *
     * Generated from Godot docs: XRCamera3D.set_tracker
     */
    fun setTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setTrackerBind, segment, trackerName)
    }

    /**
     * The name of the camera tracker we're bound to. Which trackers are available is not known during
     * design time. The default tracker refers to the HMD position of the main player. Consult the
     * documentation of the `XRInterface` for any additional trackers. There may be additional tracked
     * headsets for multiplayer systems or a tracker may be available for a physical camera in a mixed
     * reality scenario.
     *
     * Generated from Godot docs: XRCamera3D.get_tracker
     */
    fun getTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getTrackerBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRCamera3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRCamera3D? =
            if (handle.address() == 0L) null else XRCamera3D(GodotHandle(handle))

        /** A new XRCamera3D, GDScript's `XRCamera3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): XRCamera3D =
            XRCamera3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("XRCamera3D"), "XRCamera3D")))
    }

    private object Binds {
        private const val SET_TRACKER_HASH = 3304788590L
        @JvmField
        val setTrackerBind =
            ObjectCalls.getMethodBind("XRCamera3D", "set_tracker", SET_TRACKER_HASH)

        private const val GET_TRACKER_HASH = 2002593661L
        @JvmField
        val getTrackerBind =
            ObjectCalls.getMethodBind("XRCamera3D", "get_tracker", GET_TRACKER_HASH)
    }
}

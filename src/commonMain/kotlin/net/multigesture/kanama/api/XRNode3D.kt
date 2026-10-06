package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 3D node that has its position automatically updated by the `XRServer`.
 *
 * Generated from Godot docs: XRNode3D
 */
open class XRNode3D(handle: GodotHandle) : Node3D(handle) {
    var tracker: String
        @JvmName("trackerProperty")
        get() = getTracker()
        @JvmName("setTrackerProperty")
        set(value) = setTracker(value)

    var pose: String
        @JvmName("poseProperty")
        get() = getPoseName()
        @JvmName("setPoseProperty")
        set(value) = setPoseName(value)

    var showWhenTracked: Boolean
        @JvmName("showWhenTrackedProperty")
        get() = getShowWhenTracked()
        @JvmName("setShowWhenTrackedProperty")
        set(value) = setShowWhenTracked(value)

    /**
     * The name of the tracker we're bound to. Which trackers are available is not known during design
     * time. Godot defines a number of standard trackers such as `left_hand` and `right_hand` but
     * others may be configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.set_tracker
     */
    fun setTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setTrackerBind, segment, trackerName)
    }

    /**
     * The name of the tracker we're bound to. Which trackers are available is not known during design
     * time. Godot defines a number of standard trackers such as `left_hand` and `right_hand` but
     * others may be configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.get_tracker
     */
    fun getTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getTrackerBind, segment)
    }

    /**
     * The name of the pose we're bound to. Which poses a tracker supports is not known during design
     * time. Godot defines number of standard pose names such as `aim` and `grip` but other may be
     * configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.set_pose_name
     */
    fun setPoseName(pose: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setPoseNameBind, segment, pose)
    }

    /**
     * The name of the pose we're bound to. Which poses a tracker supports is not known during design
     * time. Godot defines number of standard pose names such as `aim` and `grip` but other may be
     * configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.get_pose_name
     */
    fun getPoseName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getPoseNameBind, segment)
    }

    /**
     * Enables showing the node when tracking starts, and hiding the node when tracking is lost.
     *
     * Generated from Godot docs: XRNode3D.set_show_when_tracked
     */
    fun setShowWhenTracked(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowWhenTrackedBind, segment, show)
    }

    /**
     * Enables showing the node when tracking starts, and hiding the node when tracking is lost.
     *
     * Generated from Godot docs: XRNode3D.get_show_when_tracked
     */
    fun getShowWhenTracked(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getShowWhenTrackedBind, segment)
    }

    /**
     * Returns `true` if the `tracker` has been registered and the `pose` is being tracked.
     *
     * Generated from Godot docs: XRNode3D.get_is_active
     */
    fun getIsActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getIsActiveBind, segment)
    }

    /**
     * Returns `true` if the `tracker` has current tracking data for the `pose` being tracked.
     *
     * Generated from Godot docs: XRNode3D.get_has_tracking_data
     */
    fun getHasTrackingData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getHasTrackingDataBind, segment)
    }

    /**
     * Returns the `XRPose` containing the current state of the pose being tracked. This gives access
     * to additional properties of this pose.
     *
     * Generated from Godot docs: XRNode3D.get_pose
     */
    fun getPose(): XRPose? {
        return XRPose.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPoseBind, segment))
    }

    /**
     * Triggers a haptic pulse on a device associated with this interface. `action_name` is the name of
     * the action for this pulse. `frequency` is the frequency of the pulse, set to `0.0` to have the
     * system use a default frequency. `amplitude` is the amplitude of the pulse between `0.0` and
     * `1.0`. `duration_sec` is the duration of the pulse in seconds. `delay_sec` is a delay in seconds
     * before the pulse is given.
     *
     * Generated from Godot docs: XRNode3D.trigger_haptic_pulse
     */
    fun triggerHapticPulse(actionName: String, frequency: Double, amplitude: Double, durationSec: Double, delaySec: Double) {
        ObjectCalls.ptrcallWithStringFourDoubleArgs(Binds.triggerHapticPulseBind, segment, actionName, frequency, amplitude, durationSec, delaySec)
    }

    /** Signal `tracking_changed(tracking: bool)`; see [TypedSignal]. */
    val trackingChanged: Signal1<Boolean>
        @JvmName("trackingChangedTypedSignal")
        get() = Signal1(this, "tracking_changed", SignalArgType.BOOLEAN)

    object Signals {
        const val trackingChanged: String = "tracking_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRNode3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRNode3D? =
            if (handle.address() == 0L) null else XRNode3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TRACKER_HASH = 3304788590L
        @JvmField
        val setTrackerBind =
            ObjectCalls.getMethodBind("XRNode3D", "set_tracker", SET_TRACKER_HASH)

        private const val GET_TRACKER_HASH = 2002593661L
        @JvmField
        val getTrackerBind =
            ObjectCalls.getMethodBind("XRNode3D", "get_tracker", GET_TRACKER_HASH)

        private const val SET_POSE_NAME_HASH = 3304788590L
        @JvmField
        val setPoseNameBind =
            ObjectCalls.getMethodBind("XRNode3D", "set_pose_name", SET_POSE_NAME_HASH)

        private const val GET_POSE_NAME_HASH = 2002593661L
        @JvmField
        val getPoseNameBind =
            ObjectCalls.getMethodBind("XRNode3D", "get_pose_name", GET_POSE_NAME_HASH)

        private const val SET_SHOW_WHEN_TRACKED_HASH = 2586408642L
        @JvmField
        val setShowWhenTrackedBind =
            ObjectCalls.getMethodBind("XRNode3D", "set_show_when_tracked", SET_SHOW_WHEN_TRACKED_HASH)

        private const val GET_SHOW_WHEN_TRACKED_HASH = 36873697L
        @JvmField
        val getShowWhenTrackedBind =
            ObjectCalls.getMethodBind("XRNode3D", "get_show_when_tracked", GET_SHOW_WHEN_TRACKED_HASH)

        private const val GET_IS_ACTIVE_HASH = 36873697L
        @JvmField
        val getIsActiveBind =
            ObjectCalls.getMethodBind("XRNode3D", "get_is_active", GET_IS_ACTIVE_HASH)

        private const val GET_HAS_TRACKING_DATA_HASH = 36873697L
        @JvmField
        val getHasTrackingDataBind =
            ObjectCalls.getMethodBind("XRNode3D", "get_has_tracking_data", GET_HAS_TRACKING_DATA_HASH)

        private const val GET_POSE_HASH = 2806551826L
        @JvmField
        val getPoseBind =
            ObjectCalls.getMethodBind("XRNode3D", "get_pose", GET_POSE_HASH)

        private const val TRIGGER_HAPTIC_PULSE_HASH = 508576839L
        @JvmField
        val triggerHapticPulseBind =
            ObjectCalls.getMethodBind("XRNode3D", "trigger_haptic_pulse", TRIGGER_HAPTIC_PULSE_HASH)
    }
}

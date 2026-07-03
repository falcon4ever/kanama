package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 3D node that has its position automatically updated by the `XRServer`.
 *
 * Generated from Godot docs: XRNode3D
 */
open class XRNode3D(handle: MemorySegment) : Node3D(handle) {
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
        ObjectCalls.ptrcallWithStringNameArg(setTrackerBind, handle, trackerName)
    }

    /**
     * The name of the tracker we're bound to. Which trackers are available is not known during design
     * time. Godot defines a number of standard trackers such as `left_hand` and `right_hand` but
     * others may be configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.get_tracker
     */
    fun getTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getTrackerBind, handle)
    }

    /**
     * The name of the pose we're bound to. Which poses a tracker supports is not known during design
     * time. Godot defines number of standard pose names such as `aim` and `grip` but other may be
     * configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.set_pose_name
     */
    fun setPoseName(pose: String) {
        ObjectCalls.ptrcallWithStringNameArg(setPoseNameBind, handle, pose)
    }

    /**
     * The name of the pose we're bound to. Which poses a tracker supports is not known during design
     * time. Godot defines number of standard pose names such as `aim` and `grip` but other may be
     * configured within a given `XRInterface`.
     *
     * Generated from Godot docs: XRNode3D.get_pose_name
     */
    fun getPoseName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getPoseNameBind, handle)
    }

    /**
     * Enables showing the node when tracking starts, and hiding the node when tracking is lost.
     *
     * Generated from Godot docs: XRNode3D.set_show_when_tracked
     */
    fun setShowWhenTracked(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowWhenTrackedBind, handle, show)
    }

    /**
     * Enables showing the node when tracking starts, and hiding the node when tracking is lost.
     *
     * Generated from Godot docs: XRNode3D.get_show_when_tracked
     */
    fun getShowWhenTracked(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShowWhenTrackedBind, handle)
    }

    /**
     * Returns `true` if the `tracker` has been registered and the `pose` is being tracked.
     *
     * Generated from Godot docs: XRNode3D.get_is_active
     */
    fun getIsActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsActiveBind, handle)
    }

    /**
     * Returns `true` if the `tracker` has current tracking data for the `pose` being tracked.
     *
     * Generated from Godot docs: XRNode3D.get_has_tracking_data
     */
    fun getHasTrackingData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getHasTrackingDataBind, handle)
    }

    /**
     * Returns the `XRPose` containing the current state of the pose being tracked. This gives access
     * to additional properties of this pose.
     *
     * Generated from Godot docs: XRNode3D.get_pose
     */
    fun getPose(): XRPose? {
        return XRPose.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPoseBind, handle))
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
        ObjectCalls.ptrcallWithStringFourDoubleArgs(triggerHapticPulseBind, handle, actionName, frequency, amplitude, durationSec, delaySec)
    }

    object Signals {
        const val trackingChanged: String = "tracking_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRNode3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRNode3D? =
            if (handle.address() == 0L) null else XRNode3D(handle)

        private const val SET_TRACKER_HASH = 3304788590L
        private val setTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "set_tracker", SET_TRACKER_HASH)
        }

        private const val GET_TRACKER_HASH = 2002593661L
        private val getTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "get_tracker", GET_TRACKER_HASH)
        }

        private const val SET_POSE_NAME_HASH = 3304788590L
        private val setPoseNameBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "set_pose_name", SET_POSE_NAME_HASH)
        }

        private const val GET_POSE_NAME_HASH = 2002593661L
        private val getPoseNameBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "get_pose_name", GET_POSE_NAME_HASH)
        }

        private const val SET_SHOW_WHEN_TRACKED_HASH = 2586408642L
        private val setShowWhenTrackedBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "set_show_when_tracked", SET_SHOW_WHEN_TRACKED_HASH)
        }

        private const val GET_SHOW_WHEN_TRACKED_HASH = 36873697L
        private val getShowWhenTrackedBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "get_show_when_tracked", GET_SHOW_WHEN_TRACKED_HASH)
        }

        private const val GET_IS_ACTIVE_HASH = 36873697L
        private val getIsActiveBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "get_is_active", GET_IS_ACTIVE_HASH)
        }

        private const val GET_HAS_TRACKING_DATA_HASH = 36873697L
        private val getHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "get_has_tracking_data", GET_HAS_TRACKING_DATA_HASH)
        }

        private const val GET_POSE_HASH = 2806551826L
        private val getPoseBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "get_pose", GET_POSE_HASH)
        }

        private const val TRIGGER_HAPTIC_PULSE_HASH = 508576839L
        private val triggerHapticPulseBind by lazy {
            ObjectCalls.getMethodBind("XRNode3D", "trigger_haptic_pulse", TRIGGER_HAPTIC_PULSE_HASH)
        }
    }
}

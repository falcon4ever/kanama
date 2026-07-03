package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A tracked object.
 *
 * Generated from Godot docs: XRPositionalTracker
 */
open class XRPositionalTracker(handle: MemorySegment) : XRTracker(handle) {
    var profile: String
        @JvmName("profileProperty")
        get() = getTrackerProfile()
        @JvmName("setProfileProperty")
        set(value) = setTrackerProfile(value)

    var hand: Long
        @JvmName("handProperty")
        get() = getTrackerHand()
        @JvmName("setHandProperty")
        set(value) = setTrackerHand(value)

    /**
     * The profile associated with this tracker, interface dependent but will indicate the type of
     * controller being tracked.
     *
     * Generated from Godot docs: XRPositionalTracker.get_tracker_profile
     */
    fun getTrackerProfile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTrackerProfileBind, handle)
    }

    /**
     * The profile associated with this tracker, interface dependent but will indicate the type of
     * controller being tracked.
     *
     * Generated from Godot docs: XRPositionalTracker.set_tracker_profile
     */
    fun setTrackerProfile(profile: String) {
        ObjectCalls.ptrcallWithStringArg(setTrackerProfileBind, handle, profile)
    }

    /**
     * Defines which hand this tracker relates to.
     *
     * Generated from Godot docs: XRPositionalTracker.get_tracker_hand
     */
    fun getTrackerHand(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackerHandBind, handle)
    }

    /**
     * Defines which hand this tracker relates to.
     *
     * Generated from Godot docs: XRPositionalTracker.set_tracker_hand
     */
    fun setTrackerHand(hand: Long) {
        ObjectCalls.ptrcallWithLongArg(setTrackerHandBind, handle, hand)
    }

    /**
     * Returns `true` if the tracker is available and is currently tracking the bound `name` pose.
     *
     * Generated from Godot docs: XRPositionalTracker.has_pose
     */
    fun hasPose(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasPoseBind, handle, name)
    }

    /**
     * Returns the current `XRPose` state object for the bound `name` pose.
     *
     * Generated from Godot docs: XRPositionalTracker.get_pose
     */
    fun getPose(name: String): XRPose? {
        return XRPose.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getPoseBind, handle, name))
    }

    /**
     * Marks this pose as invalid, we don't clear the last reported state but it allows users to decide
     * if trackers need to be hidden if we lose tracking or just remain at their last known position.
     *
     * Generated from Godot docs: XRPositionalTracker.invalidate_pose
     */
    fun invalidatePose(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(invalidatePoseBind, handle, name)
    }

    /**
     * Sets the transform, linear velocity, angular velocity and tracking confidence for the given
     * pose. This method is called by an `XRInterface` implementation and should not be used directly.
     *
     * Generated from Godot docs: XRPositionalTracker.set_pose
     */
    fun setPose(name: String, transform: Transform3D, linearVelocity: Vector3, angularVelocity: Vector3, trackingConfidence: Long) {
        ObjectCalls.ptrcallWithStringNameTransform3DTwoVector3LongArgs(setPoseBind, handle, name, transform, linearVelocity, angularVelocity, trackingConfidence)
    }

    /**
     * Returns an input for this tracker. It can return a boolean, float or `Vector2` value depending
     * on whether the input is a button, trigger or thumbstick/thumbpad.
     *
     * Generated from Godot docs: XRPositionalTracker.get_input
     */
    fun getInput(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getInputBind, handle, name)
    }

    /**
     * Changes the value for the given input. This method is called by an `XRInterface` implementation
     * and should not be used directly.
     *
     * Generated from Godot docs: XRPositionalTracker.set_input
     */
    fun setInput(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setInputBind, handle, name, value)
    }

    object Signals {
        const val poseChanged: String = "pose_changed"
        const val poseLostTracking: String = "pose_lost_tracking"
        const val buttonPressed: String = "button_pressed"
        const val buttonReleased: String = "button_released"
        const val inputFloatChanged: String = "input_float_changed"
        const val inputVector2Changed: String = "input_vector2_changed"
        const val profileChanged: String = "profile_changed"
    }

    companion object {
        const val TRACKER_HAND_UNKNOWN: Long = 0L
        const val TRACKER_HAND_LEFT: Long = 1L
        const val TRACKER_HAND_RIGHT: Long = 2L
        const val TRACKER_HAND_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRPositionalTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRPositionalTracker? =
            if (handle.address() == 0L) null else XRPositionalTracker(handle)

        private const val GET_TRACKER_PROFILE_HASH = 201670096L
        private val getTrackerProfileBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "get_tracker_profile", GET_TRACKER_PROFILE_HASH)
        }

        private const val SET_TRACKER_PROFILE_HASH = 83702148L
        private val setTrackerProfileBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "set_tracker_profile", SET_TRACKER_PROFILE_HASH)
        }

        private const val GET_TRACKER_HAND_HASH = 4181770860L
        private val getTrackerHandBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "get_tracker_hand", GET_TRACKER_HAND_HASH)
        }

        private const val SET_TRACKER_HAND_HASH = 3904108980L
        private val setTrackerHandBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "set_tracker_hand", SET_TRACKER_HAND_HASH)
        }

        private const val HAS_POSE_HASH = 2619796661L
        private val hasPoseBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "has_pose", HAS_POSE_HASH)
        }

        private const val GET_POSE_HASH = 4099720006L
        private val getPoseBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "get_pose", GET_POSE_HASH)
        }

        private const val INVALIDATE_POSE_HASH = 3304788590L
        private val invalidatePoseBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "invalidate_pose", INVALIDATE_POSE_HASH)
        }

        private const val SET_POSE_HASH = 3451230163L
        private val setPoseBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "set_pose", SET_POSE_HASH)
        }

        private const val GET_INPUT_HASH = 2760726917L
        private val getInputBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "get_input", GET_INPUT_HASH)
        }

        private const val SET_INPUT_HASH = 3776071444L
        private val setInputBind by lazy {
            ObjectCalls.getMethodBind("XRPositionalTracker", "set_input", SET_INPUT_HASH)
        }
    }
}

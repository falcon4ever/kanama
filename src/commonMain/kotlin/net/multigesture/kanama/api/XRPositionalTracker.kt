package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * A tracked object.
 *
 * Generated from Godot docs: XRPositionalTracker
 */
open class XRPositionalTracker(handle: GodotHandle) : XRTracker(handle) {
    var profile: String
        @JvmName("profileProperty")
        get() = getTrackerProfile()
        @JvmName("setProfileProperty")
        set(value) = setTrackerProfile(value)

    var hand: XRPositionalTracker.TrackerHand
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getTrackerProfileBind, segment)
    }

    /**
     * The profile associated with this tracker, interface dependent but will indicate the type of
     * controller being tracked.
     *
     * Generated from Godot docs: XRPositionalTracker.set_tracker_profile
     */
    fun setTrackerProfile(profile: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setTrackerProfileBind, segment, profile)
    }

    /**
     * Defines which hand this tracker relates to.
     *
     * Generated from Godot docs: XRPositionalTracker.get_tracker_hand
     */
    fun getTrackerHand(): XRPositionalTracker.TrackerHand {
        checkOpen()
        return XRPositionalTracker.TrackerHand(ObjectCalls.ptrcallNoArgsRetLong(getTrackerHandBind, segment))
    }

    /**
     * Defines which hand this tracker relates to.
     *
     * Generated from Godot docs: XRPositionalTracker.set_tracker_hand
     */
    fun setTrackerHand(hand: XRPositionalTracker.TrackerHand) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTrackerHandBind, segment, hand.value)
    }

    /**
     * Returns `true` if the tracker is available and is currently tracking the bound `name` pose.
     *
     * Generated from Godot docs: XRPositionalTracker.has_pose
     */
    fun hasPose(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasPoseBind, segment, name)
    }

    /**
     * Returns the current `XRPose` state object for the bound `name` pose.
     *
     * Generated from Godot docs: XRPositionalTracker.get_pose
     */
    fun getPose(name: String): XRPose? {
        checkOpen()
        return XRPose.wrapOwned(ObjectCalls.ptrcallWithStringNameArgRetObject(getPoseBind, segment, name))
    }

    /**
     * Marks this pose as invalid, we don't clear the last reported state but it allows users to decide
     * if trackers need to be hidden if we lose tracking or just remain at their last known position.
     *
     * Generated from Godot docs: XRPositionalTracker.invalidate_pose
     */
    fun invalidatePose(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(invalidatePoseBind, segment, name)
    }

    /**
     * Sets the transform, linear velocity, angular velocity and tracking confidence for the given
     * pose. This method is called by an `XRInterface` implementation and should not be used directly.
     *
     * Generated from Godot docs: XRPositionalTracker.set_pose
     */
    fun setPose(name: String, transform: Transform3D, linearVelocity: Vector3, angularVelocity: Vector3, trackingConfidence: XRPose.TrackingConfidence) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameTransform3DTwoVector3LongArgs(setPoseBind, segment, name, transform, linearVelocity, angularVelocity, trackingConfidence.value)
    }

    /**
     * Returns an input for this tracker. It can return a boolean, float or `Vector2` value depending
     * on whether the input is a button, trigger or thumbstick/thumbpad.
     *
     * Generated from Godot docs: XRPositionalTracker.get_input
     */
    fun getInput(name: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getInputBind, segment, name)
    }

    /**
     * Changes the value for the given input. This method is called by an `XRInterface` implementation
     * and should not be used directly.
     *
     * Generated from Godot docs: XRPositionalTracker.set_input
     */
    fun setInput(name: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setInputBind, segment, name, value)
    }

    /** Signal `pose_changed(pose: XRPose)`; see [TypedSignal]. */
    val poseChanged: Signal1<XRPose>
        @JvmName("poseChangedTypedSignal")
        get() = Signal1(this, "pose_changed", SignalArgType.objectOf("XRPose") { XRPose(it) })

    /** Signal `pose_lost_tracking(pose: XRPose)`; see [TypedSignal]. */
    val poseLostTracking: Signal1<XRPose>
        @JvmName("poseLostTrackingTypedSignal")
        get() = Signal1(this, "pose_lost_tracking", SignalArgType.objectOf("XRPose") { XRPose(it) })

    /** Signal `button_pressed(action_name: String)`; see [TypedSignal]. */
    val buttonPressed: Signal1<String>
        @JvmName("buttonPressedTypedSignal")
        get() = Signal1(this, "button_pressed", SignalArgType.STRING)

    /** Signal `button_released(action_name: String)`; see [TypedSignal]. */
    val buttonReleased: Signal1<String>
        @JvmName("buttonReleasedTypedSignal")
        get() = Signal1(this, "button_released", SignalArgType.STRING)

    /** Signal `input_float_changed(action_name: String, value: float)`; see [TypedSignal]. */
    val inputFloatChanged: Signal2<String, Double>
        @JvmName("inputFloatChangedTypedSignal")
        get() = Signal2(this, "input_float_changed", SignalArgType.STRING, SignalArgType.DOUBLE)

    /** Signal `input_vector2_changed(action_name: String, vector: Vector2)`; see [TypedSignal]. */
    val inputVector2Changed: Signal2<String, Vector2>
        @JvmName("inputVector2ChangedTypedSignal")
        get() = Signal2(this, "input_vector2_changed", SignalArgType.STRING, SignalArgType.valueOf<Vector2>("Vector2", Vector2::class))

    /** Signal `profile_changed(role: String)`; see [TypedSignal]. */
    val profileChanged: Signal1<String>
        @JvmName("profileChangedTypedSignal")
        get() = Signal1(this, "profile_changed", SignalArgType.STRING)

    object Signals {
        const val poseChanged: String = "pose_changed"
        const val poseLostTracking: String = "pose_lost_tracking"
        const val buttonPressed: String = "button_pressed"
        const val buttonReleased: String = "button_released"
        const val inputFloatChanged: String = "input_float_changed"
        const val inputVector2Changed: String = "input_vector2_changed"
        const val profileChanged: String = "profile_changed"
    }

    /**
     * Godot's `XRPositionalTracker.TrackerHand` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`XRPositionalTracker.TrackerHand.<NAME>`).
     *
     * Generated from Godot docs: XRPositionalTracker.TrackerHand
     */
    @JvmInline
    value class TrackerHand(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The hand this tracker is held in is unknown or not applicable.
             *
             * Generated from Godot docs: XRPositionalTracker.TRACKER_HAND_UNKNOWN
             */
            val UNKNOWN: TrackerHand get() = TrackerHand(0L)
            /**
             * This tracker is the left hand controller.
             *
             * Generated from Godot docs: XRPositionalTracker.TRACKER_HAND_LEFT
             */
            val LEFT: TrackerHand get() = TrackerHand(1L)
            /**
             * This tracker is the right hand controller.
             *
             * Generated from Godot docs: XRPositionalTracker.TRACKER_HAND_RIGHT
             */
            val RIGHT: TrackerHand get() = TrackerHand(2L)
            /**
             * Represents the size of the `TrackerHand` enum.
             *
             * Generated from Godot docs: XRPositionalTracker.TRACKER_HAND_MAX
             */
            val MAX: TrackerHand get() = TrackerHand(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRPositionalTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XRPositionalTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(XRPositionalTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XRPositionalTracker? =
            if (handle.address() == 0L) null else XRPositionalTracker(GodotHandle(handle))

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

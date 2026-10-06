package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A 3D node representing a spatially-tracked controller.
 *
 * Generated from Godot docs: XRController3D
 */
class XRController3D(handle: GodotHandle) : XRNode3D(handle) {
    /**
     * Returns `true` if the button with the given `name` is pressed. Note: The current `XRInterface`
     * defines the `name` for each input. In the case of OpenXR, these are the names of actions in the
     * current action set.
     *
     * Generated from Godot docs: XRController3D.is_button_pressed
     */
    fun isButtonPressed(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.isButtonPressedBind, segment, name)
    }

    /**
     * Returns a `Variant` for the input with the given `name`. This works for any input type, the
     * variant will be typed according to the actions configuration. Note: The current `XRInterface`
     * defines the `name` for each input. In the case of OpenXR, these are the names of actions in the
     * current action set.
     *
     * Generated from Godot docs: XRController3D.get_input
     */
    fun getInput(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getInputBind, segment, name)
    }

    /**
     * Returns a numeric value for the input with the given `name`. This is used for triggers and grip
     * sensors. Note: The current `XRInterface` defines the `name` for each input. In the case of
     * OpenXR, these are the names of actions in the current action set.
     *
     * Generated from Godot docs: XRController3D.get_float
     */
    fun getFloat(name: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(Binds.getFloatBind, segment, name)
    }

    /**
     * Returns a `Vector2` for the input with the given `name`. This is used for thumbsticks and
     * thumbpads found on many controllers. Note: The current `XRInterface` defines the `name` for each
     * input. In the case of OpenXR, these are the names of actions in the current action set.
     *
     * Generated from Godot docs: XRController3D.get_vector2
     */
    fun getVector2(name: String): Vector2 {
        return ObjectCalls.ptrcallWithStringNameArgRetVector2(Binds.getVector2Bind, segment, name)
    }

    /**
     * Returns the hand holding this controller, if known.
     *
     * Generated from Godot docs: XRController3D.get_tracker_hand
     */
    fun getTrackerHand(): XRPositionalTracker.TrackerHand {
        return XRPositionalTracker.TrackerHand(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTrackerHandBind, segment))
    }

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

    /** Signal `input_vector2_changed(action_name: String, value: Vector2)`; see [TypedSignal]. */
    val inputVector2Changed: Signal2<String, Vector2>
        @JvmName("inputVector2ChangedTypedSignal")
        get() = Signal2(this, "input_vector2_changed", SignalArgType.STRING, SignalArgType.valueOf<Vector2>("Vector2", Vector2::class))

    /** Signal `profile_changed(role: String)`; see [TypedSignal]. */
    val profileChanged: Signal1<String>
        @JvmName("profileChangedTypedSignal")
        get() = Signal1(this, "profile_changed", SignalArgType.STRING)

    object Signals {
        const val buttonPressed: String = "button_pressed"
        const val buttonReleased: String = "button_released"
        const val inputFloatChanged: String = "input_float_changed"
        const val inputVector2Changed: String = "input_vector2_changed"
        const val profileChanged: String = "profile_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRController3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRController3D? =
            if (handle.address() == 0L) null else XRController3D(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_BUTTON_PRESSED_HASH = 2619796661L
        @JvmField
        val isButtonPressedBind =
            ObjectCalls.getMethodBind("XRController3D", "is_button_pressed", IS_BUTTON_PRESSED_HASH)

        private const val GET_INPUT_HASH = 2760726917L
        @JvmField
        val getInputBind =
            ObjectCalls.getMethodBind("XRController3D", "get_input", GET_INPUT_HASH)

        private const val GET_FLOAT_HASH = 2349060816L
        @JvmField
        val getFloatBind =
            ObjectCalls.getMethodBind("XRController3D", "get_float", GET_FLOAT_HASH)

        private const val GET_VECTOR2_HASH = 3100822709L
        @JvmField
        val getVector2Bind =
            ObjectCalls.getMethodBind("XRController3D", "get_vector2", GET_VECTOR2_HASH)

        private const val GET_TRACKER_HAND_HASH = 4181770860L
        @JvmField
        val getTrackerHandBind =
            ObjectCalls.getMethodBind("XRController3D", "get_tracker_hand", GET_TRACKER_HAND_HASH)
    }
}

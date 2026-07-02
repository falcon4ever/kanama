package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 3D node representing a spatially-tracked controller.
 *
 * Generated from Godot docs: XRController3D
 */
class XRController3D(handle: MemorySegment) : XRNode3D(handle) {
    fun isButtonPressed(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isButtonPressedBind, handle, name)
    }

    fun getInput(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getInputBind, handle, name)
    }

    fun getFloat(name: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(getFloatBind, handle, name)
    }

    fun getVector2(name: String): Vector2 {
        return ObjectCalls.ptrcallWithStringNameArgRetVector2(getVector2Bind, handle, name)
    }

    fun getTrackerHand(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackerHandBind, handle)
    }

    object Signals {
        const val buttonPressed: String = "button_pressed"
        const val buttonReleased: String = "button_released"
        const val inputFloatChanged: String = "input_float_changed"
        const val inputVector2Changed: String = "input_vector2_changed"
        const val profileChanged: String = "profile_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRController3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRController3D? =
            if (handle.address() == 0L) null else XRController3D(handle)

        private const val IS_BUTTON_PRESSED_HASH = 2619796661L
        private val isButtonPressedBind by lazy {
            ObjectCalls.getMethodBind("XRController3D", "is_button_pressed", IS_BUTTON_PRESSED_HASH)
        }

        private const val GET_INPUT_HASH = 2760726917L
        private val getInputBind by lazy {
            ObjectCalls.getMethodBind("XRController3D", "get_input", GET_INPUT_HASH)
        }

        private const val GET_FLOAT_HASH = 2349060816L
        private val getFloatBind by lazy {
            ObjectCalls.getMethodBind("XRController3D", "get_float", GET_FLOAT_HASH)
        }

        private const val GET_VECTOR2_HASH = 3100822709L
        private val getVector2Bind by lazy {
            ObjectCalls.getMethodBind("XRController3D", "get_vector2", GET_VECTOR2_HASH)
        }

        private const val GET_TRACKER_HAND_HASH = 4181770860L
        private val getTrackerHandBind by lazy {
            ObjectCalls.getMethodBind("XRController3D", "get_tracker_hand", GET_TRACKER_HAND_HASH)
        }
    }
}

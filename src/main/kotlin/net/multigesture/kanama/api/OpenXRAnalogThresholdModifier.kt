package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRAnalogThresholdModifier
 */
class OpenXRAnalogThresholdModifier(handle: MemorySegment) : OpenXRActionBindingModifier(handle) {
    var onThreshold: Double
        @JvmName("onThresholdProperty")
        get() = getOnThreshold()
        @JvmName("setOnThresholdProperty")
        set(value) = setOnThreshold(value)

    var offThreshold: Double
        @JvmName("offThresholdProperty")
        get() = getOffThreshold()
        @JvmName("setOffThresholdProperty")
        set(value) = setOffThreshold(value)

    var onHaptic: OpenXRHapticBase?
        @JvmName("onHapticProperty")
        get() = getOnHaptic()
        @JvmName("setOnHapticProperty")
        set(value) = setOnHaptic(value)

    var offHaptic: OpenXRHapticBase?
        @JvmName("offHapticProperty")
        get() = getOffHaptic()
        @JvmName("setOffHapticProperty")
        set(value) = setOffHaptic(value)

    fun setOnThreshold(onThreshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOnThresholdBind, handle, onThreshold)
    }

    fun getOnThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOnThresholdBind, handle)
    }

    fun setOffThreshold(offThreshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOffThresholdBind, handle, offThreshold)
    }

    fun getOffThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOffThresholdBind, handle)
    }

    fun setOnHaptic(haptic: OpenXRHapticBase?) {
        ObjectCalls.ptrcallWithObjectArgs(setOnHapticBind, handle, listOf(haptic?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOnHaptic(): OpenXRHapticBase? {
        return OpenXRHapticBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOnHapticBind, handle))
    }

    fun setOffHaptic(haptic: OpenXRHapticBase?) {
        ObjectCalls.ptrcallWithObjectArgs(setOffHapticBind, handle, listOf(haptic?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOffHaptic(): OpenXRHapticBase? {
        return OpenXRHapticBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOffHapticBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRAnalogThresholdModifier? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRAnalogThresholdModifier? =
            if (handle.address() == 0L) null else OpenXRAnalogThresholdModifier(handle)

        private const val SET_ON_THRESHOLD_HASH = 373806689L
        private val setOnThresholdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_on_threshold", SET_ON_THRESHOLD_HASH)
        }

        private const val GET_ON_THRESHOLD_HASH = 1740695150L
        private val getOnThresholdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_on_threshold", GET_ON_THRESHOLD_HASH)
        }

        private const val SET_OFF_THRESHOLD_HASH = 373806689L
        private val setOffThresholdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_off_threshold", SET_OFF_THRESHOLD_HASH)
        }

        private const val GET_OFF_THRESHOLD_HASH = 1740695150L
        private val getOffThresholdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_off_threshold", GET_OFF_THRESHOLD_HASH)
        }

        private const val SET_ON_HAPTIC_HASH = 2998020150L
        private val setOnHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_on_haptic", SET_ON_HAPTIC_HASH)
        }

        private const val GET_ON_HAPTIC_HASH = 922310751L
        private val getOnHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_on_haptic", GET_ON_HAPTIC_HASH)
        }

        private const val SET_OFF_HAPTIC_HASH = 2998020150L
        private val setOffHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_off_haptic", SET_OFF_HAPTIC_HASH)
        }

        private const val GET_OFF_HAPTIC_HASH = 922310751L
        private val getOffHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_off_haptic", GET_OFF_HAPTIC_HASH)
        }
    }
}

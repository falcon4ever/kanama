package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRAnalogThresholdModifier
 */
class OpenXRAnalogThresholdModifier(handle: GodotHandle) : OpenXRActionBindingModifier(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setOnThresholdBind, segment, onThreshold)
    }

    fun getOnThreshold(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getOnThresholdBind, segment)
    }

    fun setOffThreshold(offThreshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setOffThresholdBind, segment, offThreshold)
    }

    fun getOffThreshold(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getOffThresholdBind, segment)
    }

    fun setOnHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setOnHapticBind, segment, listOf(haptic?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getOnHaptic(): OpenXRHapticBase? {
        checkOpen()
        return OpenXRHapticBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOnHapticBind, segment))
    }

    fun setOffHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setOffHapticBind, segment, listOf(haptic?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getOffHaptic(): OpenXRHapticBase? {
        checkOpen()
        return OpenXRHapticBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOffHapticBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAnalogThresholdModifier? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRAnalogThresholdModifier? =
            if (handle.address() == 0L) null else OpenXRAnalogThresholdModifier(GodotHandle(handle))

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

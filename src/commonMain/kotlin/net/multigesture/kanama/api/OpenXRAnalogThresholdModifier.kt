package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * The analog threshold binding modifier can modify a float input to a boolean input with specified
 * thresholds.
 *
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

    /**
     * When our input value is equal or larger than this value, our output becomes `true`. It stays
     * `true` until it falls under the `off_threshold` value.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.set_on_threshold
     */
    fun setOnThreshold(onThreshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOnThresholdBind, segment, onThreshold)
        ObjectCalls.keepReachable(this)
    }

    /**
     * When our input value is equal or larger than this value, our output becomes `true`. It stays
     * `true` until it falls under the `off_threshold` value.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.get_on_threshold
     */
    fun getOnThreshold(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOnThresholdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * When our input value falls below this, our output becomes `false`.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.set_off_threshold
     */
    fun setOffThreshold(offThreshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOffThresholdBind, segment, offThreshold)
        ObjectCalls.keepReachable(this)
    }

    /**
     * When our input value falls below this, our output becomes `false`.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.get_off_threshold
     */
    fun getOffThreshold(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOffThresholdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Haptic pulse to emit when the user presses the input.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.set_on_haptic
     */
    fun setOnHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setOnHapticBind, segment, listOf(haptic?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(haptic)
    }

    /**
     * Haptic pulse to emit when the user presses the input.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.get_on_haptic
     */
    fun getOnHaptic(): OpenXRHapticBase? {
        checkOpen()
        val ret = OpenXRHapticBase.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOnHapticBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Haptic pulse to emit when the user releases the input.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.set_off_haptic
     */
    fun setOffHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setOffHapticBind, segment, listOf(haptic?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(haptic)
    }

    /**
     * Haptic pulse to emit when the user releases the input.
     *
     * Generated from Godot docs: OpenXRAnalogThresholdModifier.get_off_haptic
     */
    fun getOffHaptic(): OpenXRHapticBase? {
        checkOpen()
        val ret = OpenXRHapticBase.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOffHapticBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAnalogThresholdModifier? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRAnalogThresholdModifier? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRAnalogThresholdModifier(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRAnalogThresholdModifier? =
            if (handle.address() == 0L) null else OpenXRAnalogThresholdModifier(GodotHandle(handle))

        /** A new OpenXRAnalogThresholdModifier, GDScript's `OpenXRAnalogThresholdModifier.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRAnalogThresholdModifier =
            RefCounted.owned(OpenXRAnalogThresholdModifier(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRAnalogThresholdModifier"), "OpenXRAnalogThresholdModifier"))))
    }

    private object Binds {
        private const val SET_ON_THRESHOLD_HASH = 373806689L
        @JvmField
        val setOnThresholdBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_on_threshold", SET_ON_THRESHOLD_HASH)

        private const val GET_ON_THRESHOLD_HASH = 1740695150L
        @JvmField
        val getOnThresholdBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_on_threshold", GET_ON_THRESHOLD_HASH)

        private const val SET_OFF_THRESHOLD_HASH = 373806689L
        @JvmField
        val setOffThresholdBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_off_threshold", SET_OFF_THRESHOLD_HASH)

        private const val GET_OFF_THRESHOLD_HASH = 1740695150L
        @JvmField
        val getOffThresholdBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_off_threshold", GET_OFF_THRESHOLD_HASH)

        private const val SET_ON_HAPTIC_HASH = 2998020150L
        @JvmField
        val setOnHapticBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_on_haptic", SET_ON_HAPTIC_HASH)

        private const val GET_ON_HAPTIC_HASH = 922310751L
        @JvmField
        val getOnHapticBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_on_haptic", GET_ON_HAPTIC_HASH)

        private const val SET_OFF_HAPTIC_HASH = 2998020150L
        @JvmField
        val setOffHapticBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "set_off_haptic", SET_OFF_HAPTIC_HASH)

        private const val GET_OFF_HAPTIC_HASH = 922310751L
        @JvmField
        val getOffHapticBind =
            ObjectCalls.getMethodBind("OpenXRAnalogThresholdModifier", "get_off_haptic", GET_OFF_HAPTIC_HASH)
    }
}

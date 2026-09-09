package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRDpadBindingModifier
 */
class OpenXRDpadBindingModifier(handle: MemorySegment) : OpenXRIPBindingModifier(handle) {
    var actionSet: OpenXRActionSet?
        @JvmName("actionSetProperty")
        get() = getActionSet()
        @JvmName("setActionSetProperty")
        set(value) = setActionSet(value)

    var inputPath: String
        @JvmName("inputPathProperty")
        get() = getInputPath()
        @JvmName("setInputPathProperty")
        set(value) = setInputPath(value)

    var threshold: Double
        @JvmName("thresholdProperty")
        get() = getThreshold()
        @JvmName("setThresholdProperty")
        set(value) = setThreshold(value)

    var thresholdReleased: Double
        @JvmName("thresholdReleasedProperty")
        get() = getThresholdReleased()
        @JvmName("setThresholdReleasedProperty")
        set(value) = setThresholdReleased(value)

    var centerRegion: Double
        @JvmName("centerRegionProperty")
        get() = getCenterRegion()
        @JvmName("setCenterRegionProperty")
        set(value) = setCenterRegion(value)

    var wedgeAngle: Double
        @JvmName("wedgeAngleProperty")
        get() = getWedgeAngle()
        @JvmName("setWedgeAngleProperty")
        set(value) = setWedgeAngle(value)

    var isSticky: Boolean
        @JvmName("isStickyProperty")
        get() = getIsSticky()
        @JvmName("setIsStickyProperty")
        set(value) = setIsSticky(value)

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

    fun setActionSet(actionSet: OpenXRActionSet?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setActionSetBind, handle, listOf(actionSet?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getActionSet(): OpenXRActionSet? {
        checkOpen()
        return OpenXRActionSet.wrap(ObjectCalls.ptrcallNoArgsRetObject(getActionSetBind, handle))
    }

    fun setInputPath(inputPath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setInputPathBind, handle, inputPath)
    }

    fun getInputPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getInputPathBind, handle)
    }

    fun setThreshold(threshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setThresholdBind, handle, threshold)
    }

    fun getThreshold(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getThresholdBind, handle)
    }

    fun setThresholdReleased(thresholdReleased: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setThresholdReleasedBind, handle, thresholdReleased)
    }

    fun getThresholdReleased(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getThresholdReleasedBind, handle)
    }

    fun setCenterRegion(centerRegion: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setCenterRegionBind, handle, centerRegion)
    }

    fun getCenterRegion(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCenterRegionBind, handle)
    }

    fun setWedgeAngle(wedgeAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setWedgeAngleBind, handle, wedgeAngle)
    }

    fun getWedgeAngle(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getWedgeAngleBind, handle)
    }

    fun setIsSticky(isSticky: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setIsStickyBind, handle, isSticky)
    }

    fun getIsSticky(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getIsStickyBind, handle)
    }

    fun setOnHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setOnHapticBind, handle, listOf(haptic?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOnHaptic(): OpenXRHapticBase? {
        checkOpen()
        return OpenXRHapticBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOnHapticBind, handle))
    }

    fun setOffHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setOffHapticBind, handle, listOf(haptic?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOffHaptic(): OpenXRHapticBase? {
        checkOpen()
        return OpenXRHapticBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOffHapticBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRDpadBindingModifier? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRDpadBindingModifier? =
            if (handle.address() == 0L) null else OpenXRDpadBindingModifier(handle)

        private const val SET_ACTION_SET_HASH = 2093310581L
        private val setActionSetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_action_set", SET_ACTION_SET_HASH)
        }

        private const val GET_ACTION_SET_HASH = 619941079L
        private val getActionSetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_action_set", GET_ACTION_SET_HASH)
        }

        private const val SET_INPUT_PATH_HASH = 83702148L
        private val setInputPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_input_path", SET_INPUT_PATH_HASH)
        }

        private const val GET_INPUT_PATH_HASH = 201670096L
        private val getInputPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_input_path", GET_INPUT_PATH_HASH)
        }

        private const val SET_THRESHOLD_HASH = 373806689L
        private val setThresholdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_threshold", SET_THRESHOLD_HASH)
        }

        private const val GET_THRESHOLD_HASH = 1740695150L
        private val getThresholdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_threshold", GET_THRESHOLD_HASH)
        }

        private const val SET_THRESHOLD_RELEASED_HASH = 373806689L
        private val setThresholdReleasedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_threshold_released", SET_THRESHOLD_RELEASED_HASH)
        }

        private const val GET_THRESHOLD_RELEASED_HASH = 1740695150L
        private val getThresholdReleasedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_threshold_released", GET_THRESHOLD_RELEASED_HASH)
        }

        private const val SET_CENTER_REGION_HASH = 373806689L
        private val setCenterRegionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_center_region", SET_CENTER_REGION_HASH)
        }

        private const val GET_CENTER_REGION_HASH = 1740695150L
        private val getCenterRegionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_center_region", GET_CENTER_REGION_HASH)
        }

        private const val SET_WEDGE_ANGLE_HASH = 373806689L
        private val setWedgeAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_wedge_angle", SET_WEDGE_ANGLE_HASH)
        }

        private const val GET_WEDGE_ANGLE_HASH = 1740695150L
        private val getWedgeAngleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_wedge_angle", GET_WEDGE_ANGLE_HASH)
        }

        private const val SET_IS_STICKY_HASH = 2586408642L
        private val setIsStickyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_is_sticky", SET_IS_STICKY_HASH)
        }

        private const val GET_IS_STICKY_HASH = 36873697L
        private val getIsStickyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_is_sticky", GET_IS_STICKY_HASH)
        }

        private const val SET_ON_HAPTIC_HASH = 2998020150L
        private val setOnHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_on_haptic", SET_ON_HAPTIC_HASH)
        }

        private const val GET_ON_HAPTIC_HASH = 922310751L
        private val getOnHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_on_haptic", GET_ON_HAPTIC_HASH)
        }

        private const val SET_OFF_HAPTIC_HASH = 2998020150L
        private val setOffHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_off_haptic", SET_OFF_HAPTIC_HASH)
        }

        private const val GET_OFF_HAPTIC_HASH = 922310751L
        private val getOffHapticBind by lazy {
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_off_haptic", GET_OFF_HAPTIC_HASH)
        }
    }
}

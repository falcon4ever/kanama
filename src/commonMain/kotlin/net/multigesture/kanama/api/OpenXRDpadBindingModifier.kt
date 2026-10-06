package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRDpadBindingModifier
 */
class OpenXRDpadBindingModifier(handle: GodotHandle) : OpenXRIPBindingModifier(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setActionSetBind, segment, listOf(actionSet?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getActionSet(): OpenXRActionSet? {
        checkOpen()
        return OpenXRActionSet.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getActionSetBind, segment))
    }

    fun setInputPath(inputPath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setInputPathBind, segment, inputPath)
    }

    fun getInputPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getInputPathBind, segment)
    }

    fun setThreshold(threshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setThresholdBind, segment, threshold)
    }

    fun getThreshold(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getThresholdBind, segment)
    }

    fun setThresholdReleased(thresholdReleased: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setThresholdReleasedBind, segment, thresholdReleased)
    }

    fun getThresholdReleased(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getThresholdReleasedBind, segment)
    }

    fun setCenterRegion(centerRegion: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCenterRegionBind, segment, centerRegion)
    }

    fun getCenterRegion(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCenterRegionBind, segment)
    }

    fun setWedgeAngle(wedgeAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWedgeAngleBind, segment, wedgeAngle)
    }

    fun getWedgeAngle(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWedgeAngleBind, segment)
    }

    fun setIsSticky(isSticky: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIsStickyBind, segment, isSticky)
    }

    fun getIsSticky(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getIsStickyBind, segment)
    }

    fun setOnHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setOnHapticBind, segment, listOf(haptic?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getOnHaptic(): OpenXRHapticBase? {
        checkOpen()
        return OpenXRHapticBase.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOnHapticBind, segment))
    }

    fun setOffHaptic(haptic: OpenXRHapticBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setOffHapticBind, segment, listOf(haptic?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getOffHaptic(): OpenXRHapticBase? {
        checkOpen()
        return OpenXRHapticBase.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOffHapticBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRDpadBindingModifier? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRDpadBindingModifier? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRDpadBindingModifier(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRDpadBindingModifier? =
            if (handle.address() == 0L) null else OpenXRDpadBindingModifier(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ACTION_SET_HASH = 2093310581L
        @JvmField
        val setActionSetBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_action_set", SET_ACTION_SET_HASH)

        private const val GET_ACTION_SET_HASH = 619941079L
        @JvmField
        val getActionSetBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_action_set", GET_ACTION_SET_HASH)

        private const val SET_INPUT_PATH_HASH = 83702148L
        @JvmField
        val setInputPathBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_input_path", SET_INPUT_PATH_HASH)

        private const val GET_INPUT_PATH_HASH = 201670096L
        @JvmField
        val getInputPathBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_input_path", GET_INPUT_PATH_HASH)

        private const val SET_THRESHOLD_HASH = 373806689L
        @JvmField
        val setThresholdBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_threshold", SET_THRESHOLD_HASH)

        private const val GET_THRESHOLD_HASH = 1740695150L
        @JvmField
        val getThresholdBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_threshold", GET_THRESHOLD_HASH)

        private const val SET_THRESHOLD_RELEASED_HASH = 373806689L
        @JvmField
        val setThresholdReleasedBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_threshold_released", SET_THRESHOLD_RELEASED_HASH)

        private const val GET_THRESHOLD_RELEASED_HASH = 1740695150L
        @JvmField
        val getThresholdReleasedBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_threshold_released", GET_THRESHOLD_RELEASED_HASH)

        private const val SET_CENTER_REGION_HASH = 373806689L
        @JvmField
        val setCenterRegionBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_center_region", SET_CENTER_REGION_HASH)

        private const val GET_CENTER_REGION_HASH = 1740695150L
        @JvmField
        val getCenterRegionBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_center_region", GET_CENTER_REGION_HASH)

        private const val SET_WEDGE_ANGLE_HASH = 373806689L
        @JvmField
        val setWedgeAngleBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_wedge_angle", SET_WEDGE_ANGLE_HASH)

        private const val GET_WEDGE_ANGLE_HASH = 1740695150L
        @JvmField
        val getWedgeAngleBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_wedge_angle", GET_WEDGE_ANGLE_HASH)

        private const val SET_IS_STICKY_HASH = 2586408642L
        @JvmField
        val setIsStickyBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_is_sticky", SET_IS_STICKY_HASH)

        private const val GET_IS_STICKY_HASH = 36873697L
        @JvmField
        val getIsStickyBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_is_sticky", GET_IS_STICKY_HASH)

        private const val SET_ON_HAPTIC_HASH = 2998020150L
        @JvmField
        val setOnHapticBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_on_haptic", SET_ON_HAPTIC_HASH)

        private const val GET_ON_HAPTIC_HASH = 922310751L
        @JvmField
        val getOnHapticBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_on_haptic", GET_ON_HAPTIC_HASH)

        private const val SET_OFF_HAPTIC_HASH = 2998020150L
        @JvmField
        val setOffHapticBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "set_off_haptic", SET_OFF_HAPTIC_HASH)

        private const val GET_OFF_HAPTIC_HASH = 922310751L
        @JvmField
        val getOffHapticBind =
            ObjectCalls.getMethodBind("OpenXRDpadBindingModifier", "get_off_haptic", GET_OFF_HAPTIC_HASH)
    }
}

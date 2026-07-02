package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Parent class for camera settings.
 *
 * Generated from Godot docs: CameraAttributes
 */
open class CameraAttributes(handle: MemorySegment) : Resource(handle) {
    var exposureSensitivity: Double
        @JvmName("exposureSensitivityProperty")
        get() = getExposureSensitivity()
        @JvmName("setExposureSensitivityProperty")
        set(value) = setExposureSensitivity(value)

    var exposureMultiplier: Double
        @JvmName("exposureMultiplierProperty")
        get() = getExposureMultiplier()
        @JvmName("setExposureMultiplierProperty")
        set(value) = setExposureMultiplier(value)

    var autoExposureEnabled: Boolean
        @JvmName("autoExposureEnabledProperty")
        get() = isAutoExposureEnabled()
        @JvmName("setAutoExposureEnabledProperty")
        set(value) = setAutoExposureEnabled(value)

    var autoExposureScale: Double
        @JvmName("autoExposureScaleProperty")
        get() = getAutoExposureScale()
        @JvmName("setAutoExposureScaleProperty")
        set(value) = setAutoExposureScale(value)

    var autoExposureSpeed: Double
        @JvmName("autoExposureSpeedProperty")
        get() = getAutoExposureSpeed()
        @JvmName("setAutoExposureSpeedProperty")
        set(value) = setAutoExposureSpeed(value)

    fun setExposureMultiplier(multiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExposureMultiplierBind, handle, multiplier)
    }

    fun getExposureMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExposureMultiplierBind, handle)
    }

    fun setExposureSensitivity(sensitivity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExposureSensitivityBind, handle, sensitivity)
    }

    fun getExposureSensitivity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExposureSensitivityBind, handle)
    }

    fun setAutoExposureEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoExposureEnabledBind, handle, enabled)
    }

    fun isAutoExposureEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoExposureEnabledBind, handle)
    }

    fun setAutoExposureSpeed(exposureSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureSpeedBind, handle, exposureSpeed)
    }

    fun getAutoExposureSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureSpeedBind, handle)
    }

    fun setAutoExposureScale(exposureGrey: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureScaleBind, handle, exposureGrey)
    }

    fun getAutoExposureScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureScaleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CameraAttributes? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CameraAttributes? =
            if (handle.address() == 0L) null else CameraAttributes(handle)

        private const val SET_EXPOSURE_MULTIPLIER_HASH = 373806689L
        private val setExposureMultiplierBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "set_exposure_multiplier", SET_EXPOSURE_MULTIPLIER_HASH)
        }

        private const val GET_EXPOSURE_MULTIPLIER_HASH = 1740695150L
        private val getExposureMultiplierBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "get_exposure_multiplier", GET_EXPOSURE_MULTIPLIER_HASH)
        }

        private const val SET_EXPOSURE_SENSITIVITY_HASH = 373806689L
        private val setExposureSensitivityBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "set_exposure_sensitivity", SET_EXPOSURE_SENSITIVITY_HASH)
        }

        private const val GET_EXPOSURE_SENSITIVITY_HASH = 1740695150L
        private val getExposureSensitivityBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "get_exposure_sensitivity", GET_EXPOSURE_SENSITIVITY_HASH)
        }

        private const val SET_AUTO_EXPOSURE_ENABLED_HASH = 2586408642L
        private val setAutoExposureEnabledBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "set_auto_exposure_enabled", SET_AUTO_EXPOSURE_ENABLED_HASH)
        }

        private const val IS_AUTO_EXPOSURE_ENABLED_HASH = 36873697L
        private val isAutoExposureEnabledBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "is_auto_exposure_enabled", IS_AUTO_EXPOSURE_ENABLED_HASH)
        }

        private const val SET_AUTO_EXPOSURE_SPEED_HASH = 373806689L
        private val setAutoExposureSpeedBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "set_auto_exposure_speed", SET_AUTO_EXPOSURE_SPEED_HASH)
        }

        private const val GET_AUTO_EXPOSURE_SPEED_HASH = 1740695150L
        private val getAutoExposureSpeedBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "get_auto_exposure_speed", GET_AUTO_EXPOSURE_SPEED_HASH)
        }

        private const val SET_AUTO_EXPOSURE_SCALE_HASH = 373806689L
        private val setAutoExposureScaleBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "set_auto_exposure_scale", SET_AUTO_EXPOSURE_SCALE_HASH)
        }

        private const val GET_AUTO_EXPOSURE_SCALE_HASH = 1740695150L
        private val getAutoExposureScaleBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributes", "get_auto_exposure_scale", GET_AUTO_EXPOSURE_SCALE_HASH)
        }
    }
}

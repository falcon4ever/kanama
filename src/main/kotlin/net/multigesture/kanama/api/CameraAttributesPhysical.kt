package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Physically-based camera settings.
 *
 * Generated from Godot docs: CameraAttributesPhysical
 */
class CameraAttributesPhysical(handle: MemorySegment) : CameraAttributes(handle) {
    var frustumFocusDistance: Double
        @JvmName("frustumFocusDistanceProperty")
        get() = getFocusDistance()
        @JvmName("setFrustumFocusDistanceProperty")
        set(value) = setFocusDistance(value)

    var frustumFocalLength: Double
        @JvmName("frustumFocalLengthProperty")
        get() = getFocalLength()
        @JvmName("setFrustumFocalLengthProperty")
        set(value) = setFocalLength(value)

    var frustumNear: Double
        @JvmName("frustumNearProperty")
        get() = getNear()
        @JvmName("setFrustumNearProperty")
        set(value) = setNear(value)

    var frustumFar: Double
        @JvmName("frustumFarProperty")
        get() = getFar()
        @JvmName("setFrustumFarProperty")
        set(value) = setFar(value)

    var exposureAperture: Double
        @JvmName("exposureApertureProperty")
        get() = getAperture()
        @JvmName("setExposureApertureProperty")
        set(value) = setAperture(value)

    var exposureShutterSpeed: Double
        @JvmName("exposureShutterSpeedProperty")
        get() = getShutterSpeed()
        @JvmName("setExposureShutterSpeedProperty")
        set(value) = setShutterSpeed(value)

    var autoExposureMinExposureValue: Double
        @JvmName("autoExposureMinExposureValueProperty")
        get() = getAutoExposureMinExposureValue()
        @JvmName("setAutoExposureMinExposureValueProperty")
        set(value) = setAutoExposureMinExposureValue(value)

    var autoExposureMaxExposureValue: Double
        @JvmName("autoExposureMaxExposureValueProperty")
        get() = getAutoExposureMaxExposureValue()
        @JvmName("setAutoExposureMaxExposureValueProperty")
        set(value) = setAutoExposureMaxExposureValue(value)

    fun setAperture(aperture: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setApertureBind, handle, aperture)
    }

    fun getAperture(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getApertureBind, handle)
    }

    fun setShutterSpeed(shutterSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setShutterSpeedBind, handle, shutterSpeed)
    }

    fun getShutterSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getShutterSpeedBind, handle)
    }

    fun setFocalLength(focalLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFocalLengthBind, handle, focalLength)
    }

    fun getFocalLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFocalLengthBind, handle)
    }

    fun setFocusDistance(focusDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFocusDistanceBind, handle, focusDistance)
    }

    fun getFocusDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFocusDistanceBind, handle)
    }

    fun setNear(near: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNearBind, handle, near)
    }

    fun getNear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNearBind, handle)
    }

    fun setFar(far: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFarBind, handle, far)
    }

    fun getFar(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFarBind, handle)
    }

    fun getFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovBind, handle)
    }

    fun setAutoExposureMaxExposureValue(exposureValueMax: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMaxExposureValueBind, handle, exposureValueMax)
    }

    fun getAutoExposureMaxExposureValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureMaxExposureValueBind, handle)
    }

    fun setAutoExposureMinExposureValue(exposureValueMin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMinExposureValueBind, handle, exposureValueMin)
    }

    fun getAutoExposureMinExposureValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureMinExposureValueBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CameraAttributesPhysical? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CameraAttributesPhysical? =
            if (handle.address() == 0L) null else CameraAttributesPhysical(handle)

        private const val SET_APERTURE_HASH = 373806689L
        private val setApertureBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_aperture", SET_APERTURE_HASH)
        }

        private const val GET_APERTURE_HASH = 1740695150L
        private val getApertureBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_aperture", GET_APERTURE_HASH)
        }

        private const val SET_SHUTTER_SPEED_HASH = 373806689L
        private val setShutterSpeedBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_shutter_speed", SET_SHUTTER_SPEED_HASH)
        }

        private const val GET_SHUTTER_SPEED_HASH = 1740695150L
        private val getShutterSpeedBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_shutter_speed", GET_SHUTTER_SPEED_HASH)
        }

        private const val SET_FOCAL_LENGTH_HASH = 373806689L
        private val setFocalLengthBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_focal_length", SET_FOCAL_LENGTH_HASH)
        }

        private const val GET_FOCAL_LENGTH_HASH = 1740695150L
        private val getFocalLengthBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_focal_length", GET_FOCAL_LENGTH_HASH)
        }

        private const val SET_FOCUS_DISTANCE_HASH = 373806689L
        private val setFocusDistanceBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_focus_distance", SET_FOCUS_DISTANCE_HASH)
        }

        private const val GET_FOCUS_DISTANCE_HASH = 1740695150L
        private val getFocusDistanceBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_focus_distance", GET_FOCUS_DISTANCE_HASH)
        }

        private const val SET_NEAR_HASH = 373806689L
        private val setNearBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_near", SET_NEAR_HASH)
        }

        private const val GET_NEAR_HASH = 1740695150L
        private val getNearBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_near", GET_NEAR_HASH)
        }

        private const val SET_FAR_HASH = 373806689L
        private val setFarBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_far", SET_FAR_HASH)
        }

        private const val GET_FAR_HASH = 1740695150L
        private val getFarBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_far", GET_FAR_HASH)
        }

        private const val GET_FOV_HASH = 1740695150L
        private val getFovBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_fov", GET_FOV_HASH)
        }

        private const val SET_AUTO_EXPOSURE_MAX_EXPOSURE_VALUE_HASH = 373806689L
        private val setAutoExposureMaxExposureValueBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_auto_exposure_max_exposure_value", SET_AUTO_EXPOSURE_MAX_EXPOSURE_VALUE_HASH)
        }

        private const val GET_AUTO_EXPOSURE_MAX_EXPOSURE_VALUE_HASH = 1740695150L
        private val getAutoExposureMaxExposureValueBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_auto_exposure_max_exposure_value", GET_AUTO_EXPOSURE_MAX_EXPOSURE_VALUE_HASH)
        }

        private const val SET_AUTO_EXPOSURE_MIN_EXPOSURE_VALUE_HASH = 373806689L
        private val setAutoExposureMinExposureValueBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "set_auto_exposure_min_exposure_value", SET_AUTO_EXPOSURE_MIN_EXPOSURE_VALUE_HASH)
        }

        private const val GET_AUTO_EXPOSURE_MIN_EXPOSURE_VALUE_HASH = 1740695150L
        private val getAutoExposureMinExposureValueBind by lazy {
            ObjectCalls.getMethodBind("CameraAttributesPhysical", "get_auto_exposure_min_exposure_value", GET_AUTO_EXPOSURE_MIN_EXPOSURE_VALUE_HASH)
        }
    }
}

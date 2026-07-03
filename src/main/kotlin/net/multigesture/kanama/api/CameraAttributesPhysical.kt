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

    /**
     * Size of the aperture of the camera, measured in f-stops. An f-stop is a unitless ratio between
     * the focal length of the camera and the diameter of the aperture. A high aperture setting will
     * result in a smaller aperture which leads to a dimmer image and sharper focus. A low aperture
     * results in a wide aperture which lets in more light resulting in a brighter, less-focused image.
     * Default is appropriate for outdoors at daytime (i.e. for use with a default
     * `DirectionalLight3D`), for indoor lighting, a value between 2 and 4 is more appropriate. Only
     * available when `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is
     * enabled.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_aperture
     */
    fun setAperture(aperture: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setApertureBind, handle, aperture)
    }

    /**
     * Size of the aperture of the camera, measured in f-stops. An f-stop is a unitless ratio between
     * the focal length of the camera and the diameter of the aperture. A high aperture setting will
     * result in a smaller aperture which leads to a dimmer image and sharper focus. A low aperture
     * results in a wide aperture which lets in more light resulting in a brighter, less-focused image.
     * Default is appropriate for outdoors at daytime (i.e. for use with a default
     * `DirectionalLight3D`), for indoor lighting, a value between 2 and 4 is more appropriate. Only
     * available when `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is
     * enabled.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_aperture
     */
    fun getAperture(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getApertureBind, handle)
    }

    /**
     * Time for shutter to open and close, evaluated as `1 / shutter_speed` seconds. A higher value
     * will allow less light (leading to a darker image), while a lower value will allow more light
     * (leading to a brighter image). Only available when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_shutter_speed
     */
    fun setShutterSpeed(shutterSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setShutterSpeedBind, handle, shutterSpeed)
    }

    /**
     * Time for shutter to open and close, evaluated as `1 / shutter_speed` seconds. A higher value
     * will allow less light (leading to a darker image), while a lower value will allow more light
     * (leading to a brighter image). Only available when
     * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is enabled.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_shutter_speed
     */
    fun getShutterSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getShutterSpeedBind, handle)
    }

    /**
     * Distance between camera lens and camera aperture, measured in millimeters. Controls field of
     * view and depth of field. A larger focal length will result in a smaller field of view and a
     * narrower depth of field meaning fewer objects will be in focus. A smaller focal length will
     * result in a wider field of view and a larger depth of field meaning more objects will be in
     * focus. When attached to a `Camera3D` as its `Camera3D.attributes`, it will override the
     * `Camera3D.fov` property and the `Camera3D.keep_aspect` property.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_focal_length
     */
    fun setFocalLength(focalLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFocalLengthBind, handle, focalLength)
    }

    /**
     * Distance between camera lens and camera aperture, measured in millimeters. Controls field of
     * view and depth of field. A larger focal length will result in a smaller field of view and a
     * narrower depth of field meaning fewer objects will be in focus. A smaller focal length will
     * result in a wider field of view and a larger depth of field meaning more objects will be in
     * focus. When attached to a `Camera3D` as its `Camera3D.attributes`, it will override the
     * `Camera3D.fov` property and the `Camera3D.keep_aspect` property.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_focal_length
     */
    fun getFocalLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFocalLengthBind, handle)
    }

    /**
     * Distance from camera of object that will be in focus, measured in meters. Internally this will
     * be clamped to be at least 1 millimeter larger than `frustum_focal_length`.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_focus_distance
     */
    fun setFocusDistance(focusDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFocusDistanceBind, handle, focusDistance)
    }

    /**
     * Distance from camera of object that will be in focus, measured in meters. Internally this will
     * be clamped to be at least 1 millimeter larger than `frustum_focal_length`.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_focus_distance
     */
    fun getFocusDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFocusDistanceBind, handle)
    }

    /**
     * Override value for `Camera3D.near`. Used internally when calculating depth of field. When
     * attached to a `Camera3D` as its `Camera3D.attributes`, it will override the `Camera3D.near`
     * property.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_near
     */
    fun setNear(near: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNearBind, handle, near)
    }

    /**
     * Override value for `Camera3D.near`. Used internally when calculating depth of field. When
     * attached to a `Camera3D` as its `Camera3D.attributes`, it will override the `Camera3D.near`
     * property.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_near
     */
    fun getNear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNearBind, handle)
    }

    /**
     * Override value for `Camera3D.far`. Used internally when calculating depth of field. When
     * attached to a `Camera3D` as its `Camera3D.attributes`, it will override the `Camera3D.far`
     * property.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_far
     */
    fun setFar(far: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFarBind, handle, far)
    }

    /**
     * Override value for `Camera3D.far`. Used internally when calculating depth of field. When
     * attached to a `Camera3D` as its `Camera3D.attributes`, it will override the `Camera3D.far`
     * property.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_far
     */
    fun getFar(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFarBind, handle)
    }

    /**
     * Returns the vertical field of view that corresponds to the `frustum_focal_length`. This value is
     * calculated internally whenever `frustum_focal_length` is changed.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_fov
     */
    fun getFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovBind, handle)
    }

    /**
     * The maximum luminance (in EV100) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing below a certain brightness, resulting in a cut off point where the
     * scene will remain bright.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_auto_exposure_max_exposure_value
     */
    fun setAutoExposureMaxExposureValue(exposureValueMax: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMaxExposureValueBind, handle, exposureValueMax)
    }

    /**
     * The maximum luminance (in EV100) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing below a certain brightness, resulting in a cut off point where the
     * scene will remain bright.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_auto_exposure_max_exposure_value
     */
    fun getAutoExposureMaxExposureValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoExposureMaxExposureValueBind, handle)
    }

    /**
     * The minimum luminance (in EV100) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing above a certain brightness, resulting in a cut off point where the
     * scene will remain dark.
     *
     * Generated from Godot docs: CameraAttributesPhysical.set_auto_exposure_min_exposure_value
     */
    fun setAutoExposureMinExposureValue(exposureValueMin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoExposureMinExposureValueBind, handle, exposureValueMin)
    }

    /**
     * The minimum luminance (in EV100) used when calculating auto exposure. When calculating scene
     * average luminance, color values will be clamped to at least this value. This limits the
     * auto-exposure from exposing above a certain brightness, resulting in a cut off point where the
     * scene will remain dark.
     *
     * Generated from Godot docs: CameraAttributesPhysical.get_auto_exposure_min_exposure_value
     */
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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: Camera3D
 */
open class Camera3D(handle: MemorySegment) : Node3D(handle) {
    var keepAspect: Long
        @JvmName("keepAspectProperty")
        get() = getKeepAspectMode()
        @JvmName("setKeepAspectProperty")
        set(value) = setKeepAspectMode(value)

    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    var hOffset: Double
        @JvmName("hOffsetProperty")
        get() = getHOffset()
        @JvmName("setHOffsetProperty")
        set(value) = setHOffset(value)

    var vOffset: Double
        @JvmName("vOffsetProperty")
        get() = getVOffset()
        @JvmName("setVOffsetProperty")
        set(value) = setVOffset(value)

    var dopplerTracking: Long
        @JvmName("dopplerTrackingProperty")
        get() = getDopplerTracking()
        @JvmName("setDopplerTrackingProperty")
        set(value) = setDopplerTracking(value)

    var projection: Long
        @JvmName("projectionProperty")
        get() = getProjection()
        @JvmName("setProjectionProperty")
        set(value) = setProjection(value)

    var current: Boolean
        @JvmName("currentProperty")
        get() = isCurrent()
        @JvmName("setCurrentProperty")
        set(value) = setCurrent(value)

    var fov: Double
        @JvmName("fovProperty")
        get() = getFov()
        @JvmName("setFovProperty")
        set(value) = setFov(value)

    var size: Double
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var frustumOffset: Vector2
        @JvmName("frustumOffsetProperty")
        get() = getFrustumOffset()
        @JvmName("setFrustumOffsetProperty")
        set(value) = setFrustumOffset(value)

    var near: Double
        @JvmName("nearProperty")
        get() = getNear()
        @JvmName("setNearProperty")
        set(value) = setNear(value)

    var far: Double
        @JvmName("farProperty")
        get() = getFar()
        @JvmName("setFarProperty")
        set(value) = setFar(value)

    fun projectRayNormal(screenPoint: Vector2): Vector3 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector3(projectRayNormalBind, handle, screenPoint)
    }

    fun projectLocalRayNormal(screenPoint: Vector2): Vector3 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector3(projectLocalRayNormalBind, handle, screenPoint)
    }

    fun projectRayOrigin(screenPoint: Vector2): Vector3 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector3(projectRayOriginBind, handle, screenPoint)
    }

    fun unprojectPosition(worldPoint: Vector3): Vector2 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector2(unprojectPositionBind, handle, worldPoint)
    }

    fun isPositionBehind(worldPoint: Vector3): Boolean {
        return ObjectCalls.ptrcallWithVector3ArgRetBool(isPositionBehindBind, handle, worldPoint)
    }

    fun projectPosition(screenPoint: Vector2, zDepth: Double): Vector3 {
        return ObjectCalls.ptrcallWithVector2AndDoubleArgRetVector3(projectPositionBind, handle, screenPoint, zDepth)
    }

    fun setPerspective(fov: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithThreeDoubleArgs(setPerspectiveBind, handle, fov, zNear, zFar)
    }

    fun setOrthogonal(size: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithThreeDoubleArgs(setOrthogonalBind, handle, size, zNear, zFar)
    }

    fun setFrustum(size: Double, offset: Vector2, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithDoubleVector2TwoDoubleArgs(setFrustumBind, handle, size, offset, zNear, zFar)
    }

    fun makeCurrent() {
        ObjectCalls.ptrcallNoArgs(makeCurrentBind, handle)
    }

    fun clearCurrent(enableNext: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(clearCurrentBind, handle, enableNext)
    }

    fun setCurrent(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCurrentBind, handle, enabled)
    }

    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, handle)
    }

    fun getFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovBind, handle)
    }

    fun getFrustumOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFrustumOffsetBind, handle)
    }

    fun getSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSizeBind, handle)
    }

    fun getFar(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFarBind, handle)
    }

    fun getNear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNearBind, handle)
    }

    fun setFov(fov: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFovBind, handle, fov)
    }

    fun setFrustumOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setFrustumOffsetBind, handle, offset)
    }

    fun setSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSizeBind, handle, size)
    }

    fun setFar(far: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFarBind, handle, far)
    }

    fun setNear(near: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNearBind, handle, near)
    }

    fun getProjection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProjectionBind, handle)
    }

    fun setProjection(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProjectionBind, handle, mode)
    }

    fun setHOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHOffsetBind, handle, offset)
    }

    fun getHOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHOffsetBind, handle)
    }

    fun setVOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVOffsetBind, handle, offset)
    }

    fun getVOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVOffsetBind, handle)
    }

    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, mask)
    }

    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    fun setKeepAspectMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setKeepAspectModeBind, handle, mode)
    }

    fun getKeepAspectMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeepAspectModeBind, handle)
    }

    fun setDopplerTracking(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDopplerTrackingBind, handle, mode)
    }

    fun getDopplerTracking(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDopplerTrackingBind, handle)
    }

    fun isPositionInFrustum(worldPoint: Vector3): Boolean {
        return ObjectCalls.ptrcallWithVector3ArgRetBool(isPositionInFrustumBind, handle, worldPoint)
    }

    fun setCullMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCullMaskValueBind, handle, layerNumber, value)
    }

    fun getCullMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCullMaskValueBind, handle, layerNumber)
    }

    companion object {
        const val PROJECTION_PERSPECTIVE: Long = 0L
        const val PROJECTION_ORTHOGONAL: Long = 1L
        const val PROJECTION_FRUSTUM: Long = 2L
        const val KEEP_WIDTH: Long = 0L
        const val KEEP_HEIGHT: Long = 1L
        const val DOPPLER_TRACKING_DISABLED: Long = 0L
        const val DOPPLER_TRACKING_IDLE_STEP: Long = 1L
        const val DOPPLER_TRACKING_PHYSICS_STEP: Long = 2L

        fun fromHandle(handle: MemorySegment): Camera3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Camera3D? =
            if (handle.address() == 0L) null else Camera3D(handle)

        private const val PROJECT_RAY_NORMAL_HASH = 1718073306L
        private val projectRayNormalBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_ray_normal", PROJECT_RAY_NORMAL_HASH)
        }

        private const val PROJECT_LOCAL_RAY_NORMAL_HASH = 1718073306L
        private val projectLocalRayNormalBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_local_ray_normal", PROJECT_LOCAL_RAY_NORMAL_HASH)
        }

        private const val PROJECT_RAY_ORIGIN_HASH = 1718073306L
        private val projectRayOriginBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_ray_origin", PROJECT_RAY_ORIGIN_HASH)
        }

        private const val UNPROJECT_POSITION_HASH = 3758901831L
        private val unprojectPositionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "unproject_position", UNPROJECT_POSITION_HASH)
        }

        private const val IS_POSITION_BEHIND_HASH = 3108956480L
        private val isPositionBehindBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "is_position_behind", IS_POSITION_BEHIND_HASH)
        }

        private const val PROJECT_POSITION_HASH = 2171975744L
        private val projectPositionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_position", PROJECT_POSITION_HASH)
        }

        private const val SET_PERSPECTIVE_HASH = 2385087082L
        private val setPerspectiveBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_perspective", SET_PERSPECTIVE_HASH)
        }

        private const val SET_ORTHOGONAL_HASH = 2385087082L
        private val setOrthogonalBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_orthogonal", SET_ORTHOGONAL_HASH)
        }

        private const val SET_FRUSTUM_HASH = 354890663L
        private val setFrustumBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_frustum", SET_FRUSTUM_HASH)
        }

        private const val MAKE_CURRENT_HASH = 3218959716L
        private val makeCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "make_current", MAKE_CURRENT_HASH)
        }

        private const val CLEAR_CURRENT_HASH = 3216645846L
        private val clearCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "clear_current", CLEAR_CURRENT_HASH)
        }

        private const val SET_CURRENT_HASH = 2586408642L
        private val setCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_current", SET_CURRENT_HASH)
        }

        private const val IS_CURRENT_HASH = 36873697L
        private val isCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "is_current", IS_CURRENT_HASH)
        }

        private const val GET_FOV_HASH = 1740695150L
        private val getFovBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_fov", GET_FOV_HASH)
        }

        private const val GET_FRUSTUM_OFFSET_HASH = 3341600327L
        private val getFrustumOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_frustum_offset", GET_FRUSTUM_OFFSET_HASH)
        }

        private const val GET_SIZE_HASH = 1740695150L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_size", GET_SIZE_HASH)
        }

        private const val GET_FAR_HASH = 1740695150L
        private val getFarBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_far", GET_FAR_HASH)
        }

        private const val GET_NEAR_HASH = 1740695150L
        private val getNearBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_near", GET_NEAR_HASH)
        }

        private const val SET_FOV_HASH = 373806689L
        private val setFovBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_fov", SET_FOV_HASH)
        }

        private const val SET_FRUSTUM_OFFSET_HASH = 743155724L
        private val setFrustumOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_frustum_offset", SET_FRUSTUM_OFFSET_HASH)
        }

        private const val SET_SIZE_HASH = 373806689L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_size", SET_SIZE_HASH)
        }

        private const val SET_FAR_HASH = 373806689L
        private val setFarBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_far", SET_FAR_HASH)
        }

        private const val SET_NEAR_HASH = 373806689L
        private val setNearBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_near", SET_NEAR_HASH)
        }

        private const val GET_PROJECTION_HASH = 2624185235L
        private val getProjectionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_projection", GET_PROJECTION_HASH)
        }

        private const val SET_PROJECTION_HASH = 4218540108L
        private val setProjectionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_projection", SET_PROJECTION_HASH)
        }

        private const val SET_H_OFFSET_HASH = 373806689L
        private val setHOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_h_offset", SET_H_OFFSET_HASH)
        }

        private const val GET_H_OFFSET_HASH = 1740695150L
        private val getHOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_h_offset", GET_H_OFFSET_HASH)
        }

        private const val SET_V_OFFSET_HASH = 373806689L
        private val setVOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_v_offset", SET_V_OFFSET_HASH)
        }

        private const val GET_V_OFFSET_HASH = 1740695150L
        private val getVOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_v_offset", GET_V_OFFSET_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_cull_mask", GET_CULL_MASK_HASH)
        }

        private const val SET_KEEP_ASPECT_MODE_HASH = 1740651252L
        private val setKeepAspectModeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_keep_aspect_mode", SET_KEEP_ASPECT_MODE_HASH)
        }

        private const val GET_KEEP_ASPECT_MODE_HASH = 2790278316L
        private val getKeepAspectModeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_keep_aspect_mode", GET_KEEP_ASPECT_MODE_HASH)
        }

        private const val SET_DOPPLER_TRACKING_HASH = 3109431270L
        private val setDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_doppler_tracking", SET_DOPPLER_TRACKING_HASH)
        }

        private const val GET_DOPPLER_TRACKING_HASH = 1584483649L
        private val getDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_doppler_tracking", GET_DOPPLER_TRACKING_HASH)
        }

        private const val IS_POSITION_IN_FRUSTUM_HASH = 3108956480L
        private val isPositionInFrustumBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "is_position_in_frustum", IS_POSITION_IN_FRUSTUM_HASH)
        }

        private const val SET_CULL_MASK_VALUE_HASH = 300928843L
        private val setCullMaskValueBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_cull_mask_value", SET_CULL_MASK_VALUE_HASH)
        }

        private const val GET_CULL_MASK_VALUE_HASH = 1116898809L
        private val getCullMaskValueBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_cull_mask_value", GET_CULL_MASK_VALUE_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: Area3D
 */
class Area3D(handle: MemorySegment) : CollisionObject3D(handle) {
    var monitoring: Boolean
        @JvmName("monitoringProperty")
        get() = isMonitoring()
        @JvmName("setMonitoringProperty")
        set(value) = setMonitoring(value)

    var monitorable: Boolean
        @JvmName("monitorableProperty")
        get() = isMonitorable()
        @JvmName("setMonitorableProperty")
        set(value) = setMonitorable(value)

    var priority: Int
        @JvmName("priorityProperty")
        get() = getPriority()
        @JvmName("setPriorityProperty")
        set(value) = setPriority(value)

    var gravitySpaceOverride: Long
        @JvmName("gravitySpaceOverrideProperty")
        get() = getGravitySpaceOverrideMode()
        @JvmName("setGravitySpaceOverrideProperty")
        set(value) = setGravitySpaceOverrideMode(value)

    var gravityPoint: Boolean
        @JvmName("gravityPointProperty")
        get() = isGravityAPoint()
        @JvmName("setGravityPointProperty")
        set(value) = setGravityIsPoint(value)

    var gravityPointUnitDistance: Double
        @JvmName("gravityPointUnitDistanceProperty")
        get() = getGravityPointUnitDistance()
        @JvmName("setGravityPointUnitDistanceProperty")
        set(value) = setGravityPointUnitDistance(value)

    var gravityPointCenter: Vector3
        @JvmName("gravityPointCenterProperty")
        get() = getGravityPointCenter()
        @JvmName("setGravityPointCenterProperty")
        set(value) = setGravityPointCenter(value)

    var gravityDirection: Vector3
        @JvmName("gravityDirectionProperty")
        get() = getGravityDirection()
        @JvmName("setGravityDirectionProperty")
        set(value) = setGravityDirection(value)

    var gravity: Double
        @JvmName("gravityProperty")
        get() = getGravity()
        @JvmName("setGravityProperty")
        set(value) = setGravity(value)

    var linearDampSpaceOverride: Long
        @JvmName("linearDampSpaceOverrideProperty")
        get() = getLinearDampSpaceOverrideMode()
        @JvmName("setLinearDampSpaceOverrideProperty")
        set(value) = setLinearDampSpaceOverrideMode(value)

    var linearDamp: Double
        @JvmName("linearDampProperty")
        get() = getLinearDamp()
        @JvmName("setLinearDampProperty")
        set(value) = setLinearDamp(value)

    var angularDampSpaceOverride: Long
        @JvmName("angularDampSpaceOverrideProperty")
        get() = getAngularDampSpaceOverrideMode()
        @JvmName("setAngularDampSpaceOverrideProperty")
        set(value) = setAngularDampSpaceOverrideMode(value)

    var angularDamp: Double
        @JvmName("angularDampProperty")
        get() = getAngularDamp()
        @JvmName("setAngularDampProperty")
        set(value) = setAngularDamp(value)

    var windForceMagnitude: Double
        @JvmName("windForceMagnitudeProperty")
        get() = getWindForceMagnitude()
        @JvmName("setWindForceMagnitudeProperty")
        set(value) = setWindForceMagnitude(value)

    var windAttenuationFactor: Double
        @JvmName("windAttenuationFactorProperty")
        get() = getWindAttenuationFactor()
        @JvmName("setWindAttenuationFactorProperty")
        set(value) = setWindAttenuationFactor(value)

    var audioBusOverride: Boolean
        @JvmName("audioBusOverrideProperty")
        get() = isOverridingAudioBus()
        @JvmName("setAudioBusOverrideProperty")
        set(value) = setAudioBusOverride(value)

    var reverbBusEnabled: Boolean
        @JvmName("reverbBusEnabledProperty")
        get() = isUsingReverbBus()
        @JvmName("setReverbBusEnabledProperty")
        set(value) = setUseReverbBus(value)

    var reverbBusAmount: Double
        @JvmName("reverbBusAmountProperty")
        get() = getReverbAmount()
        @JvmName("setReverbBusAmountProperty")
        set(value) = setReverbAmount(value)

    var reverbBusUniformity: Double
        @JvmName("reverbBusUniformityProperty")
        get() = getReverbUniformity()
        @JvmName("setReverbBusUniformityProperty")
        set(value) = setReverbUniformity(value)

    fun setGravitySpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGravitySpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    fun getGravitySpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGravitySpaceOverrideModeBind, handle)
    }

    fun setGravityIsPoint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGravityIsPointBind, handle, enable)
    }

    fun isGravityAPoint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGravityAPointBind, handle)
    }

    fun setGravityPointUnitDistance(distanceScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityPointUnitDistanceBind, handle, distanceScale)
    }

    fun getGravityPointUnitDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityPointUnitDistanceBind, handle)
    }

    fun setGravityPointCenter(center: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityPointCenterBind, handle, center)
    }

    fun getGravityPointCenter(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityPointCenterBind, handle)
    }

    fun setGravityDirection(direction: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityDirectionBind, handle, direction)
    }

    fun getGravityDirection(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityDirectionBind, handle)
    }

    fun setGravity(gravity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityBind, handle, gravity)
    }

    fun getGravity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityBind, handle)
    }

    fun setLinearDampSpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLinearDampSpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    fun getLinearDampSpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLinearDampSpaceOverrideModeBind, handle)
    }

    fun setAngularDampSpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAngularDampSpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    fun getAngularDampSpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAngularDampSpaceOverrideModeBind, handle)
    }

    fun setAngularDamp(angularDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularDampBind, handle, angularDamp)
    }

    fun getAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularDampBind, handle)
    }

    fun setLinearDamp(linearDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLinearDampBind, handle, linearDamp)
    }

    fun getLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLinearDampBind, handle)
    }

    fun setPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setPriorityBind, handle, priority)
    }

    fun getPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPriorityBind, handle)
    }

    fun setWindForceMagnitude(windForceMagnitude: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWindForceMagnitudeBind, handle, windForceMagnitude)
    }

    fun getWindForceMagnitude(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWindForceMagnitudeBind, handle)
    }

    fun setWindAttenuationFactor(windAttenuationFactor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWindAttenuationFactorBind, handle, windAttenuationFactor)
    }

    fun getWindAttenuationFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWindAttenuationFactorBind, handle)
    }

    fun setMonitorable(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitorableBind, handle, enable)
    }

    fun isMonitorable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitorableBind, handle)
    }

    fun setMonitoring(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitoringBind, handle, enable)
    }

    fun isMonitoring(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitoringBind, handle)
    }

    fun hasOverlappingBodies(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasOverlappingBodiesBind, handle)
    }

    fun hasOverlappingAreas(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasOverlappingAreasBind, handle)
    }

    fun overlapsBody(body: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(overlapsBodyBind, handle, body.handle)
    }

    fun overlapsArea(area: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(overlapsAreaBind, handle, area.handle)
    }

    fun setAudioBusOverride(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAudioBusOverrideBind, handle, enable)
    }

    fun isOverridingAudioBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOverridingAudioBusBind, handle)
    }

    fun setAudioBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAudioBusNameBind, handle, name)
    }

    fun setUseReverbBus(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseReverbBusBind, handle, enable)
    }

    fun isUsingReverbBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingReverbBusBind, handle)
    }

    fun setReverbBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setReverbBusNameBind, handle, name)
    }

    fun setReverbAmount(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setReverbAmountBind, handle, amount)
    }

    fun getReverbAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getReverbAmountBind, handle)
    }

    fun setReverbUniformity(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setReverbUniformityBind, handle, amount)
    }

    fun getReverbUniformity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getReverbUniformityBind, handle)
    }

    object Signals {
        const val bodyShapeEntered: String = "body_shape_entered"
        const val bodyShapeExited: String = "body_shape_exited"
        const val bodyEntered: String = "body_entered"
        const val bodyExited: String = "body_exited"
        const val areaShapeEntered: String = "area_shape_entered"
        const val areaShapeExited: String = "area_shape_exited"
        const val areaEntered: String = "area_entered"
        const val areaExited: String = "area_exited"
    }

    companion object {
        const val SPACE_OVERRIDE_DISABLED: Long = 0L
        const val SPACE_OVERRIDE_COMBINE: Long = 1L
        const val SPACE_OVERRIDE_COMBINE_REPLACE: Long = 2L
        const val SPACE_OVERRIDE_REPLACE: Long = 3L
        const val SPACE_OVERRIDE_REPLACE_COMBINE: Long = 4L

        fun fromHandle(handle: MemorySegment): Area3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Area3D? =
            if (handle.address() == 0L) null else Area3D(handle)

        private const val SET_GRAVITY_SPACE_OVERRIDE_MODE_HASH = 2311433571L
        private val setGravitySpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_gravity_space_override_mode", SET_GRAVITY_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val GET_GRAVITY_SPACE_OVERRIDE_MODE_HASH = 958191869L
        private val getGravitySpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_gravity_space_override_mode", GET_GRAVITY_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val SET_GRAVITY_IS_POINT_HASH = 2586408642L
        private val setGravityIsPointBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_gravity_is_point", SET_GRAVITY_IS_POINT_HASH)
        }

        private const val IS_GRAVITY_A_POINT_HASH = 36873697L
        private val isGravityAPointBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "is_gravity_a_point", IS_GRAVITY_A_POINT_HASH)
        }

        private const val SET_GRAVITY_POINT_UNIT_DISTANCE_HASH = 373806689L
        private val setGravityPointUnitDistanceBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_gravity_point_unit_distance", SET_GRAVITY_POINT_UNIT_DISTANCE_HASH)
        }

        private const val GET_GRAVITY_POINT_UNIT_DISTANCE_HASH = 1740695150L
        private val getGravityPointUnitDistanceBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_gravity_point_unit_distance", GET_GRAVITY_POINT_UNIT_DISTANCE_HASH)
        }

        private const val SET_GRAVITY_POINT_CENTER_HASH = 3460891852L
        private val setGravityPointCenterBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_gravity_point_center", SET_GRAVITY_POINT_CENTER_HASH)
        }

        private const val GET_GRAVITY_POINT_CENTER_HASH = 3360562783L
        private val getGravityPointCenterBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_gravity_point_center", GET_GRAVITY_POINT_CENTER_HASH)
        }

        private const val SET_GRAVITY_DIRECTION_HASH = 3460891852L
        private val setGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_gravity_direction", SET_GRAVITY_DIRECTION_HASH)
        }

        private const val GET_GRAVITY_DIRECTION_HASH = 3360562783L
        private val getGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_gravity_direction", GET_GRAVITY_DIRECTION_HASH)
        }

        private const val SET_GRAVITY_HASH = 373806689L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val GET_GRAVITY_HASH = 1740695150L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 2311433571L
        private val setLinearDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_linear_damp_space_override_mode", SET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val GET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 958191869L
        private val getLinearDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_linear_damp_space_override_mode", GET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val SET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 2311433571L
        private val setAngularDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_angular_damp_space_override_mode", SET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val GET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 958191869L
        private val getAngularDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_angular_damp_space_override_mode", GET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val SET_ANGULAR_DAMP_HASH = 373806689L
        private val setAngularDampBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_angular_damp", SET_ANGULAR_DAMP_HASH)
        }

        private const val GET_ANGULAR_DAMP_HASH = 1740695150L
        private val getAngularDampBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_angular_damp", GET_ANGULAR_DAMP_HASH)
        }

        private const val SET_LINEAR_DAMP_HASH = 373806689L
        private val setLinearDampBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_linear_damp", SET_LINEAR_DAMP_HASH)
        }

        private const val GET_LINEAR_DAMP_HASH = 1740695150L
        private val getLinearDampBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_linear_damp", GET_LINEAR_DAMP_HASH)
        }

        private const val SET_PRIORITY_HASH = 1286410249L
        private val setPriorityBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_priority", SET_PRIORITY_HASH)
        }

        private const val GET_PRIORITY_HASH = 3905245786L
        private val getPriorityBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_priority", GET_PRIORITY_HASH)
        }

        private const val SET_WIND_FORCE_MAGNITUDE_HASH = 373806689L
        private val setWindForceMagnitudeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_wind_force_magnitude", SET_WIND_FORCE_MAGNITUDE_HASH)
        }

        private const val GET_WIND_FORCE_MAGNITUDE_HASH = 1740695150L
        private val getWindForceMagnitudeBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_wind_force_magnitude", GET_WIND_FORCE_MAGNITUDE_HASH)
        }

        private const val SET_WIND_ATTENUATION_FACTOR_HASH = 373806689L
        private val setWindAttenuationFactorBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_wind_attenuation_factor", SET_WIND_ATTENUATION_FACTOR_HASH)
        }

        private const val GET_WIND_ATTENUATION_FACTOR_HASH = 1740695150L
        private val getWindAttenuationFactorBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_wind_attenuation_factor", GET_WIND_ATTENUATION_FACTOR_HASH)
        }

        private const val SET_MONITORABLE_HASH = 2586408642L
        private val setMonitorableBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_monitorable", SET_MONITORABLE_HASH)
        }

        private const val IS_MONITORABLE_HASH = 36873697L
        private val isMonitorableBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "is_monitorable", IS_MONITORABLE_HASH)
        }

        private const val SET_MONITORING_HASH = 2586408642L
        private val setMonitoringBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_monitoring", SET_MONITORING_HASH)
        }

        private const val IS_MONITORING_HASH = 36873697L
        private val isMonitoringBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "is_monitoring", IS_MONITORING_HASH)
        }

        private const val HAS_OVERLAPPING_BODIES_HASH = 36873697L
        private val hasOverlappingBodiesBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "has_overlapping_bodies", HAS_OVERLAPPING_BODIES_HASH)
        }

        private const val HAS_OVERLAPPING_AREAS_HASH = 36873697L
        private val hasOverlappingAreasBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "has_overlapping_areas", HAS_OVERLAPPING_AREAS_HASH)
        }

        private const val OVERLAPS_BODY_HASH = 3093956946L
        private val overlapsBodyBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "overlaps_body", OVERLAPS_BODY_HASH)
        }

        private const val OVERLAPS_AREA_HASH = 3093956946L
        private val overlapsAreaBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "overlaps_area", OVERLAPS_AREA_HASH)
        }

        private const val SET_AUDIO_BUS_OVERRIDE_HASH = 2586408642L
        private val setAudioBusOverrideBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_audio_bus_override", SET_AUDIO_BUS_OVERRIDE_HASH)
        }

        private const val IS_OVERRIDING_AUDIO_BUS_HASH = 36873697L
        private val isOverridingAudioBusBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "is_overriding_audio_bus", IS_OVERRIDING_AUDIO_BUS_HASH)
        }

        private const val SET_AUDIO_BUS_NAME_HASH = 3304788590L
        private val setAudioBusNameBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_audio_bus_name", SET_AUDIO_BUS_NAME_HASH)
        }

        private const val SET_USE_REVERB_BUS_HASH = 2586408642L
        private val setUseReverbBusBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_use_reverb_bus", SET_USE_REVERB_BUS_HASH)
        }

        private const val IS_USING_REVERB_BUS_HASH = 36873697L
        private val isUsingReverbBusBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "is_using_reverb_bus", IS_USING_REVERB_BUS_HASH)
        }

        private const val SET_REVERB_BUS_NAME_HASH = 3304788590L
        private val setReverbBusNameBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_reverb_bus_name", SET_REVERB_BUS_NAME_HASH)
        }

        private const val SET_REVERB_AMOUNT_HASH = 373806689L
        private val setReverbAmountBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_reverb_amount", SET_REVERB_AMOUNT_HASH)
        }

        private const val GET_REVERB_AMOUNT_HASH = 1740695150L
        private val getReverbAmountBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_reverb_amount", GET_REVERB_AMOUNT_HASH)
        }

        private const val SET_REVERB_UNIFORMITY_HASH = 373806689L
        private val setReverbUniformityBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_reverb_uniformity", SET_REVERB_UNIFORMITY_HASH)
        }

        private const val GET_REVERB_UNIFORMITY_HASH = 1740695150L
        private val getReverbUniformityBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_reverb_uniformity", GET_REVERB_UNIFORMITY_HASH)
        }
    }
}

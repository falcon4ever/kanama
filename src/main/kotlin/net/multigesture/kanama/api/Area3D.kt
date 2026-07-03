package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3

/**
 * A region of 3D space that detects other `CollisionObject3D`s entering or exiting it.
 *
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

    var windSourcePath: NodePath
        @JvmName("windSourcePathProperty")
        get() = getWindSourcePath()
        @JvmName("setWindSourcePathProperty")
        set(value) = setWindSourcePath(value)

    var audioBusOverride: Boolean
        @JvmName("audioBusOverrideProperty")
        get() = isOverridingAudioBus()
        @JvmName("setAudioBusOverrideProperty")
        set(value) = setAudioBusOverride(value)

    var audioBusName: String
        @JvmName("audioBusNameProperty")
        get() = getAudioBusName()
        @JvmName("setAudioBusNameProperty")
        set(value) = setAudioBusName(value)

    var reverbBusEnabled: Boolean
        @JvmName("reverbBusEnabledProperty")
        get() = isUsingReverbBus()
        @JvmName("setReverbBusEnabledProperty")
        set(value) = setUseReverbBus(value)

    var reverbBusName: String
        @JvmName("reverbBusNameProperty")
        get() = getReverbBusName()
        @JvmName("setReverbBusNameProperty")
        set(value) = setReverbBusName(value)

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

    /**
     * Override mode for gravity calculations within this area.
     *
     * Generated from Godot docs: Area3D.set_gravity_space_override_mode
     */
    fun setGravitySpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGravitySpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    /**
     * Override mode for gravity calculations within this area.
     *
     * Generated from Godot docs: Area3D.get_gravity_space_override_mode
     */
    fun getGravitySpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGravitySpaceOverrideModeBind, handle)
    }

    /**
     * If `true`, gravity is calculated from a point (set via `gravity_point_center`). See also
     * `gravity_space_override`.
     *
     * Generated from Godot docs: Area3D.set_gravity_is_point
     */
    fun setGravityIsPoint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGravityIsPointBind, handle, enable)
    }

    /**
     * If `true`, gravity is calculated from a point (set via `gravity_point_center`). See also
     * `gravity_space_override`.
     *
     * Generated from Godot docs: Area3D.is_gravity_a_point
     */
    fun isGravityAPoint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGravityAPointBind, handle)
    }

    /**
     * The distance at which the gravity strength is equal to `gravity`. For example, on a planet 100
     * meters in radius with a surface gravity of 4.0 m/s², set the `gravity` to 4.0 and the unit
     * distance to 100.0. The gravity will have falloff according to the inverse square law, so in the
     * example, at 200 meters from the center the gravity will be 1.0 m/s² (twice the distance, 1/4th
     * the gravity), at 50 meters it will be 16.0 m/s² (half the distance, 4x the gravity), and so on.
     * The above is true only when the unit distance is a positive number. When this is set to 0.0, the
     * gravity will be constant regardless of distance.
     *
     * Generated from Godot docs: Area3D.set_gravity_point_unit_distance
     */
    fun setGravityPointUnitDistance(distanceScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityPointUnitDistanceBind, handle, distanceScale)
    }

    /**
     * The distance at which the gravity strength is equal to `gravity`. For example, on a planet 100
     * meters in radius with a surface gravity of 4.0 m/s², set the `gravity` to 4.0 and the unit
     * distance to 100.0. The gravity will have falloff according to the inverse square law, so in the
     * example, at 200 meters from the center the gravity will be 1.0 m/s² (twice the distance, 1/4th
     * the gravity), at 50 meters it will be 16.0 m/s² (half the distance, 4x the gravity), and so on.
     * The above is true only when the unit distance is a positive number. When this is set to 0.0, the
     * gravity will be constant regardless of distance.
     *
     * Generated from Godot docs: Area3D.get_gravity_point_unit_distance
     */
    fun getGravityPointUnitDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityPointUnitDistanceBind, handle)
    }

    /**
     * If gravity is a point (see `gravity_point`), this will be the point of attraction.
     *
     * Generated from Godot docs: Area3D.set_gravity_point_center
     */
    fun setGravityPointCenter(center: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityPointCenterBind, handle, center)
    }

    /**
     * If gravity is a point (see `gravity_point`), this will be the point of attraction.
     *
     * Generated from Godot docs: Area3D.get_gravity_point_center
     */
    fun getGravityPointCenter(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityPointCenterBind, handle)
    }

    /**
     * The area's gravity vector (not normalized).
     *
     * Generated from Godot docs: Area3D.set_gravity_direction
     */
    fun setGravityDirection(direction: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityDirectionBind, handle, direction)
    }

    /**
     * The area's gravity vector (not normalized).
     *
     * Generated from Godot docs: Area3D.get_gravity_direction
     */
    fun getGravityDirection(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityDirectionBind, handle)
    }

    /**
     * The area's gravity intensity (in meters per second squared). This value multiplies the gravity
     * direction. This is useful to alter the force of gravity without altering its direction.
     *
     * Generated from Godot docs: Area3D.set_gravity
     */
    fun setGravity(gravity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityBind, handle, gravity)
    }

    /**
     * The area's gravity intensity (in meters per second squared). This value multiplies the gravity
     * direction. This is useful to alter the force of gravity without altering its direction.
     *
     * Generated from Godot docs: Area3D.get_gravity
     */
    fun getGravity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityBind, handle)
    }

    /**
     * Override mode for linear damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.set_linear_damp_space_override_mode
     */
    fun setLinearDampSpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLinearDampSpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    /**
     * Override mode for linear damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.get_linear_damp_space_override_mode
     */
    fun getLinearDampSpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLinearDampSpaceOverrideModeBind, handle)
    }

    /**
     * Override mode for angular damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.set_angular_damp_space_override_mode
     */
    fun setAngularDampSpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAngularDampSpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    /**
     * Override mode for angular damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.get_angular_damp_space_override_mode
     */
    fun getAngularDampSpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAngularDampSpaceOverrideModeBind, handle)
    }

    /**
     * The rate at which objects stop spinning in this area. Represents the angular velocity lost per
     * second. See `ProjectSettings.physics/3d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.set_angular_damp
     */
    fun setAngularDamp(angularDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularDampBind, handle, angularDamp)
    }

    /**
     * The rate at which objects stop spinning in this area. Represents the angular velocity lost per
     * second. See `ProjectSettings.physics/3d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.get_angular_damp
     */
    fun getAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularDampBind, handle)
    }

    /**
     * The rate at which objects stop moving in this area. Represents the linear velocity lost per
     * second. See `ProjectSettings.physics/3d/default_linear_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.set_linear_damp
     */
    fun setLinearDamp(linearDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLinearDampBind, handle, linearDamp)
    }

    /**
     * The rate at which objects stop moving in this area. Represents the linear velocity lost per
     * second. See `ProjectSettings.physics/3d/default_linear_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.get_linear_damp
     */
    fun getLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLinearDampBind, handle)
    }

    /**
     * The area's priority. Higher priority areas are processed first. The `World3D`'s physics is
     * always processed last, after all areas.
     *
     * Generated from Godot docs: Area3D.set_priority
     */
    fun setPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setPriorityBind, handle, priority)
    }

    /**
     * The area's priority. Higher priority areas are processed first. The `World3D`'s physics is
     * always processed last, after all areas.
     *
     * Generated from Godot docs: Area3D.get_priority
     */
    fun getPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPriorityBind, handle)
    }

    /**
     * The magnitude of area-specific wind force. Note: This wind force only applies to `SoftBody3D`
     * nodes. Other physics bodies are currently not affected by wind.
     *
     * Generated from Godot docs: Area3D.set_wind_force_magnitude
     */
    fun setWindForceMagnitude(windForceMagnitude: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWindForceMagnitudeBind, handle, windForceMagnitude)
    }

    /**
     * The magnitude of area-specific wind force. Note: This wind force only applies to `SoftBody3D`
     * nodes. Other physics bodies are currently not affected by wind.
     *
     * Generated from Godot docs: Area3D.get_wind_force_magnitude
     */
    fun getWindForceMagnitude(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWindForceMagnitudeBind, handle)
    }

    /**
     * The exponential rate at which wind force decreases with distance from its origin. Note: This
     * wind force only applies to `SoftBody3D` nodes. Other physics bodies are currently not affected
     * by wind.
     *
     * Generated from Godot docs: Area3D.set_wind_attenuation_factor
     */
    fun setWindAttenuationFactor(windAttenuationFactor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWindAttenuationFactorBind, handle, windAttenuationFactor)
    }

    /**
     * The exponential rate at which wind force decreases with distance from its origin. Note: This
     * wind force only applies to `SoftBody3D` nodes. Other physics bodies are currently not affected
     * by wind.
     *
     * Generated from Godot docs: Area3D.get_wind_attenuation_factor
     */
    fun getWindAttenuationFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWindAttenuationFactorBind, handle)
    }

    /**
     * The `Node3D` which is used to specify the direction and origin of an area-specific wind force.
     * The direction is opposite to the z-axis of the `Node3D`'s local transform, and its origin is the
     * origin of the `Node3D`'s local transform. Note: This wind force only applies to `SoftBody3D`
     * nodes. Other physics bodies are currently not affected by wind.
     *
     * Generated from Godot docs: Area3D.set_wind_source_path
     */
    fun setWindSourcePath(windSourcePath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setWindSourcePathBind, handle, windSourcePath)
    }

    /**
     * The `Node3D` which is used to specify the direction and origin of an area-specific wind force.
     * The direction is opposite to the z-axis of the `Node3D`'s local transform, and its origin is the
     * origin of the `Node3D`'s local transform. Note: This wind force only applies to `SoftBody3D`
     * nodes. Other physics bodies are currently not affected by wind.
     *
     * Generated from Godot docs: Area3D.get_wind_source_path
     */
    fun getWindSourcePath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getWindSourcePathBind, handle)
    }

    /**
     * If `true`, other monitoring areas can detect this area.
     *
     * Generated from Godot docs: Area3D.set_monitorable
     */
    fun setMonitorable(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitorableBind, handle, enable)
    }

    /**
     * If `true`, other monitoring areas can detect this area.
     *
     * Generated from Godot docs: Area3D.is_monitorable
     */
    fun isMonitorable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitorableBind, handle)
    }

    /**
     * If `true`, the area detects bodies or areas entering and exiting it.
     *
     * Generated from Godot docs: Area3D.set_monitoring
     */
    fun setMonitoring(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitoringBind, handle, enable)
    }

    /**
     * If `true`, the area detects bodies or areas entering and exiting it.
     *
     * Generated from Godot docs: Area3D.is_monitoring
     */
    fun isMonitoring(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitoringBind, handle)
    }

    /**
     * Returns a list of intersecting `PhysicsBody3D`s, `SoftBody3D`s, and `GridMap`s. The overlapping
     * body's `CollisionObject3D.collision_layer` must be part of this area's
     * `CollisionObject3D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) this list is modified once during the physics step, not
     * immediately after objects are moved. Consider using signals instead. Note: Godot Physics does
     * not support reporting overlaps with `SoftBody3D`, so will not return any such bodies.
     *
     * Generated from Godot docs: Area3D.get_overlapping_bodies
     */
    fun getOverlappingBodies(): List<Node3D> {
        return ObjectCalls.ptrcallNoArgsRetTypedNode3DList(getOverlappingBodiesBind, handle)
    }

    /**
     * Returns a list of intersecting `Area3D`s. The overlapping area's
     * `CollisionObject3D.collision_layer` must be part of this area's
     * `CollisionObject3D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) this list is modified once during the physics step, not
     * immediately after objects are moved. Consider using signals instead.
     *
     * Generated from Godot docs: Area3D.get_overlapping_areas
     */
    fun getOverlappingAreas(): List<Area3D> {
        return ObjectCalls.ptrcallNoArgsRetTypedArea3DList(getOverlappingAreasBind, handle)
    }

    /**
     * Returns `true` if intersecting any `PhysicsBody3D`s, `SoftBody3D`s, or `GridMap`s, otherwise
     * returns `false`. The overlapping body's `CollisionObject3D.collision_layer` must be part of this
     * area's `CollisionObject3D.collision_mask` in order to be detected. For performance reasons
     * (collisions are all processed at the same time) the list of overlapping bodies is modified once
     * during the physics step, not immediately after objects are moved. Consider using signals
     * instead. Note: Godot Physics does not support reporting overlaps with `SoftBody3D`, so will not
     * consider such bodies.
     *
     * Generated from Godot docs: Area3D.has_overlapping_bodies
     */
    fun hasOverlappingBodies(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasOverlappingBodiesBind, handle)
    }

    /**
     * Returns `true` if intersecting any `Area3D`s, otherwise returns `false`. The overlapping area's
     * `CollisionObject3D.collision_layer` must be part of this area's
     * `CollisionObject3D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) the list of overlapping areas is modified once during the
     * physics step, not immediately after objects are moved. Consider using signals instead.
     *
     * Generated from Godot docs: Area3D.has_overlapping_areas
     */
    fun hasOverlappingAreas(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasOverlappingAreasBind, handle)
    }

    /**
     * Returns `true` if the given physics body intersects or overlaps this `Area3D`, `false`
     * otherwise. `body` argument can either be a `PhysicsBody3D`, `SoftBody3D`, or a `GridMap`
     * instance. While GridMaps are not physics body themselves, they register their tiles with
     * collision shapes as a virtual physics body. Note: The result of this test is not immediate after
     * moving objects. For performance, list of overlaps is updated once per frame and before the
     * physics step. Consider using signals instead. Note: Godot Physics does not support reporting
     * overlaps with `SoftBody3D`, so will return `false` in such cases.
     *
     * Generated from Godot docs: Area3D.overlaps_body
     */
    fun overlapsBody(body: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(overlapsBodyBind, handle, body.handle)
    }

    /**
     * Returns `true` if the given `Area3D` intersects or overlaps this `Area3D`, `false` otherwise.
     * Note: The result of this test is not immediate after moving objects. For performance, list of
     * overlaps is updated once per frame and before the physics step. Consider using signals instead.
     *
     * Generated from Godot docs: Area3D.overlaps_area
     */
    fun overlapsArea(area: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(overlapsAreaBind, handle, area.handle)
    }

    /**
     * If `true`, the area's audio bus overrides the default audio bus.
     *
     * Generated from Godot docs: Area3D.set_audio_bus_override
     */
    fun setAudioBusOverride(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAudioBusOverrideBind, handle, enable)
    }

    /**
     * If `true`, the area's audio bus overrides the default audio bus.
     *
     * Generated from Godot docs: Area3D.is_overriding_audio_bus
     */
    fun isOverridingAudioBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOverridingAudioBusBind, handle)
    }

    /**
     * The name of the area's audio bus.
     *
     * Generated from Godot docs: Area3D.set_audio_bus_name
     */
    fun setAudioBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAudioBusNameBind, handle, name)
    }

    /**
     * The name of the area's audio bus.
     *
     * Generated from Godot docs: Area3D.get_audio_bus_name
     */
    fun getAudioBusName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getAudioBusNameBind, handle)
    }

    /**
     * If `true`, the area applies reverb to its associated audio.
     *
     * Generated from Godot docs: Area3D.set_use_reverb_bus
     */
    fun setUseReverbBus(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseReverbBusBind, handle, enable)
    }

    /**
     * If `true`, the area applies reverb to its associated audio.
     *
     * Generated from Godot docs: Area3D.is_using_reverb_bus
     */
    fun isUsingReverbBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingReverbBusBind, handle)
    }

    /**
     * The name of the reverb bus to use for this area's associated audio.
     *
     * Generated from Godot docs: Area3D.set_reverb_bus_name
     */
    fun setReverbBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setReverbBusNameBind, handle, name)
    }

    /**
     * The name of the reverb bus to use for this area's associated audio.
     *
     * Generated from Godot docs: Area3D.get_reverb_bus_name
     */
    fun getReverbBusName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getReverbBusNameBind, handle)
    }

    /**
     * The degree to which this area applies reverb to its associated audio. Ranges from `0` to `1`
     * with `0.1` precision.
     *
     * Generated from Godot docs: Area3D.set_reverb_amount
     */
    fun setReverbAmount(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setReverbAmountBind, handle, amount)
    }

    /**
     * The degree to which this area applies reverb to its associated audio. Ranges from `0` to `1`
     * with `0.1` precision.
     *
     * Generated from Godot docs: Area3D.get_reverb_amount
     */
    fun getReverbAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getReverbAmountBind, handle)
    }

    /**
     * The degree to which this area's reverb is a uniform effect. Ranges from `0` to `1` with `0.1`
     * precision.
     *
     * Generated from Godot docs: Area3D.set_reverb_uniformity
     */
    fun setReverbUniformity(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setReverbUniformityBind, handle, amount)
    }

    /**
     * The degree to which this area's reverb is a uniform effect. Ranges from `0` to `1` with `0.1`
     * precision.
     *
     * Generated from Godot docs: Area3D.get_reverb_uniformity
     */
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

        @JvmStatic
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

        private const val SET_WIND_SOURCE_PATH_HASH = 1348162250L
        private val setWindSourcePathBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "set_wind_source_path", SET_WIND_SOURCE_PATH_HASH)
        }

        private const val GET_WIND_SOURCE_PATH_HASH = 4075236667L
        private val getWindSourcePathBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_wind_source_path", GET_WIND_SOURCE_PATH_HASH)
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

        private const val GET_OVERLAPPING_BODIES_HASH = 3995934104L
        private val getOverlappingBodiesBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_overlapping_bodies", GET_OVERLAPPING_BODIES_HASH)
        }

        private const val GET_OVERLAPPING_AREAS_HASH = 3995934104L
        private val getOverlappingAreasBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_overlapping_areas", GET_OVERLAPPING_AREAS_HASH)
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

        private const val GET_AUDIO_BUS_NAME_HASH = 2002593661L
        private val getAudioBusNameBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_audio_bus_name", GET_AUDIO_BUS_NAME_HASH)
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

        private const val GET_REVERB_BUS_NAME_HASH = 2002593661L
        private val getReverbBusNameBind by lazy {
            ObjectCalls.getMethodBind("Area3D", "get_reverb_bus_name", GET_REVERB_BUS_NAME_HASH)
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

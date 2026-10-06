package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A region of 3D space that detects other `CollisionObject3D`s entering or exiting it.
 *
 * Generated from Godot docs: Area3D
 */
class Area3D(handle: GodotHandle) : CollisionObject3D(handle) {
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

    var gravitySpaceOverride: Area3D.SpaceOverride
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

    var linearDampSpaceOverride: Area3D.SpaceOverride
        @JvmName("linearDampSpaceOverrideProperty")
        get() = getLinearDampSpaceOverrideMode()
        @JvmName("setLinearDampSpaceOverrideProperty")
        set(value) = setLinearDampSpaceOverrideMode(value)

    var linearDamp: Double
        @JvmName("linearDampProperty")
        get() = getLinearDamp()
        @JvmName("setLinearDampProperty")
        set(value) = setLinearDamp(value)

    var angularDampSpaceOverride: Area3D.SpaceOverride
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
    fun setGravitySpaceOverrideMode(spaceOverrideMode: Area3D.SpaceOverride) {
        ObjectCalls.ptrcallWithLongArg(Binds.setGravitySpaceOverrideModeBind, segment, spaceOverrideMode.value)
    }

    /**
     * Override mode for gravity calculations within this area.
     *
     * Generated from Godot docs: Area3D.get_gravity_space_override_mode
     */
    fun getGravitySpaceOverrideMode(): Area3D.SpaceOverride {
        return Area3D.SpaceOverride(ObjectCalls.ptrcallNoArgsRetLong(Binds.getGravitySpaceOverrideModeBind, segment))
    }

    /**
     * If `true`, gravity is calculated from a point (set via `gravity_point_center`). See also
     * `gravity_space_override`.
     *
     * Generated from Godot docs: Area3D.set_gravity_is_point
     */
    fun setGravityIsPoint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setGravityIsPointBind, segment, enable)
    }

    /**
     * If `true`, gravity is calculated from a point (set via `gravity_point_center`). See also
     * `gravity_space_override`.
     *
     * Generated from Godot docs: Area3D.is_gravity_a_point
     */
    fun isGravityAPoint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isGravityAPointBind, segment)
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
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGravityPointUnitDistanceBind, segment, distanceScale)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGravityPointUnitDistanceBind, segment)
    }

    /**
     * If gravity is a point (see `gravity_point`), this will be the point of attraction.
     *
     * Generated from Godot docs: Area3D.set_gravity_point_center
     */
    fun setGravityPointCenter(center: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setGravityPointCenterBind, segment, center)
    }

    /**
     * If gravity is a point (see `gravity_point`), this will be the point of attraction.
     *
     * Generated from Godot docs: Area3D.get_gravity_point_center
     */
    fun getGravityPointCenter(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getGravityPointCenterBind, segment)
    }

    /**
     * The area's gravity vector (not normalized).
     *
     * Generated from Godot docs: Area3D.set_gravity_direction
     */
    fun setGravityDirection(direction: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setGravityDirectionBind, segment, direction)
    }

    /**
     * The area's gravity vector (not normalized).
     *
     * Generated from Godot docs: Area3D.get_gravity_direction
     */
    fun getGravityDirection(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getGravityDirectionBind, segment)
    }

    /**
     * The area's gravity intensity (in meters per second squared). This value multiplies the gravity
     * direction. This is useful to alter the force of gravity without altering its direction.
     *
     * Generated from Godot docs: Area3D.set_gravity
     */
    fun setGravity(gravity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGravityBind, segment, gravity)
    }

    /**
     * The area's gravity intensity (in meters per second squared). This value multiplies the gravity
     * direction. This is useful to alter the force of gravity without altering its direction.
     *
     * Generated from Godot docs: Area3D.get_gravity
     */
    fun getGravity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGravityBind, segment)
    }

    /**
     * Override mode for linear damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.set_linear_damp_space_override_mode
     */
    fun setLinearDampSpaceOverrideMode(spaceOverrideMode: Area3D.SpaceOverride) {
        ObjectCalls.ptrcallWithLongArg(Binds.setLinearDampSpaceOverrideModeBind, segment, spaceOverrideMode.value)
    }

    /**
     * Override mode for linear damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.get_linear_damp_space_override_mode
     */
    fun getLinearDampSpaceOverrideMode(): Area3D.SpaceOverride {
        return Area3D.SpaceOverride(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLinearDampSpaceOverrideModeBind, segment))
    }

    /**
     * Override mode for angular damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.set_angular_damp_space_override_mode
     */
    fun setAngularDampSpaceOverrideMode(spaceOverrideMode: Area3D.SpaceOverride) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAngularDampSpaceOverrideModeBind, segment, spaceOverrideMode.value)
    }

    /**
     * Override mode for angular damping calculations within this area.
     *
     * Generated from Godot docs: Area3D.get_angular_damp_space_override_mode
     */
    fun getAngularDampSpaceOverrideMode(): Area3D.SpaceOverride {
        return Area3D.SpaceOverride(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAngularDampSpaceOverrideModeBind, segment))
    }

    /**
     * The rate at which objects stop spinning in this area. Represents the angular velocity lost per
     * second. See `ProjectSettings.physics/3d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.set_angular_damp
     */
    fun setAngularDamp(angularDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAngularDampBind, segment, angularDamp)
    }

    /**
     * The rate at which objects stop spinning in this area. Represents the angular velocity lost per
     * second. See `ProjectSettings.physics/3d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.get_angular_damp
     */
    fun getAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAngularDampBind, segment)
    }

    /**
     * The rate at which objects stop moving in this area. Represents the linear velocity lost per
     * second. See `ProjectSettings.physics/3d/default_linear_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.set_linear_damp
     */
    fun setLinearDamp(linearDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLinearDampBind, segment, linearDamp)
    }

    /**
     * The rate at which objects stop moving in this area. Represents the linear velocity lost per
     * second. See `ProjectSettings.physics/3d/default_linear_damp` for more details about damping.
     *
     * Generated from Godot docs: Area3D.get_linear_damp
     */
    fun getLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLinearDampBind, segment)
    }

    /**
     * The area's priority. Higher priority areas are processed first. The `World3D`'s physics is
     * always processed last, after all areas.
     *
     * Generated from Godot docs: Area3D.set_priority
     */
    fun setPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setPriorityBind, segment, priority)
    }

    /**
     * The area's priority. Higher priority areas are processed first. The `World3D`'s physics is
     * always processed last, after all areas.
     *
     * Generated from Godot docs: Area3D.get_priority
     */
    fun getPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPriorityBind, segment)
    }

    /**
     * The magnitude of area-specific wind force. Note: This wind force only applies to `SoftBody3D`
     * nodes. Other physics bodies are currently not affected by wind.
     *
     * Generated from Godot docs: Area3D.set_wind_force_magnitude
     */
    fun setWindForceMagnitude(windForceMagnitude: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWindForceMagnitudeBind, segment, windForceMagnitude)
    }

    /**
     * The magnitude of area-specific wind force. Note: This wind force only applies to `SoftBody3D`
     * nodes. Other physics bodies are currently not affected by wind.
     *
     * Generated from Godot docs: Area3D.get_wind_force_magnitude
     */
    fun getWindForceMagnitude(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWindForceMagnitudeBind, segment)
    }

    /**
     * The exponential rate at which wind force decreases with distance from its origin. Note: This
     * wind force only applies to `SoftBody3D` nodes. Other physics bodies are currently not affected
     * by wind.
     *
     * Generated from Godot docs: Area3D.set_wind_attenuation_factor
     */
    fun setWindAttenuationFactor(windAttenuationFactor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWindAttenuationFactorBind, segment, windAttenuationFactor)
    }

    /**
     * The exponential rate at which wind force decreases with distance from its origin. Note: This
     * wind force only applies to `SoftBody3D` nodes. Other physics bodies are currently not affected
     * by wind.
     *
     * Generated from Godot docs: Area3D.get_wind_attenuation_factor
     */
    fun getWindAttenuationFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWindAttenuationFactorBind, segment)
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
        ObjectCalls.ptrcallWithNodePathArg(Binds.setWindSourcePathBind, segment, windSourcePath)
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
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getWindSourcePathBind, segment)
    }

    /**
     * If `true`, other monitoring areas can detect this area.
     *
     * Generated from Godot docs: Area3D.set_monitorable
     */
    fun setMonitorable(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMonitorableBind, segment, enable)
    }

    /**
     * If `true`, other monitoring areas can detect this area.
     *
     * Generated from Godot docs: Area3D.is_monitorable
     */
    fun isMonitorable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMonitorableBind, segment)
    }

    /**
     * If `true`, the area detects bodies or areas entering and exiting it.
     *
     * Generated from Godot docs: Area3D.set_monitoring
     */
    fun setMonitoring(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMonitoringBind, segment, enable)
    }

    /**
     * If `true`, the area detects bodies or areas entering and exiting it.
     *
     * Generated from Godot docs: Area3D.is_monitoring
     */
    fun isMonitoring(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMonitoringBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getOverlappingBodiesBind, segment, Node3D::wrap)
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
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getOverlappingAreasBind, segment, Area3D::wrap)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasOverlappingBodiesBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasOverlappingAreasBind, segment)
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
        return ObjectCalls.ptrcallWithObjectArgRetBool(Binds.overlapsBodyBind, segment, body.segment)
    }

    /**
     * Returns `true` if the given `Area3D` intersects or overlaps this `Area3D`, `false` otherwise.
     * Note: The result of this test is not immediate after moving objects. For performance, list of
     * overlaps is updated once per frame and before the physics step. Consider using signals instead.
     *
     * Generated from Godot docs: Area3D.overlaps_area
     */
    fun overlapsArea(area: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(Binds.overlapsAreaBind, segment, area.segment)
    }

    /**
     * If `true`, the area's audio bus overrides the default audio bus.
     *
     * Generated from Godot docs: Area3D.set_audio_bus_override
     */
    fun setAudioBusOverride(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAudioBusOverrideBind, segment, enable)
    }

    /**
     * If `true`, the area's audio bus overrides the default audio bus.
     *
     * Generated from Godot docs: Area3D.is_overriding_audio_bus
     */
    fun isOverridingAudioBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isOverridingAudioBusBind, segment)
    }

    /**
     * The name of the area's audio bus.
     *
     * Generated from Godot docs: Area3D.set_audio_bus_name
     */
    fun setAudioBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setAudioBusNameBind, segment, name)
    }

    /**
     * The name of the area's audio bus.
     *
     * Generated from Godot docs: Area3D.get_audio_bus_name
     */
    fun getAudioBusName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getAudioBusNameBind, segment)
    }

    /**
     * If `true`, the area applies reverb to its associated audio.
     *
     * Generated from Godot docs: Area3D.set_use_reverb_bus
     */
    fun setUseReverbBus(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseReverbBusBind, segment, enable)
    }

    /**
     * If `true`, the area applies reverb to its associated audio.
     *
     * Generated from Godot docs: Area3D.is_using_reverb_bus
     */
    fun isUsingReverbBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingReverbBusBind, segment)
    }

    /**
     * The name of the reverb bus to use for this area's associated audio.
     *
     * Generated from Godot docs: Area3D.set_reverb_bus_name
     */
    fun setReverbBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setReverbBusNameBind, segment, name)
    }

    /**
     * The name of the reverb bus to use for this area's associated audio.
     *
     * Generated from Godot docs: Area3D.get_reverb_bus_name
     */
    fun getReverbBusName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getReverbBusNameBind, segment)
    }

    /**
     * The degree to which this area applies reverb to its associated audio. Ranges from `0` to `1`
     * with `0.1` precision.
     *
     * Generated from Godot docs: Area3D.set_reverb_amount
     */
    fun setReverbAmount(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setReverbAmountBind, segment, amount)
    }

    /**
     * The degree to which this area applies reverb to its associated audio. Ranges from `0` to `1`
     * with `0.1` precision.
     *
     * Generated from Godot docs: Area3D.get_reverb_amount
     */
    fun getReverbAmount(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getReverbAmountBind, segment)
    }

    /**
     * The degree to which this area's reverb is a uniform effect. Ranges from `0` to `1` with `0.1`
     * precision.
     *
     * Generated from Godot docs: Area3D.set_reverb_uniformity
     */
    fun setReverbUniformity(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setReverbUniformityBind, segment, amount)
    }

    /**
     * The degree to which this area's reverb is a uniform effect. Ranges from `0` to `1` with `0.1`
     * precision.
     *
     * Generated from Godot docs: Area3D.get_reverb_uniformity
     */
    fun getReverbUniformity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getReverbUniformityBind, segment)
    }

    /** Signal `body_shape_entered(body_rid: RID, body: Node3D, body_shape_index: int, local_shape_index: int)`; see [TypedSignal]. */
    val bodyShapeEntered: Signal4<RID, Node3D?, Long, Long>
        @JvmName("bodyShapeEnteredTypedSignal")
        get() = Signal4(this, "body_shape_entered", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.nullableObjectOf("Node3D") { Node3D(it) }, SignalArgType.LONG, SignalArgType.LONG)

    /** Signal `body_shape_exited(body_rid: RID, body: Node3D, body_shape_index: int, local_shape_index: int)`; see [TypedSignal]. */
    val bodyShapeExited: Signal4<RID, Node3D?, Long, Long>
        @JvmName("bodyShapeExitedTypedSignal")
        get() = Signal4(this, "body_shape_exited", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.nullableObjectOf("Node3D") { Node3D(it) }, SignalArgType.LONG, SignalArgType.LONG)

    /** Signal `body_entered(body: Node3D)`; see [TypedSignal]. */
    val bodyEntered: Signal1<Node3D>
        @JvmName("bodyEnteredTypedSignal")
        get() = Signal1(this, "body_entered", SignalArgType.objectOf("Node3D") { Node3D(it) })

    /** Signal `body_exited(body: Node3D)`; see [TypedSignal]. */
    val bodyExited: Signal1<Node3D>
        @JvmName("bodyExitedTypedSignal")
        get() = Signal1(this, "body_exited", SignalArgType.objectOf("Node3D") { Node3D(it) })

    /** Signal `area_shape_entered(area_rid: RID, area: Area3D, area_shape_index: int, local_shape_index: int)`; see [TypedSignal]. */
    val areaShapeEntered: Signal4<RID, Area3D?, Long, Long>
        @JvmName("areaShapeEnteredTypedSignal")
        get() = Signal4(this, "area_shape_entered", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.nullableObjectOf("Area3D") { Area3D(it) }, SignalArgType.LONG, SignalArgType.LONG)

    /** Signal `area_shape_exited(area_rid: RID, area: Area3D, area_shape_index: int, local_shape_index: int)`; see [TypedSignal]. */
    val areaShapeExited: Signal4<RID, Area3D?, Long, Long>
        @JvmName("areaShapeExitedTypedSignal")
        get() = Signal4(this, "area_shape_exited", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.nullableObjectOf("Area3D") { Area3D(it) }, SignalArgType.LONG, SignalArgType.LONG)

    /** Signal `area_entered(area: Area3D)`; see [TypedSignal]. */
    val areaEntered: Signal1<Area3D>
        @JvmName("areaEnteredTypedSignal")
        get() = Signal1(this, "area_entered", SignalArgType.objectOf("Area3D") { Area3D(it) })

    /** Signal `area_exited(area: Area3D)`; see [TypedSignal]. */
    val areaExited: Signal1<Area3D>
        @JvmName("areaExitedTypedSignal")
        get() = Signal1(this, "area_exited", SignalArgType.objectOf("Area3D") { Area3D(it) })

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

    /**
     * Godot's `Area3D.SpaceOverride` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Area3D.SpaceOverride.<NAME>`).
     *
     * Generated from Godot docs: Area3D.SpaceOverride
     */
    @JvmInline
    value class SpaceOverride(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This area does not affect gravity/damping.
             *
             * Generated from Godot docs: Area3D.SPACE_OVERRIDE_DISABLED
             */
            val DISABLED: SpaceOverride get() = SpaceOverride(0L)
            /**
             * This area adds its gravity/damping values to whatever has been calculated so far (in `priority`
             * order).
             *
             * Generated from Godot docs: Area3D.SPACE_OVERRIDE_COMBINE
             */
            val COMBINE: SpaceOverride get() = SpaceOverride(1L)
            /**
             * This area adds its gravity/damping values to whatever has been calculated so far (in `priority`
             * order), ignoring any lower priority areas.
             *
             * Generated from Godot docs: Area3D.SPACE_OVERRIDE_COMBINE_REPLACE
             */
            val COMBINE_REPLACE: SpaceOverride get() = SpaceOverride(2L)
            /**
             * This area replaces any gravity/damping, even the defaults, ignoring any lower priority areas.
             *
             * Generated from Godot docs: Area3D.SPACE_OVERRIDE_REPLACE
             */
            val REPLACE: SpaceOverride get() = SpaceOverride(3L)
            /**
             * This area replaces any gravity/damping calculated so far (in `priority` order), but keeps
             * calculating the rest of the areas.
             *
             * Generated from Godot docs: Area3D.SPACE_OVERRIDE_REPLACE_COMBINE
             */
            val REPLACE_COMBINE: SpaceOverride get() = SpaceOverride(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Area3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Area3D? =
            if (handle.address() == 0L) null else Area3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_GRAVITY_SPACE_OVERRIDE_MODE_HASH = 2311433571L
        @JvmField
        val setGravitySpaceOverrideModeBind =
            ObjectCalls.getMethodBind("Area3D", "set_gravity_space_override_mode", SET_GRAVITY_SPACE_OVERRIDE_MODE_HASH)

        private const val GET_GRAVITY_SPACE_OVERRIDE_MODE_HASH = 958191869L
        @JvmField
        val getGravitySpaceOverrideModeBind =
            ObjectCalls.getMethodBind("Area3D", "get_gravity_space_override_mode", GET_GRAVITY_SPACE_OVERRIDE_MODE_HASH)

        private const val SET_GRAVITY_IS_POINT_HASH = 2586408642L
        @JvmField
        val setGravityIsPointBind =
            ObjectCalls.getMethodBind("Area3D", "set_gravity_is_point", SET_GRAVITY_IS_POINT_HASH)

        private const val IS_GRAVITY_A_POINT_HASH = 36873697L
        @JvmField
        val isGravityAPointBind =
            ObjectCalls.getMethodBind("Area3D", "is_gravity_a_point", IS_GRAVITY_A_POINT_HASH)

        private const val SET_GRAVITY_POINT_UNIT_DISTANCE_HASH = 373806689L
        @JvmField
        val setGravityPointUnitDistanceBind =
            ObjectCalls.getMethodBind("Area3D", "set_gravity_point_unit_distance", SET_GRAVITY_POINT_UNIT_DISTANCE_HASH)

        private const val GET_GRAVITY_POINT_UNIT_DISTANCE_HASH = 1740695150L
        @JvmField
        val getGravityPointUnitDistanceBind =
            ObjectCalls.getMethodBind("Area3D", "get_gravity_point_unit_distance", GET_GRAVITY_POINT_UNIT_DISTANCE_HASH)

        private const val SET_GRAVITY_POINT_CENTER_HASH = 3460891852L
        @JvmField
        val setGravityPointCenterBind =
            ObjectCalls.getMethodBind("Area3D", "set_gravity_point_center", SET_GRAVITY_POINT_CENTER_HASH)

        private const val GET_GRAVITY_POINT_CENTER_HASH = 3360562783L
        @JvmField
        val getGravityPointCenterBind =
            ObjectCalls.getMethodBind("Area3D", "get_gravity_point_center", GET_GRAVITY_POINT_CENTER_HASH)

        private const val SET_GRAVITY_DIRECTION_HASH = 3460891852L
        @JvmField
        val setGravityDirectionBind =
            ObjectCalls.getMethodBind("Area3D", "set_gravity_direction", SET_GRAVITY_DIRECTION_HASH)

        private const val GET_GRAVITY_DIRECTION_HASH = 3360562783L
        @JvmField
        val getGravityDirectionBind =
            ObjectCalls.getMethodBind("Area3D", "get_gravity_direction", GET_GRAVITY_DIRECTION_HASH)

        private const val SET_GRAVITY_HASH = 373806689L
        @JvmField
        val setGravityBind =
            ObjectCalls.getMethodBind("Area3D", "set_gravity", SET_GRAVITY_HASH)

        private const val GET_GRAVITY_HASH = 1740695150L
        @JvmField
        val getGravityBind =
            ObjectCalls.getMethodBind("Area3D", "get_gravity", GET_GRAVITY_HASH)

        private const val SET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 2311433571L
        @JvmField
        val setLinearDampSpaceOverrideModeBind =
            ObjectCalls.getMethodBind("Area3D", "set_linear_damp_space_override_mode", SET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH)

        private const val GET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 958191869L
        @JvmField
        val getLinearDampSpaceOverrideModeBind =
            ObjectCalls.getMethodBind("Area3D", "get_linear_damp_space_override_mode", GET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH)

        private const val SET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 2311433571L
        @JvmField
        val setAngularDampSpaceOverrideModeBind =
            ObjectCalls.getMethodBind("Area3D", "set_angular_damp_space_override_mode", SET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH)

        private const val GET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 958191869L
        @JvmField
        val getAngularDampSpaceOverrideModeBind =
            ObjectCalls.getMethodBind("Area3D", "get_angular_damp_space_override_mode", GET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH)

        private const val SET_ANGULAR_DAMP_HASH = 373806689L
        @JvmField
        val setAngularDampBind =
            ObjectCalls.getMethodBind("Area3D", "set_angular_damp", SET_ANGULAR_DAMP_HASH)

        private const val GET_ANGULAR_DAMP_HASH = 1740695150L
        @JvmField
        val getAngularDampBind =
            ObjectCalls.getMethodBind("Area3D", "get_angular_damp", GET_ANGULAR_DAMP_HASH)

        private const val SET_LINEAR_DAMP_HASH = 373806689L
        @JvmField
        val setLinearDampBind =
            ObjectCalls.getMethodBind("Area3D", "set_linear_damp", SET_LINEAR_DAMP_HASH)

        private const val GET_LINEAR_DAMP_HASH = 1740695150L
        @JvmField
        val getLinearDampBind =
            ObjectCalls.getMethodBind("Area3D", "get_linear_damp", GET_LINEAR_DAMP_HASH)

        private const val SET_PRIORITY_HASH = 1286410249L
        @JvmField
        val setPriorityBind =
            ObjectCalls.getMethodBind("Area3D", "set_priority", SET_PRIORITY_HASH)

        private const val GET_PRIORITY_HASH = 3905245786L
        @JvmField
        val getPriorityBind =
            ObjectCalls.getMethodBind("Area3D", "get_priority", GET_PRIORITY_HASH)

        private const val SET_WIND_FORCE_MAGNITUDE_HASH = 373806689L
        @JvmField
        val setWindForceMagnitudeBind =
            ObjectCalls.getMethodBind("Area3D", "set_wind_force_magnitude", SET_WIND_FORCE_MAGNITUDE_HASH)

        private const val GET_WIND_FORCE_MAGNITUDE_HASH = 1740695150L
        @JvmField
        val getWindForceMagnitudeBind =
            ObjectCalls.getMethodBind("Area3D", "get_wind_force_magnitude", GET_WIND_FORCE_MAGNITUDE_HASH)

        private const val SET_WIND_ATTENUATION_FACTOR_HASH = 373806689L
        @JvmField
        val setWindAttenuationFactorBind =
            ObjectCalls.getMethodBind("Area3D", "set_wind_attenuation_factor", SET_WIND_ATTENUATION_FACTOR_HASH)

        private const val GET_WIND_ATTENUATION_FACTOR_HASH = 1740695150L
        @JvmField
        val getWindAttenuationFactorBind =
            ObjectCalls.getMethodBind("Area3D", "get_wind_attenuation_factor", GET_WIND_ATTENUATION_FACTOR_HASH)

        private const val SET_WIND_SOURCE_PATH_HASH = 1348162250L
        @JvmField
        val setWindSourcePathBind =
            ObjectCalls.getMethodBind("Area3D", "set_wind_source_path", SET_WIND_SOURCE_PATH_HASH)

        private const val GET_WIND_SOURCE_PATH_HASH = 4075236667L
        @JvmField
        val getWindSourcePathBind =
            ObjectCalls.getMethodBind("Area3D", "get_wind_source_path", GET_WIND_SOURCE_PATH_HASH)

        private const val SET_MONITORABLE_HASH = 2586408642L
        @JvmField
        val setMonitorableBind =
            ObjectCalls.getMethodBind("Area3D", "set_monitorable", SET_MONITORABLE_HASH)

        private const val IS_MONITORABLE_HASH = 36873697L
        @JvmField
        val isMonitorableBind =
            ObjectCalls.getMethodBind("Area3D", "is_monitorable", IS_MONITORABLE_HASH)

        private const val SET_MONITORING_HASH = 2586408642L
        @JvmField
        val setMonitoringBind =
            ObjectCalls.getMethodBind("Area3D", "set_monitoring", SET_MONITORING_HASH)

        private const val IS_MONITORING_HASH = 36873697L
        @JvmField
        val isMonitoringBind =
            ObjectCalls.getMethodBind("Area3D", "is_monitoring", IS_MONITORING_HASH)

        private const val GET_OVERLAPPING_BODIES_HASH = 3995934104L
        @JvmField
        val getOverlappingBodiesBind =
            ObjectCalls.getMethodBind("Area3D", "get_overlapping_bodies", GET_OVERLAPPING_BODIES_HASH)

        private const val GET_OVERLAPPING_AREAS_HASH = 3995934104L
        @JvmField
        val getOverlappingAreasBind =
            ObjectCalls.getMethodBind("Area3D", "get_overlapping_areas", GET_OVERLAPPING_AREAS_HASH)

        private const val HAS_OVERLAPPING_BODIES_HASH = 36873697L
        @JvmField
        val hasOverlappingBodiesBind =
            ObjectCalls.getMethodBind("Area3D", "has_overlapping_bodies", HAS_OVERLAPPING_BODIES_HASH)

        private const val HAS_OVERLAPPING_AREAS_HASH = 36873697L
        @JvmField
        val hasOverlappingAreasBind =
            ObjectCalls.getMethodBind("Area3D", "has_overlapping_areas", HAS_OVERLAPPING_AREAS_HASH)

        private const val OVERLAPS_BODY_HASH = 3093956946L
        @JvmField
        val overlapsBodyBind =
            ObjectCalls.getMethodBind("Area3D", "overlaps_body", OVERLAPS_BODY_HASH)

        private const val OVERLAPS_AREA_HASH = 3093956946L
        @JvmField
        val overlapsAreaBind =
            ObjectCalls.getMethodBind("Area3D", "overlaps_area", OVERLAPS_AREA_HASH)

        private const val SET_AUDIO_BUS_OVERRIDE_HASH = 2586408642L
        @JvmField
        val setAudioBusOverrideBind =
            ObjectCalls.getMethodBind("Area3D", "set_audio_bus_override", SET_AUDIO_BUS_OVERRIDE_HASH)

        private const val IS_OVERRIDING_AUDIO_BUS_HASH = 36873697L
        @JvmField
        val isOverridingAudioBusBind =
            ObjectCalls.getMethodBind("Area3D", "is_overriding_audio_bus", IS_OVERRIDING_AUDIO_BUS_HASH)

        private const val SET_AUDIO_BUS_NAME_HASH = 3304788590L
        @JvmField
        val setAudioBusNameBind =
            ObjectCalls.getMethodBind("Area3D", "set_audio_bus_name", SET_AUDIO_BUS_NAME_HASH)

        private const val GET_AUDIO_BUS_NAME_HASH = 2002593661L
        @JvmField
        val getAudioBusNameBind =
            ObjectCalls.getMethodBind("Area3D", "get_audio_bus_name", GET_AUDIO_BUS_NAME_HASH)

        private const val SET_USE_REVERB_BUS_HASH = 2586408642L
        @JvmField
        val setUseReverbBusBind =
            ObjectCalls.getMethodBind("Area3D", "set_use_reverb_bus", SET_USE_REVERB_BUS_HASH)

        private const val IS_USING_REVERB_BUS_HASH = 36873697L
        @JvmField
        val isUsingReverbBusBind =
            ObjectCalls.getMethodBind("Area3D", "is_using_reverb_bus", IS_USING_REVERB_BUS_HASH)

        private const val SET_REVERB_BUS_NAME_HASH = 3304788590L
        @JvmField
        val setReverbBusNameBind =
            ObjectCalls.getMethodBind("Area3D", "set_reverb_bus_name", SET_REVERB_BUS_NAME_HASH)

        private const val GET_REVERB_BUS_NAME_HASH = 2002593661L
        @JvmField
        val getReverbBusNameBind =
            ObjectCalls.getMethodBind("Area3D", "get_reverb_bus_name", GET_REVERB_BUS_NAME_HASH)

        private const val SET_REVERB_AMOUNT_HASH = 373806689L
        @JvmField
        val setReverbAmountBind =
            ObjectCalls.getMethodBind("Area3D", "set_reverb_amount", SET_REVERB_AMOUNT_HASH)

        private const val GET_REVERB_AMOUNT_HASH = 1740695150L
        @JvmField
        val getReverbAmountBind =
            ObjectCalls.getMethodBind("Area3D", "get_reverb_amount", GET_REVERB_AMOUNT_HASH)

        private const val SET_REVERB_UNIFORMITY_HASH = 373806689L
        @JvmField
        val setReverbUniformityBind =
            ObjectCalls.getMethodBind("Area3D", "set_reverb_uniformity", SET_REVERB_UNIFORMITY_HASH)

        private const val GET_REVERB_UNIFORMITY_HASH = 1740695150L
        @JvmField
        val getReverbUniformityBind =
            ObjectCalls.getMethodBind("Area3D", "get_reverb_uniformity", GET_REVERB_UNIFORMITY_HASH)
    }
}

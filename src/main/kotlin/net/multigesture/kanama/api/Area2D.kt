package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A region of 2D space that detects other `CollisionObject2D`s entering or exiting it.
 *
 * Generated from Godot docs: Area2D
 */
class Area2D(handle: MemorySegment) : CollisionObject2D(handle) {
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

    var gravityPointCenter: Vector2
        @JvmName("gravityPointCenterProperty")
        get() = getGravityPointCenter()
        @JvmName("setGravityPointCenterProperty")
        set(value) = setGravityPointCenter(value)

    var gravityDirection: Vector2
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

    /**
     * Override mode for gravity calculations within this area.
     *
     * Generated from Godot docs: Area2D.set_gravity_space_override_mode
     */
    fun setGravitySpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGravitySpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    /**
     * Override mode for gravity calculations within this area.
     *
     * Generated from Godot docs: Area2D.get_gravity_space_override_mode
     */
    fun getGravitySpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGravitySpaceOverrideModeBind, handle)
    }

    /**
     * If `true`, gravity is calculated from a point (set via `gravity_point_center`). See also
     * `gravity_space_override`.
     *
     * Generated from Godot docs: Area2D.set_gravity_is_point
     */
    fun setGravityIsPoint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGravityIsPointBind, handle, enable)
    }

    /**
     * If `true`, gravity is calculated from a point (set via `gravity_point_center`). See also
     * `gravity_space_override`.
     *
     * Generated from Godot docs: Area2D.is_gravity_a_point
     */
    fun isGravityAPoint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGravityAPointBind, handle)
    }

    /**
     * The distance at which the gravity strength is equal to `gravity`. For example, on a planet 100
     * pixels in radius with a surface gravity of 4.0 px/s², set the `gravity` to 4.0 and the unit
     * distance to 100.0. The gravity will have falloff according to the inverse square law, so in the
     * example, at 200 pixels from the center the gravity will be 1.0 px/s² (twice the distance, 1/4th
     * the gravity), at 50 pixels it will be 16.0 px/s² (half the distance, 4x the gravity), and so on.
     * The above is true only when the unit distance is a positive number. When this is set to 0.0, the
     * gravity will be constant regardless of distance.
     *
     * Generated from Godot docs: Area2D.set_gravity_point_unit_distance
     */
    fun setGravityPointUnitDistance(distanceScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityPointUnitDistanceBind, handle, distanceScale)
    }

    /**
     * The distance at which the gravity strength is equal to `gravity`. For example, on a planet 100
     * pixels in radius with a surface gravity of 4.0 px/s², set the `gravity` to 4.0 and the unit
     * distance to 100.0. The gravity will have falloff according to the inverse square law, so in the
     * example, at 200 pixels from the center the gravity will be 1.0 px/s² (twice the distance, 1/4th
     * the gravity), at 50 pixels it will be 16.0 px/s² (half the distance, 4x the gravity), and so on.
     * The above is true only when the unit distance is a positive number. When this is set to 0.0, the
     * gravity will be constant regardless of distance.
     *
     * Generated from Godot docs: Area2D.get_gravity_point_unit_distance
     */
    fun getGravityPointUnitDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityPointUnitDistanceBind, handle)
    }

    /**
     * If gravity is a point (see `gravity_point`), this will be the point of attraction.
     *
     * Generated from Godot docs: Area2D.set_gravity_point_center
     */
    fun setGravityPointCenter(center: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGravityPointCenterBind, handle, center)
    }

    /**
     * If gravity is a point (see `gravity_point`), this will be the point of attraction.
     *
     * Generated from Godot docs: Area2D.get_gravity_point_center
     */
    fun getGravityPointCenter(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGravityPointCenterBind, handle)
    }

    /**
     * The area's gravity vector (not normalized).
     *
     * Generated from Godot docs: Area2D.set_gravity_direction
     */
    fun setGravityDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGravityDirectionBind, handle, direction)
    }

    /**
     * The area's gravity vector (not normalized).
     *
     * Generated from Godot docs: Area2D.get_gravity_direction
     */
    fun getGravityDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGravityDirectionBind, handle)
    }

    /**
     * The area's gravity intensity (in pixels per second squared). This value multiplies the gravity
     * direction. This is useful to alter the force of gravity without altering its direction.
     *
     * Generated from Godot docs: Area2D.set_gravity
     */
    fun setGravity(gravity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityBind, handle, gravity)
    }

    /**
     * The area's gravity intensity (in pixels per second squared). This value multiplies the gravity
     * direction. This is useful to alter the force of gravity without altering its direction.
     *
     * Generated from Godot docs: Area2D.get_gravity
     */
    fun getGravity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityBind, handle)
    }

    /**
     * Override mode for linear damping calculations within this area.
     *
     * Generated from Godot docs: Area2D.set_linear_damp_space_override_mode
     */
    fun setLinearDampSpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLinearDampSpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    /**
     * Override mode for linear damping calculations within this area.
     *
     * Generated from Godot docs: Area2D.get_linear_damp_space_override_mode
     */
    fun getLinearDampSpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLinearDampSpaceOverrideModeBind, handle)
    }

    /**
     * Override mode for angular damping calculations within this area.
     *
     * Generated from Godot docs: Area2D.set_angular_damp_space_override_mode
     */
    fun setAngularDampSpaceOverrideMode(spaceOverrideMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAngularDampSpaceOverrideModeBind, handle, spaceOverrideMode)
    }

    /**
     * Override mode for angular damping calculations within this area.
     *
     * Generated from Godot docs: Area2D.get_angular_damp_space_override_mode
     */
    fun getAngularDampSpaceOverrideMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAngularDampSpaceOverrideModeBind, handle)
    }

    /**
     * The rate at which objects stop moving in this area. Represents the linear velocity lost per
     * second. See `ProjectSettings.physics/2d/default_linear_damp` for more details about damping.
     *
     * Generated from Godot docs: Area2D.set_linear_damp
     */
    fun setLinearDamp(linearDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLinearDampBind, handle, linearDamp)
    }

    /**
     * The rate at which objects stop moving in this area. Represents the linear velocity lost per
     * second. See `ProjectSettings.physics/2d/default_linear_damp` for more details about damping.
     *
     * Generated from Godot docs: Area2D.get_linear_damp
     */
    fun getLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLinearDampBind, handle)
    }

    /**
     * The rate at which objects stop spinning in this area. Represents the angular velocity lost per
     * second. See `ProjectSettings.physics/2d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: Area2D.set_angular_damp
     */
    fun setAngularDamp(angularDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularDampBind, handle, angularDamp)
    }

    /**
     * The rate at which objects stop spinning in this area. Represents the angular velocity lost per
     * second. See `ProjectSettings.physics/2d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: Area2D.get_angular_damp
     */
    fun getAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularDampBind, handle)
    }

    /**
     * The area's priority. Higher priority areas are processed first. The `World2D`'s physics is
     * always processed last, after all areas.
     *
     * Generated from Godot docs: Area2D.set_priority
     */
    fun setPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setPriorityBind, handle, priority)
    }

    /**
     * The area's priority. Higher priority areas are processed first. The `World2D`'s physics is
     * always processed last, after all areas.
     *
     * Generated from Godot docs: Area2D.get_priority
     */
    fun getPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPriorityBind, handle)
    }

    /**
     * If `true`, the area detects bodies or areas entering and exiting it.
     *
     * Generated from Godot docs: Area2D.set_monitoring
     */
    fun setMonitoring(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitoringBind, handle, enable)
    }

    /**
     * If `true`, the area detects bodies or areas entering and exiting it.
     *
     * Generated from Godot docs: Area2D.is_monitoring
     */
    fun isMonitoring(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitoringBind, handle)
    }

    /**
     * If `true`, other monitoring areas can detect this area.
     *
     * Generated from Godot docs: Area2D.set_monitorable
     */
    fun setMonitorable(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitorableBind, handle, enable)
    }

    /**
     * If `true`, other monitoring areas can detect this area.
     *
     * Generated from Godot docs: Area2D.is_monitorable
     */
    fun isMonitorable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitorableBind, handle)
    }

    /**
     * Returns a list of intersecting `PhysicsBody2D`s and `TileMap`s. The overlapping body's
     * `CollisionObject2D.collision_layer` must be part of this area's
     * `CollisionObject2D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) this list is modified once during the physics step, not
     * immediately after objects are moved. Consider using signals instead.
     *
     * Generated from Godot docs: Area2D.get_overlapping_bodies
     */
    fun getOverlappingBodies(): List<Node2D> {
        return ObjectCalls.ptrcallNoArgsRetTypedNode2DList(getOverlappingBodiesBind, handle)
    }

    /**
     * Returns a list of intersecting `Area2D`s. The overlapping area's
     * `CollisionObject2D.collision_layer` must be part of this area's
     * `CollisionObject2D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) this list is modified once during the physics step, not
     * immediately after objects are moved. Consider using signals instead.
     *
     * Generated from Godot docs: Area2D.get_overlapping_areas
     */
    fun getOverlappingAreas(): List<Area2D> {
        return ObjectCalls.ptrcallNoArgsRetTypedArea2DList(getOverlappingAreasBind, handle)
    }

    /**
     * Returns `true` if intersecting any `PhysicsBody2D`s or `TileMap`s, otherwise returns `false`.
     * The overlapping body's `CollisionObject2D.collision_layer` must be part of this area's
     * `CollisionObject2D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) the list of overlapping bodies is modified once during the
     * physics step, not immediately after objects are moved. Consider using signals instead.
     *
     * Generated from Godot docs: Area2D.has_overlapping_bodies
     */
    fun hasOverlappingBodies(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasOverlappingBodiesBind, handle)
    }

    /**
     * Returns `true` if intersecting any `Area2D`s, otherwise returns `false`. The overlapping area's
     * `CollisionObject2D.collision_layer` must be part of this area's
     * `CollisionObject2D.collision_mask` in order to be detected. For performance reasons (collisions
     * are all processed at the same time) the list of overlapping areas is modified once during the
     * physics step, not immediately after objects are moved. Consider using signals instead.
     *
     * Generated from Godot docs: Area2D.has_overlapping_areas
     */
    fun hasOverlappingAreas(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasOverlappingAreasBind, handle)
    }

    /**
     * Returns `true` if the given physics body intersects or overlaps this `Area2D`, `false`
     * otherwise. Note: The result of this test is not immediate after moving objects. For performance,
     * list of overlaps is updated once per frame and before the physics step. Consider using signals
     * instead. The `body` argument can either be a `PhysicsBody2D` or a `TileMap` instance. While
     * TileMaps are not physics bodies themselves, they register their tiles with collision shapes as a
     * virtual physics body.
     *
     * Generated from Godot docs: Area2D.overlaps_body
     */
    fun overlapsBody(body: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(overlapsBodyBind, handle, body.handle)
    }

    /**
     * Returns `true` if the given `Area2D` intersects or overlaps this `Area2D`, `false` otherwise.
     * Note: The result of this test is not immediate after moving objects. For performance, the list
     * of overlaps is updated once per frame and before the physics step. Consider using signals
     * instead.
     *
     * Generated from Godot docs: Area2D.overlaps_area
     */
    fun overlapsArea(area: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(overlapsAreaBind, handle, area.handle)
    }

    /**
     * The name of the area's audio bus.
     *
     * Generated from Godot docs: Area2D.set_audio_bus_name
     */
    fun setAudioBusName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAudioBusNameBind, handle, name)
    }

    /**
     * The name of the area's audio bus.
     *
     * Generated from Godot docs: Area2D.get_audio_bus_name
     */
    fun getAudioBusName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getAudioBusNameBind, handle)
    }

    /**
     * If `true`, the area's audio bus overrides the default audio bus.
     *
     * Generated from Godot docs: Area2D.set_audio_bus_override
     */
    fun setAudioBusOverride(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAudioBusOverrideBind, handle, enable)
    }

    /**
     * If `true`, the area's audio bus overrides the default audio bus.
     *
     * Generated from Godot docs: Area2D.is_overriding_audio_bus
     */
    fun isOverridingAudioBus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOverridingAudioBusBind, handle)
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
        fun fromHandle(handle: MemorySegment): Area2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Area2D? =
            if (handle.address() == 0L) null else Area2D(handle)

        private const val SET_GRAVITY_SPACE_OVERRIDE_MODE_HASH = 2879900038L
        private val setGravitySpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_gravity_space_override_mode", SET_GRAVITY_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val GET_GRAVITY_SPACE_OVERRIDE_MODE_HASH = 3990256304L
        private val getGravitySpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_gravity_space_override_mode", GET_GRAVITY_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val SET_GRAVITY_IS_POINT_HASH = 2586408642L
        private val setGravityIsPointBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_gravity_is_point", SET_GRAVITY_IS_POINT_HASH)
        }

        private const val IS_GRAVITY_A_POINT_HASH = 36873697L
        private val isGravityAPointBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "is_gravity_a_point", IS_GRAVITY_A_POINT_HASH)
        }

        private const val SET_GRAVITY_POINT_UNIT_DISTANCE_HASH = 373806689L
        private val setGravityPointUnitDistanceBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_gravity_point_unit_distance", SET_GRAVITY_POINT_UNIT_DISTANCE_HASH)
        }

        private const val GET_GRAVITY_POINT_UNIT_DISTANCE_HASH = 1740695150L
        private val getGravityPointUnitDistanceBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_gravity_point_unit_distance", GET_GRAVITY_POINT_UNIT_DISTANCE_HASH)
        }

        private const val SET_GRAVITY_POINT_CENTER_HASH = 743155724L
        private val setGravityPointCenterBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_gravity_point_center", SET_GRAVITY_POINT_CENTER_HASH)
        }

        private const val GET_GRAVITY_POINT_CENTER_HASH = 3341600327L
        private val getGravityPointCenterBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_gravity_point_center", GET_GRAVITY_POINT_CENTER_HASH)
        }

        private const val SET_GRAVITY_DIRECTION_HASH = 743155724L
        private val setGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_gravity_direction", SET_GRAVITY_DIRECTION_HASH)
        }

        private const val GET_GRAVITY_DIRECTION_HASH = 3341600327L
        private val getGravityDirectionBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_gravity_direction", GET_GRAVITY_DIRECTION_HASH)
        }

        private const val SET_GRAVITY_HASH = 373806689L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val GET_GRAVITY_HASH = 1740695150L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 2879900038L
        private val setLinearDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_linear_damp_space_override_mode", SET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val GET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 3990256304L
        private val getLinearDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_linear_damp_space_override_mode", GET_LINEAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val SET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 2879900038L
        private val setAngularDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_angular_damp_space_override_mode", SET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val GET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH = 3990256304L
        private val getAngularDampSpaceOverrideModeBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_angular_damp_space_override_mode", GET_ANGULAR_DAMP_SPACE_OVERRIDE_MODE_HASH)
        }

        private const val SET_LINEAR_DAMP_HASH = 373806689L
        private val setLinearDampBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_linear_damp", SET_LINEAR_DAMP_HASH)
        }

        private const val GET_LINEAR_DAMP_HASH = 1740695150L
        private val getLinearDampBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_linear_damp", GET_LINEAR_DAMP_HASH)
        }

        private const val SET_ANGULAR_DAMP_HASH = 373806689L
        private val setAngularDampBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_angular_damp", SET_ANGULAR_DAMP_HASH)
        }

        private const val GET_ANGULAR_DAMP_HASH = 1740695150L
        private val getAngularDampBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_angular_damp", GET_ANGULAR_DAMP_HASH)
        }

        private const val SET_PRIORITY_HASH = 1286410249L
        private val setPriorityBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_priority", SET_PRIORITY_HASH)
        }

        private const val GET_PRIORITY_HASH = 3905245786L
        private val getPriorityBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_priority", GET_PRIORITY_HASH)
        }

        private const val SET_MONITORING_HASH = 2586408642L
        private val setMonitoringBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_monitoring", SET_MONITORING_HASH)
        }

        private const val IS_MONITORING_HASH = 36873697L
        private val isMonitoringBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "is_monitoring", IS_MONITORING_HASH)
        }

        private const val SET_MONITORABLE_HASH = 2586408642L
        private val setMonitorableBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_monitorable", SET_MONITORABLE_HASH)
        }

        private const val IS_MONITORABLE_HASH = 36873697L
        private val isMonitorableBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "is_monitorable", IS_MONITORABLE_HASH)
        }

        private const val GET_OVERLAPPING_BODIES_HASH = 3995934104L
        private val getOverlappingBodiesBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_overlapping_bodies", GET_OVERLAPPING_BODIES_HASH)
        }

        private const val GET_OVERLAPPING_AREAS_HASH = 3995934104L
        private val getOverlappingAreasBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_overlapping_areas", GET_OVERLAPPING_AREAS_HASH)
        }

        private const val HAS_OVERLAPPING_BODIES_HASH = 36873697L
        private val hasOverlappingBodiesBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "has_overlapping_bodies", HAS_OVERLAPPING_BODIES_HASH)
        }

        private const val HAS_OVERLAPPING_AREAS_HASH = 36873697L
        private val hasOverlappingAreasBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "has_overlapping_areas", HAS_OVERLAPPING_AREAS_HASH)
        }

        private const val OVERLAPS_BODY_HASH = 3093956946L
        private val overlapsBodyBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "overlaps_body", OVERLAPS_BODY_HASH)
        }

        private const val OVERLAPS_AREA_HASH = 3093956946L
        private val overlapsAreaBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "overlaps_area", OVERLAPS_AREA_HASH)
        }

        private const val SET_AUDIO_BUS_NAME_HASH = 3304788590L
        private val setAudioBusNameBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_audio_bus_name", SET_AUDIO_BUS_NAME_HASH)
        }

        private const val GET_AUDIO_BUS_NAME_HASH = 2002593661L
        private val getAudioBusNameBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "get_audio_bus_name", GET_AUDIO_BUS_NAME_HASH)
        }

        private const val SET_AUDIO_BUS_OVERRIDE_HASH = 2586408642L
        private val setAudioBusOverrideBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "set_audio_bus_override", SET_AUDIO_BUS_OVERRIDE_HASH)
        }

        private const val IS_OVERRIDING_AUDIO_BUS_HASH = 36873697L
        private val isOverridingAudioBusBind by lazy {
            ObjectCalls.getMethodBind("Area2D", "is_overriding_audio_bus", IS_OVERRIDING_AUDIO_BUS_HASH)
        }
    }
}

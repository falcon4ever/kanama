package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 2D physics body specialized for characters moved by script.
 *
 * Generated from Godot docs: CharacterBody2D
 */
class CharacterBody2D(handle: MemorySegment) : PhysicsBody2D(handle) {
    var motionMode: Long
        @JvmName("motionModeProperty")
        get() = getMotionMode()
        @JvmName("setMotionModeProperty")
        set(value) = setMotionMode(value)

    var upDirection: Vector2
        @JvmName("upDirectionProperty")
        get() = getUpDirection()
        @JvmName("setUpDirectionProperty")
        set(value) = setUpDirection(value)

    var velocity: Vector2
        @JvmName("velocityProperty")
        get() = getVelocity()
        @JvmName("setVelocityProperty")
        set(value) = setVelocity(value)

    var slideOnCeiling: Boolean
        @JvmName("slideOnCeilingProperty")
        get() = isSlideOnCeilingEnabled()
        @JvmName("setSlideOnCeilingProperty")
        set(value) = setSlideOnCeilingEnabled(value)

    var maxSlides: Int
        @JvmName("maxSlidesProperty")
        get() = getMaxSlides()
        @JvmName("setMaxSlidesProperty")
        set(value) = setMaxSlides(value)

    var wallMinSlideAngle: Double
        @JvmName("wallMinSlideAngleProperty")
        get() = getWallMinSlideAngle()
        @JvmName("setWallMinSlideAngleProperty")
        set(value) = setWallMinSlideAngle(value)

    var floorStopOnSlope: Boolean
        @JvmName("floorStopOnSlopeProperty")
        get() = isFloorStopOnSlopeEnabled()
        @JvmName("setFloorStopOnSlopeProperty")
        set(value) = setFloorStopOnSlopeEnabled(value)

    var floorConstantSpeed: Boolean
        @JvmName("floorConstantSpeedProperty")
        get() = isFloorConstantSpeedEnabled()
        @JvmName("setFloorConstantSpeedProperty")
        set(value) = setFloorConstantSpeedEnabled(value)

    var floorBlockOnWall: Boolean
        @JvmName("floorBlockOnWallProperty")
        get() = isFloorBlockOnWallEnabled()
        @JvmName("setFloorBlockOnWallProperty")
        set(value) = setFloorBlockOnWallEnabled(value)

    var floorMaxAngle: Double
        @JvmName("floorMaxAngleProperty")
        get() = getFloorMaxAngle()
        @JvmName("setFloorMaxAngleProperty")
        set(value) = setFloorMaxAngle(value)

    var floorSnapLength: Double
        @JvmName("floorSnapLengthProperty")
        get() = getFloorSnapLength()
        @JvmName("setFloorSnapLengthProperty")
        set(value) = setFloorSnapLength(value)

    var platformOnLeave: Long
        @JvmName("platformOnLeaveProperty")
        get() = getPlatformOnLeave()
        @JvmName("setPlatformOnLeaveProperty")
        set(value) = setPlatformOnLeave(value)

    var platformFloorLayers: Long
        @JvmName("platformFloorLayersProperty")
        get() = getPlatformFloorLayers()
        @JvmName("setPlatformFloorLayersProperty")
        set(value) = setPlatformFloorLayers(value)

    var platformWallLayers: Long
        @JvmName("platformWallLayersProperty")
        get() = getPlatformWallLayers()
        @JvmName("setPlatformWallLayersProperty")
        set(value) = setPlatformWallLayers(value)

    var safeMargin: Double
        @JvmName("safeMarginProperty")
        get() = getSafeMargin()
        @JvmName("setSafeMarginProperty")
        set(value) = setSafeMargin(value)

    /**
     * Moves the body based on `velocity`. If the body collides with another, it will slide along the
     * other body (by default only on floor) rather than stop immediately. If the other body is a
     * `CharacterBody2D` or `RigidBody2D`, it will also be affected by the motion of the other body.
     * You can use this to make moving and rotating platforms, or to make nodes push other nodes. This
     * method should be used in `Node._physics_process` (or in a method called by
     * `Node._physics_process`), as it uses the physics step's `delta` value automatically in
     * calculations. Otherwise, the simulation will run at an incorrect speed. Modifies `velocity` if a
     * slide collision occurred. To get the latest collision call `get_last_slide_collision`, for
     * detailed information about collisions that occurred, use `get_slide_collision`. When the body
     * touches a moving platform, the platform's velocity is automatically added to the body motion. If
     * a collision occurs due to the platform's motion, it will always be first in the slide
     * collisions. The general behavior and available properties change according to the `motion_mode`.
     * Returns `true` if the body collided, otherwise, returns `false`.
     *
     * Generated from Godot docs: CharacterBody2D.move_and_slide
     */
    fun moveAndSlide(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(moveAndSlideBind, handle)
    }

    /**
     * Allows to manually apply a snap to the floor regardless of the body's velocity. This function
     * does nothing when `is_on_floor` returns `true`.
     *
     * Generated from Godot docs: CharacterBody2D.apply_floor_snap
     */
    fun applyFloorSnap() {
        ObjectCalls.ptrcallNoArgs(applyFloorSnapBind, handle)
    }

    /**
     * Current velocity vector in pixels per second, used and modified during calls to
     * `move_and_slide`. Note: A common mistake is setting this property to the desired velocity
     * multiplied by `delta`, which produces a motion vector in pixels.
     *
     * Generated from Godot docs: CharacterBody2D.set_velocity
     */
    fun setVelocity(velocity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setVelocityBind, handle, velocity)
    }

    /**
     * Current velocity vector in pixels per second, used and modified during calls to
     * `move_and_slide`. Note: A common mistake is setting this property to the desired velocity
     * multiplied by `delta`, which produces a motion vector in pixels.
     *
     * Generated from Godot docs: CharacterBody2D.get_velocity
     */
    fun getVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getVelocityBind, handle)
    }

    /**
     * Extra margin used for collision recovery when calling `move_and_slide`. If the body is at least
     * this close to another body, it will consider them to be colliding and will be pushed away before
     * performing the actual motion. A higher value means it's more flexible for detecting collision,
     * which helps with consistently detecting walls and floors. A lower value forces the collision
     * algorithm to use more exact detection, so it can be used in cases that specifically require
     * precision, e.g at very low scale to avoid visible jittering, or for stability with a stack of
     * character bodies.
     *
     * Generated from Godot docs: CharacterBody2D.set_safe_margin
     */
    fun setSafeMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSafeMarginBind, handle, margin)
    }

    /**
     * Extra margin used for collision recovery when calling `move_and_slide`. If the body is at least
     * this close to another body, it will consider them to be colliding and will be pushed away before
     * performing the actual motion. A higher value means it's more flexible for detecting collision,
     * which helps with consistently detecting walls and floors. A lower value forces the collision
     * algorithm to use more exact detection, so it can be used in cases that specifically require
     * precision, e.g at very low scale to avoid visible jittering, or for stability with a stack of
     * character bodies.
     *
     * Generated from Godot docs: CharacterBody2D.get_safe_margin
     */
    fun getSafeMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSafeMarginBind, handle)
    }

    /**
     * If `true`, the body will not slide on slopes when calling `move_and_slide` when the body is
     * standing still. If `false`, the body will slide on floor's slopes when `velocity` applies a
     * downward force.
     *
     * Generated from Godot docs: CharacterBody2D.is_floor_stop_on_slope_enabled
     */
    fun isFloorStopOnSlopeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFloorStopOnSlopeEnabledBind, handle)
    }

    /**
     * If `true`, the body will not slide on slopes when calling `move_and_slide` when the body is
     * standing still. If `false`, the body will slide on floor's slopes when `velocity` applies a
     * downward force.
     *
     * Generated from Godot docs: CharacterBody2D.set_floor_stop_on_slope_enabled
     */
    fun setFloorStopOnSlopeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFloorStopOnSlopeEnabledBind, handle, enabled)
    }

    /**
     * If `false` (by default), the body will move faster on downward slopes and slower on upward
     * slopes. If `true`, the body will always move at the same speed on the ground no matter the
     * slope. Note that you need to use `floor_snap_length` to stick along a downward slope at constant
     * speed.
     *
     * Generated from Godot docs: CharacterBody2D.set_floor_constant_speed_enabled
     */
    fun setFloorConstantSpeedEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFloorConstantSpeedEnabledBind, handle, enabled)
    }

    /**
     * If `false` (by default), the body will move faster on downward slopes and slower on upward
     * slopes. If `true`, the body will always move at the same speed on the ground no matter the
     * slope. Note that you need to use `floor_snap_length` to stick along a downward slope at constant
     * speed.
     *
     * Generated from Godot docs: CharacterBody2D.is_floor_constant_speed_enabled
     */
    fun isFloorConstantSpeedEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFloorConstantSpeedEnabledBind, handle)
    }

    /**
     * If `true`, the body will be able to move on the floor only. This option avoids to be able to
     * walk on walls, it will however allow to slide down along them.
     *
     * Generated from Godot docs: CharacterBody2D.set_floor_block_on_wall_enabled
     */
    fun setFloorBlockOnWallEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFloorBlockOnWallEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the body will be able to move on the floor only. This option avoids to be able to
     * walk on walls, it will however allow to slide down along them.
     *
     * Generated from Godot docs: CharacterBody2D.is_floor_block_on_wall_enabled
     */
    fun isFloorBlockOnWallEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFloorBlockOnWallEnabledBind, handle)
    }

    /**
     * If `true`, during a jump against the ceiling, the body will slide, if `false` it will be stopped
     * and will fall vertically.
     *
     * Generated from Godot docs: CharacterBody2D.set_slide_on_ceiling_enabled
     */
    fun setSlideOnCeilingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSlideOnCeilingEnabledBind, handle, enabled)
    }

    /**
     * If `true`, during a jump against the ceiling, the body will slide, if `false` it will be stopped
     * and will fall vertically.
     *
     * Generated from Godot docs: CharacterBody2D.is_slide_on_ceiling_enabled
     */
    fun isSlideOnCeilingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSlideOnCeilingEnabledBind, handle)
    }

    /**
     * Collision layers that will be included for detecting floor bodies that will act as moving
     * platforms to be followed by the `CharacterBody2D`. By default, all floor bodies are detected and
     * propagate their velocity.
     *
     * Generated from Godot docs: CharacterBody2D.set_platform_floor_layers
     */
    fun setPlatformFloorLayers(excludeLayer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setPlatformFloorLayersBind, handle, excludeLayer)
    }

    /**
     * Collision layers that will be included for detecting floor bodies that will act as moving
     * platforms to be followed by the `CharacterBody2D`. By default, all floor bodies are detected and
     * propagate their velocity.
     *
     * Generated from Godot docs: CharacterBody2D.get_platform_floor_layers
     */
    fun getPlatformFloorLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getPlatformFloorLayersBind, handle)
    }

    /**
     * Collision layers that will be included for detecting wall bodies that will act as moving
     * platforms to be followed by the `CharacterBody2D`. By default, all wall bodies are ignored.
     *
     * Generated from Godot docs: CharacterBody2D.set_platform_wall_layers
     */
    fun setPlatformWallLayers(excludeLayer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setPlatformWallLayersBind, handle, excludeLayer)
    }

    /**
     * Collision layers that will be included for detecting wall bodies that will act as moving
     * platforms to be followed by the `CharacterBody2D`. By default, all wall bodies are ignored.
     *
     * Generated from Godot docs: CharacterBody2D.get_platform_wall_layers
     */
    fun getPlatformWallLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getPlatformWallLayersBind, handle)
    }

    /**
     * Maximum number of times the body can change direction before it stops when calling
     * `move_and_slide`. Must be greater than zero.
     *
     * Generated from Godot docs: CharacterBody2D.get_max_slides
     */
    fun getMaxSlides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxSlidesBind, handle)
    }

    /**
     * Maximum number of times the body can change direction before it stops when calling
     * `move_and_slide`. Must be greater than zero.
     *
     * Generated from Godot docs: CharacterBody2D.set_max_slides
     */
    fun setMaxSlides(maxSlides: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxSlidesBind, handle, maxSlides)
    }

    /**
     * Maximum angle (in radians) where a slope is still considered a floor (or a ceiling), rather than
     * a wall, when calling `move_and_slide`. The default value equals 45 degrees.
     *
     * Generated from Godot docs: CharacterBody2D.get_floor_max_angle
     */
    fun getFloorMaxAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloorMaxAngleBind, handle)
    }

    /**
     * Maximum angle (in radians) where a slope is still considered a floor (or a ceiling), rather than
     * a wall, when calling `move_and_slide`. The default value equals 45 degrees.
     *
     * Generated from Godot docs: CharacterBody2D.set_floor_max_angle
     */
    fun setFloorMaxAngle(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFloorMaxAngleBind, handle, radians)
    }

    /**
     * Sets a snapping distance. When set to a value different from `0.0`, the body is kept attached to
     * slopes when calling `move_and_slide`. The snapping vector is determined by the given distance
     * along the opposite direction of the `up_direction`. As long as the snapping vector is in contact
     * with the ground and the body moves against `up_direction`, the body will remain attached to the
     * surface. Snapping is not applied if the body moves along `up_direction`, meaning it contains
     * vertical rising velocity, so it will be able to detach from the ground when jumping or when the
     * body is pushed up by something. If you want to apply a snap without taking into account the
     * velocity, use `apply_floor_snap`.
     *
     * Generated from Godot docs: CharacterBody2D.get_floor_snap_length
     */
    fun getFloorSnapLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloorSnapLengthBind, handle)
    }

    /**
     * Sets a snapping distance. When set to a value different from `0.0`, the body is kept attached to
     * slopes when calling `move_and_slide`. The snapping vector is determined by the given distance
     * along the opposite direction of the `up_direction`. As long as the snapping vector is in contact
     * with the ground and the body moves against `up_direction`, the body will remain attached to the
     * surface. Snapping is not applied if the body moves along `up_direction`, meaning it contains
     * vertical rising velocity, so it will be able to detach from the ground when jumping or when the
     * body is pushed up by something. If you want to apply a snap without taking into account the
     * velocity, use `apply_floor_snap`.
     *
     * Generated from Godot docs: CharacterBody2D.set_floor_snap_length
     */
    fun setFloorSnapLength(floorSnapLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFloorSnapLengthBind, handle, floorSnapLength)
    }

    /**
     * Minimum angle (in radians) where the body is allowed to slide when it encounters a wall. The
     * default value equals 15 degrees. This property only affects movement when `motion_mode` is
     * `MOTION_MODE_FLOATING`.
     *
     * Generated from Godot docs: CharacterBody2D.get_wall_min_slide_angle
     */
    fun getWallMinSlideAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWallMinSlideAngleBind, handle)
    }

    /**
     * Minimum angle (in radians) where the body is allowed to slide when it encounters a wall. The
     * default value equals 15 degrees. This property only affects movement when `motion_mode` is
     * `MOTION_MODE_FLOATING`.
     *
     * Generated from Godot docs: CharacterBody2D.set_wall_min_slide_angle
     */
    fun setWallMinSlideAngle(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWallMinSlideAngleBind, handle, radians)
    }

    /**
     * Vector pointing upwards, used to determine what is a wall and what is a floor (or a ceiling)
     * when calling `move_and_slide`. Defaults to `Vector2.UP`. As the vector will be normalized it
     * can't be equal to `Vector2.ZERO`, if you want all collisions to be reported as walls, consider
     * using `MOTION_MODE_FLOATING` as `motion_mode`.
     *
     * Generated from Godot docs: CharacterBody2D.get_up_direction
     */
    fun getUpDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getUpDirectionBind, handle)
    }

    /**
     * Vector pointing upwards, used to determine what is a wall and what is a floor (or a ceiling)
     * when calling `move_and_slide`. Defaults to `Vector2.UP`. As the vector will be normalized it
     * can't be equal to `Vector2.ZERO`, if you want all collisions to be reported as walls, consider
     * using `MOTION_MODE_FLOATING` as `motion_mode`.
     *
     * Generated from Godot docs: CharacterBody2D.set_up_direction
     */
    fun setUpDirection(upDirection: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setUpDirectionBind, handle, upDirection)
    }

    /**
     * Sets the motion mode which defines the behavior of `move_and_slide`.
     *
     * Generated from Godot docs: CharacterBody2D.set_motion_mode
     */
    fun setMotionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMotionModeBind, handle, mode)
    }

    /**
     * Sets the motion mode which defines the behavior of `move_and_slide`.
     *
     * Generated from Godot docs: CharacterBody2D.get_motion_mode
     */
    fun getMotionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMotionModeBind, handle)
    }

    /**
     * Sets the behavior to apply when you leave a moving platform. By default, to be physically
     * accurate, when you leave the last platform velocity is applied.
     *
     * Generated from Godot docs: CharacterBody2D.set_platform_on_leave
     */
    fun setPlatformOnLeave(onLeaveApplyVelocity: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlatformOnLeaveBind, handle, onLeaveApplyVelocity)
    }

    /**
     * Sets the behavior to apply when you leave a moving platform. By default, to be physically
     * accurate, when you leave the last platform velocity is applied.
     *
     * Generated from Godot docs: CharacterBody2D.get_platform_on_leave
     */
    fun getPlatformOnLeave(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlatformOnLeaveBind, handle)
    }

    /**
     * Returns `true` if the body collided with the floor on the last call of `move_and_slide`.
     * Otherwise, returns `false`. The `up_direction` and `floor_max_angle` are used to determine
     * whether a surface is "floor" or not.
     *
     * Generated from Godot docs: CharacterBody2D.is_on_floor
     */
    fun isOnFloor(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnFloorBind, handle)
    }

    /**
     * Returns `true` if the body collided only with the floor on the last call of `move_and_slide`.
     * Otherwise, returns `false`. The `up_direction` and `floor_max_angle` are used to determine
     * whether a surface is "floor" or not.
     *
     * Generated from Godot docs: CharacterBody2D.is_on_floor_only
     */
    fun isOnFloorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnFloorOnlyBind, handle)
    }

    /**
     * Returns `true` if the body collided with the ceiling on the last call of `move_and_slide`.
     * Otherwise, returns `false`. The `up_direction` and `floor_max_angle` are used to determine
     * whether a surface is "ceiling" or not.
     *
     * Generated from Godot docs: CharacterBody2D.is_on_ceiling
     */
    fun isOnCeiling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnCeilingBind, handle)
    }

    /**
     * Returns `true` if the body collided only with the ceiling on the last call of `move_and_slide`.
     * Otherwise, returns `false`. The `up_direction` and `floor_max_angle` are used to determine
     * whether a surface is "ceiling" or not.
     *
     * Generated from Godot docs: CharacterBody2D.is_on_ceiling_only
     */
    fun isOnCeilingOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnCeilingOnlyBind, handle)
    }

    /**
     * Returns `true` if the body collided with a wall on the last call of `move_and_slide`. Otherwise,
     * returns `false`. The `up_direction` and `floor_max_angle` are used to determine whether a
     * surface is "wall" or not.
     *
     * Generated from Godot docs: CharacterBody2D.is_on_wall
     */
    fun isOnWall(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnWallBind, handle)
    }

    /**
     * Returns `true` if the body collided only with a wall on the last call of `move_and_slide`.
     * Otherwise, returns `false`. The `up_direction` and `floor_max_angle` are used to determine
     * whether a surface is "wall" or not.
     *
     * Generated from Godot docs: CharacterBody2D.is_on_wall_only
     */
    fun isOnWallOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnWallOnlyBind, handle)
    }

    /**
     * Returns the collision normal of the floor at the last collision point. Only valid after calling
     * `move_and_slide` and when `is_on_floor` returns `true`. Warning: The collision normal is not
     * always the same as the surface normal.
     *
     * Generated from Godot docs: CharacterBody2D.get_floor_normal
     */
    fun getFloorNormal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFloorNormalBind, handle)
    }

    /**
     * Returns the collision normal of the wall at the last collision point. Only valid after calling
     * `move_and_slide` and when `is_on_wall` returns `true`. Warning: The collision normal is not
     * always the same as the surface normal.
     *
     * Generated from Godot docs: CharacterBody2D.get_wall_normal
     */
    fun getWallNormal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getWallNormalBind, handle)
    }

    /**
     * Returns the last motion applied to the `CharacterBody2D` during the last call to
     * `move_and_slide`. The movement can be split into multiple motions when sliding occurs, and this
     * method return the last one, which is useful to retrieve the current direction of the movement.
     *
     * Generated from Godot docs: CharacterBody2D.get_last_motion
     */
    fun getLastMotion(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLastMotionBind, handle)
    }

    /**
     * Returns the travel (position delta) that occurred during the last call to `move_and_slide`.
     *
     * Generated from Godot docs: CharacterBody2D.get_position_delta
     */
    fun getPositionDelta(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionDeltaBind, handle)
    }

    /**
     * Returns the current real velocity since the last call to `move_and_slide`. For example, when you
     * climb a slope, you will move diagonally even though the velocity is horizontal. This method
     * returns the diagonal movement, as opposed to `velocity` which returns the requested velocity.
     *
     * Generated from Godot docs: CharacterBody2D.get_real_velocity
     */
    fun getRealVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRealVelocityBind, handle)
    }

    /**
     * Returns the floor's collision angle at the last collision point according to `up_direction`,
     * which is `Vector2.UP` by default. This value is always positive and only valid after calling
     * `move_and_slide` and when `is_on_floor` returns `true`.
     *
     * Generated from Godot docs: CharacterBody2D.get_floor_angle
     */
    fun getFloorAngle(upDirection: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getFloorAngleBind, handle, upDirection)
    }

    /**
     * Returns the linear velocity of the platform at the last collision point. Only valid after
     * calling `move_and_slide`.
     *
     * Generated from Godot docs: CharacterBody2D.get_platform_velocity
     */
    fun getPlatformVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPlatformVelocityBind, handle)
    }

    /**
     * Returns the number of times the body collided and changed direction during the last call to
     * `move_and_slide`.
     *
     * Generated from Godot docs: CharacterBody2D.get_slide_collision_count
     */
    fun getSlideCollisionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSlideCollisionCountBind, handle)
    }

    /**
     * Returns a `KinematicCollision2D`, which contains information about a collision that occurred
     * during the last call to `move_and_slide`. Since the body can collide several times in a single
     * call to `move_and_slide`, you must specify the index of the collision in the range 0 to
     * (`get_slide_collision_count` - 1). See also `get_last_slide_collision`.
     *
     * Generated from Godot docs: CharacterBody2D.get_slide_collision
     */
    fun getSlideCollision(slideIdx: Int): KinematicCollision2D? {
        return KinematicCollision2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSlideCollisionBind, handle, slideIdx))
    }

    /**
     * Returns a `KinematicCollision2D` if a collision occurred. The returned value contains
     * information about the latest collision that occurred during the last call to `move_and_slide`.
     * Returns `null` if no collision occurred. See also `get_slide_collision`.
     *
     * Generated from Godot docs: CharacterBody2D.get_last_slide_collision
     */
    fun getLastSlideCollision(): KinematicCollision2D? {
        return KinematicCollision2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLastSlideCollisionBind, handle))
    }

    companion object {
        const val MOTION_MODE_GROUNDED: Long = 0L
        const val MOTION_MODE_FLOATING: Long = 1L
        const val PLATFORM_ON_LEAVE_ADD_VELOCITY: Long = 0L
        const val PLATFORM_ON_LEAVE_ADD_UPWARD_VELOCITY: Long = 1L
        const val PLATFORM_ON_LEAVE_DO_NOTHING: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CharacterBody2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CharacterBody2D? =
            if (handle.address() == 0L) null else CharacterBody2D(handle)

        private const val MOVE_AND_SLIDE_HASH = 2240911060L
        private val moveAndSlideBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "move_and_slide", MOVE_AND_SLIDE_HASH)
        }

        private const val APPLY_FLOOR_SNAP_HASH = 3218959716L
        private val applyFloorSnapBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "apply_floor_snap", APPLY_FLOOR_SNAP_HASH)
        }

        private const val SET_VELOCITY_HASH = 743155724L
        private val setVelocityBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_velocity", SET_VELOCITY_HASH)
        }

        private const val GET_VELOCITY_HASH = 3341600327L
        private val getVelocityBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_velocity", GET_VELOCITY_HASH)
        }

        private const val SET_SAFE_MARGIN_HASH = 373806689L
        private val setSafeMarginBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_safe_margin", SET_SAFE_MARGIN_HASH)
        }

        private const val GET_SAFE_MARGIN_HASH = 1740695150L
        private val getSafeMarginBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_safe_margin", GET_SAFE_MARGIN_HASH)
        }

        private const val IS_FLOOR_STOP_ON_SLOPE_ENABLED_HASH = 36873697L
        private val isFloorStopOnSlopeEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_floor_stop_on_slope_enabled", IS_FLOOR_STOP_ON_SLOPE_ENABLED_HASH)
        }

        private const val SET_FLOOR_STOP_ON_SLOPE_ENABLED_HASH = 2586408642L
        private val setFloorStopOnSlopeEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_floor_stop_on_slope_enabled", SET_FLOOR_STOP_ON_SLOPE_ENABLED_HASH)
        }

        private const val SET_FLOOR_CONSTANT_SPEED_ENABLED_HASH = 2586408642L
        private val setFloorConstantSpeedEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_floor_constant_speed_enabled", SET_FLOOR_CONSTANT_SPEED_ENABLED_HASH)
        }

        private const val IS_FLOOR_CONSTANT_SPEED_ENABLED_HASH = 36873697L
        private val isFloorConstantSpeedEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_floor_constant_speed_enabled", IS_FLOOR_CONSTANT_SPEED_ENABLED_HASH)
        }

        private const val SET_FLOOR_BLOCK_ON_WALL_ENABLED_HASH = 2586408642L
        private val setFloorBlockOnWallEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_floor_block_on_wall_enabled", SET_FLOOR_BLOCK_ON_WALL_ENABLED_HASH)
        }

        private const val IS_FLOOR_BLOCK_ON_WALL_ENABLED_HASH = 36873697L
        private val isFloorBlockOnWallEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_floor_block_on_wall_enabled", IS_FLOOR_BLOCK_ON_WALL_ENABLED_HASH)
        }

        private const val SET_SLIDE_ON_CEILING_ENABLED_HASH = 2586408642L
        private val setSlideOnCeilingEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_slide_on_ceiling_enabled", SET_SLIDE_ON_CEILING_ENABLED_HASH)
        }

        private const val IS_SLIDE_ON_CEILING_ENABLED_HASH = 36873697L
        private val isSlideOnCeilingEnabledBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_slide_on_ceiling_enabled", IS_SLIDE_ON_CEILING_ENABLED_HASH)
        }

        private const val SET_PLATFORM_FLOOR_LAYERS_HASH = 1286410249L
        private val setPlatformFloorLayersBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_platform_floor_layers", SET_PLATFORM_FLOOR_LAYERS_HASH)
        }

        private const val GET_PLATFORM_FLOOR_LAYERS_HASH = 3905245786L
        private val getPlatformFloorLayersBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_platform_floor_layers", GET_PLATFORM_FLOOR_LAYERS_HASH)
        }

        private const val SET_PLATFORM_WALL_LAYERS_HASH = 1286410249L
        private val setPlatformWallLayersBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_platform_wall_layers", SET_PLATFORM_WALL_LAYERS_HASH)
        }

        private const val GET_PLATFORM_WALL_LAYERS_HASH = 3905245786L
        private val getPlatformWallLayersBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_platform_wall_layers", GET_PLATFORM_WALL_LAYERS_HASH)
        }

        private const val GET_MAX_SLIDES_HASH = 3905245786L
        private val getMaxSlidesBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_max_slides", GET_MAX_SLIDES_HASH)
        }

        private const val SET_MAX_SLIDES_HASH = 1286410249L
        private val setMaxSlidesBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_max_slides", SET_MAX_SLIDES_HASH)
        }

        private const val GET_FLOOR_MAX_ANGLE_HASH = 1740695150L
        private val getFloorMaxAngleBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_floor_max_angle", GET_FLOOR_MAX_ANGLE_HASH)
        }

        private const val SET_FLOOR_MAX_ANGLE_HASH = 373806689L
        private val setFloorMaxAngleBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_floor_max_angle", SET_FLOOR_MAX_ANGLE_HASH)
        }

        private const val GET_FLOOR_SNAP_LENGTH_HASH = 191475506L
        private val getFloorSnapLengthBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_floor_snap_length", GET_FLOOR_SNAP_LENGTH_HASH)
        }

        private const val SET_FLOOR_SNAP_LENGTH_HASH = 373806689L
        private val setFloorSnapLengthBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_floor_snap_length", SET_FLOOR_SNAP_LENGTH_HASH)
        }

        private const val GET_WALL_MIN_SLIDE_ANGLE_HASH = 1740695150L
        private val getWallMinSlideAngleBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_wall_min_slide_angle", GET_WALL_MIN_SLIDE_ANGLE_HASH)
        }

        private const val SET_WALL_MIN_SLIDE_ANGLE_HASH = 373806689L
        private val setWallMinSlideAngleBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_wall_min_slide_angle", SET_WALL_MIN_SLIDE_ANGLE_HASH)
        }

        private const val GET_UP_DIRECTION_HASH = 3341600327L
        private val getUpDirectionBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_up_direction", GET_UP_DIRECTION_HASH)
        }

        private const val SET_UP_DIRECTION_HASH = 743155724L
        private val setUpDirectionBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_up_direction", SET_UP_DIRECTION_HASH)
        }

        private const val SET_MOTION_MODE_HASH = 1224392233L
        private val setMotionModeBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_motion_mode", SET_MOTION_MODE_HASH)
        }

        private const val GET_MOTION_MODE_HASH = 1160151236L
        private val getMotionModeBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_motion_mode", GET_MOTION_MODE_HASH)
        }

        private const val SET_PLATFORM_ON_LEAVE_HASH = 2423324375L
        private val setPlatformOnLeaveBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "set_platform_on_leave", SET_PLATFORM_ON_LEAVE_HASH)
        }

        private const val GET_PLATFORM_ON_LEAVE_HASH = 4054324341L
        private val getPlatformOnLeaveBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_platform_on_leave", GET_PLATFORM_ON_LEAVE_HASH)
        }

        private const val IS_ON_FLOOR_HASH = 36873697L
        private val isOnFloorBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_on_floor", IS_ON_FLOOR_HASH)
        }

        private const val IS_ON_FLOOR_ONLY_HASH = 36873697L
        private val isOnFloorOnlyBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_on_floor_only", IS_ON_FLOOR_ONLY_HASH)
        }

        private const val IS_ON_CEILING_HASH = 36873697L
        private val isOnCeilingBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_on_ceiling", IS_ON_CEILING_HASH)
        }

        private const val IS_ON_CEILING_ONLY_HASH = 36873697L
        private val isOnCeilingOnlyBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_on_ceiling_only", IS_ON_CEILING_ONLY_HASH)
        }

        private const val IS_ON_WALL_HASH = 36873697L
        private val isOnWallBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_on_wall", IS_ON_WALL_HASH)
        }

        private const val IS_ON_WALL_ONLY_HASH = 36873697L
        private val isOnWallOnlyBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "is_on_wall_only", IS_ON_WALL_ONLY_HASH)
        }

        private const val GET_FLOOR_NORMAL_HASH = 3341600327L
        private val getFloorNormalBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_floor_normal", GET_FLOOR_NORMAL_HASH)
        }

        private const val GET_WALL_NORMAL_HASH = 3341600327L
        private val getWallNormalBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_wall_normal", GET_WALL_NORMAL_HASH)
        }

        private const val GET_LAST_MOTION_HASH = 3341600327L
        private val getLastMotionBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_last_motion", GET_LAST_MOTION_HASH)
        }

        private const val GET_POSITION_DELTA_HASH = 3341600327L
        private val getPositionDeltaBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_position_delta", GET_POSITION_DELTA_HASH)
        }

        private const val GET_REAL_VELOCITY_HASH = 3341600327L
        private val getRealVelocityBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_real_velocity", GET_REAL_VELOCITY_HASH)
        }

        private const val GET_FLOOR_ANGLE_HASH = 2841063350L
        private val getFloorAngleBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_floor_angle", GET_FLOOR_ANGLE_HASH)
        }

        private const val GET_PLATFORM_VELOCITY_HASH = 3341600327L
        private val getPlatformVelocityBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_platform_velocity", GET_PLATFORM_VELOCITY_HASH)
        }

        private const val GET_SLIDE_COLLISION_COUNT_HASH = 3905245786L
        private val getSlideCollisionCountBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_slide_collision_count", GET_SLIDE_COLLISION_COUNT_HASH)
        }

        private const val GET_SLIDE_COLLISION_HASH = 860659811L
        private val getSlideCollisionBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_slide_collision", GET_SLIDE_COLLISION_HASH)
        }

        private const val GET_LAST_SLIDE_COLLISION_HASH = 2161834755L
        private val getLastSlideCollisionBind by lazy {
            ObjectCalls.getMethodBind("CharacterBody2D", "get_last_slide_collision", GET_LAST_SLIDE_COLLISION_HASH)
        }
    }
}

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

    fun moveAndSlide(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(moveAndSlideBind, handle)
    }

    fun applyFloorSnap() {
        ObjectCalls.ptrcallNoArgs(applyFloorSnapBind, handle)
    }

    fun setVelocity(velocity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setVelocityBind, handle, velocity)
    }

    fun getVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getVelocityBind, handle)
    }

    fun setSafeMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSafeMarginBind, handle, margin)
    }

    fun getSafeMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSafeMarginBind, handle)
    }

    fun isFloorStopOnSlopeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFloorStopOnSlopeEnabledBind, handle)
    }

    fun setFloorStopOnSlopeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFloorStopOnSlopeEnabledBind, handle, enabled)
    }

    fun setFloorConstantSpeedEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFloorConstantSpeedEnabledBind, handle, enabled)
    }

    fun isFloorConstantSpeedEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFloorConstantSpeedEnabledBind, handle)
    }

    fun setFloorBlockOnWallEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFloorBlockOnWallEnabledBind, handle, enabled)
    }

    fun isFloorBlockOnWallEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFloorBlockOnWallEnabledBind, handle)
    }

    fun setSlideOnCeilingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSlideOnCeilingEnabledBind, handle, enabled)
    }

    fun isSlideOnCeilingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSlideOnCeilingEnabledBind, handle)
    }

    fun setPlatformFloorLayers(excludeLayer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setPlatformFloorLayersBind, handle, excludeLayer)
    }

    fun getPlatformFloorLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getPlatformFloorLayersBind, handle)
    }

    fun setPlatformWallLayers(excludeLayer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setPlatformWallLayersBind, handle, excludeLayer)
    }

    fun getPlatformWallLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getPlatformWallLayersBind, handle)
    }

    fun getMaxSlides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxSlidesBind, handle)
    }

    fun setMaxSlides(maxSlides: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxSlidesBind, handle, maxSlides)
    }

    fun getFloorMaxAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloorMaxAngleBind, handle)
    }

    fun setFloorMaxAngle(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFloorMaxAngleBind, handle, radians)
    }

    fun getFloorSnapLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloorSnapLengthBind, handle)
    }

    fun setFloorSnapLength(floorSnapLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFloorSnapLengthBind, handle, floorSnapLength)
    }

    fun getWallMinSlideAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWallMinSlideAngleBind, handle)
    }

    fun setWallMinSlideAngle(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWallMinSlideAngleBind, handle, radians)
    }

    fun getUpDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getUpDirectionBind, handle)
    }

    fun setUpDirection(upDirection: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setUpDirectionBind, handle, upDirection)
    }

    fun setMotionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMotionModeBind, handle, mode)
    }

    fun getMotionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMotionModeBind, handle)
    }

    fun setPlatformOnLeave(onLeaveApplyVelocity: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlatformOnLeaveBind, handle, onLeaveApplyVelocity)
    }

    fun getPlatformOnLeave(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlatformOnLeaveBind, handle)
    }

    fun isOnFloor(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnFloorBind, handle)
    }

    fun isOnFloorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnFloorOnlyBind, handle)
    }

    fun isOnCeiling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnCeilingBind, handle)
    }

    fun isOnCeilingOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnCeilingOnlyBind, handle)
    }

    fun isOnWall(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnWallBind, handle)
    }

    fun isOnWallOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnWallOnlyBind, handle)
    }

    fun getFloorNormal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFloorNormalBind, handle)
    }

    fun getWallNormal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getWallNormalBind, handle)
    }

    fun getLastMotion(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLastMotionBind, handle)
    }

    fun getPositionDelta(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionDeltaBind, handle)
    }

    fun getRealVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRealVelocityBind, handle)
    }

    fun getFloorAngle(upDirection: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getFloorAngleBind, handle, upDirection)
    }

    fun getPlatformVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPlatformVelocityBind, handle)
    }

    fun getSlideCollisionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSlideCollisionCountBind, handle)
    }

    fun getSlideCollision(slideIdx: Int): KinematicCollision2D? {
        return KinematicCollision2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSlideCollisionBind, handle, slideIdx))
    }

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

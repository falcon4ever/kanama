package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A node used to create a parallax scrolling background.
 *
 * Generated from Godot docs: Parallax2D
 */
class Parallax2D(handle: MemorySegment) : Node2D(handle) {
    var scrollScale: Vector2
        @JvmName("scrollScaleProperty")
        get() = getScrollScale()
        @JvmName("setScrollScaleProperty")
        set(value) = setScrollScale(value)

    var scrollOffset: Vector2
        @JvmName("scrollOffsetProperty")
        get() = getScrollOffset()
        @JvmName("setScrollOffsetProperty")
        set(value) = setScrollOffset(value)

    var repeatSize: Vector2
        @JvmName("repeatSizeProperty")
        get() = getRepeatSize()
        @JvmName("setRepeatSizeProperty")
        set(value) = setRepeatSize(value)

    var autoscroll: Vector2
        @JvmName("autoscrollProperty")
        get() = getAutoscroll()
        @JvmName("setAutoscrollProperty")
        set(value) = setAutoscroll(value)

    var repeatTimes: Int
        @JvmName("repeatTimesProperty")
        get() = getRepeatTimes()
        @JvmName("setRepeatTimesProperty")
        set(value) = setRepeatTimes(value)

    var limitBegin: Vector2
        @JvmName("limitBeginProperty")
        get() = getLimitBegin()
        @JvmName("setLimitBeginProperty")
        set(value) = setLimitBegin(value)

    var limitEnd: Vector2
        @JvmName("limitEndProperty")
        get() = getLimitEnd()
        @JvmName("setLimitEndProperty")
        set(value) = setLimitEnd(value)

    var followViewport: Boolean
        @JvmName("followViewportProperty")
        get() = getFollowViewport()
        @JvmName("setFollowViewportProperty")
        set(value) = setFollowViewport(value)

    var ignoreCameraScroll: Boolean
        @JvmName("ignoreCameraScrollProperty")
        get() = isIgnoreCameraScroll()
        @JvmName("setIgnoreCameraScrollProperty")
        set(value) = setIgnoreCameraScroll(value)

    var screenOffset: Vector2
        @JvmName("screenOffsetProperty")
        get() = getScreenOffset()
        @JvmName("setScreenOffsetProperty")
        set(value) = setScreenOffset(value)

    /**
     * Multiplier to the final `Parallax2D`'s offset. Can be used to simulate distance from the camera.
     * For example, a value of `1` scrolls at the same speed as the camera. A value greater than `1`
     * scrolls faster, making objects appear closer. Less than `1` scrolls slower, making objects
     * appear further, and a value of `0` stops the objects completely.
     *
     * Generated from Godot docs: Parallax2D.set_scroll_scale
     */
    fun setScrollScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollScaleBind, handle, scale)
    }

    /**
     * Multiplier to the final `Parallax2D`'s offset. Can be used to simulate distance from the camera.
     * For example, a value of `1` scrolls at the same speed as the camera. A value greater than `1`
     * scrolls faster, making objects appear closer. Less than `1` scrolls slower, making objects
     * appear further, and a value of `0` stops the objects completely.
     *
     * Generated from Godot docs: Parallax2D.get_scroll_scale
     */
    fun getScrollScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollScaleBind, handle)
    }

    /**
     * Repeats the `Texture2D` of each of this node's children and offsets them by this value. When
     * scrolling, the node's position loops, giving the illusion of an infinite scrolling background if
     * the values are larger than the screen size. If an axis is set to `0`, the `Texture2D` will not
     * be repeated.
     *
     * Generated from Godot docs: Parallax2D.set_repeat_size
     */
    fun setRepeatSize(repeatSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setRepeatSizeBind, handle, repeatSize)
    }

    /**
     * Repeats the `Texture2D` of each of this node's children and offsets them by this value. When
     * scrolling, the node's position loops, giving the illusion of an infinite scrolling background if
     * the values are larger than the screen size. If an axis is set to `0`, the `Texture2D` will not
     * be repeated.
     *
     * Generated from Godot docs: Parallax2D.get_repeat_size
     */
    fun getRepeatSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRepeatSizeBind, handle)
    }

    /**
     * Overrides the amount of times the texture repeats. Each texture copy spreads evenly from the
     * original by `repeat_size`. Useful for when zooming out with a camera.
     *
     * Generated from Godot docs: Parallax2D.set_repeat_times
     */
    fun setRepeatTimes(repeatTimes: Int) {
        ObjectCalls.ptrcallWithIntArg(setRepeatTimesBind, handle, repeatTimes)
    }

    /**
     * Overrides the amount of times the texture repeats. Each texture copy spreads evenly from the
     * original by `repeat_size`. Useful for when zooming out with a camera.
     *
     * Generated from Godot docs: Parallax2D.get_repeat_times
     */
    fun getRepeatTimes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRepeatTimesBind, handle)
    }

    /**
     * Velocity at which the offset scrolls automatically, in pixels per second.
     *
     * Generated from Godot docs: Parallax2D.set_autoscroll
     */
    fun setAutoscroll(autoscroll: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setAutoscrollBind, handle, autoscroll)
    }

    /**
     * Velocity at which the offset scrolls automatically, in pixels per second.
     *
     * Generated from Godot docs: Parallax2D.get_autoscroll
     */
    fun getAutoscroll(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getAutoscrollBind, handle)
    }

    /**
     * The `Parallax2D`'s offset. Similar to `screen_offset` and `Node2D.position`, but will not be
     * overridden. Note: Values will loop if `repeat_size` is set higher than `0`.
     *
     * Generated from Godot docs: Parallax2D.set_scroll_offset
     */
    fun setScrollOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollOffsetBind, handle, offset)
    }

    /**
     * The `Parallax2D`'s offset. Similar to `screen_offset` and `Node2D.position`, but will not be
     * overridden. Note: Values will loop if `repeat_size` is set higher than `0`.
     *
     * Generated from Godot docs: Parallax2D.get_scroll_offset
     */
    fun getScrollOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollOffsetBind, handle)
    }

    /**
     * Offset used to scroll this `Parallax2D`. This value is updated automatically unless
     * `ignore_camera_scroll` is `true`.
     *
     * Generated from Godot docs: Parallax2D.set_screen_offset
     */
    fun setScreenOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScreenOffsetBind, handle, offset)
    }

    /**
     * Offset used to scroll this `Parallax2D`. This value is updated automatically unless
     * `ignore_camera_scroll` is `true`.
     *
     * Generated from Godot docs: Parallax2D.get_screen_offset
     */
    fun getScreenOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenOffsetBind, handle)
    }

    /**
     * Top-left limits for scrolling to begin. If the camera is outside of this limit, the `Parallax2D`
     * stops scrolling. Must be lower than `limit_end` minus the viewport size to work.
     *
     * Generated from Godot docs: Parallax2D.set_limit_begin
     */
    fun setLimitBegin(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setLimitBeginBind, handle, offset)
    }

    /**
     * Top-left limits for scrolling to begin. If the camera is outside of this limit, the `Parallax2D`
     * stops scrolling. Must be lower than `limit_end` minus the viewport size to work.
     *
     * Generated from Godot docs: Parallax2D.get_limit_begin
     */
    fun getLimitBegin(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLimitBeginBind, handle)
    }

    /**
     * Bottom-right limits for scrolling to end. If the camera is outside of this limit, the
     * `Parallax2D` will stop scrolling. Must be higher than `limit_begin` and the viewport size
     * combined to work.
     *
     * Generated from Godot docs: Parallax2D.set_limit_end
     */
    fun setLimitEnd(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setLimitEndBind, handle, offset)
    }

    /**
     * Bottom-right limits for scrolling to end. If the camera is outside of this limit, the
     * `Parallax2D` will stop scrolling. Must be higher than `limit_begin` and the viewport size
     * combined to work.
     *
     * Generated from Godot docs: Parallax2D.get_limit_end
     */
    fun getLimitEnd(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLimitEndBind, handle)
    }

    /**
     * If `true`, this `Parallax2D` is offset by the current camera's position. If the `Parallax2D` is
     * in a `CanvasLayer` separate from the current camera, it may be desired to match the value with
     * `CanvasLayer.follow_viewport_enabled`.
     *
     * Generated from Godot docs: Parallax2D.set_follow_viewport
     */
    fun setFollowViewport(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowViewportBind, handle, follow)
    }

    /**
     * If `true`, this `Parallax2D` is offset by the current camera's position. If the `Parallax2D` is
     * in a `CanvasLayer` separate from the current camera, it may be desired to match the value with
     * `CanvasLayer.follow_viewport_enabled`.
     *
     * Generated from Godot docs: Parallax2D.get_follow_viewport
     */
    fun getFollowViewport(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFollowViewportBind, handle)
    }

    /**
     * If `true`, `Parallax2D`'s position is not affected by the position of the camera.
     *
     * Generated from Godot docs: Parallax2D.set_ignore_camera_scroll
     */
    fun setIgnoreCameraScroll(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreCameraScrollBind, handle, ignore)
    }

    /**
     * If `true`, `Parallax2D`'s position is not affected by the position of the camera.
     *
     * Generated from Godot docs: Parallax2D.is_ignore_camera_scroll
     */
    fun isIgnoreCameraScroll(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoreCameraScrollBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Parallax2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Parallax2D? =
            if (handle.address() == 0L) null else Parallax2D(handle)

        private const val SET_SCROLL_SCALE_HASH = 743155724L
        private val setScrollScaleBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_scroll_scale", SET_SCROLL_SCALE_HASH)
        }

        private const val GET_SCROLL_SCALE_HASH = 3341600327L
        private val getScrollScaleBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_scroll_scale", GET_SCROLL_SCALE_HASH)
        }

        private const val SET_REPEAT_SIZE_HASH = 743155724L
        private val setRepeatSizeBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_repeat_size", SET_REPEAT_SIZE_HASH)
        }

        private const val GET_REPEAT_SIZE_HASH = 3341600327L
        private val getRepeatSizeBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_repeat_size", GET_REPEAT_SIZE_HASH)
        }

        private const val SET_REPEAT_TIMES_HASH = 1286410249L
        private val setRepeatTimesBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_repeat_times", SET_REPEAT_TIMES_HASH)
        }

        private const val GET_REPEAT_TIMES_HASH = 3905245786L
        private val getRepeatTimesBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_repeat_times", GET_REPEAT_TIMES_HASH)
        }

        private const val SET_AUTOSCROLL_HASH = 743155724L
        private val setAutoscrollBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_autoscroll", SET_AUTOSCROLL_HASH)
        }

        private const val GET_AUTOSCROLL_HASH = 3341600327L
        private val getAutoscrollBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_autoscroll", GET_AUTOSCROLL_HASH)
        }

        private const val SET_SCROLL_OFFSET_HASH = 743155724L
        private val setScrollOffsetBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_scroll_offset", SET_SCROLL_OFFSET_HASH)
        }

        private const val GET_SCROLL_OFFSET_HASH = 3341600327L
        private val getScrollOffsetBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)
        }

        private const val SET_SCREEN_OFFSET_HASH = 743155724L
        private val setScreenOffsetBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_screen_offset", SET_SCREEN_OFFSET_HASH)
        }

        private const val GET_SCREEN_OFFSET_HASH = 3341600327L
        private val getScreenOffsetBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_screen_offset", GET_SCREEN_OFFSET_HASH)
        }

        private const val SET_LIMIT_BEGIN_HASH = 743155724L
        private val setLimitBeginBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_limit_begin", SET_LIMIT_BEGIN_HASH)
        }

        private const val GET_LIMIT_BEGIN_HASH = 3341600327L
        private val getLimitBeginBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_limit_begin", GET_LIMIT_BEGIN_HASH)
        }

        private const val SET_LIMIT_END_HASH = 743155724L
        private val setLimitEndBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_limit_end", SET_LIMIT_END_HASH)
        }

        private const val GET_LIMIT_END_HASH = 3341600327L
        private val getLimitEndBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_limit_end", GET_LIMIT_END_HASH)
        }

        private const val SET_FOLLOW_VIEWPORT_HASH = 2586408642L
        private val setFollowViewportBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_follow_viewport", SET_FOLLOW_VIEWPORT_HASH)
        }

        private const val GET_FOLLOW_VIEWPORT_HASH = 2240911060L
        private val getFollowViewportBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "get_follow_viewport", GET_FOLLOW_VIEWPORT_HASH)
        }

        private const val SET_IGNORE_CAMERA_SCROLL_HASH = 2586408642L
        private val setIgnoreCameraScrollBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "set_ignore_camera_scroll", SET_IGNORE_CAMERA_SCROLL_HASH)
        }

        private const val IS_IGNORE_CAMERA_SCROLL_HASH = 2240911060L
        private val isIgnoreCameraScrollBind by lazy {
            ObjectCalls.getMethodBind("Parallax2D", "is_ignore_camera_scroll", IS_IGNORE_CAMERA_SCROLL_HASH)
        }
    }
}

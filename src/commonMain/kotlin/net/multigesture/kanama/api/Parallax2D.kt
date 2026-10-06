package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A node used to create a parallax scrolling background.
 *
 * Generated from Godot docs: Parallax2D
 */
class Parallax2D(handle: GodotHandle) : Node2D(handle) {
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
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScrollScaleBind, segment, scale)
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
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScrollScaleBind, segment)
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
        ObjectCalls.ptrcallWithVector2Arg(Binds.setRepeatSizeBind, segment, repeatSize)
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
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getRepeatSizeBind, segment)
    }

    /**
     * Overrides the amount of times the texture repeats. Each texture copy spreads evenly from the
     * original by `repeat_size`. Useful for when zooming out with a camera.
     *
     * Generated from Godot docs: Parallax2D.set_repeat_times
     */
    fun setRepeatTimes(repeatTimes: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRepeatTimesBind, segment, repeatTimes)
    }

    /**
     * Overrides the amount of times the texture repeats. Each texture copy spreads evenly from the
     * original by `repeat_size`. Useful for when zooming out with a camera.
     *
     * Generated from Godot docs: Parallax2D.get_repeat_times
     */
    fun getRepeatTimes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRepeatTimesBind, segment)
    }

    /**
     * Velocity at which the offset scrolls automatically, in pixels per second.
     *
     * Generated from Godot docs: Parallax2D.set_autoscroll
     */
    fun setAutoscroll(autoscroll: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setAutoscrollBind, segment, autoscroll)
    }

    /**
     * Velocity at which the offset scrolls automatically, in pixels per second.
     *
     * Generated from Godot docs: Parallax2D.get_autoscroll
     */
    fun getAutoscroll(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getAutoscrollBind, segment)
    }

    /**
     * The `Parallax2D`'s offset. Similar to `screen_offset` and `Node2D.position`, but will not be
     * overridden. Note: Values will loop if `repeat_size` is set higher than `0`.
     *
     * Generated from Godot docs: Parallax2D.set_scroll_offset
     */
    fun setScrollOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScrollOffsetBind, segment, offset)
    }

    /**
     * The `Parallax2D`'s offset. Similar to `screen_offset` and `Node2D.position`, but will not be
     * overridden. Note: Values will loop if `repeat_size` is set higher than `0`.
     *
     * Generated from Godot docs: Parallax2D.get_scroll_offset
     */
    fun getScrollOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScrollOffsetBind, segment)
    }

    /**
     * Offset used to scroll this `Parallax2D`. This value is updated automatically unless
     * `ignore_camera_scroll` is `true`.
     *
     * Generated from Godot docs: Parallax2D.set_screen_offset
     */
    fun setScreenOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScreenOffsetBind, segment, offset)
    }

    /**
     * Offset used to scroll this `Parallax2D`. This value is updated automatically unless
     * `ignore_camera_scroll` is `true`.
     *
     * Generated from Godot docs: Parallax2D.get_screen_offset
     */
    fun getScreenOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScreenOffsetBind, segment)
    }

    /**
     * Top-left limits for scrolling to begin. If the camera is outside of this limit, the `Parallax2D`
     * stops scrolling. Must be lower than `limit_end` minus the viewport size to work.
     *
     * Generated from Godot docs: Parallax2D.set_limit_begin
     */
    fun setLimitBegin(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setLimitBeginBind, segment, offset)
    }

    /**
     * Top-left limits for scrolling to begin. If the camera is outside of this limit, the `Parallax2D`
     * stops scrolling. Must be lower than `limit_end` minus the viewport size to work.
     *
     * Generated from Godot docs: Parallax2D.get_limit_begin
     */
    fun getLimitBegin(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getLimitBeginBind, segment)
    }

    /**
     * Bottom-right limits for scrolling to end. If the camera is outside of this limit, the
     * `Parallax2D` will stop scrolling. Must be higher than `limit_begin` and the viewport size
     * combined to work.
     *
     * Generated from Godot docs: Parallax2D.set_limit_end
     */
    fun setLimitEnd(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setLimitEndBind, segment, offset)
    }

    /**
     * Bottom-right limits for scrolling to end. If the camera is outside of this limit, the
     * `Parallax2D` will stop scrolling. Must be higher than `limit_begin` and the viewport size
     * combined to work.
     *
     * Generated from Godot docs: Parallax2D.get_limit_end
     */
    fun getLimitEnd(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getLimitEndBind, segment)
    }

    /**
     * If `true`, this `Parallax2D` is offset by the current camera's position. If the `Parallax2D` is
     * in a `CanvasLayer` separate from the current camera, it may be desired to match the value with
     * `CanvasLayer.follow_viewport_enabled`.
     *
     * Generated from Godot docs: Parallax2D.set_follow_viewport
     */
    fun setFollowViewport(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFollowViewportBind, segment, follow)
    }

    /**
     * If `true`, this `Parallax2D` is offset by the current camera's position. If the `Parallax2D` is
     * in a `CanvasLayer` separate from the current camera, it may be desired to match the value with
     * `CanvasLayer.follow_viewport_enabled`.
     *
     * Generated from Godot docs: Parallax2D.get_follow_viewport
     */
    fun getFollowViewport(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFollowViewportBind, segment)
    }

    /**
     * If `true`, `Parallax2D`'s position is not affected by the position of the camera.
     *
     * Generated from Godot docs: Parallax2D.set_ignore_camera_scroll
     */
    fun setIgnoreCameraScroll(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setIgnoreCameraScrollBind, segment, ignore)
    }

    /**
     * If `true`, `Parallax2D`'s position is not affected by the position of the camera.
     *
     * Generated from Godot docs: Parallax2D.is_ignore_camera_scroll
     */
    fun isIgnoreCameraScroll(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isIgnoreCameraScrollBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Parallax2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Parallax2D? =
            if (handle.address() == 0L) null else Parallax2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SCROLL_SCALE_HASH = 743155724L
        @JvmField
        val setScrollScaleBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_scroll_scale", SET_SCROLL_SCALE_HASH)

        private const val GET_SCROLL_SCALE_HASH = 3341600327L
        @JvmField
        val getScrollScaleBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_scroll_scale", GET_SCROLL_SCALE_HASH)

        private const val SET_REPEAT_SIZE_HASH = 743155724L
        @JvmField
        val setRepeatSizeBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_repeat_size", SET_REPEAT_SIZE_HASH)

        private const val GET_REPEAT_SIZE_HASH = 3341600327L
        @JvmField
        val getRepeatSizeBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_repeat_size", GET_REPEAT_SIZE_HASH)

        private const val SET_REPEAT_TIMES_HASH = 1286410249L
        @JvmField
        val setRepeatTimesBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_repeat_times", SET_REPEAT_TIMES_HASH)

        private const val GET_REPEAT_TIMES_HASH = 3905245786L
        @JvmField
        val getRepeatTimesBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_repeat_times", GET_REPEAT_TIMES_HASH)

        private const val SET_AUTOSCROLL_HASH = 743155724L
        @JvmField
        val setAutoscrollBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_autoscroll", SET_AUTOSCROLL_HASH)

        private const val GET_AUTOSCROLL_HASH = 3341600327L
        @JvmField
        val getAutoscrollBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_autoscroll", GET_AUTOSCROLL_HASH)

        private const val SET_SCROLL_OFFSET_HASH = 743155724L
        @JvmField
        val setScrollOffsetBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_scroll_offset", SET_SCROLL_OFFSET_HASH)

        private const val GET_SCROLL_OFFSET_HASH = 3341600327L
        @JvmField
        val getScrollOffsetBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)

        private const val SET_SCREEN_OFFSET_HASH = 743155724L
        @JvmField
        val setScreenOffsetBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_screen_offset", SET_SCREEN_OFFSET_HASH)

        private const val GET_SCREEN_OFFSET_HASH = 3341600327L
        @JvmField
        val getScreenOffsetBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_screen_offset", GET_SCREEN_OFFSET_HASH)

        private const val SET_LIMIT_BEGIN_HASH = 743155724L
        @JvmField
        val setLimitBeginBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_limit_begin", SET_LIMIT_BEGIN_HASH)

        private const val GET_LIMIT_BEGIN_HASH = 3341600327L
        @JvmField
        val getLimitBeginBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_limit_begin", GET_LIMIT_BEGIN_HASH)

        private const val SET_LIMIT_END_HASH = 743155724L
        @JvmField
        val setLimitEndBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_limit_end", SET_LIMIT_END_HASH)

        private const val GET_LIMIT_END_HASH = 3341600327L
        @JvmField
        val getLimitEndBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_limit_end", GET_LIMIT_END_HASH)

        private const val SET_FOLLOW_VIEWPORT_HASH = 2586408642L
        @JvmField
        val setFollowViewportBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_follow_viewport", SET_FOLLOW_VIEWPORT_HASH)

        private const val GET_FOLLOW_VIEWPORT_HASH = 2240911060L
        @JvmField
        val getFollowViewportBind =
            ObjectCalls.getMethodBind("Parallax2D", "get_follow_viewport", GET_FOLLOW_VIEWPORT_HASH)

        private const val SET_IGNORE_CAMERA_SCROLL_HASH = 2586408642L
        @JvmField
        val setIgnoreCameraScrollBind =
            ObjectCalls.getMethodBind("Parallax2D", "set_ignore_camera_scroll", SET_IGNORE_CAMERA_SCROLL_HASH)

        private const val IS_IGNORE_CAMERA_SCROLL_HASH = 2240911060L
        @JvmField
        val isIgnoreCameraScrollBind =
            ObjectCalls.getMethodBind("Parallax2D", "is_ignore_camera_scroll", IS_IGNORE_CAMERA_SCROLL_HASH)
    }
}

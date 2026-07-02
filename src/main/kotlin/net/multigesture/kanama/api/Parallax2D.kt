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

    fun setScrollScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollScaleBind, handle, scale)
    }

    fun getScrollScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollScaleBind, handle)
    }

    fun setRepeatSize(repeatSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setRepeatSizeBind, handle, repeatSize)
    }

    fun getRepeatSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRepeatSizeBind, handle)
    }

    fun setRepeatTimes(repeatTimes: Int) {
        ObjectCalls.ptrcallWithIntArg(setRepeatTimesBind, handle, repeatTimes)
    }

    fun getRepeatTimes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRepeatTimesBind, handle)
    }

    fun setAutoscroll(autoscroll: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setAutoscrollBind, handle, autoscroll)
    }

    fun getAutoscroll(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getAutoscrollBind, handle)
    }

    fun setScrollOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollOffsetBind, handle, offset)
    }

    fun getScrollOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollOffsetBind, handle)
    }

    fun setScreenOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScreenOffsetBind, handle, offset)
    }

    fun getScreenOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenOffsetBind, handle)
    }

    fun setLimitBegin(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setLimitBeginBind, handle, offset)
    }

    fun getLimitBegin(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLimitBeginBind, handle)
    }

    fun setLimitEnd(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setLimitEndBind, handle, offset)
    }

    fun getLimitEnd(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLimitEndBind, handle)
    }

    fun setFollowViewport(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowViewportBind, handle, follow)
    }

    fun getFollowViewport(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFollowViewportBind, handle)
    }

    fun setIgnoreCameraScroll(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreCameraScrollBind, handle, ignore)
    }

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

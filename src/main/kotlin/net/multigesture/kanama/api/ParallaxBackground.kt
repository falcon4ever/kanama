package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A node used to create a parallax scrolling background.
 *
 * Generated from Godot docs: ParallaxBackground
 */
class ParallaxBackground(handle: MemorySegment) : CanvasLayer(handle) {
    var scrollOffset: Vector2
        @JvmName("scrollOffsetProperty")
        get() = getScrollOffset()
        @JvmName("setScrollOffsetProperty")
        set(value) = setScrollOffset(value)

    var scrollBaseOffset: Vector2
        @JvmName("scrollBaseOffsetProperty")
        get() = getScrollBaseOffset()
        @JvmName("setScrollBaseOffsetProperty")
        set(value) = setScrollBaseOffset(value)

    var scrollBaseScale: Vector2
        @JvmName("scrollBaseScaleProperty")
        get() = getScrollBaseScale()
        @JvmName("setScrollBaseScaleProperty")
        set(value) = setScrollBaseScale(value)

    var scrollLimitBegin: Vector2
        @JvmName("scrollLimitBeginProperty")
        get() = getLimitBegin()
        @JvmName("setScrollLimitBeginProperty")
        set(value) = setLimitBegin(value)

    var scrollLimitEnd: Vector2
        @JvmName("scrollLimitEndProperty")
        get() = getLimitEnd()
        @JvmName("setScrollLimitEndProperty")
        set(value) = setLimitEnd(value)

    var scrollIgnoreCameraZoom: Boolean
        @JvmName("scrollIgnoreCameraZoomProperty")
        get() = isIgnoreCameraZoom()
        @JvmName("setScrollIgnoreCameraZoomProperty")
        set(value) = setIgnoreCameraZoom(value)

    fun setScrollOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollOffsetBind, handle, offset)
    }

    fun getScrollOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollOffsetBind, handle)
    }

    fun setScrollBaseOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollBaseOffsetBind, handle, offset)
    }

    fun getScrollBaseOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollBaseOffsetBind, handle)
    }

    fun setScrollBaseScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScrollBaseScaleBind, handle, scale)
    }

    fun getScrollBaseScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollBaseScaleBind, handle)
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

    fun setIgnoreCameraZoom(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreCameraZoomBind, handle, ignore)
    }

    fun isIgnoreCameraZoom(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoreCameraZoomBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ParallaxBackground? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ParallaxBackground? =
            if (handle.address() == 0L) null else ParallaxBackground(handle)

        private const val SET_SCROLL_OFFSET_HASH = 743155724L
        private val setScrollOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "set_scroll_offset", SET_SCROLL_OFFSET_HASH)
        }

        private const val GET_SCROLL_OFFSET_HASH = 3341600327L
        private val getScrollOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)
        }

        private const val SET_SCROLL_BASE_OFFSET_HASH = 743155724L
        private val setScrollBaseOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "set_scroll_base_offset", SET_SCROLL_BASE_OFFSET_HASH)
        }

        private const val GET_SCROLL_BASE_OFFSET_HASH = 3341600327L
        private val getScrollBaseOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "get_scroll_base_offset", GET_SCROLL_BASE_OFFSET_HASH)
        }

        private const val SET_SCROLL_BASE_SCALE_HASH = 743155724L
        private val setScrollBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "set_scroll_base_scale", SET_SCROLL_BASE_SCALE_HASH)
        }

        private const val GET_SCROLL_BASE_SCALE_HASH = 3341600327L
        private val getScrollBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "get_scroll_base_scale", GET_SCROLL_BASE_SCALE_HASH)
        }

        private const val SET_LIMIT_BEGIN_HASH = 743155724L
        private val setLimitBeginBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "set_limit_begin", SET_LIMIT_BEGIN_HASH)
        }

        private const val GET_LIMIT_BEGIN_HASH = 3341600327L
        private val getLimitBeginBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "get_limit_begin", GET_LIMIT_BEGIN_HASH)
        }

        private const val SET_LIMIT_END_HASH = 743155724L
        private val setLimitEndBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "set_limit_end", SET_LIMIT_END_HASH)
        }

        private const val GET_LIMIT_END_HASH = 3341600327L
        private val getLimitEndBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "get_limit_end", GET_LIMIT_END_HASH)
        }

        private const val SET_IGNORE_CAMERA_ZOOM_HASH = 2586408642L
        private val setIgnoreCameraZoomBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "set_ignore_camera_zoom", SET_IGNORE_CAMERA_ZOOM_HASH)
        }

        private const val IS_IGNORE_CAMERA_ZOOM_HASH = 2240911060L
        private val isIgnoreCameraZoomBind by lazy {
            ObjectCalls.getMethodBind("ParallaxBackground", "is_ignore_camera_zoom", IS_IGNORE_CAMERA_ZOOM_HASH)
        }
    }
}

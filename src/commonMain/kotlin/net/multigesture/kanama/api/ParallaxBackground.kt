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
 * Generated from Godot docs: ParallaxBackground
 */
class ParallaxBackground(handle: GodotHandle) : CanvasLayer(handle) {
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

    /**
     * The ParallaxBackground's scroll value. Calculated automatically when using a `Camera2D`, but can
     * be used to manually manage scrolling when no camera is present.
     *
     * Generated from Godot docs: ParallaxBackground.set_scroll_offset
     */
    fun setScrollOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScrollOffsetBind, segment, offset)
    }

    /**
     * The ParallaxBackground's scroll value. Calculated automatically when using a `Camera2D`, but can
     * be used to manually manage scrolling when no camera is present.
     *
     * Generated from Godot docs: ParallaxBackground.get_scroll_offset
     */
    fun getScrollOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScrollOffsetBind, segment)
    }

    /**
     * The base position offset for all `ParallaxLayer` children.
     *
     * Generated from Godot docs: ParallaxBackground.set_scroll_base_offset
     */
    fun setScrollBaseOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScrollBaseOffsetBind, segment, offset)
    }

    /**
     * The base position offset for all `ParallaxLayer` children.
     *
     * Generated from Godot docs: ParallaxBackground.get_scroll_base_offset
     */
    fun getScrollBaseOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScrollBaseOffsetBind, segment)
    }

    /**
     * The base motion scale for all `ParallaxLayer` children.
     *
     * Generated from Godot docs: ParallaxBackground.set_scroll_base_scale
     */
    fun setScrollBaseScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScrollBaseScaleBind, segment, scale)
    }

    /**
     * The base motion scale for all `ParallaxLayer` children.
     *
     * Generated from Godot docs: ParallaxBackground.get_scroll_base_scale
     */
    fun getScrollBaseScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScrollBaseScaleBind, segment)
    }

    /**
     * Top-left limits for scrolling to begin. If the camera is outside of this limit, the background
     * will stop scrolling. Must be lower than `scroll_limit_end` to work.
     *
     * Generated from Godot docs: ParallaxBackground.set_limit_begin
     */
    fun setLimitBegin(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setLimitBeginBind, segment, offset)
    }

    /**
     * Top-left limits for scrolling to begin. If the camera is outside of this limit, the background
     * will stop scrolling. Must be lower than `scroll_limit_end` to work.
     *
     * Generated from Godot docs: ParallaxBackground.get_limit_begin
     */
    fun getLimitBegin(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getLimitBeginBind, segment)
    }

    /**
     * Bottom-right limits for scrolling to end. If the camera is outside of this limit, the background
     * will stop scrolling. Must be higher than `scroll_limit_begin` to work.
     *
     * Generated from Godot docs: ParallaxBackground.set_limit_end
     */
    fun setLimitEnd(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setLimitEndBind, segment, offset)
    }

    /**
     * Bottom-right limits for scrolling to end. If the camera is outside of this limit, the background
     * will stop scrolling. Must be higher than `scroll_limit_begin` to work.
     *
     * Generated from Godot docs: ParallaxBackground.get_limit_end
     */
    fun getLimitEnd(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getLimitEndBind, segment)
    }

    /**
     * If `true`, elements in `ParallaxLayer` child aren't affected by the zoom level of the camera.
     *
     * Generated from Godot docs: ParallaxBackground.set_ignore_camera_zoom
     */
    fun setIgnoreCameraZoom(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setIgnoreCameraZoomBind, segment, ignore)
    }

    /**
     * If `true`, elements in `ParallaxLayer` child aren't affected by the zoom level of the camera.
     *
     * Generated from Godot docs: ParallaxBackground.is_ignore_camera_zoom
     */
    fun isIgnoreCameraZoom(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isIgnoreCameraZoomBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ParallaxBackground? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ParallaxBackground? =
            if (handle.address() == 0L) null else ParallaxBackground(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SCROLL_OFFSET_HASH = 743155724L
        @JvmField
        val setScrollOffsetBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "set_scroll_offset", SET_SCROLL_OFFSET_HASH)

        private const val GET_SCROLL_OFFSET_HASH = 3341600327L
        @JvmField
        val getScrollOffsetBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)

        private const val SET_SCROLL_BASE_OFFSET_HASH = 743155724L
        @JvmField
        val setScrollBaseOffsetBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "set_scroll_base_offset", SET_SCROLL_BASE_OFFSET_HASH)

        private const val GET_SCROLL_BASE_OFFSET_HASH = 3341600327L
        @JvmField
        val getScrollBaseOffsetBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "get_scroll_base_offset", GET_SCROLL_BASE_OFFSET_HASH)

        private const val SET_SCROLL_BASE_SCALE_HASH = 743155724L
        @JvmField
        val setScrollBaseScaleBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "set_scroll_base_scale", SET_SCROLL_BASE_SCALE_HASH)

        private const val GET_SCROLL_BASE_SCALE_HASH = 3341600327L
        @JvmField
        val getScrollBaseScaleBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "get_scroll_base_scale", GET_SCROLL_BASE_SCALE_HASH)

        private const val SET_LIMIT_BEGIN_HASH = 743155724L
        @JvmField
        val setLimitBeginBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "set_limit_begin", SET_LIMIT_BEGIN_HASH)

        private const val GET_LIMIT_BEGIN_HASH = 3341600327L
        @JvmField
        val getLimitBeginBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "get_limit_begin", GET_LIMIT_BEGIN_HASH)

        private const val SET_LIMIT_END_HASH = 743155724L
        @JvmField
        val setLimitEndBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "set_limit_end", SET_LIMIT_END_HASH)

        private const val GET_LIMIT_END_HASH = 3341600327L
        @JvmField
        val getLimitEndBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "get_limit_end", GET_LIMIT_END_HASH)

        private const val SET_IGNORE_CAMERA_ZOOM_HASH = 2586408642L
        @JvmField
        val setIgnoreCameraZoomBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "set_ignore_camera_zoom", SET_IGNORE_CAMERA_ZOOM_HASH)

        private const val IS_IGNORE_CAMERA_ZOOM_HASH = 2240911060L
        @JvmField
        val isIgnoreCameraZoomBind =
            ObjectCalls.getMethodBind("ParallaxBackground", "is_ignore_camera_zoom", IS_IGNORE_CAMERA_ZOOM_HASH)
    }
}

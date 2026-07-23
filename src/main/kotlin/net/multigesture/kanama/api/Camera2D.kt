package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Camera node for 2D scenes.
 *
 * Generated from Godot docs: Camera2D
 */
class Camera2D(handle: MemorySegment) : Node2D(handle) {
    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var anchorMode: Long
        @JvmName("anchorModeProperty")
        get() = getAnchorMode()
        @JvmName("setAnchorModeProperty")
        set(value) = setAnchorMode(value)

    var ignoreRotation: Boolean
        @JvmName("ignoreRotationProperty")
        get() = isIgnoringRotation()
        @JvmName("setIgnoreRotationProperty")
        set(value) = setIgnoreRotation(value)

    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var zoom: Vector2
        @JvmName("zoomProperty")
        get() = getZoom()
        @JvmName("setZoomProperty")
        set(value) = setZoom(value)

    val customViewport: Node?
        @JvmName("customViewportProperty")
        get() = getCustomViewport()

    var processCallback: Long
        @JvmName("processCallbackProperty")
        get() = getProcessCallback()
        @JvmName("setProcessCallbackProperty")
        set(value) = setProcessCallback(value)

    var limitEnabled: Boolean
        @JvmName("limitEnabledProperty")
        get() = isLimitEnabled()
        @JvmName("setLimitEnabledProperty")
        set(value) = setLimitEnabled(value)

    var limitLeft: Int
        @JvmName("limitLeftProperty")
        get() = getLimit(0L)
        @JvmName("setLimitLeftProperty")
        set(value) = setLimit(0L, value)

    var limitTop: Int
        @JvmName("limitTopProperty")
        get() = getLimit(1L)
        @JvmName("setLimitTopProperty")
        set(value) = setLimit(1L, value)

    var limitRight: Int
        @JvmName("limitRightProperty")
        get() = getLimit(2L)
        @JvmName("setLimitRightProperty")
        set(value) = setLimit(2L, value)

    var limitBottom: Int
        @JvmName("limitBottomProperty")
        get() = getLimit(3L)
        @JvmName("setLimitBottomProperty")
        set(value) = setLimit(3L, value)

    var limitSmoothed: Boolean
        @JvmName("limitSmoothedProperty")
        get() = isLimitSmoothingEnabled()
        @JvmName("setLimitSmoothedProperty")
        set(value) = setLimitSmoothingEnabled(value)

    var positionSmoothingEnabled: Boolean
        @JvmName("positionSmoothingEnabledProperty")
        get() = isPositionSmoothingEnabled()
        @JvmName("setPositionSmoothingEnabledProperty")
        set(value) = setPositionSmoothingEnabled(value)

    var positionSmoothingSpeed: Double
        @JvmName("positionSmoothingSpeedProperty")
        get() = getPositionSmoothingSpeed()
        @JvmName("setPositionSmoothingSpeedProperty")
        set(value) = setPositionSmoothingSpeed(value)

    var rotationSmoothingEnabled: Boolean
        @JvmName("rotationSmoothingEnabledProperty")
        get() = isRotationSmoothingEnabled()
        @JvmName("setRotationSmoothingEnabledProperty")
        set(value) = setRotationSmoothingEnabled(value)

    var rotationSmoothingSpeed: Double
        @JvmName("rotationSmoothingSpeedProperty")
        get() = getRotationSmoothingSpeed()
        @JvmName("setRotationSmoothingSpeedProperty")
        set(value) = setRotationSmoothingSpeed(value)

    var dragHorizontalEnabled: Boolean
        @JvmName("dragHorizontalEnabledProperty")
        get() = isDragHorizontalEnabled()
        @JvmName("setDragHorizontalEnabledProperty")
        set(value) = setDragHorizontalEnabled(value)

    var dragVerticalEnabled: Boolean
        @JvmName("dragVerticalEnabledProperty")
        get() = isDragVerticalEnabled()
        @JvmName("setDragVerticalEnabledProperty")
        set(value) = setDragVerticalEnabled(value)

    var dragHorizontalOffset: Double
        @JvmName("dragHorizontalOffsetProperty")
        get() = getDragHorizontalOffset()
        @JvmName("setDragHorizontalOffsetProperty")
        set(value) = setDragHorizontalOffset(value)

    var dragVerticalOffset: Double
        @JvmName("dragVerticalOffsetProperty")
        get() = getDragVerticalOffset()
        @JvmName("setDragVerticalOffsetProperty")
        set(value) = setDragVerticalOffset(value)

    var dragLeftMargin: Double
        @JvmName("dragLeftMarginProperty")
        get() = getDragMargin(0L)
        @JvmName("setDragLeftMarginProperty")
        set(value) = setDragMargin(0L, value)

    var dragTopMargin: Double
        @JvmName("dragTopMarginProperty")
        get() = getDragMargin(1L)
        @JvmName("setDragTopMarginProperty")
        set(value) = setDragMargin(1L, value)

    var dragRightMargin: Double
        @JvmName("dragRightMarginProperty")
        get() = getDragMargin(2L)
        @JvmName("setDragRightMarginProperty")
        set(value) = setDragMargin(2L, value)

    var dragBottomMargin: Double
        @JvmName("dragBottomMarginProperty")
        get() = getDragMargin(3L)
        @JvmName("setDragBottomMarginProperty")
        set(value) = setDragMargin(3L, value)

    var editorDrawScreen: Boolean
        @JvmName("editorDrawScreenProperty")
        get() = isScreenDrawingEnabled()
        @JvmName("setEditorDrawScreenProperty")
        set(value) = setScreenDrawingEnabled(value)

    var editorDrawLimits: Boolean
        @JvmName("editorDrawLimitsProperty")
        get() = isLimitDrawingEnabled()
        @JvmName("setEditorDrawLimitsProperty")
        set(value) = setLimitDrawingEnabled(value)

    var editorDrawDragMargin: Boolean
        @JvmName("editorDrawDragMarginProperty")
        get() = isMarginDrawingEnabled()
        @JvmName("setEditorDrawDragMarginProperty")
        set(value) = setMarginDrawingEnabled(value)

    /**
     * The camera's relative offset. Useful for looking around or camera shake animations. The
     * offsetted camera can go past the limits defined in `limit_top`, `limit_bottom`, `limit_left` and
     * `limit_right`.
     *
     * Generated from Godot docs: Camera2D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The camera's relative offset. Useful for looking around or camera shake animations. The
     * offsetted camera can go past the limits defined in `limit_top`, `limit_bottom`, `limit_left` and
     * `limit_right`.
     *
     * Generated from Godot docs: Camera2D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * The Camera2D's anchor point.
     *
     * Generated from Godot docs: Camera2D.set_anchor_mode
     */
    fun setAnchorMode(anchorMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAnchorModeBind, handle, anchorMode)
    }

    /**
     * The Camera2D's anchor point.
     *
     * Generated from Godot docs: Camera2D.get_anchor_mode
     */
    fun getAnchorMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAnchorModeBind, handle)
    }

    /**
     * If `true`, the camera's rendered view is not affected by its `Node2D.rotation` and
     * `Node2D.global_rotation`.
     *
     * Generated from Godot docs: Camera2D.set_ignore_rotation
     */
    fun setIgnoreRotation(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreRotationBind, handle, ignore)
    }

    /**
     * If `true`, the camera's rendered view is not affected by its `Node2D.rotation` and
     * `Node2D.global_rotation`.
     *
     * Generated from Godot docs: Camera2D.is_ignoring_rotation
     */
    fun isIgnoringRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringRotationBind, handle)
    }

    /**
     * The camera's process callback.
     *
     * Generated from Godot docs: Camera2D.set_process_callback
     */
    fun setProcessCallback(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessCallbackBind, handle, mode)
    }

    /**
     * The camera's process callback.
     *
     * Generated from Godot docs: Camera2D.get_process_callback
     */
    fun getProcessCallback(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessCallbackBind, handle)
    }

    /**
     * Controls whether the camera can be active or not. If `true`, the `Camera2D` will become the main
     * camera when it enters the scene tree and there is no active camera currently (see
     * `Viewport.get_camera_2d`). When the camera is currently active and `enabled` is set to `false`,
     * the next enabled `Camera2D` in the scene tree will become active.
     *
     * Generated from Godot docs: Camera2D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    /**
     * Controls whether the camera can be active or not. If `true`, the `Camera2D` will become the main
     * camera when it enters the scene tree and there is no active camera currently (see
     * `Viewport.get_camera_2d`). When the camera is currently active and `enabled` is set to `false`,
     * the next enabled `Camera2D` in the scene tree will become active.
     *
     * Generated from Godot docs: Camera2D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    /**
     * Forces this `Camera2D` to become the current active one. `enabled` must be `true`.
     *
     * Generated from Godot docs: Camera2D.make_current
     */
    fun makeCurrent() {
        ObjectCalls.ptrcallNoArgs(makeCurrentBind, handle)
    }

    /**
     * Returns `true` if this `Camera2D` is the active camera (see `Viewport.get_camera_2d`).
     *
     * Generated from Godot docs: Camera2D.is_current
     */
    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, handle)
    }

    /**
     * If `true`, the limits will be enabled. Disabling this will allow the camera to focus anywhere,
     * when the four `limit_*` properties will not work.
     *
     * Generated from Godot docs: Camera2D.set_limit_enabled
     */
    fun setLimitEnabled(limitEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLimitEnabledBind, handle, limitEnabled)
    }

    /**
     * If `true`, the limits will be enabled. Disabling this will allow the camera to focus anywhere,
     * when the four `limit_*` properties will not work.
     *
     * Generated from Godot docs: Camera2D.is_limit_enabled
     */
    fun isLimitEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLimitEnabledBind, handle)
    }

    /**
     * Top scroll limit in pixels. The camera stops moving when reaching this value, but `offset` can
     * push the view past the limit.
     *
     * Generated from Godot docs: Camera2D.set_limit
     */
    fun setLimit(margin: Long, limit: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setLimitBind, handle, margin, limit)
    }

    /**
     * Top scroll limit in pixels. The camera stops moving when reaching this value, but `offset` can
     * push the view past the limit.
     *
     * Generated from Godot docs: Camera2D.get_limit
     */
    fun getLimit(margin: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getLimitBind, handle, margin)
    }

    /**
     * If `true`, the camera smoothly stops when reaches its limits. This property has no effect if
     * `position_smoothing_enabled` is `false`. Note: To immediately update the camera's position to be
     * within limits without smoothing, even with this setting enabled, invoke `reset_smoothing`.
     *
     * Generated from Godot docs: Camera2D.set_limit_smoothing_enabled
     */
    fun setLimitSmoothingEnabled(limitSmoothingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLimitSmoothingEnabledBind, handle, limitSmoothingEnabled)
    }

    /**
     * If `true`, the camera smoothly stops when reaches its limits. This property has no effect if
     * `position_smoothing_enabled` is `false`. Note: To immediately update the camera's position to be
     * within limits without smoothing, even with this setting enabled, invoke `reset_smoothing`.
     *
     * Generated from Godot docs: Camera2D.is_limit_smoothing_enabled
     */
    fun isLimitSmoothingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLimitSmoothingEnabledBind, handle)
    }

    /**
     * If `true`, the camera only moves when reaching the vertical (top and bottom) drag margins. If
     * `false`, the camera moves vertically regardless of the drag margins.
     *
     * Generated from Godot docs: Camera2D.set_drag_vertical_enabled
     */
    fun setDragVerticalEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragVerticalEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the camera only moves when reaching the vertical (top and bottom) drag margins. If
     * `false`, the camera moves vertically regardless of the drag margins.
     *
     * Generated from Godot docs: Camera2D.is_drag_vertical_enabled
     */
    fun isDragVerticalEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragVerticalEnabledBind, handle)
    }

    /**
     * If `true`, the camera only moves when reaching the horizontal (left and right) drag margins. If
     * `false`, the camera moves horizontally regardless of margins.
     *
     * Generated from Godot docs: Camera2D.set_drag_horizontal_enabled
     */
    fun setDragHorizontalEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragHorizontalEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the camera only moves when reaching the horizontal (left and right) drag margins. If
     * `false`, the camera moves horizontally regardless of margins.
     *
     * Generated from Godot docs: Camera2D.is_drag_horizontal_enabled
     */
    fun isDragHorizontalEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragHorizontalEnabledBind, handle)
    }

    /**
     * The relative vertical drag offset of the camera between the bottom (`-1`) and top (`1`) drag
     * margins. Note: Used to set the initial vertical drag offset; determine the current offset; or
     * force the current offset. It's not automatically updated when `drag_vertical_enabled` is `true`
     * or the drag margins are changed.
     *
     * Generated from Godot docs: Camera2D.set_drag_vertical_offset
     */
    fun setDragVerticalOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDragVerticalOffsetBind, handle, offset)
    }

    /**
     * The relative vertical drag offset of the camera between the bottom (`-1`) and top (`1`) drag
     * margins. Note: Used to set the initial vertical drag offset; determine the current offset; or
     * force the current offset. It's not automatically updated when `drag_vertical_enabled` is `true`
     * or the drag margins are changed.
     *
     * Generated from Godot docs: Camera2D.get_drag_vertical_offset
     */
    fun getDragVerticalOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDragVerticalOffsetBind, handle)
    }

    /**
     * The relative horizontal drag offset of the camera between the right (`-1`) and left (`1`) drag
     * margins. Note: Used to set the initial horizontal drag offset; determine the current offset; or
     * force the current offset. It's not automatically updated when `drag_horizontal_enabled` is
     * `true` or the drag margins are changed.
     *
     * Generated from Godot docs: Camera2D.set_drag_horizontal_offset
     */
    fun setDragHorizontalOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDragHorizontalOffsetBind, handle, offset)
    }

    /**
     * The relative horizontal drag offset of the camera between the right (`-1`) and left (`1`) drag
     * margins. Note: Used to set the initial horizontal drag offset; determine the current offset; or
     * force the current offset. It's not automatically updated when `drag_horizontal_enabled` is
     * `true` or the drag margins are changed.
     *
     * Generated from Godot docs: Camera2D.get_drag_horizontal_offset
     */
    fun getDragHorizontalOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDragHorizontalOffsetBind, handle)
    }

    /**
     * Top margin needed to drag the camera. A value of `1` makes the camera move only when reaching
     * the top edge of the screen.
     *
     * Generated from Godot docs: Camera2D.set_drag_margin
     */
    fun setDragMargin(margin: Long, dragMargin: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setDragMarginBind, handle, margin, dragMargin)
    }

    /**
     * Top margin needed to drag the camera. A value of `1` makes the camera move only when reaching
     * the top edge of the screen.
     *
     * Generated from Godot docs: Camera2D.get_drag_margin
     */
    fun getDragMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getDragMarginBind, handle, margin)
    }

    /**
     * Returns this camera's target position, in global coordinates. Note: The returned value is not
     * the same as `Node2D.global_position`, as it is affected by the drag properties. It is also not
     * the same as the current position if `position_smoothing_enabled` is `true` (see
     * `get_screen_center_position`).
     *
     * Generated from Godot docs: Camera2D.get_target_position
     */
    fun getTargetPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTargetPositionBind, handle)
    }

    /**
     * Returns the center of the screen from this camera's point of view, in global coordinates. Note:
     * The exact targeted position of the camera may be different. See `get_target_position`.
     *
     * Generated from Godot docs: Camera2D.get_screen_center_position
     */
    fun getScreenCenterPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenCenterPositionBind, handle)
    }

    /**
     * Returns the current screen rotation from this camera's point of view. Note: The screen rotation
     * can be different from `Node2D.global_rotation` if the camera is rotating smoothly due to
     * `rotation_smoothing_enabled`.
     *
     * Generated from Godot docs: Camera2D.get_screen_rotation
     */
    fun getScreenRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getScreenRotationBind, handle)
    }

    /**
     * The camera's zoom. Higher values are more zoomed in. For example, a zoom of `Vector2(2.0, 2.0)`
     * will be twice as zoomed in on each axis (the view covers an area four times smaller). In
     * contrast, a zoom of `Vector2(0.5, 0.5)` will be twice as zoomed out on each axis (the view
     * covers an area four times larger). The X and Y components should generally always be set to the
     * same value, unless you wish to stretch the camera view. Note: `FontFile.oversampling` does not
     * take `Camera2D` zoom into account. This means that zooming in/out will cause bitmap fonts and
     * rasterized (non-MSDF) dynamic fonts to appear blurry or pixelated unless the font is part of a
     * `CanvasLayer` that makes it ignore camera zoom. To ensure text remains crisp regardless of zoom,
     * you can enable MSDF font rendering by enabling
     * `ProjectSettings.gui/theme/default_font_multichannel_signed_distance_field` (applies to the
     * default project font only), or enabling Multichannel Signed Distance Field in the import options
     * of a DynamicFont for custom fonts. On system fonts,
     * `SystemFont.multichannel_signed_distance_field` can be enabled in the inspector.
     *
     * Generated from Godot docs: Camera2D.set_zoom
     */
    fun setZoom(zoom: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setZoomBind, handle, zoom)
    }

    /**
     * The camera's zoom. Higher values are more zoomed in. For example, a zoom of `Vector2(2.0, 2.0)`
     * will be twice as zoomed in on each axis (the view covers an area four times smaller). In
     * contrast, a zoom of `Vector2(0.5, 0.5)` will be twice as zoomed out on each axis (the view
     * covers an area four times larger). The X and Y components should generally always be set to the
     * same value, unless you wish to stretch the camera view. Note: `FontFile.oversampling` does not
     * take `Camera2D` zoom into account. This means that zooming in/out will cause bitmap fonts and
     * rasterized (non-MSDF) dynamic fonts to appear blurry or pixelated unless the font is part of a
     * `CanvasLayer` that makes it ignore camera zoom. To ensure text remains crisp regardless of zoom,
     * you can enable MSDF font rendering by enabling
     * `ProjectSettings.gui/theme/default_font_multichannel_signed_distance_field` (applies to the
     * default project font only), or enabling Multichannel Signed Distance Field in the import options
     * of a DynamicFont for custom fonts. On system fonts,
     * `SystemFont.multichannel_signed_distance_field` can be enabled in the inspector.
     *
     * Generated from Godot docs: Camera2D.get_zoom
     */
    fun getZoom(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getZoomBind, handle)
    }

    /**
     * The custom `Viewport` node attached to the `Camera2D`. If `null` or not a `Viewport`, uses the
     * default viewport instead.
     *
     * Generated from Godot docs: Camera2D.set_custom_viewport
     */
    fun setCustomViewport(viewport: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setCustomViewportBind, handle, listOf(viewport.handle))
    }

    /**
     * The custom `Viewport` node attached to the `Camera2D`. If `null` or not a `Viewport`, uses the
     * default viewport instead.
     *
     * Generated from Godot docs: Camera2D.get_custom_viewport
     */
    fun getCustomViewport(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCustomViewportBind, handle))
    }

    /**
     * Speed in pixels per second of the camera's smoothing effect when `position_smoothing_enabled` is
     * `true`.
     *
     * Generated from Godot docs: Camera2D.set_position_smoothing_speed
     */
    fun setPositionSmoothingSpeed(positionSmoothingSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPositionSmoothingSpeedBind, handle, positionSmoothingSpeed)
    }

    /**
     * Speed in pixels per second of the camera's smoothing effect when `position_smoothing_enabled` is
     * `true`.
     *
     * Generated from Godot docs: Camera2D.get_position_smoothing_speed
     */
    fun getPositionSmoothingSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPositionSmoothingSpeedBind, handle)
    }

    /**
     * If `true`, the camera's view smoothly moves towards its target position at
     * `position_smoothing_speed`.
     *
     * Generated from Godot docs: Camera2D.set_position_smoothing_enabled
     */
    fun setPositionSmoothingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPositionSmoothingEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the camera's view smoothly moves towards its target position at
     * `position_smoothing_speed`.
     *
     * Generated from Godot docs: Camera2D.is_position_smoothing_enabled
     */
    fun isPositionSmoothingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPositionSmoothingEnabledBind, handle)
    }

    /**
     * If `true`, the camera's view smoothly rotates, via asymptotic smoothing, to align with its
     * target rotation at `rotation_smoothing_speed`. Note: This property has no effect if
     * `ignore_rotation` is `true`.
     *
     * Generated from Godot docs: Camera2D.set_rotation_smoothing_enabled
     */
    fun setRotationSmoothingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRotationSmoothingEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the camera's view smoothly rotates, via asymptotic smoothing, to align with its
     * target rotation at `rotation_smoothing_speed`. Note: This property has no effect if
     * `ignore_rotation` is `true`.
     *
     * Generated from Godot docs: Camera2D.is_rotation_smoothing_enabled
     */
    fun isRotationSmoothingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRotationSmoothingEnabledBind, handle)
    }

    /**
     * The angular, asymptotic speed of the camera's rotation smoothing effect when
     * `rotation_smoothing_enabled` is `true`.
     *
     * Generated from Godot docs: Camera2D.set_rotation_smoothing_speed
     */
    fun setRotationSmoothingSpeed(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationSmoothingSpeedBind, handle, speed)
    }

    /**
     * The angular, asymptotic speed of the camera's rotation smoothing effect when
     * `rotation_smoothing_enabled` is `true`.
     *
     * Generated from Godot docs: Camera2D.get_rotation_smoothing_speed
     */
    fun getRotationSmoothingSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationSmoothingSpeedBind, handle)
    }

    /**
     * Forces the camera to update scroll immediately.
     *
     * Generated from Godot docs: Camera2D.force_update_scroll
     */
    fun forceUpdateScroll() {
        ObjectCalls.ptrcallNoArgs(forceUpdateScrollBind, handle)
    }

    /**
     * Sets the camera's position immediately to its current smoothing destination. This method has no
     * effect if `position_smoothing_enabled` is `false`.
     *
     * Generated from Godot docs: Camera2D.reset_smoothing
     */
    fun resetSmoothing() {
        ObjectCalls.ptrcallNoArgs(resetSmoothingBind, handle)
    }

    /**
     * Aligns the camera to the tracked node. Note: Calling `force_update_scroll` after this method is
     * not required.
     *
     * Generated from Godot docs: Camera2D.align
     */
    fun align() {
        ObjectCalls.ptrcallNoArgs(alignBind, handle)
    }

    /**
     * If `true`, draws the camera's screen rectangle in the editor.
     *
     * Generated from Godot docs: Camera2D.set_screen_drawing_enabled
     */
    fun setScreenDrawingEnabled(screenDrawingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScreenDrawingEnabledBind, handle, screenDrawingEnabled)
    }

    /**
     * If `true`, draws the camera's screen rectangle in the editor.
     *
     * Generated from Godot docs: Camera2D.is_screen_drawing_enabled
     */
    fun isScreenDrawingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScreenDrawingEnabledBind, handle)
    }

    /**
     * If `true`, draws the camera's limits rectangle in the editor.
     *
     * Generated from Godot docs: Camera2D.set_limit_drawing_enabled
     */
    fun setLimitDrawingEnabled(limitDrawingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLimitDrawingEnabledBind, handle, limitDrawingEnabled)
    }

    /**
     * If `true`, draws the camera's limits rectangle in the editor.
     *
     * Generated from Godot docs: Camera2D.is_limit_drawing_enabled
     */
    fun isLimitDrawingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLimitDrawingEnabledBind, handle)
    }

    /**
     * If `true`, draws the camera's drag margin rectangle in the editor.
     *
     * Generated from Godot docs: Camera2D.set_margin_drawing_enabled
     */
    fun setMarginDrawingEnabled(marginDrawingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMarginDrawingEnabledBind, handle, marginDrawingEnabled)
    }

    /**
     * If `true`, draws the camera's drag margin rectangle in the editor.
     *
     * Generated from Godot docs: Camera2D.is_margin_drawing_enabled
     */
    fun isMarginDrawingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMarginDrawingEnabledBind, handle)
    }

    companion object {
        const val ANCHOR_MODE_FIXED_TOP_LEFT: Long = 0L
        const val ANCHOR_MODE_DRAG_CENTER: Long = 1L
        const val CAMERA2D_PROCESS_PHYSICS: Long = 0L
        const val CAMERA2D_PROCESS_IDLE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Camera2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Camera2D? =
            if (handle.address() == 0L) null else Camera2D(handle)

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_ANCHOR_MODE_HASH = 2050398218L
        private val setAnchorModeBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_anchor_mode", SET_ANCHOR_MODE_HASH)
        }

        private const val GET_ANCHOR_MODE_HASH = 155978067L
        private val getAnchorModeBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_anchor_mode", GET_ANCHOR_MODE_HASH)
        }

        private const val SET_IGNORE_ROTATION_HASH = 2586408642L
        private val setIgnoreRotationBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_ignore_rotation", SET_IGNORE_ROTATION_HASH)
        }

        private const val IS_IGNORING_ROTATION_HASH = 36873697L
        private val isIgnoringRotationBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_ignoring_rotation", IS_IGNORING_ROTATION_HASH)
        }

        private const val SET_PROCESS_CALLBACK_HASH = 4201947462L
        private val setProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_process_callback", SET_PROCESS_CALLBACK_HASH)
        }

        private const val GET_PROCESS_CALLBACK_HASH = 2325344499L
        private val getProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_process_callback", GET_PROCESS_CALLBACK_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val MAKE_CURRENT_HASH = 3218959716L
        private val makeCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "make_current", MAKE_CURRENT_HASH)
        }

        private const val IS_CURRENT_HASH = 36873697L
        private val isCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_current", IS_CURRENT_HASH)
        }

        private const val SET_LIMIT_ENABLED_HASH = 2586408642L
        private val setLimitEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_limit_enabled", SET_LIMIT_ENABLED_HASH)
        }

        private const val IS_LIMIT_ENABLED_HASH = 36873697L
        private val isLimitEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_limit_enabled", IS_LIMIT_ENABLED_HASH)
        }

        private const val SET_LIMIT_HASH = 437707142L
        private val setLimitBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_limit", SET_LIMIT_HASH)
        }

        private const val GET_LIMIT_HASH = 1983885014L
        private val getLimitBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_limit", GET_LIMIT_HASH)
        }

        private const val SET_LIMIT_SMOOTHING_ENABLED_HASH = 2586408642L
        private val setLimitSmoothingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_limit_smoothing_enabled", SET_LIMIT_SMOOTHING_ENABLED_HASH)
        }

        private const val IS_LIMIT_SMOOTHING_ENABLED_HASH = 36873697L
        private val isLimitSmoothingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_limit_smoothing_enabled", IS_LIMIT_SMOOTHING_ENABLED_HASH)
        }

        private const val SET_DRAG_VERTICAL_ENABLED_HASH = 2586408642L
        private val setDragVerticalEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_drag_vertical_enabled", SET_DRAG_VERTICAL_ENABLED_HASH)
        }

        private const val IS_DRAG_VERTICAL_ENABLED_HASH = 36873697L
        private val isDragVerticalEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_drag_vertical_enabled", IS_DRAG_VERTICAL_ENABLED_HASH)
        }

        private const val SET_DRAG_HORIZONTAL_ENABLED_HASH = 2586408642L
        private val setDragHorizontalEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_drag_horizontal_enabled", SET_DRAG_HORIZONTAL_ENABLED_HASH)
        }

        private const val IS_DRAG_HORIZONTAL_ENABLED_HASH = 36873697L
        private val isDragHorizontalEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_drag_horizontal_enabled", IS_DRAG_HORIZONTAL_ENABLED_HASH)
        }

        private const val SET_DRAG_VERTICAL_OFFSET_HASH = 373806689L
        private val setDragVerticalOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_drag_vertical_offset", SET_DRAG_VERTICAL_OFFSET_HASH)
        }

        private const val GET_DRAG_VERTICAL_OFFSET_HASH = 1740695150L
        private val getDragVerticalOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_drag_vertical_offset", GET_DRAG_VERTICAL_OFFSET_HASH)
        }

        private const val SET_DRAG_HORIZONTAL_OFFSET_HASH = 373806689L
        private val setDragHorizontalOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_drag_horizontal_offset", SET_DRAG_HORIZONTAL_OFFSET_HASH)
        }

        private const val GET_DRAG_HORIZONTAL_OFFSET_HASH = 1740695150L
        private val getDragHorizontalOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_drag_horizontal_offset", GET_DRAG_HORIZONTAL_OFFSET_HASH)
        }

        private const val SET_DRAG_MARGIN_HASH = 4290182280L
        private val setDragMarginBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_drag_margin", SET_DRAG_MARGIN_HASH)
        }

        private const val GET_DRAG_MARGIN_HASH = 2869120046L
        private val getDragMarginBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_drag_margin", GET_DRAG_MARGIN_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3341600327L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val GET_SCREEN_CENTER_POSITION_HASH = 3341600327L
        private val getScreenCenterPositionBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_screen_center_position", GET_SCREEN_CENTER_POSITION_HASH)
        }

        private const val GET_SCREEN_ROTATION_HASH = 1740695150L
        private val getScreenRotationBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_screen_rotation", GET_SCREEN_ROTATION_HASH)
        }

        private const val SET_ZOOM_HASH = 743155724L
        private val setZoomBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_zoom", SET_ZOOM_HASH)
        }

        private const val GET_ZOOM_HASH = 3341600327L
        private val getZoomBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_zoom", GET_ZOOM_HASH)
        }

        private const val SET_CUSTOM_VIEWPORT_HASH = 1078189570L
        private val setCustomViewportBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_custom_viewport", SET_CUSTOM_VIEWPORT_HASH)
        }

        private const val GET_CUSTOM_VIEWPORT_HASH = 3160264692L
        private val getCustomViewportBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_custom_viewport", GET_CUSTOM_VIEWPORT_HASH)
        }

        private const val SET_POSITION_SMOOTHING_SPEED_HASH = 373806689L
        private val setPositionSmoothingSpeedBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_position_smoothing_speed", SET_POSITION_SMOOTHING_SPEED_HASH)
        }

        private const val GET_POSITION_SMOOTHING_SPEED_HASH = 1740695150L
        private val getPositionSmoothingSpeedBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_position_smoothing_speed", GET_POSITION_SMOOTHING_SPEED_HASH)
        }

        private const val SET_POSITION_SMOOTHING_ENABLED_HASH = 2586408642L
        private val setPositionSmoothingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_position_smoothing_enabled", SET_POSITION_SMOOTHING_ENABLED_HASH)
        }

        private const val IS_POSITION_SMOOTHING_ENABLED_HASH = 36873697L
        private val isPositionSmoothingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_position_smoothing_enabled", IS_POSITION_SMOOTHING_ENABLED_HASH)
        }

        private const val SET_ROTATION_SMOOTHING_ENABLED_HASH = 2586408642L
        private val setRotationSmoothingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_rotation_smoothing_enabled", SET_ROTATION_SMOOTHING_ENABLED_HASH)
        }

        private const val IS_ROTATION_SMOOTHING_ENABLED_HASH = 36873697L
        private val isRotationSmoothingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_rotation_smoothing_enabled", IS_ROTATION_SMOOTHING_ENABLED_HASH)
        }

        private const val SET_ROTATION_SMOOTHING_SPEED_HASH = 373806689L
        private val setRotationSmoothingSpeedBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_rotation_smoothing_speed", SET_ROTATION_SMOOTHING_SPEED_HASH)
        }

        private const val GET_ROTATION_SMOOTHING_SPEED_HASH = 1740695150L
        private val getRotationSmoothingSpeedBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "get_rotation_smoothing_speed", GET_ROTATION_SMOOTHING_SPEED_HASH)
        }

        private const val FORCE_UPDATE_SCROLL_HASH = 3218959716L
        private val forceUpdateScrollBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "force_update_scroll", FORCE_UPDATE_SCROLL_HASH)
        }

        private const val RESET_SMOOTHING_HASH = 3218959716L
        private val resetSmoothingBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "reset_smoothing", RESET_SMOOTHING_HASH)
        }

        private const val ALIGN_HASH = 3218959716L
        private val alignBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "align", ALIGN_HASH)
        }

        private const val SET_SCREEN_DRAWING_ENABLED_HASH = 2586408642L
        private val setScreenDrawingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_screen_drawing_enabled", SET_SCREEN_DRAWING_ENABLED_HASH)
        }

        private const val IS_SCREEN_DRAWING_ENABLED_HASH = 36873697L
        private val isScreenDrawingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_screen_drawing_enabled", IS_SCREEN_DRAWING_ENABLED_HASH)
        }

        private const val SET_LIMIT_DRAWING_ENABLED_HASH = 2586408642L
        private val setLimitDrawingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_limit_drawing_enabled", SET_LIMIT_DRAWING_ENABLED_HASH)
        }

        private const val IS_LIMIT_DRAWING_ENABLED_HASH = 36873697L
        private val isLimitDrawingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_limit_drawing_enabled", IS_LIMIT_DRAWING_ENABLED_HASH)
        }

        private const val SET_MARGIN_DRAWING_ENABLED_HASH = 2586408642L
        private val setMarginDrawingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "set_margin_drawing_enabled", SET_MARGIN_DRAWING_ENABLED_HASH)
        }

        private const val IS_MARGIN_DRAWING_ENABLED_HASH = 36873697L
        private val isMarginDrawingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Camera2D", "is_margin_drawing_enabled", IS_MARGIN_DRAWING_ENABLED_HASH)
        }
    }
}

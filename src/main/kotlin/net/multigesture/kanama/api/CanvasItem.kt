package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for everything in 2D space.
 *
 * Generated from Godot docs: CanvasItem
 */
open class CanvasItem(handle: MemorySegment) : Node(handle) {
    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var selfModulate: Color
        @JvmName("selfModulateProperty")
        get() = getSelfModulate()
        @JvmName("setSelfModulateProperty")
        set(value) = setSelfModulate(value)

    var showBehindParent: Boolean
        @JvmName("showBehindParentProperty")
        get() = isDrawBehindParentEnabled()
        @JvmName("setShowBehindParentProperty")
        set(value) = setDrawBehindParent(value)

    var topLevel: Boolean
        @JvmName("topLevelProperty")
        get() = isSetAsTopLevel()
        @JvmName("setTopLevelProperty")
        set(value) = setAsTopLevel(value)

    var clipChildren: Long
        @JvmName("clipChildrenProperty")
        get() = getClipChildrenMode()
        @JvmName("setClipChildrenProperty")
        set(value) = setClipChildrenMode(value)

    var oversamplingWithScale: Long
        @JvmName("oversamplingWithScaleProperty")
        get() = getOversamplingWithScale()
        @JvmName("setOversamplingWithScaleProperty")
        set(value) = setOversamplingWithScale(value)

    var lightMask: Int
        @JvmName("lightMaskProperty")
        get() = getLightMask()
        @JvmName("setLightMaskProperty")
        set(value) = setLightMask(value)

    var visibilityLayer: Long
        @JvmName("visibilityLayerProperty")
        get() = getVisibilityLayer()
        @JvmName("setVisibilityLayerProperty")
        set(value) = setVisibilityLayer(value)

    var zIndex: Int
        @JvmName("zIndexProperty")
        get() = getZIndex()
        @JvmName("setZIndexProperty")
        set(value) = setZIndex(value)

    var zAsRelative: Boolean
        @JvmName("zAsRelativeProperty")
        get() = isZRelative()
        @JvmName("setZAsRelativeProperty")
        set(value) = setZAsRelative(value)

    var ySortEnabled: Boolean
        @JvmName("ySortEnabledProperty")
        get() = isYSortEnabled()
        @JvmName("setYSortEnabledProperty")
        set(value) = setYSortEnabled(value)

    var textureFilter: Long
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: Long
        @JvmName("textureRepeatProperty")
        get() = getTextureRepeat()
        @JvmName("setTextureRepeatProperty")
        set(value) = setTextureRepeat(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    var useParentMaterial: Boolean
        @JvmName("useParentMaterialProperty")
        get() = getUseParentMaterial()
        @JvmName("setUseParentMaterialProperty")
        set(value) = setUseParentMaterial(value)

    /**
     * Returns the internal canvas item `RID` used by the `RenderingServer` for this node.
     *
     * Generated from Godot docs: CanvasItem.get_canvas_item
     */
    fun getCanvasItem(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCanvasItemBind, handle)
    }

    /**
     * If `true`, this `CanvasItem` may be drawn. Whether this `CanvasItem` is actually drawn depends
     * on the visibility of all of its `CanvasItem` ancestors. In other words: this `CanvasItem` will
     * be drawn when `is_visible_in_tree` returns `true` and all `CanvasItem` ancestors share at least
     * one `visibility_layer` with this `CanvasItem`. Note: For controls that inherit `Popup`, the
     * correct way to make them visible is to call one of the multiple `popup*()` functions instead.
     *
     * Generated from Godot docs: CanvasItem.set_visible
     */
    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    /**
     * If `true`, this `CanvasItem` may be drawn. Whether this `CanvasItem` is actually drawn depends
     * on the visibility of all of its `CanvasItem` ancestors. In other words: this `CanvasItem` will
     * be drawn when `is_visible_in_tree` returns `true` and all `CanvasItem` ancestors share at least
     * one `visibility_layer` with this `CanvasItem`. Note: For controls that inherit `Popup`, the
     * correct way to make them visible is to call one of the multiple `popup*()` functions instead.
     *
     * Generated from Godot docs: CanvasItem.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    /**
     * Returns `true` if the node is present in the `SceneTree`, its `visible` property is `true` and
     * all its ancestors are also visible. If any ancestor is hidden, this node will not be visible in
     * the scene tree, and is therefore not drawn (see `_draw`). Visibility is checked only in parent
     * nodes that inherit from `CanvasItem`, `CanvasLayer`, and `Window`. If the parent is of any other
     * type (such as `Node`, `AnimationPlayer`, or `Node3D`), it is assumed to be visible. Note: This
     * method does not take `visibility_layer` into account, so even if this method returns `true`, the
     * node might end up not being rendered.
     *
     * Generated from Godot docs: CanvasItem.is_visible_in_tree
     */
    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleInTreeBind, handle)
    }

    /**
     * Show the `CanvasItem` if it's currently hidden. This is equivalent to setting `visible` to
     * `true`. Note: For controls that inherit `Popup`, the correct way to make them visible is to call
     * one of the multiple `popup*()` functions instead.
     *
     * Generated from Godot docs: CanvasItem.show
     */
    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, handle)
    }

    /**
     * Hide the `CanvasItem` if it's currently visible. This is equivalent to setting `visible` to
     * `false`.
     *
     * Generated from Godot docs: CanvasItem.hide
     */
    fun hide() {
        ObjectCalls.ptrcallNoArgs(hideBind, handle)
    }

    /**
     * Queues the `CanvasItem` to redraw. During idle time, if `CanvasItem` is visible,
     * `NOTIFICATION_DRAW` is sent and `_draw` is called. This only occurs once per frame, even if this
     * method has been called multiple times.
     *
     * Generated from Godot docs: CanvasItem.queue_redraw
     */
    fun queueRedraw() {
        ObjectCalls.ptrcallNoArgs(queueRedrawBind, handle)
    }

    /**
     * Moves this node below its siblings, usually causing the node to draw on top of its siblings.
     * Does nothing if this node does not have a parent. See also `Node.move_child`.
     *
     * Generated from Godot docs: CanvasItem.move_to_front
     */
    fun moveToFront() {
        ObjectCalls.ptrcallNoArgs(moveToFrontBind, handle)
    }

    /**
     * If `true`, this `CanvasItem` will not inherit its transform from parent `CanvasItem`s. Its draw
     * order will also be changed to make it draw on top of other `CanvasItem`s that do not have
     * `top_level` set to `true`. The `CanvasItem` will effectively act as if it was placed as a child
     * of a bare `Node`.
     *
     * Generated from Godot docs: CanvasItem.set_as_top_level
     */
    fun setAsTopLevel(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsTopLevelBind, handle, enable)
    }

    /**
     * If `true`, this `CanvasItem` will not inherit its transform from parent `CanvasItem`s. Its draw
     * order will also be changed to make it draw on top of other `CanvasItem`s that do not have
     * `top_level` set to `true`. The `CanvasItem` will effectively act as if it was placed as a child
     * of a bare `Node`.
     *
     * Generated from Godot docs: CanvasItem.is_set_as_top_level
     */
    fun isSetAsTopLevel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSetAsTopLevelBind, handle)
    }

    /**
     * The rendering layers in which this `CanvasItem` responds to `Light2D` nodes.
     *
     * Generated from Godot docs: CanvasItem.set_light_mask
     */
    fun setLightMask(lightMask: Int) {
        ObjectCalls.ptrcallWithIntArg(setLightMaskBind, handle, lightMask)
    }

    /**
     * The rendering layers in which this `CanvasItem` responds to `Light2D` nodes.
     *
     * Generated from Godot docs: CanvasItem.get_light_mask
     */
    fun getLightMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLightMaskBind, handle)
    }

    /**
     * The color applied to this `CanvasItem`. This property does affect child `CanvasItem`s, unlike
     * `self_modulate` which only affects the node itself.
     *
     * Generated from Godot docs: CanvasItem.set_modulate
     */
    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, modulate)
    }

    /**
     * The color applied to this `CanvasItem`. This property does affect child `CanvasItem`s, unlike
     * `self_modulate` which only affects the node itself.
     *
     * Generated from Godot docs: CanvasItem.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    /**
     * The color applied to this `CanvasItem`. This property does not affect child `CanvasItem`s,
     * unlike `modulate` which affects both the node itself and its children. Note: Internal children
     * are also not affected by this property (see the `include_internal` parameter in
     * `Node.add_child`). For built-in nodes this includes sliders in `ColorPicker`, and the tab bar in
     * `TabContainer`.
     *
     * Generated from Godot docs: CanvasItem.set_self_modulate
     */
    fun setSelfModulate(selfModulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setSelfModulateBind, handle, selfModulate)
    }

    /**
     * The color applied to this `CanvasItem`. This property does not affect child `CanvasItem`s,
     * unlike `modulate` which affects both the node itself and its children. Note: Internal children
     * are also not affected by this property (see the `include_internal` parameter in
     * `Node.add_child`). For built-in nodes this includes sliders in `ColorPicker`, and the tab bar in
     * `TabContainer`.
     *
     * Generated from Godot docs: CanvasItem.get_self_modulate
     */
    fun getSelfModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getSelfModulateBind, handle)
    }

    /**
     * The order in which this node is drawn. A node with a higher Z index will display in front of
     * others. Must be between `RenderingServer.CANVAS_ITEM_Z_MIN` and
     * `RenderingServer.CANVAS_ITEM_Z_MAX` (inclusive). Note: The Z index does not affect the order in
     * which `CanvasItem` nodes are processed or the way input events are handled. This is especially
     * important to keep in mind for `Control` nodes.
     *
     * Generated from Godot docs: CanvasItem.set_z_index
     */
    fun setZIndex(zIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setZIndexBind, handle, zIndex)
    }

    /**
     * The order in which this node is drawn. A node with a higher Z index will display in front of
     * others. Must be between `RenderingServer.CANVAS_ITEM_Z_MIN` and
     * `RenderingServer.CANVAS_ITEM_Z_MAX` (inclusive). Note: The Z index does not affect the order in
     * which `CanvasItem` nodes are processed or the way input events are handled. This is especially
     * important to keep in mind for `Control` nodes.
     *
     * Generated from Godot docs: CanvasItem.get_z_index
     */
    fun getZIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getZIndexBind, handle)
    }

    /**
     * If `true`, this node's final Z index is relative to its parent's Z index. For example, if
     * `z_index` is `2` and its parent's final Z index is `3`, then this node's final Z index will be
     * `5` (`2 + 3`).
     *
     * Generated from Godot docs: CanvasItem.set_z_as_relative
     */
    fun setZAsRelative(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setZAsRelativeBind, handle, enable)
    }

    /**
     * If `true`, this node's final Z index is relative to its parent's Z index. For example, if
     * `z_index` is `2` and its parent's final Z index is `3`, then this node's final Z index will be
     * `5` (`2 + 3`).
     *
     * Generated from Godot docs: CanvasItem.is_z_relative
     */
    fun isZRelative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isZRelativeBind, handle)
    }

    /**
     * If `true`, this and child `CanvasItem` nodes with a higher Y position are rendered in front of
     * nodes with a lower Y position. If `false`, this and child `CanvasItem` nodes are rendered
     * normally in scene tree order. With Y-sorting enabled on a parent node ('A') but disabled on a
     * child node ('B'), the child node ('B') is sorted but its children ('C1', 'C2', etc.) render
     * together on the same Y position as the child node ('B'). This allows you to organize the render
     * order of a scene without changing the scene tree. Nodes sort relative to each other only if they
     * are on the same `z_index`.
     *
     * Generated from Godot docs: CanvasItem.set_y_sort_enabled
     */
    fun setYSortEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setYSortEnabledBind, handle, enabled)
    }

    /**
     * If `true`, this and child `CanvasItem` nodes with a higher Y position are rendered in front of
     * nodes with a lower Y position. If `false`, this and child `CanvasItem` nodes are rendered
     * normally in scene tree order. With Y-sorting enabled on a parent node ('A') but disabled on a
     * child node ('B'), the child node ('B') is sorted but its children ('C1', 'C2', etc.) render
     * together on the same Y position as the child node ('B'). This allows you to organize the render
     * order of a scene without changing the scene tree. Nodes sort relative to each other only if they
     * are on the same `z_index`.
     *
     * Generated from Godot docs: CanvasItem.is_y_sort_enabled
     */
    fun isYSortEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isYSortEnabledBind, handle)
    }

    /**
     * If `true`, this node draws behind its parent.
     *
     * Generated from Godot docs: CanvasItem.set_draw_behind_parent
     */
    fun setDrawBehindParent(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBehindParentBind, handle, enable)
    }

    /**
     * If `true`, this node draws behind its parent.
     *
     * Generated from Godot docs: CanvasItem.is_draw_behind_parent_enabled
     */
    fun isDrawBehindParentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawBehindParentEnabledBind, handle)
    }

    /**
     * Draws a line from a 2D point to another, with a given color and width. It can be optionally
     * antialiased. The `from` and `to` positions are defined in local space. See also
     * `draw_dashed_line`, `draw_multiline`, and `draw_polyline`. If `width` is negative, then a
     * two-point primitive will be drawn instead of a four-point one. This means that when the
     * CanvasItem is scaled, the line will remain thin. If this behavior is not desired, then pass a
     * positive `width` like `1.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_line
     */
    fun drawLine(from: Vector2, to: Vector2, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithTwoVector2ColorDoubleBoolArgs(drawLineBind, handle, from, to, color, width, antialiased)
    }

    /**
     * Draws a dashed line from a 2D point to another, with a given color and width. The `from` and
     * `to` positions are defined in local space. See also `draw_line`, `draw_multiline`, and
     * `draw_polyline`. If `width` is negative, then a two-point primitives will be drawn instead of a
     * four-point ones. This means that when the CanvasItem is scaled, the line parts will remain thin.
     * If this behavior is not desired, then pass a positive `width` like `1.0`. `dash` is the length
     * of each dash in pixels, with the gap between each dash being the same length. If `aligned` is
     * `true`, the length of the first and last dashes may be shortened or lengthened to allow the line
     * to begin and end at the precise points defined by `from` and `to`. Both ends are always
     * symmetrical when `aligned` is `true`. If `aligned` is `false`, all dashes will have the same
     * length, but the line may appear incomplete at the end due to the dash length not dividing evenly
     * into the line length. Only full dashes are drawn when `aligned` is `false`. If `antialiased` is
     * `true`, half transparent "feathers" will be attached to the boundary, making outlines smooth.
     * Note: `antialiased` is only effective if `width` is greater than `0.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_dashed_line
     */
    fun drawDashedLine(from: Vector2, to: Vector2, color: Color, width: Double = -1.0, dash: Double = 2.0, aligned: Boolean = true, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithTwoVector2ColorTwoDoubleTwoBoolArgs(drawDashedLineBind, handle, from, to, color, width, dash, aligned, antialiased)
    }

    /**
     * Draws interconnected line segments with a uniform `color` and `width` and optional antialiasing
     * (supported only for positive `width`). The `points` array is defined in local space. When
     * drawing large amounts of lines, this is faster than using individual `draw_line` calls. To draw
     * disconnected lines, use `draw_multiline` instead. See also `draw_polygon`. If `width` is
     * negative, it will be ignored and the polyline will be drawn using
     * `RenderingServer.PRIMITIVE_LINE_STRIP`. This means that when the CanvasItem is scaled, the
     * polyline will remain thin. If this behavior is not desired, then pass a positive `width` like
     * `1.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_polyline
     */
    fun drawPolyline(points: List<Vector2>, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListColorDoubleAndBoolArgs(drawPolylineBind, handle, points, color, width, antialiased)
    }

    /**
     * Draws interconnected line segments with a uniform `width`, point-by-point coloring, and optional
     * antialiasing (supported only for positive `width`). Colors assigned to line points match by
     * index between `points` and `colors`, i.e. each line segment is filled with a gradient between
     * the colors of the endpoints. The `points` array is defined in local space. When drawing large
     * amounts of lines, this is faster than using individual `draw_line` calls. To draw disconnected
     * lines, use `draw_multiline_colors` instead. See also `draw_polygon`. If `width` is negative, it
     * will be ignored and the polyline will be drawn using `RenderingServer.PRIMITIVE_LINE_STRIP`.
     * This means that when the CanvasItem is scaled, the polyline will remain thin. If this behavior
     * is not desired, then pass a positive `width` like `1.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_polyline_colors
     */
    fun drawPolylineColors(points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs(drawPolylineColorsBind, handle, points, colors, width, antialiased)
    }

    /**
     * Draws an unfilled elliptical arc between the given angles with a uniform `color` and `width` and
     * optional antialiasing (supported only for positive `width`). The larger the value of
     * `point_count`, the smoother the curve. For circular arcs, see `draw_arc`. See also
     * `draw_ellipse`. If `width` is negative, it will be ignored and the arc will be drawn using
     * `RenderingServer.PRIMITIVE_LINE_STRIP`. This means that when the CanvasItem is scaled, the arc
     * will remain thin. If this behavior is not desired, then pass a positive `width` like `1.0`. The
     * arc is drawn from `start_angle` towards the value of `end_angle` so in clockwise direction if
     * `start_angle < end_angle` and counter-clockwise otherwise. Passing the same angles but in
     * reversed order will produce the same arc. If absolute difference of `start_angle` and
     * `end_angle` is greater than `@GDScript.TAU` radians, then a full ellipse is drawn (i.e. arc will
     * not overlap itself).
     *
     * Generated from Godot docs: CanvasItem.draw_ellipse_arc
     */
    fun drawEllipseArc(center: Vector2, major: Double, minor: Double, startAngle: Double, endAngle: Double, pointCount: Int, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2FourDoubleIntColorDoubleBoolArgs(drawEllipseArcBind, handle, center, major, minor, startAngle, endAngle, pointCount, color, width, antialiased)
    }

    /**
     * Draws an unfilled arc between the given angles with a uniform `color` and `width` and optional
     * antialiasing (supported only for positive `width`). The larger the value of `point_count`, the
     * smoother the curve. `center` is defined in local space. For elliptical arcs, see
     * `draw_ellipse_arc`. See also `draw_circle`. If `width` is negative, it will be ignored and the
     * arc will be drawn using `RenderingServer.PRIMITIVE_LINE_STRIP`. This means that when the
     * CanvasItem is scaled, the arc will remain thin. If this behavior is not desired, then pass a
     * positive `width` like `1.0`. The arc is drawn from `start_angle` towards the value of
     * `end_angle` so in clockwise direction if `start_angle < end_angle` and counter-clockwise
     * otherwise. Passing the same angles but in reversed order will produce the same arc. If absolute
     * difference of `start_angle` and `end_angle` is greater than `@GDScript.TAU` radians, then a full
     * circle arc is drawn (i.e. arc will not overlap itself).
     *
     * Generated from Godot docs: CanvasItem.draw_arc
     */
    fun drawArc(center: Vector2, radius: Double, startAngle: Double, endAngle: Double, pointCount: Int, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2ThreeDoubleIntColorDoubleBoolArgs(drawArcBind, handle, center, radius, startAngle, endAngle, pointCount, color, width, antialiased)
    }

    /**
     * Draws multiple disconnected lines with a uniform `width` and `color`. Each line is defined by
     * two consecutive points from `points` array in local space, i.e. i-th segment consists of
     * `points[2 * i]`, `points[2 * i + 1]` endpoints. When drawing large amounts of lines, this is
     * faster than using individual `draw_line` calls. To draw interconnected lines, use
     * `draw_polyline` instead. If `width` is negative, then two-point primitives will be drawn instead
     * of a four-point ones. This means that when the CanvasItem is scaled, the lines will remain thin.
     * If this behavior is not desired, then pass a positive `width` like `1.0`. Note: `antialiased` is
     * only effective if `width` is greater than `0.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_multiline
     */
    fun drawMultiline(points: List<Vector2>, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListColorDoubleAndBoolArgs(drawMultilineBind, handle, points, color, width, antialiased)
    }

    /**
     * Draws multiple disconnected lines with a uniform `width` and segment-by-segment coloring. Each
     * segment is defined by two consecutive points from `points` array in local space and a
     * corresponding color from `colors` array, i.e. i-th segment consists of `points[2 * i]`,
     * `points[2 * i + 1]` endpoints and has `colors` color. When drawing large amounts of lines, this
     * is faster than using individual `draw_line` calls. To draw interconnected lines, use
     * `draw_polyline_colors` instead. If `width` is negative, then two-point primitives will be drawn
     * instead of a four-point ones. This means that when the CanvasItem is scaled, the lines will
     * remain thin. If this behavior is not desired, then pass a positive `width` like `1.0`. Note:
     * `antialiased` is only effective if `width` is greater than `0.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_multiline_colors
     */
    fun drawMultilineColors(points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs(drawMultilineColorsBind, handle, points, colors, width, antialiased)
    }

    /**
     * Draws a rectangle. If `filled` is `true`, the rectangle will be filled with the `color`
     * specified. If `filled` is `false`, the rectangle will be drawn as a stroke with the `color` and
     * `width` specified. The `rect` is specified in local space. See also `draw_texture_rect`. If
     * `width` is negative, then two-point primitives will be drawn instead of a four-point ones. This
     * means that when the CanvasItem is scaled, the lines will remain thin. If this behavior is not
     * desired, then pass a positive `width` like `1.0`. If `antialiased` is `true`, half transparent
     * "feathers" will be attached to the boundary, making outlines smooth. Note: `width` is only
     * effective if `filled` is `false`. Note: Unfilled rectangles drawn with a negative `width` may
     * not display perfectly. For example, corners may be missing or brighter due to overlapping lines
     * (for a translucent `color`).
     *
     * Generated from Godot docs: CanvasItem.draw_rect
     */
    fun drawRect(rect: Rect2, color: Color, filled: Boolean = true, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRect2ColorBoolDoubleBoolArgs(drawRectBind, handle, rect, color, filled, width, antialiased)
    }

    /**
     * Draws a circle, with `position` defined in local space. See also `draw_ellipse`, `draw_arc`,
     * `draw_polyline`, and `draw_polygon`. If `filled` is `true`, the circle will be filled with the
     * `color` specified. If `filled` is `false`, the circle will be drawn as a stroke with the `color`
     * and `width` specified. If `width` is negative, then two-point primitives will be drawn instead
     * of a four-point ones. This means that when the CanvasItem is scaled, the lines will remain thin.
     * If this behavior is not desired, then pass a positive `width` like `1.0`. If `antialiased` is
     * `true`, half transparent "feathers" will be attached to the boundary, making outlines smooth.
     * Note: `width` is only effective if `filled` is `false`.
     *
     * Generated from Godot docs: CanvasItem.draw_circle
     */
    fun drawCircle(position: Vector2, radius: Double, color: Color, filled: Boolean = true, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2DoubleColorBoolDoubleBoolArgs(drawCircleBind, handle, position, radius, color, filled, width, antialiased)
    }

    /**
     * Draws an ellipse with semi-major axis `major` and semi-minor axis `minor`. See also
     * `draw_circle`, `draw_ellipse_arc`, `draw_polyline`, and `draw_polygon`. If `filled` is `true`,
     * the ellipse will be filled with the `color` specified. If `filled` is `false`, the ellipse will
     * be drawn as a stroke with the `color` and `width` specified. If `width` is negative, then
     * two-point primitives will be drawn instead of four-point ones. This means that when the
     * CanvasItem is scaled, the lines will remain thin. If this behavior is not desired, then pass a
     * positive `width` like `1.0`. If `antialiased` is `true`, half transparent "feathers" will be
     * attached to the boundary, making outlines smooth. Note: `width` is only effective if `filled` is
     * `false`.
     *
     * Generated from Godot docs: CanvasItem.draw_ellipse
     */
    fun drawEllipse(position: Vector2, major: Double, minor: Double, color: Color, filled: Boolean = true, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2TwoDoubleColorBoolDoubleBoolArgs(drawEllipseBind, handle, position, major, minor, color, filled, width, antialiased)
    }

    /**
     * Draws a texture at a given position. The `position` is defined in local space. Note: Styleboxes,
     * textures, and meshes stored only inside local variables should not be used with this method in
     * GDScript, because the drawing operation doesn't begin immediately once this method is called. In
     * GDScript, when the function with the local variables ends, the local variables get destroyed
     * before the rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_texture
     */
    fun drawTexture(texture: Texture2D?, position: Vector2, modulate: Color) {
        ObjectCalls.ptrcallWithObjectVector2AndColorArgs(drawTextureBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, position, modulate)
    }

    /**
     * Draws a textured rectangle at a given position, optionally modulated by a color. The `rect` is
     * defined in local space. If `transpose` is `true`, the texture will have its X and Y coordinates
     * swapped. See also `draw_rect` and `draw_texture_rect_region`. Note: Styleboxes, textures, and
     * meshes stored only inside local variables should not be used with this method in GDScript,
     * because the drawing operation doesn't begin immediately once this method is called. In GDScript,
     * when the function with the local variables ends, the local variables get destroyed before the
     * rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_texture_rect
     */
    fun drawTextureRect(texture: Texture2D?, rect: Rect2, tile: Boolean, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithObjectRect2BoolColorBoolArgs(drawTextureRectBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, tile, modulate, transpose)
    }

    /**
     * Draws a textured rectangle from a texture's region (specified by `src_rect`) at a given position
     * in local space, optionally modulated by a color. If `transpose` is `true`, the texture will have
     * its X and Y coordinates swapped. See also `draw_texture_rect`. Note: Styleboxes, textures, and
     * meshes stored only inside local variables should not be used with this method in GDScript,
     * because the drawing operation doesn't begin immediately once this method is called. In GDScript,
     * when the function with the local variables ends, the local variables get destroyed before the
     * rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_texture_rect_region
     */
    fun drawTextureRectRegion(texture: Texture2D?, rect: Rect2, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithObjectTwoRect2ColorTwoBoolArgs(drawTextureRectRegionBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, srcRect, modulate, transpose, clipUv)
    }

    /**
     * Draws a textured rectangle region of the multichannel signed distance field texture at a given
     * position, optionally modulated by a color. The `rect` is defined in local space. See
     * `FontFile.multichannel_signed_distance_field` for more information and caveats about MSDF font
     * rendering. If `outline` is positive, each alpha channel value of pixel in region is set to
     * maximum value of true distance in the `outline` radius. Value of the `pixel_range` should the
     * same that was used during distance field texture generation. Note: Styleboxes, textures, and
     * meshes stored only inside local variables should not be used with this method in GDScript,
     * because the drawing operation doesn't begin immediately once this method is called. In GDScript,
     * when the function with the local variables ends, the local variables get destroyed before the
     * rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_msdf_texture_rect_region
     */
    fun drawMsdfTextureRectRegion(texture: Texture2D?, rect: Rect2, srcRect: Rect2, modulate: Color, outline: Double = 0.0, pixelRange: Double = 4.0, scale: Double = 1.0) {
        ObjectCalls.ptrcallWithObjectTwoRect2ColorThreeDoubleArgs(drawMsdfTextureRectRegionBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, srcRect, modulate, outline, pixelRange, scale)
    }

    /**
     * Draws a textured rectangle region of the font texture with LCD subpixel anti-aliasing at a given
     * position, optionally modulated by a color. The `rect` is defined in local space. Texture is
     * drawn using the following blend operation, blend mode of the `CanvasItemMaterial` is ignored:
     *
     * Generated from Godot docs: CanvasItem.draw_lcd_texture_rect_region
     */
    fun drawLcdTextureRectRegion(texture: Texture2D?, rect: Rect2, srcRect: Rect2, modulate: Color) {
        ObjectCalls.ptrcallWithObjectTwoRect2AndColorArgs(drawLcdTextureRectRegionBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, srcRect, modulate)
    }

    /**
     * Draws a styled rectangle. The `rect` is defined in local space. Note: Styleboxes, textures, and
     * meshes stored only inside local variables should not be used with this method in GDScript,
     * because the drawing operation doesn't begin immediately once this method is called. In GDScript,
     * when the function with the local variables ends, the local variables get destroyed before the
     * rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_style_box
     */
    fun drawStyleBox(styleBox: StyleBox?, rect: Rect2) {
        ObjectCalls.ptrcallWithObjectAndRect2Arg(drawStyleBoxBind, handle, styleBox?.requireOpenHandle() ?: MemorySegment.NULL, rect)
    }

    /**
     * Draws a custom primitive. 1 point for a point, 2 points for a line, 3 points for a triangle, and
     * 4 points for a quad. If 0 points or more than 4 points are specified, nothing will be drawn and
     * an error message will be printed. The `points` array is defined in local space. See also
     * `draw_line`, `draw_polyline`, `draw_polygon`, and `draw_rect`. Note: Styleboxes, textures, and
     * meshes stored only inside local variables should not be used with this method in GDScript,
     * because the drawing operation doesn't begin immediately once this method is called. In GDScript,
     * when the function with the local variables ends, the local variables get destroyed before the
     * rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_primitive
     */
    fun drawPrimitive(points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: Texture2D?) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs(drawPrimitiveBind, handle, points, colors, uvs, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Draws a solid polygon of any number of points, convex or concave. Unlike `draw_colored_polygon`,
     * each point's color can be changed individually. The `points` array is defined in local space.
     * See also `draw_polyline` and `draw_polyline_colors`. If you need more flexibility (such as being
     * able to use bones), use `RenderingServer.canvas_item_add_triangle_array` instead. Note: If you
     * frequently redraw the same polygon with a large number of vertices, consider pre-calculating the
     * triangulation with `Geometry2D.triangulate_polygon` and using `draw_mesh`, `draw_multimesh`, or
     * `RenderingServer.canvas_item_add_triangle_array`. Note: Styleboxes, textures, and meshes stored
     * only inside local variables should not be used with this method in GDScript, because the drawing
     * operation doesn't begin immediately once this method is called. In GDScript, when the function
     * with the local variables ends, the local variables get destroyed before the rendering takes
     * place.
     *
     * Generated from Godot docs: CanvasItem.draw_polygon
     */
    fun drawPolygon(points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: Texture2D?) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs(drawPolygonBind, handle, points, colors, uvs, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Draws a colored polygon of any number of points, convex or concave. The points in the `points`
     * array are defined in local space. Unlike `draw_polygon`, a single color must be specified for
     * the whole polygon. Note: If you frequently redraw the same polygon with a large number of
     * vertices, consider pre-calculating the triangulation with `Geometry2D.triangulate_polygon` and
     * using `draw_mesh`, `draw_multimesh`, or `RenderingServer.canvas_item_add_triangle_array`. Note:
     * Styleboxes, textures, and meshes stored only inside local variables should not be used with this
     * method in GDScript, because the drawing operation doesn't begin immediately once this method is
     * called. In GDScript, when the function with the local variables ends, the local variables get
     * destroyed before the rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_colored_polygon
     */
    fun drawColoredPolygon(points: List<Vector2>, color: Color, uvs: List<Vector2>, texture: Texture2D?) {
        ObjectCalls.ptrcallWithPackedVector2ListColorPackedVector2ListAndObjectArgs(drawColoredPolygonBind, handle, points, color, uvs, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Draws `text` using the specified `font` at the `pos` in local space (bottom-left corner using
     * the baseline of the font). The text will have its color multiplied by `modulate`. If `width` is
     * greater than or equal to 0, the text will be clipped if it exceeds the specified width. If
     * `oversampling` is greater than zero, it is used as font oversampling factor, otherwise viewport
     * oversampling settings are used.
     *
     * Generated from Godot docs: CanvasItem.draw_string
     */
    fun drawString(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleIntColorThreeLongDoubleArgs(drawStringBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, modulate, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Breaks `text` into lines and draws it using the specified `font` at the `pos` in local space
     * (top-left corner). The text will have its color multiplied by `modulate`. If `width` is greater
     * than or equal to 0, the text will be clipped if it exceeds the specified width. If
     * `oversampling` is greater than zero, it is used as font oversampling factor, otherwise viewport
     * oversampling settings are used.
     *
     * Generated from Godot docs: CanvasItem.draw_multiline_string
     */
    fun drawMultilineString(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(drawMultilineStringBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, maxLines, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Draws `text` outline using the specified `font` at the `pos` in local space (bottom-left corner
     * using the baseline of the font). The text will have its color multiplied by `modulate`. If
     * `width` is greater than or equal to 0, the text will be clipped if it exceeds the specified
     * width. If `oversampling` is greater than zero, it is used as font oversampling factor, otherwise
     * viewport oversampling settings are used.
     *
     * Generated from Godot docs: CanvasItem.draw_string_outline
     */
    fun drawStringOutline(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, size: Int = 1, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(drawStringOutlineBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, size, modulate, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Breaks `text` to the lines and draws text outline using the specified `font` at the `pos` in
     * local space (top-left corner). The text will have its color multiplied by `modulate`. If `width`
     * is greater than or equal to 0, the text will be clipped if it exceeds the specified width. If
     * `oversampling` is greater than zero, it is used as font oversampling factor, otherwise viewport
     * oversampling settings are used.
     *
     * Generated from Godot docs: CanvasItem.draw_multiline_string_outline
     */
    fun drawMultilineStringOutline(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, size: Int = 1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(drawMultilineStringOutlineBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, maxLines, size, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Draws a string first character using a custom font. If `oversampling` is greater than zero, it
     * is used as font oversampling factor, otherwise viewport oversampling settings are used. `pos` is
     * defined in local space.
     *
     * Generated from Godot docs: CanvasItem.draw_char
     */
    fun drawChar(font: Font?, pos: Vector2, char: String, fontSize: Int = 16, modulate: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringIntColorDoubleArgs(drawCharBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, char, fontSize, modulate, oversampling)
    }

    /**
     * Draws a string first character outline using a custom font. If `oversampling` is greater than
     * zero, it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     * `pos` is defined in local space.
     *
     * Generated from Godot docs: CanvasItem.draw_char_outline
     */
    fun drawCharOutline(font: Font?, pos: Vector2, char: String, fontSize: Int = 16, size: Int = -1, modulate: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringTwoIntColorDoubleArgs(drawCharOutlineBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, char, fontSize, size, modulate, oversampling)
    }

    /**
     * Draws a `Mesh` in 2D, using the provided texture. See `MeshInstance2D` for related
     * documentation. The `transform` is defined in local space. Note: Styleboxes, textures, and meshes
     * stored only inside local variables should not be used with this method in GDScript, because the
     * drawing operation doesn't begin immediately once this method is called. In GDScript, when the
     * function with the local variables ends, the local variables get destroyed before the rendering
     * takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_mesh
     */
    fun drawMesh(mesh: Mesh?, texture: Texture2D?, transform: Transform2D, modulate: Color) {
        ObjectCalls.ptrcallWithTwoObjectTransform2DColorArgs(drawMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, texture?.requireOpenHandle() ?: MemorySegment.NULL, transform, modulate)
    }

    /**
     * Draws a `MultiMesh` in 2D with the provided texture. See `MultiMeshInstance2D` for related
     * documentation. Note: Styleboxes, textures, and meshes stored only inside local variables should
     * not be used with this method in GDScript, because the drawing operation doesn't begin
     * immediately once this method is called. In GDScript, when the function with the local variables
     * ends, the local variables get destroyed before the rendering takes place.
     *
     * Generated from Godot docs: CanvasItem.draw_multimesh
     */
    fun drawMultimesh(multimesh: MultiMesh?, texture: Texture2D?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(drawMultimeshBind, handle, multimesh?.requireOpenHandle() ?: MemorySegment.NULL, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Sets a custom local transform for drawing via components. Anything drawn afterwards will be
     * transformed by this. Note: `FontFile.oversampling` does not take `scale` into account. This
     * means that scaling up/down will cause bitmap fonts and rasterized (non-MSDF) dynamic fonts to
     * appear blurry or pixelated. To ensure text remains crisp regardless of scale, you can enable
     * MSDF font rendering by enabling
     * `ProjectSettings.gui/theme/default_font_multichannel_signed_distance_field` (applies to the
     * default project font only), or enabling Multichannel Signed Distance Field in the import options
     * of a DynamicFont for custom fonts. On system fonts,
     * `SystemFont.multichannel_signed_distance_field` can be enabled in the inspector.
     *
     * Generated from Godot docs: CanvasItem.draw_set_transform
     */
    fun drawSetTransform(position: Vector2, rotation: Double = 0.0, scale: Vector2) {
        ObjectCalls.ptrcallWithVector2DoubleVector2Args(drawSetTransformBind, handle, position, rotation, scale)
    }

    /**
     * Sets a custom local transform for drawing via matrix. Anything drawn afterwards will be
     * transformed by this.
     *
     * Generated from Godot docs: CanvasItem.draw_set_transform_matrix
     */
    fun drawSetTransformMatrix(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(drawSetTransformMatrixBind, handle, xform)
    }

    /**
     * Subsequent drawing commands will be ignored unless they fall within the specified animation
     * slice. This is a faster way to implement animations that loop on background rather than
     * redrawing constantly.
     *
     * Generated from Godot docs: CanvasItem.draw_animation_slice
     */
    fun drawAnimationSlice(animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithFourDoubleArgs(drawAnimationSliceBind, handle, animationLength, sliceBegin, sliceEnd, offset)
    }

    /**
     * After submitting all animations slices via `draw_animation_slice`, this function can be used to
     * revert drawing to its default state (all subsequent drawing commands will be visible). If you
     * don't care about this particular use case, usage of this function after submitting the slices is
     * not required.
     *
     * Generated from Godot docs: CanvasItem.draw_end_animation
     */
    fun drawEndAnimation() {
        ObjectCalls.ptrcallNoArgs(drawEndAnimationBind, handle)
    }

    /**
     * Returns the transform matrix of this `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.get_transform
     */
    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getTransformBind, handle)
    }

    /**
     * Returns the global transform matrix of this item, i.e. the combined transform up to the topmost
     * `CanvasItem` node. The topmost item is a `CanvasItem` that either has no parent, has
     * non-`CanvasItem` parent or it has `top_level` enabled.
     *
     * Generated from Godot docs: CanvasItem.get_global_transform
     */
    fun getGlobalTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getGlobalTransformBind, handle)
    }

    /**
     * Returns the transform from the local coordinate system of this `CanvasItem` to the `Viewport`s
     * coordinate system.
     *
     * Generated from Godot docs: CanvasItem.get_global_transform_with_canvas
     */
    fun getGlobalTransformWithCanvas(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getGlobalTransformWithCanvasBind, handle)
    }

    /**
     * Returns the transform of this node, converted from its registered canvas's coordinate system to
     * its viewport embedder's coordinate system. See also `Viewport.get_final_transform` and
     * `Node.get_viewport`.
     *
     * Generated from Godot docs: CanvasItem.get_viewport_transform
     */
    fun getViewportTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getViewportTransformBind, handle)
    }

    /**
     * Returns this node's viewport boundaries as a `Rect2`. See also `Node.get_viewport`.
     *
     * Generated from Godot docs: CanvasItem.get_viewport_rect
     */
    fun getViewportRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getViewportRectBind, handle)
    }

    /**
     * Returns the transform of this node, converted from its registered canvas's coordinate system to
     * its viewport's coordinate system. See also `Node.get_viewport`.
     *
     * Generated from Godot docs: CanvasItem.get_canvas_transform
     */
    fun getCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getCanvasTransformBind, handle)
    }

    /**
     * Returns the transform of this `CanvasItem` in global screen coordinates (i.e. taking window
     * position into account). Mostly useful for editor plugins. Equivalent to
     * `get_global_transform_with_canvas` if the window is embedded (see
     * `Viewport.gui_embed_subwindows`).
     *
     * Generated from Godot docs: CanvasItem.get_screen_transform
     */
    fun getScreenTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getScreenTransformBind, handle)
    }

    /**
     * Returns the mouse's position in this `CanvasItem` using the local coordinate system of this
     * `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.get_local_mouse_position
     */
    fun getLocalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLocalMousePositionBind, handle)
    }

    /**
     * Returns mouse cursor's global position relative to the `CanvasLayer` that contains this node.
     * Note: For screen-space coordinates (e.g. when using a non-embedded `Popup`), you can use
     * `DisplayServer.mouse_get_position`.
     *
     * Generated from Godot docs: CanvasItem.get_global_mouse_position
     */
    fun getGlobalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalMousePositionBind, handle)
    }

    /**
     * Returns the `RID` of the `World2D` canvas where this node is registered to, used by the
     * `RenderingServer`.
     *
     * Generated from Godot docs: CanvasItem.get_canvas
     */
    fun getCanvas(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCanvasBind, handle)
    }

    /**
     * Returns the `CanvasLayer` that contains this node, or `null` if the node is not in any
     * `CanvasLayer`.
     *
     * Generated from Godot docs: CanvasItem.get_canvas_layer_node
     */
    fun getCanvasLayerNode(): CanvasLayer? {
        return CanvasLayer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCanvasLayerNodeBind, handle))
    }

    /**
     * Returns the `World2D` this node is registered to. Usually, this is the same as this node's
     * viewport (see `Node.get_viewport` and `Viewport.find_world_2d`).
     *
     * Generated from Godot docs: CanvasItem.get_world_2d
     */
    fun getWorld2d(): World2D? {
        return World2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld2dBind, handle))
    }

    /**
     * The material applied to this `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The material applied to this `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    /**
     * Set the value of a shader uniform for this instance only (per-instance uniform
     * ($DOCS_URL/tutorials/shaders/shader_reference/shading_language.html#per-instance-uniforms)). See
     * also `ShaderMaterial.set_shader_parameter` to assign a uniform on all instances using the same
     * `ShaderMaterial`. Note: For a shader uniform to be assignable on a per-instance basis, it must
     * be defined with `instance uniform ...` rather than `uniform ...` in the shader code. Note:
     * `name` is case-sensitive and must match the name of the uniform in the code exactly (not the
     * capitalized name in the inspector).
     *
     * Generated from Godot docs: CanvasItem.set_instance_shader_parameter
     */
    fun setInstanceShaderParameter(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setInstanceShaderParameterBind, handle, name, value)
    }

    /**
     * Get the value of a shader parameter as set on this instance.
     *
     * Generated from Godot docs: CanvasItem.get_instance_shader_parameter
     */
    fun getInstanceShaderParameter(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getInstanceShaderParameterBind, handle, name)
    }

    /**
     * If `true`, the parent `CanvasItem`'s `material` is used as this node's material.
     *
     * Generated from Godot docs: CanvasItem.set_use_parent_material
     */
    fun setUseParentMaterial(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseParentMaterialBind, handle, enable)
    }

    /**
     * If `true`, the parent `CanvasItem`'s `material` is used as this node's material.
     *
     * Generated from Godot docs: CanvasItem.get_use_parent_material
     */
    fun getUseParentMaterial(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseParentMaterialBind, handle)
    }

    /**
     * If `true`, the node will receive `NOTIFICATION_LOCAL_TRANSFORM_CHANGED` whenever its local
     * transform changes. Note: Many canvas items such as `Bone2D` or `CollisionShape2D` automatically
     * enable this in order to function correctly.
     *
     * Generated from Godot docs: CanvasItem.set_notify_local_transform
     */
    fun setNotifyLocalTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyLocalTransformBind, handle, enable)
    }

    /**
     * Returns `true` if the node receives `NOTIFICATION_LOCAL_TRANSFORM_CHANGED` whenever its local
     * transform changes. This is enabled with `set_notify_local_transform`.
     *
     * Generated from Godot docs: CanvasItem.is_local_transform_notification_enabled
     */
    fun isLocalTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalTransformNotificationEnabledBind, handle)
    }

    /**
     * If `true`, the node will receive `NOTIFICATION_TRANSFORM_CHANGED` whenever its global transform
     * changes. Note: Many canvas items such as `Camera2D` or `Light2D` automatically enable this in
     * order to function correctly.
     *
     * Generated from Godot docs: CanvasItem.set_notify_transform
     */
    fun setNotifyTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyTransformBind, handle, enable)
    }

    /**
     * Returns `true` if the node receives `NOTIFICATION_TRANSFORM_CHANGED` whenever its global
     * transform changes. This is enabled with `set_notify_transform`.
     *
     * Generated from Godot docs: CanvasItem.is_transform_notification_enabled
     */
    fun isTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransformNotificationEnabledBind, handle)
    }

    /**
     * Forces the node's transform to update. Fails if the node is not inside the tree. See also
     * `get_transform`. Note: For performance reasons, transform changes are usually accumulated and
     * applied once at the end of the frame. The update propagates through `CanvasItem` children, as
     * well. Therefore, use this method only when you need an up-to-date transform (such as during
     * physics operations).
     *
     * Generated from Godot docs: CanvasItem.force_update_transform
     */
    fun forceUpdateTransform() {
        ObjectCalls.ptrcallNoArgs(forceUpdateTransformBind, handle)
    }

    /**
     * Transforms `viewport_point` from the viewport's coordinates to this node's local coordinates.
     * For the opposite operation, use `get_global_transform_with_canvas`.
     *
     * Generated from Godot docs: CanvasItem.make_canvas_position_local
     */
    fun makeCanvasPositionLocal(viewportPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(makeCanvasPositionLocalBind, handle, viewportPoint)
    }

    /**
     * Returns a copy of the given `event` with its coordinates converted from global space to this
     * `CanvasItem`'s local space. If not possible, returns the same `InputEvent` unchanged.
     *
     * Generated from Godot docs: CanvasItem.make_input_local
     */
    fun makeInputLocal(event: InputEvent?): InputEvent? {
        return InputEvent.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(makeInputLocalBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The rendering layer in which this `CanvasItem` is rendered by `Viewport` nodes. A `Viewport`
     * will render a `CanvasItem` if it and all its parents share a layer with the `Viewport`'s canvas
     * cull mask. Note: A `CanvasItem` does not inherit its parents' visibility layers. This means that
     * if a parent `CanvasItem` does not have all the same layers as its child, the child may not be
     * visible even if both the parent and child have `visible` set to `true`. For example, if a parent
     * has layer 1 and a child has layer 2, the child will not be visible in a `Viewport` with the
     * canvas cull mask set to layer 1 or 2 (see `Viewport.canvas_cull_mask`). To ensure that both the
     * parent and child are visible, the parent must have both layers 1 and 2, or the child must have
     * `top_level` set to `true`.
     *
     * Generated from Godot docs: CanvasItem.set_visibility_layer
     */
    fun setVisibilityLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setVisibilityLayerBind, handle, layer)
    }

    /**
     * The rendering layer in which this `CanvasItem` is rendered by `Viewport` nodes. A `Viewport`
     * will render a `CanvasItem` if it and all its parents share a layer with the `Viewport`'s canvas
     * cull mask. Note: A `CanvasItem` does not inherit its parents' visibility layers. This means that
     * if a parent `CanvasItem` does not have all the same layers as its child, the child may not be
     * visible even if both the parent and child have `visible` set to `true`. For example, if a parent
     * has layer 1 and a child has layer 2, the child will not be visible in a `Viewport` with the
     * canvas cull mask set to layer 1 or 2 (see `Viewport.canvas_cull_mask`). To ensure that both the
     * parent and child are visible, the parent must have both layers 1 and 2, or the child must have
     * `top_level` set to `true`.
     *
     * Generated from Godot docs: CanvasItem.get_visibility_layer
     */
    fun getVisibilityLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getVisibilityLayerBind, handle)
    }

    /**
     * Set/clear individual bits on the rendering visibility layer. This simplifies editing this
     * `CanvasItem`'s visibility layer.
     *
     * Generated from Godot docs: CanvasItem.set_visibility_layer_bit
     */
    fun setVisibilityLayerBit(layer: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(setVisibilityLayerBitBind, handle, layer, enabled)
    }

    /**
     * Returns `true` if the layer at the given index is set in `visibility_layer`.
     *
     * Generated from Godot docs: CanvasItem.get_visibility_layer_bit
     */
    fun getVisibilityLayerBit(layer: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(getVisibilityLayerBitBind, handle, layer)
    }

    /**
     * The filtering mode used to render this `CanvasItem`'s texture(s).
     *
     * Generated from Godot docs: CanvasItem.set_texture_filter
     */
    fun setTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, mode)
    }

    /**
     * The filtering mode used to render this `CanvasItem`'s texture(s).
     *
     * Generated from Godot docs: CanvasItem.get_texture_filter
     */
    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    /**
     * The repeating mode used to render this `CanvasItem`'s texture(s). It affects what happens when
     * the texture is sampled outside its extents, for example by setting a `Sprite2D.region_rect` that
     * is larger than the texture or assigning `Polygon2D` UV points outside the texture. Note:
     * `TextureRect` is not affected by `texture_repeat`, as it uses its own texture repeating
     * implementation.
     *
     * Generated from Godot docs: CanvasItem.set_texture_repeat
     */
    fun setTextureRepeat(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureRepeatBind, handle, mode)
    }

    /**
     * The repeating mode used to render this `CanvasItem`'s texture(s). It affects what happens when
     * the texture is sampled outside its extents, for example by setting a `Sprite2D.region_rect` that
     * is larger than the texture or assigning `Polygon2D` UV points outside the texture. Note:
     * `TextureRect` is not affected by `texture_repeat`, as it uses its own texture repeating
     * implementation.
     *
     * Generated from Godot docs: CanvasItem.get_texture_repeat
     */
    fun getTextureRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureRepeatBind, handle)
    }

    /**
     * The mode in which this node clips its children, acting as a mask. Note: Clipping nodes cannot be
     * nested or placed within a `CanvasGroup`. If an ancestor of this node clips its children or is a
     * `CanvasGroup`, then this node's clip mode should be set to `CLIP_CHILDREN_DISABLED` to avoid
     * unexpected behavior.
     *
     * Generated from Godot docs: CanvasItem.set_clip_children_mode
     */
    fun setClipChildrenMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setClipChildrenModeBind, handle, mode)
    }

    /**
     * The mode in which this node clips its children, acting as a mask. Note: Clipping nodes cannot be
     * nested or placed within a `CanvasGroup`. If an ancestor of this node clips its children or is a
     * `CanvasGroup`, then this node's clip mode should be set to `CLIP_CHILDREN_DISABLED` to avoid
     * unexpected behavior.
     *
     * Generated from Godot docs: CanvasItem.get_clip_children_mode
     */
    fun getClipChildrenMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getClipChildrenModeBind, handle)
    }

    /**
     * If enabled, oversampling for this `CanvasItem` is automatically adjusted with scale.
     *
     * Generated from Godot docs: CanvasItem.set_oversampling_with_scale
     */
    fun setOversamplingWithScale(enabled: Long) {
        ObjectCalls.ptrcallWithLongArg(setOversamplingWithScaleBind, handle, enabled)
    }

    /**
     * If enabled, oversampling for this `CanvasItem` is automatically adjusted with scale.
     *
     * Generated from Godot docs: CanvasItem.get_oversampling_with_scale
     */
    fun getOversamplingWithScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOversamplingWithScaleBind, handle)
    }

    object Signals {
        const val draw: String = "draw"
        const val visibilityChanged: String = "visibility_changed"
        const val hidden: String = "hidden"
        const val itemRectChanged: String = "item_rect_changed"
    }

    companion object {
        const val NOTIFICATION_TRANSFORM_CHANGED: Long = 2000L
        const val NOTIFICATION_LOCAL_TRANSFORM_CHANGED: Long = 35L
        const val NOTIFICATION_DRAW: Long = 30L
        const val NOTIFICATION_VISIBILITY_CHANGED: Long = 31L
        const val NOTIFICATION_ENTER_CANVAS: Long = 32L
        const val NOTIFICATION_EXIT_CANVAS: Long = 33L
        const val NOTIFICATION_WORLD_2D_CHANGED: Long = 36L
        const val TEXTURE_FILTER_PARENT_NODE: Long = 0L
        const val TEXTURE_FILTER_NEAREST: Long = 1L
        const val TEXTURE_FILTER_LINEAR: Long = 2L
        const val TEXTURE_FILTER_NEAREST_WITH_MIPMAPS: Long = 3L
        const val TEXTURE_FILTER_LINEAR_WITH_MIPMAPS: Long = 4L
        const val TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC: Long = 5L
        const val TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC: Long = 6L
        const val TEXTURE_FILTER_MAX: Long = 7L
        const val TEXTURE_REPEAT_PARENT_NODE: Long = 0L
        const val TEXTURE_REPEAT_DISABLED: Long = 1L
        const val TEXTURE_REPEAT_ENABLED: Long = 2L
        const val TEXTURE_REPEAT_MIRROR: Long = 3L
        const val TEXTURE_REPEAT_MAX: Long = 4L
        const val CLIP_CHILDREN_DISABLED: Long = 0L
        const val CLIP_CHILDREN_ONLY: Long = 1L
        const val CLIP_CHILDREN_AND_DRAW: Long = 2L
        const val CLIP_CHILDREN_MAX: Long = 3L
        const val OVERSAMPLING_WITH_SCALE_PARENT_NODE: Long = 0L
        const val OVERSAMPLING_WITH_SCALE_DISABLED: Long = 1L
        const val OVERSAMPLING_WITH_SCALE_ENABLED: Long = 2L
        const val OVERSAMPLING_WITH_SCALE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasItem? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasItem? =
            if (handle.address() == 0L) null else CanvasItem(handle)

        private const val GET_CANVAS_ITEM_HASH = 2944877500L
        private val getCanvasItemBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_item", GET_CANVAS_ITEM_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 36873697L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_visible", IS_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_IN_TREE_HASH = 36873697L
        private val isVisibleInTreeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_visible_in_tree", IS_VISIBLE_IN_TREE_HASH)
        }

        private const val SHOW_HASH = 3218959716L
        private val showBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "show", SHOW_HASH)
        }

        private const val HIDE_HASH = 3218959716L
        private val hideBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "hide", HIDE_HASH)
        }

        private const val QUEUE_REDRAW_HASH = 3218959716L
        private val queueRedrawBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "queue_redraw", QUEUE_REDRAW_HASH)
        }

        private const val MOVE_TO_FRONT_HASH = 3218959716L
        private val moveToFrontBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "move_to_front", MOVE_TO_FRONT_HASH)
        }

        private const val SET_AS_TOP_LEVEL_HASH = 2586408642L
        private val setAsTopLevelBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_as_top_level", SET_AS_TOP_LEVEL_HASH)
        }

        private const val IS_SET_AS_TOP_LEVEL_HASH = 36873697L
        private val isSetAsTopLevelBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_set_as_top_level", IS_SET_AS_TOP_LEVEL_HASH)
        }

        private const val SET_LIGHT_MASK_HASH = 1286410249L
        private val setLightMaskBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_light_mask", SET_LIGHT_MASK_HASH)
        }

        private const val GET_LIGHT_MASK_HASH = 3905245786L
        private val getLightMaskBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_light_mask", GET_LIGHT_MASK_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_SELF_MODULATE_HASH = 2920490490L
        private val setSelfModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_self_modulate", SET_SELF_MODULATE_HASH)
        }

        private const val GET_SELF_MODULATE_HASH = 3444240500L
        private val getSelfModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_self_modulate", GET_SELF_MODULATE_HASH)
        }

        private const val SET_Z_INDEX_HASH = 1286410249L
        private val setZIndexBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_z_index", SET_Z_INDEX_HASH)
        }

        private const val GET_Z_INDEX_HASH = 3905245786L
        private val getZIndexBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_z_index", GET_Z_INDEX_HASH)
        }

        private const val SET_Z_AS_RELATIVE_HASH = 2586408642L
        private val setZAsRelativeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_z_as_relative", SET_Z_AS_RELATIVE_HASH)
        }

        private const val IS_Z_RELATIVE_HASH = 36873697L
        private val isZRelativeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_z_relative", IS_Z_RELATIVE_HASH)
        }

        private const val SET_Y_SORT_ENABLED_HASH = 2586408642L
        private val setYSortEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_y_sort_enabled", SET_Y_SORT_ENABLED_HASH)
        }

        private const val IS_Y_SORT_ENABLED_HASH = 36873697L
        private val isYSortEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_y_sort_enabled", IS_Y_SORT_ENABLED_HASH)
        }

        private const val SET_DRAW_BEHIND_PARENT_HASH = 2586408642L
        private val setDrawBehindParentBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_draw_behind_parent", SET_DRAW_BEHIND_PARENT_HASH)
        }

        private const val IS_DRAW_BEHIND_PARENT_ENABLED_HASH = 36873697L
        private val isDrawBehindParentEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_draw_behind_parent_enabled", IS_DRAW_BEHIND_PARENT_ENABLED_HASH)
        }

        private const val DRAW_LINE_HASH = 1562330099L
        private val drawLineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_line", DRAW_LINE_HASH)
        }

        private const val DRAW_DASHED_LINE_HASH = 3653831622L
        private val drawDashedLineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_dashed_line", DRAW_DASHED_LINE_HASH)
        }

        private const val DRAW_POLYLINE_HASH = 3797364428L
        private val drawPolylineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_polyline", DRAW_POLYLINE_HASH)
        }

        private const val DRAW_POLYLINE_COLORS_HASH = 2311979562L
        private val drawPolylineColorsBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_polyline_colors", DRAW_POLYLINE_COLORS_HASH)
        }

        private const val DRAW_ELLIPSE_ARC_HASH = 936174114L
        private val drawEllipseArcBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_ellipse_arc", DRAW_ELLIPSE_ARC_HASH)
        }

        private const val DRAW_ARC_HASH = 4140652635L
        private val drawArcBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_arc", DRAW_ARC_HASH)
        }

        private const val DRAW_MULTILINE_HASH = 3797364428L
        private val drawMultilineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline", DRAW_MULTILINE_HASH)
        }

        private const val DRAW_MULTILINE_COLORS_HASH = 2311979562L
        private val drawMultilineColorsBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_colors", DRAW_MULTILINE_COLORS_HASH)
        }

        private const val DRAW_RECT_HASH = 2773573813L
        private val drawRectBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_rect", DRAW_RECT_HASH)
        }

        private const val DRAW_CIRCLE_HASH = 3153026596L
        private val drawCircleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_circle", DRAW_CIRCLE_HASH)
        }

        private const val DRAW_ELLIPSE_HASH = 3790774806L
        private val drawEllipseBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_ellipse", DRAW_ELLIPSE_HASH)
        }

        private const val DRAW_TEXTURE_HASH = 520200117L
        private val drawTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture", DRAW_TEXTURE_HASH)
        }

        private const val DRAW_TEXTURE_RECT_HASH = 3832805018L
        private val drawTextureRectBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture_rect", DRAW_TEXTURE_RECT_HASH)
        }

        private const val DRAW_TEXTURE_RECT_REGION_HASH = 3883821411L
        private val drawTextureRectRegionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture_rect_region", DRAW_TEXTURE_RECT_REGION_HASH)
        }

        private const val DRAW_MSDF_TEXTURE_RECT_REGION_HASH = 4219163252L
        private val drawMsdfTextureRectRegionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_msdf_texture_rect_region", DRAW_MSDF_TEXTURE_RECT_REGION_HASH)
        }

        private const val DRAW_LCD_TEXTURE_RECT_REGION_HASH = 3212350954L
        private val drawLcdTextureRectRegionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_lcd_texture_rect_region", DRAW_LCD_TEXTURE_RECT_REGION_HASH)
        }

        private const val DRAW_STYLE_BOX_HASH = 388176283L
        private val drawStyleBoxBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_style_box", DRAW_STYLE_BOX_HASH)
        }

        private const val DRAW_PRIMITIVE_HASH = 3288481815L
        private val drawPrimitiveBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_primitive", DRAW_PRIMITIVE_HASH)
        }

        private const val DRAW_POLYGON_HASH = 974537912L
        private val drawPolygonBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_polygon", DRAW_POLYGON_HASH)
        }

        private const val DRAW_COLORED_POLYGON_HASH = 15245644L
        private val drawColoredPolygonBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_colored_polygon", DRAW_COLORED_POLYGON_HASH)
        }

        private const val DRAW_STRING_HASH = 719605945L
        private val drawStringBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_string", DRAW_STRING_HASH)
        }

        private const val DRAW_MULTILINE_STRING_HASH = 2341488182L
        private val drawMultilineStringBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_string", DRAW_MULTILINE_STRING_HASH)
        }

        private const val DRAW_STRING_OUTLINE_HASH = 707403449L
        private val drawStringOutlineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_string_outline", DRAW_STRING_OUTLINE_HASH)
        }

        private const val DRAW_MULTILINE_STRING_OUTLINE_HASH = 3050414441L
        private val drawMultilineStringOutlineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_string_outline", DRAW_MULTILINE_STRING_OUTLINE_HASH)
        }

        private const val DRAW_CHAR_HASH = 1336210142L
        private val drawCharBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_char", DRAW_CHAR_HASH)
        }

        private const val DRAW_CHAR_OUTLINE_HASH = 1846384149L
        private val drawCharOutlineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_char_outline", DRAW_CHAR_OUTLINE_HASH)
        }

        private const val DRAW_MESH_HASH = 153818295L
        private val drawMeshBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_mesh", DRAW_MESH_HASH)
        }

        private const val DRAW_MULTIMESH_HASH = 937992368L
        private val drawMultimeshBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multimesh", DRAW_MULTIMESH_HASH)
        }

        private const val DRAW_SET_TRANSFORM_HASH = 288975085L
        private val drawSetTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform", DRAW_SET_TRANSFORM_HASH)
        }

        private const val DRAW_SET_TRANSFORM_MATRIX_HASH = 2761652528L
        private val drawSetTransformMatrixBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform_matrix", DRAW_SET_TRANSFORM_MATRIX_HASH)
        }

        private const val DRAW_ANIMATION_SLICE_HASH = 3112831842L
        private val drawAnimationSliceBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_animation_slice", DRAW_ANIMATION_SLICE_HASH)
        }

        private const val DRAW_END_ANIMATION_HASH = 3218959716L
        private val drawEndAnimationBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_end_animation", DRAW_END_ANIMATION_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3814499831L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_TRANSFORM_HASH = 3814499831L
        private val getGlobalTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_transform", GET_GLOBAL_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_TRANSFORM_WITH_CANVAS_HASH = 3814499831L
        private val getGlobalTransformWithCanvasBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_transform_with_canvas", GET_GLOBAL_TRANSFORM_WITH_CANVAS_HASH)
        }

        private const val GET_VIEWPORT_TRANSFORM_HASH = 3814499831L
        private val getViewportTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_viewport_transform", GET_VIEWPORT_TRANSFORM_HASH)
        }

        private const val GET_VIEWPORT_RECT_HASH = 1639390495L
        private val getViewportRectBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_viewport_rect", GET_VIEWPORT_RECT_HASH)
        }

        private const val GET_CANVAS_TRANSFORM_HASH = 3814499831L
        private val getCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_transform", GET_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_SCREEN_TRANSFORM_HASH = 3814499831L
        private val getScreenTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_screen_transform", GET_SCREEN_TRANSFORM_HASH)
        }

        private const val GET_LOCAL_MOUSE_POSITION_HASH = 3341600327L
        private val getLocalMousePositionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_local_mouse_position", GET_LOCAL_MOUSE_POSITION_HASH)
        }

        private const val GET_GLOBAL_MOUSE_POSITION_HASH = 3341600327L
        private val getGlobalMousePositionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_mouse_position", GET_GLOBAL_MOUSE_POSITION_HASH)
        }

        private const val GET_CANVAS_HASH = 2944877500L
        private val getCanvasBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas", GET_CANVAS_HASH)
        }

        private const val GET_CANVAS_LAYER_NODE_HASH = 2602762519L
        private val getCanvasLayerNodeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_layer_node", GET_CANVAS_LAYER_NODE_HASH)
        }

        private const val GET_WORLD_2D_HASH = 2339128592L
        private val getWorld2dBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_world_2d", GET_WORLD_2D_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_material", GET_MATERIAL_HASH)
        }

        private const val SET_INSTANCE_SHADER_PARAMETER_HASH = 3776071444L
        private val setInstanceShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_instance_shader_parameter", SET_INSTANCE_SHADER_PARAMETER_HASH)
        }

        private const val GET_INSTANCE_SHADER_PARAMETER_HASH = 2760726917L
        private val getInstanceShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_instance_shader_parameter", GET_INSTANCE_SHADER_PARAMETER_HASH)
        }

        private const val SET_USE_PARENT_MATERIAL_HASH = 2586408642L
        private val setUseParentMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_use_parent_material", SET_USE_PARENT_MATERIAL_HASH)
        }

        private const val GET_USE_PARENT_MATERIAL_HASH = 36873697L
        private val getUseParentMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_use_parent_material", GET_USE_PARENT_MATERIAL_HASH)
        }

        private const val SET_NOTIFY_LOCAL_TRANSFORM_HASH = 2586408642L
        private val setNotifyLocalTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_notify_local_transform", SET_NOTIFY_LOCAL_TRANSFORM_HASH)
        }

        private const val IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        private val isLocalTransformNotificationEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_local_transform_notification_enabled", IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH)
        }

        private const val SET_NOTIFY_TRANSFORM_HASH = 2586408642L
        private val setNotifyTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_notify_transform", SET_NOTIFY_TRANSFORM_HASH)
        }

        private const val IS_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        private val isTransformNotificationEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_transform_notification_enabled", IS_TRANSFORM_NOTIFICATION_ENABLED_HASH)
        }

        private const val FORCE_UPDATE_TRANSFORM_HASH = 3218959716L
        private val forceUpdateTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "force_update_transform", FORCE_UPDATE_TRANSFORM_HASH)
        }

        private const val MAKE_CANVAS_POSITION_LOCAL_HASH = 2656412154L
        private val makeCanvasPositionLocalBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "make_canvas_position_local", MAKE_CANVAS_POSITION_LOCAL_HASH)
        }

        private const val MAKE_INPUT_LOCAL_HASH = 811130057L
        private val makeInputLocalBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "make_input_local", MAKE_INPUT_LOCAL_HASH)
        }

        private const val SET_VISIBILITY_LAYER_HASH = 1286410249L
        private val setVisibilityLayerBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_visibility_layer", SET_VISIBILITY_LAYER_HASH)
        }

        private const val GET_VISIBILITY_LAYER_HASH = 3905245786L
        private val getVisibilityLayerBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_visibility_layer", GET_VISIBILITY_LAYER_HASH)
        }

        private const val SET_VISIBILITY_LAYER_BIT_HASH = 300928843L
        private val setVisibilityLayerBitBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_visibility_layer_bit", SET_VISIBILITY_LAYER_BIT_HASH)
        }

        private const val GET_VISIBILITY_LAYER_BIT_HASH = 1116898809L
        private val getVisibilityLayerBitBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_visibility_layer_bit", GET_VISIBILITY_LAYER_BIT_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 1037999706L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 121960042L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val SET_TEXTURE_REPEAT_HASH = 1716472974L
        private val setTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)
        }

        private const val GET_TEXTURE_REPEAT_HASH = 2667158319L
        private val getTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)
        }

        private const val SET_CLIP_CHILDREN_MODE_HASH = 1319393776L
        private val setClipChildrenModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_clip_children_mode", SET_CLIP_CHILDREN_MODE_HASH)
        }

        private const val GET_CLIP_CHILDREN_MODE_HASH = 3581808349L
        private val getClipChildrenModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_clip_children_mode", GET_CLIP_CHILDREN_MODE_HASH)
        }

        private const val SET_OVERSAMPLING_WITH_SCALE_HASH = 872218804L
        private val setOversamplingWithScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_oversampling_with_scale", SET_OVERSAMPLING_WITH_SCALE_HASH)
        }

        private const val GET_OVERSAMPLING_WITH_SCALE_HASH = 2026097197L
        private val getOversamplingWithScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_oversampling_with_scale", GET_OVERSAMPLING_WITH_SCALE_HASH)
        }
    }
}

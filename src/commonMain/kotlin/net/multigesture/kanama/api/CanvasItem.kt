package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireGodotReturn
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
open class CanvasItem(handle: GodotHandle) : Node(handle) {
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

    var clipChildren: CanvasItem.ClipChildrenMode
        @JvmName("clipChildrenProperty")
        get() = getClipChildrenMode()
        @JvmName("setClipChildrenProperty")
        set(value) = setClipChildrenMode(value)

    var oversamplingWithScale: CanvasItem.OversamplingWithScale
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

    var textureFilter: CanvasItem.TextureFilter
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: CanvasItem.TextureRepeat
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
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getCanvasItemBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibleBind, segment, visible)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleInTreeBind, segment)
    }

    /**
     * Show the `CanvasItem` if it's currently hidden. This is equivalent to setting `visible` to
     * `true`. Note: For controls that inherit `Popup`, the correct way to make them visible is to call
     * one of the multiple `popup*()` functions instead.
     *
     * Generated from Godot docs: CanvasItem.show
     */
    fun show() {
        ObjectCalls.ptrcallNoArgs(Binds.showBind, segment)
    }

    /**
     * Hide the `CanvasItem` if it's currently visible. This is equivalent to setting `visible` to
     * `false`.
     *
     * Generated from Godot docs: CanvasItem.hide
     */
    fun hide() {
        ObjectCalls.ptrcallNoArgs(Binds.hideBind, segment)
    }

    /**
     * Queues the `CanvasItem` to redraw. During idle time, if `CanvasItem` is visible,
     * `NOTIFICATION_DRAW` is sent and `_draw` is called. This only occurs once per frame, even if this
     * method has been called multiple times.
     *
     * Generated from Godot docs: CanvasItem.queue_redraw
     */
    fun queueRedraw() {
        ObjectCalls.ptrcallNoArgs(Binds.queueRedrawBind, segment)
    }

    /**
     * Moves this node below its siblings, usually causing the node to draw on top of its siblings.
     * Does nothing if this node does not have a parent. See also `Node.move_child`.
     *
     * Generated from Godot docs: CanvasItem.move_to_front
     */
    fun moveToFront() {
        ObjectCalls.ptrcallNoArgs(Binds.moveToFrontBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setAsTopLevelBind, segment, enable)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSetAsTopLevelBind, segment)
    }

    /**
     * The rendering layers in which this `CanvasItem` responds to `Light2D` nodes.
     *
     * Generated from Godot docs: CanvasItem.set_light_mask
     */
    fun setLightMask(lightMask: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setLightMaskBind, segment, lightMask)
    }

    /**
     * The rendering layers in which this `CanvasItem` responds to `Light2D` nodes.
     *
     * Generated from Godot docs: CanvasItem.get_light_mask
     */
    fun getLightMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLightMaskBind, segment)
    }

    /**
     * The color applied to this `CanvasItem`. This property does affect child `CanvasItem`s, unlike
     * `self_modulate` which only affects the node itself.
     *
     * Generated from Godot docs: CanvasItem.set_modulate
     */
    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setModulateBind, segment, modulate)
    }

    /**
     * The color applied to this `CanvasItem`. This property does affect child `CanvasItem`s, unlike
     * `self_modulate` which only affects the node itself.
     *
     * Generated from Godot docs: CanvasItem.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getModulateBind, segment)
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
        ObjectCalls.ptrcallWithColorArg(Binds.setSelfModulateBind, segment, selfModulate)
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
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getSelfModulateBind, segment)
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
        ObjectCalls.ptrcallWithIntArg(Binds.setZIndexBind, segment, zIndex)
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
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getZIndexBind, segment)
    }

    /**
     * If `true`, this node's final Z index is relative to its parent's Z index. For example, if
     * `z_index` is `2` and its parent's final Z index is `3`, then this node's final Z index will be
     * `5` (`2 + 3`).
     *
     * Generated from Godot docs: CanvasItem.set_z_as_relative
     */
    fun setZAsRelative(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setZAsRelativeBind, segment, enable)
    }

    /**
     * If `true`, this node's final Z index is relative to its parent's Z index. For example, if
     * `z_index` is `2` and its parent's final Z index is `3`, then this node's final Z index will be
     * `5` (`2 + 3`).
     *
     * Generated from Godot docs: CanvasItem.is_z_relative
     */
    fun isZRelative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isZRelativeBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setYSortEnabledBind, segment, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isYSortEnabledBind, segment)
    }

    /**
     * If `true`, this node draws behind its parent.
     *
     * Generated from Godot docs: CanvasItem.set_draw_behind_parent
     */
    fun setDrawBehindParent(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDrawBehindParentBind, segment, enable)
    }

    /**
     * If `true`, this node draws behind its parent.
     *
     * Generated from Godot docs: CanvasItem.is_draw_behind_parent_enabled
     */
    fun isDrawBehindParentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDrawBehindParentEnabledBind, segment)
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
        ObjectCalls.ptrcallWithTwoVector2ColorDoubleBoolArgs(Binds.drawLineBind, segment, from, to, color, width, antialiased)
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
        ObjectCalls.ptrcallWithTwoVector2ColorTwoDoubleTwoBoolArgs(Binds.drawDashedLineBind, segment, from, to, color, width, dash, aligned, antialiased)
    }

    /**
     * Draws interconnected line segments with a uniform `color` and `width` and optional antialiasing
     * (supported only for positive `width`). The `points` array is defined in local space. When
     * drawing large amounts of lines, this is faster than using individual `draw_line` calls. To draw
     * disconnected lines, use `draw_multiline` instead. See also `draw_polygon`. If `width` is
     * negative, it will be ignored and the polyline will be drawn using
     * `RenderingServer.PrimitiveType.LINE_STRIP`. This means that when the CanvasItem is scaled, the
     * polyline will remain thin. If this behavior is not desired, then pass a positive `width` like
     * `1.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_polyline
     */
    fun drawPolyline(points: List<Vector2>, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListColorDoubleAndBoolArgs(Binds.drawPolylineBind, segment, points, color, width, antialiased)
    }

    /**
     * Draws interconnected line segments with a uniform `width`, point-by-point coloring, and optional
     * antialiasing (supported only for positive `width`). Colors assigned to line points match by
     * index between `points` and `colors`, i.e. each line segment is filled with a gradient between
     * the colors of the endpoints. The `points` array is defined in local space. When drawing large
     * amounts of lines, this is faster than using individual `draw_line` calls. To draw disconnected
     * lines, use `draw_multiline_colors` instead. See also `draw_polygon`. If `width` is negative, it
     * will be ignored and the polyline will be drawn using `RenderingServer.PrimitiveType.LINE_STRIP`.
     * This means that when the CanvasItem is scaled, the polyline will remain thin. If this behavior
     * is not desired, then pass a positive `width` like `1.0`.
     *
     * Generated from Godot docs: CanvasItem.draw_polyline_colors
     */
    fun drawPolylineColors(points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs(Binds.drawPolylineColorsBind, segment, points, colors, width, antialiased)
    }

    /**
     * Draws an unfilled elliptical arc between the given angles with a uniform `color` and `width` and
     * optional antialiasing (supported only for positive `width`). The larger the value of
     * `point_count`, the smoother the curve. For circular arcs, see `draw_arc`. See also
     * `draw_ellipse`. If `width` is negative, it will be ignored and the arc will be drawn using
     * `RenderingServer.PrimitiveType.LINE_STRIP`. This means that when the CanvasItem is scaled, the
     * arc will remain thin. If this behavior is not desired, then pass a positive `width` like `1.0`.
     * The arc is drawn from `start_angle` towards the value of `end_angle` so in clockwise direction
     * if `start_angle < end_angle` and counter-clockwise otherwise. Passing the same angles but in
     * reversed order will produce the same arc. If absolute difference of `start_angle` and
     * `end_angle` is greater than `@GDScript.TAU` radians, then a full ellipse is drawn (i.e. arc will
     * not overlap itself).
     *
     * Generated from Godot docs: CanvasItem.draw_ellipse_arc
     */
    fun drawEllipseArc(center: Vector2, major: Double, minor: Double, startAngle: Double, endAngle: Double, pointCount: Int, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2FourDoubleIntColorDoubleBoolArgs(Binds.drawEllipseArcBind, segment, center, major, minor, startAngle, endAngle, pointCount, color, width, antialiased)
    }

    /**
     * Draws an unfilled arc between the given angles with a uniform `color` and `width` and optional
     * antialiasing (supported only for positive `width`). The larger the value of `point_count`, the
     * smoother the curve. `center` is defined in local space. For elliptical arcs, see
     * `draw_ellipse_arc`. See also `draw_circle`. If `width` is negative, it will be ignored and the
     * arc will be drawn using `RenderingServer.PrimitiveType.LINE_STRIP`. This means that when the
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
        ObjectCalls.ptrcallWithVector2ThreeDoubleIntColorDoubleBoolArgs(Binds.drawArcBind, segment, center, radius, startAngle, endAngle, pointCount, color, width, antialiased)
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
        ObjectCalls.ptrcallWithPackedVector2ListColorDoubleAndBoolArgs(Binds.drawMultilineBind, segment, points, color, width, antialiased)
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
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs(Binds.drawMultilineColorsBind, segment, points, colors, width, antialiased)
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
        ObjectCalls.ptrcallWithRect2ColorBoolDoubleBoolArgs(Binds.drawRectBind, segment, rect, color, filled, width, antialiased)
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
        ObjectCalls.ptrcallWithVector2DoubleColorBoolDoubleBoolArgs(Binds.drawCircleBind, segment, position, radius, color, filled, width, antialiased)
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
        ObjectCalls.ptrcallWithVector2TwoDoubleColorBoolDoubleBoolArgs(Binds.drawEllipseBind, segment, position, major, minor, color, filled, width, antialiased)
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
    fun drawTexture(texture: Texture2D, position: Vector2, modulate: Color) {
        ObjectCalls.ptrcallWithObjectVector2AndColorArgs(Binds.drawTextureBind, segment, texture.requireOpenHandle(), position, modulate)
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
    fun drawTextureRect(texture: Texture2D, rect: Rect2, tile: Boolean, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithObjectRect2BoolColorBoolArgs(Binds.drawTextureRectBind, segment, texture.requireOpenHandle(), rect, tile, modulate, transpose)
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
    fun drawTextureRectRegion(texture: Texture2D, rect: Rect2, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithObjectTwoRect2ColorTwoBoolArgs(Binds.drawTextureRectRegionBind, segment, texture.requireOpenHandle(), rect, srcRect, modulate, transpose, clipUv)
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
    fun drawMsdfTextureRectRegion(texture: Texture2D, rect: Rect2, srcRect: Rect2, modulate: Color, outline: Double = 0.0, pixelRange: Double = 4.0, scale: Double = 1.0) {
        ObjectCalls.ptrcallWithObjectTwoRect2ColorThreeDoubleArgs(Binds.drawMsdfTextureRectRegionBind, segment, texture.requireOpenHandle(), rect, srcRect, modulate, outline, pixelRange, scale)
    }

    /**
     * Draws a textured rectangle region of the font texture with LCD subpixel anti-aliasing at a given
     * position, optionally modulated by a color. The `rect` is defined in local space.
     *
     * Generated from Godot docs: CanvasItem.draw_lcd_texture_rect_region
     */
    fun drawLcdTextureRectRegion(texture: Texture2D, rect: Rect2, srcRect: Rect2, modulate: Color) {
        ObjectCalls.ptrcallWithObjectTwoRect2AndColorArgs(Binds.drawLcdTextureRectRegionBind, segment, texture.requireOpenHandle(), rect, srcRect, modulate)
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
    fun drawStyleBox(styleBox: StyleBox, rect: Rect2) {
        ObjectCalls.ptrcallWithObjectAndRect2Arg(Binds.drawStyleBoxBind, segment, styleBox.requireOpenHandle(), rect)
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
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs(Binds.drawPrimitiveBind, segment, points, colors, uvs, texture?.requireOpenHandle() ?: NULL_SEGMENT)
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
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs(Binds.drawPolygonBind, segment, points, colors, uvs, texture?.requireOpenHandle() ?: NULL_SEGMENT)
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
        ObjectCalls.ptrcallWithPackedVector2ListColorPackedVector2ListAndObjectArgs(Binds.drawColoredPolygonBind, segment, points, color, uvs, texture?.requireOpenHandle() ?: NULL_SEGMENT)
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
    fun drawString(font: Font, pos: Vector2, text: String, alignment: HorizontalAlignment = HorizontalAlignment.LEFT, width: Double = -1.0, fontSize: Int = 16, modulate: Color, justificationFlags: TextServer.JustificationFlag = TextServer.JustificationFlag(3L), direction: TextServer.Direction = TextServer.Direction.AUTO, orientation: TextServer.Orientation = TextServer.Orientation.HORIZONTAL, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleIntColorThreeLongDoubleArgs(Binds.drawStringBind, segment, font.requireOpenHandle(), pos, text, alignment.value, width, fontSize, modulate, justificationFlags.value, direction.value, orientation.value, oversampling)
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
    fun drawMultilineString(font: Font, pos: Vector2, text: String, alignment: HorizontalAlignment = HorizontalAlignment.LEFT, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, modulate: Color, brkFlags: TextServer.LineBreakFlag = TextServer.LineBreakFlag(3L), justificationFlags: TextServer.JustificationFlag = TextServer.JustificationFlag(3L), direction: TextServer.Direction = TextServer.Direction.AUTO, orientation: TextServer.Orientation = TextServer.Orientation.HORIZONTAL, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(Binds.drawMultilineStringBind, segment, font.requireOpenHandle(), pos, text, alignment.value, width, fontSize, maxLines, modulate, brkFlags.value, justificationFlags.value, direction.value, orientation.value, oversampling)
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
    fun drawStringOutline(font: Font, pos: Vector2, text: String, alignment: HorizontalAlignment = HorizontalAlignment.LEFT, width: Double = -1.0, fontSize: Int = 16, size: Int = 1, modulate: Color, justificationFlags: TextServer.JustificationFlag = TextServer.JustificationFlag(3L), direction: TextServer.Direction = TextServer.Direction.AUTO, orientation: TextServer.Orientation = TextServer.Orientation.HORIZONTAL, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(Binds.drawStringOutlineBind, segment, font.requireOpenHandle(), pos, text, alignment.value, width, fontSize, size, modulate, justificationFlags.value, direction.value, orientation.value, oversampling)
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
    fun drawMultilineStringOutline(font: Font, pos: Vector2, text: String, alignment: HorizontalAlignment = HorizontalAlignment.LEFT, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, size: Int = 1, modulate: Color, brkFlags: TextServer.LineBreakFlag = TextServer.LineBreakFlag(3L), justificationFlags: TextServer.JustificationFlag = TextServer.JustificationFlag(3L), direction: TextServer.Direction = TextServer.Direction.AUTO, orientation: TextServer.Orientation = TextServer.Orientation.HORIZONTAL, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(Binds.drawMultilineStringOutlineBind, segment, font.requireOpenHandle(), pos, text, alignment.value, width, fontSize, maxLines, size, modulate, brkFlags.value, justificationFlags.value, direction.value, orientation.value, oversampling)
    }

    /**
     * Draws a string first character using a custom font. If `oversampling` is greater than zero, it
     * is used as font oversampling factor, otherwise viewport oversampling settings are used. `pos` is
     * defined in local space.
     *
     * Generated from Godot docs: CanvasItem.draw_char
     */
    fun drawChar(font: Font, pos: Vector2, char: String, fontSize: Int = 16, modulate: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringIntColorDoubleArgs(Binds.drawCharBind, segment, font.requireOpenHandle(), pos, char, fontSize, modulate, oversampling)
    }

    /**
     * Draws a string first character outline using a custom font. If `oversampling` is greater than
     * zero, it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     * `pos` is defined in local space.
     *
     * Generated from Godot docs: CanvasItem.draw_char_outline
     */
    fun drawCharOutline(font: Font, pos: Vector2, char: String, fontSize: Int = 16, size: Int = -1, modulate: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringTwoIntColorDoubleArgs(Binds.drawCharOutlineBind, segment, font.requireOpenHandle(), pos, char, fontSize, size, modulate, oversampling)
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
    fun drawMesh(mesh: Mesh, texture: Texture2D?, transform: Transform2D, modulate: Color) {
        ObjectCalls.ptrcallWithTwoObjectTransform2DColorArgs(Binds.drawMeshBind, segment, mesh.requireOpenHandle(), texture?.requireOpenHandle() ?: NULL_SEGMENT, transform, modulate)
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
    fun drawMultimesh(multimesh: MultiMesh, texture: Texture2D?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.drawMultimeshBind, segment, multimesh.requireOpenHandle(), texture?.requireOpenHandle() ?: NULL_SEGMENT)
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
        ObjectCalls.ptrcallWithVector2DoubleVector2Args(Binds.drawSetTransformBind, segment, position, rotation, scale)
    }

    /**
     * Sets a custom local transform for drawing via matrix. Anything drawn afterwards will be
     * transformed by this.
     *
     * Generated from Godot docs: CanvasItem.draw_set_transform_matrix
     */
    fun drawSetTransformMatrix(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(Binds.drawSetTransformMatrixBind, segment, xform)
    }

    /**
     * Subsequent drawing commands will be ignored unless they fall within the specified animation
     * slice. This is a faster way to implement animations that loop on background rather than
     * redrawing constantly.
     *
     * Generated from Godot docs: CanvasItem.draw_animation_slice
     */
    fun drawAnimationSlice(animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithFourDoubleArgs(Binds.drawAnimationSliceBind, segment, animationLength, sliceBegin, sliceEnd, offset)
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
        ObjectCalls.ptrcallNoArgs(Binds.drawEndAnimationBind, segment)
    }

    /**
     * Returns the transform matrix of this `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.get_transform
     */
    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getTransformBind, segment)
    }

    /**
     * Returns the global transform matrix of this item, i.e. the combined transform up to the topmost
     * `CanvasItem` node. The topmost item is a `CanvasItem` that either has no parent, has
     * non-`CanvasItem` parent or it has `top_level` enabled.
     *
     * Generated from Godot docs: CanvasItem.get_global_transform
     */
    fun getGlobalTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getGlobalTransformBind, segment)
    }

    /**
     * Returns the transform from the local coordinate system of this `CanvasItem` to the `Viewport`s
     * coordinate system.
     *
     * Generated from Godot docs: CanvasItem.get_global_transform_with_canvas
     */
    fun getGlobalTransformWithCanvas(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getGlobalTransformWithCanvasBind, segment)
    }

    /**
     * Returns the transform of this node, converted from its registered canvas's coordinate system to
     * its viewport embedder's coordinate system. See also `Viewport.get_final_transform` and
     * `Node.get_viewport`.
     *
     * Generated from Godot docs: CanvasItem.get_viewport_transform
     */
    fun getViewportTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getViewportTransformBind, segment)
    }

    /**
     * Returns this node's viewport boundaries as a `Rect2`. See also `Node.get_viewport`.
     *
     * Generated from Godot docs: CanvasItem.get_viewport_rect
     */
    fun getViewportRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getViewportRectBind, segment)
    }

    /**
     * Returns the transform of this node, converted from its registered canvas's coordinate system to
     * its viewport's coordinate system. See also `Node.get_viewport`.
     *
     * Generated from Godot docs: CanvasItem.get_canvas_transform
     */
    fun getCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getCanvasTransformBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getScreenTransformBind, segment)
    }

    /**
     * Returns the mouse's position in this `CanvasItem` using the local coordinate system of this
     * `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.get_local_mouse_position
     */
    fun getLocalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getLocalMousePositionBind, segment)
    }

    /**
     * Returns mouse cursor's global position relative to the `CanvasLayer` that contains this node.
     * Note: For screen-space coordinates (e.g. when using a non-embedded `Popup`), you can use
     * `DisplayServer.mouse_get_position`.
     *
     * Generated from Godot docs: CanvasItem.get_global_mouse_position
     */
    fun getGlobalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGlobalMousePositionBind, segment)
    }

    /**
     * Returns the `RID` of the `World2D` canvas where this node is registered to, used by the
     * `RenderingServer`.
     *
     * Generated from Godot docs: CanvasItem.get_canvas
     */
    fun getCanvas(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getCanvasBind, segment)
    }

    /**
     * Returns the `CanvasLayer` that contains this node, or `null` if the node is not in any
     * `CanvasLayer`.
     *
     * Generated from Godot docs: CanvasItem.get_canvas_layer_node
     */
    fun getCanvasLayerNode(): CanvasLayer? {
        return CanvasLayer.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCanvasLayerNodeBind, segment))
    }

    /**
     * Returns the `World2D` this node is registered to. Usually, this is the same as this node's
     * viewport (see `Node.get_viewport` and `Viewport.find_world_2d`).
     *
     * Generated from Godot docs: CanvasItem.get_world_2d
     */
    fun getWorld2d(): World2D? {
        return World2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getWorld2dBind, segment))
    }

    /**
     * The material applied to this `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The material applied to this `CanvasItem`.
     *
     * Generated from Godot docs: CanvasItem.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
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
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setInstanceShaderParameterBind, segment, name, value)
    }

    /**
     * Get the value of a shader parameter as set on this instance.
     *
     * Generated from Godot docs: CanvasItem.get_instance_shader_parameter
     */
    fun getInstanceShaderParameter(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getInstanceShaderParameterBind, segment, name)
    }

    /**
     * If `true`, the parent `CanvasItem`'s `material` is used as this node's material.
     *
     * Generated from Godot docs: CanvasItem.set_use_parent_material
     */
    fun setUseParentMaterial(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseParentMaterialBind, segment, enable)
    }

    /**
     * If `true`, the parent `CanvasItem`'s `material` is used as this node's material.
     *
     * Generated from Godot docs: CanvasItem.get_use_parent_material
     */
    fun getUseParentMaterial(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseParentMaterialBind, segment)
    }

    /**
     * If `true`, the node will receive `NOTIFICATION_LOCAL_TRANSFORM_CHANGED` whenever its local
     * transform changes. Note: Many canvas items such as `Bone2D` or `CollisionShape2D` automatically
     * enable this in order to function correctly.
     *
     * Generated from Godot docs: CanvasItem.set_notify_local_transform
     */
    fun setNotifyLocalTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setNotifyLocalTransformBind, segment, enable)
    }

    /**
     * Returns `true` if the node receives `NOTIFICATION_LOCAL_TRANSFORM_CHANGED` whenever its local
     * transform changes. This is enabled with `set_notify_local_transform`.
     *
     * Generated from Godot docs: CanvasItem.is_local_transform_notification_enabled
     */
    fun isLocalTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isLocalTransformNotificationEnabledBind, segment)
    }

    /**
     * If `true`, the node will receive `NOTIFICATION_TRANSFORM_CHANGED` whenever its global transform
     * changes. Note: Many canvas items such as `Camera2D` or `Light2D` automatically enable this in
     * order to function correctly.
     *
     * Generated from Godot docs: CanvasItem.set_notify_transform
     */
    fun setNotifyTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setNotifyTransformBind, segment, enable)
    }

    /**
     * Returns `true` if the node receives `NOTIFICATION_TRANSFORM_CHANGED` whenever its global
     * transform changes. This is enabled with `set_notify_transform`.
     *
     * Generated from Godot docs: CanvasItem.is_transform_notification_enabled
     */
    fun isTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTransformNotificationEnabledBind, segment)
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
        ObjectCalls.ptrcallNoArgs(Binds.forceUpdateTransformBind, segment)
    }

    /**
     * Transforms `viewport_point` from the viewport's coordinates to this node's local coordinates.
     * For the opposite operation, use `get_global_transform_with_canvas`.
     *
     * Generated from Godot docs: CanvasItem.make_canvas_position_local
     */
    fun makeCanvasPositionLocal(viewportPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(Binds.makeCanvasPositionLocalBind, segment, viewportPoint)
    }

    /**
     * Returns a copy of the given `event` with its coordinates converted from global space to this
     * `CanvasItem`'s local space. If not possible, returns the same `InputEvent` unchanged.
     *
     * Generated from Godot docs: CanvasItem.make_input_local
     */
    fun makeInputLocal(event: InputEvent): InputEvent {
        return requireGodotReturn(InputEvent.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.makeInputLocalBind, segment, event.requireOpenHandle())), "CanvasItem.make_input_local")
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
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setVisibilityLayerBind, segment, layer)
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
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getVisibilityLayerBind, segment)
    }

    /**
     * Set/clear individual bits on the rendering visibility layer. This simplifies editing this
     * `CanvasItem`'s visibility layer.
     *
     * Generated from Godot docs: CanvasItem.set_visibility_layer_bit
     */
    fun setVisibilityLayerBit(layer: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(Binds.setVisibilityLayerBitBind, segment, layer, enabled)
    }

    /**
     * Returns `true` if the layer at the given index is set in `visibility_layer`.
     *
     * Generated from Godot docs: CanvasItem.get_visibility_layer_bit
     */
    fun getVisibilityLayerBit(layer: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(Binds.getVisibilityLayerBitBind, segment, layer)
    }

    /**
     * The filtering mode used to render this `CanvasItem`'s texture(s).
     *
     * Generated from Godot docs: CanvasItem.set_texture_filter
     */
    fun setTextureFilter(mode: CanvasItem.TextureFilter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureFilterBind, segment, mode.value)
    }

    /**
     * The filtering mode used to render this `CanvasItem`'s texture(s).
     *
     * Generated from Godot docs: CanvasItem.get_texture_filter
     */
    fun getTextureFilter(): CanvasItem.TextureFilter {
        return CanvasItem.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureFilterBind, segment))
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
    fun setTextureRepeat(mode: CanvasItem.TextureRepeat) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureRepeatBind, segment, mode.value)
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
    fun getTextureRepeat(): CanvasItem.TextureRepeat {
        return CanvasItem.TextureRepeat(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureRepeatBind, segment))
    }

    /**
     * The mode in which this node clips its children, acting as a mask. Note: Clipping nodes cannot be
     * nested or placed within a `CanvasGroup`. If an ancestor of this node clips its children or is a
     * `CanvasGroup`, then this node's clip mode should be set to `ClipChildrenMode.DISABLED` to avoid
     * unexpected behavior.
     *
     * Generated from Godot docs: CanvasItem.set_clip_children_mode
     */
    fun setClipChildrenMode(mode: CanvasItem.ClipChildrenMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setClipChildrenModeBind, segment, mode.value)
    }

    /**
     * The mode in which this node clips its children, acting as a mask. Note: Clipping nodes cannot be
     * nested or placed within a `CanvasGroup`. If an ancestor of this node clips its children or is a
     * `CanvasGroup`, then this node's clip mode should be set to `ClipChildrenMode.DISABLED` to avoid
     * unexpected behavior.
     *
     * Generated from Godot docs: CanvasItem.get_clip_children_mode
     */
    fun getClipChildrenMode(): CanvasItem.ClipChildrenMode {
        return CanvasItem.ClipChildrenMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getClipChildrenModeBind, segment))
    }

    /**
     * If enabled, oversampling for this `CanvasItem` is automatically adjusted with scale.
     *
     * Generated from Godot docs: CanvasItem.set_oversampling_with_scale
     */
    fun setOversamplingWithScale(enabled: CanvasItem.OversamplingWithScale) {
        ObjectCalls.ptrcallWithLongArg(Binds.setOversamplingWithScaleBind, segment, enabled.value)
    }

    /**
     * If enabled, oversampling for this `CanvasItem` is automatically adjusted with scale.
     *
     * Generated from Godot docs: CanvasItem.get_oversampling_with_scale
     */
    fun getOversamplingWithScale(): CanvasItem.OversamplingWithScale {
        return CanvasItem.OversamplingWithScale(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOversamplingWithScaleBind, segment))
    }

    /** Signal `draw()`; see [TypedSignal]. */
    val draw: Signal0
        @JvmName("drawTypedSignal")
        get() = Signal0(this, "draw")

    /** Signal `visibility_changed()`; see [TypedSignal]. */
    val visibilityChanged: Signal0
        @JvmName("visibilityChangedTypedSignal")
        get() = Signal0(this, "visibility_changed")

    /** Signal `hidden()`; see [TypedSignal]. */
    val hidden: Signal0
        @JvmName("hiddenTypedSignal")
        get() = Signal0(this, "hidden")

    /** Signal `item_rect_changed()`; see [TypedSignal]. */
    val itemRectChanged: Signal0
        @JvmName("itemRectChangedTypedSignal")
        get() = Signal0(this, "item_rect_changed")

    object Signals {
        const val draw: String = "draw"
        const val visibilityChanged: String = "visibility_changed"
        const val hidden: String = "hidden"
        const val itemRectChanged: String = "item_rect_changed"
    }

    /**
     * Godot's `CanvasItem.TextureFilter` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CanvasItem.TextureFilter.<NAME>`).
     *
     * Generated from Godot docs: CanvasItem.TextureFilter
     */
    @JvmInline
    value class TextureFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `CanvasItem` will inherit the filter from its parent.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_PARENT_NODE
             */
            val PARENT_NODE: TextureFilter get() = TextureFilter(0L)
            /**
             * The texture filter reads from the nearest pixel only. This makes the texture look pixelated from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_NEAREST
             */
            val NEAREST: TextureFilter get() = TextureFilter(1L)
            /**
             * The texture filter blends between the nearest 4 pixels. This makes the texture look smooth from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_LINEAR
             */
            val LINEAR: TextureFilter get() = TextureFilter(2L)
            /**
             * The texture filter reads from the nearest pixel and blends between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look pixelated from up close, and smooth from a distance. Use this for
             * non-pixel art textures that may be viewed at a low scale (e.g. due to `Camera2D` zoom or sprite
             * scaling), as mipmaps are important to smooth out pixels that are smaller than on-screen pixels.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS
             */
            val NEAREST_WITH_MIPMAPS: TextureFilter get() = TextureFilter(3L)
            /**
             * The texture filter blends between the nearest 4 pixels and between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look smooth from up close, and smooth from a distance. Use this for non-pixel
             * art textures that may be viewed at a low scale (e.g. due to `Camera2D` zoom or sprite scaling),
             * as mipmaps are important to smooth out pixels that are smaller than on-screen pixels.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS
             */
            val LINEAR_WITH_MIPMAPS: TextureFilter get() = TextureFilter(4L)
            /**
             * The texture filter reads from the nearest pixel and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look pixelated from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`. Note: This
             * texture filter is rarely useful in 2D projects. `TextureFilter.NEAREST_WITH_MIPMAPS` is usually
             * more appropriate in this case.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC
             */
            val NEAREST_WITH_MIPMAPS_ANISOTROPIC: TextureFilter get() = TextureFilter(5L)
            /**
             * The texture filter blends between the nearest 4 pixels and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look smooth from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`. Note: This
             * texture filter is rarely useful in 2D projects. `TextureFilter.LINEAR_WITH_MIPMAPS` is usually
             * more appropriate in this case.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC
             */
            val LINEAR_WITH_MIPMAPS_ANISOTROPIC: TextureFilter get() = TextureFilter(6L)
            /**
             * Represents the size of the `TextureFilter` enum.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_FILTER_MAX
             */
            val MAX: TextureFilter get() = TextureFilter(7L)
        }
    }

    /**
     * Godot's `CanvasItem.TextureRepeat` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CanvasItem.TextureRepeat.<NAME>`).
     *
     * Generated from Godot docs: CanvasItem.TextureRepeat
     */
    @JvmInline
    value class TextureRepeat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `CanvasItem` will inherit the repeat mode from its parent.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_REPEAT_PARENT_NODE
             */
            val PARENT_NODE: TextureRepeat get() = TextureRepeat(0L)
            /**
             * The texture does not repeat. Sampling the texture outside its extents will result in
             * "stretching" of the edge pixels. You can avoid this by ensuring a 1-pixel fully transparent
             * border on each side of the texture.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_REPEAT_DISABLED
             */
            val DISABLED: TextureRepeat get() = TextureRepeat(1L)
            /**
             * The texture repeats when exceeding the texture's size.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_REPEAT_ENABLED
             */
            val ENABLED: TextureRepeat get() = TextureRepeat(2L)
            /**
             * The texture repeats when the exceeding the texture's size in a "2×2 tiled mode". Repeated
             * textures at even positions are mirrored.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_REPEAT_MIRROR
             */
            val MIRROR: TextureRepeat get() = TextureRepeat(3L)
            /**
             * Represents the size of the `TextureRepeat` enum.
             *
             * Generated from Godot docs: CanvasItem.TEXTURE_REPEAT_MAX
             */
            val MAX: TextureRepeat get() = TextureRepeat(4L)
        }
    }

    /**
     * Godot's `CanvasItem.ClipChildrenMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CanvasItem.ClipChildrenMode.<NAME>`).
     *
     * Generated from Godot docs: CanvasItem.ClipChildrenMode
     */
    @JvmInline
    value class ClipChildrenMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Children are drawn over this node and are not clipped.
             *
             * Generated from Godot docs: CanvasItem.CLIP_CHILDREN_DISABLED
             */
            val DISABLED: ClipChildrenMode get() = ClipChildrenMode(0L)
            /**
             * This node is used as a mask and is not drawn. The mask is based on this node's alpha channel:
             * Opaque pixels are kept, transparent pixels are discarded, and semi-transparent pixels are
             * blended in according to their opacity. Children are clipped to this node's drawn area.
             *
             * Generated from Godot docs: CanvasItem.CLIP_CHILDREN_ONLY
             */
            val ONLY: ClipChildrenMode get() = ClipChildrenMode(1L)
            /**
             * This node is used as a mask and is also drawn. The mask is based on this node's alpha channel:
             * Opaque pixels are kept, transparent pixels are discarded, and semi-transparent pixels are
             * blended in according to their opacity. Children are clipped to the parent's drawn area.
             *
             * Generated from Godot docs: CanvasItem.CLIP_CHILDREN_AND_DRAW
             */
            val AND_DRAW: ClipChildrenMode get() = ClipChildrenMode(2L)
            /**
             * Represents the size of the `ClipChildrenMode` enum.
             *
             * Generated from Godot docs: CanvasItem.CLIP_CHILDREN_MAX
             */
            val MAX: ClipChildrenMode get() = ClipChildrenMode(3L)
        }
    }

    /**
     * Godot's `CanvasItem.OversamplingWithScale` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`CanvasItem.OversamplingWithScale.<NAME>`).
     *
     * Generated from Godot docs: CanvasItem.OversamplingWithScale
     */
    @JvmInline
    value class OversamplingWithScale(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `CanvasItem` will inherit the oversampling mode from its parent.
             *
             * Generated from Godot docs: CanvasItem.OVERSAMPLING_WITH_SCALE_PARENT_NODE
             */
            val PARENT_NODE: OversamplingWithScale get() = OversamplingWithScale(0L)
            /**
             * The oversampling is not affected by `CanvasItem` scale, and is equal to the `Viewport`
             * oversampling.
             *
             * Generated from Godot docs: CanvasItem.OVERSAMPLING_WITH_SCALE_DISABLED
             */
            val DISABLED: OversamplingWithScale get() = OversamplingWithScale(1L)
            /**
             * The oversampling is a product of `CanvasItem` scale and `Viewport` oversampling.
             *
             * Generated from Godot docs: CanvasItem.OVERSAMPLING_WITH_SCALE_ENABLED
             */
            val ENABLED: OversamplingWithScale get() = OversamplingWithScale(2L)
            /**
             * Represents the size of the `OversamplingWithScale` enum.
             *
             * Generated from Godot docs: CanvasItem.OVERSAMPLING_WITH_SCALE_MAX
             */
            val MAX: OversamplingWithScale get() = OversamplingWithScale(3L)
        }
    }

    companion object {
        const val NOTIFICATION_TRANSFORM_CHANGED: Long = 2000L
        const val NOTIFICATION_LOCAL_TRANSFORM_CHANGED: Long = 35L
        const val NOTIFICATION_DRAW: Long = 30L
        const val NOTIFICATION_VISIBILITY_CHANGED: Long = 31L
        const val NOTIFICATION_ENTER_CANVAS: Long = 32L
        const val NOTIFICATION_EXIT_CANVAS: Long = 33L
        const val NOTIFICATION_WORLD_2D_CHANGED: Long = 36L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): CanvasItem? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CanvasItem? =
            if (handle.address() == 0L) null else CanvasItem(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_CANVAS_ITEM_HASH = 2944877500L
        @JvmField
        val getCanvasItemBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_item", GET_CANVAS_ITEM_HASH)

        private const val SET_VISIBLE_HASH = 2586408642L
        @JvmField
        val setVisibleBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_visible", SET_VISIBLE_HASH)

        private const val IS_VISIBLE_HASH = 36873697L
        @JvmField
        val isVisibleBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_visible", IS_VISIBLE_HASH)

        private const val IS_VISIBLE_IN_TREE_HASH = 36873697L
        @JvmField
        val isVisibleInTreeBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_visible_in_tree", IS_VISIBLE_IN_TREE_HASH)

        private const val SHOW_HASH = 3218959716L
        @JvmField
        val showBind =
            ObjectCalls.getMethodBind("CanvasItem", "show", SHOW_HASH)

        private const val HIDE_HASH = 3218959716L
        @JvmField
        val hideBind =
            ObjectCalls.getMethodBind("CanvasItem", "hide", HIDE_HASH)

        private const val QUEUE_REDRAW_HASH = 3218959716L
        @JvmField
        val queueRedrawBind =
            ObjectCalls.getMethodBind("CanvasItem", "queue_redraw", QUEUE_REDRAW_HASH)

        private const val MOVE_TO_FRONT_HASH = 3218959716L
        @JvmField
        val moveToFrontBind =
            ObjectCalls.getMethodBind("CanvasItem", "move_to_front", MOVE_TO_FRONT_HASH)

        private const val SET_AS_TOP_LEVEL_HASH = 2586408642L
        @JvmField
        val setAsTopLevelBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_as_top_level", SET_AS_TOP_LEVEL_HASH)

        private const val IS_SET_AS_TOP_LEVEL_HASH = 36873697L
        @JvmField
        val isSetAsTopLevelBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_set_as_top_level", IS_SET_AS_TOP_LEVEL_HASH)

        private const val SET_LIGHT_MASK_HASH = 1286410249L
        @JvmField
        val setLightMaskBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_light_mask", SET_LIGHT_MASK_HASH)

        private const val GET_LIGHT_MASK_HASH = 3905245786L
        @JvmField
        val getLightMaskBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_light_mask", GET_LIGHT_MASK_HASH)

        private const val SET_MODULATE_HASH = 2920490490L
        @JvmField
        val setModulateBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_modulate", SET_MODULATE_HASH)

        private const val GET_MODULATE_HASH = 3444240500L
        @JvmField
        val getModulateBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_modulate", GET_MODULATE_HASH)

        private const val SET_SELF_MODULATE_HASH = 2920490490L
        @JvmField
        val setSelfModulateBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_self_modulate", SET_SELF_MODULATE_HASH)

        private const val GET_SELF_MODULATE_HASH = 3444240500L
        @JvmField
        val getSelfModulateBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_self_modulate", GET_SELF_MODULATE_HASH)

        private const val SET_Z_INDEX_HASH = 1286410249L
        @JvmField
        val setZIndexBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_z_index", SET_Z_INDEX_HASH)

        private const val GET_Z_INDEX_HASH = 3905245786L
        @JvmField
        val getZIndexBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_z_index", GET_Z_INDEX_HASH)

        private const val SET_Z_AS_RELATIVE_HASH = 2586408642L
        @JvmField
        val setZAsRelativeBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_z_as_relative", SET_Z_AS_RELATIVE_HASH)

        private const val IS_Z_RELATIVE_HASH = 36873697L
        @JvmField
        val isZRelativeBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_z_relative", IS_Z_RELATIVE_HASH)

        private const val SET_Y_SORT_ENABLED_HASH = 2586408642L
        @JvmField
        val setYSortEnabledBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_y_sort_enabled", SET_Y_SORT_ENABLED_HASH)

        private const val IS_Y_SORT_ENABLED_HASH = 36873697L
        @JvmField
        val isYSortEnabledBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_y_sort_enabled", IS_Y_SORT_ENABLED_HASH)

        private const val SET_DRAW_BEHIND_PARENT_HASH = 2586408642L
        @JvmField
        val setDrawBehindParentBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_draw_behind_parent", SET_DRAW_BEHIND_PARENT_HASH)

        private const val IS_DRAW_BEHIND_PARENT_ENABLED_HASH = 36873697L
        @JvmField
        val isDrawBehindParentEnabledBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_draw_behind_parent_enabled", IS_DRAW_BEHIND_PARENT_ENABLED_HASH)

        private const val DRAW_LINE_HASH = 1562330099L
        @JvmField
        val drawLineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_line", DRAW_LINE_HASH)

        private const val DRAW_DASHED_LINE_HASH = 3653831622L
        @JvmField
        val drawDashedLineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_dashed_line", DRAW_DASHED_LINE_HASH)

        private const val DRAW_POLYLINE_HASH = 3797364428L
        @JvmField
        val drawPolylineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_polyline", DRAW_POLYLINE_HASH)

        private const val DRAW_POLYLINE_COLORS_HASH = 2311979562L
        @JvmField
        val drawPolylineColorsBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_polyline_colors", DRAW_POLYLINE_COLORS_HASH)

        private const val DRAW_ELLIPSE_ARC_HASH = 936174114L
        @JvmField
        val drawEllipseArcBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_ellipse_arc", DRAW_ELLIPSE_ARC_HASH)

        private const val DRAW_ARC_HASH = 4140652635L
        @JvmField
        val drawArcBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_arc", DRAW_ARC_HASH)

        private const val DRAW_MULTILINE_HASH = 3797364428L
        @JvmField
        val drawMultilineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline", DRAW_MULTILINE_HASH)

        private const val DRAW_MULTILINE_COLORS_HASH = 2311979562L
        @JvmField
        val drawMultilineColorsBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_colors", DRAW_MULTILINE_COLORS_HASH)

        private const val DRAW_RECT_HASH = 2773573813L
        @JvmField
        val drawRectBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_rect", DRAW_RECT_HASH)

        private const val DRAW_CIRCLE_HASH = 3153026596L
        @JvmField
        val drawCircleBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_circle", DRAW_CIRCLE_HASH)

        private const val DRAW_ELLIPSE_HASH = 3790774806L
        @JvmField
        val drawEllipseBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_ellipse", DRAW_ELLIPSE_HASH)

        private const val DRAW_TEXTURE_HASH = 520200117L
        @JvmField
        val drawTextureBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture", DRAW_TEXTURE_HASH)

        private const val DRAW_TEXTURE_RECT_HASH = 3832805018L
        @JvmField
        val drawTextureRectBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture_rect", DRAW_TEXTURE_RECT_HASH)

        private const val DRAW_TEXTURE_RECT_REGION_HASH = 3883821411L
        @JvmField
        val drawTextureRectRegionBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture_rect_region", DRAW_TEXTURE_RECT_REGION_HASH)

        private const val DRAW_MSDF_TEXTURE_RECT_REGION_HASH = 4219163252L
        @JvmField
        val drawMsdfTextureRectRegionBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_msdf_texture_rect_region", DRAW_MSDF_TEXTURE_RECT_REGION_HASH)

        private const val DRAW_LCD_TEXTURE_RECT_REGION_HASH = 3212350954L
        @JvmField
        val drawLcdTextureRectRegionBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_lcd_texture_rect_region", DRAW_LCD_TEXTURE_RECT_REGION_HASH)

        private const val DRAW_STYLE_BOX_HASH = 388176283L
        @JvmField
        val drawStyleBoxBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_style_box", DRAW_STYLE_BOX_HASH)

        private const val DRAW_PRIMITIVE_HASH = 3288481815L
        @JvmField
        val drawPrimitiveBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_primitive", DRAW_PRIMITIVE_HASH)

        private const val DRAW_POLYGON_HASH = 974537912L
        @JvmField
        val drawPolygonBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_polygon", DRAW_POLYGON_HASH)

        private const val DRAW_COLORED_POLYGON_HASH = 15245644L
        @JvmField
        val drawColoredPolygonBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_colored_polygon", DRAW_COLORED_POLYGON_HASH)

        private const val DRAW_STRING_HASH = 719605945L
        @JvmField
        val drawStringBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_string", DRAW_STRING_HASH)

        private const val DRAW_MULTILINE_STRING_HASH = 2341488182L
        @JvmField
        val drawMultilineStringBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_string", DRAW_MULTILINE_STRING_HASH)

        private const val DRAW_STRING_OUTLINE_HASH = 707403449L
        @JvmField
        val drawStringOutlineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_string_outline", DRAW_STRING_OUTLINE_HASH)

        private const val DRAW_MULTILINE_STRING_OUTLINE_HASH = 3050414441L
        @JvmField
        val drawMultilineStringOutlineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_string_outline", DRAW_MULTILINE_STRING_OUTLINE_HASH)

        private const val DRAW_CHAR_HASH = 1336210142L
        @JvmField
        val drawCharBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_char", DRAW_CHAR_HASH)

        private const val DRAW_CHAR_OUTLINE_HASH = 1846384149L
        @JvmField
        val drawCharOutlineBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_char_outline", DRAW_CHAR_OUTLINE_HASH)

        private const val DRAW_MESH_HASH = 153818295L
        @JvmField
        val drawMeshBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_mesh", DRAW_MESH_HASH)

        private const val DRAW_MULTIMESH_HASH = 937992368L
        @JvmField
        val drawMultimeshBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_multimesh", DRAW_MULTIMESH_HASH)

        private const val DRAW_SET_TRANSFORM_HASH = 288975085L
        @JvmField
        val drawSetTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform", DRAW_SET_TRANSFORM_HASH)

        private const val DRAW_SET_TRANSFORM_MATRIX_HASH = 2761652528L
        @JvmField
        val drawSetTransformMatrixBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform_matrix", DRAW_SET_TRANSFORM_MATRIX_HASH)

        private const val DRAW_ANIMATION_SLICE_HASH = 3112831842L
        @JvmField
        val drawAnimationSliceBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_animation_slice", DRAW_ANIMATION_SLICE_HASH)

        private const val DRAW_END_ANIMATION_HASH = 3218959716L
        @JvmField
        val drawEndAnimationBind =
            ObjectCalls.getMethodBind("CanvasItem", "draw_end_animation", DRAW_END_ANIMATION_HASH)

        private const val GET_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_transform", GET_TRANSFORM_HASH)

        private const val GET_GLOBAL_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getGlobalTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_global_transform", GET_GLOBAL_TRANSFORM_HASH)

        private const val GET_GLOBAL_TRANSFORM_WITH_CANVAS_HASH = 3814499831L
        @JvmField
        val getGlobalTransformWithCanvasBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_global_transform_with_canvas", GET_GLOBAL_TRANSFORM_WITH_CANVAS_HASH)

        private const val GET_VIEWPORT_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getViewportTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_viewport_transform", GET_VIEWPORT_TRANSFORM_HASH)

        private const val GET_VIEWPORT_RECT_HASH = 1639390495L
        @JvmField
        val getViewportRectBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_viewport_rect", GET_VIEWPORT_RECT_HASH)

        private const val GET_CANVAS_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getCanvasTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_transform", GET_CANVAS_TRANSFORM_HASH)

        private const val GET_SCREEN_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getScreenTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_screen_transform", GET_SCREEN_TRANSFORM_HASH)

        private const val GET_LOCAL_MOUSE_POSITION_HASH = 3341600327L
        @JvmField
        val getLocalMousePositionBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_local_mouse_position", GET_LOCAL_MOUSE_POSITION_HASH)

        private const val GET_GLOBAL_MOUSE_POSITION_HASH = 3341600327L
        @JvmField
        val getGlobalMousePositionBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_global_mouse_position", GET_GLOBAL_MOUSE_POSITION_HASH)

        private const val GET_CANVAS_HASH = 2944877500L
        @JvmField
        val getCanvasBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas", GET_CANVAS_HASH)

        private const val GET_CANVAS_LAYER_NODE_HASH = 2602762519L
        @JvmField
        val getCanvasLayerNodeBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_layer_node", GET_CANVAS_LAYER_NODE_HASH)

        private const val GET_WORLD_2D_HASH = 2339128592L
        @JvmField
        val getWorld2dBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_world_2d", GET_WORLD_2D_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_material", GET_MATERIAL_HASH)

        private const val SET_INSTANCE_SHADER_PARAMETER_HASH = 3776071444L
        @JvmField
        val setInstanceShaderParameterBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_instance_shader_parameter", SET_INSTANCE_SHADER_PARAMETER_HASH)

        private const val GET_INSTANCE_SHADER_PARAMETER_HASH = 2760726917L
        @JvmField
        val getInstanceShaderParameterBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_instance_shader_parameter", GET_INSTANCE_SHADER_PARAMETER_HASH)

        private const val SET_USE_PARENT_MATERIAL_HASH = 2586408642L
        @JvmField
        val setUseParentMaterialBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_use_parent_material", SET_USE_PARENT_MATERIAL_HASH)

        private const val GET_USE_PARENT_MATERIAL_HASH = 36873697L
        @JvmField
        val getUseParentMaterialBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_use_parent_material", GET_USE_PARENT_MATERIAL_HASH)

        private const val SET_NOTIFY_LOCAL_TRANSFORM_HASH = 2586408642L
        @JvmField
        val setNotifyLocalTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_notify_local_transform", SET_NOTIFY_LOCAL_TRANSFORM_HASH)

        private const val IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        @JvmField
        val isLocalTransformNotificationEnabledBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_local_transform_notification_enabled", IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH)

        private const val SET_NOTIFY_TRANSFORM_HASH = 2586408642L
        @JvmField
        val setNotifyTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_notify_transform", SET_NOTIFY_TRANSFORM_HASH)

        private const val IS_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        @JvmField
        val isTransformNotificationEnabledBind =
            ObjectCalls.getMethodBind("CanvasItem", "is_transform_notification_enabled", IS_TRANSFORM_NOTIFICATION_ENABLED_HASH)

        private const val FORCE_UPDATE_TRANSFORM_HASH = 3218959716L
        @JvmField
        val forceUpdateTransformBind =
            ObjectCalls.getMethodBind("CanvasItem", "force_update_transform", FORCE_UPDATE_TRANSFORM_HASH)

        private const val MAKE_CANVAS_POSITION_LOCAL_HASH = 2656412154L
        @JvmField
        val makeCanvasPositionLocalBind =
            ObjectCalls.getMethodBind("CanvasItem", "make_canvas_position_local", MAKE_CANVAS_POSITION_LOCAL_HASH)

        private const val MAKE_INPUT_LOCAL_HASH = 811130057L
        @JvmField
        val makeInputLocalBind =
            ObjectCalls.getMethodBind("CanvasItem", "make_input_local", MAKE_INPUT_LOCAL_HASH)

        private const val SET_VISIBILITY_LAYER_HASH = 1286410249L
        @JvmField
        val setVisibilityLayerBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_visibility_layer", SET_VISIBILITY_LAYER_HASH)

        private const val GET_VISIBILITY_LAYER_HASH = 3905245786L
        @JvmField
        val getVisibilityLayerBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_visibility_layer", GET_VISIBILITY_LAYER_HASH)

        private const val SET_VISIBILITY_LAYER_BIT_HASH = 300928843L
        @JvmField
        val setVisibilityLayerBitBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_visibility_layer_bit", SET_VISIBILITY_LAYER_BIT_HASH)

        private const val GET_VISIBILITY_LAYER_BIT_HASH = 1116898809L
        @JvmField
        val getVisibilityLayerBitBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_visibility_layer_bit", GET_VISIBILITY_LAYER_BIT_HASH)

        private const val SET_TEXTURE_FILTER_HASH = 1037999706L
        @JvmField
        val setTextureFilterBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_texture_filter", SET_TEXTURE_FILTER_HASH)

        private const val GET_TEXTURE_FILTER_HASH = 121960042L
        @JvmField
        val getTextureFilterBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_texture_filter", GET_TEXTURE_FILTER_HASH)

        private const val SET_TEXTURE_REPEAT_HASH = 1716472974L
        @JvmField
        val setTextureRepeatBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)

        private const val GET_TEXTURE_REPEAT_HASH = 2667158319L
        @JvmField
        val getTextureRepeatBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)

        private const val SET_CLIP_CHILDREN_MODE_HASH = 1319393776L
        @JvmField
        val setClipChildrenModeBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_clip_children_mode", SET_CLIP_CHILDREN_MODE_HASH)

        private const val GET_CLIP_CHILDREN_MODE_HASH = 3581808349L
        @JvmField
        val getClipChildrenModeBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_clip_children_mode", GET_CLIP_CHILDREN_MODE_HASH)

        private const val SET_OVERSAMPLING_WITH_SCALE_HASH = 872218804L
        @JvmField
        val setOversamplingWithScaleBind =
            ObjectCalls.getMethodBind("CanvasItem", "set_oversampling_with_scale", SET_OVERSAMPLING_WITH_SCALE_HASH)

        private const val GET_OVERSAMPLING_WITH_SCALE_HASH = 2026097197L
        @JvmField
        val getOversamplingWithScaleBind =
            ObjectCalls.getMethodBind("CanvasItem", "get_oversampling_with_scale", GET_OVERSAMPLING_WITH_SCALE_HASH)
    }
}

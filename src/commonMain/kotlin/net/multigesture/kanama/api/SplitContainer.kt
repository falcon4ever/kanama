package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that arranges child controls horizontally or vertically and provides grabbers for
 * adjusting the split ratios between them.
 *
 * Generated from Godot docs: SplitContainer
 */
open class SplitContainer(handle: GodotHandle) : Container(handle) {
    var splitOffsets: List<Int>
        @JvmName("splitOffsetsProperty")
        get() = getSplitOffsets()
        @JvmName("setSplitOffsetsProperty")
        set(value) = setSplitOffsets(value)

    var collapsed: Boolean
        @JvmName("collapsedProperty")
        get() = isCollapsed()
        @JvmName("setCollapsedProperty")
        set(value) = setCollapsed(value)

    var draggingEnabled: Boolean
        @JvmName("draggingEnabledProperty")
        get() = isDraggingEnabled()
        @JvmName("setDraggingEnabledProperty")
        set(value) = setDraggingEnabled(value)

    var draggerVisibility: SplitContainer.DraggerVisibility
        @JvmName("draggerVisibilityProperty")
        get() = getDraggerVisibility()
        @JvmName("setDraggerVisibilityProperty")
        set(value) = setDraggerVisibility(value)

    var vertical: Boolean
        @JvmName("verticalProperty")
        get() = isVertical()
        @JvmName("setVerticalProperty")
        set(value) = setVertical(value)

    var touchDraggerEnabled: Boolean
        @JvmName("touchDraggerEnabledProperty")
        get() = isTouchDraggerEnabled()
        @JvmName("setTouchDraggerEnabledProperty")
        set(value) = setTouchDraggerEnabled(value)

    var dragNestedIntersections: Boolean
        @JvmName("dragNestedIntersectionsProperty")
        get() = isDraggingNestedIntersections()
        @JvmName("setDragNestedIntersectionsProperty")
        set(value) = setDragNestedIntersections(value)

    var dragAreaMarginBegin: Int
        @JvmName("dragAreaMarginBeginProperty")
        get() = getDragAreaMarginBegin()
        @JvmName("setDragAreaMarginBeginProperty")
        set(value) = setDragAreaMarginBegin(value)

    var dragAreaMarginEnd: Int
        @JvmName("dragAreaMarginEndProperty")
        get() = getDragAreaMarginEnd()
        @JvmName("setDragAreaMarginEndProperty")
        set(value) = setDragAreaMarginEnd(value)

    var dragAreaOffset: Int
        @JvmName("dragAreaOffsetProperty")
        get() = getDragAreaOffset()
        @JvmName("setDragAreaOffsetProperty")
        set(value) = setDragAreaOffset(value)

    var dragAreaHighlightInEditor: Boolean
        @JvmName("dragAreaHighlightInEditorProperty")
        get() = isDragAreaHighlightInEditorEnabled()
        @JvmName("setDragAreaHighlightInEditorProperty")
        set(value) = setDragAreaHighlightInEditor(value)

    var splitOffset: Int
        @JvmName("splitOffsetProperty")
        get() = getSplitOffset()
        @JvmName("setSplitOffsetProperty")
        set(value) = setSplitOffset(value)

    /**
     * Offsets for each dragger in pixels. Each one is the offset of the split between the `Control`
     * nodes before and after the dragger, with `0` being the default position. The default position is
     * based on the `Control` nodes expand flags and minimum sizes. See
     * `Control.size_flags_horizontal`, `Control.size_flags_vertical`, and
     * `Control.size_flags_stretch_ratio`. If none of the `Control` nodes before the dragger are
     * expanded, the default position will be at the start of the `SplitContainer`. If none of the
     * `Control` nodes after the dragger are expanded, the default position will be at the end of the
     * `SplitContainer`. If the dragger is in between expanded `Control` nodes, the default position
     * will be in the middle, based on the `Control.size_flags_stretch_ratio`s and minimum sizes. Note:
     * If the split offsets cause `Control` nodes to overlap, the first split will take priority when
     * resolving the positions.
     *
     * Generated from Godot docs: SplitContainer.set_split_offsets
     */
    fun setSplitOffsets(offsets: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setSplitOffsetsBind, segment, offsets)
    }

    /**
     * Offsets for each dragger in pixels. Each one is the offset of the split between the `Control`
     * nodes before and after the dragger, with `0` being the default position. The default position is
     * based on the `Control` nodes expand flags and minimum sizes. See
     * `Control.size_flags_horizontal`, `Control.size_flags_vertical`, and
     * `Control.size_flags_stretch_ratio`. If none of the `Control` nodes before the dragger are
     * expanded, the default position will be at the start of the `SplitContainer`. If none of the
     * `Control` nodes after the dragger are expanded, the default position will be at the end of the
     * `SplitContainer`. If the dragger is in between expanded `Control` nodes, the default position
     * will be in the middle, based on the `Control.size_flags_stretch_ratio`s and minimum sizes. Note:
     * If the split offsets cause `Control` nodes to overlap, the first split will take priority when
     * resolving the positions.
     *
     * Generated from Godot docs: SplitContainer.get_split_offsets
     */
    fun getSplitOffsets(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getSplitOffsetsBind, segment)
    }

    /**
     * Clamps the `split_offsets` values to ensure they are within valid ranges and do not overlap with
     * each other. When overlaps occur, this method prioritizes one split offset (at index
     * `priority_index`) by clamping any overlapping split offsets to it.
     *
     * Generated from Godot docs: SplitContainer.clamp_split_offset
     */
    fun clampSplitOffset(priorityIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(Binds.clampSplitOffsetBind, segment, priorityIndex)
    }

    /**
     * If `true`, the draggers will be disabled and the children will be sized as if all
     * `split_offsets` were `0`.
     *
     * Generated from Godot docs: SplitContainer.set_collapsed
     */
    fun setCollapsed(collapsed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollapsedBind, segment, collapsed)
    }

    /**
     * If `true`, the draggers will be disabled and the children will be sized as if all
     * `split_offsets` were `0`.
     *
     * Generated from Godot docs: SplitContainer.is_collapsed
     */
    fun isCollapsed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollapsedBind, segment)
    }

    /**
     * Determines the dragger's visibility. This property does not determine whether dragging is
     * enabled or not. Use `dragging_enabled` for that.
     *
     * Generated from Godot docs: SplitContainer.set_dragger_visibility
     */
    fun setDraggerVisibility(mode: SplitContainer.DraggerVisibility) {
        ObjectCalls.ptrcallWithLongArg(Binds.setDraggerVisibilityBind, segment, mode.value)
    }

    /**
     * Determines the dragger's visibility. This property does not determine whether dragging is
     * enabled or not. Use `dragging_enabled` for that.
     *
     * Generated from Godot docs: SplitContainer.get_dragger_visibility
     */
    fun getDraggerVisibility(): SplitContainer.DraggerVisibility {
        return SplitContainer.DraggerVisibility(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDraggerVisibilityBind, segment))
    }

    /**
     * If `true`, the `SplitContainer` will arrange its children vertically, rather than horizontally.
     * Can't be changed when using `HSplitContainer` and `VSplitContainer`.
     *
     * Generated from Godot docs: SplitContainer.set_vertical
     */
    fun setVertical(vertical: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVerticalBind, segment, vertical)
    }

    /**
     * If `true`, the `SplitContainer` will arrange its children vertically, rather than horizontally.
     * Can't be changed when using `HSplitContainer` and `VSplitContainer`.
     *
     * Generated from Godot docs: SplitContainer.is_vertical
     */
    fun isVertical(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVerticalBind, segment)
    }

    /**
     * Enables or disables split dragging.
     *
     * Generated from Godot docs: SplitContainer.set_dragging_enabled
     */
    fun setDraggingEnabled(draggingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDraggingEnabledBind, segment, draggingEnabled)
    }

    /**
     * Enables or disables split dragging.
     *
     * Generated from Godot docs: SplitContainer.is_dragging_enabled
     */
    fun isDraggingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDraggingEnabledBind, segment)
    }

    /**
     * Reduces the size of the drag area and split bar `split_bar_background` at the beginning of the
     * container.
     *
     * Generated from Godot docs: SplitContainer.set_drag_area_margin_begin
     */
    fun setDragAreaMarginBegin(margin: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDragAreaMarginBeginBind, segment, margin)
    }

    /**
     * Reduces the size of the drag area and split bar `split_bar_background` at the beginning of the
     * container.
     *
     * Generated from Godot docs: SplitContainer.get_drag_area_margin_begin
     */
    fun getDragAreaMarginBegin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDragAreaMarginBeginBind, segment)
    }

    /**
     * Reduces the size of the drag area and split bar `split_bar_background` at the end of the
     * container.
     *
     * Generated from Godot docs: SplitContainer.set_drag_area_margin_end
     */
    fun setDragAreaMarginEnd(margin: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDragAreaMarginEndBind, segment, margin)
    }

    /**
     * Reduces the size of the drag area and split bar `split_bar_background` at the end of the
     * container.
     *
     * Generated from Godot docs: SplitContainer.get_drag_area_margin_end
     */
    fun getDragAreaMarginEnd(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDragAreaMarginEndBind, segment)
    }

    /**
     * Shifts the drag area in the axis of the container to prevent the drag area from overlapping the
     * `ScrollBar` or other selectable `Control` of a child node.
     *
     * Generated from Godot docs: SplitContainer.set_drag_area_offset
     */
    fun setDragAreaOffset(offset: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDragAreaOffsetBind, segment, offset)
    }

    /**
     * Shifts the drag area in the axis of the container to prevent the drag area from overlapping the
     * `ScrollBar` or other selectable `Control` of a child node.
     *
     * Generated from Godot docs: SplitContainer.get_drag_area_offset
     */
    fun getDragAreaOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDragAreaOffsetBind, segment)
    }

    /**
     * Highlights the drag area `Rect2` so you can see where it is during development. The drag area is
     * gold if `dragging_enabled` is `true`, and red if `false`.
     *
     * Generated from Godot docs: SplitContainer.set_drag_area_highlight_in_editor
     */
    fun setDragAreaHighlightInEditor(dragAreaHighlightInEditor: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDragAreaHighlightInEditorBind, segment, dragAreaHighlightInEditor)
    }

    /**
     * Highlights the drag area `Rect2` so you can see where it is during development. The drag area is
     * gold if `dragging_enabled` is `true`, and red if `false`.
     *
     * Generated from Godot docs: SplitContainer.is_drag_area_highlight_in_editor_enabled
     */
    fun isDragAreaHighlightInEditorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDragAreaHighlightInEditorEnabledBind, segment)
    }

    /**
     * Returns an `Array` of the drag area `Control`s. These are the interactable `Control` nodes
     * between each child. For example, this can be used to add a pre-configured button to a drag area
     * `Control` so that it rides along with the split bar. Try setting the `Button` anchors to
     * `center` prior to the `Node.reparent` call.
     *
     * Generated from Godot docs: SplitContainer.get_drag_area_controls
     */
    fun getDragAreaControls(): List<Control> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getDragAreaControlsBind, segment, Control::wrap)
    }

    /**
     * If `true`, a touch-friendly drag handle will be enabled for better usability on smaller screens.
     * Unlike the standard grabber, this drag handle overlaps the `SplitContainer`'s children and does
     * not affect their minimum separation. The standard grabber will no longer be drawn when this
     * option is enabled.
     *
     * Generated from Godot docs: SplitContainer.set_touch_dragger_enabled
     */
    fun setTouchDraggerEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTouchDraggerEnabledBind, segment, enabled)
    }

    /**
     * If `true`, a touch-friendly drag handle will be enabled for better usability on smaller screens.
     * Unlike the standard grabber, this drag handle overlaps the `SplitContainer`'s children and does
     * not affect their minimum separation. The standard grabber will no longer be drawn when this
     * option is enabled.
     *
     * Generated from Godot docs: SplitContainer.is_touch_dragger_enabled
     */
    fun isTouchDraggerEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTouchDraggerEnabledBind, segment)
    }

    /**
     * Adds extra draggers at the intersection of the draggers of two SplitContainers to allow dragging
     * both at once. This must be set to `true` for both SplitContainers, and one needs to be a
     * descendant of the other. They also must be orthogonal (their `vertical` are different) and the
     * descendant must be next to at least one of the ancestor's draggers (within
     * `minimum_grab_thickness`).
     *
     * Generated from Godot docs: SplitContainer.set_drag_nested_intersections
     */
    fun setDragNestedIntersections(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDragNestedIntersectionsBind, segment, enabled)
    }

    /**
     * Adds extra draggers at the intersection of the draggers of two SplitContainers to allow dragging
     * both at once. This must be set to `true` for both SplitContainers, and one needs to be a
     * descendant of the other. They also must be orthogonal (their `vertical` are different) and the
     * descendant must be next to at least one of the ancestor's draggers (within
     * `minimum_grab_thickness`).
     *
     * Generated from Godot docs: SplitContainer.is_dragging_nested_intersections
     */
    fun isDraggingNestedIntersections(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDraggingNestedIntersectionsBind, segment)
    }

    /**
     * Returns the drag area `Control`. For example, you can move a pre-configured button into the drag
     * area `Control` so that it rides along with the split bar. Try setting the `Button` anchors to
     * `center` prior to the `reparent()` call.
     *
     * Generated from Godot docs: SplitContainer.get_drag_area_control
     */
    fun getDragAreaControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getDragAreaControlBind, segment))
    }

    /**
     * The first element of `split_offsets`.
     *
     * Generated from Godot docs: SplitContainer.set_split_offset
     */
    fun setSplitOffset(offset: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSplitOffsetBind, segment, offset)
    }

    /**
     * The first element of `split_offsets`.
     *
     * Generated from Godot docs: SplitContainer.get_split_offset
     */
    fun getSplitOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSplitOffsetBind, segment)
    }

    /** Signal `dragged(offset: int)`; see [TypedSignal]. */
    val dragged: Signal1<Long>
        @JvmName("draggedTypedSignal")
        get() = Signal1(this, "dragged", SignalArgType.LONG)

    /** Signal `drag_started()`; see [TypedSignal]. */
    val dragStarted: Signal0
        @JvmName("dragStartedTypedSignal")
        get() = Signal0(this, "drag_started")

    /** Signal `drag_ended()`; see [TypedSignal]. */
    val dragEnded: Signal0
        @JvmName("dragEndedTypedSignal")
        get() = Signal0(this, "drag_ended")

    object Signals {
        const val dragged: String = "dragged"
        const val dragStarted: String = "drag_started"
        const val dragEnded: String = "drag_ended"
    }

    /**
     * Godot's `SplitContainer.DraggerVisibility` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`SplitContainer.DraggerVisibility.<NAME>`).
     *
     * Generated from Godot docs: SplitContainer.DraggerVisibility
     */
    @JvmInline
    value class DraggerVisibility(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The split dragger icon is always visible when `autohide` is `false`, otherwise visible only when
             * the cursor hovers it. The size of the grabber icon determines the minimum `separation`. The
             * dragger icon is automatically hidden if the length of the grabber icon is longer than the split
             * bar.
             *
             * Generated from Godot docs: SplitContainer.DRAGGER_VISIBLE
             */
            val VISIBLE: DraggerVisibility get() = DraggerVisibility(0L)
            /**
             * The split dragger icon is never visible regardless of the value of `autohide`. The size of the
             * grabber icon determines the minimum `separation`.
             *
             * Generated from Godot docs: SplitContainer.DRAGGER_HIDDEN
             */
            val HIDDEN: DraggerVisibility get() = DraggerVisibility(1L)
            /**
             * The split dragger icon is not visible, and the split bar is collapsed to zero thickness.
             *
             * Generated from Godot docs: SplitContainer.DRAGGER_HIDDEN_COLLAPSED
             */
            val HIDDEN_COLLAPSED: DraggerVisibility get() = DraggerVisibility(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SplitContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SplitContainer? =
            if (handle.address() == 0L) null else SplitContainer(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SPLIT_OFFSETS_HASH = 3614634198L
        @JvmField
        val setSplitOffsetsBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_split_offsets", SET_SPLIT_OFFSETS_HASH)

        private const val GET_SPLIT_OFFSETS_HASH = 1930428628L
        @JvmField
        val getSplitOffsetsBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_split_offsets", GET_SPLIT_OFFSETS_HASH)

        private const val CLAMP_SPLIT_OFFSET_HASH = 1995695955L
        @JvmField
        val clampSplitOffsetBind =
            ObjectCalls.getMethodBind("SplitContainer", "clamp_split_offset", CLAMP_SPLIT_OFFSET_HASH)

        private const val SET_COLLAPSED_HASH = 2586408642L
        @JvmField
        val setCollapsedBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_collapsed", SET_COLLAPSED_HASH)

        private const val IS_COLLAPSED_HASH = 36873697L
        @JvmField
        val isCollapsedBind =
            ObjectCalls.getMethodBind("SplitContainer", "is_collapsed", IS_COLLAPSED_HASH)

        private const val SET_DRAGGER_VISIBILITY_HASH = 1168273952L
        @JvmField
        val setDraggerVisibilityBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_dragger_visibility", SET_DRAGGER_VISIBILITY_HASH)

        private const val GET_DRAGGER_VISIBILITY_HASH = 967297479L
        @JvmField
        val getDraggerVisibilityBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_dragger_visibility", GET_DRAGGER_VISIBILITY_HASH)

        private const val SET_VERTICAL_HASH = 2586408642L
        @JvmField
        val setVerticalBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_vertical", SET_VERTICAL_HASH)

        private const val IS_VERTICAL_HASH = 36873697L
        @JvmField
        val isVerticalBind =
            ObjectCalls.getMethodBind("SplitContainer", "is_vertical", IS_VERTICAL_HASH)

        private const val SET_DRAGGING_ENABLED_HASH = 2586408642L
        @JvmField
        val setDraggingEnabledBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_dragging_enabled", SET_DRAGGING_ENABLED_HASH)

        private const val IS_DRAGGING_ENABLED_HASH = 36873697L
        @JvmField
        val isDraggingEnabledBind =
            ObjectCalls.getMethodBind("SplitContainer", "is_dragging_enabled", IS_DRAGGING_ENABLED_HASH)

        private const val SET_DRAG_AREA_MARGIN_BEGIN_HASH = 1286410249L
        @JvmField
        val setDragAreaMarginBeginBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_margin_begin", SET_DRAG_AREA_MARGIN_BEGIN_HASH)

        private const val GET_DRAG_AREA_MARGIN_BEGIN_HASH = 3905245786L
        @JvmField
        val getDragAreaMarginBeginBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_margin_begin", GET_DRAG_AREA_MARGIN_BEGIN_HASH)

        private const val SET_DRAG_AREA_MARGIN_END_HASH = 1286410249L
        @JvmField
        val setDragAreaMarginEndBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_margin_end", SET_DRAG_AREA_MARGIN_END_HASH)

        private const val GET_DRAG_AREA_MARGIN_END_HASH = 3905245786L
        @JvmField
        val getDragAreaMarginEndBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_margin_end", GET_DRAG_AREA_MARGIN_END_HASH)

        private const val SET_DRAG_AREA_OFFSET_HASH = 1286410249L
        @JvmField
        val setDragAreaOffsetBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_offset", SET_DRAG_AREA_OFFSET_HASH)

        private const val GET_DRAG_AREA_OFFSET_HASH = 3905245786L
        @JvmField
        val getDragAreaOffsetBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_offset", GET_DRAG_AREA_OFFSET_HASH)

        private const val SET_DRAG_AREA_HIGHLIGHT_IN_EDITOR_HASH = 2586408642L
        @JvmField
        val setDragAreaHighlightInEditorBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_highlight_in_editor", SET_DRAG_AREA_HIGHLIGHT_IN_EDITOR_HASH)

        private const val IS_DRAG_AREA_HIGHLIGHT_IN_EDITOR_ENABLED_HASH = 36873697L
        @JvmField
        val isDragAreaHighlightInEditorEnabledBind =
            ObjectCalls.getMethodBind("SplitContainer", "is_drag_area_highlight_in_editor_enabled", IS_DRAG_AREA_HIGHLIGHT_IN_EDITOR_ENABLED_HASH)

        private const val GET_DRAG_AREA_CONTROLS_HASH = 2915620761L
        @JvmField
        val getDragAreaControlsBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_controls", GET_DRAG_AREA_CONTROLS_HASH)

        private const val SET_TOUCH_DRAGGER_ENABLED_HASH = 2586408642L
        @JvmField
        val setTouchDraggerEnabledBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_touch_dragger_enabled", SET_TOUCH_DRAGGER_ENABLED_HASH)

        private const val IS_TOUCH_DRAGGER_ENABLED_HASH = 36873697L
        @JvmField
        val isTouchDraggerEnabledBind =
            ObjectCalls.getMethodBind("SplitContainer", "is_touch_dragger_enabled", IS_TOUCH_DRAGGER_ENABLED_HASH)

        private const val SET_DRAG_NESTED_INTERSECTIONS_HASH = 2586408642L
        @JvmField
        val setDragNestedIntersectionsBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_nested_intersections", SET_DRAG_NESTED_INTERSECTIONS_HASH)

        private const val IS_DRAGGING_NESTED_INTERSECTIONS_HASH = 36873697L
        @JvmField
        val isDraggingNestedIntersectionsBind =
            ObjectCalls.getMethodBind("SplitContainer", "is_dragging_nested_intersections", IS_DRAGGING_NESTED_INTERSECTIONS_HASH)

        private const val GET_DRAG_AREA_CONTROL_HASH = 829782337L
        @JvmField
        val getDragAreaControlBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_control", GET_DRAG_AREA_CONTROL_HASH)

        private const val SET_SPLIT_OFFSET_HASH = 1286410249L
        @JvmField
        val setSplitOffsetBind =
            ObjectCalls.getMethodBind("SplitContainer", "set_split_offset", SET_SPLIT_OFFSET_HASH)

        private const val GET_SPLIT_OFFSET_HASH = 3905245786L
        @JvmField
        val getSplitOffsetBind =
            ObjectCalls.getMethodBind("SplitContainer", "get_split_offset", GET_SPLIT_OFFSET_HASH)
    }
}

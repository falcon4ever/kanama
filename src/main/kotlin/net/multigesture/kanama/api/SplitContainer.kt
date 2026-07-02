package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that arranges child controls horizontally or vertically and provides grabbers for
 * adjusting the split ratios between them.
 *
 * Generated from Godot docs: SplitContainer
 */
open class SplitContainer(handle: MemorySegment) : Container(handle) {
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

    var draggerVisibility: Long
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

    fun setSplitOffsets(offsets: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setSplitOffsetsBind, handle, offsets)
    }

    fun getSplitOffsets(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getSplitOffsetsBind, handle)
    }

    fun clampSplitOffset(priorityIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(clampSplitOffsetBind, handle, priorityIndex)
    }

    fun setCollapsed(collapsed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollapsedBind, handle, collapsed)
    }

    fun isCollapsed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollapsedBind, handle)
    }

    fun setDraggerVisibility(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDraggerVisibilityBind, handle, mode)
    }

    fun getDraggerVisibility(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDraggerVisibilityBind, handle)
    }

    fun setVertical(vertical: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVerticalBind, handle, vertical)
    }

    fun isVertical(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVerticalBind, handle)
    }

    fun setDraggingEnabled(draggingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDraggingEnabledBind, handle, draggingEnabled)
    }

    fun isDraggingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDraggingEnabledBind, handle)
    }

    fun setDragAreaMarginBegin(margin: Int) {
        ObjectCalls.ptrcallWithIntArg(setDragAreaMarginBeginBind, handle, margin)
    }

    fun getDragAreaMarginBegin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDragAreaMarginBeginBind, handle)
    }

    fun setDragAreaMarginEnd(margin: Int) {
        ObjectCalls.ptrcallWithIntArg(setDragAreaMarginEndBind, handle, margin)
    }

    fun getDragAreaMarginEnd(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDragAreaMarginEndBind, handle)
    }

    fun setDragAreaOffset(offset: Int) {
        ObjectCalls.ptrcallWithIntArg(setDragAreaOffsetBind, handle, offset)
    }

    fun getDragAreaOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDragAreaOffsetBind, handle)
    }

    fun setDragAreaHighlightInEditor(dragAreaHighlightInEditor: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragAreaHighlightInEditorBind, handle, dragAreaHighlightInEditor)
    }

    fun isDragAreaHighlightInEditorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragAreaHighlightInEditorEnabledBind, handle)
    }

    fun getDragAreaControls(): List<Control> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getDragAreaControlsBind, handle, Control::fromHandle)
    }

    fun setTouchDraggerEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTouchDraggerEnabledBind, handle, enabled)
    }

    fun isTouchDraggerEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTouchDraggerEnabledBind, handle)
    }

    fun setDragNestedIntersections(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragNestedIntersectionsBind, handle, enabled)
    }

    fun isDraggingNestedIntersections(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDraggingNestedIntersectionsBind, handle)
    }

    fun getDragAreaControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDragAreaControlBind, handle))
    }

    fun setSplitOffset(offset: Int) {
        ObjectCalls.ptrcallWithIntArg(setSplitOffsetBind, handle, offset)
    }

    fun getSplitOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSplitOffsetBind, handle)
    }

    object Signals {
        const val dragged: String = "dragged"
        const val dragStarted: String = "drag_started"
        const val dragEnded: String = "drag_ended"
    }

    companion object {
        const val DRAGGER_VISIBLE: Long = 0L
        const val DRAGGER_HIDDEN: Long = 1L
        const val DRAGGER_HIDDEN_COLLAPSED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SplitContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SplitContainer? =
            if (handle.address() == 0L) null else SplitContainer(handle)

        private const val SET_SPLIT_OFFSETS_HASH = 3614634198L
        private val setSplitOffsetsBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_split_offsets", SET_SPLIT_OFFSETS_HASH)
        }

        private const val GET_SPLIT_OFFSETS_HASH = 1930428628L
        private val getSplitOffsetsBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_split_offsets", GET_SPLIT_OFFSETS_HASH)
        }

        private const val CLAMP_SPLIT_OFFSET_HASH = 1995695955L
        private val clampSplitOffsetBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "clamp_split_offset", CLAMP_SPLIT_OFFSET_HASH)
        }

        private const val SET_COLLAPSED_HASH = 2586408642L
        private val setCollapsedBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_collapsed", SET_COLLAPSED_HASH)
        }

        private const val IS_COLLAPSED_HASH = 36873697L
        private val isCollapsedBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "is_collapsed", IS_COLLAPSED_HASH)
        }

        private const val SET_DRAGGER_VISIBILITY_HASH = 1168273952L
        private val setDraggerVisibilityBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_dragger_visibility", SET_DRAGGER_VISIBILITY_HASH)
        }

        private const val GET_DRAGGER_VISIBILITY_HASH = 967297479L
        private val getDraggerVisibilityBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_dragger_visibility", GET_DRAGGER_VISIBILITY_HASH)
        }

        private const val SET_VERTICAL_HASH = 2586408642L
        private val setVerticalBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_vertical", SET_VERTICAL_HASH)
        }

        private const val IS_VERTICAL_HASH = 36873697L
        private val isVerticalBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "is_vertical", IS_VERTICAL_HASH)
        }

        private const val SET_DRAGGING_ENABLED_HASH = 2586408642L
        private val setDraggingEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_dragging_enabled", SET_DRAGGING_ENABLED_HASH)
        }

        private const val IS_DRAGGING_ENABLED_HASH = 36873697L
        private val isDraggingEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "is_dragging_enabled", IS_DRAGGING_ENABLED_HASH)
        }

        private const val SET_DRAG_AREA_MARGIN_BEGIN_HASH = 1286410249L
        private val setDragAreaMarginBeginBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_margin_begin", SET_DRAG_AREA_MARGIN_BEGIN_HASH)
        }

        private const val GET_DRAG_AREA_MARGIN_BEGIN_HASH = 3905245786L
        private val getDragAreaMarginBeginBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_margin_begin", GET_DRAG_AREA_MARGIN_BEGIN_HASH)
        }

        private const val SET_DRAG_AREA_MARGIN_END_HASH = 1286410249L
        private val setDragAreaMarginEndBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_margin_end", SET_DRAG_AREA_MARGIN_END_HASH)
        }

        private const val GET_DRAG_AREA_MARGIN_END_HASH = 3905245786L
        private val getDragAreaMarginEndBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_margin_end", GET_DRAG_AREA_MARGIN_END_HASH)
        }

        private const val SET_DRAG_AREA_OFFSET_HASH = 1286410249L
        private val setDragAreaOffsetBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_offset", SET_DRAG_AREA_OFFSET_HASH)
        }

        private const val GET_DRAG_AREA_OFFSET_HASH = 3905245786L
        private val getDragAreaOffsetBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_offset", GET_DRAG_AREA_OFFSET_HASH)
        }

        private const val SET_DRAG_AREA_HIGHLIGHT_IN_EDITOR_HASH = 2586408642L
        private val setDragAreaHighlightInEditorBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_area_highlight_in_editor", SET_DRAG_AREA_HIGHLIGHT_IN_EDITOR_HASH)
        }

        private const val IS_DRAG_AREA_HIGHLIGHT_IN_EDITOR_ENABLED_HASH = 36873697L
        private val isDragAreaHighlightInEditorEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "is_drag_area_highlight_in_editor_enabled", IS_DRAG_AREA_HIGHLIGHT_IN_EDITOR_ENABLED_HASH)
        }

        private const val GET_DRAG_AREA_CONTROLS_HASH = 2915620761L
        private val getDragAreaControlsBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_controls", GET_DRAG_AREA_CONTROLS_HASH)
        }

        private const val SET_TOUCH_DRAGGER_ENABLED_HASH = 2586408642L
        private val setTouchDraggerEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_touch_dragger_enabled", SET_TOUCH_DRAGGER_ENABLED_HASH)
        }

        private const val IS_TOUCH_DRAGGER_ENABLED_HASH = 36873697L
        private val isTouchDraggerEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "is_touch_dragger_enabled", IS_TOUCH_DRAGGER_ENABLED_HASH)
        }

        private const val SET_DRAG_NESTED_INTERSECTIONS_HASH = 2586408642L
        private val setDragNestedIntersectionsBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_drag_nested_intersections", SET_DRAG_NESTED_INTERSECTIONS_HASH)
        }

        private const val IS_DRAGGING_NESTED_INTERSECTIONS_HASH = 36873697L
        private val isDraggingNestedIntersectionsBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "is_dragging_nested_intersections", IS_DRAGGING_NESTED_INTERSECTIONS_HASH)
        }

        private const val GET_DRAG_AREA_CONTROL_HASH = 829782337L
        private val getDragAreaControlBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_drag_area_control", GET_DRAG_AREA_CONTROL_HASH)
        }

        private const val SET_SPLIT_OFFSET_HASH = 1286410249L
        private val setSplitOffsetBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "set_split_offset", SET_SPLIT_OFFSET_HASH)
        }

        private const val GET_SPLIT_OFFSET_HASH = 3905245786L
        private val getSplitOffsetBind by lazy {
            ObjectCalls.getMethodBind("SplitContainer", "get_split_offset", GET_SPLIT_OFFSET_HASH)
        }
    }
}

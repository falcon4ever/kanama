package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A container that represents a basic element that can be placed inside a `GraphEdit` control.
 *
 * Generated from Godot docs: GraphElement
 */
open class GraphElement(handle: MemorySegment) : Container(handle) {
    var positionOffset: Vector2
        @JvmName("positionOffsetProperty")
        get() = getPositionOffset()
        @JvmName("setPositionOffsetProperty")
        set(value) = setPositionOffset(value)

    var resizable: Boolean
        @JvmName("resizableProperty")
        get() = isResizable()
        @JvmName("setResizableProperty")
        set(value) = setResizable(value)

    var draggable: Boolean
        @JvmName("draggableProperty")
        get() = isDraggable()
        @JvmName("setDraggableProperty")
        set(value) = setDraggable(value)

    var selectable: Boolean
        @JvmName("selectableProperty")
        get() = isSelectable()
        @JvmName("setSelectableProperty")
        set(value) = setSelectable(value)

    var selected: Boolean
        @JvmName("selectedProperty")
        get() = isSelected()
        @JvmName("setSelectedProperty")
        set(value) = setSelected(value)

    var scalingMenus: Boolean
        @JvmName("scalingMenusProperty")
        get() = isScalingMenus()
        @JvmName("setScalingMenusProperty")
        set(value) = setScalingMenus(value)

    /**
     * If `true`, the user can resize the GraphElement. Note: Dragging the handle will only emit the
     * `resize_request` and `resize_end` signals, the GraphElement needs to be resized manually.
     *
     * Generated from Godot docs: GraphElement.set_resizable
     */
    fun setResizable(resizable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setResizableBind, handle, resizable)
    }

    /**
     * If `true`, the user can resize the GraphElement. Note: Dragging the handle will only emit the
     * `resize_request` and `resize_end` signals, the GraphElement needs to be resized manually.
     *
     * Generated from Godot docs: GraphElement.is_resizable
     */
    fun isResizable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isResizableBind, handle)
    }

    /**
     * If `true`, the user can drag the GraphElement.
     *
     * Generated from Godot docs: GraphElement.set_draggable
     */
    fun setDraggable(draggable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDraggableBind, handle, draggable)
    }

    /**
     * If `true`, the user can drag the GraphElement.
     *
     * Generated from Godot docs: GraphElement.is_draggable
     */
    fun isDraggable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDraggableBind, handle)
    }

    /**
     * If `true`, the user can select the GraphElement.
     *
     * Generated from Godot docs: GraphElement.set_selectable
     */
    fun setSelectable(selectable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectableBind, handle, selectable)
    }

    /**
     * If `true`, the user can select the GraphElement.
     *
     * Generated from Godot docs: GraphElement.is_selectable
     */
    fun isSelectable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectableBind, handle)
    }

    /**
     * If `true`, the GraphElement is selected.
     *
     * Generated from Godot docs: GraphElement.set_selected
     */
    fun setSelected(selected: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectedBind, handle, selected)
    }

    /**
     * If `true`, the GraphElement is selected.
     *
     * Generated from Godot docs: GraphElement.is_selected
     */
    fun isSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectedBind, handle)
    }

    /**
     * If `true`, `PopupMenu`s that are descendants of the GraphElement are scaled with the `GraphEdit`
     * zoom.
     *
     * Generated from Godot docs: GraphElement.set_scaling_menus
     */
    fun setScalingMenus(scalingMenus: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScalingMenusBind, handle, scalingMenus)
    }

    /**
     * If `true`, `PopupMenu`s that are descendants of the GraphElement are scaled with the `GraphEdit`
     * zoom.
     *
     * Generated from Godot docs: GraphElement.is_scaling_menus
     */
    fun isScalingMenus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScalingMenusBind, handle)
    }

    /**
     * The offset of the GraphElement, relative to the scroll offset of the `GraphEdit`.
     *
     * Generated from Godot docs: GraphElement.set_position_offset
     */
    fun setPositionOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPositionOffsetBind, handle, offset)
    }

    /**
     * The offset of the GraphElement, relative to the scroll offset of the `GraphEdit`.
     *
     * Generated from Godot docs: GraphElement.get_position_offset
     */
    fun getPositionOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionOffsetBind, handle)
    }

    object Signals {
        const val nodeSelected: String = "node_selected"
        const val nodeDeselected: String = "node_deselected"
        const val raiseRequest: String = "raise_request"
        const val deleteRequest: String = "delete_request"
        const val resizeRequest: String = "resize_request"
        const val resizeEnd: String = "resize_end"
        const val dragged: String = "dragged"
        const val positionOffsetChanged: String = "position_offset_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GraphElement? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GraphElement? =
            if (handle.address() == 0L) null else GraphElement(handle)

        private const val SET_RESIZABLE_HASH = 2586408642L
        private val setResizableBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "set_resizable", SET_RESIZABLE_HASH)
        }

        private const val IS_RESIZABLE_HASH = 36873697L
        private val isResizableBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "is_resizable", IS_RESIZABLE_HASH)
        }

        private const val SET_DRAGGABLE_HASH = 2586408642L
        private val setDraggableBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "set_draggable", SET_DRAGGABLE_HASH)
        }

        private const val IS_DRAGGABLE_HASH = 2240911060L
        private val isDraggableBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "is_draggable", IS_DRAGGABLE_HASH)
        }

        private const val SET_SELECTABLE_HASH = 2586408642L
        private val setSelectableBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "set_selectable", SET_SELECTABLE_HASH)
        }

        private const val IS_SELECTABLE_HASH = 2240911060L
        private val isSelectableBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "is_selectable", IS_SELECTABLE_HASH)
        }

        private const val SET_SELECTED_HASH = 2586408642L
        private val setSelectedBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "set_selected", SET_SELECTED_HASH)
        }

        private const val IS_SELECTED_HASH = 2240911060L
        private val isSelectedBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "is_selected", IS_SELECTED_HASH)
        }

        private const val SET_SCALING_MENUS_HASH = 2586408642L
        private val setScalingMenusBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "set_scaling_menus", SET_SCALING_MENUS_HASH)
        }

        private const val IS_SCALING_MENUS_HASH = 36873697L
        private val isScalingMenusBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "is_scaling_menus", IS_SCALING_MENUS_HASH)
        }

        private const val SET_POSITION_OFFSET_HASH = 743155724L
        private val setPositionOffsetBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "set_position_offset", SET_POSITION_OFFSET_HASH)
        }

        private const val GET_POSITION_OFFSET_HASH = 3341600327L
        private val getPositionOffsetBind by lazy {
            ObjectCalls.getMethodBind("GraphElement", "get_position_offset", GET_POSITION_OFFSET_HASH)
        }
    }
}

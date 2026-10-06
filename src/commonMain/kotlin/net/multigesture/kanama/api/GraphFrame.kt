package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * GraphFrame is a special `GraphElement` that can be used to organize other `GraphElement`s inside
 * a `GraphEdit`.
 *
 * Generated from Godot docs: GraphFrame
 */
class GraphFrame(handle: GodotHandle) : GraphElement(handle) {
    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var autoshrinkEnabled: Boolean
        @JvmName("autoshrinkEnabledProperty")
        get() = isAutoshrinkEnabled()
        @JvmName("setAutoshrinkEnabledProperty")
        set(value) = setAutoshrinkEnabled(value)

    var autoshrinkMargin: Int
        @JvmName("autoshrinkMarginProperty")
        get() = getAutoshrinkMargin()
        @JvmName("setAutoshrinkMarginProperty")
        set(value) = setAutoshrinkMargin(value)

    var dragMargin: Int
        @JvmName("dragMarginProperty")
        get() = getDragMargin()
        @JvmName("setDragMarginProperty")
        set(value) = setDragMargin(value)

    var tintColorEnabled: Boolean
        @JvmName("tintColorEnabledProperty")
        get() = isTintColorEnabled()
        @JvmName("setTintColorEnabledProperty")
        set(value) = setTintColorEnabled(value)

    var tintColor: Color
        @JvmName("tintColorProperty")
        get() = getTintColor()
        @JvmName("setTintColorProperty")
        set(value) = setTintColor(value)

    /**
     * Title of the frame.
     *
     * Generated from Godot docs: GraphFrame.set_title
     */
    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTitleBind, segment, title)
    }

    /**
     * Title of the frame.
     *
     * Generated from Godot docs: GraphFrame.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTitleBind, segment)
    }

    /**
     * Returns the `HBoxContainer` used for the title bar, only containing a `Label` for displaying the
     * title by default. This can be used to add custom controls to the title bar such as option or
     * close buttons.
     *
     * Generated from Godot docs: GraphFrame.get_titlebar_hbox
     */
    fun getTitlebarHbox(): HBoxContainer? {
        return HBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTitlebarHboxBind, segment))
    }

    /**
     * If `true`, the frame's rect will be adjusted automatically to enclose all attached
     * `GraphElement`s.
     *
     * Generated from Godot docs: GraphFrame.set_autoshrink_enabled
     */
    fun setAutoshrinkEnabled(shrink: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoshrinkEnabledBind, segment, shrink)
    }

    /**
     * If `true`, the frame's rect will be adjusted automatically to enclose all attached
     * `GraphElement`s.
     *
     * Generated from Godot docs: GraphFrame.is_autoshrink_enabled
     */
    fun isAutoshrinkEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutoshrinkEnabledBind, segment)
    }

    /**
     * The margin around the attached nodes that is used to calculate the size of the frame when
     * `autoshrink_enabled` is `true`.
     *
     * Generated from Godot docs: GraphFrame.set_autoshrink_margin
     */
    fun setAutoshrinkMargin(autoshrinkMargin: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setAutoshrinkMarginBind, segment, autoshrinkMargin)
    }

    /**
     * The margin around the attached nodes that is used to calculate the size of the frame when
     * `autoshrink_enabled` is `true`.
     *
     * Generated from Godot docs: GraphFrame.get_autoshrink_margin
     */
    fun getAutoshrinkMargin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getAutoshrinkMarginBind, segment)
    }

    /**
     * The margin inside the frame that can be used to drag the frame.
     *
     * Generated from Godot docs: GraphFrame.set_drag_margin
     */
    fun setDragMargin(dragMargin: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDragMarginBind, segment, dragMargin)
    }

    /**
     * The margin inside the frame that can be used to drag the frame.
     *
     * Generated from Godot docs: GraphFrame.get_drag_margin
     */
    fun getDragMargin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDragMarginBind, segment)
    }

    /**
     * If `true`, the tint color will be used to tint the frame.
     *
     * Generated from Godot docs: GraphFrame.set_tint_color_enabled
     */
    fun setTintColorEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTintColorEnabledBind, segment, enable)
    }

    /**
     * If `true`, the tint color will be used to tint the frame.
     *
     * Generated from Godot docs: GraphFrame.is_tint_color_enabled
     */
    fun isTintColorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTintColorEnabledBind, segment)
    }

    /**
     * The color of the frame when `tint_color_enabled` is `true`.
     *
     * Generated from Godot docs: GraphFrame.set_tint_color
     */
    fun setTintColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setTintColorBind, segment, color)
    }

    /**
     * The color of the frame when `tint_color_enabled` is `true`.
     *
     * Generated from Godot docs: GraphFrame.get_tint_color
     */
    fun getTintColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getTintColorBind, segment)
    }

    /** Signal `autoshrink_changed()`; see [TypedSignal]. */
    val autoshrinkChanged: Signal0
        @JvmName("autoshrinkChangedTypedSignal")
        get() = Signal0(this, "autoshrink_changed")

    object Signals {
        const val autoshrinkChanged: String = "autoshrink_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GraphFrame? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GraphFrame? =
            if (handle.address() == 0L) null else GraphFrame(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TITLE_HASH = 83702148L
        @JvmField
        val setTitleBind =
            ObjectCalls.getMethodBind("GraphFrame", "set_title", SET_TITLE_HASH)

        private const val GET_TITLE_HASH = 201670096L
        @JvmField
        val getTitleBind =
            ObjectCalls.getMethodBind("GraphFrame", "get_title", GET_TITLE_HASH)

        private const val GET_TITLEBAR_HBOX_HASH = 3590609951L
        @JvmField
        val getTitlebarHboxBind =
            ObjectCalls.getMethodBind("GraphFrame", "get_titlebar_hbox", GET_TITLEBAR_HBOX_HASH)

        private const val SET_AUTOSHRINK_ENABLED_HASH = 2586408642L
        @JvmField
        val setAutoshrinkEnabledBind =
            ObjectCalls.getMethodBind("GraphFrame", "set_autoshrink_enabled", SET_AUTOSHRINK_ENABLED_HASH)

        private const val IS_AUTOSHRINK_ENABLED_HASH = 36873697L
        @JvmField
        val isAutoshrinkEnabledBind =
            ObjectCalls.getMethodBind("GraphFrame", "is_autoshrink_enabled", IS_AUTOSHRINK_ENABLED_HASH)

        private const val SET_AUTOSHRINK_MARGIN_HASH = 1286410249L
        @JvmField
        val setAutoshrinkMarginBind =
            ObjectCalls.getMethodBind("GraphFrame", "set_autoshrink_margin", SET_AUTOSHRINK_MARGIN_HASH)

        private const val GET_AUTOSHRINK_MARGIN_HASH = 3905245786L
        @JvmField
        val getAutoshrinkMarginBind =
            ObjectCalls.getMethodBind("GraphFrame", "get_autoshrink_margin", GET_AUTOSHRINK_MARGIN_HASH)

        private const val SET_DRAG_MARGIN_HASH = 1286410249L
        @JvmField
        val setDragMarginBind =
            ObjectCalls.getMethodBind("GraphFrame", "set_drag_margin", SET_DRAG_MARGIN_HASH)

        private const val GET_DRAG_MARGIN_HASH = 3905245786L
        @JvmField
        val getDragMarginBind =
            ObjectCalls.getMethodBind("GraphFrame", "get_drag_margin", GET_DRAG_MARGIN_HASH)

        private const val SET_TINT_COLOR_ENABLED_HASH = 2586408642L
        @JvmField
        val setTintColorEnabledBind =
            ObjectCalls.getMethodBind("GraphFrame", "set_tint_color_enabled", SET_TINT_COLOR_ENABLED_HASH)

        private const val IS_TINT_COLOR_ENABLED_HASH = 36873697L
        @JvmField
        val isTintColorEnabledBind =
            ObjectCalls.getMethodBind("GraphFrame", "is_tint_color_enabled", IS_TINT_COLOR_ENABLED_HASH)

        private const val SET_TINT_COLOR_HASH = 2920490490L
        @JvmField
        val setTintColorBind =
            ObjectCalls.getMethodBind("GraphFrame", "set_tint_color", SET_TINT_COLOR_HASH)

        private const val GET_TINT_COLOR_HASH = 3444240500L
        @JvmField
        val getTintColorBind =
            ObjectCalls.getMethodBind("GraphFrame", "get_tint_color", GET_TINT_COLOR_HASH)
    }
}

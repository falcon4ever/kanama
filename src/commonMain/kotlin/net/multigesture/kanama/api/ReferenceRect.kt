package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * A rectangular box for designing UIs.
 *
 * Generated from Godot docs: ReferenceRect
 */
class ReferenceRect(handle: GodotHandle) : Control(handle) {
    var borderColor: Color
        @JvmName("borderColorProperty")
        get() = getBorderColor()
        @JvmName("setBorderColorProperty")
        set(value) = setBorderColor(value)

    var borderWidth: Double
        @JvmName("borderWidthProperty")
        get() = getBorderWidth()
        @JvmName("setBorderWidthProperty")
        set(value) = setBorderWidth(value)

    var editorOnly: Boolean
        @JvmName("editorOnlyProperty")
        get() = getEditorOnly()
        @JvmName("setEditorOnlyProperty")
        set(value) = setEditorOnly(value)

    /**
     * Sets the border color of the `ReferenceRect`.
     *
     * Generated from Godot docs: ReferenceRect.get_border_color
     */
    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getBorderColorBind, segment)
    }

    /**
     * Sets the border color of the `ReferenceRect`.
     *
     * Generated from Godot docs: ReferenceRect.set_border_color
     */
    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setBorderColorBind, segment, color)
    }

    /**
     * Sets the border width of the `ReferenceRect`. The border grows both inwards and outwards with
     * respect to the rectangle box.
     *
     * Generated from Godot docs: ReferenceRect.get_border_width
     */
    fun getBorderWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBorderWidthBind, segment)
    }

    /**
     * Sets the border width of the `ReferenceRect`. The border grows both inwards and outwards with
     * respect to the rectangle box.
     *
     * Generated from Godot docs: ReferenceRect.set_border_width
     */
    fun setBorderWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBorderWidthBind, segment, width)
    }

    /**
     * If `true`, the `ReferenceRect` will only be visible while in editor. Otherwise, `ReferenceRect`
     * will be visible in the running project.
     *
     * Generated from Godot docs: ReferenceRect.get_editor_only
     */
    fun getEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEditorOnlyBind, segment)
    }

    /**
     * If `true`, the `ReferenceRect` will only be visible while in editor. Otherwise, `ReferenceRect`
     * will be visible in the running project.
     *
     * Generated from Godot docs: ReferenceRect.set_editor_only
     */
    fun setEditorOnly(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditorOnlyBind, segment, enabled)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ReferenceRect? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ReferenceRect? =
            if (handle.address() == 0L) null else ReferenceRect(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_BORDER_COLOR_HASH = 3444240500L
        @JvmField
        val getBorderColorBind =
            ObjectCalls.getMethodBind("ReferenceRect", "get_border_color", GET_BORDER_COLOR_HASH)

        private const val SET_BORDER_COLOR_HASH = 2920490490L
        @JvmField
        val setBorderColorBind =
            ObjectCalls.getMethodBind("ReferenceRect", "set_border_color", SET_BORDER_COLOR_HASH)

        private const val GET_BORDER_WIDTH_HASH = 1740695150L
        @JvmField
        val getBorderWidthBind =
            ObjectCalls.getMethodBind("ReferenceRect", "get_border_width", GET_BORDER_WIDTH_HASH)

        private const val SET_BORDER_WIDTH_HASH = 373806689L
        @JvmField
        val setBorderWidthBind =
            ObjectCalls.getMethodBind("ReferenceRect", "set_border_width", SET_BORDER_WIDTH_HASH)

        private const val GET_EDITOR_ONLY_HASH = 36873697L
        @JvmField
        val getEditorOnlyBind =
            ObjectCalls.getMethodBind("ReferenceRect", "get_editor_only", GET_EDITOR_ONLY_HASH)

        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        @JvmField
        val setEditorOnlyBind =
            ObjectCalls.getMethodBind("ReferenceRect", "set_editor_only", SET_EDITOR_ONLY_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A rectangular box for designing UIs.
 *
 * Generated from Godot docs: ReferenceRect
 */
class ReferenceRect(handle: MemorySegment) : Control(handle) {
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

    fun getBorderColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBorderColorBind, handle)
    }

    fun setBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setBorderColorBind, handle, color)
    }

    fun getBorderWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBorderWidthBind, handle)
    }

    fun setBorderWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBorderWidthBind, handle, width)
    }

    fun getEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEditorOnlyBind, handle)
    }

    fun setEditorOnly(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditorOnlyBind, handle, enabled)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ReferenceRect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ReferenceRect? =
            if (handle.address() == 0L) null else ReferenceRect(handle)

        private const val GET_BORDER_COLOR_HASH = 3444240500L
        private val getBorderColorBind by lazy {
            ObjectCalls.getMethodBind("ReferenceRect", "get_border_color", GET_BORDER_COLOR_HASH)
        }

        private const val SET_BORDER_COLOR_HASH = 2920490490L
        private val setBorderColorBind by lazy {
            ObjectCalls.getMethodBind("ReferenceRect", "set_border_color", SET_BORDER_COLOR_HASH)
        }

        private const val GET_BORDER_WIDTH_HASH = 1740695150L
        private val getBorderWidthBind by lazy {
            ObjectCalls.getMethodBind("ReferenceRect", "get_border_width", GET_BORDER_WIDTH_HASH)
        }

        private const val SET_BORDER_WIDTH_HASH = 373806689L
        private val setBorderWidthBind by lazy {
            ObjectCalls.getMethodBind("ReferenceRect", "set_border_width", SET_BORDER_WIDTH_HASH)
        }

        private const val GET_EDITOR_ONLY_HASH = 36873697L
        private val getEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("ReferenceRect", "get_editor_only", GET_EDITOR_ONLY_HASH)
        }

        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        private val setEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("ReferenceRect", "set_editor_only", SET_EDITOR_ONLY_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that arranges its child controls horizontally or vertically and wraps them around at
 * the borders.
 *
 * Generated from Godot docs: FlowContainer
 */
open class FlowContainer(handle: MemorySegment) : Container(handle) {
    var alignment: Long
        @JvmName("alignmentProperty")
        get() = getAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setAlignment(value)

    var lastWrapAlignment: Long
        @JvmName("lastWrapAlignmentProperty")
        get() = getLastWrapAlignment()
        @JvmName("setLastWrapAlignmentProperty")
        set(value) = setLastWrapAlignment(value)

    var vertical: Boolean
        @JvmName("verticalProperty")
        get() = isVertical()
        @JvmName("setVerticalProperty")
        set(value) = setVertical(value)

    var reverseFill: Boolean
        @JvmName("reverseFillProperty")
        get() = isReverseFill()
        @JvmName("setReverseFillProperty")
        set(value) = setReverseFill(value)

    /**
     * Returns the current line count.
     *
     * Generated from Godot docs: FlowContainer.get_line_count
     */
    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    /**
     * The alignment of the container's children (must be one of `ALIGNMENT_BEGIN`, `ALIGNMENT_CENTER`,
     * or `ALIGNMENT_END`).
     *
     * Generated from Godot docs: FlowContainer.set_alignment
     */
    fun setAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentBind, handle, alignment)
    }

    /**
     * The alignment of the container's children (must be one of `ALIGNMENT_BEGIN`, `ALIGNMENT_CENTER`,
     * or `ALIGNMENT_END`).
     *
     * Generated from Godot docs: FlowContainer.get_alignment
     */
    fun getAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlignmentBind, handle)
    }

    /**
     * The wrap behavior of the last, partially filled row or column (must be one of
     * `LAST_WRAP_ALIGNMENT_INHERIT`, `LAST_WRAP_ALIGNMENT_BEGIN`, `LAST_WRAP_ALIGNMENT_CENTER`, or
     * `LAST_WRAP_ALIGNMENT_END`).
     *
     * Generated from Godot docs: FlowContainer.set_last_wrap_alignment
     */
    fun setLastWrapAlignment(lastWrapAlignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setLastWrapAlignmentBind, handle, lastWrapAlignment)
    }

    /**
     * The wrap behavior of the last, partially filled row or column (must be one of
     * `LAST_WRAP_ALIGNMENT_INHERIT`, `LAST_WRAP_ALIGNMENT_BEGIN`, `LAST_WRAP_ALIGNMENT_CENTER`, or
     * `LAST_WRAP_ALIGNMENT_END`).
     *
     * Generated from Godot docs: FlowContainer.get_last_wrap_alignment
     */
    fun getLastWrapAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLastWrapAlignmentBind, handle)
    }

    /**
     * If `true`, the `FlowContainer` will arrange its children vertically, rather than horizontally.
     * Can't be changed when using `HFlowContainer` and `VFlowContainer`.
     *
     * Generated from Godot docs: FlowContainer.set_vertical
     */
    fun setVertical(vertical: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVerticalBind, handle, vertical)
    }

    /**
     * If `true`, the `FlowContainer` will arrange its children vertically, rather than horizontally.
     * Can't be changed when using `HFlowContainer` and `VFlowContainer`.
     *
     * Generated from Godot docs: FlowContainer.is_vertical
     */
    fun isVertical(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVerticalBind, handle)
    }

    /**
     * If `true`, reverses fill direction. Horizontal `FlowContainer`s will fill rows bottom to top,
     * vertical `FlowContainer`s will fill columns right to left. When using a vertical `FlowContainer`
     * with a right to left `Control.layout_direction`, columns will fill left to right instead.
     *
     * Generated from Godot docs: FlowContainer.set_reverse_fill
     */
    fun setReverseFill(reverseFill: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setReverseFillBind, handle, reverseFill)
    }

    /**
     * If `true`, reverses fill direction. Horizontal `FlowContainer`s will fill rows bottom to top,
     * vertical `FlowContainer`s will fill columns right to left. When using a vertical `FlowContainer`
     * with a right to left `Control.layout_direction`, columns will fill left to right instead.
     *
     * Generated from Godot docs: FlowContainer.is_reverse_fill
     */
    fun isReverseFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReverseFillBind, handle)
    }

    companion object {
        const val ALIGNMENT_BEGIN: Long = 0L
        const val ALIGNMENT_CENTER: Long = 1L
        const val ALIGNMENT_END: Long = 2L
        const val LAST_WRAP_ALIGNMENT_INHERIT: Long = 0L
        const val LAST_WRAP_ALIGNMENT_BEGIN: Long = 1L
        const val LAST_WRAP_ALIGNMENT_CENTER: Long = 2L
        const val LAST_WRAP_ALIGNMENT_END: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): FlowContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FlowContainer? =
            if (handle.address() == 0L) null else FlowContainer(handle)

        private const val GET_LINE_COUNT_HASH = 3905245786L
        private val getLineCountBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "get_line_count", GET_LINE_COUNT_HASH)
        }

        private const val SET_ALIGNMENT_HASH = 575250951L
        private val setAlignmentBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "set_alignment", SET_ALIGNMENT_HASH)
        }

        private const val GET_ALIGNMENT_HASH = 3749743559L
        private val getAlignmentBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "get_alignment", GET_ALIGNMENT_HASH)
        }

        private const val SET_LAST_WRAP_ALIGNMENT_HASH = 2899697495L
        private val setLastWrapAlignmentBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "set_last_wrap_alignment", SET_LAST_WRAP_ALIGNMENT_HASH)
        }

        private const val GET_LAST_WRAP_ALIGNMENT_HASH = 3743456014L
        private val getLastWrapAlignmentBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "get_last_wrap_alignment", GET_LAST_WRAP_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_HASH = 2586408642L
        private val setVerticalBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "set_vertical", SET_VERTICAL_HASH)
        }

        private const val IS_VERTICAL_HASH = 36873697L
        private val isVerticalBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "is_vertical", IS_VERTICAL_HASH)
        }

        private const val SET_REVERSE_FILL_HASH = 2586408642L
        private val setReverseFillBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "set_reverse_fill", SET_REVERSE_FILL_HASH)
        }

        private const val IS_REVERSE_FILL_HASH = 36873697L
        private val isReverseFillBind by lazy {
            ObjectCalls.getMethodBind("FlowContainer", "is_reverse_fill", IS_REVERSE_FILL_HASH)
        }
    }
}

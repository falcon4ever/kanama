package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that arranges its child controls horizontally or vertically.
 *
 * Generated from Godot docs: BoxContainer
 */
open class BoxContainer(handle: MemorySegment) : Container(handle) {
    var alignment: Long
        @JvmName("alignmentProperty")
        get() = getAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setAlignment(value)

    var vertical: Boolean
        @JvmName("verticalProperty")
        get() = isVertical()
        @JvmName("setVerticalProperty")
        set(value) = setVertical(value)

    fun addSpacer(begin: Boolean): Control? {
        return Control.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(addSpacerBind, handle, begin))
    }

    fun setAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentBind, handle, alignment)
    }

    fun getAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlignmentBind, handle)
    }

    fun setVertical(vertical: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVerticalBind, handle, vertical)
    }

    fun isVertical(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVerticalBind, handle)
    }

    companion object {
        const val ALIGNMENT_BEGIN: Long = 0L
        const val ALIGNMENT_CENTER: Long = 1L
        const val ALIGNMENT_END: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): BoxContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoxContainer? =
            if (handle.address() == 0L) null else BoxContainer(handle)

        private const val ADD_SPACER_HASH = 1326660695L
        private val addSpacerBind by lazy {
            ObjectCalls.getMethodBind("BoxContainer", "add_spacer", ADD_SPACER_HASH)
        }

        private const val SET_ALIGNMENT_HASH = 2456745134L
        private val setAlignmentBind by lazy {
            ObjectCalls.getMethodBind("BoxContainer", "set_alignment", SET_ALIGNMENT_HASH)
        }

        private const val GET_ALIGNMENT_HASH = 1915476527L
        private val getAlignmentBind by lazy {
            ObjectCalls.getMethodBind("BoxContainer", "get_alignment", GET_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_HASH = 2586408642L
        private val setVerticalBind by lazy {
            ObjectCalls.getMethodBind("BoxContainer", "set_vertical", SET_VERTICAL_HASH)
        }

        private const val IS_VERTICAL_HASH = 36873697L
        private val isVerticalBind by lazy {
            ObjectCalls.getMethodBind("BoxContainer", "is_vertical", IS_VERTICAL_HASH)
        }
    }
}

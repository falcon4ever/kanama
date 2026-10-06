package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that arranges its child controls horizontally or vertically.
 *
 * Generated from Godot docs: BoxContainer
 */
open class BoxContainer(handle: GodotHandle) : Container(handle) {
    var alignment: BoxContainer.AlignmentMode
        @JvmName("alignmentProperty")
        get() = getAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setAlignment(value)

    var vertical: Boolean
        @JvmName("verticalProperty")
        get() = isVertical()
        @JvmName("setVerticalProperty")
        set(value) = setVertical(value)

    /**
     * Adds a `Control` node to the box as a spacer. If `begin` is `true`, it will insert the `Control`
     * node in front of all other children.
     *
     * Generated from Godot docs: BoxContainer.add_spacer
     */
    fun addSpacer(begin: Boolean): Control? {
        return Control.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.addSpacerBind, segment, begin))
    }

    /**
     * The alignment of the container's children (must be one of `AlignmentMode.BEGIN`,
     * `AlignmentMode.CENTER`, or `AlignmentMode.END`).
     *
     * Generated from Godot docs: BoxContainer.set_alignment
     */
    fun setAlignment(alignment: BoxContainer.AlignmentMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlignmentBind, segment, alignment.value)
    }

    /**
     * The alignment of the container's children (must be one of `AlignmentMode.BEGIN`,
     * `AlignmentMode.CENTER`, or `AlignmentMode.END`).
     *
     * Generated from Godot docs: BoxContainer.get_alignment
     */
    fun getAlignment(): BoxContainer.AlignmentMode {
        return BoxContainer.AlignmentMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlignmentBind, segment))
    }

    /**
     * If `true`, the `BoxContainer` will arrange its children vertically, rather than horizontally.
     * Can't be changed when using `HBoxContainer` and `VBoxContainer`.
     *
     * Generated from Godot docs: BoxContainer.set_vertical
     */
    fun setVertical(vertical: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVerticalBind, segment, vertical)
    }

    /**
     * If `true`, the `BoxContainer` will arrange its children vertically, rather than horizontally.
     * Can't be changed when using `HBoxContainer` and `VBoxContainer`.
     *
     * Generated from Godot docs: BoxContainer.is_vertical
     */
    fun isVertical(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVerticalBind, segment)
    }

    /**
     * Godot's `BoxContainer.AlignmentMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`BoxContainer.AlignmentMode.<NAME>`).
     *
     * Generated from Godot docs: BoxContainer.AlignmentMode
     */
    @JvmInline
    value class AlignmentMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The child controls will be arranged at the beginning of the container, i.e. top if orientation
             * is vertical, left if orientation is horizontal (right for RTL layout).
             *
             * Generated from Godot docs: BoxContainer.ALIGNMENT_BEGIN
             */
            val BEGIN: AlignmentMode get() = AlignmentMode(0L)
            /**
             * The child controls will be centered in the container.
             *
             * Generated from Godot docs: BoxContainer.ALIGNMENT_CENTER
             */
            val CENTER: AlignmentMode get() = AlignmentMode(1L)
            /**
             * The child controls will be arranged at the end of the container, i.e. bottom if orientation is
             * vertical, right if orientation is horizontal (left for RTL layout).
             *
             * Generated from Godot docs: BoxContainer.ALIGNMENT_END
             */
            val END: AlignmentMode get() = AlignmentMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoxContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BoxContainer? =
            if (handle.address() == 0L) null else BoxContainer(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_SPACER_HASH = 1326660695L
        @JvmField
        val addSpacerBind =
            ObjectCalls.getMethodBind("BoxContainer", "add_spacer", ADD_SPACER_HASH)

        private const val SET_ALIGNMENT_HASH = 2456745134L
        @JvmField
        val setAlignmentBind =
            ObjectCalls.getMethodBind("BoxContainer", "set_alignment", SET_ALIGNMENT_HASH)

        private const val GET_ALIGNMENT_HASH = 1915476527L
        @JvmField
        val getAlignmentBind =
            ObjectCalls.getMethodBind("BoxContainer", "get_alignment", GET_ALIGNMENT_HASH)

        private const val SET_VERTICAL_HASH = 2586408642L
        @JvmField
        val setVerticalBind =
            ObjectCalls.getMethodBind("BoxContainer", "set_vertical", SET_VERTICAL_HASH)

        private const val IS_VERTICAL_HASH = 36873697L
        @JvmField
        val isVerticalBind =
            ObjectCalls.getMethodBind("BoxContainer", "is_vertical", IS_VERTICAL_HASH)
    }
}

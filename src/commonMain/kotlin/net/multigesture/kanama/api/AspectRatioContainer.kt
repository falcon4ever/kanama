package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that preserves the proportions of its child controls.
 *
 * Generated from Godot docs: AspectRatioContainer
 */
class AspectRatioContainer(handle: GodotHandle) : Container(handle) {
    var ratio: Double
        @JvmName("ratioProperty")
        get() = getRatio()
        @JvmName("setRatioProperty")
        set(value) = setRatio(value)

    var stretchMode: AspectRatioContainer.StretchMode
        @JvmName("stretchModeProperty")
        get() = getStretchMode()
        @JvmName("setStretchModeProperty")
        set(value) = setStretchMode(value)

    var alignmentHorizontal: AspectRatioContainer.AlignmentMode
        @JvmName("alignmentHorizontalProperty")
        get() = getAlignmentHorizontal()
        @JvmName("setAlignmentHorizontalProperty")
        set(value) = setAlignmentHorizontal(value)

    var alignmentVertical: AspectRatioContainer.AlignmentMode
        @JvmName("alignmentVerticalProperty")
        get() = getAlignmentVertical()
        @JvmName("setAlignmentVerticalProperty")
        set(value) = setAlignmentVertical(value)

    /**
     * The aspect ratio to enforce on child controls. This is the width divided by the height. The
     * ratio depends on the `stretch_mode`.
     *
     * Generated from Godot docs: AspectRatioContainer.set_ratio
     */
    fun setRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRatioBind, segment, ratio)
    }

    /**
     * The aspect ratio to enforce on child controls. This is the width divided by the height. The
     * ratio depends on the `stretch_mode`.
     *
     * Generated from Godot docs: AspectRatioContainer.get_ratio
     */
    fun getRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRatioBind, segment)
    }

    /**
     * The stretch mode used to align child controls.
     *
     * Generated from Godot docs: AspectRatioContainer.set_stretch_mode
     */
    fun setStretchMode(stretchMode: AspectRatioContainer.StretchMode) {
        ObjectCalls.ptrcallWithLongArg(setStretchModeBind, segment, stretchMode.value)
    }

    /**
     * The stretch mode used to align child controls.
     *
     * Generated from Godot docs: AspectRatioContainer.get_stretch_mode
     */
    fun getStretchMode(): AspectRatioContainer.StretchMode {
        return AspectRatioContainer.StretchMode(ObjectCalls.ptrcallNoArgsRetLong(getStretchModeBind, segment))
    }

    /**
     * Specifies the horizontal relative position of child controls.
     *
     * Generated from Godot docs: AspectRatioContainer.set_alignment_horizontal
     */
    fun setAlignmentHorizontal(alignmentHorizontal: AspectRatioContainer.AlignmentMode) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentHorizontalBind, segment, alignmentHorizontal.value)
    }

    /**
     * Specifies the horizontal relative position of child controls.
     *
     * Generated from Godot docs: AspectRatioContainer.get_alignment_horizontal
     */
    fun getAlignmentHorizontal(): AspectRatioContainer.AlignmentMode {
        return AspectRatioContainer.AlignmentMode(ObjectCalls.ptrcallNoArgsRetLong(getAlignmentHorizontalBind, segment))
    }

    /**
     * Specifies the vertical relative position of child controls.
     *
     * Generated from Godot docs: AspectRatioContainer.set_alignment_vertical
     */
    fun setAlignmentVertical(alignmentVertical: AspectRatioContainer.AlignmentMode) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentVerticalBind, segment, alignmentVertical.value)
    }

    /**
     * Specifies the vertical relative position of child controls.
     *
     * Generated from Godot docs: AspectRatioContainer.get_alignment_vertical
     */
    fun getAlignmentVertical(): AspectRatioContainer.AlignmentMode {
        return AspectRatioContainer.AlignmentMode(ObjectCalls.ptrcallNoArgsRetLong(getAlignmentVerticalBind, segment))
    }

    @JvmInline
    value class StretchMode(val value: Long) {
        companion object {
            /**
             * The height of child controls is automatically adjusted based on the width of the container.
             *
             * Generated from Godot docs: AspectRatioContainer.STRETCH_WIDTH_CONTROLS_HEIGHT
             */
            val WIDTH_CONTROLS_HEIGHT: StretchMode get() = StretchMode(0L)
            /**
             * The width of child controls is automatically adjusted based on the height of the container.
             *
             * Generated from Godot docs: AspectRatioContainer.STRETCH_HEIGHT_CONTROLS_WIDTH
             */
            val HEIGHT_CONTROLS_WIDTH: StretchMode get() = StretchMode(1L)
            /**
             * The bounding rectangle of child controls is automatically adjusted to fit inside the container
             * while keeping the aspect ratio.
             *
             * Generated from Godot docs: AspectRatioContainer.STRETCH_FIT
             */
            val FIT: StretchMode get() = StretchMode(2L)
            /**
             * The width and height of child controls is automatically adjusted to make their bounding
             * rectangle cover the entire area of the container while keeping the aspect ratio. When the
             * bounding rectangle of child controls exceed the container's size and `Control.clip_contents` is
             * enabled, this allows to show only the container's area restricted by its own bounding rectangle.
             *
             * Generated from Godot docs: AspectRatioContainer.STRETCH_COVER
             */
            val COVER: StretchMode get() = StretchMode(3L)
        }
    }

    @JvmInline
    value class AlignmentMode(val value: Long) {
        companion object {
            /**
             * Aligns child controls with the beginning (left or top) of the container.
             *
             * Generated from Godot docs: AspectRatioContainer.ALIGNMENT_BEGIN
             */
            val BEGIN: AlignmentMode get() = AlignmentMode(0L)
            /**
             * Aligns child controls with the center of the container.
             *
             * Generated from Godot docs: AspectRatioContainer.ALIGNMENT_CENTER
             */
            val CENTER: AlignmentMode get() = AlignmentMode(1L)
            /**
             * Aligns child controls with the end (right or bottom) of the container.
             *
             * Generated from Godot docs: AspectRatioContainer.ALIGNMENT_END
             */
            val END: AlignmentMode get() = AlignmentMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AspectRatioContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AspectRatioContainer? =
            if (handle.address() == 0L) null else AspectRatioContainer(GodotHandle(handle))

        private const val SET_RATIO_HASH = 373806689L
        private val setRatioBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "set_ratio", SET_RATIO_HASH)
        }

        private const val GET_RATIO_HASH = 1740695150L
        private val getRatioBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "get_ratio", GET_RATIO_HASH)
        }

        private const val SET_STRETCH_MODE_HASH = 1876743467L
        private val setStretchModeBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "set_stretch_mode", SET_STRETCH_MODE_HASH)
        }

        private const val GET_STRETCH_MODE_HASH = 3416449033L
        private val getStretchModeBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "get_stretch_mode", GET_STRETCH_MODE_HASH)
        }

        private const val SET_ALIGNMENT_HORIZONTAL_HASH = 2147829016L
        private val setAlignmentHorizontalBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "set_alignment_horizontal", SET_ALIGNMENT_HORIZONTAL_HASH)
        }

        private const val GET_ALIGNMENT_HORIZONTAL_HASH = 3838875429L
        private val getAlignmentHorizontalBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "get_alignment_horizontal", GET_ALIGNMENT_HORIZONTAL_HASH)
        }

        private const val SET_ALIGNMENT_VERTICAL_HASH = 2147829016L
        private val setAlignmentVerticalBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "set_alignment_vertical", SET_ALIGNMENT_VERTICAL_HASH)
        }

        private const val GET_ALIGNMENT_VERTICAL_HASH = 3838875429L
        private val getAlignmentVerticalBind by lazy {
            ObjectCalls.getMethodBind("AspectRatioContainer", "get_alignment_vertical", GET_ALIGNMENT_VERTICAL_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that preserves the proportions of its child controls.
 *
 * Generated from Godot docs: AspectRatioContainer
 */
class AspectRatioContainer(handle: MemorySegment) : Container(handle) {
    var ratio: Double
        @JvmName("ratioProperty")
        get() = getRatio()
        @JvmName("setRatioProperty")
        set(value) = setRatio(value)

    var stretchMode: Long
        @JvmName("stretchModeProperty")
        get() = getStretchMode()
        @JvmName("setStretchModeProperty")
        set(value) = setStretchMode(value)

    var alignmentHorizontal: Long
        @JvmName("alignmentHorizontalProperty")
        get() = getAlignmentHorizontal()
        @JvmName("setAlignmentHorizontalProperty")
        set(value) = setAlignmentHorizontal(value)

    var alignmentVertical: Long
        @JvmName("alignmentVerticalProperty")
        get() = getAlignmentVertical()
        @JvmName("setAlignmentVerticalProperty")
        set(value) = setAlignmentVertical(value)

    fun setRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRatioBind, handle, ratio)
    }

    fun getRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRatioBind, handle)
    }

    fun setStretchMode(stretchMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setStretchModeBind, handle, stretchMode)
    }

    fun getStretchMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStretchModeBind, handle)
    }

    fun setAlignmentHorizontal(alignmentHorizontal: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentHorizontalBind, handle, alignmentHorizontal)
    }

    fun getAlignmentHorizontal(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlignmentHorizontalBind, handle)
    }

    fun setAlignmentVertical(alignmentVertical: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentVerticalBind, handle, alignmentVertical)
    }

    fun getAlignmentVertical(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlignmentVerticalBind, handle)
    }

    companion object {
        const val STRETCH_WIDTH_CONTROLS_HEIGHT: Long = 0L
        const val STRETCH_HEIGHT_CONTROLS_WIDTH: Long = 1L
        const val STRETCH_FIT: Long = 2L
        const val STRETCH_COVER: Long = 3L
        const val ALIGNMENT_BEGIN: Long = 0L
        const val ALIGNMENT_CENTER: Long = 1L
        const val ALIGNMENT_END: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AspectRatioContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AspectRatioContainer? =
            if (handle.address() == 0L) null else AspectRatioContainer(handle)

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

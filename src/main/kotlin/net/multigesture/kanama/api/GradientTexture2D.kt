package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 2D texture that creates a pattern with colors obtained from a `Gradient`.
 *
 * Generated from Godot docs: GradientTexture2D
 */
class GradientTexture2D(handle: MemorySegment) : Texture2D(handle) {
    var gradient: Gradient?
        @JvmName("gradientProperty")
        get() = getGradient()
        @JvmName("setGradientProperty")
        set(value) = setGradient(value)

    var useHdr: Boolean
        @JvmName("useHdrProperty")
        get() = isUsingHdr()
        @JvmName("setUseHdrProperty")
        set(value) = setUseHdr(value)

    var fill: Long
        @JvmName("fillProperty")
        get() = getFill()
        @JvmName("setFillProperty")
        set(value) = setFill(value)

    var fillFrom: Vector2
        @JvmName("fillFromProperty")
        get() = getFillFrom()
        @JvmName("setFillFromProperty")
        set(value) = setFillFrom(value)

    var fillTo: Vector2
        @JvmName("fillToProperty")
        get() = getFillTo()
        @JvmName("setFillToProperty")
        set(value) = setFillTo(value)

    var repeat: Long
        @JvmName("repeatProperty")
        get() = getRepeat()
        @JvmName("setRepeatProperty")
        set(value) = setRepeat(value)

    fun setGradient(gradient: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setGradientBind, handle, listOf(gradient?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getGradient(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGradientBind, handle))
    }

    fun setWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    fun setHeight(height: Int) {
        ObjectCalls.ptrcallWithIntArg(setHeightBind, handle, height)
    }

    fun setUseHdr(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseHdrBind, handle, enabled)
    }

    fun isUsingHdr(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingHdrBind, handle)
    }

    fun setFill(fill: Long) {
        ObjectCalls.ptrcallWithLongArg(setFillBind, handle, fill)
    }

    fun getFill(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFillBind, handle)
    }

    fun setFillFrom(fillFrom: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setFillFromBind, handle, fillFrom)
    }

    fun getFillFrom(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFillFromBind, handle)
    }

    fun setFillTo(fillTo: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setFillToBind, handle, fillTo)
    }

    fun getFillTo(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFillToBind, handle)
    }

    fun setRepeat(repeat: Long) {
        ObjectCalls.ptrcallWithLongArg(setRepeatBind, handle, repeat)
    }

    fun getRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRepeatBind, handle)
    }

    companion object {
        const val FILL_LINEAR: Long = 0L
        const val FILL_RADIAL: Long = 1L
        const val FILL_SQUARE: Long = 2L
        const val FILL_CONIC: Long = 3L
        const val REPEAT_NONE: Long = 0L
        const val REPEAT: Long = 1L
        const val REPEAT_MIRROR: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GradientTexture2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GradientTexture2D? =
            if (handle.address() == 0L) null else GradientTexture2D(handle)

        private const val SET_GRADIENT_HASH = 2756054477L
        private val setGradientBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_gradient", SET_GRADIENT_HASH)
        }

        private const val GET_GRADIENT_HASH = 132272999L
        private val getGradientBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "get_gradient", GET_GRADIENT_HASH)
        }

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_width", SET_WIDTH_HASH)
        }

        private const val SET_HEIGHT_HASH = 1286410249L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_height", SET_HEIGHT_HASH)
        }

        private const val SET_USE_HDR_HASH = 2586408642L
        private val setUseHdrBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_use_hdr", SET_USE_HDR_HASH)
        }

        private const val IS_USING_HDR_HASH = 36873697L
        private val isUsingHdrBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "is_using_hdr", IS_USING_HDR_HASH)
        }

        private const val SET_FILL_HASH = 3623927636L
        private val setFillBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_fill", SET_FILL_HASH)
        }

        private const val GET_FILL_HASH = 1876227217L
        private val getFillBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "get_fill", GET_FILL_HASH)
        }

        private const val SET_FILL_FROM_HASH = 743155724L
        private val setFillFromBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_fill_from", SET_FILL_FROM_HASH)
        }

        private const val GET_FILL_FROM_HASH = 3341600327L
        private val getFillFromBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "get_fill_from", GET_FILL_FROM_HASH)
        }

        private const val SET_FILL_TO_HASH = 743155724L
        private val setFillToBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_fill_to", SET_FILL_TO_HASH)
        }

        private const val GET_FILL_TO_HASH = 3341600327L
        private val getFillToBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "get_fill_to", GET_FILL_TO_HASH)
        }

        private const val SET_REPEAT_HASH = 1357597002L
        private val setRepeatBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "set_repeat", SET_REPEAT_HASH)
        }

        private const val GET_REPEAT_HASH = 3351758665L
        private val getRepeatBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture2D", "get_repeat", GET_REPEAT_HASH)
        }
    }
}

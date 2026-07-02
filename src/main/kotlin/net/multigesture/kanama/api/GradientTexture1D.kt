package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 1D texture that uses colors obtained from a `Gradient`.
 *
 * Generated from Godot docs: GradientTexture1D
 */
class GradientTexture1D(handle: MemorySegment) : Texture2D(handle) {
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

    fun setGradient(gradient: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setGradientBind, handle, listOf(gradient?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getGradient(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGradientBind, handle))
    }

    fun setWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    fun setUseHdr(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseHdrBind, handle, enabled)
    }

    fun isUsingHdr(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingHdrBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GradientTexture1D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GradientTexture1D? =
            if (handle.address() == 0L) null else GradientTexture1D(handle)

        private const val SET_GRADIENT_HASH = 2756054477L
        private val setGradientBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture1D", "set_gradient", SET_GRADIENT_HASH)
        }

        private const val GET_GRADIENT_HASH = 132272999L
        private val getGradientBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture1D", "get_gradient", GET_GRADIENT_HASH)
        }

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture1D", "set_width", SET_WIDTH_HASH)
        }

        private const val SET_USE_HDR_HASH = 2586408642L
        private val setUseHdrBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture1D", "set_use_hdr", SET_USE_HDR_HASH)
        }

        private const val IS_USING_HDR_HASH = 36873697L
        private val isUsingHdrBind by lazy {
            ObjectCalls.getMethodBind("GradientTexture1D", "is_using_hdr", IS_USING_HDR_HASH)
        }
    }
}

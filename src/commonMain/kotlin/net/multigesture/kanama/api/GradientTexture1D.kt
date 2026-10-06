package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 1D texture that uses colors obtained from a `Gradient`.
 *
 * Generated from Godot docs: GradientTexture1D
 */
class GradientTexture1D(handle: GodotHandle) : Texture2D(handle) {
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

    /**
     * The `Gradient` used to fill the texture.
     *
     * Generated from Godot docs: GradientTexture1D.set_gradient
     */
    fun setGradient(gradient: Gradient?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setGradientBind, segment, listOf(gradient?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Gradient` used to fill the texture.
     *
     * Generated from Godot docs: GradientTexture1D.get_gradient
     */
    fun getGradient(): Gradient? {
        checkOpen()
        return Gradient.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGradientBind, segment))
    }

    /**
     * The number of color samples that will be obtained from the `Gradient`.
     *
     * Generated from Godot docs: GradientTexture1D.set_width
     */
    fun setWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setWidthBind, segment, width)
    }

    /**
     * If `true`, the generated texture will support high dynamic range (`Image.Format.RGBAF` format).
     * This allows for glow effects to work if `Environment.glow_enabled` is `true`. If `false`, the
     * generated texture will use low dynamic range; overbright colors will be clamped
     * (`Image.Format.RGBA8` format).
     *
     * Generated from Godot docs: GradientTexture1D.set_use_hdr
     */
    fun setUseHdr(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseHdrBind, segment, enabled)
    }

    /**
     * If `true`, the generated texture will support high dynamic range (`Image.Format.RGBAF` format).
     * This allows for glow effects to work if `Environment.glow_enabled` is `true`. If `false`, the
     * generated texture will use low dynamic range; overbright colors will be clamped
     * (`Image.Format.RGBA8` format).
     *
     * Generated from Godot docs: GradientTexture1D.is_using_hdr
     */
    fun isUsingHdr(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingHdrBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GradientTexture1D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GradientTexture1D? =
            if (handle.address() == 0L) null else RefCounted.owned(GradientTexture1D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GradientTexture1D? =
            if (handle.address() == 0L) null else GradientTexture1D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_GRADIENT_HASH = 2756054477L
        @JvmField
        val setGradientBind =
            ObjectCalls.getMethodBind("GradientTexture1D", "set_gradient", SET_GRADIENT_HASH)

        private const val GET_GRADIENT_HASH = 132272999L
        @JvmField
        val getGradientBind =
            ObjectCalls.getMethodBind("GradientTexture1D", "get_gradient", GET_GRADIENT_HASH)

        private const val SET_WIDTH_HASH = 1286410249L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("GradientTexture1D", "set_width", SET_WIDTH_HASH)

        private const val SET_USE_HDR_HASH = 2586408642L
        @JvmField
        val setUseHdrBind =
            ObjectCalls.getMethodBind("GradientTexture1D", "set_use_hdr", SET_USE_HDR_HASH)

        private const val IS_USING_HDR_HASH = 36873697L
        @JvmField
        val isUsingHdrBind =
            ObjectCalls.getMethodBind("GradientTexture1D", "is_using_hdr", IS_USING_HDR_HASH)
    }
}

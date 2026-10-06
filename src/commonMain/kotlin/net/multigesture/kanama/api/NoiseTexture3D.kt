package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: NoiseTexture3D
 */
class NoiseTexture3D(handle: GodotHandle) : Texture3D(handle) {
    var noise: Noise?
        @JvmName("noiseProperty")
        get() = getNoise()
        @JvmName("setNoiseProperty")
        set(value) = setNoise(value)

    var colorRamp: Gradient?
        @JvmName("colorRampProperty")
        get() = getColorRamp()
        @JvmName("setColorRampProperty")
        set(value) = setColorRamp(value)

    var seamless: Boolean
        @JvmName("seamlessProperty")
        get() = getSeamless()
        @JvmName("setSeamlessProperty")
        set(value) = setSeamless(value)

    var invert: Boolean
        @JvmName("invertProperty")
        get() = getInvert()
        @JvmName("setInvertProperty")
        set(value) = setInvert(value)

    var normalize: Boolean
        @JvmName("normalizeProperty")
        get() = isNormalized()
        @JvmName("setNormalizeProperty")
        set(value) = setNormalize(value)

    var seamlessBlendSkirt: Double
        @JvmName("seamlessBlendSkirtProperty")
        get() = getSeamlessBlendSkirt()
        @JvmName("setSeamlessBlendSkirtProperty")
        set(value) = setSeamlessBlendSkirt(value)

    fun setWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setWidthBind, segment, width)
    }

    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setHeightBind, segment, height)
    }

    fun setDepth(depth: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDepthBind, segment, depth)
    }

    fun setNoise(noise: Noise?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setNoiseBind, segment, listOf(noise?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getNoise(): Noise? {
        checkOpen()
        return Noise.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getNoiseBind, segment))
    }

    fun setColorRamp(gradient: Gradient?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setColorRampBind, segment, listOf(gradient?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getColorRamp(): Gradient? {
        checkOpen()
        return Gradient.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColorRampBind, segment))
    }

    fun setSeamless(seamless: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setSeamlessBind, segment, seamless)
    }

    fun getSeamless(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSeamlessBind, segment)
    }

    fun setInvert(invert: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setInvertBind, segment, invert)
    }

    fun getInvert(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getInvertBind, segment)
    }

    fun setNormalize(normalize: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNormalizeBind, segment, normalize)
    }

    fun isNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNormalizedBind, segment)
    }

    fun setSeamlessBlendSkirt(seamlessBlendSkirt: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSeamlessBlendSkirtBind, segment, seamlessBlendSkirt)
    }

    fun getSeamlessBlendSkirt(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSeamlessBlendSkirtBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NoiseTexture3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): NoiseTexture3D? =
            if (handle.address() == 0L) null else RefCounted.owned(NoiseTexture3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): NoiseTexture3D? =
            if (handle.address() == 0L) null else NoiseTexture3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_WIDTH_HASH = 1286410249L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_width", SET_WIDTH_HASH)

        private const val SET_HEIGHT_HASH = 1286410249L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_height", SET_HEIGHT_HASH)

        private const val SET_DEPTH_HASH = 1286410249L
        @JvmField
        val setDepthBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_depth", SET_DEPTH_HASH)

        private const val SET_NOISE_HASH = 4135492439L
        @JvmField
        val setNoiseBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_noise", SET_NOISE_HASH)

        private const val GET_NOISE_HASH = 185851837L
        @JvmField
        val getNoiseBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_noise", GET_NOISE_HASH)

        private const val SET_COLOR_RAMP_HASH = 2756054477L
        @JvmField
        val setColorRampBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_color_ramp", SET_COLOR_RAMP_HASH)

        private const val GET_COLOR_RAMP_HASH = 132272999L
        @JvmField
        val getColorRampBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_color_ramp", GET_COLOR_RAMP_HASH)

        private const val SET_SEAMLESS_HASH = 2586408642L
        @JvmField
        val setSeamlessBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_seamless", SET_SEAMLESS_HASH)

        private const val GET_SEAMLESS_HASH = 2240911060L
        @JvmField
        val getSeamlessBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_seamless", GET_SEAMLESS_HASH)

        private const val SET_INVERT_HASH = 2586408642L
        @JvmField
        val setInvertBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_invert", SET_INVERT_HASH)

        private const val GET_INVERT_HASH = 36873697L
        @JvmField
        val getInvertBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_invert", GET_INVERT_HASH)

        private const val SET_NORMALIZE_HASH = 2586408642L
        @JvmField
        val setNormalizeBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_normalize", SET_NORMALIZE_HASH)

        private const val IS_NORMALIZED_HASH = 36873697L
        @JvmField
        val isNormalizedBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "is_normalized", IS_NORMALIZED_HASH)

        private const val SET_SEAMLESS_BLEND_SKIRT_HASH = 373806689L
        @JvmField
        val setSeamlessBlendSkirtBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_seamless_blend_skirt", SET_SEAMLESS_BLEND_SKIRT_HASH)

        private const val GET_SEAMLESS_BLEND_SKIRT_HASH = 191475506L
        @JvmField
        val getSeamlessBlendSkirtBind =
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_seamless_blend_skirt", GET_SEAMLESS_BLEND_SKIRT_HASH)
    }
}

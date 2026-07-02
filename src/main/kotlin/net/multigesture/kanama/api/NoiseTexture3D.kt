package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: NoiseTexture3D
 */
class NoiseTexture3D(handle: MemorySegment) : Texture3D(handle) {
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
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    fun setHeight(height: Int) {
        ObjectCalls.ptrcallWithIntArg(setHeightBind, handle, height)
    }

    fun setDepth(depth: Int) {
        ObjectCalls.ptrcallWithIntArg(setDepthBind, handle, depth)
    }

    fun setNoise(noise: Noise?) {
        ObjectCalls.ptrcallWithObjectArgs(setNoiseBind, handle, listOf(noise?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNoise(): Noise? {
        return Noise.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNoiseBind, handle))
    }

    fun setColorRamp(gradient: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, handle, listOf(gradient?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getColorRamp(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, handle))
    }

    fun setSeamless(seamless: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSeamlessBind, handle, seamless)
    }

    fun getSeamless(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSeamlessBind, handle)
    }

    fun setInvert(invert: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInvertBind, handle, invert)
    }

    fun getInvert(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getInvertBind, handle)
    }

    fun setNormalize(normalize: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNormalizeBind, handle, normalize)
    }

    fun isNormalized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNormalizedBind, handle)
    }

    fun setSeamlessBlendSkirt(seamlessBlendSkirt: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSeamlessBlendSkirtBind, handle, seamlessBlendSkirt)
    }

    fun getSeamlessBlendSkirt(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSeamlessBlendSkirtBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NoiseTexture3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NoiseTexture3D? =
            if (handle.address() == 0L) null else NoiseTexture3D(handle)

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_width", SET_WIDTH_HASH)
        }

        private const val SET_HEIGHT_HASH = 1286410249L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val SET_DEPTH_HASH = 1286410249L
        private val setDepthBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_depth", SET_DEPTH_HASH)
        }

        private const val SET_NOISE_HASH = 4135492439L
        private val setNoiseBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_noise", SET_NOISE_HASH)
        }

        private const val GET_NOISE_HASH = 185851837L
        private val getNoiseBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_noise", GET_NOISE_HASH)
        }

        private const val SET_COLOR_RAMP_HASH = 2756054477L
        private val setColorRampBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_color_ramp", SET_COLOR_RAMP_HASH)
        }

        private const val GET_COLOR_RAMP_HASH = 132272999L
        private val getColorRampBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_color_ramp", GET_COLOR_RAMP_HASH)
        }

        private const val SET_SEAMLESS_HASH = 2586408642L
        private val setSeamlessBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_seamless", SET_SEAMLESS_HASH)
        }

        private const val GET_SEAMLESS_HASH = 2240911060L
        private val getSeamlessBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_seamless", GET_SEAMLESS_HASH)
        }

        private const val SET_INVERT_HASH = 2586408642L
        private val setInvertBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_invert", SET_INVERT_HASH)
        }

        private const val GET_INVERT_HASH = 36873697L
        private val getInvertBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_invert", GET_INVERT_HASH)
        }

        private const val SET_NORMALIZE_HASH = 2586408642L
        private val setNormalizeBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_normalize", SET_NORMALIZE_HASH)
        }

        private const val IS_NORMALIZED_HASH = 36873697L
        private val isNormalizedBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "is_normalized", IS_NORMALIZED_HASH)
        }

        private const val SET_SEAMLESS_BLEND_SKIRT_HASH = 373806689L
        private val setSeamlessBlendSkirtBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "set_seamless_blend_skirt", SET_SEAMLESS_BLEND_SKIRT_HASH)
        }

        private const val GET_SEAMLESS_BLEND_SKIRT_HASH = 191475506L
        private val getSeamlessBlendSkirtBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture3D", "get_seamless_blend_skirt", GET_SEAMLESS_BLEND_SKIRT_HASH)
        }
    }
}

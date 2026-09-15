package net.multigesture.kanama.api

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
        ObjectCalls.ptrcallWithIntArg(setWidthBind, segment, width)
    }

    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHeightBind, segment, height)
    }

    fun setDepth(depth: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setDepthBind, segment, depth)
    }

    fun setNoise(noise: Noise?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNoiseBind, segment, listOf(noise?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getNoise(): Noise? {
        checkOpen()
        return Noise.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNoiseBind, segment))
    }

    fun setColorRamp(gradient: Gradient?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, segment, listOf(gradient?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getColorRamp(): Gradient? {
        checkOpen()
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, segment))
    }

    fun setSeamless(seamless: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setSeamlessBind, segment, seamless)
    }

    fun getSeamless(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getSeamlessBind, segment)
    }

    fun setInvert(invert: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setInvertBind, segment, invert)
    }

    fun getInvert(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getInvertBind, segment)
    }

    fun setNormalize(normalize: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setNormalizeBind, segment, normalize)
    }

    fun isNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isNormalizedBind, segment)
    }

    fun setSeamlessBlendSkirt(seamlessBlendSkirt: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSeamlessBlendSkirtBind, segment, seamlessBlendSkirt)
    }

    fun getSeamlessBlendSkirt(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSeamlessBlendSkirtBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NoiseTexture3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): NoiseTexture3D? =
            if (handle.address() == 0L) null else NoiseTexture3D(GodotHandle(handle))

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

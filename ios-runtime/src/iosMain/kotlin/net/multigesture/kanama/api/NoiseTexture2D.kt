package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: NoiseTexture2D
 */
class NoiseTexture2D(handle: MemorySegment) : Texture2D(handle) {
    var generateMipmaps: Boolean
        @JvmName("generateMipmapsProperty")
        get() = isGeneratingMipmaps()
        @JvmName("setGenerateMipmapsProperty")
        set(value) = setGenerateMipmaps(value)

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

    var in3dSpace: Boolean
        @JvmName("in3dSpaceProperty")
        get() = isIn3dSpace()
        @JvmName("setIn3dSpaceProperty")
        set(value) = setIn3dSpace(value)

    var asNormalMap: Boolean
        @JvmName("asNormalMapProperty")
        get() = isNormalMap()
        @JvmName("setAsNormalMapProperty")
        set(value) = setAsNormalMap(value)

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

    var bumpStrength: Double
        @JvmName("bumpStrengthProperty")
        get() = getBumpStrength()
        @JvmName("setBumpStrengthProperty")
        set(value) = setBumpStrength(value)

    fun setWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHeightBind, handle, height)
    }

    fun setGenerateMipmaps(invert: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setGenerateMipmapsBind, handle, invert)
    }

    fun isGeneratingMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isGeneratingMipmapsBind, handle)
    }

    fun setNoise(noise: Noise?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNoiseBind, handle, listOf(noise?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNoise(): Noise? {
        checkOpen()
        return Noise.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNoiseBind, handle))
    }

    fun setColorRamp(gradient: Gradient?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, handle, listOf(gradient?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getColorRamp(): Gradient? {
        checkOpen()
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, handle))
    }

    fun setSeamless(seamless: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setSeamlessBind, handle, seamless)
    }

    fun getSeamless(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getSeamlessBind, handle)
    }

    fun setInvert(invert: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setInvertBind, handle, invert)
    }

    fun getInvert(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getInvertBind, handle)
    }

    fun setIn3dSpace(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setIn3dSpaceBind, handle, enable)
    }

    fun isIn3dSpace(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isIn3dSpaceBind, handle)
    }

    fun setAsNormalMap(asNormalMap: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAsNormalMapBind, handle, asNormalMap)
    }

    fun isNormalMap(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isNormalMapBind, handle)
    }

    fun setNormalize(normalize: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setNormalizeBind, handle, normalize)
    }

    fun isNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isNormalizedBind, handle)
    }

    fun setSeamlessBlendSkirt(seamlessBlendSkirt: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSeamlessBlendSkirtBind, handle, seamlessBlendSkirt)
    }

    fun getSeamlessBlendSkirt(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSeamlessBlendSkirtBind, handle)
    }

    fun setBumpStrength(bumpStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBumpStrengthBind, handle, bumpStrength)
    }

    fun getBumpStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBumpStrengthBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): NoiseTexture2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NoiseTexture2D? =
            if (handle.address() == 0L) null else NoiseTexture2D(handle)

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_width", SET_WIDTH_HASH)
        }

        private const val SET_HEIGHT_HASH = 1286410249L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_height", SET_HEIGHT_HASH)
        }

        private const val SET_GENERATE_MIPMAPS_HASH = 2586408642L
        private val setGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_generate_mipmaps", SET_GENERATE_MIPMAPS_HASH)
        }

        private const val IS_GENERATING_MIPMAPS_HASH = 36873697L
        private val isGeneratingMipmapsBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_generating_mipmaps", IS_GENERATING_MIPMAPS_HASH)
        }

        private const val SET_NOISE_HASH = 4135492439L
        private val setNoiseBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_noise", SET_NOISE_HASH)
        }

        private const val GET_NOISE_HASH = 185851837L
        private val getNoiseBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_noise", GET_NOISE_HASH)
        }

        private const val SET_COLOR_RAMP_HASH = 2756054477L
        private val setColorRampBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_color_ramp", SET_COLOR_RAMP_HASH)
        }

        private const val GET_COLOR_RAMP_HASH = 132272999L
        private val getColorRampBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_color_ramp", GET_COLOR_RAMP_HASH)
        }

        private const val SET_SEAMLESS_HASH = 2586408642L
        private val setSeamlessBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_seamless", SET_SEAMLESS_HASH)
        }

        private const val GET_SEAMLESS_HASH = 2240911060L
        private val getSeamlessBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_seamless", GET_SEAMLESS_HASH)
        }

        private const val SET_INVERT_HASH = 2586408642L
        private val setInvertBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_invert", SET_INVERT_HASH)
        }

        private const val GET_INVERT_HASH = 36873697L
        private val getInvertBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_invert", GET_INVERT_HASH)
        }

        private const val SET_IN_3D_SPACE_HASH = 2586408642L
        private val setIn3dSpaceBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_in_3d_space", SET_IN_3D_SPACE_HASH)
        }

        private const val IS_IN_3D_SPACE_HASH = 36873697L
        private val isIn3dSpaceBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_in_3d_space", IS_IN_3D_SPACE_HASH)
        }

        private const val SET_AS_NORMAL_MAP_HASH = 2586408642L
        private val setAsNormalMapBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_as_normal_map", SET_AS_NORMAL_MAP_HASH)
        }

        private const val IS_NORMAL_MAP_HASH = 2240911060L
        private val isNormalMapBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_normal_map", IS_NORMAL_MAP_HASH)
        }

        private const val SET_NORMALIZE_HASH = 2586408642L
        private val setNormalizeBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_normalize", SET_NORMALIZE_HASH)
        }

        private const val IS_NORMALIZED_HASH = 36873697L
        private val isNormalizedBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_normalized", IS_NORMALIZED_HASH)
        }

        private const val SET_SEAMLESS_BLEND_SKIRT_HASH = 373806689L
        private val setSeamlessBlendSkirtBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_seamless_blend_skirt", SET_SEAMLESS_BLEND_SKIRT_HASH)
        }

        private const val GET_SEAMLESS_BLEND_SKIRT_HASH = 191475506L
        private val getSeamlessBlendSkirtBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_seamless_blend_skirt", GET_SEAMLESS_BLEND_SKIRT_HASH)
        }

        private const val SET_BUMP_STRENGTH_HASH = 373806689L
        private val setBumpStrengthBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_bump_strength", SET_BUMP_STRENGTH_HASH)
        }

        private const val GET_BUMP_STRENGTH_HASH = 191475506L
        private val getBumpStrengthBind by lazy {
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_bump_strength", GET_BUMP_STRENGTH_HASH)
        }
    }
}

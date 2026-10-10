package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: NoiseTexture2D
 */
class NoiseTexture2D(handle: GodotHandle) : Texture2D(handle) {
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
        ObjectCalls.ptrcallWithIntArg(Binds.setWidthBind, segment, width)
    }

    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setHeightBind, segment, height)
    }

    fun setGenerateMipmaps(generate: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setGenerateMipmapsBind, segment, generate)
    }

    fun isGeneratingMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isGeneratingMipmapsBind, segment)
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

    fun setIn3dSpace(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIn3dSpaceBind, segment, enable)
    }

    fun isIn3dSpace(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isIn3dSpaceBind, segment)
    }

    fun setAsNormalMap(asNormalMap: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAsNormalMapBind, segment, asNormalMap)
    }

    fun isNormalMap(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNormalMapBind, segment)
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

    fun setBumpStrength(bumpStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBumpStrengthBind, segment, bumpStrength)
    }

    fun getBumpStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBumpStrengthBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NoiseTexture2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): NoiseTexture2D? =
            if (handle.address() == 0L) null else RefCounted.owned(NoiseTexture2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): NoiseTexture2D? =
            if (handle.address() == 0L) null else NoiseTexture2D(GodotHandle(handle))

        // Downcast a GodotObject to NoiseTexture2D (null if not).
        @JvmStatic
        fun fromObject(value: GodotObject): NoiseTexture2D? =
            if (value.isClass("NoiseTexture2D")) RefCounted.retained(NoiseTexture2D(value.handle)) else null

        // Downcast a Resource to NoiseTexture2D (null if not).
        @JvmStatic
        fun fromResource(value: Resource): NoiseTexture2D? =
            if (value.isClass("NoiseTexture2D")) RefCounted.retained(NoiseTexture2D(value.handle)) else null
    }

    private object Binds {
        private const val SET_WIDTH_HASH = 1286410249L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_width", SET_WIDTH_HASH)

        private const val SET_HEIGHT_HASH = 1286410249L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_height", SET_HEIGHT_HASH)

        private const val SET_GENERATE_MIPMAPS_HASH = 2586408642L
        @JvmField
        val setGenerateMipmapsBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_generate_mipmaps", SET_GENERATE_MIPMAPS_HASH)

        private const val IS_GENERATING_MIPMAPS_HASH = 36873697L
        @JvmField
        val isGeneratingMipmapsBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_generating_mipmaps", IS_GENERATING_MIPMAPS_HASH)

        private const val SET_NOISE_HASH = 4135492439L
        @JvmField
        val setNoiseBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_noise", SET_NOISE_HASH)

        private const val GET_NOISE_HASH = 185851837L
        @JvmField
        val getNoiseBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_noise", GET_NOISE_HASH)

        private const val SET_COLOR_RAMP_HASH = 2756054477L
        @JvmField
        val setColorRampBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_color_ramp", SET_COLOR_RAMP_HASH)

        private const val GET_COLOR_RAMP_HASH = 132272999L
        @JvmField
        val getColorRampBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_color_ramp", GET_COLOR_RAMP_HASH)

        private const val SET_SEAMLESS_HASH = 2586408642L
        @JvmField
        val setSeamlessBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_seamless", SET_SEAMLESS_HASH)

        private const val GET_SEAMLESS_HASH = 2240911060L
        @JvmField
        val getSeamlessBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_seamless", GET_SEAMLESS_HASH)

        private const val SET_INVERT_HASH = 2586408642L
        @JvmField
        val setInvertBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_invert", SET_INVERT_HASH)

        private const val GET_INVERT_HASH = 36873697L
        @JvmField
        val getInvertBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_invert", GET_INVERT_HASH)

        private const val SET_IN_3D_SPACE_HASH = 2586408642L
        @JvmField
        val setIn3dSpaceBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_in_3d_space", SET_IN_3D_SPACE_HASH)

        private const val IS_IN_3D_SPACE_HASH = 36873697L
        @JvmField
        val isIn3dSpaceBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_in_3d_space", IS_IN_3D_SPACE_HASH)

        private const val SET_AS_NORMAL_MAP_HASH = 2586408642L
        @JvmField
        val setAsNormalMapBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_as_normal_map", SET_AS_NORMAL_MAP_HASH)

        private const val IS_NORMAL_MAP_HASH = 2240911060L
        @JvmField
        val isNormalMapBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_normal_map", IS_NORMAL_MAP_HASH)

        private const val SET_NORMALIZE_HASH = 2586408642L
        @JvmField
        val setNormalizeBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_normalize", SET_NORMALIZE_HASH)

        private const val IS_NORMALIZED_HASH = 36873697L
        @JvmField
        val isNormalizedBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "is_normalized", IS_NORMALIZED_HASH)

        private const val SET_SEAMLESS_BLEND_SKIRT_HASH = 373806689L
        @JvmField
        val setSeamlessBlendSkirtBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_seamless_blend_skirt", SET_SEAMLESS_BLEND_SKIRT_HASH)

        private const val GET_SEAMLESS_BLEND_SKIRT_HASH = 191475506L
        @JvmField
        val getSeamlessBlendSkirtBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_seamless_blend_skirt", GET_SEAMLESS_BLEND_SKIRT_HASH)

        private const val SET_BUMP_STRENGTH_HASH = 373806689L
        @JvmField
        val setBumpStrengthBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "set_bump_strength", SET_BUMP_STRENGTH_HASH)

        private const val GET_BUMP_STRENGTH_HASH = 191475506L
        @JvmField
        val getBumpStrengthBind =
            ObjectCalls.getMethodBind("NoiseTexture2D", "get_bump_strength", GET_BUMP_STRENGTH_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A font loaded from a system font. Falls back to a default theme font if not implemented on the
 * host OS.
 *
 * Generated from Godot docs: SystemFont
 */
class SystemFont(handle: MemorySegment) : Font(handle) {
    var fontNames: List<String>
        @JvmName("fontNamesProperty")
        get() = getFontNames()
        @JvmName("setFontNamesProperty")
        set(value) = setFontNames(value)

    var fontItalic: Boolean
        @JvmName("fontItalicProperty")
        get() = getFontItalic()
        @JvmName("setFontItalicProperty")
        set(value) = setFontItalic(value)

    var antialiasing: Long
        @JvmName("antialiasingProperty")
        get() = getAntialiasing()
        @JvmName("setAntialiasingProperty")
        set(value) = setAntialiasing(value)

    var generateMipmaps: Boolean
        @JvmName("generateMipmapsProperty")
        get() = getGenerateMipmaps()
        @JvmName("setGenerateMipmapsProperty")
        set(value) = setGenerateMipmaps(value)

    var disableEmbeddedBitmaps: Boolean
        @JvmName("disableEmbeddedBitmapsProperty")
        get() = getDisableEmbeddedBitmaps()
        @JvmName("setDisableEmbeddedBitmapsProperty")
        set(value) = setDisableEmbeddedBitmaps(value)

    var allowSystemFallback: Boolean
        @JvmName("allowSystemFallbackProperty")
        get() = isAllowSystemFallback()
        @JvmName("setAllowSystemFallbackProperty")
        set(value) = setAllowSystemFallback(value)

    var forceAutohinter: Boolean
        @JvmName("forceAutohinterProperty")
        get() = isForceAutohinter()
        @JvmName("setForceAutohinterProperty")
        set(value) = setForceAutohinter(value)

    var modulateColorGlyphs: Boolean
        @JvmName("modulateColorGlyphsProperty")
        get() = isModulateColorGlyphs()
        @JvmName("setModulateColorGlyphsProperty")
        set(value) = setModulateColorGlyphs(value)

    var hinting: Long
        @JvmName("hintingProperty")
        get() = getHinting()
        @JvmName("setHintingProperty")
        set(value) = setHinting(value)

    var subpixelPositioning: Long
        @JvmName("subpixelPositioningProperty")
        get() = getSubpixelPositioning()
        @JvmName("setSubpixelPositioningProperty")
        set(value) = setSubpixelPositioning(value)

    var keepRoundingRemainders: Boolean
        @JvmName("keepRoundingRemaindersProperty")
        get() = getKeepRoundingRemainders()
        @JvmName("setKeepRoundingRemaindersProperty")
        set(value) = setKeepRoundingRemainders(value)

    var multichannelSignedDistanceField: Boolean
        @JvmName("multichannelSignedDistanceFieldProperty")
        get() = isMultichannelSignedDistanceField()
        @JvmName("setMultichannelSignedDistanceFieldProperty")
        set(value) = setMultichannelSignedDistanceField(value)

    var msdfPixelRange: Int
        @JvmName("msdfPixelRangeProperty")
        get() = getMsdfPixelRange()
        @JvmName("setMsdfPixelRangeProperty")
        set(value) = setMsdfPixelRange(value)

    var msdfSize: Int
        @JvmName("msdfSizeProperty")
        get() = getMsdfSize()
        @JvmName("setMsdfSizeProperty")
        set(value) = setMsdfSize(value)

    var oversampling: Double
        @JvmName("oversamplingProperty")
        get() = getOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setOversampling(value)

    fun setAntialiasing(antialiasing: Long) {
        ObjectCalls.ptrcallWithLongArg(setAntialiasingBind, handle, antialiasing)
    }

    fun getAntialiasing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAntialiasingBind, handle)
    }

    fun setDisableEmbeddedBitmaps(disableEmbeddedBitmaps: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableEmbeddedBitmapsBind, handle, disableEmbeddedBitmaps)
    }

    fun getDisableEmbeddedBitmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDisableEmbeddedBitmapsBind, handle)
    }

    fun setGenerateMipmaps(generateMipmaps: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGenerateMipmapsBind, handle, generateMipmaps)
    }

    fun getGenerateMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getGenerateMipmapsBind, handle)
    }

    fun setAllowSystemFallback(allowSystemFallback: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowSystemFallbackBind, handle, allowSystemFallback)
    }

    fun isAllowSystemFallback(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowSystemFallbackBind, handle)
    }

    fun setForceAutohinter(forceAutohinter: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setForceAutohinterBind, handle, forceAutohinter)
    }

    fun isForceAutohinter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isForceAutohinterBind, handle)
    }

    fun setModulateColorGlyphs(modulate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setModulateColorGlyphsBind, handle, modulate)
    }

    fun isModulateColorGlyphs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isModulateColorGlyphsBind, handle)
    }

    fun setHinting(hinting: Long) {
        ObjectCalls.ptrcallWithLongArg(setHintingBind, handle, hinting)
    }

    fun getHinting(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHintingBind, handle)
    }

    fun setSubpixelPositioning(subpixelPositioning: Long) {
        ObjectCalls.ptrcallWithLongArg(setSubpixelPositioningBind, handle, subpixelPositioning)
    }

    fun getSubpixelPositioning(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSubpixelPositioningBind, handle)
    }

    fun setKeepRoundingRemainders(keepRoundingRemainders: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepRoundingRemaindersBind, handle, keepRoundingRemainders)
    }

    fun getKeepRoundingRemainders(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getKeepRoundingRemaindersBind, handle)
    }

    fun setMultichannelSignedDistanceField(msdf: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMultichannelSignedDistanceFieldBind, handle, msdf)
    }

    fun isMultichannelSignedDistanceField(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMultichannelSignedDistanceFieldBind, handle)
    }

    fun setMsdfPixelRange(msdfPixelRange: Int) {
        ObjectCalls.ptrcallWithIntArg(setMsdfPixelRangeBind, handle, msdfPixelRange)
    }

    fun getMsdfPixelRange(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMsdfPixelRangeBind, handle)
    }

    fun setMsdfSize(msdfSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setMsdfSizeBind, handle, msdfSize)
    }

    fun getMsdfSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMsdfSizeBind, handle)
    }

    fun setOversampling(oversampling: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOversamplingBind, handle, oversampling)
    }

    fun getOversampling(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversamplingBind, handle)
    }

    fun getFontNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFontNamesBind, handle)
    }

    fun setFontNames(names: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setFontNamesBind, handle, names)
    }

    fun getFontItalic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFontItalicBind, handle)
    }

    fun setFontItalic(italic: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFontItalicBind, handle, italic)
    }

    fun setFontWeight(weight: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontWeightBind, handle, weight)
    }

    fun setFontStretch(stretch: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontStretchBind, handle, stretch)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SystemFont? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SystemFont? =
            if (handle.address() == 0L) null else SystemFont(handle)

        private const val SET_ANTIALIASING_HASH = 1669900L
        private val setAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_antialiasing", SET_ANTIALIASING_HASH)
        }

        private const val GET_ANTIALIASING_HASH = 4262718649L
        private val getAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_antialiasing", GET_ANTIALIASING_HASH)
        }

        private const val SET_DISABLE_EMBEDDED_BITMAPS_HASH = 2586408642L
        private val setDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_disable_embedded_bitmaps", SET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val GET_DISABLE_EMBEDDED_BITMAPS_HASH = 36873697L
        private val getDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_disable_embedded_bitmaps", GET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val SET_GENERATE_MIPMAPS_HASH = 2586408642L
        private val setGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_generate_mipmaps", SET_GENERATE_MIPMAPS_HASH)
        }

        private const val GET_GENERATE_MIPMAPS_HASH = 36873697L
        private val getGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_generate_mipmaps", GET_GENERATE_MIPMAPS_HASH)
        }

        private const val SET_ALLOW_SYSTEM_FALLBACK_HASH = 2586408642L
        private val setAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_allow_system_fallback", SET_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val IS_ALLOW_SYSTEM_FALLBACK_HASH = 36873697L
        private val isAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "is_allow_system_fallback", IS_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val SET_FORCE_AUTOHINTER_HASH = 2586408642L
        private val setForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_force_autohinter", SET_FORCE_AUTOHINTER_HASH)
        }

        private const val IS_FORCE_AUTOHINTER_HASH = 36873697L
        private val isForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "is_force_autohinter", IS_FORCE_AUTOHINTER_HASH)
        }

        private const val SET_MODULATE_COLOR_GLYPHS_HASH = 2586408642L
        private val setModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_modulate_color_glyphs", SET_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val IS_MODULATE_COLOR_GLYPHS_HASH = 36873697L
        private val isModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "is_modulate_color_glyphs", IS_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val SET_HINTING_HASH = 1827459492L
        private val setHintingBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_hinting", SET_HINTING_HASH)
        }

        private const val GET_HINTING_HASH = 3683214614L
        private val getHintingBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_hinting", GET_HINTING_HASH)
        }

        private const val SET_SUBPIXEL_POSITIONING_HASH = 4225742182L
        private val setSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_subpixel_positioning", SET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val GET_SUBPIXEL_POSITIONING_HASH = 1069238588L
        private val getSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_subpixel_positioning", GET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val SET_KEEP_ROUNDING_REMAINDERS_HASH = 2586408642L
        private val setKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_keep_rounding_remainders", SET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val GET_KEEP_ROUNDING_REMAINDERS_HASH = 36873697L
        private val getKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_keep_rounding_remainders", GET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 2586408642L
        private val setMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_multichannel_signed_distance_field", SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 36873697L
        private val isMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "is_multichannel_signed_distance_field", IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val SET_MSDF_PIXEL_RANGE_HASH = 1286410249L
        private val setMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_msdf_pixel_range", SET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val GET_MSDF_PIXEL_RANGE_HASH = 3905245786L
        private val getMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_msdf_pixel_range", GET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val SET_MSDF_SIZE_HASH = 1286410249L
        private val setMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_msdf_size", SET_MSDF_SIZE_HASH)
        }

        private const val GET_MSDF_SIZE_HASH = 3905245786L
        private val getMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_msdf_size", GET_MSDF_SIZE_HASH)
        }

        private const val SET_OVERSAMPLING_HASH = 373806689L
        private val setOversamplingBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_oversampling", SET_OVERSAMPLING_HASH)
        }

        private const val GET_OVERSAMPLING_HASH = 1740695150L
        private val getOversamplingBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_oversampling", GET_OVERSAMPLING_HASH)
        }

        private const val GET_FONT_NAMES_HASH = 1139954409L
        private val getFontNamesBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_font_names", GET_FONT_NAMES_HASH)
        }

        private const val SET_FONT_NAMES_HASH = 4015028928L
        private val setFontNamesBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_font_names", SET_FONT_NAMES_HASH)
        }

        private const val GET_FONT_ITALIC_HASH = 36873697L
        private val getFontItalicBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "get_font_italic", GET_FONT_ITALIC_HASH)
        }

        private const val SET_FONT_ITALIC_HASH = 2586408642L
        private val setFontItalicBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_font_italic", SET_FONT_ITALIC_HASH)
        }

        private const val SET_FONT_WEIGHT_HASH = 1286410249L
        private val setFontWeightBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_font_weight", SET_FONT_WEIGHT_HASH)
        }

        private const val SET_FONT_STRETCH_HASH = 1286410249L
        private val setFontStretchBind by lazy {
            ObjectCalls.getMethodBind("SystemFont", "set_font_stretch", SET_FONT_STRETCH_HASH)
        }
    }
}

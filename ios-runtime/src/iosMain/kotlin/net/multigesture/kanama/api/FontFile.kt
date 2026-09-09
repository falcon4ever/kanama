package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: FontFile
 */
class FontFile(handle: MemorySegment) : Font(handle) {
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

    var antialiasing: Long
        @JvmName("antialiasingProperty")
        get() = getAntialiasing()
        @JvmName("setAntialiasingProperty")
        set(value) = setAntialiasing(value)

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

    var fixedSize: Int
        @JvmName("fixedSizeProperty")
        get() = getFixedSize()
        @JvmName("setFixedSizeProperty")
        set(value) = setFixedSize(value)

    var fixedSizeScaleMode: Long
        @JvmName("fixedSizeScaleModeProperty")
        get() = getFixedSizeScaleMode()
        @JvmName("setFixedSizeScaleModeProperty")
        set(value) = setFixedSizeScaleMode(value)

    var oversampling: Double
        @JvmName("oversamplingProperty")
        get() = getOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setOversampling(value)

    fun loadBitmapFont(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBitmapFontBind, handle, path)
    }

    fun loadDynamicFont(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadDynamicFontBind, handle, path)
    }

    fun setFontName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setFontNameBind, handle, name)
    }

    fun setFontStyleName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setFontStyleNameBind, handle, name)
    }

    fun setFontStyle(style: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFontStyleBind, handle, style)
    }

    fun setFontWeight(weight: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setFontWeightBind, handle, weight)
    }

    fun setFontStretch(stretch: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setFontStretchBind, handle, stretch)
    }

    fun setAntialiasing(antialiasing: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAntialiasingBind, handle, antialiasing)
    }

    fun getAntialiasing(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getAntialiasingBind, handle)
    }

    fun setDisableEmbeddedBitmaps(disableEmbeddedBitmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDisableEmbeddedBitmapsBind, handle, disableEmbeddedBitmaps)
    }

    fun getDisableEmbeddedBitmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getDisableEmbeddedBitmapsBind, handle)
    }

    fun setGenerateMipmaps(generateMipmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setGenerateMipmapsBind, handle, generateMipmaps)
    }

    fun getGenerateMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getGenerateMipmapsBind, handle)
    }

    fun setMultichannelSignedDistanceField(msdf: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setMultichannelSignedDistanceFieldBind, handle, msdf)
    }

    fun isMultichannelSignedDistanceField(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isMultichannelSignedDistanceFieldBind, handle)
    }

    fun setMsdfPixelRange(msdfPixelRange: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMsdfPixelRangeBind, handle, msdfPixelRange)
    }

    fun getMsdfPixelRange(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMsdfPixelRangeBind, handle)
    }

    fun setMsdfSize(msdfSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMsdfSizeBind, handle, msdfSize)
    }

    fun getMsdfSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMsdfSizeBind, handle)
    }

    fun setFixedSize(fixedSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setFixedSizeBind, handle, fixedSize)
    }

    fun getFixedSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedSizeBind, handle)
    }

    fun setFixedSizeScaleMode(fixedSizeScaleMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFixedSizeScaleModeBind, handle, fixedSizeScaleMode)
    }

    fun getFixedSizeScaleMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFixedSizeScaleModeBind, handle)
    }

    fun setAllowSystemFallback(allowSystemFallback: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAllowSystemFallbackBind, handle, allowSystemFallback)
    }

    fun isAllowSystemFallback(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowSystemFallbackBind, handle)
    }

    fun setForceAutohinter(forceAutohinter: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setForceAutohinterBind, handle, forceAutohinter)
    }

    fun isForceAutohinter(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isForceAutohinterBind, handle)
    }

    fun setModulateColorGlyphs(modulate: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setModulateColorGlyphsBind, handle, modulate)
    }

    fun isModulateColorGlyphs(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isModulateColorGlyphsBind, handle)
    }

    fun setHinting(hinting: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHintingBind, handle, hinting)
    }

    fun getHinting(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getHintingBind, handle)
    }

    fun setSubpixelPositioning(subpixelPositioning: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSubpixelPositioningBind, handle, subpixelPositioning)
    }

    fun getSubpixelPositioning(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSubpixelPositioningBind, handle)
    }

    fun setKeepRoundingRemainders(keepRoundingRemainders: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setKeepRoundingRemaindersBind, handle, keepRoundingRemainders)
    }

    fun getKeepRoundingRemainders(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getKeepRoundingRemaindersBind, handle)
    }

    fun setOversampling(oversampling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setOversamplingBind, handle, oversampling)
    }

    fun getOversampling(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversamplingBind, handle)
    }

    fun getCacheCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCacheCountBind, handle)
    }

    fun clearCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearCacheBind, handle)
    }

    fun removeCache(cacheIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(removeCacheBind, handle, cacheIndex)
    }

    fun clearSizeCache(cacheIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(clearSizeCacheBind, handle, cacheIndex)
    }

    fun removeSizeCache(cacheIndex: Int, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(removeSizeCacheBind, handle, cacheIndex, size)
    }

    fun setEmbolden(cacheIndex: Int, strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(setEmboldenBind, handle, cacheIndex, strength)
    }

    fun getEmbolden(cacheIndex: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getEmboldenBind, handle, cacheIndex)
    }

    fun setTransform(cacheIndex: Int, transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform2DArg(setTransformBind, handle, cacheIndex, transform)
    }

    fun getTransform(cacheIndex: Int): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform2D(getTransformBind, handle, cacheIndex)
    }

    fun setExtraSpacing(cacheIndex: Int, spacing: Long, value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntTwoLongArgs(setExtraSpacingBind, handle, cacheIndex, spacing, value)
    }

    fun getExtraSpacing(cacheIndex: Int, spacing: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndLongArgsRetLong(getExtraSpacingBind, handle, cacheIndex, spacing)
    }

    fun setExtraBaselineOffset(cacheIndex: Int, baselineOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(setExtraBaselineOffsetBind, handle, cacheIndex, baselineOffset)
    }

    fun getExtraBaselineOffset(cacheIndex: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getExtraBaselineOffsetBind, handle, cacheIndex)
    }

    fun setFaceIndex(cacheIndex: Int, faceIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(setFaceIndexBind, handle, cacheIndex, faceIndex)
    }

    fun getFaceIndex(cacheIndex: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getFaceIndexBind, handle, cacheIndex)
    }

    fun setCacheAscent(cacheIndex: Int, size: Int, ascent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setCacheAscentBind, handle, cacheIndex, size, ascent)
    }

    fun getCacheAscent(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getCacheAscentBind, handle, cacheIndex, size)
    }

    fun setCacheDescent(cacheIndex: Int, size: Int, descent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setCacheDescentBind, handle, cacheIndex, size, descent)
    }

    fun getCacheDescent(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getCacheDescentBind, handle, cacheIndex, size)
    }

    fun setCacheUnderlinePosition(cacheIndex: Int, size: Int, underlinePosition: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setCacheUnderlinePositionBind, handle, cacheIndex, size, underlinePosition)
    }

    fun getCacheUnderlinePosition(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getCacheUnderlinePositionBind, handle, cacheIndex, size)
    }

    fun setCacheUnderlineThickness(cacheIndex: Int, size: Int, underlineThickness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setCacheUnderlineThicknessBind, handle, cacheIndex, size, underlineThickness)
    }

    fun getCacheUnderlineThickness(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getCacheUnderlineThicknessBind, handle, cacheIndex, size)
    }

    fun setCacheScale(cacheIndex: Int, size: Int, scale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setCacheScaleBind, handle, cacheIndex, size, scale)
    }

    fun getCacheScale(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getCacheScaleBind, handle, cacheIndex, size)
    }

    fun getTextureCount(cacheIndex: Int, size: Vector2i): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndVector2iArgRetInt(getTextureCountBind, handle, cacheIndex, size)
    }

    fun clearTextures(cacheIndex: Int, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(clearTexturesBind, handle, cacheIndex, size)
    }

    fun removeTexture(cacheIndex: Int, size: Vector2i, textureIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(removeTextureBind, handle, cacheIndex, size, textureIndex)
    }

    fun setTextureImage(cacheIndex: Int, size: Vector2i, textureIndex: Int, image: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntObjectArgs(setTextureImageBind, handle, cacheIndex, size, textureIndex, image?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getTextureImage(cacheIndex: Int, size: Vector2i, textureIndex: Int): Image? {
        checkOpen()
        return Image.wrap(ObjectCalls.ptrcallWithIntVector2iIntArgsRetObject(getTextureImageBind, handle, cacheIndex, size, textureIndex))
    }

    fun clearGlyphs(cacheIndex: Int, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(clearGlyphsBind, handle, cacheIndex, size)
    }

    fun removeGlyph(cacheIndex: Int, size: Vector2i, glyph: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(removeGlyphBind, handle, cacheIndex, size, glyph)
    }

    fun setGlyphAdvance(cacheIndex: Int, size: Int, glyph: Int, advance: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntAndVector2Arg(setGlyphAdvanceBind, handle, cacheIndex, size, glyph, advance)
    }

    fun getGlyphAdvance(cacheIndex: Int, size: Int, glyph: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeIntArgsRetVector2(getGlyphAdvanceBind, handle, cacheIndex, size, glyph)
    }

    fun setGlyphOffset(cacheIndex: Int, size: Vector2i, glyph: Int, offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntVector2Args(setGlyphOffsetBind, handle, cacheIndex, size, glyph, offset)
    }

    fun getGlyphOffset(cacheIndex: Int, size: Vector2i, glyph: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetVector2(getGlyphOffsetBind, handle, cacheIndex, size, glyph)
    }

    fun setGlyphSize(cacheIndex: Int, size: Vector2i, glyph: Int, glSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntVector2Args(setGlyphSizeBind, handle, cacheIndex, size, glyph, glSize)
    }

    fun getGlyphSize(cacheIndex: Int, size: Vector2i, glyph: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetVector2(getGlyphSizeBind, handle, cacheIndex, size, glyph)
    }

    fun setGlyphUvRect(cacheIndex: Int, size: Vector2i, glyph: Int, uvRect: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntRect2Args(setGlyphUvRectBind, handle, cacheIndex, size, glyph, uvRect)
    }

    fun getGlyphUvRect(cacheIndex: Int, size: Vector2i, glyph: Int): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetRect2(getGlyphUvRectBind, handle, cacheIndex, size, glyph)
    }

    fun setGlyphTextureIdx(cacheIndex: Int, size: Vector2i, glyph: Int, textureIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iTwoIntArgs(setGlyphTextureIdxBind, handle, cacheIndex, size, glyph, textureIdx)
    }

    fun getGlyphTextureIdx(cacheIndex: Int, size: Vector2i, glyph: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetInt(getGlyphTextureIdxBind, handle, cacheIndex, size, glyph)
    }

    fun clearKerningMap(cacheIndex: Int, size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(clearKerningMapBind, handle, cacheIndex, size)
    }

    fun removeKerning(cacheIndex: Int, size: Int, glyphPair: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndVector2iArg(removeKerningBind, handle, cacheIndex, size, glyphPair)
    }

    fun setKerning(cacheIndex: Int, size: Int, glyphPair: Vector2i, kerning: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntVector2iVector2Args(setKerningBind, handle, cacheIndex, size, glyphPair, kerning)
    }

    fun getKerning(cacheIndex: Int, size: Int, glyphPair: Vector2i): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntVector2iArgRetVector2(getKerningBind, handle, cacheIndex, size, glyphPair)
    }

    fun renderRange(cacheIndex: Int, size: Vector2i, start: Int, end: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iTwoIntArgs(renderRangeBind, handle, cacheIndex, size, start, end)
    }

    fun renderGlyph(cacheIndex: Int, size: Vector2i, index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(renderGlyphBind, handle, cacheIndex, size, index)
    }

    fun setLanguageSupportOverride(language: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(setLanguageSupportOverrideBind, handle, language, supported)
    }

    fun getLanguageSupportOverride(language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(getLanguageSupportOverrideBind, handle, language)
    }

    fun removeLanguageSupportOverride(language: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(removeLanguageSupportOverrideBind, handle, language)
    }

    fun getLanguageSupportOverrides(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getLanguageSupportOverridesBind, handle)
    }

    fun setScriptSupportOverride(script: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(setScriptSupportOverrideBind, handle, script, supported)
    }

    fun getScriptSupportOverride(script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(getScriptSupportOverrideBind, handle, script)
    }

    fun removeScriptSupportOverride(script: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(removeScriptSupportOverrideBind, handle, script)
    }

    fun getScriptSupportOverrides(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getScriptSupportOverridesBind, handle)
    }

    fun getGlyphIndex(size: Int, char: Int, variationSelector: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeIntArgsRetInt(getGlyphIndexBind, handle, size, char, variationSelector)
    }

    fun getCharFromGlyphIndex(size: Int, glyphIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getCharFromGlyphIndexBind, handle, size, glyphIndex)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): FontFile? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FontFile? =
            if (handle.address() == 0L) null else FontFile(handle)

        private const val LOAD_BITMAP_FONT_HASH = 166001499L
        private val loadBitmapFontBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "load_bitmap_font", LOAD_BITMAP_FONT_HASH)
        }

        private const val LOAD_DYNAMIC_FONT_HASH = 166001499L
        private val loadDynamicFontBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "load_dynamic_font", LOAD_DYNAMIC_FONT_HASH)
        }

        private const val SET_FONT_NAME_HASH = 83702148L
        private val setFontNameBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_font_name", SET_FONT_NAME_HASH)
        }

        private const val SET_FONT_STYLE_NAME_HASH = 83702148L
        private val setFontStyleNameBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_font_style_name", SET_FONT_STYLE_NAME_HASH)
        }

        private const val SET_FONT_STYLE_HASH = 918070724L
        private val setFontStyleBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_font_style", SET_FONT_STYLE_HASH)
        }

        private const val SET_FONT_WEIGHT_HASH = 1286410249L
        private val setFontWeightBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_font_weight", SET_FONT_WEIGHT_HASH)
        }

        private const val SET_FONT_STRETCH_HASH = 1286410249L
        private val setFontStretchBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_font_stretch", SET_FONT_STRETCH_HASH)
        }

        private const val SET_ANTIALIASING_HASH = 1669900L
        private val setAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_antialiasing", SET_ANTIALIASING_HASH)
        }

        private const val GET_ANTIALIASING_HASH = 4262718649L
        private val getAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_antialiasing", GET_ANTIALIASING_HASH)
        }

        private const val SET_DISABLE_EMBEDDED_BITMAPS_HASH = 2586408642L
        private val setDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_disable_embedded_bitmaps", SET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val GET_DISABLE_EMBEDDED_BITMAPS_HASH = 36873697L
        private val getDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_disable_embedded_bitmaps", GET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val SET_GENERATE_MIPMAPS_HASH = 2586408642L
        private val setGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_generate_mipmaps", SET_GENERATE_MIPMAPS_HASH)
        }

        private const val GET_GENERATE_MIPMAPS_HASH = 36873697L
        private val getGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_generate_mipmaps", GET_GENERATE_MIPMAPS_HASH)
        }

        private const val SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 2586408642L
        private val setMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_multichannel_signed_distance_field", SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 36873697L
        private val isMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "is_multichannel_signed_distance_field", IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val SET_MSDF_PIXEL_RANGE_HASH = 1286410249L
        private val setMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_msdf_pixel_range", SET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val GET_MSDF_PIXEL_RANGE_HASH = 3905245786L
        private val getMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_msdf_pixel_range", GET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val SET_MSDF_SIZE_HASH = 1286410249L
        private val setMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_msdf_size", SET_MSDF_SIZE_HASH)
        }

        private const val GET_MSDF_SIZE_HASH = 3905245786L
        private val getMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_msdf_size", GET_MSDF_SIZE_HASH)
        }

        private const val SET_FIXED_SIZE_HASH = 1286410249L
        private val setFixedSizeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_fixed_size", SET_FIXED_SIZE_HASH)
        }

        private const val GET_FIXED_SIZE_HASH = 3905245786L
        private val getFixedSizeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_fixed_size", GET_FIXED_SIZE_HASH)
        }

        private const val SET_FIXED_SIZE_SCALE_MODE_HASH = 1660989956L
        private val setFixedSizeScaleModeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_fixed_size_scale_mode", SET_FIXED_SIZE_SCALE_MODE_HASH)
        }

        private const val GET_FIXED_SIZE_SCALE_MODE_HASH = 753873478L
        private val getFixedSizeScaleModeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_fixed_size_scale_mode", GET_FIXED_SIZE_SCALE_MODE_HASH)
        }

        private const val SET_ALLOW_SYSTEM_FALLBACK_HASH = 2586408642L
        private val setAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_allow_system_fallback", SET_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val IS_ALLOW_SYSTEM_FALLBACK_HASH = 36873697L
        private val isAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "is_allow_system_fallback", IS_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val SET_FORCE_AUTOHINTER_HASH = 2586408642L
        private val setForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_force_autohinter", SET_FORCE_AUTOHINTER_HASH)
        }

        private const val IS_FORCE_AUTOHINTER_HASH = 36873697L
        private val isForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "is_force_autohinter", IS_FORCE_AUTOHINTER_HASH)
        }

        private const val SET_MODULATE_COLOR_GLYPHS_HASH = 2586408642L
        private val setModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_modulate_color_glyphs", SET_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val IS_MODULATE_COLOR_GLYPHS_HASH = 36873697L
        private val isModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "is_modulate_color_glyphs", IS_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val SET_HINTING_HASH = 1827459492L
        private val setHintingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_hinting", SET_HINTING_HASH)
        }

        private const val GET_HINTING_HASH = 3683214614L
        private val getHintingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_hinting", GET_HINTING_HASH)
        }

        private const val SET_SUBPIXEL_POSITIONING_HASH = 4225742182L
        private val setSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_subpixel_positioning", SET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val GET_SUBPIXEL_POSITIONING_HASH = 1069238588L
        private val getSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_subpixel_positioning", GET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val SET_KEEP_ROUNDING_REMAINDERS_HASH = 2586408642L
        private val setKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_keep_rounding_remainders", SET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val GET_KEEP_ROUNDING_REMAINDERS_HASH = 36873697L
        private val getKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_keep_rounding_remainders", GET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val SET_OVERSAMPLING_HASH = 373806689L
        private val setOversamplingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_oversampling", SET_OVERSAMPLING_HASH)
        }

        private const val GET_OVERSAMPLING_HASH = 1740695150L
        private val getOversamplingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_oversampling", GET_OVERSAMPLING_HASH)
        }

        private const val GET_CACHE_COUNT_HASH = 3905245786L
        private val getCacheCountBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_cache_count", GET_CACHE_COUNT_HASH)
        }

        private const val CLEAR_CACHE_HASH = 3218959716L
        private val clearCacheBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "clear_cache", CLEAR_CACHE_HASH)
        }

        private const val REMOVE_CACHE_HASH = 1286410249L
        private val removeCacheBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_cache", REMOVE_CACHE_HASH)
        }

        private const val CLEAR_SIZE_CACHE_HASH = 1286410249L
        private val clearSizeCacheBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "clear_size_cache", CLEAR_SIZE_CACHE_HASH)
        }

        private const val REMOVE_SIZE_CACHE_HASH = 2311374912L
        private val removeSizeCacheBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_size_cache", REMOVE_SIZE_CACHE_HASH)
        }

        private const val SET_EMBOLDEN_HASH = 1602489585L
        private val setEmboldenBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_embolden", SET_EMBOLDEN_HASH)
        }

        private const val GET_EMBOLDEN_HASH = 2339986948L
        private val getEmboldenBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_embolden", GET_EMBOLDEN_HASH)
        }

        private const val SET_TRANSFORM_HASH = 30160968L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3836996910L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val SET_EXTRA_SPACING_HASH = 62942285L
        private val setExtraSpacingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_extra_spacing", SET_EXTRA_SPACING_HASH)
        }

        private const val GET_EXTRA_SPACING_HASH = 1924257185L
        private val getExtraSpacingBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_extra_spacing", GET_EXTRA_SPACING_HASH)
        }

        private const val SET_EXTRA_BASELINE_OFFSET_HASH = 1602489585L
        private val setExtraBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_extra_baseline_offset", SET_EXTRA_BASELINE_OFFSET_HASH)
        }

        private const val GET_EXTRA_BASELINE_OFFSET_HASH = 2339986948L
        private val getExtraBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_extra_baseline_offset", GET_EXTRA_BASELINE_OFFSET_HASH)
        }

        private const val SET_FACE_INDEX_HASH = 3937882851L
        private val setFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_face_index", SET_FACE_INDEX_HASH)
        }

        private const val GET_FACE_INDEX_HASH = 923996154L
        private val getFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_face_index", GET_FACE_INDEX_HASH)
        }

        private const val SET_CACHE_ASCENT_HASH = 3506521499L
        private val setCacheAscentBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_cache_ascent", SET_CACHE_ASCENT_HASH)
        }

        private const val GET_CACHE_ASCENT_HASH = 3085491603L
        private val getCacheAscentBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_cache_ascent", GET_CACHE_ASCENT_HASH)
        }

        private const val SET_CACHE_DESCENT_HASH = 3506521499L
        private val setCacheDescentBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_cache_descent", SET_CACHE_DESCENT_HASH)
        }

        private const val GET_CACHE_DESCENT_HASH = 3085491603L
        private val getCacheDescentBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_cache_descent", GET_CACHE_DESCENT_HASH)
        }

        private const val SET_CACHE_UNDERLINE_POSITION_HASH = 3506521499L
        private val setCacheUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_cache_underline_position", SET_CACHE_UNDERLINE_POSITION_HASH)
        }

        private const val GET_CACHE_UNDERLINE_POSITION_HASH = 3085491603L
        private val getCacheUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_cache_underline_position", GET_CACHE_UNDERLINE_POSITION_HASH)
        }

        private const val SET_CACHE_UNDERLINE_THICKNESS_HASH = 3506521499L
        private val setCacheUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_cache_underline_thickness", SET_CACHE_UNDERLINE_THICKNESS_HASH)
        }

        private const val GET_CACHE_UNDERLINE_THICKNESS_HASH = 3085491603L
        private val getCacheUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_cache_underline_thickness", GET_CACHE_UNDERLINE_THICKNESS_HASH)
        }

        private const val SET_CACHE_SCALE_HASH = 3506521499L
        private val setCacheScaleBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_cache_scale", SET_CACHE_SCALE_HASH)
        }

        private const val GET_CACHE_SCALE_HASH = 3085491603L
        private val getCacheScaleBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_cache_scale", GET_CACHE_SCALE_HASH)
        }

        private const val GET_TEXTURE_COUNT_HASH = 1987661582L
        private val getTextureCountBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_texture_count", GET_TEXTURE_COUNT_HASH)
        }

        private const val CLEAR_TEXTURES_HASH = 2311374912L
        private val clearTexturesBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "clear_textures", CLEAR_TEXTURES_HASH)
        }

        private const val REMOVE_TEXTURE_HASH = 2328951467L
        private val removeTextureBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_texture", REMOVE_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_IMAGE_HASH = 4157974066L
        private val setTextureImageBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_texture_image", SET_TEXTURE_IMAGE_HASH)
        }

        private const val GET_TEXTURE_IMAGE_HASH = 3878418953L
        private val getTextureImageBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_texture_image", GET_TEXTURE_IMAGE_HASH)
        }

        private const val CLEAR_GLYPHS_HASH = 2311374912L
        private val clearGlyphsBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "clear_glyphs", CLEAR_GLYPHS_HASH)
        }

        private const val REMOVE_GLYPH_HASH = 2328951467L
        private val removeGlyphBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_glyph", REMOVE_GLYPH_HASH)
        }

        private const val SET_GLYPH_ADVANCE_HASH = 947991729L
        private val setGlyphAdvanceBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_glyph_advance", SET_GLYPH_ADVANCE_HASH)
        }

        private const val GET_GLYPH_ADVANCE_HASH = 1601573536L
        private val getGlyphAdvanceBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_glyph_advance", GET_GLYPH_ADVANCE_HASH)
        }

        private const val SET_GLYPH_OFFSET_HASH = 921719850L
        private val setGlyphOffsetBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_glyph_offset", SET_GLYPH_OFFSET_HASH)
        }

        private const val GET_GLYPH_OFFSET_HASH = 3205412300L
        private val getGlyphOffsetBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_glyph_offset", GET_GLYPH_OFFSET_HASH)
        }

        private const val SET_GLYPH_SIZE_HASH = 921719850L
        private val setGlyphSizeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_glyph_size", SET_GLYPH_SIZE_HASH)
        }

        private const val GET_GLYPH_SIZE_HASH = 3205412300L
        private val getGlyphSizeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_glyph_size", GET_GLYPH_SIZE_HASH)
        }

        private const val SET_GLYPH_UV_RECT_HASH = 3821620992L
        private val setGlyphUvRectBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_glyph_uv_rect", SET_GLYPH_UV_RECT_HASH)
        }

        private const val GET_GLYPH_UV_RECT_HASH = 3927917900L
        private val getGlyphUvRectBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_glyph_uv_rect", GET_GLYPH_UV_RECT_HASH)
        }

        private const val SET_GLYPH_TEXTURE_IDX_HASH = 355564111L
        private val setGlyphTextureIdxBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_glyph_texture_idx", SET_GLYPH_TEXTURE_IDX_HASH)
        }

        private const val GET_GLYPH_TEXTURE_IDX_HASH = 1629411054L
        private val getGlyphTextureIdxBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_glyph_texture_idx", GET_GLYPH_TEXTURE_IDX_HASH)
        }

        private const val CLEAR_KERNING_MAP_HASH = 3937882851L
        private val clearKerningMapBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "clear_kerning_map", CLEAR_KERNING_MAP_HASH)
        }

        private const val REMOVE_KERNING_HASH = 3930204747L
        private val removeKerningBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_kerning", REMOVE_KERNING_HASH)
        }

        private const val SET_KERNING_HASH = 3182200918L
        private val setKerningBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_kerning", SET_KERNING_HASH)
        }

        private const val GET_KERNING_HASH = 1611912865L
        private val getKerningBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_kerning", GET_KERNING_HASH)
        }

        private const val RENDER_RANGE_HASH = 355564111L
        private val renderRangeBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "render_range", RENDER_RANGE_HASH)
        }

        private const val RENDER_GLYPH_HASH = 2328951467L
        private val renderGlyphBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "render_glyph", RENDER_GLYPH_HASH)
        }

        private const val SET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2678287736L
        private val setLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_language_support_override", SET_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val GET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 3927539163L
        private val getLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_language_support_override", GET_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH = 83702148L
        private val removeLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_language_support_override", REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val GET_LANGUAGE_SUPPORT_OVERRIDES_HASH = 1139954409L
        private val getLanguageSupportOverridesBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_language_support_overrides", GET_LANGUAGE_SUPPORT_OVERRIDES_HASH)
        }

        private const val SET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2678287736L
        private val setScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "set_script_support_override", SET_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val GET_SCRIPT_SUPPORT_OVERRIDE_HASH = 3927539163L
        private val getScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_script_support_override", GET_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH = 83702148L
        private val removeScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "remove_script_support_override", REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val GET_SCRIPT_SUPPORT_OVERRIDES_HASH = 1139954409L
        private val getScriptSupportOverridesBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_script_support_overrides", GET_SCRIPT_SUPPORT_OVERRIDES_HASH)
        }

        private const val GET_GLYPH_INDEX_HASH = 864943070L
        private val getGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_glyph_index", GET_GLYPH_INDEX_HASH)
        }

        private const val GET_CHAR_FROM_GLYPH_INDEX_HASH = 3175239445L
        private val getCharFromGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("FontFile", "get_char_from_glyph_index", GET_CHAR_FROM_GLYPH_INDEX_HASH)
        }
    }
}

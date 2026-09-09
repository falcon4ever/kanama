package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: TextServer
 */
open class TextServer(handle: MemorySegment) : RefCounted(handle) {
    fun hasFeature(feature: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, handle, feature)
    }

    fun getName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    fun getFeatures(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFeaturesBind, handle)
    }

    fun loadSupportData(filename: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(loadSupportDataBind, handle, filename)
    }

    fun getSupportDataFilename(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSupportDataFilenameBind, handle)
    }

    fun getSupportDataInfo(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSupportDataInfoBind, handle)
    }

    fun saveSupportData(filename: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(saveSupportDataBind, handle, filename)
    }

    fun isLocaleUsingSupportData(locale: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isLocaleUsingSupportDataBind, handle, locale)
    }

    fun isLocaleRightToLeft(locale: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isLocaleRightToLeftBind, handle, locale)
    }

    fun nameToTag(name: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(nameToTagBind, handle, name)
    }

    fun has(rid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(hasBind, handle, rid)
    }

    fun freeRid(rid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, handle, rid)
    }

    fun createFont(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(createFontBind, handle)
    }

    fun createFontLinkedVariation(fontRid: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(createFontLinkedVariationBind, handle, fontRid)
    }

    fun fontSetFaceIndex(fontRid: RID, faceIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetFaceIndexBind, handle, fontRid, faceIndex)
    }

    fun fontGetFaceIndex(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFaceIndexBind, handle, fontRid)
    }

    fun fontGetFaceCount(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFaceCountBind, handle, fontRid)
    }

    fun fontSetStyle(fontRid: RID, style: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetStyleBind, handle, fontRid, style)
    }

    fun fontGetStyle(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetStyleBind, handle, fontRid)
    }

    fun fontSetName(fontRid: RID, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(fontSetNameBind, handle, fontRid, name)
    }

    fun fontSetStyleName(fontRid: RID, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(fontSetStyleNameBind, handle, fontRid, name)
    }

    fun fontSetWeight(fontRid: RID, weight: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetWeightBind, handle, fontRid, weight)
    }

    fun fontGetWeight(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetWeightBind, handle, fontRid)
    }

    fun fontSetStretch(fontRid: RID, weight: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetStretchBind, handle, fontRid, weight)
    }

    fun fontGetStretch(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetStretchBind, handle, fontRid)
    }

    fun fontSetAntialiasing(fontRid: RID, antialiasing: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetAntialiasingBind, handle, fontRid, antialiasing)
    }

    fun fontGetAntialiasing(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetAntialiasingBind, handle, fontRid)
    }

    fun fontSetDisableEmbeddedBitmaps(fontRid: RID, disableEmbeddedBitmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetDisableEmbeddedBitmapsBind, handle, fontRid, disableEmbeddedBitmaps)
    }

    fun fontGetDisableEmbeddedBitmaps(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontGetDisableEmbeddedBitmapsBind, handle, fontRid)
    }

    fun fontSetGenerateMipmaps(fontRid: RID, generateMipmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetGenerateMipmapsBind, handle, fontRid, generateMipmaps)
    }

    fun fontGetGenerateMipmaps(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontGetGenerateMipmapsBind, handle, fontRid)
    }

    fun fontSetMultichannelSignedDistanceField(fontRid: RID, msdf: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetMultichannelSignedDistanceFieldBind, handle, fontRid, msdf)
    }

    fun fontIsMultichannelSignedDistanceField(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsMultichannelSignedDistanceFieldBind, handle, fontRid)
    }

    fun fontSetMsdfPixelRange(fontRid: RID, msdfPixelRange: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetMsdfPixelRangeBind, handle, fontRid, msdfPixelRange)
    }

    fun fontGetMsdfPixelRange(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetMsdfPixelRangeBind, handle, fontRid)
    }

    fun fontSetMsdfSize(fontRid: RID, msdfSize: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetMsdfSizeBind, handle, fontRid, msdfSize)
    }

    fun fontGetMsdfSize(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetMsdfSizeBind, handle, fontRid)
    }

    fun fontSetFixedSize(fontRid: RID, fixedSize: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetFixedSizeBind, handle, fontRid, fixedSize)
    }

    fun fontGetFixedSize(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFixedSizeBind, handle, fontRid)
    }

    fun fontSetFixedSizeScaleMode(fontRid: RID, fixedSizeScaleMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetFixedSizeScaleModeBind, handle, fontRid, fixedSizeScaleMode)
    }

    fun fontGetFixedSizeScaleMode(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFixedSizeScaleModeBind, handle, fontRid)
    }

    fun fontSetAllowSystemFallback(fontRid: RID, allowSystemFallback: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetAllowSystemFallbackBind, handle, fontRid, allowSystemFallback)
    }

    fun fontIsAllowSystemFallback(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsAllowSystemFallbackBind, handle, fontRid)
    }

    fun fontClearSystemFallbackCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(fontClearSystemFallbackCacheBind, handle)
    }

    fun fontSetForceAutohinter(fontRid: RID, forceAutohinter: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetForceAutohinterBind, handle, fontRid, forceAutohinter)
    }

    fun fontIsForceAutohinter(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsForceAutohinterBind, handle, fontRid)
    }

    fun fontSetModulateColorGlyphs(fontRid: RID, modulate: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetModulateColorGlyphsBind, handle, fontRid, modulate)
    }

    fun fontIsModulateColorGlyphs(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsModulateColorGlyphsBind, handle, fontRid)
    }

    fun fontGetPaletteCount(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetPaletteCountBind, handle, fontRid)
    }

    fun fontGetUsedPalette(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetUsedPaletteBind, handle, fontRid)
    }

    fun fontSetUsedPalette(fontRid: RID, index: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetUsedPaletteBind, handle, fontRid, index)
    }

    fun fontSetHinting(fontRid: RID, hinting: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetHintingBind, handle, fontRid, hinting)
    }

    fun fontGetHinting(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetHintingBind, handle, fontRid)
    }

    fun fontSetSubpixelPositioning(fontRid: RID, subpixelPositioning: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetSubpixelPositioningBind, handle, fontRid, subpixelPositioning)
    }

    fun fontGetSubpixelPositioning(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetSubpixelPositioningBind, handle, fontRid)
    }

    fun fontSetKeepRoundingRemainders(fontRid: RID, keepRoundingRemainders: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetKeepRoundingRemaindersBind, handle, fontRid, keepRoundingRemainders)
    }

    fun fontGetKeepRoundingRemainders(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontGetKeepRoundingRemaindersBind, handle, fontRid)
    }

    fun fontSetEmbolden(fontRid: RID, strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDoubleArg(fontSetEmboldenBind, handle, fontRid, strength)
    }

    fun fontGetEmbolden(fontRid: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(fontGetEmboldenBind, handle, fontRid)
    }

    fun fontSetSpacing(fontRid: RID, spacing: Long, value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(fontSetSpacingBind, handle, fontRid, spacing, value)
    }

    fun fontGetSpacing(fontRid: RID, spacing: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(fontGetSpacingBind, handle, fontRid, spacing)
    }

    fun fontSetBaselineOffset(fontRid: RID, baselineOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDoubleArg(fontSetBaselineOffsetBind, handle, fontRid, baselineOffset)
    }

    fun fontGetBaselineOffset(fontRid: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(fontGetBaselineOffsetBind, handle, fontRid)
    }

    fun fontSetTransform(fontRid: RID, transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(fontSetTransformBind, handle, fontRid, transform)
    }

    fun fontGetTransform(fontRid: RID): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetTransform2D(fontGetTransformBind, handle, fontRid)
    }

    fun fontSetOversampling(fontRid: RID, oversampling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDoubleArg(fontSetOversamplingBind, handle, fontRid, oversampling)
    }

    fun fontGetOversampling(fontRid: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(fontGetOversamplingBind, handle, fontRid)
    }

    fun fontClearSizeCache(fontRid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(fontClearSizeCacheBind, handle, fontRid)
    }

    fun fontRemoveSizeCache(fontRid: RID, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndVector2iArg(fontRemoveSizeCacheBind, handle, fontRid, size)
    }

    fun fontSetAscent(fontRid: RID, size: Long, ascent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetAscentBind, handle, fontRid, size, ascent)
    }

    fun fontGetAscent(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetAscentBind, handle, fontRid, size)
    }

    fun fontSetDescent(fontRid: RID, size: Long, descent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetDescentBind, handle, fontRid, size, descent)
    }

    fun fontGetDescent(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetDescentBind, handle, fontRid, size)
    }

    fun fontSetUnderlinePosition(fontRid: RID, size: Long, underlinePosition: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetUnderlinePositionBind, handle, fontRid, size, underlinePosition)
    }

    fun fontGetUnderlinePosition(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetUnderlinePositionBind, handle, fontRid, size)
    }

    fun fontSetUnderlineThickness(fontRid: RID, size: Long, underlineThickness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetUnderlineThicknessBind, handle, fontRid, size, underlineThickness)
    }

    fun fontGetUnderlineThickness(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetUnderlineThicknessBind, handle, fontRid, size)
    }

    fun fontSetScale(fontRid: RID, size: Long, scale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetScaleBind, handle, fontRid, size, scale)
    }

    fun fontGetScale(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetScaleBind, handle, fontRid, size)
    }

    fun fontGetTextureCount(fontRid: RID, size: Vector2i): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVector2iArgRetLong(fontGetTextureCountBind, handle, fontRid, size)
    }

    fun fontClearTextures(fontRid: RID, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndVector2iArg(fontClearTexturesBind, handle, fontRid, size)
    }

    fun fontRemoveTexture(fontRid: RID, size: Vector2i, textureIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(fontRemoveTextureBind, handle, fontRid, size, textureIndex)
    }

    fun fontSetTextureImage(fontRid: RID, size: Vector2i, textureIndex: Long, image: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongObjectArgs(fontSetTextureImageBind, handle, fontRid, size, textureIndex, image?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun fontGetTextureImage(fontRid: RID, size: Vector2i, textureIndex: Long): Image? {
        checkOpen()
        return Image.wrap(ObjectCalls.ptrcallWithRIDVector2iLongArgsRetObject(fontGetTextureImageBind, handle, fontRid, size, textureIndex))
    }

    fun fontClearGlyphs(fontRid: RID, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndVector2iArg(fontClearGlyphsBind, handle, fontRid, size)
    }

    fun fontRemoveGlyph(fontRid: RID, size: Vector2i, glyph: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(fontRemoveGlyphBind, handle, fontRid, size, glyph)
    }

    fun fontGetGlyphAdvance(fontRid: RID, size: Long, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetVector2(fontGetGlyphAdvanceBind, handle, fontRid, size, glyph)
    }

    fun fontSetGlyphAdvance(fontRid: RID, size: Long, glyph: Long, advance: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDTwoLongAndVector2Args(fontSetGlyphAdvanceBind, handle, fontRid, size, glyph, advance)
    }

    fun fontGetGlyphOffset(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(fontGetGlyphOffsetBind, handle, fontRid, size, glyph)
    }

    fun fontSetGlyphOffset(fontRid: RID, size: Vector2i, glyph: Long, offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(fontSetGlyphOffsetBind, handle, fontRid, size, glyph, offset)
    }

    fun fontGetGlyphSize(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(fontGetGlyphSizeBind, handle, fontRid, size, glyph)
    }

    fun fontSetGlyphSize(fontRid: RID, size: Vector2i, glyph: Long, glSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(fontSetGlyphSizeBind, handle, fontRid, size, glyph, glSize)
    }

    fun fontGetGlyphUvRect(fontRid: RID, size: Vector2i, glyph: Long): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRect2(fontGetGlyphUvRectBind, handle, fontRid, size, glyph)
    }

    fun fontSetGlyphUvRect(fontRid: RID, size: Vector2i, glyph: Long, uvRect: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongRect2Args(fontSetGlyphUvRectBind, handle, fontRid, size, glyph, uvRect)
    }

    fun fontGetGlyphTextureIdx(fontRid: RID, size: Vector2i, glyph: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetLong(fontGetGlyphTextureIdxBind, handle, fontRid, size, glyph)
    }

    fun fontSetGlyphTextureIdx(fontRid: RID, size: Vector2i, glyph: Long, textureIdx: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(fontSetGlyphTextureIdxBind, handle, fontRid, size, glyph, textureIdx)
    }

    fun fontGetGlyphTextureRid(fontRid: RID, size: Vector2i, glyph: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRID(fontGetGlyphTextureRidBind, handle, fontRid, size, glyph)
    }

    fun fontGetGlyphTextureSize(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(fontGetGlyphTextureSizeBind, handle, fontRid, size, glyph)
    }

    fun fontClearKerningMap(fontRid: RID, size: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(fontClearKerningMapBind, handle, fontRid, size)
    }

    fun fontRemoveKerning(fontRid: RID, size: Long, glyphPair: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongVector2iArgs(fontRemoveKerningBind, handle, fontRid, size, glyphPair)
    }

    fun fontSetKerning(fontRid: RID, size: Long, glyphPair: Vector2i, kerning: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongVector2iAndVector2Args(fontSetKerningBind, handle, fontRid, size, glyphPair, kerning)
    }

    fun fontGetKerning(fontRid: RID, size: Long, glyphPair: Vector2i): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDLongVector2iArgsRetVector2(fontGetKerningBind, handle, fontRid, size, glyphPair)
    }

    fun fontGetGlyphIndex(fontRid: RID, size: Long, char: Long, variationSelector: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndThreeLongArgsRetLong(fontGetGlyphIndexBind, handle, fontRid, size, char, variationSelector)
    }

    fun fontGetCharFromGlyphIndex(fontRid: RID, size: Long, glyphIndex: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(fontGetCharFromGlyphIndexBind, handle, fontRid, size, glyphIndex)
    }

    fun fontHasChar(fontRid: RID, char: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(fontHasCharBind, handle, fontRid, char)
    }

    fun fontRenderRange(fontRid: RID, size: Vector2i, start: Long, end: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(fontRenderRangeBind, handle, fontRid, size, start, end)
    }

    fun fontRenderGlyph(fontRid: RID, size: Vector2i, index: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(fontRenderGlyphBind, handle, fontRid, size, index)
    }

    fun fontDrawGlyph(fontRid: RID, canvas: RID, size: Long, pos: Vector2, index: Long, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDLongVector2LongColorDoubleArgs(fontDrawGlyphBind, handle, fontRid, canvas, size, pos, index, color, oversampling)
    }

    fun fontDrawGlyphOutline(fontRid: RID, canvas: RID, size: Long, outlineSize: Long, pos: Vector2, index: Long, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDTwoLongVector2LongColorDoubleArgs(fontDrawGlyphOutlineBind, handle, fontRid, canvas, size, outlineSize, pos, index, color, oversampling)
    }

    fun fontIsLanguageSupported(fontRid: RID, language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontIsLanguageSupportedBind, handle, fontRid, language)
    }

    fun fontSetLanguageSupportOverride(fontRid: RID, language: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDStringAndBoolArgs(fontSetLanguageSupportOverrideBind, handle, fontRid, language, supported)
    }

    fun fontGetLanguageSupportOverride(fontRid: RID, language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontGetLanguageSupportOverrideBind, handle, fontRid, language)
    }

    fun fontRemoveLanguageSupportOverride(fontRid: RID, language: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(fontRemoveLanguageSupportOverrideBind, handle, fontRid, language)
    }

    fun fontIsScriptSupported(fontRid: RID, script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontIsScriptSupportedBind, handle, fontRid, script)
    }

    fun fontSetScriptSupportOverride(fontRid: RID, script: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDStringAndBoolArgs(fontSetScriptSupportOverrideBind, handle, fontRid, script, supported)
    }

    fun fontGetScriptSupportOverride(fontRid: RID, script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontGetScriptSupportOverrideBind, handle, fontRid, script)
    }

    fun fontRemoveScriptSupportOverride(fontRid: RID, script: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(fontRemoveScriptSupportOverrideBind, handle, fontRid, script)
    }

    fun fontGetGlobalOversampling(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(fontGetGlobalOversamplingBind, handle)
    }

    fun fontSetGlobalOversampling(oversampling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(fontSetGlobalOversamplingBind, handle, oversampling)
    }

    fun getHexCodeBoxSize(size: Long, index: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector2(getHexCodeBoxSizeBind, handle, size, index)
    }

    fun drawHexCodeBox(canvas: RID, size: Long, pos: Vector2, index: Long, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongVector2LongColorArgs(drawHexCodeBoxBind, handle, canvas, size, pos, index, color)
    }

    fun createShapedText(direction: Long = 0L, orientation: Long = 0L): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetRID(createShapedTextBind, handle, direction, orientation)
    }

    fun shapedTextClear(rid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(shapedTextClearBind, handle, rid)
    }

    fun shapedTextDuplicate(rid: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(shapedTextDuplicateBind, handle, rid)
    }

    fun shapedTextSetDirection(shaped: RID, direction: Long = 0L) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(shapedTextSetDirectionBind, handle, shaped, direction)
    }

    fun shapedTextGetDirection(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetDirectionBind, handle, shaped)
    }

    fun shapedTextGetInferredDirection(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetInferredDirectionBind, handle, shaped)
    }

    fun shapedTextSetCustomPunctuation(shaped: RID, punct: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(shapedTextSetCustomPunctuationBind, handle, shaped, punct)
    }

    fun shapedTextSetCustomEllipsis(shaped: RID, char: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(shapedTextSetCustomEllipsisBind, handle, shaped, char)
    }

    fun shapedTextGetCustomEllipsis(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetCustomEllipsisBind, handle, shaped)
    }

    fun shapedTextSetOrientation(shaped: RID, orientation: Long = 0L) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(shapedTextSetOrientationBind, handle, shaped, orientation)
    }

    fun shapedTextGetOrientation(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetOrientationBind, handle, shaped)
    }

    fun shapedTextSetPreserveInvalid(shaped: RID, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(shapedTextSetPreserveInvalidBind, handle, shaped, enabled)
    }

    fun shapedTextGetPreserveInvalid(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextGetPreserveInvalidBind, handle, shaped)
    }

    fun shapedTextSetPreserveControl(shaped: RID, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(shapedTextSetPreserveControlBind, handle, shaped, enabled)
    }

    fun shapedTextGetPreserveControl(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextGetPreserveControlBind, handle, shaped)
    }

    fun shapedTextSetSpacing(shaped: RID, spacing: Long, value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(shapedTextSetSpacingBind, handle, shaped, spacing, value)
    }

    fun shapedTextGetSpacing(shaped: RID, spacing: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextGetSpacingBind, handle, shaped, spacing)
    }

    fun shapedGetSpanCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedGetSpanCountBind, handle, shaped)
    }

    fun shapedGetRunCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedGetRunCountBind, handle, shaped)
    }

    fun shapedGetRunRange(shaped: RID, index: Long): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(shapedGetRunRangeBind, handle, shaped, index)
    }

    fun shapedGetRunGlyphRange(shaped: RID, index: Long): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(shapedGetRunGlyphRangeBind, handle, shaped, index)
    }

    fun shapedGetRunFontRid(shaped: RID, index: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(shapedGetRunFontRidBind, handle, shaped, index)
    }

    fun shapedGetRunFontSize(shaped: RID, index: Long): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetInt(shapedGetRunFontSizeBind, handle, shaped, index)
    }

    fun shapedGetRunDirection(shaped: RID, index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedGetRunDirectionBind, handle, shaped, index)
    }

    fun shapedTextSubstr(shaped: RID, start: Long, length: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(shapedTextSubstrBind, handle, shaped, start, length)
    }

    fun shapedTextGetParent(shaped: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(shapedTextGetParentBind, handle, shaped)
    }

    fun shapedTextFitToWidth(shaped: RID, width: Double, justificationFlags: Long = 3L): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDDoubleAndLongArgsRetDouble(shapedTextFitToWidthBind, handle, shaped, width, justificationFlags)
    }

    fun shapedTextShape(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextShapeBind, handle, shaped)
    }

    fun shapedTextIsReady(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextIsReadyBind, handle, shaped)
    }

    fun shapedTextHasVisibleChars(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextHasVisibleCharsBind, handle, shaped)
    }

    fun shapedTextGetGlyphCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetGlyphCountBind, handle, shaped)
    }

    fun shapedTextGetRange(shaped: RID): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(shapedTextGetRangeBind, handle, shaped)
    }

    fun shapedTextGetTrimPos(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetTrimPosBind, handle, shaped)
    }

    fun shapedTextGetEllipsisPos(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetEllipsisPosBind, handle, shaped)
    }

    fun shapedTextGetEllipsisGlyphCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetEllipsisGlyphCountBind, handle, shaped)
    }

    fun shapedTextOverrunTrimToWidth(shaped: RID, width: Double = 0.0, overrunTrimFlags: Long = 0L) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDDoubleAndLongArgs(shapedTextOverrunTrimToWidthBind, handle, shaped, width, overrunTrimFlags)
    }

    fun shapedTextGetSize(shaped: RID): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetVector2(shapedTextGetSizeBind, handle, shaped)
    }

    fun shapedTextGetAscent(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetAscentBind, handle, shaped)
    }

    fun shapedTextGetDescent(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetDescentBind, handle, shaped)
    }

    fun shapedTextGetWidth(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetWidthBind, handle, shaped)
    }

    fun shapedTextGetUnderlinePosition(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetUnderlinePositionBind, handle, shaped)
    }

    fun shapedTextGetUnderlineThickness(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetUnderlineThicknessBind, handle, shaped)
    }

    fun shapedTextHitTestGrapheme(shaped: RID, coords: Double): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(shapedTextHitTestGraphemeBind, handle, shaped, coords)
    }

    fun shapedTextHitTestPosition(shaped: RID, coords: Double): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(shapedTextHitTestPositionBind, handle, shaped, coords)
    }

    fun shapedTextGetGraphemeBounds(shaped: RID, pos: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2(shapedTextGetGraphemeBoundsBind, handle, shaped, pos)
    }

    fun shapedTextNextGraphemePos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextNextGraphemePosBind, handle, shaped, pos)
    }

    fun shapedTextPrevGraphemePos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextPrevGraphemePosBind, handle, shaped, pos)
    }

    fun shapedTextNextCharacterPos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextNextCharacterPosBind, handle, shaped, pos)
    }

    fun shapedTextPrevCharacterPos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextPrevCharacterPosBind, handle, shaped, pos)
    }

    fun shapedTextClosestCharacterPos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextClosestCharacterPosBind, handle, shaped, pos)
    }

    fun shapedTextDraw(shaped: RID, canvas: RID, pos: Vector2, clipL: Double = -1.0, clipR: Double = -1.0, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleColorDoubleArgs(shapedTextDrawBind, handle, shaped, canvas, pos, clipL, clipR, color, oversampling)
    }

    fun shapedTextDrawOutline(shaped: RID, canvas: RID, pos: Vector2, clipL: Double = -1.0, clipR: Double = -1.0, outlineSize: Long = 1L, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleLongColorDoubleArgs(shapedTextDrawOutlineBind, handle, shaped, canvas, pos, clipL, clipR, outlineSize, color, oversampling)
    }

    fun shapedTextGetDominantDirectionInRange(shaped: RID, start: Long, end: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(shapedTextGetDominantDirectionInRangeBind, handle, shaped, start, end)
    }

    fun percentSign(language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(percentSignBind, handle, language)
    }

    fun spoofCheck(string: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(spoofCheckBind, handle, string)
    }

    fun stripDiacritics(string: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(stripDiacriticsBind, handle, string)
    }

    fun isValidIdentifier(string: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isValidIdentifierBind, handle, string)
    }

    fun isValidLetter(unicode: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(isValidLetterBind, handle, unicode)
    }

    companion object {
        const val FONT_ANTIALIASING_NONE: Long = 0L
        const val FONT_ANTIALIASING_GRAY: Long = 1L
        const val FONT_ANTIALIASING_LCD: Long = 2L
        const val FONT_LCD_SUBPIXEL_LAYOUT_NONE: Long = 0L
        const val FONT_LCD_SUBPIXEL_LAYOUT_HRGB: Long = 1L
        const val FONT_LCD_SUBPIXEL_LAYOUT_HBGR: Long = 2L
        const val FONT_LCD_SUBPIXEL_LAYOUT_VRGB: Long = 3L
        const val FONT_LCD_SUBPIXEL_LAYOUT_VBGR: Long = 4L
        const val FONT_LCD_SUBPIXEL_LAYOUT_MAX: Long = 5L
        const val DIRECTION_AUTO: Long = 0L
        const val DIRECTION_LTR: Long = 1L
        const val DIRECTION_RTL: Long = 2L
        const val DIRECTION_INHERITED: Long = 3L
        const val ORIENTATION_HORIZONTAL: Long = 0L
        const val ORIENTATION_VERTICAL: Long = 1L
        const val JUSTIFICATION_NONE: Long = 0L
        const val JUSTIFICATION_KASHIDA: Long = 1L
        const val JUSTIFICATION_WORD_BOUND: Long = 2L
        const val JUSTIFICATION_TRIM_EDGE_SPACES: Long = 4L
        const val JUSTIFICATION_AFTER_LAST_TAB: Long = 8L
        const val JUSTIFICATION_CONSTRAIN_ELLIPSIS: Long = 16L
        const val JUSTIFICATION_SKIP_LAST_LINE: Long = 32L
        const val JUSTIFICATION_SKIP_LAST_LINE_WITH_VISIBLE_CHARS: Long = 64L
        const val JUSTIFICATION_DO_NOT_SKIP_SINGLE_LINE: Long = 128L
        const val AUTOWRAP_OFF: Long = 0L
        const val AUTOWRAP_ARBITRARY: Long = 1L
        const val AUTOWRAP_WORD: Long = 2L
        const val AUTOWRAP_WORD_SMART: Long = 3L
        const val BREAK_NONE: Long = 0L
        const val BREAK_MANDATORY: Long = 1L
        const val BREAK_WORD_BOUND: Long = 2L
        const val BREAK_GRAPHEME_BOUND: Long = 4L
        const val BREAK_ADAPTIVE: Long = 8L
        const val BREAK_TRIM_EDGE_SPACES: Long = 16L
        const val BREAK_TRIM_INDENT: Long = 32L
        const val BREAK_TRIM_START_EDGE_SPACES: Long = 64L
        const val BREAK_TRIM_END_EDGE_SPACES: Long = 128L
        const val VC_CHARS_BEFORE_SHAPING: Long = 0L
        const val VC_CHARS_AFTER_SHAPING: Long = 1L
        const val VC_GLYPHS_AUTO: Long = 2L
        const val VC_GLYPHS_LTR: Long = 3L
        const val VC_GLYPHS_RTL: Long = 4L
        const val OVERRUN_NO_TRIMMING: Long = 0L
        const val OVERRUN_TRIM_CHAR: Long = 1L
        const val OVERRUN_TRIM_WORD: Long = 2L
        const val OVERRUN_TRIM_ELLIPSIS: Long = 3L
        const val OVERRUN_TRIM_WORD_ELLIPSIS: Long = 4L
        const val OVERRUN_TRIM_ELLIPSIS_FORCE: Long = 5L
        const val OVERRUN_TRIM_WORD_ELLIPSIS_FORCE: Long = 6L
        const val OVERRUN_NO_TRIM: Long = 0L
        const val OVERRUN_TRIM: Long = 1L
        const val OVERRUN_TRIM_WORD_ONLY: Long = 2L
        const val OVERRUN_ADD_ELLIPSIS: Long = 4L
        const val OVERRUN_ENFORCE_ELLIPSIS: Long = 8L
        const val OVERRUN_JUSTIFICATION_AWARE: Long = 16L
        const val OVERRUN_SHORT_STRING_ELLIPSIS: Long = 32L
        const val GRAPHEME_IS_VALID: Long = 1L
        const val GRAPHEME_IS_RTL: Long = 2L
        const val GRAPHEME_IS_VIRTUAL: Long = 4L
        const val GRAPHEME_IS_SPACE: Long = 8L
        const val GRAPHEME_IS_BREAK_HARD: Long = 16L
        const val GRAPHEME_IS_BREAK_SOFT: Long = 32L
        const val GRAPHEME_IS_TAB: Long = 64L
        const val GRAPHEME_IS_ELONGATION: Long = 128L
        const val GRAPHEME_IS_PUNCTUATION: Long = 256L
        const val GRAPHEME_IS_UNDERSCORE: Long = 512L
        const val GRAPHEME_IS_CONNECTED: Long = 1024L
        const val GRAPHEME_IS_SAFE_TO_INSERT_TATWEEL: Long = 2048L
        const val GRAPHEME_IS_EMBEDDED_OBJECT: Long = 4096L
        const val GRAPHEME_IS_SOFT_HYPHEN: Long = 8192L
        const val HINTING_NONE: Long = 0L
        const val HINTING_LIGHT: Long = 1L
        const val HINTING_NORMAL: Long = 2L
        const val SUBPIXEL_POSITIONING_DISABLED: Long = 0L
        const val SUBPIXEL_POSITIONING_AUTO: Long = 1L
        const val SUBPIXEL_POSITIONING_ONE_HALF: Long = 2L
        const val SUBPIXEL_POSITIONING_ONE_QUARTER: Long = 3L
        const val SUBPIXEL_POSITIONING_ONE_HALF_MAX_SIZE: Long = 20L
        const val SUBPIXEL_POSITIONING_ONE_QUARTER_MAX_SIZE: Long = 16L
        const val FEATURE_SIMPLE_LAYOUT: Long = 1L
        const val FEATURE_BIDI_LAYOUT: Long = 2L
        const val FEATURE_VERTICAL_LAYOUT: Long = 4L
        const val FEATURE_SHAPING: Long = 8L
        const val FEATURE_KASHIDA_JUSTIFICATION: Long = 16L
        const val FEATURE_BREAK_ITERATORS: Long = 32L
        const val FEATURE_FONT_BITMAP: Long = 64L
        const val FEATURE_FONT_DYNAMIC: Long = 128L
        const val FEATURE_FONT_MSDF: Long = 256L
        const val FEATURE_FONT_SYSTEM: Long = 512L
        const val FEATURE_FONT_VARIABLE: Long = 1024L
        const val FEATURE_CONTEXT_SENSITIVE_CASE_CONVERSION: Long = 2048L
        const val FEATURE_USE_SUPPORT_DATA: Long = 4096L
        const val FEATURE_UNICODE_IDENTIFIERS: Long = 8192L
        const val FEATURE_UNICODE_SECURITY: Long = 16384L
        const val CONTOUR_CURVE_TAG_ON: Long = 1L
        const val CONTOUR_CURVE_TAG_OFF_CONIC: Long = 0L
        const val CONTOUR_CURVE_TAG_OFF_CUBIC: Long = 2L
        const val SPACING_GLYPH: Long = 0L
        const val SPACING_SPACE: Long = 1L
        const val SPACING_TOP: Long = 2L
        const val SPACING_BOTTOM: Long = 3L
        const val SPACING_MAX: Long = 4L
        const val FONT_BOLD: Long = 1L
        const val FONT_ITALIC: Long = 2L
        const val FONT_FIXED_WIDTH: Long = 4L
        const val STRUCTURED_TEXT_DEFAULT: Long = 0L
        const val STRUCTURED_TEXT_URI: Long = 1L
        const val STRUCTURED_TEXT_FILE: Long = 2L
        const val STRUCTURED_TEXT_EMAIL: Long = 3L
        const val STRUCTURED_TEXT_LIST: Long = 4L
        const val STRUCTURED_TEXT_GDSCRIPT: Long = 5L
        const val STRUCTURED_TEXT_CUSTOM: Long = 6L
        const val FIXED_SIZE_SCALE_DISABLE: Long = 0L
        const val FIXED_SIZE_SCALE_INTEGER_ONLY: Long = 1L
        const val FIXED_SIZE_SCALE_ENABLED: Long = 2L

        fun fromHandle(handle: MemorySegment): TextServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextServer? =
            if (handle.address() == 0L) null else TextServer(handle)

        private const val HAS_FEATURE_HASH = 3967367083L
        private val hasFeatureBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "has_feature", HAS_FEATURE_HASH)
        }

        private const val GET_NAME_HASH = 201670096L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_name", GET_NAME_HASH)
        }

        private const val GET_FEATURES_HASH = 3905245786L
        private val getFeaturesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_features", GET_FEATURES_HASH)
        }

        private const val LOAD_SUPPORT_DATA_HASH = 2323990056L
        private val loadSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "load_support_data", LOAD_SUPPORT_DATA_HASH)
        }

        private const val GET_SUPPORT_DATA_FILENAME_HASH = 201670096L
        private val getSupportDataFilenameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_support_data_filename", GET_SUPPORT_DATA_FILENAME_HASH)
        }

        private const val GET_SUPPORT_DATA_INFO_HASH = 201670096L
        private val getSupportDataInfoBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_support_data_info", GET_SUPPORT_DATA_INFO_HASH)
        }

        private const val SAVE_SUPPORT_DATA_HASH = 3927539163L
        private val saveSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "save_support_data", SAVE_SUPPORT_DATA_HASH)
        }

        private const val IS_LOCALE_USING_SUPPORT_DATA_HASH = 3927539163L
        private val isLocaleUsingSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_locale_using_support_data", IS_LOCALE_USING_SUPPORT_DATA_HASH)
        }

        private const val IS_LOCALE_RIGHT_TO_LEFT_HASH = 3927539163L
        private val isLocaleRightToLeftBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_locale_right_to_left", IS_LOCALE_RIGHT_TO_LEFT_HASH)
        }

        private const val NAME_TO_TAG_HASH = 1321353865L
        private val nameToTagBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "name_to_tag", NAME_TO_TAG_HASH)
        }

        private const val HAS_HASH = 3521089500L
        private val hasBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "has", HAS_HASH)
        }

        private const val FREE_RID_HASH = 2722037293L
        private val freeRidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "free_rid", FREE_RID_HASH)
        }

        private const val CREATE_FONT_HASH = 529393457L
        private val createFontBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "create_font", CREATE_FONT_HASH)
        }

        private const val CREATE_FONT_LINKED_VARIATION_HASH = 41030802L
        private val createFontLinkedVariationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "create_font_linked_variation", CREATE_FONT_LINKED_VARIATION_HASH)
        }

        private const val FONT_SET_FACE_INDEX_HASH = 3411492887L
        private val fontSetFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_face_index", FONT_SET_FACE_INDEX_HASH)
        }

        private const val FONT_GET_FACE_INDEX_HASH = 2198884583L
        private val fontGetFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_face_index", FONT_GET_FACE_INDEX_HASH)
        }

        private const val FONT_GET_FACE_COUNT_HASH = 2198884583L
        private val fontGetFaceCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_face_count", FONT_GET_FACE_COUNT_HASH)
        }

        private const val FONT_SET_STYLE_HASH = 898466325L
        private val fontSetStyleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_style", FONT_SET_STYLE_HASH)
        }

        private const val FONT_GET_STYLE_HASH = 3082502592L
        private val fontGetStyleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_style", FONT_GET_STYLE_HASH)
        }

        private const val FONT_SET_NAME_HASH = 2726140452L
        private val fontSetNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_name", FONT_SET_NAME_HASH)
        }

        private const val FONT_SET_STYLE_NAME_HASH = 2726140452L
        private val fontSetStyleNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_style_name", FONT_SET_STYLE_NAME_HASH)
        }

        private const val FONT_SET_WEIGHT_HASH = 3411492887L
        private val fontSetWeightBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_weight", FONT_SET_WEIGHT_HASH)
        }

        private const val FONT_GET_WEIGHT_HASH = 2198884583L
        private val fontGetWeightBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_weight", FONT_GET_WEIGHT_HASH)
        }

        private const val FONT_SET_STRETCH_HASH = 3411492887L
        private val fontSetStretchBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_stretch", FONT_SET_STRETCH_HASH)
        }

        private const val FONT_GET_STRETCH_HASH = 2198884583L
        private val fontGetStretchBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_stretch", FONT_GET_STRETCH_HASH)
        }

        private const val FONT_SET_ANTIALIASING_HASH = 958337235L
        private val fontSetAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_antialiasing", FONT_SET_ANTIALIASING_HASH)
        }

        private const val FONT_GET_ANTIALIASING_HASH = 3389420495L
        private val fontGetAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_antialiasing", FONT_GET_ANTIALIASING_HASH)
        }

        private const val FONT_SET_DISABLE_EMBEDDED_BITMAPS_HASH = 1265174801L
        private val fontSetDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_disable_embedded_bitmaps", FONT_SET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val FONT_GET_DISABLE_EMBEDDED_BITMAPS_HASH = 4155700596L
        private val fontGetDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_disable_embedded_bitmaps", FONT_GET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val FONT_SET_GENERATE_MIPMAPS_HASH = 1265174801L
        private val fontSetGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_generate_mipmaps", FONT_SET_GENERATE_MIPMAPS_HASH)
        }

        private const val FONT_GET_GENERATE_MIPMAPS_HASH = 4155700596L
        private val fontGetGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_generate_mipmaps", FONT_GET_GENERATE_MIPMAPS_HASH)
        }

        private const val FONT_SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 1265174801L
        private val fontSetMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_multichannel_signed_distance_field", FONT_SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val FONT_IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 4155700596L
        private val fontIsMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_multichannel_signed_distance_field", FONT_IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val FONT_SET_MSDF_PIXEL_RANGE_HASH = 3411492887L
        private val fontSetMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_msdf_pixel_range", FONT_SET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val FONT_GET_MSDF_PIXEL_RANGE_HASH = 2198884583L
        private val fontGetMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_msdf_pixel_range", FONT_GET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val FONT_SET_MSDF_SIZE_HASH = 3411492887L
        private val fontSetMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_msdf_size", FONT_SET_MSDF_SIZE_HASH)
        }

        private const val FONT_GET_MSDF_SIZE_HASH = 2198884583L
        private val fontGetMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_msdf_size", FONT_GET_MSDF_SIZE_HASH)
        }

        private const val FONT_SET_FIXED_SIZE_HASH = 3411492887L
        private val fontSetFixedSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_fixed_size", FONT_SET_FIXED_SIZE_HASH)
        }

        private const val FONT_GET_FIXED_SIZE_HASH = 2198884583L
        private val fontGetFixedSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_fixed_size", FONT_GET_FIXED_SIZE_HASH)
        }

        private const val FONT_SET_FIXED_SIZE_SCALE_MODE_HASH = 1029390307L
        private val fontSetFixedSizeScaleModeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_fixed_size_scale_mode", FONT_SET_FIXED_SIZE_SCALE_MODE_HASH)
        }

        private const val FONT_GET_FIXED_SIZE_SCALE_MODE_HASH = 4113120379L
        private val fontGetFixedSizeScaleModeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_fixed_size_scale_mode", FONT_GET_FIXED_SIZE_SCALE_MODE_HASH)
        }

        private const val FONT_SET_ALLOW_SYSTEM_FALLBACK_HASH = 1265174801L
        private val fontSetAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_allow_system_fallback", FONT_SET_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val FONT_IS_ALLOW_SYSTEM_FALLBACK_HASH = 4155700596L
        private val fontIsAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_allow_system_fallback", FONT_IS_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val FONT_CLEAR_SYSTEM_FALLBACK_CACHE_HASH = 3218959716L
        private val fontClearSystemFallbackCacheBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_system_fallback_cache", FONT_CLEAR_SYSTEM_FALLBACK_CACHE_HASH)
        }

        private const val FONT_SET_FORCE_AUTOHINTER_HASH = 1265174801L
        private val fontSetForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_force_autohinter", FONT_SET_FORCE_AUTOHINTER_HASH)
        }

        private const val FONT_IS_FORCE_AUTOHINTER_HASH = 4155700596L
        private val fontIsForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_force_autohinter", FONT_IS_FORCE_AUTOHINTER_HASH)
        }

        private const val FONT_SET_MODULATE_COLOR_GLYPHS_HASH = 1265174801L
        private val fontSetModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_modulate_color_glyphs", FONT_SET_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val FONT_IS_MODULATE_COLOR_GLYPHS_HASH = 4155700596L
        private val fontIsModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_modulate_color_glyphs", FONT_IS_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val FONT_GET_PALETTE_COUNT_HASH = 2198884583L
        private val fontGetPaletteCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_count", FONT_GET_PALETTE_COUNT_HASH)
        }

        private const val FONT_GET_USED_PALETTE_HASH = 2198884583L
        private val fontGetUsedPaletteBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_used_palette", FONT_GET_USED_PALETTE_HASH)
        }

        private const val FONT_SET_USED_PALETTE_HASH = 3411492887L
        private val fontSetUsedPaletteBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_used_palette", FONT_SET_USED_PALETTE_HASH)
        }

        private const val FONT_SET_HINTING_HASH = 1520010864L
        private val fontSetHintingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_hinting", FONT_SET_HINTING_HASH)
        }

        private const val FONT_GET_HINTING_HASH = 3971592737L
        private val fontGetHintingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_hinting", FONT_GET_HINTING_HASH)
        }

        private const val FONT_SET_SUBPIXEL_POSITIONING_HASH = 3830459669L
        private val fontSetSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_subpixel_positioning", FONT_SET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val FONT_GET_SUBPIXEL_POSITIONING_HASH = 2752233671L
        private val fontGetSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_subpixel_positioning", FONT_GET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val FONT_SET_KEEP_ROUNDING_REMAINDERS_HASH = 1265174801L
        private val fontSetKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_keep_rounding_remainders", FONT_SET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val FONT_GET_KEEP_ROUNDING_REMAINDERS_HASH = 4155700596L
        private val fontGetKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_keep_rounding_remainders", FONT_GET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val FONT_SET_EMBOLDEN_HASH = 1794382983L
        private val fontSetEmboldenBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_embolden", FONT_SET_EMBOLDEN_HASH)
        }

        private const val FONT_GET_EMBOLDEN_HASH = 866169185L
        private val fontGetEmboldenBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_embolden", FONT_GET_EMBOLDEN_HASH)
        }

        private const val FONT_SET_SPACING_HASH = 1307259930L
        private val fontSetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_spacing", FONT_SET_SPACING_HASH)
        }

        private const val FONT_GET_SPACING_HASH = 1213653558L
        private val fontGetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_spacing", FONT_GET_SPACING_HASH)
        }

        private const val FONT_SET_BASELINE_OFFSET_HASH = 1794382983L
        private val fontSetBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_baseline_offset", FONT_SET_BASELINE_OFFSET_HASH)
        }

        private const val FONT_GET_BASELINE_OFFSET_HASH = 866169185L
        private val fontGetBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_baseline_offset", FONT_GET_BASELINE_OFFSET_HASH)
        }

        private const val FONT_SET_TRANSFORM_HASH = 1246044741L
        private val fontSetTransformBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_transform", FONT_SET_TRANSFORM_HASH)
        }

        private const val FONT_GET_TRANSFORM_HASH = 213527486L
        private val fontGetTransformBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_transform", FONT_GET_TRANSFORM_HASH)
        }

        private const val FONT_SET_OVERSAMPLING_HASH = 1794382983L
        private val fontSetOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_oversampling", FONT_SET_OVERSAMPLING_HASH)
        }

        private const val FONT_GET_OVERSAMPLING_HASH = 866169185L
        private val fontGetOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_oversampling", FONT_GET_OVERSAMPLING_HASH)
        }

        private const val FONT_CLEAR_SIZE_CACHE_HASH = 2722037293L
        private val fontClearSizeCacheBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_size_cache", FONT_CLEAR_SIZE_CACHE_HASH)
        }

        private const val FONT_REMOVE_SIZE_CACHE_HASH = 2450610377L
        private val fontRemoveSizeCacheBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_size_cache", FONT_REMOVE_SIZE_CACHE_HASH)
        }

        private const val FONT_SET_ASCENT_HASH = 1892459533L
        private val fontSetAscentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_ascent", FONT_SET_ASCENT_HASH)
        }

        private const val FONT_GET_ASCENT_HASH = 755457166L
        private val fontGetAscentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_ascent", FONT_GET_ASCENT_HASH)
        }

        private const val FONT_SET_DESCENT_HASH = 1892459533L
        private val fontSetDescentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_descent", FONT_SET_DESCENT_HASH)
        }

        private const val FONT_GET_DESCENT_HASH = 755457166L
        private val fontGetDescentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_descent", FONT_GET_DESCENT_HASH)
        }

        private const val FONT_SET_UNDERLINE_POSITION_HASH = 1892459533L
        private val fontSetUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_underline_position", FONT_SET_UNDERLINE_POSITION_HASH)
        }

        private const val FONT_GET_UNDERLINE_POSITION_HASH = 755457166L
        private val fontGetUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_underline_position", FONT_GET_UNDERLINE_POSITION_HASH)
        }

        private const val FONT_SET_UNDERLINE_THICKNESS_HASH = 1892459533L
        private val fontSetUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_underline_thickness", FONT_SET_UNDERLINE_THICKNESS_HASH)
        }

        private const val FONT_GET_UNDERLINE_THICKNESS_HASH = 755457166L
        private val fontGetUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_underline_thickness", FONT_GET_UNDERLINE_THICKNESS_HASH)
        }

        private const val FONT_SET_SCALE_HASH = 1892459533L
        private val fontSetScaleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_scale", FONT_SET_SCALE_HASH)
        }

        private const val FONT_GET_SCALE_HASH = 755457166L
        private val fontGetScaleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_scale", FONT_GET_SCALE_HASH)
        }

        private const val FONT_GET_TEXTURE_COUNT_HASH = 1311001310L
        private val fontGetTextureCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_count", FONT_GET_TEXTURE_COUNT_HASH)
        }

        private const val FONT_CLEAR_TEXTURES_HASH = 2450610377L
        private val fontClearTexturesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_textures", FONT_CLEAR_TEXTURES_HASH)
        }

        private const val FONT_REMOVE_TEXTURE_HASH = 3810512262L
        private val fontRemoveTextureBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_texture", FONT_REMOVE_TEXTURE_HASH)
        }

        private const val FONT_SET_TEXTURE_IMAGE_HASH = 2354485091L
        private val fontSetTextureImageBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_texture_image", FONT_SET_TEXTURE_IMAGE_HASH)
        }

        private const val FONT_GET_TEXTURE_IMAGE_HASH = 2451761155L
        private val fontGetTextureImageBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_image", FONT_GET_TEXTURE_IMAGE_HASH)
        }

        private const val FONT_CLEAR_GLYPHS_HASH = 2450610377L
        private val fontClearGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_glyphs", FONT_CLEAR_GLYPHS_HASH)
        }

        private const val FONT_REMOVE_GLYPH_HASH = 3810512262L
        private val fontRemoveGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_glyph", FONT_REMOVE_GLYPH_HASH)
        }

        private const val FONT_GET_GLYPH_ADVANCE_HASH = 2555689501L
        private val fontGetGlyphAdvanceBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_advance", FONT_GET_GLYPH_ADVANCE_HASH)
        }

        private const val FONT_SET_GLYPH_ADVANCE_HASH = 3219397315L
        private val fontSetGlyphAdvanceBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_advance", FONT_SET_GLYPH_ADVANCE_HASH)
        }

        private const val FONT_GET_GLYPH_OFFSET_HASH = 513728628L
        private val fontGetGlyphOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_offset", FONT_GET_GLYPH_OFFSET_HASH)
        }

        private const val FONT_SET_GLYPH_OFFSET_HASH = 1812632090L
        private val fontSetGlyphOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_offset", FONT_SET_GLYPH_OFFSET_HASH)
        }

        private const val FONT_GET_GLYPH_SIZE_HASH = 513728628L
        private val fontGetGlyphSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_size", FONT_GET_GLYPH_SIZE_HASH)
        }

        private const val FONT_SET_GLYPH_SIZE_HASH = 1812632090L
        private val fontSetGlyphSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_size", FONT_SET_GLYPH_SIZE_HASH)
        }

        private const val FONT_GET_GLYPH_UV_RECT_HASH = 2274268786L
        private val fontGetGlyphUvRectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_uv_rect", FONT_GET_GLYPH_UV_RECT_HASH)
        }

        private const val FONT_SET_GLYPH_UV_RECT_HASH = 1973324081L
        private val fontSetGlyphUvRectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_uv_rect", FONT_SET_GLYPH_UV_RECT_HASH)
        }

        private const val FONT_GET_GLYPH_TEXTURE_IDX_HASH = 4292800474L
        private val fontGetGlyphTextureIdxBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_idx", FONT_GET_GLYPH_TEXTURE_IDX_HASH)
        }

        private const val FONT_SET_GLYPH_TEXTURE_IDX_HASH = 4254580980L
        private val fontSetGlyphTextureIdxBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_texture_idx", FONT_SET_GLYPH_TEXTURE_IDX_HASH)
        }

        private const val FONT_GET_GLYPH_TEXTURE_RID_HASH = 1451696141L
        private val fontGetGlyphTextureRidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_rid", FONT_GET_GLYPH_TEXTURE_RID_HASH)
        }

        private const val FONT_GET_GLYPH_TEXTURE_SIZE_HASH = 513728628L
        private val fontGetGlyphTextureSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_size", FONT_GET_GLYPH_TEXTURE_SIZE_HASH)
        }

        private const val FONT_CLEAR_KERNING_MAP_HASH = 3411492887L
        private val fontClearKerningMapBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_kerning_map", FONT_CLEAR_KERNING_MAP_HASH)
        }

        private const val FONT_REMOVE_KERNING_HASH = 2141860016L
        private val fontRemoveKerningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_kerning", FONT_REMOVE_KERNING_HASH)
        }

        private const val FONT_SET_KERNING_HASH = 3630965883L
        private val fontSetKerningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_kerning", FONT_SET_KERNING_HASH)
        }

        private const val FONT_GET_KERNING_HASH = 1019980169L
        private val fontGetKerningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_kerning", FONT_GET_KERNING_HASH)
        }

        private const val FONT_GET_GLYPH_INDEX_HASH = 1765635060L
        private val fontGetGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_index", FONT_GET_GLYPH_INDEX_HASH)
        }

        private const val FONT_GET_CHAR_FROM_GLYPH_INDEX_HASH = 2156738276L
        private val fontGetCharFromGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_char_from_glyph_index", FONT_GET_CHAR_FROM_GLYPH_INDEX_HASH)
        }

        private const val FONT_HAS_CHAR_HASH = 3120086654L
        private val fontHasCharBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_has_char", FONT_HAS_CHAR_HASH)
        }

        private const val FONT_RENDER_RANGE_HASH = 4254580980L
        private val fontRenderRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_render_range", FONT_RENDER_RANGE_HASH)
        }

        private const val FONT_RENDER_GLYPH_HASH = 3810512262L
        private val fontRenderGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_render_glyph", FONT_RENDER_GLYPH_HASH)
        }

        private const val FONT_DRAW_GLYPH_HASH = 3103234926L
        private val fontDrawGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_draw_glyph", FONT_DRAW_GLYPH_HASH)
        }

        private const val FONT_DRAW_GLYPH_OUTLINE_HASH = 1976041553L
        private val fontDrawGlyphOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_draw_glyph_outline", FONT_DRAW_GLYPH_OUTLINE_HASH)
        }

        private const val FONT_IS_LANGUAGE_SUPPORTED_HASH = 3199320846L
        private val fontIsLanguageSupportedBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_language_supported", FONT_IS_LANGUAGE_SUPPORTED_HASH)
        }

        private const val FONT_SET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2313957094L
        private val fontSetLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_language_support_override", FONT_SET_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2829184646L
        private val fontGetLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_language_support_override", FONT_GET_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2726140452L
        private val fontRemoveLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_language_support_override", FONT_REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_IS_SCRIPT_SUPPORTED_HASH = 3199320846L
        private val fontIsScriptSupportedBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_script_supported", FONT_IS_SCRIPT_SUPPORTED_HASH)
        }

        private const val FONT_SET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2313957094L
        private val fontSetScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_script_support_override", FONT_SET_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2829184646L
        private val fontGetScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_script_support_override", FONT_GET_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH = 2726140452L
        private val fontRemoveScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_script_support_override", FONT_REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_GLOBAL_OVERSAMPLING_HASH = 1740695150L
        private val fontGetGlobalOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_global_oversampling", FONT_GET_GLOBAL_OVERSAMPLING_HASH)
        }

        private const val FONT_SET_GLOBAL_OVERSAMPLING_HASH = 373806689L
        private val fontSetGlobalOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_global_oversampling", FONT_SET_GLOBAL_OVERSAMPLING_HASH)
        }

        private const val GET_HEX_CODE_BOX_SIZE_HASH = 3016396712L
        private val getHexCodeBoxSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_hex_code_box_size", GET_HEX_CODE_BOX_SIZE_HASH)
        }

        private const val DRAW_HEX_CODE_BOX_HASH = 1602046441L
        private val drawHexCodeBoxBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "draw_hex_code_box", DRAW_HEX_CODE_BOX_HASH)
        }

        private const val CREATE_SHAPED_TEXT_HASH = 1231398698L
        private val createShapedTextBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "create_shaped_text", CREATE_SHAPED_TEXT_HASH)
        }

        private const val SHAPED_TEXT_CLEAR_HASH = 2722037293L
        private val shapedTextClearBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_clear", SHAPED_TEXT_CLEAR_HASH)
        }

        private const val SHAPED_TEXT_DUPLICATE_HASH = 41030802L
        private val shapedTextDuplicateBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_duplicate", SHAPED_TEXT_DUPLICATE_HASH)
        }

        private const val SHAPED_TEXT_SET_DIRECTION_HASH = 1551430183L
        private val shapedTextSetDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_direction", SHAPED_TEXT_SET_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_GET_DIRECTION_HASH = 3065904362L
        private val shapedTextGetDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_direction", SHAPED_TEXT_GET_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_GET_INFERRED_DIRECTION_HASH = 3065904362L
        private val shapedTextGetInferredDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_inferred_direction", SHAPED_TEXT_GET_INFERRED_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_SET_CUSTOM_PUNCTUATION_HASH = 2726140452L
        private val shapedTextSetCustomPunctuationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_custom_punctuation", SHAPED_TEXT_SET_CUSTOM_PUNCTUATION_HASH)
        }

        private const val SHAPED_TEXT_SET_CUSTOM_ELLIPSIS_HASH = 3411492887L
        private val shapedTextSetCustomEllipsisBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_custom_ellipsis", SHAPED_TEXT_SET_CUSTOM_ELLIPSIS_HASH)
        }

        private const val SHAPED_TEXT_GET_CUSTOM_ELLIPSIS_HASH = 2198884583L
        private val shapedTextGetCustomEllipsisBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_custom_ellipsis", SHAPED_TEXT_GET_CUSTOM_ELLIPSIS_HASH)
        }

        private const val SHAPED_TEXT_SET_ORIENTATION_HASH = 3019609126L
        private val shapedTextSetOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_orientation", SHAPED_TEXT_SET_ORIENTATION_HASH)
        }

        private const val SHAPED_TEXT_GET_ORIENTATION_HASH = 3142708106L
        private val shapedTextGetOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_orientation", SHAPED_TEXT_GET_ORIENTATION_HASH)
        }

        private const val SHAPED_TEXT_SET_PRESERVE_INVALID_HASH = 1265174801L
        private val shapedTextSetPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_preserve_invalid", SHAPED_TEXT_SET_PRESERVE_INVALID_HASH)
        }

        private const val SHAPED_TEXT_GET_PRESERVE_INVALID_HASH = 4155700596L
        private val shapedTextGetPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_preserve_invalid", SHAPED_TEXT_GET_PRESERVE_INVALID_HASH)
        }

        private const val SHAPED_TEXT_SET_PRESERVE_CONTROL_HASH = 1265174801L
        private val shapedTextSetPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_preserve_control", SHAPED_TEXT_SET_PRESERVE_CONTROL_HASH)
        }

        private const val SHAPED_TEXT_GET_PRESERVE_CONTROL_HASH = 4155700596L
        private val shapedTextGetPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_preserve_control", SHAPED_TEXT_GET_PRESERVE_CONTROL_HASH)
        }

        private const val SHAPED_TEXT_SET_SPACING_HASH = 1307259930L
        private val shapedTextSetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_spacing", SHAPED_TEXT_SET_SPACING_HASH)
        }

        private const val SHAPED_TEXT_GET_SPACING_HASH = 1213653558L
        private val shapedTextGetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_spacing", SHAPED_TEXT_GET_SPACING_HASH)
        }

        private const val SHAPED_GET_SPAN_COUNT_HASH = 2198884583L
        private val shapedGetSpanCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_count", SHAPED_GET_SPAN_COUNT_HASH)
        }

        private const val SHAPED_GET_RUN_COUNT_HASH = 2198884583L
        private val shapedGetRunCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_count", SHAPED_GET_RUN_COUNT_HASH)
        }

        private const val SHAPED_GET_RUN_RANGE_HASH = 4069534484L
        private val shapedGetRunRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_range", SHAPED_GET_RUN_RANGE_HASH)
        }

        private const val SHAPED_GET_RUN_GLYPH_RANGE_HASH = 4069534484L
        private val shapedGetRunGlyphRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_glyph_range", SHAPED_GET_RUN_GLYPH_RANGE_HASH)
        }

        private const val SHAPED_GET_RUN_FONT_RID_HASH = 1066463050L
        private val shapedGetRunFontRidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_font_rid", SHAPED_GET_RUN_FONT_RID_HASH)
        }

        private const val SHAPED_GET_RUN_FONT_SIZE_HASH = 1120910005L
        private val shapedGetRunFontSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_font_size", SHAPED_GET_RUN_FONT_SIZE_HASH)
        }

        private const val SHAPED_GET_RUN_DIRECTION_HASH = 2413896864L
        private val shapedGetRunDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_direction", SHAPED_GET_RUN_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_SUBSTR_HASH = 1937682086L
        private val shapedTextSubstrBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_substr", SHAPED_TEXT_SUBSTR_HASH)
        }

        private const val SHAPED_TEXT_GET_PARENT_HASH = 3814569979L
        private val shapedTextGetParentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_parent", SHAPED_TEXT_GET_PARENT_HASH)
        }

        private const val SHAPED_TEXT_FIT_TO_WIDTH_HASH = 530670926L
        private val shapedTextFitToWidthBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_fit_to_width", SHAPED_TEXT_FIT_TO_WIDTH_HASH)
        }

        private const val SHAPED_TEXT_SHAPE_HASH = 3521089500L
        private val shapedTextShapeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_shape", SHAPED_TEXT_SHAPE_HASH)
        }

        private const val SHAPED_TEXT_IS_READY_HASH = 4155700596L
        private val shapedTextIsReadyBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_is_ready", SHAPED_TEXT_IS_READY_HASH)
        }

        private const val SHAPED_TEXT_HAS_VISIBLE_CHARS_HASH = 4155700596L
        private val shapedTextHasVisibleCharsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_has_visible_chars", SHAPED_TEXT_HAS_VISIBLE_CHARS_HASH)
        }

        private const val SHAPED_TEXT_GET_GLYPH_COUNT_HASH = 2198884583L
        private val shapedTextGetGlyphCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_glyph_count", SHAPED_TEXT_GET_GLYPH_COUNT_HASH)
        }

        private const val SHAPED_TEXT_GET_RANGE_HASH = 733700038L
        private val shapedTextGetRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_range", SHAPED_TEXT_GET_RANGE_HASH)
        }

        private const val SHAPED_TEXT_GET_TRIM_POS_HASH = 2198884583L
        private val shapedTextGetTrimPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_trim_pos", SHAPED_TEXT_GET_TRIM_POS_HASH)
        }

        private const val SHAPED_TEXT_GET_ELLIPSIS_POS_HASH = 2198884583L
        private val shapedTextGetEllipsisPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_pos", SHAPED_TEXT_GET_ELLIPSIS_POS_HASH)
        }

        private const val SHAPED_TEXT_GET_ELLIPSIS_GLYPH_COUNT_HASH = 2198884583L
        private val shapedTextGetEllipsisGlyphCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_glyph_count", SHAPED_TEXT_GET_ELLIPSIS_GLYPH_COUNT_HASH)
        }

        private const val SHAPED_TEXT_OVERRUN_TRIM_TO_WIDTH_HASH = 2723146520L
        private val shapedTextOverrunTrimToWidthBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_overrun_trim_to_width", SHAPED_TEXT_OVERRUN_TRIM_TO_WIDTH_HASH)
        }

        private const val SHAPED_TEXT_GET_SIZE_HASH = 2440833711L
        private val shapedTextGetSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_size", SHAPED_TEXT_GET_SIZE_HASH)
        }

        private const val SHAPED_TEXT_GET_ASCENT_HASH = 866169185L
        private val shapedTextGetAscentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ascent", SHAPED_TEXT_GET_ASCENT_HASH)
        }

        private const val SHAPED_TEXT_GET_DESCENT_HASH = 866169185L
        private val shapedTextGetDescentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_descent", SHAPED_TEXT_GET_DESCENT_HASH)
        }

        private const val SHAPED_TEXT_GET_WIDTH_HASH = 866169185L
        private val shapedTextGetWidthBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_width", SHAPED_TEXT_GET_WIDTH_HASH)
        }

        private const val SHAPED_TEXT_GET_UNDERLINE_POSITION_HASH = 866169185L
        private val shapedTextGetUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_underline_position", SHAPED_TEXT_GET_UNDERLINE_POSITION_HASH)
        }

        private const val SHAPED_TEXT_GET_UNDERLINE_THICKNESS_HASH = 866169185L
        private val shapedTextGetUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_underline_thickness", SHAPED_TEXT_GET_UNDERLINE_THICKNESS_HASH)
        }

        private const val SHAPED_TEXT_HIT_TEST_GRAPHEME_HASH = 3149310417L
        private val shapedTextHitTestGraphemeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_hit_test_grapheme", SHAPED_TEXT_HIT_TEST_GRAPHEME_HASH)
        }

        private const val SHAPED_TEXT_HIT_TEST_POSITION_HASH = 3149310417L
        private val shapedTextHitTestPositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_hit_test_position", SHAPED_TEXT_HIT_TEST_POSITION_HASH)
        }

        private const val SHAPED_TEXT_GET_GRAPHEME_BOUNDS_HASH = 2546185844L
        private val shapedTextGetGraphemeBoundsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_grapheme_bounds", SHAPED_TEXT_GET_GRAPHEME_BOUNDS_HASH)
        }

        private const val SHAPED_TEXT_NEXT_GRAPHEME_POS_HASH = 1120910005L
        private val shapedTextNextGraphemePosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_next_grapheme_pos", SHAPED_TEXT_NEXT_GRAPHEME_POS_HASH)
        }

        private const val SHAPED_TEXT_PREV_GRAPHEME_POS_HASH = 1120910005L
        private val shapedTextPrevGraphemePosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_prev_grapheme_pos", SHAPED_TEXT_PREV_GRAPHEME_POS_HASH)
        }

        private const val SHAPED_TEXT_NEXT_CHARACTER_POS_HASH = 1120910005L
        private val shapedTextNextCharacterPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_next_character_pos", SHAPED_TEXT_NEXT_CHARACTER_POS_HASH)
        }

        private const val SHAPED_TEXT_PREV_CHARACTER_POS_HASH = 1120910005L
        private val shapedTextPrevCharacterPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_prev_character_pos", SHAPED_TEXT_PREV_CHARACTER_POS_HASH)
        }

        private const val SHAPED_TEXT_CLOSEST_CHARACTER_POS_HASH = 1120910005L
        private val shapedTextClosestCharacterPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_closest_character_pos", SHAPED_TEXT_CLOSEST_CHARACTER_POS_HASH)
        }

        private const val SHAPED_TEXT_DRAW_HASH = 1647687596L
        private val shapedTextDrawBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_draw", SHAPED_TEXT_DRAW_HASH)
        }

        private const val SHAPED_TEXT_DRAW_OUTLINE_HASH = 1217146601L
        private val shapedTextDrawOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_draw_outline", SHAPED_TEXT_DRAW_OUTLINE_HASH)
        }

        private const val SHAPED_TEXT_GET_DOMINANT_DIRECTION_IN_RANGE_HASH = 3326907668L
        private val shapedTextGetDominantDirectionInRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_dominant_direction_in_range", SHAPED_TEXT_GET_DOMINANT_DIRECTION_IN_RANGE_HASH)
        }

        private const val PERCENT_SIGN_HASH = 993269549L
        private val percentSignBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "percent_sign", PERCENT_SIGN_HASH)
        }

        private const val SPOOF_CHECK_HASH = 3927539163L
        private val spoofCheckBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "spoof_check", SPOOF_CHECK_HASH)
        }

        private const val STRIP_DIACRITICS_HASH = 3135753539L
        private val stripDiacriticsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "strip_diacritics", STRIP_DIACRITICS_HASH)
        }

        private const val IS_VALID_IDENTIFIER_HASH = 3927539163L
        private val isValidIdentifierBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_valid_identifier", IS_VALID_IDENTIFIER_HASH)
        }

        private const val IS_VALID_LETTER_HASH = 1116898809L
        private val isValidLetterBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_valid_letter", IS_VALID_LETTER_HASH)
        }
    }
}

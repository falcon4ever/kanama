package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Abstract base class for fonts and font variations.
 *
 * Generated from Godot docs: Font
 */
open class Font(handle: MemorySegment) : Resource(handle) {
    var fallbacks: List<Font>
        @JvmName("fallbacksProperty")
        get() = getFallbacks()
        @JvmName("setFallbacksProperty")
        set(value) = setFallbacks(value)

    /**
     * Array of fallback `Font`s to use as a substitute if a glyph is not found in this current `Font`.
     * If this array is empty in a `FontVariation`, the `FontVariation.base_font`'s fallbacks are used
     * instead.
     *
     * Generated from Godot docs: Font.set_fallbacks
     */
    fun setFallbacks(fallbacks: List<Font>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setFallbacksBind, handle, fallbacks)
    }

    /**
     * Array of fallback `Font`s to use as a substitute if a glyph is not found in this current `Font`.
     * If this array is empty in a `FontVariation`, the `FontVariation.base_font`'s fallbacks are used
     * instead.
     *
     * Generated from Godot docs: Font.get_fallbacks
     */
    fun getFallbacks(): List<Font> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getFallbacksBind, handle, Font::fromHandle)
    }

    /**
     * Returns `TextServer` RID of the font cache for specific variation.
     *
     * Generated from Godot docs: Font.find_variation
     */
    fun findVariation(variationCoordinates: Map<String, Any?>, faceIndex: Int = 0, strength: Double = 0.0, transform: Transform2D, spacingTop: Int = 0, spacingBottom: Int = 0, spacingSpace: Int = 0, spacingGlyph: Int = 0, baselineOffset: Double = 0.0, paletteIndex: Long = 0L, customColors: List<Color>): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryIntDoubleTransform2DFourIntDoubleLongPackedColorListArgsRetRID(findVariationBind, handle, variationCoordinates, faceIndex, strength, transform, spacingTop, spacingBottom, spacingSpace, spacingGlyph, baselineOffset, paletteIndex, customColors)
    }

    /**
     * Returns `Array` of valid `Font` `RID`s, which can be passed to the `TextServer` methods.
     *
     * Generated from Godot docs: Font.get_rids
     */
    fun getRids(): List<RID> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRIDList(getRidsBind, handle)
    }

    /**
     * Returns the total average font height (ascent plus descent) in pixels. Note: Real height of the
     * string is context-dependent and can be significantly different from the value returned by this
     * function. Use it only as rough estimate (e.g. as the height of empty line).
     *
     * Generated from Godot docs: Font.get_height
     */
    fun getHeight(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getHeightBind, handle, fontSize)
    }

    /**
     * Returns the maximum font ascent (number of pixels above the baseline) of this font and all
     * fallback fonts. Note: Real ascent of the string is context-dependent and can be significantly
     * different from the value returned by this function. Use it only as rough estimate (e.g. as the
     * ascent of empty line).
     *
     * Generated from Godot docs: Font.get_ascent
     */
    fun getAscent(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getAscentBind, handle, fontSize)
    }

    /**
     * Returns the maximum font descent (number of pixels below the baseline) of this font and all
     * fallback fonts. Note: Real descent of the string is context-dependent and can be significantly
     * different from the value returned by this function. Use it only as rough estimate (e.g. as the
     * descent of empty line).
     *
     * Generated from Godot docs: Font.get_descent
     */
    fun getDescent(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getDescentBind, handle, fontSize)
    }

    /**
     * Returns average pixel offset of the underline below the baseline. Note: Real underline position
     * of the string is context-dependent and can be significantly different from the value returned by
     * this function. Use it only as rough estimate.
     *
     * Generated from Godot docs: Font.get_underline_position
     */
    fun getUnderlinePosition(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getUnderlinePositionBind, handle, fontSize)
    }

    /**
     * Returns average thickness of the underline. Note: Real underline thickness of the string is
     * context-dependent and can be significantly different from the value returned by this function.
     * Use it only as rough estimate.
     *
     * Generated from Godot docs: Font.get_underline_thickness
     */
    fun getUnderlineThickness(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getUnderlineThicknessBind, handle, fontSize)
    }

    /**
     * Returns font family name.
     *
     * Generated from Godot docs: Font.get_font_name
     */
    fun getFontName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFontNameBind, handle)
    }

    /**
     * Returns font style name.
     *
     * Generated from Godot docs: Font.get_font_style_name
     */
    fun getFontStyleName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFontStyleNameBind, handle)
    }

    /**
     * Returns `Dictionary` with OpenType font name strings (localized font names, version,
     * description, license information, sample text, etc.).
     *
     * Generated from Godot docs: Font.get_ot_name_strings
     */
    fun getOtNameStrings(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getOtNameStringsBind, handle)
    }

    /**
     * Returns font style flags.
     *
     * Generated from Godot docs: Font.get_font_style
     */
    fun getFontStyle(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFontStyleBind, handle)
    }

    /**
     * Returns weight (boldness) of the font. A value in the `100...999` range, normal font weight is
     * `400`, bold font weight is `700`.
     *
     * Generated from Godot docs: Font.get_font_weight
     */
    fun getFontWeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFontWeightBind, handle)
    }

    /**
     * Returns font stretch amount, compared to a normal width. A percentage value between `50%` and
     * `200%`.
     *
     * Generated from Godot docs: Font.get_font_stretch
     */
    fun getFontStretch(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFontStretchBind, handle)
    }

    /**
     * Returns the amount of spacing for the given `spacing` type.
     *
     * Generated from Godot docs: Font.get_spacing
     */
    fun getSpacing(spacing: Long): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetInt(getSpacingBind, handle, spacing)
    }

    /**
     * Returns a set of OpenType feature tags. More info: OpenType feature tags
     * (https://docs.microsoft.com/en-us/typography/opentype/spec/featuretags).
     *
     * Generated from Godot docs: Font.get_opentype_features
     */
    fun getOpentypeFeatures(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getOpentypeFeaturesBind, handle)
    }

    /**
     * Sets LRU cache capacity for `draw_*` methods.
     *
     * Generated from Godot docs: Font.set_cache_capacity
     */
    fun setCacheCapacity(singleLine: Int, multiLine: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(setCacheCapacityBind, handle, singleLine, multiLine)
    }

    /**
     * Returns the size of a bounding box of a single-line string, taking kerning, advance and subpixel
     * positioning into account. See also `get_multiline_string_size` and `draw_string`. For example,
     * to get the string size as displayed by a single-line Label, use:
     *
     * Generated from Godot docs: Font.get_string_size
     */
    fun getStringSize(text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithStringLongDoubleIntThreeLongArgsRetVector2(getStringSizeBind, handle, text, alignment, width, fontSize, justificationFlags, direction, orientation)
    }

    /**
     * Returns the size of a bounding box of a string broken into the lines, taking kerning and advance
     * into account. See also `draw_multiline_string`.
     *
     * Generated from Godot docs: Font.get_multiline_string_size
     */
    fun getMultilineStringSize(text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithStringLongDoubleTwoIntFourLongArgsRetVector2(getMultilineStringSizeBind, handle, text, alignment, width, fontSize, maxLines, brkFlags, justificationFlags, direction, orientation)
    }

    /**
     * Draw `text` into a canvas item using the font, at a given position, with `modulate` color,
     * optionally clipping the width and aligning horizontally. `pos` specifies the baseline, not the
     * top. To draw from the top, ascent must be added to the Y axis. If `oversampling` is greater than
     * zero, it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     * See also `CanvasItem.draw_string`.
     *
     * Generated from Godot docs: Font.draw_string
     */
    fun drawString(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleIntColorThreeLongDoubleArgs(drawStringBind, handle, canvasItem, pos, text, alignment, width, fontSize, modulate, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Breaks `text` into lines using rules specified by `brk_flags` and draws it into a canvas item
     * using the font, at a given position, with `modulate` color, optionally clipping the width and
     * aligning horizontally. `pos` specifies the baseline of the first line, not the top. To draw from
     * the top, ascent must be added to the Y axis. If `oversampling` is greater than zero, it is used
     * as font oversampling factor, otherwise viewport oversampling settings are used. See also
     * `CanvasItem.draw_multiline_string`.
     *
     * Generated from Godot docs: Font.draw_multiline_string
     */
    fun drawMultilineString(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(drawMultilineStringBind, handle, canvasItem, pos, text, alignment, width, fontSize, maxLines, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Draw `text` outline into a canvas item using the font, at a given position, with `modulate`
     * color and `size` outline size, optionally clipping the width and aligning horizontally. `pos`
     * specifies the baseline, not the top. To draw from the top, ascent must be added to the Y axis.
     * If `oversampling` is greater than zero, it is used as font oversampling factor, otherwise
     * viewport oversampling settings are used. See also `CanvasItem.draw_string_outline`.
     *
     * Generated from Godot docs: Font.draw_string_outline
     */
    fun drawStringOutline(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, size: Int = 1, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(drawStringOutlineBind, handle, canvasItem, pos, text, alignment, width, fontSize, size, modulate, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Breaks `text` to the lines using rules specified by `brk_flags` and draws text outline into a
     * canvas item using the font, at a given position, with `modulate` color and `size` outline size,
     * optionally clipping the width and aligning horizontally. `pos` specifies the baseline of the
     * first line, not the top. To draw from the top, ascent must be added to the Y axis. If
     * `oversampling` is greater than zero, it is used as font oversampling factor, otherwise viewport
     * oversampling settings are used. See also `CanvasItem.draw_multiline_string_outline`.
     *
     * Generated from Godot docs: Font.draw_multiline_string_outline
     */
    fun drawMultilineStringOutline(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, size: Int = 1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(drawMultilineStringOutlineBind, handle, canvasItem, pos, text, alignment, width, fontSize, maxLines, size, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    /**
     * Returns the size of a character. Does not take kerning into account. Note: Do not use this
     * function to calculate width of the string character by character, use `get_string_size` or
     * `TextLine` instead. The height returned is the font height (see also `get_height`) and has no
     * relation to the glyph height.
     *
     * Generated from Godot docs: Font.get_char_size
     */
    fun getCharSize(char: Int, fontSize: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(getCharSizeBind, handle, char, fontSize)
    }

    /**
     * Draw a single Unicode character `char` into a canvas item using the font, at a given position,
     * with `modulate` color. `pos` specifies the baseline, not the top. To draw from the top, ascent
     * must be added to the Y axis. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used. Note: Do not use this
     * function to draw strings character by character, use `draw_string` or `TextLine` instead.
     *
     * Generated from Godot docs: Font.draw_char
     */
    fun drawChar(canvasItem: RID, pos: Vector2, char: Int, fontSize: Int, modulate: Color, oversampling: Double = 0.0): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2TwoIntColorDoubleArgsRetDouble(drawCharBind, handle, canvasItem, pos, char, fontSize, modulate, oversampling)
    }

    /**
     * Draw a single Unicode character `char` outline into a canvas item using the font, at a given
     * position, with `modulate` color and `size` outline size. `pos` specifies the baseline, not the
     * top. To draw from the top, ascent must be added to the Y axis. If `oversampling` is greater than
     * zero, it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     * Note: Do not use this function to draw strings character by character, use `draw_string` or
     * `TextLine` instead.
     *
     * Generated from Godot docs: Font.draw_char_outline
     */
    fun drawCharOutline(canvasItem: RID, pos: Vector2, char: Int, fontSize: Int, size: Int = -1, modulate: Color, oversampling: Double = 0.0): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2ThreeIntColorDoubleArgsRetDouble(drawCharOutlineBind, handle, canvasItem, pos, char, fontSize, size, modulate, oversampling)
    }

    /**
     * Returns `true` if a Unicode `char` is available in the font.
     *
     * Generated from Godot docs: Font.has_char
     */
    fun hasChar(char: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(hasCharBind, handle, char)
    }

    /**
     * Returns a string containing all the characters available in the font. If a given character is
     * included in more than one font data source, it appears only once in the returned string.
     *
     * Generated from Godot docs: Font.get_supported_chars
     */
    fun getSupportedChars(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSupportedCharsBind, handle)
    }

    /**
     * Returns `true` if the font supports the given language (as a ISO 639
     * (https://en.wikipedia.org/wiki/ISO_639-1) code).
     *
     * Generated from Godot docs: Font.is_language_supported
     */
    fun isLanguageSupported(language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isLanguageSupportedBind, handle, language)
    }

    /**
     * Returns `true` if the font supports the given script (as a ISO 15924
     * (https://en.wikipedia.org/wiki/ISO_15924) code).
     *
     * Generated from Godot docs: Font.is_script_supported
     */
    fun isScriptSupported(script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isScriptSupportedBind, handle, script)
    }

    /**
     * Returns list of OpenType features supported by font.
     *
     * Generated from Godot docs: Font.get_supported_feature_list
     */
    fun getSupportedFeatureList(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSupportedFeatureListBind, handle)
    }

    /**
     * Returns list of supported variation coordinates
     * (https://docs.microsoft.com/en-us/typography/opentype/spec/dvaraxisreg), each coordinate is
     * returned as `tag: Vector3i(min_value,max_value,default_value)`. Font variations allow for
     * continuous change of glyph characteristics along some given design axis, such as weight, width
     * or slant. To print available variation axes of a variable font:
     *
     * Generated from Godot docs: Font.get_supported_variation_list
     */
    fun getSupportedVariationList(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSupportedVariationListBind, handle)
    }

    /**
     * Returns number of faces in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: Font.get_face_count
     */
    fun getFaceCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFaceCountBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Font? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Font? =
            if (handle.address() == 0L) null else Font(handle)

        private const val SET_FALLBACKS_HASH = 381264803L
        private val setFallbacksBind by lazy {
            ObjectCalls.getMethodBind("Font", "set_fallbacks", SET_FALLBACKS_HASH)
        }

        private const val GET_FALLBACKS_HASH = 3995934104L
        private val getFallbacksBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_fallbacks", GET_FALLBACKS_HASH)
        }

        private const val FIND_VARIATION_HASH = 3275867622L
        private val findVariationBind by lazy {
            ObjectCalls.getMethodBind("Font", "find_variation", FIND_VARIATION_HASH)
        }

        private const val GET_RIDS_HASH = 3995934104L
        private val getRidsBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_rids", GET_RIDS_HASH)
        }

        private const val GET_HEIGHT_HASH = 378113874L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_height", GET_HEIGHT_HASH)
        }

        private const val GET_ASCENT_HASH = 378113874L
        private val getAscentBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_ascent", GET_ASCENT_HASH)
        }

        private const val GET_DESCENT_HASH = 378113874L
        private val getDescentBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_descent", GET_DESCENT_HASH)
        }

        private const val GET_UNDERLINE_POSITION_HASH = 378113874L
        private val getUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_underline_position", GET_UNDERLINE_POSITION_HASH)
        }

        private const val GET_UNDERLINE_THICKNESS_HASH = 378113874L
        private val getUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_underline_thickness", GET_UNDERLINE_THICKNESS_HASH)
        }

        private const val GET_FONT_NAME_HASH = 201670096L
        private val getFontNameBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_font_name", GET_FONT_NAME_HASH)
        }

        private const val GET_FONT_STYLE_NAME_HASH = 201670096L
        private val getFontStyleNameBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_font_style_name", GET_FONT_STYLE_NAME_HASH)
        }

        private const val GET_OT_NAME_STRINGS_HASH = 3102165223L
        private val getOtNameStringsBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_ot_name_strings", GET_OT_NAME_STRINGS_HASH)
        }

        private const val GET_FONT_STYLE_HASH = 2520224254L
        private val getFontStyleBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_font_style", GET_FONT_STYLE_HASH)
        }

        private const val GET_FONT_WEIGHT_HASH = 3905245786L
        private val getFontWeightBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_font_weight", GET_FONT_WEIGHT_HASH)
        }

        private const val GET_FONT_STRETCH_HASH = 3905245786L
        private val getFontStretchBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_font_stretch", GET_FONT_STRETCH_HASH)
        }

        private const val GET_SPACING_HASH = 1310880908L
        private val getSpacingBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_spacing", GET_SPACING_HASH)
        }

        private const val GET_OPENTYPE_FEATURES_HASH = 3102165223L
        private val getOpentypeFeaturesBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_opentype_features", GET_OPENTYPE_FEATURES_HASH)
        }

        private const val SET_CACHE_CAPACITY_HASH = 3937882851L
        private val setCacheCapacityBind by lazy {
            ObjectCalls.getMethodBind("Font", "set_cache_capacity", SET_CACHE_CAPACITY_HASH)
        }

        private const val GET_STRING_SIZE_HASH = 1868866121L
        private val getStringSizeBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_string_size", GET_STRING_SIZE_HASH)
        }

        private const val GET_MULTILINE_STRING_SIZE_HASH = 519636710L
        private val getMultilineStringSizeBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_multiline_string_size", GET_MULTILINE_STRING_SIZE_HASH)
        }

        private const val DRAW_STRING_HASH = 1976686372L
        private val drawStringBind by lazy {
            ObjectCalls.getMethodBind("Font", "draw_string", DRAW_STRING_HASH)
        }

        private const val DRAW_MULTILINE_STRING_HASH = 2686601589L
        private val drawMultilineStringBind by lazy {
            ObjectCalls.getMethodBind("Font", "draw_multiline_string", DRAW_MULTILINE_STRING_HASH)
        }

        private const val DRAW_STRING_OUTLINE_HASH = 701417663L
        private val drawStringOutlineBind by lazy {
            ObjectCalls.getMethodBind("Font", "draw_string_outline", DRAW_STRING_OUTLINE_HASH)
        }

        private const val DRAW_MULTILINE_STRING_OUTLINE_HASH = 4147839237L
        private val drawMultilineStringOutlineBind by lazy {
            ObjectCalls.getMethodBind("Font", "draw_multiline_string_outline", DRAW_MULTILINE_STRING_OUTLINE_HASH)
        }

        private const val GET_CHAR_SIZE_HASH = 3016396712L
        private val getCharSizeBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_char_size", GET_CHAR_SIZE_HASH)
        }

        private const val DRAW_CHAR_HASH = 3500170256L
        private val drawCharBind by lazy {
            ObjectCalls.getMethodBind("Font", "draw_char", DRAW_CHAR_HASH)
        }

        private const val DRAW_CHAR_OUTLINE_HASH = 1684114874L
        private val drawCharOutlineBind by lazy {
            ObjectCalls.getMethodBind("Font", "draw_char_outline", DRAW_CHAR_OUTLINE_HASH)
        }

        private const val HAS_CHAR_HASH = 1116898809L
        private val hasCharBind by lazy {
            ObjectCalls.getMethodBind("Font", "has_char", HAS_CHAR_HASH)
        }

        private const val GET_SUPPORTED_CHARS_HASH = 201670096L
        private val getSupportedCharsBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_supported_chars", GET_SUPPORTED_CHARS_HASH)
        }

        private const val IS_LANGUAGE_SUPPORTED_HASH = 3927539163L
        private val isLanguageSupportedBind by lazy {
            ObjectCalls.getMethodBind("Font", "is_language_supported", IS_LANGUAGE_SUPPORTED_HASH)
        }

        private const val IS_SCRIPT_SUPPORTED_HASH = 3927539163L
        private val isScriptSupportedBind by lazy {
            ObjectCalls.getMethodBind("Font", "is_script_supported", IS_SCRIPT_SUPPORTED_HASH)
        }

        private const val GET_SUPPORTED_FEATURE_LIST_HASH = 3102165223L
        private val getSupportedFeatureListBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_supported_feature_list", GET_SUPPORTED_FEATURE_LIST_HASH)
        }

        private const val GET_SUPPORTED_VARIATION_LIST_HASH = 3102165223L
        private val getSupportedVariationListBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_supported_variation_list", GET_SUPPORTED_VARIATION_LIST_HASH)
        }

        private const val GET_FACE_COUNT_HASH = 3905245786L
        private val getFaceCountBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_face_count", GET_FACE_COUNT_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: Font
 */
open class Font(handle: MemorySegment) : Resource(handle) {
    val fallbacks: List<Font>
        @JvmName("fallbacksProperty")
        get() = getFallbacks()

    fun getFallbacks(): List<Font> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getFallbacksBind, handle, Font::fromHandle)
    }

    fun getHeight(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getHeightBind, handle, fontSize)
    }

    fun getAscent(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getAscentBind, handle, fontSize)
    }

    fun getDescent(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getDescentBind, handle, fontSize)
    }

    fun getUnderlinePosition(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getUnderlinePositionBind, handle, fontSize)
    }

    fun getUnderlineThickness(fontSize: Int = 16): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getUnderlineThicknessBind, handle, fontSize)
    }

    fun getFontName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFontNameBind, handle)
    }

    fun getFontStyleName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFontStyleNameBind, handle)
    }

    fun getFontStyle(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFontStyleBind, handle)
    }

    fun getFontWeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFontWeightBind, handle)
    }

    fun getFontStretch(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFontStretchBind, handle)
    }

    fun getPaletteCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPaletteCountBind, handle)
    }

    fun getSpacing(spacing: Long): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetInt(getSpacingBind, handle, spacing)
    }

    fun setCacheCapacity(singleLine: Int, multiLine: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(setCacheCapacityBind, handle, singleLine, multiLine)
    }

    fun getStringSize(text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithStringLongDoubleIntThreeLongArgsRetVector2(getStringSizeBind, handle, text, alignment, width, fontSize, justificationFlags, direction, orientation)
    }

    fun getMultilineStringSize(text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithStringLongDoubleTwoIntFourLongArgsRetVector2(getMultilineStringSizeBind, handle, text, alignment, width, fontSize, maxLines, brkFlags, justificationFlags, direction, orientation)
    }

    fun drawString(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleIntColorThreeLongDoubleArgs(drawStringBind, handle, canvasItem, pos, text, alignment, width, fontSize, modulate, justificationFlags, direction, orientation, oversampling)
    }

    fun drawMultilineString(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(drawMultilineStringBind, handle, canvasItem, pos, text, alignment, width, fontSize, maxLines, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    fun drawStringOutline(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, size: Int = 1, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(drawStringOutlineBind, handle, canvasItem, pos, text, alignment, width, fontSize, size, modulate, justificationFlags, direction, orientation, oversampling)
    }

    fun drawMultilineStringOutline(canvasItem: RID, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, size: Int = 1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(drawMultilineStringOutlineBind, handle, canvasItem, pos, text, alignment, width, fontSize, maxLines, size, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    fun getCharSize(char: Int, fontSize: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(getCharSizeBind, handle, char, fontSize)
    }

    fun drawChar(canvasItem: RID, pos: Vector2, char: Int, fontSize: Int, modulate: Color, oversampling: Double = 0.0): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2TwoIntColorDoubleArgsRetDouble(drawCharBind, handle, canvasItem, pos, char, fontSize, modulate, oversampling)
    }

    fun drawCharOutline(canvasItem: RID, pos: Vector2, char: Int, fontSize: Int, size: Int = -1, modulate: Color, oversampling: Double = 0.0): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2ThreeIntColorDoubleArgsRetDouble(drawCharOutlineBind, handle, canvasItem, pos, char, fontSize, size, modulate, oversampling)
    }

    fun hasChar(char: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(hasCharBind, handle, char)
    }

    fun getSupportedChars(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSupportedCharsBind, handle)
    }

    fun isLanguageSupported(language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isLanguageSupportedBind, handle, language)
    }

    fun isScriptSupported(script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isScriptSupportedBind, handle, script)
    }

    fun getFaceCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFaceCountBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Font? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Font? =
            if (handle.address() == 0L) null else Font(handle)

        private const val GET_FALLBACKS_HASH = 3995934104L
        private val getFallbacksBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_fallbacks", GET_FALLBACKS_HASH)
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

        private const val GET_PALETTE_COUNT_HASH = 3905245786L
        private val getPaletteCountBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_palette_count", GET_PALETTE_COUNT_HASH)
        }

        private const val GET_SPACING_HASH = 1310880908L
        private val getSpacingBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_spacing", GET_SPACING_HASH)
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

        private const val GET_FACE_COUNT_HASH = 3905245786L
        private val getFaceCountBind by lazy {
            ObjectCalls.getMethodBind("Font", "get_face_count", GET_FACE_COUNT_HASH)
        }
    }
}

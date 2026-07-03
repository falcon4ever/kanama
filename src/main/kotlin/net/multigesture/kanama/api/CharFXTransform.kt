package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Controls how an individual character will be displayed in a `RichTextEffect`.
 *
 * Generated from Godot docs: CharFXTransform
 */
class CharFXTransform(handle: MemorySegment) : RefCounted(handle) {
    var transform: Transform2D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    var range: Vector2i
        @JvmName("rangeProperty")
        get() = getRange()
        @JvmName("setRangeProperty")
        set(value) = setRange(value)

    var elapsedTime: Double
        @JvmName("elapsedTimeProperty")
        get() = getElapsedTime()
        @JvmName("setElapsedTimeProperty")
        set(value) = setElapsedTime(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisibility(value)

    var outline: Boolean
        @JvmName("outlineProperty")
        get() = isOutline()
        @JvmName("setOutlineProperty")
        set(value) = setOutline(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var env: Map<String, Any?>
        @JvmName("envProperty")
        get() = getEnvironment()
        @JvmName("setEnvProperty")
        set(value) = setEnvironment(value)

    var glyphIndex: Long
        @JvmName("glyphIndexProperty")
        get() = getGlyphIndex()
        @JvmName("setGlyphIndexProperty")
        set(value) = setGlyphIndex(value)

    var glyphCount: Int
        @JvmName("glyphCountProperty")
        get() = getGlyphCount()
        @JvmName("setGlyphCountProperty")
        set(value) = setGlyphCount(value)

    var glyphFlags: Int
        @JvmName("glyphFlagsProperty")
        get() = getGlyphFlags()
        @JvmName("setGlyphFlagsProperty")
        set(value) = setGlyphFlags(value)

    var relativeIndex: Int
        @JvmName("relativeIndexProperty")
        get() = getRelativeIndex()
        @JvmName("setRelativeIndexProperty")
        set(value) = setRelativeIndex(value)

    var font: RID
        @JvmName("fontProperty")
        get() = getFont()
        @JvmName("setFontProperty")
        set(value) = setFont(value)

    /**
     * The current transform of the current glyph. It can be overridden (for example, by driving the
     * position and rotation from a curve). You can also alter the existing value to apply transforms
     * on top of other effects.
     *
     * Generated from Godot docs: CharFXTransform.get_transform
     */
    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getTransformBind, handle)
    }

    /**
     * The current transform of the current glyph. It can be overridden (for example, by driving the
     * position and rotation from a curve). You can also alter the existing value to apply transforms
     * on top of other effects.
     *
     * Generated from Godot docs: CharFXTransform.set_transform
     */
    fun setTransform(transform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setTransformBind, handle, transform)
    }

    /**
     * Absolute character range in the string, corresponding to the glyph. Note: Read-only. Setting
     * this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_range
     */
    fun getRange(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getRangeBind, handle)
    }

    /**
     * Absolute character range in the string, corresponding to the glyph. Note: Read-only. Setting
     * this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_range
     */
    fun setRange(range: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setRangeBind, handle, range)
    }

    /**
     * The time elapsed since the `RichTextLabel` was added to the scene tree (in seconds). Time stops
     * when the `RichTextLabel` is paused (see `Node.process_mode`). Resets when the text in the
     * `RichTextLabel` is changed. Note: Time still passes while the `RichTextLabel` is hidden.
     *
     * Generated from Godot docs: CharFXTransform.get_elapsed_time
     */
    fun getElapsedTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getElapsedTimeBind, handle)
    }

    /**
     * The time elapsed since the `RichTextLabel` was added to the scene tree (in seconds). Time stops
     * when the `RichTextLabel` is paused (see `Node.process_mode`). Resets when the text in the
     * `RichTextLabel` is changed. Note: Time still passes while the `RichTextLabel` is hidden.
     *
     * Generated from Godot docs: CharFXTransform.set_elapsed_time
     */
    fun setElapsedTime(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setElapsedTimeBind, handle, time)
    }

    /**
     * If `true`, the character will be drawn. If `false`, the character will be hidden. Characters
     * around hidden characters will reflow to take the space of hidden characters. If this is not
     * desired, set their `color` to `Color(1, 1, 1, 0)` instead.
     *
     * Generated from Godot docs: CharFXTransform.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    /**
     * If `true`, the character will be drawn. If `false`, the character will be hidden. Characters
     * around hidden characters will reflow to take the space of hidden characters. If this is not
     * desired, set their `color` to `Color(1, 1, 1, 0)` instead.
     *
     * Generated from Godot docs: CharFXTransform.set_visibility
     */
    fun setVisibility(visibility: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibilityBind, handle, visibility)
    }

    /**
     * If `true`, FX transform is called for outline drawing. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.is_outline
     */
    fun isOutline(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOutlineBind, handle)
    }

    /**
     * If `true`, FX transform is called for outline drawing. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_outline
     */
    fun setOutline(outline: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOutlineBind, handle, outline)
    }

    /**
     * The position offset the character will be drawn with (in pixels).
     *
     * Generated from Godot docs: CharFXTransform.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * The position offset the character will be drawn with (in pixels).
     *
     * Generated from Godot docs: CharFXTransform.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The color the character will be drawn with.
     *
     * Generated from Godot docs: CharFXTransform.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    /**
     * The color the character will be drawn with.
     *
     * Generated from Godot docs: CharFXTransform.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * Contains the arguments passed in the opening BBCode tag. By default, arguments are strings; if
     * their contents match a type such as `bool`, `int` or `float`, they will be converted
     * automatically. Color codes in the form `#rrggbb` or `#rgb` will be converted to an opaque
     * `Color`. String arguments may not contain spaces, even if they're quoted. If present, quotes
     * will also be present in the final string. For example, the opening BBCode tag `[example
     * foo=hello bar=true baz=42 color=#ffffff]` will map to the following `Dictionary`:
     *
     * Generated from Godot docs: CharFXTransform.get_environment
     */
    fun getEnvironment(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getEnvironmentBind, handle)
    }

    /**
     * Contains the arguments passed in the opening BBCode tag. By default, arguments are strings; if
     * their contents match a type such as `bool`, `int` or `float`, they will be converted
     * automatically. Color codes in the form `#rrggbb` or `#rgb` will be converted to an opaque
     * `Color`. String arguments may not contain spaces, even if they're quoted. If present, quotes
     * will also be present in the final string. For example, the opening BBCode tag `[example
     * foo=hello bar=true baz=42 color=#ffffff]` will map to the following `Dictionary`:
     *
     * Generated from Godot docs: CharFXTransform.set_environment
     */
    fun setEnvironment(environment: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setEnvironmentBind, handle, environment)
    }

    /**
     * Glyph index specific to the `font`. If you want to replace this glyph, use
     * `TextServer.font_get_glyph_index` with `font` to get a new glyph index for a single character.
     *
     * Generated from Godot docs: CharFXTransform.get_glyph_index
     */
    fun getGlyphIndex(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getGlyphIndexBind, handle)
    }

    /**
     * Glyph index specific to the `font`. If you want to replace this glyph, use
     * `TextServer.font_get_glyph_index` with `font` to get a new glyph index for a single character.
     *
     * Generated from Godot docs: CharFXTransform.set_glyph_index
     */
    fun setGlyphIndex(glyphIndex: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setGlyphIndexBind, handle, glyphIndex)
    }

    /**
     * The character offset of the glyph, relative to the current `RichTextEffect` custom block. Note:
     * Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_relative_index
     */
    fun getRelativeIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRelativeIndexBind, handle)
    }

    /**
     * The character offset of the glyph, relative to the current `RichTextEffect` custom block. Note:
     * Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_relative_index
     */
    fun setRelativeIndex(relativeIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setRelativeIndexBind, handle, relativeIndex)
    }

    /**
     * Number of glyphs in the grapheme cluster. This value is set in the first glyph of a cluster.
     * Note: Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_glyph_count
     */
    fun getGlyphCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getGlyphCountBind, handle)
    }

    /**
     * Number of glyphs in the grapheme cluster. This value is set in the first glyph of a cluster.
     * Note: Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_glyph_count
     */
    fun setGlyphCount(glyphCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setGlyphCountBind, handle, glyphCount)
    }

    /**
     * Glyph flags. See `TextServer.GraphemeFlag` for more info. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_glyph_flags
     */
    fun getGlyphFlags(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getGlyphFlagsBind, handle)
    }

    /**
     * Glyph flags. See `TextServer.GraphemeFlag` for more info. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_glyph_flags
     */
    fun setGlyphFlags(glyphFlags: Int) {
        ObjectCalls.ptrcallWithIntArg(setGlyphFlagsBind, handle, glyphFlags)
    }

    /**
     * `TextServer` RID of the font used to render glyph, this value can be used with
     * `TextServer.font_*` methods to retrieve font information. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_font
     */
    fun getFont(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getFontBind, handle)
    }

    /**
     * `TextServer` RID of the font used to render glyph, this value can be used with
     * `TextServer.font_*` methods to retrieve font information. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_font
     */
    fun setFont(font: RID) {
        ObjectCalls.ptrcallWithRIDArg(setFontBind, handle, font)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CharFXTransform? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CharFXTransform? =
            if (handle.address() == 0L) null else CharFXTransform(handle)

        private const val GET_TRANSFORM_HASH = 3761352769L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2761652528L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_RANGE_HASH = 2741790807L
        private val getRangeBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_range", GET_RANGE_HASH)
        }

        private const val SET_RANGE_HASH = 1130785943L
        private val setRangeBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_range", SET_RANGE_HASH)
        }

        private const val GET_ELAPSED_TIME_HASH = 191475506L
        private val getElapsedTimeBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_elapsed_time", GET_ELAPSED_TIME_HASH)
        }

        private const val SET_ELAPSED_TIME_HASH = 373806689L
        private val setElapsedTimeBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_elapsed_time", SET_ELAPSED_TIME_HASH)
        }

        private const val IS_VISIBLE_HASH = 2240911060L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "is_visible", IS_VISIBLE_HASH)
        }

        private const val SET_VISIBILITY_HASH = 2586408642L
        private val setVisibilityBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_visibility", SET_VISIBILITY_HASH)
        }

        private const val IS_OUTLINE_HASH = 2240911060L
        private val isOutlineBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "is_outline", IS_OUTLINE_HASH)
        }

        private const val SET_OUTLINE_HASH = 2586408642L
        private val setOutlineBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_outline", SET_OUTLINE_HASH)
        }

        private const val GET_OFFSET_HASH = 1497962370L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_COLOR_HASH = 3200896285L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_color", GET_COLOR_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_color", SET_COLOR_HASH)
        }

        private const val GET_ENVIRONMENT_HASH = 2382534195L
        private val getEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_environment", GET_ENVIRONMENT_HASH)
        }

        private const val SET_ENVIRONMENT_HASH = 4155329257L
        private val setEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_environment", SET_ENVIRONMENT_HASH)
        }

        private const val GET_GLYPH_INDEX_HASH = 3905245786L
        private val getGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_glyph_index", GET_GLYPH_INDEX_HASH)
        }

        private const val SET_GLYPH_INDEX_HASH = 1286410249L
        private val setGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_glyph_index", SET_GLYPH_INDEX_HASH)
        }

        private const val GET_RELATIVE_INDEX_HASH = 3905245786L
        private val getRelativeIndexBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_relative_index", GET_RELATIVE_INDEX_HASH)
        }

        private const val SET_RELATIVE_INDEX_HASH = 1286410249L
        private val setRelativeIndexBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_relative_index", SET_RELATIVE_INDEX_HASH)
        }

        private const val GET_GLYPH_COUNT_HASH = 3905245786L
        private val getGlyphCountBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_glyph_count", GET_GLYPH_COUNT_HASH)
        }

        private const val SET_GLYPH_COUNT_HASH = 1286410249L
        private val setGlyphCountBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_glyph_count", SET_GLYPH_COUNT_HASH)
        }

        private const val GET_GLYPH_FLAGS_HASH = 3905245786L
        private val getGlyphFlagsBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_glyph_flags", GET_GLYPH_FLAGS_HASH)
        }

        private const val SET_GLYPH_FLAGS_HASH = 1286410249L
        private val setGlyphFlagsBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_glyph_flags", SET_GLYPH_FLAGS_HASH)
        }

        private const val GET_FONT_HASH = 2944877500L
        private val getFontBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "get_font", GET_FONT_HASH)
        }

        private const val SET_FONT_HASH = 2722037293L
        private val setFontBind by lazy {
            ObjectCalls.getMethodBind("CharFXTransform", "set_font", SET_FONT_HASH)
        }
    }
}

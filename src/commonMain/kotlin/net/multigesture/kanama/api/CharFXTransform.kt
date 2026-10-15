package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
class CharFXTransform(handle: GodotHandle) : RefCounted(handle) {
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getTransformBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The current transform of the current glyph. It can be overridden (for example, by driving the
     * position and rotation from a curve). You can also alter the existing value to apply transforms
     * on top of other effects.
     *
     * Generated from Godot docs: CharFXTransform.set_transform
     */
    fun setTransform(transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform2DArg(Binds.setTransformBind, segment, transform)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Absolute character range in the string, corresponding to the glyph. Note: Read-only. Setting
     * this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_range
     */
    fun getRange(): Vector2i {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getRangeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Absolute character range in the string, corresponding to the glyph. Note: Read-only. Setting
     * this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_range
     */
    fun setRange(range: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setRangeBind, segment, range)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The time elapsed since the `RichTextLabel` was added to the scene tree (in seconds). Time stops
     * when the `RichTextLabel` is paused (see `Node.process_mode`). Resets when the text in the
     * `RichTextLabel` is changed. Note: Time still passes while the `RichTextLabel` is hidden.
     *
     * Generated from Godot docs: CharFXTransform.get_elapsed_time
     */
    fun getElapsedTime(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getElapsedTimeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The time elapsed since the `RichTextLabel` was added to the scene tree (in seconds). Time stops
     * when the `RichTextLabel` is paused (see `Node.process_mode`). Resets when the text in the
     * `RichTextLabel` is changed. Note: Time still passes while the `RichTextLabel` is hidden.
     *
     * Generated from Godot docs: CharFXTransform.set_elapsed_time
     */
    fun setElapsedTime(time: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setElapsedTimeBind, segment, time)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the character will be drawn. If `false`, the character will be hidden. Characters
     * around hidden characters will reflow to take the space of hidden characters. If this is not
     * desired, set their `color` to `Color(1, 1, 1, 0)` instead.
     *
     * Generated from Godot docs: CharFXTransform.is_visible
     */
    fun isVisible(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the character will be drawn. If `false`, the character will be hidden. Characters
     * around hidden characters will reflow to take the space of hidden characters. If this is not
     * desired, set their `color` to `Color(1, 1, 1, 0)` instead.
     *
     * Generated from Godot docs: CharFXTransform.set_visibility
     */
    fun setVisibility(visibility: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibilityBind, segment, visibility)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, FX transform is called for outline drawing. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.is_outline
     */
    fun isOutline(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isOutlineBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, FX transform is called for outline drawing. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_outline
     */
    fun setOutline(outline: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setOutlineBind, segment, outline)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The position offset the character will be drawn with (in pixels).
     *
     * Generated from Godot docs: CharFXTransform.get_offset
     */
    fun getOffset(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The position offset the character will be drawn with (in pixels).
     *
     * Generated from Godot docs: CharFXTransform.set_offset
     */
    fun setOffset(offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setOffsetBind, segment, offset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The color the character will be drawn with.
     *
     * Generated from Godot docs: CharFXTransform.get_color
     */
    fun getColor(): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The color the character will be drawn with.
     *
     * Generated from Godot docs: CharFXTransform.set_color
     */
    fun setColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Contains the arguments passed in the opening BBCode tag. By default, arguments are strings; if
     * their contents match a type such as `bool`, `int` or `float`, they will be converted
     * automatically. Color codes in the form `#rrggbb` or `#rgb` will be converted to an opaque
     * `Color`. String arguments may not contain spaces, even if they're quoted. If present, quotes
     * will also be present in the final string.
     *
     * Generated from Godot docs: CharFXTransform.get_environment
     */
    fun getEnvironment(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getEnvironmentBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Contains the arguments passed in the opening BBCode tag. By default, arguments are strings; if
     * their contents match a type such as `bool`, `int` or `float`, they will be converted
     * automatically. Color codes in the form `#rrggbb` or `#rgb` will be converted to an opaque
     * `Color`. String arguments may not contain spaces, even if they're quoted. If present, quotes
     * will also be present in the final string.
     *
     * Generated from Godot docs: CharFXTransform.set_environment
     */
    fun setEnvironment(environment: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setEnvironmentBind, segment, environment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Glyph index specific to the `font`. If you want to replace this glyph, use
     * `TextServer.font_get_glyph_index` with `font` to get a new glyph index for a single character.
     *
     * Generated from Godot docs: CharFXTransform.get_glyph_index
     */
    fun getGlyphIndex(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getGlyphIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Glyph index specific to the `font`. If you want to replace this glyph, use
     * `TextServer.font_get_glyph_index` with `font` to get a new glyph index for a single character.
     *
     * Generated from Godot docs: CharFXTransform.set_glyph_index
     */
    fun setGlyphIndex(glyphIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setGlyphIndexBind, segment, glyphIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The character offset of the glyph, relative to the current `RichTextEffect` custom block. Note:
     * Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_relative_index
     */
    fun getRelativeIndex(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getRelativeIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The character offset of the glyph, relative to the current `RichTextEffect` custom block. Note:
     * Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_relative_index
     */
    fun setRelativeIndex(relativeIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setRelativeIndexBind, segment, relativeIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Number of glyphs in the grapheme cluster. This value is set in the first glyph of a cluster.
     * Note: Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_glyph_count
     */
    fun getGlyphCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getGlyphCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Number of glyphs in the grapheme cluster. This value is set in the first glyph of a cluster.
     * Note: Read-only. Setting this property won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_glyph_count
     */
    fun setGlyphCount(glyphCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setGlyphCountBind, segment, glyphCount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Glyph flags. See `TextServer.GraphemeFlag` for more info. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_glyph_flags
     */
    fun getGlyphFlags(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getGlyphFlagsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Glyph flags. See `TextServer.GraphemeFlag` for more info. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_glyph_flags
     */
    fun setGlyphFlags(glyphFlags: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setGlyphFlagsBind, segment, glyphFlags)
        ObjectCalls.keepReachable(this)
    }

    /**
     * `TextServer` RID of the font used to render glyph, this value can be used with
     * `TextServer.font_*` methods to retrieve font information. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.get_font
     */
    fun getFont(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getFontBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * `TextServer` RID of the font used to render glyph, this value can be used with
     * `TextServer.font_*` methods to retrieve font information. Note: Read-only. Setting this property
     * won't affect drawing.
     *
     * Generated from Godot docs: CharFXTransform.set_font
     */
    fun setFont(font: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setFontBind, segment, font)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CharFXTransform? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CharFXTransform? =
            if (handle.address() == 0L) null else RefCounted.owned(CharFXTransform(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CharFXTransform? =
            if (handle.address() == 0L) null else CharFXTransform(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TRANSFORM_HASH = 3761352769L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_transform", GET_TRANSFORM_HASH)

        private const val SET_TRANSFORM_HASH = 2761652528L
        @JvmField
        val setTransformBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_transform", SET_TRANSFORM_HASH)

        private const val GET_RANGE_HASH = 2741790807L
        @JvmField
        val getRangeBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_range", GET_RANGE_HASH)

        private const val SET_RANGE_HASH = 1130785943L
        @JvmField
        val setRangeBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_range", SET_RANGE_HASH)

        private const val GET_ELAPSED_TIME_HASH = 191475506L
        @JvmField
        val getElapsedTimeBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_elapsed_time", GET_ELAPSED_TIME_HASH)

        private const val SET_ELAPSED_TIME_HASH = 373806689L
        @JvmField
        val setElapsedTimeBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_elapsed_time", SET_ELAPSED_TIME_HASH)

        private const val IS_VISIBLE_HASH = 2240911060L
        @JvmField
        val isVisibleBind =
            ObjectCalls.getMethodBind("CharFXTransform", "is_visible", IS_VISIBLE_HASH)

        private const val SET_VISIBILITY_HASH = 2586408642L
        @JvmField
        val setVisibilityBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_visibility", SET_VISIBILITY_HASH)

        private const val IS_OUTLINE_HASH = 2240911060L
        @JvmField
        val isOutlineBind =
            ObjectCalls.getMethodBind("CharFXTransform", "is_outline", IS_OUTLINE_HASH)

        private const val SET_OUTLINE_HASH = 2586408642L
        @JvmField
        val setOutlineBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_outline", SET_OUTLINE_HASH)

        private const val GET_OFFSET_HASH = 1497962370L
        @JvmField
        val getOffsetBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_offset", GET_OFFSET_HASH)

        private const val SET_OFFSET_HASH = 743155724L
        @JvmField
        val setOffsetBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_offset", SET_OFFSET_HASH)

        private const val GET_COLOR_HASH = 3200896285L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_color", GET_COLOR_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_color", SET_COLOR_HASH)

        private const val GET_ENVIRONMENT_HASH = 2382534195L
        @JvmField
        val getEnvironmentBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_environment", GET_ENVIRONMENT_HASH)

        private const val SET_ENVIRONMENT_HASH = 4155329257L
        @JvmField
        val setEnvironmentBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_environment", SET_ENVIRONMENT_HASH)

        private const val GET_GLYPH_INDEX_HASH = 3905245786L
        @JvmField
        val getGlyphIndexBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_glyph_index", GET_GLYPH_INDEX_HASH)

        private const val SET_GLYPH_INDEX_HASH = 1286410249L
        @JvmField
        val setGlyphIndexBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_glyph_index", SET_GLYPH_INDEX_HASH)

        private const val GET_RELATIVE_INDEX_HASH = 3905245786L
        @JvmField
        val getRelativeIndexBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_relative_index", GET_RELATIVE_INDEX_HASH)

        private const val SET_RELATIVE_INDEX_HASH = 1286410249L
        @JvmField
        val setRelativeIndexBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_relative_index", SET_RELATIVE_INDEX_HASH)

        private const val GET_GLYPH_COUNT_HASH = 3905245786L
        @JvmField
        val getGlyphCountBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_glyph_count", GET_GLYPH_COUNT_HASH)

        private const val SET_GLYPH_COUNT_HASH = 1286410249L
        @JvmField
        val setGlyphCountBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_glyph_count", SET_GLYPH_COUNT_HASH)

        private const val GET_GLYPH_FLAGS_HASH = 3905245786L
        @JvmField
        val getGlyphFlagsBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_glyph_flags", GET_GLYPH_FLAGS_HASH)

        private const val SET_GLYPH_FLAGS_HASH = 1286410249L
        @JvmField
        val setGlyphFlagsBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_glyph_flags", SET_GLYPH_FLAGS_HASH)

        private const val GET_FONT_HASH = 2944877500L
        @JvmField
        val getFontBind =
            ObjectCalls.getMethodBind("CharFXTransform", "get_font", GET_FONT_HASH)

        private const val SET_FONT_HASH = 2722037293L
        @JvmField
        val setFontBind =
            ObjectCalls.getMethodBind("CharFXTransform", "set_font", SET_FONT_HASH)
    }
}

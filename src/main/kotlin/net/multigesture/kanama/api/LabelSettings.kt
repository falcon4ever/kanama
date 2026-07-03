package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * Provides common settings to customize the text in a `Label`.
 *
 * Generated from Godot docs: LabelSettings
 */
class LabelSettings(handle: MemorySegment) : Resource(handle) {
    var lineSpacing: Double
        @JvmName("lineSpacingProperty")
        get() = getLineSpacing()
        @JvmName("setLineSpacingProperty")
        set(value) = setLineSpacing(value)

    var paragraphSpacing: Double
        @JvmName("paragraphSpacingProperty")
        get() = getParagraphSpacing()
        @JvmName("setParagraphSpacingProperty")
        set(value) = setParagraphSpacing(value)

    var font: Font?
        @JvmName("fontProperty")
        get() = getFont()
        @JvmName("setFontProperty")
        set(value) = setFont(value)

    var fontSize: Int
        @JvmName("fontSizeProperty")
        get() = getFontSize()
        @JvmName("setFontSizeProperty")
        set(value) = setFontSize(value)

    var fontColor: Color
        @JvmName("fontColorProperty")
        get() = getFontColor()
        @JvmName("setFontColorProperty")
        set(value) = setFontColor(value)

    var outlineSize: Int
        @JvmName("outlineSizeProperty")
        get() = getOutlineSize()
        @JvmName("setOutlineSizeProperty")
        set(value) = setOutlineSize(value)

    var outlineColor: Color
        @JvmName("outlineColorProperty")
        get() = getOutlineColor()
        @JvmName("setOutlineColorProperty")
        set(value) = setOutlineColor(value)

    var shadowSize: Int
        @JvmName("shadowSizeProperty")
        get() = getShadowSize()
        @JvmName("setShadowSizeProperty")
        set(value) = setShadowSize(value)

    var shadowColor: Color
        @JvmName("shadowColorProperty")
        get() = getShadowColor()
        @JvmName("setShadowColorProperty")
        set(value) = setShadowColor(value)

    var shadowOffset: Vector2
        @JvmName("shadowOffsetProperty")
        get() = getShadowOffset()
        @JvmName("setShadowOffsetProperty")
        set(value) = setShadowOffset(value)

    var stackedOutlineCount: Int
        @JvmName("stackedOutlineCountProperty")
        get() = getStackedOutlineCount()
        @JvmName("setStackedOutlineCountProperty")
        set(value) = setStackedOutlineCount(value)

    var stackedShadowCount: Int
        @JvmName("stackedShadowCountProperty")
        get() = getStackedShadowCount()
        @JvmName("setStackedShadowCountProperty")
        set(value) = setStackedShadowCount(value)

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: LabelSettings.set_line_spacing
     */
    fun setLineSpacing(spacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLineSpacingBind, handle, spacing)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: LabelSettings.get_line_spacing
     */
    fun getLineSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineSpacingBind, handle)
    }

    /**
     * Vertical space between paragraphs. Added on top of `line_spacing`.
     *
     * Generated from Godot docs: LabelSettings.set_paragraph_spacing
     */
    fun setParagraphSpacing(spacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setParagraphSpacingBind, handle, spacing)
    }

    /**
     * Vertical space between paragraphs. Added on top of `line_spacing`.
     *
     * Generated from Godot docs: LabelSettings.get_paragraph_spacing
     */
    fun getParagraphSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getParagraphSpacingBind, handle)
    }

    /**
     * `Font` used for the text.
     *
     * Generated from Godot docs: LabelSettings.set_font
     */
    fun setFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Font` used for the text.
     *
     * Generated from Godot docs: LabelSettings.get_font
     */
    fun getFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFontBind, handle))
    }

    /**
     * Size of the text.
     *
     * Generated from Godot docs: LabelSettings.set_font_size
     */
    fun setFontSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontSizeBind, handle, size)
    }

    /**
     * Size of the text.
     *
     * Generated from Godot docs: LabelSettings.get_font_size
     */
    fun getFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFontSizeBind, handle)
    }

    /**
     * Color of the text.
     *
     * Generated from Godot docs: LabelSettings.set_font_color
     */
    fun setFontColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setFontColorBind, handle, color)
    }

    /**
     * Color of the text.
     *
     * Generated from Godot docs: LabelSettings.get_font_color
     */
    fun getFontColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getFontColorBind, handle)
    }

    /**
     * Text outline size.
     *
     * Generated from Godot docs: LabelSettings.set_outline_size
     */
    fun setOutlineSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutlineSizeBind, handle, size)
    }

    /**
     * Text outline size.
     *
     * Generated from Godot docs: LabelSettings.get_outline_size
     */
    fun getOutlineSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutlineSizeBind, handle)
    }

    /**
     * The color of the outline.
     *
     * Generated from Godot docs: LabelSettings.set_outline_color
     */
    fun setOutlineColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setOutlineColorBind, handle, color)
    }

    /**
     * The color of the outline.
     *
     * Generated from Godot docs: LabelSettings.get_outline_color
     */
    fun getOutlineColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getOutlineColorBind, handle)
    }

    /**
     * Size of the shadow effect.
     *
     * Generated from Godot docs: LabelSettings.set_shadow_size
     */
    fun setShadowSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setShadowSizeBind, handle, size)
    }

    /**
     * Size of the shadow effect.
     *
     * Generated from Godot docs: LabelSettings.get_shadow_size
     */
    fun getShadowSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getShadowSizeBind, handle)
    }

    /**
     * Color of the shadow effect. If alpha is `0`, no shadow will be drawn.
     *
     * Generated from Godot docs: LabelSettings.set_shadow_color
     */
    fun setShadowColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setShadowColorBind, handle, color)
    }

    /**
     * Color of the shadow effect. If alpha is `0`, no shadow will be drawn.
     *
     * Generated from Godot docs: LabelSettings.get_shadow_color
     */
    fun getShadowColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getShadowColorBind, handle)
    }

    /**
     * Offset of the shadow effect, in pixels.
     *
     * Generated from Godot docs: LabelSettings.set_shadow_offset
     */
    fun setShadowOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setShadowOffsetBind, handle, offset)
    }

    /**
     * Offset of the shadow effect, in pixels.
     *
     * Generated from Godot docs: LabelSettings.get_shadow_offset
     */
    fun getShadowOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getShadowOffsetBind, handle)
    }

    /**
     * The number of stacked outlines.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_outline_count
     */
    fun getStackedOutlineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStackedOutlineCountBind, handle)
    }

    /**
     * The number of stacked outlines.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_outline_count
     */
    fun setStackedOutlineCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setStackedOutlineCountBind, handle, count)
    }

    /**
     * Adds a new stacked outline to the label at the given `index`. If `index` is `-1`, the new
     * stacked outline will be added at the end of the list.
     *
     * Generated from Godot docs: LabelSettings.add_stacked_outline
     */
    fun addStackedOutline(index: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addStackedOutlineBind, handle, index)
    }

    /**
     * Moves the stacked outline at index `from_index` to the given position `to_position` in the
     * array.
     *
     * Generated from Godot docs: LabelSettings.move_stacked_outline
     */
    fun moveStackedOutline(fromIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveStackedOutlineBind, handle, fromIndex, toPosition)
    }

    /**
     * Removes the stacked outline at index `index`.
     *
     * Generated from Godot docs: LabelSettings.remove_stacked_outline
     */
    fun removeStackedOutline(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeStackedOutlineBind, handle, index)
    }

    /**
     * Sets the size of the stacked outline identified by the given `index` to `size`.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_outline_size
     */
    fun setStackedOutlineSize(index: Int, size: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setStackedOutlineSizeBind, handle, index, size)
    }

    /**
     * Returns the size of the stacked outline at `index`.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_outline_size
     */
    fun getStackedOutlineSize(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getStackedOutlineSizeBind, handle, index)
    }

    /**
     * Sets the color of the stacked outline identified by the given `index` to `color`.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_outline_color
     */
    fun setStackedOutlineColor(index: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setStackedOutlineColorBind, handle, index, color)
    }

    /**
     * Returns the color of the stacked outline at `index`.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_outline_color
     */
    fun getStackedOutlineColor(index: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getStackedOutlineColorBind, handle, index)
    }

    /**
     * The number of stacked shadows.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_shadow_count
     */
    fun getStackedShadowCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStackedShadowCountBind, handle)
    }

    /**
     * The number of stacked shadows.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_shadow_count
     */
    fun setStackedShadowCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setStackedShadowCountBind, handle, count)
    }

    /**
     * Adds a new stacked shadow to the label at the given `index`. If `index` is `-1`, the new stacked
     * shadow will be added at the end of the list.
     *
     * Generated from Godot docs: LabelSettings.add_stacked_shadow
     */
    fun addStackedShadow(index: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addStackedShadowBind, handle, index)
    }

    /**
     * Moves the stacked shadow at index `from_index` to the given position `to_position` in the array.
     *
     * Generated from Godot docs: LabelSettings.move_stacked_shadow
     */
    fun moveStackedShadow(fromIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveStackedShadowBind, handle, fromIndex, toPosition)
    }

    /**
     * Removes the stacked shadow at index `index`.
     *
     * Generated from Godot docs: LabelSettings.remove_stacked_shadow
     */
    fun removeStackedShadow(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeStackedShadowBind, handle, index)
    }

    /**
     * Sets the offset of the stacked shadow identified by the given `index` to `offset`.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_shadow_offset
     */
    fun setStackedShadowOffset(index: Int, offset: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setStackedShadowOffsetBind, handle, index, offset)
    }

    /**
     * Returns the offset of the stacked shadow at `index`.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_shadow_offset
     */
    fun getStackedShadowOffset(index: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getStackedShadowOffsetBind, handle, index)
    }

    /**
     * Sets the color of the stacked shadow identified by the given `index` to `color`.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_shadow_color
     */
    fun setStackedShadowColor(index: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setStackedShadowColorBind, handle, index, color)
    }

    /**
     * Returns the color of the stacked shadow at `index`.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_shadow_color
     */
    fun getStackedShadowColor(index: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getStackedShadowColorBind, handle, index)
    }

    /**
     * Sets the outline size of the stacked shadow identified by the given `index` to `size`.
     *
     * Generated from Godot docs: LabelSettings.set_stacked_shadow_outline_size
     */
    fun setStackedShadowOutlineSize(index: Int, size: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setStackedShadowOutlineSizeBind, handle, index, size)
    }

    /**
     * Returns the outline size of the stacked shadow at `index`.
     *
     * Generated from Godot docs: LabelSettings.get_stacked_shadow_outline_size
     */
    fun getStackedShadowOutlineSize(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getStackedShadowOutlineSizeBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): LabelSettings? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LabelSettings? =
            if (handle.address() == 0L) null else LabelSettings(handle)

        private const val SET_LINE_SPACING_HASH = 373806689L
        private val setLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_line_spacing", SET_LINE_SPACING_HASH)
        }

        private const val GET_LINE_SPACING_HASH = 1740695150L
        private val getLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_line_spacing", GET_LINE_SPACING_HASH)
        }

        private const val SET_PARAGRAPH_SPACING_HASH = 373806689L
        private val setParagraphSpacingBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_paragraph_spacing", SET_PARAGRAPH_SPACING_HASH)
        }

        private const val GET_PARAGRAPH_SPACING_HASH = 1740695150L
        private val getParagraphSpacingBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_paragraph_spacing", GET_PARAGRAPH_SPACING_HASH)
        }

        private const val SET_FONT_HASH = 1262170328L
        private val setFontBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_font", SET_FONT_HASH)
        }

        private const val GET_FONT_HASH = 3229501585L
        private val getFontBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_font", GET_FONT_HASH)
        }

        private const val SET_FONT_SIZE_HASH = 1286410249L
        private val setFontSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_font_size", SET_FONT_SIZE_HASH)
        }

        private const val GET_FONT_SIZE_HASH = 3905245786L
        private val getFontSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_font_size", GET_FONT_SIZE_HASH)
        }

        private const val SET_FONT_COLOR_HASH = 2920490490L
        private val setFontColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_font_color", SET_FONT_COLOR_HASH)
        }

        private const val GET_FONT_COLOR_HASH = 3444240500L
        private val getFontColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_font_color", GET_FONT_COLOR_HASH)
        }

        private const val SET_OUTLINE_SIZE_HASH = 1286410249L
        private val setOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_outline_size", SET_OUTLINE_SIZE_HASH)
        }

        private const val GET_OUTLINE_SIZE_HASH = 3905245786L
        private val getOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_outline_size", GET_OUTLINE_SIZE_HASH)
        }

        private const val SET_OUTLINE_COLOR_HASH = 2920490490L
        private val setOutlineColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_outline_color", SET_OUTLINE_COLOR_HASH)
        }

        private const val GET_OUTLINE_COLOR_HASH = 3444240500L
        private val getOutlineColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_outline_color", GET_OUTLINE_COLOR_HASH)
        }

        private const val SET_SHADOW_SIZE_HASH = 1286410249L
        private val setShadowSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_shadow_size", SET_SHADOW_SIZE_HASH)
        }

        private const val GET_SHADOW_SIZE_HASH = 3905245786L
        private val getShadowSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_shadow_size", GET_SHADOW_SIZE_HASH)
        }

        private const val SET_SHADOW_COLOR_HASH = 2920490490L
        private val setShadowColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_shadow_color", SET_SHADOW_COLOR_HASH)
        }

        private const val GET_SHADOW_COLOR_HASH = 3444240500L
        private val getShadowColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_shadow_color", GET_SHADOW_COLOR_HASH)
        }

        private const val SET_SHADOW_OFFSET_HASH = 743155724L
        private val setShadowOffsetBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_shadow_offset", SET_SHADOW_OFFSET_HASH)
        }

        private const val GET_SHADOW_OFFSET_HASH = 3341600327L
        private val getShadowOffsetBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_shadow_offset", GET_SHADOW_OFFSET_HASH)
        }

        private const val GET_STACKED_OUTLINE_COUNT_HASH = 3905245786L
        private val getStackedOutlineCountBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_outline_count", GET_STACKED_OUTLINE_COUNT_HASH)
        }

        private const val SET_STACKED_OUTLINE_COUNT_HASH = 1286410249L
        private val setStackedOutlineCountBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_outline_count", SET_STACKED_OUTLINE_COUNT_HASH)
        }

        private const val ADD_STACKED_OUTLINE_HASH = 1025054187L
        private val addStackedOutlineBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "add_stacked_outline", ADD_STACKED_OUTLINE_HASH)
        }

        private const val MOVE_STACKED_OUTLINE_HASH = 3937882851L
        private val moveStackedOutlineBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "move_stacked_outline", MOVE_STACKED_OUTLINE_HASH)
        }

        private const val REMOVE_STACKED_OUTLINE_HASH = 1286410249L
        private val removeStackedOutlineBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "remove_stacked_outline", REMOVE_STACKED_OUTLINE_HASH)
        }

        private const val SET_STACKED_OUTLINE_SIZE_HASH = 3937882851L
        private val setStackedOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_outline_size", SET_STACKED_OUTLINE_SIZE_HASH)
        }

        private const val GET_STACKED_OUTLINE_SIZE_HASH = 923996154L
        private val getStackedOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_outline_size", GET_STACKED_OUTLINE_SIZE_HASH)
        }

        private const val SET_STACKED_OUTLINE_COLOR_HASH = 2878471219L
        private val setStackedOutlineColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_outline_color", SET_STACKED_OUTLINE_COLOR_HASH)
        }

        private const val GET_STACKED_OUTLINE_COLOR_HASH = 3457211756L
        private val getStackedOutlineColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_outline_color", GET_STACKED_OUTLINE_COLOR_HASH)
        }

        private const val GET_STACKED_SHADOW_COUNT_HASH = 3905245786L
        private val getStackedShadowCountBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_shadow_count", GET_STACKED_SHADOW_COUNT_HASH)
        }

        private const val SET_STACKED_SHADOW_COUNT_HASH = 1286410249L
        private val setStackedShadowCountBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_shadow_count", SET_STACKED_SHADOW_COUNT_HASH)
        }

        private const val ADD_STACKED_SHADOW_HASH = 1025054187L
        private val addStackedShadowBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "add_stacked_shadow", ADD_STACKED_SHADOW_HASH)
        }

        private const val MOVE_STACKED_SHADOW_HASH = 3937882851L
        private val moveStackedShadowBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "move_stacked_shadow", MOVE_STACKED_SHADOW_HASH)
        }

        private const val REMOVE_STACKED_SHADOW_HASH = 1286410249L
        private val removeStackedShadowBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "remove_stacked_shadow", REMOVE_STACKED_SHADOW_HASH)
        }

        private const val SET_STACKED_SHADOW_OFFSET_HASH = 163021252L
        private val setStackedShadowOffsetBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_shadow_offset", SET_STACKED_SHADOW_OFFSET_HASH)
        }

        private const val GET_STACKED_SHADOW_OFFSET_HASH = 2299179447L
        private val getStackedShadowOffsetBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_shadow_offset", GET_STACKED_SHADOW_OFFSET_HASH)
        }

        private const val SET_STACKED_SHADOW_COLOR_HASH = 2878471219L
        private val setStackedShadowColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_shadow_color", SET_STACKED_SHADOW_COLOR_HASH)
        }

        private const val GET_STACKED_SHADOW_COLOR_HASH = 3457211756L
        private val getStackedShadowColorBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_shadow_color", GET_STACKED_SHADOW_COLOR_HASH)
        }

        private const val SET_STACKED_SHADOW_OUTLINE_SIZE_HASH = 3937882851L
        private val setStackedShadowOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "set_stacked_shadow_outline_size", SET_STACKED_SHADOW_OUTLINE_SIZE_HASH)
        }

        private const val GET_STACKED_SHADOW_OUTLINE_SIZE_HASH = 923996154L
        private val getStackedShadowOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("LabelSettings", "get_stacked_shadow_outline_size", GET_STACKED_SHADOW_OUTLINE_SIZE_HASH)
        }
    }
}

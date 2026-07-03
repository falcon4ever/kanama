package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A syntax highlighter intended for code.
 *
 * Generated from Godot docs: CodeHighlighter
 */
class CodeHighlighter(handle: MemorySegment) : SyntaxHighlighter(handle) {
    var numberColor: Color
        @JvmName("numberColorProperty")
        get() = getNumberColor()
        @JvmName("setNumberColorProperty")
        set(value) = setNumberColor(value)

    var symbolColor: Color
        @JvmName("symbolColorProperty")
        get() = getSymbolColor()
        @JvmName("setSymbolColorProperty")
        set(value) = setSymbolColor(value)

    var functionColor: Color
        @JvmName("functionColorProperty")
        get() = getFunctionColor()
        @JvmName("setFunctionColorProperty")
        set(value) = setFunctionColor(value)

    var memberVariableColor: Color
        @JvmName("memberVariableColorProperty")
        get() = getMemberVariableColor()
        @JvmName("setMemberVariableColorProperty")
        set(value) = setMemberVariableColor(value)

    var keywordColors: Map<String, Any?>
        @JvmName("keywordColorsProperty")
        get() = getKeywordColors()
        @JvmName("setKeywordColorsProperty")
        set(value) = setKeywordColors(value)

    var memberKeywordColors: Map<String, Any?>
        @JvmName("memberKeywordColorsProperty")
        get() = getMemberKeywordColors()
        @JvmName("setMemberKeywordColorsProperty")
        set(value) = setMemberKeywordColors(value)

    var colorRegions: Map<String, Any?>
        @JvmName("colorRegionsProperty")
        get() = getColorRegions()
        @JvmName("setColorRegionsProperty")
        set(value) = setColorRegions(value)

    /**
     * Sets the color for a keyword. The keyword cannot contain any symbols except '_'.
     *
     * Generated from Godot docs: CodeHighlighter.add_keyword_color
     */
    fun addKeywordColor(keyword: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(addKeywordColorBind, handle, keyword, color)
    }

    /**
     * Removes the keyword.
     *
     * Generated from Godot docs: CodeHighlighter.remove_keyword_color
     */
    fun removeKeywordColor(keyword: String) {
        ObjectCalls.ptrcallWithStringArg(removeKeywordColorBind, handle, keyword)
    }

    /**
     * Returns `true` if the keyword exists, else `false`.
     *
     * Generated from Godot docs: CodeHighlighter.has_keyword_color
     */
    fun hasKeywordColor(keyword: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasKeywordColorBind, handle, keyword)
    }

    /**
     * Returns the color for a keyword.
     *
     * Generated from Godot docs: CodeHighlighter.get_keyword_color
     */
    fun getKeywordColor(keyword: String): Color {
        return ObjectCalls.ptrcallWithStringArgRetColor(getKeywordColorBind, handle, keyword)
    }

    /**
     * Sets the keyword colors. All existing keywords will be removed. The `Dictionary` key is the
     * keyword. The value is the keyword color.
     *
     * Generated from Godot docs: CodeHighlighter.set_keyword_colors
     */
    fun setKeywordColors(keywords: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setKeywordColorsBind, handle, keywords)
    }

    /**
     * Removes all keywords.
     *
     * Generated from Godot docs: CodeHighlighter.clear_keyword_colors
     */
    fun clearKeywordColors() {
        ObjectCalls.ptrcallNoArgs(clearKeywordColorsBind, handle)
    }

    /**
     * Sets the keyword colors. All existing keywords will be removed. The `Dictionary` key is the
     * keyword. The value is the keyword color.
     *
     * Generated from Godot docs: CodeHighlighter.get_keyword_colors
     */
    fun getKeywordColors(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getKeywordColorsBind, handle)
    }

    /**
     * Sets the color for a member keyword. The member keyword cannot contain any symbols except '_'.
     * It will not be highlighted if preceded by a '.'.
     *
     * Generated from Godot docs: CodeHighlighter.add_member_keyword_color
     */
    fun addMemberKeywordColor(memberKeyword: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(addMemberKeywordColorBind, handle, memberKeyword, color)
    }

    /**
     * Removes the member keyword.
     *
     * Generated from Godot docs: CodeHighlighter.remove_member_keyword_color
     */
    fun removeMemberKeywordColor(memberKeyword: String) {
        ObjectCalls.ptrcallWithStringArg(removeMemberKeywordColorBind, handle, memberKeyword)
    }

    /**
     * Returns `true` if the member keyword exists, else `false`.
     *
     * Generated from Godot docs: CodeHighlighter.has_member_keyword_color
     */
    fun hasMemberKeywordColor(memberKeyword: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasMemberKeywordColorBind, handle, memberKeyword)
    }

    /**
     * Returns the color for a member keyword.
     *
     * Generated from Godot docs: CodeHighlighter.get_member_keyword_color
     */
    fun getMemberKeywordColor(memberKeyword: String): Color {
        return ObjectCalls.ptrcallWithStringArgRetColor(getMemberKeywordColorBind, handle, memberKeyword)
    }

    /**
     * Sets the member keyword colors. All existing member keyword will be removed. The `Dictionary`
     * key is the member keyword. The value is the member keyword color.
     *
     * Generated from Godot docs: CodeHighlighter.set_member_keyword_colors
     */
    fun setMemberKeywordColors(memberKeyword: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setMemberKeywordColorsBind, handle, memberKeyword)
    }

    /**
     * Removes all member keywords.
     *
     * Generated from Godot docs: CodeHighlighter.clear_member_keyword_colors
     */
    fun clearMemberKeywordColors() {
        ObjectCalls.ptrcallNoArgs(clearMemberKeywordColorsBind, handle)
    }

    /**
     * Sets the member keyword colors. All existing member keyword will be removed. The `Dictionary`
     * key is the member keyword. The value is the member keyword color.
     *
     * Generated from Godot docs: CodeHighlighter.get_member_keyword_colors
     */
    fun getMemberKeywordColors(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getMemberKeywordColorsBind, handle)
    }

    /**
     * Adds a color region (such as for comments or strings) from `start_key` to `end_key`. Both keys
     * should be symbols, and `start_key` must not be shared with other delimiters. If `line_only` is
     * `true` or `end_key` is an empty `String`, the region does not carry over to the next line.
     *
     * Generated from Godot docs: CodeHighlighter.add_color_region
     */
    fun addColorRegion(startKey: String, endKey: String, color: Color, lineOnly: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringColorBoolArgs(addColorRegionBind, handle, startKey, endKey, color, lineOnly)
    }

    /**
     * Removes the color region that uses that start key.
     *
     * Generated from Godot docs: CodeHighlighter.remove_color_region
     */
    fun removeColorRegion(startKey: String) {
        ObjectCalls.ptrcallWithStringArg(removeColorRegionBind, handle, startKey)
    }

    /**
     * Returns `true` if the start key exists, else `false`.
     *
     * Generated from Godot docs: CodeHighlighter.has_color_region
     */
    fun hasColorRegion(startKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasColorRegionBind, handle, startKey)
    }

    /**
     * Sets the color regions. All existing regions will be removed. The `Dictionary` key is the region
     * start and end key, separated by a space. The value is the region color.
     *
     * Generated from Godot docs: CodeHighlighter.set_color_regions
     */
    fun setColorRegions(colorRegions: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setColorRegionsBind, handle, colorRegions)
    }

    /**
     * Removes all color regions.
     *
     * Generated from Godot docs: CodeHighlighter.clear_color_regions
     */
    fun clearColorRegions() {
        ObjectCalls.ptrcallNoArgs(clearColorRegionsBind, handle)
    }

    /**
     * Sets the color regions. All existing regions will be removed. The `Dictionary` key is the region
     * start and end key, separated by a space. The value is the region color.
     *
     * Generated from Godot docs: CodeHighlighter.get_color_regions
     */
    fun getColorRegions(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getColorRegionsBind, handle)
    }

    /**
     * Sets color for functions. A function is a non-keyword string followed by a '('.
     *
     * Generated from Godot docs: CodeHighlighter.set_function_color
     */
    fun setFunctionColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setFunctionColorBind, handle, color)
    }

    /**
     * Sets color for functions. A function is a non-keyword string followed by a '('.
     *
     * Generated from Godot docs: CodeHighlighter.get_function_color
     */
    fun getFunctionColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getFunctionColorBind, handle)
    }

    /**
     * Sets the color for numbers.
     *
     * Generated from Godot docs: CodeHighlighter.set_number_color
     */
    fun setNumberColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setNumberColorBind, handle, color)
    }

    /**
     * Sets the color for numbers.
     *
     * Generated from Godot docs: CodeHighlighter.get_number_color
     */
    fun getNumberColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getNumberColorBind, handle)
    }

    /**
     * Sets the color for symbols.
     *
     * Generated from Godot docs: CodeHighlighter.set_symbol_color
     */
    fun setSymbolColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setSymbolColorBind, handle, color)
    }

    /**
     * Sets the color for symbols.
     *
     * Generated from Godot docs: CodeHighlighter.get_symbol_color
     */
    fun getSymbolColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getSymbolColorBind, handle)
    }

    /**
     * Sets color for member variables. A member variable is non-keyword, non-function string proceeded
     * with a '.'.
     *
     * Generated from Godot docs: CodeHighlighter.set_member_variable_color
     */
    fun setMemberVariableColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setMemberVariableColorBind, handle, color)
    }

    /**
     * Sets color for member variables. A member variable is non-keyword, non-function string proceeded
     * with a '.'.
     *
     * Generated from Godot docs: CodeHighlighter.get_member_variable_color
     */
    fun getMemberVariableColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getMemberVariableColorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CodeHighlighter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CodeHighlighter? =
            if (handle.address() == 0L) null else CodeHighlighter(handle)

        private const val ADD_KEYWORD_COLOR_HASH = 1636512886L
        private val addKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "add_keyword_color", ADD_KEYWORD_COLOR_HASH)
        }

        private const val REMOVE_KEYWORD_COLOR_HASH = 83702148L
        private val removeKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "remove_keyword_color", REMOVE_KEYWORD_COLOR_HASH)
        }

        private const val HAS_KEYWORD_COLOR_HASH = 3927539163L
        private val hasKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "has_keyword_color", HAS_KEYWORD_COLOR_HASH)
        }

        private const val GET_KEYWORD_COLOR_HASH = 3855908743L
        private val getKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_keyword_color", GET_KEYWORD_COLOR_HASH)
        }

        private const val SET_KEYWORD_COLORS_HASH = 4155329257L
        private val setKeywordColorsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_keyword_colors", SET_KEYWORD_COLORS_HASH)
        }

        private const val CLEAR_KEYWORD_COLORS_HASH = 3218959716L
        private val clearKeywordColorsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "clear_keyword_colors", CLEAR_KEYWORD_COLORS_HASH)
        }

        private const val GET_KEYWORD_COLORS_HASH = 3102165223L
        private val getKeywordColorsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_keyword_colors", GET_KEYWORD_COLORS_HASH)
        }

        private const val ADD_MEMBER_KEYWORD_COLOR_HASH = 1636512886L
        private val addMemberKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "add_member_keyword_color", ADD_MEMBER_KEYWORD_COLOR_HASH)
        }

        private const val REMOVE_MEMBER_KEYWORD_COLOR_HASH = 83702148L
        private val removeMemberKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "remove_member_keyword_color", REMOVE_MEMBER_KEYWORD_COLOR_HASH)
        }

        private const val HAS_MEMBER_KEYWORD_COLOR_HASH = 3927539163L
        private val hasMemberKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "has_member_keyword_color", HAS_MEMBER_KEYWORD_COLOR_HASH)
        }

        private const val GET_MEMBER_KEYWORD_COLOR_HASH = 3855908743L
        private val getMemberKeywordColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_member_keyword_color", GET_MEMBER_KEYWORD_COLOR_HASH)
        }

        private const val SET_MEMBER_KEYWORD_COLORS_HASH = 4155329257L
        private val setMemberKeywordColorsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_member_keyword_colors", SET_MEMBER_KEYWORD_COLORS_HASH)
        }

        private const val CLEAR_MEMBER_KEYWORD_COLORS_HASH = 3218959716L
        private val clearMemberKeywordColorsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "clear_member_keyword_colors", CLEAR_MEMBER_KEYWORD_COLORS_HASH)
        }

        private const val GET_MEMBER_KEYWORD_COLORS_HASH = 3102165223L
        private val getMemberKeywordColorsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_member_keyword_colors", GET_MEMBER_KEYWORD_COLORS_HASH)
        }

        private const val ADD_COLOR_REGION_HASH = 2924977451L
        private val addColorRegionBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "add_color_region", ADD_COLOR_REGION_HASH)
        }

        private const val REMOVE_COLOR_REGION_HASH = 83702148L
        private val removeColorRegionBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "remove_color_region", REMOVE_COLOR_REGION_HASH)
        }

        private const val HAS_COLOR_REGION_HASH = 3927539163L
        private val hasColorRegionBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "has_color_region", HAS_COLOR_REGION_HASH)
        }

        private const val SET_COLOR_REGIONS_HASH = 4155329257L
        private val setColorRegionsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_color_regions", SET_COLOR_REGIONS_HASH)
        }

        private const val CLEAR_COLOR_REGIONS_HASH = 3218959716L
        private val clearColorRegionsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "clear_color_regions", CLEAR_COLOR_REGIONS_HASH)
        }

        private const val GET_COLOR_REGIONS_HASH = 3102165223L
        private val getColorRegionsBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_color_regions", GET_COLOR_REGIONS_HASH)
        }

        private const val SET_FUNCTION_COLOR_HASH = 2920490490L
        private val setFunctionColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_function_color", SET_FUNCTION_COLOR_HASH)
        }

        private const val GET_FUNCTION_COLOR_HASH = 3444240500L
        private val getFunctionColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_function_color", GET_FUNCTION_COLOR_HASH)
        }

        private const val SET_NUMBER_COLOR_HASH = 2920490490L
        private val setNumberColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_number_color", SET_NUMBER_COLOR_HASH)
        }

        private const val GET_NUMBER_COLOR_HASH = 3444240500L
        private val getNumberColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_number_color", GET_NUMBER_COLOR_HASH)
        }

        private const val SET_SYMBOL_COLOR_HASH = 2920490490L
        private val setSymbolColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_symbol_color", SET_SYMBOL_COLOR_HASH)
        }

        private const val GET_SYMBOL_COLOR_HASH = 3444240500L
        private val getSymbolColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_symbol_color", GET_SYMBOL_COLOR_HASH)
        }

        private const val SET_MEMBER_VARIABLE_COLOR_HASH = 2920490490L
        private val setMemberVariableColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "set_member_variable_color", SET_MEMBER_VARIABLE_COLOR_HASH)
        }

        private const val GET_MEMBER_VARIABLE_COLOR_HASH = 3444240500L
        private val getMemberVariableColorBind by lazy {
            ObjectCalls.getMethodBind("CodeHighlighter", "get_member_variable_color", GET_MEMBER_VARIABLE_COLOR_HASH)
        }
    }
}

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

    fun addKeywordColor(keyword: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(addKeywordColorBind, handle, keyword, color)
    }

    fun removeKeywordColor(keyword: String) {
        ObjectCalls.ptrcallWithStringArg(removeKeywordColorBind, handle, keyword)
    }

    fun hasKeywordColor(keyword: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasKeywordColorBind, handle, keyword)
    }

    fun getKeywordColor(keyword: String): Color {
        return ObjectCalls.ptrcallWithStringArgRetColor(getKeywordColorBind, handle, keyword)
    }

    fun setKeywordColors(keywords: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setKeywordColorsBind, handle, keywords)
    }

    fun clearKeywordColors() {
        ObjectCalls.ptrcallNoArgs(clearKeywordColorsBind, handle)
    }

    fun getKeywordColors(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getKeywordColorsBind, handle)
    }

    fun addMemberKeywordColor(memberKeyword: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(addMemberKeywordColorBind, handle, memberKeyword, color)
    }

    fun removeMemberKeywordColor(memberKeyword: String) {
        ObjectCalls.ptrcallWithStringArg(removeMemberKeywordColorBind, handle, memberKeyword)
    }

    fun hasMemberKeywordColor(memberKeyword: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasMemberKeywordColorBind, handle, memberKeyword)
    }

    fun getMemberKeywordColor(memberKeyword: String): Color {
        return ObjectCalls.ptrcallWithStringArgRetColor(getMemberKeywordColorBind, handle, memberKeyword)
    }

    fun setMemberKeywordColors(memberKeyword: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setMemberKeywordColorsBind, handle, memberKeyword)
    }

    fun clearMemberKeywordColors() {
        ObjectCalls.ptrcallNoArgs(clearMemberKeywordColorsBind, handle)
    }

    fun getMemberKeywordColors(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getMemberKeywordColorsBind, handle)
    }

    fun addColorRegion(startKey: String, endKey: String, color: Color, lineOnly: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringColorBoolArgs(addColorRegionBind, handle, startKey, endKey, color, lineOnly)
    }

    fun removeColorRegion(startKey: String) {
        ObjectCalls.ptrcallWithStringArg(removeColorRegionBind, handle, startKey)
    }

    fun hasColorRegion(startKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasColorRegionBind, handle, startKey)
    }

    fun setColorRegions(colorRegions: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setColorRegionsBind, handle, colorRegions)
    }

    fun clearColorRegions() {
        ObjectCalls.ptrcallNoArgs(clearColorRegionsBind, handle)
    }

    fun getColorRegions(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getColorRegionsBind, handle)
    }

    fun setFunctionColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setFunctionColorBind, handle, color)
    }

    fun getFunctionColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getFunctionColorBind, handle)
    }

    fun setNumberColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setNumberColorBind, handle, color)
    }

    fun getNumberColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getNumberColorBind, handle)
    }

    fun setSymbolColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setSymbolColorBind, handle, color)
    }

    fun getSymbolColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getSymbolColorBind, handle)
    }

    fun setMemberVariableColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setMemberVariableColorBind, handle, color)
    }

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

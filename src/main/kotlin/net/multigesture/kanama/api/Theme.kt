package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A resource used for styling/skinning `Control`s and `Window`s.
 *
 * Generated from Godot docs: Theme
 */
class Theme(handle: MemorySegment) : Resource(handle) {
    var defaultBaseScale: Double
        @JvmName("defaultBaseScaleProperty")
        get() = getDefaultBaseScale()
        @JvmName("setDefaultBaseScaleProperty")
        set(value) = setDefaultBaseScale(value)

    var defaultFont: Font?
        @JvmName("defaultFontProperty")
        get() = getDefaultFont()
        @JvmName("setDefaultFontProperty")
        set(value) = setDefaultFont(value)

    var defaultFontSize: Int
        @JvmName("defaultFontSizeProperty")
        get() = getDefaultFontSize()
        @JvmName("setDefaultFontSizeProperty")
        set(value) = setDefaultFontSize(value)

    fun setIcon(name: String, themeType: String, texture: Texture2D?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(setIconBind, handle, name, themeType, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getIcon(name: String, themeType: String): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getIconBind, handle, name, themeType))
    }

    fun hasIcon(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasIconBind, handle, name, themeType)
    }

    fun renameIcon(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameIconBind, handle, oldName, name, themeType)
    }

    fun clearIcon(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearIconBind, handle, name, themeType)
    }

    fun getIconList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getIconListBind, handle, themeType)
    }

    fun getIconTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getIconTypeListBind, handle)
    }

    fun setStylebox(name: String, themeType: String, texture: StyleBox?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(setStyleboxBind, handle, name, themeType, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getStylebox(name: String, themeType: String): StyleBox? {
        return StyleBox.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getStyleboxBind, handle, name, themeType))
    }

    fun hasStylebox(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasStyleboxBind, handle, name, themeType)
    }

    fun renameStylebox(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameStyleboxBind, handle, oldName, name, themeType)
    }

    fun clearStylebox(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearStyleboxBind, handle, name, themeType)
    }

    fun getStyleboxList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getStyleboxListBind, handle, themeType)
    }

    fun getStyleboxTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getStyleboxTypeListBind, handle)
    }

    fun setFont(name: String, themeType: String, font: Font?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(setFontBind, handle, name, themeType, font?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getFont(name: String, themeType: String): Font? {
        return Font.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getFontBind, handle, name, themeType))
    }

    fun hasFont(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasFontBind, handle, name, themeType)
    }

    fun renameFont(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameFontBind, handle, oldName, name, themeType)
    }

    fun clearFont(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearFontBind, handle, name, themeType)
    }

    fun getFontList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFontListBind, handle, themeType)
    }

    fun getFontTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFontTypeListBind, handle)
    }

    fun setFontSize(name: String, themeType: String, fontSize: Int) {
        ObjectCalls.ptrcallWithTwoStringNameAndIntArg(setFontSizeBind, handle, name, themeType, fontSize)
    }

    fun getFontSize(name: String, themeType: String): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getFontSizeBind, handle, name, themeType)
    }

    fun hasFontSize(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasFontSizeBind, handle, name, themeType)
    }

    fun renameFontSize(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameFontSizeBind, handle, oldName, name, themeType)
    }

    fun clearFontSize(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearFontSizeBind, handle, name, themeType)
    }

    fun getFontSizeList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFontSizeListBind, handle, themeType)
    }

    fun getFontSizeTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFontSizeTypeListBind, handle)
    }

    fun setColor(name: String, themeType: String, color: Color) {
        ObjectCalls.ptrcallWithTwoStringNameAndColorArg(setColorBind, handle, name, themeType, color)
    }

    fun getColor(name: String, themeType: String): Color {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetColor(getColorBind, handle, name, themeType)
    }

    fun hasColor(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasColorBind, handle, name, themeType)
    }

    fun renameColor(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameColorBind, handle, oldName, name, themeType)
    }

    fun clearColor(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearColorBind, handle, name, themeType)
    }

    fun getColorList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getColorListBind, handle, themeType)
    }

    fun getColorTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getColorTypeListBind, handle)
    }

    fun setConstant(name: String, themeType: String, constant: Int) {
        ObjectCalls.ptrcallWithTwoStringNameAndIntArg(setConstantBind, handle, name, themeType, constant)
    }

    fun getConstant(name: String, themeType: String): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getConstantBind, handle, name, themeType)
    }

    fun hasConstant(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasConstantBind, handle, name, themeType)
    }

    fun renameConstant(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameConstantBind, handle, oldName, name, themeType)
    }

    fun clearConstant(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearConstantBind, handle, name, themeType)
    }

    fun getConstantList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getConstantListBind, handle, themeType)
    }

    fun getConstantTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getConstantTypeListBind, handle)
    }

    fun setDefaultBaseScale(baseScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDefaultBaseScaleBind, handle, baseScale)
    }

    fun getDefaultBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDefaultBaseScaleBind, handle)
    }

    fun hasDefaultBaseScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDefaultBaseScaleBind, handle)
    }

    fun setDefaultFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setDefaultFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getDefaultFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDefaultFontBind, handle))
    }

    fun hasDefaultFont(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDefaultFontBind, handle)
    }

    fun setDefaultFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setDefaultFontSizeBind, handle, fontSize)
    }

    fun getDefaultFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDefaultFontSizeBind, handle)
    }

    fun hasDefaultFontSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDefaultFontSizeBind, handle)
    }

    fun setThemeItem(dataType: Long, name: String, themeType: String, value: Any?) {
        ObjectCalls.ptrcallWithLongAndTwoStringNameAndVariantArg(setThemeItemBind, handle, dataType, name, themeType, value)
    }

    fun getThemeItem(dataType: Long, name: String, themeType: String): Any? {
        return ObjectCalls.ptrcallWithLongAndTwoStringNameArgsRetVariantScalar(getThemeItemBind, handle, dataType, name, themeType)
    }

    fun hasThemeItem(dataType: Long, name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithLongAndTwoStringNameArgsRetBool(hasThemeItemBind, handle, dataType, name, themeType)
    }

    fun renameThemeItem(dataType: Long, oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithLongAndThreeStringNameArgs(renameThemeItemBind, handle, dataType, oldName, name, themeType)
    }

    fun clearThemeItem(dataType: Long, name: String, themeType: String) {
        ObjectCalls.ptrcallWithLongAndTwoStringNameArgs(clearThemeItemBind, handle, dataType, name, themeType)
    }

    fun getThemeItemList(dataType: Long, themeType: String): List<String> {
        return ObjectCalls.ptrcallWithLongAndStringArgRetPackedStringList(getThemeItemListBind, handle, dataType, themeType)
    }

    fun getThemeItemTypeList(dataType: Long): List<String> {
        return ObjectCalls.ptrcallWithLongArgRetPackedStringList(getThemeItemTypeListBind, handle, dataType)
    }

    fun setTypeVariation(themeType: String, baseType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(setTypeVariationBind, handle, themeType, baseType)
    }

    fun isTypeVariation(themeType: String, baseType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isTypeVariationBind, handle, themeType, baseType)
    }

    fun clearTypeVariation(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(clearTypeVariationBind, handle, themeType)
    }

    fun getTypeVariationBase(themeType: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(getTypeVariationBaseBind, handle, themeType)
    }

    fun getTypeVariationList(baseType: String): List<String> {
        return ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(getTypeVariationListBind, handle, baseType)
    }

    fun addType(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(addTypeBind, handle, themeType)
    }

    fun removeType(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeTypeBind, handle, themeType)
    }

    fun renameType(oldThemeType: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameTypeBind, handle, oldThemeType, themeType)
    }

    fun getTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getTypeListBind, handle)
    }

    fun mergeWith(other: Theme?) {
        ObjectCalls.ptrcallWithObjectArgs(mergeWithBind, handle, listOf(other?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes all the theme properties defined on the theme resource.
     *
     * Generated from Godot docs: Theme.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    companion object {
        const val DATA_TYPE_COLOR: Long = 0L
        const val DATA_TYPE_CONSTANT: Long = 1L
        const val DATA_TYPE_FONT: Long = 2L
        const val DATA_TYPE_FONT_SIZE: Long = 3L
        const val DATA_TYPE_ICON: Long = 4L
        const val DATA_TYPE_STYLEBOX: Long = 5L
        const val DATA_TYPE_MAX: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Theme? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Theme? =
            if (handle.address() == 0L) null else Theme(handle)

        private const val SET_ICON_HASH = 2188371082L
        private val setIconBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_icon", SET_ICON_HASH)
        }

        private const val GET_ICON_HASH = 934555193L
        private val getIconBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_icon", GET_ICON_HASH)
        }

        private const val HAS_ICON_HASH = 471820014L
        private val hasIconBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_icon", HAS_ICON_HASH)
        }

        private const val RENAME_ICON_HASH = 642128662L
        private val renameIconBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_icon", RENAME_ICON_HASH)
        }

        private const val CLEAR_ICON_HASH = 3740211285L
        private val clearIconBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_icon", CLEAR_ICON_HASH)
        }

        private const val GET_ICON_LIST_HASH = 4291131558L
        private val getIconListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_icon_list", GET_ICON_LIST_HASH)
        }

        private const val GET_ICON_TYPE_LIST_HASH = 1139954409L
        private val getIconTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_icon_type_list", GET_ICON_TYPE_LIST_HASH)
        }

        private const val SET_STYLEBOX_HASH = 2075907568L
        private val setStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_stylebox", SET_STYLEBOX_HASH)
        }

        private const val GET_STYLEBOX_HASH = 3405608165L
        private val getStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_stylebox", GET_STYLEBOX_HASH)
        }

        private const val HAS_STYLEBOX_HASH = 471820014L
        private val hasStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_stylebox", HAS_STYLEBOX_HASH)
        }

        private const val RENAME_STYLEBOX_HASH = 642128662L
        private val renameStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_stylebox", RENAME_STYLEBOX_HASH)
        }

        private const val CLEAR_STYLEBOX_HASH = 3740211285L
        private val clearStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_stylebox", CLEAR_STYLEBOX_HASH)
        }

        private const val GET_STYLEBOX_LIST_HASH = 4291131558L
        private val getStyleboxListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_stylebox_list", GET_STYLEBOX_LIST_HASH)
        }

        private const val GET_STYLEBOX_TYPE_LIST_HASH = 1139954409L
        private val getStyleboxTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_stylebox_type_list", GET_STYLEBOX_TYPE_LIST_HASH)
        }

        private const val SET_FONT_HASH = 177292320L
        private val setFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_font", SET_FONT_HASH)
        }

        private const val GET_FONT_HASH = 3445063586L
        private val getFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_font", GET_FONT_HASH)
        }

        private const val HAS_FONT_HASH = 471820014L
        private val hasFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_font", HAS_FONT_HASH)
        }

        private const val RENAME_FONT_HASH = 642128662L
        private val renameFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_font", RENAME_FONT_HASH)
        }

        private const val CLEAR_FONT_HASH = 3740211285L
        private val clearFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_font", CLEAR_FONT_HASH)
        }

        private const val GET_FONT_LIST_HASH = 4291131558L
        private val getFontListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_font_list", GET_FONT_LIST_HASH)
        }

        private const val GET_FONT_TYPE_LIST_HASH = 1139954409L
        private val getFontTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_font_type_list", GET_FONT_TYPE_LIST_HASH)
        }

        private const val SET_FONT_SIZE_HASH = 281601298L
        private val setFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_font_size", SET_FONT_SIZE_HASH)
        }

        private const val GET_FONT_SIZE_HASH = 2419549490L
        private val getFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_font_size", GET_FONT_SIZE_HASH)
        }

        private const val HAS_FONT_SIZE_HASH = 471820014L
        private val hasFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_font_size", HAS_FONT_SIZE_HASH)
        }

        private const val RENAME_FONT_SIZE_HASH = 642128662L
        private val renameFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_font_size", RENAME_FONT_SIZE_HASH)
        }

        private const val CLEAR_FONT_SIZE_HASH = 3740211285L
        private val clearFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_font_size", CLEAR_FONT_SIZE_HASH)
        }

        private const val GET_FONT_SIZE_LIST_HASH = 4291131558L
        private val getFontSizeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_font_size_list", GET_FONT_SIZE_LIST_HASH)
        }

        private const val GET_FONT_SIZE_TYPE_LIST_HASH = 1139954409L
        private val getFontSizeTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_font_size_type_list", GET_FONT_SIZE_TYPE_LIST_HASH)
        }

        private const val SET_COLOR_HASH = 4111215154L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 2015923404L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_color", GET_COLOR_HASH)
        }

        private const val HAS_COLOR_HASH = 471820014L
        private val hasColorBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_color", HAS_COLOR_HASH)
        }

        private const val RENAME_COLOR_HASH = 642128662L
        private val renameColorBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_color", RENAME_COLOR_HASH)
        }

        private const val CLEAR_COLOR_HASH = 3740211285L
        private val clearColorBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_color", CLEAR_COLOR_HASH)
        }

        private const val GET_COLOR_LIST_HASH = 4291131558L
        private val getColorListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_color_list", GET_COLOR_LIST_HASH)
        }

        private const val GET_COLOR_TYPE_LIST_HASH = 1139954409L
        private val getColorTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_color_type_list", GET_COLOR_TYPE_LIST_HASH)
        }

        private const val SET_CONSTANT_HASH = 281601298L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 2419549490L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_constant", GET_CONSTANT_HASH)
        }

        private const val HAS_CONSTANT_HASH = 471820014L
        private val hasConstantBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_constant", HAS_CONSTANT_HASH)
        }

        private const val RENAME_CONSTANT_HASH = 642128662L
        private val renameConstantBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_constant", RENAME_CONSTANT_HASH)
        }

        private const val CLEAR_CONSTANT_HASH = 3740211285L
        private val clearConstantBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_constant", CLEAR_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_LIST_HASH = 4291131558L
        private val getConstantListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_constant_list", GET_CONSTANT_LIST_HASH)
        }

        private const val GET_CONSTANT_TYPE_LIST_HASH = 1139954409L
        private val getConstantTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_constant_type_list", GET_CONSTANT_TYPE_LIST_HASH)
        }

        private const val SET_DEFAULT_BASE_SCALE_HASH = 373806689L
        private val setDefaultBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_default_base_scale", SET_DEFAULT_BASE_SCALE_HASH)
        }

        private const val GET_DEFAULT_BASE_SCALE_HASH = 1740695150L
        private val getDefaultBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_default_base_scale", GET_DEFAULT_BASE_SCALE_HASH)
        }

        private const val HAS_DEFAULT_BASE_SCALE_HASH = 36873697L
        private val hasDefaultBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_default_base_scale", HAS_DEFAULT_BASE_SCALE_HASH)
        }

        private const val SET_DEFAULT_FONT_HASH = 1262170328L
        private val setDefaultFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_default_font", SET_DEFAULT_FONT_HASH)
        }

        private const val GET_DEFAULT_FONT_HASH = 3229501585L
        private val getDefaultFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_default_font", GET_DEFAULT_FONT_HASH)
        }

        private const val HAS_DEFAULT_FONT_HASH = 36873697L
        private val hasDefaultFontBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_default_font", HAS_DEFAULT_FONT_HASH)
        }

        private const val SET_DEFAULT_FONT_SIZE_HASH = 1286410249L
        private val setDefaultFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_default_font_size", SET_DEFAULT_FONT_SIZE_HASH)
        }

        private const val GET_DEFAULT_FONT_SIZE_HASH = 3905245786L
        private val getDefaultFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_default_font_size", GET_DEFAULT_FONT_SIZE_HASH)
        }

        private const val HAS_DEFAULT_FONT_SIZE_HASH = 36873697L
        private val hasDefaultFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_default_font_size", HAS_DEFAULT_FONT_SIZE_HASH)
        }

        private const val SET_THEME_ITEM_HASH = 2492983623L
        private val setThemeItemBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_theme_item", SET_THEME_ITEM_HASH)
        }

        private const val GET_THEME_ITEM_HASH = 2191024021L
        private val getThemeItemBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_theme_item", GET_THEME_ITEM_HASH)
        }

        private const val HAS_THEME_ITEM_HASH = 1739311056L
        private val hasThemeItemBind by lazy {
            ObjectCalls.getMethodBind("Theme", "has_theme_item", HAS_THEME_ITEM_HASH)
        }

        private const val RENAME_THEME_ITEM_HASH = 3900867553L
        private val renameThemeItemBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_theme_item", RENAME_THEME_ITEM_HASH)
        }

        private const val CLEAR_THEME_ITEM_HASH = 2965505587L
        private val clearThemeItemBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_theme_item", CLEAR_THEME_ITEM_HASH)
        }

        private const val GET_THEME_ITEM_LIST_HASH = 3726716710L
        private val getThemeItemListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_theme_item_list", GET_THEME_ITEM_LIST_HASH)
        }

        private const val GET_THEME_ITEM_TYPE_LIST_HASH = 1316004935L
        private val getThemeItemTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_theme_item_type_list", GET_THEME_ITEM_TYPE_LIST_HASH)
        }

        private const val SET_TYPE_VARIATION_HASH = 3740211285L
        private val setTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Theme", "set_type_variation", SET_TYPE_VARIATION_HASH)
        }

        private const val IS_TYPE_VARIATION_HASH = 471820014L
        private val isTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Theme", "is_type_variation", IS_TYPE_VARIATION_HASH)
        }

        private const val CLEAR_TYPE_VARIATION_HASH = 3304788590L
        private val clearTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear_type_variation", CLEAR_TYPE_VARIATION_HASH)
        }

        private const val GET_TYPE_VARIATION_BASE_HASH = 1965194235L
        private val getTypeVariationBaseBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_type_variation_base", GET_TYPE_VARIATION_BASE_HASH)
        }

        private const val GET_TYPE_VARIATION_LIST_HASH = 1761182771L
        private val getTypeVariationListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_type_variation_list", GET_TYPE_VARIATION_LIST_HASH)
        }

        private const val ADD_TYPE_HASH = 3304788590L
        private val addTypeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "add_type", ADD_TYPE_HASH)
        }

        private const val REMOVE_TYPE_HASH = 3304788590L
        private val removeTypeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "remove_type", REMOVE_TYPE_HASH)
        }

        private const val RENAME_TYPE_HASH = 3740211285L
        private val renameTypeBind by lazy {
            ObjectCalls.getMethodBind("Theme", "rename_type", RENAME_TYPE_HASH)
        }

        private const val GET_TYPE_LIST_HASH = 1139954409L
        private val getTypeListBind by lazy {
            ObjectCalls.getMethodBind("Theme", "get_type_list", GET_TYPE_LIST_HASH)
        }

        private const val MERGE_WITH_HASH = 2326690814L
        private val mergeWithBind by lazy {
            ObjectCalls.getMethodBind("Theme", "merge_with", MERGE_WITH_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("Theme", "clear", CLEAR_HASH)
        }
    }
}

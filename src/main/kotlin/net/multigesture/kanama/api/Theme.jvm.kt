package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Theme (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Theme waits on: ptrcallWithLongAndStringArgRetPackedStringList,
//   ptrcallWithLongAndTwoStringNameAndVariantArg,
//   ptrcallWithLongAndTwoStringNameArgsRetVariantScalar, ptrcallWithLongArgRetPackedStringList,
//   ptrcallWithStringArgRetPackedStringList, ptrcallWithStringNameArgRetPackedStringList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a list of names for icon properties defined with `theme_type`. Use `get_icon_type_list`
 * to get a list of possible theme type names.
 *
 * Generated from Godot docs: Theme.get_icon_list
 */
fun Theme.getIconList(themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getIconListBind, handle, themeType)
}

/**
 * Returns a list of names for `StyleBox` properties defined with `theme_type`. Use
 * `get_stylebox_type_list` to get a list of possible theme type names.
 *
 * Generated from Godot docs: Theme.get_stylebox_list
 */
fun Theme.getStyleboxList(themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getStyleboxListBind, handle, themeType)
}

/**
 * Returns a list of names for `Font` properties defined with `theme_type`. Use
 * `get_font_type_list` to get a list of possible theme type names.
 *
 * Generated from Godot docs: Theme.get_font_list
 */
fun Theme.getFontList(themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFontListBind, handle, themeType)
}

/**
 * Returns a list of names for font size properties defined with `theme_type`. Use
 * `get_font_size_type_list` to get a list of possible theme type names.
 *
 * Generated from Godot docs: Theme.get_font_size_list
 */
fun Theme.getFontSizeList(themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFontSizeListBind, handle, themeType)
}

/**
 * Returns a list of names for `Color` properties defined with `theme_type`. Use
 * `get_color_type_list` to get a list of possible theme type names.
 *
 * Generated from Godot docs: Theme.get_color_list
 */
fun Theme.getColorList(themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getColorListBind, handle, themeType)
}

/**
 * Returns a list of names for constant properties defined with `theme_type`. Use
 * `get_constant_type_list` to get a list of possible theme type names.
 *
 * Generated from Godot docs: Theme.get_constant_list
 */
fun Theme.getConstantList(themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getConstantListBind, handle, themeType)
}

/**
 * Creates or changes the value of the theme property of `data_type` defined by `name` and
 * `theme_type`. Use `clear_theme_item` to remove the property. Fails if the `value` type is not
 * accepted by `data_type`. Note: This method is analogous to calling the corresponding data type
 * specific method, but can be used for more generalized logic.
 *
 * Generated from Godot docs: Theme.set_theme_item
 */
fun Theme.setThemeItem(dataType: Long, name: String, themeType: String, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithLongAndTwoStringNameAndVariantArg(setThemeItemBind, handle, dataType, name, themeType, value)
}

/**
 * Returns the theme property of `data_type` defined by `name` and `theme_type`, if it exists.
 * Returns the engine fallback value if the property doesn't exist (see `ThemeDB`). Use
 * `has_theme_item` to check for existence. Note: This method is analogous to calling the
 * corresponding data type specific method, but can be used for more generalized logic.
 *
 * Generated from Godot docs: Theme.get_theme_item
 */
fun Theme.getThemeItem(dataType: Long, name: String, themeType: String): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithLongAndTwoStringNameArgsRetVariantScalar(getThemeItemBind, handle, dataType, name, themeType)
}

/**
 * Returns a list of names for properties of `data_type` defined with `theme_type`. Use
 * `get_theme_item_type_list` to get a list of possible theme type names. Note: This method is
 * analogous to calling the corresponding data type specific method, but can be used for more
 * generalized logic.
 *
 * Generated from Godot docs: Theme.get_theme_item_list
 */
fun Theme.getThemeItemList(dataType: Long, themeType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongAndStringArgRetPackedStringList(getThemeItemListBind, handle, dataType, themeType)
}

/**
 * Returns a list of all unique theme type names for `data_type` properties. Use `get_type_list` to
 * get a list of all unique theme types. Note: This method is analogous to calling the
 * corresponding data type specific method, but can be used for more generalized logic.
 *
 * Generated from Godot docs: Theme.get_theme_item_type_list
 */
fun Theme.getThemeItemTypeList(dataType: Long): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetPackedStringList(getThemeItemTypeListBind, handle, dataType)
}

/**
 * Returns a list of all type variations for the given `base_type`.
 *
 * Generated from Godot docs: Theme.get_type_variation_list
 */
fun Theme.getTypeVariationList(baseType: String): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(getTypeVariationListBind, handle, baseType)
}

private const val GET_ICON_LIST_HASH = 4291131558L
private val getIconListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_icon_list", GET_ICON_LIST_HASH)
}

private const val GET_STYLEBOX_LIST_HASH = 4291131558L
private val getStyleboxListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_stylebox_list", GET_STYLEBOX_LIST_HASH)
}

private const val GET_FONT_LIST_HASH = 4291131558L
private val getFontListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_font_list", GET_FONT_LIST_HASH)
}

private const val GET_FONT_SIZE_LIST_HASH = 4291131558L
private val getFontSizeListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_font_size_list", GET_FONT_SIZE_LIST_HASH)
}

private const val GET_COLOR_LIST_HASH = 4291131558L
private val getColorListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_color_list", GET_COLOR_LIST_HASH)
}

private const val GET_CONSTANT_LIST_HASH = 4291131558L
private val getConstantListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_constant_list", GET_CONSTANT_LIST_HASH)
}

private const val SET_THEME_ITEM_HASH = 2492983623L
private val setThemeItemBind by lazy {
    ObjectCalls.getMethodBind("Theme", "set_theme_item", SET_THEME_ITEM_HASH)
}

private const val GET_THEME_ITEM_HASH = 2191024021L
private val getThemeItemBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_theme_item", GET_THEME_ITEM_HASH)
}

private const val GET_THEME_ITEM_LIST_HASH = 3726716710L
private val getThemeItemListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_theme_item_list", GET_THEME_ITEM_LIST_HASH)
}

private const val GET_THEME_ITEM_TYPE_LIST_HASH = 1316004935L
private val getThemeItemTypeListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_theme_item_type_list", GET_THEME_ITEM_TYPE_LIST_HASH)
}

private const val GET_TYPE_VARIATION_LIST_HASH = 1761182771L
private val getTypeVariationListBind by lazy {
    ObjectCalls.getMethodBind("Theme", "get_type_variation_list", GET_TYPE_VARIATION_LIST_HASH)
}

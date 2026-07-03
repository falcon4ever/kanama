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

    /**
     * Creates or changes the value of the icon property defined by `name` and `theme_type`. Use
     * `clear_icon` to remove the property.
     *
     * Generated from Godot docs: Theme.set_icon
     */
    fun setIcon(name: String, themeType: String, texture: Texture2D?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(setIconBind, handle, name, themeType, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the icon property defined by `name` and `theme_type`, if it exists. Returns the engine
     * fallback icon value if the property doesn't exist (see `ThemeDB.fallback_icon`). Use `has_icon`
     * to check for existence.
     *
     * Generated from Godot docs: Theme.get_icon
     */
    fun getIcon(name: String, themeType: String): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getIconBind, handle, name, themeType))
    }

    /**
     * Returns `true` if the icon property defined by `name` and `theme_type` exists. Returns `false`
     * if it doesn't exist. Use `set_icon` to define it.
     *
     * Generated from Godot docs: Theme.has_icon
     */
    fun hasIcon(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasIconBind, handle, name, themeType)
    }

    /**
     * Renames the icon property defined by `old_name` and `theme_type` to `name`, if it exists. Fails
     * if it doesn't exist, or if a similar property with the new name already exists. Use `has_icon`
     * to check for existence, and `clear_icon` to remove the existing property.
     *
     * Generated from Godot docs: Theme.rename_icon
     */
    fun renameIcon(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameIconBind, handle, oldName, name, themeType)
    }

    /**
     * Removes the icon property defined by `name` and `theme_type`, if it exists. Fails if it doesn't
     * exist. Use `has_icon` to check for existence.
     *
     * Generated from Godot docs: Theme.clear_icon
     */
    fun clearIcon(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearIconBind, handle, name, themeType)
    }

    /**
     * Returns a list of names for icon properties defined with `theme_type`. Use `get_icon_type_list`
     * to get a list of possible theme type names.
     *
     * Generated from Godot docs: Theme.get_icon_list
     */
    fun getIconList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getIconListBind, handle, themeType)
    }

    /**
     * Returns a list of all unique theme type names for icon properties. Use `get_type_list` to get a
     * list of all unique theme types.
     *
     * Generated from Godot docs: Theme.get_icon_type_list
     */
    fun getIconTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getIconTypeListBind, handle)
    }

    /**
     * Creates or changes the value of the `StyleBox` property defined by `name` and `theme_type`. Use
     * `clear_stylebox` to remove the property.
     *
     * Generated from Godot docs: Theme.set_stylebox
     */
    fun setStylebox(name: String, themeType: String, texture: StyleBox?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(setStyleboxBind, handle, name, themeType, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the `StyleBox` property defined by `name` and `theme_type`, if it exists. Returns the
     * engine fallback stylebox value if the property doesn't exist (see `ThemeDB.fallback_stylebox`).
     * Use `has_stylebox` to check for existence.
     *
     * Generated from Godot docs: Theme.get_stylebox
     */
    fun getStylebox(name: String, themeType: String): StyleBox? {
        return StyleBox.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getStyleboxBind, handle, name, themeType))
    }

    /**
     * Returns `true` if the `StyleBox` property defined by `name` and `theme_type` exists. Returns
     * `false` if it doesn't exist. Use `set_stylebox` to define it.
     *
     * Generated from Godot docs: Theme.has_stylebox
     */
    fun hasStylebox(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasStyleboxBind, handle, name, themeType)
    }

    /**
     * Renames the `StyleBox` property defined by `old_name` and `theme_type` to `name`, if it exists.
     * Fails if it doesn't exist, or if a similar property with the new name already exists. Use
     * `has_stylebox` to check for existence, and `clear_stylebox` to remove the existing property.
     *
     * Generated from Godot docs: Theme.rename_stylebox
     */
    fun renameStylebox(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameStyleboxBind, handle, oldName, name, themeType)
    }

    /**
     * Removes the `StyleBox` property defined by `name` and `theme_type`, if it exists. Fails if it
     * doesn't exist. Use `has_stylebox` to check for existence.
     *
     * Generated from Godot docs: Theme.clear_stylebox
     */
    fun clearStylebox(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearStyleboxBind, handle, name, themeType)
    }

    /**
     * Returns a list of names for `StyleBox` properties defined with `theme_type`. Use
     * `get_stylebox_type_list` to get a list of possible theme type names.
     *
     * Generated from Godot docs: Theme.get_stylebox_list
     */
    fun getStyleboxList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getStyleboxListBind, handle, themeType)
    }

    /**
     * Returns a list of all unique theme type names for `StyleBox` properties. Use `get_type_list` to
     * get a list of all unique theme types.
     *
     * Generated from Godot docs: Theme.get_stylebox_type_list
     */
    fun getStyleboxTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getStyleboxTypeListBind, handle)
    }

    /**
     * Creates or changes the value of the `Font` property defined by `name` and `theme_type`. Use
     * `clear_font` to remove the property.
     *
     * Generated from Godot docs: Theme.set_font
     */
    fun setFont(name: String, themeType: String, font: Font?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(setFontBind, handle, name, themeType, font?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the `Font` property defined by `name` and `theme_type`, if it exists. Returns the
     * default theme font if the property doesn't exist and the default theme font is set up (see
     * `default_font`). Use `has_font` to check for existence of the property and `has_default_font` to
     * check for existence of the default theme font. Returns the engine fallback font value, if
     * neither exist (see `ThemeDB.fallback_font`).
     *
     * Generated from Godot docs: Theme.get_font
     */
    fun getFont(name: String, themeType: String): Font? {
        return Font.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getFontBind, handle, name, themeType))
    }

    /**
     * Returns `true` if the `Font` property defined by `name` and `theme_type` exists, or if the
     * default theme font is set up (see `has_default_font`). Returns `false` if neither exist. Use
     * `set_font` to define the property.
     *
     * Generated from Godot docs: Theme.has_font
     */
    fun hasFont(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasFontBind, handle, name, themeType)
    }

    /**
     * Renames the `Font` property defined by `old_name` and `theme_type` to `name`, if it exists.
     * Fails if it doesn't exist, or if a similar property with the new name already exists. Use
     * `has_font` to check for existence, and `clear_font` to remove the existing property.
     *
     * Generated from Godot docs: Theme.rename_font
     */
    fun renameFont(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameFontBind, handle, oldName, name, themeType)
    }

    /**
     * Removes the `Font` property defined by `name` and `theme_type`, if it exists. Fails if it
     * doesn't exist. Use `has_font` to check for existence.
     *
     * Generated from Godot docs: Theme.clear_font
     */
    fun clearFont(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearFontBind, handle, name, themeType)
    }

    /**
     * Returns a list of names for `Font` properties defined with `theme_type`. Use
     * `get_font_type_list` to get a list of possible theme type names.
     *
     * Generated from Godot docs: Theme.get_font_list
     */
    fun getFontList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFontListBind, handle, themeType)
    }

    /**
     * Returns a list of all unique theme type names for `Font` properties. Use `get_type_list` to get
     * a list of all unique theme types.
     *
     * Generated from Godot docs: Theme.get_font_type_list
     */
    fun getFontTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFontTypeListBind, handle)
    }

    /**
     * Creates or changes the value of the font size property defined by `name` and `theme_type`. Use
     * `clear_font_size` to remove the property.
     *
     * Generated from Godot docs: Theme.set_font_size
     */
    fun setFontSize(name: String, themeType: String, fontSize: Int) {
        ObjectCalls.ptrcallWithTwoStringNameAndIntArg(setFontSizeBind, handle, name, themeType, fontSize)
    }

    /**
     * Returns the font size property defined by `name` and `theme_type`, if it exists. Returns the
     * default theme font size if the property doesn't exist and the default theme font size is set up
     * (see `default_font_size`). Use `has_font_size` to check for existence of the property and
     * `has_default_font_size` to check for existence of the default theme font. Returns the engine
     * fallback font size value, if neither exist (see `ThemeDB.fallback_font_size`).
     *
     * Generated from Godot docs: Theme.get_font_size
     */
    fun getFontSize(name: String, themeType: String): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getFontSizeBind, handle, name, themeType)
    }

    /**
     * Returns `true` if the font size property defined by `name` and `theme_type` exists, or if the
     * default theme font size is set up (see `has_default_font_size`). Returns `false` if neither
     * exist. Use `set_font_size` to define the property.
     *
     * Generated from Godot docs: Theme.has_font_size
     */
    fun hasFontSize(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasFontSizeBind, handle, name, themeType)
    }

    /**
     * Renames the font size property defined by `old_name` and `theme_type` to `name`, if it exists.
     * Fails if it doesn't exist, or if a similar property with the new name already exists. Use
     * `has_font_size` to check for existence, and `clear_font_size` to remove the existing property.
     *
     * Generated from Godot docs: Theme.rename_font_size
     */
    fun renameFontSize(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameFontSizeBind, handle, oldName, name, themeType)
    }

    /**
     * Removes the font size property defined by `name` and `theme_type`, if it exists. Fails if it
     * doesn't exist. Use `has_font_size` to check for existence.
     *
     * Generated from Godot docs: Theme.clear_font_size
     */
    fun clearFontSize(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearFontSizeBind, handle, name, themeType)
    }

    /**
     * Returns a list of names for font size properties defined with `theme_type`. Use
     * `get_font_size_type_list` to get a list of possible theme type names.
     *
     * Generated from Godot docs: Theme.get_font_size_list
     */
    fun getFontSizeList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFontSizeListBind, handle, themeType)
    }

    /**
     * Returns a list of all unique theme type names for font size properties. Use `get_type_list` to
     * get a list of all unique theme types.
     *
     * Generated from Godot docs: Theme.get_font_size_type_list
     */
    fun getFontSizeTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFontSizeTypeListBind, handle)
    }

    /**
     * Creates or changes the value of the `Color` property defined by `name` and `theme_type`. Use
     * `clear_color` to remove the property.
     *
     * Generated from Godot docs: Theme.set_color
     */
    fun setColor(name: String, themeType: String, color: Color) {
        ObjectCalls.ptrcallWithTwoStringNameAndColorArg(setColorBind, handle, name, themeType, color)
    }

    /**
     * Returns the `Color` property defined by `name` and `theme_type`, if it exists. Returns the
     * default color value if the property doesn't exist. Use `has_color` to check for existence.
     *
     * Generated from Godot docs: Theme.get_color
     */
    fun getColor(name: String, themeType: String): Color {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetColor(getColorBind, handle, name, themeType)
    }

    /**
     * Returns `true` if the `Color` property defined by `name` and `theme_type` exists. Returns
     * `false` if it doesn't exist. Use `set_color` to define it.
     *
     * Generated from Godot docs: Theme.has_color
     */
    fun hasColor(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasColorBind, handle, name, themeType)
    }

    /**
     * Renames the `Color` property defined by `old_name` and `theme_type` to `name`, if it exists.
     * Fails if it doesn't exist, or if a similar property with the new name already exists. Use
     * `has_color` to check for existence, and `clear_color` to remove the existing property.
     *
     * Generated from Godot docs: Theme.rename_color
     */
    fun renameColor(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameColorBind, handle, oldName, name, themeType)
    }

    /**
     * Removes the `Color` property defined by `name` and `theme_type`, if it exists. Fails if it
     * doesn't exist. Use `has_color` to check for existence.
     *
     * Generated from Godot docs: Theme.clear_color
     */
    fun clearColor(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearColorBind, handle, name, themeType)
    }

    /**
     * Returns a list of names for `Color` properties defined with `theme_type`. Use
     * `get_color_type_list` to get a list of possible theme type names.
     *
     * Generated from Godot docs: Theme.get_color_list
     */
    fun getColorList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getColorListBind, handle, themeType)
    }

    /**
     * Returns a list of all unique theme type names for `Color` properties. Use `get_type_list` to get
     * a list of all unique theme types.
     *
     * Generated from Godot docs: Theme.get_color_type_list
     */
    fun getColorTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getColorTypeListBind, handle)
    }

    /**
     * Creates or changes the value of the constant property defined by `name` and `theme_type`. Use
     * `clear_constant` to remove the property.
     *
     * Generated from Godot docs: Theme.set_constant
     */
    fun setConstant(name: String, themeType: String, constant: Int) {
        ObjectCalls.ptrcallWithTwoStringNameAndIntArg(setConstantBind, handle, name, themeType, constant)
    }

    /**
     * Returns the constant property defined by `name` and `theme_type`, if it exists. Returns `0` if
     * the property doesn't exist. Use `has_constant` to check for existence.
     *
     * Generated from Godot docs: Theme.get_constant
     */
    fun getConstant(name: String, themeType: String): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getConstantBind, handle, name, themeType)
    }

    /**
     * Returns `true` if the constant property defined by `name` and `theme_type` exists. Returns
     * `false` if it doesn't exist. Use `set_constant` to define it.
     *
     * Generated from Godot docs: Theme.has_constant
     */
    fun hasConstant(name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasConstantBind, handle, name, themeType)
    }

    /**
     * Renames the constant property defined by `old_name` and `theme_type` to `name`, if it exists.
     * Fails if it doesn't exist, or if a similar property with the new name already exists. Use
     * `has_constant` to check for existence, and `clear_constant` to remove the existing property.
     *
     * Generated from Godot docs: Theme.rename_constant
     */
    fun renameConstant(oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithThreeStringNameArgs(renameConstantBind, handle, oldName, name, themeType)
    }

    /**
     * Removes the constant property defined by `name` and `theme_type`, if it exists. Fails if it
     * doesn't exist. Use `has_constant` to check for existence.
     *
     * Generated from Godot docs: Theme.clear_constant
     */
    fun clearConstant(name: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(clearConstantBind, handle, name, themeType)
    }

    /**
     * Returns a list of names for constant properties defined with `theme_type`. Use
     * `get_constant_type_list` to get a list of possible theme type names.
     *
     * Generated from Godot docs: Theme.get_constant_list
     */
    fun getConstantList(themeType: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getConstantListBind, handle, themeType)
    }

    /**
     * Returns a list of all unique theme type names for constant properties. Use `get_type_list` to
     * get a list of all unique theme types.
     *
     * Generated from Godot docs: Theme.get_constant_type_list
     */
    fun getConstantTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getConstantTypeListBind, handle)
    }

    /**
     * The default base scale factor of this theme resource. Used by some controls to scale their
     * visual properties based on the global scale factor. If this value is set to `0.0`, the global
     * scale factor is used (see `ThemeDB.fallback_base_scale`). Use `has_default_base_scale` to check
     * if this value is valid.
     *
     * Generated from Godot docs: Theme.set_default_base_scale
     */
    fun setDefaultBaseScale(baseScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDefaultBaseScaleBind, handle, baseScale)
    }

    /**
     * The default base scale factor of this theme resource. Used by some controls to scale their
     * visual properties based on the global scale factor. If this value is set to `0.0`, the global
     * scale factor is used (see `ThemeDB.fallback_base_scale`). Use `has_default_base_scale` to check
     * if this value is valid.
     *
     * Generated from Godot docs: Theme.get_default_base_scale
     */
    fun getDefaultBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDefaultBaseScaleBind, handle)
    }

    /**
     * Returns `true` if `default_base_scale` has a valid value. Returns `false` if it doesn't. The
     * value must be greater than `0.0` to be considered valid.
     *
     * Generated from Godot docs: Theme.has_default_base_scale
     */
    fun hasDefaultBaseScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDefaultBaseScaleBind, handle)
    }

    /**
     * The default font of this theme resource. Used as the default value when trying to fetch a font
     * resource that doesn't exist in this theme or is in invalid state. If the default font is also
     * missing or invalid, the engine fallback value is used (see `ThemeDB.fallback_font`). Use
     * `has_default_font` to check if this value is valid.
     *
     * Generated from Godot docs: Theme.set_default_font
     */
    fun setDefaultFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setDefaultFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The default font of this theme resource. Used as the default value when trying to fetch a font
     * resource that doesn't exist in this theme or is in invalid state. If the default font is also
     * missing or invalid, the engine fallback value is used (see `ThemeDB.fallback_font`). Use
     * `has_default_font` to check if this value is valid.
     *
     * Generated from Godot docs: Theme.get_default_font
     */
    fun getDefaultFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDefaultFontBind, handle))
    }

    /**
     * Returns `true` if `default_font` has a valid value. Returns `false` if it doesn't.
     *
     * Generated from Godot docs: Theme.has_default_font
     */
    fun hasDefaultFont(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDefaultFontBind, handle)
    }

    /**
     * The default font size of this theme resource. Used as the default value when trying to fetch a
     * font size value that doesn't exist in this theme or is in invalid state. If the default font
     * size is also missing or invalid, the engine fallback value is used (see
     * `ThemeDB.fallback_font_size`). Values below `1` are invalid and can be used to unset the
     * property. Use `has_default_font_size` to check if this value is valid.
     *
     * Generated from Godot docs: Theme.set_default_font_size
     */
    fun setDefaultFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setDefaultFontSizeBind, handle, fontSize)
    }

    /**
     * The default font size of this theme resource. Used as the default value when trying to fetch a
     * font size value that doesn't exist in this theme or is in invalid state. If the default font
     * size is also missing or invalid, the engine fallback value is used (see
     * `ThemeDB.fallback_font_size`). Values below `1` are invalid and can be used to unset the
     * property. Use `has_default_font_size` to check if this value is valid.
     *
     * Generated from Godot docs: Theme.get_default_font_size
     */
    fun getDefaultFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDefaultFontSizeBind, handle)
    }

    /**
     * Returns `true` if `default_font_size` has a valid value. Returns `false` if it doesn't. The
     * value must be greater than `0` to be considered valid.
     *
     * Generated from Godot docs: Theme.has_default_font_size
     */
    fun hasDefaultFontSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDefaultFontSizeBind, handle)
    }

    /**
     * Creates or changes the value of the theme property of `data_type` defined by `name` and
     * `theme_type`. Use `clear_theme_item` to remove the property. Fails if the `value` type is not
     * accepted by `data_type`. Note: This method is analogous to calling the corresponding data type
     * specific method, but can be used for more generalized logic.
     *
     * Generated from Godot docs: Theme.set_theme_item
     */
    fun setThemeItem(dataType: Long, name: String, themeType: String, value: Any?) {
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
    fun getThemeItem(dataType: Long, name: String, themeType: String): Any? {
        return ObjectCalls.ptrcallWithLongAndTwoStringNameArgsRetVariantScalar(getThemeItemBind, handle, dataType, name, themeType)
    }

    /**
     * Returns `true` if the theme property of `data_type` defined by `name` and `theme_type` exists.
     * Returns `false` if it doesn't exist. Use `set_theme_item` to define it. Note: This method is
     * analogous to calling the corresponding data type specific method, but can be used for more
     * generalized logic.
     *
     * Generated from Godot docs: Theme.has_theme_item
     */
    fun hasThemeItem(dataType: Long, name: String, themeType: String): Boolean {
        return ObjectCalls.ptrcallWithLongAndTwoStringNameArgsRetBool(hasThemeItemBind, handle, dataType, name, themeType)
    }

    /**
     * Renames the theme property of `data_type` defined by `old_name` and `theme_type` to `name`, if
     * it exists. Fails if it doesn't exist, or if a similar property with the new name already exists.
     * Use `has_theme_item` to check for existence, and `clear_theme_item` to remove the existing
     * property. Note: This method is analogous to calling the corresponding data type specific method,
     * but can be used for more generalized logic.
     *
     * Generated from Godot docs: Theme.rename_theme_item
     */
    fun renameThemeItem(dataType: Long, oldName: String, name: String, themeType: String) {
        ObjectCalls.ptrcallWithLongAndThreeStringNameArgs(renameThemeItemBind, handle, dataType, oldName, name, themeType)
    }

    /**
     * Removes the theme property of `data_type` defined by `name` and `theme_type`, if it exists.
     * Fails if it doesn't exist. Use `has_theme_item` to check for existence. Note: This method is
     * analogous to calling the corresponding data type specific method, but can be used for more
     * generalized logic.
     *
     * Generated from Godot docs: Theme.clear_theme_item
     */
    fun clearThemeItem(dataType: Long, name: String, themeType: String) {
        ObjectCalls.ptrcallWithLongAndTwoStringNameArgs(clearThemeItemBind, handle, dataType, name, themeType)
    }

    /**
     * Returns a list of names for properties of `data_type` defined with `theme_type`. Use
     * `get_theme_item_type_list` to get a list of possible theme type names. Note: This method is
     * analogous to calling the corresponding data type specific method, but can be used for more
     * generalized logic.
     *
     * Generated from Godot docs: Theme.get_theme_item_list
     */
    fun getThemeItemList(dataType: Long, themeType: String): List<String> {
        return ObjectCalls.ptrcallWithLongAndStringArgRetPackedStringList(getThemeItemListBind, handle, dataType, themeType)
    }

    /**
     * Returns a list of all unique theme type names for `data_type` properties. Use `get_type_list` to
     * get a list of all unique theme types. Note: This method is analogous to calling the
     * corresponding data type specific method, but can be used for more generalized logic.
     *
     * Generated from Godot docs: Theme.get_theme_item_type_list
     */
    fun getThemeItemTypeList(dataType: Long): List<String> {
        return ObjectCalls.ptrcallWithLongArgRetPackedStringList(getThemeItemTypeListBind, handle, dataType)
    }

    /**
     * Marks `theme_type` as a variation of `base_type`. This adds `theme_type` as a suggested option
     * for `Control.theme_type_variation` on a `Control` that is of the `base_type` class. Variations
     * can also be nested, i.e. `base_type` can be another variation. If a chain of variations ends
     * with a `base_type` matching the class of the `Control`, the whole chain is going to be suggested
     * as options. Note: Suggestions only show up if this theme resource is set as the project default
     * theme. See `ProjectSettings.gui/theme/custom`.
     *
     * Generated from Godot docs: Theme.set_type_variation
     */
    fun setTypeVariation(themeType: String, baseType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(setTypeVariationBind, handle, themeType, baseType)
    }

    /**
     * Returns `true` if `theme_type` is marked as a variation of `base_type`.
     *
     * Generated from Godot docs: Theme.is_type_variation
     */
    fun isTypeVariation(themeType: String, baseType: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isTypeVariationBind, handle, themeType, baseType)
    }

    /**
     * Unmarks `theme_type` as being a variation of another theme type. See `set_type_variation`.
     *
     * Generated from Godot docs: Theme.clear_type_variation
     */
    fun clearTypeVariation(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(clearTypeVariationBind, handle, themeType)
    }

    /**
     * Returns the name of the base theme type if `theme_type` is a valid variation type. Returns an
     * empty string otherwise.
     *
     * Generated from Godot docs: Theme.get_type_variation_base
     */
    fun getTypeVariationBase(themeType: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(getTypeVariationBaseBind, handle, themeType)
    }

    /**
     * Returns a list of all type variations for the given `base_type`.
     *
     * Generated from Godot docs: Theme.get_type_variation_list
     */
    fun getTypeVariationList(baseType: String): List<String> {
        return ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(getTypeVariationListBind, handle, baseType)
    }

    /**
     * Adds an empty theme type for every valid data type. Note: Empty types are not saved with the
     * theme. This method only exists to perform in-memory changes to the resource. Use available
     * `set_*` methods to add theme items.
     *
     * Generated from Godot docs: Theme.add_type
     */
    fun addType(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(addTypeBind, handle, themeType)
    }

    /**
     * Removes the theme type, gracefully discarding defined theme items. If the type is a variation,
     * this information is also erased. If the type is a base for type variations, those variations
     * lose their base.
     *
     * Generated from Godot docs: Theme.remove_type
     */
    fun removeType(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeTypeBind, handle, themeType)
    }

    /**
     * Renames the theme type `old_theme_type` to `theme_type`, if the old type exists and the new one
     * doesn't exist. Note: Renaming a theme type to an empty name or a variation to a type associated
     * with a built-in class removes type variation connections in a way that cannot be undone by
     * reversing the rename alone.
     *
     * Generated from Godot docs: Theme.rename_type
     */
    fun renameType(oldThemeType: String, themeType: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameTypeBind, handle, oldThemeType, themeType)
    }

    /**
     * Returns a list of all unique theme type names. Use the appropriate `get_*_type_list` method to
     * get a list of unique theme types for a single data type.
     *
     * Generated from Godot docs: Theme.get_type_list
     */
    fun getTypeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getTypeListBind, handle)
    }

    /**
     * Adds missing and overrides existing definitions with values from the `other` theme resource.
     * Note: This modifies the current theme. If you want to merge two themes together without
     * modifying either one, create a new empty theme and merge the other two into it one after
     * another.
     *
     * Generated from Godot docs: Theme.merge_with
     */
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

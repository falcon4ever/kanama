package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A horizontal menu bar that creates a menu for each `PopupMenu` child.
 *
 * Generated from Godot docs: MenuBar
 */
class MenuBar(handle: GodotHandle) : Control(handle) {
    var flat: Boolean
        @JvmName("flatProperty")
        get() = isFlat()
        @JvmName("setFlatProperty")
        set(value) = setFlat(value)

    var startIndex: Int
        @JvmName("startIndexProperty")
        get() = getStartIndex()
        @JvmName("setStartIndexProperty")
        set(value) = setStartIndex(value)

    var switchOnHover: Boolean
        @JvmName("switchOnHoverProperty")
        get() = isSwitchOnHover()
        @JvmName("setSwitchOnHoverProperty")
        set(value) = setSwitchOnHover(value)

    var preferGlobalMenu: Boolean
        @JvmName("preferGlobalMenuProperty")
        get() = isPreferGlobalMenu()
        @JvmName("setPreferGlobalMenuProperty")
        set(value) = setPreferGlobalMenu(value)

    var textDirection: Long
        @JvmName("textDirectionProperty")
        get() = getTextDirection()
        @JvmName("setTextDirectionProperty")
        set(value) = setTextDirection(value)

    var language: String
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    /**
     * If `true`, when the cursor hovers above menu item, it will close the current `PopupMenu` and
     * open the other one.
     *
     * Generated from Godot docs: MenuBar.set_switch_on_hover
     */
    fun setSwitchOnHover(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSwitchOnHoverBind, segment, enable)
    }

    /**
     * If `true`, when the cursor hovers above menu item, it will close the current `PopupMenu` and
     * open the other one.
     *
     * Generated from Godot docs: MenuBar.is_switch_on_hover
     */
    fun isSwitchOnHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSwitchOnHoverBind, segment)
    }

    /**
     * If `true`, shortcuts are disabled and cannot be used to trigger the button.
     *
     * Generated from Godot docs: MenuBar.set_disable_shortcuts
     */
    fun setDisableShortcuts(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableShortcutsBind, segment, disabled)
    }

    /**
     * If `true`, `MenuBar` will use system global menu when supported. Note: If `true` and global menu
     * is supported, this node is not displayed, has zero size, and all its child nodes except
     * `PopupMenu`s are inaccessible. Note: This property overrides the value of the
     * `PopupMenu.prefer_native_menu` property of the child nodes.
     *
     * Generated from Godot docs: MenuBar.set_prefer_global_menu
     */
    fun setPreferGlobalMenu(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreferGlobalMenuBind, segment, enabled)
    }

    /**
     * If `true`, `MenuBar` will use system global menu when supported. Note: If `true` and global menu
     * is supported, this node is not displayed, has zero size, and all its child nodes except
     * `PopupMenu`s are inaccessible. Note: This property overrides the value of the
     * `PopupMenu.prefer_native_menu` property of the child nodes.
     *
     * Generated from Godot docs: MenuBar.is_prefer_global_menu
     */
    fun isPreferGlobalMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPreferGlobalMenuBind, segment)
    }

    /**
     * Returns `true` if the current system's global menu is supported and used by this `MenuBar`.
     *
     * Generated from Godot docs: MenuBar.is_native_menu
     */
    fun isNativeMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNativeMenuBind, segment)
    }

    /**
     * Returns number of menu items.
     *
     * Generated from Godot docs: MenuBar.get_menu_count
     */
    fun getMenuCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMenuCountBind, segment)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: MenuBar.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, segment, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: MenuBar.get_text_direction
     */
    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, segment)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: MenuBar.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: MenuBar.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, segment)
    }

    /**
     * Flat `MenuBar` don't display item decoration.
     *
     * Generated from Godot docs: MenuBar.set_flat
     */
    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlatBind, segment, enabled)
    }

    /**
     * Flat `MenuBar` don't display item decoration.
     *
     * Generated from Godot docs: MenuBar.is_flat
     */
    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlatBind, segment)
    }

    /**
     * Position order in the global menu to insert `MenuBar` items at. All menu items in the `MenuBar`
     * are always inserted as a continuous range. Menus with lower `start_index` are inserted first.
     * Menus with `start_index` equal to `-1` are inserted last.
     *
     * Generated from Godot docs: MenuBar.set_start_index
     */
    fun setStartIndex(enabled: Int) {
        ObjectCalls.ptrcallWithIntArg(setStartIndexBind, segment, enabled)
    }

    /**
     * Position order in the global menu to insert `MenuBar` items at. All menu items in the `MenuBar`
     * are always inserted as a continuous range. Menus with lower `start_index` are inserted first.
     * Menus with `start_index` equal to `-1` are inserted last.
     *
     * Generated from Godot docs: MenuBar.get_start_index
     */
    fun getStartIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStartIndexBind, segment)
    }

    /**
     * Sets menu item title.
     *
     * Generated from Godot docs: MenuBar.set_menu_title
     */
    fun setMenuTitle(menu: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setMenuTitleBind, segment, menu, title)
    }

    /**
     * Returns menu item title.
     *
     * Generated from Godot docs: MenuBar.get_menu_title
     */
    fun getMenuTitle(menu: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getMenuTitleBind, segment, menu)
    }

    /**
     * Sets menu item tooltip.
     *
     * Generated from Godot docs: MenuBar.set_menu_tooltip
     */
    fun setMenuTooltip(menu: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setMenuTooltipBind, segment, menu, tooltip)
    }

    /**
     * Returns menu item tooltip.
     *
     * Generated from Godot docs: MenuBar.get_menu_tooltip
     */
    fun getMenuTooltip(menu: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getMenuTooltipBind, segment, menu)
    }

    /**
     * If `true`, menu item is disabled.
     *
     * Generated from Godot docs: MenuBar.set_menu_disabled
     */
    fun setMenuDisabled(menu: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setMenuDisabledBind, segment, menu, disabled)
    }

    /**
     * Returns `true` if the menu item is disabled.
     *
     * Generated from Godot docs: MenuBar.is_menu_disabled
     */
    fun isMenuDisabled(menu: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isMenuDisabledBind, segment, menu)
    }

    /**
     * If `true`, menu item is hidden.
     *
     * Generated from Godot docs: MenuBar.set_menu_hidden
     */
    fun setMenuHidden(menu: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setMenuHiddenBind, segment, menu, hidden)
    }

    /**
     * Returns `true` if the menu item is hidden.
     *
     * Generated from Godot docs: MenuBar.is_menu_hidden
     */
    fun isMenuHidden(menu: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isMenuHiddenBind, segment, menu)
    }

    /**
     * Returns `PopupMenu` associated with menu item.
     *
     * Generated from Godot docs: MenuBar.get_menu_popup
     */
    fun getMenuPopup(menu: Int): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getMenuPopupBind, segment, menu))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MenuBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MenuBar? =
            if (handle.address() == 0L) null else MenuBar(GodotHandle(handle))

        private const val SET_SWITCH_ON_HOVER_HASH = 2586408642L
        private val setSwitchOnHoverBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_switch_on_hover", SET_SWITCH_ON_HOVER_HASH)
        }

        private const val IS_SWITCH_ON_HOVER_HASH = 2240911060L
        private val isSwitchOnHoverBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "is_switch_on_hover", IS_SWITCH_ON_HOVER_HASH)
        }

        private const val SET_DISABLE_SHORTCUTS_HASH = 2586408642L
        private val setDisableShortcutsBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_disable_shortcuts", SET_DISABLE_SHORTCUTS_HASH)
        }

        private const val SET_PREFER_GLOBAL_MENU_HASH = 2586408642L
        private val setPreferGlobalMenuBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_prefer_global_menu", SET_PREFER_GLOBAL_MENU_HASH)
        }

        private const val IS_PREFER_GLOBAL_MENU_HASH = 36873697L
        private val isPreferGlobalMenuBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "is_prefer_global_menu", IS_PREFER_GLOBAL_MENU_HASH)
        }

        private const val IS_NATIVE_MENU_HASH = 36873697L
        private val isNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "is_native_menu", IS_NATIVE_MENU_HASH)
        }

        private const val GET_MENU_COUNT_HASH = 3905245786L
        private val getMenuCountBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_menu_count", GET_MENU_COUNT_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_FLAT_HASH = 2586408642L
        private val setFlatBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_flat", SET_FLAT_HASH)
        }

        private const val IS_FLAT_HASH = 36873697L
        private val isFlatBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "is_flat", IS_FLAT_HASH)
        }

        private const val SET_START_INDEX_HASH = 1286410249L
        private val setStartIndexBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_start_index", SET_START_INDEX_HASH)
        }

        private const val GET_START_INDEX_HASH = 3905245786L
        private val getStartIndexBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_start_index", GET_START_INDEX_HASH)
        }

        private const val SET_MENU_TITLE_HASH = 501894301L
        private val setMenuTitleBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_menu_title", SET_MENU_TITLE_HASH)
        }

        private const val GET_MENU_TITLE_HASH = 844755477L
        private val getMenuTitleBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_menu_title", GET_MENU_TITLE_HASH)
        }

        private const val SET_MENU_TOOLTIP_HASH = 501894301L
        private val setMenuTooltipBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_menu_tooltip", SET_MENU_TOOLTIP_HASH)
        }

        private const val GET_MENU_TOOLTIP_HASH = 844755477L
        private val getMenuTooltipBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_menu_tooltip", GET_MENU_TOOLTIP_HASH)
        }

        private const val SET_MENU_DISABLED_HASH = 300928843L
        private val setMenuDisabledBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_menu_disabled", SET_MENU_DISABLED_HASH)
        }

        private const val IS_MENU_DISABLED_HASH = 1116898809L
        private val isMenuDisabledBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "is_menu_disabled", IS_MENU_DISABLED_HASH)
        }

        private const val SET_MENU_HIDDEN_HASH = 300928843L
        private val setMenuHiddenBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "set_menu_hidden", SET_MENU_HIDDEN_HASH)
        }

        private const val IS_MENU_HIDDEN_HASH = 1116898809L
        private val isMenuHiddenBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "is_menu_hidden", IS_MENU_HIDDEN_HASH)
        }

        private const val GET_MENU_POPUP_HASH = 2100501353L
        private val getMenuPopupBind by lazy {
            ObjectCalls.getMethodBind("MenuBar", "get_menu_popup", GET_MENU_POPUP_HASH)
        }
    }
}

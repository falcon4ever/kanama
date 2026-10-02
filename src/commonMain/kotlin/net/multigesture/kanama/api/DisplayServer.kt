package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3i

/**
 * A server interface for low-level window management.
 *
 * Generated from Godot docs: DisplayServer
 */
object DisplayServer {
    private val singleton: RawSegment by lazy {
        ObjectCalls.getSingleton("DisplayServer")
    }

    const val INVALID_SCREEN: Long = -1L
    const val SCREEN_WITH_MOUSE_FOCUS: Long = -4L
    const val SCREEN_WITH_KEYBOARD_FOCUS: Long = -3L
    const val SCREEN_PRIMARY: Long = -2L
    const val SCREEN_OF_MAIN_WINDOW: Long = -1L
    const val MAIN_WINDOW_ID: Long = 0L
    const val INVALID_WINDOW_ID: Long = -1L
    const val INVALID_INDICATOR_ID: Long = -1L

    /**
     * Returns `true` if the specified `feature` is supported by the current `DisplayServer`, `false`
     * otherwise.
     *
     * Generated from Godot docs: DisplayServer.has_feature
     */
    @JvmStatic
    fun hasFeature(feature: DisplayServer.Feature): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, singleton, feature.value)
    }

    /**
     * Returns the name of the `DisplayServer` currently in use. Most operating systems only have a
     * single `DisplayServer`, but Linux has access to more than one `DisplayServer` (currently X11 and
     * Wayland). The names of built-in display servers are `Windows`, `macOS`, `X11` (Linux), `Wayland`
     * (Linux), `Android`, `iOS`, `web` (HTML5), and `headless` (when started with the `--headless`
     * command line argument ($DOCS_URL/tutorials/editor/command_line_tutorial.html)).
     *
     * Generated from Godot docs: DisplayServer.get_name
     */
    @JvmStatic
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, singleton)
    }

    /**
     * Sets native help system search callbacks. `search_callback` has the following arguments: `String
     * search_string, int result_limit` and return a `Dictionary` with "key, display name" pairs for
     * the search results. Called when the user enters search terms in the `Help` menu.
     * `action_callback` has the following arguments: `String key`. Called when the user selects a
     * search result in the `Help` menu. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.help_set_search_callbacks
     */
    @JvmStatic
    fun helpSetSearchCallbacks(searchCallback: GodotCallable, actionCallback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoCallableArgs(helpSetSearchCallbacksBind, singleton, searchCallback.target.segment, searchCallback.method, actionCallback.target.segment, actionCallback.method)
    }

    /**
     * Registers callables to emit when the menu is respectively about to show or closed. Callback
     * methods should have zero arguments.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_popup_callbacks
     */
    @JvmStatic
    fun globalMenuSetPopupCallbacks(menuRoot: String, openCallback: GodotCallable, closeCallback: GodotCallable) {
        ObjectCalls.ptrcallWithStringTwoCallableArgs(globalMenuSetPopupCallbacksBind, singleton, menuRoot, openCallback.target.segment, openCallback.method, closeCallback.target.segment, closeCallback.method)
    }

    /**
     * Adds an item that will act as a submenu of the global menu `menu_root`. The `submenu` argument
     * is the ID of the global menu root that will be shown when the item is clicked. Returns index of
     * the inserted item, it's not guaranteed to be the same as `index` value. Note: This method is
     * implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_submenu_item
     */
    @JvmStatic
    fun globalMenuAddSubmenuItem(menuRoot: String, label: String, submenu: String, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithThreeStringAndIntArgRetInt(globalMenuAddSubmenuItemBind, singleton, menuRoot, label, submenu, index)
    }

    /**
     * Adds a new item with text `label` to the global menu with ID `menu_root`. Returns index of the
     * inserted item, it's not guaranteed to be the same as `index` value. An `accelerator` can
     * optionally be defined, which is a keyboard shortcut that can be pressed to trigger the menu
     * button even if it's not currently open. The `accelerator` is generally a combination of
     * `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). Note:
     * The `callback` and `key_callback` Callables need to accept exactly one Variant parameter, the
     * parameter passed to the Callables will be the value passed to `tag`. Note: This method is
     * implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_item
     */
    @JvmStatic
    fun globalMenuAddItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddItemBind, singleton, menuRoot, label, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a new checkable item with text `label` to the global menu with ID `menu_root`. Returns
     * index of the inserted item, it's not guaranteed to be the same as `index` value. An
     * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
     * trigger the menu button even if it's not currently open. The `accelerator` is generally a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly one Variant
     * parameter, the parameter passed to the Callables will be the value passed to `tag`. Note: This
     * method is implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_check_item
     */
    @JvmStatic
    fun globalMenuAddCheckItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddCheckItemBind, singleton, menuRoot, label, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a new item with text `label` and icon `icon` to the global menu with ID `menu_root`.
     * Returns index of the inserted item, it's not guaranteed to be the same as `index` value. An
     * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
     * trigger the menu button even if it's not currently open. The `accelerator` is generally a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly one Variant
     * parameter, the parameter passed to the Callables will be the value passed to `tag`. Note: This
     * method is implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_icon_item
     */
    @JvmStatic
    fun globalMenuAddIconItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconItemBind, singleton, menuRoot, icon?.requireOpenHandle() ?: NULL_SEGMENT, label, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a new checkable item with text `label` and icon `icon` to the global menu with ID
     * `menu_root`. Returns index of the inserted item, it's not guaranteed to be the same as `index`
     * value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can be
     * pressed to trigger the menu button even if it's not currently open. The `accelerator` is
     * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
     * | KEY_A` (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly
     * one Variant parameter, the parameter passed to the Callables will be the value passed to `tag`.
     * Note: This method is implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_icon_check_item
     */
    @JvmStatic
    fun globalMenuAddIconCheckItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconCheckItemBind, singleton, menuRoot, icon?.requireOpenHandle() ?: NULL_SEGMENT, label, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a new radio-checkable item with text `label` to the global menu with ID `menu_root`.
     * Returns index of the inserted item, it's not guaranteed to be the same as `index` value. An
     * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
     * trigger the menu button even if it's not currently open. The `accelerator` is generally a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: Radio-checkable items just display a checkmark, but don't have any built-in
     * checking behavior and must be checked/unchecked manually. See `global_menu_set_item_checked` for
     * more info on how to control it. Note: The `callback` and `key_callback` Callables need to accept
     * exactly one Variant parameter, the parameter passed to the Callables will be the value passed to
     * `tag`. Note: This method is implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_radio_check_item
     */
    @JvmStatic
    fun globalMenuAddRadioCheckItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddRadioCheckItemBind, singleton, menuRoot, label, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a new radio-checkable item with text `label` and icon `icon` to the global menu with ID
     * `menu_root`. Returns index of the inserted item, it's not guaranteed to be the same as `index`
     * value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can be
     * pressed to trigger the menu button even if it's not currently open. The `accelerator` is
     * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
     * | KEY_A` (Ctrl + A). Note: Radio-checkable items just display a checkmark, but don't have any
     * built-in checking behavior and must be checked/unchecked manually. See
     * `global_menu_set_item_checked` for more info on how to control it. Note: The `callback` and
     * `key_callback` Callables need to accept exactly one Variant parameter, the parameter passed to
     * the Callables will be the value passed to `tag`. Note: This method is implemented only on macOS.
     * Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_icon_radio_check_item
     */
    @JvmStatic
    fun globalMenuAddIconRadioCheckItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconRadioCheckItemBind, singleton, menuRoot, icon?.requireOpenHandle() ?: NULL_SEGMENT, label, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a new item with text `label` to the global menu with ID `menu_root`. Contrarily to normal
     * binary items, multistate items can have more than two states, as defined by `max_states`. Each
     * press or activate of the item will increase the state by one. The default value is defined by
     * `default_state`. Returns index of the inserted item, it's not guaranteed to be the same as
     * `index` value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can
     * be pressed to trigger the menu button even if it's not currently open. The `accelerator` is
     * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
     * | KEY_A` (Ctrl + A). Note: By default, there's no indication of the current item state, it
     * should be changed manually. Note: The `callback` and `key_callback` Callables need to accept
     * exactly one Variant parameter, the parameter passed to the Callables will be the value passed to
     * `tag`. Note: This method is implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_multistate_item
     */
    @JvmStatic
    fun globalMenuAddMultistateItem(menuRoot: String, label: String, maxStates: Int, defaultState: Int, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Key = Key.NONE, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoIntTwoCallableVariantLongIntArgsRetInt(globalMenuAddMultistateItemBind, singleton, menuRoot, label, maxStates, defaultState, callback.target.segment, callback.method, keyCallback.target.segment, keyCallback.method, tag, accelerator.value, index)
    }

    /**
     * Adds a separator between items to the global menu with ID `menu_root`. Separators also occupy an
     * index. Returns index of the inserted item, it's not guaranteed to be the same as `index` value.
     * Note: This method is implemented only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_add_separator
     */
    @JvmStatic
    fun globalMenuAddSeparator(menuRoot: String, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuAddSeparatorBind, singleton, menuRoot, index)
    }

    /**
     * Returns the index of the item with the specified `text`. Indices are automatically assigned to
     * each item by the engine, and cannot be set manually. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_index_from_text
     */
    @JvmStatic
    fun globalMenuGetItemIndexFromText(menuRoot: String, text: String): Int {
        return ObjectCalls.ptrcallWithTwoStringArgsRetInt(globalMenuGetItemIndexFromTextBind, singleton, menuRoot, text)
    }

    /**
     * Returns the index of the item with the specified `tag`. Indices are automatically assigned to
     * each item by the engine, and cannot be set manually. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_index_from_tag
     */
    @JvmStatic
    fun globalMenuGetItemIndexFromTag(menuRoot: String, tag: Any?): Int {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetInt(globalMenuGetItemIndexFromTagBind, singleton, menuRoot, tag)
    }

    /**
     * Returns `true` if the item at index `idx` is checked. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_is_item_checked
     */
    @JvmStatic
    fun globalMenuIsItemChecked(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemCheckedBind, singleton, menuRoot, idx)
    }

    /**
     * Returns `true` if the item at index `idx` is checkable in some way, i.e. if it has a checkbox or
     * radio button. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_is_item_checkable
     */
    @JvmStatic
    fun globalMenuIsItemCheckable(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemCheckableBind, singleton, menuRoot, idx)
    }

    /**
     * Returns `true` if the item at index `idx` has radio button-style checkability. Note: This is
     * purely cosmetic; you must add the logic for checking/unchecking items in radio groups. Note:
     * This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_is_item_radio_checkable
     */
    @JvmStatic
    fun globalMenuIsItemRadioCheckable(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemRadioCheckableBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the callback of the item at index `idx`. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_callback
     */
    @JvmStatic
    fun globalMenuGetItemCallback(menuRoot: String, idx: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemCallbackBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the callback of the item accelerator at index `idx`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_key_callback
     */
    @JvmStatic
    fun globalMenuGetItemKeyCallback(menuRoot: String, idx: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemKeyCallbackBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the metadata of the specified item, which might be of any type. You can set it with
     * `global_menu_set_item_tag`, which provides a simple way of assigning context data to items.
     * Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_tag
     */
    @JvmStatic
    fun globalMenuGetItemTag(menuRoot: String, idx: Int): Any? {
        return ObjectCalls.ptrcallWithStringAndIntArgRetVariantScalar(globalMenuGetItemTagBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the text of the item at index `idx`. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_text
     */
    @JvmStatic
    fun globalMenuGetItemText(menuRoot: String, idx: Int): String {
        return ObjectCalls.ptrcallWithStringAndIntArgRetString(globalMenuGetItemTextBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the submenu ID of the item at index `idx`. See `global_menu_add_submenu_item` for more
     * info on how to add a submenu. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_submenu
     */
    @JvmStatic
    fun globalMenuGetItemSubmenu(menuRoot: String, idx: Int): String {
        return ObjectCalls.ptrcallWithStringAndIntArgRetString(globalMenuGetItemSubmenuBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the accelerator of the item at index `idx`. Accelerators are special combinations of
     * keys that activate the item, no matter which control is focused. Note: This method is
     * implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_accelerator
     */
    @JvmStatic
    fun globalMenuGetItemAccelerator(menuRoot: String, idx: Int): Key {
        return Key(ObjectCalls.ptrcallWithStringAndIntArgRetLong(globalMenuGetItemAcceleratorBind, singleton, menuRoot, idx))
    }

    /**
     * Returns `true` if the item at index `idx` is disabled. When it is disabled it can't be selected,
     * or its action invoked. See `global_menu_set_item_disabled` for more info on how to disable an
     * item. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_is_item_disabled
     */
    @JvmStatic
    fun globalMenuIsItemDisabled(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemDisabledBind, singleton, menuRoot, idx)
    }

    /**
     * Returns `true` if the item at index `idx` is hidden. See `global_menu_set_item_hidden` for more
     * info on how to hide an item. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_is_item_hidden
     */
    @JvmStatic
    fun globalMenuIsItemHidden(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemHiddenBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the tooltip associated with the specified index `idx`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_tooltip
     */
    @JvmStatic
    fun globalMenuGetItemTooltip(menuRoot: String, idx: Int): String {
        return ObjectCalls.ptrcallWithStringAndIntArgRetString(globalMenuGetItemTooltipBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the state of a multistate item. See `global_menu_add_multistate_item` for details. Note:
     * This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_state
     */
    @JvmStatic
    fun globalMenuGetItemState(menuRoot: String, idx: Int): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuGetItemStateBind, singleton, menuRoot, idx)
    }

    /**
     * Returns number of states of a multistate item. See `global_menu_add_multistate_item` for
     * details. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_max_states
     */
    @JvmStatic
    fun globalMenuGetItemMaxStates(menuRoot: String, idx: Int): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuGetItemMaxStatesBind, singleton, menuRoot, idx)
    }

    /**
     * Returns the icon of the item at index `idx`. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_icon
     */
    @JvmStatic
    fun globalMenuGetItemIcon(menuRoot: String, idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithStringAndIntArgRetObject(globalMenuGetItemIconBind, singleton, menuRoot, idx))
    }

    /**
     * Returns the horizontal offset of the item at the given `idx`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_indentation_level
     */
    @JvmStatic
    fun globalMenuGetItemIndentationLevel(menuRoot: String, idx: Int): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuGetItemIndentationLevelBind, singleton, menuRoot, idx)
    }

    /**
     * Sets the checkstate status of the item at index `idx`. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_checked
     */
    @JvmStatic
    fun globalMenuSetItemChecked(menuRoot: String, idx: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemCheckedBind, singleton, menuRoot, idx, checked)
    }

    /**
     * Sets whether the item at index `idx` has a checkbox. If `false`, sets the type of the item to
     * plain text. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_checkable
     */
    @JvmStatic
    fun globalMenuSetItemCheckable(menuRoot: String, idx: Int, checkable: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemCheckableBind, singleton, menuRoot, idx, checkable)
    }

    /**
     * Sets the type of the item at the specified index `idx` to radio button. If `false`, sets the
     * type of the item to plain text. Note: This is purely cosmetic; you must add the logic for
     * checking/unchecking items in radio groups. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_radio_checkable
     */
    @JvmStatic
    fun globalMenuSetItemRadioCheckable(menuRoot: String, idx: Int, checkable: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemRadioCheckableBind, singleton, menuRoot, idx, checkable)
    }

    /**
     * Sets the callback of the item at index `idx`. Callback is emitted when an item is pressed. Note:
     * The `callback` Callable needs to accept exactly one Variant parameter, the parameter passed to
     * the Callable will be the value passed to the `tag` parameter when the menu item was created.
     * Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_callback
     */
    @JvmStatic
    fun globalMenuSetItemCallback(menuRoot: String, idx: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringIntCallableArgs(globalMenuSetItemCallbackBind, singleton, menuRoot, idx, callback.target.segment, callback.method)
    }

    /**
     * Sets the callback of the item at index `idx`. The callback is emitted when an item is hovered.
     * Note: The `callback` Callable needs to accept exactly one Variant parameter, the parameter
     * passed to the Callable will be the value passed to the `tag` parameter when the menu item was
     * created. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_hover_callbacks
     */
    @JvmStatic
    fun globalMenuSetItemHoverCallbacks(menuRoot: String, idx: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringIntCallableArgs(globalMenuSetItemHoverCallbacksBind, singleton, menuRoot, idx, callback.target.segment, callback.method)
    }

    /**
     * Sets the callback of the item at index `idx`. Callback is emitted when its accelerator is
     * activated. Note: The `key_callback` Callable needs to accept exactly one Variant parameter, the
     * parameter passed to the Callable will be the value passed to the `tag` parameter when the menu
     * item was created. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_key_callback
     */
    @JvmStatic
    fun globalMenuSetItemKeyCallback(menuRoot: String, idx: Int, keyCallback: GodotCallable) {
        ObjectCalls.ptrcallWithStringIntCallableArgs(globalMenuSetItemKeyCallbackBind, singleton, menuRoot, idx, keyCallback.target.segment, keyCallback.method)
    }

    /**
     * Sets the metadata of an item, which may be of any type. You can later get it with
     * `global_menu_get_item_tag`, which provides a simple way of assigning context data to items.
     * Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_tag
     */
    @JvmStatic
    fun globalMenuSetItemTag(menuRoot: String, idx: Int, tag: Any?) {
        ObjectCalls.ptrcallWithStringIntAndVariantArg(globalMenuSetItemTagBind, singleton, menuRoot, idx, tag)
    }

    /**
     * Sets the text of the item at index `idx`. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_text
     */
    @JvmStatic
    fun globalMenuSetItemText(menuRoot: String, idx: Int, text: String) {
        ObjectCalls.ptrcallWithStringIntAndStringArgs(globalMenuSetItemTextBind, singleton, menuRoot, idx, text)
    }

    /**
     * Sets the submenu of the item at index `idx`. The submenu is the ID of a global menu root that
     * would be shown when the item is clicked. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_submenu
     */
    @JvmStatic
    fun globalMenuSetItemSubmenu(menuRoot: String, idx: Int, submenu: String) {
        ObjectCalls.ptrcallWithStringIntAndStringArgs(globalMenuSetItemSubmenuBind, singleton, menuRoot, idx, submenu)
    }

    /**
     * Sets the accelerator of the item at index `idx`. `keycode` can be a single `Key`, or a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_accelerator
     */
    @JvmStatic
    fun globalMenuSetItemAccelerator(menuRoot: String, idx: Int, keycode: Key) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(globalMenuSetItemAcceleratorBind, singleton, menuRoot, idx, keycode.value)
    }

    /**
     * Enables/disables the item at index `idx`. When it is disabled, it can't be selected and its
     * action can't be invoked. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_disabled
     */
    @JvmStatic
    fun globalMenuSetItemDisabled(menuRoot: String, idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemDisabledBind, singleton, menuRoot, idx, disabled)
    }

    /**
     * Hides/shows the item at index `idx`. When it is hidden, an item does not appear in a menu and
     * its action cannot be invoked. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_hidden
     */
    @JvmStatic
    fun globalMenuSetItemHidden(menuRoot: String, idx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemHiddenBind, singleton, menuRoot, idx, hidden)
    }

    /**
     * Sets the `String` tooltip of the item at the specified index `idx`. Note: This method is
     * implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_tooltip
     */
    @JvmStatic
    fun globalMenuSetItemTooltip(menuRoot: String, idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithStringIntAndStringArgs(globalMenuSetItemTooltipBind, singleton, menuRoot, idx, tooltip)
    }

    /**
     * Sets the state of a multistate item. See `global_menu_add_multistate_item` for details. Note:
     * This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_state
     */
    @JvmStatic
    fun globalMenuSetItemState(menuRoot: String, idx: Int, state: Int) {
        ObjectCalls.ptrcallWithStringIntAndIntArgs(globalMenuSetItemStateBind, singleton, menuRoot, idx, state)
    }

    /**
     * Sets number of state of a multistate item. See `global_menu_add_multistate_item` for details.
     * Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_max_states
     */
    @JvmStatic
    fun globalMenuSetItemMaxStates(menuRoot: String, idx: Int, maxStates: Int) {
        ObjectCalls.ptrcallWithStringIntAndIntArgs(globalMenuSetItemMaxStatesBind, singleton, menuRoot, idx, maxStates)
    }

    /**
     * Replaces the `Texture2D` icon of the specified `idx`. Note: This method is implemented only on
     * macOS. Note: This method is not supported by macOS "_dock" menu items.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_icon
     */
    @JvmStatic
    fun globalMenuSetItemIcon(menuRoot: String, idx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringIntAndObjectArgs(globalMenuSetItemIconBind, singleton, menuRoot, idx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Sets the horizontal offset of the item at the given `idx`. Note: This method is implemented only
     * on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_set_item_indentation_level
     */
    @JvmStatic
    fun globalMenuSetItemIndentationLevel(menuRoot: String, idx: Int, level: Int) {
        ObjectCalls.ptrcallWithStringIntAndIntArgs(globalMenuSetItemIndentationLevelBind, singleton, menuRoot, idx, level)
    }

    /**
     * Returns number of items in the global menu with ID `menu_root`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_item_count
     */
    @JvmStatic
    fun globalMenuGetItemCount(menuRoot: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(globalMenuGetItemCountBind, singleton, menuRoot)
    }

    /**
     * Removes the item at index `idx` from the global menu `menu_root`. Note: The indices of items
     * after the removed item will be shifted by one. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_remove_item
     */
    @JvmStatic
    fun globalMenuRemoveItem(menuRoot: String, idx: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(globalMenuRemoveItemBind, singleton, menuRoot, idx)
    }

    /**
     * Removes all items from the global menu with ID `menu_root`. Note: This method is implemented
     * only on macOS. Supported system menu IDs:
     *
     * Generated from Godot docs: DisplayServer.global_menu_clear
     */
    @JvmStatic
    fun globalMenuClear(menuRoot: String) {
        ObjectCalls.ptrcallWithStringArg(globalMenuClearBind, singleton, menuRoot)
    }

    /**
     * Returns Dictionary of supported system menu IDs and names. Note: This method is implemented only
     * on macOS.
     *
     * Generated from Godot docs: DisplayServer.global_menu_get_system_menu_roots
     */
    @JvmStatic
    fun globalMenuGetSystemMenuRoots(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(globalMenuGetSystemMenuRootsBind, singleton)
    }

    /**
     * Returns `true` if the synthesizer is generating speech, or have utterance waiting in the queue.
     * Note: This method is implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_is_speaking
     */
    @JvmStatic
    fun ttsIsSpeaking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(ttsIsSpeakingBind, singleton)
    }

    /**
     * Returns `true` if the synthesizer is in a paused state. Note: This method is implemented on
     * Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_is_paused
     */
    @JvmStatic
    fun ttsIsPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(ttsIsPausedBind, singleton)
    }

    /**
     * Returns an `Array` of voice information dictionaries. Each `Dictionary` contains two `String`
     * entries: - `name` is voice name. - `id` is voice identifier. - `language` is language code in
     * `lang_Variant` format. The `lang` part is a 2 or 3-letter code based on the ISO-639 standard, in
     * lowercase. The `Variant` part is an engine-dependent string describing country, region or/and
     * dialect. Note that Godot depends on system libraries for text-to-speech functionality. These
     * libraries are installed by default on Windows and macOS, but not on all Linux distributions. If
     * they are not present, this method will return an empty list. This applies to both Godot users on
     * Linux, as well as end-users on Linux running Godot games that use text-to-speech. Note: This
     * method is implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_get_voices
     */
    @JvmStatic
    fun ttsGetVoices(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(ttsGetVoicesBind, singleton)
    }

    /**
     * Returns a `PackedStringArray` of voice identifiers for the `language`. Note: This method is
     * implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_get_voices_for_language
     */
    @JvmStatic
    fun ttsGetVoicesForLanguage(language: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(ttsGetVoicesForLanguageBind, singleton, language)
    }

    /**
     * Adds an utterance to the queue. If `interrupt` is `true`, the queue is cleared first. - `voice`
     * identifier is one of the `"id"` values returned by `tts_get_voices` or one of the values
     * returned by `tts_get_voices_for_language`. - `volume` ranges from `0` (lowest) to `100`
     * (highest). - `pitch` ranges from `0.0` (lowest) to `2.0` (highest), `1.0` is default pitch for
     * the current voice. - `rate` ranges from `0.1` (lowest) to `10.0` (highest), `1.0` is a normal
     * speaking rate. Other values act as a percentage relative. - `utterance_id` is passed as a
     * parameter to the callback functions. Note: On Windows and Linux (X11/Wayland), utterance `text`
     * can use SSML markup. SSML support is engine and voice dependent. If the engine does not support
     * SSML, you should strip out all XML markup before calling `tts_speak`. Note: The granularity of
     * pitch, rate, and volume is engine and voice dependent. Values may be truncated. Note: This
     * method is implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_speak
     */
    @JvmStatic
    fun ttsSpeak(text: String, voice: String, volume: Int = 50, pitch: Double = 1.0, rate: Double = 1.0, utteranceId: Long = 0L, interrupt: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringIntTwoDoubleLongBoolArgs(ttsSpeakBind, singleton, text, voice, volume, pitch, rate, utteranceId, interrupt)
    }

    /**
     * Puts the synthesizer into a paused state. Note: This method is implemented on Android, iOS, Web,
     * Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_pause
     */
    @JvmStatic
    fun ttsPause() {
        ObjectCalls.ptrcallNoArgs(ttsPauseBind, singleton)
    }

    /**
     * Resumes the synthesizer if it was paused. Note: This method is implemented on Android, iOS, Web,
     * Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_resume
     */
    @JvmStatic
    fun ttsResume() {
        ObjectCalls.ptrcallNoArgs(ttsResumeBind, singleton)
    }

    /**
     * Stops synthesis in progress and removes all utterances from the queue. Note: This method is
     * implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_stop
     */
    @JvmStatic
    fun ttsStop() {
        ObjectCalls.ptrcallNoArgs(ttsStopBind, singleton)
    }

    /**
     * Adds a callback, which is called when the utterance has started, finished, canceled or reached a
     * text boundary. - `TTS_UTTERANCE_STARTED`, `TTS_UTTERANCE_ENDED`, and `TTS_UTTERANCE_CANCELED`
     * callable's method should take one `int` parameter, the utterance ID. - `TTS_UTTERANCE_BOUNDARY`
     * callable's method should take two `int` parameters, the index of the character and the utterance
     * ID. Note: The granularity of the boundary callbacks is engine dependent. Note: This method is
     * implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.tts_set_utterance_callback
     */
    @JvmStatic
    fun ttsSetUtteranceCallback(event: DisplayServer.TTSUtteranceEvent, callable: GodotCallable) {
        ObjectCalls.ptrcallWithLongCallableArgs(ttsSetUtteranceCallbackBind, singleton, event.value, callable.target.segment, callable.method)
    }

    /**
     * Returns `true` if OS supports dark mode. Note: This method is implemented on Android, iOS,
     * macOS, Windows, and Linux (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.is_dark_mode_supported
     */
    @JvmStatic
    fun isDarkModeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDarkModeSupportedBind, singleton)
    }

    /**
     * Returns `true` if OS is using dark mode. Note: This method is implemented on Android, iOS,
     * macOS, Windows, and Linux (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.is_dark_mode
     */
    @JvmStatic
    fun isDarkMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDarkModeBind, singleton)
    }

    /**
     * Returns OS theme accent color. Returns `Color(0, 0, 0, 0)`, if accent color is unknown. Note:
     * This method is implemented on macOS, Windows, Android, and Linux (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.get_accent_color
     */
    @JvmStatic
    fun getAccentColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAccentColorBind, singleton)
    }

    /**
     * Returns the OS theme base color (default control background). Returns `Color(0, 0, 0, 0)` if the
     * base color is unknown. Note: This method is implemented on macOS, Windows, and Android.
     *
     * Generated from Godot docs: DisplayServer.get_base_color
     */
    @JvmStatic
    fun getBaseColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBaseColorBind, singleton)
    }

    /**
     * Sets the callback that should be called when the system's theme settings are changed. `callable`
     * should accept zero arguments. Note: This method is implemented on Android, iOS, macOS, Windows,
     * and Linux (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.set_system_theme_change_callback
     */
    @JvmStatic
    fun setSystemThemeChangeCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setSystemThemeChangeCallbackBind, singleton, callable.target.segment, callable.method)
    }

    /**
     * Sets the current mouse mode. See also `mouse_get_mode`.
     *
     * Generated from Godot docs: DisplayServer.mouse_set_mode
     */
    @JvmStatic
    fun mouseSetMode(mouseMode: DisplayServer.MouseMode) {
        ObjectCalls.ptrcallWithLongArg(mouseSetModeBind, singleton, mouseMode.value)
    }

    /**
     * Returns the current mouse mode. See also `mouse_set_mode`.
     *
     * Generated from Godot docs: DisplayServer.mouse_get_mode
     */
    @JvmStatic
    fun mouseGetMode(): DisplayServer.MouseMode {
        return DisplayServer.MouseMode(ObjectCalls.ptrcallNoArgsRetLong(mouseGetModeBind, singleton))
    }

    /**
     * Sets the mouse cursor position to the given `position` relative to an origin at the upper left
     * corner of the currently focused game Window Manager window. Note: `warp_mouse` is only supported
     * on Windows, macOS, and Linux (X11/Wayland). It has no effect on Android, iOS, and Web.
     *
     * Generated from Godot docs: DisplayServer.warp_mouse
     */
    @JvmStatic
    fun warpMouse(position: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(warpMouseBind, singleton, position)
    }

    /**
     * Returns the mouse cursor's current position in screen coordinates.
     *
     * Generated from Godot docs: DisplayServer.mouse_get_position
     */
    @JvmStatic
    fun mouseGetPosition(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(mouseGetPositionBind, singleton)
    }

    /**
     * Returns the current state of mouse buttons (whether each button is pressed) as a bitmask. If
     * multiple mouse buttons are pressed at the same time, the bits are added together. Equivalent to
     * `Input.get_mouse_button_mask`.
     *
     * Generated from Godot docs: DisplayServer.mouse_get_button_state
     */
    @JvmStatic
    fun mouseGetButtonState(): MouseButtonMask {
        return MouseButtonMask(ObjectCalls.ptrcallNoArgsRetLong(mouseGetButtonStateBind, singleton))
    }

    /**
     * Sets the user's clipboard content to the given string.
     *
     * Generated from Godot docs: DisplayServer.clipboard_set
     */
    @JvmStatic
    fun clipboardSet(clipboard: String) {
        ObjectCalls.ptrcallWithStringArg(clipboardSetBind, singleton, clipboard)
    }

    /**
     * Returns the user's clipboard as a string if possible.
     *
     * Generated from Godot docs: DisplayServer.clipboard_get
     */
    @JvmStatic
    fun clipboardGet(): String {
        return ObjectCalls.ptrcallNoArgsRetString(clipboardGetBind, singleton)
    }

    /**
     * Returns the user's clipboard as an image if possible. Note: This method uses the copied pixel
     * data, e.g. from an image editing software or a web browser, not an image file copied from file
     * explorer.
     *
     * Generated from Godot docs: DisplayServer.clipboard_get_image
     */
    @JvmStatic
    fun clipboardGetImage(): Image? {
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(clipboardGetImageBind, singleton))
    }

    /**
     * Returns `true` if there is a text content on the user's clipboard.
     *
     * Generated from Godot docs: DisplayServer.clipboard_has
     */
    @JvmStatic
    fun clipboardHas(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(clipboardHasBind, singleton)
    }

    /**
     * Returns `true` if there is an image content on the user's clipboard.
     *
     * Generated from Godot docs: DisplayServer.clipboard_has_image
     */
    @JvmStatic
    fun clipboardHasImage(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(clipboardHasImageBind, singleton)
    }

    /**
     * Sets the user's primary
     * (https://unix.stackexchange.com/questions/139191/whats-the-difference-between-primary-selection-and-clipboard-buffer)
     * clipboard content to the given string. This is the clipboard that is set when the user selects
     * text in any application, rather than when pressing Ctrl + C. The clipboard data can then be
     * pasted by clicking the middle mouse button in any application that supports the primary
     * clipboard mechanism. Note: This method is only implemented on Linux (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.clipboard_set_primary
     */
    @JvmStatic
    fun clipboardSetPrimary(clipboardPrimary: String) {
        ObjectCalls.ptrcallWithStringArg(clipboardSetPrimaryBind, singleton, clipboardPrimary)
    }

    /**
     * Returns the user's primary
     * (https://unix.stackexchange.com/questions/139191/whats-the-difference-between-primary-selection-and-clipboard-buffer)
     * clipboard as a string if possible. This is the clipboard that is set when the user selects text
     * in any application, rather than when pressing Ctrl + C. The clipboard data can then be pasted by
     * clicking the middle mouse button in any application that supports the primary clipboard
     * mechanism. Note: This method is only implemented on Linux (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.clipboard_get_primary
     */
    @JvmStatic
    fun clipboardGetPrimary(): String {
        return ObjectCalls.ptrcallNoArgsRetString(clipboardGetPrimaryBind, singleton)
    }

    /**
     * Returns an `Array` of `Rect2`, each of which is the bounding rectangle for a display cutout or
     * notch. These are non-functional areas on edge-to-edge screens used by cameras and sensors.
     * Returns an empty array if the device does not have cutouts. See also `get_display_safe_area`.
     * Note: Currently only implemented on Android. Other platforms will return an empty array even if
     * they do have display cutouts or notches.
     *
     * Generated from Godot docs: DisplayServer.get_display_cutouts
     */
    @JvmStatic
    fun getDisplayCutouts(): List<Rect2> {
        return ObjectCalls.ptrcallNoArgsRetRect2List(getDisplayCutoutsBind, singleton)
    }

    /**
     * Returns the unobscured area of the display where interactive controls should be rendered. See
     * also `get_display_cutouts`. Note: Currently only implemented on Android and iOS. On other
     * platforms, `screen_get_usable_rect(SCREEN_OF_MAIN_WINDOW)` will be returned as a fallback. See
     * also `screen_get_usable_rect`.
     *
     * Generated from Godot docs: DisplayServer.get_display_safe_area
     */
    @JvmStatic
    fun getDisplaySafeArea(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getDisplaySafeAreaBind, singleton)
    }

    /**
     * Returns the number of displays available. Note: This method is implemented on Linux (X11 and
     * Wayland), macOS, and Windows. On other platforms, this method always returns `1`.
     *
     * Generated from Godot docs: DisplayServer.get_screen_count
     */
    @JvmStatic
    fun getScreenCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getScreenCountBind, singleton)
    }

    /**
     * Returns the index of the primary screen. Note: This method is implemented on Linux/X11, macOS,
     * and Windows. On other platforms, this method always returns `0`.
     *
     * Generated from Godot docs: DisplayServer.get_primary_screen
     */
    @JvmStatic
    fun getPrimaryScreen(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPrimaryScreenBind, singleton)
    }

    /**
     * Returns the index of the screen containing the window with the keyboard focus, or the primary
     * screen if there's no focused window. Note: This method is implemented on Linux/X11, macOS, and
     * Windows. On other platforms, this method always returns the primary screen.
     *
     * Generated from Godot docs: DisplayServer.get_keyboard_focus_screen
     */
    @JvmStatic
    fun getKeyboardFocusScreen(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getKeyboardFocusScreenBind, singleton)
    }

    /**
     * Returns the index of the screen that overlaps the most with the given rectangle. Returns
     * `INVALID_SCREEN` if the rectangle doesn't overlap with any screen or has no area.
     *
     * Generated from Godot docs: DisplayServer.get_screen_from_rect
     */
    @JvmStatic
    fun getScreenFromRect(rect: Rect2): Int {
        return ObjectCalls.ptrcallWithRect2ArgRetInt(getScreenFromRectBind, singleton, rect)
    }

    /**
     * Returns the screen's top-left corner position in pixels. Returns `Vector2i.ZERO` if `screen` is
     * invalid. On multi-monitor setups, the screen position is relative to the virtual desktop area.
     * On multi-monitor setups with different screen resolutions or orientations, the origin might be
     * located outside any display like this:
     *
     * Generated from Godot docs: DisplayServer.screen_get_position
     */
    @JvmStatic
    fun screenGetPosition(screen: Int = -1): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(screenGetPositionBind, singleton, screen)
    }

    /**
     * Returns the screen's size in pixels. See also `screen_get_position` and
     * `screen_get_usable_rect`. Returns `Vector2i.ZERO` if `screen` is invalid. Note: One of the
     * following constants can be used as `screen`: `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`,
     * `SCREEN_WITH_MOUSE_FOCUS`, or `SCREEN_WITH_KEYBOARD_FOCUS`.
     *
     * Generated from Godot docs: DisplayServer.screen_get_size
     */
    @JvmStatic
    fun screenGetSize(screen: Int = -1): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(screenGetSizeBind, singleton, screen)
    }

    /**
     * Returns the portion of the screen that is not obstructed by a status bar in pixels. See also
     * `screen_get_size`. Note: One of the following constants can be used as `screen`:
     * `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`, `SCREEN_WITH_MOUSE_FOCUS`, or
     * `SCREEN_WITH_KEYBOARD_FOCUS`. Note: This method is implemented on Linux/X11, macOS, and Windows.
     * On other platforms, this method always returns `Rect2i(screen_get_position(screen),
     * screen_get_size(screen))`.
     *
     * Generated from Godot docs: DisplayServer.screen_get_usable_rect
     */
    @JvmStatic
    fun screenGetUsableRect(screen: Int = -1): Rect2i {
        return ObjectCalls.ptrcallWithIntArgRetRect2i(screenGetUsableRectBind, singleton, screen)
    }

    /**
     * Returns the dots per inch density of the specified screen. Returns platform specific default
     * value if `screen` is invalid. Note: One of the following constants can be used as `screen`:
     * `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`, `SCREEN_WITH_MOUSE_FOCUS`, or
     * `SCREEN_WITH_KEYBOARD_FOCUS`. Note: On macOS, returned value is inaccurate if fractional display
     * scaling mode is used. Note: On Android devices, the actual screen densities are grouped into six
     * generalized densities:
     *
     * Generated from Godot docs: DisplayServer.screen_get_dpi
     */
    @JvmStatic
    fun screenGetDpi(screen: Int = -1): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(screenGetDpiBind, singleton, screen)
    }

    /**
     * Returns the scale factor of the specified screen by index. Returns `1.0` if `screen` is invalid.
     * See also `screen_get_max_scale`. Note: One of the following constants can be used as `screen`:
     * `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`, `SCREEN_WITH_MOUSE_FOCUS`, or
     * `SCREEN_WITH_KEYBOARD_FOCUS`. Note: On macOS, the returned value is `2.0` for hiDPI (Retina)
     * screens, and `1.0` for all other cases. Note: On Linux (Wayland), the returned value is accurate
     * only when `screen` is `SCREEN_OF_MAIN_WINDOW`. Due to API limitations, passing a direct index
     * will return a rounded-up integer, if the screen has a fractional scale (e.g. `1.25` would get
     * rounded up to `2.0`). Note: This method is implemented on Android, iOS, Web, macOS, and Linux
     * (Wayland). On other platforms, this method always returns `1.0`.
     *
     * Generated from Godot docs: DisplayServer.screen_get_scale
     */
    @JvmStatic
    fun screenGetScale(screen: Int = -1): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(screenGetScaleBind, singleton, screen)
    }

    /**
     * Returns `true` if touch events are available (Android or iOS), the capability is detected on the
     * Web platform or if `ProjectSettings.input_devices/pointing/emulate_touch_from_mouse` is `true`.
     *
     * Generated from Godot docs: DisplayServer.is_touchscreen_available
     */
    @JvmStatic
    fun isTouchscreenAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTouchscreenAvailableBind, singleton)
    }

    /**
     * Returns the greatest scale factor of all screens. See also `screen_get_scale`. Note: On macOS,
     * the returned value is `2.0` if there is at least one hiDPI (Retina) screen in the system, and
     * `1.0` in all other cases.
     *
     * Generated from Godot docs: DisplayServer.screen_get_max_scale
     */
    @JvmStatic
    fun screenGetMaxScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(screenGetMaxScaleBind, singleton)
    }

    /**
     * Returns the current refresh rate of the specified screen. When V-Sync is enabled, this returns
     * the maximum framerate the project can effectively reach. Returns `-1.0` if `screen` is invalid
     * or the `DisplayServer` fails to find the refresh rate for the specified screen. To fallback to a
     * default refresh rate if the method fails, try:
     *
     * Generated from Godot docs: DisplayServer.screen_get_refresh_rate
     */
    @JvmStatic
    fun screenGetRefreshRate(screen: Int = -1): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(screenGetRefreshRateBind, singleton, screen)
    }

    /**
     * Returns the color of the pixel at the given screen `position`. On multi-monitor setups, the
     * screen position is relative to the virtual desktop area. Note: This method is implemented on
     * Linux (X11, excluding XWayland), macOS, and Windows. On other platforms, this method always
     * returns `Color(0, 0, 0, 1)`. Note: On macOS, this method requires the "Screen Recording"
     * permission. If permission is not granted, this method returns a color from a screenshot that
     * will not include other application windows or OS elements not related to the application.
     *
     * Generated from Godot docs: DisplayServer.screen_get_pixel
     */
    @JvmStatic
    fun screenGetPixel(position: Vector2i): Color {
        return ObjectCalls.ptrcallWithVector2iArgRetColor(screenGetPixelBind, singleton, position)
    }

    /**
     * Returns a screenshot of the `screen`. Returns `null` if `screen` is invalid or the
     * `DisplayServer` fails to capture screenshot. Note: One of the following constants can be used as
     * `screen`: `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`, `SCREEN_WITH_MOUSE_FOCUS`, or
     * `SCREEN_WITH_KEYBOARD_FOCUS`. Note: This method is implemented on Linux (X11, excluding
     * XWayland), macOS, and Windows. On other platforms, this method always returns `null`. Note: On
     * macOS, this method requires the "Screen Recording" permission. If permission is not granted,
     * this method returns a screenshot that will not include other application windows or OS elements
     * not related to the application.
     *
     * Generated from Godot docs: DisplayServer.screen_get_image
     */
    @JvmStatic
    fun screenGetImage(screen: Int = -1): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithIntArgRetObject(screenGetImageBind, singleton, screen))
    }

    /**
     * Returns a screenshot of the screen region defined by `rect`. Returns `null` if `rect` is outside
     * screen bounds or the `DisplayServer` fails to capture screenshot. Note: This method is
     * implemented on macOS and Windows. On other platforms, this method always returns `null`. Note:
     * On macOS, this method requires the "Screen Recording" permission. If permission is not granted,
     * this method returns a screenshot that will not include other application windows or OS elements
     * not related to the application.
     *
     * Generated from Godot docs: DisplayServer.screen_get_image_rect
     */
    @JvmStatic
    fun screenGetImageRect(rect: Rect2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRect2iArgRetObject(screenGetImageRectBind, singleton, rect))
    }

    /**
     * Sets the `screen`'s `orientation`. See also `screen_get_orientation`. Note: One of the following
     * constants can be used as `screen`: `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`,
     * `SCREEN_WITH_MOUSE_FOCUS`, or `SCREEN_WITH_KEYBOARD_FOCUS`. Note: This method is implemented on
     * Android and iOS. Note: On iOS, this method has no effect if
     * `ProjectSettings.display/window/handheld/orientation` is not set to `SCREEN_SENSOR`.
     *
     * Generated from Godot docs: DisplayServer.screen_set_orientation
     */
    @JvmStatic
    fun screenSetOrientation(orientation: DisplayServer.ScreenOrientation, screen: Int = -1) {
        ObjectCalls.ptrcallWithLongAndIntArgs(screenSetOrientationBind, singleton, orientation.value, screen)
    }

    /**
     * Returns the `screen`'s current orientation. See also `screen_set_orientation`. Returns
     * `SCREEN_LANDSCAPE` if `screen` is invalid. Note: One of the following constants can be used as
     * `screen`: `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`, `SCREEN_WITH_MOUSE_FOCUS`, or
     * `SCREEN_WITH_KEYBOARD_FOCUS`. Note: This method is implemented on Android and iOS. On other
     * platforms, this method always returns `SCREEN_LANDSCAPE`.
     *
     * Generated from Godot docs: DisplayServer.screen_get_orientation
     */
    @JvmStatic
    fun screenGetOrientation(screen: Int = -1): DisplayServer.ScreenOrientation {
        return DisplayServer.ScreenOrientation(ObjectCalls.ptrcallWithIntArgRetLong(screenGetOrientationBind, singleton, screen))
    }

    /**
     * Sets whether the screen should never be turned off by the operating system's power-saving
     * measures. See also `screen_is_kept_on`.
     *
     * Generated from Godot docs: DisplayServer.screen_set_keep_on
     */
    @JvmStatic
    fun screenSetKeepOn(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(screenSetKeepOnBind, singleton, enable)
    }

    /**
     * Returns `true` if the screen should never be turned off by the operating system's power-saving
     * measures. See also `screen_set_keep_on`.
     *
     * Generated from Godot docs: DisplayServer.screen_is_kept_on
     */
    @JvmStatic
    fun screenIsKeptOn(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(screenIsKeptOnBind, singleton)
    }

    /**
     * Returns the list of Godot window IDs belonging to this process. Note: Native dialogs are not
     * included in this list.
     *
     * Generated from Godot docs: DisplayServer.get_window_list
     */
    @JvmStatic
    fun getWindowList(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getWindowListBind, singleton)
    }

    /**
     * Returns the ID of the window at the specified screen `position` (in pixels). On multi-monitor
     * setups, the screen position is relative to the virtual desktop area. On multi-monitor setups
     * with different screen resolutions or orientations, the origin may be located outside any display
     * like this:
     *
     * Generated from Godot docs: DisplayServer.get_window_at_screen_position
     */
    @JvmStatic
    fun getWindowAtScreenPosition(position: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getWindowAtScreenPositionBind, singleton, position)
    }

    /**
     * Returns internal structure pointers for use in plugins. Note: This method is implemented on
     * Android, Linux (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_get_native_handle
     */
    @JvmStatic
    fun windowGetNativeHandle(handleType: DisplayServer.HandleType, windowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetLong(windowGetNativeHandleBind, singleton, handleType.value, windowId)
    }

    /**
     * Returns ID of the active popup window, or `INVALID_WINDOW_ID` if there is none.
     *
     * Generated from Godot docs: DisplayServer.window_get_active_popup
     */
    @JvmStatic
    fun windowGetActivePopup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(windowGetActivePopupBind, singleton)
    }

    /**
     * Sets the bounding box of control, or menu item that was used to open the popup window, in the
     * screen coordinate system. Clicking this area will not auto-close this popup.
     *
     * Generated from Godot docs: DisplayServer.window_set_popup_safe_rect
     */
    @JvmStatic
    fun windowSetPopupSafeRect(window: Int, rect: Rect2i) {
        ObjectCalls.ptrcallWithIntAndRect2iArg(windowSetPopupSafeRectBind, singleton, window, rect)
    }

    /**
     * Returns the bounding box of control, or menu item that was used to open the popup window, in the
     * screen coordinate system.
     *
     * Generated from Godot docs: DisplayServer.window_get_popup_safe_rect
     */
    @JvmStatic
    fun windowGetPopupSafeRect(window: Int): Rect2i {
        return ObjectCalls.ptrcallWithIntArgRetRect2i(windowGetPopupSafeRectBind, singleton, window)
    }

    /**
     * Sets the title of the given window to `title`. Note: It's recommended to change this value using
     * `Window.title` instead. Note: Avoid changing the window title every frame, as this can cause
     * performance issues on certain window managers. Try to change the window title only a few times
     * per second at most.
     *
     * Generated from Godot docs: DisplayServer.window_set_title
     */
    @JvmStatic
    fun windowSetTitle(title: String, windowId: Int = 0) {
        ObjectCalls.ptrcallWithStringAndIntArg(windowSetTitleBind, singleton, title, windowId)
    }

    /**
     * Returns the estimated window title bar size (including text and window buttons) for the window
     * specified by `window_id` (in pixels). This method does not change the window title. Note: This
     * method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_get_title_size
     */
    @JvmStatic
    fun windowGetTitleSize(title: String, windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithStringAndIntArgRetVector2i(windowGetTitleSizeBind, singleton, title, windowId)
    }

    /**
     * Sets a polygonal region of the window which accepts mouse events. Mouse events outside the
     * region will be passed through. Passing an empty array will disable passthrough support (all
     * mouse events will be intercepted by the window, which is the default behavior).
     *
     * Generated from Godot docs: DisplayServer.window_set_mouse_passthrough
     */
    @JvmStatic
    fun windowSetMousePassthrough(region: List<Vector2>, windowId: Int = 0) {
        ObjectCalls.ptrcallWithPackedVector2ListAndIntArgs(windowSetMousePassthroughBind, singleton, region, windowId)
    }

    /**
     * Returns the screen the window specified by `window_id` is currently positioned on. If the screen
     * overlaps multiple displays, the screen where the window's center is located is returned. See
     * also `window_set_current_screen`. Returns `INVALID_SCREEN` if `window_id` is invalid. Note: This
     * method is implemented on Linux/X11, macOS, and Windows. On other platforms, this method always
     * returns `0`.
     *
     * Generated from Godot docs: DisplayServer.window_get_current_screen
     */
    @JvmStatic
    fun windowGetCurrentScreen(windowId: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(windowGetCurrentScreenBind, singleton, windowId)
    }

    /**
     * Moves the window specified by `window_id` to the specified `screen`. See also
     * `window_get_current_screen`. Note: One of the following constants can be used as `screen`:
     * `SCREEN_OF_MAIN_WINDOW`, `SCREEN_PRIMARY`, `SCREEN_WITH_MOUSE_FOCUS`, or
     * `SCREEN_WITH_KEYBOARD_FOCUS`. Note: This method is implemented on Linux/X11, macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_set_current_screen
     */
    @JvmStatic
    fun windowSetCurrentScreen(screen: Int, windowId: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(windowSetCurrentScreenBind, singleton, screen, windowId)
    }

    /**
     * Returns the position of the client area of the given window on the screen.
     *
     * Generated from Godot docs: DisplayServer.window_get_position
     */
    @JvmStatic
    fun windowGetPosition(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetPositionBind, singleton, windowId)
    }

    /**
     * Returns the position of the given window on the screen including the borders drawn by the
     * operating system. See also `window_get_position`.
     *
     * Generated from Godot docs: DisplayServer.window_get_position_with_decorations
     */
    @JvmStatic
    fun windowGetPositionWithDecorations(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetPositionWithDecorationsBind, singleton, windowId)
    }

    /**
     * Sets the position of the given window to `position`. On multi-monitor setups, the screen
     * position is relative to the virtual desktop area. On multi-monitor setups with different screen
     * resolutions or orientations, the origin may be located outside any display like this:
     *
     * Generated from Godot docs: DisplayServer.window_set_position
     */
    @JvmStatic
    fun windowSetPosition(position: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetPositionBind, singleton, position, windowId)
    }

    /**
     * Returns the size of the window specified by `window_id` (in pixels), excluding the borders drawn
     * by the operating system. This is also called the "client area". See also
     * `window_get_size_with_decorations`, `window_set_size` and `window_get_position`.
     *
     * Generated from Godot docs: DisplayServer.window_get_size
     */
    @JvmStatic
    fun windowGetSize(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetSizeBind, singleton, windowId)
    }

    /**
     * Sets the size of the given window to `size` (in pixels). See also `window_get_size` and
     * `window_get_position`. Note: It's recommended to change this value using `Window.size` instead.
     *
     * Generated from Godot docs: DisplayServer.window_set_size
     */
    @JvmStatic
    fun windowSetSize(size: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetSizeBind, singleton, size, windowId)
    }

    /**
     * Sets the `callback` that will be called when the window specified by `window_id` is moved or
     * resized. Warning: Advanced users only! Adding such a callback to a `Window` node will override
     * its default implementation, which can introduce bugs.
     *
     * Generated from Godot docs: DisplayServer.window_set_rect_changed_callback
     */
    @JvmStatic
    fun windowSetRectChangedCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetRectChangedCallbackBind, singleton, callback.target.segment, callback.method, windowId)
    }

    /**
     * Sets the `callback` that will be called when an event occurs in the window specified by
     * `window_id`. Warning: Advanced users only! Adding such a callback to a `Window` node will
     * override its default implementation, which can introduce bugs.
     *
     * Generated from Godot docs: DisplayServer.window_set_window_event_callback
     */
    @JvmStatic
    fun windowSetWindowEventCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetWindowEventCallbackBind, singleton, callback.target.segment, callback.method, windowId)
    }

    /**
     * Sets the `callback` that should be called when any `InputEvent` is sent to the window specified
     * by `window_id`. Warning: Advanced users only! Adding such a callback to a `Window` node will
     * override its default implementation, which can introduce bugs.
     *
     * Generated from Godot docs: DisplayServer.window_set_input_event_callback
     */
    @JvmStatic
    fun windowSetInputEventCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetInputEventCallbackBind, singleton, callback.target.segment, callback.method, windowId)
    }

    /**
     * Sets the `callback` that should be called when text is entered using the virtual keyboard to the
     * window specified by `window_id`. Warning: Advanced users only! Adding such a callback to a
     * `Window` node will override its default implementation, which can introduce bugs.
     *
     * Generated from Godot docs: DisplayServer.window_set_input_text_callback
     */
    @JvmStatic
    fun windowSetInputTextCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetInputTextCallbackBind, singleton, callback.target.segment, callback.method, windowId)
    }

    /**
     * Sets the `callback` that should be called when files are dropped from the operating system's
     * file manager to the window specified by `window_id`. `callback` should take one
     * `PackedStringArray` argument, which is the list of dropped files. Warning: Advanced users only!
     * Adding such a callback to a `Window` node will override its default implementation, which can
     * introduce bugs. Note: This method is implemented on Windows, macOS, Linux (X11/Wayland), and
     * Web.
     *
     * Generated from Godot docs: DisplayServer.window_set_drop_files_callback
     */
    @JvmStatic
    fun windowSetDropFilesCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetDropFilesCallbackBind, singleton, callback.target.segment, callback.method, windowId)
    }

    /**
     * Returns the `Object.get_instance_id` of the `Window` the `window_id` is attached to.
     *
     * Generated from Godot docs: DisplayServer.window_get_attached_instance_id
     */
    @JvmStatic
    fun windowGetAttachedInstanceId(windowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(windowGetAttachedInstanceIdBind, singleton, windowId)
    }

    /**
     * Returns the window's maximum size (in pixels). See also `window_set_max_size`.
     *
     * Generated from Godot docs: DisplayServer.window_get_max_size
     */
    @JvmStatic
    fun windowGetMaxSize(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetMaxSizeBind, singleton, windowId)
    }

    /**
     * Sets the maximum size of the window specified by `window_id` in pixels. Normally, the user will
     * not be able to drag the window to make it larger than the specified size. See also
     * `window_get_max_size`. Note: It's recommended to change this value using `Window.max_size`
     * instead. Note: Using third-party tools, it is possible for users to disable window geometry
     * restrictions and therefore bypass this limit.
     *
     * Generated from Godot docs: DisplayServer.window_set_max_size
     */
    @JvmStatic
    fun windowSetMaxSize(maxSize: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetMaxSizeBind, singleton, maxSize, windowId)
    }

    /**
     * Returns the window's minimum size (in pixels). See also `window_set_min_size`.
     *
     * Generated from Godot docs: DisplayServer.window_get_min_size
     */
    @JvmStatic
    fun windowGetMinSize(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetMinSizeBind, singleton, windowId)
    }

    /**
     * Sets the minimum size for the given window to `min_size` in pixels. Normally, the user will not
     * be able to drag the window to make it smaller than the specified size. See also
     * `window_get_min_size`. Note: It's recommended to change this value using `Window.min_size`
     * instead. Note: By default, the main window has a minimum size of `Vector2i(64, 64)`. This
     * prevents issues that can arise when the window is resized to a near-zero size. Note: Using
     * third-party tools, it is possible for users to disable window geometry restrictions and
     * therefore bypass this limit.
     *
     * Generated from Godot docs: DisplayServer.window_set_min_size
     */
    @JvmStatic
    fun windowSetMinSize(minSize: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetMinSizeBind, singleton, minSize, windowId)
    }

    /**
     * Returns the size of the window specified by `window_id` (in pixels), including the borders drawn
     * by the operating system. See also `window_get_size`.
     *
     * Generated from Godot docs: DisplayServer.window_get_size_with_decorations
     */
    @JvmStatic
    fun windowGetSizeWithDecorations(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetSizeWithDecorationsBind, singleton, windowId)
    }

    /**
     * Returns the mode of the given window.
     *
     * Generated from Godot docs: DisplayServer.window_get_mode
     */
    @JvmStatic
    fun windowGetMode(windowId: Int = 0): DisplayServer.WindowMode {
        return DisplayServer.WindowMode(ObjectCalls.ptrcallWithIntArgRetLong(windowGetModeBind, singleton, windowId))
    }

    /**
     * Sets window mode for the given window to `mode`. Note: On Android, setting it to
     * `WINDOW_MODE_FULLSCREEN` or `WINDOW_MODE_EXCLUSIVE_FULLSCREEN` will enable immersive mode. Note:
     * Setting the window to full screen forcibly sets the borderless flag to `true`, so make sure to
     * set it back to `false` when not wanted.
     *
     * Generated from Godot docs: DisplayServer.window_set_mode
     */
    @JvmStatic
    fun windowSetMode(mode: DisplayServer.WindowMode, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowSetModeBind, singleton, mode.value, windowId)
    }

    /**
     * Enables or disables the given window's given `flag`.
     *
     * Generated from Godot docs: DisplayServer.window_set_flag
     */
    @JvmStatic
    fun windowSetFlag(flag: DisplayServer.WindowFlags, enabled: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongBoolIntArgs(windowSetFlagBind, singleton, flag.value, enabled, windowId)
    }

    /**
     * Returns the current value of the given window's `flag`.
     *
     * Generated from Godot docs: DisplayServer.window_get_flag
     */
    @JvmStatic
    fun windowGetFlag(flag: DisplayServer.WindowFlags, windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetBool(windowGetFlagBind, singleton, flag.value, windowId)
    }

    /**
     * Sets the window icon (usually displayed in the top-left corner) for the window specified by
     * `window_id`. Note: This method is implemented on Linux and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_set_icon
     */
    @JvmStatic
    fun windowSetIcon(icon: Image?, windowId: Int = 0) {
        ObjectCalls.ptrcallWithObjectAndIntArg(windowSetIconBind, singleton, icon?.requireOpenHandle() ?: NULL_SEGMENT, windowId)
    }

    /**
     * When `WINDOW_FLAG_EXTEND_TO_TITLE` flag is set, set offset to the center of the first titlebar
     * button. Note: This flag is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.window_set_window_buttons_offset
     */
    @JvmStatic
    fun windowSetWindowButtonsOffset(offset: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetWindowButtonsOffsetBind, singleton, offset, windowId)
    }

    /**
     * Returns left margins (`x`), right margins (`y`) and height (`z`) of the title that are safe to
     * use (contains no buttons or other elements) when `WINDOW_FLAG_EXTEND_TO_TITLE` flag is set.
     *
     * Generated from Godot docs: DisplayServer.window_get_safe_title_margins
     */
    @JvmStatic
    fun windowGetSafeTitleMargins(windowId: Int = 0): Vector3i {
        return ObjectCalls.ptrcallWithIntArgRetVector3i(windowGetSafeTitleMarginsBind, singleton, windowId)
    }

    /**
     * Makes the window specified by `window_id` request attention, which is materialized by the window
     * title and taskbar entry blinking until the window is focused. This usually has no visible effect
     * if the window is currently focused. The exact behavior varies depending on the operating system.
     *
     * Generated from Godot docs: DisplayServer.window_request_attention
     */
    @JvmStatic
    fun windowRequestAttention(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(windowRequestAttentionBind, singleton, windowId)
    }

    /**
     * Creates a progress bar on the taskbar/dock icon of the window specified by `window_id` if it
     * does not exist, sets the progress of the icon. `value` acts as a relative percentage value,
     * ranges from `0.0` (lowest) to `1.0` (highest). Note: This method is implemented only on Windows
     * and macOS. Note: On macOS, the progress bar is displayed only for the main window.
     *
     * Generated from Godot docs: DisplayServer.window_set_taskbar_progress_value
     */
    @JvmStatic
    fun windowSetTaskbarProgressValue(value: Double, windowId: Int = 0) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(windowSetTaskbarProgressValueBind, singleton, value, windowId)
    }

    /**
     * Sets the type and state of the progress bar on the taskbar/dock icon of the window specified by
     * `window_id`. See `ProgressState` for possible values and how each mode behaves. Note: This
     * method is implemented only on Windows and macOS. Note: On macOS, the progress bar is displayed
     * only for the main window.
     *
     * Generated from Godot docs: DisplayServer.window_set_taskbar_progress_state
     */
    @JvmStatic
    fun windowSetTaskbarProgressState(state: DisplayServer.ProgressState, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowSetTaskbarProgressStateBind, singleton, state.value, windowId)
    }

    /**
     * Moves the window specified by `window_id` to the foreground, so that it is visible over other
     * windows.
     *
     * Generated from Godot docs: DisplayServer.window_move_to_foreground
     */
    @JvmStatic
    fun windowMoveToForeground(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(windowMoveToForegroundBind, singleton, windowId)
    }

    /**
     * Returns `true` if the window specified by `window_id` is focused.
     *
     * Generated from Godot docs: DisplayServer.window_is_focused
     */
    @JvmStatic
    fun windowIsFocused(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsFocusedBind, singleton, windowId)
    }

    /**
     * Returns `true` if anything can be drawn in the window specified by `window_id`, `false`
     * otherwise. Using the `--disable-render-loop` command line argument or a headless build will
     * return `false`.
     *
     * Generated from Godot docs: DisplayServer.window_can_draw
     */
    @JvmStatic
    fun windowCanDraw(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowCanDrawBind, singleton, windowId)
    }

    /**
     * Sets window transient parent. Transient window will be destroyed with its transient parent and
     * will return focus to their parent when closed. The transient window is displayed on top of a
     * non-exclusive full-screen parent window. Transient windows can't enter full-screen mode. Note:
     * It's recommended to change this value using `Window.transient` instead. Note: The behavior might
     * be different depending on the platform.
     *
     * Generated from Godot docs: DisplayServer.window_set_transient
     */
    @JvmStatic
    fun windowSetTransient(windowId: Int, parentWindowId: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(windowSetTransientBind, singleton, windowId, parentWindowId)
    }

    /**
     * If set to `true`, this window will always stay on top of its parent window, parent window will
     * ignore input while this window is opened. Note: On macOS, exclusive windows are confined to the
     * same space (virtual desktop or screen) as the parent window. Note: This method is implemented on
     * macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_set_exclusive
     */
    @JvmStatic
    fun windowSetExclusive(windowId: Int, exclusive: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(windowSetExclusiveBind, singleton, windowId, exclusive)
    }

    /**
     * Sets whether Input Method Editor (https://en.wikipedia.org/wiki/Input_method) should be enabled
     * for the window specified by `window_id`. See also `window_set_ime_position`.
     *
     * Generated from Godot docs: DisplayServer.window_set_ime_active
     */
    @JvmStatic
    fun windowSetImeActive(active: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithBoolAndIntArgs(windowSetImeActiveBind, singleton, active, windowId)
    }

    /**
     * Sets the position of the Input Method Editor (https://en.wikipedia.org/wiki/Input_method) popup
     * for the specified `window_id`. Only effective if `window_set_ime_active` was set to `true` for
     * the specified `window_id`.
     *
     * Generated from Godot docs: DisplayServer.window_set_ime_position
     */
    @JvmStatic
    fun windowSetImePosition(position: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetImePositionBind, singleton, position, windowId)
    }

    /**
     * Sets the V-Sync mode of the given window. See also
     * `ProjectSettings.display/window/vsync/vsync_mode`. Depending on the platform and used renderer,
     * the engine will fall back to `VSYNC_ENABLED` if the desired mode is not supported. Note: V-Sync
     * modes other than `VSYNC_ENABLED` are only supported in the Forward+ and Mobile rendering
     * methods, not Compatibility.
     *
     * Generated from Godot docs: DisplayServer.window_set_vsync_mode
     */
    @JvmStatic
    fun windowSetVsyncMode(vsyncMode: DisplayServer.VSyncMode, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowSetVsyncModeBind, singleton, vsyncMode.value, windowId)
    }

    /**
     * Returns the V-Sync mode of the given window.
     *
     * Generated from Godot docs: DisplayServer.window_get_vsync_mode
     */
    @JvmStatic
    fun windowGetVsyncMode(windowId: Int = 0): DisplayServer.VSyncMode {
        return DisplayServer.VSyncMode(ObjectCalls.ptrcallWithIntArgRetLong(windowGetVsyncModeBind, singleton, windowId))
    }

    /**
     * Returns `true` if the window specified by `window_id` supports HDR output. This depends on the
     * platform, screen capabilities, system settings, and the screen the window is currently on.
     *
     * Generated from Godot docs: DisplayServer.window_is_hdr_output_supported
     */
    @JvmStatic
    fun windowIsHdrOutputSupported(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsHdrOutputSupportedBind, singleton, windowId)
    }

    /**
     * If `enable` is `true`, HDR output is requested for the window specified by `window_id`. The
     * window will automatically switch between HDR and SDR if it is moved between screens, screen
     * capabilities change, or system settings are modified. This will internally force
     * `Viewport.use_hdr_2d` to be enabled on the main `Viewport`. All other `SubViewport` of the
     * `Window` must have their `Viewport.use_hdr_2d` property enabled to produce HDR output.
     * Corresponds to `Window.hdr_output_requested`.
     *
     * Generated from Godot docs: DisplayServer.window_request_hdr_output
     */
    @JvmStatic
    fun windowRequestHdrOutput(enable: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithBoolAndIntArgs(windowRequestHdrOutputBind, singleton, enable, windowId)
    }

    /**
     * Returns `true` if HDR output is requested for the window specified by `window_id`. Corresponds
     * to `Window.hdr_output_requested`.
     *
     * Generated from Godot docs: DisplayServer.window_is_hdr_output_requested
     */
    @JvmStatic
    fun windowIsHdrOutputRequested(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsHdrOutputRequestedBind, singleton, windowId)
    }

    /**
     * Returns `true` if HDR output is currently enabled for the window specified by `window_id`. The
     * returned value may change dynamically based on system settings, screen capabilities, and which
     * screen the window is currently on.
     *
     * Generated from Godot docs: DisplayServer.window_is_hdr_output_enabled
     */
    @JvmStatic
    fun windowIsHdrOutputEnabled(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsHdrOutputEnabledBind, singleton, windowId)
    }

    /**
     * Sets the reference white luminance in nits (cd/m²) for HDR output by the window specified by
     * `window_id`. If `reference_luminance` is negative, the window automatically adjusts to the
     * brightness set by the operating system. By default, this luminance is set to `-1.0` for every
     * window. Typically this property should be left at this default value, but may optionally be
     * exposed as an "HDR Brightness" in-game setting to allow the player to adjust the brightness of
     * their game, independently of their device settings. See also
     * `window_get_hdr_output_current_reference_luminance` and
     * `window_get_hdr_output_reference_luminance`. Note: This method is only implemented on Windows.
     * Other platforms will always use the reference luminance that is reported by the operating
     * system.
     *
     * Generated from Godot docs: DisplayServer.window_set_hdr_output_reference_luminance
     */
    @JvmStatic
    fun windowSetHdrOutputReferenceLuminance(referenceLuminance: Double, windowId: Int = 0) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(windowSetHdrOutputReferenceLuminanceBind, singleton, referenceLuminance, windowId)
    }

    /**
     * Returns the reference white luminance in nits (cd/m²) set for HDR output by the window specified
     * by `window_id`. Negative values indicate that the value is being automatically adjusted to match
     * the operating system brightness. See also `window_get_hdr_output_current_reference_luminance`.
     *
     * Generated from Godot docs: DisplayServer.window_get_hdr_output_reference_luminance
     */
    @JvmStatic
    fun windowGetHdrOutputReferenceLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputReferenceLuminanceBind, singleton, windowId)
    }

    /**
     * When `window_is_hdr_output_enabled` returns `true`, this returns the current reference white
     * luminance in nits (cd/m²) for HDR output by the window specified by `window_id`. If the
     * reference luminance is being automatically adjusted to match the operating system brightness,
     * this will return that value. Otherwise, it will return the value set by
     * `window_set_hdr_output_reference_luminance`. This reference luminance value is used when
     * calculating `window_get_output_max_linear_value`. Note: This reference white luminance may not
     * match the physical behavior of the screen, but will always be proportionally correct relative to
     * `window_get_hdr_output_current_max_luminance`.
     *
     * Generated from Godot docs: DisplayServer.window_get_hdr_output_current_reference_luminance
     */
    @JvmStatic
    fun windowGetHdrOutputCurrentReferenceLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputCurrentReferenceLuminanceBind, singleton, windowId)
    }

    /**
     * Sets the maximum luminance in nits (cd/m²) for HDR output by the window specified by
     * `window_id`. If `max_luminance` is negative, the window uses the screen's maximum luminance that
     * is reported by the operating system. By default, this luminance is set to `-1.0` for every
     * window. Typically this property should be left at this default value, but may optionally be
     * exposed through in-game settings to allow the player to correct an inaccurate maximum luminance
     * reported by the operating system. See also `window_get_hdr_output_current_max_luminance` and
     * `window_get_hdr_output_max_luminance`. Note: This method is only implemented on macOS and
     * Windows. Other platforms will always use the screen's maximum luminance that is reported by the
     * operating system.
     *
     * Generated from Godot docs: DisplayServer.window_set_hdr_output_max_luminance
     */
    @JvmStatic
    fun windowSetHdrOutputMaxLuminance(maxLuminance: Double, windowId: Int = 0) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(windowSetHdrOutputMaxLuminanceBind, singleton, maxLuminance, windowId)
    }

    /**
     * Returns the maximum luminance in nits (cd/m²) set for HDR output by the window specified by
     * `window_id`. Negative values indicate that the value is being automatically adjusted based on
     * the screen's capabilities. See also `window_get_hdr_output_current_max_luminance`.
     *
     * Generated from Godot docs: DisplayServer.window_get_hdr_output_max_luminance
     */
    @JvmStatic
    fun windowGetHdrOutputMaxLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputMaxLuminanceBind, singleton, windowId)
    }

    /**
     * When `window_is_hdr_output_enabled` returns `true`, this returns the current maximum luminance
     * in nits (cd/m²) for HDR output by the window specified by `window_id`. If the maximum luminance
     * is being automatically adjusted based on the screen's capabilities, this method will return that
     * value. Otherwise, it will return the value set by `window_set_hdr_output_max_luminance`. This
     * maximum luminance value is used when calculating `window_get_output_max_linear_value`. Note:
     * This maximum luminance may not match the physical behavior of the screen, but will always be
     * proportionally correct relative to `window_get_hdr_output_current_reference_luminance`.
     *
     * Generated from Godot docs: DisplayServer.window_get_hdr_output_current_max_luminance
     */
    @JvmStatic
    fun windowGetHdrOutputCurrentMaxLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputCurrentMaxLuminanceBind, singleton, windowId)
    }

    /**
     * Returns the maximum value for linear color components that can be displayed for the window
     * specified by `window_id`, regardless of SDR or HDR output. Returns `1.0` if HDR is not enabled
     * or not supported. When HDR output is enabled, this is calculated based on
     * `window_get_hdr_output_current_reference_luminance` and
     * `window_get_hdr_output_current_max_luminance`. The `Window.output_max_linear_value_changed`
     * signal will be emitted whenever this value changes. This value is used by tonemapping and other
     * `Environment` effects to ensure that bright colors are presented in the range that can be
     * displayed by this window. Corresponds to `Window.get_output_max_linear_value`.
     *
     * Generated from Godot docs: DisplayServer.window_get_output_max_linear_value
     */
    @JvmStatic
    fun windowGetOutputMaxLinearValue(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetOutputMaxLinearValueBind, singleton, windowId)
    }

    /**
     * Returns `true` if the given window can be maximized (the maximize button is enabled).
     *
     * Generated from Godot docs: DisplayServer.window_is_maximize_allowed
     */
    @JvmStatic
    fun windowIsMaximizeAllowed(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsMaximizeAllowedBind, singleton, windowId)
    }

    /**
     * Returns `true` if double-clicking on a window's title should maximize it. Note: This method is
     * implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.window_maximize_on_title_dbl_click
     */
    @JvmStatic
    fun windowMaximizeOnTitleDblClick(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(windowMaximizeOnTitleDblClickBind, singleton)
    }

    /**
     * Returns `true` if double-clicking on a window's title should minimize it. Note: This method is
     * implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.window_minimize_on_title_dbl_click
     */
    @JvmStatic
    fun windowMinimizeOnTitleDblClick(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(windowMinimizeOnTitleDblClickBind, singleton)
    }

    /**
     * Starts an interactive drag operation on the window with the given `window_id`, using the current
     * mouse position. Call this method when handling a mouse button being pressed to simulate a
     * pressed event on the window's title bar. Using this method allows the window to participate in
     * space switching, tiling, and other system features. Note: This method is implemented on Linux
     * (X11/Wayland), macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_start_drag
     */
    @JvmStatic
    fun windowStartDrag(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(windowStartDragBind, singleton, windowId)
    }

    /**
     * Starts an interactive resize operation on the window with the given `window_id`, using the
     * current mouse position. Call this method when handling a mouse button being pressed to simulate
     * a pressed event on the window's edge. Note: This method is implemented on Linux (X11/Wayland),
     * macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.window_start_resize
     */
    @JvmStatic
    fun windowStartResize(edge: DisplayServer.WindowResizeEdge, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowStartResizeBind, singleton, edge.value, windowId)
    }

    /**
     * Sets the background color of the root window. Note: This method is implemented only on Android.
     *
     * Generated from Godot docs: DisplayServer.window_set_color
     */
    @JvmStatic
    fun windowSetColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(windowSetColorBind, singleton, color)
    }

    /**
     * Returns `1` if a high-contrast user interface theme should be used, `0` otherwise. Returns `-1`
     * if status is unknown. Note: This method is implemented on Linux (X11/Wayland, GNOME), macOS, and
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.accessibility_should_increase_contrast
     */
    @JvmStatic
    fun accessibilityShouldIncreaseContrast(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityShouldIncreaseContrastBind, singleton)
    }

    /**
     * Returns `1` if flashing, blinking, and other moving content that can cause seizures in users
     * with photosensitive epilepsy should be disabled, `0` otherwise. Returns `-1` if status is
     * unknown. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.accessibility_should_reduce_animation
     */
    @JvmStatic
    fun accessibilityShouldReduceAnimation(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityShouldReduceAnimationBind, singleton)
    }

    /**
     * Returns `1` if background images, transparency, and other features that can reduce the contrast
     * between the foreground and background should be disabled, `0` otherwise. Returns `-1` if status
     * is unknown. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.accessibility_should_reduce_transparency
     */
    @JvmStatic
    fun accessibilityShouldReduceTransparency(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityShouldReduceTransparencyBind, singleton)
    }

    /**
     * Returns `1` if a screen reader, Braille display or other assistive app is active, `0` otherwise.
     * Returns `-1` if status is unknown. Note: This method is implemented on Linux, macOS, and
     * Windows. Note: Accessibility debugging tools, such as Accessibility Insights for Windows,
     * Accessibility Inspector (macOS), or AT-SPI Browser (Linux/BSD), do not count as assistive apps
     * and will not affect this value. To test your project with these tools, set
     * `ProjectSettings.accessibility/general/accessibility_support` to `1`.
     *
     * Generated from Godot docs: DisplayServer.accessibility_screen_reader_active
     */
    @JvmStatic
    fun accessibilityScreenReaderActive(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityScreenReaderActiveBind, singleton)
    }

    /**
     * Creates a new, empty accessibility element resource. Note: An accessibility element is created
     * and freed automatically for each `Node`. In general, this function should not be called
     * manually.
     *
     * Generated from Godot docs: DisplayServer.accessibility_create_element
     */
    @JvmStatic
    fun accessibilityCreateElement(windowId: Int, role: DisplayServer.AccessibilityRole): RID {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetRID(accessibilityCreateElementBind, singleton, windowId, role.value)
    }

    /**
     * Creates a new, empty accessibility sub-element resource. Sub-elements can be used to provide
     * accessibility information for objects which are not `Node`s, such as list items, table cells, or
     * menu items. Sub-elements are freed automatically when the parent element is freed, or can be
     * freed early using the `accessibility_free_element` method.
     *
     * Generated from Godot docs: DisplayServer.accessibility_create_sub_element
     */
    @JvmStatic
    fun accessibilityCreateSubElement(parentRid: RID, role: DisplayServer.AccessibilityRole, insertPos: Int = -1): RID {
        return ObjectCalls.ptrcallWithRIDLongIntArgsRetRID(accessibilityCreateSubElementBind, singleton, parentRid, role.value, insertPos)
    }

    /**
     * Creates a new, empty accessibility sub-element from the shaped text buffer. Sub-elements are
     * freed automatically when the parent element is freed, or can be freed early using the
     * `accessibility_free_element` method. If `is_last_line` is `true`, no trailing newline is
     * appended to the text content. Set to `true` for the last line in multi-line text fields and for
     * single-line text fields.
     *
     * Generated from Godot docs: DisplayServer.accessibility_create_sub_text_edit_elements
     */
    @JvmStatic
    fun accessibilityCreateSubTextEditElements(parentRid: RID, shapedText: RID, minHeight: Double, insertPos: Int = -1, isLastLine: Boolean = false): RID {
        return ObjectCalls.ptrcallWithTwoRIDDoubleIntBoolArgsRetRID(accessibilityCreateSubTextEditElementsBind, singleton, parentRid, shapedText, minHeight, insertPos, isLastLine)
    }

    /**
     * Returns `true` if `id` is a valid accessibility element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_has_element
     */
    @JvmStatic
    fun accessibilityHasElement(id: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(accessibilityHasElementBind, singleton, id)
    }

    /**
     * Frees the accessibility element `id` created by `accessibility_create_element`,
     * `accessibility_create_sub_element`, or `accessibility_create_sub_text_edit_elements`.
     *
     * Generated from Godot docs: DisplayServer.accessibility_free_element
     */
    @JvmStatic
    fun accessibilityFreeElement(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(accessibilityFreeElementBind, singleton, id)
    }

    /**
     * Sets the metadata of the accessibility element `id` to `meta`.
     *
     * Generated from Godot docs: DisplayServer.accessibility_element_set_meta
     */
    @JvmStatic
    fun accessibilityElementSetMeta(id: RID, meta: Any?) {
        ObjectCalls.ptrcallWithRIDAndVariantArg(accessibilityElementSetMetaBind, singleton, id, meta)
    }

    /**
     * Returns the metadata of the accessibility element `id`.
     *
     * Generated from Godot docs: DisplayServer.accessibility_element_get_meta
     */
    @JvmStatic
    fun accessibilityElementGetMeta(id: RID): Any? {
        return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(accessibilityElementGetMetaBind, singleton, id)
    }

    /**
     * Sets window outer (with decorations) and inner (without decorations) bounds for assistive apps.
     * Note: This method is implemented on Linux, macOS, and Windows. Note: Advanced users only!
     * `Window` objects call this method automatically.
     *
     * Generated from Godot docs: DisplayServer.accessibility_set_window_rect
     */
    @JvmStatic
    fun accessibilitySetWindowRect(windowId: Int, rectOut: Rect2, rectIn: Rect2) {
        ObjectCalls.ptrcallWithIntRect2Rect2Args(accessibilitySetWindowRectBind, singleton, windowId, rectOut, rectIn)
    }

    /**
     * Sets the window focused state for assistive apps. Note: This method is implemented on Linux,
     * macOS, and Windows. Note: Advanced users only! `Window` objects call this method automatically.
     *
     * Generated from Godot docs: DisplayServer.accessibility_set_window_focused
     */
    @JvmStatic
    fun accessibilitySetWindowFocused(windowId: Int, focused: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(accessibilitySetWindowFocusedBind, singleton, windowId, focused)
    }

    /**
     * Sets currently focused element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_focus
     */
    @JvmStatic
    fun accessibilityUpdateSetFocus(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(accessibilityUpdateSetFocusBind, singleton, id)
    }

    /**
     * Returns the main accessibility element of the OS native window.
     *
     * Generated from Godot docs: DisplayServer.accessibility_get_window_root
     */
    @JvmStatic
    fun accessibilityGetWindowRoot(windowId: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(accessibilityGetWindowRootBind, singleton, windowId)
    }

    /**
     * Sets element accessibility role.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_role
     */
    @JvmStatic
    fun accessibilityUpdateSetRole(id: RID, role: DisplayServer.AccessibilityRole) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetRoleBind, singleton, id, role.value)
    }

    /**
     * Sets element accessibility name.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_name
     */
    @JvmStatic
    fun accessibilityUpdateSetName(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetNameBind, singleton, id, name)
    }

    /**
     * Sets element accessibility extra information added to the element name.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_extra_info
     */
    @JvmStatic
    fun accessibilityUpdateSetExtraInfo(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetExtraInfoBind, singleton, id, name)
    }

    /**
     * Sets element accessibility description.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_description
     */
    @JvmStatic
    fun accessibilityUpdateSetDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetDescriptionBind, singleton, id, description)
    }

    /**
     * Sets element text value.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_value
     */
    @JvmStatic
    fun accessibilityUpdateSetValue(id: RID, value: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetValueBind, singleton, id, value)
    }

    /**
     * Sets tooltip text.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_tooltip
     */
    @JvmStatic
    fun accessibilityUpdateSetTooltip(id: RID, tooltip: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetTooltipBind, singleton, id, tooltip)
    }

    /**
     * Sets element bounding box, relative to the node position.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_bounds
     */
    @JvmStatic
    fun accessibilityUpdateSetBounds(id: RID, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDAndRect2Arg(accessibilityUpdateSetBoundsBind, singleton, id, rect)
    }

    /**
     * Sets element 2D transform.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_transform
     */
    @JvmStatic
    fun accessibilityUpdateSetTransform(id: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(accessibilityUpdateSetTransformBind, singleton, id, transform)
    }

    /**
     * Adds a child accessibility element. Note: `Node` children and sub-elements are added to the
     * child list automatically.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_child
     */
    @JvmStatic
    fun accessibilityUpdateAddChild(id: RID, childId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddChildBind, singleton, id, childId)
    }

    /**
     * Adds an element that is controlled by this element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_related_controls
     */
    @JvmStatic
    fun accessibilityUpdateAddRelatedControls(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedControlsBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that details this element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_related_details
     */
    @JvmStatic
    fun accessibilityUpdateAddRelatedDetails(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedDetailsBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that describes this element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_related_described_by
     */
    @JvmStatic
    fun accessibilityUpdateAddRelatedDescribedBy(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedDescribedByBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that this element flow into.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_related_flow_to
     */
    @JvmStatic
    fun accessibilityUpdateAddRelatedFlowTo(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedFlowToBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that labels this element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_related_labeled_by
     */
    @JvmStatic
    fun accessibilityUpdateAddRelatedLabeledBy(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedLabeledByBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that is part of the same radio group. Note: This method should be called on each
     * element of the group, using all other elements as `related_id`.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_related_radio_group
     */
    @JvmStatic
    fun accessibilityUpdateAddRelatedRadioGroup(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedRadioGroupBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that is an active descendant of this element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_active_descendant
     */
    @JvmStatic
    fun accessibilityUpdateSetActiveDescendant(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetActiveDescendantBind, singleton, id, otherId)
    }

    /**
     * Sets next element on the line.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_next_on_line
     */
    @JvmStatic
    fun accessibilityUpdateSetNextOnLine(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetNextOnLineBind, singleton, id, otherId)
    }

    /**
     * Sets previous element on the line.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_previous_on_line
     */
    @JvmStatic
    fun accessibilityUpdateSetPreviousOnLine(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetPreviousOnLineBind, singleton, id, otherId)
    }

    /**
     * Sets the element to be a member of the group.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_member_of
     */
    @JvmStatic
    fun accessibilityUpdateSetMemberOf(id: RID, groupId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetMemberOfBind, singleton, id, groupId)
    }

    /**
     * Sets target element for the link.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_in_page_link_target
     */
    @JvmStatic
    fun accessibilityUpdateSetInPageLinkTarget(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetInPageLinkTargetBind, singleton, id, otherId)
    }

    /**
     * Sets an element which contains an error message for this element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_error_message
     */
    @JvmStatic
    fun accessibilityUpdateSetErrorMessage(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetErrorMessageBind, singleton, id, otherId)
    }

    /**
     * Sets the priority of the live region updates.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_live
     */
    @JvmStatic
    fun accessibilityUpdateSetLive(id: RID, live: DisplayServer.AccessibilityLiveMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetLiveBind, singleton, id, live.value)
    }

    /**
     * Adds a callback for the accessibility action (action which can be performed by using a special
     * screen reader command or buttons on the Braille display), and marks this action as supported.
     * The action callback receives one `Variant` argument, which value depends on action type.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_action
     */
    @JvmStatic
    fun accessibilityUpdateAddAction(id: RID, action: DisplayServer.AccessibilityAction, callable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(accessibilityUpdateAddActionBind, singleton, id, action.value, callable.target.segment, callable.method)
    }

    /**
     * Adds support for a custom accessibility action. `action_id` is passed as an argument to the
     * callback of `ACTION_CUSTOM` action.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_add_custom_action
     */
    @JvmStatic
    fun accessibilityUpdateAddCustomAction(id: RID, actionId: Int, actionDescription: String) {
        ObjectCalls.ptrcallWithRIDIntAndStringArgs(accessibilityUpdateAddCustomActionBind, singleton, id, actionId, actionDescription)
    }

    /**
     * Sets number of rows in the table.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_table_row_count
     */
    @JvmStatic
    fun accessibilityUpdateSetTableRowCount(id: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableRowCountBind, singleton, id, count)
    }

    /**
     * Sets number of columns in the table.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_table_column_count
     */
    @JvmStatic
    fun accessibilityUpdateSetTableColumnCount(id: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableColumnCountBind, singleton, id, count)
    }

    /**
     * Sets position of the row in the table.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_table_row_index
     */
    @JvmStatic
    fun accessibilityUpdateSetTableRowIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableRowIndexBind, singleton, id, index)
    }

    /**
     * Sets position of the column.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_table_column_index
     */
    @JvmStatic
    fun accessibilityUpdateSetTableColumnIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableColumnIndexBind, singleton, id, index)
    }

    /**
     * Sets cell position in the table.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_table_cell_position
     */
    @JvmStatic
    fun accessibilityUpdateSetTableCellPosition(id: RID, rowIndex: Int, columnIndex: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(accessibilityUpdateSetTableCellPositionBind, singleton, id, rowIndex, columnIndex)
    }

    /**
     * Sets cell row/column span.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_table_cell_span
     */
    @JvmStatic
    fun accessibilityUpdateSetTableCellSpan(id: RID, rowSpan: Int, columnSpan: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(accessibilityUpdateSetTableCellSpanBind, singleton, id, rowSpan, columnSpan)
    }

    /**
     * Sets number of items in the list.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_list_item_count
     */
    @JvmStatic
    fun accessibilityUpdateSetListItemCount(id: RID, size: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetListItemCountBind, singleton, id, size)
    }

    /**
     * Sets the position of the element in the list.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_list_item_index
     */
    @JvmStatic
    fun accessibilityUpdateSetListItemIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetListItemIndexBind, singleton, id, index)
    }

    /**
     * Sets the hierarchical level of the element in the list.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_list_item_level
     */
    @JvmStatic
    fun accessibilityUpdateSetListItemLevel(id: RID, level: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetListItemLevelBind, singleton, id, level)
    }

    /**
     * Sets list/tree item selected status.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_list_item_selected
     */
    @JvmStatic
    fun accessibilityUpdateSetListItemSelected(id: RID, selected: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetListItemSelectedBind, singleton, id, selected)
    }

    /**
     * Sets list/tree item expanded status.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_list_item_expanded
     */
    @JvmStatic
    fun accessibilityUpdateSetListItemExpanded(id: RID, expanded: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetListItemExpandedBind, singleton, id, expanded)
    }

    /**
     * Sets popup type for popup buttons.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_popup_type
     */
    @JvmStatic
    fun accessibilityUpdateSetPopupType(id: RID, popup: DisplayServer.AccessibilityPopupType) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetPopupTypeBind, singleton, id, popup.value)
    }

    /**
     * Sets element checked state.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_checked
     */
    @JvmStatic
    fun accessibilityUpdateSetChecked(id: RID, checekd: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetCheckedBind, singleton, id, checekd)
    }

    /**
     * Sets numeric value.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_num_value
     */
    @JvmStatic
    fun accessibilityUpdateSetNumValue(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetNumValueBind, singleton, id, position)
    }

    /**
     * Sets numeric value range.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_num_range
     */
    @JvmStatic
    fun accessibilityUpdateSetNumRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(accessibilityUpdateSetNumRangeBind, singleton, id, min, max)
    }

    /**
     * Sets numeric value step.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_num_step
     */
    @JvmStatic
    fun accessibilityUpdateSetNumStep(id: RID, step: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetNumStepBind, singleton, id, step)
    }

    /**
     * Sets numeric value jump.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_num_jump
     */
    @JvmStatic
    fun accessibilityUpdateSetNumJump(id: RID, jump: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetNumJumpBind, singleton, id, jump)
    }

    /**
     * Sets scroll bar x position.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_scroll_x
     */
    @JvmStatic
    fun accessibilityUpdateSetScrollX(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetScrollXBind, singleton, id, position)
    }

    /**
     * Sets scroll bar x range.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_scroll_x_range
     */
    @JvmStatic
    fun accessibilityUpdateSetScrollXRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(accessibilityUpdateSetScrollXRangeBind, singleton, id, min, max)
    }

    /**
     * Sets scroll bar y position.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_scroll_y
     */
    @JvmStatic
    fun accessibilityUpdateSetScrollY(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetScrollYBind, singleton, id, position)
    }

    /**
     * Sets scroll bar y range.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_scroll_y_range
     */
    @JvmStatic
    fun accessibilityUpdateSetScrollYRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(accessibilityUpdateSetScrollYRangeBind, singleton, id, min, max)
    }

    /**
     * Sets text underline/overline/strikethrough.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_text_decorations
     */
    @JvmStatic
    fun accessibilityUpdateSetTextDecorations(id: RID, underline: Boolean, strikethrough: Boolean, overline: Boolean) {
        ObjectCalls.ptrcallWithRIDAndThreeBoolArgs(accessibilityUpdateSetTextDecorationsBind, singleton, id, underline, strikethrough, overline)
    }

    /**
     * Sets element text alignment.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_text_align
     */
    @JvmStatic
    fun accessibilityUpdateSetTextAlign(id: RID, align: HorizontalAlignment) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetTextAlignBind, singleton, id, align.value)
    }

    /**
     * Sets text selection to the text field. `text_start_id` and `text_end_id` should be elements
     * created by `accessibility_create_sub_text_edit_elements`. Character offsets are relative to the
     * corresponding element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_text_selection
     */
    @JvmStatic
    fun accessibilityUpdateSetTextSelection(id: RID, textStartId: RID, startChar: Int, textEndId: RID, endChar: Int) {
        ObjectCalls.ptrcallWithTwoRIDIntRIDIntArgs(accessibilityUpdateSetTextSelectionBind, singleton, id, textStartId, startChar, textEndId, endChar)
    }

    /**
     * Sets element flag.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_flag
     */
    @JvmStatic
    fun accessibilityUpdateSetFlag(id: RID, flag: DisplayServer.AccessibilityFlags, value: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(accessibilityUpdateSetFlagBind, singleton, id, flag.value, value)
    }

    /**
     * Sets element class name.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_classname
     */
    @JvmStatic
    fun accessibilityUpdateSetClassname(id: RID, classname: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetClassnameBind, singleton, id, classname)
    }

    /**
     * Sets placeholder text.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_placeholder
     */
    @JvmStatic
    fun accessibilityUpdateSetPlaceholder(id: RID, placeholder: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetPlaceholderBind, singleton, id, placeholder)
    }

    /**
     * Sets element text language.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_language
     */
    @JvmStatic
    fun accessibilityUpdateSetLanguage(id: RID, language: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetLanguageBind, singleton, id, language)
    }

    /**
     * Sets text orientation.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_text_orientation
     */
    @JvmStatic
    fun accessibilityUpdateSetTextOrientation(id: RID, vertical: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetTextOrientationBind, singleton, id, vertical)
    }

    /**
     * Sets the orientation of the list elements.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_list_orientation
     */
    @JvmStatic
    fun accessibilityUpdateSetListOrientation(id: RID, vertical: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetListOrientationBind, singleton, id, vertical)
    }

    /**
     * Sets the list of keyboard shortcuts used by element.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_shortcut
     */
    @JvmStatic
    fun accessibilityUpdateSetShortcut(id: RID, shortcut: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetShortcutBind, singleton, id, shortcut)
    }

    /**
     * Sets link URL.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_url
     */
    @JvmStatic
    fun accessibilityUpdateSetUrl(id: RID, url: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetUrlBind, singleton, id, url)
    }

    /**
     * Sets element accessibility role description text.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_role_description
     */
    @JvmStatic
    fun accessibilityUpdateSetRoleDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetRoleDescriptionBind, singleton, id, description)
    }

    /**
     * Sets human-readable description of the current checked state.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_state_description
     */
    @JvmStatic
    fun accessibilityUpdateSetStateDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetStateDescriptionBind, singleton, id, description)
    }

    /**
     * Sets element color value.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_color_value
     */
    @JvmStatic
    fun accessibilityUpdateSetColorValue(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(accessibilityUpdateSetColorValueBind, singleton, id, color)
    }

    /**
     * Sets element background color.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_background_color
     */
    @JvmStatic
    fun accessibilityUpdateSetBackgroundColor(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(accessibilityUpdateSetBackgroundColorBind, singleton, id, color)
    }

    /**
     * Sets element foreground color.
     *
     * Generated from Godot docs: DisplayServer.accessibility_update_set_foreground_color
     */
    @JvmStatic
    fun accessibilityUpdateSetForegroundColor(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(accessibilityUpdateSetForegroundColorBind, singleton, id, color)
    }

    /**
     * Returns the text selection in the Input Method Editor
     * (https://en.wikipedia.org/wiki/Input_method) composition string, with the `Vector2i`'s `x`
     * component being the caret position and `y` being the length of the selection. Note: This method
     * is implemented only on macOS.
     *
     * Generated from Godot docs: DisplayServer.ime_get_selection
     */
    @JvmStatic
    fun imeGetSelection(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(imeGetSelectionBind, singleton)
    }

    /**
     * Returns the composition string contained within the Input Method Editor
     * (https://en.wikipedia.org/wiki/Input_method) window. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: DisplayServer.ime_get_text
     */
    @JvmStatic
    fun imeGetText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(imeGetTextBind, singleton)
    }

    /**
     * Shows the virtual keyboard if the platform has one. `existing_text` parameter is useful for
     * implementing your own `LineEdit` or `TextEdit`, as it tells the virtual keyboard what text has
     * already been typed (the virtual keyboard uses it for auto-correct and predictions). `position`
     * parameter is the screen space `Rect2` of the edited text. `type` parameter allows configuring
     * which type of virtual keyboard to show. `max_length` limits the number of characters that can be
     * entered if different from `-1`. `cursor_start` can optionally define the current text cursor
     * position if `cursor_end` is not set. `cursor_start` and `cursor_end` can optionally define the
     * current text selection. Note: This method is implemented on Android, iOS and Web.
     *
     * Generated from Godot docs: DisplayServer.virtual_keyboard_show
     */
    @JvmStatic
    fun virtualKeyboardShow(existingText: String, position: Rect2, type: DisplayServer.VirtualKeyboardType = DisplayServer.VirtualKeyboardType.DEFAULT, maxLength: Int = -1, cursorStart: Int = -1, cursorEnd: Int = -1) {
        ObjectCalls.ptrcallWithStringRect2LongThreeIntArgs(virtualKeyboardShowBind, singleton, existingText, position, type.value, maxLength, cursorStart, cursorEnd)
    }

    /**
     * Hides the virtual keyboard if it is shown, does nothing otherwise.
     *
     * Generated from Godot docs: DisplayServer.virtual_keyboard_hide
     */
    @JvmStatic
    fun virtualKeyboardHide() {
        ObjectCalls.ptrcallNoArgs(virtualKeyboardHideBind, singleton)
    }

    /**
     * Returns the on-screen keyboard's height in pixels. Returns `0` if there is no keyboard or if it
     * is currently hidden. Note: On Android 7 and 8, the keyboard height may return `0` the first time
     * the keyboard is opened in non-immersive mode. This behavior does not occur in immersive mode.
     *
     * Generated from Godot docs: DisplayServer.virtual_keyboard_get_height
     */
    @JvmStatic
    fun virtualKeyboardGetHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(virtualKeyboardGetHeightBind, singleton)
    }

    /**
     * Returns `true` if a hardware keyboard is connected. Note: This method is implemented on Android
     * and iOS. On other platforms, this method always returns `true`.
     *
     * Generated from Godot docs: DisplayServer.has_hardware_keyboard
     */
    @JvmStatic
    fun hasHardwareKeyboard(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasHardwareKeyboardBind, singleton)
    }

    /**
     * Sets the callback that should be called when a hardware keyboard is connected or disconnected.
     * `callable` should accept a single `bool` argument indicating whether the keyboard has been
     * connected (`true`) or disconnected (`false`). Note: This method is only implemented on Android.
     *
     * Generated from Godot docs: DisplayServer.set_hardware_keyboard_connection_change_callback
     */
    @JvmStatic
    fun setHardwareKeyboardConnectionChangeCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setHardwareKeyboardConnectionChangeCallbackBind, singleton, callable.target.segment, callable.method)
    }

    /**
     * Sets the default mouse cursor shape. The cursor's appearance will vary depending on the user's
     * operating system and mouse cursor theme. See also `cursor_get_shape` and
     * `cursor_set_custom_image`.
     *
     * Generated from Godot docs: DisplayServer.cursor_set_shape
     */
    @JvmStatic
    fun cursorSetShape(shape: DisplayServer.CursorShape) {
        ObjectCalls.ptrcallWithLongArg(cursorSetShapeBind, singleton, shape.value)
    }

    /**
     * Returns the default mouse cursor shape set by `cursor_set_shape`.
     *
     * Generated from Godot docs: DisplayServer.cursor_get_shape
     */
    @JvmStatic
    fun cursorGetShape(): DisplayServer.CursorShape {
        return DisplayServer.CursorShape(ObjectCalls.ptrcallNoArgsRetLong(cursorGetShapeBind, singleton))
    }

    /**
     * Sets a custom mouse cursor image for the given `shape`. This means the user's operating system
     * and mouse cursor theme will no longer influence the mouse cursor's appearance. `cursor` can be
     * either a `Texture2D` or an `Image`, and it should not be larger than 256×256 to display
     * correctly. Optionally, `hotspot` can be set to offset the image's position relative to the click
     * point. By default, `hotspot` is set to the top-left corner of the image. See also
     * `cursor_set_shape`. Note: On Web, calling this method every frame can cause the cursor to
     * flicker.
     *
     * Generated from Godot docs: DisplayServer.cursor_set_custom_image
     */
    @JvmStatic
    fun cursorSetCustomImage(cursor: Resource?, shape: DisplayServer.CursorShape = DisplayServer.CursorShape.ARROW, hotspot: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithObjectLongAndVector2Arg(cursorSetCustomImageBind, singleton, cursor?.requireOpenHandle() ?: NULL_SEGMENT, shape.value, hotspot)
    }

    /**
     * Returns `true` if positions of OK and Cancel buttons are swapped in dialogs. This is enabled by
     * default on Windows to follow interface conventions, and be toggled by changing
     * `ProjectSettings.gui/common/swap_cancel_ok`. Note: This doesn't affect native dialogs such as
     * the ones spawned by `DisplayServer.dialog_show`.
     *
     * Generated from Godot docs: DisplayServer.get_swap_cancel_ok
     */
    @JvmStatic
    fun getSwapCancelOk(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSwapCancelOkBind, singleton)
    }

    /**
     * Allows the `process_id` PID to steal focus from this window. In other words, this disables the
     * operating system's focus stealing protection for the specified PID. Note: This method is
     * implemented only on Windows.
     *
     * Generated from Godot docs: DisplayServer.enable_for_stealing_focus
     */
    @JvmStatic
    fun enableForStealingFocus(processId: Long) {
        ObjectCalls.ptrcallWithLongArg(enableForStealingFocusBind, singleton, processId)
    }

    /**
     * Shows a text dialog which uses the operating system's native look-and-feel. `callback` should
     * accept a single `int` parameter which corresponds to the index of the pressed button. Note: This
     * method is implemented if the display server has the `FEATURE_NATIVE_DIALOG` feature. Supported
     * platforms include macOS, Windows, and Android.
     *
     * Generated from Godot docs: DisplayServer.dialog_show
     */
    @JvmStatic
    fun dialogShow(title: String, description: String, buttons: List<String>, callback: GodotCallable): GodotError {
        return GodotError(ObjectCalls.ptrcallWithTwoStringPackedStringListCallableArgsRetLong(dialogShowBind, singleton, title, description, buttons, callback.target.segment, callback.method))
    }

    /**
     * Shows a text input dialog which uses the operating system's native look-and-feel. `callback`
     * should accept a single `String` parameter which contains the text field's contents. Note: This
     * method is implemented if the display server has the `FEATURE_NATIVE_DIALOG_INPUT` feature.
     * Supported platforms include macOS, Windows, and Android.
     *
     * Generated from Godot docs: DisplayServer.dialog_input_text
     */
    @JvmStatic
    fun dialogInputText(title: String, description: String, existingText: String, callback: GodotCallable): GodotError {
        return GodotError(ObjectCalls.ptrcallWithThreeStringCallableArgsRetLong(dialogInputTextBind, singleton, title, description, existingText, callback.target.segment, callback.method))
    }

    /**
     * Displays OS native dialog for selecting files or directories in the file system. Each filter
     * string in the `filters` array should be formatted like this: `*.png,*.jpg,*.jpeg;Image
     * Files;image/png,image/jpeg`. The description text of the filter is optional and can be omitted.
     * It is recommended to set both file extension and MIME type. See also `FileDialog.filters`.
     * Callbacks have the following arguments: `status: bool, selected_paths: PackedStringArray,
     * selected_filter_index: int`. On Android, the third callback argument (`selected_filter_index`)
     * is always `0`. Note: This method is implemented if the display server has the
     * `FEATURE_NATIVE_DIALOG_FILE` feature. Supported platforms include Linux (X11/Wayland), Windows,
     * macOS, and Android. Note: `current_directory` might be ignored. Note: Embedded file dialogs and
     * Windows file dialogs support only file extensions, while Android, Linux, and macOS file dialogs
     * also support MIME types. Note: On Android and Linux, `show_hidden` is ignored. Note: On Android
     * and macOS, native file dialogs have no title. Note: On macOS, sandboxed apps will save
     * security-scoped bookmarks to retain access to the opened folders across multiple sessions. Use
     * `OS.get_granted_permissions` to get a list of saved bookmarks. Note: On Android, this method
     * uses the Android Storage Access Framework (SAF). The file picker returns a URI instead of a
     * filesystem path. This URI can be passed directly to `FileAccess` to perform read/write
     * operations. When using `FILE_DIALOG_MODE_OPEN_DIR`, it returns a tree URI that grants full
     * access to the selected directory. File operations inside this directory can be performed by
     * passing a path on the form `treeUri#relative/path/to/file` to `FileAccess`. To avoid opening the
     * file picker again after each app restart, you can take persistable URI permission as follows:
     *
     * Generated from Godot docs: DisplayServer.file_dialog_show
     */
    @JvmStatic
    fun fileDialogShow(title: String, currentDirectory: String, filename: String, showHidden: Boolean, mode: DisplayServer.FileDialogMode, filters: List<String>, callback: GodotCallable, parentWindowId: Int = 0): GodotError {
        return GodotError(ObjectCalls.ptrcallWithThreeStringBoolLongPackedStringListCallableIntArgsRetLong(fileDialogShowBind, singleton, title, currentDirectory, filename, showHidden, mode.value, filters, callback.target.segment, callback.method, parentWindowId))
    }

    /**
     * Displays OS native dialog for selecting files or directories in the file system with additional
     * user selectable options. Each filter string in the `filters` array should be formatted like
     * this: `*.png,*.jpg,*.jpeg;Image Files;image/png,image/jpeg`. The description text of the filter
     * is optional and can be omitted. It is recommended to set both file extension and MIME type. See
     * also `FileDialog.filters`. `options` is array of `Dictionary`s with the following keys: -
     * `"name"` - option's name `String`. - `"values"` - `PackedStringArray` of values. If empty,
     * boolean option (check box) is used. - `"default"` - default selected option index (`int`) or
     * default boolean value (`bool`). Callbacks have the following arguments: `status: bool,
     * selected_paths: PackedStringArray, selected_filter_index: int, selected_option: Dictionary`.
     * Note: This method is implemented if the display server has the
     * `FEATURE_NATIVE_DIALOG_FILE_EXTRA` feature. Supported platforms include Linux (X11/Wayland),
     * Windows, and macOS. Note: `current_directory` might be ignored. Note: Embedded file dialogs and
     * Windows file dialogs support only file extensions, while Android, Linux, and macOS file dialogs
     * also support MIME types. Note: On Linux (X11), `show_hidden` is ignored. Note: On macOS, native
     * file dialogs have no title. Note: On macOS, sandboxed apps will save security-scoped bookmarks
     * to retain access to the opened folders across multiple sessions. Use
     * `OS.get_granted_permissions` to get a list of saved bookmarks.
     *
     * Generated from Godot docs: DisplayServer.file_dialog_with_options_show
     */
    @JvmStatic
    fun fileDialogWithOptionsShow(title: String, currentDirectory: String, root: String, filename: String, showHidden: Boolean, mode: DisplayServer.FileDialogMode, filters: List<String>, options: List<Map<String, Any?>>, callback: GodotCallable, parentWindowId: Int = 0): GodotError {
        return GodotError(ObjectCalls.ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong(fileDialogWithOptionsShowBind, singleton, title, currentDirectory, root, filename, showHidden, mode.value, filters, options, callback.target.segment, callback.method, parentWindowId))
    }

    /**
     * Plays the beep sound from the operative system, if possible. Because it comes from the OS, the
     * beep sound will be audible even if the application is muted. It may also be disabled for the
     * entire OS by the user. Note: This method is implemented on macOS, Linux (X11/Wayland), and
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.beep
     */
    @JvmStatic
    fun beep() {
        ObjectCalls.ptrcallNoArgs(beepBind, singleton)
    }

    /**
     * Returns the number of keyboard layouts. Note: This method is implemented on Linux (X11/Wayland),
     * macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_get_layout_count
     */
    @JvmStatic
    fun keyboardGetLayoutCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(keyboardGetLayoutCountBind, singleton)
    }

    /**
     * Returns active keyboard layout index. Note: This method is implemented on Linux (X11/Wayland),
     * macOS, and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_get_current_layout
     */
    @JvmStatic
    fun keyboardGetCurrentLayout(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(keyboardGetCurrentLayoutBind, singleton)
    }

    /**
     * Sets the active keyboard layout. Note: This method is implemented on Linux (X11/Wayland), macOS
     * and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_set_current_layout
     */
    @JvmStatic
    fun keyboardSetCurrentLayout(index: Int) {
        ObjectCalls.ptrcallWithIntArg(keyboardSetCurrentLayoutBind, singleton, index)
    }

    /**
     * Returns the ISO-639/BCP-47 language code of the keyboard layout at position `index`. Note: This
     * method is implemented on Linux (X11/Wayland), macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_get_layout_language
     */
    @JvmStatic
    fun keyboardGetLayoutLanguage(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(keyboardGetLayoutLanguageBind, singleton, index)
    }

    /**
     * Returns the localized name of the keyboard layout at position `index`. Note: This method is
     * implemented on Linux (X11/Wayland), macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_get_layout_name
     */
    @JvmStatic
    fun keyboardGetLayoutName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(keyboardGetLayoutNameBind, singleton, index)
    }

    /**
     * Converts a physical (US QWERTY) `keycode` to one in the active keyboard layout. Note: This
     * method is implemented on Linux (X11/Wayland), macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_get_keycode_from_physical
     */
    @JvmStatic
    fun keyboardGetKeycodeFromPhysical(keycode: Key): Key {
        return Key(ObjectCalls.ptrcallWithLongArgRetLong(keyboardGetKeycodeFromPhysicalBind, singleton, keycode.value))
    }

    /**
     * Converts a physical (US QWERTY) `keycode` to localized label printed on the key in the active
     * keyboard layout. Note: This method is implemented on Linux (X11/Wayland), macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.keyboard_get_label_from_physical
     */
    @JvmStatic
    fun keyboardGetLabelFromPhysical(keycode: Key): Key {
        return Key(ObjectCalls.ptrcallWithLongArgRetLong(keyboardGetLabelFromPhysicalBind, singleton, keycode.value))
    }

    /**
     * Opens system emoji and symbol picker. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.show_emoji_and_symbol_picker
     */
    @JvmStatic
    fun showEmojiAndSymbolPicker() {
        ObjectCalls.ptrcallNoArgs(showEmojiAndSymbolPickerBind, singleton)
    }

    /**
     * Displays OS native color picker. Callbacks have the following arguments: `status: bool, color:
     * Color`. Note: This method is implemented if the display server has the
     * `FEATURE_NATIVE_COLOR_PICKER` feature. Note: This method is only implemented on Linux
     * (X11/Wayland).
     *
     * Generated from Godot docs: DisplayServer.color_picker
     */
    @JvmStatic
    fun colorPicker(callback: GodotCallable): Boolean {
        return ObjectCalls.ptrcallWithCallableArgRetBool(colorPickerBind, singleton, callback.target.segment, callback.method)
    }

    /**
     * Perform window manager processing, including input flushing. See also
     * `force_process_and_drop_events`, `Input.flush_buffered_events` and
     * `Input.use_accumulated_input`.
     *
     * Generated from Godot docs: DisplayServer.process_events
     */
    @JvmStatic
    fun processEvents() {
        ObjectCalls.ptrcallNoArgs(processEventsBind, singleton)
    }

    /**
     * Forces window manager processing while ignoring all `InputEvent`s. See also `process_events`.
     * Note: This method is implemented on Windows and macOS.
     *
     * Generated from Godot docs: DisplayServer.force_process_and_drop_events
     */
    @JvmStatic
    fun forceProcessAndDropEvents() {
        ObjectCalls.ptrcallNoArgs(forceProcessAndDropEventsBind, singleton)
    }

    /**
     * Sets the window icon (usually displayed in the top-left corner) in the operating system's native
     * format. The file at `filename` must be in `.ico` format on Windows or `.icns` on macOS. By using
     * specially crafted `.ico` or `.icns` icons, `set_native_icon` allows specifying different icons
     * depending on the size the icon is displayed at. This size is determined by the operating system
     * and user preferences (including the display scale factor). To use icons in other formats, use
     * `set_icon` instead. Note: Requires support for `FEATURE_NATIVE_ICON`.
     *
     * Generated from Godot docs: DisplayServer.set_native_icon
     */
    @JvmStatic
    fun setNativeIcon(filename: String) {
        ObjectCalls.ptrcallWithStringArg(setNativeIconBind, singleton, filename)
    }

    /**
     * Sets the application icon and icons of all windows with an `Image`. To use icons in the
     * operating system's native format, use `set_native_icon` instead. Note: Requires support for
     * `FEATURE_ICON`.
     *
     * Generated from Godot docs: DisplayServer.set_icon
     */
    @JvmStatic
    fun setIcon(image: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setIconBind, singleton, listOf(image?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Creates a new application status indicator with the specified icon, tooltip, and activation
     * callback. `callback` should take two arguments: the pressed mouse button (one of the
     * `MouseButton` constants) and the click position in screen coordinates (a `Vector2i`).
     *
     * Generated from Godot docs: DisplayServer.create_status_indicator
     */
    @JvmStatic
    fun createStatusIndicator(icon: Texture2D?, tooltip: String, callback: GodotCallable): Int {
        return ObjectCalls.ptrcallWithObjectStringCallableArgsRetInt(createStatusIndicatorBind, singleton, icon?.requireOpenHandle() ?: NULL_SEGMENT, tooltip, callback.target.segment, callback.method)
    }

    /**
     * Sets the application status indicator icon. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.status_indicator_set_icon
     */
    @JvmStatic
    fun statusIndicatorSetIcon(id: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(statusIndicatorSetIconBind, singleton, id, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Sets the application status indicator tooltip. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.status_indicator_set_tooltip
     */
    @JvmStatic
    fun statusIndicatorSetTooltip(id: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(statusIndicatorSetTooltipBind, singleton, id, tooltip)
    }

    /**
     * Sets the application status indicator native popup menu. Note: On macOS, the menu is activated
     * by any mouse button. Its activation callback is not triggered. Note: On Windows, the menu is
     * activated by the right mouse button, selecting the status icon and pressing Shift + F10, or the
     * applications key. The menu's activation callback for the other mouse buttons is still triggered.
     * Note: Native popup is only supported if `NativeMenu` supports the
     * `NativeMenu.FEATURE_POPUP_MENU` feature.
     *
     * Generated from Godot docs: DisplayServer.status_indicator_set_menu
     */
    @JvmStatic
    fun statusIndicatorSetMenu(id: Int, menuRid: RID) {
        ObjectCalls.ptrcallWithIntAndRIDArg(statusIndicatorSetMenuBind, singleton, id, menuRid)
    }

    /**
     * Sets the application status indicator activation callback. `callback` should take two arguments:
     * `int` mouse button index (one of `MouseButton` values) and `Vector2i` click position in screen
     * coordinates. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: DisplayServer.status_indicator_set_callback
     */
    @JvmStatic
    fun statusIndicatorSetCallback(id: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithIntCallableArgs(statusIndicatorSetCallbackBind, singleton, id, callback.target.segment, callback.method)
    }

    /**
     * Returns the rectangle for the given status indicator `id` in screen coordinates. If the status
     * indicator is not visible, returns an empty `Rect2`. Note: This method is implemented on macOS
     * and Windows.
     *
     * Generated from Godot docs: DisplayServer.status_indicator_get_rect
     */
    @JvmStatic
    fun statusIndicatorGetRect(id: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(statusIndicatorGetRectBind, singleton, id)
    }

    /**
     * Removes the application status indicator.
     *
     * Generated from Godot docs: DisplayServer.delete_status_indicator
     */
    @JvmStatic
    fun deleteStatusIndicator(id: Int) {
        ObjectCalls.ptrcallWithIntArg(deleteStatusIndicatorBind, singleton, id)
    }

    /**
     * Returns the total number of available tablet drivers. Note: This method is implemented only on
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.tablet_get_driver_count
     */
    @JvmStatic
    fun tabletGetDriverCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(tabletGetDriverCountBind, singleton)
    }

    /**
     * Returns the tablet driver name for the given index. Note: This method is implemented only on
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.tablet_get_driver_name
     */
    @JvmStatic
    fun tabletGetDriverName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(tabletGetDriverNameBind, singleton, idx)
    }

    /**
     * Returns current active tablet driver name. Note: This method is implemented only on Windows.
     *
     * Generated from Godot docs: DisplayServer.tablet_get_current_driver
     */
    @JvmStatic
    fun tabletGetCurrentDriver(): String {
        return ObjectCalls.ptrcallNoArgsRetString(tabletGetCurrentDriverBind, singleton)
    }

    /**
     * Set active tablet driver name. Supported drivers: - `winink`: Windows Ink API, default. -
     * `wintab`: Wacom Wintab API (compatible device driver required). - `dummy`: Dummy driver, tablet
     * input is disabled. Note: This method is implemented only on Windows.
     *
     * Generated from Godot docs: DisplayServer.tablet_set_current_driver
     */
    @JvmStatic
    fun tabletSetCurrentDriver(name: String) {
        ObjectCalls.ptrcallWithStringArg(tabletSetCurrentDriverBind, singleton, name)
    }

    /**
     * Returns `true` if the window background can be made transparent. This method returns `false` if
     * `ProjectSettings.display/window/per_pixel_transparency/allowed` is set to `false`, or if
     * transparency is not supported by the renderer or OS compositor.
     *
     * Generated from Godot docs: DisplayServer.is_window_transparency_available
     */
    @JvmStatic
    fun isWindowTransparencyAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isWindowTransparencyAvailableBind, singleton)
    }

    /**
     * Registers an `Object` which represents an additional output that will be rendered too, beyond
     * normal windows. The `Object` is only used as an identifier, which can be later passed to
     * `unregister_additional_output`. This can be used to prevent Godot from skipping rendering when
     * no normal windows are visible.
     *
     * Generated from Godot docs: DisplayServer.register_additional_output
     */
    @JvmStatic
    fun registerAdditionalOutput(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(registerAdditionalOutputBind, singleton, listOf(objectValue.segment))
    }

    /**
     * Unregisters an `Object` representing an additional output, that was registered via
     * `register_additional_output`.
     *
     * Generated from Godot docs: DisplayServer.unregister_additional_output
     */
    @JvmStatic
    fun unregisterAdditionalOutput(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterAdditionalOutputBind, singleton, listOf(objectValue.segment))
    }

    /**
     * Returns `true` if any additional outputs have been registered via `register_additional_output`.
     *
     * Generated from Godot docs: DisplayServer.has_additional_outputs
     */
    @JvmStatic
    fun hasAdditionalOutputs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAdditionalOutputsBind, singleton)
    }

    /**
     * Returns `true` if the application is in picture-in-picture mode. Note: This method is
     * implemented on Android.
     *
     * Generated from Godot docs: DisplayServer.is_in_pip_mode
     */
    @JvmStatic
    fun isInPipMode(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isInPipModeBind, singleton, windowId)
    }

    /**
     * Enters picture-in-picture mode. Note: This method is implemented on Android.
     *
     * Generated from Godot docs: DisplayServer.pip_mode_enter
     */
    @JvmStatic
    fun pipModeEnter(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(pipModeEnterBind, singleton, windowId)
    }

    /**
     * Specifies the aspect ratio for picture-in-picture mode. Note: This method is implemented on
     * Android.
     *
     * Generated from Godot docs: DisplayServer.pip_mode_set_aspect_ratio
     */
    @JvmStatic
    fun pipModeSetAspectRatio(numerator: Int, denominator: Int, windowId: Int = 0) {
        ObjectCalls.ptrcallWithThreeIntArgs(pipModeSetAspectRatioBind, singleton, numerator, denominator, windowId)
    }

    /**
     * Specifies whether picture-in-picture mode should be entered automatically when the application
     * goes in the background. Note: This method is implemented on Android.
     *
     * Generated from Godot docs: DisplayServer.pip_mode_set_auto_enter_on_background
     */
    @JvmStatic
    fun pipModeSetAutoEnterOnBackground(autoEnterOnBackground: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithBoolAndIntArgs(pipModeSetAutoEnterOnBackgroundBind, singleton, autoEnterOnBackground, windowId)
    }

    object Signals {
        const val orientationChanged: String = "orientation_changed"
    }

    @JvmInline
    value class Feature(val value: Long) {
        companion object {
            /**
             * Display server supports global menu. This allows the application to display its menu items in
             * the operating system's top bar. macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_GLOBAL_MENU
             */
            val GLOBAL_MENU: Feature get() = Feature(0L)
            /**
             * Display server supports multiple windows that can be moved outside of the main window. Windows,
             * macOS, Linux (X11)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_SUBWINDOWS
             */
            val SUBWINDOWS: Feature get() = Feature(1L)
            /**
             * Display server supports touchscreen input. Windows, Linux (X11/Wayland), Android, iOS, Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_TOUCHSCREEN
             */
            val TOUCHSCREEN: Feature get() = Feature(2L)
            /**
             * Display server supports mouse input. Windows, macOS, Linux (X11/Wayland), Android, Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_MOUSE
             */
            val MOUSE: Feature get() = Feature(3L)
            /**
             * Display server supports warping mouse coordinates to keep the mouse cursor constrained within an
             * area, but looping when one of the edges is reached. Windows, macOS, Linux (X11/Wayland)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_MOUSE_WARP
             */
            val MOUSE_WARP: Feature get() = Feature(4L)
            /**
             * Display server supports setting and getting clipboard data. See also
             * `FEATURE_CLIPBOARD_PRIMARY`. Windows, macOS, Linux (X11/Wayland), Android, iOS, Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_CLIPBOARD
             */
            val CLIPBOARD: Feature get() = Feature(5L)
            /**
             * Display server supports popping up a virtual keyboard when requested to input text without a
             * physical keyboard. Android, iOS, Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_VIRTUAL_KEYBOARD
             */
            val VIRTUAL_KEYBOARD: Feature get() = Feature(6L)
            /**
             * Display server supports setting the mouse cursor shape to be different from the default.
             * Windows, macOS, Linux (X11/Wayland), Android, Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_CURSOR_SHAPE
             */
            val CURSOR_SHAPE: Feature get() = Feature(7L)
            /**
             * Display server supports setting the mouse cursor shape to a custom image. Windows, macOS, Linux
             * (X11/Wayland), Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_CUSTOM_CURSOR_SHAPE
             */
            val CUSTOM_CURSOR_SHAPE: Feature get() = Feature(8L)
            /**
             * Display server supports spawning text dialogs using the operating system's native look-and-feel.
             * See `dialog_show`. Windows, macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_DIALOG
             */
            val NATIVE_DIALOG: Feature get() = Feature(9L)
            /**
             * Display server supports Input Method Editor (https://en.wikipedia.org/wiki/Input_method), which
             * is commonly used for inputting Chinese/Japanese/Korean text. This is handled by the operating
             * system, rather than by Godot. Windows, macOS, Linux (X11)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_IME
             */
            val IME: Feature get() = Feature(10L)
            /**
             * Display server supports windows can use per-pixel transparency to make windows behind them
             * partially or fully visible. Windows, macOS, Linux (X11/Wayland), Android
             *
             * Generated from Godot docs: DisplayServer.FEATURE_WINDOW_TRANSPARENCY
             */
            val WINDOW_TRANSPARENCY: Feature get() = Feature(11L)
            /**
             * Display server supports querying the operating system's display scale factor. This allows
             * automatically detecting the hiDPI display reliably, instead of guessing based on the screen
             * resolution and the display's reported DPI (which might be unreliable due to broken monitor
             * EDID). Windows, Linux (Wayland), macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_HIDPI
             */
            val HIDPI: Feature get() = Feature(12L)
            /**
             * Display server supports changing the window icon (usually displayed in the top-left corner).
             * Windows, macOS, Linux (X11/Wayland) Note: Use on Wayland requires the compositor to implement
             * the xdg_toplevel_icon_v1
             * (https://wayland.app/protocols/xdg-toplevel-icon-v1#xdg_toplevel_icon_v1) protocol, which not
             * all compositors do. See xdg_toplevel_icon_v1#compositor-support
             * (https://wayland.app/protocols/xdg-toplevel-icon-v1#compositor-support) for more information on
             * individual compositor support.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_ICON
             */
            val ICON: Feature get() = Feature(13L)
            /**
             * Display server supports changing the window icon (usually displayed in the top-left corner).
             * Windows, macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_ICON
             */
            val NATIVE_ICON: Feature get() = Feature(14L)
            /**
             * Display server supports changing the screen orientation. Android, iOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_ORIENTATION
             */
            val ORIENTATION: Feature get() = Feature(15L)
            /**
             * Display server supports V-Sync status can be changed from the default (which is forced to be
             * enabled platforms not supporting this feature). Windows, macOS, Linux (X11/Wayland)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_SWAP_BUFFERS
             */
            val SWAP_BUFFERS: Feature get() = Feature(16L)
            /**
             * Display server supports Primary clipboard can be used. This is a different clipboard from
             * `FEATURE_CLIPBOARD`. Linux (X11/Wayland)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_CLIPBOARD_PRIMARY
             */
            val CLIPBOARD_PRIMARY: Feature get() = Feature(18L)
            /**
             * Display server supports text-to-speech. See `tts_*` methods. Windows, macOS, Linux
             * (X11/Wayland), Android, iOS, Web
             *
             * Generated from Godot docs: DisplayServer.FEATURE_TEXT_TO_SPEECH
             */
            val TEXT_TO_SPEECH: Feature get() = Feature(19L)
            /**
             * Display server supports expanding window content to the title. See
             * `WINDOW_FLAG_EXTEND_TO_TITLE`. macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_EXTEND_TO_TITLE
             */
            val EXTEND_TO_TITLE: Feature get() = Feature(20L)
            /**
             * Display server supports reading screen pixels. See `screen_get_pixel`.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_SCREEN_CAPTURE
             */
            val SCREEN_CAPTURE: Feature get() = Feature(21L)
            /**
             * Display server supports application status indicators.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_STATUS_INDICATOR
             */
            val STATUS_INDICATOR: Feature get() = Feature(22L)
            /**
             * Display server supports native help system search callbacks. See `help_set_search_callbacks`.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_HELP
             */
            val NATIVE_HELP: Feature get() = Feature(23L)
            /**
             * Display server supports spawning text input dialogs using the operating system's native
             * look-and-feel. See `dialog_input_text`. Windows, macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_DIALOG_INPUT
             */
            val NATIVE_DIALOG_INPUT: Feature get() = Feature(24L)
            /**
             * Display server supports spawning dialogs for selecting files or directories using the operating
             * system's native look-and-feel. See `file_dialog_show`. Windows, macOS, Linux (X11/Wayland),
             * Android
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_DIALOG_FILE
             */
            val NATIVE_DIALOG_FILE: Feature get() = Feature(25L)
            /**
             * The display server supports all features of `FEATURE_NATIVE_DIALOG_FILE`, with the added
             * functionality of Options and native dialog file access to `res://` and `user://` paths. See
             * `file_dialog_show` and `file_dialog_with_options_show`. Windows, macOS, Linux (X11/Wayland)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_DIALOG_FILE_EXTRA
             */
            val NATIVE_DIALOG_FILE_EXTRA: Feature get() = Feature(26L)
            /**
             * The display server supports initiating window drag and resize operations on demand. See
             * `window_start_drag` and `window_start_resize`.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_WINDOW_DRAG
             */
            val WINDOW_DRAG: Feature get() = Feature(27L)
            /**
             * Display server supports `WINDOW_FLAG_EXCLUDE_FROM_CAPTURE` window flag. Windows, macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_SCREEN_EXCLUDE_FROM_CAPTURE
             */
            val SCREEN_EXCLUDE_FROM_CAPTURE: Feature get() = Feature(28L)
            /**
             * Display server supports embedding a window from another process. Windows, Linux (X11), macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_WINDOW_EMBEDDING
             */
            val WINDOW_EMBEDDING: Feature get() = Feature(29L)
            /**
             * Native file selection dialog supports MIME types as filters.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_DIALOG_FILE_MIME
             */
            val NATIVE_DIALOG_FILE_MIME: Feature get() = Feature(30L)
            /**
             * Display server supports system emoji and symbol picker. Windows, macOS
             *
             * Generated from Godot docs: DisplayServer.FEATURE_EMOJI_AND_SYMBOL_PICKER
             */
            val EMOJI_AND_SYMBOL_PICKER: Feature get() = Feature(31L)
            /**
             * Display server supports native color picker. Linux (X11/Wayland)
             *
             * Generated from Godot docs: DisplayServer.FEATURE_NATIVE_COLOR_PICKER
             */
            val NATIVE_COLOR_PICKER: Feature get() = Feature(32L)
            /**
             * Display server automatically fits popups according to the screen boundaries. Window nodes should
             * not attempt to do that themselves.
             *
             * Generated from Godot docs: DisplayServer.FEATURE_SELF_FITTING_WINDOWS
             */
            val SELF_FITTING_WINDOWS: Feature get() = Feature(33L)
            /**
             * Display server supports interaction with screen reader or Braille display. Linux (X11/Wayland),
             * macOS, Windows
             *
             * Generated from Godot docs: DisplayServer.FEATURE_ACCESSIBILITY_SCREEN_READER
             */
            val ACCESSIBILITY_SCREEN_READER: Feature get() = Feature(34L)
            /**
             * Display server supports HDR output. Linux (Wayland), macOS, iOS, visionOS, Windows
             *
             * Generated from Godot docs: DisplayServer.FEATURE_HDR_OUTPUT
             */
            val HDR_OUTPUT: Feature get() = Feature(35L)
            /**
             * Display server supports putting the application in picture-in-picture mode. Android
             *
             * Generated from Godot docs: DisplayServer.FEATURE_PIP_MODE
             */
            val PIP_MODE: Feature get() = Feature(36L)
        }
    }

    @JvmInline
    value class AccessibilityRole(val value: Long) {
        companion object {
            /**
             * Unknown or custom role.
             *
             * Generated from Godot docs: DisplayServer.ROLE_UNKNOWN
             */
            val UNKNOWN: AccessibilityRole get() = AccessibilityRole(0L)
            /**
             * Default dialog button element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_DEFAULT_BUTTON
             */
            val DEFAULT_BUTTON: AccessibilityRole get() = AccessibilityRole(1L)
            /**
             * Audio player element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_AUDIO
             */
            val AUDIO: AccessibilityRole get() = AccessibilityRole(2L)
            /**
             * Video player element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_VIDEO
             */
            val VIDEO: AccessibilityRole get() = AccessibilityRole(3L)
            /**
             * Non-editable text label.
             *
             * Generated from Godot docs: DisplayServer.ROLE_STATIC_TEXT
             */
            val STATIC_TEXT: AccessibilityRole get() = AccessibilityRole(4L)
            /**
             * Container element. Elements with this role are used for internal structure and ignored by screen
             * readers.
             *
             * Generated from Godot docs: DisplayServer.ROLE_CONTAINER
             */
            val CONTAINER: AccessibilityRole get() = AccessibilityRole(5L)
            /**
             * Panel container element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_PANEL
             */
            val PANEL: AccessibilityRole get() = AccessibilityRole(6L)
            /**
             * Button element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_BUTTON
             */
            val BUTTON: AccessibilityRole get() = AccessibilityRole(7L)
            /**
             * Link element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_LINK
             */
            val LINK: AccessibilityRole get() = AccessibilityRole(8L)
            /**
             * Check box element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_CHECK_BOX
             */
            val CHECK_BOX: AccessibilityRole get() = AccessibilityRole(9L)
            /**
             * Radio button element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_RADIO_BUTTON
             */
            val RADIO_BUTTON: AccessibilityRole get() = AccessibilityRole(10L)
            /**
             * Check button element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_CHECK_BUTTON
             */
            val CHECK_BUTTON: AccessibilityRole get() = AccessibilityRole(11L)
            /**
             * Scroll bar element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_SCROLL_BAR
             */
            val SCROLL_BAR: AccessibilityRole get() = AccessibilityRole(12L)
            /**
             * Scroll container element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_SCROLL_VIEW
             */
            val SCROLL_VIEW: AccessibilityRole get() = AccessibilityRole(13L)
            /**
             * Container splitter handle element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_SPLITTER
             */
            val SPLITTER: AccessibilityRole get() = AccessibilityRole(14L)
            /**
             * Slider element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_SLIDER
             */
            val SLIDER: AccessibilityRole get() = AccessibilityRole(15L)
            /**
             * Spin box element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_SPIN_BUTTON
             */
            val SPIN_BUTTON: AccessibilityRole get() = AccessibilityRole(16L)
            /**
             * Progress indicator element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_PROGRESS_INDICATOR
             */
            val PROGRESS_INDICATOR: AccessibilityRole get() = AccessibilityRole(17L)
            /**
             * Editable text field element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TEXT_FIELD
             */
            val TEXT_FIELD: AccessibilityRole get() = AccessibilityRole(18L)
            /**
             * Multiline editable text field element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_MULTILINE_TEXT_FIELD
             */
            val MULTILINE_TEXT_FIELD: AccessibilityRole get() = AccessibilityRole(19L)
            /**
             * Color picker element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_COLOR_PICKER
             */
            val COLOR_PICKER: AccessibilityRole get() = AccessibilityRole(20L)
            /**
             * Table element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TABLE
             */
            val TABLE: AccessibilityRole get() = AccessibilityRole(21L)
            /**
             * Table/tree cell element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_CELL
             */
            val CELL: AccessibilityRole get() = AccessibilityRole(22L)
            /**
             * Table/tree row element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_ROW
             */
            val ROW: AccessibilityRole get() = AccessibilityRole(23L)
            /**
             * Table/tree row group element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_ROW_GROUP
             */
            val ROW_GROUP: AccessibilityRole get() = AccessibilityRole(24L)
            /**
             * Table/tree row header element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_ROW_HEADER
             */
            val ROW_HEADER: AccessibilityRole get() = AccessibilityRole(25L)
            /**
             * Table/tree column header element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_COLUMN_HEADER
             */
            val COLUMN_HEADER: AccessibilityRole get() = AccessibilityRole(26L)
            /**
             * Tree view element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TREE
             */
            val TREE: AccessibilityRole get() = AccessibilityRole(27L)
            /**
             * Tree view item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TREE_ITEM
             */
            val TREE_ITEM: AccessibilityRole get() = AccessibilityRole(28L)
            /**
             * List element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_LIST
             */
            val LIST: AccessibilityRole get() = AccessibilityRole(29L)
            /**
             * List item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_LIST_ITEM
             */
            val LIST_ITEM: AccessibilityRole get() = AccessibilityRole(30L)
            /**
             * List view element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_LIST_BOX
             */
            val LIST_BOX: AccessibilityRole get() = AccessibilityRole(31L)
            /**
             * List view item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_LIST_BOX_OPTION
             */
            val LIST_BOX_OPTION: AccessibilityRole get() = AccessibilityRole(32L)
            /**
             * Tab bar element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TAB_BAR
             */
            val TAB_BAR: AccessibilityRole get() = AccessibilityRole(33L)
            /**
             * Tab bar item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TAB
             */
            val TAB: AccessibilityRole get() = AccessibilityRole(34L)
            /**
             * Tab panel element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TAB_PANEL
             */
            val TAB_PANEL: AccessibilityRole get() = AccessibilityRole(35L)
            /**
             * Menu bar element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_MENU_BAR
             */
            val MENU_BAR: AccessibilityRole get() = AccessibilityRole(36L)
            /**
             * Popup menu element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_MENU
             */
            val MENU: AccessibilityRole get() = AccessibilityRole(37L)
            /**
             * Popup menu item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_MENU_ITEM
             */
            val MENU_ITEM: AccessibilityRole get() = AccessibilityRole(38L)
            /**
             * Popup menu check button item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_MENU_ITEM_CHECK_BOX
             */
            val MENU_ITEM_CHECK_BOX: AccessibilityRole get() = AccessibilityRole(39L)
            /**
             * Popup menu radio button item element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_MENU_ITEM_RADIO
             */
            val MENU_ITEM_RADIO: AccessibilityRole get() = AccessibilityRole(40L)
            /**
             * Image element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_IMAGE
             */
            val IMAGE: AccessibilityRole get() = AccessibilityRole(41L)
            /**
             * Window element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_WINDOW
             */
            val WINDOW: AccessibilityRole get() = AccessibilityRole(42L)
            /**
             * Embedded window title bar element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TITLE_BAR
             */
            val TITLE_BAR: AccessibilityRole get() = AccessibilityRole(43L)
            /**
             * Dialog window element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_DIALOG
             */
            val DIALOG: AccessibilityRole get() = AccessibilityRole(44L)
            /**
             * Tooltip element.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TOOLTIP
             */
            val TOOLTIP: AccessibilityRole get() = AccessibilityRole(45L)
            /**
             * Region/landmark element. Screen readers can navigate between regions using landmark navigation.
             *
             * Generated from Godot docs: DisplayServer.ROLE_REGION
             */
            val REGION: AccessibilityRole get() = AccessibilityRole(46L)
            /**
             * Unifor text run. Note: This role is used for internal text elements, and should not be assigned
             * to nodes.
             *
             * Generated from Godot docs: DisplayServer.ROLE_TEXT_RUN
             */
            val TEXT_RUN: AccessibilityRole get() = AccessibilityRole(47L)
        }
    }

    @JvmInline
    value class AccessibilityPopupType(val value: Long) {
        companion object {
            /**
             * Popup menu.
             *
             * Generated from Godot docs: DisplayServer.POPUP_MENU
             */
            val MENU: AccessibilityPopupType get() = AccessibilityPopupType(0L)
            /**
             * Popup list.
             *
             * Generated from Godot docs: DisplayServer.POPUP_LIST
             */
            val LIST: AccessibilityPopupType get() = AccessibilityPopupType(1L)
            /**
             * Popup tree view.
             *
             * Generated from Godot docs: DisplayServer.POPUP_TREE
             */
            val TREE: AccessibilityPopupType get() = AccessibilityPopupType(2L)
            /**
             * Popup dialog.
             *
             * Generated from Godot docs: DisplayServer.POPUP_DIALOG
             */
            val DIALOG: AccessibilityPopupType get() = AccessibilityPopupType(3L)
        }
    }

    @JvmInline
    value class AccessibilityFlags(val value: Long) {
        companion object {
            /**
             * Element is hidden for accessibility tools.
             *
             * Generated from Godot docs: DisplayServer.FLAG_HIDDEN
             */
            val HIDDEN: AccessibilityFlags get() = AccessibilityFlags(0L)
            /**
             * Element supports multiple item selection.
             *
             * Generated from Godot docs: DisplayServer.FLAG_MULTISELECTABLE
             */
            val MULTISELECTABLE: AccessibilityFlags get() = AccessibilityFlags(1L)
            /**
             * Element require user input.
             *
             * Generated from Godot docs: DisplayServer.FLAG_REQUIRED
             */
            val REQUIRED: AccessibilityFlags get() = AccessibilityFlags(2L)
            /**
             * Element is a visited link.
             *
             * Generated from Godot docs: DisplayServer.FLAG_VISITED
             */
            val VISITED: AccessibilityFlags get() = AccessibilityFlags(3L)
            /**
             * Element content is not ready (e.g. loading).
             *
             * Generated from Godot docs: DisplayServer.FLAG_BUSY
             */
            val BUSY: AccessibilityFlags get() = AccessibilityFlags(4L)
            /**
             * Element is modal window.
             *
             * Generated from Godot docs: DisplayServer.FLAG_MODAL
             */
            val MODAL: AccessibilityFlags get() = AccessibilityFlags(5L)
            /**
             * Element allows touches to be passed through when a screen reader is in touch exploration mode.
             *
             * Generated from Godot docs: DisplayServer.FLAG_TOUCH_PASSTHROUGH
             */
            val TOUCH_PASSTHROUGH: AccessibilityFlags get() = AccessibilityFlags(6L)
            /**
             * Element is text field with selectable but read-only text.
             *
             * Generated from Godot docs: DisplayServer.FLAG_READONLY
             */
            val READONLY: AccessibilityFlags get() = AccessibilityFlags(7L)
            /**
             * Element is disabled.
             *
             * Generated from Godot docs: DisplayServer.FLAG_DISABLED
             */
            val DISABLED: AccessibilityFlags get() = AccessibilityFlags(8L)
            /**
             * Element clips children.
             *
             * Generated from Godot docs: DisplayServer.FLAG_CLIPS_CHILDREN
             */
            val CLIPS_CHILDREN: AccessibilityFlags get() = AccessibilityFlags(9L)
        }
    }

    @JvmInline
    value class AccessibilityAction(val value: Long) {
        companion object {
            /**
             * Single click action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_CLICK
             */
            val CLICK: AccessibilityAction get() = AccessibilityAction(0L)
            /**
             * Focus action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_FOCUS
             */
            val FOCUS: AccessibilityAction get() = AccessibilityAction(1L)
            /**
             * Blur action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_BLUR
             */
            val BLUR: AccessibilityAction get() = AccessibilityAction(2L)
            /**
             * Collapse action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_COLLAPSE
             */
            val COLLAPSE: AccessibilityAction get() = AccessibilityAction(3L)
            /**
             * Expand action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_EXPAND
             */
            val EXPAND: AccessibilityAction get() = AccessibilityAction(4L)
            /**
             * Decrement action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_DECREMENT
             */
            val DECREMENT: AccessibilityAction get() = AccessibilityAction(5L)
            /**
             * Increment action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_INCREMENT
             */
            val INCREMENT: AccessibilityAction get() = AccessibilityAction(6L)
            /**
             * Hide tooltip action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_HIDE_TOOLTIP
             */
            val HIDE_TOOLTIP: AccessibilityAction get() = AccessibilityAction(7L)
            /**
             * Show tooltip action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SHOW_TOOLTIP
             */
            val SHOW_TOOLTIP: AccessibilityAction get() = AccessibilityAction(8L)
            /**
             * Set text selection action, callback argument is set to `Dictionary` with the following keys: -
             * `"start_element"` accessibility element of the selection start. - `"start_char"` character
             * offset relative to the accessibility element of the selection start. - `"end_element"`
             * accessibility element of the selection end. - `"end_char"` character offset relative to the
             * accessibility element of the selection end.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SET_TEXT_SELECTION
             */
            val SET_TEXT_SELECTION: AccessibilityAction get() = AccessibilityAction(9L)
            /**
             * Replace text action, callback argument is set to `String` with the replacement text.
             *
             * Generated from Godot docs: DisplayServer.ACTION_REPLACE_SELECTED_TEXT
             */
            val REPLACE_SELECTED_TEXT: AccessibilityAction get() = AccessibilityAction(10L)
            /**
             * Scroll backward action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_BACKWARD
             */
            val SCROLL_BACKWARD: AccessibilityAction get() = AccessibilityAction(11L)
            /**
             * Scroll down action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_DOWN
             */
            val SCROLL_DOWN: AccessibilityAction get() = AccessibilityAction(12L)
            /**
             * Scroll forward action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_FORWARD
             */
            val SCROLL_FORWARD: AccessibilityAction get() = AccessibilityAction(13L)
            /**
             * Scroll left action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_LEFT
             */
            val SCROLL_LEFT: AccessibilityAction get() = AccessibilityAction(14L)
            /**
             * Scroll right action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_RIGHT
             */
            val SCROLL_RIGHT: AccessibilityAction get() = AccessibilityAction(15L)
            /**
             * Scroll up action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_UP
             */
            val SCROLL_UP: AccessibilityAction get() = AccessibilityAction(16L)
            /**
             * Scroll into view action, callback argument is set to `AccessibilityScrollHint`.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_INTO_VIEW
             */
            val SCROLL_INTO_VIEW: AccessibilityAction get() = AccessibilityAction(17L)
            /**
             * Scroll to point action, callback argument is set to `Vector2` with the relative point
             * coordinates.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SCROLL_TO_POINT
             */
            val SCROLL_TO_POINT: AccessibilityAction get() = AccessibilityAction(18L)
            /**
             * Set scroll offset action, callback argument is set to `Vector2` with the scroll offset.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SET_SCROLL_OFFSET
             */
            val SET_SCROLL_OFFSET: AccessibilityAction get() = AccessibilityAction(19L)
            /**
             * Set value action, callback argument is set to `String` or number with the new value.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SET_VALUE
             */
            val SET_VALUE: AccessibilityAction get() = AccessibilityAction(20L)
            /**
             * Show context menu action, callback argument is not set.
             *
             * Generated from Godot docs: DisplayServer.ACTION_SHOW_CONTEXT_MENU
             */
            val SHOW_CONTEXT_MENU: AccessibilityAction get() = AccessibilityAction(21L)
            /**
             * Custom action, callback argument is set to the integer action ID.
             *
             * Generated from Godot docs: DisplayServer.ACTION_CUSTOM
             */
            val CUSTOM: AccessibilityAction get() = AccessibilityAction(22L)
        }
    }

    @JvmInline
    value class AccessibilityLiveMode(val value: Long) {
        companion object {
            /**
             * Indicates that updates to the live region should not be presented.
             *
             * Generated from Godot docs: DisplayServer.LIVE_OFF
             */
            val OFF: AccessibilityLiveMode get() = AccessibilityLiveMode(0L)
            /**
             * Indicates that updates to the live region should be presented at the next opportunity (for
             * example at the end of speaking the current sentence).
             *
             * Generated from Godot docs: DisplayServer.LIVE_POLITE
             */
            val POLITE: AccessibilityLiveMode get() = AccessibilityLiveMode(1L)
            /**
             * Indicates that updates to the live region have the highest priority and should be presented
             * immediately.
             *
             * Generated from Godot docs: DisplayServer.LIVE_ASSERTIVE
             */
            val ASSERTIVE: AccessibilityLiveMode get() = AccessibilityLiveMode(2L)
        }
    }

    @JvmInline
    value class AccessibilityScrollUnit(val value: Long) {
        companion object {
            /**
             * The amount by which to scroll. A single item of a list, line of text.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_UNIT_ITEM
             */
            val ITEM: AccessibilityScrollUnit get() = AccessibilityScrollUnit(0L)
            /**
             * The amount by which to scroll. A single page.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_UNIT_PAGE
             */
            val PAGE: AccessibilityScrollUnit get() = AccessibilityScrollUnit(1L)
        }
    }

    @JvmInline
    value class AccessibilityScrollHint(val value: Long) {
        companion object {
            /**
             * A preferred position for the node scrolled into view. Top-left edge of the scroll container.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_HINT_TOP_LEFT
             */
            val TOP_LEFT: AccessibilityScrollHint get() = AccessibilityScrollHint(0L)
            /**
             * A preferred position for the node scrolled into view. Bottom-right edge of the scroll container.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_HINT_BOTTOM_RIGHT
             */
            val BOTTOM_RIGHT: AccessibilityScrollHint get() = AccessibilityScrollHint(1L)
            /**
             * A preferred position for the node scrolled into view. Top edge of the scroll container.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_HINT_TOP_EDGE
             */
            val TOP_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(2L)
            /**
             * A preferred position for the node scrolled into view. Bottom edge of the scroll container.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_HINT_BOTTOM_EDGE
             */
            val BOTTOM_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(3L)
            /**
             * A preferred position for the node scrolled into view. Left edge of the scroll container.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_HINT_LEFT_EDGE
             */
            val LEFT_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(4L)
            /**
             * A preferred position for the node scrolled into view. Right edge of the scroll container.
             *
             * Generated from Godot docs: DisplayServer.SCROLL_HINT_RIGHT_EDGE
             */
            val RIGHT_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(5L)
        }
    }

    @JvmInline
    value class MouseMode(val value: Long) {
        companion object {
            /**
             * Makes the mouse cursor visible if it is hidden.
             *
             * Generated from Godot docs: DisplayServer.MOUSE_MODE_VISIBLE
             */
            val VISIBLE: MouseMode get() = MouseMode(0L)
            /**
             * Makes the mouse cursor hidden if it is visible.
             *
             * Generated from Godot docs: DisplayServer.MOUSE_MODE_HIDDEN
             */
            val HIDDEN: MouseMode get() = MouseMode(1L)
            /**
             * Captures the mouse. The mouse will be hidden and its position locked at the center of the window
             * manager's window. Note: If you want to process the mouse's movement in this mode, you need to
             * use `InputEventMouseMotion.relative`.
             *
             * Generated from Godot docs: DisplayServer.MOUSE_MODE_CAPTURED
             */
            val CAPTURED: MouseMode get() = MouseMode(2L)
            /**
             * Confines the mouse cursor to the game window, and make it visible.
             *
             * Generated from Godot docs: DisplayServer.MOUSE_MODE_CONFINED
             */
            val CONFINED: MouseMode get() = MouseMode(3L)
            /**
             * Confines the mouse cursor to the game window, and make it hidden.
             *
             * Generated from Godot docs: DisplayServer.MOUSE_MODE_CONFINED_HIDDEN
             */
            val CONFINED_HIDDEN: MouseMode get() = MouseMode(4L)
            /**
             * Max value of the `MouseMode`.
             *
             * Generated from Godot docs: DisplayServer.MOUSE_MODE_MAX
             */
            val MAX: MouseMode get() = MouseMode(5L)
        }
    }

    @JvmInline
    value class ScreenOrientation(val value: Long) {
        companion object {
            /**
             * Default landscape orientation.
             *
             * Generated from Godot docs: DisplayServer.SCREEN_LANDSCAPE
             */
            val LANDSCAPE: ScreenOrientation get() = ScreenOrientation(0L)
            /**
             * Default portrait orientation.
             *
             * Generated from Godot docs: DisplayServer.SCREEN_PORTRAIT
             */
            val PORTRAIT: ScreenOrientation get() = ScreenOrientation(1L)
            /**
             * Reverse landscape orientation (upside down).
             *
             * Generated from Godot docs: DisplayServer.SCREEN_REVERSE_LANDSCAPE
             */
            val REVERSE_LANDSCAPE: ScreenOrientation get() = ScreenOrientation(2L)
            /**
             * Reverse portrait orientation (upside down).
             *
             * Generated from Godot docs: DisplayServer.SCREEN_REVERSE_PORTRAIT
             */
            val REVERSE_PORTRAIT: ScreenOrientation get() = ScreenOrientation(3L)
            /**
             * Automatic landscape orientation (default or reverse depending on sensor).
             *
             * Generated from Godot docs: DisplayServer.SCREEN_SENSOR_LANDSCAPE
             */
            val SENSOR_LANDSCAPE: ScreenOrientation get() = ScreenOrientation(4L)
            /**
             * Automatic portrait orientation (default or reverse depending on sensor).
             *
             * Generated from Godot docs: DisplayServer.SCREEN_SENSOR_PORTRAIT
             */
            val SENSOR_PORTRAIT: ScreenOrientation get() = ScreenOrientation(5L)
            /**
             * Automatic landscape or portrait orientation (default or reverse depending on sensor).
             *
             * Generated from Godot docs: DisplayServer.SCREEN_SENSOR
             */
            val SENSOR: ScreenOrientation get() = ScreenOrientation(6L)
        }
    }

    @JvmInline
    value class VirtualKeyboardType(val value: Long) {
        companion object {
            /**
             * Default text virtual keyboard.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_DEFAULT
             */
            val DEFAULT: VirtualKeyboardType get() = VirtualKeyboardType(0L)
            /**
             * Multiline virtual keyboard.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_MULTILINE
             */
            val MULTILINE: VirtualKeyboardType get() = VirtualKeyboardType(1L)
            /**
             * Virtual number keypad, useful for PIN entry.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_NUMBER
             */
            val NUMBER: VirtualKeyboardType get() = VirtualKeyboardType(2L)
            /**
             * Virtual number keypad, useful for entering fractional numbers.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_NUMBER_DECIMAL
             */
            val NUMBER_DECIMAL: VirtualKeyboardType get() = VirtualKeyboardType(3L)
            /**
             * Virtual phone number keypad.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_PHONE
             */
            val PHONE: VirtualKeyboardType get() = VirtualKeyboardType(4L)
            /**
             * Virtual keyboard with additional keys to assist with typing email addresses.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_EMAIL_ADDRESS
             */
            val EMAIL_ADDRESS: VirtualKeyboardType get() = VirtualKeyboardType(5L)
            /**
             * Virtual keyboard for entering a password. On most platforms, this should disable autocomplete
             * and autocapitalization. Note: This is not supported on Web. Instead, this behaves identically to
             * `KEYBOARD_TYPE_DEFAULT`.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_PASSWORD
             */
            val PASSWORD: VirtualKeyboardType get() = VirtualKeyboardType(6L)
            /**
             * Virtual keyboard with additional keys to assist with typing URLs.
             *
             * Generated from Godot docs: DisplayServer.KEYBOARD_TYPE_URL
             */
            val URL: VirtualKeyboardType get() = VirtualKeyboardType(7L)
        }
    }

    @JvmInline
    value class CursorShape(val value: Long) {
        companion object {
            /**
             * Arrow cursor shape. This is the default when not pointing anything that overrides the mouse
             * cursor, such as a `LineEdit` or `TextEdit`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_ARROW
             */
            val ARROW: CursorShape get() = CursorShape(0L)
            /**
             * I-beam cursor shape. This is used by default when hovering a control that accepts text input,
             * such as `LineEdit` or `TextEdit`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_IBEAM
             */
            val IBEAM: CursorShape get() = CursorShape(1L)
            /**
             * Pointing hand cursor shape. This is used by default when hovering a `LinkButton` or a URL tag in
             * a `RichTextLabel`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_POINTING_HAND
             */
            val POINTING_HAND: CursorShape get() = CursorShape(2L)
            /**
             * Crosshair cursor. This is intended to be displayed when the user needs precise aim over an
             * element, such as a rectangle selection tool or a color picker.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_CROSS
             */
            val CROSS: CursorShape get() = CursorShape(3L)
            /**
             * Wait cursor. On most cursor themes, this displays a spinning icon besides the arrow. Intended to
             * be used for non-blocking operations (when the user can do something else at the moment). See
             * also `CURSOR_BUSY`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_WAIT
             */
            val WAIT: CursorShape get() = CursorShape(4L)
            /**
             * Wait cursor. On most cursor themes, this replaces the arrow with a spinning icon. Intended to be
             * used for blocking operations (when the user can't do anything else at the moment). See also
             * `CURSOR_WAIT`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_BUSY
             */
            val BUSY: CursorShape get() = CursorShape(5L)
            /**
             * Dragging hand cursor. This is displayed during drag-and-drop operations. See also
             * `CURSOR_CAN_DROP`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_DRAG
             */
            val DRAG: CursorShape get() = CursorShape(6L)
            /**
             * "Can drop" cursor. This is displayed during drag-and-drop operations if hovering over a
             * `Control` that can accept the drag-and-drop event. On most cursor themes, this displays a
             * dragging hand with an arrow symbol besides it. See also `CURSOR_DRAG`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_CAN_DROP
             */
            val CAN_DROP: CursorShape get() = CursorShape(7L)
            /**
             * Forbidden cursor. This is displayed during drag-and-drop operations if the hovered `Control`
             * can't accept the drag-and-drop event.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_FORBIDDEN
             */
            val FORBIDDEN: CursorShape get() = CursorShape(8L)
            /**
             * Vertical resize cursor. Intended to be displayed when the hovered `Control` can be vertically
             * resized using the mouse. See also `CURSOR_VSPLIT`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_VSIZE
             */
            val VSIZE: CursorShape get() = CursorShape(9L)
            /**
             * Horizontal resize cursor. Intended to be displayed when the hovered `Control` can be
             * horizontally resized using the mouse. See also `CURSOR_HSPLIT`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_HSIZE
             */
            val HSIZE: CursorShape get() = CursorShape(10L)
            /**
             * Secondary diagonal resize cursor (top-right/bottom-left). Intended to be displayed when the
             * hovered `Control` can be resized on both axes at once using the mouse.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_BDIAGSIZE
             */
            val BDIAGSIZE: CursorShape get() = CursorShape(11L)
            /**
             * Main diagonal resize cursor (top-left/bottom-right). Intended to be displayed when the hovered
             * `Control` can be resized on both axes at once using the mouse.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_FDIAGSIZE
             */
            val FDIAGSIZE: CursorShape get() = CursorShape(12L)
            /**
             * Move cursor. Intended to be displayed when the hovered `Control` can be moved using the mouse.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_MOVE
             */
            val MOVE: CursorShape get() = CursorShape(13L)
            /**
             * Vertical split cursor. This is displayed when hovering a `Control` with splits that can be
             * vertically resized using the mouse, such as `VSplitContainer`. On some cursor themes, this
             * cursor may have the same appearance as `CURSOR_VSIZE`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_VSPLIT
             */
            val VSPLIT: CursorShape get() = CursorShape(14L)
            /**
             * Horizontal split cursor. This is displayed when hovering a `Control` with splits that can be
             * horizontally resized using the mouse, such as `HSplitContainer`. On some cursor themes, this
             * cursor may have the same appearance as `CURSOR_HSIZE`.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_HSPLIT
             */
            val HSPLIT: CursorShape get() = CursorShape(15L)
            /**
             * Help cursor. On most cursor themes, this displays a question mark icon instead of the mouse
             * cursor. Intended to be used when the user has requested help on the next element that will be
             * clicked.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_HELP
             */
            val HELP: CursorShape get() = CursorShape(16L)
            /**
             * Represents the size of the `CursorShape` enum.
             *
             * Generated from Godot docs: DisplayServer.CURSOR_MAX
             */
            val MAX: CursorShape get() = CursorShape(17L)
        }
    }

    @JvmInline
    value class FileDialogMode(val value: Long) {
        companion object {
            /**
             * The native file dialog allows selecting one, and only one file.
             *
             * Generated from Godot docs: DisplayServer.FILE_DIALOG_MODE_OPEN_FILE
             */
            val OPEN_FILE: FileDialogMode get() = FileDialogMode(0L)
            /**
             * The native file dialog allows selecting multiple files.
             *
             * Generated from Godot docs: DisplayServer.FILE_DIALOG_MODE_OPEN_FILES
             */
            val OPEN_FILES: FileDialogMode get() = FileDialogMode(1L)
            /**
             * The native file dialog only allows selecting a directory, disallowing the selection of any file.
             *
             * Generated from Godot docs: DisplayServer.FILE_DIALOG_MODE_OPEN_DIR
             */
            val OPEN_DIR: FileDialogMode get() = FileDialogMode(2L)
            /**
             * The native file dialog allows selecting one file or directory.
             *
             * Generated from Godot docs: DisplayServer.FILE_DIALOG_MODE_OPEN_ANY
             */
            val OPEN_ANY: FileDialogMode get() = FileDialogMode(3L)
            /**
             * The native file dialog will warn when a file exists.
             *
             * Generated from Godot docs: DisplayServer.FILE_DIALOG_MODE_SAVE_FILE
             */
            val SAVE_FILE: FileDialogMode get() = FileDialogMode(4L)
        }
    }

    @JvmInline
    value class WindowMode(val value: Long) {
        companion object {
            /**
             * Windowed mode, i.e. `Window` doesn't occupy the whole screen (unless set to the size of the
             * screen).
             *
             * Generated from Godot docs: DisplayServer.WINDOW_MODE_WINDOWED
             */
            val WINDOWED: WindowMode get() = WindowMode(0L)
            /**
             * Minimized window mode, i.e. `Window` is not visible and available on window manager's window
             * list. Normally happens when the minimize button is pressed.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_MODE_MINIMIZED
             */
            val MINIMIZED: WindowMode get() = WindowMode(1L)
            /**
             * Maximized window mode, i.e. `Window` will occupy whole screen area except task bar and still
             * display its borders. Normally happens when the maximize button is pressed.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_MODE_MAXIMIZED
             */
            val MAXIMIZED: WindowMode get() = WindowMode(2L)
            /**
             * Full screen mode with full multi-window support. Full screen window covers the entire display
             * area of a screen and has no decorations. The display's video mode is not changed. On Android:
             * This enables immersive mode. On macOS: A new desktop is used to display the running project.
             * Note: Regardless of the platform, enabling full screen will change the window size to match the
             * monitor's size. Therefore, make sure your project supports multiple resolutions
             * ($DOCS_URL/tutorials/rendering/multiple_resolutions.html) when enabling full screen mode.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_MODE_FULLSCREEN
             */
            val FULLSCREEN: WindowMode get() = WindowMode(3L)
            /**
             * A single window full screen mode. This mode has less overhead, but only one window can be open
             * on a given screen at a time (opening a child window or application switching will trigger a full
             * screen transition). Full screen window covers the entire display area of a screen and has no
             * border or decorations. The display's video mode is not changed. Note: This mode might not work
             * with screen recording software. On Android: This enables immersive mode. On Windows: Depending
             * on video driver, full screen transition might cause screens to go black for a moment. On macOS:
             * A new desktop is used to display the running project. Exclusive full screen mode prevents Dock
             * and Menu from showing up when the mouse pointer is hovering the edge of the screen. On Linux
             * (X11): Exclusive full screen mode bypasses compositor. On Linux (Wayland): Equivalent to
             * `WINDOW_MODE_FULLSCREEN`. Note: Regardless of the platform, enabling full screen will change the
             * window size to match the monitor's size. Therefore, make sure your project supports multiple
             * resolutions ($DOCS_URL/tutorials/rendering/multiple_resolutions.html) when enabling full screen
             * mode.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_MODE_EXCLUSIVE_FULLSCREEN
             */
            val EXCLUSIVE_FULLSCREEN: WindowMode get() = WindowMode(4L)
        }
    }

    @JvmInline
    value class ProgressState(val value: Long) {
        companion object {
            /**
             * Stops displaying progress and returns the button to its normal state.
             *
             * Generated from Godot docs: DisplayServer.PROGRESS_STATE_NOPROGRESS
             */
            val NOPROGRESS: ProgressState get() = ProgressState(0L)
            /**
             * The progress indicator shows an indeterminate progress. On Windows, the progress indicator does
             * not grow in size, but cycles repeatedly along the length of the taskbar button by default.
             *
             * Generated from Godot docs: DisplayServer.PROGRESS_STATE_INDETERMINATE
             */
            val INDETERMINATE: ProgressState get() = ProgressState(1L)
            /**
             * The progress indicator shows progress normally.
             *
             * Generated from Godot docs: DisplayServer.PROGRESS_STATE_NORMAL
             */
            val NORMAL: ProgressState get() = ProgressState(2L)
            /**
             * The progress indicator shows that an error has occurred. On Windows, the progress indicator
             * turns red by default to show that an error has occurred in one of the windows that is
             * broadcasting progress.
             *
             * Generated from Godot docs: DisplayServer.PROGRESS_STATE_ERROR
             */
            val ERROR: ProgressState get() = ProgressState(3L)
            /**
             * The progress indicator shows it was paused. On Windows, the progress indicator turns yellow by
             * default to show that progress is currently stopped in one of the windows but can be resumed by
             * the user.
             *
             * Generated from Godot docs: DisplayServer.PROGRESS_STATE_PAUSED
             */
            val PAUSED: ProgressState get() = ProgressState(4L)
        }
    }

    @JvmInline
    value class WindowFlags(val value: Long) {
        companion object {
            /**
             * The window can't be resized by dragging its resize grip. It's still possible to resize the
             * window using `window_set_size`. This flag is ignored for full screen windows. Note: This flag is
             * implemented on Linux (X11), macOS, and Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_RESIZE_DISABLED
             */
            val RESIZE_DISABLED: WindowFlags get() = WindowFlags(0L)
            /**
             * The window do not have native title bar and other decorations. This flag is ignored for
             * full-screen windows. Note: This flag is implemented on Linux (X11/Wayland), macOS, and Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_BORDERLESS
             */
            val BORDERLESS: WindowFlags get() = WindowFlags(1L)
            /**
             * The window is floating on top of all other windows. This flag is ignored for full-screen
             * windows. Note: This flag is implemented on Linux (X11), macOS, and Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_ALWAYS_ON_TOP
             */
            val ALWAYS_ON_TOP: WindowFlags get() = WindowFlags(2L)
            /**
             * The window background can be transparent. Note: This flag has no effect if
             * `is_window_transparency_available` returns `false`. Note: Transparency support is implemented on
             * Linux (X11/Wayland), macOS, and Windows, but availability might vary depending on GPU driver,
             * display manager, and compositor capabilities. Note: Transparency support is implemented on
             * Android, but can only be enabled via
             * `ProjectSettings.display/window/per_pixel_transparency/allowed`. This flag has no effect on
             * Android.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_TRANSPARENT
             */
            val TRANSPARENT: WindowFlags get() = WindowFlags(3L)
            /**
             * The window can't be focused. No-focus window will ignore all input, except mouse clicks. Note:
             * This flag is implemented on Linux (X11), macOS, and Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_NO_FOCUS
             */
            val NO_FOCUS: WindowFlags get() = WindowFlags(4L)
            /**
             * Window is part of menu or `OptionButton` dropdown. This flag can't be changed when the window is
             * visible. An active popup window will exclusively receive all input, without stealing focus from
             * its parent. Popup windows are automatically closed when uses click outside it, or when an
             * application is switched. Popup window must have transient parent set (see
             * `window_set_transient`). Note: This flag is implemented on Linux (X11/Wayland), macOS, and
             * Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_POPUP
             */
            val POPUP: WindowFlags get() = WindowFlags(5L)
            /**
             * Window content is expanded to the full size of the window. Unlike borderless window, the frame
             * is left intact and can be used to resize the window, title bar is transparent, but have
             * minimize/maximize/close buttons. Use `window_set_window_buttons_offset` to adjust
             * minimize/maximize/close buttons offset. Use `window_get_safe_title_margins` to determine area
             * under the title bar that is not covered by decorations. Note: This flag is implemented only on
             * macOS.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_EXTEND_TO_TITLE
             */
            val EXTEND_TO_TITLE: WindowFlags get() = WindowFlags(6L)
            /**
             * All mouse events are passed to the underlying window of the same application. Note: This flag is
             * implemented on Linux (X11), macOS, and Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_MOUSE_PASSTHROUGH
             */
            val MOUSE_PASSTHROUGH: WindowFlags get() = WindowFlags(7L)
            /**
             * Window style is overridden, forcing sharp corners. Note: This flag is implemented only on
             * Windows (11).
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_SHARP_CORNERS
             */
            val SHARP_CORNERS: WindowFlags get() = WindowFlags(8L)
            /**
             * Window is excluded from screenshots taken by `screen_get_image`, `screen_get_image_rect`, and
             * `screen_get_pixel`. Note: This flag is implemented on macOS and Windows (10, 20H1). Note:
             * Setting this flag will prevent standard screenshot methods from capturing a window image, but
             * does NOT guarantee that other apps won't be able to capture an image. It should not be used as a
             * DRM or security measure.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_EXCLUDE_FROM_CAPTURE
             */
            val EXCLUDE_FROM_CAPTURE: WindowFlags get() = WindowFlags(9L)
            /**
             * Signals the window manager that this window is supposed to be an implementation-defined "popup"
             * (usually a floating, borderless, untileable and immovable child window). Note: This flag is
             * implemented on Linux (Wayland).
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_POPUP_WM_HINT
             */
            val POPUP_WM_HINT: WindowFlags get() = WindowFlags(10L)
            /**
             * Window minimize button is disabled. Note: This flag is implemented on Linux (X11), macOS, and
             * Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_MINIMIZE_DISABLED
             */
            val MINIMIZE_DISABLED: WindowFlags get() = WindowFlags(11L)
            /**
             * Window maximize button is disabled. Note: This flag is implemented on Linux (X11), macOS, and
             * Windows.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_MAXIMIZE_DISABLED
             */
            val MAXIMIZE_DISABLED: WindowFlags get() = WindowFlags(12L)
            /**
             * Represents the size of the `WindowFlags` enum.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_FLAG_MAX
             */
            val MAX: WindowFlags get() = WindowFlags(13L)
        }
    }

    @JvmInline
    value class WindowEvent(val value: Long) {
        companion object {
            /**
             * Sent when the mouse pointer enters the window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_MOUSE_ENTER
             */
            val MOUSE_ENTER: WindowEvent get() = WindowEvent(0L)
            /**
             * Sent when the mouse pointer exits the window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_MOUSE_EXIT
             */
            val MOUSE_EXIT: WindowEvent get() = WindowEvent(1L)
            /**
             * Sent when the window grabs focus.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_FOCUS_IN
             */
            val FOCUS_IN: WindowEvent get() = WindowEvent(2L)
            /**
             * Sent when the window loses focus.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_FOCUS_OUT
             */
            val FOCUS_OUT: WindowEvent get() = WindowEvent(3L)
            /**
             * Sent when the user has attempted to close the window (e.g. close button is pressed).
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_CLOSE_REQUEST
             */
            val CLOSE_REQUEST: WindowEvent get() = WindowEvent(4L)
            /**
             * Sent when the device "Back" button is pressed. Note: This event is implemented only on Android.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST
             */
            val GO_BACK_REQUEST: WindowEvent get() = WindowEvent(5L)
            /**
             * Sent when the window is moved to the display with different DPI, or display DPI is changed.
             * Note: This flag is implemented only on macOS and Linux (Wayland).
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_DPI_CHANGE
             */
            val DPI_CHANGE: WindowEvent get() = WindowEvent(6L)
            /**
             * Sent when the window title bar decoration is changed (e.g. `WINDOW_FLAG_EXTEND_TO_TITLE` is set
             * or window entered/exited full screen mode). Note: This flag is implemented only on macOS.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_TITLEBAR_CHANGE
             */
            val TITLEBAR_CHANGE: WindowEvent get() = WindowEvent(7L)
            /**
             * Sent when the window has been forcibly closed by the display server. The window will immediately
             * hide and clean any internal rendering references. Note: This flag is implemented only on Linux
             * (Wayland).
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_FORCE_CLOSE
             */
            val FORCE_CLOSE: WindowEvent get() = WindowEvent(8L)
            /**
             * Sent when the output max linear value returned by `Window.get_output_max_linear_value` has
             * changed. This occurs when HDR output is enabled or disabled and when any HDR output luminance
             * values of the window have changed, such as when the player adjusts their screen brightness
             * setting or moves the window to a different screen.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EVENT_OUTPUT_MAX_LINEAR_VALUE_CHANGED
             */
            val OUTPUT_MAX_LINEAR_VALUE_CHANGED: WindowEvent get() = WindowEvent(9L)
        }
    }

    @JvmInline
    value class WindowResizeEdge(val value: Long) {
        companion object {
            /**
             * Top-left edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_TOP_LEFT
             */
            val TOP_LEFT: WindowResizeEdge get() = WindowResizeEdge(0L)
            /**
             * Top edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_TOP
             */
            val TOP: WindowResizeEdge get() = WindowResizeEdge(1L)
            /**
             * Top-right edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_TOP_RIGHT
             */
            val TOP_RIGHT: WindowResizeEdge get() = WindowResizeEdge(2L)
            /**
             * Left edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_LEFT
             */
            val LEFT: WindowResizeEdge get() = WindowResizeEdge(3L)
            /**
             * Right edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_RIGHT
             */
            val RIGHT: WindowResizeEdge get() = WindowResizeEdge(4L)
            /**
             * Bottom-left edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_BOTTOM_LEFT
             */
            val BOTTOM_LEFT: WindowResizeEdge get() = WindowResizeEdge(5L)
            /**
             * Bottom edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_BOTTOM
             */
            val BOTTOM: WindowResizeEdge get() = WindowResizeEdge(6L)
            /**
             * Bottom-right edge of a window.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_BOTTOM_RIGHT
             */
            val BOTTOM_RIGHT: WindowResizeEdge get() = WindowResizeEdge(7L)
            /**
             * Represents the size of the `WindowResizeEdge` enum.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_EDGE_MAX
             */
            val MAX: WindowResizeEdge get() = WindowResizeEdge(8L)
        }
    }

    @JvmInline
    value class VSyncMode(val value: Long) {
        companion object {
            /**
             * No vertical synchronization, which means the engine will display frames as fast as possible
             * (tearing may be visible). Framerate is unlimited (regardless of `Engine.max_fps`).
             *
             * Generated from Godot docs: DisplayServer.VSYNC_DISABLED
             */
            val DISABLED: VSyncMode get() = VSyncMode(0L)
            /**
             * Default vertical synchronization mode, the image is displayed only on vertical blanking
             * intervals (no tearing is visible). Framerate is limited by the monitor refresh rate (regardless
             * of `Engine.max_fps`).
             *
             * Generated from Godot docs: DisplayServer.VSYNC_ENABLED
             */
            val ENABLED: VSyncMode get() = VSyncMode(1L)
            /**
             * Behaves like `VSYNC_DISABLED` when the framerate drops below the screen's refresh rate to reduce
             * stuttering (tearing may be visible). Otherwise, vertical synchronization is enabled to avoid
             * tearing. Framerate is limited by the monitor refresh rate (regardless of `Engine.max_fps`).
             * Behaves like `VSYNC_ENABLED` when using the Compatibility rendering method.
             *
             * Generated from Godot docs: DisplayServer.VSYNC_ADAPTIVE
             */
            val ADAPTIVE: VSyncMode get() = VSyncMode(2L)
            /**
             * Displays the most recent image in the queue on vertical blanking intervals, while rendering to
             * the other images (no tearing is visible). Framerate is unlimited (regardless of
             * `Engine.max_fps`). Although not guaranteed, the images can be rendered as fast as possible,
             * which may reduce input lag (also called "Fast" V-Sync mode). `VSYNC_MAILBOX` works best when at
             * least twice as many frames as the display refresh rate are rendered. Behaves like
             * `VSYNC_ENABLED` when using the Compatibility rendering method.
             *
             * Generated from Godot docs: DisplayServer.VSYNC_MAILBOX
             */
            val MAILBOX: VSyncMode get() = VSyncMode(3L)
        }
    }

    @JvmInline
    value class HandleType(val value: Long) {
        companion object {
            /**
             * Display handle: - Linux (X11): `X11::Display*` for the display. - Linux (Wayland): `wl_display`
             * for the display. - Android: `EGLDisplay` for the display.
             *
             * Generated from Godot docs: DisplayServer.DISPLAY_HANDLE
             */
            val DISPLAY_HANDLE: HandleType get() = HandleType(0L)
            /**
             * Window handle: - Windows: `HWND` for the window. - Linux (X11): `X11::Window*` for the window. -
             * Linux (Wayland): `wl_surface` for the window. - macOS: `NSWindow*` for the window. - iOS:
             * `UIViewController*` for the view controller. - Android: `jObject` for the activity.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_HANDLE
             */
            val WINDOW_HANDLE: HandleType get() = HandleType(1L)
            /**
             * Window view: - Windows: `HDC` for the window (only with the Compatibility renderer). - macOS:
             * `NSView*` for the window main view. - iOS: `UIView*` for the window main view.
             *
             * Generated from Godot docs: DisplayServer.WINDOW_VIEW
             */
            val WINDOW_VIEW: HandleType get() = HandleType(2L)
            /**
             * OpenGL context (only with the Compatibility renderer): - Windows: `HGLRC` for the window (native
             * GL), or `EGLContext` for the window (ANGLE). - Linux (X11): `GLXContext*` for the window. -
             * Linux (Wayland): `EGLContext` for the window. - macOS: `NSOpenGLContext*` for the window (native
             * GL), or `EGLContext` for the window (ANGLE). - Android: `EGLContext` for the window.
             *
             * Generated from Godot docs: DisplayServer.OPENGL_CONTEXT
             */
            val OPENGL_CONTEXT: HandleType get() = HandleType(3L)
            /**
             * - Windows: `EGLDisplay` for the window (ANGLE). - macOS: `EGLDisplay` for the window (ANGLE). -
             * Linux (Wayland): `EGLDisplay` for the window.
             *
             * Generated from Godot docs: DisplayServer.EGL_DISPLAY
             */
            val EGL_DISPLAY: HandleType get() = HandleType(4L)
            /**
             * - Windows: `EGLConfig` for the window (ANGLE). - macOS: `EGLConfig` for the window (ANGLE). -
             * Linux (Wayland): `EGLConfig` for the window.
             *
             * Generated from Godot docs: DisplayServer.EGL_CONFIG
             */
            val EGL_CONFIG: HandleType get() = HandleType(5L)
            /**
             * The GLX `VisualID` for the window. Note: Only available on Linux when using X11.
             *
             * Generated from Godot docs: DisplayServer.GLX_VISUALID
             */
            val GLX_VISUALID: HandleType get() = HandleType(6L)
            /**
             * The `GLXFBConfig` for the window. Note: Only available on Linux when using X11.
             *
             * Generated from Godot docs: DisplayServer.GLX_FBCONFIG
             */
            val GLX_FBCONFIG: HandleType get() = HandleType(7L)
        }
    }

    @JvmInline
    value class TTSUtteranceEvent(val value: Long) {
        companion object {
            /**
             * Utterance has begun to be spoken.
             *
             * Generated from Godot docs: DisplayServer.TTS_UTTERANCE_STARTED
             */
            val STARTED: TTSUtteranceEvent get() = TTSUtteranceEvent(0L)
            /**
             * Utterance was successfully finished.
             *
             * Generated from Godot docs: DisplayServer.TTS_UTTERANCE_ENDED
             */
            val ENDED: TTSUtteranceEvent get() = TTSUtteranceEvent(1L)
            /**
             * Utterance was canceled, or TTS service was unable to process it.
             *
             * Generated from Godot docs: DisplayServer.TTS_UTTERANCE_CANCELED
             */
            val CANCELED: TTSUtteranceEvent get() = TTSUtteranceEvent(2L)
            /**
             * Utterance reached a word or sentence boundary.
             *
             * Generated from Godot docs: DisplayServer.TTS_UTTERANCE_BOUNDARY
             */
            val BOUNDARY: TTSUtteranceEvent get() = TTSUtteranceEvent(3L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): DisplayServer? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): DisplayServer? =
        if (handle.address() == 0L) null else this

    private const val HAS_FEATURE_HASH = 334065950L
    private val hasFeatureBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "has_feature", HAS_FEATURE_HASH)
    }

    private const val GET_NAME_HASH = 201670096L
    private val getNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_name", GET_NAME_HASH)
    }

    private const val HELP_SET_SEARCH_CALLBACKS_HASH = 1687350599L
    private val helpSetSearchCallbacksBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "help_set_search_callbacks", HELP_SET_SEARCH_CALLBACKS_HASH)
    }

    private const val GLOBAL_MENU_SET_POPUP_CALLBACKS_HASH = 3893727526L
    private val globalMenuSetPopupCallbacksBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_popup_callbacks", GLOBAL_MENU_SET_POPUP_CALLBACKS_HASH)
    }

    private const val GLOBAL_MENU_ADD_SUBMENU_ITEM_HASH = 2828985934L
    private val globalMenuAddSubmenuItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_submenu_item", GLOBAL_MENU_ADD_SUBMENU_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ITEM_HASH = 3616842746L
    private val globalMenuAddItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_item", GLOBAL_MENU_ADD_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_CHECK_ITEM_HASH = 3616842746L
    private val globalMenuAddCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_check_item", GLOBAL_MENU_ADD_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ICON_ITEM_HASH = 3867083847L
    private val globalMenuAddIconItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_item", GLOBAL_MENU_ADD_ICON_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ICON_CHECK_ITEM_HASH = 3867083847L
    private val globalMenuAddIconCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_check_item", GLOBAL_MENU_ADD_ICON_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_RADIO_CHECK_ITEM_HASH = 3616842746L
    private val globalMenuAddRadioCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_radio_check_item", GLOBAL_MENU_ADD_RADIO_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ICON_RADIO_CHECK_ITEM_HASH = 3867083847L
    private val globalMenuAddIconRadioCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_radio_check_item", GLOBAL_MENU_ADD_ICON_RADIO_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_MULTISTATE_ITEM_HASH = 3297554655L
    private val globalMenuAddMultistateItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_multistate_item", GLOBAL_MENU_ADD_MULTISTATE_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_SEPARATOR_HASH = 3214812433L
    private val globalMenuAddSeparatorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_separator", GLOBAL_MENU_ADD_SEPARATOR_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_INDEX_FROM_TEXT_HASH = 2878152881L
    private val globalMenuGetItemIndexFromTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_index_from_text", GLOBAL_MENU_GET_ITEM_INDEX_FROM_TEXT_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_INDEX_FROM_TAG_HASH = 2941063483L
    private val globalMenuGetItemIndexFromTagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_index_from_tag", GLOBAL_MENU_GET_ITEM_INDEX_FROM_TAG_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_CHECKED_HASH = 3511468594L
    private val globalMenuIsItemCheckedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_checked", GLOBAL_MENU_IS_ITEM_CHECKED_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_CHECKABLE_HASH = 3511468594L
    private val globalMenuIsItemCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_checkable", GLOBAL_MENU_IS_ITEM_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_RADIO_CHECKABLE_HASH = 3511468594L
    private val globalMenuIsItemRadioCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_radio_checkable", GLOBAL_MENU_IS_ITEM_RADIO_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_CALLBACK_HASH = 748666903L
    private val globalMenuGetItemCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_callback", GLOBAL_MENU_GET_ITEM_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH = 748666903L
    private val globalMenuGetItemKeyCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_key_callback", GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_TAG_HASH = 330672633L
    private val globalMenuGetItemTagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_tag", GLOBAL_MENU_GET_ITEM_TAG_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_TEXT_HASH = 591067909L
    private val globalMenuGetItemTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_text", GLOBAL_MENU_GET_ITEM_TEXT_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_SUBMENU_HASH = 591067909L
    private val globalMenuGetItemSubmenuBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_submenu", GLOBAL_MENU_GET_ITEM_SUBMENU_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_ACCELERATOR_HASH = 936065394L
    private val globalMenuGetItemAcceleratorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_accelerator", GLOBAL_MENU_GET_ITEM_ACCELERATOR_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_DISABLED_HASH = 3511468594L
    private val globalMenuIsItemDisabledBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_disabled", GLOBAL_MENU_IS_ITEM_DISABLED_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_HIDDEN_HASH = 3511468594L
    private val globalMenuIsItemHiddenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_hidden", GLOBAL_MENU_IS_ITEM_HIDDEN_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_TOOLTIP_HASH = 591067909L
    private val globalMenuGetItemTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_tooltip", GLOBAL_MENU_GET_ITEM_TOOLTIP_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_STATE_HASH = 3422818498L
    private val globalMenuGetItemStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_state", GLOBAL_MENU_GET_ITEM_STATE_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_MAX_STATES_HASH = 3422818498L
    private val globalMenuGetItemMaxStatesBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_max_states", GLOBAL_MENU_GET_ITEM_MAX_STATES_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_ICON_HASH = 3591713183L
    private val globalMenuGetItemIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_icon", GLOBAL_MENU_GET_ITEM_ICON_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_INDENTATION_LEVEL_HASH = 3422818498L
    private val globalMenuGetItemIndentationLevelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_indentation_level", GLOBAL_MENU_GET_ITEM_INDENTATION_LEVEL_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_CHECKED_HASH = 4108344793L
    private val globalMenuSetItemCheckedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_checked", GLOBAL_MENU_SET_ITEM_CHECKED_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_CHECKABLE_HASH = 4108344793L
    private val globalMenuSetItemCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_checkable", GLOBAL_MENU_SET_ITEM_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_RADIO_CHECKABLE_HASH = 4108344793L
    private val globalMenuSetItemRadioCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_radio_checkable", GLOBAL_MENU_SET_ITEM_RADIO_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_CALLBACK_HASH = 3809915389L
    private val globalMenuSetItemCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_callback", GLOBAL_MENU_SET_ITEM_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_HOVER_CALLBACKS_HASH = 3809915389L
    private val globalMenuSetItemHoverCallbacksBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_hover_callbacks", GLOBAL_MENU_SET_ITEM_HOVER_CALLBACKS_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_KEY_CALLBACK_HASH = 3809915389L
    private val globalMenuSetItemKeyCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_key_callback", GLOBAL_MENU_SET_ITEM_KEY_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_TAG_HASH = 453659863L
    private val globalMenuSetItemTagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_tag", GLOBAL_MENU_SET_ITEM_TAG_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_TEXT_HASH = 965966136L
    private val globalMenuSetItemTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_text", GLOBAL_MENU_SET_ITEM_TEXT_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_SUBMENU_HASH = 965966136L
    private val globalMenuSetItemSubmenuBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_submenu", GLOBAL_MENU_SET_ITEM_SUBMENU_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_ACCELERATOR_HASH = 566943293L
    private val globalMenuSetItemAcceleratorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_accelerator", GLOBAL_MENU_SET_ITEM_ACCELERATOR_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_DISABLED_HASH = 4108344793L
    private val globalMenuSetItemDisabledBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_disabled", GLOBAL_MENU_SET_ITEM_DISABLED_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_HIDDEN_HASH = 4108344793L
    private val globalMenuSetItemHiddenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_hidden", GLOBAL_MENU_SET_ITEM_HIDDEN_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_TOOLTIP_HASH = 965966136L
    private val globalMenuSetItemTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_tooltip", GLOBAL_MENU_SET_ITEM_TOOLTIP_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_STATE_HASH = 3474840532L
    private val globalMenuSetItemStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_state", GLOBAL_MENU_SET_ITEM_STATE_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_MAX_STATES_HASH = 3474840532L
    private val globalMenuSetItemMaxStatesBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_max_states", GLOBAL_MENU_SET_ITEM_MAX_STATES_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_ICON_HASH = 3201338066L
    private val globalMenuSetItemIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_icon", GLOBAL_MENU_SET_ITEM_ICON_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_INDENTATION_LEVEL_HASH = 3474840532L
    private val globalMenuSetItemIndentationLevelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_indentation_level", GLOBAL_MENU_SET_ITEM_INDENTATION_LEVEL_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_COUNT_HASH = 1321353865L
    private val globalMenuGetItemCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_count", GLOBAL_MENU_GET_ITEM_COUNT_HASH)
    }

    private const val GLOBAL_MENU_REMOVE_ITEM_HASH = 2956805083L
    private val globalMenuRemoveItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_remove_item", GLOBAL_MENU_REMOVE_ITEM_HASH)
    }

    private const val GLOBAL_MENU_CLEAR_HASH = 83702148L
    private val globalMenuClearBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_clear", GLOBAL_MENU_CLEAR_HASH)
    }

    private const val GLOBAL_MENU_GET_SYSTEM_MENU_ROOTS_HASH = 3102165223L
    private val globalMenuGetSystemMenuRootsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_system_menu_roots", GLOBAL_MENU_GET_SYSTEM_MENU_ROOTS_HASH)
    }

    private const val TTS_IS_SPEAKING_HASH = 36873697L
    private val ttsIsSpeakingBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_is_speaking", TTS_IS_SPEAKING_HASH)
    }

    private const val TTS_IS_PAUSED_HASH = 36873697L
    private val ttsIsPausedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_is_paused", TTS_IS_PAUSED_HASH)
    }

    private const val TTS_GET_VOICES_HASH = 3995934104L
    private val ttsGetVoicesBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_get_voices", TTS_GET_VOICES_HASH)
    }

    private const val TTS_GET_VOICES_FOR_LANGUAGE_HASH = 4291131558L
    private val ttsGetVoicesForLanguageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_get_voices_for_language", TTS_GET_VOICES_FOR_LANGUAGE_HASH)
    }

    private const val TTS_SPEAK_HASH = 903992738L
    private val ttsSpeakBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_speak", TTS_SPEAK_HASH)
    }

    private const val TTS_PAUSE_HASH = 3218959716L
    private val ttsPauseBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_pause", TTS_PAUSE_HASH)
    }

    private const val TTS_RESUME_HASH = 3218959716L
    private val ttsResumeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_resume", TTS_RESUME_HASH)
    }

    private const val TTS_STOP_HASH = 3218959716L
    private val ttsStopBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_stop", TTS_STOP_HASH)
    }

    private const val TTS_SET_UTTERANCE_CALLBACK_HASH = 109679083L
    private val ttsSetUtteranceCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_set_utterance_callback", TTS_SET_UTTERANCE_CALLBACK_HASH)
    }

    private const val IS_DARK_MODE_SUPPORTED_HASH = 36873697L
    private val isDarkModeSupportedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_dark_mode_supported", IS_DARK_MODE_SUPPORTED_HASH)
    }

    private const val IS_DARK_MODE_HASH = 36873697L
    private val isDarkModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_dark_mode", IS_DARK_MODE_HASH)
    }

    private const val GET_ACCENT_COLOR_HASH = 3444240500L
    private val getAccentColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_accent_color", GET_ACCENT_COLOR_HASH)
    }

    private const val GET_BASE_COLOR_HASH = 3444240500L
    private val getBaseColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_base_color", GET_BASE_COLOR_HASH)
    }

    private const val SET_SYSTEM_THEME_CHANGE_CALLBACK_HASH = 1611583062L
    private val setSystemThemeChangeCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_system_theme_change_callback", SET_SYSTEM_THEME_CHANGE_CALLBACK_HASH)
    }

    private const val MOUSE_SET_MODE_HASH = 348288463L
    private val mouseSetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_set_mode", MOUSE_SET_MODE_HASH)
    }

    private const val MOUSE_GET_MODE_HASH = 1353961651L
    private val mouseGetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_get_mode", MOUSE_GET_MODE_HASH)
    }

    private const val WARP_MOUSE_HASH = 1130785943L
    private val warpMouseBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "warp_mouse", WARP_MOUSE_HASH)
    }

    private const val MOUSE_GET_POSITION_HASH = 3690982128L
    private val mouseGetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_get_position", MOUSE_GET_POSITION_HASH)
    }

    private const val MOUSE_GET_BUTTON_STATE_HASH = 2512161324L
    private val mouseGetButtonStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_get_button_state", MOUSE_GET_BUTTON_STATE_HASH)
    }

    private const val CLIPBOARD_SET_HASH = 83702148L
    private val clipboardSetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_set", CLIPBOARD_SET_HASH)
    }

    private const val CLIPBOARD_GET_HASH = 201670096L
    private val clipboardGetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_get", CLIPBOARD_GET_HASH)
    }

    private const val CLIPBOARD_GET_IMAGE_HASH = 4190603485L
    private val clipboardGetImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_get_image", CLIPBOARD_GET_IMAGE_HASH)
    }

    private const val CLIPBOARD_HAS_HASH = 36873697L
    private val clipboardHasBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_has", CLIPBOARD_HAS_HASH)
    }

    private const val CLIPBOARD_HAS_IMAGE_HASH = 36873697L
    private val clipboardHasImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_has_image", CLIPBOARD_HAS_IMAGE_HASH)
    }

    private const val CLIPBOARD_SET_PRIMARY_HASH = 83702148L
    private val clipboardSetPrimaryBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_set_primary", CLIPBOARD_SET_PRIMARY_HASH)
    }

    private const val CLIPBOARD_GET_PRIMARY_HASH = 201670096L
    private val clipboardGetPrimaryBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_get_primary", CLIPBOARD_GET_PRIMARY_HASH)
    }

    private const val GET_DISPLAY_CUTOUTS_HASH = 3995934104L
    private val getDisplayCutoutsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_display_cutouts", GET_DISPLAY_CUTOUTS_HASH)
    }

    private const val GET_DISPLAY_SAFE_AREA_HASH = 410525958L
    private val getDisplaySafeAreaBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_display_safe_area", GET_DISPLAY_SAFE_AREA_HASH)
    }

    private const val GET_SCREEN_COUNT_HASH = 3905245786L
    private val getScreenCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_screen_count", GET_SCREEN_COUNT_HASH)
    }

    private const val GET_PRIMARY_SCREEN_HASH = 3905245786L
    private val getPrimaryScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_primary_screen", GET_PRIMARY_SCREEN_HASH)
    }

    private const val GET_KEYBOARD_FOCUS_SCREEN_HASH = 3905245786L
    private val getKeyboardFocusScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_keyboard_focus_screen", GET_KEYBOARD_FOCUS_SCREEN_HASH)
    }

    private const val GET_SCREEN_FROM_RECT_HASH = 741354659L
    private val getScreenFromRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_screen_from_rect", GET_SCREEN_FROM_RECT_HASH)
    }

    private const val SCREEN_GET_POSITION_HASH = 1725937825L
    private val screenGetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_position", SCREEN_GET_POSITION_HASH)
    }

    private const val SCREEN_GET_SIZE_HASH = 1725937825L
    private val screenGetSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_size", SCREEN_GET_SIZE_HASH)
    }

    private const val SCREEN_GET_USABLE_RECT_HASH = 2439012528L
    private val screenGetUsableRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_usable_rect", SCREEN_GET_USABLE_RECT_HASH)
    }

    private const val SCREEN_GET_DPI_HASH = 181039630L
    private val screenGetDpiBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_dpi", SCREEN_GET_DPI_HASH)
    }

    private const val SCREEN_GET_SCALE_HASH = 909105437L
    private val screenGetScaleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_scale", SCREEN_GET_SCALE_HASH)
    }

    private const val IS_TOUCHSCREEN_AVAILABLE_HASH = 36873697L
    private val isTouchscreenAvailableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_touchscreen_available", IS_TOUCHSCREEN_AVAILABLE_HASH)
    }

    private const val SCREEN_GET_MAX_SCALE_HASH = 1740695150L
    private val screenGetMaxScaleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_max_scale", SCREEN_GET_MAX_SCALE_HASH)
    }

    private const val SCREEN_GET_REFRESH_RATE_HASH = 909105437L
    private val screenGetRefreshRateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_refresh_rate", SCREEN_GET_REFRESH_RATE_HASH)
    }

    private const val SCREEN_GET_PIXEL_HASH = 1532707496L
    private val screenGetPixelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_pixel", SCREEN_GET_PIXEL_HASH)
    }

    private const val SCREEN_GET_IMAGE_HASH = 3813388802L
    private val screenGetImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_image", SCREEN_GET_IMAGE_HASH)
    }

    private const val SCREEN_GET_IMAGE_RECT_HASH = 2601441065L
    private val screenGetImageRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_image_rect", SCREEN_GET_IMAGE_RECT_HASH)
    }

    private const val SCREEN_SET_ORIENTATION_HASH = 2211511631L
    private val screenSetOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_set_orientation", SCREEN_SET_ORIENTATION_HASH)
    }

    private const val SCREEN_GET_ORIENTATION_HASH = 133818562L
    private val screenGetOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_orientation", SCREEN_GET_ORIENTATION_HASH)
    }

    private const val SCREEN_SET_KEEP_ON_HASH = 2586408642L
    private val screenSetKeepOnBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_set_keep_on", SCREEN_SET_KEEP_ON_HASH)
    }

    private const val SCREEN_IS_KEPT_ON_HASH = 36873697L
    private val screenIsKeptOnBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_is_kept_on", SCREEN_IS_KEPT_ON_HASH)
    }

    private const val GET_WINDOW_LIST_HASH = 1930428628L
    private val getWindowListBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_window_list", GET_WINDOW_LIST_HASH)
    }

    private const val GET_WINDOW_AT_SCREEN_POSITION_HASH = 2485466453L
    private val getWindowAtScreenPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_window_at_screen_position", GET_WINDOW_AT_SCREEN_POSITION_HASH)
    }

    private const val WINDOW_GET_NATIVE_HANDLE_HASH = 1096425680L
    private val windowGetNativeHandleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_native_handle", WINDOW_GET_NATIVE_HANDLE_HASH)
    }

    private const val WINDOW_GET_ACTIVE_POPUP_HASH = 3905245786L
    private val windowGetActivePopupBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_active_popup", WINDOW_GET_ACTIVE_POPUP_HASH)
    }

    private const val WINDOW_SET_POPUP_SAFE_RECT_HASH = 3317281434L
    private val windowSetPopupSafeRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_popup_safe_rect", WINDOW_SET_POPUP_SAFE_RECT_HASH)
    }

    private const val WINDOW_GET_POPUP_SAFE_RECT_HASH = 2161169500L
    private val windowGetPopupSafeRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_popup_safe_rect", WINDOW_GET_POPUP_SAFE_RECT_HASH)
    }

    private const val WINDOW_SET_TITLE_HASH = 441246282L
    private val windowSetTitleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_title", WINDOW_SET_TITLE_HASH)
    }

    private const val WINDOW_GET_TITLE_SIZE_HASH = 2925301799L
    private val windowGetTitleSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_title_size", WINDOW_GET_TITLE_SIZE_HASH)
    }

    private const val WINDOW_SET_MOUSE_PASSTHROUGH_HASH = 1993637420L
    private val windowSetMousePassthroughBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_mouse_passthrough", WINDOW_SET_MOUSE_PASSTHROUGH_HASH)
    }

    private const val WINDOW_GET_CURRENT_SCREEN_HASH = 1591665591L
    private val windowGetCurrentScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_current_screen", WINDOW_GET_CURRENT_SCREEN_HASH)
    }

    private const val WINDOW_SET_CURRENT_SCREEN_HASH = 2230941749L
    private val windowSetCurrentScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_current_screen", WINDOW_SET_CURRENT_SCREEN_HASH)
    }

    private const val WINDOW_GET_POSITION_HASH = 763922886L
    private val windowGetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_position", WINDOW_GET_POSITION_HASH)
    }

    private const val WINDOW_GET_POSITION_WITH_DECORATIONS_HASH = 763922886L
    private val windowGetPositionWithDecorationsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_position_with_decorations", WINDOW_GET_POSITION_WITH_DECORATIONS_HASH)
    }

    private const val WINDOW_SET_POSITION_HASH = 2019273902L
    private val windowSetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_position", WINDOW_SET_POSITION_HASH)
    }

    private const val WINDOW_GET_SIZE_HASH = 763922886L
    private val windowGetSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_size", WINDOW_GET_SIZE_HASH)
    }

    private const val WINDOW_SET_SIZE_HASH = 2019273902L
    private val windowSetSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_size", WINDOW_SET_SIZE_HASH)
    }

    private const val WINDOW_SET_RECT_CHANGED_CALLBACK_HASH = 1091192925L
    private val windowSetRectChangedCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_rect_changed_callback", WINDOW_SET_RECT_CHANGED_CALLBACK_HASH)
    }

    private const val WINDOW_SET_WINDOW_EVENT_CALLBACK_HASH = 1091192925L
    private val windowSetWindowEventCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_window_event_callback", WINDOW_SET_WINDOW_EVENT_CALLBACK_HASH)
    }

    private const val WINDOW_SET_INPUT_EVENT_CALLBACK_HASH = 1091192925L
    private val windowSetInputEventCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_input_event_callback", WINDOW_SET_INPUT_EVENT_CALLBACK_HASH)
    }

    private const val WINDOW_SET_INPUT_TEXT_CALLBACK_HASH = 1091192925L
    private val windowSetInputTextCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_input_text_callback", WINDOW_SET_INPUT_TEXT_CALLBACK_HASH)
    }

    private const val WINDOW_SET_DROP_FILES_CALLBACK_HASH = 1091192925L
    private val windowSetDropFilesCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_drop_files_callback", WINDOW_SET_DROP_FILES_CALLBACK_HASH)
    }

    private const val WINDOW_GET_ATTACHED_INSTANCE_ID_HASH = 1591665591L
    private val windowGetAttachedInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_attached_instance_id", WINDOW_GET_ATTACHED_INSTANCE_ID_HASH)
    }

    private const val WINDOW_GET_MAX_SIZE_HASH = 763922886L
    private val windowGetMaxSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_max_size", WINDOW_GET_MAX_SIZE_HASH)
    }

    private const val WINDOW_SET_MAX_SIZE_HASH = 2019273902L
    private val windowSetMaxSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_max_size", WINDOW_SET_MAX_SIZE_HASH)
    }

    private const val WINDOW_GET_MIN_SIZE_HASH = 763922886L
    private val windowGetMinSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_min_size", WINDOW_GET_MIN_SIZE_HASH)
    }

    private const val WINDOW_SET_MIN_SIZE_HASH = 2019273902L
    private val windowSetMinSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_min_size", WINDOW_SET_MIN_SIZE_HASH)
    }

    private const val WINDOW_GET_SIZE_WITH_DECORATIONS_HASH = 763922886L
    private val windowGetSizeWithDecorationsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_size_with_decorations", WINDOW_GET_SIZE_WITH_DECORATIONS_HASH)
    }

    private const val WINDOW_GET_MODE_HASH = 2185728461L
    private val windowGetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_mode", WINDOW_GET_MODE_HASH)
    }

    private const val WINDOW_SET_MODE_HASH = 1319965401L
    private val windowSetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_mode", WINDOW_SET_MODE_HASH)
    }

    private const val WINDOW_SET_FLAG_HASH = 254894155L
    private val windowSetFlagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_flag", WINDOW_SET_FLAG_HASH)
    }

    private const val WINDOW_GET_FLAG_HASH = 802816991L
    private val windowGetFlagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_flag", WINDOW_GET_FLAG_HASH)
    }

    private const val WINDOW_SET_ICON_HASH = 2457502155L
    private val windowSetIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_icon", WINDOW_SET_ICON_HASH)
    }

    private const val WINDOW_SET_WINDOW_BUTTONS_OFFSET_HASH = 2019273902L
    private val windowSetWindowButtonsOffsetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_window_buttons_offset", WINDOW_SET_WINDOW_BUTTONS_OFFSET_HASH)
    }

    private const val WINDOW_GET_SAFE_TITLE_MARGINS_HASH = 2295066620L
    private val windowGetSafeTitleMarginsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_safe_title_margins", WINDOW_GET_SAFE_TITLE_MARGINS_HASH)
    }

    private const val WINDOW_REQUEST_ATTENTION_HASH = 1995695955L
    private val windowRequestAttentionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_request_attention", WINDOW_REQUEST_ATTENTION_HASH)
    }

    private const val WINDOW_SET_TASKBAR_PROGRESS_VALUE_HASH = 3506631519L
    private val windowSetTaskbarProgressValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_taskbar_progress_value", WINDOW_SET_TASKBAR_PROGRESS_VALUE_HASH)
    }

    private const val WINDOW_SET_TASKBAR_PROGRESS_STATE_HASH = 4119882768L
    private val windowSetTaskbarProgressStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_taskbar_progress_state", WINDOW_SET_TASKBAR_PROGRESS_STATE_HASH)
    }

    private const val WINDOW_MOVE_TO_FOREGROUND_HASH = 1995695955L
    private val windowMoveToForegroundBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_move_to_foreground", WINDOW_MOVE_TO_FOREGROUND_HASH)
    }

    private const val WINDOW_IS_FOCUSED_HASH = 1051549951L
    private val windowIsFocusedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_focused", WINDOW_IS_FOCUSED_HASH)
    }

    private const val WINDOW_CAN_DRAW_HASH = 1051549951L
    private val windowCanDrawBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_can_draw", WINDOW_CAN_DRAW_HASH)
    }

    private const val WINDOW_SET_TRANSIENT_HASH = 3937882851L
    private val windowSetTransientBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_transient", WINDOW_SET_TRANSIENT_HASH)
    }

    private const val WINDOW_SET_EXCLUSIVE_HASH = 300928843L
    private val windowSetExclusiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_exclusive", WINDOW_SET_EXCLUSIVE_HASH)
    }

    private const val WINDOW_SET_IME_ACTIVE_HASH = 1661950165L
    private val windowSetImeActiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_ime_active", WINDOW_SET_IME_ACTIVE_HASH)
    }

    private const val WINDOW_SET_IME_POSITION_HASH = 2019273902L
    private val windowSetImePositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_ime_position", WINDOW_SET_IME_POSITION_HASH)
    }

    private const val WINDOW_SET_VSYNC_MODE_HASH = 2179333492L
    private val windowSetVsyncModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_vsync_mode", WINDOW_SET_VSYNC_MODE_HASH)
    }

    private const val WINDOW_GET_VSYNC_MODE_HASH = 578873795L
    private val windowGetVsyncModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_vsync_mode", WINDOW_GET_VSYNC_MODE_HASH)
    }

    private const val WINDOW_IS_HDR_OUTPUT_SUPPORTED_HASH = 1051549951L
    private val windowIsHdrOutputSupportedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_hdr_output_supported", WINDOW_IS_HDR_OUTPUT_SUPPORTED_HASH)
    }

    private const val WINDOW_REQUEST_HDR_OUTPUT_HASH = 1661950165L
    private val windowRequestHdrOutputBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_request_hdr_output", WINDOW_REQUEST_HDR_OUTPUT_HASH)
    }

    private const val WINDOW_IS_HDR_OUTPUT_REQUESTED_HASH = 1051549951L
    private val windowIsHdrOutputRequestedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_hdr_output_requested", WINDOW_IS_HDR_OUTPUT_REQUESTED_HASH)
    }

    private const val WINDOW_IS_HDR_OUTPUT_ENABLED_HASH = 1051549951L
    private val windowIsHdrOutputEnabledBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_hdr_output_enabled", WINDOW_IS_HDR_OUTPUT_ENABLED_HASH)
    }

    private const val WINDOW_SET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH = 3506631519L
    private val windowSetHdrOutputReferenceLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_hdr_output_reference_luminance", WINDOW_SET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputReferenceLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_reference_luminance", WINDOW_GET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_CURRENT_REFERENCE_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputCurrentReferenceLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_current_reference_luminance", WINDOW_GET_HDR_OUTPUT_CURRENT_REFERENCE_LUMINANCE_HASH)
    }

    private const val WINDOW_SET_HDR_OUTPUT_MAX_LUMINANCE_HASH = 3506631519L
    private val windowSetHdrOutputMaxLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_hdr_output_max_luminance", WINDOW_SET_HDR_OUTPUT_MAX_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_MAX_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputMaxLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_max_luminance", WINDOW_GET_HDR_OUTPUT_MAX_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_CURRENT_MAX_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputCurrentMaxLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_current_max_luminance", WINDOW_GET_HDR_OUTPUT_CURRENT_MAX_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_OUTPUT_MAX_LINEAR_VALUE_HASH = 218038398L
    private val windowGetOutputMaxLinearValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_output_max_linear_value", WINDOW_GET_OUTPUT_MAX_LINEAR_VALUE_HASH)
    }

    private const val WINDOW_IS_MAXIMIZE_ALLOWED_HASH = 1051549951L
    private val windowIsMaximizeAllowedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_maximize_allowed", WINDOW_IS_MAXIMIZE_ALLOWED_HASH)
    }

    private const val WINDOW_MAXIMIZE_ON_TITLE_DBL_CLICK_HASH = 36873697L
    private val windowMaximizeOnTitleDblClickBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_maximize_on_title_dbl_click", WINDOW_MAXIMIZE_ON_TITLE_DBL_CLICK_HASH)
    }

    private const val WINDOW_MINIMIZE_ON_TITLE_DBL_CLICK_HASH = 36873697L
    private val windowMinimizeOnTitleDblClickBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_minimize_on_title_dbl_click", WINDOW_MINIMIZE_ON_TITLE_DBL_CLICK_HASH)
    }

    private const val WINDOW_START_DRAG_HASH = 1995695955L
    private val windowStartDragBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_start_drag", WINDOW_START_DRAG_HASH)
    }

    private const val WINDOW_START_RESIZE_HASH = 4009722312L
    private val windowStartResizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_start_resize", WINDOW_START_RESIZE_HASH)
    }

    private const val WINDOW_SET_COLOR_HASH = 2920490490L
    private val windowSetColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_color", WINDOW_SET_COLOR_HASH)
    }

    private const val ACCESSIBILITY_SHOULD_INCREASE_CONTRAST_HASH = 3905245786L
    private val accessibilityShouldIncreaseContrastBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_should_increase_contrast", ACCESSIBILITY_SHOULD_INCREASE_CONTRAST_HASH)
    }

    private const val ACCESSIBILITY_SHOULD_REDUCE_ANIMATION_HASH = 3905245786L
    private val accessibilityShouldReduceAnimationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_should_reduce_animation", ACCESSIBILITY_SHOULD_REDUCE_ANIMATION_HASH)
    }

    private const val ACCESSIBILITY_SHOULD_REDUCE_TRANSPARENCY_HASH = 3905245786L
    private val accessibilityShouldReduceTransparencyBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_should_reduce_transparency", ACCESSIBILITY_SHOULD_REDUCE_TRANSPARENCY_HASH)
    }

    private const val ACCESSIBILITY_SCREEN_READER_ACTIVE_HASH = 3905245786L
    private val accessibilityScreenReaderActiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_screen_reader_active", ACCESSIBILITY_SCREEN_READER_ACTIVE_HASH)
    }

    private const val ACCESSIBILITY_CREATE_ELEMENT_HASH = 2968347744L
    private val accessibilityCreateElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_create_element", ACCESSIBILITY_CREATE_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_CREATE_SUB_ELEMENT_HASH = 1949948826L
    private val accessibilityCreateSubElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_create_sub_element", ACCESSIBILITY_CREATE_SUB_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_CREATE_SUB_TEXT_EDIT_ELEMENTS_HASH = 2702009895L
    private val accessibilityCreateSubTextEditElementsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_create_sub_text_edit_elements", ACCESSIBILITY_CREATE_SUB_TEXT_EDIT_ELEMENTS_HASH)
    }

    private const val ACCESSIBILITY_HAS_ELEMENT_HASH = 4155700596L
    private val accessibilityHasElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_has_element", ACCESSIBILITY_HAS_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_FREE_ELEMENT_HASH = 2722037293L
    private val accessibilityFreeElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_free_element", ACCESSIBILITY_FREE_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_ELEMENT_SET_META_HASH = 3175752987L
    private val accessibilityElementSetMetaBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_element_set_meta", ACCESSIBILITY_ELEMENT_SET_META_HASH)
    }

    private const val ACCESSIBILITY_ELEMENT_GET_META_HASH = 4171304767L
    private val accessibilityElementGetMetaBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_element_get_meta", ACCESSIBILITY_ELEMENT_GET_META_HASH)
    }

    private const val ACCESSIBILITY_SET_WINDOW_RECT_HASH = 2386961724L
    private val accessibilitySetWindowRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_set_window_rect", ACCESSIBILITY_SET_WINDOW_RECT_HASH)
    }

    private const val ACCESSIBILITY_SET_WINDOW_FOCUSED_HASH = 300928843L
    private val accessibilitySetWindowFocusedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_set_window_focused", ACCESSIBILITY_SET_WINDOW_FOCUSED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_FOCUS_HASH = 2722037293L
    private val accessibilityUpdateSetFocusBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_focus", ACCESSIBILITY_UPDATE_SET_FOCUS_HASH)
    }

    private const val ACCESSIBILITY_GET_WINDOW_ROOT_HASH = 495598643L
    private val accessibilityGetWindowRootBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_get_window_root", ACCESSIBILITY_GET_WINDOW_ROOT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ROLE_HASH = 3352768215L
    private val accessibilityUpdateSetRoleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_role", ACCESSIBILITY_UPDATE_SET_ROLE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NAME_HASH = 2726140452L
    private val accessibilityUpdateSetNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_name", ACCESSIBILITY_UPDATE_SET_NAME_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_EXTRA_INFO_HASH = 2726140452L
    private val accessibilityUpdateSetExtraInfoBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_extra_info", ACCESSIBILITY_UPDATE_SET_EXTRA_INFO_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_DESCRIPTION_HASH = 2726140452L
    private val accessibilityUpdateSetDescriptionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_description", ACCESSIBILITY_UPDATE_SET_DESCRIPTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_VALUE_HASH = 2726140452L
    private val accessibilityUpdateSetValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_value", ACCESSIBILITY_UPDATE_SET_VALUE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TOOLTIP_HASH = 2726140452L
    private val accessibilityUpdateSetTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_tooltip", ACCESSIBILITY_UPDATE_SET_TOOLTIP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_BOUNDS_HASH = 1378122625L
    private val accessibilityUpdateSetBoundsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_bounds", ACCESSIBILITY_UPDATE_SET_BOUNDS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TRANSFORM_HASH = 1246044741L
    private val accessibilityUpdateSetTransformBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_transform", ACCESSIBILITY_UPDATE_SET_TRANSFORM_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_CHILD_HASH = 395945892L
    private val accessibilityUpdateAddChildBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_child", ACCESSIBILITY_UPDATE_ADD_CHILD_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_CONTROLS_HASH = 395945892L
    private val accessibilityUpdateAddRelatedControlsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_controls", ACCESSIBILITY_UPDATE_ADD_RELATED_CONTROLS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_DETAILS_HASH = 395945892L
    private val accessibilityUpdateAddRelatedDetailsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_details", ACCESSIBILITY_UPDATE_ADD_RELATED_DETAILS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_DESCRIBED_BY_HASH = 395945892L
    private val accessibilityUpdateAddRelatedDescribedByBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_described_by", ACCESSIBILITY_UPDATE_ADD_RELATED_DESCRIBED_BY_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_FLOW_TO_HASH = 395945892L
    private val accessibilityUpdateAddRelatedFlowToBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_flow_to", ACCESSIBILITY_UPDATE_ADD_RELATED_FLOW_TO_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_LABELED_BY_HASH = 395945892L
    private val accessibilityUpdateAddRelatedLabeledByBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_labeled_by", ACCESSIBILITY_UPDATE_ADD_RELATED_LABELED_BY_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_RADIO_GROUP_HASH = 395945892L
    private val accessibilityUpdateAddRelatedRadioGroupBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_radio_group", ACCESSIBILITY_UPDATE_ADD_RELATED_RADIO_GROUP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ACTIVE_DESCENDANT_HASH = 395945892L
    private val accessibilityUpdateSetActiveDescendantBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_active_descendant", ACCESSIBILITY_UPDATE_SET_ACTIVE_DESCENDANT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NEXT_ON_LINE_HASH = 395945892L
    private val accessibilityUpdateSetNextOnLineBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_next_on_line", ACCESSIBILITY_UPDATE_SET_NEXT_ON_LINE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_PREVIOUS_ON_LINE_HASH = 395945892L
    private val accessibilityUpdateSetPreviousOnLineBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_previous_on_line", ACCESSIBILITY_UPDATE_SET_PREVIOUS_ON_LINE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_MEMBER_OF_HASH = 395945892L
    private val accessibilityUpdateSetMemberOfBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_member_of", ACCESSIBILITY_UPDATE_SET_MEMBER_OF_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_IN_PAGE_LINK_TARGET_HASH = 395945892L
    private val accessibilityUpdateSetInPageLinkTargetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_in_page_link_target", ACCESSIBILITY_UPDATE_SET_IN_PAGE_LINK_TARGET_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ERROR_MESSAGE_HASH = 395945892L
    private val accessibilityUpdateSetErrorMessageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_error_message", ACCESSIBILITY_UPDATE_SET_ERROR_MESSAGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIVE_HASH = 2683302212L
    private val accessibilityUpdateSetLiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_live", ACCESSIBILITY_UPDATE_SET_LIVE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_ACTION_HASH = 2898696987L
    private val accessibilityUpdateAddActionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_action", ACCESSIBILITY_UPDATE_ADD_ACTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_CUSTOM_ACTION_HASH = 4153150897L
    private val accessibilityUpdateAddCustomActionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_custom_action", ACCESSIBILITY_UPDATE_ADD_CUSTOM_ACTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_ROW_COUNT_HASH = 3411492887L
    private val accessibilityUpdateSetTableRowCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_row_count", ACCESSIBILITY_UPDATE_SET_TABLE_ROW_COUNT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_COUNT_HASH = 3411492887L
    private val accessibilityUpdateSetTableColumnCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_column_count", ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_COUNT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_ROW_INDEX_HASH = 3411492887L
    private val accessibilityUpdateSetTableRowIndexBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_row_index", ACCESSIBILITY_UPDATE_SET_TABLE_ROW_INDEX_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_INDEX_HASH = 3411492887L
    private val accessibilityUpdateSetTableColumnIndexBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_column_index", ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_INDEX_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_CELL_POSITION_HASH = 4288446313L
    private val accessibilityUpdateSetTableCellPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_cell_position", ACCESSIBILITY_UPDATE_SET_TABLE_CELL_POSITION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_CELL_SPAN_HASH = 4288446313L
    private val accessibilityUpdateSetTableCellSpanBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_cell_span", ACCESSIBILITY_UPDATE_SET_TABLE_CELL_SPAN_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_COUNT_HASH = 3411492887L
    private val accessibilityUpdateSetListItemCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_count", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_COUNT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_INDEX_HASH = 3411492887L
    private val accessibilityUpdateSetListItemIndexBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_index", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_INDEX_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_LEVEL_HASH = 3411492887L
    private val accessibilityUpdateSetListItemLevelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_level", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_LEVEL_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_SELECTED_HASH = 1265174801L
    private val accessibilityUpdateSetListItemSelectedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_selected", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_SELECTED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_EXPANDED_HASH = 1265174801L
    private val accessibilityUpdateSetListItemExpandedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_expanded", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_EXPANDED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_POPUP_TYPE_HASH = 2040885448L
    private val accessibilityUpdateSetPopupTypeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_popup_type", ACCESSIBILITY_UPDATE_SET_POPUP_TYPE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_CHECKED_HASH = 1265174801L
    private val accessibilityUpdateSetCheckedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_checked", ACCESSIBILITY_UPDATE_SET_CHECKED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_VALUE_HASH = 1794382983L
    private val accessibilityUpdateSetNumValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_value", ACCESSIBILITY_UPDATE_SET_NUM_VALUE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_RANGE_HASH = 2513314492L
    private val accessibilityUpdateSetNumRangeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_range", ACCESSIBILITY_UPDATE_SET_NUM_RANGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_STEP_HASH = 1794382983L
    private val accessibilityUpdateSetNumStepBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_step", ACCESSIBILITY_UPDATE_SET_NUM_STEP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_JUMP_HASH = 1794382983L
    private val accessibilityUpdateSetNumJumpBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_jump", ACCESSIBILITY_UPDATE_SET_NUM_JUMP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_X_HASH = 1794382983L
    private val accessibilityUpdateSetScrollXBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_x", ACCESSIBILITY_UPDATE_SET_SCROLL_X_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_X_RANGE_HASH = 2513314492L
    private val accessibilityUpdateSetScrollXRangeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_x_range", ACCESSIBILITY_UPDATE_SET_SCROLL_X_RANGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_Y_HASH = 1794382983L
    private val accessibilityUpdateSetScrollYBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_y", ACCESSIBILITY_UPDATE_SET_SCROLL_Y_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_Y_RANGE_HASH = 2513314492L
    private val accessibilityUpdateSetScrollYRangeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_y_range", ACCESSIBILITY_UPDATE_SET_SCROLL_Y_RANGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_DECORATIONS_HASH = 1672422386L
    private val accessibilityUpdateSetTextDecorationsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_decorations", ACCESSIBILITY_UPDATE_SET_TEXT_DECORATIONS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_ALIGN_HASH = 3725995085L
    private val accessibilityUpdateSetTextAlignBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_align", ACCESSIBILITY_UPDATE_SET_TEXT_ALIGN_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_SELECTION_HASH = 3119144029L
    private val accessibilityUpdateSetTextSelectionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_selection", ACCESSIBILITY_UPDATE_SET_TEXT_SELECTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_FLAG_HASH = 3758675396L
    private val accessibilityUpdateSetFlagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_flag", ACCESSIBILITY_UPDATE_SET_FLAG_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_CLASSNAME_HASH = 2726140452L
    private val accessibilityUpdateSetClassnameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_classname", ACCESSIBILITY_UPDATE_SET_CLASSNAME_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_PLACEHOLDER_HASH = 2726140452L
    private val accessibilityUpdateSetPlaceholderBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_placeholder", ACCESSIBILITY_UPDATE_SET_PLACEHOLDER_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LANGUAGE_HASH = 2726140452L
    private val accessibilityUpdateSetLanguageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_language", ACCESSIBILITY_UPDATE_SET_LANGUAGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_ORIENTATION_HASH = 1265174801L
    private val accessibilityUpdateSetTextOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_orientation", ACCESSIBILITY_UPDATE_SET_TEXT_ORIENTATION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ORIENTATION_HASH = 1265174801L
    private val accessibilityUpdateSetListOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_orientation", ACCESSIBILITY_UPDATE_SET_LIST_ORIENTATION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SHORTCUT_HASH = 2726140452L
    private val accessibilityUpdateSetShortcutBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_shortcut", ACCESSIBILITY_UPDATE_SET_SHORTCUT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_URL_HASH = 2726140452L
    private val accessibilityUpdateSetUrlBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_url", ACCESSIBILITY_UPDATE_SET_URL_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ROLE_DESCRIPTION_HASH = 2726140452L
    private val accessibilityUpdateSetRoleDescriptionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_role_description", ACCESSIBILITY_UPDATE_SET_ROLE_DESCRIPTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_STATE_DESCRIPTION_HASH = 2726140452L
    private val accessibilityUpdateSetStateDescriptionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_state_description", ACCESSIBILITY_UPDATE_SET_STATE_DESCRIPTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_COLOR_VALUE_HASH = 2948539648L
    private val accessibilityUpdateSetColorValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_color_value", ACCESSIBILITY_UPDATE_SET_COLOR_VALUE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_BACKGROUND_COLOR_HASH = 2948539648L
    private val accessibilityUpdateSetBackgroundColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_background_color", ACCESSIBILITY_UPDATE_SET_BACKGROUND_COLOR_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_FOREGROUND_COLOR_HASH = 2948539648L
    private val accessibilityUpdateSetForegroundColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_foreground_color", ACCESSIBILITY_UPDATE_SET_FOREGROUND_COLOR_HASH)
    }

    private const val IME_GET_SELECTION_HASH = 3690982128L
    private val imeGetSelectionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "ime_get_selection", IME_GET_SELECTION_HASH)
    }

    private const val IME_GET_TEXT_HASH = 201670096L
    private val imeGetTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "ime_get_text", IME_GET_TEXT_HASH)
    }

    private const val VIRTUAL_KEYBOARD_SHOW_HASH = 3042891259L
    private val virtualKeyboardShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "virtual_keyboard_show", VIRTUAL_KEYBOARD_SHOW_HASH)
    }

    private const val VIRTUAL_KEYBOARD_HIDE_HASH = 3218959716L
    private val virtualKeyboardHideBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "virtual_keyboard_hide", VIRTUAL_KEYBOARD_HIDE_HASH)
    }

    private const val VIRTUAL_KEYBOARD_GET_HEIGHT_HASH = 3905245786L
    private val virtualKeyboardGetHeightBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "virtual_keyboard_get_height", VIRTUAL_KEYBOARD_GET_HEIGHT_HASH)
    }

    private const val HAS_HARDWARE_KEYBOARD_HASH = 36873697L
    private val hasHardwareKeyboardBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "has_hardware_keyboard", HAS_HARDWARE_KEYBOARD_HASH)
    }

    private const val SET_HARDWARE_KEYBOARD_CONNECTION_CHANGE_CALLBACK_HASH = 1611583062L
    private val setHardwareKeyboardConnectionChangeCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_hardware_keyboard_connection_change_callback", SET_HARDWARE_KEYBOARD_CONNECTION_CHANGE_CALLBACK_HASH)
    }

    private const val CURSOR_SET_SHAPE_HASH = 2026291549L
    private val cursorSetShapeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "cursor_set_shape", CURSOR_SET_SHAPE_HASH)
    }

    private const val CURSOR_GET_SHAPE_HASH = 1087724927L
    private val cursorGetShapeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "cursor_get_shape", CURSOR_GET_SHAPE_HASH)
    }

    private const val CURSOR_SET_CUSTOM_IMAGE_HASH = 1816663697L
    private val cursorSetCustomImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "cursor_set_custom_image", CURSOR_SET_CUSTOM_IMAGE_HASH)
    }

    private const val GET_SWAP_CANCEL_OK_HASH = 2240911060L
    private val getSwapCancelOkBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_swap_cancel_ok", GET_SWAP_CANCEL_OK_HASH)
    }

    private const val ENABLE_FOR_STEALING_FOCUS_HASH = 1286410249L
    private val enableForStealingFocusBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "enable_for_stealing_focus", ENABLE_FOR_STEALING_FOCUS_HASH)
    }

    private const val DIALOG_SHOW_HASH = 4115553226L
    private val dialogShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "dialog_show", DIALOG_SHOW_HASH)
    }

    private const val DIALOG_INPUT_TEXT_HASH = 3088703427L
    private val dialogInputTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "dialog_input_text", DIALOG_INPUT_TEXT_HASH)
    }

    private const val FILE_DIALOG_SHOW_HASH = 1386825884L
    private val fileDialogShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "file_dialog_show", FILE_DIALOG_SHOW_HASH)
    }

    private const val FILE_DIALOG_WITH_OPTIONS_SHOW_HASH = 1448789813L
    private val fileDialogWithOptionsShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "file_dialog_with_options_show", FILE_DIALOG_WITH_OPTIONS_SHOW_HASH)
    }

    private const val BEEP_HASH = 4051624405L
    private val beepBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "beep", BEEP_HASH)
    }

    private const val KEYBOARD_GET_LAYOUT_COUNT_HASH = 3905245786L
    private val keyboardGetLayoutCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_layout_count", KEYBOARD_GET_LAYOUT_COUNT_HASH)
    }

    private const val KEYBOARD_GET_CURRENT_LAYOUT_HASH = 3905245786L
    private val keyboardGetCurrentLayoutBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_current_layout", KEYBOARD_GET_CURRENT_LAYOUT_HASH)
    }

    private const val KEYBOARD_SET_CURRENT_LAYOUT_HASH = 1286410249L
    private val keyboardSetCurrentLayoutBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_set_current_layout", KEYBOARD_SET_CURRENT_LAYOUT_HASH)
    }

    private const val KEYBOARD_GET_LAYOUT_LANGUAGE_HASH = 844755477L
    private val keyboardGetLayoutLanguageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_layout_language", KEYBOARD_GET_LAYOUT_LANGUAGE_HASH)
    }

    private const val KEYBOARD_GET_LAYOUT_NAME_HASH = 844755477L
    private val keyboardGetLayoutNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_layout_name", KEYBOARD_GET_LAYOUT_NAME_HASH)
    }

    private const val KEYBOARD_GET_KEYCODE_FROM_PHYSICAL_HASH = 3447613187L
    private val keyboardGetKeycodeFromPhysicalBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_keycode_from_physical", KEYBOARD_GET_KEYCODE_FROM_PHYSICAL_HASH)
    }

    private const val KEYBOARD_GET_LABEL_FROM_PHYSICAL_HASH = 3447613187L
    private val keyboardGetLabelFromPhysicalBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_label_from_physical", KEYBOARD_GET_LABEL_FROM_PHYSICAL_HASH)
    }

    private const val SHOW_EMOJI_AND_SYMBOL_PICKER_HASH = 4051624405L
    private val showEmojiAndSymbolPickerBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "show_emoji_and_symbol_picker", SHOW_EMOJI_AND_SYMBOL_PICKER_HASH)
    }

    private const val COLOR_PICKER_HASH = 151643214L
    private val colorPickerBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "color_picker", COLOR_PICKER_HASH)
    }

    private const val PROCESS_EVENTS_HASH = 3218959716L
    private val processEventsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "process_events", PROCESS_EVENTS_HASH)
    }

    private const val FORCE_PROCESS_AND_DROP_EVENTS_HASH = 3218959716L
    private val forceProcessAndDropEventsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "force_process_and_drop_events", FORCE_PROCESS_AND_DROP_EVENTS_HASH)
    }

    private const val SET_NATIVE_ICON_HASH = 83702148L
    private val setNativeIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_native_icon", SET_NATIVE_ICON_HASH)
    }

    private const val SET_ICON_HASH = 532598488L
    private val setIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_icon", SET_ICON_HASH)
    }

    private const val CREATE_STATUS_INDICATOR_HASH = 1904285171L
    private val createStatusIndicatorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "create_status_indicator", CREATE_STATUS_INDICATOR_HASH)
    }

    private const val STATUS_INDICATOR_SET_ICON_HASH = 666127730L
    private val statusIndicatorSetIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_icon", STATUS_INDICATOR_SET_ICON_HASH)
    }

    private const val STATUS_INDICATOR_SET_TOOLTIP_HASH = 501894301L
    private val statusIndicatorSetTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_tooltip", STATUS_INDICATOR_SET_TOOLTIP_HASH)
    }

    private const val STATUS_INDICATOR_SET_MENU_HASH = 4040184819L
    private val statusIndicatorSetMenuBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_menu", STATUS_INDICATOR_SET_MENU_HASH)
    }

    private const val STATUS_INDICATOR_SET_CALLBACK_HASH = 957362965L
    private val statusIndicatorSetCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_callback", STATUS_INDICATOR_SET_CALLBACK_HASH)
    }

    private const val STATUS_INDICATOR_GET_RECT_HASH = 3327874267L
    private val statusIndicatorGetRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_get_rect", STATUS_INDICATOR_GET_RECT_HASH)
    }

    private const val DELETE_STATUS_INDICATOR_HASH = 1286410249L
    private val deleteStatusIndicatorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "delete_status_indicator", DELETE_STATUS_INDICATOR_HASH)
    }

    private const val TABLET_GET_DRIVER_COUNT_HASH = 3905245786L
    private val tabletGetDriverCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_get_driver_count", TABLET_GET_DRIVER_COUNT_HASH)
    }

    private const val TABLET_GET_DRIVER_NAME_HASH = 844755477L
    private val tabletGetDriverNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_get_driver_name", TABLET_GET_DRIVER_NAME_HASH)
    }

    private const val TABLET_GET_CURRENT_DRIVER_HASH = 201670096L
    private val tabletGetCurrentDriverBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_get_current_driver", TABLET_GET_CURRENT_DRIVER_HASH)
    }

    private const val TABLET_SET_CURRENT_DRIVER_HASH = 83702148L
    private val tabletSetCurrentDriverBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_set_current_driver", TABLET_SET_CURRENT_DRIVER_HASH)
    }

    private const val IS_WINDOW_TRANSPARENCY_AVAILABLE_HASH = 36873697L
    private val isWindowTransparencyAvailableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_window_transparency_available", IS_WINDOW_TRANSPARENCY_AVAILABLE_HASH)
    }

    private const val REGISTER_ADDITIONAL_OUTPUT_HASH = 3975164845L
    private val registerAdditionalOutputBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "register_additional_output", REGISTER_ADDITIONAL_OUTPUT_HASH)
    }

    private const val UNREGISTER_ADDITIONAL_OUTPUT_HASH = 3975164845L
    private val unregisterAdditionalOutputBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "unregister_additional_output", UNREGISTER_ADDITIONAL_OUTPUT_HASH)
    }

    private const val HAS_ADDITIONAL_OUTPUTS_HASH = 36873697L
    private val hasAdditionalOutputsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "has_additional_outputs", HAS_ADDITIONAL_OUTPUTS_HASH)
    }

    private const val IS_IN_PIP_MODE_HASH = 1885608816L
    private val isInPipModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_in_pip_mode", IS_IN_PIP_MODE_HASH)
    }

    private const val PIP_MODE_ENTER_HASH = 1995695955L
    private val pipModeEnterBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "pip_mode_enter", PIP_MODE_ENTER_HASH)
    }

    private const val PIP_MODE_SET_ASPECT_RATIO_HASH = 3471927553L
    private val pipModeSetAspectRatioBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "pip_mode_set_aspect_ratio", PIP_MODE_SET_ASPECT_RATIO_HASH)
    }

    private const val PIP_MODE_SET_AUTO_ENTER_ON_BACKGROUND_HASH = 1661950165L
    private val pipModeSetAutoEnterOnBackgroundBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "pip_mode_set_auto_enter_on_background", PIP_MODE_SET_AUTO_ENTER_ON_BACKGROUND_HASH)
    }
}

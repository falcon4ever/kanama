package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A modal window used to display a list of options.
 *
 * Generated from Godot docs: PopupMenu
 */
class PopupMenu(handle: MemorySegment) : Popup(handle) {
    var hideOnItemSelection: Boolean
        @JvmName("hideOnItemSelectionProperty")
        get() = isHideOnItemSelection()
        @JvmName("setHideOnItemSelectionProperty")
        set(value) = setHideOnItemSelection(value)

    var hideOnCheckableItemSelection: Boolean
        @JvmName("hideOnCheckableItemSelectionProperty")
        get() = isHideOnCheckableItemSelection()
        @JvmName("setHideOnCheckableItemSelectionProperty")
        set(value) = setHideOnCheckableItemSelection(value)

    var hideOnStateItemSelection: Boolean
        @JvmName("hideOnStateItemSelectionProperty")
        get() = isHideOnStateItemSelection()
        @JvmName("setHideOnStateItemSelectionProperty")
        set(value) = setHideOnStateItemSelection(value)

    var submenuPopupDelay: Double
        @JvmName("submenuPopupDelayProperty")
        get() = getSubmenuPopupDelay()
        @JvmName("setSubmenuPopupDelayProperty")
        set(value) = setSubmenuPopupDelay(value)

    var allowSearch: Boolean
        @JvmName("allowSearchProperty")
        get() = getAllowSearch()
        @JvmName("setAllowSearchProperty")
        set(value) = setAllowSearch(value)

    var systemMenuId: Long
        @JvmName("systemMenuIdProperty")
        get() = getSystemMenu()
        @JvmName("setSystemMenuIdProperty")
        set(value) = setSystemMenu(value)

    var preferNativeMenu: Boolean
        @JvmName("preferNativeMenuProperty")
        get() = isPreferNativeMenu()
        @JvmName("setPreferNativeMenuProperty")
        set(value) = setPreferNativeMenu(value)

    var shrinkHeight: Boolean
        @JvmName("shrinkHeightProperty")
        get() = getShrinkHeight()
        @JvmName("setShrinkHeightProperty")
        set(value) = setShrinkHeight(value)

    var shrinkWidth: Boolean
        @JvmName("shrinkWidthProperty")
        get() = getShrinkWidth()
        @JvmName("setShrinkWidthProperty")
        set(value) = setShrinkWidth(value)

    var searchBarEnabled: Boolean
        @JvmName("searchBarEnabledProperty")
        get() = isSearchBarEnabled()
        @JvmName("setSearchBarEnabledProperty")
        set(value) = setSearchBarEnabled(value)

    var searchBarMinItemCount: Int
        @JvmName("searchBarMinItemCountProperty")
        get() = getSearchBarMinItemCount()
        @JvmName("setSearchBarMinItemCountProperty")
        set(value) = setSearchBarMinItemCount(value)

    var searchBarFuzzySearchEnabled: Boolean
        @JvmName("searchBarFuzzySearchEnabledProperty")
        get() = isSearchBarFuzzySearchEnabled()
        @JvmName("setSearchBarFuzzySearchEnabledProperty")
        set(value) = setSearchBarFuzzySearchEnabled(value)

    var searchBarFuzzySearchMaxMisses: Int
        @JvmName("searchBarFuzzySearchMaxMissesProperty")
        get() = getSearchBarFuzzySearchMaxMisses()
        @JvmName("setSearchBarFuzzySearchMaxMissesProperty")
        set(value) = setSearchBarFuzzySearchMaxMisses(value)

    var itemCount: Int
        @JvmName("itemCountProperty")
        get() = getItemCount()
        @JvmName("setItemCountProperty")
        set(value) = setItemCount(value)

    /**
     * Checks the provided `event` against the `PopupMenu`'s shortcuts and accelerators, and activates
     * the first item with matching events. If `for_global_only` is `true`, only shortcuts and
     * accelerators with `global` set to `true` will be called. Returns `true` if an item was
     * successfully activated. Note: Certain `Control`s, such as `MenuButton`, will call this method
     * automatically.
     *
     * Generated from Godot docs: PopupMenu.activate_item_by_event
     */
    fun activateItemByEvent(event: InputEvent?, forGlobalOnly: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetBool(activateItemByEventBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL, forGlobalOnly)
    }

    /**
     * If `true`, `MenuBar` will use native menu when supported. Note: If `PopupMenu` is linked to
     * `StatusIndicator`, `MenuBar`, or another `PopupMenu` item it can use native menu regardless of
     * this property, use `is_native_menu` to check it.
     *
     * Generated from Godot docs: PopupMenu.set_prefer_native_menu
     */
    fun setPreferNativeMenu(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreferNativeMenuBind, handle, enabled)
    }

    /**
     * If `true`, `MenuBar` will use native menu when supported. Note: If `PopupMenu` is linked to
     * `StatusIndicator`, `MenuBar`, or another `PopupMenu` item it can use native menu regardless of
     * this property, use `is_native_menu` to check it.
     *
     * Generated from Godot docs: PopupMenu.is_prefer_native_menu
     */
    fun isPreferNativeMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPreferNativeMenuBind, handle)
    }

    /**
     * Returns `true` if the system native menu is supported and currently used by this `PopupMenu`.
     *
     * Generated from Godot docs: PopupMenu.is_native_menu
     */
    fun isNativeMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNativeMenuBind, handle)
    }

    /**
     * Adds a new item with text `label`. An `id` can optionally be provided, as well as an accelerator
     * (`accel`). If no `id` is provided, one will be created from the index. If no `accel` is
     * provided, then the default value of 0 (corresponding to `@GlobalScope.KEY_NONE`) will be
     * assigned to the item (which means it won't have any accelerator). See `get_item_accelerator` for
     * more info on accelerators. Note: The provided `id` is used only in `id_pressed` and `id_focused`
     * signals. It's not related to the `index` arguments in e.g. `set_item_checked`.
     *
     * Generated from Godot docs: PopupMenu.add_item
     */
    fun addItem(label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(addItemBind, handle, label, id, accel)
    }

    /**
     * Adds a new item with text `label` and icon `texture`. An `id` can optionally be provided, as
     * well as an accelerator (`accel`). If no `id` is provided, one will be created from the index. If
     * no `accel` is provided, then the default value of 0 (corresponding to `@GlobalScope.KEY_NONE`)
     * will be assigned to the item (which means it won't have any accelerator). See
     * `get_item_accelerator` for more info on accelerators.
     *
     * Generated from Godot docs: PopupMenu.add_icon_item
     */
    fun addIconItem(texture: Texture2D?, label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithObjectStringIntLongArgs(addIconItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id, accel)
    }

    /**
     * Adds a new checkable item with text `label`. An `id` can optionally be provided, as well as an
     * accelerator (`accel`). If no `id` is provided, one will be created from the index. If no `accel`
     * is provided, then the default value of 0 (corresponding to `@GlobalScope.KEY_NONE`) will be
     * assigned to the item (which means it won't have any accelerator). See `get_item_accelerator` for
     * more info on accelerators. Note: Checkable items just display a checkmark, but don't have any
     * built-in checking behavior and must be checked/unchecked manually. See `set_item_checked` for
     * more info on how to control it.
     *
     * Generated from Godot docs: PopupMenu.add_check_item
     */
    fun addCheckItem(label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(addCheckItemBind, handle, label, id, accel)
    }

    /**
     * Adds a new checkable item with text `label` and icon `texture`. An `id` can optionally be
     * provided, as well as an accelerator (`accel`). If no `id` is provided, one will be created from
     * the index. If no `accel` is provided, then the default value of 0 (corresponding to
     * `@GlobalScope.KEY_NONE`) will be assigned to the item (which means it won't have any
     * accelerator). See `get_item_accelerator` for more info on accelerators. Note: Checkable items
     * just display a checkmark, but don't have any built-in checking behavior and must be
     * checked/unchecked manually. See `set_item_checked` for more info on how to control it.
     *
     * Generated from Godot docs: PopupMenu.add_icon_check_item
     */
    fun addIconCheckItem(texture: Texture2D?, label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithObjectStringIntLongArgs(addIconCheckItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id, accel)
    }

    /**
     * Adds a new radio check button with text `label`. An `id` can optionally be provided, as well as
     * an accelerator (`accel`). If no `id` is provided, one will be created from the index. If no
     * `accel` is provided, then the default value of 0 (corresponding to `@GlobalScope.KEY_NONE`) will
     * be assigned to the item (which means it won't have any accelerator). See `get_item_accelerator`
     * for more info on accelerators. Note: Checkable items just display a checkmark, but don't have
     * any built-in checking behavior and must be checked/unchecked manually. See `set_item_checked`
     * for more info on how to control it.
     *
     * Generated from Godot docs: PopupMenu.add_radio_check_item
     */
    fun addRadioCheckItem(label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(addRadioCheckItemBind, handle, label, id, accel)
    }

    /**
     * Same as `add_icon_check_item`, but uses a radio check button.
     *
     * Generated from Godot docs: PopupMenu.add_icon_radio_check_item
     */
    fun addIconRadioCheckItem(texture: Texture2D?, label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithObjectStringIntLongArgs(addIconRadioCheckItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id, accel)
    }

    /**
     * Adds a new multistate item with text `label`. Contrarily to normal binary items, multistate
     * items can have more than two states, as defined by `max_states`. The default value is defined by
     * `default_state`. An `id` can optionally be provided, as well as an accelerator (`accel`). If no
     * `id` is provided, one will be created from the index. If no `accel` is provided, then the
     * default value of 0 (corresponding to `@GlobalScope.KEY_NONE`) will be assigned to the item
     * (which means it won't have any accelerator). See `get_item_accelerator` for more info on
     * accelerators.
     *
     * Generated from Godot docs: PopupMenu.add_multistate_item
     */
    fun addMultistateItem(label: String, maxStates: Int, defaultState: Int = 0, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringThreeIntLongArgs(addMultistateItemBind, handle, label, maxStates, defaultState, id, accel)
    }

    /**
     * Adds a `Shortcut`. An `id` can optionally be provided. If no `id` is provided, one will be
     * created from the index. If `allow_echo` is `true`, the shortcut can be activated with echo
     * events.
     *
     * Generated from Godot docs: PopupMenu.add_shortcut
     */
    fun addShortcut(shortcut: Shortcut?, id: Int = -1, global: Boolean = false, allowEcho: Boolean = false) {
        ObjectCalls.ptrcallWithObjectIntTwoBoolArgs(addShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global, allowEcho)
    }

    /**
     * Adds a new item and assigns the specified `Shortcut` and icon `texture` to it. Sets the label of
     * the checkbox to the `Shortcut`'s name. An `id` can optionally be provided. If no `id` is
     * provided, one will be created from the index. If `allow_echo` is `true`, the shortcut can be
     * activated with echo events.
     *
     * Generated from Godot docs: PopupMenu.add_icon_shortcut
     */
    fun addIconShortcut(texture: Texture2D?, shortcut: Shortcut?, id: Int = -1, global: Boolean = false, allowEcho: Boolean = false) {
        ObjectCalls.ptrcallWithObjectObjectIntTwoBoolArgs(addIconShortcutBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global, allowEcho)
    }

    /**
     * Adds a new checkable item and assigns the specified `Shortcut` to it. Sets the label of the
     * checkbox to the `Shortcut`'s name. An `id` can optionally be provided. If no `id` is provided,
     * one will be created from the index. Note: Checkable items just display a checkmark, but don't
     * have any built-in checking behavior and must be checked/unchecked manually. See
     * `set_item_checked` for more info on how to control it.
     *
     * Generated from Godot docs: PopupMenu.add_check_shortcut
     */
    fun addCheckShortcut(shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectIntBoolArgs(addCheckShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    /**
     * Adds a new checkable item and assigns the specified `Shortcut` and icon `texture` to it. Sets
     * the label of the checkbox to the `Shortcut`'s name. An `id` can optionally be provided. If no
     * `id` is provided, one will be created from the index. Note: Checkable items just display a
     * checkmark, but don't have any built-in checking behavior and must be checked/unchecked manually.
     * See `set_item_checked` for more info on how to control it.
     *
     * Generated from Godot docs: PopupMenu.add_icon_check_shortcut
     */
    fun addIconCheckShortcut(texture: Texture2D?, shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectObjectIntBoolArgs(addIconCheckShortcutBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    /**
     * Adds a new radio check button and assigns a `Shortcut` to it. Sets the label of the checkbox to
     * the `Shortcut`'s name. An `id` can optionally be provided. If no `id` is provided, one will be
     * created from the index. Note: Checkable items just display a checkmark, but don't have any
     * built-in checking behavior and must be checked/unchecked manually. See `set_item_checked` for
     * more info on how to control it.
     *
     * Generated from Godot docs: PopupMenu.add_radio_check_shortcut
     */
    fun addRadioCheckShortcut(shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectIntBoolArgs(addRadioCheckShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    /**
     * Same as `add_icon_check_shortcut`, but uses a radio check button.
     *
     * Generated from Godot docs: PopupMenu.add_icon_radio_check_shortcut
     */
    fun addIconRadioCheckShortcut(texture: Texture2D?, shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectObjectIntBoolArgs(addIconRadioCheckShortcutBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    /**
     * Adds an item that will act as a submenu of the parent `PopupMenu` node when clicked. The
     * `submenu` argument must be the name of an existing `PopupMenu` that has been added as a child to
     * this node. This submenu will be shown when the item is clicked, hovered for long enough, or
     * activated using the `ui_select` or `ui_right` input actions. An `id` can optionally be provided.
     * If no `id` is provided, one will be created from the index.
     *
     * Generated from Godot docs: PopupMenu.add_submenu_item
     */
    fun addSubmenuItem(label: String, submenu: String, id: Int = -1) {
        ObjectCalls.ptrcallWithTwoStringAndIntArgs(addSubmenuItemBind, handle, label, submenu, id)
    }

    /**
     * Adds an item that will act as a submenu of the parent `PopupMenu` node when clicked. This
     * submenu will be shown when the item is clicked, hovered for long enough, or activated using the
     * `ui_select` or `ui_right` input actions. `submenu` must be either child of this `PopupMenu` or
     * has no parent node (in which case it will be automatically added as a child). If the `submenu`
     * popup has another parent, this method will fail. An `id` can optionally be provided. If no `id`
     * is provided, one will be created from the index.
     *
     * Generated from Godot docs: PopupMenu.add_submenu_node_item
     */
    fun addSubmenuNodeItem(label: String, submenu: PopupMenu, id: Int = -1) {
        ObjectCalls.ptrcallWithStringObjectIntArgs(addSubmenuNodeItemBind, handle, label, submenu.handle, id)
    }

    /**
     * Sets the text of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_text
     */
    fun setItemText(index: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTextBind, handle, index, text)
    }

    /**
     * Sets item's text base writing direction.
     *
     * Generated from Godot docs: PopupMenu.set_item_text_direction
     */
    fun setItemTextDirection(index: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemTextDirectionBind, handle, index, direction)
    }

    /**
     * Sets the language code of the text for the item at the given index to `language`. This is used
     * for line-breaking and text shaping algorithms. If `language` is empty, the current locale is
     * used.
     *
     * Generated from Godot docs: PopupMenu.set_item_language
     */
    fun setItemLanguage(index: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemLanguageBind, handle, index, language)
    }

    /**
     * Sets the auto translate mode of the item at the given `index`. Items use
     * `Node.AUTO_TRANSLATE_MODE_INHERIT` by default, which uses the same auto translate mode as the
     * `PopupMenu` itself.
     *
     * Generated from Godot docs: PopupMenu.set_item_auto_translate_mode
     */
    fun setItemAutoTranslateMode(index: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAutoTranslateModeBind, handle, index, mode)
    }

    /**
     * Replaces the `Texture2D` icon of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_icon
     */
    fun setItemIcon(index: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemIconBind, handle, index, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Sets the maximum allowed width of the icon for the item at the given `index`. This limit is
     * applied on top of the default size of the icon and on top of `icon_max_width`. The height is
     * adjusted according to the icon's ratio.
     *
     * Generated from Godot docs: PopupMenu.set_item_icon_max_width
     */
    fun setItemIconMaxWidth(index: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIconMaxWidthBind, handle, index, width)
    }

    /**
     * Sets a modulating `Color` of the item's icon at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_icon_modulate
     */
    fun setItemIconModulate(index: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemIconModulateBind, handle, index, modulate)
    }

    /**
     * Sets the checkstate status of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_checked
     */
    fun setItemChecked(index: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemCheckedBind, handle, index, checked)
    }

    /**
     * Sets the `id` of the item at the given `index`. The `id` is used in `id_pressed` and
     * `id_focused` signals.
     *
     * Generated from Godot docs: PopupMenu.set_item_id
     */
    fun setItemId(index: Int, id: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIdBind, handle, index, id)
    }

    /**
     * Sets the accelerator of the item at the given `index`. An accelerator is a keyboard shortcut
     * that can be pressed to trigger the menu button even if it's not currently open. `accel` is
     * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
     * | KEY_A` (Ctrl + A).
     *
     * Generated from Godot docs: PopupMenu.set_item_accelerator
     */
    fun setItemAccelerator(index: Int, accel: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAcceleratorBind, handle, index, accel)
    }

    /**
     * Sets the metadata of an item, which may be of any type. You can later get it with
     * `get_item_metadata`, which provides a simple way of assigning context data to items.
     *
     * Generated from Godot docs: PopupMenu.set_item_metadata
     */
    fun setItemMetadata(index: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, index, metadata)
    }

    /**
     * Enables/disables the item at the given `index`. When it is disabled, it can't be selected and
     * its action can't be invoked.
     *
     * Generated from Godot docs: PopupMenu.set_item_disabled
     */
    fun setItemDisabled(index: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemDisabledBind, handle, index, disabled)
    }

    /**
     * Sets the submenu of the item at the given `index`. The submenu is the name of a child
     * `PopupMenu` node that would be shown when the item is clicked.
     *
     * Generated from Godot docs: PopupMenu.set_item_submenu
     */
    fun setItemSubmenu(index: Int, submenu: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemSubmenuBind, handle, index, submenu)
    }

    /**
     * Sets the submenu of the item at the given `index`. The submenu is a `PopupMenu` node that would
     * be shown when the item is clicked. It must either be a child of this `PopupMenu` or has no
     * parent (in which case it will be automatically added as a child). If the `submenu` popup has
     * another parent, this method will fail.
     *
     * Generated from Godot docs: PopupMenu.set_item_submenu_node
     */
    fun setItemSubmenuNode(index: Int, submenu: PopupMenu) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemSubmenuNodeBind, handle, index, submenu.handle)
    }

    /**
     * Mark the item at the given `index` as a separator, which means that it would be displayed as a
     * line. If `false`, sets the type of the item to plain text.
     *
     * Generated from Godot docs: PopupMenu.set_item_as_separator
     */
    fun setItemAsSeparator(index: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemAsSeparatorBind, handle, index, enable)
    }

    /**
     * Sets whether the item at the given `index` has a checkbox. If `false`, sets the type of the item
     * to plain text. Note: Checkable items just display a checkmark, but don't have any built-in
     * checking behavior and must be checked/unchecked manually.
     *
     * Generated from Godot docs: PopupMenu.set_item_as_checkable
     */
    fun setItemAsCheckable(index: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemAsCheckableBind, handle, index, enable)
    }

    /**
     * Sets the type of the item at the given `index` to radio button. If `false`, sets the type of the
     * item to plain text.
     *
     * Generated from Godot docs: PopupMenu.set_item_as_radio_checkable
     */
    fun setItemAsRadioCheckable(index: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemAsRadioCheckableBind, handle, index, enable)
    }

    /**
     * Sets the `String` tooltip of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_tooltip
     */
    fun setItemTooltip(index: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTooltipBind, handle, index, tooltip)
    }

    /**
     * Sets a `Shortcut` for the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_shortcut
     */
    fun setItemShortcut(index: Int, shortcut: Shortcut?, global: Boolean = false) {
        ObjectCalls.ptrcallWithIntObjectBoolArgs(setItemShortcutBind, handle, index, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, global)
    }

    /**
     * Sets the horizontal offset of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_indent
     */
    fun setItemIndent(index: Int, indent: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIndentBind, handle, index, indent)
    }

    /**
     * Sets the state of a multistate item. See `add_multistate_item` for details.
     *
     * Generated from Godot docs: PopupMenu.set_item_multistate
     */
    fun setItemMultistate(index: Int, state: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemMultistateBind, handle, index, state)
    }

    /**
     * Sets the max states of a multistate item. See `add_multistate_item` for details.
     *
     * Generated from Godot docs: PopupMenu.set_item_multistate_max
     */
    fun setItemMultistateMax(index: Int, maxStates: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemMultistateMaxBind, handle, index, maxStates)
    }

    /**
     * Disables the `Shortcut` of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.set_item_shortcut_disabled
     */
    fun setItemShortcutDisabled(index: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemShortcutDisabledBind, handle, index, disabled)
    }

    /**
     * Changes the index of the item at index `index` to be at index `target_index`. This can be used
     * to move an item above other items. The moved item will keep the same ID, even if it was
     * generated from the original index. Note: The indices of any items between index `index` and
     * index `target_index` will be shifted by one.
     *
     * Generated from Godot docs: PopupMenu.set_item_index
     */
    fun setItemIndex(index: Int, targetIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIndexBind, handle, index, targetIndex)
    }

    /**
     * Toggles the check state of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.toggle_item_checked
     */
    fun toggleItemChecked(index: Int) {
        ObjectCalls.ptrcallWithIntArg(toggleItemCheckedBind, handle, index)
    }

    /**
     * Cycle to the next state of a multistate item. See `add_multistate_item` for details.
     *
     * Generated from Godot docs: PopupMenu.toggle_item_multistate
     */
    fun toggleItemMultistate(index: Int) {
        ObjectCalls.ptrcallWithIntArg(toggleItemMultistateBind, handle, index)
    }

    /**
     * Returns the text of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_text
     */
    fun getItemText(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTextBind, handle, index)
    }

    /**
     * Returns item's text base writing direction.
     *
     * Generated from Godot docs: PopupMenu.get_item_text_direction
     */
    fun getItemTextDirection(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemTextDirectionBind, handle, index)
    }

    /**
     * Returns item's text language code.
     *
     * Generated from Godot docs: PopupMenu.get_item_language
     */
    fun getItemLanguage(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemLanguageBind, handle, index)
    }

    /**
     * Returns the auto translate mode of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_auto_translate_mode
     */
    fun getItemAutoTranslateMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAutoTranslateModeBind, handle, index)
    }

    /**
     * Returns the icon of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_icon
     */
    fun getItemIcon(index: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemIconBind, handle, index))
    }

    /**
     * Returns the maximum allowed width of the icon for the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_icon_max_width
     */
    fun getItemIconMaxWidth(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIconMaxWidthBind, handle, index)
    }

    /**
     * Returns a `Color` modulating the item's icon at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_icon_modulate
     */
    fun getItemIconModulate(index: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemIconModulateBind, handle, index)
    }

    /**
     * Returns `true` if the item at the given `index` is checked.
     *
     * Generated from Godot docs: PopupMenu.is_item_checked
     */
    fun isItemChecked(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemCheckedBind, handle, index)
    }

    /**
     * Returns the ID of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_id
     */
    fun getItemId(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIdBind, handle, index)
    }

    /**
     * Returns the index of the item containing the specified `id`. The index is automatically assigned
     * to each item by the engine when added and represents the order items will be displayed.
     *
     * Generated from Godot docs: PopupMenu.get_item_index
     */
    fun getItemIndex(id: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIndexBind, handle, id)
    }

    /**
     * Returns the accelerator of the item at the given `index`. An accelerator is a keyboard shortcut
     * that can be pressed to trigger the menu button even if it's not currently open. The return value
     * is an integer which is generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR
     * such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). If no accelerator is defined for the specified
     * `index`, `get_item_accelerator` returns `0` (corresponding to `@GlobalScope.KEY_NONE`).
     *
     * Generated from Godot docs: PopupMenu.get_item_accelerator
     */
    fun getItemAccelerator(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAcceleratorBind, handle, index)
    }

    /**
     * Returns the metadata of the specified item, which might be of any type. You can set it with
     * `set_item_metadata`, which provides a simple way of assigning context data to items.
     *
     * Generated from Godot docs: PopupMenu.get_item_metadata
     */
    fun getItemMetadata(index: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, index)
    }

    /**
     * Returns `true` if the item at the given `index` is disabled. When it is disabled it can't be
     * selected, or its action invoked. See `set_item_disabled` for more info on how to disable an
     * item.
     *
     * Generated from Godot docs: PopupMenu.is_item_disabled
     */
    fun isItemDisabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemDisabledBind, handle, index)
    }

    /**
     * Returns the submenu name of the item at the given `index`. See `add_submenu_item` for more info
     * on how to add a submenu.
     *
     * Generated from Godot docs: PopupMenu.get_item_submenu
     */
    fun getItemSubmenu(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemSubmenuBind, handle, index)
    }

    /**
     * Returns the submenu of the item at the given `index`, or `null` if no submenu was added. See
     * `add_submenu_node_item` for more info on how to add a submenu.
     *
     * Generated from Godot docs: PopupMenu.get_item_submenu_node
     */
    fun getItemSubmenuNode(index: Int): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemSubmenuNodeBind, handle, index))
    }

    /**
     * Returns `true` if the item is a separator. If it is, it will be displayed as a line. See
     * `add_separator` for more info on how to add a separator.
     *
     * Generated from Godot docs: PopupMenu.is_item_separator
     */
    fun isItemSeparator(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemSeparatorBind, handle, index)
    }

    /**
     * Returns `true` if the item at the given `index` is checkable in some way, i.e. if it has a
     * checkbox or radio button. Note: Checkable items just display a checkmark or radio button, but
     * don't have any built-in checking behavior and must be checked/unchecked manually.
     *
     * Generated from Godot docs: PopupMenu.is_item_checkable
     */
    fun isItemCheckable(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemCheckableBind, handle, index)
    }

    /**
     * Returns `true` if the item at the given `index` has radio button-style checkability. Note: This
     * is purely cosmetic; you must add the logic for checking/unchecking items in radio groups.
     *
     * Generated from Godot docs: PopupMenu.is_item_radio_checkable
     */
    fun isItemRadioCheckable(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemRadioCheckableBind, handle, index)
    }

    /**
     * Returns `true` if the specified item's shortcut is disabled.
     *
     * Generated from Godot docs: PopupMenu.is_item_shortcut_disabled
     */
    fun isItemShortcutDisabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemShortcutDisabledBind, handle, index)
    }

    /**
     * Returns the tooltip associated with the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_tooltip
     */
    fun getItemTooltip(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTooltipBind, handle, index)
    }

    /**
     * Returns the `Shortcut` associated with the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_shortcut
     */
    fun getItemShortcut(index: Int): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemShortcutBind, handle, index))
    }

    /**
     * Returns the horizontal offset of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_indent
     */
    fun getItemIndent(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIndentBind, handle, index)
    }

    /**
     * Returns the max states of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_multistate_max
     */
    fun getItemMultistateMax(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemMultistateMaxBind, handle, index)
    }

    /**
     * Returns the state of the item at the given `index`.
     *
     * Generated from Godot docs: PopupMenu.get_item_multistate
     */
    fun getItemMultistate(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemMultistateBind, handle, index)
    }

    /**
     * Sets the currently focused item as the given `index`. Passing `-1` as the index makes so that no
     * item is focused.
     *
     * Generated from Godot docs: PopupMenu.set_focused_item
     */
    fun setFocusedItem(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setFocusedItemBind, handle, index)
    }

    /**
     * Returns the index of the currently focused item. Returns `-1` if no item is focused.
     *
     * Generated from Godot docs: PopupMenu.get_focused_item
     */
    fun getFocusedItem(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFocusedItemBind, handle)
    }

    /**
     * The number of items currently in the list.
     *
     * Generated from Godot docs: PopupMenu.set_item_count
     */
    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCountBind, handle, count)
    }

    /**
     * The number of items currently in the list.
     *
     * Generated from Godot docs: PopupMenu.get_item_count
     */
    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

    /**
     * Moves the scroll view to make the item at the given `index` visible.
     *
     * Generated from Godot docs: PopupMenu.scroll_to_item
     */
    fun scrollToItem(index: Int) {
        ObjectCalls.ptrcallWithIntArg(scrollToItemBind, handle, index)
    }

    /**
     * Removes the item at the given `index` from the menu. Note: The indices of items after the
     * removed item will be shifted by one.
     *
     * Generated from Godot docs: PopupMenu.remove_item
     */
    fun removeItem(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeItemBind, handle, index)
    }

    /**
     * Adds a separator between items. Separators also occupy an index, which you can set by using the
     * `id` parameter. A `label` can optionally be provided, which will appear at the center of the
     * separator.
     *
     * Generated from Godot docs: PopupMenu.add_separator
     */
    fun addSeparator(label: String = "", id: Int = -1) {
        ObjectCalls.ptrcallWithStringAndIntArg(addSeparatorBind, handle, label, id)
    }

    /**
     * Removes all items from the `PopupMenu`. If `free_submenus` is `true`, the submenu nodes are
     * automatically freed.
     *
     * Generated from Godot docs: PopupMenu.clear
     */
    fun clear(freeSubmenus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(clearBind, handle, freeSubmenus)
    }

    /**
     * If `true`, hides the `PopupMenu` when an item is selected.
     *
     * Generated from Godot docs: PopupMenu.set_hide_on_item_selection
     */
    fun setHideOnItemSelection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnItemSelectionBind, handle, enable)
    }

    /**
     * If `true`, hides the `PopupMenu` when an item is selected.
     *
     * Generated from Godot docs: PopupMenu.is_hide_on_item_selection
     */
    fun isHideOnItemSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHideOnItemSelectionBind, handle)
    }

    /**
     * If `true`, hides the `PopupMenu` when a checkbox or radio button is selected.
     *
     * Generated from Godot docs: PopupMenu.set_hide_on_checkable_item_selection
     */
    fun setHideOnCheckableItemSelection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnCheckableItemSelectionBind, handle, enable)
    }

    /**
     * If `true`, hides the `PopupMenu` when a checkbox or radio button is selected.
     *
     * Generated from Godot docs: PopupMenu.is_hide_on_checkable_item_selection
     */
    fun isHideOnCheckableItemSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHideOnCheckableItemSelectionBind, handle)
    }

    /**
     * If `true`, hides the `PopupMenu` when a state item is selected.
     *
     * Generated from Godot docs: PopupMenu.set_hide_on_state_item_selection
     */
    fun setHideOnStateItemSelection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnStateItemSelectionBind, handle, enable)
    }

    /**
     * If `true`, hides the `PopupMenu` when a state item is selected.
     *
     * Generated from Godot docs: PopupMenu.is_hide_on_state_item_selection
     */
    fun isHideOnStateItemSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHideOnStateItemSelectionBind, handle)
    }

    /**
     * Sets the delay time in seconds for the submenu item to popup on mouse hovering. If the popup
     * menu is added as a child of another (acting as a submenu), it will inherit the delay time of the
     * parent menu item. Note: If the mouse is exiting a submenu item with an open submenu and enters a
     * different submenu item, the submenu popup delay time is affected by the direction of the mouse
     * movement toward the open submenu. If the mouse is moving toward the submenu, the open submenu
     * will wait approximately `0.5` seconds before closing, which then allows the hovered submenu item
     * to open. This additional delay allows the mouse time to move to the open submenu across other
     * menu items without prematurely closing. If the mouse is not moving toward the open submenu, for
     * example in a downward direction, the open submenu will close immediately.
     *
     * Generated from Godot docs: PopupMenu.set_submenu_popup_delay
     */
    fun setSubmenuPopupDelay(seconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSubmenuPopupDelayBind, handle, seconds)
    }

    /**
     * Sets the delay time in seconds for the submenu item to popup on mouse hovering. If the popup
     * menu is added as a child of another (acting as a submenu), it will inherit the delay time of the
     * parent menu item. Note: If the mouse is exiting a submenu item with an open submenu and enters a
     * different submenu item, the submenu popup delay time is affected by the direction of the mouse
     * movement toward the open submenu. If the mouse is moving toward the submenu, the open submenu
     * will wait approximately `0.5` seconds before closing, which then allows the hovered submenu item
     * to open. This additional delay allows the mouse time to move to the open submenu across other
     * menu items without prematurely closing. If the mouse is not moving toward the open submenu, for
     * example in a downward direction, the open submenu will close immediately.
     *
     * Generated from Godot docs: PopupMenu.get_submenu_popup_delay
     */
    fun getSubmenuPopupDelay(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSubmenuPopupDelayBind, handle)
    }

    /**
     * If `true`, allows navigating `PopupMenu` with letter keys.
     *
     * Generated from Godot docs: PopupMenu.set_allow_search
     */
    fun setAllowSearch(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowSearchBind, handle, allow)
    }

    /**
     * If `true`, allows navigating `PopupMenu` with letter keys.
     *
     * Generated from Godot docs: PopupMenu.get_allow_search
     */
    fun getAllowSearch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowSearchBind, handle)
    }

    /**
     * Returns `true` if the menu is bound to the special system menu.
     *
     * Generated from Godot docs: PopupMenu.is_system_menu
     */
    fun isSystemMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSystemMenuBind, handle)
    }

    /**
     * If set to one of the values of `NativeMenu.SystemMenus`, this `PopupMenu` is bound to the
     * special system menu. Only one `PopupMenu` can be bound to each special menu at a time.
     *
     * Generated from Godot docs: PopupMenu.set_system_menu
     */
    fun setSystemMenu(systemMenuId: Long) {
        ObjectCalls.ptrcallWithLongArg(setSystemMenuBind, handle, systemMenuId)
    }

    /**
     * If set to one of the values of `NativeMenu.SystemMenus`, this `PopupMenu` is bound to the
     * special system menu. Only one `PopupMenu` can be bound to each special menu at a time.
     *
     * Generated from Godot docs: PopupMenu.get_system_menu
     */
    fun getSystemMenu(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSystemMenuBind, handle)
    }

    /**
     * If `true`, shows a search bar at the top of the `PopupMenu` for filtering items. See
     * `search_bar_min_item_count` for dynamically controlling its visibility based on the number of
     * items. Note: When enabled, `allow_search` is ignored.
     *
     * Generated from Godot docs: PopupMenu.set_search_bar_enabled
     */
    fun setSearchBarEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSearchBarEnabledBind, handle, enabled)
    }

    /**
     * If `true`, shows a search bar at the top of the `PopupMenu` for filtering items. See
     * `search_bar_min_item_count` for dynamically controlling its visibility based on the number of
     * items. Note: When enabled, `allow_search` is ignored.
     *
     * Generated from Godot docs: PopupMenu.is_search_bar_enabled
     */
    fun isSearchBarEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSearchBarEnabledBind, handle)
    }

    /**
     * Sets the minimum number of items required for the search bar to be visible. `search_bar_enabled`
     * must be `true` for this to have any effect. Separator items are not counted.
     *
     * Generated from Godot docs: PopupMenu.set_search_bar_min_item_count
     */
    fun setSearchBarMinItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setSearchBarMinItemCountBind, handle, count)
    }

    /**
     * Sets the minimum number of items required for the search bar to be visible. `search_bar_enabled`
     * must be `true` for this to have any effect. Separator items are not counted.
     *
     * Generated from Godot docs: PopupMenu.get_search_bar_min_item_count
     */
    fun getSearchBarMinItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSearchBarMinItemCountBind, handle)
    }

    /**
     * If `true`, enables fuzzy searching in the `PopupMenu` search bar. This allows the search results
     * to include items that almost match the search query, as well items that match the individual
     * characters of the search query, but not in sequence. Use `search_bar_fuzzy_search_max_misses` to
     * set the maximum number of mismatches allowed in the search results.
     *
     * Generated from Godot docs: PopupMenu.set_search_bar_fuzzy_search_enabled
     */
    fun setSearchBarFuzzySearchEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSearchBarFuzzySearchEnabledBind, handle, enabled)
    }

    /**
     * If `true`, enables fuzzy searching in the `PopupMenu` search bar. This allows the search results
     * to include items that almost match the search query, as well items that match the individual
     * characters of the search query, but not in sequence. Use `search_bar_fuzzy_search_max_misses` to
     * set the maximum number of mismatches allowed in the search results.
     *
     * Generated from Godot docs: PopupMenu.is_search_bar_fuzzy_search_enabled
     */
    fun isSearchBarFuzzySearchEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSearchBarFuzzySearchEnabledBind, handle)
    }

    /**
     * Sets the maximum number of mismatches allowed in each search result when fuzzy searching is
     * enabled for the `PopupMenu` search bar. Any item with more mismatches will be hidden from the
     * search results.
     *
     * Generated from Godot docs: PopupMenu.set_search_bar_fuzzy_search_max_misses
     */
    fun setSearchBarFuzzySearchMaxMisses(maxMisses: Int) {
        ObjectCalls.ptrcallWithIntArg(setSearchBarFuzzySearchMaxMissesBind, handle, maxMisses)
    }

    /**
     * Sets the maximum number of mismatches allowed in each search result when fuzzy searching is
     * enabled for the `PopupMenu` search bar. Any item with more mismatches will be hidden from the
     * search results.
     *
     * Generated from Godot docs: PopupMenu.get_search_bar_fuzzy_search_max_misses
     */
    fun getSearchBarFuzzySearchMaxMisses(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSearchBarFuzzySearchMaxMissesBind, handle)
    }

    /**
     * If `true`, shrinks `PopupMenu` to minimum height when it's shown.
     *
     * Generated from Godot docs: PopupMenu.set_shrink_height
     */
    fun setShrinkHeight(shrink: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShrinkHeightBind, handle, shrink)
    }

    /**
     * If `true`, shrinks `PopupMenu` to minimum height when it's shown.
     *
     * Generated from Godot docs: PopupMenu.get_shrink_height
     */
    fun getShrinkHeight(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShrinkHeightBind, handle)
    }

    /**
     * If `true`, shrinks `PopupMenu` to minimum width when it's shown.
     *
     * Generated from Godot docs: PopupMenu.set_shrink_width
     */
    fun setShrinkWidth(shrink: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShrinkWidthBind, handle, shrink)
    }

    /**
     * If `true`, shrinks `PopupMenu` to minimum width when it's shown.
     *
     * Generated from Godot docs: PopupMenu.get_shrink_width
     */
    fun getShrinkWidth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShrinkWidthBind, handle)
    }

    object Signals {
        const val idPressed: String = "id_pressed"
        const val idFocused: String = "id_focused"
        const val indexPressed: String = "index_pressed"
        const val menuChanged: String = "menu_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PopupMenu? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PopupMenu? =
            if (handle.address() == 0L) null else PopupMenu(handle)

        private const val ACTIVATE_ITEM_BY_EVENT_HASH = 3716412023L
        private val activateItemByEventBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "activate_item_by_event", ACTIVATE_ITEM_BY_EVENT_HASH)
        }

        private const val SET_PREFER_NATIVE_MENU_HASH = 2586408642L
        private val setPreferNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_prefer_native_menu", SET_PREFER_NATIVE_MENU_HASH)
        }

        private const val IS_PREFER_NATIVE_MENU_HASH = 36873697L
        private val isPreferNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_prefer_native_menu", IS_PREFER_NATIVE_MENU_HASH)
        }

        private const val IS_NATIVE_MENU_HASH = 36873697L
        private val isNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_native_menu", IS_NATIVE_MENU_HASH)
        }

        private const val ADD_ITEM_HASH = 3674230041L
        private val addItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_item", ADD_ITEM_HASH)
        }

        private const val ADD_ICON_ITEM_HASH = 1086190128L
        private val addIconItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_item", ADD_ICON_ITEM_HASH)
        }

        private const val ADD_CHECK_ITEM_HASH = 3674230041L
        private val addCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_check_item", ADD_CHECK_ITEM_HASH)
        }

        private const val ADD_ICON_CHECK_ITEM_HASH = 1086190128L
        private val addIconCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_check_item", ADD_ICON_CHECK_ITEM_HASH)
        }

        private const val ADD_RADIO_CHECK_ITEM_HASH = 3674230041L
        private val addRadioCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_radio_check_item", ADD_RADIO_CHECK_ITEM_HASH)
        }

        private const val ADD_ICON_RADIO_CHECK_ITEM_HASH = 1086190128L
        private val addIconRadioCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_radio_check_item", ADD_ICON_RADIO_CHECK_ITEM_HASH)
        }

        private const val ADD_MULTISTATE_ITEM_HASH = 150780458L
        private val addMultistateItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_multistate_item", ADD_MULTISTATE_ITEM_HASH)
        }

        private const val ADD_SHORTCUT_HASH = 3451850107L
        private val addShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_shortcut", ADD_SHORTCUT_HASH)
        }

        private const val ADD_ICON_SHORTCUT_HASH = 2997871092L
        private val addIconShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_shortcut", ADD_ICON_SHORTCUT_HASH)
        }

        private const val ADD_CHECK_SHORTCUT_HASH = 1642193386L
        private val addCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_check_shortcut", ADD_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_ICON_CHECK_SHORTCUT_HASH = 3856247530L
        private val addIconCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_check_shortcut", ADD_ICON_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_RADIO_CHECK_SHORTCUT_HASH = 1642193386L
        private val addRadioCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_radio_check_shortcut", ADD_RADIO_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_ICON_RADIO_CHECK_SHORTCUT_HASH = 3856247530L
        private val addIconRadioCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_radio_check_shortcut", ADD_ICON_RADIO_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_SUBMENU_ITEM_HASH = 2979222410L
        private val addSubmenuItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_submenu_item", ADD_SUBMENU_ITEM_HASH)
        }

        private const val ADD_SUBMENU_NODE_ITEM_HASH = 1325455216L
        private val addSubmenuNodeItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_submenu_node_item", ADD_SUBMENU_NODE_ITEM_HASH)
        }

        private const val SET_ITEM_TEXT_HASH = 501894301L
        private val setItemTextBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_text", SET_ITEM_TEXT_HASH)
        }

        private const val SET_ITEM_TEXT_DIRECTION_HASH = 1707680378L
        private val setItemTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_text_direction", SET_ITEM_TEXT_DIRECTION_HASH)
        }

        private const val SET_ITEM_LANGUAGE_HASH = 501894301L
        private val setItemLanguageBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_language", SET_ITEM_LANGUAGE_HASH)
        }

        private const val SET_ITEM_AUTO_TRANSLATE_MODE_HASH = 287402019L
        private val setItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_auto_translate_mode", SET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val SET_ITEM_ICON_HASH = 666127730L
        private val setItemIconBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_icon", SET_ITEM_ICON_HASH)
        }

        private const val SET_ITEM_ICON_MAX_WIDTH_HASH = 3937882851L
        private val setItemIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_icon_max_width", SET_ITEM_ICON_MAX_WIDTH_HASH)
        }

        private const val SET_ITEM_ICON_MODULATE_HASH = 2878471219L
        private val setItemIconModulateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_icon_modulate", SET_ITEM_ICON_MODULATE_HASH)
        }

        private const val SET_ITEM_CHECKED_HASH = 300928843L
        private val setItemCheckedBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_checked", SET_ITEM_CHECKED_HASH)
        }

        private const val SET_ITEM_ID_HASH = 3937882851L
        private val setItemIdBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_id", SET_ITEM_ID_HASH)
        }

        private const val SET_ITEM_ACCELERATOR_HASH = 2992817551L
        private val setItemAcceleratorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_accelerator", SET_ITEM_ACCELERATOR_HASH)
        }

        private const val SET_ITEM_METADATA_HASH = 2152698145L
        private val setItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_metadata", SET_ITEM_METADATA_HASH)
        }

        private const val SET_ITEM_DISABLED_HASH = 300928843L
        private val setItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_disabled", SET_ITEM_DISABLED_HASH)
        }

        private const val SET_ITEM_SUBMENU_HASH = 501894301L
        private val setItemSubmenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_submenu", SET_ITEM_SUBMENU_HASH)
        }

        private const val SET_ITEM_SUBMENU_NODE_HASH = 1068370740L
        private val setItemSubmenuNodeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_submenu_node", SET_ITEM_SUBMENU_NODE_HASH)
        }

        private const val SET_ITEM_AS_SEPARATOR_HASH = 300928843L
        private val setItemAsSeparatorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_as_separator", SET_ITEM_AS_SEPARATOR_HASH)
        }

        private const val SET_ITEM_AS_CHECKABLE_HASH = 300928843L
        private val setItemAsCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_as_checkable", SET_ITEM_AS_CHECKABLE_HASH)
        }

        private const val SET_ITEM_AS_RADIO_CHECKABLE_HASH = 300928843L
        private val setItemAsRadioCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_as_radio_checkable", SET_ITEM_AS_RADIO_CHECKABLE_HASH)
        }

        private const val SET_ITEM_TOOLTIP_HASH = 501894301L
        private val setItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_tooltip", SET_ITEM_TOOLTIP_HASH)
        }

        private const val SET_ITEM_SHORTCUT_HASH = 825127832L
        private val setItemShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_shortcut", SET_ITEM_SHORTCUT_HASH)
        }

        private const val SET_ITEM_INDENT_HASH = 3937882851L
        private val setItemIndentBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_indent", SET_ITEM_INDENT_HASH)
        }

        private const val SET_ITEM_MULTISTATE_HASH = 3937882851L
        private val setItemMultistateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_multistate", SET_ITEM_MULTISTATE_HASH)
        }

        private const val SET_ITEM_MULTISTATE_MAX_HASH = 3937882851L
        private val setItemMultistateMaxBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_multistate_max", SET_ITEM_MULTISTATE_MAX_HASH)
        }

        private const val SET_ITEM_SHORTCUT_DISABLED_HASH = 300928843L
        private val setItemShortcutDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_shortcut_disabled", SET_ITEM_SHORTCUT_DISABLED_HASH)
        }

        private const val SET_ITEM_INDEX_HASH = 3937882851L
        private val setItemIndexBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_index", SET_ITEM_INDEX_HASH)
        }

        private const val TOGGLE_ITEM_CHECKED_HASH = 1286410249L
        private val toggleItemCheckedBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "toggle_item_checked", TOGGLE_ITEM_CHECKED_HASH)
        }

        private const val TOGGLE_ITEM_MULTISTATE_HASH = 1286410249L
        private val toggleItemMultistateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "toggle_item_multistate", TOGGLE_ITEM_MULTISTATE_HASH)
        }

        private const val GET_ITEM_TEXT_HASH = 844755477L
        private val getItemTextBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_text", GET_ITEM_TEXT_HASH)
        }

        private const val GET_ITEM_TEXT_DIRECTION_HASH = 4235602388L
        private val getItemTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_text_direction", GET_ITEM_TEXT_DIRECTION_HASH)
        }

        private const val GET_ITEM_LANGUAGE_HASH = 844755477L
        private val getItemLanguageBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_language", GET_ITEM_LANGUAGE_HASH)
        }

        private const val GET_ITEM_AUTO_TRANSLATE_MODE_HASH = 906302372L
        private val getItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_auto_translate_mode", GET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val GET_ITEM_ICON_HASH = 3536238170L
        private val getItemIconBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_icon", GET_ITEM_ICON_HASH)
        }

        private const val GET_ITEM_ICON_MAX_WIDTH_HASH = 923996154L
        private val getItemIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_icon_max_width", GET_ITEM_ICON_MAX_WIDTH_HASH)
        }

        private const val GET_ITEM_ICON_MODULATE_HASH = 3457211756L
        private val getItemIconModulateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_icon_modulate", GET_ITEM_ICON_MODULATE_HASH)
        }

        private const val IS_ITEM_CHECKED_HASH = 1116898809L
        private val isItemCheckedBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_checked", IS_ITEM_CHECKED_HASH)
        }

        private const val GET_ITEM_ID_HASH = 923996154L
        private val getItemIdBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_id", GET_ITEM_ID_HASH)
        }

        private const val GET_ITEM_INDEX_HASH = 923996154L
        private val getItemIndexBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_index", GET_ITEM_INDEX_HASH)
        }

        private const val GET_ITEM_ACCELERATOR_HASH = 253789942L
        private val getItemAcceleratorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_accelerator", GET_ITEM_ACCELERATOR_HASH)
        }

        private const val GET_ITEM_METADATA_HASH = 4227898402L
        private val getItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_metadata", GET_ITEM_METADATA_HASH)
        }

        private const val IS_ITEM_DISABLED_HASH = 1116898809L
        private val isItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_disabled", IS_ITEM_DISABLED_HASH)
        }

        private const val GET_ITEM_SUBMENU_HASH = 844755477L
        private val getItemSubmenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_submenu", GET_ITEM_SUBMENU_HASH)
        }

        private const val GET_ITEM_SUBMENU_NODE_HASH = 2100501353L
        private val getItemSubmenuNodeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_submenu_node", GET_ITEM_SUBMENU_NODE_HASH)
        }

        private const val IS_ITEM_SEPARATOR_HASH = 1116898809L
        private val isItemSeparatorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_separator", IS_ITEM_SEPARATOR_HASH)
        }

        private const val IS_ITEM_CHECKABLE_HASH = 1116898809L
        private val isItemCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_checkable", IS_ITEM_CHECKABLE_HASH)
        }

        private const val IS_ITEM_RADIO_CHECKABLE_HASH = 1116898809L
        private val isItemRadioCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_radio_checkable", IS_ITEM_RADIO_CHECKABLE_HASH)
        }

        private const val IS_ITEM_SHORTCUT_DISABLED_HASH = 1116898809L
        private val isItemShortcutDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_shortcut_disabled", IS_ITEM_SHORTCUT_DISABLED_HASH)
        }

        private const val GET_ITEM_TOOLTIP_HASH = 844755477L
        private val getItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
        }

        private const val GET_ITEM_SHORTCUT_HASH = 1449483325L
        private val getItemShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_shortcut", GET_ITEM_SHORTCUT_HASH)
        }

        private const val GET_ITEM_INDENT_HASH = 923996154L
        private val getItemIndentBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_indent", GET_ITEM_INDENT_HASH)
        }

        private const val GET_ITEM_MULTISTATE_MAX_HASH = 923996154L
        private val getItemMultistateMaxBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_multistate_max", GET_ITEM_MULTISTATE_MAX_HASH)
        }

        private const val GET_ITEM_MULTISTATE_HASH = 923996154L
        private val getItemMultistateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_multistate", GET_ITEM_MULTISTATE_HASH)
        }

        private const val SET_FOCUSED_ITEM_HASH = 1286410249L
        private val setFocusedItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_focused_item", SET_FOCUSED_ITEM_HASH)
        }

        private const val GET_FOCUSED_ITEM_HASH = 3905245786L
        private val getFocusedItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_focused_item", GET_FOCUSED_ITEM_HASH)
        }

        private const val SET_ITEM_COUNT_HASH = 1286410249L
        private val setItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_count", SET_ITEM_COUNT_HASH)
        }

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        private val getItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_count", GET_ITEM_COUNT_HASH)
        }

        private const val SCROLL_TO_ITEM_HASH = 1286410249L
        private val scrollToItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "scroll_to_item", SCROLL_TO_ITEM_HASH)
        }

        private const val REMOVE_ITEM_HASH = 1286410249L
        private val removeItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "remove_item", REMOVE_ITEM_HASH)
        }

        private const val ADD_SEPARATOR_HASH = 2266703459L
        private val addSeparatorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_separator", ADD_SEPARATOR_HASH)
        }

        private const val CLEAR_HASH = 107499316L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "clear", CLEAR_HASH)
        }

        private const val SET_HIDE_ON_ITEM_SELECTION_HASH = 2586408642L
        private val setHideOnItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_hide_on_item_selection", SET_HIDE_ON_ITEM_SELECTION_HASH)
        }

        private const val IS_HIDE_ON_ITEM_SELECTION_HASH = 36873697L
        private val isHideOnItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_hide_on_item_selection", IS_HIDE_ON_ITEM_SELECTION_HASH)
        }

        private const val SET_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH = 2586408642L
        private val setHideOnCheckableItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_hide_on_checkable_item_selection", SET_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH)
        }

        private const val IS_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH = 36873697L
        private val isHideOnCheckableItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_hide_on_checkable_item_selection", IS_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH)
        }

        private const val SET_HIDE_ON_STATE_ITEM_SELECTION_HASH = 2586408642L
        private val setHideOnStateItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_hide_on_state_item_selection", SET_HIDE_ON_STATE_ITEM_SELECTION_HASH)
        }

        private const val IS_HIDE_ON_STATE_ITEM_SELECTION_HASH = 36873697L
        private val isHideOnStateItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_hide_on_state_item_selection", IS_HIDE_ON_STATE_ITEM_SELECTION_HASH)
        }

        private const val SET_SUBMENU_POPUP_DELAY_HASH = 373806689L
        private val setSubmenuPopupDelayBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_submenu_popup_delay", SET_SUBMENU_POPUP_DELAY_HASH)
        }

        private const val GET_SUBMENU_POPUP_DELAY_HASH = 1740695150L
        private val getSubmenuPopupDelayBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_submenu_popup_delay", GET_SUBMENU_POPUP_DELAY_HASH)
        }

        private const val SET_ALLOW_SEARCH_HASH = 2586408642L
        private val setAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_allow_search", SET_ALLOW_SEARCH_HASH)
        }

        private const val GET_ALLOW_SEARCH_HASH = 36873697L
        private val getAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_allow_search", GET_ALLOW_SEARCH_HASH)
        }

        private const val IS_SYSTEM_MENU_HASH = 36873697L
        private val isSystemMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_system_menu", IS_SYSTEM_MENU_HASH)
        }

        private const val SET_SYSTEM_MENU_HASH = 600639674L
        private val setSystemMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_system_menu", SET_SYSTEM_MENU_HASH)
        }

        private const val GET_SYSTEM_MENU_HASH = 1222557358L
        private val getSystemMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_system_menu", GET_SYSTEM_MENU_HASH)
        }

        private const val SET_SEARCH_BAR_ENABLED_HASH = 2586408642L
        private val setSearchBarEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_enabled", SET_SEARCH_BAR_ENABLED_HASH)
        }

        private const val IS_SEARCH_BAR_ENABLED_HASH = 36873697L
        private val isSearchBarEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_search_bar_enabled", IS_SEARCH_BAR_ENABLED_HASH)
        }

        private const val SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 1286410249L
        private val setSearchBarMinItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_min_item_count", SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)
        }

        private const val GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 3905245786L
        private val getSearchBarMinItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_search_bar_min_item_count", GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)
        }

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 2586408642L
        private val setSearchBarFuzzySearchEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_fuzzy_search_enabled", SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)
        }

        private const val IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 36873697L
        private val isSearchBarFuzzySearchEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_search_bar_fuzzy_search_enabled", IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)
        }

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 1286410249L
        private val setSearchBarFuzzySearchMaxMissesBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_fuzzy_search_max_misses", SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)
        }

        private const val GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 3905245786L
        private val getSearchBarFuzzySearchMaxMissesBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_search_bar_fuzzy_search_max_misses", GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)
        }

        private const val SET_SHRINK_HEIGHT_HASH = 2586408642L
        private val setShrinkHeightBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_shrink_height", SET_SHRINK_HEIGHT_HASH)
        }

        private const val GET_SHRINK_HEIGHT_HASH = 36873697L
        private val getShrinkHeightBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_shrink_height", GET_SHRINK_HEIGHT_HASH)
        }

        private const val SET_SHRINK_WIDTH_HASH = 2586408642L
        private val setShrinkWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_shrink_width", SET_SHRINK_WIDTH_HASH)
        }

        private const val GET_SHRINK_WIDTH_HASH = 36873697L
        private val getShrinkWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_shrink_width", GET_SHRINK_WIDTH_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A button that brings up a `PopupMenu` when clicked.
 *
 * Generated from Godot docs: MenuButton
 */
class MenuButton(handle: MemorySegment) : Button(handle) {
    var switchOnHover: Boolean
        @JvmName("switchOnHoverProperty")
        get() = isSwitchOnHover()
        @JvmName("setSwitchOnHoverProperty")
        set(value) = setSwitchOnHover(value)

    var itemCount: Int
        @JvmName("itemCountProperty")
        get() = getItemCount()
        @JvmName("setItemCountProperty")
        set(value) = setItemCount(value)

    /**
     * Returns the `PopupMenu` contained in this button. Warning: This is a required internal node,
     * removing and freeing it may cause a crash. If you wish to hide it or any of its children, use
     * their `Window.visible` property.
     *
     * Generated from Godot docs: MenuButton.get_popup
     */
    fun getPopup(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPopupBind, handle))
    }

    /**
     * Adjusts popup position and sizing for the `MenuButton`, then shows the `PopupMenu`. Prefer this
     * over using `get_popup().popup()`.
     *
     * Generated from Godot docs: MenuButton.show_popup
     */
    fun showPopup() {
        ObjectCalls.ptrcallNoArgs(showPopupBind, handle)
    }

    /**
     * If `true`, when the cursor hovers above another `MenuButton` within the same parent which also
     * has `switch_on_hover` enabled, it will close the current `MenuButton` and open the other one.
     *
     * Generated from Godot docs: MenuButton.set_switch_on_hover
     */
    fun setSwitchOnHover(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSwitchOnHoverBind, handle, enable)
    }

    /**
     * If `true`, when the cursor hovers above another `MenuButton` within the same parent which also
     * has `switch_on_hover` enabled, it will close the current `MenuButton` and open the other one.
     *
     * Generated from Godot docs: MenuButton.is_switch_on_hover
     */
    fun isSwitchOnHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSwitchOnHoverBind, handle)
    }

    /**
     * If `true`, shortcuts are disabled and cannot be used to trigger the button.
     *
     * Generated from Godot docs: MenuButton.set_disable_shortcuts
     */
    fun setDisableShortcuts(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableShortcutsBind, handle, disabled)
    }

    /**
     * The number of items currently in the list.
     *
     * Generated from Godot docs: MenuButton.set_item_count
     */
    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCountBind, handle, count)
    }

    /**
     * The number of items currently in the list.
     *
     * Generated from Godot docs: MenuButton.get_item_count
     */
    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

    object Signals {
        const val aboutToPopup: String = "about_to_popup"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MenuButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MenuButton? =
            if (handle.address() == 0L) null else MenuButton(handle)

        private const val GET_POPUP_HASH = 229722558L
        private val getPopupBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "get_popup", GET_POPUP_HASH)
        }

        private const val SHOW_POPUP_HASH = 3218959716L
        private val showPopupBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "show_popup", SHOW_POPUP_HASH)
        }

        private const val SET_SWITCH_ON_HOVER_HASH = 2586408642L
        private val setSwitchOnHoverBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "set_switch_on_hover", SET_SWITCH_ON_HOVER_HASH)
        }

        private const val IS_SWITCH_ON_HOVER_HASH = 2240911060L
        private val isSwitchOnHoverBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "is_switch_on_hover", IS_SWITCH_ON_HOVER_HASH)
        }

        private const val SET_DISABLE_SHORTCUTS_HASH = 2586408642L
        private val setDisableShortcutsBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "set_disable_shortcuts", SET_DISABLE_SHORTCUTS_HASH)
        }

        private const val SET_ITEM_COUNT_HASH = 1286410249L
        private val setItemCountBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "set_item_count", SET_ITEM_COUNT_HASH)
        }

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        private val getItemCountBind by lazy {
            ObjectCalls.getMethodBind("MenuButton", "get_item_count", GET_ITEM_COUNT_HASH)
        }
    }
}

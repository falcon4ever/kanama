package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plugin for adding custom context menus in the editor.
 *
 * Generated from Godot docs: EditorContextMenuPlugin
 */
class EditorContextMenuPlugin(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Registers a shortcut associated with the plugin's context menu. This method should be called
     * once (e.g. in plugin's `Object._init`). `callback` will be called when user presses the
     * specified `shortcut` while the menu's context is in effect (e.g. FileSystem dock is focused).
     * Callback should take single `Array` argument; array contents depend on context menu slot.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_menu_shortcut
     */
    fun addMenuShortcut(shortcut: Shortcut?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithObjectCallableArgs(addMenuShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    /**
     * Add custom option to the context menu of the plugin's specified slot. When the option is
     * activated, `callback` will be called. Callback should take single `Array` argument; array
     * contents depend on context menu slot.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_menu_item
     */
    fun addContextMenuItem(name: String, callback: GodotCallable, icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringCallableObjectArgs(addContextMenuItemBind, handle, name, callback.target.handle, callback.method, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Add custom option to the context menu of the plugin's specified slot. The option will have the
     * `shortcut` assigned and reuse its callback. The shortcut has to be registered beforehand with
     * `add_menu_shortcut`.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_menu_item_from_shortcut
     */
    fun addContextMenuItemFromShortcut(name: String, shortcut: Shortcut?, icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringAndTwoObjectArgs(addContextMenuItemFromShortcutBind, handle, name, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Add a submenu to the context menu of the plugin's specified slot. The submenu is not
     * automatically handled, you need to connect to its signals yourself. Also the submenu is freed on
     * every popup, so provide a new `PopupMenu` every time.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_submenu_item
     */
    fun addContextSubmenuItem(name: String, menu: PopupMenu, icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringAndTwoObjectArgs(addContextSubmenuItemBind, handle, name, menu.handle, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        const val CONTEXT_SLOT_SCENE_TREE: Long = 0L
        const val CONTEXT_SLOT_FILESYSTEM: Long = 1L
        const val CONTEXT_SLOT_SCRIPT_EDITOR: Long = 2L
        const val CONTEXT_SLOT_FILESYSTEM_CREATE: Long = 3L
        const val CONTEXT_SLOT_SCRIPT_EDITOR_CODE: Long = 4L
        const val CONTEXT_SLOT_SCENE_TABS: Long = 5L
        const val CONTEXT_SLOT_2D_EDITOR: Long = 6L
        const val CONTEXT_SLOT_INSPECTOR_PROPERTY: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorContextMenuPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorContextMenuPlugin? =
            if (handle.address() == 0L) null else EditorContextMenuPlugin(handle)

        private const val ADD_MENU_SHORTCUT_HASH = 851596305L
        private val addMenuShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_menu_shortcut", ADD_MENU_SHORTCUT_HASH)
        }

        private const val ADD_CONTEXT_MENU_ITEM_HASH = 2748336951L
        private val addContextMenuItemBind by lazy {
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_context_menu_item", ADD_CONTEXT_MENU_ITEM_HASH)
        }

        private const val ADD_CONTEXT_MENU_ITEM_FROM_SHORTCUT_HASH = 3799546916L
        private val addContextMenuItemFromShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_context_menu_item_from_shortcut", ADD_CONTEXT_MENU_ITEM_FROM_SHORTCUT_HASH)
        }

        private const val ADD_CONTEXT_SUBMENU_ITEM_HASH = 1994674995L
        private val addContextSubmenuItemBind by lazy {
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_context_submenu_item", ADD_CONTEXT_SUBMENU_ITEM_HASH)
        }
    }
}

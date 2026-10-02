package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Plugin for adding custom context menus in the editor.
 *
 * Generated from Godot docs: EditorContextMenuPlugin
 */
class EditorContextMenuPlugin(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Registers a shortcut associated with the plugin's context menu. This method should be called
     * once (e.g. in plugin's `Object._init`). `callback` will be called when user presses the
     * specified `shortcut` while the menu's context is in effect (e.g. FileSystem dock is focused).
     * Callback should take single `Array` argument; array contents depend on context menu slot.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_menu_shortcut
     */
    fun addMenuShortcut(shortcut: Shortcut?, callback: GodotCallable) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectCallableArgs(addMenuShortcutBind, segment, shortcut?.requireOpenHandle() ?: NULL_SEGMENT, callback.target.segment, callback.method)
    }

    /**
     * Add custom option to the context menu of the plugin's specified slot. When the option is
     * activated, `callback` will be called. Callback should take single `Array` argument; array
     * contents depend on context menu slot.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_menu_item
     */
    fun addContextMenuItem(name: String, callback: GodotCallable, icon: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringCallableObjectArgs(addContextMenuItemBind, segment, name, callback.target.segment, callback.method, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Add custom option to the context menu of the plugin's specified slot. The option will have the
     * `shortcut` assigned and reuse its callback. The shortcut has to be registered beforehand with
     * `add_menu_shortcut`.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_menu_item_from_shortcut
     */
    fun addContextMenuItemFromShortcut(name: String, shortcut: Shortcut?, icon: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndTwoObjectArgs(addContextMenuItemFromShortcutBind, segment, name, shortcut?.requireOpenHandle() ?: NULL_SEGMENT, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Add a submenu to the context menu of the plugin's specified slot. The submenu is not
     * automatically handled, you need to connect to its signals yourself. Also the submenu is freed on
     * every popup, so provide a new `PopupMenu` every time.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_submenu_item
     */
    fun addContextSubmenuItem(name: String, menu: PopupMenu, icon: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndTwoObjectArgs(addContextSubmenuItemBind, segment, name, menu.segment, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    @JvmInline
    value class ContextMenuSlot(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Context menu of Scene dock. `_popup_menu` will be called with a list of paths to currently
             * selected nodes, while option callback will receive the list of currently selected nodes.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCENE_TREE
             */
            val SCENE_TREE: ContextMenuSlot get() = ContextMenuSlot(0L)
            /**
             * Context menu of FileSystem dock. `_popup_menu` and option callback will be called with list of
             * paths of the currently selected files.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_FILESYSTEM
             */
            val FILESYSTEM: ContextMenuSlot get() = ContextMenuSlot(1L)
            /**
             * Context menu of Script editor's script tabs. `_popup_menu` will be called with the path to the
             * currently edited script, while option callback will receive reference to that script.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCRIPT_EDITOR
             */
            val SCRIPT_EDITOR: ContextMenuSlot get() = ContextMenuSlot(2L)
            /**
             * The "Create..." submenu of FileSystem dock's context menu, or the "New" section of the main
             * context menu when empty space is clicked. `_popup_menu` and option callback will be called with
             * the path of the currently selected folder. When clicking the empty space, the list of paths for
             * popup method will be empty.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_FILESYSTEM_CREATE
             */
            val FILESYSTEM_CREATE: ContextMenuSlot get() = ContextMenuSlot(3L)
            /**
             * Context menu of Script editor's code editor. `_popup_menu` will be called with the path to the
             * `CodeEdit` node. You can fetch it using this code:
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCRIPT_EDITOR_CODE
             */
            val SCRIPT_EDITOR_CODE: ContextMenuSlot get() = ContextMenuSlot(4L)
            /**
             * Context menu of scene tabs. `_popup_menu` will be called with the path of the clicked scene, or
             * empty `PackedStringArray` if the menu was opened on empty space. The option callback will
             * receive the path of the clicked scene, or empty `String` if none was clicked.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCENE_TABS
             */
            val SCENE_TABS: ContextMenuSlot get() = ContextMenuSlot(5L)
            /**
             * Context menu of 2D editor's basic right-click menu. `_popup_menu` will be called with paths to
             * all `CanvasItem` nodes under the cursor. You can fetch them using this code:
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_2D_EDITOR
             */
            val SLOT_2D_EDITOR: ContextMenuSlot get() = ContextMenuSlot(6L)
            /**
             * Context menu of the inspectors right-click menu. `_popup_menu` will be called with an array of
             * two items: The first will be the object's ID, the second will be the property name. An object
             * can be retrieved from it's ID via `@GlobalScope.instance_from_id` after converting it to an int.
             * The option callback will receive the EditorProperty directly.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_INSPECTOR_PROPERTY
             */
            val INSPECTOR_PROPERTY: ContextMenuSlot get() = ContextMenuSlot(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorContextMenuPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorContextMenuPlugin? =
            if (handle.address() == 0L) null else EditorContextMenuPlugin(GodotHandle(handle))

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

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Plugin for adding custom context menus in the editor.
 *
 * Generated from Godot docs: EditorContextMenuPlugin
 */
class EditorContextMenuPlugin(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Registers a shortcut associated with the plugin's context menu. This method should be called
     * once (e.g. in the plugin's `Object._init`). `callback` will be called when user presses the
     * specified `shortcut` while the menu's context is in effect (e.g. FileSystem dock is focused).
     * Callback should take single data `Dictionary`, same as in `add_context_menu_item`.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_menu_shortcut
     */
    fun addMenuShortcut(shortcut: Shortcut?, callback: GodotCallable) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectCallableArgs(Binds.addMenuShortcutBind, segment, shortcut?.requireOpenHandle() ?: NULL_SEGMENT, callback.target.segment, callback.method)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(shortcut)
        ObjectCalls.keepReachable(callback)
    }

    /**
     * Add custom option to the context menu of the plugin's specified slot. `callback` should be a
     * method that takes a single argument of type `Dictionary`. It will be called when the option is
     * activated and the data received in the callback is the same as in `_get_menu_options`.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_menu_item
     */
    fun addContextMenuItem(name: String, callback: GodotCallable, icon: Texture2D? = null) {
        checkOpen()
        ObjectCalls.ptrcallWithStringCallableObjectArgs(Binds.addContextMenuItemBind, segment, name, callback.target.segment, callback.method, icon?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(callback)
        ObjectCalls.keepReachable(icon)
    }

    /**
     * Add custom option to the context menu of the plugin's specified slot. The option will have the
     * `shortcut` assigned and reuse its callback. The shortcut has to be registered beforehand with
     * `add_menu_shortcut`.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_menu_item_from_shortcut
     */
    fun addContextMenuItemFromShortcut(name: String, shortcut: Shortcut?, icon: Texture2D? = null) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndTwoObjectArgs(Binds.addContextMenuItemFromShortcutBind, segment, name, shortcut?.requireOpenHandle() ?: NULL_SEGMENT, icon?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(shortcut)
        ObjectCalls.keepReachable(icon)
    }

    /**
     * Add a submenu to the context menu of the plugin's specified slot. The submenu is not
     * automatically handled, you need to connect to its signals yourself. Also the submenu is freed on
     * every popup, so provide a new `PopupMenu` every time.
     *
     * Generated from Godot docs: EditorContextMenuPlugin.add_context_submenu_item
     */
    fun addContextSubmenuItem(name: String, menu: PopupMenu, icon: Texture2D? = null) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndTwoObjectArgs(Binds.addContextSubmenuItemBind, segment, name, menu.segment, icon?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(menu)
        ObjectCalls.keepReachable(icon)
    }

    /**
     * Godot's `EditorContextMenuPlugin.ContextMenuSlot` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`EditorContextMenuPlugin.ContextMenuSlot.<NAME>`).
     *
     * Generated from Godot docs: EditorContextMenuPlugin.ContextMenuSlot
     */
    @JvmInline
    value class ContextMenuSlot(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Context menu of Scene dock. The data `Dictionary` contains: `&"selected_nodes": Array`Node`` -
             * The list of currently selected nodes (same as `EditorSelection.get_top_selected_nodes`).
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCENE_TREE
             */
            val SCENE_TREE: ContextMenuSlot get() = ContextMenuSlot(0L)
            /**
             * Context menu of FileSystem dock. The data `Dictionary` contains: `&"selected_files":
             * PackedStringArray` - The list of selected files.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_FILESYSTEM
             */
            val FILESYSTEM: ContextMenuSlot get() = ContextMenuSlot(1L)
            /**
             * Context menu of Script editor's script tabs and Shader editor's shader tabs. The data
             * `Dictionary` contains: `&"selected_resource": Resource` - The currently selected resource. Can
             * be a `Script`, a `Shader`, a text file, or `null` in case of help pages. `&"path": String` - The
             * path of the selected file. If a help page is selected, the path will be the name of the opened
             * class, prefixed with `help:`.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCRIPT_EDITOR
             */
            val SCRIPT_EDITOR: ContextMenuSlot get() = ContextMenuSlot(2L)
            /**
             * The "Create..." submenu of FileSystem dock's context menu, or the "New" section of the main
             * context menu when empty space is clicked. The data `Dictionary` contains: `&"base_directory":
             * String` - Path to the base directory where a file should be created. `&"needs_prefix": bool` -
             * Whether the option needs a "New" prefix (for consistency with native options).
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_FILESYSTEM_CREATE
             */
            val FILESYSTEM_CREATE: ContextMenuSlot get() = ContextMenuSlot(3L)
            /**
             * Context menu of Script editor's code editor. The data `Dictionary` contains: `&"code_edit":
             * CodeEdit` - The code editor instance. `&"file_path": String` - Path to the currently edited
             * file.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCRIPT_EDITOR_CODE
             */
            val SCRIPT_EDITOR_CODE: ContextMenuSlot get() = ContextMenuSlot(4L)
            /**
             * Context menu of scene tabs. The data `Dictionary` contains: `&"selected_scene": String` - Scene
             * path of the selected/clicked scene tab. Empty if empty space was clicked.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_SCENE_TABS
             */
            val SCENE_TABS: ContextMenuSlot get() = ContextMenuSlot(5L)
            /**
             * Context menu of the 2D editor's viewport. The data `Dictionary` contains: `&"hovered_nodes":
             * Array`CanvasItem`` - The `CanvasItem` nodes under the cursor. `&"mouse_position": Vector2` - The
             * local mouse position in the 2D scene.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_2D_EDITOR
             */
            val SLOT_2D_EDITOR: ContextMenuSlot get() = ContextMenuSlot(6L)
            /**
             * Context menu of an editor property in the inspector. The data `Dictionary` contains:
             * `&"property": EditorProperty` - The clicked `EditorProperty` object. `&"object": Object` - The
             * object edited by the inspector. `&"property_name": StringName` - The name of the object's
             * property that was clicked.
             *
             * Generated from Godot docs: EditorContextMenuPlugin.CONTEXT_SLOT_INSPECTOR_PROPERTY
             */
            val INSPECTOR_PROPERTY: ContextMenuSlot get() = ContextMenuSlot(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorContextMenuPlugin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorContextMenuPlugin? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorContextMenuPlugin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorContextMenuPlugin? =
            if (handle.address() == 0L) null else EditorContextMenuPlugin(GodotHandle(handle))

        /** A new EditorContextMenuPlugin, GDScript's `EditorContextMenuPlugin.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorContextMenuPlugin =
            RefCounted.owned(EditorContextMenuPlugin(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorContextMenuPlugin"), "EditorContextMenuPlugin"))))
    }

    private object Binds {
        private const val ADD_MENU_SHORTCUT_HASH = 851596305L
        @JvmField
        val addMenuShortcutBind =
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_menu_shortcut", ADD_MENU_SHORTCUT_HASH)

        private const val ADD_CONTEXT_MENU_ITEM_HASH = 2748336951L
        @JvmField
        val addContextMenuItemBind =
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_context_menu_item", ADD_CONTEXT_MENU_ITEM_HASH)

        private const val ADD_CONTEXT_MENU_ITEM_FROM_SHORTCUT_HASH = 3799546916L
        @JvmField
        val addContextMenuItemFromShortcutBind =
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_context_menu_item_from_shortcut", ADD_CONTEXT_MENU_ITEM_FROM_SHORTCUT_HASH)

        private const val ADD_CONTEXT_SUBMENU_ITEM_HASH = 1994674995L
        @JvmField
        val addContextSubmenuItemBind =
            ObjectCalls.getMethodBind("EditorContextMenuPlugin", "add_context_submenu_item", ADD_CONTEXT_SUBMENU_ITEM_HASH)
    }
}

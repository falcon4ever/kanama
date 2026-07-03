package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Used by the editor to extend its functionality.
 *
 * Generated from Godot docs: EditorPlugin
 */
open class EditorPlugin(handle: MemorySegment) : Node(handle) {
    /**
     * Adds a new dock. When your plugin is deactivated, make sure to remove your custom dock with
     * `remove_dock` and free it with `Node.queue_free`.
     *
     * Generated from Godot docs: EditorPlugin.add_dock
     */
    fun addDock(dock: EditorDock) {
        ObjectCalls.ptrcallWithObjectArgs(addDockBind, handle, listOf(dock.handle))
    }

    /**
     * Removes `dock` from the available docks. You should manually call `Node.queue_free` to free it.
     *
     * Generated from Godot docs: EditorPlugin.remove_dock
     */
    fun removeDock(dock: EditorDock) {
        ObjectCalls.ptrcallWithObjectArgs(removeDockBind, handle, listOf(dock.handle))
    }

    /**
     * Adds a custom control to a container in the editor UI. Please remember that you have to manage
     * the visibility of your custom controls yourself (and likely hide it after adding it). When your
     * plugin is deactivated, make sure to remove your custom control with
     * `remove_control_from_container` and free it with `Node.queue_free`.
     *
     * Generated from Godot docs: EditorPlugin.add_control_to_container
     */
    fun addControlToContainer(container: Long, control: Control) {
        ObjectCalls.ptrcallWithLongAndObjectArg(addControlToContainerBind, handle, container, control.handle)
    }

    /**
     * Removes the control from the specified container. You have to manually `Node.queue_free` the
     * control.
     *
     * Generated from Godot docs: EditorPlugin.remove_control_from_container
     */
    fun removeControlFromContainer(container: Long, control: Control) {
        ObjectCalls.ptrcallWithLongAndObjectArg(removeControlFromContainerBind, handle, container, control.handle)
    }

    /**
     * Adds a custom menu item to Project > Tools named `name`. When clicked, the provided `callable`
     * will be called.
     *
     * Generated from Godot docs: EditorPlugin.add_tool_menu_item
     */
    fun addToolMenuItem(name: String, callable: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(addToolMenuItemBind, handle, name, callable.target.handle, callable.method)
    }

    /**
     * Adds a custom `PopupMenu` submenu under Project > Tools > `name`. Use `remove_tool_menu_item` on
     * plugin clean up to remove the menu.
     *
     * Generated from Godot docs: EditorPlugin.add_tool_submenu_item
     */
    fun addToolSubmenuItem(name: String, submenu: PopupMenu) {
        ObjectCalls.ptrcallWithStringAndObjectArg(addToolSubmenuItemBind, handle, name, submenu.handle)
    }

    /**
     * Removes a menu `name` from Project > Tools.
     *
     * Generated from Godot docs: EditorPlugin.remove_tool_menu_item
     */
    fun removeToolMenuItem(name: String) {
        ObjectCalls.ptrcallWithStringArg(removeToolMenuItemBind, handle, name)
    }

    /**
     * Returns the `PopupMenu` under Scene > Export As....
     *
     * Generated from Godot docs: EditorPlugin.get_export_as_menu
     */
    fun getExportAsMenu(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExportAsMenuBind, handle))
    }

    /**
     * Adds a custom type, which will appear in the list of nodes or resources. When a given node or
     * resource is selected, the base type will be instantiated (e.g. "Node3D", "Control", "Resource"),
     * then the script will be loaded and set to this object. Note: The base type is the base engine
     * class which this type's class hierarchy inherits, not any custom type parent classes. You can
     * use the virtual method `_handles` to check if your custom object is being edited by checking the
     * script or using the `is` keyword. During run-time, this will be a simple object with a script so
     * this function does not need to be called then. Note: Custom types added this way are not true
     * classes. They are just a helper to create a node with specific script.
     *
     * Generated from Godot docs: EditorPlugin.add_custom_type
     */
    fun addCustomType(type: String, base: String, script: Script?, icon: Texture2D?) {
        ObjectCalls.ptrcallWithTwoStringTwoObjectArgs(addCustomTypeBind, handle, type, base, script?.requireOpenHandle() ?: MemorySegment.NULL, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes a custom type added by `add_custom_type`.
     *
     * Generated from Godot docs: EditorPlugin.remove_custom_type
     */
    fun removeCustomType(type: String) {
        ObjectCalls.ptrcallWithStringArg(removeCustomTypeBind, handle, type)
    }

    /**
     * Adds the control to a specific dock slot. If the dock is repositioned and as long as the plugin
     * is active, the editor will save the dock position on further sessions. When your plugin is
     * deactivated, make sure to remove your custom control with `remove_control_from_docks` and free
     * it with `Node.queue_free`. Optionally, you can specify a shortcut parameter. When pressed, this
     * shortcut will open and focus the dock.
     *
     * Generated from Godot docs: EditorPlugin.add_control_to_dock
     */
    fun addControlToDock(slot: Long, control: Control, shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithLongAndTwoObjectArgs(addControlToDockBind, handle, slot, control.handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the control from the dock. You have to manually `Node.queue_free` the control.
     *
     * Generated from Godot docs: EditorPlugin.remove_control_from_docks
     */
    fun removeControlFromDocks(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(removeControlFromDocksBind, handle, listOf(control.handle))
    }

    /**
     * Sets the tab icon for the given control in a dock slot. Setting to `null` removes the icon.
     *
     * Generated from Godot docs: EditorPlugin.set_dock_tab_icon
     */
    fun setDockTabIcon(control: Control, icon: Texture2D?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setDockTabIconBind, handle, control.handle, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Adds a control to the bottom panel (together with Output, Debug, Animation, etc.). Returns a
     * reference to a button that is outside the scene tree. It's up to you to hide/show the button
     * when needed. When your plugin is deactivated, make sure to remove your custom control with
     * `remove_control_from_bottom_panel` and free it with `Node.queue_free`. `shortcut` is a shortcut
     * that, when activated, will toggle the bottom panel's visibility. The shortcut object is only set
     * when this control is added to the bottom panel. Note See the default editor bottom panel
     * shortcuts in the Editor Settings for inspiration. By convention, they all use Alt modifier.
     *
     * Generated from Godot docs: EditorPlugin.add_control_to_bottom_panel
     */
    fun addControlToBottomPanel(control: Control, title: String, shortcut: Shortcut?): Button? {
        return Button.wrap(ObjectCalls.ptrcallWithObjectStringObjectArgsRetObject(addControlToBottomPanelBind, handle, control.handle, title, shortcut?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes the control from the bottom panel. You have to manually `Node.queue_free` the control.
     *
     * Generated from Godot docs: EditorPlugin.remove_control_from_bottom_panel
     */
    fun removeControlFromBottomPanel(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(removeControlFromBottomPanelBind, handle, listOf(control.handle))
    }

    /**
     * Adds a script at `path` to the Autoload list as `name`.
     *
     * Generated from Godot docs: EditorPlugin.add_autoload_singleton
     */
    fun addAutoloadSingleton(name: String, path: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(addAutoloadSingletonBind, handle, name, path)
    }

    /**
     * Removes an Autoload `name` from the list.
     *
     * Generated from Godot docs: EditorPlugin.remove_autoload_singleton
     */
    fun removeAutoloadSingleton(name: String) {
        ObjectCalls.ptrcallWithStringArg(removeAutoloadSingletonBind, handle, name)
    }

    /**
     * Updates the overlays of the 2D and 3D editor viewport. Causes methods
     * `_forward_canvas_draw_over_viewport`, `_forward_canvas_force_draw_over_viewport`,
     * `_forward_3d_draw_over_viewport` and `_forward_3d_force_draw_over_viewport` to be called.
     *
     * Generated from Godot docs: EditorPlugin.update_overlays
     */
    fun updateOverlays(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(updateOverlaysBind, handle)
    }

    /**
     * Makes a specific item in the bottom panel visible.
     *
     * Generated from Godot docs: EditorPlugin.make_bottom_panel_item_visible
     */
    fun makeBottomPanelItemVisible(item: Control) {
        ObjectCalls.ptrcallWithObjectArgs(makeBottomPanelItemVisibleBind, handle, listOf(item.handle))
    }

    /**
     * Minimizes the bottom panel.
     *
     * Generated from Godot docs: EditorPlugin.hide_bottom_panel
     */
    fun hideBottomPanel() {
        ObjectCalls.ptrcallNoArgs(hideBottomPanelBind, handle)
    }

    /**
     * Gets the undo/redo object. Most actions in the editor can be undoable, so use this object to
     * make sure this happens when it's worth it.
     *
     * Generated from Godot docs: EditorPlugin.get_undo_redo
     */
    fun getUndoRedo(): EditorUndoRedoManager? {
        return EditorUndoRedoManager.wrap(ObjectCalls.ptrcallNoArgsRetObject(getUndoRedoBind, handle))
    }

    /**
     * Hooks a callback into the undo/redo action creation when a property is modified in the
     * inspector. This allows, for example, to save other properties that may be lost when a given
     * property is modified. The callback should have 4 arguments: `Object` `undo_redo`, `Object`
     * `modified_object`, `String` `property` and `Variant` `new_value`. They are, respectively, the
     * `UndoRedo` object used by the inspector, the currently modified object, the name of the modified
     * property and the new value the property is about to take.
     *
     * Generated from Godot docs: EditorPlugin.add_undo_redo_inspector_hook_callback
     */
    fun addUndoRedoInspectorHookCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(addUndoRedoInspectorHookCallbackBind, handle, callable.target.handle, callable.method)
    }

    /**
     * Removes a callback previously added by `add_undo_redo_inspector_hook_callback`.
     *
     * Generated from Godot docs: EditorPlugin.remove_undo_redo_inspector_hook_callback
     */
    fun removeUndoRedoInspectorHookCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(removeUndoRedoInspectorHookCallbackBind, handle, callable.target.handle, callable.method)
    }

    /**
     * Queue save the project's editor layout.
     *
     * Generated from Godot docs: EditorPlugin.queue_save_layout
     */
    fun queueSaveLayout() {
        ObjectCalls.ptrcallNoArgs(queueSaveLayoutBind, handle)
    }

    /**
     * Registers a custom translation parser plugin for extracting translatable strings from custom
     * files.
     *
     * Generated from Godot docs: EditorPlugin.add_translation_parser_plugin
     */
    fun addTranslationParserPlugin(parser: EditorTranslationParserPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addTranslationParserPluginBind, handle, listOf(parser?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes a custom translation parser plugin registered by `add_translation_parser_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_translation_parser_plugin
     */
    fun removeTranslationParserPlugin(parser: EditorTranslationParserPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeTranslationParserPluginBind, handle, listOf(parser?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorImportPlugin`. Import plugins are used to import custom and unsupported
     * assets as a custom `Resource` type. If `first_priority` is `true`, the new import plugin is
     * inserted first in the list and takes precedence over pre-existing plugins. Note: If you want to
     * import custom 3D asset formats use `add_scene_format_importer_plugin` instead. See
     * `add_inspector_plugin` for an example of how to register a plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_import_plugin
     */
    fun addImportPlugin(importer: EditorImportPlugin?, firstPriority: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(addImportPluginBind, handle, importer?.requireOpenHandle() ?: MemorySegment.NULL, firstPriority)
    }

    /**
     * Removes an import plugin registered by `add_import_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_import_plugin
     */
    fun removeImportPlugin(importer: EditorImportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeImportPluginBind, handle, listOf(importer?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorSceneFormatImporter`. Scene importers are used to import custom 3D asset
     * formats as scenes. If `first_priority` is `true`, the new import plugin is inserted first in the
     * list and takes precedence over pre-existing plugins.
     *
     * Generated from Godot docs: EditorPlugin.add_scene_format_importer_plugin
     */
    fun addSceneFormatImporterPlugin(sceneFormatImporter: EditorSceneFormatImporter?, firstPriority: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(addSceneFormatImporterPluginBind, handle, sceneFormatImporter?.requireOpenHandle() ?: MemorySegment.NULL, firstPriority)
    }

    /**
     * Removes a scene format importer registered by `add_scene_format_importer_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_scene_format_importer_plugin
     */
    fun removeSceneFormatImporterPlugin(sceneFormatImporter: EditorSceneFormatImporter?) {
        ObjectCalls.ptrcallWithObjectArgs(removeSceneFormatImporterPluginBind, handle, listOf(sceneFormatImporter?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Add an `EditorScenePostImportPlugin`. These plugins allow customizing the import process of 3D
     * assets by adding new options to the import dialogs. If `first_priority` is `true`, the new
     * import plugin is inserted first in the list and takes precedence over pre-existing plugins.
     *
     * Generated from Godot docs: EditorPlugin.add_scene_post_import_plugin
     */
    fun addScenePostImportPlugin(sceneImportPlugin: EditorScenePostImportPlugin?, firstPriority: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(addScenePostImportPluginBind, handle, sceneImportPlugin?.requireOpenHandle() ?: MemorySegment.NULL, firstPriority)
    }

    /**
     * Remove the `EditorScenePostImportPlugin`, added with `add_scene_post_import_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_scene_post_import_plugin
     */
    fun removeScenePostImportPlugin(sceneImportPlugin: EditorScenePostImportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeScenePostImportPluginBind, handle, listOf(sceneImportPlugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorExportPlugin`. Export plugins are used to perform tasks when the project
     * is being exported. See `add_inspector_plugin` for an example of how to register a plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_export_plugin
     */
    fun addExportPlugin(plugin: EditorExportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addExportPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes an export plugin registered by `add_export_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_export_plugin
     */
    fun removeExportPlugin(plugin: EditorExportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeExportPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorExportPlatform`. Export platforms provides functionality of exporting to
     * the specific platform.
     *
     * Generated from Godot docs: EditorPlugin.add_export_platform
     */
    fun addExportPlatform(platform: EditorExportPlatform?) {
        ObjectCalls.ptrcallWithObjectArgs(addExportPlatformBind, handle, listOf(platform?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes an export platform registered by `add_export_platform`.
     *
     * Generated from Godot docs: EditorPlugin.remove_export_platform
     */
    fun removeExportPlatform(platform: EditorExportPlatform?) {
        ObjectCalls.ptrcallWithObjectArgs(removeExportPlatformBind, handle, listOf(platform?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorNode3DGizmoPlugin`. Gizmo plugins are used to add custom gizmos to the 3D
     * preview viewport for a `Node3D`. See `add_inspector_plugin` for an example of how to register a
     * plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_node_3d_gizmo_plugin
     */
    fun addNode3dGizmoPlugin(plugin: EditorNode3DGizmoPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addNode3dGizmoPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes a gizmo plugin registered by `add_node_3d_gizmo_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_node_3d_gizmo_plugin
     */
    fun removeNode3dGizmoPlugin(plugin: EditorNode3DGizmoPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeNode3dGizmoPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorInspectorPlugin`. Inspector plugins are used to extend `EditorInspector`
     * and provide custom configuration tools for your object's properties. Note: Always use
     * `remove_inspector_plugin` to remove the registered `EditorInspectorPlugin` when your
     * `EditorPlugin` is disabled to prevent leaks and an unexpected behavior.
     *
     * Generated from Godot docs: EditorPlugin.add_inspector_plugin
     */
    fun addInspectorPlugin(plugin: EditorInspectorPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addInspectorPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes an inspector plugin registered by `add_inspector_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_inspector_plugin
     */
    fun removeInspectorPlugin(plugin: EditorInspectorPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeInspectorPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Registers a new `EditorResourceConversionPlugin`. Resource conversion plugins are used to add
     * custom resource converters to the editor inspector. See `EditorResourceConversionPlugin` for an
     * example of how to create a resource conversion plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_resource_conversion_plugin
     */
    fun addResourceConversionPlugin(plugin: EditorResourceConversionPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addResourceConversionPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes a resource conversion plugin registered by `add_resource_conversion_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_resource_conversion_plugin
     */
    fun removeResourceConversionPlugin(plugin: EditorResourceConversionPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeResourceConversionPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Use this method if you always want to receive inputs from 3D view screen inside
     * `_forward_3d_gui_input`. It might be especially usable if your plugin will want to use raycast
     * in the scene.
     *
     * Generated from Godot docs: EditorPlugin.set_input_event_forwarding_always_enabled
     */
    fun setInputEventForwardingAlwaysEnabled() {
        ObjectCalls.ptrcallNoArgs(setInputEventForwardingAlwaysEnabledBind, handle)
    }

    /**
     * Enables calling of `_forward_canvas_force_draw_over_viewport` for the 2D editor and
     * `_forward_3d_force_draw_over_viewport` for the 3D editor when their viewports are updated. You
     * need to call this method only once and it will work permanently for this plugin.
     *
     * Generated from Godot docs: EditorPlugin.set_force_draw_over_forwarding_enabled
     */
    fun setForceDrawOverForwardingEnabled() {
        ObjectCalls.ptrcallNoArgs(setForceDrawOverForwardingEnabledBind, handle)
    }

    /**
     * Adds a plugin to the context menu. `slot` is the context menu where the plugin will be added.
     * Note: A plugin instance can belong only to a single context menu slot.
     *
     * Generated from Godot docs: EditorPlugin.add_context_menu_plugin
     */
    fun addContextMenuPlugin(slot: Long, plugin: EditorContextMenuPlugin?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(addContextMenuPluginBind, handle, slot, plugin?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the specified context menu plugin.
     *
     * Generated from Godot docs: EditorPlugin.remove_context_menu_plugin
     */
    fun removeContextMenuPlugin(plugin: EditorContextMenuPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeContextMenuPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the `EditorInterface` singleton instance.
     *
     * Generated from Godot docs: EditorPlugin.get_editor_interface
     */
    fun getEditorInterface(): EditorInterface? {
        return EditorInterface.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorInterfaceBind, handle))
    }

    /**
     * Gets the Editor's dialog used for making scripts. Note: Users can configure it before use.
     * Warning: Removing and freeing this node will render a part of the editor useless and may cause a
     * crash.
     *
     * Generated from Godot docs: EditorPlugin.get_script_create_dialog
     */
    fun getScriptCreateDialog(): ScriptCreateDialog? {
        return ScriptCreateDialog.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScriptCreateDialogBind, handle))
    }

    /**
     * Adds a `Script` as debugger plugin to the Debugger. The script must extend
     * `EditorDebuggerPlugin`.
     *
     * Generated from Godot docs: EditorPlugin.add_debugger_plugin
     */
    fun addDebuggerPlugin(script: EditorDebuggerPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addDebuggerPluginBind, handle, listOf(script?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes the debugger plugin with given script from the Debugger.
     *
     * Generated from Godot docs: EditorPlugin.remove_debugger_plugin
     */
    fun removeDebuggerPlugin(script: EditorDebuggerPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeDebuggerPluginBind, handle, listOf(script?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Provide the version of the plugin declared in the `plugin.cfg` config file.
     *
     * Generated from Godot docs: EditorPlugin.get_plugin_version
     */
    fun getPluginVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPluginVersionBind, handle)
    }

    object Signals {
        const val sceneChanged: String = "scene_changed"
        const val sceneClosed: String = "scene_closed"
        const val mainScreenChanged: String = "main_screen_changed"
        const val resourceSaved: String = "resource_saved"
        const val sceneSaved: String = "scene_saved"
        const val projectSettingsChanged: String = "project_settings_changed"
    }

    companion object {
        const val CONTAINER_TOOLBAR: Long = 0L
        const val CONTAINER_SPATIAL_EDITOR_MENU: Long = 1L
        const val CONTAINER_SPATIAL_EDITOR_SIDE_LEFT: Long = 2L
        const val CONTAINER_SPATIAL_EDITOR_SIDE_RIGHT: Long = 3L
        const val CONTAINER_SPATIAL_EDITOR_BOTTOM: Long = 4L
        const val CONTAINER_CANVAS_EDITOR_MENU: Long = 5L
        const val CONTAINER_CANVAS_EDITOR_SIDE_LEFT: Long = 6L
        const val CONTAINER_CANVAS_EDITOR_SIDE_RIGHT: Long = 7L
        const val CONTAINER_CANVAS_EDITOR_BOTTOM: Long = 8L
        const val CONTAINER_INSPECTOR_BOTTOM: Long = 9L
        const val CONTAINER_PROJECT_SETTING_TAB_LEFT: Long = 10L
        const val CONTAINER_PROJECT_SETTING_TAB_RIGHT: Long = 11L
        const val DOCK_SLOT_NONE: Long = -1L
        const val DOCK_SLOT_LEFT_UL: Long = 0L
        const val DOCK_SLOT_LEFT_BL: Long = 1L
        const val DOCK_SLOT_LEFT_UR: Long = 2L
        const val DOCK_SLOT_LEFT_BR: Long = 3L
        const val DOCK_SLOT_RIGHT_UL: Long = 4L
        const val DOCK_SLOT_RIGHT_BL: Long = 5L
        const val DOCK_SLOT_RIGHT_UR: Long = 6L
        const val DOCK_SLOT_RIGHT_BR: Long = 7L
        const val DOCK_SLOT_BOTTOM: Long = 8L
        const val DOCK_SLOT_MAX: Long = 9L
        const val AFTER_GUI_INPUT_PASS: Long = 0L
        const val AFTER_GUI_INPUT_STOP: Long = 1L
        const val AFTER_GUI_INPUT_CUSTOM: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorPlugin? =
            if (handle.address() == 0L) null else EditorPlugin(handle)

        private const val ADD_DOCK_HASH = 158651717L
        private val addDockBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_dock", ADD_DOCK_HASH)
        }

        private const val REMOVE_DOCK_HASH = 158651717L
        private val removeDockBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_dock", REMOVE_DOCK_HASH)
        }

        private const val ADD_CONTROL_TO_CONTAINER_HASH = 3092750152L
        private val addControlToContainerBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_control_to_container", ADD_CONTROL_TO_CONTAINER_HASH)
        }

        private const val REMOVE_CONTROL_FROM_CONTAINER_HASH = 3092750152L
        private val removeControlFromContainerBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_control_from_container", REMOVE_CONTROL_FROM_CONTAINER_HASH)
        }

        private const val ADD_TOOL_MENU_ITEM_HASH = 2137474292L
        private val addToolMenuItemBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_tool_menu_item", ADD_TOOL_MENU_ITEM_HASH)
        }

        private const val ADD_TOOL_SUBMENU_ITEM_HASH = 1019428915L
        private val addToolSubmenuItemBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_tool_submenu_item", ADD_TOOL_SUBMENU_ITEM_HASH)
        }

        private const val REMOVE_TOOL_MENU_ITEM_HASH = 83702148L
        private val removeToolMenuItemBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_tool_menu_item", REMOVE_TOOL_MENU_ITEM_HASH)
        }

        private const val GET_EXPORT_AS_MENU_HASH = 1775878644L
        private val getExportAsMenuBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "get_export_as_menu", GET_EXPORT_AS_MENU_HASH)
        }

        private const val ADD_CUSTOM_TYPE_HASH = 1986814599L
        private val addCustomTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_custom_type", ADD_CUSTOM_TYPE_HASH)
        }

        private const val REMOVE_CUSTOM_TYPE_HASH = 83702148L
        private val removeCustomTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_custom_type", REMOVE_CUSTOM_TYPE_HASH)
        }

        private const val ADD_CONTROL_TO_DOCK_HASH = 2994930786L
        private val addControlToDockBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_control_to_dock", ADD_CONTROL_TO_DOCK_HASH)
        }

        private const val REMOVE_CONTROL_FROM_DOCKS_HASH = 1496901182L
        private val removeControlFromDocksBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_control_from_docks", REMOVE_CONTROL_FROM_DOCKS_HASH)
        }

        private const val SET_DOCK_TAB_ICON_HASH = 3450529724L
        private val setDockTabIconBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "set_dock_tab_icon", SET_DOCK_TAB_ICON_HASH)
        }

        private const val ADD_CONTROL_TO_BOTTOM_PANEL_HASH = 111032269L
        private val addControlToBottomPanelBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_control_to_bottom_panel", ADD_CONTROL_TO_BOTTOM_PANEL_HASH)
        }

        private const val REMOVE_CONTROL_FROM_BOTTOM_PANEL_HASH = 1496901182L
        private val removeControlFromBottomPanelBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_control_from_bottom_panel", REMOVE_CONTROL_FROM_BOTTOM_PANEL_HASH)
        }

        private const val ADD_AUTOLOAD_SINGLETON_HASH = 3186203200L
        private val addAutoloadSingletonBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_autoload_singleton", ADD_AUTOLOAD_SINGLETON_HASH)
        }

        private const val REMOVE_AUTOLOAD_SINGLETON_HASH = 83702148L
        private val removeAutoloadSingletonBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_autoload_singleton", REMOVE_AUTOLOAD_SINGLETON_HASH)
        }

        private const val UPDATE_OVERLAYS_HASH = 3905245786L
        private val updateOverlaysBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "update_overlays", UPDATE_OVERLAYS_HASH)
        }

        private const val MAKE_BOTTOM_PANEL_ITEM_VISIBLE_HASH = 1496901182L
        private val makeBottomPanelItemVisibleBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "make_bottom_panel_item_visible", MAKE_BOTTOM_PANEL_ITEM_VISIBLE_HASH)
        }

        private const val HIDE_BOTTOM_PANEL_HASH = 3218959716L
        private val hideBottomPanelBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "hide_bottom_panel", HIDE_BOTTOM_PANEL_HASH)
        }

        private const val GET_UNDO_REDO_HASH = 773492341L
        private val getUndoRedoBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "get_undo_redo", GET_UNDO_REDO_HASH)
        }

        private const val ADD_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH = 1611583062L
        private val addUndoRedoInspectorHookCallbackBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_undo_redo_inspector_hook_callback", ADD_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH)
        }

        private const val REMOVE_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH = 1611583062L
        private val removeUndoRedoInspectorHookCallbackBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_undo_redo_inspector_hook_callback", REMOVE_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH)
        }

        private const val QUEUE_SAVE_LAYOUT_HASH = 3218959716L
        private val queueSaveLayoutBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "queue_save_layout", QUEUE_SAVE_LAYOUT_HASH)
        }

        private const val ADD_TRANSLATION_PARSER_PLUGIN_HASH = 3116463128L
        private val addTranslationParserPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_translation_parser_plugin", ADD_TRANSLATION_PARSER_PLUGIN_HASH)
        }

        private const val REMOVE_TRANSLATION_PARSER_PLUGIN_HASH = 3116463128L
        private val removeTranslationParserPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_translation_parser_plugin", REMOVE_TRANSLATION_PARSER_PLUGIN_HASH)
        }

        private const val ADD_IMPORT_PLUGIN_HASH = 3113975762L
        private val addImportPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_import_plugin", ADD_IMPORT_PLUGIN_HASH)
        }

        private const val REMOVE_IMPORT_PLUGIN_HASH = 2312482773L
        private val removeImportPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_import_plugin", REMOVE_IMPORT_PLUGIN_HASH)
        }

        private const val ADD_SCENE_FORMAT_IMPORTER_PLUGIN_HASH = 2764104752L
        private val addSceneFormatImporterPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_scene_format_importer_plugin", ADD_SCENE_FORMAT_IMPORTER_PLUGIN_HASH)
        }

        private const val REMOVE_SCENE_FORMAT_IMPORTER_PLUGIN_HASH = 2637776123L
        private val removeSceneFormatImporterPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_scene_format_importer_plugin", REMOVE_SCENE_FORMAT_IMPORTER_PLUGIN_HASH)
        }

        private const val ADD_SCENE_POST_IMPORT_PLUGIN_HASH = 3492436322L
        private val addScenePostImportPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_scene_post_import_plugin", ADD_SCENE_POST_IMPORT_PLUGIN_HASH)
        }

        private const val REMOVE_SCENE_POST_IMPORT_PLUGIN_HASH = 3045178206L
        private val removeScenePostImportPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_scene_post_import_plugin", REMOVE_SCENE_POST_IMPORT_PLUGIN_HASH)
        }

        private const val ADD_EXPORT_PLUGIN_HASH = 4095952207L
        private val addExportPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_export_plugin", ADD_EXPORT_PLUGIN_HASH)
        }

        private const val REMOVE_EXPORT_PLUGIN_HASH = 4095952207L
        private val removeExportPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_export_plugin", REMOVE_EXPORT_PLUGIN_HASH)
        }

        private const val ADD_EXPORT_PLATFORM_HASH = 3431312373L
        private val addExportPlatformBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_export_platform", ADD_EXPORT_PLATFORM_HASH)
        }

        private const val REMOVE_EXPORT_PLATFORM_HASH = 3431312373L
        private val removeExportPlatformBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_export_platform", REMOVE_EXPORT_PLATFORM_HASH)
        }

        private const val ADD_NODE_3D_GIZMO_PLUGIN_HASH = 1541015022L
        private val addNode3dGizmoPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_node_3d_gizmo_plugin", ADD_NODE_3D_GIZMO_PLUGIN_HASH)
        }

        private const val REMOVE_NODE_3D_GIZMO_PLUGIN_HASH = 1541015022L
        private val removeNode3dGizmoPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_node_3d_gizmo_plugin", REMOVE_NODE_3D_GIZMO_PLUGIN_HASH)
        }

        private const val ADD_INSPECTOR_PLUGIN_HASH = 546395733L
        private val addInspectorPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_inspector_plugin", ADD_INSPECTOR_PLUGIN_HASH)
        }

        private const val REMOVE_INSPECTOR_PLUGIN_HASH = 546395733L
        private val removeInspectorPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_inspector_plugin", REMOVE_INSPECTOR_PLUGIN_HASH)
        }

        private const val ADD_RESOURCE_CONVERSION_PLUGIN_HASH = 2124849111L
        private val addResourceConversionPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_resource_conversion_plugin", ADD_RESOURCE_CONVERSION_PLUGIN_HASH)
        }

        private const val REMOVE_RESOURCE_CONVERSION_PLUGIN_HASH = 2124849111L
        private val removeResourceConversionPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_resource_conversion_plugin", REMOVE_RESOURCE_CONVERSION_PLUGIN_HASH)
        }

        private const val SET_INPUT_EVENT_FORWARDING_ALWAYS_ENABLED_HASH = 3218959716L
        private val setInputEventForwardingAlwaysEnabledBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "set_input_event_forwarding_always_enabled", SET_INPUT_EVENT_FORWARDING_ALWAYS_ENABLED_HASH)
        }

        private const val SET_FORCE_DRAW_OVER_FORWARDING_ENABLED_HASH = 3218959716L
        private val setForceDrawOverForwardingEnabledBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "set_force_draw_over_forwarding_enabled", SET_FORCE_DRAW_OVER_FORWARDING_ENABLED_HASH)
        }

        private const val ADD_CONTEXT_MENU_PLUGIN_HASH = 1904221872L
        private val addContextMenuPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_context_menu_plugin", ADD_CONTEXT_MENU_PLUGIN_HASH)
        }

        private const val REMOVE_CONTEXT_MENU_PLUGIN_HASH = 2281511854L
        private val removeContextMenuPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_context_menu_plugin", REMOVE_CONTEXT_MENU_PLUGIN_HASH)
        }

        private const val GET_EDITOR_INTERFACE_HASH = 4223731786L
        private val getEditorInterfaceBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "get_editor_interface", GET_EDITOR_INTERFACE_HASH)
        }

        private const val GET_SCRIPT_CREATE_DIALOG_HASH = 3121871482L
        private val getScriptCreateDialogBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "get_script_create_dialog", GET_SCRIPT_CREATE_DIALOG_HASH)
        }

        private const val ADD_DEBUGGER_PLUGIN_HASH = 3749880309L
        private val addDebuggerPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "add_debugger_plugin", ADD_DEBUGGER_PLUGIN_HASH)
        }

        private const val REMOVE_DEBUGGER_PLUGIN_HASH = 3749880309L
        private val removeDebuggerPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "remove_debugger_plugin", REMOVE_DEBUGGER_PLUGIN_HASH)
        }

        private const val GET_PLUGIN_VERSION_HASH = 201670096L
        private val getPluginVersionBind by lazy {
            ObjectCalls.getMethodBind("EditorPlugin", "get_plugin_version", GET_PLUGIN_VERSION_HASH)
        }
    }
}

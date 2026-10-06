package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Used by the editor to extend its functionality.
 *
 * Generated from Godot docs: EditorPlugin
 */
open class EditorPlugin(handle: GodotHandle) : Node(handle) {
    /**
     * Adds a new dock. When your plugin is deactivated, make sure to remove your custom dock with
     * `remove_dock` and free it with `Node.queue_free`.
     *
     * Generated from Godot docs: EditorPlugin.add_dock
     */
    fun addDock(dock: EditorDock) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addDockBind, segment, listOf(dock.segment))
    }

    /**
     * Removes `dock` from the available docks. You should manually call `Node.queue_free` to free it.
     *
     * Generated from Godot docs: EditorPlugin.remove_dock
     */
    fun removeDock(dock: EditorDock) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeDockBind, segment, listOf(dock.segment))
    }

    /**
     * Adds a custom control to a container in the editor UI. Please remember that you have to manage
     * the visibility of your custom controls yourself (and likely hide it after adding it). When your
     * plugin is deactivated, make sure to remove your custom control with
     * `remove_control_from_container` and free it with `Node.queue_free`.
     *
     * Generated from Godot docs: EditorPlugin.add_control_to_container
     */
    fun addControlToContainer(container: EditorPlugin.CustomControlContainer, control: Control) {
        ObjectCalls.ptrcallWithLongAndObjectArg(Binds.addControlToContainerBind, segment, container.value, control.segment)
    }

    /**
     * Removes the control from the specified container. You have to manually `Node.queue_free` the
     * control.
     *
     * Generated from Godot docs: EditorPlugin.remove_control_from_container
     */
    fun removeControlFromContainer(container: EditorPlugin.CustomControlContainer, control: Control) {
        ObjectCalls.ptrcallWithLongAndObjectArg(Binds.removeControlFromContainerBind, segment, container.value, control.segment)
    }

    /**
     * Adds a custom menu item to Project > Tools named `name`. When clicked, the provided `callable`
     * will be called.
     *
     * Generated from Godot docs: EditorPlugin.add_tool_menu_item
     */
    fun addToolMenuItem(name: String, callable: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(Binds.addToolMenuItemBind, segment, name, callable.target.segment, callable.method)
    }

    /**
     * Adds a custom `PopupMenu` submenu under Project > Tools > `name`. Use `remove_tool_menu_item` on
     * plugin clean up to remove the menu.
     *
     * Generated from Godot docs: EditorPlugin.add_tool_submenu_item
     */
    fun addToolSubmenuItem(name: String, submenu: PopupMenu) {
        ObjectCalls.ptrcallWithStringAndObjectArg(Binds.addToolSubmenuItemBind, segment, name, submenu.segment)
    }

    /**
     * Removes a menu `name` from Project > Tools.
     *
     * Generated from Godot docs: EditorPlugin.remove_tool_menu_item
     */
    fun removeToolMenuItem(name: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.removeToolMenuItemBind, segment, name)
    }

    /**
     * Returns the `PopupMenu` under Scene > Export As....
     *
     * Generated from Godot docs: EditorPlugin.get_export_as_menu
     */
    fun getExportAsMenu(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getExportAsMenuBind, segment))
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
        ObjectCalls.ptrcallWithTwoStringTwoObjectArgs(Binds.addCustomTypeBind, segment, type, base, script?.requireOpenHandle() ?: NULL_SEGMENT, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Removes a custom type added by `add_custom_type`.
     *
     * Generated from Godot docs: EditorPlugin.remove_custom_type
     */
    fun removeCustomType(type: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.removeCustomTypeBind, segment, type)
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
    fun addControlToDock(slot: EditorPlugin.DockSlot, control: Control, shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithLongAndTwoObjectArgs(Binds.addControlToDockBind, segment, slot.value, control.segment, shortcut?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Removes the control from the dock. You have to manually `Node.queue_free` the control.
     *
     * Generated from Godot docs: EditorPlugin.remove_control_from_docks
     */
    fun removeControlFromDocks(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeControlFromDocksBind, segment, listOf(control.segment))
    }

    /**
     * Sets the tab icon for the given control in a dock slot. Setting to `null` removes the icon.
     *
     * Generated from Godot docs: EditorPlugin.set_dock_tab_icon
     */
    fun setDockTabIcon(control: Control, icon: Texture2D?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.setDockTabIconBind, segment, control.segment, icon?.requireOpenHandle() ?: NULL_SEGMENT)
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
        return Button.wrap(ObjectCalls.ptrcallWithObjectStringObjectArgsRetObject(Binds.addControlToBottomPanelBind, segment, control.segment, title, shortcut?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes the control from the bottom panel. You have to manually `Node.queue_free` the control.
     *
     * Generated from Godot docs: EditorPlugin.remove_control_from_bottom_panel
     */
    fun removeControlFromBottomPanel(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeControlFromBottomPanelBind, segment, listOf(control.segment))
    }

    /**
     * Adds a script at `path` to the Autoload list as `name`.
     *
     * Generated from Godot docs: EditorPlugin.add_autoload_singleton
     */
    fun addAutoloadSingleton(name: String, path: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.addAutoloadSingletonBind, segment, name, path)
    }

    /**
     * Removes an Autoload `name` from the list.
     *
     * Generated from Godot docs: EditorPlugin.remove_autoload_singleton
     */
    fun removeAutoloadSingleton(name: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.removeAutoloadSingletonBind, segment, name)
    }

    /**
     * Updates the overlays of the 2D and 3D editor viewport. Causes methods
     * `_forward_canvas_draw_over_viewport`, `_forward_canvas_force_draw_over_viewport`,
     * `_forward_3d_draw_over_viewport` and `_forward_3d_force_draw_over_viewport` to be called.
     *
     * Generated from Godot docs: EditorPlugin.update_overlays
     */
    fun updateOverlays(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.updateOverlaysBind, segment)
    }

    /**
     * Makes a specific item in the bottom panel visible.
     *
     * Generated from Godot docs: EditorPlugin.make_bottom_panel_item_visible
     */
    fun makeBottomPanelItemVisible(item: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.makeBottomPanelItemVisibleBind, segment, listOf(item.segment))
    }

    /**
     * Minimizes the bottom panel.
     *
     * Generated from Godot docs: EditorPlugin.hide_bottom_panel
     */
    fun hideBottomPanel() {
        ObjectCalls.ptrcallNoArgs(Binds.hideBottomPanelBind, segment)
    }

    /**
     * Gets the undo/redo object. Most actions in the editor can be undoable, so use this object to
     * make sure this happens when it's worth it.
     *
     * Generated from Godot docs: EditorPlugin.get_undo_redo
     */
    fun getUndoRedo(): EditorUndoRedoManager? {
        return EditorUndoRedoManager.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getUndoRedoBind, segment))
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
        ObjectCalls.ptrcallWithCallableArg(Binds.addUndoRedoInspectorHookCallbackBind, segment, callable.target.segment, callable.method)
    }

    /**
     * Removes a callback previously added by `add_undo_redo_inspector_hook_callback`.
     *
     * Generated from Godot docs: EditorPlugin.remove_undo_redo_inspector_hook_callback
     */
    fun removeUndoRedoInspectorHookCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(Binds.removeUndoRedoInspectorHookCallbackBind, segment, callable.target.segment, callable.method)
    }

    /**
     * Queue save the project's editor layout.
     *
     * Generated from Godot docs: EditorPlugin.queue_save_layout
     */
    fun queueSaveLayout() {
        ObjectCalls.ptrcallNoArgs(Binds.queueSaveLayoutBind, segment)
    }

    /**
     * Registers a custom translation parser plugin for extracting translatable strings from custom
     * files.
     *
     * Generated from Godot docs: EditorPlugin.add_translation_parser_plugin
     */
    fun addTranslationParserPlugin(parser: EditorTranslationParserPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addTranslationParserPluginBind, segment, listOf(parser?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes a custom translation parser plugin registered by `add_translation_parser_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_translation_parser_plugin
     */
    fun removeTranslationParserPlugin(parser: EditorTranslationParserPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeTranslationParserPluginBind, segment, listOf(parser?.requireOpenHandle() ?: NULL_SEGMENT))
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
        ObjectCalls.ptrcallWithObjectAndBoolArg(Binds.addImportPluginBind, segment, importer?.requireOpenHandle() ?: NULL_SEGMENT, firstPriority)
    }

    /**
     * Removes an import plugin registered by `add_import_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_import_plugin
     */
    fun removeImportPlugin(importer: EditorImportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeImportPluginBind, segment, listOf(importer?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Registers a new `EditorSceneFormatImporter`. Scene importers are used to import custom 3D asset
     * formats as scenes. If `first_priority` is `true`, the new import plugin is inserted first in the
     * list and takes precedence over pre-existing plugins.
     *
     * Generated from Godot docs: EditorPlugin.add_scene_format_importer_plugin
     */
    fun addSceneFormatImporterPlugin(sceneFormatImporter: EditorSceneFormatImporter?, firstPriority: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(Binds.addSceneFormatImporterPluginBind, segment, sceneFormatImporter?.requireOpenHandle() ?: NULL_SEGMENT, firstPriority)
    }

    /**
     * Removes a scene format importer registered by `add_scene_format_importer_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_scene_format_importer_plugin
     */
    fun removeSceneFormatImporterPlugin(sceneFormatImporter: EditorSceneFormatImporter?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeSceneFormatImporterPluginBind, segment, listOf(sceneFormatImporter?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Add an `EditorScenePostImportPlugin`. These plugins allow customizing the import process of 3D
     * assets by adding new options to the import dialogs. If `first_priority` is `true`, the new
     * import plugin is inserted first in the list and takes precedence over pre-existing plugins.
     *
     * Generated from Godot docs: EditorPlugin.add_scene_post_import_plugin
     */
    fun addScenePostImportPlugin(sceneImportPlugin: EditorScenePostImportPlugin?, firstPriority: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(Binds.addScenePostImportPluginBind, segment, sceneImportPlugin?.requireOpenHandle() ?: NULL_SEGMENT, firstPriority)
    }

    /**
     * Remove the `EditorScenePostImportPlugin`, added with `add_scene_post_import_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_scene_post_import_plugin
     */
    fun removeScenePostImportPlugin(sceneImportPlugin: EditorScenePostImportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeScenePostImportPluginBind, segment, listOf(sceneImportPlugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Registers a new `EditorExportPlugin`. Export plugins are used to perform tasks when the project
     * is being exported. See `add_inspector_plugin` for an example of how to register a plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_export_plugin
     */
    fun addExportPlugin(plugin: EditorExportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addExportPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes an export plugin registered by `add_export_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_export_plugin
     */
    fun removeExportPlugin(plugin: EditorExportPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeExportPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Registers a new `EditorExportPlatform`. Export platforms provides functionality of exporting to
     * the specific platform.
     *
     * Generated from Godot docs: EditorPlugin.add_export_platform
     */
    fun addExportPlatform(platform: EditorExportPlatform?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addExportPlatformBind, segment, listOf(platform?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes an export platform registered by `add_export_platform`.
     *
     * Generated from Godot docs: EditorPlugin.remove_export_platform
     */
    fun removeExportPlatform(platform: EditorExportPlatform?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeExportPlatformBind, segment, listOf(platform?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Registers a new `EditorNode3DGizmoPlugin`. Gizmo plugins are used to add custom gizmos to the 3D
     * preview viewport for a `Node3D`. See `add_inspector_plugin` for an example of how to register a
     * plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_node_3d_gizmo_plugin
     */
    fun addNode3dGizmoPlugin(plugin: EditorNode3DGizmoPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addNode3dGizmoPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes a gizmo plugin registered by `add_node_3d_gizmo_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_node_3d_gizmo_plugin
     */
    fun removeNode3dGizmoPlugin(plugin: EditorNode3DGizmoPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeNode3dGizmoPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.addInspectorPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes an inspector plugin registered by `add_inspector_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_inspector_plugin
     */
    fun removeInspectorPlugin(plugin: EditorInspectorPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeInspectorPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Registers a new `EditorResourceConversionPlugin`. Resource conversion plugins are used to add
     * custom resource converters to the editor inspector. See `EditorResourceConversionPlugin` for an
     * example of how to create a resource conversion plugin.
     *
     * Generated from Godot docs: EditorPlugin.add_resource_conversion_plugin
     */
    fun addResourceConversionPlugin(plugin: EditorResourceConversionPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addResourceConversionPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes a resource conversion plugin registered by `add_resource_conversion_plugin`.
     *
     * Generated from Godot docs: EditorPlugin.remove_resource_conversion_plugin
     */
    fun removeResourceConversionPlugin(plugin: EditorResourceConversionPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeResourceConversionPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Use this method if you always want to receive inputs from 3D view screen inside
     * `_forward_3d_gui_input`. It might be especially usable if your plugin will want to use raycast
     * in the scene.
     *
     * Generated from Godot docs: EditorPlugin.set_input_event_forwarding_always_enabled
     */
    fun setInputEventForwardingAlwaysEnabled() {
        ObjectCalls.ptrcallNoArgs(Binds.setInputEventForwardingAlwaysEnabledBind, segment)
    }

    /**
     * Enables calling of `_forward_canvas_force_draw_over_viewport` for the 2D editor and
     * `_forward_3d_force_draw_over_viewport` for the 3D editor when their viewports are updated. You
     * need to call this method only once and it will work permanently for this plugin.
     *
     * Generated from Godot docs: EditorPlugin.set_force_draw_over_forwarding_enabled
     */
    fun setForceDrawOverForwardingEnabled() {
        ObjectCalls.ptrcallNoArgs(Binds.setForceDrawOverForwardingEnabledBind, segment)
    }

    /**
     * Adds a plugin to the context menu. `slot` is the context menu where the plugin will be added.
     * Note: A plugin instance can belong only to a single context menu slot.
     *
     * Generated from Godot docs: EditorPlugin.add_context_menu_plugin
     */
    fun addContextMenuPlugin(slot: EditorContextMenuPlugin.ContextMenuSlot, plugin: EditorContextMenuPlugin?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(Binds.addContextMenuPluginBind, segment, slot.value, plugin?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Removes the specified context menu plugin.
     *
     * Generated from Godot docs: EditorPlugin.remove_context_menu_plugin
     */
    fun removeContextMenuPlugin(plugin: EditorContextMenuPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeContextMenuPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the `EditorInterface` singleton instance.
     *
     * Generated from Godot docs: EditorPlugin.get_editor_interface
     */
    fun getEditorInterface(): EditorInterface? {
        return EditorInterface.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEditorInterfaceBind, segment))
    }

    /**
     * Gets the Editor's dialog used for making scripts. Note: Users can configure it before use.
     * Warning: Removing and freeing this node will render a part of the editor useless and may cause a
     * crash.
     *
     * Generated from Godot docs: EditorPlugin.get_script_create_dialog
     */
    fun getScriptCreateDialog(): ScriptCreateDialog? {
        return ScriptCreateDialog.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getScriptCreateDialogBind, segment))
    }

    /**
     * Adds a `Script` as debugger plugin to the Debugger. The script must extend
     * `EditorDebuggerPlugin`.
     *
     * Generated from Godot docs: EditorPlugin.add_debugger_plugin
     */
    fun addDebuggerPlugin(script: EditorDebuggerPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addDebuggerPluginBind, segment, listOf(script?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes the debugger plugin with given script from the Debugger.
     *
     * Generated from Godot docs: EditorPlugin.remove_debugger_plugin
     */
    fun removeDebuggerPlugin(script: EditorDebuggerPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeDebuggerPluginBind, segment, listOf(script?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Provide the version of the plugin declared in the `plugin.cfg` config file.
     *
     * Generated from Godot docs: EditorPlugin.get_plugin_version
     */
    fun getPluginVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPluginVersionBind, segment)
    }

    /** Signal `scene_changed(scene_root: Node)`; see [TypedSignal]. */
    val sceneChanged: Signal1<Node?>
        @JvmName("sceneChangedTypedSignal")
        get() = Signal1(this, "scene_changed", SignalArgType.nullableObjectOf("Node") { Node(it) })

    /** Signal `scene_closed(filepath: String)`; see [TypedSignal]. */
    val sceneClosed: Signal1<String>
        @JvmName("sceneClosedTypedSignal")
        get() = Signal1(this, "scene_closed", SignalArgType.STRING)

    /** Signal `main_screen_changed(screen_name: String)`; see [TypedSignal]. */
    val mainScreenChanged: Signal1<String>
        @JvmName("mainScreenChangedTypedSignal")
        get() = Signal1(this, "main_screen_changed", SignalArgType.STRING)

    /** Signal `resource_saved(resource: Resource)`; see [TypedSignal]. */
    val resourceSaved: Signal1<Resource?>
        @JvmName("resourceSavedTypedSignal")
        get() = Signal1(this, "resource_saved", SignalArgType.nullableObjectOf("Resource") { Resource(it) })

    /** Signal `scene_saved(filepath: String)`; see [TypedSignal]. */
    val sceneSaved: Signal1<String>
        @JvmName("sceneSavedTypedSignal")
        get() = Signal1(this, "scene_saved", SignalArgType.STRING)

    /** Signal `project_settings_changed()`; see [TypedSignal]. */
    val projectSettingsChanged: Signal0
        @JvmName("projectSettingsChangedTypedSignal")
        get() = Signal0(this, "project_settings_changed")

    object Signals {
        const val sceneChanged: String = "scene_changed"
        const val sceneClosed: String = "scene_closed"
        const val mainScreenChanged: String = "main_screen_changed"
        const val resourceSaved: String = "resource_saved"
        const val sceneSaved: String = "scene_saved"
        const val projectSettingsChanged: String = "project_settings_changed"
    }

    /**
     * Godot's `EditorPlugin.CustomControlContainer` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`EditorPlugin.CustomControlContainer.<NAME>`).
     *
     * Generated from Godot docs: EditorPlugin.CustomControlContainer
     */
    @JvmInline
    value class CustomControlContainer(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Main editor toolbar, next to play buttons.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_TOOLBAR
             */
            val TOOLBAR: CustomControlContainer get() = CustomControlContainer(0L)
            /**
             * The toolbar that appears when 3D editor is active.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_SPATIAL_EDITOR_MENU
             */
            val SPATIAL_EDITOR_MENU: CustomControlContainer get() = CustomControlContainer(1L)
            /**
             * Left sidebar of the 3D editor.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_SPATIAL_EDITOR_SIDE_LEFT
             */
            val SPATIAL_EDITOR_SIDE_LEFT: CustomControlContainer get() = CustomControlContainer(2L)
            /**
             * Right sidebar of the 3D editor.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_SPATIAL_EDITOR_SIDE_RIGHT
             */
            val SPATIAL_EDITOR_SIDE_RIGHT: CustomControlContainer get() = CustomControlContainer(3L)
            /**
             * Bottom panel of the 3D editor.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_SPATIAL_EDITOR_BOTTOM
             */
            val SPATIAL_EDITOR_BOTTOM: CustomControlContainer get() = CustomControlContainer(4L)
            /**
             * The toolbar that appears when 2D editor is active.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_CANVAS_EDITOR_MENU
             */
            val CANVAS_EDITOR_MENU: CustomControlContainer get() = CustomControlContainer(5L)
            /**
             * Left sidebar of the 2D editor.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_CANVAS_EDITOR_SIDE_LEFT
             */
            val CANVAS_EDITOR_SIDE_LEFT: CustomControlContainer get() = CustomControlContainer(6L)
            /**
             * Right sidebar of the 2D editor.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_CANVAS_EDITOR_SIDE_RIGHT
             */
            val CANVAS_EDITOR_SIDE_RIGHT: CustomControlContainer get() = CustomControlContainer(7L)
            /**
             * Bottom panel of the 2D editor.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_CANVAS_EDITOR_BOTTOM
             */
            val CANVAS_EDITOR_BOTTOM: CustomControlContainer get() = CustomControlContainer(8L)
            /**
             * Bottom section of the inspector.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_INSPECTOR_BOTTOM
             */
            val INSPECTOR_BOTTOM: CustomControlContainer get() = CustomControlContainer(9L)
            /**
             * Tab of Project Settings dialog, to the left of other tabs.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_PROJECT_SETTING_TAB_LEFT
             */
            val PROJECT_SETTING_TAB_LEFT: CustomControlContainer get() = CustomControlContainer(10L)
            /**
             * Tab of Project Settings dialog, to the right of other tabs.
             *
             * Generated from Godot docs: EditorPlugin.CONTAINER_PROJECT_SETTING_TAB_RIGHT
             */
            val PROJECT_SETTING_TAB_RIGHT: CustomControlContainer get() = CustomControlContainer(11L)
        }
    }

    /**
     * Godot's `EditorPlugin.DockSlot` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`EditorPlugin.DockSlot.<NAME>`).
     *
     * Generated from Godot docs: EditorPlugin.DockSlot
     */
    @JvmInline
    value class DockSlot(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The dock is closed.
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_NONE
             */
            val NONE: DockSlot get() = DockSlot(-1L)
            /**
             * Dock slot, left side, upper-left (empty in default layout).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_LEFT_UL
             */
            val LEFT_UL: DockSlot get() = DockSlot(0L)
            /**
             * Dock slot, left side, bottom-left (empty in default layout).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_LEFT_BL
             */
            val LEFT_BL: DockSlot get() = DockSlot(1L)
            /**
             * Dock slot, left side, upper-right (in default layout includes Scene and Import docks).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_LEFT_UR
             */
            val LEFT_UR: DockSlot get() = DockSlot(2L)
            /**
             * Dock slot, left side, bottom-right (in default layout includes FileSystem dock).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_LEFT_BR
             */
            val LEFT_BR: DockSlot get() = DockSlot(3L)
            /**
             * Dock slot, right side, upper-left (in default layout includes Inspector, Node, and History
             * docks).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_RIGHT_UL
             */
            val RIGHT_UL: DockSlot get() = DockSlot(4L)
            /**
             * Dock slot, right side, bottom-left (empty in default layout).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_RIGHT_BL
             */
            val RIGHT_BL: DockSlot get() = DockSlot(5L)
            /**
             * Dock slot, right side, upper-right (empty in default layout).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_RIGHT_UR
             */
            val RIGHT_UR: DockSlot get() = DockSlot(6L)
            /**
             * Dock slot, right side, bottom-right (empty in default layout).
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_RIGHT_BR
             */
            val RIGHT_BR: DockSlot get() = DockSlot(7L)
            /**
             * Bottom panel.
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_BOTTOM
             */
            val BOTTOM: DockSlot get() = DockSlot(8L)
            /**
             * Represents the size of the `DockSlot` enum.
             *
             * Generated from Godot docs: EditorPlugin.DOCK_SLOT_MAX
             */
            val MAX: DockSlot get() = DockSlot(9L)
        }
    }

    /**
     * Godot's `EditorPlugin.AfterGUIInput` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`EditorPlugin.AfterGUIInput.<NAME>`).
     *
     * Generated from Godot docs: EditorPlugin.AfterGUIInput
     */
    @JvmInline
    value class AfterGUIInput(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Forwards the `InputEvent` to other EditorPlugins.
             *
             * Generated from Godot docs: EditorPlugin.AFTER_GUI_INPUT_PASS
             */
            val PASS: AfterGUIInput get() = AfterGUIInput(0L)
            /**
             * Prevents the `InputEvent` from reaching other Editor classes.
             *
             * Generated from Godot docs: EditorPlugin.AFTER_GUI_INPUT_STOP
             */
            val STOP: AfterGUIInput get() = AfterGUIInput(1L)
            /**
             * Pass the `InputEvent` to other editor plugins except the main `Node3D` one. This can be used to
             * prevent node selection changes and work with sub-gizmos instead.
             *
             * Generated from Godot docs: EditorPlugin.AFTER_GUI_INPUT_CUSTOM
             */
            val CUSTOM: AfterGUIInput get() = AfterGUIInput(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorPlugin? =
            if (handle.address() == 0L) null else EditorPlugin(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_DOCK_HASH = 158651717L
        @JvmField
        val addDockBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_dock", ADD_DOCK_HASH)

        private const val REMOVE_DOCK_HASH = 158651717L
        @JvmField
        val removeDockBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_dock", REMOVE_DOCK_HASH)

        private const val ADD_CONTROL_TO_CONTAINER_HASH = 3092750152L
        @JvmField
        val addControlToContainerBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_control_to_container", ADD_CONTROL_TO_CONTAINER_HASH)

        private const val REMOVE_CONTROL_FROM_CONTAINER_HASH = 3092750152L
        @JvmField
        val removeControlFromContainerBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_control_from_container", REMOVE_CONTROL_FROM_CONTAINER_HASH)

        private const val ADD_TOOL_MENU_ITEM_HASH = 2137474292L
        @JvmField
        val addToolMenuItemBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_tool_menu_item", ADD_TOOL_MENU_ITEM_HASH)

        private const val ADD_TOOL_SUBMENU_ITEM_HASH = 1019428915L
        @JvmField
        val addToolSubmenuItemBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_tool_submenu_item", ADD_TOOL_SUBMENU_ITEM_HASH)

        private const val REMOVE_TOOL_MENU_ITEM_HASH = 83702148L
        @JvmField
        val removeToolMenuItemBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_tool_menu_item", REMOVE_TOOL_MENU_ITEM_HASH)

        private const val GET_EXPORT_AS_MENU_HASH = 1775878644L
        @JvmField
        val getExportAsMenuBind =
            ObjectCalls.getMethodBind("EditorPlugin", "get_export_as_menu", GET_EXPORT_AS_MENU_HASH)

        private const val ADD_CUSTOM_TYPE_HASH = 1986814599L
        @JvmField
        val addCustomTypeBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_custom_type", ADD_CUSTOM_TYPE_HASH)

        private const val REMOVE_CUSTOM_TYPE_HASH = 83702148L
        @JvmField
        val removeCustomTypeBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_custom_type", REMOVE_CUSTOM_TYPE_HASH)

        private const val ADD_CONTROL_TO_DOCK_HASH = 2994930786L
        @JvmField
        val addControlToDockBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_control_to_dock", ADD_CONTROL_TO_DOCK_HASH)

        private const val REMOVE_CONTROL_FROM_DOCKS_HASH = 1496901182L
        @JvmField
        val removeControlFromDocksBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_control_from_docks", REMOVE_CONTROL_FROM_DOCKS_HASH)

        private const val SET_DOCK_TAB_ICON_HASH = 3450529724L
        @JvmField
        val setDockTabIconBind =
            ObjectCalls.getMethodBind("EditorPlugin", "set_dock_tab_icon", SET_DOCK_TAB_ICON_HASH)

        private const val ADD_CONTROL_TO_BOTTOM_PANEL_HASH = 111032269L
        @JvmField
        val addControlToBottomPanelBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_control_to_bottom_panel", ADD_CONTROL_TO_BOTTOM_PANEL_HASH)

        private const val REMOVE_CONTROL_FROM_BOTTOM_PANEL_HASH = 1496901182L
        @JvmField
        val removeControlFromBottomPanelBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_control_from_bottom_panel", REMOVE_CONTROL_FROM_BOTTOM_PANEL_HASH)

        private const val ADD_AUTOLOAD_SINGLETON_HASH = 3186203200L
        @JvmField
        val addAutoloadSingletonBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_autoload_singleton", ADD_AUTOLOAD_SINGLETON_HASH)

        private const val REMOVE_AUTOLOAD_SINGLETON_HASH = 83702148L
        @JvmField
        val removeAutoloadSingletonBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_autoload_singleton", REMOVE_AUTOLOAD_SINGLETON_HASH)

        private const val UPDATE_OVERLAYS_HASH = 3905245786L
        @JvmField
        val updateOverlaysBind =
            ObjectCalls.getMethodBind("EditorPlugin", "update_overlays", UPDATE_OVERLAYS_HASH)

        private const val MAKE_BOTTOM_PANEL_ITEM_VISIBLE_HASH = 1496901182L
        @JvmField
        val makeBottomPanelItemVisibleBind =
            ObjectCalls.getMethodBind("EditorPlugin", "make_bottom_panel_item_visible", MAKE_BOTTOM_PANEL_ITEM_VISIBLE_HASH)

        private const val HIDE_BOTTOM_PANEL_HASH = 3218959716L
        @JvmField
        val hideBottomPanelBind =
            ObjectCalls.getMethodBind("EditorPlugin", "hide_bottom_panel", HIDE_BOTTOM_PANEL_HASH)

        private const val GET_UNDO_REDO_HASH = 773492341L
        @JvmField
        val getUndoRedoBind =
            ObjectCalls.getMethodBind("EditorPlugin", "get_undo_redo", GET_UNDO_REDO_HASH)

        private const val ADD_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH = 1611583062L
        @JvmField
        val addUndoRedoInspectorHookCallbackBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_undo_redo_inspector_hook_callback", ADD_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH)

        private const val REMOVE_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH = 1611583062L
        @JvmField
        val removeUndoRedoInspectorHookCallbackBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_undo_redo_inspector_hook_callback", REMOVE_UNDO_REDO_INSPECTOR_HOOK_CALLBACK_HASH)

        private const val QUEUE_SAVE_LAYOUT_HASH = 3218959716L
        @JvmField
        val queueSaveLayoutBind =
            ObjectCalls.getMethodBind("EditorPlugin", "queue_save_layout", QUEUE_SAVE_LAYOUT_HASH)

        private const val ADD_TRANSLATION_PARSER_PLUGIN_HASH = 3116463128L
        @JvmField
        val addTranslationParserPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_translation_parser_plugin", ADD_TRANSLATION_PARSER_PLUGIN_HASH)

        private const val REMOVE_TRANSLATION_PARSER_PLUGIN_HASH = 3116463128L
        @JvmField
        val removeTranslationParserPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_translation_parser_plugin", REMOVE_TRANSLATION_PARSER_PLUGIN_HASH)

        private const val ADD_IMPORT_PLUGIN_HASH = 3113975762L
        @JvmField
        val addImportPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_import_plugin", ADD_IMPORT_PLUGIN_HASH)

        private const val REMOVE_IMPORT_PLUGIN_HASH = 2312482773L
        @JvmField
        val removeImportPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_import_plugin", REMOVE_IMPORT_PLUGIN_HASH)

        private const val ADD_SCENE_FORMAT_IMPORTER_PLUGIN_HASH = 2764104752L
        @JvmField
        val addSceneFormatImporterPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_scene_format_importer_plugin", ADD_SCENE_FORMAT_IMPORTER_PLUGIN_HASH)

        private const val REMOVE_SCENE_FORMAT_IMPORTER_PLUGIN_HASH = 2637776123L
        @JvmField
        val removeSceneFormatImporterPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_scene_format_importer_plugin", REMOVE_SCENE_FORMAT_IMPORTER_PLUGIN_HASH)

        private const val ADD_SCENE_POST_IMPORT_PLUGIN_HASH = 3492436322L
        @JvmField
        val addScenePostImportPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_scene_post_import_plugin", ADD_SCENE_POST_IMPORT_PLUGIN_HASH)

        private const val REMOVE_SCENE_POST_IMPORT_PLUGIN_HASH = 3045178206L
        @JvmField
        val removeScenePostImportPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_scene_post_import_plugin", REMOVE_SCENE_POST_IMPORT_PLUGIN_HASH)

        private const val ADD_EXPORT_PLUGIN_HASH = 4095952207L
        @JvmField
        val addExportPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_export_plugin", ADD_EXPORT_PLUGIN_HASH)

        private const val REMOVE_EXPORT_PLUGIN_HASH = 4095952207L
        @JvmField
        val removeExportPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_export_plugin", REMOVE_EXPORT_PLUGIN_HASH)

        private const val ADD_EXPORT_PLATFORM_HASH = 3431312373L
        @JvmField
        val addExportPlatformBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_export_platform", ADD_EXPORT_PLATFORM_HASH)

        private const val REMOVE_EXPORT_PLATFORM_HASH = 3431312373L
        @JvmField
        val removeExportPlatformBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_export_platform", REMOVE_EXPORT_PLATFORM_HASH)

        private const val ADD_NODE_3D_GIZMO_PLUGIN_HASH = 1541015022L
        @JvmField
        val addNode3dGizmoPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_node_3d_gizmo_plugin", ADD_NODE_3D_GIZMO_PLUGIN_HASH)

        private const val REMOVE_NODE_3D_GIZMO_PLUGIN_HASH = 1541015022L
        @JvmField
        val removeNode3dGizmoPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_node_3d_gizmo_plugin", REMOVE_NODE_3D_GIZMO_PLUGIN_HASH)

        private const val ADD_INSPECTOR_PLUGIN_HASH = 546395733L
        @JvmField
        val addInspectorPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_inspector_plugin", ADD_INSPECTOR_PLUGIN_HASH)

        private const val REMOVE_INSPECTOR_PLUGIN_HASH = 546395733L
        @JvmField
        val removeInspectorPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_inspector_plugin", REMOVE_INSPECTOR_PLUGIN_HASH)

        private const val ADD_RESOURCE_CONVERSION_PLUGIN_HASH = 2124849111L
        @JvmField
        val addResourceConversionPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_resource_conversion_plugin", ADD_RESOURCE_CONVERSION_PLUGIN_HASH)

        private const val REMOVE_RESOURCE_CONVERSION_PLUGIN_HASH = 2124849111L
        @JvmField
        val removeResourceConversionPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_resource_conversion_plugin", REMOVE_RESOURCE_CONVERSION_PLUGIN_HASH)

        private const val SET_INPUT_EVENT_FORWARDING_ALWAYS_ENABLED_HASH = 3218959716L
        @JvmField
        val setInputEventForwardingAlwaysEnabledBind =
            ObjectCalls.getMethodBind("EditorPlugin", "set_input_event_forwarding_always_enabled", SET_INPUT_EVENT_FORWARDING_ALWAYS_ENABLED_HASH)

        private const val SET_FORCE_DRAW_OVER_FORWARDING_ENABLED_HASH = 3218959716L
        @JvmField
        val setForceDrawOverForwardingEnabledBind =
            ObjectCalls.getMethodBind("EditorPlugin", "set_force_draw_over_forwarding_enabled", SET_FORCE_DRAW_OVER_FORWARDING_ENABLED_HASH)

        private const val ADD_CONTEXT_MENU_PLUGIN_HASH = 1904221872L
        @JvmField
        val addContextMenuPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_context_menu_plugin", ADD_CONTEXT_MENU_PLUGIN_HASH)

        private const val REMOVE_CONTEXT_MENU_PLUGIN_HASH = 2281511854L
        @JvmField
        val removeContextMenuPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_context_menu_plugin", REMOVE_CONTEXT_MENU_PLUGIN_HASH)

        private const val GET_EDITOR_INTERFACE_HASH = 4223731786L
        @JvmField
        val getEditorInterfaceBind =
            ObjectCalls.getMethodBind("EditorPlugin", "get_editor_interface", GET_EDITOR_INTERFACE_HASH)

        private const val GET_SCRIPT_CREATE_DIALOG_HASH = 3121871482L
        @JvmField
        val getScriptCreateDialogBind =
            ObjectCalls.getMethodBind("EditorPlugin", "get_script_create_dialog", GET_SCRIPT_CREATE_DIALOG_HASH)

        private const val ADD_DEBUGGER_PLUGIN_HASH = 3749880309L
        @JvmField
        val addDebuggerPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "add_debugger_plugin", ADD_DEBUGGER_PLUGIN_HASH)

        private const val REMOVE_DEBUGGER_PLUGIN_HASH = 3749880309L
        @JvmField
        val removeDebuggerPluginBind =
            ObjectCalls.getMethodBind("EditorPlugin", "remove_debugger_plugin", REMOVE_DEBUGGER_PLUGIN_HASH)

        private const val GET_PLUGIN_VERSION_HASH = 201670096L
        @JvmField
        val getPluginVersionBind =
            ObjectCalls.getMethodBind("EditorPlugin", "get_plugin_version", GET_PLUGIN_VERSION_HASH)
    }
}

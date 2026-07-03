package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2i

/**
 * Godot editor's interface.
 *
 * Generated from Godot docs: EditorInterface
 */
object EditorInterface {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("EditorInterface")
    }

    var distractionFreeMode: Boolean
        @JvmName("distractionFreeModeProperty")
        get() = isDistractionFreeModeEnabled()
        @JvmName("setDistractionFreeModeProperty")
        set(value) = setDistractionFreeMode(value)

    var movieMakerEnabled: Boolean
        @JvmName("movieMakerEnabledProperty")
        get() = isMovieMakerEnabled()
        @JvmName("setMovieMakerEnabledProperty")
        set(value) = setMovieMakerEnabled(value)

    /**
     * Restarts the editor. This closes the editor and then opens the same project. If `save` is
     * `true`, the project will be saved before restarting.
     *
     * Generated from Godot docs: EditorInterface.restart_editor
     */
    @JvmStatic
    fun restartEditor(save: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(restartEditorBind, singleton, save)
    }

    /**
     * Returns the editor's `EditorCommandPalette` instance. Warning: Removing and freeing this node
     * will render a part of the editor useless and may cause a crash.
     *
     * Generated from Godot docs: EditorInterface.get_command_palette
     */
    @JvmStatic
    fun getCommandPalette(): EditorCommandPalette? {
        return EditorCommandPalette.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCommandPaletteBind, singleton))
    }

    /**
     * Returns the editor's `EditorFileSystem` instance.
     *
     * Generated from Godot docs: EditorInterface.get_resource_filesystem
     */
    @JvmStatic
    fun getResourceFilesystem(): EditorFileSystem? {
        return EditorFileSystem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getResourceFilesystemBind, singleton))
    }

    /**
     * Returns the `EditorPaths` singleton.
     *
     * Generated from Godot docs: EditorInterface.get_editor_paths
     */
    @JvmStatic
    fun getEditorPaths(): EditorPaths? {
        return EditorPaths.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorPathsBind, singleton))
    }

    /**
     * Returns the editor's `EditorResourcePreview` instance.
     *
     * Generated from Godot docs: EditorInterface.get_resource_previewer
     */
    @JvmStatic
    fun getResourcePreviewer(): EditorResourcePreview? {
        return EditorResourcePreview.wrap(ObjectCalls.ptrcallNoArgsRetObject(getResourcePreviewerBind, singleton))
    }

    /**
     * Returns the editor's `EditorSelection` instance.
     *
     * Generated from Godot docs: EditorInterface.get_selection
     */
    @JvmStatic
    fun getSelection(): EditorSelection? {
        return EditorSelection.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSelectionBind, singleton))
    }

    /**
     * Returns the editor's `EditorSettings` instance.
     *
     * Generated from Godot docs: EditorInterface.get_editor_settings
     */
    @JvmStatic
    fun getEditorSettings(): EditorSettings? {
        return EditorSettings.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorSettingsBind, singleton))
    }

    /**
     * Returns the editor's `EditorToaster`.
     *
     * Generated from Godot docs: EditorInterface.get_editor_toaster
     */
    @JvmStatic
    fun getEditorToaster(): EditorToaster? {
        return EditorToaster.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorToasterBind, singleton))
    }

    /**
     * Returns the editor's `EditorUndoRedoManager`.
     *
     * Generated from Godot docs: EditorInterface.get_editor_undo_redo
     */
    @JvmStatic
    fun getEditorUndoRedo(): EditorUndoRedoManager? {
        return EditorUndoRedoManager.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorUndoRedoBind, singleton))
    }

    /**
     * Returns mesh previews rendered at the given size as an `Array` of `Texture2D`s.
     *
     * Generated from Godot docs: EditorInterface.make_mesh_previews
     */
    @JvmStatic
    fun makeMeshPreviews(meshes: List<Mesh>, previewSize: Int): List<Texture2D> {
        return ObjectCalls.ptrcallWithObjectListIntArgsRetTypedObjectList(makeMeshPreviewsBind, singleton, meshes, previewSize, Texture2D::fromHandle)
    }

    /**
     * Sets the enabled status of a plugin. The plugin name is the same as its directory name.
     *
     * Generated from Godot docs: EditorInterface.set_plugin_enabled
     */
    @JvmStatic
    fun setPluginEnabled(plugin: String, enabled: Boolean) {
        ObjectCalls.ptrcallWithStringAndBoolArg(setPluginEnabledBind, singleton, plugin, enabled)
    }

    /**
     * Returns `true` if the specified `plugin` is enabled. The plugin name is the same as its
     * directory name.
     *
     * Generated from Godot docs: EditorInterface.is_plugin_enabled
     */
    @JvmStatic
    fun isPluginEnabled(plugin: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(isPluginEnabledBind, singleton, plugin)
    }

    /**
     * Returns the editor's `Theme`. Note: When creating custom editor UI, prefer accessing theme items
     * directly from your GUI nodes using the `get_theme_*` methods.
     *
     * Generated from Godot docs: EditorInterface.get_editor_theme
     */
    @JvmStatic
    fun getEditorTheme(): Theme? {
        return Theme.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorThemeBind, singleton))
    }

    /**
     * Returns the main container of Godot editor's window. For example, you can use it to retrieve the
     * size of the container and place your controls accordingly. Warning: Removing and freeing this
     * node will render the editor useless and may cause a crash.
     *
     * Generated from Godot docs: EditorInterface.get_base_control
     */
    @JvmStatic
    fun getBaseControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBaseControlBind, singleton))
    }

    /**
     * Returns the editor control responsible for main screen plugins and tools. Use it with plugins
     * that implement `EditorPlugin._has_main_screen`. Note: This node is a `VBoxContainer`, which
     * means that if you add a `Control` child to it, you need to set the child's
     * `Control.size_flags_vertical` to `Control.SIZE_EXPAND_FILL` to make it use the full available
     * space. Warning: Removing and freeing this node will render a part of the editor useless and may
     * cause a crash.
     *
     * Generated from Godot docs: EditorInterface.get_editor_main_screen
     */
    @JvmStatic
    fun getEditorMainScreen(): VBoxContainer? {
        return VBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorMainScreenBind, singleton))
    }

    /**
     * Returns the editor's `ScriptEditor` instance. Warning: Removing and freeing this node will
     * render a part of the editor useless and may cause a crash.
     *
     * Generated from Godot docs: EditorInterface.get_script_editor
     */
    @JvmStatic
    fun getScriptEditor(): ScriptEditor? {
        return ScriptEditor.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScriptEditorBind, singleton))
    }

    /**
     * Returns the 2D editor `SubViewport`. It does not have a camera. Instead, the view transforms are
     * done directly and can be accessed with `Viewport.global_canvas_transform`.
     *
     * Generated from Godot docs: EditorInterface.get_editor_viewport_2d
     */
    @JvmStatic
    fun getEditorViewport2d(): SubViewport? {
        return SubViewport.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorViewport2dBind, singleton))
    }

    /**
     * Returns the specified 3D editor `SubViewport`, from `0` to `3`. The viewport can be used to
     * access the active editor cameras with `Viewport.get_camera_3d`.
     *
     * Generated from Godot docs: EditorInterface.get_editor_viewport_3d
     */
    @JvmStatic
    fun getEditorViewport3d(idx: Int = 0): SubViewport? {
        return SubViewport.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getEditorViewport3dBind, singleton, idx))
    }

    /**
     * Sets the editor's current main screen to the one specified in `name`. `name` must match the
     * title of the tab in question exactly (e.g. `2D`, `3D`, `Script`, `Game`, or `Asset Store` for
     * default tabs).
     *
     * Generated from Godot docs: EditorInterface.set_main_screen_editor
     */
    @JvmStatic
    fun setMainScreenEditor(name: String) {
        ObjectCalls.ptrcallWithStringArg(setMainScreenEditorBind, singleton, name)
    }

    /**
     * If `true`, enables distraction-free mode which hides side docks to increase the space available
     * for the main view.
     *
     * Generated from Godot docs: EditorInterface.set_distraction_free_mode
     */
    @JvmStatic
    fun setDistractionFreeMode(enter: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDistractionFreeModeBind, singleton, enter)
    }

    /**
     * If `true`, enables distraction-free mode which hides side docks to increase the space available
     * for the main view.
     *
     * Generated from Godot docs: EditorInterface.is_distraction_free_mode_enabled
     */
    @JvmStatic
    fun isDistractionFreeModeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDistractionFreeModeEnabledBind, singleton)
    }

    /**
     * Returns `true` if multiple window support is enabled in the editor. Multiple window support is
     * enabled if all of these statements are true: - `EditorSettings.interface/multi_window/enable` is
     * `true`. - `EditorSettings.interface/editor/display/single_window_mode` is `false`. -
     * `Viewport.gui_embed_subwindows` is `false`. This is forced to `true` on platforms that don't
     * support multiple windows such as Web, or when the `--single-window` command line argument
     * ($DOCS_URL/tutorials/editor/command_line_tutorial.html) is used.
     *
     * Generated from Godot docs: EditorInterface.is_multi_window_enabled
     */
    @JvmStatic
    fun isMultiWindowEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMultiWindowEnabledBind, singleton)
    }

    /**
     * Returns the actual scale of the editor UI (`1.0` being 100% scale). This can be used to adjust
     * position and dimensions of the UI added by plugins. Note: This value is set via the
     * `EditorSettings.interface/editor/appearance/display_scale` and
     * `EditorSettings.interface/editor/appearance/custom_display_scale` settings. The editor must be
     * restarted for changes to be properly applied.
     *
     * Generated from Godot docs: EditorInterface.get_editor_scale
     */
    @JvmStatic
    fun getEditorScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEditorScaleBind, singleton)
    }

    /**
     * Returns the language currently used for the editor interface.
     *
     * Generated from Godot docs: EditorInterface.get_editor_language
     */
    @JvmStatic
    fun getEditorLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEditorLanguageBind, singleton)
    }

    /**
     * Returns `true` if the 3D editor currently has snapping mode enabled, and `false` otherwise.
     *
     * Generated from Godot docs: EditorInterface.is_node_3d_snap_enabled
     */
    @JvmStatic
    fun isNode3dSnapEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNode3dSnapEnabledBind, singleton)
    }

    /**
     * Returns the amount of units the 3D editor's translation snapping is set to.
     *
     * Generated from Godot docs: EditorInterface.get_node_3d_translate_snap
     */
    @JvmStatic
    fun getNode3dTranslateSnap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNode3dTranslateSnapBind, singleton)
    }

    /**
     * Returns the amount of degrees the 3D editor's rotational snapping is set to.
     *
     * Generated from Godot docs: EditorInterface.get_node_3d_rotate_snap
     */
    @JvmStatic
    fun getNode3dRotateSnap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNode3dRotateSnapBind, singleton)
    }

    /**
     * Returns the amount of units the 3D editor's scale snapping is set to.
     *
     * Generated from Godot docs: EditorInterface.get_node_3d_scale_snap
     */
    @JvmStatic
    fun getNode3dScaleSnap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNode3dScaleSnapBind, singleton)
    }

    /**
     * Pops up the `dialog` in the editor UI with `Window.popup_exclusive`. The dialog must have no
     * current parent, otherwise the method fails. See also `Window.set_unparent_when_invisible`.
     *
     * Generated from Godot docs: EditorInterface.popup_dialog
     */
    @JvmStatic
    fun popupDialog(dialog: Window, rect: Rect2i) {
        ObjectCalls.ptrcallWithObjectAndRect2iArg(popupDialogBind, singleton, dialog.handle, rect)
    }

    /**
     * Pops up the `dialog` in the editor UI with `Window.popup_exclusive_centered`. The dialog must
     * have no current parent, otherwise the method fails. See also
     * `Window.set_unparent_when_invisible`.
     *
     * Generated from Godot docs: EditorInterface.popup_dialog_centered
     */
    @JvmStatic
    fun popupDialogCentered(dialog: Window, minsize: Vector2i) {
        ObjectCalls.ptrcallWithObjectAndVector2iArg(popupDialogCenteredBind, singleton, dialog.handle, minsize)
    }

    /**
     * Pops up the `dialog` in the editor UI with `Window.popup_exclusive_centered_ratio`. The dialog
     * must have no current parent, otherwise the method fails. See also
     * `Window.set_unparent_when_invisible`.
     *
     * Generated from Godot docs: EditorInterface.popup_dialog_centered_ratio
     */
    @JvmStatic
    fun popupDialogCenteredRatio(dialog: Window, ratio: Double = 0.8) {
        ObjectCalls.ptrcallWithObjectAndDoubleArg(popupDialogCenteredRatioBind, singleton, dialog.handle, ratio)
    }

    /**
     * Pops up the `dialog` in the editor UI with `Window.popup_exclusive_centered_clamped`. The dialog
     * must have no current parent, otherwise the method fails. See also
     * `Window.set_unparent_when_invisible`.
     *
     * Generated from Godot docs: EditorInterface.popup_dialog_centered_clamped
     */
    @JvmStatic
    fun popupDialogCenteredClamped(dialog: Window, minsize: Vector2i, fallbackRatio: Double = 0.75) {
        ObjectCalls.ptrcallWithObjectVector2iAndDoubleArg(popupDialogCenteredClampedBind, singleton, dialog.handle, minsize, fallbackRatio)
    }

    /**
     * Returns the name of the currently activated feature profile. If the default profile is currently
     * active, an empty string is returned instead. In order to get a reference to the
     * `EditorFeatureProfile`, you must load the feature profile using
     * `EditorFeatureProfile.load_from_file`. Note: Feature profiles created via the user interface are
     * loaded from the `feature_profiles` directory, as a file with the `.profile` extension. The
     * editor configuration folder can be found by using `EditorPaths.get_config_dir`.
     *
     * Generated from Godot docs: EditorInterface.get_current_feature_profile
     */
    @JvmStatic
    fun getCurrentFeatureProfile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentFeatureProfileBind, singleton)
    }

    /**
     * Selects and activates the specified feature profile with the given `profile_name`. Set
     * `profile_name` to an empty string to reset to the default feature profile. A feature profile can
     * be created programmatically using the `EditorFeatureProfile` class. Note: The feature profile
     * that gets activated must be located in the `feature_profiles` directory, as a file with the
     * `.profile` extension. If a profile could not be found, an error occurs. The editor configuration
     * folder can be found by using `EditorPaths.get_config_dir`.
     *
     * Generated from Godot docs: EditorInterface.set_current_feature_profile
     */
    @JvmStatic
    fun setCurrentFeatureProfile(profileName: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentFeatureProfileBind, singleton, profileName)
    }

    /**
     * Pops up an editor dialog for selecting a `Node` from the edited scene. The `callback` must take
     * a single argument of type `NodePath`. It is called on the selected `NodePath` or the empty path
     * `^""` if the dialog is canceled. If `valid_types` is provided, the dialog will only show Nodes
     * that match one of the listed Node types. If `current_value` is provided, the Node will be
     * automatically selected in the tree, if it exists.
     *
     * Generated from Godot docs: EditorInterface.popup_node_selector
     */
    @JvmStatic
    fun popupNodeSelector(callback: GodotCallable, validTypes: List<String>, currentValue: Node) {
        ObjectCalls.ptrcallWithCallableStringNameListObjectArgs(popupNodeSelectorBind, singleton, callback.target.handle, callback.method, validTypes, currentValue.handle)
    }

    /**
     * Pops up an editor dialog for selecting properties from `object`. The `callback` must take a
     * single argument of type `NodePath`. It is called on the selected property path (see
     * `NodePath.get_as_property_path`) or the empty path `^""` if the dialog is canceled. If
     * `type_filter` is provided, the dialog will only show properties that match one of the listed
     * `Variant.Type` values. If `current_value` is provided, the property will be selected
     * automatically in the property list, if it exists.
     *
     * Generated from Godot docs: EditorInterface.popup_property_selector
     */
    @JvmStatic
    fun popupPropertySelector(objectValue: GodotObject, callback: GodotCallable, typeFilter: List<Int>, currentValue: String = "") {
        ObjectCalls.ptrcallWithObjectCallablePackedInt32ListStringArgs(popupPropertySelectorBind, singleton, objectValue.handle, callback.target.handle, callback.method, typeFilter, currentValue)
    }

    /**
     * Pops up an editor dialog for selecting a method from `object`. The `callback` must take a single
     * argument of type `String` which will contain the name of the selected method or be empty if the
     * dialog is canceled. If `current_value` is provided, the method will be selected automatically in
     * the method list, if it exists.
     *
     * Generated from Godot docs: EditorInterface.popup_method_selector
     */
    @JvmStatic
    fun popupMethodSelector(objectValue: GodotObject, callback: GodotCallable, currentValue: String = "") {
        ObjectCalls.ptrcallWithObjectCallableStringArgs(popupMethodSelectorBind, singleton, objectValue.handle, callback.target.handle, callback.method, currentValue)
    }

    /**
     * Pops up an editor dialog for quick selecting a resource file. The `callback` must take a single
     * argument of type `String` which will contain the path of the selected resource or be empty if
     * the dialog is canceled. If `base_types` is provided, the dialog will only show resources that
     * match these types. Only types deriving from `Resource` are supported.
     *
     * Generated from Godot docs: EditorInterface.popup_quick_open
     */
    @JvmStatic
    fun popupQuickOpen(callback: GodotCallable, baseTypes: List<String>) {
        ObjectCalls.ptrcallWithCallableStringNameListArgs(popupQuickOpenBind, singleton, callback.target.handle, callback.method, baseTypes)
    }

    /**
     * Pops up an editor dialog for creating an object. The `callback` must take a single argument of
     * type `String`, which will contain the type name of the selected object (or the script path of
     * the type, if the type is created from a script), or be an empty string if no item is selected.
     * The `base_type` specifies the base type of objects to display. For example, if you set this to
     * "Resource", all types derived from `Resource` will display in the create dialog. The
     * `current_type` will be passed in the search box of the create dialog, and the specified type can
     * be immediately selected when the dialog pops up. If the `current_type` is not derived from
     * `base_type`, there will be no result of the type in the dialog. The `dialog_title` allows you to
     * define a custom title for the dialog. This is useful if you want to accurately hint the usage of
     * the dialog. If the `dialog_title` is an empty string, the dialog will use "Create New 'Base
     * Type'" as the default title. The `type_blocklist` contains a list of type names, and the types
     * in the blocklist will be hidden from the create dialog. Note: Trying to list the base type in
     * the `type_blocklist` will hide all types derived from the base type from the create dialog.
     *
     * Generated from Godot docs: EditorInterface.popup_create_dialog
     */
    @JvmStatic
    fun popupCreateDialog(callback: GodotCallable, baseType: String = "", currentType: String = "", dialogTitle: String = "", typeBlocklist: List<String>) {
        ObjectCalls.ptrcallWithCallableStringNameTwoStringStringNameListArgs(popupCreateDialogBind, singleton, callback.target.handle, callback.method, baseType, currentType, dialogTitle, typeBlocklist)
    }

    /**
     * Returns the editor's `FileSystemDock` instance. Warning: Removing and freeing this node will
     * render a part of the editor useless and may cause a crash.
     *
     * Generated from Godot docs: EditorInterface.get_file_system_dock
     */
    @JvmStatic
    fun getFileSystemDock(): FileSystemDock? {
        return FileSystemDock.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFileSystemDockBind, singleton))
    }

    /**
     * Selects the file, with the path provided by `file`, in the FileSystem dock.
     *
     * Generated from Godot docs: EditorInterface.select_file
     */
    @JvmStatic
    fun selectFile(file: String) {
        ObjectCalls.ptrcallWithStringArg(selectFileBind, singleton, file)
    }

    /**
     * Returns an array containing the paths of the currently selected files (and directories) in the
     * `FileSystemDock`.
     *
     * Generated from Godot docs: EditorInterface.get_selected_paths
     */
    @JvmStatic
    fun getSelectedPaths(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSelectedPathsBind, singleton)
    }

    /**
     * Returns the current path being viewed in the `FileSystemDock`.
     *
     * Generated from Godot docs: EditorInterface.get_current_path
     */
    @JvmStatic
    fun getCurrentPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentPathBind, singleton)
    }

    /**
     * Returns the current directory being viewed in the `FileSystemDock`. If a file is selected, its
     * base directory will be returned using `String.get_base_dir` instead.
     *
     * Generated from Godot docs: EditorInterface.get_current_directory
     */
    @JvmStatic
    fun getCurrentDirectory(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentDirectoryBind, singleton)
    }

    /**
     * Returns the editor's `EditorInspector` instance. Warning: Removing and freeing this node will
     * render a part of the editor useless and may cause a crash.
     *
     * Generated from Godot docs: EditorInterface.get_inspector
     */
    @JvmStatic
    fun getInspector(): EditorInspector? {
        return EditorInspector.wrap(ObjectCalls.ptrcallNoArgsRetObject(getInspectorBind, singleton))
    }

    /**
     * Shows the given property on the given `object` in the editor's Inspector dock. If
     * `inspector_only` is `true`, plugins will not attempt to edit `object`.
     *
     * Generated from Godot docs: EditorInterface.inspect_object
     */
    @JvmStatic
    fun inspectObject(objectValue: GodotObject, forProperty: String = "", inspectorOnly: Boolean = false) {
        ObjectCalls.ptrcallWithObjectStringBoolArgs(inspectObjectBind, singleton, objectValue.handle, forProperty, inspectorOnly)
    }

    /**
     * Edits the given `Resource`. If the resource is a `Script` you can also edit it with
     * `edit_script` to specify the line and column position.
     *
     * Generated from Godot docs: EditorInterface.edit_resource
     */
    @JvmStatic
    fun editResource(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(editResourceBind, singleton, listOf(resource?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Edits the given `Node`. The node will be also selected if it's inside the scene tree.
     *
     * Generated from Godot docs: EditorInterface.edit_node
     */
    @JvmStatic
    fun editNode(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(editNodeBind, singleton, listOf(node.handle))
    }

    /**
     * Edits the given `Script`. The line and column on which to open the script can also be specified.
     * The script will be open with the user-configured editor for the script's language which may be
     * an external editor.
     *
     * Generated from Godot docs: EditorInterface.edit_script
     */
    @JvmStatic
    fun editScript(script: Script?, line: Int = -1, column: Int = 0, grabFocus: Boolean = true) {
        ObjectCalls.ptrcallWithObjectTwoIntBoolArgs(editScriptBind, singleton, script?.requireOpenHandle() ?: MemorySegment.NULL, line, column, grabFocus)
    }

    /**
     * Opens the scene at the given path. If `set_inherited` is `true`, creates a new inherited scene.
     *
     * Generated from Godot docs: EditorInterface.open_scene_from_path
     */
    @JvmStatic
    fun openSceneFromPath(sceneFilepath: String, setInherited: Boolean = false) {
        ObjectCalls.ptrcallWithStringAndBoolArg(openSceneFromPathBind, singleton, sceneFilepath, setInherited)
    }

    /**
     * Reloads the scene at the given path. Fails if the scene is not open.
     *
     * Generated from Godot docs: EditorInterface.reload_scene_from_path
     */
    @JvmStatic
    fun reloadSceneFromPath(sceneFilepath: String) {
        ObjectCalls.ptrcallWithStringArg(reloadSceneFromPathBind, singleton, sceneFilepath)
    }

    /**
     * If `edited` is `true`, the object is marked as edited. Note: This is primarily used by the
     * editor for `Resource` based objects to track their modified state. For example, any changes to
     * an open scene, a resource in the inspector, or an edited script will cause this method to be
     * called with `true`. Saving the scene, script, or resource resets the edited state by calling
     * this method with `false`. Note: Each call to this method increments the object's edited version.
     * This is used to track changes in the editor and to trigger when thumbnails should be regenerated
     * for resources.
     *
     * Generated from Godot docs: EditorInterface.set_object_edited
     */
    @JvmStatic
    fun setObjectEdited(objectValue: GodotObject, edited: Boolean) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(setObjectEditedBind, singleton, objectValue.handle, edited)
    }

    /**
     * Returns `true` if the object has been marked as edited through `set_object_edited`.
     *
     * Generated from Godot docs: EditorInterface.is_object_edited
     */
    @JvmStatic
    fun isObjectEdited(objectValue: GodotObject): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isObjectEditedBind, singleton, objectValue.handle)
    }

    /**
     * Returns an array with the file paths of the currently opened scenes.
     *
     * Generated from Godot docs: EditorInterface.get_open_scenes
     */
    @JvmStatic
    fun getOpenScenes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getOpenScenesBind, singleton)
    }

    /**
     * Returns an array of file paths of currently unsaved scenes.
     *
     * Generated from Godot docs: EditorInterface.get_unsaved_scenes
     */
    @JvmStatic
    fun getUnsavedScenes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getUnsavedScenesBind, singleton)
    }

    /**
     * Returns an array with references to the root nodes of the currently opened scenes.
     *
     * Generated from Godot docs: EditorInterface.get_open_scene_roots
     */
    @JvmStatic
    fun getOpenSceneRoots(): List<Node> {
        return ObjectCalls.ptrcallNoArgsRetTypedNodeList(getOpenSceneRootsBind, singleton)
    }

    /**
     * Returns the edited (current) scene's root `Node`.
     *
     * Generated from Godot docs: EditorInterface.get_edited_scene_root
     */
    @JvmStatic
    fun getEditedSceneRoot(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedSceneRootBind, singleton))
    }

    /**
     * Makes `node` root of the currently opened scene. Only works if the scene is empty. If the `node`
     * is a scene instance, an inheriting scene will be created.
     *
     * Generated from Godot docs: EditorInterface.add_root_node
     */
    @JvmStatic
    fun addRootNode(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addRootNodeBind, singleton, listOf(node.handle))
    }

    /**
     * Saves the currently active scene. Returns either `OK` or `ERR_CANT_CREATE`.
     *
     * Generated from Godot docs: EditorInterface.save_scene
     */
    @JvmStatic
    fun saveScene(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(saveSceneBind, singleton)
    }

    /**
     * Saves the currently active scene as a file at `path`.
     *
     * Generated from Godot docs: EditorInterface.save_scene_as
     */
    @JvmStatic
    fun saveSceneAs(path: String, withPreview: Boolean = true) {
        ObjectCalls.ptrcallWithStringAndBoolArg(saveSceneAsBind, singleton, path, withPreview)
    }

    /**
     * Saves all opened scenes in the editor.
     *
     * Generated from Godot docs: EditorInterface.save_all_scenes
     */
    @JvmStatic
    fun saveAllScenes() {
        ObjectCalls.ptrcallNoArgs(saveAllScenesBind, singleton)
    }

    /**
     * Closes the currently active scene, discarding any pending changes in the process. Returns `OK`
     * on success or `ERR_DOES_NOT_EXIST` if there is no scene to close.
     *
     * Generated from Godot docs: EditorInterface.close_scene
     */
    @JvmStatic
    fun closeScene(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(closeSceneBind, singleton)
    }

    /**
     * Marks the current scene tab as unsaved.
     *
     * Generated from Godot docs: EditorInterface.mark_scene_as_unsaved
     */
    @JvmStatic
    fun markSceneAsUnsaved() {
        ObjectCalls.ptrcallNoArgs(markSceneAsUnsavedBind, singleton)
    }

    /**
     * Plays the main scene.
     *
     * Generated from Godot docs: EditorInterface.play_main_scene
     */
    @JvmStatic
    fun playMainScene() {
        ObjectCalls.ptrcallNoArgs(playMainSceneBind, singleton)
    }

    /**
     * Plays the currently active scene.
     *
     * Generated from Godot docs: EditorInterface.play_current_scene
     */
    @JvmStatic
    fun playCurrentScene() {
        ObjectCalls.ptrcallNoArgs(playCurrentSceneBind, singleton)
    }

    /**
     * Plays the scene specified by its filepath.
     *
     * Generated from Godot docs: EditorInterface.play_custom_scene
     */
    @JvmStatic
    fun playCustomScene(sceneFilepath: String) {
        ObjectCalls.ptrcallWithStringArg(playCustomSceneBind, singleton, sceneFilepath)
    }

    /**
     * Stops the scene that is currently playing.
     *
     * Generated from Godot docs: EditorInterface.stop_playing_scene
     */
    @JvmStatic
    fun stopPlayingScene() {
        ObjectCalls.ptrcallNoArgs(stopPlayingSceneBind, singleton)
    }

    /**
     * Returns `true` if a scene is currently being played, `false` otherwise. Paused scenes are
     * considered as being played.
     *
     * Generated from Godot docs: EditorInterface.is_playing_scene
     */
    @JvmStatic
    fun isPlayingScene(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingSceneBind, singleton)
    }

    /**
     * Returns the name of the scene that is being played. If no scene is currently being played,
     * returns an empty string.
     *
     * Generated from Godot docs: EditorInterface.get_playing_scene
     */
    @JvmStatic
    fun getPlayingScene(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPlayingSceneBind, singleton)
    }

    /**
     * If `true`, the Movie Maker mode is enabled in the editor. See `MovieWriter` for more
     * information.
     *
     * Generated from Godot docs: EditorInterface.set_movie_maker_enabled
     */
    @JvmStatic
    fun setMovieMakerEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMovieMakerEnabledBind, singleton, enabled)
    }

    /**
     * If `true`, the Movie Maker mode is enabled in the editor. See `MovieWriter` for more
     * information.
     *
     * Generated from Godot docs: EditorInterface.is_movie_maker_enabled
     */
    @JvmStatic
    fun isMovieMakerEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMovieMakerEnabledBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): EditorInterface? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): EditorInterface? =
        if (handle.address() == 0L) null else this

    private const val RESTART_EDITOR_HASH = 3216645846L
    private val restartEditorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "restart_editor", RESTART_EDITOR_HASH)
    }

    private const val GET_COMMAND_PALETTE_HASH = 2471163807L
    private val getCommandPaletteBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_command_palette", GET_COMMAND_PALETTE_HASH)
    }

    private const val GET_RESOURCE_FILESYSTEM_HASH = 780151678L
    private val getResourceFilesystemBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_resource_filesystem", GET_RESOURCE_FILESYSTEM_HASH)
    }

    private const val GET_EDITOR_PATHS_HASH = 1595760068L
    private val getEditorPathsBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_paths", GET_EDITOR_PATHS_HASH)
    }

    private const val GET_RESOURCE_PREVIEWER_HASH = 943486957L
    private val getResourcePreviewerBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_resource_previewer", GET_RESOURCE_PREVIEWER_HASH)
    }

    private const val GET_SELECTION_HASH = 2690272531L
    private val getSelectionBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_selection", GET_SELECTION_HASH)
    }

    private const val GET_EDITOR_SETTINGS_HASH = 4086932459L
    private val getEditorSettingsBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_settings", GET_EDITOR_SETTINGS_HASH)
    }

    private const val GET_EDITOR_TOASTER_HASH = 3612675797L
    private val getEditorToasterBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_toaster", GET_EDITOR_TOASTER_HASH)
    }

    private const val GET_EDITOR_UNDO_REDO_HASH = 3819628421L
    private val getEditorUndoRedoBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_undo_redo", GET_EDITOR_UNDO_REDO_HASH)
    }

    private const val MAKE_MESH_PREVIEWS_HASH = 878078554L
    private val makeMeshPreviewsBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "make_mesh_previews", MAKE_MESH_PREVIEWS_HASH)
    }

    private const val SET_PLUGIN_ENABLED_HASH = 2678287736L
    private val setPluginEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "set_plugin_enabled", SET_PLUGIN_ENABLED_HASH)
    }

    private const val IS_PLUGIN_ENABLED_HASH = 3927539163L
    private val isPluginEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_plugin_enabled", IS_PLUGIN_ENABLED_HASH)
    }

    private const val GET_EDITOR_THEME_HASH = 3846893731L
    private val getEditorThemeBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_theme", GET_EDITOR_THEME_HASH)
    }

    private const val GET_BASE_CONTROL_HASH = 2783021301L
    private val getBaseControlBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_base_control", GET_BASE_CONTROL_HASH)
    }

    private const val GET_EDITOR_MAIN_SCREEN_HASH = 1706218421L
    private val getEditorMainScreenBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_main_screen", GET_EDITOR_MAIN_SCREEN_HASH)
    }

    private const val GET_SCRIPT_EDITOR_HASH = 90868003L
    private val getScriptEditorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_script_editor", GET_SCRIPT_EDITOR_HASH)
    }

    private const val GET_EDITOR_VIEWPORT_2D_HASH = 3750751911L
    private val getEditorViewport2dBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_viewport_2d", GET_EDITOR_VIEWPORT_2D_HASH)
    }

    private const val GET_EDITOR_VIEWPORT_3D_HASH = 1970834490L
    private val getEditorViewport3dBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_viewport_3d", GET_EDITOR_VIEWPORT_3D_HASH)
    }

    private const val SET_MAIN_SCREEN_EDITOR_HASH = 83702148L
    private val setMainScreenEditorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "set_main_screen_editor", SET_MAIN_SCREEN_EDITOR_HASH)
    }

    private const val SET_DISTRACTION_FREE_MODE_HASH = 2586408642L
    private val setDistractionFreeModeBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "set_distraction_free_mode", SET_DISTRACTION_FREE_MODE_HASH)
    }

    private const val IS_DISTRACTION_FREE_MODE_ENABLED_HASH = 36873697L
    private val isDistractionFreeModeEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_distraction_free_mode_enabled", IS_DISTRACTION_FREE_MODE_ENABLED_HASH)
    }

    private const val IS_MULTI_WINDOW_ENABLED_HASH = 36873697L
    private val isMultiWindowEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_multi_window_enabled", IS_MULTI_WINDOW_ENABLED_HASH)
    }

    private const val GET_EDITOR_SCALE_HASH = 1740695150L
    private val getEditorScaleBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_scale", GET_EDITOR_SCALE_HASH)
    }

    private const val GET_EDITOR_LANGUAGE_HASH = 201670096L
    private val getEditorLanguageBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_editor_language", GET_EDITOR_LANGUAGE_HASH)
    }

    private const val IS_NODE_3D_SNAP_ENABLED_HASH = 36873697L
    private val isNode3dSnapEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_node_3d_snap_enabled", IS_NODE_3D_SNAP_ENABLED_HASH)
    }

    private const val GET_NODE_3D_TRANSLATE_SNAP_HASH = 1740695150L
    private val getNode3dTranslateSnapBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_node_3d_translate_snap", GET_NODE_3D_TRANSLATE_SNAP_HASH)
    }

    private const val GET_NODE_3D_ROTATE_SNAP_HASH = 1740695150L
    private val getNode3dRotateSnapBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_node_3d_rotate_snap", GET_NODE_3D_ROTATE_SNAP_HASH)
    }

    private const val GET_NODE_3D_SCALE_SNAP_HASH = 1740695150L
    private val getNode3dScaleSnapBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_node_3d_scale_snap", GET_NODE_3D_SCALE_SNAP_HASH)
    }

    private const val POPUP_DIALOG_HASH = 2015770942L
    private val popupDialogBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_dialog", POPUP_DIALOG_HASH)
    }

    private const val POPUP_DIALOG_CENTERED_HASH = 346557367L
    private val popupDialogCenteredBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_dialog_centered", POPUP_DIALOG_CENTERED_HASH)
    }

    private const val POPUP_DIALOG_CENTERED_RATIO_HASH = 2093669136L
    private val popupDialogCenteredRatioBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_dialog_centered_ratio", POPUP_DIALOG_CENTERED_RATIO_HASH)
    }

    private const val POPUP_DIALOG_CENTERED_CLAMPED_HASH = 3763385571L
    private val popupDialogCenteredClampedBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_dialog_centered_clamped", POPUP_DIALOG_CENTERED_CLAMPED_HASH)
    }

    private const val GET_CURRENT_FEATURE_PROFILE_HASH = 201670096L
    private val getCurrentFeatureProfileBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_current_feature_profile", GET_CURRENT_FEATURE_PROFILE_HASH)
    }

    private const val SET_CURRENT_FEATURE_PROFILE_HASH = 83702148L
    private val setCurrentFeatureProfileBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "set_current_feature_profile", SET_CURRENT_FEATURE_PROFILE_HASH)
    }

    private const val POPUP_NODE_SELECTOR_HASH = 2444591477L
    private val popupNodeSelectorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_node_selector", POPUP_NODE_SELECTOR_HASH)
    }

    private const val POPUP_PROPERTY_SELECTOR_HASH = 2955609011L
    private val popupPropertySelectorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_property_selector", POPUP_PROPERTY_SELECTOR_HASH)
    }

    private const val POPUP_METHOD_SELECTOR_HASH = 3585505226L
    private val popupMethodSelectorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_method_selector", POPUP_METHOD_SELECTOR_HASH)
    }

    private const val POPUP_QUICK_OPEN_HASH = 2271411043L
    private val popupQuickOpenBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_quick_open", POPUP_QUICK_OPEN_HASH)
    }

    private const val POPUP_CREATE_DIALOG_HASH = 495277124L
    private val popupCreateDialogBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "popup_create_dialog", POPUP_CREATE_DIALOG_HASH)
    }

    private const val GET_FILE_SYSTEM_DOCK_HASH = 3751012327L
    private val getFileSystemDockBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_file_system_dock", GET_FILE_SYSTEM_DOCK_HASH)
    }

    private const val SELECT_FILE_HASH = 83702148L
    private val selectFileBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "select_file", SELECT_FILE_HASH)
    }

    private const val GET_SELECTED_PATHS_HASH = 1139954409L
    private val getSelectedPathsBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_selected_paths", GET_SELECTED_PATHS_HASH)
    }

    private const val GET_CURRENT_PATH_HASH = 201670096L
    private val getCurrentPathBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_current_path", GET_CURRENT_PATH_HASH)
    }

    private const val GET_CURRENT_DIRECTORY_HASH = 201670096L
    private val getCurrentDirectoryBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_current_directory", GET_CURRENT_DIRECTORY_HASH)
    }

    private const val GET_INSPECTOR_HASH = 3517113938L
    private val getInspectorBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_inspector", GET_INSPECTOR_HASH)
    }

    private const val INSPECT_OBJECT_HASH = 127962172L
    private val inspectObjectBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "inspect_object", INSPECT_OBJECT_HASH)
    }

    private const val EDIT_RESOURCE_HASH = 968641751L
    private val editResourceBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "edit_resource", EDIT_RESOURCE_HASH)
    }

    private const val EDIT_NODE_HASH = 1078189570L
    private val editNodeBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "edit_node", EDIT_NODE_HASH)
    }

    private const val EDIT_SCRIPT_HASH = 219829402L
    private val editScriptBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "edit_script", EDIT_SCRIPT_HASH)
    }

    private const val OPEN_SCENE_FROM_PATH_HASH = 1168363258L
    private val openSceneFromPathBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "open_scene_from_path", OPEN_SCENE_FROM_PATH_HASH)
    }

    private const val RELOAD_SCENE_FROM_PATH_HASH = 83702148L
    private val reloadSceneFromPathBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "reload_scene_from_path", RELOAD_SCENE_FROM_PATH_HASH)
    }

    private const val SET_OBJECT_EDITED_HASH = 1462101905L
    private val setObjectEditedBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "set_object_edited", SET_OBJECT_EDITED_HASH)
    }

    private const val IS_OBJECT_EDITED_HASH = 397768994L
    private val isObjectEditedBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_object_edited", IS_OBJECT_EDITED_HASH)
    }

    private const val GET_OPEN_SCENES_HASH = 1139954409L
    private val getOpenScenesBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_open_scenes", GET_OPEN_SCENES_HASH)
    }

    private const val GET_UNSAVED_SCENES_HASH = 1139954409L
    private val getUnsavedScenesBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_unsaved_scenes", GET_UNSAVED_SCENES_HASH)
    }

    private const val GET_OPEN_SCENE_ROOTS_HASH = 3995934104L
    private val getOpenSceneRootsBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_open_scene_roots", GET_OPEN_SCENE_ROOTS_HASH)
    }

    private const val GET_EDITED_SCENE_ROOT_HASH = 3160264692L
    private val getEditedSceneRootBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_edited_scene_root", GET_EDITED_SCENE_ROOT_HASH)
    }

    private const val ADD_ROOT_NODE_HASH = 1078189570L
    private val addRootNodeBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "add_root_node", ADD_ROOT_NODE_HASH)
    }

    private const val SAVE_SCENE_HASH = 166280745L
    private val saveSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "save_scene", SAVE_SCENE_HASH)
    }

    private const val SAVE_SCENE_AS_HASH = 3647332257L
    private val saveSceneAsBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "save_scene_as", SAVE_SCENE_AS_HASH)
    }

    private const val SAVE_ALL_SCENES_HASH = 3218959716L
    private val saveAllScenesBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "save_all_scenes", SAVE_ALL_SCENES_HASH)
    }

    private const val CLOSE_SCENE_HASH = 166280745L
    private val closeSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "close_scene", CLOSE_SCENE_HASH)
    }

    private const val MARK_SCENE_AS_UNSAVED_HASH = 3218959716L
    private val markSceneAsUnsavedBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "mark_scene_as_unsaved", MARK_SCENE_AS_UNSAVED_HASH)
    }

    private const val PLAY_MAIN_SCENE_HASH = 3218959716L
    private val playMainSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "play_main_scene", PLAY_MAIN_SCENE_HASH)
    }

    private const val PLAY_CURRENT_SCENE_HASH = 3218959716L
    private val playCurrentSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "play_current_scene", PLAY_CURRENT_SCENE_HASH)
    }

    private const val PLAY_CUSTOM_SCENE_HASH = 83702148L
    private val playCustomSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "play_custom_scene", PLAY_CUSTOM_SCENE_HASH)
    }

    private const val STOP_PLAYING_SCENE_HASH = 3218959716L
    private val stopPlayingSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "stop_playing_scene", STOP_PLAYING_SCENE_HASH)
    }

    private const val IS_PLAYING_SCENE_HASH = 36873697L
    private val isPlayingSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_playing_scene", IS_PLAYING_SCENE_HASH)
    }

    private const val GET_PLAYING_SCENE_HASH = 201670096L
    private val getPlayingSceneBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "get_playing_scene", GET_PLAYING_SCENE_HASH)
    }

    private const val SET_MOVIE_MAKER_ENABLED_HASH = 2586408642L
    private val setMovieMakerEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "set_movie_maker_enabled", SET_MOVIE_MAKER_ENABLED_HASH)
    }

    private const val IS_MOVIE_MAKER_ENABLED_HASH = 36873697L
    private val isMovieMakerEnabledBind by lazy {
        ObjectCalls.getMethodBind("EditorInterface", "is_movie_maker_enabled", IS_MOVIE_MAKER_ENABLED_HASH)
    }
}

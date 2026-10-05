package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A dialog for selecting files or directories in the filesystem.
 *
 * Generated from Godot docs: FileDialog
 */
open class FileDialog(handle: GodotHandle) : ConfirmationDialog(handle) {
    var modeOverridesTitle: Boolean
        @JvmName("modeOverridesTitleProperty")
        get() = isModeOverridingTitle()
        @JvmName("setModeOverridesTitleProperty")
        set(value) = setModeOverridesTitle(value)

    var fileMode: FileDialog.FileMode
        @JvmName("fileModeProperty")
        get() = getFileMode()
        @JvmName("setFileModeProperty")
        set(value) = setFileMode(value)

    var displayMode: FileDialog.DisplayMode
        @JvmName("displayModeProperty")
        get() = getDisplayMode()
        @JvmName("setDisplayModeProperty")
        set(value) = setDisplayMode(value)

    var access: FileDialog.Access
        @JvmName("accessProperty")
        get() = getAccess()
        @JvmName("setAccessProperty")
        set(value) = setAccess(value)

    var rootSubfolder: String
        @JvmName("rootSubfolderProperty")
        get() = getRootSubfolder()
        @JvmName("setRootSubfolderProperty")
        set(value) = setRootSubfolder(value)

    var filters: List<String>
        @JvmName("filtersProperty")
        get() = getFilters()
        @JvmName("setFiltersProperty")
        set(value) = setFilters(value)

    var filenameFilter: String
        @JvmName("filenameFilterProperty")
        get() = getFilenameFilter()
        @JvmName("setFilenameFilterProperty")
        set(value) = setFilenameFilter(value)

    var showHiddenFiles: Boolean
        @JvmName("showHiddenFilesProperty")
        get() = isShowingHiddenFiles()
        @JvmName("setShowHiddenFilesProperty")
        set(value) = setShowHiddenFiles(value)

    var useNativeDialog: Boolean
        @JvmName("useNativeDialogProperty")
        get() = getUseNativeDialog()
        @JvmName("setUseNativeDialogProperty")
        set(value) = setUseNativeDialog(value)

    var optionCount: Int
        @JvmName("optionCountProperty")
        get() = getOptionCount()
        @JvmName("setOptionCountProperty")
        set(value) = setOptionCount(value)

    var hiddenFilesToggleEnabled: Boolean
        @JvmName("hiddenFilesToggleEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.HIDDEN_FILES)
        @JvmName("setHiddenFilesToggleEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.HIDDEN_FILES, value)

    var fileFilterToggleEnabled: Boolean
        @JvmName("fileFilterToggleEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.FILE_FILTER)
        @JvmName("setFileFilterToggleEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.FILE_FILTER, value)

    var fileSortOptionsEnabled: Boolean
        @JvmName("fileSortOptionsEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.FILE_SORT)
        @JvmName("setFileSortOptionsEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.FILE_SORT, value)

    var folderCreationEnabled: Boolean
        @JvmName("folderCreationEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.CREATE_FOLDER)
        @JvmName("setFolderCreationEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.CREATE_FOLDER, value)

    var favoritesEnabled: Boolean
        @JvmName("favoritesEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.FAVORITES)
        @JvmName("setFavoritesEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.FAVORITES, value)

    var recentListEnabled: Boolean
        @JvmName("recentListEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.RECENT)
        @JvmName("setRecentListEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.RECENT, value)

    var layoutToggleEnabled: Boolean
        @JvmName("layoutToggleEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.LAYOUT)
        @JvmName("setLayoutToggleEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.LAYOUT, value)

    var overwriteWarningEnabled: Boolean
        @JvmName("overwriteWarningEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.OVERWRITE_WARNING)
        @JvmName("setOverwriteWarningEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.OVERWRITE_WARNING, value)

    var deletingEnabled: Boolean
        @JvmName("deletingEnabledProperty")
        get() = isCustomizationFlagEnabled(FileDialog.Customization.DELETE)
        @JvmName("setDeletingEnabledProperty")
        set(value) = setCustomizationFlagEnabled(FileDialog.Customization.DELETE, value)

    var currentDir: String
        @JvmName("currentDirProperty")
        get() = getCurrentDir()
        @JvmName("setCurrentDirProperty")
        set(value) = setCurrentDir(value)

    var currentFile: String
        @JvmName("currentFileProperty")
        get() = getCurrentFile()
        @JvmName("setCurrentFileProperty")
        set(value) = setCurrentFile(value)

    var currentPath: String
        @JvmName("currentPathProperty")
        get() = getCurrentPath()
        @JvmName("setCurrentPathProperty")
        set(value) = setCurrentPath(value)

    /**
     * Clear all the added filters in the dialog.
     *
     * Generated from Godot docs: FileDialog.clear_filters
     */
    fun clearFilters() {
        ObjectCalls.ptrcallNoArgs(clearFiltersBind, segment)
    }

    /**
     * Adds a comma-separated file extension `filter` and comma-separated MIME type `mime_type` option
     * to the `FileDialog` with an optional `description`, which restricts what files can be picked. A
     * `filter` should be of the form `"filename.extension"`, where filename and extension can be `*`
     * to match any string. Filters starting with `.` (i.e. empty filenames) are not allowed. For
     * example, a `filter` of `"*.png, *.jpg"`, a `mime_type` of `image/png, image/jpeg`, and a
     * `description` of `"Images"` results in filter text "Images (*.png, *.jpg)". Note: Embedded file
     * dialogs and Windows file dialogs support only file extensions, while Android, Linux, and macOS
     * file dialogs also support MIME types.
     *
     * Generated from Godot docs: FileDialog.add_filter
     */
    fun addFilter(filter: String, description: String = "", mimeType: String = "") {
        ObjectCalls.ptrcallWithThreeStringArgs(addFilterBind, segment, filter, description, mimeType)
    }

    /**
     * The available file type filters. Each filter string in the array should be formatted like this:
     * `*.png,*.jpg,*.jpeg;Image Files;image/png,image/jpeg`. The description text of the filter is
     * optional and can be omitted. Both file extensions and MIME type should be always set. Note:
     * Embedded file dialogs and Windows file dialogs support only file extensions, while Android,
     * Linux, and macOS file dialogs also support MIME types.
     *
     * Generated from Godot docs: FileDialog.set_filters
     */
    fun setFilters(filters: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setFiltersBind, segment, filters)
    }

    /**
     * The available file type filters. Each filter string in the array should be formatted like this:
     * `*.png,*.jpg,*.jpeg;Image Files;image/png,image/jpeg`. The description text of the filter is
     * optional and can be omitted. Both file extensions and MIME type should be always set. Note:
     * Embedded file dialogs and Windows file dialogs support only file extensions, while Android,
     * Linux, and macOS file dialogs also support MIME types.
     *
     * Generated from Godot docs: FileDialog.get_filters
     */
    fun getFilters(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFiltersBind, segment)
    }

    /**
     * Clear the filter for file names.
     *
     * Generated from Godot docs: FileDialog.clear_filename_filter
     */
    fun clearFilenameFilter() {
        ObjectCalls.ptrcallNoArgs(clearFilenameFilterBind, segment)
    }

    /**
     * The filter for file names (case-insensitive). When set to a non-empty string, only files that
     * contains the substring will be shown. `filename_filter` can be edited by the user with the
     * filter button at the top of the file dialog. See also `filters`, which should be used to
     * restrict the file types that can be selected instead of `filename_filter` which is meant to be
     * set by the user.
     *
     * Generated from Godot docs: FileDialog.set_filename_filter
     */
    fun setFilenameFilter(filter: String) {
        ObjectCalls.ptrcallWithStringArg(setFilenameFilterBind, segment, filter)
    }

    /**
     * The filter for file names (case-insensitive). When set to a non-empty string, only files that
     * contains the substring will be shown. `filename_filter` can be edited by the user with the
     * filter button at the top of the file dialog. See also `filters`, which should be used to
     * restrict the file types that can be selected instead of `filename_filter` which is meant to be
     * set by the user.
     *
     * Generated from Godot docs: FileDialog.get_filename_filter
     */
    fun getFilenameFilter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getFilenameFilterBind, segment)
    }

    /**
     * Returns the name of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.get_option_name
     */
    fun getOptionName(option: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getOptionNameBind, segment, option)
    }

    /**
     * Returns an array of values of the `OptionButton` with index `option`.
     *
     * Generated from Godot docs: FileDialog.get_option_values
     */
    fun getOptionValues(option: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getOptionValuesBind, segment, option)
    }

    /**
     * Returns the default value index of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.get_option_default
     */
    fun getOptionDefault(option: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOptionDefaultBind, segment, option)
    }

    /**
     * Sets the name of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.set_option_name
     */
    fun setOptionName(option: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setOptionNameBind, segment, option, name)
    }

    /**
     * Sets the option values of the `OptionButton` with index `option`.
     *
     * Generated from Godot docs: FileDialog.set_option_values
     */
    fun setOptionValues(option: Int, values: List<String>) {
        ObjectCalls.ptrcallWithIntAndPackedStringListArg(setOptionValuesBind, segment, option, values)
    }

    /**
     * Sets the default value index of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.set_option_default
     */
    fun setOptionDefault(option: Int, defaultValueIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOptionDefaultBind, segment, option, defaultValueIndex)
    }

    /**
     * The number of additional `OptionButton`s and `CheckBox`es in the dialog.
     *
     * Generated from Godot docs: FileDialog.set_option_count
     */
    fun setOptionCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setOptionCountBind, segment, count)
    }

    /**
     * The number of additional `OptionButton`s and `CheckBox`es in the dialog.
     *
     * Generated from Godot docs: FileDialog.get_option_count
     */
    fun getOptionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOptionCountBind, segment)
    }

    /**
     * Adds an additional `OptionButton` to the file dialog. If `values` is empty, a `CheckBox` is
     * added instead. `default_value_index` should be an index of the value in the `values`. If
     * `values` is empty it should be either `1` (checked), or `0` (unchecked).
     *
     * Generated from Godot docs: FileDialog.add_option
     */
    fun addOption(name: String, values: List<String>, defaultValueIndex: Int) {
        ObjectCalls.ptrcallWithStringPackedStringListAndIntArgs(addOptionBind, segment, name, values, defaultValueIndex)
    }

    /**
     * Returns a `Dictionary` with the selected values of the additional `OptionButton`s and/or
     * `CheckBox`es. `Dictionary` keys are names and values are selected value indices.
     *
     * Generated from Godot docs: FileDialog.get_selected_options
     */
    fun getSelectedOptions(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSelectedOptionsBind, segment)
    }

    /**
     * The current working directory of the file dialog. Note: For native file dialogs, this property
     * is only treated as a hint and may not be respected by specific OS implementations.
     *
     * Generated from Godot docs: FileDialog.get_current_dir
     */
    fun getCurrentDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentDirBind, segment)
    }

    /**
     * The currently selected file of the file dialog.
     *
     * Generated from Godot docs: FileDialog.get_current_file
     */
    fun getCurrentFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentFileBind, segment)
    }

    /**
     * The currently selected file path of the file dialog.
     *
     * Generated from Godot docs: FileDialog.get_current_path
     */
    fun getCurrentPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentPathBind, segment)
    }

    /**
     * The current working directory of the file dialog. Note: For native file dialogs, this property
     * is only treated as a hint and may not be respected by specific OS implementations.
     *
     * Generated from Godot docs: FileDialog.set_current_dir
     */
    fun setCurrentDir(dir: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentDirBind, segment, dir)
    }

    /**
     * The currently selected file of the file dialog.
     *
     * Generated from Godot docs: FileDialog.set_current_file
     */
    fun setCurrentFile(file: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentFileBind, segment, file)
    }

    /**
     * The currently selected file path of the file dialog.
     *
     * Generated from Godot docs: FileDialog.set_current_path
     */
    fun setCurrentPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentPathBind, segment, path)
    }

    /**
     * If `true`, changing the `file_mode` property will set the window title accordingly (e.g. setting
     * `file_mode` to `FileMode.OPEN_FILE` will change the window title to "Open a File").
     *
     * Generated from Godot docs: FileDialog.set_mode_overrides_title
     */
    fun setModeOverridesTitle(override: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setModeOverridesTitleBind, segment, override)
    }

    /**
     * If `true`, changing the `file_mode` property will set the window title accordingly (e.g. setting
     * `file_mode` to `FileMode.OPEN_FILE` will change the window title to "Open a File").
     *
     * Generated from Godot docs: FileDialog.is_mode_overriding_title
     */
    fun isModeOverridingTitle(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isModeOverridingTitleBind, segment)
    }

    /**
     * The dialog's open or save mode, which affects the selection behavior.
     *
     * Generated from Godot docs: FileDialog.set_file_mode
     */
    fun setFileMode(mode: FileDialog.FileMode) {
        ObjectCalls.ptrcallWithLongArg(setFileModeBind, segment, mode.value)
    }

    /**
     * The dialog's open or save mode, which affects the selection behavior.
     *
     * Generated from Godot docs: FileDialog.get_file_mode
     */
    fun getFileMode(): FileDialog.FileMode {
        return FileDialog.FileMode(ObjectCalls.ptrcallNoArgsRetLong(getFileModeBind, segment))
    }

    /**
     * Display mode of the dialog's file list.
     *
     * Generated from Godot docs: FileDialog.set_display_mode
     */
    fun setDisplayMode(mode: FileDialog.DisplayMode) {
        ObjectCalls.ptrcallWithLongArg(setDisplayModeBind, segment, mode.value)
    }

    /**
     * Display mode of the dialog's file list.
     *
     * Generated from Godot docs: FileDialog.get_display_mode
     */
    fun getDisplayMode(): FileDialog.DisplayMode {
        return FileDialog.DisplayMode(ObjectCalls.ptrcallNoArgsRetLong(getDisplayModeBind, segment))
    }

    /**
     * Returns the vertical box container of the dialog, custom controls can be added to it. Warning:
     * This is a required internal node, removing and freeing it may cause a crash. If you wish to hide
     * it or any of its children, use their `CanvasItem.visible` property. Note: Changes to this node
     * are ignored by native file dialogs, use `add_option` to add custom elements to the dialog
     * instead.
     *
     * Generated from Godot docs: FileDialog.get_vbox
     */
    fun getVbox(): VBoxContainer? {
        return VBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVboxBind, segment))
    }

    /**
     * Returns the LineEdit for the selected file. Warning: This is a required internal node, removing
     * and freeing it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: FileDialog.get_line_edit
     */
    fun getLineEdit(): LineEdit? {
        return LineEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLineEditBind, segment))
    }

    /**
     * The file system access scope. Warning: In Web builds, FileDialog cannot access the host file
     * system. In sandboxed Linux and macOS environments, `use_native_dialog` is automatically used to
     * allow limited access to host file system.
     *
     * Generated from Godot docs: FileDialog.set_access
     */
    fun setAccess(access: FileDialog.Access) {
        ObjectCalls.ptrcallWithLongArg(setAccessBind, segment, access.value)
    }

    /**
     * The file system access scope. Warning: In Web builds, FileDialog cannot access the host file
     * system. In sandboxed Linux and macOS environments, `use_native_dialog` is automatically used to
     * allow limited access to host file system.
     *
     * Generated from Godot docs: FileDialog.get_access
     */
    fun getAccess(): FileDialog.Access {
        return FileDialog.Access(ObjectCalls.ptrcallNoArgsRetLong(getAccessBind, segment))
    }

    /**
     * If non-empty, the given sub-folder will be "root" of this `FileDialog`, i.e. user won't be able
     * to go to its parent directory. Note: This property is ignored by native file dialogs.
     *
     * Generated from Godot docs: FileDialog.set_root_subfolder
     */
    fun setRootSubfolder(dir: String) {
        ObjectCalls.ptrcallWithStringArg(setRootSubfolderBind, segment, dir)
    }

    /**
     * If non-empty, the given sub-folder will be "root" of this `FileDialog`, i.e. user won't be able
     * to go to its parent directory. Note: This property is ignored by native file dialogs.
     *
     * Generated from Godot docs: FileDialog.get_root_subfolder
     */
    fun getRootSubfolder(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRootSubfolderBind, segment)
    }

    /**
     * If `true`, the dialog will show hidden files. Note: This property is ignored by native file
     * dialogs on Android and Linux.
     *
     * Generated from Godot docs: FileDialog.set_show_hidden_files
     */
    fun setShowHiddenFiles(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowHiddenFilesBind, segment, show)
    }

    /**
     * If `true`, the dialog will show hidden files. Note: This property is ignored by native file
     * dialogs on Android and Linux.
     *
     * Generated from Godot docs: FileDialog.is_showing_hidden_files
     */
    fun isShowingHiddenFiles(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShowingHiddenFilesBind, segment)
    }

    /**
     * If `true`, and if supported by the current `DisplayServer`, OS native dialog will be used
     * instead of custom one. Note: On Android, it is only supported when using `Access.FILESYSTEM`.
     * For access mode `Access.RESOURCES` and `Access.USERDATA`, the system will fall back to custom
     * FileDialog. Note: On Linux and macOS, sandboxed apps always use native dialogs to access the
     * host file system. Note: On macOS, sandboxed apps will save security-scoped bookmarks to retain
     * access to the opened folders across multiple sessions. Use `OS.get_granted_permissions` to get a
     * list of saved bookmarks. Note: Native dialogs are isolated from the base process, file dialog
     * properties can't be modified once the dialog is shown. Note: This property is ignored in
     * `EditorFileDialog`.
     *
     * Generated from Godot docs: FileDialog.set_use_native_dialog
     */
    fun setUseNativeDialog(native: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseNativeDialogBind, segment, native)
    }

    /**
     * If `true`, and if supported by the current `DisplayServer`, OS native dialog will be used
     * instead of custom one. Note: On Android, it is only supported when using `Access.FILESYSTEM`.
     * For access mode `Access.RESOURCES` and `Access.USERDATA`, the system will fall back to custom
     * FileDialog. Note: On Linux and macOS, sandboxed apps always use native dialogs to access the
     * host file system. Note: On macOS, sandboxed apps will save security-scoped bookmarks to retain
     * access to the opened folders across multiple sessions. Use `OS.get_granted_permissions` to get a
     * list of saved bookmarks. Note: Native dialogs are isolated from the base process, file dialog
     * properties can't be modified once the dialog is shown. Note: This property is ignored in
     * `EditorFileDialog`.
     *
     * Generated from Godot docs: FileDialog.get_use_native_dialog
     */
    fun getUseNativeDialog(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseNativeDialogBind, segment)
    }

    /**
     * If `true`, shows the recent directories list on the left side of the dialog.
     *
     * Generated from Godot docs: FileDialog.set_customization_flag_enabled
     */
    fun setCustomizationFlagEnabled(flag: FileDialog.Customization, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setCustomizationFlagEnabledBind, segment, flag.value, enabled)
    }

    /**
     * If `true`, shows the recent directories list on the left side of the dialog.
     *
     * Generated from Godot docs: FileDialog.is_customization_flag_enabled
     */
    fun isCustomizationFlagEnabled(flag: FileDialog.Customization): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isCustomizationFlagEnabledBind, segment, flag.value)
    }

    /**
     * Clear all currently selected items in the dialog.
     *
     * Generated from Godot docs: FileDialog.deselect_all
     */
    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, segment)
    }

    /**
     * Shows the `FileDialog` using the default size and position for file dialogs, and selects the
     * file name if there is a current file.
     *
     * Generated from Godot docs: FileDialog.popup_file_dialog
     */
    fun popupFileDialog() {
        ObjectCalls.ptrcallNoArgs(popupFileDialogBind, segment)
    }

    /**
     * Invalidates and updates this dialog's content list. Note: This method does nothing on native
     * file dialogs.
     *
     * Generated from Godot docs: FileDialog.invalidate
     */
    fun invalidate() {
        ObjectCalls.ptrcallNoArgs(invalidateBind, segment)
    }

    /** Signal `file_selected(path: String)`; see [TypedSignal]. */
    val fileSelected: Signal1<String>
        @JvmName("fileSelectedTypedSignal")
        get() = Signal1(this, "file_selected", SignalArgType.STRING)

    /** Signal `files_selected(paths: PackedStringArray)`; see [TypedSignal]. On iOS a PackedStringArray argument is not delivered yet: a connection reports a script error. */
    val filesSelected: Signal1<List<String>>
        @JvmName("filesSelectedTypedSignal")
        get() = Signal1(this, "files_selected", SignalArgType.valueOf<List<String>>("PackedStringArray", List::class))

    /** Signal `dir_selected(dir: String)`; see [TypedSignal]. */
    val dirSelected: Signal1<String>
        @JvmName("dirSelectedTypedSignal")
        get() = Signal1(this, "dir_selected", SignalArgType.STRING)

    /** Signal `filename_filter_changed(filter: String)`; see [TypedSignal]. */
    val filenameFilterChanged: Signal1<String>
        @JvmName("filenameFilterChangedTypedSignal")
        get() = Signal1(this, "filename_filter_changed", SignalArgType.STRING)

    object Signals {
        const val fileSelected: String = "file_selected"
        const val filesSelected: String = "files_selected"
        const val dirSelected: String = "dir_selected"
        const val filenameFilterChanged: String = "filename_filter_changed"
    }

    /**
     * Godot's `FileDialog.FileMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`FileDialog.FileMode.<NAME>`).
     *
     * Generated from Godot docs: FileDialog.FileMode
     */
    @JvmInline
    value class FileMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The dialog allows selecting one, and only one file.
             *
             * Generated from Godot docs: FileDialog.FILE_MODE_OPEN_FILE
             */
            val OPEN_FILE: FileMode get() = FileMode(0L)
            /**
             * The dialog allows selecting multiple files.
             *
             * Generated from Godot docs: FileDialog.FILE_MODE_OPEN_FILES
             */
            val OPEN_FILES: FileMode get() = FileMode(1L)
            /**
             * The dialog only allows selecting a directory, disallowing the selection of any file.
             *
             * Generated from Godot docs: FileDialog.FILE_MODE_OPEN_DIR
             */
            val OPEN_DIR: FileMode get() = FileMode(2L)
            /**
             * The dialog allows selecting one file or directory.
             *
             * Generated from Godot docs: FileDialog.FILE_MODE_OPEN_ANY
             */
            val OPEN_ANY: FileMode get() = FileMode(3L)
            /**
             * The dialog will warn when a file exists.
             *
             * Generated from Godot docs: FileDialog.FILE_MODE_SAVE_FILE
             */
            val SAVE_FILE: FileMode get() = FileMode(4L)
        }
    }

    /**
     * Godot's `FileDialog.Access` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`FileDialog.Access.<NAME>`).
     *
     * Generated from Godot docs: FileDialog.Access
     */
    @JvmInline
    value class Access(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The dialog only allows accessing files under the `Resource` path (`res://`).
             *
             * Generated from Godot docs: FileDialog.ACCESS_RESOURCES
             */
            val RESOURCES: Access get() = Access(0L)
            /**
             * The dialog only allows accessing files under user data path (`user://`).
             *
             * Generated from Godot docs: FileDialog.ACCESS_USERDATA
             */
            val USERDATA: Access get() = Access(1L)
            /**
             * The dialog allows accessing files on the whole file system.
             *
             * Generated from Godot docs: FileDialog.ACCESS_FILESYSTEM
             */
            val FILESYSTEM: Access get() = Access(2L)
        }
    }

    /**
     * Godot's `FileDialog.DisplayMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`FileDialog.DisplayMode.<NAME>`).
     *
     * Generated from Godot docs: FileDialog.DisplayMode
     */
    @JvmInline
    value class DisplayMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The dialog displays files as a grid of thumbnails. Use `thumbnail_size` to adjust their size.
             *
             * Generated from Godot docs: FileDialog.DISPLAY_THUMBNAILS
             */
            val THUMBNAILS: DisplayMode get() = DisplayMode(0L)
            /**
             * The dialog displays files as a list of filenames.
             *
             * Generated from Godot docs: FileDialog.DISPLAY_LIST
             */
            val LIST: DisplayMode get() = DisplayMode(1L)
        }
    }

    /**
     * Godot's `FileDialog.Customization` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`FileDialog.Customization.<NAME>`).
     *
     * Generated from Godot docs: FileDialog.Customization
     */
    @JvmInline
    value class Customization(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Toggles visibility of the favorite button, and the favorite list on the left side of the dialog.
             * Equivalent to `hidden_files_toggle_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_HIDDEN_FILES
             */
            val HIDDEN_FILES: Customization get() = Customization(0L)
            /**
             * If enabled, shows the button for creating new directories (when using `FileMode.OPEN_DIR`,
             * `FileMode.OPEN_ANY`, or `FileMode.SAVE_FILE`). Equivalent to `folder_creation_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_CREATE_FOLDER
             */
            val CREATE_FOLDER: Customization get() = Customization(1L)
            /**
             * If enabled, shows the toggle file filter button. Equivalent to `file_filter_toggle_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_FILE_FILTER
             */
            val FILE_FILTER: Customization get() = Customization(2L)
            /**
             * If enabled, shows the file sorting options button. Equivalent to `file_sort_options_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_FILE_SORT
             */
            val FILE_SORT: Customization get() = Customization(3L)
            /**
             * If enabled, shows the toggle favorite button and favorite list on the left side of the dialog.
             * Equivalent to `favorites_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_FAVORITES
             */
            val FAVORITES: Customization get() = Customization(4L)
            /**
             * If enabled, shows the recent directories list on the left side of the dialog. Equivalent to
             * `recent_list_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_RECENT
             */
            val RECENT: Customization get() = Customization(5L)
            /**
             * If enabled, shows the layout switch buttons (list/thumbnails). Equivalent to
             * `layout_toggle_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_LAYOUT
             */
            val LAYOUT: Customization get() = Customization(6L)
            /**
             * If enabled, the `FileDialog` will warn the user before overwriting files in save mode.
             * Equivalent to `overwrite_warning_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_OVERWRITE_WARNING
             */
            val OVERWRITE_WARNING: Customization get() = Customization(7L)
            /**
             * If enabled, the context menu will show the "Delete" option, which allows moving files and
             * folders to trash. Equivalent to `deleting_enabled`.
             *
             * Generated from Godot docs: FileDialog.CUSTOMIZATION_DELETE
             */
            val DELETE: Customization get() = Customization(8L)
        }
    }

    companion object {
        /**
         * Sets the list of favorite directories, which is shared by all `FileDialog` nodes. Useful to
         * restore the list of favorites saved with `get_favorite_list`. This method can be called only
         * from the main thread. Note: `FileDialog` will update its internal `ItemList` of favorites when
         * its visibility changes. Be sure to call this method earlier if you want your changes to have
         * effect.
         *
         * Generated from Godot docs: FileDialog.set_favorite_list
         */
        fun setFavoriteList(favorites: List<String>) {
            ObjectCalls.ptrcallWithPackedStringListArg(setFavoriteListBind, NULL_SEGMENT, favorites)
        }

        /**
         * Returns the list of favorite directories, which is shared by all `FileDialog` nodes. Useful to
         * store the list of favorites between project sessions. This method can be called only from the
         * main thread.
         *
         * Generated from Godot docs: FileDialog.get_favorite_list
         */
        fun getFavoriteList(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoriteListBind, NULL_SEGMENT)
        }

        /**
         * Sets the list of recent directories, which is shared by all `FileDialog` nodes. Useful to
         * restore the list of recents saved with `set_recent_list`. This method can be called only from
         * the main thread. Note: `FileDialog` will update its internal `ItemList` of recent directories
         * when its visibility changes. Be sure to call this method earlier if you want your changes to
         * have effect.
         *
         * Generated from Godot docs: FileDialog.set_recent_list
         */
        fun setRecentList(recents: List<String>) {
            ObjectCalls.ptrcallWithPackedStringListArg(setRecentListBind, NULL_SEGMENT, recents)
        }

        /**
         * Returns the list of recent directories, which is shared by all `FileDialog` nodes. Useful to
         * store the list of recents between project sessions. This method can be called only from the main
         * thread.
         *
         * Generated from Godot docs: FileDialog.get_recent_list
         */
        fun getRecentList(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentListBind, NULL_SEGMENT)
        }

        /**
         * Sets the callback used by the `FileDialog` nodes to get a file icon, when `DisplayMode.LIST`
         * mode is used. The callback should take a single `String` argument (file path), and return a
         * `Texture2D`. If an invalid texture is returned, the `file` icon will be used instead.
         *
         * Generated from Godot docs: FileDialog.set_get_icon_callback
         */
        fun setGetIconCallback(callback: GodotCallable) {
            ObjectCalls.ptrcallWithCallableArg(setGetIconCallbackBind, NULL_SEGMENT, callback.target.segment, callback.method)
        }

        /**
         * Sets the callback used by the `FileDialog` nodes to get a file icon, when
         * `DisplayMode.THUMBNAILS` mode is used. The callback should take a single `String` argument (file
         * path), and return a `Texture2D`. If an invalid texture is returned, the `file_thumbnail` icon
         * will be used instead. Thumbnails are usually more complex and may take a while to load. To avoid
         * stalling the application, you can use `ImageTexture` to asynchronously create the thumbnail.
         *
         * Generated from Godot docs: FileDialog.set_get_thumbnail_callback
         */
        fun setGetThumbnailCallback(callback: GodotCallable) {
            ObjectCalls.ptrcallWithCallableArg(setGetThumbnailCallbackBind, NULL_SEGMENT, callback.target.segment, callback.method)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): FileDialog? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FileDialog? =
            if (handle.address() == 0L) null else FileDialog(GodotHandle(handle))

        private const val CLEAR_FILTERS_HASH = 3218959716L
        private val clearFiltersBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "clear_filters", CLEAR_FILTERS_HASH)
        }

        private const val ADD_FILTER_HASH = 914921954L
        private val addFilterBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "add_filter", ADD_FILTER_HASH)
        }

        private const val SET_FILTERS_HASH = 4015028928L
        private val setFiltersBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_filters", SET_FILTERS_HASH)
        }

        private const val GET_FILTERS_HASH = 1139954409L
        private val getFiltersBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_filters", GET_FILTERS_HASH)
        }

        private const val CLEAR_FILENAME_FILTER_HASH = 3218959716L
        private val clearFilenameFilterBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "clear_filename_filter", CLEAR_FILENAME_FILTER_HASH)
        }

        private const val SET_FILENAME_FILTER_HASH = 83702148L
        private val setFilenameFilterBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_filename_filter", SET_FILENAME_FILTER_HASH)
        }

        private const val GET_FILENAME_FILTER_HASH = 201670096L
        private val getFilenameFilterBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_filename_filter", GET_FILENAME_FILTER_HASH)
        }

        private const val GET_OPTION_NAME_HASH = 844755477L
        private val getOptionNameBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_option_name", GET_OPTION_NAME_HASH)
        }

        private const val GET_OPTION_VALUES_HASH = 647634434L
        private val getOptionValuesBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_option_values", GET_OPTION_VALUES_HASH)
        }

        private const val GET_OPTION_DEFAULT_HASH = 923996154L
        private val getOptionDefaultBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_option_default", GET_OPTION_DEFAULT_HASH)
        }

        private const val SET_OPTION_NAME_HASH = 501894301L
        private val setOptionNameBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_option_name", SET_OPTION_NAME_HASH)
        }

        private const val SET_OPTION_VALUES_HASH = 3353661094L
        private val setOptionValuesBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_option_values", SET_OPTION_VALUES_HASH)
        }

        private const val SET_OPTION_DEFAULT_HASH = 3937882851L
        private val setOptionDefaultBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_option_default", SET_OPTION_DEFAULT_HASH)
        }

        private const val SET_OPTION_COUNT_HASH = 1286410249L
        private val setOptionCountBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_option_count", SET_OPTION_COUNT_HASH)
        }

        private const val GET_OPTION_COUNT_HASH = 3905245786L
        private val getOptionCountBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_option_count", GET_OPTION_COUNT_HASH)
        }

        private const val ADD_OPTION_HASH = 149592325L
        private val addOptionBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "add_option", ADD_OPTION_HASH)
        }

        private const val GET_SELECTED_OPTIONS_HASH = 3102165223L
        private val getSelectedOptionsBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_selected_options", GET_SELECTED_OPTIONS_HASH)
        }

        private const val GET_CURRENT_DIR_HASH = 201670096L
        private val getCurrentDirBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_current_dir", GET_CURRENT_DIR_HASH)
        }

        private const val GET_CURRENT_FILE_HASH = 201670096L
        private val getCurrentFileBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_current_file", GET_CURRENT_FILE_HASH)
        }

        private const val GET_CURRENT_PATH_HASH = 201670096L
        private val getCurrentPathBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_current_path", GET_CURRENT_PATH_HASH)
        }

        private const val SET_CURRENT_DIR_HASH = 83702148L
        private val setCurrentDirBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_current_dir", SET_CURRENT_DIR_HASH)
        }

        private const val SET_CURRENT_FILE_HASH = 83702148L
        private val setCurrentFileBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_current_file", SET_CURRENT_FILE_HASH)
        }

        private const val SET_CURRENT_PATH_HASH = 83702148L
        private val setCurrentPathBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_current_path", SET_CURRENT_PATH_HASH)
        }

        private const val SET_MODE_OVERRIDES_TITLE_HASH = 2586408642L
        private val setModeOverridesTitleBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_mode_overrides_title", SET_MODE_OVERRIDES_TITLE_HASH)
        }

        private const val IS_MODE_OVERRIDING_TITLE_HASH = 36873697L
        private val isModeOverridingTitleBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "is_mode_overriding_title", IS_MODE_OVERRIDING_TITLE_HASH)
        }

        private const val SET_FILE_MODE_HASH = 3654936397L
        private val setFileModeBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_file_mode", SET_FILE_MODE_HASH)
        }

        private const val GET_FILE_MODE_HASH = 4074825319L
        private val getFileModeBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_file_mode", GET_FILE_MODE_HASH)
        }

        private const val SET_DISPLAY_MODE_HASH = 2692197101L
        private val setDisplayModeBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_display_mode", SET_DISPLAY_MODE_HASH)
        }

        private const val GET_DISPLAY_MODE_HASH = 1092104624L
        private val getDisplayModeBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_display_mode", GET_DISPLAY_MODE_HASH)
        }

        private const val GET_VBOX_HASH = 915758477L
        private val getVboxBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_vbox", GET_VBOX_HASH)
        }

        private const val GET_LINE_EDIT_HASH = 4071694264L
        private val getLineEditBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_line_edit", GET_LINE_EDIT_HASH)
        }

        private const val SET_ACCESS_HASH = 4104413466L
        private val setAccessBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_access", SET_ACCESS_HASH)
        }

        private const val GET_ACCESS_HASH = 3344081076L
        private val getAccessBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_access", GET_ACCESS_HASH)
        }

        private const val SET_ROOT_SUBFOLDER_HASH = 83702148L
        private val setRootSubfolderBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_root_subfolder", SET_ROOT_SUBFOLDER_HASH)
        }

        private const val GET_ROOT_SUBFOLDER_HASH = 201670096L
        private val getRootSubfolderBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_root_subfolder", GET_ROOT_SUBFOLDER_HASH)
        }

        private const val SET_SHOW_HIDDEN_FILES_HASH = 2586408642L
        private val setShowHiddenFilesBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_show_hidden_files", SET_SHOW_HIDDEN_FILES_HASH)
        }

        private const val IS_SHOWING_HIDDEN_FILES_HASH = 36873697L
        private val isShowingHiddenFilesBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "is_showing_hidden_files", IS_SHOWING_HIDDEN_FILES_HASH)
        }

        private const val SET_USE_NATIVE_DIALOG_HASH = 2586408642L
        private val setUseNativeDialogBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_use_native_dialog", SET_USE_NATIVE_DIALOG_HASH)
        }

        private const val GET_USE_NATIVE_DIALOG_HASH = 36873697L
        private val getUseNativeDialogBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_use_native_dialog", GET_USE_NATIVE_DIALOG_HASH)
        }

        private const val SET_CUSTOMIZATION_FLAG_ENABLED_HASH = 3849177100L
        private val setCustomizationFlagEnabledBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_customization_flag_enabled", SET_CUSTOMIZATION_FLAG_ENABLED_HASH)
        }

        private const val IS_CUSTOMIZATION_FLAG_ENABLED_HASH = 3722277863L
        private val isCustomizationFlagEnabledBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "is_customization_flag_enabled", IS_CUSTOMIZATION_FLAG_ENABLED_HASH)
        }

        private const val DESELECT_ALL_HASH = 3218959716L
        private val deselectAllBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "deselect_all", DESELECT_ALL_HASH)
        }

        private const val SET_FAVORITE_LIST_HASH = 4015028928L
        private val setFavoriteListBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_favorite_list", SET_FAVORITE_LIST_HASH)
        }

        private const val GET_FAVORITE_LIST_HASH = 2981934095L
        private val getFavoriteListBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_favorite_list", GET_FAVORITE_LIST_HASH)
        }

        private const val SET_RECENT_LIST_HASH = 4015028928L
        private val setRecentListBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_recent_list", SET_RECENT_LIST_HASH)
        }

        private const val GET_RECENT_LIST_HASH = 2981934095L
        private val getRecentListBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "get_recent_list", GET_RECENT_LIST_HASH)
        }

        private const val SET_GET_ICON_CALLBACK_HASH = 1611583062L
        private val setGetIconCallbackBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_get_icon_callback", SET_GET_ICON_CALLBACK_HASH)
        }

        private const val SET_GET_THUMBNAIL_CALLBACK_HASH = 1611583062L
        private val setGetThumbnailCallbackBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "set_get_thumbnail_callback", SET_GET_THUMBNAIL_CALLBACK_HASH)
        }

        private const val POPUP_FILE_DIALOG_HASH = 3218959716L
        private val popupFileDialogBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "popup_file_dialog", POPUP_FILE_DIALOG_HASH)
        }

        private const val INVALIDATE_HASH = 3218959716L
        private val invalidateBind by lazy {
            ObjectCalls.getMethodBind("FileDialog", "invalidate", INVALIDATE_HASH)
        }
    }
}

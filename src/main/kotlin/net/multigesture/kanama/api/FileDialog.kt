package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A dialog for selecting files or directories in the filesystem.
 *
 * Generated from Godot docs: FileDialog
 */
open class FileDialog(handle: MemorySegment) : ConfirmationDialog(handle) {
    var modeOverridesTitle: Boolean
        @JvmName("modeOverridesTitleProperty")
        get() = isModeOverridingTitle()
        @JvmName("setModeOverridesTitleProperty")
        set(value) = setModeOverridesTitle(value)

    var fileMode: Long
        @JvmName("fileModeProperty")
        get() = getFileMode()
        @JvmName("setFileModeProperty")
        set(value) = setFileMode(value)

    var displayMode: Long
        @JvmName("displayModeProperty")
        get() = getDisplayMode()
        @JvmName("setDisplayModeProperty")
        set(value) = setDisplayMode(value)

    var access: Long
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
        ObjectCalls.ptrcallNoArgs(clearFiltersBind, handle)
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
        ObjectCalls.ptrcallWithThreeStringArgs(addFilterBind, handle, filter, description, mimeType)
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
        ObjectCalls.ptrcallWithPackedStringListArg(setFiltersBind, handle, filters)
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
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFiltersBind, handle)
    }

    /**
     * Clear the filter for file names.
     *
     * Generated from Godot docs: FileDialog.clear_filename_filter
     */
    fun clearFilenameFilter() {
        ObjectCalls.ptrcallNoArgs(clearFilenameFilterBind, handle)
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
        ObjectCalls.ptrcallWithStringArg(setFilenameFilterBind, handle, filter)
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
        return ObjectCalls.ptrcallNoArgsRetString(getFilenameFilterBind, handle)
    }

    /**
     * Returns the name of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.get_option_name
     */
    fun getOptionName(option: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getOptionNameBind, handle, option)
    }

    /**
     * Returns an array of values of the `OptionButton` with index `option`.
     *
     * Generated from Godot docs: FileDialog.get_option_values
     */
    fun getOptionValues(option: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getOptionValuesBind, handle, option)
    }

    /**
     * Returns the default value index of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.get_option_default
     */
    fun getOptionDefault(option: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOptionDefaultBind, handle, option)
    }

    /**
     * Sets the name of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.set_option_name
     */
    fun setOptionName(option: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setOptionNameBind, handle, option, name)
    }

    /**
     * Sets the option values of the `OptionButton` with index `option`.
     *
     * Generated from Godot docs: FileDialog.set_option_values
     */
    fun setOptionValues(option: Int, values: List<String>) {
        ObjectCalls.ptrcallWithIntAndPackedStringListArg(setOptionValuesBind, handle, option, values)
    }

    /**
     * Sets the default value index of the `OptionButton` or `CheckBox` with index `option`.
     *
     * Generated from Godot docs: FileDialog.set_option_default
     */
    fun setOptionDefault(option: Int, defaultValueIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOptionDefaultBind, handle, option, defaultValueIndex)
    }

    /**
     * The number of additional `OptionButton`s and `CheckBox`es in the dialog.
     *
     * Generated from Godot docs: FileDialog.set_option_count
     */
    fun setOptionCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setOptionCountBind, handle, count)
    }

    /**
     * The number of additional `OptionButton`s and `CheckBox`es in the dialog.
     *
     * Generated from Godot docs: FileDialog.get_option_count
     */
    fun getOptionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOptionCountBind, handle)
    }

    /**
     * Adds an additional `OptionButton` to the file dialog. If `values` is empty, a `CheckBox` is
     * added instead. `default_value_index` should be an index of the value in the `values`. If
     * `values` is empty it should be either `1` (checked), or `0` (unchecked).
     *
     * Generated from Godot docs: FileDialog.add_option
     */
    fun addOption(name: String, values: List<String>, defaultValueIndex: Int) {
        ObjectCalls.ptrcallWithStringPackedStringListAndIntArgs(addOptionBind, handle, name, values, defaultValueIndex)
    }

    /**
     * Returns a `Dictionary` with the selected values of the additional `OptionButton`s and/or
     * `CheckBox`es. `Dictionary` keys are names and values are selected value indices.
     *
     * Generated from Godot docs: FileDialog.get_selected_options
     */
    fun getSelectedOptions(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSelectedOptionsBind, handle)
    }

    /**
     * The current working directory of the file dialog. Note: For native file dialogs, this property
     * is only treated as a hint and may not be respected by specific OS implementations.
     *
     * Generated from Godot docs: FileDialog.get_current_dir
     */
    fun getCurrentDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentDirBind, handle)
    }

    /**
     * The currently selected file of the file dialog.
     *
     * Generated from Godot docs: FileDialog.get_current_file
     */
    fun getCurrentFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentFileBind, handle)
    }

    /**
     * The currently selected file path of the file dialog.
     *
     * Generated from Godot docs: FileDialog.get_current_path
     */
    fun getCurrentPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentPathBind, handle)
    }

    /**
     * The current working directory of the file dialog. Note: For native file dialogs, this property
     * is only treated as a hint and may not be respected by specific OS implementations.
     *
     * Generated from Godot docs: FileDialog.set_current_dir
     */
    fun setCurrentDir(dir: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentDirBind, handle, dir)
    }

    /**
     * The currently selected file of the file dialog.
     *
     * Generated from Godot docs: FileDialog.set_current_file
     */
    fun setCurrentFile(file: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentFileBind, handle, file)
    }

    /**
     * The currently selected file path of the file dialog.
     *
     * Generated from Godot docs: FileDialog.set_current_path
     */
    fun setCurrentPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentPathBind, handle, path)
    }

    /**
     * If `true`, changing the `file_mode` property will set the window title accordingly (e.g. setting
     * `file_mode` to `FILE_MODE_OPEN_FILE` will change the window title to "Open a File").
     *
     * Generated from Godot docs: FileDialog.set_mode_overrides_title
     */
    fun setModeOverridesTitle(override: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setModeOverridesTitleBind, handle, override)
    }

    /**
     * If `true`, changing the `file_mode` property will set the window title accordingly (e.g. setting
     * `file_mode` to `FILE_MODE_OPEN_FILE` will change the window title to "Open a File").
     *
     * Generated from Godot docs: FileDialog.is_mode_overriding_title
     */
    fun isModeOverridingTitle(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isModeOverridingTitleBind, handle)
    }

    /**
     * The dialog's open or save mode, which affects the selection behavior.
     *
     * Generated from Godot docs: FileDialog.set_file_mode
     */
    fun setFileMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFileModeBind, handle, mode)
    }

    /**
     * The dialog's open or save mode, which affects the selection behavior.
     *
     * Generated from Godot docs: FileDialog.get_file_mode
     */
    fun getFileMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFileModeBind, handle)
    }

    /**
     * Display mode of the dialog's file list.
     *
     * Generated from Godot docs: FileDialog.set_display_mode
     */
    fun setDisplayMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDisplayModeBind, handle, mode)
    }

    /**
     * Display mode of the dialog's file list.
     *
     * Generated from Godot docs: FileDialog.get_display_mode
     */
    fun getDisplayMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDisplayModeBind, handle)
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
        return VBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVboxBind, handle))
    }

    /**
     * Returns the LineEdit for the selected file. Warning: This is a required internal node, removing
     * and freeing it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: FileDialog.get_line_edit
     */
    fun getLineEdit(): LineEdit? {
        return LineEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLineEditBind, handle))
    }

    /**
     * The file system access scope. Warning: In Web builds, FileDialog cannot access the host file
     * system. In sandboxed Linux and macOS environments, `use_native_dialog` is automatically used to
     * allow limited access to host file system.
     *
     * Generated from Godot docs: FileDialog.set_access
     */
    fun setAccess(access: Long) {
        ObjectCalls.ptrcallWithLongArg(setAccessBind, handle, access)
    }

    /**
     * The file system access scope. Warning: In Web builds, FileDialog cannot access the host file
     * system. In sandboxed Linux and macOS environments, `use_native_dialog` is automatically used to
     * allow limited access to host file system.
     *
     * Generated from Godot docs: FileDialog.get_access
     */
    fun getAccess(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAccessBind, handle)
    }

    /**
     * If non-empty, the given sub-folder will be "root" of this `FileDialog`, i.e. user won't be able
     * to go to its parent directory. Note: This property is ignored by native file dialogs.
     *
     * Generated from Godot docs: FileDialog.set_root_subfolder
     */
    fun setRootSubfolder(dir: String) {
        ObjectCalls.ptrcallWithStringArg(setRootSubfolderBind, handle, dir)
    }

    /**
     * If non-empty, the given sub-folder will be "root" of this `FileDialog`, i.e. user won't be able
     * to go to its parent directory. Note: This property is ignored by native file dialogs.
     *
     * Generated from Godot docs: FileDialog.get_root_subfolder
     */
    fun getRootSubfolder(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRootSubfolderBind, handle)
    }

    /**
     * If `true`, the dialog will show hidden files. Note: This property is ignored by native file
     * dialogs on Android and Linux.
     *
     * Generated from Godot docs: FileDialog.set_show_hidden_files
     */
    fun setShowHiddenFiles(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowHiddenFilesBind, handle, show)
    }

    /**
     * If `true`, the dialog will show hidden files. Note: This property is ignored by native file
     * dialogs on Android and Linux.
     *
     * Generated from Godot docs: FileDialog.is_showing_hidden_files
     */
    fun isShowingHiddenFiles(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShowingHiddenFilesBind, handle)
    }

    /**
     * If `true`, and if supported by the current `DisplayServer`, OS native dialog will be used
     * instead of custom one. Note: On Android, it is only supported when using `ACCESS_FILESYSTEM`.
     * For access mode `ACCESS_RESOURCES` and `ACCESS_USERDATA`, the system will fall back to custom
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
        ObjectCalls.ptrcallWithBoolArg(setUseNativeDialogBind, handle, native)
    }

    /**
     * If `true`, and if supported by the current `DisplayServer`, OS native dialog will be used
     * instead of custom one. Note: On Android, it is only supported when using `ACCESS_FILESYSTEM`.
     * For access mode `ACCESS_RESOURCES` and `ACCESS_USERDATA`, the system will fall back to custom
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
        return ObjectCalls.ptrcallNoArgsRetBool(getUseNativeDialogBind, handle)
    }

    /**
     * If `true`, shows the recent directories list on the left side of the dialog.
     *
     * Generated from Godot docs: FileDialog.set_customization_flag_enabled
     */
    fun setCustomizationFlagEnabled(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setCustomizationFlagEnabledBind, handle, flag, enabled)
    }

    /**
     * If `true`, shows the recent directories list on the left side of the dialog.
     *
     * Generated from Godot docs: FileDialog.is_customization_flag_enabled
     */
    fun isCustomizationFlagEnabled(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isCustomizationFlagEnabledBind, handle, flag)
    }

    /**
     * Clear all currently selected items in the dialog.
     *
     * Generated from Godot docs: FileDialog.deselect_all
     */
    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, handle)
    }

    /**
     * Shows the `FileDialog` using the default size and position for file dialogs, and selects the
     * file name if there is a current file.
     *
     * Generated from Godot docs: FileDialog.popup_file_dialog
     */
    fun popupFileDialog() {
        ObjectCalls.ptrcallNoArgs(popupFileDialogBind, handle)
    }

    /**
     * Invalidates and updates this dialog's content list. Note: This method does nothing on native
     * file dialogs.
     *
     * Generated from Godot docs: FileDialog.invalidate
     */
    fun invalidate() {
        ObjectCalls.ptrcallNoArgs(invalidateBind, handle)
    }

    object Signals {
        const val fileSelected: String = "file_selected"
        const val filesSelected: String = "files_selected"
        const val dirSelected: String = "dir_selected"
        const val filenameFilterChanged: String = "filename_filter_changed"
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
            ObjectCalls.ptrcallWithPackedStringListArg(setFavoriteListBind, MemorySegment.NULL, favorites)
        }

        /**
         * Returns the list of favorite directories, which is shared by all `FileDialog` nodes. Useful to
         * store the list of favorites between project sessions. This method can be called only from the
         * main thread.
         *
         * Generated from Godot docs: FileDialog.get_favorite_list
         */
        fun getFavoriteList(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoriteListBind, MemorySegment.NULL)
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
            ObjectCalls.ptrcallWithPackedStringListArg(setRecentListBind, MemorySegment.NULL, recents)
        }

        /**
         * Returns the list of recent directories, which is shared by all `FileDialog` nodes. Useful to
         * store the list of recents between project sessions. This method can be called only from the main
         * thread.
         *
         * Generated from Godot docs: FileDialog.get_recent_list
         */
        fun getRecentList(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentListBind, MemorySegment.NULL)
        }

        /**
         * Sets the callback used by the `FileDialog` nodes to get a file icon, when `DISPLAY_LIST` mode is
         * used. The callback should take a single `String` argument (file path), and return a `Texture2D`.
         * If an invalid texture is returned, the `file` icon will be used instead.
         *
         * Generated from Godot docs: FileDialog.set_get_icon_callback
         */
        fun setGetIconCallback(callback: GodotCallable) {
            ObjectCalls.ptrcallWithCallableArg(setGetIconCallbackBind, MemorySegment.NULL, callback.target.handle, callback.method)
        }

        /**
         * Sets the callback used by the `FileDialog` nodes to get a file icon, when `DISPLAY_THUMBNAILS`
         * mode is used. The callback should take a single `String` argument (file path), and return a
         * `Texture2D`. If an invalid texture is returned, the `file_thumbnail` icon will be used instead.
         * Thumbnails are usually more complex and may take a while to load. To avoid stalling the
         * application, you can use `ImageTexture` to asynchronously create the thumbnail.
         *
         * Generated from Godot docs: FileDialog.set_get_thumbnail_callback
         */
        fun setGetThumbnailCallback(callback: GodotCallable) {
            ObjectCalls.ptrcallWithCallableArg(setGetThumbnailCallbackBind, MemorySegment.NULL, callback.target.handle, callback.method)
        }

        const val FILE_MODE_OPEN_FILE: Long = 0L
        const val FILE_MODE_OPEN_FILES: Long = 1L
        const val FILE_MODE_OPEN_DIR: Long = 2L
        const val FILE_MODE_OPEN_ANY: Long = 3L
        const val FILE_MODE_SAVE_FILE: Long = 4L
        const val ACCESS_RESOURCES: Long = 0L
        const val ACCESS_USERDATA: Long = 1L
        const val ACCESS_FILESYSTEM: Long = 2L
        const val DISPLAY_THUMBNAILS: Long = 0L
        const val DISPLAY_LIST: Long = 1L
        const val CUSTOMIZATION_HIDDEN_FILES: Long = 0L
        const val CUSTOMIZATION_CREATE_FOLDER: Long = 1L
        const val CUSTOMIZATION_FILE_FILTER: Long = 2L
        const val CUSTOMIZATION_FILE_SORT: Long = 3L
        const val CUSTOMIZATION_FAVORITES: Long = 4L
        const val CUSTOMIZATION_RECENT: Long = 5L
        const val CUSTOMIZATION_LAYOUT: Long = 6L
        const val CUSTOMIZATION_OVERWRITE_WARNING: Long = 7L
        const val CUSTOMIZATION_DELETE: Long = 8L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): FileDialog? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FileDialog? =
            if (handle.address() == 0L) null else FileDialog(handle)

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

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

    fun clearFilters() {
        ObjectCalls.ptrcallNoArgs(clearFiltersBind, handle)
    }

    fun addFilter(filter: String, description: String = "", mimeType: String = "") {
        ObjectCalls.ptrcallWithThreeStringArgs(addFilterBind, handle, filter, description, mimeType)
    }

    fun setFilters(filters: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setFiltersBind, handle, filters)
    }

    fun getFilters(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFiltersBind, handle)
    }

    fun clearFilenameFilter() {
        ObjectCalls.ptrcallNoArgs(clearFilenameFilterBind, handle)
    }

    fun setFilenameFilter(filter: String) {
        ObjectCalls.ptrcallWithStringArg(setFilenameFilterBind, handle, filter)
    }

    fun getFilenameFilter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getFilenameFilterBind, handle)
    }

    fun getOptionName(option: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getOptionNameBind, handle, option)
    }

    fun getOptionValues(option: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getOptionValuesBind, handle, option)
    }

    fun getOptionDefault(option: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOptionDefaultBind, handle, option)
    }

    fun setOptionName(option: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setOptionNameBind, handle, option, name)
    }

    fun setOptionValues(option: Int, values: List<String>) {
        ObjectCalls.ptrcallWithIntAndPackedStringListArg(setOptionValuesBind, handle, option, values)
    }

    fun setOptionDefault(option: Int, defaultValueIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOptionDefaultBind, handle, option, defaultValueIndex)
    }

    fun setOptionCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setOptionCountBind, handle, count)
    }

    fun getOptionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOptionCountBind, handle)
    }

    fun addOption(name: String, values: List<String>, defaultValueIndex: Int) {
        ObjectCalls.ptrcallWithStringPackedStringListAndIntArgs(addOptionBind, handle, name, values, defaultValueIndex)
    }

    fun getSelectedOptions(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSelectedOptionsBind, handle)
    }

    fun getCurrentDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentDirBind, handle)
    }

    fun getCurrentFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentFileBind, handle)
    }

    fun getCurrentPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentPathBind, handle)
    }

    fun setCurrentDir(dir: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentDirBind, handle, dir)
    }

    fun setCurrentFile(file: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentFileBind, handle, file)
    }

    fun setCurrentPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(setCurrentPathBind, handle, path)
    }

    fun setModeOverridesTitle(override: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setModeOverridesTitleBind, handle, override)
    }

    fun isModeOverridingTitle(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isModeOverridingTitleBind, handle)
    }

    fun setFileMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFileModeBind, handle, mode)
    }

    fun getFileMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFileModeBind, handle)
    }

    fun setDisplayMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDisplayModeBind, handle, mode)
    }

    fun getDisplayMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDisplayModeBind, handle)
    }

    fun getVbox(): VBoxContainer? {
        return VBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVboxBind, handle))
    }

    fun getLineEdit(): LineEdit? {
        return LineEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLineEditBind, handle))
    }

    fun setAccess(access: Long) {
        ObjectCalls.ptrcallWithLongArg(setAccessBind, handle, access)
    }

    fun getAccess(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAccessBind, handle)
    }

    fun setRootSubfolder(dir: String) {
        ObjectCalls.ptrcallWithStringArg(setRootSubfolderBind, handle, dir)
    }

    fun getRootSubfolder(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRootSubfolderBind, handle)
    }

    fun setShowHiddenFiles(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowHiddenFilesBind, handle, show)
    }

    fun isShowingHiddenFiles(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShowingHiddenFilesBind, handle)
    }

    fun setUseNativeDialog(native: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseNativeDialogBind, handle, native)
    }

    fun getUseNativeDialog(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseNativeDialogBind, handle)
    }

    fun setCustomizationFlagEnabled(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setCustomizationFlagEnabledBind, handle, flag, enabled)
    }

    fun isCustomizationFlagEnabled(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isCustomizationFlagEnabledBind, handle, flag)
    }

    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, handle)
    }

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
        fun setFavoriteList(favorites: List<String>) {
            ObjectCalls.ptrcallWithPackedStringListArg(setFavoriteListBind, MemorySegment.NULL, favorites)
        }

        fun getFavoriteList(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoriteListBind, MemorySegment.NULL)
        }

        fun setRecentList(recents: List<String>) {
            ObjectCalls.ptrcallWithPackedStringListArg(setRecentListBind, MemorySegment.NULL, recents)
        }

        fun getRecentList(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentListBind, MemorySegment.NULL)
        }

        fun setGetIconCallback(callback: GodotCallable) {
            ObjectCalls.ptrcallWithCallableArg(setGetIconCallbackBind, MemorySegment.NULL, callback.target.handle, callback.method)
        }

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

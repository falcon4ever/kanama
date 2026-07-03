package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Object that holds the project-independent editor settings.
 *
 * Generated from Godot docs: EditorSettings
 */
class EditorSettings(handle: MemorySegment) : Resource(handle) {
    /**
     * Returns `true` if the setting specified by `name` exists, `false` otherwise.
     *
     * Generated from Godot docs: EditorSettings.has_setting
     */
    fun hasSetting(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSettingBind, handle, name)
    }

    /**
     * Sets the `value` of the setting specified by `name`. This is equivalent to using `Object.set` on
     * the EditorSettings instance.
     *
     * Generated from Godot docs: EditorSettings.set_setting
     */
    fun setSetting(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setSettingBind, handle, name, value)
    }

    /**
     * Returns the value of the setting specified by `name`. This is equivalent to using `Object.get`
     * on the EditorSettings instance.
     *
     * Generated from Godot docs: EditorSettings.get_setting
     */
    fun getSetting(name: String): Any? {
        return ObjectCalls.ptrcallWithStringArgRetVariantScalar(getSettingBind, handle, name)
    }

    /**
     * Erases the setting whose name is specified by `property`.
     *
     * Generated from Godot docs: EditorSettings.erase
     */
    fun erase(property: String) {
        ObjectCalls.ptrcallWithStringArg(eraseBind, handle, property)
    }

    /**
     * Sets the initial value of the setting specified by `name` to `value`. This is used to provide a
     * value for the Revert button in the Editor Settings. If `update_current` is `true`, the setting
     * is reset to `value` as well.
     *
     * Generated from Godot docs: EditorSettings.set_initial_value
     */
    fun setInitialValue(name: String, value: Any?, updateCurrent: Boolean) {
        ObjectCalls.ptrcallWithStringNameVariantBoolArgs(setInitialValueBind, handle, name, value, updateCurrent)
    }

    /**
     * Adds a custom property info to a property. The dictionary must contain: - `name`: `String` (the
     * name of the property) - `type`: `int` (see `Variant.Type`) - optionally `hint`: `int` (see
     * `PropertyHint`) and `hint_string`: `String`
     *
     * Generated from Godot docs: EditorSettings.add_property_info
     */
    fun addPropertyInfo(info: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(addPropertyInfoBind, handle, info)
    }

    /**
     * Sets project-specific metadata with the `section`, `key` and `data` specified. This metadata is
     * stored outside the project folder and therefore won't be checked into version control. See also
     * `get_project_metadata`.
     *
     * Generated from Godot docs: EditorSettings.set_project_metadata
     */
    fun setProjectMetadata(section: String, key: String, data: Any?) {
        ObjectCalls.ptrcallWithTwoStringAndVariantArg(setProjectMetadataBind, handle, section, key, data)
    }

    /**
     * Returns project-specific metadata for the `section` and `key` specified. If the metadata doesn't
     * exist, `default` will be returned instead. See also `set_project_metadata`.
     *
     * Generated from Godot docs: EditorSettings.get_project_metadata
     */
    fun getProjectMetadata(section: String, key: String, default: Any? = null): Any? {
        return ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(getProjectMetadataBind, handle, section, key, default)
    }

    /**
     * Sets the list of favorite files and directories for this project.
     *
     * Generated from Godot docs: EditorSettings.set_favorites
     */
    fun setFavorites(dirs: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setFavoritesBind, handle, dirs)
    }

    /**
     * Returns the list of favorite files and directories for this project.
     *
     * Generated from Godot docs: EditorSettings.get_favorites
     */
    fun getFavorites(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoritesBind, handle)
    }

    /**
     * Sets the list of recently visited folders in the file dialog for this project.
     *
     * Generated from Godot docs: EditorSettings.set_recent_dirs
     */
    fun setRecentDirs(dirs: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setRecentDirsBind, handle, dirs)
    }

    /**
     * Returns the list of recently visited folders in the file dialog for this project.
     *
     * Generated from Godot docs: EditorSettings.get_recent_dirs
     */
    fun getRecentDirs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentDirsBind, handle)
    }

    /**
     * Overrides the built-in editor action `name` with the input actions defined in `actions_list`.
     *
     * Generated from Godot docs: EditorSettings.set_builtin_action_override
     */
    fun setBuiltinActionOverride(name: String, actionsList: List<InputEvent>) {
        ObjectCalls.ptrcallWithStringAndObjectListArgs(setBuiltinActionOverrideBind, handle, name, actionsList)
    }

    /**
     * Adds a `shortcut` whose path is specified by `path`. The `path` determines how the shortcut is
     * organized and displayed in the editor's shortcut settings. The path format affects the display
     * as follows: - `"name"` (no slash): Creates a category named `name` with the shortcut displayed
     * as `name`. - `"category/name"` (single slash): Displays as `name` in the `category` section. -
     * `"category/name/extra"` (multiple slashes): Extra path components are ignored, so this behaves
     * the same as `"category/name"`. Note: Shortcuts are only saved to the editor settings if they
     * differ from their original/default state. This means empty shortcuts that were originally empty
     * will not persist between editor sessions and must be re-added. If a shortcut with the same
     * `path` already exists, this method will update it with the new `shortcut` instead of creating a
     * duplicate.
     *
     * Generated from Godot docs: EditorSettings.add_shortcut
     */
    fun addShortcut(path: String, shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithStringAndObjectArg(addShortcutBind, handle, path, shortcut?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the shortcut specified by `path`.
     *
     * Generated from Godot docs: EditorSettings.remove_shortcut
     */
    fun removeShortcut(path: String) {
        ObjectCalls.ptrcallWithStringArg(removeShortcutBind, handle, path)
    }

    /**
     * Returns `true` if the shortcut specified by `path` matches the event specified by `event`,
     * `false` otherwise.
     *
     * Generated from Godot docs: EditorSettings.is_shortcut
     */
    fun isShortcut(path: String, event: InputEvent?): Boolean {
        return ObjectCalls.ptrcallWithStringAndObjectArgRetBool(isShortcutBind, handle, path, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns `true` if the shortcut specified by `path` exists, `false` otherwise.
     *
     * Generated from Godot docs: EditorSettings.has_shortcut
     */
    fun hasShortcut(path: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasShortcutBind, handle, path)
    }

    /**
     * Returns the shortcut specified by `path`. Tries to find a built-in action if no shortcut with
     * the provided path is found in the shortcut list. If found, adds it to the list and returns it,
     * otherwise returns `null`.
     *
     * Generated from Godot docs: EditorSettings.get_shortcut
     */
    fun getShortcut(path: String): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getShortcutBind, handle, path))
    }

    /**
     * Returns the list of stored shortcut paths.
     *
     * Generated from Godot docs: EditorSettings.get_shortcut_list
     */
    fun getShortcutList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getShortcutListBind, handle)
    }

    /**
     * Checks if any settings with the prefix `setting_prefix` exist in the set of changed settings.
     * See also `get_changed_settings`.
     *
     * Generated from Godot docs: EditorSettings.check_changed_settings_in_group
     */
    fun checkChangedSettingsInGroup(settingPrefix: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(checkChangedSettingsInGroupBind, handle, settingPrefix)
    }

    /**
     * Gets an array of the settings which have been changed since the last save. Note that internally
     * `changed_settings` is cleared after a successful save, so generally the most appropriate place
     * to use this method is when processing `NOTIFICATION_EDITOR_SETTINGS_CHANGED`.
     *
     * Generated from Godot docs: EditorSettings.get_changed_settings
     */
    fun getChangedSettings(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getChangedSettingsBind, handle)
    }

    /**
     * Marks the passed editor setting as being changed, see `get_changed_settings`. Only settings
     * which exist (see `has_setting`) will be accepted.
     *
     * Generated from Godot docs: EditorSettings.mark_setting_changed
     */
    fun markSettingChanged(setting: String) {
        ObjectCalls.ptrcallWithStringArg(markSettingChangedBind, handle, setting)
    }

    object Signals {
        const val settingsChanged: String = "settings_changed"
    }

    companion object {
        const val NOTIFICATION_EDITOR_SETTINGS_CHANGED: Long = 10000L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSettings? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSettings? =
            if (handle.address() == 0L) null else EditorSettings(handle)

        private const val HAS_SETTING_HASH = 3927539163L
        private val hasSettingBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "has_setting", HAS_SETTING_HASH)
        }

        private const val SET_SETTING_HASH = 402577236L
        private val setSettingBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "set_setting", SET_SETTING_HASH)
        }

        private const val GET_SETTING_HASH = 1868160156L
        private val getSettingBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_setting", GET_SETTING_HASH)
        }

        private const val ERASE_HASH = 83702148L
        private val eraseBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "erase", ERASE_HASH)
        }

        private const val SET_INITIAL_VALUE_HASH = 1529169264L
        private val setInitialValueBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "set_initial_value", SET_INITIAL_VALUE_HASH)
        }

        private const val ADD_PROPERTY_INFO_HASH = 4155329257L
        private val addPropertyInfoBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "add_property_info", ADD_PROPERTY_INFO_HASH)
        }

        private const val SET_PROJECT_METADATA_HASH = 2504492430L
        private val setProjectMetadataBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "set_project_metadata", SET_PROJECT_METADATA_HASH)
        }

        private const val GET_PROJECT_METADATA_HASH = 89809366L
        private val getProjectMetadataBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_project_metadata", GET_PROJECT_METADATA_HASH)
        }

        private const val SET_FAVORITES_HASH = 4015028928L
        private val setFavoritesBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "set_favorites", SET_FAVORITES_HASH)
        }

        private const val GET_FAVORITES_HASH = 1139954409L
        private val getFavoritesBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_favorites", GET_FAVORITES_HASH)
        }

        private const val SET_RECENT_DIRS_HASH = 4015028928L
        private val setRecentDirsBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "set_recent_dirs", SET_RECENT_DIRS_HASH)
        }

        private const val GET_RECENT_DIRS_HASH = 1139954409L
        private val getRecentDirsBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_recent_dirs", GET_RECENT_DIRS_HASH)
        }

        private const val SET_BUILTIN_ACTION_OVERRIDE_HASH = 1209351045L
        private val setBuiltinActionOverrideBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "set_builtin_action_override", SET_BUILTIN_ACTION_OVERRIDE_HASH)
        }

        private const val ADD_SHORTCUT_HASH = 4124020929L
        private val addShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "add_shortcut", ADD_SHORTCUT_HASH)
        }

        private const val REMOVE_SHORTCUT_HASH = 83702148L
        private val removeShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "remove_shortcut", REMOVE_SHORTCUT_HASH)
        }

        private const val IS_SHORTCUT_HASH = 699917945L
        private val isShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "is_shortcut", IS_SHORTCUT_HASH)
        }

        private const val HAS_SHORTCUT_HASH = 3927539163L
        private val hasShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "has_shortcut", HAS_SHORTCUT_HASH)
        }

        private const val GET_SHORTCUT_HASH = 1149070301L
        private val getShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_shortcut", GET_SHORTCUT_HASH)
        }

        private const val GET_SHORTCUT_LIST_HASH = 2981934095L
        private val getShortcutListBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_shortcut_list", GET_SHORTCUT_LIST_HASH)
        }

        private const val CHECK_CHANGED_SETTINGS_IN_GROUP_HASH = 3927539163L
        private val checkChangedSettingsInGroupBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "check_changed_settings_in_group", CHECK_CHANGED_SETTINGS_IN_GROUP_HASH)
        }

        private const val GET_CHANGED_SETTINGS_HASH = 1139954409L
        private val getChangedSettingsBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_changed_settings", GET_CHANGED_SETTINGS_HASH)
        }

        private const val MARK_SETTING_CHANGED_HASH = 83702148L
        private val markSettingChangedBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "mark_setting_changed", MARK_SETTING_CHANGED_HASH)
        }
    }
}

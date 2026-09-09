package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

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
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSettingBind, handle, name)
    }

    /**
     * Erases the setting whose name is specified by `property`.
     *
     * Generated from Godot docs: EditorSettings.erase
     */
    fun erase(property: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(eraseBind, handle, property)
    }

    /**
     * Returns the list of favorite files and directories for this project.
     *
     * Generated from Godot docs: EditorSettings.get_favorites
     */
    fun getFavorites(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoritesBind, handle)
    }

    /**
     * Returns the list of recently visited folders in the file dialog for this project.
     *
     * Generated from Godot docs: EditorSettings.get_recent_dirs
     */
    fun getRecentDirs(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentDirsBind, handle)
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
        checkOpen()
        ObjectCalls.ptrcallWithStringAndObjectArg(addShortcutBind, handle, path, shortcut?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the shortcut specified by `path`.
     *
     * Generated from Godot docs: EditorSettings.remove_shortcut
     */
    fun removeShortcut(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(removeShortcutBind, handle, path)
    }

    /**
     * Returns `true` if the shortcut specified by `path` matches the event specified by `event`,
     * `false` otherwise.
     *
     * Generated from Godot docs: EditorSettings.is_shortcut
     */
    fun isShortcut(path: String, event: InputEvent?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndObjectArgRetBool(isShortcutBind, handle, path, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns `true` if the shortcut specified by `path` exists, `false` otherwise.
     *
     * Generated from Godot docs: EditorSettings.has_shortcut
     */
    fun hasShortcut(path: String): Boolean {
        checkOpen()
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
        checkOpen()
        return Shortcut.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getShortcutBind, handle, path))
    }

    /**
     * Returns the list of stored shortcut paths.
     *
     * Generated from Godot docs: EditorSettings.get_shortcut_list
     */
    fun getShortcutList(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getShortcutListBind, handle)
    }

    /**
     * Checks if any settings with the prefix `setting_prefix` exist in the set of changed settings.
     * See also `get_changed_settings`.
     *
     * Generated from Godot docs: EditorSettings.check_changed_settings_in_group
     */
    fun checkChangedSettingsInGroup(settingPrefix: String): Boolean {
        checkOpen()
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getChangedSettingsBind, handle)
    }

    /**
     * Marks the passed editor setting as being changed, see `get_changed_settings`. Only settings
     * which exist (see `has_setting`) will be accepted.
     *
     * Generated from Godot docs: EditorSettings.mark_setting_changed
     */
    fun markSettingChanged(setting: String) {
        checkOpen()
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

        private const val ERASE_HASH = 83702148L
        private val eraseBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "erase", ERASE_HASH)
        }

        private const val GET_FAVORITES_HASH = 1139954409L
        private val getFavoritesBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_favorites", GET_FAVORITES_HASH)
        }

        private const val GET_RECENT_DIRS_HASH = 1139954409L
        private val getRecentDirsBind by lazy {
            ObjectCalls.getMethodBind("EditorSettings", "get_recent_dirs", GET_RECENT_DIRS_HASH)
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

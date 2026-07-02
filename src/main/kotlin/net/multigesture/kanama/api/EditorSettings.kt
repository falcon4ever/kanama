package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Object that holds the project-independent editor settings.
 *
 * Generated from Godot docs: EditorSettings
 */
class EditorSettings(handle: MemorySegment) : Resource(handle) {
    fun hasSetting(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSettingBind, handle, name)
    }

    fun setSetting(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setSettingBind, handle, name, value)
    }

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

    fun setInitialValue(name: String, value: Any?, updateCurrent: Boolean) {
        ObjectCalls.ptrcallWithStringNameVariantBoolArgs(setInitialValueBind, handle, name, value, updateCurrent)
    }

    fun addPropertyInfo(info: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(addPropertyInfoBind, handle, info)
    }

    fun setProjectMetadata(section: String, key: String, data: Any?) {
        ObjectCalls.ptrcallWithTwoStringAndVariantArg(setProjectMetadataBind, handle, section, key, data)
    }

    fun getProjectMetadata(section: String, key: String, default: Any? = null): Any? {
        return ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(getProjectMetadataBind, handle, section, key, default)
    }

    fun setFavorites(dirs: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setFavoritesBind, handle, dirs)
    }

    fun getFavorites(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoritesBind, handle)
    }

    fun setRecentDirs(dirs: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setRecentDirsBind, handle, dirs)
    }

    fun getRecentDirs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentDirsBind, handle)
    }

    fun setBuiltinActionOverride(name: String, actionsList: List<InputEvent>) {
        ObjectCalls.ptrcallWithStringAndObjectListArgs(setBuiltinActionOverrideBind, handle, name, actionsList)
    }

    fun addShortcut(path: String, shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithStringAndObjectArg(addShortcutBind, handle, path, shortcut?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun removeShortcut(path: String) {
        ObjectCalls.ptrcallWithStringArg(removeShortcutBind, handle, path)
    }

    fun isShortcut(path: String, event: InputEvent?): Boolean {
        return ObjectCalls.ptrcallWithStringAndObjectArgRetBool(isShortcutBind, handle, path, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun hasShortcut(path: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasShortcutBind, handle, path)
    }

    fun getShortcut(path: String): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getShortcutBind, handle, path))
    }

    fun getShortcutList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getShortcutListBind, handle)
    }

    fun checkChangedSettingsInGroup(settingPrefix: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(checkChangedSettingsInGroupBind, handle, settingPrefix)
    }

    fun getChangedSettings(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getChangedSettingsBind, handle)
    }

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

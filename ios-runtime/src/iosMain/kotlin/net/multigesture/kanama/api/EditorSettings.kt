package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorSettings
 */
class EditorSettings(handle: MemorySegment) : Resource(handle) {
    fun hasSetting(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSettingBind, handle, name)
    }

    fun erase(property: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(eraseBind, handle, property)
    }

    fun getFavorites(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFavoritesBind, handle)
    }

    fun getRecentDirs(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRecentDirsBind, handle)
    }

    fun addShortcut(path: String, shortcut: Shortcut?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndObjectArg(addShortcutBind, handle, path, shortcut?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun removeShortcut(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(removeShortcutBind, handle, path)
    }

    fun isShortcut(path: String, event: InputEvent?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndObjectArgRetBool(isShortcutBind, handle, path, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun hasShortcut(path: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(hasShortcutBind, handle, path)
    }

    fun getShortcut(path: String): Shortcut? {
        checkOpen()
        return Shortcut.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getShortcutBind, handle, path))
    }

    fun getShortcutList(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getShortcutListBind, handle)
    }

    fun checkChangedSettingsInGroup(settingPrefix: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(checkChangedSettingsInGroupBind, handle, settingPrefix)
    }

    fun getChangedSettings(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getChangedSettingsBind, handle)
    }

    fun markSettingChanged(setting: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(markSettingChangedBind, handle, setting)
    }

    object Signals {
        const val settingsChanged: String = "settings_changed"
    }

    companion object {
        const val NOTIFICATION_EDITOR_SETTINGS_CHANGED: Long = 10000L

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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An editor feature profile which can be used to disable specific features.
 *
 * Generated from Godot docs: EditorFeatureProfile
 */
class EditorFeatureProfile(handle: MemorySegment) : RefCounted(handle) {
    fun setDisableClass(className: String, disable: Boolean) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setDisableClassBind, handle, className, disable)
    }

    fun isClassDisabled(className: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassDisabledBind, handle, className)
    }

    fun setDisableClassEditor(className: String, disable: Boolean) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setDisableClassEditorBind, handle, className, disable)
    }

    fun isClassEditorDisabled(className: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassEditorDisabledBind, handle, className)
    }

    fun setDisableClassProperty(className: String, property: String, disable: Boolean) {
        ObjectCalls.ptrcallWithTwoStringNameAndBoolArgs(setDisableClassPropertyBind, handle, className, property, disable)
    }

    fun isClassPropertyDisabled(className: String, property: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isClassPropertyDisabledBind, handle, className, property)
    }

    fun setDisableFeature(feature: Long, disable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setDisableFeatureBind, handle, feature, disable)
    }

    fun isFeatureDisabled(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isFeatureDisabledBind, handle, feature)
    }

    fun getFeatureName(feature: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getFeatureNameBind, handle, feature)
    }

    fun saveToFile(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveToFileBind, handle, path)
    }

    fun loadFromFile(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadFromFileBind, handle, path)
    }

    companion object {
        const val FEATURE_3D: Long = 0L
        const val FEATURE_SCRIPT: Long = 1L
        const val FEATURE_ASSET_LIB: Long = 2L
        const val FEATURE_SCENE_TREE: Long = 3L
        const val FEATURE_NODE_DOCK: Long = 4L
        const val FEATURE_FILESYSTEM_DOCK: Long = 5L
        const val FEATURE_IMPORT_DOCK: Long = 6L
        const val FEATURE_HISTORY_DOCK: Long = 7L
        const val FEATURE_GAME: Long = 8L
        const val FEATURE_SIGNALS_DOCK: Long = 9L
        const val FEATURE_GROUPS_DOCK: Long = 10L
        const val FEATURE_MAX: Long = 11L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorFeatureProfile? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorFeatureProfile? =
            if (handle.address() == 0L) null else EditorFeatureProfile(handle)

        private const val SET_DISABLE_CLASS_HASH = 2524380260L
        private val setDisableClassBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "set_disable_class", SET_DISABLE_CLASS_HASH)
        }

        private const val IS_CLASS_DISABLED_HASH = 2619796661L
        private val isClassDisabledBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "is_class_disabled", IS_CLASS_DISABLED_HASH)
        }

        private const val SET_DISABLE_CLASS_EDITOR_HASH = 2524380260L
        private val setDisableClassEditorBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "set_disable_class_editor", SET_DISABLE_CLASS_EDITOR_HASH)
        }

        private const val IS_CLASS_EDITOR_DISABLED_HASH = 2619796661L
        private val isClassEditorDisabledBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "is_class_editor_disabled", IS_CLASS_EDITOR_DISABLED_HASH)
        }

        private const val SET_DISABLE_CLASS_PROPERTY_HASH = 865197084L
        private val setDisableClassPropertyBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "set_disable_class_property", SET_DISABLE_CLASS_PROPERTY_HASH)
        }

        private const val IS_CLASS_PROPERTY_DISABLED_HASH = 471820014L
        private val isClassPropertyDisabledBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "is_class_property_disabled", IS_CLASS_PROPERTY_DISABLED_HASH)
        }

        private const val SET_DISABLE_FEATURE_HASH = 1884871044L
        private val setDisableFeatureBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "set_disable_feature", SET_DISABLE_FEATURE_HASH)
        }

        private const val IS_FEATURE_DISABLED_HASH = 2974403161L
        private val isFeatureDisabledBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "is_feature_disabled", IS_FEATURE_DISABLED_HASH)
        }

        private const val GET_FEATURE_NAME_HASH = 3401335809L
        private val getFeatureNameBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "get_feature_name", GET_FEATURE_NAME_HASH)
        }

        private const val SAVE_TO_FILE_HASH = 166001499L
        private val saveToFileBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "save_to_file", SAVE_TO_FILE_HASH)
        }

        private const val LOAD_FROM_FILE_HASH = 166001499L
        private val loadFromFileBind by lazy {
            ObjectCalls.getMethodBind("EditorFeatureProfile", "load_from_file", LOAD_FROM_FILE_HASH)
        }
    }
}

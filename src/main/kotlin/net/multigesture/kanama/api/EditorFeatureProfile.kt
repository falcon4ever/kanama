package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An editor feature profile which can be used to disable specific features.
 *
 * Generated from Godot docs: EditorFeatureProfile
 */
class EditorFeatureProfile(handle: MemorySegment) : RefCounted(handle) {
    /**
     * If `disable` is `true`, disables the class specified by `class_name`. When disabled, the class
     * won't appear in the Create New Node dialog.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_class
     */
    fun setDisableClass(className: String, disable: Boolean) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setDisableClassBind, handle, className, disable)
    }

    /**
     * Returns `true` if the class specified by `class_name` is disabled. When disabled, the class
     * won't appear in the Create New Node dialog.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_class_disabled
     */
    fun isClassDisabled(className: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassDisabledBind, handle, className)
    }

    /**
     * If `disable` is `true`, disables editing for the class specified by `class_name`. When disabled,
     * the class will still appear in the Create New Node dialog but the Inspector will be read-only
     * when selecting a node that extends the class.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_class_editor
     */
    fun setDisableClassEditor(className: String, disable: Boolean) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setDisableClassEditorBind, handle, className, disable)
    }

    /**
     * Returns `true` if editing for the class specified by `class_name` is disabled. When disabled,
     * the class will still appear in the Create New Node dialog but the Inspector will be read-only
     * when selecting a node that extends the class.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_class_editor_disabled
     */
    fun isClassEditorDisabled(className: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassEditorDisabledBind, handle, className)
    }

    /**
     * If `disable` is `true`, disables editing for `property` in the class specified by `class_name`.
     * When a property is disabled, it won't appear in the Inspector when selecting a node that extends
     * the class specified by `class_name`.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_class_property
     */
    fun setDisableClassProperty(className: String, property: String, disable: Boolean) {
        ObjectCalls.ptrcallWithTwoStringNameAndBoolArgs(setDisableClassPropertyBind, handle, className, property, disable)
    }

    /**
     * Returns `true` if `property` is disabled in the class specified by `class_name`. When a property
     * is disabled, it won't appear in the Inspector when selecting a node that extends the class
     * specified by `class_name`.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_class_property_disabled
     */
    fun isClassPropertyDisabled(className: String, property: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isClassPropertyDisabledBind, handle, className, property)
    }

    /**
     * If `disable` is `true`, disables the editor feature specified in `feature`. When a feature is
     * disabled, it will disappear from the editor entirely.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_feature
     */
    fun setDisableFeature(feature: Long, disable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setDisableFeatureBind, handle, feature, disable)
    }

    /**
     * Returns `true` if the `feature` is disabled. When a feature is disabled, it will disappear from
     * the editor entirely.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_feature_disabled
     */
    fun isFeatureDisabled(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isFeatureDisabledBind, handle, feature)
    }

    /**
     * Returns the specified `feature`'s human-readable name.
     *
     * Generated from Godot docs: EditorFeatureProfile.get_feature_name
     */
    fun getFeatureName(feature: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getFeatureNameBind, handle, feature)
    }

    /**
     * Saves the editor feature profile to a file in JSON format. It can then be imported using the
     * feature profile manager's Import button or the `load_from_file` method. Note: Feature profiles
     * created via the user interface are saved in the `feature_profiles` directory, as a file with the
     * `.profile` extension. The editor configuration folder can be found by using
     * `EditorPaths.get_config_dir`.
     *
     * Generated from Godot docs: EditorFeatureProfile.save_to_file
     */
    fun saveToFile(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveToFileBind, handle, path)
    }

    /**
     * Loads an editor feature profile from a file. The file must follow the JSON format obtained by
     * using the feature profile manager's Export button or the `save_to_file` method. Note: Feature
     * profiles created via the user interface are loaded from the `feature_profiles` directory, as a
     * file with the `.profile` extension. The editor configuration folder can be found by using
     * `EditorPaths.get_config_dir`.
     *
     * Generated from Godot docs: EditorFeatureProfile.load_from_file
     */
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

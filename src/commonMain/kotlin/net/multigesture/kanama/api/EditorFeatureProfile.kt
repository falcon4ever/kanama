package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An editor feature profile which can be used to disable specific features.
 *
 * Generated from Godot docs: EditorFeatureProfile
 */
class EditorFeatureProfile(handle: GodotHandle) : RefCounted(handle) {
    /**
     * If `disable` is `true`, disables the class specified by `class_name`. When disabled, the class
     * won't appear in the Create New Node dialog.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_class
     */
    fun setDisableClass(className: String, disable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setDisableClassBind, segment, className, disable)
    }

    /**
     * Returns `true` if the class specified by `class_name` is disabled. When disabled, the class
     * won't appear in the Create New Node dialog.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_class_disabled
     */
    fun isClassDisabled(className: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassDisabledBind, segment, className)
    }

    /**
     * If `disable` is `true`, disables editing for the class specified by `class_name`. When disabled,
     * the class will still appear in the Create New Node dialog but the Inspector will be read-only
     * when selecting a node that extends the class.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_class_editor
     */
    fun setDisableClassEditor(className: String, disable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setDisableClassEditorBind, segment, className, disable)
    }

    /**
     * Returns `true` if editing for the class specified by `class_name` is disabled. When disabled,
     * the class will still appear in the Create New Node dialog but the Inspector will be read-only
     * when selecting a node that extends the class.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_class_editor_disabled
     */
    fun isClassEditorDisabled(className: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassEditorDisabledBind, segment, className)
    }

    /**
     * If `disable` is `true`, disables editing for `property` in the class specified by `class_name`.
     * When a property is disabled, it won't appear in the Inspector when selecting a node that extends
     * the class specified by `class_name`.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_class_property
     */
    fun setDisableClassProperty(className: String, property: String, disable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringNameAndBoolArgs(setDisableClassPropertyBind, segment, className, property, disable)
    }

    /**
     * Returns `true` if `property` is disabled in the class specified by `class_name`. When a property
     * is disabled, it won't appear in the Inspector when selecting a node that extends the class
     * specified by `class_name`.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_class_property_disabled
     */
    fun isClassPropertyDisabled(className: String, property: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isClassPropertyDisabledBind, segment, className, property)
    }

    /**
     * If `disable` is `true`, disables the editor feature specified in `feature`. When a feature is
     * disabled, it will disappear from the editor entirely.
     *
     * Generated from Godot docs: EditorFeatureProfile.set_disable_feature
     */
    fun setDisableFeature(feature: EditorFeatureProfile.Feature, disable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndBoolArgs(setDisableFeatureBind, segment, feature.value, disable)
    }

    /**
     * Returns `true` if the `feature` is disabled. When a feature is disabled, it will disappear from
     * the editor entirely.
     *
     * Generated from Godot docs: EditorFeatureProfile.is_feature_disabled
     */
    fun isFeatureDisabled(feature: EditorFeatureProfile.Feature): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(isFeatureDisabledBind, segment, feature.value)
    }

    /**
     * Returns the specified `feature`'s human-readable name.
     *
     * Generated from Godot docs: EditorFeatureProfile.get_feature_name
     */
    fun getFeatureName(feature: EditorFeatureProfile.Feature): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(getFeatureNameBind, segment, feature.value)
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
    fun saveToFile(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(saveToFileBind, segment, path))
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
    fun loadFromFile(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(loadFromFileBind, segment, path))
    }

    @JvmInline
    value class Feature(val value: Long) {
        companion object {
            /**
             * The 3D editor. If this feature is disabled, the 3D editor won't display but 3D nodes will still
             * display in the Create New Node dialog.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_3D
             */
            val FEATURE_3D: Feature get() = Feature(0L)
            /**
             * The Script tab, which contains the script editor and class reference browser. If this feature is
             * disabled, the Script tab won't display.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_SCRIPT
             */
            val SCRIPT: Feature get() = Feature(1L)
            /**
             * The Asset Store tab. If this feature is disabled, the Asset Store tab won't display.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_ASSET_LIB
             */
            val ASSET_LIB: Feature get() = Feature(2L)
            /**
             * Scene tree editing. If this feature is disabled, the Scene tree dock will still be visible but
             * will be read-only.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_SCENE_TREE
             */
            val SCENE_TREE: Feature get() = Feature(3L)
            /**
             * The Node dock. If this feature is disabled, signals and groups won't be visible and modifiable
             * from the editor.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_NODE_DOCK
             */
            val NODE_DOCK: Feature get() = Feature(4L)
            /**
             * The FileSystem dock. If this feature is disabled, the FileSystem dock won't be visible.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_FILESYSTEM_DOCK
             */
            val FILESYSTEM_DOCK: Feature get() = Feature(5L)
            /**
             * The Import dock. If this feature is disabled, the Import dock won't be visible.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_IMPORT_DOCK
             */
            val IMPORT_DOCK: Feature get() = Feature(6L)
            /**
             * The History dock. If this feature is disabled, the History dock won't be visible.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_HISTORY_DOCK
             */
            val HISTORY_DOCK: Feature get() = Feature(7L)
            /**
             * The Game tab, which allows embedding the game window and selecting nodes by clicking inside of
             * it. If this feature is disabled, the Game tab won't display.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_GAME
             */
            val GAME: Feature get() = Feature(8L)
            /**
             * The Signals dock. If this feature is disabled, signals won't be visible and modifiable from the
             * editor.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_SIGNALS_DOCK
             */
            val SIGNALS_DOCK: Feature get() = Feature(9L)
            /**
             * The Groups dock. If this feature is disabled, groups won't be visible and modifiable from the
             * editor.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_GROUPS_DOCK
             */
            val GROUPS_DOCK: Feature get() = Feature(10L)
            /**
             * Represents the size of the `Feature` enum.
             *
             * Generated from Godot docs: EditorFeatureProfile.FEATURE_MAX
             */
            val MAX: Feature get() = Feature(11L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorFeatureProfile? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorFeatureProfile? =
            if (handle.address() == 0L) null else EditorFeatureProfile(GodotHandle(handle))

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

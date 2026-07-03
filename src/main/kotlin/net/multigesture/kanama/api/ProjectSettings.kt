package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Stores globally-accessible variables.
 *
 * Generated from Godot docs: ProjectSettings
 */
object ProjectSettings {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ProjectSettings")
    }

    /**
     * Returns `true` if a configuration value is present. Note: In order to be be detected, custom
     * settings have to be either defined with `set_setting`, or exist in the `project.godot` file.
     * This is especially relevant when using `set_initial_value`.
     *
     * Generated from Godot docs: ProjectSettings.has_setting
     */
    @JvmStatic
    fun hasSetting(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSettingBind, singleton, name)
    }

    /**
     * Sets the value of a setting.
     *
     * Generated from Godot docs: ProjectSettings.set_setting
     */
    @JvmStatic
    fun setSetting(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setSettingBind, singleton, name, value)
    }

    /**
     * Returns the value of the setting identified by `name`. If the setting doesn't exist and
     * `default_value` is specified, the value of `default_value` is returned. Otherwise, `null` is
     * returned.
     *
     * Generated from Godot docs: ProjectSettings.get_setting
     */
    @JvmStatic
    fun getSetting(name: String, defaultValue: Any? = null): Any? {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetVariantScalar(getSettingBind, singleton, name, defaultValue)
    }

    /**
     * Similar to `get_setting`, but applies feature tag overrides if any exists and is valid.
     *
     * Generated from Godot docs: ProjectSettings.get_setting_with_override
     */
    @JvmStatic
    fun getSettingWithOverride(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getSettingWithOverrideBind, singleton, name)
    }

    /**
     * Returns an `Array` of registered global classes. Each global class is represented as a
     * `Dictionary` that contains the following entries: - `base` is a name of the base class; -
     * `class` is a name of the registered global class; - `icon` is a path to a custom icon of the
     * global class, if it has any; - `language` is a name of a programming language in which the
     * global class is written; - `path` is a path to a file containing the global class. Note: Both
     * the script and the icon paths are local to the project filesystem, i.e. they start with
     * `res://`.
     *
     * Generated from Godot docs: ProjectSettings.get_global_class_list
     */
    @JvmStatic
    fun getGlobalClassList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getGlobalClassListBind, singleton)
    }

    /**
     * Similar to `get_setting_with_override`, but applies feature tag overrides instead of current OS
     * features.
     *
     * Generated from Godot docs: ProjectSettings.get_setting_with_override_and_custom_features
     */
    @JvmStatic
    fun getSettingWithOverrideAndCustomFeatures(name: String, features: List<String>): Any? {
        return ObjectCalls.ptrcallWithStringNamePackedStringListArgRetVariantScalar(getSettingWithOverrideAndCustomFeaturesBind, singleton, name, features)
    }

    /**
     * Sets the order of a configuration value (influences when saved to the config file).
     *
     * Generated from Godot docs: ProjectSettings.set_order
     */
    @JvmStatic
    fun setOrder(name: String, position: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setOrderBind, singleton, name, position)
    }

    /**
     * Returns the order of a configuration value (influences when saved to the config file).
     *
     * Generated from Godot docs: ProjectSettings.get_order
     */
    @JvmStatic
    fun getOrder(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(getOrderBind, singleton, name)
    }

    /**
     * Sets the specified setting's initial value. This is the value the setting reverts to. The
     * setting should already exist before calling this method. Note that project settings equal to
     * their default value are not saved, so your code needs to account for that.
     *
     * Generated from Godot docs: ProjectSettings.set_initial_value
     */
    @JvmStatic
    fun setInitialValue(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setInitialValueBind, singleton, name, value)
    }

    /**
     * Defines if the specified setting is considered basic or advanced. Basic settings will always be
     * shown in the project settings. Advanced settings will only be shown if the user enables the
     * "Advanced Settings" option.
     *
     * Generated from Godot docs: ProjectSettings.set_as_basic
     */
    @JvmStatic
    fun setAsBasic(name: String, basic: Boolean) {
        ObjectCalls.ptrcallWithStringAndBoolArg(setAsBasicBind, singleton, name, basic)
    }

    /**
     * Defines if the specified setting is considered internal. An internal setting won't show up in
     * the Project Settings dialog. This is mostly useful for addons that need to store their own
     * internal settings without exposing them directly to the user.
     *
     * Generated from Godot docs: ProjectSettings.set_as_internal
     */
    @JvmStatic
    fun setAsInternal(name: String, internalValue: Boolean) {
        ObjectCalls.ptrcallWithStringAndBoolArg(setAsInternalBind, singleton, name, internalValue)
    }

    /**
     * Adds a custom property info to a property. The dictionary must contain: - `"name"`: `String`
     * (the property's name) - `"type"`: `int` (see `Variant.Type`) - optionally `"hint"`: `int` (see
     * `PropertyHint`) and `"hint_string"`: `String`
     *
     * Generated from Godot docs: ProjectSettings.add_property_info
     */
    @JvmStatic
    fun addPropertyInfo(hint: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(addPropertyInfoBind, singleton, hint)
    }

    /**
     * Sets whether a setting requires restarting the editor to properly take effect. Note: This is
     * just a hint to display to the user that the editor must be restarted for changes to take effect.
     * Enabling `set_restart_if_changed` does not delay the setting being set when changed.
     *
     * Generated from Godot docs: ProjectSettings.set_restart_if_changed
     */
    @JvmStatic
    fun setRestartIfChanged(name: String, restart: Boolean) {
        ObjectCalls.ptrcallWithStringAndBoolArg(setRestartIfChangedBind, singleton, name, restart)
    }

    @JvmStatic
    /**
     * Clears the whole configuration (not recommended, may break things).
     *
     * Generated from Godot docs: ProjectSettings.clear
     */
    fun clear(name: String) {
        ObjectCalls.ptrcallWithStringArg(clearBind, singleton, name)
    }

    /**
     * Returns the localized path (starting with `res://`) corresponding to the absolute, native OS
     * `path`. See also `globalize_path`.
     *
     * Generated from Godot docs: ProjectSettings.localize_path
     */
    @JvmStatic
    fun localizePath(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(localizePathBind, singleton, path)
    }

    /**
     * Returns the absolute, native OS path corresponding to the localized `path` (starting with
     * `res://` or `user://`). The returned path will vary depending on the operating system and user
     * preferences. See File paths in Godot projects ($DOCS_URL/tutorials/io/data_paths.html) to see
     * what those paths convert to. See also `localize_path`. Note: `globalize_path` with `res://` will
     * not work in an exported project. Instead, prepend the executable's base directory to the path
     * when running from an exported project:
     *
     * Generated from Godot docs: ProjectSettings.globalize_path
     */
    @JvmStatic
    fun globalizePath(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(globalizePathBind, singleton, path)
    }

    @JvmStatic
    /**
     * Saves the configuration to the `project.godot` file. Note: This method is intended to be used by
     * editor plugins, as modified `ProjectSettings` can't be loaded back in the running app. If you
     * want to change project settings in exported projects, use `save_custom` to save an
     * `override.cfg` file.
     *
     * Generated from Godot docs: ProjectSettings.save
     */
    fun save(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(saveBind, singleton)
    }

    /**
     * Loads the contents of the .pck or .zip file specified by `pack` into the resource filesystem
     * (`res://`). Returns `true` on success. Note: If a file from `pack` shares the same path as a
     * file already in the resource filesystem, any attempts to load that file will use the file from
     * `pack` unless `replace_files` is set to `false`. Note: The optional `offset` parameter can be
     * used to specify the offset in bytes to the start of the resource pack. This is only supported
     * for .pck files. Note: `DirAccess` will not show changes made to the contents of `res://` after
     * calling this function.
     *
     * Generated from Godot docs: ProjectSettings.load_resource_pack
     */
    @JvmStatic
    fun loadResourcePack(pack: String, replaceFiles: Boolean = true, offset: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithStringBoolIntArgsRetBool(loadResourcePackBind, singleton, pack, replaceFiles, offset)
    }

    /**
     * Saves the configuration to a custom file. The file extension must be `.godot` (to save in
     * text-based `ConfigFile` format) or `.binary` (to save in binary format). You can also save an
     * `override.cfg` file, which is also text, but can be used in exported projects unlike other
     * formats.
     *
     * Generated from Godot docs: ProjectSettings.save_custom
     */
    @JvmStatic
    fun saveCustom(file: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveCustomBind, singleton, file)
    }

    /**
     * Gets an array of the settings which have been changed since the last save. Note that internally
     * `changed_settings` is cleared after a successful save, so generally the most appropriate place
     * to use this method is when processing `settings_changed`.
     *
     * Generated from Godot docs: ProjectSettings.get_changed_settings
     */
    @JvmStatic
    fun getChangedSettings(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getChangedSettingsBind, singleton)
    }

    /**
     * Checks if any settings with the prefix `setting_prefix` exist in the set of changed settings.
     * See also `get_changed_settings`.
     *
     * Generated from Godot docs: ProjectSettings.check_changed_settings_in_group
     */
    @JvmStatic
    fun checkChangedSettingsInGroup(settingPrefix: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(checkChangedSettingsInGroupBind, singleton, settingPrefix)
    }

    @JvmStatic
    fun getSettingString(name: String, defaultValue: String = ""): String =
        getSetting(name, defaultValue) as? String ?: defaultValue

    @JvmStatic
    fun getSettingBool(name: String, defaultValue: Boolean = false): Boolean =
        getSetting(name, defaultValue) as? Boolean ?: defaultValue

    @JvmStatic
    fun getSettingLong(name: String, defaultValue: Long = 0): Long =
        (getSetting(name, defaultValue) as? Number)?.toLong() ?: defaultValue

    @JvmStatic
    fun getSettingDouble(name: String, defaultValue: Double = 0.0): Double =
        (getSetting(name, defaultValue) as? Number)?.toDouble() ?: defaultValue

    @JvmStatic
    fun getSettingStringList(name: String, defaultValue: List<String> = emptyList()): List<String> =
        stringListOrDefault(getSetting(name, defaultValue), defaultValue)

    @JvmStatic
    fun getSettingDictionary(name: String, defaultValue: Map<String, Any?> = emptyMap()): Map<String, Any?> =
        dictionaryOrDefault(getSetting(name, defaultValue), defaultValue)

    @JvmStatic
    fun getSettingWithOverrideString(name: String, defaultValue: String = ""): String =
        getSettingWithOverride(name) as? String ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideBool(name: String, defaultValue: Boolean = false): Boolean =
        getSettingWithOverride(name) as? Boolean ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideLong(name: String, defaultValue: Long = 0): Long =
        (getSettingWithOverride(name) as? Number)?.toLong() ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideDouble(name: String, defaultValue: Double = 0.0): Double =
        (getSettingWithOverride(name) as? Number)?.toDouble() ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesString(
        name: String,
        customFeatures: List<String>,
        defaultValue: String = "",
    ): String =
        getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? String ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesBool(
        name: String,
        customFeatures: List<String>,
        defaultValue: Boolean = false,
    ): Boolean =
        getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? Boolean ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesLong(
        name: String,
        customFeatures: List<String>,
        defaultValue: Long = 0,
    ): Long =
        (getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? Number)?.toLong() ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesDouble(
        name: String,
        customFeatures: List<String>,
        defaultValue: Double = 0.0,
    ): Double =
        (getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? Number)?.toDouble() ?: defaultValue

    private fun stringListOrDefault(value: Any?, defaultValue: List<String>): List<String> =
        (value as? List<*>)?.mapNotNull { it as? String } ?: defaultValue

    private fun dictionaryOrDefault(value: Any?, defaultValue: Map<String, Any?>): Map<String, Any?> =
        (value as? Map<*, *>)?.entries?.associate { (key, mapValue) -> key.toString() to mapValue } ?: defaultValue

    object Signals {
        const val settingsChanged: String = "settings_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): ProjectSettings? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): ProjectSettings? =
        if (handle.address() == 0L) null else this

    private const val HAS_SETTING_HASH = 3927539163L
    private val hasSettingBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "has_setting", HAS_SETTING_HASH)
    }

    private const val SET_SETTING_HASH = 402577236L
    private val setSettingBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "set_setting", SET_SETTING_HASH)
    }

    private const val GET_SETTING_HASH = 223050753L
    private val getSettingBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "get_setting", GET_SETTING_HASH)
    }

    private const val GET_SETTING_WITH_OVERRIDE_HASH = 2760726917L
    private val getSettingWithOverrideBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "get_setting_with_override", GET_SETTING_WITH_OVERRIDE_HASH)
    }

    private const val GET_GLOBAL_CLASS_LIST_HASH = 2915620761L
    private val getGlobalClassListBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "get_global_class_list", GET_GLOBAL_CLASS_LIST_HASH)
    }

    private const val GET_SETTING_WITH_OVERRIDE_AND_CUSTOM_FEATURES_HASH = 2434817427L
    private val getSettingWithOverrideAndCustomFeaturesBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "get_setting_with_override_and_custom_features", GET_SETTING_WITH_OVERRIDE_AND_CUSTOM_FEATURES_HASH)
    }

    private const val SET_ORDER_HASH = 2956805083L
    private val setOrderBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "set_order", SET_ORDER_HASH)
    }

    private const val GET_ORDER_HASH = 1321353865L
    private val getOrderBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "get_order", GET_ORDER_HASH)
    }

    private const val SET_INITIAL_VALUE_HASH = 402577236L
    private val setInitialValueBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "set_initial_value", SET_INITIAL_VALUE_HASH)
    }

    private const val SET_AS_BASIC_HASH = 2678287736L
    private val setAsBasicBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "set_as_basic", SET_AS_BASIC_HASH)
    }

    private const val SET_AS_INTERNAL_HASH = 2678287736L
    private val setAsInternalBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "set_as_internal", SET_AS_INTERNAL_HASH)
    }

    private const val ADD_PROPERTY_INFO_HASH = 4155329257L
    private val addPropertyInfoBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "add_property_info", ADD_PROPERTY_INFO_HASH)
    }

    private const val SET_RESTART_IF_CHANGED_HASH = 2678287736L
    private val setRestartIfChangedBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "set_restart_if_changed", SET_RESTART_IF_CHANGED_HASH)
    }

    private const val CLEAR_HASH = 83702148L
    private val clearBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "clear", CLEAR_HASH)
    }

    private const val LOCALIZE_PATH_HASH = 3135753539L
    private val localizePathBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "localize_path", LOCALIZE_PATH_HASH)
    }

    private const val GLOBALIZE_PATH_HASH = 3135753539L
    private val globalizePathBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "globalize_path", GLOBALIZE_PATH_HASH)
    }

    private const val SAVE_HASH = 166280745L
    private val saveBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "save", SAVE_HASH)
    }

    private const val LOAD_RESOURCE_PACK_HASH = 708980503L
    private val loadResourcePackBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "load_resource_pack", LOAD_RESOURCE_PACK_HASH)
    }

    private const val SAVE_CUSTOM_HASH = 166001499L
    private val saveCustomBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "save_custom", SAVE_CUSTOM_HASH)
    }

    private const val GET_CHANGED_SETTINGS_HASH = 1139954409L
    private val getChangedSettingsBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "get_changed_settings", GET_CHANGED_SETTINGS_HASH)
    }

    private const val CHECK_CHANGED_SETTINGS_IN_GROUP_HASH = 3927539163L
    private val checkChangedSettingsInGroupBind by lazy {
        ObjectCalls.getMethodBind("ProjectSettings", "check_changed_settings_in_group", CHECK_CHANGED_SETTINGS_IN_GROUP_HASH)
    }
}

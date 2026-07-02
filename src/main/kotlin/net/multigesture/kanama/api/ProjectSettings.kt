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

    @JvmStatic
    fun hasSetting(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSettingBind, singleton, name)
    }

    @JvmStatic
    fun setSetting(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setSettingBind, singleton, name, value)
    }

    @JvmStatic
    fun getSetting(name: String, defaultValue: Any? = null): Any? {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetVariantScalar(getSettingBind, singleton, name, defaultValue)
    }

    @JvmStatic
    fun getSettingWithOverride(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getSettingWithOverrideBind, singleton, name)
    }

    @JvmStatic
    fun getGlobalClassList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getGlobalClassListBind, singleton)
    }

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeatures(name: String, features: List<String>): Any? {
        return ObjectCalls.ptrcallWithStringNamePackedStringListArgRetVariantScalar(getSettingWithOverrideAndCustomFeaturesBind, singleton, name, features)
    }

    @JvmStatic
    fun setOrder(name: String, position: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setOrderBind, singleton, name, position)
    }

    @JvmStatic
    fun getOrder(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(getOrderBind, singleton, name)
    }

    @JvmStatic
    fun setInitialValue(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setInitialValueBind, singleton, name, value)
    }

    @JvmStatic
    fun setAsBasic(name: String, basic: Boolean) {
        ObjectCalls.ptrcallWithStringAndBoolArg(setAsBasicBind, singleton, name, basic)
    }

    @JvmStatic
    fun setAsInternal(name: String, internalValue: Boolean) {
        ObjectCalls.ptrcallWithStringAndBoolArg(setAsInternalBind, singleton, name, internalValue)
    }

    @JvmStatic
    fun addPropertyInfo(hint: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(addPropertyInfoBind, singleton, hint)
    }

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

    @JvmStatic
    fun localizePath(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(localizePathBind, singleton, path)
    }

    @JvmStatic
    fun globalizePath(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(globalizePathBind, singleton, path)
    }

    @JvmStatic
    /**
     * Saves the configuration to the `project.godot` file. Note: This method is intended to be used by
     * editor plugins, as modified `ProjectSettings` can't be loaded back in the running app. If you
     * want to change project settings in exported projects, use `save_custom` to save `override.cfg`
     * file.
     *
     * Generated from Godot docs: ProjectSettings.save
     */
    fun save(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(saveBind, singleton)
    }

    @JvmStatic
    fun loadResourcePack(pack: String, replaceFiles: Boolean = true, offset: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithStringBoolIntArgsRetBool(loadResourcePackBind, singleton, pack, replaceFiles, offset)
    }

    @JvmStatic
    fun saveCustom(file: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveCustomBind, singleton, file)
    }

    @JvmStatic
    fun getChangedSettings(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getChangedSettingsBind, singleton)
    }

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

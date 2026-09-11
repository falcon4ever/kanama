package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorSettings (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorSettings waits on: ptrcallWithDictionaryArg, ptrcallWithStringAndVariantArg,
//   ptrcallWithStringNameVariantBoolArgs, ptrcallWithTwoStringAndVariantArg,
//   ptrcallWithTwoStringAndVariantArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the `value` of the setting specified by `name`. This is equivalent to using `Object.set` on
 * the EditorSettings instance.
 *
 * Generated from Godot docs: EditorSettings.set_setting
 */
fun EditorSettings.setSetting(name: String, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringAndVariantArg(setSettingBind, handle, name, value)
}

/**
 * Sets the initial value of the setting specified by `name` to `value`. This is used to provide a
 * value for the Revert button in the Editor Settings. If `update_current` is `true`, the setting
 * is reset to `value` as well.
 *
 * Generated from Godot docs: EditorSettings.set_initial_value
 */
fun EditorSettings.setInitialValue(name: String, value: Any?, updateCurrent: Boolean) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameVariantBoolArgs(setInitialValueBind, handle, name, value, updateCurrent)
}

/**
 * Adds a custom property info to a property. The dictionary must contain: - `name`: `String` (the
 * name of the property) - `type`: `int` (see `Variant.Type`) - optionally `hint`: `int` (see
 * `PropertyHint`) and `hint_string`: `String`
 *
 * Generated from Godot docs: EditorSettings.add_property_info
 */
fun EditorSettings.addPropertyInfo(info: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(addPropertyInfoBind, handle, info)
}

/**
 * Sets project-specific metadata with the `section`, `key` and `data` specified. This metadata is
 * stored outside the project folder and therefore won't be checked into version control. See also
 * `get_project_metadata`.
 *
 * Generated from Godot docs: EditorSettings.set_project_metadata
 */
fun EditorSettings.setProjectMetadata(section: String, key: String, data: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithTwoStringAndVariantArg(setProjectMetadataBind, handle, section, key, data)
}

/**
 * Returns project-specific metadata for the `section` and `key` specified. If the metadata doesn't
 * exist, `default` will be returned instead. See also `set_project_metadata`.
 *
 * Generated from Godot docs: EditorSettings.get_project_metadata
 */
fun EditorSettings.getProjectMetadata(section: String, key: String, default: Any? = null): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(getProjectMetadataBind, handle, section, key, default)
}

private const val SET_SETTING_HASH = 402577236L
private val setSettingBind by lazy {
    ObjectCalls.getMethodBind("EditorSettings", "set_setting", SET_SETTING_HASH)
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

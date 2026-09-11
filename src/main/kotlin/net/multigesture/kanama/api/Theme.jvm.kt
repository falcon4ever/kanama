package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Theme (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Theme waits on: ptrcallWithLongAndTwoStringNameAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates or changes the value of the theme property of `data_type` defined by `name` and
 * `theme_type`. Use `clear_theme_item` to remove the property. Fails if the `value` type is not
 * accepted by `data_type`. Note: This method is analogous to calling the corresponding data type
 * specific method, but can be used for more generalized logic.
 *
 * Generated from Godot docs: Theme.set_theme_item
 */
fun Theme.setThemeItem(dataType: Long, name: String, themeType: String, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithLongAndTwoStringNameAndVariantArg(setThemeItemBind, handle, dataType, name, themeType, value)
}

private const val SET_THEME_ITEM_HASH = 2492983623L
private val setThemeItemBind by lazy {
    ObjectCalls.getMethodBind("Theme", "set_theme_item", SET_THEME_ITEM_HASH)
}

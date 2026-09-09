package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TabContainer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TabContainer waits on: ptrcallWithIntAndVariantArg, ptrcallWithIntArgRetString,
//   ptrcallWithIntArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the title of the tab at index `tab_idx`. Tab titles default to the name of the indexed
 * child node, but this can be overridden with `set_tab_title`.
 *
 * Generated from Godot docs: TabContainer.get_tab_title
 */
fun TabContainer.getTabTitle(tabIdx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getTabTitleBind, handle, tabIdx)
}

/**
 * Returns the tooltip text of the tab at index `tab_idx`.
 *
 * Generated from Godot docs: TabContainer.get_tab_tooltip
 */
fun TabContainer.getTabTooltip(tabIdx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getTabTooltipBind, handle, tabIdx)
}

/**
 * Sets the metadata value for the tab at index `tab_idx`, which can be retrieved later using
 * `get_tab_metadata`.
 *
 * Generated from Godot docs: TabContainer.set_tab_metadata
 */
fun TabContainer.setTabMetadata(tabIdx: Int, metadata: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setTabMetadataBind, handle, tabIdx, metadata)
}

/**
 * Returns the metadata value set to the tab at index `tab_idx` using `set_tab_metadata`. If no
 * metadata was previously set, returns `null` by default.
 *
 * Generated from Godot docs: TabContainer.get_tab_metadata
 */
fun TabContainer.getTabMetadata(tabIdx: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getTabMetadataBind, handle, tabIdx)
}

private const val GET_TAB_TITLE_HASH = 844755477L
private val getTabTitleBind by lazy {
    ObjectCalls.getMethodBind("TabContainer", "get_tab_title", GET_TAB_TITLE_HASH)
}

private const val GET_TAB_TOOLTIP_HASH = 844755477L
private val getTabTooltipBind by lazy {
    ObjectCalls.getMethodBind("TabContainer", "get_tab_tooltip", GET_TAB_TOOLTIP_HASH)
}

private const val SET_TAB_METADATA_HASH = 2152698145L
private val setTabMetadataBind by lazy {
    ObjectCalls.getMethodBind("TabContainer", "set_tab_metadata", SET_TAB_METADATA_HASH)
}

private const val GET_TAB_METADATA_HASH = 4227898402L
private val getTabMetadataBind by lazy {
    ObjectCalls.getMethodBind("TabContainer", "get_tab_metadata", GET_TAB_METADATA_HASH)
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for MenuBar (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP MenuBar waits on: ptrcallWithIntArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns menu item title.
 *
 * Generated from Godot docs: MenuBar.get_menu_title
 */
fun MenuBar.getMenuTitle(menu: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getMenuTitleBind, handle, menu)
}

/**
 * Returns menu item tooltip.
 *
 * Generated from Godot docs: MenuBar.get_menu_tooltip
 */
fun MenuBar.getMenuTooltip(menu: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getMenuTooltipBind, handle, menu)
}

private const val GET_MENU_TITLE_HASH = 844755477L
private val getMenuTitleBind by lazy {
    ObjectCalls.getMethodBind("MenuBar", "get_menu_title", GET_MENU_TITLE_HASH)
}

private const val GET_MENU_TOOLTIP_HASH = 844755477L
private val getMenuTooltipBind by lazy {
    ObjectCalls.getMethodBind("MenuBar", "get_menu_tooltip", GET_MENU_TOOLTIP_HASH)
}

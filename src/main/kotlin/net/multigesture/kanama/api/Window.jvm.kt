package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for Window (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Window waits on: ptrcallWithObjectAndRect2iArg, ptrcallWithPackedVector2ListArg,
//   ptrcallWithRect2iArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * If set, defines the window's custom decoration area which will receive mouse input, even if
 * normal input to the window is blocked (such as when it has an exclusive child opened). See also
 * `nonclient_window_input`.
 *
 * Generated from Godot docs: Window.set_nonclient_area
 */
fun Window.setNonclientArea(area: Rect2i) {
    ObjectCalls.ptrcallWithRect2iArg(setNonclientAreaBind, handle, area)
}

/**
 * Sets a polygonal region of the window which accepts mouse events. Mouse events outside the
 * region will be passed through. Passing an empty array will disable passthrough support (all
 * mouse events will be intercepted by the window, which is the default behavior).
 *
 * Generated from Godot docs: Window.set_mouse_passthrough_polygon
 */
fun Window.setMousePassthroughPolygon(polygon: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setMousePassthroughPolygonBind, handle, polygon)
}

/**
 * Shows the `Window` and makes it transient (see `transient`). If `rect` is provided, it will be
 * set as the `Window`'s size. Fails if called on the main window. If
 * `ProjectSettings.display/window/subwindows/embed_subwindows` is `true` (single-window mode),
 * `rect`'s coordinates are global and relative to the main window's top-left corner (excluding
 * window decorations). If `rect`'s position coordinates are negative, the window will be located
 * outside the main window and may not be visible as a result. If
 * `ProjectSettings.display/window/subwindows/embed_subwindows` is `false` (multi-window mode),
 * `rect`'s coordinates are global and relative to the top-left corner of the leftmost screen. If
 * `rect`'s position coordinates are negative, the window will be placed at the top-left corner of
 * the screen. Note: `rect` must be in global coordinates if specified.
 *
 * Generated from Godot docs: Window.popup
 */
fun Window.popup(rect: Rect2i) {
    ObjectCalls.ptrcallWithRect2iArg(popupBind, handle, rect)
}

/**
 * Popups the `Window` with a position shifted by parent `Window`'s position. If the `Window` is
 * embedded, has the same effect as `popup`.
 *
 * Generated from Godot docs: Window.popup_on_parent
 */
fun Window.popupOnParent(parentRect: Rect2i) {
    ObjectCalls.ptrcallWithRect2iArg(popupOnParentBind, handle, parentRect)
}

/**
 * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
 * calls `Window.popup` on it. The dialog must have no current parent, otherwise the method fails.
 * See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
 *
 * Generated from Godot docs: Window.popup_exclusive
 */
fun Window.popupExclusive(fromNode: Node, rect: Rect2i) {
    ObjectCalls.ptrcallWithObjectAndRect2iArg(popupExclusiveBind, handle, fromNode.handle, rect)
}

/**
 * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
 * calls `Window.popup_on_parent` on it. The dialog must have no current parent, otherwise the
 * method fails. See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
 *
 * Generated from Godot docs: Window.popup_exclusive_on_parent
 */
fun Window.popupExclusiveOnParent(fromNode: Node, parentRect: Rect2i) {
    ObjectCalls.ptrcallWithObjectAndRect2iArg(popupExclusiveOnParentBind, handle, fromNode.handle, parentRect)
}

private const val SET_NONCLIENT_AREA_HASH = 1763793166L
private val setNonclientAreaBind by lazy {
    ObjectCalls.getMethodBind("Window", "set_nonclient_area", SET_NONCLIENT_AREA_HASH)
}

private const val SET_MOUSE_PASSTHROUGH_POLYGON_HASH = 1509147220L
private val setMousePassthroughPolygonBind by lazy {
    ObjectCalls.getMethodBind("Window", "set_mouse_passthrough_polygon", SET_MOUSE_PASSTHROUGH_POLYGON_HASH)
}

private const val POPUP_HASH = 1680304321L
private val popupBind by lazy {
    ObjectCalls.getMethodBind("Window", "popup", POPUP_HASH)
}

private const val POPUP_ON_PARENT_HASH = 1763793166L
private val popupOnParentBind by lazy {
    ObjectCalls.getMethodBind("Window", "popup_on_parent", POPUP_ON_PARENT_HASH)
}

private const val POPUP_EXCLUSIVE_HASH = 2134721627L
private val popupExclusiveBind by lazy {
    ObjectCalls.getMethodBind("Window", "popup_exclusive", POPUP_EXCLUSIVE_HASH)
}

private const val POPUP_EXCLUSIVE_ON_PARENT_HASH = 2344671043L
private val popupExclusiveOnParentBind by lazy {
    ObjectCalls.getMethodBind("Window", "popup_exclusive_on_parent", POPUP_EXCLUSIVE_ON_PARENT_HASH)
}

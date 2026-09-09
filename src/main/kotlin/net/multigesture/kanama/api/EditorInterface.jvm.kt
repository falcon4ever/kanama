package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i

// GENERATED desktop/Android companion for EditorInterface (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorInterface waits on: ptrcallWithCallableStringNameListArgs,
//   ptrcallWithCallableStringNameListObjectArgs,
//   ptrcallWithCallableStringNameTwoStringStringNameListArgs, ptrcallWithObjectAndRect2iArg,
//   ptrcallWithObjectCallablePackedInt32ListStringArgs,
//   ptrcallWithObjectListIntArgsRetTypedObjectList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns mesh previews rendered at the given size as an `Array` of `Texture2D`s.
 *
 * Generated from Godot docs: EditorInterface.make_mesh_previews
 */
fun EditorInterface.makeMeshPreviews(meshes: List<Mesh>, previewSize: Int): List<Texture2D> {
    return ObjectCalls.ptrcallWithObjectListIntArgsRetTypedObjectList(makeMeshPreviewsBind, editorInterfaceSingleton, meshes, previewSize, Texture2D::fromHandle)
}

/**
 * Pops up the `dialog` in the editor UI with `Window.popup_exclusive`. The dialog must have no
 * current parent, otherwise the method fails. See also `Window.set_unparent_when_invisible`.
 *
 * Generated from Godot docs: EditorInterface.popup_dialog
 */
fun EditorInterface.popupDialog(dialog: Window, rect: Rect2i) {
    ObjectCalls.ptrcallWithObjectAndRect2iArg(popupDialogBind, editorInterfaceSingleton, dialog.handle, rect)
}

/**
 * Pops up an editor dialog for selecting a `Node` from the edited scene. The `callback` must take
 * a single argument of type `NodePath`. It is called on the selected `NodePath` or the empty path
 * `^""` if the dialog is canceled. If `valid_types` is provided, the dialog will only show Nodes
 * that match one of the listed Node types. If `current_value` is provided, the Node will be
 * automatically selected in the tree, if it exists.
 *
 * Generated from Godot docs: EditorInterface.popup_node_selector
 */
fun EditorInterface.popupNodeSelector(callback: GodotCallable, validTypes: List<String>, currentValue: Node) {
    ObjectCalls.ptrcallWithCallableStringNameListObjectArgs(popupNodeSelectorBind, editorInterfaceSingleton, callback.target.handle, callback.method, validTypes, currentValue.handle)
}

/**
 * Pops up an editor dialog for selecting properties from `object`. The `callback` must take a
 * single argument of type `NodePath`. It is called on the selected property path (see
 * `NodePath.get_as_property_path`) or the empty path `^""` if the dialog is canceled. If
 * `type_filter` is provided, the dialog will only show properties that match one of the listed
 * `Variant.Type` values. If `current_value` is provided, the property will be selected
 * automatically in the property list, if it exists.
 *
 * Generated from Godot docs: EditorInterface.popup_property_selector
 */
fun EditorInterface.popupPropertySelector(objectValue: GodotObject, callback: GodotCallable, typeFilter: List<Int>, currentValue: String = "") {
    ObjectCalls.ptrcallWithObjectCallablePackedInt32ListStringArgs(popupPropertySelectorBind, editorInterfaceSingleton, objectValue.handle, callback.target.handle, callback.method, typeFilter, currentValue)
}

/**
 * Pops up an editor dialog for quick selecting a resource file. The `callback` must take a single
 * argument of type `String` which will contain the path of the selected resource or be empty if
 * the dialog is canceled. If `base_types` is provided, the dialog will only show resources that
 * match these types. Only types deriving from `Resource` are supported.
 *
 * Generated from Godot docs: EditorInterface.popup_quick_open
 */
fun EditorInterface.popupQuickOpen(callback: GodotCallable, baseTypes: List<String>) {
    ObjectCalls.ptrcallWithCallableStringNameListArgs(popupQuickOpenBind, editorInterfaceSingleton, callback.target.handle, callback.method, baseTypes)
}

/**
 * Pops up an editor dialog for creating an object. The `callback` must take a single argument of
 * type `String`, which will contain the type name of the selected object (or the script path of
 * the type, if the type is created from a script), or be an empty string if no item is selected.
 * The `base_type` specifies the base type of objects to display. For example, if you set this to
 * "Resource", all types derived from `Resource` will display in the create dialog. The
 * `current_type` will be passed in the search box of the create dialog, and the specified type can
 * be immediately selected when the dialog pops up. If the `current_type` is not derived from
 * `base_type`, there will be no result of the type in the dialog. The `dialog_title` allows you to
 * define a custom title for the dialog. This is useful if you want to accurately hint the usage of
 * the dialog. If the `dialog_title` is an empty string, the dialog will use "Create New 'Base
 * Type'" as the default title. The `type_blocklist` contains a list of type names, and the types
 * in the blocklist will be hidden from the create dialog. Note: Trying to list the base type in
 * the `type_blocklist` will hide all types derived from the base type from the create dialog.
 *
 * Generated from Godot docs: EditorInterface.popup_create_dialog
 */
fun EditorInterface.popupCreateDialog(callback: GodotCallable, baseType: String = "", currentType: String = "", dialogTitle: String = "", typeBlocklist: List<String>) {
    ObjectCalls.ptrcallWithCallableStringNameTwoStringStringNameListArgs(popupCreateDialogBind, editorInterfaceSingleton, callback.target.handle, callback.method, baseType, currentType, dialogTitle, typeBlocklist)
}

private val editorInterfaceSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("EditorInterface")
}

private const val MAKE_MESH_PREVIEWS_HASH = 878078554L
private val makeMeshPreviewsBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "make_mesh_previews", MAKE_MESH_PREVIEWS_HASH)
}

private const val POPUP_DIALOG_HASH = 2015770942L
private val popupDialogBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "popup_dialog", POPUP_DIALOG_HASH)
}

private const val POPUP_NODE_SELECTOR_HASH = 2444591477L
private val popupNodeSelectorBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "popup_node_selector", POPUP_NODE_SELECTOR_HASH)
}

private const val POPUP_PROPERTY_SELECTOR_HASH = 2955609011L
private val popupPropertySelectorBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "popup_property_selector", POPUP_PROPERTY_SELECTOR_HASH)
}

private const val POPUP_QUICK_OPEN_HASH = 2271411043L
private val popupQuickOpenBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "popup_quick_open", POPUP_QUICK_OPEN_HASH)
}

private const val POPUP_CREATE_DIALOG_HASH = 495277124L
private val popupCreateDialogBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "popup_create_dialog", POPUP_CREATE_DIALOG_HASH)
}

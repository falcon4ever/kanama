package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorInterface (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorInterface waits on: ptrcallWithObjectListIntArgsRetTypedObjectList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns mesh previews rendered at the given size as an `Array` of `Texture2D`s.
 *
 * Generated from Godot docs: EditorInterface.make_mesh_previews
 */
fun EditorInterface.makeMeshPreviews(meshes: List<Mesh>, previewSize: Int): List<Texture2D> {
    return ObjectCalls.ptrcallWithObjectListIntArgsRetTypedObjectList(makeMeshPreviewsBind, editorInterfaceSingleton, meshes, previewSize, Texture2D::fromHandle)
}

private val editorInterfaceSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("EditorInterface")
}

private const val MAKE_MESH_PREVIEWS_HASH = 878078554L
private val makeMeshPreviewsBind by lazy {
    ObjectCalls.getMethodBind("EditorInterface", "make_mesh_previews", MAKE_MESH_PREVIEWS_HASH)
}

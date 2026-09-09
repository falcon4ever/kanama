package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorResourcePreview (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorResourcePreview waits on: ptrcallWithStringObjectStringNameVariantArgs,
//   ptrcallWithTwoObjectStringNameVariantArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Queue a resource file located at `path` for preview. Once the preview is ready, the `receiver`'s
 * `receiver_func` will be called. The `receiver_func` must take the following four arguments:
 * `String` path, `Texture2D` preview, `Texture2D` thumbnail_preview, `Variant` userdata.
 * `userdata` can be anything, and will be returned when `receiver_func` is called. Note: If it was
 * not possible to create the preview the `receiver_func` will still be called, but the preview
 * will be `null`.
 *
 * Generated from Godot docs: EditorResourcePreview.queue_resource_preview
 */
fun EditorResourcePreview.queueResourcePreview(path: String, receiver: GodotObject, receiverFunc: String, userdata: Any?) {
    ObjectCalls.ptrcallWithStringObjectStringNameVariantArgs(queueResourcePreviewBind, handle, path, receiver.handle, receiverFunc, userdata)
}

/**
 * Queue the `resource` being edited for preview. Once the preview is ready, the `receiver`'s
 * `receiver_func` will be called. The `receiver_func` must take the following four arguments:
 * `String` path, `Texture2D` preview, `Texture2D` thumbnail_preview, `Variant` userdata.
 * `userdata` can be anything, and will be returned when `receiver_func` is called. Note: If it was
 * not possible to create the preview the `receiver_func` will still be called, but the preview
 * will be `null`.
 *
 * Generated from Godot docs: EditorResourcePreview.queue_edited_resource_preview
 */
fun EditorResourcePreview.queueEditedResourcePreview(resource: Resource?, receiver: GodotObject, receiverFunc: String, userdata: Any?) {
    ObjectCalls.ptrcallWithTwoObjectStringNameVariantArgs(queueEditedResourcePreviewBind, handle, resource?.requireOpenHandle() ?: MemorySegment.NULL, receiver.handle, receiverFunc, userdata)
}

private const val QUEUE_RESOURCE_PREVIEW_HASH = 233177534L
private val queueResourcePreviewBind by lazy {
    ObjectCalls.getMethodBind("EditorResourcePreview", "queue_resource_preview", QUEUE_RESOURCE_PREVIEW_HASH)
}

private const val QUEUE_EDITED_RESOURCE_PREVIEW_HASH = 1608376650L
private val queueEditedResourcePreviewBind by lazy {
    ObjectCalls.getMethodBind("EditorResourcePreview", "queue_edited_resource_preview", QUEUE_EDITED_RESOURCE_PREVIEW_HASH)
}

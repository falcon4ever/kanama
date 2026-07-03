package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node used to generate previews of resources or files.
 *
 * Generated from Godot docs: EditorResourcePreview
 */
class EditorResourcePreview(handle: MemorySegment) : Node(handle) {
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
    fun queueResourcePreview(path: String, receiver: GodotObject, receiverFunc: String, userdata: Any?) {
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
    fun queueEditedResourcePreview(resource: Resource?, receiver: GodotObject, receiverFunc: String, userdata: Any?) {
        ObjectCalls.ptrcallWithTwoObjectStringNameVariantArgs(queueEditedResourcePreviewBind, handle, resource?.requireOpenHandle() ?: MemorySegment.NULL, receiver.handle, receiverFunc, userdata)
    }

    /**
     * Create an own, custom preview generator.
     *
     * Generated from Godot docs: EditorResourcePreview.add_preview_generator
     */
    fun addPreviewGenerator(generator: EditorResourcePreviewGenerator?) {
        ObjectCalls.ptrcallWithObjectArgs(addPreviewGeneratorBind, handle, listOf(generator?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes a custom preview generator.
     *
     * Generated from Godot docs: EditorResourcePreview.remove_preview_generator
     */
    fun removePreviewGenerator(generator: EditorResourcePreviewGenerator?) {
        ObjectCalls.ptrcallWithObjectArgs(removePreviewGeneratorBind, handle, listOf(generator?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Check if the resource changed, if so, it will be invalidated and the corresponding signal
     * emitted.
     *
     * Generated from Godot docs: EditorResourcePreview.check_for_invalidation
     */
    fun checkForInvalidation(path: String) {
        ObjectCalls.ptrcallWithStringArg(checkForInvalidationBind, handle, path)
    }

    object Signals {
        const val previewInvalidated: String = "preview_invalidated"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorResourcePreview? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorResourcePreview? =
            if (handle.address() == 0L) null else EditorResourcePreview(handle)

        private const val QUEUE_RESOURCE_PREVIEW_HASH = 233177534L
        private val queueResourcePreviewBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePreview", "queue_resource_preview", QUEUE_RESOURCE_PREVIEW_HASH)
        }

        private const val QUEUE_EDITED_RESOURCE_PREVIEW_HASH = 1608376650L
        private val queueEditedResourcePreviewBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePreview", "queue_edited_resource_preview", QUEUE_EDITED_RESOURCE_PREVIEW_HASH)
        }

        private const val ADD_PREVIEW_GENERATOR_HASH = 332288124L
        private val addPreviewGeneratorBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePreview", "add_preview_generator", ADD_PREVIEW_GENERATOR_HASH)
        }

        private const val REMOVE_PREVIEW_GENERATOR_HASH = 332288124L
        private val removePreviewGeneratorBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePreview", "remove_preview_generator", REMOVE_PREVIEW_GENERATOR_HASH)
        }

        private const val CHECK_FOR_INVALIDATION_HASH = 83702148L
        private val checkForInvalidationBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePreview", "check_for_invalidation", CHECK_FOR_INVALIDATION_HASH)
        }
    }
}

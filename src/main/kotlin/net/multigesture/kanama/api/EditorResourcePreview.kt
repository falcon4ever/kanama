package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node used to generate previews of resources or files.
 *
 * Generated from Godot docs: EditorResourcePreview
 */
class EditorResourcePreview(handle: MemorySegment) : Node(handle) {
    fun queueResourcePreview(path: String, receiver: GodotObject, receiverFunc: String, userdata: Any?) {
        ObjectCalls.ptrcallWithStringObjectStringNameVariantArgs(queueResourcePreviewBind, handle, path, receiver.handle, receiverFunc, userdata)
    }

    fun queueEditedResourcePreview(resource: Resource?, receiver: GodotObject, receiverFunc: String, userdata: Any?) {
        ObjectCalls.ptrcallWithTwoObjectStringNameVariantArgs(queueEditedResourcePreviewBind, handle, resource?.requireOpenHandle() ?: MemorySegment.NULL, receiver.handle, receiverFunc, userdata)
    }

    fun addPreviewGenerator(generator: EditorResourcePreviewGenerator?) {
        ObjectCalls.ptrcallWithObjectArgs(addPreviewGeneratorBind, handle, listOf(generator?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removePreviewGenerator(generator: EditorResourcePreviewGenerator?) {
        ObjectCalls.ptrcallWithObjectArgs(removePreviewGeneratorBind, handle, listOf(generator?.requireOpenHandle() ?: MemorySegment.NULL))
    }

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

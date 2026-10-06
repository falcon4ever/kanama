package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node used to generate previews of resources or files.
 *
 * Generated from Godot docs: EditorResourcePreview
 */
class EditorResourcePreview(handle: GodotHandle) : Node(handle) {
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
        ObjectCalls.ptrcallWithStringObjectStringNameVariantArgs(Binds.queueResourcePreviewBind, segment, path, receiver.segment, receiverFunc, userdata)
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
        ObjectCalls.ptrcallWithTwoObjectStringNameVariantArgs(Binds.queueEditedResourcePreviewBind, segment, resource?.requireOpenHandle() ?: NULL_SEGMENT, receiver.segment, receiverFunc, userdata)
    }

    /**
     * Create an own, custom preview generator.
     *
     * Generated from Godot docs: EditorResourcePreview.add_preview_generator
     */
    fun addPreviewGenerator(generator: EditorResourcePreviewGenerator?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addPreviewGeneratorBind, segment, listOf(generator?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes a custom preview generator.
     *
     * Generated from Godot docs: EditorResourcePreview.remove_preview_generator
     */
    fun removePreviewGenerator(generator: EditorResourcePreviewGenerator?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removePreviewGeneratorBind, segment, listOf(generator?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Check if the resource changed, if so, it will be invalidated and the corresponding signal
     * emitted.
     *
     * Generated from Godot docs: EditorResourcePreview.check_for_invalidation
     */
    fun checkForInvalidation(path: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.checkForInvalidationBind, segment, path)
    }

    /** Signal `preview_invalidated(path: String)`; see [TypedSignal]. */
    val previewInvalidated: Signal1<String>
        @JvmName("previewInvalidatedTypedSignal")
        get() = Signal1(this, "preview_invalidated", SignalArgType.STRING)

    object Signals {
        const val previewInvalidated: String = "preview_invalidated"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorResourcePreview? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorResourcePreview? =
            if (handle.address() == 0L) null else EditorResourcePreview(GodotHandle(handle))
    }

    private object Binds {
        private const val QUEUE_RESOURCE_PREVIEW_HASH = 233177534L
        @JvmField
        val queueResourcePreviewBind =
            ObjectCalls.getMethodBind("EditorResourcePreview", "queue_resource_preview", QUEUE_RESOURCE_PREVIEW_HASH)

        private const val QUEUE_EDITED_RESOURCE_PREVIEW_HASH = 1608376650L
        @JvmField
        val queueEditedResourcePreviewBind =
            ObjectCalls.getMethodBind("EditorResourcePreview", "queue_edited_resource_preview", QUEUE_EDITED_RESOURCE_PREVIEW_HASH)

        private const val ADD_PREVIEW_GENERATOR_HASH = 332288124L
        @JvmField
        val addPreviewGeneratorBind =
            ObjectCalls.getMethodBind("EditorResourcePreview", "add_preview_generator", ADD_PREVIEW_GENERATOR_HASH)

        private const val REMOVE_PREVIEW_GENERATOR_HASH = 332288124L
        @JvmField
        val removePreviewGeneratorBind =
            ObjectCalls.getMethodBind("EditorResourcePreview", "remove_preview_generator", REMOVE_PREVIEW_GENERATOR_HASH)

        private const val CHECK_FOR_INVALIDATION_HASH = 83702148L
        @JvmField
        val checkForInvalidationBind =
            ObjectCalls.getMethodBind("EditorResourcePreview", "check_for_invalidation", CHECK_FOR_INVALIDATION_HASH)
    }
}

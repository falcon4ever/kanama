package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A node used to generate previews of resources or files.
 *
 * Generated from Godot docs: EditorResourcePreview
 */
class EditorResourcePreview(handle: MemorySegment) : Node(handle) {
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

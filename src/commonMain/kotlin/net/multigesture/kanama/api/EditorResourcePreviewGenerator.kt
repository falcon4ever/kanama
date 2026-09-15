package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Custom generator of previews.
 *
 * Generated from Godot docs: EditorResourcePreviewGenerator
 */
class EditorResourcePreviewGenerator(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Call from within `_generate` to request the rendering server draw to the `viewport`.
     *
     * Generated from Godot docs: EditorResourcePreviewGenerator.request_draw_and_wait
     */
    fun requestDrawAndWait(viewport: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(requestDrawAndWaitBind, segment, viewport)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorResourcePreviewGenerator? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorResourcePreviewGenerator? =
            if (handle.address() == 0L) null else EditorResourcePreviewGenerator(GodotHandle(handle))

        private const val REQUEST_DRAW_AND_WAIT_HASH = 145472570L
        private val requestDrawAndWaitBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePreviewGenerator", "request_draw_and_wait", REQUEST_DRAW_AND_WAIT_HASH)
        }
    }
}

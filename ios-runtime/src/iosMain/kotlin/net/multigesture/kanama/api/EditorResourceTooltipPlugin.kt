package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorResourceTooltipPlugin
 */
class EditorResourceTooltipPlugin(handle: MemorySegment) : RefCounted(handle) {
    fun requestThumbnail(path: String, control: TextureRect) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndObjectArg(requestThumbnailBind, handle, path, control.handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): EditorResourceTooltipPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorResourceTooltipPlugin? =
            if (handle.address() == 0L) null else EditorResourceTooltipPlugin(handle)

        private const val REQUEST_THUMBNAIL_HASH = 3245519720L
        private val requestThumbnailBind by lazy {
            ObjectCalls.getMethodBind("EditorResourceTooltipPlugin", "request_thumbnail", REQUEST_THUMBNAIL_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A plugin that advanced tooltip for its handled resource type.
 *
 * Generated from Godot docs: EditorResourceTooltipPlugin
 */
class EditorResourceTooltipPlugin(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Requests a thumbnail for the given `TextureRect`. The thumbnail is created asynchronously by
     * `EditorResourcePreview` and automatically set when available.
     *
     * Generated from Godot docs: EditorResourceTooltipPlugin.request_thumbnail
     */
    fun requestThumbnail(path: String, control: TextureRect) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndObjectArg(Binds.requestThumbnailBind, segment, path, control.segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorResourceTooltipPlugin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorResourceTooltipPlugin? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorResourceTooltipPlugin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorResourceTooltipPlugin? =
            if (handle.address() == 0L) null else EditorResourceTooltipPlugin(GodotHandle(handle))
    }

    private object Binds {
        private const val REQUEST_THUMBNAIL_HASH = 3245519720L
        @JvmField
        val requestThumbnailBind =
            ObjectCalls.getMethodBind("EditorResourceTooltipPlugin", "request_thumbnail", REQUEST_THUMBNAIL_HASH)
    }
}

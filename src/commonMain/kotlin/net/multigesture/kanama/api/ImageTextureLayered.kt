package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for texture types which contain the data of multiple `ImageTexture`s. Each image is
 * of the same size and format.
 *
 * Generated from Godot docs: ImageTextureLayered
 */
open class ImageTextureLayered(handle: MemorySegment) : TextureLayered(handle) {
    /**
     * Replaces the existing `Image` data at the given `layer` with this new image. The given `Image`
     * must have the same width, height, image format, and mipmapping flag as the rest of the
     * referenced images. If the image format is unsupported, it will be decompressed and converted to
     * a similar and supported `Image.Format`. The update is immediate: it's synchronized with drawing.
     *
     * Generated from Godot docs: ImageTextureLayered.update_layer
     */
    fun updateLayer(image: Image?, layer: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndIntArg(updateLayerBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, layer)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImageTextureLayered? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageTextureLayered? =
            if (handle.address() == 0L) null else ImageTextureLayered(handle)

        private const val UPDATE_LAYER_HASH = 3331733361L
        private val updateLayerBind by lazy {
            ObjectCalls.getMethodBind("ImageTextureLayered", "update_layer", UPDATE_LAYER_HASH)
        }
    }
}

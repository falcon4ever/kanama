package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for texture types which contain the data of multiple `ImageTexture`s. Each image is
 * of the same size and format.
 *
 * Generated from Godot docs: ImageTextureLayered
 */
open class ImageTextureLayered(handle: MemorySegment) : TextureLayered(handle) {
    fun createFromImages(images: List<Image>): Long {
        return ObjectCalls.ptrcallWithObjectListArgRetLong(createFromImagesBind, handle, images)
    }

    fun updateLayer(image: Image?, layer: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(updateLayerBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, layer)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImageTextureLayered? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageTextureLayered? =
            if (handle.address() == 0L) null else ImageTextureLayered(handle)

        private const val CREATE_FROM_IMAGES_HASH = 2785773503L
        private val createFromImagesBind by lazy {
            ObjectCalls.getMethodBind("ImageTextureLayered", "create_from_images", CREATE_FROM_IMAGES_HASH)
        }

        private const val UPDATE_LAYER_HASH = 3331733361L
        private val updateLayerBind by lazy {
            ObjectCalls.getMethodBind("ImageTextureLayered", "update_layer", UPDATE_LAYER_HASH)
        }
    }
}

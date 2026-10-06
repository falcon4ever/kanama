package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for texture types which contain the data of multiple `ImageTexture`s. Each image is
 * of the same size and format.
 *
 * Generated from Godot docs: ImageTextureLayered
 */
open class ImageTextureLayered(handle: GodotHandle) : TextureLayered(handle) {
    /**
     * Creates an `ImageTextureLayered` from an array of `Image`s. See `Image.create` for the expected
     * data format. The first image decides the width, height, image format and mipmapping setting. The
     * other images must have the same width, height, image format and mipmapping setting. Each `Image`
     * represents one `layer`.
     *
     * Generated from Godot docs: ImageTextureLayered.create_from_images
     */
    fun createFromImages(images: List<Image>): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectListArgRetLong(Binds.createFromImagesBind, segment, images))
    }

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
        ObjectCalls.ptrcallWithObjectAndIntArg(Binds.updateLayerBind, segment, image?.requireOpenHandle() ?: NULL_SEGMENT, layer)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImageTextureLayered? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ImageTextureLayered? =
            if (handle.address() == 0L) null else RefCounted.owned(ImageTextureLayered(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ImageTextureLayered? =
            if (handle.address() == 0L) null else ImageTextureLayered(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_FROM_IMAGES_HASH = 2785773503L
        @JvmField
        val createFromImagesBind =
            ObjectCalls.getMethodBind("ImageTextureLayered", "create_from_images", CREATE_FROM_IMAGES_HASH)

        private const val UPDATE_LAYER_HASH = 3331733361L
        @JvmField
        val updateLayerBind =
            ObjectCalls.getMethodBind("ImageTextureLayered", "update_layer", UPDATE_LAYER_HASH)
    }
}

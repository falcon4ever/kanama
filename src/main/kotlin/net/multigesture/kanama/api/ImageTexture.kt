package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

/**
 * A `Texture2D` based on an `Image`.
 *
 * Generated from Godot docs: ImageTexture
 */
class ImageTexture(handle: MemorySegment) : Texture2D(handle) {
    /**
     * Replaces the texture's data with a new `Image`. This will re-allocate new memory for the
     * texture. If you want to update the image, but don't need to change its parameters (format,
     * size), use `update` instead for better performance.
     *
     * Generated from Godot docs: ImageTexture.set_image
     */
    fun setImage(image: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setImageBind, handle, listOf(image?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Replaces the texture's data with a new `Image`. Note: The texture has to be created using
     * `create_from_image` or initialized first with the `set_image` method before it can be updated.
     * The new image dimensions, format, and mipmaps configuration should match the existing texture's
     * image configuration. Use this method over `set_image` if you need to update the texture
     * frequently, which is faster than allocating additional memory for a new texture each time.
     *
     * Generated from Godot docs: ImageTexture.update
     */
    fun update(image: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(updateBind, handle, listOf(image?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Resizes the texture to the specified dimensions.
     *
     * Generated from Godot docs: ImageTexture.set_size_override
     */
    fun setSizeOverride(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeOverrideBind, handle, size)
    }

    companion object {
        /**
         * Creates a new `ImageTexture` and initializes it by allocating and setting the data from an
         * `Image`.
         *
         * Generated from Godot docs: ImageTexture.create_from_image
         */
        fun createFromImage(image: Image?): ImageTexture? {
            return ImageTexture.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(createFromImageBind, MemorySegment.NULL, image?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImageTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageTexture? =
            if (handle.address() == 0L) null else ImageTexture(handle)

        private const val CREATE_FROM_IMAGE_HASH = 2775144163L
        private val createFromImageBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "create_from_image", CREATE_FROM_IMAGE_HASH)
        }

        private const val SET_IMAGE_HASH = 532598488L
        private val setImageBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "set_image", SET_IMAGE_HASH)
        }

        private const val UPDATE_HASH = 532598488L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "update", UPDATE_HASH)
        }

        private const val SET_SIZE_OVERRIDE_HASH = 1130785943L
        private val setSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "set_size_override", SET_SIZE_OVERRIDE_HASH)
        }
    }
}

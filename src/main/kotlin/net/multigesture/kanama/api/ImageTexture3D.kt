package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Texture with 3 dimensions.
 *
 * Generated from Godot docs: ImageTexture3D
 */
class ImageTexture3D(handle: MemorySegment) : Texture3D(handle) {
    /**
     * Creates the `ImageTexture3D` with specified `format`, `width`, `height`, and `depth`. If
     * `use_mipmaps` is `true`, generates mipmaps for the `ImageTexture3D`.
     *
     * Generated from Godot docs: ImageTexture3D.create
     */
    fun create(format: Long, width: Int, height: Int, depth: Int, useMipmaps: Boolean, data: List<Image>): Long {
        return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetLong(createBind, handle, format, width, height, depth, useMipmaps, data)
    }

    /**
     * Replaces the texture's existing data with the layers specified in `data`. The size of `data`
     * must match the parameters that were used for `create`. In other words, the texture cannot be
     * resized or have its format changed by calling `update`.
     *
     * Generated from Godot docs: ImageTexture3D.update
     */
    fun update(data: List<Image>) {
        ObjectCalls.ptrcallWithObjectListArg(updateBind, handle, data)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImageTexture3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageTexture3D? =
            if (handle.address() == 0L) null else ImageTexture3D(handle)

        private const val CREATE_HASH = 1130379827L
        private val createBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture3D", "create", CREATE_HASH)
        }

        private const val UPDATE_HASH = 381264803L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture3D", "update", UPDATE_HASH)
        }
    }
}

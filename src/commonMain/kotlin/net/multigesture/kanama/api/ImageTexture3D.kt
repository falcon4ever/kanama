package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Texture with 3 dimensions.
 *
 * Generated from Godot docs: ImageTexture3D
 */
class ImageTexture3D(handle: GodotHandle) : Texture3D(handle) {
    /**
     * Creates the `ImageTexture3D` with specified `format`, `width`, `height`, and `depth`. If
     * `use_mipmaps` is `true`, generates mipmaps for the `ImageTexture3D`.
     *
     * Generated from Godot docs: ImageTexture3D.create
     */
    fun create(format: Long, width: Int, height: Int, depth: Int, useMipmaps: Boolean, data: List<Image>): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetLong(createBind, segment, format, width, height, depth, useMipmaps, data)
    }

    /**
     * Replaces the texture's existing data with the layers specified in `data`. The size of `data`
     * must match the parameters that were used for `create`. In other words, the texture cannot be
     * resized or have its format changed by calling `update`.
     *
     * Generated from Godot docs: ImageTexture3D.update
     */
    fun update(data: List<Image>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(updateBind, segment, data)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImageTexture3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ImageTexture3D? =
            if (handle.address() == 0L) null else ImageTexture3D(GodotHandle(handle))

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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ImageTextureLayered
 */
open class ImageTextureLayered(handle: MemorySegment) : TextureLayered(handle) {
    fun updateLayer(image: Image?, layer: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndIntArg(updateLayerBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, layer)
    }

    companion object {
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

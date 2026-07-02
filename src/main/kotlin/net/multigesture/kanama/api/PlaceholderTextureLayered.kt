package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

/**
 * Placeholder class for a 2-dimensional texture array.
 *
 * Generated from Godot docs: PlaceholderTextureLayered
 */
open class PlaceholderTextureLayered(handle: MemorySegment) : TextureLayered(handle) {
    var size: Vector2i
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun setLayers(layers: Int) {
        ObjectCalls.ptrcallWithIntArg(setLayersBind, handle, layers)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderTextureLayered? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderTextureLayered? =
            if (handle.address() == 0L) null else PlaceholderTextureLayered(handle)

        private const val SET_SIZE_HASH = 1130785943L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderTextureLayered", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderTextureLayered", "get_size", GET_SIZE_HASH)
        }

        private const val SET_LAYERS_HASH = 1286410249L
        private val setLayersBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderTextureLayered", "set_layers", SET_LAYERS_HASH)
        }
    }
}

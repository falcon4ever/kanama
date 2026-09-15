package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2i

/**
 * Placeholder class for a 2-dimensional texture array.
 *
 * Generated from Godot docs: PlaceholderTextureLayered
 */
open class PlaceholderTextureLayered(handle: GodotHandle) : TextureLayered(handle) {
    var size: Vector2i
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    /**
     * The size of each texture layer (in pixels).
     *
     * Generated from Godot docs: PlaceholderTextureLayered.set_size
     */
    fun setSize(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, segment, size)
    }

    /**
     * The size of each texture layer (in pixels).
     *
     * Generated from Godot docs: PlaceholderTextureLayered.get_size
     */
    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, segment)
    }

    /**
     * The number of layers in the texture array.
     *
     * Generated from Godot docs: PlaceholderTextureLayered.set_layers
     */
    fun setLayers(layers: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setLayersBind, segment, layers)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderTextureLayered? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PlaceholderTextureLayered? =
            if (handle.address() == 0L) null else PlaceholderTextureLayered(GodotHandle(handle))

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

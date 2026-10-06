package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
        ObjectCalls.ptrcallWithVector2iArg(Binds.setSizeBind, segment, size)
    }

    /**
     * The size of each texture layer (in pixels).
     *
     * Generated from Godot docs: PlaceholderTextureLayered.get_size
     */
    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getSizeBind, segment)
    }

    /**
     * The number of layers in the texture array.
     *
     * Generated from Godot docs: PlaceholderTextureLayered.set_layers
     */
    fun setLayers(layers: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setLayersBind, segment, layers)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderTextureLayered? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaceholderTextureLayered? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaceholderTextureLayered(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaceholderTextureLayered? =
            if (handle.address() == 0L) null else PlaceholderTextureLayered(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 1130785943L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("PlaceholderTextureLayered", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3690982128L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("PlaceholderTextureLayered", "get_size", GET_SIZE_HASH)

        private const val SET_LAYERS_HASH = 1286410249L
        @JvmField
        val setLayersBind =
            ObjectCalls.getMethodBind("PlaceholderTextureLayered", "set_layers", SET_LAYERS_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for texture types which contain the data of multiple `Image`s. Each image is of the
 * same size and format.
 *
 * Generated from Godot docs: TextureLayered
 */
open class TextureLayered(handle: MemorySegment) : Texture(handle) {
    /**
     * Returns the current format being used by this texture.
     *
     * Generated from Godot docs: TextureLayered.get_format
     */
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    /**
     * Returns the `TextureLayered`'s type. The type determines how the data is accessed, with cubemaps
     * having special types.
     *
     * Generated from Godot docs: TextureLayered.get_layered_type
     */
    fun getLayeredType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLayeredTypeBind, handle)
    }

    /**
     * Returns the width of the texture in pixels. Width is typically represented by the X axis.
     *
     * Generated from Godot docs: TextureLayered.get_width
     */
    fun getWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWidthBind, handle)
    }

    /**
     * Returns the height of the texture in pixels. Height is typically represented by the Y axis.
     *
     * Generated from Godot docs: TextureLayered.get_height
     */
    fun getHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, handle)
    }

    /**
     * Returns the number of referenced `Image`s.
     *
     * Generated from Godot docs: TextureLayered.get_layers
     */
    fun getLayers(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLayersBind, handle)
    }

    /**
     * Returns `true` if the layers have generated mipmaps.
     *
     * Generated from Godot docs: TextureLayered.has_mipmaps
     */
    fun hasMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasMipmapsBind, handle)
    }

    /**
     * Returns an `Image` resource with the data from specified `layer`.
     *
     * Generated from Godot docs: TextureLayered.get_layer_data
     */
    fun getLayerData(layer: Int): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getLayerDataBind, handle, layer))
    }

    companion object {
        const val LAYERED_TYPE_2D_ARRAY: Long = 0L
        const val LAYERED_TYPE_CUBEMAP: Long = 1L
        const val LAYERED_TYPE_CUBEMAP_ARRAY: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextureLayered? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextureLayered? =
            if (handle.address() == 0L) null else TextureLayered(handle)

        private const val GET_FORMAT_HASH = 3847873762L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "get_format", GET_FORMAT_HASH)
        }

        private const val GET_LAYERED_TYPE_HASH = 518123893L
        private val getLayeredTypeBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "get_layered_type", GET_LAYERED_TYPE_HASH)
        }

        private const val GET_WIDTH_HASH = 3905245786L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "get_width", GET_WIDTH_HASH)
        }

        private const val GET_HEIGHT_HASH = 3905245786L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "get_height", GET_HEIGHT_HASH)
        }

        private const val GET_LAYERS_HASH = 3905245786L
        private val getLayersBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "get_layers", GET_LAYERS_HASH)
        }

        private const val HAS_MIPMAPS_HASH = 36873697L
        private val hasMipmapsBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "has_mipmaps", HAS_MIPMAPS_HASH)
        }

        private const val GET_LAYER_DATA_HASH = 3655284255L
        private val getLayerDataBind by lazy {
            ObjectCalls.getMethodBind("TextureLayered", "get_layer_data", GET_LAYER_DATA_HASH)
        }
    }
}

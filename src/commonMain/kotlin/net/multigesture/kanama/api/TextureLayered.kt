package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for texture types which contain the data of multiple `Image`s. Each image is of the
 * same size and format.
 *
 * Generated from Godot docs: TextureLayered
 */
open class TextureLayered(handle: GodotHandle) : Texture(handle) {
    /**
     * Returns the current format being used by this texture.
     *
     * Generated from Godot docs: TextureLayered.get_format
     */
    fun getFormat(): Image.Format {
        checkOpen()
        return Image.Format(ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, segment))
    }

    /**
     * Returns the `TextureLayered`'s type. The type determines how the data is accessed, with cubemaps
     * having special types.
     *
     * Generated from Godot docs: TextureLayered.get_layered_type
     */
    fun getLayeredType(): TextureLayered.LayeredType {
        checkOpen()
        return TextureLayered.LayeredType(ObjectCalls.ptrcallNoArgsRetLong(getLayeredTypeBind, segment))
    }

    /**
     * Returns the width of the texture in pixels. Width is typically represented by the X axis.
     *
     * Generated from Godot docs: TextureLayered.get_width
     */
    fun getWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getWidthBind, segment)
    }

    /**
     * Returns the height of the texture in pixels. Height is typically represented by the Y axis.
     *
     * Generated from Godot docs: TextureLayered.get_height
     */
    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, segment)
    }

    /**
     * Returns the number of referenced `Image`s.
     *
     * Generated from Godot docs: TextureLayered.get_layers
     */
    fun getLayers(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLayersBind, segment)
    }

    /**
     * Returns `true` if the layers have generated mipmaps.
     *
     * Generated from Godot docs: TextureLayered.has_mipmaps
     */
    fun hasMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasMipmapsBind, segment)
    }

    /**
     * Returns an `Image` resource with the data from specified `layer`.
     *
     * Generated from Godot docs: TextureLayered.get_layer_data
     */
    fun getLayerData(layer: Int): Image? {
        checkOpen()
        return Image.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getLayerDataBind, segment, layer))
    }

    @JvmInline
    value class LayeredType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Texture is a generic `Texture2DArray`.
             *
             * Generated from Godot docs: TextureLayered.LAYERED_TYPE_2D_ARRAY
             */
            val TYPE_2D_ARRAY: LayeredType get() = LayeredType(0L)
            /**
             * Texture is a `Cubemap`, with each side in its own layer (6 in total).
             *
             * Generated from Godot docs: TextureLayered.LAYERED_TYPE_CUBEMAP
             */
            val CUBEMAP: LayeredType get() = LayeredType(1L)
            /**
             * Texture is a `CubemapArray`, with each cubemap being made of 6 layers.
             *
             * Generated from Godot docs: TextureLayered.LAYERED_TYPE_CUBEMAP_ARRAY
             */
            val CUBEMAP_ARRAY: LayeredType get() = LayeredType(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureLayered? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureLayered? =
            if (handle.address() == 0L) null else TextureLayered(GodotHandle(handle))

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

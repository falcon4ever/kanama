package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        return Image.Format(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
    }

    /**
     * Returns the `TextureLayered`'s type. The type determines how the data is accessed, with cubemaps
     * having special types.
     *
     * Generated from Godot docs: TextureLayered.get_layered_type
     */
    fun getLayeredType(): TextureLayered.LayeredType {
        checkOpen()
        return TextureLayered.LayeredType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLayeredTypeBind, segment))
    }

    /**
     * Returns the width of the texture in pixels. Width is typically represented by the X axis.
     *
     * Generated from Godot docs: TextureLayered.get_width
     */
    fun getWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getWidthBind, segment)
    }

    /**
     * Returns the height of the texture in pixels. Height is typically represented by the Y axis.
     *
     * Generated from Godot docs: TextureLayered.get_height
     */
    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getHeightBind, segment)
    }

    /**
     * Returns the number of referenced `Image`s.
     *
     * Generated from Godot docs: TextureLayered.get_layers
     */
    fun getLayers(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLayersBind, segment)
    }

    /**
     * Returns `true` if the layers have generated mipmaps.
     *
     * Generated from Godot docs: TextureLayered.has_mipmaps
     */
    fun hasMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasMipmapsBind, segment)
    }

    /**
     * Returns an `Image` resource with the data from specified `layer`.
     *
     * Generated from Godot docs: TextureLayered.get_layer_data
     */
    fun getLayerData(layer: Int): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getLayerDataBind, segment, layer))
    }

    /**
     * Godot's `TextureLayered.LayeredType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextureLayered.LayeredType.<NAME>`).
     *
     * Generated from Godot docs: TextureLayered.LayeredType
     */
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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextureLayered? =
            if (handle.address() == 0L) null else RefCounted.owned(TextureLayered(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextureLayered? =
            if (handle.address() == 0L) null else TextureLayered(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_FORMAT_HASH = 3847873762L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("TextureLayered", "get_format", GET_FORMAT_HASH)

        private const val GET_LAYERED_TYPE_HASH = 518123893L
        @JvmField
        val getLayeredTypeBind =
            ObjectCalls.getMethodBind("TextureLayered", "get_layered_type", GET_LAYERED_TYPE_HASH)

        private const val GET_WIDTH_HASH = 3905245786L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("TextureLayered", "get_width", GET_WIDTH_HASH)

        private const val GET_HEIGHT_HASH = 3905245786L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("TextureLayered", "get_height", GET_HEIGHT_HASH)

        private const val GET_LAYERS_HASH = 3905245786L
        @JvmField
        val getLayersBind =
            ObjectCalls.getMethodBind("TextureLayered", "get_layers", GET_LAYERS_HASH)

        private const val HAS_MIPMAPS_HASH = 36873697L
        @JvmField
        val hasMipmapsBind =
            ObjectCalls.getMethodBind("TextureLayered", "has_mipmaps", HAS_MIPMAPS_HASH)

        private const val GET_LAYER_DATA_HASH = 3655284255L
        @JvmField
        val getLayerDataBind =
            ObjectCalls.getMethodBind("TextureLayered", "get_layer_data", GET_LAYER_DATA_HASH)
    }
}

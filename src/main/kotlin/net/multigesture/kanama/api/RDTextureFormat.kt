package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Texture format (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDTextureFormat
 */
class RDTextureFormat(handle: MemorySegment) : RefCounted(handle) {
    var format: Long
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var width: Long
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var height: Long
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var depth: Long
        @JvmName("depthProperty")
        get() = getDepth()
        @JvmName("setDepthProperty")
        set(value) = setDepth(value)

    var arrayLayers: Long
        @JvmName("arrayLayersProperty")
        get() = getArrayLayers()
        @JvmName("setArrayLayersProperty")
        set(value) = setArrayLayers(value)

    var mipmaps: Long
        @JvmName("mipmapsProperty")
        get() = getMipmaps()
        @JvmName("setMipmapsProperty")
        set(value) = setMipmaps(value)

    var textureType: Long
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    var samples: Long
        @JvmName("samplesProperty")
        get() = getSamples()
        @JvmName("setSamplesProperty")
        set(value) = setSamples(value)

    var usageBits: Long
        @JvmName("usageBitsProperty")
        get() = getUsageBits()
        @JvmName("setUsageBitsProperty")
        set(value) = setUsageBits(value)

    var isResolveBuffer: Boolean
        @JvmName("isResolveBufferProperty")
        get() = getIsResolveBuffer()
        @JvmName("setIsResolveBufferProperty")
        set(value) = setIsResolveBuffer(value)

    var isDiscardable: Boolean
        @JvmName("isDiscardableProperty")
        get() = getIsDiscardable()
        @JvmName("setIsDiscardableProperty")
        set(value) = setIsDiscardable(value)

    /**
     * The texture's pixel data format.
     *
     * Generated from Godot docs: RDTextureFormat.set_format
     */
    fun setFormat(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, pMember)
    }

    /**
     * The texture's pixel data format.
     *
     * Generated from Godot docs: RDTextureFormat.get_format
     */
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    /**
     * The texture's width (in pixels).
     *
     * Generated from Godot docs: RDTextureFormat.set_width
     */
    fun setWidth(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setWidthBind, handle, pMember)
    }

    /**
     * The texture's width (in pixels).
     *
     * Generated from Godot docs: RDTextureFormat.get_width
     */
    fun getWidth(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getWidthBind, handle)
    }

    /**
     * The texture's height (in pixels).
     *
     * Generated from Godot docs: RDTextureFormat.set_height
     */
    fun setHeight(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setHeightBind, handle, pMember)
    }

    /**
     * The texture's height (in pixels).
     *
     * Generated from Godot docs: RDTextureFormat.get_height
     */
    fun getHeight(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getHeightBind, handle)
    }

    /**
     * The texture's depth (in pixels). This is always `1` for 2D textures.
     *
     * Generated from Godot docs: RDTextureFormat.set_depth
     */
    fun setDepth(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setDepthBind, handle, pMember)
    }

    /**
     * The texture's depth (in pixels). This is always `1` for 2D textures.
     *
     * Generated from Godot docs: RDTextureFormat.get_depth
     */
    fun getDepth(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getDepthBind, handle)
    }

    /**
     * The number of layers in the texture. Only relevant for 2D texture arrays.
     *
     * Generated from Godot docs: RDTextureFormat.set_array_layers
     */
    fun setArrayLayers(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setArrayLayersBind, handle, pMember)
    }

    /**
     * The number of layers in the texture. Only relevant for 2D texture arrays.
     *
     * Generated from Godot docs: RDTextureFormat.get_array_layers
     */
    fun getArrayLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getArrayLayersBind, handle)
    }

    /**
     * The number of mipmaps available in the texture.
     *
     * Generated from Godot docs: RDTextureFormat.set_mipmaps
     */
    fun setMipmaps(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setMipmapsBind, handle, pMember)
    }

    /**
     * The number of mipmaps available in the texture.
     *
     * Generated from Godot docs: RDTextureFormat.get_mipmaps
     */
    fun getMipmaps(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getMipmapsBind, handle)
    }

    /**
     * The texture type.
     *
     * Generated from Godot docs: RDTextureFormat.set_texture_type
     */
    fun setTextureType(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureTypeBind, handle, pMember)
    }

    /**
     * The texture type.
     *
     * Generated from Godot docs: RDTextureFormat.get_texture_type
     */
    fun getTextureType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureTypeBind, handle)
    }

    /**
     * The number of samples used when sampling the texture.
     *
     * Generated from Godot docs: RDTextureFormat.set_samples
     */
    fun setSamples(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSamplesBind, handle, pMember)
    }

    /**
     * The number of samples used when sampling the texture.
     *
     * Generated from Godot docs: RDTextureFormat.get_samples
     */
    fun getSamples(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSamplesBind, handle)
    }

    /**
     * The texture's usage bits, which determine what can be done using the texture.
     *
     * Generated from Godot docs: RDTextureFormat.set_usage_bits
     */
    fun setUsageBits(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setUsageBitsBind, handle, pMember)
    }

    /**
     * The texture's usage bits, which determine what can be done using the texture.
     *
     * Generated from Godot docs: RDTextureFormat.get_usage_bits
     */
    fun getUsageBits(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUsageBitsBind, handle)
    }

    /**
     * The texture will be used as the destination of a resolve operation.
     *
     * Generated from Godot docs: RDTextureFormat.set_is_resolve_buffer
     */
    fun setIsResolveBuffer(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIsResolveBufferBind, handle, pMember)
    }

    /**
     * The texture will be used as the destination of a resolve operation.
     *
     * Generated from Godot docs: RDTextureFormat.get_is_resolve_buffer
     */
    fun getIsResolveBuffer(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsResolveBufferBind, handle)
    }

    /**
     * If a texture is discardable, its contents do not need to be preserved between frames. This flag
     * is only relevant when the texture is used as target in a draw list. This information is used by
     * `RenderingDevice` to figure out if a texture's contents can be discarded, eliminating
     * unnecessary writes to memory and boosting performance.
     *
     * Generated from Godot docs: RDTextureFormat.set_is_discardable
     */
    fun setIsDiscardable(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIsDiscardableBind, handle, pMember)
    }

    /**
     * If a texture is discardable, its contents do not need to be preserved between frames. This flag
     * is only relevant when the texture is used as target in a draw list. This information is used by
     * `RenderingDevice` to figure out if a texture's contents can be discarded, eliminating
     * unnecessary writes to memory and boosting performance.
     *
     * Generated from Godot docs: RDTextureFormat.get_is_discardable
     */
    fun getIsDiscardable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsDiscardableBind, handle)
    }

    /**
     * Adds `format` as a valid format for the corresponding `RDTextureView`'s
     * `RDTextureView.format_override` property. If any format is added as shareable, then the main
     * `format` must also be added.
     *
     * Generated from Godot docs: RDTextureFormat.add_shareable_format
     */
    fun addShareableFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(addShareableFormatBind, handle, format)
    }

    /**
     * Removes `format` from the list of valid formats that the corresponding `RDTextureView`'s
     * `RDTextureView.format_override` property can be set to.
     *
     * Generated from Godot docs: RDTextureFormat.remove_shareable_format
     */
    fun removeShareableFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(removeShareableFormatBind, handle, format)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDTextureFormat? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDTextureFormat? =
            if (handle.address() == 0L) null else RDTextureFormat(handle)

        private const val SET_FORMAT_HASH = 565531219L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_format", SET_FORMAT_HASH)
        }

        private const val GET_FORMAT_HASH = 2235804183L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_format", GET_FORMAT_HASH)
        }

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_width", SET_WIDTH_HASH)
        }

        private const val GET_WIDTH_HASH = 3905245786L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_width", GET_WIDTH_HASH)
        }

        private const val SET_HEIGHT_HASH = 1286410249L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 3905245786L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_DEPTH_HASH = 1286410249L
        private val setDepthBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_depth", SET_DEPTH_HASH)
        }

        private const val GET_DEPTH_HASH = 3905245786L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_depth", GET_DEPTH_HASH)
        }

        private const val SET_ARRAY_LAYERS_HASH = 1286410249L
        private val setArrayLayersBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_array_layers", SET_ARRAY_LAYERS_HASH)
        }

        private const val GET_ARRAY_LAYERS_HASH = 3905245786L
        private val getArrayLayersBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_array_layers", GET_ARRAY_LAYERS_HASH)
        }

        private const val SET_MIPMAPS_HASH = 1286410249L
        private val setMipmapsBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_mipmaps", SET_MIPMAPS_HASH)
        }

        private const val GET_MIPMAPS_HASH = 3905245786L
        private val getMipmapsBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_mipmaps", GET_MIPMAPS_HASH)
        }

        private const val SET_TEXTURE_TYPE_HASH = 652343381L
        private val setTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_texture_type", SET_TEXTURE_TYPE_HASH)
        }

        private const val GET_TEXTURE_TYPE_HASH = 4036357416L
        private val getTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_texture_type", GET_TEXTURE_TYPE_HASH)
        }

        private const val SET_SAMPLES_HASH = 3774171498L
        private val setSamplesBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_samples", SET_SAMPLES_HASH)
        }

        private const val GET_SAMPLES_HASH = 407791724L
        private val getSamplesBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_samples", GET_SAMPLES_HASH)
        }

        private const val SET_USAGE_BITS_HASH = 245642367L
        private val setUsageBitsBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_usage_bits", SET_USAGE_BITS_HASH)
        }

        private const val GET_USAGE_BITS_HASH = 1313398998L
        private val getUsageBitsBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_usage_bits", GET_USAGE_BITS_HASH)
        }

        private const val SET_IS_RESOLVE_BUFFER_HASH = 2586408642L
        private val setIsResolveBufferBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_is_resolve_buffer", SET_IS_RESOLVE_BUFFER_HASH)
        }

        private const val GET_IS_RESOLVE_BUFFER_HASH = 36873697L
        private val getIsResolveBufferBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_is_resolve_buffer", GET_IS_RESOLVE_BUFFER_HASH)
        }

        private const val SET_IS_DISCARDABLE_HASH = 2586408642L
        private val setIsDiscardableBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "set_is_discardable", SET_IS_DISCARDABLE_HASH)
        }

        private const val GET_IS_DISCARDABLE_HASH = 36873697L
        private val getIsDiscardableBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "get_is_discardable", GET_IS_DISCARDABLE_HASH)
        }

        private const val ADD_SHAREABLE_FORMAT_HASH = 565531219L
        private val addShareableFormatBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "add_shareable_format", ADD_SHAREABLE_FORMAT_HASH)
        }

        private const val REMOVE_SHAREABLE_FORMAT_HASH = 565531219L
        private val removeShareableFormatBind by lazy {
            ObjectCalls.getMethodBind("RDTextureFormat", "remove_shareable_format", REMOVE_SHAREABLE_FORMAT_HASH)
        }
    }
}

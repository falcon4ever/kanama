package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3

/**
 * Abstraction for working with modern low-level graphics APIs.
 *
 * Generated from Godot docs: RenderingDevice
 */
class RenderingDevice(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Creates a new texture. It can be accessed with the RID that is returned. Once finished with your
     * RID, you will want to free the RID using the RenderingDevice's `free_rid` method. Note: `data`
     * takes an `Array` of `PackedByteArray`s. For `TextureType.TYPE_1D`, `TextureType.TYPE_2D`, and
     * `TextureType.TYPE_3D` types, this array should only have one element, a `PackedByteArray`
     * containing all the data for the texture. For `_ARRAY` and `_CUBE` types, the length should be
     * the same as the number of `RDTextureFormat.array_layers` in `format`. Note: Not to be confused
     * with `RenderingServer.texture_2d_create`, which creates the Godot-specific `Texture2D` resource
     * as opposed to the graphics API's own texture type.
     *
     * Generated from Godot docs: RenderingDevice.texture_create
     */
    fun textureCreate(format: RDTextureFormat?, view: RDTextureView?, data: List<ByteArray>): RID {
        return ObjectCalls.ptrcallWithTwoObjectByteArrayListArgsRetRID(textureCreateBind, segment, format?.requireOpenHandle() ?: NULL_SEGMENT, view?.requireOpenHandle() ?: NULL_SEGMENT, data)
    }

    /**
     * Creates a shared texture using the specified `view` and the texture information from
     * `with_texture`. This will be freed automatically when the `with_texture` is freed.
     *
     * Generated from Godot docs: RenderingDevice.texture_create_shared
     */
    fun textureCreateShared(view: RDTextureView?, withTexture: RID): RID {
        return ObjectCalls.ptrcallWithObjectRIDArgsRetRID(textureCreateSharedBind, segment, view?.requireOpenHandle() ?: NULL_SEGMENT, withTexture)
    }

    /**
     * Creates a shared texture using the specified `view` and the texture information from
     * `with_texture`'s `layer` and `mipmap`. The number of included mipmaps from the original texture
     * can be controlled using the `mipmaps` parameter. Only relevant for textures with multiple
     * layers, such as 3D textures, texture arrays and cubemaps. For single-layer textures, use
     * `texture_create_shared`. For 2D textures (which only have one layer), `layer` must be `0`. Note:
     * Layer slicing is only supported for 2D texture arrays, not 3D textures or cubemaps. This will be
     * freed automatically when the `with_texture` is freed.
     *
     * Generated from Godot docs: RenderingDevice.texture_create_shared_from_slice
     */
    fun textureCreateSharedFromSlice(view: RDTextureView?, withTexture: RID, layer: Long, mipmap: Long, mipmaps: Long = 1L, sliceType: RenderingDevice.TextureSliceType = RenderingDevice.TextureSliceType.SLICE_2D): RID {
        return ObjectCalls.ptrcallWithObjectRIDThreeUInt32LongArgsRetRID(textureCreateSharedFromSliceBind, segment, view?.requireOpenHandle() ?: NULL_SEGMENT, withTexture, layer, mipmap, mipmaps, sliceType.value)
    }

    /**
     * Returns an RID for an existing `image` (`VkImage`) with the given `type`, `format`, `samples`,
     * `usage_flags`, `width`, `height`, `depth`, `layers`, and `mipmaps`. This can be used to allow
     * Godot to render onto foreign images.
     *
     * Generated from Godot docs: RenderingDevice.texture_create_from_extension
     */
    fun textureCreateFromExtension(type: RenderingDevice.TextureType, format: RenderingDevice.DataFormat, samples: RenderingDevice.TextureSamples, usageFlags: RenderingDevice.TextureUsageBits, image: Long, width: Long, height: Long, depth: Long, layers: Long, mipmaps: Long = 1L): RID {
        return ObjectCalls.ptrcallWithFourLongLongLongLongLongLongArgsRetRID(textureCreateFromExtensionBind, segment, type.value, format.value, samples.value, usageFlags.value, image, width, height, depth, layers, mipmaps)
    }

    /**
     * Updates texture data with new data, replacing the previous data in place. The updated texture
     * data must have the same dimensions and format. For 2D textures (which only have one layer),
     * `layer` must be `0`. Returns `GodotError.OK` if the update was successful,
     * `GodotError.ERR_INVALID_PARAMETER` otherwise. Note: Updating textures is forbidden during
     * creation of a draw or compute list. Note: The existing `texture` can't be updated while a draw
     * list that uses it as part of a framebuffer is being created. Ensure the draw list is finalized
     * (and that the color/depth texture using it is not set to `FinalAction.CONTINUE`) to update this
     * texture. Note: The existing `texture` requires the `TextureUsageBits.CAN_UPDATE_BIT` to be
     * updatable.
     *
     * Generated from Godot docs: RenderingDevice.texture_update
     */
    fun textureUpdate(texture: RID, layer: Long, data: ByteArray): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDUInt32ByteArrayArgsRetLong(textureUpdateBind, segment, texture, layer, data))
    }

    /**
     * Returns the `texture` data for the specified `layer` as raw binary data. For 2D textures (which
     * only have one layer), `layer` must be `0`. Note: `texture` can't be retrieved while a draw list
     * that uses it as part of a framebuffer is being created. Ensure the draw list is finalized (and
     * that the color/depth texture using it is not set to `FinalAction.CONTINUE`) to retrieve this
     * texture. Otherwise, an error is printed and an empty `PackedByteArray` is returned. Note:
     * `texture` requires the `TextureUsageBits.CAN_COPY_FROM_BIT` to be retrieved. Otherwise, an error
     * is printed and an empty `PackedByteArray` is returned. Note: This method will block the GPU from
     * working until the data is retrieved. Refer to `texture_get_data_async` for an alternative that
     * returns the data in more performant way.
     *
     * Generated from Godot docs: RenderingDevice.texture_get_data
     */
    fun textureGetData(texture: RID, layer: Long): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetByteArray(textureGetDataBind, segment, texture, layer)
    }

    /**
     * Asynchronous version of `texture_get_data`. RenderingDevice will call `callback` in a certain
     * amount of frames with the data the texture had at the time of the request. Note: At the moment,
     * the delay corresponds to the amount of frames specified by
     * `ProjectSettings.rendering/rendering_device/vsync/frame_queue_size`. Note: Downloading large
     * textures can have a prohibitive cost for real-time even when using the asynchronous method due
     * to hardware bandwidth limitations. When dealing with large resources, you can adjust settings
     * such as
     * `ProjectSettings.rendering/rendering_device/staging_buffer/texture_download_region_size_px` and
     * `ProjectSettings.rendering/rendering_device/staging_buffer/block_size_kb` to improve the
     * transfer speed at the cost of extra memory.
     *
     * Generated from Godot docs: RenderingDevice.texture_get_data_async
     */
    fun textureGetDataAsync(texture: RID, layer: Long, callback: GodotCallable): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDUInt32CallableArgsRetLong(textureGetDataAsyncBind, segment, texture, layer, callback.target.segment, callback.method))
    }

    /**
     * Returns `true` if the specified `format` is supported for the given `usage_flags`, `false`
     * otherwise.
     *
     * Generated from Godot docs: RenderingDevice.texture_is_format_supported_for_usage
     */
    fun textureIsFormatSupportedForUsage(format: RenderingDevice.DataFormat, usageFlags: RenderingDevice.TextureUsageBits): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(textureIsFormatSupportedForUsageBind, segment, format.value, usageFlags.value)
    }

    /**
     * Returns `true` if the `texture` is shared, `false` otherwise. See `RDTextureView`.
     *
     * Generated from Godot docs: RenderingDevice.texture_is_shared
     */
    fun textureIsShared(texture: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(textureIsSharedBind, segment, texture)
    }

    /**
     * Returns `true` if the `texture` is valid, `false` otherwise.
     *
     * Generated from Godot docs: RenderingDevice.texture_is_valid
     */
    fun textureIsValid(texture: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(textureIsValidBind, segment, texture)
    }

    /**
     * Updates the discardable property of `texture`. If a texture is discardable, its contents do not
     * need to be preserved between frames. This flag is only relevant when the texture is used as
     * target in a draw list. This information is used by `RenderingDevice` to figure out if a
     * texture's contents can be discarded, eliminating unnecessary writes to memory and boosting
     * performance.
     *
     * Generated from Godot docs: RenderingDevice.texture_set_discardable
     */
    fun textureSetDiscardable(texture: RID, discardable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(textureSetDiscardableBind, segment, texture, discardable)
    }

    /**
     * Returns `true` if the `texture` is discardable, `false` otherwise. See `RDTextureFormat` or
     * `texture_set_discardable`.
     *
     * Generated from Godot docs: RenderingDevice.texture_is_discardable
     */
    fun textureIsDiscardable(texture: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(textureIsDiscardableBind, segment, texture)
    }

    /**
     * Copies the `from_texture` to `to_texture` with the specified `from_pos`, `to_pos` and `size`
     * coordinates. For 2-dimensional textures, `from_pos` and `to_pos` must have a Z axis of `0`, and
     * `size` must have a Z axis of `1`. Source and destination mipmaps/layers must also be specified,
     * with these parameters being `0` for textures without mipmaps or single-layer textures. Returns
     * `GodotError.OK` if the texture copy was successful or `GodotError.ERR_INVALID_PARAMETER`
     * otherwise. Note: `from_texture` texture can't be copied while a draw list that uses it as part
     * of a framebuffer is being created. Ensure the draw list is finalized (and that the color/depth
     * texture using it is not set to `FinalAction.CONTINUE`) to copy this texture. Note:
     * `from_texture` texture requires the `TextureUsageBits.CAN_COPY_FROM_BIT` to be retrieved. Note:
     * `to_texture` can't be copied while a draw list that uses it as part of a framebuffer is being
     * created. Ensure the draw list is finalized (and that the color/depth texture using it is not set
     * to `FinalAction.CONTINUE`) to copy this texture. Note: `to_texture` requires the
     * `TextureUsageBits.CAN_COPY_TO_BIT` to be retrieved. Note: `from_texture` and `to_texture` must
     * be of the same type (color or depth).
     *
     * Generated from Godot docs: RenderingDevice.texture_copy
     */
    fun textureCopy(fromTexture: RID, toTexture: RID, fromPos: Vector3, toPos: Vector3, size: Vector3, srcMipmap: Long, dstMipmap: Long, srcLayer: Long, dstLayer: Long): GodotError {
        return GodotError(ObjectCalls.ptrcallWithTwoRIDThreeVector3FourUInt32ArgsRetLong(textureCopyBind, segment, fromTexture, toTexture, fromPos, toPos, size, srcMipmap, dstMipmap, srcLayer, dstLayer))
    }

    /**
     * Clears the specified `texture` by replacing all of its pixels with the specified `color`.
     * `base_mipmap` and `mipmap_count` determine which mipmaps of the texture are affected by this
     * clear operation, while `base_layer` and `layer_count` determine which layers of a 3D texture (or
     * texture array) are affected by this clear operation. For 2D textures (which only have one layer
     * by design), `base_layer` must be `0` and `layer_count` must be `1`. Note: `texture` can't be
     * cleared while a draw list that uses it as part of a framebuffer is being created. Ensure the
     * draw list is finalized (and that the color/depth texture using it is not set to
     * `FinalAction.CONTINUE`) to clear this texture.
     *
     * Generated from Godot docs: RenderingDevice.texture_clear
     */
    fun textureClear(texture: RID, color: Color, baseMipmap: Long, mipmapCount: Long, baseLayer: Long, layerCount: Long): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDColorFourUInt32ArgsRetLong(textureClearBind, segment, texture, color, baseMipmap, mipmapCount, baseLayer, layerCount))
    }

    /**
     * Resolves the `from_texture` texture onto `to_texture` with multisample antialiasing enabled.
     * This must be used when rendering a framebuffer for MSAA to work. Returns `GodotError.OK` if
     * successful, `GodotError.ERR_INVALID_PARAMETER` otherwise. Note: `from_texture` and `to_texture`
     * textures must have the same dimension, format and type (color or depth). Note: `from_texture`
     * can't be copied while a draw list that uses it as part of a framebuffer is being created. Ensure
     * the draw list is finalized (and that the color/depth texture using it is not set to
     * `FinalAction.CONTINUE`) to resolve this texture. Note: `from_texture` requires the
     * `TextureUsageBits.CAN_COPY_FROM_BIT` to be retrieved. Note: `from_texture` must be multisampled
     * and must also be 2D (or a slice of a 3D/cubemap texture). Note: `to_texture` can't be copied
     * while a draw list that uses it as part of a framebuffer is being created. Ensure the draw list
     * is finalized (and that the color/depth texture using it is not set to `FinalAction.CONTINUE`) to
     * resolve this texture. Note: `to_texture` texture requires the `TextureUsageBits.CAN_COPY_TO_BIT`
     * to be retrieved. Note: `to_texture` texture must not be multisampled and must also be 2D (or a
     * slice of a 3D/cubemap texture).
     *
     * Generated from Godot docs: RenderingDevice.texture_resolve_multisample
     */
    fun textureResolveMultisample(fromTexture: RID, toTexture: RID): GodotError {
        return GodotError(ObjectCalls.ptrcallWithTwoRIDArgsRetLong(textureResolveMultisampleBind, segment, fromTexture, toTexture))
    }

    /**
     * Returns the data format used to create this texture.
     *
     * Generated from Godot docs: RenderingDevice.texture_get_format
     */
    fun textureGetFormat(texture: RID): RDTextureFormat? {
        return RDTextureFormat.wrapOwned(ObjectCalls.ptrcallWithRIDArgRetObject(textureGetFormatBind, segment, texture))
    }

    /**
     * Returns the internal graphics handle for this texture object. For use when communicating with
     * third-party APIs mostly with GDExtension. Note: This function returns a `uint64_t` which
     * internally maps to a `GLuint` (OpenGL) or `VkImage` (Vulkan).
     *
     * Generated from Godot docs: RenderingDevice.texture_get_native_handle
     */
    fun textureGetNativeHandle(texture: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(textureGetNativeHandleBind, segment, texture)
    }

    /**
     * Creates a new framebuffer format with the specified `attachments` and `view_count`. Returns the
     * new framebuffer's unique framebuffer format ID. If `view_count` is greater than or equal to `2`,
     * enables multiview which is used for VR rendering. This requires support for the Vulkan multiview
     * extension.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_format_create
     */
    fun framebufferFormatCreate(attachments: List<RDAttachmentFormat>, viewCount: Long = 1L): Long {
        return ObjectCalls.ptrcallWithObjectListUInt32ArgsRetLong(framebufferFormatCreateBind, segment, attachments, viewCount)
    }

    /**
     * Creates a multipass framebuffer format with the specified `attachments`, `passes` and
     * `view_count` and returns its ID. If `view_count` is greater than or equal to `2`, enables
     * multiview which is used for VR rendering. This requires support for the Vulkan multiview
     * extension.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_format_create_multipass
     */
    fun framebufferFormatCreateMultipass(attachments: List<RDAttachmentFormat>, passes: List<RDFramebufferPass>, viewCount: Long = 1L): Long {
        return ObjectCalls.ptrcallWithTwoObjectListUInt32ArgsRetLong(framebufferFormatCreateMultipassBind, segment, attachments, passes, viewCount)
    }

    /**
     * Creates a new empty framebuffer format with the specified number of `samples` and returns its
     * ID.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_format_create_empty
     */
    fun framebufferFormatCreateEmpty(samples: RenderingDevice.TextureSamples = RenderingDevice.TextureSamples.SAMPLES_1): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(framebufferFormatCreateEmptyBind, segment, samples.value)
    }

    /**
     * Returns the number of texture samples used for the given framebuffer `format` ID (returned by
     * `framebuffer_get_format`).
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_format_get_texture_samples
     */
    fun framebufferFormatGetTextureSamples(format: Long, renderPass: Long = 0L): RenderingDevice.TextureSamples {
        return RenderingDevice.TextureSamples(ObjectCalls.ptrcallWithLongAndUInt32ArgRetLong(framebufferFormatGetTextureSamplesBind, segment, format, renderPass))
    }

    /**
     * Creates a new framebuffer. It can be accessed with the RID that is returned. Once finished with
     * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This will
     * be freed automatically when any of the `textures` is freed.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_create
     */
    fun framebufferCreate(textures: List<RID>, validateWithFormat: Long = -1L, viewCount: Long = 1L): RID {
        return ObjectCalls.ptrcallWithRIDListLongUInt32ArgsRetRID(framebufferCreateBind, segment, textures, validateWithFormat, viewCount)
    }

    /**
     * Creates a new multipass framebuffer. It can be accessed with the RID that is returned. Once
     * finished with your RID, you will want to free the RID using the RenderingDevice's `free_rid`
     * method. This will be freed automatically when any of the `textures` is freed.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_create_multipass
     */
    fun framebufferCreateMultipass(textures: List<RID>, passes: List<RDFramebufferPass>, validateWithFormat: Long = -1L, viewCount: Long = 1L): RID {
        return ObjectCalls.ptrcallWithRIDListObjectListLongUInt32ArgsRetRID(framebufferCreateMultipassBind, segment, textures, passes, validateWithFormat, viewCount)
    }

    /**
     * Creates a new empty framebuffer. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_create_empty
     */
    fun framebufferCreateEmpty(size: Vector2i, samples: RenderingDevice.TextureSamples = RenderingDevice.TextureSamples.SAMPLES_1, validateWithFormat: Long = -1L): RID {
        return ObjectCalls.ptrcallWithVector2iLongLongArgsRetRID(framebufferCreateEmptyBind, segment, size, samples.value, validateWithFormat)
    }

    /**
     * Returns the format ID of the framebuffer specified by the `framebuffer` RID. This ID is
     * guaranteed to be unique for the same formats and does not need to be freed.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_get_format
     */
    fun framebufferGetFormat(framebuffer: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(framebufferGetFormatBind, segment, framebuffer)
    }

    /**
     * Returns `true` if the framebuffer specified by the `framebuffer` RID is valid, `false`
     * otherwise.
     *
     * Generated from Godot docs: RenderingDevice.framebuffer_is_valid
     */
    fun framebufferIsValid(framebuffer: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(framebufferIsValidBind, segment, framebuffer)
    }

    /**
     * Creates a new sampler. It can be accessed with the RID that is returned. Once finished with your
     * RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.sampler_create
     */
    fun samplerCreate(state: RDSamplerState?): RID {
        return ObjectCalls.ptrcallWithObjectArgRetRID(samplerCreateBind, segment, state?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns `true` if implementation supports using a texture of `format` with the given
     * `sampler_filter`.
     *
     * Generated from Godot docs: RenderingDevice.sampler_is_format_supported_for_filter
     */
    fun samplerIsFormatSupportedForFilter(format: RenderingDevice.DataFormat, samplerFilter: RenderingDevice.SamplerFilter): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(samplerIsFormatSupportedForFilterBind, segment, format.value, samplerFilter.value)
    }

    /**
     * Creates a new vertex buffer. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.vertex_buffer_create
     */
    fun vertexBufferCreate(sizeBytes: Long, data: ByteArray, creationBits: RenderingDevice.BufferCreationBits = RenderingDevice.BufferCreationBits(0L)): RID {
        return ObjectCalls.ptrcallWithUInt32ByteArrayLongArgsRetRID(vertexBufferCreateBind, segment, sizeBytes, data, creationBits.value)
    }

    /**
     * Creates a new vertex format with the specified `vertex_descriptions`. Returns a unique vertex
     * format ID corresponding to the newly created vertex format.
     *
     * Generated from Godot docs: RenderingDevice.vertex_format_create
     */
    fun vertexFormatCreate(vertexDescriptions: List<RDVertexAttribute>): Long {
        return ObjectCalls.ptrcallWithObjectListArgRetLong(vertexFormatCreateBind, segment, vertexDescriptions)
    }

    /**
     * Creates a vertex array based on the specified buffers. Optionally, `offsets` (in bytes) may be
     * defined for each buffer. Once finished with your RID, you will want to free the RID using the
     * RenderingDevice's `free_rid` method. This will be freed automatically when any of the
     * `src_buffers` is freed.
     *
     * Generated from Godot docs: RenderingDevice.vertex_array_create
     */
    fun vertexArrayCreate(vertexCount: Long, vertexFormat: Long, srcBuffers: List<RID>, offsets: List<Long>): RID {
        return ObjectCalls.ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID(vertexArrayCreateBind, segment, vertexCount, vertexFormat, srcBuffers, offsets)
    }

    /**
     * Creates a new index buffer. It can be accessed with the RID that is returned. Once finished with
     * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.index_buffer_create
     */
    fun indexBufferCreate(sizeIndices: Long, format: RenderingDevice.IndexBufferFormat, data: ByteArray, useRestartIndices: Boolean = false, creationBits: RenderingDevice.BufferCreationBits = RenderingDevice.BufferCreationBits(0L)): RID {
        return ObjectCalls.ptrcallWithUInt32LongPackedByteArrayBoolLongArgsRetRID(indexBufferCreateBind, segment, sizeIndices, format.value, data, useRestartIndices, creationBits.value)
    }

    /**
     * Creates a new index array. It can be accessed with the RID that is returned. Once finished with
     * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This will
     * be freed automatically when the `index_buffer` is freed.
     *
     * Generated from Godot docs: RenderingDevice.index_array_create
     */
    fun indexArrayCreate(indexBuffer: RID, indexOffset: Long, indexCount: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetRID(indexArrayCreateBind, segment, indexBuffer, indexOffset, indexCount)
    }

    /**
     * Compiles a SPIR-V from the shader source code in `shader_source` and returns the SPIR-V as an
     * `RDShaderSPIRV`. This intermediate language shader is portable across different GPU models and
     * driver versions, but cannot be run directly by GPUs until compiled into a binary shader using
     * `shader_compile_binary_from_spirv`. If `allow_cache` is `true`, make use of the shader cache
     * generated by Godot. This avoids a potentially lengthy shader compilation step if the shader is
     * already in cache. If `allow_cache` is `false`, Godot's shader cache is ignored and the shader
     * will always be recompiled.
     *
     * Generated from Godot docs: RenderingDevice.shader_compile_spirv_from_source
     */
    fun shaderCompileSpirvFromSource(shaderSource: RDShaderSource?, allowCache: Boolean = true): RDShaderSPIRV? {
        return RDShaderSPIRV.wrapOwned(ObjectCalls.ptrcallWithObjectAndBoolArgRetObject(shaderCompileSpirvFromSourceBind, segment, shaderSource?.requireOpenHandle() ?: NULL_SEGMENT, allowCache))
    }

    /**
     * Compiles a binary shader from `spirv_data` and returns the compiled binary data as a
     * `PackedByteArray`. This compiled shader is specific to the GPU model and driver version used; it
     * will not work on different GPU models or even different driver versions. See also
     * `shader_compile_spirv_from_source`. `name` is an optional human-readable name that can be given
     * to the compiled shader for organizational purposes.
     *
     * Generated from Godot docs: RenderingDevice.shader_compile_binary_from_spirv
     */
    fun shaderCompileBinaryFromSpirv(spirvData: RDShaderSPIRV?, name: String = ""): ByteArray {
        return ObjectCalls.ptrcallWithObjectAndStringArgRetByteArray(shaderCompileBinaryFromSpirvBind, segment, spirvData?.requireOpenHandle() ?: NULL_SEGMENT, name)
    }

    /**
     * Creates a new shader instance from SPIR-V intermediate code. It can be accessed with the RID
     * that is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingDevice's `free_rid` method. See also `shader_compile_spirv_from_source` and
     * `shader_create_from_bytecode`.
     *
     * Generated from Godot docs: RenderingDevice.shader_create_from_spirv
     */
    fun shaderCreateFromSpirv(spirvData: RDShaderSPIRV?, name: String = ""): RID {
        return ObjectCalls.ptrcallWithObjectStringArgsRetRID(shaderCreateFromSpirvBind, segment, spirvData?.requireOpenHandle() ?: NULL_SEGMENT, name)
    }

    /**
     * Creates a new shader instance from a binary compiled shader. It can be accessed with the RID
     * that is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingDevice's `free_rid` method. See also `shader_compile_binary_from_spirv` and
     * `shader_create_from_spirv`.
     *
     * Generated from Godot docs: RenderingDevice.shader_create_from_bytecode
     */
    fun shaderCreateFromBytecode(binaryData: ByteArray, placeholderRid: RID): RID {
        return ObjectCalls.ptrcallWithByteArrayAndRIDArgRetRID(shaderCreateFromBytecodeBind, segment, binaryData, placeholderRid)
    }

    /**
     * Create a placeholder RID by allocating an RID without initializing it for use in
     * `shader_create_from_bytecode`. This allows you to create an RID for a shader and pass it around,
     * but defer compiling the shader to a later time.
     *
     * Generated from Godot docs: RenderingDevice.shader_create_placeholder
     */
    fun shaderCreatePlaceholder(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(shaderCreatePlaceholderBind, segment)
    }

    /**
     * Returns the internal vertex input mask. Internally, the vertex input mask is an unsigned integer
     * consisting of the locations (specified in GLSL via. `layout(location = ...)`) of the input
     * variables (specified in GLSL by the `in` keyword).
     *
     * Generated from Godot docs: RenderingDevice.shader_get_vertex_input_attribute_mask
     */
    fun shaderGetVertexInputAttributeMask(shader: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shaderGetVertexInputAttributeMaskBind, segment, shader)
    }

    /**
     * Creates a new uniform buffer. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.uniform_buffer_create
     */
    fun uniformBufferCreate(sizeBytes: Long, data: ByteArray, creationBits: RenderingDevice.BufferCreationBits = RenderingDevice.BufferCreationBits(0L)): RID {
        return ObjectCalls.ptrcallWithUInt32ByteArrayLongArgsRetRID(uniformBufferCreateBind, segment, sizeBytes, data, creationBits.value)
    }

    /**
     * Creates a storage buffer (https://vkguide.dev/docs/chapter-4/storage_buffers/) with the
     * specified `data` and `usage`. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.storage_buffer_create
     */
    fun storageBufferCreate(sizeBytes: Long, data: ByteArray, usage: RenderingDevice.StorageBufferUsage = RenderingDevice.StorageBufferUsage(0L), creationBits: RenderingDevice.BufferCreationBits = RenderingDevice.BufferCreationBits(0L)): RID {
        return ObjectCalls.ptrcallWithUInt32PackedByteArrayTwoLongArgsRetRID(storageBufferCreateBind, segment, sizeBytes, data, usage.value, creationBits.value)
    }

    /**
     * Creates a new texture buffer. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.texture_buffer_create
     */
    fun textureBufferCreate(sizeBytes: Long, format: RenderingDevice.DataFormat, data: ByteArray): RID {
        return ObjectCalls.ptrcallWithUInt32LongByteArrayArgsRetRID(textureBufferCreateBind, segment, sizeBytes, format.value, data)
    }

    /**
     * Creates a new uniform set. It can be accessed with the RID that is returned. Once finished with
     * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This will
     * be freed automatically when the `shader` or any of the RIDs in the `uniforms` is freed.
     *
     * Generated from Godot docs: RenderingDevice.uniform_set_create
     */
    fun uniformSetCreate(uniforms: List<RDUniform>, shader: RID, shaderSet: Long): RID {
        return ObjectCalls.ptrcallWithObjectListRIDUInt32ArgsRetRID(uniformSetCreateBind, segment, uniforms, shader, shaderSet)
    }

    /**
     * Checks if the `uniform_set` is valid, i.e. is owned.
     *
     * Generated from Godot docs: RenderingDevice.uniform_set_is_valid
     */
    fun uniformSetIsValid(uniformSet: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(uniformSetIsValidBind, segment, uniformSet)
    }

    /**
     * Copies `size` bytes from the `src_buffer` at `src_offset` into `dst_buffer` at `dst_offset`.
     * Prints an error if: - `size` exceeds the size of either `src_buffer` or `dst_buffer` at their
     * corresponding offsets - a draw list is currently active (created by `draw_list_begin`) - a
     * compute list is currently active (created by `compute_list_begin`)
     *
     * Generated from Godot docs: RenderingDevice.buffer_copy
     */
    fun bufferCopy(srcBuffer: RID, dstBuffer: RID, srcOffset: Long, dstOffset: Long, size: Long): GodotError {
        return GodotError(ObjectCalls.ptrcallWithTwoRIDThreeUInt32ArgsRetLong(bufferCopyBind, segment, srcBuffer, dstBuffer, srcOffset, dstOffset, size))
    }

    /**
     * Updates a region of `size_bytes` bytes, starting at `offset`, in the buffer, with the specified
     * `data`. Prints an error if: - the region specified by `offset` + `size_bytes` exceeds the buffer
     * - a draw list is currently active (created by `draw_list_begin`) - a compute list is currently
     * active (created by `compute_list_begin`)
     *
     * Generated from Godot docs: RenderingDevice.buffer_update
     */
    fun bufferUpdate(buffer: RID, offset: Long, sizeBytes: Long, data: ByteArray): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDTwoUInt32PackedByteArrayArgsRetLong(bufferUpdateBind, segment, buffer, offset, sizeBytes, data))
    }

    /**
     * Clears the contents of the `buffer`, clearing `size_bytes` bytes, starting at `offset`. Prints
     * an error if: - the size isn't a multiple of four - the region specified by `offset` +
     * `size_bytes` exceeds the buffer - a draw list is currently active (created by `draw_list_begin`)
     * - a compute list is currently active (created by `compute_list_begin`)
     *
     * Generated from Godot docs: RenderingDevice.buffer_clear
     */
    fun bufferClear(buffer: RID, offset: Long, sizeBytes: Long): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetLong(bufferClearBind, segment, buffer, offset, sizeBytes))
    }

    /**
     * Returns a copy of the data of the specified `buffer`, optionally `offset_bytes` and `size_bytes`
     * can be set to copy only a portion of the buffer. Note: This method will block the GPU from
     * working until the data is retrieved. Refer to `buffer_get_data_async` for an alternative that
     * returns the data in more performant way.
     *
     * Generated from Godot docs: RenderingDevice.buffer_get_data
     */
    fun bufferGetData(buffer: RID, offsetBytes: Long = 0L, sizeBytes: Long = 0L): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetByteArray(bufferGetDataBind, segment, buffer, offsetBytes, sizeBytes)
    }

    /**
     * Asynchronous version of `buffer_get_data`. RenderingDevice will call `callback` in a certain
     * amount of frames with the data the buffer had at the time of the request. Note: At the moment,
     * the delay corresponds to the amount of frames specified by
     * `ProjectSettings.rendering/rendering_device/vsync/frame_queue_size`. Note: Downloading large
     * buffers can have a prohibitive cost for real-time even when using the asynchronous method due to
     * hardware bandwidth limitations. When dealing with large resources, you can adjust settings such
     * as `ProjectSettings.rendering/rendering_device/staging_buffer/block_size_kb` to improve the
     * transfer speed at the cost of extra memory.
     *
     * Generated from Godot docs: RenderingDevice.buffer_get_data_async
     */
    fun bufferGetDataAsync(buffer: RID, callback: GodotCallable, offsetBytes: Long = 0L, sizeBytes: Long = 0L): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDCallableTwoUInt32ArgsRetLong(bufferGetDataAsyncBind, segment, buffer, callback.target.segment, callback.method, offsetBytes, sizeBytes))
    }

    /**
     * Returns the address of the given `buffer` which can be passed to shaders in any way to access
     * underlying data. Buffer must have been created with this feature enabled. Note: You must check
     * that the GPU supports this functionality by calling `has_feature` with
     * `Features.BUFFER_DEVICE_ADDRESS` as a parameter.
     *
     * Generated from Godot docs: RenderingDevice.buffer_get_device_address
     */
    fun bufferGetDeviceAddress(buffer: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bufferGetDeviceAddressBind, segment, buffer)
    }

    /**
     * Creates a new render pipeline. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This
     * will be freed automatically when the `shader` is freed.
     *
     * Generated from Godot docs: RenderingDevice.render_pipeline_create
     */
    fun renderPipelineCreate(shader: RID, framebufferFormat: Long, vertexFormat: Long, primitive: RenderingDevice.RenderPrimitive, rasterizationState: RDPipelineRasterizationState?, multisampleState: RDPipelineMultisampleState?, stencilState: RDPipelineDepthStencilState?, colorBlendState: RDPipelineColorBlendState?, dynamicStateFlags: RenderingDevice.PipelineDynamicStateFlags = RenderingDevice.PipelineDynamicStateFlags(0L), forRenderPass: Long = 0L, specializationConstants: List<RDPipelineSpecializationConstant>): RID {
        return ObjectCalls.ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID(renderPipelineCreateBind, segment, shader, framebufferFormat, vertexFormat, primitive.value, rasterizationState?.requireOpenHandle() ?: NULL_SEGMENT, multisampleState?.requireOpenHandle() ?: NULL_SEGMENT, stencilState?.requireOpenHandle() ?: NULL_SEGMENT, colorBlendState?.requireOpenHandle() ?: NULL_SEGMENT, dynamicStateFlags.value, forRenderPass, specializationConstants)
    }

    /**
     * Returns `true` if the render pipeline specified by the `render_pipeline` RID is valid, `false`
     * otherwise.
     *
     * Generated from Godot docs: RenderingDevice.render_pipeline_is_valid
     */
    fun renderPipelineIsValid(renderPipeline: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(renderPipelineIsValidBind, segment, renderPipeline)
    }

    /**
     * Creates a new compute pipeline. It can be accessed with the RID that is returned. Once finished
     * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This
     * will be freed automatically when the `shader` is freed.
     *
     * Generated from Godot docs: RenderingDevice.compute_pipeline_create
     */
    fun computePipelineCreate(shader: RID, specializationConstants: List<RDPipelineSpecializationConstant>): RID {
        return ObjectCalls.ptrcallWithRIDObjectListArgsRetRID(computePipelineCreateBind, segment, shader, specializationConstants)
    }

    /**
     * Returns `true` if the compute pipeline specified by the `compute_pipeline` RID is valid, `false`
     * otherwise.
     *
     * Generated from Godot docs: RenderingDevice.compute_pipeline_is_valid
     */
    fun computePipelineIsValid(computePipeline: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(computePipelineIsValidBind, segment, computePipeline)
    }

    /**
     * Creates a new raytracing pipeline. It can be accessed with the RID that is returned. Once
     * finished with your RID, you will want to free the RID using the RenderingDevice's `free_rid`
     * method. Each shader must provide the required stage. All stages must use compatible pipeline
     * layouts. The pipeline selects the required stage from each shader. Input order defines stable
     * indices used by the API: - `raygen_shaders` is indexed in `raytracing_list_trace_rays`. -
     * `miss_shaders` is indexed in `traceRayEXT`. - `hit_groups` is indexed in `hit_sbt_range_update`.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_pipeline_create
     */
    fun raytracingPipelineCreate(raygenShaders: List<RDPipelineShader>, missShaders: List<RDPipelineShader>, hitGroups: List<RDHitGroup>, maxTraceRecursionDepth: Long): RID {
        return ObjectCalls.ptrcallWithThreeObjectListUInt32ArgsRetRID(raytracingPipelineCreateBind, segment, raygenShaders, missShaders, hitGroups, maxTraceRecursionDepth)
    }

    /**
     * Returns `true` if the raytracing pipeline specified by the `raytracing_pipeline` RID is valid,
     * `false` otherwise.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_pipeline_is_valid
     */
    fun raytracingPipelineIsValid(raytracingPipeline: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(raytracingPipelineIsValidBind, segment, raytracingPipeline)
    }

    /**
     * Creates a new Bottom-Level Acceleration Structure (BLAS). It can be accessed with the RID that
     * is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingDevice's `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.blas_create
     */
    fun blasCreate(geometries: List<RDAccelerationStructureGeometry>, flags: RenderingDevice.AccelerationStructureFlagBits): RID {
        return ObjectCalls.ptrcallWithObjectListLongArgsRetRID(blasCreateBind, segment, geometries, flags.value)
    }

    /**
     * Creates a new Top-Level Acceleration Structure (TLAS). It can be accessed with the RID that is
     * returned. Once finished with your RID, you will want to free the RID using the RenderingDevice's
     * `free_rid` method.
     *
     * Generated from Godot docs: RenderingDevice.tlas_create
     */
    fun tlasCreate(maxInstanceCount: Long, flags: RenderingDevice.AccelerationStructureFlagBits): RID {
        return ObjectCalls.ptrcallWithUInt32AndLongArgRetRID(tlasCreateBind, segment, maxInstanceCount, flags.value)
    }

    /**
     * Builds the `blas`.
     *
     * Generated from Godot docs: RenderingDevice.blas_build
     */
    fun blasBuild(blas: RID): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDArgRetLong(blasBuildBind, segment, blas))
    }

    /**
     * Builds the `tlas`. The contents of previous builds are discarded. Any BLAS provided through the
     * `RDAccelerationStructureInstance.blas` member must already have been built using the
     * `blas_build` method. The number of instances can be equal to or smaller than the maximum
     * instance count provided in the `tlas_create` method. Note: Freeing or rebuilding any of the
     * provided BLASes after this method invalidates the TLAS and requires it to be rebuilt.
     *
     * Generated from Godot docs: RenderingDevice.tlas_build
     */
    fun tlasBuild(tlas: RID, instances: List<RDAccelerationStructureInstance>): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDAndObjectListArgsRetLong(tlasBuildBind, segment, tlas, instances))
    }

    /**
     * Creates a new hit shader binding table (SBT). It can be accessed with the RID that is returned.
     * Once finished with your RID, you will want to free the RID using the RenderingDevice's
     * `free_rid` method. This will be freed automatically when the `raytracing_pipeline` is freed. The
     * hit SBT resizes itself as needed. `initial_hit_group_capacity` is used to allocate the initial
     * backing memory.
     *
     * Generated from Godot docs: RenderingDevice.hit_sbt_create
     */
    fun hitSbtCreate(raytracingPipeline: RID, initialHitGroupCapacity: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetRID(hitSbtCreateBind, segment, raytracingPipeline, initialHitGroupCapacity)
    }

    /**
     * Sets a new `raytracing_pipeline` for `hit_sbt`. The new pipeline must be a superset of the
     * previous one. Existing hit groups must keep the same order and new hit groups should be appended
     * to the end. This preserves existing SBT entries. The previous pipeline must remain valid during
     * the call.
     *
     * Generated from Godot docs: RenderingDevice.hit_sbt_set_pipeline
     */
    fun hitSbtSetPipeline(hitSbt: RID, raytracingPipeline: RID): GodotError {
        return GodotError(ObjectCalls.ptrcallWithTwoRIDArgsRetLong(hitSbtSetPipelineBind, segment, hitSbt, raytracingPipeline))
    }

    /**
     * Allocates a contiguous range of SBT entries from `hit_sbt`. The returned value should be
     * assigned to `RDAccelerationStructureInstance.hit_sbt_range`. During ray traversal, hit group
     * index is computed as: (geometry index in `RDAccelerationStructureInstance.blas`) × (SBT stride
     * used in `traceRayEXT`) + (SBT offset used in `traceRayEXT`) + (range offset) `hit_group_count`
     * must be large enough to cover all SBT entries that may be indexed by this equation. This
     * typically corresponds to: (geometry count in `RDAccelerationStructureInstance.blas`) × (SBT
     * stride used in `traceRayEXT`) The allocated range is uninitialized and must be filled using
     * `hit_sbt_range_update`.
     *
     * Generated from Godot docs: RenderingDevice.hit_sbt_range_alloc
     */
    fun hitSbtRangeAlloc(hitSbt: RID, hitGroupCount: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetLong(hitSbtRangeAllocBind, segment, hitSbt, hitGroupCount)
    }

    /**
     * Frees a hit SBT range previously allocated with `hit_sbt_range_alloc`. The range must not be in
     * use by any acceleration structure after being freed.
     *
     * Generated from Godot docs: RenderingDevice.hit_sbt_range_free
     */
    fun hitSbtRangeFree(hitSbt: RID, range: Long): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDAndLongArgRetLong(hitSbtRangeFreeBind, segment, hitSbt, range))
    }

    /**
     * Updates the contents of a hit SBT range. `hit_group_indices` specifies indices into the hit
     * group array provided in `raytracing_pipeline_create`. The `offset` parameter specifies where
     * within the allocated range the writing begins. This allows partial updates of a range. However,
     * the complete range must be fully initialized before it is used in a raytracing dispatch.
     *
     * Generated from Godot docs: RenderingDevice.hit_sbt_range_update
     */
    fun hitSbtRangeUpdate(hitSbt: RID, range: Long, offset: Long, hitGroupIndices: List<Int>): GodotError {
        return GodotError(ObjectCalls.ptrcallWithRIDLongUInt32AndPackedInt32ListArgRetLong(hitSbtRangeUpdateBind, segment, hitSbt, range, offset, hitGroupIndices))
    }

    /**
     * Returns the window width matching the graphics API context for the given window ID (in pixels).
     * Despite the parameter being named `screen`, this returns the window size. See also
     * `screen_get_height`. Note: Only the main `RenderingDevice` returned by
     * `RenderingServer.get_rendering_device` has a width. If called on a local `RenderingDevice`, this
     * method prints an error and returns `INVALID_ID`.
     *
     * Generated from Godot docs: RenderingDevice.screen_get_width
     */
    fun screenGetWidth(screen: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(screenGetWidthBind, segment, screen)
    }

    /**
     * Returns the window height matching the graphics API context for the given window ID (in pixels).
     * Despite the parameter being named `screen`, this returns the window size. See also
     * `screen_get_width`. Note: Only the main `RenderingDevice` returned by
     * `RenderingServer.get_rendering_device` has a height. If called on a local `RenderingDevice`,
     * this method prints an error and returns `INVALID_ID`.
     *
     * Generated from Godot docs: RenderingDevice.screen_get_height
     */
    fun screenGetHeight(screen: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(screenGetHeightBind, segment, screen)
    }

    /**
     * Returns the framebuffer format of the given screen. Note: Only the main `RenderingDevice`
     * returned by `RenderingServer.get_rendering_device` has a format. If called on a local
     * `RenderingDevice`, this method prints an error and returns `INVALID_ID`.
     *
     * Generated from Godot docs: RenderingDevice.screen_get_framebuffer_format
     */
    fun screenGetFramebufferFormat(screen: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(screenGetFramebufferFormatBind, segment, screen)
    }

    /**
     * High-level variant of `draw_list_begin`, with the parameters automatically being adjusted for
     * drawing onto the window specified by the `screen` ID. Note: Cannot be used with local
     * RenderingDevices, as these don't have a screen. If called on a local RenderingDevice,
     * `draw_list_begin_for_screen` returns `INVALID_ID`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_begin_for_screen
     */
    fun drawListBeginForScreen(screen: Int = 0, clearColor: Color): Long {
        return ObjectCalls.ptrcallWithIntColorArgsRetLong(drawListBeginForScreenBind, segment, screen, clearColor)
    }

    /**
     * Starts a list of raster drawing commands created with the `draw_*` methods. The returned value
     * should be passed to other `draw_list_*` functions. Multiple draw lists cannot be created at the
     * same time; you must finish the previous draw list first using `draw_list_end`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_begin
     */
    fun drawListBegin(framebuffer: RID, drawFlags: RenderingDevice.DrawFlags = RenderingDevice.DrawFlags.DEFAULT_ALL, clearColorValues: List<Color>, clearDepthValue: Double = 1.0, clearStencilValue: Long = 0L, region: Rect2, breadcrumb: Long = 0L): Long {
        return ObjectCalls.ptrcallWithRIDLongPackedColorListDoubleUInt32Rect2UInt32ArgsRetLong(drawListBeginBind, segment, framebuffer, drawFlags.value, clearColorValues, clearDepthValue, clearStencilValue, region, breadcrumb)
    }

    /**
     * This method does nothing and always returns an empty `PackedInt64Array`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_begin_split
     */
    fun drawListBeginSplit(framebuffer: RID, splits: Long, initialColorAction: RenderingDevice.InitialAction, finalColorAction: RenderingDevice.FinalAction, initialDepthAction: RenderingDevice.InitialAction, finalDepthAction: RenderingDevice.FinalAction, clearColorValues: List<Color>, clearDepth: Double = 1.0, clearStencil: Long = 0L, region: Rect2, storageTextures: List<RID>): List<Long> {
        return ObjectCalls.ptrcallWithRIDUInt32FourLongPackedColorListDoubleUInt32Rect2RIDListArgsRetPackedInt64List(drawListBeginSplitBind, segment, framebuffer, splits, initialColorAction.value, finalColorAction.value, initialDepthAction.value, finalDepthAction.value, clearColorValues, clearDepth, clearStencil, region, storageTextures)
    }

    /**
     * Sets blend constants for the specified `draw_list` to `color`. Blend constants are used only if
     * the graphics pipeline is created with `PipelineDynamicStateFlags.BLEND_CONSTANTS` flag set.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_set_blend_constants
     */
    fun drawListSetBlendConstants(drawList: Long, color: Color) {
        ObjectCalls.ptrcallWithLongAndColorArg(drawListSetBlendConstantsBind, segment, drawList, color)
    }

    /**
     * Binds `render_pipeline` to the specified `draw_list`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_bind_render_pipeline
     */
    fun drawListBindRenderPipeline(drawList: Long, renderPipeline: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(drawListBindRenderPipelineBind, segment, drawList, renderPipeline)
    }

    /**
     * Binds `uniform_set` to the specified `draw_list`. A `set_index` must also be specified, which is
     * an identifier starting from `0` that must match the one expected by the draw list.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_bind_uniform_set
     */
    fun drawListBindUniformSet(drawList: Long, uniformSet: RID, setIndex: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(drawListBindUniformSetBind, segment, drawList, uniformSet, setIndex)
    }

    /**
     * Binds `vertex_array` to the specified `draw_list`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_bind_vertex_array
     */
    fun drawListBindVertexArray(drawList: Long, vertexArray: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(drawListBindVertexArrayBind, segment, drawList, vertexArray)
    }

    /**
     * Binds a set of `vertex_buffers` directly to the specified `draw_list` using `vertex_format`
     * without creating a vertex array RID. Provide the number of vertices in `vertex_count`; optional
     * per-buffer byte `offsets` may also be supplied.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_bind_vertex_buffers_format
     */
    fun drawListBindVertexBuffersFormat(drawList: Long, vertexFormat: Long, vertexCount: Long, vertexBuffers: List<RID>, offsets: List<Long>) {
        ObjectCalls.ptrcallWithTwoLongUInt32RIDListPackedInt64ListArgs(drawListBindVertexBuffersFormatBind, segment, drawList, vertexFormat, vertexCount, vertexBuffers, offsets)
    }

    /**
     * Binds `index_array` to the specified `draw_list`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_bind_index_array
     */
    fun drawListBindIndexArray(drawList: Long, indexArray: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(drawListBindIndexArrayBind, segment, drawList, indexArray)
    }

    /**
     * Sets the push constant data to `buffer` for the specified `draw_list`. The shader determines how
     * this binary data is used. The buffer's size in bytes must also be specified in `size_bytes`
     * (this can be obtained by calling the `PackedByteArray.size` method on the passed `buffer`).
     *
     * Generated from Godot docs: RenderingDevice.draw_list_set_push_constant
     */
    fun drawListSetPushConstant(drawList: Long, buffer: ByteArray, sizeBytes: Long) {
        ObjectCalls.ptrcallWithLongByteArrayUInt32Args(drawListSetPushConstantBind, segment, drawList, buffer, sizeBytes)
    }

    /**
     * Submits `draw_list` for rendering on the GPU. This is the raster equivalent to
     * `compute_list_dispatch`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_draw
     */
    fun drawListDraw(drawList: Long, useIndices: Boolean, instances: Long, proceduralVertexCount: Long = 0L) {
        ObjectCalls.ptrcallWithLongBoolTwoUInt32Args(drawListDrawBind, segment, drawList, useIndices, instances, proceduralVertexCount)
    }

    /**
     * Submits `draw_list` for rendering on the GPU with the given parameters stored in the `buffer` at
     * `offset`. Parameters being integers: vertex count, instance count, first vertex, first instance.
     * And when using indices: index count, instance count, first index, vertex offset, first instance.
     * Buffer must have been created with `StorageBufferUsage.INDIRECT` flag.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_draw_indirect
     */
    fun drawListDrawIndirect(drawList: Long, useIndices: Boolean, buffer: RID, offset: Long = 0L, drawCount: Long = 1L, stride: Long = 0L) {
        ObjectCalls.ptrcallWithLongBoolRIDThreeUInt32Args(drawListDrawIndirectBind, segment, drawList, useIndices, buffer, offset, drawCount, stride)
    }

    /**
     * Creates a scissor rectangle and enables it for the specified `draw_list`. Scissor rectangles are
     * used for clipping by discarding fragments that fall outside a specified rectangular portion of
     * the screen. See also `draw_list_disable_scissor`. Note: The specified `rect` is automatically
     * intersected with the screen's dimensions, which means it cannot exceed the screen's dimensions.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_enable_scissor
     */
    fun drawListEnableScissor(drawList: Long, rect: Rect2) {
        ObjectCalls.ptrcallWithLongRect2Args(drawListEnableScissorBind, segment, drawList, rect)
    }

    /**
     * Removes and disables the scissor rectangle for the specified `draw_list`. See also
     * `draw_list_enable_scissor`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_disable_scissor
     */
    fun drawListDisableScissor(drawList: Long) {
        ObjectCalls.ptrcallWithLongArg(drawListDisableScissorBind, segment, drawList)
    }

    /**
     * Switches to the next draw pass.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_switch_to_next_pass
     */
    fun drawListSwitchToNextPass(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(drawListSwitchToNextPassBind, segment)
    }

    /**
     * This method does nothing and always returns an empty `PackedInt64Array`.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_switch_to_next_pass_split
     */
    fun drawListSwitchToNextPassSplit(splits: Long): List<Long> {
        return ObjectCalls.ptrcallWithUInt32ArgRetPackedInt64List(drawListSwitchToNextPassSplitBind, segment, splits)
    }

    /**
     * Finishes a list of raster drawing commands created with the `draw_*` methods.
     *
     * Generated from Godot docs: RenderingDevice.draw_list_end
     */
    fun drawListEnd() {
        ObjectCalls.ptrcallNoArgs(drawListEndBind, segment)
    }

    /**
     * Starts a list of compute commands created with the `compute_*` methods. The returned value
     * should be passed to other `compute_list_*` functions. Multiple compute lists cannot be created
     * at the same time; you must finish the previous compute list first using `compute_list_end`.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_begin
     */
    fun computeListBegin(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(computeListBeginBind, segment)
    }

    /**
     * Tells the GPU what compute pipeline to use when processing the compute list. If the shader has
     * changed since the last time this function was called, Godot will unbind all descriptor sets and
     * will re-bind them inside `compute_list_dispatch`.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_bind_compute_pipeline
     */
    fun computeListBindComputePipeline(computeList: Long, computePipeline: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(computeListBindComputePipelineBind, segment, computeList, computePipeline)
    }

    /**
     * Sets the push constant data to `buffer` for the specified `compute_list`. The shader determines
     * how this binary data is used. The buffer's size in bytes must also be specified in `size_bytes`
     * (this can be obtained by calling the `PackedByteArray.size` method on the passed `buffer`).
     *
     * Generated from Godot docs: RenderingDevice.compute_list_set_push_constant
     */
    fun computeListSetPushConstant(computeList: Long, buffer: ByteArray, sizeBytes: Long) {
        ObjectCalls.ptrcallWithLongByteArrayUInt32Args(computeListSetPushConstantBind, segment, computeList, buffer, sizeBytes)
    }

    /**
     * Binds the `uniform_set` to this `compute_list`. Godot ensures that all textures in the uniform
     * set have the correct Vulkan access masks. If Godot had to change access masks of textures, it
     * will raise a Vulkan image memory barrier.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_bind_uniform_set
     */
    fun computeListBindUniformSet(computeList: Long, uniformSet: RID, setIndex: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(computeListBindUniformSetBind, segment, computeList, uniformSet, setIndex)
    }

    /**
     * Submits the compute list for processing on the GPU. This is the compute equivalent to
     * `draw_list_draw`.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_dispatch
     */
    fun computeListDispatch(computeList: Long, xGroups: Long, yGroups: Long, zGroups: Long) {
        ObjectCalls.ptrcallWithLongAndThreeUInt32Args(computeListDispatchBind, segment, computeList, xGroups, yGroups, zGroups)
    }

    /**
     * Submits the compute list for processing on the GPU with the given group counts stored in the
     * `buffer` at `offset`. Buffer must have been created with `StorageBufferUsage.INDIRECT` flag.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_dispatch_indirect
     */
    fun computeListDispatchIndirect(computeList: Long, buffer: RID, offset: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(computeListDispatchIndirectBind, segment, computeList, buffer, offset)
    }

    /**
     * Raises a Vulkan compute barrier in the specified `compute_list`.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_add_barrier
     */
    fun computeListAddBarrier(computeList: Long) {
        ObjectCalls.ptrcallWithLongArg(computeListAddBarrierBind, segment, computeList)
    }

    /**
     * Finishes a list of compute commands created with the `compute_*` methods.
     *
     * Generated from Godot docs: RenderingDevice.compute_list_end
     */
    fun computeListEnd() {
        ObjectCalls.ptrcallNoArgs(computeListEndBind, segment)
    }

    /**
     * Starts a list of raytracing commands. The returned value should be passed to other
     * `raytracing_list_*` functions. Multiple raytracing lists cannot be created at the same time; you
     * must finish the previous raytracing list first using `raytracing_list_end`.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_list_begin
     */
    fun raytracingListBegin(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(raytracingListBeginBind, segment)
    }

    /**
     * Binds `raytracing_pipeline` to the specified `raytracing_list`.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_list_bind_raytracing_pipeline
     */
    fun raytracingListBindRaytracingPipeline(raytracingList: Long, raytracingPipeline: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(raytracingListBindRaytracingPipelineBind, segment, raytracingList, raytracingPipeline)
    }

    /**
     * Sets the push constant data to `buffer` for the specified `raytracing_list`. The shader
     * determines how this binary data is used. The buffer's size in bytes must also be specified in
     * `size_bytes` (this can be obtained by calling the `PackedByteArray.size` method on the passed
     * `buffer`).
     *
     * Generated from Godot docs: RenderingDevice.raytracing_list_set_push_constant
     */
    fun raytracingListSetPushConstant(raytracingList: Long, buffer: ByteArray, sizeBytes: Long) {
        ObjectCalls.ptrcallWithLongByteArrayUInt32Args(raytracingListSetPushConstantBind, segment, raytracingList, buffer, sizeBytes)
    }

    /**
     * Binds the `uniform_set` to this `raytracing_list`.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_list_bind_uniform_set
     */
    fun raytracingListBindUniformSet(raytracingList: Long, uniformSet: RID, setIndex: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(raytracingListBindUniformSetBind, segment, raytracingList, uniformSet, setIndex)
    }

    /**
     * Initializes a raytracing dispatch for `raytracing_list`, launching `width` × `height` × `depth`
     * rays. `raygen_shader_index` selects the ray generation shader from the pipeline bound with
     * `raytracing_list_bind_raytracing_pipeline`. `hit_sbt` must use the same pipeline bound to
     * `raytracing_list`.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_list_trace_rays
     */
    fun raytracingListTraceRays(raytracingList: Long, raygenShaderIndex: Long, hitSbt: RID, width: Long, height: Long, depth: Long) {
        ObjectCalls.ptrcallWithLongUInt32RIDThreeUInt32Args(raytracingListTraceRaysBind, segment, raytracingList, raygenShaderIndex, hitSbt, width, height, depth)
    }

    /**
     * Finishes a list of raytracing commands created with the `raytracing_*` methods.
     *
     * Generated from Godot docs: RenderingDevice.raytracing_list_end
     */
    fun raytracingListEnd() {
        ObjectCalls.ptrcallNoArgs(raytracingListEndBind, segment)
    }

    /**
     * Tries to free an object in the RenderingDevice. To avoid memory leaks, this should be called
     * after using an object as memory management does not occur automatically when using
     * RenderingDevice directly.
     *
     * Generated from Godot docs: RenderingDevice.free_rid
     */
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, segment, rid)
    }

    /**
     * Creates a timestamp marker with the specified `name`. This is used for performance reporting
     * with the `get_captured_timestamp_cpu_time`, `get_captured_timestamp_gpu_time` and
     * `get_captured_timestamp_name` methods.
     *
     * Generated from Godot docs: RenderingDevice.capture_timestamp
     */
    fun captureTimestamp(name: String) {
        ObjectCalls.ptrcallWithStringArg(captureTimestampBind, segment, name)
    }

    /**
     * Returns the total number of timestamps (rendering steps) available for profiling.
     *
     * Generated from Godot docs: RenderingDevice.get_captured_timestamps_count
     */
    fun getCapturedTimestampsCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCapturedTimestampsCountBind, segment)
    }

    /**
     * Returns the index of the last frame rendered that has rendering timestamps available for
     * querying.
     *
     * Generated from Godot docs: RenderingDevice.get_captured_timestamps_frame
     */
    fun getCapturedTimestampsFrame(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCapturedTimestampsFrameBind, segment)
    }

    /**
     * Returns the timestamp in GPU time for the rendering step specified by `index` (in microseconds
     * since the engine started). See also `get_captured_timestamp_cpu_time` and `capture_timestamp`.
     *
     * Generated from Godot docs: RenderingDevice.get_captured_timestamp_gpu_time
     */
    fun getCapturedTimestampGpuTime(index: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getCapturedTimestampGpuTimeBind, segment, index)
    }

    /**
     * Returns the timestamp in CPU time for the rendering step specified by `index` (in microseconds
     * since the engine started). See also `get_captured_timestamp_gpu_time` and `capture_timestamp`.
     *
     * Generated from Godot docs: RenderingDevice.get_captured_timestamp_cpu_time
     */
    fun getCapturedTimestampCpuTime(index: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getCapturedTimestampCpuTimeBind, segment, index)
    }

    /**
     * Returns the timestamp's name for the rendering step specified by `index`. See also
     * `capture_timestamp`.
     *
     * Generated from Godot docs: RenderingDevice.get_captured_timestamp_name
     */
    fun getCapturedTimestampName(index: Long): String {
        return ObjectCalls.ptrcallWithUInt32ArgRetString(getCapturedTimestampNameBind, segment, index)
    }

    /**
     * Returns `true` if the `feature` is supported by the GPU.
     *
     * Generated from Godot docs: RenderingDevice.has_feature
     */
    fun hasFeature(feature: RenderingDevice.Features): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, segment, feature.value)
    }

    /**
     * Returns the value of the specified `limit`. This limit varies depending on the current graphics
     * hardware (and sometimes the driver version). If the given limit is exceeded, rendering errors
     * will occur. Limits for various graphics hardware can be found in the Vulkan Hardware Database
     * (https://vulkan.gpuinfo.org/).
     *
     * Generated from Godot docs: RenderingDevice.limit_get
     */
    fun limitGet(limit: RenderingDevice.Limit): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(limitGetBind, segment, limit.value)
    }

    /**
     * Returns the frame count kept by the graphics API. Higher values result in higher input lag, but
     * with more consistent throughput. For the main `RenderingDevice`, frames are cycled (usually 3
     * with triple-buffered V-Sync enabled). However, local `RenderingDevice`s only have 1 frame.
     *
     * Generated from Godot docs: RenderingDevice.get_frame_delay
     */
    fun getFrameDelay(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrameDelayBind, segment)
    }

    /**
     * Pushes the frame setup and draw command buffers then marks the local device as currently
     * processing (which allows calling `sync`). Note: Only available in local RenderingDevices.
     *
     * Generated from Godot docs: RenderingDevice.submit
     */
    fun submit() {
        ObjectCalls.ptrcallNoArgs(submitBind, segment)
    }

    /**
     * Forces a synchronization between the CPU and GPU, which may be required in certain cases. Only
     * call this when needed, as CPU-GPU synchronization has a performance cost. Note: Only available
     * in local RenderingDevices. Note: `sync` can only be called after a `submit`.
     *
     * Generated from Godot docs: RenderingDevice.sync
     */
    fun sync() {
        ObjectCalls.ptrcallNoArgs(syncBind, segment)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: RenderingDevice.barrier
     */
    fun barrier(from: RenderingDevice.BarrierMask = RenderingDevice.BarrierMask.ALL_BARRIERS, to: RenderingDevice.BarrierMask = RenderingDevice.BarrierMask.ALL_BARRIERS) {
        ObjectCalls.ptrcallWithTwoLongArgs(barrierBind, segment, from.value, to.value)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: RenderingDevice.full_barrier
     */
    fun fullBarrier() {
        ObjectCalls.ptrcallNoArgs(fullBarrierBind, segment)
    }

    /**
     * Create a new local `RenderingDevice`. This is most useful for performing compute operations on
     * the GPU independently from the rest of the engine.
     *
     * Generated from Godot docs: RenderingDevice.create_local_device
     */
    fun createLocalDevice(): RenderingDevice? {
        return RenderingDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(createLocalDeviceBind, segment))
    }

    /**
     * Sets the resource name for `id` to `name`. This is used for debugging with third-party tools
     * such as RenderDoc (https://renderdoc.org/). The following types of resources can be named:
     * texture, sampler, vertex buffer, index buffer, uniform buffer, texture buffer, storage buffer,
     * uniform set buffer, shader, render pipeline and compute pipeline. Framebuffers cannot be named.
     * Attempting to name an incompatible resource type will print an error. Note: Resource names are
     * only set when the engine runs in verbose mode (`OS.is_stdout_verbose` = `true`), or when using
     * an engine build compiled with the `dev_mode=yes` SCons option. The graphics driver must also
     * support the `VK_EXT_DEBUG_UTILS_EXTENSION_NAME` Vulkan extension for named resources to work.
     *
     * Generated from Godot docs: RenderingDevice.set_resource_name
     */
    fun setResourceName(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(setResourceNameBind, segment, id, name)
    }

    /**
     * Create a command buffer debug label region that can be displayed in third-party tools such as
     * RenderDoc (https://renderdoc.org/). All regions must be ended with a `draw_command_end_label`
     * call. When viewed from the linear series of submissions to a single queue, calls to
     * `draw_command_begin_label` and `draw_command_end_label` must be matched and balanced. The
     * `VK_EXT_DEBUG_UTILS_EXTENSION_NAME` Vulkan extension must be available and enabled for command
     * buffer debug label region to work. See also `draw_command_end_label`.
     *
     * Generated from Godot docs: RenderingDevice.draw_command_begin_label
     */
    fun drawCommandBeginLabel(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(drawCommandBeginLabelBind, segment, name, color)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: RenderingDevice.draw_command_insert_label
     */
    fun drawCommandInsertLabel(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(drawCommandInsertLabelBind, segment, name, color)
    }

    /**
     * Ends the command buffer debug label region started by a `draw_command_begin_label` call.
     *
     * Generated from Godot docs: RenderingDevice.draw_command_end_label
     */
    fun drawCommandEndLabel() {
        ObjectCalls.ptrcallNoArgs(drawCommandEndLabelBind, segment)
    }

    /**
     * Returns the vendor of the video adapter (e.g. "NVIDIA Corporation"). Equivalent to
     * `RenderingServer.get_video_adapter_vendor`. See also `get_device_name`.
     *
     * Generated from Godot docs: RenderingDevice.get_device_vendor_name
     */
    fun getDeviceVendorName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDeviceVendorNameBind, segment)
    }

    /**
     * Returns the name of the video adapter (e.g. "GeForce GTX 1080/PCIe/SSE2"). Equivalent to
     * `RenderingServer.get_video_adapter_name`. See also `get_device_vendor_name`.
     *
     * Generated from Godot docs: RenderingDevice.get_device_name
     */
    fun getDeviceName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDeviceNameBind, segment)
    }

    /**
     * Returns the universally unique identifier for the pipeline cache. This is used to cache shader
     * files on disk, which avoids shader recompilations on subsequent engine runs. This UUID varies
     * depending on the graphics card model, but also the driver version. Therefore, updating graphics
     * drivers will invalidate the shader cache.
     *
     * Generated from Godot docs: RenderingDevice.get_device_pipeline_cache_uuid
     */
    fun getDevicePipelineCacheUuid(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDevicePipelineCacheUuidBind, segment)
    }

    /**
     * Returns the memory usage in bytes corresponding to the given `type`. When using Vulkan, these
     * statistics are calculated by Vulkan Memory Allocator
     * (https://github.com/GPUOpen-LibrariesAndSDKs/VulkanMemoryAllocator).
     *
     * Generated from Godot docs: RenderingDevice.get_memory_usage
     */
    fun getMemoryUsage(type: RenderingDevice.MemoryType): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getMemoryUsageBind, segment, type.value)
    }

    /**
     * Returns the unique identifier of the driver `resource` for the specified `rid`. Some driver
     * resource types ignore the specified `rid`. `index` is always ignored but must be specified
     * anyway.
     *
     * Generated from Godot docs: RenderingDevice.get_driver_resource
     */
    fun getDriverResource(resource: RenderingDevice.DriverResource, rid: RID, index: Long): Long {
        return ObjectCalls.ptrcallWithLongRIDLongArgsRetLong(getDriverResourceBind, segment, resource.value, rid, index)
    }

    /**
     * Returns a string with a performance report from the past frame. Updates every frame.
     *
     * Generated from Godot docs: RenderingDevice.get_perf_report
     */
    fun getPerfReport(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPerfReportBind, segment)
    }

    /**
     * Returns string report in CSV format using the following methods: - `get_tracked_object_name` -
     * `get_tracked_object_type_count` - `get_driver_total_memory` - `get_driver_allocation_count` -
     * `get_driver_memory_by_object_type` - `get_driver_allocs_by_object_type` -
     * `get_device_total_memory` - `get_device_allocation_count` - `get_device_memory_by_object_type` -
     * `get_device_allocs_by_object_type` This is only used by Vulkan in debug builds. Godot must also
     * be started with the `--extra-gpu-memory-tracking` command line argument
     * ($DOCS_URL/tutorials/editor/command_line_tutorial.html).
     *
     * Generated from Godot docs: RenderingDevice.get_driver_and_device_memory_report
     */
    fun getDriverAndDeviceMemoryReport(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDriverAndDeviceMemoryReportBind, segment)
    }

    /**
     * Returns the name of the type of object for the given `type_index`. This value must be in range
     * `[0; get_tracked_object_type_count - 1]`. If `get_tracked_object_type_count` is 0, then type
     * argument is ignored and always returns the same string. The return value is important because it
     * gives meaning to the types passed to `get_driver_memory_by_object_type`,
     * `get_driver_allocs_by_object_type`, `get_device_memory_by_object_type`, and
     * `get_device_allocs_by_object_type`. Examples of strings it can return (not exhaustive): -
     * DEVICE_MEMORY - PIPELINE_CACHE - SWAPCHAIN_KHR - COMMAND_POOL Thus if e.g.
     * `get_tracked_object_name(5)` returns "COMMAND_POOL", then `get_device_memory_by_object_type(5)`
     * returns the bytes used by the GPU for command pools. This is only used by Vulkan in debug
     * builds. Godot must also be started with the `--extra-gpu-memory-tracking` command line argument
     * ($DOCS_URL/tutorials/editor/command_line_tutorial.html).
     *
     * Generated from Godot docs: RenderingDevice.get_tracked_object_name
     */
    fun getTrackedObjectName(typeIndex: Long): String {
        return ObjectCalls.ptrcallWithUInt32ArgRetString(getTrackedObjectNameBind, segment, typeIndex)
    }

    /**
     * Returns how many types of trackable objects there are. This is only used by Vulkan in debug
     * builds. Godot must also be started with the `--extra-gpu-memory-tracking` command line argument
     * ($DOCS_URL/tutorials/editor/command_line_tutorial.html).
     *
     * Generated from Godot docs: RenderingDevice.get_tracked_object_type_count
     */
    fun getTrackedObjectTypeCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackedObjectTypeCountBind, segment)
    }

    /**
     * Returns how much bytes the GPU driver is using for internal driver structures. This is only used
     * by Vulkan in debug builds and can return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_driver_total_memory
     */
    fun getDriverTotalMemory(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDriverTotalMemoryBind, segment)
    }

    /**
     * Returns how many allocations the GPU driver has performed for internal driver structures. This
     * is only used by Vulkan in debug builds and can return 0 when this information is not tracked or
     * unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_driver_allocation_count
     */
    fun getDriverAllocationCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDriverAllocationCountBind, segment)
    }

    /**
     * Same as `get_driver_total_memory` but filtered for a given object type. The type argument must
     * be in range `[0; get_tracked_object_type_count - 1]`. If `get_tracked_object_type_count` is 0,
     * then type argument is ignored and always returns 0. This is only used by Vulkan in debug builds
     * and can return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_driver_memory_by_object_type
     */
    fun getDriverMemoryByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDriverMemoryByObjectTypeBind, segment, type)
    }

    /**
     * Same as `get_driver_allocation_count` but filtered for a given object type. The type argument
     * must be in range `[0; get_tracked_object_type_count - 1]`. If `get_tracked_object_type_count` is
     * 0, then type argument is ignored and always returns 0. This is only used by Vulkan in debug
     * builds and can return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_driver_allocs_by_object_type
     */
    fun getDriverAllocsByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDriverAllocsByObjectTypeBind, segment, type)
    }

    /**
     * Returns how much bytes the GPU is using. This is only used by Vulkan in debug builds and can
     * return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_device_total_memory
     */
    fun getDeviceTotalMemory(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDeviceTotalMemoryBind, segment)
    }

    /**
     * Returns how many allocations the GPU has performed for internal driver structures. This is only
     * used by Vulkan in debug builds and can return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_device_allocation_count
     */
    fun getDeviceAllocationCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDeviceAllocationCountBind, segment)
    }

    /**
     * Same as `get_device_total_memory` but filtered for a given object type. The type argument must
     * be in range `[0; get_tracked_object_type_count - 1]`. If `get_tracked_object_type_count` is 0,
     * then type argument is ignored and always returns 0. This is only used by Vulkan in debug builds
     * and can return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_device_memory_by_object_type
     */
    fun getDeviceMemoryByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDeviceMemoryByObjectTypeBind, segment, type)
    }

    /**
     * Same as `get_device_allocation_count` but filtered for a given object type. The type argument
     * must be in range `[0; get_tracked_object_type_count - 1]`. If `get_tracked_object_type_count` is
     * 0, then type argument is ignored and always returns 0. This is only used by Vulkan in debug
     * builds and can return 0 when this information is not tracked or unknown.
     *
     * Generated from Godot docs: RenderingDevice.get_device_allocs_by_object_type
     */
    fun getDeviceAllocsByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDeviceAllocsByObjectTypeBind, segment, type)
    }

    /**
     * Godot's `RenderingDevice.DeviceType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.DeviceType.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.DeviceType
     */
    @JvmInline
    value class DeviceType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Rendering device type does not match any of the other enum values or is unknown.
             *
             * Generated from Godot docs: RenderingDevice.DEVICE_TYPE_OTHER
             */
            val OTHER: DeviceType get() = DeviceType(0L)
            /**
             * Rendering device is an integrated GPU, which is typically (but not always) slower than dedicated
             * GPUs (`DeviceType.DISCRETE_GPU`). On Android and iOS, the rendering device type is always
             * considered to be `DeviceType.INTEGRATED_GPU`.
             *
             * Generated from Godot docs: RenderingDevice.DEVICE_TYPE_INTEGRATED_GPU
             */
            val INTEGRATED_GPU: DeviceType get() = DeviceType(1L)
            /**
             * Rendering device is a dedicated GPU, which is typically (but not always) faster than integrated
             * GPUs (`DeviceType.INTEGRATED_GPU`).
             *
             * Generated from Godot docs: RenderingDevice.DEVICE_TYPE_DISCRETE_GPU
             */
            val DISCRETE_GPU: DeviceType get() = DeviceType(2L)
            /**
             * Rendering device is an emulated GPU in a virtual environment. This is typically much slower than
             * the host GPU, which means the expected performance level on a dedicated GPU will be roughly
             * equivalent to `DeviceType.INTEGRATED_GPU`. Virtual machine GPU passthrough (such as VFIO) will
             * not report the device type as `DeviceType.VIRTUAL_GPU`. Instead, the host GPU's device type will
             * be reported as if the GPU was not emulated.
             *
             * Generated from Godot docs: RenderingDevice.DEVICE_TYPE_VIRTUAL_GPU
             */
            val VIRTUAL_GPU: DeviceType get() = DeviceType(3L)
            /**
             * Rendering device is provided by software emulation (such as Lavapipe or SwiftShader
             * (https://github.com/google/swiftshader)). This is the slowest kind of rendering device
             * available; it's typically much slower than `DeviceType.INTEGRATED_GPU`.
             *
             * Generated from Godot docs: RenderingDevice.DEVICE_TYPE_CPU
             */
            val CPU: DeviceType get() = DeviceType(4L)
            /**
             * Represents the size of the `DeviceType` enum.
             *
             * Generated from Godot docs: RenderingDevice.DEVICE_TYPE_MAX
             */
            val MAX: DeviceType get() = DeviceType(5L)
        }
    }

    /**
     * Godot's `RenderingDevice.DriverResource` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.DriverResource.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.DriverResource
     */
    @JvmInline
    value class DriverResource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Specific device object based on a physical device (`rid` parameter is ignored). - Vulkan: Vulkan
             * device driver resource (`VkDevice`). - D3D12: D3D12 device driver resource (`ID3D12Device`). -
             * Metal: Metal device driver resource (`MTLDevice`).
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_LOGICAL_DEVICE
             */
            val LOGICAL_DEVICE: DriverResource get() = DriverResource(0L)
            /**
             * Physical device the specific logical device is based on (`rid` parameter is ignored). - Vulkan:
             * `VkPhysicalDevice`. - D3D12: `IDXGIAdapter`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_PHYSICAL_DEVICE
             */
            val PHYSICAL_DEVICE: DriverResource get() = DriverResource(1L)
            /**
             * Top-most graphics API entry object (`rid` parameter is ignored). - Vulkan: `VkInstance`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_TOPMOST_OBJECT
             */
            val TOPMOST_OBJECT: DriverResource get() = DriverResource(2L)
            /**
             * The main graphics-compute command queue (`rid` parameter is ignored). - Vulkan: `VkQueue`. -
             * D3D12: `ID3D12CommandQueue`. - Metal: `MTLCommandQueue`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_COMMAND_QUEUE
             */
            val COMMAND_QUEUE: DriverResource get() = DriverResource(3L)
            /**
             * The specific family the main queue belongs to (`rid` parameter is ignored). - Vulkan: The queue
             * family index, a `uint32_t`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_QUEUE_FAMILY
             */
            val QUEUE_FAMILY: DriverResource get() = DriverResource(4L)
            /**
             * - Vulkan: `VkImage`. - D3D12: `ID3D12Resource`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_TEXTURE
             */
            val TEXTURE: DriverResource get() = DriverResource(5L)
            /**
             * The view of an owned or shared texture. - Vulkan: `VkImageView`. - D3D12: `ID3D12Resource`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_TEXTURE_VIEW
             */
            val TEXTURE_VIEW: DriverResource get() = DriverResource(6L)
            /**
             * The native id of the data format of the texture. - Vulkan: `VkFormat`. - D3D12: `DXGI_FORMAT`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_TEXTURE_DATA_FORMAT
             */
            val TEXTURE_DATA_FORMAT: DriverResource get() = DriverResource(7L)
            /**
             * - Vulkan: `VkSampler`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_SAMPLER
             */
            val SAMPLER: DriverResource get() = DriverResource(8L)
            /**
             * - Vulkan: `VkDescriptorSet`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_UNIFORM_SET
             */
            val UNIFORM_SET: DriverResource get() = DriverResource(9L)
            /**
             * Buffer of any kind of (storage, vertex, etc.). - Vulkan: `VkBuffer`. - D3D12: `ID3D12Resource`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_BUFFER
             */
            val BUFFER: DriverResource get() = DriverResource(10L)
            /**
             * - Vulkan: `VkPipeline`. - Metal: `MTLComputePipelineState`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_COMPUTE_PIPELINE
             */
            val COMPUTE_PIPELINE: DriverResource get() = DriverResource(11L)
            /**
             * - Vulkan: `VkPipeline`. - Metal: `MTLRenderPipelineState`.
             *
             * Generated from Godot docs: RenderingDevice.DRIVER_RESOURCE_RENDER_PIPELINE
             */
            val RENDER_PIPELINE: DriverResource get() = DriverResource(12L)
            val VULKAN_DEVICE: DriverResource get() = DriverResource(0L)
            val VULKAN_PHYSICAL_DEVICE: DriverResource get() = DriverResource(1L)
            val VULKAN_INSTANCE: DriverResource get() = DriverResource(2L)
            val VULKAN_QUEUE: DriverResource get() = DriverResource(3L)
            val VULKAN_QUEUE_FAMILY_INDEX: DriverResource get() = DriverResource(4L)
            val VULKAN_IMAGE: DriverResource get() = DriverResource(5L)
            val VULKAN_IMAGE_VIEW: DriverResource get() = DriverResource(6L)
            val VULKAN_IMAGE_NATIVE_TEXTURE_FORMAT: DriverResource get() = DriverResource(7L)
            val VULKAN_SAMPLER: DriverResource get() = DriverResource(8L)
            val VULKAN_DESCRIPTOR_SET: DriverResource get() = DriverResource(9L)
            val VULKAN_BUFFER: DriverResource get() = DriverResource(10L)
            val VULKAN_COMPUTE_PIPELINE: DriverResource get() = DriverResource(11L)
            val VULKAN_RENDER_PIPELINE: DriverResource get() = DriverResource(12L)
        }
    }

    /**
     * Godot's `RenderingDevice.DataFormat` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.DataFormat.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.DataFormat
     */
    @JvmInline
    value class DataFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 4-bit-per-channel red/green channel data format, packed into 8 bits. Values are in the `[0.0,
             * 1.0]` range. Note: More information on all data formats can be found on the Identification of
             * formats
             * (https://registry.khronos.org/vulkan/specs/1.1/html/vkspec.html#_identification_of_formats)
             * section of the Vulkan specification, as well as the VkFormat
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/man/html/VkFormat.html) enum.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R4G4_UNORM_PACK8
             */
            val R4G4_UNORM_PACK8: DataFormat get() = DataFormat(0L)
            /**
             * 4-bit-per-channel red/green/blue/alpha channel data format, packed into 16 bits. Values are in
             * the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R4G4B4A4_UNORM_PACK16
             */
            val R4G4B4A4_UNORM_PACK16: DataFormat get() = DataFormat(1L)
            /**
             * 4-bit-per-channel blue/green/red/alpha channel data format, packed into 16 bits. Values are in
             * the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B4G4R4A4_UNORM_PACK16
             */
            val B4G4R4A4_UNORM_PACK16: DataFormat get() = DataFormat(2L)
            /**
             * Red/green/blue channel data format with 5 bits of red, 6 bits of green and 5 bits of blue,
             * packed into 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R5G6B5_UNORM_PACK16
             */
            val R5G6B5_UNORM_PACK16: DataFormat get() = DataFormat(3L)
            /**
             * Blue/green/red channel data format with 5 bits of blue, 6 bits of green and 5 bits of red,
             * packed into 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B5G6R5_UNORM_PACK16
             */
            val B5G6R5_UNORM_PACK16: DataFormat get() = DataFormat(4L)
            /**
             * Red/green/blue/alpha channel data format with 5 bits of red, 6 bits of green, 5 bits of blue and
             * 1 bit of alpha, packed into 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R5G5B5A1_UNORM_PACK16
             */
            val R5G5B5A1_UNORM_PACK16: DataFormat get() = DataFormat(5L)
            /**
             * Blue/green/red/alpha channel data format with 5 bits of blue, 6 bits of green, 5 bits of red and
             * 1 bit of alpha, packed into 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B5G5R5A1_UNORM_PACK16
             */
            val B5G5R5A1_UNORM_PACK16: DataFormat get() = DataFormat(6L)
            /**
             * Alpha/red/green/blue channel data format with 1 bit of alpha, 5 bits of red, 6 bits of green and
             * 5 bits of blue, packed into 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A1R5G5B5_UNORM_PACK16
             */
            val A1R5G5B5_UNORM_PACK16: DataFormat get() = DataFormat(7L)
            /**
             * 8-bit-per-channel unsigned floating-point red channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_UNORM
             */
            val R8_UNORM: DataFormat get() = DataFormat(8L)
            /**
             * 8-bit-per-channel signed floating-point red channel data format with normalized value. Values
             * are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_SNORM
             */
            val R8_SNORM: DataFormat get() = DataFormat(9L)
            /**
             * 8-bit-per-channel unsigned floating-point red channel data format with scaled value (value is
             * converted from integer to float). Values are in the `[0.0, 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_USCALED
             */
            val R8_USCALED: DataFormat get() = DataFormat(10L)
            /**
             * 8-bit-per-channel signed floating-point red channel data format with scaled value (value is
             * converted from integer to float). Values are in the `[-127.0, 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_SSCALED
             */
            val R8_SSCALED: DataFormat get() = DataFormat(11L)
            /**
             * 8-bit-per-channel unsigned integer red channel data format. Values are in the `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_UINT
             */
            val R8_UINT: DataFormat get() = DataFormat(12L)
            /**
             * 8-bit-per-channel signed integer red channel data format. Values are in the `[-127, 127]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_SINT
             */
            val R8_SINT: DataFormat get() = DataFormat(13L)
            /**
             * 8-bit-per-channel unsigned floating-point red channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8_SRGB
             */
            val R8_SRGB: DataFormat get() = DataFormat(14L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green channel data format with normalized value.
             * Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_UNORM
             */
            val R8G8_UNORM: DataFormat get() = DataFormat(15L)
            /**
             * 8-bit-per-channel signed floating-point red/green channel data format with normalized value.
             * Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_SNORM
             */
            val R8G8_SNORM: DataFormat get() = DataFormat(16L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green channel data format with scaled value (value
             * is converted from integer to float). Values are in the `[0.0, 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_USCALED
             */
            val R8G8_USCALED: DataFormat get() = DataFormat(17L)
            /**
             * 8-bit-per-channel signed floating-point red/green channel data format with scaled value (value
             * is converted from integer to float). Values are in the `[-127.0, 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_SSCALED
             */
            val R8G8_SSCALED: DataFormat get() = DataFormat(18L)
            /**
             * 8-bit-per-channel unsigned integer red/green channel data format. Values are in the `[0, 255]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_UINT
             */
            val R8G8_UINT: DataFormat get() = DataFormat(19L)
            /**
             * 8-bit-per-channel signed integer red/green channel data format. Values are in the `[-127, 127]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_SINT
             */
            val R8G8_SINT: DataFormat get() = DataFormat(20L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green channel data format with normalized value
             * and nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8_SRGB
             */
            val R8G8_SRGB: DataFormat get() = DataFormat(21L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green/blue channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_UNORM
             */
            val R8G8B8_UNORM: DataFormat get() = DataFormat(22L)
            /**
             * 8-bit-per-channel signed floating-point red/green/blue channel data format with normalized
             * value. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_SNORM
             */
            val R8G8B8_SNORM: DataFormat get() = DataFormat(23L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green/blue channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[0.0, 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_USCALED
             */
            val R8G8B8_USCALED: DataFormat get() = DataFormat(24L)
            /**
             * 8-bit-per-channel signed floating-point red/green/blue channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[-127.0, 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_SSCALED
             */
            val R8G8B8_SSCALED: DataFormat get() = DataFormat(25L)
            /**
             * 8-bit-per-channel unsigned integer red/green/blue channel data format. Values are in the `[0,
             * 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_UINT
             */
            val R8G8B8_UINT: DataFormat get() = DataFormat(26L)
            /**
             * 8-bit-per-channel signed integer red/green/blue channel data format. Values are in the `[-127,
             * 127]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_SINT
             */
            val R8G8B8_SINT: DataFormat get() = DataFormat(27L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green/blue channel data format with normalized
             * value and nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8_SRGB
             */
            val R8G8B8_SRGB: DataFormat get() = DataFormat(28L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_UNORM
             */
            val B8G8R8_UNORM: DataFormat get() = DataFormat(29L)
            /**
             * 8-bit-per-channel signed floating-point blue/green/red channel data format with normalized
             * value. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_SNORM
             */
            val B8G8R8_SNORM: DataFormat get() = DataFormat(30L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[0.0, 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_USCALED
             */
            val B8G8R8_USCALED: DataFormat get() = DataFormat(31L)
            /**
             * 8-bit-per-channel signed floating-point blue/green/red channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[-127.0, 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_SSCALED
             */
            val B8G8R8_SSCALED: DataFormat get() = DataFormat(32L)
            /**
             * 8-bit-per-channel unsigned integer blue/green/red channel data format. Values are in the `[0,
             * 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_UINT
             */
            val B8G8R8_UINT: DataFormat get() = DataFormat(33L)
            /**
             * 8-bit-per-channel signed integer blue/green/red channel data format. Values are in the `[-127,
             * 127]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_SINT
             */
            val B8G8R8_SINT: DataFormat get() = DataFormat(34L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8_SRGB
             */
            val B8G8R8_SRGB: DataFormat get() = DataFormat(35L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green/blue/alpha channel data format with
             * normalized value. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_UNORM
             */
            val R8G8B8A8_UNORM: DataFormat get() = DataFormat(36L)
            /**
             * 8-bit-per-channel signed floating-point red/green/blue/alpha channel data format with normalized
             * value. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_SNORM
             */
            val R8G8B8A8_SNORM: DataFormat get() = DataFormat(37L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green/blue/alpha channel data format with scaled
             * value (value is converted from integer to float). Values are in the `[0.0, 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_USCALED
             */
            val R8G8B8A8_USCALED: DataFormat get() = DataFormat(38L)
            /**
             * 8-bit-per-channel signed floating-point red/green/blue/alpha channel data format with scaled
             * value (value is converted from integer to float). Values are in the `[-127.0, 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_SSCALED
             */
            val R8G8B8A8_SSCALED: DataFormat get() = DataFormat(39L)
            /**
             * 8-bit-per-channel unsigned integer red/green/blue/alpha channel data format. Values are in the
             * `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_UINT
             */
            val R8G8B8A8_UINT: DataFormat get() = DataFormat(40L)
            /**
             * 8-bit-per-channel signed integer red/green/blue/alpha channel data format. Values are in the
             * `[-127, 127]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_SINT
             */
            val R8G8B8A8_SINT: DataFormat get() = DataFormat(41L)
            /**
             * 8-bit-per-channel unsigned floating-point red/green/blue/alpha channel data format with
             * normalized value and nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R8G8B8A8_SRGB
             */
            val R8G8B8A8_SRGB: DataFormat get() = DataFormat(42L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red/alpha channel data format with
             * normalized value. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_UNORM
             */
            val B8G8R8A8_UNORM: DataFormat get() = DataFormat(43L)
            /**
             * 8-bit-per-channel signed floating-point blue/green/red/alpha channel data format with normalized
             * value. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_SNORM
             */
            val B8G8R8A8_SNORM: DataFormat get() = DataFormat(44L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red/alpha channel data format with scaled
             * value (value is converted from integer to float). Values are in the `[0.0, 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_USCALED
             */
            val B8G8R8A8_USCALED: DataFormat get() = DataFormat(45L)
            /**
             * 8-bit-per-channel signed floating-point blue/green/red/alpha channel data format with scaled
             * value (value is converted from integer to float). Values are in the `[-127.0, 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_SSCALED
             */
            val B8G8R8A8_SSCALED: DataFormat get() = DataFormat(46L)
            /**
             * 8-bit-per-channel unsigned integer blue/green/red/alpha channel data format. Values are in the
             * `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_UINT
             */
            val B8G8R8A8_UINT: DataFormat get() = DataFormat(47L)
            /**
             * 8-bit-per-channel signed integer blue/green/red/alpha channel data format. Values are in the
             * `[-127, 127]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_SINT
             */
            val B8G8R8A8_SINT: DataFormat get() = DataFormat(48L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red/alpha channel data format with
             * normalized value and nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8A8_SRGB
             */
            val B8G8R8A8_SRGB: DataFormat get() = DataFormat(49L)
            /**
             * 8-bit-per-channel unsigned floating-point alpha/red/green/blue channel data format with
             * normalized value, packed in 32 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_UNORM_PACK32
             */
            val A8B8G8R8_UNORM_PACK32: DataFormat get() = DataFormat(50L)
            /**
             * 8-bit-per-channel signed floating-point alpha/red/green/blue channel data format with normalized
             * value, packed in 32 bits. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_SNORM_PACK32
             */
            val A8B8G8R8_SNORM_PACK32: DataFormat get() = DataFormat(51L)
            /**
             * 8-bit-per-channel unsigned floating-point alpha/red/green/blue channel data format with scaled
             * value (value is converted from integer to float), packed in 32 bits. Values are in the `[0.0,
             * 255.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_USCALED_PACK32
             */
            val A8B8G8R8_USCALED_PACK32: DataFormat get() = DataFormat(52L)
            /**
             * 8-bit-per-channel signed floating-point alpha/red/green/blue channel data format with scaled
             * value (value is converted from integer to float), packed in 32 bits. Values are in the `[-127.0,
             * 127.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_SSCALED_PACK32
             */
            val A8B8G8R8_SSCALED_PACK32: DataFormat get() = DataFormat(53L)
            /**
             * 8-bit-per-channel unsigned integer alpha/red/green/blue channel data format, packed in 32 bits.
             * Values are in the `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_UINT_PACK32
             */
            val A8B8G8R8_UINT_PACK32: DataFormat get() = DataFormat(54L)
            /**
             * 8-bit-per-channel signed integer alpha/red/green/blue channel data format, packed in 32 bits.
             * Values are in the `[-127, 127]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_SINT_PACK32
             */
            val A8B8G8R8_SINT_PACK32: DataFormat get() = DataFormat(55L)
            /**
             * 8-bit-per-channel unsigned floating-point alpha/red/green/blue channel data format with
             * normalized value and nonlinear sRGB encoding, packed in 32 bits. Values are in the `[0.0, 1.0]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A8B8G8R8_SRGB_PACK32
             */
            val A8B8G8R8_SRGB_PACK32: DataFormat get() = DataFormat(56L)
            /**
             * Unsigned floating-point alpha/red/green/blue channel data format with normalized value, packed
             * in 32 bits. Format contains 2 bits of alpha, 10 bits of red, 10 bits of green and 10 bits of
             * blue. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2R10G10B10_UNORM_PACK32
             */
            val A2R10G10B10_UNORM_PACK32: DataFormat get() = DataFormat(57L)
            /**
             * Signed floating-point alpha/red/green/blue channel data format with normalized value, packed in
             * 32 bits. Format contains 2 bits of alpha, 10 bits of red, 10 bits of green and 10 bits of blue.
             * Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2R10G10B10_SNORM_PACK32
             */
            val A2R10G10B10_SNORM_PACK32: DataFormat get() = DataFormat(58L)
            /**
             * Unsigned floating-point alpha/red/green/blue channel data format with normalized value, packed
             * in 32 bits. Format contains 2 bits of alpha, 10 bits of red, 10 bits of green and 10 bits of
             * blue. Values are in the `[0.0, 1023.0]` range for red/green/blue and `[0.0, 3.0]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2R10G10B10_USCALED_PACK32
             */
            val A2R10G10B10_USCALED_PACK32: DataFormat get() = DataFormat(59L)
            /**
             * Signed floating-point alpha/red/green/blue channel data format with normalized value, packed in
             * 32 bits. Format contains 2 bits of alpha, 10 bits of red, 10 bits of green and 10 bits of blue.
             * Values are in the `[-511.0, 511.0]` range for red/green/blue and `[-1.0, 1.0]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2R10G10B10_SSCALED_PACK32
             */
            val A2R10G10B10_SSCALED_PACK32: DataFormat get() = DataFormat(60L)
            /**
             * Unsigned integer alpha/red/green/blue channel data format with normalized value, packed in 32
             * bits. Format contains 2 bits of alpha, 10 bits of red, 10 bits of green and 10 bits of blue.
             * Values are in the `[0, 1023]` range for red/green/blue and `[0, 3]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2R10G10B10_UINT_PACK32
             */
            val A2R10G10B10_UINT_PACK32: DataFormat get() = DataFormat(61L)
            /**
             * Signed integer alpha/red/green/blue channel data format with normalized value, packed in 32
             * bits. Format contains 2 bits of alpha, 10 bits of red, 10 bits of green and 10 bits of blue.
             * Values are in the `[-511, 511]` range for red/green/blue and `[-1, 1]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2R10G10B10_SINT_PACK32
             */
            val A2R10G10B10_SINT_PACK32: DataFormat get() = DataFormat(62L)
            /**
             * Unsigned floating-point alpha/blue/green/red channel data format with normalized value, packed
             * in 32 bits. Format contains 2 bits of alpha, 10 bits of blue, 10 bits of green and 10 bits of
             * red. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2B10G10R10_UNORM_PACK32
             */
            val A2B10G10R10_UNORM_PACK32: DataFormat get() = DataFormat(63L)
            /**
             * Signed floating-point alpha/blue/green/red channel data format with normalized value, packed in
             * 32 bits. Format contains 2 bits of alpha, 10 bits of blue, 10 bits of green and 10 bits of red.
             * Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2B10G10R10_SNORM_PACK32
             */
            val A2B10G10R10_SNORM_PACK32: DataFormat get() = DataFormat(64L)
            /**
             * Unsigned floating-point alpha/blue/green/red channel data format with normalized value, packed
             * in 32 bits. Format contains 2 bits of alpha, 10 bits of blue, 10 bits of green and 10 bits of
             * red. Values are in the `[0.0, 1023.0]` range for blue/green/red and `[0.0, 3.0]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2B10G10R10_USCALED_PACK32
             */
            val A2B10G10R10_USCALED_PACK32: DataFormat get() = DataFormat(65L)
            /**
             * Signed floating-point alpha/blue/green/red channel data format with normalized value, packed in
             * 32 bits. Format contains 2 bits of alpha, 10 bits of blue, 10 bits of green and 10 bits of red.
             * Values are in the `[-511.0, 511.0]` range for blue/green/red and `[-1.0, 1.0]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2B10G10R10_SSCALED_PACK32
             */
            val A2B10G10R10_SSCALED_PACK32: DataFormat get() = DataFormat(66L)
            /**
             * Unsigned integer alpha/blue/green/red channel data format with normalized value, packed in 32
             * bits. Format contains 2 bits of alpha, 10 bits of blue, 10 bits of green and 10 bits of red.
             * Values are in the `[0, 1023]` range for blue/green/red and `[0, 3]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2B10G10R10_UINT_PACK32
             */
            val A2B10G10R10_UINT_PACK32: DataFormat get() = DataFormat(67L)
            /**
             * Signed integer alpha/blue/green/red channel data format with normalized value, packed in 32
             * bits. Format contains 2 bits of alpha, 10 bits of blue, 10 bits of green and 10 bits of red.
             * Values are in the `[-511, 511]` range for blue/green/red and `[-1, 1]` for alpha.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_A2B10G10R10_SINT_PACK32
             */
            val A2B10G10R10_SINT_PACK32: DataFormat get() = DataFormat(68L)
            /**
             * 16-bit-per-channel unsigned floating-point red channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_UNORM
             */
            val R16_UNORM: DataFormat get() = DataFormat(69L)
            /**
             * 16-bit-per-channel signed floating-point red channel data format with normalized value. Values
             * are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_SNORM
             */
            val R16_SNORM: DataFormat get() = DataFormat(70L)
            /**
             * 16-bit-per-channel unsigned floating-point red channel data format with scaled value (value is
             * converted from integer to float). Values are in the `[0.0, 65535.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_USCALED
             */
            val R16_USCALED: DataFormat get() = DataFormat(71L)
            /**
             * 16-bit-per-channel signed floating-point red channel data format with scaled value (value is
             * converted from integer to float). Values are in the `[-32767.0, 32767.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_SSCALED
             */
            val R16_SSCALED: DataFormat get() = DataFormat(72L)
            /**
             * 16-bit-per-channel unsigned integer red channel data format. Values are in the `[0.0, 65535]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_UINT
             */
            val R16_UINT: DataFormat get() = DataFormat(73L)
            /**
             * 16-bit-per-channel signed integer red channel data format. Values are in the `[-32767, 32767]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_SINT
             */
            val R16_SINT: DataFormat get() = DataFormat(74L)
            /**
             * 16-bit-per-channel signed floating-point red channel data format with the value stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16_SFLOAT
             */
            val R16_SFLOAT: DataFormat get() = DataFormat(75L)
            /**
             * 16-bit-per-channel unsigned floating-point red/green channel data format with normalized value.
             * Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_UNORM
             */
            val R16G16_UNORM: DataFormat get() = DataFormat(76L)
            /**
             * 16-bit-per-channel signed floating-point red/green channel data format with normalized value.
             * Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_SNORM
             */
            val R16G16_SNORM: DataFormat get() = DataFormat(77L)
            /**
             * 16-bit-per-channel unsigned floating-point red/green channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[0.0, 65535.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_USCALED
             */
            val R16G16_USCALED: DataFormat get() = DataFormat(78L)
            /**
             * 16-bit-per-channel signed floating-point red/green channel data format with scaled value (value
             * is converted from integer to float). Values are in the `[-32767.0, 32767.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_SSCALED
             */
            val R16G16_SSCALED: DataFormat get() = DataFormat(79L)
            /**
             * 16-bit-per-channel unsigned integer red/green channel data format. Values are in the `[0.0,
             * 65535]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_UINT
             */
            val R16G16_UINT: DataFormat get() = DataFormat(80L)
            /**
             * 16-bit-per-channel signed integer red/green channel data format. Values are in the `[-32767,
             * 32767]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_SINT
             */
            val R16G16_SINT: DataFormat get() = DataFormat(81L)
            /**
             * 16-bit-per-channel signed floating-point red/green channel data format with the value stored
             * as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16_SFLOAT
             */
            val R16G16_SFLOAT: DataFormat get() = DataFormat(82L)
            /**
             * 16-bit-per-channel unsigned floating-point red/green/blue channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_UNORM
             */
            val R16G16B16_UNORM: DataFormat get() = DataFormat(83L)
            /**
             * 16-bit-per-channel signed floating-point red/green/blue channel data format with normalized
             * value. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_SNORM
             */
            val R16G16B16_SNORM: DataFormat get() = DataFormat(84L)
            /**
             * 16-bit-per-channel unsigned floating-point red/green/blue channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[0.0, 65535.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_USCALED
             */
            val R16G16B16_USCALED: DataFormat get() = DataFormat(85L)
            /**
             * 16-bit-per-channel signed floating-point red/green/blue channel data format with scaled value
             * (value is converted from integer to float). Values are in the `[-32767.0, 32767.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_SSCALED
             */
            val R16G16B16_SSCALED: DataFormat get() = DataFormat(86L)
            /**
             * 16-bit-per-channel unsigned integer red/green/blue channel data format. Values are in the `[0.0,
             * 65535]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_UINT
             */
            val R16G16B16_UINT: DataFormat get() = DataFormat(87L)
            /**
             * 16-bit-per-channel signed integer red/green/blue channel data format. Values are in the
             * `[-32767, 32767]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_SINT
             */
            val R16G16B16_SINT: DataFormat get() = DataFormat(88L)
            /**
             * 16-bit-per-channel signed floating-point red/green/blue channel data format with the value
             * stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16_SFLOAT
             */
            val R16G16B16_SFLOAT: DataFormat get() = DataFormat(89L)
            /**
             * 16-bit-per-channel unsigned floating-point red/green/blue/alpha channel data format with
             * normalized value. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_UNORM
             */
            val R16G16B16A16_UNORM: DataFormat get() = DataFormat(90L)
            /**
             * 16-bit-per-channel signed floating-point red/green/blue/alpha channel data format with
             * normalized value. Values are in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_SNORM
             */
            val R16G16B16A16_SNORM: DataFormat get() = DataFormat(91L)
            /**
             * 16-bit-per-channel unsigned floating-point red/green/blue/alpha channel data format with scaled
             * value (value is converted from integer to float). Values are in the `[0.0, 65535.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_USCALED
             */
            val R16G16B16A16_USCALED: DataFormat get() = DataFormat(92L)
            /**
             * 16-bit-per-channel signed floating-point red/green/blue/alpha channel data format with scaled
             * value (value is converted from integer to float). Values are in the `[-32767.0, 32767.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_SSCALED
             */
            val R16G16B16A16_SSCALED: DataFormat get() = DataFormat(93L)
            /**
             * 16-bit-per-channel unsigned integer red/green/blue/alpha channel data format. Values are in the
             * `[0.0, 65535]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_UINT
             */
            val R16G16B16A16_UINT: DataFormat get() = DataFormat(94L)
            /**
             * 16-bit-per-channel signed integer red/green/blue/alpha channel data format. Values are in the
             * `[-32767, 32767]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_SINT
             */
            val R16G16B16A16_SINT: DataFormat get() = DataFormat(95L)
            /**
             * 16-bit-per-channel signed floating-point red/green/blue/alpha channel data format with the value
             * stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R16G16B16A16_SFLOAT
             */
            val R16G16B16A16_SFLOAT: DataFormat get() = DataFormat(96L)
            /**
             * 32-bit-per-channel unsigned integer red channel data format. Values are in the `[0, 2^32 - 1]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32_UINT
             */
            val R32_UINT: DataFormat get() = DataFormat(97L)
            /**
             * 32-bit-per-channel signed integer red channel data format. Values are in the `[2^31 + 1, 2^31 -
             * 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32_SINT
             */
            val R32_SINT: DataFormat get() = DataFormat(98L)
            /**
             * 32-bit-per-channel signed floating-point red channel data format with the value stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32_SFLOAT
             */
            val R32_SFLOAT: DataFormat get() = DataFormat(99L)
            /**
             * 32-bit-per-channel unsigned integer red/green channel data format. Values are in the `[0, 2^32 -
             * 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32_UINT
             */
            val R32G32_UINT: DataFormat get() = DataFormat(100L)
            /**
             * 32-bit-per-channel signed integer red/green channel data format. Values are in the `[2^31 + 1,
             * 2^31 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32_SINT
             */
            val R32G32_SINT: DataFormat get() = DataFormat(101L)
            /**
             * 32-bit-per-channel signed floating-point red/green channel data format with the value stored
             * as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32_SFLOAT
             */
            val R32G32_SFLOAT: DataFormat get() = DataFormat(102L)
            /**
             * 32-bit-per-channel unsigned integer red/green/blue channel data format. Values are in the `[0,
             * 2^32 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32B32_UINT
             */
            val R32G32B32_UINT: DataFormat get() = DataFormat(103L)
            /**
             * 32-bit-per-channel signed integer red/green/blue channel data format. Values are in the `[2^31 +
             * 1, 2^31 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32B32_SINT
             */
            val R32G32B32_SINT: DataFormat get() = DataFormat(104L)
            /**
             * 32-bit-per-channel signed floating-point red/green/blue channel data format with the value
             * stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32B32_SFLOAT
             */
            val R32G32B32_SFLOAT: DataFormat get() = DataFormat(105L)
            /**
             * 32-bit-per-channel unsigned integer red/green/blue/alpha channel data format. Values are in the
             * `[0, 2^32 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32B32A32_UINT
             */
            val R32G32B32A32_UINT: DataFormat get() = DataFormat(106L)
            /**
             * 32-bit-per-channel signed integer red/green/blue/alpha channel data format. Values are in the
             * `[2^31 + 1, 2^31 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32B32A32_SINT
             */
            val R32G32B32A32_SINT: DataFormat get() = DataFormat(107L)
            /**
             * 32-bit-per-channel signed floating-point red/green/blue/alpha channel data format with the value
             * stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R32G32B32A32_SFLOAT
             */
            val R32G32B32A32_SFLOAT: DataFormat get() = DataFormat(108L)
            /**
             * 64-bit-per-channel unsigned integer red channel data format. Values are in the `[0, 2^64 - 1]`
             * range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64_UINT
             */
            val R64_UINT: DataFormat get() = DataFormat(109L)
            /**
             * 64-bit-per-channel signed integer red channel data format. Values are in the `[2^63 + 1, 2^63 -
             * 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64_SINT
             */
            val R64_SINT: DataFormat get() = DataFormat(110L)
            /**
             * 64-bit-per-channel signed floating-point red channel data format with the value stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64_SFLOAT
             */
            val R64_SFLOAT: DataFormat get() = DataFormat(111L)
            /**
             * 64-bit-per-channel unsigned integer red/green channel data format. Values are in the `[0, 2^64 -
             * 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64_UINT
             */
            val R64G64_UINT: DataFormat get() = DataFormat(112L)
            /**
             * 64-bit-per-channel signed integer red/green channel data format. Values are in the `[2^63 + 1,
             * 2^63 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64_SINT
             */
            val R64G64_SINT: DataFormat get() = DataFormat(113L)
            /**
             * 64-bit-per-channel signed floating-point red/green channel data format with the value stored
             * as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64_SFLOAT
             */
            val R64G64_SFLOAT: DataFormat get() = DataFormat(114L)
            /**
             * 64-bit-per-channel unsigned integer red/green/blue channel data format. Values are in the `[0,
             * 2^64 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64B64_UINT
             */
            val R64G64B64_UINT: DataFormat get() = DataFormat(115L)
            /**
             * 64-bit-per-channel signed integer red/green/blue channel data format. Values are in the `[2^63 +
             * 1, 2^63 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64B64_SINT
             */
            val R64G64B64_SINT: DataFormat get() = DataFormat(116L)
            /**
             * 64-bit-per-channel signed floating-point red/green/blue channel data format with the value
             * stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64B64_SFLOAT
             */
            val R64G64B64_SFLOAT: DataFormat get() = DataFormat(117L)
            /**
             * 64-bit-per-channel unsigned integer red/green/blue/alpha channel data format. Values are in the
             * `[0, 2^64 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64B64A64_UINT
             */
            val R64G64B64A64_UINT: DataFormat get() = DataFormat(118L)
            /**
             * 64-bit-per-channel signed integer red/green/blue/alpha channel data format. Values are in the
             * `[2^63 + 1, 2^63 - 1]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64B64A64_SINT
             */
            val R64G64B64A64_SINT: DataFormat get() = DataFormat(119L)
            /**
             * 64-bit-per-channel signed floating-point red/green/blue/alpha channel data format with the value
             * stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R64G64B64A64_SFLOAT
             */
            val R64G64B64A64_SFLOAT: DataFormat get() = DataFormat(120L)
            /**
             * Unsigned floating-point blue/green/red data format with the value stored as-is, packed in 32
             * bits. The format's precision is 10 bits of blue channel, 11 bits of green channel and 11 bits of
             * red channel.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B10G11R11_UFLOAT_PACK32
             */
            val B10G11R11_UFLOAT_PACK32: DataFormat get() = DataFormat(121L)
            /**
             * Unsigned floating-point exposure/blue/green/red data format with the value stored as-is, packed
             * in 32 bits. The format's precision is 5 bits of exposure, 9 bits of blue channel, 9 bits of
             * green channel and 9 bits of red channel.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_E5B9G9R9_UFLOAT_PACK32
             */
            val E5B9G9R9_UFLOAT_PACK32: DataFormat get() = DataFormat(122L)
            /**
             * 16-bit unsigned floating-point depth data format with normalized value. Values are in the `[0.0,
             * 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_D16_UNORM
             */
            val D16_UNORM: DataFormat get() = DataFormat(123L)
            /**
             * 24-bit unsigned floating-point depth data format with normalized value, plus 8 unused bits,
             * packed in 32 bits. Values for depth are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_X8_D24_UNORM_PACK32
             */
            val X8_D24_UNORM_PACK32: DataFormat get() = DataFormat(124L)
            /**
             * 32-bit signed floating-point depth data format with the value stored as-is.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_D32_SFLOAT
             */
            val D32_SFLOAT: DataFormat get() = DataFormat(125L)
            /**
             * 8-bit unsigned integer stencil data format.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_S8_UINT
             */
            val S8_UINT: DataFormat get() = DataFormat(126L)
            /**
             * 16-bit unsigned floating-point depth data format with normalized value, plus 8 bits of stencil
             * in unsigned integer format. Values for depth are in the `[0.0, 1.0]` range. Values for stencil
             * are in the `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_D16_UNORM_S8_UINT
             */
            val D16_UNORM_S8_UINT: DataFormat get() = DataFormat(127L)
            /**
             * 24-bit unsigned floating-point depth data format with normalized value, plus 8 bits of stencil
             * in unsigned integer format. Values for depth are in the `[0.0, 1.0]` range. Values for stencil
             * are in the `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_D24_UNORM_S8_UINT
             */
            val D24_UNORM_S8_UINT: DataFormat get() = DataFormat(128L)
            /**
             * 32-bit signed floating-point depth data format with the value stored as-is, plus 8 bits of
             * stencil in unsigned integer format. Values for stencil are in the `[0, 255]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_D32_SFLOAT_S8_UINT
             */
            val D32_SFLOAT_S8_UINT: DataFormat get() = DataFormat(129L)
            /**
             * VRAM-compressed unsigned red/green/blue channel data format with normalized value. Values are in
             * the `[0.0, 1.0]` range. The format's precision is 5 bits of red channel, 6 bits of green channel
             * and 5 bits of blue channel. Using BC1 texture compression (also known as S3TC DXT1).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC1_RGB_UNORM_BLOCK
             */
            val BC1_RGB_UNORM_BLOCK: DataFormat get() = DataFormat(130L)
            /**
             * VRAM-compressed unsigned red/green/blue channel data format with normalized value and nonlinear
             * sRGB encoding. Values are in the `[0.0, 1.0]` range. The format's precision is 5 bits of red
             * channel, 6 bits of green channel, and 5 bits of blue channel. Using BC1 texture compression
             * (also known as S3TC DXT1).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC1_RGB_SRGB_BLOCK
             */
            val BC1_RGB_SRGB_BLOCK: DataFormat get() = DataFormat(131L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range. The format's precision is 5 bits of red channel, 6 bits of green
             * channel, 5 bits of blue channel and 1 bit of alpha channel. Using BC1 texture compression (also
             * known as S3TC DXT1).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC1_RGBA_UNORM_BLOCK
             */
            val BC1_RGBA_UNORM_BLOCK: DataFormat get() = DataFormat(132L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range. The format's precision is 5 bits
             * of red channel, 6 bits of green channel, 5 bits of blue channel, and 1 bit of alpha channel.
             * Using BC1 texture compression (also known as S3TC DXT1).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC1_RGBA_SRGB_BLOCK
             */
            val BC1_RGBA_SRGB_BLOCK: DataFormat get() = DataFormat(133L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range. The format's precision is 5 bits of red channel, 6 bits of green
             * channel, 5 bits of blue channel and 4 bits of alpha channel. Using BC2 texture compression (also
             * known as S3TC DXT3).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC2_UNORM_BLOCK
             */
            val BC2_UNORM_BLOCK: DataFormat get() = DataFormat(134L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range. The format's precision is 5 bits
             * of red channel, 6 bits of green channel, 5 bits of blue channel, and 4 bits of alpha channel.
             * Using BC2 texture compression (also known as S3TC DXT3).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC2_SRGB_BLOCK
             */
            val BC2_SRGB_BLOCK: DataFormat get() = DataFormat(135L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range. The format's precision is 5 bits of red channel, 6 bits of green
             * channel, 5 bits of blue channel and 8 bits of alpha channel. Using BC3 texture compression (also
             * known as S3TC DXT5).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC3_UNORM_BLOCK
             */
            val BC3_UNORM_BLOCK: DataFormat get() = DataFormat(136L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range. The format's precision is 5 bits
             * of red channel, 6 bits of green channel, 5 bits of blue channel, and 8 bits of alpha channel.
             * Using BC3 texture compression (also known as S3TC DXT5).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC3_SRGB_BLOCK
             */
            val BC3_SRGB_BLOCK: DataFormat get() = DataFormat(137L)
            /**
             * VRAM-compressed unsigned red channel data format with normalized value. Values are in the `[0.0,
             * 1.0]` range. The format's precision is 8 bits of red channel. Using BC4 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC4_UNORM_BLOCK
             */
            val BC4_UNORM_BLOCK: DataFormat get() = DataFormat(138L)
            /**
             * VRAM-compressed signed red channel data format with normalized value. Values are in the `[-1.0,
             * 1.0]` range. The format's precision is 8 bits of red channel. Using BC4 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC4_SNORM_BLOCK
             */
            val BC4_SNORM_BLOCK: DataFormat get() = DataFormat(139L)
            /**
             * VRAM-compressed unsigned red/green channel data format with normalized value. Values are in the
             * `[0.0, 1.0]` range. The format's precision is 8 bits of red channel and 8 bits of green channel.
             * Using BC5 texture compression (also known as S3TC RGTC).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC5_UNORM_BLOCK
             */
            val BC5_UNORM_BLOCK: DataFormat get() = DataFormat(140L)
            /**
             * VRAM-compressed signed red/green channel data format with normalized value. Values are in the
             * `[-1.0, 1.0]` range. The format's precision is 8 bits of red channel and 8 bits of green
             * channel. Using BC5 texture compression (also known as S3TC RGTC).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC5_SNORM_BLOCK
             */
            val BC5_SNORM_BLOCK: DataFormat get() = DataFormat(141L)
            /**
             * VRAM-compressed unsigned red/green/blue channel data format with the floating-point value stored
             * as-is. The format's precision is between 10 and 13 bits for the red/green/blue channels. Using
             * BC6H texture compression (also known as BPTC HDR).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC6H_UFLOAT_BLOCK
             */
            val BC6H_UFLOAT_BLOCK: DataFormat get() = DataFormat(142L)
            /**
             * VRAM-compressed signed red/green/blue channel data format with the floating-point value stored
             * as-is. The format's precision is between 10 and 13 bits for the red/green/blue channels. Using
             * BC6H texture compression (also known as BPTC HDR).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC6H_SFLOAT_BLOCK
             */
            val BC6H_SFLOAT_BLOCK: DataFormat get() = DataFormat(143L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range. The format's precision is between 4 and 7 bits for the
             * red/green/blue channels and between 0 and 8 bits for the alpha channel. Also known as BPTC LDR.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC7_UNORM_BLOCK
             */
            val BC7_UNORM_BLOCK: DataFormat get() = DataFormat(144L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range. The format's precision is between
             * 4 and 7 bits for the red/green/blue channels and between 0 and 8 bits for the alpha channel.
             * Also known as BPTC LDR.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_BC7_SRGB_BLOCK
             */
            val BC7_SRGB_BLOCK: DataFormat get() = DataFormat(145L)
            /**
             * VRAM-compressed unsigned red/green/blue channel data format with normalized value. Values are in
             * the `[0.0, 1.0]` range. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_ETC2_R8G8B8_UNORM_BLOCK
             */
            val ETC2_R8G8B8_UNORM_BLOCK: DataFormat get() = DataFormat(146L)
            /**
             * VRAM-compressed unsigned red/green/blue channel data format with normalized value and nonlinear
             * sRGB encoding. Values are in the `[0.0, 1.0]` range. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_ETC2_R8G8B8_SRGB_BLOCK
             */
            val ETC2_R8G8B8_SRGB_BLOCK: DataFormat get() = DataFormat(147L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range. Red/green/blue use 8 bit of precision each, with alpha using 1
             * bit of precision. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_ETC2_R8G8B8A1_UNORM_BLOCK
             */
            val ETC2_R8G8B8A1_UNORM_BLOCK: DataFormat get() = DataFormat(148L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range. Red/green/blue use 8 bit of
             * precision each, with alpha using 1 bit of precision. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_ETC2_R8G8B8A1_SRGB_BLOCK
             */
            val ETC2_R8G8B8A1_SRGB_BLOCK: DataFormat get() = DataFormat(149L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value. Values
             * are in the `[0.0, 1.0]` range. Red/green/blue use 8 bits of precision each, with alpha using 8
             * bits of precision. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_ETC2_R8G8B8A8_UNORM_BLOCK
             */
            val ETC2_R8G8B8A8_UNORM_BLOCK: DataFormat get() = DataFormat(150L)
            /**
             * VRAM-compressed unsigned red/green/blue/alpha channel data format with normalized value and
             * nonlinear sRGB encoding. Values are in the `[0.0, 1.0]` range. Red/green/blue use 8 bits of
             * precision each, with alpha using 8 bits of precision. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_ETC2_R8G8B8A8_SRGB_BLOCK
             */
            val ETC2_R8G8B8A8_SRGB_BLOCK: DataFormat get() = DataFormat(151L)
            /**
             * 11-bit VRAM-compressed unsigned red channel data format with normalized value. Values are in the
             * `[0.0, 1.0]` range. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_EAC_R11_UNORM_BLOCK
             */
            val EAC_R11_UNORM_BLOCK: DataFormat get() = DataFormat(152L)
            /**
             * 11-bit VRAM-compressed signed red channel data format with normalized value. Values are in the
             * `[-1.0, 1.0]` range. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_EAC_R11_SNORM_BLOCK
             */
            val EAC_R11_SNORM_BLOCK: DataFormat get() = DataFormat(153L)
            /**
             * 11-bit VRAM-compressed unsigned red/green channel data format with normalized value. Values are
             * in the `[0.0, 1.0]` range. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_EAC_R11G11_UNORM_BLOCK
             */
            val EAC_R11G11_UNORM_BLOCK: DataFormat get() = DataFormat(154L)
            /**
             * 11-bit VRAM-compressed signed red/green channel data format with normalized value. Values are in
             * the `[-1.0, 1.0]` range. Using ETC2 texture compression.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_EAC_R11G11_SNORM_BLOCK
             */
            val EAC_R11G11_SNORM_BLOCK: DataFormat get() = DataFormat(155L)
            val ASTC_4x4_UNORM_BLOCK: DataFormat get() = DataFormat(156L)
            val ASTC_4x4_SRGB_BLOCK: DataFormat get() = DataFormat(157L)
            val ASTC_5x4_UNORM_BLOCK: DataFormat get() = DataFormat(158L)
            val ASTC_5x4_SRGB_BLOCK: DataFormat get() = DataFormat(159L)
            val ASTC_5x5_UNORM_BLOCK: DataFormat get() = DataFormat(160L)
            val ASTC_5x5_SRGB_BLOCK: DataFormat get() = DataFormat(161L)
            val ASTC_6x5_UNORM_BLOCK: DataFormat get() = DataFormat(162L)
            val ASTC_6x5_SRGB_BLOCK: DataFormat get() = DataFormat(163L)
            val ASTC_6x6_UNORM_BLOCK: DataFormat get() = DataFormat(164L)
            val ASTC_6x6_SRGB_BLOCK: DataFormat get() = DataFormat(165L)
            val ASTC_8x5_UNORM_BLOCK: DataFormat get() = DataFormat(166L)
            val ASTC_8x5_SRGB_BLOCK: DataFormat get() = DataFormat(167L)
            val ASTC_8x6_UNORM_BLOCK: DataFormat get() = DataFormat(168L)
            val ASTC_8x6_SRGB_BLOCK: DataFormat get() = DataFormat(169L)
            val ASTC_8x8_UNORM_BLOCK: DataFormat get() = DataFormat(170L)
            val ASTC_8x8_SRGB_BLOCK: DataFormat get() = DataFormat(171L)
            val ASTC_10x5_UNORM_BLOCK: DataFormat get() = DataFormat(172L)
            val ASTC_10x5_SRGB_BLOCK: DataFormat get() = DataFormat(173L)
            val ASTC_10x6_UNORM_BLOCK: DataFormat get() = DataFormat(174L)
            val ASTC_10x6_SRGB_BLOCK: DataFormat get() = DataFormat(175L)
            val ASTC_10x8_UNORM_BLOCK: DataFormat get() = DataFormat(176L)
            val ASTC_10x8_SRGB_BLOCK: DataFormat get() = DataFormat(177L)
            val ASTC_10x10_UNORM_BLOCK: DataFormat get() = DataFormat(178L)
            val ASTC_10x10_SRGB_BLOCK: DataFormat get() = DataFormat(179L)
            val ASTC_12x10_UNORM_BLOCK: DataFormat get() = DataFormat(180L)
            val ASTC_12x10_SRGB_BLOCK: DataFormat get() = DataFormat(181L)
            val ASTC_12x12_UNORM_BLOCK: DataFormat get() = DataFormat(182L)
            val ASTC_12x12_SRGB_BLOCK: DataFormat get() = DataFormat(183L)
            /**
             * 8-bit-per-channel unsigned floating-point green/blue/red channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved
             * horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value for the
             * blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G8B8G8R8_422_UNORM
             */
            val G8B8G8R8_422_UNORM: DataFormat get() = DataFormat(184L)
            /**
             * 8-bit-per-channel unsigned floating-point blue/green/red channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved
             * horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value for the
             * blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B8G8R8G8_422_UNORM
             */
            val B8G8R8G8_422_UNORM: DataFormat get() = DataFormat(185L)
            /**
             * 8-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * stored across 3 separate planes (green + blue + red). Values are in the `[0.0, 1.0]` range. Blue
             * and red channel data is stored at halved horizontal and vertical resolution (i.e. 2×2 adjacent
             * pixels will share the same value for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G8_B8_R8_3PLANE_420_UNORM
             */
            val G8_B8_R8_3PLANE_420_UNORM: DataFormat get() = DataFormat(186L)
            /**
             * 8-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * stored across 2 separate planes (green + blue/red). Values are in the `[0.0, 1.0]` range. Blue
             * and red channel data is stored at halved horizontal and vertical resolution (i.e. 2×2 adjacent
             * pixels will share the same value for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G8_B8R8_2PLANE_420_UNORM
             */
            val G8_B8R8_2PLANE_420_UNORM: DataFormat get() = DataFormat(187L)
            /**
             * 8-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * stored across 2 separate planes (green + blue + red). Values are in the `[0.0, 1.0]` range. Blue
             * and red channel data is stored at halved horizontal resolution (i.e. 2 horizontally adjacent
             * pixels will share the same value for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G8_B8_R8_3PLANE_422_UNORM
             */
            val G8_B8_R8_3PLANE_422_UNORM: DataFormat get() = DataFormat(188L)
            /**
             * 8-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * stored across 2 separate planes (green + blue/red). Values are in the `[0.0, 1.0]` range. Blue
             * and red channel data is stored at halved horizontal resolution (i.e. 2 horizontally adjacent
             * pixels will share the same value for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G8_B8R8_2PLANE_422_UNORM
             */
            val G8_B8R8_2PLANE_422_UNORM: DataFormat get() = DataFormat(189L)
            /**
             * 8-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * stored across 3 separate planes. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G8_B8_R8_3PLANE_444_UNORM
             */
            val G8_B8_R8_3PLANE_444_UNORM: DataFormat get() = DataFormat(190L)
            /**
             * 10-bit-per-channel unsigned floating-point red channel data with normalized value, plus 6 unused
             * bits, packed in 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R10X6_UNORM_PACK16
             */
            val R10X6_UNORM_PACK16: DataFormat get() = DataFormat(191L)
            /**
             * 10-bit-per-channel unsigned floating-point red/green channel data with normalized value, plus 6
             * unused bits after each channel, packed in 2×16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R10X6G10X6_UNORM_2PACK16
             */
            val R10X6G10X6_UNORM_2PACK16: DataFormat get() = DataFormat(192L)
            /**
             * 10-bit-per-channel unsigned floating-point red/green/blue/alpha channel data with normalized
             * value, plus 6 unused bits after each channel, packed in 4×16 bits. Values are in the `[0.0,
             * 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R10X6G10X6B10X6A10X6_UNORM_4PACK16
             */
            val R10X6G10X6B10X6A10X6_UNORM_4PACK16: DataFormat get() = DataFormat(193L)
            /**
             * 10-bit-per-channel unsigned floating-point green/blue/green/red channel data with normalized
             * value, plus 6 unused bits after each channel, packed in 4×16 bits. Values are in the `[0.0,
             * 1.0]` range. Blue and red channel data is stored at halved horizontal resolution (i.e. 2
             * horizontally adjacent pixels will share the same value for the blue/red channel). The green
             * channel is listed twice, but contains different values to allow it to be represented at full
             * resolution.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G10X6B10X6G10X6R10X6_422_UNORM_4PACK16
             */
            val G10X6B10X6G10X6R10X6_422_UNORM_4PACK16: DataFormat get() = DataFormat(194L)
            /**
             * 10-bit-per-channel unsigned floating-point blue/green/red/green channel data with normalized
             * value, plus 6 unused bits after each channel, packed in 4×16 bits. Values are in the `[0.0,
             * 1.0]` range. Blue and red channel data is stored at halved horizontal resolution (i.e. 2
             * horizontally adjacent pixels will share the same value for the blue/red channel). The green
             * channel is listed twice, but contains different values to allow it to be represented at full
             * resolution.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B10X6G10X6R10X6G10X6_422_UNORM_4PACK16
             */
            val B10X6G10X6R10X6G10X6_422_UNORM_4PACK16: DataFormat get() = DataFormat(195L)
            /**
             * 10-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 2 separate planes
             * (green + blue + red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored
             * at halved horizontal and vertical resolution (i.e. 2×2 adjacent pixels will share the same value
             * for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G10X6_B10X6_R10X6_3PLANE_420_UNORM_3PACK16
             */
            val G10X6_B10X6_R10X6_3PLANE_420_UNORM_3PACK16: DataFormat get() = DataFormat(196L)
            /**
             * 10-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 2 separate planes
             * (green + blue/red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at
             * halved horizontal and vertical resolution (i.e. 2×2 adjacent pixels will share the same value
             * for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G10X6_B10X6R10X6_2PLANE_420_UNORM_3PACK16
             */
            val G10X6_B10X6R10X6_2PLANE_420_UNORM_3PACK16: DataFormat get() = DataFormat(197L)
            /**
             * 10-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 3 separate planes
             * (green + blue + red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored
             * at halved horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value
             * for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G10X6_B10X6_R10X6_3PLANE_422_UNORM_3PACK16
             */
            val G10X6_B10X6_R10X6_3PLANE_422_UNORM_3PACK16: DataFormat get() = DataFormat(198L)
            /**
             * 10-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 3 separate planes
             * (green + blue/red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at
             * halved horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value for
             * the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G10X6_B10X6R10X6_2PLANE_422_UNORM_3PACK16
             */
            val G10X6_B10X6R10X6_2PLANE_422_UNORM_3PACK16: DataFormat get() = DataFormat(199L)
            /**
             * 10-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 3 separate planes
             * (green + blue + red). Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G10X6_B10X6_R10X6_3PLANE_444_UNORM_3PACK16
             */
            val G10X6_B10X6_R10X6_3PLANE_444_UNORM_3PACK16: DataFormat get() = DataFormat(200L)
            /**
             * 12-bit-per-channel unsigned floating-point red channel data with normalized value, plus 6 unused
             * bits, packed in 16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R12X4_UNORM_PACK16
             */
            val R12X4_UNORM_PACK16: DataFormat get() = DataFormat(201L)
            /**
             * 12-bit-per-channel unsigned floating-point red/green channel data with normalized value, plus 6
             * unused bits after each channel, packed in 2×16 bits. Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R12X4G12X4_UNORM_2PACK16
             */
            val R12X4G12X4_UNORM_2PACK16: DataFormat get() = DataFormat(202L)
            /**
             * 12-bit-per-channel unsigned floating-point red/green/blue/alpha channel data with normalized
             * value, plus 6 unused bits after each channel, packed in 4×16 bits. Values are in the `[0.0,
             * 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_R12X4G12X4B12X4A12X4_UNORM_4PACK16
             */
            val R12X4G12X4B12X4A12X4_UNORM_4PACK16: DataFormat get() = DataFormat(203L)
            /**
             * 12-bit-per-channel unsigned floating-point green/blue/green/red channel data with normalized
             * value, plus 6 unused bits after each channel, packed in 4×16 bits. Values are in the `[0.0,
             * 1.0]` range. Blue and red channel data is stored at halved horizontal resolution (i.e. 2
             * horizontally adjacent pixels will share the same value for the blue/red channel). The green
             * channel is listed twice, but contains different values to allow it to be represented at full
             * resolution.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G12X4B12X4G12X4R12X4_422_UNORM_4PACK16
             */
            val G12X4B12X4G12X4R12X4_422_UNORM_4PACK16: DataFormat get() = DataFormat(204L)
            /**
             * 12-bit-per-channel unsigned floating-point blue/green/red/green channel data with normalized
             * value, plus 6 unused bits after each channel, packed in 4×16 bits. Values are in the `[0.0,
             * 1.0]` range. Blue and red channel data is stored at halved horizontal resolution (i.e. 2
             * horizontally adjacent pixels will share the same value for the blue/red channel). The green
             * channel is listed twice, but contains different values to allow it to be represented at full
             * resolution.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B12X4G12X4R12X4G12X4_422_UNORM_4PACK16
             */
            val B12X4G12X4R12X4G12X4_422_UNORM_4PACK16: DataFormat get() = DataFormat(205L)
            /**
             * 12-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 2 separate planes
             * (green + blue + red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored
             * at halved horizontal and vertical resolution (i.e. 2×2 adjacent pixels will share the same value
             * for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G12X4_B12X4_R12X4_3PLANE_420_UNORM_3PACK16
             */
            val G12X4_B12X4_R12X4_3PLANE_420_UNORM_3PACK16: DataFormat get() = DataFormat(206L)
            /**
             * 12-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 2 separate planes
             * (green + blue/red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at
             * halved horizontal and vertical resolution (i.e. 2×2 adjacent pixels will share the same value
             * for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G12X4_B12X4R12X4_2PLANE_420_UNORM_3PACK16
             */
            val G12X4_B12X4R12X4_2PLANE_420_UNORM_3PACK16: DataFormat get() = DataFormat(207L)
            /**
             * 12-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 3 separate planes
             * (green + blue + red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored
             * at halved horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value
             * for the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G12X4_B12X4_R12X4_3PLANE_422_UNORM_3PACK16
             */
            val G12X4_B12X4_R12X4_3PLANE_422_UNORM_3PACK16: DataFormat get() = DataFormat(208L)
            /**
             * 12-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 3 separate planes
             * (green + blue/red). Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at
             * halved horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value for
             * the blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G12X4_B12X4R12X4_2PLANE_422_UNORM_3PACK16
             */
            val G12X4_B12X4R12X4_2PLANE_422_UNORM_3PACK16: DataFormat get() = DataFormat(209L)
            /**
             * 12-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Packed in 3×16 bits and stored across 3 separate planes
             * (green + blue + red). Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G12X4_B12X4_R12X4_3PLANE_444_UNORM_3PACK16
             */
            val G12X4_B12X4_R12X4_3PLANE_444_UNORM_3PACK16: DataFormat get() = DataFormat(210L)
            /**
             * 16-bit-per-channel unsigned floating-point green/blue/red channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved
             * horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value for the
             * blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G16B16G16R16_422_UNORM
             */
            val G16B16G16R16_422_UNORM: DataFormat get() = DataFormat(211L)
            /**
             * 16-bit-per-channel unsigned floating-point blue/green/red channel data format with normalized
             * value. Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved
             * horizontal resolution (i.e. 2 horizontally adjacent pixels will share the same value for the
             * blue/red channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_B16G16R16G16_422_UNORM
             */
            val B16G16R16G16_422_UNORM: DataFormat get() = DataFormat(212L)
            /**
             * 16-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Stored across 2 separate planes (green + blue + red).
             * Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved horizontal
             * and vertical resolution (i.e. 2×2 adjacent pixels will share the same value for the blue/red
             * channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G16_B16_R16_3PLANE_420_UNORM
             */
            val G16_B16_R16_3PLANE_420_UNORM: DataFormat get() = DataFormat(213L)
            /**
             * 16-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Stored across 2 separate planes (green + blue/red).
             * Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved horizontal
             * and vertical resolution (i.e. 2×2 adjacent pixels will share the same value for the blue/red
             * channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G16_B16R16_2PLANE_420_UNORM
             */
            val G16_B16R16_2PLANE_420_UNORM: DataFormat get() = DataFormat(214L)
            /**
             * 16-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Stored across 3 separate planes (green + blue + red).
             * Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved horizontal
             * resolution (i.e. 2 horizontally adjacent pixels will share the same value for the blue/red
             * channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G16_B16_R16_3PLANE_422_UNORM
             */
            val G16_B16_R16_3PLANE_422_UNORM: DataFormat get() = DataFormat(215L)
            /**
             * 16-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Stored across 3 separate planes (green + blue/red).
             * Values are in the `[0.0, 1.0]` range. Blue and red channel data is stored at halved horizontal
             * resolution (i.e. 2 horizontally adjacent pixels will share the same value for the blue/red
             * channel).
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G16_B16R16_2PLANE_422_UNORM
             */
            val G16_B16R16_2PLANE_422_UNORM: DataFormat get() = DataFormat(216L)
            /**
             * 16-bit-per-channel unsigned floating-point green/blue/red channel data with normalized value,
             * plus 6 unused bits after each channel. Stored across 3 separate planes (green + blue + red).
             * Values are in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_G16_B16_R16_3PLANE_444_UNORM
             */
            val G16_B16_R16_3PLANE_444_UNORM: DataFormat get() = DataFormat(217L)
            val ASTC_4x4_SFLOAT_BLOCK: DataFormat get() = DataFormat(218L)
            val ASTC_5x4_SFLOAT_BLOCK: DataFormat get() = DataFormat(219L)
            val ASTC_5x5_SFLOAT_BLOCK: DataFormat get() = DataFormat(220L)
            val ASTC_6x5_SFLOAT_BLOCK: DataFormat get() = DataFormat(221L)
            val ASTC_6x6_SFLOAT_BLOCK: DataFormat get() = DataFormat(222L)
            val ASTC_8x5_SFLOAT_BLOCK: DataFormat get() = DataFormat(223L)
            val ASTC_8x6_SFLOAT_BLOCK: DataFormat get() = DataFormat(224L)
            val ASTC_8x8_SFLOAT_BLOCK: DataFormat get() = DataFormat(225L)
            val ASTC_10x5_SFLOAT_BLOCK: DataFormat get() = DataFormat(226L)
            val ASTC_10x6_SFLOAT_BLOCK: DataFormat get() = DataFormat(227L)
            val ASTC_10x8_SFLOAT_BLOCK: DataFormat get() = DataFormat(228L)
            val ASTC_10x10_SFLOAT_BLOCK: DataFormat get() = DataFormat(229L)
            val ASTC_12x10_SFLOAT_BLOCK: DataFormat get() = DataFormat(230L)
            val ASTC_12x12_SFLOAT_BLOCK: DataFormat get() = DataFormat(231L)
            /**
             * Represents the size of the `DataFormat` enum.
             *
             * Generated from Godot docs: RenderingDevice.DATA_FORMAT_MAX
             */
            val MAX: DataFormat get() = DataFormat(232L)
        }
    }

    /**
     * Godot's `RenderingDevice.BarrierMask` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`RenderingDevice.BarrierMask.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.BarrierMask
     */
    @JvmInline
    value class BarrierMask(override val value: Long) : GodotEnumValue {
        infix fun or(other: BarrierMask): BarrierMask = BarrierMask(value or other.value)

        infix fun and(other: BarrierMask): BarrierMask = BarrierMask(value and other.value)

        infix fun xor(other: BarrierMask): BarrierMask = BarrierMask(value xor other.value)

        fun inv(): BarrierMask = BarrierMask(value.inv())

        operator fun contains(other: BarrierMask): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Vertex shader barrier mask.
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_VERTEX
             */
            val VERTEX: BarrierMask get() = BarrierMask(1L)
            /**
             * Fragment shader barrier mask.
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_FRAGMENT
             */
            val FRAGMENT: BarrierMask get() = BarrierMask(8L)
            /**
             * Compute barrier mask.
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_COMPUTE
             */
            val COMPUTE: BarrierMask get() = BarrierMask(2L)
            /**
             * Transfer barrier mask.
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_TRANSFER
             */
            val TRANSFER: BarrierMask get() = BarrierMask(4L)
            /**
             * Raster barrier mask (vertex and fragment). Equivalent to `BARRIER_MASK_VERTEX |
             * BARRIER_MASK_FRAGMENT`.
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_RASTER
             */
            val RASTER: BarrierMask get() = BarrierMask(9L)
            /**
             * Barrier mask for all types (vertex, fragment, compute, transfer).
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_ALL_BARRIERS
             */
            val ALL_BARRIERS: BarrierMask get() = BarrierMask(32767L)
            /**
             * No barrier for any type.
             *
             * Generated from Godot docs: RenderingDevice.BARRIER_MASK_NO_BARRIER
             */
            val NO_BARRIER: BarrierMask get() = BarrierMask(32768L)
        }
    }

    /**
     * Godot's `RenderingDevice.TextureType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.TextureType.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.TextureType
     */
    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 1-dimensional texture.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_1D
             */
            val TYPE_1D: TextureType get() = TextureType(0L)
            /**
             * 2-dimensional texture.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_2D
             */
            val TYPE_2D: TextureType get() = TextureType(1L)
            /**
             * 3-dimensional texture.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_3D
             */
            val TYPE_3D: TextureType get() = TextureType(2L)
            /**
             * `Cubemap` texture.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_CUBE
             */
            val CUBE: TextureType get() = TextureType(3L)
            /**
             * Array of 1-dimensional textures.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_1D_ARRAY
             */
            val TYPE_1D_ARRAY: TextureType get() = TextureType(4L)
            /**
             * Array of 2-dimensional textures.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_2D_ARRAY
             */
            val TYPE_2D_ARRAY: TextureType get() = TextureType(5L)
            /**
             * Array of `Cubemap` textures.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_CUBE_ARRAY
             */
            val CUBE_ARRAY: TextureType get() = TextureType(6L)
            /**
             * Represents the size of the `TextureType` enum.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_TYPE_MAX
             */
            val MAX: TextureType get() = TextureType(7L)
        }
    }

    /**
     * Godot's `RenderingDevice.TextureSamples` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.TextureSamples.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.TextureSamples
     */
    @JvmInline
    value class TextureSamples(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Perform 1 texture sample (this is the fastest but lowest-quality for antialiasing).
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_1
             */
            val SAMPLES_1: TextureSamples get() = TextureSamples(0L)
            /**
             * Perform 2 texture samples.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_2
             */
            val SAMPLES_2: TextureSamples get() = TextureSamples(1L)
            /**
             * Perform 4 texture samples.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_4
             */
            val SAMPLES_4: TextureSamples get() = TextureSamples(2L)
            /**
             * Perform 8 texture samples. Not supported on mobile GPUs (including Apple Silicon).
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_8
             */
            val SAMPLES_8: TextureSamples get() = TextureSamples(3L)
            /**
             * Perform 16 texture samples. Not supported on mobile GPUs and many desktop GPUs.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_16
             */
            val SAMPLES_16: TextureSamples get() = TextureSamples(4L)
            /**
             * Perform 32 texture samples. Not supported on most GPUs.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_32
             */
            val SAMPLES_32: TextureSamples get() = TextureSamples(5L)
            /**
             * Perform 64 texture samples (this is the slowest but highest-quality for antialiasing). Not
             * supported on most GPUs.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_64
             */
            val SAMPLES_64: TextureSamples get() = TextureSamples(6L)
            /**
             * Represents the size of the `TextureSamples` enum.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SAMPLES_MAX
             */
            val MAX: TextureSamples get() = TextureSamples(7L)
        }
    }

    /**
     * Godot's `RenderingDevice.TextureUsageBits` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.TextureUsageBits.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.TextureUsageBits
     */
    @JvmInline
    value class TextureUsageBits(override val value: Long) : GodotEnumValue {
        infix fun or(other: TextureUsageBits): TextureUsageBits = TextureUsageBits(value or other.value)

        infix fun and(other: TextureUsageBits): TextureUsageBits = TextureUsageBits(value and other.value)

        infix fun xor(other: TextureUsageBits): TextureUsageBits = TextureUsageBits(value xor other.value)

        fun inv(): TextureUsageBits = TextureUsageBits(value.inv())

        operator fun contains(other: TextureUsageBits): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Texture can be sampled.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_SAMPLING_BIT
             */
            val SAMPLING_BIT: TextureUsageBits get() = TextureUsageBits(1L)
            /**
             * Texture can be used as a color attachment in a framebuffer.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_COLOR_ATTACHMENT_BIT
             */
            val COLOR_ATTACHMENT_BIT: TextureUsageBits get() = TextureUsageBits(2L)
            /**
             * Texture can be used as a depth/stencil attachment in a framebuffer.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT
             */
            val DEPTH_STENCIL_ATTACHMENT_BIT: TextureUsageBits get() = TextureUsageBits(4L)
            /**
             * Texture can be used as a depth/stencil resolve attachment in a framebuffer.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_DEPTH_RESOLVE_ATTACHMENT_BIT
             */
            val DEPTH_RESOLVE_ATTACHMENT_BIT: TextureUsageBits get() = TextureUsageBits(4096L)
            /**
             * Texture can be used as a storage image
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#descriptorsets-storageimage).
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_STORAGE_BIT
             */
            val STORAGE_BIT: TextureUsageBits get() = TextureUsageBits(8L)
            /**
             * Texture can be used as a storage image
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#descriptorsets-storageimage)
             * with support for atomic operations.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_STORAGE_ATOMIC_BIT
             */
            val STORAGE_ATOMIC_BIT: TextureUsageBits get() = TextureUsageBits(16L)
            /**
             * Texture can be read back on the CPU using `texture_get_data` faster than without this bit, since
             * it is always kept in the system memory.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_CPU_READ_BIT
             */
            val CPU_READ_BIT: TextureUsageBits get() = TextureUsageBits(32L)
            /**
             * Texture can be updated using `texture_update`.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_CAN_UPDATE_BIT
             */
            val CAN_UPDATE_BIT: TextureUsageBits get() = TextureUsageBits(64L)
            /**
             * Texture can be a source for `texture_copy`.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_CAN_COPY_FROM_BIT
             */
            val CAN_COPY_FROM_BIT: TextureUsageBits get() = TextureUsageBits(128L)
            /**
             * Texture can be a destination for `texture_copy`.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_CAN_COPY_TO_BIT
             */
            val CAN_COPY_TO_BIT: TextureUsageBits get() = TextureUsageBits(256L)
            /**
             * Texture can be used as a input attachment
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#descriptorsets-inputattachment)
             * in a framebuffer.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_USAGE_INPUT_ATTACHMENT_BIT
             */
            val INPUT_ATTACHMENT_BIT: TextureUsageBits get() = TextureUsageBits(512L)
        }
    }

    /**
     * Godot's `RenderingDevice.TextureSwizzle` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.TextureSwizzle.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.TextureSwizzle
     */
    @JvmInline
    value class TextureSwizzle(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Return the sampled value as-is.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_IDENTITY
             */
            val IDENTITY: TextureSwizzle get() = TextureSwizzle(0L)
            /**
             * Always return `0.0` when sampling.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_ZERO
             */
            val ZERO: TextureSwizzle get() = TextureSwizzle(1L)
            /**
             * Always return `1.0` when sampling.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_ONE
             */
            val ONE: TextureSwizzle get() = TextureSwizzle(2L)
            /**
             * Sample the red color channel.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_R
             */
            val R: TextureSwizzle get() = TextureSwizzle(3L)
            /**
             * Sample the green color channel.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_G
             */
            val G: TextureSwizzle get() = TextureSwizzle(4L)
            /**
             * Sample the blue color channel.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_B
             */
            val B: TextureSwizzle get() = TextureSwizzle(5L)
            /**
             * Sample the alpha channel.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_A
             */
            val A: TextureSwizzle get() = TextureSwizzle(6L)
            /**
             * Represents the size of the `TextureSwizzle` enum.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SWIZZLE_MAX
             */
            val MAX: TextureSwizzle get() = TextureSwizzle(7L)
        }
    }

    /**
     * Godot's `RenderingDevice.TextureSliceType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.TextureSliceType.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.TextureSliceType
     */
    @JvmInline
    value class TextureSliceType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 2-dimensional texture slice.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SLICE_2D
             */
            val SLICE_2D: TextureSliceType get() = TextureSliceType(0L)
            /**
             * Cubemap texture slice.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SLICE_CUBEMAP
             */
            val CUBEMAP: TextureSliceType get() = TextureSliceType(1L)
            /**
             * 3-dimensional texture slice.
             *
             * Generated from Godot docs: RenderingDevice.TEXTURE_SLICE_3D
             */
            val SLICE_3D: TextureSliceType get() = TextureSliceType(2L)
        }
    }

    /**
     * Godot's `RenderingDevice.SamplerFilter` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.SamplerFilter.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.SamplerFilter
     */
    @JvmInline
    value class SamplerFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Nearest-neighbor sampler filtering. Sampling at higher resolutions than the source will result
             * in a pixelated look.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_FILTER_NEAREST
             */
            val NEAREST: SamplerFilter get() = SamplerFilter(0L)
            /**
             * Bilinear sampler filtering. Sampling at higher resolutions than the source will result in a
             * blurry look.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_FILTER_LINEAR
             */
            val LINEAR: SamplerFilter get() = SamplerFilter(1L)
        }
    }

    /**
     * Godot's `RenderingDevice.SamplerRepeatMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.SamplerRepeatMode.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.SamplerRepeatMode
     */
    @JvmInline
    value class SamplerRepeatMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sample with repeating enabled.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_REPEAT_MODE_REPEAT
             */
            val REPEAT: SamplerRepeatMode get() = SamplerRepeatMode(0L)
            /**
             * Sample with mirrored repeating enabled. When sampling outside the `[0.0, 1.0]` range, return a
             * mirrored version of the sampler. This mirrored version is mirrored again if sampling further
             * away, with the pattern repeating indefinitely.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_REPEAT_MODE_MIRRORED_REPEAT
             */
            val MIRRORED_REPEAT: SamplerRepeatMode get() = SamplerRepeatMode(1L)
            /**
             * Sample with repeating disabled. When sampling outside the `[0.0, 1.0]` range, return the color
             * of the last pixel on the edge.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_REPEAT_MODE_CLAMP_TO_EDGE
             */
            val CLAMP_TO_EDGE: SamplerRepeatMode get() = SamplerRepeatMode(2L)
            /**
             * Sample with repeating disabled. When sampling outside the `[0.0, 1.0]` range, return the
             * specified `RDSamplerState.border_color`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_REPEAT_MODE_CLAMP_TO_BORDER
             */
            val CLAMP_TO_BORDER: SamplerRepeatMode get() = SamplerRepeatMode(3L)
            /**
             * Sample with mirrored repeating enabled, but only once. When sampling in the `[-1.0, 0.0]` range,
             * return a mirrored version of the sampler. When sampling outside the `[-1.0, 1.0]` range, return
             * the color of the last pixel on the edge.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_REPEAT_MODE_MIRROR_CLAMP_TO_EDGE
             */
            val MIRROR_CLAMP_TO_EDGE: SamplerRepeatMode get() = SamplerRepeatMode(4L)
            /**
             * Represents the size of the `SamplerRepeatMode` enum.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_REPEAT_MODE_MAX
             */
            val MAX: SamplerRepeatMode get() = SamplerRepeatMode(5L)
        }
    }

    /**
     * Godot's `RenderingDevice.SamplerBorderColor` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.SamplerBorderColor.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.SamplerBorderColor
     */
    @JvmInline
    value class SamplerBorderColor(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Return a floating-point transparent black color when sampling outside the `[0.0, 1.0]` range.
             * Only effective if the sampler repeat mode is `SamplerRepeatMode.CLAMP_TO_BORDER`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_FLOAT_TRANSPARENT_BLACK
             */
            val FLOAT_TRANSPARENT_BLACK: SamplerBorderColor get() = SamplerBorderColor(0L)
            /**
             * Return an integer transparent black color when sampling outside the `[0.0, 1.0]` range. Only
             * effective if the sampler repeat mode is `SamplerRepeatMode.CLAMP_TO_BORDER`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_INT_TRANSPARENT_BLACK
             */
            val INT_TRANSPARENT_BLACK: SamplerBorderColor get() = SamplerBorderColor(1L)
            /**
             * Return a floating-point opaque black color when sampling outside the `[0.0, 1.0]` range. Only
             * effective if the sampler repeat mode is `SamplerRepeatMode.CLAMP_TO_BORDER`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_FLOAT_OPAQUE_BLACK
             */
            val FLOAT_OPAQUE_BLACK: SamplerBorderColor get() = SamplerBorderColor(2L)
            /**
             * Return an integer opaque black color when sampling outside the `[0.0, 1.0]` range. Only
             * effective if the sampler repeat mode is `SamplerRepeatMode.CLAMP_TO_BORDER`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_INT_OPAQUE_BLACK
             */
            val INT_OPAQUE_BLACK: SamplerBorderColor get() = SamplerBorderColor(3L)
            /**
             * Return a floating-point opaque white color when sampling outside the `[0.0, 1.0]` range. Only
             * effective if the sampler repeat mode is `SamplerRepeatMode.CLAMP_TO_BORDER`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_FLOAT_OPAQUE_WHITE
             */
            val FLOAT_OPAQUE_WHITE: SamplerBorderColor get() = SamplerBorderColor(4L)
            /**
             * Return an integer opaque white color when sampling outside the `[0.0, 1.0]` range. Only
             * effective if the sampler repeat mode is `SamplerRepeatMode.CLAMP_TO_BORDER`.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_INT_OPAQUE_WHITE
             */
            val INT_OPAQUE_WHITE: SamplerBorderColor get() = SamplerBorderColor(5L)
            /**
             * Represents the size of the `SamplerBorderColor` enum.
             *
             * Generated from Godot docs: RenderingDevice.SAMPLER_BORDER_COLOR_MAX
             */
            val MAX: SamplerBorderColor get() = SamplerBorderColor(6L)
        }
    }

    /**
     * Godot's `RenderingDevice.VertexFrequency` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`RenderingDevice.VertexFrequency.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.VertexFrequency
     */
    @JvmInline
    value class VertexFrequency(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Vertex attribute addressing is a function of the vertex. This is used to specify the rate at
             * which vertex attributes are pulled from buffers.
             *
             * Generated from Godot docs: RenderingDevice.VERTEX_FREQUENCY_VERTEX
             */
            val VERTEX: VertexFrequency get() = VertexFrequency(0L)
            /**
             * Vertex attribute addressing is a function of the instance index. This is used to specify the
             * rate at which vertex attributes are pulled from buffers.
             *
             * Generated from Godot docs: RenderingDevice.VERTEX_FREQUENCY_INSTANCE
             */
            val INSTANCE: VertexFrequency get() = VertexFrequency(1L)
        }
    }

    /**
     * Godot's `RenderingDevice.IndexBufferFormat` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.IndexBufferFormat.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.IndexBufferFormat
     */
    @JvmInline
    value class IndexBufferFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Index buffer in 16-bit unsigned integer format. This limits the maximum index that can be
             * specified to `65535`.
             *
             * Generated from Godot docs: RenderingDevice.INDEX_BUFFER_FORMAT_UINT16
             */
            val UINT16: IndexBufferFormat get() = IndexBufferFormat(0L)
            /**
             * Index buffer in 32-bit unsigned integer format. This limits the maximum index that can be
             * specified to `4294967295`.
             *
             * Generated from Godot docs: RenderingDevice.INDEX_BUFFER_FORMAT_UINT32
             */
            val UINT32: IndexBufferFormat get() = IndexBufferFormat(1L)
        }
    }

    /**
     * Godot's `RenderingDevice.StorageBufferUsage` bitfield as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`RenderingDevice.StorageBufferUsage.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.StorageBufferUsage
     */
    @JvmInline
    value class StorageBufferUsage(override val value: Long) : GodotEnumValue {
        infix fun or(other: StorageBufferUsage): StorageBufferUsage = StorageBufferUsage(value or other.value)

        infix fun and(other: StorageBufferUsage): StorageBufferUsage = StorageBufferUsage(value and other.value)

        infix fun xor(other: StorageBufferUsage): StorageBufferUsage = StorageBufferUsage(value xor other.value)

        fun inv(): StorageBufferUsage = StorageBufferUsage(value.inv())

        operator fun contains(other: StorageBufferUsage): Boolean = (value and other.value) == other.value

        companion object {
            val INDIRECT: StorageBufferUsage get() = StorageBufferUsage(1L)
        }
    }

    /**
     * Godot's `RenderingDevice.BufferCreationBits` bitfield as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`RenderingDevice.BufferCreationBits.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.BufferCreationBits
     */
    @JvmInline
    value class BufferCreationBits(override val value: Long) : GodotEnumValue {
        infix fun or(other: BufferCreationBits): BufferCreationBits = BufferCreationBits(value or other.value)

        infix fun and(other: BufferCreationBits): BufferCreationBits = BufferCreationBits(value and other.value)

        infix fun xor(other: BufferCreationBits): BufferCreationBits = BufferCreationBits(value xor other.value)

        fun inv(): BufferCreationBits = BufferCreationBits(value.inv())

        operator fun contains(other: BufferCreationBits): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Optionally, set this flag if you wish to use `buffer_get_device_address` functionality.
             *
             * Generated from Godot docs: RenderingDevice.BUFFER_CREATION_DEVICE_ADDRESS_BIT
             */
            val DEVICE_ADDRESS_BIT: BufferCreationBits get() = BufferCreationBits(1L)
            /**
             * Set this flag so that it is created as storage. This is useful if Compute Shaders need access
             * (for reading or writing) to the buffer, e.g. skeletal animations are processed in Compute
             * Shaders which need access to vertex buffers, to be later consumed by vertex shaders as part of
             * the regular rasterization pipeline.
             *
             * Generated from Godot docs: RenderingDevice.BUFFER_CREATION_AS_STORAGE_BIT
             */
            val AS_STORAGE_BIT: BufferCreationBits get() = BufferCreationBits(2L)
            /**
             * Allows usage of this buffer as input data for an acceleration structure build operation.
             *
             * Generated from Godot docs: RenderingDevice.BUFFER_CREATION_ACCELERATION_STRUCTURE_BUILD_INPUT_READ_ONLY_BIT
             */
            val ACCELERATION_STRUCTURE_BUILD_INPUT_READ_ONLY_BIT: BufferCreationBits get() = BufferCreationBits(8L)
        }
    }

    /**
     * Godot's `RenderingDevice.AccelerationStructureFlagBits` bitfield as a typed value: `.value` is
     * the raw number Godot uses, and the companion holds the named values
     * (`RenderingDevice.AccelerationStructureFlagBits.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.AccelerationStructureFlagBits
     */
    @JvmInline
    value class AccelerationStructureFlagBits(override val value: Long) : GodotEnumValue {
        infix fun or(other: AccelerationStructureFlagBits): AccelerationStructureFlagBits = AccelerationStructureFlagBits(value or other.value)

        infix fun and(other: AccelerationStructureFlagBits): AccelerationStructureFlagBits = AccelerationStructureFlagBits(value and other.value)

        infix fun xor(other: AccelerationStructureFlagBits): AccelerationStructureFlagBits = AccelerationStructureFlagBits(value xor other.value)

        fun inv(): AccelerationStructureFlagBits = AccelerationStructureFlagBits(value.inv())

        operator fun contains(other: AccelerationStructureFlagBits): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Allows the acceleration structure to be updated after it has been built.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_ALLOW_UPDATE_BIT
             */
            val ALLOW_UPDATE_BIT: AccelerationStructureFlagBits get() = AccelerationStructureFlagBits(1L)
            /**
             * Allows the acceleration structure to be compacted to reduce memory usage after it has been
             * built.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_ALLOW_COMPACTION_BIT
             */
            val ALLOW_COMPACTION_BIT: AccelerationStructureFlagBits get() = AccelerationStructureFlagBits(2L)
            /**
             * Prioritizes ray traversal performance over build performance when building the acceleration
             * structure.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_PREFER_FAST_TRACE_BIT
             */
            val PREFER_FAST_TRACE_BIT: AccelerationStructureFlagBits get() = AccelerationStructureFlagBits(4L)
            /**
             * Prioritizes build performance over ray traversal performance when building the acceleration
             * structure.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_PREFER_FAST_BUILD_BIT
             */
            val PREFER_FAST_BUILD_BIT: AccelerationStructureFlagBits get() = AccelerationStructureFlagBits(8L)
            /**
             * Reduces the memory usage of the acceleration structure, potentially at the cost of reduced ray
             * traversal performance.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_LOW_MEMORY_BIT
             */
            val LOW_MEMORY_BIT: AccelerationStructureFlagBits get() = AccelerationStructureFlagBits(16L)
        }
    }

    /**
     * Godot's `RenderingDevice.AccelerationStructureGeometryFlagBits` bitfield as a typed value:
     * `.value` is the raw number Godot uses, and the companion holds the named values
     * (`RenderingDevice.AccelerationStructureGeometryFlagBits.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.AccelerationStructureGeometryFlagBits
     */
    @JvmInline
    value class AccelerationStructureGeometryFlagBits(override val value: Long) : GodotEnumValue {
        infix fun or(other: AccelerationStructureGeometryFlagBits): AccelerationStructureGeometryFlagBits = AccelerationStructureGeometryFlagBits(value or other.value)

        infix fun and(other: AccelerationStructureGeometryFlagBits): AccelerationStructureGeometryFlagBits = AccelerationStructureGeometryFlagBits(value and other.value)

        infix fun xor(other: AccelerationStructureGeometryFlagBits): AccelerationStructureGeometryFlagBits = AccelerationStructureGeometryFlagBits(value xor other.value)

        fun inv(): AccelerationStructureGeometryFlagBits = AccelerationStructureGeometryFlagBits(value.inv())

        operator fun contains(other: AccelerationStructureGeometryFlagBits): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * An opaque geometry does not invoke the any hit shaders.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_GEOMETRY_OPAQUE_BIT
             */
            val OPAQUE_BIT: AccelerationStructureGeometryFlagBits get() = AccelerationStructureGeometryFlagBits(1L)
            /**
             * This geometry only calls the any hit shader a single time for each primitive.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_GEOMETRY_NO_DUPLICATE_ANY_HIT_INVOCATION_BIT
             */
            val NO_DUPLICATE_ANY_HIT_INVOCATION_BIT: AccelerationStructureGeometryFlagBits get() = AccelerationStructureGeometryFlagBits(2L)
        }
    }

    /**
     * Godot's `RenderingDevice.AccelerationStructureInstanceFlagBits` bitfield as a typed value:
     * `.value` is the raw number Godot uses, and the companion holds the named values
     * (`RenderingDevice.AccelerationStructureInstanceFlagBits.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.AccelerationStructureInstanceFlagBits
     */
    @JvmInline
    value class AccelerationStructureInstanceFlagBits(override val value: Long) : GodotEnumValue {
        infix fun or(other: AccelerationStructureInstanceFlagBits): AccelerationStructureInstanceFlagBits = AccelerationStructureInstanceFlagBits(value or other.value)

        infix fun and(other: AccelerationStructureInstanceFlagBits): AccelerationStructureInstanceFlagBits = AccelerationStructureInstanceFlagBits(value and other.value)

        infix fun xor(other: AccelerationStructureInstanceFlagBits): AccelerationStructureInstanceFlagBits = AccelerationStructureInstanceFlagBits(value xor other.value)

        fun inv(): AccelerationStructureInstanceFlagBits = AccelerationStructureInstanceFlagBits(value.inv())

        operator fun contains(other: AccelerationStructureInstanceFlagBits): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Disables triangle face culling for this instance during ray traversal.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_INSTANCE_TRIANGLE_FACING_CULL_DISABLE_BIT
             */
            val TRIANGLE_FACING_CULL_DISABLE_BIT: AccelerationStructureInstanceFlagBits get() = AccelerationStructureInstanceFlagBits(1L)
            /**
             * Flips the triangle facing direction for this instance during ray traversal.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_INSTANCE_TRIANGLE_FLIP_FACING_BIT
             */
            val TRIANGLE_FLIP_FACING_BIT: AccelerationStructureInstanceFlagBits get() = AccelerationStructureInstanceFlagBits(2L)
            /**
             * Forces all geometries in this instance to be treated as opaque, preventing any hit shaders from
             * being invoked.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_INSTANCE_FORCE_OPAQUE_BIT
             */
            val FORCE_OPAQUE_BIT: AccelerationStructureInstanceFlagBits get() = AccelerationStructureInstanceFlagBits(4L)
            /**
             * Forces all geometries in this instance to be treated as non-opaque, allowing any hit shaders to
             * be invoked.
             *
             * Generated from Godot docs: RenderingDevice.ACCELERATION_STRUCTURE_INSTANCE_FORCE_NO_OPAQUE_BIT
             */
            val FORCE_NO_OPAQUE_BIT: AccelerationStructureInstanceFlagBits get() = AccelerationStructureInstanceFlagBits(8L)
        }
    }

    /**
     * Godot's `RenderingDevice.UniformType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.UniformType.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.UniformType
     */
    @JvmInline
    value class UniformType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sampler uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_SAMPLER
             */
            val SAMPLER: UniformType get() = UniformType(0L)
            /**
             * Sampler uniform with a texture.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_SAMPLER_WITH_TEXTURE
             */
            val SAMPLER_WITH_TEXTURE: UniformType get() = UniformType(1L)
            /**
             * Texture uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_TEXTURE
             */
            val TEXTURE: UniformType get() = UniformType(2L)
            /**
             * Image uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_IMAGE
             */
            val IMAGE: UniformType get() = UniformType(3L)
            /**
             * Texture buffer uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_TEXTURE_BUFFER
             */
            val TEXTURE_BUFFER: UniformType get() = UniformType(4L)
            /**
             * Sampler uniform with a texture buffer.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_SAMPLER_WITH_TEXTURE_BUFFER
             */
            val SAMPLER_WITH_TEXTURE_BUFFER: UniformType get() = UniformType(5L)
            /**
             * Image buffer uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_IMAGE_BUFFER
             */
            val IMAGE_BUFFER: UniformType get() = UniformType(6L)
            /**
             * Uniform buffer uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_UNIFORM_BUFFER
             */
            val UNIFORM_BUFFER: UniformType get() = UniformType(7L)
            /**
             * Storage buffer (https://vkguide.dev/docs/chapter-4/storage_buffers/) uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_STORAGE_BUFFER
             */
            val STORAGE_BUFFER: UniformType get() = UniformType(8L)
            /**
             * Input attachment uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_INPUT_ATTACHMENT
             */
            val INPUT_ATTACHMENT: UniformType get() = UniformType(9L)
            /**
             * Same as UNIFORM_TYPE_UNIFORM_BUFFER but for buffers created with
             * BUFFER_CREATION_DYNAMIC_PERSISTENT_BIT. Note: This flag is not available to GD users due to
             * being too dangerous (i.e. wrong usage can result in visual glitches). It's exposed in case GD
             * users receive a buffer created with such flag from Godot.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_UNIFORM_BUFFER_DYNAMIC
             */
            val UNIFORM_BUFFER_DYNAMIC: UniformType get() = UniformType(10L)
            /**
             * Same as UNIFORM_TYPE_STORAGE_BUFFER but for buffers created with
             * BUFFER_CREATION_DYNAMIC_PERSISTENT_BIT. Note: This flag is not available to GD users due to
             * being too dangerous (i.e. wrong usage can result in visual glitches). It's exposed in case GD
             * users receive a buffer created with such flag from Godot.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_STORAGE_BUFFER_DYNAMIC
             */
            val STORAGE_BUFFER_DYNAMIC: UniformType get() = UniformType(11L)
            /**
             * Acceleration structure uniform.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_ACCELERATION_STRUCTURE
             */
            val ACCELERATION_STRUCTURE: UniformType get() = UniformType(12L)
            /**
             * Represents the size of the `UniformType` enum.
             *
             * Generated from Godot docs: RenderingDevice.UNIFORM_TYPE_MAX
             */
            val MAX: UniformType get() = UniformType(13L)
        }
    }

    /**
     * Godot's `RenderingDevice.RenderPrimitive` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`RenderingDevice.RenderPrimitive.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.RenderPrimitive
     */
    @JvmInline
    value class RenderPrimitive(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Point rendering primitive (with constant size, regardless of distance from camera).
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_POINTS
             */
            val POINTS: RenderPrimitive get() = RenderPrimitive(0L)
            /**
             * Line list rendering primitive. Lines are drawn separated from each other.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_LINES
             */
            val LINES: RenderPrimitive get() = RenderPrimitive(1L)
            /**
             * Line list rendering primitive with adjacency.
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#drawing-line-lists-with-adjacency)
             * Note: Adjacency is only useful with geometry shaders, which Godot does not expose.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_LINES_WITH_ADJACENCY
             */
            val LINES_WITH_ADJACENCY: RenderPrimitive get() = RenderPrimitive(2L)
            /**
             * Line strip rendering primitive. Lines drawn are connected to the previous vertex.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_LINESTRIPS
             */
            val LINESTRIPS: RenderPrimitive get() = RenderPrimitive(3L)
            /**
             * Line strip rendering primitive with adjacency.
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#drawing-line-strips-with-adjacency)
             * Note: Adjacency is only useful with geometry shaders, which Godot does not expose.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_LINESTRIPS_WITH_ADJACENCY
             */
            val LINESTRIPS_WITH_ADJACENCY: RenderPrimitive get() = RenderPrimitive(4L)
            /**
             * Triangle list rendering primitive. Triangles are drawn separated from each other.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_TRIANGLES
             */
            val TRIANGLES: RenderPrimitive get() = RenderPrimitive(5L)
            /**
             * Triangle list rendering primitive with adjacency.
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#drawing-triangle-lists-with-adjacency)
             * Note: Adjacency is only useful with geometry shaders, which Godot does not expose.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_TRIANGLES_WITH_ADJACENCY
             */
            val TRIANGLES_WITH_ADJACENCY: RenderPrimitive get() = RenderPrimitive(6L)
            /**
             * Triangle strip rendering primitive. Triangles drawn are connected to the previous triangle.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_TRIANGLE_STRIPS
             */
            val TRIANGLE_STRIPS: RenderPrimitive get() = RenderPrimitive(7L)
            /**
             * Triangle strip rendering primitive with adjacency.
             * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#drawing-triangle-strips-with-adjacency)
             * Note: Adjacency is only useful with geometry shaders, which Godot does not expose.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_TRIANGLE_STRIPS_WITH_AJACENCY
             */
            val TRIANGLE_STRIPS_WITH_AJACENCY: RenderPrimitive get() = RenderPrimitive(8L)
            /**
             * Triangle strip rendering primitive with primitive restart enabled. Triangles drawn are connected
             * to the previous triangle, but a primitive restart index can be specified before drawing to
             * create a second triangle strip after the specified index. Note: Only compatible with indexed
             * draws.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_TRIANGLE_STRIPS_WITH_RESTART_INDEX
             */
            val TRIANGLE_STRIPS_WITH_RESTART_INDEX: RenderPrimitive get() = RenderPrimitive(9L)
            /**
             * Tessellation patch rendering primitive. Only useful with tessellation shaders, which can be used
             * to deform these patches.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_TESSELATION_PATCH
             */
            val TESSELATION_PATCH: RenderPrimitive get() = RenderPrimitive(10L)
            /**
             * Represents the size of the `RenderPrimitive` enum.
             *
             * Generated from Godot docs: RenderingDevice.RENDER_PRIMITIVE_MAX
             */
            val MAX: RenderPrimitive get() = RenderPrimitive(11L)
        }
    }

    /**
     * Godot's `RenderingDevice.PolygonCullMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`RenderingDevice.PolygonCullMode.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.PolygonCullMode
     */
    @JvmInline
    value class PolygonCullMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not use polygon front face or backface culling.
             *
             * Generated from Godot docs: RenderingDevice.POLYGON_CULL_DISABLED
             */
            val DISABLED: PolygonCullMode get() = PolygonCullMode(0L)
            /**
             * Use polygon frontface culling (faces pointing towards the camera are hidden).
             *
             * Generated from Godot docs: RenderingDevice.POLYGON_CULL_FRONT
             */
            val FRONT: PolygonCullMode get() = PolygonCullMode(1L)
            /**
             * Use polygon backface culling (faces pointing away from the camera are hidden).
             *
             * Generated from Godot docs: RenderingDevice.POLYGON_CULL_BACK
             */
            val BACK: PolygonCullMode get() = PolygonCullMode(2L)
        }
    }

    /**
     * Godot's `RenderingDevice.PolygonFrontFace` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.PolygonFrontFace.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.PolygonFrontFace
     */
    @JvmInline
    value class PolygonFrontFace(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Clockwise winding order to determine which face of a polygon is its front face.
             *
             * Generated from Godot docs: RenderingDevice.POLYGON_FRONT_FACE_CLOCKWISE
             */
            val CLOCKWISE: PolygonFrontFace get() = PolygonFrontFace(0L)
            /**
             * Counter-clockwise winding order to determine which face of a polygon is its front face.
             *
             * Generated from Godot docs: RenderingDevice.POLYGON_FRONT_FACE_COUNTER_CLOCKWISE
             */
            val COUNTER_CLOCKWISE: PolygonFrontFace get() = PolygonFrontFace(1L)
        }
    }

    /**
     * Godot's `RenderingDevice.StencilOperation` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.StencilOperation.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.StencilOperation
     */
    @JvmInline
    value class StencilOperation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Keep the current stencil value.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_KEEP
             */
            val KEEP: StencilOperation get() = StencilOperation(0L)
            /**
             * Set the stencil value to `0`.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_ZERO
             */
            val ZERO: StencilOperation get() = StencilOperation(1L)
            /**
             * Replace the existing stencil value with the new one.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_REPLACE
             */
            val REPLACE: StencilOperation get() = StencilOperation(2L)
            /**
             * Increment the existing stencil value and clamp to the maximum representable unsigned value if
             * reached. Stencil bits are considered as an unsigned integer.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_INCREMENT_AND_CLAMP
             */
            val INCREMENT_AND_CLAMP: StencilOperation get() = StencilOperation(3L)
            /**
             * Decrement the existing stencil value and clamp to the minimum value if reached. Stencil bits are
             * considered as an unsigned integer.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_DECREMENT_AND_CLAMP
             */
            val DECREMENT_AND_CLAMP: StencilOperation get() = StencilOperation(4L)
            /**
             * Bitwise-invert the existing stencil value.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_INVERT
             */
            val INVERT: StencilOperation get() = StencilOperation(5L)
            /**
             * Increment the stencil value and wrap around to `0` if reaching the maximum representable
             * unsigned. Stencil bits are considered as an unsigned integer.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_INCREMENT_AND_WRAP
             */
            val INCREMENT_AND_WRAP: StencilOperation get() = StencilOperation(6L)
            /**
             * Decrement the stencil value and wrap around to the maximum representable unsigned if reaching
             * the minimum. Stencil bits are considered as an unsigned integer.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_DECREMENT_AND_WRAP
             */
            val DECREMENT_AND_WRAP: StencilOperation get() = StencilOperation(7L)
            /**
             * Represents the size of the `StencilOperation` enum.
             *
             * Generated from Godot docs: RenderingDevice.STENCIL_OP_MAX
             */
            val MAX: StencilOperation get() = StencilOperation(8L)
        }
    }

    /**
     * Godot's `RenderingDevice.CompareOperator` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`RenderingDevice.CompareOperator.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.CompareOperator
     */
    @JvmInline
    value class CompareOperator(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * "Never" comparison (opposite of `CompareOperator.ALWAYS`).
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_NEVER
             */
            val NEVER: CompareOperator get() = CompareOperator(0L)
            /**
             * "Less than" comparison.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_LESS
             */
            val LESS: CompareOperator get() = CompareOperator(1L)
            /**
             * "Equal" comparison.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_EQUAL
             */
            val EQUAL: CompareOperator get() = CompareOperator(2L)
            /**
             * "Less than or equal" comparison.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_LESS_OR_EQUAL
             */
            val LESS_OR_EQUAL: CompareOperator get() = CompareOperator(3L)
            /**
             * "Greater than" comparison.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_GREATER
             */
            val GREATER: CompareOperator get() = CompareOperator(4L)
            /**
             * "Not equal" comparison.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_NOT_EQUAL
             */
            val NOT_EQUAL: CompareOperator get() = CompareOperator(5L)
            /**
             * "Greater than or equal" comparison.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_GREATER_OR_EQUAL
             */
            val GREATER_OR_EQUAL: CompareOperator get() = CompareOperator(6L)
            /**
             * "Always" comparison (opposite of `CompareOperator.NEVER`).
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_ALWAYS
             */
            val ALWAYS: CompareOperator get() = CompareOperator(7L)
            /**
             * Represents the size of the `CompareOperator` enum.
             *
             * Generated from Godot docs: RenderingDevice.COMPARE_OP_MAX
             */
            val MAX: CompareOperator get() = CompareOperator(8L)
        }
    }

    /**
     * Godot's `RenderingDevice.LogicOperation` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.LogicOperation.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.LogicOperation
     */
    @JvmInline
    value class LogicOperation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Clear logic operation (result is always `0`). See also `LogicOperation.SET`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_CLEAR
             */
            val CLEAR: LogicOperation get() = LogicOperation(0L)
            /**
             * AND logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_AND
             */
            val AND: LogicOperation get() = LogicOperation(1L)
            /**
             * AND logic operation with the destination operand being inverted. See also
             * `LogicOperation.AND_INVERTED`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_AND_REVERSE
             */
            val AND_REVERSE: LogicOperation get() = LogicOperation(2L)
            /**
             * Copy logic operation (keeps the source value as-is). See also `LogicOperation.COPY_INVERTED` and
             * `LogicOperation.NO_OP`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_COPY
             */
            val COPY: LogicOperation get() = LogicOperation(3L)
            /**
             * AND logic operation with the source operand being inverted. See also
             * `LogicOperation.AND_REVERSE`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_AND_INVERTED
             */
            val AND_INVERTED: LogicOperation get() = LogicOperation(4L)
            /**
             * No-op logic operation (keeps the destination value as-is). See also `LogicOperation.COPY`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_NO_OP
             */
            val NO_OP: LogicOperation get() = LogicOperation(5L)
            /**
             * Exclusive or (XOR) logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_XOR
             */
            val XOR: LogicOperation get() = LogicOperation(6L)
            /**
             * OR logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_OR
             */
            val OR: LogicOperation get() = LogicOperation(7L)
            /**
             * Not-OR (NOR) logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_NOR
             */
            val NOR: LogicOperation get() = LogicOperation(8L)
            /**
             * Not-XOR (XNOR) logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_EQUIVALENT
             */
            val EQUIVALENT: LogicOperation get() = LogicOperation(9L)
            /**
             * Invert logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_INVERT
             */
            val INVERT: LogicOperation get() = LogicOperation(10L)
            /**
             * OR logic operation with the destination operand being inverted. See also
             * `LogicOperation.OR_REVERSE`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_OR_REVERSE
             */
            val OR_REVERSE: LogicOperation get() = LogicOperation(11L)
            /**
             * NOT logic operation (inverts the value). See also `LogicOperation.COPY`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_COPY_INVERTED
             */
            val COPY_INVERTED: LogicOperation get() = LogicOperation(12L)
            /**
             * OR logic operation with the source operand being inverted. See also `LogicOperation.OR_REVERSE`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_OR_INVERTED
             */
            val OR_INVERTED: LogicOperation get() = LogicOperation(13L)
            /**
             * Not-AND (NAND) logic operation.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_NAND
             */
            val NAND: LogicOperation get() = LogicOperation(14L)
            /**
             * SET logic operation (result is always `1`). See also `LogicOperation.CLEAR`.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_SET
             */
            val SET: LogicOperation get() = LogicOperation(15L)
            /**
             * Represents the size of the `LogicOperation` enum.
             *
             * Generated from Godot docs: RenderingDevice.LOGIC_OP_MAX
             */
            val MAX: LogicOperation get() = LogicOperation(16L)
        }
    }

    /**
     * Godot's `RenderingDevice.BlendFactor` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.BlendFactor.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.BlendFactor
     */
    @JvmInline
    value class BlendFactor(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Constant `0.0` blend factor.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ZERO
             */
            val ZERO: BlendFactor get() = BlendFactor(0L)
            /**
             * Constant `1.0` blend factor.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE
             */
            val ONE: BlendFactor get() = BlendFactor(1L)
            /**
             * Color blend factor is `source color`. Alpha blend factor is `source alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_SRC_COLOR
             */
            val SRC_COLOR: BlendFactor get() = BlendFactor(2L)
            /**
             * Color blend factor is `1.0 - source color`. Alpha blend factor is `1.0 - source alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_SRC_COLOR
             */
            val ONE_MINUS_SRC_COLOR: BlendFactor get() = BlendFactor(3L)
            /**
             * Color blend factor is `destination color`. Alpha blend factor is `destination alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_DST_COLOR
             */
            val DST_COLOR: BlendFactor get() = BlendFactor(4L)
            /**
             * Color blend factor is `1.0 - destination color`. Alpha blend factor is `1.0 - destination
             * alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_DST_COLOR
             */
            val ONE_MINUS_DST_COLOR: BlendFactor get() = BlendFactor(5L)
            /**
             * Color and alpha blend factor is `source alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_SRC_ALPHA
             */
            val SRC_ALPHA: BlendFactor get() = BlendFactor(6L)
            /**
             * Color and alpha blend factor is `1.0 - source alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_SRC_ALPHA
             */
            val ONE_MINUS_SRC_ALPHA: BlendFactor get() = BlendFactor(7L)
            /**
             * Color and alpha blend factor is `destination alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_DST_ALPHA
             */
            val DST_ALPHA: BlendFactor get() = BlendFactor(8L)
            /**
             * Color and alpha blend factor is `1.0 - destination alpha`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_DST_ALPHA
             */
            val ONE_MINUS_DST_ALPHA: BlendFactor get() = BlendFactor(9L)
            /**
             * Color blend factor is `blend constant color`. Alpha blend factor is `blend constant alpha` (see
             * `draw_list_set_blend_constants`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_CONSTANT_COLOR
             */
            val CONSTANT_COLOR: BlendFactor get() = BlendFactor(10L)
            /**
             * Color blend factor is `1.0 - blend constant color`. Alpha blend factor is `1.0 - blend constant
             * alpha` (see `draw_list_set_blend_constants`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_CONSTANT_COLOR
             */
            val ONE_MINUS_CONSTANT_COLOR: BlendFactor get() = BlendFactor(11L)
            /**
             * Color and alpha blend factor is `blend constant alpha` (see `draw_list_set_blend_constants`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_CONSTANT_ALPHA
             */
            val CONSTANT_ALPHA: BlendFactor get() = BlendFactor(12L)
            /**
             * Color and alpha blend factor is `1.0 - blend constant alpha` (see
             * `draw_list_set_blend_constants`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_CONSTANT_ALPHA
             */
            val ONE_MINUS_CONSTANT_ALPHA: BlendFactor get() = BlendFactor(13L)
            /**
             * Color blend factor is `min(source alpha, 1.0 - destination alpha)`. Alpha blend factor is `1.0`.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_SRC_ALPHA_SATURATE
             */
            val SRC_ALPHA_SATURATE: BlendFactor get() = BlendFactor(14L)
            /**
             * Color blend factor is `second source color`. Alpha blend factor is `second source alpha`. Only
             * relevant for dual-source blending.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_SRC1_COLOR
             */
            val SRC1_COLOR: BlendFactor get() = BlendFactor(15L)
            /**
             * Color blend factor is `1.0 - second source color`. Alpha blend factor is `1.0 - second source
             * alpha`. Only relevant for dual-source blending.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_SRC1_COLOR
             */
            val ONE_MINUS_SRC1_COLOR: BlendFactor get() = BlendFactor(16L)
            /**
             * Color and alpha blend factor is `second source alpha`. Only relevant for dual-source blending.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_SRC1_ALPHA
             */
            val SRC1_ALPHA: BlendFactor get() = BlendFactor(17L)
            /**
             * Color and alpha blend factor is `1.0 - second source alpha`. Only relevant for dual-source
             * blending.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_ONE_MINUS_SRC1_ALPHA
             */
            val ONE_MINUS_SRC1_ALPHA: BlendFactor get() = BlendFactor(18L)
            /**
             * Represents the size of the `BlendFactor` enum.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_FACTOR_MAX
             */
            val MAX: BlendFactor get() = BlendFactor(19L)
        }
    }

    /**
     * Godot's `RenderingDevice.BlendOperation` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.BlendOperation.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.BlendOperation
     */
    @JvmInline
    value class BlendOperation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Additive blending operation (`source + destination`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_OP_ADD
             */
            val ADD: BlendOperation get() = BlendOperation(0L)
            /**
             * Subtractive blending operation (`source - destination`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_OP_SUBTRACT
             */
            val SUBTRACT: BlendOperation get() = BlendOperation(1L)
            /**
             * Reverse subtractive blending operation (`destination - source`).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_OP_REVERSE_SUBTRACT
             */
            val REVERSE_SUBTRACT: BlendOperation get() = BlendOperation(2L)
            /**
             * Minimum blending operation (keep the lowest value of the two).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_OP_MINIMUM
             */
            val MINIMUM: BlendOperation get() = BlendOperation(3L)
            /**
             * Maximum blending operation (keep the highest value of the two).
             *
             * Generated from Godot docs: RenderingDevice.BLEND_OP_MAXIMUM
             */
            val MAXIMUM: BlendOperation get() = BlendOperation(4L)
            /**
             * Represents the size of the `BlendOperation` enum.
             *
             * Generated from Godot docs: RenderingDevice.BLEND_OP_MAX
             */
            val MAX: BlendOperation get() = BlendOperation(5L)
        }
    }

    /**
     * Godot's `RenderingDevice.PipelineDynamicStateFlags` bitfield as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`RenderingDevice.PipelineDynamicStateFlags.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.PipelineDynamicStateFlags
     */
    @JvmInline
    value class PipelineDynamicStateFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: PipelineDynamicStateFlags): PipelineDynamicStateFlags = PipelineDynamicStateFlags(value or other.value)

        infix fun and(other: PipelineDynamicStateFlags): PipelineDynamicStateFlags = PipelineDynamicStateFlags(value and other.value)

        infix fun xor(other: PipelineDynamicStateFlags): PipelineDynamicStateFlags = PipelineDynamicStateFlags(value xor other.value)

        fun inv(): PipelineDynamicStateFlags = PipelineDynamicStateFlags(value.inv())

        operator fun contains(other: PipelineDynamicStateFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Allows dynamically changing the width of rendering lines.
             *
             * Generated from Godot docs: RenderingDevice.DYNAMIC_STATE_LINE_WIDTH
             */
            val LINE_WIDTH: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(1L)
            /**
             * Allows dynamically changing the depth bias.
             *
             * Generated from Godot docs: RenderingDevice.DYNAMIC_STATE_DEPTH_BIAS
             */
            val DEPTH_BIAS: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(2L)
            val BLEND_CONSTANTS: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(4L)
            val DEPTH_BOUNDS: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(8L)
            val STENCIL_COMPARE_MASK: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(16L)
            val STENCIL_WRITE_MASK: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(32L)
            val STENCIL_REFERENCE: PipelineDynamicStateFlags get() = PipelineDynamicStateFlags(64L)
        }
    }

    /**
     * Godot's `RenderingDevice.InitialAction` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.InitialAction.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.InitialAction
     */
    @JvmInline
    value class InitialAction(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Load the previous contents of the framebuffer.
             *
             * Generated from Godot docs: RenderingDevice.INITIAL_ACTION_LOAD
             */
            val LOAD: InitialAction get() = InitialAction(0L)
            /**
             * Clear the whole framebuffer or its specified region.
             *
             * Generated from Godot docs: RenderingDevice.INITIAL_ACTION_CLEAR
             */
            val CLEAR: InitialAction get() = InitialAction(1L)
            /**
             * Ignore the previous contents of the framebuffer. This is the fastest option if you'll overwrite
             * all of the pixels and don't need to read any of them.
             *
             * Generated from Godot docs: RenderingDevice.INITIAL_ACTION_DISCARD
             */
            val DISCARD: InitialAction get() = InitialAction(2L)
            /**
             * Represents the size of the `InitialAction` enum.
             *
             * Generated from Godot docs: RenderingDevice.INITIAL_ACTION_MAX
             */
            val MAX: InitialAction get() = InitialAction(3L)
            val CLEAR_REGION: InitialAction get() = InitialAction(1L)
            val CLEAR_REGION_CONTINUE: InitialAction get() = InitialAction(1L)
            val KEEP: InitialAction get() = InitialAction(0L)
            val DROP: InitialAction get() = InitialAction(2L)
            val CONTINUE: InitialAction get() = InitialAction(0L)
        }
    }

    /**
     * Godot's `RenderingDevice.FinalAction` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.FinalAction.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.FinalAction
     */
    @JvmInline
    value class FinalAction(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Store the result of the draw list in the framebuffer. This is generally what you want to do.
             *
             * Generated from Godot docs: RenderingDevice.FINAL_ACTION_STORE
             */
            val STORE: FinalAction get() = FinalAction(0L)
            /**
             * Discard the contents of the framebuffer. This is the fastest option if you don't need to use the
             * results of the draw list.
             *
             * Generated from Godot docs: RenderingDevice.FINAL_ACTION_DISCARD
             */
            val DISCARD: FinalAction get() = FinalAction(1L)
            /**
             * Represents the size of the `FinalAction` enum.
             *
             * Generated from Godot docs: RenderingDevice.FINAL_ACTION_MAX
             */
            val MAX: FinalAction get() = FinalAction(2L)
            val READ: FinalAction get() = FinalAction(0L)
            val CONTINUE: FinalAction get() = FinalAction(0L)
        }
    }

    /**
     * Godot's `RenderingDevice.ShaderStage` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.ShaderStage.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.ShaderStage
     */
    @JvmInline
    value class ShaderStage(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Vertex shader stage. This can be used to manipulate vertices from a shader (but not create new
             * vertices).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_VERTEX
             */
            val VERTEX: ShaderStage get() = ShaderStage(0L)
            /**
             * Fragment shader stage (called "pixel shader" in Direct3D). This can be used to manipulate pixels
             * from a shader.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_FRAGMENT
             */
            val FRAGMENT: ShaderStage get() = ShaderStage(1L)
            /**
             * Tessellation control shader stage. This can be used to create additional geometry from a shader.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_TESSELATION_CONTROL
             */
            val TESSELATION_CONTROL: ShaderStage get() = ShaderStage(2L)
            /**
             * Tessellation evaluation shader stage. This can be used to create additional geometry from a
             * shader.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_TESSELATION_EVALUATION
             */
            val TESSELATION_EVALUATION: ShaderStage get() = ShaderStage(3L)
            /**
             * Compute shader stage. This can be used to run arbitrary computing tasks in a shader, performing
             * them on the GPU instead of the CPU.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_COMPUTE
             */
            val COMPUTE: ShaderStage get() = ShaderStage(4L)
            /**
             * Ray generation shader stage. This can be used to generate primary rays.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_RAYGEN
             */
            val RAYGEN: ShaderStage get() = ShaderStage(5L)
            /**
             * Any hit shader stage. Invoked when ray intersections are not opaque. This can be used to specify
             * what happens when a ray hits any of the geometry in the scene.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_ANY_HIT
             */
            val ANY_HIT: ShaderStage get() = ShaderStage(6L)
            /**
             * Closest hit shader stage. This can be used to specify what happens when a ray hits the closest
             * geometry in the scene.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_CLOSEST_HIT
             */
            val CLOSEST_HIT: ShaderStage get() = ShaderStage(7L)
            /**
             * Miss shader stage. This can be used to specify what happens if a ray does not hit anything in
             * the scene.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_MISS
             */
            val MISS: ShaderStage get() = ShaderStage(8L)
            /**
             * Intersection shader stage. The intersection shader for triangles is built-in. This can be used
             * to compute ray intersections with primitives that are not triangles.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_INTERSECTION
             */
            val INTERSECTION: ShaderStage get() = ShaderStage(9L)
            /**
             * Represents the size of the `ShaderStage` enum.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_MAX
             */
            val MAX: ShaderStage get() = ShaderStage(10L)
            /**
             * Vertex shader stage bit (see also `ShaderStage.VERTEX`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_VERTEX_BIT
             */
            val VERTEX_BIT: ShaderStage get() = ShaderStage(1L)
            /**
             * Fragment shader stage bit (see also `ShaderStage.FRAGMENT`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_FRAGMENT_BIT
             */
            val FRAGMENT_BIT: ShaderStage get() = ShaderStage(2L)
            /**
             * Tessellation control shader stage bit (see also `ShaderStage.TESSELATION_CONTROL`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_TESSELATION_CONTROL_BIT
             */
            val TESSELATION_CONTROL_BIT: ShaderStage get() = ShaderStage(4L)
            /**
             * Tessellation evaluation shader stage bit (see also `ShaderStage.TESSELATION_EVALUATION`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_TESSELATION_EVALUATION_BIT
             */
            val TESSELATION_EVALUATION_BIT: ShaderStage get() = ShaderStage(8L)
            /**
             * Compute shader stage bit (see also `ShaderStage.COMPUTE`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_COMPUTE_BIT
             */
            val COMPUTE_BIT: ShaderStage get() = ShaderStage(16L)
            /**
             * Ray generation shader stage bit (see also `ShaderStage.RAYGEN`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_RAYGEN_BIT
             */
            val RAYGEN_BIT: ShaderStage get() = ShaderStage(32L)
            /**
             * Any hit shader stage bit (see also `ShaderStage.ANY_HIT`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_ANY_HIT_BIT
             */
            val ANY_HIT_BIT: ShaderStage get() = ShaderStage(64L)
            /**
             * Closest hit shader stage bit (see also `ShaderStage.CLOSEST_HIT`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_CLOSEST_HIT_BIT
             */
            val CLOSEST_HIT_BIT: ShaderStage get() = ShaderStage(128L)
            /**
             * Miss shader stage bit (see also `ShaderStage.MISS`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_MISS_BIT
             */
            val MISS_BIT: ShaderStage get() = ShaderStage(256L)
            /**
             * Intersection shader stage bit (see also `ShaderStage.INTERSECTION`).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_STAGE_INTERSECTION_BIT
             */
            val INTERSECTION_BIT: ShaderStage get() = ShaderStage(512L)
        }
    }

    /**
     * Godot's `RenderingDevice.ShaderLanguage` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.ShaderLanguage.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.ShaderLanguage
     */
    @JvmInline
    value class ShaderLanguage(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Khronos' GLSL shading language (used natively by OpenGL and Vulkan). This is the language used
             * for core Godot shaders.
             *
             * Generated from Godot docs: RenderingDevice.SHADER_LANGUAGE_GLSL
             */
            val GLSL: ShaderLanguage get() = ShaderLanguage(0L)
            /**
             * Microsoft's High-Level Shading Language (used natively by Direct3D, but can also be used in
             * Vulkan).
             *
             * Generated from Godot docs: RenderingDevice.SHADER_LANGUAGE_HLSL
             */
            val HLSL: ShaderLanguage get() = ShaderLanguage(1L)
        }
    }

    /**
     * Godot's `RenderingDevice.PipelineSpecializationConstantType` enum as a typed value: `.value` is
     * the raw number Godot uses, and the companion holds the named values
     * (`RenderingDevice.PipelineSpecializationConstantType.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.PipelineSpecializationConstantType
     */
    @JvmInline
    value class PipelineSpecializationConstantType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Boolean specialization constant.
             *
             * Generated from Godot docs: RenderingDevice.PIPELINE_SPECIALIZATION_CONSTANT_TYPE_BOOL
             */
            val BOOL: PipelineSpecializationConstantType get() = PipelineSpecializationConstantType(0L)
            /**
             * Integer specialization constant.
             *
             * Generated from Godot docs: RenderingDevice.PIPELINE_SPECIALIZATION_CONSTANT_TYPE_INT
             */
            val INT: PipelineSpecializationConstantType get() = PipelineSpecializationConstantType(1L)
            /**
             * Floating-point specialization constant.
             *
             * Generated from Godot docs: RenderingDevice.PIPELINE_SPECIALIZATION_CONSTANT_TYPE_FLOAT
             */
            val FLOAT: PipelineSpecializationConstantType get() = PipelineSpecializationConstantType(2L)
        }
    }

    /**
     * Godot's `RenderingDevice.Features` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`RenderingDevice.Features.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.Features
     */
    @JvmInline
    value class Features(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Support for MetalFX spatial upscaling.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_METALFX_SPATIAL
             */
            val METALFX_SPATIAL: Features get() = Features(3L)
            /**
             * Support for MetalFX temporal upscaling.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_METALFX_TEMPORAL
             */
            val METALFX_TEMPORAL: Features get() = Features(4L)
            /**
             * Features support for buffer device address extension.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_BUFFER_DEVICE_ADDRESS
             */
            val BUFFER_DEVICE_ADDRESS: Features get() = Features(6L)
            /**
             * Support for 32-bit image atomic operations.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_IMAGE_ATOMIC_32_BIT
             */
            val IMAGE_ATOMIC_32_BIT: Features get() = Features(7L)
            /**
             * Support for ray query extension.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_RAY_QUERY
             */
            val RAY_QUERY: Features get() = Features(11L)
            /**
             * Support for raytracing pipeline extension.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_RAYTRACING_PIPELINE
             */
            val RAYTRACING_PIPELINE: Features get() = Features(12L)
            /**
             * Support for high dynamic range (HDR) output.
             *
             * Generated from Godot docs: RenderingDevice.SUPPORTS_HDR_OUTPUT
             */
            val HDR_OUTPUT: Features get() = Features(13L)
        }
    }

    /**
     * Godot's `RenderingDevice.Limit` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`RenderingDevice.Limit.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.Limit
     */
    @JvmInline
    value class Limit(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Maximum number of uniform sets that can be bound at a given time.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_BOUND_UNIFORM_SETS
             */
            val MAX_BOUND_UNIFORM_SETS: Limit get() = Limit(0L)
            /**
             * Maximum number of color framebuffer attachments that can be used at a given time.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_FRAMEBUFFER_COLOR_ATTACHMENTS
             */
            val MAX_FRAMEBUFFER_COLOR_ATTACHMENTS: Limit get() = Limit(1L)
            /**
             * Maximum number of textures that can be used per uniform set.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURES_PER_UNIFORM_SET
             */
            val MAX_TEXTURES_PER_UNIFORM_SET: Limit get() = Limit(2L)
            /**
             * Maximum number of samplers that can be used per uniform set.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_SAMPLERS_PER_UNIFORM_SET
             */
            val MAX_SAMPLERS_PER_UNIFORM_SET: Limit get() = Limit(3L)
            /**
             * Maximum number of storage buffers (https://vkguide.dev/docs/chapter-4/storage_buffers/) per
             * uniform set.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_STORAGE_BUFFERS_PER_UNIFORM_SET
             */
            val MAX_STORAGE_BUFFERS_PER_UNIFORM_SET: Limit get() = Limit(4L)
            /**
             * Maximum number of storage images per uniform set.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_STORAGE_IMAGES_PER_UNIFORM_SET
             */
            val MAX_STORAGE_IMAGES_PER_UNIFORM_SET: Limit get() = Limit(5L)
            /**
             * Maximum number of uniform buffers per uniform set.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_UNIFORM_BUFFERS_PER_UNIFORM_SET
             */
            val MAX_UNIFORM_BUFFERS_PER_UNIFORM_SET: Limit get() = Limit(6L)
            /**
             * Maximum index for an indexed draw command.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_DRAW_INDEXED_INDEX
             */
            val MAX_DRAW_INDEXED_INDEX: Limit get() = Limit(7L)
            /**
             * Maximum height of a framebuffer (in pixels).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_FRAMEBUFFER_HEIGHT
             */
            val MAX_FRAMEBUFFER_HEIGHT: Limit get() = Limit(8L)
            /**
             * Maximum width of a framebuffer (in pixels).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_FRAMEBUFFER_WIDTH
             */
            val MAX_FRAMEBUFFER_WIDTH: Limit get() = Limit(9L)
            /**
             * Maximum number of texture array layers.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURE_ARRAY_LAYERS
             */
            val MAX_TEXTURE_ARRAY_LAYERS: Limit get() = Limit(10L)
            /**
             * Maximum supported 1-dimensional texture size (in pixels on a single axis).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURE_SIZE_1D
             */
            val MAX_TEXTURE_SIZE_1D: Limit get() = Limit(11L)
            /**
             * Maximum supported 2-dimensional texture size (in pixels on a single axis).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURE_SIZE_2D
             */
            val MAX_TEXTURE_SIZE_2D: Limit get() = Limit(12L)
            /**
             * Maximum supported 3-dimensional texture size (in pixels on a single axis).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURE_SIZE_3D
             */
            val MAX_TEXTURE_SIZE_3D: Limit get() = Limit(13L)
            /**
             * Maximum supported cubemap texture size (in pixels on a single axis of a single face).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURE_SIZE_CUBE
             */
            val MAX_TEXTURE_SIZE_CUBE: Limit get() = Limit(14L)
            /**
             * Maximum number of textures per shader stage.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_TEXTURES_PER_SHADER_STAGE
             */
            val MAX_TEXTURES_PER_SHADER_STAGE: Limit get() = Limit(15L)
            /**
             * Maximum number of samplers per shader stage.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_SAMPLERS_PER_SHADER_STAGE
             */
            val MAX_SAMPLERS_PER_SHADER_STAGE: Limit get() = Limit(16L)
            /**
             * Maximum number of storage buffers (https://vkguide.dev/docs/chapter-4/storage_buffers/) per
             * shader stage.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_STORAGE_BUFFERS_PER_SHADER_STAGE
             */
            val MAX_STORAGE_BUFFERS_PER_SHADER_STAGE: Limit get() = Limit(17L)
            /**
             * Maximum number of storage images per shader stage.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_STORAGE_IMAGES_PER_SHADER_STAGE
             */
            val MAX_STORAGE_IMAGES_PER_SHADER_STAGE: Limit get() = Limit(18L)
            /**
             * Maximum number of uniform buffers per uniform set.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_UNIFORM_BUFFERS_PER_SHADER_STAGE
             */
            val MAX_UNIFORM_BUFFERS_PER_SHADER_STAGE: Limit get() = Limit(19L)
            /**
             * Maximum size of a push constant. A lot of devices are limited to 128 bytes, so try to avoid
             * exceeding 128 bytes in push constants to ensure compatibility even if your GPU is reporting a
             * higher value.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_PUSH_CONSTANT_SIZE
             */
            val MAX_PUSH_CONSTANT_SIZE: Limit get() = Limit(20L)
            /**
             * Maximum size of a uniform buffer.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_UNIFORM_BUFFER_SIZE
             */
            val MAX_UNIFORM_BUFFER_SIZE: Limit get() = Limit(21L)
            /**
             * Maximum vertex input attribute offset.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_VERTEX_INPUT_ATTRIBUTE_OFFSET
             */
            val MAX_VERTEX_INPUT_ATTRIBUTE_OFFSET: Limit get() = Limit(22L)
            /**
             * Maximum number of vertex input attributes.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_VERTEX_INPUT_ATTRIBUTES
             */
            val MAX_VERTEX_INPUT_ATTRIBUTES: Limit get() = Limit(23L)
            /**
             * Maximum number of vertex input bindings.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_VERTEX_INPUT_BINDINGS
             */
            val MAX_VERTEX_INPUT_BINDINGS: Limit get() = Limit(24L)
            /**
             * Maximum vertex input binding stride.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_VERTEX_INPUT_BINDING_STRIDE
             */
            val MAX_VERTEX_INPUT_BINDING_STRIDE: Limit get() = Limit(25L)
            /**
             * Minimum uniform buffer offset alignment.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MIN_UNIFORM_BUFFER_OFFSET_ALIGNMENT
             */
            val MIN_UNIFORM_BUFFER_OFFSET_ALIGNMENT: Limit get() = Limit(26L)
            /**
             * Maximum shared memory size for compute shaders.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_SHARED_MEMORY_SIZE
             */
            val MAX_COMPUTE_SHARED_MEMORY_SIZE: Limit get() = Limit(27L)
            /**
             * Maximum number of workgroups for compute shaders on the X axis.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_COUNT_X
             */
            val MAX_COMPUTE_WORKGROUP_COUNT_X: Limit get() = Limit(28L)
            /**
             * Maximum number of workgroups for compute shaders on the Y axis.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_COUNT_Y
             */
            val MAX_COMPUTE_WORKGROUP_COUNT_Y: Limit get() = Limit(29L)
            /**
             * Maximum number of workgroups for compute shaders on the Z axis.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_COUNT_Z
             */
            val MAX_COMPUTE_WORKGROUP_COUNT_Z: Limit get() = Limit(30L)
            /**
             * Maximum number of workgroup invocations for compute shaders.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_INVOCATIONS
             */
            val MAX_COMPUTE_WORKGROUP_INVOCATIONS: Limit get() = Limit(31L)
            /**
             * Maximum workgroup size for compute shaders on the X axis.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_SIZE_X
             */
            val MAX_COMPUTE_WORKGROUP_SIZE_X: Limit get() = Limit(32L)
            /**
             * Maximum workgroup size for compute shaders on the Y axis.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_SIZE_Y
             */
            val MAX_COMPUTE_WORKGROUP_SIZE_Y: Limit get() = Limit(33L)
            /**
             * Maximum workgroup size for compute shaders on the Z axis.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_COMPUTE_WORKGROUP_SIZE_Z
             */
            val MAX_COMPUTE_WORKGROUP_SIZE_Z: Limit get() = Limit(34L)
            /**
             * Maximum viewport width (in pixels).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_VIEWPORT_DIMENSIONS_X
             */
            val MAX_VIEWPORT_DIMENSIONS_X: Limit get() = Limit(35L)
            /**
             * Maximum viewport height (in pixels).
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_MAX_VIEWPORT_DIMENSIONS_Y
             */
            val MAX_VIEWPORT_DIMENSIONS_Y: Limit get() = Limit(36L)
            /**
             * Returns the smallest value for `ProjectSettings.rendering/scaling_3d/scale` when using the
             * MetalFX temporal upscaler. Note: The returned value is multiplied by a factor of `1000000` to
             * preserve 6 digits of precision. It must be divided by `1000000.0` to convert the value to a
             * floating point number.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_METALFX_TEMPORAL_SCALER_MIN_SCALE
             */
            val METALFX_TEMPORAL_SCALER_MIN_SCALE: Limit get() = Limit(46L)
            /**
             * Returns the largest value for `ProjectSettings.rendering/scaling_3d/scale` when using the
             * MetalFX temporal upscaler. Note: The returned value is multiplied by a factor of `1000000` to
             * preserve 6 digits of precision. It must be divided by `1000000.0` to convert the value to a
             * floating point number.
             *
             * Generated from Godot docs: RenderingDevice.LIMIT_METALFX_TEMPORAL_SCALER_MAX_SCALE
             */
            val METALFX_TEMPORAL_SCALER_MAX_SCALE: Limit get() = Limit(47L)
        }
    }

    /**
     * Godot's `RenderingDevice.MemoryType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.MemoryType.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.MemoryType
     */
    @JvmInline
    value class MemoryType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Memory taken by textures.
             *
             * Generated from Godot docs: RenderingDevice.MEMORY_TEXTURES
             */
            val TEXTURES: MemoryType get() = MemoryType(0L)
            /**
             * Memory taken by buffers.
             *
             * Generated from Godot docs: RenderingDevice.MEMORY_BUFFERS
             */
            val BUFFERS: MemoryType get() = MemoryType(1L)
            /**
             * Total memory taken. This is greater than the sum of `MemoryType.TEXTURES` and
             * `MemoryType.BUFFERS`, as it also includes miscellaneous memory usage.
             *
             * Generated from Godot docs: RenderingDevice.MEMORY_TOTAL
             */
            val TOTAL: MemoryType get() = MemoryType(2L)
        }
    }

    /**
     * Godot's `RenderingDevice.BreadcrumbMarker` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`RenderingDevice.BreadcrumbMarker.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.BreadcrumbMarker
     */
    @JvmInline
    value class BreadcrumbMarker(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No breadcrumb marker will be added.
             *
             * Generated from Godot docs: RenderingDevice.NONE
             */
            val NONE: BreadcrumbMarker get() = BreadcrumbMarker(0L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include
             * `"REFLECTION_PROBES"` for added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.REFLECTION_PROBES
             */
            val REFLECTION_PROBES: BreadcrumbMarker get() = BreadcrumbMarker(65536L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"SKY_PASS"` for
             * added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.SKY_PASS
             */
            val SKY_PASS: BreadcrumbMarker get() = BreadcrumbMarker(131072L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"LIGHTMAPPER_PASS"`
             * for added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.LIGHTMAPPER_PASS
             */
            val LIGHTMAPPER_PASS: BreadcrumbMarker get() = BreadcrumbMarker(196608L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include
             * `"SHADOW_PASS_DIRECTIONAL"` for added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.SHADOW_PASS_DIRECTIONAL
             */
            val SHADOW_PASS_DIRECTIONAL: BreadcrumbMarker get() = BreadcrumbMarker(262144L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"SHADOW_PASS_CUBE"`
             * for added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.SHADOW_PASS_CUBE
             */
            val SHADOW_PASS_CUBE: BreadcrumbMarker get() = BreadcrumbMarker(327680L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"OPAQUE_PASS"` for
             * added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.OPAQUE_PASS
             */
            val OPAQUE_PASS: BreadcrumbMarker get() = BreadcrumbMarker(393216L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"ALPHA_PASS"` for
             * added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.ALPHA_PASS
             */
            val ALPHA_PASS: BreadcrumbMarker get() = BreadcrumbMarker(458752L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"TRANSPARENT_PASS"`
             * for added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.TRANSPARENT_PASS
             */
            val TRANSPARENT_PASS: BreadcrumbMarker get() = BreadcrumbMarker(524288L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include
             * `"POST_PROCESSING_PASS"` for added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.POST_PROCESSING_PASS
             */
            val POST_PROCESSING_PASS: BreadcrumbMarker get() = BreadcrumbMarker(589824L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"BLIT_PASS"` for
             * added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.BLIT_PASS
             */
            val BLIT_PASS: BreadcrumbMarker get() = BreadcrumbMarker(655360L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"UI_PASS"` for
             * added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.UI_PASS
             */
            val UI_PASS: BreadcrumbMarker get() = BreadcrumbMarker(720896L)
            /**
             * During a GPU crash in dev or debug mode, Godot's error message will include `"DEBUG_PASS"` for
             * added context as to when the crash occurred.
             *
             * Generated from Godot docs: RenderingDevice.DEBUG_PASS
             */
            val DEBUG_PASS: BreadcrumbMarker get() = BreadcrumbMarker(786432L)
        }
    }

    /**
     * Godot's `RenderingDevice.DrawFlags` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`RenderingDevice.DrawFlags.<NAME>`).
     *
     * Generated from Godot docs: RenderingDevice.DrawFlags
     */
    @JvmInline
    value class DrawFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: DrawFlags): DrawFlags = DrawFlags(value or other.value)

        infix fun and(other: DrawFlags): DrawFlags = DrawFlags(value and other.value)

        infix fun xor(other: DrawFlags): DrawFlags = DrawFlags(value xor other.value)

        fun inv(): DrawFlags = DrawFlags(value.inv())

        operator fun contains(other: DrawFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Do not clear or ignore any attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_DEFAULT_ALL
             */
            val DEFAULT_ALL: DrawFlags get() = DrawFlags(0L)
            /**
             * Clear the first color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_0
             */
            val CLEAR_COLOR_0: DrawFlags get() = DrawFlags(1L)
            /**
             * Clear the second color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_1
             */
            val CLEAR_COLOR_1: DrawFlags get() = DrawFlags(2L)
            /**
             * Clear the third color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_2
             */
            val CLEAR_COLOR_2: DrawFlags get() = DrawFlags(4L)
            /**
             * Clear the fourth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_3
             */
            val CLEAR_COLOR_3: DrawFlags get() = DrawFlags(8L)
            /**
             * Clear the fifth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_4
             */
            val CLEAR_COLOR_4: DrawFlags get() = DrawFlags(16L)
            /**
             * Clear the sixth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_5
             */
            val CLEAR_COLOR_5: DrawFlags get() = DrawFlags(32L)
            /**
             * Clear the seventh color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_6
             */
            val CLEAR_COLOR_6: DrawFlags get() = DrawFlags(64L)
            /**
             * Clear the eighth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_7
             */
            val CLEAR_COLOR_7: DrawFlags get() = DrawFlags(128L)
            /**
             * Mask for clearing all color attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_MASK
             */
            val CLEAR_COLOR_MASK: DrawFlags get() = DrawFlags(255L)
            /**
             * Clear all color attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_COLOR_ALL
             */
            val CLEAR_COLOR_ALL: DrawFlags get() = DrawFlags(255L)
            /**
             * Ignore the previous contents of the first color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_0
             */
            val IGNORE_COLOR_0: DrawFlags get() = DrawFlags(256L)
            /**
             * Ignore the previous contents of the second color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_1
             */
            val IGNORE_COLOR_1: DrawFlags get() = DrawFlags(512L)
            /**
             * Ignore the previous contents of the third color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_2
             */
            val IGNORE_COLOR_2: DrawFlags get() = DrawFlags(1024L)
            /**
             * Ignore the previous contents of the fourth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_3
             */
            val IGNORE_COLOR_3: DrawFlags get() = DrawFlags(2048L)
            /**
             * Ignore the previous contents of the fifth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_4
             */
            val IGNORE_COLOR_4: DrawFlags get() = DrawFlags(4096L)
            /**
             * Ignore the previous contents of the sixth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_5
             */
            val IGNORE_COLOR_5: DrawFlags get() = DrawFlags(8192L)
            /**
             * Ignore the previous contents of the seventh color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_6
             */
            val IGNORE_COLOR_6: DrawFlags get() = DrawFlags(16384L)
            /**
             * Ignore the previous contents of the eighth color attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_7
             */
            val IGNORE_COLOR_7: DrawFlags get() = DrawFlags(32768L)
            /**
             * Mask for ignoring all the previous contents of the color attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_MASK
             */
            val IGNORE_COLOR_MASK: DrawFlags get() = DrawFlags(65280L)
            /**
             * Ignore the previous contents of all color attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_COLOR_ALL
             */
            val IGNORE_COLOR_ALL: DrawFlags get() = DrawFlags(65280L)
            /**
             * Clear the depth attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_DEPTH
             */
            val CLEAR_DEPTH: DrawFlags get() = DrawFlags(65536L)
            /**
             * Ignore the previous contents of the depth attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_DEPTH
             */
            val IGNORE_DEPTH: DrawFlags get() = DrawFlags(131072L)
            /**
             * Clear the stencil attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_STENCIL
             */
            val CLEAR_STENCIL: DrawFlags get() = DrawFlags(262144L)
            /**
             * Ignore the previous contents of the stencil attachment.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_STENCIL
             */
            val IGNORE_STENCIL: DrawFlags get() = DrawFlags(524288L)
            /**
             * Clear all attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_CLEAR_ALL
             */
            val CLEAR_ALL: DrawFlags get() = DrawFlags(327935L)
            /**
             * Ignore the previous contents of all attachments.
             *
             * Generated from Godot docs: RenderingDevice.DRAW_IGNORE_ALL
             */
            val IGNORE_ALL: DrawFlags get() = DrawFlags(720640L)
        }
    }

    companion object {
        const val INVALID_ID: Long = -1L
        const val INVALID_FORMAT_ID: Long = -1L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderingDevice? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RenderingDevice? =
            if (handle.address() == 0L) null else RenderingDevice(GodotHandle(handle))

        private const val TEXTURE_CREATE_HASH = 3709173589L
        private val textureCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_create", TEXTURE_CREATE_HASH)
        }

        private const val TEXTURE_CREATE_SHARED_HASH = 3178156134L
        private val textureCreateSharedBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_create_shared", TEXTURE_CREATE_SHARED_HASH)
        }

        private const val TEXTURE_CREATE_SHARED_FROM_SLICE_HASH = 1808971279L
        private val textureCreateSharedFromSliceBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_create_shared_from_slice", TEXTURE_CREATE_SHARED_FROM_SLICE_HASH)
        }

        private const val TEXTURE_CREATE_FROM_EXTENSION_HASH = 3732868568L
        private val textureCreateFromExtensionBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_create_from_extension", TEXTURE_CREATE_FROM_EXTENSION_HASH)
        }

        private const val TEXTURE_UPDATE_HASH = 1349464008L
        private val textureUpdateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_update", TEXTURE_UPDATE_HASH)
        }

        private const val TEXTURE_GET_DATA_HASH = 1859412099L
        private val textureGetDataBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_get_data", TEXTURE_GET_DATA_HASH)
        }

        private const val TEXTURE_GET_DATA_ASYNC_HASH = 498832090L
        private val textureGetDataAsyncBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_get_data_async", TEXTURE_GET_DATA_ASYNC_HASH)
        }

        private const val TEXTURE_IS_FORMAT_SUPPORTED_FOR_USAGE_HASH = 2592520478L
        private val textureIsFormatSupportedForUsageBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_is_format_supported_for_usage", TEXTURE_IS_FORMAT_SUPPORTED_FOR_USAGE_HASH)
        }

        private const val TEXTURE_IS_SHARED_HASH = 3521089500L
        private val textureIsSharedBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_is_shared", TEXTURE_IS_SHARED_HASH)
        }

        private const val TEXTURE_IS_VALID_HASH = 3521089500L
        private val textureIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_is_valid", TEXTURE_IS_VALID_HASH)
        }

        private const val TEXTURE_SET_DISCARDABLE_HASH = 1265174801L
        private val textureSetDiscardableBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_set_discardable", TEXTURE_SET_DISCARDABLE_HASH)
        }

        private const val TEXTURE_IS_DISCARDABLE_HASH = 3521089500L
        private val textureIsDiscardableBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_is_discardable", TEXTURE_IS_DISCARDABLE_HASH)
        }

        private const val TEXTURE_COPY_HASH = 2859522160L
        private val textureCopyBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_copy", TEXTURE_COPY_HASH)
        }

        private const val TEXTURE_CLEAR_HASH = 3477703247L
        private val textureClearBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_clear", TEXTURE_CLEAR_HASH)
        }

        private const val TEXTURE_RESOLVE_MULTISAMPLE_HASH = 3181288260L
        private val textureResolveMultisampleBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_resolve_multisample", TEXTURE_RESOLVE_MULTISAMPLE_HASH)
        }

        private const val TEXTURE_GET_FORMAT_HASH = 1374471690L
        private val textureGetFormatBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_get_format", TEXTURE_GET_FORMAT_HASH)
        }

        private const val TEXTURE_GET_NATIVE_HANDLE_HASH = 3917799429L
        private val textureGetNativeHandleBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_get_native_handle", TEXTURE_GET_NATIVE_HANDLE_HASH)
        }

        private const val FRAMEBUFFER_FORMAT_CREATE_HASH = 697032759L
        private val framebufferFormatCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_format_create", FRAMEBUFFER_FORMAT_CREATE_HASH)
        }

        private const val FRAMEBUFFER_FORMAT_CREATE_MULTIPASS_HASH = 2647479094L
        private val framebufferFormatCreateMultipassBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_format_create_multipass", FRAMEBUFFER_FORMAT_CREATE_MULTIPASS_HASH)
        }

        private const val FRAMEBUFFER_FORMAT_CREATE_EMPTY_HASH = 555930169L
        private val framebufferFormatCreateEmptyBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_format_create_empty", FRAMEBUFFER_FORMAT_CREATE_EMPTY_HASH)
        }

        private const val FRAMEBUFFER_FORMAT_GET_TEXTURE_SAMPLES_HASH = 4223391010L
        private val framebufferFormatGetTextureSamplesBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_format_get_texture_samples", FRAMEBUFFER_FORMAT_GET_TEXTURE_SAMPLES_HASH)
        }

        private const val FRAMEBUFFER_CREATE_HASH = 3284231055L
        private val framebufferCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_create", FRAMEBUFFER_CREATE_HASH)
        }

        private const val FRAMEBUFFER_CREATE_MULTIPASS_HASH = 1750306695L
        private val framebufferCreateMultipassBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_create_multipass", FRAMEBUFFER_CREATE_MULTIPASS_HASH)
        }

        private const val FRAMEBUFFER_CREATE_EMPTY_HASH = 3058360618L
        private val framebufferCreateEmptyBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_create_empty", FRAMEBUFFER_CREATE_EMPTY_HASH)
        }

        private const val FRAMEBUFFER_GET_FORMAT_HASH = 3917799429L
        private val framebufferGetFormatBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_get_format", FRAMEBUFFER_GET_FORMAT_HASH)
        }

        private const val FRAMEBUFFER_IS_VALID_HASH = 4155700596L
        private val framebufferIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_is_valid", FRAMEBUFFER_IS_VALID_HASH)
        }

        private const val SAMPLER_CREATE_HASH = 2327892535L
        private val samplerCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "sampler_create", SAMPLER_CREATE_HASH)
        }

        private const val SAMPLER_IS_FORMAT_SUPPORTED_FOR_FILTER_HASH = 2247922238L
        private val samplerIsFormatSupportedForFilterBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "sampler_is_format_supported_for_filter", SAMPLER_IS_FORMAT_SUPPORTED_FOR_FILTER_HASH)
        }

        private const val VERTEX_BUFFER_CREATE_HASH = 2089548973L
        private val vertexBufferCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "vertex_buffer_create", VERTEX_BUFFER_CREATE_HASH)
        }

        private const val VERTEX_FORMAT_CREATE_HASH = 1242678479L
        private val vertexFormatCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "vertex_format_create", VERTEX_FORMAT_CREATE_HASH)
        }

        private const val VERTEX_ARRAY_CREATE_HASH = 3799816279L
        private val vertexArrayCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "vertex_array_create", VERTEX_ARRAY_CREATE_HASH)
        }

        private const val INDEX_BUFFER_CREATE_HASH = 2368684885L
        private val indexBufferCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "index_buffer_create", INDEX_BUFFER_CREATE_HASH)
        }

        private const val INDEX_ARRAY_CREATE_HASH = 2256026069L
        private val indexArrayCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "index_array_create", INDEX_ARRAY_CREATE_HASH)
        }

        private const val SHADER_COMPILE_SPIRV_FROM_SOURCE_HASH = 1178973306L
        private val shaderCompileSpirvFromSourceBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "shader_compile_spirv_from_source", SHADER_COMPILE_SPIRV_FROM_SOURCE_HASH)
        }

        private const val SHADER_COMPILE_BINARY_FROM_SPIRV_HASH = 134910450L
        private val shaderCompileBinaryFromSpirvBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "shader_compile_binary_from_spirv", SHADER_COMPILE_BINARY_FROM_SPIRV_HASH)
        }

        private const val SHADER_CREATE_FROM_SPIRV_HASH = 342949005L
        private val shaderCreateFromSpirvBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "shader_create_from_spirv", SHADER_CREATE_FROM_SPIRV_HASH)
        }

        private const val SHADER_CREATE_FROM_BYTECODE_HASH = 1687031350L
        private val shaderCreateFromBytecodeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "shader_create_from_bytecode", SHADER_CREATE_FROM_BYTECODE_HASH)
        }

        private const val SHADER_CREATE_PLACEHOLDER_HASH = 529393457L
        private val shaderCreatePlaceholderBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "shader_create_placeholder", SHADER_CREATE_PLACEHOLDER_HASH)
        }

        private const val SHADER_GET_VERTEX_INPUT_ATTRIBUTE_MASK_HASH = 3917799429L
        private val shaderGetVertexInputAttributeMaskBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "shader_get_vertex_input_attribute_mask", SHADER_GET_VERTEX_INPUT_ATTRIBUTE_MASK_HASH)
        }

        private const val UNIFORM_BUFFER_CREATE_HASH = 2089548973L
        private val uniformBufferCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "uniform_buffer_create", UNIFORM_BUFFER_CREATE_HASH)
        }

        private const val STORAGE_BUFFER_CREATE_HASH = 1609052553L
        private val storageBufferCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "storage_buffer_create", STORAGE_BUFFER_CREATE_HASH)
        }

        private const val TEXTURE_BUFFER_CREATE_HASH = 1470338698L
        private val textureBufferCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "texture_buffer_create", TEXTURE_BUFFER_CREATE_HASH)
        }

        private const val UNIFORM_SET_CREATE_HASH = 2280795797L
        private val uniformSetCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "uniform_set_create", UNIFORM_SET_CREATE_HASH)
        }

        private const val UNIFORM_SET_IS_VALID_HASH = 3521089500L
        private val uniformSetIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "uniform_set_is_valid", UNIFORM_SET_IS_VALID_HASH)
        }

        private const val BUFFER_COPY_HASH = 864257779L
        private val bufferCopyBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "buffer_copy", BUFFER_COPY_HASH)
        }

        private const val BUFFER_UPDATE_HASH = 3454956949L
        private val bufferUpdateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "buffer_update", BUFFER_UPDATE_HASH)
        }

        private const val BUFFER_CLEAR_HASH = 2452320800L
        private val bufferClearBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "buffer_clear", BUFFER_CLEAR_HASH)
        }

        private const val BUFFER_GET_DATA_HASH = 3101830688L
        private val bufferGetDataBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "buffer_get_data", BUFFER_GET_DATA_HASH)
        }

        private const val BUFFER_GET_DATA_ASYNC_HASH = 2370287848L
        private val bufferGetDataAsyncBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "buffer_get_data_async", BUFFER_GET_DATA_ASYNC_HASH)
        }

        private const val BUFFER_GET_DEVICE_ADDRESS_HASH = 3917799429L
        private val bufferGetDeviceAddressBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "buffer_get_device_address", BUFFER_GET_DEVICE_ADDRESS_HASH)
        }

        private const val RENDER_PIPELINE_CREATE_HASH = 2385451958L
        private val renderPipelineCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "render_pipeline_create", RENDER_PIPELINE_CREATE_HASH)
        }

        private const val RENDER_PIPELINE_IS_VALID_HASH = 3521089500L
        private val renderPipelineIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "render_pipeline_is_valid", RENDER_PIPELINE_IS_VALID_HASH)
        }

        private const val COMPUTE_PIPELINE_CREATE_HASH = 1448838280L
        private val computePipelineCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_pipeline_create", COMPUTE_PIPELINE_CREATE_HASH)
        }

        private const val COMPUTE_PIPELINE_IS_VALID_HASH = 3521089500L
        private val computePipelineIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_pipeline_is_valid", COMPUTE_PIPELINE_IS_VALID_HASH)
        }

        private const val RAYTRACING_PIPELINE_CREATE_HASH = 1489129684L
        private val raytracingPipelineCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_pipeline_create", RAYTRACING_PIPELINE_CREATE_HASH)
        }

        private const val RAYTRACING_PIPELINE_IS_VALID_HASH = 3521089500L
        private val raytracingPipelineIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_pipeline_is_valid", RAYTRACING_PIPELINE_IS_VALID_HASH)
        }

        private const val BLAS_CREATE_HASH = 1010940044L
        private val blasCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "blas_create", BLAS_CREATE_HASH)
        }

        private const val TLAS_CREATE_HASH = 592780330L
        private val tlasCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "tlas_create", TLAS_CREATE_HASH)
        }

        private const val BLAS_BUILD_HASH = 813180755L
        private val blasBuildBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "blas_build", BLAS_BUILD_HASH)
        }

        private const val TLAS_BUILD_HASH = 261981775L
        private val tlasBuildBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "tlas_build", TLAS_BUILD_HASH)
        }

        private const val HIT_SBT_CREATE_HASH = 2233757277L
        private val hitSbtCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "hit_sbt_create", HIT_SBT_CREATE_HASH)
        }

        private const val HIT_SBT_SET_PIPELINE_HASH = 3181288260L
        private val hitSbtSetPipelineBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "hit_sbt_set_pipeline", HIT_SBT_SET_PIPELINE_HASH)
        }

        private const val HIT_SBT_RANGE_ALLOC_HASH = 2722015314L
        private val hitSbtRangeAllocBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "hit_sbt_range_alloc", HIT_SBT_RANGE_ALLOC_HASH)
        }

        private const val HIT_SBT_RANGE_FREE_HASH = 3804025326L
        private val hitSbtRangeFreeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "hit_sbt_range_free", HIT_SBT_RANGE_FREE_HASH)
        }

        private const val HIT_SBT_RANGE_UPDATE_HASH = 1332346675L
        private val hitSbtRangeUpdateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "hit_sbt_range_update", HIT_SBT_RANGE_UPDATE_HASH)
        }

        private const val SCREEN_GET_WIDTH_HASH = 1591665591L
        private val screenGetWidthBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "screen_get_width", SCREEN_GET_WIDTH_HASH)
        }

        private const val SCREEN_GET_HEIGHT_HASH = 1591665591L
        private val screenGetHeightBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "screen_get_height", SCREEN_GET_HEIGHT_HASH)
        }

        private const val SCREEN_GET_FRAMEBUFFER_FORMAT_HASH = 1591665591L
        private val screenGetFramebufferFormatBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "screen_get_framebuffer_format", SCREEN_GET_FRAMEBUFFER_FORMAT_HASH)
        }

        private const val DRAW_LIST_BEGIN_FOR_SCREEN_HASH = 3988079995L
        private val drawListBeginForScreenBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_begin_for_screen", DRAW_LIST_BEGIN_FOR_SCREEN_HASH)
        }

        private const val DRAW_LIST_BEGIN_HASH = 1317926357L
        private val drawListBeginBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_begin", DRAW_LIST_BEGIN_HASH)
        }

        private const val DRAW_LIST_BEGIN_SPLIT_HASH = 2406300660L
        private val drawListBeginSplitBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_begin_split", DRAW_LIST_BEGIN_SPLIT_HASH)
        }

        private const val DRAW_LIST_SET_BLEND_CONSTANTS_HASH = 2878471219L
        private val drawListSetBlendConstantsBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_set_blend_constants", DRAW_LIST_SET_BLEND_CONSTANTS_HASH)
        }

        private const val DRAW_LIST_BIND_RENDER_PIPELINE_HASH = 4040184819L
        private val drawListBindRenderPipelineBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_render_pipeline", DRAW_LIST_BIND_RENDER_PIPELINE_HASH)
        }

        private const val DRAW_LIST_BIND_UNIFORM_SET_HASH = 749655778L
        private val drawListBindUniformSetBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_uniform_set", DRAW_LIST_BIND_UNIFORM_SET_HASH)
        }

        private const val DRAW_LIST_BIND_VERTEX_ARRAY_HASH = 4040184819L
        private val drawListBindVertexArrayBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_vertex_array", DRAW_LIST_BIND_VERTEX_ARRAY_HASH)
        }

        private const val DRAW_LIST_BIND_VERTEX_BUFFERS_FORMAT_HASH = 2008628980L
        private val drawListBindVertexBuffersFormatBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_vertex_buffers_format", DRAW_LIST_BIND_VERTEX_BUFFERS_FORMAT_HASH)
        }

        private const val DRAW_LIST_BIND_INDEX_ARRAY_HASH = 4040184819L
        private val drawListBindIndexArrayBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_index_array", DRAW_LIST_BIND_INDEX_ARRAY_HASH)
        }

        private const val DRAW_LIST_SET_PUSH_CONSTANT_HASH = 2772371345L
        private val drawListSetPushConstantBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_set_push_constant", DRAW_LIST_SET_PUSH_CONSTANT_HASH)
        }

        private const val DRAW_LIST_DRAW_HASH = 4230067973L
        private val drawListDrawBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_draw", DRAW_LIST_DRAW_HASH)
        }

        private const val DRAW_LIST_DRAW_INDIRECT_HASH = 1092133571L
        private val drawListDrawIndirectBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_draw_indirect", DRAW_LIST_DRAW_INDIRECT_HASH)
        }

        private const val DRAW_LIST_ENABLE_SCISSOR_HASH = 244650101L
        private val drawListEnableScissorBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_enable_scissor", DRAW_LIST_ENABLE_SCISSOR_HASH)
        }

        private const val DRAW_LIST_DISABLE_SCISSOR_HASH = 1286410249L
        private val drawListDisableScissorBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_disable_scissor", DRAW_LIST_DISABLE_SCISSOR_HASH)
        }

        private const val DRAW_LIST_SWITCH_TO_NEXT_PASS_HASH = 2455072627L
        private val drawListSwitchToNextPassBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_switch_to_next_pass", DRAW_LIST_SWITCH_TO_NEXT_PASS_HASH)
        }

        private const val DRAW_LIST_SWITCH_TO_NEXT_PASS_SPLIT_HASH = 2865087369L
        private val drawListSwitchToNextPassSplitBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_switch_to_next_pass_split", DRAW_LIST_SWITCH_TO_NEXT_PASS_SPLIT_HASH)
        }

        private const val DRAW_LIST_END_HASH = 3218959716L
        private val drawListEndBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_list_end", DRAW_LIST_END_HASH)
        }

        private const val COMPUTE_LIST_BEGIN_HASH = 2455072627L
        private val computeListBeginBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_begin", COMPUTE_LIST_BEGIN_HASH)
        }

        private const val COMPUTE_LIST_BIND_COMPUTE_PIPELINE_HASH = 4040184819L
        private val computeListBindComputePipelineBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_bind_compute_pipeline", COMPUTE_LIST_BIND_COMPUTE_PIPELINE_HASH)
        }

        private const val COMPUTE_LIST_SET_PUSH_CONSTANT_HASH = 2772371345L
        private val computeListSetPushConstantBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_set_push_constant", COMPUTE_LIST_SET_PUSH_CONSTANT_HASH)
        }

        private const val COMPUTE_LIST_BIND_UNIFORM_SET_HASH = 749655778L
        private val computeListBindUniformSetBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_bind_uniform_set", COMPUTE_LIST_BIND_UNIFORM_SET_HASH)
        }

        private const val COMPUTE_LIST_DISPATCH_HASH = 4275841770L
        private val computeListDispatchBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_dispatch", COMPUTE_LIST_DISPATCH_HASH)
        }

        private const val COMPUTE_LIST_DISPATCH_INDIRECT_HASH = 749655778L
        private val computeListDispatchIndirectBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_dispatch_indirect", COMPUTE_LIST_DISPATCH_INDIRECT_HASH)
        }

        private const val COMPUTE_LIST_ADD_BARRIER_HASH = 1286410249L
        private val computeListAddBarrierBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_add_barrier", COMPUTE_LIST_ADD_BARRIER_HASH)
        }

        private const val COMPUTE_LIST_END_HASH = 3218959716L
        private val computeListEndBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "compute_list_end", COMPUTE_LIST_END_HASH)
        }

        private const val RAYTRACING_LIST_BEGIN_HASH = 2455072627L
        private val raytracingListBeginBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_begin", RAYTRACING_LIST_BEGIN_HASH)
        }

        private const val RAYTRACING_LIST_BIND_RAYTRACING_PIPELINE_HASH = 4040184819L
        private val raytracingListBindRaytracingPipelineBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_bind_raytracing_pipeline", RAYTRACING_LIST_BIND_RAYTRACING_PIPELINE_HASH)
        }

        private const val RAYTRACING_LIST_SET_PUSH_CONSTANT_HASH = 2772371345L
        private val raytracingListSetPushConstantBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_set_push_constant", RAYTRACING_LIST_SET_PUSH_CONSTANT_HASH)
        }

        private const val RAYTRACING_LIST_BIND_UNIFORM_SET_HASH = 749655778L
        private val raytracingListBindUniformSetBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_bind_uniform_set", RAYTRACING_LIST_BIND_UNIFORM_SET_HASH)
        }

        private const val RAYTRACING_LIST_TRACE_RAYS_HASH = 2559472681L
        private val raytracingListTraceRaysBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_trace_rays", RAYTRACING_LIST_TRACE_RAYS_HASH)
        }

        private const val RAYTRACING_LIST_END_HASH = 3218959716L
        private val raytracingListEndBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_end", RAYTRACING_LIST_END_HASH)
        }

        private const val FREE_RID_HASH = 2722037293L
        private val freeRidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "free_rid", FREE_RID_HASH)
        }

        private const val CAPTURE_TIMESTAMP_HASH = 83702148L
        private val captureTimestampBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "capture_timestamp", CAPTURE_TIMESTAMP_HASH)
        }

        private const val GET_CAPTURED_TIMESTAMPS_COUNT_HASH = 3905245786L
        private val getCapturedTimestampsCountBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_captured_timestamps_count", GET_CAPTURED_TIMESTAMPS_COUNT_HASH)
        }

        private const val GET_CAPTURED_TIMESTAMPS_FRAME_HASH = 3905245786L
        private val getCapturedTimestampsFrameBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_captured_timestamps_frame", GET_CAPTURED_TIMESTAMPS_FRAME_HASH)
        }

        private const val GET_CAPTURED_TIMESTAMP_GPU_TIME_HASH = 923996154L
        private val getCapturedTimestampGpuTimeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_captured_timestamp_gpu_time", GET_CAPTURED_TIMESTAMP_GPU_TIME_HASH)
        }

        private const val GET_CAPTURED_TIMESTAMP_CPU_TIME_HASH = 923996154L
        private val getCapturedTimestampCpuTimeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_captured_timestamp_cpu_time", GET_CAPTURED_TIMESTAMP_CPU_TIME_HASH)
        }

        private const val GET_CAPTURED_TIMESTAMP_NAME_HASH = 844755477L
        private val getCapturedTimestampNameBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_captured_timestamp_name", GET_CAPTURED_TIMESTAMP_NAME_HASH)
        }

        private const val HAS_FEATURE_HASH = 1772728326L
        private val hasFeatureBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "has_feature", HAS_FEATURE_HASH)
        }

        private const val LIMIT_GET_HASH = 1559202131L
        private val limitGetBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "limit_get", LIMIT_GET_HASH)
        }

        private const val GET_FRAME_DELAY_HASH = 3905245786L
        private val getFrameDelayBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_frame_delay", GET_FRAME_DELAY_HASH)
        }

        private const val SUBMIT_HASH = 3218959716L
        private val submitBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "submit", SUBMIT_HASH)
        }

        private const val SYNC_HASH = 3218959716L
        private val syncBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "sync", SYNC_HASH)
        }

        private const val BARRIER_HASH = 3718155691L
        private val barrierBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "barrier", BARRIER_HASH)
        }

        private const val FULL_BARRIER_HASH = 3218959716L
        private val fullBarrierBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "full_barrier", FULL_BARRIER_HASH)
        }

        private const val CREATE_LOCAL_DEVICE_HASH = 2846302423L
        private val createLocalDeviceBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "create_local_device", CREATE_LOCAL_DEVICE_HASH)
        }

        private const val SET_RESOURCE_NAME_HASH = 2726140452L
        private val setResourceNameBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "set_resource_name", SET_RESOURCE_NAME_HASH)
        }

        private const val DRAW_COMMAND_BEGIN_LABEL_HASH = 1636512886L
        private val drawCommandBeginLabelBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_command_begin_label", DRAW_COMMAND_BEGIN_LABEL_HASH)
        }

        private const val DRAW_COMMAND_INSERT_LABEL_HASH = 1636512886L
        private val drawCommandInsertLabelBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_command_insert_label", DRAW_COMMAND_INSERT_LABEL_HASH)
        }

        private const val DRAW_COMMAND_END_LABEL_HASH = 3218959716L
        private val drawCommandEndLabelBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "draw_command_end_label", DRAW_COMMAND_END_LABEL_HASH)
        }

        private const val GET_DEVICE_VENDOR_NAME_HASH = 201670096L
        private val getDeviceVendorNameBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_vendor_name", GET_DEVICE_VENDOR_NAME_HASH)
        }

        private const val GET_DEVICE_NAME_HASH = 201670096L
        private val getDeviceNameBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_name", GET_DEVICE_NAME_HASH)
        }

        private const val GET_DEVICE_PIPELINE_CACHE_UUID_HASH = 201670096L
        private val getDevicePipelineCacheUuidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_pipeline_cache_uuid", GET_DEVICE_PIPELINE_CACHE_UUID_HASH)
        }

        private const val GET_MEMORY_USAGE_HASH = 251690689L
        private val getMemoryUsageBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_memory_usage", GET_MEMORY_USAGE_HASH)
        }

        private const val GET_DRIVER_RESOURCE_HASH = 501815484L
        private val getDriverResourceBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_driver_resource", GET_DRIVER_RESOURCE_HASH)
        }

        private const val GET_PERF_REPORT_HASH = 201670096L
        private val getPerfReportBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_perf_report", GET_PERF_REPORT_HASH)
        }

        private const val GET_DRIVER_AND_DEVICE_MEMORY_REPORT_HASH = 201670096L
        private val getDriverAndDeviceMemoryReportBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_driver_and_device_memory_report", GET_DRIVER_AND_DEVICE_MEMORY_REPORT_HASH)
        }

        private const val GET_TRACKED_OBJECT_NAME_HASH = 844755477L
        private val getTrackedObjectNameBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_tracked_object_name", GET_TRACKED_OBJECT_NAME_HASH)
        }

        private const val GET_TRACKED_OBJECT_TYPE_COUNT_HASH = 3905245786L
        private val getTrackedObjectTypeCountBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_tracked_object_type_count", GET_TRACKED_OBJECT_TYPE_COUNT_HASH)
        }

        private const val GET_DRIVER_TOTAL_MEMORY_HASH = 3905245786L
        private val getDriverTotalMemoryBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_driver_total_memory", GET_DRIVER_TOTAL_MEMORY_HASH)
        }

        private const val GET_DRIVER_ALLOCATION_COUNT_HASH = 3905245786L
        private val getDriverAllocationCountBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_driver_allocation_count", GET_DRIVER_ALLOCATION_COUNT_HASH)
        }

        private const val GET_DRIVER_MEMORY_BY_OBJECT_TYPE_HASH = 923996154L
        private val getDriverMemoryByObjectTypeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_driver_memory_by_object_type", GET_DRIVER_MEMORY_BY_OBJECT_TYPE_HASH)
        }

        private const val GET_DRIVER_ALLOCS_BY_OBJECT_TYPE_HASH = 923996154L
        private val getDriverAllocsByObjectTypeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_driver_allocs_by_object_type", GET_DRIVER_ALLOCS_BY_OBJECT_TYPE_HASH)
        }

        private const val GET_DEVICE_TOTAL_MEMORY_HASH = 3905245786L
        private val getDeviceTotalMemoryBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_total_memory", GET_DEVICE_TOTAL_MEMORY_HASH)
        }

        private const val GET_DEVICE_ALLOCATION_COUNT_HASH = 3905245786L
        private val getDeviceAllocationCountBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_allocation_count", GET_DEVICE_ALLOCATION_COUNT_HASH)
        }

        private const val GET_DEVICE_MEMORY_BY_OBJECT_TYPE_HASH = 923996154L
        private val getDeviceMemoryByObjectTypeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_memory_by_object_type", GET_DEVICE_MEMORY_BY_OBJECT_TYPE_HASH)
        }

        private const val GET_DEVICE_ALLOCS_BY_OBJECT_TYPE_HASH = 923996154L
        private val getDeviceAllocsByObjectTypeBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "get_device_allocs_by_object_type", GET_DEVICE_ALLOCS_BY_OBJECT_TYPE_HASH)
        }
    }
}

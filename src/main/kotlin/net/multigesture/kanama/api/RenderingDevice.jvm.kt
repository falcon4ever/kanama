package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2

// GENERATED desktop/Android companion for RenderingDevice (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingDevice waits on: ptrcallWithByteArrayAndRIDArgRetRID,
//   ptrcallWithLongByteArrayUInt32Args, ptrcallWithObjectAndStringArgRetByteArray,
//   ptrcallWithObjectListArgRetLong, ptrcallWithObjectListLongArgsRetRID,
//   ptrcallWithObjectListRIDUInt32ArgsRetRID, ptrcallWithObjectListUInt32ArgsRetLong,
//   ptrcallWithRIDAndObjectListArgsRetLong, ptrcallWithRIDAndTwoUInt32ArgsRetByteArray,
//   ptrcallWithRIDAndUInt32ArgRetByteArray, ptrcallWithRIDListLongUInt32ArgsRetRID,
//   ptrcallWithRIDListObjectListLongUInt32ArgsRetRID,
//   ptrcallWithRIDLongPackedColorListDoubleUInt32Rect2UInt32ArgsRetLong,
//   ptrcallWithRIDLongUInt32AndPackedInt32ListArgRetLong, ptrcallWithRIDObjectListArgsRetRID,
//   ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID,
//   ptrcallWithRIDTwoUInt32PackedByteArrayArgsRetLong, ptrcallWithRIDUInt32ByteArrayArgsRetLong,
//   ptrcallWithRIDUInt32FourLongPackedColorListDoubleUInt32Rect2RIDListArgsRetPackedInt64List,
//   ptrcallWithThreeObjectListUInt32ArgsRetRID, ptrcallWithTwoLongUInt32RIDListPackedInt64ListArgs,
//   ptrcallWithTwoObjectByteArrayListArgsRetRID, ptrcallWithTwoObjectListUInt32ArgsRetLong,
//   ptrcallWithUInt32ArgRetPackedInt64List, ptrcallWithUInt32ArgRetString,
//   ptrcallWithUInt32ByteArrayLongArgsRetRID, ptrcallWithUInt32LongByteArrayArgsRetRID,
//   ptrcallWithUInt32LongPackedByteArrayBoolLongArgsRetRID,
//   ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID,
//   ptrcallWithUInt32PackedByteArrayTwoLongArgsRetRID
// Index: docs/contributing/ios-shape-gap.md

/**
 * Creates a new texture. It can be accessed with the RID that is returned. Once finished with your
 * RID, you will want to free the RID using the RenderingDevice's `free_rid` method. Note: `data`
 * takes an `Array` of `PackedByteArray`s. For `TEXTURE_TYPE_1D`, `TEXTURE_TYPE_2D`, and
 * `TEXTURE_TYPE_3D` types, this array should only have one element, a `PackedByteArray` containing
 * all the data for the texture. For `_ARRAY` and `_CUBE` types, the length should be the same as
 * the number of `RDTextureFormat.array_layers` in `format`. Note: Not to be confused with
 * `RenderingServer.texture_2d_create`, which creates the Godot-specific `Texture2D` resource as
 * opposed to the graphics API's own texture type.
 *
 * Generated from Godot docs: RenderingDevice.texture_create
 */
fun RenderingDevice.textureCreate(format: RDTextureFormat?, view: RDTextureView?, data: List<ByteArray>): RID {
    return ObjectCalls.ptrcallWithTwoObjectByteArrayListArgsRetRID(textureCreateBind, handle, format?.requireOpenHandle() ?: MemorySegment.NULL, view?.requireOpenHandle() ?: MemorySegment.NULL, data)
}

/**
 * Updates texture data with new data, replacing the previous data in place. The updated texture
 * data must have the same dimensions and format. For 2D textures (which only have one layer),
 * `layer` must be `0`. Returns `@GlobalScope.OK` if the update was successful,
 * `@GlobalScope.ERR_INVALID_PARAMETER` otherwise. Note: Updating textures is forbidden during
 * creation of a draw or compute list. Note: The existing `texture` can't be updated while a draw
 * list that uses it as part of a framebuffer is being created. Ensure the draw list is finalized
 * (and that the color/depth texture using it is not set to `FINAL_ACTION_CONTINUE`) to update this
 * texture. Note: The existing `texture` requires the `TEXTURE_USAGE_CAN_UPDATE_BIT` to be
 * updatable.
 *
 * Generated from Godot docs: RenderingDevice.texture_update
 */
fun RenderingDevice.textureUpdate(texture: RID, layer: Long, data: ByteArray): Long {
    return ObjectCalls.ptrcallWithRIDUInt32ByteArrayArgsRetLong(textureUpdateBind, handle, texture, layer, data)
}

/**
 * Returns the `texture` data for the specified `layer` as raw binary data. For 2D textures (which
 * only have one layer), `layer` must be `0`. Note: `texture` can't be retrieved while a draw list
 * that uses it as part of a framebuffer is being created. Ensure the draw list is finalized (and
 * that the color/depth texture using it is not set to `FINAL_ACTION_CONTINUE`) to retrieve this
 * texture. Otherwise, an error is printed and an empty `PackedByteArray` is returned. Note:
 * `texture` requires the `TEXTURE_USAGE_CAN_COPY_FROM_BIT` to be retrieved. Otherwise, an error is
 * printed and an empty `PackedByteArray` is returned. Note: This method will block the GPU from
 * working until the data is retrieved. Refer to `texture_get_data_async` for an alternative that
 * returns the data in more performant way.
 *
 * Generated from Godot docs: RenderingDevice.texture_get_data
 */
fun RenderingDevice.textureGetData(texture: RID, layer: Long): ByteArray {
    return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetByteArray(textureGetDataBind, handle, texture, layer)
}

/**
 * Creates a new framebuffer format with the specified `attachments` and `view_count`. Returns the
 * new framebuffer's unique framebuffer format ID. If `view_count` is greater than or equal to `2`,
 * enables multiview which is used for VR rendering. This requires support for the Vulkan multiview
 * extension.
 *
 * Generated from Godot docs: RenderingDevice.framebuffer_format_create
 */
fun RenderingDevice.framebufferFormatCreate(attachments: List<RDAttachmentFormat>, viewCount: Long = 1L): Long {
    return ObjectCalls.ptrcallWithObjectListUInt32ArgsRetLong(framebufferFormatCreateBind, handle, attachments, viewCount)
}

/**
 * Creates a multipass framebuffer format with the specified `attachments`, `passes` and
 * `view_count` and returns its ID. If `view_count` is greater than or equal to `2`, enables
 * multiview which is used for VR rendering. This requires support for the Vulkan multiview
 * extension.
 *
 * Generated from Godot docs: RenderingDevice.framebuffer_format_create_multipass
 */
fun RenderingDevice.framebufferFormatCreateMultipass(attachments: List<RDAttachmentFormat>, passes: List<RDFramebufferPass>, viewCount: Long = 1L): Long {
    return ObjectCalls.ptrcallWithTwoObjectListUInt32ArgsRetLong(framebufferFormatCreateMultipassBind, handle, attachments, passes, viewCount)
}

/**
 * Creates a new framebuffer. It can be accessed with the RID that is returned. Once finished with
 * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This will
 * be freed automatically when any of the `textures` is freed.
 *
 * Generated from Godot docs: RenderingDevice.framebuffer_create
 */
fun RenderingDevice.framebufferCreate(textures: List<RID>, validateWithFormat: Long = -1L, viewCount: Long = 1L): RID {
    return ObjectCalls.ptrcallWithRIDListLongUInt32ArgsRetRID(framebufferCreateBind, handle, textures, validateWithFormat, viewCount)
}

/**
 * Creates a new multipass framebuffer. It can be accessed with the RID that is returned. Once
 * finished with your RID, you will want to free the RID using the RenderingDevice's `free_rid`
 * method. This will be freed automatically when any of the `textures` is freed.
 *
 * Generated from Godot docs: RenderingDevice.framebuffer_create_multipass
 */
fun RenderingDevice.framebufferCreateMultipass(textures: List<RID>, passes: List<RDFramebufferPass>, validateWithFormat: Long = -1L, viewCount: Long = 1L): RID {
    return ObjectCalls.ptrcallWithRIDListObjectListLongUInt32ArgsRetRID(framebufferCreateMultipassBind, handle, textures, passes, validateWithFormat, viewCount)
}

/**
 * Creates a new vertex buffer. It can be accessed with the RID that is returned. Once finished
 * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
 *
 * Generated from Godot docs: RenderingDevice.vertex_buffer_create
 */
fun RenderingDevice.vertexBufferCreate(sizeBytes: Long, data: ByteArray, creationBits: Long = 0L): RID {
    return ObjectCalls.ptrcallWithUInt32ByteArrayLongArgsRetRID(vertexBufferCreateBind, handle, sizeBytes, data, creationBits)
}

/**
 * Creates a new vertex format with the specified `vertex_descriptions`. Returns a unique vertex
 * format ID corresponding to the newly created vertex format.
 *
 * Generated from Godot docs: RenderingDevice.vertex_format_create
 */
fun RenderingDevice.vertexFormatCreate(vertexDescriptions: List<RDVertexAttribute>): Long {
    return ObjectCalls.ptrcallWithObjectListArgRetLong(vertexFormatCreateBind, handle, vertexDescriptions)
}

/**
 * Creates a vertex array based on the specified buffers. Optionally, `offsets` (in bytes) may be
 * defined for each buffer. Once finished with your RID, you will want to free the RID using the
 * RenderingDevice's `free_rid` method. This will be freed automatically when any of the
 * `src_buffers` is freed.
 *
 * Generated from Godot docs: RenderingDevice.vertex_array_create
 */
fun RenderingDevice.vertexArrayCreate(vertexCount: Long, vertexFormat: Long, srcBuffers: List<RID>, offsets: List<Long>): RID {
    return ObjectCalls.ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID(vertexArrayCreateBind, handle, vertexCount, vertexFormat, srcBuffers, offsets)
}

/**
 * Creates a new index buffer. It can be accessed with the RID that is returned. Once finished with
 * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
 *
 * Generated from Godot docs: RenderingDevice.index_buffer_create
 */
fun RenderingDevice.indexBufferCreate(sizeIndices: Long, format: Long, data: ByteArray, useRestartIndices: Boolean = false, creationBits: Long = 0L): RID {
    return ObjectCalls.ptrcallWithUInt32LongPackedByteArrayBoolLongArgsRetRID(indexBufferCreateBind, handle, sizeIndices, format, data, useRestartIndices, creationBits)
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
fun RenderingDevice.shaderCompileBinaryFromSpirv(spirvData: RDShaderSPIRV?, name: String = ""): ByteArray {
    return ObjectCalls.ptrcallWithObjectAndStringArgRetByteArray(shaderCompileBinaryFromSpirvBind, handle, spirvData?.requireOpenHandle() ?: MemorySegment.NULL, name)
}

/**
 * Creates a new shader instance from a binary compiled shader. It can be accessed with the RID
 * that is returned. Once finished with your RID, you will want to free the RID using the
 * RenderingDevice's `free_rid` method. See also `shader_compile_binary_from_spirv` and
 * `shader_create_from_spirv`.
 *
 * Generated from Godot docs: RenderingDevice.shader_create_from_bytecode
 */
fun RenderingDevice.shaderCreateFromBytecode(binaryData: ByteArray, placeholderRid: RID): RID {
    return ObjectCalls.ptrcallWithByteArrayAndRIDArgRetRID(shaderCreateFromBytecodeBind, handle, binaryData, placeholderRid)
}

/**
 * Creates a new uniform buffer. It can be accessed with the RID that is returned. Once finished
 * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
 *
 * Generated from Godot docs: RenderingDevice.uniform_buffer_create
 */
fun RenderingDevice.uniformBufferCreate(sizeBytes: Long, data: ByteArray, creationBits: Long = 0L): RID {
    return ObjectCalls.ptrcallWithUInt32ByteArrayLongArgsRetRID(uniformBufferCreateBind, handle, sizeBytes, data, creationBits)
}

/**
 * Creates a storage buffer (https://vkguide.dev/docs/chapter-4/storage_buffers/) with the
 * specified `data` and `usage`. It can be accessed with the RID that is returned. Once finished
 * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
 *
 * Generated from Godot docs: RenderingDevice.storage_buffer_create
 */
fun RenderingDevice.storageBufferCreate(sizeBytes: Long, data: ByteArray, usage: Long = 0L, creationBits: Long = 0L): RID {
    return ObjectCalls.ptrcallWithUInt32PackedByteArrayTwoLongArgsRetRID(storageBufferCreateBind, handle, sizeBytes, data, usage, creationBits)
}

/**
 * Creates a new texture buffer. It can be accessed with the RID that is returned. Once finished
 * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method.
 *
 * Generated from Godot docs: RenderingDevice.texture_buffer_create
 */
fun RenderingDevice.textureBufferCreate(sizeBytes: Long, format: Long, data: ByteArray): RID {
    return ObjectCalls.ptrcallWithUInt32LongByteArrayArgsRetRID(textureBufferCreateBind, handle, sizeBytes, format, data)
}

/**
 * Creates a new uniform set. It can be accessed with the RID that is returned. Once finished with
 * your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This will
 * be freed automatically when the `shader` or any of the RIDs in the `uniforms` is freed.
 *
 * Generated from Godot docs: RenderingDevice.uniform_set_create
 */
fun RenderingDevice.uniformSetCreate(uniforms: List<RDUniform>, shader: RID, shaderSet: Long): RID {
    return ObjectCalls.ptrcallWithObjectListRIDUInt32ArgsRetRID(uniformSetCreateBind, handle, uniforms, shader, shaderSet)
}

/**
 * Updates a region of `size_bytes` bytes, starting at `offset`, in the buffer, with the specified
 * `data`. Prints an error if: - the region specified by `offset` + `size_bytes` exceeds the buffer
 * - a draw list is currently active (created by `draw_list_begin`) - a compute list is currently
 * active (created by `compute_list_begin`)
 *
 * Generated from Godot docs: RenderingDevice.buffer_update
 */
fun RenderingDevice.bufferUpdate(buffer: RID, offset: Long, sizeBytes: Long, data: ByteArray): Long {
    return ObjectCalls.ptrcallWithRIDTwoUInt32PackedByteArrayArgsRetLong(bufferUpdateBind, handle, buffer, offset, sizeBytes, data)
}

/**
 * Returns a copy of the data of the specified `buffer`, optionally `offset_bytes` and `size_bytes`
 * can be set to copy only a portion of the buffer. Note: This method will block the GPU from
 * working until the data is retrieved. Refer to `buffer_get_data_async` for an alternative that
 * returns the data in more performant way.
 *
 * Generated from Godot docs: RenderingDevice.buffer_get_data
 */
fun RenderingDevice.bufferGetData(buffer: RID, offsetBytes: Long = 0L, sizeBytes: Long = 0L): ByteArray {
    return ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetByteArray(bufferGetDataBind, handle, buffer, offsetBytes, sizeBytes)
}

/**
 * Creates a new render pipeline. It can be accessed with the RID that is returned. Once finished
 * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This
 * will be freed automatically when the `shader` is freed.
 *
 * Generated from Godot docs: RenderingDevice.render_pipeline_create
 */
fun RenderingDevice.renderPipelineCreate(shader: RID, framebufferFormat: Long, vertexFormat: Long, primitive: Long, rasterizationState: RDPipelineRasterizationState?, multisampleState: RDPipelineMultisampleState?, stencilState: RDPipelineDepthStencilState?, colorBlendState: RDPipelineColorBlendState?, dynamicStateFlags: Long = 0L, forRenderPass: Long = 0L, specializationConstants: List<RDPipelineSpecializationConstant>): RID {
    return ObjectCalls.ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID(renderPipelineCreateBind, handle, shader, framebufferFormat, vertexFormat, primitive, rasterizationState?.requireOpenHandle() ?: MemorySegment.NULL, multisampleState?.requireOpenHandle() ?: MemorySegment.NULL, stencilState?.requireOpenHandle() ?: MemorySegment.NULL, colorBlendState?.requireOpenHandle() ?: MemorySegment.NULL, dynamicStateFlags, forRenderPass, specializationConstants)
}

/**
 * Creates a new compute pipeline. It can be accessed with the RID that is returned. Once finished
 * with your RID, you will want to free the RID using the RenderingDevice's `free_rid` method. This
 * will be freed automatically when the `shader` is freed.
 *
 * Generated from Godot docs: RenderingDevice.compute_pipeline_create
 */
fun RenderingDevice.computePipelineCreate(shader: RID, specializationConstants: List<RDPipelineSpecializationConstant>): RID {
    return ObjectCalls.ptrcallWithRIDObjectListArgsRetRID(computePipelineCreateBind, handle, shader, specializationConstants)
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
fun RenderingDevice.raytracingPipelineCreate(raygenShaders: List<RDPipelineShader>, missShaders: List<RDPipelineShader>, hitGroups: List<RDHitGroup>, maxTraceRecursionDepth: Long): RID {
    return ObjectCalls.ptrcallWithThreeObjectListUInt32ArgsRetRID(raytracingPipelineCreateBind, handle, raygenShaders, missShaders, hitGroups, maxTraceRecursionDepth)
}

/**
 * Creates a new Bottom-Level Acceleration Structure (BLAS). It can be accessed with the RID that
 * is returned. Once finished with your RID, you will want to free the RID using the
 * RenderingDevice's `free_rid` method.
 *
 * Generated from Godot docs: RenderingDevice.blas_create
 */
fun RenderingDevice.blasCreate(geometries: List<RDAccelerationStructureGeometry>, flags: Long): RID {
    return ObjectCalls.ptrcallWithObjectListLongArgsRetRID(blasCreateBind, handle, geometries, flags)
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
fun RenderingDevice.tlasBuild(tlas: RID, instances: List<RDAccelerationStructureInstance>): Long {
    return ObjectCalls.ptrcallWithRIDAndObjectListArgsRetLong(tlasBuildBind, handle, tlas, instances)
}

/**
 * Updates the contents of a hit SBT range. `hit_group_indices` specifies indices into the hit
 * group array provided in `raytracing_pipeline_create`. The `offset` parameter specifies where
 * within the allocated range the writing begins. This allows partial updates of a range. However,
 * the complete range must be fully initialized before it is used in a raytracing dispatch.
 *
 * Generated from Godot docs: RenderingDevice.hit_sbt_range_update
 */
fun RenderingDevice.hitSbtRangeUpdate(hitSbt: RID, range: Long, offset: Long, hitGroupIndices: List<Int>): Long {
    return ObjectCalls.ptrcallWithRIDLongUInt32AndPackedInt32ListArgRetLong(hitSbtRangeUpdateBind, handle, hitSbt, range, offset, hitGroupIndices)
}

/**
 * Starts a list of raster drawing commands created with the `draw_*` methods. The returned value
 * should be passed to other `draw_list_*` functions. Multiple draw lists cannot be created at the
 * same time; you must finish the previous draw list first using `draw_list_end`. A simple drawing
 * operation might look like this (code is not a complete example):
 *
 * Generated from Godot docs: RenderingDevice.draw_list_begin
 */
fun RenderingDevice.drawListBegin(framebuffer: RID, drawFlags: Long = 0L, clearColorValues: List<Color>, clearDepthValue: Double = 1.0, clearStencilValue: Long = 0L, region: Rect2, breadcrumb: Long = 0L): Long {
    return ObjectCalls.ptrcallWithRIDLongPackedColorListDoubleUInt32Rect2UInt32ArgsRetLong(drawListBeginBind, handle, framebuffer, drawFlags, clearColorValues, clearDepthValue, clearStencilValue, region, breadcrumb)
}

/**
 * This method does nothing and always returns an empty `PackedInt64Array`.
 *
 * Generated from Godot docs: RenderingDevice.draw_list_begin_split
 */
fun RenderingDevice.drawListBeginSplit(framebuffer: RID, splits: Long, initialColorAction: Long, finalColorAction: Long, initialDepthAction: Long, finalDepthAction: Long, clearColorValues: List<Color>, clearDepth: Double = 1.0, clearStencil: Long = 0L, region: Rect2, storageTextures: List<RID>): List<Long> {
    return ObjectCalls.ptrcallWithRIDUInt32FourLongPackedColorListDoubleUInt32Rect2RIDListArgsRetPackedInt64List(drawListBeginSplitBind, handle, framebuffer, splits, initialColorAction, finalColorAction, initialDepthAction, finalDepthAction, clearColorValues, clearDepth, clearStencil, region, storageTextures)
}

/**
 * Binds a set of `vertex_buffers` directly to the specified `draw_list` using `vertex_format`
 * without creating a vertex array RID. Provide the number of vertices in `vertex_count`; optional
 * per-buffer byte `offsets` may also be supplied.
 *
 * Generated from Godot docs: RenderingDevice.draw_list_bind_vertex_buffers_format
 */
fun RenderingDevice.drawListBindVertexBuffersFormat(drawList: Long, vertexFormat: Long, vertexCount: Long, vertexBuffers: List<RID>, offsets: List<Long>) {
    ObjectCalls.ptrcallWithTwoLongUInt32RIDListPackedInt64ListArgs(drawListBindVertexBuffersFormatBind, handle, drawList, vertexFormat, vertexCount, vertexBuffers, offsets)
}

/**
 * Sets the push constant data to `buffer` for the specified `draw_list`. The shader determines how
 * this binary data is used. The buffer's size in bytes must also be specified in `size_bytes`
 * (this can be obtained by calling the `PackedByteArray.size` method on the passed `buffer`).
 *
 * Generated from Godot docs: RenderingDevice.draw_list_set_push_constant
 */
fun RenderingDevice.drawListSetPushConstant(drawList: Long, buffer: ByteArray, sizeBytes: Long) {
    ObjectCalls.ptrcallWithLongByteArrayUInt32Args(drawListSetPushConstantBind, handle, drawList, buffer, sizeBytes)
}

/**
 * This method does nothing and always returns an empty `PackedInt64Array`.
 *
 * Generated from Godot docs: RenderingDevice.draw_list_switch_to_next_pass_split
 */
fun RenderingDevice.drawListSwitchToNextPassSplit(splits: Long): List<Long> {
    return ObjectCalls.ptrcallWithUInt32ArgRetPackedInt64List(drawListSwitchToNextPassSplitBind, handle, splits)
}

/**
 * Sets the push constant data to `buffer` for the specified `compute_list`. The shader determines
 * how this binary data is used. The buffer's size in bytes must also be specified in `size_bytes`
 * (this can be obtained by calling the `PackedByteArray.size` method on the passed `buffer`).
 *
 * Generated from Godot docs: RenderingDevice.compute_list_set_push_constant
 */
fun RenderingDevice.computeListSetPushConstant(computeList: Long, buffer: ByteArray, sizeBytes: Long) {
    ObjectCalls.ptrcallWithLongByteArrayUInt32Args(computeListSetPushConstantBind, handle, computeList, buffer, sizeBytes)
}

/**
 * Sets the push constant data to `buffer` for the specified `raytracing_list`. The shader
 * determines how this binary data is used. The buffer's size in bytes must also be specified in
 * `size_bytes` (this can be obtained by calling the `PackedByteArray.size` method on the passed
 * `buffer`).
 *
 * Generated from Godot docs: RenderingDevice.raytracing_list_set_push_constant
 */
fun RenderingDevice.raytracingListSetPushConstant(raytracingList: Long, buffer: ByteArray, sizeBytes: Long) {
    ObjectCalls.ptrcallWithLongByteArrayUInt32Args(raytracingListSetPushConstantBind, handle, raytracingList, buffer, sizeBytes)
}

/**
 * Returns the timestamp's name for the rendering step specified by `index`. See also
 * `capture_timestamp`.
 *
 * Generated from Godot docs: RenderingDevice.get_captured_timestamp_name
 */
fun RenderingDevice.getCapturedTimestampName(index: Long): String {
    return ObjectCalls.ptrcallWithUInt32ArgRetString(getCapturedTimestampNameBind, handle, index)
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
fun RenderingDevice.getTrackedObjectName(typeIndex: Long): String {
    return ObjectCalls.ptrcallWithUInt32ArgRetString(getTrackedObjectNameBind, handle, typeIndex)
}

private const val TEXTURE_CREATE_HASH = 3709173589L
private val textureCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "texture_create", TEXTURE_CREATE_HASH)
}

private const val TEXTURE_UPDATE_HASH = 1349464008L
private val textureUpdateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "texture_update", TEXTURE_UPDATE_HASH)
}

private const val TEXTURE_GET_DATA_HASH = 1859412099L
private val textureGetDataBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "texture_get_data", TEXTURE_GET_DATA_HASH)
}

private const val FRAMEBUFFER_FORMAT_CREATE_HASH = 697032759L
private val framebufferFormatCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_format_create", FRAMEBUFFER_FORMAT_CREATE_HASH)
}

private const val FRAMEBUFFER_FORMAT_CREATE_MULTIPASS_HASH = 2647479094L
private val framebufferFormatCreateMultipassBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_format_create_multipass", FRAMEBUFFER_FORMAT_CREATE_MULTIPASS_HASH)
}

private const val FRAMEBUFFER_CREATE_HASH = 3284231055L
private val framebufferCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_create", FRAMEBUFFER_CREATE_HASH)
}

private const val FRAMEBUFFER_CREATE_MULTIPASS_HASH = 1750306695L
private val framebufferCreateMultipassBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "framebuffer_create_multipass", FRAMEBUFFER_CREATE_MULTIPASS_HASH)
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

private const val SHADER_COMPILE_BINARY_FROM_SPIRV_HASH = 134910450L
private val shaderCompileBinaryFromSpirvBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "shader_compile_binary_from_spirv", SHADER_COMPILE_BINARY_FROM_SPIRV_HASH)
}

private const val SHADER_CREATE_FROM_BYTECODE_HASH = 1687031350L
private val shaderCreateFromBytecodeBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "shader_create_from_bytecode", SHADER_CREATE_FROM_BYTECODE_HASH)
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

private const val BUFFER_UPDATE_HASH = 3454956949L
private val bufferUpdateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "buffer_update", BUFFER_UPDATE_HASH)
}

private const val BUFFER_GET_DATA_HASH = 3101830688L
private val bufferGetDataBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "buffer_get_data", BUFFER_GET_DATA_HASH)
}

private const val RENDER_PIPELINE_CREATE_HASH = 2385451958L
private val renderPipelineCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "render_pipeline_create", RENDER_PIPELINE_CREATE_HASH)
}

private const val COMPUTE_PIPELINE_CREATE_HASH = 1448838280L
private val computePipelineCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "compute_pipeline_create", COMPUTE_PIPELINE_CREATE_HASH)
}

private const val RAYTRACING_PIPELINE_CREATE_HASH = 1489129684L
private val raytracingPipelineCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "raytracing_pipeline_create", RAYTRACING_PIPELINE_CREATE_HASH)
}

private const val BLAS_CREATE_HASH = 1010940044L
private val blasCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "blas_create", BLAS_CREATE_HASH)
}

private const val TLAS_BUILD_HASH = 261981775L
private val tlasBuildBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "tlas_build", TLAS_BUILD_HASH)
}

private const val HIT_SBT_RANGE_UPDATE_HASH = 1332346675L
private val hitSbtRangeUpdateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "hit_sbt_range_update", HIT_SBT_RANGE_UPDATE_HASH)
}

private const val DRAW_LIST_BEGIN_HASH = 1317926357L
private val drawListBeginBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_begin", DRAW_LIST_BEGIN_HASH)
}

private const val DRAW_LIST_BEGIN_SPLIT_HASH = 2406300660L
private val drawListBeginSplitBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_begin_split", DRAW_LIST_BEGIN_SPLIT_HASH)
}

private const val DRAW_LIST_BIND_VERTEX_BUFFERS_FORMAT_HASH = 2008628980L
private val drawListBindVertexBuffersFormatBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_vertex_buffers_format", DRAW_LIST_BIND_VERTEX_BUFFERS_FORMAT_HASH)
}

private const val DRAW_LIST_SET_PUSH_CONSTANT_HASH = 2772371345L
private val drawListSetPushConstantBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_set_push_constant", DRAW_LIST_SET_PUSH_CONSTANT_HASH)
}

private const val DRAW_LIST_SWITCH_TO_NEXT_PASS_SPLIT_HASH = 2865087369L
private val drawListSwitchToNextPassSplitBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_switch_to_next_pass_split", DRAW_LIST_SWITCH_TO_NEXT_PASS_SPLIT_HASH)
}

private const val COMPUTE_LIST_SET_PUSH_CONSTANT_HASH = 2772371345L
private val computeListSetPushConstantBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "compute_list_set_push_constant", COMPUTE_LIST_SET_PUSH_CONSTANT_HASH)
}

private const val RAYTRACING_LIST_SET_PUSH_CONSTANT_HASH = 2772371345L
private val raytracingListSetPushConstantBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "raytracing_list_set_push_constant", RAYTRACING_LIST_SET_PUSH_CONSTANT_HASH)
}

private const val GET_CAPTURED_TIMESTAMP_NAME_HASH = 844755477L
private val getCapturedTimestampNameBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "get_captured_timestamp_name", GET_CAPTURED_TIMESTAMP_NAME_HASH)
}

private const val GET_TRACKED_OBJECT_NAME_HASH = 844755477L
private val getTrackedObjectNameBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "get_tracked_object_name", GET_TRACKED_OBJECT_NAME_HASH)
}

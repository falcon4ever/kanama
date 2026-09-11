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
// KANAMA-IOS-GAP RenderingDevice waits on: ptrcallWithObjectListArgRetLong,
//   ptrcallWithObjectListLongArgsRetRID, ptrcallWithObjectListRIDUInt32ArgsRetRID,
//   ptrcallWithObjectListUInt32ArgsRetLong, ptrcallWithRIDAndObjectListArgsRetLong,
//   ptrcallWithRIDListLongUInt32ArgsRetRID, ptrcallWithRIDListObjectListLongUInt32ArgsRetRID,
//   ptrcallWithRIDObjectListArgsRetRID,
//   ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID,
//   ptrcallWithRIDUInt32FourLongPackedColorListDoubleUInt32Rect2RIDListArgsRetPackedInt64List,
//   ptrcallWithThreeObjectListUInt32ArgsRetRID, ptrcallWithTwoLongUInt32RIDListPackedInt64ListArgs,
//   ptrcallWithTwoObjectByteArrayListArgsRetRID, ptrcallWithTwoObjectListUInt32ArgsRetLong,
//   ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID
// Index: docs/reference/generated/ios-shape-gap.md

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

private const val TEXTURE_CREATE_HASH = 3709173589L
private val textureCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "texture_create", TEXTURE_CREATE_HASH)
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

private const val VERTEX_FORMAT_CREATE_HASH = 1242678479L
private val vertexFormatCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "vertex_format_create", VERTEX_FORMAT_CREATE_HASH)
}

private const val VERTEX_ARRAY_CREATE_HASH = 3799816279L
private val vertexArrayCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "vertex_array_create", VERTEX_ARRAY_CREATE_HASH)
}

private const val UNIFORM_SET_CREATE_HASH = 2280795797L
private val uniformSetCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "uniform_set_create", UNIFORM_SET_CREATE_HASH)
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

private const val DRAW_LIST_BEGIN_SPLIT_HASH = 2406300660L
private val drawListBeginSplitBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_begin_split", DRAW_LIST_BEGIN_SPLIT_HASH)
}

private const val DRAW_LIST_BIND_VERTEX_BUFFERS_FORMAT_HASH = 2008628980L
private val drawListBindVertexBuffersFormatBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "draw_list_bind_vertex_buffers_format", DRAW_LIST_BIND_VERTEX_BUFFERS_FORMAT_HASH)
}

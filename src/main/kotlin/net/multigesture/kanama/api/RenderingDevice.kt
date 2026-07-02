package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
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
class RenderingDevice(handle: MemorySegment) : GodotObject(handle) {
    fun textureCreate(format: RDTextureFormat?, view: RDTextureView?, data: List<ByteArray>): RID {
        return ObjectCalls.ptrcallWithTwoObjectByteArrayListArgsRetRID(textureCreateBind, handle, format?.requireOpenHandle() ?: MemorySegment.NULL, view?.requireOpenHandle() ?: MemorySegment.NULL, data)
    }

    fun textureCreateShared(view: RDTextureView?, withTexture: RID): RID {
        return ObjectCalls.ptrcallWithObjectRIDArgsRetRID(textureCreateSharedBind, handle, view?.requireOpenHandle() ?: MemorySegment.NULL, withTexture)
    }

    fun textureCreateSharedFromSlice(view: RDTextureView?, withTexture: RID, layer: Long, mipmap: Long, mipmaps: Long = 1L, sliceType: Long = 0L): RID {
        return ObjectCalls.ptrcallWithObjectRIDThreeUInt32LongArgsRetRID(textureCreateSharedFromSliceBind, handle, view?.requireOpenHandle() ?: MemorySegment.NULL, withTexture, layer, mipmap, mipmaps, sliceType)
    }

    fun textureCreateFromExtension(type: Long, format: Long, samples: Long, usageFlags: Long, image: Long, width: Long, height: Long, depth: Long, layers: Long, mipmaps: Long = 1L): RID {
        return ObjectCalls.ptrcallWithFourLongLongLongLongLongLongArgsRetRID(textureCreateFromExtensionBind, handle, type, format, samples, usageFlags, image, width, height, depth, layers, mipmaps)
    }

    fun textureUpdate(texture: RID, layer: Long, data: ByteArray): Long {
        return ObjectCalls.ptrcallWithRIDUInt32ByteArrayArgsRetLong(textureUpdateBind, handle, texture, layer, data)
    }

    fun textureGetData(texture: RID, layer: Long): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetByteArray(textureGetDataBind, handle, texture, layer)
    }

    fun textureGetDataAsync(texture: RID, layer: Long, callback: GodotCallable): Long {
        return ObjectCalls.ptrcallWithRIDUInt32CallableArgsRetLong(textureGetDataAsyncBind, handle, texture, layer, callback.target.handle, callback.method)
    }

    fun textureIsFormatSupportedForUsage(format: Long, usageFlags: Long): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(textureIsFormatSupportedForUsageBind, handle, format, usageFlags)
    }

    fun textureIsShared(texture: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(textureIsSharedBind, handle, texture)
    }

    fun textureIsValid(texture: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(textureIsValidBind, handle, texture)
    }

    fun textureSetDiscardable(texture: RID, discardable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(textureSetDiscardableBind, handle, texture, discardable)
    }

    fun textureIsDiscardable(texture: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(textureIsDiscardableBind, handle, texture)
    }

    fun textureCopy(fromTexture: RID, toTexture: RID, fromPos: Vector3, toPos: Vector3, size: Vector3, srcMipmap: Long, dstMipmap: Long, srcLayer: Long, dstLayer: Long): Long {
        return ObjectCalls.ptrcallWithTwoRIDThreeVector3FourUInt32ArgsRetLong(textureCopyBind, handle, fromTexture, toTexture, fromPos, toPos, size, srcMipmap, dstMipmap, srcLayer, dstLayer)
    }

    fun textureClear(texture: RID, color: Color, baseMipmap: Long, mipmapCount: Long, baseLayer: Long, layerCount: Long): Long {
        return ObjectCalls.ptrcallWithRIDColorFourUInt32ArgsRetLong(textureClearBind, handle, texture, color, baseMipmap, mipmapCount, baseLayer, layerCount)
    }

    fun textureResolveMultisample(fromTexture: RID, toTexture: RID): Long {
        return ObjectCalls.ptrcallWithTwoRIDArgsRetLong(textureResolveMultisampleBind, handle, fromTexture, toTexture)
    }

    fun textureGetFormat(texture: RID): RDTextureFormat? {
        return RDTextureFormat.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(textureGetFormatBind, handle, texture))
    }

    fun textureGetNativeHandle(texture: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(textureGetNativeHandleBind, handle, texture)
    }

    fun framebufferFormatCreate(attachments: List<RDAttachmentFormat>, viewCount: Long = 1L): Long {
        return ObjectCalls.ptrcallWithObjectListUInt32ArgsRetLong(framebufferFormatCreateBind, handle, attachments, viewCount)
    }

    fun framebufferFormatCreateMultipass(attachments: List<RDAttachmentFormat>, passes: List<RDFramebufferPass>, viewCount: Long = 1L): Long {
        return ObjectCalls.ptrcallWithTwoObjectListUInt32ArgsRetLong(framebufferFormatCreateMultipassBind, handle, attachments, passes, viewCount)
    }

    fun framebufferFormatCreateEmpty(samples: Long = 0L): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(framebufferFormatCreateEmptyBind, handle, samples)
    }

    fun framebufferFormatGetTextureSamples(format: Long, renderPass: Long = 0L): Long {
        return ObjectCalls.ptrcallWithLongAndUInt32ArgRetLong(framebufferFormatGetTextureSamplesBind, handle, format, renderPass)
    }

    fun framebufferCreate(textures: List<RID>, validateWithFormat: Long = -1L, viewCount: Long = 1L): RID {
        return ObjectCalls.ptrcallWithRIDListLongUInt32ArgsRetRID(framebufferCreateBind, handle, textures, validateWithFormat, viewCount)
    }

    fun framebufferCreateMultipass(textures: List<RID>, passes: List<RDFramebufferPass>, validateWithFormat: Long = -1L, viewCount: Long = 1L): RID {
        return ObjectCalls.ptrcallWithRIDListObjectListLongUInt32ArgsRetRID(framebufferCreateMultipassBind, handle, textures, passes, validateWithFormat, viewCount)
    }

    fun framebufferCreateEmpty(size: Vector2i, samples: Long = 0L, validateWithFormat: Long = -1L): RID {
        return ObjectCalls.ptrcallWithVector2iLongLongArgsRetRID(framebufferCreateEmptyBind, handle, size, samples, validateWithFormat)
    }

    fun framebufferGetFormat(framebuffer: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(framebufferGetFormatBind, handle, framebuffer)
    }

    fun framebufferIsValid(framebuffer: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(framebufferIsValidBind, handle, framebuffer)
    }

    fun samplerCreate(state: RDSamplerState?): RID {
        return ObjectCalls.ptrcallWithObjectArgRetRID(samplerCreateBind, handle, state?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun samplerIsFormatSupportedForFilter(format: Long, samplerFilter: Long): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(samplerIsFormatSupportedForFilterBind, handle, format, samplerFilter)
    }

    fun vertexBufferCreate(sizeBytes: Long, data: ByteArray, creationBits: Long = 0L): RID {
        return ObjectCalls.ptrcallWithUInt32ByteArrayLongArgsRetRID(vertexBufferCreateBind, handle, sizeBytes, data, creationBits)
    }

    fun vertexFormatCreate(vertexDescriptions: List<RDVertexAttribute>): Long {
        return ObjectCalls.ptrcallWithObjectListArgRetLong(vertexFormatCreateBind, handle, vertexDescriptions)
    }

    fun vertexArrayCreate(vertexCount: Long, vertexFormat: Long, srcBuffers: List<RID>, offsets: List<Long>): RID {
        return ObjectCalls.ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID(vertexArrayCreateBind, handle, vertexCount, vertexFormat, srcBuffers, offsets)
    }

    fun indexBufferCreate(sizeIndices: Long, format: Long, data: ByteArray, useRestartIndices: Boolean = false, creationBits: Long = 0L): RID {
        return ObjectCalls.ptrcallWithUInt32LongPackedByteArrayBoolLongArgsRetRID(indexBufferCreateBind, handle, sizeIndices, format, data, useRestartIndices, creationBits)
    }

    fun indexArrayCreate(indexBuffer: RID, indexOffset: Long, indexCount: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetRID(indexArrayCreateBind, handle, indexBuffer, indexOffset, indexCount)
    }

    fun shaderCompileSpirvFromSource(shaderSource: RDShaderSource?, allowCache: Boolean = true): RDShaderSPIRV? {
        return RDShaderSPIRV.wrap(ObjectCalls.ptrcallWithObjectAndBoolArgRetObject(shaderCompileSpirvFromSourceBind, handle, shaderSource?.requireOpenHandle() ?: MemorySegment.NULL, allowCache))
    }

    fun shaderCompileBinaryFromSpirv(spirvData: RDShaderSPIRV?, name: String = ""): ByteArray {
        return ObjectCalls.ptrcallWithObjectAndStringArgRetByteArray(shaderCompileBinaryFromSpirvBind, handle, spirvData?.requireOpenHandle() ?: MemorySegment.NULL, name)
    }

    fun shaderCreateFromSpirv(spirvData: RDShaderSPIRV?, name: String = ""): RID {
        return ObjectCalls.ptrcallWithObjectStringArgsRetRID(shaderCreateFromSpirvBind, handle, spirvData?.requireOpenHandle() ?: MemorySegment.NULL, name)
    }

    fun shaderCreateFromBytecode(binaryData: ByteArray, placeholderRid: RID): RID {
        return ObjectCalls.ptrcallWithByteArrayAndRIDArgRetRID(shaderCreateFromBytecodeBind, handle, binaryData, placeholderRid)
    }

    fun shaderCreatePlaceholder(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(shaderCreatePlaceholderBind, handle)
    }

    fun shaderGetVertexInputAttributeMask(shader: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shaderGetVertexInputAttributeMaskBind, handle, shader)
    }

    fun uniformBufferCreate(sizeBytes: Long, data: ByteArray, creationBits: Long = 0L): RID {
        return ObjectCalls.ptrcallWithUInt32ByteArrayLongArgsRetRID(uniformBufferCreateBind, handle, sizeBytes, data, creationBits)
    }

    fun storageBufferCreate(sizeBytes: Long, data: ByteArray, usage: Long = 0L, creationBits: Long = 0L): RID {
        return ObjectCalls.ptrcallWithUInt32PackedByteArrayTwoLongArgsRetRID(storageBufferCreateBind, handle, sizeBytes, data, usage, creationBits)
    }

    fun textureBufferCreate(sizeBytes: Long, format: Long, data: ByteArray): RID {
        return ObjectCalls.ptrcallWithUInt32LongByteArrayArgsRetRID(textureBufferCreateBind, handle, sizeBytes, format, data)
    }

    fun uniformSetCreate(uniforms: List<RDUniform>, shader: RID, shaderSet: Long): RID {
        return ObjectCalls.ptrcallWithObjectListRIDUInt32ArgsRetRID(uniformSetCreateBind, handle, uniforms, shader, shaderSet)
    }

    fun uniformSetIsValid(uniformSet: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(uniformSetIsValidBind, handle, uniformSet)
    }

    fun bufferCopy(srcBuffer: RID, dstBuffer: RID, srcOffset: Long, dstOffset: Long, size: Long): Long {
        return ObjectCalls.ptrcallWithTwoRIDThreeUInt32ArgsRetLong(bufferCopyBind, handle, srcBuffer, dstBuffer, srcOffset, dstOffset, size)
    }

    fun bufferUpdate(buffer: RID, offset: Long, sizeBytes: Long, data: ByteArray): Long {
        return ObjectCalls.ptrcallWithRIDTwoUInt32PackedByteArrayArgsRetLong(bufferUpdateBind, handle, buffer, offset, sizeBytes, data)
    }

    fun bufferClear(buffer: RID, offset: Long, sizeBytes: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetLong(bufferClearBind, handle, buffer, offset, sizeBytes)
    }

    fun bufferGetData(buffer: RID, offsetBytes: Long = 0L, sizeBytes: Long = 0L): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetByteArray(bufferGetDataBind, handle, buffer, offsetBytes, sizeBytes)
    }

    fun bufferGetDataAsync(buffer: RID, callback: GodotCallable, offsetBytes: Long = 0L, sizeBytes: Long = 0L): Long {
        return ObjectCalls.ptrcallWithRIDCallableTwoUInt32ArgsRetLong(bufferGetDataAsyncBind, handle, buffer, callback.target.handle, callback.method, offsetBytes, sizeBytes)
    }

    fun bufferGetDeviceAddress(buffer: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bufferGetDeviceAddressBind, handle, buffer)
    }

    fun renderPipelineCreate(shader: RID, framebufferFormat: Long, vertexFormat: Long, primitive: Long, rasterizationState: RDPipelineRasterizationState?, multisampleState: RDPipelineMultisampleState?, stencilState: RDPipelineDepthStencilState?, colorBlendState: RDPipelineColorBlendState?, dynamicStateFlags: Long = 0L, forRenderPass: Long = 0L, specializationConstants: List<RDPipelineSpecializationConstant>): RID {
        return ObjectCalls.ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID(renderPipelineCreateBind, handle, shader, framebufferFormat, vertexFormat, primitive, rasterizationState?.requireOpenHandle() ?: MemorySegment.NULL, multisampleState?.requireOpenHandle() ?: MemorySegment.NULL, stencilState?.requireOpenHandle() ?: MemorySegment.NULL, colorBlendState?.requireOpenHandle() ?: MemorySegment.NULL, dynamicStateFlags, forRenderPass, specializationConstants)
    }

    fun renderPipelineIsValid(renderPipeline: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(renderPipelineIsValidBind, handle, renderPipeline)
    }

    fun computePipelineCreate(shader: RID, specializationConstants: List<RDPipelineSpecializationConstant>): RID {
        return ObjectCalls.ptrcallWithRIDObjectListArgsRetRID(computePipelineCreateBind, handle, shader, specializationConstants)
    }

    fun computePipelineIsValid(computePipeline: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(computePipelineIsValidBind, handle, computePipeline)
    }

    fun raytracingPipelineIsValid(raytracingPipeline: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(raytracingPipelineIsValidBind, handle, raytracingPipeline)
    }

    fun tlasCreate(maxInstanceCount: Long, flags: Long): RID {
        return ObjectCalls.ptrcallWithUInt32AndLongArgRetRID(tlasCreateBind, handle, maxInstanceCount, flags)
    }

    fun blasBuild(blas: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(blasBuildBind, handle, blas)
    }

    fun hitSbtCreate(raytracingPipeline: RID, initialHitGroupCapacity: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetRID(hitSbtCreateBind, handle, raytracingPipeline, initialHitGroupCapacity)
    }

    fun hitSbtSetPipeline(hitSbt: RID, raytracingPipeline: RID): Long {
        return ObjectCalls.ptrcallWithTwoRIDArgsRetLong(hitSbtSetPipelineBind, handle, hitSbt, raytracingPipeline)
    }

    fun hitSbtRangeAlloc(hitSbt: RID, hitGroupCount: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetLong(hitSbtRangeAllocBind, handle, hitSbt, hitGroupCount)
    }

    fun hitSbtRangeFree(hitSbt: RID, range: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(hitSbtRangeFreeBind, handle, hitSbt, range)
    }

    fun hitSbtRangeUpdate(hitSbt: RID, range: Long, offset: Long, hitGroupIndices: List<Int>): Long {
        return ObjectCalls.ptrcallWithRIDLongUInt32AndPackedInt32ListArgRetLong(hitSbtRangeUpdateBind, handle, hitSbt, range, offset, hitGroupIndices)
    }

    fun screenGetWidth(screen: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(screenGetWidthBind, handle, screen)
    }

    fun screenGetHeight(screen: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(screenGetHeightBind, handle, screen)
    }

    fun screenGetFramebufferFormat(screen: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(screenGetFramebufferFormatBind, handle, screen)
    }

    fun drawListBeginForScreen(screen: Int = 0, clearColor: Color): Long {
        return ObjectCalls.ptrcallWithIntColorArgsRetLong(drawListBeginForScreenBind, handle, screen, clearColor)
    }

    fun drawListBegin(framebuffer: RID, drawFlags: Long = 0L, clearColorValues: List<Color>, clearDepthValue: Double = 1.0, clearStencilValue: Long = 0L, region: Rect2, breadcrumb: Long = 0L): Long {
        return ObjectCalls.ptrcallWithRIDLongPackedColorListDoubleUInt32Rect2UInt32ArgsRetLong(drawListBeginBind, handle, framebuffer, drawFlags, clearColorValues, clearDepthValue, clearStencilValue, region, breadcrumb)
    }

    fun drawListBeginSplit(framebuffer: RID, splits: Long, initialColorAction: Long, finalColorAction: Long, initialDepthAction: Long, finalDepthAction: Long, clearColorValues: List<Color>, clearDepth: Double = 1.0, clearStencil: Long = 0L, region: Rect2, storageTextures: List<RID>): List<Long> {
        return ObjectCalls.ptrcallWithRIDUInt32FourLongPackedColorListDoubleUInt32Rect2RIDListArgsRetPackedInt64List(drawListBeginSplitBind, handle, framebuffer, splits, initialColorAction, finalColorAction, initialDepthAction, finalDepthAction, clearColorValues, clearDepth, clearStencil, region, storageTextures)
    }

    fun drawListSetBlendConstants(drawList: Long, color: Color) {
        ObjectCalls.ptrcallWithLongAndColorArg(drawListSetBlendConstantsBind, handle, drawList, color)
    }

    fun drawListBindRenderPipeline(drawList: Long, renderPipeline: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(drawListBindRenderPipelineBind, handle, drawList, renderPipeline)
    }

    fun drawListBindUniformSet(drawList: Long, uniformSet: RID, setIndex: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(drawListBindUniformSetBind, handle, drawList, uniformSet, setIndex)
    }

    fun drawListBindVertexArray(drawList: Long, vertexArray: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(drawListBindVertexArrayBind, handle, drawList, vertexArray)
    }

    fun drawListBindVertexBuffersFormat(drawList: Long, vertexFormat: Long, vertexCount: Long, vertexBuffers: List<RID>, offsets: List<Long>) {
        ObjectCalls.ptrcallWithTwoLongUInt32RIDListPackedInt64ListArgs(drawListBindVertexBuffersFormatBind, handle, drawList, vertexFormat, vertexCount, vertexBuffers, offsets)
    }

    fun drawListBindIndexArray(drawList: Long, indexArray: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(drawListBindIndexArrayBind, handle, drawList, indexArray)
    }

    fun drawListSetPushConstant(drawList: Long, buffer: ByteArray, sizeBytes: Long) {
        ObjectCalls.ptrcallWithLongByteArrayUInt32Args(drawListSetPushConstantBind, handle, drawList, buffer, sizeBytes)
    }

    fun drawListDraw(drawList: Long, useIndices: Boolean, instances: Long, proceduralVertexCount: Long = 0L) {
        ObjectCalls.ptrcallWithLongBoolTwoUInt32Args(drawListDrawBind, handle, drawList, useIndices, instances, proceduralVertexCount)
    }

    fun drawListDrawIndirect(drawList: Long, useIndices: Boolean, buffer: RID, offset: Long = 0L, drawCount: Long = 1L, stride: Long = 0L) {
        ObjectCalls.ptrcallWithLongBoolRIDThreeUInt32Args(drawListDrawIndirectBind, handle, drawList, useIndices, buffer, offset, drawCount, stride)
    }

    fun drawListEnableScissor(drawList: Long, rect: Rect2) {
        ObjectCalls.ptrcallWithLongRect2Args(drawListEnableScissorBind, handle, drawList, rect)
    }

    fun drawListDisableScissor(drawList: Long) {
        ObjectCalls.ptrcallWithLongArg(drawListDisableScissorBind, handle, drawList)
    }

    fun drawListSwitchToNextPass(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(drawListSwitchToNextPassBind, handle)
    }

    fun drawListSwitchToNextPassSplit(splits: Long): List<Long> {
        return ObjectCalls.ptrcallWithUInt32ArgRetPackedInt64List(drawListSwitchToNextPassSplitBind, handle, splits)
    }

    fun drawListEnd() {
        ObjectCalls.ptrcallNoArgs(drawListEndBind, handle)
    }

    fun computeListBegin(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(computeListBeginBind, handle)
    }

    fun computeListBindComputePipeline(computeList: Long, computePipeline: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(computeListBindComputePipelineBind, handle, computeList, computePipeline)
    }

    fun computeListSetPushConstant(computeList: Long, buffer: ByteArray, sizeBytes: Long) {
        ObjectCalls.ptrcallWithLongByteArrayUInt32Args(computeListSetPushConstantBind, handle, computeList, buffer, sizeBytes)
    }

    fun computeListBindUniformSet(computeList: Long, uniformSet: RID, setIndex: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(computeListBindUniformSetBind, handle, computeList, uniformSet, setIndex)
    }

    fun computeListDispatch(computeList: Long, xGroups: Long, yGroups: Long, zGroups: Long) {
        ObjectCalls.ptrcallWithLongAndThreeUInt32Args(computeListDispatchBind, handle, computeList, xGroups, yGroups, zGroups)
    }

    fun computeListDispatchIndirect(computeList: Long, buffer: RID, offset: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(computeListDispatchIndirectBind, handle, computeList, buffer, offset)
    }

    fun computeListAddBarrier(computeList: Long) {
        ObjectCalls.ptrcallWithLongArg(computeListAddBarrierBind, handle, computeList)
    }

    fun computeListEnd() {
        ObjectCalls.ptrcallNoArgs(computeListEndBind, handle)
    }

    fun raytracingListBegin(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(raytracingListBeginBind, handle)
    }

    fun raytracingListBindRaytracingPipeline(raytracingList: Long, raytracingPipeline: RID) {
        ObjectCalls.ptrcallWithLongAndRIDArg(raytracingListBindRaytracingPipelineBind, handle, raytracingList, raytracingPipeline)
    }

    fun raytracingListSetPushConstant(raytracingList: Long, buffer: ByteArray, sizeBytes: Long) {
        ObjectCalls.ptrcallWithLongByteArrayUInt32Args(raytracingListSetPushConstantBind, handle, raytracingList, buffer, sizeBytes)
    }

    fun raytracingListBindUniformSet(raytracingList: Long, uniformSet: RID, setIndex: Long) {
        ObjectCalls.ptrcallWithLongRIDAndUInt32Args(raytracingListBindUniformSetBind, handle, raytracingList, uniformSet, setIndex)
    }

    fun raytracingListTraceRays(raytracingList: Long, raygenShaderIndex: Long, hitSbt: RID, width: Long, height: Long, depth: Long) {
        ObjectCalls.ptrcallWithLongUInt32RIDThreeUInt32Args(raytracingListTraceRaysBind, handle, raytracingList, raygenShaderIndex, hitSbt, width, height, depth)
    }

    fun raytracingListEnd() {
        ObjectCalls.ptrcallNoArgs(raytracingListEndBind, handle)
    }

    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, handle, rid)
    }

    fun captureTimestamp(name: String) {
        ObjectCalls.ptrcallWithStringArg(captureTimestampBind, handle, name)
    }

    fun getCapturedTimestampsCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCapturedTimestampsCountBind, handle)
    }

    fun getCapturedTimestampsFrame(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCapturedTimestampsFrameBind, handle)
    }

    fun getCapturedTimestampGpuTime(index: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getCapturedTimestampGpuTimeBind, handle, index)
    }

    fun getCapturedTimestampCpuTime(index: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getCapturedTimestampCpuTimeBind, handle, index)
    }

    fun getCapturedTimestampName(index: Long): String {
        return ObjectCalls.ptrcallWithUInt32ArgRetString(getCapturedTimestampNameBind, handle, index)
    }

    fun hasFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, handle, feature)
    }

    fun limitGet(limit: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(limitGetBind, handle, limit)
    }

    fun getFrameDelay(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getFrameDelayBind, handle)
    }

    /**
     * Pushes the frame setup and draw command buffers then marks the local device as currently
     * processing (which allows calling `sync`). Note: Only available in local RenderingDevices.
     *
     * Generated from Godot docs: RenderingDevice.submit
     */
    fun submit() {
        ObjectCalls.ptrcallNoArgs(submitBind, handle)
    }

    /**
     * Forces a synchronization between the CPU and GPU, which may be required in certain cases. Only
     * call this when needed, as CPU-GPU synchronization has a performance cost. Note: Only available
     * in local RenderingDevices. Note: `sync` can only be called after a `submit`.
     *
     * Generated from Godot docs: RenderingDevice.sync
     */
    fun sync() {
        ObjectCalls.ptrcallNoArgs(syncBind, handle)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: RenderingDevice.barrier
     */
    fun barrier(from: Long = 32767L, to: Long = 32767L) {
        ObjectCalls.ptrcallWithTwoLongArgs(barrierBind, handle, from, to)
    }

    fun fullBarrier() {
        ObjectCalls.ptrcallNoArgs(fullBarrierBind, handle)
    }

    fun createLocalDevice(): RenderingDevice? {
        return RenderingDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(createLocalDeviceBind, handle))
    }

    fun setResourceName(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(setResourceNameBind, handle, id, name)
    }

    fun drawCommandBeginLabel(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(drawCommandBeginLabelBind, handle, name, color)
    }

    fun drawCommandInsertLabel(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringAndColorArg(drawCommandInsertLabelBind, handle, name, color)
    }

    fun drawCommandEndLabel() {
        ObjectCalls.ptrcallNoArgs(drawCommandEndLabelBind, handle)
    }

    fun getDeviceVendorName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDeviceVendorNameBind, handle)
    }

    fun getDeviceName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDeviceNameBind, handle)
    }

    fun getDevicePipelineCacheUuid(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDevicePipelineCacheUuidBind, handle)
    }

    fun getMemoryUsage(type: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getMemoryUsageBind, handle, type)
    }

    fun getDriverResource(resource: Long, rid: RID, index: Long): Long {
        return ObjectCalls.ptrcallWithLongRIDLongArgsRetLong(getDriverResourceBind, handle, resource, rid, index)
    }

    fun getPerfReport(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPerfReportBind, handle)
    }

    fun getDriverAndDeviceMemoryReport(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDriverAndDeviceMemoryReportBind, handle)
    }

    fun getTrackedObjectName(typeIndex: Long): String {
        return ObjectCalls.ptrcallWithUInt32ArgRetString(getTrackedObjectNameBind, handle, typeIndex)
    }

    fun getTrackedObjectTypeCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackedObjectTypeCountBind, handle)
    }

    fun getDriverTotalMemory(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDriverTotalMemoryBind, handle)
    }

    fun getDriverAllocationCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDriverAllocationCountBind, handle)
    }

    fun getDriverMemoryByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDriverMemoryByObjectTypeBind, handle, type)
    }

    fun getDriverAllocsByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDriverAllocsByObjectTypeBind, handle, type)
    }

    fun getDeviceTotalMemory(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDeviceTotalMemoryBind, handle)
    }

    fun getDeviceAllocationCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDeviceAllocationCountBind, handle)
    }

    fun getDeviceMemoryByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDeviceMemoryByObjectTypeBind, handle, type)
    }

    fun getDeviceAllocsByObjectType(type: Long): Long {
        return ObjectCalls.ptrcallWithUInt32ArgRetLong(getDeviceAllocsByObjectTypeBind, handle, type)
    }

    companion object {
        const val INVALID_ID: Long = -1L
        const val INVALID_FORMAT_ID: Long = -1L
        const val DEVICE_TYPE_OTHER: Long = 0L
        const val DEVICE_TYPE_INTEGRATED_GPU: Long = 1L
        const val DEVICE_TYPE_DISCRETE_GPU: Long = 2L
        const val DEVICE_TYPE_VIRTUAL_GPU: Long = 3L
        const val DEVICE_TYPE_CPU: Long = 4L
        const val DEVICE_TYPE_MAX: Long = 5L
        const val DRIVER_RESOURCE_LOGICAL_DEVICE: Long = 0L
        const val DRIVER_RESOURCE_PHYSICAL_DEVICE: Long = 1L
        const val DRIVER_RESOURCE_TOPMOST_OBJECT: Long = 2L
        const val DRIVER_RESOURCE_COMMAND_QUEUE: Long = 3L
        const val DRIVER_RESOURCE_QUEUE_FAMILY: Long = 4L
        const val DRIVER_RESOURCE_TEXTURE: Long = 5L
        const val DRIVER_RESOURCE_TEXTURE_VIEW: Long = 6L
        const val DRIVER_RESOURCE_TEXTURE_DATA_FORMAT: Long = 7L
        const val DRIVER_RESOURCE_SAMPLER: Long = 8L
        const val DRIVER_RESOURCE_UNIFORM_SET: Long = 9L
        const val DRIVER_RESOURCE_BUFFER: Long = 10L
        const val DRIVER_RESOURCE_COMPUTE_PIPELINE: Long = 11L
        const val DRIVER_RESOURCE_RENDER_PIPELINE: Long = 12L
        const val DRIVER_RESOURCE_VULKAN_DEVICE: Long = 0L
        const val DRIVER_RESOURCE_VULKAN_PHYSICAL_DEVICE: Long = 1L
        const val DRIVER_RESOURCE_VULKAN_INSTANCE: Long = 2L
        const val DRIVER_RESOURCE_VULKAN_QUEUE: Long = 3L
        const val DRIVER_RESOURCE_VULKAN_QUEUE_FAMILY_INDEX: Long = 4L
        const val DRIVER_RESOURCE_VULKAN_IMAGE: Long = 5L
        const val DRIVER_RESOURCE_VULKAN_IMAGE_VIEW: Long = 6L
        const val DRIVER_RESOURCE_VULKAN_IMAGE_NATIVE_TEXTURE_FORMAT: Long = 7L
        const val DRIVER_RESOURCE_VULKAN_SAMPLER: Long = 8L
        const val DRIVER_RESOURCE_VULKAN_DESCRIPTOR_SET: Long = 9L
        const val DRIVER_RESOURCE_VULKAN_BUFFER: Long = 10L
        const val DRIVER_RESOURCE_VULKAN_COMPUTE_PIPELINE: Long = 11L
        const val DRIVER_RESOURCE_VULKAN_RENDER_PIPELINE: Long = 12L
        const val DATA_FORMAT_R4G4_UNORM_PACK8: Long = 0L
        const val DATA_FORMAT_R4G4B4A4_UNORM_PACK16: Long = 1L
        const val DATA_FORMAT_B4G4R4A4_UNORM_PACK16: Long = 2L
        const val DATA_FORMAT_R5G6B5_UNORM_PACK16: Long = 3L
        const val DATA_FORMAT_B5G6R5_UNORM_PACK16: Long = 4L
        const val DATA_FORMAT_R5G5B5A1_UNORM_PACK16: Long = 5L
        const val DATA_FORMAT_B5G5R5A1_UNORM_PACK16: Long = 6L
        const val DATA_FORMAT_A1R5G5B5_UNORM_PACK16: Long = 7L
        const val DATA_FORMAT_R8_UNORM: Long = 8L
        const val DATA_FORMAT_R8_SNORM: Long = 9L
        const val DATA_FORMAT_R8_USCALED: Long = 10L
        const val DATA_FORMAT_R8_SSCALED: Long = 11L
        const val DATA_FORMAT_R8_UINT: Long = 12L
        const val DATA_FORMAT_R8_SINT: Long = 13L
        const val DATA_FORMAT_R8_SRGB: Long = 14L
        const val DATA_FORMAT_R8G8_UNORM: Long = 15L
        const val DATA_FORMAT_R8G8_SNORM: Long = 16L
        const val DATA_FORMAT_R8G8_USCALED: Long = 17L
        const val DATA_FORMAT_R8G8_SSCALED: Long = 18L
        const val DATA_FORMAT_R8G8_UINT: Long = 19L
        const val DATA_FORMAT_R8G8_SINT: Long = 20L
        const val DATA_FORMAT_R8G8_SRGB: Long = 21L
        const val DATA_FORMAT_R8G8B8_UNORM: Long = 22L
        const val DATA_FORMAT_R8G8B8_SNORM: Long = 23L
        const val DATA_FORMAT_R8G8B8_USCALED: Long = 24L
        const val DATA_FORMAT_R8G8B8_SSCALED: Long = 25L
        const val DATA_FORMAT_R8G8B8_UINT: Long = 26L
        const val DATA_FORMAT_R8G8B8_SINT: Long = 27L
        const val DATA_FORMAT_R8G8B8_SRGB: Long = 28L
        const val DATA_FORMAT_B8G8R8_UNORM: Long = 29L
        const val DATA_FORMAT_B8G8R8_SNORM: Long = 30L
        const val DATA_FORMAT_B8G8R8_USCALED: Long = 31L
        const val DATA_FORMAT_B8G8R8_SSCALED: Long = 32L
        const val DATA_FORMAT_B8G8R8_UINT: Long = 33L
        const val DATA_FORMAT_B8G8R8_SINT: Long = 34L
        const val DATA_FORMAT_B8G8R8_SRGB: Long = 35L
        const val DATA_FORMAT_R8G8B8A8_UNORM: Long = 36L
        const val DATA_FORMAT_R8G8B8A8_SNORM: Long = 37L
        const val DATA_FORMAT_R8G8B8A8_USCALED: Long = 38L
        const val DATA_FORMAT_R8G8B8A8_SSCALED: Long = 39L
        const val DATA_FORMAT_R8G8B8A8_UINT: Long = 40L
        const val DATA_FORMAT_R8G8B8A8_SINT: Long = 41L
        const val DATA_FORMAT_R8G8B8A8_SRGB: Long = 42L
        const val DATA_FORMAT_B8G8R8A8_UNORM: Long = 43L
        const val DATA_FORMAT_B8G8R8A8_SNORM: Long = 44L
        const val DATA_FORMAT_B8G8R8A8_USCALED: Long = 45L
        const val DATA_FORMAT_B8G8R8A8_SSCALED: Long = 46L
        const val DATA_FORMAT_B8G8R8A8_UINT: Long = 47L
        const val DATA_FORMAT_B8G8R8A8_SINT: Long = 48L
        const val DATA_FORMAT_B8G8R8A8_SRGB: Long = 49L
        const val DATA_FORMAT_A8B8G8R8_UNORM_PACK32: Long = 50L
        const val DATA_FORMAT_A8B8G8R8_SNORM_PACK32: Long = 51L
        const val DATA_FORMAT_A8B8G8R8_USCALED_PACK32: Long = 52L
        const val DATA_FORMAT_A8B8G8R8_SSCALED_PACK32: Long = 53L
        const val DATA_FORMAT_A8B8G8R8_UINT_PACK32: Long = 54L
        const val DATA_FORMAT_A8B8G8R8_SINT_PACK32: Long = 55L
        const val DATA_FORMAT_A8B8G8R8_SRGB_PACK32: Long = 56L
        const val DATA_FORMAT_A2R10G10B10_UNORM_PACK32: Long = 57L
        const val DATA_FORMAT_A2R10G10B10_SNORM_PACK32: Long = 58L
        const val DATA_FORMAT_A2R10G10B10_USCALED_PACK32: Long = 59L
        const val DATA_FORMAT_A2R10G10B10_SSCALED_PACK32: Long = 60L
        const val DATA_FORMAT_A2R10G10B10_UINT_PACK32: Long = 61L
        const val DATA_FORMAT_A2R10G10B10_SINT_PACK32: Long = 62L
        const val DATA_FORMAT_A2B10G10R10_UNORM_PACK32: Long = 63L
        const val DATA_FORMAT_A2B10G10R10_SNORM_PACK32: Long = 64L
        const val DATA_FORMAT_A2B10G10R10_USCALED_PACK32: Long = 65L
        const val DATA_FORMAT_A2B10G10R10_SSCALED_PACK32: Long = 66L
        const val DATA_FORMAT_A2B10G10R10_UINT_PACK32: Long = 67L
        const val DATA_FORMAT_A2B10G10R10_SINT_PACK32: Long = 68L
        const val DATA_FORMAT_R16_UNORM: Long = 69L
        const val DATA_FORMAT_R16_SNORM: Long = 70L
        const val DATA_FORMAT_R16_USCALED: Long = 71L
        const val DATA_FORMAT_R16_SSCALED: Long = 72L
        const val DATA_FORMAT_R16_UINT: Long = 73L
        const val DATA_FORMAT_R16_SINT: Long = 74L
        const val DATA_FORMAT_R16_SFLOAT: Long = 75L
        const val DATA_FORMAT_R16G16_UNORM: Long = 76L
        const val DATA_FORMAT_R16G16_SNORM: Long = 77L
        const val DATA_FORMAT_R16G16_USCALED: Long = 78L
        const val DATA_FORMAT_R16G16_SSCALED: Long = 79L
        const val DATA_FORMAT_R16G16_UINT: Long = 80L
        const val DATA_FORMAT_R16G16_SINT: Long = 81L
        const val DATA_FORMAT_R16G16_SFLOAT: Long = 82L
        const val DATA_FORMAT_R16G16B16_UNORM: Long = 83L
        const val DATA_FORMAT_R16G16B16_SNORM: Long = 84L
        const val DATA_FORMAT_R16G16B16_USCALED: Long = 85L
        const val DATA_FORMAT_R16G16B16_SSCALED: Long = 86L
        const val DATA_FORMAT_R16G16B16_UINT: Long = 87L
        const val DATA_FORMAT_R16G16B16_SINT: Long = 88L
        const val DATA_FORMAT_R16G16B16_SFLOAT: Long = 89L
        const val DATA_FORMAT_R16G16B16A16_UNORM: Long = 90L
        const val DATA_FORMAT_R16G16B16A16_SNORM: Long = 91L
        const val DATA_FORMAT_R16G16B16A16_USCALED: Long = 92L
        const val DATA_FORMAT_R16G16B16A16_SSCALED: Long = 93L
        const val DATA_FORMAT_R16G16B16A16_UINT: Long = 94L
        const val DATA_FORMAT_R16G16B16A16_SINT: Long = 95L
        const val DATA_FORMAT_R16G16B16A16_SFLOAT: Long = 96L
        const val DATA_FORMAT_R32_UINT: Long = 97L
        const val DATA_FORMAT_R32_SINT: Long = 98L
        const val DATA_FORMAT_R32_SFLOAT: Long = 99L
        const val DATA_FORMAT_R32G32_UINT: Long = 100L
        const val DATA_FORMAT_R32G32_SINT: Long = 101L
        const val DATA_FORMAT_R32G32_SFLOAT: Long = 102L
        const val DATA_FORMAT_R32G32B32_UINT: Long = 103L
        const val DATA_FORMAT_R32G32B32_SINT: Long = 104L
        const val DATA_FORMAT_R32G32B32_SFLOAT: Long = 105L
        const val DATA_FORMAT_R32G32B32A32_UINT: Long = 106L
        const val DATA_FORMAT_R32G32B32A32_SINT: Long = 107L
        const val DATA_FORMAT_R32G32B32A32_SFLOAT: Long = 108L
        const val DATA_FORMAT_R64_UINT: Long = 109L
        const val DATA_FORMAT_R64_SINT: Long = 110L
        const val DATA_FORMAT_R64_SFLOAT: Long = 111L
        const val DATA_FORMAT_R64G64_UINT: Long = 112L
        const val DATA_FORMAT_R64G64_SINT: Long = 113L
        const val DATA_FORMAT_R64G64_SFLOAT: Long = 114L
        const val DATA_FORMAT_R64G64B64_UINT: Long = 115L
        const val DATA_FORMAT_R64G64B64_SINT: Long = 116L
        const val DATA_FORMAT_R64G64B64_SFLOAT: Long = 117L
        const val DATA_FORMAT_R64G64B64A64_UINT: Long = 118L
        const val DATA_FORMAT_R64G64B64A64_SINT: Long = 119L
        const val DATA_FORMAT_R64G64B64A64_SFLOAT: Long = 120L
        const val DATA_FORMAT_B10G11R11_UFLOAT_PACK32: Long = 121L
        const val DATA_FORMAT_E5B9G9R9_UFLOAT_PACK32: Long = 122L
        const val DATA_FORMAT_D16_UNORM: Long = 123L
        const val DATA_FORMAT_X8_D24_UNORM_PACK32: Long = 124L
        const val DATA_FORMAT_D32_SFLOAT: Long = 125L
        const val DATA_FORMAT_S8_UINT: Long = 126L
        const val DATA_FORMAT_D16_UNORM_S8_UINT: Long = 127L
        const val DATA_FORMAT_D24_UNORM_S8_UINT: Long = 128L
        const val DATA_FORMAT_D32_SFLOAT_S8_UINT: Long = 129L
        const val DATA_FORMAT_BC1_RGB_UNORM_BLOCK: Long = 130L
        const val DATA_FORMAT_BC1_RGB_SRGB_BLOCK: Long = 131L
        const val DATA_FORMAT_BC1_RGBA_UNORM_BLOCK: Long = 132L
        const val DATA_FORMAT_BC1_RGBA_SRGB_BLOCK: Long = 133L
        const val DATA_FORMAT_BC2_UNORM_BLOCK: Long = 134L
        const val DATA_FORMAT_BC2_SRGB_BLOCK: Long = 135L
        const val DATA_FORMAT_BC3_UNORM_BLOCK: Long = 136L
        const val DATA_FORMAT_BC3_SRGB_BLOCK: Long = 137L
        const val DATA_FORMAT_BC4_UNORM_BLOCK: Long = 138L
        const val DATA_FORMAT_BC4_SNORM_BLOCK: Long = 139L
        const val DATA_FORMAT_BC5_UNORM_BLOCK: Long = 140L
        const val DATA_FORMAT_BC5_SNORM_BLOCK: Long = 141L
        const val DATA_FORMAT_BC6H_UFLOAT_BLOCK: Long = 142L
        const val DATA_FORMAT_BC6H_SFLOAT_BLOCK: Long = 143L
        const val DATA_FORMAT_BC7_UNORM_BLOCK: Long = 144L
        const val DATA_FORMAT_BC7_SRGB_BLOCK: Long = 145L
        const val DATA_FORMAT_ETC2_R8G8B8_UNORM_BLOCK: Long = 146L
        const val DATA_FORMAT_ETC2_R8G8B8_SRGB_BLOCK: Long = 147L
        const val DATA_FORMAT_ETC2_R8G8B8A1_UNORM_BLOCK: Long = 148L
        const val DATA_FORMAT_ETC2_R8G8B8A1_SRGB_BLOCK: Long = 149L
        const val DATA_FORMAT_ETC2_R8G8B8A8_UNORM_BLOCK: Long = 150L
        const val DATA_FORMAT_ETC2_R8G8B8A8_SRGB_BLOCK: Long = 151L
        const val DATA_FORMAT_EAC_R11_UNORM_BLOCK: Long = 152L
        const val DATA_FORMAT_EAC_R11_SNORM_BLOCK: Long = 153L
        const val DATA_FORMAT_EAC_R11G11_UNORM_BLOCK: Long = 154L
        const val DATA_FORMAT_EAC_R11G11_SNORM_BLOCK: Long = 155L
        const val DATA_FORMAT_ASTC_4x4_UNORM_BLOCK: Long = 156L
        const val DATA_FORMAT_ASTC_4x4_SRGB_BLOCK: Long = 157L
        const val DATA_FORMAT_ASTC_5x4_UNORM_BLOCK: Long = 158L
        const val DATA_FORMAT_ASTC_5x4_SRGB_BLOCK: Long = 159L
        const val DATA_FORMAT_ASTC_5x5_UNORM_BLOCK: Long = 160L
        const val DATA_FORMAT_ASTC_5x5_SRGB_BLOCK: Long = 161L
        const val DATA_FORMAT_ASTC_6x5_UNORM_BLOCK: Long = 162L
        const val DATA_FORMAT_ASTC_6x5_SRGB_BLOCK: Long = 163L
        const val DATA_FORMAT_ASTC_6x6_UNORM_BLOCK: Long = 164L
        const val DATA_FORMAT_ASTC_6x6_SRGB_BLOCK: Long = 165L
        const val DATA_FORMAT_ASTC_8x5_UNORM_BLOCK: Long = 166L
        const val DATA_FORMAT_ASTC_8x5_SRGB_BLOCK: Long = 167L
        const val DATA_FORMAT_ASTC_8x6_UNORM_BLOCK: Long = 168L
        const val DATA_FORMAT_ASTC_8x6_SRGB_BLOCK: Long = 169L
        const val DATA_FORMAT_ASTC_8x8_UNORM_BLOCK: Long = 170L
        const val DATA_FORMAT_ASTC_8x8_SRGB_BLOCK: Long = 171L
        const val DATA_FORMAT_ASTC_10x5_UNORM_BLOCK: Long = 172L
        const val DATA_FORMAT_ASTC_10x5_SRGB_BLOCK: Long = 173L
        const val DATA_FORMAT_ASTC_10x6_UNORM_BLOCK: Long = 174L
        const val DATA_FORMAT_ASTC_10x6_SRGB_BLOCK: Long = 175L
        const val DATA_FORMAT_ASTC_10x8_UNORM_BLOCK: Long = 176L
        const val DATA_FORMAT_ASTC_10x8_SRGB_BLOCK: Long = 177L
        const val DATA_FORMAT_ASTC_10x10_UNORM_BLOCK: Long = 178L
        const val DATA_FORMAT_ASTC_10x10_SRGB_BLOCK: Long = 179L
        const val DATA_FORMAT_ASTC_12x10_UNORM_BLOCK: Long = 180L
        const val DATA_FORMAT_ASTC_12x10_SRGB_BLOCK: Long = 181L
        const val DATA_FORMAT_ASTC_12x12_UNORM_BLOCK: Long = 182L
        const val DATA_FORMAT_ASTC_12x12_SRGB_BLOCK: Long = 183L
        const val DATA_FORMAT_G8B8G8R8_422_UNORM: Long = 184L
        const val DATA_FORMAT_B8G8R8G8_422_UNORM: Long = 185L
        const val DATA_FORMAT_G8_B8_R8_3PLANE_420_UNORM: Long = 186L
        const val DATA_FORMAT_G8_B8R8_2PLANE_420_UNORM: Long = 187L
        const val DATA_FORMAT_G8_B8_R8_3PLANE_422_UNORM: Long = 188L
        const val DATA_FORMAT_G8_B8R8_2PLANE_422_UNORM: Long = 189L
        const val DATA_FORMAT_G8_B8_R8_3PLANE_444_UNORM: Long = 190L
        const val DATA_FORMAT_R10X6_UNORM_PACK16: Long = 191L
        const val DATA_FORMAT_R10X6G10X6_UNORM_2PACK16: Long = 192L
        const val DATA_FORMAT_R10X6G10X6B10X6A10X6_UNORM_4PACK16: Long = 193L
        const val DATA_FORMAT_G10X6B10X6G10X6R10X6_422_UNORM_4PACK16: Long = 194L
        const val DATA_FORMAT_B10X6G10X6R10X6G10X6_422_UNORM_4PACK16: Long = 195L
        const val DATA_FORMAT_G10X6_B10X6_R10X6_3PLANE_420_UNORM_3PACK16: Long = 196L
        const val DATA_FORMAT_G10X6_B10X6R10X6_2PLANE_420_UNORM_3PACK16: Long = 197L
        const val DATA_FORMAT_G10X6_B10X6_R10X6_3PLANE_422_UNORM_3PACK16: Long = 198L
        const val DATA_FORMAT_G10X6_B10X6R10X6_2PLANE_422_UNORM_3PACK16: Long = 199L
        const val DATA_FORMAT_G10X6_B10X6_R10X6_3PLANE_444_UNORM_3PACK16: Long = 200L
        const val DATA_FORMAT_R12X4_UNORM_PACK16: Long = 201L
        const val DATA_FORMAT_R12X4G12X4_UNORM_2PACK16: Long = 202L
        const val DATA_FORMAT_R12X4G12X4B12X4A12X4_UNORM_4PACK16: Long = 203L
        const val DATA_FORMAT_G12X4B12X4G12X4R12X4_422_UNORM_4PACK16: Long = 204L
        const val DATA_FORMAT_B12X4G12X4R12X4G12X4_422_UNORM_4PACK16: Long = 205L
        const val DATA_FORMAT_G12X4_B12X4_R12X4_3PLANE_420_UNORM_3PACK16: Long = 206L
        const val DATA_FORMAT_G12X4_B12X4R12X4_2PLANE_420_UNORM_3PACK16: Long = 207L
        const val DATA_FORMAT_G12X4_B12X4_R12X4_3PLANE_422_UNORM_3PACK16: Long = 208L
        const val DATA_FORMAT_G12X4_B12X4R12X4_2PLANE_422_UNORM_3PACK16: Long = 209L
        const val DATA_FORMAT_G12X4_B12X4_R12X4_3PLANE_444_UNORM_3PACK16: Long = 210L
        const val DATA_FORMAT_G16B16G16R16_422_UNORM: Long = 211L
        const val DATA_FORMAT_B16G16R16G16_422_UNORM: Long = 212L
        const val DATA_FORMAT_G16_B16_R16_3PLANE_420_UNORM: Long = 213L
        const val DATA_FORMAT_G16_B16R16_2PLANE_420_UNORM: Long = 214L
        const val DATA_FORMAT_G16_B16_R16_3PLANE_422_UNORM: Long = 215L
        const val DATA_FORMAT_G16_B16R16_2PLANE_422_UNORM: Long = 216L
        const val DATA_FORMAT_G16_B16_R16_3PLANE_444_UNORM: Long = 217L
        const val DATA_FORMAT_ASTC_4x4_SFLOAT_BLOCK: Long = 218L
        const val DATA_FORMAT_ASTC_5x4_SFLOAT_BLOCK: Long = 219L
        const val DATA_FORMAT_ASTC_5x5_SFLOAT_BLOCK: Long = 220L
        const val DATA_FORMAT_ASTC_6x5_SFLOAT_BLOCK: Long = 221L
        const val DATA_FORMAT_ASTC_6x6_SFLOAT_BLOCK: Long = 222L
        const val DATA_FORMAT_ASTC_8x5_SFLOAT_BLOCK: Long = 223L
        const val DATA_FORMAT_ASTC_8x6_SFLOAT_BLOCK: Long = 224L
        const val DATA_FORMAT_ASTC_8x8_SFLOAT_BLOCK: Long = 225L
        const val DATA_FORMAT_ASTC_10x5_SFLOAT_BLOCK: Long = 226L
        const val DATA_FORMAT_ASTC_10x6_SFLOAT_BLOCK: Long = 227L
        const val DATA_FORMAT_ASTC_10x8_SFLOAT_BLOCK: Long = 228L
        const val DATA_FORMAT_ASTC_10x10_SFLOAT_BLOCK: Long = 229L
        const val DATA_FORMAT_ASTC_12x10_SFLOAT_BLOCK: Long = 230L
        const val DATA_FORMAT_ASTC_12x12_SFLOAT_BLOCK: Long = 231L
        const val DATA_FORMAT_MAX: Long = 232L
        const val BARRIER_MASK_VERTEX: Long = 1L
        const val BARRIER_MASK_FRAGMENT: Long = 8L
        const val BARRIER_MASK_COMPUTE: Long = 2L
        const val BARRIER_MASK_TRANSFER: Long = 4L
        const val BARRIER_MASK_RASTER: Long = 9L
        const val BARRIER_MASK_ALL_BARRIERS: Long = 32767L
        const val BARRIER_MASK_NO_BARRIER: Long = 32768L
        const val TEXTURE_TYPE_1D: Long = 0L
        const val TEXTURE_TYPE_2D: Long = 1L
        const val TEXTURE_TYPE_3D: Long = 2L
        const val TEXTURE_TYPE_CUBE: Long = 3L
        const val TEXTURE_TYPE_1D_ARRAY: Long = 4L
        const val TEXTURE_TYPE_2D_ARRAY: Long = 5L
        const val TEXTURE_TYPE_CUBE_ARRAY: Long = 6L
        const val TEXTURE_TYPE_MAX: Long = 7L
        const val TEXTURE_SAMPLES_1: Long = 0L
        const val TEXTURE_SAMPLES_2: Long = 1L
        const val TEXTURE_SAMPLES_4: Long = 2L
        const val TEXTURE_SAMPLES_8: Long = 3L
        const val TEXTURE_SAMPLES_16: Long = 4L
        const val TEXTURE_SAMPLES_32: Long = 5L
        const val TEXTURE_SAMPLES_64: Long = 6L
        const val TEXTURE_SAMPLES_MAX: Long = 7L
        const val TEXTURE_USAGE_SAMPLING_BIT: Long = 1L
        const val TEXTURE_USAGE_COLOR_ATTACHMENT_BIT: Long = 2L
        const val TEXTURE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT: Long = 4L
        const val TEXTURE_USAGE_DEPTH_RESOLVE_ATTACHMENT_BIT: Long = 4096L
        const val TEXTURE_USAGE_STORAGE_BIT: Long = 8L
        const val TEXTURE_USAGE_STORAGE_ATOMIC_BIT: Long = 16L
        const val TEXTURE_USAGE_CPU_READ_BIT: Long = 32L
        const val TEXTURE_USAGE_CAN_UPDATE_BIT: Long = 64L
        const val TEXTURE_USAGE_CAN_COPY_FROM_BIT: Long = 128L
        const val TEXTURE_USAGE_CAN_COPY_TO_BIT: Long = 256L
        const val TEXTURE_USAGE_INPUT_ATTACHMENT_BIT: Long = 512L
        const val TEXTURE_SWIZZLE_IDENTITY: Long = 0L
        const val TEXTURE_SWIZZLE_ZERO: Long = 1L
        const val TEXTURE_SWIZZLE_ONE: Long = 2L
        const val TEXTURE_SWIZZLE_R: Long = 3L
        const val TEXTURE_SWIZZLE_G: Long = 4L
        const val TEXTURE_SWIZZLE_B: Long = 5L
        const val TEXTURE_SWIZZLE_A: Long = 6L
        const val TEXTURE_SWIZZLE_MAX: Long = 7L
        const val TEXTURE_SLICE_2D: Long = 0L
        const val TEXTURE_SLICE_CUBEMAP: Long = 1L
        const val TEXTURE_SLICE_3D: Long = 2L
        const val SAMPLER_FILTER_NEAREST: Long = 0L
        const val SAMPLER_FILTER_LINEAR: Long = 1L
        const val SAMPLER_REPEAT_MODE_REPEAT: Long = 0L
        const val SAMPLER_REPEAT_MODE_MIRRORED_REPEAT: Long = 1L
        const val SAMPLER_REPEAT_MODE_CLAMP_TO_EDGE: Long = 2L
        const val SAMPLER_REPEAT_MODE_CLAMP_TO_BORDER: Long = 3L
        const val SAMPLER_REPEAT_MODE_MIRROR_CLAMP_TO_EDGE: Long = 4L
        const val SAMPLER_REPEAT_MODE_MAX: Long = 5L
        const val SAMPLER_BORDER_COLOR_FLOAT_TRANSPARENT_BLACK: Long = 0L
        const val SAMPLER_BORDER_COLOR_INT_TRANSPARENT_BLACK: Long = 1L
        const val SAMPLER_BORDER_COLOR_FLOAT_OPAQUE_BLACK: Long = 2L
        const val SAMPLER_BORDER_COLOR_INT_OPAQUE_BLACK: Long = 3L
        const val SAMPLER_BORDER_COLOR_FLOAT_OPAQUE_WHITE: Long = 4L
        const val SAMPLER_BORDER_COLOR_INT_OPAQUE_WHITE: Long = 5L
        const val SAMPLER_BORDER_COLOR_MAX: Long = 6L
        const val VERTEX_FREQUENCY_VERTEX: Long = 0L
        const val VERTEX_FREQUENCY_INSTANCE: Long = 1L
        const val INDEX_BUFFER_FORMAT_UINT16: Long = 0L
        const val INDEX_BUFFER_FORMAT_UINT32: Long = 1L
        const val STORAGE_BUFFER_USAGE_DISPATCH_INDIRECT: Long = 1L
        const val BUFFER_CREATION_DEVICE_ADDRESS_BIT: Long = 1L
        const val BUFFER_CREATION_AS_STORAGE_BIT: Long = 2L
        const val BUFFER_CREATION_ACCELERATION_STRUCTURE_BUILD_INPUT_READ_ONLY_BIT: Long = 8L
        const val ACCELERATION_STRUCTURE_ALLOW_UPDATE_BIT: Long = 1L
        const val ACCELERATION_STRUCTURE_ALLOW_COMPACTION_BIT: Long = 2L
        const val ACCELERATION_STRUCTURE_PREFER_FAST_TRACE_BIT: Long = 4L
        const val ACCELERATION_STRUCTURE_PREFER_FAST_BUILD_BIT: Long = 8L
        const val ACCELERATION_STRUCTURE_LOW_MEMORY_BIT: Long = 16L
        const val ACCELERATION_STRUCTURE_GEOMETRY_OPAQUE_BIT: Long = 1L
        const val ACCELERATION_STRUCTURE_GEOMETRY_NO_DUPLICATE_ANY_HIT_INVOCATION_BIT: Long = 2L
        const val ACCELERATION_STRUCTURE_INSTANCE_TRIANGLE_FACING_CULL_DISABLE_BIT: Long = 1L
        const val ACCELERATION_STRUCTURE_INSTANCE_TRIANGLE_FLIP_FACING_BIT: Long = 2L
        const val ACCELERATION_STRUCTURE_INSTANCE_FORCE_OPAQUE_BIT: Long = 4L
        const val ACCELERATION_STRUCTURE_INSTANCE_FORCE_NO_OPAQUE_BIT: Long = 8L
        const val UNIFORM_TYPE_SAMPLER: Long = 0L
        const val UNIFORM_TYPE_SAMPLER_WITH_TEXTURE: Long = 1L
        const val UNIFORM_TYPE_TEXTURE: Long = 2L
        const val UNIFORM_TYPE_IMAGE: Long = 3L
        const val UNIFORM_TYPE_TEXTURE_BUFFER: Long = 4L
        const val UNIFORM_TYPE_SAMPLER_WITH_TEXTURE_BUFFER: Long = 5L
        const val UNIFORM_TYPE_IMAGE_BUFFER: Long = 6L
        const val UNIFORM_TYPE_UNIFORM_BUFFER: Long = 7L
        const val UNIFORM_TYPE_STORAGE_BUFFER: Long = 8L
        const val UNIFORM_TYPE_INPUT_ATTACHMENT: Long = 9L
        const val UNIFORM_TYPE_UNIFORM_BUFFER_DYNAMIC: Long = 10L
        const val UNIFORM_TYPE_STORAGE_BUFFER_DYNAMIC: Long = 11L
        const val UNIFORM_TYPE_ACCELERATION_STRUCTURE: Long = 12L
        const val UNIFORM_TYPE_MAX: Long = 13L
        const val RENDER_PRIMITIVE_POINTS: Long = 0L
        const val RENDER_PRIMITIVE_LINES: Long = 1L
        const val RENDER_PRIMITIVE_LINES_WITH_ADJACENCY: Long = 2L
        const val RENDER_PRIMITIVE_LINESTRIPS: Long = 3L
        const val RENDER_PRIMITIVE_LINESTRIPS_WITH_ADJACENCY: Long = 4L
        const val RENDER_PRIMITIVE_TRIANGLES: Long = 5L
        const val RENDER_PRIMITIVE_TRIANGLES_WITH_ADJACENCY: Long = 6L
        const val RENDER_PRIMITIVE_TRIANGLE_STRIPS: Long = 7L
        const val RENDER_PRIMITIVE_TRIANGLE_STRIPS_WITH_AJACENCY: Long = 8L
        const val RENDER_PRIMITIVE_TRIANGLE_STRIPS_WITH_RESTART_INDEX: Long = 9L
        const val RENDER_PRIMITIVE_TESSELATION_PATCH: Long = 10L
        const val RENDER_PRIMITIVE_MAX: Long = 11L
        const val POLYGON_CULL_DISABLED: Long = 0L
        const val POLYGON_CULL_FRONT: Long = 1L
        const val POLYGON_CULL_BACK: Long = 2L
        const val POLYGON_FRONT_FACE_CLOCKWISE: Long = 0L
        const val POLYGON_FRONT_FACE_COUNTER_CLOCKWISE: Long = 1L
        const val STENCIL_OP_KEEP: Long = 0L
        const val STENCIL_OP_ZERO: Long = 1L
        const val STENCIL_OP_REPLACE: Long = 2L
        const val STENCIL_OP_INCREMENT_AND_CLAMP: Long = 3L
        const val STENCIL_OP_DECREMENT_AND_CLAMP: Long = 4L
        const val STENCIL_OP_INVERT: Long = 5L
        const val STENCIL_OP_INCREMENT_AND_WRAP: Long = 6L
        const val STENCIL_OP_DECREMENT_AND_WRAP: Long = 7L
        const val STENCIL_OP_MAX: Long = 8L
        const val COMPARE_OP_NEVER: Long = 0L
        const val COMPARE_OP_LESS: Long = 1L
        const val COMPARE_OP_EQUAL: Long = 2L
        const val COMPARE_OP_LESS_OR_EQUAL: Long = 3L
        const val COMPARE_OP_GREATER: Long = 4L
        const val COMPARE_OP_NOT_EQUAL: Long = 5L
        const val COMPARE_OP_GREATER_OR_EQUAL: Long = 6L
        const val COMPARE_OP_ALWAYS: Long = 7L
        const val COMPARE_OP_MAX: Long = 8L
        const val LOGIC_OP_CLEAR: Long = 0L
        const val LOGIC_OP_AND: Long = 1L
        const val LOGIC_OP_AND_REVERSE: Long = 2L
        const val LOGIC_OP_COPY: Long = 3L
        const val LOGIC_OP_AND_INVERTED: Long = 4L
        const val LOGIC_OP_NO_OP: Long = 5L
        const val LOGIC_OP_XOR: Long = 6L
        const val LOGIC_OP_OR: Long = 7L
        const val LOGIC_OP_NOR: Long = 8L
        const val LOGIC_OP_EQUIVALENT: Long = 9L
        const val LOGIC_OP_INVERT: Long = 10L
        const val LOGIC_OP_OR_REVERSE: Long = 11L
        const val LOGIC_OP_COPY_INVERTED: Long = 12L
        const val LOGIC_OP_OR_INVERTED: Long = 13L
        const val LOGIC_OP_NAND: Long = 14L
        const val LOGIC_OP_SET: Long = 15L
        const val LOGIC_OP_MAX: Long = 16L
        const val BLEND_FACTOR_ZERO: Long = 0L
        const val BLEND_FACTOR_ONE: Long = 1L
        const val BLEND_FACTOR_SRC_COLOR: Long = 2L
        const val BLEND_FACTOR_ONE_MINUS_SRC_COLOR: Long = 3L
        const val BLEND_FACTOR_DST_COLOR: Long = 4L
        const val BLEND_FACTOR_ONE_MINUS_DST_COLOR: Long = 5L
        const val BLEND_FACTOR_SRC_ALPHA: Long = 6L
        const val BLEND_FACTOR_ONE_MINUS_SRC_ALPHA: Long = 7L
        const val BLEND_FACTOR_DST_ALPHA: Long = 8L
        const val BLEND_FACTOR_ONE_MINUS_DST_ALPHA: Long = 9L
        const val BLEND_FACTOR_CONSTANT_COLOR: Long = 10L
        const val BLEND_FACTOR_ONE_MINUS_CONSTANT_COLOR: Long = 11L
        const val BLEND_FACTOR_CONSTANT_ALPHA: Long = 12L
        const val BLEND_FACTOR_ONE_MINUS_CONSTANT_ALPHA: Long = 13L
        const val BLEND_FACTOR_SRC_ALPHA_SATURATE: Long = 14L
        const val BLEND_FACTOR_SRC1_COLOR: Long = 15L
        const val BLEND_FACTOR_ONE_MINUS_SRC1_COLOR: Long = 16L
        const val BLEND_FACTOR_SRC1_ALPHA: Long = 17L
        const val BLEND_FACTOR_ONE_MINUS_SRC1_ALPHA: Long = 18L
        const val BLEND_FACTOR_MAX: Long = 19L
        const val BLEND_OP_ADD: Long = 0L
        const val BLEND_OP_SUBTRACT: Long = 1L
        const val BLEND_OP_REVERSE_SUBTRACT: Long = 2L
        const val BLEND_OP_MINIMUM: Long = 3L
        const val BLEND_OP_MAXIMUM: Long = 4L
        const val BLEND_OP_MAX: Long = 5L
        const val DYNAMIC_STATE_LINE_WIDTH: Long = 1L
        const val DYNAMIC_STATE_DEPTH_BIAS: Long = 2L
        const val DYNAMIC_STATE_BLEND_CONSTANTS: Long = 4L
        const val DYNAMIC_STATE_DEPTH_BOUNDS: Long = 8L
        const val DYNAMIC_STATE_STENCIL_COMPARE_MASK: Long = 16L
        const val DYNAMIC_STATE_STENCIL_WRITE_MASK: Long = 32L
        const val DYNAMIC_STATE_STENCIL_REFERENCE: Long = 64L
        const val INITIAL_ACTION_LOAD: Long = 0L
        const val INITIAL_ACTION_CLEAR: Long = 1L
        const val INITIAL_ACTION_DISCARD: Long = 2L
        const val INITIAL_ACTION_MAX: Long = 3L
        const val INITIAL_ACTION_CLEAR_REGION: Long = 1L
        const val INITIAL_ACTION_CLEAR_REGION_CONTINUE: Long = 1L
        const val INITIAL_ACTION_KEEP: Long = 0L
        const val INITIAL_ACTION_DROP: Long = 2L
        const val INITIAL_ACTION_CONTINUE: Long = 0L
        const val FINAL_ACTION_STORE: Long = 0L
        const val FINAL_ACTION_DISCARD: Long = 1L
        const val FINAL_ACTION_MAX: Long = 2L
        const val FINAL_ACTION_READ: Long = 0L
        const val FINAL_ACTION_CONTINUE: Long = 0L
        const val SHADER_STAGE_VERTEX: Long = 0L
        const val SHADER_STAGE_FRAGMENT: Long = 1L
        const val SHADER_STAGE_TESSELATION_CONTROL: Long = 2L
        const val SHADER_STAGE_TESSELATION_EVALUATION: Long = 3L
        const val SHADER_STAGE_COMPUTE: Long = 4L
        const val SHADER_STAGE_RAYGEN: Long = 5L
        const val SHADER_STAGE_ANY_HIT: Long = 6L
        const val SHADER_STAGE_CLOSEST_HIT: Long = 7L
        const val SHADER_STAGE_MISS: Long = 8L
        const val SHADER_STAGE_INTERSECTION: Long = 9L
        const val SHADER_STAGE_MAX: Long = 10L
        const val SHADER_STAGE_VERTEX_BIT: Long = 1L
        const val SHADER_STAGE_FRAGMENT_BIT: Long = 2L
        const val SHADER_STAGE_TESSELATION_CONTROL_BIT: Long = 4L
        const val SHADER_STAGE_TESSELATION_EVALUATION_BIT: Long = 8L
        const val SHADER_STAGE_COMPUTE_BIT: Long = 16L
        const val SHADER_STAGE_RAYGEN_BIT: Long = 32L
        const val SHADER_STAGE_ANY_HIT_BIT: Long = 64L
        const val SHADER_STAGE_CLOSEST_HIT_BIT: Long = 128L
        const val SHADER_STAGE_MISS_BIT: Long = 256L
        const val SHADER_STAGE_INTERSECTION_BIT: Long = 512L
        const val SHADER_LANGUAGE_GLSL: Long = 0L
        const val SHADER_LANGUAGE_HLSL: Long = 1L
        const val PIPELINE_SPECIALIZATION_CONSTANT_TYPE_BOOL: Long = 0L
        const val PIPELINE_SPECIALIZATION_CONSTANT_TYPE_INT: Long = 1L
        const val PIPELINE_SPECIALIZATION_CONSTANT_TYPE_FLOAT: Long = 2L
        const val SUPPORTS_METALFX_SPATIAL: Long = 3L
        const val SUPPORTS_METALFX_TEMPORAL: Long = 4L
        const val SUPPORTS_BUFFER_DEVICE_ADDRESS: Long = 6L
        const val SUPPORTS_IMAGE_ATOMIC_32_BIT: Long = 7L
        const val SUPPORTS_RAY_QUERY: Long = 11L
        const val SUPPORTS_RAYTRACING_PIPELINE: Long = 12L
        const val SUPPORTS_HDR_OUTPUT: Long = 13L
        const val LIMIT_MAX_BOUND_UNIFORM_SETS: Long = 0L
        const val LIMIT_MAX_FRAMEBUFFER_COLOR_ATTACHMENTS: Long = 1L
        const val LIMIT_MAX_TEXTURES_PER_UNIFORM_SET: Long = 2L
        const val LIMIT_MAX_SAMPLERS_PER_UNIFORM_SET: Long = 3L
        const val LIMIT_MAX_STORAGE_BUFFERS_PER_UNIFORM_SET: Long = 4L
        const val LIMIT_MAX_STORAGE_IMAGES_PER_UNIFORM_SET: Long = 5L
        const val LIMIT_MAX_UNIFORM_BUFFERS_PER_UNIFORM_SET: Long = 6L
        const val LIMIT_MAX_DRAW_INDEXED_INDEX: Long = 7L
        const val LIMIT_MAX_FRAMEBUFFER_HEIGHT: Long = 8L
        const val LIMIT_MAX_FRAMEBUFFER_WIDTH: Long = 9L
        const val LIMIT_MAX_TEXTURE_ARRAY_LAYERS: Long = 10L
        const val LIMIT_MAX_TEXTURE_SIZE_1D: Long = 11L
        const val LIMIT_MAX_TEXTURE_SIZE_2D: Long = 12L
        const val LIMIT_MAX_TEXTURE_SIZE_3D: Long = 13L
        const val LIMIT_MAX_TEXTURE_SIZE_CUBE: Long = 14L
        const val LIMIT_MAX_TEXTURES_PER_SHADER_STAGE: Long = 15L
        const val LIMIT_MAX_SAMPLERS_PER_SHADER_STAGE: Long = 16L
        const val LIMIT_MAX_STORAGE_BUFFERS_PER_SHADER_STAGE: Long = 17L
        const val LIMIT_MAX_STORAGE_IMAGES_PER_SHADER_STAGE: Long = 18L
        const val LIMIT_MAX_UNIFORM_BUFFERS_PER_SHADER_STAGE: Long = 19L
        const val LIMIT_MAX_PUSH_CONSTANT_SIZE: Long = 20L
        const val LIMIT_MAX_UNIFORM_BUFFER_SIZE: Long = 21L
        const val LIMIT_MAX_VERTEX_INPUT_ATTRIBUTE_OFFSET: Long = 22L
        const val LIMIT_MAX_VERTEX_INPUT_ATTRIBUTES: Long = 23L
        const val LIMIT_MAX_VERTEX_INPUT_BINDINGS: Long = 24L
        const val LIMIT_MAX_VERTEX_INPUT_BINDING_STRIDE: Long = 25L
        const val LIMIT_MIN_UNIFORM_BUFFER_OFFSET_ALIGNMENT: Long = 26L
        const val LIMIT_MAX_COMPUTE_SHARED_MEMORY_SIZE: Long = 27L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_COUNT_X: Long = 28L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_COUNT_Y: Long = 29L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_COUNT_Z: Long = 30L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_INVOCATIONS: Long = 31L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_SIZE_X: Long = 32L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_SIZE_Y: Long = 33L
        const val LIMIT_MAX_COMPUTE_WORKGROUP_SIZE_Z: Long = 34L
        const val LIMIT_MAX_VIEWPORT_DIMENSIONS_X: Long = 35L
        const val LIMIT_MAX_VIEWPORT_DIMENSIONS_Y: Long = 36L
        const val LIMIT_METALFX_TEMPORAL_SCALER_MIN_SCALE: Long = 46L
        const val LIMIT_METALFX_TEMPORAL_SCALER_MAX_SCALE: Long = 47L
        const val MEMORY_TEXTURES: Long = 0L
        const val MEMORY_BUFFERS: Long = 1L
        const val MEMORY_TOTAL: Long = 2L
        const val NONE: Long = 0L
        const val REFLECTION_PROBES: Long = 65536L
        const val SKY_PASS: Long = 131072L
        const val LIGHTMAPPER_PASS: Long = 196608L
        const val SHADOW_PASS_DIRECTIONAL: Long = 262144L
        const val SHADOW_PASS_CUBE: Long = 327680L
        const val OPAQUE_PASS: Long = 393216L
        const val ALPHA_PASS: Long = 458752L
        const val TRANSPARENT_PASS: Long = 524288L
        const val POST_PROCESSING_PASS: Long = 589824L
        const val BLIT_PASS: Long = 655360L
        const val UI_PASS: Long = 720896L
        const val DEBUG_PASS: Long = 786432L
        const val DRAW_DEFAULT_ALL: Long = 0L
        const val DRAW_CLEAR_COLOR_0: Long = 1L
        const val DRAW_CLEAR_COLOR_1: Long = 2L
        const val DRAW_CLEAR_COLOR_2: Long = 4L
        const val DRAW_CLEAR_COLOR_3: Long = 8L
        const val DRAW_CLEAR_COLOR_4: Long = 16L
        const val DRAW_CLEAR_COLOR_5: Long = 32L
        const val DRAW_CLEAR_COLOR_6: Long = 64L
        const val DRAW_CLEAR_COLOR_7: Long = 128L
        const val DRAW_CLEAR_COLOR_MASK: Long = 255L
        const val DRAW_CLEAR_COLOR_ALL: Long = 255L
        const val DRAW_IGNORE_COLOR_0: Long = 256L
        const val DRAW_IGNORE_COLOR_1: Long = 512L
        const val DRAW_IGNORE_COLOR_2: Long = 1024L
        const val DRAW_IGNORE_COLOR_3: Long = 2048L
        const val DRAW_IGNORE_COLOR_4: Long = 4096L
        const val DRAW_IGNORE_COLOR_5: Long = 8192L
        const val DRAW_IGNORE_COLOR_6: Long = 16384L
        const val DRAW_IGNORE_COLOR_7: Long = 32768L
        const val DRAW_IGNORE_COLOR_MASK: Long = 65280L
        const val DRAW_IGNORE_COLOR_ALL: Long = 65280L
        const val DRAW_CLEAR_DEPTH: Long = 65536L
        const val DRAW_IGNORE_DEPTH: Long = 131072L
        const val DRAW_CLEAR_STENCIL: Long = 262144L
        const val DRAW_IGNORE_STENCIL: Long = 524288L
        const val DRAW_CLEAR_ALL: Long = 327935L
        const val DRAW_IGNORE_ALL: Long = 720640L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderingDevice? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderingDevice? =
            if (handle.address() == 0L) null else RenderingDevice(handle)

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

        private const val RAYTRACING_PIPELINE_IS_VALID_HASH = 3521089500L
        private val raytracingPipelineIsValidBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "raytracing_pipeline_is_valid", RAYTRACING_PIPELINE_IS_VALID_HASH)
        }

        private const val TLAS_CREATE_HASH = 592780330L
        private val tlasCreateBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "tlas_create", TLAS_CREATE_HASH)
        }

        private const val BLAS_BUILD_HASH = 813180755L
        private val blasBuildBind by lazy {
            ObjectCalls.getMethodBind("RenderingDevice", "blas_build", BLAS_BUILD_HASH)
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

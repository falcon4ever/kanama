package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: Viewport
 */
open class Viewport(handle: MemorySegment) : Node(handle) {
    var disable3d: Boolean
        @JvmName("disable3dProperty")
        get() = is3dDisabled()
        @JvmName("setDisable3dProperty")
        set(value) = setDisable3d(value)

    var useXr: Boolean
        @JvmName("useXrProperty")
        get() = isUsingXr()
        @JvmName("setUseXrProperty")
        set(value) = setUseXr(value)

    var ownWorld3d: Boolean
        @JvmName("ownWorld3dProperty")
        get() = isUsingOwnWorld3d()
        @JvmName("setOwnWorld3dProperty")
        set(value) = setUseOwnWorld3d(value)

    var world3d: World3D?
        @JvmName("world3dProperty")
        get() = getWorld3d()
        @JvmName("setWorld3dProperty")
        set(value) = setWorld3d(value)

    var world2d: World2D?
        @JvmName("world2dProperty")
        get() = getWorld2d()
        @JvmName("setWorld2dProperty")
        set(value) = setWorld2d(value)

    var transparentBg: Boolean
        @JvmName("transparentBgProperty")
        get() = hasTransparentBackground()
        @JvmName("setTransparentBgProperty")
        set(value) = setTransparentBackground(value)

    var handleInputLocally: Boolean
        @JvmName("handleInputLocallyProperty")
        get() = isHandlingInputLocally()
        @JvmName("setHandleInputLocallyProperty")
        set(value) = setHandleInputLocally(value)

    var snap2dTransformsToPixel: Boolean
        @JvmName("snap2dTransformsToPixelProperty")
        get() = isSnap2dTransformsToPixelEnabled()
        @JvmName("setSnap2dTransformsToPixelProperty")
        set(value) = setSnap2dTransformsToPixel(value)

    var snap2dVerticesToPixel: Boolean
        @JvmName("snap2dVerticesToPixelProperty")
        get() = isSnap2dVerticesToPixelEnabled()
        @JvmName("setSnap2dVerticesToPixelProperty")
        set(value) = setSnap2dVerticesToPixel(value)

    var msaa2d: Long
        @JvmName("msaa2dProperty")
        get() = getMsaa2d()
        @JvmName("setMsaa2dProperty")
        set(value) = setMsaa2d(value)

    var msaa3d: Long
        @JvmName("msaa3dProperty")
        get() = getMsaa3d()
        @JvmName("setMsaa3dProperty")
        set(value) = setMsaa3d(value)

    var screenSpaceAa: Long
        @JvmName("screenSpaceAaProperty")
        get() = getScreenSpaceAa()
        @JvmName("setScreenSpaceAaProperty")
        set(value) = setScreenSpaceAa(value)

    var useTaa: Boolean
        @JvmName("useTaaProperty")
        get() = isUsingTaa()
        @JvmName("setUseTaaProperty")
        set(value) = setUseTaa(value)

    var useDebanding: Boolean
        @JvmName("useDebandingProperty")
        get() = isUsingDebanding()
        @JvmName("setUseDebandingProperty")
        set(value) = setUseDebanding(value)

    var useOcclusionCulling: Boolean
        @JvmName("useOcclusionCullingProperty")
        get() = isUsingOcclusionCulling()
        @JvmName("setUseOcclusionCullingProperty")
        set(value) = setUseOcclusionCulling(value)

    var meshLodThreshold: Double
        @JvmName("meshLodThresholdProperty")
        get() = getMeshLodThreshold()
        @JvmName("setMeshLodThresholdProperty")
        set(value) = setMeshLodThreshold(value)

    var debugDraw: Long
        @JvmName("debugDrawProperty")
        get() = getDebugDraw()
        @JvmName("setDebugDrawProperty")
        set(value) = setDebugDraw(value)

    var useHdr2d: Boolean
        @JvmName("useHdr2dProperty")
        get() = isUsingHdr2d()
        @JvmName("setUseHdr2dProperty")
        set(value) = setUseHdr2d(value)

    var scaling3dMode: Long
        @JvmName("scaling3dModeProperty")
        get() = getScaling3dMode()
        @JvmName("setScaling3dModeProperty")
        set(value) = setScaling3dMode(value)

    var scaling3dScale: Double
        @JvmName("scaling3dScaleProperty")
        get() = getScaling3dScale()
        @JvmName("setScaling3dScaleProperty")
        set(value) = setScaling3dScale(value)

    var textureMipmapBias: Double
        @JvmName("textureMipmapBiasProperty")
        get() = getTextureMipmapBias()
        @JvmName("setTextureMipmapBiasProperty")
        set(value) = setTextureMipmapBias(value)

    var anisotropicFilteringLevel: Long
        @JvmName("anisotropicFilteringLevelProperty")
        get() = getAnisotropicFilteringLevel()
        @JvmName("setAnisotropicFilteringLevelProperty")
        set(value) = setAnisotropicFilteringLevel(value)

    var fsrSharpness: Double
        @JvmName("fsrSharpnessProperty")
        get() = getFsrSharpness()
        @JvmName("setFsrSharpnessProperty")
        set(value) = setFsrSharpness(value)

    var vrsMode: Long
        @JvmName("vrsModeProperty")
        get() = getVrsMode()
        @JvmName("setVrsModeProperty")
        set(value) = setVrsMode(value)

    var vrsUpdateMode: Long
        @JvmName("vrsUpdateModeProperty")
        get() = getVrsUpdateMode()
        @JvmName("setVrsUpdateModeProperty")
        set(value) = setVrsUpdateMode(value)

    var vrsTexture: Texture2D?
        @JvmName("vrsTextureProperty")
        get() = getVrsTexture()
        @JvmName("setVrsTextureProperty")
        set(value) = setVrsTexture(value)

    var canvasItemDefaultTextureFilter: Long
        @JvmName("canvasItemDefaultTextureFilterProperty")
        get() = getDefaultCanvasItemTextureFilter()
        @JvmName("setCanvasItemDefaultTextureFilterProperty")
        set(value) = setDefaultCanvasItemTextureFilter(value)

    var canvasItemDefaultTextureRepeat: Long
        @JvmName("canvasItemDefaultTextureRepeatProperty")
        get() = getDefaultCanvasItemTextureRepeat()
        @JvmName("setCanvasItemDefaultTextureRepeatProperty")
        set(value) = setDefaultCanvasItemTextureRepeat(value)

    var audioListenerEnable2d: Boolean
        @JvmName("audioListenerEnable2dProperty")
        get() = isAudioListener2d()
        @JvmName("setAudioListenerEnable2dProperty")
        set(value) = setAsAudioListener2d(value)

    var audioListenerEnable3d: Boolean
        @JvmName("audioListenerEnable3dProperty")
        get() = isAudioListener3d()
        @JvmName("setAudioListenerEnable3dProperty")
        set(value) = setAsAudioListener3d(value)

    var physicsObjectPicking: Boolean
        @JvmName("physicsObjectPickingProperty")
        get() = getPhysicsObjectPicking()
        @JvmName("setPhysicsObjectPickingProperty")
        set(value) = setPhysicsObjectPicking(value)

    var physicsObjectPickingSort: Boolean
        @JvmName("physicsObjectPickingSortProperty")
        get() = getPhysicsObjectPickingSort()
        @JvmName("setPhysicsObjectPickingSortProperty")
        set(value) = setPhysicsObjectPickingSort(value)

    var physicsObjectPickingFirstOnly: Boolean
        @JvmName("physicsObjectPickingFirstOnlyProperty")
        get() = getPhysicsObjectPickingFirstOnly()
        @JvmName("setPhysicsObjectPickingFirstOnlyProperty")
        set(value) = setPhysicsObjectPickingFirstOnly(value)

    var guiDisableInput: Boolean
        @JvmName("guiDisableInputProperty")
        get() = isInputDisabled()
        @JvmName("setGuiDisableInputProperty")
        set(value) = setDisableInput(value)

    var guiSnapControlsToPixels: Boolean
        @JvmName("guiSnapControlsToPixelsProperty")
        get() = isSnapControlsToPixelsEnabled()
        @JvmName("setGuiSnapControlsToPixelsProperty")
        set(value) = setSnapControlsToPixels(value)

    var guiEmbedSubwindows: Boolean
        @JvmName("guiEmbedSubwindowsProperty")
        get() = isEmbeddingSubwindows()
        @JvmName("setGuiEmbedSubwindowsProperty")
        set(value) = setEmbeddingSubwindows(value)

    var guiDragThreshold: Int
        @JvmName("guiDragThresholdProperty")
        get() = getDragThreshold()
        @JvmName("setGuiDragThresholdProperty")
        set(value) = setDragThreshold(value)

    var sdfOversize: Long
        @JvmName("sdfOversizeProperty")
        get() = getSdfOversize()
        @JvmName("setSdfOversizeProperty")
        set(value) = setSdfOversize(value)

    var sdfScale: Long
        @JvmName("sdfScaleProperty")
        get() = getSdfScale()
        @JvmName("setSdfScaleProperty")
        set(value) = setSdfScale(value)

    var positionalShadowAtlasSize: Int
        @JvmName("positionalShadowAtlasSizeProperty")
        get() = getPositionalShadowAtlasSize()
        @JvmName("setPositionalShadowAtlasSizeProperty")
        set(value) = setPositionalShadowAtlasSize(value)

    var positionalShadowAtlas16Bits: Boolean
        @JvmName("positionalShadowAtlas16BitsProperty")
        get() = getPositionalShadowAtlas16Bits()
        @JvmName("setPositionalShadowAtlas16BitsProperty")
        set(value) = setPositionalShadowAtlas16Bits(value)

    var positionalShadowAtlasQuad0: Long
        @JvmName("positionalShadowAtlasQuad0Property")
        get() = getPositionalShadowAtlasQuadrantSubdiv(0)
        @JvmName("setPositionalShadowAtlasQuad0Property")
        set(value) = setPositionalShadowAtlasQuadrantSubdiv(0, value)

    var positionalShadowAtlasQuad1: Long
        @JvmName("positionalShadowAtlasQuad1Property")
        get() = getPositionalShadowAtlasQuadrantSubdiv(1)
        @JvmName("setPositionalShadowAtlasQuad1Property")
        set(value) = setPositionalShadowAtlasQuadrantSubdiv(1, value)

    var positionalShadowAtlasQuad2: Long
        @JvmName("positionalShadowAtlasQuad2Property")
        get() = getPositionalShadowAtlasQuadrantSubdiv(2)
        @JvmName("setPositionalShadowAtlasQuad2Property")
        set(value) = setPositionalShadowAtlasQuadrantSubdiv(2, value)

    var positionalShadowAtlasQuad3: Long
        @JvmName("positionalShadowAtlasQuad3Property")
        get() = getPositionalShadowAtlasQuadrantSubdiv(3)
        @JvmName("setPositionalShadowAtlasQuad3Property")
        set(value) = setPositionalShadowAtlasQuadrantSubdiv(3, value)

    var canvasTransform: Transform2D
        @JvmName("canvasTransformProperty")
        get() = getCanvasTransform()
        @JvmName("setCanvasTransformProperty")
        set(value) = setCanvasTransform(value)

    var globalCanvasTransform: Transform2D
        @JvmName("globalCanvasTransformProperty")
        get() = getGlobalCanvasTransform()
        @JvmName("setGlobalCanvasTransformProperty")
        set(value) = setGlobalCanvasTransform(value)

    var canvasCullMask: Long
        @JvmName("canvasCullMaskProperty")
        get() = getCanvasCullMask()
        @JvmName("setCanvasCullMaskProperty")
        set(value) = setCanvasCullMask(value)

    var oversampling: Boolean
        @JvmName("oversamplingProperty")
        get() = isUsingOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setUseOversampling(value)

    var oversamplingOverride: Double
        @JvmName("oversamplingOverrideProperty")
        get() = getOversamplingOverride()
        @JvmName("setOversamplingOverrideProperty")
        set(value) = setOversamplingOverride(value)

    fun setWorld2d(world2d: World2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setWorld2dBind, handle, listOf(world2d?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getWorld2d(): World2D? {
        return World2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld2dBind, handle))
    }

    fun findWorld2d(): World2D? {
        return World2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(findWorld2dBind, handle))
    }

    fun setCanvasTransform(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setCanvasTransformBind, handle, xform)
    }

    fun getCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getCanvasTransformBind, handle)
    }

    fun setGlobalCanvasTransform(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setGlobalCanvasTransformBind, handle, xform)
    }

    fun getGlobalCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getGlobalCanvasTransformBind, handle)
    }

    fun getStretchTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getStretchTransformBind, handle)
    }

    fun getFinalTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getFinalTransformBind, handle)
    }

    fun getScreenTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getScreenTransformBind, handle)
    }

    fun getVisibleRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getVisibleRectBind, handle)
    }

    fun setTransparentBackground(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransparentBackgroundBind, handle, enable)
    }

    fun hasTransparentBackground(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasTransparentBackgroundBind, handle)
    }

    fun setUseHdr2d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseHdr2dBind, handle, enable)
    }

    fun isUsingHdr2d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingHdr2dBind, handle)
    }

    fun setMsaa2d(msaa: Long) {
        ObjectCalls.ptrcallWithLongArg(setMsaa2dBind, handle, msaa)
    }

    fun getMsaa2d(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMsaa2dBind, handle)
    }

    fun setMsaa3d(msaa: Long) {
        ObjectCalls.ptrcallWithLongArg(setMsaa3dBind, handle, msaa)
    }

    fun getMsaa3d(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMsaa3dBind, handle)
    }

    fun setScreenSpaceAa(screenSpaceAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setScreenSpaceAaBind, handle, screenSpaceAa)
    }

    fun getScreenSpaceAa(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScreenSpaceAaBind, handle)
    }

    fun setUseTaa(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTaaBind, handle, enable)
    }

    fun isUsingTaa(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTaaBind, handle)
    }

    fun setUseDebanding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDebandingBind, handle, enable)
    }

    fun isUsingDebanding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingDebandingBind, handle)
    }

    fun setUseOcclusionCulling(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseOcclusionCullingBind, handle, enable)
    }

    fun isUsingOcclusionCulling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingOcclusionCullingBind, handle)
    }

    fun setDebugDraw(debugDraw: Long) {
        ObjectCalls.ptrcallWithLongArg(setDebugDrawBind, handle, debugDraw)
    }

    fun getDebugDraw(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDebugDrawBind, handle)
    }

    fun setUseOversampling(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseOversamplingBind, handle, enable)
    }

    fun isUsingOversampling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingOversamplingBind, handle)
    }

    fun setOversamplingOverride(oversampling: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOversamplingOverrideBind, handle, oversampling)
    }

    fun getOversamplingOverride(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversamplingOverrideBind, handle)
    }

    fun getOversampling(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversamplingBind, handle)
    }

    fun getRenderInfo(type: Long, info: Long): Int {
        return ObjectCalls.ptrcallWithTwoLongArgsRetInt(getRenderInfoBind, handle, type, info)
    }

    fun getTexture(): ViewportTexture? {
        return ViewportTexture.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setPhysicsObjectPicking(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsObjectPickingBind, handle, enable)
    }

    fun getPhysicsObjectPicking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPhysicsObjectPickingBind, handle)
    }

    fun setPhysicsObjectPickingSort(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsObjectPickingSortBind, handle, enable)
    }

    fun getPhysicsObjectPickingSort(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPhysicsObjectPickingSortBind, handle)
    }

    fun setPhysicsObjectPickingFirstOnly(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsObjectPickingFirstOnlyBind, handle, enable)
    }

    fun getPhysicsObjectPickingFirstOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPhysicsObjectPickingFirstOnlyBind, handle)
    }

    fun getViewportRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getViewportRidBind, handle)
    }

    fun pushTextInput(text: String) {
        ObjectCalls.ptrcallWithStringArg(pushTextInputBind, handle, text)
    }

    fun pushInput(event: InputEvent, inLocalCoords: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(pushInputBind, handle, event.requireOpenHandle(), inLocalCoords)
    }

    fun pushUnhandledInput(event: InputEvent, inLocalCoords: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(pushUnhandledInputBind, handle, event.requireOpenHandle(), inLocalCoords)
    }

    fun notifyMouseEntered() {
        ObjectCalls.ptrcallNoArgs(notifyMouseEnteredBind, handle)
    }

    fun notifyMouseExited() {
        ObjectCalls.ptrcallNoArgs(notifyMouseExitedBind, handle)
    }

    fun getMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMousePositionBind, handle)
    }

    fun warpMouse(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(warpMouseBind, handle, position)
    }

    fun updateMouseCursorState() {
        ObjectCalls.ptrcallNoArgs(updateMouseCursorStateBind, handle)
    }

    fun guiCancelDrag() {
        ObjectCalls.ptrcallNoArgs(guiCancelDragBind, handle)
    }

    fun guiGetDragData(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(guiGetDragDataBind, handle)
    }

    fun guiGetDragDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(guiGetDragDescriptionBind, handle)
    }

    fun guiSetDragDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(guiSetDragDescriptionBind, handle, description)
    }

    fun guiIsDragging(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(guiIsDraggingBind, handle)
    }

    fun guiIsDragSuccessful(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(guiIsDragSuccessfulBind, handle)
    }

    fun guiReleaseFocus() {
        ObjectCalls.ptrcallNoArgs(guiReleaseFocusBind, handle)
    }

    fun guiGetFocusOwner(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(guiGetFocusOwnerBind, handle))
    }

    fun guiGetHoveredControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(guiGetHoveredControlBind, handle))
    }

    fun setDisableInput(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableInputBind, handle, disable)
    }

    fun isInputDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInputDisabledBind, handle)
    }

    fun setPositionalShadowAtlasSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setPositionalShadowAtlasSizeBind, handle, size)
    }

    fun getPositionalShadowAtlasSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPositionalShadowAtlasSizeBind, handle)
    }

    fun setPositionalShadowAtlas16Bits(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPositionalShadowAtlas16BitsBind, handle, enable)
    }

    fun getPositionalShadowAtlas16Bits(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPositionalShadowAtlas16BitsBind, handle)
    }

    fun setSnapControlsToPixels(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSnapControlsToPixelsBind, handle, enabled)
    }

    fun isSnapControlsToPixelsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSnapControlsToPixelsEnabledBind, handle)
    }

    fun setSnap2dTransformsToPixel(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSnap2dTransformsToPixelBind, handle, enabled)
    }

    fun isSnap2dTransformsToPixelEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSnap2dTransformsToPixelEnabledBind, handle)
    }

    fun setSnap2dVerticesToPixel(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSnap2dVerticesToPixelBind, handle, enabled)
    }

    fun isSnap2dVerticesToPixelEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSnap2dVerticesToPixelEnabledBind, handle)
    }

    fun setPositionalShadowAtlasQuadrantSubdiv(quadrant: Int, subdiv: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setPositionalShadowAtlasQuadrantSubdivBind, handle, quadrant, subdiv)
    }

    fun getPositionalShadowAtlasQuadrantSubdiv(quadrant: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getPositionalShadowAtlasQuadrantSubdivBind, handle, quadrant)
    }

    fun setInputAsHandled() {
        ObjectCalls.ptrcallNoArgs(setInputAsHandledBind, handle)
    }

    fun isInputHandled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInputHandledBind, handle)
    }

    fun setHandleInputLocally(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHandleInputLocallyBind, handle, enable)
    }

    fun isHandlingInputLocally(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHandlingInputLocallyBind, handle)
    }

    fun setDefaultCanvasItemTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCanvasItemTextureFilterBind, handle, mode)
    }

    fun getDefaultCanvasItemTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultCanvasItemTextureFilterBind, handle)
    }

    fun setEmbeddingSubwindows(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmbeddingSubwindowsBind, handle, enable)
    }

    fun isEmbeddingSubwindows(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmbeddingSubwindowsBind, handle)
    }

    fun getEmbeddedSubwindows(): List<Window> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getEmbeddedSubwindowsBind, handle, Window::fromHandle)
    }

    fun setDragThreshold(threshold: Int) {
        ObjectCalls.ptrcallWithIntArg(setDragThresholdBind, handle, threshold)
    }

    fun getDragThreshold(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDragThresholdBind, handle)
    }

    fun setCanvasCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCanvasCullMaskBind, handle, mask)
    }

    fun getCanvasCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCanvasCullMaskBind, handle)
    }

    fun setCanvasCullMaskBit(layer: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(setCanvasCullMaskBitBind, handle, layer, enable)
    }

    fun getCanvasCullMaskBit(layer: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(getCanvasCullMaskBitBind, handle, layer)
    }

    fun setDefaultCanvasItemTextureRepeat(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCanvasItemTextureRepeatBind, handle, mode)
    }

    fun getDefaultCanvasItemTextureRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultCanvasItemTextureRepeatBind, handle)
    }

    fun setSdfOversize(oversize: Long) {
        ObjectCalls.ptrcallWithLongArg(setSdfOversizeBind, handle, oversize)
    }

    fun getSdfOversize(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSdfOversizeBind, handle)
    }

    fun setSdfScale(scale: Long) {
        ObjectCalls.ptrcallWithLongArg(setSdfScaleBind, handle, scale)
    }

    fun getSdfScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSdfScaleBind, handle)
    }

    fun setMeshLodThreshold(pixels: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMeshLodThresholdBind, handle, pixels)
    }

    fun getMeshLodThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMeshLodThresholdBind, handle)
    }

    fun setAsAudioListener2d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsAudioListener2dBind, handle, enable)
    }

    fun isAudioListener2d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAudioListener2dBind, handle)
    }

    fun getAudioListener2d(): AudioListener2D? {
        return AudioListener2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAudioListener2dBind, handle))
    }

    fun getCamera2d(): Camera2D? {
        return Camera2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCamera2dBind, handle))
    }

    fun setWorld3d(world3d: World3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setWorld3dBind, handle, listOf(world3d?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getWorld3d(): World3D? {
        return World3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld3dBind, handle))
    }

    fun findWorld3d(): World3D? {
        return World3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(findWorld3dBind, handle))
    }

    fun setUseOwnWorld3d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseOwnWorld3dBind, handle, enable)
    }

    fun isUsingOwnWorld3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingOwnWorld3dBind, handle)
    }

    fun getAudioListener3d(): AudioListener3D? {
        return AudioListener3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAudioListener3dBind, handle))
    }

    fun getCamera3d(): Camera3D? {
        return Camera3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCamera3dBind, handle))
    }

    fun setAsAudioListener3d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsAudioListener3dBind, handle, enable)
    }

    fun isAudioListener3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAudioListener3dBind, handle)
    }

    fun setDisable3d(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisable3dBind, handle, disable)
    }

    fun is3dDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(is3dDisabledBind, handle)
    }

    fun setUseXr(use: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseXrBind, handle, use)
    }

    fun isUsingXr(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingXrBind, handle)
    }

    fun setScaling3dMode(scaling3dMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setScaling3dModeBind, handle, scaling3dMode)
    }

    fun getScaling3dMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScaling3dModeBind, handle)
    }

    fun setScaling3dScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setScaling3dScaleBind, handle, scale)
    }

    fun getScaling3dScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getScaling3dScaleBind, handle)
    }

    fun setFsrSharpness(fsrSharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFsrSharpnessBind, handle, fsrSharpness)
    }

    fun getFsrSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFsrSharpnessBind, handle)
    }

    fun setTextureMipmapBias(textureMipmapBias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureMipmapBiasBind, handle, textureMipmapBias)
    }

    fun getTextureMipmapBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTextureMipmapBiasBind, handle)
    }

    fun setAnisotropicFilteringLevel(anisotropicFilteringLevel: Long) {
        ObjectCalls.ptrcallWithLongArg(setAnisotropicFilteringLevelBind, handle, anisotropicFilteringLevel)
    }

    fun getAnisotropicFilteringLevel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAnisotropicFilteringLevelBind, handle)
    }

    fun setVrsMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVrsModeBind, handle, mode)
    }

    fun getVrsMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVrsModeBind, handle)
    }

    fun setVrsUpdateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVrsUpdateModeBind, handle, mode)
    }

    fun getVrsUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVrsUpdateModeBind, handle)
    }

    fun setVrsTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setVrsTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getVrsTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVrsTextureBind, handle))
    }

    // getCamera3D/getCamera2D camelCase aliases (the generator emits getCamera3d/getCamera2d);
    // match the Android aliases so shared demo code resolves on both backends.
    fun getCamera3D(): Camera3D? = getCamera3d()

    fun getCamera2D(): Camera2D? = getCamera2d()

    object Signals {
        const val sizeChanged: String = "size_changed"
        const val guiFocusChanged: String = "gui_focus_changed"
    }

    companion object {
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_DISABLED: Long = 0L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_1: Long = 1L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_4: Long = 2L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_16: Long = 3L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_64: Long = 4L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_256: Long = 5L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_1024: Long = 6L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_MAX: Long = 7L
        const val SCALING_3D_MODE_BILINEAR: Long = 0L
        const val SCALING_3D_MODE_FSR: Long = 1L
        const val SCALING_3D_MODE_FSR2: Long = 2L
        const val SCALING_3D_MODE_METALFX_SPATIAL: Long = 3L
        const val SCALING_3D_MODE_METALFX_TEMPORAL: Long = 4L
        const val SCALING_3D_MODE_NEAREST: Long = 5L
        const val SCALING_3D_MODE_MAX: Long = 6L
        const val MSAA_DISABLED: Long = 0L
        const val MSAA_2X: Long = 1L
        const val MSAA_4X: Long = 2L
        const val MSAA_8X: Long = 3L
        const val MSAA_MAX: Long = 4L
        const val ANISOTROPY_DISABLED: Long = 0L
        const val ANISOTROPY_2X: Long = 1L
        const val ANISOTROPY_4X: Long = 2L
        const val ANISOTROPY_8X: Long = 3L
        const val ANISOTROPY_16X: Long = 4L
        const val ANISOTROPY_MAX: Long = 5L
        const val SCREEN_SPACE_AA_DISABLED: Long = 0L
        const val SCREEN_SPACE_AA_FXAA: Long = 1L
        const val SCREEN_SPACE_AA_SMAA: Long = 2L
        const val SCREEN_SPACE_AA_MAX: Long = 3L
        const val RENDER_INFO_OBJECTS_IN_FRAME: Long = 0L
        const val RENDER_INFO_PRIMITIVES_IN_FRAME: Long = 1L
        const val RENDER_INFO_DRAW_CALLS_IN_FRAME: Long = 2L
        const val RENDER_INFO_MAX: Long = 3L
        const val RENDER_INFO_TYPE_VISIBLE: Long = 0L
        const val RENDER_INFO_TYPE_SHADOW: Long = 1L
        const val RENDER_INFO_TYPE_CANVAS: Long = 2L
        const val RENDER_INFO_TYPE_MAX: Long = 3L
        const val DEBUG_DRAW_DISABLED: Long = 0L
        const val DEBUG_DRAW_UNSHADED: Long = 1L
        const val DEBUG_DRAW_LIGHTING: Long = 2L
        const val DEBUG_DRAW_OVERDRAW: Long = 3L
        const val DEBUG_DRAW_WIREFRAME: Long = 4L
        const val DEBUG_DRAW_NORMAL_BUFFER: Long = 5L
        const val DEBUG_DRAW_VOXEL_GI_ALBEDO: Long = 6L
        const val DEBUG_DRAW_VOXEL_GI_LIGHTING: Long = 7L
        const val DEBUG_DRAW_VOXEL_GI_EMISSION: Long = 8L
        const val DEBUG_DRAW_SHADOW_ATLAS: Long = 9L
        const val DEBUG_DRAW_DIRECTIONAL_SHADOW_ATLAS: Long = 10L
        const val DEBUG_DRAW_SCENE_LUMINANCE: Long = 11L
        const val DEBUG_DRAW_SSAO: Long = 12L
        const val DEBUG_DRAW_SSIL: Long = 13L
        const val DEBUG_DRAW_PSSM_SPLITS: Long = 14L
        const val DEBUG_DRAW_DECAL_ATLAS: Long = 15L
        const val DEBUG_DRAW_SDFGI: Long = 16L
        const val DEBUG_DRAW_SDFGI_PROBES: Long = 17L
        const val DEBUG_DRAW_GI_BUFFER: Long = 18L
        const val DEBUG_DRAW_DISABLE_LOD: Long = 19L
        const val DEBUG_DRAW_CLUSTER_OMNI_LIGHTS: Long = 20L
        const val DEBUG_DRAW_CLUSTER_SPOT_LIGHTS: Long = 21L
        const val DEBUG_DRAW_CLUSTER_DECALS: Long = 22L
        const val DEBUG_DRAW_CLUSTER_REFLECTION_PROBES: Long = 23L
        const val DEBUG_DRAW_OCCLUDERS: Long = 24L
        const val DEBUG_DRAW_MOTION_VECTORS: Long = 25L
        const val DEBUG_DRAW_INTERNAL_BUFFER: Long = 26L
        const val DEBUG_DRAW_CLUSTER_AREA_LIGHTS: Long = 27L
        const val DEBUG_DRAW_AREA_LIGHT_ATLAS: Long = 28L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_NEAREST: Long = 0L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_LINEAR: Long = 1L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS: Long = 2L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS: Long = 3L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_PARENT_NODE: Long = 4L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_MAX: Long = 5L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_DISABLED: Long = 0L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_ENABLED: Long = 1L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_MIRROR: Long = 2L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_PARENT_NODE: Long = 3L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_MAX: Long = 4L
        const val SDF_OVERSIZE_100_PERCENT: Long = 0L
        const val SDF_OVERSIZE_120_PERCENT: Long = 1L
        const val SDF_OVERSIZE_150_PERCENT: Long = 2L
        const val SDF_OVERSIZE_200_PERCENT: Long = 3L
        const val SDF_OVERSIZE_MAX: Long = 4L
        const val SDF_SCALE_100_PERCENT: Long = 0L
        const val SDF_SCALE_50_PERCENT: Long = 1L
        const val SDF_SCALE_25_PERCENT: Long = 2L
        const val SDF_SCALE_MAX: Long = 3L
        const val VRS_DISABLED: Long = 0L
        const val VRS_TEXTURE: Long = 1L
        const val VRS_XR: Long = 2L
        const val VRS_MAX: Long = 3L
        const val VRS_UPDATE_DISABLED: Long = 0L
        const val VRS_UPDATE_ONCE: Long = 1L
        const val VRS_UPDATE_ALWAYS: Long = 2L
        const val VRS_UPDATE_MAX: Long = 3L

        fun fromHandle(handle: MemorySegment): Viewport? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Viewport? =
            if (handle.address() == 0L) null else Viewport(handle)

        private const val SET_WORLD_2D_HASH = 2736080068L
        private val setWorld2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_world_2d", SET_WORLD_2D_HASH)
        }

        private const val GET_WORLD_2D_HASH = 2339128592L
        private val getWorld2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_world_2d", GET_WORLD_2D_HASH)
        }

        private const val FIND_WORLD_2D_HASH = 2339128592L
        private val findWorld2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "find_world_2d", FIND_WORLD_2D_HASH)
        }

        private const val SET_CANVAS_TRANSFORM_HASH = 2761652528L
        private val setCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_canvas_transform", SET_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_CANVAS_TRANSFORM_HASH = 3814499831L
        private val getCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_canvas_transform", GET_CANVAS_TRANSFORM_HASH)
        }

        private const val SET_GLOBAL_CANVAS_TRANSFORM_HASH = 2761652528L
        private val setGlobalCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_global_canvas_transform", SET_GLOBAL_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_CANVAS_TRANSFORM_HASH = 3814499831L
        private val getGlobalCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_global_canvas_transform", GET_GLOBAL_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_STRETCH_TRANSFORM_HASH = 3814499831L
        private val getStretchTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_stretch_transform", GET_STRETCH_TRANSFORM_HASH)
        }

        private const val GET_FINAL_TRANSFORM_HASH = 3814499831L
        private val getFinalTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_final_transform", GET_FINAL_TRANSFORM_HASH)
        }

        private const val GET_SCREEN_TRANSFORM_HASH = 3814499831L
        private val getScreenTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_screen_transform", GET_SCREEN_TRANSFORM_HASH)
        }

        private const val GET_VISIBLE_RECT_HASH = 1639390495L
        private val getVisibleRectBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_visible_rect", GET_VISIBLE_RECT_HASH)
        }

        private const val SET_TRANSPARENT_BACKGROUND_HASH = 2586408642L
        private val setTransparentBackgroundBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_transparent_background", SET_TRANSPARENT_BACKGROUND_HASH)
        }

        private const val HAS_TRANSPARENT_BACKGROUND_HASH = 36873697L
        private val hasTransparentBackgroundBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "has_transparent_background", HAS_TRANSPARENT_BACKGROUND_HASH)
        }

        private const val SET_USE_HDR_2D_HASH = 2586408642L
        private val setUseHdr2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_hdr_2d", SET_USE_HDR_2D_HASH)
        }

        private const val IS_USING_HDR_2D_HASH = 36873697L
        private val isUsingHdr2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_hdr_2d", IS_USING_HDR_2D_HASH)
        }

        private const val SET_MSAA_2D_HASH = 3330258708L
        private val setMsaa2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_msaa_2d", SET_MSAA_2D_HASH)
        }

        private const val GET_MSAA_2D_HASH = 2542055527L
        private val getMsaa2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_msaa_2d", GET_MSAA_2D_HASH)
        }

        private const val SET_MSAA_3D_HASH = 3330258708L
        private val setMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_msaa_3d", SET_MSAA_3D_HASH)
        }

        private const val GET_MSAA_3D_HASH = 2542055527L
        private val getMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_msaa_3d", GET_MSAA_3D_HASH)
        }

        private const val SET_SCREEN_SPACE_AA_HASH = 3544169389L
        private val setScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_screen_space_aa", SET_SCREEN_SPACE_AA_HASH)
        }

        private const val GET_SCREEN_SPACE_AA_HASH = 1390814124L
        private val getScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_screen_space_aa", GET_SCREEN_SPACE_AA_HASH)
        }

        private const val SET_USE_TAA_HASH = 2586408642L
        private val setUseTaaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_taa", SET_USE_TAA_HASH)
        }

        private const val IS_USING_TAA_HASH = 36873697L
        private val isUsingTaaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_taa", IS_USING_TAA_HASH)
        }

        private const val SET_USE_DEBANDING_HASH = 2586408642L
        private val setUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_debanding", SET_USE_DEBANDING_HASH)
        }

        private const val IS_USING_DEBANDING_HASH = 36873697L
        private val isUsingDebandingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_debanding", IS_USING_DEBANDING_HASH)
        }

        private const val SET_USE_OCCLUSION_CULLING_HASH = 2586408642L
        private val setUseOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_occlusion_culling", SET_USE_OCCLUSION_CULLING_HASH)
        }

        private const val IS_USING_OCCLUSION_CULLING_HASH = 36873697L
        private val isUsingOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_occlusion_culling", IS_USING_OCCLUSION_CULLING_HASH)
        }

        private const val SET_DEBUG_DRAW_HASH = 1970246205L
        private val setDebugDrawBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_debug_draw", SET_DEBUG_DRAW_HASH)
        }

        private const val GET_DEBUG_DRAW_HASH = 579191299L
        private val getDebugDrawBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_debug_draw", GET_DEBUG_DRAW_HASH)
        }

        private const val SET_USE_OVERSAMPLING_HASH = 2586408642L
        private val setUseOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_oversampling", SET_USE_OVERSAMPLING_HASH)
        }

        private const val IS_USING_OVERSAMPLING_HASH = 36873697L
        private val isUsingOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_oversampling", IS_USING_OVERSAMPLING_HASH)
        }

        private const val SET_OVERSAMPLING_OVERRIDE_HASH = 373806689L
        private val setOversamplingOverrideBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_oversampling_override", SET_OVERSAMPLING_OVERRIDE_HASH)
        }

        private const val GET_OVERSAMPLING_OVERRIDE_HASH = 1740695150L
        private val getOversamplingOverrideBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_oversampling_override", GET_OVERSAMPLING_OVERRIDE_HASH)
        }

        private const val GET_OVERSAMPLING_HASH = 1740695150L
        private val getOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_oversampling", GET_OVERSAMPLING_HASH)
        }

        private const val GET_RENDER_INFO_HASH = 481977019L
        private val getRenderInfoBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_render_info", GET_RENDER_INFO_HASH)
        }

        private const val GET_TEXTURE_HASH = 1746695840L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_PHYSICS_OBJECT_PICKING_HASH = 2586408642L
        private val setPhysicsObjectPickingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_physics_object_picking", SET_PHYSICS_OBJECT_PICKING_HASH)
        }

        private const val GET_PHYSICS_OBJECT_PICKING_HASH = 2240911060L
        private val getPhysicsObjectPickingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_physics_object_picking", GET_PHYSICS_OBJECT_PICKING_HASH)
        }

        private const val SET_PHYSICS_OBJECT_PICKING_SORT_HASH = 2586408642L
        private val setPhysicsObjectPickingSortBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_physics_object_picking_sort", SET_PHYSICS_OBJECT_PICKING_SORT_HASH)
        }

        private const val GET_PHYSICS_OBJECT_PICKING_SORT_HASH = 2240911060L
        private val getPhysicsObjectPickingSortBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_physics_object_picking_sort", GET_PHYSICS_OBJECT_PICKING_SORT_HASH)
        }

        private const val SET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH = 2586408642L
        private val setPhysicsObjectPickingFirstOnlyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_physics_object_picking_first_only", SET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH)
        }

        private const val GET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH = 2240911060L
        private val getPhysicsObjectPickingFirstOnlyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_physics_object_picking_first_only", GET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH)
        }

        private const val GET_VIEWPORT_RID_HASH = 2944877500L
        private val getViewportRidBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_viewport_rid", GET_VIEWPORT_RID_HASH)
        }

        private const val PUSH_TEXT_INPUT_HASH = 83702148L
        private val pushTextInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "push_text_input", PUSH_TEXT_INPUT_HASH)
        }

        private const val PUSH_INPUT_HASH = 3644664830L
        private val pushInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "push_input", PUSH_INPUT_HASH)
        }

        private const val PUSH_UNHANDLED_INPUT_HASH = 3644664830L
        private val pushUnhandledInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "push_unhandled_input", PUSH_UNHANDLED_INPUT_HASH)
        }

        private const val NOTIFY_MOUSE_ENTERED_HASH = 3218959716L
        private val notifyMouseEnteredBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "notify_mouse_entered", NOTIFY_MOUSE_ENTERED_HASH)
        }

        private const val NOTIFY_MOUSE_EXITED_HASH = 3218959716L
        private val notifyMouseExitedBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "notify_mouse_exited", NOTIFY_MOUSE_EXITED_HASH)
        }

        private const val GET_MOUSE_POSITION_HASH = 3341600327L
        private val getMousePositionBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_mouse_position", GET_MOUSE_POSITION_HASH)
        }

        private const val WARP_MOUSE_HASH = 743155724L
        private val warpMouseBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "warp_mouse", WARP_MOUSE_HASH)
        }

        private const val UPDATE_MOUSE_CURSOR_STATE_HASH = 3218959716L
        private val updateMouseCursorStateBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "update_mouse_cursor_state", UPDATE_MOUSE_CURSOR_STATE_HASH)
        }

        private const val GUI_CANCEL_DRAG_HASH = 3218959716L
        private val guiCancelDragBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_cancel_drag", GUI_CANCEL_DRAG_HASH)
        }

        private const val GUI_GET_DRAG_DATA_HASH = 1214101251L
        private val guiGetDragDataBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_drag_data", GUI_GET_DRAG_DATA_HASH)
        }

        private const val GUI_GET_DRAG_DESCRIPTION_HASH = 201670096L
        private val guiGetDragDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_drag_description", GUI_GET_DRAG_DESCRIPTION_HASH)
        }

        private const val GUI_SET_DRAG_DESCRIPTION_HASH = 83702148L
        private val guiSetDragDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_set_drag_description", GUI_SET_DRAG_DESCRIPTION_HASH)
        }

        private const val GUI_IS_DRAGGING_HASH = 36873697L
        private val guiIsDraggingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_is_dragging", GUI_IS_DRAGGING_HASH)
        }

        private const val GUI_IS_DRAG_SUCCESSFUL_HASH = 36873697L
        private val guiIsDragSuccessfulBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_is_drag_successful", GUI_IS_DRAG_SUCCESSFUL_HASH)
        }

        private const val GUI_RELEASE_FOCUS_HASH = 3218959716L
        private val guiReleaseFocusBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_release_focus", GUI_RELEASE_FOCUS_HASH)
        }

        private const val GUI_GET_FOCUS_OWNER_HASH = 2783021301L
        private val guiGetFocusOwnerBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_focus_owner", GUI_GET_FOCUS_OWNER_HASH)
        }

        private const val GUI_GET_HOVERED_CONTROL_HASH = 2783021301L
        private val guiGetHoveredControlBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_hovered_control", GUI_GET_HOVERED_CONTROL_HASH)
        }

        private const val SET_DISABLE_INPUT_HASH = 2586408642L
        private val setDisableInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_disable_input", SET_DISABLE_INPUT_HASH)
        }

        private const val IS_INPUT_DISABLED_HASH = 36873697L
        private val isInputDisabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_input_disabled", IS_INPUT_DISABLED_HASH)
        }

        private const val SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH = 1286410249L
        private val setPositionalShadowAtlasSizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_positional_shadow_atlas_size", SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH)
        }

        private const val GET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH = 3905245786L
        private val getPositionalShadowAtlasSizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_positional_shadow_atlas_size", GET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH)
        }

        private const val SET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH = 2586408642L
        private val setPositionalShadowAtlas16BitsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_positional_shadow_atlas_16_bits", SET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH)
        }

        private const val GET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH = 36873697L
        private val getPositionalShadowAtlas16BitsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_positional_shadow_atlas_16_bits", GET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH)
        }

        private const val SET_SNAP_CONTROLS_TO_PIXELS_HASH = 2586408642L
        private val setSnapControlsToPixelsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_snap_controls_to_pixels", SET_SNAP_CONTROLS_TO_PIXELS_HASH)
        }

        private const val IS_SNAP_CONTROLS_TO_PIXELS_ENABLED_HASH = 36873697L
        private val isSnapControlsToPixelsEnabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_snap_controls_to_pixels_enabled", IS_SNAP_CONTROLS_TO_PIXELS_ENABLED_HASH)
        }

        private const val SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH = 2586408642L
        private val setSnap2dTransformsToPixelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_snap_2d_transforms_to_pixel", SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH)
        }

        private const val IS_SNAP_2D_TRANSFORMS_TO_PIXEL_ENABLED_HASH = 36873697L
        private val isSnap2dTransformsToPixelEnabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_snap_2d_transforms_to_pixel_enabled", IS_SNAP_2D_TRANSFORMS_TO_PIXEL_ENABLED_HASH)
        }

        private const val SET_SNAP_2D_VERTICES_TO_PIXEL_HASH = 2586408642L
        private val setSnap2dVerticesToPixelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_snap_2d_vertices_to_pixel", SET_SNAP_2D_VERTICES_TO_PIXEL_HASH)
        }

        private const val IS_SNAP_2D_VERTICES_TO_PIXEL_ENABLED_HASH = 36873697L
        private val isSnap2dVerticesToPixelEnabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_snap_2d_vertices_to_pixel_enabled", IS_SNAP_2D_VERTICES_TO_PIXEL_ENABLED_HASH)
        }

        private const val SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH = 2596956071L
        private val setPositionalShadowAtlasQuadrantSubdivBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_positional_shadow_atlas_quadrant_subdiv", SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH)
        }

        private const val GET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH = 2676778355L
        private val getPositionalShadowAtlasQuadrantSubdivBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_positional_shadow_atlas_quadrant_subdiv", GET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH)
        }

        private const val SET_INPUT_AS_HANDLED_HASH = 3218959716L
        private val setInputAsHandledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_input_as_handled", SET_INPUT_AS_HANDLED_HASH)
        }

        private const val IS_INPUT_HANDLED_HASH = 36873697L
        private val isInputHandledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_input_handled", IS_INPUT_HANDLED_HASH)
        }

        private const val SET_HANDLE_INPUT_LOCALLY_HASH = 2586408642L
        private val setHandleInputLocallyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_handle_input_locally", SET_HANDLE_INPUT_LOCALLY_HASH)
        }

        private const val IS_HANDLING_INPUT_LOCALLY_HASH = 36873697L
        private val isHandlingInputLocallyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_handling_input_locally", IS_HANDLING_INPUT_LOCALLY_HASH)
        }

        private const val SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH = 2815160100L
        private val setDefaultCanvasItemTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_default_canvas_item_texture_filter", SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH)
        }

        private const val GET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH = 896601198L
        private val getDefaultCanvasItemTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_default_canvas_item_texture_filter", GET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH)
        }

        private const val SET_EMBEDDING_SUBWINDOWS_HASH = 2586408642L
        private val setEmbeddingSubwindowsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_embedding_subwindows", SET_EMBEDDING_SUBWINDOWS_HASH)
        }

        private const val IS_EMBEDDING_SUBWINDOWS_HASH = 36873697L
        private val isEmbeddingSubwindowsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_embedding_subwindows", IS_EMBEDDING_SUBWINDOWS_HASH)
        }

        private const val GET_EMBEDDED_SUBWINDOWS_HASH = 3995934104L
        private val getEmbeddedSubwindowsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_embedded_subwindows", GET_EMBEDDED_SUBWINDOWS_HASH)
        }

        private const val SET_DRAG_THRESHOLD_HASH = 1286410249L
        private val setDragThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_drag_threshold", SET_DRAG_THRESHOLD_HASH)
        }

        private const val GET_DRAG_THRESHOLD_HASH = 3905245786L
        private val getDragThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_drag_threshold", GET_DRAG_THRESHOLD_HASH)
        }

        private const val SET_CANVAS_CULL_MASK_HASH = 1286410249L
        private val setCanvasCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_canvas_cull_mask", SET_CANVAS_CULL_MASK_HASH)
        }

        private const val GET_CANVAS_CULL_MASK_HASH = 3905245786L
        private val getCanvasCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_canvas_cull_mask", GET_CANVAS_CULL_MASK_HASH)
        }

        private const val SET_CANVAS_CULL_MASK_BIT_HASH = 300928843L
        private val setCanvasCullMaskBitBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_canvas_cull_mask_bit", SET_CANVAS_CULL_MASK_BIT_HASH)
        }

        private const val GET_CANVAS_CULL_MASK_BIT_HASH = 1116898809L
        private val getCanvasCullMaskBitBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_canvas_cull_mask_bit", GET_CANVAS_CULL_MASK_BIT_HASH)
        }

        private const val SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH = 1658513413L
        private val setDefaultCanvasItemTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_default_canvas_item_texture_repeat", SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH)
        }

        private const val GET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH = 4049774160L
        private val getDefaultCanvasItemTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_default_canvas_item_texture_repeat", GET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH)
        }

        private const val SET_SDF_OVERSIZE_HASH = 2574159017L
        private val setSdfOversizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_sdf_oversize", SET_SDF_OVERSIZE_HASH)
        }

        private const val GET_SDF_OVERSIZE_HASH = 2631427510L
        private val getSdfOversizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_sdf_oversize", GET_SDF_OVERSIZE_HASH)
        }

        private const val SET_SDF_SCALE_HASH = 1402773951L
        private val setSdfScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_sdf_scale", SET_SDF_SCALE_HASH)
        }

        private const val GET_SDF_SCALE_HASH = 3162688184L
        private val getSdfScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_sdf_scale", GET_SDF_SCALE_HASH)
        }

        private const val SET_MESH_LOD_THRESHOLD_HASH = 373806689L
        private val setMeshLodThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_mesh_lod_threshold", SET_MESH_LOD_THRESHOLD_HASH)
        }

        private const val GET_MESH_LOD_THRESHOLD_HASH = 1740695150L
        private val getMeshLodThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_mesh_lod_threshold", GET_MESH_LOD_THRESHOLD_HASH)
        }

        private const val SET_AS_AUDIO_LISTENER_2D_HASH = 2586408642L
        private val setAsAudioListener2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_as_audio_listener_2d", SET_AS_AUDIO_LISTENER_2D_HASH)
        }

        private const val IS_AUDIO_LISTENER_2D_HASH = 36873697L
        private val isAudioListener2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_audio_listener_2d", IS_AUDIO_LISTENER_2D_HASH)
        }

        private const val GET_AUDIO_LISTENER_2D_HASH = 1840977180L
        private val getAudioListener2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_audio_listener_2d", GET_AUDIO_LISTENER_2D_HASH)
        }

        private const val GET_CAMERA_2D_HASH = 3551466917L
        private val getCamera2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_camera_2d", GET_CAMERA_2D_HASH)
        }

        private const val SET_WORLD_3D_HASH = 1400875337L
        private val setWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_world_3d", SET_WORLD_3D_HASH)
        }

        private const val GET_WORLD_3D_HASH = 317588385L
        private val getWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_world_3d", GET_WORLD_3D_HASH)
        }

        private const val FIND_WORLD_3D_HASH = 317588385L
        private val findWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "find_world_3d", FIND_WORLD_3D_HASH)
        }

        private const val SET_USE_OWN_WORLD_3D_HASH = 2586408642L
        private val setUseOwnWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_own_world_3d", SET_USE_OWN_WORLD_3D_HASH)
        }

        private const val IS_USING_OWN_WORLD_3D_HASH = 36873697L
        private val isUsingOwnWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_own_world_3d", IS_USING_OWN_WORLD_3D_HASH)
        }

        private const val GET_AUDIO_LISTENER_3D_HASH = 3472246991L
        private val getAudioListener3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_audio_listener_3d", GET_AUDIO_LISTENER_3D_HASH)
        }

        private const val GET_CAMERA_3D_HASH = 2285090890L
        private val getCamera3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_camera_3d", GET_CAMERA_3D_HASH)
        }

        private const val SET_AS_AUDIO_LISTENER_3D_HASH = 2586408642L
        private val setAsAudioListener3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_as_audio_listener_3d", SET_AS_AUDIO_LISTENER_3D_HASH)
        }

        private const val IS_AUDIO_LISTENER_3D_HASH = 36873697L
        private val isAudioListener3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_audio_listener_3d", IS_AUDIO_LISTENER_3D_HASH)
        }

        private const val SET_DISABLE_3D_HASH = 2586408642L
        private val setDisable3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_disable_3d", SET_DISABLE_3D_HASH)
        }

        private const val IS_3D_DISABLED_HASH = 36873697L
        private val is3dDisabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_3d_disabled", IS_3D_DISABLED_HASH)
        }

        private const val SET_USE_XR_HASH = 2586408642L
        private val setUseXrBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_xr", SET_USE_XR_HASH)
        }

        private const val IS_USING_XR_HASH = 36873697L
        private val isUsingXrBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_xr", IS_USING_XR_HASH)
        }

        private const val SET_SCALING_3D_MODE_HASH = 1531597597L
        private val setScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_scaling_3d_mode", SET_SCALING_3D_MODE_HASH)
        }

        private const val GET_SCALING_3D_MODE_HASH = 2597660574L
        private val getScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_scaling_3d_mode", GET_SCALING_3D_MODE_HASH)
        }

        private const val SET_SCALING_3D_SCALE_HASH = 373806689L
        private val setScaling3dScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_scaling_3d_scale", SET_SCALING_3D_SCALE_HASH)
        }

        private const val GET_SCALING_3D_SCALE_HASH = 1740695150L
        private val getScaling3dScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_scaling_3d_scale", GET_SCALING_3D_SCALE_HASH)
        }

        private const val SET_FSR_SHARPNESS_HASH = 373806689L
        private val setFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_fsr_sharpness", SET_FSR_SHARPNESS_HASH)
        }

        private const val GET_FSR_SHARPNESS_HASH = 1740695150L
        private val getFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_fsr_sharpness", GET_FSR_SHARPNESS_HASH)
        }

        private const val SET_TEXTURE_MIPMAP_BIAS_HASH = 373806689L
        private val setTextureMipmapBiasBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_texture_mipmap_bias", SET_TEXTURE_MIPMAP_BIAS_HASH)
        }

        private const val GET_TEXTURE_MIPMAP_BIAS_HASH = 1740695150L
        private val getTextureMipmapBiasBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_texture_mipmap_bias", GET_TEXTURE_MIPMAP_BIAS_HASH)
        }

        private const val SET_ANISOTROPIC_FILTERING_LEVEL_HASH = 3445583046L
        private val setAnisotropicFilteringLevelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_anisotropic_filtering_level", SET_ANISOTROPIC_FILTERING_LEVEL_HASH)
        }

        private const val GET_ANISOTROPIC_FILTERING_LEVEL_HASH = 3991528932L
        private val getAnisotropicFilteringLevelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_anisotropic_filtering_level", GET_ANISOTROPIC_FILTERING_LEVEL_HASH)
        }

        private const val SET_VRS_MODE_HASH = 2749867817L
        private val setVrsModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_vrs_mode", SET_VRS_MODE_HASH)
        }

        private const val GET_VRS_MODE_HASH = 349660525L
        private val getVrsModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_vrs_mode", GET_VRS_MODE_HASH)
        }

        private const val SET_VRS_UPDATE_MODE_HASH = 3182412319L
        private val setVrsUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_vrs_update_mode", SET_VRS_UPDATE_MODE_HASH)
        }

        private const val GET_VRS_UPDATE_MODE_HASH = 2255951583L
        private val getVrsUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_vrs_update_mode", GET_VRS_UPDATE_MODE_HASH)
        }

        private const val SET_VRS_TEXTURE_HASH = 4051416890L
        private val setVrsTextureBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_vrs_texture", SET_VRS_TEXTURE_HASH)
        }

        private const val GET_VRS_TEXTURE_HASH = 3635182373L
        private val getVrsTextureBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_vrs_texture", GET_VRS_TEXTURE_HASH)
        }
    }
}

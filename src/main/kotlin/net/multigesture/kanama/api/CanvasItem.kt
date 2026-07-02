package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for everything in 2D space.
 *
 * Generated from Godot docs: CanvasItem
 */
open class CanvasItem(handle: MemorySegment) : Node(handle) {
    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var selfModulate: Color
        @JvmName("selfModulateProperty")
        get() = getSelfModulate()
        @JvmName("setSelfModulateProperty")
        set(value) = setSelfModulate(value)

    var showBehindParent: Boolean
        @JvmName("showBehindParentProperty")
        get() = isDrawBehindParentEnabled()
        @JvmName("setShowBehindParentProperty")
        set(value) = setDrawBehindParent(value)

    var topLevel: Boolean
        @JvmName("topLevelProperty")
        get() = isSetAsTopLevel()
        @JvmName("setTopLevelProperty")
        set(value) = setAsTopLevel(value)

    var clipChildren: Long
        @JvmName("clipChildrenProperty")
        get() = getClipChildrenMode()
        @JvmName("setClipChildrenProperty")
        set(value) = setClipChildrenMode(value)

    var oversamplingWithScale: Long
        @JvmName("oversamplingWithScaleProperty")
        get() = getOversamplingWithScale()
        @JvmName("setOversamplingWithScaleProperty")
        set(value) = setOversamplingWithScale(value)

    var lightMask: Int
        @JvmName("lightMaskProperty")
        get() = getLightMask()
        @JvmName("setLightMaskProperty")
        set(value) = setLightMask(value)

    var visibilityLayer: Long
        @JvmName("visibilityLayerProperty")
        get() = getVisibilityLayer()
        @JvmName("setVisibilityLayerProperty")
        set(value) = setVisibilityLayer(value)

    var zIndex: Int
        @JvmName("zIndexProperty")
        get() = getZIndex()
        @JvmName("setZIndexProperty")
        set(value) = setZIndex(value)

    var zAsRelative: Boolean
        @JvmName("zAsRelativeProperty")
        get() = isZRelative()
        @JvmName("setZAsRelativeProperty")
        set(value) = setZAsRelative(value)

    var ySortEnabled: Boolean
        @JvmName("ySortEnabledProperty")
        get() = isYSortEnabled()
        @JvmName("setYSortEnabledProperty")
        set(value) = setYSortEnabled(value)

    var textureFilter: Long
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: Long
        @JvmName("textureRepeatProperty")
        get() = getTextureRepeat()
        @JvmName("setTextureRepeatProperty")
        set(value) = setTextureRepeat(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    var useParentMaterial: Boolean
        @JvmName("useParentMaterialProperty")
        get() = getUseParentMaterial()
        @JvmName("setUseParentMaterialProperty")
        set(value) = setUseParentMaterial(value)

    fun getCanvasItem(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCanvasItemBind, handle)
    }

    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleInTreeBind, handle)
    }

    /**
     * Show the `CanvasItem` if it's currently hidden. This is equivalent to setting `visible` to
     * `true`. Note: For controls that inherit `Popup`, the correct way to make them visible is to call
     * one of the multiple `popup*()` functions instead.
     *
     * Generated from Godot docs: CanvasItem.show
     */
    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, handle)
    }

    /**
     * Hide the `CanvasItem` if it's currently visible. This is equivalent to setting `visible` to
     * `false`.
     *
     * Generated from Godot docs: CanvasItem.hide
     */
    fun hide() {
        ObjectCalls.ptrcallNoArgs(hideBind, handle)
    }

    fun queueRedraw() {
        ObjectCalls.ptrcallNoArgs(queueRedrawBind, handle)
    }

    fun moveToFront() {
        ObjectCalls.ptrcallNoArgs(moveToFrontBind, handle)
    }

    fun setAsTopLevel(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsTopLevelBind, handle, enable)
    }

    fun isSetAsTopLevel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSetAsTopLevelBind, handle)
    }

    fun setLightMask(lightMask: Int) {
        ObjectCalls.ptrcallWithIntArg(setLightMaskBind, handle, lightMask)
    }

    fun getLightMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLightMaskBind, handle)
    }

    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, modulate)
    }

    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    fun setSelfModulate(selfModulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setSelfModulateBind, handle, selfModulate)
    }

    fun getSelfModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getSelfModulateBind, handle)
    }

    fun setZIndex(zIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setZIndexBind, handle, zIndex)
    }

    fun getZIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getZIndexBind, handle)
    }

    fun setZAsRelative(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setZAsRelativeBind, handle, enable)
    }

    fun isZRelative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isZRelativeBind, handle)
    }

    fun setYSortEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setYSortEnabledBind, handle, enabled)
    }

    fun isYSortEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isYSortEnabledBind, handle)
    }

    fun setDrawBehindParent(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBehindParentBind, handle, enable)
    }

    fun isDrawBehindParentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawBehindParentEnabledBind, handle)
    }

    fun drawLine(from: Vector2, to: Vector2, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithTwoVector2ColorDoubleBoolArgs(drawLineBind, handle, from, to, color, width, antialiased)
    }

    fun drawDashedLine(from: Vector2, to: Vector2, color: Color, width: Double = -1.0, dash: Double = 2.0, aligned: Boolean = true, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithTwoVector2ColorTwoDoubleTwoBoolArgs(drawDashedLineBind, handle, from, to, color, width, dash, aligned, antialiased)
    }

    fun drawPolyline(points: List<Vector2>, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListColorDoubleAndBoolArgs(drawPolylineBind, handle, points, color, width, antialiased)
    }

    fun drawPolylineColors(points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs(drawPolylineColorsBind, handle, points, colors, width, antialiased)
    }

    fun drawEllipseArc(center: Vector2, major: Double, minor: Double, startAngle: Double, endAngle: Double, pointCount: Int, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2FourDoubleIntColorDoubleBoolArgs(drawEllipseArcBind, handle, center, major, minor, startAngle, endAngle, pointCount, color, width, antialiased)
    }

    fun drawArc(center: Vector2, radius: Double, startAngle: Double, endAngle: Double, pointCount: Int, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2ThreeDoubleIntColorDoubleBoolArgs(drawArcBind, handle, center, radius, startAngle, endAngle, pointCount, color, width, antialiased)
    }

    fun drawMultiline(points: List<Vector2>, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListColorDoubleAndBoolArgs(drawMultilineBind, handle, points, color, width, antialiased)
    }

    fun drawMultilineColors(points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs(drawMultilineColorsBind, handle, points, colors, width, antialiased)
    }

    fun drawRect(rect: Rect2, color: Color, filled: Boolean = true, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRect2ColorBoolDoubleBoolArgs(drawRectBind, handle, rect, color, filled, width, antialiased)
    }

    fun drawCircle(position: Vector2, radius: Double, color: Color, filled: Boolean = true, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2DoubleColorBoolDoubleBoolArgs(drawCircleBind, handle, position, radius, color, filled, width, antialiased)
    }

    fun drawEllipse(position: Vector2, major: Double, minor: Double, color: Color, filled: Boolean = true, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithVector2TwoDoubleColorBoolDoubleBoolArgs(drawEllipseBind, handle, position, major, minor, color, filled, width, antialiased)
    }

    fun drawTexture(texture: Texture2D?, position: Vector2, modulate: Color) {
        ObjectCalls.ptrcallWithObjectVector2AndColorArgs(drawTextureBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, position, modulate)
    }

    fun drawTextureRect(texture: Texture2D?, rect: Rect2, tile: Boolean, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithObjectRect2BoolColorBoolArgs(drawTextureRectBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, tile, modulate, transpose)
    }

    fun drawTextureRectRegion(texture: Texture2D?, rect: Rect2, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithObjectTwoRect2ColorTwoBoolArgs(drawTextureRectRegionBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, srcRect, modulate, transpose, clipUv)
    }

    fun drawMsdfTextureRectRegion(texture: Texture2D?, rect: Rect2, srcRect: Rect2, modulate: Color, outline: Double = 0.0, pixelRange: Double = 4.0, scale: Double = 1.0) {
        ObjectCalls.ptrcallWithObjectTwoRect2ColorThreeDoubleArgs(drawMsdfTextureRectRegionBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, srcRect, modulate, outline, pixelRange, scale)
    }

    fun drawLcdTextureRectRegion(texture: Texture2D?, rect: Rect2, srcRect: Rect2, modulate: Color) {
        ObjectCalls.ptrcallWithObjectTwoRect2AndColorArgs(drawLcdTextureRectRegionBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, rect, srcRect, modulate)
    }

    fun drawStyleBox(styleBox: StyleBox?, rect: Rect2) {
        ObjectCalls.ptrcallWithObjectAndRect2Arg(drawStyleBoxBind, handle, styleBox?.requireOpenHandle() ?: MemorySegment.NULL, rect)
    }

    fun drawPrimitive(points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: Texture2D?) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs(drawPrimitiveBind, handle, points, colors, uvs, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun drawPolygon(points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: Texture2D?) {
        ObjectCalls.ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs(drawPolygonBind, handle, points, colors, uvs, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun drawColoredPolygon(points: List<Vector2>, color: Color, uvs: List<Vector2>, texture: Texture2D?) {
        ObjectCalls.ptrcallWithPackedVector2ListColorPackedVector2ListAndObjectArgs(drawColoredPolygonBind, handle, points, color, uvs, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun drawString(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleIntColorThreeLongDoubleArgs(drawStringBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, modulate, justificationFlags, direction, orientation, oversampling)
    }

    fun drawMultilineString(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(drawMultilineStringBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, maxLines, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    fun drawStringOutline(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, size: Int = 1, modulate: Color, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(drawStringOutlineBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, size, modulate, justificationFlags, direction, orientation, oversampling)
    }

    fun drawMultilineStringOutline(font: Font?, pos: Vector2, text: String, alignment: Long = 0L, width: Double = -1.0, fontSize: Int = 16, maxLines: Int = -1, size: Int = 1, modulate: Color, brkFlags: Long = 3L, justificationFlags: Long = 3L, direction: Long = 0L, orientation: Long = 0L, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(drawMultilineStringOutlineBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, text, alignment, width, fontSize, maxLines, size, modulate, brkFlags, justificationFlags, direction, orientation, oversampling)
    }

    fun drawChar(font: Font?, pos: Vector2, char: String, fontSize: Int = 16, modulate: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringIntColorDoubleArgs(drawCharBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, char, fontSize, modulate, oversampling)
    }

    fun drawCharOutline(font: Font?, pos: Vector2, char: String, fontSize: Int = 16, size: Int = -1, modulate: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithObjectVector2StringTwoIntColorDoubleArgs(drawCharOutlineBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, pos, char, fontSize, size, modulate, oversampling)
    }

    fun drawMesh(mesh: Mesh?, texture: Texture2D?, transform: Transform2D, modulate: Color) {
        ObjectCalls.ptrcallWithTwoObjectTransform2DColorArgs(drawMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, texture?.requireOpenHandle() ?: MemorySegment.NULL, transform, modulate)
    }

    fun drawMultimesh(multimesh: MultiMesh?, texture: Texture2D?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(drawMultimeshBind, handle, multimesh?.requireOpenHandle() ?: MemorySegment.NULL, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun drawSetTransform(position: Vector2, rotation: Double = 0.0, scale: Vector2) {
        ObjectCalls.ptrcallWithVector2DoubleVector2Args(drawSetTransformBind, handle, position, rotation, scale)
    }

    fun drawSetTransformMatrix(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(drawSetTransformMatrixBind, handle, xform)
    }

    fun drawAnimationSlice(animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithFourDoubleArgs(drawAnimationSliceBind, handle, animationLength, sliceBegin, sliceEnd, offset)
    }

    fun drawEndAnimation() {
        ObjectCalls.ptrcallNoArgs(drawEndAnimationBind, handle)
    }

    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getTransformBind, handle)
    }

    fun getGlobalTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getGlobalTransformBind, handle)
    }

    fun getGlobalTransformWithCanvas(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getGlobalTransformWithCanvasBind, handle)
    }

    fun getViewportTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getViewportTransformBind, handle)
    }

    fun getViewportRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getViewportRectBind, handle)
    }

    fun getCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getCanvasTransformBind, handle)
    }

    fun getScreenTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getScreenTransformBind, handle)
    }

    fun getLocalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLocalMousePositionBind, handle)
    }

    fun getGlobalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalMousePositionBind, handle)
    }

    fun getCanvas(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCanvasBind, handle)
    }

    fun getCanvasLayerNode(): CanvasLayer? {
        return CanvasLayer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCanvasLayerNodeBind, handle))
    }

    fun getWorld2d(): World2D? {
        return World2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld2dBind, handle))
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    fun setInstanceShaderParameter(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setInstanceShaderParameterBind, handle, name, value)
    }

    fun getInstanceShaderParameter(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getInstanceShaderParameterBind, handle, name)
    }

    fun setUseParentMaterial(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseParentMaterialBind, handle, enable)
    }

    fun getUseParentMaterial(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseParentMaterialBind, handle)
    }

    fun setNotifyLocalTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyLocalTransformBind, handle, enable)
    }

    fun isLocalTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalTransformNotificationEnabledBind, handle)
    }

    fun setNotifyTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyTransformBind, handle, enable)
    }

    fun isTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransformNotificationEnabledBind, handle)
    }

    fun forceUpdateTransform() {
        ObjectCalls.ptrcallNoArgs(forceUpdateTransformBind, handle)
    }

    fun makeCanvasPositionLocal(viewportPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(makeCanvasPositionLocalBind, handle, viewportPoint)
    }

    fun makeInputLocal(event: InputEvent?): InputEvent? {
        return InputEvent.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(makeInputLocalBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setVisibilityLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setVisibilityLayerBind, handle, layer)
    }

    fun getVisibilityLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getVisibilityLayerBind, handle)
    }

    fun setVisibilityLayerBit(layer: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(setVisibilityLayerBitBind, handle, layer, enabled)
    }

    fun getVisibilityLayerBit(layer: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(getVisibilityLayerBitBind, handle, layer)
    }

    fun setTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, mode)
    }

    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    fun setTextureRepeat(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureRepeatBind, handle, mode)
    }

    fun getTextureRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureRepeatBind, handle)
    }

    fun setClipChildrenMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setClipChildrenModeBind, handle, mode)
    }

    fun getClipChildrenMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getClipChildrenModeBind, handle)
    }

    fun setOversamplingWithScale(enabled: Long) {
        ObjectCalls.ptrcallWithLongArg(setOversamplingWithScaleBind, handle, enabled)
    }

    fun getOversamplingWithScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOversamplingWithScaleBind, handle)
    }

    object Signals {
        const val draw: String = "draw"
        const val visibilityChanged: String = "visibility_changed"
        const val hidden: String = "hidden"
        const val itemRectChanged: String = "item_rect_changed"
    }

    companion object {
        const val NOTIFICATION_TRANSFORM_CHANGED: Long = 2000L
        const val NOTIFICATION_LOCAL_TRANSFORM_CHANGED: Long = 35L
        const val NOTIFICATION_DRAW: Long = 30L
        const val NOTIFICATION_VISIBILITY_CHANGED: Long = 31L
        const val NOTIFICATION_ENTER_CANVAS: Long = 32L
        const val NOTIFICATION_EXIT_CANVAS: Long = 33L
        const val NOTIFICATION_WORLD_2D_CHANGED: Long = 36L
        const val TEXTURE_FILTER_PARENT_NODE: Long = 0L
        const val TEXTURE_FILTER_NEAREST: Long = 1L
        const val TEXTURE_FILTER_LINEAR: Long = 2L
        const val TEXTURE_FILTER_NEAREST_WITH_MIPMAPS: Long = 3L
        const val TEXTURE_FILTER_LINEAR_WITH_MIPMAPS: Long = 4L
        const val TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC: Long = 5L
        const val TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC: Long = 6L
        const val TEXTURE_FILTER_MAX: Long = 7L
        const val TEXTURE_REPEAT_PARENT_NODE: Long = 0L
        const val TEXTURE_REPEAT_DISABLED: Long = 1L
        const val TEXTURE_REPEAT_ENABLED: Long = 2L
        const val TEXTURE_REPEAT_MIRROR: Long = 3L
        const val TEXTURE_REPEAT_MAX: Long = 4L
        const val CLIP_CHILDREN_DISABLED: Long = 0L
        const val CLIP_CHILDREN_ONLY: Long = 1L
        const val CLIP_CHILDREN_AND_DRAW: Long = 2L
        const val CLIP_CHILDREN_MAX: Long = 3L
        const val OVERSAMPLING_WITH_SCALE_PARENT_NODE: Long = 0L
        const val OVERSAMPLING_WITH_SCALE_DISABLED: Long = 1L
        const val OVERSAMPLING_WITH_SCALE_ENABLED: Long = 2L
        const val OVERSAMPLING_WITH_SCALE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasItem? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasItem? =
            if (handle.address() == 0L) null else CanvasItem(handle)

        private const val GET_CANVAS_ITEM_HASH = 2944877500L
        private val getCanvasItemBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_item", GET_CANVAS_ITEM_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 36873697L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_visible", IS_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_IN_TREE_HASH = 36873697L
        private val isVisibleInTreeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_visible_in_tree", IS_VISIBLE_IN_TREE_HASH)
        }

        private const val SHOW_HASH = 3218959716L
        private val showBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "show", SHOW_HASH)
        }

        private const val HIDE_HASH = 3218959716L
        private val hideBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "hide", HIDE_HASH)
        }

        private const val QUEUE_REDRAW_HASH = 3218959716L
        private val queueRedrawBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "queue_redraw", QUEUE_REDRAW_HASH)
        }

        private const val MOVE_TO_FRONT_HASH = 3218959716L
        private val moveToFrontBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "move_to_front", MOVE_TO_FRONT_HASH)
        }

        private const val SET_AS_TOP_LEVEL_HASH = 2586408642L
        private val setAsTopLevelBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_as_top_level", SET_AS_TOP_LEVEL_HASH)
        }

        private const val IS_SET_AS_TOP_LEVEL_HASH = 36873697L
        private val isSetAsTopLevelBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_set_as_top_level", IS_SET_AS_TOP_LEVEL_HASH)
        }

        private const val SET_LIGHT_MASK_HASH = 1286410249L
        private val setLightMaskBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_light_mask", SET_LIGHT_MASK_HASH)
        }

        private const val GET_LIGHT_MASK_HASH = 3905245786L
        private val getLightMaskBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_light_mask", GET_LIGHT_MASK_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_SELF_MODULATE_HASH = 2920490490L
        private val setSelfModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_self_modulate", SET_SELF_MODULATE_HASH)
        }

        private const val GET_SELF_MODULATE_HASH = 3444240500L
        private val getSelfModulateBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_self_modulate", GET_SELF_MODULATE_HASH)
        }

        private const val SET_Z_INDEX_HASH = 1286410249L
        private val setZIndexBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_z_index", SET_Z_INDEX_HASH)
        }

        private const val GET_Z_INDEX_HASH = 3905245786L
        private val getZIndexBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_z_index", GET_Z_INDEX_HASH)
        }

        private const val SET_Z_AS_RELATIVE_HASH = 2586408642L
        private val setZAsRelativeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_z_as_relative", SET_Z_AS_RELATIVE_HASH)
        }

        private const val IS_Z_RELATIVE_HASH = 36873697L
        private val isZRelativeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_z_relative", IS_Z_RELATIVE_HASH)
        }

        private const val SET_Y_SORT_ENABLED_HASH = 2586408642L
        private val setYSortEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_y_sort_enabled", SET_Y_SORT_ENABLED_HASH)
        }

        private const val IS_Y_SORT_ENABLED_HASH = 36873697L
        private val isYSortEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_y_sort_enabled", IS_Y_SORT_ENABLED_HASH)
        }

        private const val SET_DRAW_BEHIND_PARENT_HASH = 2586408642L
        private val setDrawBehindParentBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_draw_behind_parent", SET_DRAW_BEHIND_PARENT_HASH)
        }

        private const val IS_DRAW_BEHIND_PARENT_ENABLED_HASH = 36873697L
        private val isDrawBehindParentEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_draw_behind_parent_enabled", IS_DRAW_BEHIND_PARENT_ENABLED_HASH)
        }

        private const val DRAW_LINE_HASH = 1562330099L
        private val drawLineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_line", DRAW_LINE_HASH)
        }

        private const val DRAW_DASHED_LINE_HASH = 3653831622L
        private val drawDashedLineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_dashed_line", DRAW_DASHED_LINE_HASH)
        }

        private const val DRAW_POLYLINE_HASH = 3797364428L
        private val drawPolylineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_polyline", DRAW_POLYLINE_HASH)
        }

        private const val DRAW_POLYLINE_COLORS_HASH = 2311979562L
        private val drawPolylineColorsBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_polyline_colors", DRAW_POLYLINE_COLORS_HASH)
        }

        private const val DRAW_ELLIPSE_ARC_HASH = 936174114L
        private val drawEllipseArcBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_ellipse_arc", DRAW_ELLIPSE_ARC_HASH)
        }

        private const val DRAW_ARC_HASH = 4140652635L
        private val drawArcBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_arc", DRAW_ARC_HASH)
        }

        private const val DRAW_MULTILINE_HASH = 3797364428L
        private val drawMultilineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline", DRAW_MULTILINE_HASH)
        }

        private const val DRAW_MULTILINE_COLORS_HASH = 2311979562L
        private val drawMultilineColorsBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_colors", DRAW_MULTILINE_COLORS_HASH)
        }

        private const val DRAW_RECT_HASH = 2773573813L
        private val drawRectBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_rect", DRAW_RECT_HASH)
        }

        private const val DRAW_CIRCLE_HASH = 3153026596L
        private val drawCircleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_circle", DRAW_CIRCLE_HASH)
        }

        private const val DRAW_ELLIPSE_HASH = 3790774806L
        private val drawEllipseBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_ellipse", DRAW_ELLIPSE_HASH)
        }

        private const val DRAW_TEXTURE_HASH = 520200117L
        private val drawTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture", DRAW_TEXTURE_HASH)
        }

        private const val DRAW_TEXTURE_RECT_HASH = 3832805018L
        private val drawTextureRectBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture_rect", DRAW_TEXTURE_RECT_HASH)
        }

        private const val DRAW_TEXTURE_RECT_REGION_HASH = 3883821411L
        private val drawTextureRectRegionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_texture_rect_region", DRAW_TEXTURE_RECT_REGION_HASH)
        }

        private const val DRAW_MSDF_TEXTURE_RECT_REGION_HASH = 4219163252L
        private val drawMsdfTextureRectRegionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_msdf_texture_rect_region", DRAW_MSDF_TEXTURE_RECT_REGION_HASH)
        }

        private const val DRAW_LCD_TEXTURE_RECT_REGION_HASH = 3212350954L
        private val drawLcdTextureRectRegionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_lcd_texture_rect_region", DRAW_LCD_TEXTURE_RECT_REGION_HASH)
        }

        private const val DRAW_STYLE_BOX_HASH = 388176283L
        private val drawStyleBoxBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_style_box", DRAW_STYLE_BOX_HASH)
        }

        private const val DRAW_PRIMITIVE_HASH = 3288481815L
        private val drawPrimitiveBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_primitive", DRAW_PRIMITIVE_HASH)
        }

        private const val DRAW_POLYGON_HASH = 974537912L
        private val drawPolygonBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_polygon", DRAW_POLYGON_HASH)
        }

        private const val DRAW_COLORED_POLYGON_HASH = 15245644L
        private val drawColoredPolygonBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_colored_polygon", DRAW_COLORED_POLYGON_HASH)
        }

        private const val DRAW_STRING_HASH = 719605945L
        private val drawStringBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_string", DRAW_STRING_HASH)
        }

        private const val DRAW_MULTILINE_STRING_HASH = 2341488182L
        private val drawMultilineStringBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_string", DRAW_MULTILINE_STRING_HASH)
        }

        private const val DRAW_STRING_OUTLINE_HASH = 707403449L
        private val drawStringOutlineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_string_outline", DRAW_STRING_OUTLINE_HASH)
        }

        private const val DRAW_MULTILINE_STRING_OUTLINE_HASH = 3050414441L
        private val drawMultilineStringOutlineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multiline_string_outline", DRAW_MULTILINE_STRING_OUTLINE_HASH)
        }

        private const val DRAW_CHAR_HASH = 1336210142L
        private val drawCharBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_char", DRAW_CHAR_HASH)
        }

        private const val DRAW_CHAR_OUTLINE_HASH = 1846384149L
        private val drawCharOutlineBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_char_outline", DRAW_CHAR_OUTLINE_HASH)
        }

        private const val DRAW_MESH_HASH = 153818295L
        private val drawMeshBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_mesh", DRAW_MESH_HASH)
        }

        private const val DRAW_MULTIMESH_HASH = 937992368L
        private val drawMultimeshBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_multimesh", DRAW_MULTIMESH_HASH)
        }

        private const val DRAW_SET_TRANSFORM_HASH = 288975085L
        private val drawSetTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform", DRAW_SET_TRANSFORM_HASH)
        }

        private const val DRAW_SET_TRANSFORM_MATRIX_HASH = 2761652528L
        private val drawSetTransformMatrixBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform_matrix", DRAW_SET_TRANSFORM_MATRIX_HASH)
        }

        private const val DRAW_ANIMATION_SLICE_HASH = 3112831842L
        private val drawAnimationSliceBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_animation_slice", DRAW_ANIMATION_SLICE_HASH)
        }

        private const val DRAW_END_ANIMATION_HASH = 3218959716L
        private val drawEndAnimationBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_end_animation", DRAW_END_ANIMATION_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3814499831L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_TRANSFORM_HASH = 3814499831L
        private val getGlobalTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_transform", GET_GLOBAL_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_TRANSFORM_WITH_CANVAS_HASH = 3814499831L
        private val getGlobalTransformWithCanvasBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_transform_with_canvas", GET_GLOBAL_TRANSFORM_WITH_CANVAS_HASH)
        }

        private const val GET_VIEWPORT_TRANSFORM_HASH = 3814499831L
        private val getViewportTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_viewport_transform", GET_VIEWPORT_TRANSFORM_HASH)
        }

        private const val GET_VIEWPORT_RECT_HASH = 1639390495L
        private val getViewportRectBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_viewport_rect", GET_VIEWPORT_RECT_HASH)
        }

        private const val GET_CANVAS_TRANSFORM_HASH = 3814499831L
        private val getCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_transform", GET_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_SCREEN_TRANSFORM_HASH = 3814499831L
        private val getScreenTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_screen_transform", GET_SCREEN_TRANSFORM_HASH)
        }

        private const val GET_LOCAL_MOUSE_POSITION_HASH = 3341600327L
        private val getLocalMousePositionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_local_mouse_position", GET_LOCAL_MOUSE_POSITION_HASH)
        }

        private const val GET_GLOBAL_MOUSE_POSITION_HASH = 3341600327L
        private val getGlobalMousePositionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_mouse_position", GET_GLOBAL_MOUSE_POSITION_HASH)
        }

        private const val GET_CANVAS_HASH = 2944877500L
        private val getCanvasBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas", GET_CANVAS_HASH)
        }

        private const val GET_CANVAS_LAYER_NODE_HASH = 2602762519L
        private val getCanvasLayerNodeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_canvas_layer_node", GET_CANVAS_LAYER_NODE_HASH)
        }

        private const val GET_WORLD_2D_HASH = 2339128592L
        private val getWorld2dBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_world_2d", GET_WORLD_2D_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_material", GET_MATERIAL_HASH)
        }

        private const val SET_INSTANCE_SHADER_PARAMETER_HASH = 3776071444L
        private val setInstanceShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_instance_shader_parameter", SET_INSTANCE_SHADER_PARAMETER_HASH)
        }

        private const val GET_INSTANCE_SHADER_PARAMETER_HASH = 2760726917L
        private val getInstanceShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_instance_shader_parameter", GET_INSTANCE_SHADER_PARAMETER_HASH)
        }

        private const val SET_USE_PARENT_MATERIAL_HASH = 2586408642L
        private val setUseParentMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_use_parent_material", SET_USE_PARENT_MATERIAL_HASH)
        }

        private const val GET_USE_PARENT_MATERIAL_HASH = 36873697L
        private val getUseParentMaterialBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_use_parent_material", GET_USE_PARENT_MATERIAL_HASH)
        }

        private const val SET_NOTIFY_LOCAL_TRANSFORM_HASH = 2586408642L
        private val setNotifyLocalTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_notify_local_transform", SET_NOTIFY_LOCAL_TRANSFORM_HASH)
        }

        private const val IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        private val isLocalTransformNotificationEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_local_transform_notification_enabled", IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH)
        }

        private const val SET_NOTIFY_TRANSFORM_HASH = 2586408642L
        private val setNotifyTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_notify_transform", SET_NOTIFY_TRANSFORM_HASH)
        }

        private const val IS_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        private val isTransformNotificationEnabledBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "is_transform_notification_enabled", IS_TRANSFORM_NOTIFICATION_ENABLED_HASH)
        }

        private const val FORCE_UPDATE_TRANSFORM_HASH = 3218959716L
        private val forceUpdateTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "force_update_transform", FORCE_UPDATE_TRANSFORM_HASH)
        }

        private const val MAKE_CANVAS_POSITION_LOCAL_HASH = 2656412154L
        private val makeCanvasPositionLocalBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "make_canvas_position_local", MAKE_CANVAS_POSITION_LOCAL_HASH)
        }

        private const val MAKE_INPUT_LOCAL_HASH = 811130057L
        private val makeInputLocalBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "make_input_local", MAKE_INPUT_LOCAL_HASH)
        }

        private const val SET_VISIBILITY_LAYER_HASH = 1286410249L
        private val setVisibilityLayerBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_visibility_layer", SET_VISIBILITY_LAYER_HASH)
        }

        private const val GET_VISIBILITY_LAYER_HASH = 3905245786L
        private val getVisibilityLayerBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_visibility_layer", GET_VISIBILITY_LAYER_HASH)
        }

        private const val SET_VISIBILITY_LAYER_BIT_HASH = 300928843L
        private val setVisibilityLayerBitBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_visibility_layer_bit", SET_VISIBILITY_LAYER_BIT_HASH)
        }

        private const val GET_VISIBILITY_LAYER_BIT_HASH = 1116898809L
        private val getVisibilityLayerBitBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_visibility_layer_bit", GET_VISIBILITY_LAYER_BIT_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 1037999706L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 121960042L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val SET_TEXTURE_REPEAT_HASH = 1716472974L
        private val setTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)
        }

        private const val GET_TEXTURE_REPEAT_HASH = 2667158319L
        private val getTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)
        }

        private const val SET_CLIP_CHILDREN_MODE_HASH = 1319393776L
        private val setClipChildrenModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_clip_children_mode", SET_CLIP_CHILDREN_MODE_HASH)
        }

        private const val GET_CLIP_CHILDREN_MODE_HASH = 3581808349L
        private val getClipChildrenModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_clip_children_mode", GET_CLIP_CHILDREN_MODE_HASH)
        }

        private const val SET_OVERSAMPLING_WITH_SCALE_HASH = 872218804L
        private val setOversamplingWithScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "set_oversampling_with_scale", SET_OVERSAMPLING_WITH_SCALE_HASH)
        }

        private const val GET_OVERSAMPLING_WITH_SCALE_HASH = 2026097197L
        private val getOversamplingWithScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_oversampling_with_scale", GET_OVERSAMPLING_WITH_SCALE_HASH)
        }
    }
}

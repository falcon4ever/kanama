package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: CanvasItem
 */
open class CanvasItem(handle: MemorySegment) : Node(handle) {
    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

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

    var useParentMaterial: Boolean
        @JvmName("useParentMaterialProperty")
        get() = getUseParentMaterial()
        @JvmName("setUseParentMaterialProperty")
        set(value) = setUseParentMaterial(value)

    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleInTreeBind, handle)
    }

    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, handle)
    }

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

    fun drawSetTransform(position: Vector2, rotation: Double = 0.0, scale: Vector2) {
        ObjectCalls.ptrcallWithVector2DoubleVector2Args(drawSetTransformBind, handle, position, rotation, scale)
    }

    fun drawAnimationSlice(animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithFourDoubleArgs(drawAnimationSliceBind, handle, animationLength, sliceBegin, sliceEnd, offset)
    }

    fun drawEndAnimation() {
        ObjectCalls.ptrcallNoArgs(drawEndAnimationBind, handle)
    }

    fun getLocalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLocalMousePositionBind, handle)
    }

    fun getGlobalMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalMousePositionBind, handle)
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

        fun fromHandle(handle: MemorySegment): CanvasItem? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasItem? =
            if (handle.address() == 0L) null else CanvasItem(handle)

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

        private const val DRAW_SET_TRANSFORM_HASH = 288975085L
        private val drawSetTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_set_transform", DRAW_SET_TRANSFORM_HASH)
        }

        private const val DRAW_ANIMATION_SLICE_HASH = 3112831842L
        private val drawAnimationSliceBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_animation_slice", DRAW_ANIMATION_SLICE_HASH)
        }

        private const val DRAW_END_ANIMATION_HASH = 3218959716L
        private val drawEndAnimationBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "draw_end_animation", DRAW_END_ANIMATION_HASH)
        }

        private const val GET_LOCAL_MOUSE_POSITION_HASH = 3341600327L
        private val getLocalMousePositionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_local_mouse_position", GET_LOCAL_MOUSE_POSITION_HASH)
        }

        private const val GET_GLOBAL_MOUSE_POSITION_HASH = 3341600327L
        private val getGlobalMousePositionBind by lazy {
            ObjectCalls.getMethodBind("CanvasItem", "get_global_mouse_position", GET_GLOBAL_MOUSE_POSITION_HASH)
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

    // KANAMA-IOS-SUGAR: hand-added to a generated file; re-add after regeneration.
    // ── Kanama sugar (hand) - preserve on regenerate ──────────────────────────

    fun getViewportRect(): net.multigesture.kanama.types.Rect2 =
        IosGodot.canvasItemGetViewportRect(handle.address())

    var modulate: net.multigesture.kanama.types.Color
        // KANAMA-IOS-STUB: get_modulate not read (Color return not audited); set is real. Backlog.
        get() = net.multigesture.kanama.types.Color(1f, 1f, 1f)
        set(value) { IosGodot.canvasItemSetModulate(handle.address(), value) }
}

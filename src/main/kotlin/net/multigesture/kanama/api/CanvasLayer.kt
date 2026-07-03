package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * A node used for independent rendering of objects within a 2D scene.
 *
 * Generated from Godot docs: CanvasLayer
 */
open class CanvasLayer(handle: MemorySegment) : Node(handle) {
    var layer: Int
        @JvmName("layerProperty")
        get() = getLayer()
        @JvmName("setLayerProperty")
        set(value) = setLayer(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var rotation: Double
        @JvmName("rotationProperty")
        get() = getRotation()
        @JvmName("setRotationProperty")
        set(value) = setRotation(value)

    var scale: Vector2
        @JvmName("scaleProperty")
        get() = getScale()
        @JvmName("setScaleProperty")
        set(value) = setScale(value)

    var transform: Transform2D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    val customViewport: Node?
        @JvmName("customViewportProperty")
        get() = getCustomViewport()

    var followViewportEnabled: Boolean
        @JvmName("followViewportEnabledProperty")
        get() = isFollowingViewport()
        @JvmName("setFollowViewportEnabledProperty")
        set(value) = setFollowViewport(value)

    var followViewportScale: Double
        @JvmName("followViewportScaleProperty")
        get() = getFollowViewportScale()
        @JvmName("setFollowViewportScaleProperty")
        set(value) = setFollowViewportScale(value)

    /**
     * Layer index for draw order. Lower values are drawn behind higher values. Note: If multiple
     * CanvasLayers have the same layer index, `CanvasItem` children of one CanvasLayer are drawn
     * behind the `CanvasItem` children of the other CanvasLayer. Which CanvasLayer is drawn in front
     * is non-deterministic. Note: The layer index should be between `RenderingServer.CANVAS_LAYER_MIN`
     * and `RenderingServer.CANVAS_LAYER_MAX` (inclusive). Any other value will wrap around.
     *
     * Generated from Godot docs: CanvasLayer.set_layer
     */
    fun setLayer(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setLayerBind, handle, layer)
    }

    /**
     * Layer index for draw order. Lower values are drawn behind higher values. Note: If multiple
     * CanvasLayers have the same layer index, `CanvasItem` children of one CanvasLayer are drawn
     * behind the `CanvasItem` children of the other CanvasLayer. Which CanvasLayer is drawn in front
     * is non-deterministic. Note: The layer index should be between `RenderingServer.CANVAS_LAYER_MIN`
     * and `RenderingServer.CANVAS_LAYER_MAX` (inclusive). Any other value will wrap around.
     *
     * Generated from Godot docs: CanvasLayer.get_layer
     */
    fun getLayer(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLayerBind, handle)
    }

    /**
     * If `false`, any `CanvasItem` under this `CanvasLayer` will be hidden. Unlike
     * `CanvasItem.visible`, visibility of a `CanvasLayer` isn't propagated to underlying layers.
     *
     * Generated from Godot docs: CanvasLayer.set_visible
     */
    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    /**
     * If `false`, any `CanvasItem` under this `CanvasLayer` will be hidden. Unlike
     * `CanvasItem.visible`, visibility of a `CanvasLayer` isn't propagated to underlying layers.
     *
     * Generated from Godot docs: CanvasLayer.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    /**
     * Shows any `CanvasItem` under this `CanvasLayer`. This is equivalent to setting `visible` to
     * `true`.
     *
     * Generated from Godot docs: CanvasLayer.show
     */
    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, handle)
    }

    /**
     * Hides any `CanvasItem` under this `CanvasLayer`. This is equivalent to setting `visible` to
     * `false`.
     *
     * Generated from Godot docs: CanvasLayer.hide
     */
    fun hide() {
        ObjectCalls.ptrcallNoArgs(hideBind, handle)
    }

    /**
     * The layer's transform.
     *
     * Generated from Godot docs: CanvasLayer.set_transform
     */
    fun setTransform(transform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setTransformBind, handle, transform)
    }

    /**
     * The layer's transform.
     *
     * Generated from Godot docs: CanvasLayer.get_transform
     */
    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getTransformBind, handle)
    }

    /**
     * Returns the transform from the `CanvasLayer`s coordinate system to the `Viewport`s coordinate
     * system.
     *
     * Generated from Godot docs: CanvasLayer.get_final_transform
     */
    fun getFinalTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getFinalTransformBind, handle)
    }

    /**
     * The layer's base offset.
     *
     * Generated from Godot docs: CanvasLayer.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The layer's base offset.
     *
     * Generated from Godot docs: CanvasLayer.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * The layer's rotation in radians.
     *
     * Generated from Godot docs: CanvasLayer.set_rotation
     */
    fun setRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationBind, handle, radians)
    }

    /**
     * The layer's rotation in radians.
     *
     * Generated from Godot docs: CanvasLayer.get_rotation
     */
    fun getRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationBind, handle)
    }

    /**
     * The layer's scale.
     *
     * Generated from Godot docs: CanvasLayer.set_scale
     */
    fun setScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScaleBind, handle, scale)
    }

    /**
     * The layer's scale.
     *
     * Generated from Godot docs: CanvasLayer.get_scale
     */
    fun getScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScaleBind, handle)
    }

    /**
     * If enabled, the `CanvasLayer` maintains its position in world space. If disabled, the
     * `CanvasLayer` stays in a fixed position on the screen. Together with `follow_viewport_scale`,
     * this can be used for a pseudo-3D effect.
     *
     * Generated from Godot docs: CanvasLayer.set_follow_viewport
     */
    fun setFollowViewport(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowViewportBind, handle, enable)
    }

    /**
     * If enabled, the `CanvasLayer` maintains its position in world space. If disabled, the
     * `CanvasLayer` stays in a fixed position on the screen. Together with `follow_viewport_scale`,
     * this can be used for a pseudo-3D effect.
     *
     * Generated from Godot docs: CanvasLayer.is_following_viewport
     */
    fun isFollowingViewport(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFollowingViewportBind, handle)
    }

    /**
     * Scales the layer when using `follow_viewport_enabled`. Layers moving into the foreground should
     * have increasing scales, while layers moving into the background should have decreasing scales.
     *
     * Generated from Godot docs: CanvasLayer.set_follow_viewport_scale
     */
    fun setFollowViewportScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFollowViewportScaleBind, handle, scale)
    }

    /**
     * Scales the layer when using `follow_viewport_enabled`. Layers moving into the foreground should
     * have increasing scales, while layers moving into the background should have decreasing scales.
     *
     * Generated from Godot docs: CanvasLayer.get_follow_viewport_scale
     */
    fun getFollowViewportScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFollowViewportScaleBind, handle)
    }

    /**
     * The custom `Viewport` node assigned to the `CanvasLayer`. If `null`, uses the default viewport
     * instead.
     *
     * Generated from Godot docs: CanvasLayer.set_custom_viewport
     */
    fun setCustomViewport(viewport: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setCustomViewportBind, handle, listOf(viewport.handle))
    }

    /**
     * The custom `Viewport` node assigned to the `CanvasLayer`. If `null`, uses the default viewport
     * instead.
     *
     * Generated from Godot docs: CanvasLayer.get_custom_viewport
     */
    fun getCustomViewport(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCustomViewportBind, handle))
    }

    /**
     * Returns the RID of the canvas used by this layer.
     *
     * Generated from Godot docs: CanvasLayer.get_canvas
     */
    fun getCanvas(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCanvasBind, handle)
    }

    object Signals {
        const val visibilityChanged: String = "visibility_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasLayer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasLayer? =
            if (handle.address() == 0L) null else CanvasLayer(handle)

        private const val SET_LAYER_HASH = 1286410249L
        private val setLayerBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_layer", SET_LAYER_HASH)
        }

        private const val GET_LAYER_HASH = 3905245786L
        private val getLayerBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_layer", GET_LAYER_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 36873697L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "is_visible", IS_VISIBLE_HASH)
        }

        private const val SHOW_HASH = 3218959716L
        private val showBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "show", SHOW_HASH)
        }

        private const val HIDE_HASH = 3218959716L
        private val hideBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "hide", HIDE_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2761652528L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3814499831L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_FINAL_TRANSFORM_HASH = 3814499831L
        private val getFinalTransformBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_final_transform", GET_FINAL_TRANSFORM_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_ROTATION_HASH = 373806689L
        private val setRotationBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_rotation", SET_ROTATION_HASH)
        }

        private const val GET_ROTATION_HASH = 1740695150L
        private val getRotationBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_rotation", GET_ROTATION_HASH)
        }

        private const val SET_SCALE_HASH = 743155724L
        private val setScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_scale", SET_SCALE_HASH)
        }

        private const val GET_SCALE_HASH = 3341600327L
        private val getScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_scale", GET_SCALE_HASH)
        }

        private const val SET_FOLLOW_VIEWPORT_HASH = 2586408642L
        private val setFollowViewportBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_follow_viewport", SET_FOLLOW_VIEWPORT_HASH)
        }

        private const val IS_FOLLOWING_VIEWPORT_HASH = 36873697L
        private val isFollowingViewportBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "is_following_viewport", IS_FOLLOWING_VIEWPORT_HASH)
        }

        private const val SET_FOLLOW_VIEWPORT_SCALE_HASH = 373806689L
        private val setFollowViewportScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_follow_viewport_scale", SET_FOLLOW_VIEWPORT_SCALE_HASH)
        }

        private const val GET_FOLLOW_VIEWPORT_SCALE_HASH = 1740695150L
        private val getFollowViewportScaleBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_follow_viewport_scale", GET_FOLLOW_VIEWPORT_SCALE_HASH)
        }

        private const val SET_CUSTOM_VIEWPORT_HASH = 1078189570L
        private val setCustomViewportBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "set_custom_viewport", SET_CUSTOM_VIEWPORT_HASH)
        }

        private const val GET_CUSTOM_VIEWPORT_HASH = 3160264692L
        private val getCustomViewportBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_custom_viewport", GET_CUSTOM_VIEWPORT_HASH)
        }

        private const val GET_CANVAS_HASH = 2944877500L
        private val getCanvasBind by lazy {
            ObjectCalls.getMethodBind("CanvasLayer", "get_canvas", GET_CANVAS_HASH)
        }
    }
}

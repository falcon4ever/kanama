package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A parallax scrolling layer to be used with `ParallaxBackground`.
 *
 * Generated from Godot docs: ParallaxLayer
 */
class ParallaxLayer(handle: MemorySegment) : Node2D(handle) {
    var motionScale: Vector2
        @JvmName("motionScaleProperty")
        get() = getMotionScale()
        @JvmName("setMotionScaleProperty")
        set(value) = setMotionScale(value)

    var motionOffset: Vector2
        @JvmName("motionOffsetProperty")
        get() = getMotionOffset()
        @JvmName("setMotionOffsetProperty")
        set(value) = setMotionOffset(value)

    var motionMirroring: Vector2
        @JvmName("motionMirroringProperty")
        get() = getMirroring()
        @JvmName("setMotionMirroringProperty")
        set(value) = setMirroring(value)

    /**
     * Multiplies the ParallaxLayer's motion. If an axis is set to `0`, it will not scroll.
     *
     * Generated from Godot docs: ParallaxLayer.set_motion_scale
     */
    fun setMotionScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMotionScaleBind, handle, scale)
    }

    /**
     * Multiplies the ParallaxLayer's motion. If an axis is set to `0`, it will not scroll.
     *
     * Generated from Godot docs: ParallaxLayer.get_motion_scale
     */
    fun getMotionScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMotionScaleBind, handle)
    }

    /**
     * The ParallaxLayer's offset relative to the parent ParallaxBackground's
     * `ParallaxBackground.scroll_offset`.
     *
     * Generated from Godot docs: ParallaxLayer.set_motion_offset
     */
    fun setMotionOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMotionOffsetBind, handle, offset)
    }

    /**
     * The ParallaxLayer's offset relative to the parent ParallaxBackground's
     * `ParallaxBackground.scroll_offset`.
     *
     * Generated from Godot docs: ParallaxLayer.get_motion_offset
     */
    fun getMotionOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMotionOffsetBind, handle)
    }

    /**
     * The interval, in pixels, at which the `ParallaxLayer` is drawn repeatedly. Useful for creating
     * an infinitely scrolling background. If an axis is set to `0`, the `ParallaxLayer` will be drawn
     * only once along that direction. Note: If you want the repetition to pixel-perfect match a
     * `Texture2D` displayed by a child node, you should account for any scale applied to the texture
     * when defining this interval. For example, if you use a child `Sprite2D` scaled to `0.5` to
     * display a 600x600 texture, and want this sprite to be repeated continuously horizontally, you
     * should set the mirroring to `Vector2(300, 0)`. Note: If the length of the viewport axis is
     * bigger than twice the repeated axis size, it will not repeat infinitely, as the parallax layer
     * only draws 2 instances of the layer at any given time. The visibility window is calculated from
     * the parent `ParallaxBackground`'s position, not the layer's own position. So, if you use
     * mirroring, do not change the `ParallaxLayer` position relative to its parent. Instead, if you
     * need to adjust the background's position, set the `CanvasLayer.offset` property in the parent
     * `ParallaxBackground`. Note: Despite the name, the layer will not be mirrored, it will only be
     * repeated.
     *
     * Generated from Godot docs: ParallaxLayer.set_mirroring
     */
    fun setMirroring(mirror: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMirroringBind, handle, mirror)
    }

    /**
     * The interval, in pixels, at which the `ParallaxLayer` is drawn repeatedly. Useful for creating
     * an infinitely scrolling background. If an axis is set to `0`, the `ParallaxLayer` will be drawn
     * only once along that direction. Note: If you want the repetition to pixel-perfect match a
     * `Texture2D` displayed by a child node, you should account for any scale applied to the texture
     * when defining this interval. For example, if you use a child `Sprite2D` scaled to `0.5` to
     * display a 600x600 texture, and want this sprite to be repeated continuously horizontally, you
     * should set the mirroring to `Vector2(300, 0)`. Note: If the length of the viewport axis is
     * bigger than twice the repeated axis size, it will not repeat infinitely, as the parallax layer
     * only draws 2 instances of the layer at any given time. The visibility window is calculated from
     * the parent `ParallaxBackground`'s position, not the layer's own position. So, if you use
     * mirroring, do not change the `ParallaxLayer` position relative to its parent. Instead, if you
     * need to adjust the background's position, set the `CanvasLayer.offset` property in the parent
     * `ParallaxBackground`. Note: Despite the name, the layer will not be mirrored, it will only be
     * repeated.
     *
     * Generated from Godot docs: ParallaxLayer.get_mirroring
     */
    fun getMirroring(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMirroringBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ParallaxLayer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ParallaxLayer? =
            if (handle.address() == 0L) null else ParallaxLayer(handle)

        private const val SET_MOTION_SCALE_HASH = 743155724L
        private val setMotionScaleBind by lazy {
            ObjectCalls.getMethodBind("ParallaxLayer", "set_motion_scale", SET_MOTION_SCALE_HASH)
        }

        private const val GET_MOTION_SCALE_HASH = 3341600327L
        private val getMotionScaleBind by lazy {
            ObjectCalls.getMethodBind("ParallaxLayer", "get_motion_scale", GET_MOTION_SCALE_HASH)
        }

        private const val SET_MOTION_OFFSET_HASH = 743155724L
        private val setMotionOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParallaxLayer", "set_motion_offset", SET_MOTION_OFFSET_HASH)
        }

        private const val GET_MOTION_OFFSET_HASH = 3341600327L
        private val getMotionOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParallaxLayer", "get_motion_offset", GET_MOTION_OFFSET_HASH)
        }

        private const val SET_MIRRORING_HASH = 743155724L
        private val setMirroringBind by lazy {
            ObjectCalls.getMethodBind("ParallaxLayer", "set_mirroring", SET_MIRRORING_HASH)
        }

        private const val GET_MIRRORING_HASH = 3341600327L
        private val getMirroringBind by lazy {
            ObjectCalls.getMethodBind("ParallaxLayer", "get_mirroring", GET_MIRRORING_HASH)
        }
    }
}

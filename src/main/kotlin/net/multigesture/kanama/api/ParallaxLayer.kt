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

    fun setMotionScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMotionScaleBind, handle, scale)
    }

    fun getMotionScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMotionScaleBind, handle)
    }

    fun setMotionOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMotionOffsetBind, handle, offset)
    }

    fun getMotionOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMotionOffsetBind, handle)
    }

    fun setMirroring(mirror: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMirroringBind, handle, mirror)
    }

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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

/**
 * Base class for XR interface extensions (plugins).
 *
 * Generated from Godot docs: XRInterfaceExtension
 */
class XRInterfaceExtension(handle: MemorySegment) : XRInterface(handle) {
    fun getColorTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getColorTextureBind, handle)
    }

    fun getDepthTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getDepthTextureBind, handle)
    }

    fun getVelocityTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getVelocityTextureBind, handle)
    }

    fun addBlit(renderTarget: RID, srcRect: Rect2, dstRect: Rect2i, useLayer: Boolean, layer: Long, applyLensDistortion: Boolean, eyeCenter: Vector2, k1: Double, k2: Double, upscale: Double, aspectRatio: Double) {
        ObjectCalls.ptrcallWithRIDRect2Rect2iBoolUInt32BoolVector2FourDoubleArgs(addBlitBind, handle, renderTarget, srcRect, dstRect, useLayer, layer, applyLensDistortion, eyeCenter, k1, k2, upscale, aspectRatio)
    }

    fun getRenderTargetTexture(renderTarget: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(getRenderTargetTextureBind, handle, renderTarget)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRInterfaceExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRInterfaceExtension? =
            if (handle.address() == 0L) null else XRInterfaceExtension(handle)

        private const val GET_COLOR_TEXTURE_HASH = 529393457L
        private val getColorTextureBind by lazy {
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_color_texture", GET_COLOR_TEXTURE_HASH)
        }

        private const val GET_DEPTH_TEXTURE_HASH = 529393457L
        private val getDepthTextureBind by lazy {
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_depth_texture", GET_DEPTH_TEXTURE_HASH)
        }

        private const val GET_VELOCITY_TEXTURE_HASH = 529393457L
        private val getVelocityTextureBind by lazy {
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_velocity_texture", GET_VELOCITY_TEXTURE_HASH)
        }

        private const val ADD_BLIT_HASH = 258596971L
        private val addBlitBind by lazy {
            ObjectCalls.getMethodBind("XRInterfaceExtension", "add_blit", ADD_BLIT_HASH)
        }

        private const val GET_RENDER_TARGET_TEXTURE_HASH = 41030802L
        private val getRenderTargetTextureBind by lazy {
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_render_target_texture", GET_RENDER_TARGET_TEXTURE_HASH)
        }
    }
}

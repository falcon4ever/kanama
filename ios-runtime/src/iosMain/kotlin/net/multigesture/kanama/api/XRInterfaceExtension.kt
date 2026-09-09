package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: XRInterfaceExtension
 */
class XRInterfaceExtension(handle: MemorySegment) : XRInterface(handle) {
    fun getColorTexture(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getColorTextureBind, handle)
    }

    fun getDepthTexture(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getDepthTextureBind, handle)
    }

    fun getVelocityTexture(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getVelocityTextureBind, handle)
    }

    fun getRenderTargetTexture(renderTarget: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(getRenderTargetTextureBind, handle, renderTarget)
    }

    companion object {
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

        private const val GET_RENDER_TARGET_TEXTURE_HASH = 41030802L
        private val getRenderTargetTextureBind by lazy {
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_render_target_texture", GET_RENDER_TARGET_TEXTURE_HASH)
        }
    }
}

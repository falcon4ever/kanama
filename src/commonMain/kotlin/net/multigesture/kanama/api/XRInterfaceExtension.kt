package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

/**
 * Base class for XR interface extensions (plugins).
 *
 * Generated from Godot docs: XRInterfaceExtension
 */
class XRInterfaceExtension(handle: GodotHandle) : XRInterface(handle) {
    fun getColorTexture(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getColorTextureBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun getDepthTexture(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getDepthTextureBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun getVelocityTexture(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getVelocityTextureBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Blits our render results to screen optionally applying lens distortion. This can only be called
     * while processing `_commit_views`.
     *
     * Generated from Godot docs: XRInterfaceExtension.add_blit
     */
    fun addBlit(renderTarget: RID, srcRect: Rect2, dstRect: Rect2i, useLayer: Boolean, layer: Long, applyLensDistortion: Boolean, eyeCenter: Vector2, k1: Double, k2: Double, upscale: Double, aspectRatio: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDRect2Rect2iBoolUInt32BoolVector2FourDoubleArgs(Binds.addBlitBind, segment, renderTarget, srcRect, dstRect, useLayer, layer, applyLensDistortion, eyeCenter, k1, k2, upscale, aspectRatio)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns a valid `RID` for a texture to which we should render the current frame if supported by
     * the interface.
     *
     * Generated from Godot docs: XRInterfaceExtension.get_render_target_texture
     */
    fun getRenderTargetTexture(renderTarget: RID): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithRIDArgRetRID(Binds.getRenderTargetTextureBind, segment, renderTarget)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRInterfaceExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XRInterfaceExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(XRInterfaceExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XRInterfaceExtension? =
            if (handle.address() == 0L) null else XRInterfaceExtension(GodotHandle(handle))

        /** A new XRInterfaceExtension, GDScript's `XRInterfaceExtension.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): XRInterfaceExtension =
            RefCounted.owned(XRInterfaceExtension(GodotHandle(ObjectCalls.constructObject("XRInterfaceExtension"))))
    }

    private object Binds {
        private const val GET_COLOR_TEXTURE_HASH = 529393457L
        @JvmField
        val getColorTextureBind =
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_color_texture", GET_COLOR_TEXTURE_HASH)

        private const val GET_DEPTH_TEXTURE_HASH = 529393457L
        @JvmField
        val getDepthTextureBind =
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_depth_texture", GET_DEPTH_TEXTURE_HASH)

        private const val GET_VELOCITY_TEXTURE_HASH = 529393457L
        @JvmField
        val getVelocityTextureBind =
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_velocity_texture", GET_VELOCITY_TEXTURE_HASH)

        private const val ADD_BLIT_HASH = 258596971L
        @JvmField
        val addBlitBind =
            ObjectCalls.getMethodBind("XRInterfaceExtension", "add_blit", ADD_BLIT_HASH)

        private const val GET_RENDER_TARGET_TEXTURE_HASH = 41030802L
        @JvmField
        val getRenderTargetTextureBind =
            ObjectCalls.getMethodBind("XRInterfaceExtension", "get_render_target_texture", GET_RENDER_TARGET_TEXTURE_HASH)
    }
}

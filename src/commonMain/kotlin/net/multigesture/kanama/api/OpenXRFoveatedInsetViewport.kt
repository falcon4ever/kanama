package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * This viewport is used for rendering our foveated inset.
 *
 * Generated from Godot docs: OpenXRFoveatedInsetViewport
 */
class OpenXRFoveatedInsetViewport(handle: GodotHandle) : SubViewport(handle) {
    /**
     * Returns the `XROrigin3D` node used for tracking the foveated inset. Our built-in logic handles
     * positioning this node. It will copy the position and orientation of the `XROrigin3D` node marked
     * as current.
     *
     * Generated from Godot docs: OpenXRFoveatedInsetViewport.get_xr_origin3d
     */
    fun getXrOrigin3d(): XROrigin3D? {
        return XROrigin3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getXrOrigin3dBind, segment))
    }

    /**
     * Returns the `XRCamera3D` node used for rendering the foveated inset.
     *
     * Generated from Godot docs: OpenXRFoveatedInsetViewport.get_xr_camera3d
     */
    fun getXrCamera3d(): XRCamera3D? {
        return XRCamera3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getXrCamera3dBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRFoveatedInsetViewport? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRFoveatedInsetViewport? =
            if (handle.address() == 0L) null else OpenXRFoveatedInsetViewport(GodotHandle(handle))

        /** A new OpenXRFoveatedInsetViewport, GDScript's `OpenXRFoveatedInsetViewport.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRFoveatedInsetViewport =
            OpenXRFoveatedInsetViewport(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRFoveatedInsetViewport"), "OpenXRFoveatedInsetViewport")))
    }

    private object Binds {
        private const val GET_XR_ORIGIN3D_HASH = 505038244L
        @JvmField
        val getXrOrigin3dBind =
            ObjectCalls.getMethodBind("OpenXRFoveatedInsetViewport", "get_xr_origin3d", GET_XR_ORIGIN3D_HASH)

        private const val GET_XR_CAMERA3D_HASH = 1715292258L
        @JvmField
        val getXrCamera3dBind =
            ObjectCalls.getMethodBind("OpenXRFoveatedInsetViewport", "get_xr_camera3d", GET_XR_CAMERA3D_HASH)
    }
}

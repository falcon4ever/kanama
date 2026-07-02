package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract scene buffers object, created for each viewport for which 3D rendering is done.
 *
 * Generated from Godot docs: RenderSceneBuffers
 */
open class RenderSceneBuffers(handle: MemorySegment) : RefCounted(handle) {
    /**
     * This method is called by the rendering server when the associated viewport's configuration is
     * changed. It will discard the old buffers and recreate the internal buffers used.
     *
     * Generated from Godot docs: RenderSceneBuffers.configure
     */
    fun configure(config: RenderSceneBuffersConfiguration?) {
        ObjectCalls.ptrcallWithObjectArgs(configureBind, handle, listOf(config?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneBuffers? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneBuffers? =
            if (handle.address() == 0L) null else RenderSceneBuffers(handle)

        private const val CONFIGURE_HASH = 3072623270L
        private val configureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffers", "configure", CONFIGURE_HASH)
        }
    }
}

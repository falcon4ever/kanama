package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract scene buffers object, created for each viewport for which 3D rendering is done.
 *
 * Generated from Godot docs: RenderSceneBuffers
 */
open class RenderSceneBuffers(handle: GodotHandle) : RefCounted(handle) {
    /**
     * This method is called by the rendering server when the associated viewport's configuration is
     * changed. It will discard the old buffers and recreate the internal buffers used.
     *
     * Generated from Godot docs: RenderSceneBuffers.configure
     */
    fun configure(config: RenderSceneBuffersConfiguration?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.configureBind, segment, listOf(config?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderSceneBuffers? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RenderSceneBuffers? =
            if (handle.address() == 0L) null else RefCounted.owned(RenderSceneBuffers(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RenderSceneBuffers? =
            if (handle.address() == 0L) null else RenderSceneBuffers(GodotHandle(handle))
    }

    private object Binds {
        private const val CONFIGURE_HASH = 3072623270L
        @JvmField
        val configureBind =
            ObjectCalls.getMethodBind("RenderSceneBuffers", "configure", CONFIGURE_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Framebuffer cache manager for Rendering Device based renderers.
 *
 * Generated from Godot docs: FramebufferCacheRD
 */
class FramebufferCacheRD(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun getCacheMultipass(textures: List<RID>, passes: List<RDFramebufferPass>, views: Long): RID {
            return ObjectCalls.ptrcallWithRIDListObjectListUInt32ArgsRetRID(getCacheMultipassBind, MemorySegment.NULL, textures, passes, views)
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): FramebufferCacheRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FramebufferCacheRD? =
            if (handle.address() == 0L) null else FramebufferCacheRD(handle)

        private const val GET_CACHE_MULTIPASS_HASH = 3437881813L
        private val getCacheMultipassBind by lazy {
            ObjectCalls.getMethodBind("FramebufferCacheRD", "get_cache_multipass", GET_CACHE_MULTIPASS_HASH)
        }
    }
}

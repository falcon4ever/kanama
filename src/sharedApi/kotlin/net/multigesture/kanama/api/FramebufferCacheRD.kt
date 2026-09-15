package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Framebuffer cache manager for Rendering Device based renderers.
 *
 * Generated from Godot docs: FramebufferCacheRD
 */
class FramebufferCacheRD(handle: GodotHandle) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        /**
         * Creates, or obtains a cached, framebuffer. `textures` lists textures accessed. `passes` defines
         * the subpasses and texture allocation, if left empty a single pass is created and textures are
         * allocated depending on their usage flags. `views` defines the number of views used when
         * rendering.
         *
         * Generated from Godot docs: FramebufferCacheRD.get_cache_multipass
         */
        fun getCacheMultipass(textures: List<RID>, passes: List<RDFramebufferPass>, views: Long): RID {
            return ObjectCalls.ptrcallWithRIDListObjectListUInt32ArgsRetRID(getCacheMultipassBind, NULL_SEGMENT, textures, passes, views)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): FramebufferCacheRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FramebufferCacheRD? =
            if (handle.address() == 0L) null else FramebufferCacheRD(GodotHandle(handle))

        private const val GET_CACHE_MULTIPASS_HASH = 3437881813L
        private val getCacheMultipassBind by lazy {
            ObjectCalls.getMethodBind("FramebufferCacheRD", "get_cache_multipass", GET_CACHE_MULTIPASS_HASH)
        }
    }
}

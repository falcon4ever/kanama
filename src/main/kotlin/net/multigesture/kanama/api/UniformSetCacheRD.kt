package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Uniform set cache manager for Rendering Device based renderers.
 *
 * Generated from Godot docs: UniformSetCacheRD
 */
class UniformSetCacheRD(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        fun getCache(shader: RID, set: Long, uniforms: List<RDUniform>): RID {
            return ObjectCalls.ptrcallWithRIDUInt32ObjectListArgsRetRID(getCacheBind, MemorySegment.NULL, shader, set, uniforms)
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): UniformSetCacheRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UniformSetCacheRD? =
            if (handle.address() == 0L) null else UniformSetCacheRD(handle)

        private const val GET_CACHE_HASH = 658571723L
        private val getCacheBind by lazy {
            ObjectCalls.getMethodBind("UniformSetCacheRD", "get_cache", GET_CACHE_HASH)
        }
    }
}

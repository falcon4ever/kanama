package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * A reference-counted holder object for a skeleton RID used in the `RenderingServer`.
 *
 * Generated from Godot docs: SkinReference
 */
class SkinReference(handle: MemorySegment) : RefCounted(handle) {
    fun getSkeleton(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSkeletonBind, handle)
    }

    fun getSkin(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkinReference? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkinReference? =
            if (handle.address() == 0L) null else SkinReference(handle)

        private const val GET_SKELETON_HASH = 2944877500L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("SkinReference", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val GET_SKIN_HASH = 2074563878L
        private val getSkinBind by lazy {
            ObjectCalls.getMethodBind("SkinReference", "get_skin", GET_SKIN_HASH)
        }
    }
}

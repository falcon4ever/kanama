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
    /**
     * Returns the `RID` owned by this SkinReference, as returned by `RenderingServer.skeleton_create`.
     *
     * Generated from Godot docs: SkinReference.get_skeleton
     */
    fun getSkeleton(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSkeletonBind, handle)
    }

    /**
     * Returns the `Skin` connected to this SkinReference. In the case of `MeshInstance3D` with no
     * `MeshInstance3D.skin` assigned, this will reference an internal default `Skin` owned by that
     * `MeshInstance3D`. Note that a single `Skin` may have more than one `SkinReference` in the case
     * that it is shared by meshes across multiple `Skeleton3D` nodes.
     *
     * Generated from Godot docs: SkinReference.get_skin
     */
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

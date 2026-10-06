package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * A reference-counted holder object for a skeleton RID used in the `RenderingServer`.
 *
 * Generated from Godot docs: SkinReference
 */
class SkinReference(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the `RID` owned by this SkinReference, as returned by `RenderingServer.skeleton_create`.
     *
     * Generated from Godot docs: SkinReference.get_skeleton
     */
    fun getSkeleton(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getSkeletonBind, segment)
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
        checkOpen()
        return Skin.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getSkinBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkinReference? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkinReference? =
            if (handle.address() == 0L) null else RefCounted.owned(SkinReference(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkinReference? =
            if (handle.address() == 0L) null else SkinReference(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_SKELETON_HASH = 2944877500L
        @JvmField
        val getSkeletonBind =
            ObjectCalls.getMethodBind("SkinReference", "get_skeleton", GET_SKELETON_HASH)

        private const val GET_SKIN_HASH = 2074563878L
        @JvmField
        val getSkinBind =
            ObjectCalls.getMethodBind("SkinReference", "get_skin", GET_SKIN_HASH)
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Flat plane shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: QuadOccluder3D
 */
class QuadOccluder3D(handle: GodotHandle) : Occluder3D(handle) {
    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    /**
     * The quad's size in 3D units.
     *
     * Generated from Godot docs: QuadOccluder3D.set_size
     */
    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The quad's size in 3D units.
     *
     * Generated from Godot docs: QuadOccluder3D.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): QuadOccluder3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): QuadOccluder3D? =
            if (handle.address() == 0L) null else RefCounted.owned(QuadOccluder3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): QuadOccluder3D? =
            if (handle.address() == 0L) null else QuadOccluder3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 743155724L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("QuadOccluder3D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("QuadOccluder3D", "get_size", GET_SIZE_HASH)
    }
}

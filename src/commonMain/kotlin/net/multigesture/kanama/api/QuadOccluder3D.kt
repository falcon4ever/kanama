package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
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
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, segment, size)
    }

    /**
     * The quad's size in 3D units.
     *
     * Generated from Godot docs: QuadOccluder3D.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): QuadOccluder3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): QuadOccluder3D? =
            if (handle.address() == 0L) null else QuadOccluder3D(GodotHandle(handle))

        private const val SET_SIZE_HASH = 743155724L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("QuadOccluder3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("QuadOccluder3D", "get_size", GET_SIZE_HASH)
        }
    }
}

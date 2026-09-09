package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: QuadOccluder3D
 */
class QuadOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): QuadOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): QuadOccluder3D? =
            if (handle.address() == 0L) null else QuadOccluder3D(handle)

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

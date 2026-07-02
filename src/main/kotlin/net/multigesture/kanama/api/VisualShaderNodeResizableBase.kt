package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: VisualShaderNodeResizableBase
 */
open class VisualShaderNodeResizableBase(handle: MemorySegment) : VisualShaderNode(handle) {
    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    fun setSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeResizableBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeResizableBase? =
            if (handle.address() == 0L) null else VisualShaderNodeResizableBase(handle)

        private const val SET_SIZE_HASH = 743155724L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeResizableBase", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeResizableBase", "get_size", GET_SIZE_HASH)
        }
    }
}

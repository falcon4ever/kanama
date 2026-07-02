package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeSample3D
 */
open class VisualShaderNodeSample3D(handle: MemorySegment) : VisualShaderNode(handle) {
    var source: Long
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    fun setSource(value: Long) {
        ObjectCalls.ptrcallWithLongArg(setSourceBind, handle, value)
    }

    fun getSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSourceBind, handle)
    }

    companion object {
        const val SOURCE_TEXTURE: Long = 0L
        const val SOURCE_PORT: Long = 1L
        const val SOURCE_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeSample3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeSample3D? =
            if (handle.address() == 0L) null else VisualShaderNodeSample3D(handle)

        private const val SET_SOURCE_HASH = 3315130991L
        private val setSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSample3D", "set_source", SET_SOURCE_HASH)
        }

        private const val GET_SOURCE_HASH = 1079494121L
        private val getSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSample3D", "get_source", GET_SOURCE_HASH)
        }
    }
}

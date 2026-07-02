package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRCompositionLayerQuad
 */
class OpenXRCompositionLayerQuad(handle: MemorySegment) : OpenXRCompositionLayer(handle) {
    var quadSize: Vector2
        @JvmName("quadSizeProperty")
        get() = getQuadSize()
        @JvmName("setQuadSizeProperty")
        set(value) = setQuadSize(value)

    fun setQuadSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setQuadSizeBind, handle, size)
    }

    fun getQuadSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getQuadSizeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRCompositionLayerQuad? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRCompositionLayerQuad? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerQuad(handle)

        private const val SET_QUAD_SIZE_HASH = 743155724L
        private val setQuadSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerQuad", "set_quad_size", SET_QUAD_SIZE_HASH)
        }

        private const val GET_QUAD_SIZE_HASH = 3341600327L
        private val getQuadSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRCompositionLayerQuad", "get_quad_size", GET_QUAD_SIZE_HASH)
        }
    }
}

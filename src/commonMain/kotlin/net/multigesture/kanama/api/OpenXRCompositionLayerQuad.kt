package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRCompositionLayerQuad
 */
class OpenXRCompositionLayerQuad(handle: GodotHandle) : OpenXRCompositionLayer(handle) {
    var quadSize: Vector2
        @JvmName("quadSizeProperty")
        get() = getQuadSize()
        @JvmName("setQuadSizeProperty")
        set(value) = setQuadSize(value)

    fun setQuadSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setQuadSizeBind, segment, size)
    }

    fun getQuadSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getQuadSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRCompositionLayerQuad? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRCompositionLayerQuad? =
            if (handle.address() == 0L) null else OpenXRCompositionLayerQuad(GodotHandle(handle))

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

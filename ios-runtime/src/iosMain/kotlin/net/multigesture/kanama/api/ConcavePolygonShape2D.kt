package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: ConcavePolygonShape2D
 */
class ConcavePolygonShape2D(handle: MemorySegment) : Shape2D(handle) {
    val segments: List<Vector2>
        @JvmName("segmentsProperty")
        get() = getSegments()

    fun getSegments(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getSegmentsBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ConcavePolygonShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConcavePolygonShape2D? =
            if (handle.address() == 0L) null else ConcavePolygonShape2D(handle)

        private const val GET_SEGMENTS_HASH = 2961356807L
        private val getSegmentsBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape2D", "get_segments", GET_SEGMENTS_HASH)
        }
    }
}

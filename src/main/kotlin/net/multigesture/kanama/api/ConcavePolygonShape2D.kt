package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 2D polyline shape used for physics collision.
 *
 * Generated from Godot docs: ConcavePolygonShape2D
 */
class ConcavePolygonShape2D(handle: MemorySegment) : Shape2D(handle) {
    var segments: List<Vector2>
        @JvmName("segmentsProperty")
        get() = getSegments()
        @JvmName("setSegmentsProperty")
        set(value) = setSegments(value)

    /**
     * The array of points that make up the `ConcavePolygonShape2D`'s line segments. The array (of
     * length divisible by two) is naturally divided into pairs (one pair for each segment); each pair
     * consists of the starting point of a segment and the endpoint of a segment.
     *
     * Generated from Godot docs: ConcavePolygonShape2D.set_segments
     */
    fun setSegments(segments: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setSegmentsBind, handle, segments)
    }

    /**
     * The array of points that make up the `ConcavePolygonShape2D`'s line segments. The array (of
     * length divisible by two) is naturally divided into pairs (one pair for each segment); each pair
     * consists of the starting point of a segment and the endpoint of a segment.
     *
     * Generated from Godot docs: ConcavePolygonShape2D.get_segments
     */
    fun getSegments(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getSegmentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConcavePolygonShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConcavePolygonShape2D? =
            if (handle.address() == 0L) null else ConcavePolygonShape2D(handle)

        private const val SET_SEGMENTS_HASH = 1509147220L
        private val setSegmentsBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape2D", "set_segments", SET_SEGMENTS_HASH)
        }

        private const val GET_SEGMENTS_HASH = 2961356807L
        private val getSegmentsBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape2D", "get_segments", GET_SEGMENTS_HASH)
        }
    }
}

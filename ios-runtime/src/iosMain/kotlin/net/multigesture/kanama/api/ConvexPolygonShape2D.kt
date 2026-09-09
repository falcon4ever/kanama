package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: ConvexPolygonShape2D
 */
class ConvexPolygonShape2D(handle: MemorySegment) : Shape2D(handle) {
    val points: List<Vector2>
        @JvmName("pointsProperty")
        get() = getPoints()

    fun getPoints(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPointsBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ConvexPolygonShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvexPolygonShape2D? =
            if (handle.address() == 0L) null else ConvexPolygonShape2D(handle)

        private const val GET_POINTS_HASH = 2961356807L
        private val getPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape2D", "get_points", GET_POINTS_HASH)
        }
    }
}

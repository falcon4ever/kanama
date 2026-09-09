package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: WorldBoundaryShape2D
 */
class WorldBoundaryShape2D(handle: MemorySegment) : Shape2D(handle) {
    var normal: Vector2
        @JvmName("normalProperty")
        get() = getNormal()
        @JvmName("setNormalProperty")
        set(value) = setNormal(value)

    var distance: Double
        @JvmName("distanceProperty")
        get() = getDistance()
        @JvmName("setDistanceProperty")
        set(value) = setDistance(value)

    fun setNormal(normal: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setNormalBind, handle, normal)
    }

    fun getNormal(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getNormalBind, handle)
    }

    fun setDistance(distance: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDistanceBind, handle, distance)
    }

    fun getDistance(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDistanceBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): WorldBoundaryShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WorldBoundaryShape2D? =
            if (handle.address() == 0L) null else WorldBoundaryShape2D(handle)

        private const val SET_NORMAL_HASH = 743155724L
        private val setNormalBind by lazy {
            ObjectCalls.getMethodBind("WorldBoundaryShape2D", "set_normal", SET_NORMAL_HASH)
        }

        private const val GET_NORMAL_HASH = 3341600327L
        private val getNormalBind by lazy {
            ObjectCalls.getMethodBind("WorldBoundaryShape2D", "get_normal", GET_NORMAL_HASH)
        }

        private const val SET_DISTANCE_HASH = 373806689L
        private val setDistanceBind by lazy {
            ObjectCalls.getMethodBind("WorldBoundaryShape2D", "set_distance", SET_DISTANCE_HASH)
        }

        private const val GET_DISTANCE_HASH = 1740695150L
        private val getDistanceBind by lazy {
            ObjectCalls.getMethodBind("WorldBoundaryShape2D", "get_distance", GET_DISTANCE_HASH)
        }
    }
}

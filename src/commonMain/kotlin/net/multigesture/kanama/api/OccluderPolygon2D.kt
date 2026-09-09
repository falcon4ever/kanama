package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Defines a 2D polygon for LightOccluder2D.
 *
 * Generated from Godot docs: OccluderPolygon2D
 */
class OccluderPolygon2D(handle: MemorySegment) : Resource(handle) {
    var polygonClosed: Boolean
        @JvmName("polygonClosedProperty")
        get() = isPolygonClosed()
        @JvmName("setPolygonClosedProperty")
        set(value) = setPolygonClosed(value)

    var cullMode: Long
        @JvmName("cullModeProperty")
        get() = getCullMode()
        @JvmName("setCullModeProperty")
        set(value) = setCullMode(value)

    val polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()

    fun setPolygonClosed(closed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPolygonClosedBind, handle, closed)
    }

    fun isPolygonClosed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPolygonClosedBind, handle)
    }

    /**
     * The culling mode to use.
     *
     * Generated from Godot docs: OccluderPolygon2D.set_cull_mode
     */
    fun setCullMode(cullMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCullModeBind, handle, cullMode)
    }

    /**
     * The culling mode to use.
     *
     * Generated from Godot docs: OccluderPolygon2D.get_cull_mode
     */
    fun getCullMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getCullModeBind, handle)
    }

    /**
     * A `Vector2` array with the index for polygon's vertices positions.
     *
     * Generated from Godot docs: OccluderPolygon2D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    companion object {
        const val CULL_DISABLED: Long = 0L
        const val CULL_CLOCKWISE: Long = 1L
        const val CULL_COUNTER_CLOCKWISE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OccluderPolygon2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OccluderPolygon2D? =
            if (handle.address() == 0L) null else OccluderPolygon2D(handle)

        private const val SET_CLOSED_HASH = 2586408642L
        private val setPolygonClosedBind by lazy {
            ObjectCalls.getMethodBind("OccluderPolygon2D", "set_closed", SET_CLOSED_HASH)
        }

        private const val IS_CLOSED_HASH = 36873697L
        private val isPolygonClosedBind by lazy {
            ObjectCalls.getMethodBind("OccluderPolygon2D", "is_closed", IS_CLOSED_HASH)
        }

        private const val SET_CULL_MODE_HASH = 3500863002L
        private val setCullModeBind by lazy {
            ObjectCalls.getMethodBind("OccluderPolygon2D", "set_cull_mode", SET_CULL_MODE_HASH)
        }

        private const val GET_CULL_MODE_HASH = 33931036L
        private val getCullModeBind by lazy {
            ObjectCalls.getMethodBind("OccluderPolygon2D", "get_cull_mode", GET_CULL_MODE_HASH)
        }

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("OccluderPolygon2D", "get_polygon", GET_POLYGON_HASH)
        }
    }
}

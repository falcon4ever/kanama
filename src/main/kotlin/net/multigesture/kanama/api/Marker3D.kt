package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generic 3D position hint for editing.
 *
 * Generated from Godot docs: Marker3D
 */
class Marker3D(handle: MemorySegment) : Node3D(handle) {
    var gizmoExtents: Double
        @JvmName("gizmoExtentsProperty")
        get() = getGizmoExtents()
        @JvmName("setGizmoExtentsProperty")
        set(value) = setGizmoExtents(value)

    fun setGizmoExtents(extents: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGizmoExtentsBind, handle, extents)
    }

    fun getGizmoExtents(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGizmoExtentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Marker3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Marker3D? =
            if (handle.address() == 0L) null else Marker3D(handle)

        private const val SET_GIZMO_EXTENTS_HASH = 373806689L
        private val setGizmoExtentsBind by lazy {
            ObjectCalls.getMethodBind("Marker3D", "set_gizmo_extents", SET_GIZMO_EXTENTS_HASH)
        }

        private const val GET_GIZMO_EXTENTS_HASH = 1740695150L
        private val getGizmoExtentsBind by lazy {
            ObjectCalls.getMethodBind("Marker3D", "get_gizmo_extents", GET_GIZMO_EXTENTS_HASH)
        }
    }
}

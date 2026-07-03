package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generic 2D position hint for editing.
 *
 * Generated from Godot docs: Marker2D
 */
class Marker2D(handle: MemorySegment) : Node2D(handle) {
    var gizmoExtents: Double
        @JvmName("gizmoExtentsProperty")
        get() = getGizmoExtents()
        @JvmName("setGizmoExtentsProperty")
        set(value) = setGizmoExtents(value)

    /**
     * Size of the gizmo cross that appears in the editor.
     *
     * Generated from Godot docs: Marker2D.set_gizmo_extents
     */
    fun setGizmoExtents(extents: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGizmoExtentsBind, handle, extents)
    }

    /**
     * Size of the gizmo cross that appears in the editor.
     *
     * Generated from Godot docs: Marker2D.get_gizmo_extents
     */
    fun getGizmoExtents(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGizmoExtentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Marker2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Marker2D? =
            if (handle.address() == 0L) null else Marker2D(handle)

        private const val SET_GIZMO_EXTENTS_HASH = 373806689L
        private val setGizmoExtentsBind by lazy {
            ObjectCalls.getMethodBind("Marker2D", "set_gizmo_extents", SET_GIZMO_EXTENTS_HASH)
        }

        private const val GET_GIZMO_EXTENTS_HASH = 1740695150L
        private val getGizmoExtentsBind by lazy {
            ObjectCalls.getMethodBind("Marker2D", "get_gizmo_extents", GET_GIZMO_EXTENTS_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generic 2D position hint for editing.
 *
 * Generated from Godot docs: Marker2D
 */
class Marker2D(handle: GodotHandle) : Node2D(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setGizmoExtentsBind, segment, extents)
    }

    /**
     * Size of the gizmo cross that appears in the editor.
     *
     * Generated from Godot docs: Marker2D.get_gizmo_extents
     */
    fun getGizmoExtents(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGizmoExtentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Marker2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Marker2D? =
            if (handle.address() == 0L) null else Marker2D(GodotHandle(handle))

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

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Generic 3D position hint for editing.
 *
 * Generated from Godot docs: Marker3D
 */
class Marker3D(handle: GodotHandle) : Node3D(handle) {
    var gizmoExtents: Double
        @JvmName("gizmoExtentsProperty")
        get() = getGizmoExtents()
        @JvmName("setGizmoExtentsProperty")
        set(value) = setGizmoExtents(value)

    /**
     * Size of the gizmo cross that appears in the editor.
     *
     * Generated from Godot docs: Marker3D.set_gizmo_extents
     */
    fun setGizmoExtents(extents: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGizmoExtentsBind, segment, extents)
    }

    /**
     * Size of the gizmo cross that appears in the editor.
     *
     * Generated from Godot docs: Marker3D.get_gizmo_extents
     */
    fun getGizmoExtents(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGizmoExtentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Marker3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Marker3D? =
            if (handle.address() == 0L) null else Marker3D(GodotHandle(handle))

        /** A new Marker3D, GDScript's `Marker3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): Marker3D =
            Marker3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("Marker3D"), "Marker3D")))
    }

    private object Binds {
        private const val SET_GIZMO_EXTENTS_HASH = 373806689L
        @JvmField
        val setGizmoExtentsBind =
            ObjectCalls.getMethodBind("Marker3D", "set_gizmo_extents", SET_GIZMO_EXTENTS_HASH)

        private const val GET_GIZMO_EXTENTS_HASH = 1740695150L
        @JvmField
        val getGizmoExtentsBind =
            ObjectCalls.getMethodBind("Marker3D", "get_gizmo_extents", GET_GIZMO_EXTENTS_HASH)
    }
}

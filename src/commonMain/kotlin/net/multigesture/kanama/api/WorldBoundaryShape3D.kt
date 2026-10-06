package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Plane

/**
 * A 3D world boundary (half-space) shape used for physics collision.
 *
 * Generated from Godot docs: WorldBoundaryShape3D
 */
class WorldBoundaryShape3D(handle: GodotHandle) : Shape3D(handle) {
    var plane: Plane
        @JvmName("planeProperty")
        get() = getPlane()
        @JvmName("setPlaneProperty")
        set(value) = setPlane(value)

    /**
     * The `Plane` used by the `WorldBoundaryShape3D` for collision.
     *
     * Generated from Godot docs: WorldBoundaryShape3D.set_plane
     */
    fun setPlane(plane: Plane) {
        checkOpen()
        ObjectCalls.ptrcallWithPlaneArg(Binds.setPlaneBind, segment, plane)
    }

    /**
     * The `Plane` used by the `WorldBoundaryShape3D` for collision.
     *
     * Generated from Godot docs: WorldBoundaryShape3D.get_plane
     */
    fun getPlane(): Plane {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPlane(Binds.getPlaneBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WorldBoundaryShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WorldBoundaryShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(WorldBoundaryShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WorldBoundaryShape3D? =
            if (handle.address() == 0L) null else WorldBoundaryShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PLANE_HASH = 3505987427L
        @JvmField
        val setPlaneBind =
            ObjectCalls.getMethodBind("WorldBoundaryShape3D", "set_plane", SET_PLANE_HASH)

        private const val GET_PLANE_HASH = 2753500971L
        @JvmField
        val getPlaneBind =
            ObjectCalls.getMethodBind("WorldBoundaryShape3D", "get_plane", GET_PLANE_HASH)
    }
}

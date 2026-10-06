package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 3D sphere shape used for physics collision.
 *
 * Generated from Godot docs: SphereShape3D
 */
class SphereShape3D(handle: GodotHandle) : Shape3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    /**
     * The sphere's radius. The shape's diameter is double the radius.
     *
     * Generated from Godot docs: SphereShape3D.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The sphere's radius. The shape's diameter is double the radius.
     *
     * Generated from Godot docs: SphereShape3D.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SphereShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SphereShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(SphereShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SphereShape3D? =
            if (handle.address() == 0L) null else SphereShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("SphereShape3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("SphereShape3D", "get_radius", GET_RADIUS_HASH)
    }
}

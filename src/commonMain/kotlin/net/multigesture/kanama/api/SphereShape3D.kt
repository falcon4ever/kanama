package net.multigesture.kanama.api

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
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, segment, radius)
    }

    /**
     * The sphere's radius. The shape's diameter is double the radius.
     *
     * Generated from Godot docs: SphereShape3D.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SphereShape3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SphereShape3D? =
            if (handle.address() == 0L) null else SphereShape3D(GodotHandle(handle))

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("SphereShape3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("SphereShape3D", "get_radius", GET_RADIUS_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A sphere shape collision that interacts with `SpringBoneSimulator3D`.
 *
 * Generated from Godot docs: SpringBoneCollisionSphere3D
 */
class SpringBoneCollisionSphere3D(handle: GodotHandle) : SpringBoneCollision3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var inside: Boolean
        @JvmName("insideProperty")
        get() = isInside()
        @JvmName("setInsideProperty")
        set(value) = setInside(value)

    /**
     * The sphere's radius.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The sphere's radius.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * If `true`, the collision acts to trap the joint within the collision.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.set_inside
     */
    fun setInside(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setInsideBind, segment, enabled)
    }

    /**
     * If `true`, the collision acts to trap the joint within the collision.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.is_inside
     */
    fun isInside(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isInsideBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SpringBoneCollisionSphere3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SpringBoneCollisionSphere3D? =
            if (handle.address() == 0L) null else SpringBoneCollisionSphere3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_INSIDE_HASH = 2586408642L
        @JvmField
        val setInsideBind =
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "set_inside", SET_INSIDE_HASH)

        private const val IS_INSIDE_HASH = 36873697L
        @JvmField
        val isInsideBind =
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "is_inside", IS_INSIDE_HASH)
    }
}

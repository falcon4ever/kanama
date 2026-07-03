package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A sphere shape collision that interacts with `SpringBoneSimulator3D`.
 *
 * Generated from Godot docs: SpringBoneCollisionSphere3D
 */
class SpringBoneCollisionSphere3D(handle: MemorySegment) : SpringBoneCollision3D(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * The sphere's radius.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * If `true`, the collision acts to trap the joint within the collision.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.set_inside
     */
    fun setInside(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInsideBind, handle, enabled)
    }

    /**
     * If `true`, the collision acts to trap the joint within the collision.
     *
     * Generated from Godot docs: SpringBoneCollisionSphere3D.is_inside
     */
    fun isInside(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInsideBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpringBoneCollisionSphere3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpringBoneCollisionSphere3D? =
            if (handle.address() == 0L) null else SpringBoneCollisionSphere3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_INSIDE_HASH = 2586408642L
        private val setInsideBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "set_inside", SET_INSIDE_HASH)
        }

        private const val IS_INSIDE_HASH = 36873697L
        private val isInsideBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionSphere3D", "is_inside", IS_INSIDE_HASH)
        }
    }
}

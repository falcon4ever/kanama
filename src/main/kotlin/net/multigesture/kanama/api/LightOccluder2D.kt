package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Occludes light cast by a Light2D, casting shadows.
 *
 * Generated from Godot docs: LightOccluder2D
 */
class LightOccluder2D(handle: MemorySegment) : Node2D(handle) {
    var occluder: OccluderPolygon2D?
        @JvmName("occluderProperty")
        get() = getOccluderPolygon()
        @JvmName("setOccluderProperty")
        set(value) = setOccluderPolygon(value)

    var sdfCollision: Boolean
        @JvmName("sdfCollisionProperty")
        get() = isSetAsSdfCollision()
        @JvmName("setSdfCollisionProperty")
        set(value) = setAsSdfCollision(value)

    var occluderLightMask: Int
        @JvmName("occluderLightMaskProperty")
        get() = getOccluderLightMask()
        @JvmName("setOccluderLightMaskProperty")
        set(value) = setOccluderLightMask(value)

    fun setOccluderPolygon(polygon: OccluderPolygon2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setOccluderPolygonBind, handle, listOf(polygon?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOccluderPolygon(): OccluderPolygon2D? {
        return OccluderPolygon2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOccluderPolygonBind, handle))
    }

    fun setOccluderLightMask(mask: Int) {
        ObjectCalls.ptrcallWithIntArg(setOccluderLightMaskBind, handle, mask)
    }

    fun getOccluderLightMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOccluderLightMaskBind, handle)
    }

    fun setAsSdfCollision(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsSdfCollisionBind, handle, enable)
    }

    fun isSetAsSdfCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSetAsSdfCollisionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): LightOccluder2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LightOccluder2D? =
            if (handle.address() == 0L) null else LightOccluder2D(handle)

        private const val SET_OCCLUDER_POLYGON_HASH = 3258315893L
        private val setOccluderPolygonBind by lazy {
            ObjectCalls.getMethodBind("LightOccluder2D", "set_occluder_polygon", SET_OCCLUDER_POLYGON_HASH)
        }

        private const val GET_OCCLUDER_POLYGON_HASH = 3962317075L
        private val getOccluderPolygonBind by lazy {
            ObjectCalls.getMethodBind("LightOccluder2D", "get_occluder_polygon", GET_OCCLUDER_POLYGON_HASH)
        }

        private const val SET_OCCLUDER_LIGHT_MASK_HASH = 1286410249L
        private val setOccluderLightMaskBind by lazy {
            ObjectCalls.getMethodBind("LightOccluder2D", "set_occluder_light_mask", SET_OCCLUDER_LIGHT_MASK_HASH)
        }

        private const val GET_OCCLUDER_LIGHT_MASK_HASH = 3905245786L
        private val getOccluderLightMaskBind by lazy {
            ObjectCalls.getMethodBind("LightOccluder2D", "get_occluder_light_mask", GET_OCCLUDER_LIGHT_MASK_HASH)
        }

        private const val SET_AS_SDF_COLLISION_HASH = 2586408642L
        private val setAsSdfCollisionBind by lazy {
            ObjectCalls.getMethodBind("LightOccluder2D", "set_as_sdf_collision", SET_AS_SDF_COLLISION_HASH)
        }

        private const val IS_SET_AS_SDF_COLLISION_HASH = 36873697L
        private val isSetAsSdfCollisionBind by lazy {
            ObjectCalls.getMethodBind("LightOccluder2D", "is_set_as_sdf_collision", IS_SET_AS_SDF_COLLISION_HASH)
        }
    }
}

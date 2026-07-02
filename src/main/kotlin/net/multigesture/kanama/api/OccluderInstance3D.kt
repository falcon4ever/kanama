package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides occlusion culling for 3D nodes, which improves performance in closed areas.
 *
 * Generated from Godot docs: OccluderInstance3D
 */
class OccluderInstance3D(handle: MemorySegment) : VisualInstance3D(handle) {
    var occluder: Occluder3D?
        @JvmName("occluderProperty")
        get() = getOccluder()
        @JvmName("setOccluderProperty")
        set(value) = setOccluder(value)

    var bakeMask: Long
        @JvmName("bakeMaskProperty")
        get() = getBakeMask()
        @JvmName("setBakeMaskProperty")
        set(value) = setBakeMask(value)

    var bakeSimplificationDistance: Double
        @JvmName("bakeSimplificationDistanceProperty")
        get() = getBakeSimplificationDistance()
        @JvmName("setBakeSimplificationDistanceProperty")
        set(value) = setBakeSimplificationDistance(value)

    fun setBakeMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBakeMaskBind, handle, mask)
    }

    fun getBakeMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBakeMaskBind, handle)
    }

    fun setBakeMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBakeMaskValueBind, handle, layerNumber, value)
    }

    fun getBakeMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getBakeMaskValueBind, handle, layerNumber)
    }

    fun setBakeSimplificationDistance(simplificationDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBakeSimplificationDistanceBind, handle, simplificationDistance)
    }

    fun getBakeSimplificationDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeSimplificationDistanceBind, handle)
    }

    fun setOccluder(occluder: Occluder3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setOccluderBind, handle, listOf(occluder?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getOccluder(): Occluder3D? {
        return Occluder3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOccluderBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OccluderInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OccluderInstance3D? =
            if (handle.address() == 0L) null else OccluderInstance3D(handle)

        private const val SET_BAKE_MASK_HASH = 1286410249L
        private val setBakeMaskBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "set_bake_mask", SET_BAKE_MASK_HASH)
        }

        private const val GET_BAKE_MASK_HASH = 3905245786L
        private val getBakeMaskBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "get_bake_mask", GET_BAKE_MASK_HASH)
        }

        private const val SET_BAKE_MASK_VALUE_HASH = 300928843L
        private val setBakeMaskValueBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "set_bake_mask_value", SET_BAKE_MASK_VALUE_HASH)
        }

        private const val GET_BAKE_MASK_VALUE_HASH = 1116898809L
        private val getBakeMaskValueBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "get_bake_mask_value", GET_BAKE_MASK_VALUE_HASH)
        }

        private const val SET_BAKE_SIMPLIFICATION_DISTANCE_HASH = 373806689L
        private val setBakeSimplificationDistanceBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "set_bake_simplification_distance", SET_BAKE_SIMPLIFICATION_DISTANCE_HASH)
        }

        private const val GET_BAKE_SIMPLIFICATION_DISTANCE_HASH = 1740695150L
        private val getBakeSimplificationDistanceBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "get_bake_simplification_distance", GET_BAKE_SIMPLIFICATION_DISTANCE_HASH)
        }

        private const val SET_OCCLUDER_HASH = 1664878165L
        private val setOccluderBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "set_occluder", SET_OCCLUDER_HASH)
        }

        private const val GET_OCCLUDER_HASH = 1696836198L
        private val getOccluderBind by lazy {
            ObjectCalls.getMethodBind("OccluderInstance3D", "get_occluder", GET_OCCLUDER_HASH)
        }
    }
}

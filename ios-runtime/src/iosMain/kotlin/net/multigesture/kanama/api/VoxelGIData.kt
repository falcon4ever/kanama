package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: VoxelGIData
 */
class VoxelGIData(handle: MemorySegment) : Resource(handle) {
    var dynamicRange: Double
        @JvmName("dynamicRangeProperty")
        get() = getDynamicRange()
        @JvmName("setDynamicRangeProperty")
        set(value) = setDynamicRange(value)

    var energy: Double
        @JvmName("energyProperty")
        get() = getEnergy()
        @JvmName("setEnergyProperty")
        set(value) = setEnergy(value)

    var bias: Double
        @JvmName("biasProperty")
        get() = getBias()
        @JvmName("setBiasProperty")
        set(value) = setBias(value)

    var normalBias: Double
        @JvmName("normalBiasProperty")
        get() = getNormalBias()
        @JvmName("setNormalBiasProperty")
        set(value) = setNormalBias(value)

    var propagation: Double
        @JvmName("propagationProperty")
        get() = getPropagation()
        @JvmName("setPropagationProperty")
        set(value) = setPropagation(value)

    var useTwoBounces: Boolean
        @JvmName("useTwoBouncesProperty")
        get() = isUsingTwoBounces()
        @JvmName("setUseTwoBouncesProperty")
        set(value) = setUseTwoBounces(value)

    var interior: Boolean
        @JvmName("interiorProperty")
        get() = isInterior()
        @JvmName("setInteriorProperty")
        set(value) = setInterior(value)

    fun getBounds(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getBoundsBind, handle)
    }

    fun getOctreeSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getOctreeSizeBind, handle)
    }

    fun getToCellXform(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getToCellXformBind, handle)
    }

    fun getLevelCounts(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getLevelCountsBind, handle)
    }

    fun setDynamicRange(dynamicRange: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDynamicRangeBind, handle, dynamicRange)
    }

    fun getDynamicRange(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDynamicRangeBind, handle)
    }

    fun setEnergy(energy: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setEnergyBind, handle, energy)
    }

    fun getEnergy(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyBind, handle)
    }

    fun setBias(bias: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBiasBind, handle, bias)
    }

    fun getBias(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBiasBind, handle)
    }

    fun setNormalBias(bias: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setNormalBiasBind, handle, bias)
    }

    fun getNormalBias(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getNormalBiasBind, handle)
    }

    fun setPropagation(propagation: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPropagationBind, handle, propagation)
    }

    fun getPropagation(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPropagationBind, handle)
    }

    fun setInterior(interior: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setInteriorBind, handle, interior)
    }

    fun isInterior(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isInteriorBind, handle)
    }

    fun setUseTwoBounces(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseTwoBouncesBind, handle, enable)
    }

    fun isUsingTwoBounces(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTwoBouncesBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): VoxelGIData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VoxelGIData? =
            if (handle.address() == 0L) null else VoxelGIData(handle)

        private const val GET_BOUNDS_HASH = 1068685055L
        private val getBoundsBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_bounds", GET_BOUNDS_HASH)
        }

        private const val GET_OCTREE_SIZE_HASH = 3360562783L
        private val getOctreeSizeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_octree_size", GET_OCTREE_SIZE_HASH)
        }

        private const val GET_TO_CELL_XFORM_HASH = 3229777777L
        private val getToCellXformBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_to_cell_xform", GET_TO_CELL_XFORM_HASH)
        }

        private const val GET_LEVEL_COUNTS_HASH = 1930428628L
        private val getLevelCountsBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_level_counts", GET_LEVEL_COUNTS_HASH)
        }

        private const val SET_DYNAMIC_RANGE_HASH = 373806689L
        private val setDynamicRangeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_dynamic_range", SET_DYNAMIC_RANGE_HASH)
        }

        private const val GET_DYNAMIC_RANGE_HASH = 1740695150L
        private val getDynamicRangeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_dynamic_range", GET_DYNAMIC_RANGE_HASH)
        }

        private const val SET_ENERGY_HASH = 373806689L
        private val setEnergyBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_energy", SET_ENERGY_HASH)
        }

        private const val GET_ENERGY_HASH = 1740695150L
        private val getEnergyBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_energy", GET_ENERGY_HASH)
        }

        private const val SET_BIAS_HASH = 373806689L
        private val setBiasBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_bias", SET_BIAS_HASH)
        }

        private const val GET_BIAS_HASH = 1740695150L
        private val getBiasBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_bias", GET_BIAS_HASH)
        }

        private const val SET_NORMAL_BIAS_HASH = 373806689L
        private val setNormalBiasBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_normal_bias", SET_NORMAL_BIAS_HASH)
        }

        private const val GET_NORMAL_BIAS_HASH = 1740695150L
        private val getNormalBiasBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_normal_bias", GET_NORMAL_BIAS_HASH)
        }

        private const val SET_PROPAGATION_HASH = 373806689L
        private val setPropagationBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_propagation", SET_PROPAGATION_HASH)
        }

        private const val GET_PROPAGATION_HASH = 1740695150L
        private val getPropagationBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_propagation", GET_PROPAGATION_HASH)
        }

        private const val SET_INTERIOR_HASH = 2586408642L
        private val setInteriorBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_interior", SET_INTERIOR_HASH)
        }

        private const val IS_INTERIOR_HASH = 36873697L
        private val isInteriorBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "is_interior", IS_INTERIOR_HASH)
        }

        private const val SET_USE_TWO_BOUNCES_HASH = 2586408642L
        private val setUseTwoBouncesBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "set_use_two_bounces", SET_USE_TWO_BOUNCES_HASH)
        }

        private const val IS_USING_TWO_BOUNCES_HASH = 36873697L
        private val isUsingTwoBouncesBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "is_using_two_bounces", IS_USING_TWO_BOUNCES_HASH)
        }
    }
}

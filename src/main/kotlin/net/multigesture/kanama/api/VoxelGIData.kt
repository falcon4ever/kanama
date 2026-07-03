package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Contains baked voxel global illumination data for use in a `VoxelGI` node.
 *
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

    /**
     * Initializes this `VoxelGIData` with the specified data. `octree_cells` must be a multiple of 32.
     * `octree_cells` must be double the size of `data_cells`. The allocated data can be retrieved
     * later using the various getter methods.
     *
     * Generated from Godot docs: VoxelGIData.allocate
     */
    fun allocate(toCellXform: Transform3D, aabb: AABB, octreeSize: Vector3, octreeCells: ByteArray, dataCells: ByteArray, distanceField: ByteArray, levelCounts: List<Int>) {
        ObjectCalls.ptrcallWithTransform3DAABBVector3ThreeByteArrayPackedInt32ListArgs(allocateBind, handle, toCellXform, aabb, octreeSize, octreeCells, dataCells, distanceField, levelCounts)
    }

    /**
     * Returns the bounds of the baked voxel data as an `AABB`, which should match `VoxelGI.size` after
     * being baked (which only contains the size as a `Vector3`). Note: If the size was modified
     * without baking the VoxelGI data, then the value of `get_bounds` and `VoxelGI.size` will not
     * match.
     *
     * Generated from Godot docs: VoxelGIData.get_bounds
     */
    fun getBounds(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getBoundsBind, handle)
    }

    /**
     * Returns the baked octree size for this `VoxelGIData`, which corresponds to the number of
     * subdivisions per axis. This can be viewed in the editor by hovering the Bake VoxelGI button at
     * the top of the 3D editor viewport when a `VoxelGI` node is selected and looking at the
     * Subdivisions field in the tooltip.
     *
     * Generated from Godot docs: VoxelGIData.get_octree_size
     */
    fun getOctreeSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getOctreeSizeBind, handle)
    }

    /**
     * Returns the baked cell transform for this `VoxelGIData`.
     *
     * Generated from Godot docs: VoxelGIData.get_to_cell_xform
     */
    fun getToCellXform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getToCellXformBind, handle)
    }

    /**
     * Returns the baked octree cell data for this `VoxelGIData`.
     *
     * Generated from Godot docs: VoxelGIData.get_octree_cells
     */
    fun getOctreeCells(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getOctreeCellsBind, handle)
    }

    /**
     * Returns the baked cell data for this `VoxelGIData`.
     *
     * Generated from Godot docs: VoxelGIData.get_data_cells
     */
    fun getDataCells(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getDataCellsBind, handle)
    }

    /**
     * Returns the baked level counts for this `VoxelGIData`.
     *
     * Generated from Godot docs: VoxelGIData.get_level_counts
     */
    fun getLevelCounts(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getLevelCountsBind, handle)
    }

    /**
     * The dynamic range to use (`1.0` represents a low dynamic range scene brightness). Higher values
     * can be used to provide brighter indirect lighting, at the cost of more visible color banding in
     * dark areas (both in indirect lighting and reflections). To avoid color banding, it's recommended
     * to use the lowest value that does not result in visible light clipping.
     *
     * Generated from Godot docs: VoxelGIData.set_dynamic_range
     */
    fun setDynamicRange(dynamicRange: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDynamicRangeBind, handle, dynamicRange)
    }

    /**
     * The dynamic range to use (`1.0` represents a low dynamic range scene brightness). Higher values
     * can be used to provide brighter indirect lighting, at the cost of more visible color banding in
     * dark areas (both in indirect lighting and reflections). To avoid color banding, it's recommended
     * to use the lowest value that does not result in visible light clipping.
     *
     * Generated from Godot docs: VoxelGIData.get_dynamic_range
     */
    fun getDynamicRange(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDynamicRangeBind, handle)
    }

    /**
     * The energy of the indirect lighting and reflections produced by the `VoxelGI` node. Higher
     * values result in brighter indirect lighting. If indirect lighting looks too flat, try decreasing
     * `propagation` while increasing `energy` at the same time. See also `use_two_bounces` which
     * influences the indirect lighting's effective brightness.
     *
     * Generated from Godot docs: VoxelGIData.set_energy
     */
    fun setEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnergyBind, handle, energy)
    }

    /**
     * The energy of the indirect lighting and reflections produced by the `VoxelGI` node. Higher
     * values result in brighter indirect lighting. If indirect lighting looks too flat, try decreasing
     * `propagation` while increasing `energy` at the same time. See also `use_two_bounces` which
     * influences the indirect lighting's effective brightness.
     *
     * Generated from Godot docs: VoxelGIData.get_energy
     */
    fun getEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyBind, handle)
    }

    /**
     * The normal bias to use for indirect lighting and reflections. Higher values reduce
     * self-reflections visible in non-rough materials, at the cost of more visible light leaking and
     * flatter-looking indirect lighting. To prioritize hiding self-reflections over lighting quality,
     * set `bias` to `0.0` and `normal_bias` to a value between `1.0` and `2.0`.
     *
     * Generated from Godot docs: VoxelGIData.set_bias
     */
    fun setBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBiasBind, handle, bias)
    }

    /**
     * The normal bias to use for indirect lighting and reflections. Higher values reduce
     * self-reflections visible in non-rough materials, at the cost of more visible light leaking and
     * flatter-looking indirect lighting. To prioritize hiding self-reflections over lighting quality,
     * set `bias` to `0.0` and `normal_bias` to a value between `1.0` and `2.0`.
     *
     * Generated from Godot docs: VoxelGIData.get_bias
     */
    fun getBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBiasBind, handle)
    }

    /**
     * The normal bias to use for indirect lighting and reflections. Higher values reduce
     * self-reflections visible in non-rough materials, at the cost of more visible light leaking and
     * flatter-looking indirect lighting. See also `bias`. To prioritize hiding self-reflections over
     * lighting quality, set `bias` to `0.0` and `normal_bias` to a value between `1.0` and `2.0`.
     *
     * Generated from Godot docs: VoxelGIData.set_normal_bias
     */
    fun setNormalBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNormalBiasBind, handle, bias)
    }

    /**
     * The normal bias to use for indirect lighting and reflections. Higher values reduce
     * self-reflections visible in non-rough materials, at the cost of more visible light leaking and
     * flatter-looking indirect lighting. See also `bias`. To prioritize hiding self-reflections over
     * lighting quality, set `bias` to `0.0` and `normal_bias` to a value between `1.0` and `2.0`.
     *
     * Generated from Godot docs: VoxelGIData.get_normal_bias
     */
    fun getNormalBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNormalBiasBind, handle)
    }

    /**
     * The multiplier to use when light bounces off a surface. Higher values result in brighter
     * indirect lighting. If indirect lighting looks too flat, try decreasing `propagation` while
     * increasing `energy` at the same time. See also `use_two_bounces` which influences the indirect
     * lighting's effective brightness.
     *
     * Generated from Godot docs: VoxelGIData.set_propagation
     */
    fun setPropagation(propagation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPropagationBind, handle, propagation)
    }

    /**
     * The multiplier to use when light bounces off a surface. Higher values result in brighter
     * indirect lighting. If indirect lighting looks too flat, try decreasing `propagation` while
     * increasing `energy` at the same time. See also `use_two_bounces` which influences the indirect
     * lighting's effective brightness.
     *
     * Generated from Godot docs: VoxelGIData.get_propagation
     */
    fun getPropagation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPropagationBind, handle)
    }

    /**
     * If `true`, `Environment` lighting is ignored by the `VoxelGI` node. If `false`, `Environment`
     * lighting is taken into account by the `VoxelGI` node. `Environment` lighting updates in
     * real-time, which means it can be changed without having to bake the `VoxelGI` node again.
     *
     * Generated from Godot docs: VoxelGIData.set_interior
     */
    fun setInterior(interior: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInteriorBind, handle, interior)
    }

    /**
     * If `true`, `Environment` lighting is ignored by the `VoxelGI` node. If `false`, `Environment`
     * lighting is taken into account by the `VoxelGI` node. `Environment` lighting updates in
     * real-time, which means it can be changed without having to bake the `VoxelGI` node again.
     *
     * Generated from Godot docs: VoxelGIData.is_interior
     */
    fun isInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInteriorBind, handle)
    }

    /**
     * If `true`, performs two bounces of indirect lighting instead of one. This makes indirect
     * lighting look more natural and brighter at a small performance cost. The second bounce is also
     * visible in reflections. If the scene appears too bright after enabling `use_two_bounces`, adjust
     * `propagation` and `energy`.
     *
     * Generated from Godot docs: VoxelGIData.set_use_two_bounces
     */
    fun setUseTwoBounces(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTwoBouncesBind, handle, enable)
    }

    /**
     * If `true`, performs two bounces of indirect lighting instead of one. This makes indirect
     * lighting look more natural and brighter at a small performance cost. The second bounce is also
     * visible in reflections. If the scene appears too bright after enabling `use_two_bounces`, adjust
     * `propagation` and `energy`.
     *
     * Generated from Godot docs: VoxelGIData.is_using_two_bounces
     */
    fun isUsingTwoBounces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTwoBouncesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VoxelGIData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VoxelGIData? =
            if (handle.address() == 0L) null else VoxelGIData(handle)

        private const val ALLOCATE_HASH = 4041601946L
        private val allocateBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "allocate", ALLOCATE_HASH)
        }

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

        private const val GET_OCTREE_CELLS_HASH = 2362200018L
        private val getOctreeCellsBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_octree_cells", GET_OCTREE_CELLS_HASH)
        }

        private const val GET_DATA_CELLS_HASH = 2362200018L
        private val getDataCellsBind by lazy {
            ObjectCalls.getMethodBind("VoxelGIData", "get_data_cells", GET_DATA_CELLS_HASH)
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

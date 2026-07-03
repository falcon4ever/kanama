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

    /**
     * The visual layers to account for when baking for occluders. Only `MeshInstance3D`s whose
     * `VisualInstance3D.layers` match with this `bake_mask` will be included in the generated occluder
     * mesh. By default, all objects with opaque materials are taken into account for the occluder
     * baking. To improve performance and avoid artifacts, it is recommended to exclude dynamic
     * objects, small objects and fixtures from the baking process by moving them to a separate visual
     * layer and excluding this layer in `bake_mask`.
     *
     * Generated from Godot docs: OccluderInstance3D.set_bake_mask
     */
    fun setBakeMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBakeMaskBind, handle, mask)
    }

    /**
     * The visual layers to account for when baking for occluders. Only `MeshInstance3D`s whose
     * `VisualInstance3D.layers` match with this `bake_mask` will be included in the generated occluder
     * mesh. By default, all objects with opaque materials are taken into account for the occluder
     * baking. To improve performance and avoid artifacts, it is recommended to exclude dynamic
     * objects, small objects and fixtures from the baking process by moving them to a separate visual
     * layer and excluding this layer in `bake_mask`.
     *
     * Generated from Godot docs: OccluderInstance3D.get_bake_mask
     */
    fun getBakeMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBakeMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `bake_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: OccluderInstance3D.set_bake_mask_value
     */
    fun setBakeMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBakeMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `bake_mask` is enabled, given a `layer_number`
     * between 1 and 32.
     *
     * Generated from Godot docs: OccluderInstance3D.get_bake_mask_value
     */
    fun getBakeMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getBakeMaskValueBind, handle, layerNumber)
    }

    /**
     * The simplification distance to use for simplifying the generated occluder polygon (in 3D units).
     * Higher values result in a less detailed occluder mesh, which improves performance but reduces
     * culling accuracy. The occluder geometry is rendered on the CPU, so it is important to keep its
     * geometry as simple as possible. Since the buffer is rendered at a low resolution, less detailed
     * occluder meshes generally still work well. The default value is fairly aggressive, so you may
     * have to decrease it if you run into false negatives (objects being occluded even though they are
     * visible by the camera). A value of `0.01` will act conservatively, and will keep geometry
     * perceptually unaffected in the occlusion culling buffer. Depending on the scene, a value of
     * `0.01` may still simplify the mesh noticeably compared to disabling simplification entirely.
     * Setting this to `0.0` disables simplification entirely, but vertices in the exact same position
     * will still be merged. The mesh will also be re-indexed to reduce both the number of vertices and
     * indices. Note: This uses the meshoptimizer (https://meshoptimizer.org/) library under the hood,
     * similar to LOD generation.
     *
     * Generated from Godot docs: OccluderInstance3D.set_bake_simplification_distance
     */
    fun setBakeSimplificationDistance(simplificationDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBakeSimplificationDistanceBind, handle, simplificationDistance)
    }

    /**
     * The simplification distance to use for simplifying the generated occluder polygon (in 3D units).
     * Higher values result in a less detailed occluder mesh, which improves performance but reduces
     * culling accuracy. The occluder geometry is rendered on the CPU, so it is important to keep its
     * geometry as simple as possible. Since the buffer is rendered at a low resolution, less detailed
     * occluder meshes generally still work well. The default value is fairly aggressive, so you may
     * have to decrease it if you run into false negatives (objects being occluded even though they are
     * visible by the camera). A value of `0.01` will act conservatively, and will keep geometry
     * perceptually unaffected in the occlusion culling buffer. Depending on the scene, a value of
     * `0.01` may still simplify the mesh noticeably compared to disabling simplification entirely.
     * Setting this to `0.0` disables simplification entirely, but vertices in the exact same position
     * will still be merged. The mesh will also be re-indexed to reduce both the number of vertices and
     * indices. Note: This uses the meshoptimizer (https://meshoptimizer.org/) library under the hood,
     * similar to LOD generation.
     *
     * Generated from Godot docs: OccluderInstance3D.get_bake_simplification_distance
     */
    fun getBakeSimplificationDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeSimplificationDistanceBind, handle)
    }

    /**
     * The occluder resource for this `OccluderInstance3D`. You can generate an occluder resource by
     * selecting an `OccluderInstance3D` node then using the Bake Occluders button at the top of the
     * editor. You can also draw your own 2D occluder polygon by adding a new `PolygonOccluder3D`
     * resource to the `occluder` property in the Inspector. Alternatively, you can select a primitive
     * occluder to use: `QuadOccluder3D`, `BoxOccluder3D` or `SphereOccluder3D`.
     *
     * Generated from Godot docs: OccluderInstance3D.set_occluder
     */
    fun setOccluder(occluder: Occluder3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setOccluderBind, handle, listOf(occluder?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The occluder resource for this `OccluderInstance3D`. You can generate an occluder resource by
     * selecting an `OccluderInstance3D` node then using the Bake Occluders button at the top of the
     * editor. You can also draw your own 2D occluder polygon by adding a new `PolygonOccluder3D`
     * resource to the `occluder` property in the Inspector. Alternatively, you can select a primitive
     * occluder to use: `QuadOccluder3D`, `BoxOccluder3D` or `SphereOccluder3D`.
     *
     * Generated from Godot docs: OccluderInstance3D.get_occluder
     */
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

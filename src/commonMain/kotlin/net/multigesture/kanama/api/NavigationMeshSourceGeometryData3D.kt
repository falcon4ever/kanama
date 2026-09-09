package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D

/**
 * Container for parsed source geometry data used in navigation mesh baking.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D
 */
class NavigationMeshSourceGeometryData3D(handle: MemorySegment) : Resource(handle) {
    var vertices: List<Float>
        @JvmName("verticesProperty")
        get() = getVertices()
        @JvmName("setVerticesProperty")
        set(value) = setVertices(value)

    val indices: List<Int>
        @JvmName("indicesProperty")
        get() = getIndices()

    val projectedObstructions: List<Any?>
        @JvmName("projectedObstructionsProperty")
        get() = getProjectedObstructions()

    /**
     * Sets the parsed source geometry data vertices. The vertices need to be matched with appropriated
     * indices. Warning: Inappropriate data can crash the baking process of the involved third-party
     * libraries.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.set_vertices
     */
    fun setVertices(vertices: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setVerticesBind, handle, vertices)
    }

    /**
     * Returns the parsed source geometry data vertices array.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.get_vertices
     */
    fun getVertices(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getVerticesBind, handle)
    }

    /**
     * Returns the parsed source geometry data indices array.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.get_indices
     */
    fun getIndices(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, handle)
    }

    /**
     * Clears the internal data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Returns `true` when parsed source geometry data exists.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.has_data
     */
    fun hasData(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasDataBind, handle)
    }

    /**
     * Adds the geometry data of a `Mesh` resource to the navigation mesh baking data. The mesh must
     * have valid triangulated mesh data to be considered. Since `NavigationMesh` resources have no
     * transform, all vertex positions need to be offset by the node's transform using `xform`.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.add_mesh
     */
    fun addMesh(mesh: Mesh?, xform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndTransform3DArg(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, xform)
    }

    /**
     * Adds the geometry data of another `NavigationMeshSourceGeometryData3D` to the navigation mesh
     * baking data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.merge
     */
    fun merge(otherGeometry: NavigationMeshSourceGeometryData3D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(mergeBind, handle, listOf(otherGeometry?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Clears all projected obstructions.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.clear_projected_obstructions
     */
    fun clearProjectedObstructions() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearProjectedObstructionsBind, handle)
    }

    /**
     * Returns the projected obstructions as an `Array` of dictionaries. Each `Dictionary` contains the
     * following entries: - `vertices` - A `PackedFloat32Array` that defines the outline points of the
     * projected shape. - `elevation` - A `float` that defines the projected shape placement on the
     * y-axis. - `height` - A `float` that defines how much the projected shape is extruded along the
     * y-axis. - `carve` - A `bool` that defines how the obstacle affects the navigation mesh baking.
     * If `true` the projected shape will not be affected by addition offsets, e.g. agent radius.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.get_projected_obstructions
     */
    fun getProjectedObstructions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getProjectedObstructionsBind, handle)
    }

    /**
     * Returns an axis-aligned bounding box that covers all the stored geometry data. The bounds are
     * calculated when calling this function with the result cached until further geometry changes are
     * made.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.get_bounds
     */
    fun getBounds(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getBoundsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationMeshSourceGeometryData3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationMeshSourceGeometryData3D? =
            if (handle.address() == 0L) null else NavigationMeshSourceGeometryData3D(handle)

        private const val SET_VERTICES_HASH = 2899603908L
        private val setVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "set_vertices", SET_VERTICES_HASH)
        }

        private const val GET_VERTICES_HASH = 675695659L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "get_vertices", GET_VERTICES_HASH)
        }

        private const val GET_INDICES_HASH = 1930428628L
        private val getIndicesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "get_indices", GET_INDICES_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "clear", CLEAR_HASH)
        }

        private const val HAS_DATA_HASH = 2240911060L
        private val hasDataBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "has_data", HAS_DATA_HASH)
        }

        private const val ADD_MESH_HASH = 975462459L
        private val addMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_mesh", ADD_MESH_HASH)
        }

        private const val MERGE_HASH = 655828145L
        private val mergeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "merge", MERGE_HASH)
        }

        private const val CLEAR_PROJECTED_OBSTRUCTIONS_HASH = 3218959716L
        private val clearProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "clear_projected_obstructions", CLEAR_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val GET_PROJECTED_OBSTRUCTIONS_HASH = 3995934104L
        private val getProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "get_projected_obstructions", GET_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val GET_BOUNDS_HASH = 1021181044L
        private val getBoundsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "get_bounds", GET_BOUNDS_HASH)
        }
    }
}

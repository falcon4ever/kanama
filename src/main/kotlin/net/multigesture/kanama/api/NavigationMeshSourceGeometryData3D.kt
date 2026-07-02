package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

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

    var indices: List<Int>
        @JvmName("indicesProperty")
        get() = getIndices()
        @JvmName("setIndicesProperty")
        set(value) = setIndices(value)

    var projectedObstructions: List<Any?>
        @JvmName("projectedObstructionsProperty")
        get() = getProjectedObstructions()
        @JvmName("setProjectedObstructionsProperty")
        set(value) = setProjectedObstructions(value)

    fun setVertices(vertices: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setVerticesBind, handle, vertices)
    }

    fun getVertices(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getVerticesBind, handle)
    }

    fun setIndices(indices: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setIndicesBind, handle, indices)
    }

    fun getIndices(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, handle)
    }

    fun appendArrays(vertices: List<Float>, indices: List<Int>) {
        ObjectCalls.ptrcallWithPackedFloat32ListAndPackedInt32ListArgs(appendArraysBind, handle, vertices, indices)
    }

    /**
     * Clears the internal data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun hasData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDataBind, handle)
    }

    fun addMesh(mesh: Mesh?, xform: Transform3D) {
        ObjectCalls.ptrcallWithObjectAndTransform3DArg(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, xform)
    }

    fun addMeshArray(meshArray: List<Any?>, xform: Transform3D) {
        ObjectCalls.ptrcallWithArrayTransform3DArgs(addMeshArrayBind, handle, meshArray, xform)
    }

    fun addFaces(faces: List<Vector3>, xform: Transform3D) {
        ObjectCalls.ptrcallWithPackedVector3ListAndTransform3DArg(addFacesBind, handle, faces, xform)
    }

    /**
     * Adds the geometry data of another `NavigationMeshSourceGeometryData3D` to the navigation mesh
     * baking data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData3D.merge
     */
    fun merge(otherGeometry: NavigationMeshSourceGeometryData3D?) {
        ObjectCalls.ptrcallWithObjectArgs(mergeBind, handle, listOf(otherGeometry?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun addProjectedObstruction(vertices: List<Vector3>, elevation: Double, height: Double, carve: Boolean) {
        ObjectCalls.ptrcallWithPackedVector3ListTwoDoubleAndBoolArgs(addProjectedObstructionBind, handle, vertices, elevation, height, carve)
    }

    fun clearProjectedObstructions() {
        ObjectCalls.ptrcallNoArgs(clearProjectedObstructionsBind, handle)
    }

    fun setProjectedObstructions(projectedObstructions: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setProjectedObstructionsBind, handle, projectedObstructions)
    }

    fun getProjectedObstructions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getProjectedObstructionsBind, handle)
    }

    fun getBounds(): AABB {
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

        private const val SET_INDICES_HASH = 3614634198L
        private val setIndicesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "set_indices", SET_INDICES_HASH)
        }

        private const val GET_INDICES_HASH = 1930428628L
        private val getIndicesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "get_indices", GET_INDICES_HASH)
        }

        private const val APPEND_ARRAYS_HASH = 3117535015L
        private val appendArraysBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "append_arrays", APPEND_ARRAYS_HASH)
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

        private const val ADD_MESH_ARRAY_HASH = 4235710913L
        private val addMeshArrayBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_mesh_array", ADD_MESH_ARRAY_HASH)
        }

        private const val ADD_FACES_HASH = 1440358797L
        private val addFacesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_faces", ADD_FACES_HASH)
        }

        private const val MERGE_HASH = 655828145L
        private val mergeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "merge", MERGE_HASH)
        }

        private const val ADD_PROJECTED_OBSTRUCTION_HASH = 3351846707L
        private val addProjectedObstructionBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_projected_obstruction", ADD_PROJECTED_OBSTRUCTION_HASH)
        }

        private const val CLEAR_PROJECTED_OBSTRUCTIONS_HASH = 3218959716L
        private val clearProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "clear_projected_obstructions", CLEAR_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
        private val setProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
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

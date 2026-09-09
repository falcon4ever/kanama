package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D

/**
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

    fun setVertices(vertices: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setVerticesBind, handle, vertices)
    }

    fun getVertices(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getVerticesBind, handle)
    }

    fun getIndices(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, handle)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun hasData(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasDataBind, handle)
    }

    fun addMesh(mesh: Mesh?, xform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndTransform3DArg(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, xform)
    }

    fun merge(otherGeometry: NavigationMeshSourceGeometryData3D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(mergeBind, handle, listOf(otherGeometry?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun clearProjectedObstructions() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearProjectedObstructionsBind, handle)
    }

    fun getProjectedObstructions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getProjectedObstructionsBind, handle)
    }

    fun getBounds(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getBoundsBind, handle)
    }

    companion object {
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

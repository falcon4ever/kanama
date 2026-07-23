package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: MeshDataTool
 */
class MeshDataTool(handle: MemorySegment) : RefCounted(handle) {
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun createFromSurface(mesh: ArrayMesh?, surface: Int): Long {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetLong(createFromSurfaceBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, surface)
    }

    fun commitToSurface(mesh: ArrayMesh?, compressionFlags: Long = 0L): Long {
        return ObjectCalls.ptrcallWithObjectAndLongArgRetLong(commitToSurfaceBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, compressionFlags)
    }

    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun getVertexCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVertexCountBind, handle)
    }

    fun getEdgeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEdgeCountBind, handle)
    }

    fun getFaceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFaceCountBind, handle)
    }

    fun setVertex(idx: Int, vertex: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexBind, handle, idx, vertex)
    }

    fun getVertex(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getVertexBind, handle, idx)
    }

    fun setVertexNormal(idx: Int, normal: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexNormalBind, handle, idx, normal)
    }

    fun getVertexNormal(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getVertexNormalBind, handle, idx)
    }

    fun setVertexUv(idx: Int, uv: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setVertexUvBind, handle, idx, uv)
    }

    fun getVertexUv(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getVertexUvBind, handle, idx)
    }

    fun setVertexUv2(idx: Int, uv2: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setVertexUv2Bind, handle, idx, uv2)
    }

    fun getVertexUv2(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getVertexUv2Bind, handle, idx)
    }

    fun setVertexColor(idx: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setVertexColorBind, handle, idx, color)
    }

    fun getVertexColor(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getVertexColorBind, handle, idx)
    }

    fun getEdgeVertex(idx: Int, vertex: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getEdgeVertexBind, handle, idx, vertex)
    }

    fun getFaceVertex(idx: Int, vertex: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getFaceVertexBind, handle, idx, vertex)
    }

    fun getFaceEdge(idx: Int, edge: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getFaceEdgeBind, handle, idx, edge)
    }

    fun getFaceNormal(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getFaceNormalBind, handle, idx)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): MeshDataTool? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MeshDataTool? =
            if (handle.address() == 0L) null else MeshDataTool(handle)

        // Instantiate a MeshDataTool (RefCounted; used to read mesh vertex/face data).
        fun create(): MeshDataTool =
            MeshDataTool(MemorySegment.ofAddress(IosGodot.constructObject("MeshDataTool")))

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "clear", CLEAR_HASH)
        }

        private const val CREATE_FROM_SURFACE_HASH = 2727020678L
        private val createFromSurfaceBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "create_from_surface", CREATE_FROM_SURFACE_HASH)
        }

        private const val COMMIT_TO_SURFACE_HASH = 2021686445L
        private val commitToSurfaceBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "commit_to_surface", COMMIT_TO_SURFACE_HASH)
        }

        private const val GET_FORMAT_HASH = 3905245786L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_format", GET_FORMAT_HASH)
        }

        private const val GET_VERTEX_COUNT_HASH = 3905245786L
        private val getVertexCountBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_count", GET_VERTEX_COUNT_HASH)
        }

        private const val GET_EDGE_COUNT_HASH = 3905245786L
        private val getEdgeCountBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_count", GET_EDGE_COUNT_HASH)
        }

        private const val GET_FACE_COUNT_HASH = 3905245786L
        private val getFaceCountBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_count", GET_FACE_COUNT_HASH)
        }

        private const val SET_VERTEX_HASH = 1530502735L
        private val setVertexBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex", SET_VERTEX_HASH)
        }

        private const val GET_VERTEX_HASH = 711720468L
        private val getVertexBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex", GET_VERTEX_HASH)
        }

        private const val SET_VERTEX_NORMAL_HASH = 1530502735L
        private val setVertexNormalBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_normal", SET_VERTEX_NORMAL_HASH)
        }

        private const val GET_VERTEX_NORMAL_HASH = 711720468L
        private val getVertexNormalBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_normal", GET_VERTEX_NORMAL_HASH)
        }

        private const val SET_VERTEX_UV_HASH = 163021252L
        private val setVertexUvBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_uv", SET_VERTEX_UV_HASH)
        }

        private const val GET_VERTEX_UV_HASH = 2299179447L
        private val getVertexUvBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_uv", GET_VERTEX_UV_HASH)
        }

        private const val SET_VERTEX_UV2_HASH = 163021252L
        private val setVertexUv2Bind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_uv2", SET_VERTEX_UV2_HASH)
        }

        private const val GET_VERTEX_UV2_HASH = 2299179447L
        private val getVertexUv2Bind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_uv2", GET_VERTEX_UV2_HASH)
        }

        private const val SET_VERTEX_COLOR_HASH = 2878471219L
        private val setVertexColorBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_color", SET_VERTEX_COLOR_HASH)
        }

        private const val GET_VERTEX_COLOR_HASH = 3457211756L
        private val getVertexColorBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_color", GET_VERTEX_COLOR_HASH)
        }

        private const val GET_EDGE_VERTEX_HASH = 3175239445L
        private val getEdgeVertexBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_vertex", GET_EDGE_VERTEX_HASH)
        }

        private const val GET_FACE_VERTEX_HASH = 3175239445L
        private val getFaceVertexBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_vertex", GET_FACE_VERTEX_HASH)
        }

        private const val GET_FACE_EDGE_HASH = 3175239445L
        private val getFaceEdgeBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_edge", GET_FACE_EDGE_HASH)
        }

        private const val GET_FACE_NORMAL_HASH = 711720468L
        private val getFaceNormalBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_normal", GET_FACE_NORMAL_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_material", GET_MATERIAL_HASH)
        }
    }
}

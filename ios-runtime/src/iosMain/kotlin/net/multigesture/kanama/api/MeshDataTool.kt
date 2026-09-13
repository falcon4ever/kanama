package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: MeshDataTool
 */
class MeshDataTool(handle: GodotHandle) : RefCounted(handle) {
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, segment)
    }

    fun createFromSurface(mesh: ArrayMesh?, surface: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndIntArgRetLong(createFromSurfaceBind, segment, mesh?.requireOpenHandle() ?: MemorySegment.NULL, surface)
    }

    fun commitToSurface(mesh: ArrayMesh?, compressionFlags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndLongArgRetLong(commitToSurfaceBind, segment, mesh?.requireOpenHandle() ?: MemorySegment.NULL, compressionFlags)
    }

    fun getFormat(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, segment)
    }

    fun getVertexCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getVertexCountBind, segment)
    }

    fun getEdgeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getEdgeCountBind, segment)
    }

    fun getFaceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFaceCountBind, segment)
    }

    fun setVertex(idx: Int, vertex: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexBind, segment, idx, vertex)
    }

    fun getVertex(idx: Int): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector3(getVertexBind, segment, idx)
    }

    fun setVertexNormal(idx: Int, normal: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexNormalBind, segment, idx, normal)
    }

    fun getVertexNormal(idx: Int): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector3(getVertexNormalBind, segment, idx)
    }

    fun setVertexTangent(idx: Int, tangent: Plane) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndPlaneArg(setVertexTangentBind, segment, idx, tangent)
    }

    fun getVertexTangent(idx: Int): Plane {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPlane(getVertexTangentBind, segment, idx)
    }

    fun setVertexUv(idx: Int, uv: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(setVertexUvBind, segment, idx, uv)
    }

    fun getVertexUv(idx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(getVertexUvBind, segment, idx)
    }

    fun setVertexUv2(idx: Int, uv2: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(setVertexUv2Bind, segment, idx, uv2)
    }

    fun getVertexUv2(idx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(getVertexUv2Bind, segment, idx)
    }

    fun setVertexColor(idx: Int, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndColorArg(setVertexColorBind, segment, idx, color)
    }

    fun getVertexColor(idx: Int): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetColor(getVertexColorBind, segment, idx)
    }

    fun setVertexBones(idx: Int, bones: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndPackedInt32ListArgs(setVertexBonesBind, segment, idx, bones)
    }

    fun getVertexBones(idx: Int): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getVertexBonesBind, segment, idx)
    }

    fun setVertexWeights(idx: Int, weights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndPackedFloat32ListArgs(setVertexWeightsBind, segment, idx, weights)
    }

    fun getVertexWeights(idx: Int): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedFloat32List(getVertexWeightsBind, segment, idx)
    }

    fun setVertexMeta(idx: Int, meta: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVariantArg(setVertexMetaBind, segment, idx, meta)
    }

    fun getVertexMeta(idx: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getVertexMetaBind, segment, idx)
    }

    fun getVertexEdges(idx: Int): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getVertexEdgesBind, segment, idx)
    }

    fun getVertexFaces(idx: Int): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getVertexFacesBind, segment, idx)
    }

    fun getEdgeVertex(idx: Int, vertex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getEdgeVertexBind, segment, idx, vertex)
    }

    fun getEdgeFaces(idx: Int): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getEdgeFacesBind, segment, idx)
    }

    fun setEdgeMeta(idx: Int, meta: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVariantArg(setEdgeMetaBind, segment, idx, meta)
    }

    fun getEdgeMeta(idx: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getEdgeMetaBind, segment, idx)
    }

    fun getFaceVertex(idx: Int, vertex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getFaceVertexBind, segment, idx, vertex)
    }

    fun getFaceEdge(idx: Int, edge: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getFaceEdgeBind, segment, idx, edge)
    }

    fun setFaceMeta(idx: Int, meta: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVariantArg(setFaceMetaBind, segment, idx, meta)
    }

    fun getFaceMeta(idx: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getFaceMetaBind, segment, idx)
    }

    fun getFaceNormal(idx: Int): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector3(getFaceNormalBind, segment, idx)
    }

    fun setMaterial(material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        checkOpen()
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MeshDataTool? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): MeshDataTool? =
            if (handle.address() == 0L) null else MeshDataTool(GodotHandle(handle))

        // Instantiate a MeshDataTool (RefCounted; used to read mesh vertex/face data).
        fun create(): MeshDataTool =
            MeshDataTool(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("MeshDataTool"))))

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

        private const val SET_VERTEX_TANGENT_HASH = 1104099133L
        private val setVertexTangentBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_tangent", SET_VERTEX_TANGENT_HASH)
        }

        private const val GET_VERTEX_TANGENT_HASH = 1372055458L
        private val getVertexTangentBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_tangent", GET_VERTEX_TANGENT_HASH)
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

        private const val SET_VERTEX_BONES_HASH = 3500328261L
        private val setVertexBonesBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_bones", SET_VERTEX_BONES_HASH)
        }

        private const val GET_VERTEX_BONES_HASH = 1706082319L
        private val getVertexBonesBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_bones", GET_VERTEX_BONES_HASH)
        }

        private const val SET_VERTEX_WEIGHTS_HASH = 1345852415L
        private val setVertexWeightsBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_weights", SET_VERTEX_WEIGHTS_HASH)
        }

        private const val GET_VERTEX_WEIGHTS_HASH = 1542882410L
        private val getVertexWeightsBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_weights", GET_VERTEX_WEIGHTS_HASH)
        }

        private const val SET_VERTEX_META_HASH = 2152698145L
        private val setVertexMetaBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_meta", SET_VERTEX_META_HASH)
        }

        private const val GET_VERTEX_META_HASH = 4227898402L
        private val getVertexMetaBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_meta", GET_VERTEX_META_HASH)
        }

        private const val GET_VERTEX_EDGES_HASH = 1706082319L
        private val getVertexEdgesBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_edges", GET_VERTEX_EDGES_HASH)
        }

        private const val GET_VERTEX_FACES_HASH = 1706082319L
        private val getVertexFacesBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_faces", GET_VERTEX_FACES_HASH)
        }

        private const val GET_EDGE_VERTEX_HASH = 3175239445L
        private val getEdgeVertexBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_vertex", GET_EDGE_VERTEX_HASH)
        }

        private const val GET_EDGE_FACES_HASH = 1706082319L
        private val getEdgeFacesBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_faces", GET_EDGE_FACES_HASH)
        }

        private const val SET_EDGE_META_HASH = 2152698145L
        private val setEdgeMetaBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_edge_meta", SET_EDGE_META_HASH)
        }

        private const val GET_EDGE_META_HASH = 4227898402L
        private val getEdgeMetaBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_meta", GET_EDGE_META_HASH)
        }

        private const val GET_FACE_VERTEX_HASH = 3175239445L
        private val getFaceVertexBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_vertex", GET_FACE_VERTEX_HASH)
        }

        private const val GET_FACE_EDGE_HASH = 3175239445L
        private val getFaceEdgeBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_edge", GET_FACE_EDGE_HASH)
        }

        private const val SET_FACE_META_HASH = 2152698145L
        private val setFaceMetaBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "set_face_meta", SET_FACE_META_HASH)
        }

        private const val GET_FACE_META_HASH = 4227898402L
        private val getFaceMetaBind by lazy {
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_meta", GET_FACE_META_HASH)
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

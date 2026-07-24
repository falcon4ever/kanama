package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import java.lang.foreign.MemorySegment

/**
 * Helper tool to access and edit `Mesh` data.
 *
 * Generated from Godot docs: MeshDataTool
 */
class MeshDataTool(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Clears all data currently in MeshDataTool.
     *
     * Generated from Godot docs: MeshDataTool.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Uses specified surface of given `Mesh` to populate data for MeshDataTool. Requires `Mesh` with
     * primitive type `Mesh.PRIMITIVE_TRIANGLES`.
     *
     * Generated from Godot docs: MeshDataTool.create_from_surface
     */
    fun createFromSurface(mesh: ArrayMesh?, surface: Int): Long {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetLong(createFromSurfaceBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, surface)
    }

    /**
     * Adds a new surface to specified `Mesh` with edited data.
     *
     * Generated from Godot docs: MeshDataTool.commit_to_surface
     */
    fun commitToSurface(mesh: ArrayMesh?, compressionFlags: Long = 0L): Long {
        return ObjectCalls.ptrcallWithObjectAndLongArgRetLong(commitToSurfaceBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, compressionFlags)
    }

    /**
     * Returns the `Mesh`'s format as a combination of the `Mesh.ArrayFormat` flags. For example, a
     * mesh containing both vertices and normals would return a format of `3` because
     * `Mesh.ARRAY_FORMAT_VERTEX` is `1` and `Mesh.ARRAY_FORMAT_NORMAL` is `2`.
     *
     * Generated from Godot docs: MeshDataTool.get_format
     */
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    /**
     * Returns the total number of vertices in `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_count
     */
    fun getVertexCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVertexCountBind, handle)
    }

    /**
     * Returns the number of edges in this `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_count
     */
    fun getEdgeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEdgeCountBind, handle)
    }

    /**
     * Returns the number of faces in this `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_face_count
     */
    fun getFaceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFaceCountBind, handle)
    }

    /**
     * Sets the position of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex
     */
    fun setVertex(idx: Int, vertex: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexBind, handle, idx, vertex)
    }

    /**
     * Returns the position of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex
     */
    fun getVertex(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getVertexBind, handle, idx)
    }

    /**
     * Sets the normal of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_normal
     */
    fun setVertexNormal(idx: Int, normal: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setVertexNormalBind, handle, idx, normal)
    }

    /**
     * Returns the normal of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_normal
     */
    fun getVertexNormal(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getVertexNormalBind, handle, idx)
    }

    /**
     * Sets the tangent of the given vertex. Note: Even though `tangent` is a `Plane`, it does not
     * directly represent the tangent plane. Its `Plane.x`, `Plane.y`, and `Plane.z` represent the
     * tangent vector and `Plane.d` should be either `-1` or `1`. See also `Mesh.ARRAY_TANGENT`.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_tangent
     */
    fun setVertexTangent(idx: Int, tangent: Plane) {
        ObjectCalls.ptrcallWithIntAndPlaneArg(setVertexTangentBind, handle, idx, tangent)
    }

    /**
     * Returns the tangent of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_tangent
     */
    fun getVertexTangent(idx: Int): Plane {
        return ObjectCalls.ptrcallWithIntArgRetPlane(getVertexTangentBind, handle, idx)
    }

    /**
     * Sets the UV of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_uv
     */
    fun setVertexUv(idx: Int, uv: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setVertexUvBind, handle, idx, uv)
    }

    /**
     * Returns the UV of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_uv
     */
    fun getVertexUv(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getVertexUvBind, handle, idx)
    }

    /**
     * Sets the UV2 of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_uv2
     */
    fun setVertexUv2(idx: Int, uv2: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setVertexUv2Bind, handle, idx, uv2)
    }

    /**
     * Returns the UV2 of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_uv2
     */
    fun getVertexUv2(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getVertexUv2Bind, handle, idx)
    }

    /**
     * Sets the color of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_color
     */
    fun setVertexColor(idx: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setVertexColorBind, handle, idx, color)
    }

    /**
     * Returns the color of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_color
     */
    fun getVertexColor(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getVertexColorBind, handle, idx)
    }

    /**
     * Sets the bones of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_bones
     */
    fun setVertexBones(idx: Int, bones: List<Int>) {
        ObjectCalls.ptrcallWithIntAndPackedInt32ListArgs(setVertexBonesBind, handle, idx, bones)
    }

    /**
     * Returns the bones of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_bones
     */
    fun getVertexBones(idx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getVertexBonesBind, handle, idx)
    }

    /**
     * Sets the bone weights of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_weights
     */
    fun setVertexWeights(idx: Int, weights: List<Float>) {
        ObjectCalls.ptrcallWithIntAndPackedFloat32ListArgs(setVertexWeightsBind, handle, idx, weights)
    }

    /**
     * Returns bone weights of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_weights
     */
    fun getVertexWeights(idx: Int): List<Float> {
        return ObjectCalls.ptrcallWithIntArgRetPackedFloat32List(getVertexWeightsBind, handle, idx)
    }

    /**
     * Sets the metadata associated with the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_meta
     */
    fun setVertexMeta(idx: Int, meta: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setVertexMetaBind, handle, idx, meta)
    }

    /**
     * Returns the metadata associated with the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_meta
     */
    fun getVertexMeta(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getVertexMetaBind, handle, idx)
    }

    /**
     * Returns an array of edges that share the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_edges
     */
    fun getVertexEdges(idx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getVertexEdgesBind, handle, idx)
    }

    /**
     * Returns an array of faces that share the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_faces
     */
    fun getVertexFaces(idx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getVertexFacesBind, handle, idx)
    }

    /**
     * Returns the index of the specified `vertex` connected to the edge at index `idx`. `vertex` can
     * only be `0` or `1`, as edges are composed of two vertices.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_vertex
     */
    fun getEdgeVertex(idx: Int, vertex: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getEdgeVertexBind, handle, idx, vertex)
    }

    /**
     * Returns array of faces that touch given edge.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_faces
     */
    fun getEdgeFaces(idx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getEdgeFacesBind, handle, idx)
    }

    /**
     * Sets the metadata of the given edge.
     *
     * Generated from Godot docs: MeshDataTool.set_edge_meta
     */
    fun setEdgeMeta(idx: Int, meta: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setEdgeMetaBind, handle, idx, meta)
    }

    /**
     * Returns meta information assigned to given edge.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_meta
     */
    fun getEdgeMeta(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getEdgeMetaBind, handle, idx)
    }

    /**
     * Returns the specified vertex index of the given face. `vertex` must be either `0`, `1`, or `2`
     * because faces contain three vertices.
     *
     * Generated from Godot docs: MeshDataTool.get_face_vertex
     */
    fun getFaceVertex(idx: Int, vertex: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getFaceVertexBind, handle, idx, vertex)
    }

    /**
     * Returns the edge associated with the face at index `idx`. `edge` argument must be either `0`,
     * `1`, or `2` because a face only has three edges.
     *
     * Generated from Godot docs: MeshDataTool.get_face_edge
     */
    fun getFaceEdge(idx: Int, edge: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getFaceEdgeBind, handle, idx, edge)
    }

    /**
     * Sets the metadata of the given face.
     *
     * Generated from Godot docs: MeshDataTool.set_face_meta
     */
    fun setFaceMeta(idx: Int, meta: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setFaceMetaBind, handle, idx, meta)
    }

    /**
     * Returns the metadata associated with the given face.
     *
     * Generated from Godot docs: MeshDataTool.get_face_meta
     */
    fun getFaceMeta(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getFaceMetaBind, handle, idx)
    }

    /**
     * Calculates and returns the face normal of the given face.
     *
     * Generated from Godot docs: MeshDataTool.get_face_normal
     */
    fun getFaceNormal(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getFaceNormalBind, handle, idx)
    }

    /**
     * Sets the material to be used by newly-constructed `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the material assigned to the `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MeshDataTool? =
            wrap(handle)

        @JvmStatic
        fun create(): MeshDataTool =
            MeshDataTool(ObjectCalls.constructObject("MeshDataTool"))

        internal fun wrap(handle: MemorySegment): MeshDataTool? =
            if (handle.address() == 0L) null else MeshDataTool(handle)

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

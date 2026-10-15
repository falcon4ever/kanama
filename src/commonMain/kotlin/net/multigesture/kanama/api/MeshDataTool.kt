package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Helper tool to access and edit `Mesh` data.
 *
 * Generated from Godot docs: MeshDataTool
 */
class MeshDataTool(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Clears all data currently in MeshDataTool.
     *
     * Generated from Godot docs: MeshDataTool.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Uses specified surface of given `Mesh` to populate data for MeshDataTool. Requires `Mesh` with
     * primitive type `Mesh.PrimitiveType.TRIANGLES`.
     *
     * Generated from Godot docs: MeshDataTool.create_from_surface
     */
    fun createFromSurface(mesh: ArrayMesh?, surface: Int): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithObjectAndIntArgRetLong(Binds.createFromSurfaceBind, segment, mesh?.requireOpenHandle() ?: NULL_SEGMENT, surface))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(mesh)
        return ret
    }

    /**
     * Adds a new surface to specified `Mesh` with edited data.
     *
     * Generated from Godot docs: MeshDataTool.commit_to_surface
     */
    fun commitToSurface(mesh: ArrayMesh?, compressionFlags: Long = 0L): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithObjectAndLongArgRetLong(Binds.commitToSurfaceBind, segment, mesh?.requireOpenHandle() ?: NULL_SEGMENT, compressionFlags))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(mesh)
        return ret
    }

    /**
     * Returns the `Mesh`'s format as a combination of the `Mesh.ArrayFormat` flags. For example, a
     * mesh containing both vertices and normals would return a format of `3` because
     * `Mesh.ArrayFormat.FORMAT_VERTEX` is `1` and `Mesh.ArrayFormat.FORMAT_NORMAL` is `2`.
     *
     * Generated from Godot docs: MeshDataTool.get_format
     */
    fun getFormat(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the total number of vertices in `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_count
     */
    fun getVertexCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getVertexCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the number of edges in this `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_count
     */
    fun getEdgeCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getEdgeCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the number of faces in this `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_face_count
     */
    fun getFaceCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getFaceCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the position of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex
     */
    fun setVertex(idx: Int, vertex: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setVertexBind, segment, idx, vertex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the position of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex
     */
    fun getVertex(idx: Int): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getVertexBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the normal of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_normal
     */
    fun setVertexNormal(idx: Int, normal: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setVertexNormalBind, segment, idx, normal)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the normal of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_normal
     */
    fun getVertexNormal(idx: Int): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getVertexNormalBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the tangent of the given vertex. Note: Even though `tangent` is a `Plane`, it does not
     * directly represent the tangent plane. Its `Plane.x`, `Plane.y`, and `Plane.z` represent the
     * tangent vector and `Plane.d` should be either `-1` or `1`. See also `Mesh.ArrayType.TANGENT`.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_tangent
     */
    fun setVertexTangent(idx: Int, tangent: Plane) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndPlaneArg(Binds.setVertexTangentBind, segment, idx, tangent)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the tangent of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_tangent
     */
    fun getVertexTangent(idx: Int): Plane {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetPlane(Binds.getVertexTangentBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the UV of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_uv
     */
    fun setVertexUv(idx: Int, uv: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setVertexUvBind, segment, idx, uv)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the UV of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_uv
     */
    fun getVertexUv(idx: Int): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getVertexUvBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the UV2 of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_uv2
     */
    fun setVertexUv2(idx: Int, uv2: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setVertexUv2Bind, segment, idx, uv2)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the UV2 of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_uv2
     */
    fun getVertexUv2(idx: Int): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getVertexUv2Bind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the color of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_color
     */
    fun setVertexColor(idx: Int, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndColorArg(Binds.setVertexColorBind, segment, idx, color)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the color of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_color
     */
    fun getVertexColor(idx: Int): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetColor(Binds.getVertexColorBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the bones of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_bones
     */
    fun setVertexBones(idx: Int, bones: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndPackedInt32ListArgs(Binds.setVertexBonesBind, segment, idx, bones)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the bones of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_bones
     */
    fun getVertexBones(idx: Int): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetPackedInt32List(Binds.getVertexBonesBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the bone weights of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_weights
     */
    fun setVertexWeights(idx: Int, weights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndPackedFloat32ListArgs(Binds.setVertexWeightsBind, segment, idx, weights)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns bone weights of the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_weights
     */
    fun getVertexWeights(idx: Int): List<Float> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetPackedFloat32List(Binds.getVertexWeightsBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the metadata associated with the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.set_vertex_meta
     */
    fun setVertexMeta(idx: Int, meta: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setVertexMetaBind, segment, idx, meta)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the metadata associated with the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_meta
     */
    fun getVertexMeta(idx: Int): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getVertexMetaBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns an array of edges that share the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_edges
     */
    fun getVertexEdges(idx: Int): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetPackedInt32List(Binds.getVertexEdgesBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns an array of faces that share the given vertex.
     *
     * Generated from Godot docs: MeshDataTool.get_vertex_faces
     */
    fun getVertexFaces(idx: Int): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetPackedInt32List(Binds.getVertexFacesBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the index of the specified `vertex` connected to the edge at index `idx`. `vertex` can
     * only be `0` or `1`, as edges are composed of two vertices.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_vertex
     */
    fun getEdgeVertex(idx: Int, vertex: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getEdgeVertexBind, segment, idx, vertex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns array of faces that touch given edge.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_faces
     */
    fun getEdgeFaces(idx: Int): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetPackedInt32List(Binds.getEdgeFacesBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the metadata of the given edge.
     *
     * Generated from Godot docs: MeshDataTool.set_edge_meta
     */
    fun setEdgeMeta(idx: Int, meta: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setEdgeMetaBind, segment, idx, meta)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns meta information assigned to given edge.
     *
     * Generated from Godot docs: MeshDataTool.get_edge_meta
     */
    fun getEdgeMeta(idx: Int): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getEdgeMetaBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the specified vertex index of the given face. `vertex` must be either `0`, `1`, or `2`
     * because faces contain three vertices.
     *
     * Generated from Godot docs: MeshDataTool.get_face_vertex
     */
    fun getFaceVertex(idx: Int, vertex: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getFaceVertexBind, segment, idx, vertex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the edge associated with the face at index `idx`. `edge` argument must be either `0`,
     * `1`, or `2` because a face only has three edges.
     *
     * Generated from Godot docs: MeshDataTool.get_face_edge
     */
    fun getFaceEdge(idx: Int, edge: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getFaceEdgeBind, segment, idx, edge)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the metadata of the given face.
     *
     * Generated from Godot docs: MeshDataTool.set_face_meta
     */
    fun setFaceMeta(idx: Int, meta: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setFaceMetaBind, segment, idx, meta)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the metadata associated with the given face.
     *
     * Generated from Godot docs: MeshDataTool.get_face_meta
     */
    fun getFaceMeta(idx: Int): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getFaceMetaBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Calculates and returns the face normal of the given face.
     *
     * Generated from Godot docs: MeshDataTool.get_face_normal
     */
    fun getFaceNormal(idx: Int): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getFaceNormalBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the material to be used by newly-constructed `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.set_material
     */
    fun setMaterial(material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(material)
    }

    /**
     * Returns the material assigned to the `Mesh`.
     *
     * Generated from Godot docs: MeshDataTool.get_material
     */
    fun getMaterial(): Material? {
        checkOpen()
        val ret = Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MeshDataTool? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): MeshDataTool? =
            if (handle.address() == 0L) null else RefCounted.owned(MeshDataTool(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): MeshDataTool? =
            if (handle.address() == 0L) null else MeshDataTool(GodotHandle(handle))

        // Instantiate a MeshDataTool.
        @JvmStatic
        fun create(): MeshDataTool =
            RefCounted.owned(MeshDataTool(GodotHandle(ObjectCalls.constructObject("MeshDataTool"))))
    }

    private object Binds {
        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("MeshDataTool", "clear", CLEAR_HASH)

        private const val CREATE_FROM_SURFACE_HASH = 2727020678L
        @JvmField
        val createFromSurfaceBind =
            ObjectCalls.getMethodBind("MeshDataTool", "create_from_surface", CREATE_FROM_SURFACE_HASH)

        private const val COMMIT_TO_SURFACE_HASH = 2021686445L
        @JvmField
        val commitToSurfaceBind =
            ObjectCalls.getMethodBind("MeshDataTool", "commit_to_surface", COMMIT_TO_SURFACE_HASH)

        private const val GET_FORMAT_HASH = 3905245786L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_format", GET_FORMAT_HASH)

        private const val GET_VERTEX_COUNT_HASH = 3905245786L
        @JvmField
        val getVertexCountBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_count", GET_VERTEX_COUNT_HASH)

        private const val GET_EDGE_COUNT_HASH = 3905245786L
        @JvmField
        val getEdgeCountBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_count", GET_EDGE_COUNT_HASH)

        private const val GET_FACE_COUNT_HASH = 3905245786L
        @JvmField
        val getFaceCountBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_count", GET_FACE_COUNT_HASH)

        private const val SET_VERTEX_HASH = 1530502735L
        @JvmField
        val setVertexBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex", SET_VERTEX_HASH)

        private const val GET_VERTEX_HASH = 711720468L
        @JvmField
        val getVertexBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex", GET_VERTEX_HASH)

        private const val SET_VERTEX_NORMAL_HASH = 1530502735L
        @JvmField
        val setVertexNormalBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_normal", SET_VERTEX_NORMAL_HASH)

        private const val GET_VERTEX_NORMAL_HASH = 711720468L
        @JvmField
        val getVertexNormalBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_normal", GET_VERTEX_NORMAL_HASH)

        private const val SET_VERTEX_TANGENT_HASH = 1104099133L
        @JvmField
        val setVertexTangentBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_tangent", SET_VERTEX_TANGENT_HASH)

        private const val GET_VERTEX_TANGENT_HASH = 1372055458L
        @JvmField
        val getVertexTangentBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_tangent", GET_VERTEX_TANGENT_HASH)

        private const val SET_VERTEX_UV_HASH = 163021252L
        @JvmField
        val setVertexUvBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_uv", SET_VERTEX_UV_HASH)

        private const val GET_VERTEX_UV_HASH = 2299179447L
        @JvmField
        val getVertexUvBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_uv", GET_VERTEX_UV_HASH)

        private const val SET_VERTEX_UV2_HASH = 163021252L
        @JvmField
        val setVertexUv2Bind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_uv2", SET_VERTEX_UV2_HASH)

        private const val GET_VERTEX_UV2_HASH = 2299179447L
        @JvmField
        val getVertexUv2Bind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_uv2", GET_VERTEX_UV2_HASH)

        private const val SET_VERTEX_COLOR_HASH = 2878471219L
        @JvmField
        val setVertexColorBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_color", SET_VERTEX_COLOR_HASH)

        private const val GET_VERTEX_COLOR_HASH = 3457211756L
        @JvmField
        val getVertexColorBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_color", GET_VERTEX_COLOR_HASH)

        private const val SET_VERTEX_BONES_HASH = 3500328261L
        @JvmField
        val setVertexBonesBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_bones", SET_VERTEX_BONES_HASH)

        private const val GET_VERTEX_BONES_HASH = 1706082319L
        @JvmField
        val getVertexBonesBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_bones", GET_VERTEX_BONES_HASH)

        private const val SET_VERTEX_WEIGHTS_HASH = 1345852415L
        @JvmField
        val setVertexWeightsBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_weights", SET_VERTEX_WEIGHTS_HASH)

        private const val GET_VERTEX_WEIGHTS_HASH = 1542882410L
        @JvmField
        val getVertexWeightsBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_weights", GET_VERTEX_WEIGHTS_HASH)

        private const val SET_VERTEX_META_HASH = 2152698145L
        @JvmField
        val setVertexMetaBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_vertex_meta", SET_VERTEX_META_HASH)

        private const val GET_VERTEX_META_HASH = 4227898402L
        @JvmField
        val getVertexMetaBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_meta", GET_VERTEX_META_HASH)

        private const val GET_VERTEX_EDGES_HASH = 1706082319L
        @JvmField
        val getVertexEdgesBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_edges", GET_VERTEX_EDGES_HASH)

        private const val GET_VERTEX_FACES_HASH = 1706082319L
        @JvmField
        val getVertexFacesBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_vertex_faces", GET_VERTEX_FACES_HASH)

        private const val GET_EDGE_VERTEX_HASH = 3175239445L
        @JvmField
        val getEdgeVertexBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_vertex", GET_EDGE_VERTEX_HASH)

        private const val GET_EDGE_FACES_HASH = 1706082319L
        @JvmField
        val getEdgeFacesBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_faces", GET_EDGE_FACES_HASH)

        private const val SET_EDGE_META_HASH = 2152698145L
        @JvmField
        val setEdgeMetaBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_edge_meta", SET_EDGE_META_HASH)

        private const val GET_EDGE_META_HASH = 4227898402L
        @JvmField
        val getEdgeMetaBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_edge_meta", GET_EDGE_META_HASH)

        private const val GET_FACE_VERTEX_HASH = 3175239445L
        @JvmField
        val getFaceVertexBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_vertex", GET_FACE_VERTEX_HASH)

        private const val GET_FACE_EDGE_HASH = 3175239445L
        @JvmField
        val getFaceEdgeBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_edge", GET_FACE_EDGE_HASH)

        private const val SET_FACE_META_HASH = 2152698145L
        @JvmField
        val setFaceMetaBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_face_meta", SET_FACE_META_HASH)

        private const val GET_FACE_META_HASH = 4227898402L
        @JvmField
        val getFaceMetaBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_meta", GET_FACE_META_HASH)

        private const val GET_FACE_NORMAL_HASH = 711720468L
        @JvmField
        val getFaceNormalBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_face_normal", GET_FACE_NORMAL_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("MeshDataTool", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("MeshDataTool", "get_material", GET_MATERIAL_HASH)
    }
}

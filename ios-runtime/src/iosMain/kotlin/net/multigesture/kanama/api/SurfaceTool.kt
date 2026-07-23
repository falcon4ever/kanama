package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: SurfaceTool
 */
class SurfaceTool(handle: MemorySegment) : RefCounted(handle) {
    fun setSkinWeightCount(count: Long) {
        ObjectCalls.ptrcallWithLongArg(setSkinWeightCountBind, handle, count)
    }

    fun getSkinWeightCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSkinWeightCountBind, handle)
    }

    fun setCustomFormat(channelIndex: Int, format: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCustomFormatBind, handle, channelIndex, format)
    }

    fun getCustomFormat(channelIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCustomFormatBind, handle, channelIndex)
    }

    fun begin(primitive: Long) {
        ObjectCalls.ptrcallWithLongArg(beginBind, handle, primitive)
    }

    fun addVertex(vertex: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(addVertexBind, handle, vertex)
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun setNormal(normal: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setNormalBind, handle, normal)
    }

    fun setUv(uv: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setUvBind, handle, uv)
    }

    fun setUv2(uv2: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setUv2Bind, handle, uv2)
    }

    fun setWeights(weights: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setWeightsBind, handle, weights)
    }

    fun setCustom(channelIndex: Int, customColor: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setCustomBind, handle, channelIndex, customColor)
    }

    fun setSmoothGroup(index: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSmoothGroupBind, handle, index)
    }

    fun addIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(addIndexBind, handle, index)
    }

    fun index() {
        ObjectCalls.ptrcallNoArgs(indexBind, handle)
    }

    fun deindex() {
        ObjectCalls.ptrcallNoArgs(deindexBind, handle)
    }

    fun generateNormals(flip: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(generateNormalsBind, handle, flip)
    }

    fun generateTangents() {
        ObjectCalls.ptrcallNoArgs(generateTangentsBind, handle)
    }

    fun optimizeIndicesForCache() {
        ObjectCalls.ptrcallNoArgs(optimizeIndicesForCacheBind, handle)
    }

    fun getAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getAabbBind, handle)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPrimitiveType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPrimitiveTypeBind, handle)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun createFrom(existing: Mesh?, surface: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(createFromBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, surface)
    }

    fun createFromBlendShape(existing: Mesh?, surface: Int, blendShape: String) {
        ObjectCalls.ptrcallWithObjectIntStringArgs(createFromBlendShapeBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, surface, blendShape)
    }

    fun appendFrom(existing: Mesh?, surface: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithObjectIntTransform3DArgs(appendFromBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, surface, transform)
    }

    fun commit(existing: ArrayMesh?, flags: Long = 0L): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectAndLongArgsRetObject(commitBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, flags))
    }

    fun commitToArrays(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(commitToArraysBind, handle)
    }

    // No-arg commit() — the generated commit(existing, flags) doesn't default the nullable `existing`
    // ArrayMesh; this overload matches the desktop/Android commit() default-arg call.
    fun commit(): ArrayMesh? = commit(null)

    companion object {
        const val CUSTOM_RGBA8_UNORM: Long = 0L
        const val CUSTOM_RGBA8_SNORM: Long = 1L
        const val CUSTOM_RG_HALF: Long = 2L
        const val CUSTOM_RGBA_HALF: Long = 3L
        const val CUSTOM_R_FLOAT: Long = 4L
        const val CUSTOM_RG_FLOAT: Long = 5L
        const val CUSTOM_RGB_FLOAT: Long = 6L
        const val CUSTOM_RGBA_FLOAT: Long = 7L
        const val CUSTOM_MAX: Long = 8L
        const val SKIN_4_WEIGHTS: Long = 0L
        const val SKIN_8_WEIGHTS: Long = 1L

        fun fromHandle(handle: MemorySegment): SurfaceTool? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SurfaceTool? =
            if (handle.address() == 0L) null else SurfaceTool(handle)

        // Instantiate a SurfaceTool (RefCounted; used to build meshes procedurally).
        fun create(): SurfaceTool =
            SurfaceTool(MemorySegment.ofAddress(IosGodot.constructObject("SurfaceTool")))

        private const val SET_SKIN_WEIGHT_COUNT_HASH = 618679515L
        private val setSkinWeightCountBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_skin_weight_count", SET_SKIN_WEIGHT_COUNT_HASH)
        }

        private const val GET_SKIN_WEIGHT_COUNT_HASH = 1072401130L
        private val getSkinWeightCountBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_skin_weight_count", GET_SKIN_WEIGHT_COUNT_HASH)
        }

        private const val SET_CUSTOM_FORMAT_HASH = 4087759856L
        private val setCustomFormatBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_custom_format", SET_CUSTOM_FORMAT_HASH)
        }

        private const val GET_CUSTOM_FORMAT_HASH = 839863283L
        private val getCustomFormatBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_custom_format", GET_CUSTOM_FORMAT_HASH)
        }

        private const val BEGIN_HASH = 2230304113L
        private val beginBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "begin", BEGIN_HASH)
        }

        private const val ADD_VERTEX_HASH = 3460891852L
        private val addVertexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "add_vertex", ADD_VERTEX_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_color", SET_COLOR_HASH)
        }

        private const val SET_NORMAL_HASH = 3460891852L
        private val setNormalBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_normal", SET_NORMAL_HASH)
        }

        private const val SET_UV_HASH = 743155724L
        private val setUvBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_uv", SET_UV_HASH)
        }

        private const val SET_UV2_HASH = 743155724L
        private val setUv2Bind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_uv2", SET_UV2_HASH)
        }

        private const val SET_WEIGHTS_HASH = 2899603908L
        private val setWeightsBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_weights", SET_WEIGHTS_HASH)
        }

        private const val SET_CUSTOM_HASH = 2878471219L
        private val setCustomBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_custom", SET_CUSTOM_HASH)
        }

        private const val SET_SMOOTH_GROUP_HASH = 1286410249L
        private val setSmoothGroupBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_smooth_group", SET_SMOOTH_GROUP_HASH)
        }

        private const val ADD_INDEX_HASH = 1286410249L
        private val addIndexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "add_index", ADD_INDEX_HASH)
        }

        private const val INDEX_HASH = 3218959716L
        private val indexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "index", INDEX_HASH)
        }

        private const val DEINDEX_HASH = 3218959716L
        private val deindexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "deindex", DEINDEX_HASH)
        }

        private const val GENERATE_NORMALS_HASH = 107499316L
        private val generateNormalsBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "generate_normals", GENERATE_NORMALS_HASH)
        }

        private const val GENERATE_TANGENTS_HASH = 3218959716L
        private val generateTangentsBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "generate_tangents", GENERATE_TANGENTS_HASH)
        }

        private const val OPTIMIZE_INDICES_FOR_CACHE_HASH = 3218959716L
        private val optimizeIndicesForCacheBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "optimize_indices_for_cache", OPTIMIZE_INDICES_FOR_CACHE_HASH)
        }

        private const val GET_AABB_HASH = 1068685055L
        private val getAabbBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_aabb", GET_AABB_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_PRIMITIVE_TYPE_HASH = 768822145L
        private val getPrimitiveTypeBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_primitive_type", GET_PRIMITIVE_TYPE_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "clear", CLEAR_HASH)
        }

        private const val CREATE_FROM_HASH = 1767024570L
        private val createFromBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "create_from", CREATE_FROM_HASH)
        }

        private const val CREATE_FROM_BLEND_SHAPE_HASH = 1306185582L
        private val createFromBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "create_from_blend_shape", CREATE_FROM_BLEND_SHAPE_HASH)
        }

        private const val APPEND_FROM_HASH = 2217967155L
        private val appendFromBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "append_from", APPEND_FROM_HASH)
        }

        private const val COMMIT_HASH = 4107864055L
        private val commitBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "commit", COMMIT_HASH)
        }

        private const val COMMIT_TO_ARRAYS_HASH = 2915620761L
        private val commitToArraysBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "commit_to_arrays", COMMIT_TO_ARRAYS_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Parameters to be used with a `Mesh` convex decomposition operation.
 *
 * Generated from Godot docs: MeshConvexDecompositionSettings
 */
class MeshConvexDecompositionSettings(handle: MemorySegment) : RefCounted(handle) {
    var maxConcavity: Double
        @JvmName("maxConcavityProperty")
        get() = getMaxConcavity()
        @JvmName("setMaxConcavityProperty")
        set(value) = setMaxConcavity(value)

    var symmetryPlanesClippingBias: Double
        @JvmName("symmetryPlanesClippingBiasProperty")
        get() = getSymmetryPlanesClippingBias()
        @JvmName("setSymmetryPlanesClippingBiasProperty")
        set(value) = setSymmetryPlanesClippingBias(value)

    var revolutionAxesClippingBias: Double
        @JvmName("revolutionAxesClippingBiasProperty")
        get() = getRevolutionAxesClippingBias()
        @JvmName("setRevolutionAxesClippingBiasProperty")
        set(value) = setRevolutionAxesClippingBias(value)

    var minVolumePerConvexHull: Double
        @JvmName("minVolumePerConvexHullProperty")
        get() = getMinVolumePerConvexHull()
        @JvmName("setMinVolumePerConvexHullProperty")
        set(value) = setMinVolumePerConvexHull(value)

    var resolution: Long
        @JvmName("resolutionProperty")
        get() = getResolution()
        @JvmName("setResolutionProperty")
        set(value) = setResolution(value)

    var maxNumVerticesPerConvexHull: Long
        @JvmName("maxNumVerticesPerConvexHullProperty")
        get() = getMaxNumVerticesPerConvexHull()
        @JvmName("setMaxNumVerticesPerConvexHullProperty")
        set(value) = setMaxNumVerticesPerConvexHull(value)

    var planeDownsampling: Long
        @JvmName("planeDownsamplingProperty")
        get() = getPlaneDownsampling()
        @JvmName("setPlaneDownsamplingProperty")
        set(value) = setPlaneDownsampling(value)

    var convexHullDownsampling: Long
        @JvmName("convexHullDownsamplingProperty")
        get() = getConvexHullDownsampling()
        @JvmName("setConvexHullDownsamplingProperty")
        set(value) = setConvexHullDownsampling(value)

    var normalizeMesh: Boolean
        @JvmName("normalizeMeshProperty")
        get() = getNormalizeMesh()
        @JvmName("setNormalizeMeshProperty")
        set(value) = setNormalizeMesh(value)

    var mode: Long
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    var convexHullApproximation: Boolean
        @JvmName("convexHullApproximationProperty")
        get() = getConvexHullApproximation()
        @JvmName("setConvexHullApproximationProperty")
        set(value) = setConvexHullApproximation(value)

    var maxConvexHulls: Long
        @JvmName("maxConvexHullsProperty")
        get() = getMaxConvexHulls()
        @JvmName("setMaxConvexHullsProperty")
        set(value) = setMaxConvexHulls(value)

    var projectHullVertices: Boolean
        @JvmName("projectHullVerticesProperty")
        get() = getProjectHullVertices()
        @JvmName("setProjectHullVerticesProperty")
        set(value) = setProjectHullVertices(value)

    fun setMaxConcavity(maxConcavity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxConcavityBind, handle, maxConcavity)
    }

    fun getMaxConcavity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxConcavityBind, handle)
    }

    fun setSymmetryPlanesClippingBias(symmetryPlanesClippingBias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSymmetryPlanesClippingBiasBind, handle, symmetryPlanesClippingBias)
    }

    fun getSymmetryPlanesClippingBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSymmetryPlanesClippingBiasBind, handle)
    }

    fun setRevolutionAxesClippingBias(revolutionAxesClippingBias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRevolutionAxesClippingBiasBind, handle, revolutionAxesClippingBias)
    }

    fun getRevolutionAxesClippingBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRevolutionAxesClippingBiasBind, handle)
    }

    fun setMinVolumePerConvexHull(minVolumePerConvexHull: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinVolumePerConvexHullBind, handle, minVolumePerConvexHull)
    }

    fun getMinVolumePerConvexHull(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinVolumePerConvexHullBind, handle)
    }

    fun setResolution(minVolumePerConvexHull: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setResolutionBind, handle, minVolumePerConvexHull)
    }

    fun getResolution(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getResolutionBind, handle)
    }

    fun setMaxNumVerticesPerConvexHull(maxNumVerticesPerConvexHull: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setMaxNumVerticesPerConvexHullBind, handle, maxNumVerticesPerConvexHull)
    }

    fun getMaxNumVerticesPerConvexHull(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getMaxNumVerticesPerConvexHullBind, handle)
    }

    fun setPlaneDownsampling(planeDownsampling: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setPlaneDownsamplingBind, handle, planeDownsampling)
    }

    fun getPlaneDownsampling(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getPlaneDownsamplingBind, handle)
    }

    fun setConvexHullDownsampling(convexHullDownsampling: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setConvexHullDownsamplingBind, handle, convexHullDownsampling)
    }

    fun getConvexHullDownsampling(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getConvexHullDownsamplingBind, handle)
    }

    fun setNormalizeMesh(normalizeMesh: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNormalizeMeshBind, handle, normalizeMesh)
    }

    fun getNormalizeMesh(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getNormalizeMeshBind, handle)
    }

    fun setMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModeBind, handle, mode)
    }

    fun getMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, handle)
    }

    fun setConvexHullApproximation(convexHullApproximation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setConvexHullApproximationBind, handle, convexHullApproximation)
    }

    fun getConvexHullApproximation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getConvexHullApproximationBind, handle)
    }

    fun setMaxConvexHulls(maxConvexHulls: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setMaxConvexHullsBind, handle, maxConvexHulls)
    }

    fun getMaxConvexHulls(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getMaxConvexHullsBind, handle)
    }

    fun setProjectHullVertices(projectHullVertices: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProjectHullVerticesBind, handle, projectHullVertices)
    }

    fun getProjectHullVertices(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getProjectHullVerticesBind, handle)
    }

    companion object {
        const val CONVEX_DECOMPOSITION_MODE_VOXEL: Long = 0L
        const val CONVEX_DECOMPOSITION_MODE_TETRAHEDRON: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): MeshConvexDecompositionSettings? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MeshConvexDecompositionSettings? =
            if (handle.address() == 0L) null else MeshConvexDecompositionSettings(handle)

        private const val SET_MAX_CONCAVITY_HASH = 373806689L
        private val setMaxConcavityBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_max_concavity", SET_MAX_CONCAVITY_HASH)
        }

        private const val GET_MAX_CONCAVITY_HASH = 1740695150L
        private val getMaxConcavityBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_max_concavity", GET_MAX_CONCAVITY_HASH)
        }

        private const val SET_SYMMETRY_PLANES_CLIPPING_BIAS_HASH = 373806689L
        private val setSymmetryPlanesClippingBiasBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_symmetry_planes_clipping_bias", SET_SYMMETRY_PLANES_CLIPPING_BIAS_HASH)
        }

        private const val GET_SYMMETRY_PLANES_CLIPPING_BIAS_HASH = 1740695150L
        private val getSymmetryPlanesClippingBiasBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_symmetry_planes_clipping_bias", GET_SYMMETRY_PLANES_CLIPPING_BIAS_HASH)
        }

        private const val SET_REVOLUTION_AXES_CLIPPING_BIAS_HASH = 373806689L
        private val setRevolutionAxesClippingBiasBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_revolution_axes_clipping_bias", SET_REVOLUTION_AXES_CLIPPING_BIAS_HASH)
        }

        private const val GET_REVOLUTION_AXES_CLIPPING_BIAS_HASH = 1740695150L
        private val getRevolutionAxesClippingBiasBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_revolution_axes_clipping_bias", GET_REVOLUTION_AXES_CLIPPING_BIAS_HASH)
        }

        private const val SET_MIN_VOLUME_PER_CONVEX_HULL_HASH = 373806689L
        private val setMinVolumePerConvexHullBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_min_volume_per_convex_hull", SET_MIN_VOLUME_PER_CONVEX_HULL_HASH)
        }

        private const val GET_MIN_VOLUME_PER_CONVEX_HULL_HASH = 1740695150L
        private val getMinVolumePerConvexHullBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_min_volume_per_convex_hull", GET_MIN_VOLUME_PER_CONVEX_HULL_HASH)
        }

        private const val SET_RESOLUTION_HASH = 1286410249L
        private val setResolutionBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_resolution", SET_RESOLUTION_HASH)
        }

        private const val GET_RESOLUTION_HASH = 3905245786L
        private val getResolutionBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_resolution", GET_RESOLUTION_HASH)
        }

        private const val SET_MAX_NUM_VERTICES_PER_CONVEX_HULL_HASH = 1286410249L
        private val setMaxNumVerticesPerConvexHullBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_max_num_vertices_per_convex_hull", SET_MAX_NUM_VERTICES_PER_CONVEX_HULL_HASH)
        }

        private const val GET_MAX_NUM_VERTICES_PER_CONVEX_HULL_HASH = 3905245786L
        private val getMaxNumVerticesPerConvexHullBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_max_num_vertices_per_convex_hull", GET_MAX_NUM_VERTICES_PER_CONVEX_HULL_HASH)
        }

        private const val SET_PLANE_DOWNSAMPLING_HASH = 1286410249L
        private val setPlaneDownsamplingBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_plane_downsampling", SET_PLANE_DOWNSAMPLING_HASH)
        }

        private const val GET_PLANE_DOWNSAMPLING_HASH = 3905245786L
        private val getPlaneDownsamplingBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_plane_downsampling", GET_PLANE_DOWNSAMPLING_HASH)
        }

        private const val SET_CONVEX_HULL_DOWNSAMPLING_HASH = 1286410249L
        private val setConvexHullDownsamplingBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_convex_hull_downsampling", SET_CONVEX_HULL_DOWNSAMPLING_HASH)
        }

        private const val GET_CONVEX_HULL_DOWNSAMPLING_HASH = 3905245786L
        private val getConvexHullDownsamplingBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_convex_hull_downsampling", GET_CONVEX_HULL_DOWNSAMPLING_HASH)
        }

        private const val SET_NORMALIZE_MESH_HASH = 2586408642L
        private val setNormalizeMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_normalize_mesh", SET_NORMALIZE_MESH_HASH)
        }

        private const val GET_NORMALIZE_MESH_HASH = 36873697L
        private val getNormalizeMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_normalize_mesh", GET_NORMALIZE_MESH_HASH)
        }

        private const val SET_MODE_HASH = 1668072869L
        private val setModeBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_mode", SET_MODE_HASH)
        }

        private const val GET_MODE_HASH = 23479454L
        private val getModeBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_mode", GET_MODE_HASH)
        }

        private const val SET_CONVEX_HULL_APPROXIMATION_HASH = 2586408642L
        private val setConvexHullApproximationBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_convex_hull_approximation", SET_CONVEX_HULL_APPROXIMATION_HASH)
        }

        private const val GET_CONVEX_HULL_APPROXIMATION_HASH = 36873697L
        private val getConvexHullApproximationBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_convex_hull_approximation", GET_CONVEX_HULL_APPROXIMATION_HASH)
        }

        private const val SET_MAX_CONVEX_HULLS_HASH = 1286410249L
        private val setMaxConvexHullsBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_max_convex_hulls", SET_MAX_CONVEX_HULLS_HASH)
        }

        private const val GET_MAX_CONVEX_HULLS_HASH = 3905245786L
        private val getMaxConvexHullsBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_max_convex_hulls", GET_MAX_CONVEX_HULLS_HASH)
        }

        private const val SET_PROJECT_HULL_VERTICES_HASH = 2586408642L
        private val setProjectHullVerticesBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "set_project_hull_vertices", SET_PROJECT_HULL_VERTICES_HASH)
        }

        private const val GET_PROJECT_HULL_VERTICES_HASH = 36873697L
        private val getProjectHullVerticesBind by lazy {
            ObjectCalls.getMethodBind("MeshConvexDecompositionSettings", "get_project_hull_vertices", GET_PROJECT_HULL_VERTICES_HASH)
        }
    }
}

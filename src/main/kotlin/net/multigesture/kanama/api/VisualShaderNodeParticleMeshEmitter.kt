package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter
 */
class VisualShaderNodeParticleMeshEmitter(handle: MemorySegment) : VisualShaderNodeParticleEmitter(handle) {
    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var useAllSurfaces: Boolean
        @JvmName("useAllSurfacesProperty")
        get() = isUseAllSurfaces()
        @JvmName("setUseAllSurfacesProperty")
        set(value) = setUseAllSurfaces(value)

    var surfaceIndex: Int
        @JvmName("surfaceIndexProperty")
        get() = getSurfaceIndex()
        @JvmName("setSurfaceIndexProperty")
        set(value) = setSurfaceIndex(value)

    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    fun setUseAllSurfaces(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAllSurfacesBind, handle, enabled)
    }

    fun isUseAllSurfaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUseAllSurfacesBind, handle)
    }

    fun setSurfaceIndex(surfaceIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setSurfaceIndexBind, handle, surfaceIndex)
    }

    fun getSurfaceIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceIndexBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleMeshEmitter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleMeshEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleMeshEmitter(handle)

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 1808005922L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_USE_ALL_SURFACES_HASH = 2586408642L
        private val setUseAllSurfacesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "set_use_all_surfaces", SET_USE_ALL_SURFACES_HASH)
        }

        private const val IS_USE_ALL_SURFACES_HASH = 36873697L
        private val isUseAllSurfacesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "is_use_all_surfaces", IS_USE_ALL_SURFACES_HASH)
        }

        private const val SET_SURFACE_INDEX_HASH = 1286410249L
        private val setSurfaceIndexBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "set_surface_index", SET_SURFACE_INDEX_HASH)
        }

        private const val GET_SURFACE_INDEX_HASH = 3905245786L
        private val getSurfaceIndexBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "get_surface_index", GET_SURFACE_INDEX_HASH)
        }
    }
}

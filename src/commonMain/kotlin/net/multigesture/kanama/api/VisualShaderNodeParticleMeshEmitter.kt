package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter
 */
class VisualShaderNodeParticleMeshEmitter(handle: GodotHandle) : VisualShaderNodeParticleEmitter(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMesh(): Mesh? {
        checkOpen()
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, segment))
    }

    fun setUseAllSurfaces(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseAllSurfacesBind, segment, enabled)
    }

    fun isUseAllSurfaces(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUseAllSurfacesBind, segment)
    }

    fun setSurfaceIndex(surfaceIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSurfaceIndexBind, segment, surfaceIndex)
    }

    fun getSurfaceIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceIndexBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleMeshEmitter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleMeshEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleMeshEmitter(GodotHandle(handle))

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

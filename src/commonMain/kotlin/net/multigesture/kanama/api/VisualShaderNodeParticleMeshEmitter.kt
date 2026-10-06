package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMesh(): Mesh? {
        checkOpen()
        return Mesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
    }

    fun setUseAllSurfaces(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseAllSurfacesBind, segment, enabled)
    }

    fun isUseAllSurfaces(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUseAllSurfacesBind, segment)
    }

    fun setSurfaceIndex(surfaceIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSurfaceIndexBind, segment, surfaceIndex)
    }

    fun getSurfaceIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSurfaceIndexBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleMeshEmitter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleMeshEmitter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleMeshEmitter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleMeshEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleMeshEmitter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MESH_HASH = 194775623L
        @JvmField
        val setMeshBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "set_mesh", SET_MESH_HASH)

        private const val GET_MESH_HASH = 1808005922L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "get_mesh", GET_MESH_HASH)

        private const val SET_USE_ALL_SURFACES_HASH = 2586408642L
        @JvmField
        val setUseAllSurfacesBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "set_use_all_surfaces", SET_USE_ALL_SURFACES_HASH)

        private const val IS_USE_ALL_SURFACES_HASH = 36873697L
        @JvmField
        val isUseAllSurfacesBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "is_use_all_surfaces", IS_USE_ALL_SURFACES_HASH)

        private const val SET_SURFACE_INDEX_HASH = 1286410249L
        @JvmField
        val setSurfaceIndexBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "set_surface_index", SET_SURFACE_INDEX_HASH)

        private const val GET_SURFACE_INDEX_HASH = 3905245786L
        @JvmField
        val getSurfaceIndexBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleMeshEmitter", "get_surface_index", GET_SURFACE_INDEX_HASH)
    }
}

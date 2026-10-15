package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node that makes particles emitted in a shape defined by a `Mesh`.
 *
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

    /**
     * The `Mesh` that defines emission shape.
     *
     * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter.set_mesh
     */
    fun setMesh(mesh: Mesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(mesh)
    }

    /**
     * The `Mesh` that defines emission shape.
     *
     * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter.get_mesh
     */
    fun getMesh(): Mesh? {
        checkOpen()
        val ret = Mesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the particles will emit from all surfaces of the mesh.
     *
     * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter.set_use_all_surfaces
     */
    fun setUseAllSurfaces(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseAllSurfacesBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the particles will emit from all surfaces of the mesh.
     *
     * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter.is_use_all_surfaces
     */
    fun isUseAllSurfaces(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isUseAllSurfacesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Index of the surface that emits particles. `use_all_surfaces` must be `false` for this to take
     * effect.
     *
     * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter.set_surface_index
     */
    fun setSurfaceIndex(surfaceIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSurfaceIndexBind, segment, surfaceIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Index of the surface that emits particles. `use_all_surfaces` must be `false` for this to take
     * effect.
     *
     * Generated from Godot docs: VisualShaderNodeParticleMeshEmitter.get_surface_index
     */
    fun getSurfaceIndex(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSurfaceIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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

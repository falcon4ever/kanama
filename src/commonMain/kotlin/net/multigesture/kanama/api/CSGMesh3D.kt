package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: CSGMesh3D
 */
class CSGMesh3D(handle: GodotHandle) : CSGPrimitive3D(handle) {
    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, segment))
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGMesh3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGMesh3D? =
            if (handle.address() == 0L) null else CSGMesh3D(GodotHandle(handle))

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("CSGMesh3D", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 4081188045L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("CSGMesh3D", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGMesh3D", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGMesh3D", "get_material", GET_MATERIAL_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFMesh
 */
class GLTFMesh(handle: MemorySegment) : Resource(handle) {
    var originalName: String
        @JvmName("originalNameProperty")
        get() = getOriginalName()
        @JvmName("setOriginalNameProperty")
        set(value) = setOriginalName(value)

    var mesh: ImporterMesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var blendWeights: List<Float>
        @JvmName("blendWeightsProperty")
        get() = getBlendWeights()
        @JvmName("setBlendWeightsProperty")
        set(value) = setBlendWeights(value)

    val instanceMaterials: List<Material>
        @JvmName("instanceMaterialsProperty")
        get() = getInstanceMaterials()

    fun getOriginalName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, handle)
    }

    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, handle, originalName)
    }

    fun getMesh(): ImporterMesh? {
        checkOpen()
        return ImporterMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    fun setMesh(mesh: ImporterMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getBlendWeights(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getBlendWeightsBind, handle)
    }

    fun setBlendWeights(blendWeights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setBlendWeightsBind, handle, blendWeights)
    }

    fun getInstanceMaterials(): List<Material> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getInstanceMaterialsBind, handle, Material::fromHandle)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): GLTFMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFMesh? =
            if (handle.address() == 0L) null else GLTFMesh(handle)

        private const val GET_ORIGINAL_NAME_HASH = 2841200299L
        private val getOriginalNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "get_original_name", GET_ORIGINAL_NAME_HASH)
        }

        private const val SET_ORIGINAL_NAME_HASH = 83702148L
        private val setOriginalNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "set_original_name", SET_ORIGINAL_NAME_HASH)
        }

        private const val GET_MESH_HASH = 3754628756L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_MESH_HASH = 2255166972L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_BLEND_WEIGHTS_HASH = 2445143706L
        private val getBlendWeightsBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "get_blend_weights", GET_BLEND_WEIGHTS_HASH)
        }

        private const val SET_BLEND_WEIGHTS_HASH = 2899603908L
        private val setBlendWeightsBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "set_blend_weights", SET_BLEND_WEIGHTS_HASH)
        }

        private const val GET_INSTANCE_MATERIALS_HASH = 2915620761L
        private val getInstanceMaterialsBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "get_instance_materials", GET_INSTANCE_MATERIALS_HASH)
        }

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        private val getAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "get_additional_data", GET_ADDITIONAL_DATA_HASH)
        }
    }
}

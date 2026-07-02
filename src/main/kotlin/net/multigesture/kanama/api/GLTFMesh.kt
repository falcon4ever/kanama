package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

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

    var instanceMaterials: List<Material>
        @JvmName("instanceMaterialsProperty")
        get() = getInstanceMaterials()
        @JvmName("setInstanceMaterialsProperty")
        set(value) = setInstanceMaterials(value)

    fun getOriginalName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, handle)
    }

    fun setOriginalName(originalName: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, handle, originalName)
    }

    fun getMesh(): ImporterMesh? {
        return ImporterMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    fun setMesh(mesh: ImporterMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getBlendWeights(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getBlendWeightsBind, handle)
    }

    fun setBlendWeights(blendWeights: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setBlendWeightsBind, handle, blendWeights)
    }

    fun getInstanceMaterials(): List<Material> {
        return ObjectCalls.ptrcallNoArgsRetTypedMaterialList(getInstanceMaterialsBind, handle)
    }

    fun setInstanceMaterials(instanceMaterials: List<Material>) {
        ObjectCalls.ptrcallWithTypedMaterialListArg(setInstanceMaterialsBind, handle, instanceMaterials)
    }

    fun getAdditionalData(extensionName: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
    }

    companion object {
        @JvmStatic
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

        private const val SET_INSTANCE_MATERIALS_HASH = 381264803L
        private val setInstanceMaterialsBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "set_instance_materials", SET_INSTANCE_MATERIALS_HASH)
        }

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        private val getAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "get_additional_data", GET_ADDITIONAL_DATA_HASH)
        }

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        private val setAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFMesh", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GLTFMesh
 */
class GLTFMesh(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, segment)
    }

    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, segment, originalName)
    }

    fun getMesh(): ImporterMesh? {
        checkOpen()
        return ImporterMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, segment))
    }

    fun setMesh(mesh: ImporterMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getBlendWeights(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getBlendWeightsBind, segment)
    }

    fun setBlendWeights(blendWeights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setBlendWeightsBind, segment, blendWeights)
    }

    fun getInstanceMaterials(): List<Material> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getInstanceMaterialsBind, segment, Material::wrap)
    }

    fun setInstanceMaterials(instanceMaterials: List<Material>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedMaterialListArg(setInstanceMaterialsBind, segment, instanceMaterials)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, segment, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, segment, extensionName, additionalData)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFMesh? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GLTFMesh? =
            if (handle.address() == 0L) null else GLTFMesh(GodotHandle(handle))

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

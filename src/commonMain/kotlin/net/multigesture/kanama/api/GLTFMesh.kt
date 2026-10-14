package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * GLTFMesh represents a glTF mesh.
 *
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

    /**
     * The original name of the mesh.
     *
     * Generated from Godot docs: GLTFMesh.get_original_name
     */
    fun getOriginalName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getOriginalNameBind, segment)
    }

    /**
     * The original name of the mesh.
     *
     * Generated from Godot docs: GLTFMesh.set_original_name
     */
    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setOriginalNameBind, segment, originalName)
    }

    /**
     * The `ImporterMesh` object representing the mesh itself.
     *
     * Generated from Godot docs: GLTFMesh.get_mesh
     */
    fun getMesh(): ImporterMesh? {
        checkOpen()
        return ImporterMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
    }

    /**
     * The `ImporterMesh` object representing the mesh itself.
     *
     * Generated from Godot docs: GLTFMesh.set_mesh
     */
    fun setMesh(mesh: ImporterMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * An array of floats representing the blend weights of the mesh.
     *
     * Generated from Godot docs: GLTFMesh.get_blend_weights
     */
    fun getBlendWeights(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(Binds.getBlendWeightsBind, segment)
    }

    /**
     * An array of floats representing the blend weights of the mesh.
     *
     * Generated from Godot docs: GLTFMesh.set_blend_weights
     */
    fun setBlendWeights(blendWeights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.setBlendWeightsBind, segment, blendWeights)
    }

    /**
     * An array of Material objects representing the materials used in the mesh.
     *
     * Generated from Godot docs: GLTFMesh.get_instance_materials
     */
    fun getInstanceMaterials(): List<Material> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getInstanceMaterialsBind, segment, Material::wrapBorrowed)
    }

    /**
     * An array of Material objects representing the materials used in the mesh.
     *
     * Generated from Godot docs: GLTFMesh.set_instance_materials
     */
    fun setInstanceMaterials(instanceMaterials: List<Material>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedMaterialListArg(Binds.setInstanceMaterialsBind, segment, instanceMaterials)
    }

    /**
     * Gets additional arbitrary data in this `GLTFMesh` instance. This can be used to keep per-node
     * state data in `GLTFDocumentExtension` classes, which is important because they are stateless.
     * The argument should be the `GLTFDocumentExtension` name (does not have to match the extension
     * name in the glTF file), and the return value can be anything you set. If nothing was set, the
     * return value is `null`.
     *
     * Generated from Godot docs: GLTFMesh.get_additional_data
     */
    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
    }

    /**
     * Sets additional arbitrary data in this `GLTFMesh` instance. This can be used to keep per-node
     * state data in `GLTFDocumentExtension` classes, which is important because they are stateless.
     * The first argument should be the `GLTFDocumentExtension` name (does not have to match the
     * extension name in the glTF file), and the second argument can be anything you want.
     *
     * Generated from Godot docs: GLTFMesh.set_additional_data
     */
    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFMesh? =
            if (handle.address() == 0L) null else GLTFMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_ORIGINAL_NAME_HASH = 2841200299L
        @JvmField
        val getOriginalNameBind =
            ObjectCalls.getMethodBind("GLTFMesh", "get_original_name", GET_ORIGINAL_NAME_HASH)

        private const val SET_ORIGINAL_NAME_HASH = 83702148L
        @JvmField
        val setOriginalNameBind =
            ObjectCalls.getMethodBind("GLTFMesh", "set_original_name", SET_ORIGINAL_NAME_HASH)

        private const val GET_MESH_HASH = 3754628756L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("GLTFMesh", "get_mesh", GET_MESH_HASH)

        private const val SET_MESH_HASH = 2255166972L
        @JvmField
        val setMeshBind =
            ObjectCalls.getMethodBind("GLTFMesh", "set_mesh", SET_MESH_HASH)

        private const val GET_BLEND_WEIGHTS_HASH = 2445143706L
        @JvmField
        val getBlendWeightsBind =
            ObjectCalls.getMethodBind("GLTFMesh", "get_blend_weights", GET_BLEND_WEIGHTS_HASH)

        private const val SET_BLEND_WEIGHTS_HASH = 2899603908L
        @JvmField
        val setBlendWeightsBind =
            ObjectCalls.getMethodBind("GLTFMesh", "set_blend_weights", SET_BLEND_WEIGHTS_HASH)

        private const val GET_INSTANCE_MATERIALS_HASH = 2915620761L
        @JvmField
        val getInstanceMaterialsBind =
            ObjectCalls.getMethodBind("GLTFMesh", "get_instance_materials", GET_INSTANCE_MATERIALS_HASH)

        private const val SET_INSTANCE_MATERIALS_HASH = 381264803L
        @JvmField
        val setInstanceMaterialsBind =
            ObjectCalls.getMethodBind("GLTFMesh", "set_instance_materials", SET_INSTANCE_MATERIALS_HASH)

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        @JvmField
        val getAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFMesh", "get_additional_data", GET_ADDITIONAL_DATA_HASH)

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        @JvmField
        val setAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFMesh", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
    }
}

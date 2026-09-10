package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: GLTFDocument
 */
open class GLTFDocument(handle: MemorySegment) : Resource(handle) {
    var imageFormat: String
        @JvmName("imageFormatProperty")
        get() = getImageFormat()
        @JvmName("setImageFormatProperty")
        set(value) = setImageFormat(value)

    var lossyQuality: Double
        @JvmName("lossyQualityProperty")
        get() = getLossyQuality()
        @JvmName("setLossyQualityProperty")
        set(value) = setLossyQuality(value)

    var fallbackImageFormat: String
        @JvmName("fallbackImageFormatProperty")
        get() = getFallbackImageFormat()
        @JvmName("setFallbackImageFormatProperty")
        set(value) = setFallbackImageFormat(value)

    var fallbackImageQuality: Double
        @JvmName("fallbackImageQualityProperty")
        get() = getFallbackImageQuality()
        @JvmName("setFallbackImageQualityProperty")
        set(value) = setFallbackImageQuality(value)

    var rootNodeMode: Long
        @JvmName("rootNodeModeProperty")
        get() = getRootNodeMode()
        @JvmName("setRootNodeModeProperty")
        set(value) = setRootNodeMode(value)

    var textureMapMode: Long
        @JvmName("textureMapModeProperty")
        get() = getTextureMapMode()
        @JvmName("setTextureMapModeProperty")
        set(value) = setTextureMapMode(value)

    var visibilityMode: Long
        @JvmName("visibilityModeProperty")
        get() = getVisibilityMode()
        @JvmName("setVisibilityModeProperty")
        set(value) = setVisibilityMode(value)

    fun setImageFormat(imageFormat: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setImageFormatBind, handle, imageFormat)
    }

    fun getImageFormat(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getImageFormatBind, handle)
    }

    fun setLossyQuality(lossyQuality: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setLossyQualityBind, handle, lossyQuality)
    }

    fun getLossyQuality(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLossyQualityBind, handle)
    }

    fun setFallbackImageFormat(fallbackImageFormat: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setFallbackImageFormatBind, handle, fallbackImageFormat)
    }

    fun getFallbackImageFormat(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFallbackImageFormatBind, handle)
    }

    fun setFallbackImageQuality(fallbackImageQuality: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFallbackImageQualityBind, handle, fallbackImageQuality)
    }

    fun getFallbackImageQuality(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFallbackImageQualityBind, handle)
    }

    fun setRootNodeMode(rootNodeMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setRootNodeModeBind, handle, rootNodeMode)
    }

    fun getRootNodeMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getRootNodeModeBind, handle)
    }

    fun setTextureMapMode(textureMapMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureMapModeBind, handle, textureMapMode)
    }

    fun getTextureMapMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureMapModeBind, handle)
    }

    fun setVisibilityMode(visibilityMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setVisibilityModeBind, handle, visibilityMode)
    }

    fun getVisibilityMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityModeBind, handle)
    }

    fun appendFromFile(path: String, state: GLTFState?, flags: Long = 0L, basePath: String = ""): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringObjectUInt32StringArgsRetLong(appendFromFileBind, handle, path, state?.requireOpenHandle() ?: MemorySegment.NULL, flags, basePath)
    }

    fun appendFromBuffer(bytes: ByteArray, basePath: String, state: GLTFState?, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithPackedByteArrayStringObjectUInt32ArgsRetLong(appendFromBufferBind, handle, bytes, basePath, state?.requireOpenHandle() ?: MemorySegment.NULL, flags)
    }

    fun appendFromScene(node: Node, state: GLTFState?, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoObjectUInt32ArgsRetLong(appendFromSceneBind, handle, node.handle, state?.requireOpenHandle() ?: MemorySegment.NULL, flags)
    }

    fun generateScene(state: GLTFState?, bakeFps: Double = 30.0, trimming: Boolean = false, removeImmutableTracks: Boolean = true): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallWithObjectDoubleTwoBoolArgsRetObject(generateSceneBind, handle, state?.requireOpenHandle() ?: MemorySegment.NULL, bakeFps, trimming, removeImmutableTracks))
    }

    fun generateBuffer(state: GLTFState?): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetByteArray(generateBufferBind, handle, state?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun writeToFilesystem(state: GLTFState?, path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndStringArgRetLong(writeToFilesystemBind, handle, state?.requireOpenHandle() ?: MemorySegment.NULL, path)
    }

    companion object {
        fun importObjectModelProperty(state: GLTFState?, jsonPointer: String): GLTFObjectModelProperty? {
            return GLTFObjectModelProperty.wrap(ObjectCalls.ptrcallWithObjectStringArgRetObject(importObjectModelPropertyBind, MemorySegment.NULL, state?.requireOpenHandle() ?: MemorySegment.NULL, jsonPointer))
        }

        fun exportObjectModelProperty(state: GLTFState?, nodePath: NodePath, godotNode: Node, gltfNodeIndex: Int): GLTFObjectModelProperty? {
            return GLTFObjectModelProperty.wrap(ObjectCalls.ptrcallWithObjectNodePathObjectIntArgsRetObject(exportObjectModelPropertyBind, MemorySegment.NULL, state?.requireOpenHandle() ?: MemorySegment.NULL, nodePath, godotNode.handle, gltfNodeIndex))
        }

        fun registerGltfDocumentExtension(extension: GLTFDocumentExtension?, firstPriority: Boolean = false) {
            ObjectCalls.ptrcallWithObjectAndBoolArg(registerGltfDocumentExtensionBind, MemorySegment.NULL, extension?.requireOpenHandle() ?: MemorySegment.NULL, firstPriority)
        }

        fun unregisterGltfDocumentExtension(extension: GLTFDocumentExtension?) {
            ObjectCalls.ptrcallWithObjectArgs(unregisterGltfDocumentExtensionBind, MemorySegment.NULL, listOf(extension?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        fun getSupportedGltfExtensions(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSupportedGltfExtensionsBind, MemorySegment.NULL)
        }

        const val ROOT_NODE_MODE_SINGLE_ROOT: Long = 0L
        const val ROOT_NODE_MODE_KEEP_ROOT: Long = 1L
        const val ROOT_NODE_MODE_MULTI_ROOT: Long = 2L
        const val TEXTURE_MAP_MODE_DO_NOT_REMAP: Long = 0L
        const val TEXTURE_MAP_MODE_REMAP_TO_STANDARD_MATERIAL: Long = 1L
        const val VISIBILITY_MODE_INCLUDE_REQUIRED: Long = 0L
        const val VISIBILITY_MODE_INCLUDE_OPTIONAL: Long = 1L
        const val VISIBILITY_MODE_EXCLUDE: Long = 2L
        const val IMPORT_FLAG_GENERATE_TANGENT_ARRAYS: Long = 8L
        const val IMPORT_FLAG_USE_NAMED_SKIN_BINDS: Long = 16L
        const val IMPORT_FLAG_DISCARD_MESHES_AND_MATERIALS: Long = 32L
        const val IMPORT_FLAG_FORCE_DISABLE_MESH_COMPRESSION: Long = 64L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFDocument? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFDocument? =
            if (handle.address() == 0L) null else GLTFDocument(handle)

        private const val SET_IMAGE_FORMAT_HASH = 83702148L
        private val setImageFormatBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_image_format", SET_IMAGE_FORMAT_HASH)
        }

        private const val GET_IMAGE_FORMAT_HASH = 201670096L
        private val getImageFormatBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_image_format", GET_IMAGE_FORMAT_HASH)
        }

        private const val SET_LOSSY_QUALITY_HASH = 373806689L
        private val setLossyQualityBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_lossy_quality", SET_LOSSY_QUALITY_HASH)
        }

        private const val GET_LOSSY_QUALITY_HASH = 1740695150L
        private val getLossyQualityBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_lossy_quality", GET_LOSSY_QUALITY_HASH)
        }

        private const val SET_FALLBACK_IMAGE_FORMAT_HASH = 83702148L
        private val setFallbackImageFormatBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_fallback_image_format", SET_FALLBACK_IMAGE_FORMAT_HASH)
        }

        private const val GET_FALLBACK_IMAGE_FORMAT_HASH = 201670096L
        private val getFallbackImageFormatBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_fallback_image_format", GET_FALLBACK_IMAGE_FORMAT_HASH)
        }

        private const val SET_FALLBACK_IMAGE_QUALITY_HASH = 373806689L
        private val setFallbackImageQualityBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_fallback_image_quality", SET_FALLBACK_IMAGE_QUALITY_HASH)
        }

        private const val GET_FALLBACK_IMAGE_QUALITY_HASH = 1740695150L
        private val getFallbackImageQualityBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_fallback_image_quality", GET_FALLBACK_IMAGE_QUALITY_HASH)
        }

        private const val SET_ROOT_NODE_MODE_HASH = 463633402L
        private val setRootNodeModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_root_node_mode", SET_ROOT_NODE_MODE_HASH)
        }

        private const val GET_ROOT_NODE_MODE_HASH = 948057992L
        private val getRootNodeModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_root_node_mode", GET_ROOT_NODE_MODE_HASH)
        }

        private const val SET_TEXTURE_MAP_MODE_HASH = 3144426102L
        private val setTextureMapModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_texture_map_mode", SET_TEXTURE_MAP_MODE_HASH)
        }

        private const val GET_TEXTURE_MAP_MODE_HASH = 2113256994L
        private val getTextureMapModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_texture_map_mode", GET_TEXTURE_MAP_MODE_HASH)
        }

        private const val SET_VISIBILITY_MODE_HASH = 2803579218L
        private val setVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "set_visibility_mode", SET_VISIBILITY_MODE_HASH)
        }

        private const val GET_VISIBILITY_MODE_HASH = 3885445962L
        private val getVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_visibility_mode", GET_VISIBILITY_MODE_HASH)
        }

        private const val APPEND_FROM_FILE_HASH = 866380864L
        private val appendFromFileBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "append_from_file", APPEND_FROM_FILE_HASH)
        }

        private const val APPEND_FROM_BUFFER_HASH = 1616081266L
        private val appendFromBufferBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "append_from_buffer", APPEND_FROM_BUFFER_HASH)
        }

        private const val APPEND_FROM_SCENE_HASH = 1622574258L
        private val appendFromSceneBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "append_from_scene", APPEND_FROM_SCENE_HASH)
        }

        private const val GENERATE_SCENE_HASH = 596118388L
        private val generateSceneBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "generate_scene", GENERATE_SCENE_HASH)
        }

        private const val GENERATE_BUFFER_HASH = 741783455L
        private val generateBufferBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "generate_buffer", GENERATE_BUFFER_HASH)
        }

        private const val WRITE_TO_FILESYSTEM_HASH = 1784551478L
        private val writeToFilesystemBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "write_to_filesystem", WRITE_TO_FILESYSTEM_HASH)
        }

        private const val IMPORT_OBJECT_MODEL_PROPERTY_HASH = 1206708632L
        private val importObjectModelPropertyBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "import_object_model_property", IMPORT_OBJECT_MODEL_PROPERTY_HASH)
        }

        private const val EXPORT_OBJECT_MODEL_PROPERTY_HASH = 314209806L
        private val exportObjectModelPropertyBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "export_object_model_property", EXPORT_OBJECT_MODEL_PROPERTY_HASH)
        }

        private const val REGISTER_GLTF_DOCUMENT_EXTENSION_HASH = 3752678331L
        private val registerGltfDocumentExtensionBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "register_gltf_document_extension", REGISTER_GLTF_DOCUMENT_EXTENSION_HASH)
        }

        private const val UNREGISTER_GLTF_DOCUMENT_EXTENSION_HASH = 2684415758L
        private val unregisterGltfDocumentExtensionBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "unregister_gltf_document_extension", UNREGISTER_GLTF_DOCUMENT_EXTENSION_HASH)
        }

        private const val GET_SUPPORTED_GLTF_EXTENSIONS_HASH = 2981934095L
        private val getSupportedGltfExtensionsBind by lazy {
            ObjectCalls.getMethodBind("GLTFDocument", "get_supported_gltf_extensions", GET_SUPPORTED_GLTF_EXTENSIONS_HASH)
        }
    }
}

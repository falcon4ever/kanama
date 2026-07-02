package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: GLTFObjectModelProperty
 */
class GLTFObjectModelProperty(handle: MemorySegment) : RefCounted(handle) {
    var gltfToGodotExpression: Expression?
        @JvmName("gltfToGodotExpressionProperty")
        get() = getGltfToGodotExpression()
        @JvmName("setGltfToGodotExpressionProperty")
        set(value) = setGltfToGodotExpression(value)

    var godotToGltfExpression: Expression?
        @JvmName("godotToGltfExpressionProperty")
        get() = getGodotToGltfExpression()
        @JvmName("setGodotToGltfExpressionProperty")
        set(value) = setGodotToGltfExpression(value)

    var nodePaths: List<NodePath>
        @JvmName("nodePathsProperty")
        get() = getNodePaths()
        @JvmName("setNodePathsProperty")
        set(value) = setNodePaths(value)

    var objectModelType: Long
        @JvmName("objectModelTypeProperty")
        get() = getObjectModelType()
        @JvmName("setObjectModelTypeProperty")
        set(value) = setObjectModelType(value)

    var jsonPointers: List<List<String>>
        @JvmName("jsonPointersProperty")
        get() = getJsonPointers()
        @JvmName("setJsonPointersProperty")
        set(value) = setJsonPointers(value)

    var variantType: Long
        @JvmName("variantTypeProperty")
        get() = getVariantType()
        @JvmName("setVariantTypeProperty")
        set(value) = setVariantType(value)

    fun appendNodePath(nodePath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(appendNodePathBind, handle, nodePath)
    }

    fun appendPathToProperty(nodePath: NodePath, propName: String) {
        ObjectCalls.ptrcallWithNodePathStringNameArgs(appendPathToPropertyBind, handle, nodePath, propName)
    }

    fun getAccessorType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAccessorTypeBind, handle)
    }

    fun getGltfToGodotExpression(): Expression? {
        return Expression.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGltfToGodotExpressionBind, handle))
    }

    fun setGltfToGodotExpression(gltfToGodotExpr: Expression?) {
        ObjectCalls.ptrcallWithObjectArgs(setGltfToGodotExpressionBind, handle, listOf(gltfToGodotExpr?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getGodotToGltfExpression(): Expression? {
        return Expression.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGodotToGltfExpressionBind, handle))
    }

    fun setGodotToGltfExpression(godotToGltfExpr: Expression?) {
        ObjectCalls.ptrcallWithObjectArgs(setGodotToGltfExpressionBind, handle, listOf(godotToGltfExpr?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNodePaths(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getNodePathsBind, handle)
    }

    fun hasNodePaths(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasNodePathsBind, handle)
    }

    fun setNodePaths(nodePaths: List<NodePath>) {
        ObjectCalls.ptrcallWithNodePathListArg(setNodePathsBind, handle, nodePaths)
    }

    fun getObjectModelType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getObjectModelTypeBind, handle)
    }

    fun setObjectModelType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setObjectModelTypeBind, handle, type)
    }

    fun getJsonPointers(): List<List<String>> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringListList(getJsonPointersBind, handle)
    }

    fun hasJsonPointers(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasJsonPointersBind, handle)
    }

    fun setJsonPointers(jsonPointers: List<List<String>>) {
        ObjectCalls.ptrcallWithPackedStringListListArg(setJsonPointersBind, handle, jsonPointers)
    }

    fun getVariantType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVariantTypeBind, handle)
    }

    fun setVariantType(variantType: Long) {
        ObjectCalls.ptrcallWithLongArg(setVariantTypeBind, handle, variantType)
    }

    fun setTypes(variantType: Long, objModelType: Long) {
        ObjectCalls.ptrcallWithTwoLongArgs(setTypesBind, handle, variantType, objModelType)
    }

    companion object {
        const val GLTF_OBJECT_MODEL_TYPE_UNKNOWN: Long = 0L
        const val GLTF_OBJECT_MODEL_TYPE_BOOL: Long = 1L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT: Long = 2L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT_ARRAY: Long = 3L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT2: Long = 4L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT3: Long = 5L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT4: Long = 6L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT2X2: Long = 7L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT3X3: Long = 8L
        const val GLTF_OBJECT_MODEL_TYPE_FLOAT4X4: Long = 9L
        const val GLTF_OBJECT_MODEL_TYPE_INT: Long = 10L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFObjectModelProperty? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFObjectModelProperty? =
            if (handle.address() == 0L) null else GLTFObjectModelProperty(handle)

        private const val APPEND_NODE_PATH_HASH = 1348162250L
        private val appendNodePathBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "append_node_path", APPEND_NODE_PATH_HASH)
        }

        private const val APPEND_PATH_TO_PROPERTY_HASH = 1331931644L
        private val appendPathToPropertyBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "append_path_to_property", APPEND_PATH_TO_PROPERTY_HASH)
        }

        private const val GET_ACCESSOR_TYPE_HASH = 1998183368L
        private val getAccessorTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_accessor_type", GET_ACCESSOR_TYPE_HASH)
        }

        private const val GET_GLTF_TO_GODOT_EXPRESSION_HASH = 2240072449L
        private val getGltfToGodotExpressionBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_gltf_to_godot_expression", GET_GLTF_TO_GODOT_EXPRESSION_HASH)
        }

        private const val SET_GLTF_TO_GODOT_EXPRESSION_HASH = 1815845073L
        private val setGltfToGodotExpressionBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_gltf_to_godot_expression", SET_GLTF_TO_GODOT_EXPRESSION_HASH)
        }

        private const val GET_GODOT_TO_GLTF_EXPRESSION_HASH = 2240072449L
        private val getGodotToGltfExpressionBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_godot_to_gltf_expression", GET_GODOT_TO_GLTF_EXPRESSION_HASH)
        }

        private const val SET_GODOT_TO_GLTF_EXPRESSION_HASH = 1815845073L
        private val setGodotToGltfExpressionBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_godot_to_gltf_expression", SET_GODOT_TO_GLTF_EXPRESSION_HASH)
        }

        private const val GET_NODE_PATHS_HASH = 3995934104L
        private val getNodePathsBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_node_paths", GET_NODE_PATHS_HASH)
        }

        private const val HAS_NODE_PATHS_HASH = 36873697L
        private val hasNodePathsBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "has_node_paths", HAS_NODE_PATHS_HASH)
        }

        private const val SET_NODE_PATHS_HASH = 381264803L
        private val setNodePathsBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_node_paths", SET_NODE_PATHS_HASH)
        }

        private const val GET_OBJECT_MODEL_TYPE_HASH = 1094778507L
        private val getObjectModelTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_object_model_type", GET_OBJECT_MODEL_TYPE_HASH)
        }

        private const val SET_OBJECT_MODEL_TYPE_HASH = 4108684086L
        private val setObjectModelTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_object_model_type", SET_OBJECT_MODEL_TYPE_HASH)
        }

        private const val GET_JSON_POINTERS_HASH = 3995934104L
        private val getJsonPointersBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_json_pointers", GET_JSON_POINTERS_HASH)
        }

        private const val HAS_JSON_POINTERS_HASH = 36873697L
        private val hasJsonPointersBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "has_json_pointers", HAS_JSON_POINTERS_HASH)
        }

        private const val SET_JSON_POINTERS_HASH = 381264803L
        private val setJsonPointersBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_json_pointers", SET_JSON_POINTERS_HASH)
        }

        private const val GET_VARIANT_TYPE_HASH = 3416842102L
        private val getVariantTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_variant_type", GET_VARIANT_TYPE_HASH)
        }

        private const val SET_VARIANT_TYPE_HASH = 2887708385L
        private val setVariantTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_variant_type", SET_VARIANT_TYPE_HASH)
        }

        private const val SET_TYPES_HASH = 4150728237L
        private val setTypesBind by lazy {
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_types", SET_TYPES_HASH)
        }
    }
}

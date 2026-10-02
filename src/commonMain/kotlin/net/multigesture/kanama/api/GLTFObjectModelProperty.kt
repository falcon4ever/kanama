package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: GLTFObjectModelProperty
 */
class GLTFObjectModelProperty(handle: GodotHandle) : RefCounted(handle) {
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

    var objectModelType: GLTFObjectModelProperty.GLTFObjectModelType
        @JvmName("objectModelTypeProperty")
        get() = getObjectModelType()
        @JvmName("setObjectModelTypeProperty")
        set(value) = setObjectModelType(value)

    var jsonPointers: List<List<String>>
        @JvmName("jsonPointersProperty")
        get() = getJsonPointers()
        @JvmName("setJsonPointersProperty")
        set(value) = setJsonPointers(value)

    var variantType: VariantType
        @JvmName("variantTypeProperty")
        get() = getVariantType()
        @JvmName("setVariantTypeProperty")
        set(value) = setVariantType(value)

    fun appendNodePath(nodePath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(appendNodePathBind, segment, nodePath)
    }

    fun appendPathToProperty(nodePath: NodePath, propName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathStringNameArgs(appendPathToPropertyBind, segment, nodePath, propName)
    }

    fun getAccessorType(): GLTFAccessor.GLTFAccessorType {
        checkOpen()
        return GLTFAccessor.GLTFAccessorType(ObjectCalls.ptrcallNoArgsRetLong(getAccessorTypeBind, segment))
    }

    fun getGltfToGodotExpression(): Expression? {
        checkOpen()
        return Expression.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGltfToGodotExpressionBind, segment))
    }

    fun setGltfToGodotExpression(gltfToGodotExpr: Expression?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setGltfToGodotExpressionBind, segment, listOf(gltfToGodotExpr?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getGodotToGltfExpression(): Expression? {
        checkOpen()
        return Expression.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGodotToGltfExpressionBind, segment))
    }

    fun setGodotToGltfExpression(godotToGltfExpr: Expression?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setGodotToGltfExpressionBind, segment, listOf(godotToGltfExpr?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getNodePaths(): List<NodePath> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getNodePathsBind, segment)
    }

    fun hasNodePaths(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasNodePathsBind, segment)
    }

    fun setNodePaths(nodePaths: List<NodePath>) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathListArg(setNodePathsBind, segment, nodePaths)
    }

    fun getObjectModelType(): GLTFObjectModelProperty.GLTFObjectModelType {
        checkOpen()
        return GLTFObjectModelProperty.GLTFObjectModelType(ObjectCalls.ptrcallNoArgsRetLong(getObjectModelTypeBind, segment))
    }

    fun setObjectModelType(type: GLTFObjectModelProperty.GLTFObjectModelType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setObjectModelTypeBind, segment, type.value)
    }

    fun getJsonPointers(): List<List<String>> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringListList(getJsonPointersBind, segment)
    }

    fun hasJsonPointers(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasJsonPointersBind, segment)
    }

    fun setJsonPointers(jsonPointers: List<List<String>>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListListArg(setJsonPointersBind, segment, jsonPointers)
    }

    fun getVariantType(): VariantType {
        checkOpen()
        return VariantType(ObjectCalls.ptrcallNoArgsRetLong(getVariantTypeBind, segment))
    }

    fun setVariantType(variantType: VariantType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setVariantTypeBind, segment, variantType.value)
    }

    fun setTypes(variantType: VariantType, objModelType: GLTFObjectModelProperty.GLTFObjectModelType) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(setTypesBind, segment, variantType.value, objModelType.value)
    }

    @JvmInline
    value class GLTFObjectModelType(override val value: Long) : GodotEnumValue {
        companion object {
            val UNKNOWN: GLTFObjectModelType get() = GLTFObjectModelType(0L)
            val BOOL: GLTFObjectModelType get() = GLTFObjectModelType(1L)
            val FLOAT: GLTFObjectModelType get() = GLTFObjectModelType(2L)
            val FLOAT_ARRAY: GLTFObjectModelType get() = GLTFObjectModelType(3L)
            val FLOAT2: GLTFObjectModelType get() = GLTFObjectModelType(4L)
            val FLOAT3: GLTFObjectModelType get() = GLTFObjectModelType(5L)
            val FLOAT4: GLTFObjectModelType get() = GLTFObjectModelType(6L)
            val FLOAT2X2: GLTFObjectModelType get() = GLTFObjectModelType(7L)
            val FLOAT3X3: GLTFObjectModelType get() = GLTFObjectModelType(8L)
            val FLOAT4X4: GLTFObjectModelType get() = GLTFObjectModelType(9L)
            val INT: GLTFObjectModelType get() = GLTFObjectModelType(10L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFObjectModelProperty? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GLTFObjectModelProperty? =
            if (handle.address() == 0L) null else GLTFObjectModelProperty(GodotHandle(handle))

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

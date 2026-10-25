package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Describes how to access a property as defined in the glTF object model.
 *
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

    /**
     * Appends a `NodePath` to `node_paths`. This can be used by `GLTFDocumentExtension` classes to
     * define how a glTF object model property maps to a Godot property, or multiple Godot properties.
     * Prefer using `append_path_to_property` for simple cases. Be sure to also call `set_types` once
     * (the order does not matter).
     *
     * Generated from Godot docs: GLTFObjectModelProperty.append_node_path
     */
    fun appendNodePath(nodePath: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.appendNodePathBind, segment, nodePath)
        ObjectCalls.keepReachable(this)
    }

    /**
     * High-level wrapper over `append_node_path` that handles the most common cases. It constructs a
     * new `NodePath` using `node_path` as a base and appends `prop_name` to the subpath. Be sure to
     * also call `set_types` once (the order does not matter).
     *
     * Generated from Godot docs: GLTFObjectModelProperty.append_path_to_property
     */
    fun appendPathToProperty(nodePath: NodePath, propName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathStringNameArgs(Binds.appendPathToPropertyBind, segment, nodePath, propName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The GLTF accessor type associated with this property's `object_model_type`. See
     * `GLTFAccessor.accessor_type` for possible values, and see `GLTFObjectModelType` for how the
     * object model type maps to accessor types.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_accessor_type
     */
    fun getAccessorType(): GLTFAccessor.GLTFAccessorType {
        checkOpen()
        val ret = GLTFAccessor.GLTFAccessorType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAccessorTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If set, this `Expression` will be used to convert the property value from the glTF object model
     * to the value expected by the Godot property. This is useful when the glTF object model uses a
     * different unit system, or when the data needs to be transformed in some way. If `null`, the
     * value will be copied as-is.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_gltf_to_godot_expression
     */
    fun getGltfToGodotExpression(): Expression? {
        checkOpen()
        val ret = Expression.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGltfToGodotExpressionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If set, this `Expression` will be used to convert the property value from the glTF object model
     * to the value expected by the Godot property. This is useful when the glTF object model uses a
     * different unit system, or when the data needs to be transformed in some way. If `null`, the
     * value will be copied as-is.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_gltf_to_godot_expression
     */
    fun setGltfToGodotExpression(gltfToGodotExpr: Expression?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setGltfToGodotExpressionBind, segment, listOf(gltfToGodotExpr?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(gltfToGodotExpr)
    }

    /**
     * If set, this `Expression` will be used to convert the property value from the Godot property to
     * the value expected by the glTF object model. This is useful when the glTF object model uses a
     * different unit system, or when the data needs to be transformed in some way. If `null`, the
     * value will be copied as-is.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_godot_to_gltf_expression
     */
    fun getGodotToGltfExpression(): Expression? {
        checkOpen()
        val ret = Expression.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGodotToGltfExpressionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If set, this `Expression` will be used to convert the property value from the Godot property to
     * the value expected by the glTF object model. This is useful when the glTF object model uses a
     * different unit system, or when the data needs to be transformed in some way. If `null`, the
     * value will be copied as-is.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_godot_to_gltf_expression
     */
    fun setGodotToGltfExpression(godotToGltfExpr: Expression?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setGodotToGltfExpressionBind, segment, listOf(godotToGltfExpr?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(godotToGltfExpr)
    }

    /**
     * An array of `NodePath`s that point to a property, or multiple properties, in the Godot scene
     * tree. On import, this will either be set by `GLTFDocument`, or by a `GLTFDocumentExtension`
     * class. For simple cases, use `append_path_to_property` to add properties to this array. In most
     * cases `node_paths` will only have one item, but in some cases a single glTF JSON pointer will
     * map to multiple Godot properties. For example, a `GLTFCamera` or `GLTFLight` used on multiple
     * glTF nodes will be represented by multiple Godot nodes.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_node_paths
     */
    fun getNodePaths(): List<NodePath> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetNodePathList(Binds.getNodePathsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if `node_paths` is not empty. This is used during import to determine if a
     * `GLTFObjectModelProperty` can handle converting a glTF object model property to a Godot
     * property.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.has_node_paths
     */
    fun hasNodePaths(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.hasNodePathsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * An array of `NodePath`s that point to a property, or multiple properties, in the Godot scene
     * tree. On import, this will either be set by `GLTFDocument`, or by a `GLTFDocumentExtension`
     * class. For simple cases, use `append_path_to_property` to add properties to this array. In most
     * cases `node_paths` will only have one item, but in some cases a single glTF JSON pointer will
     * map to multiple Godot properties. For example, a `GLTFCamera` or `GLTFLight` used on multiple
     * glTF nodes will be represented by multiple Godot nodes.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_node_paths
     */
    fun setNodePaths(nodePaths: List<NodePath>) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathListArg(Binds.setNodePathsBind, segment, nodePaths)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type of data stored in the glTF file as defined by the object model. This is a superset of
     * the available accessor types, and determines the accessor type.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_object_model_type
     */
    fun getObjectModelType(): GLTFObjectModelProperty.GLTFObjectModelType {
        checkOpen()
        val ret = GLTFObjectModelProperty.GLTFObjectModelType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getObjectModelTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of data stored in the glTF file as defined by the object model. This is a superset of
     * the available accessor types, and determines the accessor type.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_object_model_type
     */
    fun setObjectModelType(type: GLTFObjectModelProperty.GLTFObjectModelType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setObjectModelTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The glTF object model JSON pointers used to identify the property in the glTF object model. In
     * most cases, there will be only one item in this array, but specific cases may require multiple
     * pointers. The items are themselves arrays which represent the JSON pointer split into its
     * components.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_json_pointers
     */
    fun getJsonPointers(): List<List<String>> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringListList(Binds.getJsonPointersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if `json_pointers` is not empty. This is used during export to determine if a
     * `GLTFObjectModelProperty` can handle converting a Godot property to a glTF object model
     * property.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.has_json_pointers
     */
    fun hasJsonPointers(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.hasJsonPointersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The glTF object model JSON pointers used to identify the property in the glTF object model. In
     * most cases, there will be only one item in this array, but specific cases may require multiple
     * pointers. The items are themselves arrays which represent the JSON pointer split into its
     * components.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_json_pointers
     */
    fun setJsonPointers(jsonPointers: List<List<String>>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListListArg(Binds.setJsonPointersBind, segment, jsonPointers)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type of data stored in the Godot property. This is the type of the property that the
     * `node_paths` point to.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.get_variant_type
     */
    fun getVariantType(): VariantType {
        checkOpen()
        val ret = VariantType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVariantTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of data stored in the Godot property. This is the type of the property that the
     * `node_paths` point to.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_variant_type
     */
    fun setVariantType(variantType: VariantType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setVariantTypeBind, segment, variantType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the `variant_type` and `object_model_type` properties. This is a convenience method to set
     * both properties at once, since they are almost always known at the same time. This method should
     * be called once. Calling it again with the same values will have no effect.
     *
     * Generated from Godot docs: GLTFObjectModelProperty.set_types
     */
    fun setTypes(variantType: VariantType, objModelType: GLTFObjectModelProperty.GLTFObjectModelType) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(Binds.setTypesBind, segment, variantType.value, objModelType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Godot's `GLTFObjectModelProperty.GLTFObjectModelType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`GLTFObjectModelProperty.GLTFObjectModelType.<NAME>`).
     *
     * Generated from Godot docs: GLTFObjectModelProperty.GLTFObjectModelType
     */
    @JvmInline
    value class GLTFObjectModelType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Unknown or not set object model type. If the object model type is set to this value, the real
             * type still needs to be determined.
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_UNKNOWN
             */
            val UNKNOWN: GLTFObjectModelType get() = GLTFObjectModelType(0L)
            /**
             * Object model type "bool". Represented in the glTF JSON as a boolean, and encoded in a
             * `GLTFAccessor` as "SCALAR". When encoded in an accessor, a value of `0` is `false`, and any
             * other value is `true`.
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_BOOL
             */
            val BOOL: GLTFObjectModelType get() = GLTFObjectModelType(1L)
            /**
             * Object model type "float". Represented in the glTF JSON as a number, and encoded in a
             * `GLTFAccessor` as "SCALAR".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT
             */
            val FLOAT: GLTFObjectModelType get() = GLTFObjectModelType(2L)
            /**
             * Object model type "float`lb``rb`". Represented in the glTF JSON as an array of numbers, and
             * encoded in a `GLTFAccessor` as "SCALAR".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT_ARRAY
             */
            val FLOAT_ARRAY: GLTFObjectModelType get() = GLTFObjectModelType(3L)
            /**
             * Object model type "float2". Represented in the glTF JSON as an array of two numbers, and encoded
             * in a `GLTFAccessor` as "VEC2".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT2
             */
            val FLOAT2: GLTFObjectModelType get() = GLTFObjectModelType(4L)
            /**
             * Object model type "float3". Represented in the glTF JSON as an array of three numbers, and
             * encoded in a `GLTFAccessor` as "VEC3".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT3
             */
            val FLOAT3: GLTFObjectModelType get() = GLTFObjectModelType(5L)
            /**
             * Object model type "float4". Represented in the glTF JSON as an array of four numbers, and
             * encoded in a `GLTFAccessor` as "VEC4".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT4
             */
            val FLOAT4: GLTFObjectModelType get() = GLTFObjectModelType(6L)
            /**
             * Object model type "float2x2". Represented in the glTF JSON as an array of four numbers, and
             * encoded in a `GLTFAccessor` as "MAT2".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT2X2
             */
            val FLOAT2X2: GLTFObjectModelType get() = GLTFObjectModelType(7L)
            /**
             * Object model type "float3x3". Represented in the glTF JSON as an array of nine numbers, and
             * encoded in a `GLTFAccessor` as "MAT3".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT3X3
             */
            val FLOAT3X3: GLTFObjectModelType get() = GLTFObjectModelType(8L)
            /**
             * Object model type "float4x4". Represented in the glTF JSON as an array of sixteen numbers, and
             * encoded in a `GLTFAccessor` as "MAT4".
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_FLOAT4X4
             */
            val FLOAT4X4: GLTFObjectModelType get() = GLTFObjectModelType(9L)
            /**
             * Object model type "int". Represented in the glTF JSON as a number, and encoded in a
             * `GLTFAccessor` as "SCALAR". The range of values is limited to signed integers. For
             * `KHR_interactivity`, only 32-bit integers are supported.
             *
             * Generated from Godot docs: GLTFObjectModelProperty.GLTF_OBJECT_MODEL_TYPE_INT
             */
            val INT: GLTFObjectModelType get() = GLTFObjectModelType(10L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFObjectModelProperty? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFObjectModelProperty? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFObjectModelProperty(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFObjectModelProperty? =
            if (handle.address() == 0L) null else GLTFObjectModelProperty(GodotHandle(handle))

        /** A new GLTFObjectModelProperty, GDScript's `GLTFObjectModelProperty.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFObjectModelProperty =
            RefCounted.owned(GLTFObjectModelProperty(GodotHandle(ObjectCalls.constructObject("GLTFObjectModelProperty"))))
    }

    private object Binds {
        private const val APPEND_NODE_PATH_HASH = 1348162250L
        @JvmField
        val appendNodePathBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "append_node_path", APPEND_NODE_PATH_HASH)

        private const val APPEND_PATH_TO_PROPERTY_HASH = 1331931644L
        @JvmField
        val appendPathToPropertyBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "append_path_to_property", APPEND_PATH_TO_PROPERTY_HASH)

        private const val GET_ACCESSOR_TYPE_HASH = 1998183368L
        @JvmField
        val getAccessorTypeBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_accessor_type", GET_ACCESSOR_TYPE_HASH)

        private const val GET_GLTF_TO_GODOT_EXPRESSION_HASH = 2240072449L
        @JvmField
        val getGltfToGodotExpressionBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_gltf_to_godot_expression", GET_GLTF_TO_GODOT_EXPRESSION_HASH)

        private const val SET_GLTF_TO_GODOT_EXPRESSION_HASH = 1815845073L
        @JvmField
        val setGltfToGodotExpressionBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_gltf_to_godot_expression", SET_GLTF_TO_GODOT_EXPRESSION_HASH)

        private const val GET_GODOT_TO_GLTF_EXPRESSION_HASH = 2240072449L
        @JvmField
        val getGodotToGltfExpressionBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_godot_to_gltf_expression", GET_GODOT_TO_GLTF_EXPRESSION_HASH)

        private const val SET_GODOT_TO_GLTF_EXPRESSION_HASH = 1815845073L
        @JvmField
        val setGodotToGltfExpressionBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_godot_to_gltf_expression", SET_GODOT_TO_GLTF_EXPRESSION_HASH)

        private const val GET_NODE_PATHS_HASH = 3995934104L
        @JvmField
        val getNodePathsBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_node_paths", GET_NODE_PATHS_HASH)

        private const val HAS_NODE_PATHS_HASH = 36873697L
        @JvmField
        val hasNodePathsBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "has_node_paths", HAS_NODE_PATHS_HASH)

        private const val SET_NODE_PATHS_HASH = 381264803L
        @JvmField
        val setNodePathsBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_node_paths", SET_NODE_PATHS_HASH)

        private const val GET_OBJECT_MODEL_TYPE_HASH = 1094778507L
        @JvmField
        val getObjectModelTypeBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_object_model_type", GET_OBJECT_MODEL_TYPE_HASH)

        private const val SET_OBJECT_MODEL_TYPE_HASH = 4108684086L
        @JvmField
        val setObjectModelTypeBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_object_model_type", SET_OBJECT_MODEL_TYPE_HASH)

        private const val GET_JSON_POINTERS_HASH = 3995934104L
        @JvmField
        val getJsonPointersBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_json_pointers", GET_JSON_POINTERS_HASH)

        private const val HAS_JSON_POINTERS_HASH = 36873697L
        @JvmField
        val hasJsonPointersBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "has_json_pointers", HAS_JSON_POINTERS_HASH)

        private const val SET_JSON_POINTERS_HASH = 381264803L
        @JvmField
        val setJsonPointersBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_json_pointers", SET_JSON_POINTERS_HASH)

        private const val GET_VARIANT_TYPE_HASH = 3416842102L
        @JvmField
        val getVariantTypeBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_variant_type", GET_VARIANT_TYPE_HASH)

        private const val SET_VARIANT_TYPE_HASH = 2887708385L
        @JvmField
        val setVariantTypeBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_variant_type", SET_VARIANT_TYPE_HASH)

        private const val SET_TYPES_HASH = 4150728237L
        @JvmField
        val setTypesBind =
            ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_types", SET_TYPES_HASH)
    }
}

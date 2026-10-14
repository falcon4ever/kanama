package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a glTF accessor.
 *
 * Generated from Godot docs: GLTFAccessor
 */
class GLTFAccessor(handle: GodotHandle) : Resource(handle) {
    var bufferView: Int
        @JvmName("bufferViewProperty")
        get() = getBufferView()
        @JvmName("setBufferViewProperty")
        set(value) = setBufferView(value)

    var byteOffset: Long
        @JvmName("byteOffsetProperty")
        get() = getByteOffset()
        @JvmName("setByteOffsetProperty")
        set(value) = setByteOffset(value)

    var componentType: GLTFAccessor.GLTFComponentType
        @JvmName("componentTypeProperty")
        get() = getComponentType()
        @JvmName("setComponentTypeProperty")
        set(value) = setComponentType(value)

    var normalized: Boolean
        @JvmName("normalizedProperty")
        get() = getNormalized()
        @JvmName("setNormalizedProperty")
        set(value) = setNormalized(value)

    var count: Long
        @JvmName("countProperty")
        get() = getCount()
        @JvmName("setCountProperty")
        set(value) = setCount(value)

    var accessorType: GLTFAccessor.GLTFAccessorType
        @JvmName("accessorTypeProperty")
        get() = getAccessorType()
        @JvmName("setAccessorTypeProperty")
        set(value) = setAccessorType(value)

    var type: Int
        @JvmName("typeProperty")
        get() = getType()
        @JvmName("setTypeProperty")
        set(value) = setType(value)

    var min: List<Double>
        @JvmName("minProperty")
        get() = getMin()
        @JvmName("setMinProperty")
        set(value) = setMin(value)

    var max: List<Double>
        @JvmName("maxProperty")
        get() = getMax()
        @JvmName("setMaxProperty")
        set(value) = setMax(value)

    var sparseCount: Long
        @JvmName("sparseCountProperty")
        get() = getSparseCount()
        @JvmName("setSparseCountProperty")
        set(value) = setSparseCount(value)

    var sparseIndicesBufferView: Int
        @JvmName("sparseIndicesBufferViewProperty")
        get() = getSparseIndicesBufferView()
        @JvmName("setSparseIndicesBufferViewProperty")
        set(value) = setSparseIndicesBufferView(value)

    var sparseIndicesByteOffset: Long
        @JvmName("sparseIndicesByteOffsetProperty")
        get() = getSparseIndicesByteOffset()
        @JvmName("setSparseIndicesByteOffsetProperty")
        set(value) = setSparseIndicesByteOffset(value)

    var sparseIndicesComponentType: GLTFAccessor.GLTFComponentType
        @JvmName("sparseIndicesComponentTypeProperty")
        get() = getSparseIndicesComponentType()
        @JvmName("setSparseIndicesComponentTypeProperty")
        set(value) = setSparseIndicesComponentType(value)

    var sparseValuesBufferView: Int
        @JvmName("sparseValuesBufferViewProperty")
        get() = getSparseValuesBufferView()
        @JvmName("setSparseValuesBufferViewProperty")
        set(value) = setSparseValuesBufferView(value)

    var sparseValuesByteOffset: Long
        @JvmName("sparseValuesByteOffsetProperty")
        get() = getSparseValuesByteOffset()
        @JvmName("setSparseValuesByteOffsetProperty")
        set(value) = setSparseValuesByteOffset(value)

    /**
     * Serializes this GLTFAccessor instance into a `Dictionary`.
     *
     * Generated from Godot docs: GLTFAccessor.to_dictionary
     */
    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
    }

    /**
     * The index of the buffer view this accessor is referencing. If `-1`, this accessor is not
     * referencing any buffer view.
     *
     * Generated from Godot docs: GLTFAccessor.get_buffer_view
     */
    fun getBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBufferViewBind, segment)
    }

    /**
     * The index of the buffer view this accessor is referencing. If `-1`, this accessor is not
     * referencing any buffer view.
     *
     * Generated from Godot docs: GLTFAccessor.set_buffer_view
     */
    fun setBufferView(bufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBufferViewBind, segment, bufferView)
    }

    /**
     * The offset relative to the start of the buffer view in bytes.
     *
     * Generated from Godot docs: GLTFAccessor.get_byte_offset
     */
    fun getByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteOffsetBind, segment)
    }

    /**
     * The offset relative to the start of the buffer view in bytes.
     *
     * Generated from Godot docs: GLTFAccessor.set_byte_offset
     */
    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteOffsetBind, segment, byteOffset)
    }

    /**
     * The glTF component type as an enum. See `GLTFComponentType` for possible values. Within the core
     * glTF specification, a value of 5125 or "UNSIGNED_INT" must not be used for any accessor that is
     * not referenced by mesh.primitive.indices.
     *
     * Generated from Godot docs: GLTFAccessor.get_component_type
     */
    fun getComponentType(): GLTFAccessor.GLTFComponentType {
        checkOpen()
        return GLTFAccessor.GLTFComponentType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getComponentTypeBind, segment))
    }

    /**
     * The glTF component type as an enum. See `GLTFComponentType` for possible values. Within the core
     * glTF specification, a value of 5125 or "UNSIGNED_INT" must not be used for any accessor that is
     * not referenced by mesh.primitive.indices.
     *
     * Generated from Godot docs: GLTFAccessor.set_component_type
     */
    fun setComponentType(componentType: GLTFAccessor.GLTFComponentType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setComponentTypeBind, segment, componentType.value)
    }

    /**
     * Specifies whether integer data values are normalized before usage.
     *
     * Generated from Godot docs: GLTFAccessor.get_normalized
     */
    fun getNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getNormalizedBind, segment)
    }

    /**
     * Specifies whether integer data values are normalized before usage.
     *
     * Generated from Godot docs: GLTFAccessor.set_normalized
     */
    fun setNormalized(normalized: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNormalizedBind, segment, normalized)
    }

    /**
     * The number of elements referenced by this accessor.
     *
     * Generated from Godot docs: GLTFAccessor.get_count
     */
    fun getCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getCountBind, segment)
    }

    /**
     * The number of elements referenced by this accessor.
     *
     * Generated from Godot docs: GLTFAccessor.set_count
     */
    fun setCount(count: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCountBind, segment, count)
    }

    /**
     * The glTF accessor type, as an enum.
     *
     * Generated from Godot docs: GLTFAccessor.get_accessor_type
     */
    fun getAccessorType(): GLTFAccessor.GLTFAccessorType {
        checkOpen()
        return GLTFAccessor.GLTFAccessorType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAccessorTypeBind, segment))
    }

    /**
     * The glTF accessor type, as an enum.
     *
     * Generated from Godot docs: GLTFAccessor.set_accessor_type
     */
    fun setAccessorType(accessorType: GLTFAccessor.GLTFAccessorType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAccessorTypeBind, segment, accessorType.value)
    }

    /**
     * The glTF accessor type, as an `int`. Possible values are `0` for "SCALAR", `1` for "VEC2", `2`
     * for "VEC3", `3` for "VEC4", `4` for "MAT2", `5` for "MAT3", and `6` for "MAT4".
     *
     * Generated from Godot docs: GLTFAccessor.get_type
     */
    fun getType(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTypeBind, segment)
    }

    /**
     * The glTF accessor type, as an `int`. Possible values are `0` for "SCALAR", `1` for "VEC2", `2`
     * for "VEC3", `3` for "VEC4", `4` for "MAT2", `5` for "MAT3", and `6` for "MAT4".
     *
     * Generated from Godot docs: GLTFAccessor.set_type
     */
    fun setType(type: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setTypeBind, segment, type)
    }

    /**
     * Minimum value of each component in this accessor.
     *
     * Generated from Godot docs: GLTFAccessor.get_min
     */
    fun getMin(): List<Double> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(Binds.getMinBind, segment)
    }

    /**
     * Minimum value of each component in this accessor.
     *
     * Generated from Godot docs: GLTFAccessor.set_min
     */
    fun setMin(min: List<Double>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat64ListArg(Binds.setMinBind, segment, min)
    }

    /**
     * Maximum value of each component in this accessor.
     *
     * Generated from Godot docs: GLTFAccessor.get_max
     */
    fun getMax(): List<Double> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(Binds.getMaxBind, segment)
    }

    /**
     * Maximum value of each component in this accessor.
     *
     * Generated from Godot docs: GLTFAccessor.set_max
     */
    fun setMax(max: List<Double>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat64ListArg(Binds.setMaxBind, segment, max)
    }

    /**
     * Number of deviating accessor values stored in the sparse array.
     *
     * Generated from Godot docs: GLTFAccessor.get_sparse_count
     */
    fun getSparseCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseCountBind, segment)
    }

    /**
     * Number of deviating accessor values stored in the sparse array.
     *
     * Generated from Godot docs: GLTFAccessor.set_sparse_count
     */
    fun setSparseCount(sparseCount: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseCountBind, segment, sparseCount)
    }

    /**
     * The index of the buffer view with sparse indices. The referenced buffer view MUST NOT have its
     * target or byteStride properties defined. The buffer view and the optional byteOffset MUST be
     * aligned to the componentType byte length.
     *
     * Generated from Godot docs: GLTFAccessor.get_sparse_indices_buffer_view
     */
    fun getSparseIndicesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSparseIndicesBufferViewBind, segment)
    }

    /**
     * The index of the buffer view with sparse indices. The referenced buffer view MUST NOT have its
     * target or byteStride properties defined. The buffer view and the optional byteOffset MUST be
     * aligned to the componentType byte length.
     *
     * Generated from Godot docs: GLTFAccessor.set_sparse_indices_buffer_view
     */
    fun setSparseIndicesBufferView(sparseIndicesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSparseIndicesBufferViewBind, segment, sparseIndicesBufferView)
    }

    /**
     * The offset relative to the start of the buffer view in bytes.
     *
     * Generated from Godot docs: GLTFAccessor.get_sparse_indices_byte_offset
     */
    fun getSparseIndicesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseIndicesByteOffsetBind, segment)
    }

    /**
     * The offset relative to the start of the buffer view in bytes.
     *
     * Generated from Godot docs: GLTFAccessor.set_sparse_indices_byte_offset
     */
    fun setSparseIndicesByteOffset(sparseIndicesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseIndicesByteOffsetBind, segment, sparseIndicesByteOffset)
    }

    /**
     * The indices component data type as an enum. Possible values are 5121 for "UNSIGNED_BYTE", 5123
     * for "UNSIGNED_SHORT", and 5125 for "UNSIGNED_INT".
     *
     * Generated from Godot docs: GLTFAccessor.get_sparse_indices_component_type
     */
    fun getSparseIndicesComponentType(): GLTFAccessor.GLTFComponentType {
        checkOpen()
        return GLTFAccessor.GLTFComponentType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseIndicesComponentTypeBind, segment))
    }

    /**
     * The indices component data type as an enum. Possible values are 5121 for "UNSIGNED_BYTE", 5123
     * for "UNSIGNED_SHORT", and 5125 for "UNSIGNED_INT".
     *
     * Generated from Godot docs: GLTFAccessor.set_sparse_indices_component_type
     */
    fun setSparseIndicesComponentType(sparseIndicesComponentType: GLTFAccessor.GLTFComponentType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseIndicesComponentTypeBind, segment, sparseIndicesComponentType.value)
    }

    /**
     * The index of the bufferView with sparse values. The referenced buffer view MUST NOT have its
     * target or byteStride properties defined.
     *
     * Generated from Godot docs: GLTFAccessor.get_sparse_values_buffer_view
     */
    fun getSparseValuesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSparseValuesBufferViewBind, segment)
    }

    /**
     * The index of the bufferView with sparse values. The referenced buffer view MUST NOT have its
     * target or byteStride properties defined.
     *
     * Generated from Godot docs: GLTFAccessor.set_sparse_values_buffer_view
     */
    fun setSparseValuesBufferView(sparseValuesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSparseValuesBufferViewBind, segment, sparseValuesBufferView)
    }

    /**
     * The offset relative to the start of the bufferView in bytes.
     *
     * Generated from Godot docs: GLTFAccessor.get_sparse_values_byte_offset
     */
    fun getSparseValuesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseValuesByteOffsetBind, segment)
    }

    /**
     * The offset relative to the start of the bufferView in bytes.
     *
     * Generated from Godot docs: GLTFAccessor.set_sparse_values_byte_offset
     */
    fun setSparseValuesByteOffset(sparseValuesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseValuesByteOffsetBind, segment, sparseValuesByteOffset)
    }

    /**
     * Godot's `GLTFAccessor.GLTFAccessorType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GLTFAccessor.GLTFAccessorType.<NAME>`).
     *
     * Generated from Godot docs: GLTFAccessor.GLTFAccessorType
     */
    @JvmInline
    value class GLTFAccessorType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Accessor type "SCALAR". For the glTF object model, this can be used to map to a single float,
             * int, or bool value, or a float array.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_SCALAR
             */
            val SCALAR: GLTFAccessorType get() = GLTFAccessorType(0L)
            /**
             * Accessor type "VEC2". For the glTF object model, this maps to "float2", represented in the glTF
             * JSON as an array of two floats.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_VEC2
             */
            val VEC2: GLTFAccessorType get() = GLTFAccessorType(1L)
            /**
             * Accessor type "VEC3". For the glTF object model, this maps to "float3", represented in the glTF
             * JSON as an array of three floats.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_VEC3
             */
            val VEC3: GLTFAccessorType get() = GLTFAccessorType(2L)
            /**
             * Accessor type "VEC4". For the glTF object model, this maps to "float4", represented in the glTF
             * JSON as an array of four floats.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_VEC4
             */
            val VEC4: GLTFAccessorType get() = GLTFAccessorType(3L)
            /**
             * Accessor type "MAT2". For the glTF object model, this maps to "float2x2", represented in the
             * glTF JSON as an array of four floats.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_MAT2
             */
            val MAT2: GLTFAccessorType get() = GLTFAccessorType(4L)
            /**
             * Accessor type "MAT3". For the glTF object model, this maps to "float3x3", represented in the
             * glTF JSON as an array of nine floats.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_MAT3
             */
            val MAT3: GLTFAccessorType get() = GLTFAccessorType(5L)
            /**
             * Accessor type "MAT4". For the glTF object model, this maps to "float4x4", represented in the
             * glTF JSON as an array of sixteen floats.
             *
             * Generated from Godot docs: GLTFAccessor.TYPE_MAT4
             */
            val MAT4: GLTFAccessorType get() = GLTFAccessorType(6L)
        }
    }

    /**
     * Godot's `GLTFAccessor.GLTFComponentType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GLTFAccessor.GLTFComponentType.<NAME>`).
     *
     * Generated from Godot docs: GLTFAccessor.GLTFComponentType
     */
    @JvmInline
    value class GLTFComponentType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Component type "NONE". This is not a valid component type, and is used to indicate that the
             * component type is not set.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_NONE
             */
            val NONE: GLTFComponentType get() = GLTFComponentType(0L)
            /**
             * Component type "BYTE". The value is `0x1400` which comes from OpenGL. This indicates data is
             * stored in 1-byte or 8-bit signed integers. This is a core part of the glTF specification.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_SIGNED_BYTE
             */
            val SIGNED_BYTE: GLTFComponentType get() = GLTFComponentType(5120L)
            /**
             * Component type "UNSIGNED_BYTE". The value is `0x1401` which comes from OpenGL. This indicates
             * data is stored in 1-byte or 8-bit unsigned integers. This is a core part of the glTF
             * specification.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_UNSIGNED_BYTE
             */
            val UNSIGNED_BYTE: GLTFComponentType get() = GLTFComponentType(5121L)
            /**
             * Component type "SHORT". The value is `0x1402` which comes from OpenGL. This indicates data is
             * stored in 2-byte or 16-bit signed integers. This is a core part of the glTF specification.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_SIGNED_SHORT
             */
            val SIGNED_SHORT: GLTFComponentType get() = GLTFComponentType(5122L)
            /**
             * Component type "UNSIGNED_SHORT". The value is `0x1403` which comes from OpenGL. This indicates
             * data is stored in 2-byte or 16-bit unsigned integers. This is a core part of the glTF
             * specification.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_UNSIGNED_SHORT
             */
            val UNSIGNED_SHORT: GLTFComponentType get() = GLTFComponentType(5123L)
            /**
             * Component type "INT". The value is `0x1404` which comes from OpenGL. This indicates data is
             * stored in 4-byte or 32-bit signed integers. This is NOT a core part of the glTF specification,
             * and may not be supported by all glTF importers. May be used by some extensions including
             * `KHR_interactivity`.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_SIGNED_INT
             */
            val SIGNED_INT: GLTFComponentType get() = GLTFComponentType(5124L)
            /**
             * Component type "UNSIGNED_INT". The value is `0x1405` which comes from OpenGL. This indicates
             * data is stored in 4-byte or 32-bit unsigned integers. This is a core part of the glTF
             * specification.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_UNSIGNED_INT
             */
            val UNSIGNED_INT: GLTFComponentType get() = GLTFComponentType(5125L)
            /**
             * Component type "FLOAT". The value is `0x1406` which comes from OpenGL. This indicates data is
             * stored in 4-byte or 32-bit floating-point numbers. This is a core part of the glTF
             * specification.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_SINGLE_FLOAT
             */
            val SINGLE_FLOAT: GLTFComponentType get() = GLTFComponentType(5126L)
            /**
             * Component type "DOUBLE". The value is `0x140A` which comes from OpenGL. This indicates data is
             * stored in 8-byte or 64-bit floating-point numbers. This is NOT a core part of the glTF
             * specification, and may not be supported by all glTF importers. May be used by some extensions
             * including `KHR_interactivity`.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_DOUBLE_FLOAT
             */
            val DOUBLE_FLOAT: GLTFComponentType get() = GLTFComponentType(5130L)
            /**
             * Component type "HALF_FLOAT". The value is `0x140B` which comes from OpenGL. This indicates data
             * is stored in 2-byte or 16-bit floating-point numbers. This is NOT a core part of the glTF
             * specification, and may not be supported by all glTF importers. May be used by some extensions
             * including `KHR_interactivity`.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_HALF_FLOAT
             */
            val HALF_FLOAT: GLTFComponentType get() = GLTFComponentType(5131L)
            /**
             * Component type "LONG". The value is `0x140E` which comes from OpenGL. This indicates data is
             * stored in 8-byte or 64-bit signed integers. This is NOT a core part of the glTF specification,
             * and may not be supported by all glTF importers. May be used by some extensions including
             * `KHR_interactivity`.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_SIGNED_LONG
             */
            val SIGNED_LONG: GLTFComponentType get() = GLTFComponentType(5134L)
            /**
             * Component type "UNSIGNED_LONG". The value is `0x140F` which comes from OpenGL. This indicates
             * data is stored in 8-byte or 64-bit unsigned integers. This is NOT a core part of the glTF
             * specification, and may not be supported by all glTF importers. May be used by some extensions
             * including `KHR_interactivity`.
             *
             * Generated from Godot docs: GLTFAccessor.COMPONENT_TYPE_UNSIGNED_LONG
             */
            val UNSIGNED_LONG: GLTFComponentType get() = GLTFComponentType(5135L)
        }
    }

    companion object {
        /**
         * Creates a new GLTFAccessor instance by parsing the given `Dictionary`.
         *
         * Generated from Godot docs: GLTFAccessor.from_dictionary
         */
        fun fromDictionary(dictionary: Map<String, Any?>): GLTFAccessor? {
            return GLTFAccessor.wrapOwned(ObjectCalls.ptrcallWithDictionaryArgRetObject(Binds.fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFAccessor? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFAccessor? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFAccessor(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFAccessor? =
            if (handle.address() == 0L) null else GLTFAccessor(GodotHandle(handle))
    }

    private object Binds {
        private const val FROM_DICTIONARY_HASH = 3495091019L
        @JvmField
        val fromDictionaryBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "from_dictionary", FROM_DICTIONARY_HASH)

        private const val TO_DICTIONARY_HASH = 3102165223L
        @JvmField
        val toDictionaryBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "to_dictionary", TO_DICTIONARY_HASH)

        private const val GET_BUFFER_VIEW_HASH = 3905245786L
        @JvmField
        val getBufferViewBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_buffer_view", GET_BUFFER_VIEW_HASH)

        private const val SET_BUFFER_VIEW_HASH = 1286410249L
        @JvmField
        val setBufferViewBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_buffer_view", SET_BUFFER_VIEW_HASH)

        private const val GET_BYTE_OFFSET_HASH = 3905245786L
        @JvmField
        val getByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_byte_offset", GET_BYTE_OFFSET_HASH)

        private const val SET_BYTE_OFFSET_HASH = 1286410249L
        @JvmField
        val setByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_byte_offset", SET_BYTE_OFFSET_HASH)

        private const val GET_COMPONENT_TYPE_HASH = 852227802L
        @JvmField
        val getComponentTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_component_type", GET_COMPONENT_TYPE_HASH)

        private const val SET_COMPONENT_TYPE_HASH = 1780020221L
        @JvmField
        val setComponentTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_component_type", SET_COMPONENT_TYPE_HASH)

        private const val GET_NORMALIZED_HASH = 36873697L
        @JvmField
        val getNormalizedBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_normalized", GET_NORMALIZED_HASH)

        private const val SET_NORMALIZED_HASH = 2586408642L
        @JvmField
        val setNormalizedBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_normalized", SET_NORMALIZED_HASH)

        private const val GET_COUNT_HASH = 3905245786L
        @JvmField
        val getCountBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_count", GET_COUNT_HASH)

        private const val SET_COUNT_HASH = 1286410249L
        @JvmField
        val setCountBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_count", SET_COUNT_HASH)

        private const val GET_ACCESSOR_TYPE_HASH = 1998183368L
        @JvmField
        val getAccessorTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_accessor_type", GET_ACCESSOR_TYPE_HASH)

        private const val SET_ACCESSOR_TYPE_HASH = 2347728198L
        @JvmField
        val setAccessorTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_accessor_type", SET_ACCESSOR_TYPE_HASH)

        private const val GET_TYPE_HASH = 3905245786L
        @JvmField
        val getTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_type", GET_TYPE_HASH)

        private const val SET_TYPE_HASH = 1286410249L
        @JvmField
        val setTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_type", SET_TYPE_HASH)

        private const val GET_MIN_HASH = 547233126L
        @JvmField
        val getMinBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_min", GET_MIN_HASH)

        private const val SET_MIN_HASH = 2576592201L
        @JvmField
        val setMinBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_min", SET_MIN_HASH)

        private const val GET_MAX_HASH = 547233126L
        @JvmField
        val getMaxBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_max", GET_MAX_HASH)

        private const val SET_MAX_HASH = 2576592201L
        @JvmField
        val setMaxBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_max", SET_MAX_HASH)

        private const val GET_SPARSE_COUNT_HASH = 3905245786L
        @JvmField
        val getSparseCountBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_count", GET_SPARSE_COUNT_HASH)

        private const val SET_SPARSE_COUNT_HASH = 1286410249L
        @JvmField
        val setSparseCountBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_count", SET_SPARSE_COUNT_HASH)

        private const val GET_SPARSE_INDICES_BUFFER_VIEW_HASH = 3905245786L
        @JvmField
        val getSparseIndicesBufferViewBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_indices_buffer_view", GET_SPARSE_INDICES_BUFFER_VIEW_HASH)

        private const val SET_SPARSE_INDICES_BUFFER_VIEW_HASH = 1286410249L
        @JvmField
        val setSparseIndicesBufferViewBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_indices_buffer_view", SET_SPARSE_INDICES_BUFFER_VIEW_HASH)

        private const val GET_SPARSE_INDICES_BYTE_OFFSET_HASH = 3905245786L
        @JvmField
        val getSparseIndicesByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_indices_byte_offset", GET_SPARSE_INDICES_BYTE_OFFSET_HASH)

        private const val SET_SPARSE_INDICES_BYTE_OFFSET_HASH = 1286410249L
        @JvmField
        val setSparseIndicesByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_indices_byte_offset", SET_SPARSE_INDICES_BYTE_OFFSET_HASH)

        private const val GET_SPARSE_INDICES_COMPONENT_TYPE_HASH = 852227802L
        @JvmField
        val getSparseIndicesComponentTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_indices_component_type", GET_SPARSE_INDICES_COMPONENT_TYPE_HASH)

        private const val SET_SPARSE_INDICES_COMPONENT_TYPE_HASH = 1780020221L
        @JvmField
        val setSparseIndicesComponentTypeBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_indices_component_type", SET_SPARSE_INDICES_COMPONENT_TYPE_HASH)

        private const val GET_SPARSE_VALUES_BUFFER_VIEW_HASH = 3905245786L
        @JvmField
        val getSparseValuesBufferViewBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_values_buffer_view", GET_SPARSE_VALUES_BUFFER_VIEW_HASH)

        private const val SET_SPARSE_VALUES_BUFFER_VIEW_HASH = 1286410249L
        @JvmField
        val setSparseValuesBufferViewBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_values_buffer_view", SET_SPARSE_VALUES_BUFFER_VIEW_HASH)

        private const val GET_SPARSE_VALUES_BYTE_OFFSET_HASH = 3905245786L
        @JvmField
        val getSparseValuesByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_values_byte_offset", GET_SPARSE_VALUES_BYTE_OFFSET_HASH)

        private const val SET_SPARSE_VALUES_BYTE_OFFSET_HASH = 1286410249L
        @JvmField
        val setSparseValuesByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_values_byte_offset", SET_SPARSE_VALUES_BYTE_OFFSET_HASH)
    }
}

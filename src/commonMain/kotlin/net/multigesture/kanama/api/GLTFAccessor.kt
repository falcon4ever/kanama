package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, segment)
    }

    fun getBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferViewBind, segment)
    }

    fun setBufferView(bufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setBufferViewBind, segment, bufferView)
    }

    fun getByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getByteOffsetBind, segment)
    }

    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setByteOffsetBind, segment, byteOffset)
    }

    fun getComponentType(): GLTFAccessor.GLTFComponentType {
        checkOpen()
        return GLTFAccessor.GLTFComponentType(ObjectCalls.ptrcallNoArgsRetLong(getComponentTypeBind, segment))
    }

    fun setComponentType(componentType: GLTFAccessor.GLTFComponentType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setComponentTypeBind, segment, componentType.value)
    }

    fun getNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getNormalizedBind, segment)
    }

    fun setNormalized(normalized: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setNormalizedBind, segment, normalized)
    }

    fun getCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getCountBind, segment)
    }

    fun setCount(count: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCountBind, segment, count)
    }

    fun getAccessorType(): GLTFAccessor.GLTFAccessorType {
        checkOpen()
        return GLTFAccessor.GLTFAccessorType(ObjectCalls.ptrcallNoArgsRetLong(getAccessorTypeBind, segment))
    }

    fun setAccessorType(accessorType: GLTFAccessor.GLTFAccessorType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAccessorTypeBind, segment, accessorType.value)
    }

    fun getType(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getTypeBind, segment)
    }

    fun setType(type: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setTypeBind, segment, type)
    }

    fun getMin(): List<Double> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(getMinBind, segment)
    }

    fun setMin(min: List<Double>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat64ListArg(setMinBind, segment, min)
    }

    fun getMax(): List<Double> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(getMaxBind, segment)
    }

    fun setMax(max: List<Double>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat64ListArg(setMaxBind, segment, max)
    }

    fun getSparseCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseCountBind, segment)
    }

    fun setSparseCount(sparseCount: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseCountBind, segment, sparseCount)
    }

    fun getSparseIndicesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSparseIndicesBufferViewBind, segment)
    }

    fun setSparseIndicesBufferView(sparseIndicesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSparseIndicesBufferViewBind, segment, sparseIndicesBufferView)
    }

    fun getSparseIndicesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseIndicesByteOffsetBind, segment)
    }

    fun setSparseIndicesByteOffset(sparseIndicesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseIndicesByteOffsetBind, segment, sparseIndicesByteOffset)
    }

    fun getSparseIndicesComponentType(): GLTFAccessor.GLTFComponentType {
        checkOpen()
        return GLTFAccessor.GLTFComponentType(ObjectCalls.ptrcallNoArgsRetLong(getSparseIndicesComponentTypeBind, segment))
    }

    fun setSparseIndicesComponentType(sparseIndicesComponentType: GLTFAccessor.GLTFComponentType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseIndicesComponentTypeBind, segment, sparseIndicesComponentType.value)
    }

    fun getSparseValuesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSparseValuesBufferViewBind, segment)
    }

    fun setSparseValuesBufferView(sparseValuesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSparseValuesBufferViewBind, segment, sparseValuesBufferView)
    }

    fun getSparseValuesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseValuesByteOffsetBind, segment)
    }

    fun setSparseValuesByteOffset(sparseValuesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseValuesByteOffsetBind, segment, sparseValuesByteOffset)
    }

    @JvmInline
    value class GLTFAccessorType(val value: Long) {
        companion object {
            val SCALAR: GLTFAccessorType get() = GLTFAccessorType(0L)
            val VEC2: GLTFAccessorType get() = GLTFAccessorType(1L)
            val VEC3: GLTFAccessorType get() = GLTFAccessorType(2L)
            val VEC4: GLTFAccessorType get() = GLTFAccessorType(3L)
            val MAT2: GLTFAccessorType get() = GLTFAccessorType(4L)
            val MAT3: GLTFAccessorType get() = GLTFAccessorType(5L)
            val MAT4: GLTFAccessorType get() = GLTFAccessorType(6L)
        }
    }

    @JvmInline
    value class GLTFComponentType(val value: Long) {
        companion object {
            val NONE: GLTFComponentType get() = GLTFComponentType(0L)
            val SIGNED_BYTE: GLTFComponentType get() = GLTFComponentType(5120L)
            val UNSIGNED_BYTE: GLTFComponentType get() = GLTFComponentType(5121L)
            val SIGNED_SHORT: GLTFComponentType get() = GLTFComponentType(5122L)
            val UNSIGNED_SHORT: GLTFComponentType get() = GLTFComponentType(5123L)
            val SIGNED_INT: GLTFComponentType get() = GLTFComponentType(5124L)
            val UNSIGNED_INT: GLTFComponentType get() = GLTFComponentType(5125L)
            val SINGLE_FLOAT: GLTFComponentType get() = GLTFComponentType(5126L)
            val DOUBLE_FLOAT: GLTFComponentType get() = GLTFComponentType(5130L)
            val HALF_FLOAT: GLTFComponentType get() = GLTFComponentType(5131L)
            val SIGNED_LONG: GLTFComponentType get() = GLTFComponentType(5134L)
            val UNSIGNED_LONG: GLTFComponentType get() = GLTFComponentType(5135L)
        }
    }

    companion object {
        fun fromDictionary(dictionary: Map<String, Any?>): GLTFAccessor? {
            return GLTFAccessor.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFAccessor? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GLTFAccessor? =
            if (handle.address() == 0L) null else GLTFAccessor(GodotHandle(handle))

        private const val FROM_DICTIONARY_HASH = 3495091019L
        private val fromDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "from_dictionary", FROM_DICTIONARY_HASH)
        }

        private const val TO_DICTIONARY_HASH = 3102165223L
        private val toDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "to_dictionary", TO_DICTIONARY_HASH)
        }

        private const val GET_BUFFER_VIEW_HASH = 3905245786L
        private val getBufferViewBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_buffer_view", GET_BUFFER_VIEW_HASH)
        }

        private const val SET_BUFFER_VIEW_HASH = 1286410249L
        private val setBufferViewBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_buffer_view", SET_BUFFER_VIEW_HASH)
        }

        private const val GET_BYTE_OFFSET_HASH = 3905245786L
        private val getByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_byte_offset", GET_BYTE_OFFSET_HASH)
        }

        private const val SET_BYTE_OFFSET_HASH = 1286410249L
        private val setByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_byte_offset", SET_BYTE_OFFSET_HASH)
        }

        private const val GET_COMPONENT_TYPE_HASH = 852227802L
        private val getComponentTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_component_type", GET_COMPONENT_TYPE_HASH)
        }

        private const val SET_COMPONENT_TYPE_HASH = 1780020221L
        private val setComponentTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_component_type", SET_COMPONENT_TYPE_HASH)
        }

        private const val GET_NORMALIZED_HASH = 36873697L
        private val getNormalizedBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_normalized", GET_NORMALIZED_HASH)
        }

        private const val SET_NORMALIZED_HASH = 2586408642L
        private val setNormalizedBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_normalized", SET_NORMALIZED_HASH)
        }

        private const val GET_COUNT_HASH = 3905245786L
        private val getCountBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_count", GET_COUNT_HASH)
        }

        private const val SET_COUNT_HASH = 1286410249L
        private val setCountBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_count", SET_COUNT_HASH)
        }

        private const val GET_ACCESSOR_TYPE_HASH = 1998183368L
        private val getAccessorTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_accessor_type", GET_ACCESSOR_TYPE_HASH)
        }

        private const val SET_ACCESSOR_TYPE_HASH = 2347728198L
        private val setAccessorTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_accessor_type", SET_ACCESSOR_TYPE_HASH)
        }

        private const val GET_TYPE_HASH = 3905245786L
        private val getTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_type", GET_TYPE_HASH)
        }

        private const val SET_TYPE_HASH = 1286410249L
        private val setTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_type", SET_TYPE_HASH)
        }

        private const val GET_MIN_HASH = 547233126L
        private val getMinBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_min", GET_MIN_HASH)
        }

        private const val SET_MIN_HASH = 2576592201L
        private val setMinBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_min", SET_MIN_HASH)
        }

        private const val GET_MAX_HASH = 547233126L
        private val getMaxBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_max", GET_MAX_HASH)
        }

        private const val SET_MAX_HASH = 2576592201L
        private val setMaxBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_max", SET_MAX_HASH)
        }

        private const val GET_SPARSE_COUNT_HASH = 3905245786L
        private val getSparseCountBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_count", GET_SPARSE_COUNT_HASH)
        }

        private const val SET_SPARSE_COUNT_HASH = 1286410249L
        private val setSparseCountBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_count", SET_SPARSE_COUNT_HASH)
        }

        private const val GET_SPARSE_INDICES_BUFFER_VIEW_HASH = 3905245786L
        private val getSparseIndicesBufferViewBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_indices_buffer_view", GET_SPARSE_INDICES_BUFFER_VIEW_HASH)
        }

        private const val SET_SPARSE_INDICES_BUFFER_VIEW_HASH = 1286410249L
        private val setSparseIndicesBufferViewBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_indices_buffer_view", SET_SPARSE_INDICES_BUFFER_VIEW_HASH)
        }

        private const val GET_SPARSE_INDICES_BYTE_OFFSET_HASH = 3905245786L
        private val getSparseIndicesByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_indices_byte_offset", GET_SPARSE_INDICES_BYTE_OFFSET_HASH)
        }

        private const val SET_SPARSE_INDICES_BYTE_OFFSET_HASH = 1286410249L
        private val setSparseIndicesByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_indices_byte_offset", SET_SPARSE_INDICES_BYTE_OFFSET_HASH)
        }

        private const val GET_SPARSE_INDICES_COMPONENT_TYPE_HASH = 852227802L
        private val getSparseIndicesComponentTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_indices_component_type", GET_SPARSE_INDICES_COMPONENT_TYPE_HASH)
        }

        private const val SET_SPARSE_INDICES_COMPONENT_TYPE_HASH = 1780020221L
        private val setSparseIndicesComponentTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_indices_component_type", SET_SPARSE_INDICES_COMPONENT_TYPE_HASH)
        }

        private const val GET_SPARSE_VALUES_BUFFER_VIEW_HASH = 3905245786L
        private val getSparseValuesBufferViewBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_values_buffer_view", GET_SPARSE_VALUES_BUFFER_VIEW_HASH)
        }

        private const val SET_SPARSE_VALUES_BUFFER_VIEW_HASH = 1286410249L
        private val setSparseValuesBufferViewBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_values_buffer_view", SET_SPARSE_VALUES_BUFFER_VIEW_HASH)
        }

        private const val GET_SPARSE_VALUES_BYTE_OFFSET_HASH = 3905245786L
        private val getSparseValuesByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "get_sparse_values_byte_offset", GET_SPARSE_VALUES_BYTE_OFFSET_HASH)
        }

        private const val SET_SPARSE_VALUES_BYTE_OFFSET_HASH = 1286410249L
        private val setSparseValuesByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFAccessor", "set_sparse_values_byte_offset", SET_SPARSE_VALUES_BYTE_OFFSET_HASH)
        }
    }
}

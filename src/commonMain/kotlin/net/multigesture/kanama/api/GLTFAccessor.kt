package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
    }

    fun getBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBufferViewBind, segment)
    }

    fun setBufferView(bufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBufferViewBind, segment, bufferView)
    }

    fun getByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteOffsetBind, segment)
    }

    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteOffsetBind, segment, byteOffset)
    }

    fun getComponentType(): GLTFAccessor.GLTFComponentType {
        checkOpen()
        return GLTFAccessor.GLTFComponentType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getComponentTypeBind, segment))
    }

    fun setComponentType(componentType: GLTFAccessor.GLTFComponentType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setComponentTypeBind, segment, componentType.value)
    }

    fun getNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getNormalizedBind, segment)
    }

    fun setNormalized(normalized: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNormalizedBind, segment, normalized)
    }

    fun getCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getCountBind, segment)
    }

    fun setCount(count: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCountBind, segment, count)
    }

    fun getAccessorType(): GLTFAccessor.GLTFAccessorType {
        checkOpen()
        return GLTFAccessor.GLTFAccessorType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAccessorTypeBind, segment))
    }

    fun setAccessorType(accessorType: GLTFAccessor.GLTFAccessorType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAccessorTypeBind, segment, accessorType.value)
    }

    fun getType(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTypeBind, segment)
    }

    fun setType(type: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setTypeBind, segment, type)
    }

    fun getMin(): List<Double> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(Binds.getMinBind, segment)
    }

    fun setMin(min: List<Double>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat64ListArg(Binds.setMinBind, segment, min)
    }

    fun getMax(): List<Double> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(Binds.getMaxBind, segment)
    }

    fun setMax(max: List<Double>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat64ListArg(Binds.setMaxBind, segment, max)
    }

    fun getSparseCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseCountBind, segment)
    }

    fun setSparseCount(sparseCount: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseCountBind, segment, sparseCount)
    }

    fun getSparseIndicesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSparseIndicesBufferViewBind, segment)
    }

    fun setSparseIndicesBufferView(sparseIndicesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSparseIndicesBufferViewBind, segment, sparseIndicesBufferView)
    }

    fun getSparseIndicesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseIndicesByteOffsetBind, segment)
    }

    fun setSparseIndicesByteOffset(sparseIndicesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseIndicesByteOffsetBind, segment, sparseIndicesByteOffset)
    }

    fun getSparseIndicesComponentType(): GLTFAccessor.GLTFComponentType {
        checkOpen()
        return GLTFAccessor.GLTFComponentType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseIndicesComponentTypeBind, segment))
    }

    fun setSparseIndicesComponentType(sparseIndicesComponentType: GLTFAccessor.GLTFComponentType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseIndicesComponentTypeBind, segment, sparseIndicesComponentType.value)
    }

    fun getSparseValuesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSparseValuesBufferViewBind, segment)
    }

    fun setSparseValuesBufferView(sparseValuesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSparseValuesBufferViewBind, segment, sparseValuesBufferView)
    }

    fun getSparseValuesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getSparseValuesByteOffsetBind, segment)
    }

    fun setSparseValuesByteOffset(sparseValuesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSparseValuesByteOffsetBind, segment, sparseValuesByteOffset)
    }

    @JvmInline
    value class GLTFAccessorType(override val value: Long) : GodotEnumValue {
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
    value class GLTFComponentType(override val value: Long) : GodotEnumValue {
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

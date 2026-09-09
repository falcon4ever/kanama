package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFAccessor
 */
class GLTFAccessor(handle: MemorySegment) : Resource(handle) {
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

    var componentType: Long
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

    var accessorType: Long
        @JvmName("accessorTypeProperty")
        get() = getAccessorType()
        @JvmName("setAccessorTypeProperty")
        set(value) = setAccessorType(value)

    var type: Int
        @JvmName("typeProperty")
        get() = getType()
        @JvmName("setTypeProperty")
        set(value) = setType(value)

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

    var sparseIndicesComponentType: Long
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

    fun getBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferViewBind, handle)
    }

    fun setBufferView(bufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setBufferViewBind, handle, bufferView)
    }

    fun getByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getByteOffsetBind, handle)
    }

    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setByteOffsetBind, handle, byteOffset)
    }

    fun getComponentType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getComponentTypeBind, handle)
    }

    fun setComponentType(componentType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setComponentTypeBind, handle, componentType)
    }

    fun getNormalized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getNormalizedBind, handle)
    }

    fun setNormalized(normalized: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setNormalizedBind, handle, normalized)
    }

    fun getCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getCountBind, handle)
    }

    fun setCount(count: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCountBind, handle, count)
    }

    fun getAccessorType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getAccessorTypeBind, handle)
    }

    fun setAccessorType(accessorType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAccessorTypeBind, handle, accessorType)
    }

    fun getType(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getTypeBind, handle)
    }

    fun setType(type: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setTypeBind, handle, type)
    }

    fun getSparseCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseCountBind, handle)
    }

    fun setSparseCount(sparseCount: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseCountBind, handle, sparseCount)
    }

    fun getSparseIndicesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSparseIndicesBufferViewBind, handle)
    }

    fun setSparseIndicesBufferView(sparseIndicesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSparseIndicesBufferViewBind, handle, sparseIndicesBufferView)
    }

    fun getSparseIndicesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseIndicesByteOffsetBind, handle)
    }

    fun setSparseIndicesByteOffset(sparseIndicesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseIndicesByteOffsetBind, handle, sparseIndicesByteOffset)
    }

    fun getSparseIndicesComponentType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseIndicesComponentTypeBind, handle)
    }

    fun setSparseIndicesComponentType(sparseIndicesComponentType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseIndicesComponentTypeBind, handle, sparseIndicesComponentType)
    }

    fun getSparseValuesBufferView(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSparseValuesBufferViewBind, handle)
    }

    fun setSparseValuesBufferView(sparseValuesBufferView: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSparseValuesBufferViewBind, handle, sparseValuesBufferView)
    }

    fun getSparseValuesByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSparseValuesByteOffsetBind, handle)
    }

    fun setSparseValuesByteOffset(sparseValuesByteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSparseValuesByteOffsetBind, handle, sparseValuesByteOffset)
    }

    companion object {
        const val TYPE_SCALAR: Long = 0L
        const val TYPE_VEC2: Long = 1L
        const val TYPE_VEC3: Long = 2L
        const val TYPE_VEC4: Long = 3L
        const val TYPE_MAT2: Long = 4L
        const val TYPE_MAT3: Long = 5L
        const val TYPE_MAT4: Long = 6L
        const val COMPONENT_TYPE_NONE: Long = 0L
        const val COMPONENT_TYPE_SIGNED_BYTE: Long = 5120L
        const val COMPONENT_TYPE_UNSIGNED_BYTE: Long = 5121L
        const val COMPONENT_TYPE_SIGNED_SHORT: Long = 5122L
        const val COMPONENT_TYPE_UNSIGNED_SHORT: Long = 5123L
        const val COMPONENT_TYPE_SIGNED_INT: Long = 5124L
        const val COMPONENT_TYPE_UNSIGNED_INT: Long = 5125L
        const val COMPONENT_TYPE_SINGLE_FLOAT: Long = 5126L
        const val COMPONENT_TYPE_DOUBLE_FLOAT: Long = 5130L
        const val COMPONENT_TYPE_HALF_FLOAT: Long = 5131L
        const val COMPONENT_TYPE_SIGNED_LONG: Long = 5134L
        const val COMPONENT_TYPE_UNSIGNED_LONG: Long = 5135L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFAccessor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFAccessor? =
            if (handle.address() == 0L) null else GLTFAccessor(handle)

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

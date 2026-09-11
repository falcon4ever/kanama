package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFBufferView
 */
class GLTFBufferView(handle: MemorySegment) : Resource(handle) {
    var buffer: Int
        @JvmName("bufferProperty")
        get() = getBuffer()
        @JvmName("setBufferProperty")
        set(value) = setBuffer(value)

    var byteOffset: Long
        @JvmName("byteOffsetProperty")
        get() = getByteOffset()
        @JvmName("setByteOffsetProperty")
        set(value) = setByteOffset(value)

    var byteLength: Long
        @JvmName("byteLengthProperty")
        get() = getByteLength()
        @JvmName("setByteLengthProperty")
        set(value) = setByteLength(value)

    var byteStride: Long
        @JvmName("byteStrideProperty")
        get() = getByteStride()
        @JvmName("setByteStrideProperty")
        set(value) = setByteStride(value)

    var indices: Boolean
        @JvmName("indicesProperty")
        get() = getIndices()
        @JvmName("setIndicesProperty")
        set(value) = setIndices(value)

    var vertexAttributes: Boolean
        @JvmName("vertexAttributesProperty")
        get() = getVertexAttributes()
        @JvmName("setVertexAttributesProperty")
        set(value) = setVertexAttributes(value)

    fun loadBufferViewData(state: GLTFState?): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetByteArray(loadBufferViewDataBind, handle, state?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
    }

    fun getBuffer(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferBind, handle)
    }

    fun setBuffer(buffer: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setBufferBind, handle, buffer)
    }

    fun getByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getByteOffsetBind, handle)
    }

    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setByteOffsetBind, handle, byteOffset)
    }

    fun getByteLength(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getByteLengthBind, handle)
    }

    fun setByteLength(byteLength: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setByteLengthBind, handle, byteLength)
    }

    fun getByteStride(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getByteStrideBind, handle)
    }

    fun setByteStride(byteStride: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setByteStrideBind, handle, byteStride)
    }

    fun getIndices(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getIndicesBind, handle)
    }

    fun setIndices(indices: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setIndicesBind, handle, indices)
    }

    fun getVertexAttributes(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getVertexAttributesBind, handle)
    }

    fun setVertexAttributes(isAttributes: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setVertexAttributesBind, handle, isAttributes)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFBufferView? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFBufferView? =
            if (handle.address() == 0L) null else GLTFBufferView(handle)

        private const val LOAD_BUFFER_VIEW_DATA_HASH = 3945446907L
        private val loadBufferViewDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "load_buffer_view_data", LOAD_BUFFER_VIEW_DATA_HASH)
        }

        private const val TO_DICTIONARY_HASH = 3102165223L
        private val toDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "to_dictionary", TO_DICTIONARY_HASH)
        }

        private const val GET_BUFFER_HASH = 3905245786L
        private val getBufferBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "get_buffer", GET_BUFFER_HASH)
        }

        private const val SET_BUFFER_HASH = 1286410249L
        private val setBufferBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "set_buffer", SET_BUFFER_HASH)
        }

        private const val GET_BYTE_OFFSET_HASH = 3905245786L
        private val getByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "get_byte_offset", GET_BYTE_OFFSET_HASH)
        }

        private const val SET_BYTE_OFFSET_HASH = 1286410249L
        private val setByteOffsetBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "set_byte_offset", SET_BYTE_OFFSET_HASH)
        }

        private const val GET_BYTE_LENGTH_HASH = 3905245786L
        private val getByteLengthBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "get_byte_length", GET_BYTE_LENGTH_HASH)
        }

        private const val SET_BYTE_LENGTH_HASH = 1286410249L
        private val setByteLengthBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "set_byte_length", SET_BYTE_LENGTH_HASH)
        }

        private const val GET_BYTE_STRIDE_HASH = 3905245786L
        private val getByteStrideBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "get_byte_stride", GET_BYTE_STRIDE_HASH)
        }

        private const val SET_BYTE_STRIDE_HASH = 1286410249L
        private val setByteStrideBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "set_byte_stride", SET_BYTE_STRIDE_HASH)
        }

        private const val GET_INDICES_HASH = 36873697L
        private val getIndicesBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "get_indices", GET_INDICES_HASH)
        }

        private const val SET_INDICES_HASH = 2586408642L
        private val setIndicesBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "set_indices", SET_INDICES_HASH)
        }

        private const val GET_VERTEX_ATTRIBUTES_HASH = 36873697L
        private val getVertexAttributesBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "get_vertex_attributes", GET_VERTEX_ATTRIBUTES_HASH)
        }

        private const val SET_VERTEX_ATTRIBUTES_HASH = 2586408642L
        private val setVertexAttributesBind by lazy {
            ObjectCalls.getMethodBind("GLTFBufferView", "set_vertex_attributes", SET_VERTEX_ATTRIBUTES_HASH)
        }
    }
}

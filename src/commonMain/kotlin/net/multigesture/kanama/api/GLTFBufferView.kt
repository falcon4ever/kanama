package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GLTFBufferView
 */
class GLTFBufferView(handle: GodotHandle) : Resource(handle) {
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
        return ObjectCalls.ptrcallWithObjectArgRetByteArray(Binds.loadBufferViewDataBind, segment, state?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
    }

    fun getBuffer(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBufferBind, segment)
    }

    fun setBuffer(buffer: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBufferBind, segment, buffer)
    }

    fun getByteOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteOffsetBind, segment)
    }

    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteOffsetBind, segment, byteOffset)
    }

    fun getByteLength(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteLengthBind, segment)
    }

    fun setByteLength(byteLength: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteLengthBind, segment, byteLength)
    }

    fun getByteStride(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteStrideBind, segment)
    }

    fun setByteStride(byteStride: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteStrideBind, segment, byteStride)
    }

    fun getIndices(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getIndicesBind, segment)
    }

    fun setIndices(indices: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIndicesBind, segment, indices)
    }

    fun getVertexAttributes(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getVertexAttributesBind, segment)
    }

    fun setVertexAttributes(isAttributes: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setVertexAttributesBind, segment, isAttributes)
    }

    companion object {
        fun fromDictionary(dictionary: Map<String, Any?>): GLTFBufferView? {
            return GLTFBufferView.wrapOwned(ObjectCalls.ptrcallWithDictionaryArgRetObject(Binds.fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFBufferView? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFBufferView? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFBufferView(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFBufferView? =
            if (handle.address() == 0L) null else GLTFBufferView(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_BUFFER_VIEW_DATA_HASH = 3945446907L
        @JvmField
        val loadBufferViewDataBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "load_buffer_view_data", LOAD_BUFFER_VIEW_DATA_HASH)

        private const val FROM_DICTIONARY_HASH = 2594413512L
        @JvmField
        val fromDictionaryBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "from_dictionary", FROM_DICTIONARY_HASH)

        private const val TO_DICTIONARY_HASH = 3102165223L
        @JvmField
        val toDictionaryBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "to_dictionary", TO_DICTIONARY_HASH)

        private const val GET_BUFFER_HASH = 3905245786L
        @JvmField
        val getBufferBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "get_buffer", GET_BUFFER_HASH)

        private const val SET_BUFFER_HASH = 1286410249L
        @JvmField
        val setBufferBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "set_buffer", SET_BUFFER_HASH)

        private const val GET_BYTE_OFFSET_HASH = 3905245786L
        @JvmField
        val getByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "get_byte_offset", GET_BYTE_OFFSET_HASH)

        private const val SET_BYTE_OFFSET_HASH = 1286410249L
        @JvmField
        val setByteOffsetBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "set_byte_offset", SET_BYTE_OFFSET_HASH)

        private const val GET_BYTE_LENGTH_HASH = 3905245786L
        @JvmField
        val getByteLengthBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "get_byte_length", GET_BYTE_LENGTH_HASH)

        private const val SET_BYTE_LENGTH_HASH = 1286410249L
        @JvmField
        val setByteLengthBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "set_byte_length", SET_BYTE_LENGTH_HASH)

        private const val GET_BYTE_STRIDE_HASH = 3905245786L
        @JvmField
        val getByteStrideBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "get_byte_stride", GET_BYTE_STRIDE_HASH)

        private const val SET_BYTE_STRIDE_HASH = 1286410249L
        @JvmField
        val setByteStrideBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "set_byte_stride", SET_BYTE_STRIDE_HASH)

        private const val GET_INDICES_HASH = 36873697L
        @JvmField
        val getIndicesBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "get_indices", GET_INDICES_HASH)

        private const val SET_INDICES_HASH = 2586408642L
        @JvmField
        val setIndicesBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "set_indices", SET_INDICES_HASH)

        private const val GET_VERTEX_ATTRIBUTES_HASH = 36873697L
        @JvmField
        val getVertexAttributesBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "get_vertex_attributes", GET_VERTEX_ATTRIBUTES_HASH)

        private const val SET_VERTEX_ATTRIBUTES_HASH = 2586408642L
        @JvmField
        val setVertexAttributesBind =
            ObjectCalls.getMethodBind("GLTFBufferView", "set_vertex_attributes", SET_VERTEX_ATTRIBUTES_HASH)
    }
}

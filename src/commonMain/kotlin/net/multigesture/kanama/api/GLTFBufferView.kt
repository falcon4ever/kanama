package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a glTF buffer view.
 *
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

    /**
     * Loads the buffer view data from the buffer referenced by this buffer view in the given
     * `GLTFState`. Interleaved data with a byte stride is not yet supported by this method. The data
     * is returned as a `PackedByteArray`.
     *
     * Generated from Godot docs: GLTFBufferView.load_buffer_view_data
     */
    fun loadBufferViewData(state: GLTFState?): ByteArray {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithObjectArgRetByteArray(Binds.loadBufferViewDataBind, segment, state?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Serializes this GLTFBufferView instance into a `Dictionary`.
     *
     * Generated from Godot docs: GLTFBufferView.to_dictionary
     */
    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The index of the buffer this buffer view is referencing. If `-1`, this buffer view is not
     * referencing any buffer.
     *
     * Generated from Godot docs: GLTFBufferView.get_buffer
     */
    fun getBuffer(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getBufferBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The index of the buffer this buffer view is referencing. If `-1`, this buffer view is not
     * referencing any buffer.
     *
     * Generated from Godot docs: GLTFBufferView.set_buffer
     */
    fun setBuffer(buffer: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBufferBind, segment, buffer)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The offset, in bytes, from the start of the buffer to the start of this buffer view.
     *
     * Generated from Godot docs: GLTFBufferView.get_byte_offset
     */
    fun getByteOffset(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The offset, in bytes, from the start of the buffer to the start of this buffer view.
     *
     * Generated from Godot docs: GLTFBufferView.set_byte_offset
     */
    fun setByteOffset(byteOffset: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteOffsetBind, segment, byteOffset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The length, in bytes, of this buffer view. If `0`, this buffer view is empty.
     *
     * Generated from Godot docs: GLTFBufferView.get_byte_length
     */
    fun getByteLength(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteLengthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The length, in bytes, of this buffer view. If `0`, this buffer view is empty.
     *
     * Generated from Godot docs: GLTFBufferView.set_byte_length
     */
    fun setByteLength(byteLength: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteLengthBind, segment, byteLength)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The stride, in bytes, between interleaved data. If `-1`, this buffer view is not interleaved.
     *
     * Generated from Godot docs: GLTFBufferView.get_byte_stride
     */
    fun getByteStride(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getByteStrideBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The stride, in bytes, between interleaved data. If `-1`, this buffer view is not interleaved.
     *
     * Generated from Godot docs: GLTFBufferView.set_byte_stride
     */
    fun setByteStride(byteStride: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setByteStrideBind, segment, byteStride)
        ObjectCalls.keepReachable(this)
    }

    /**
     * `true` if the GLTFBufferView's OpenGL GPU buffer type is an `ELEMENT_ARRAY_BUFFER` used for
     * vertex indices (integer constant `34963`). `false` if the buffer type is any other value. See
     * Buffers, BufferViews, and Accessors
     * (https://github.com/KhronosGroup/glTF-Tutorials/blob/master/gltfTutorial/gltfTutorial_005_BuffersBufferViewsAccessors.md)
     * for possible values. This property is set on import and used on export.
     *
     * Generated from Godot docs: GLTFBufferView.get_indices
     */
    fun getIndices(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getIndicesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * `true` if the GLTFBufferView's OpenGL GPU buffer type is an `ELEMENT_ARRAY_BUFFER` used for
     * vertex indices (integer constant `34963`). `false` if the buffer type is any other value. See
     * Buffers, BufferViews, and Accessors
     * (https://github.com/KhronosGroup/glTF-Tutorials/blob/master/gltfTutorial/gltfTutorial_005_BuffersBufferViewsAccessors.md)
     * for possible values. This property is set on import and used on export.
     *
     * Generated from Godot docs: GLTFBufferView.set_indices
     */
    fun setIndices(indices: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIndicesBind, segment, indices)
        ObjectCalls.keepReachable(this)
    }

    /**
     * `true` if the GLTFBufferView's OpenGL GPU buffer type is an `ARRAY_BUFFER` used for vertex
     * attributes (integer constant `34962`). `false` if the buffer type is any other value. See
     * Buffers, BufferViews, and Accessors
     * (https://github.com/KhronosGroup/glTF-Tutorials/blob/master/gltfTutorial/gltfTutorial_005_BuffersBufferViewsAccessors.md)
     * for possible values. This property is set on import and used on export.
     *
     * Generated from Godot docs: GLTFBufferView.get_vertex_attributes
     */
    fun getVertexAttributes(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getVertexAttributesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * `true` if the GLTFBufferView's OpenGL GPU buffer type is an `ARRAY_BUFFER` used for vertex
     * attributes (integer constant `34962`). `false` if the buffer type is any other value. See
     * Buffers, BufferViews, and Accessors
     * (https://github.com/KhronosGroup/glTF-Tutorials/blob/master/gltfTutorial/gltfTutorial_005_BuffersBufferViewsAccessors.md)
     * for possible values. This property is set on import and used on export.
     *
     * Generated from Godot docs: GLTFBufferView.set_vertex_attributes
     */
    fun setVertexAttributes(isAttributes: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setVertexAttributesBind, segment, isAttributes)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        /**
         * Creates a new GLTFBufferView instance by parsing the given `Dictionary`.
         *
         * Generated from Godot docs: GLTFBufferView.from_dictionary
         */
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

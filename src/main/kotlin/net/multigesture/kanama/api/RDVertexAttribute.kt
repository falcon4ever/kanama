package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Vertex attribute (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDVertexAttribute
 */
class RDVertexAttribute(handle: MemorySegment) : RefCounted(handle) {
    var binding: Long
        @JvmName("bindingProperty")
        get() = getBinding()
        @JvmName("setBindingProperty")
        set(value) = setBinding(value)

    var location: Long
        @JvmName("locationProperty")
        get() = getLocation()
        @JvmName("setLocationProperty")
        set(value) = setLocation(value)

    var offset: Long
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var format: Long
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var stride: Long
        @JvmName("strideProperty")
        get() = getStride()
        @JvmName("setStrideProperty")
        set(value) = setStride(value)

    var frequency: Long
        @JvmName("frequencyProperty")
        get() = getFrequency()
        @JvmName("setFrequencyProperty")
        set(value) = setFrequency(value)

    /**
     * The index of the buffer in the vertex buffer array to bind this vertex attribute. When set to
     * `-1`, it defaults to the index of the attribute. Note: You cannot mix binding explicitly
     * assigned attributes with implicitly assigned ones (i.e. `-1`). Either all attributes must have
     * their binding set to `-1`, or all must have explicit bindings.
     *
     * Generated from Godot docs: RDVertexAttribute.set_binding
     */
    fun setBinding(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBindingBind, handle, pMember)
    }

    /**
     * The index of the buffer in the vertex buffer array to bind this vertex attribute. When set to
     * `-1`, it defaults to the index of the attribute. Note: You cannot mix binding explicitly
     * assigned attributes with implicitly assigned ones (i.e. `-1`). Either all attributes must have
     * their binding set to `-1`, or all must have explicit bindings.
     *
     * Generated from Godot docs: RDVertexAttribute.get_binding
     */
    fun getBinding(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBindingBind, handle)
    }

    /**
     * The location in the shader that this attribute is bound to.
     *
     * Generated from Godot docs: RDVertexAttribute.set_location
     */
    fun setLocation(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setLocationBind, handle, pMember)
    }

    /**
     * The location in the shader that this attribute is bound to.
     *
     * Generated from Godot docs: RDVertexAttribute.get_location
     */
    fun getLocation(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getLocationBind, handle)
    }

    /**
     * The number of bytes between the start of the vertex buffer and the first instance of this
     * attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.set_offset
     */
    fun setOffset(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setOffsetBind, handle, pMember)
    }

    /**
     * The number of bytes between the start of the vertex buffer and the first instance of this
     * attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.get_offset
     */
    fun getOffset(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getOffsetBind, handle)
    }

    /**
     * The way that this attribute's data is interpreted when sent to a shader.
     *
     * Generated from Godot docs: RDVertexAttribute.set_format
     */
    fun setFormat(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, pMember)
    }

    /**
     * The way that this attribute's data is interpreted when sent to a shader.
     *
     * Generated from Godot docs: RDVertexAttribute.get_format
     */
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    /**
     * The number of bytes between the starts of consecutive instances of this attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.set_stride
     */
    fun setStride(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setStrideBind, handle, pMember)
    }

    /**
     * The number of bytes between the starts of consecutive instances of this attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.get_stride
     */
    fun getStride(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getStrideBind, handle)
    }

    /**
     * The rate at which this attribute is pulled from its vertex buffer.
     *
     * Generated from Godot docs: RDVertexAttribute.set_frequency
     */
    fun setFrequency(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFrequencyBind, handle, pMember)
    }

    /**
     * The rate at which this attribute is pulled from its vertex buffer.
     *
     * Generated from Godot docs: RDVertexAttribute.get_frequency
     */
    fun getFrequency(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrequencyBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDVertexAttribute? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDVertexAttribute? =
            if (handle.address() == 0L) null else RDVertexAttribute(handle)

        private const val SET_BINDING_HASH = 1286410249L
        private val setBindingBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_binding", SET_BINDING_HASH)
        }

        private const val GET_BINDING_HASH = 3905245786L
        private val getBindingBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_binding", GET_BINDING_HASH)
        }

        private const val SET_LOCATION_HASH = 1286410249L
        private val setLocationBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_location", SET_LOCATION_HASH)
        }

        private const val GET_LOCATION_HASH = 3905245786L
        private val getLocationBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_location", GET_LOCATION_HASH)
        }

        private const val SET_OFFSET_HASH = 1286410249L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3905245786L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_FORMAT_HASH = 565531219L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_format", SET_FORMAT_HASH)
        }

        private const val GET_FORMAT_HASH = 2235804183L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_format", GET_FORMAT_HASH)
        }

        private const val SET_STRIDE_HASH = 1286410249L
        private val setStrideBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_stride", SET_STRIDE_HASH)
        }

        private const val GET_STRIDE_HASH = 3905245786L
        private val getStrideBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_stride", GET_STRIDE_HASH)
        }

        private const val SET_FREQUENCY_HASH = 522141836L
        private val setFrequencyBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_frequency", SET_FREQUENCY_HASH)
        }

        private const val GET_FREQUENCY_HASH = 4154106413L
        private val getFrequencyBind by lazy {
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_frequency", GET_FREQUENCY_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Vertex attribute (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDVertexAttribute
 */
class RDVertexAttribute(handle: GodotHandle) : RefCounted(handle) {
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

    var format: RenderingDevice.DataFormat
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var stride: Long
        @JvmName("strideProperty")
        get() = getStride()
        @JvmName("setStrideProperty")
        set(value) = setStride(value)

    var frequency: RenderingDevice.VertexFrequency
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
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setBindingBind, segment, pMember)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getBindingBind, segment)
    }

    /**
     * The location in the shader that this attribute is bound to.
     *
     * Generated from Godot docs: RDVertexAttribute.set_location
     */
    fun setLocation(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setLocationBind, segment, pMember)
    }

    /**
     * The location in the shader that this attribute is bound to.
     *
     * Generated from Godot docs: RDVertexAttribute.get_location
     */
    fun getLocation(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getLocationBind, segment)
    }

    /**
     * The number of bytes between the start of the vertex buffer and the first instance of this
     * attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.set_offset
     */
    fun setOffset(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setOffsetBind, segment, pMember)
    }

    /**
     * The number of bytes between the start of the vertex buffer and the first instance of this
     * attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.get_offset
     */
    fun getOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getOffsetBind, segment)
    }

    /**
     * The way that this attribute's data is interpreted when sent to a shader.
     *
     * Generated from Godot docs: RDVertexAttribute.set_format
     */
    fun setFormat(pMember: RenderingDevice.DataFormat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFormatBind, segment, pMember.value)
    }

    /**
     * The way that this attribute's data is interpreted when sent to a shader.
     *
     * Generated from Godot docs: RDVertexAttribute.get_format
     */
    fun getFormat(): RenderingDevice.DataFormat {
        checkOpen()
        return RenderingDevice.DataFormat(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
    }

    /**
     * The number of bytes between the starts of consecutive instances of this attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.set_stride
     */
    fun setStride(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setStrideBind, segment, pMember)
    }

    /**
     * The number of bytes between the starts of consecutive instances of this attribute.
     *
     * Generated from Godot docs: RDVertexAttribute.get_stride
     */
    fun getStride(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getStrideBind, segment)
    }

    /**
     * The rate at which this attribute is pulled from its vertex buffer.
     *
     * Generated from Godot docs: RDVertexAttribute.set_frequency
     */
    fun setFrequency(pMember: RenderingDevice.VertexFrequency) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFrequencyBind, segment, pMember.value)
    }

    /**
     * The rate at which this attribute is pulled from its vertex buffer.
     *
     * Generated from Godot docs: RDVertexAttribute.get_frequency
     */
    fun getFrequency(): RenderingDevice.VertexFrequency {
        checkOpen()
        return RenderingDevice.VertexFrequency(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFrequencyBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDVertexAttribute? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDVertexAttribute? =
            if (handle.address() == 0L) null else RefCounted.owned(RDVertexAttribute(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDVertexAttribute? =
            if (handle.address() == 0L) null else RDVertexAttribute(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BINDING_HASH = 1286410249L
        @JvmField
        val setBindingBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_binding", SET_BINDING_HASH)

        private const val GET_BINDING_HASH = 3905245786L
        @JvmField
        val getBindingBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_binding", GET_BINDING_HASH)

        private const val SET_LOCATION_HASH = 1286410249L
        @JvmField
        val setLocationBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_location", SET_LOCATION_HASH)

        private const val GET_LOCATION_HASH = 3905245786L
        @JvmField
        val getLocationBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_location", GET_LOCATION_HASH)

        private const val SET_OFFSET_HASH = 1286410249L
        @JvmField
        val setOffsetBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_offset", SET_OFFSET_HASH)

        private const val GET_OFFSET_HASH = 3905245786L
        @JvmField
        val getOffsetBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_offset", GET_OFFSET_HASH)

        private const val SET_FORMAT_HASH = 565531219L
        @JvmField
        val setFormatBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_format", SET_FORMAT_HASH)

        private const val GET_FORMAT_HASH = 2235804183L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_format", GET_FORMAT_HASH)

        private const val SET_STRIDE_HASH = 1286410249L
        @JvmField
        val setStrideBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_stride", SET_STRIDE_HASH)

        private const val GET_STRIDE_HASH = 3905245786L
        @JvmField
        val getStrideBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_stride", GET_STRIDE_HASH)

        private const val SET_FREQUENCY_HASH = 522141836L
        @JvmField
        val setFrequencyBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "set_frequency", SET_FREQUENCY_HASH)

        private const val GET_FREQUENCY_HASH = 4154106413L
        @JvmField
        val getFrequencyBind =
            ObjectCalls.getMethodBind("RDVertexAttribute", "get_frequency", GET_FREQUENCY_HASH)
    }
}

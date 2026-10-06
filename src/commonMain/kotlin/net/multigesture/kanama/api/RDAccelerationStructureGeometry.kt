package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Acceleration structure geometry (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDAccelerationStructureGeometry
 */
class RDAccelerationStructureGeometry(handle: GodotHandle) : RefCounted(handle) {
    var flags: RenderingDevice.AccelerationStructureGeometryFlagBits
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

    var vertexBuffer: RID
        @JvmName("vertexBufferProperty")
        get() = getVertexBuffer()
        @JvmName("setVertexBufferProperty")
        set(value) = setVertexBuffer(value)

    var vertexOffset: Long
        @JvmName("vertexOffsetProperty")
        get() = getVertexOffset()
        @JvmName("setVertexOffsetProperty")
        set(value) = setVertexOffset(value)

    var vertexStride: Long
        @JvmName("vertexStrideProperty")
        get() = getVertexStride()
        @JvmName("setVertexStrideProperty")
        set(value) = setVertexStride(value)

    var vertexCount: Long
        @JvmName("vertexCountProperty")
        get() = getVertexCount()
        @JvmName("setVertexCountProperty")
        set(value) = setVertexCount(value)

    var vertexFormat: RenderingDevice.DataFormat
        @JvmName("vertexFormatProperty")
        get() = getVertexFormat()
        @JvmName("setVertexFormatProperty")
        set(value) = setVertexFormat(value)

    var indexBuffer: RID
        @JvmName("indexBufferProperty")
        get() = getIndexBuffer()
        @JvmName("setIndexBufferProperty")
        set(value) = setIndexBuffer(value)

    var indexOffset: Long
        @JvmName("indexOffsetProperty")
        get() = getIndexOffset()
        @JvmName("setIndexOffsetProperty")
        set(value) = setIndexOffset(value)

    var indexCount: Long
        @JvmName("indexCountProperty")
        get() = getIndexCount()
        @JvmName("setIndexCountProperty")
        set(value) = setIndexCount(value)

    /**
     * Flags for the geometry.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_flags
     */
    fun setFlags(pMember: RenderingDevice.AccelerationStructureGeometryFlagBits) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFlagsBind, segment, pMember.value)
    }

    /**
     * Flags for the geometry.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_flags
     */
    fun getFlags(): RenderingDevice.AccelerationStructureGeometryFlagBits {
        checkOpen()
        return RenderingDevice.AccelerationStructureGeometryFlagBits(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFlagsBind, segment))
    }

    /**
     * Buffer containing vertices.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_vertex_buffer
     */
    fun setVertexBuffer(pMember: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setVertexBufferBind, segment, pMember)
    }

    /**
     * Buffer containing vertices.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_vertex_buffer
     */
    fun getVertexBuffer(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getVertexBufferBind, segment)
    }

    /**
     * Byte offset of the first vertex in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_vertex_offset
     */
    fun setVertexOffset(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setVertexOffsetBind, segment, pMember)
    }

    /**
     * Byte offset of the first vertex in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_vertex_offset
     */
    fun getVertexOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getVertexOffsetBind, segment)
    }

    /**
     * Number of bytes between each vertex in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_vertex_stride
     */
    fun setVertexStride(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setVertexStrideBind, segment, pMember)
    }

    /**
     * Number of bytes between each vertex in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_vertex_stride
     */
    fun getVertexStride(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getVertexStrideBind, segment)
    }

    /**
     * Number of vertices used by this geometry in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_vertex_count
     */
    fun setVertexCount(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setVertexCountBind, segment, pMember)
    }

    /**
     * Number of vertices used by this geometry in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_vertex_count
     */
    fun getVertexCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getVertexCountBind, segment)
    }

    /**
     * Format of the vertices in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_vertex_format
     */
    fun setVertexFormat(pMember: RenderingDevice.DataFormat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setVertexFormatBind, segment, pMember.value)
    }

    /**
     * Format of the vertices in `vertex_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_vertex_format
     */
    fun getVertexFormat(): RenderingDevice.DataFormat {
        checkOpen()
        return RenderingDevice.DataFormat(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVertexFormatBind, segment))
    }

    /**
     * Buffer containing vertex indices. If `null`, triangles are non-indexed.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_index_buffer
     */
    fun setIndexBuffer(pMember: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setIndexBufferBind, segment, pMember)
    }

    /**
     * Buffer containing vertex indices. If `null`, triangles are non-indexed.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_index_buffer
     */
    fun getIndexBuffer(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getIndexBufferBind, segment)
    }

    /**
     * Byte offset of the first index in `index_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_index_offset
     */
    fun setIndexOffset(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setIndexOffsetBind, segment, pMember)
    }

    /**
     * Byte offset of the first index in `index_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_index_offset
     */
    fun getIndexOffset(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getIndexOffsetBind, segment)
    }

    /**
     * Number of indices used by this geometry in `index_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.set_index_count
     */
    fun setIndexCount(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setIndexCountBind, segment, pMember)
    }

    /**
     * Number of indices used by this geometry in `index_buffer`.
     *
     * Generated from Godot docs: RDAccelerationStructureGeometry.get_index_count
     */
    fun getIndexCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getIndexCountBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDAccelerationStructureGeometry? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDAccelerationStructureGeometry? =
            if (handle.address() == 0L) null else RefCounted.owned(RDAccelerationStructureGeometry(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDAccelerationStructureGeometry? =
            if (handle.address() == 0L) null else RDAccelerationStructureGeometry(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FLAGS_HASH = 1046628555L
        @JvmField
        val setFlagsBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_flags", SET_FLAGS_HASH)

        private const val GET_FLAGS_HASH = 1694887119L
        @JvmField
        val getFlagsBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_flags", GET_FLAGS_HASH)

        private const val SET_VERTEX_BUFFER_HASH = 2722037293L
        @JvmField
        val setVertexBufferBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_buffer", SET_VERTEX_BUFFER_HASH)

        private const val GET_VERTEX_BUFFER_HASH = 2944877500L
        @JvmField
        val getVertexBufferBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_buffer", GET_VERTEX_BUFFER_HASH)

        private const val SET_VERTEX_OFFSET_HASH = 1286410249L
        @JvmField
        val setVertexOffsetBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_offset", SET_VERTEX_OFFSET_HASH)

        private const val GET_VERTEX_OFFSET_HASH = 3905245786L
        @JvmField
        val getVertexOffsetBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_offset", GET_VERTEX_OFFSET_HASH)

        private const val SET_VERTEX_STRIDE_HASH = 1286410249L
        @JvmField
        val setVertexStrideBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_stride", SET_VERTEX_STRIDE_HASH)

        private const val GET_VERTEX_STRIDE_HASH = 3905245786L
        @JvmField
        val getVertexStrideBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_stride", GET_VERTEX_STRIDE_HASH)

        private const val SET_VERTEX_COUNT_HASH = 1286410249L
        @JvmField
        val setVertexCountBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_count", SET_VERTEX_COUNT_HASH)

        private const val GET_VERTEX_COUNT_HASH = 3905245786L
        @JvmField
        val getVertexCountBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_count", GET_VERTEX_COUNT_HASH)

        private const val SET_VERTEX_FORMAT_HASH = 565531219L
        @JvmField
        val setVertexFormatBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_format", SET_VERTEX_FORMAT_HASH)

        private const val GET_VERTEX_FORMAT_HASH = 2235804183L
        @JvmField
        val getVertexFormatBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_format", GET_VERTEX_FORMAT_HASH)

        private const val SET_INDEX_BUFFER_HASH = 2722037293L
        @JvmField
        val setIndexBufferBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_index_buffer", SET_INDEX_BUFFER_HASH)

        private const val GET_INDEX_BUFFER_HASH = 2944877500L
        @JvmField
        val getIndexBufferBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_index_buffer", GET_INDEX_BUFFER_HASH)

        private const val SET_INDEX_OFFSET_HASH = 1286410249L
        @JvmField
        val setIndexOffsetBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_index_offset", SET_INDEX_OFFSET_HASH)

        private const val GET_INDEX_OFFSET_HASH = 3905245786L
        @JvmField
        val getIndexOffsetBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_index_offset", GET_INDEX_OFFSET_HASH)

        private const val SET_INDEX_COUNT_HASH = 1286410249L
        @JvmField
        val setIndexCountBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_index_count", SET_INDEX_COUNT_HASH)

        private const val GET_INDEX_COUNT_HASH = 3905245786L
        @JvmField
        val getIndexCountBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_index_count", GET_INDEX_COUNT_HASH)
    }
}

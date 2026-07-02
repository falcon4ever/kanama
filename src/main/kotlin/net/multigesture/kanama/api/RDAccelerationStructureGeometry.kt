package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Acceleration structure geometry (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDAccelerationStructureGeometry
 */
class RDAccelerationStructureGeometry(handle: MemorySegment) : RefCounted(handle) {
    var flags: Long
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

    var vertexFormat: Long
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

    fun setFlags(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, handle, pMember)
    }

    fun getFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, handle)
    }

    fun setVertexBuffer(pMember: RID) {
        ObjectCalls.ptrcallWithRIDArg(setVertexBufferBind, handle, pMember)
    }

    fun getVertexBuffer(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getVertexBufferBind, handle)
    }

    fun setVertexOffset(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setVertexOffsetBind, handle, pMember)
    }

    fun getVertexOffset(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getVertexOffsetBind, handle)
    }

    fun setVertexStride(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setVertexStrideBind, handle, pMember)
    }

    fun getVertexStride(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getVertexStrideBind, handle)
    }

    fun setVertexCount(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setVertexCountBind, handle, pMember)
    }

    fun getVertexCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getVertexCountBind, handle)
    }

    fun setVertexFormat(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setVertexFormatBind, handle, pMember)
    }

    fun getVertexFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVertexFormatBind, handle)
    }

    fun setIndexBuffer(pMember: RID) {
        ObjectCalls.ptrcallWithRIDArg(setIndexBufferBind, handle, pMember)
    }

    fun getIndexBuffer(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getIndexBufferBind, handle)
    }

    fun setIndexOffset(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setIndexOffsetBind, handle, pMember)
    }

    fun getIndexOffset(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getIndexOffsetBind, handle)
    }

    fun setIndexCount(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setIndexCountBind, handle, pMember)
    }

    fun getIndexCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getIndexCountBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDAccelerationStructureGeometry? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDAccelerationStructureGeometry? =
            if (handle.address() == 0L) null else RDAccelerationStructureGeometry(handle)

        private const val SET_FLAGS_HASH = 1046628555L
        private val setFlagsBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_flags", SET_FLAGS_HASH)
        }

        private const val GET_FLAGS_HASH = 1694887119L
        private val getFlagsBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_flags", GET_FLAGS_HASH)
        }

        private const val SET_VERTEX_BUFFER_HASH = 2722037293L
        private val setVertexBufferBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_buffer", SET_VERTEX_BUFFER_HASH)
        }

        private const val GET_VERTEX_BUFFER_HASH = 2944877500L
        private val getVertexBufferBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_buffer", GET_VERTEX_BUFFER_HASH)
        }

        private const val SET_VERTEX_OFFSET_HASH = 1286410249L
        private val setVertexOffsetBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_offset", SET_VERTEX_OFFSET_HASH)
        }

        private const val GET_VERTEX_OFFSET_HASH = 3905245786L
        private val getVertexOffsetBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_offset", GET_VERTEX_OFFSET_HASH)
        }

        private const val SET_VERTEX_STRIDE_HASH = 1286410249L
        private val setVertexStrideBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_stride", SET_VERTEX_STRIDE_HASH)
        }

        private const val GET_VERTEX_STRIDE_HASH = 3905245786L
        private val getVertexStrideBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_stride", GET_VERTEX_STRIDE_HASH)
        }

        private const val SET_VERTEX_COUNT_HASH = 1286410249L
        private val setVertexCountBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_count", SET_VERTEX_COUNT_HASH)
        }

        private const val GET_VERTEX_COUNT_HASH = 3905245786L
        private val getVertexCountBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_count", GET_VERTEX_COUNT_HASH)
        }

        private const val SET_VERTEX_FORMAT_HASH = 565531219L
        private val setVertexFormatBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_vertex_format", SET_VERTEX_FORMAT_HASH)
        }

        private const val GET_VERTEX_FORMAT_HASH = 2235804183L
        private val getVertexFormatBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_vertex_format", GET_VERTEX_FORMAT_HASH)
        }

        private const val SET_INDEX_BUFFER_HASH = 2722037293L
        private val setIndexBufferBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_index_buffer", SET_INDEX_BUFFER_HASH)
        }

        private const val GET_INDEX_BUFFER_HASH = 2944877500L
        private val getIndexBufferBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_index_buffer", GET_INDEX_BUFFER_HASH)
        }

        private const val SET_INDEX_OFFSET_HASH = 1286410249L
        private val setIndexOffsetBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_index_offset", SET_INDEX_OFFSET_HASH)
        }

        private const val GET_INDEX_OFFSET_HASH = 3905245786L
        private val getIndexOffsetBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_index_offset", GET_INDEX_OFFSET_HASH)
        }

        private const val SET_INDEX_COUNT_HASH = 1286410249L
        private val setIndexCountBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "set_index_count", SET_INDEX_COUNT_HASH)
        }

        private const val GET_INDEX_COUNT_HASH = 3905245786L
        private val getIndexCountBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureGeometry", "get_index_count", GET_INDEX_COUNT_HASH)
        }
    }
}

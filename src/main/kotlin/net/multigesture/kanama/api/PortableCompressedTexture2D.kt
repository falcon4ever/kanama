package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Provides a compressed texture for disk and/or VRAM in a way that is portable.
 *
 * Generated from Godot docs: PortableCompressedTexture2D
 */
class PortableCompressedTexture2D(handle: MemorySegment) : Texture2D(handle) {
    var sizeOverride: Vector2
        @JvmName("sizeOverrideProperty")
        get() = getSizeOverride()
        @JvmName("setSizeOverrideProperty")
        set(value) = setSizeOverride(value)

    var keepCompressedBuffer: Boolean
        @JvmName("keepCompressedBufferProperty")
        get() = isKeepingCompressedBuffer()
        @JvmName("setKeepCompressedBufferProperty")
        set(value) = setKeepCompressedBuffer(value)

    fun createFromImage(image: Image?, compressionMode: Long, normalMap: Boolean = false, lossyQuality: Double = 0.8) {
        ObjectCalls.ptrcallWithObjectLongBoolDoubleArgs(createFromImageBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, compressionMode, normalMap, lossyQuality)
    }

    fun getCompressionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCompressionModeBind, handle)
    }

    fun setSizeOverride(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSizeOverrideBind, handle, size)
    }

    fun getSizeOverride(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeOverrideBind, handle)
    }

    fun setKeepCompressedBuffer(keep: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepCompressedBufferBind, handle, keep)
    }

    fun isKeepingCompressedBuffer(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isKeepingCompressedBufferBind, handle)
    }

    fun setBasisuCompressorParams(uastcLevel: Int, rdoQualityLoss: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBasisuCompressorParamsBind, handle, uastcLevel, rdoQualityLoss)
    }

    companion object {
        fun setKeepAllCompressedBuffers(keep: Boolean) {
            ObjectCalls.ptrcallWithBoolArg(setKeepAllCompressedBuffersBind, MemorySegment.NULL, keep)
        }

        fun isKeepingAllCompressedBuffers(): Boolean {
            return ObjectCalls.ptrcallNoArgsRetBool(isKeepingAllCompressedBuffersBind, MemorySegment.NULL)
        }

        const val COMPRESSION_MODE_LOSSLESS: Long = 0L
        const val COMPRESSION_MODE_LOSSY: Long = 1L
        const val COMPRESSION_MODE_BASIS_UNIVERSAL: Long = 2L
        const val COMPRESSION_MODE_S3TC: Long = 3L
        const val COMPRESSION_MODE_ETC2: Long = 4L
        const val COMPRESSION_MODE_BPTC: Long = 5L
        const val COMPRESSION_MODE_ASTC: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PortableCompressedTexture2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PortableCompressedTexture2D? =
            if (handle.address() == 0L) null else PortableCompressedTexture2D(handle)

        private const val CREATE_FROM_IMAGE_HASH = 3679243433L
        private val createFromImageBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "create_from_image", CREATE_FROM_IMAGE_HASH)
        }

        private const val GET_COMPRESSION_MODE_HASH = 3265612739L
        private val getCompressionModeBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "get_compression_mode", GET_COMPRESSION_MODE_HASH)
        }

        private const val SET_SIZE_OVERRIDE_HASH = 743155724L
        private val setSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "set_size_override", SET_SIZE_OVERRIDE_HASH)
        }

        private const val GET_SIZE_OVERRIDE_HASH = 3341600327L
        private val getSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "get_size_override", GET_SIZE_OVERRIDE_HASH)
        }

        private const val SET_KEEP_COMPRESSED_BUFFER_HASH = 2586408642L
        private val setKeepCompressedBufferBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "set_keep_compressed_buffer", SET_KEEP_COMPRESSED_BUFFER_HASH)
        }

        private const val IS_KEEPING_COMPRESSED_BUFFER_HASH = 36873697L
        private val isKeepingCompressedBufferBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "is_keeping_compressed_buffer", IS_KEEPING_COMPRESSED_BUFFER_HASH)
        }

        private const val SET_BASISU_COMPRESSOR_PARAMS_HASH = 1602489585L
        private val setBasisuCompressorParamsBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "set_basisu_compressor_params", SET_BASISU_COMPRESSOR_PARAMS_HASH)
        }

        private const val SET_KEEP_ALL_COMPRESSED_BUFFERS_HASH = 2586408642L
        private val setKeepAllCompressedBuffersBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "set_keep_all_compressed_buffers", SET_KEEP_ALL_COMPRESSED_BUFFERS_HASH)
        }

        private const val IS_KEEPING_ALL_COMPRESSED_BUFFERS_HASH = 2240911060L
        private val isKeepingAllCompressedBuffersBind by lazy {
            ObjectCalls.getMethodBind("PortableCompressedTexture2D", "is_keeping_all_compressed_buffers", IS_KEEPING_ALL_COMPRESSED_BUFFERS_HASH)
        }
    }
}

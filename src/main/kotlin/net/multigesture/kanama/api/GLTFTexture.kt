package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GLTFTexture
 */
class GLTFTexture(handle: MemorySegment) : Resource(handle) {
    var srcImage: Int
        @JvmName("srcImageProperty")
        get() = getSrcImage()
        @JvmName("setSrcImageProperty")
        set(value) = setSrcImage(value)

    var sampler: Int
        @JvmName("samplerProperty")
        get() = getSampler()
        @JvmName("setSamplerProperty")
        set(value) = setSampler(value)

    fun getSrcImage(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSrcImageBind, handle)
    }

    fun setSrcImage(srcImage: Int) {
        ObjectCalls.ptrcallWithIntArg(setSrcImageBind, handle, srcImage)
    }

    fun getSampler(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSamplerBind, handle)
    }

    fun setSampler(sampler: Int) {
        ObjectCalls.ptrcallWithIntArg(setSamplerBind, handle, sampler)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFTexture? =
            if (handle.address() == 0L) null else GLTFTexture(handle)

        private const val GET_SRC_IMAGE_HASH = 3905245786L
        private val getSrcImageBind by lazy {
            ObjectCalls.getMethodBind("GLTFTexture", "get_src_image", GET_SRC_IMAGE_HASH)
        }

        private const val SET_SRC_IMAGE_HASH = 1286410249L
        private val setSrcImageBind by lazy {
            ObjectCalls.getMethodBind("GLTFTexture", "set_src_image", SET_SRC_IMAGE_HASH)
        }

        private const val GET_SAMPLER_HASH = 3905245786L
        private val getSamplerBind by lazy {
            ObjectCalls.getMethodBind("GLTFTexture", "get_sampler", GET_SAMPLER_HASH)
        }

        private const val SET_SAMPLER_HASH = 1286410249L
        private val setSamplerBind by lazy {
            ObjectCalls.getMethodBind("GLTFTexture", "set_sampler", SET_SAMPLER_HASH)
        }
    }
}

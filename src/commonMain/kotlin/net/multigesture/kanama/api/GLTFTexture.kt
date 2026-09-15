package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFTexture
 */
class GLTFTexture(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSrcImageBind, segment)
    }

    fun setSrcImage(srcImage: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSrcImageBind, segment, srcImage)
    }

    fun getSampler(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSamplerBind, segment)
    }

    fun setSampler(sampler: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSamplerBind, segment, sampler)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFTexture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GLTFTexture? =
            if (handle.address() == 0L) null else GLTFTexture(GodotHandle(handle))

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

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSrcImageBind, segment)
    }

    fun setSrcImage(srcImage: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSrcImageBind, segment, srcImage)
    }

    fun getSampler(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSamplerBind, segment)
    }

    fun setSampler(sampler: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSamplerBind, segment, sampler)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFTexture? =
            if (handle.address() == 0L) null else GLTFTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_SRC_IMAGE_HASH = 3905245786L
        @JvmField
        val getSrcImageBind =
            ObjectCalls.getMethodBind("GLTFTexture", "get_src_image", GET_SRC_IMAGE_HASH)

        private const val SET_SRC_IMAGE_HASH = 1286410249L
        @JvmField
        val setSrcImageBind =
            ObjectCalls.getMethodBind("GLTFTexture", "set_src_image", SET_SRC_IMAGE_HASH)

        private const val GET_SAMPLER_HASH = 3905245786L
        @JvmField
        val getSamplerBind =
            ObjectCalls.getMethodBind("GLTFTexture", "get_sampler", GET_SAMPLER_HASH)

        private const val SET_SAMPLER_HASH = 1286410249L
        @JvmField
        val setSamplerBind =
            ObjectCalls.getMethodBind("GLTFTexture", "set_sampler", SET_SAMPLER_HASH)
    }
}

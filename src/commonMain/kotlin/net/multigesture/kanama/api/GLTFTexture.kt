package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * GLTFTexture represents a texture in a glTF file.
 *
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

    /**
     * The index of the image associated with this texture, see `GLTFState.get_images`. If -1, then
     * this texture does not have an image assigned.
     *
     * Generated from Godot docs: GLTFTexture.get_src_image
     */
    fun getSrcImage(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSrcImageBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The index of the image associated with this texture, see `GLTFState.get_images`. If -1, then
     * this texture does not have an image assigned.
     *
     * Generated from Godot docs: GLTFTexture.set_src_image
     */
    fun setSrcImage(srcImage: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSrcImageBind, segment, srcImage)
        ObjectCalls.keepReachable(this)
    }

    /**
     * ID of the texture sampler to use when sampling the image. If -1, then the default texture
     * sampler is used (linear filtering, and repeat wrapping in both axes).
     *
     * Generated from Godot docs: GLTFTexture.get_sampler
     */
    fun getSampler(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSamplerBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * ID of the texture sampler to use when sampling the image. If -1, then the default texture
     * sampler is used (linear filtering, and repeat wrapping in both axes).
     *
     * Generated from Godot docs: GLTFTexture.set_sampler
     */
    fun setSampler(sampler: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSamplerBind, segment, sampler)
        ObjectCalls.keepReachable(this)
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

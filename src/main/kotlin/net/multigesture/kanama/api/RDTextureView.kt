package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Texture view (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDTextureView
 */
class RDTextureView(handle: MemorySegment) : RefCounted(handle) {
    var formatOverride: Long
        @JvmName("formatOverrideProperty")
        get() = getFormatOverride()
        @JvmName("setFormatOverrideProperty")
        set(value) = setFormatOverride(value)

    var swizzleR: Long
        @JvmName("swizzleRProperty")
        get() = getSwizzleR()
        @JvmName("setSwizzleRProperty")
        set(value) = setSwizzleR(value)

    var swizzleG: Long
        @JvmName("swizzleGProperty")
        get() = getSwizzleG()
        @JvmName("setSwizzleGProperty")
        set(value) = setSwizzleG(value)

    var swizzleB: Long
        @JvmName("swizzleBProperty")
        get() = getSwizzleB()
        @JvmName("setSwizzleBProperty")
        set(value) = setSwizzleB(value)

    var swizzleA: Long
        @JvmName("swizzleAProperty")
        get() = getSwizzleA()
        @JvmName("setSwizzleAProperty")
        set(value) = setSwizzleA(value)

    /**
     * Optional override for the data format to return sampled values in. The corresponding
     * `RDTextureFormat` must have had this added as a shareable format. The default value of
     * `RenderingDevice.DATA_FORMAT_MAX` does not override the format.
     *
     * Generated from Godot docs: RDTextureView.set_format_override
     */
    fun setFormatOverride(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatOverrideBind, handle, pMember)
    }

    /**
     * Optional override for the data format to return sampled values in. The corresponding
     * `RDTextureFormat` must have had this added as a shareable format. The default value of
     * `RenderingDevice.DATA_FORMAT_MAX` does not override the format.
     *
     * Generated from Godot docs: RDTextureView.get_format_override
     */
    fun getFormatOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatOverrideBind, handle)
    }

    /**
     * The channel to sample when sampling the red color channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_r
     */
    fun setSwizzleR(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSwizzleRBind, handle, pMember)
    }

    /**
     * The channel to sample when sampling the red color channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_r
     */
    fun getSwizzleR(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSwizzleRBind, handle)
    }

    /**
     * The channel to sample when sampling the green color channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_g
     */
    fun setSwizzleG(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSwizzleGBind, handle, pMember)
    }

    /**
     * The channel to sample when sampling the green color channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_g
     */
    fun getSwizzleG(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSwizzleGBind, handle)
    }

    /**
     * The channel to sample when sampling the blue color channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_b
     */
    fun setSwizzleB(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSwizzleBBind, handle, pMember)
    }

    /**
     * The channel to sample when sampling the blue color channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_b
     */
    fun getSwizzleB(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSwizzleBBind, handle)
    }

    /**
     * The channel to sample when sampling the alpha channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_a
     */
    fun setSwizzleA(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSwizzleABind, handle, pMember)
    }

    /**
     * The channel to sample when sampling the alpha channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_a
     */
    fun getSwizzleA(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSwizzleABind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDTextureView? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDTextureView? =
            if (handle.address() == 0L) null else RDTextureView(handle)

        private const val SET_FORMAT_OVERRIDE_HASH = 565531219L
        private val setFormatOverrideBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "set_format_override", SET_FORMAT_OVERRIDE_HASH)
        }

        private const val GET_FORMAT_OVERRIDE_HASH = 2235804183L
        private val getFormatOverrideBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "get_format_override", GET_FORMAT_OVERRIDE_HASH)
        }

        private const val SET_SWIZZLE_R_HASH = 3833362581L
        private val setSwizzleRBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "set_swizzle_r", SET_SWIZZLE_R_HASH)
        }

        private const val GET_SWIZZLE_R_HASH = 4150792614L
        private val getSwizzleRBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "get_swizzle_r", GET_SWIZZLE_R_HASH)
        }

        private const val SET_SWIZZLE_G_HASH = 3833362581L
        private val setSwizzleGBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "set_swizzle_g", SET_SWIZZLE_G_HASH)
        }

        private const val GET_SWIZZLE_G_HASH = 4150792614L
        private val getSwizzleGBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "get_swizzle_g", GET_SWIZZLE_G_HASH)
        }

        private const val SET_SWIZZLE_B_HASH = 3833362581L
        private val setSwizzleBBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "set_swizzle_b", SET_SWIZZLE_B_HASH)
        }

        private const val GET_SWIZZLE_B_HASH = 4150792614L
        private val getSwizzleBBind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "get_swizzle_b", GET_SWIZZLE_B_HASH)
        }

        private const val SET_SWIZZLE_A_HASH = 3833362581L
        private val setSwizzleABind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "set_swizzle_a", SET_SWIZZLE_A_HASH)
        }

        private const val GET_SWIZZLE_A_HASH = 4150792614L
        private val getSwizzleABind by lazy {
            ObjectCalls.getMethodBind("RDTextureView", "get_swizzle_a", GET_SWIZZLE_A_HASH)
        }
    }
}

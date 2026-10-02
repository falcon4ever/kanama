package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Texture view (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDTextureView
 */
class RDTextureView(handle: GodotHandle) : RefCounted(handle) {
    var formatOverride: RenderingDevice.DataFormat
        @JvmName("formatOverrideProperty")
        get() = getFormatOverride()
        @JvmName("setFormatOverrideProperty")
        set(value) = setFormatOverride(value)

    var swizzleR: RenderingDevice.TextureSwizzle
        @JvmName("swizzleRProperty")
        get() = getSwizzleR()
        @JvmName("setSwizzleRProperty")
        set(value) = setSwizzleR(value)

    var swizzleG: RenderingDevice.TextureSwizzle
        @JvmName("swizzleGProperty")
        get() = getSwizzleG()
        @JvmName("setSwizzleGProperty")
        set(value) = setSwizzleG(value)

    var swizzleB: RenderingDevice.TextureSwizzle
        @JvmName("swizzleBProperty")
        get() = getSwizzleB()
        @JvmName("setSwizzleBProperty")
        set(value) = setSwizzleB(value)

    var swizzleA: RenderingDevice.TextureSwizzle
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
    fun setFormatOverride(pMember: RenderingDevice.DataFormat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFormatOverrideBind, segment, pMember.value)
    }

    /**
     * Optional override for the data format to return sampled values in. The corresponding
     * `RDTextureFormat` must have had this added as a shareable format. The default value of
     * `RenderingDevice.DATA_FORMAT_MAX` does not override the format.
     *
     * Generated from Godot docs: RDTextureView.get_format_override
     */
    fun getFormatOverride(): RenderingDevice.DataFormat {
        checkOpen()
        return RenderingDevice.DataFormat(ObjectCalls.ptrcallNoArgsRetLong(getFormatOverrideBind, segment))
    }

    /**
     * The channel to sample when sampling the red color channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_r
     */
    fun setSwizzleR(pMember: RenderingDevice.TextureSwizzle) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSwizzleRBind, segment, pMember.value)
    }

    /**
     * The channel to sample when sampling the red color channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_r
     */
    fun getSwizzleR(): RenderingDevice.TextureSwizzle {
        checkOpen()
        return RenderingDevice.TextureSwizzle(ObjectCalls.ptrcallNoArgsRetLong(getSwizzleRBind, segment))
    }

    /**
     * The channel to sample when sampling the green color channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_g
     */
    fun setSwizzleG(pMember: RenderingDevice.TextureSwizzle) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSwizzleGBind, segment, pMember.value)
    }

    /**
     * The channel to sample when sampling the green color channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_g
     */
    fun getSwizzleG(): RenderingDevice.TextureSwizzle {
        checkOpen()
        return RenderingDevice.TextureSwizzle(ObjectCalls.ptrcallNoArgsRetLong(getSwizzleGBind, segment))
    }

    /**
     * The channel to sample when sampling the blue color channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_b
     */
    fun setSwizzleB(pMember: RenderingDevice.TextureSwizzle) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSwizzleBBind, segment, pMember.value)
    }

    /**
     * The channel to sample when sampling the blue color channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_b
     */
    fun getSwizzleB(): RenderingDevice.TextureSwizzle {
        checkOpen()
        return RenderingDevice.TextureSwizzle(ObjectCalls.ptrcallNoArgsRetLong(getSwizzleBBind, segment))
    }

    /**
     * The channel to sample when sampling the alpha channel.
     *
     * Generated from Godot docs: RDTextureView.set_swizzle_a
     */
    fun setSwizzleA(pMember: RenderingDevice.TextureSwizzle) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSwizzleABind, segment, pMember.value)
    }

    /**
     * The channel to sample when sampling the alpha channel.
     *
     * Generated from Godot docs: RDTextureView.get_swizzle_a
     */
    fun getSwizzleA(): RenderingDevice.TextureSwizzle {
        checkOpen()
        return RenderingDevice.TextureSwizzle(ObjectCalls.ptrcallNoArgsRetLong(getSwizzleABind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDTextureView? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RDTextureView? =
            if (handle.address() == 0L) null else RDTextureView(GodotHandle(handle))

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

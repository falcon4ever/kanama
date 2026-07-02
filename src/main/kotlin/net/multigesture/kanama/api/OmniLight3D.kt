package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Omnidirectional light, such as a light bulb or a candle.
 *
 * Generated from Godot docs: OmniLight3D
 */
class OmniLight3D(handle: MemorySegment) : Light3D(handle) {
    var omniShadowMode: Long
        @JvmName("omniShadowModeProperty")
        get() = getShadowMode()
        @JvmName("setOmniShadowModeProperty")
        set(value) = setShadowMode(value)

    fun setShadowMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadowModeBind, handle, mode)
    }

    fun getShadowMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadowModeBind, handle)
    }

    companion object {
        const val SHADOW_DUAL_PARABOLOID: Long = 0L
        const val SHADOW_CUBE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OmniLight3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OmniLight3D? =
            if (handle.address() == 0L) null else OmniLight3D(handle)

        private const val SET_SHADOW_MODE_HASH = 121862228L
        private val setShadowModeBind by lazy {
            ObjectCalls.getMethodBind("OmniLight3D", "set_shadow_mode", SET_SHADOW_MODE_HASH)
        }

        private const val GET_SHADOW_MODE_HASH = 4181586331L
        private val getShadowModeBind by lazy {
            ObjectCalls.getMethodBind("OmniLight3D", "get_shadow_mode", GET_SHADOW_MODE_HASH)
        }
    }
}

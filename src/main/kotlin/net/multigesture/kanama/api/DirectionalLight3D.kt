package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Directional light from a distance, as from the Sun.
 *
 * Generated from Godot docs: DirectionalLight3D
 */
class DirectionalLight3D(handle: MemorySegment) : Light3D(handle) {
    var directionalShadowMode: Long
        @JvmName("directionalShadowModeProperty")
        get() = getShadowMode()
        @JvmName("setDirectionalShadowModeProperty")
        set(value) = setShadowMode(value)

    var directionalShadowBlendSplits: Boolean
        @JvmName("directionalShadowBlendSplitsProperty")
        get() = isBlendSplitsEnabled()
        @JvmName("setDirectionalShadowBlendSplitsProperty")
        set(value) = setBlendSplits(value)

    var skyMode: Long
        @JvmName("skyModeProperty")
        get() = getSkyMode()
        @JvmName("setSkyModeProperty")
        set(value) = setSkyMode(value)

    fun setShadowMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadowModeBind, handle, mode)
    }

    fun getShadowMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadowModeBind, handle)
    }

    fun setBlendSplits(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBlendSplitsBind, handle, enabled)
    }

    fun isBlendSplitsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBlendSplitsEnabledBind, handle)
    }

    fun setSkyMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSkyModeBind, handle, mode)
    }

    fun getSkyMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSkyModeBind, handle)
    }

    companion object {
        const val SHADOW_ORTHOGONAL: Long = 0L
        const val SHADOW_PARALLEL_2_SPLITS: Long = 1L
        const val SHADOW_PARALLEL_4_SPLITS: Long = 2L
        const val SKY_MODE_LIGHT_AND_SKY: Long = 0L
        const val SKY_MODE_LIGHT_ONLY: Long = 1L
        const val SKY_MODE_SKY_ONLY: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): DirectionalLight3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DirectionalLight3D? =
            if (handle.address() == 0L) null else DirectionalLight3D(handle)

        private const val SET_SHADOW_MODE_HASH = 1261211726L
        private val setShadowModeBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight3D", "set_shadow_mode", SET_SHADOW_MODE_HASH)
        }

        private const val GET_SHADOW_MODE_HASH = 2765228544L
        private val getShadowModeBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight3D", "get_shadow_mode", GET_SHADOW_MODE_HASH)
        }

        private const val SET_BLEND_SPLITS_HASH = 2586408642L
        private val setBlendSplitsBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight3D", "set_blend_splits", SET_BLEND_SPLITS_HASH)
        }

        private const val IS_BLEND_SPLITS_ENABLED_HASH = 36873697L
        private val isBlendSplitsEnabledBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight3D", "is_blend_splits_enabled", IS_BLEND_SPLITS_ENABLED_HASH)
        }

        private const val SET_SKY_MODE_HASH = 2691194817L
        private val setSkyModeBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight3D", "set_sky_mode", SET_SKY_MODE_HASH)
        }

        private const val GET_SKY_MODE_HASH = 3819982774L
        private val getSkyModeBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight3D", "get_sky_mode", GET_SKY_MODE_HASH)
        }
    }
}

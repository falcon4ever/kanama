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

    var directionalShadowSplit1: Double
        @JvmName("directionalShadowSplit1Property")
        get() = getParam(10L)
        @JvmName("setDirectionalShadowSplit1Property")
        set(value) = setParam(10L, value)

    var directionalShadowSplit2: Double
        @JvmName("directionalShadowSplit2Property")
        get() = getParam(11L)
        @JvmName("setDirectionalShadowSplit2Property")
        set(value) = setParam(11L, value)

    var directionalShadowSplit3: Double
        @JvmName("directionalShadowSplit3Property")
        get() = getParam(12L)
        @JvmName("setDirectionalShadowSplit3Property")
        set(value) = setParam(12L, value)

    var directionalShadowBlendSplits: Boolean
        @JvmName("directionalShadowBlendSplitsProperty")
        get() = isBlendSplitsEnabled()
        @JvmName("setDirectionalShadowBlendSplitsProperty")
        set(value) = setBlendSplits(value)

    var directionalShadowFadeStart: Double
        @JvmName("directionalShadowFadeStartProperty")
        get() = getParam(13L)
        @JvmName("setDirectionalShadowFadeStartProperty")
        set(value) = setParam(13L, value)

    var directionalShadowMaxDistance: Double
        @JvmName("directionalShadowMaxDistanceProperty")
        get() = getParam(9L)
        @JvmName("setDirectionalShadowMaxDistanceProperty")
        set(value) = setParam(9L, value)

    var directionalShadowPancakeSize: Double
        @JvmName("directionalShadowPancakeSizeProperty")
        get() = getParam(16L)
        @JvmName("setDirectionalShadowPancakeSizeProperty")
        set(value) = setParam(16L, value)

    var skyMode: Long
        @JvmName("skyModeProperty")
        get() = getSkyMode()
        @JvmName("setSkyModeProperty")
        set(value) = setSkyMode(value)

    /**
     * The light's shadow rendering algorithm.
     *
     * Generated from Godot docs: DirectionalLight3D.set_shadow_mode
     */
    fun setShadowMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadowModeBind, handle, mode)
    }

    /**
     * The light's shadow rendering algorithm.
     *
     * Generated from Godot docs: DirectionalLight3D.get_shadow_mode
     */
    fun getShadowMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadowModeBind, handle)
    }

    /**
     * If `true`, shadow detail is sacrificed in exchange for smoother transitions between splits.
     * Enabling shadow blend splitting also has a moderate performance cost. This is ignored when
     * `directional_shadow_mode` is `SHADOW_ORTHOGONAL`.
     *
     * Generated from Godot docs: DirectionalLight3D.set_blend_splits
     */
    fun setBlendSplits(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBlendSplitsBind, handle, enabled)
    }

    /**
     * If `true`, shadow detail is sacrificed in exchange for smoother transitions between splits.
     * Enabling shadow blend splitting also has a moderate performance cost. This is ignored when
     * `directional_shadow_mode` is `SHADOW_ORTHOGONAL`.
     *
     * Generated from Godot docs: DirectionalLight3D.is_blend_splits_enabled
     */
    fun isBlendSplitsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBlendSplitsEnabledBind, handle)
    }

    /**
     * Whether this `DirectionalLight3D` is visible in the sky, in the scene, or both in the sky and in
     * the scene.
     *
     * Generated from Godot docs: DirectionalLight3D.set_sky_mode
     */
    fun setSkyMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSkyModeBind, handle, mode)
    }

    /**
     * Whether this `DirectionalLight3D` is visible in the sky, in the scene, or both in the sky and in
     * the scene.
     *
     * Generated from Godot docs: DirectionalLight3D.get_sky_mode
     */
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

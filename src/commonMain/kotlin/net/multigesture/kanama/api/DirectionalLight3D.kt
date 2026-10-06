package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Directional light from a distance, as from the Sun.
 *
 * Generated from Godot docs: DirectionalLight3D
 */
class DirectionalLight3D(handle: GodotHandle) : Light3D(handle) {
    var directionalShadowMode: DirectionalLight3D.ShadowMode
        @JvmName("directionalShadowModeProperty")
        get() = getShadowMode()
        @JvmName("setDirectionalShadowModeProperty")
        set(value) = setShadowMode(value)

    var directionalShadowSplit1: Double
        @JvmName("directionalShadowSplit1Property")
        get() = getParam(Light3D.Param.SHADOW_SPLIT_1_OFFSET)
        @JvmName("setDirectionalShadowSplit1Property")
        set(value) = setParam(Light3D.Param.SHADOW_SPLIT_1_OFFSET, value)

    var directionalShadowSplit2: Double
        @JvmName("directionalShadowSplit2Property")
        get() = getParam(Light3D.Param.SHADOW_SPLIT_2_OFFSET)
        @JvmName("setDirectionalShadowSplit2Property")
        set(value) = setParam(Light3D.Param.SHADOW_SPLIT_2_OFFSET, value)

    var directionalShadowSplit3: Double
        @JvmName("directionalShadowSplit3Property")
        get() = getParam(Light3D.Param.SHADOW_SPLIT_3_OFFSET)
        @JvmName("setDirectionalShadowSplit3Property")
        set(value) = setParam(Light3D.Param.SHADOW_SPLIT_3_OFFSET, value)

    var directionalShadowBlendSplits: Boolean
        @JvmName("directionalShadowBlendSplitsProperty")
        get() = isBlendSplitsEnabled()
        @JvmName("setDirectionalShadowBlendSplitsProperty")
        set(value) = setBlendSplits(value)

    var directionalShadowFadeStart: Double
        @JvmName("directionalShadowFadeStartProperty")
        get() = getParam(Light3D.Param.SHADOW_FADE_START)
        @JvmName("setDirectionalShadowFadeStartProperty")
        set(value) = setParam(Light3D.Param.SHADOW_FADE_START, value)

    var directionalShadowMaxDistance: Double
        @JvmName("directionalShadowMaxDistanceProperty")
        get() = getParam(Light3D.Param.SHADOW_MAX_DISTANCE)
        @JvmName("setDirectionalShadowMaxDistanceProperty")
        set(value) = setParam(Light3D.Param.SHADOW_MAX_DISTANCE, value)

    var directionalShadowPancakeSize: Double
        @JvmName("directionalShadowPancakeSizeProperty")
        get() = getParam(Light3D.Param.SHADOW_PANCAKE_SIZE)
        @JvmName("setDirectionalShadowPancakeSizeProperty")
        set(value) = setParam(Light3D.Param.SHADOW_PANCAKE_SIZE, value)

    var skyMode: DirectionalLight3D.SkyMode
        @JvmName("skyModeProperty")
        get() = getSkyMode()
        @JvmName("setSkyModeProperty")
        set(value) = setSkyMode(value)

    /**
     * The light's shadow rendering algorithm.
     *
     * Generated from Godot docs: DirectionalLight3D.set_shadow_mode
     */
    fun setShadowMode(mode: DirectionalLight3D.ShadowMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setShadowModeBind, segment, mode.value)
    }

    /**
     * The light's shadow rendering algorithm.
     *
     * Generated from Godot docs: DirectionalLight3D.get_shadow_mode
     */
    fun getShadowMode(): DirectionalLight3D.ShadowMode {
        return DirectionalLight3D.ShadowMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getShadowModeBind, segment))
    }

    /**
     * If `true`, shadow detail is sacrificed in exchange for smoother transitions between splits.
     * Enabling shadow blend splitting also has a moderate performance cost. This is ignored when
     * `directional_shadow_mode` is `ShadowMode.ORTHOGONAL`.
     *
     * Generated from Godot docs: DirectionalLight3D.set_blend_splits
     */
    fun setBlendSplits(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setBlendSplitsBind, segment, enabled)
    }

    /**
     * If `true`, shadow detail is sacrificed in exchange for smoother transitions between splits.
     * Enabling shadow blend splitting also has a moderate performance cost. This is ignored when
     * `directional_shadow_mode` is `ShadowMode.ORTHOGONAL`.
     *
     * Generated from Godot docs: DirectionalLight3D.is_blend_splits_enabled
     */
    fun isBlendSplitsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBlendSplitsEnabledBind, segment)
    }

    /**
     * Whether this `DirectionalLight3D` is visible in the sky, in the scene, or both in the sky and in
     * the scene.
     *
     * Generated from Godot docs: DirectionalLight3D.set_sky_mode
     */
    fun setSkyMode(mode: DirectionalLight3D.SkyMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setSkyModeBind, segment, mode.value)
    }

    /**
     * Whether this `DirectionalLight3D` is visible in the sky, in the scene, or both in the sky and in
     * the scene.
     *
     * Generated from Godot docs: DirectionalLight3D.get_sky_mode
     */
    fun getSkyMode(): DirectionalLight3D.SkyMode {
        return DirectionalLight3D.SkyMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSkyModeBind, segment))
    }

    /**
     * Godot's `DirectionalLight3D.ShadowMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`DirectionalLight3D.ShadowMode.<NAME>`).
     *
     * Generated from Godot docs: DirectionalLight3D.ShadowMode
     */
    @JvmInline
    value class ShadowMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Renders the entire scene's shadow map from an orthogonal point of view. This is the fastest
             * directional shadow mode. May result in blurrier shadows on close objects.
             *
             * Generated from Godot docs: DirectionalLight3D.SHADOW_ORTHOGONAL
             */
            val ORTHOGONAL: ShadowMode get() = ShadowMode(0L)
            /**
             * Splits the view frustum in 2 areas, each with its own shadow map. This shadow mode is a
             * compromise between `ShadowMode.ORTHOGONAL` and `ShadowMode.PARALLEL_4_SPLITS` in terms of
             * performance.
             *
             * Generated from Godot docs: DirectionalLight3D.SHADOW_PARALLEL_2_SPLITS
             */
            val PARALLEL_2_SPLITS: ShadowMode get() = ShadowMode(1L)
            /**
             * Splits the view frustum in 4 areas, each with its own shadow map. This is the slowest
             * directional shadow mode.
             *
             * Generated from Godot docs: DirectionalLight3D.SHADOW_PARALLEL_4_SPLITS
             */
            val PARALLEL_4_SPLITS: ShadowMode get() = ShadowMode(2L)
        }
    }

    /**
     * Godot's `DirectionalLight3D.SkyMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`DirectionalLight3D.SkyMode.<NAME>`).
     *
     * Generated from Godot docs: DirectionalLight3D.SkyMode
     */
    @JvmInline
    value class SkyMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Makes the light visible in both scene lighting and sky rendering.
             *
             * Generated from Godot docs: DirectionalLight3D.SKY_MODE_LIGHT_AND_SKY
             */
            val LIGHT_AND_SKY: SkyMode get() = SkyMode(0L)
            /**
             * Makes the light visible in scene lighting only (including direct lighting and global
             * illumination). When using this mode, the light will not be visible from sky shaders.
             *
             * Generated from Godot docs: DirectionalLight3D.SKY_MODE_LIGHT_ONLY
             */
            val LIGHT_ONLY: SkyMode get() = SkyMode(1L)
            /**
             * Makes the light visible to sky shaders only. When using this mode the light will not cast light
             * into the scene (either through direct lighting or through global illumination), but can be
             * accessed through sky shaders. This can be useful, for example, when you want to control sky
             * effects without illuminating the scene (during a night cycle, for example).
             *
             * Generated from Godot docs: DirectionalLight3D.SKY_MODE_SKY_ONLY
             */
            val SKY_ONLY: SkyMode get() = SkyMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): DirectionalLight3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): DirectionalLight3D? =
            if (handle.address() == 0L) null else DirectionalLight3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SHADOW_MODE_HASH = 1261211726L
        @JvmField
        val setShadowModeBind =
            ObjectCalls.getMethodBind("DirectionalLight3D", "set_shadow_mode", SET_SHADOW_MODE_HASH)

        private const val GET_SHADOW_MODE_HASH = 2765228544L
        @JvmField
        val getShadowModeBind =
            ObjectCalls.getMethodBind("DirectionalLight3D", "get_shadow_mode", GET_SHADOW_MODE_HASH)

        private const val SET_BLEND_SPLITS_HASH = 2586408642L
        @JvmField
        val setBlendSplitsBind =
            ObjectCalls.getMethodBind("DirectionalLight3D", "set_blend_splits", SET_BLEND_SPLITS_HASH)

        private const val IS_BLEND_SPLITS_ENABLED_HASH = 36873697L
        @JvmField
        val isBlendSplitsEnabledBind =
            ObjectCalls.getMethodBind("DirectionalLight3D", "is_blend_splits_enabled", IS_BLEND_SPLITS_ENABLED_HASH)

        private const val SET_SKY_MODE_HASH = 2691194817L
        @JvmField
        val setSkyModeBind =
            ObjectCalls.getMethodBind("DirectionalLight3D", "set_sky_mode", SET_SKY_MODE_HASH)

        private const val GET_SKY_MODE_HASH = 3819982774L
        @JvmField
        val getSkyModeBind =
            ObjectCalls.getMethodBind("DirectionalLight3D", "get_sky_mode", GET_SKY_MODE_HASH)
    }
}

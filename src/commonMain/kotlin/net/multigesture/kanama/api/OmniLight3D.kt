package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Omnidirectional light, such as a light bulb or a candle.
 *
 * Generated from Godot docs: OmniLight3D
 */
class OmniLight3D(handle: GodotHandle) : Light3D(handle) {
    var omniRange: Double
        @JvmName("omniRangeProperty")
        get() = getParam(Light3D.Param.RANGE)
        @JvmName("setOmniRangeProperty")
        set(value) = setParam(Light3D.Param.RANGE, value)

    var omniAttenuation: Double
        @JvmName("omniAttenuationProperty")
        get() = getParam(Light3D.Param.ATTENUATION)
        @JvmName("setOmniAttenuationProperty")
        set(value) = setParam(Light3D.Param.ATTENUATION, value)

    var omniShadowMode: OmniLight3D.ShadowMode
        @JvmName("omniShadowModeProperty")
        get() = getShadowMode()
        @JvmName("setOmniShadowModeProperty")
        set(value) = setShadowMode(value)

    fun setShadowMode(mode: OmniLight3D.ShadowMode) {
        ObjectCalls.ptrcallWithLongArg(setShadowModeBind, segment, mode.value)
    }

    fun getShadowMode(): OmniLight3D.ShadowMode {
        return OmniLight3D.ShadowMode(ObjectCalls.ptrcallNoArgsRetLong(getShadowModeBind, segment))
    }

    @JvmInline
    value class ShadowMode(val value: Long) {
        companion object {
            /**
             * Shadows are rendered to a dual-paraboloid texture. Faster than `SHADOW_CUBE`, but lower-quality.
             *
             * Generated from Godot docs: OmniLight3D.SHADOW_DUAL_PARABOLOID
             */
            val DUAL_PARABOLOID: ShadowMode get() = ShadowMode(0L)
            /**
             * Shadows are rendered to a cubemap. Slower than `SHADOW_DUAL_PARABOLOID`, but higher-quality.
             *
             * Generated from Godot docs: OmniLight3D.SHADOW_CUBE
             */
            val CUBE: ShadowMode get() = ShadowMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OmniLight3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OmniLight3D? =
            if (handle.address() == 0L) null else OmniLight3D(GodotHandle(handle))

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

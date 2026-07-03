package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * This resource allows for creating a custom rendering effect.
 *
 * Generated from Godot docs: CompositorEffect
 */
class CompositorEffect(handle: MemorySegment) : Resource(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = getEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var effectCallbackType: Long
        @JvmName("effectCallbackTypeProperty")
        get() = getEffectCallbackType()
        @JvmName("setEffectCallbackTypeProperty")
        set(value) = setEffectCallbackType(value)

    var accessResolvedColor: Boolean
        @JvmName("accessResolvedColorProperty")
        get() = getAccessResolvedColor()
        @JvmName("setAccessResolvedColorProperty")
        set(value) = setAccessResolvedColor(value)

    var accessResolvedDepth: Boolean
        @JvmName("accessResolvedDepthProperty")
        get() = getAccessResolvedDepth()
        @JvmName("setAccessResolvedDepthProperty")
        set(value) = setAccessResolvedDepth(value)

    var needsMotionVectors: Boolean
        @JvmName("needsMotionVectorsProperty")
        get() = getNeedsMotionVectors()
        @JvmName("setNeedsMotionVectorsProperty")
        set(value) = setNeedsMotionVectors(value)

    var needsNormalRoughness: Boolean
        @JvmName("needsNormalRoughnessProperty")
        get() = getNeedsNormalRoughness()
        @JvmName("setNeedsNormalRoughnessProperty")
        set(value) = setNeedsNormalRoughness(value)

    var needsSeparateSpecular: Boolean
        @JvmName("needsSeparateSpecularProperty")
        get() = getNeedsSeparateSpecular()
        @JvmName("setNeedsSeparateSpecularProperty")
        set(value) = setNeedsSeparateSpecular(value)

    /**
     * If `true` this rendering effect is applied to any viewport it is added to.
     *
     * Generated from Godot docs: CompositorEffect.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    /**
     * If `true` this rendering effect is applied to any viewport it is added to.
     *
     * Generated from Godot docs: CompositorEffect.get_enabled
     */
    fun getEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnabledBind, handle)
    }

    /**
     * The type of effect that is implemented, determines at what stage of rendering the callback is
     * called.
     *
     * Generated from Godot docs: CompositorEffect.set_effect_callback_type
     */
    fun setEffectCallbackType(effectCallbackType: Long) {
        ObjectCalls.ptrcallWithLongArg(setEffectCallbackTypeBind, handle, effectCallbackType)
    }

    /**
     * The type of effect that is implemented, determines at what stage of rendering the callback is
     * called.
     *
     * Generated from Godot docs: CompositorEffect.get_effect_callback_type
     */
    fun getEffectCallbackType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEffectCallbackTypeBind, handle)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a color buffer resolve before the effect is
     * run. Note: In `_render_callback`, to access the resolved buffer use:
     *
     * Generated from Godot docs: CompositorEffect.set_access_resolved_color
     */
    fun setAccessResolvedColor(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAccessResolvedColorBind, handle, enable)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a color buffer resolve before the effect is
     * run. Note: In `_render_callback`, to access the resolved buffer use:
     *
     * Generated from Godot docs: CompositorEffect.get_access_resolved_color
     */
    fun getAccessResolvedColor(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAccessResolvedColorBind, handle)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a depth buffer resolve before the effect is
     * run. Note: In `_render_callback`, to access the resolved buffer use:
     *
     * Generated from Godot docs: CompositorEffect.set_access_resolved_depth
     */
    fun setAccessResolvedDepth(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAccessResolvedDepthBind, handle, enable)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a depth buffer resolve before the effect is
     * run. Note: In `_render_callback`, to access the resolved buffer use:
     *
     * Generated from Godot docs: CompositorEffect.get_access_resolved_depth
     */
    fun getAccessResolvedDepth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAccessResolvedDepthBind, handle)
    }

    /**
     * If `true` this triggers motion vectors being calculated during the opaque render state. Note: In
     * `_render_callback`, to access the motion vector buffer use:
     *
     * Generated from Godot docs: CompositorEffect.set_needs_motion_vectors
     */
    fun setNeedsMotionVectors(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNeedsMotionVectorsBind, handle, enable)
    }

    /**
     * If `true` this triggers motion vectors being calculated during the opaque render state. Note: In
     * `_render_callback`, to access the motion vector buffer use:
     *
     * Generated from Godot docs: CompositorEffect.get_needs_motion_vectors
     */
    fun getNeedsMotionVectors(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getNeedsMotionVectorsBind, handle)
    }

    /**
     * If `true` this triggers normal and roughness data to be output during our depth pre-pass, only
     * applicable for the Forward+ renderer. Note: In `_render_callback`, to access the roughness
     * buffer use:
     *
     * Generated from Godot docs: CompositorEffect.set_needs_normal_roughness
     */
    fun setNeedsNormalRoughness(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNeedsNormalRoughnessBind, handle, enable)
    }

    /**
     * If `true` this triggers normal and roughness data to be output during our depth pre-pass, only
     * applicable for the Forward+ renderer. Note: In `_render_callback`, to access the roughness
     * buffer use:
     *
     * Generated from Godot docs: CompositorEffect.get_needs_normal_roughness
     */
    fun getNeedsNormalRoughness(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getNeedsNormalRoughnessBind, handle)
    }

    /**
     * If `true` this triggers specular data being rendered to a separate buffer and combined after
     * effects have been applied, only applicable for the Forward+ renderer.
     *
     * Generated from Godot docs: CompositorEffect.set_needs_separate_specular
     */
    fun setNeedsSeparateSpecular(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNeedsSeparateSpecularBind, handle, enable)
    }

    /**
     * If `true` this triggers specular data being rendered to a separate buffer and combined after
     * effects have been applied, only applicable for the Forward+ renderer.
     *
     * Generated from Godot docs: CompositorEffect.get_needs_separate_specular
     */
    fun getNeedsSeparateSpecular(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getNeedsSeparateSpecularBind, handle)
    }

    companion object {
        const val EFFECT_CALLBACK_TYPE_PRE_OPAQUE: Long = 0L
        const val EFFECT_CALLBACK_TYPE_POST_OPAQUE: Long = 1L
        const val EFFECT_CALLBACK_TYPE_POST_SKY: Long = 2L
        const val EFFECT_CALLBACK_TYPE_PRE_TRANSPARENT: Long = 3L
        const val EFFECT_CALLBACK_TYPE_POST_TRANSPARENT: Long = 4L
        const val EFFECT_CALLBACK_TYPE_MAX: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CompositorEffect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CompositorEffect? =
            if (handle.address() == 0L) null else CompositorEffect(handle)

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_enabled", SET_ENABLED_HASH)
        }

        private const val GET_ENABLED_HASH = 36873697L
        private val getEnabledBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_enabled", GET_ENABLED_HASH)
        }

        private const val SET_EFFECT_CALLBACK_TYPE_HASH = 1390728419L
        private val setEffectCallbackTypeBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_effect_callback_type", SET_EFFECT_CALLBACK_TYPE_HASH)
        }

        private const val GET_EFFECT_CALLBACK_TYPE_HASH = 1221912590L
        private val getEffectCallbackTypeBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_effect_callback_type", GET_EFFECT_CALLBACK_TYPE_HASH)
        }

        private const val SET_ACCESS_RESOLVED_COLOR_HASH = 2586408642L
        private val setAccessResolvedColorBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_access_resolved_color", SET_ACCESS_RESOLVED_COLOR_HASH)
        }

        private const val GET_ACCESS_RESOLVED_COLOR_HASH = 36873697L
        private val getAccessResolvedColorBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_access_resolved_color", GET_ACCESS_RESOLVED_COLOR_HASH)
        }

        private const val SET_ACCESS_RESOLVED_DEPTH_HASH = 2586408642L
        private val setAccessResolvedDepthBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_access_resolved_depth", SET_ACCESS_RESOLVED_DEPTH_HASH)
        }

        private const val GET_ACCESS_RESOLVED_DEPTH_HASH = 36873697L
        private val getAccessResolvedDepthBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_access_resolved_depth", GET_ACCESS_RESOLVED_DEPTH_HASH)
        }

        private const val SET_NEEDS_MOTION_VECTORS_HASH = 2586408642L
        private val setNeedsMotionVectorsBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_needs_motion_vectors", SET_NEEDS_MOTION_VECTORS_HASH)
        }

        private const val GET_NEEDS_MOTION_VECTORS_HASH = 36873697L
        private val getNeedsMotionVectorsBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_needs_motion_vectors", GET_NEEDS_MOTION_VECTORS_HASH)
        }

        private const val SET_NEEDS_NORMAL_ROUGHNESS_HASH = 2586408642L
        private val setNeedsNormalRoughnessBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_needs_normal_roughness", SET_NEEDS_NORMAL_ROUGHNESS_HASH)
        }

        private const val GET_NEEDS_NORMAL_ROUGHNESS_HASH = 36873697L
        private val getNeedsNormalRoughnessBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_needs_normal_roughness", GET_NEEDS_NORMAL_ROUGHNESS_HASH)
        }

        private const val SET_NEEDS_SEPARATE_SPECULAR_HASH = 2586408642L
        private val setNeedsSeparateSpecularBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "set_needs_separate_specular", SET_NEEDS_SEPARATE_SPECULAR_HASH)
        }

        private const val GET_NEEDS_SEPARATE_SPECULAR_HASH = 36873697L
        private val getNeedsSeparateSpecularBind by lazy {
            ObjectCalls.getMethodBind("CompositorEffect", "get_needs_separate_specular", GET_NEEDS_SEPARATE_SPECULAR_HASH)
        }
    }
}

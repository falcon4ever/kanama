package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * This resource allows for creating a custom rendering effect.
 *
 * Generated from Godot docs: CompositorEffect
 */
class CompositorEffect(handle: GodotHandle) : Resource(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = getEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var effectCallbackType: CompositorEffect.EffectCallbackType
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * If `true` this rendering effect is applied to any viewport it is added to.
     *
     * Generated from Godot docs: CompositorEffect.get_enabled
     */
    fun getEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnabledBind, segment)
    }

    /**
     * The type of effect that is implemented, determines at what stage of rendering the callback is
     * called.
     *
     * Generated from Godot docs: CompositorEffect.set_effect_callback_type
     */
    fun setEffectCallbackType(effectCallbackType: CompositorEffect.EffectCallbackType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setEffectCallbackTypeBind, segment, effectCallbackType.value)
    }

    /**
     * The type of effect that is implemented, determines at what stage of rendering the callback is
     * called.
     *
     * Generated from Godot docs: CompositorEffect.get_effect_callback_type
     */
    fun getEffectCallbackType(): CompositorEffect.EffectCallbackType {
        checkOpen()
        return CompositorEffect.EffectCallbackType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getEffectCallbackTypeBind, segment))
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a color buffer resolve before the effect is
     * run.
     *
     * Generated from Godot docs: CompositorEffect.set_access_resolved_color
     */
    fun setAccessResolvedColor(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAccessResolvedColorBind, segment, enable)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a color buffer resolve before the effect is
     * run.
     *
     * Generated from Godot docs: CompositorEffect.get_access_resolved_color
     */
    fun getAccessResolvedColor(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAccessResolvedColorBind, segment)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a depth buffer resolve before the effect is
     * run.
     *
     * Generated from Godot docs: CompositorEffect.set_access_resolved_depth
     */
    fun setAccessResolvedDepth(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAccessResolvedDepthBind, segment, enable)
    }

    /**
     * If `true` and MSAA is enabled, this will trigger a depth buffer resolve before the effect is
     * run.
     *
     * Generated from Godot docs: CompositorEffect.get_access_resolved_depth
     */
    fun getAccessResolvedDepth(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAccessResolvedDepthBind, segment)
    }

    /**
     * If `true` this triggers motion vectors being calculated during the opaque render state.
     *
     * Generated from Godot docs: CompositorEffect.set_needs_motion_vectors
     */
    fun setNeedsMotionVectors(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNeedsMotionVectorsBind, segment, enable)
    }

    /**
     * If `true` this triggers motion vectors being calculated during the opaque render state.
     *
     * Generated from Godot docs: CompositorEffect.get_needs_motion_vectors
     */
    fun getNeedsMotionVectors(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getNeedsMotionVectorsBind, segment)
    }

    /**
     * If `true` this triggers normal and roughness data to be output during our depth pre-pass, only
     * applicable for the Forward+ renderer.
     *
     * Generated from Godot docs: CompositorEffect.set_needs_normal_roughness
     */
    fun setNeedsNormalRoughness(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNeedsNormalRoughnessBind, segment, enable)
    }

    /**
     * If `true` this triggers normal and roughness data to be output during our depth pre-pass, only
     * applicable for the Forward+ renderer.
     *
     * Generated from Godot docs: CompositorEffect.get_needs_normal_roughness
     */
    fun getNeedsNormalRoughness(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getNeedsNormalRoughnessBind, segment)
    }

    /**
     * If `true` this triggers specular data being rendered to a separate buffer and combined after
     * effects have been applied, only applicable for the Forward+ renderer.
     *
     * Generated from Godot docs: CompositorEffect.set_needs_separate_specular
     */
    fun setNeedsSeparateSpecular(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNeedsSeparateSpecularBind, segment, enable)
    }

    /**
     * If `true` this triggers specular data being rendered to a separate buffer and combined after
     * effects have been applied, only applicable for the Forward+ renderer.
     *
     * Generated from Godot docs: CompositorEffect.get_needs_separate_specular
     */
    fun getNeedsSeparateSpecular(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getNeedsSeparateSpecularBind, segment)
    }

    /**
     * Godot's `CompositorEffect.EffectCallbackType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`CompositorEffect.EffectCallbackType.<NAME>`).
     *
     * Generated from Godot docs: CompositorEffect.EffectCallbackType
     */
    @JvmInline
    value class EffectCallbackType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The callback is called before our opaque rendering pass, but after depth prepass (if
             * applicable).
             *
             * Generated from Godot docs: CompositorEffect.EFFECT_CALLBACK_TYPE_PRE_OPAQUE
             */
            val PRE_OPAQUE: EffectCallbackType get() = EffectCallbackType(0L)
            /**
             * The callback is called after our opaque rendering pass, but before our sky is rendered.
             *
             * Generated from Godot docs: CompositorEffect.EFFECT_CALLBACK_TYPE_POST_OPAQUE
             */
            val POST_OPAQUE: EffectCallbackType get() = EffectCallbackType(1L)
            /**
             * The callback is called after our sky is rendered, but before our back buffers are created (and
             * if enabled, before subsurface scattering and/or screen space reflections).
             *
             * Generated from Godot docs: CompositorEffect.EFFECT_CALLBACK_TYPE_POST_SKY
             */
            val POST_SKY: EffectCallbackType get() = EffectCallbackType(2L)
            /**
             * The callback is called before our transparent rendering pass, but after our sky is rendered and
             * we've created our back buffers.
             *
             * Generated from Godot docs: CompositorEffect.EFFECT_CALLBACK_TYPE_PRE_TRANSPARENT
             */
            val PRE_TRANSPARENT: EffectCallbackType get() = EffectCallbackType(3L)
            /**
             * The callback is called after our transparent rendering pass, but before any built-in
             * post-processing effects and output to our render target.
             *
             * Generated from Godot docs: CompositorEffect.EFFECT_CALLBACK_TYPE_POST_TRANSPARENT
             */
            val POST_TRANSPARENT: EffectCallbackType get() = EffectCallbackType(4L)
            /**
             * Represents the size of the `EffectCallbackType` enum.
             *
             * Generated from Godot docs: CompositorEffect.EFFECT_CALLBACK_TYPE_MAX
             */
            val MAX: EffectCallbackType get() = EffectCallbackType(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompositorEffect? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CompositorEffect? =
            if (handle.address() == 0L) null else RefCounted.owned(CompositorEffect(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CompositorEffect? =
            if (handle.address() == 0L) null else CompositorEffect(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_enabled", SET_ENABLED_HASH)

        private const val GET_ENABLED_HASH = 36873697L
        @JvmField
        val getEnabledBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_enabled", GET_ENABLED_HASH)

        private const val SET_EFFECT_CALLBACK_TYPE_HASH = 1390728419L
        @JvmField
        val setEffectCallbackTypeBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_effect_callback_type", SET_EFFECT_CALLBACK_TYPE_HASH)

        private const val GET_EFFECT_CALLBACK_TYPE_HASH = 1221912590L
        @JvmField
        val getEffectCallbackTypeBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_effect_callback_type", GET_EFFECT_CALLBACK_TYPE_HASH)

        private const val SET_ACCESS_RESOLVED_COLOR_HASH = 2586408642L
        @JvmField
        val setAccessResolvedColorBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_access_resolved_color", SET_ACCESS_RESOLVED_COLOR_HASH)

        private const val GET_ACCESS_RESOLVED_COLOR_HASH = 36873697L
        @JvmField
        val getAccessResolvedColorBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_access_resolved_color", GET_ACCESS_RESOLVED_COLOR_HASH)

        private const val SET_ACCESS_RESOLVED_DEPTH_HASH = 2586408642L
        @JvmField
        val setAccessResolvedDepthBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_access_resolved_depth", SET_ACCESS_RESOLVED_DEPTH_HASH)

        private const val GET_ACCESS_RESOLVED_DEPTH_HASH = 36873697L
        @JvmField
        val getAccessResolvedDepthBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_access_resolved_depth", GET_ACCESS_RESOLVED_DEPTH_HASH)

        private const val SET_NEEDS_MOTION_VECTORS_HASH = 2586408642L
        @JvmField
        val setNeedsMotionVectorsBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_needs_motion_vectors", SET_NEEDS_MOTION_VECTORS_HASH)

        private const val GET_NEEDS_MOTION_VECTORS_HASH = 36873697L
        @JvmField
        val getNeedsMotionVectorsBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_needs_motion_vectors", GET_NEEDS_MOTION_VECTORS_HASH)

        private const val SET_NEEDS_NORMAL_ROUGHNESS_HASH = 2586408642L
        @JvmField
        val setNeedsNormalRoughnessBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_needs_normal_roughness", SET_NEEDS_NORMAL_ROUGHNESS_HASH)

        private const val GET_NEEDS_NORMAL_ROUGHNESS_HASH = 36873697L
        @JvmField
        val getNeedsNormalRoughnessBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_needs_normal_roughness", GET_NEEDS_NORMAL_ROUGHNESS_HASH)

        private const val SET_NEEDS_SEPARATE_SPECULAR_HASH = 2586408642L
        @JvmField
        val setNeedsSeparateSpecularBind =
            ObjectCalls.getMethodBind("CompositorEffect", "set_needs_separate_specular", SET_NEEDS_SEPARATE_SPECULAR_HASH)

        private const val GET_NEEDS_SEPARATE_SPECULAR_HASH = 36873697L
        @JvmField
        val getNeedsSeparateSpecularBind =
            ObjectCalls.getMethodBind("CompositorEffect", "get_needs_separate_specular", GET_NEEDS_SEPARATE_SPECULAR_HASH)
    }
}

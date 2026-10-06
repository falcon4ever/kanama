package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Stores attributes used to customize how a Viewport is rendered.
 *
 * Generated from Godot docs: Compositor
 */
class Compositor(handle: GodotHandle) : Resource(handle) {
    var compositorEffects: List<CompositorEffect>
        @JvmName("compositorEffectsProperty")
        get() = getCompositorEffects()
        @JvmName("setCompositorEffectsProperty")
        set(value) = setCompositorEffects(value)

    /**
     * The custom `CompositorEffect`s that are applied during rendering of viewports using this
     * compositor.
     *
     * Generated from Godot docs: Compositor.set_compositor_effects
     */
    fun setCompositorEffects(compositorEffects: List<CompositorEffect>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setCompositorEffectsBind, segment, compositorEffects)
    }

    /**
     * The custom `CompositorEffect`s that are applied during rendering of viewports using this
     * compositor.
     *
     * Generated from Godot docs: Compositor.get_compositor_effects
     */
    fun getCompositorEffects(): List<CompositorEffect> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getCompositorEffectsBind, segment, CompositorEffect::wrapBorrowed)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Compositor? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Compositor? =
            if (handle.address() == 0L) null else RefCounted.owned(Compositor(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Compositor? =
            if (handle.address() == 0L) null else Compositor(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_COMPOSITOR_EFFECTS_HASH = 381264803L
        @JvmField
        val setCompositorEffectsBind =
            ObjectCalls.getMethodBind("Compositor", "set_compositor_effects", SET_COMPOSITOR_EFFECTS_HASH)

        private const val GET_COMPOSITOR_EFFECTS_HASH = 3995934104L
        @JvmField
        val getCompositorEffectsBind =
            ObjectCalls.getMethodBind("Compositor", "get_compositor_effects", GET_COMPOSITOR_EFFECTS_HASH)
    }
}

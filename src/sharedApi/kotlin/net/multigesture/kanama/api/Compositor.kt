package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
        ObjectCalls.ptrcallWithObjectListArg(setCompositorEffectsBind, segment, compositorEffects)
    }

    /**
     * The custom `CompositorEffect`s that are applied during rendering of viewports using this
     * compositor.
     *
     * Generated from Godot docs: Compositor.get_compositor_effects
     */
    fun getCompositorEffects(): List<CompositorEffect> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCompositorEffectsBind, segment, CompositorEffect::wrap)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Compositor? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Compositor? =
            if (handle.address() == 0L) null else Compositor(GodotHandle(handle))

        private const val SET_COMPOSITOR_EFFECTS_HASH = 381264803L
        private val setCompositorEffectsBind by lazy {
            ObjectCalls.getMethodBind("Compositor", "set_compositor_effects", SET_COMPOSITOR_EFFECTS_HASH)
        }

        private const val GET_COMPOSITOR_EFFECTS_HASH = 3995934104L
        private val getCompositorEffectsBind by lazy {
            ObjectCalls.getMethodBind("Compositor", "get_compositor_effects", GET_COMPOSITOR_EFFECTS_HASH)
        }
    }
}

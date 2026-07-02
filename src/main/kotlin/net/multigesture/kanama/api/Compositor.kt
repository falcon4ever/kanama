package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Stores attributes used to customize how a Viewport is rendered.
 *
 * Generated from Godot docs: Compositor
 */
class Compositor(handle: MemorySegment) : Resource(handle) {
    var compositorEffects: List<CompositorEffect>
        @JvmName("compositorEffectsProperty")
        get() = getCompositorEffects()
        @JvmName("setCompositorEffectsProperty")
        set(value) = setCompositorEffects(value)

    fun setCompositorEffects(compositorEffects: List<CompositorEffect>) {
        ObjectCalls.ptrcallWithObjectListArg(setCompositorEffectsBind, handle, compositorEffects)
    }

    fun getCompositorEffects(): List<CompositorEffect> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCompositorEffectsBind, handle, CompositorEffect::fromHandle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Compositor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Compositor? =
            if (handle.address() == 0L) null else Compositor(handle)

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

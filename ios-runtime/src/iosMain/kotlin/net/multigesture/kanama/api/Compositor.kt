package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Compositor
 */
class Compositor(handle: MemorySegment) : Resource(handle) {
    val compositorEffects: List<CompositorEffect>
        @JvmName("compositorEffectsProperty")
        get() = getCompositorEffects()

    fun getCompositorEffects(): List<CompositorEffect> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCompositorEffectsBind, handle, CompositorEffect::fromHandle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Compositor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Compositor? =
            if (handle.address() == 0L) null else Compositor(handle)

        private const val GET_COMPOSITOR_EFFECTS_HASH = 3995934104L
        private val getCompositorEffectsBind by lazy {
            ObjectCalls.getMethodBind("Compositor", "get_compositor_effects", GET_COMPOSITOR_EFFECTS_HASH)
        }
    }
}

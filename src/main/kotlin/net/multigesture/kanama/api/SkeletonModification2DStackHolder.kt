package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A modification that holds and executes a `SkeletonModificationStack2D`.
 *
 * Generated from Godot docs: SkeletonModification2DStackHolder
 */
class SkeletonModification2DStackHolder(handle: MemorySegment) : SkeletonModification2D(handle) {
    fun setHeldModificationStack(heldModificationStack: SkeletonModificationStack2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setHeldModificationStackBind, handle, listOf(heldModificationStack?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getHeldModificationStack(): SkeletonModificationStack2D? {
        return SkeletonModificationStack2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHeldModificationStackBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModification2DStackHolder? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DStackHolder? =
            if (handle.address() == 0L) null else SkeletonModification2DStackHolder(handle)

        private const val SET_HELD_MODIFICATION_STACK_HASH = 3907307132L
        private val setHeldModificationStackBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DStackHolder", "set_held_modification_stack", SET_HELD_MODIFICATION_STACK_HASH)
        }

        private const val GET_HELD_MODIFICATION_STACK_HASH = 2107508396L
        private val getHeldModificationStackBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DStackHolder", "get_held_modification_stack", GET_HELD_MODIFICATION_STACK_HASH)
        }
    }
}

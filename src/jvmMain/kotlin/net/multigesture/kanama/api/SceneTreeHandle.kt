package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/**
 * Non-owning SceneTree instance handle returned by Node.get_tree.
 */
class SceneTreeHandle internal constructor(handle: GodotHandle) : MainLoop(handle) {
    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneTreeHandle? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): SceneTreeHandle? =
            if (handle.address() == 0L) null else SceneTreeHandle(GodotHandle(handle))
    }
}


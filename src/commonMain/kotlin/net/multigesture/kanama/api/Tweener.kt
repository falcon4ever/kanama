package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract class for all Tweeners used by `Tween`.
 *
 * Generated from Godot docs: Tweener
 */
open class Tweener(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Tweener? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Tweener? =
            if (handle.address() == 0L) null else Tweener(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

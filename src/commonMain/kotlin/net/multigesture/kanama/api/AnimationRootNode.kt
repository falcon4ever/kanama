package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for `AnimationNode`s that hold one or multiple composite animations. Usually used for
 * `AnimationTree.tree_root`.
 *
 * Generated from Godot docs: AnimationRootNode
 */
open class AnimationRootNode(handle: GodotHandle) : AnimationNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationRootNode? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationRootNode? =
            if (handle.address() == 0L) null else AnimationRootNode(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

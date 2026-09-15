package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for creating custom profilers.
 *
 * Generated from Godot docs: EngineProfiler
 */
class EngineProfiler(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EngineProfiler? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EngineProfiler? =
            if (handle.address() == 0L) null else EngineProfiler(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

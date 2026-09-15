package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class for resource importers.
 *
 * Generated from Godot docs: ResourceImporter
 */
open class ResourceImporter(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val IMPORT_ORDER_DEFAULT: Long = 0L
        const val IMPORT_ORDER_SCENE: Long = 100L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporter? =
            if (handle.address() == 0L) null else ResourceImporter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

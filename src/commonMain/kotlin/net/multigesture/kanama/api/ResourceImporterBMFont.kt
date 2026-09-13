package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a bitmap font in the BMFont (`.fnt`) format.
 *
 * Generated from Godot docs: ResourceImporterBMFont
 */
class ResourceImporterBMFont(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterBMFont? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ResourceImporterBMFont? =
            if (handle.address() == 0L) null else ResourceImporterBMFont(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

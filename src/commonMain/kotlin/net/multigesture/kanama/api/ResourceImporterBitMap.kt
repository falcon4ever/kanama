package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a `BitMap` resource (2D array of boolean values).
 *
 * Generated from Godot docs: ResourceImporterBitMap
 */
class ResourceImporterBitMap(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterBitMap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterBitMap? =
            if (handle.address() == 0L) null else ResourceImporterBitMap(handle)

        // No MethodBinds emitted yet.
    }
}

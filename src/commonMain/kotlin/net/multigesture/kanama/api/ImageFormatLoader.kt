package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class to add support for specific image formats.
 *
 * Generated from Godot docs: ImageFormatLoader
 */
open class ImageFormatLoader(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val FLAG_NONE: Long = 0L
        const val FLAG_FORCE_LINEAR: Long = 1L
        const val FLAG_CONVERT_COLORS: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImageFormatLoader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageFormatLoader? =
            if (handle.address() == 0L) null else ImageFormatLoader(handle)

        // No MethodBinds emitted yet.
    }
}

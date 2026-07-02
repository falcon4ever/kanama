package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for script language integrations.
 *
 * Generated from Godot docs: ScriptLanguage
 */
open class ScriptLanguage(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val SCRIPT_NAME_CASING_AUTO: Long = 0L
        const val SCRIPT_NAME_CASING_PASCAL_CASE: Long = 1L
        const val SCRIPT_NAME_CASING_SNAKE_CASE: Long = 2L
        const val SCRIPT_NAME_CASING_KEBAB_CASE: Long = 3L
        const val SCRIPT_NAME_CASING_CAMEL_CASE: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScriptLanguage? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptLanguage? =
            if (handle.address() == 0L) null else ScriptLanguage(handle)

        // No MethodBinds emitted yet.
    }
}

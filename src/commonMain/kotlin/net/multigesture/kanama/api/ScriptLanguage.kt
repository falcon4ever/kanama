package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ScriptLanguage
 */
open class ScriptLanguage(handle: GodotHandle) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    @JvmInline
    value class ScriptNameCasing(val value: Long) {
        companion object {
            val AUTO: ScriptNameCasing get() = ScriptNameCasing(0L)
            val PASCAL_CASE: ScriptNameCasing get() = ScriptNameCasing(1L)
            val SNAKE_CASE: ScriptNameCasing get() = ScriptNameCasing(2L)
            val KEBAB_CASE: ScriptNameCasing get() = ScriptNameCasing(3L)
            val CAMEL_CASE: ScriptNameCasing get() = ScriptNameCasing(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScriptLanguage? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptLanguage? =
            if (handle.address() == 0L) null else ScriptLanguage(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

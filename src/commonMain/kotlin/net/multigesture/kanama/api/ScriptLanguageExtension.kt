package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ScriptLanguageExtension
 */
class ScriptLanguageExtension(handle: MemorySegment) : ScriptLanguage(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val LOOKUP_RESULT_SCRIPT_LOCATION: Long = 0L
        const val LOOKUP_RESULT_CLASS: Long = 1L
        const val LOOKUP_RESULT_CLASS_CONSTANT: Long = 2L
        const val LOOKUP_RESULT_CLASS_PROPERTY: Long = 3L
        const val LOOKUP_RESULT_CLASS_METHOD: Long = 4L
        const val LOOKUP_RESULT_CLASS_SIGNAL: Long = 5L
        const val LOOKUP_RESULT_CLASS_ENUM: Long = 6L
        const val LOOKUP_RESULT_CLASS_TBD_GLOBALSCOPE: Long = 7L
        const val LOOKUP_RESULT_CLASS_ANNOTATION: Long = 8L
        const val LOOKUP_RESULT_LOCAL_CONSTANT: Long = 9L
        const val LOOKUP_RESULT_LOCAL_VARIABLE: Long = 10L
        const val LOOKUP_RESULT_MAX: Long = 11L
        const val LOCATION_LOCAL: Long = 0L
        const val LOCATION_PARENT_MASK: Long = 256L
        const val LOCATION_OTHER_USER_CODE: Long = 512L
        const val LOCATION_OTHER: Long = 1024L
        const val CODE_COMPLETION_KIND_CLASS: Long = 0L
        const val CODE_COMPLETION_KIND_FUNCTION: Long = 1L
        const val CODE_COMPLETION_KIND_SIGNAL: Long = 2L
        const val CODE_COMPLETION_KIND_VARIABLE: Long = 3L
        const val CODE_COMPLETION_KIND_MEMBER: Long = 4L
        const val CODE_COMPLETION_KIND_ENUM: Long = 5L
        const val CODE_COMPLETION_KIND_CONSTANT: Long = 6L
        const val CODE_COMPLETION_KIND_NODE_PATH: Long = 7L
        const val CODE_COMPLETION_KIND_FILE_PATH: Long = 8L
        const val CODE_COMPLETION_KIND_PLAIN_TEXT: Long = 9L
        const val CODE_COMPLETION_KIND_KEYWORD: Long = 10L
        const val CODE_COMPLETION_KIND_MAX: Long = 11L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScriptLanguageExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptLanguageExtension? =
            if (handle.address() == 0L) null else ScriptLanguageExtension(handle)

        // No MethodBinds emitted yet.
    }
}

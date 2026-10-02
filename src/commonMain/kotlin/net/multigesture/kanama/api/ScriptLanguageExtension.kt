package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ScriptLanguageExtension
 */
class ScriptLanguageExtension(handle: GodotHandle) : ScriptLanguage(handle) {
    // No conservative instance methods emitted yet.

    /**
     * Godot's `ScriptLanguageExtension.LookupResultType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ScriptLanguageExtension.LookupResultType.<NAME>`).
     *
     * Generated from Godot docs: ScriptLanguageExtension.LookupResultType
     */
    @JvmInline
    value class LookupResultType(override val value: Long) : GodotEnumValue {
        companion object {
            val SCRIPT_LOCATION: LookupResultType get() = LookupResultType(0L)
            val CLASS: LookupResultType get() = LookupResultType(1L)
            val CLASS_CONSTANT: LookupResultType get() = LookupResultType(2L)
            val CLASS_PROPERTY: LookupResultType get() = LookupResultType(3L)
            val CLASS_METHOD: LookupResultType get() = LookupResultType(4L)
            val CLASS_SIGNAL: LookupResultType get() = LookupResultType(5L)
            val CLASS_ENUM: LookupResultType get() = LookupResultType(6L)
            val CLASS_TBD_GLOBALSCOPE: LookupResultType get() = LookupResultType(7L)
            val CLASS_ANNOTATION: LookupResultType get() = LookupResultType(8L)
            val LOCAL_CONSTANT: LookupResultType get() = LookupResultType(9L)
            val LOCAL_VARIABLE: LookupResultType get() = LookupResultType(10L)
            val MAX: LookupResultType get() = LookupResultType(11L)
        }
    }

    /**
     * Godot's `ScriptLanguageExtension.CodeCompletionLocation` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`ScriptLanguageExtension.CodeCompletionLocation.<NAME>`).
     *
     * Generated from Godot docs: ScriptLanguageExtension.CodeCompletionLocation
     */
    @JvmInline
    value class CodeCompletionLocation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The option is local to the location of the code completion query - e.g. a local variable.
             * Subsequent value of location represent options from the outer class, the exact value represent
             * how far they are (in terms of inner classes).
             *
             * Generated from Godot docs: ScriptLanguageExtension.LOCATION_LOCAL
             */
            val LOCAL: CodeCompletionLocation get() = CodeCompletionLocation(0L)
            /**
             * The option is from the containing class or a parent class, relative to the location of the code
             * completion query. Perform a bitwise OR with the class depth (e.g. `0` for the local class, `1`
             * for the parent, `2` for the grandparent, etc.) to store the depth of an option in the class or a
             * parent class.
             *
             * Generated from Godot docs: ScriptLanguageExtension.LOCATION_PARENT_MASK
             */
            val PARENT_MASK: CodeCompletionLocation get() = CodeCompletionLocation(256L)
            /**
             * The option is from user code which is not local and not in a derived class (e.g. Autoload
             * Singletons).
             *
             * Generated from Godot docs: ScriptLanguageExtension.LOCATION_OTHER_USER_CODE
             */
            val OTHER_USER_CODE: CodeCompletionLocation get() = CodeCompletionLocation(512L)
            /**
             * The option is from other engine code, not covered by the other enum constants - e.g. built-in
             * classes.
             *
             * Generated from Godot docs: ScriptLanguageExtension.LOCATION_OTHER
             */
            val OTHER: CodeCompletionLocation get() = CodeCompletionLocation(1024L)
        }
    }

    /**
     * Godot's `ScriptLanguageExtension.CodeCompletionKind` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ScriptLanguageExtension.CodeCompletionKind.<NAME>`).
     *
     * Generated from Godot docs: ScriptLanguageExtension.CodeCompletionKind
     */
    @JvmInline
    value class CodeCompletionKind(override val value: Long) : GodotEnumValue {
        companion object {
            val CLASS: CodeCompletionKind get() = CodeCompletionKind(0L)
            val FUNCTION: CodeCompletionKind get() = CodeCompletionKind(1L)
            val SIGNAL: CodeCompletionKind get() = CodeCompletionKind(2L)
            val VARIABLE: CodeCompletionKind get() = CodeCompletionKind(3L)
            val MEMBER: CodeCompletionKind get() = CodeCompletionKind(4L)
            val ENUM: CodeCompletionKind get() = CodeCompletionKind(5L)
            val CONSTANT: CodeCompletionKind get() = CodeCompletionKind(6L)
            val NODE_PATH: CodeCompletionKind get() = CodeCompletionKind(7L)
            val FILE_PATH: CodeCompletionKind get() = CodeCompletionKind(8L)
            val PLAIN_TEXT: CodeCompletionKind get() = CodeCompletionKind(9L)
            val KEYWORD: CodeCompletionKind get() = CodeCompletionKind(10L)
            val MAX: CodeCompletionKind get() = CodeCompletionKind(11L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScriptLanguageExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptLanguageExtension? =
            if (handle.address() == 0L) null else ScriptLanguageExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}

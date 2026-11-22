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

    /**
     * Godot's `ScriptLanguage.ScriptNameCasing` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`ScriptLanguage.ScriptNameCasing.<NAME>`).
     *
     * Generated from Godot docs: ScriptLanguage.ScriptNameCasing
     */
    @JvmInline
    value class ScriptNameCasing(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Used by `ProjectSettings.editor/naming/script_name_casing` to set the default script name casing
             * to the script language's preferred casing.
             *
             * Generated from Godot docs: ScriptLanguage.SCRIPT_NAME_CASING_AUTO
             */
            val AUTO: ScriptNameCasing get() = ScriptNameCasing(0L)
            /**
             * Used by `ProjectSettings.editor/naming/script_name_casing` to set the default script name casing
             * to `PascalCase`.
             *
             * Generated from Godot docs: ScriptLanguage.SCRIPT_NAME_CASING_PASCAL_CASE
             */
            val PASCAL_CASE: ScriptNameCasing get() = ScriptNameCasing(1L)
            /**
             * Used by `ProjectSettings.editor/naming/script_name_casing` to set the default script name casing
             * to `snake_case`.
             *
             * Generated from Godot docs: ScriptLanguage.SCRIPT_NAME_CASING_SNAKE_CASE
             */
            val SNAKE_CASE: ScriptNameCasing get() = ScriptNameCasing(2L)
            /**
             * Used by `ProjectSettings.editor/naming/script_name_casing` to set the default script name casing
             * to `kebab-case`.
             *
             * Generated from Godot docs: ScriptLanguage.SCRIPT_NAME_CASING_KEBAB_CASE
             */
            val KEBAB_CASE: ScriptNameCasing get() = ScriptNameCasing(3L)
            /**
             * Used by `ProjectSettings.editor/naming/script_name_casing` to set the default script name casing
             * to `camelCase`.
             *
             * Generated from Godot docs: ScriptLanguage.SCRIPT_NAME_CASING_CAMEL_CASE
             */
            val CAMEL_CASE: ScriptNameCasing get() = ScriptNameCasing(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScriptLanguage? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptLanguage? =
            if (handle.address() == 0L) null else ScriptLanguage(GodotHandle(handle))
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base editor for editing scripts in the `ScriptEditor`.
 *
 * Generated from Godot docs: ScriptEditorBase
 */
class ScriptEditorBase(handle: MemorySegment) : VBoxContainer(handle) {
    fun addSyntaxHighlighter(highlighter: EditorSyntaxHighlighter?) {
        ObjectCalls.ptrcallWithObjectArgs(addSyntaxHighlighterBind, handle, listOf(highlighter?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getBaseEditor(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBaseEditorBind, handle))
    }

    object Signals {
        const val nameChanged: String = "name_changed"
        const val editedScriptChanged: String = "edited_script_changed"
        const val searchInFilesRequested: String = "search_in_files_requested"
        const val requestSaveHistory: String = "request_save_history"
        const val requestHelp: String = "request_help"
        const val requestOpenScriptAtLine: String = "request_open_script_at_line"
        const val goToHelp: String = "go_to_help"
        const val requestSavePreviousState: String = "request_save_previous_state"
        const val replaceInFilesRequested: String = "replace_in_files_requested"
        const val goToMethod: String = "go_to_method"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScriptEditorBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptEditorBase? =
            if (handle.address() == 0L) null else ScriptEditorBase(handle)

        private const val ADD_SYNTAX_HIGHLIGHTER_HASH = 1092774468L
        private val addSyntaxHighlighterBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditorBase", "add_syntax_highlighter", ADD_SYNTAX_HIGHLIGHTER_HASH)
        }

        private const val GET_BASE_EDITOR_HASH = 2783021301L
        private val getBaseEditorBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditorBase", "get_base_editor", GET_BASE_EDITOR_HASH)
        }
    }
}

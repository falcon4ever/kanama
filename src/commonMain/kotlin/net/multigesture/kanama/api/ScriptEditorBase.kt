package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base editor for editing scripts in the `ScriptEditor`.
 *
 * Generated from Godot docs: ScriptEditorBase
 */
class ScriptEditorBase(handle: GodotHandle) : VBoxContainer(handle) {
    /**
     * Adds an `EditorSyntaxHighlighter` to the open script.
     *
     * Generated from Godot docs: ScriptEditorBase.add_syntax_highlighter
     */
    fun addSyntaxHighlighter(highlighter: EditorSyntaxHighlighter?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addSyntaxHighlighterBind, segment, listOf(highlighter?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the underlying `Control` used for editing scripts. For text scripts, this is a
     * `CodeEdit`.
     *
     * Generated from Godot docs: ScriptEditorBase.get_base_editor
     */
    fun getBaseEditor(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getBaseEditorBind, segment))
    }

    /** Signal `name_changed()`; see [TypedSignal]. */
    val nameChanged: Signal0
        @JvmName("nameChangedTypedSignal")
        get() = Signal0(this, "name_changed")

    /** Signal `edited_script_changed()`; see [TypedSignal]. */
    val editedScriptChanged: Signal0
        @JvmName("editedScriptChangedTypedSignal")
        get() = Signal0(this, "edited_script_changed")

    /** Signal `search_in_files_requested(text: String)`; see [TypedSignal]. */
    val searchInFilesRequested: Signal1<String>
        @JvmName("searchInFilesRequestedTypedSignal")
        get() = Signal1(this, "search_in_files_requested", SignalArgType.STRING)

    /** Signal `request_save_history()`; see [TypedSignal]. */
    val requestSaveHistory: Signal0
        @JvmName("requestSaveHistoryTypedSignal")
        get() = Signal0(this, "request_save_history")

    /** Signal `request_help(topic: String)`; see [TypedSignal]. */
    val requestHelp: Signal1<String>
        @JvmName("requestHelpTypedSignal")
        get() = Signal1(this, "request_help", SignalArgType.STRING)

    /** Signal `request_open_script_at_line(script: Object, line: int)`; see [TypedSignal]. */
    val requestOpenScriptAtLine: Signal2<GodotObject, Long>
        @JvmName("requestOpenScriptAtLineTypedSignal")
        get() = Signal2(this, "request_open_script_at_line", SignalArgType.objectOf("Object") { GodotObject(it) }, SignalArgType.LONG)

    /** Signal `go_to_help(what: String)`; see [TypedSignal]. */
    val goToHelp: Signal1<String>
        @JvmName("goToHelpTypedSignal")
        get() = Signal1(this, "go_to_help", SignalArgType.STRING)

    /** Signal `request_save_previous_state(state: Dictionary)`; see [TypedSignal]. On iOS a Dictionary argument is not delivered yet: a connection reports a script error. */
    val requestSavePreviousState: Signal1<Map<Any?, Any?>>
        @JvmName("requestSavePreviousStateTypedSignal")
        get() = Signal1(this, "request_save_previous_state", SignalArgType.valueOf<Map<Any?, Any?>>("Dictionary", Map::class))

    /** Signal `replace_in_files_requested(text: String)`; see [TypedSignal]. */
    val replaceInFilesRequested: Signal1<String>
        @JvmName("replaceInFilesRequestedTypedSignal")
        get() = Signal1(this, "replace_in_files_requested", SignalArgType.STRING)

    /** Signal `go_to_method(script: Object, method: String)`; see [TypedSignal]. */
    val goToMethod: Signal2<GodotObject, String>
        @JvmName("goToMethodTypedSignal")
        get() = Signal2(this, "go_to_method", SignalArgType.objectOf("Object") { GodotObject(it) }, SignalArgType.STRING)

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
        fun fromHandle(handle: GodotHandle): ScriptEditorBase? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptEditorBase? =
            if (handle.address() == 0L) null else ScriptEditorBase(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_SYNTAX_HIGHLIGHTER_HASH = 1092774468L
        @JvmField
        val addSyntaxHighlighterBind =
            ObjectCalls.getMethodBind("ScriptEditorBase", "add_syntax_highlighter", ADD_SYNTAX_HIGHLIGHTER_HASH)

        private const val GET_BASE_EDITOR_HASH = 2783021301L
        @JvmField
        val getBaseEditorBind =
            ObjectCalls.getMethodBind("ScriptEditorBase", "get_base_editor", GET_BASE_EDITOR_HASH)
    }
}

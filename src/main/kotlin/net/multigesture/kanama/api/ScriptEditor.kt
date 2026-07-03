package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's script editor.
 *
 * Generated from Godot docs: ScriptEditor
 */
class ScriptEditor(handle: MemorySegment) : PanelContainer(handle) {
    /**
     * Returns the `ScriptEditorBase` object that the user is currently editing.
     *
     * Generated from Godot docs: ScriptEditor.get_current_editor
     */
    fun getCurrentEditor(): ScriptEditorBase? {
        return ScriptEditorBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentEditorBind, handle))
    }

    /**
     * Returns an array with all `ScriptEditorBase` objects which are currently open in editor.
     *
     * Generated from Godot docs: ScriptEditor.get_open_script_editors
     */
    fun getOpenScriptEditors(): List<ScriptEditorBase> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getOpenScriptEditorsBind, handle, ScriptEditorBase::fromHandle)
    }

    /**
     * Returns array of breakpoints.
     *
     * Generated from Godot docs: ScriptEditor.get_breakpoints
     */
    fun getBreakpoints(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getBreakpointsBind, handle)
    }

    /**
     * Registers the `EditorSyntaxHighlighter` to the editor, the `EditorSyntaxHighlighter` will be
     * available on all open scripts. Note: Does not apply to scripts that are already opened.
     *
     * Generated from Godot docs: ScriptEditor.register_syntax_highlighter
     */
    fun registerSyntaxHighlighter(syntaxHighlighter: EditorSyntaxHighlighter?) {
        ObjectCalls.ptrcallWithObjectArgs(registerSyntaxHighlighterBind, handle, listOf(syntaxHighlighter?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Unregisters the `EditorSyntaxHighlighter` from the editor. Note: The `EditorSyntaxHighlighter`
     * will still be applied to scripts that are already opened.
     *
     * Generated from Godot docs: ScriptEditor.unregister_syntax_highlighter
     */
    fun unregisterSyntaxHighlighter(syntaxHighlighter: EditorSyntaxHighlighter?) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterSyntaxHighlighterBind, handle, listOf(syntaxHighlighter?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Goes to the specified line in the current script.
     *
     * Generated from Godot docs: ScriptEditor.goto_line
     */
    fun gotoLine(lineNumber: Int) {
        ObjectCalls.ptrcallWithIntArg(gotoLineBind, handle, lineNumber)
    }

    /**
     * Returns a `Script` that is currently active in editor.
     *
     * Generated from Godot docs: ScriptEditor.get_current_script
     */
    fun getCurrentScript(): Script? {
        return Script.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentScriptBind, handle))
    }

    /**
     * Returns an array with all `Script` objects which are currently open in editor.
     *
     * Generated from Godot docs: ScriptEditor.get_open_scripts
     */
    fun getOpenScripts(): List<Script> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getOpenScriptsBind, handle, Script::fromHandle)
    }

    /**
     * Opens the script create dialog. The script will extend `base_name`. The file extension can be
     * omitted from `base_path`. It will be added based on the selected scripting language.
     *
     * Generated from Godot docs: ScriptEditor.open_script_create_dialog
     */
    fun openScriptCreateDialog(baseName: String, basePath: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(openScriptCreateDialogBind, handle, baseName, basePath)
    }

    /**
     * Reloads all currently opened files. This should be used when opened files are changed outside of
     * the script editor. The user may be prompted to resolve file conflicts, see
     * `EditorSettings.text_editor/behavior/files/auto_reload_scripts_on_external_change`.
     *
     * Generated from Godot docs: ScriptEditor.reload_open_files
     */
    fun reloadOpenFiles() {
        ObjectCalls.ptrcallNoArgs(reloadOpenFilesBind, handle)
    }

    /**
     * Opens help for the given topic. The `topic` is an encoded string that controls which class,
     * method, constant, signal, annotation, property, or theme item should be focused. The supported
     * `topic` formats include `class_name:class`, `class_method:class:method`,
     * `class_constant:class:constant`, `class_signal:class:signal`,
     * `class_annotation:class:@annotation`, `class_property:class:property`, and
     * `class_theme_item:class:item`, where `class` is the class name, `method` is the method name,
     * `constant` is the constant name, `signal` is the signal name, `annotation` is the annotation
     * name, `property` is the property name, and `item` is the theme item.
     *
     * Generated from Godot docs: ScriptEditor.goto_help
     */
    fun gotoHelp(topic: String) {
        ObjectCalls.ptrcallWithStringArg(gotoHelpBind, handle, topic)
    }

    /**
     * Updates the documentation for the given `script`. Note: This should be called whenever the
     * script is changed to keep the open documentation state up to date.
     *
     * Generated from Godot docs: ScriptEditor.update_docs_from_script
     */
    fun updateDocsFromScript(script: Script?) {
        ObjectCalls.ptrcallWithObjectArgs(updateDocsFromScriptBind, handle, listOf(script?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes the documentation for the given `script`. Note: This should be called whenever the
     * script is changed to keep the open documentation state up to date.
     *
     * Generated from Godot docs: ScriptEditor.clear_docs_from_script
     */
    fun clearDocsFromScript(script: Script?) {
        ObjectCalls.ptrcallWithObjectArgs(clearDocsFromScriptBind, handle, listOf(script?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns an array of file paths of scripts with unsaved changes open in the editor.
     *
     * Generated from Godot docs: ScriptEditor.get_unsaved_files
     */
    fun getUnsavedFiles(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getUnsavedFilesBind, handle)
    }

    /**
     * Saves all open scripts.
     *
     * Generated from Godot docs: ScriptEditor.save_all_scripts
     */
    fun saveAllScripts() {
        ObjectCalls.ptrcallNoArgs(saveAllScriptsBind, handle)
    }

    /**
     * Closes the file at the given `path`, discarding any unsaved changes. Returns `OK` on success or
     * `ERR_FILE_NOT_FOUND` if the file is not found.
     *
     * Generated from Godot docs: ScriptEditor.close_file
     */
    fun closeFile(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(closeFileBind, handle, path)
    }

    object Signals {
        const val editorScriptChanged: String = "editor_script_changed"
        const val scriptClose: String = "script_close"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScriptEditor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptEditor? =
            if (handle.address() == 0L) null else ScriptEditor(handle)

        private const val GET_CURRENT_EDITOR_HASH = 1906266726L
        private val getCurrentEditorBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "get_current_editor", GET_CURRENT_EDITOR_HASH)
        }

        private const val GET_OPEN_SCRIPT_EDITORS_HASH = 3995934104L
        private val getOpenScriptEditorsBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "get_open_script_editors", GET_OPEN_SCRIPT_EDITORS_HASH)
        }

        private const val GET_BREAKPOINTS_HASH = 2981934095L
        private val getBreakpointsBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "get_breakpoints", GET_BREAKPOINTS_HASH)
        }

        private const val REGISTER_SYNTAX_HIGHLIGHTER_HASH = 1092774468L
        private val registerSyntaxHighlighterBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "register_syntax_highlighter", REGISTER_SYNTAX_HIGHLIGHTER_HASH)
        }

        private const val UNREGISTER_SYNTAX_HIGHLIGHTER_HASH = 1092774468L
        private val unregisterSyntaxHighlighterBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "unregister_syntax_highlighter", UNREGISTER_SYNTAX_HIGHLIGHTER_HASH)
        }

        private const val GOTO_LINE_HASH = 1286410249L
        private val gotoLineBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "goto_line", GOTO_LINE_HASH)
        }

        private const val GET_CURRENT_SCRIPT_HASH = 2146468882L
        private val getCurrentScriptBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "get_current_script", GET_CURRENT_SCRIPT_HASH)
        }

        private const val GET_OPEN_SCRIPTS_HASH = 3995934104L
        private val getOpenScriptsBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "get_open_scripts", GET_OPEN_SCRIPTS_HASH)
        }

        private const val OPEN_SCRIPT_CREATE_DIALOG_HASH = 3186203200L
        private val openScriptCreateDialogBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "open_script_create_dialog", OPEN_SCRIPT_CREATE_DIALOG_HASH)
        }

        private const val RELOAD_OPEN_FILES_HASH = 3218959716L
        private val reloadOpenFilesBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "reload_open_files", RELOAD_OPEN_FILES_HASH)
        }

        private const val GOTO_HELP_HASH = 83702148L
        private val gotoHelpBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "goto_help", GOTO_HELP_HASH)
        }

        private const val UPDATE_DOCS_FROM_SCRIPT_HASH = 3657522847L
        private val updateDocsFromScriptBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "update_docs_from_script", UPDATE_DOCS_FROM_SCRIPT_HASH)
        }

        private const val CLEAR_DOCS_FROM_SCRIPT_HASH = 3657522847L
        private val clearDocsFromScriptBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "clear_docs_from_script", CLEAR_DOCS_FROM_SCRIPT_HASH)
        }

        private const val GET_UNSAVED_FILES_HASH = 1139954409L
        private val getUnsavedFilesBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "get_unsaved_files", GET_UNSAVED_FILES_HASH)
        }

        private const val SAVE_ALL_SCRIPTS_HASH = 3218959716L
        private val saveAllScriptsBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "save_all_scripts", SAVE_ALL_SCRIPTS_HASH)
        }

        private const val CLOSE_FILE_HASH = 166001499L
        private val closeFileBind by lazy {
            ObjectCalls.getMethodBind("ScriptEditor", "close_file", CLOSE_FILE_HASH)
        }
    }
}

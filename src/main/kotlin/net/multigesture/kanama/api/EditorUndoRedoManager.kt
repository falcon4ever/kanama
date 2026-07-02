package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Manages undo history of scenes opened in the editor.
 *
 * Generated from Godot docs: EditorUndoRedoManager
 */
class EditorUndoRedoManager(handle: MemorySegment) : GodotObject(handle) {
    fun createAction(name: String, mergeMode: Long = 0L, customContext: GodotObject, backwardUndoOps: Boolean = false, markUnsaved: Boolean = true) {
        ObjectCalls.ptrcallWithStringLongObjectTwoBoolArgs(createActionBind, handle, name, mergeMode, customContext.handle, backwardUndoOps, markUnsaved)
    }

    fun commitAction(execute: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(commitActionBind, handle, execute)
    }

    fun isCommittingAction(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCommittingActionBind, handle)
    }

    fun forceFixedHistory() {
        ObjectCalls.ptrcallNoArgs(forceFixedHistoryBind, handle)
    }

    fun addDoMethod(objectValue: GodotObject, method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(addDoMethodBind, handle, listOf(objectValue, method, *extraArgs))
    }

    fun addUndoMethod(objectValue: GodotObject, method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(addUndoMethodBind, handle, listOf(objectValue, method, *extraArgs))
    }

    fun addDoProperty(objectValue: GodotObject, property: String, value: Any?) {
        ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addDoPropertyBind, handle, objectValue.handle, property, value)
    }

    fun addUndoProperty(objectValue: GodotObject, property: String, value: Any?) {
        ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addUndoPropertyBind, handle, objectValue.handle, property, value)
    }

    fun addDoReference(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(addDoReferenceBind, handle, listOf(objectValue.handle))
    }

    fun addUndoReference(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(addUndoReferenceBind, handle, listOf(objectValue.handle))
    }

    fun getObjectHistoryId(objectValue: GodotObject): Int {
        return ObjectCalls.ptrcallWithObjectArgRetInt(getObjectHistoryIdBind, handle, objectValue.handle)
    }

    fun getHistoryUndoRedo(id: Int): UndoRedo? {
        return UndoRedo.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getHistoryUndoRedoBind, handle, id))
    }

    fun clearHistory(id: Int = -99, increaseVersion: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(clearHistoryBind, handle, id, increaseVersion)
    }

    object Signals {
        const val historyChanged: String = "history_changed"
        const val versionChanged: String = "version_changed"
    }

    companion object {
        const val GLOBAL_HISTORY: Long = 0L
        const val REMOTE_HISTORY: Long = -9L
        const val INVALID_HISTORY: Long = -99L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorUndoRedoManager? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorUndoRedoManager? =
            if (handle.address() == 0L) null else EditorUndoRedoManager(handle)

        private const val CREATE_ACTION_HASH = 796197507L
        private val createActionBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "create_action", CREATE_ACTION_HASH)
        }

        private const val COMMIT_ACTION_HASH = 3216645846L
        private val commitActionBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "commit_action", COMMIT_ACTION_HASH)
        }

        private const val IS_COMMITTING_ACTION_HASH = 36873697L
        private val isCommittingActionBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "is_committing_action", IS_COMMITTING_ACTION_HASH)
        }

        private const val FORCE_FIXED_HISTORY_HASH = 3218959716L
        private val forceFixedHistoryBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "force_fixed_history", FORCE_FIXED_HISTORY_HASH)
        }

        private const val ADD_DO_METHOD_HASH = 1517810467L
        private val addDoMethodBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_do_method", ADD_DO_METHOD_HASH)
        }

        private const val ADD_UNDO_METHOD_HASH = 1517810467L
        private val addUndoMethodBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_undo_method", ADD_UNDO_METHOD_HASH)
        }

        private const val ADD_DO_PROPERTY_HASH = 1017172818L
        private val addDoPropertyBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_do_property", ADD_DO_PROPERTY_HASH)
        }

        private const val ADD_UNDO_PROPERTY_HASH = 1017172818L
        private val addUndoPropertyBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_undo_property", ADD_UNDO_PROPERTY_HASH)
        }

        private const val ADD_DO_REFERENCE_HASH = 3975164845L
        private val addDoReferenceBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_do_reference", ADD_DO_REFERENCE_HASH)
        }

        private const val ADD_UNDO_REFERENCE_HASH = 3975164845L
        private val addUndoReferenceBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "add_undo_reference", ADD_UNDO_REFERENCE_HASH)
        }

        private const val GET_OBJECT_HISTORY_ID_HASH = 1107568780L
        private val getObjectHistoryIdBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "get_object_history_id", GET_OBJECT_HISTORY_ID_HASH)
        }

        private const val GET_HISTORY_UNDO_REDO_HASH = 2417974513L
        private val getHistoryUndoRedoBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "get_history_undo_redo", GET_HISTORY_UNDO_REDO_HASH)
        }

        private const val CLEAR_HISTORY_HASH = 2020603371L
        private val clearHistoryBind by lazy {
            ObjectCalls.getMethodBind("EditorUndoRedoManager", "clear_history", CLEAR_HISTORY_HASH)
        }
    }
}

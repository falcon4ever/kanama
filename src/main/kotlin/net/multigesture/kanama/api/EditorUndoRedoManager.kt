package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Manages undo history of scenes opened in the editor.
 *
 * Generated from Godot docs: EditorUndoRedoManager
 */
class EditorUndoRedoManager(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Create a new action. After this is called, do all your calls to `add_do_method`,
     * `add_undo_method`, `add_do_property`, and `add_undo_property`, then commit the action with
     * `commit_action`. The way actions are merged is dictated by the `merge_mode` argument. If
     * `custom_context` object is provided, it will be used for deducing target history (instead of
     * using the first operation). The way undo operation are ordered in actions is dictated by
     * `backward_undo_ops`. When `backward_undo_ops` is `false` undo option are ordered in the same
     * order they were added. Which means the first operation to be added will be the first to be
     * undone. If `mark_unsaved` is `false`, the action will not mark the history as unsaved. This is
     * useful for example for actions that change a selection, or a setting that will be saved
     * automatically. Otherwise, this should be left to `true` if the action requires saving by the
     * user or if it can cause data loss when left unsaved.
     *
     * Generated from Godot docs: EditorUndoRedoManager.create_action
     */
    fun createAction(name: String, mergeMode: Long = 0L, customContext: GodotObject, backwardUndoOps: Boolean = false, markUnsaved: Boolean = true) {
        ObjectCalls.ptrcallWithStringLongObjectTwoBoolArgs(createActionBind, handle, name, mergeMode, customContext.handle, backwardUndoOps, markUnsaved)
    }

    /**
     * Commits the action. If `execute` is `true` (default), all "do" methods/properties are called/set
     * when this function is called.
     *
     * Generated from Godot docs: EditorUndoRedoManager.commit_action
     */
    fun commitAction(execute: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(commitActionBind, handle, execute)
    }

    /**
     * Returns `true` if the `EditorUndoRedoManager` is currently committing the action, i.e. running
     * its "do" method or property change (see `commit_action`).
     *
     * Generated from Godot docs: EditorUndoRedoManager.is_committing_action
     */
    fun isCommittingAction(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCommittingActionBind, handle)
    }

    /**
     * Forces the next operation (e.g. `add_do_method`) to use the action's history rather than
     * guessing it from the object. This is sometimes needed when a history can't be correctly
     * determined, like for a nested resource that doesn't have a path yet. This method should only be
     * used when absolutely necessary, otherwise it might cause invalid history state. For most of
     * complex cases, the `custom_context` parameter of `create_action` is sufficient.
     *
     * Generated from Godot docs: EditorUndoRedoManager.force_fixed_history
     */
    fun forceFixedHistory() {
        ObjectCalls.ptrcallNoArgs(forceFixedHistoryBind, handle)
    }

    /**
     * Register a method that will be called when the action is committed (i.e. the "do" action). If
     * this is the first operation, the `object` will be used to deduce target undo history.
     *
     * Generated from Godot docs: EditorUndoRedoManager.add_do_method
     */
    fun addDoMethod(objectValue: GodotObject, method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(addDoMethodBind, handle, listOf(objectValue, method, *extraArgs))
    }

    /**
     * Register a method that will be called when the action is undone (i.e. the "undo" action). If
     * this is the first operation, the `object` will be used to deduce target undo history.
     *
     * Generated from Godot docs: EditorUndoRedoManager.add_undo_method
     */
    fun addUndoMethod(objectValue: GodotObject, method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(addUndoMethodBind, handle, listOf(objectValue, method, *extraArgs))
    }

    /**
     * Register a property value change for "do". If this is the first operation, the `object` will be
     * used to deduce target undo history.
     *
     * Generated from Godot docs: EditorUndoRedoManager.add_do_property
     */
    fun addDoProperty(objectValue: GodotObject, property: String, value: Any?) {
        ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addDoPropertyBind, handle, objectValue.handle, property, value)
    }

    /**
     * Register a property value change for "undo". If this is the first operation, the `object` will
     * be used to deduce target undo history.
     *
     * Generated from Godot docs: EditorUndoRedoManager.add_undo_property
     */
    fun addUndoProperty(objectValue: GodotObject, property: String, value: Any?) {
        ObjectCalls.ptrcallWithObjectStringNameAndVariantArg(addUndoPropertyBind, handle, objectValue.handle, property, value)
    }

    /**
     * Register a reference for "do" that will be erased if the "do" history is lost. This is useful
     * mostly for new nodes created for the "do" call. Do not use for resources.
     *
     * Generated from Godot docs: EditorUndoRedoManager.add_do_reference
     */
    fun addDoReference(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(addDoReferenceBind, handle, listOf(objectValue.handle))
    }

    /**
     * Register a reference for "undo" that will be erased if the "undo" history is lost. This is
     * useful mostly for nodes removed with the "do" call (not the "undo" call!).
     *
     * Generated from Godot docs: EditorUndoRedoManager.add_undo_reference
     */
    fun addUndoReference(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(addUndoReferenceBind, handle, listOf(objectValue.handle))
    }

    /**
     * Returns the history ID deduced from the given `object`. It can be used with
     * `get_history_undo_redo`.
     *
     * Generated from Godot docs: EditorUndoRedoManager.get_object_history_id
     */
    fun getObjectHistoryId(objectValue: GodotObject): Int {
        return ObjectCalls.ptrcallWithObjectArgRetInt(getObjectHistoryIdBind, handle, objectValue.handle)
    }

    /**
     * Returns the `UndoRedo` object associated with the given history `id`. `id` above `0` are mapped
     * to the opened scene tabs (but it doesn't match their order). `id` of `0` or lower have special
     * meaning (see `SpecialHistory`). Best used with `get_object_history_id`. This method is only
     * provided in case you need some more advanced methods of `UndoRedo` (but keep in mind that
     * directly operating on the `UndoRedo` object might affect editor's stability).
     *
     * Generated from Godot docs: EditorUndoRedoManager.get_history_undo_redo
     */
    fun getHistoryUndoRedo(id: Int): UndoRedo? {
        return UndoRedo.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getHistoryUndoRedoBind, handle, id))
    }

    /**
     * Clears the given undo history. You can clear history for a specific scene, global history, or
     * for all histories at once (except `REMOTE_HISTORY`) if `id` is `INVALID_HISTORY`. If
     * `increase_version` is `true`, the undo history version will be increased, marking it as unsaved.
     * Useful for operations that modify the scene, but don't support undo.
     *
     * Generated from Godot docs: EditorUndoRedoManager.clear_history
     */
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

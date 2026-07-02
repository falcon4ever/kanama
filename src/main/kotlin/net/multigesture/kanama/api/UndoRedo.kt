package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides a high-level interface for implementing undo and redo operations.
 *
 * Generated from Godot docs: UndoRedo
 */
class UndoRedo(handle: MemorySegment) : GodotObject(handle) {
    var maxSteps: Int
        @JvmName("maxStepsProperty")
        get() = getMaxSteps()
        @JvmName("setMaxStepsProperty")
        set(value) = setMaxSteps(value)

    fun createAction(name: String, mergeMode: Long = 0L, backwardUndoOps: Boolean = false) {
        ObjectCalls.ptrcallWithStringLongBoolArgs(createActionBind, handle, name, mergeMode, backwardUndoOps)
    }

    fun commitAction(execute: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(commitActionBind, handle, execute)
    }

    fun isCommittingAction(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCommittingActionBind, handle)
    }

    fun addDoMethod(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(addDoMethodBind, handle, callable.target.handle, callable.method)
    }

    fun addUndoMethod(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(addUndoMethodBind, handle, callable.target.handle, callable.method)
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

    fun startForceKeepInMergeEnds() {
        ObjectCalls.ptrcallNoArgs(startForceKeepInMergeEndsBind, handle)
    }

    fun endForceKeepInMergeEnds() {
        ObjectCalls.ptrcallNoArgs(endForceKeepInMergeEndsBind, handle)
    }

    fun getHistoryCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHistoryCountBind, handle)
    }

    fun getCurrentAction(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentActionBind, handle)
    }

    fun getActionName(id: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getActionNameBind, handle, id)
    }

    fun clearHistory(increaseVersion: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(clearHistoryBind, handle, increaseVersion)
    }

    fun getCurrentActionName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentActionNameBind, handle)
    }

    fun hasUndo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasUndoBind, handle)
    }

    fun hasRedo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasRedoBind, handle)
    }

    fun getVersion(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVersionBind, handle)
    }

    fun setMaxSteps(maxSteps: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxStepsBind, handle, maxSteps)
    }

    fun getMaxSteps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxStepsBind, handle)
    }

    /**
     * Redo the last action. Returns `false` if there was no action to redo.
     *
     * Generated from Godot docs: UndoRedo.redo
     */
    fun redo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(redoBind, handle)
    }

    /**
     * Undo the last action. Returns `false` if there was no action to undo.
     *
     * Generated from Godot docs: UndoRedo.undo
     */
    fun undo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(undoBind, handle)
    }

    object Signals {
        const val versionChanged: String = "version_changed"
    }

    companion object {
        const val MERGE_DISABLE: Long = 0L
        const val MERGE_ENDS: Long = 1L
        const val MERGE_ALL: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): UndoRedo? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UndoRedo? =
            if (handle.address() == 0L) null else UndoRedo(handle)

        private const val CREATE_ACTION_HASH = 3171901514L
        private val createActionBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "create_action", CREATE_ACTION_HASH)
        }

        private const val COMMIT_ACTION_HASH = 3216645846L
        private val commitActionBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "commit_action", COMMIT_ACTION_HASH)
        }

        private const val IS_COMMITTING_ACTION_HASH = 36873697L
        private val isCommittingActionBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "is_committing_action", IS_COMMITTING_ACTION_HASH)
        }

        private const val ADD_DO_METHOD_HASH = 1611583062L
        private val addDoMethodBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "add_do_method", ADD_DO_METHOD_HASH)
        }

        private const val ADD_UNDO_METHOD_HASH = 1611583062L
        private val addUndoMethodBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "add_undo_method", ADD_UNDO_METHOD_HASH)
        }

        private const val ADD_DO_PROPERTY_HASH = 1017172818L
        private val addDoPropertyBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "add_do_property", ADD_DO_PROPERTY_HASH)
        }

        private const val ADD_UNDO_PROPERTY_HASH = 1017172818L
        private val addUndoPropertyBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "add_undo_property", ADD_UNDO_PROPERTY_HASH)
        }

        private const val ADD_DO_REFERENCE_HASH = 3975164845L
        private val addDoReferenceBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "add_do_reference", ADD_DO_REFERENCE_HASH)
        }

        private const val ADD_UNDO_REFERENCE_HASH = 3975164845L
        private val addUndoReferenceBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "add_undo_reference", ADD_UNDO_REFERENCE_HASH)
        }

        private const val START_FORCE_KEEP_IN_MERGE_ENDS_HASH = 3218959716L
        private val startForceKeepInMergeEndsBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "start_force_keep_in_merge_ends", START_FORCE_KEEP_IN_MERGE_ENDS_HASH)
        }

        private const val END_FORCE_KEEP_IN_MERGE_ENDS_HASH = 3218959716L
        private val endForceKeepInMergeEndsBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "end_force_keep_in_merge_ends", END_FORCE_KEEP_IN_MERGE_ENDS_HASH)
        }

        private const val GET_HISTORY_COUNT_HASH = 2455072627L
        private val getHistoryCountBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "get_history_count", GET_HISTORY_COUNT_HASH)
        }

        private const val GET_CURRENT_ACTION_HASH = 2455072627L
        private val getCurrentActionBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "get_current_action", GET_CURRENT_ACTION_HASH)
        }

        private const val GET_ACTION_NAME_HASH = 990163283L
        private val getActionNameBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "get_action_name", GET_ACTION_NAME_HASH)
        }

        private const val CLEAR_HISTORY_HASH = 3216645846L
        private val clearHistoryBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "clear_history", CLEAR_HISTORY_HASH)
        }

        private const val GET_CURRENT_ACTION_NAME_HASH = 201670096L
        private val getCurrentActionNameBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "get_current_action_name", GET_CURRENT_ACTION_NAME_HASH)
        }

        private const val HAS_UNDO_HASH = 36873697L
        private val hasUndoBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "has_undo", HAS_UNDO_HASH)
        }

        private const val HAS_REDO_HASH = 36873697L
        private val hasRedoBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "has_redo", HAS_REDO_HASH)
        }

        private const val GET_VERSION_HASH = 3905245786L
        private val getVersionBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "get_version", GET_VERSION_HASH)
        }

        private const val SET_MAX_STEPS_HASH = 1286410249L
        private val setMaxStepsBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "set_max_steps", SET_MAX_STEPS_HASH)
        }

        private const val GET_MAX_STEPS_HASH = 3905245786L
        private val getMaxStepsBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "get_max_steps", GET_MAX_STEPS_HASH)
        }

        private const val REDO_HASH = 2240911060L
        private val redoBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "redo", REDO_HASH)
        }

        private const val UNDO_HASH = 2240911060L
        private val undoBind by lazy {
            ObjectCalls.getMethodBind("UndoRedo", "undo", UNDO_HASH)
        }
    }
}

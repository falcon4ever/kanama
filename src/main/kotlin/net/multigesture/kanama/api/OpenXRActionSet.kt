package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRActionSet
 */
class OpenXRActionSet(handle: MemorySegment) : Resource(handle) {
    var localizedName: String
        @JvmName("localizedNameProperty")
        get() = getLocalizedName()
        @JvmName("setLocalizedNameProperty")
        set(value) = setLocalizedName(value)

    var priority: Int
        @JvmName("priorityProperty")
        get() = getPriority()
        @JvmName("setPriorityProperty")
        set(value) = setPriority(value)

    var actions: List<Any?>
        @JvmName("actionsProperty")
        get() = getActions()
        @JvmName("setActionsProperty")
        set(value) = setActions(value)

    fun setLocalizedName(localizedName: String) {
        ObjectCalls.ptrcallWithStringArg(setLocalizedNameBind, handle, localizedName)
    }

    fun getLocalizedName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocalizedNameBind, handle)
    }

    fun setPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setPriorityBind, handle, priority)
    }

    fun getPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPriorityBind, handle)
    }

    fun getActionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getActionCountBind, handle)
    }

    fun setActions(actions: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setActionsBind, handle, actions)
    }

    fun getActions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getActionsBind, handle)
    }

    fun addAction(action: OpenXRAction?) {
        ObjectCalls.ptrcallWithObjectArgs(addActionBind, handle, listOf(action?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeAction(action: OpenXRAction?) {
        ObjectCalls.ptrcallWithObjectArgs(removeActionBind, handle, listOf(action?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRActionSet? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRActionSet? =
            if (handle.address() == 0L) null else OpenXRActionSet(handle)

        private const val SET_LOCALIZED_NAME_HASH = 83702148L
        private val setLocalizedNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "set_localized_name", SET_LOCALIZED_NAME_HASH)
        }

        private const val GET_LOCALIZED_NAME_HASH = 201670096L
        private val getLocalizedNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_localized_name", GET_LOCALIZED_NAME_HASH)
        }

        private const val SET_PRIORITY_HASH = 1286410249L
        private val setPriorityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "set_priority", SET_PRIORITY_HASH)
        }

        private const val GET_PRIORITY_HASH = 3905245786L
        private val getPriorityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_priority", GET_PRIORITY_HASH)
        }

        private const val GET_ACTION_COUNT_HASH = 3905245786L
        private val getActionCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_action_count", GET_ACTION_COUNT_HASH)
        }

        private const val SET_ACTIONS_HASH = 381264803L
        private val setActionsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "set_actions", SET_ACTIONS_HASH)
        }

        private const val GET_ACTIONS_HASH = 3995934104L
        private val getActionsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_actions", GET_ACTIONS_HASH)
        }

        private const val ADD_ACTION_HASH = 349361333L
        private val addActionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "add_action", ADD_ACTION_HASH)
        }

        private const val REMOVE_ACTION_HASH = 349361333L
        private val removeActionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionSet", "remove_action", REMOVE_ACTION_HASH)
        }
    }
}

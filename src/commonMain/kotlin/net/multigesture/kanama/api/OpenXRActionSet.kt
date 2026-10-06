package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRActionSet
 */
class OpenXRActionSet(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setLocalizedNameBind, segment, localizedName)
    }

    fun getLocalizedName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLocalizedNameBind, segment)
    }

    fun setPriority(priority: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setPriorityBind, segment, priority)
    }

    fun getPriority(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPriorityBind, segment)
    }

    fun getActionCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getActionCountBind, segment)
    }

    fun setActions(actions: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setActionsBind, segment, actions)
    }

    fun getActions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getActionsBind, segment)
    }

    fun addAction(action: OpenXRAction?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addActionBind, segment, listOf(action?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun removeAction(action: OpenXRAction?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeActionBind, segment, listOf(action?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRActionSet? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRActionSet? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRActionSet(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRActionSet? =
            if (handle.address() == 0L) null else OpenXRActionSet(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_LOCALIZED_NAME_HASH = 83702148L
        @JvmField
        val setLocalizedNameBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "set_localized_name", SET_LOCALIZED_NAME_HASH)

        private const val GET_LOCALIZED_NAME_HASH = 201670096L
        @JvmField
        val getLocalizedNameBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_localized_name", GET_LOCALIZED_NAME_HASH)

        private const val SET_PRIORITY_HASH = 1286410249L
        @JvmField
        val setPriorityBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "set_priority", SET_PRIORITY_HASH)

        private const val GET_PRIORITY_HASH = 3905245786L
        @JvmField
        val getPriorityBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_priority", GET_PRIORITY_HASH)

        private const val GET_ACTION_COUNT_HASH = 3905245786L
        @JvmField
        val getActionCountBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_action_count", GET_ACTION_COUNT_HASH)

        private const val SET_ACTIONS_HASH = 381264803L
        @JvmField
        val setActionsBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "set_actions", SET_ACTIONS_HASH)

        private const val GET_ACTIONS_HASH = 3995934104L
        @JvmField
        val getActionsBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "get_actions", GET_ACTIONS_HASH)

        private const val ADD_ACTION_HASH = 349361333L
        @JvmField
        val addActionBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "add_action", ADD_ACTION_HASH)

        private const val REMOVE_ACTION_HASH = 349361333L
        @JvmField
        val removeActionBind =
            ObjectCalls.getMethodBind("OpenXRActionSet", "remove_action", REMOVE_ACTION_HASH)
    }
}

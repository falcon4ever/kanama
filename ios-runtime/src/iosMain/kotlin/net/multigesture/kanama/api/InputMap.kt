package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: InputMap
 */
object InputMap {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("InputMap")
    }

    fun hasAction(action: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasActionBind, singleton, action)
    }

    fun getActions(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getActionsBind, singleton)
    }

    fun addAction(action: String, deadzone: Double = 0.2) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(addActionBind, singleton, action, deadzone)
    }

    fun eraseAction(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(eraseActionBind, singleton, action)
    }

    fun actionSetDeadzone(action: String, deadzone: Double) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(actionSetDeadzoneBind, singleton, action, deadzone)
    }

    fun actionGetDeadzone(action: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(actionGetDeadzoneBind, singleton, action)
    }

    fun actionAddEvent(action: String, event: InputEvent?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(actionAddEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun actionHasEvent(action: String, event: InputEvent?): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndObjectArgRetBool(actionHasEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun actionEraseEvent(action: String, event: InputEvent?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(actionEraseEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun actionEraseEvents(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(actionEraseEventsBind, singleton, action)
    }

    fun eventIsAction(event: InputEvent?, action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithObjectStringNameAndBoolArgRetBool(eventIsActionBind, singleton, event?.requireOpenHandle() ?: MemorySegment.NULL, action, exactMatch)
    }

    fun loadFromProjectSettings() {
        ObjectCalls.ptrcallNoArgs(loadFromProjectSettingsBind, singleton)
    }

    object Signals {
        const val projectSettingsLoaded: String = "project_settings_loaded"
    }

    fun fromHandle(handle: MemorySegment): InputMap? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): InputMap? =
        if (handle.address() == 0L) null else this

    private const val HAS_ACTION_HASH = 2619796661L
    private val hasActionBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "has_action", HAS_ACTION_HASH)
    }

    private const val GET_ACTIONS_HASH = 2915620761L
    private val getActionsBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "get_actions", GET_ACTIONS_HASH)
    }

    private const val ADD_ACTION_HASH = 1195233573L
    private val addActionBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "add_action", ADD_ACTION_HASH)
    }

    private const val ERASE_ACTION_HASH = 3304788590L
    private val eraseActionBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "erase_action", ERASE_ACTION_HASH)
    }

    private const val ACTION_SET_DEADZONE_HASH = 4135858297L
    private val actionSetDeadzoneBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_set_deadzone", ACTION_SET_DEADZONE_HASH)
    }

    private const val ACTION_GET_DEADZONE_HASH = 1391627649L
    private val actionGetDeadzoneBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_get_deadzone", ACTION_GET_DEADZONE_HASH)
    }

    private const val ACTION_ADD_EVENT_HASH = 518302593L
    private val actionAddEventBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_add_event", ACTION_ADD_EVENT_HASH)
    }

    private const val ACTION_HAS_EVENT_HASH = 1185871985L
    private val actionHasEventBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_has_event", ACTION_HAS_EVENT_HASH)
    }

    private const val ACTION_ERASE_EVENT_HASH = 518302593L
    private val actionEraseEventBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_erase_event", ACTION_ERASE_EVENT_HASH)
    }

    private const val ACTION_ERASE_EVENTS_HASH = 3304788590L
    private val actionEraseEventsBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_erase_events", ACTION_ERASE_EVENTS_HASH)
    }

    private const val EVENT_IS_ACTION_HASH = 3193353650L
    private val eventIsActionBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "event_is_action", EVENT_IS_ACTION_HASH)
    }

    private const val LOAD_FROM_PROJECT_SETTINGS_HASH = 3218959716L
    private val loadFromProjectSettingsBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "load_from_project_settings", LOAD_FROM_PROJECT_SETTINGS_HASH)
    }
}

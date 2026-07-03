package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton that manages all `InputEventAction`s.
 *
 * Generated from Godot docs: InputMap
 */
object InputMap {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("InputMap")
    }

    /**
     * Returns `true` if the `InputMap` has a registered action with the given name.
     *
     * Generated from Godot docs: InputMap.has_action
     */
    @JvmStatic
    fun hasAction(action: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasActionBind, singleton, action)
    }

    /**
     * Returns an array of all actions in the `InputMap`.
     *
     * Generated from Godot docs: InputMap.get_actions
     */
    @JvmStatic
    fun getActions(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getActionsBind, singleton)
    }

    /**
     * Adds an empty action to the `InputMap` with a configurable `deadzone`. An `InputEvent` can then
     * be added to this action with `action_add_event`.
     *
     * Generated from Godot docs: InputMap.add_action
     */
    @JvmStatic
    fun addAction(action: String, deadzone: Double = 0.2) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(addActionBind, singleton, action, deadzone)
    }

    /**
     * Removes an action from the `InputMap`.
     *
     * Generated from Godot docs: InputMap.erase_action
     */
    @JvmStatic
    fun eraseAction(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(eraseActionBind, singleton, action)
    }

    /**
     * Returns the human-readable description of the given action.
     *
     * Generated from Godot docs: InputMap.get_action_description
     */
    @JvmStatic
    fun getActionDescription(action: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetString(getActionDescriptionBind, singleton, action)
    }

    /**
     * Sets a deadzone value for the action.
     *
     * Generated from Godot docs: InputMap.action_set_deadzone
     */
    @JvmStatic
    fun actionSetDeadzone(action: String, deadzone: Double) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(actionSetDeadzoneBind, singleton, action, deadzone)
    }

    /**
     * Returns a deadzone value for the action.
     *
     * Generated from Godot docs: InputMap.action_get_deadzone
     */
    @JvmStatic
    fun actionGetDeadzone(action: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(actionGetDeadzoneBind, singleton, action)
    }

    /**
     * Adds an `InputEvent` to an action. This `InputEvent` will trigger the action.
     *
     * Generated from Godot docs: InputMap.action_add_event
     */
    @JvmStatic
    fun actionAddEvent(action: String, event: InputEvent?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(actionAddEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns `true` if the action has the given `InputEvent` associated with it.
     *
     * Generated from Godot docs: InputMap.action_has_event
     */
    @JvmStatic
    fun actionHasEvent(action: String, event: InputEvent?): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndObjectArgRetBool(actionHasEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes an `InputEvent` from an action.
     *
     * Generated from Godot docs: InputMap.action_erase_event
     */
    @JvmStatic
    fun actionEraseEvent(action: String, event: InputEvent?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(actionEraseEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes all events from an action.
     *
     * Generated from Godot docs: InputMap.action_erase_events
     */
    @JvmStatic
    fun actionEraseEvents(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(actionEraseEventsBind, singleton, action)
    }

    /**
     * Returns an array of `InputEvent`s associated with a given action. Note: When used in the editor
     * (e.g. a tool script or `EditorPlugin`), this method will return events for the editor action. If
     * you want to access your project's input binds from the editor, read the `input/\*` settings from
     * `ProjectSettings`.
     *
     * Generated from Godot docs: InputMap.action_get_events
     */
    @JvmStatic
    fun actionGetEvents(action: String): List<InputEvent> {
        return ObjectCalls.ptrcallWithStringNameArgRetTypedObjectList(actionGetEventsBind, singleton, action, InputEvent::fromHandle)
    }

    /**
     * Returns `true` if the given event is part of an existing action. This method ignores keyboard
     * modifiers if the given `InputEvent` is not pressed (for proper release detection). See
     * `action_has_event` if you don't want this behavior. If `exact_match` is `false`, it ignores
     * additional input modifiers for `InputEventKey` and `InputEventMouseButton` events, and the
     * direction for `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: InputMap.event_is_action
     */
    @JvmStatic
    fun eventIsAction(event: InputEvent?, action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithObjectStringNameAndBoolArgRetBool(eventIsActionBind, singleton, event?.requireOpenHandle() ?: MemorySegment.NULL, action, exactMatch)
    }

    /**
     * Clears all `InputEventAction` in the `InputMap` and load it anew from `ProjectSettings`.
     *
     * Generated from Godot docs: InputMap.load_from_project_settings
     */
    @JvmStatic
    fun loadFromProjectSettings() {
        ObjectCalls.ptrcallNoArgs(loadFromProjectSettingsBind, singleton)
    }

    object Signals {
        const val projectSettingsLoaded: String = "project_settings_loaded"
    }

    @JvmStatic
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

    private const val GET_ACTION_DESCRIPTION_HASH = 957595536L
    private val getActionDescriptionBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "get_action_description", GET_ACTION_DESCRIPTION_HASH)
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

    private const val ACTION_GET_EVENTS_HASH = 689397652L
    private val actionGetEventsBind by lazy {
        ObjectCalls.getMethodBind("InputMap", "action_get_events", ACTION_GET_EVENTS_HASH)
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

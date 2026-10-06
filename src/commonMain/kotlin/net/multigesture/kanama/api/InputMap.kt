package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A singleton that manages all `InputEventAction`s.
 *
 * Generated from Godot docs: InputMap
 */
object InputMap {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Returns `true` if the `InputMap` has a registered action with the given name.
     *
     * Generated from Godot docs: InputMap.has_action
     */
    @JvmStatic
    fun hasAction(action: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasActionBind, singleton, action)
    }

    /**
     * Returns an array of all actions in the `InputMap`.
     *
     * Generated from Godot docs: InputMap.get_actions
     */
    @JvmStatic
    fun getActions(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(Binds.getActionsBind, singleton)
    }

    /**
     * Adds an empty action to the `InputMap` with a configurable `deadzone`. An `InputEvent` can then
     * be added to this action with `action_add_event`.
     *
     * Generated from Godot docs: InputMap.add_action
     */
    @JvmStatic
    fun addAction(action: String, deadzone: Double = 0.2) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(Binds.addActionBind, singleton, action, deadzone)
    }

    /**
     * Removes an action from the `InputMap`.
     *
     * Generated from Godot docs: InputMap.erase_action
     */
    @JvmStatic
    fun eraseAction(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.eraseActionBind, singleton, action)
    }

    /**
     * Returns the human-readable description of the given action.
     *
     * Generated from Godot docs: InputMap.get_action_description
     */
    @JvmStatic
    fun getActionDescription(action: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetString(Binds.getActionDescriptionBind, singleton, action)
    }

    /**
     * Sets a deadzone value for the action.
     *
     * Generated from Godot docs: InputMap.action_set_deadzone
     */
    @JvmStatic
    fun actionSetDeadzone(action: String, deadzone: Double) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(Binds.actionSetDeadzoneBind, singleton, action, deadzone)
    }

    /**
     * Returns a deadzone value for the action.
     *
     * Generated from Godot docs: InputMap.action_get_deadzone
     */
    @JvmStatic
    fun actionGetDeadzone(action: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(Binds.actionGetDeadzoneBind, singleton, action)
    }

    /**
     * Adds an `InputEvent` to an action. This `InputEvent` will trigger the action.
     *
     * Generated from Godot docs: InputMap.action_add_event
     */
    @JvmStatic
    fun actionAddEvent(action: String, event: InputEvent) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(Binds.actionAddEventBind, singleton, action, event.requireOpenHandle())
    }

    /**
     * Returns `true` if the action has the given `InputEvent` associated with it.
     *
     * Generated from Godot docs: InputMap.action_has_event
     */
    @JvmStatic
    fun actionHasEvent(action: String, event: InputEvent): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndObjectArgRetBool(Binds.actionHasEventBind, singleton, action, event.requireOpenHandle())
    }

    /**
     * Removes an `InputEvent` from an action.
     *
     * Generated from Godot docs: InputMap.action_erase_event
     */
    @JvmStatic
    fun actionEraseEvent(action: String, event: InputEvent) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(Binds.actionEraseEventBind, singleton, action, event.requireOpenHandle())
    }

    /**
     * Removes all events from an action.
     *
     * Generated from Godot docs: InputMap.action_erase_events
     */
    @JvmStatic
    fun actionEraseEvents(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.actionEraseEventsBind, singleton, action)
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
        return ObjectCalls.ptrcallWithStringNameArgRetTypedObjectList(Binds.actionGetEventsBind, singleton, action, InputEvent::wrapBorrowed)
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
    fun eventIsAction(event: InputEvent, action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithObjectStringNameAndBoolArgRetBool(Binds.eventIsActionBind, singleton, event.requireOpenHandle(), action, exactMatch)
    }

    /**
     * Clears all `InputEventAction` in the `InputMap` and load it anew from `ProjectSettings`.
     *
     * Generated from Godot docs: InputMap.load_from_project_settings
     */
    @JvmStatic
    fun loadFromProjectSettings() {
        ObjectCalls.ptrcallNoArgs(Binds.loadFromProjectSettingsBind, singleton)
    }

    /** Signal `project_settings_loaded()`; see [TypedSignal]. */
    val projectSettingsLoaded: Signal0
        @JvmName("projectSettingsLoadedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "project_settings_loaded")

    object Signals {
        const val projectSettingsLoaded: String = "project_settings_loaded"
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): InputMap? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): InputMap? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("InputMap")

        private const val HAS_ACTION_HASH = 2619796661L
        @JvmField
        val hasActionBind =
            ObjectCalls.getMethodBind("InputMap", "has_action", HAS_ACTION_HASH)

        private const val GET_ACTIONS_HASH = 2915620761L
        @JvmField
        val getActionsBind =
            ObjectCalls.getMethodBind("InputMap", "get_actions", GET_ACTIONS_HASH)

        private const val ADD_ACTION_HASH = 1195233573L
        @JvmField
        val addActionBind =
            ObjectCalls.getMethodBind("InputMap", "add_action", ADD_ACTION_HASH)

        private const val ERASE_ACTION_HASH = 3304788590L
        @JvmField
        val eraseActionBind =
            ObjectCalls.getMethodBind("InputMap", "erase_action", ERASE_ACTION_HASH)

        private const val GET_ACTION_DESCRIPTION_HASH = 957595536L
        @JvmField
        val getActionDescriptionBind =
            ObjectCalls.getMethodBind("InputMap", "get_action_description", GET_ACTION_DESCRIPTION_HASH)

        private const val ACTION_SET_DEADZONE_HASH = 4135858297L
        @JvmField
        val actionSetDeadzoneBind =
            ObjectCalls.getMethodBind("InputMap", "action_set_deadzone", ACTION_SET_DEADZONE_HASH)

        private const val ACTION_GET_DEADZONE_HASH = 1391627649L
        @JvmField
        val actionGetDeadzoneBind =
            ObjectCalls.getMethodBind("InputMap", "action_get_deadzone", ACTION_GET_DEADZONE_HASH)

        private const val ACTION_ADD_EVENT_HASH = 518302593L
        @JvmField
        val actionAddEventBind =
            ObjectCalls.getMethodBind("InputMap", "action_add_event", ACTION_ADD_EVENT_HASH)

        private const val ACTION_HAS_EVENT_HASH = 1185871985L
        @JvmField
        val actionHasEventBind =
            ObjectCalls.getMethodBind("InputMap", "action_has_event", ACTION_HAS_EVENT_HASH)

        private const val ACTION_ERASE_EVENT_HASH = 518302593L
        @JvmField
        val actionEraseEventBind =
            ObjectCalls.getMethodBind("InputMap", "action_erase_event", ACTION_ERASE_EVENT_HASH)

        private const val ACTION_ERASE_EVENTS_HASH = 3304788590L
        @JvmField
        val actionEraseEventsBind =
            ObjectCalls.getMethodBind("InputMap", "action_erase_events", ACTION_ERASE_EVENTS_HASH)

        private const val ACTION_GET_EVENTS_HASH = 689397652L
        @JvmField
        val actionGetEventsBind =
            ObjectCalls.getMethodBind("InputMap", "action_get_events", ACTION_GET_EVENTS_HASH)

        private const val EVENT_IS_ACTION_HASH = 3193353650L
        @JvmField
        val eventIsActionBind =
            ObjectCalls.getMethodBind("InputMap", "event_is_action", EVENT_IS_ACTION_HASH)

        private const val LOAD_FROM_PROJECT_SETTINGS_HASH = 3218959716L
        @JvmField
        val loadFromProjectSettingsBind =
            ObjectCalls.getMethodBind("InputMap", "load_from_project_settings", LOAD_FROM_PROJECT_SETTINGS_HASH)
    }
}

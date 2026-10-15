package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An OpenXR action.
 *
 * Generated from Godot docs: OpenXRAction
 */
class OpenXRAction(handle: GodotHandle) : Resource(handle) {
    var localizedName: String
        @JvmName("localizedNameProperty")
        get() = getLocalizedName()
        @JvmName("setLocalizedNameProperty")
        set(value) = setLocalizedName(value)

    var actionType: OpenXRAction.ActionType
        @JvmName("actionTypeProperty")
        get() = getActionType()
        @JvmName("setActionTypeProperty")
        set(value) = setActionType(value)

    var toplevelPaths: List<String>
        @JvmName("toplevelPathsProperty")
        get() = getToplevelPaths()
        @JvmName("setToplevelPathsProperty")
        set(value) = setToplevelPaths(value)

    /**
     * The localized description of this action.
     *
     * Generated from Godot docs: OpenXRAction.set_localized_name
     */
    fun setLocalizedName(localizedName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setLocalizedNameBind, segment, localizedName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The localized description of this action.
     *
     * Generated from Godot docs: OpenXRAction.get_localized_name
     */
    fun getLocalizedName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getLocalizedNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of action.
     *
     * Generated from Godot docs: OpenXRAction.set_action_type
     */
    fun setActionType(actionType: OpenXRAction.ActionType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setActionTypeBind, segment, actionType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type of action.
     *
     * Generated from Godot docs: OpenXRAction.get_action_type
     */
    fun getActionType(): OpenXRAction.ActionType {
        checkOpen()
        val ret = OpenXRAction.ActionType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getActionTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * A collections of toplevel paths to which this action can be bound.
     *
     * Generated from Godot docs: OpenXRAction.set_toplevel_paths
     */
    fun setToplevelPaths(toplevelPaths: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setToplevelPathsBind, segment, toplevelPaths)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A collections of toplevel paths to which this action can be bound.
     *
     * Generated from Godot docs: OpenXRAction.get_toplevel_paths
     */
    fun getToplevelPaths(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getToplevelPathsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `OpenXRAction.ActionType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`OpenXRAction.ActionType.<NAME>`).
     *
     * Generated from Godot docs: OpenXRAction.ActionType
     */
    @JvmInline
    value class ActionType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This action provides a boolean value.
             *
             * Generated from Godot docs: OpenXRAction.OPENXR_ACTION_BOOL
             */
            val BOOL: ActionType get() = ActionType(0L)
            /**
             * This action provides a float value between `0.0` and `1.0` for any analog input such as
             * triggers.
             *
             * Generated from Godot docs: OpenXRAction.OPENXR_ACTION_FLOAT
             */
            val FLOAT: ActionType get() = ActionType(1L)
            /**
             * This action provides a `Vector2` value and can be bound to embedded trackpads and joysticks.
             *
             * Generated from Godot docs: OpenXRAction.OPENXR_ACTION_VECTOR2
             */
            val VECTOR2: ActionType get() = ActionType(2L)
            val POSE: ActionType get() = ActionType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAction? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRAction? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRAction(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRAction? =
            if (handle.address() == 0L) null else OpenXRAction(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_LOCALIZED_NAME_HASH = 83702148L
        @JvmField
        val setLocalizedNameBind =
            ObjectCalls.getMethodBind("OpenXRAction", "set_localized_name", SET_LOCALIZED_NAME_HASH)

        private const val GET_LOCALIZED_NAME_HASH = 201670096L
        @JvmField
        val getLocalizedNameBind =
            ObjectCalls.getMethodBind("OpenXRAction", "get_localized_name", GET_LOCALIZED_NAME_HASH)

        private const val SET_ACTION_TYPE_HASH = 1675238366L
        @JvmField
        val setActionTypeBind =
            ObjectCalls.getMethodBind("OpenXRAction", "set_action_type", SET_ACTION_TYPE_HASH)

        private const val GET_ACTION_TYPE_HASH = 3536542431L
        @JvmField
        val getActionTypeBind =
            ObjectCalls.getMethodBind("OpenXRAction", "get_action_type", GET_ACTION_TYPE_HASH)

        private const val SET_TOPLEVEL_PATHS_HASH = 4015028928L
        @JvmField
        val setToplevelPathsBind =
            ObjectCalls.getMethodBind("OpenXRAction", "set_toplevel_paths", SET_TOPLEVEL_PATHS_HASH)

        private const val GET_TOPLEVEL_PATHS_HASH = 1139954409L
        @JvmField
        val getToplevelPathsBind =
            ObjectCalls.getMethodBind("OpenXRAction", "get_toplevel_paths", GET_TOPLEVEL_PATHS_HASH)
    }
}

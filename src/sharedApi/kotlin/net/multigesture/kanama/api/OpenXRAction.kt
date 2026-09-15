package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRAction
 */
class OpenXRAction(handle: GodotHandle) : Resource(handle) {
    var localizedName: String
        @JvmName("localizedNameProperty")
        get() = getLocalizedName()
        @JvmName("setLocalizedNameProperty")
        set(value) = setLocalizedName(value)

    var actionType: Long
        @JvmName("actionTypeProperty")
        get() = getActionType()
        @JvmName("setActionTypeProperty")
        set(value) = setActionType(value)

    var toplevelPaths: List<String>
        @JvmName("toplevelPathsProperty")
        get() = getToplevelPaths()
        @JvmName("setToplevelPathsProperty")
        set(value) = setToplevelPaths(value)

    fun setLocalizedName(localizedName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setLocalizedNameBind, segment, localizedName)
    }

    fun getLocalizedName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLocalizedNameBind, segment)
    }

    fun setActionType(actionType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setActionTypeBind, segment, actionType)
    }

    fun getActionType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getActionTypeBind, segment)
    }

    fun setToplevelPaths(toplevelPaths: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(setToplevelPathsBind, segment, toplevelPaths)
    }

    fun getToplevelPaths(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getToplevelPathsBind, segment)
    }

    companion object {
        const val OPENXR_ACTION_BOOL: Long = 0L
        const val OPENXR_ACTION_FLOAT: Long = 1L
        const val OPENXR_ACTION_VECTOR2: Long = 2L
        const val OPENXR_ACTION_POSE: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAction? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRAction? =
            if (handle.address() == 0L) null else OpenXRAction(GodotHandle(handle))

        private const val SET_LOCALIZED_NAME_HASH = 83702148L
        private val setLocalizedNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAction", "set_localized_name", SET_LOCALIZED_NAME_HASH)
        }

        private const val GET_LOCALIZED_NAME_HASH = 201670096L
        private val getLocalizedNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAction", "get_localized_name", GET_LOCALIZED_NAME_HASH)
        }

        private const val SET_ACTION_TYPE_HASH = 1675238366L
        private val setActionTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAction", "set_action_type", SET_ACTION_TYPE_HASH)
        }

        private const val GET_ACTION_TYPE_HASH = 3536542431L
        private val getActionTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAction", "get_action_type", GET_ACTION_TYPE_HASH)
        }

        private const val SET_TOPLEVEL_PATHS_HASH = 4015028928L
        private val setToplevelPathsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAction", "set_toplevel_paths", SET_TOPLEVEL_PATHS_HASH)
        }

        private const val GET_TOPLEVEL_PATHS_HASH = 1139954409L
        private val getToplevelPathsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAction", "get_toplevel_paths", GET_TOPLEVEL_PATHS_HASH)
        }
    }
}

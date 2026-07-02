package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRAction
 */
class OpenXRAction(handle: MemorySegment) : Resource(handle) {
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
        ObjectCalls.ptrcallWithStringArg(setLocalizedNameBind, handle, localizedName)
    }

    fun getLocalizedName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocalizedNameBind, handle)
    }

    fun setActionType(actionType: Long) {
        ObjectCalls.ptrcallWithLongArg(setActionTypeBind, handle, actionType)
    }

    fun getActionType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getActionTypeBind, handle)
    }

    fun setToplevelPaths(toplevelPaths: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setToplevelPathsBind, handle, toplevelPaths)
    }

    fun getToplevelPaths(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getToplevelPathsBind, handle)
    }

    companion object {
        const val OPENXR_ACTION_BOOL: Long = 0L
        const val OPENXR_ACTION_FLOAT: Long = 1L
        const val OPENXR_ACTION_VECTOR2: Long = 2L
        const val OPENXR_ACTION_POSE: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRAction? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRAction? =
            if (handle.address() == 0L) null else OpenXRAction(handle)

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

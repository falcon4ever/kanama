package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRIPBinding
 */
class OpenXRIPBinding(handle: MemorySegment) : Resource(handle) {
    var action: OpenXRAction?
        @JvmName("actionProperty")
        get() = getAction()
        @JvmName("setActionProperty")
        set(value) = setAction(value)

    var bindingPath: String
        @JvmName("bindingPathProperty")
        get() = getBindingPath()
        @JvmName("setBindingPathProperty")
        set(value) = setBindingPath(value)

    var bindingModifiers: List<Any?>
        @JvmName("bindingModifiersProperty")
        get() = getBindingModifiers()
        @JvmName("setBindingModifiersProperty")
        set(value) = setBindingModifiers(value)

    var paths: List<String>
        @JvmName("pathsProperty")
        get() = getPaths()
        @JvmName("setPathsProperty")
        set(value) = setPaths(value)

    fun setAction(action: OpenXRAction?) {
        ObjectCalls.ptrcallWithObjectArgs(setActionBind, handle, listOf(action?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getAction(): OpenXRAction? {
        return OpenXRAction.wrap(ObjectCalls.ptrcallNoArgsRetObject(getActionBind, handle))
    }

    fun setBindingPath(bindingPath: String) {
        ObjectCalls.ptrcallWithStringArg(setBindingPathBind, handle, bindingPath)
    }

    fun getBindingPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBindingPathBind, handle)
    }

    fun getBindingModifierCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBindingModifierCountBind, handle)
    }

    fun getBindingModifier(index: Int): OpenXRActionBindingModifier? {
        return OpenXRActionBindingModifier.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBindingModifierBind, handle, index))
    }

    fun setBindingModifiers(bindingModifiers: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setBindingModifiersBind, handle, bindingModifiers)
    }

    fun getBindingModifiers(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getBindingModifiersBind, handle)
    }

    fun setPaths(paths: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setPathsBind, handle, paths)
    }

    fun getPaths(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getPathsBind, handle)
    }

    fun getPathCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPathCountBind, handle)
    }

    fun hasPath(path: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasPathBind, handle, path)
    }

    fun addPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(addPathBind, handle, path)
    }

    fun removePath(path: String) {
        ObjectCalls.ptrcallWithStringArg(removePathBind, handle, path)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRIPBinding? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRIPBinding? =
            if (handle.address() == 0L) null else OpenXRIPBinding(handle)

        private const val SET_ACTION_HASH = 349361333L
        private val setActionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_action", SET_ACTION_HASH)
        }

        private const val GET_ACTION_HASH = 4072409085L
        private val getActionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_action", GET_ACTION_HASH)
        }

        private const val SET_BINDING_PATH_HASH = 83702148L
        private val setBindingPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_binding_path", SET_BINDING_PATH_HASH)
        }

        private const val GET_BINDING_PATH_HASH = 201670096L
        private val getBindingPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_path", GET_BINDING_PATH_HASH)
        }

        private const val GET_BINDING_MODIFIER_COUNT_HASH = 3905245786L
        private val getBindingModifierCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_modifier_count", GET_BINDING_MODIFIER_COUNT_HASH)
        }

        private const val GET_BINDING_MODIFIER_HASH = 3538296211L
        private val getBindingModifierBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_modifier", GET_BINDING_MODIFIER_HASH)
        }

        private const val SET_BINDING_MODIFIERS_HASH = 381264803L
        private val setBindingModifiersBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_binding_modifiers", SET_BINDING_MODIFIERS_HASH)
        }

        private const val GET_BINDING_MODIFIERS_HASH = 3995934104L
        private val getBindingModifiersBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_modifiers", GET_BINDING_MODIFIERS_HASH)
        }

        private const val SET_PATHS_HASH = 4015028928L
        private val setPathsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_paths", SET_PATHS_HASH)
        }

        private const val GET_PATHS_HASH = 1139954409L
        private val getPathsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_paths", GET_PATHS_HASH)
        }

        private const val GET_PATH_COUNT_HASH = 3905245786L
        private val getPathCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_path_count", GET_PATH_COUNT_HASH)
        }

        private const val HAS_PATH_HASH = 3927539163L
        private val hasPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "has_path", HAS_PATH_HASH)
        }

        private const val ADD_PATH_HASH = 83702148L
        private val addPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "add_path", ADD_PATH_HASH)
        }

        private const val REMOVE_PATH_HASH = 83702148L
        private val removePathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRIPBinding", "remove_path", REMOVE_PATH_HASH)
        }
    }
}

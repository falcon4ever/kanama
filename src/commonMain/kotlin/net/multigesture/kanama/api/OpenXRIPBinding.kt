package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRIPBinding
 */
class OpenXRIPBinding(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setActionBind, segment, listOf(action?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getAction(): OpenXRAction? {
        checkOpen()
        return OpenXRAction.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getActionBind, segment))
    }

    fun setBindingPath(bindingPath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBindingPathBind, segment, bindingPath)
    }

    fun getBindingPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getBindingPathBind, segment)
    }

    fun getBindingModifierCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindingModifierCountBind, segment)
    }

    fun getBindingModifier(index: Int): OpenXRActionBindingModifier? {
        checkOpen()
        return OpenXRActionBindingModifier.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBindingModifierBind, segment, index))
    }

    fun setBindingModifiers(bindingModifiers: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBindingModifiersBind, segment, bindingModifiers)
    }

    fun getBindingModifiers(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getBindingModifiersBind, segment)
    }

    fun setPaths(paths: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setPathsBind, segment, paths)
    }

    fun getPaths(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getPathsBind, segment)
    }

    fun getPathCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPathCountBind, segment)
    }

    fun hasPath(path: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasPathBind, segment, path)
    }

    fun addPath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.addPathBind, segment, path)
    }

    fun removePath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.removePathBind, segment, path)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRIPBinding? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRIPBinding? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRIPBinding(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRIPBinding? =
            if (handle.address() == 0L) null else OpenXRIPBinding(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ACTION_HASH = 349361333L
        @JvmField
        val setActionBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_action", SET_ACTION_HASH)

        private const val GET_ACTION_HASH = 4072409085L
        @JvmField
        val getActionBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_action", GET_ACTION_HASH)

        private const val SET_BINDING_PATH_HASH = 83702148L
        @JvmField
        val setBindingPathBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_binding_path", SET_BINDING_PATH_HASH)

        private const val GET_BINDING_PATH_HASH = 201670096L
        @JvmField
        val getBindingPathBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_path", GET_BINDING_PATH_HASH)

        private const val GET_BINDING_MODIFIER_COUNT_HASH = 3905245786L
        @JvmField
        val getBindingModifierCountBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_modifier_count", GET_BINDING_MODIFIER_COUNT_HASH)

        private const val GET_BINDING_MODIFIER_HASH = 3538296211L
        @JvmField
        val getBindingModifierBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_modifier", GET_BINDING_MODIFIER_HASH)

        private const val SET_BINDING_MODIFIERS_HASH = 381264803L
        @JvmField
        val setBindingModifiersBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_binding_modifiers", SET_BINDING_MODIFIERS_HASH)

        private const val GET_BINDING_MODIFIERS_HASH = 3995934104L
        @JvmField
        val getBindingModifiersBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_binding_modifiers", GET_BINDING_MODIFIERS_HASH)

        private const val SET_PATHS_HASH = 4015028928L
        @JvmField
        val setPathsBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "set_paths", SET_PATHS_HASH)

        private const val GET_PATHS_HASH = 1139954409L
        @JvmField
        val getPathsBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_paths", GET_PATHS_HASH)

        private const val GET_PATH_COUNT_HASH = 3905245786L
        @JvmField
        val getPathCountBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "get_path_count", GET_PATH_COUNT_HASH)

        private const val HAS_PATH_HASH = 3927539163L
        @JvmField
        val hasPathBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "has_path", HAS_PATH_HASH)

        private const val ADD_PATH_HASH = 83702148L
        @JvmField
        val addPathBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "add_path", ADD_PATH_HASH)

        private const val REMOVE_PATH_HASH = 83702148L
        @JvmField
        val removePathBind =
            ObjectCalls.getMethodBind("OpenXRIPBinding", "remove_path", REMOVE_PATH_HASH)
    }
}

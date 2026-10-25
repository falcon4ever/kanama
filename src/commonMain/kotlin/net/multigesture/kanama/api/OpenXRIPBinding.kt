package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Defines a binding between an `OpenXRAction` and an XR input or output.
 *
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

    /**
     * `OpenXRAction` that is bound to `binding_path`.
     *
     * Generated from Godot docs: OpenXRIPBinding.set_action
     */
    fun setAction(action: OpenXRAction?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setActionBind, segment, listOf(action?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(action)
    }

    /**
     * `OpenXRAction` that is bound to `binding_path`.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_action
     */
    fun getAction(): OpenXRAction? {
        checkOpen()
        val ret = OpenXRAction.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getActionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Binding path that defines the input or output bound to `action`. Note: Binding paths are
     * suggestions, an XR runtime may choose to bind the action to a different input or output
     * emulating this input or output.
     *
     * Generated from Godot docs: OpenXRIPBinding.set_binding_path
     */
    fun setBindingPath(bindingPath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBindingPathBind, segment, bindingPath)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Binding path that defines the input or output bound to `action`. Note: Binding paths are
     * suggestions, an XR runtime may choose to bind the action to a different input or output
     * emulating this input or output.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_binding_path
     */
    fun getBindingPath(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getBindingPathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Get the number of binding modifiers for this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_binding_modifier_count
     */
    fun getBindingModifierCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindingModifierCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Get the `OpenXRBindingModifier` at this index.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_binding_modifier
     */
    fun getBindingModifier(index: Int): OpenXRActionBindingModifier? {
        checkOpen()
        val ret = OpenXRActionBindingModifier.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBindingModifierBind, segment, index))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Binding modifiers for this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.set_binding_modifiers
     */
    fun setBindingModifiers(bindingModifiers: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBindingModifiersBind, segment, bindingModifiers)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Binding modifiers for this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_binding_modifiers
     */
    fun getBindingModifiers(): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArray(Binds.getBindingModifiersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Paths that define the inputs or outputs bound on the device.
     *
     * Generated from Godot docs: OpenXRIPBinding.set_paths
     */
    fun setPaths(paths: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setPathsBind, segment, paths)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Paths that define the inputs or outputs bound on the device.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_paths
     */
    fun getPaths(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getPathsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Get the number of input/output paths in this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.get_path_count
     */
    fun getPathCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getPathCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if this input/output path is part of this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.has_path
     */
    fun hasPath(path: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasPathBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Add an input/output path to this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.add_path
     */
    fun addPath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.addPathBind, segment, path)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Removes this input/output path from this binding.
     *
     * Generated from Godot docs: OpenXRIPBinding.remove_path
     */
    fun removePath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.removePathBind, segment, path)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRIPBinding? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRIPBinding? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRIPBinding(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRIPBinding? =
            if (handle.address() == 0L) null else OpenXRIPBinding(GodotHandle(handle))

        /** A new OpenXRIPBinding, GDScript's `OpenXRIPBinding.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRIPBinding =
            RefCounted.owned(OpenXRIPBinding(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRIPBinding"), "OpenXRIPBinding"))))
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

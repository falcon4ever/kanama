package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Suggested bindings object for OpenXR.
 *
 * Generated from Godot docs: OpenXRInteractionProfile
 */
class OpenXRInteractionProfile(handle: GodotHandle) : Resource(handle) {
    var interactionProfilePath: String
        @JvmName("interactionProfilePathProperty")
        get() = getInteractionProfilePath()
        @JvmName("setInteractionProfilePathProperty")
        set(value) = setInteractionProfilePath(value)

    var bindings: List<Any?>
        @JvmName("bindingsProperty")
        get() = getBindings()
        @JvmName("setBindingsProperty")
        set(value) = setBindings(value)

    var bindingModifiers: List<Any?>
        @JvmName("bindingModifiersProperty")
        get() = getBindingModifiers()
        @JvmName("setBindingModifiersProperty")
        set(value) = setBindingModifiers(value)

    /**
     * The interaction profile path identifying the XR device.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.set_interaction_profile_path
     */
    fun setInteractionProfilePath(interactionProfilePath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setInteractionProfilePathBind, segment, interactionProfilePath)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The interaction profile path identifying the XR device.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.get_interaction_profile_path
     */
    fun getInteractionProfilePath(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getInteractionProfilePathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Get the number of bindings in this interaction profile.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.get_binding_count
     */
    fun getBindingCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindingCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Retrieve the binding at this index.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.get_binding
     */
    fun getBinding(index: Int): OpenXRIPBinding? {
        checkOpen()
        val ret = OpenXRIPBinding.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBindingBind, segment, index))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Action bindings for this interaction profile.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.set_bindings
     */
    fun setBindings(bindings: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBindingsBind, segment, bindings)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Action bindings for this interaction profile.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.get_bindings
     */
    fun getBindings(): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArray(Binds.getBindingsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Get the number of binding modifiers in this interaction profile.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.get_binding_modifier_count
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
     * Generated from Godot docs: OpenXRInteractionProfile.get_binding_modifier
     */
    fun getBindingModifier(index: Int): OpenXRIPBindingModifier? {
        checkOpen()
        val ret = OpenXRIPBindingModifier.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBindingModifierBind, segment, index))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Binding modifiers for this interaction profile.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.set_binding_modifiers
     */
    fun setBindingModifiers(bindingModifiers: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBindingModifiersBind, segment, bindingModifiers)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Binding modifiers for this interaction profile.
     *
     * Generated from Godot docs: OpenXRInteractionProfile.get_binding_modifiers
     */
    fun getBindingModifiers(): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArray(Binds.getBindingModifiersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfile? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRInteractionProfile? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRInteractionProfile(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRInteractionProfile? =
            if (handle.address() == 0L) null else OpenXRInteractionProfile(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_INTERACTION_PROFILE_PATH_HASH = 83702148L
        @JvmField
        val setInteractionProfilePathBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_interaction_profile_path", SET_INTERACTION_PROFILE_PATH_HASH)

        private const val GET_INTERACTION_PROFILE_PATH_HASH = 201670096L
        @JvmField
        val getInteractionProfilePathBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_interaction_profile_path", GET_INTERACTION_PROFILE_PATH_HASH)

        private const val GET_BINDING_COUNT_HASH = 3905245786L
        @JvmField
        val getBindingCountBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_count", GET_BINDING_COUNT_HASH)

        private const val GET_BINDING_HASH = 3934429652L
        @JvmField
        val getBindingBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding", GET_BINDING_HASH)

        private const val SET_BINDINGS_HASH = 381264803L
        @JvmField
        val setBindingsBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_bindings", SET_BINDINGS_HASH)

        private const val GET_BINDINGS_HASH = 3995934104L
        @JvmField
        val getBindingsBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_bindings", GET_BINDINGS_HASH)

        private const val GET_BINDING_MODIFIER_COUNT_HASH = 3905245786L
        @JvmField
        val getBindingModifierCountBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_modifier_count", GET_BINDING_MODIFIER_COUNT_HASH)

        private const val GET_BINDING_MODIFIER_HASH = 2419896583L
        @JvmField
        val getBindingModifierBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_modifier", GET_BINDING_MODIFIER_HASH)

        private const val SET_BINDING_MODIFIERS_HASH = 381264803L
        @JvmField
        val setBindingModifiersBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_binding_modifiers", SET_BINDING_MODIFIERS_HASH)

        private const val GET_BINDING_MODIFIERS_HASH = 3995934104L
        @JvmField
        val getBindingModifiersBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_modifiers", GET_BINDING_MODIFIERS_HASH)
    }
}

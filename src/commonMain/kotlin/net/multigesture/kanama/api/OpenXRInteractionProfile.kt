package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun setInteractionProfilePath(interactionProfilePath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setInteractionProfilePathBind, segment, interactionProfilePath)
    }

    fun getInteractionProfilePath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getInteractionProfilePathBind, segment)
    }

    fun getBindingCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindingCountBind, segment)
    }

    fun getBinding(index: Int): OpenXRIPBinding? {
        checkOpen()
        return OpenXRIPBinding.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBindingBind, segment, index))
    }

    fun setBindings(bindings: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBindingsBind, segment, bindings)
    }

    fun getBindings(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getBindingsBind, segment)
    }

    fun getBindingModifierCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindingModifierCountBind, segment)
    }

    fun getBindingModifier(index: Int): OpenXRIPBindingModifier? {
        checkOpen()
        return OpenXRIPBindingModifier.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBindingModifierBind, segment, index))
    }

    fun setBindingModifiers(bindingModifiers: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBindingModifiersBind, segment, bindingModifiers)
    }

    fun getBindingModifiers(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getBindingModifiersBind, segment)
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

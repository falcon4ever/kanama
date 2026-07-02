package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRInteractionProfile
 */
class OpenXRInteractionProfile(handle: MemorySegment) : Resource(handle) {
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
        ObjectCalls.ptrcallWithStringArg(setInteractionProfilePathBind, handle, interactionProfilePath)
    }

    fun getInteractionProfilePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getInteractionProfilePathBind, handle)
    }

    fun getBindingCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBindingCountBind, handle)
    }

    fun getBinding(index: Int): OpenXRIPBinding? {
        return OpenXRIPBinding.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBindingBind, handle, index))
    }

    fun setBindings(bindings: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setBindingsBind, handle, bindings)
    }

    fun getBindings(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getBindingsBind, handle)
    }

    fun getBindingModifierCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBindingModifierCountBind, handle)
    }

    fun getBindingModifier(index: Int): OpenXRIPBindingModifier? {
        return OpenXRIPBindingModifier.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBindingModifierBind, handle, index))
    }

    fun setBindingModifiers(bindingModifiers: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setBindingModifiersBind, handle, bindingModifiers)
    }

    fun getBindingModifiers(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getBindingModifiersBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRInteractionProfile? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRInteractionProfile? =
            if (handle.address() == 0L) null else OpenXRInteractionProfile(handle)

        private const val SET_INTERACTION_PROFILE_PATH_HASH = 83702148L
        private val setInteractionProfilePathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_interaction_profile_path", SET_INTERACTION_PROFILE_PATH_HASH)
        }

        private const val GET_INTERACTION_PROFILE_PATH_HASH = 201670096L
        private val getInteractionProfilePathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_interaction_profile_path", GET_INTERACTION_PROFILE_PATH_HASH)
        }

        private const val GET_BINDING_COUNT_HASH = 3905245786L
        private val getBindingCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_count", GET_BINDING_COUNT_HASH)
        }

        private const val GET_BINDING_HASH = 3934429652L
        private val getBindingBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding", GET_BINDING_HASH)
        }

        private const val SET_BINDINGS_HASH = 381264803L
        private val setBindingsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_bindings", SET_BINDINGS_HASH)
        }

        private const val GET_BINDINGS_HASH = 3995934104L
        private val getBindingsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_bindings", GET_BINDINGS_HASH)
        }

        private const val GET_BINDING_MODIFIER_COUNT_HASH = 3905245786L
        private val getBindingModifierCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_modifier_count", GET_BINDING_MODIFIER_COUNT_HASH)
        }

        private const val GET_BINDING_MODIFIER_HASH = 2419896583L
        private val getBindingModifierBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_modifier", GET_BINDING_MODIFIER_HASH)
        }

        private const val SET_BINDING_MODIFIERS_HASH = 381264803L
        private val setBindingModifiersBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_binding_modifiers", SET_BINDING_MODIFIERS_HASH)
        }

        private const val GET_BINDING_MODIFIERS_HASH = 3995934104L
        private val getBindingModifiersBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfile", "get_binding_modifiers", GET_BINDING_MODIFIERS_HASH)
        }
    }
}

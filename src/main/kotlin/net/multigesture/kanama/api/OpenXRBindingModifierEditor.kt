package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRBindingModifierEditor
 */
class OpenXRBindingModifierEditor(handle: MemorySegment) : PanelContainer(handle) {
    fun getBindingModifier(): OpenXRBindingModifier? {
        return OpenXRBindingModifier.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBindingModifierBind, handle))
    }

    fun setup(actionMap: OpenXRActionMap?, bindingModifier: OpenXRBindingModifier?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setupBind, handle, actionMap?.requireOpenHandle() ?: MemorySegment.NULL, bindingModifier?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    object Signals {
        const val bindingModifierRemoved: String = "binding_modifier_removed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRBindingModifierEditor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRBindingModifierEditor? =
            if (handle.address() == 0L) null else OpenXRBindingModifierEditor(handle)

        private const val GET_BINDING_MODIFIER_HASH = 2930765082L
        private val getBindingModifierBind by lazy {
            ObjectCalls.getMethodBind("OpenXRBindingModifierEditor", "get_binding_modifier", GET_BINDING_MODIFIER_HASH)
        }

        private const val SETUP_HASH = 1284787389L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("OpenXRBindingModifierEditor", "setup", SETUP_HASH)
        }
    }
}

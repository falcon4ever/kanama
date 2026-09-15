package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRBindingModifierEditor
 */
class OpenXRBindingModifierEditor(handle: GodotHandle) : PanelContainer(handle) {
    fun getBindingModifier(): OpenXRBindingModifier? {
        return OpenXRBindingModifier.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBindingModifierBind, segment))
    }

    fun setup(actionMap: OpenXRActionMap?, bindingModifier: OpenXRBindingModifier?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setupBind, segment, actionMap?.requireOpenHandle() ?: NULL_SEGMENT, bindingModifier?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    object Signals {
        const val bindingModifierRemoved: String = "binding_modifier_removed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRBindingModifierEditor? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRBindingModifierEditor? =
            if (handle.address() == 0L) null else OpenXRBindingModifierEditor(GodotHandle(handle))

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

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Binding modifier editor.
 *
 * Generated from Godot docs: OpenXRBindingModifierEditor
 */
class OpenXRBindingModifierEditor(handle: GodotHandle) : PanelContainer(handle) {
    /**
     * Returns the `OpenXRBindingModifier` currently being edited.
     *
     * Generated from Godot docs: OpenXRBindingModifierEditor.get_binding_modifier
     */
    fun getBindingModifier(): OpenXRBindingModifier? {
        return OpenXRBindingModifier.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getBindingModifierBind, segment))
    }

    /**
     * Setup this editor for the provided `action_map` and `binding_modifier`.
     *
     * Generated from Godot docs: OpenXRBindingModifierEditor.setup
     */
    fun setup(actionMap: OpenXRActionMap?, bindingModifier: OpenXRBindingModifier?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.setupBind, segment, actionMap?.requireOpenHandle() ?: NULL_SEGMENT, bindingModifier?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(actionMap)
        ObjectCalls.keepReachable(bindingModifier)
    }

    /** Signal `binding_modifier_removed(binding_modifier_editor: Object)`; see [TypedSignal]. */
    val bindingModifierRemoved: Signal1<GodotObject>
        @JvmName("bindingModifierRemovedTypedSignal")
        get() = Signal1(this, "binding_modifier_removed", SignalArgType.objectOf("Object") { GodotObject(it) })

    object Signals {
        const val bindingModifierRemoved: String = "binding_modifier_removed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRBindingModifierEditor? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRBindingModifierEditor? =
            if (handle.address() == 0L) null else OpenXRBindingModifierEditor(GodotHandle(handle))

        /** A new OpenXRBindingModifierEditor, GDScript's `OpenXRBindingModifierEditor.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRBindingModifierEditor =
            OpenXRBindingModifierEditor(GodotHandle(ObjectCalls.constructObject("OpenXRBindingModifierEditor")))
    }

    private object Binds {
        private const val GET_BINDING_MODIFIER_HASH = 2930765082L
        @JvmField
        val getBindingModifierBind =
            ObjectCalls.getMethodBind("OpenXRBindingModifierEditor", "get_binding_modifier", GET_BINDING_MODIFIER_HASH)

        private const val SETUP_HASH = 1284787389L
        @JvmField
        val setupBind =
            ObjectCalls.getMethodBind("OpenXRBindingModifierEditor", "setup", SETUP_HASH)
    }
}

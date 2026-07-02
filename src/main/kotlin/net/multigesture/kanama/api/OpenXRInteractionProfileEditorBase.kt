package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRInteractionProfileEditorBase
 */
open class OpenXRInteractionProfileEditorBase(handle: MemorySegment) : HBoxContainer(handle) {
    fun setup(actionMap: OpenXRActionMap?, interactionProfile: OpenXRInteractionProfile?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setupBind, handle, actionMap?.requireOpenHandle() ?: MemorySegment.NULL, interactionProfile?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRInteractionProfileEditorBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRInteractionProfileEditorBase? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditorBase(handle)

        private const val SETUP_HASH = 421962938L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileEditorBase", "setup", SETUP_HASH)
        }
    }
}

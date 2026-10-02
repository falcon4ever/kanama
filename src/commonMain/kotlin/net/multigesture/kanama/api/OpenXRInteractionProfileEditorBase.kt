package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRInteractionProfileEditorBase
 */
open class OpenXRInteractionProfileEditorBase(handle: GodotHandle) : HBoxContainer(handle) {
    fun setup(actionMap: OpenXRActionMap?, interactionProfile: OpenXRInteractionProfile?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setupBind, segment, actionMap?.requireOpenHandle() ?: NULL_SEGMENT, interactionProfile?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfileEditorBase? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRInteractionProfileEditorBase? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditorBase(GodotHandle(handle))

        private const val SETUP_HASH = 421962938L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileEditorBase", "setup", SETUP_HASH)
        }
    }
}

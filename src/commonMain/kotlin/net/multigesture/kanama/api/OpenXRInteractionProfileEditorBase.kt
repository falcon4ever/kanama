package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for editing interaction profiles.
 *
 * Generated from Godot docs: OpenXRInteractionProfileEditorBase
 */
open class OpenXRInteractionProfileEditorBase(handle: GodotHandle) : HBoxContainer(handle) {
    /**
     * Setup this editor for the provided `action_map` and `interaction_profile`.
     *
     * Generated from Godot docs: OpenXRInteractionProfileEditorBase.setup
     */
    fun setup(actionMap: OpenXRActionMap?, interactionProfile: OpenXRInteractionProfile?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.setupBind, segment, actionMap?.requireOpenHandle() ?: NULL_SEGMENT, interactionProfile?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(actionMap)
        ObjectCalls.keepReachable(interactionProfile)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfileEditorBase? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRInteractionProfileEditorBase? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditorBase(GodotHandle(handle))
    }

    private object Binds {
        private const val SETUP_HASH = 421962938L
        @JvmField
        val setupBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfileEditorBase", "setup", SETUP_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRInteractionProfileEditorBase
 */
open class OpenXRInteractionProfileEditorBase(handle: GodotHandle) : HBoxContainer(handle) {
    fun setup(actionMap: OpenXRActionMap?, interactionProfile: OpenXRInteractionProfile?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setupBind, segment, actionMap?.requireOpenHandle() ?: MemorySegment.NULL, interactionProfile?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfileEditorBase? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRInteractionProfileEditorBase? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditorBase(GodotHandle(handle))

        private const val SETUP_HASH = 421962938L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileEditorBase", "setup", SETUP_HASH)
        }
    }
}

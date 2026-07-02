package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A dialog used for confirmation of actions.
 *
 * Generated from Godot docs: ConfirmationDialog
 */
open class ConfirmationDialog(handle: MemorySegment) : AcceptDialog(handle) {
    var cancelButtonText: String
        @JvmName("cancelButtonTextProperty")
        get() = getCancelButtonText()
        @JvmName("setCancelButtonTextProperty")
        set(value) = setCancelButtonText(value)

    fun getCancelButton(): Button? {
        return Button.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCancelButtonBind, handle))
    }

    fun setCancelButtonText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setCancelButtonTextBind, handle, text)
    }

    fun getCancelButtonText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCancelButtonTextBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConfirmationDialog? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConfirmationDialog? =
            if (handle.address() == 0L) null else ConfirmationDialog(handle)

        private const val GET_CANCEL_BUTTON_HASH = 1856205918L
        private val getCancelButtonBind by lazy {
            ObjectCalls.getMethodBind("ConfirmationDialog", "get_cancel_button", GET_CANCEL_BUTTON_HASH)
        }

        private const val SET_CANCEL_BUTTON_TEXT_HASH = 83702148L
        private val setCancelButtonTextBind by lazy {
            ObjectCalls.getMethodBind("ConfirmationDialog", "set_cancel_button_text", SET_CANCEL_BUTTON_TEXT_HASH)
        }

        private const val GET_CANCEL_BUTTON_TEXT_HASH = 201670096L
        private val getCancelButtonTextBind by lazy {
            ObjectCalls.getMethodBind("ConfirmationDialog", "get_cancel_button_text", GET_CANCEL_BUTTON_TEXT_HASH)
        }
    }
}

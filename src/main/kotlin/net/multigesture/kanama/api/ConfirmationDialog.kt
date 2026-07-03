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

    /**
     * Returns the cancel button. Warning: This is a required internal node, removing and freeing it
     * may cause a crash. If you wish to hide it or any of its children, use their `CanvasItem.visible`
     * property.
     *
     * Generated from Godot docs: ConfirmationDialog.get_cancel_button
     */
    fun getCancelButton(): Button? {
        return Button.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCancelButtonBind, handle))
    }

    /**
     * The text displayed by the cancel button (see `get_cancel_button`).
     *
     * Generated from Godot docs: ConfirmationDialog.set_cancel_button_text
     */
    fun setCancelButtonText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setCancelButtonTextBind, handle, text)
    }

    /**
     * The text displayed by the cancel button (see `get_cancel_button`).
     *
     * Generated from Godot docs: ConfirmationDialog.get_cancel_button_text
     */
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

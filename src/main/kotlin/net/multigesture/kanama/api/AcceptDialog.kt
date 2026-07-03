package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A base dialog used for user notification.
 *
 * Generated from Godot docs: AcceptDialog
 */
open class AcceptDialog(handle: MemorySegment) : Window(handle) {
    var okButtonText: String
        @JvmName("okButtonTextProperty")
        get() = getOkButtonText()
        @JvmName("setOkButtonTextProperty")
        set(value) = setOkButtonText(value)

    var dialogText: String
        @JvmName("dialogTextProperty")
        get() = getText()
        @JvmName("setDialogTextProperty")
        set(value) = setText(value)

    var dialogHideOnOk: Boolean
        @JvmName("dialogHideOnOkProperty")
        get() = getHideOnOk()
        @JvmName("setDialogHideOnOkProperty")
        set(value) = setHideOnOk(value)

    var dialogCloseOnEscape: Boolean
        @JvmName("dialogCloseOnEscapeProperty")
        get() = getCloseOnEscape()
        @JvmName("setDialogCloseOnEscapeProperty")
        set(value) = setCloseOnEscape(value)

    var dialogAutowrap: Boolean
        @JvmName("dialogAutowrapProperty")
        get() = hasAutowrap()
        @JvmName("setDialogAutowrapProperty")
        set(value) = setAutowrap(value)

    /**
     * Returns the OK `Button` instance. Warning: This is a required internal node, removing and
     * freeing it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: AcceptDialog.get_ok_button
     */
    fun getOkButton(): Button? {
        return Button.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOkButtonBind, handle))
    }

    /**
     * Returns the label used for built-in text. Warning: This is a required internal node, removing
     * and freeing it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: AcceptDialog.get_label
     */
    fun getLabel(): Label? {
        return Label.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLabelBind, handle))
    }

    /**
     * If `true`, the dialog is hidden when the OK button is pressed. You can set it to `false` if you
     * want to do e.g. input validation when receiving the `confirmed` signal, and handle hiding the
     * dialog in your own logic. Note: Some nodes derived from this class can have a different default
     * value, and potentially their own built-in logic overriding this setting. For example
     * `FileDialog` defaults to `false`, and has its own input validation code that is called when you
     * press OK, which eventually hides the dialog if the input is valid. As such, this property can't
     * be used in `FileDialog` to disable hiding the dialog when pressing OK.
     *
     * Generated from Godot docs: AcceptDialog.set_hide_on_ok
     */
    fun setHideOnOk(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnOkBind, handle, enabled)
    }

    /**
     * If `true`, the dialog is hidden when the OK button is pressed. You can set it to `false` if you
     * want to do e.g. input validation when receiving the `confirmed` signal, and handle hiding the
     * dialog in your own logic. Note: Some nodes derived from this class can have a different default
     * value, and potentially their own built-in logic overriding this setting. For example
     * `FileDialog` defaults to `false`, and has its own input validation code that is called when you
     * press OK, which eventually hides the dialog if the input is valid. As such, this property can't
     * be used in `FileDialog` to disable hiding the dialog when pressing OK.
     *
     * Generated from Godot docs: AcceptDialog.get_hide_on_ok
     */
    fun getHideOnOk(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getHideOnOkBind, handle)
    }

    /**
     * If `true`, the dialog will be hidden when the `ui_close_dialog` action is pressed (by default,
     * this action is bound to Escape, or Cmd + W on macOS).
     *
     * Generated from Godot docs: AcceptDialog.set_close_on_escape
     */
    fun setCloseOnEscape(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCloseOnEscapeBind, handle, enabled)
    }

    /**
     * If `true`, the dialog will be hidden when the `ui_close_dialog` action is pressed (by default,
     * this action is bound to Escape, or Cmd + W on macOS).
     *
     * Generated from Godot docs: AcceptDialog.get_close_on_escape
     */
    fun getCloseOnEscape(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCloseOnEscapeBind, handle)
    }

    /**
     * Adds a button with label `text` and a custom `action` to the dialog and returns the created
     * button. If `action` is not empty, pressing the button will emit the `custom_action` signal with
     * the specified action string. If `true`, `right` will place the button to the right of any
     * sibling buttons. You can use `remove_button` method to remove a button created with this method
     * from the dialog.
     *
     * Generated from Godot docs: AcceptDialog.add_button
     */
    fun addButton(text: String, right: Boolean = false, action: String = ""): Button? {
        return Button.wrap(ObjectCalls.ptrcallWithStringBoolStringArgsRetObject(addButtonBind, handle, text, right, action))
    }

    /**
     * Adds a button with label `name` and a cancel action to the dialog and returns the created
     * button. You can use `remove_button` method to remove a button created with this method from the
     * dialog.
     *
     * Generated from Godot docs: AcceptDialog.add_cancel_button
     */
    fun addCancelButton(name: String): Button? {
        return Button.wrap(ObjectCalls.ptrcallWithStringArgRetObject(addCancelButtonBind, handle, name))
    }

    /**
     * Removes the `button` from the dialog. Does NOT free the `button`. The `button` must be a
     * `Button` added with `add_button` or `add_cancel_button` method. After removal, pressing the
     * `button` will no longer emit this dialog's `custom_action` or `canceled` signals.
     *
     * Generated from Godot docs: AcceptDialog.remove_button
     */
    fun removeButton(button: Button) {
        ObjectCalls.ptrcallWithObjectArgs(removeButtonBind, handle, listOf(button.handle))
    }

    /**
     * Registers a `LineEdit` in the dialog. When the enter key is pressed, the dialog will be
     * accepted.
     *
     * Generated from Godot docs: AcceptDialog.register_text_enter
     */
    fun registerTextEnter(lineEdit: LineEdit) {
        ObjectCalls.ptrcallWithObjectArgs(registerTextEnterBind, handle, listOf(lineEdit.handle))
    }

    /**
     * The text displayed by the dialog.
     *
     * Generated from Godot docs: AcceptDialog.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    /**
     * The text displayed by the dialog.
     *
     * Generated from Godot docs: AcceptDialog.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    /**
     * Sets autowrapping for the text in the dialog.
     *
     * Generated from Godot docs: AcceptDialog.set_autowrap
     */
    fun setAutowrap(autowrap: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutowrapBind, handle, autowrap)
    }

    /**
     * Sets autowrapping for the text in the dialog.
     *
     * Generated from Godot docs: AcceptDialog.has_autowrap
     */
    fun hasAutowrap(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutowrapBind, handle)
    }

    /**
     * The text displayed by the OK button (see `get_ok_button`). If empty, a default text will be
     * used.
     *
     * Generated from Godot docs: AcceptDialog.set_ok_button_text
     */
    fun setOkButtonText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setOkButtonTextBind, handle, text)
    }

    /**
     * The text displayed by the OK button (see `get_ok_button`). If empty, a default text will be
     * used.
     *
     * Generated from Godot docs: AcceptDialog.get_ok_button_text
     */
    fun getOkButtonText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOkButtonTextBind, handle)
    }

    object Signals {
        const val confirmed: String = "confirmed"
        const val canceled: String = "canceled"
        const val customAction: String = "custom_action"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AcceptDialog? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AcceptDialog? =
            if (handle.address() == 0L) null else AcceptDialog(handle)

        private const val GET_OK_BUTTON_HASH = 1856205918L
        private val getOkButtonBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "get_ok_button", GET_OK_BUTTON_HASH)
        }

        private const val GET_LABEL_HASH = 566733104L
        private val getLabelBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "get_label", GET_LABEL_HASH)
        }

        private const val SET_HIDE_ON_OK_HASH = 2586408642L
        private val setHideOnOkBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "set_hide_on_ok", SET_HIDE_ON_OK_HASH)
        }

        private const val GET_HIDE_ON_OK_HASH = 36873697L
        private val getHideOnOkBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "get_hide_on_ok", GET_HIDE_ON_OK_HASH)
        }

        private const val SET_CLOSE_ON_ESCAPE_HASH = 2586408642L
        private val setCloseOnEscapeBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "set_close_on_escape", SET_CLOSE_ON_ESCAPE_HASH)
        }

        private const val GET_CLOSE_ON_ESCAPE_HASH = 36873697L
        private val getCloseOnEscapeBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "get_close_on_escape", GET_CLOSE_ON_ESCAPE_HASH)
        }

        private const val ADD_BUTTON_HASH = 3328440682L
        private val addButtonBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "add_button", ADD_BUTTON_HASH)
        }

        private const val ADD_CANCEL_BUTTON_HASH = 242045556L
        private val addCancelButtonBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "add_cancel_button", ADD_CANCEL_BUTTON_HASH)
        }

        private const val REMOVE_BUTTON_HASH = 2068354942L
        private val removeButtonBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "remove_button", REMOVE_BUTTON_HASH)
        }

        private const val REGISTER_TEXT_ENTER_HASH = 3714008017L
        private val registerTextEnterBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "register_text_enter", REGISTER_TEXT_ENTER_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "get_text", GET_TEXT_HASH)
        }

        private const val SET_AUTOWRAP_HASH = 2586408642L
        private val setAutowrapBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "set_autowrap", SET_AUTOWRAP_HASH)
        }

        private const val HAS_AUTOWRAP_HASH = 2240911060L
        private val hasAutowrapBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "has_autowrap", HAS_AUTOWRAP_HASH)
        }

        private const val SET_OK_BUTTON_TEXT_HASH = 83702148L
        private val setOkButtonTextBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "set_ok_button_text", SET_OK_BUTTON_TEXT_HASH)
        }

        private const val GET_OK_BUTTON_TEXT_HASH = 201670096L
        private val getOkButtonTextBind by lazy {
            ObjectCalls.getMethodBind("AcceptDialog", "get_ok_button_text", GET_OK_BUTTON_TEXT_HASH)
        }
    }
}

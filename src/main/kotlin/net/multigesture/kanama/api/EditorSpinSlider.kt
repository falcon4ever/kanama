package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's control for editing numeric values.
 *
 * Generated from Godot docs: EditorSpinSlider
 */
class EditorSpinSlider(handle: MemorySegment) : Range(handle) {
    var label: String
        @JvmName("labelProperty")
        get() = getLabel()
        @JvmName("setLabelProperty")
        set(value) = setLabel(value)

    var suffix: String
        @JvmName("suffixProperty")
        get() = getSuffix()
        @JvmName("setSuffixProperty")
        set(value) = setSuffix(value)

    var readOnly: Boolean
        @JvmName("readOnlyProperty")
        get() = isReadOnly()
        @JvmName("setReadOnlyProperty")
        set(value) = setReadOnly(value)

    var flat: Boolean
        @JvmName("flatProperty")
        get() = isFlat()
        @JvmName("setFlatProperty")
        set(value) = setFlat(value)

    var controlState: Long
        @JvmName("controlStateProperty")
        get() = getControlState()
        @JvmName("setControlStateProperty")
        set(value) = setControlState(value)

    var hideSlider: Boolean
        @JvmName("hideSliderProperty")
        get() = isHidingSlider()
        @JvmName("setHideSliderProperty")
        set(value) = setHideSlider(value)

    var editingInteger: Boolean
        @JvmName("editingIntegerProperty")
        get() = isEditingInteger()
        @JvmName("setEditingIntegerProperty")
        set(value) = setEditingInteger(value)

    var deferredDragMode: Boolean
        @JvmName("deferredDragModeProperty")
        get() = isDeferredDragModeEnabled()
        @JvmName("setDeferredDragModeProperty")
        set(value) = setDeferredDragModeEnabled(value)

    /**
     * The text that displays to the left of the value.
     *
     * Generated from Godot docs: EditorSpinSlider.set_label
     */
    fun setLabel(label: String) {
        ObjectCalls.ptrcallWithStringArg(setLabelBind, handle, label)
    }

    /**
     * The text that displays to the left of the value.
     *
     * Generated from Godot docs: EditorSpinSlider.get_label
     */
    fun getLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLabelBind, handle)
    }

    /**
     * The suffix to display after the value (in a faded color). This should generally be a plural
     * word. You may have to use an abbreviation if the suffix is too long to be displayed.
     *
     * Generated from Godot docs: EditorSpinSlider.set_suffix
     */
    fun setSuffix(suffix: String) {
        ObjectCalls.ptrcallWithStringArg(setSuffixBind, handle, suffix)
    }

    /**
     * The suffix to display after the value (in a faded color). This should generally be a plural
     * word. You may have to use an abbreviation if the suffix is too long to be displayed.
     *
     * Generated from Godot docs: EditorSpinSlider.get_suffix
     */
    fun getSuffix(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSuffixBind, handle)
    }

    /**
     * If `true`, the slider can't be interacted with.
     *
     * Generated from Godot docs: EditorSpinSlider.set_read_only
     */
    fun setReadOnly(readOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setReadOnlyBind, handle, readOnly)
    }

    /**
     * If `true`, the slider can't be interacted with.
     *
     * Generated from Godot docs: EditorSpinSlider.is_read_only
     */
    fun isReadOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReadOnlyBind, handle)
    }

    /**
     * If `true`, the slider will not draw background.
     *
     * Generated from Godot docs: EditorSpinSlider.set_flat
     */
    fun setFlat(flat: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlatBind, handle, flat)
    }

    /**
     * If `true`, the slider will not draw background.
     *
     * Generated from Godot docs: EditorSpinSlider.is_flat
     */
    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlatBind, handle)
    }

    /**
     * The state in which the control used to manipulate the value will be.
     *
     * Generated from Godot docs: EditorSpinSlider.set_control_state
     */
    fun setControlState(state: Long) {
        ObjectCalls.ptrcallWithLongArg(setControlStateBind, handle, state)
    }

    /**
     * The state in which the control used to manipulate the value will be.
     *
     * Generated from Godot docs: EditorSpinSlider.get_control_state
     */
    fun getControlState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getControlStateBind, handle)
    }

    /**
     * If `true`, the slider and up/down arrows are hidden.
     *
     * Generated from Godot docs: EditorSpinSlider.set_hide_slider
     */
    fun setHideSlider(hideSlider: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideSliderBind, handle, hideSlider)
    }

    /**
     * If `true`, the slider and up/down arrows are hidden.
     *
     * Generated from Godot docs: EditorSpinSlider.is_hiding_slider
     */
    fun isHidingSlider(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHidingSliderBind, handle)
    }

    /**
     * If `true`, the `EditorSpinSlider` is considered to be editing an integer value. If `false`, the
     * `EditorSpinSlider` is considered to be editing a floating-point value. This is used to determine
     * whether a slider should be drawn by default. The slider is only drawn for floats; integers use
     * up-down arrows similar to `SpinBox` instead, unless `control_state` is set to
     * `CONTROL_STATE_PREFER_SLIDER`. It will also use
     * `EditorSettings.interface/inspector/integer_drag_speed` instead of
     * `EditorSettings.interface/inspector/float_drag_speed` if the slider is available.
     *
     * Generated from Godot docs: EditorSpinSlider.set_editing_integer
     */
    fun setEditingInteger(editingInteger: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditingIntegerBind, handle, editingInteger)
    }

    /**
     * If `true`, the `EditorSpinSlider` is considered to be editing an integer value. If `false`, the
     * `EditorSpinSlider` is considered to be editing a floating-point value. This is used to determine
     * whether a slider should be drawn by default. The slider is only drawn for floats; integers use
     * up-down arrows similar to `SpinBox` instead, unless `control_state` is set to
     * `CONTROL_STATE_PREFER_SLIDER`. It will also use
     * `EditorSettings.interface/inspector/integer_drag_speed` instead of
     * `EditorSettings.interface/inspector/float_drag_speed` if the slider is available.
     *
     * Generated from Godot docs: EditorSpinSlider.is_editing_integer
     */
    fun isEditingInteger(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditingIntegerBind, handle)
    }

    /**
     * If `true`, changing via dragging is applied only at the end of the input (for example, when the
     * user releases a mouse button).
     *
     * Generated from Godot docs: EditorSpinSlider.set_deferred_drag_mode_enabled
     */
    fun setDeferredDragModeEnabled(enabled: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(setDeferredDragModeEnabledBind, handle, enabled)
    }

    /**
     * If `true`, changing via dragging is applied only at the end of the input (for example, when the
     * user releases a mouse button).
     *
     * Generated from Godot docs: EditorSpinSlider.is_deferred_drag_mode_enabled
     */
    fun isDeferredDragModeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeferredDragModeEnabledBind, handle)
    }

    object Signals {
        const val grabbed: String = "grabbed"
        const val ungrabbed: String = "ungrabbed"
        const val updownPressed: String = "updown_pressed"
        const val valueFocusEntered: String = "value_focus_entered"
        const val valueFocusExited: String = "value_focus_exited"
    }

    companion object {
        const val CONTROL_STATE_DEFAULT: Long = 0L
        const val CONTROL_STATE_PREFER_SLIDER: Long = 1L
        const val CONTROL_STATE_HIDE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSpinSlider? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSpinSlider? =
            if (handle.address() == 0L) null else EditorSpinSlider(handle)

        private const val SET_LABEL_HASH = 83702148L
        private val setLabelBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_label", SET_LABEL_HASH)
        }

        private const val GET_LABEL_HASH = 201670096L
        private val getLabelBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "get_label", GET_LABEL_HASH)
        }

        private const val SET_SUFFIX_HASH = 83702148L
        private val setSuffixBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_suffix", SET_SUFFIX_HASH)
        }

        private const val GET_SUFFIX_HASH = 201670096L
        private val getSuffixBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "get_suffix", GET_SUFFIX_HASH)
        }

        private const val SET_READ_ONLY_HASH = 2586408642L
        private val setReadOnlyBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_read_only", SET_READ_ONLY_HASH)
        }

        private const val IS_READ_ONLY_HASH = 36873697L
        private val isReadOnlyBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_read_only", IS_READ_ONLY_HASH)
        }

        private const val SET_FLAT_HASH = 2586408642L
        private val setFlatBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_flat", SET_FLAT_HASH)
        }

        private const val IS_FLAT_HASH = 36873697L
        private val isFlatBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_flat", IS_FLAT_HASH)
        }

        private const val SET_CONTROL_STATE_HASH = 1324557109L
        private val setControlStateBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_control_state", SET_CONTROL_STATE_HASH)
        }

        private const val GET_CONTROL_STATE_HASH = 3406006200L
        private val getControlStateBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "get_control_state", GET_CONTROL_STATE_HASH)
        }

        private const val SET_HIDE_SLIDER_HASH = 2586408642L
        private val setHideSliderBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_hide_slider", SET_HIDE_SLIDER_HASH)
        }

        private const val IS_HIDING_SLIDER_HASH = 36873697L
        private val isHidingSliderBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_hiding_slider", IS_HIDING_SLIDER_HASH)
        }

        private const val SET_EDITING_INTEGER_HASH = 2586408642L
        private val setEditingIntegerBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_editing_integer", SET_EDITING_INTEGER_HASH)
        }

        private const val IS_EDITING_INTEGER_HASH = 36873697L
        private val isEditingIntegerBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_editing_integer", IS_EDITING_INTEGER_HASH)
        }

        private const val SET_DEFERRED_DRAG_MODE_ENABLED_HASH = 3216645846L
        private val setDeferredDragModeEnabledBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_deferred_drag_mode_enabled", SET_DEFERRED_DRAG_MODE_ENABLED_HASH)
        }

        private const val IS_DEFERRED_DRAG_MODE_ENABLED_HASH = 36873697L
        private val isDeferredDragModeEnabledBind by lazy {
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_deferred_drag_mode_enabled", IS_DEFERRED_DRAG_MODE_ENABLED_HASH)
        }
    }
}

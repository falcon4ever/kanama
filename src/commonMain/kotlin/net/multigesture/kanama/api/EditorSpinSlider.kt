package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Godot editor's control for editing numeric values.
 *
 * Generated from Godot docs: EditorSpinSlider
 */
class EditorSpinSlider(handle: GodotHandle) : Range(handle) {
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

    var controlState: EditorSpinSlider.ControlState
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
        ObjectCalls.ptrcallWithStringArg(Binds.setLabelBind, segment, label)
    }

    /**
     * The text that displays to the left of the value.
     *
     * Generated from Godot docs: EditorSpinSlider.get_label
     */
    fun getLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLabelBind, segment)
    }

    /**
     * The suffix to display after the value (in a faded color). This should generally be a plural
     * word. You may have to use an abbreviation if the suffix is too long to be displayed.
     *
     * Generated from Godot docs: EditorSpinSlider.set_suffix
     */
    fun setSuffix(suffix: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setSuffixBind, segment, suffix)
    }

    /**
     * The suffix to display after the value (in a faded color). This should generally be a plural
     * word. You may have to use an abbreviation if the suffix is too long to be displayed.
     *
     * Generated from Godot docs: EditorSpinSlider.get_suffix
     */
    fun getSuffix(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSuffixBind, segment)
    }

    /**
     * If `true`, the slider can't be interacted with.
     *
     * Generated from Godot docs: EditorSpinSlider.set_read_only
     */
    fun setReadOnly(readOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setReadOnlyBind, segment, readOnly)
    }

    /**
     * If `true`, the slider can't be interacted with.
     *
     * Generated from Godot docs: EditorSpinSlider.is_read_only
     */
    fun isReadOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isReadOnlyBind, segment)
    }

    /**
     * If `true`, the slider will not draw background.
     *
     * Generated from Godot docs: EditorSpinSlider.set_flat
     */
    fun setFlat(flat: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlatBind, segment, flat)
    }

    /**
     * If `true`, the slider will not draw background.
     *
     * Generated from Godot docs: EditorSpinSlider.is_flat
     */
    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFlatBind, segment)
    }

    /**
     * The state in which the control used to manipulate the value will be.
     *
     * Generated from Godot docs: EditorSpinSlider.set_control_state
     */
    fun setControlState(state: EditorSpinSlider.ControlState) {
        ObjectCalls.ptrcallWithLongArg(Binds.setControlStateBind, segment, state.value)
    }

    /**
     * The state in which the control used to manipulate the value will be.
     *
     * Generated from Godot docs: EditorSpinSlider.get_control_state
     */
    fun getControlState(): EditorSpinSlider.ControlState {
        return EditorSpinSlider.ControlState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getControlStateBind, segment))
    }

    /**
     * If `true`, the slider and up/down arrows are hidden.
     *
     * Generated from Godot docs: EditorSpinSlider.set_hide_slider
     */
    fun setHideSlider(hideSlider: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setHideSliderBind, segment, hideSlider)
    }

    /**
     * If `true`, the slider and up/down arrows are hidden.
     *
     * Generated from Godot docs: EditorSpinSlider.is_hiding_slider
     */
    fun isHidingSlider(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isHidingSliderBind, segment)
    }

    /**
     * If `true`, the `EditorSpinSlider` is considered to be editing an integer value. If `false`, the
     * `EditorSpinSlider` is considered to be editing a floating-point value. This is used to determine
     * whether a slider should be drawn by default. The slider is only drawn for floats; integers use
     * up-down arrows similar to `SpinBox` instead, unless `control_state` is set to
     * `ControlState.PREFER_SLIDER`. It will also use
     * `EditorSettings.interface/inspector/integer_drag_speed` instead of
     * `EditorSettings.interface/inspector/float_drag_speed` if the slider is available.
     *
     * Generated from Godot docs: EditorSpinSlider.set_editing_integer
     */
    fun setEditingInteger(editingInteger: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditingIntegerBind, segment, editingInteger)
    }

    /**
     * If `true`, the `EditorSpinSlider` is considered to be editing an integer value. If `false`, the
     * `EditorSpinSlider` is considered to be editing a floating-point value. This is used to determine
     * whether a slider should be drawn by default. The slider is only drawn for floats; integers use
     * up-down arrows similar to `SpinBox` instead, unless `control_state` is set to
     * `ControlState.PREFER_SLIDER`. It will also use
     * `EditorSettings.interface/inspector/integer_drag_speed` instead of
     * `EditorSettings.interface/inspector/float_drag_speed` if the slider is available.
     *
     * Generated from Godot docs: EditorSpinSlider.is_editing_integer
     */
    fun isEditingInteger(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditingIntegerBind, segment)
    }

    /**
     * If `true`, changing via dragging is applied only at the end of the input (for example, when the
     * user releases a mouse button).
     *
     * Generated from Godot docs: EditorSpinSlider.set_deferred_drag_mode_enabled
     */
    fun setDeferredDragModeEnabled(enabled: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeferredDragModeEnabledBind, segment, enabled)
    }

    /**
     * If `true`, changing via dragging is applied only at the end of the input (for example, when the
     * user releases a mouse button).
     *
     * Generated from Godot docs: EditorSpinSlider.is_deferred_drag_mode_enabled
     */
    fun isDeferredDragModeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDeferredDragModeEnabledBind, segment)
    }

    /** Signal `grabbed()`; see [TypedSignal]. */
    val grabbed: Signal0
        @JvmName("grabbedTypedSignal")
        get() = Signal0(this, "grabbed")

    /** Signal `ungrabbed()`; see [TypedSignal]. */
    val ungrabbed: Signal0
        @JvmName("ungrabbedTypedSignal")
        get() = Signal0(this, "ungrabbed")

    /** Signal `updown_pressed()`; see [TypedSignal]. */
    val updownPressed: Signal0
        @JvmName("updownPressedTypedSignal")
        get() = Signal0(this, "updown_pressed")

    /** Signal `value_focus_entered()`; see [TypedSignal]. */
    val valueFocusEntered: Signal0
        @JvmName("valueFocusEnteredTypedSignal")
        get() = Signal0(this, "value_focus_entered")

    /** Signal `value_focus_exited()`; see [TypedSignal]. */
    val valueFocusExited: Signal0
        @JvmName("valueFocusExitedTypedSignal")
        get() = Signal0(this, "value_focus_exited")

    object Signals {
        const val grabbed: String = "grabbed"
        const val ungrabbed: String = "ungrabbed"
        const val updownPressed: String = "updown_pressed"
        const val valueFocusEntered: String = "value_focus_entered"
        const val valueFocusExited: String = "value_focus_exited"
    }

    /**
     * Godot's `EditorSpinSlider.ControlState` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`EditorSpinSlider.ControlState.<NAME>`).
     *
     * Generated from Godot docs: EditorSpinSlider.ControlState
     */
    @JvmInline
    value class ControlState(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The type of control used will depend on the value of `editing_integer`. Up-down arrows if
             * `true`, a slider if `false`.
             *
             * Generated from Godot docs: EditorSpinSlider.CONTROL_STATE_DEFAULT
             */
            val DEFAULT: ControlState get() = ControlState(0L)
            /**
             * A slider will always be used, even if `editing_integer` is enabled.
             *
             * Generated from Godot docs: EditorSpinSlider.CONTROL_STATE_PREFER_SLIDER
             */
            val PREFER_SLIDER: ControlState get() = ControlState(1L)
            /**
             * Neither the up-down arrows nor the slider will be shown.
             *
             * Generated from Godot docs: EditorSpinSlider.CONTROL_STATE_HIDE
             */
            val HIDE: ControlState get() = ControlState(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSpinSlider? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorSpinSlider? =
            if (handle.address() == 0L) null else EditorSpinSlider(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_LABEL_HASH = 83702148L
        @JvmField
        val setLabelBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_label", SET_LABEL_HASH)

        private const val GET_LABEL_HASH = 201670096L
        @JvmField
        val getLabelBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "get_label", GET_LABEL_HASH)

        private const val SET_SUFFIX_HASH = 83702148L
        @JvmField
        val setSuffixBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_suffix", SET_SUFFIX_HASH)

        private const val GET_SUFFIX_HASH = 201670096L
        @JvmField
        val getSuffixBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "get_suffix", GET_SUFFIX_HASH)

        private const val SET_READ_ONLY_HASH = 2586408642L
        @JvmField
        val setReadOnlyBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_read_only", SET_READ_ONLY_HASH)

        private const val IS_READ_ONLY_HASH = 36873697L
        @JvmField
        val isReadOnlyBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_read_only", IS_READ_ONLY_HASH)

        private const val SET_FLAT_HASH = 2586408642L
        @JvmField
        val setFlatBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_flat", SET_FLAT_HASH)

        private const val IS_FLAT_HASH = 36873697L
        @JvmField
        val isFlatBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_flat", IS_FLAT_HASH)

        private const val SET_CONTROL_STATE_HASH = 1324557109L
        @JvmField
        val setControlStateBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_control_state", SET_CONTROL_STATE_HASH)

        private const val GET_CONTROL_STATE_HASH = 3406006200L
        @JvmField
        val getControlStateBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "get_control_state", GET_CONTROL_STATE_HASH)

        private const val SET_HIDE_SLIDER_HASH = 2586408642L
        @JvmField
        val setHideSliderBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_hide_slider", SET_HIDE_SLIDER_HASH)

        private const val IS_HIDING_SLIDER_HASH = 36873697L
        @JvmField
        val isHidingSliderBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_hiding_slider", IS_HIDING_SLIDER_HASH)

        private const val SET_EDITING_INTEGER_HASH = 2586408642L
        @JvmField
        val setEditingIntegerBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_editing_integer", SET_EDITING_INTEGER_HASH)

        private const val IS_EDITING_INTEGER_HASH = 36873697L
        @JvmField
        val isEditingIntegerBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_editing_integer", IS_EDITING_INTEGER_HASH)

        private const val SET_DEFERRED_DRAG_MODE_ENABLED_HASH = 3216645846L
        @JvmField
        val setDeferredDragModeEnabledBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "set_deferred_drag_mode_enabled", SET_DEFERRED_DRAG_MODE_ENABLED_HASH)

        private const val IS_DEFERRED_DRAG_MODE_ENABLED_HASH = 36873697L
        @JvmField
        val isDeferredDragModeEnabledBind =
            ObjectCalls.getMethodBind("EditorSpinSlider", "is_deferred_drag_mode_enabled", IS_DEFERRED_DRAG_MODE_ENABLED_HASH)
    }
}

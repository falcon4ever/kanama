package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A button that brings up a `ColorPicker` when pressed.
 *
 * Generated from Godot docs: ColorPickerButton
 */
class ColorPickerButton(handle: MemorySegment) : Button(handle) {
    var color: Color
        @JvmName("colorProperty")
        get() = getPickColor()
        @JvmName("setColorProperty")
        set(value) = setPickColor(value)

    var editAlpha: Boolean
        @JvmName("editAlphaProperty")
        get() = isEditingAlpha()
        @JvmName("setEditAlphaProperty")
        set(value) = setEditAlpha(value)

    var editIntensity: Boolean
        @JvmName("editIntensityProperty")
        get() = isEditingIntensity()
        @JvmName("setEditIntensityProperty")
        set(value) = setEditIntensity(value)

    fun setPickColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setPickColorBind, handle, color)
    }

    fun getPickColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getPickColorBind, handle)
    }

    fun getPicker(): ColorPicker? {
        return ColorPicker.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPickerBind, handle))
    }

    fun getPopup(): PopupPanel? {
        return PopupPanel.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPopupBind, handle))
    }

    fun setEditAlpha(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditAlphaBind, handle, show)
    }

    fun isEditingAlpha(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditingAlphaBind, handle)
    }

    fun setEditIntensity(show: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditIntensityBind, handle, show)
    }

    fun isEditingIntensity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditingIntensityBind, handle)
    }

    object Signals {
        const val colorChanged: String = "color_changed"
        const val popupClosed: String = "popup_closed"
        const val pickerCreated: String = "picker_created"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ColorPickerButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ColorPickerButton? =
            if (handle.address() == 0L) null else ColorPickerButton(handle)

        private const val SET_PICK_COLOR_HASH = 2920490490L
        private val setPickColorBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "set_pick_color", SET_PICK_COLOR_HASH)
        }

        private const val GET_PICK_COLOR_HASH = 3444240500L
        private val getPickColorBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "get_pick_color", GET_PICK_COLOR_HASH)
        }

        private const val GET_PICKER_HASH = 331835996L
        private val getPickerBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "get_picker", GET_PICKER_HASH)
        }

        private const val GET_POPUP_HASH = 1322440207L
        private val getPopupBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "get_popup", GET_POPUP_HASH)
        }

        private const val SET_EDIT_ALPHA_HASH = 2586408642L
        private val setEditAlphaBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "set_edit_alpha", SET_EDIT_ALPHA_HASH)
        }

        private const val IS_EDITING_ALPHA_HASH = 36873697L
        private val isEditingAlphaBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "is_editing_alpha", IS_EDITING_ALPHA_HASH)
        }

        private const val SET_EDIT_INTENSITY_HASH = 2586408642L
        private val setEditIntensityBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "set_edit_intensity", SET_EDIT_INTENSITY_HASH)
        }

        private const val IS_EDITING_INTENSITY_HASH = 36873697L
        private val isEditingIntensityBind by lazy {
            ObjectCalls.getMethodBind("ColorPickerButton", "is_editing_intensity", IS_EDITING_INTENSITY_HASH)
        }
    }
}

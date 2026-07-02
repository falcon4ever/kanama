package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A widget that provides an interface for selecting or modifying a color.
 *
 * Generated from Godot docs: ColorPicker
 */
class ColorPicker(handle: MemorySegment) : VBoxContainer(handle) {
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

    var colorMode: Long
        @JvmName("colorModeProperty")
        get() = getColorMode()
        @JvmName("setColorModeProperty")
        set(value) = setColorMode(value)

    var deferredMode: Boolean
        @JvmName("deferredModeProperty")
        get() = isDeferredMode()
        @JvmName("setDeferredModeProperty")
        set(value) = setDeferredMode(value)

    var pickerShape: Long
        @JvmName("pickerShapeProperty")
        get() = getPickerShape()
        @JvmName("setPickerShapeProperty")
        set(value) = setPickerShape(value)

    var canAddSwatches: Boolean
        @JvmName("canAddSwatchesProperty")
        get() = areSwatchesEnabled()
        @JvmName("setCanAddSwatchesProperty")
        set(value) = setCanAddSwatches(value)

    var samplerVisible: Boolean
        @JvmName("samplerVisibleProperty")
        get() = isSamplerVisible()
        @JvmName("setSamplerVisibleProperty")
        set(value) = setSamplerVisible(value)

    var colorModesVisible: Boolean
        @JvmName("colorModesVisibleProperty")
        get() = areModesVisible()
        @JvmName("setColorModesVisibleProperty")
        set(value) = setModesVisible(value)

    var slidersVisible: Boolean
        @JvmName("slidersVisibleProperty")
        get() = areSlidersVisible()
        @JvmName("setSlidersVisibleProperty")
        set(value) = setSlidersVisible(value)

    var hexVisible: Boolean
        @JvmName("hexVisibleProperty")
        get() = isHexVisible()
        @JvmName("setHexVisibleProperty")
        set(value) = setHexVisible(value)

    var presetsVisible: Boolean
        @JvmName("presetsVisibleProperty")
        get() = arePresetsVisible()
        @JvmName("setPresetsVisibleProperty")
        set(value) = setPresetsVisible(value)

    fun setPickColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setPickColorBind, handle, color)
    }

    fun getPickColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getPickColorBind, handle)
    }

    fun setDeferredMode(mode: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeferredModeBind, handle, mode)
    }

    fun isDeferredMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeferredModeBind, handle)
    }

    fun setColorMode(colorMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setColorModeBind, handle, colorMode)
    }

    fun getColorMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getColorModeBind, handle)
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

    fun setCanAddSwatches(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCanAddSwatchesBind, handle, enabled)
    }

    fun areSwatchesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areSwatchesEnabledBind, handle)
    }

    fun setPresetsVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPresetsVisibleBind, handle, visible)
    }

    fun arePresetsVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(arePresetsVisibleBind, handle)
    }

    fun setModesVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setModesVisibleBind, handle, visible)
    }

    fun areModesVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areModesVisibleBind, handle)
    }

    fun setSamplerVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSamplerVisibleBind, handle, visible)
    }

    fun isSamplerVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSamplerVisibleBind, handle)
    }

    fun setSlidersVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSlidersVisibleBind, handle, visible)
    }

    fun areSlidersVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areSlidersVisibleBind, handle)
    }

    fun setHexVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHexVisibleBind, handle, visible)
    }

    fun isHexVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHexVisibleBind, handle)
    }

    fun addPreset(color: Color) {
        ObjectCalls.ptrcallWithColorArg(addPresetBind, handle, color)
    }

    fun erasePreset(color: Color) {
        ObjectCalls.ptrcallWithColorArg(erasePresetBind, handle, color)
    }

    fun getPresets(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getPresetsBind, handle)
    }

    fun addRecentPreset(color: Color) {
        ObjectCalls.ptrcallWithColorArg(addRecentPresetBind, handle, color)
    }

    fun eraseRecentPreset(color: Color) {
        ObjectCalls.ptrcallWithColorArg(eraseRecentPresetBind, handle, color)
    }

    fun getRecentPresets(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getRecentPresetsBind, handle)
    }

    fun setPickerShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setPickerShapeBind, handle, shape)
    }

    fun getPickerShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPickerShapeBind, handle)
    }

    object Signals {
        const val colorChanged: String = "color_changed"
        const val presetAdded: String = "preset_added"
        const val presetRemoved: String = "preset_removed"
    }

    companion object {
        const val MODE_RGB: Long = 0L
        const val MODE_HSV: Long = 1L
        const val MODE_RAW: Long = 2L
        const val MODE_LINEAR: Long = 2L
        const val MODE_OKHSL: Long = 3L
        const val SHAPE_HSV_RECTANGLE: Long = 0L
        const val SHAPE_HSV_WHEEL: Long = 1L
        const val SHAPE_VHS_CIRCLE: Long = 2L
        const val SHAPE_OKHSL_CIRCLE: Long = 3L
        const val SHAPE_NONE: Long = 4L
        const val SHAPE_OK_HS_RECTANGLE: Long = 5L
        const val SHAPE_OK_HL_RECTANGLE: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ColorPicker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ColorPicker? =
            if (handle.address() == 0L) null else ColorPicker(handle)

        private const val SET_PICK_COLOR_HASH = 2920490490L
        private val setPickColorBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_pick_color", SET_PICK_COLOR_HASH)
        }

        private const val GET_PICK_COLOR_HASH = 3444240500L
        private val getPickColorBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "get_pick_color", GET_PICK_COLOR_HASH)
        }

        private const val SET_DEFERRED_MODE_HASH = 2586408642L
        private val setDeferredModeBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_deferred_mode", SET_DEFERRED_MODE_HASH)
        }

        private const val IS_DEFERRED_MODE_HASH = 36873697L
        private val isDeferredModeBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "is_deferred_mode", IS_DEFERRED_MODE_HASH)
        }

        private const val SET_COLOR_MODE_HASH = 1579114136L
        private val setColorModeBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_color_mode", SET_COLOR_MODE_HASH)
        }

        private const val GET_COLOR_MODE_HASH = 392907674L
        private val getColorModeBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "get_color_mode", GET_COLOR_MODE_HASH)
        }

        private const val SET_EDIT_ALPHA_HASH = 2586408642L
        private val setEditAlphaBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_edit_alpha", SET_EDIT_ALPHA_HASH)
        }

        private const val IS_EDITING_ALPHA_HASH = 36873697L
        private val isEditingAlphaBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "is_editing_alpha", IS_EDITING_ALPHA_HASH)
        }

        private const val SET_EDIT_INTENSITY_HASH = 2586408642L
        private val setEditIntensityBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_edit_intensity", SET_EDIT_INTENSITY_HASH)
        }

        private const val IS_EDITING_INTENSITY_HASH = 36873697L
        private val isEditingIntensityBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "is_editing_intensity", IS_EDITING_INTENSITY_HASH)
        }

        private const val SET_CAN_ADD_SWATCHES_HASH = 2586408642L
        private val setCanAddSwatchesBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_can_add_swatches", SET_CAN_ADD_SWATCHES_HASH)
        }

        private const val ARE_SWATCHES_ENABLED_HASH = 36873697L
        private val areSwatchesEnabledBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "are_swatches_enabled", ARE_SWATCHES_ENABLED_HASH)
        }

        private const val SET_PRESETS_VISIBLE_HASH = 2586408642L
        private val setPresetsVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_presets_visible", SET_PRESETS_VISIBLE_HASH)
        }

        private const val ARE_PRESETS_VISIBLE_HASH = 36873697L
        private val arePresetsVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "are_presets_visible", ARE_PRESETS_VISIBLE_HASH)
        }

        private const val SET_MODES_VISIBLE_HASH = 2586408642L
        private val setModesVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_modes_visible", SET_MODES_VISIBLE_HASH)
        }

        private const val ARE_MODES_VISIBLE_HASH = 36873697L
        private val areModesVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "are_modes_visible", ARE_MODES_VISIBLE_HASH)
        }

        private const val SET_SAMPLER_VISIBLE_HASH = 2586408642L
        private val setSamplerVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_sampler_visible", SET_SAMPLER_VISIBLE_HASH)
        }

        private const val IS_SAMPLER_VISIBLE_HASH = 36873697L
        private val isSamplerVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "is_sampler_visible", IS_SAMPLER_VISIBLE_HASH)
        }

        private const val SET_SLIDERS_VISIBLE_HASH = 2586408642L
        private val setSlidersVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_sliders_visible", SET_SLIDERS_VISIBLE_HASH)
        }

        private const val ARE_SLIDERS_VISIBLE_HASH = 36873697L
        private val areSlidersVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "are_sliders_visible", ARE_SLIDERS_VISIBLE_HASH)
        }

        private const val SET_HEX_VISIBLE_HASH = 2586408642L
        private val setHexVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_hex_visible", SET_HEX_VISIBLE_HASH)
        }

        private const val IS_HEX_VISIBLE_HASH = 36873697L
        private val isHexVisibleBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "is_hex_visible", IS_HEX_VISIBLE_HASH)
        }

        private const val ADD_PRESET_HASH = 2920490490L
        private val addPresetBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "add_preset", ADD_PRESET_HASH)
        }

        private const val ERASE_PRESET_HASH = 2920490490L
        private val erasePresetBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "erase_preset", ERASE_PRESET_HASH)
        }

        private const val GET_PRESETS_HASH = 1392750486L
        private val getPresetsBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "get_presets", GET_PRESETS_HASH)
        }

        private const val ADD_RECENT_PRESET_HASH = 2920490490L
        private val addRecentPresetBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "add_recent_preset", ADD_RECENT_PRESET_HASH)
        }

        private const val ERASE_RECENT_PRESET_HASH = 2920490490L
        private val eraseRecentPresetBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "erase_recent_preset", ERASE_RECENT_PRESET_HASH)
        }

        private const val GET_RECENT_PRESETS_HASH = 1392750486L
        private val getRecentPresetsBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "get_recent_presets", GET_RECENT_PRESETS_HASH)
        }

        private const val SET_PICKER_SHAPE_HASH = 3981373861L
        private val setPickerShapeBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "set_picker_shape", SET_PICKER_SHAPE_HASH)
        }

        private const val GET_PICKER_SHAPE_HASH = 1143229889L
        private val getPickerShapeBind by lazy {
            ObjectCalls.getMethodBind("ColorPicker", "get_picker_shape", GET_PICKER_SHAPE_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A container with connection ports, representing a node in a `GraphEdit`.
 *
 * Generated from Godot docs: GraphNode
 */
class GraphNode(handle: MemorySegment) : GraphElement(handle) {
    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var ignoreInvalidConnectionType: Boolean
        @JvmName("ignoreInvalidConnectionTypeProperty")
        get() = isIgnoringValidConnectionType()
        @JvmName("setIgnoreInvalidConnectionTypeProperty")
        set(value) = setIgnoreInvalidConnectionType(value)

    var slotsFocusMode: Long
        @JvmName("slotsFocusModeProperty")
        get() = getSlotsFocusMode()
        @JvmName("setSlotsFocusModeProperty")
        set(value) = setSlotsFocusMode(value)

    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, title)
    }

    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    fun getTitlebarHbox(): HBoxContainer? {
        return HBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTitlebarHboxBind, handle))
    }

    fun setSlot(slotIndex: Int, enableLeftPort: Boolean, typeLeft: Int, colorLeft: Color, enableRightPort: Boolean, typeRight: Int, colorRight: Color, customIconLeft: Texture2D?, customIconRight: Texture2D?, drawStylebox: Boolean = true) {
        ObjectCalls.ptrcallWithIntBoolIntColorBoolIntColorTwoObjectBoolArgs(setSlotBind, handle, slotIndex, enableLeftPort, typeLeft, colorLeft, enableRightPort, typeRight, colorRight, customIconLeft?.requireOpenHandle() ?: MemorySegment.NULL, customIconRight?.requireOpenHandle() ?: MemorySegment.NULL, drawStylebox)
    }

    fun clearSlot(slotIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(clearSlotBind, handle, slotIndex)
    }

    fun clearAllSlots() {
        ObjectCalls.ptrcallNoArgs(clearAllSlotsBind, handle)
    }

    fun isSlotEnabledLeft(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSlotEnabledLeftBind, handle, slotIndex)
    }

    fun setSlotEnabledLeft(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSlotEnabledLeftBind, handle, slotIndex, enable)
    }

    fun setSlotTypeLeft(slotIndex: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSlotTypeLeftBind, handle, slotIndex, type)
    }

    fun getSlotTypeLeft(slotIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSlotTypeLeftBind, handle, slotIndex)
    }

    fun setSlotColorLeft(slotIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setSlotColorLeftBind, handle, slotIndex, color)
    }

    fun getSlotColorLeft(slotIndex: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getSlotColorLeftBind, handle, slotIndex)
    }

    fun setSlotCustomIconLeft(slotIndex: Int, customIcon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSlotCustomIconLeftBind, handle, slotIndex, customIcon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getSlotCustomIconLeft(slotIndex: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSlotCustomIconLeftBind, handle, slotIndex))
    }

    fun setSlotMetadataLeft(slotIndex: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setSlotMetadataLeftBind, handle, slotIndex, value)
    }

    fun getSlotMetadataLeft(slotIndex: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getSlotMetadataLeftBind, handle, slotIndex)
    }

    fun isSlotEnabledRight(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSlotEnabledRightBind, handle, slotIndex)
    }

    fun setSlotEnabledRight(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSlotEnabledRightBind, handle, slotIndex, enable)
    }

    fun setSlotTypeRight(slotIndex: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSlotTypeRightBind, handle, slotIndex, type)
    }

    fun getSlotTypeRight(slotIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSlotTypeRightBind, handle, slotIndex)
    }

    fun setSlotColorRight(slotIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setSlotColorRightBind, handle, slotIndex, color)
    }

    fun getSlotColorRight(slotIndex: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getSlotColorRightBind, handle, slotIndex)
    }

    fun setSlotCustomIconRight(slotIndex: Int, customIcon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSlotCustomIconRightBind, handle, slotIndex, customIcon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getSlotCustomIconRight(slotIndex: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSlotCustomIconRightBind, handle, slotIndex))
    }

    fun setSlotMetadataRight(slotIndex: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setSlotMetadataRightBind, handle, slotIndex, value)
    }

    fun getSlotMetadataRight(slotIndex: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getSlotMetadataRightBind, handle, slotIndex)
    }

    fun isSlotDrawStylebox(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSlotDrawStyleboxBind, handle, slotIndex)
    }

    fun setSlotDrawStylebox(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSlotDrawStyleboxBind, handle, slotIndex, enable)
    }

    fun setIgnoreInvalidConnectionType(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreInvalidConnectionTypeBind, handle, ignore)
    }

    fun isIgnoringValidConnectionType(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringValidConnectionTypeBind, handle)
    }

    fun setSlotsFocusMode(focusMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSlotsFocusModeBind, handle, focusMode)
    }

    fun getSlotsFocusMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSlotsFocusModeBind, handle)
    }

    fun getInputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputPortCountBind, handle)
    }

    fun getInputPortPosition(portIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getInputPortPositionBind, handle, portIdx)
    }

    fun getInputPortType(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getInputPortTypeBind, handle, portIdx)
    }

    fun getInputPortColor(portIdx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getInputPortColorBind, handle, portIdx)
    }

    fun getInputPortSlot(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getInputPortSlotBind, handle, portIdx)
    }

    fun getOutputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutputPortCountBind, handle)
    }

    fun getOutputPortPosition(portIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getOutputPortPositionBind, handle, portIdx)
    }

    fun getOutputPortType(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOutputPortTypeBind, handle, portIdx)
    }

    fun getOutputPortColor(portIdx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getOutputPortColorBind, handle, portIdx)
    }

    fun getOutputPortSlot(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOutputPortSlotBind, handle, portIdx)
    }

    object Signals {
        const val slotUpdated: String = "slot_updated"
        const val slotSizesChanged: String = "slot_sizes_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GraphNode? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GraphNode? =
            if (handle.address() == 0L) null else GraphNode(handle)

        private const val SET_TITLE_HASH = 83702148L
        private val setTitleBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_title", SET_TITLE_HASH)
        }

        private const val GET_TITLE_HASH = 201670096L
        private val getTitleBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_title", GET_TITLE_HASH)
        }

        private const val GET_TITLEBAR_HBOX_HASH = 3590609951L
        private val getTitlebarHboxBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_titlebar_hbox", GET_TITLEBAR_HBOX_HASH)
        }

        private const val SET_SLOT_HASH = 2873310869L
        private val setSlotBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot", SET_SLOT_HASH)
        }

        private const val CLEAR_SLOT_HASH = 1286410249L
        private val clearSlotBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "clear_slot", CLEAR_SLOT_HASH)
        }

        private const val CLEAR_ALL_SLOTS_HASH = 3218959716L
        private val clearAllSlotsBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "clear_all_slots", CLEAR_ALL_SLOTS_HASH)
        }

        private const val IS_SLOT_ENABLED_LEFT_HASH = 1116898809L
        private val isSlotEnabledLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "is_slot_enabled_left", IS_SLOT_ENABLED_LEFT_HASH)
        }

        private const val SET_SLOT_ENABLED_LEFT_HASH = 300928843L
        private val setSlotEnabledLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_enabled_left", SET_SLOT_ENABLED_LEFT_HASH)
        }

        private const val SET_SLOT_TYPE_LEFT_HASH = 3937882851L
        private val setSlotTypeLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_type_left", SET_SLOT_TYPE_LEFT_HASH)
        }

        private const val GET_SLOT_TYPE_LEFT_HASH = 923996154L
        private val getSlotTypeLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_type_left", GET_SLOT_TYPE_LEFT_HASH)
        }

        private const val SET_SLOT_COLOR_LEFT_HASH = 2878471219L
        private val setSlotColorLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_color_left", SET_SLOT_COLOR_LEFT_HASH)
        }

        private const val GET_SLOT_COLOR_LEFT_HASH = 3457211756L
        private val getSlotColorLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_color_left", GET_SLOT_COLOR_LEFT_HASH)
        }

        private const val SET_SLOT_CUSTOM_ICON_LEFT_HASH = 666127730L
        private val setSlotCustomIconLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_custom_icon_left", SET_SLOT_CUSTOM_ICON_LEFT_HASH)
        }

        private const val GET_SLOT_CUSTOM_ICON_LEFT_HASH = 3536238170L
        private val getSlotCustomIconLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_custom_icon_left", GET_SLOT_CUSTOM_ICON_LEFT_HASH)
        }

        private const val SET_SLOT_METADATA_LEFT_HASH = 2152698145L
        private val setSlotMetadataLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_metadata_left", SET_SLOT_METADATA_LEFT_HASH)
        }

        private const val GET_SLOT_METADATA_LEFT_HASH = 4227898402L
        private val getSlotMetadataLeftBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_metadata_left", GET_SLOT_METADATA_LEFT_HASH)
        }

        private const val IS_SLOT_ENABLED_RIGHT_HASH = 1116898809L
        private val isSlotEnabledRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "is_slot_enabled_right", IS_SLOT_ENABLED_RIGHT_HASH)
        }

        private const val SET_SLOT_ENABLED_RIGHT_HASH = 300928843L
        private val setSlotEnabledRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_enabled_right", SET_SLOT_ENABLED_RIGHT_HASH)
        }

        private const val SET_SLOT_TYPE_RIGHT_HASH = 3937882851L
        private val setSlotTypeRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_type_right", SET_SLOT_TYPE_RIGHT_HASH)
        }

        private const val GET_SLOT_TYPE_RIGHT_HASH = 923996154L
        private val getSlotTypeRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_type_right", GET_SLOT_TYPE_RIGHT_HASH)
        }

        private const val SET_SLOT_COLOR_RIGHT_HASH = 2878471219L
        private val setSlotColorRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_color_right", SET_SLOT_COLOR_RIGHT_HASH)
        }

        private const val GET_SLOT_COLOR_RIGHT_HASH = 3457211756L
        private val getSlotColorRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_color_right", GET_SLOT_COLOR_RIGHT_HASH)
        }

        private const val SET_SLOT_CUSTOM_ICON_RIGHT_HASH = 666127730L
        private val setSlotCustomIconRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_custom_icon_right", SET_SLOT_CUSTOM_ICON_RIGHT_HASH)
        }

        private const val GET_SLOT_CUSTOM_ICON_RIGHT_HASH = 3536238170L
        private val getSlotCustomIconRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_custom_icon_right", GET_SLOT_CUSTOM_ICON_RIGHT_HASH)
        }

        private const val SET_SLOT_METADATA_RIGHT_HASH = 2152698145L
        private val setSlotMetadataRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_metadata_right", SET_SLOT_METADATA_RIGHT_HASH)
        }

        private const val GET_SLOT_METADATA_RIGHT_HASH = 4227898402L
        private val getSlotMetadataRightBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slot_metadata_right", GET_SLOT_METADATA_RIGHT_HASH)
        }

        private const val IS_SLOT_DRAW_STYLEBOX_HASH = 1116898809L
        private val isSlotDrawStyleboxBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "is_slot_draw_stylebox", IS_SLOT_DRAW_STYLEBOX_HASH)
        }

        private const val SET_SLOT_DRAW_STYLEBOX_HASH = 300928843L
        private val setSlotDrawStyleboxBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slot_draw_stylebox", SET_SLOT_DRAW_STYLEBOX_HASH)
        }

        private const val SET_IGNORE_INVALID_CONNECTION_TYPE_HASH = 2586408642L
        private val setIgnoreInvalidConnectionTypeBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_ignore_invalid_connection_type", SET_IGNORE_INVALID_CONNECTION_TYPE_HASH)
        }

        private const val IS_IGNORING_VALID_CONNECTION_TYPE_HASH = 36873697L
        private val isIgnoringValidConnectionTypeBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "is_ignoring_valid_connection_type", IS_IGNORING_VALID_CONNECTION_TYPE_HASH)
        }

        private const val SET_SLOTS_FOCUS_MODE_HASH = 3232914922L
        private val setSlotsFocusModeBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "set_slots_focus_mode", SET_SLOTS_FOCUS_MODE_HASH)
        }

        private const val GET_SLOTS_FOCUS_MODE_HASH = 2132829277L
        private val getSlotsFocusModeBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_slots_focus_mode", GET_SLOTS_FOCUS_MODE_HASH)
        }

        private const val GET_INPUT_PORT_COUNT_HASH = 2455072627L
        private val getInputPortCountBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_count", GET_INPUT_PORT_COUNT_HASH)
        }

        private const val GET_INPUT_PORT_POSITION_HASH = 3114997196L
        private val getInputPortPositionBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_position", GET_INPUT_PORT_POSITION_HASH)
        }

        private const val GET_INPUT_PORT_TYPE_HASH = 3744713108L
        private val getInputPortTypeBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_type", GET_INPUT_PORT_TYPE_HASH)
        }

        private const val GET_INPUT_PORT_COLOR_HASH = 2624840992L
        private val getInputPortColorBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_color", GET_INPUT_PORT_COLOR_HASH)
        }

        private const val GET_INPUT_PORT_SLOT_HASH = 3744713108L
        private val getInputPortSlotBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_slot", GET_INPUT_PORT_SLOT_HASH)
        }

        private const val GET_OUTPUT_PORT_COUNT_HASH = 2455072627L
        private val getOutputPortCountBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_count", GET_OUTPUT_PORT_COUNT_HASH)
        }

        private const val GET_OUTPUT_PORT_POSITION_HASH = 3114997196L
        private val getOutputPortPositionBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_position", GET_OUTPUT_PORT_POSITION_HASH)
        }

        private const val GET_OUTPUT_PORT_TYPE_HASH = 3744713108L
        private val getOutputPortTypeBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_type", GET_OUTPUT_PORT_TYPE_HASH)
        }

        private const val GET_OUTPUT_PORT_COLOR_HASH = 2624840992L
        private val getOutputPortColorBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_color", GET_OUTPUT_PORT_COLOR_HASH)
        }

        private const val GET_OUTPUT_PORT_SLOT_HASH = 3744713108L
        private val getOutputPortSlotBind by lazy {
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_slot", GET_OUTPUT_PORT_SLOT_HASH)
        }
    }
}

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

    /**
     * The text displayed in the GraphNode's title bar.
     *
     * Generated from Godot docs: GraphNode.set_title
     */
    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, title)
    }

    /**
     * The text displayed in the GraphNode's title bar.
     *
     * Generated from Godot docs: GraphNode.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    /**
     * Returns the `HBoxContainer` used for the title bar, only containing a `Label` for displaying the
     * title by default. This can be used to add custom controls to the title bar such as option or
     * close buttons.
     *
     * Generated from Godot docs: GraphNode.get_titlebar_hbox
     */
    fun getTitlebarHbox(): HBoxContainer? {
        return HBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTitlebarHboxBind, handle))
    }

    /**
     * Sets properties of the slot with the given `slot_index`. If
     * `enable_left_port`/`enable_right_port` is `true`, a port will appear and the slot will be able
     * to be connected from this side. With `type_left`/`type_right` an arbitrary type can be assigned
     * to each port. Two ports can be connected if they share the same type, or if the connection
     * between their types is allowed in the parent `GraphEdit` (see
     * `GraphEdit.add_valid_connection_type`). Keep in mind that the `GraphEdit` has the final say in
     * accepting the connection. Type compatibility simply allows the `GraphEdit.connection_request`
     * signal to be emitted. Ports can be further customized using `color_left`/`color_right` and
     * `custom_icon_left`/`custom_icon_right`. The color parameter adds a tint to the icon. The custom
     * icon can be used to override the default port dot. Additionally, `draw_stylebox` can be used to
     * enable or disable drawing of the background stylebox for each slot. See `slot`. Individual
     * properties can also be set using one of the `set_slot_*` methods. Note: This method only sets
     * properties of the slot. To create the slot itself, add a `Control`-derived child to the
     * GraphNode.
     *
     * Generated from Godot docs: GraphNode.set_slot
     */
    fun setSlot(slotIndex: Int, enableLeftPort: Boolean, typeLeft: Int, colorLeft: Color, enableRightPort: Boolean, typeRight: Int, colorRight: Color, customIconLeft: Texture2D?, customIconRight: Texture2D?, drawStylebox: Boolean = true) {
        ObjectCalls.ptrcallWithIntBoolIntColorBoolIntColorTwoObjectBoolArgs(setSlotBind, handle, slotIndex, enableLeftPort, typeLeft, colorLeft, enableRightPort, typeRight, colorRight, customIconLeft?.requireOpenHandle() ?: MemorySegment.NULL, customIconRight?.requireOpenHandle() ?: MemorySegment.NULL, drawStylebox)
    }

    /**
     * Disables the slot with the given `slot_index`. This will remove the corresponding input and
     * output port from the GraphNode.
     *
     * Generated from Godot docs: GraphNode.clear_slot
     */
    fun clearSlot(slotIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(clearSlotBind, handle, slotIndex)
    }

    /**
     * Disables all slots of the GraphNode. This will remove all input/output ports from the GraphNode.
     *
     * Generated from Godot docs: GraphNode.clear_all_slots
     */
    fun clearAllSlots() {
        ObjectCalls.ptrcallNoArgs(clearAllSlotsBind, handle)
    }

    /**
     * Returns `true` if left (input) side of the slot with the given `slot_index` is enabled.
     *
     * Generated from Godot docs: GraphNode.is_slot_enabled_left
     */
    fun isSlotEnabledLeft(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSlotEnabledLeftBind, handle, slotIndex)
    }

    /**
     * Toggles the left (input) side of the slot with the given `slot_index`. If `enable` is `true`, a
     * port will appear on the left side and the slot will be able to be connected from this side.
     *
     * Generated from Godot docs: GraphNode.set_slot_enabled_left
     */
    fun setSlotEnabledLeft(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSlotEnabledLeftBind, handle, slotIndex, enable)
    }

    /**
     * Sets the left (input) type of the slot with the given `slot_index` to `type`. If the value is
     * negative, all connections will be disallowed to be created via user inputs.
     *
     * Generated from Godot docs: GraphNode.set_slot_type_left
     */
    fun setSlotTypeLeft(slotIndex: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSlotTypeLeftBind, handle, slotIndex, type)
    }

    /**
     * Returns the left (input) type of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_type_left
     */
    fun getSlotTypeLeft(slotIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSlotTypeLeftBind, handle, slotIndex)
    }

    /**
     * Sets the `Color` of the left (input) side of the slot with the given `slot_index` to `color`.
     *
     * Generated from Godot docs: GraphNode.set_slot_color_left
     */
    fun setSlotColorLeft(slotIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setSlotColorLeftBind, handle, slotIndex, color)
    }

    /**
     * Returns the left (input) `Color` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_color_left
     */
    fun getSlotColorLeft(slotIndex: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getSlotColorLeftBind, handle, slotIndex)
    }

    /**
     * Sets the custom `Texture2D` of the left (input) side of the slot with the given `slot_index` to
     * `custom_icon`.
     *
     * Generated from Godot docs: GraphNode.set_slot_custom_icon_left
     */
    fun setSlotCustomIconLeft(slotIndex: Int, customIcon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSlotCustomIconLeftBind, handle, slotIndex, customIcon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the left (input) custom `Texture2D` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_custom_icon_left
     */
    fun getSlotCustomIconLeft(slotIndex: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSlotCustomIconLeftBind, handle, slotIndex))
    }

    /**
     * Sets the custom metadata for the left (input) side of the slot with the given `slot_index` to
     * `value`.
     *
     * Generated from Godot docs: GraphNode.set_slot_metadata_left
     */
    fun setSlotMetadataLeft(slotIndex: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setSlotMetadataLeftBind, handle, slotIndex, value)
    }

    /**
     * Returns the left (input) metadata of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_metadata_left
     */
    fun getSlotMetadataLeft(slotIndex: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getSlotMetadataLeftBind, handle, slotIndex)
    }

    /**
     * Returns `true` if right (output) side of the slot with the given `slot_index` is enabled.
     *
     * Generated from Godot docs: GraphNode.is_slot_enabled_right
     */
    fun isSlotEnabledRight(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSlotEnabledRightBind, handle, slotIndex)
    }

    /**
     * Toggles the right (output) side of the slot with the given `slot_index`. If `enable` is `true`,
     * a port will appear on the right side and the slot will be able to be connected from this side.
     *
     * Generated from Godot docs: GraphNode.set_slot_enabled_right
     */
    fun setSlotEnabledRight(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSlotEnabledRightBind, handle, slotIndex, enable)
    }

    /**
     * Sets the right (output) type of the slot with the given `slot_index` to `type`. If the value is
     * negative, all connections will be disallowed to be created via user inputs.
     *
     * Generated from Godot docs: GraphNode.set_slot_type_right
     */
    fun setSlotTypeRight(slotIndex: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSlotTypeRightBind, handle, slotIndex, type)
    }

    /**
     * Returns the right (output) type of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_type_right
     */
    fun getSlotTypeRight(slotIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSlotTypeRightBind, handle, slotIndex)
    }

    /**
     * Sets the `Color` of the right (output) side of the slot with the given `slot_index` to `color`.
     *
     * Generated from Godot docs: GraphNode.set_slot_color_right
     */
    fun setSlotColorRight(slotIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setSlotColorRightBind, handle, slotIndex, color)
    }

    /**
     * Returns the right (output) `Color` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_color_right
     */
    fun getSlotColorRight(slotIndex: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getSlotColorRightBind, handle, slotIndex)
    }

    /**
     * Sets the custom `Texture2D` of the right (output) side of the slot with the given `slot_index`
     * to `custom_icon`.
     *
     * Generated from Godot docs: GraphNode.set_slot_custom_icon_right
     */
    fun setSlotCustomIconRight(slotIndex: Int, customIcon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSlotCustomIconRightBind, handle, slotIndex, customIcon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the right (output) custom `Texture2D` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_custom_icon_right
     */
    fun getSlotCustomIconRight(slotIndex: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSlotCustomIconRightBind, handle, slotIndex))
    }

    /**
     * Sets the custom metadata for the right (output) side of the slot with the given `slot_index` to
     * `value`.
     *
     * Generated from Godot docs: GraphNode.set_slot_metadata_right
     */
    fun setSlotMetadataRight(slotIndex: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setSlotMetadataRightBind, handle, slotIndex, value)
    }

    /**
     * Returns the right (output) metadata of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_metadata_right
     */
    fun getSlotMetadataRight(slotIndex: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getSlotMetadataRightBind, handle, slotIndex)
    }

    /**
     * Returns `true` if the background `StyleBox` of the slot with the given `slot_index` is drawn.
     *
     * Generated from Godot docs: GraphNode.is_slot_draw_stylebox
     */
    fun isSlotDrawStylebox(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSlotDrawStyleboxBind, handle, slotIndex)
    }

    /**
     * Toggles the background `StyleBox` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.set_slot_draw_stylebox
     */
    fun setSlotDrawStylebox(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSlotDrawStyleboxBind, handle, slotIndex, enable)
    }

    /**
     * If `true`, you can connect ports with different types, even if the connection was not explicitly
     * allowed in the parent `GraphEdit`.
     *
     * Generated from Godot docs: GraphNode.set_ignore_invalid_connection_type
     */
    fun setIgnoreInvalidConnectionType(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreInvalidConnectionTypeBind, handle, ignore)
    }

    /**
     * If `true`, you can connect ports with different types, even if the connection was not explicitly
     * allowed in the parent `GraphEdit`.
     *
     * Generated from Godot docs: GraphNode.is_ignoring_valid_connection_type
     */
    fun isIgnoringValidConnectionType(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringValidConnectionTypeBind, handle)
    }

    /**
     * Determines how connection slots can be focused. - If set to `Control.FOCUS_CLICK`, connections
     * can only be made with the mouse. - If set to `Control.FOCUS_ALL`, slots can also be focused
     * using the `ProjectSettings.input/ui_up` and `ProjectSettings.input/ui_down` and connected using
     * `ProjectSettings.input/ui_left` and `ProjectSettings.input/ui_right` input actions. - If set to
     * `Control.FOCUS_ACCESSIBILITY`, slot input actions are only enabled when the screen reader is
     * active.
     *
     * Generated from Godot docs: GraphNode.set_slots_focus_mode
     */
    fun setSlotsFocusMode(focusMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSlotsFocusModeBind, handle, focusMode)
    }

    /**
     * Determines how connection slots can be focused. - If set to `Control.FOCUS_CLICK`, connections
     * can only be made with the mouse. - If set to `Control.FOCUS_ALL`, slots can also be focused
     * using the `ProjectSettings.input/ui_up` and `ProjectSettings.input/ui_down` and connected using
     * `ProjectSettings.input/ui_left` and `ProjectSettings.input/ui_right` input actions. - If set to
     * `Control.FOCUS_ACCESSIBILITY`, slot input actions are only enabled when the screen reader is
     * active.
     *
     * Generated from Godot docs: GraphNode.get_slots_focus_mode
     */
    fun getSlotsFocusMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSlotsFocusModeBind, handle)
    }

    /**
     * Returns the number of slots with an enabled input port.
     *
     * Generated from Godot docs: GraphNode.get_input_port_count
     */
    fun getInputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputPortCountBind, handle)
    }

    /**
     * Returns the position of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_position
     */
    fun getInputPortPosition(portIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getInputPortPositionBind, handle, portIdx)
    }

    /**
     * Returns the type of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_type
     */
    fun getInputPortType(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getInputPortTypeBind, handle, portIdx)
    }

    /**
     * Returns the `Color` of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_color
     */
    fun getInputPortColor(portIdx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getInputPortColorBind, handle, portIdx)
    }

    /**
     * Returns the corresponding slot index of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_slot
     */
    fun getInputPortSlot(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getInputPortSlotBind, handle, portIdx)
    }

    /**
     * Returns the number of slots with an enabled output port.
     *
     * Generated from Godot docs: GraphNode.get_output_port_count
     */
    fun getOutputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutputPortCountBind, handle)
    }

    /**
     * Returns the position of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_position
     */
    fun getOutputPortPosition(portIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getOutputPortPositionBind, handle, portIdx)
    }

    /**
     * Returns the type of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_type
     */
    fun getOutputPortType(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOutputPortTypeBind, handle, portIdx)
    }

    /**
     * Returns the `Color` of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_color
     */
    fun getOutputPortColor(portIdx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getOutputPortColorBind, handle, portIdx)
    }

    /**
     * Returns the corresponding slot index of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_slot
     */
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

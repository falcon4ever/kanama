package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A container with connection ports, representing a node in a `GraphEdit`.
 *
 * Generated from Godot docs: GraphNode
 */
class GraphNode(handle: GodotHandle) : GraphElement(handle) {
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

    var slotsFocusMode: Control.FocusMode
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
        ObjectCalls.ptrcallWithStringArg(Binds.setTitleBind, segment, title)
    }

    /**
     * The text displayed in the GraphNode's title bar.
     *
     * Generated from Godot docs: GraphNode.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTitleBind, segment)
    }

    /**
     * Returns the `HBoxContainer` used for the title bar, only containing a `Label` for displaying the
     * title by default. This can be used to add custom controls to the title bar such as option or
     * close buttons.
     *
     * Generated from Godot docs: GraphNode.get_titlebar_hbox
     */
    fun getTitlebarHbox(): HBoxContainer? {
        return HBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTitlebarHboxBind, segment))
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
        ObjectCalls.ptrcallWithIntBoolIntColorBoolIntColorTwoObjectBoolArgs(Binds.setSlotBind, segment, slotIndex, enableLeftPort, typeLeft, colorLeft, enableRightPort, typeRight, colorRight, customIconLeft?.requireOpenHandle() ?: NULL_SEGMENT, customIconRight?.requireOpenHandle() ?: NULL_SEGMENT, drawStylebox)
    }

    /**
     * Disables the slot with the given `slot_index`. This will remove the corresponding input and
     * output port from the GraphNode.
     *
     * Generated from Godot docs: GraphNode.clear_slot
     */
    fun clearSlot(slotIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.clearSlotBind, segment, slotIndex)
    }

    /**
     * Disables all slots of the GraphNode. This will remove all input/output ports from the GraphNode.
     *
     * Generated from Godot docs: GraphNode.clear_all_slots
     */
    fun clearAllSlots() {
        ObjectCalls.ptrcallNoArgs(Binds.clearAllSlotsBind, segment)
    }

    /**
     * Returns `true` if left (input) side of the slot with the given `slot_index` is enabled.
     *
     * Generated from Godot docs: GraphNode.is_slot_enabled_left
     */
    fun isSlotEnabledLeft(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isSlotEnabledLeftBind, segment, slotIndex)
    }

    /**
     * Toggles the left (input) side of the slot with the given `slot_index`. If `enable` is `true`, a
     * port will appear on the left side and the slot will be able to be connected from this side.
     *
     * Generated from Godot docs: GraphNode.set_slot_enabled_left
     */
    fun setSlotEnabledLeft(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setSlotEnabledLeftBind, segment, slotIndex, enable)
    }

    /**
     * Sets the left (input) type of the slot with the given `slot_index` to `type`. If the value is
     * negative, all connections will be disallowed to be created via user inputs.
     *
     * Generated from Godot docs: GraphNode.set_slot_type_left
     */
    fun setSlotTypeLeft(slotIndex: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setSlotTypeLeftBind, segment, slotIndex, type)
    }

    /**
     * Returns the left (input) type of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_type_left
     */
    fun getSlotTypeLeft(slotIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getSlotTypeLeftBind, segment, slotIndex)
    }

    /**
     * Sets the `Color` of the left (input) side of the slot with the given `slot_index` to `color`.
     *
     * Generated from Godot docs: GraphNode.set_slot_color_left
     */
    fun setSlotColorLeft(slotIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(Binds.setSlotColorLeftBind, segment, slotIndex, color)
    }

    /**
     * Returns the left (input) `Color` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_color_left
     */
    fun getSlotColorLeft(slotIndex: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getSlotColorLeftBind, segment, slotIndex)
    }

    /**
     * Sets the custom `Texture2D` of the left (input) side of the slot with the given `slot_index` to
     * `custom_icon`.
     *
     * Generated from Godot docs: GraphNode.set_slot_custom_icon_left
     */
    fun setSlotCustomIconLeft(slotIndex: Int, customIcon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setSlotCustomIconLeftBind, segment, slotIndex, customIcon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the left (input) custom `Texture2D` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_custom_icon_left
     */
    fun getSlotCustomIconLeft(slotIndex: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSlotCustomIconLeftBind, segment, slotIndex))
    }

    /**
     * Sets the custom metadata for the left (input) side of the slot with the given `slot_index` to
     * `value`.
     *
     * Generated from Godot docs: GraphNode.set_slot_metadata_left
     */
    fun setSlotMetadataLeft(slotIndex: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setSlotMetadataLeftBind, segment, slotIndex, value)
    }

    /**
     * Returns the left (input) metadata of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_metadata_left
     */
    fun getSlotMetadataLeft(slotIndex: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getSlotMetadataLeftBind, segment, slotIndex)
    }

    /**
     * Returns `true` if right (output) side of the slot with the given `slot_index` is enabled.
     *
     * Generated from Godot docs: GraphNode.is_slot_enabled_right
     */
    fun isSlotEnabledRight(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isSlotEnabledRightBind, segment, slotIndex)
    }

    /**
     * Toggles the right (output) side of the slot with the given `slot_index`. If `enable` is `true`,
     * a port will appear on the right side and the slot will be able to be connected from this side.
     *
     * Generated from Godot docs: GraphNode.set_slot_enabled_right
     */
    fun setSlotEnabledRight(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setSlotEnabledRightBind, segment, slotIndex, enable)
    }

    /**
     * Sets the right (output) type of the slot with the given `slot_index` to `type`. If the value is
     * negative, all connections will be disallowed to be created via user inputs.
     *
     * Generated from Godot docs: GraphNode.set_slot_type_right
     */
    fun setSlotTypeRight(slotIndex: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setSlotTypeRightBind, segment, slotIndex, type)
    }

    /**
     * Returns the right (output) type of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_type_right
     */
    fun getSlotTypeRight(slotIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getSlotTypeRightBind, segment, slotIndex)
    }

    /**
     * Sets the `Color` of the right (output) side of the slot with the given `slot_index` to `color`.
     *
     * Generated from Godot docs: GraphNode.set_slot_color_right
     */
    fun setSlotColorRight(slotIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(Binds.setSlotColorRightBind, segment, slotIndex, color)
    }

    /**
     * Returns the right (output) `Color` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_color_right
     */
    fun getSlotColorRight(slotIndex: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getSlotColorRightBind, segment, slotIndex)
    }

    /**
     * Sets the custom `Texture2D` of the right (output) side of the slot with the given `slot_index`
     * to `custom_icon`.
     *
     * Generated from Godot docs: GraphNode.set_slot_custom_icon_right
     */
    fun setSlotCustomIconRight(slotIndex: Int, customIcon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setSlotCustomIconRightBind, segment, slotIndex, customIcon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the right (output) custom `Texture2D` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_custom_icon_right
     */
    fun getSlotCustomIconRight(slotIndex: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSlotCustomIconRightBind, segment, slotIndex))
    }

    /**
     * Sets the custom metadata for the right (output) side of the slot with the given `slot_index` to
     * `value`.
     *
     * Generated from Godot docs: GraphNode.set_slot_metadata_right
     */
    fun setSlotMetadataRight(slotIndex: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setSlotMetadataRightBind, segment, slotIndex, value)
    }

    /**
     * Returns the right (output) metadata of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.get_slot_metadata_right
     */
    fun getSlotMetadataRight(slotIndex: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getSlotMetadataRightBind, segment, slotIndex)
    }

    /**
     * Returns `true` if the background `StyleBox` of the slot with the given `slot_index` is drawn.
     *
     * Generated from Godot docs: GraphNode.is_slot_draw_stylebox
     */
    fun isSlotDrawStylebox(slotIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isSlotDrawStyleboxBind, segment, slotIndex)
    }

    /**
     * Toggles the background `StyleBox` of the slot with the given `slot_index`.
     *
     * Generated from Godot docs: GraphNode.set_slot_draw_stylebox
     */
    fun setSlotDrawStylebox(slotIndex: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setSlotDrawStyleboxBind, segment, slotIndex, enable)
    }

    /**
     * If `true`, you can connect ports with different types, even if the connection was not explicitly
     * allowed in the parent `GraphEdit`.
     *
     * Generated from Godot docs: GraphNode.set_ignore_invalid_connection_type
     */
    fun setIgnoreInvalidConnectionType(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setIgnoreInvalidConnectionTypeBind, segment, ignore)
    }

    /**
     * If `true`, you can connect ports with different types, even if the connection was not explicitly
     * allowed in the parent `GraphEdit`.
     *
     * Generated from Godot docs: GraphNode.is_ignoring_valid_connection_type
     */
    fun isIgnoringValidConnectionType(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isIgnoringValidConnectionTypeBind, segment)
    }

    /**
     * Determines how connection slots can be focused. - If set to `Control.FocusMode.CLICK`,
     * connections can only be made with the mouse. - If set to `Control.FocusMode.ALL`, slots can also
     * be focused using the `ProjectSettings.input/ui_up` and `ProjectSettings.input/ui_down` and
     * connected using `ProjectSettings.input/ui_left` and `ProjectSettings.input/ui_right` input
     * actions. - If set to `Control.FocusMode.ACCESSIBILITY`, slot input actions are only enabled when
     * the screen reader is active.
     *
     * Generated from Godot docs: GraphNode.set_slots_focus_mode
     */
    fun setSlotsFocusMode(focusMode: Control.FocusMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setSlotsFocusModeBind, segment, focusMode.value)
    }

    /**
     * Determines how connection slots can be focused. - If set to `Control.FocusMode.CLICK`,
     * connections can only be made with the mouse. - If set to `Control.FocusMode.ALL`, slots can also
     * be focused using the `ProjectSettings.input/ui_up` and `ProjectSettings.input/ui_down` and
     * connected using `ProjectSettings.input/ui_left` and `ProjectSettings.input/ui_right` input
     * actions. - If set to `Control.FocusMode.ACCESSIBILITY`, slot input actions are only enabled when
     * the screen reader is active.
     *
     * Generated from Godot docs: GraphNode.get_slots_focus_mode
     */
    fun getSlotsFocusMode(): Control.FocusMode {
        return Control.FocusMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSlotsFocusModeBind, segment))
    }

    /**
     * Returns the number of slots with an enabled input port.
     *
     * Generated from Godot docs: GraphNode.get_input_port_count
     */
    fun getInputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInputPortCountBind, segment)
    }

    /**
     * Returns the position of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_position
     */
    fun getInputPortPosition(portIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getInputPortPositionBind, segment, portIdx)
    }

    /**
     * Returns the type of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_type
     */
    fun getInputPortType(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getInputPortTypeBind, segment, portIdx)
    }

    /**
     * Returns the `Color` of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_color
     */
    fun getInputPortColor(portIdx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getInputPortColorBind, segment, portIdx)
    }

    /**
     * Returns the corresponding slot index of the input port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_input_port_slot
     */
    fun getInputPortSlot(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getInputPortSlotBind, segment, portIdx)
    }

    /**
     * Returns the number of slots with an enabled output port.
     *
     * Generated from Godot docs: GraphNode.get_output_port_count
     */
    fun getOutputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutputPortCountBind, segment)
    }

    /**
     * Returns the position of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_position
     */
    fun getOutputPortPosition(portIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getOutputPortPositionBind, segment, portIdx)
    }

    /**
     * Returns the type of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_type
     */
    fun getOutputPortType(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getOutputPortTypeBind, segment, portIdx)
    }

    /**
     * Returns the `Color` of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_color
     */
    fun getOutputPortColor(portIdx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getOutputPortColorBind, segment, portIdx)
    }

    /**
     * Returns the corresponding slot index of the output port with the given `port_idx`.
     *
     * Generated from Godot docs: GraphNode.get_output_port_slot
     */
    fun getOutputPortSlot(portIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getOutputPortSlotBind, segment, portIdx)
    }

    /** Signal `slot_updated(slot_index: int)`; see [TypedSignal]. */
    val slotUpdated: Signal1<Long>
        @JvmName("slotUpdatedTypedSignal")
        get() = Signal1(this, "slot_updated", SignalArgType.LONG)

    /** Signal `slot_sizes_changed()`; see [TypedSignal]. */
    val slotSizesChanged: Signal0
        @JvmName("slotSizesChangedTypedSignal")
        get() = Signal0(this, "slot_sizes_changed")

    object Signals {
        const val slotUpdated: String = "slot_updated"
        const val slotSizesChanged: String = "slot_sizes_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GraphNode? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GraphNode? =
            if (handle.address() == 0L) null else GraphNode(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TITLE_HASH = 83702148L
        @JvmField
        val setTitleBind =
            ObjectCalls.getMethodBind("GraphNode", "set_title", SET_TITLE_HASH)

        private const val GET_TITLE_HASH = 201670096L
        @JvmField
        val getTitleBind =
            ObjectCalls.getMethodBind("GraphNode", "get_title", GET_TITLE_HASH)

        private const val GET_TITLEBAR_HBOX_HASH = 3590609951L
        @JvmField
        val getTitlebarHboxBind =
            ObjectCalls.getMethodBind("GraphNode", "get_titlebar_hbox", GET_TITLEBAR_HBOX_HASH)

        private const val SET_SLOT_HASH = 2873310869L
        @JvmField
        val setSlotBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot", SET_SLOT_HASH)

        private const val CLEAR_SLOT_HASH = 1286410249L
        @JvmField
        val clearSlotBind =
            ObjectCalls.getMethodBind("GraphNode", "clear_slot", CLEAR_SLOT_HASH)

        private const val CLEAR_ALL_SLOTS_HASH = 3218959716L
        @JvmField
        val clearAllSlotsBind =
            ObjectCalls.getMethodBind("GraphNode", "clear_all_slots", CLEAR_ALL_SLOTS_HASH)

        private const val IS_SLOT_ENABLED_LEFT_HASH = 1116898809L
        @JvmField
        val isSlotEnabledLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "is_slot_enabled_left", IS_SLOT_ENABLED_LEFT_HASH)

        private const val SET_SLOT_ENABLED_LEFT_HASH = 300928843L
        @JvmField
        val setSlotEnabledLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_enabled_left", SET_SLOT_ENABLED_LEFT_HASH)

        private const val SET_SLOT_TYPE_LEFT_HASH = 3937882851L
        @JvmField
        val setSlotTypeLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_type_left", SET_SLOT_TYPE_LEFT_HASH)

        private const val GET_SLOT_TYPE_LEFT_HASH = 923996154L
        @JvmField
        val getSlotTypeLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_type_left", GET_SLOT_TYPE_LEFT_HASH)

        private const val SET_SLOT_COLOR_LEFT_HASH = 2878471219L
        @JvmField
        val setSlotColorLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_color_left", SET_SLOT_COLOR_LEFT_HASH)

        private const val GET_SLOT_COLOR_LEFT_HASH = 3457211756L
        @JvmField
        val getSlotColorLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_color_left", GET_SLOT_COLOR_LEFT_HASH)

        private const val SET_SLOT_CUSTOM_ICON_LEFT_HASH = 666127730L
        @JvmField
        val setSlotCustomIconLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_custom_icon_left", SET_SLOT_CUSTOM_ICON_LEFT_HASH)

        private const val GET_SLOT_CUSTOM_ICON_LEFT_HASH = 3536238170L
        @JvmField
        val getSlotCustomIconLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_custom_icon_left", GET_SLOT_CUSTOM_ICON_LEFT_HASH)

        private const val SET_SLOT_METADATA_LEFT_HASH = 2152698145L
        @JvmField
        val setSlotMetadataLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_metadata_left", SET_SLOT_METADATA_LEFT_HASH)

        private const val GET_SLOT_METADATA_LEFT_HASH = 4227898402L
        @JvmField
        val getSlotMetadataLeftBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_metadata_left", GET_SLOT_METADATA_LEFT_HASH)

        private const val IS_SLOT_ENABLED_RIGHT_HASH = 1116898809L
        @JvmField
        val isSlotEnabledRightBind =
            ObjectCalls.getMethodBind("GraphNode", "is_slot_enabled_right", IS_SLOT_ENABLED_RIGHT_HASH)

        private const val SET_SLOT_ENABLED_RIGHT_HASH = 300928843L
        @JvmField
        val setSlotEnabledRightBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_enabled_right", SET_SLOT_ENABLED_RIGHT_HASH)

        private const val SET_SLOT_TYPE_RIGHT_HASH = 3937882851L
        @JvmField
        val setSlotTypeRightBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_type_right", SET_SLOT_TYPE_RIGHT_HASH)

        private const val GET_SLOT_TYPE_RIGHT_HASH = 923996154L
        @JvmField
        val getSlotTypeRightBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_type_right", GET_SLOT_TYPE_RIGHT_HASH)

        private const val SET_SLOT_COLOR_RIGHT_HASH = 2878471219L
        @JvmField
        val setSlotColorRightBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_color_right", SET_SLOT_COLOR_RIGHT_HASH)

        private const val GET_SLOT_COLOR_RIGHT_HASH = 3457211756L
        @JvmField
        val getSlotColorRightBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_color_right", GET_SLOT_COLOR_RIGHT_HASH)

        private const val SET_SLOT_CUSTOM_ICON_RIGHT_HASH = 666127730L
        @JvmField
        val setSlotCustomIconRightBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_custom_icon_right", SET_SLOT_CUSTOM_ICON_RIGHT_HASH)

        private const val GET_SLOT_CUSTOM_ICON_RIGHT_HASH = 3536238170L
        @JvmField
        val getSlotCustomIconRightBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_custom_icon_right", GET_SLOT_CUSTOM_ICON_RIGHT_HASH)

        private const val SET_SLOT_METADATA_RIGHT_HASH = 2152698145L
        @JvmField
        val setSlotMetadataRightBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_metadata_right", SET_SLOT_METADATA_RIGHT_HASH)

        private const val GET_SLOT_METADATA_RIGHT_HASH = 4227898402L
        @JvmField
        val getSlotMetadataRightBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slot_metadata_right", GET_SLOT_METADATA_RIGHT_HASH)

        private const val IS_SLOT_DRAW_STYLEBOX_HASH = 1116898809L
        @JvmField
        val isSlotDrawStyleboxBind =
            ObjectCalls.getMethodBind("GraphNode", "is_slot_draw_stylebox", IS_SLOT_DRAW_STYLEBOX_HASH)

        private const val SET_SLOT_DRAW_STYLEBOX_HASH = 300928843L
        @JvmField
        val setSlotDrawStyleboxBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slot_draw_stylebox", SET_SLOT_DRAW_STYLEBOX_HASH)

        private const val SET_IGNORE_INVALID_CONNECTION_TYPE_HASH = 2586408642L
        @JvmField
        val setIgnoreInvalidConnectionTypeBind =
            ObjectCalls.getMethodBind("GraphNode", "set_ignore_invalid_connection_type", SET_IGNORE_INVALID_CONNECTION_TYPE_HASH)

        private const val IS_IGNORING_VALID_CONNECTION_TYPE_HASH = 36873697L
        @JvmField
        val isIgnoringValidConnectionTypeBind =
            ObjectCalls.getMethodBind("GraphNode", "is_ignoring_valid_connection_type", IS_IGNORING_VALID_CONNECTION_TYPE_HASH)

        private const val SET_SLOTS_FOCUS_MODE_HASH = 3232914922L
        @JvmField
        val setSlotsFocusModeBind =
            ObjectCalls.getMethodBind("GraphNode", "set_slots_focus_mode", SET_SLOTS_FOCUS_MODE_HASH)

        private const val GET_SLOTS_FOCUS_MODE_HASH = 2132829277L
        @JvmField
        val getSlotsFocusModeBind =
            ObjectCalls.getMethodBind("GraphNode", "get_slots_focus_mode", GET_SLOTS_FOCUS_MODE_HASH)

        private const val GET_INPUT_PORT_COUNT_HASH = 2455072627L
        @JvmField
        val getInputPortCountBind =
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_count", GET_INPUT_PORT_COUNT_HASH)

        private const val GET_INPUT_PORT_POSITION_HASH = 3114997196L
        @JvmField
        val getInputPortPositionBind =
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_position", GET_INPUT_PORT_POSITION_HASH)

        private const val GET_INPUT_PORT_TYPE_HASH = 3744713108L
        @JvmField
        val getInputPortTypeBind =
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_type", GET_INPUT_PORT_TYPE_HASH)

        private const val GET_INPUT_PORT_COLOR_HASH = 2624840992L
        @JvmField
        val getInputPortColorBind =
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_color", GET_INPUT_PORT_COLOR_HASH)

        private const val GET_INPUT_PORT_SLOT_HASH = 3744713108L
        @JvmField
        val getInputPortSlotBind =
            ObjectCalls.getMethodBind("GraphNode", "get_input_port_slot", GET_INPUT_PORT_SLOT_HASH)

        private const val GET_OUTPUT_PORT_COUNT_HASH = 2455072627L
        @JvmField
        val getOutputPortCountBind =
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_count", GET_OUTPUT_PORT_COUNT_HASH)

        private const val GET_OUTPUT_PORT_POSITION_HASH = 3114997196L
        @JvmField
        val getOutputPortPositionBind =
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_position", GET_OUTPUT_PORT_POSITION_HASH)

        private const val GET_OUTPUT_PORT_TYPE_HASH = 3744713108L
        @JvmField
        val getOutputPortTypeBind =
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_type", GET_OUTPUT_PORT_TYPE_HASH)

        private const val GET_OUTPUT_PORT_COLOR_HASH = 2624840992L
        @JvmField
        val getOutputPortColorBind =
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_color", GET_OUTPUT_PORT_COLOR_HASH)

        private const val GET_OUTPUT_PORT_SLOT_HASH = 3744713108L
        @JvmField
        val getOutputPortSlotBind =
            ObjectCalls.getMethodBind("GraphNode", "get_output_port_slot", GET_OUTPUT_PORT_SLOT_HASH)
    }
}

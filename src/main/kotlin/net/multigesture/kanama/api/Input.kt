package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * A singleton for handling inputs.
 *
 * Generated from Godot docs: Input
 */
object Input {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Input")
    }

    const val MOUSE_MODE_VISIBLE: Long = 0L
    const val MOUSE_MODE_HIDDEN: Long = 1L
    const val MOUSE_MODE_CAPTURED: Long = 2L
    const val MOUSE_MODE_CONFINED: Long = 3L
    const val MOUSE_MODE_CONFINED_HIDDEN: Long = 4L
    const val MOUSE_MODE_MAX: Long = 5L
    const val CURSOR_ARROW: Long = 0L
    const val CURSOR_IBEAM: Long = 1L
    const val CURSOR_POINTING_HAND: Long = 2L
    const val CURSOR_CROSS: Long = 3L
    const val CURSOR_WAIT: Long = 4L
    const val CURSOR_BUSY: Long = 5L
    const val CURSOR_DRAG: Long = 6L
    const val CURSOR_CAN_DROP: Long = 7L
    const val CURSOR_FORBIDDEN: Long = 8L
    const val CURSOR_VSIZE: Long = 9L
    const val CURSOR_HSIZE: Long = 10L
    const val CURSOR_BDIAGSIZE: Long = 11L
    const val CURSOR_FDIAGSIZE: Long = 12L
    const val CURSOR_MOVE: Long = 13L
    const val CURSOR_VSPLIT: Long = 14L
    const val CURSOR_HSPLIT: Long = 15L
    const val CURSOR_HELP: Long = 16L

    var mouseMode: Long
        @JvmName("mouseModeProperty")
        get() = getMouseMode()
        @JvmName("setMouseModeProperty")
        set(value) = setMouseMode(value)

    var useAccumulatedInput: Boolean
        @JvmName("useAccumulatedInputProperty")
        get() = isUsingAccumulatedInput()
        @JvmName("setUseAccumulatedInputProperty")
        set(value) = setUseAccumulatedInput(value)

    var emulateMouseFromTouch: Boolean
        @JvmName("emulateMouseFromTouchProperty")
        get() = isEmulatingMouseFromTouch()
        @JvmName("setEmulateMouseFromTouchProperty")
        set(value) = setEmulateMouseFromTouch(value)

    var emulateTouchFromMouse: Boolean
        @JvmName("emulateTouchFromMouseProperty")
        get() = isEmulatingTouchFromMouse()
        @JvmName("setEmulateTouchFromMouseProperty")
        set(value) = setEmulateTouchFromMouse(value)

    var ignoreJoypadOnUnfocusedApplication: Boolean
        @JvmName("ignoreJoypadOnUnfocusedApplicationProperty")
        get() = isIgnoringJoypadOnUnfocusedApplication()
        @JvmName("setIgnoreJoypadOnUnfocusedApplicationProperty")
        set(value) = setIgnoreJoypadOnUnfocusedApplication(value)

    /**
     * Returns `true` if any action, key, joypad button, or mouse button is being pressed. This will
     * also return `true` if any action is simulated via code by calling `action_press`.
     *
     * Generated from Godot docs: Input.is_anything_pressed
     */
    @JvmStatic
    fun isAnythingPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAnythingPressedBind, singleton)
    }

    /**
     * Returns `true` if you are pressing the Latin key in the current keyboard layout. You can pass a
     * `Key` constant. `is_key_pressed` is only recommended over `is_physical_key_pressed` in non-game
     * applications. This ensures that shortcut keys behave as expected depending on the user's
     * keyboard layout, as keyboard shortcuts are generally dependent on the keyboard layout in
     * non-game applications. If in doubt, use `is_physical_key_pressed`. Note: Due to keyboard
     * ghosting, `is_key_pressed` may return `false` even if one of the action's keys is pressed. See
     * Input examples ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in the
     * documentation for more information. Note: If you want to check if a key was just pressed by
     * using its keycode, use Godot's input action system with `is_action_just_pressed` or use the
     * `Node._input` method like this instead:
     *
     * Generated from Godot docs: Input.is_key_pressed
     */
    @JvmStatic
    fun isKeyPressed(keycode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isKeyPressedBind, singleton, keycode)
    }

    /**
     * Returns `true` if you are pressing the key in the physical location on the 101/102-key US QWERTY
     * keyboard. You can pass a `Key` constant. `is_physical_key_pressed` is recommended over
     * `is_key_pressed` for in-game actions, as it will make W/A/S/D layouts work regardless of the
     * user's keyboard layout. `is_physical_key_pressed` will also ensure that the top row number keys
     * work on any keyboard layout. If in doubt, use `is_physical_key_pressed`. Note: Due to keyboard
     * ghosting, `is_physical_key_pressed` may return `false` even if one of the action's keys is
     * pressed. See Input examples ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in
     * the documentation for more information. Note: If you want to check if a key was just pressed by
     * using its physical keycode, use Godot's input action system with `is_action_just_pressed` or use
     * the `Node._input` method like this instead:
     *
     * Generated from Godot docs: Input.is_physical_key_pressed
     */
    @JvmStatic
    fun isPhysicalKeyPressed(keycode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isPhysicalKeyPressedBind, singleton, keycode)
    }

    /**
     * Returns `true` if you are pressing the key with the `keycode` printed on it. You can pass a
     * `Key` constant or any Unicode character code. Note: If you want to check if a key was just
     * pressed by using its label, use Godot's input action system with `is_action_just_pressed` or use
     * the `Node._input` method like this instead:
     *
     * Generated from Godot docs: Input.is_key_label_pressed
     */
    @JvmStatic
    fun isKeyLabelPressed(keycode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isKeyLabelPressedBind, singleton, keycode)
    }

    /**
     * Returns `true` if you are pressing the mouse button specified with `MouseButton`. Note: If you
     * want to check if a mouse button was just pressed, use Godot's input action system with
     * `is_action_just_pressed` or use the `Node._input` method like this instead:
     *
     * Generated from Godot docs: Input.is_mouse_button_pressed
     */
    @JvmStatic
    fun isMouseButtonPressed(button: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isMouseButtonPressedBind, singleton, button)
    }

    /**
     * Returns `true` if you are pressing the joypad button at index `button`. Note: If you want to
     * check if a joypad button was just pressed, use Godot's input action system with
     * `is_action_just_pressed` or use the `Node._input` method like this instead:
     *
     * Generated from Godot docs: Input.is_joy_button_pressed
     */
    @JvmStatic
    fun isJoyButtonPressed(device: Int, button: Long): Boolean {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetBool(isJoyButtonPressedBind, singleton, device, button)
    }

    /**
     * Returns `true` if you are pressing the action event. If `exact_match` is `false`, it ignores
     * additional input modifiers for `InputEventKey` and `InputEventMouseButton` events, and the
     * direction for `InputEventJoypadMotion` events. Note: Due to keyboard ghosting,
     * `is_action_pressed` may return `false` even if one of the action's keys is pressed. See Input
     * examples ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in the documentation
     * for more information.
     *
     * Generated from Godot docs: Input.is_action_pressed
     */
    @JvmStatic
    fun isActionPressed(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionPressedBind, singleton, action, exactMatch)
    }

    /**
     * Returns `true` when the user has started pressing the action event in the current frame or
     * physics tick. It will only return `true` on the frame or tick that the user pressed down the
     * button. This is useful for code that needs to run only once when an action is pressed, instead
     * of every frame while it's pressed. If `exact_match` is `false`, it ignores additional input
     * modifiers for `InputEventKey` and `InputEventMouseButton` events, and the direction for
     * `InputEventJoypadMotion` events. Note: Returning `true` does not imply that the action is still
     * pressed. An action can be pressed and released again rapidly, and `true` will still be returned
     * so as not to miss input. Note: Due to keyboard ghosting, `is_action_just_pressed` may return
     * `false` even if one of the action's keys is pressed. See Input examples
     * ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in the documentation for more
     * information. Note: During input handling (e.g. `Node._input`), use
     * `InputEvent.is_action_pressed` instead to query the action state of the current event. See also
     * `is_action_just_pressed_by_event`.
     *
     * Generated from Godot docs: Input.is_action_just_pressed
     */
    @JvmStatic
    fun isActionJustPressed(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionJustPressedBind, singleton, action, exactMatch)
    }

    /**
     * Returns `true` when the user stops pressing the action event in the current frame or physics
     * tick. It will only return `true` on the frame or tick that the user releases the button. Note:
     * Returning `true` does not imply that the action is still not pressed. An action can be released
     * and pressed again rapidly, and `true` will still be returned so as not to miss input. If
     * `exact_match` is `false`, it ignores additional input modifiers for `InputEventKey` and
     * `InputEventMouseButton` events, and the direction for `InputEventJoypadMotion` events. Note:
     * During input handling (e.g. `Node._input`), use `InputEvent.is_action_released` instead to query
     * the action state of the current event. See also `is_action_just_released_by_event`.
     *
     * Generated from Godot docs: Input.is_action_just_released
     */
    @JvmStatic
    fun isActionJustReleased(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionJustReleasedBind, singleton, action, exactMatch)
    }

    /**
     * Returns `true` when the user has started pressing the action event in the current frame or
     * physics tick, and the first event that triggered action press in the current frame/physics tick
     * was `event`. It will only return `true` on the frame or tick that the user pressed down the
     * button. This is useful for code that needs to run only once when an action is pressed, and the
     * action is processed during input handling (e.g. `Node._input`). If `exact_match` is `false`, it
     * ignores additional input modifiers for `InputEventKey` and `InputEventMouseButton` events, and
     * the direction for `InputEventJoypadMotion` events. Note: Returning `true` does not imply that
     * the action is still pressed. An action can be pressed and released again rapidly, and `true`
     * will still be returned so as not to miss input. Note: Due to keyboard ghosting,
     * `is_action_just_pressed` may return `false` even if one of the action's keys is pressed. See
     * Input examples ($DOCS_URL/tutorials/inputs/input_examples.html#keyboard-events) in the
     * documentation for more information.
     *
     * Generated from Godot docs: Input.is_action_just_pressed_by_event
     */
    @JvmStatic
    fun isActionJustPressedByEvent(action: String, event: InputEvent, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameObjectAndBoolArgRetBool(isActionJustPressedByEventBind, singleton, action, event.requireOpenHandle(), exactMatch)
    }

    /**
     * Returns `true` when the user stops pressing the action event in the current frame or physics
     * tick, and the first event that triggered action release in the current frame/physics tick was
     * `event`. It will only return `true` on the frame or tick that the user releases the button. This
     * is useful when an action is processed during input handling (e.g. `Node._input`). Note:
     * Returning `true` does not imply that the action is still not pressed. An action can be released
     * and pressed again rapidly, and `true` will still be returned so as not to miss input. If
     * `exact_match` is `false`, it ignores additional input modifiers for `InputEventKey` and
     * `InputEventMouseButton` events, and the direction for `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: Input.is_action_just_released_by_event
     */
    @JvmStatic
    fun isActionJustReleasedByEvent(action: String, event: InputEvent, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameObjectAndBoolArgRetBool(isActionJustReleasedByEventBind, singleton, action, event.requireOpenHandle(), exactMatch)
    }

    /**
     * Returns a value between 0 and 1 representing the intensity of the given action. In a joypad, for
     * example, the further away the axis (analog sticks or L2, R2 triggers) is from the dead zone, the
     * closer the value will be to 1. If the action is mapped to a control that has no axis such as the
     * keyboard, the value returned will be 0 or 1. If `exact_match` is `false`, it ignores additional
     * input modifiers for `InputEventKey` and `InputEventMouseButton` events, and the direction for
     * `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: Input.get_action_strength
     */
    @JvmStatic
    fun getActionStrength(action: String, exactMatch: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(getActionStrengthBind, singleton, action, exactMatch)
    }

    /**
     * Returns a value between 0 and 1 representing the raw intensity of the given action, ignoring the
     * action's deadzone. In most cases, you should use `get_action_strength` instead. If `exact_match`
     * is `false`, it ignores additional input modifiers for `InputEventKey` and
     * `InputEventMouseButton` events, and the direction for `InputEventJoypadMotion` events.
     *
     * Generated from Godot docs: Input.get_action_raw_strength
     */
    @JvmStatic
    fun getActionRawStrength(action: String, exactMatch: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(getActionRawStrengthBind, singleton, action, exactMatch)
    }

    /**
     * Get axis input by specifying two actions, one negative and one positive. This is a shorthand for
     * writing `Input.get_action_strength("positive_action") -
     * Input.get_action_strength("negative_action")`.
     *
     * Generated from Godot docs: Input.get_axis
     */
    @JvmStatic
    fun getAxis(negativeAction: String, positiveAction: String): Double {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(getAxisBind, singleton, negativeAction, positiveAction)
    }

    /**
     * Gets an input vector by specifying four actions for the positive and negative X and Y axes. This
     * method is useful when getting vector input, such as from a joystick, directional pad, arrows, or
     * WASD. The vector has its length limited to 1 and has a circular deadzone, which is useful for
     * using vector input as movement. By default, the deadzone is automatically calculated from the
     * average of the action deadzones. However, you can override the deadzone to be whatever you want
     * (on the range of 0 to 1).
     *
     * Generated from Godot docs: Input.get_vector
     */
    @JvmStatic
    fun getVector(negativeX: String, positiveX: String, negativeY: String, positiveY: String, deadzone: Double = -1.0): Vector2 {
        return ObjectCalls.ptrcallWithFourStringNameAndFloatArgRetVector2(getVectorBind, singleton, negativeX, positiveX, negativeY, positiveY, deadzone)
    }

    /**
     * Adds a new mapping entry (in SDL2 format) to the mapping database. Optionally update already
     * connected devices.
     *
     * Generated from Godot docs: Input.add_joy_mapping
     */
    @JvmStatic
    fun addJoyMapping(mapping: String, updateExisting: Boolean = false) {
        ObjectCalls.ptrcallWithStringAndBoolArg(addJoyMappingBind, singleton, mapping, updateExisting)
    }

    /**
     * Removes all mappings from the internal database that match the given GUID. All currently
     * connected joypads that use this GUID will become unmapped. On Android, Godot will map to an
     * internal fallback mapping.
     *
     * Generated from Godot docs: Input.remove_joy_mapping
     */
    @JvmStatic
    fun removeJoyMapping(guid: String) {
        ObjectCalls.ptrcallWithStringArg(removeJoyMappingBind, singleton, guid)
    }

    /**
     * Returns `true` if the system knows the specified device. This means that it sets all button and
     * axis indices. Unknown joypads are not expected to match these constants, but you can still
     * retrieve events from them.
     *
     * Generated from Godot docs: Input.is_joy_known
     */
    @JvmStatic
    fun isJoyKnown(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyKnownBind, singleton, device)
    }

    /**
     * Returns the current value of the joypad axis at index `axis`.
     *
     * Generated from Godot docs: Input.get_joy_axis
     */
    @JvmStatic
    fun getJoyAxis(device: Int, axis: Long): Double {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetDouble(getJoyAxisBind, singleton, device, axis)
    }

    /**
     * Returns the name of the joypad at the specified device index, e.g. `PS4 Controller`. Godot uses
     * the SDL2 game controller database (https://github.com/gabomdq/SDL_GameControllerDB) to determine
     * gamepad names.
     *
     * Generated from Godot docs: Input.get_joy_name
     */
    @JvmStatic
    fun getJoyName(device: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getJoyNameBind, singleton, device)
    }

    /**
     * Returns an SDL-compatible device GUID on platforms that use gamepad remapping, e.g.
     * `030000004c050000c405000000010000`. Returns an empty string if it cannot be found. Godot uses
     * SDL's internal mappings, supplemented by community-contributed mappings, to determine gamepad
     * names and mappings based on this GUID. On Windows, all XInput joypad GUIDs will be overridden by
     * Godot to `__XINPUT_DEVICE__`, because their mappings are the same.
     *
     * Generated from Godot docs: Input.get_joy_guid
     */
    @JvmStatic
    fun getJoyGuid(device: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getJoyGuidBind, singleton, device)
    }

    /**
     * Returns a dictionary with extra platform-specific information about the device, e.g. the raw
     * gamepad name from the OS or the Steam Input index. On Windows, Linux, macOS, and iOS, the
     * dictionary contains the following fields: `raw_name`: The name of the controller as it came from
     * the OS, before getting renamed by the controller database. `vendor_id`: The USB vendor ID of the
     * device. `product_id`: The USB product ID of the device. `serial_number`: The serial number of
     * the device. This key won't be present if the serial number is unavailable. The dictionary can
     * also include the following fields under selected platforms: `steam_input_index`: The Steam Input
     * gamepad index (Windows, Linux, and macOS only). If the device is not a Steam Input device this
     * key won't be present. `xinput_index`: The index of the controller in the XInput system (Windows
     * only). This key won't be present for devices not handled by XInput. Note: The returned
     * dictionary is always empty on Android and Web.
     *
     * Generated from Godot docs: Input.get_joy_info
     */
    @JvmStatic
    fun getJoyInfo(device: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getJoyInfoBind, singleton, device)
    }

    /**
     * Queries whether an input device should be ignored or not. Devices can be ignored by setting the
     * environment variable `SDL_GAMECONTROLLER_IGNORE_DEVICES`. Read the SDL documentation
     * (https://wiki.libsdl.org/SDL2) for more information. Note: Some 3rd party tools can contribute
     * to the list of ignored devices. For example, SteamInput creates virtual devices from physical
     * devices for remapping purposes. To avoid handling the same input device twice, the original
     * device is added to the ignore list.
     *
     * Generated from Godot docs: Input.should_ignore_device
     */
    @JvmStatic
    fun shouldIgnoreDevice(vendorId: Int, productId: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(shouldIgnoreDeviceBind, singleton, vendorId, productId)
    }

    /**
     * Returns an `Array` containing the device IDs of all currently connected joypads. Note: The order
     * of connected joypads can not be guaranteed to be the same after a project and/or the editor is
     * restarted, because Godot doesn't save the order of joypad connections. Joypads are registered in
     * the order they are discovered by Godot.
     *
     * Generated from Godot docs: Input.get_connected_joypads
     */
    @JvmStatic
    fun getConnectedJoypads(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getConnectedJoypadsBind, singleton)
    }

    /**
     * Returns the strength of the joypad vibration: x is the strength of the weak motor, and y is the
     * strength of the strong motor. Note: This method returns the same values that were passed to
     * `start_joy_vibration`, and these values do not change when the joypad's vibration runs out, they
     * only get reset after a call to `stop_joy_vibration`. If you want to check if a joypad is still
     * vibrating, use `is_joy_vibrating` instead.
     *
     * Generated from Godot docs: Input.get_joy_vibration_strength
     */
    @JvmStatic
    fun getJoyVibrationStrength(device: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getJoyVibrationStrengthBind, singleton, device)
    }

    /**
     * Returns the duration of the current vibration effect in seconds. Note: This method returns the
     * same value that was passed to `start_joy_vibration`, and this value does not change when the
     * joypad's vibration runs out, it only gets reset after a call to `stop_joy_vibration`. If you
     * want to check if a joypad is still vibrating, use `is_joy_vibrating` instead.
     *
     * Generated from Godot docs: Input.get_joy_vibration_duration
     */
    @JvmStatic
    fun getJoyVibrationDuration(device: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJoyVibrationDurationBind, singleton, device)
    }

    /**
     * Returns the remaining duration of the current vibration effect in seconds.
     *
     * Generated from Godot docs: Input.get_joy_vibration_remaining_duration
     */
    @JvmStatic
    fun getJoyVibrationRemainingDuration(device: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJoyVibrationRemainingDurationBind, singleton, device)
    }

    /**
     * Returns `true` if the joypad is still vibrating after a call to `start_joy_vibration`. Unlike
     * `get_joy_vibration_strength` and `get_joy_vibration_duration`, this method returns `false` after
     * the joypad's vibration runs out.
     *
     * Generated from Godot docs: Input.is_joy_vibrating
     */
    @JvmStatic
    fun isJoyVibrating(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyVibratingBind, singleton, device)
    }

    /**
     * Returns `true` if the joypad supports vibration. See also `start_joy_vibration`. Note: For
     * macOS, vibration is only supported in macOS 11 and later. When connected via USB, vibration is
     * only supported for major brand controllers (except Xbox One and Xbox Series X/S controllers) due
     * to macOS limitations.
     *
     * Generated from Godot docs: Input.has_joy_vibration
     */
    @JvmStatic
    fun hasJoyVibration(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasJoyVibrationBind, singleton, device)
    }

    /**
     * Starts to vibrate the joypad. See also `has_joy_vibration` and `is_joy_vibrating`. Joypads
     * usually come with two rumble motors, a strong and a weak one. `weak_magnitude` is the strength
     * of the weak motor (between `0.0` and `1.0`). `strong_magnitude` is the strength of the strong
     * motor (between `0.0` and `1.0`). `duration` is the duration of the effect in seconds (a duration
     * of `0.0` will try to play the vibration as long as possible, which is about 65 seconds). The
     * vibration can be stopped early by calling `stop_joy_vibration`. See also
     * `get_joy_vibration_strength` and `get_joy_vibration_duration`. Note: For macOS, vibration is
     * only supported in macOS 11 and later. When connected via USB, vibration is only supported for
     * major brand controllers (except Xbox One and Xbox Series X/S controllers) due to macOS
     * limitations.
     *
     * Generated from Godot docs: Input.start_joy_vibration
     */
    @JvmStatic
    fun startJoyVibration(device: Int, weakMagnitude: Double, strongMagnitude: Double, duration: Double = 0.0) {
        ObjectCalls.ptrcallWithIntAndThreeDoubleArgs(startJoyVibrationBind, singleton, device, weakMagnitude, strongMagnitude, duration)
    }

    /**
     * Stops the vibration of the joypad started with `start_joy_vibration`.
     *
     * Generated from Godot docs: Input.stop_joy_vibration
     */
    @JvmStatic
    fun stopJoyVibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(stopJoyVibrationBind, singleton, device)
    }

    /**
     * Vibrate the handheld device for the specified duration in milliseconds. `amplitude` is the
     * strength of the vibration, as a value between `0.0` and `1.0`. If set to `-1.0`, the default
     * vibration strength of the device is used. Note: This method is implemented on Android, iOS, and
     * Web. It has no effect on other platforms. Note: For Android, `vibrate_handheld` requires
     * enabling the `VIBRATE` permission in the export preset. Otherwise, `vibrate_handheld` will have
     * no effect. Note: For iOS, specifying the duration is only supported in iOS 13 and later. Note:
     * For Web, the amplitude cannot be changed. Note: Some web browsers such as Safari and Firefox for
     * Android do not support `vibrate_handheld`. Note: Device settings such as vibration on/off, "do
     * not disturb" mode or specific haptic feedback on/off may prevent `vibrate_handheld` effects.
     *
     * Generated from Godot docs: Input.vibrate_handheld
     */
    @JvmStatic
    fun vibrateHandheld(durationMs: Int = 500, amplitude: Double = -1.0) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(vibrateHandheldBind, singleton, durationMs, amplitude)
    }

    /**
     * If `true`, joypad input (including motion sensors) and LED light changes will be ignored and
     * joypad vibration will be stopped when the application is not focused.
     *
     * Generated from Godot docs: Input.set_ignore_joypad_on_unfocused_application
     */
    @JvmStatic
    fun setIgnoreJoypadOnUnfocusedApplication(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreJoypadOnUnfocusedApplicationBind, singleton, enable)
    }

    /**
     * If `true`, joypad input (including motion sensors) and LED light changes will be ignored and
     * joypad vibration will be stopped when the application is not focused.
     *
     * Generated from Godot docs: Input.is_ignoring_joypad_on_unfocused_application
     */
    @JvmStatic
    fun isIgnoringJoypadOnUnfocusedApplication(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringJoypadOnUnfocusedApplicationBind, singleton)
    }

    /**
     * Returns the gravity in m/s² of the device's accelerometer sensor, if the device has one.
     * Otherwise, the method returns `Vector3.ZERO`. Note: This method only works on Android and iOS.
     * On other platforms, it always returns `Vector3.ZERO`. Note: For Android,
     * `ProjectSettings.input_devices/sensors/enable_gravity` must be enabled.
     *
     * Generated from Godot docs: Input.get_gravity
     */
    @JvmStatic
    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, singleton)
    }

    /**
     * Returns the acceleration in m/s² of the device's accelerometer sensor, if the device has one.
     * Otherwise, the method returns `Vector3.ZERO`. Note this method returns an empty `Vector3` when
     * running from the editor even when your device has an accelerometer. You must export your project
     * to a supported device to read values from the accelerometer. Note: This method only works on
     * Android and iOS. On other platforms, it always returns `Vector3.ZERO`. Note: For Android,
     * `ProjectSettings.input_devices/sensors/enable_accelerometer` must be enabled.
     *
     * Generated from Godot docs: Input.get_accelerometer
     */
    @JvmStatic
    fun getAccelerometer(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getAccelerometerBind, singleton)
    }

    /**
     * Returns the magnetic field strength in micro-Tesla for all axes of the device's magnetometer
     * sensor, if the device has one. Otherwise, the method returns `Vector3.ZERO`. Note: This method
     * only works on Android and iOS. On other platforms, it always returns `Vector3.ZERO`. Note: For
     * Android, `ProjectSettings.input_devices/sensors/enable_magnetometer` must be enabled.
     *
     * Generated from Godot docs: Input.get_magnetometer
     */
    @JvmStatic
    fun getMagnetometer(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getMagnetometerBind, singleton)
    }

    /**
     * Returns the rotation rate in rad/s around a device's X, Y, and Z axes of the gyroscope sensor,
     * if the device has one. Otherwise, the method returns `Vector3.ZERO`. Note: This method only
     * works on Android and iOS. On other platforms, it always returns `Vector3.ZERO`. Note: For
     * Android, `ProjectSettings.input_devices/sensors/enable_gyroscope` must be enabled.
     *
     * Generated from Godot docs: Input.get_gyroscope
     */
    @JvmStatic
    fun getGyroscope(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGyroscopeBind, singleton)
    }

    /**
     * Returns the acceleration, including the force of gravity, in m/s² of the joypad's accelerometer
     * sensor, if the joypad has one and it's currently enabled. Otherwise, the method returns
     * `Vector3.ZERO`. See also `get_joy_gravity` and `set_joy_motion_sensors_enabled`. For a joypad
     * held in front of you, the returned axes are defined as follows: +X ... -X: left ... right; +Y
     * ... -Y: bottom ... top; +Z ... -Z: farther ... closer. The gravity part value is measured as a
     * vector with length of `9.8` away from the center of the Earth, which is a negative Y value.
     * Note: This feature is only supported on Windows, Linux, and macOS. On iOS, joypad accelerometer
     * sensor reading is not supported due to OS limitations.
     *
     * Generated from Godot docs: Input.get_joy_accelerometer
     */
    @JvmStatic
    fun getJoyAccelerometer(device: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getJoyAccelerometerBind, singleton, device)
    }

    /**
     * Returns the gravity in m/s² of the joypad's accelerometer sensor, if the joypad has one and it's
     * currently enabled. Otherwise, the method returns `Vector3.ZERO`. See also
     * `get_joy_accelerometer` and `set_joy_motion_sensors_enabled`. For a joypad held in front of you,
     * the returned axes are defined as follows: +X ... -X: left ... right; +Y ... -Y: bottom ... top;
     * +Z ... -Z: farther ... closer. The gravity part value is measured as a vector with length of
     * `9.8` away from the center of the Earth, which is a negative Y value. Note: This feature is only
     * supported on Windows, Linux, and macOS. On iOS, joypad accelerometer sensor reading is not
     * supported due to OS limitations.
     *
     * Generated from Godot docs: Input.get_joy_gravity
     */
    @JvmStatic
    fun getJoyGravity(device: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getJoyGravityBind, singleton, device)
    }

    /**
     * Returns the rotation rate in rad/s around a joypad's X, Y, and Z axes of the gyroscope sensor,
     * if the joypad has one and it's currently enabled. Otherwise, the method returns `Vector3.ZERO`.
     * See also `set_joy_motion_sensors_enabled`. The rotation is positive in the counter-clockwise
     * direction. For a joypad held in front of you, the returned axes are defined as follows: X:
     * Angular speed around the X axis (pitch); Y: Angular speed around the Y axis (yaw); Z: Angular
     * speed around the Z axis (roll). See `start_joy_motion_sensors_calibration` for an example on how
     * to use joypad gyroscope and gyroscope calibration in your games. Note: This feature is only
     * supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.get_joy_gyroscope
     */
    @JvmStatic
    fun getJoyGyroscope(device: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getJoyGyroscopeBind, singleton, device)
    }

    /**
     * Returns the joypad's motion sensor rate in Hz, if the joypad has motion sensors and they're
     * currently enabled. See also `set_joy_motion_sensors_enabled`. Note: This feature is only
     * supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.get_joy_motion_sensors_rate
     */
    @JvmStatic
    fun getJoyMotionSensorsRate(device: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJoyMotionSensorsRateBind, singleton, device)
    }

    /**
     * Returns `true` if the requested joypad has motion sensors (accelerometer and gyroscope) and they
     * are currently enabled. See also `set_joy_motion_sensors_enabled` and `has_joy_motion_sensors`.
     * See `start_joy_motion_sensors_calibration` for an example on how to use joypad motion sensors
     * and calibration in your games. Note: This feature is only supported on Windows, Linux, macOS,
     * and iOS.
     *
     * Generated from Godot docs: Input.is_joy_motion_sensors_enabled
     */
    @JvmStatic
    fun isJoyMotionSensorsEnabled(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyMotionSensorsEnabledBind, singleton, device)
    }

    /**
     * Enables or disables the motion sensors (accelerometer and gyroscope), if available, on the
     * specified joypad. See `start_joy_motion_sensors_calibration` for an example on how to use joypad
     * motion sensors and calibration in your games. It's recommended to disable the motion sensors
     * when they're no longer being used, because otherwise it might drain the controller battery
     * faster. Note: This feature is only supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.set_joy_motion_sensors_enabled
     */
    @JvmStatic
    fun setJoyMotionSensorsEnabled(device: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setJoyMotionSensorsEnabledBind, singleton, device, enable)
    }

    /**
     * Returns `true` if the joypad has motion sensors (accelerometer and gyroscope). Note: On iOS,
     * joypad accelerometer sensor reading is not supported due to OS limitations. Note: This feature
     * is only supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.has_joy_motion_sensors
     */
    @JvmStatic
    fun hasJoyMotionSensors(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasJoyMotionSensorsBind, singleton, device)
    }

    /**
     * Starts the process of calibrating the specified joypad's gyroscope, if it has one. Once a
     * joypad's gyroscope has been calibrated correctly (e.g. laying still on a table without being
     * rotated), `get_joy_gyroscope` will return values close or equal to `Vector3.ZERO` when the
     * joypad is not being rotated. Here's an example of how to use joypad gyroscope and gyroscope
     * calibration in your games:
     *
     * Generated from Godot docs: Input.start_joy_motion_sensors_calibration
     */
    @JvmStatic
    fun startJoyMotionSensorsCalibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(startJoyMotionSensorsCalibrationBind, singleton, device)
    }

    /**
     * Stops the calibration process of the specified joypad's motion sensors. See
     * `start_joy_motion_sensors_calibration` for an example on how to use joypad motion sensors and
     * calibration in your games. Note: This feature is only supported on Windows, Linux, macOS, and
     * iOS.
     *
     * Generated from Godot docs: Input.stop_joy_motion_sensors_calibration
     */
    @JvmStatic
    fun stopJoyMotionSensorsCalibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(stopJoyMotionSensorsCalibrationBind, singleton, device)
    }

    /**
     * Clears the calibration information for the specified joypad's motion sensors, if it has any and
     * if they were calibrated. See `start_joy_motion_sensors_calibration` for an example on how to use
     * joypad motion sensors and calibration in your games. Note: This feature is only supported on
     * Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.clear_joy_motion_sensors_calibration
     */
    @JvmStatic
    fun clearJoyMotionSensorsCalibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(clearJoyMotionSensorsCalibrationBind, singleton, device)
    }

    /**
     * Returns the calibration information about the specified joypad's motion sensors in the form of a
     * `Dictionary`, if it has any and if they have been calibrated, otherwise returns an empty
     * `Dictionary`. The dictionary contains the following fields: `gyroscope_offset`: average offset
     * in gyroscope values from `Vector2.ZERO` in rad/s. See `start_joy_motion_sensors_calibration` for
     * an example on how to use joypad motion sensors and calibration in your games. Note: This feature
     * is only supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.get_joy_motion_sensors_calibration
     */
    @JvmStatic
    fun getJoyMotionSensorsCalibration(device: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getJoyMotionSensorsCalibrationBind, singleton, device)
    }

    /**
     * Sets the specified joypad's calibration information. See also
     * `get_joy_motion_sensors_calibration`. See `start_joy_motion_sensors_calibration` for an example
     * on how to use joypad motion sensors and calibration in your games. Note: This feature is only
     * supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.set_joy_motion_sensors_calibration
     */
    @JvmStatic
    fun setJoyMotionSensorsCalibration(device: Int, calibrationInfo: Map<String, Any?>) {
        ObjectCalls.ptrcallWithIntAndDictionaryArg(setJoyMotionSensorsCalibrationBind, singleton, device, calibrationInfo)
    }

    /**
     * Returns `true` if the joypad's motion sensors have been calibrated. See
     * `start_joy_motion_sensors_calibration` for an example on how to use joypad motion sensors and
     * calibration in your games. Note: This feature is only supported on Windows, Linux, macOS, and
     * iOS.
     *
     * Generated from Godot docs: Input.is_joy_motion_sensors_calibrated
     */
    @JvmStatic
    fun isJoyMotionSensorsCalibrated(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyMotionSensorsCalibratedBind, singleton, device)
    }

    /**
     * Returns `true` if the joypad's motion sensors are currently being calibrated. See
     * `start_joy_motion_sensors_calibration` for an example on how to use joypad motion sensors and
     * calibration in your games. Note: This feature is only supported on Windows, Linux, macOS, and
     * iOS.
     *
     * Generated from Godot docs: Input.is_joy_motion_sensors_calibrating
     */
    @JvmStatic
    fun isJoyMotionSensorsCalibrating(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyMotionSensorsCalibratingBind, singleton, device)
    }

    /**
     * Sets the gravity value of the accelerometer sensor. Can be used for debugging on devices without
     * a hardware sensor, for example in an editor on a PC. Note: This value can be immediately
     * overwritten by the hardware sensor value on Android and iOS.
     *
     * Generated from Godot docs: Input.set_gravity
     */
    @JvmStatic
    fun setGravity(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityBind, singleton, value)
    }

    /**
     * Sets the acceleration value of the accelerometer sensor. Can be used for debugging on devices
     * without a hardware sensor, for example in an editor on a PC. Note: This value can be immediately
     * overwritten by the hardware sensor value on Android and iOS.
     *
     * Generated from Godot docs: Input.set_accelerometer
     */
    @JvmStatic
    fun setAccelerometer(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setAccelerometerBind, singleton, value)
    }

    /**
     * Sets the value of the magnetic field of the magnetometer sensor. Can be used for debugging on
     * devices without a hardware sensor, for example in an editor on a PC. Note: This value can be
     * immediately overwritten by the hardware sensor value on Android and iOS.
     *
     * Generated from Godot docs: Input.set_magnetometer
     */
    @JvmStatic
    fun setMagnetometer(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setMagnetometerBind, singleton, value)
    }

    /**
     * Sets the value of the rotation rate of the gyroscope sensor. Can be used for debugging on
     * devices without a hardware sensor, for example in an editor on a PC. Note: This value can be
     * immediately overwritten by the hardware sensor value on Android and iOS.
     *
     * Generated from Godot docs: Input.set_gyroscope
     */
    @JvmStatic
    fun setGyroscope(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGyroscopeBind, singleton, value)
    }

    /**
     * Sets the joypad's LED light, if available, to the specified color. See also `has_joy_light`.
     * Note: There is no way to get the color of the light from a joypad. If you need to know the
     * assigned color, store it separately. Note: This feature is only supported on Windows, Linux,
     * macOS, and iOS.
     *
     * Generated from Godot docs: Input.set_joy_light
     */
    @JvmStatic
    fun setJoyLight(device: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setJoyLightBind, singleton, device, color)
    }

    /**
     * Returns `true` if the joypad has an LED light that can change colors and/or brightness. See also
     * `set_joy_light`. Note: This feature is only supported on Windows, Linux, macOS, and iOS.
     *
     * Generated from Godot docs: Input.has_joy_light
     */
    @JvmStatic
    fun hasJoyLight(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasJoyLightBind, singleton, device)
    }

    /**
     * Returns the last mouse velocity. To provide a precise and jitter-free velocity, mouse velocity
     * is only calculated every 0.1s. Therefore, mouse velocity will lag mouse movements.
     *
     * Generated from Godot docs: Input.get_last_mouse_velocity
     */
    @JvmStatic
    fun getLastMouseVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLastMouseVelocityBind, singleton)
    }

    /**
     * Returns the last mouse velocity in screen coordinates. To provide a precise and jitter-free
     * velocity, mouse velocity is only calculated every 0.1s. Therefore, mouse velocity will lag mouse
     * movements.
     *
     * Generated from Godot docs: Input.get_last_mouse_screen_velocity
     */
    @JvmStatic
    fun getLastMouseScreenVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLastMouseScreenVelocityBind, singleton)
    }

    /**
     * Returns mouse buttons as a bitmask. If multiple mouse buttons are pressed at the same time, the
     * bits are added together. Equivalent to `DisplayServer.mouse_get_button_state`.
     *
     * Generated from Godot docs: Input.get_mouse_button_mask
     */
    @JvmStatic
    fun getMouseButtonMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseButtonMaskBind, singleton)
    }

    /**
     * Controls the mouse mode.
     *
     * Generated from Godot docs: Input.set_mouse_mode
     */
    @JvmStatic
    fun setMouseMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMouseModeBind, singleton, mode)
    }

    /**
     * Controls the mouse mode.
     *
     * Generated from Godot docs: Input.get_mouse_mode
     */
    @JvmStatic
    fun getMouseMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseModeBind, singleton)
    }

    /**
     * Sets the mouse position to the specified vector, provided in pixels and relative to an origin at
     * the upper left corner of the currently focused Window Manager game window. Mouse position is
     * clipped to the limits of the screen resolution, or to the limits of the game window if
     * `MouseMode` is set to `MOUSE_MODE_CONFINED` or `MOUSE_MODE_CONFINED_HIDDEN`. Note: `warp_mouse`
     * is only supported on Windows, macOS and Linux. It has no effect on Android, iOS and Web.
     *
     * Generated from Godot docs: Input.warp_mouse
     */
    @JvmStatic
    fun warpMouse(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(warpMouseBind, singleton, position)
    }

    /**
     * This will simulate pressing the specified action. The strength can be used for non-boolean
     * actions, it's ranged between 0 and 1 representing the intensity of the given action. Note: This
     * method will not cause any `Node._input` calls. It is intended to be used with
     * `is_action_pressed` and `is_action_just_pressed`. If you want to simulate `_input`, use
     * `parse_input_event` instead.
     *
     * Generated from Godot docs: Input.action_press
     */
    @JvmStatic
    fun actionPress(action: String, strength: Double = 1.0) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(actionPressBind, singleton, action, strength)
    }

    /**
     * If the specified action is already pressed, this will release it.
     *
     * Generated from Godot docs: Input.action_release
     */
    @JvmStatic
    fun actionRelease(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(actionReleaseBind, singleton, action)
    }

    /**
     * Sets the default cursor shape to be used in the viewport instead of `CURSOR_ARROW`. Note: If you
     * want to change the default cursor shape for `Control`'s nodes, use
     * `Control.mouse_default_cursor_shape` instead. Note: This method generates an
     * `InputEventMouseMotion` to update cursor immediately.
     *
     * Generated from Godot docs: Input.set_default_cursor_shape
     */
    @JvmStatic
    fun setDefaultCursorShape(shape: Long = 0L) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCursorShapeBind, singleton, shape)
    }

    /**
     * Returns the currently assigned cursor shape.
     *
     * Generated from Godot docs: Input.get_current_cursor_shape
     */
    @JvmStatic
    fun getCurrentCursorShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCurrentCursorShapeBind, singleton)
    }

    /**
     * Sets a custom mouse cursor image, which is only visible inside the game window, for the given
     * mouse `shape`. The hotspot can also be specified. Passing `null` to the image parameter resets
     * to the system cursor. `image` can be either `Texture2D` or `Image` and its size must be lower
     * than or equal to 256×256. To avoid rendering issues, sizes lower than or equal to 128×128 are
     * recommended. `hotspot` must be within `image`'s size. Note: `AnimatedTexture`s aren't supported
     * as custom mouse cursors. If using an `AnimatedTexture`, only the first frame will be displayed.
     * Note: The Lossless, Lossy or Uncompressed compression modes are recommended. The Video RAM
     * compression mode can be used, but it will be decompressed on the CPU, which means loading times
     * are slowed down and no memory is saved compared to lossless modes. Note: On the web platform,
     * the maximum allowed cursor image size is 128×128. Cursor images larger than 32×32 will also only
     * be displayed if the mouse cursor image is entirely located within the page for security reasons
     * (https://chromestatus.com/feature/5825971391299584).
     *
     * Generated from Godot docs: Input.set_custom_mouse_cursor
     */
    @JvmStatic
    fun setCustomMouseCursor(image: Resource?, shape: Long = 0L, hotspot: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithObjectLongAndVector2Arg(setCustomMouseCursorBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL, shape, hotspot)
    }

    /**
     * Feeds an `InputEvent` to the game. Can be used to artificially trigger input events from code.
     * Also generates `Node._input` calls.
     *
     * Generated from Godot docs: Input.parse_input_event
     */
    @JvmStatic
    fun parseInputEvent(event: InputEvent) {
        ObjectCalls.ptrcallWithObjectArgs(parseInputEventBind, singleton, listOf(event.requireOpenHandle()))
    }

    /**
     * If `true`, similar input events sent by the operating system are accumulated. When input
     * accumulation is enabled, all input events generated during a frame will be merged and emitted
     * when the frame is done rendering. Therefore, this limits the number of input method calls per
     * second to the rendering FPS. Input accumulation can be disabled to get slightly more
     * precise/reactive input at the cost of increased CPU usage. In applications where drawing
     * freehand lines is required, input accumulation should generally be disabled while the user is
     * drawing the line to get results that closely follow the actual input. Note: Input accumulation
     * is enabled by default.
     *
     * Generated from Godot docs: Input.set_use_accumulated_input
     */
    @JvmStatic
    fun setUseAccumulatedInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAccumulatedInputBind, singleton, enable)
    }

    /**
     * If `true`, similar input events sent by the operating system are accumulated. When input
     * accumulation is enabled, all input events generated during a frame will be merged and emitted
     * when the frame is done rendering. Therefore, this limits the number of input method calls per
     * second to the rendering FPS. Input accumulation can be disabled to get slightly more
     * precise/reactive input at the cost of increased CPU usage. In applications where drawing
     * freehand lines is required, input accumulation should generally be disabled while the user is
     * drawing the line to get results that closely follow the actual input. Note: Input accumulation
     * is enabled by default.
     *
     * Generated from Godot docs: Input.is_using_accumulated_input
     */
    @JvmStatic
    fun isUsingAccumulatedInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingAccumulatedInputBind, singleton)
    }

    /**
     * Sends all input events which are in the current buffer to the game loop. These events may have
     * been buffered as a result of accumulated input (`use_accumulated_input`) or agile input flushing
     * (`ProjectSettings.input_devices/buffering/agile_event_flushing`). The engine will already do
     * this itself at key execution points (at least once per frame). However, this can be useful in
     * advanced cases where you want precise control over the timing of event handling.
     *
     * Generated from Godot docs: Input.flush_buffered_events
     */
    @JvmStatic
    fun flushBufferedEvents() {
        ObjectCalls.ptrcallNoArgs(flushBufferedEventsBind, singleton)
    }

    /**
     * If `true`, sends mouse input events when tapping or swiping on the touchscreen. See also
     * `ProjectSettings.input_devices/pointing/emulate_mouse_from_touch`.
     *
     * Generated from Godot docs: Input.set_emulate_mouse_from_touch
     */
    @JvmStatic
    fun setEmulateMouseFromTouch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmulateMouseFromTouchBind, singleton, enable)
    }

    /**
     * If `true`, sends mouse input events when tapping or swiping on the touchscreen. See also
     * `ProjectSettings.input_devices/pointing/emulate_mouse_from_touch`.
     *
     * Generated from Godot docs: Input.is_emulating_mouse_from_touch
     */
    @JvmStatic
    fun isEmulatingMouseFromTouch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmulatingMouseFromTouchBind, singleton)
    }

    /**
     * If `true`, sends touch input events when clicking or dragging the mouse. See also
     * `ProjectSettings.input_devices/pointing/emulate_touch_from_mouse`.
     *
     * Generated from Godot docs: Input.set_emulate_touch_from_mouse
     */
    @JvmStatic
    fun setEmulateTouchFromMouse(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmulateTouchFromMouseBind, singleton, enable)
    }

    /**
     * If `true`, sends touch input events when clicking or dragging the mouse. See also
     * `ProjectSettings.input_devices/pointing/emulate_touch_from_mouse`.
     *
     * Generated from Godot docs: Input.is_emulating_touch_from_mouse
     */
    @JvmStatic
    fun isEmulatingTouchFromMouse(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmulatingTouchFromMouseBind, singleton)
    }

    object Signals {
        const val joyConnectionChanged: String = "joy_connection_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Input? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Input? =
        if (handle.address() == 0L) null else this

    private const val IS_ANYTHING_PRESSED_HASH = 36873697L
    private val isAnythingPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_anything_pressed", IS_ANYTHING_PRESSED_HASH)
    }

    private const val IS_KEY_PRESSED_HASH = 1938909964L
    private val isKeyPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_key_pressed", IS_KEY_PRESSED_HASH)
    }

    private const val IS_PHYSICAL_KEY_PRESSED_HASH = 1938909964L
    private val isPhysicalKeyPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_physical_key_pressed", IS_PHYSICAL_KEY_PRESSED_HASH)
    }

    private const val IS_KEY_LABEL_PRESSED_HASH = 1938909964L
    private val isKeyLabelPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_key_label_pressed", IS_KEY_LABEL_PRESSED_HASH)
    }

    private const val IS_MOUSE_BUTTON_PRESSED_HASH = 1821097125L
    private val isMouseButtonPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_mouse_button_pressed", IS_MOUSE_BUTTON_PRESSED_HASH)
    }

    private const val IS_JOY_BUTTON_PRESSED_HASH = 787208542L
    private val isJoyButtonPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_joy_button_pressed", IS_JOY_BUTTON_PRESSED_HASH)
    }

    private const val IS_ACTION_PRESSED_HASH = 1558498928L
    private val isActionPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_action_pressed", IS_ACTION_PRESSED_HASH)
    }

    private const val IS_ACTION_JUST_PRESSED_HASH = 1558498928L
    private val isActionJustPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_action_just_pressed", IS_ACTION_JUST_PRESSED_HASH)
    }

    private const val IS_ACTION_JUST_RELEASED_HASH = 1558498928L
    private val isActionJustReleasedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_action_just_released", IS_ACTION_JUST_RELEASED_HASH)
    }

    private const val IS_ACTION_JUST_PRESSED_BY_EVENT_HASH = 551972873L
    private val isActionJustPressedByEventBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_action_just_pressed_by_event", IS_ACTION_JUST_PRESSED_BY_EVENT_HASH)
    }

    private const val IS_ACTION_JUST_RELEASED_BY_EVENT_HASH = 551972873L
    private val isActionJustReleasedByEventBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_action_just_released_by_event", IS_ACTION_JUST_RELEASED_BY_EVENT_HASH)
    }

    private const val GET_ACTION_STRENGTH_HASH = 801543509L
    private val getActionStrengthBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_action_strength", GET_ACTION_STRENGTH_HASH)
    }

    private const val GET_ACTION_RAW_STRENGTH_HASH = 801543509L
    private val getActionRawStrengthBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_action_raw_strength", GET_ACTION_RAW_STRENGTH_HASH)
    }

    private const val GET_AXIS_HASH = 1958752504L
    private val getAxisBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_axis", GET_AXIS_HASH)
    }

    private const val GET_VECTOR_HASH = 2479607902L
    private val getVectorBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_vector", GET_VECTOR_HASH)
    }

    private const val ADD_JOY_MAPPING_HASH = 1168363258L
    private val addJoyMappingBind by lazy {
        ObjectCalls.getMethodBind("Input", "add_joy_mapping", ADD_JOY_MAPPING_HASH)
    }

    private const val REMOVE_JOY_MAPPING_HASH = 83702148L
    private val removeJoyMappingBind by lazy {
        ObjectCalls.getMethodBind("Input", "remove_joy_mapping", REMOVE_JOY_MAPPING_HASH)
    }

    private const val IS_JOY_KNOWN_HASH = 3067735520L
    private val isJoyKnownBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_joy_known", IS_JOY_KNOWN_HASH)
    }

    private const val GET_JOY_AXIS_HASH = 4063175957L
    private val getJoyAxisBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_axis", GET_JOY_AXIS_HASH)
    }

    private const val GET_JOY_NAME_HASH = 990163283L
    private val getJoyNameBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_name", GET_JOY_NAME_HASH)
    }

    private const val GET_JOY_GUID_HASH = 844755477L
    private val getJoyGuidBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_guid", GET_JOY_GUID_HASH)
    }

    private const val GET_JOY_INFO_HASH = 3485342025L
    private val getJoyInfoBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_info", GET_JOY_INFO_HASH)
    }

    private const val SHOULD_IGNORE_DEVICE_HASH = 2522259332L
    private val shouldIgnoreDeviceBind by lazy {
        ObjectCalls.getMethodBind("Input", "should_ignore_device", SHOULD_IGNORE_DEVICE_HASH)
    }

    private const val GET_CONNECTED_JOYPADS_HASH = 2915620761L
    private val getConnectedJoypadsBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_connected_joypads", GET_CONNECTED_JOYPADS_HASH)
    }

    private const val GET_JOY_VIBRATION_STRENGTH_HASH = 3114997196L
    private val getJoyVibrationStrengthBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_vibration_strength", GET_JOY_VIBRATION_STRENGTH_HASH)
    }

    private const val GET_JOY_VIBRATION_DURATION_HASH = 4025615559L
    private val getJoyVibrationDurationBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_vibration_duration", GET_JOY_VIBRATION_DURATION_HASH)
    }

    private const val GET_JOY_VIBRATION_REMAINING_DURATION_HASH = 4025615559L
    private val getJoyVibrationRemainingDurationBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_vibration_remaining_duration", GET_JOY_VIBRATION_REMAINING_DURATION_HASH)
    }

    private const val IS_JOY_VIBRATING_HASH = 3067735520L
    private val isJoyVibratingBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_joy_vibrating", IS_JOY_VIBRATING_HASH)
    }

    private const val HAS_JOY_VIBRATION_HASH = 1116898809L
    private val hasJoyVibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "has_joy_vibration", HAS_JOY_VIBRATION_HASH)
    }

    private const val START_JOY_VIBRATION_HASH = 2576575033L
    private val startJoyVibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "start_joy_vibration", START_JOY_VIBRATION_HASH)
    }

    private const val STOP_JOY_VIBRATION_HASH = 1286410249L
    private val stopJoyVibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "stop_joy_vibration", STOP_JOY_VIBRATION_HASH)
    }

    private const val VIBRATE_HANDHELD_HASH = 544894297L
    private val vibrateHandheldBind by lazy {
        ObjectCalls.getMethodBind("Input", "vibrate_handheld", VIBRATE_HANDHELD_HASH)
    }

    private const val SET_IGNORE_JOYPAD_ON_UNFOCUSED_APPLICATION_HASH = 2586408642L
    private val setIgnoreJoypadOnUnfocusedApplicationBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_ignore_joypad_on_unfocused_application", SET_IGNORE_JOYPAD_ON_UNFOCUSED_APPLICATION_HASH)
    }

    private const val IS_IGNORING_JOYPAD_ON_UNFOCUSED_APPLICATION_HASH = 36873697L
    private val isIgnoringJoypadOnUnfocusedApplicationBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_ignoring_joypad_on_unfocused_application", IS_IGNORING_JOYPAD_ON_UNFOCUSED_APPLICATION_HASH)
    }

    private const val GET_GRAVITY_HASH = 3360562783L
    private val getGravityBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_gravity", GET_GRAVITY_HASH)
    }

    private const val GET_ACCELEROMETER_HASH = 3360562783L
    private val getAccelerometerBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_accelerometer", GET_ACCELEROMETER_HASH)
    }

    private const val GET_MAGNETOMETER_HASH = 3360562783L
    private val getMagnetometerBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_magnetometer", GET_MAGNETOMETER_HASH)
    }

    private const val GET_GYROSCOPE_HASH = 3360562783L
    private val getGyroscopeBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_gyroscope", GET_GYROSCOPE_HASH)
    }

    private const val GET_JOY_ACCELEROMETER_HASH = 711720468L
    private val getJoyAccelerometerBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_accelerometer", GET_JOY_ACCELEROMETER_HASH)
    }

    private const val GET_JOY_GRAVITY_HASH = 711720468L
    private val getJoyGravityBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_gravity", GET_JOY_GRAVITY_HASH)
    }

    private const val GET_JOY_GYROSCOPE_HASH = 711720468L
    private val getJoyGyroscopeBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_gyroscope", GET_JOY_GYROSCOPE_HASH)
    }

    private const val GET_JOY_MOTION_SENSORS_RATE_HASH = 2339986948L
    private val getJoyMotionSensorsRateBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_motion_sensors_rate", GET_JOY_MOTION_SENSORS_RATE_HASH)
    }

    private const val IS_JOY_MOTION_SENSORS_ENABLED_HASH = 1116898809L
    private val isJoyMotionSensorsEnabledBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_joy_motion_sensors_enabled", IS_JOY_MOTION_SENSORS_ENABLED_HASH)
    }

    private const val SET_JOY_MOTION_SENSORS_ENABLED_HASH = 300928843L
    private val setJoyMotionSensorsEnabledBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_joy_motion_sensors_enabled", SET_JOY_MOTION_SENSORS_ENABLED_HASH)
    }

    private const val HAS_JOY_MOTION_SENSORS_HASH = 1116898809L
    private val hasJoyMotionSensorsBind by lazy {
        ObjectCalls.getMethodBind("Input", "has_joy_motion_sensors", HAS_JOY_MOTION_SENSORS_HASH)
    }

    private const val START_JOY_MOTION_SENSORS_CALIBRATION_HASH = 1286410249L
    private val startJoyMotionSensorsCalibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "start_joy_motion_sensors_calibration", START_JOY_MOTION_SENSORS_CALIBRATION_HASH)
    }

    private const val STOP_JOY_MOTION_SENSORS_CALIBRATION_HASH = 1286410249L
    private val stopJoyMotionSensorsCalibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "stop_joy_motion_sensors_calibration", STOP_JOY_MOTION_SENSORS_CALIBRATION_HASH)
    }

    private const val CLEAR_JOY_MOTION_SENSORS_CALIBRATION_HASH = 1286410249L
    private val clearJoyMotionSensorsCalibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "clear_joy_motion_sensors_calibration", CLEAR_JOY_MOTION_SENSORS_CALIBRATION_HASH)
    }

    private const val GET_JOY_MOTION_SENSORS_CALIBRATION_HASH = 3485342025L
    private val getJoyMotionSensorsCalibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_joy_motion_sensors_calibration", GET_JOY_MOTION_SENSORS_CALIBRATION_HASH)
    }

    private const val SET_JOY_MOTION_SENSORS_CALIBRATION_HASH = 64545446L
    private val setJoyMotionSensorsCalibrationBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_joy_motion_sensors_calibration", SET_JOY_MOTION_SENSORS_CALIBRATION_HASH)
    }

    private const val IS_JOY_MOTION_SENSORS_CALIBRATED_HASH = 1116898809L
    private val isJoyMotionSensorsCalibratedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_joy_motion_sensors_calibrated", IS_JOY_MOTION_SENSORS_CALIBRATED_HASH)
    }

    private const val IS_JOY_MOTION_SENSORS_CALIBRATING_HASH = 1116898809L
    private val isJoyMotionSensorsCalibratingBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_joy_motion_sensors_calibrating", IS_JOY_MOTION_SENSORS_CALIBRATING_HASH)
    }

    private const val SET_GRAVITY_HASH = 3460891852L
    private val setGravityBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_gravity", SET_GRAVITY_HASH)
    }

    private const val SET_ACCELEROMETER_HASH = 3460891852L
    private val setAccelerometerBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_accelerometer", SET_ACCELEROMETER_HASH)
    }

    private const val SET_MAGNETOMETER_HASH = 3460891852L
    private val setMagnetometerBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_magnetometer", SET_MAGNETOMETER_HASH)
    }

    private const val SET_GYROSCOPE_HASH = 3460891852L
    private val setGyroscopeBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_gyroscope", SET_GYROSCOPE_HASH)
    }

    private const val SET_JOY_LIGHT_HASH = 2878471219L
    private val setJoyLightBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_joy_light", SET_JOY_LIGHT_HASH)
    }

    private const val HAS_JOY_LIGHT_HASH = 1116898809L
    private val hasJoyLightBind by lazy {
        ObjectCalls.getMethodBind("Input", "has_joy_light", HAS_JOY_LIGHT_HASH)
    }

    private const val GET_LAST_MOUSE_VELOCITY_HASH = 1497962370L
    private val getLastMouseVelocityBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_last_mouse_velocity", GET_LAST_MOUSE_VELOCITY_HASH)
    }

    private const val GET_LAST_MOUSE_SCREEN_VELOCITY_HASH = 1497962370L
    private val getLastMouseScreenVelocityBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_last_mouse_screen_velocity", GET_LAST_MOUSE_SCREEN_VELOCITY_HASH)
    }

    private const val GET_MOUSE_BUTTON_MASK_HASH = 2512161324L
    private val getMouseButtonMaskBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_mouse_button_mask", GET_MOUSE_BUTTON_MASK_HASH)
    }

    private const val SET_MOUSE_MODE_HASH = 2228490894L
    private val setMouseModeBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_mouse_mode", SET_MOUSE_MODE_HASH)
    }

    private const val GET_MOUSE_MODE_HASH = 965286182L
    private val getMouseModeBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_mouse_mode", GET_MOUSE_MODE_HASH)
    }

    private const val WARP_MOUSE_HASH = 743155724L
    private val warpMouseBind by lazy {
        ObjectCalls.getMethodBind("Input", "warp_mouse", WARP_MOUSE_HASH)
    }

    private const val ACTION_PRESS_HASH = 1713091165L
    private val actionPressBind by lazy {
        ObjectCalls.getMethodBind("Input", "action_press", ACTION_PRESS_HASH)
    }

    private const val ACTION_RELEASE_HASH = 3304788590L
    private val actionReleaseBind by lazy {
        ObjectCalls.getMethodBind("Input", "action_release", ACTION_RELEASE_HASH)
    }

    private const val SET_DEFAULT_CURSOR_SHAPE_HASH = 2124816902L
    private val setDefaultCursorShapeBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_default_cursor_shape", SET_DEFAULT_CURSOR_SHAPE_HASH)
    }

    private const val GET_CURRENT_CURSOR_SHAPE_HASH = 3455658929L
    private val getCurrentCursorShapeBind by lazy {
        ObjectCalls.getMethodBind("Input", "get_current_cursor_shape", GET_CURRENT_CURSOR_SHAPE_HASH)
    }

    private const val SET_CUSTOM_MOUSE_CURSOR_HASH = 703945977L
    private val setCustomMouseCursorBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_custom_mouse_cursor", SET_CUSTOM_MOUSE_CURSOR_HASH)
    }

    private const val PARSE_INPUT_EVENT_HASH = 3754044979L
    private val parseInputEventBind by lazy {
        ObjectCalls.getMethodBind("Input", "parse_input_event", PARSE_INPUT_EVENT_HASH)
    }

    private const val SET_USE_ACCUMULATED_INPUT_HASH = 2586408642L
    private val setUseAccumulatedInputBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_use_accumulated_input", SET_USE_ACCUMULATED_INPUT_HASH)
    }

    private const val IS_USING_ACCUMULATED_INPUT_HASH = 2240911060L
    private val isUsingAccumulatedInputBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_using_accumulated_input", IS_USING_ACCUMULATED_INPUT_HASH)
    }

    private const val FLUSH_BUFFERED_EVENTS_HASH = 3218959716L
    private val flushBufferedEventsBind by lazy {
        ObjectCalls.getMethodBind("Input", "flush_buffered_events", FLUSH_BUFFERED_EVENTS_HASH)
    }

    private const val SET_EMULATE_MOUSE_FROM_TOUCH_HASH = 2586408642L
    private val setEmulateMouseFromTouchBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_emulate_mouse_from_touch", SET_EMULATE_MOUSE_FROM_TOUCH_HASH)
    }

    private const val IS_EMULATING_MOUSE_FROM_TOUCH_HASH = 36873697L
    private val isEmulatingMouseFromTouchBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_emulating_mouse_from_touch", IS_EMULATING_MOUSE_FROM_TOUCH_HASH)
    }

    private const val SET_EMULATE_TOUCH_FROM_MOUSE_HASH = 2586408642L
    private val setEmulateTouchFromMouseBind by lazy {
        ObjectCalls.getMethodBind("Input", "set_emulate_touch_from_mouse", SET_EMULATE_TOUCH_FROM_MOUSE_HASH)
    }

    private const val IS_EMULATING_TOUCH_FROM_MOUSE_HASH = 36873697L
    private val isEmulatingTouchFromMouseBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_emulating_touch_from_mouse", IS_EMULATING_TOUCH_FROM_MOUSE_HASH)
    }
}

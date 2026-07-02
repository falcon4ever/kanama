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

    @JvmStatic
    fun isAnythingPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAnythingPressedBind, singleton)
    }

    @JvmStatic
    fun isKeyPressed(keycode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isKeyPressedBind, singleton, keycode)
    }

    @JvmStatic
    fun isPhysicalKeyPressed(keycode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isPhysicalKeyPressedBind, singleton, keycode)
    }

    @JvmStatic
    fun isKeyLabelPressed(keycode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isKeyLabelPressedBind, singleton, keycode)
    }

    @JvmStatic
    fun isMouseButtonPressed(button: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isMouseButtonPressedBind, singleton, button)
    }

    @JvmStatic
    fun isJoyButtonPressed(device: Int, button: Long): Boolean {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetBool(isJoyButtonPressedBind, singleton, device, button)
    }

    @JvmStatic
    fun isActionPressed(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionPressedBind, singleton, action, exactMatch)
    }

    @JvmStatic
    fun isActionJustPressed(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionJustPressedBind, singleton, action, exactMatch)
    }

    @JvmStatic
    fun isActionJustReleased(action: String, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionJustReleasedBind, singleton, action, exactMatch)
    }

    @JvmStatic
    fun isActionJustPressedByEvent(action: String, event: InputEvent?, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameObjectAndBoolArgRetBool(isActionJustPressedByEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL, exactMatch)
    }

    @JvmStatic
    fun isActionJustReleasedByEvent(action: String, event: InputEvent?, exactMatch: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithStringNameObjectAndBoolArgRetBool(isActionJustReleasedByEventBind, singleton, action, event?.requireOpenHandle() ?: MemorySegment.NULL, exactMatch)
    }

    @JvmStatic
    fun getActionStrength(action: String, exactMatch: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(getActionStrengthBind, singleton, action, exactMatch)
    }

    @JvmStatic
    fun getActionRawStrength(action: String, exactMatch: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(getActionRawStrengthBind, singleton, action, exactMatch)
    }

    @JvmStatic
    fun getAxis(negativeAction: String, positiveAction: String): Double {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(getAxisBind, singleton, negativeAction, positiveAction)
    }

    @JvmStatic
    fun getVector(negativeX: String, positiveX: String, negativeY: String, positiveY: String, deadzone: Double = -1.0): Vector2 {
        return ObjectCalls.ptrcallWithFourStringNameAndFloatArgRetVector2(getVectorBind, singleton, negativeX, positiveX, negativeY, positiveY, deadzone)
    }

    @JvmStatic
    fun addJoyMapping(mapping: String, updateExisting: Boolean = false) {
        ObjectCalls.ptrcallWithStringAndBoolArg(addJoyMappingBind, singleton, mapping, updateExisting)
    }

    @JvmStatic
    fun removeJoyMapping(guid: String) {
        ObjectCalls.ptrcallWithStringArg(removeJoyMappingBind, singleton, guid)
    }

    @JvmStatic
    fun isJoyKnown(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyKnownBind, singleton, device)
    }

    @JvmStatic
    fun getJoyAxis(device: Int, axis: Long): Double {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetDouble(getJoyAxisBind, singleton, device, axis)
    }

    @JvmStatic
    fun getJoyName(device: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getJoyNameBind, singleton, device)
    }

    @JvmStatic
    fun getJoyGuid(device: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getJoyGuidBind, singleton, device)
    }

    @JvmStatic
    fun getJoyInfo(device: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getJoyInfoBind, singleton, device)
    }

    @JvmStatic
    fun shouldIgnoreDevice(vendorId: Int, productId: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(shouldIgnoreDeviceBind, singleton, vendorId, productId)
    }

    @JvmStatic
    fun getConnectedJoypads(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getConnectedJoypadsBind, singleton)
    }

    @JvmStatic
    fun getJoyVibrationStrength(device: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getJoyVibrationStrengthBind, singleton, device)
    }

    @JvmStatic
    fun getJoyVibrationDuration(device: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJoyVibrationDurationBind, singleton, device)
    }

    @JvmStatic
    fun getJoyVibrationRemainingDuration(device: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJoyVibrationRemainingDurationBind, singleton, device)
    }

    @JvmStatic
    fun isJoyVibrating(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyVibratingBind, singleton, device)
    }

    @JvmStatic
    fun hasJoyVibration(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasJoyVibrationBind, singleton, device)
    }

    @JvmStatic
    fun startJoyVibration(device: Int, weakMagnitude: Double, strongMagnitude: Double, duration: Double = 0.0) {
        ObjectCalls.ptrcallWithIntAndThreeDoubleArgs(startJoyVibrationBind, singleton, device, weakMagnitude, strongMagnitude, duration)
    }

    @JvmStatic
    fun stopJoyVibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(stopJoyVibrationBind, singleton, device)
    }

    @JvmStatic
    fun vibrateHandheld(durationMs: Int = 500, amplitude: Double = -1.0) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(vibrateHandheldBind, singleton, durationMs, amplitude)
    }

    @JvmStatic
    fun setIgnoreJoypadOnUnfocusedApplication(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreJoypadOnUnfocusedApplicationBind, singleton, enable)
    }

    @JvmStatic
    fun isIgnoringJoypadOnUnfocusedApplication(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringJoypadOnUnfocusedApplicationBind, singleton)
    }

    @JvmStatic
    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, singleton)
    }

    @JvmStatic
    fun getAccelerometer(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getAccelerometerBind, singleton)
    }

    @JvmStatic
    fun getMagnetometer(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getMagnetometerBind, singleton)
    }

    @JvmStatic
    fun getGyroscope(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGyroscopeBind, singleton)
    }

    @JvmStatic
    fun getJoyAccelerometer(device: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getJoyAccelerometerBind, singleton, device)
    }

    @JvmStatic
    fun getJoyGravity(device: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getJoyGravityBind, singleton, device)
    }

    @JvmStatic
    fun getJoyGyroscope(device: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getJoyGyroscopeBind, singleton, device)
    }

    @JvmStatic
    fun getJoyMotionSensorsRate(device: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJoyMotionSensorsRateBind, singleton, device)
    }

    @JvmStatic
    fun isJoyMotionSensorsEnabled(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyMotionSensorsEnabledBind, singleton, device)
    }

    @JvmStatic
    fun setJoyMotionSensorsEnabled(device: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setJoyMotionSensorsEnabledBind, singleton, device, enable)
    }

    @JvmStatic
    fun hasJoyMotionSensors(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasJoyMotionSensorsBind, singleton, device)
    }

    @JvmStatic
    fun startJoyMotionSensorsCalibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(startJoyMotionSensorsCalibrationBind, singleton, device)
    }

    @JvmStatic
    fun stopJoyMotionSensorsCalibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(stopJoyMotionSensorsCalibrationBind, singleton, device)
    }

    @JvmStatic
    fun clearJoyMotionSensorsCalibration(device: Int) {
        ObjectCalls.ptrcallWithIntArg(clearJoyMotionSensorsCalibrationBind, singleton, device)
    }

    @JvmStatic
    fun getJoyMotionSensorsCalibration(device: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getJoyMotionSensorsCalibrationBind, singleton, device)
    }

    @JvmStatic
    fun setJoyMotionSensorsCalibration(device: Int, calibrationInfo: Map<String, Any?>) {
        ObjectCalls.ptrcallWithIntAndDictionaryArg(setJoyMotionSensorsCalibrationBind, singleton, device, calibrationInfo)
    }

    @JvmStatic
    fun isJoyMotionSensorsCalibrated(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyMotionSensorsCalibratedBind, singleton, device)
    }

    @JvmStatic
    fun isJoyMotionSensorsCalibrating(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isJoyMotionSensorsCalibratingBind, singleton, device)
    }

    @JvmStatic
    fun setGravity(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityBind, singleton, value)
    }

    @JvmStatic
    fun setAccelerometer(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setAccelerometerBind, singleton, value)
    }

    @JvmStatic
    fun setMagnetometer(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setMagnetometerBind, singleton, value)
    }

    @JvmStatic
    fun setGyroscope(value: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGyroscopeBind, singleton, value)
    }

    @JvmStatic
    fun setJoyLight(device: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setJoyLightBind, singleton, device, color)
    }

    @JvmStatic
    fun hasJoyLight(device: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasJoyLightBind, singleton, device)
    }

    @JvmStatic
    fun getLastMouseVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLastMouseVelocityBind, singleton)
    }

    @JvmStatic
    fun getLastMouseScreenVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLastMouseScreenVelocityBind, singleton)
    }

    @JvmStatic
    fun getMouseButtonMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseButtonMaskBind, singleton)
    }

    @JvmStatic
    fun setMouseMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMouseModeBind, singleton, mode)
    }

    @JvmStatic
    fun getMouseMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseModeBind, singleton)
    }

    @JvmStatic
    fun warpMouse(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(warpMouseBind, singleton, position)
    }

    @JvmStatic
    fun actionPress(action: String, strength: Double = 1.0) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(actionPressBind, singleton, action, strength)
    }

    @JvmStatic
    fun actionRelease(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(actionReleaseBind, singleton, action)
    }

    @JvmStatic
    fun setDefaultCursorShape(shape: Long = 0L) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCursorShapeBind, singleton, shape)
    }

    @JvmStatic
    fun getCurrentCursorShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCurrentCursorShapeBind, singleton)
    }

    @JvmStatic
    fun setCustomMouseCursor(image: Resource?, shape: Long = 0L, hotspot: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithObjectLongAndVector2Arg(setCustomMouseCursorBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL, shape, hotspot)
    }

    @JvmStatic
    fun parseInputEvent(event: InputEvent?) {
        ObjectCalls.ptrcallWithObjectArgs(parseInputEventBind, singleton, listOf(event?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun setUseAccumulatedInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAccumulatedInputBind, singleton, enable)
    }

    @JvmStatic
    fun isUsingAccumulatedInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingAccumulatedInputBind, singleton)
    }

    @JvmStatic
    fun flushBufferedEvents() {
        ObjectCalls.ptrcallNoArgs(flushBufferedEventsBind, singleton)
    }

    @JvmStatic
    fun setEmulateMouseFromTouch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmulateMouseFromTouchBind, singleton, enable)
    }

    @JvmStatic
    fun isEmulatingMouseFromTouch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmulatingMouseFromTouchBind, singleton)
    }

    @JvmStatic
    fun setEmulateTouchFromMouse(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmulateTouchFromMouseBind, singleton, enable)
    }

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

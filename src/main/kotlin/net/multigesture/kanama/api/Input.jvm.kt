package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Input (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Input waits on: ptrcallWithIntAndDictionaryArg, ptrcallWithIntArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

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
fun Input.getJoyInfo(device: Int): Map<String, Any?> {
    return ObjectCalls.ptrcallWithIntArgRetDictionary(getJoyInfoBind, inputSingleton, device)
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
fun Input.getJoyMotionSensorsCalibration(device: Int): Map<String, Any?> {
    return ObjectCalls.ptrcallWithIntArgRetDictionary(getJoyMotionSensorsCalibrationBind, inputSingleton, device)
}

/**
 * Sets the specified joypad's calibration information. See also
 * `get_joy_motion_sensors_calibration`. See `start_joy_motion_sensors_calibration` for an example
 * on how to use joypad motion sensors and calibration in your games. Note: This feature is only
 * supported on Windows, Linux, macOS, and iOS.
 *
 * Generated from Godot docs: Input.set_joy_motion_sensors_calibration
 */
fun Input.setJoyMotionSensorsCalibration(device: Int, calibrationInfo: Map<String, Any?>) {
    ObjectCalls.ptrcallWithIntAndDictionaryArg(setJoyMotionSensorsCalibrationBind, inputSingleton, device, calibrationInfo)
}

private val inputSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Input")
}

private const val GET_JOY_INFO_HASH = 3485342025L
private val getJoyInfoBind by lazy {
    ObjectCalls.getMethodBind("Input", "get_joy_info", GET_JOY_INFO_HASH)
}

private const val GET_JOY_MOTION_SENSORS_CALIBRATION_HASH = 3485342025L
private val getJoyMotionSensorsCalibrationBind by lazy {
    ObjectCalls.getMethodBind("Input", "get_joy_motion_sensors_calibration", GET_JOY_MOTION_SENSORS_CALIBRATION_HASH)
}

private const val SET_JOY_MOTION_SENSORS_CALIBRATION_HASH = 64545446L
private val setJoyMotionSensorsCalibrationBind by lazy {
    ObjectCalls.getMethodBind("Input", "set_joy_motion_sensors_calibration", SET_JOY_MOTION_SENSORS_CALIBRATION_HASH)
}

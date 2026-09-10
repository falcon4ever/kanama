package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Input (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Input waits on: ptrcallWithIntAndDictionaryArg
// Index: docs/reference/generated/ios-shape-gap.md

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

private const val SET_JOY_MOTION_SENSORS_CALIBRATION_HASH = 64545446L
private val setJoyMotionSensorsCalibrationBind by lazy {
    ObjectCalls.getMethodBind("Input", "set_joy_motion_sensors_calibration", SET_JOY_MOTION_SENSORS_CALIBRATION_HASH)
}

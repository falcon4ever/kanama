package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for AudioServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioServer waits on: ptrcallWithIntArgRetPackedVector2List,
//   ptrcallWithIntArgRetString, ptrcallWithIntArgRetStringName
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the name of the bus with the index `bus_idx`.
 *
 * Generated from Godot docs: AudioServer.get_bus_name
 */
fun AudioServer.getBusName(busIdx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getBusNameBind, audioServerSingleton, busIdx)
}

/**
 * Returns the name of the bus that the bus at index `bus_idx` sends to.
 *
 * Generated from Godot docs: AudioServer.get_bus_send
 */
fun AudioServer.getBusSend(busIdx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetStringName(getBusSendBind, audioServerSingleton, busIdx)
}

/**
 * Returns a `PackedVector2Array` containing exactly `frames` audio samples from the internal
 * microphone buffer if available, otherwise returns an empty `PackedVector2Array`. The buffer is
 * filled at the rate of `get_input_mix_rate` frames per second when `set_input_device_active` has
 * successfully been set to `true`. The samples are signed floating-point PCM values between `-1`
 * and `1`.
 *
 * Generated from Godot docs: AudioServer.get_input_frames
 */
fun AudioServer.getInputFrames(frames: Int): List<Vector2> {
    return ObjectCalls.ptrcallWithIntArgRetPackedVector2List(getInputFramesBind, audioServerSingleton, frames)
}

private val audioServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("AudioServer")
}

private const val GET_BUS_NAME_HASH = 844755477L
private val getBusNameBind by lazy {
    ObjectCalls.getMethodBind("AudioServer", "get_bus_name", GET_BUS_NAME_HASH)
}

private const val GET_BUS_SEND_HASH = 659327637L
private val getBusSendBind by lazy {
    ObjectCalls.getMethodBind("AudioServer", "get_bus_send", GET_BUS_SEND_HASH)
}

private const val GET_INPUT_FRAMES_HASH = 2649534757L
private val getInputFramesBind by lazy {
    ObjectCalls.getMethodBind("AudioServer", "get_input_frames", GET_INPUT_FRAMES_HASH)
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Server interface for low-level audio access.
 *
 * Generated from Godot docs: AudioServer
 */
object AudioServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("AudioServer")
    }

    const val SPEAKER_MODE_STEREO: Long = 0L
    const val SPEAKER_SURROUND_31: Long = 1L
    const val SPEAKER_SURROUND_51: Long = 2L
    const val SPEAKER_SURROUND_71: Long = 3L
    const val PLAYBACK_TYPE_DEFAULT: Long = 0L
    const val PLAYBACK_TYPE_STREAM: Long = 1L
    const val PLAYBACK_TYPE_SAMPLE: Long = 2L
    const val PLAYBACK_TYPE_MAX: Long = 3L

    var busCount: Int
        @JvmName("busCountProperty")
        get() = getBusCount()
        @JvmName("setBusCountProperty")
        set(value) = setBusCount(value)

    var outputDevice: String
        @JvmName("outputDeviceProperty")
        get() = getOutputDevice()
        @JvmName("setOutputDeviceProperty")
        set(value) = setOutputDevice(value)

    var inputDevice: String
        @JvmName("inputDeviceProperty")
        get() = getInputDevice()
        @JvmName("setInputDeviceProperty")
        set(value) = setInputDevice(value)

    var playbackSpeedScale: Double
        @JvmName("playbackSpeedScaleProperty")
        get() = getPlaybackSpeedScale()
        @JvmName("setPlaybackSpeedScaleProperty")
        set(value) = setPlaybackSpeedScale(value)

    /**
     * Number of available audio buses.
     *
     * Generated from Godot docs: AudioServer.set_bus_count
     */
    @JvmStatic
    fun setBusCount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setBusCountBind, singleton, amount)
    }

    /**
     * Number of available audio buses.
     *
     * Generated from Godot docs: AudioServer.get_bus_count
     */
    @JvmStatic
    fun getBusCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBusCountBind, singleton)
    }

    /**
     * Removes the bus at index `index`.
     *
     * Generated from Godot docs: AudioServer.remove_bus
     */
    @JvmStatic
    fun removeBus(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeBusBind, singleton, index)
    }

    /**
     * Adds a bus at `at_position`.
     *
     * Generated from Godot docs: AudioServer.add_bus
     */
    @JvmStatic
    fun addBus(atPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addBusBind, singleton, atPosition)
    }

    /**
     * Moves the bus from index `index` to index `to_index`.
     *
     * Generated from Godot docs: AudioServer.move_bus
     */
    @JvmStatic
    fun moveBus(index: Int, toIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveBusBind, singleton, index, toIndex)
    }

    /**
     * Sets the name of the bus at index `bus_idx` to `name`.
     *
     * Generated from Godot docs: AudioServer.set_bus_name
     */
    @JvmStatic
    fun setBusName(busIdx: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setBusNameBind, singleton, busIdx, name)
    }

    /**
     * Returns the name of the bus with the index `bus_idx`.
     *
     * Generated from Godot docs: AudioServer.get_bus_name
     */
    @JvmStatic
    fun getBusName(busIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getBusNameBind, singleton, busIdx)
    }

    /**
     * Returns the index of the bus with the name `bus_name`. Returns `-1` if no bus with the specified
     * name exist.
     *
     * Generated from Godot docs: AudioServer.get_bus_index
     */
    @JvmStatic
    fun getBusIndex(busName: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(getBusIndexBind, singleton, busName)
    }

    /**
     * Returns the number of channels of the bus at index `bus_idx`.
     *
     * Generated from Godot docs: AudioServer.get_bus_channels
     */
    @JvmStatic
    fun getBusChannels(busIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getBusChannelsBind, singleton, busIdx)
    }

    /**
     * Sets the volume in decibels of the bus at index `bus_idx` to `volume_db`.
     *
     * Generated from Godot docs: AudioServer.set_bus_volume_db
     */
    @JvmStatic
    fun setBusVolumeDb(busIdx: Int, volumeDb: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBusVolumeDbBind, singleton, busIdx, volumeDb)
    }

    /**
     * Returns the volume of the bus at index `bus_idx` in dB.
     *
     * Generated from Godot docs: AudioServer.get_bus_volume_db
     */
    @JvmStatic
    fun getBusVolumeDb(busIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getBusVolumeDbBind, singleton, busIdx)
    }

    /**
     * Sets the volume as a linear value of the bus at index `bus_idx` to `volume_linear`. Note: Using
     * this method is equivalent to calling `set_bus_volume_db` with the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioServer.set_bus_volume_linear
     */
    @JvmStatic
    fun setBusVolumeLinear(busIdx: Int, volumeLinear: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBusVolumeLinearBind, singleton, busIdx, volumeLinear)
    }

    /**
     * Returns the volume of the bus at index `bus_idx` as a linear value. Note: The returned value is
     * equivalent to the result of `@GlobalScope.db_to_linear` on the result of `get_bus_volume_db`.
     *
     * Generated from Godot docs: AudioServer.get_bus_volume_linear
     */
    @JvmStatic
    fun getBusVolumeLinear(busIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getBusVolumeLinearBind, singleton, busIdx)
    }

    /**
     * Connects the output of the bus at `bus_idx` to the bus named `send`.
     *
     * Generated from Godot docs: AudioServer.set_bus_send
     */
    @JvmStatic
    fun setBusSend(busIdx: Int, send: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBusSendBind, singleton, busIdx, send)
    }

    /**
     * Returns the name of the bus that the bus at index `bus_idx` sends to.
     *
     * Generated from Godot docs: AudioServer.get_bus_send
     */
    @JvmStatic
    fun getBusSend(busIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBusSendBind, singleton, busIdx)
    }

    /**
     * If `true`, the bus at index `bus_idx` is in solo mode.
     *
     * Generated from Godot docs: AudioServer.set_bus_solo
     */
    @JvmStatic
    fun setBusSolo(busIdx: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBusSoloBind, singleton, busIdx, enable)
    }

    /**
     * If `true`, the bus at index `bus_idx` is in solo mode.
     *
     * Generated from Godot docs: AudioServer.is_bus_solo
     */
    @JvmStatic
    fun isBusSolo(busIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isBusSoloBind, singleton, busIdx)
    }

    /**
     * If `true`, the bus at index `bus_idx` is muted.
     *
     * Generated from Godot docs: AudioServer.set_bus_mute
     */
    @JvmStatic
    fun setBusMute(busIdx: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBusMuteBind, singleton, busIdx, enable)
    }

    /**
     * If `true`, the bus at index `bus_idx` is muted.
     *
     * Generated from Godot docs: AudioServer.is_bus_mute
     */
    @JvmStatic
    fun isBusMute(busIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isBusMuteBind, singleton, busIdx)
    }

    /**
     * If `true`, the bus at index `bus_idx` is bypassing effects.
     *
     * Generated from Godot docs: AudioServer.set_bus_bypass_effects
     */
    @JvmStatic
    fun setBusBypassEffects(busIdx: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBusBypassEffectsBind, singleton, busIdx, enable)
    }

    /**
     * If `true`, the bus at index `bus_idx` is bypassing effects.
     *
     * Generated from Godot docs: AudioServer.is_bus_bypassing_effects
     */
    @JvmStatic
    fun isBusBypassingEffects(busIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isBusBypassingEffectsBind, singleton, busIdx)
    }

    /**
     * Adds an `AudioEffect` effect to the bus `bus_idx` at `at_position`.
     *
     * Generated from Godot docs: AudioServer.add_bus_effect
     */
    @JvmStatic
    fun addBusEffect(busIdx: Int, effect: AudioEffect?, atPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntObjectAndIntArgs(addBusEffectBind, singleton, busIdx, effect?.requireOpenHandle() ?: MemorySegment.NULL, atPosition)
    }

    /**
     * Removes the effect at index `effect_idx` from the bus at index `bus_idx`.
     *
     * Generated from Godot docs: AudioServer.remove_bus_effect
     */
    @JvmStatic
    fun removeBusEffect(busIdx: Int, effectIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(removeBusEffectBind, singleton, busIdx, effectIdx)
    }

    /**
     * Returns the number of effects on the bus at `bus_idx`.
     *
     * Generated from Godot docs: AudioServer.get_bus_effect_count
     */
    @JvmStatic
    fun getBusEffectCount(busIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getBusEffectCountBind, singleton, busIdx)
    }

    /**
     * Returns the `AudioEffect` at position `effect_idx` in bus `bus_idx`.
     *
     * Generated from Godot docs: AudioServer.get_bus_effect
     */
    @JvmStatic
    fun getBusEffect(busIdx: Int, effectIdx: Int): AudioEffect? {
        return AudioEffect.wrap(ObjectCalls.ptrcallWithTwoIntArgsRetObject(getBusEffectBind, singleton, busIdx, effectIdx))
    }

    /**
     * Returns the `AudioEffectInstance` assigned to the given bus and effect indices (and optionally
     * channel).
     *
     * Generated from Godot docs: AudioServer.get_bus_effect_instance
     */
    @JvmStatic
    fun getBusEffectInstance(busIdx: Int, effectIdx: Int, channel: Int = 0): AudioEffectInstance? {
        return AudioEffectInstance.wrap(ObjectCalls.ptrcallWithThreeIntArgsRetObject(getBusEffectInstanceBind, singleton, busIdx, effectIdx, channel))
    }

    /**
     * Swaps the position of two effects in bus `bus_idx`.
     *
     * Generated from Godot docs: AudioServer.swap_bus_effects
     */
    @JvmStatic
    fun swapBusEffects(busIdx: Int, effectIdx: Int, byEffectIdx: Int) {
        ObjectCalls.ptrcallWithThreeIntArgs(swapBusEffectsBind, singleton, busIdx, effectIdx, byEffectIdx)
    }

    /**
     * If `true`, the effect at index `effect_idx` on the bus at index `bus_idx` is enabled.
     *
     * Generated from Godot docs: AudioServer.set_bus_effect_enabled
     */
    @JvmStatic
    fun setBusEffectEnabled(busIdx: Int, effectIdx: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setBusEffectEnabledBind, singleton, busIdx, effectIdx, enabled)
    }

    /**
     * If `true`, the effect at index `effect_idx` on the bus at index `bus_idx` is enabled.
     *
     * Generated from Godot docs: AudioServer.is_bus_effect_enabled
     */
    @JvmStatic
    fun isBusEffectEnabled(busIdx: Int, effectIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isBusEffectEnabledBind, singleton, busIdx, effectIdx)
    }

    /**
     * Returns the peak volume of the left speaker at bus index `bus_idx` and channel index `channel`.
     *
     * Generated from Godot docs: AudioServer.get_bus_peak_volume_left_db
     */
    @JvmStatic
    fun getBusPeakVolumeLeftDb(busIdx: Int, channel: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getBusPeakVolumeLeftDbBind, singleton, busIdx, channel)
    }

    /**
     * Returns the peak volume of the right speaker at bus index `bus_idx` and channel index `channel`.
     *
     * Generated from Godot docs: AudioServer.get_bus_peak_volume_right_db
     */
    @JvmStatic
    fun getBusPeakVolumeRightDb(busIdx: Int, channel: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getBusPeakVolumeRightDbBind, singleton, busIdx, channel)
    }

    /**
     * Scales the rate at which audio is played (i.e. setting it to `0.5` will make the audio be played
     * at half its speed). See also `Engine.time_scale` to affect the general simulation speed, which
     * is independent from `AudioServer.playback_speed_scale`.
     *
     * Generated from Godot docs: AudioServer.set_playback_speed_scale
     */
    @JvmStatic
    fun setPlaybackSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPlaybackSpeedScaleBind, singleton, scale)
    }

    /**
     * Scales the rate at which audio is played (i.e. setting it to `0.5` will make the audio be played
     * at half its speed). See also `Engine.time_scale` to affect the general simulation speed, which
     * is independent from `AudioServer.playback_speed_scale`.
     *
     * Generated from Godot docs: AudioServer.get_playback_speed_scale
     */
    @JvmStatic
    fun getPlaybackSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackSpeedScaleBind, singleton)
    }

    @JvmStatic
    /**
     * Locks the audio driver's main loop. Note: Remember to unlock it afterwards.
     *
     * Generated from Godot docs: AudioServer.lock
     */
    fun lock() {
        ObjectCalls.ptrcallNoArgs(lockBind, singleton)
    }

    @JvmStatic
    /**
     * Unlocks the audio driver's main loop. (After locking it, you should always unlock it.)
     *
     * Generated from Godot docs: AudioServer.unlock
     */
    fun unlock() {
        ObjectCalls.ptrcallNoArgs(unlockBind, singleton)
    }

    /**
     * Returns the speaker configuration.
     *
     * Generated from Godot docs: AudioServer.get_speaker_mode
     */
    @JvmStatic
    fun getSpeakerMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSpeakerModeBind, singleton)
    }

    /**
     * Returns the sample rate at the output of the `AudioServer`.
     *
     * Generated from Godot docs: AudioServer.get_mix_rate
     */
    @JvmStatic
    fun getMixRate(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMixRateBind, singleton)
    }

    /**
     * Returns the sample rate at the input of the `AudioServer`.
     *
     * Generated from Godot docs: AudioServer.get_input_mix_rate
     */
    @JvmStatic
    fun getInputMixRate(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInputMixRateBind, singleton)
    }

    /**
     * Returns the name of the current audio driver. The default usually depends on the operating
     * system, but may be overridden via the `--audio-driver` command line argument
     * ($DOCS_URL/tutorials/editor/command_line_tutorial.html). `--headless` also automatically sets
     * the audio driver to `Dummy`. See also `ProjectSettings.audio/driver/driver`.
     *
     * Generated from Godot docs: AudioServer.get_driver_name
     */
    @JvmStatic
    fun getDriverName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDriverNameBind, singleton)
    }

    /**
     * Returns the names of all audio output devices detected on the system.
     *
     * Generated from Godot docs: AudioServer.get_output_device_list
     */
    @JvmStatic
    fun getOutputDeviceList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getOutputDeviceListBind, singleton)
    }

    /**
     * Name of the current device for audio output (see `get_output_device_list`). On systems with
     * multiple audio outputs (such as analog, USB and HDMI audio), this can be used to select the
     * audio output device. The value `"Default"` will play audio on the system-wide default audio
     * output. If an invalid device name is set, the value will be reverted back to `"Default"`.
     *
     * Generated from Godot docs: AudioServer.get_output_device
     */
    @JvmStatic
    fun getOutputDevice(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOutputDeviceBind, singleton)
    }

    /**
     * Name of the current device for audio output (see `get_output_device_list`). On systems with
     * multiple audio outputs (such as analog, USB and HDMI audio), this can be used to select the
     * audio output device. The value `"Default"` will play audio on the system-wide default audio
     * output. If an invalid device name is set, the value will be reverted back to `"Default"`.
     *
     * Generated from Godot docs: AudioServer.set_output_device
     */
    @JvmStatic
    fun setOutputDevice(name: String) {
        ObjectCalls.ptrcallWithStringArg(setOutputDeviceBind, singleton, name)
    }

    /**
     * Returns the relative time until the next mix occurs, in seconds.
     *
     * Generated from Godot docs: AudioServer.get_time_to_next_mix
     */
    @JvmStatic
    fun getTimeToNextMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeToNextMixBind, singleton)
    }

    /**
     * Returns the relative time since the last mix occurred, in seconds.
     *
     * Generated from Godot docs: AudioServer.get_time_since_last_mix
     */
    @JvmStatic
    fun getTimeSinceLastMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeSinceLastMixBind, singleton)
    }

    /**
     * Returns the audio driver's effective output latency. This is based on
     * `ProjectSettings.audio/driver/output_latency`, but the exact returned value will differ
     * depending on the operating system and audio driver. Note: This can be expensive; it is not
     * recommended to call `get_output_latency` every frame.
     *
     * Generated from Godot docs: AudioServer.get_output_latency
     */
    @JvmStatic
    fun getOutputLatency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOutputLatencyBind, singleton)
    }

    /**
     * Returns the names of all audio input devices detected on the system. Note:
     * `ProjectSettings.audio/driver/enable_input` must be `true` for audio input to work. See also
     * that setting's description for caveats related to permissions and operating system privacy
     * settings.
     *
     * Generated from Godot docs: AudioServer.get_input_device_list
     */
    @JvmStatic
    fun getInputDeviceList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getInputDeviceListBind, singleton)
    }

    /**
     * Name of the current device for audio input (see `get_input_device_list`). On systems with
     * multiple audio inputs (such as analog, USB and HDMI audio), this can be used to select the audio
     * input device. The value `"Default"` will record audio on the system-wide default audio input. If
     * an invalid device name is set, the value will be reverted back to `"Default"`. Note:
     * `ProjectSettings.audio/driver/enable_input` must be `true` for audio input to work. See also
     * that setting's description for caveats related to permissions and operating system privacy
     * settings.
     *
     * Generated from Godot docs: AudioServer.get_input_device
     */
    @JvmStatic
    fun getInputDevice(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getInputDeviceBind, singleton)
    }

    /**
     * Name of the current device for audio input (see `get_input_device_list`). On systems with
     * multiple audio inputs (such as analog, USB and HDMI audio), this can be used to select the audio
     * input device. The value `"Default"` will record audio on the system-wide default audio input. If
     * an invalid device name is set, the value will be reverted back to `"Default"`. Note:
     * `ProjectSettings.audio/driver/enable_input` must be `true` for audio input to work. See also
     * that setting's description for caveats related to permissions and operating system privacy
     * settings.
     *
     * Generated from Godot docs: AudioServer.set_input_device
     */
    @JvmStatic
    fun setInputDevice(name: String) {
        ObjectCalls.ptrcallWithStringArg(setInputDeviceBind, singleton, name)
    }

    /**
     * If `active` is `true`, starts the microphone input stream specified by `input_device` or returns
     * an error if it failed. If `active` is `false`, stops the input stream if it is running.
     *
     * Generated from Godot docs: AudioServer.set_input_device_active
     */
    @JvmStatic
    fun setInputDeviceActive(active: Boolean): Long {
        return ObjectCalls.ptrcallWithBoolArgRetLong(setInputDeviceActiveBind, singleton, active)
    }

    /**
     * Returns the number of frames available to read using `get_input_frames`.
     *
     * Generated from Godot docs: AudioServer.get_input_frames_available
     */
    @JvmStatic
    fun getInputFramesAvailable(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputFramesAvailableBind, singleton)
    }

    /**
     * Returns the absolute size of the microphone input buffer. This is set to a multiple of the audio
     * latency and can be used to estimate the minimum rate at which the frames need to be fetched.
     *
     * Generated from Godot docs: AudioServer.get_input_buffer_length_frames
     */
    @JvmStatic
    fun getInputBufferLengthFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputBufferLengthFramesBind, singleton)
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
    @JvmStatic
    fun getInputFrames(frames: Int): List<Vector2> {
        return ObjectCalls.ptrcallWithIntArgRetPackedVector2List(getInputFramesBind, singleton, frames)
    }

    /**
     * Overwrites the currently used `AudioBusLayout`.
     *
     * Generated from Godot docs: AudioServer.set_bus_layout
     */
    @JvmStatic
    fun setBusLayout(busLayout: AudioBusLayout?) {
        ObjectCalls.ptrcallWithObjectArgs(setBusLayoutBind, singleton, listOf(busLayout?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Generates an `AudioBusLayout` using the available buses and effects.
     *
     * Generated from Godot docs: AudioServer.generate_bus_layout
     */
    @JvmStatic
    fun generateBusLayout(): AudioBusLayout? {
        return AudioBusLayout.wrap(ObjectCalls.ptrcallNoArgsRetObject(generateBusLayoutBind, singleton))
    }

    /**
     * If set to `true`, all instances of `AudioStreamPlayback` will call
     * `AudioStreamPlayback._tag_used_streams` every mix step. Note: This is enabled by default in the
     * editor, as it is used by editor plugins for the audio stream previews.
     *
     * Generated from Godot docs: AudioServer.set_enable_tagging_used_audio_streams
     */
    @JvmStatic
    fun setEnableTaggingUsedAudioStreams(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableTaggingUsedAudioStreamsBind, singleton, enable)
    }

    /**
     * If `true`, the stream is registered as a sample. The engine will not have to register it before
     * playing the sample. If `false`, the stream will have to be registered before playing it. To
     * prevent lag spikes, register the stream as sample with `register_stream_as_sample`.
     *
     * Generated from Godot docs: AudioServer.is_stream_registered_as_sample
     */
    @JvmStatic
    fun isStreamRegisteredAsSample(stream: AudioStream?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isStreamRegisteredAsSampleBind, singleton, stream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Forces the registration of a stream as a sample. Note: Lag spikes may occur when calling this
     * method, especially on single-threaded builds. It is suggested to call this method while loading
     * assets, where the lag spike could be masked, instead of registering the sample right before it
     * needs to be played.
     *
     * Generated from Godot docs: AudioServer.register_stream_as_sample
     */
    @JvmStatic
    fun registerStreamAsSample(stream: AudioStream?) {
        ObjectCalls.ptrcallWithObjectArgs(registerStreamAsSampleBind, singleton, listOf(stream?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    object Signals {
        const val busLayoutChanged: String = "bus_layout_changed"
        const val busRenamed: String = "bus_renamed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): AudioServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): AudioServer? =
        if (handle.address() == 0L) null else this

    private const val SET_BUS_COUNT_HASH = 1286410249L
    private val setBusCountBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_count", SET_BUS_COUNT_HASH)
    }

    private const val GET_BUS_COUNT_HASH = 3905245786L
    private val getBusCountBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_count", GET_BUS_COUNT_HASH)
    }

    private const val REMOVE_BUS_HASH = 1286410249L
    private val removeBusBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "remove_bus", REMOVE_BUS_HASH)
    }

    private const val ADD_BUS_HASH = 1025054187L
    private val addBusBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "add_bus", ADD_BUS_HASH)
    }

    private const val MOVE_BUS_HASH = 3937882851L
    private val moveBusBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "move_bus", MOVE_BUS_HASH)
    }

    private const val SET_BUS_NAME_HASH = 501894301L
    private val setBusNameBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_name", SET_BUS_NAME_HASH)
    }

    private const val GET_BUS_NAME_HASH = 844755477L
    private val getBusNameBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_name", GET_BUS_NAME_HASH)
    }

    private const val GET_BUS_INDEX_HASH = 2458036349L
    private val getBusIndexBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_index", GET_BUS_INDEX_HASH)
    }

    private const val GET_BUS_CHANNELS_HASH = 923996154L
    private val getBusChannelsBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_channels", GET_BUS_CHANNELS_HASH)
    }

    private const val SET_BUS_VOLUME_DB_HASH = 1602489585L
    private val setBusVolumeDbBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_volume_db", SET_BUS_VOLUME_DB_HASH)
    }

    private const val GET_BUS_VOLUME_DB_HASH = 2339986948L
    private val getBusVolumeDbBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_volume_db", GET_BUS_VOLUME_DB_HASH)
    }

    private const val SET_BUS_VOLUME_LINEAR_HASH = 1602489585L
    private val setBusVolumeLinearBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_volume_linear", SET_BUS_VOLUME_LINEAR_HASH)
    }

    private const val GET_BUS_VOLUME_LINEAR_HASH = 2339986948L
    private val getBusVolumeLinearBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_volume_linear", GET_BUS_VOLUME_LINEAR_HASH)
    }

    private const val SET_BUS_SEND_HASH = 3780747571L
    private val setBusSendBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_send", SET_BUS_SEND_HASH)
    }

    private const val GET_BUS_SEND_HASH = 659327637L
    private val getBusSendBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_send", GET_BUS_SEND_HASH)
    }

    private const val SET_BUS_SOLO_HASH = 300928843L
    private val setBusSoloBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_solo", SET_BUS_SOLO_HASH)
    }

    private const val IS_BUS_SOLO_HASH = 1116898809L
    private val isBusSoloBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "is_bus_solo", IS_BUS_SOLO_HASH)
    }

    private const val SET_BUS_MUTE_HASH = 300928843L
    private val setBusMuteBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_mute", SET_BUS_MUTE_HASH)
    }

    private const val IS_BUS_MUTE_HASH = 1116898809L
    private val isBusMuteBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "is_bus_mute", IS_BUS_MUTE_HASH)
    }

    private const val SET_BUS_BYPASS_EFFECTS_HASH = 300928843L
    private val setBusBypassEffectsBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_bypass_effects", SET_BUS_BYPASS_EFFECTS_HASH)
    }

    private const val IS_BUS_BYPASSING_EFFECTS_HASH = 1116898809L
    private val isBusBypassingEffectsBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "is_bus_bypassing_effects", IS_BUS_BYPASSING_EFFECTS_HASH)
    }

    private const val ADD_BUS_EFFECT_HASH = 4068819785L
    private val addBusEffectBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "add_bus_effect", ADD_BUS_EFFECT_HASH)
    }

    private const val REMOVE_BUS_EFFECT_HASH = 3937882851L
    private val removeBusEffectBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "remove_bus_effect", REMOVE_BUS_EFFECT_HASH)
    }

    private const val GET_BUS_EFFECT_COUNT_HASH = 3744713108L
    private val getBusEffectCountBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_effect_count", GET_BUS_EFFECT_COUNT_HASH)
    }

    private const val GET_BUS_EFFECT_HASH = 726064442L
    private val getBusEffectBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_effect", GET_BUS_EFFECT_HASH)
    }

    private const val GET_BUS_EFFECT_INSTANCE_HASH = 1829771234L
    private val getBusEffectInstanceBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_effect_instance", GET_BUS_EFFECT_INSTANCE_HASH)
    }

    private const val SWAP_BUS_EFFECTS_HASH = 1649997291L
    private val swapBusEffectsBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "swap_bus_effects", SWAP_BUS_EFFECTS_HASH)
    }

    private const val SET_BUS_EFFECT_ENABLED_HASH = 1383440665L
    private val setBusEffectEnabledBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_effect_enabled", SET_BUS_EFFECT_ENABLED_HASH)
    }

    private const val IS_BUS_EFFECT_ENABLED_HASH = 2522259332L
    private val isBusEffectEnabledBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "is_bus_effect_enabled", IS_BUS_EFFECT_ENABLED_HASH)
    }

    private const val GET_BUS_PEAK_VOLUME_LEFT_DB_HASH = 3085491603L
    private val getBusPeakVolumeLeftDbBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_peak_volume_left_db", GET_BUS_PEAK_VOLUME_LEFT_DB_HASH)
    }

    private const val GET_BUS_PEAK_VOLUME_RIGHT_DB_HASH = 3085491603L
    private val getBusPeakVolumeRightDbBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_bus_peak_volume_right_db", GET_BUS_PEAK_VOLUME_RIGHT_DB_HASH)
    }

    private const val SET_PLAYBACK_SPEED_SCALE_HASH = 373806689L
    private val setPlaybackSpeedScaleBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_playback_speed_scale", SET_PLAYBACK_SPEED_SCALE_HASH)
    }

    private const val GET_PLAYBACK_SPEED_SCALE_HASH = 1740695150L
    private val getPlaybackSpeedScaleBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_playback_speed_scale", GET_PLAYBACK_SPEED_SCALE_HASH)
    }

    private const val LOCK_HASH = 3218959716L
    private val lockBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "lock", LOCK_HASH)
    }

    private const val UNLOCK_HASH = 3218959716L
    private val unlockBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "unlock", UNLOCK_HASH)
    }

    private const val GET_SPEAKER_MODE_HASH = 2549190337L
    private val getSpeakerModeBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_speaker_mode", GET_SPEAKER_MODE_HASH)
    }

    private const val GET_MIX_RATE_HASH = 1740695150L
    private val getMixRateBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_mix_rate", GET_MIX_RATE_HASH)
    }

    private const val GET_INPUT_MIX_RATE_HASH = 1740695150L
    private val getInputMixRateBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_input_mix_rate", GET_INPUT_MIX_RATE_HASH)
    }

    private const val GET_DRIVER_NAME_HASH = 201670096L
    private val getDriverNameBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_driver_name", GET_DRIVER_NAME_HASH)
    }

    private const val GET_OUTPUT_DEVICE_LIST_HASH = 2981934095L
    private val getOutputDeviceListBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_output_device_list", GET_OUTPUT_DEVICE_LIST_HASH)
    }

    private const val GET_OUTPUT_DEVICE_HASH = 2841200299L
    private val getOutputDeviceBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_output_device", GET_OUTPUT_DEVICE_HASH)
    }

    private const val SET_OUTPUT_DEVICE_HASH = 83702148L
    private val setOutputDeviceBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_output_device", SET_OUTPUT_DEVICE_HASH)
    }

    private const val GET_TIME_TO_NEXT_MIX_HASH = 1740695150L
    private val getTimeToNextMixBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_time_to_next_mix", GET_TIME_TO_NEXT_MIX_HASH)
    }

    private const val GET_TIME_SINCE_LAST_MIX_HASH = 1740695150L
    private val getTimeSinceLastMixBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_time_since_last_mix", GET_TIME_SINCE_LAST_MIX_HASH)
    }

    private const val GET_OUTPUT_LATENCY_HASH = 1740695150L
    private val getOutputLatencyBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_output_latency", GET_OUTPUT_LATENCY_HASH)
    }

    private const val GET_INPUT_DEVICE_LIST_HASH = 2981934095L
    private val getInputDeviceListBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_input_device_list", GET_INPUT_DEVICE_LIST_HASH)
    }

    private const val GET_INPUT_DEVICE_HASH = 2841200299L
    private val getInputDeviceBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_input_device", GET_INPUT_DEVICE_HASH)
    }

    private const val SET_INPUT_DEVICE_HASH = 83702148L
    private val setInputDeviceBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_input_device", SET_INPUT_DEVICE_HASH)
    }

    private const val SET_INPUT_DEVICE_ACTIVE_HASH = 1413768114L
    private val setInputDeviceActiveBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_input_device_active", SET_INPUT_DEVICE_ACTIVE_HASH)
    }

    private const val GET_INPUT_FRAMES_AVAILABLE_HASH = 2455072627L
    private val getInputFramesAvailableBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_input_frames_available", GET_INPUT_FRAMES_AVAILABLE_HASH)
    }

    private const val GET_INPUT_BUFFER_LENGTH_FRAMES_HASH = 2455072627L
    private val getInputBufferLengthFramesBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_input_buffer_length_frames", GET_INPUT_BUFFER_LENGTH_FRAMES_HASH)
    }

    private const val GET_INPUT_FRAMES_HASH = 2649534757L
    private val getInputFramesBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "get_input_frames", GET_INPUT_FRAMES_HASH)
    }

    private const val SET_BUS_LAYOUT_HASH = 3319058824L
    private val setBusLayoutBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_bus_layout", SET_BUS_LAYOUT_HASH)
    }

    private const val GENERATE_BUS_LAYOUT_HASH = 3769973890L
    private val generateBusLayoutBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "generate_bus_layout", GENERATE_BUS_LAYOUT_HASH)
    }

    private const val SET_ENABLE_TAGGING_USED_AUDIO_STREAMS_HASH = 2586408642L
    private val setEnableTaggingUsedAudioStreamsBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "set_enable_tagging_used_audio_streams", SET_ENABLE_TAGGING_USED_AUDIO_STREAMS_HASH)
    }

    private const val IS_STREAM_REGISTERED_AS_SAMPLE_HASH = 500225754L
    private val isStreamRegisteredAsSampleBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "is_stream_registered_as_sample", IS_STREAM_REGISTERED_AS_SAMPLE_HASH)
    }

    private const val REGISTER_STREAM_AS_SAMPLE_HASH = 2210767741L
    private val registerStreamAsSampleBind by lazy {
        ObjectCalls.getMethodBind("AudioServer", "register_stream_as_sample", REGISTER_STREAM_AS_SAMPLE_HASH)
    }
}

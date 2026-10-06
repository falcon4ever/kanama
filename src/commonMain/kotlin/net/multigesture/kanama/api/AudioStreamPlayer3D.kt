package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Plays positional sound in 3D space.
 *
 * Generated from Godot docs: AudioStreamPlayer3D
 */
class AudioStreamPlayer3D(handle: GodotHandle) : Node3D(handle) {
    var stream: AudioStream?
        @JvmName("streamProperty")
        get() = getStream()
        @JvmName("setStreamProperty")
        set(value) = setStream(value)

    var attenuationModel: AudioStreamPlayer3D.AttenuationModel
        @JvmName("attenuationModelProperty")
        get() = getAttenuationModel()
        @JvmName("setAttenuationModelProperty")
        set(value) = setAttenuationModel(value)

    var volumeDb: Double
        @JvmName("volumeDbProperty")
        get() = getVolumeDb()
        @JvmName("setVolumeDbProperty")
        set(value) = setVolumeDb(value)

    var volumeLinear: Double
        @JvmName("volumeLinearProperty")
        get() = getVolumeLinear()
        @JvmName("setVolumeLinearProperty")
        set(value) = setVolumeLinear(value)

    var unitSize: Double
        @JvmName("unitSizeProperty")
        get() = getUnitSize()
        @JvmName("setUnitSizeProperty")
        set(value) = setUnitSize(value)

    var maxDb: Double
        @JvmName("maxDbProperty")
        get() = getMaxDb()
        @JvmName("setMaxDbProperty")
        set(value) = setMaxDb(value)

    var pitchScale: Double
        @JvmName("pitchScaleProperty")
        get() = getPitchScale()
        @JvmName("setPitchScaleProperty")
        set(value) = setPitchScale(value)

    var playing: Boolean
        @JvmName("playingProperty")
        get() = isPlaying()
        @JvmName("setPlayingProperty")
        set(value) = setPlaying(value)

    var autoplay: Boolean
        @JvmName("autoplayProperty")
        get() = isAutoplayEnabled()
        @JvmName("setAutoplayProperty")
        set(value) = setAutoplay(value)

    var streamPaused: Boolean
        @JvmName("streamPausedProperty")
        get() = getStreamPaused()
        @JvmName("setStreamPausedProperty")
        set(value) = setStreamPaused(value)

    var maxDistance: Double
        @JvmName("maxDistanceProperty")
        get() = getMaxDistance()
        @JvmName("setMaxDistanceProperty")
        set(value) = setMaxDistance(value)

    var maxPolyphony: Int
        @JvmName("maxPolyphonyProperty")
        get() = getMaxPolyphony()
        @JvmName("setMaxPolyphonyProperty")
        set(value) = setMaxPolyphony(value)

    var panningStrength: Double
        @JvmName("panningStrengthProperty")
        get() = getPanningStrength()
        @JvmName("setPanningStrengthProperty")
        set(value) = setPanningStrength(value)

    var bus: String
        @JvmName("busProperty")
        get() = getBus()
        @JvmName("setBusProperty")
        set(value) = setBus(value)

    var areaMask: Long
        @JvmName("areaMaskProperty")
        get() = getAreaMask()
        @JvmName("setAreaMaskProperty")
        set(value) = setAreaMask(value)

    var playbackType: AudioServer.PlaybackType
        @JvmName("playbackTypeProperty")
        get() = getPlaybackType()
        @JvmName("setPlaybackTypeProperty")
        set(value) = setPlaybackType(value)

    var emissionAngleEnabled: Boolean
        @JvmName("emissionAngleEnabledProperty")
        get() = isEmissionAngleEnabled()
        @JvmName("setEmissionAngleEnabledProperty")
        set(value) = setEmissionAngleEnabled(value)

    var emissionAngleDegrees: Double
        @JvmName("emissionAngleDegreesProperty")
        get() = getEmissionAngle()
        @JvmName("setEmissionAngleDegreesProperty")
        set(value) = setEmissionAngle(value)

    var emissionAngleFilterAttenuationDb: Double
        @JvmName("emissionAngleFilterAttenuationDbProperty")
        get() = getEmissionAngleFilterAttenuationDb()
        @JvmName("setEmissionAngleFilterAttenuationDbProperty")
        set(value) = setEmissionAngleFilterAttenuationDb(value)

    var attenuationFilterCutoffHz: Double
        @JvmName("attenuationFilterCutoffHzProperty")
        get() = getAttenuationFilterCutoffHz()
        @JvmName("setAttenuationFilterCutoffHzProperty")
        set(value) = setAttenuationFilterCutoffHz(value)

    var attenuationFilterDb: Double
        @JvmName("attenuationFilterDbProperty")
        get() = getAttenuationFilterDb()
        @JvmName("setAttenuationFilterDbProperty")
        set(value) = setAttenuationFilterDb(value)

    var dopplerTracking: AudioStreamPlayer3D.DopplerTracking
        @JvmName("dopplerTrackingProperty")
        get() = getDopplerTracking()
        @JvmName("setDopplerTrackingProperty")
        set(value) = setDopplerTracking(value)

    /**
     * The `AudioStream` resource to be played.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_stream
     */
    fun setStream(stream: AudioStream?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setStreamBind, segment, listOf(stream?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `AudioStream` resource to be played.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_stream
     */
    fun getStream(): AudioStream? {
        return AudioStream.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getStreamBind, segment))
    }

    /**
     * The base sound level before attenuation, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_volume_db
     */
    fun setVolumeDb(volumeDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVolumeDbBind, segment, volumeDb)
    }

    /**
     * The base sound level before attenuation, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_volume_db
     */
    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVolumeDbBind, segment)
    }

    /**
     * The base sound level before attenuation, as a linear value. Note: This member modifies
     * `volume_db` for convenience. The returned value is equivalent to the result of
     * `@GlobalScope.db_to_linear` on `volume_db`. Setting this member is equivalent to setting
     * `volume_db` to the result of `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_volume_linear
     */
    fun setVolumeLinear(volumeLinear: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVolumeLinearBind, segment, volumeLinear)
    }

    /**
     * The base sound level before attenuation, as a linear value. Note: This member modifies
     * `volume_db` for convenience. The returned value is equivalent to the result of
     * `@GlobalScope.db_to_linear` on `volume_db`. Setting this member is equivalent to setting
     * `volume_db` to the result of `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_volume_linear
     */
    fun getVolumeLinear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVolumeLinearBind, segment)
    }

    /**
     * The factor for the attenuation effect. Higher values make the sound audible over a larger
     * distance.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_unit_size
     */
    fun setUnitSize(unitSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setUnitSizeBind, segment, unitSize)
    }

    /**
     * The factor for the attenuation effect. Higher values make the sound audible over a larger
     * distance.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_unit_size
     */
    fun getUnitSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getUnitSizeBind, segment)
    }

    /**
     * Sets the absolute maximum of the sound level, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_max_db
     */
    fun setMaxDb(maxDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxDbBind, segment, maxDb)
    }

    /**
     * Sets the absolute maximum of the sound level, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_max_db
     */
    fun getMaxDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxDbBind, segment)
    }

    /**
     * The pitch and the tempo of the audio, as a multiplier of the audio sample's sample rate.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_pitch_scale
     */
    fun setPitchScale(pitchScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPitchScaleBind, segment, pitchScale)
    }

    /**
     * The pitch and the tempo of the audio, as a multiplier of the audio sample's sample rate.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_pitch_scale
     */
    fun getPitchScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPitchScaleBind, segment)
    }

    /**
     * Queues the audio to play on the next physics frame, from the given position `from_position`, in
     * seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.play
     */
    fun play(fromPosition: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.playBind, segment, fromPosition)
    }

    /**
     * Sets the position from which audio will be played, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.seek
     */
    fun seek(toPosition: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.seekBind, segment, toPosition)
    }

    /**
     * Stops the audio.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(Binds.stopBind, segment)
    }

    /**
     * If `true`, audio is playing or is queued to be played (see `play`).
     *
     * Generated from Godot docs: AudioStreamPlayer3D.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPlayingBind, segment)
    }

    /**
     * Returns the position in the `AudioStream`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_playback_position
     */
    fun getPlaybackPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPlaybackPositionBind, segment)
    }

    /**
     * The bus on which this audio is playing. Note: When setting this property, keep in mind that no
     * validation is performed to see if the given name matches an existing bus. This is because audio
     * bus layouts might be loaded after this property is set. If this given name can't be resolved at
     * runtime, it will fall back to `"Master"`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_bus
     */
    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setBusBind, segment, bus)
    }

    /**
     * The bus on which this audio is playing. Note: When setting this property, keep in mind that no
     * validation is performed to see if the given name matches an existing bus. This is because audio
     * bus layouts might be loaded after this property is set. If this given name can't be resolved at
     * runtime, it will fall back to `"Master"`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_bus
     */
    fun getBus(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getBusBind, segment)
    }

    /**
     * If `true`, audio plays when the AudioStreamPlayer3D node is added to scene tree.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_autoplay
     */
    fun setAutoplay(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoplayBind, segment, enable)
    }

    /**
     * If `true`, audio plays when the AudioStreamPlayer3D node is added to scene tree.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.is_autoplay_enabled
     */
    fun isAutoplayEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutoplayEnabledBind, segment)
    }

    /**
     * If `true`, audio is playing or is queued to be played (see `play`).
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_playing
     */
    fun setPlaying(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPlayingBind, segment, enable)
    }

    /**
     * The distance past which the sound can no longer be heard at all. Only has an effect if set to a
     * value greater than `0.0`. `max_distance` works in tandem with `unit_size`. However, unlike
     * `unit_size` whose behavior depends on the `attenuation_model`, `max_distance` always works in a
     * linear fashion. This can be used to prevent the `AudioStreamPlayer3D` from requiring audio
     * mixing when the listener is far away, which saves CPU resources.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_max_distance
     */
    fun setMaxDistance(meters: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxDistanceBind, segment, meters)
    }

    /**
     * The distance past which the sound can no longer be heard at all. Only has an effect if set to a
     * value greater than `0.0`. `max_distance` works in tandem with `unit_size`. However, unlike
     * `unit_size` whose behavior depends on the `attenuation_model`, `max_distance` always works in a
     * linear fashion. This can be used to prevent the `AudioStreamPlayer3D` from requiring audio
     * mixing when the listener is far away, which saves CPU resources.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_max_distance
     */
    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxDistanceBind, segment)
    }

    /**
     * Determines which `Area3D` layers affect the sound for reverb and audio bus effects. Areas can be
     * used to redirect `AudioStream`s so that they play in a certain audio bus. An example of how you
     * might use this is making a "water" area so that sounds played in the water are redirected
     * through an audio bus to make them sound like they are being played underwater.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_area_mask
     */
    fun setAreaMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setAreaMaskBind, segment, mask)
    }

    /**
     * Determines which `Area3D` layers affect the sound for reverb and audio bus effects. Areas can be
     * used to redirect `AudioStream`s so that they play in a certain audio bus. An example of how you
     * might use this is making a "water" area so that sounds played in the water are redirected
     * through an audio bus to make them sound like they are being played underwater.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_area_mask
     */
    fun getAreaMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getAreaMaskBind, segment)
    }

    /**
     * The angle in which the audio reaches a listener unattenuated.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_emission_angle
     */
    fun setEmissionAngle(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionAngleBind, segment, degrees)
    }

    /**
     * The angle in which the audio reaches a listener unattenuated.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_emission_angle
     */
    fun getEmissionAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionAngleBind, segment)
    }

    /**
     * If `true`, the audio should be attenuated according to the direction of the sound.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_emission_angle_enabled
     */
    fun setEmissionAngleEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEmissionAngleEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the audio should be attenuated according to the direction of the sound.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.is_emission_angle_enabled
     */
    fun isEmissionAngleEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmissionAngleEnabledBind, segment)
    }

    /**
     * Attenuation factor used if listener is outside of `emission_angle_degrees` and
     * `emission_angle_enabled` is set, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_emission_angle_filter_attenuation_db
     */
    fun setEmissionAngleFilterAttenuationDb(db: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionAngleFilterAttenuationDbBind, segment, db)
    }

    /**
     * Attenuation factor used if listener is outside of `emission_angle_degrees` and
     * `emission_angle_enabled` is set, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_emission_angle_filter_attenuation_db
     */
    fun getEmissionAngleFilterAttenuationDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionAngleFilterAttenuationDbBind, segment)
    }

    /**
     * The cutoff frequency of the attenuation low-pass filter, in Hz. A sound above this frequency is
     * attenuated more than a sound below this frequency. To disable this effect, set this to `20500`
     * as this frequency is above the human hearing limit.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_attenuation_filter_cutoff_hz
     */
    fun setAttenuationFilterCutoffHz(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAttenuationFilterCutoffHzBind, segment, degrees)
    }

    /**
     * The cutoff frequency of the attenuation low-pass filter, in Hz. A sound above this frequency is
     * attenuated more than a sound below this frequency. To disable this effect, set this to `20500`
     * as this frequency is above the human hearing limit.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_attenuation_filter_cutoff_hz
     */
    fun getAttenuationFilterCutoffHz(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAttenuationFilterCutoffHzBind, segment)
    }

    /**
     * Amount how much the filter affects the loudness, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_attenuation_filter_db
     */
    fun setAttenuationFilterDb(db: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAttenuationFilterDbBind, segment, db)
    }

    /**
     * Amount how much the filter affects the loudness, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_attenuation_filter_db
     */
    fun getAttenuationFilterDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAttenuationFilterDbBind, segment)
    }

    /**
     * Decides if audio should get quieter with distance linearly, quadratically, logarithmically, or
     * not be affected by distance, effectively disabling attenuation.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_attenuation_model
     */
    fun setAttenuationModel(model: AudioStreamPlayer3D.AttenuationModel) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAttenuationModelBind, segment, model.value)
    }

    /**
     * Decides if audio should get quieter with distance linearly, quadratically, logarithmically, or
     * not be affected by distance, effectively disabling attenuation.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_attenuation_model
     */
    fun getAttenuationModel(): AudioStreamPlayer3D.AttenuationModel {
        return AudioStreamPlayer3D.AttenuationModel(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAttenuationModelBind, segment))
    }

    /**
     * Decides in which step the Doppler effect should be calculated. Note: If `doppler_tracking` is
     * not `DopplerTracking.DISABLED` but the current `Camera3D`/`AudioListener3D` has doppler tracking
     * disabled, the Doppler effect will be heard but will not take the movement of the current
     * listener into account. If accurate Doppler effect is desired, doppler tracking should be enabled
     * on both the `AudioStreamPlayer3D` and the current `Camera3D`/`AudioListener3D`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_doppler_tracking
     */
    fun setDopplerTracking(mode: AudioStreamPlayer3D.DopplerTracking) {
        ObjectCalls.ptrcallWithLongArg(Binds.setDopplerTrackingBind, segment, mode.value)
    }

    /**
     * Decides in which step the Doppler effect should be calculated. Note: If `doppler_tracking` is
     * not `DopplerTracking.DISABLED` but the current `Camera3D`/`AudioListener3D` has doppler tracking
     * disabled, the Doppler effect will be heard but will not take the movement of the current
     * listener into account. If accurate Doppler effect is desired, doppler tracking should be enabled
     * on both the `AudioStreamPlayer3D` and the current `Camera3D`/`AudioListener3D`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_doppler_tracking
     */
    fun getDopplerTracking(): AudioStreamPlayer3D.DopplerTracking {
        return AudioStreamPlayer3D.DopplerTracking(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDopplerTrackingBind, segment))
    }

    /**
     * If `true`, the playback is paused. You can resume it by setting `stream_paused` to `false`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_stream_paused
     */
    fun setStreamPaused(pause: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setStreamPausedBind, segment, pause)
    }

    /**
     * If `true`, the playback is paused. You can resume it by setting `stream_paused` to `false`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_stream_paused
     */
    fun getStreamPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getStreamPausedBind, segment)
    }

    /**
     * The maximum number of sounds this node can play at the same time. Playing additional sounds
     * after this value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_max_polyphony
     */
    fun setMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxPolyphonyBind, segment, maxPolyphony)
    }

    /**
     * The maximum number of sounds this node can play at the same time. Playing additional sounds
     * after this value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_max_polyphony
     */
    fun getMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxPolyphonyBind, segment)
    }

    /**
     * Scales the panning strength for this node by multiplying the base
     * `ProjectSettings.audio/general/3d_panning_strength` by this factor. If the product is `0.0` then
     * stereo panning is disabled and the volume is the same for all channels. If the product is `1.0`
     * then one of the channels will be muted when the sound is located exactly to the left (or right)
     * of the listener. Two speaker stereo arrangements implement the WebAudio standard for
     * StereoPannerNode Panning (https://webaudio.github.io/web-audio-api/#stereopanner-algorithm)
     * where the volume is cosine of half the azimuth angle to the ear. For other speaker arrangements
     * such as the 5.1 and 7.1 the SPCAP (Speaker-Placement Correction Amplitude) algorithm is
     * implemented.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_panning_strength
     */
    fun setPanningStrength(panningStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPanningStrengthBind, segment, panningStrength)
    }

    /**
     * Scales the panning strength for this node by multiplying the base
     * `ProjectSettings.audio/general/3d_panning_strength` by this factor. If the product is `0.0` then
     * stereo panning is disabled and the volume is the same for all channels. If the product is `1.0`
     * then one of the channels will be muted when the sound is located exactly to the left (or right)
     * of the listener. Two speaker stereo arrangements implement the WebAudio standard for
     * StereoPannerNode Panning (https://webaudio.github.io/web-audio-api/#stereopanner-algorithm)
     * where the volume is cosine of half the azimuth angle to the ear. For other speaker arrangements
     * such as the 5.1 and 7.1 the SPCAP (Speaker-Placement Correction Amplitude) algorithm is
     * implemented.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_panning_strength
     */
    fun getPanningStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPanningStrengthBind, segment)
    }

    /**
     * Returns whether the `AudioStreamPlayer` can return the `AudioStreamPlayback` object or not.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.has_stream_playback
     */
    fun hasStreamPlayback(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasStreamPlaybackBind, segment)
    }

    /**
     * Returns the `AudioStreamPlayback` object associated with this `AudioStreamPlayer3D`.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_stream_playback
     */
    fun getStreamPlayback(): AudioStreamPlayback? {
        return AudioStreamPlayback.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getStreamPlaybackBind, segment))
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.set_playback_type
     */
    fun setPlaybackType(playbackType: AudioServer.PlaybackType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPlaybackTypeBind, segment, playbackType.value)
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.get_playback_type
     */
    fun getPlaybackType(): AudioServer.PlaybackType {
        return AudioServer.PlaybackType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaybackTypeBind, segment))
    }

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val finished: String = "finished"
    }

    /**
     * Godot's `AudioStreamPlayer3D.AttenuationModel` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AudioStreamPlayer3D.AttenuationModel.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamPlayer3D.AttenuationModel
     */
    @JvmInline
    value class AttenuationModel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Attenuation of loudness according to linear distance.
             *
             * Generated from Godot docs: AudioStreamPlayer3D.ATTENUATION_INVERSE_DISTANCE
             */
            val INVERSE_DISTANCE: AttenuationModel get() = AttenuationModel(0L)
            /**
             * Attenuation of loudness according to squared distance.
             *
             * Generated from Godot docs: AudioStreamPlayer3D.ATTENUATION_INVERSE_SQUARE_DISTANCE
             */
            val INVERSE_SQUARE_DISTANCE: AttenuationModel get() = AttenuationModel(1L)
            /**
             * Attenuation of loudness according to logarithmic distance.
             *
             * Generated from Godot docs: AudioStreamPlayer3D.ATTENUATION_LOGARITHMIC
             */
            val LOGARITHMIC: AttenuationModel get() = AttenuationModel(2L)
            /**
             * No attenuation of loudness according to distance. The sound will still be heard positionally,
             * unlike an `AudioStreamPlayer`. `AttenuationModel.DISABLED` can be combined with a `max_distance`
             * value greater than `0.0` to achieve linear attenuation clamped to a sphere of a defined size.
             *
             * Generated from Godot docs: AudioStreamPlayer3D.ATTENUATION_DISABLED
             */
            val DISABLED: AttenuationModel get() = AttenuationModel(3L)
        }
    }

    /**
     * Godot's `AudioStreamPlayer3D.DopplerTracking` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AudioStreamPlayer3D.DopplerTracking.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamPlayer3D.DopplerTracking
     */
    @JvmInline
    value class DopplerTracking(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables doppler tracking.
             *
             * Generated from Godot docs: AudioStreamPlayer3D.DOPPLER_TRACKING_DISABLED
             */
            val DISABLED: DopplerTracking get() = DopplerTracking(0L)
            /**
             * Executes doppler tracking during process frames (see `Node.NOTIFICATION_INTERNAL_PROCESS`).
             *
             * Generated from Godot docs: AudioStreamPlayer3D.DOPPLER_TRACKING_IDLE_STEP
             */
            val IDLE_STEP: DopplerTracking get() = DopplerTracking(1L)
            /**
             * Executes doppler tracking during physics frames (see
             * `Node.NOTIFICATION_INTERNAL_PHYSICS_PROCESS`).
             *
             * Generated from Godot docs: AudioStreamPlayer3D.DOPPLER_TRACKING_PHYSICS_STEP
             */
            val PHYSICS_STEP: DopplerTracking get() = DopplerTracking(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlayer3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPlayer3D? =
            if (handle.address() == 0L) null else AudioStreamPlayer3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_STREAM_HASH = 2210767741L
        @JvmField
        val setStreamBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_stream", SET_STREAM_HASH)

        private const val GET_STREAM_HASH = 160907539L
        @JvmField
        val getStreamBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_stream", GET_STREAM_HASH)

        private const val SET_VOLUME_DB_HASH = 373806689L
        @JvmField
        val setVolumeDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_volume_db", SET_VOLUME_DB_HASH)

        private const val GET_VOLUME_DB_HASH = 1740695150L
        @JvmField
        val getVolumeDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_volume_db", GET_VOLUME_DB_HASH)

        private const val SET_VOLUME_LINEAR_HASH = 373806689L
        @JvmField
        val setVolumeLinearBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_volume_linear", SET_VOLUME_LINEAR_HASH)

        private const val GET_VOLUME_LINEAR_HASH = 1740695150L
        @JvmField
        val getVolumeLinearBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_volume_linear", GET_VOLUME_LINEAR_HASH)

        private const val SET_UNIT_SIZE_HASH = 373806689L
        @JvmField
        val setUnitSizeBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_unit_size", SET_UNIT_SIZE_HASH)

        private const val GET_UNIT_SIZE_HASH = 1740695150L
        @JvmField
        val getUnitSizeBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_unit_size", GET_UNIT_SIZE_HASH)

        private const val SET_MAX_DB_HASH = 373806689L
        @JvmField
        val setMaxDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_max_db", SET_MAX_DB_HASH)

        private const val GET_MAX_DB_HASH = 1740695150L
        @JvmField
        val getMaxDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_max_db", GET_MAX_DB_HASH)

        private const val SET_PITCH_SCALE_HASH = 373806689L
        @JvmField
        val setPitchScaleBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_pitch_scale", SET_PITCH_SCALE_HASH)

        private const val GET_PITCH_SCALE_HASH = 1740695150L
        @JvmField
        val getPitchScaleBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_pitch_scale", GET_PITCH_SCALE_HASH)

        private const val PLAY_HASH = 1958160172L
        @JvmField
        val playBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "play", PLAY_HASH)

        private const val SEEK_HASH = 373806689L
        @JvmField
        val seekBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "seek", SEEK_HASH)

        private const val STOP_HASH = 3218959716L
        @JvmField
        val stopBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "stop", STOP_HASH)

        private const val IS_PLAYING_HASH = 36873697L
        @JvmField
        val isPlayingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "is_playing", IS_PLAYING_HASH)

        private const val GET_PLAYBACK_POSITION_HASH = 191475506L
        @JvmField
        val getPlaybackPositionBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_playback_position", GET_PLAYBACK_POSITION_HASH)

        private const val SET_BUS_HASH = 3304788590L
        @JvmField
        val setBusBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_bus", SET_BUS_HASH)

        private const val GET_BUS_HASH = 2002593661L
        @JvmField
        val getBusBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_bus", GET_BUS_HASH)

        private const val SET_AUTOPLAY_HASH = 2586408642L
        @JvmField
        val setAutoplayBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_autoplay", SET_AUTOPLAY_HASH)

        private const val IS_AUTOPLAY_ENABLED_HASH = 36873697L
        @JvmField
        val isAutoplayEnabledBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "is_autoplay_enabled", IS_AUTOPLAY_ENABLED_HASH)

        private const val SET_PLAYING_HASH = 2586408642L
        @JvmField
        val setPlayingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_playing", SET_PLAYING_HASH)

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        @JvmField
        val setMaxDistanceBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_max_distance", SET_MAX_DISTANCE_HASH)

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        @JvmField
        val getMaxDistanceBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_max_distance", GET_MAX_DISTANCE_HASH)

        private const val SET_AREA_MASK_HASH = 1286410249L
        @JvmField
        val setAreaMaskBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_area_mask", SET_AREA_MASK_HASH)

        private const val GET_AREA_MASK_HASH = 3905245786L
        @JvmField
        val getAreaMaskBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_area_mask", GET_AREA_MASK_HASH)

        private const val SET_EMISSION_ANGLE_HASH = 373806689L
        @JvmField
        val setEmissionAngleBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_emission_angle", SET_EMISSION_ANGLE_HASH)

        private const val GET_EMISSION_ANGLE_HASH = 1740695150L
        @JvmField
        val getEmissionAngleBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_emission_angle", GET_EMISSION_ANGLE_HASH)

        private const val SET_EMISSION_ANGLE_ENABLED_HASH = 2586408642L
        @JvmField
        val setEmissionAngleEnabledBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_emission_angle_enabled", SET_EMISSION_ANGLE_ENABLED_HASH)

        private const val IS_EMISSION_ANGLE_ENABLED_HASH = 36873697L
        @JvmField
        val isEmissionAngleEnabledBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "is_emission_angle_enabled", IS_EMISSION_ANGLE_ENABLED_HASH)

        private const val SET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH = 373806689L
        @JvmField
        val setEmissionAngleFilterAttenuationDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_emission_angle_filter_attenuation_db", SET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH)

        private const val GET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH = 1740695150L
        @JvmField
        val getEmissionAngleFilterAttenuationDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_emission_angle_filter_attenuation_db", GET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH)

        private const val SET_ATTENUATION_FILTER_CUTOFF_HZ_HASH = 373806689L
        @JvmField
        val setAttenuationFilterCutoffHzBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_attenuation_filter_cutoff_hz", SET_ATTENUATION_FILTER_CUTOFF_HZ_HASH)

        private const val GET_ATTENUATION_FILTER_CUTOFF_HZ_HASH = 1740695150L
        @JvmField
        val getAttenuationFilterCutoffHzBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_attenuation_filter_cutoff_hz", GET_ATTENUATION_FILTER_CUTOFF_HZ_HASH)

        private const val SET_ATTENUATION_FILTER_DB_HASH = 373806689L
        @JvmField
        val setAttenuationFilterDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_attenuation_filter_db", SET_ATTENUATION_FILTER_DB_HASH)

        private const val GET_ATTENUATION_FILTER_DB_HASH = 1740695150L
        @JvmField
        val getAttenuationFilterDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_attenuation_filter_db", GET_ATTENUATION_FILTER_DB_HASH)

        private const val SET_ATTENUATION_MODEL_HASH = 2988086229L
        @JvmField
        val setAttenuationModelBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_attenuation_model", SET_ATTENUATION_MODEL_HASH)

        private const val GET_ATTENUATION_MODEL_HASH = 3035106060L
        @JvmField
        val getAttenuationModelBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_attenuation_model", GET_ATTENUATION_MODEL_HASH)

        private const val SET_DOPPLER_TRACKING_HASH = 3968161450L
        @JvmField
        val setDopplerTrackingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_doppler_tracking", SET_DOPPLER_TRACKING_HASH)

        private const val GET_DOPPLER_TRACKING_HASH = 1702418664L
        @JvmField
        val getDopplerTrackingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_doppler_tracking", GET_DOPPLER_TRACKING_HASH)

        private const val SET_STREAM_PAUSED_HASH = 2586408642L
        @JvmField
        val setStreamPausedBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_stream_paused", SET_STREAM_PAUSED_HASH)

        private const val GET_STREAM_PAUSED_HASH = 36873697L
        @JvmField
        val getStreamPausedBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_stream_paused", GET_STREAM_PAUSED_HASH)

        private const val SET_MAX_POLYPHONY_HASH = 1286410249L
        @JvmField
        val setMaxPolyphonyBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_max_polyphony", SET_MAX_POLYPHONY_HASH)

        private const val GET_MAX_POLYPHONY_HASH = 3905245786L
        @JvmField
        val getMaxPolyphonyBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_max_polyphony", GET_MAX_POLYPHONY_HASH)

        private const val SET_PANNING_STRENGTH_HASH = 373806689L
        @JvmField
        val setPanningStrengthBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_panning_strength", SET_PANNING_STRENGTH_HASH)

        private const val GET_PANNING_STRENGTH_HASH = 1740695150L
        @JvmField
        val getPanningStrengthBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_panning_strength", GET_PANNING_STRENGTH_HASH)

        private const val HAS_STREAM_PLAYBACK_HASH = 2240911060L
        @JvmField
        val hasStreamPlaybackBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "has_stream_playback", HAS_STREAM_PLAYBACK_HASH)

        private const val GET_STREAM_PLAYBACK_HASH = 210135309L
        @JvmField
        val getStreamPlaybackBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_stream_playback", GET_STREAM_PLAYBACK_HASH)

        private const val SET_PLAYBACK_TYPE_HASH = 725473817L
        @JvmField
        val setPlaybackTypeBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_playback_type", SET_PLAYBACK_TYPE_HASH)

        private const val GET_PLAYBACK_TYPE_HASH = 4011264623L
        @JvmField
        val getPlaybackTypeBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_playback_type", GET_PLAYBACK_TYPE_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plays positional sound in 2D space.
 *
 * Generated from Godot docs: AudioStreamPlayer2D
 */
class AudioStreamPlayer2D(handle: MemorySegment) : Node2D(handle) {
    var stream: AudioStream?
        @JvmName("streamProperty")
        get() = getStream()
        @JvmName("setStreamProperty")
        set(value) = setStream(value)

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

    var attenuation: Double
        @JvmName("attenuationProperty")
        get() = getAttenuation()
        @JvmName("setAttenuationProperty")
        set(value) = setAttenuation(value)

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

    var playbackType: Long
        @JvmName("playbackTypeProperty")
        get() = getPlaybackType()
        @JvmName("setPlaybackTypeProperty")
        set(value) = setPlaybackType(value)

    /**
     * The `AudioStream` object to be played.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_stream
     */
    fun setStream(stream: AudioStream?) {
        ObjectCalls.ptrcallWithObjectArgs(setStreamBind, handle, listOf(stream?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `AudioStream` object to be played.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_stream
     */
    fun getStream(): AudioStream? {
        return AudioStream.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamBind, handle))
    }

    /**
     * Base volume before attenuation, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_volume_db
     */
    fun setVolumeDb(volumeDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeDbBind, handle, volumeDb)
    }

    /**
     * Base volume before attenuation, in decibels.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_volume_db
     */
    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeDbBind, handle)
    }

    /**
     * Base volume before attenuation, as a linear value. Note: This member modifies `volume_db` for
     * convenience. The returned value is equivalent to the result of `@GlobalScope.db_to_linear` on
     * `volume_db`. Setting this member is equivalent to setting `volume_db` to the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_volume_linear
     */
    fun setVolumeLinear(volumeLinear: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeLinearBind, handle, volumeLinear)
    }

    /**
     * Base volume before attenuation, as a linear value. Note: This member modifies `volume_db` for
     * convenience. The returned value is equivalent to the result of `@GlobalScope.db_to_linear` on
     * `volume_db`. Setting this member is equivalent to setting `volume_db` to the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_volume_linear
     */
    fun getVolumeLinear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeLinearBind, handle)
    }

    /**
     * The pitch and the tempo of the audio, as a multiplier of the audio sample's sample rate.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_pitch_scale
     */
    fun setPitchScale(pitchScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPitchScaleBind, handle, pitchScale)
    }

    /**
     * The pitch and the tempo of the audio, as a multiplier of the audio sample's sample rate.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_pitch_scale
     */
    fun getPitchScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPitchScaleBind, handle)
    }

    /**
     * Queues the audio to play on the next physics frame, from the given position `from_position`, in
     * seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.play
     */
    fun play(fromPosition: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(playBind, handle, fromPosition)
    }

    /**
     * Sets the position from which audio will be played, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.seek
     */
    fun seek(toPosition: Double) {
        ObjectCalls.ptrcallWithDoubleArg(seekBind, handle, toPosition)
    }

    /**
     * Stops the audio.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    /**
     * If `true`, audio is playing or is queued to be played (see `play`).
     *
     * Generated from Godot docs: AudioStreamPlayer2D.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    /**
     * Returns the position in the `AudioStream`.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_playback_position
     */
    fun getPlaybackPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackPositionBind, handle)
    }

    /**
     * Bus on which this audio is playing. Note: When setting this property, keep in mind that no
     * validation is performed to see if the given name matches an existing bus. This is because audio
     * bus layouts might be loaded after this property is set. If this given name can't be resolved at
     * runtime, it will fall back to `"Master"`.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_bus
     */
    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(setBusBind, handle, bus)
    }

    /**
     * Bus on which this audio is playing. Note: When setting this property, keep in mind that no
     * validation is performed to see if the given name matches an existing bus. This is because audio
     * bus layouts might be loaded after this property is set. If this given name can't be resolved at
     * runtime, it will fall back to `"Master"`.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_bus
     */
    fun getBus(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getBusBind, handle)
    }

    /**
     * If `true`, audio plays when added to scene tree.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_autoplay
     */
    fun setAutoplay(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoplayBind, handle, enable)
    }

    /**
     * If `true`, audio plays when added to scene tree.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.is_autoplay_enabled
     */
    fun isAutoplayEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoplayEnabledBind, handle)
    }

    /**
     * If `true`, audio is playing or is queued to be played (see `play`).
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_playing
     */
    fun setPlaying(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPlayingBind, handle, enable)
    }

    /**
     * Maximum distance from which audio is still hearable.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_max_distance
     */
    fun setMaxDistance(pixels: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDistanceBind, handle, pixels)
    }

    /**
     * Maximum distance from which audio is still hearable.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_max_distance
     */
    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDistanceBind, handle)
    }

    /**
     * The volume is attenuated over distance with this as an exponent.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_attenuation
     */
    fun setAttenuation(curve: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAttenuationBind, handle, curve)
    }

    /**
     * The volume is attenuated over distance with this as an exponent.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_attenuation
     */
    fun getAttenuation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAttenuationBind, handle)
    }

    /**
     * Determines which `Area2D` layers affect the sound for reverb and audio bus effects. Areas can be
     * used to redirect `AudioStream`s so that they play in a certain audio bus. An example of how you
     * might use this is making a "water" area so that sounds played in the water are redirected
     * through an audio bus to make them sound like they are being played underwater.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_area_mask
     */
    fun setAreaMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAreaMaskBind, handle, mask)
    }

    /**
     * Determines which `Area2D` layers affect the sound for reverb and audio bus effects. Areas can be
     * used to redirect `AudioStream`s so that they play in a certain audio bus. An example of how you
     * might use this is making a "water" area so that sounds played in the water are redirected
     * through an audio bus to make them sound like they are being played underwater.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_area_mask
     */
    fun getAreaMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAreaMaskBind, handle)
    }

    /**
     * If `true`, the playback is paused. You can resume it by setting `stream_paused` to `false`.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_stream_paused
     */
    fun setStreamPaused(pause: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setStreamPausedBind, handle, pause)
    }

    /**
     * If `true`, the playback is paused. You can resume it by setting `stream_paused` to `false`.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_stream_paused
     */
    fun getStreamPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getStreamPausedBind, handle)
    }

    /**
     * The maximum number of sounds this node can play at the same time. Playing additional sounds
     * after this value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_max_polyphony
     */
    fun setMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxPolyphonyBind, handle, maxPolyphony)
    }

    /**
     * The maximum number of sounds this node can play at the same time. Playing additional sounds
     * after this value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_max_polyphony
     */
    fun getMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxPolyphonyBind, handle)
    }

    /**
     * Scales the panning strength for this node by multiplying the base
     * `ProjectSettings.audio/general/2d_panning_strength` with this factor. Higher values will pan
     * audio from left to right more dramatically than lower values.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_panning_strength
     */
    fun setPanningStrength(panningStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPanningStrengthBind, handle, panningStrength)
    }

    /**
     * Scales the panning strength for this node by multiplying the base
     * `ProjectSettings.audio/general/2d_panning_strength` with this factor. Higher values will pan
     * audio from left to right more dramatically than lower values.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_panning_strength
     */
    fun getPanningStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPanningStrengthBind, handle)
    }

    /**
     * Returns whether the `AudioStreamPlayer` can return the `AudioStreamPlayback` object or not.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.has_stream_playback
     */
    fun hasStreamPlayback(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasStreamPlaybackBind, handle)
    }

    /**
     * Returns the `AudioStreamPlayback` object associated with this `AudioStreamPlayer2D`.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_stream_playback
     */
    fun getStreamPlayback(): AudioStreamPlayback? {
        return AudioStreamPlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamPlaybackBind, handle))
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.set_playback_type
     */
    fun setPlaybackType(playbackType: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlaybackTypeBind, handle, playbackType)
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer2D.get_playback_type
     */
    fun getPlaybackType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlaybackTypeBind, handle)
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlayer2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlayer2D? =
            if (handle.address() == 0L) null else AudioStreamPlayer2D(handle)

        private const val SET_STREAM_HASH = 2210767741L
        private val setStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_stream", SET_STREAM_HASH)
        }

        private const val GET_STREAM_HASH = 160907539L
        private val getStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_stream", GET_STREAM_HASH)
        }

        private const val SET_VOLUME_DB_HASH = 373806689L
        private val setVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_volume_db", SET_VOLUME_DB_HASH)
        }

        private const val GET_VOLUME_DB_HASH = 1740695150L
        private val getVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_volume_db", GET_VOLUME_DB_HASH)
        }

        private const val SET_VOLUME_LINEAR_HASH = 373806689L
        private val setVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_volume_linear", SET_VOLUME_LINEAR_HASH)
        }

        private const val GET_VOLUME_LINEAR_HASH = 1740695150L
        private val getVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_volume_linear", GET_VOLUME_LINEAR_HASH)
        }

        private const val SET_PITCH_SCALE_HASH = 373806689L
        private val setPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_pitch_scale", SET_PITCH_SCALE_HASH)
        }

        private const val GET_PITCH_SCALE_HASH = 1740695150L
        private val getPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_pitch_scale", GET_PITCH_SCALE_HASH)
        }

        private const val PLAY_HASH = 1958160172L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "play", PLAY_HASH)
        }

        private const val SEEK_HASH = 373806689L
        private val seekBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "seek", SEEK_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "stop", STOP_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "is_playing", IS_PLAYING_HASH)
        }

        private const val GET_PLAYBACK_POSITION_HASH = 191475506L
        private val getPlaybackPositionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_playback_position", GET_PLAYBACK_POSITION_HASH)
        }

        private const val SET_BUS_HASH = 3304788590L
        private val setBusBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_bus", SET_BUS_HASH)
        }

        private const val GET_BUS_HASH = 2002593661L
        private val getBusBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_bus", GET_BUS_HASH)
        }

        private const val SET_AUTOPLAY_HASH = 2586408642L
        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_autoplay", SET_AUTOPLAY_HASH)
        }

        private const val IS_AUTOPLAY_ENABLED_HASH = 36873697L
        private val isAutoplayEnabledBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "is_autoplay_enabled", IS_AUTOPLAY_ENABLED_HASH)
        }

        private const val SET_PLAYING_HASH = 2586408642L
        private val setPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_playing", SET_PLAYING_HASH)
        }

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        private val setMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_max_distance", SET_MAX_DISTANCE_HASH)
        }

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        private val getMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_max_distance", GET_MAX_DISTANCE_HASH)
        }

        private const val SET_ATTENUATION_HASH = 373806689L
        private val setAttenuationBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_attenuation", SET_ATTENUATION_HASH)
        }

        private const val GET_ATTENUATION_HASH = 1740695150L
        private val getAttenuationBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_attenuation", GET_ATTENUATION_HASH)
        }

        private const val SET_AREA_MASK_HASH = 1286410249L
        private val setAreaMaskBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_area_mask", SET_AREA_MASK_HASH)
        }

        private const val GET_AREA_MASK_HASH = 3905245786L
        private val getAreaMaskBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_area_mask", GET_AREA_MASK_HASH)
        }

        private const val SET_STREAM_PAUSED_HASH = 2586408642L
        private val setStreamPausedBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_stream_paused", SET_STREAM_PAUSED_HASH)
        }

        private const val GET_STREAM_PAUSED_HASH = 36873697L
        private val getStreamPausedBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_stream_paused", GET_STREAM_PAUSED_HASH)
        }

        private const val SET_MAX_POLYPHONY_HASH = 1286410249L
        private val setMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_max_polyphony", SET_MAX_POLYPHONY_HASH)
        }

        private const val GET_MAX_POLYPHONY_HASH = 3905245786L
        private val getMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_max_polyphony", GET_MAX_POLYPHONY_HASH)
        }

        private const val SET_PANNING_STRENGTH_HASH = 373806689L
        private val setPanningStrengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_panning_strength", SET_PANNING_STRENGTH_HASH)
        }

        private const val GET_PANNING_STRENGTH_HASH = 1740695150L
        private val getPanningStrengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_panning_strength", GET_PANNING_STRENGTH_HASH)
        }

        private const val HAS_STREAM_PLAYBACK_HASH = 2240911060L
        private val hasStreamPlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "has_stream_playback", HAS_STREAM_PLAYBACK_HASH)
        }

        private const val GET_STREAM_PLAYBACK_HASH = 210135309L
        private val getStreamPlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_stream_playback", GET_STREAM_PLAYBACK_HASH)
        }

        private const val SET_PLAYBACK_TYPE_HASH = 725473817L
        private val setPlaybackTypeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "set_playback_type", SET_PLAYBACK_TYPE_HASH)
        }

        private const val GET_PLAYBACK_TYPE_HASH = 4011264623L
        private val getPlaybackTypeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer2D", "get_playback_type", GET_PLAYBACK_TYPE_HASH)
        }
    }
}

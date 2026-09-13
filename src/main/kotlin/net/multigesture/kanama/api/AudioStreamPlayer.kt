package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * A node for audio playback.
 *
 * Generated from Godot docs: AudioStreamPlayer
 */
class AudioStreamPlayer(handle: GodotHandle) : Node(handle) {

    /**
     * Plays a sound from the beginning, or the given `from_position` in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer.play
     */
    fun play(fromPosition: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(playBind, segment, fromPosition)
    }

    /**
     * Restarts all sounds to be played from the given `to_position`, in seconds. Does nothing if no
     * sounds are playing.
     *
     * Generated from Godot docs: AudioStreamPlayer.seek
     */
    fun seek(toPosition: Double) {
        ObjectCalls.ptrcallWithDoubleArg(seekBind, segment, toPosition)
    }

    /**
     * Stops all sounds from this node.
     *
     * Generated from Godot docs: AudioStreamPlayer.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, segment)
    }

    /**
     * If `true`, this node is playing sounds. Setting this property has the same effect as `play` and
     * `stop`.
     *
     * Generated from Godot docs: AudioStreamPlayer.is_playing
     */
    fun isPlaying(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, segment)

    /**
     * If `true`, this node is playing sounds. Setting this property has the same effect as `play` and
     * `stop`.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_playing
     */
    fun setPlaying(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPlayingBind, segment, enable)
    }

    /**
     * If `true`, the sounds are paused. Setting `stream_paused` to `false` resumes all sounds. Note:
     * This property is automatically changed when exiting or entering the tree, or this node is paused
     * (see `Node.process_mode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.set_stream_paused
     */
    fun setStreamPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setStreamPausedBind, segment, paused)
    }

    /**
     * If `true`, the sounds are paused. Setting `stream_paused` to `false` resumes all sounds. Note:
     * This property is automatically changed when exiting or entering the tree, or this node is paused
     * (see `Node.process_mode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.get_stream_paused
     */
    fun getStreamPaused(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(getStreamPausedBind, segment)

    /**
     * The `AudioStream` resource to be played. Setting this property stops all currently playing
     * sounds. If left empty, the `AudioStreamPlayer` does not work.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_stream
     */
    fun setStream(stream: AudioStream?) {
        ObjectCalls.ptrcallWithObjectArgs(setStreamBind, segment, listOf(stream?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Loads an `AudioStream` from [path], assigns it to this player, and releases Kanama's temporary
     * resource wrapper. Use this when mirroring GDScript's `stream = load(path)` pattern.
     */
    fun setStreamFromPath(path: String, cacheMode: Long = ResourceLoader.CACHE_MODE_REUSE) {
        ResourceLoader.loadAudioStream(path, cacheMode)?.use { stream ->
            setStream(stream)
        }
    }

    /**
     * The `AudioStream` resource to be played. Setting this property stops all currently playing
     * sounds. If left empty, the `AudioStreamPlayer` does not work.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_stream
     */
    fun getStream(): AudioStream? =
        AudioStream.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamBind, segment))

    /**
     * Volume of sound, in decibels. This is an offset of the `stream`'s volume. Note: To convert
     * between decibel and linear energy (like most volume sliders do), use `volume_linear`, or
     * `@GlobalScope.db_to_linear` and `@GlobalScope.linear_to_db`.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_volume_db
     */
    fun setVolumeDb(volumeDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeDbBind, segment, volumeDb)
    }

    /**
     * Volume of sound, in decibels. This is an offset of the `stream`'s volume. Note: To convert
     * between decibel and linear energy (like most volume sliders do), use `volume_linear`, or
     * `@GlobalScope.db_to_linear` and `@GlobalScope.linear_to_db`.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_volume_db
     */
    fun getVolumeDb(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getVolumeDbBind, segment)

    /**
     * Volume of sound, as a linear value. Note: This member modifies `volume_db` for convenience. The
     * returned value is equivalent to the result of `@GlobalScope.db_to_linear` on `volume_db`.
     * Setting this member is equivalent to setting `volume_db` to the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_volume_linear
     */
    fun setVolumeLinear(volumeLinear: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeLinearBind, segment, volumeLinear)
    }

    /**
     * Volume of sound, as a linear value. Note: This member modifies `volume_db` for convenience. The
     * returned value is equivalent to the result of `@GlobalScope.db_to_linear` on `volume_db`.
     * Setting this member is equivalent to setting `volume_db` to the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_volume_linear
     */
    fun getVolumeLinear(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getVolumeLinearBind, segment)

    /**
     * The audio's pitch and tempo, as a multiplier of the `stream`'s sample rate. A value of `2.0`
     * doubles the audio's pitch, while a value of `0.5` halves the pitch.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_pitch_scale
     */
    fun setPitchScale(pitchScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPitchScaleBind, segment, pitchScale)
    }

    /**
     * The audio's pitch and tempo, as a multiplier of the `stream`'s sample rate. A value of `2.0`
     * doubles the audio's pitch, while a value of `0.5` halves the pitch.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_pitch_scale
     */
    fun getPitchScale(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getPitchScaleBind, segment)

    /**
     * The target bus name. All sounds from this node will be playing on this bus. Note: At runtime, if
     * no bus with the given name exists, all sounds will fall back on `"Master"`. See also
     * `AudioServer.get_bus_name`.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_bus
     */
    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(setBusBind, segment, bus)
    }

    /**
     * The target bus name. All sounds from this node will be playing on this bus. Note: At runtime, if
     * no bus with the given name exists, all sounds will fall back on `"Master"`. See also
     * `AudioServer.get_bus_name`.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_bus
     */
    fun getBus(): String =
        ObjectCalls.ptrcallNoArgsRetStringName(getBusBind, segment)

    /**
     * If `true`, this node calls `play` when entering the tree.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_autoplay
     */
    fun setAutoplay(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoplayBind, segment, enabled)
    }

    /**
     * If `true`, this node calls `play` when entering the tree.
     *
     * Generated from Godot docs: AudioStreamPlayer.is_autoplay_enabled
     */
    fun isAutoplayEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isAutoplayEnabledBind, segment)

    /**
     * The mix target channels. Has no effect when two speakers or less are detected (see
     * `AudioServer.SpeakerMode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.set_mix_target
     */
    fun setMixTarget(mixTarget: Long) {
        ObjectCalls.ptrcallWithLongArg(setMixTargetBind, segment, mixTarget)
    }

    /**
     * The mix target channels. Has no effect when two speakers or less are detected (see
     * `AudioServer.SpeakerMode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.get_mix_target
     */
    fun getMixTarget(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getMixTargetBind, segment)

    /**
     * The maximum number of sounds this node can play at the same time. Calling `play` after this
     * value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_max_polyphony
     */
    fun setMaxPolyphony(maxPolyphony: Long) {
        ObjectCalls.ptrcallWithIntArg(setMaxPolyphonyBind, segment, maxPolyphony.toInt())
    }

    /**
     * The maximum number of sounds this node can play at the same time. Calling `play` after this
     * value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_max_polyphony
     */
    fun getMaxPolyphony(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getMaxPolyphonyBind, segment).toLong()

    /**
     * Returns the position in the `AudioStream` of the latest sound, in seconds. Returns `0.0` if no
     * sounds are playing. Note: The position is not always accurate, as the `AudioServer` does not mix
     * audio every processed frame. To get more accurate results, add
     * `AudioServer.get_time_since_last_mix` to the returned position. Note: This method always returns
     * `0.0` if the `stream` is an `AudioStreamInteractive`, since it can have multiple clips playing
     * at once.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_playback_position
     */
    fun getPlaybackPosition(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackPositionBind, segment)

    /**
     * Returns `true` if any sound is active, even if `stream_paused` is set to `true`. See also
     * `playing` and `get_stream_playback`.
     *
     * Generated from Godot docs: AudioStreamPlayer.has_stream_playback
     */
    fun hasStreamPlayback(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(hasStreamPlaybackBind, segment)

    /**
     * Returns the latest `AudioStreamPlayback` of this node, usually the most recently created by
     * `play`. If no sounds are playing, this method fails and returns an empty playback.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_stream_playback
     */
    fun getStreamPlayback(): AudioStreamPlayback? =
        AudioStreamPlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamPlaybackBind, segment))

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_playback_type
     */
    fun setPlaybackType(playbackType: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlaybackTypeBind, segment, playbackType)
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_playback_type
     */
    fun getPlaybackType(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getPlaybackTypeBind, segment)

    companion object {
        const val MIX_TARGET_STEREO = 0L
        const val MIX_TARGET_SURROUND = 1L
        const val MIX_TARGET_CENTER = 2L

        private const val DOUBLE_VOID_HASH = 373806689L
        private const val PLAY_HASH = 1958160172L
        private const val NOARGS_VOID_HASH = 3218959716L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val BOOL_VOID_HASH = 2586408642L
        private const val NOARGS_DOUBLE_HASH = 1740695150L
        private const val PLAYBACK_POSITION_HASH = 191475506L
        private const val STRING_NAME_VOID_HASH = 3304788590L
        private const val NOARGS_STRING_NAME_HASH = 2002593661L
        private const val LONG_VOID_HASH = 1286410249L
        private const val NOARGS_LONG_HASH = 3905245786L
        private const val SET_STREAM_HASH = 2210767741L
        private const val GET_STREAM_HASH = 160907539L
        private const val SET_MIX_TARGET_HASH = 2300306138L
        private const val GET_MIX_TARGET_HASH = 172807476L
        private const val HAS_STREAM_PLAYBACK_HASH = 2240911060L
        private const val GET_STREAM_PLAYBACK_HASH = 210135309L
        private const val SET_PLAYBACK_TYPE_HASH = 725473817L
        private const val GET_PLAYBACK_TYPE_HASH = 4011264623L

        private val playBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "play", PLAY_HASH)
        }

        private val seekBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "seek", DOUBLE_VOID_HASH)
        }

        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "stop", NOARGS_VOID_HASH)
        }

        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "is_playing", NOARGS_BOOL_HASH)
        }

        private val setPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_playing", BOOL_VOID_HASH)
        }

        private val setStreamPausedBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_stream_paused", BOOL_VOID_HASH)
        }

        private val getStreamPausedBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_stream_paused", NOARGS_BOOL_HASH)
        }

        private val setStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_stream", SET_STREAM_HASH)
        }

        private val getStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_stream", GET_STREAM_HASH)
        }

        private val setVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_volume_db", DOUBLE_VOID_HASH)
        }

        private val getVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_volume_db", NOARGS_DOUBLE_HASH)
        }

        private val setVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_volume_linear", DOUBLE_VOID_HASH)
        }

        private val getVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_volume_linear", NOARGS_DOUBLE_HASH)
        }

        private val setPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_pitch_scale", DOUBLE_VOID_HASH)
        }

        private val getPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_pitch_scale", NOARGS_DOUBLE_HASH)
        }

        private val setBusBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_bus", STRING_NAME_VOID_HASH)
        }

        private val getBusBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_bus", NOARGS_STRING_NAME_HASH)
        }

        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_autoplay", BOOL_VOID_HASH)
        }

        private val isAutoplayEnabledBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "is_autoplay_enabled", NOARGS_BOOL_HASH)
        }

        private val setMixTargetBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_mix_target", SET_MIX_TARGET_HASH)
        }

        private val getMixTargetBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_mix_target", GET_MIX_TARGET_HASH)
        }

        private val setMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_max_polyphony", LONG_VOID_HASH)
        }

        private val getMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_max_polyphony", NOARGS_LONG_HASH)
        }

        private val getPlaybackPositionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_playback_position", PLAYBACK_POSITION_HASH)
        }

        private val hasStreamPlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "has_stream_playback", HAS_STREAM_PLAYBACK_HASH)
        }

        private val getStreamPlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_stream_playback", GET_STREAM_PLAYBACK_HASH)
        }

        private val setPlaybackTypeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_playback_type", SET_PLAYBACK_TYPE_HASH)
        }

        private val getPlaybackTypeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_playback_type", GET_PLAYBACK_TYPE_HASH)
        }

        @JvmStatic
        fun create(): AudioStreamPlayer =
            AudioStreamPlayer(GodotHandle(ObjectCalls.constructObject("AudioStreamPlayer")))
    }
}

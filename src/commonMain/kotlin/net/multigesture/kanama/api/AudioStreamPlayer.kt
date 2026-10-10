package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node for audio playback.
 *
 * Generated from Godot docs: AudioStreamPlayer
 */
class AudioStreamPlayer(handle: GodotHandle) : Node(handle) {
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

    var mixTarget: AudioStreamPlayer.MixTarget
        @JvmName("mixTargetProperty")
        get() = getMixTarget()
        @JvmName("setMixTargetProperty")
        set(value) = setMixTarget(value)

    var maxPolyphony: Int
        @JvmName("maxPolyphonyProperty")
        get() = getMaxPolyphony()
        @JvmName("setMaxPolyphonyProperty")
        set(value) = setMaxPolyphony(value)

    var bus: String
        @JvmName("busProperty")
        get() = getBus()
        @JvmName("setBusProperty")
        set(value) = setBus(value)

    var playbackType: AudioServer.PlaybackType
        @JvmName("playbackTypeProperty")
        get() = getPlaybackType()
        @JvmName("setPlaybackTypeProperty")
        set(value) = setPlaybackType(value)

    /**
     * The `AudioStream` resource to be played. Setting this property stops all currently playing
     * sounds. If left empty, the `AudioStreamPlayer` does not work.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_stream
     */
    fun setStream(stream: AudioStream?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setStreamBind, segment, listOf(stream?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `AudioStream` resource to be played. Setting this property stops all currently playing
     * sounds. If left empty, the `AudioStreamPlayer` does not work.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_stream
     */
    fun getStream(): AudioStream? {
        return AudioStream.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getStreamBind, segment))
    }

    /**
     * Volume of sound, in decibels. This is an offset of the `stream`'s volume. Note: To convert
     * between decibel and linear energy (like most volume sliders do), use `volume_linear`, or
     * `@GlobalScope.db_to_linear` and `@GlobalScope.linear_to_db`.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_volume_db
     */
    fun setVolumeDb(volumeDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVolumeDbBind, segment, volumeDb)
    }

    /**
     * Volume of sound, in decibels. This is an offset of the `stream`'s volume. Note: To convert
     * between decibel and linear energy (like most volume sliders do), use `volume_linear`, or
     * `@GlobalScope.db_to_linear` and `@GlobalScope.linear_to_db`.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_volume_db
     */
    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVolumeDbBind, segment)
    }

    /**
     * Volume of sound, as a linear value. Note: This member modifies `volume_db` for convenience. The
     * returned value is equivalent to the result of `@GlobalScope.db_to_linear` on `volume_db`.
     * Setting this member is equivalent to setting `volume_db` to the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_volume_linear
     */
    fun setVolumeLinear(volumeLinear: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVolumeLinearBind, segment, volumeLinear)
    }

    /**
     * Volume of sound, as a linear value. Note: This member modifies `volume_db` for convenience. The
     * returned value is equivalent to the result of `@GlobalScope.db_to_linear` on `volume_db`.
     * Setting this member is equivalent to setting `volume_db` to the result of
     * `@GlobalScope.linear_to_db` on a value.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_volume_linear
     */
    fun getVolumeLinear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVolumeLinearBind, segment)
    }

    /**
     * The audio's pitch and tempo, as a multiplier of the `stream`'s sample rate. A value of `2.0`
     * doubles the audio's pitch, while a value of `0.5` halves the pitch.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_pitch_scale
     */
    fun setPitchScale(pitchScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPitchScaleBind, segment, pitchScale)
    }

    /**
     * The audio's pitch and tempo, as a multiplier of the `stream`'s sample rate. A value of `2.0`
     * doubles the audio's pitch, while a value of `0.5` halves the pitch.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_pitch_scale
     */
    fun getPitchScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPitchScaleBind, segment)
    }

    /**
     * Plays a sound from the beginning, or the given `from_position` in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer.play
     */
    fun play(fromPosition: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.playBind, segment, fromPosition)
    }

    /**
     * Restarts all sounds to be played from the given `to_position`, in seconds. Does nothing if no
     * sounds are playing.
     *
     * Generated from Godot docs: AudioStreamPlayer.seek
     */
    fun seek(toPosition: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.seekBind, segment, toPosition)
    }

    /**
     * Stops all sounds from this node.
     *
     * Generated from Godot docs: AudioStreamPlayer.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(Binds.stopBind, segment)
    }

    /**
     * If `true`, this node is playing sounds. Setting this property has the same effect as `play` and
     * `stop`.
     *
     * Generated from Godot docs: AudioStreamPlayer.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPlayingBind, segment)
    }

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
    fun getPlaybackPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPlaybackPositionBind, segment)
    }

    /**
     * The target bus name. All sounds from this node will be playing on this bus. Note: At runtime, if
     * no bus with the given name exists, all sounds will fall back on `"Master"`. See also
     * `AudioServer.get_bus_name`.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_bus
     */
    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setBusBind, segment, bus)
    }

    /**
     * The target bus name. All sounds from this node will be playing on this bus. Note: At runtime, if
     * no bus with the given name exists, all sounds will fall back on `"Master"`. See also
     * `AudioServer.get_bus_name`.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_bus
     */
    fun getBus(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getBusBind, segment)
    }

    /**
     * If `true`, this node calls `play` when entering the tree.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_autoplay
     */
    fun setAutoplay(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoplayBind, segment, enabled)
    }

    /**
     * If `true`, this node calls `play` when entering the tree.
     *
     * Generated from Godot docs: AudioStreamPlayer.is_autoplay_enabled
     */
    fun isAutoplayEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutoplayEnabledBind, segment)
    }

    /**
     * The mix target channels. Has no effect when two speakers or less are detected (see
     * `AudioServer.SpeakerMode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.set_mix_target
     */
    fun setMixTarget(mixTarget: AudioStreamPlayer.MixTarget) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMixTargetBind, segment, mixTarget.value)
    }

    /**
     * The mix target channels. Has no effect when two speakers or less are detected (see
     * `AudioServer.SpeakerMode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.get_mix_target
     */
    fun getMixTarget(): AudioStreamPlayer.MixTarget {
        return AudioStreamPlayer.MixTarget(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMixTargetBind, segment))
    }

    /**
     * If `true`, this node is playing sounds. Setting this property has the same effect as `play` and
     * `stop`.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_playing
     */
    fun setPlaying(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPlayingBind, segment, enable)
    }

    /**
     * If `true`, the sounds are paused. Setting `stream_paused` to `false` resumes all sounds. Note:
     * This property is automatically changed when exiting or entering the tree, or this node is paused
     * (see `Node.process_mode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.set_stream_paused
     */
    fun setStreamPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setStreamPausedBind, segment, paused)
    }

    /**
     * If `true`, the sounds are paused. Setting `stream_paused` to `false` resumes all sounds. Note:
     * This property is automatically changed when exiting or entering the tree, or this node is paused
     * (see `Node.process_mode`).
     *
     * Generated from Godot docs: AudioStreamPlayer.get_stream_paused
     */
    fun getStreamPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getStreamPausedBind, segment)
    }

    /**
     * The maximum number of sounds this node can play at the same time. Calling `play` after this
     * value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_max_polyphony
     */
    fun setMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxPolyphonyBind, segment, maxPolyphony)
    }

    /**
     * The maximum number of sounds this node can play at the same time. Calling `play` after this
     * value is reached will cut off the oldest sounds.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_max_polyphony
     */
    fun getMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxPolyphonyBind, segment)
    }

    /**
     * Returns `true` if any sound is active, even if `stream_paused` is set to `true`. See also
     * `playing` and `get_stream_playback`.
     *
     * Generated from Godot docs: AudioStreamPlayer.has_stream_playback
     */
    fun hasStreamPlayback(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasStreamPlaybackBind, segment)
    }

    /**
     * Returns the latest `AudioStreamPlayback` of this node, usually the most recently created by
     * `play`. If no sounds are playing, this method fails and returns an empty playback.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_stream_playback
     */
    fun getStreamPlayback(): AudioStreamPlayback? {
        return AudioStreamPlayback.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getStreamPlaybackBind, segment))
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer.set_playback_type
     */
    fun setPlaybackType(playbackType: AudioServer.PlaybackType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPlaybackTypeBind, segment, playbackType.value)
    }

    /**
     * The playback type of the stream player. If set other than to the default value, it will force
     * that playback type.
     *
     * Generated from Godot docs: AudioStreamPlayer.get_playback_type
     */
    fun getPlaybackType(): AudioServer.PlaybackType {
        return AudioServer.PlaybackType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaybackTypeBind, segment))
    }

    /**
     * Loads an `AudioStream` from [path], assigns it to this player, and releases Kanama's temporary
     * resource wrapper. Use this when mirroring GDScript's `stream = load(path)` pattern.
     */
    fun setStreamFromPath(path: String, cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE) {
        ResourceLoader.loadAudioStream(path, cacheMode)?.use { stream -> setStream(stream) }
    }

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val finished: String = "finished"
    }

    /**
     * Godot's `AudioStreamPlayer.MixTarget` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AudioStreamPlayer.MixTarget.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamPlayer.MixTarget
     */
    @JvmInline
    value class MixTarget(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The audio will be played only on the first channel. This is the default.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_STEREO
             */
            val STEREO: MixTarget get() = MixTarget(0L)
            /**
             * The audio will be played on all surround channels.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_SURROUND
             */
            val SURROUND: MixTarget get() = MixTarget(1L)
            /**
             * The audio will be played on the second channel, which is usually the center.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_CENTER
             */
            val CENTER: MixTarget get() = MixTarget(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlayer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPlayer? =
            if (handle.address() == 0L) null else AudioStreamPlayer(GodotHandle(handle))

        // Instantiate an AudioStreamPlayer.
        @JvmStatic
        fun create(): AudioStreamPlayer =
            AudioStreamPlayer(GodotHandle(ObjectCalls.constructObject("AudioStreamPlayer")))
    }

    private object Binds {
        private const val SET_STREAM_HASH = 2210767741L
        @JvmField
        val setStreamBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_stream", SET_STREAM_HASH)

        private const val GET_STREAM_HASH = 160907539L
        @JvmField
        val getStreamBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_stream", GET_STREAM_HASH)

        private const val SET_VOLUME_DB_HASH = 373806689L
        @JvmField
        val setVolumeDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_volume_db", SET_VOLUME_DB_HASH)

        private const val GET_VOLUME_DB_HASH = 1740695150L
        @JvmField
        val getVolumeDbBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_volume_db", GET_VOLUME_DB_HASH)

        private const val SET_VOLUME_LINEAR_HASH = 373806689L
        @JvmField
        val setVolumeLinearBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_volume_linear", SET_VOLUME_LINEAR_HASH)

        private const val GET_VOLUME_LINEAR_HASH = 1740695150L
        @JvmField
        val getVolumeLinearBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_volume_linear", GET_VOLUME_LINEAR_HASH)

        private const val SET_PITCH_SCALE_HASH = 373806689L
        @JvmField
        val setPitchScaleBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_pitch_scale", SET_PITCH_SCALE_HASH)

        private const val GET_PITCH_SCALE_HASH = 1740695150L
        @JvmField
        val getPitchScaleBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_pitch_scale", GET_PITCH_SCALE_HASH)

        private const val PLAY_HASH = 1958160172L
        @JvmField
        val playBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "play", PLAY_HASH)

        private const val SEEK_HASH = 373806689L
        @JvmField
        val seekBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "seek", SEEK_HASH)

        private const val STOP_HASH = 3218959716L
        @JvmField
        val stopBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "stop", STOP_HASH)

        private const val IS_PLAYING_HASH = 36873697L
        @JvmField
        val isPlayingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "is_playing", IS_PLAYING_HASH)

        private const val GET_PLAYBACK_POSITION_HASH = 191475506L
        @JvmField
        val getPlaybackPositionBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_playback_position", GET_PLAYBACK_POSITION_HASH)

        private const val SET_BUS_HASH = 3304788590L
        @JvmField
        val setBusBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_bus", SET_BUS_HASH)

        private const val GET_BUS_HASH = 2002593661L
        @JvmField
        val getBusBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_bus", GET_BUS_HASH)

        private const val SET_AUTOPLAY_HASH = 2586408642L
        @JvmField
        val setAutoplayBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_autoplay", SET_AUTOPLAY_HASH)

        private const val IS_AUTOPLAY_ENABLED_HASH = 36873697L
        @JvmField
        val isAutoplayEnabledBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "is_autoplay_enabled", IS_AUTOPLAY_ENABLED_HASH)

        private const val SET_MIX_TARGET_HASH = 2300306138L
        @JvmField
        val setMixTargetBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_mix_target", SET_MIX_TARGET_HASH)

        private const val GET_MIX_TARGET_HASH = 172807476L
        @JvmField
        val getMixTargetBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_mix_target", GET_MIX_TARGET_HASH)

        private const val SET_PLAYING_HASH = 2586408642L
        @JvmField
        val setPlayingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_playing", SET_PLAYING_HASH)

        private const val SET_STREAM_PAUSED_HASH = 2586408642L
        @JvmField
        val setStreamPausedBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_stream_paused", SET_STREAM_PAUSED_HASH)

        private const val GET_STREAM_PAUSED_HASH = 36873697L
        @JvmField
        val getStreamPausedBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_stream_paused", GET_STREAM_PAUSED_HASH)

        private const val SET_MAX_POLYPHONY_HASH = 1286410249L
        @JvmField
        val setMaxPolyphonyBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_max_polyphony", SET_MAX_POLYPHONY_HASH)

        private const val GET_MAX_POLYPHONY_HASH = 3905245786L
        @JvmField
        val getMaxPolyphonyBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_max_polyphony", GET_MAX_POLYPHONY_HASH)

        private const val HAS_STREAM_PLAYBACK_HASH = 2240911060L
        @JvmField
        val hasStreamPlaybackBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "has_stream_playback", HAS_STREAM_PLAYBACK_HASH)

        private const val GET_STREAM_PLAYBACK_HASH = 210135309L
        @JvmField
        val getStreamPlaybackBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_stream_playback", GET_STREAM_PLAYBACK_HASH)

        private const val SET_PLAYBACK_TYPE_HASH = 725473817L
        @JvmField
        val setPlaybackTypeBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "set_playback_type", SET_PLAYBACK_TYPE_HASH)

        private const val GET_PLAYBACK_TYPE_HASH = 4011264623L
        @JvmField
        val getPlaybackTypeBind =
            ObjectCalls.getMethodBind("AudioStreamPlayer", "get_playback_type", GET_PLAYBACK_TYPE_HASH)
    }
}

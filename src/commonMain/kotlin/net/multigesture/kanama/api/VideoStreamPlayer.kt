package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A control used for video playback.
 *
 * Generated from Godot docs: VideoStreamPlayer
 */
class VideoStreamPlayer(handle: GodotHandle) : Control(handle) {
    var audioTrack: Int
        @JvmName("audioTrackProperty")
        get() = getAudioTrack()
        @JvmName("setAudioTrackProperty")
        set(value) = setAudioTrack(value)

    var stream: VideoStream?
        @JvmName("streamProperty")
        get() = getStream()
        @JvmName("setStreamProperty")
        set(value) = setStream(value)

    var volumeDb: Double
        @JvmName("volumeDbProperty")
        get() = getVolumeDb()
        @JvmName("setVolumeDbProperty")
        set(value) = setVolumeDb(value)

    var volume: Double
        @JvmName("volumeProperty")
        get() = getVolume()
        @JvmName("setVolumeProperty")
        set(value) = setVolume(value)

    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)

    var autoplay: Boolean
        @JvmName("autoplayProperty")
        get() = hasAutoplay()
        @JvmName("setAutoplayProperty")
        set(value) = setAutoplay(value)

    var paused: Boolean
        @JvmName("pausedProperty")
        get() = isPaused()
        @JvmName("setPausedProperty")
        set(value) = setPaused(value)

    var expand: Boolean
        @JvmName("expandProperty")
        get() = hasExpand()
        @JvmName("setExpandProperty")
        set(value) = setExpand(value)

    var loop: Boolean
        @JvmName("loopProperty")
        get() = hasLoop()
        @JvmName("setLoopProperty")
        set(value) = setLoop(value)

    var bufferingMsec: Int
        @JvmName("bufferingMsecProperty")
        get() = getBufferingMsec()
        @JvmName("setBufferingMsecProperty")
        set(value) = setBufferingMsec(value)

    var streamPosition: Double
        @JvmName("streamPositionProperty")
        get() = getStreamPosition()
        @JvmName("setStreamPositionProperty")
        set(value) = setStreamPosition(value)

    var bus: String
        @JvmName("busProperty")
        get() = getBus()
        @JvmName("setBusProperty")
        set(value) = setBus(value)

    /**
     * The assigned video stream. See description for supported formats.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_stream
     */
    fun setStream(stream: VideoStream?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setStreamBind, segment, listOf(stream?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The assigned video stream. See description for supported formats.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_stream
     */
    fun getStream(): VideoStream? {
        return VideoStream.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getStreamBind, segment))
    }

    /**
     * Starts the video playback from the beginning. If the video is paused, this will not unpause the
     * video.
     *
     * Generated from Godot docs: VideoStreamPlayer.play
     */
    fun play() {
        ObjectCalls.ptrcallNoArgs(Binds.playBind, segment)
    }

    /**
     * Stops the video playback and sets the stream position to 0. Note: Although the stream position
     * will be set to 0, the first frame of the video stream won't become the current frame.
     *
     * Generated from Godot docs: VideoStreamPlayer.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(Binds.stopBind, segment)
    }

    /**
     * Returns `true` if the video is playing. Note: The video is still considered playing if paused
     * during playback.
     *
     * Generated from Godot docs: VideoStreamPlayer.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPlayingBind, segment)
    }

    /**
     * If `true`, the video is paused.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_paused
     */
    fun setPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPausedBind, segment, paused)
    }

    /**
     * If `true`, the video is paused.
     *
     * Generated from Godot docs: VideoStreamPlayer.is_paused
     */
    fun isPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPausedBind, segment)
    }

    /**
     * If `true`, the video restarts when it reaches its end.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_loop
     */
    fun setLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setLoopBind, segment, loop)
    }

    /**
     * If `true`, the video restarts when it reaches its end.
     *
     * Generated from Godot docs: VideoStreamPlayer.has_loop
     */
    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasLoopBind, segment)
    }

    /**
     * Audio volume as a linear value.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_volume
     */
    fun setVolume(volume: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVolumeBind, segment, volume)
    }

    /**
     * Audio volume as a linear value.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_volume
     */
    fun getVolume(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVolumeBind, segment)
    }

    /**
     * Audio volume in dB.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_volume_db
     */
    fun setVolumeDb(db: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVolumeDbBind, segment, db)
    }

    /**
     * Audio volume in dB.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_volume_db
     */
    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVolumeDbBind, segment)
    }

    /**
     * The stream's current speed scale. `1.0` is the normal speed, while `2.0` is double speed and
     * `0.5` is half speed. A speed scale of `0.0` pauses the video, similar to setting `paused` to
     * `true`.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_speed_scale
     */
    fun setSpeedScale(speedScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpeedScaleBind, segment, speedScale)
    }

    /**
     * The stream's current speed scale. `1.0` is the normal speed, while `2.0` is double speed and
     * `0.5` is half speed. A speed scale of `0.0` pauses the video, similar to setting `paused` to
     * `true`.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpeedScaleBind, segment)
    }

    /**
     * The embedded audio track to play.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_audio_track
     */
    fun setAudioTrack(track: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setAudioTrackBind, segment, track)
    }

    /**
     * The embedded audio track to play.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_audio_track
     */
    fun getAudioTrack(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getAudioTrackBind, segment)
    }

    /**
     * Returns the video stream's name, or `"<No Stream>"` if no video stream is assigned.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_stream_name
     */
    fun getStreamName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getStreamNameBind, segment)
    }

    /**
     * The length of the current stream, in seconds.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_stream_length
     */
    fun getStreamLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStreamLengthBind, segment)
    }

    /**
     * The current position of the stream, in seconds.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_stream_position
     */
    fun setStreamPosition(position: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStreamPositionBind, segment, position)
    }

    /**
     * The current position of the stream, in seconds.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_stream_position
     */
    fun getStreamPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStreamPositionBind, segment)
    }

    /**
     * If `true`, playback starts when the scene loads.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_autoplay
     */
    fun setAutoplay(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoplayBind, segment, enabled)
    }

    /**
     * If `true`, playback starts when the scene loads.
     *
     * Generated from Godot docs: VideoStreamPlayer.has_autoplay
     */
    fun hasAutoplay(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasAutoplayBind, segment)
    }

    /**
     * If `true`, the video scales to the control size. Otherwise, the control minimum size will be
     * automatically adjusted to match the video stream's dimensions.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_expand
     */
    fun setExpand(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExpandBind, segment, enable)
    }

    /**
     * If `true`, the video scales to the control size. Otherwise, the control minimum size will be
     * automatically adjusted to match the video stream's dimensions.
     *
     * Generated from Godot docs: VideoStreamPlayer.has_expand
     */
    fun hasExpand(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasExpandBind, segment)
    }

    /**
     * Amount of time in milliseconds to store in buffer while playing.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_buffering_msec
     */
    fun setBufferingMsec(msec: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setBufferingMsecBind, segment, msec)
    }

    /**
     * Amount of time in milliseconds to store in buffer while playing.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_buffering_msec
     */
    fun getBufferingMsec(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBufferingMsecBind, segment)
    }

    /**
     * Audio bus to use for sound playback.
     *
     * Generated from Godot docs: VideoStreamPlayer.set_bus
     */
    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setBusBind, segment, bus)
    }

    /**
     * Audio bus to use for sound playback.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_bus
     */
    fun getBus(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getBusBind, segment)
    }

    /**
     * Returns the current frame as a `Texture2D`.
     *
     * Generated from Godot docs: VideoStreamPlayer.get_video_texture
     */
    fun getVideoTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getVideoTextureBind, segment))
    }

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VideoStreamPlayer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VideoStreamPlayer? =
            if (handle.address() == 0L) null else VideoStreamPlayer(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_STREAM_HASH = 2317102564L
        @JvmField
        val setStreamBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_stream", SET_STREAM_HASH)

        private const val GET_STREAM_HASH = 438621487L
        @JvmField
        val getStreamBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream", GET_STREAM_HASH)

        private const val PLAY_HASH = 3218959716L
        @JvmField
        val playBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "play", PLAY_HASH)

        private const val STOP_HASH = 3218959716L
        @JvmField
        val stopBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "stop", STOP_HASH)

        private const val IS_PLAYING_HASH = 36873697L
        @JvmField
        val isPlayingBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "is_playing", IS_PLAYING_HASH)

        private const val SET_PAUSED_HASH = 2586408642L
        @JvmField
        val setPausedBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_paused", SET_PAUSED_HASH)

        private const val IS_PAUSED_HASH = 36873697L
        @JvmField
        val isPausedBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "is_paused", IS_PAUSED_HASH)

        private const val SET_LOOP_HASH = 2586408642L
        @JvmField
        val setLoopBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_loop", SET_LOOP_HASH)

        private const val HAS_LOOP_HASH = 36873697L
        @JvmField
        val hasLoopBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "has_loop", HAS_LOOP_HASH)

        private const val SET_VOLUME_HASH = 373806689L
        @JvmField
        val setVolumeBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_volume", SET_VOLUME_HASH)

        private const val GET_VOLUME_HASH = 1740695150L
        @JvmField
        val getVolumeBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_volume", GET_VOLUME_HASH)

        private const val SET_VOLUME_DB_HASH = 373806689L
        @JvmField
        val setVolumeDbBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_volume_db", SET_VOLUME_DB_HASH)

        private const val GET_VOLUME_DB_HASH = 1740695150L
        @JvmField
        val getVolumeDbBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_volume_db", GET_VOLUME_DB_HASH)

        private const val SET_SPEED_SCALE_HASH = 373806689L
        @JvmField
        val setSpeedScaleBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_speed_scale", SET_SPEED_SCALE_HASH)

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        @JvmField
        val getSpeedScaleBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_speed_scale", GET_SPEED_SCALE_HASH)

        private const val SET_AUDIO_TRACK_HASH = 1286410249L
        @JvmField
        val setAudioTrackBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_audio_track", SET_AUDIO_TRACK_HASH)

        private const val GET_AUDIO_TRACK_HASH = 3905245786L
        @JvmField
        val getAudioTrackBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_audio_track", GET_AUDIO_TRACK_HASH)

        private const val GET_STREAM_NAME_HASH = 201670096L
        @JvmField
        val getStreamNameBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream_name", GET_STREAM_NAME_HASH)

        private const val GET_STREAM_LENGTH_HASH = 1740695150L
        @JvmField
        val getStreamLengthBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream_length", GET_STREAM_LENGTH_HASH)

        private const val SET_STREAM_POSITION_HASH = 373806689L
        @JvmField
        val setStreamPositionBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_stream_position", SET_STREAM_POSITION_HASH)

        private const val GET_STREAM_POSITION_HASH = 1740695150L
        @JvmField
        val getStreamPositionBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream_position", GET_STREAM_POSITION_HASH)

        private const val SET_AUTOPLAY_HASH = 2586408642L
        @JvmField
        val setAutoplayBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_autoplay", SET_AUTOPLAY_HASH)

        private const val HAS_AUTOPLAY_HASH = 36873697L
        @JvmField
        val hasAutoplayBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "has_autoplay", HAS_AUTOPLAY_HASH)

        private const val SET_EXPAND_HASH = 2586408642L
        @JvmField
        val setExpandBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_expand", SET_EXPAND_HASH)

        private const val HAS_EXPAND_HASH = 36873697L
        @JvmField
        val hasExpandBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "has_expand", HAS_EXPAND_HASH)

        private const val SET_BUFFERING_MSEC_HASH = 1286410249L
        @JvmField
        val setBufferingMsecBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_buffering_msec", SET_BUFFERING_MSEC_HASH)

        private const val GET_BUFFERING_MSEC_HASH = 3905245786L
        @JvmField
        val getBufferingMsecBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_buffering_msec", GET_BUFFERING_MSEC_HASH)

        private const val SET_BUS_HASH = 3304788590L
        @JvmField
        val setBusBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_bus", SET_BUS_HASH)

        private const val GET_BUS_HASH = 2002593661L
        @JvmField
        val getBusBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_bus", GET_BUS_HASH)

        private const val GET_VIDEO_TEXTURE_HASH = 3635182373L
        @JvmField
        val getVideoTextureBind =
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_video_texture", GET_VIDEO_TEXTURE_HASH)
    }
}

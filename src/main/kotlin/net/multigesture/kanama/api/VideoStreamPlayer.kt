package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A control used for video playback.
 *
 * Generated from Godot docs: VideoStreamPlayer
 */
class VideoStreamPlayer(handle: MemorySegment) : Control(handle) {
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

    fun setStream(stream: VideoStream?) {
        ObjectCalls.ptrcallWithObjectArgs(setStreamBind, handle, listOf(stream?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getStream(): VideoStream? {
        return VideoStream.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamBind, handle))
    }

    /**
     * Starts the video playback from the beginning. If the video is paused, this will not unpause the
     * video.
     *
     * Generated from Godot docs: VideoStreamPlayer.play
     */
    fun play() {
        ObjectCalls.ptrcallNoArgs(playBind, handle)
    }

    /**
     * Stops the video playback and sets the stream position to 0. Note: Although the stream position
     * will be set to 0, the first frame of the video stream won't become the current frame.
     *
     * Generated from Godot docs: VideoStreamPlayer.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    fun setPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPausedBind, handle, paused)
    }

    fun isPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPausedBind, handle)
    }

    fun setLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, handle, loop)
    }

    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasLoopBind, handle)
    }

    fun setVolume(volume: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeBind, handle, volume)
    }

    fun getVolume(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeBind, handle)
    }

    fun setVolumeDb(db: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeDbBind, handle, db)
    }

    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeDbBind, handle)
    }

    fun setSpeedScale(speedScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, speedScale)
    }

    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    fun setAudioTrack(track: Int) {
        ObjectCalls.ptrcallWithIntArg(setAudioTrackBind, handle, track)
    }

    fun getAudioTrack(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAudioTrackBind, handle)
    }

    fun getStreamName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getStreamNameBind, handle)
    }

    fun getStreamLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStreamLengthBind, handle)
    }

    fun setStreamPosition(position: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStreamPositionBind, handle, position)
    }

    fun getStreamPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStreamPositionBind, handle)
    }

    fun setAutoplay(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoplayBind, handle, enabled)
    }

    fun hasAutoplay(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutoplayBind, handle)
    }

    fun setExpand(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExpandBind, handle, enable)
    }

    fun hasExpand(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasExpandBind, handle)
    }

    fun setBufferingMsec(msec: Int) {
        ObjectCalls.ptrcallWithIntArg(setBufferingMsecBind, handle, msec)
    }

    fun getBufferingMsec(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferingMsecBind, handle)
    }

    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(setBusBind, handle, bus)
    }

    fun getBus(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getBusBind, handle)
    }

    fun getVideoTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVideoTextureBind, handle))
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VideoStreamPlayer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VideoStreamPlayer? =
            if (handle.address() == 0L) null else VideoStreamPlayer(handle)

        private const val SET_STREAM_HASH = 2317102564L
        private val setStreamBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_stream", SET_STREAM_HASH)
        }

        private const val GET_STREAM_HASH = 438621487L
        private val getStreamBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream", GET_STREAM_HASH)
        }

        private const val PLAY_HASH = 3218959716L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "play", PLAY_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "stop", STOP_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "is_playing", IS_PLAYING_HASH)
        }

        private const val SET_PAUSED_HASH = 2586408642L
        private val setPausedBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_paused", SET_PAUSED_HASH)
        }

        private const val IS_PAUSED_HASH = 36873697L
        private val isPausedBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "is_paused", IS_PAUSED_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_loop", SET_LOOP_HASH)
        }

        private const val HAS_LOOP_HASH = 36873697L
        private val hasLoopBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "has_loop", HAS_LOOP_HASH)
        }

        private const val SET_VOLUME_HASH = 373806689L
        private val setVolumeBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_volume", SET_VOLUME_HASH)
        }

        private const val GET_VOLUME_HASH = 1740695150L
        private val getVolumeBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_volume", GET_VOLUME_HASH)
        }

        private const val SET_VOLUME_DB_HASH = 373806689L
        private val setVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_volume_db", SET_VOLUME_DB_HASH)
        }

        private const val GET_VOLUME_DB_HASH = 1740695150L
        private val getVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_volume_db", GET_VOLUME_DB_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val SET_AUDIO_TRACK_HASH = 1286410249L
        private val setAudioTrackBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_audio_track", SET_AUDIO_TRACK_HASH)
        }

        private const val GET_AUDIO_TRACK_HASH = 3905245786L
        private val getAudioTrackBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_audio_track", GET_AUDIO_TRACK_HASH)
        }

        private const val GET_STREAM_NAME_HASH = 201670096L
        private val getStreamNameBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream_name", GET_STREAM_NAME_HASH)
        }

        private const val GET_STREAM_LENGTH_HASH = 1740695150L
        private val getStreamLengthBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream_length", GET_STREAM_LENGTH_HASH)
        }

        private const val SET_STREAM_POSITION_HASH = 373806689L
        private val setStreamPositionBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_stream_position", SET_STREAM_POSITION_HASH)
        }

        private const val GET_STREAM_POSITION_HASH = 1740695150L
        private val getStreamPositionBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_stream_position", GET_STREAM_POSITION_HASH)
        }

        private const val SET_AUTOPLAY_HASH = 2586408642L
        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_autoplay", SET_AUTOPLAY_HASH)
        }

        private const val HAS_AUTOPLAY_HASH = 36873697L
        private val hasAutoplayBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "has_autoplay", HAS_AUTOPLAY_HASH)
        }

        private const val SET_EXPAND_HASH = 2586408642L
        private val setExpandBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_expand", SET_EXPAND_HASH)
        }

        private const val HAS_EXPAND_HASH = 36873697L
        private val hasExpandBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "has_expand", HAS_EXPAND_HASH)
        }

        private const val SET_BUFFERING_MSEC_HASH = 1286410249L
        private val setBufferingMsecBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_buffering_msec", SET_BUFFERING_MSEC_HASH)
        }

        private const val GET_BUFFERING_MSEC_HASH = 3905245786L
        private val getBufferingMsecBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_buffering_msec", GET_BUFFERING_MSEC_HASH)
        }

        private const val SET_BUS_HASH = 3304788590L
        private val setBusBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "set_bus", SET_BUS_HASH)
        }

        private const val GET_BUS_HASH = 2002593661L
        private val getBusBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_bus", GET_BUS_HASH)
        }

        private const val GET_VIDEO_TEXTURE_HASH = 3635182373L
        private val getVideoTextureBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayer", "get_video_texture", GET_VIDEO_TEXTURE_HASH)
        }
    }
}

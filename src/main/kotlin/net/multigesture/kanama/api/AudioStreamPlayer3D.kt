package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plays positional sound in 3D space.
 *
 * Generated from Godot docs: AudioStreamPlayer3D
 */
class AudioStreamPlayer3D(handle: MemorySegment) : Node3D(handle) {
    var stream: AudioStream?
        @JvmName("streamProperty")
        get() = getStream()
        @JvmName("setStreamProperty")
        set(value) = setStream(value)

    var attenuationModel: Long
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

    var playbackType: Long
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

    var dopplerTracking: Long
        @JvmName("dopplerTrackingProperty")
        get() = getDopplerTracking()
        @JvmName("setDopplerTrackingProperty")
        set(value) = setDopplerTracking(value)

    fun setStream(stream: AudioStream?) {
        ObjectCalls.ptrcallWithObjectArgs(setStreamBind, handle, listOf(stream?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getStream(): AudioStream? {
        return AudioStream.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamBind, handle))
    }

    fun setVolumeDb(volumeDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeDbBind, handle, volumeDb)
    }

    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeDbBind, handle)
    }

    fun setVolumeLinear(volumeLinear: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeLinearBind, handle, volumeLinear)
    }

    fun getVolumeLinear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeLinearBind, handle)
    }

    fun setUnitSize(unitSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUnitSizeBind, handle, unitSize)
    }

    fun getUnitSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUnitSizeBind, handle)
    }

    fun setMaxDb(maxDb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDbBind, handle, maxDb)
    }

    fun getMaxDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDbBind, handle)
    }

    fun setPitchScale(pitchScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPitchScaleBind, handle, pitchScale)
    }

    fun getPitchScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPitchScaleBind, handle)
    }

    /**
     * Queues the audio to play on the next physics frame, from the given position `from_position`, in
     * seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.play
     */
    fun play(fromPosition: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(playBind, handle, fromPosition)
    }

    /**
     * Sets the position from which audio will be played, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.seek
     */
    fun seek(toPosition: Double) {
        ObjectCalls.ptrcallWithDoubleArg(seekBind, handle, toPosition)
    }

    /**
     * Stops the audio.
     *
     * Generated from Godot docs: AudioStreamPlayer3D.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    fun getPlaybackPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackPositionBind, handle)
    }

    fun setBus(bus: String) {
        ObjectCalls.ptrcallWithStringNameArg(setBusBind, handle, bus)
    }

    fun getBus(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getBusBind, handle)
    }

    fun setAutoplay(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoplayBind, handle, enable)
    }

    fun isAutoplayEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoplayEnabledBind, handle)
    }

    fun setPlaying(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPlayingBind, handle, enable)
    }

    fun setMaxDistance(meters: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDistanceBind, handle, meters)
    }

    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDistanceBind, handle)
    }

    fun setAreaMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAreaMaskBind, handle, mask)
    }

    fun getAreaMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAreaMaskBind, handle)
    }

    fun setEmissionAngle(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionAngleBind, handle, degrees)
    }

    fun getEmissionAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionAngleBind, handle)
    }

    fun setEmissionAngleEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmissionAngleEnabledBind, handle, enabled)
    }

    fun isEmissionAngleEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmissionAngleEnabledBind, handle)
    }

    fun setEmissionAngleFilterAttenuationDb(db: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionAngleFilterAttenuationDbBind, handle, db)
    }

    fun getEmissionAngleFilterAttenuationDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionAngleFilterAttenuationDbBind, handle)
    }

    fun setAttenuationFilterCutoffHz(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAttenuationFilterCutoffHzBind, handle, degrees)
    }

    fun getAttenuationFilterCutoffHz(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAttenuationFilterCutoffHzBind, handle)
    }

    fun setAttenuationFilterDb(db: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAttenuationFilterDbBind, handle, db)
    }

    fun getAttenuationFilterDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAttenuationFilterDbBind, handle)
    }

    fun setAttenuationModel(model: Long) {
        ObjectCalls.ptrcallWithLongArg(setAttenuationModelBind, handle, model)
    }

    fun getAttenuationModel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAttenuationModelBind, handle)
    }

    fun setDopplerTracking(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDopplerTrackingBind, handle, mode)
    }

    fun getDopplerTracking(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDopplerTrackingBind, handle)
    }

    fun setStreamPaused(pause: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setStreamPausedBind, handle, pause)
    }

    fun getStreamPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getStreamPausedBind, handle)
    }

    fun setMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxPolyphonyBind, handle, maxPolyphony)
    }

    fun getMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxPolyphonyBind, handle)
    }

    fun setPanningStrength(panningStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPanningStrengthBind, handle, panningStrength)
    }

    fun getPanningStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPanningStrengthBind, handle)
    }

    fun hasStreamPlayback(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasStreamPlaybackBind, handle)
    }

    fun getStreamPlayback(): AudioStreamPlayback? {
        return AudioStreamPlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamPlaybackBind, handle))
    }

    fun setPlaybackType(playbackType: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlaybackTypeBind, handle, playbackType)
    }

    fun getPlaybackType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlaybackTypeBind, handle)
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        const val ATTENUATION_INVERSE_DISTANCE: Long = 0L
        const val ATTENUATION_INVERSE_SQUARE_DISTANCE: Long = 1L
        const val ATTENUATION_LOGARITHMIC: Long = 2L
        const val ATTENUATION_DISABLED: Long = 3L
        const val DOPPLER_TRACKING_DISABLED: Long = 0L
        const val DOPPLER_TRACKING_IDLE_STEP: Long = 1L
        const val DOPPLER_TRACKING_PHYSICS_STEP: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlayer3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlayer3D? =
            if (handle.address() == 0L) null else AudioStreamPlayer3D(handle)

        private const val SET_STREAM_HASH = 2210767741L
        private val setStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_stream", SET_STREAM_HASH)
        }

        private const val GET_STREAM_HASH = 160907539L
        private val getStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_stream", GET_STREAM_HASH)
        }

        private const val SET_VOLUME_DB_HASH = 373806689L
        private val setVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_volume_db", SET_VOLUME_DB_HASH)
        }

        private const val GET_VOLUME_DB_HASH = 1740695150L
        private val getVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_volume_db", GET_VOLUME_DB_HASH)
        }

        private const val SET_VOLUME_LINEAR_HASH = 373806689L
        private val setVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_volume_linear", SET_VOLUME_LINEAR_HASH)
        }

        private const val GET_VOLUME_LINEAR_HASH = 1740695150L
        private val getVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_volume_linear", GET_VOLUME_LINEAR_HASH)
        }

        private const val SET_UNIT_SIZE_HASH = 373806689L
        private val setUnitSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_unit_size", SET_UNIT_SIZE_HASH)
        }

        private const val GET_UNIT_SIZE_HASH = 1740695150L
        private val getUnitSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_unit_size", GET_UNIT_SIZE_HASH)
        }

        private const val SET_MAX_DB_HASH = 373806689L
        private val setMaxDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_max_db", SET_MAX_DB_HASH)
        }

        private const val GET_MAX_DB_HASH = 1740695150L
        private val getMaxDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_max_db", GET_MAX_DB_HASH)
        }

        private const val SET_PITCH_SCALE_HASH = 373806689L
        private val setPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_pitch_scale", SET_PITCH_SCALE_HASH)
        }

        private const val GET_PITCH_SCALE_HASH = 1740695150L
        private val getPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_pitch_scale", GET_PITCH_SCALE_HASH)
        }

        private const val PLAY_HASH = 1958160172L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "play", PLAY_HASH)
        }

        private const val SEEK_HASH = 373806689L
        private val seekBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "seek", SEEK_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "stop", STOP_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "is_playing", IS_PLAYING_HASH)
        }

        private const val GET_PLAYBACK_POSITION_HASH = 191475506L
        private val getPlaybackPositionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_playback_position", GET_PLAYBACK_POSITION_HASH)
        }

        private const val SET_BUS_HASH = 3304788590L
        private val setBusBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_bus", SET_BUS_HASH)
        }

        private const val GET_BUS_HASH = 2002593661L
        private val getBusBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_bus", GET_BUS_HASH)
        }

        private const val SET_AUTOPLAY_HASH = 2586408642L
        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_autoplay", SET_AUTOPLAY_HASH)
        }

        private const val IS_AUTOPLAY_ENABLED_HASH = 36873697L
        private val isAutoplayEnabledBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "is_autoplay_enabled", IS_AUTOPLAY_ENABLED_HASH)
        }

        private const val SET_PLAYING_HASH = 2586408642L
        private val setPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_playing", SET_PLAYING_HASH)
        }

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        private val setMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_max_distance", SET_MAX_DISTANCE_HASH)
        }

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        private val getMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_max_distance", GET_MAX_DISTANCE_HASH)
        }

        private const val SET_AREA_MASK_HASH = 1286410249L
        private val setAreaMaskBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_area_mask", SET_AREA_MASK_HASH)
        }

        private const val GET_AREA_MASK_HASH = 3905245786L
        private val getAreaMaskBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_area_mask", GET_AREA_MASK_HASH)
        }

        private const val SET_EMISSION_ANGLE_HASH = 373806689L
        private val setEmissionAngleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_emission_angle", SET_EMISSION_ANGLE_HASH)
        }

        private const val GET_EMISSION_ANGLE_HASH = 1740695150L
        private val getEmissionAngleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_emission_angle", GET_EMISSION_ANGLE_HASH)
        }

        private const val SET_EMISSION_ANGLE_ENABLED_HASH = 2586408642L
        private val setEmissionAngleEnabledBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_emission_angle_enabled", SET_EMISSION_ANGLE_ENABLED_HASH)
        }

        private const val IS_EMISSION_ANGLE_ENABLED_HASH = 36873697L
        private val isEmissionAngleEnabledBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "is_emission_angle_enabled", IS_EMISSION_ANGLE_ENABLED_HASH)
        }

        private const val SET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH = 373806689L
        private val setEmissionAngleFilterAttenuationDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_emission_angle_filter_attenuation_db", SET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH)
        }

        private const val GET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH = 1740695150L
        private val getEmissionAngleFilterAttenuationDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_emission_angle_filter_attenuation_db", GET_EMISSION_ANGLE_FILTER_ATTENUATION_DB_HASH)
        }

        private const val SET_ATTENUATION_FILTER_CUTOFF_HZ_HASH = 373806689L
        private val setAttenuationFilterCutoffHzBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_attenuation_filter_cutoff_hz", SET_ATTENUATION_FILTER_CUTOFF_HZ_HASH)
        }

        private const val GET_ATTENUATION_FILTER_CUTOFF_HZ_HASH = 1740695150L
        private val getAttenuationFilterCutoffHzBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_attenuation_filter_cutoff_hz", GET_ATTENUATION_FILTER_CUTOFF_HZ_HASH)
        }

        private const val SET_ATTENUATION_FILTER_DB_HASH = 373806689L
        private val setAttenuationFilterDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_attenuation_filter_db", SET_ATTENUATION_FILTER_DB_HASH)
        }

        private const val GET_ATTENUATION_FILTER_DB_HASH = 1740695150L
        private val getAttenuationFilterDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_attenuation_filter_db", GET_ATTENUATION_FILTER_DB_HASH)
        }

        private const val SET_ATTENUATION_MODEL_HASH = 2988086229L
        private val setAttenuationModelBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_attenuation_model", SET_ATTENUATION_MODEL_HASH)
        }

        private const val GET_ATTENUATION_MODEL_HASH = 3035106060L
        private val getAttenuationModelBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_attenuation_model", GET_ATTENUATION_MODEL_HASH)
        }

        private const val SET_DOPPLER_TRACKING_HASH = 3968161450L
        private val setDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_doppler_tracking", SET_DOPPLER_TRACKING_HASH)
        }

        private const val GET_DOPPLER_TRACKING_HASH = 1702418664L
        private val getDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_doppler_tracking", GET_DOPPLER_TRACKING_HASH)
        }

        private const val SET_STREAM_PAUSED_HASH = 2586408642L
        private val setStreamPausedBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_stream_paused", SET_STREAM_PAUSED_HASH)
        }

        private const val GET_STREAM_PAUSED_HASH = 36873697L
        private val getStreamPausedBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_stream_paused", GET_STREAM_PAUSED_HASH)
        }

        private const val SET_MAX_POLYPHONY_HASH = 1286410249L
        private val setMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_max_polyphony", SET_MAX_POLYPHONY_HASH)
        }

        private const val GET_MAX_POLYPHONY_HASH = 3905245786L
        private val getMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_max_polyphony", GET_MAX_POLYPHONY_HASH)
        }

        private const val SET_PANNING_STRENGTH_HASH = 373806689L
        private val setPanningStrengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_panning_strength", SET_PANNING_STRENGTH_HASH)
        }

        private const val GET_PANNING_STRENGTH_HASH = 1740695150L
        private val getPanningStrengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_panning_strength", GET_PANNING_STRENGTH_HASH)
        }

        private const val HAS_STREAM_PLAYBACK_HASH = 2240911060L
        private val hasStreamPlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "has_stream_playback", HAS_STREAM_PLAYBACK_HASH)
        }

        private const val GET_STREAM_PLAYBACK_HASH = 210135309L
        private val getStreamPlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_stream_playback", GET_STREAM_PLAYBACK_HASH)
        }

        private const val SET_PLAYBACK_TYPE_HASH = 725473817L
        private val setPlaybackTypeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "set_playback_type", SET_PLAYBACK_TYPE_HASH)
        }

        private const val GET_PLAYBACK_TYPE_HASH = 4011264623L
        private val getPlaybackTypeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayer3D", "get_playback_type", GET_PLAYBACK_TYPE_HASH)
        }
    }
}

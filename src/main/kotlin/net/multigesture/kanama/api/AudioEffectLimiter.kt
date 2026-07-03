package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a soft-clip limiter audio effect to an audio bus.
 *
 * Generated from Godot docs: AudioEffectLimiter
 */
class AudioEffectLimiter(handle: MemorySegment) : AudioEffect(handle) {
    var ceilingDb: Double
        @JvmName("ceilingDbProperty")
        get() = getCeilingDb()
        @JvmName("setCeilingDbProperty")
        set(value) = setCeilingDb(value)

    var thresholdDb: Double
        @JvmName("thresholdDbProperty")
        get() = getThresholdDb()
        @JvmName("setThresholdDbProperty")
        set(value) = setThresholdDb(value)

    var softClipDb: Double
        @JvmName("softClipDbProperty")
        get() = getSoftClipDb()
        @JvmName("setSoftClipDbProperty")
        set(value) = setSoftClipDb(value)

    var softClipRatio: Double
        @JvmName("softClipRatioProperty")
        get() = getSoftClipRatio()
        @JvmName("setSoftClipRatioProperty")
        set(value) = setSoftClipRatio(value)

    /**
     * The waveform's maximum allowed value, in dB. Value can range from -20 to -0.1.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_ceiling_db
     */
    fun setCeilingDb(ceiling: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCeilingDbBind, handle, ceiling)
    }

    /**
     * The waveform's maximum allowed value, in dB. Value can range from -20 to -0.1.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_ceiling_db
     */
    fun getCeilingDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCeilingDbBind, handle)
    }

    /**
     * The volume threshold level from which the limiter begins to be active, in dB. Value can range
     * from -30 to 0.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_threshold_db
     */
    fun setThresholdDb(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setThresholdDbBind, handle, threshold)
    }

    /**
     * The volume threshold level from which the limiter begins to be active, in dB. Value can range
     * from -30 to 0.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_threshold_db
     */
    fun getThresholdDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getThresholdDbBind, handle)
    }

    /**
     * Modifies the volume of the limited waves, in dB. Value can range from 0 to 6.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_soft_clip_db
     */
    fun setSoftClipDb(softClip: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSoftClipDbBind, handle, softClip)
    }

    /**
     * Modifies the volume of the limited waves, in dB. Value can range from 0 to 6.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_soft_clip_db
     */
    fun getSoftClipDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSoftClipDbBind, handle)
    }

    /**
     * This property has no effect on the audio. Use `AudioEffectHardLimiter` instead, as this Limiter
     * effect is deprecated.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_soft_clip_ratio
     */
    fun setSoftClipRatio(softClip: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSoftClipRatioBind, handle, softClip)
    }

    /**
     * This property has no effect on the audio. Use `AudioEffectHardLimiter` instead, as this Limiter
     * effect is deprecated.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_soft_clip_ratio
     */
    fun getSoftClipRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSoftClipRatioBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectLimiter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectLimiter? =
            if (handle.address() == 0L) null else AudioEffectLimiter(handle)

        private const val SET_CEILING_DB_HASH = 373806689L
        private val setCeilingDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_ceiling_db", SET_CEILING_DB_HASH)
        }

        private const val GET_CEILING_DB_HASH = 1740695150L
        private val getCeilingDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_ceiling_db", GET_CEILING_DB_HASH)
        }

        private const val SET_THRESHOLD_DB_HASH = 373806689L
        private val setThresholdDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_threshold_db", SET_THRESHOLD_DB_HASH)
        }

        private const val GET_THRESHOLD_DB_HASH = 1740695150L
        private val getThresholdDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_threshold_db", GET_THRESHOLD_DB_HASH)
        }

        private const val SET_SOFT_CLIP_DB_HASH = 373806689L
        private val setSoftClipDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_soft_clip_db", SET_SOFT_CLIP_DB_HASH)
        }

        private const val GET_SOFT_CLIP_DB_HASH = 1740695150L
        private val getSoftClipDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_soft_clip_db", GET_SOFT_CLIP_DB_HASH)
        }

        private const val SET_SOFT_CLIP_RATIO_HASH = 373806689L
        private val setSoftClipRatioBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_soft_clip_ratio", SET_SOFT_CLIP_RATIO_HASH)
        }

        private const val GET_SOFT_CLIP_RATIO_HASH = 1740695150L
        private val getSoftClipRatioBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_soft_clip_ratio", GET_SOFT_CLIP_RATIO_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a soft-clip limiter audio effect to an audio bus.
 *
 * Generated from Godot docs: AudioEffectLimiter
 */
class AudioEffectLimiter(handle: GodotHandle) : AudioEffect(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCeilingDbBind, segment, ceiling)
    }

    /**
     * The waveform's maximum allowed value, in dB. Value can range from -20 to -0.1.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_ceiling_db
     */
    fun getCeilingDb(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCeilingDbBind, segment)
    }

    /**
     * The volume threshold level from which the limiter begins to be active, in dB. Value can range
     * from -30 to 0.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_threshold_db
     */
    fun setThresholdDb(threshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setThresholdDbBind, segment, threshold)
    }

    /**
     * The volume threshold level from which the limiter begins to be active, in dB. Value can range
     * from -30 to 0.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_threshold_db
     */
    fun getThresholdDb(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getThresholdDbBind, segment)
    }

    /**
     * Modifies the volume of the limited waves, in dB. Value can range from 0 to 6.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_soft_clip_db
     */
    fun setSoftClipDb(softClip: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSoftClipDbBind, segment, softClip)
    }

    /**
     * Modifies the volume of the limited waves, in dB. Value can range from 0 to 6.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_soft_clip_db
     */
    fun getSoftClipDb(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSoftClipDbBind, segment)
    }

    /**
     * This property has no effect on the audio. Use `AudioEffectHardLimiter` instead, as this Limiter
     * effect is deprecated.
     *
     * Generated from Godot docs: AudioEffectLimiter.set_soft_clip_ratio
     */
    fun setSoftClipRatio(softClip: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSoftClipRatioBind, segment, softClip)
    }

    /**
     * This property has no effect on the audio. Use `AudioEffectHardLimiter` instead, as this Limiter
     * effect is deprecated.
     *
     * Generated from Godot docs: AudioEffectLimiter.get_soft_clip_ratio
     */
    fun getSoftClipRatio(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSoftClipRatioBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectLimiter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectLimiter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectLimiter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectLimiter? =
            if (handle.address() == 0L) null else AudioEffectLimiter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CEILING_DB_HASH = 373806689L
        @JvmField
        val setCeilingDbBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_ceiling_db", SET_CEILING_DB_HASH)

        private const val GET_CEILING_DB_HASH = 1740695150L
        @JvmField
        val getCeilingDbBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_ceiling_db", GET_CEILING_DB_HASH)

        private const val SET_THRESHOLD_DB_HASH = 373806689L
        @JvmField
        val setThresholdDbBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_threshold_db", SET_THRESHOLD_DB_HASH)

        private const val GET_THRESHOLD_DB_HASH = 1740695150L
        @JvmField
        val getThresholdDbBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_threshold_db", GET_THRESHOLD_DB_HASH)

        private const val SET_SOFT_CLIP_DB_HASH = 373806689L
        @JvmField
        val setSoftClipDbBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_soft_clip_db", SET_SOFT_CLIP_DB_HASH)

        private const val GET_SOFT_CLIP_DB_HASH = 1740695150L
        @JvmField
        val getSoftClipDbBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_soft_clip_db", GET_SOFT_CLIP_DB_HASH)

        private const val SET_SOFT_CLIP_RATIO_HASH = 373806689L
        @JvmField
        val setSoftClipRatioBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "set_soft_clip_ratio", SET_SOFT_CLIP_RATIO_HASH)

        private const val GET_SOFT_CLIP_RATIO_HASH = 1740695150L
        @JvmField
        val getSoftClipRatioBind =
            ObjectCalls.getMethodBind("AudioEffectLimiter", "get_soft_clip_ratio", GET_SOFT_CLIP_RATIO_HASH)
    }
}

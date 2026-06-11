package net.multigesture.kanama.api
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
/**
 * Generated from Godot docs: AnimationPlayer
 */
class AnimationPlayer(handle: MemorySegment) : AnimationMixer(handle) {
    val currentAnimationLength: Double
        @JvmName("currentAnimationLengthProperty")
        get() = getCurrentAnimationLength()
    val currentAnimationPosition: Double
        @JvmName("currentAnimationPositionProperty")
        get() = getCurrentAnimationPosition()
    var playbackAutoCapture: Boolean
        @JvmName("playbackAutoCaptureProperty")
        get() = isAutoCapture()
        @JvmName("setPlaybackAutoCaptureProperty")
        set(value) = setAutoCapture(value)
    var playbackAutoCaptureDuration: Double
        @JvmName("playbackAutoCaptureDurationProperty")
        get() = getAutoCaptureDuration()
        @JvmName("setPlaybackAutoCaptureDurationProperty")
        set(value) = setAutoCaptureDuration(value)
    var playbackAutoCaptureTransitionType: Long
        @JvmName("playbackAutoCaptureTransitionTypeProperty")
        get() = getAutoCaptureTransitionType()
        @JvmName("setPlaybackAutoCaptureTransitionTypeProperty")
        set(value) = setAutoCaptureTransitionType(value)
    var playbackAutoCaptureEaseType: Long
        @JvmName("playbackAutoCaptureEaseTypeProperty")
        get() = getAutoCaptureEaseType()
        @JvmName("setPlaybackAutoCaptureEaseTypeProperty")
        set(value) = setAutoCaptureEaseType(value)
    var playbackDefaultBlendTime: Double
        @JvmName("playbackDefaultBlendTimeProperty")
        get() = getDefaultBlendTime()
        @JvmName("setPlaybackDefaultBlendTimeProperty")
        set(value) = setDefaultBlendTime(value)
    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)
    var movieQuitOnFinish: Boolean
        @JvmName("movieQuitOnFinishProperty")
        get() = isMovieQuitOnFinishEnabled()
        @JvmName("setMovieQuitOnFinishProperty")
        set(value) = setMovieQuitOnFinishEnabled(value)
    fun animationSetNext(animationFrom: String, animationTo: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(animationSetNextBind, handle, animationFrom, animationTo)
    }
    fun setBlendTime(animationFrom: String, animationTo: String, sec: Double) {
        ObjectCalls.ptrcallWithTwoStringNameAndDoubleArg(setBlendTimeBind, handle, animationFrom, animationTo, sec)
    }
    fun getBlendTime(animationFrom: String, animationTo: String): Double {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(getBlendTimeBind, handle, animationFrom, animationTo)
    }
    fun setDefaultBlendTime(sec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDefaultBlendTimeBind, handle, sec)
    }
    fun getDefaultBlendTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDefaultBlendTimeBind, handle)
    }
    fun setAutoCapture(autoCapture: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoCaptureBind, handle, autoCapture)
    }
    fun isAutoCapture(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoCaptureBind, handle)
    }
    fun setAutoCaptureDuration(autoCaptureDuration: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutoCaptureDurationBind, handle, autoCaptureDuration)
    }
    fun getAutoCaptureDuration(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutoCaptureDurationBind, handle)
    }
    fun setAutoCaptureTransitionType(autoCaptureTransitionType: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutoCaptureTransitionTypeBind, handle, autoCaptureTransitionType)
    }
    fun getAutoCaptureTransitionType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutoCaptureTransitionTypeBind, handle)
    }
    fun setAutoCaptureEaseType(autoCaptureEaseType: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutoCaptureEaseTypeBind, handle, autoCaptureEaseType)
    }
    fun getAutoCaptureEaseType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutoCaptureEaseTypeBind, handle)
    }
    fun play(name: String, customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        currentAnimationName = name // Kanama sugar (hand): track for getCurrentAnimation
        ObjectCalls.ptrcallWithStringNameDoubleDoubleBoolArgs(playBind, handle, name, customBlend, customSpeed, fromEnd)
    }
    fun playSectionWithMarkers(name: String, startMarker: String, endMarker: String, customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithThreeStringNameTwoDoubleBoolArgs(playSectionWithMarkersBind, handle, name, startMarker, endMarker, customBlend, customSpeed, fromEnd)
    }
    fun playSection(name: String, startTime: Double = -1.0, endTime: Double = -1.0, customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameFourDoubleBoolArgs(playSectionBind, handle, name, startTime, endTime, customBlend, customSpeed, fromEnd)
    }
    fun playBackwards(name: String, customBlend: Double = -1.0) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(playBackwardsBind, handle, name, customBlend)
    }
    fun playSectionWithMarkersBackwards(name: String, startMarker: String, endMarker: String, customBlend: Double = -1.0) {
        ObjectCalls.ptrcallWithThreeStringNameAndDoubleArg(playSectionWithMarkersBackwardsBind, handle, name, startMarker, endMarker, customBlend)
    }
    fun playSectionBackwards(name: String, startTime: Double = -1.0, endTime: Double = -1.0, customBlend: Double = -1.0) {
        ObjectCalls.ptrcallWithStringNameAndThreeDoubleArgs(playSectionBackwardsBind, handle, name, startTime, endTime, customBlend)
    }
    fun playWithCapture(name: String, duration: Double = -1.0, customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false, transType: Long = 0L, easeType: Long = 0L) {
        ObjectCalls.ptrcallWithStringNameThreeDoubleBoolTwoLongArgs(playWithCaptureBind, handle, name, duration, customBlend, customSpeed, fromEnd, transType, easeType)
    }
    fun pause() {
        ObjectCalls.ptrcallNoArgs(pauseBind, handle)
    }
    fun stop(keepState: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(stopBind, handle, keepState)
    }
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }
    fun isAnimationActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAnimationActiveBind, handle)
    }
    fun setCurrentAnimation(animation: String) {
        ObjectCalls.ptrcallWithStringNameArg(setCurrentAnimationBind, handle, animation)
    }
    fun setAssignedAnimation(animation: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAssignedAnimationBind, handle, animation)
    }
    fun queue(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(queueBind, handle, name)
    }
    fun clearQueue() {
        ObjectCalls.ptrcallNoArgs(clearQueueBind, handle)
    }
    fun setSpeedScale(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, speed)
    }
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }
    fun getPlayingSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlayingSpeedBind, handle)
    }
    fun setAutoplay(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAutoplayBind, handle, name)
    }
    fun setMovieQuitOnFinishEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMovieQuitOnFinishEnabledBind, handle, enabled)
    }
    fun isMovieQuitOnFinishEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMovieQuitOnFinishEnabledBind, handle)
    }
    fun getCurrentAnimationPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCurrentAnimationPositionBind, handle)
    }
    fun getCurrentAnimationLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCurrentAnimationLengthBind, handle)
    }
    fun setSectionWithMarkers(startMarker: String, endMarker: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(setSectionWithMarkersBind, handle, startMarker, endMarker)
    }
    fun setSection(startTime: Double = -1.0, endTime: Double = -1.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(setSectionBind, handle, startTime, endTime)
    }
    fun resetSection() {
        ObjectCalls.ptrcallNoArgs(resetSectionBind, handle)
    }
    fun getSectionStartTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSectionStartTimeBind, handle)
    }
    fun getSectionEndTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSectionEndTimeBind, handle)
    }
    fun hasSection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasSectionBind, handle)
    }
    fun seek(seconds: Double, update: Boolean = false, updateOnly: Boolean = false) {
        ObjectCalls.ptrcallWithDoubleAndTwoBoolArgs(seekBind, handle, seconds, update, updateOnly)
    }
    fun setProcessCallback(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessCallbackBind, handle, mode)
    }
    fun getProcessCallback(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessCallbackBind, handle)
    }
    fun setMethodCallMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMethodCallModeBind, handle, mode)
    }
    fun getMethodCallMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMethodCallModeBind, handle)
    }

    // KANAMA-IOS-SUGAR: hand-added to a generated file; re-add after regeneration.
    // ── Kanama sugar (not generated from Godot docs) ──────────────────────────

    // KANAMA-IOS-STUB: getCurrentAnimation returns the last play() name, not Godot's live
    // current_animation (String-return ptrcall not wired). Backlog.
    // get_current_animation() returns StringName — a String-return ptrcall isn't wired
    // yet. Track the last name passed to play() instead (the demos only change animation
    // via play()), so `if (getCurrentAnimation() != "walk") play("walk")` stops
    // re-triggering every frame (which caused the walk/idle animation to jitter).
    // Backlog: real String-return ptrcall (then read Godot's actual current_animation).
    private var currentAnimationName: String = ""

    fun getCurrentAnimation(): String = currentAnimationName

    object Signals {
        const val currentAnimationChanged: String = "current_animation_changed"
        const val animationChanged: String = "animation_changed"
    }
    companion object {
        const val ANIMATION_PROCESS_PHYSICS: Long = 0L
        const val ANIMATION_PROCESS_IDLE: Long = 1L
        const val ANIMATION_PROCESS_MANUAL: Long = 2L
        const val ANIMATION_METHOD_CALL_DEFERRED: Long = 0L
        const val ANIMATION_METHOD_CALL_IMMEDIATE: Long = 1L
        fun fromHandle(handle: MemorySegment): AnimationPlayer? =
            wrap(handle)
        internal fun wrap(handle: MemorySegment): AnimationPlayer? =
            if (handle.address() == 0L) null else AnimationPlayer(handle)
        private const val ANIMATION_SET_NEXT_HASH = 3740211285L
        private val animationSetNextBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "animation_set_next", ANIMATION_SET_NEXT_HASH)
        }
        private const val SET_BLEND_TIME_HASH = 3231131886L
        private val setBlendTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_blend_time", SET_BLEND_TIME_HASH)
        }
        private const val GET_BLEND_TIME_HASH = 1958752504L
        private val getBlendTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_blend_time", GET_BLEND_TIME_HASH)
        }
        private const val SET_DEFAULT_BLEND_TIME_HASH = 373806689L
        private val setDefaultBlendTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_default_blend_time", SET_DEFAULT_BLEND_TIME_HASH)
        }
        private const val GET_DEFAULT_BLEND_TIME_HASH = 1740695150L
        private val getDefaultBlendTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_default_blend_time", GET_DEFAULT_BLEND_TIME_HASH)
        }
        private const val SET_AUTO_CAPTURE_HASH = 2586408642L
        private val setAutoCaptureBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture", SET_AUTO_CAPTURE_HASH)
        }
        private const val IS_AUTO_CAPTURE_HASH = 36873697L
        private val isAutoCaptureBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "is_auto_capture", IS_AUTO_CAPTURE_HASH)
        }
        private const val SET_AUTO_CAPTURE_DURATION_HASH = 373806689L
        private val setAutoCaptureDurationBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture_duration", SET_AUTO_CAPTURE_DURATION_HASH)
        }
        private const val GET_AUTO_CAPTURE_DURATION_HASH = 1740695150L
        private val getAutoCaptureDurationBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_auto_capture_duration", GET_AUTO_CAPTURE_DURATION_HASH)
        }
        private const val SET_AUTO_CAPTURE_TRANSITION_TYPE_HASH = 1058637742L
        private val setAutoCaptureTransitionTypeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture_transition_type", SET_AUTO_CAPTURE_TRANSITION_TYPE_HASH)
        }
        private const val GET_AUTO_CAPTURE_TRANSITION_TYPE_HASH = 3842314528L
        private val getAutoCaptureTransitionTypeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_auto_capture_transition_type", GET_AUTO_CAPTURE_TRANSITION_TYPE_HASH)
        }
        private const val SET_AUTO_CAPTURE_EASE_TYPE_HASH = 1208105857L
        private val setAutoCaptureEaseTypeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture_ease_type", SET_AUTO_CAPTURE_EASE_TYPE_HASH)
        }
        private const val GET_AUTO_CAPTURE_EASE_TYPE_HASH = 631880200L
        private val getAutoCaptureEaseTypeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_auto_capture_ease_type", GET_AUTO_CAPTURE_EASE_TYPE_HASH)
        }
        private const val PLAY_HASH = 3118260607L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play", PLAY_HASH)
        }
        private const val PLAY_SECTION_WITH_MARKERS_HASH = 1421431412L
        private val playSectionWithMarkersBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section_with_markers", PLAY_SECTION_WITH_MARKERS_HASH)
        }
        private const val PLAY_SECTION_HASH = 284774635L
        private val playSectionBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section", PLAY_SECTION_HASH)
        }
        private const val PLAY_BACKWARDS_HASH = 2787282401L
        private val playBackwardsBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play_backwards", PLAY_BACKWARDS_HASH)
        }
        private const val PLAY_SECTION_WITH_MARKERS_BACKWARDS_HASH = 910195100L
        private val playSectionWithMarkersBackwardsBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section_with_markers_backwards", PLAY_SECTION_WITH_MARKERS_BACKWARDS_HASH)
        }
        private const val PLAY_SECTION_BACKWARDS_HASH = 831955981L
        private val playSectionBackwardsBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section_backwards", PLAY_SECTION_BACKWARDS_HASH)
        }
        private const val PLAY_WITH_CAPTURE_HASH = 1572969103L
        private val playWithCaptureBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "play_with_capture", PLAY_WITH_CAPTURE_HASH)
        }
        private const val PAUSE_HASH = 3218959716L
        private val pauseBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "pause", PAUSE_HASH)
        }
        private const val STOP_HASH = 107499316L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "stop", STOP_HASH)
        }
        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "is_playing", IS_PLAYING_HASH)
        }
        private const val IS_ANIMATION_ACTIVE_HASH = 36873697L
        private val isAnimationActiveBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "is_animation_active", IS_ANIMATION_ACTIVE_HASH)
        }
        private const val SET_CURRENT_ANIMATION_HASH = 3304788590L
        private val setCurrentAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_current_animation", SET_CURRENT_ANIMATION_HASH)
        }
        private const val SET_ASSIGNED_ANIMATION_HASH = 3304788590L
        private val setAssignedAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_assigned_animation", SET_ASSIGNED_ANIMATION_HASH)
        }
        private const val QUEUE_HASH = 3304788590L
        private val queueBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "queue", QUEUE_HASH)
        }
        private const val CLEAR_QUEUE_HASH = 3218959716L
        private val clearQueueBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "clear_queue", CLEAR_QUEUE_HASH)
        }
        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }
        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }
        private const val GET_PLAYING_SPEED_HASH = 1740695150L
        private val getPlayingSpeedBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_playing_speed", GET_PLAYING_SPEED_HASH)
        }
        private const val SET_AUTOPLAY_HASH = 3304788590L
        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_autoplay", SET_AUTOPLAY_HASH)
        }
        private const val SET_MOVIE_QUIT_ON_FINISH_ENABLED_HASH = 2586408642L
        private val setMovieQuitOnFinishEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_movie_quit_on_finish_enabled", SET_MOVIE_QUIT_ON_FINISH_ENABLED_HASH)
        }
        private const val IS_MOVIE_QUIT_ON_FINISH_ENABLED_HASH = 36873697L
        private val isMovieQuitOnFinishEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "is_movie_quit_on_finish_enabled", IS_MOVIE_QUIT_ON_FINISH_ENABLED_HASH)
        }
        private const val GET_CURRENT_ANIMATION_POSITION_HASH = 1740695150L
        private val getCurrentAnimationPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_current_animation_position", GET_CURRENT_ANIMATION_POSITION_HASH)
        }
        private const val GET_CURRENT_ANIMATION_LENGTH_HASH = 1740695150L
        private val getCurrentAnimationLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_current_animation_length", GET_CURRENT_ANIMATION_LENGTH_HASH)
        }
        private const val SET_SECTION_WITH_MARKERS_HASH = 794792241L
        private val setSectionWithMarkersBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_section_with_markers", SET_SECTION_WITH_MARKERS_HASH)
        }
        private const val SET_SECTION_HASH = 3749779719L
        private val setSectionBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_section", SET_SECTION_HASH)
        }
        private const val RESET_SECTION_HASH = 3218959716L
        private val resetSectionBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "reset_section", RESET_SECTION_HASH)
        }
        private const val GET_SECTION_START_TIME_HASH = 1740695150L
        private val getSectionStartTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_section_start_time", GET_SECTION_START_TIME_HASH)
        }
        private const val GET_SECTION_END_TIME_HASH = 1740695150L
        private val getSectionEndTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_section_end_time", GET_SECTION_END_TIME_HASH)
        }
        private const val HAS_SECTION_HASH = 36873697L
        private val hasSectionBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "has_section", HAS_SECTION_HASH)
        }
        private const val SEEK_HASH = 1807872683L
        private val seekBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "seek", SEEK_HASH)
        }
        private const val SET_PROCESS_CALLBACK_HASH = 1663839457L
        private val setProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_process_callback", SET_PROCESS_CALLBACK_HASH)
        }
        private const val GET_PROCESS_CALLBACK_HASH = 4207496604L
        private val getProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_process_callback", GET_PROCESS_CALLBACK_HASH)
        }
        private const val SET_METHOD_CALL_MODE_HASH = 3413514846L
        private val setMethodCallModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "set_method_call_mode", SET_METHOD_CALL_MODE_HASH)
        }
        private const val GET_METHOD_CALL_MODE_HASH = 3583380054L
        private val getMethodCallModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationPlayer", "get_method_call_mode", GET_METHOD_CALL_MODE_HASH)
        }
    }
}

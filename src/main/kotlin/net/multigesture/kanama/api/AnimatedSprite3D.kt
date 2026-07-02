package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * 2D sprite node in 3D world, that can use multiple 2D textures for animation.
 *
 * Generated from Godot docs: AnimatedSprite3D
 */
class AnimatedSprite3D(handle: MemorySegment) : SpriteBase3D(handle) {
    var spriteFrames: SpriteFrames?
        @JvmName("spriteFramesProperty")
        get() = getSpriteFrames()
        @JvmName("setSpriteFramesProperty")
        set(value) = setSpriteFrames(value)

    var animation: String
        @JvmName("animationProperty")
        get() = getAnimation()
        @JvmName("setAnimationProperty")
        set(value) = setAnimation(value)

    var autoplay: String
        @JvmName("autoplayProperty")
        get() = getAutoplay()
        @JvmName("setAutoplayProperty")
        set(value) = setAutoplay(value)

    var frame: Int
        @JvmName("frameProperty")
        get() = getFrame()
        @JvmName("setFrameProperty")
        set(value) = setFrame(value)

    var frameProgress: Double
        @JvmName("frameProgressProperty")
        get() = getFrameProgress()
        @JvmName("setFrameProgressProperty")
        set(value) = setFrameProgress(value)

    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)

    fun setSpriteFrames(spriteFrames: SpriteFrames?) {
        ObjectCalls.ptrcallWithObjectArgs(setSpriteFramesBind, handle, listOf(spriteFrames?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSpriteFrames(): SpriteFrames? {
        return SpriteFrames.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSpriteFramesBind, handle))
    }

    fun setAnimation(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAnimationBind, handle, name)
    }

    fun getAnimation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getAnimationBind, handle)
    }

    fun setAutoplay(name: String) {
        ObjectCalls.ptrcallWithStringArg(setAutoplayBind, handle, name)
    }

    fun getAutoplay(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAutoplayBind, handle)
    }

    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    /**
     * Plays the animation with key `name`. If `custom_speed` is negative and `from_end` is `true`, the
     * animation will play backwards (which is equivalent to calling `play_backwards`). If this method
     * is called with that same animation `name`, or with no `name` parameter, the assigned animation
     * will resume playing if it was paused.
     *
     * Generated from Godot docs: AnimatedSprite3D.play
     */
    fun play(name: String = "", customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameDoubleBoolArgs(playBind, handle, name, customSpeed, fromEnd)
    }

    fun playBackwards(name: String = "") {
        ObjectCalls.ptrcallWithStringNameArg(playBackwardsBind, handle, name)
    }

    /**
     * Pauses the currently playing animation. The `frame` and `frame_progress` will be kept and
     * calling `play` or `play_backwards` without arguments will resume the animation from the current
     * playback position. See also `stop`.
     *
     * Generated from Godot docs: AnimatedSprite3D.pause
     */
    fun pause() {
        ObjectCalls.ptrcallNoArgs(pauseBind, handle)
    }

    /**
     * Stops the currently playing animation. The animation position is reset to `0` and the
     * `custom_speed` is reset to `1.0`. See also `pause`.
     *
     * Generated from Godot docs: AnimatedSprite3D.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setFrameBind, handle, frame)
    }

    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameBind, handle)
    }

    fun setFrameProgress(progress: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrameProgressBind, handle, progress)
    }

    fun getFrameProgress(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrameProgressBind, handle)
    }

    fun setFrameAndProgress(frame: Int, progress: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setFrameAndProgressBind, handle, frame, progress)
    }

    fun setSpeedScale(speedScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, speedScale)
    }

    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    fun getPlayingSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlayingSpeedBind, handle)
    }

    object Signals {
        const val spriteFramesChanged: String = "sprite_frames_changed"
        const val animationChanged: String = "animation_changed"
        const val frameChanged: String = "frame_changed"
        const val animationLooped: String = "animation_looped"
        const val animationFinished: String = "animation_finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimatedSprite3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimatedSprite3D? =
            if (handle.address() == 0L) null else AnimatedSprite3D(handle)

        private const val SET_SPRITE_FRAMES_HASH = 905781144L
        private val setSpriteFramesBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_sprite_frames", SET_SPRITE_FRAMES_HASH)
        }

        private const val GET_SPRITE_FRAMES_HASH = 3804851214L
        private val getSpriteFramesBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_sprite_frames", GET_SPRITE_FRAMES_HASH)
        }

        private const val SET_ANIMATION_HASH = 3304788590L
        private val setAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_animation", SET_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_HASH = 2002593661L
        private val getAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_animation", GET_ANIMATION_HASH)
        }

        private const val SET_AUTOPLAY_HASH = 83702148L
        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_autoplay", SET_AUTOPLAY_HASH)
        }

        private const val GET_AUTOPLAY_HASH = 201670096L
        private val getAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_autoplay", GET_AUTOPLAY_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "is_playing", IS_PLAYING_HASH)
        }

        private const val PLAY_HASH = 3269405555L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "play", PLAY_HASH)
        }

        private const val PLAY_BACKWARDS_HASH = 3323268493L
        private val playBackwardsBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "play_backwards", PLAY_BACKWARDS_HASH)
        }

        private const val PAUSE_HASH = 3218959716L
        private val pauseBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "pause", PAUSE_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "stop", STOP_HASH)
        }

        private const val SET_FRAME_HASH = 1286410249L
        private val setFrameBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_frame", SET_FRAME_HASH)
        }

        private const val GET_FRAME_HASH = 3905245786L
        private val getFrameBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_frame", GET_FRAME_HASH)
        }

        private const val SET_FRAME_PROGRESS_HASH = 373806689L
        private val setFrameProgressBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_frame_progress", SET_FRAME_PROGRESS_HASH)
        }

        private const val GET_FRAME_PROGRESS_HASH = 1740695150L
        private val getFrameProgressBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_frame_progress", GET_FRAME_PROGRESS_HASH)
        }

        private const val SET_FRAME_AND_PROGRESS_HASH = 1602489585L
        private val setFrameAndProgressBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_frame_and_progress", SET_FRAME_AND_PROGRESS_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val GET_PLAYING_SPEED_HASH = 1740695150L
        private val getPlayingSpeedBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite3D", "get_playing_speed", GET_PLAYING_SPEED_HASH)
        }
    }
}

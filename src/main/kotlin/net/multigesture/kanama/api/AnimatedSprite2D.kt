package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Sprite node that contains multiple textures as frames to play for animation.
 *
 * Generated from Godot docs: AnimatedSprite2D
 */
class AnimatedSprite2D(handle: MemorySegment) : Node2D(handle) {
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

    var centered: Boolean
        @JvmName("centeredProperty")
        get() = isCentered()
        @JvmName("setCenteredProperty")
        set(value) = setCentered(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var flipH: Boolean
        @JvmName("flipHProperty")
        get() = isFlippedH()
        @JvmName("setFlipHProperty")
        set(value) = setFlipH(value)

    var flipV: Boolean
        @JvmName("flipVProperty")
        get() = isFlippedV()
        @JvmName("setFlipVProperty")
        set(value) = setFlipV(value)

    /**
     * The `SpriteFrames` resource containing the animation(s). Allows you the option to load, edit,
     * clear, make unique and save the states of the `SpriteFrames` resource.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_sprite_frames
     */
    fun setSpriteFrames(spriteFrames: SpriteFrames?) {
        ObjectCalls.ptrcallWithObjectArgs(setSpriteFramesBind, handle, listOf(spriteFrames?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `SpriteFrames` resource containing the animation(s). Allows you the option to load, edit,
     * clear, make unique and save the states of the `SpriteFrames` resource.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_sprite_frames
     */
    fun getSpriteFrames(): SpriteFrames? {
        return SpriteFrames.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSpriteFramesBind, handle))
    }

    /**
     * The current animation from the `sprite_frames` resource. If this value is changed, the `frame`
     * counter and the `frame_progress` are reset.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_animation
     */
    fun setAnimation(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAnimationBind, handle, name)
    }

    /**
     * The current animation from the `sprite_frames` resource. If this value is changed, the `frame`
     * counter and the `frame_progress` are reset.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_animation
     */
    fun getAnimation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getAnimationBind, handle)
    }

    /**
     * The key of the animation to play when the scene loads.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_autoplay
     */
    fun setAutoplay(name: String) {
        ObjectCalls.ptrcallWithStringArg(setAutoplayBind, handle, name)
    }

    /**
     * The key of the animation to play when the scene loads.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_autoplay
     */
    fun getAutoplay(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAutoplayBind, handle)
    }

    /**
     * Returns `true` if an animation is currently playing (even if `speed_scale` and/or `custom_speed`
     * are `0`).
     *
     * Generated from Godot docs: AnimatedSprite2D.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    /**
     * Plays the animation with key `name`. If `custom_speed` is negative and `from_end` is `true`, the
     * animation will play backwards (which is equivalent to calling `play_backwards`). If this method
     * is called with that same animation `name`, or with no `name` parameter, the assigned animation
     * will resume playing if it was paused.
     *
     * Generated from Godot docs: AnimatedSprite2D.play
     */
    fun play(name: String = "", customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameDoubleBoolArgs(playBind, handle, name, customSpeed, fromEnd)
    }

    /**
     * Plays the animation with key `name` in reverse. This method is a shorthand for `play` with
     * `custom_speed = -1.0` and `from_end = true`, so see its description for more information.
     *
     * Generated from Godot docs: AnimatedSprite2D.play_backwards
     */
    fun playBackwards(name: String = "") {
        ObjectCalls.ptrcallWithStringNameArg(playBackwardsBind, handle, name)
    }

    /**
     * Pauses the currently playing animation. The `frame` and `frame_progress` will be kept and
     * calling `play` or `play_backwards` without arguments will resume the animation from the current
     * playback position. See also `stop`.
     *
     * Generated from Godot docs: AnimatedSprite2D.pause
     */
    fun pause() {
        ObjectCalls.ptrcallNoArgs(pauseBind, handle)
    }

    /**
     * Stops the currently playing animation. The animation position is reset to `0` and the
     * `custom_speed` is reset to `1.0`. See also `pause`.
     *
     * Generated from Godot docs: AnimatedSprite2D.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    /**
     * If `true`, texture will be centered. Note: For games with a pixel art aesthetic, textures may
     * appear deformed when centered. This is caused by their position being between pixels. To prevent
     * this, set this property to `false`, or consider enabling
     * `ProjectSettings.rendering/2d/snap/snap_2d_vertices_to_pixel` and
     * `ProjectSettings.rendering/2d/snap/snap_2d_transforms_to_pixel`.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_centered
     */
    fun setCentered(centered: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenteredBind, handle, centered)
    }

    /**
     * If `true`, texture will be centered. Note: For games with a pixel art aesthetic, textures may
     * appear deformed when centered. This is caused by their position being between pixels. To prevent
     * this, set this property to `false`, or consider enabling
     * `ProjectSettings.rendering/2d/snap/snap_2d_vertices_to_pixel` and
     * `ProjectSettings.rendering/2d/snap/snap_2d_transforms_to_pixel`.
     *
     * Generated from Godot docs: AnimatedSprite2D.is_centered
     */
    fun isCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCenteredBind, handle)
    }

    /**
     * The texture's drawing offset.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The texture's drawing offset.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_flip_h
     */
    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, handle, flipH)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: AnimatedSprite2D.is_flipped_h
     */
    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, handle)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_flip_v
     */
    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, handle, flipV)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: AnimatedSprite2D.is_flipped_v
     */
    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, handle)
    }

    /**
     * The displayed animation frame's index. Setting this property also resets `frame_progress`. If
     * this is not desired, use `set_frame_and_progress`.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_frame
     */
    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setFrameBind, handle, frame)
    }

    /**
     * The displayed animation frame's index. Setting this property also resets `frame_progress`. If
     * this is not desired, use `set_frame_and_progress`.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_frame
     */
    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameBind, handle)
    }

    /**
     * The progress value between `0.0` and `1.0` until the current frame transitions to the next
     * frame. If the animation is playing backwards, the value transitions from `1.0` to `0.0`.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_frame_progress
     */
    fun setFrameProgress(progress: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrameProgressBind, handle, progress)
    }

    /**
     * The progress value between `0.0` and `1.0` until the current frame transitions to the next
     * frame. If the animation is playing backwards, the value transitions from `1.0` to `0.0`.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_frame_progress
     */
    fun getFrameProgress(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrameProgressBind, handle)
    }

    /**
     * Sets `frame` and `frame_progress` to the given values. Unlike setting `frame`, this method does
     * not reset the `frame_progress` to `0.0` implicitly.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_frame_and_progress
     */
    fun setFrameAndProgress(frame: Int, progress: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setFrameAndProgressBind, handle, frame, progress)
    }

    /**
     * The speed scaling ratio. For example, if this value is `1`, then the animation plays at normal
     * speed. If it's `0.5`, then it plays at half speed. If it's `2`, then it plays at double speed.
     * If set to a negative value, the animation is played in reverse. If set to `0`, the animation
     * will not advance.
     *
     * Generated from Godot docs: AnimatedSprite2D.set_speed_scale
     */
    fun setSpeedScale(speedScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, speedScale)
    }

    /**
     * The speed scaling ratio. For example, if this value is `1`, then the animation plays at normal
     * speed. If it's `0.5`, then it plays at half speed. If it's `2`, then it plays at double speed.
     * If set to a negative value, the animation is played in reverse. If set to `0`, the animation
     * will not advance.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    /**
     * Returns the actual playing speed of current animation or `0` if not playing. This speed is the
     * `speed_scale` property multiplied by `custom_speed` argument specified when calling the `play`
     * method. Returns a negative value if the current animation is playing backwards.
     *
     * Generated from Godot docs: AnimatedSprite2D.get_playing_speed
     */
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
        fun fromHandle(handle: MemorySegment): AnimatedSprite2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimatedSprite2D? =
            if (handle.address() == 0L) null else AnimatedSprite2D(handle)

        private const val SET_SPRITE_FRAMES_HASH = 905781144L
        private val setSpriteFramesBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_sprite_frames", SET_SPRITE_FRAMES_HASH)
        }

        private const val GET_SPRITE_FRAMES_HASH = 3804851214L
        private val getSpriteFramesBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_sprite_frames", GET_SPRITE_FRAMES_HASH)
        }

        private const val SET_ANIMATION_HASH = 3304788590L
        private val setAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_animation", SET_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_HASH = 2002593661L
        private val getAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_animation", GET_ANIMATION_HASH)
        }

        private const val SET_AUTOPLAY_HASH = 83702148L
        private val setAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_autoplay", SET_AUTOPLAY_HASH)
        }

        private const val GET_AUTOPLAY_HASH = 201670096L
        private val getAutoplayBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_autoplay", GET_AUTOPLAY_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "is_playing", IS_PLAYING_HASH)
        }

        private const val PLAY_HASH = 3269405555L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "play", PLAY_HASH)
        }

        private const val PLAY_BACKWARDS_HASH = 3323268493L
        private val playBackwardsBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "play_backwards", PLAY_BACKWARDS_HASH)
        }

        private const val PAUSE_HASH = 3218959716L
        private val pauseBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "pause", PAUSE_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "stop", STOP_HASH)
        }

        private const val SET_CENTERED_HASH = 2586408642L
        private val setCenteredBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_centered", SET_CENTERED_HASH)
        }

        private const val IS_CENTERED_HASH = 36873697L
        private val isCenteredBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "is_centered", IS_CENTERED_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val IS_FLIPPED_H_HASH = 36873697L
        private val isFlippedHBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "is_flipped_h", IS_FLIPPED_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val IS_FLIPPED_V_HASH = 36873697L
        private val isFlippedVBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "is_flipped_v", IS_FLIPPED_V_HASH)
        }

        private const val SET_FRAME_HASH = 1286410249L
        private val setFrameBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_frame", SET_FRAME_HASH)
        }

        private const val GET_FRAME_HASH = 3905245786L
        private val getFrameBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_frame", GET_FRAME_HASH)
        }

        private const val SET_FRAME_PROGRESS_HASH = 373806689L
        private val setFrameProgressBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_frame_progress", SET_FRAME_PROGRESS_HASH)
        }

        private const val GET_FRAME_PROGRESS_HASH = 1740695150L
        private val getFrameProgressBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_frame_progress", GET_FRAME_PROGRESS_HASH)
        }

        private const val SET_FRAME_AND_PROGRESS_HASH = 1602489585L
        private val setFrameAndProgressBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_frame_and_progress", SET_FRAME_AND_PROGRESS_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val GET_PLAYING_SPEED_HASH = 1740695150L
        private val getPlayingSpeedBind by lazy {
            ObjectCalls.getMethodBind("AnimatedSprite2D", "get_playing_speed", GET_PLAYING_SPEED_HASH)
        }
    }
}

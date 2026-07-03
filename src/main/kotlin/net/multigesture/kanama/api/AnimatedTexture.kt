package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Proxy texture for simple frame-based animations.
 *
 * Generated from Godot docs: AnimatedTexture
 */
class AnimatedTexture(handle: MemorySegment) : Texture2D(handle) {
    var frames: Int
        @JvmName("framesProperty")
        get() = getFrames()
        @JvmName("setFramesProperty")
        set(value) = setFrames(value)

    var currentFrame: Int
        @JvmName("currentFrameProperty")
        get() = getCurrentFrame()
        @JvmName("setCurrentFrameProperty")
        set(value) = setCurrentFrame(value)

    var pause: Boolean
        @JvmName("pauseProperty")
        get() = getPause()
        @JvmName("setPauseProperty")
        set(value) = setPause(value)

    var oneShot: Boolean
        @JvmName("oneShotProperty")
        get() = getOneShot()
        @JvmName("setOneShotProperty")
        set(value) = setOneShot(value)

    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)

    /**
     * Number of frames to use in the animation. While you can create the frames independently with
     * `set_frame_texture`, you need to set this value for the animation to take new frames into
     * account. The maximum number of frames is `MAX_FRAMES`.
     *
     * Generated from Godot docs: AnimatedTexture.set_frames
     */
    fun setFrames(frames: Int) {
        ObjectCalls.ptrcallWithIntArg(setFramesBind, handle, frames)
    }

    /**
     * Number of frames to use in the animation. While you can create the frames independently with
     * `set_frame_texture`, you need to set this value for the animation to take new frames into
     * account. The maximum number of frames is `MAX_FRAMES`.
     *
     * Generated from Godot docs: AnimatedTexture.get_frames
     */
    fun getFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFramesBind, handle)
    }

    /**
     * Sets the currently visible frame of the texture. Setting this frame while playing resets the
     * current frame time, so the newly selected frame plays for its whole configured frame duration.
     *
     * Generated from Godot docs: AnimatedTexture.set_current_frame
     */
    fun setCurrentFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setCurrentFrameBind, handle, frame)
    }

    /**
     * Sets the currently visible frame of the texture. Setting this frame while playing resets the
     * current frame time, so the newly selected frame plays for its whole configured frame duration.
     *
     * Generated from Godot docs: AnimatedTexture.get_current_frame
     */
    fun getCurrentFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentFrameBind, handle)
    }

    /**
     * If `true`, the animation will pause where it currently is (i.e. at `current_frame`). The
     * animation will continue from where it was paused when changing this property to `false`.
     *
     * Generated from Godot docs: AnimatedTexture.set_pause
     */
    fun setPause(pause: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPauseBind, handle, pause)
    }

    /**
     * If `true`, the animation will pause where it currently is (i.e. at `current_frame`). The
     * animation will continue from where it was paused when changing this property to `false`.
     *
     * Generated from Godot docs: AnimatedTexture.get_pause
     */
    fun getPause(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPauseBind, handle)
    }

    /**
     * If `true`, the animation will only play once and will not loop back to the first frame after
     * reaching the end. Note that reaching the end will not set `pause` to `true`.
     *
     * Generated from Godot docs: AnimatedTexture.set_one_shot
     */
    fun setOneShot(oneShot: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, oneShot)
    }

    /**
     * If `true`, the animation will only play once and will not loop back to the first frame after
     * reaching the end. Note that reaching the end will not set `pause` to `true`.
     *
     * Generated from Godot docs: AnimatedTexture.get_one_shot
     */
    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOneShotBind, handle)
    }

    /**
     * The animation speed is multiplied by this value. If set to a negative value, the animation is
     * played in reverse.
     *
     * Generated from Godot docs: AnimatedTexture.set_speed_scale
     */
    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, scale)
    }

    /**
     * The animation speed is multiplied by this value. If set to a negative value, the animation is
     * played in reverse.
     *
     * Generated from Godot docs: AnimatedTexture.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    /**
     * Assigns a `Texture2D` to the given frame. Frame IDs start at 0, so the first frame has ID 0, and
     * the last frame of the animation has ID `frames` - 1. You can define any number of textures up to
     * `MAX_FRAMES`, but keep in mind that only frames from 0 to `frames` - 1 will be part of the
     * animation.
     *
     * Generated from Godot docs: AnimatedTexture.set_frame_texture
     */
    fun setFrameTexture(frame: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setFrameTextureBind, handle, frame, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the given frame's `Texture2D`.
     *
     * Generated from Godot docs: AnimatedTexture.get_frame_texture
     */
    fun getFrameTexture(frame: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getFrameTextureBind, handle, frame))
    }

    /**
     * Sets the duration of any given `frame`. The final duration is affected by the `speed_scale`. If
     * set to `0`, the frame is skipped during playback.
     *
     * Generated from Godot docs: AnimatedTexture.set_frame_duration
     */
    fun setFrameDuration(frame: Int, duration: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setFrameDurationBind, handle, frame, duration)
    }

    /**
     * Returns the given `frame`'s duration, in seconds.
     *
     * Generated from Godot docs: AnimatedTexture.get_frame_duration
     */
    fun getFrameDuration(frame: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getFrameDurationBind, handle, frame)
    }

    companion object {
        const val MAX_FRAMES: Long = 256L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimatedTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimatedTexture? =
            if (handle.address() == 0L) null else AnimatedTexture(handle)

        private const val SET_FRAMES_HASH = 1286410249L
        private val setFramesBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_frames", SET_FRAMES_HASH)
        }

        private const val GET_FRAMES_HASH = 3905245786L
        private val getFramesBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_frames", GET_FRAMES_HASH)
        }

        private const val SET_CURRENT_FRAME_HASH = 1286410249L
        private val setCurrentFrameBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_current_frame", SET_CURRENT_FRAME_HASH)
        }

        private const val GET_CURRENT_FRAME_HASH = 3905245786L
        private val getCurrentFrameBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_current_frame", GET_CURRENT_FRAME_HASH)
        }

        private const val SET_PAUSE_HASH = 2586408642L
        private val setPauseBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_pause", SET_PAUSE_HASH)
        }

        private const val GET_PAUSE_HASH = 36873697L
        private val getPauseBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_pause", GET_PAUSE_HASH)
        }

        private const val SET_ONE_SHOT_HASH = 2586408642L
        private val setOneShotBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_one_shot", SET_ONE_SHOT_HASH)
        }

        private const val GET_ONE_SHOT_HASH = 36873697L
        private val getOneShotBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_one_shot", GET_ONE_SHOT_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val SET_FRAME_TEXTURE_HASH = 666127730L
        private val setFrameTextureBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_frame_texture", SET_FRAME_TEXTURE_HASH)
        }

        private const val GET_FRAME_TEXTURE_HASH = 3536238170L
        private val getFrameTextureBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_frame_texture", GET_FRAME_TEXTURE_HASH)
        }

        private const val SET_FRAME_DURATION_HASH = 1602489585L
        private val setFrameDurationBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "set_frame_duration", SET_FRAME_DURATION_HASH)
        }

        private const val GET_FRAME_DURATION_HASH = 2339986948L
        private val getFrameDurationBind by lazy {
            ObjectCalls.getMethodBind("AnimatedTexture", "get_frame_duration", GET_FRAME_DURATION_HASH)
        }
    }
}

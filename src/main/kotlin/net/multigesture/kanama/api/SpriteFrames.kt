package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Sprite frame library for AnimatedSprite2D and AnimatedSprite3D.
 *
 * Generated from Godot docs: SpriteFrames
 */
class SpriteFrames(handle: MemorySegment) : Resource(handle) {
    /**
     * Adds a new `anim` animation to the library.
     *
     * Generated from Godot docs: SpriteFrames.add_animation
     */
    fun addAnimation(anim: String) {
        ObjectCalls.ptrcallWithStringNameArg(addAnimationBind, handle, anim)
    }

    /**
     * Returns `true` if the `anim` animation exists.
     *
     * Generated from Godot docs: SpriteFrames.has_animation
     */
    fun hasAnimation(anim: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationBind, handle, anim)
    }

    /**
     * Duplicates the animation `anim_from` to a new animation named `anim_to`. Fails if `anim_to`
     * already exists, or if `anim_from` does not exist.
     *
     * Generated from Godot docs: SpriteFrames.duplicate_animation
     */
    fun duplicateAnimation(animFrom: String, animTo: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(duplicateAnimationBind, handle, animFrom, animTo)
    }

    /**
     * Removes the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.remove_animation
     */
    fun removeAnimation(anim: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeAnimationBind, handle, anim)
    }

    /**
     * Changes the `anim` animation's name to `newname`.
     *
     * Generated from Godot docs: SpriteFrames.rename_animation
     */
    fun renameAnimation(anim: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameAnimationBind, handle, anim, newname)
    }

    /**
     * Returns an array containing the names associated to each animation. Values are placed in
     * alphabetical order.
     *
     * Generated from Godot docs: SpriteFrames.get_animation_names
     */
    fun getAnimationNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAnimationNamesBind, handle)
    }

    /**
     * Sets the speed for the `anim` animation in frames per second.
     *
     * Generated from Godot docs: SpriteFrames.set_animation_speed
     */
    fun setAnimationSpeed(anim: String, fps: Double) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(setAnimationSpeedBind, handle, anim, fps)
    }

    /**
     * Returns the speed in frames per second for the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.get_animation_speed
     */
    fun getAnimationSpeed(anim: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(getAnimationSpeedBind, handle, anim)
    }

    /**
     * If `loop` is `false` equivalent to `set_animation_loop_mode(LOOP_NONE)`. If `loop` is `true`
     * equivalent to `set_animation_loop_mode(LOOP_LINEAR)`.
     *
     * Generated from Godot docs: SpriteFrames.set_animation_loop
     */
    fun setAnimationLoop(anim: String, loop: Boolean) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(setAnimationLoopBind, handle, anim, loop)
    }

    /**
     * Returns `true` if `get_animation_loop_mode(anim) == LOOP_LINEAR`. Otherwise, returns `false`.
     *
     * Generated from Godot docs: SpriteFrames.get_animation_loop
     */
    fun getAnimationLoop(anim: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(getAnimationLoopBind, handle, anim)
    }

    /**
     * Sets the `loop_mode` for the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.set_animation_loop_mode
     */
    fun setAnimationLoopMode(anim: String, loopMode: Long) {
        ObjectCalls.ptrcallWithStringNameAndLongArg(setAnimationLoopModeBind, handle, anim, loopMode)
    }

    /**
     * Returns the loop mode for the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.get_animation_loop_mode
     */
    fun getAnimationLoopMode(anim: String): Long {
        return ObjectCalls.ptrcallWithStringNameArgRetLong(getAnimationLoopModeBind, handle, anim)
    }

    /**
     * Adds a frame to the `anim` animation. If `at_position` is `-1`, the frame will be added to the
     * end of the animation. `duration` specifies the relative duration, see `get_frame_duration` for
     * details.
     *
     * Generated from Godot docs: SpriteFrames.add_frame
     */
    fun addFrame(anim: String, texture: Texture2D?, duration: Double = 1.0, atPosition: Int = -1) {
        ObjectCalls.ptrcallWithStringNameObjectDoubleIntArgs(addFrameBind, handle, anim, texture?.requireOpenHandle() ?: MemorySegment.NULL, duration, atPosition)
    }

    /**
     * Sets the `texture` and the `duration` of the frame `idx` in the `anim` animation. `duration`
     * specifies the relative duration, see `get_frame_duration` for details.
     *
     * Generated from Godot docs: SpriteFrames.set_frame
     */
    fun setFrame(anim: String, idx: Int, texture: Texture2D?, duration: Double = 1.0) {
        ObjectCalls.ptrcallWithStringNameIntObjectDoubleArgs(setFrameBind, handle, anim, idx, texture?.requireOpenHandle() ?: MemorySegment.NULL, duration)
    }

    /**
     * Removes the `anim` animation's frame `idx`.
     *
     * Generated from Godot docs: SpriteFrames.remove_frame
     */
    fun removeFrame(anim: String, idx: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(removeFrameBind, handle, anim, idx)
    }

    /**
     * Returns the number of frames for the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.get_frame_count
     */
    fun getFrameCount(anim: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(getFrameCountBind, handle, anim)
    }

    /**
     * Returns the texture of the frame `idx` in the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.get_frame_texture
     */
    fun getFrameTexture(anim: String, idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithStringNameAndIntArgRetObject(getFrameTextureBind, handle, anim, idx))
    }

    /**
     * Returns a relative duration of the frame `idx` in the `anim` animation (defaults to `1.0`). For
     * example, a frame with a duration of `2.0` is displayed twice as long as a frame with a duration
     * of `1.0`. You can calculate the absolute duration (in seconds) of a frame using the following
     * formula:
     *
     * Generated from Godot docs: SpriteFrames.get_frame_duration
     */
    fun getFrameDuration(anim: String, idx: Int): Double {
        return ObjectCalls.ptrcallWithStringNameAndIntArgRetDouble(getFrameDurationBind, handle, anim, idx)
    }

    /**
     * Removes all frames from the `anim` animation.
     *
     * Generated from Godot docs: SpriteFrames.clear
     */
    fun clear(anim: String) {
        ObjectCalls.ptrcallWithStringNameArg(clearBind, handle, anim)
    }

    /**
     * Removes all animations. An empty `default` animation will be created.
     *
     * Generated from Godot docs: SpriteFrames.clear_all
     */
    fun clearAll() {
        ObjectCalls.ptrcallNoArgs(clearAllBind, handle)
    }

    companion object {
        const val LOOP_NONE: Long = 0L
        const val LOOP_LINEAR: Long = 1L
        const val LOOP_PINGPONG: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpriteFrames? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpriteFrames? =
            if (handle.address() == 0L) null else SpriteFrames(handle)

        private const val ADD_ANIMATION_HASH = 3304788590L
        private val addAnimationBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "add_animation", ADD_ANIMATION_HASH)
        }

        private const val HAS_ANIMATION_HASH = 2619796661L
        private val hasAnimationBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "has_animation", HAS_ANIMATION_HASH)
        }

        private const val DUPLICATE_ANIMATION_HASH = 3740211285L
        private val duplicateAnimationBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "duplicate_animation", DUPLICATE_ANIMATION_HASH)
        }

        private const val REMOVE_ANIMATION_HASH = 3304788590L
        private val removeAnimationBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "remove_animation", REMOVE_ANIMATION_HASH)
        }

        private const val RENAME_ANIMATION_HASH = 3740211285L
        private val renameAnimationBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "rename_animation", RENAME_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_NAMES_HASH = 1139954409L
        private val getAnimationNamesBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_animation_names", GET_ANIMATION_NAMES_HASH)
        }

        private const val SET_ANIMATION_SPEED_HASH = 4135858297L
        private val setAnimationSpeedBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "set_animation_speed", SET_ANIMATION_SPEED_HASH)
        }

        private const val GET_ANIMATION_SPEED_HASH = 2349060816L
        private val getAnimationSpeedBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_animation_speed", GET_ANIMATION_SPEED_HASH)
        }

        private const val SET_ANIMATION_LOOP_HASH = 2524380260L
        private val setAnimationLoopBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "set_animation_loop", SET_ANIMATION_LOOP_HASH)
        }

        private const val GET_ANIMATION_LOOP_HASH = 2619796661L
        private val getAnimationLoopBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_animation_loop", GET_ANIMATION_LOOP_HASH)
        }

        private const val SET_ANIMATION_LOOP_MODE_HASH = 918068248L
        private val setAnimationLoopModeBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "set_animation_loop_mode", SET_ANIMATION_LOOP_MODE_HASH)
        }

        private const val GET_ANIMATION_LOOP_MODE_HASH = 3606360228L
        private val getAnimationLoopModeBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_animation_loop_mode", GET_ANIMATION_LOOP_MODE_HASH)
        }

        private const val ADD_FRAME_HASH = 1351332740L
        private val addFrameBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "add_frame", ADD_FRAME_HASH)
        }

        private const val SET_FRAME_HASH = 56804795L
        private val setFrameBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "set_frame", SET_FRAME_HASH)
        }

        private const val REMOVE_FRAME_HASH = 2415702435L
        private val removeFrameBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "remove_frame", REMOVE_FRAME_HASH)
        }

        private const val GET_FRAME_COUNT_HASH = 2458036349L
        private val getFrameCountBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_frame_count", GET_FRAME_COUNT_HASH)
        }

        private const val GET_FRAME_TEXTURE_HASH = 2900517879L
        private val getFrameTextureBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_frame_texture", GET_FRAME_TEXTURE_HASH)
        }

        private const val GET_FRAME_DURATION_HASH = 1129309260L
        private val getFrameDurationBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "get_frame_duration", GET_FRAME_DURATION_HASH)
        }

        private const val CLEAR_HASH = 3304788590L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "clear", CLEAR_HASH)
        }

        private const val CLEAR_ALL_HASH = 3218959716L
        private val clearAllBind by lazy {
            ObjectCalls.getMethodBind("SpriteFrames", "clear_all", CLEAR_ALL_HASH)
        }
    }
}

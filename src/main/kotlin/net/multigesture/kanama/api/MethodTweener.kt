package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Interpolates an abstract value and supplies it to a method called over time.
 *
 * Generated from Godot docs: MethodTweener
 */
class MethodTweener(handle: MemorySegment) : Tweener(handle) {
    /**
     * Sets the time in seconds after which the `MethodTweener` will start interpolating. By default
     * there's no delay.
     *
     * Generated from Godot docs: MethodTweener.set_delay
     */
    fun setDelay(delay: Double): MethodTweener? {
        return MethodTweener.wrap(ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, handle, delay))
    }

    /**
     * Sets the type of used transition from `Tween.TransitionType`. If not set, the default transition
     * is used from the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: MethodTweener.set_trans
     */
    fun setTrans(trans: Long): MethodTweener? {
        return MethodTweener.wrap(ObjectCalls.ptrcallWithLongArgRetObject(setTransBind, handle, trans))
    }

    /**
     * Sets the type of used easing from `Tween.EaseType`. If not set, the default easing is used from
     * the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: MethodTweener.set_ease
     */
    fun setEase(ease: Long): MethodTweener? {
        return MethodTweener.wrap(ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, handle, ease))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MethodTweener? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MethodTweener? =
            if (handle.address() == 0L) null else MethodTweener(handle)

        private const val SET_DELAY_HASH = 266477812L
        private val setDelayBind by lazy {
            ObjectCalls.getMethodBind("MethodTweener", "set_delay", SET_DELAY_HASH)
        }

        private const val SET_TRANS_HASH = 3740975367L
        private val setTransBind by lazy {
            ObjectCalls.getMethodBind("MethodTweener", "set_trans", SET_TRANS_HASH)
        }

        private const val SET_EASE_HASH = 315540545L
        private val setEaseBind by lazy {
            ObjectCalls.getMethodBind("MethodTweener", "set_ease", SET_EASE_HASH)
        }
    }
}

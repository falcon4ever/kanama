package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireGodotReturn

/**
 * Interpolates an abstract value and supplies it to a method called over time.
 *
 * Generated from Godot docs: MethodTweener
 */
class MethodTweener(handle: GodotHandle) : Tweener(handle) {
    /**
     * Sets the time in seconds after which the `MethodTweener` will start interpolating. By default
     * there's no delay.
     *
     * Generated from Godot docs: MethodTweener.set_delay
     */
    fun setDelay(delay: Double): MethodTweener {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, segment, delay)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(MethodTweener.wrap(ret), "MethodTweener.set_delay")
    }

    /**
     * Sets the type of used transition from `Tween.TransitionType`. If not set, the default transition
     * is used from the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: MethodTweener.set_trans
     */
    fun setTrans(trans: Tween.TransitionType): MethodTweener {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setTransBind, segment, trans.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(MethodTweener.wrap(ret), "MethodTweener.set_trans")
    }

    /**
     * Sets the type of used easing from `Tween.EaseType`. If not set, the default easing is used from
     * the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: MethodTweener.set_ease
     */
    fun setEase(ease: Tween.EaseType): MethodTweener {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, segment, ease.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(MethodTweener.wrap(ret), "MethodTweener.set_ease")
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MethodTweener? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MethodTweener? =
            if (handle.address() == 0L) null else MethodTweener(GodotHandle(handle))

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

package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Interpolates an `Object`'s property over time.
 *
 * Generated from Godot docs: PropertyTweener
 */
class PropertyTweener(handle: GodotHandle) : Tweener(handle) {
    /**
     * Sets a custom initial value to the `PropertyTweener`.
     *
     * Generated from Godot docs: PropertyTweener.from
     */
    fun from(value: Any?): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithVariantArgRetObject(fromBind, segment, value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    /**
     * Makes the `PropertyTweener` use the current property value (i.e. at the time of creating this
     * `PropertyTweener`) as a starting point. This is equivalent of using `from` with the current
     * value. These two calls will do the same:
     *
     * Generated from Godot docs: PropertyTweener.from_current
     */
    fun fromCurrent(): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(fromCurrentBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    /**
     * When called, the final value will be used as a relative value instead.
     *
     * Generated from Godot docs: PropertyTweener.as_relative
     */
    fun asRelative(): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(asRelativeBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    /**
     * Sets the type of used transition from `Tween.TransitionType`. If not set, the default transition
     * is used from the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: PropertyTweener.set_trans
     */
    fun setTrans(trans: Long): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setTransBind, segment, trans)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    /**
     * Sets the type of used easing from `Tween.EaseType`. If not set, the default easing is used from
     * the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: PropertyTweener.set_ease
     */
    fun setEase(ease: Long): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, segment, ease)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    /**
     * Allows interpolating the value with a custom easing function. The provided `interpolator_method`
     * will be called with a value ranging from `0.0` to `1.0` and is expected to return a value within
     * the same range (values outside the range can be used for overshoot). The return value of the
     * method is then used for interpolation between initial and final value. Note that the parameter
     * passed to the method is still subject to the tweener's own easing.
     *
     * Generated from Godot docs: PropertyTweener.set_custom_interpolator
     */
    fun setCustomInterpolator(interpolatorMethod: GodotCallable): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithCallableArgRetObject(setCustomInterpolatorBind, segment, interpolatorMethod.target.segment, interpolatorMethod.method)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    /**
     * Sets the time in seconds after which the `PropertyTweener` will start interpolating. By default
     * there's no delay.
     *
     * Generated from Godot docs: PropertyTweener.set_delay
     */
    fun setDelay(delay: Double): PropertyTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, segment, delay)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return PropertyTweener.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PropertyTweener? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PropertyTweener? =
            if (handle.address() == 0L) null else PropertyTweener(GodotHandle(handle))

        private const val FROM_HASH = 4190193059L
        private val fromBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "from", FROM_HASH)
        }

        private const val FROM_CURRENT_HASH = 4279177709L
        private val fromCurrentBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "from_current", FROM_CURRENT_HASH)
        }

        private const val AS_RELATIVE_HASH = 4279177709L
        private val asRelativeBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "as_relative", AS_RELATIVE_HASH)
        }

        private const val SET_TRANS_HASH = 1899107404L
        private val setTransBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_trans", SET_TRANS_HASH)
        }

        private const val SET_EASE_HASH = 1080455622L
        private val setEaseBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_ease", SET_EASE_HASH)
        }

        private const val SET_CUSTOM_INTERPOLATOR_HASH = 3174170268L
        private val setCustomInterpolatorBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_custom_interpolator", SET_CUSTOM_INTERPOLATOR_HASH)
        }

        private const val SET_DELAY_HASH = 2171559331L
        private val setDelayBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_delay", SET_DELAY_HASH)
        }
    }
}

package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Interpolates an `Object`'s property over time.
 *
 * Generated from Godot docs: PropertyTweener
 */
class PropertyTweener internal constructor(handle: GodotHandle) : Tweener(handle) {

    /**
     * Sets a custom initial value to the `PropertyTweener`.
     *
     * Generated from Godot docs: PropertyTweener.from
     */
    fun from(value: Any?): PropertyTweener {
        checkOpen()
        // Must be the ptrcall path: wrapOrThis releases the +1 the return slot transfers,
        // and the Variant call path is ref-neutral (releasing there underflows the tweener).
        return wrapOrThis(ObjectCalls.ptrcallWithVariantArgRetObject(fromBind, segment, value))
    }

    /**
     * Makes the `PropertyTweener` use the current property value (i.e. at the time of creating this
     * `PropertyTweener`) as a starting point. This is equivalent of using `from` with the current
     * value. These two calls will do the same:
     *
     * Generated from Godot docs: PropertyTweener.from_current
     */
    fun fromCurrent(): PropertyTweener {
        checkOpen()
        return wrapOrThis(ObjectCalls.ptrcallNoArgsRetObject(fromCurrentBind, segment))
    }

    /**
     * When called, the final value will be used as a relative value instead.
     *
     * Generated from Godot docs: PropertyTweener.as_relative
     */
    fun asRelative(): PropertyTweener {
        checkOpen()
        return wrapOrThis(ObjectCalls.ptrcallNoArgsRetObject(asRelativeBind, segment))
    }

    /**
     * Sets the type of used transition from `Tween.TransitionType`. If not set, the default transition
     * is used from the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: PropertyTweener.set_trans
     */
    fun setTrans(trans: Long): PropertyTweener {
        checkOpen()
        return wrapOrThis(ObjectCalls.ptrcallWithLongArgRetObject(setTransBind, segment, trans))
    }

    /**
     * Sets the type of used easing from `Tween.EaseType`. If not set, the default easing is used from
     * the `Tween` that contains this Tweener.
     *
     * Generated from Godot docs: PropertyTweener.set_ease
     */
    fun setEase(ease: Long): PropertyTweener {
        checkOpen()
        return wrapOrThis(ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, segment, ease))
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
    fun setCustomInterpolator(interpolatorMethod: GodotCallable): PropertyTweener {
        checkOpen()
        return wrapOrThis(
            ObjectCalls.ptrcallWithCallableArgRetObject(
                setCustomInterpolatorBind,
                segment,
                interpolatorMethod.target.segment,
                interpolatorMethod.method,
            ),
        )
    }

    /**
     * Sets the time in seconds after which the `PropertyTweener` will start interpolating. By default
     * there's no delay.
     *
     * Generated from Godot docs: PropertyTweener.set_delay
     */
    fun setDelay(delay: Double): PropertyTweener {
        checkOpen()
        return wrapOrThis(ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, segment, delay))
    }

    private fun wrapOrThis(value: MemorySegment): PropertyTweener =
        if (value.address() == 0L) {
            this
        } else if (value.address() == segment.address()) {
            releaseHandle(value)
            this
        } else {
            PropertyTweener(GodotHandle(value))
        }

    companion object {
        private const val VARIANT_RET_HASH = 4190193059L
        private const val NOARGS_RET_HASH = 4279177709L
        private const val SET_TRANS_HASH = 1899107404L
        private const val SET_EASE_HASH = 1080455622L
        private const val SET_CUSTOM_INTERPOLATOR_HASH = 3174170268L
        private const val SET_DELAY_HASH = 2171559331L

        private val fromBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "from", VARIANT_RET_HASH)
        }

        private val fromCurrentBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "from_current", NOARGS_RET_HASH)
        }

        private val asRelativeBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "as_relative", NOARGS_RET_HASH)
        }

        private val setTransBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_trans", SET_TRANS_HASH)
        }

        private val setEaseBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_ease", SET_EASE_HASH)
        }

        private val setCustomInterpolatorBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_custom_interpolator", SET_CUSTOM_INTERPOLATOR_HASH)
        }

        private val setDelayBind by lazy {
            ObjectCalls.getMethodBind("PropertyTweener", "set_delay", SET_DELAY_HASH)
        }

        internal fun wrap(handle: MemorySegment): PropertyTweener? =
            if (handle.address() == 0L) null else PropertyTweener(GodotHandle(handle))
    }
}

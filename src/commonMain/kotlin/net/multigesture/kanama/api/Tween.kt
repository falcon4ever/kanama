package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireGodotReturn
import net.multigesture.kanama.types.NodePath

/**
 * Lightweight object used for general-purpose animation via script, using `Tweener`s.
 *
 * Generated from Godot docs: Tween
 */
class Tween(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Creates and appends a `PropertyTweener`. This method tweens a `property` of an `object` between
     * an initial value and `final_val` in a span of time equal to `duration`, in seconds. The initial
     * value by default is the property's value at the time the tweening of the `PropertyTweener`
     * starts.
     *
     * Generated from Godot docs: Tween.tween_property
     */
    fun tweenProperty(target: GodotObject, property: NodePath, finalValue: Any?, duration: Double): PropertyTweener {
        checkOpen()
        return requireGodotReturn(PropertyTweener.wrapOwned(ObjectCalls.ptrcallWithObjectNodePathVariantDoubleArgsRetObject(tweenPropertyBind, segment, target.segment, property, finalValue, duration)), "Tween.tween_property")
    }

    /**
     * Creates and appends an `IntervalTweener`. This method can be used to create delays in the tween
     * animation, as an alternative to using the delay in other `Tweener`s, or when there's no
     * animation (in which case the `Tween` acts as a timer). `time` is the length of the interval, in
     * seconds.
     *
     * Generated from Godot docs: Tween.tween_interval
     */
    fun tweenInterval(time: Double): IntervalTweener {
        checkOpen()
        return requireGodotReturn(IntervalTweener.wrapOwned(ObjectCalls.ptrcallWithDoubleArgRetObject(tweenIntervalBind, segment, time)), "Tween.tween_interval")
    }

    /**
     * Creates and appends a `CallbackTweener`. This method can be used to call an arbitrary method in
     * any object. Use `Callable.bind` to bind additional arguments for the call.
     *
     * Generated from Godot docs: Tween.tween_callback
     */
    fun tweenCallback(callback: GodotCallable): CallbackTweener {
        checkOpen()
        return requireGodotReturn(CallbackTweener.wrapOwned(ObjectCalls.ptrcallWithCallableArgRetObject(tweenCallbackBind, segment, callback.target.segment, callback.method)), "Tween.tween_callback")
    }

    /**
     * Creates and appends a `MethodTweener`. This method is similar to a combination of
     * `tween_callback` and `tween_property`. It calls a method over time with a tweened value provided
     * as an argument. The value is tweened between `from` and `to` over the time specified by
     * `duration`, in seconds. Use `Callable.bind` to bind additional arguments for the call. You can
     * use `MethodTweener.set_ease` and `MethodTweener.set_trans` to tweak the easing and transition of
     * the value or `MethodTweener.set_delay` to delay the tweening.
     *
     * Generated from Godot docs: Tween.tween_method
     */
    fun tweenMethod(method: GodotCallable, from: Any?, to: Any?, duration: Double): MethodTweener {
        checkOpen()
        return requireGodotReturn(MethodTweener.wrapOwned(ObjectCalls.ptrcallWithCallableVariantVariantDoubleArgsRetObject(tweenMethodBind, segment, method.target.segment, method.method, from, to, duration)), "Tween.tween_method")
    }

    /**
     * Creates and appends a `SubtweenTweener`. This method can be used to nest `subtween` within this
     * `Tween`, allowing for the creation of more complex and composable sequences.
     *
     * Generated from Godot docs: Tween.tween_subtween
     */
    fun tweenSubtween(subtween: Tween): SubtweenTweener {
        checkOpen()
        return requireGodotReturn(SubtweenTweener.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(tweenSubtweenBind, segment, subtween.requireOpenHandle())), "Tween.tween_subtween")
    }

    /**
     * Processes the `Tween` by the given `delta` value, in seconds. This is mostly useful for manual
     * control when the `Tween` is paused. It can also be used to end the `Tween` animation
     * immediately, by setting `delta` longer than the whole duration of the `Tween` animation. Returns
     * `true` if the `Tween` still has `Tweener`s that haven't finished.
     *
     * Generated from Godot docs: Tween.custom_step
     */
    fun customStep(delta: Double): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithDoubleArgRetBool(customStepBind, segment, delta)
    }

    /**
     * Stops the tweening and resets the `Tween` to its initial state. This will not remove any
     * appended `Tweener`s. Note: This does not reset targets of `PropertyTweener`s to their values
     * when the `Tween` first started.
     *
     * Generated from Godot docs: Tween.stop
     */
    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(stopBind, segment)
    }

    /**
     * Pauses the tweening. The animation can be resumed by using `play`. Note: If a Tween is paused
     * and not bound to any node, it will exist indefinitely until manually started or invalidated. If
     * you lose a reference to such Tween, you can retrieve it using `SceneTree.get_processed_tweens`.
     *
     * Generated from Godot docs: Tween.pause
     */
    fun pause() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(pauseBind, segment)
    }

    /**
     * Resumes a paused or stopped `Tween`.
     *
     * Generated from Godot docs: Tween.play
     */
    fun play() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(playBind, segment)
    }

    /**
     * Aborts all tweening operations and invalidates the `Tween`.
     *
     * Generated from Godot docs: Tween.kill
     */
    fun kill() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(killBind, segment)
    }

    /**
     * Returns the total time in seconds the `Tween` has been animating (i.e. the time since it
     * started, not counting pauses etc.). The time is affected by `set_speed_scale`, and `stop` will
     * reset it to `0`. Note: As it results from accumulating frame deltas, the time returned after the
     * `Tween` has finished animating will be slightly greater than the actual `Tween` duration.
     *
     * Generated from Godot docs: Tween.get_total_elapsed_time
     */
    fun getTotalElapsedTime(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getTotalElapsedTimeBind, segment)
    }

    /**
     * Returns `true` if any `Tweener` has been added to the `Tween` and the `Tween` is valid. Useful
     * when tweeners are added dynamically and the tween can end up empty. Killing an empty tween
     * before it starts will prevent errors.
     *
     * Generated from Godot docs: Tween.has_tweeners
     */
    fun hasTweeners(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasTweenersBind, segment)
    }

    /**
     * Returns whether the `Tween` is currently running, i.e. it wasn't paused and it's not finished.
     *
     * Generated from Godot docs: Tween.is_running
     */
    fun isRunning(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isRunningBind, segment)
    }

    /**
     * Returns whether the `Tween` is valid. A valid `Tween` is a `Tween` contained by the scene tree
     * (i.e. the array from `SceneTree.get_processed_tweens` will contain this `Tween`). A `Tween`
     * might become invalid when it has finished tweening, is killed, or when created with
     * `Tween.new()`. Invalid `Tween`s can't have `Tweener`s appended.
     *
     * Generated from Godot docs: Tween.is_valid
     */
    fun isValid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isValidBind, segment)
    }

    /**
     * Binds this `Tween` with the given `node`. `Tween`s are processed directly by the `SceneTree`, so
     * they run independently of the animated nodes. When you bind a `Node` with the `Tween`, the
     * `Tween` will halt the animation when the object is not inside tree and the `Tween` will be
     * automatically killed when the bound object is freed. Also `TweenPauseMode.BOUND` will make the
     * pausing behavior dependent on the bound node. For a shorter way to create and bind a `Tween`,
     * you can use `Node.create_tween`.
     *
     * Generated from Godot docs: Tween.bind_node
     */
    fun bindNode(node: Node): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithObjectArgRetObject(bindNodeBind, segment, node.segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.bind_node")
    }

    /**
     * Determines whether the `Tween` should run after process frames (see `Node._process`) or physics
     * frames (see `Node._physics_process`). Default value is `TweenProcessMode.IDLE`.
     *
     * Generated from Godot docs: Tween.set_process_mode
     */
    fun setProcessMode(mode: Tween.TweenProcessMode): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setProcessModeBind, segment, mode.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_process_mode")
    }

    /**
     * Determines the behavior of the `Tween` when the `SceneTree` is paused. Default value is
     * `TweenPauseMode.BOUND`.
     *
     * Generated from Godot docs: Tween.set_pause_mode
     */
    fun setPauseMode(mode: Tween.TweenPauseMode): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setPauseModeBind, segment, mode.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_pause_mode")
    }

    /**
     * If `ignore` is `true`, the tween will ignore `Engine.time_scale` and update with the real,
     * elapsed time. This affects all `Tweener`s and their delays. Default value is `false`.
     *
     * Generated from Godot docs: Tween.set_ignore_time_scale
     */
    fun setIgnoreTimeScale(ignore: Boolean = true): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithBoolArgRetObject(setIgnoreTimeScaleBind, segment, ignore)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_ignore_time_scale")
    }

    /**
     * If `parallel` is `true`, the `Tweener`s appended after this method will by default run
     * simultaneously, as opposed to sequentially. Note: Just like with `parallel`, the tweener added
     * right before this method will also be part of the parallel step.
     *
     * Generated from Godot docs: Tween.set_parallel
     */
    fun setParallel(parallel: Boolean = true): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithBoolArgRetObject(setParallelBind, segment, parallel)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_parallel")
    }

    /**
     * Sets the number of times the tweening sequence will be repeated, i.e. `set_loops(2)` will run
     * the animation twice. Calling this method without arguments will make the `Tween` run infinitely,
     * until either it is killed with `kill`, the `Tween`'s bound node is freed, or all the animated
     * objects have been freed (which makes further animation impossible). Warning: Make sure to always
     * add some duration/delay when using infinite loops. To prevent the game freezing, 0-duration
     * looped animations (e.g. a single `CallbackTweener` with no delay) are stopped after a small
     * number of loops, which may produce unexpected results. If a `Tween`'s lifetime depends on some
     * node, always use `bind_node`.
     *
     * Generated from Godot docs: Tween.set_loops
     */
    fun setLoops(loops: Int = 0): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetObject(setLoopsBind, segment, loops)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_loops")
    }

    /**
     * Returns the number of remaining loops for this `Tween` (see `set_loops`). A return value of `-1`
     * indicates an infinitely looping `Tween`, and a return value of `0` indicates that the `Tween`
     * has already finished.
     *
     * Generated from Godot docs: Tween.get_loops_left
     */
    fun getLoopsLeft(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopsLeftBind, segment)
    }

    /**
     * Scales the speed of tweening. This affects all `Tweener`s and their delays.
     *
     * Generated from Godot docs: Tween.set_speed_scale
     */
    fun setSpeedScale(speed: Double): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setSpeedScaleBind, segment, speed)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_speed_scale")
    }

    /**
     * Sets the default transition type for `PropertyTweener`s and `MethodTweener`s appended after this
     * method. Before this method is called, the default transition type is `TransitionType.LINEAR`.
     *
     * Generated from Godot docs: Tween.set_trans
     */
    fun setTrans(trans: Tween.TransitionType): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setTransBind, segment, trans.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_trans")
    }

    /**
     * Sets the default ease type for `PropertyTweener`s and `MethodTweener`s appended after this
     * method. Before this method is called, the default ease type is `EaseType.IN_OUT`.
     *
     * Generated from Godot docs: Tween.set_ease
     */
    fun setEase(ease: Tween.EaseType): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, segment, ease.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.set_ease")
    }

    /**
     * Makes the next `Tweener` run parallelly to the previous one.
     *
     * Generated from Godot docs: Tween.parallel
     */
    fun parallel(): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(parallelBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.parallel")
    }

    /**
     * Used to chain two `Tweener`s after `set_parallel` is called with `true`.
     *
     * Generated from Godot docs: Tween.chain
     */
    fun chain(): Tween {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(chainBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return requireGodotReturn(Tween.wrapOwned(ret), "Tween.chain")
    }

    // ── Kanama Tween ergonomics (generator custom-section, not from Godot docs) ───────────────
    // GDScript writes `tween_property(node, "position", v, 1.0)` and `tween_callback(node.method)`;
    // these overloads keep that shape. They were the hand-written desktop Tween's signatures and
    // a subset of the hand-written iOS one until task 129 A generated Tween once: a String property
    // path ("position", "modulate:a"), and a target object plus method name for the Callable
    // arguments. No KDoc here: the names are Godot methods, so sync_kdoc_from_godot_docs.py owns
    // their doc blocks.

    /**
     * Creates and appends a `PropertyTweener`. This method tweens a `property` of an `object` between
     * an initial value and `final_val` in a span of time equal to `duration`, in seconds. The initial
     * value by default is the property's value at the time the tweening of the `PropertyTweener`
     * starts.
     *
     * Generated from Godot docs: Tween.tween_property
     */
    fun tweenProperty(target: GodotObject, property: String, finalValue: Any?, duration: Double): PropertyTweener =
        this.tweenProperty(target, NodePath(property), finalValue, duration)

    /**
     * Creates and appends a `CallbackTweener`. This method can be used to call an arbitrary method in
     * any object. Use `Callable.bind` to bind additional arguments for the call.
     *
     * Generated from Godot docs: Tween.tween_callback
     */
    fun tweenCallback(target: GodotObject, method: String): CallbackTweener =
        this.tweenCallback(GodotCallable(target, method))

    /**
     * Creates and appends a `MethodTweener`. This method is similar to a combination of
     * `tween_callback` and `tween_property`. It calls a method over time with a tweened value provided
     * as an argument. The value is tweened between `from` and `to` over the time specified by
     * `duration`, in seconds. Use `Callable.bind` to bind additional arguments for the call. You can
     * use `MethodTweener.set_ease` and `MethodTweener.set_trans` to tweak the easing and transition of
     * the value or `MethodTweener.set_delay` to delay the tweening.
     *
     * Generated from Godot docs: Tween.tween_method
     */
    fun tweenMethod(target: GodotObject, method: String, from: Any?, to: Any?, duration: Double): MethodTweener =
        this.tweenMethod(GodotCallable(target, method), from, to, duration)

    /** Signal `step_finished(idx: int)`; see [TypedSignal]. */
    val stepFinished: Signal1<Long>
        @JvmName("stepFinishedTypedSignal")
        get() = Signal1(this, "step_finished", SignalArgType.LONG)

    /** Signal `loop_finished(loop_count: int)`; see [TypedSignal]. */
    val loopFinished: Signal1<Long>
        @JvmName("loopFinishedTypedSignal")
        get() = Signal1(this, "loop_finished", SignalArgType.LONG)

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val stepFinished: String = "step_finished"
        const val loopFinished: String = "loop_finished"
        const val finished: String = "finished"
    }

    /**
     * Godot's `Tween.TweenProcessMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Tween.TweenProcessMode.<NAME>`).
     *
     * Generated from Godot docs: Tween.TweenProcessMode
     */
    @JvmInline
    value class TweenProcessMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `Tween` updates after each physics frame (see `Node._physics_process`).
             *
             * Generated from Godot docs: Tween.TWEEN_PROCESS_PHYSICS
             */
            val PHYSICS: TweenProcessMode get() = TweenProcessMode(0L)
            /**
             * The `Tween` updates after each process frame (see `Node._process`).
             *
             * Generated from Godot docs: Tween.TWEEN_PROCESS_IDLE
             */
            val IDLE: TweenProcessMode get() = TweenProcessMode(1L)
        }
    }

    /**
     * Godot's `Tween.TweenPauseMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Tween.TweenPauseMode.<NAME>`).
     *
     * Generated from Godot docs: Tween.TweenPauseMode
     */
    @JvmInline
    value class TweenPauseMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If the `Tween` has a bound node, it will process when that node can process (see
             * `Node.process_mode`). Otherwise it's the same as `TweenPauseMode.STOP`.
             *
             * Generated from Godot docs: Tween.TWEEN_PAUSE_BOUND
             */
            val BOUND: TweenPauseMode get() = TweenPauseMode(0L)
            /**
             * If `SceneTree` is paused, the `Tween` will also pause.
             *
             * Generated from Godot docs: Tween.TWEEN_PAUSE_STOP
             */
            val STOP: TweenPauseMode get() = TweenPauseMode(1L)
            /**
             * The `Tween` will process regardless of whether `SceneTree` is paused.
             *
             * Generated from Godot docs: Tween.TWEEN_PAUSE_PROCESS
             */
            val PROCESS: TweenPauseMode get() = TweenPauseMode(2L)
        }
    }

    /**
     * Godot's `Tween.TransitionType` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Tween.TransitionType.<NAME>`).
     *
     * Generated from Godot docs: Tween.TransitionType
     */
    @JvmInline
    value class TransitionType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The animation is interpolated linearly.
             *
             * Generated from Godot docs: Tween.TRANS_LINEAR
             */
            val LINEAR: TransitionType get() = TransitionType(0L)
            /**
             * The animation is interpolated using a sine function.
             *
             * Generated from Godot docs: Tween.TRANS_SINE
             */
            val SINE: TransitionType get() = TransitionType(1L)
            /**
             * The animation is interpolated with a quintic (to the power of 5) function.
             *
             * Generated from Godot docs: Tween.TRANS_QUINT
             */
            val QUINT: TransitionType get() = TransitionType(2L)
            /**
             * The animation is interpolated with a quartic (to the power of 4) function.
             *
             * Generated from Godot docs: Tween.TRANS_QUART
             */
            val QUART: TransitionType get() = TransitionType(3L)
            /**
             * The animation is interpolated with a quadratic (to the power of 2) function.
             *
             * Generated from Godot docs: Tween.TRANS_QUAD
             */
            val QUAD: TransitionType get() = TransitionType(4L)
            /**
             * The animation is interpolated with an exponential (to the power of x) function.
             *
             * Generated from Godot docs: Tween.TRANS_EXPO
             */
            val EXPO: TransitionType get() = TransitionType(5L)
            /**
             * The animation is interpolated with elasticity, wiggling around the edges.
             *
             * Generated from Godot docs: Tween.TRANS_ELASTIC
             */
            val ELASTIC: TransitionType get() = TransitionType(6L)
            /**
             * The animation is interpolated with a cubic (to the power of 3) function.
             *
             * Generated from Godot docs: Tween.TRANS_CUBIC
             */
            val CUBIC: TransitionType get() = TransitionType(7L)
            /**
             * The animation is interpolated with a function using square roots.
             *
             * Generated from Godot docs: Tween.TRANS_CIRC
             */
            val CIRC: TransitionType get() = TransitionType(8L)
            /**
             * The animation is interpolated by bouncing at the end.
             *
             * Generated from Godot docs: Tween.TRANS_BOUNCE
             */
            val BOUNCE: TransitionType get() = TransitionType(9L)
            /**
             * The animation is interpolated backing out at ends.
             *
             * Generated from Godot docs: Tween.TRANS_BACK
             */
            val BACK: TransitionType get() = TransitionType(10L)
            /**
             * The animation is interpolated like a spring towards the end.
             *
             * Generated from Godot docs: Tween.TRANS_SPRING
             */
            val SPRING: TransitionType get() = TransitionType(11L)
        }
    }

    /**
     * Godot's `Tween.EaseType` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Tween.EaseType.<NAME>`).
     *
     * Generated from Godot docs: Tween.EaseType
     */
    @JvmInline
    value class EaseType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The interpolation starts slowly and speeds up towards the end.
             *
             * Generated from Godot docs: Tween.EASE_IN
             */
            val IN: EaseType get() = EaseType(0L)
            /**
             * The interpolation starts quickly and slows down towards the end.
             *
             * Generated from Godot docs: Tween.EASE_OUT
             */
            val OUT: EaseType get() = EaseType(1L)
            /**
             * A combination of `EaseType.IN` and `EaseType.OUT`. The interpolation is slowest at both ends.
             *
             * Generated from Godot docs: Tween.EASE_IN_OUT
             */
            val IN_OUT: EaseType get() = EaseType(2L)
            /**
             * A combination of `EaseType.IN` and `EaseType.OUT`. The interpolation is fastest at both ends.
             *
             * Generated from Godot docs: Tween.EASE_OUT_IN
             */
            val OUT_IN: EaseType get() = EaseType(3L)
        }
    }

    companion object {
        /**
         * This method can be used for manual interpolation of a value, when you don't want `Tween` to do
         * animating for you. It's similar to `@GlobalScope.lerp`, but with support for custom transition
         * and easing. `initial_value` is the starting value of the interpolation. `delta_value` is the
         * change of the value in the interpolation, i.e. it's equal to `final_value - initial_value`.
         * `elapsed_time` is the time in seconds that passed after the interpolation started and it's used
         * to control the position of the interpolation. E.g. when it's equal to half of the `duration`,
         * the interpolated value will be halfway between initial and final values. This value can also be
         * greater than `duration` or lower than 0, which will extrapolate the value. `duration` is the
         * total time of the interpolation. Note: If `duration` is equal to `0`, the method will always
         * return the final value, regardless of `elapsed_time` provided.
         *
         * Generated from Godot docs: Tween.interpolate_value
         */
        fun interpolateValue(initialValue: Any?, deltaValue: Any?, elapsedTime: Double, duration: Double, transType: Tween.TransitionType, easeType: Tween.EaseType): Any? {
            return ObjectCalls.ptrcallWithTwoVariantTwoDoubleTwoLongArgsRetVariantScalar(interpolateValueBind, NULL_SEGMENT, initialValue, deltaValue, elapsedTime, duration, transType.value, easeType.value)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Tween? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Tween? =
            if (handle.address() == 0L) null else RefCounted.owned(Tween(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Tween? =
            if (handle.address() == 0L) null else Tween(GodotHandle(handle))

        private const val TWEEN_PROPERTY_HASH = 4049770449L
        private val tweenPropertyBind by lazy {
            ObjectCalls.getMethodBind("Tween", "tween_property", TWEEN_PROPERTY_HASH)
        }

        private const val TWEEN_INTERVAL_HASH = 413360199L
        private val tweenIntervalBind by lazy {
            ObjectCalls.getMethodBind("Tween", "tween_interval", TWEEN_INTERVAL_HASH)
        }

        private const val TWEEN_CALLBACK_HASH = 1540176488L
        private val tweenCallbackBind by lazy {
            ObjectCalls.getMethodBind("Tween", "tween_callback", TWEEN_CALLBACK_HASH)
        }

        private const val TWEEN_METHOD_HASH = 2337877153L
        private val tweenMethodBind by lazy {
            ObjectCalls.getMethodBind("Tween", "tween_method", TWEEN_METHOD_HASH)
        }

        private const val TWEEN_SUBTWEEN_HASH = 1567358477L
        private val tweenSubtweenBind by lazy {
            ObjectCalls.getMethodBind("Tween", "tween_subtween", TWEEN_SUBTWEEN_HASH)
        }

        private const val CUSTOM_STEP_HASH = 330693286L
        private val customStepBind by lazy {
            ObjectCalls.getMethodBind("Tween", "custom_step", CUSTOM_STEP_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("Tween", "stop", STOP_HASH)
        }

        private const val PAUSE_HASH = 3218959716L
        private val pauseBind by lazy {
            ObjectCalls.getMethodBind("Tween", "pause", PAUSE_HASH)
        }

        private const val PLAY_HASH = 3218959716L
        private val playBind by lazy {
            ObjectCalls.getMethodBind("Tween", "play", PLAY_HASH)
        }

        private const val KILL_HASH = 3218959716L
        private val killBind by lazy {
            ObjectCalls.getMethodBind("Tween", "kill", KILL_HASH)
        }

        private const val GET_TOTAL_ELAPSED_TIME_HASH = 1740695150L
        private val getTotalElapsedTimeBind by lazy {
            ObjectCalls.getMethodBind("Tween", "get_total_elapsed_time", GET_TOTAL_ELAPSED_TIME_HASH)
        }

        private const val HAS_TWEENERS_HASH = 36873697L
        private val hasTweenersBind by lazy {
            ObjectCalls.getMethodBind("Tween", "has_tweeners", HAS_TWEENERS_HASH)
        }

        private const val IS_RUNNING_HASH = 2240911060L
        private val isRunningBind by lazy {
            ObjectCalls.getMethodBind("Tween", "is_running", IS_RUNNING_HASH)
        }

        private const val IS_VALID_HASH = 2240911060L
        private val isValidBind by lazy {
            ObjectCalls.getMethodBind("Tween", "is_valid", IS_VALID_HASH)
        }

        private const val BIND_NODE_HASH = 2946786331L
        private val bindNodeBind by lazy {
            ObjectCalls.getMethodBind("Tween", "bind_node", BIND_NODE_HASH)
        }

        private const val SET_PROCESS_MODE_HASH = 855258840L
        private val setProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_process_mode", SET_PROCESS_MODE_HASH)
        }

        private const val SET_PAUSE_MODE_HASH = 3363368837L
        private val setPauseModeBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_pause_mode", SET_PAUSE_MODE_HASH)
        }

        private const val SET_IGNORE_TIME_SCALE_HASH = 1942052223L
        private val setIgnoreTimeScaleBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_ignore_time_scale", SET_IGNORE_TIME_SCALE_HASH)
        }

        private const val SET_PARALLEL_HASH = 1942052223L
        private val setParallelBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_parallel", SET_PARALLEL_HASH)
        }

        private const val SET_LOOPS_HASH = 2670836414L
        private val setLoopsBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_loops", SET_LOOPS_HASH)
        }

        private const val GET_LOOPS_LEFT_HASH = 3905245786L
        private val getLoopsLeftBind by lazy {
            ObjectCalls.getMethodBind("Tween", "get_loops_left", GET_LOOPS_LEFT_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 3961971106L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val SET_TRANS_HASH = 3965963875L
        private val setTransBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_trans", SET_TRANS_HASH)
        }

        private const val SET_EASE_HASH = 1208117252L
        private val setEaseBind by lazy {
            ObjectCalls.getMethodBind("Tween", "set_ease", SET_EASE_HASH)
        }

        private const val PARALLEL_HASH = 3426978995L
        private val parallelBind by lazy {
            ObjectCalls.getMethodBind("Tween", "parallel", PARALLEL_HASH)
        }

        private const val CHAIN_HASH = 3426978995L
        private val chainBind by lazy {
            ObjectCalls.getMethodBind("Tween", "chain", CHAIN_HASH)
        }

        private const val INTERPOLATE_VALUE_HASH = 3452526450L
        private val interpolateValueBind by lazy {
            ObjectCalls.getMethodBind("Tween", "interpolate_value", INTERPOLATE_VALUE_HASH)
        }
    }
}

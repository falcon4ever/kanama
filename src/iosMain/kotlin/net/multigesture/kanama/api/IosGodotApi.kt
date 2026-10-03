@file:Suppress("unused")

package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.RawSegment
import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.* // generated ObjectCalls.* extension helpers
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.binding.runtime.IosScriptErrors
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_hide
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_get_local_mouse_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_get_viewport_rect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_set_modulate
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_show
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_collision_shape3d_set_disabled
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles2d_restart
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles2d_set_emitting
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles2d_set_lifetime
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles3d_restart
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles3d_set_emitting
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_input_event_is_pressed
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_input_event_is_released
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_input_event_mouse_button_get_button_index
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_create_tween
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_get_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_get_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_set_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_set_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_global_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_rotation
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_rotate_y
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_global_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_rotation
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_add_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_child_count
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_node_or_null
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_tree
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_viewport
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_is_in_group
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_remove_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_set_process_input
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_play
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_bus
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_pitch_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_stream
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_stream_paused
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_volume_db
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_construct_object
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_set_process_unhandled_input
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_disconnect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_disconnect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_int
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_vector2i
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_is_instance_id_valid
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_get_instance_id
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_is_class
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_queue_free
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_packed_scene_instantiate
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_resource_loader_load
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_sprite2d_set_texture
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_kill
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_set_parallel
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_tween_callback
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_tween_method
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_viewport_get_visible_rect
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import kotlin.coroutines.CoroutineContext
import kotlin.math.PI
import kotlin.math.pow
import kotlin.random.Random

/**
 * Deprecated, no longer applied to any Kanama API (task 97; the generated iOS `RefCounted.close()`
 * dropped it in task 103). Kept so an existing `@OptIn(ManualGodotLifetimeApi::class)` still
 * compiles; delete the opt-in, nothing replaces it.
 */
@Deprecated("No longer required; close() is the documented contract (docs/game-dev/godot-api.md#resource-ownership)")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ManualGodotLifetimeApi

abstract class KanamaScript<Self : Any>(
    val godotObject: GodotHandle,
    selfFactory: (GodotHandle) -> Self,
) {
    val self: Self = selfFactory(godotObject)

    inline fun <T> selfAs(ctor: (GodotHandle) -> T): T = ctor(godotObject)
}

// KANAMA-IOS-HANDWRITTEN: [platform] KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json.
class KanamaScope : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    fun cancel() {
        job.cancel()
    }
}

interface KanamaCoroutineOwner {
    val kanamaScope: KanamaScope
}

class AudioStreamPlayer(handle: GodotHandle) : Node(handle) {
    // ===== BEGIN GENERATED ENUMS: AudioStreamPlayer (scripts/generate_api_wrapper.py — do not edit) =====
    /**
     * Godot's `AudioStreamPlayer.MixTarget` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AudioStreamPlayer.MixTarget.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamPlayer.MixTarget
     */
    value class MixTarget(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The audio will be played only on the first channel. This is the default.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_STEREO
             */
            val STEREO: MixTarget get() = MixTarget(0L)
            /**
             * The audio will be played on all surround channels.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_SURROUND
             */
            val SURROUND: MixTarget get() = MixTarget(1L)
            /**
             * The audio will be played on the second channel, which is usually the center.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_CENTER
             */
            val CENTER: MixTarget get() = MixTarget(2L)
        }
    }
    // ===== END GENERATED ENUMS: AudioStreamPlayer =====

    fun setStreamFromPath(path: String) {
        ResourceLoader.loadAudioStream(path)?.use { stream ->
            setStream(stream)
        }
    }

    // AudioStreamPlayer.set_stream(stream) — null clears the assigned stream. Mirrors the
    // generated 2D/3D variants; routed through the existing cinterop glue (0 == null).
    fun setStream(stream: AudioStream?) {
        IosGodot.audioStreamPlayerSetStream(segment.address(), stream?.segment?.address() ?: 0L)
    }

    fun setPitchScale(value: Double) {
        IosGodot.audioStreamPlayerSetPitchScale(segment.address(), value)
    }

    fun setVolumeDb(value: Double) {
        IosGodot.audioStreamPlayerSetVolumeDb(segment.address(), value)
    }

    fun setBus(value: String) {
        IosGodot.audioStreamPlayerSetBus(segment.address(), value)
    }

    fun setStreamPaused(value: Boolean) {
        IosGodot.audioStreamPlayerSetStreamPaused(segment.address(), value)
    }

    fun play() {
        IosGodot.audioStreamPlayerPlay(segment.address(), 0.0)
    }

    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, segment)
    }

    companion object {
        private val stopBind by lazy { ObjectCalls.getMethodBind("AudioStreamPlayer", "stop", 3218959716L) }

        fun create(): AudioStreamPlayer =
            AudioStreamPlayer(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("AudioStreamPlayer"))))
    }
}

// KANAMA-IOS-HANDWRITTEN: [runtime] Tween uses the Variant tween_property path (final-value is a
// Variant), not generatable via the audited ptrcall set. Bespoke by design. Its Tweener return
// types are the generated shared classes (task 117 P2'): Tweener, PropertyTweener, MethodTweener
// and CallbackTweener are one generated class each now, carrying their own fluent
// setTrans/setEase/setDelay/from with the generator's self-return collapse.
actual class Tween(handle: GodotHandle) : RefCounted(handle) {
    // ===== BEGIN GENERATED ENUMS: Tween (scripts/generate_api_wrapper.py — do not edit) =====
    /**
     * Godot's `Tween.TweenProcessMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Tween.TweenProcessMode.<NAME>`).
     *
     * Generated from Godot docs: Tween.TweenProcessMode
     */
    actual value class TweenProcessMode
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual companion object {
            /**
             * The `Tween` updates after each physics frame (see `Node._physics_process`).
             *
             * Generated from Godot docs: Tween.TWEEN_PROCESS_PHYSICS
             */
            actual val PHYSICS: TweenProcessMode get() = TweenProcessMode(0L)
            /**
             * The `Tween` updates after each process frame (see `Node._process`).
             *
             * Generated from Godot docs: Tween.TWEEN_PROCESS_IDLE
             */
            actual val IDLE: TweenProcessMode get() = TweenProcessMode(1L)
        }
    }

    /**
     * Godot's `Tween.TweenPauseMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Tween.TweenPauseMode.<NAME>`).
     *
     * Generated from Godot docs: Tween.TweenPauseMode
     */
    actual value class TweenPauseMode
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual companion object {
            /**
             * If the `Tween` has a bound node, it will process when that node can process (see
             * `Node.process_mode`). Otherwise it's the same as `TweenPauseMode.STOP`.
             *
             * Generated from Godot docs: Tween.TWEEN_PAUSE_BOUND
             */
            actual val BOUND: TweenPauseMode get() = TweenPauseMode(0L)
            /**
             * If `SceneTree` is paused, the `Tween` will also pause.
             *
             * Generated from Godot docs: Tween.TWEEN_PAUSE_STOP
             */
            actual val STOP: TweenPauseMode get() = TweenPauseMode(1L)
            /**
             * The `Tween` will process regardless of whether `SceneTree` is paused.
             *
             * Generated from Godot docs: Tween.TWEEN_PAUSE_PROCESS
             */
            actual val PROCESS: TweenPauseMode get() = TweenPauseMode(2L)
        }
    }

    /**
     * Godot's `Tween.TransitionType` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Tween.TransitionType.<NAME>`).
     *
     * Generated from Godot docs: Tween.TransitionType
     */
    actual value class TransitionType
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual companion object {
            /**
             * The animation is interpolated linearly.
             *
             * Generated from Godot docs: Tween.TRANS_LINEAR
             */
            actual val LINEAR: TransitionType get() = TransitionType(0L)
            /**
             * The animation is interpolated using a sine function.
             *
             * Generated from Godot docs: Tween.TRANS_SINE
             */
            actual val SINE: TransitionType get() = TransitionType(1L)
            /**
             * The animation is interpolated with a quintic (to the power of 5) function.
             *
             * Generated from Godot docs: Tween.TRANS_QUINT
             */
            actual val QUINT: TransitionType get() = TransitionType(2L)
            /**
             * The animation is interpolated with a quartic (to the power of 4) function.
             *
             * Generated from Godot docs: Tween.TRANS_QUART
             */
            actual val QUART: TransitionType get() = TransitionType(3L)
            /**
             * The animation is interpolated with a quadratic (to the power of 2) function.
             *
             * Generated from Godot docs: Tween.TRANS_QUAD
             */
            actual val QUAD: TransitionType get() = TransitionType(4L)
            /**
             * The animation is interpolated with an exponential (to the power of x) function.
             *
             * Generated from Godot docs: Tween.TRANS_EXPO
             */
            actual val EXPO: TransitionType get() = TransitionType(5L)
            /**
             * The animation is interpolated with elasticity, wiggling around the edges.
             *
             * Generated from Godot docs: Tween.TRANS_ELASTIC
             */
            actual val ELASTIC: TransitionType get() = TransitionType(6L)
            /**
             * The animation is interpolated with a cubic (to the power of 3) function.
             *
             * Generated from Godot docs: Tween.TRANS_CUBIC
             */
            actual val CUBIC: TransitionType get() = TransitionType(7L)
            /**
             * The animation is interpolated with a function using square roots.
             *
             * Generated from Godot docs: Tween.TRANS_CIRC
             */
            actual val CIRC: TransitionType get() = TransitionType(8L)
            /**
             * The animation is interpolated by bouncing at the end.
             *
             * Generated from Godot docs: Tween.TRANS_BOUNCE
             */
            actual val BOUNCE: TransitionType get() = TransitionType(9L)
            /**
             * The animation is interpolated backing out at ends.
             *
             * Generated from Godot docs: Tween.TRANS_BACK
             */
            actual val BACK: TransitionType get() = TransitionType(10L)
            /**
             * The animation is interpolated like a spring towards the end.
             *
             * Generated from Godot docs: Tween.TRANS_SPRING
             */
            actual val SPRING: TransitionType get() = TransitionType(11L)
        }
    }

    /**
     * Godot's `Tween.EaseType` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Tween.EaseType.<NAME>`).
     *
     * Generated from Godot docs: Tween.EaseType
     */
    actual value class EaseType
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual companion object {
            /**
             * The interpolation starts slowly and speeds up towards the end.
             *
             * Generated from Godot docs: Tween.EASE_IN
             */
            actual val IN: EaseType get() = EaseType(0L)
            /**
             * The interpolation starts quickly and slows down towards the end.
             *
             * Generated from Godot docs: Tween.EASE_OUT
             */
            actual val OUT: EaseType get() = EaseType(1L)
            /**
             * A combination of `EaseType.IN` and `EaseType.OUT`. The interpolation is slowest at both ends.
             *
             * Generated from Godot docs: Tween.EASE_IN_OUT
             */
            actual val IN_OUT: EaseType get() = EaseType(2L)
            /**
             * A combination of `EaseType.IN` and `EaseType.OUT`. The interpolation is fastest at both ends.
             *
             * Generated from Godot docs: Tween.EASE_OUT_IN
             */
            actual val OUT_IN: EaseType get() = EaseType(3L)
        }
    }
    // ===== END GENERATED ENUMS: Tween =====

    fun setParallel(parallel: Boolean): Tween {
        releaseIosFluentSelf(
            segment,
            IosGodot.tweenSetParallel(segment.address(), if (parallel) 1 else 0),
            "Tween.set_parallel",
        )
        return this
    }

    // Tween.bind_node(node) — ptrcall (object arg, returns self). Mirrors desktop Tween.bindNode.
    fun bindNode(node: Node): Tween {
        releaseIosFluentSelf(
            segment,
            ObjectCalls.ptrcallWithObjectArgRetObject(bindNodeBind, segment, node.segment).address(),
            "Tween.bind_node",
        )
        return this
    }

    // Tween.set_ease(ease) — ptrcall (int arg, returns self). Tween-level default ease (distinct
    // from Tweener.setEase, which configures an individual tweener).
    fun setEase(ease: Tween.EaseType): Tween {
        releaseIosFluentSelf(
            segment,
            ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, segment, ease.value).address(),
            "Tween.set_ease",
        )
        return this
    }

    // Tween.tween_callback(Callable(target, method)). Routed through the C shim (Callable arg).
    fun tweenCallback(target: GodotObject, method: String): CallbackTweener =
        requireGodotReturn(
            IosGodot.tweenTweenCallback(segment.address(), target.segment.address(), method)
                .takeIf { it != 0L }
                ?.let { CallbackTweener(GodotHandle(MemorySegment.ofAddress(it))) },
            "Tween.tween_callback",
        )

    // Tween.tween_method(Callable(target, method), from, to, duration) — animates [from]->[to] over
    // [duration], calling target.method(value) each frame. Callable arg → routed through the C shim.
    fun tweenMethod(target: GodotObject, method: String, from: Double, to: Double, duration: Double): MethodTweener =
        requireGodotReturn(
            IosGodot.tweenTweenMethod(segment.address(), target.segment.address(), method, from, to, duration)
                .takeIf { it != 0L }
                ?.let { MethodTweener(GodotHandle(MemorySegment.ofAddress(it))) },
            "Tween.tween_method",
        )

    // Tween.tween_property through the general Variant encoder (task 128 A review): any
    // Variant-expressible final value (Double, Long, Vector2/3, Color, a typed GodotEnumValue, ...),
    // as on desktop. The old Vector2 / Color C-shim pair tweened every other value to Vector2(0, 0).
    fun tweenProperty(target: GodotObject, property: String, finalValue: Any?, duration: Double): PropertyTweener =
        requireGodotReturn(
            ObjectCalls.ptrcallWithObjectNodePathVariantDoubleArgsRetObject(
                tweenPropertyBind,
                segment,
                target.segment,
                property,
                finalValue,
                duration,
            ).takeIf { it.address() != 0L }?.let { PropertyTweener(GodotHandle(it)) },
            "Tween.tween_property",
        )

    fun kill() {
        IosGodot.tweenKill(segment.address())
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {

        private val bindNodeBind by lazy { ObjectCalls.getMethodBind("Tween", "bind_node", 2946786331L) }
        private val setEaseBind by lazy { ObjectCalls.getMethodBind("Tween", "set_ease", 1208117252L) }
        private val tweenPropertyBind by lazy { ObjectCalls.getMethodBind("Tween", "tween_property", 4049770449L) }
    }
}

// `meta: "required"` (task 128 A): a null fluent return throws instead of being ignored.
private fun releaseIosFluentSelf(receiver: MemorySegment, returned: Long, godotMethod: String) {
    if (returned == 0L) requireGodotReturn<Any>(null, godotMethod)
    check(returned == receiver.address()) {
        "Godot fluent RefCounted call returned a different object"
    }
    RefCounted.releaseHandle(MemorySegment.ofAddress(returned))
}

// An InputEvent (task 128 C), so `InputMap.actionAddEvent(action, InputEventMouseButton.create())` is the
// same call on every backend. `from` also wraps an InputEventScreenTouch (touch drives the mouse-button
// paths on iOS); that is still a valid InputEvent, so the inherited InputEvent members (isPressed,
// isReleased, ...) are correct for both, while the button members check the real class.
class InputEventMouseButton(handle: GodotHandle) : InputEvent(handle) {
    var buttonIndex: MouseButton
        get() = getButtonIndex()
        set(value) = setButtonIndex(value)

    fun getButtonIndex(): MouseButton =
        MouseButton(if (isClass("InputEventMouseButton")) IosGodot.inputEventMouseButtonGetButtonIndex(segment.address())
        else MouseButton.LEFT.value)

    fun setButtonIndex(buttonIndex: MouseButton) {
        checkOpen()
        check(isClass("InputEventMouseButton")) { "setButtonIndex on a touch event wrapped as InputEventMouseButton" }
        ObjectCalls.ptrcallWithLongArg(setButtonIndexBind, segment, buttonIndex.value)
    }

    companion object {

        fun from(value: GodotObject): InputEventMouseButton? =
            if (value.isClass("InputEventMouseButton")) InputEventMouseButton(value.handle)
            else if (value.isClass("InputEventScreenTouch")) InputEventMouseButton(value.handle)
            else null

        // Instantiate an InputEventMouseButton (owned: close() it, or `use { }`).
        fun create(): InputEventMouseButton =
            InputEventMouseButton(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("InputEventMouseButton"))))

        private val setButtonIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseButton", "set_button_index", 3624991109L)
        }
    }
}

// Input is now a generated iOS wrapper (api/Input.kt, `object Input`), matching desktop's
// generated Input. The previous hand-written stub (getAxis/isActionJustPressed/setCustomMouseCursor)
// is retired; the generated object is a superset. setCustomMouseCursor remains guardrail-skipped on
// iOS (Variant Object arg) and was unused.

// KANAMA-IOS-HANDWRITTEN: [platform] pure-Kotlin math helpers (no Godot call). Bespoke utility,
// matching the desktop Mathf facade (which is hand-authored, not generated).
object Mathf {
    const val PI: Double = kotlin.math.PI
    const val TAU: Double = kotlin.math.PI * 2.0

    fun abs(value: Double): Double = kotlin.math.abs(value)

    fun abs(value: Float): Float = kotlin.math.abs(value)

    fun min(a: Double, b: Double): Double = kotlin.math.min(a, b)

    fun max(a: Double, b: Double): Double = kotlin.math.max(a, b)

    fun min(a: Long, b: Long): Long = kotlin.math.min(a, b)

    fun max(a: Long, b: Long): Long = kotlin.math.max(a, b)

    fun cos(value: Double): Double = kotlin.math.cos(value)

    fun sin(value: Double): Double = kotlin.math.sin(value)

    fun sqrt(value: Double): Double = kotlin.math.sqrt(value)

    fun log(value: Double): Double = kotlin.math.ln(value)

    // Godot's @GlobalScope.inverse_lerp: the weight that lerp(from, to, w) == value.
    fun inverseLerp(from: Double, to: Double, value: Double): Double =
        if (to == from) 0.0 else (value - from) / (to - from)

    fun lerp(from: Double, to: Double, weight: Double): Double =
        from + (to - from) * weight

    // Godot's @GlobalScope.atan2 / pow — pure math, matching the desktop Mathf helpers.
    fun atan2(y: Double, x: Double): Double = kotlin.math.atan2(y, x)

    fun pow(x: Double, y: Double): Double = x.pow(y)

    // Godot's @GlobalScope.move_toward: step [from] toward [to] by at most [delta].
    fun moveToward(from: Double, to: Double, delta: Double): Double =
        if (kotlin.math.abs(to - from) <= delta) to
        else from + (if (to > from) 1.0 else -1.0) * delta

    // Godot's @GlobalScope.is_equal_approx (CMP_EPSILON fuzzy compare), via the shared iOS helper.
    fun isEqualApprox(a: Double, b: Double): Boolean =
        if (a == b) {
            true
        } else {
            val epsilon = 0.00001
            val tolerance = kotlin.math.max(epsilon * kotlin.math.abs(a), epsilon)
            kotlin.math.abs(a - b) < tolerance
        }

    fun lerpAngle(from: Double, to: Double, weight: Double): Double {
        val difference = ((to - from + PI) % (PI * 2.0)) - PI
        return from + difference * weight
    }

    fun clamp(value: Double, min: Double, max: Double): Double =
        value.coerceIn(min, max)

    // Godot's @GlobalScope.roundi (half away from zero, matching the desktop
    // Mathf.roundToInt -> GD.roundi path; kotlin.math.round ties-to-even would diverge on .5).
    fun roundToInt(value: Double): Long =
        if (value >= 0.0) kotlin.math.floor(value + 0.5).toLong()
        else kotlin.math.ceil(value - 0.5).toLong()
}

// KANAMA-IOS-HANDWRITTEN: [glue] ResourceLoader singleton. Not retired to the generated wrapper:
// the generated ResourceLoader.load() returns a bare Resource, but demos call the bespoke typed
// loaders (loadTexture2D/loadAudioStream/loadPackedScene), which pass a type_hint through the C
// shim and wrap the result to the concrete type. The generator emits no typed-load sugar, so this
// stays bespoke.
object ResourceLoader {
    // ===== BEGIN GENERATED ENUMS: ResourceLoader (scripts/generate_api_wrapper.py — do not edit) =====
    /**
     * Godot's `ResourceLoader.ThreadLoadStatus` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`ResourceLoader.ThreadLoadStatus.<NAME>`).
     *
     * Generated from Godot docs: ResourceLoader.ThreadLoadStatus
     */
    value class ThreadLoadStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The resource is invalid, or has not been loaded with `load_threaded_request`.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_INVALID_RESOURCE
             */
            val INVALID_RESOURCE: ThreadLoadStatus get() = ThreadLoadStatus(0L)
            /**
             * The resource is still being loaded.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_IN_PROGRESS
             */
            val IN_PROGRESS: ThreadLoadStatus get() = ThreadLoadStatus(1L)
            /**
             * Some error occurred during loading and it failed.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_FAILED
             */
            val FAILED: ThreadLoadStatus get() = ThreadLoadStatus(2L)
            /**
             * The resource was loaded successfully and can be accessed via `load_threaded_get`.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_LOADED
             */
            val LOADED: ThreadLoadStatus get() = ThreadLoadStatus(3L)
        }
    }

    /**
     * Godot's `ResourceLoader.CacheMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`ResourceLoader.CacheMode.<NAME>`).
     *
     * Generated from Godot docs: ResourceLoader.CacheMode
     */
    value class CacheMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Neither the main resource (the one requested to be loaded) nor any of its subresources are
             * retrieved from cache nor stored into it. Dependencies (external resources) are loaded with
             * `CacheMode.REUSE`.
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_IGNORE
             */
            val IGNORE: CacheMode get() = CacheMode(0L)
            /**
             * The main resource (the one requested to be loaded), its subresources, and its dependencies
             * (external resources) are retrieved from cache if present, instead of loaded. Those not cached
             * are loaded and then stored into the cache. The same rules are propagated recursively down the
             * tree of dependencies (external resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REUSE
             */
            val REUSE: CacheMode get() = CacheMode(1L)
            /**
             * Like `CacheMode.REUSE`, but the cache is checked for the main resource (the one requested to be
             * loaded) as well as for each of its subresources. Those already in the cache, as long as the
             * loaded and cached types match, have their data refreshed from storage into the already existing
             * instances. Otherwise, they are recreated as completely new objects.
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REPLACE
             */
            val REPLACE: CacheMode get() = CacheMode(2L)
            /**
             * Like `CacheMode.IGNORE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_IGNORE_DEEP
             */
            val IGNORE_DEEP: CacheMode get() = CacheMode(3L)
            /**
             * Like `CacheMode.REPLACE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REPLACE_DEEP
             */
            val REPLACE_DEEP: CacheMode get() = CacheMode(4L)
        }
    }
    // ===== END GENERATED ENUMS: ResourceLoader =====

    fun load(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let {
            Resource(GodotHandle(MemorySegment.ofAddress(it)))
        }

    fun loadTexture2D(path: String): Texture2D? =
        IosGodot.resourceLoaderLoad(path, "Texture2D").takeIf { it != 0L }?.let {
            Texture2D(GodotHandle(MemorySegment.ofAddress(it)))
        }

    fun loadAudioStream(path: String): AudioStream? =
        IosGodot.resourceLoaderLoad(path, "AudioStream").takeIf { it != 0L }?.let {
            AudioStream(GodotHandle(MemorySegment.ofAddress(it)))
        }

    fun loadPackedScene(path: String): PackedScene? =
        IosGodot.resourceLoaderLoad(path, "PackedScene").takeIf { it != 0L }?.let {
            PackedScene(GodotHandle(MemorySegment.ofAddress(it)))
        }

    fun loadLightmapGIData(path: String): LightmapGIData? =
        IosGodot.resourceLoaderLoad(path, "LightmapGIData").takeIf { it != 0L }?.let {
            LightmapGIData(GodotHandle(MemorySegment.ofAddress(it)))
        }


    /**
     * [loadThreadedGetStatusWithProgress]'s result (named `ThreadLoadStatus` before task 128 A, when
     * that name became Godot's enum `ResourceLoader.ThreadLoadStatus`).
     */
    data class ThreadLoadProgress(val status: ResourceLoader.ThreadLoadStatus, val progress: Double?)

    // Threaded loading: the request goes through the generic Variant call path (int return decodes
    // cleanly). The status poll ptrcalls load_threaded_get_status with the optional progress
    // out-Array supplied C-side, so progress is a real [0,1] value on iOS too.
    fun loadThreadedRequest(
        path: String,
        typeHint: String = "",
        useSubThreads: Boolean = false,
        cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE,
    ): GodotError =
        GodotError((ObjectCalls.callWithVariantArgs(loadThreadedRequestBind, singleton, listOf(path, typeHint, useSubThreads, cacheMode.value)) as? Number)?.toLong()
            ?: 0L)

    fun loadThreadedGetStatusWithProgress(path: String): ThreadLoadProgress {
        val (status, progress) = ObjectCalls.ptrcallLoadStatusWithProgress(loadThreadedGetStatusBind, singleton, path)
        return if (status < 0) {
            ThreadLoadProgress(ResourceLoader.ThreadLoadStatus.INVALID_RESOURCE, null)
        } else {
            ThreadLoadProgress(ResourceLoader.ThreadLoadStatus(status), progress)
        }
    }

    // After a threaded load completes the resource is in the ResourceLoader cache, so fetch it
    // through the same synchronous C-shim as load() (which references the RefCounted resource
    // correctly). The generic Variant-call path returned a handle whose PackedScene.instantiate()
    // silently yielded null on device.
    fun loadThreadedGet(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let { Resource(GodotHandle(MemorySegment.ofAddress(it))) }

    fun loadThreadedGetPackedScene(path: String): PackedScene? =
        IosGodot.resourceLoaderLoad(path, "PackedScene").takeIf { it != 0L }?.let { PackedScene(GodotHandle(MemorySegment.ofAddress(it))) }

    private val singleton by lazy { ObjectCalls.getSingleton("ResourceLoader") }
    private val loadThreadedRequestBind by lazy { ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_request", 3614384323L) }
    private val loadThreadedGetStatusBind by lazy { ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get_status", 4137685479L) }
    private val loadThreadedGetBind by lazy { ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get", 1748875256L) }
}

// KANAMA-IOS-HANDWRITTEN: [platform] GD global helpers (rand*, print) — Kotlin/native impls, bespoke.
object GD {
    fun randomize() {
    }

    fun randi(): Long =
        Random.nextLong().let { if (it == Long.MIN_VALUE) 0L else kotlin.math.abs(it) }

    fun randf(): Double =
        Random.nextDouble()

    fun randiRange(from: Long, to: Long): Long =
        if (to <= from) from else Random.nextLong(from, to + 1)

    fun randfRange(from: Double, to: Double): Double =
        if (to <= from) from else Random.nextDouble(from, to)

    // Godot's @GlobalScope.randfn: normally-distributed pseudo-random via Box-Muller.
    fun randfn(mean: Double, deviation: Double): Double {
        val u1 = Random.nextDouble().coerceAtLeast(Double.MIN_VALUE)
        val u2 = Random.nextDouble()
        val standard = kotlin.math.sqrt(-2.0 * kotlin.math.ln(u1)) * kotlin.math.cos(2.0 * Mathf.PI * u2)
        return mean + deviation * standard
    }

    // @GlobalScope print facade. iOS has no utility-function call path (see the isInstanceValid note
    // + roadmap), so these route to the Kotlin/Native console (device log / xcrun devicectl --console)
    // instead of Godot's print stream. Output is APPROXIMATE — right text, different sink — matching
    // the demos' debug-logging use. Godot's print() concatenates its args with no separator.
    private fun joinValues(values: Array<out Any?>): String =
        values.joinToString("") { it?.toString() ?: "<null>" }

    fun print(vararg values: Any?) = println(joinValues(values))

    fun printRich(vararg values: Any?) = println(joinValues(values))

    fun printErr(vararg values: Any?) = println(joinValues(values))

    fun printS(vararg values: Any?) = println(values.joinToString(" ") { it?.toString() ?: "<null>" })

    fun printRaw(vararg values: Any?) = kotlin.io.print(joinValues(values))

    fun printVerbose(vararg values: Any?) = println(joinValues(values))

    fun pushWarning(vararg values: Any?) = println("WARNING: " + joinValues(values))

    fun pushError(vararg values: Any?) = println("ERROR: " + joinValues(values))

    // @GlobalScope math facade (pure-Kotlin, matching the desktop GD utility helpers).
    fun signf(value: Double): Double = kotlin.math.sign(value)

    fun lerpf(from: Double, to: Double, weight: Double): Double =
        from + (to - from) * weight

    fun clampf(value: Double, min: Double, max: Double): Double =
        value.coerceIn(min, max)

    fun lerpAngle(from: Double, to: Double, weight: Double): Double =
        Mathf.lerpAngle(from, to, weight)

    fun remap(value: Double, istart: Double, istop: Double, ostart: Double, ostop: Double): Double =
        ostart + (ostop - ostart) * ((value - istart) / (istop - istart))

    fun degToRad(degrees: Double): Double = degrees * (Mathf.PI / 180.0)

    fun radToDeg(radians: Double): Double = radians * (180.0 / Mathf.PI)

    // @GlobalScope.is_instance_valid (task 98 mirror). Answered by the ObjectDB lookup of the
    // instance id the wrapper captured at construction (shim object_get_instance_from_id), so a
    // wrapper whose object was freed reports false without dereferencing the dead pointer —
    // the same contract as desktop GD.isInstanceValid. Non-object values are simply non-null.
    fun isInstanceValid(value: Any?): Boolean =
        when (value) {
            null -> false
            is GodotObject -> IosGodot.isInstanceIdValid(value.instanceId)
            else -> true
        }

    // @GlobalScope.is_instance_id_valid: the id-shaped counterpart of isInstanceValid.
    fun isInstanceIdValid(id: Long): Boolean = IosGodot.isInstanceIdValid(id)
}

inline fun <reified T> GodotObject.kotlinScriptInstance(): T? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(handle.segment.address()) as? T

inline fun <reified T> Node.kotlinScriptInstance(): T? =
    GodotObject(handle).kotlinScriptInstance<T>()

@OptIn(ExperimentalForeignApi::class)
// KANAMA-IOS-HANDWRITTEN: [glue] thin cinterop facade over the C shim helpers used by the bespoke
// classes above. Predates the generated ObjectCalls path; kept for the bespoke runtime.
internal object IosGodot {
    private const val LABEL_SET_TEXT_HASH = 83702148L
    private var labelSetTextBind = 0L

    fun setObjectText(objectHandle: Long, value: String): Boolean {
        val bind = labelSetTextBind()
        if (bind == 0L || objectHandle == 0L) {
            return false
        }
        net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall_string_arg(bind, objectHandle, value)
        return true
    }

    fun objectQueueFree(objectHandle: Long) {
        kanama_ios_godot_object_queue_free(objectHandle)
    }

    fun objectIsClass(objectHandle: Long, className: String): Boolean =
        kanama_ios_godot_object_is_class(objectHandle, className) != 0

    fun objectGetInstanceId(objectHandle: Long): Long =
        kanama_ios_godot_object_get_instance_id(objectHandle)

    fun isInstanceIdValid(instanceId: Long): Boolean =
        kanama_ios_godot_is_instance_id_valid(instanceId) != 0

    fun nodeIsInGroup(node: Long, groupName: String): Boolean =
        kanama_ios_godot_node_is_in_group(node, groupName) != 0

    fun inputEventIsPressed(event: Long): Boolean =
        kanama_ios_godot_input_event_is_pressed(event) != 0

    fun inputEventIsReleased(event: Long): Boolean =
        kanama_ios_godot_input_event_is_released(event) != 0

    fun inputEventMouseButtonGetButtonIndex(event: Long): Long =
        kanama_ios_godot_input_event_mouse_button_get_button_index(event)

    fun nodeAddChild(parent: Long, child: Long) {
        kanama_ios_godot_node_add_child(parent, child)
    }

    fun nodeRemoveChild(parent: Long, child: Long) {
        kanama_ios_godot_node_remove_child(parent, child)
    }

    fun nodeGetChildCount(node: Long): Long =
        kanama_ios_godot_node_get_child_count(node)

    fun nodeGetChild(node: Long, index: Int): Long =
        kanama_ios_godot_node_get_child(node, index)

    fun nodeGetNodeOrNull(node: Long, path: String): Long =
        kanama_ios_godot_node_get_node_or_null(node, path)

    fun nodeGetTree(node: Long): Long =
        kanama_ios_godot_node_get_tree(node)

    fun nodeGetViewport(node: Long): Long =
        kanama_ios_godot_node_get_viewport(node)

    fun nodeCreateTween(node: Long): Long =
        kanama_ios_godot_node_create_tween(node)

    fun nodeSetProcessInput(node: Long, enabled: Boolean) {
        kanama_ios_godot_node_set_process_input(node, if (enabled) 1 else 0)
    }

    fun nodeSetProcessUnhandledInput(node: Long, enabled: Boolean) {
        kanama_ios_godot_node_set_process_unhandled_input(node, if (enabled) 1 else 0)
    }

    fun node2dGetPosition(node: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_node2d_get_position(node, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun node2dSetPosition(node: Long, value: Vector2) {
        kanama_ios_godot_node2d_set_position(node, value.x.toDouble(), value.y.toDouble())
    }

    fun node2dGetScale(node: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_node2d_get_scale(node, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun node2dSetScale(node: Long, value: Vector2) {
        kanama_ios_godot_node2d_set_scale(node, value.x.toDouble(), value.y.toDouble())
    }

    fun node3dGetPosition(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_position)

    fun node3dSetPosition(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_position(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dGetRotation(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_rotation)

    fun node3dSetRotation(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_rotation(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dGetScale(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_scale)

    fun node3dSetScale(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_scale(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dGetGlobalPosition(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_global_position)

    fun node3dSetGlobalPosition(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_global_position(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dRotateY(node: Long, angle: Double) {
        kanama_ios_godot_node3d_rotate_y(node, angle)
    }

    fun canvasItemGetViewportRect(objectHandle: Long): Rect2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val width = alloc<DoubleVarCompat>()
            val height = alloc<DoubleVarCompat>()
            kanama_ios_godot_canvas_item_get_viewport_rect(
                objectHandle,
                x.ptr,
                y.ptr,
                width.ptr,
                height.ptr,
            )
            Rect2(Vector2(x.value, y.value), Vector2(width.value, height.value))
        }

    fun canvasItemGetLocalMousePosition(objectHandle: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_canvas_item_get_local_mouse_position(objectHandle, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun canvasItemHide(objectHandle: Long) {
        kanama_ios_godot_canvas_item_hide(objectHandle)
    }

    fun canvasItemShow(objectHandle: Long) {
        kanama_ios_godot_canvas_item_show(objectHandle)
    }

    fun canvasItemSetModulate(objectHandle: Long, color: Color) {
        kanama_ios_godot_canvas_item_set_modulate(
            objectHandle,
            color.r.toDouble(),
            color.g.toDouble(),
            color.b.toDouble(),
            color.a.toDouble(),
        )
    }

    fun packedSceneInstantiate(packedScene: Long, editState: Long): Long =
        kanama_ios_godot_packed_scene_instantiate(packedScene, editState)

    fun gpuParticles2dSetEmitting(particles: Long, value: Boolean) {
        kanama_ios_godot_gpu_particles2d_set_emitting(particles, if (value) 1 else 0)
    }

    fun gpuParticles2dSetLifetime(particles: Long, value: Double) {
        kanama_ios_godot_gpu_particles2d_set_lifetime(particles, value)
    }

    fun gpuParticles2dRestart(particles: Long, keepSeed: Boolean) {
        kanama_ios_godot_gpu_particles2d_restart(particles, if (keepSeed) 1 else 0)
    }

    fun gpuParticles3dSetEmitting(particles: Long, value: Boolean) {
        kanama_ios_godot_gpu_particles3d_set_emitting(particles, if (value) 1 else 0)
    }

    fun gpuParticles3dRestart(particles: Long, keepSeed: Boolean) {
        kanama_ios_godot_gpu_particles3d_restart(particles, if (keepSeed) 1 else 0)
    }

    fun collisionShape3dSetDisabled(shape: Long, disabled: Boolean) {
        kanama_ios_godot_collision_shape3d_set_disabled(shape, if (disabled) 1 else 0)
    }

    fun resourceLoaderLoad(path: String, typeHint: String): Long =
        kanama_ios_godot_resource_loader_load(path, typeHint)

    fun sprite2dSetTexture(sprite: Long, texture: Long) {
        kanama_ios_godot_sprite2d_set_texture(sprite, texture)
    }

    fun constructObject(className: String): Long =
        kanama_ios_godot_construct_object(className)

    fun audioStreamPlayerSetStream(player: Long, stream: Long) {
        kanama_ios_godot_audio_stream_player_set_stream(player, stream)
    }

    fun audioStreamPlayerSetVolumeDb(player: Long, volumeDb: Double) {
        kanama_ios_godot_audio_stream_player_set_volume_db(player, volumeDb)
    }

    fun audioStreamPlayerSetPitchScale(player: Long, pitchScale: Double) {
        kanama_ios_godot_audio_stream_player_set_pitch_scale(player, pitchScale)
    }

    fun audioStreamPlayerSetBus(player: Long, bus: String) {
        kanama_ios_godot_audio_stream_player_set_bus(player, bus)
    }

    fun audioStreamPlayerSetStreamPaused(player: Long, paused: Boolean) {
        kanama_ios_godot_audio_stream_player_set_stream_paused(player, if (paused) 1 else 0)
    }

    fun audioStreamPlayerPlay(player: Long, fromPosition: Double) {
        kanama_ios_godot_audio_stream_player_play(player, fromPosition)
    }

    fun objectEmitSignalInt(objectHandle: Long, signalName: String, value: Long): Int =
        kanama_ios_godot_object_emit_signal_int(objectHandle, signalName, value)

    fun objectEmitSignalVector2i(objectHandle: Long, signalName: String, x: Long, y: Long): Int =
        kanama_ios_godot_object_emit_signal_vector2i(objectHandle, signalName, x, y)

    fun objectConnect(sourceObject: Long, signalName: String, targetObject: Long, method: String, flags: Long): Long =
        kanama_ios_godot_object_connect(sourceObject, signalName, targetObject, method, flags)

    fun objectDisconnect(sourceObject: Long, signalName: String, targetObject: Long, method: String): Int =
        kanama_ios_godot_object_disconnect(sourceObject, signalName, targetObject, method)

    fun objectConnectCallable(sourceObject: Long, signalName: String, targetObject: Long, callbackId: Long, flags: Long): Long =
        kanama_ios_godot_object_connect_callable(sourceObject, signalName, targetObject, callbackId, flags)

    fun objectDisconnectCallable(sourceObject: Long, signalName: String, targetObject: Long, callbackId: Long): Int =
        kanama_ios_godot_object_disconnect_callable(sourceObject, signalName, targetObject, callbackId)

    fun tweenSetParallel(tween: Long, parallel: Int): Long =
        kanama_ios_godot_tween_set_parallel(tween, parallel)

    fun tweenTweenCallback(tween: Long, target: Long, method: String): Long =
        kanama_ios_godot_tween_tween_callback(tween, target, method)

    fun tweenTweenMethod(tween: Long, target: Long, method: String, from: Double, to: Double, duration: Double): Long =
        kanama_ios_godot_tween_tween_method(tween, target, method, from, to, duration)

    fun tweenKill(tween: Long) {
        kanama_ios_godot_tween_kill(tween)
    }

    fun viewportGetVisibleRect(viewport: Long): Rect2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val w = alloc<DoubleVarCompat>()
            val h = alloc<DoubleVarCompat>()
            kanama_ios_godot_viewport_get_visible_rect(viewport, x.ptr, y.ptr, w.ptr, h.ptr)
            Rect2(Vector2(x.value, y.value), Vector2(w.value, h.value))
        }

    private fun labelSetTextBind(): Long {
        if (labelSetTextBind == 0L) {
            labelSetTextBind = kanama_ios_godot_get_method_bind(
                "Label",
                "set_text",
                LABEL_SET_TEXT_HASH,
            )
        }
        return labelSetTextBind
    }

    private inline fun node3dGetVector3(
        node: Long,
        getter: (Long, kotlinx.cinterop.CPointer<DoubleVarCompat>?, kotlinx.cinterop.CPointer<DoubleVarCompat>?, kotlinx.cinterop.CPointer<DoubleVarCompat>?) -> Unit,
    ): Vector3 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val z = alloc<DoubleVarCompat>()
            getter(node, x.ptr, y.ptr, z.ptr)
            Vector3(x.value, y.value, z.value)
        }
}

@OptIn(ExperimentalForeignApi::class)
private typealias DoubleVarCompat = kotlinx.cinterop.DoubleVar

// Registry backing lambda/bound signal connections. A connection registers its
// callback here and passes the integer id to the C shim, which binds it to a
// custom Godot Callable. When the signal fires the shim calls back into
// kanamaIosRuntimeDispatchCallable; when Godot drops the connection it calls
// kanamaIosRuntimeReleaseCallable so the entry can be collected.
internal object IosCallableRegistry {
    private var nextId = 1L
    private val callbacks = HashMap<Long, (List<Any?>) -> Unit>()

    fun register(callback: (List<Any?>) -> Unit): Long {
        val id = nextId++
        callbacks[id] = callback
        return id
    }

    fun dispatch(callbackId: Long, args: List<Any?>) {
        callbacks[callbackId]?.invoke(args)
    }

    fun release(callbackId: Long) {
        callbacks.remove(callbackId)
    }

    /** Live entries; the self-test's leak rows compare it before and after (task 131). */
    val size: Int
        get() = callbacks.size
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_dispatch_callable")
fun kanamaIosRuntimeDispatchCallable(
    callbackId: Long,
    argumentCount: Int,
    argumentTypes: CPointer<IntVar>?,
    argumentValues: CPointer<LongVar>?,
) {
    // A throwing signal lambda is contained here (task 131): an exception crossing this @CName
    // export terminates the app. It is printed and reported as a Godot script error; the Callable
    // call itself still completes, as a GDScript lambda's runtime error does.
    try {
        val count = argumentCount.coerceIn(0, MAX_CALLABLE_ARGUMENTS)
        val args = ArrayList<Any?>(count)
        for (i in 0 until count) {
            val type = argumentTypes?.get(i) ?: VT_NIL
            val value = argumentValues?.get(i) ?: 0L
            args.add(
                when (type) {
                    VT_BOOL -> value != 0L
                    VT_INT -> value
                    VT_FLOAT -> Double.fromBits(value)
                    VT_OBJECT -> GodotObject.wrap(RawSegment.ofAddress(value))
                    else -> null
                },
            )
        }
        IosCallableRegistry.dispatch(callbackId, args)
    } catch (t: Throwable) {
        IosScriptErrors.report(t, "signal lambda")
    }
}

private const val MAX_CALLABLE_ARGUMENTS = 4
private const val VT_NIL = 0
private const val VT_BOOL = 1
private const val VT_INT = 2
private const val VT_FLOAT = 3
private const val VT_OBJECT = 24

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_release_callable")
fun kanamaIosRuntimeReleaseCallable(callbackId: Long) {
    IosCallableRegistry.release(callbackId)
}
